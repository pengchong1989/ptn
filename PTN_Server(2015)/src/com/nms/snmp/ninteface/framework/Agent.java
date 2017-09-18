package com.nms.snmp.ninteface.framework;

import java.io.File;

import org.snmp4j.agent.BaseAgent;
import org.snmp4j.agent.CommandProcessor;
import org.snmp4j.agent.mo.snmp.SnmpCommunityMIB;
import org.snmp4j.agent.mo.snmp.SnmpNotificationMIB;
import org.snmp4j.agent.mo.snmp.SnmpTargetMIB;
import org.snmp4j.agent.mo.snmp.VacmMIB;
import org.snmp4j.security.USM;
/**
 * 
 * Agent为启动类 与上层NMS 之间通信处理
 * @author Administrator
 *
 */
public class Agent extends BaseAgent {

	protected Agent(File bootCounterFile, File configFile, CommandProcessor commandProcessor) {
		super(bootCounterFile, configFile, commandProcessor);
		// TODO Auto-generated constructor stub
	}
//	
//	private static MOFactory moFactory = DefaultMOFactory.getInstance();
//	//mib
//	private DataxHeartBeatMib heartBeatMib;
//	private EMSConfigration emsMib;
//	private DataxAlarmModel alarmMib;
//	private HisAlarm hisAlarmMib;
//	private PerformanceMib performanceMib;
//	private SubnetworkConnection connMib;
//	private PerformanceTrap performanceTrap;
//	private FlowDomainFragment flowDomainFragment;
//	private ManagedElement managedElement;
//	private TerminationPoint portMib;
//	private TopologicalLink topoLink;
//	private Equipment equipment;
//	private EquipmentHolder equipmentHolder;
//	private ProtectionGroup protectionGroup;
//	private TrailNtwProtection trailNtwProtection;
//	private EquiporHolder equiporHolder;
//	private TPData tpDataMib;
//	private LayeredParameters LayeredParameterMib;
//	private FlowDomain flowDomain;
//	
//	protected Agent(File bootCounterFile, File configFile,
//			CommandProcessor commandProcessor) {
//		super(bootCounterFile, configFile, commandProcessor);
//		//创建获取请求的4个线程
//		agent.setWorkerPool(ThreadPool.create("RequestPool", 4));
//	    // Uncomment the following if you want to use AES 192 or 256 with 3DES like key extension.
//	    SecurityProtocols.getInstance().addPrivacyProtocol(new PrivAES256With3DESKeyExtension());
//	    
////	    ServiceFactory serviceFactory = new ServiceFactory();
////		Properties properties = new Properties();
////		properties.put(ServiceFactory.HOSTNAME, "192.168.200.212");
////		properties.put(ServiceFactory.PTNUSER, "admin");
////		try {
////			serviceFactory.startup(properties);
////		} catch (Exception e) {
////			ExceptionManage.dispose(e,this.getClass());
////		}
////		ConstantUtil.serviceFactory = serviceFactory;
//	    //开启ftp传输线程,自动上传snmp文件
//		//开启性能采集任务
//	    performanceTrap = new PerformanceTrap();
//	}
//
//	static {
//	    LogFactory.setLogFactory(new Log4jLogFactory());
//	}
//	public void init(){
//	    try {
//			super.init();
//			loadConfig(ImportModes.REPLACE_CREATE);
//			addShutdownHook();
//			getServer().addContext(new OctetString("public"));
//			finishInit();
//			performanceTrap.collectPM();
//			new Thread(new FtpTransThread()).start();
//		} catch (IOException e) {
//			ExceptionManage.dispose(e,this.getClass());
//	        }
//	}
//	
//	public void start(){
//		run();
//		sendColdStartNotification();
//    }
//	
//	public DataxHeartBeatMib getDataxHeartBeatMib(){
//		return heartBeatMib;
//	}
//	
//	public DataxAlarmModel getAlarmMib() {
//		return alarmMib;
//	}
//
//	protected  void registerSnmpMIBs() {
//		heartBeatMib = new DataxHeartBeatMib(moFactory);
//		emsMib = new EMSConfigration(moFactory);
//		performanceMib = new PerformanceMib(moFactory);
//		alarmMib = new DataxAlarmModel(moFactory);
//		hisAlarmMib = new HisAlarm(moFactory);
//		connMib = new SubnetworkConnection(moFactory);
//		flowDomainFragment  = new FlowDomainFragment(moFactory);
//		managedElement = new ManagedElement(moFactory);
//		portMib = new TerminationPoint(moFactory);
//		topoLink = new TopologicalLink(moFactory);
//		tpDataMib = new TPData(moFactory);
//		LayeredParameterMib = new LayeredParameters(moFactory);
//		equipment = new Equipment(moFactory); 
//		equipmentHolder = new EquipmentHolder(moFactory);
//		protectionGroup = new ProtectionGroup(moFactory);
//		trailNtwProtection = new TrailNtwProtection(moFactory);
//		equiporHolder = new EquiporHolder(moFactory);
//		flowDomain = new FlowDomain(moFactory);
//	    super.registerSnmpMIBs();
//	}
//	@Override
//	protected void registerManagedObjects() {
//		try {
//			heartBeatMib.registerMOs(server, null);
//			emsMib.registerMOs(server, null);
//			performanceMib.registerMOs(server, null);
//			alarmMib.registerMOs(server, null);
//			hisAlarmMib.registerMOs(server, null);
//			connMib.registerMOs(server, null);
//			flowDomainFragment.registerMOs(server, null);
//			managedElement.registerMOs(server, null);
//			portMib.registerMOs(server, null);
//			topoLink.registerMOs(server, null);
//			tpDataMib.registerMOs(server, null);
//			LayeredParameterMib.registerMOs(server, null);
//			equipment.registerMOs(server, null);
//			equipmentHolder.registerMOs(server, null);
//			protectionGroup.registerMOs(server, null);
//			trailNtwProtection.registerMOs(server, null);
//			equiporHolder.registerMOs(server, null);
//			flowDomain.registerMOs(server, null);
//		} catch (DuplicateRegistrationException e) {
//			ExceptionManage.dispose(e,this.getClass());
//		}
//	}
//
//	@Override
//	protected void unregisterManagedObjects() {
//		heartBeatMib.unregisterMOs(server, null);
//		emsMib.unregisterMOs(server, null);
//		alarmMib.unregisterMOs(server, null);
//		hisAlarmMib.unregisterMOs(server, null);
//		performanceMib.unregisterMOs(server, null);
//		connMib.unregisterMOs(server, null);
//		flowDomainFragment.unregisterMOs(server, null);
//		equipment.unregisterMOs(server, null);
//		equipmentHolder.unregisterMOs(server, null);
//		protectionGroup.unregisterMOs(server, null);
//		trailNtwProtection.unregisterMOs(server, null);
//		equiporHolder.unregisterMOs(server, null);
//	}
//
//	@Override
//	protected void addUsmUser(USM usm) {
//	    UsmUser user = new UsmUser(new OctetString("SHADES"),
//                  AuthSHA.ID,
//                  new OctetString("SHADESAuthPassword"),
//                  PrivDES.ID,
//                  new OctetString("SHADESPrivPassword"));
//		//usm.addUser(user.getSecurityName(), usm.getLocalEngineID(), user);
//		usm.addUser(user.getSecurityName(), null, user);
//		user = new UsmUser(new OctetString("TEST"),
//		                  AuthSHA.ID,
//		                  new OctetString("maplesyrup"),
//		                  PrivDES.ID,
//		                  new OctetString("maplesyrup"));
//		usm.addUser(user.getSecurityName(), usm.getLocalEngineID(), user);
//		user = new UsmUser(new OctetString("SHA"),
//		                  AuthSHA.ID,
//		                  new OctetString("SHAAuthPassword"),
//		                  null,
//		                  null);
//		usm.addUser(user.getSecurityName(), usm.getLocalEngineID(), user);
//		user = new UsmUser(new OctetString("SHADES"),
//		                  AuthSHA.ID,
//		                  new OctetString("SHADESAuthPassword"),
//		                  PrivDES.ID,
//		                  new OctetString("SHADESPrivPassword"));
//		usm.addUser(user.getSecurityName(), usm.getLocalEngineID(), user);
//		user = new UsmUser(new OctetString("MD5DES"),
//		                  AuthMD5.ID,
//		                  new OctetString("MD5DESAuthPassword"),
//		                  PrivDES.ID,
//		                  new OctetString("MD5DESPrivPassword"));
//		usm.addUser(user.getSecurityName(), usm.getLocalEngineID(), user);
//		user = new UsmUser(new OctetString("SHAAES128"),
//		                  AuthSHA.ID,
//		                  new OctetString("SHAAES128AuthPassword"),
//		                  PrivAES128.ID,
//		                  new OctetString("SHAAES128PrivPassword"));
//		usm.addUser(user.getSecurityName(), usm.getLocalEngineID(), user);
//		user = new UsmUser(new OctetString("SHAAES192"),
//		                  AuthSHA.ID,
//		                  new OctetString("SHAAES192AuthPassword"),
//		                  PrivAES192.ID,
//		                  new OctetString("SHAAES192PrivPassword"));
//		usm.addUser(user.getSecurityName(), usm.getLocalEngineID(), user);
//		user = new UsmUser(new OctetString("SHAAES256"),
//		                  AuthSHA.ID,
//		                  new OctetString("SHAAES256AuthPassword"),
//		                  PrivAES256.ID,
//		                  new OctetString("SHAAES256PrivPassword"));
//		usm.addUser(user.getSecurityName(), usm.getLocalEngineID(), user);
//		user = new UsmUser(new OctetString("SHAAES256p"),
//		AuthSHA.ID,
//		new OctetString("SHAAES256AuthPassword"),
//		PrivAES256With3DESKeyExtension.ID,
//		new OctetString("SHAAES256PrivPassword"));
//		usm.addUser(user.getSecurityName(), usm.getLocalEngineID(), user);
//		
//		user = new UsmUser(new OctetString("MD5AES128"),
//		                  AuthMD5.ID,
//		                  new OctetString("MD5AES128AuthPassword"),
//		                  PrivAES128.ID,
//		                  new OctetString("MD5AES128PrivPassword"));
//		usm.addUser(user.getSecurityName(), usm.getLocalEngineID(), user);
//		user = new UsmUser(new OctetString("MD5AES192"),
//		                  AuthMD5.ID,
//		                  new OctetString("MD5AES192AuthPassword"),
//		                  PrivAES192.ID,
//		                  new OctetString("MD5AES192PrivPassword"));
//		usm.addUser(user.getSecurityName(), usm.getLocalEngineID(), user);
//		user = new UsmUser(new OctetString("MD5AES256"),
//		                  AuthMD5.ID,
//		                  new OctetString("MD5AES256AuthPassword"),
//		                  PrivAES256.ID,
//		                  new OctetString("MD5AES256PrivPassword"));
//		usm.addUser(user.getSecurityName(), usm.getLocalEngineID(), user);
//		user = new UsmUser(new OctetString("MD5AES256"),
//		                  AuthMD5.ID,
//		                  new OctetString("MD5AES256AuthPassword"),
//		                  PrivAES256.ID,
//		                  new OctetString("MD5AES256PrivPassword"));
//		usm.addUser(user.getSecurityName(), usm.getLocalEngineID(), user);
//		user = new UsmUser(new OctetString("v3notify"),
//		                  null,
//		                  null,
//		                  null,
//		                  null);
//		usm.addUser(user.getSecurityName(), null, user);
//	}
//
//	@Override
//	protected void addNotificationTargets(SnmpTargetMIB targetMIB,
//			SnmpNotificationMIB notificationMIB) {
//	    targetMIB.addDefaultTDomains();
//	    targetMIB.addTargetAddress(new OctetString("notificationV2c"),
//	                               TransportDomains.transportDomainUdpIpv4,
//	                               new OctetString(new UdpAddress("127.0.0.1/162").getValue()),
//	                               200, 1,
//	                               new OctetString("notify"),
//	                               new OctetString("v2c"),
//	                               StorageType.permanent);
//	    targetMIB.addTargetAddress(new OctetString("notificationV3"),
//	                               TransportDomains.transportDomainUdpIpv4,
//	                               new OctetString(new UdpAddress("127.0.0.1/162").getValue()),
//	                               200, 1,
//	                               new OctetString("notify"),
//	                               new OctetString("v3notify"),
//	                               StorageType.permanent);
//	    targetMIB.addTargetParams(new OctetString("v2c"),
//	                              MessageProcessingModel.MPv2c,
//	                              SecurityModel.SECURITY_MODEL_SNMPv2c,
//	                              new OctetString("cpublic"),
//	                              SecurityLevel.NOAUTH_NOPRIV,
//	                              StorageType.permanent);
//	    targetMIB.addTargetParams(new OctetString("v3notify"),
//	                              MessageProcessingModel.MPv3,
//	                              SecurityModel.SECURITY_MODEL_USM,
//	                              new OctetString("cpublic"),
//	                              SecurityLevel.NOAUTH_NOPRIV,
//	                              StorageType.permanent);
//	    notificationMIB.addNotifyEntry(new OctetString("default"),
//	                                   new OctetString("notify"),
//	                                   SnmpNotificationMIB.SnmpNotifyTypeEnum.inform,
//	                                   StorageType.permanent);
//
//	}
//
//	@Override
//	protected void addViews(VacmMIB vacm) {
//		vacm.addGroup(SecurityModel.SECURITY_MODEL_SNMPv1,
//	                  new OctetString("cpublic"),
//	                  new OctetString("v1v2group"),
//	                  StorageType.nonVolatile);
//	    vacm.addGroup(SecurityModel.SECURITY_MODEL_SNMPv2c,
//	                  new OctetString("cpublic"),
//	                  new OctetString("v1v2group"),
//	                  StorageType.nonVolatile);
//	    vacm.addGroup(SecurityModel.SECURITY_MODEL_USM,
//	                  new OctetString("SHADES"),
//	                  new OctetString("v3group"),
//	                  StorageType.nonVolatile);
//	    vacm.addGroup(SecurityModel.SECURITY_MODEL_USM,
//	                  new OctetString("MD5DES"),
//	                  new OctetString("v3group"),
//	                  StorageType.nonVolatile);
//	    vacm.addGroup(SecurityModel.SECURITY_MODEL_USM,
//	                  new OctetString("TEST"),
//	                  new OctetString("v3test"),
//	                  StorageType.nonVolatile);
//	    vacm.addGroup(SecurityModel.SECURITY_MODEL_USM,
//	                  new OctetString("SHA"),
//	                  new OctetString("v3restricted"),
//	                  StorageType.nonVolatile);
//	    vacm.addGroup(SecurityModel.SECURITY_MODEL_USM,
//	                  new OctetString("SHAAES128"),
//	                  new OctetString("v3group"),
//	                  StorageType.nonVolatile);
//	    vacm.addGroup(SecurityModel.SECURITY_MODEL_USM,
//	                  new OctetString("SHAAES192"),
//	                  new OctetString("v3group"),
//	                  StorageType.nonVolatile);
//	    vacm.addGroup(SecurityModel.SECURITY_MODEL_USM,
//	                  new OctetString("SHAAES256"),
//	                  new OctetString("v3group"),
//	                  StorageType.nonVolatile);
//	    vacm.addGroup(SecurityModel.SECURITY_MODEL_USM,
//	        new OctetString("SHAAES256p"),
//	        new OctetString("v3group"),
//	        StorageType.nonVolatile);
//	    vacm.addGroup(SecurityModel.SECURITY_MODEL_USM,
//	                  new OctetString("MD5AES128"),
//	                  new OctetString("v3group"),
//	                  StorageType.nonVolatile);
//	    vacm.addGroup(SecurityModel.SECURITY_MODEL_USM,
//	                  new OctetString("MD5AES192"),
//	                  new OctetString("v3group"),
//	                  StorageType.nonVolatile);
//	    vacm.addGroup(SecurityModel.SECURITY_MODEL_USM,
//	                  new OctetString("MD5AES256"),
//	                  new OctetString("v3group"),
//	                  StorageType.nonVolatile);
//	    vacm.addGroup(SecurityModel.SECURITY_MODEL_USM,
//	                  new OctetString("v3notify"),
//	                  new OctetString("v3group"),
//	                  StorageType.nonVolatile);
//
//	    vacm.addAccess(new OctetString("v1v2group"), new OctetString(),
//	                   SecurityModel.SECURITY_MODEL_ANY,
//	                   SecurityLevel.NOAUTH_NOPRIV,
//	                   MutableVACM.VACM_MATCH_EXACT,
//	                   //MutableVACM.VACM_MATCH_EXACT,
//	                   new OctetString("fullReadView"),
//	                   new OctetString("fullWriteView"),
//	                   new OctetString("fullNotifyView"),
//	                   StorageType.nonVolatile);
//	    vacm.addAccess(new OctetString("v3group"), new OctetString(),
//	                   SecurityModel.SECURITY_MODEL_USM,
//	                   SecurityLevel.AUTH_PRIV,
//	                   MutableVACM.VACM_MATCH_EXACT,
//	                   new OctetString("fullReadView"),
//	                   new OctetString("fullWriteView"),
//	                   new OctetString("fullNotifyView"),
//	                   StorageType.nonVolatile);
//	    vacm.addAccess(new OctetString("v3restricted"), new OctetString(),
//	                   SecurityModel.SECURITY_MODEL_USM,
//	                   SecurityLevel.NOAUTH_NOPRIV,
//	                   MutableVACM.VACM_MATCH_EXACT,
//	                   new OctetString("restrictedReadView"),
//	                   new OctetString("restrictedWriteView"),
//	                   new OctetString("restrictedNotifyView"),
//	                   StorageType.nonVolatile);
//	    /*
//	    vacm.addAccess(new OctetString("v3test"), new OctetString(),
//	                   SecurityModel.SECURITY_MODEL_USM,
//	                   SecurityLevel.AUTH_PRIV,
//	                   MutableVACM.VACM_MATCH_EXACT,
//	                   new OctetString("testReadView"),
//	                   new OctetString("testWriteView"),
//	                   new OctetString("testNotifyView"),
//	                   StorageType.nonVolatile);
//		*/
//	    vacm.addViewTreeFamily(new OctetString("fullReadView"), new OID("1.3"),
//	                           new OctetString(), VacmMIB.vacmViewIncluded,
//	                           StorageType.nonVolatile);
//	    vacm.addViewTreeFamily(new OctetString("fullWriteView"), new OID("1.3"),
//				                new OctetString(), VacmMIB.vacmViewIncluded,
//				                StorageType.nonVolatile);
//
//	    vacm.addViewTreeFamily(new OctetString("fullNotifyView"), new OID("1.3"),
//	                           new OctetString(), VacmMIB.vacmViewIncluded,
//	                           StorageType.nonVolatile);
//	    //vacm.addViewTreeFamily(new OctetString("fullReadView"), new OID("1.3.6.1.2.1.1.1.0"),
//	    //    new OctetString(), VacmMIB.vacmViewExcluded,
//	    //    StorageType.nonVolatile);
//	    //vacm.addViewTreeFamily(new OctetString("fullReadView"), new OID("1.3.6.1.2.1.1.2.0"),
//	    //    new OctetString(), VacmMIB.vacmViewIncluded,
//	    //    StorageType.nonVolatile);
//	    
//
//
//	    vacm.addViewTreeFamily(new OctetString("restrictedReadView"),
//	                           new OID("1.3.6.1.2"),
//	                           new OctetString(), VacmMIB.vacmViewIncluded,
//	                           StorageType.nonVolatile);
//	    vacm.addViewTreeFamily(new OctetString("restrictedWriteView"),
//	                           new OID("1.3.6.1.2.1"),
//	                           new OctetString(),
//	                           VacmMIB.vacmViewIncluded,
//	                           StorageType.nonVolatile);
//	    vacm.addViewTreeFamily(new OctetString("restrictedNotifyView"),
//	                           new OID("1.3.6.1.2"),
//	                           new OctetString(), VacmMIB.vacmViewIncluded,
//	                           StorageType.nonVolatile);
//	    vacm.addViewTreeFamily(new OctetString("restrictedNotifyView"),
//	                           new OID("1.3.6.1.6.3.1"),
//	                           new OctetString(), VacmMIB.vacmViewIncluded,
//	                           StorageType.nonVolatile);
//	    /*
//	    vacm.addViewTreeFamily(new OctetString("testReadView"),
//	                           new OID("1.3.6.1.2"),
//	                           new OctetString(), VacmMIB.vacmViewIncluded,
//	                           StorageType.nonVolatile);
//	    
//	    vacm.addViewTreeFamily(new OctetString("testReadView"),
//	                           new OID("1.3.6.1.2.1.1"),
//	                           new OctetString(), VacmMIB.vacmViewExcluded,
//	                           StorageType.nonVolatile);
//	    
//	    vacm.addViewTreeFamily(new OctetString("testWriteView"),
//	                           new OID("1.3.6.1.2.1"),
//	                           new OctetString(),
//	                           VacmMIB.vacmViewIncluded,
//	                           StorageType.nonVolatile);
//	    
//	    vacm.addViewTreeFamily(new OctetString("testNotifyView"),
//	                           new OID("1.3.6.1.2"),
//	                           new OctetString(), VacmMIB.vacmViewIncluded,
//	                           StorageType.nonVolatile);
//		*/
//	}
//
//	@Override
//	protected void addCommunities(SnmpCommunityMIB communityMIB) {
//	    Variable[] com2sec = new Variable[] {
//	            new OctetString("public"),              // community name
//	            new OctetString("cpublic"),              // security name
//	            getAgent().getContextEngineID(),        // local engine ID
//	            new OctetString(),              // default context name
//	            new OctetString(),                      // transport tag
//	            new Integer32(StorageType.nonVolatile), // storage type
//	            new Integer32(RowStatus.active)         // row status
//	        };
//	        SnmpCommunityMIB.SnmpCommunityEntryRow row =
//	            communityMIB.getSnmpCommunityEntry().createRow(
//	              new OctetString("public2public").toSubIndex(true), com2sec);
//	        communityMIB.getSnmpCommunityEntry().addRow(row);
//	}
//

	@Override
	protected void addCommunities(SnmpCommunityMIB arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addNotificationTargets(SnmpTargetMIB arg0, SnmpNotificationMIB arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addUsmUser(USM arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void addViews(VacmMIB arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void registerManagedObjects() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void unregisterManagedObjects() {
		// TODO Auto-generated method stub
		
	}
}
