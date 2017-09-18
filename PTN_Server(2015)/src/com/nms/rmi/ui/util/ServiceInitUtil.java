package com.nms.rmi.ui.util;

import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.perform.PerformanceTaskInfo;
import com.nms.db.bean.system.Field;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.ERunStates;
import com.nms.drive.service.DriveService;
import com.nms.drivechenxiao.service.CXDriveService;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.perform.PerformanceTaskService_MB;
import com.nms.model.system.FieldService_MB;
import com.nms.model.util.CodeConfigItem;
import com.nms.model.util.ServiceFactory;
import com.nms.model.util.Services;
import com.nms.rmi.ui.AutoDatabaseBackThradUtil;
import com.nms.rmi.ui.CheckBoxPanel;
import com.nms.service.bean.AlarmObjectService;
import com.nms.service.bean.PerformanceObjectService;
import com.nms.service.impl.dispatch.ARPDispatch;
import com.nms.service.impl.dispatch.AcDispatch;
import com.nms.service.impl.dispatch.AclDispatch;
import com.nms.service.impl.dispatch.AdministrateConfigDispath;
import com.nms.service.impl.dispatch.AlarmDispatch;
import com.nms.service.impl.dispatch.AllConfigDispatch;
import com.nms.service.impl.dispatch.BfdDispatch;
import com.nms.service.impl.dispatch.BlackWhiteMacDispatch;
import com.nms.service.impl.dispatch.CCNDispatch;
import com.nms.service.impl.dispatch.CardDispatch;
import com.nms.service.impl.dispatch.CccDispatch;
import com.nms.service.impl.dispatch.CesDispatch;
import com.nms.service.impl.dispatch.ClockFrequDispatch;
import com.nms.service.impl.dispatch.ClockSourceDispatch;
import com.nms.service.impl.dispatch.DualDispatch;
import com.nms.service.impl.dispatch.DualWhDispatch;
import com.nms.service.impl.dispatch.E1Dispatch;
import com.nms.service.impl.dispatch.ETHLinkOAMConfigDispath;
import com.nms.service.impl.dispatch.ElanDispatch;
import com.nms.service.impl.dispatch.ElineDispatch;
import com.nms.service.impl.dispatch.EthLoopDispatch;
import com.nms.service.impl.dispatch.EthOAMConfigDispath;
import com.nms.service.impl.dispatch.EthServiceDispatch;
import com.nms.service.impl.dispatch.EtreeDispatch;
import com.nms.service.impl.dispatch.ExpMappingPhbDispatch;
import com.nms.service.impl.dispatch.ExternalClockDispatch;
import com.nms.service.impl.dispatch.FrequencySiteDispatch;
import com.nms.service.impl.dispatch.GroupSpreadDispatch;
import com.nms.service.impl.dispatch.L2cpDispath;
import com.nms.service.impl.dispatch.LineClockInterfaceDispatch;
import com.nms.service.impl.dispatch.LoopProRotateDispatch;
import com.nms.service.impl.dispatch.MCNDispatch;
import com.nms.service.impl.dispatch.MacLearningLimitDispatch;
import com.nms.service.impl.dispatch.MacManagementDispatch;
import com.nms.service.impl.dispatch.MacStudyDispatch;
import com.nms.service.impl.dispatch.MspDispatch;
import com.nms.service.impl.dispatch.OSPFAREADispatch;
import com.nms.service.impl.dispatch.OSPFINFODispatch;
import com.nms.service.impl.dispatch.OSPFInterfaceDispatch;
import com.nms.service.impl.dispatch.OamDispatch;
import com.nms.service.impl.dispatch.OamPingDispatch;
import com.nms.service.impl.dispatch.PerformanceDispatch;
import com.nms.service.impl.dispatch.PhbMappingExpDispatch;
import com.nms.service.impl.dispatch.PmLimiteDispatch;
import com.nms.service.impl.dispatch.Port2LayerAttrDispatch;
import com.nms.service.impl.dispatch.PortDiscardDispatch;
import com.nms.service.impl.dispatch.PortDispatch;
import com.nms.service.impl.dispatch.PortLagDispatch;
import com.nms.service.impl.dispatch.PortStmDispatch;
import com.nms.service.impl.dispatch.PortStmTimeslotDispatch;
import com.nms.service.impl.dispatch.ProtectRorateDispatch;
import com.nms.service.impl.dispatch.ProtectRotateDispatch;
import com.nms.service.impl.dispatch.PtpSiteDispatch;
import com.nms.service.impl.dispatch.PwBufferDispatch;
import com.nms.service.impl.dispatch.PwDispatch;
import com.nms.service.impl.dispatch.QosDispatch;
import com.nms.service.impl.dispatch.QosQueueDispatch;
import com.nms.service.impl.dispatch.RedistributeDispatch;
import com.nms.service.impl.dispatch.SegmentDispatch;
import com.nms.service.impl.dispatch.ServiceDispatch;
import com.nms.service.impl.dispatch.SiteDispatch;
import com.nms.service.impl.dispatch.SlotResetDispatch;
import com.nms.service.impl.dispatch.SmartFanDispatch;
import com.nms.service.impl.dispatch.SoftwareUpdateDispatch;
import com.nms.service.impl.dispatch.StaticUniDispatch;
import com.nms.service.impl.dispatch.TMCOAMConfigDispath;
import com.nms.service.impl.dispatch.TMPOAMConfigDispath;
import com.nms.service.impl.dispatch.TMSOAMConfigDispath;
import com.nms.service.impl.dispatch.TdmLoopBackDispatch;
import com.nms.service.impl.dispatch.TimePortConfigDispatch;
import com.nms.service.impl.dispatch.TimeSyncDispatch;
import com.nms.service.impl.dispatch.TmsOamDispatch;
import com.nms.service.impl.dispatch.TunnelDispatch;
import com.nms.service.impl.dispatch.TunnelProtectDispatch;
import com.nms.service.impl.dispatch.V35PortDispatch;
import com.nms.service.impl.dispatch.WrappingDispatch;
import com.nms.service.impl.dispatch.rmi.AlarmDispatchI;
import com.nms.service.impl.dispatch.rmi.CardDispatchI;
import com.nms.service.impl.dispatch.rmi.ClockDispatchI;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.dispatch.rmi.PerformanceDispatchI;
import com.nms.service.impl.dispatch.rmi.QosDispatchI;
import com.nms.service.impl.dispatch.rmi.RmiInitI;
import com.nms.service.impl.dispatch.rmi.SegmentDispatchI;
import com.nms.service.impl.dispatch.rmi.SiteDispatchI;
import com.nms.service.impl.dispatch.rmi.impl.RmiInitImpl;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.PerforTaskThreadFactory;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.alarm.controller.CirculateCurrectTime;
import com.nms.ui.ptn.alarm.controller.CurAlarmTimerTask;
import com.nms.ui.ptn.alarm.controller.OperateCurrAlarmTask;
import com.nms.ui.ptn.alarm.controller.QueryCurrAlarmBySitesTask;
import com.nms.ui.ptn.alarm.controller.SiteConnectTask;
import com.nms.ui.ptn.performance.thread.PerformanceTimerThread;

/**
 * service端界面的线程类，所有启动时要开启的线程都写在里面
 * 
 * @author kk
 * 
 */
public class ServiceInitUtil {

	private Registry registry = null;

	public ServiceInitUtil(Registry registry) throws Exception {
		this.registry = registry;
		this.initDate();
		this.initSite();
		this.initRmi();
		this.initThread();
	}
	public ServiceInitUtil(Registry registry,int label) throws Exception {
		this.registry = registry;
		this.initRmi();
	}
	
	/**
	 * 初始化数据
	 * 
	 * @throws Exception
	 */
	public void initDate() throws Exception {
		ServiceFactory serviceFactory = null;
//		Properties properties = null;
		
		try {

			// 初始化工厂类
			serviceFactory = new ServiceFactory();
//			properties = new Properties();
//			properties.put(ServiceFactory.HOSTNAME, "192.168.200.212");
//			properties.put(ServiceFactory.PTNUSER, "admin");
//			serviceFactory.startup(properties);
			ConstantUtil.serviceFactory = serviceFactory;

			// 初始化告警
			ConstantUtil.alarmObjectService = new AlarmObjectService();
			ConstantUtil.PerformanceObjectService = new PerformanceObjectService();
			// 初始武汉驱动
			ConstantUtil.driveService = new DriveService();
			ConstantUtil.driveService.setAlarmObjectService(ConstantUtil.alarmObjectService);
			ConstantUtil.driveService.setPerformanceObjectService(ConstantUtil.PerformanceObjectService);
			ConstantUtil.driveService.init();
			// 初始晨晓驱动
			ConstantUtil.cxDriveService = new CXDriveService();
			ConstantUtil.cxDriveService.setAlarmObjectService(ConstantUtil.alarmObjectService);
			
			initNeAddress();//加载网元内存信息
			ExceptionManage.infor("加载网元信息", this.getClass());
		} catch (Exception e) {
			throw e;
		}

	}

	private void initNeAddress(){
		SiteService_MB siteServiceMB = null;
		List<SiteInst> siteInsts = null;
		FieldService_MB fieldService = null;
		
		try {
			siteServiceMB = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			siteInsts = siteServiceMB.selectByISmsite();
			for(SiteInst siteInst: siteInsts){
				if(siteInst.getManufacturer() == EManufacturer.WUHAN.getValue()){
					List<Field> fields = fieldService.selectByFieldId(siteInst.getFieldID());
					if(fields.size()>0){
						if("field".equals(fields.get(0).getObjectType())){//对应域
							//获得真实ne地址
							ConstantUtil.mSiteMap.put((fields.get(0).getGroupId()*256+Integer.parseInt(siteInst.getSite_Hum_Id()))+"/"+siteInst.getCellDescribe(),siteInst.getSite_Inst_Id());
						}else if("subnet".equals(fields.get(0).getObjectType())){//对应子网
							
							//获取域id
							Field field = new Field();
							field.setId(fields.get(0).getParentId());
							fields = fieldService.select(field);
							if(fields.size()>0){
								if("field".equals(fields.get(0).getObjectType())){
									ConstantUtil.mSiteMap.put((fields.get(0).getGroupId()*256+Integer.parseInt(siteInst.getSite_Hum_Id()))+"/"+siteInst.getCellDescribe(),siteInst.getSite_Inst_Id());
								}
							}
						}
					}
				}
				List<SiteInst> asiteInsts = siteServiceMB.querySitesByIp(siteInst.getCellDescribe());
				HashMap<Integer, Integer> ne_id = new HashMap<Integer, Integer>();
				asiteInsts.add(siteInst);
				for(SiteInst asiteInst:asiteInsts){
					List<Field> fields = fieldService.selectByFieldId(asiteInst.getFieldID());
					if(fields.size()>0){
						if("field".equals(fields.get(0).getObjectType())){//对应域
							//获得真实ne地址
							ne_id.put(fields.get(0).getGroupId()*256+Integer.parseInt(asiteInst.getSite_Hum_Id()), asiteInst.getSite_Inst_Id());
						}else if("subnet".equals(fields.get(0).getObjectType())){//对应子网
							
							//获取域id
							Field field = new Field();
							field.setId(fields.get(0).getParentId());
							fields = fieldService.select(field);
							if(fields.size()>0){
								if("field".equals(fields.get(0).getObjectType())){
									ne_id.put(fields.get(0).getGroupId()*256+Integer.parseInt(asiteInst.getSite_Hum_Id()), asiteInst.getSite_Inst_Id());
								}
							}
						}
					}
				}
				ConstantUtil.m_aSiteMap.put(siteInst.getSite_Inst_Id(), ne_id);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(siteServiceMB);
			UiUtil.closeService_MB(fieldService);
		}
	}
	
	/**
	 * 初始化网元
	 * 
	 * @throws Exception
	 */
	private void initSite() throws Exception {
		SiteDispatch siteDispatch = new SiteDispatch();
		siteDispatch.siteLogin();
	}

	/**
	 * 初始化所有线程
	 * 
	 * @throws Exception
	 */
	private void initThread() throws Exception {

		// 查询主动上报告警线程
		if(null==ThreadUtil.curAlarmTimerTask){
			ThreadUtil.curAlarmTimerTask = new CurAlarmTimerTask();
		}
		ThreadUtil.curAlarmTimerTask.startThread();

		// 武汉网元失连计数判断
		if(null==ThreadUtil.siteConnectTask){
			ThreadUtil.siteConnectTask=new SiteConnectTask();
		}
		ThreadUtil.siteConnectTask.startThread();
		
		//处理长期性能任务
		if(null == ThreadUtil.performanceTimerThread)
		{
			ThreadUtil.performanceTimerThread = new PerformanceTimerThread();
		}
		ThreadUtil.performanceTimerThread.startThread();
		
		//控制告警轮询
		CodeConfigItem	codeConfigItem = CodeConfigItem.getInstance();
		if(codeConfigItem.getAlarmPoll() == 1)
		{
			// (武汉)网管轮询设备所有未失连网元的当前告警
			if(null==ThreadUtil.queryCurrAlarmBySitesTask){
				ThreadUtil.queryCurrAlarmBySitesTask = new QueryCurrAlarmBySitesTask();
			}
			ThreadUtil.queryCurrAlarmBySitesTask.startThread();
	
			//(武汉)处理网管轮询网元当前告警
			if(null==ThreadUtil.operateCurrAlarmTask){
				ThreadUtil.operateCurrAlarmTask = new OperateCurrAlarmTask();
			}
			ThreadUtil.operateCurrAlarmTask.startThread();
		}
		
		//(武汉)主动给在线网元校时
		if(null==ThreadUtil.circulateCurrectTime){
			ThreadUtil.circulateCurrectTime = new CirculateCurrectTime();
		}
		ThreadUtil.circulateCurrectTime.startThread();
		
		// 晨晓监控是否有失连网元线程
//		if(null==ThreadUtil.guardianshipThread){
//			ThreadUtil.guardianshipThread=new GuardianshipThread();
//		}
//		ThreadUtil.guardianshipThread.startThread();

		// 开启长期性能任务
		startPerformanceTask();
		
		//开启自动的默认备份数据 不影响其他的启动线程任务
		CheckBoxPanel checkBoxPanel = new CheckBoxPanel();
		List<String> tableNames = new ArrayList<String>();
		long cycleTime = ServerConstant.CYCLETIME*60*60*1000;
		AutoDatabaseBackThradUtil autoDatabaseBackThradUtil = new AutoDatabaseBackThradUtil();
		String[] tables = new String[]{ServerConstant.BACKUPS_ALL,ServerConstant.BACKUPS_USER,ServerConstant.BACKUPS_SITE};
		try {
			for (int i = 0; i<tables.length;i++) {
				tableNames.addAll(checkBoxPanel.getTableNameForXml(tables[i]));
			}
		  autoDatabaseBackThradUtil.createThread(tableNames, ServerConstant.AUTODATABACK_FILE,0, cycleTime);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 checkBoxPanel = null;
			 tableNames = null;
			 autoDatabaseBackThradUtil = null;
			 tables = null;
		}
		
	}

	/**
	 * 长期性能任务
	 * 
	 * @throws Exception
	 */
	private void startPerformanceTask() throws Exception {
		PerformanceTaskService_MB service = null;
		List<PerformanceTaskInfo> taskInfos = null;
		PerforTaskThreadFactory factory = null;
		try {
			factory = PerforTaskThreadFactory.getInstance();
			service = (PerformanceTaskService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PerformanceTask);
			taskInfos = service.select();
			if(taskInfos != null && taskInfos.size()>0){
				for(PerformanceTaskInfo task : taskInfos){
					if(task.getRunstates() == ERunStates.RUN){
						factory.createPerforTaskThread();
						break;
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
			taskInfos = null;
			factory = null;
		}
	}

	/**
	 * 初始化rmi接口
	 * 
	 * @throws Exception
	 */
	private void initRmi() throws Exception {
		this.registry(RmiKeys.RMI_AC, registry);
		this.registry(RmiKeys.RMI_ADMINISTRATECONFIG, registry);
		this.registry(RmiKeys.RMI_ALARM, registry);
		this.registry(RmiKeys.RMI_ALLCONFIG, registry);
		this.registry(RmiKeys.RMI_TIMESYNC, registry);
		this.registry(RmiKeys.RMI_CARD, registry);
		this.registry(RmiKeys.RMI_CCN, registry);
		this.registry(RmiKeys.RMI_CES, registry);
		this.registry(RmiKeys.RMI_CLOCKFREQU, registry);
		this.registry(RmiKeys.RMI_CLOCKSOURCE, registry);
		this.registry(RmiKeys.RMI_E1, registry);
		this.registry(RmiKeys.RMI_ELAN, registry);
		this.registry(RmiKeys.RMI_ELINE, registry);
		this.registry(RmiKeys.RMI_ETHLINKOAMCONFIG, registry);
		this.registry(RmiKeys.RMI_ETHOAMCONFIG, registry);
		this.registry(RmiKeys.RMI_ETREE, registry);
		this.registry(RmiKeys.RMI_EXPMAPPINGPHB, registry);
		this.registry(RmiKeys.RMI_EXTERNALCLOCK, registry);
		this.registry(RmiKeys.RMI_FREQUENCYSITE, registry);
		this.registry(RmiKeys.RMI_GROUPSPREAD, registry);
		this.registry(RmiKeys.RMI_LINECLOCKINTERFACE, registry);
		this.registry(RmiKeys.RMI_MCN, registry);
		this.registry(RmiKeys.RMI_OAM, registry);
		this.registry(RmiKeys.RMI_OSPFAREA, registry);
		this.registry(RmiKeys.RMI_OSPFINFO, registry);
		this.registry(RmiKeys.RMI_OSPFINTERFACE, registry);
		this.registry(RmiKeys.RMI_PERFORMANCE, registry);
		this.registry(RmiKeys.RMI_PHBMAPPINGEXP, registry);
		this.registry(RmiKeys.RMI_PORT, registry);
		this.registry(RmiKeys.RMI_PORTLAG, registry);
		this.registry(RmiKeys.RMI_PORTSTM, registry);
		this.registry(RmiKeys.RMI_PORTSTMTIMESLOT, registry);
		this.registry(RmiKeys.RMI_PROTECTRORATE, registry);
		this.registry(RmiKeys.RMI_PROTECTROTATE, registry);
		this.registry(RmiKeys.RMI_PTPSITE, registry);
		this.registry(RmiKeys.RMI_PW, registry);
		this.registry(RmiKeys.RMI_QOS, registry);
		this.registry(RmiKeys.RMI_QOSQUEUE, registry);
		this.registry(RmiKeys.RMI_REDISTRIBUTE, registry);
		this.registry(RmiKeys.RMI_SEGMENT, registry);
		this.registry(RmiKeys.RMI_SITE, registry);
		this.registry(RmiKeys.RMI_SLOTRESET, registry);
		this.registry(RmiKeys.RMI_SOFTWAREUPDATE, registry);
		this.registry(RmiKeys.RMI_STATICUNI, registry);
		this.registry(RmiKeys.RMI_TDMLOOPBACK, registry);
		this.registry(RmiKeys.RMI_TIMEPORTCONFIG, registry);
		this.registry(RmiKeys.RMI_TMCOAMCONFIG, registry);
		this.registry(RmiKeys.RMI_TMPOAMCONFIG, registry);
		this.registry(RmiKeys.RMI_TMSOAMCONFIG, registry);
		this.registry(RmiKeys.RMI_TMSOAMDISPATCH, registry);
		this.registry(RmiKeys.RMI_TUNNEL, registry);
		this.registry(RmiKeys.RMI_WRAPPING, registry);
		this.registry(RmiKeys.RMI_INIT, registry);
		this.registry(RmiKeys.RMI_QINQ, registry);
		this.registry(RmiKeys.RMI_PMLIMITE, registry);
		this.registry(RmiKeys.RMI_MSPPROTECT, registry);
		this.registry(RmiKeys.RMI_DUALPROTECT, registry);
		this.registry(RmiKeys.RMI_DUAL, registry);
		this.registry(RmiKeys.RMI_PWBUFFER, registry);
		this.registry(RmiKeys.RMI_BLACKLISTMAC, registry);
		this.registry(RmiKeys.RMI_OAMPING, registry);
		this.registry(RmiKeys.RMI_EHTLOOP, registry);
		this.registry(RmiKeys.RMI_ACL, registry);
		this.registry(RmiKeys.RMI_MACLEARNINGLIMIT, registry);
		this.registry(RmiKeys.RMI_BLACKWHITEMAC, registry);
		this.registry(RmiKeys.RMI_V35PORT, registry);
		this.registry(RmiKeys.RMI_LOOPPROROTATE, registry);
		this.registry(RmiKeys.RMI_SMARTFAN, registry);
		this.registry(RmiKeys.RMI_ETHSERVICE,registry);
		this.registry(RmiKeys.RMI_TUNNELPROTECT,registry);
		this.registry(RmiKeys.RMI_L2CPSERVICE, registry);
		this.registry(RmiKeys.RMI_PORTDISCARD, registry);
		this.registry(RmiKeys.RMI_PORT_2LAYER_ATTR, registry);
		this.registry(RmiKeys.RMI_SERVICE, registry);
		this.registry(RmiKeys.RMI_MACSTUDY, registry);
		this.registry(RmiKeys.RMI_CCC, registry);
		this.registry(RmiKeys.RMI_BFD, registry);
		this.registry(RmiKeys.RMI_ARP, registry);
	}

	/**
	 * 注册
	 * 
	 * @param key
	 * @param registry
	 * @throws Exception
	 */
	private void registry(String key, Registry registry) throws Exception {

		try {
			if (RmiKeys.RMI_AC.equals(key)) {
				acDispatch = new AcDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(acDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_ADMINISTRATECONFIG.equals(key)) {
				administrateConfigDispath = new AdministrateConfigDispath();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(administrateConfigDispath, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_ALARM.equals(key)) {
				alarmDispatch = new AlarmDispatch();
				alarmDispatchI = (AlarmDispatchI) UnicastRemoteObject.exportObject(alarmDispatch, 1);
				registry.rebind(key, alarmDispatchI);
			} else if (RmiKeys.RMI_ALLCONFIG.equals(key)) {
				allConfigDispatch = new AllConfigDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(allConfigDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if (RmiKeys.RMI_TIMESYNC.equals(key)) {
				timesyncDispatch = new TimeSyncDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(timesyncDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} 
			else if (RmiKeys.RMI_CARD.equals(key)) {
				cardDispatch = new CardDispatch();
				cardDispatchI = (CardDispatchI) UnicastRemoteObject.exportObject(cardDispatch, 1);
				registry.rebind(key, cardDispatchI);
			} else if (RmiKeys.RMI_CES.equals(key)) {
				cesDispatch = new CesDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(cesDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_CLOCKFREQU.equals(key)) {
				clockFrequDispatch = new ClockFrequDispatch();
				clockDispatchI = (ClockDispatchI) UnicastRemoteObject.exportObject(clockFrequDispatch, 1);
				registry.rebind(key, clockDispatchI);
			} else if (RmiKeys.RMI_CLOCKSOURCE.equals(key)) {
				clockSourceDispatch = new ClockSourceDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(clockSourceDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_E1.equals(key)) {
				e1Dispatch = new E1Dispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(e1Dispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_ELAN.equals(key)) {
				elanDispatch = new ElanDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(elanDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_ELINE.equals(key)) {
				elineDispatch = new ElineDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(elineDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_ETHLINKOAMCONFIG.equals(key)) {
				ethLinkOAMConfigDispath = new ETHLinkOAMConfigDispath();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(ethLinkOAMConfigDispath, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_ETHOAMCONFIG.equals(key)) {
				ethOAMConfigDispath = new EthOAMConfigDispath();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(ethOAMConfigDispath, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_ETREE.equals(key)) {
				etreeDispatch = new EtreeDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(etreeDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_EXPMAPPINGPHB.equals(key)) {
				expMappingPhbDispatch = new ExpMappingPhbDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(expMappingPhbDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_EXTERNALCLOCK.equals(key)) {
				externalClockDispatch = new ExternalClockDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(externalClockDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_FREQUENCYSITE.equals(key)) {
				frequencySiteDispatch = new FrequencySiteDispatch();
				clockDispatchI = (ClockDispatchI) UnicastRemoteObject.exportObject(frequencySiteDispatch, 1);
				registry.rebind(key, clockDispatchI);
			} else if (RmiKeys.RMI_GROUPSPREAD.equals(key)) {
				groupSpreadDispatch = new GroupSpreadDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(groupSpreadDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_LINECLOCKINTERFACE.equals(key)) {
				lineClockInterfaceDispatch = new LineClockInterfaceDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(lineClockInterfaceDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_MCN.equals(key)) {
				mcnDispatch = new MCNDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(mcnDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_OAM.equals(key)) {
				oamDispatch = new OamDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(oamDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_OSPFAREA.equals(key)) {
				oSPFAREADispatch = new OSPFAREADispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(oSPFAREADispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_OSPFINFO.equals(key)) {
				ospfinfoDispatch = new OSPFINFODispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(ospfinfoDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_OSPFINTERFACE.equals(key)) {
				ospfInterfaceDispatch = new OSPFInterfaceDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(ospfInterfaceDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_PERFORMANCE.equals(key)) {
				performanceDispatch = new PerformanceDispatch();
				performanceDispatchI = (PerformanceDispatchI) UnicastRemoteObject.exportObject(performanceDispatch, 1);
				registry.rebind(key, performanceDispatchI);
			} else if (RmiKeys.RMI_PHBMAPPINGEXP.equals(key)) {
				phbMappingExpDispatch = new PhbMappingExpDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(phbMappingExpDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_PHBMAPPINGEXP.equals(key)) {
				phbMappingExpDispatch = new PhbMappingExpDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(phbMappingExpDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_PORT.equals(key)) {
				portDispatch = new PortDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(portDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_PORTLAG.equals(key)) {
				portLagDispatch = new PortLagDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(portLagDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_PORTSTM.equals(key)) {
				portStmDispatch = new PortStmDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(portStmDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_PORTSTMTIMESLOT.equals(key)) {
				portStmTimeslotDispatch = new PortStmTimeslotDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(portStmTimeslotDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_PROTECTRORATE.equals(key)) {
				protectRorateDispatch = new ProtectRorateDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(protectRorateDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_PROTECTROTATE.equals(key)) {
				protectRotateDispatch = new ProtectRotateDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(protectRotateDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_PTPSITE.equals(key)) {
				ptpSiteDispatch = new PtpSiteDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(ptpSiteDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_PW.equals(key)) {
				pwDispatch = new PwDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(pwDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_QOS.equals(key)) {
				qosDispatch = new QosDispatch();
				qosDispatchI = (QosDispatchI) UnicastRemoteObject.exportObject(qosDispatch, 1);
				registry.rebind(key, qosDispatchI);
			} else if (RmiKeys.RMI_QOSQUEUE.equals(key)) {
				qosQueueDispatch = new QosQueueDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(qosQueueDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_REDISTRIBUTE.equals(key)) {
				redistributeDispatch = new RedistributeDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(redistributeDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_SEGMENT.equals(key)) {
				segmentDispatch = new SegmentDispatch();
				segmentDispatchI = (SegmentDispatchI) UnicastRemoteObject.exportObject(segmentDispatch, 1);
				registry.rebind(key, segmentDispatchI);
			} else if (RmiKeys.RMI_SITE.equals(key)) {
				siteDispatch = new SiteDispatch();
				siteDispatchI = (SiteDispatchI) UnicastRemoteObject.exportObject(siteDispatch, 1);
				registry.rebind(key, siteDispatchI);
			} else if (RmiKeys.RMI_SLOTRESET.equals(key)) {
				slotResetDispatch = new SlotResetDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(slotResetDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_SOFTWAREUPDATE.equals(key)) {
				softwareUpdateDispatch = new SoftwareUpdateDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(softwareUpdateDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_STATICUNI.equals(key)) {
				staticUniDispatch = new StaticUniDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(staticUniDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_TDMLOOPBACK.equals(key)) {
				tdmLoopBackDispatch = new TdmLoopBackDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(tdmLoopBackDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_TIMEPORTCONFIG.equals(key)) {
				timePortConfigDispatch = new TimePortConfigDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(timePortConfigDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_TMCOAMCONFIG.equals(key)) {
				tmcoamConfigDispath = new TMCOAMConfigDispath();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(tmcoamConfigDispath, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_TMPOAMCONFIG.equals(key)) {
				tmpoamConfigDispath = new TMPOAMConfigDispath();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(tmpoamConfigDispath, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_TMSOAMCONFIG.equals(key)) {
				tmsoamConfigDispath = new TMSOAMConfigDispath();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(tmsoamConfigDispath, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_TMSOAMDISPATCH.equals(key)) {
				tmsOamDispatch = new TmsOamDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(tmsOamDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_TUNNEL.equals(key)) {
				tunnelDispatch = new TunnelDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(tunnelDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_WRAPPING.equals(key)) {
				wrappingDispatch = new WrappingDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(wrappingDispatch, 1);
				registry.rebind(key, dispatchInterface);
			} else if (RmiKeys.RMI_INIT.equals(key)) {
				rmiInitImpl = new RmiInitImpl();
				rmiInitI = (RmiInitI) UnicastRemoteObject.exportObject(rmiInitImpl, 1);
				registry.rebind(key, rmiInitI);
//			}else if (RmiKeys.RMI_QINQ.equals(key)) {
//				qinqDispatch = new QinQDispatch();
//				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(qinqDispatch, 1);
//				registry.rebind(key, dispatchInterface);
			}else if (RmiKeys.RMI_PMLIMITE.equals(key)) {
				pmLimiteDispatch = new PmLimiteDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(pmLimiteDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if (RmiKeys.RMI_MSPPROTECT.equals(key)) {
				mspDispatch = new MspDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(mspDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if (RmiKeys.RMI_DUALPROTECT.equals(key)) {
				dualDispatch = new DualDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(dualDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if (RmiKeys.RMI_DUAL.equals(key)) {
				dualWhDispatch = new DualWhDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(dualWhDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if (RmiKeys.RMI_PWBUFFER.equals(key)) {
				pwBufferDispatch = new PwBufferDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(pwBufferDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if (RmiKeys.RMI_BLACKLISTMAC.equals(key)) {
				macDispatch = new MacManagementDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(macDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if (RmiKeys.RMI_CCN.equals(key)) {
				ccnDispatch = new CCNDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(ccnDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if (RmiKeys.RMI_OAMPING.equals(key)) {
				oamPingDispatch = new OamPingDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(oamPingDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}
			else if (RmiKeys.RMI_EHTLOOP.equals(key)) {
				ethLoopDispatch = new EthLoopDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(ethLoopDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}
			else if (RmiKeys.RMI_ACL.equals(key)) {
				aclDispatch = new AclDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(aclDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}
			else if(RmiKeys.RMI_MACLEARNINGLIMIT.equals(key)){
				macLearningLimitDispatch = new MacLearningLimitDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(macLearningLimitDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}
			else if(RmiKeys.RMI_BLACKWHITEMAC.equals(key)){
				blackWhiteMacDispatch = new BlackWhiteMacDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(blackWhiteMacDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if(RmiKeys.RMI_V35PORT.equals(key)){
				v35PortDispatch = new V35PortDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(v35PortDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if(RmiKeys.RMI_LOOPPROROTATE.equals(key)){
				loopProRotateDispatch = new LoopProRotateDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(loopProRotateDispatch, 1);;
				registry.rebind(key, dispatchInterface);
			}else if(RmiKeys.RMI_SMARTFAN.equals(key)){
				smartFanDispatch = new SmartFanDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(smartFanDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if(RmiKeys.RMI_ETHSERVICE.equals(key)){
				ethServiceDispatch = new EthServiceDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(ethServiceDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if(RmiKeys.RMI_TUNNELPROTECT.equals(key)){
				tunnelProtectDispatch = new TunnelProtectDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(tunnelProtectDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if(RmiKeys.RMI_L2CPSERVICE.equals(key)){
				L2cpDispath = new L2cpDispath();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(L2cpDispath, 1);
				registry.rebind(key, dispatchInterface);
			}else if(RmiKeys.RMI_PORTDISCARD.equals(key)){
				portDiscardDispatch = new PortDiscardDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(portDiscardDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if(RmiKeys.RMI_PORT_2LAYER_ATTR.equals(key)){
				port2LayerAttrDispatch = new Port2LayerAttrDispatch();
				dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(port2LayerAttrDispatch, 1);
				registry.rebind(key, dispatchInterface);
			}else if(RmiKeys.RMI_SERVICE.equals(key)){
				serviceDispatch = new ServiceDispatch();
			    dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(serviceDispatch, 1);
			    registry.rebind(key, dispatchInterface);
		   }else if(RmiKeys.RMI_MACSTUDY.equals(key)){
			macStudyDispatch = new MacStudyDispatch();
		    dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(macStudyDispatch, 1);
		    registry.rebind(key, dispatchInterface);	       
		   }else if(RmiKeys.RMI_CCC.equals(key)){
			cccDispatch = new CccDispatch();
			dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(cccDispatch, 1);
			registry.rebind(key, dispatchInterface);
		   }else if(RmiKeys.RMI_BFD.equals(key)){
				bfdDispatch = new BfdDispatch();
			    dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(bfdDispatch, 1);
			    registry.rebind(key, dispatchInterface);
		   }else if(RmiKeys.RMI_ARP.equals(key)){
			   arpDispatch = new ARPDispatch();
			   dispatchInterface = (DispatchInterface) UnicastRemoteObject.exportObject(arpDispatch, 1);
			    registry.rebind(key, dispatchInterface);
		   }

		} catch (Exception e) {
			throw e;
		} finally {
		}

	}

	private static DispatchInterface dispatchInterface = null;
	private static CardDispatchI cardDispatchI = null;
	private static AlarmDispatchI alarmDispatchI = null;
	private static PerformanceDispatchI performanceDispatchI = null;
	private static QosDispatchI qosDispatchI = null;
	private static SegmentDispatchI segmentDispatchI = null;
	private static SiteDispatchI siteDispatchI = null;
	private static ClockDispatchI clockDispatchI = null;
	private static RmiInitI rmiInitI = null;

	private static AcDispatch acDispatch = null;
	private static SiteDispatch siteDispatch = null;
	private static TunnelDispatch tunnelDispatch = null;
	private static AdministrateConfigDispath administrateConfigDispath = null;
	private static AlarmDispatch alarmDispatch = null;
	private static AllConfigDispatch allConfigDispatch = null;
	private static TimeSyncDispatch timesyncDispatch = null;
	private static CardDispatch cardDispatch = null;
	private static CesDispatch cesDispatch = null;
	private static ClockFrequDispatch clockFrequDispatch = null;
	private static ClockSourceDispatch clockSourceDispatch = null;
	private static E1Dispatch e1Dispatch = null;
	private static ElanDispatch elanDispatch = null;
	private static ElineDispatch elineDispatch = null;
	private static ETHLinkOAMConfigDispath ethLinkOAMConfigDispath = null;
	private static EthOAMConfigDispath ethOAMConfigDispath = null;
	private static EtreeDispatch etreeDispatch = null;
	private static ExpMappingPhbDispatch expMappingPhbDispatch = null;
	private static ExternalClockDispatch externalClockDispatch = null;
	private static FrequencySiteDispatch frequencySiteDispatch = null;
	private static GroupSpreadDispatch groupSpreadDispatch = null;
	private static LineClockInterfaceDispatch lineClockInterfaceDispatch = null;
	private static MCNDispatch mcnDispatch = null;
	private static OamDispatch oamDispatch = null;
	private static OSPFAREADispatch oSPFAREADispatch = null;
	private static OSPFINFODispatch ospfinfoDispatch = null;
	private static OSPFInterfaceDispatch ospfInterfaceDispatch = null;
	private static PerformanceDispatch performanceDispatch = null;
	private static PhbMappingExpDispatch phbMappingExpDispatch = null;
	private static PortDispatch portDispatch = null;
	private static PortLagDispatch portLagDispatch = null;
	private static PortStmDispatch portStmDispatch = null;
	private static PortStmTimeslotDispatch portStmTimeslotDispatch = null;
	private static ProtectRorateDispatch protectRorateDispatch = null;
	private static ProtectRotateDispatch protectRotateDispatch = null;
	private static PtpSiteDispatch ptpSiteDispatch = null;
	private static PwDispatch pwDispatch = null;
	private static QosDispatch qosDispatch = null;
	private static QosQueueDispatch qosQueueDispatch = null;
	private static RedistributeDispatch redistributeDispatch = null;
	private static SegmentDispatch segmentDispatch = null;
	private static SlotResetDispatch slotResetDispatch = null;
	private static SoftwareUpdateDispatch softwareUpdateDispatch = null;
	private static StaticUniDispatch staticUniDispatch = null;
	private static TdmLoopBackDispatch tdmLoopBackDispatch = null;
	private static TimePortConfigDispatch timePortConfigDispatch = null;
	private static TMCOAMConfigDispath tmcoamConfigDispath = null;
	private static TMPOAMConfigDispath tmpoamConfigDispath = null;
	private static TMSOAMConfigDispath tmsoamConfigDispath = null;
	private static TmsOamDispatch tmsOamDispatch = null;
	private static WrappingDispatch wrappingDispatch = null;
	private static RmiInitImpl rmiInitImpl = null;
//	private static QinQDispatch qinqDispatch = null;
	private static PmLimiteDispatch pmLimiteDispatch = null;
	private static MspDispatch mspDispatch = null;
	private static DualDispatch dualDispatch = null;
	private static DualWhDispatch dualWhDispatch = null;
	private static PwBufferDispatch pwBufferDispatch = null;
	private static MacManagementDispatch macDispatch = null;
	private static CCNDispatch ccnDispatch=null;
	private static OamPingDispatch oamPingDispatch = null;
	private static EthLoopDispatch ethLoopDispatch =null;
	private static AclDispatch aclDispatch = null;
	private static MacLearningLimitDispatch macLearningLimitDispatch =null;
	private static BlackWhiteMacDispatch blackWhiteMacDispatch =null;
	private static V35PortDispatch v35PortDispatch =null;
	private static LoopProRotateDispatch loopProRotateDispatch = null;
	private static EthServiceDispatch ethServiceDispatch = null;
	private static SmartFanDispatch smartFanDispatch = null;
	private static TunnelProtectDispatch tunnelProtectDispatch = null;
	private static L2cpDispath L2cpDispath = null;
	private static PortDiscardDispatch portDiscardDispatch = null;
	private static Port2LayerAttrDispatch port2LayerAttrDispatch = null;
	private static ServiceDispatch serviceDispatch = null;
	private static MacStudyDispatch macStudyDispatch = null;
	private static CccDispatch cccDispatch = null;
	private static BfdDispatch bfdDispatch = null;	
	private static ARPDispatch arpDispatch = null;
}
