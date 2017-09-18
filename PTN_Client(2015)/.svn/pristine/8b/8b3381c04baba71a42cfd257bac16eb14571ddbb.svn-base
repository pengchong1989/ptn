package com.nms.ui.ptn.statistics.site;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.ExportExcel;
import com.nms.model.util.Services;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysTab;

/**
 * 网元配置信息的事件处理类
 * @author sy
 *
 */
public class SiteCountStatisticsPanelController extends AbstractController {
	
	private SiteCountStatisticsPanel view;
	
	public SiteCountStatisticsPanelController(SiteCountStatisticsPanel siteCountStatisticsPanel) {
		this.setView(siteCountStatisticsPanel);
	}

	@Override
	public void refresh() throws Exception {
		this.searchAndrefreshdata();
	}
	
	//导出统计数据保存到excel
	@Override
	public void export() throws Exception {
		
		List<SiteInst> infos = null;
		String result;
		ExportExcel export=null;
		// 得到页面信息
		try {
			infos  =  this.view.getTable().getAllElement();
			export = new ExportExcel();
			//得到bean的集合转为  String[]的List
			List<String[]> beanData = export.tranListString(infos,"siteCount");
			//导出页面的信息-Excel
			result = export.exportExcel(beanData, "siteCount");
			//添加操作日志记录
			this.insertOpeLog(EOperationLogType.SITESTATISTICSEXPORT.getValue(),ResultString.CONFIG_SUCCESS, null, null);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			infos = null;
			result = null;
		}
	}
    // 页面初始化数据、点击刷新按钮刷新数据
	private void searchAndrefreshdata() {
		List<SiteInst> infos = null;
		
		try {
			infos = this.getSiteList(true);
			this.view.clear();
			this.view.initData(infos);
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			infos = null;
		}
	}
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
		AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac, 0,ResourceUtil.srcStr(StringKeysMenu.TAB_NECARDCOUNT_T),"");		
	}
	
	/**
	 * 从数据库中获取site的结果集
	 * @param flag true=显示统计列表上需要显示的属性
	 * @return
	 * @throws Exception 
	 */
	private List<SiteInst> getSiteList(boolean flag) throws Exception{
		SiteService_MB siteService = null;
		List<SiteInst> infos = null;
		List<SiteInst> siteInfoList = null;
		int countAll = 0;
		try {
			infos = new ArrayList<SiteInst>();
			siteInfoList = new ArrayList<SiteInst>();
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			infos = siteService.queryBySiteCount();			
			if(infos != null && infos.size()>0){
				for(SiteInst siteInfo : infos){
					countAll += siteInfo.getNeCount();
				}
				for(SiteInst siteInfo : infos){
					siteInfo.putClientProperty("neCount",siteInfo.getNeCount());
					siteInfo.putClientProperty("cardCount", myPercent(siteInfo.getNeCount(),countAll));
					siteInfo.setSitePercent(myPercent(siteInfo.getNeCount(),countAll));
					siteInfoList.add(siteInfo);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally{
			UiUtil.closeService_MB(siteService);
		}
		return infos;
	}

	public SiteCountStatisticsPanel getView() {
		return view;
	}

	public void setView(SiteCountStatisticsPanel view) {
		this.view = view;
	}
	
	/**
	 *根据给的值计算百分比
	 * @param y
	 * @param z
	 * @return
	 */
	private String myPercent(int y,int z)  
	{  
	    String baifenbi="";//接受百分比的值  
	    double baiy=y*1.0;  
	    double baiz=z*1.0;  
	    double fen=baiy/baiz;  
	    DecimalFormat df1 = new DecimalFormat("##.00%");    //##.00%   百分比格式，后面不足2位的用0补齐  
	    baifenbi= df1.format(fen);   
	    return baifenbi;  
	} 
}
