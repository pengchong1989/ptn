package com.nms.ui.topology;



import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import twaver.TWaverConst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.CommonBean;
import com.nms.db.bean.system.Field;
import com.nms.db.bean.system.NetWork;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.system.FieldService_MB;
import com.nms.model.system.NetService_MB;
import com.nms.model.system.SubnetService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.ui.Ptnf;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.TelnetUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.manager.xmlbean.Telnet;
import com.nms.ui.ptn.alarm.view.SiteCurrentAlarmPanel;
import com.nms.ui.ptn.alarm.view.SiteHisAlarmPanel;
import com.nms.ui.ptn.basicinfo.dialog.field.AddNetWorkDialog;
import com.nms.ui.ptn.basicinfo.dialog.field.MoveSiteDialog;
import com.nms.ui.ptn.basicinfo.dialog.group.view.AddGroupDialog;
import com.nms.ui.ptn.basicinfo.dialog.site.AddSiteDialog;
import com.nms.ui.ptn.basicinfo.dialog.site.CopySiteDialog;
import com.nms.ui.ptn.basicinfo.dialog.site.SelectSiteDialog;
import com.nms.ui.ptn.basicinfo.dialog.subnet.view.AddSubnetDialog;
import com.nms.ui.ptn.ne.allConfig.view.DataDownLoadDialog1;
import com.nms.ui.ptn.ne.site.WHSiteAttributePanel;
import com.nms.ui.ptn.performance.view.CurrentPerformancePanel;
import com.nms.ui.ptn.performance.view.HisPerformancePanel;
import com.nms.ui.ptn.safety.roleManage.RoleRoot;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import com.nms.ui.ptn.statistics.site.SiteStatisticsPanel;
import com.nms.ui.topology.routebusiness.view.PingCmdDialog;
import com.nms.ui.topology.routebusiness.view.QuerySfpPowDialog;
import com.nms.ui.topology.routebusiness.view.RouteBusinessDialog;


/**
 * 拓扑的菜单处理类
 * 
 * @author kk
 * 
 */
class TopoMenu {
	
	

	/**
	 * 创建右键菜
	 * 
	 * @param key
	 *            双语用的key 用此ke来判断是什么操作。
	 * @return 菜单对象
	 * @throws Exception
	 */
	protected static JMenuItem createMenu(final String key) throws Exception {

		if (null == key && "".equals(key)) {
			throw new Exception("key is null");
		}
		JMenuItem menuItem = new JMenuItem(ResourceUtil.srcStr(key));
		/*
		 * 添加 权限验证
		 */
		RoleRoot roleRoot=new RoleRoot();
		if(key.equals(StringKeysTitle.TIT_CREATE_SITE)
				||key.equals(StringKeysMenu.MENU_DELETENE)
				||key.equals(StringKeysTitle.TIT_UPDATE_SITE)
				||key.equals(StringKeysMenu.MENU_COPYSITE)
				){
			roleRoot.setItemEnbale(menuItem, RootFactory.DEPLOY_MANAGE);
		}
		else if(  key.equals(StringKeysTitle.TIT_CREATE_FIELD)
				||key.equals(StringKeysBtn.BTN_DELETE)
				||key.equals(StringKeysTitle.TIT_CREATE_SUBNET)
				||key.equals(StringKeysMenu.MENU_DELETESUBNET)
				||key.equals(StringKeysTitle.TIT_UPDATE_SUBNET)
				||key.equals(StringKeysTitle.TIT_UPDATE_FIELD)
				||key.equals(StringKeysTitle.TIT_TOPO_FOUND)
				||key.equals(StringKeysMenu.MENU_FIELDALLINFO)
				||key.equals(StringKeysMenu.MENU_SITEINFO)
		){
			roleRoot.setItemEnbale(menuItem, RootFactory.TOPOLOGY_MANAGE);
		}
		roleRoot=null;
		try {

			menuItem.addActionListener(new java.awt.event.ActionListener() {
				@Override
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					try {
						if (key.equals(StringKeysTitle.TIT_CREATE_FIELD)) { // 新建域
							new AddNetWorkDialog(null, true, "");
						} else if (key.equals(StringKeysTitle.TIT_CREATE_SITE)) { // 新建网元

							AddSiteDialog addSiteDialog = new AddSiteDialog(true, "");
							addSiteDialog.setLocation(UiUtil.getWindowWidth(addSiteDialog.getWidth()), UiUtil.getWindowHeight(addSiteDialog.getHeight()));
							addSiteDialog.setVisible(true);
						} else if (key.equals(StringKeysMenu.MENU_SELECTSITE)) { // 选中网元
							SelectSiteDialog selectsitedialog = new SelectSiteDialog(NetworkElementPanel.getNetworkElementPanel().getNetwork(), true);
							selectsitedialog.setLocation(UiUtil.getWindowWidth(selectsitedialog.getWidth()), UiUtil.getWindowHeight(selectsitedialog.getHeight()));
							selectsitedialog.setVisible(true);
					    }else if (key.equals(StringKeysMenu.MENU_AUTOLAYOUT)) { // 自动布局
							NetworkElementPanel.getNetworkElementPanel().getNetwork().doLayout(TWaverConst.LAYOUT_CIRCULAR);
						} else if (key.equals(StringKeysTitle.TIT_UPDATE_FIELD)) {//修改域
							if (null != NetworkElementPanel.getNetworkElementPanel().getBox().getLastSelectedElement().getUserObject()) {
								NetWork netWork = (NetWork) NetworkElementPanel.getNetworkElementPanel().getBox().getLastSelectedElement().getUserObject();
								new AddNetWorkDialog(null, true, netWork.getNetWorkId() + "");
							}
						} else if (key.equals(StringKeysBtn.BTN_DELETE)) { // 删除域
							if (null != NetworkElementPanel.getNetworkElementPanel().getBox().getLastSelectedElement().getUserObject()) {
								NetWork netWork = (NetWork) NetworkElementPanel.getNetworkElementPanel().getBox().getLastSelectedElement().getUserObject();
								delField(netWork);
							}
						} else if (key.equals(StringKeysMenu.MENU_CONFIGURATION)) { // 网元配置管理
							SiteInst siteInst = (SiteInst) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();
							ConstantUtil.siteId = siteInst.getSite_Inst_Id();
						//	Ptnf.getPtnf().mainTabPanel(ConstantUtil.jTabbedPane, siteInst.getCellId() + "/" + ResourceUtil.srcStr(StringKeysTab.TAB_NETWORK), new NetWorkInfoPanel());
							Ptnf.getPtnf().mainTabPanel(ConstantUtil.jTabbedPane, siteInst.getCellId() + "/" +ResourceUtil.srcStr(StringKeysTab.TAB_NETWORK), new WHSiteAttributePanel());
						} else if (key.equals(StringKeysTitle.TIT_MOVE_SITE_FIELD)) { // 移动网元
							SiteInst siteInst = (SiteInst) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();
							new MoveSiteDialog(siteInst);
						} else if (key.equals(StringKeysMenu.MENU_DELETENE)) { // 删除网元
							SiteInst siteInst = (SiteInst) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();															
							SiteUtil siteUtil = new SiteUtil();
							int flag = ((SiteUtil) siteUtil).SiteTypeOnlineUtil(siteInst.getSite_Inst_Id());
							if(flag==1){
								DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_DELETEONLINE));
								return;
							}else{
							    deleteSite(siteInst);						
							    NetworkElementPanel.getNetworkElementPanel().showTopo(true);
							}
						} else if (key.equals(StringKeysTitle.TIT_UPDATE_SITE)) { // 修改网元
							SiteInst siteInst = (SiteInst) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();
							AddSiteDialog addSiteDialog = new AddSiteDialog(true, siteInst.getSite_Inst_Id() + "");
							addSiteDialog.setLocation(UiUtil.getWindowWidth(addSiteDialog.getWidth()), UiUtil.getWindowHeight(addSiteDialog.getHeight()));
							addSiteDialog.setVisible(true);
						} else if (key.equals(StringKeysMenu.MENU_LOGINSITE)) { // 登陆网元
							SiteInst siteInst = (SiteInst) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();
							DispatchUtil siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
							List<SiteInst> siteInstList = new ArrayList<SiteInst>();
							siteInstList.add(siteInst);
							siteDispatch.siteLogin(siteInstList);
						} else if (key.equals(StringKeysTitle.TIT_UPDATE_SUBNET)) { // 修改子网
							Field field = (Field) NetworkElementPanel.getNetworkElementPanel().getBox().getLastSelectedElement().getUserObject();
							new AddSubnetDialog(null, true, field);
						} else if (key.equals(StringKeysTitle.TIT_CREATE_SUBNET)) { // 新建子网
							new AddSubnetDialog(null, true, null);
						} else if (key.equals(StringKeysMenu.MENU_COPYSITE)) { // 复制网元
							SiteInst siteInst = (SiteInst) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();
							new CopySiteDialog(true, siteInst, null);
						} else if (key.equals(StringKeysMenu.MENU_DELETESUBNET)) { // 删除子网
							Field field = (Field) NetworkElementPanel.getNetworkElementPanel().getBox().getLastSelectedElement().getUserObject();
							deleteSubnet(field);
						} else if (key.equals(StringKeysMenu.MENU_SELECT_HOMEROUTEBUSINESS)) { // 查询本站路由业务
							SiteInst siteInst = (SiteInst) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();
							new RouteBusinessDialog(siteInst,"NORMAL");
						} else if (key.equals(StringKeysMenu.MENU_SELECT_XC_ROUTEBUSINESS)) { // 查询经过路由业务
							SiteInst siteInst = (SiteInst) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();
							new RouteBusinessDialog(siteInst,"XC");
						} else if(key.equals(StringKeysTitle.TIT_QUERY_SFPPOW)){//查询光功率
							Segment seg = (Segment) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();		
							new QuerySfpPowDialog(seg);
						}else if (key.equals(StringKeysTitle.TIT_CREATE_GROUP)) { //新建组
							new AddGroupDialog(null, true);
						}else if(key.equals(StringKeysMenu.TAB_CURRPERFOR_T1)){//当前性能
							Ptnf.getPtnf().mainTabPanel(ConstantUtil.jTabbedPane, ResourceUtil.srcStr(StringKeysTab.TAB_CURRPERFOR), new CurrentPerformancePanel());
					   }else if(key.equals(StringKeysMenu.TAB_HISPERFOR_T1)){//历史性能
						   Ptnf.getPtnf().mainTabPanel(ConstantUtil.jTabbedPane, ResourceUtil.srcStr(StringKeysTab.TAB_HISPERFOR), new HisPerformancePanel());
					   }else if(key.equals(StringKeysMenu.MENU_SYNCHRO)){//同步
						   SiteInst siteInst = (SiteInst) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();
							ConstantUtil.siteId = siteInst.getSite_Inst_Id();						
						   new DataDownLoadDialog1(siteInst,true,"synchro");
						   
					   }else if (key.equals(StringKeysMenu.MENU_CURRENTALARM)) { // 当前告警
							SiteInst siteInst = (SiteInst) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();
							ConstantUtil.siteId = siteInst.getSite_Inst_Id();						
							Ptnf.getPtnf().mainTabPanel(ConstantUtil.jTabbedPane, siteInst.getCellId() + "/" +ResourceUtil.srcStr(StringKeysTab.TAB_CURRALARM), new SiteCurrentAlarmPanel());
						}else if (key.equals(StringKeysMenu.MENU_HISALARM)) { // 历史告警
							SiteInst siteInst = (SiteInst) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();
							ConstantUtil.siteId = siteInst.getSite_Inst_Id();
							Ptnf.getPtnf().mainTabPanel(ConstantUtil.jTabbedPane, siteInst.getCellId() + "/" +ResourceUtil.srcStr(StringKeysTab.TAB_HISALARM), new SiteHisAlarmPanel());
						}
					   else if(key.equals(StringKeysMenu.MENU_PING)){//ping功能
							SiteInst siteInst = (SiteInst) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();
							new PingCmdDialog(siteInst.getCellDescribe());
						
						} else if(key.equals(StringKeysMenu.MENU_TOPO_FOUND)){//自动发现拓扑
							List<Segment> ss=new ArrayList<Segment>();
							List<Segment> seg=new ArrayList<Segment>();
							String result=ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
							DispatchUtil dispatchUtil = new DispatchUtil(RmiKeys.RMI_SITE);
							seg = dispatchUtil.topological(null, ConstantUtil.fieldId,seg);
							SiteService_MB siteService = null;
							try {
								siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
							if(seg!=null){
								for(int i=0;i<seg.size();i++){
									Segment old=new Segment();
									CommonBean aCom=new CommonBean();
									CommonBean zCom=new CommonBean();
									CommonBean oldaCom=new CommonBean();
									CommonBean oldzCom=new CommonBean();
									seg.get(i).setShowSiteAname(siteService.select(seg.get(i).getASITEID()).getCellId());
									seg.get(i).setShowSiteZname(siteService.select(seg.get(i).getZSITEID()).getCellId());
									seg.get(i).setShowPortAname(getPortName(seg.get(i).getAPORTID()));
									seg.get(i).setShowPortZname(getPortName(seg.get(i).getZPORTID()));		
									aCom.setTopuSiteName(seg.get(i).getShowSiteAname());
									zCom.setTopuSiteName(seg.get(i).getShowSiteZname());
									aCom.setTopuPortName(seg.get(i).getShowPortAname());
									zCom.setTopuPortName(seg.get(i).getShowPortZname());
									aCom.setTopuPortType(seg.get(i).getaPortInst().getPortType());
									zCom.setTopuPortType(seg.get(i).getzPortInst().getPortType());
									aCom.setTopuMaxFrameSize(seg.get(i).getaPortInst().getPortAttr().getMaxFrameSize());
									zCom.setTopuMaxFrameSize(seg.get(i).getzPortInst().getPortAttr().getMaxFrameSize());
									aCom.setTopuIsOccupy(seg.get(i).getaPortInst().getIsOccupy());
									zCom.setTopuIsOccupy(seg.get(i).getzPortInst().getIsOccupy());
									seg.get(i).setzCommonBean(zCom);
							    	seg.get(i).setaCommonBean(aCom);
								
									oldaCom.setTopuSiteName(seg.get(i).getShowSiteAname());
									oldzCom.setTopuSiteName(seg.get(i).getShowSiteZname());
									oldaCom.setTopuPortName(getPortName(seg.get(i).getoldaPortInst().getPortId()));
									oldzCom.setTopuPortName(getPortName(seg.get(i).getoldzPortInst().getPortId()));
									oldaCom.setTopuPortType(seg.get(i).getoldaPortInst().getPortType());
									oldzCom.setTopuPortType(seg.get(i).getoldzPortInst().getPortType());
									oldaCom.setTopuMaxFrameSize(seg.get(i).getoldaPortInst().getPortAttr().getMaxFrameSize());
									oldzCom.setTopuMaxFrameSize(seg.get(i).getoldzPortInst().getPortAttr().getMaxFrameSize());
									oldaCom.setTopuIsOccupy(seg.get(i).getoldaPortInst().getIsOccupy());
									oldzCom.setTopuIsOccupy(seg.get(i).getoldzPortInst().getIsOccupy());
									old.setaCommonBean(oldaCom);
									old.setzCommonBean(oldzCom);
									
									seg.get(i).setaPortInst(null);
									seg.get(i).setzPortInst(null);
									seg.get(i).setoldaPortInst(null);
									seg.get(i).setoldzPortInst(null);
									ss.add(old);
								}
							}
							} catch (Exception e) {
								ExceptionManage.dispose(e, this.getClass());
							} finally {				
								UiUtil.closeService_MB(siteService);
							}
							result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
							DialogBoxUtil.succeedDialog(null, result);
							for(int i=0;i<seg.size();i++){
								this.insertOpeLog(EOperationLogType.SEGEMENTINSERT.getValue(), result, ss.get(i), seg.get(i));	
							}
							NetworkElementPanel.getNetworkElementPanel().showTopo(true);
						}else if(key.equals(StringKeysMenu.MENU_FIELDALLINFO))
						{
							Ptnf.getPtnf().mainTabPanel(ConstantUtil.jTabbedPane, ResourceUtil.srcStr(StringKeysTab.TAB_SITEINFO), new SiteStatisticsPanel(ConstantUtil.fieldId));
						}else if(key.equals(StringKeysMenu.MENU_SITEINFO))
						{
							SiteInst siteInst = (SiteInst) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();
							ConstantUtil.siteId = siteInst.getSite_Inst_Id();
//							Ptnf.getPtnf().mainTabPanel(ConstantUtil.jTabbedPane, ResourceUtil.srcStr(StringKeysTab.TAB_SITEINFO), new SiteStatisticsPanel(ConstantUtil.fieldId));
							AddSiteDialog addSiteDialog = new AddSiteDialog(true, siteInst.getSite_Inst_Id() + "",1);
							addSiteDialog.setLocation(UiUtil.getWindowWidth(addSiteDialog.getWidth()), UiUtil.getWindowHeight(addSiteDialog.getHeight()));
							addSiteDialog.setVisible(true);
						
						}
						else if(key.equals(StringKeysMenu.MENU_TELNET)){//TELNET功能
							SiteInst siteInst = (SiteInst) NetworkElementPanel.getNetworkElementPanel().getSelectElement().getUserObject();
							Telnet telnet=new Telnet();
							telnet.setNeIp(siteInst.getCellDescribe());
							TelnetUtil telnetUtil=new TelnetUtil();
							telnetUtil.writeLoginConfig(telnet);
							Process p = Runtime.getRuntime().exec("cmd.exe /C \""+System.getProperty("user.dir")+"/telnet.bat\"");							
							p.waitFor();
							p.destroy();
						
						}
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					}
				}

				private void insertOpeLog(int operationType, String result, Segment oldSeg, Segment newSeg){
					newSeg.setAqosqueue(null);
					newSeg.setZqosqueue(null);
					AddOperateLog.insertOperLog(null, operationType, result, oldSeg, newSeg, newSeg.getASITEID(), newSeg.getNAME(), "segmentSearch");
					AddOperateLog.insertOperLog(null, operationType, result, oldSeg, newSeg, newSeg.getZSITEID(), newSeg.getNAME(), "segmentSearch");					
				}
				
				private String getPortName(int portId) {
					PortService_MB portService = null;
					try {
						portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
						return portService.selectPortybyid(portId).getPortName();
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					}
					finally
					{
						UiUtil.closeService_MB(portService);
					}
					return "";
				}

			});

		} catch (Exception e) {
			throw e;
		}
		return menuItem;
	}
	
	


	
	
	/**
	 * 删除域
	 * 
	 * @author kk
	 * @Exception 异常对象
	 */
	private static void delField(NetWork netWork) {
		List<Field> fields = null;
		FieldService_MB fieldService = null;
		// 添加日志记录
		NetService_MB netService = null;
		try {
			netService = (NetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.NETWORKSERVICE);
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			fields = fieldService.queryByNetWorkid(netWork.getNetWorkId());

			if (fields.size() == 0) {
				netService.delete(netWork);
			} else {
				DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_EXIST_GROUP));
				insertOpeLogNet(EOperationLogType.EXIST_GROUP.getValue(),ResultString.CONFIG_FAILED, null, netWork);	
			}
			insertOpeLogNet(EOperationLogType.FIELDDELETE.getValue(), ResultString.CONFIG_SUCCESS, null, netWork);	
			NetworkElementPanel.getNetworkElementPanel().showTopo(true);
		} catch (Exception e) {
			ExceptionManage.dispose(e, TopoMenu.class);
		} finally {
			UiUtil.closeService_MB(netService);
			UiUtil.closeService_MB(fieldService);
		}

	}

	private static void insertOpeLogNet(int operationType, String result, Object oldMac, Object newMac){
		NetWork net=(NetWork)newMac;
		String netName=net.getNetWorkName();		
		newMac=null;
		AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac, 0,netName,"");		
	}
	
	/**
	 * 删除网元
	 * 
	 * @author kk
	 * @Exception 异常对象
	 */
	private static void deleteSite(SiteInst siteInst) throws Exception {
		int returnValue;
		SiteService_MB siteService = null;
		int resultDelete = 0;
		String resultStr = "";
		try {			
			int result = DialogBoxUtil.confirmDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_IS_DELETE));
			DispatchUtil siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
			if (result == 0) {
				// zhangkun 修改2013-9-12
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				// 验证网元是否可以被删除
				returnValue = siteService.isDeleteSite(siteInst);
				if (returnValue == 0) {
//					resultDelete = siteService.delete(siteInst);
					resultStr = siteDispatch.excuteDelete(siteInst);
					if (resultStr.equals(ResultString.CONFIG_SUCCESS)) {
						resultDelete = 1;
					}
					// NetworkElementPanel.getNetworkElementPanel().showTopo(true);
				} else if (returnValue == 3) {
					DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_AC_NEHASSEGMENT));
					insertOpeLogSite(EOperationLogType.SITEEXISTAC.getValue(), ResultString.CONFIG_FAILED, null, siteInst);	
			//		UiUtil.insertOperationLog(EOperationLogType.SITEEXISTAC.getValue());
				} else if (returnValue == 2) {
					DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_TUNNEL_NEHASSEGMENT));
					insertOpeLogSite(EOperationLogType.SITEEXITTUNNEL.getValue(), ResultString.CONFIG_FAILED, null, siteInst);	
				} else if (returnValue == 1) {
					DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_NEHASSEGMENT));
					insertOpeLogSite(EOperationLogType.SITEEXITSEGMENT.getValue(),ResultString.CONFIG_FAILED, null, siteInst);	
				}

				String config;
				if (resultDelete > 0) {
					config=ResultString.CONFIG_SUCCESS;
				} else {
					config=ResultString.CONFIG_FAILED;
				}
				insertOpeLogSite(EOperationLogType.SITELISTDELETE.getValue(), config, null, siteInst);	
				// // 验证网元是否可以被删除
				// if (siteService.isDeleteSite(siteInst)) {
				// siteService.delete(siteInst);
				// NetworkElementPanel.getNetworkElementPanel().showTopo(true);
				// } else {
				// DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_NEHASSEGMENT));
				// }
//				NetworkElementPanel.getNetworkElementPanel().showTopo(true);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	private static void insertOpeLogSite(int operationType, String result, Object oldMac, Object newMac){
		SiteInst net=(SiteInst)newMac;
		String siteName=net.getCellId();
		int siteId=net.getSite_Inst_Id();
		newMac=null;
		AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac,siteId,siteName,"");
	}
	
	/**
	 * 删除子网
	 * 
	 * @param field
	 */
	private static void deleteSubnet(Field field) {
		SubnetService_MB service = null;
		// 添加日志记录
		boolean flag = true;
		boolean Single;
		try {
			service = (SubnetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SUBNETSERVICE);
			int result = DialogBoxUtil.confirmDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_IS_DELETE));
			if (result == 0) {
				// 验证子网下有没有网元
				Single = service.isSingle(field);
				if (Single) {
					flag = false;
				} else {
					service.delete(field);			
				}
			}
			if (!flag) {
				DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_SUBNET_DELETE_NODE));
				insertOpeLogSub(EOperationLogType.SITEEXITSUB.getValue(),ResultString.CONFIG_FAILED, null, field);	
				return;
			}
			insertOpeLogSub(EOperationLogType.SUBNETDELETE.getValue(), ResultString.CONFIG_SUCCESS, null, field);	
			NetworkElementPanel.getNetworkElementPanel().showTopo(true);
		} catch (Exception e) {
			ExceptionManage.dispose(e, TopoMenu.class);
		} finally {
			UiUtil.closeService_MB(service);
		}
	}
	
	
	private static void insertOpeLogSub(int operationType, String result, Object oldMac, Object newMac){
		Field field=(Field)newMac;
		String fieldName=field.getFieldName();		
		newMac=null;
		AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac, 0,fieldName,"");		
	}
	
}
