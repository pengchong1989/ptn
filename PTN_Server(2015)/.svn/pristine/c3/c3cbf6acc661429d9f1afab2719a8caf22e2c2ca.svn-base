package com.nms.corba.ninterface.client.framework;

import managedElement.ManagedElementIterator_IHolder;
import managedElement.ManagedElementList_THolder;
import managedElementManager.ManagedElementMgr_I;
import managedElementManager.ManagedElementMgr_IHelper;
import multiLayerSubnetwork.SubnetworkIterator_IHolder;
import multiLayerSubnetwork.SubnetworkList_THolder;
import nmsSession.NmsSession_I;
import nmsSession.NmsSession_IPOA;
import nmsSession.NmsSession_IPOATie;

import org.omg.CORBA.StringHolder;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import protection.ProtectionCommand_T;
import protection.ProtectionMgr_I;
import protection.ProtectionMgr_IHelper;
import protection.ReversionMode_T;
import protection.SwitchData_T;
import protection.SwitchData_THolder;
import securityManager.SecurityMgr_I;
import securityManager.SecurityMgr_IHelper;
import session.Session_I;
import subnetworkConnection.CrossConnect_T;
import timeMgr.TimeMgr_I;
import timeMgr.TimeMgr_IHelper;
import trailNtwProtection.TNPSwitchData_THolder;
import trailNtwProtection.TrailNtwProtMgr_I;
import trailNtwProtection.TrailNtwProtMgr_IHelper;
import trailNtwProtection.TrailNtwProtModifyData_T;
import trailNtwProtection.TrailNtwProtection_THolder;

import com.nms.corba.ninterface.enums.EProtectionType;
import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.corba.test.common.CorbaConnect;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import emsMgr.EMSMgr_IHelper;
import emsSession.EmsSession_I;
import emsSession.EmsSession_IHolder;
import emsSession.EmsSession_IPackage.managerNames_THolder;
import emsSessionFactory.EmsSessionFactory_I;
import emsSessionFactory.EmsSessionFactory_IHelper;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

public class CorbaTest {
	static EmsSession_I emsSession = null;
	static org.omg.CORBA.ORB orb = null;
	static NamingContext nameContext = null;
	static POA rootPOA = null;
	
	//网元管理者
	ManagedElementMgr_I meMgr = null;
    //ems管理者
	EMSMgr_I emsMgr = null;
	
	public static void main(String args[]) {
		try {
			CorbaConnect cc = new CorbaConnect();
			cc.isConnect();
			emsSession = cc.emsSession;
			//下面为测试代码,获取网元
//			CorbaTest.getAllManagedElements();
//			CorbaTest.getSNCMessage();//测试子网yes
//			CorbaTest.changeSNCMessage();//修改子网yes
//			CorbaTest.setUserLabel();//msp，tunnel，loop修改yes
//			CorbaTest.protectionCommand();//人工保护倒换msp,tunnel,yes
//			CorbaTest.setandgetTimeTest();//get set  yes；
//			CorbaTest.modifyPassword();//设置用户口令 yes
			CorbaTest.deleteTNP();//删除tnp yes
//			CorbaTest.modifyTrailNtwProtection();//测试修改tnp保护组 yes
			
//			CorbaTest.modifyPassword();//tnp保护倒换测试 yes
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,CorbaTest.class);
		}
	}
	
	/**
	 * 测试子网信息查询
	 */
	private static void getSNCMessage() {
		System.out.println(111);
		EMSMgr_I emsMgr = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("EMS", mgrHolder);
			emsMgr = EMSMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + emsMgr.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		//获取管理者
	
		SubnetworkList_THolder sList = new SubnetworkList_THolder();
		SubnetworkIterator_IHolder sIt = new SubnetworkIterator_IHolder();
	
		
		try {
			emsMgr.getAllTopLevelSubnetworks(1, sList, sIt);
			System.out.println("检测完毕！！");
			if (sList.value != null && sList.value.length >0 ) {
				for (int i = 0; i < sList.value.length; i++) {
					System.out.println(sList.value[i].name[1].value+"**");
					System.out.println(sList.value[0].owner+"*///");
				}
			}
			if (sIt.value != null) {
				while (sIt.value.next_n(1, sList)) {
					for (int i = 0; i < sList.value.length; i++) {
						System.out.println(sList.value[i].name[1].value);
					}
				}
				for (int i = 0; i < sList.value.length; i++) {
					System.out.println(sList.value[i].name[1].value+"***");
				}
				sIt.value.destroy();
			}
			
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,CorbaTest.class);
		}
	}

	/**
	 * 测试子网修改
	 */
	private static void changeSNCMessage() {
		System.out.println(1112);
		EMSMgr_I emsMgr = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("EMS", mgrHolder);
			emsMgr = EMSMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + emsMgr.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		NameAndStringValue_T[] objectName = new NameAndStringValue_T[2];
		objectName[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		objectName[1] = new NameAndStringValue_T("MultiLayerSubnetwork","subnetworkconnection");
		NVSList_THolder NVSList_THolder = new NVSList_THolder();
		NVSList_THolder.value = new NameAndStringValue_T[1];
		NVSList_THolder.value[0]= new NameAndStringValue_T("macLocation","555");
		try {
//			emsMgr.setUserLabel(objectName, "hhii", false);
			emsMgr.setAdditionalInfo(objectName, NVSList_THolder);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,CorbaTest.class);
		}
		
	}
	
	
	/**
	 * 修改网络保护组测试单网元侧
	 */
	
	private static void setUserLabel() {
		System.out.println(222);
		ProtectionMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("Protection", mgrHolder);
			mgr_I = ProtectionMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
//		if (null == objectName|| objectName.length < 3 || null == objectName[1].name ||null == objectName[2].name
//				|| !objectName[1].name.equals("ManagedElement")||!objectName[2].name.equals("PGP")
//				||null == userLabel||"".equals(userLabel)) {
//			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"参数错误");
//		}
		NameAndStringValue_T[] objectName = new NameAndStringValue_T[3];
		
		objectName[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		objectName[1] = new NameAndStringValue_T("ManagedElement","");
//		objectName[2] = new NameAndStringValue_T("PGP",EProtectionType.MSPPROTECT.getValue()+"/1");
//		System.out.println("msp测试修改");
		objectName[2] = new NameAndStringValue_T("PGP",EProtectionType.TUNNEL.getValue()+"/6");
		System.out.println("tunnel测试修改");
//		objectName[2] = new NameAndStringValue_T("PGP",EProtectionType.LOOPPROTECT.getValue()+"/1");
//		System.out.println("loop测试修改");
		try {
			mgr_I.setUserLabel(objectName, "dsd", false);
		} catch (ProcessingFailureException e) {
//			 TODO Auto-generated catch block
			ExceptionManage.dispose(e,CorbaTest.class);
		}
		
	}
	
	/**
	 * 人工保护倒换测试
	 */
	private static void protectionCommand(){
		ProtectionMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("Protection", mgrHolder);
			mgr_I = ProtectionMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] reliableSinkCtpOrGroupName = new NameAndStringValue_T[3];
		
		reliableSinkCtpOrGroupName[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		reliableSinkCtpOrGroupName[1] = new NameAndStringValue_T("ManagedElement","");
		reliableSinkCtpOrGroupName[2] = new NameAndStringValue_T("PGP",EProtectionType.MSPPROTECT.getValue()+"/1");
//		reliableSinkCtpOrGroupName[2] = new NameAndStringValue_T("PGP",EProtectionType.TUNNEL.getValue()+"/8");
		NameAndStringValue_T[] toTp = new NameAndStringValue_T[3];
		toTp[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		toTp[1] = new NameAndStringValue_T("ManagedElement", "");
//		toTp[2] = new NameAndStringValue_T("PTP", "37");//msp
//		toTp[2] = new NameAndStringValue_T("PTP", "14");//tunnel
		
		
		NameAndStringValue_T[] fromTp = null;
		SwitchData_THolder switchData = new SwitchData_THolder();
		switchData.value = new SwitchData_T();
		try {
			mgr_I.performProtectionCommand(ProtectionCommand_T.from_int(1),reliableSinkCtpOrGroupName, toTp, toTp,switchData);
		} catch (ProcessingFailureException e) {
//			 TODO Auto-generated catch block
			ExceptionManage.dispose(e,CorbaTest.class);
		}
		
	}
	
	/**
	 * 查询和修改时间测试
	 */
	private static void setandgetTimeTest(){
		TimeMgr_I mgr_I = null;
		try {System.out.println(31);
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("Time", mgrHolder);
			mgr_I = TimeMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		NameAndStringValue_T[] emsName = new NameAndStringValue_T[1];
		
		emsName[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());

		StringHolder emsTime = new StringHolder();
		
		try {
//			mgr_I.getEMSTime(emsName,emsTime);
//			System.out.println(emsTime.value);
			
			boolean a  = mgr_I.setEMSTime(emsName,"2002-7-8 12:1:1");
			System.out.println(1);
		} catch (ProcessingFailureException e) {
//			 TODO Auto-generated catch block
			ExceptionManage.dispose(e,CorbaTest.class);
		}
		
	}
	
	/**
	 * 修改用户口令测试
	 */
	private static void modifyPassword(){
		SecurityMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("Security", mgrHolder);
			mgr_I = SecurityMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
	
		try {
			mgr_I.modifyPassword("admin", "1","2", "2");
		} catch (ProcessingFailureException e) {
	//		 TODO Auto-generated catch block
			ExceptionManage.dispose(e,CorbaTest.class);
		}
	
	}
	
	/**
	 * TNP删除测试
	 * @throws ProcessingFailureException 
	 */
	private static void deleteTNP() throws ProcessingFailureException{
		TrailNtwProtMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("TrailNtwProtection", mgrHolder);
			mgr_I = TrailNtwProtMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] tnpName = new NameAndStringValue_T[3];
//		tnpName[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
//		tnpName[1] = new NameAndStringValue_T("ManagedElement", "");
//		tnpName[2] = new NameAndStringValue_T("PGP",EProtectionType.TUNNEL.toString() + "/18");

		CorbaConvertBase convertBase = new CorbaConvertBase();
		tnpName = convertBase.convertName(ELayerRate.TNP.getValue(), 24, 0);
		StringHolder errorReason = new StringHolder();
				
		try {
			mgr_I.deleteTrailNtwProtection(tnpName,errorReason);
			System.out.println(errorReason.value);
		} catch (ProcessingFailureException e) {
			System.out.println(e.errorReason);
		}
	}
	
	
	/**
	 * 测试tnp修改
	 * @throws ProcessingFailureException 
	 */
	private static void modifyTrailNtwProtection() throws ProcessingFailureException{
		int ad;
		int add;
		TrailNtwProtMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("TrailNtwProtection", mgrHolder);
			mgr_I = TrailNtwProtMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] tnpName = new NameAndStringValue_T[3];
		CorbaConvertBase convertBase = new CorbaConvertBase();
		tnpName = convertBase.convertName(ELayerRate.TNP.getValue(), 25, 0);

		TrailNtwProtModifyData_T tnpModifyData = new TrailNtwProtModifyData_T();
		tnpModifyData.nativeEMSName = "kk_test123";
		tnpModifyData.reversionMode = ReversionMode_T.RM_UNKNOWN;
		tnpModifyData.tnpParameters = new NameAndStringValue_T[0];
//		tnpModifyData.tnpParameters[0] = new NameAndStringValue_T("roateModel","2");
//		tnpModifyData.tnpParameters[1] = new NameAndStringValue_T("direction",ESwitchDirection.WORK.toString());
		tnpModifyData.pgATPList = new NameAndStringValue_T[0][0];
		tnpModifyData.pgZTPList = new NameAndStringValue_T[0][0];
		tnpModifyData.additionalInfo = new NameAndStringValue_T[0];
		tnpModifyData.route = new CrossConnect_T[0] ;
		
		TrailNtwProtection_THolder modifiedTNP = new TrailNtwProtection_THolder();
		StringHolder errorReason = new StringHolder();
		
		try {
			mgr_I.modifyTrailNtwProtection(tnpName, tnpModifyData, modifiedTNP, errorReason);
			System.out.println(errorReason.value);
		} catch (ProcessingFailureException e) {
			System.out.println(e.errorReason);
		}catch (Exception e) {
			ExceptionManage.dispose(e,CorbaTest.class);
		}finally{
			ad = 1;
			add = 1;
		}
		
	}
	
	/**
	 * tnp倒换
	 * @throws ProcessingFailureException 
	 */
	private static void performTNPCommand() throws ProcessingFailureException{
		TrailNtwProtMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("TrailNtwProtection", mgrHolder);
			mgr_I = TrailNtwProtMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
//		public void performTNPCommand(ProtectionCommand_T protectionCommand,
//				NameAndStringValue_T[] tnpName, String switchDirection,
//				NameAndStringValue_T[] protectionGroupAName,
//				NameAndStringValue_T[] protectionGroupZName,
//				NameAndStringValue_T[][][] workerTrailList,
//				NameAndStringValue_T[][] protectionTrail,
//				TNPSwitchData_THolder tnpSwitchData)
		
		NameAndStringValue_T[] tnpName = new NameAndStringValue_T[3];
		CorbaConvertBase convertBase = new CorbaConvertBase();
		tnpName = convertBase.convertName(ELayerRate.TNP.getValue(), 20, 0);
		
		String switchDirection = "倒换到工作侧";
		
		ProtectionCommand_T protectionCommand = ProtectionCommand_T.PC_MANUAL_SWITCH;
		
		NameAndStringValue_T[] protectionGroupAName = new NameAndStringValue_T[0];
		NameAndStringValue_T[] protectionGroupZName = new NameAndStringValue_T[0];
		NameAndStringValue_T[][][] workerTrailList = new NameAndStringValue_T[0][0][0];
		NameAndStringValue_T[][] protectionTrail = new NameAndStringValue_T[0][0];
		
		TNPSwitchData_THolder tnpSwitchData = new TNPSwitchData_THolder();
		
		try {
			mgr_I.performTNPCommand(protectionCommand,tnpName, switchDirection,protectionGroupAName,protectionGroupZName,workerTrailList,protectionTrail,tnpSwitchData);
		} catch (ProcessingFailureException e) {
//			 TODO Auto-generated catch block
			ExceptionManage.dispose(e,CorbaTest.class);
		}
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void getAllManagedElements() {
		ManagedElementList_THolder meList = new ManagedElementList_THolder();
		ManagedElementIterator_IHolder meIter = new ManagedElementIterator_IHolder();
		try {
			
			//获取管理者
			Common_IHolder mgrHolder = new Common_IHolder();
			//不同接口管理者名称不同
			emsSession.getManager("ManagedElement", mgrHolder);
			ManagedElementMgr_I meMgr = ManagedElementMgr_IHelper.narrow(mgrHolder.value);
			
			//调用测试接口
			meMgr.getAllManagedElements(1, meList, meIter);
			if (meList.value != null && meList.value.length > 0) {
				for (int i = 0; i < meList.value.length; i++) {
					System.out.println(meList.value[i].name[1].value);
				}
			}
			if (meIter.value != null) {
				while (meIter.value.next_n(1, meList)) {
					for (int i = 0; i < meList.value.length; i++) {
						System.out.println(meList.value[i].name[1].value);
					}
				}
				for (int i = 0; i < meList.value.length; i++) {
					System.out.println(meList.value[i].name[1].value);
				}
				meIter.value.destroy();
			}
			
			
//			在这里测试 CORBA 接口
		} catch (ProcessingFailureException ex) {
			ex.printStackTrace();
			meList.value = null;
		}

		System.out.println(meList.value);
	}
	
	
	private boolean init() {
		// 建立连接登录、建立消息通道
		if (!initOrb()) {
			return false;
		}

		if (!initNameService()) {
			return false;
		}
		// 开始准备登陆并且获取EmsSession!
		EmsSessionFactory_I emsFactory = getEmsSessionFactory();
		if (emsFactory == null) {
			return false;
		}

		if (!initAndActiveRootPOA()) {
			return false;
		}

		NmsSession_I nmsSession = getNmsSession_I();
		if(nmsSession == null){
			return false;
		}
		System.out.println("NMSsession ---" + nmsSession.toString());
		
		EmsSession_IHolder sessionHolder = new EmsSession_IHolder();
		try {
			System.out.println("获取EmsSession引用对象");
			emsFactory.getEmsSession("admin", "admin", nmsSession, sessionHolder);
			emsSession = sessionHolder.value;
			System.out.println("EMSsession ---" + emsSession.toString());

		} catch (ProcessingFailureException ex) {
			System.out
					.println("获取EmsSession引用对象，异常！---ProcessingFailureException---");
			System.out.println("可能是用户名或者密码错误，或者权限不够，或者已登陆的用户还未退出！");
			System.out.println(ex.toString());
		}

		// 获得所支持的管理器
		try {
			managerNames_THolder mgrHolder = new managerNames_THolder();
			emsSession.getSupportedManagers(mgrHolder);
			String[] manages = mgrHolder.value;
			for (int i = 0; i < manages.length; i++) {
				System.out.println("管理器--Manager " + i + "  " + manages[i]);
			}
		} catch (ProcessingFailureException pfe) {
			System.out.println("获得所支持的管理器,异常！---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("EMS", mgrHolder);
			emsMgr = EMSMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + emsMgr.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		return true;
	}
	
	private boolean initOrb() {
		try {
			String[] args = new String[2];
			args[0] = "-ORBInitRef";
			args[1] = "NameService=corbaloc::" + "127.0.0.1" + ":" + "8801"
					+ "/NameService";
			for (int i = 0; i < args.length; i++) {
				System.out.println("初始化ORB对象的启动参数为： arg[" + i + "] = "
						+ args[i]);
			}
			orb = org.omg.CORBA.ORB.init(args, null);
			System.out.println("成功初始化ORB对象!－－－－Initialize Orb");
		} catch (SystemException ex) {
			System.out.println("初始化ORB对象异常！");
			System.out.println(ex.getMessage());
		}
		if (orb == null) {
			System.out.println("orb == null");
			return false;
		}
		return true;
	}

	private boolean initNameService() {

		try {
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			nameContext = NamingContextHelper.narrow(objRef);
			System.out.println("成功获取取名字服务!－－－－Get Nameservice reference");
		} catch (org.omg.CORBA.ORBPackage.InvalidName ex) {
			System.out.println("取名字服务索引异常!");
			System.out.println(ex.getMessage());
			return false;
		}

		return true;
	}

	private EmsSessionFactory_I getEmsSessionFactory() {

		EmsSessionFactory_I emsFactory = null;
		NameComponent[] name = new NameComponent[1];
		name[0] = new NameComponent("DATAX/INM200", "EMSFactory");
		org.omg.CORBA.Object obj = null;
		try {
			obj = nameContext.resolve(name);
			emsFactory = EmsSessionFactory_IHelper.narrow(obj);
			System.out
					.println("成功获取EMSSession工厂!   Get Reference to EMSSessionFactory");
		} catch (NotFound ex) {
			System.out.println("取EMSSession工厂异常!---NotFound---");
			System.out.println(ex.getMessage());
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
			System.out.println("取EMSSession工厂异常!---InvalidName---");
			System.out.println(ex.getMessage());
		} catch (org.omg.CosNaming.NamingContextPackage.CannotProceed ex) {
			System.out.println("取EMSSession工厂异常!---CannotProceed---");
			System.out.println(ex.getMessage());
		}

		return emsFactory;
	}

	private boolean initAndActiveRootPOA() {
		try {
			rootPOA = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();
		} catch (AdapterInactive e) {
			ExceptionManage.dispose(e,CorbaTest.class);
			return false;
		} catch (InvalidName e) {
			ExceptionManage.dispose(e,CorbaTest.class);
			return false;
		}
		return true;
	}

	private NmsSession_I getNmsSession_I() {
		NmsSession_I session = null;
		try {
			NmsSessionImpl nmsSessionImpl = new NmsSessionImpl();
			rootPOA.activate_object(nmsSessionImpl);
			System.out.println("创建NmsSession并且托管给POA!");
			NmsSession_IPOATie tie = new NmsSession_IPOATie(nmsSessionImpl,
					rootPOA);
			System.out.println("在orb上激活NmsSession对象!");
			session = tie._this(orb);
		} catch (ServantAlreadyActive e) {
			ExceptionManage.dispose(e,CorbaTest.class);
		} catch (WrongPolicy e) {
			ExceptionManage.dispose(e,CorbaTest.class);
		}

		return session;
	}
	
	public class NmsSessionImpl extends NmsSession_IPOA {

		@Override
		public void eventLossOccurred(String startTime, String notificationId) {
			// TODO Auto-generated method stub

		}

		@Override
		public void eventLossCleared(String endTime) {
			// TODO Auto-generated method stub

		}

		@Override
		public void alarmLossOccurred(String startTime, String notificationId) {
			// TODO Auto-generated method stub

		}

		@Override
		public Session_I associatedSession() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void ping() {
			// TODO Auto-generated method stub
			System.out.println("ems ping nms");
		}

		@Override
		public void endSession() {
			// TODO Auto-generated method stub

		}

	}
}
