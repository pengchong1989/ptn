package com.nms.ui.ptn.statistics.lable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.report.SSLabel;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.tunnel.LspInfoService_MB;
import com.nms.model.report.StaticsticsService_MB;
import com.nms.model.util.ExportExcel;
import com.nms.model.util.Services;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ListingFilter;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTitle;

public class LableInfoController extends AbstractController{
	private LableInfoPanel view;
	private List<Integer> portIdList = new ArrayList<Integer>();
	private List<Integer> siteIdList = new ArrayList<Integer>();

	LableInfoController(LableInfoPanel lableInfoPanel){
		this.setView(lableInfoPanel);
	}
	
	@Override
	public void refresh() throws Exception {
		this.searchAndrefreshdata();
		
	}
	//导出统计数据保存到excel
	@Override
	public void export() throws Exception {
		List<SSLabel> infos = null;
		String result;
		ExportExcel export=null;
		try {
			// 得到页面信息
			infos =  this.view.getTable().getAllElement();
			export=new ExportExcel();
			//得到bean的集合转为  String[]的List
			List<String[]> beanData=export.tranListString(infos,"LableInfoPanel");
			//导出页面的信息-Excel
			result=export.exportExcel(beanData, "LableInfoPanel");
			//添加操作日志记录
			this.insertOpeLog(EOperationLogType.LABELEXPORT.getValue(),ResultString.CONFIG_SUCCESS, null, null);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			infos = null;
			result=null;
			export=null;
		}
	}
	
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
		AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac, 0,ResourceUtil.srcStr(StringKeysTitle.TIT_LABLE_INFO),"");		
	}
	// 页面初始化数据、点击刷新按钮刷新数据
	@SuppressWarnings("unchecked")
	private void searchAndrefreshdata() {
		StaticsticsService_MB service = null;
		try {
			if(!this.portIdList.isEmpty()){
				//Tunnel查询
				this.filterSSLabel("TUNNEL");
			}else if(!this.siteIdList.isEmpty()){
				//PW查询
				this.filterSSLabel("PW");
			}else{
				service = (StaticsticsService_MB) ConstantUtil.serviceFactory.newService_MB(Services.STATISTICS);
				ListingFilter filter = new ListingFilter();
				List<SSLabel> infos = (List<SSLabel>) filter.filterList(service.SSLabelSelect());
				this.view.clear();
				this.view.initData(infos);
				this.view.updateUI();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}
	
	public LableInfoPanel getView() {
		return view;
	}

	public void setView(LableInfoPanel view) {
		this.view = view;
	}

	/**
	 * pw类型根据网元过滤
	 */
	public void filterByPw(List<Integer> siteIdList) {
		this.portIdList.clear();
		this.siteIdList = siteIdList;
		this.filterSSLabel("PW");
	}

	/**
	 * tunnel类型根据网元+端口过滤
	 */
	public void filterByTunnel(List<Integer> siteIdList, List<Integer> portIdList) {
		this.portIdList = portIdList;
		this.siteIdList = siteIdList;
		this.filterSSLabel("TUNNEL");
	}
	
	@SuppressWarnings("unchecked")
	private void filterSSLabel(String type) {
		StaticsticsService_MB service = null;
		LspInfoService_MB lspService = null;
		Map<Integer, Integer> lspLabelMap = null;
		PortService_MB portService = null;
		SiteService_MB siteService = null;
		List<PortInst> portList = new ArrayList<PortInst>();
		Map<Integer, String> siteNameMap = new HashMap<Integer, String>();
		try {
			List<SSLabel> ssLabelList = new ArrayList<SSLabel>();
			//根据网元或者网元和端口查询
			lspService = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO);
			if("TUNNEL".equals(type)){
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				for (Integer siteId : this.siteIdList) {
					portList.addAll(portService.selectNniPortname(siteId));
					siteNameMap.put(siteId, siteService.getSiteName(siteId));
				}
				if(this.portIdList.isEmpty()){
					for (PortInst port : portList) {
						this.portIdList.add(port.getPortId());
					}
				}
				lspLabelMap = lspService.selectLspLabelByPort(this.portIdList);
				for (int portId : this.portIdList) {
					SSLabel ssLabel = new SSLabel();
					for (PortInst p : portList) {
						if(p.getPortId() == portId){
							ssLabel.setPortId(portId);
							ssLabel.setPortName(p.getPortName());
							ssLabel.setSiteId(p.getSiteId());
							ssLabel.setSiteName(siteNameMap.get(p.getSiteId()));
							break;
						}
					}
					ssLabel.setLabelType("TUNNEL");
					ssLabel.setLspUsed(lspLabelMap.get(portId)+"");
					ssLabelList.add(ssLabel);
				}
			}else{
				//pw类型根据网元查询
				lspLabelMap = lspService.selectLspLabelBySite(this.siteIdList);
				service = (StaticsticsService_MB) ConstantUtil.serviceFactory.newService_MB(Services.STATISTICS);
				List<SSLabel> labelList = service.SSLabelSelect();
				for (Integer siteId : this.siteIdList) {
					for (SSLabel ssLabel : labelList) {
						if(siteId == ssLabel.getSiteId()){
							ssLabel.setPortName("ALL");
							ssLabel.setLabelType("PW");
							ssLabel.setLspUsed(Integer.parseInt(ssLabel.getLspUsed()) - lspLabelMap.get(siteId)+"");
							ssLabel.setLspCanUsed(null);
							ssLabel.setLspCount(null);
							ssLabelList.add(ssLabel);
							break;
						}
					}
				}
			}
			ListingFilter filter = new ListingFilter();
			List<SSLabel> infos = (List<SSLabel>) filter.filterList(ssLabelList);
			this.view.clear();
			this.view.initData(infos);
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
			UiUtil.closeService_MB(lspService);
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(siteService);
		}
	}

	public List<Integer> getPortIdList() {
		return portIdList;
	}
	
	public List<Integer> getSiteIdList() {
		return siteIdList;
	}
}