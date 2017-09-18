package com.nms.ui.ptn.statistics.site;

import java.util.ArrayList;
import java.util.List;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.util.ExportExcel;
import com.nms.model.util.Services;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTab;


/**
 * 网元配置信息的事件处理类
 * @author sy
 *
 */
public class SiteStatisticsPanelController extends AbstractController {
	
	private SiteStatisticsPanel view;
	
	public SiteStatisticsPanelController(SiteStatisticsPanel siteStatisticsPanel) {
		this.setView(siteStatisticsPanel);
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
			infos =  this.view.getTable().getAllElement();
			export= new ExportExcel();
			//得到bean的集合转为  String[]的List
			List<String[]> beanData = export.tranListString(infos,"siteinfoTable");
			//导出页面的信息-Excel
			result=export.exportExcel(beanData, "siteinfoTable");
			//添加操作日志记录			
			this.insertOpeLog(EOperationLogType.SITESTATISTICSEXPORT.getValue(),ResultString.CONFIG_SUCCESS, null, null);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			infos = null;
			result=null;
		}
	}
	
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
		AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac, 0,ResourceUtil.srcStr(StringKeysTab.TAB_SITEINFO),"");		
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
	
	
	/**
	 * 从数据库中获取site的结果集
	 * @param flag true=显示统计列表上需要显示的属性
	 * @return
	 * @throws Exception 
	 */
	private List<SiteInst> getSiteList(boolean flag) throws Exception{
		SiteInst siteinst = null;
		SiteService_MB siteService = null;
		List<SiteInst> infos = null;
		SegmentService_MB segmentService= null;
		List<Segment> segmentList = null;
		PortService_MB portService = null;
		try {
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			if(view.getFileId() == 0)
			{
				infos = siteService.selectRootSite(ConstantUtil.user);	
			}
			else
			{
				infos = siteService.selectByFileId(view.getFileId());
			}
			if(flag){
				if(infos!=null && infos.size()>0){
					for(int i = 0;i < infos.size();i++) {
						siteinst = infos.get(i);
						siteinst.putClientProperty("siteVersions", siteinst.getVersions());
						siteinst.putClientProperty("ipAddress", siteinst.getCellDescribe());
						siteinst.putClientProperty("ipMask", "255.255.255.0");
						segmentList = segmentService.selectBySite(siteinst.getSite_Inst_Id());
						if(null != segmentList && !segmentList.isEmpty())
						{
							siteinst.putClientProperty("cableNumber",segmentList.size());
							siteinst.putClientProperty("cablePort", getCablePortNames(siteinst.getSite_Inst_Id(),portService,segmentList));
						}else
						{
							siteinst.putClientProperty("cableNumber",0);
							siteinst.putClientProperty("cablePort", "");	
						}
						siteinst.putClientProperty("createTime", siteinst.getCreateTime());
					}
				}
			}
		} finally{
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(segmentService);
			UiUtil.closeService_MB(portService);
		}
		return infos;
	}
	
	private String getCablePortNames(int siteId,PortService_MB portService,List<Segment> segmentList)
	{
		String portName = "";
		List<PortInst> portInstList = null;
		List<Integer> portList = null;
		try {
			portList = new ArrayList<Integer>();
			for(Segment segment : segmentList)
			{
				portList.add(segment.getAPORTID());
				portList.add(segment.getZPORTID());
			}
			if(!portList.isEmpty())
			{
			  portInstList = portService.getAllPortByIdsAndSiteId(portList,siteId);
				if(null != portInstList && !portInstList.isEmpty())
				{
					for(int i =0 ;i<portInstList.size() ; i++)
					{
						if(i == 0)
						{
							portName +=portInstList.get(i).getPortName();
						}
						else{
							portName += "/"+portInstList.get(i).getPortName();
						}
					}
				}	
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			 portInstList = null;
		}
		return portName;
	}
	
	
	
	public void setView(SiteStatisticsPanel view) {
		this.view = view;
	}

	public SiteStatisticsPanel getView() {
		return view;
	};

}
