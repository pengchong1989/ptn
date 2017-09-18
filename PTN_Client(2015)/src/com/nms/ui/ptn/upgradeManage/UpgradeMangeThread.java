package com.nms.ui.ptn.upgradeManage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JTextField;
import javax.swing.table.TableModel;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.nms.db.bean.equipment.manager.UpgradeManage;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.enums.EOperationLogType;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.systemconfig.dialog.bsUpdate.UpdateLog;

public class UpgradeMangeThread implements Runnable{
	private SiteInst siteInst;
	private int type;
	private List<ZipEntry> zipEntries;
	private ZipFile zipFile;
	private ConcurrentHashMap<SiteInst, String> values;
	private TableModel tableModel;
	private int index;
	public UpgradeMangeThread(SiteInst siteInst,int type,List<ZipEntry> zipEntries,ConcurrentHashMap<SiteInst, String> values,ZipFile zipFile,TableModel tableModel ,int index) {
		this.siteInst = siteInst;
		this.type = type;
		this.zipEntries = zipEntries;
		this.values = values;
		this.zipFile = zipFile;
		this.tableModel = tableModel;
		this.index = index;
	}
	
	/**
	 * 升级主流程
	 */
	@Override
	public void run() {
		System.out.println(siteInst.getCellDescribe()+"开始");
		UpdateLog nowupdateLog = new UpdateLog();
		UpdateLog updateLog = new UpdateLog();
		//第一步：查询摘要
		List<UpgradeManage> upgradeManages = softwareSummary(siteInst, type);
		updateLog.setUpgradeManages(upgradeManages);
		if(upgradeManages == null || upgradeManages.size()==0){
			siteInst.setResult(ResourceUtil.srcStr(StringKeysLbl.LBL_QUERY_SUMMAY_FAIL));
			values.put(siteInst, ResourceUtil.srcStr(StringKeysLbl.LBL_QUERY_SUMMAY_FAIL));
			tableModel.setValueAt(ResourceUtil.srcStr(StringKeysLbl.LBL_QUERY_SUMMAY_FAIL), index, 5);
			System.out.println(siteInst.getCellDescribe()+ResourceUtil.srcStr(StringKeysLbl.LBL_QUERY_SUMMAY_FAIL));
			return;
		}
		
		//第二步：过滤升级文件
		List<ZipEntry> entriesNeed = new ArrayList<ZipEntry>();
		UpgradeManageUtil.getSummary(nowupdateLog.getUpgradeManages(),zipEntries, zipFile, siteInst, upgradeManages, entriesNeed,1,false);
		if(entriesNeed.size() == 0){
			siteInst.setResult(ResourceUtil.srcStr(StringKeysLbl.LBL_ZIPENTRY_EMPTY));
			values.put(siteInst, ResourceUtil.srcStr(StringKeysLbl.LBL_ZIPENTRY_EMPTY));
			tableModel.setValueAt(ResourceUtil.srcStr(StringKeysLbl.LBL_ZIPENTRY_EMPTY), index, 5);
			System.out.println(siteInst.getCellDescribe()+ResourceUtil.srcStr(StringKeysLbl.LBL_ZIPENTRY_EMPTY));
			return;
		}
		tableModel.setValueAt(siteInst.getAllFileName(), index, 2);
		//第三步：下发过滤后的摘要
		String sendSummary = sendSummary(siteInst);
		if(!sendSummary.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
			siteInst.setResult(sendSummary);
			values.put(siteInst, sendSummary);
			System.out.println(siteInst.getCellDescribe()+sendSummary);
			return;
		}
		
		//第四步：升级开始
		String result = UpgradeManageUtil.upgradeProcedure(entriesNeed, new JTextField(), siteInst, type, null, zipFile,tableModel,index);
		siteInst.setResult(result);
		tableModel.setValueAt(result, index, 5);
		AddOperateLog.insertOperLog(new PtnButton(""), EOperationLogType.EQUIMENT.getValue(), result, 
				updateLog, nowupdateLog, ConstantUtil.siteId, ResourceUtil.srcStr(StringKeysTitle.TIT_VERSIONS_UPGRADE), "updateLog");
		values.put(siteInst, result);
		
		for(SiteInst inst: values.keySet()){
			System.out.println(inst.getCellDescribe()+"++++"+values.get(inst));
		}
	}
	
	
	/**
	 * 查询摘要
	 * @param siteInst
	 * @param type
	 * @return
	 */
	private List<UpgradeManage> softwareSummary(SiteInst siteInst,int type){
		List<UpgradeManage> upgradeManages = null;
		try {
			DispatchUtil siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
			siteInst.setCardNumber(type+"");
			siteInst.setBs(null);
			upgradeManages = siteDispatch.softwareSummary(siteInst);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return upgradeManages;
	}
	
	/**
	 * 下发摘要
	 * @param siteInst
	 * @return
	 */
	private String sendSummary(SiteInst siteInst){
		String resultSummary = "";
		try {
			DispatchUtil siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
			resultSummary = siteDispatch.sendSummary(siteInst);
		} catch (Exception e) {
			ExceptionManage.dispose(e, e.getClass());
		}
		
		return resultSummary;
	}
}
