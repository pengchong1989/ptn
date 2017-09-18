package com.nms.corba.test.CorbaTest_sy;

import managedElementManager.ManagedElementMgr_I;
import multiLayerSubnetwork.MultiLayerSubnetworkMgr_I;
import multiLayerSubnetwork.MultiLayerSubnetworkMgr_IHelper;
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

import performance.AdministrativeState_T;
import session.Session_I;
import subnetworkConnection.CrossConnect_T;
import subnetworkConnection.NetworkRouted_T;
import subnetworkConnection.ProtectionEffort_T;
import subnetworkConnection.Reroute_T;
import subnetworkConnection.RouteDescriptor_T;
import subnetworkConnection.Route_THolder;
import subnetworkConnection.SNCCreateData_T;
import subnetworkConnection.SNCIterator_IHolder;
import subnetworkConnection.SNCModifyData_T;
import subnetworkConnection.SNCState_T;
import subnetworkConnection.SNCType_T;
import subnetworkConnection.StaticProtectionLevel_T;
import subnetworkConnection.SubnetworkConnectionList_THolder;
import subnetworkConnection.SubnetworkConnection_THolder;
import subnetworkConnection.TPData_T;
import transmissionParameters.LayeredParameters_T;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EServiceType;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import emsMgr.EMSMgr_IHelper;
import emsSession.EmsSession_I;
import emsSession.EmsSession_IHolder;
import emsSession.EmsSession_IPackage.managerNames_THolder;
import emsSessionFactory.EmsSessionFactory_I;
import emsSessionFactory.EmsSessionFactory_IHelper;
import flowDomain.FlowDomainMgr_I;
import flowDomain.FlowDomainMgr_IHelper;
import flowDomainFragment.FDFrCreateData_T;
import flowDomainFragment.FDFrIterator_IHolder;
import flowDomainFragment.FDFrList_THolder;
import flowDomainFragment.FDFrModifyData_T;
import flowDomainFragment.FDFrRoute_THolder;
import flowDomainFragment.FlowDomainFragment_T;
import flowDomainFragment.FlowDomainFragment_THolder;
import flowDomainFragment.MatrixFlowDomainFragmentList_THolder;
import flowDomainFragment.MatrixFlowDomainFragment_T;
import globaldefs.ConnectionDirection_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

public class T2 {

	static EmsSession_I emsSession = null;
	static org.omg.CORBA.ORB orb = null;
	static NamingContext nameContext = null;
	static POA rootPOA = null;
	
	//网元管理者
	ManagedElementMgr_I meMgr = null;
    //ems管理者
	EMSMgr_I emsMgr = null;
	/**
	 * 1、MultiLayerSubnetworkMgr_Impl中的方法都只是验证了 名字的长度 不用具体验证时干啥的？
	 * 验证不符合规范的 东西？比如id传的不是数字是别的字母等东西
	 * 
	 * 2、getRoute()中如果返回的长度为0，没有个提示？
	 * 
	 * 3、getAllSubnetworkConnections中short[] layers = new short[]{3,4,5};没报错？
	 * 
	 * 4、deleteSNC 中删除tunnel时没有此tunnel不报错？
	 * 
	 * 
	 */
	public static void main(String args[]) {
		try {
//			CorbaConnect cc = new CorbaConnect();
//			cc.isConnect();
//			T2 t=new T2();
//			emsSession = cc.emsSession;
////			CorbaTest.getSNC();
			System.out.println(Integer.parseInt(("14/4").split("/")[0]));

		} catch (Exception e) {
			ExceptionManage.dispose(e,T2.class);
		}
	}
	
	/**
	 * 一条子网snc
	 */
	private static void getSNC(){
		
		MultiLayerSubnetworkMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("MultiLayerSubnetwork", mgrHolder);
			mgr_I = MultiLayerSubnetworkMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[3];
		nameAndStringValue_T[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndStringValue_T[1] = new NameAndStringValue_T("MultiLayerSubnetwork","");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.TUNNEL.getValue()+"/22");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.PW.getValue()+"/1");
		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.CES.getValue()+"/13");
		SubnetworkConnection_THolder subnetworkConnection_THolder = new SubnetworkConnection_THolder();
		
		try {
			mgr_I.getSNC(nameAndStringValue_T,subnetworkConnection_THolder);
			System.out.println(subnetworkConnection_THolder.value.userLabel+"**");
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
	
	}
	
	/**
	 * 所有子网snc
	 */
	private static void getAllSubnetworkConnections(){
		MultiLayerSubnetworkMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("MultiLayerSubnetwork", mgrHolder);
			mgr_I = MultiLayerSubnetworkMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
System.out.println("*****************");		
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[3];
		nameAndStringValue_T[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndStringValue_T[1] = new NameAndStringValue_T("MultiLayerSubnetwork","");
		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.TUNNEL.getValue()+"/22");
	
//		short[] layers = new short[]{(short) ELayerRate.TUNNEL.getValue(),(short) ELayerRate.PW.getValue(),(short) ELayerRate.CES.getValue()};
		short[] layers = new short[]{3,4,5};
		int howmany = 5;
		
		SubnetworkConnectionList_THolder sncList = new SubnetworkConnectionList_THolder();
		
		SNCIterator_IHolder sncIt = new SNCIterator_IHolder();
		
		try {
			mgr_I.getAllSubnetworkConnections(nameAndStringValue_T, layers, howmany, sncList, sncIt);
			System.out.println();
			if (sncList.value != null && sncList.value.length > 0) {
				for (int i = 0; i < sncList.value.length; i++) {
					System.out.println(sncList.value[i].name[1].value);
				}
			}
			if (sncIt.value != null) {
				while (sncIt.value.next_n(5, sncList)) {
					for (int i = 0; i < sncList.value.length; i++) {
						System.out.println(sncList.value[i].name[1].value);
					}
				}
				for (int i = 0; i < sncList.value.length; i++) {
					System.out.println(sncList.value[i].name[1].value);
				}
				sncIt.value.destroy();
			}
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
	}
	
	/**
	 * 创建子网连接snc
	 */
	private  void createSNC(){
		MultiLayerSubnetworkMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("MultiLayerSubnetwork", mgrHolder);
			mgr_I = MultiLayerSubnetworkMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		SNCCreateData_T sncCreateData_T = new SNCCreateData_T();
//		sncCreateData_T.layerRate = (short) ELayerRate.TUNNEL.getValue();
		sncCreateData_T.layerRate = (short) ELayerRate.PW.getValue();
//		sncCreateData_T.layerRate = (short) ELayerRate.PW.getValue();
		sncCreateData_T.userLabel = "test__22";
		sncCreateData_T.forceUniqueness = true;
		sncCreateData_T.owner = "DATAX/1";
		sncCreateData_T.direction = ConnectionDirection_T.CD_BI;
		sncCreateData_T.staticProtectionLevel = StaticProtectionLevel_T.FULLY_PROTECTED;
		sncCreateData_T.protectionEffort = ProtectionEffort_T.EFFORT_SAME;
		sncCreateData_T.rerouteAllowed = Reroute_T.RR_NA;
		sncCreateData_T.networkRouted = NetworkRouted_T.NR_NA;
		sncCreateData_T.sncType = SNCType_T.ST_ADD_DROP_A;
		/**
		 * 获取路由信息
		 *   没有XC 只需要给予默认值既可
		 *   
		 */
		sncCreateData_T.ccInclusions = new CrossConnect_T[0];
//		sncCreateData_T.ccInclusions = getCrossConnect_T();//tunnel的中间路由信息
		sncCreateData_T.neTpInclusions =new NameAndStringValue_T[0][0];
		sncCreateData_T.fullRoute = false;
		sncCreateData_T.neTpSncExclusions = new NameAndStringValue_T[0][0];
		// NameAndStringValue_T[] addinfo =additionalInfo();
		sncCreateData_T.additionalCreationInfo = additionalInfo();
		
		sncCreateData_T.aEnd = new NameAndStringValue_T[1][2];
		sncCreateData_T.aEnd[0] = new NameAndStringValue_T[2];	
//		/**
//		 * tunnel  pw用FTP   指端口
//		 */
//		sncCreateData_T.aEnd[0][0] = new NameAndStringValue_T("ManagedElement", 7+ "");
//		sncCreateData_T.aEnd[0][1] = new NameAndStringValue_T("PTP",271+ "");
//		sncCreateData_T.zEnd = new NameAndStringValue_T[1][2];
//		sncCreateData_T.zEnd[0] = new NameAndStringValue_T[2];
//		sncCreateData_T.zEnd[0][0] = new NameAndStringValue_T("ManagedElement",8 + "");
//		sncCreateData_T.zEnd[0][1] = new NameAndStringValue_T("PTP",303+ "");
		
		//ces   有4层结构
		sncCreateData_T.aEnd = new NameAndStringValue_T[1][4];
		sncCreateData_T.aEnd[0] = new NameAndStringValue_T[4];
		sncCreateData_T.aEnd[0][0] = new NameAndStringValue_T("EMS", "");
		sncCreateData_T.aEnd[0][1] = new NameAndStringValue_T("ManagedElement", 6+ "");
		sncCreateData_T.aEnd[0][2] = new NameAndStringValue_T("PTP", 219+ "");
		sncCreateData_T.aEnd[0][3] = new NameAndStringValue_T("CTP",  ELayerRate.CES.getValue()+"/271");
		sncCreateData_T.zEnd = new NameAndStringValue_T[1][4];
		sncCreateData_T.zEnd[0] = new NameAndStringValue_T[4];
		sncCreateData_T.zEnd[0][0] = new NameAndStringValue_T("EMS", "");
		sncCreateData_T.zEnd[0][1] = new NameAndStringValue_T("ManagedElement",7+ "");
		sncCreateData_T.zEnd[0][2] = new NameAndStringValue_T("PTP", 283+ "");
		sncCreateData_T.zEnd[0][3] = new NameAndStringValue_T("CTP", ELayerRate.CES.getValue()+"/303");
		
		SubnetworkConnection_THolder sncDataHolder = new SubnetworkConnection_THolder();
		
		StringHolder result = new StringHolder();
		
		try {
			mgr_I.createSNC(sncCreateData_T, sncDataHolder, result);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
	}
	
	/**
	 * 修改SNC连接信息
	 */
	private void modifySNC(){
		MultiLayerSubnetworkMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("MultiLayerSubnetwork", mgrHolder);
			mgr_I = MultiLayerSubnetworkMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[3];
		nameAndStringValue_T[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndStringValue_T[1] = new NameAndStringValue_T("MultiLayerSubnetwork","");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.TUNNEL.getValue()+"/23");
		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.CES.getValue()+"/2");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.PW.getValue()+"/12");
		SNCModifyData_T modifyData = new SNCModifyData_T();
		modifyData.userLabel = "testmodify";
		modifyData.forceUniqueness = true;
		modifyData.owner = "DATAX/1";
		modifyData.direction = ConnectionDirection_T.CD_UNI;
		modifyData.modifyType = "Tunnel";
		modifyData.retainOldSNC = true;
		modifyData.modifyServers_allowed = true;
		modifyData.staticProtectionLevel = StaticProtectionLevel_T.FULLY_PROTECTED;
		modifyData.protectionEffort = ProtectionEffort_T.EFFORT_SAME;
		modifyData.rerouteAllowed = Reroute_T.RR_NA;
		modifyData.networkRouted = NetworkRouted_T.NR_NA;
		modifyData.sncType = SNCType_T.ST_ADD_DROP_Z;
		modifyData.layerRate = 1;
		modifyData.addedOrNewRoute = getRoutDescriptor();
		modifyData.removedRoute = getRoutDescriptor();
		modifyData.fullRoute = true;
		modifyData.neTpSncExclusions = new NameAndStringValue_T[0][0];
		modifyData.neTpInclusions =new NameAndStringValue_T[0][0];
		modifyData.aEnd = new NameAndStringValue_T[0][0];
		modifyData.zEnd = new NameAndStringValue_T[0][0];
		modifyData.additionalCreationInfo = getAdditionalInfo();
		
		SubnetworkConnection_THolder subnetConn = new SubnetworkConnection_THolder();
		
		StringHolder result = new StringHolder();
		
		try {
			mgr_I.modifySNC(nameAndStringValue_T,modifyData,subnetConn,result);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
		
	}
	
	/**
	 * 激活SNC连接信息
	 */
	private void activateSNC(){
		
//		NameAndStringValue_T[] sncName, SubnetworkConnection_THolder subnetConn,
//		StringHolder holder
		
		MultiLayerSubnetworkMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("MultiLayerSubnetwork", mgrHolder);
			mgr_I = MultiLayerSubnetworkMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[3];
		nameAndStringValue_T[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndStringValue_T[1] = new NameAndStringValue_T("MultiLayerSubnetwork","");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.TUNNEL.getValue()+"/23");
		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.CES.getValue()+"/2");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.PW.getValue()+"/12");
		
		SubnetworkConnection_THolder subnetConn = new SubnetworkConnection_THolder();
		
		StringHolder result = new StringHolder();
		
		try {
			mgr_I.activateSNC(nameAndStringValue_T,subnetConn,result);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
	}
	
	
	
	/**
	 * 根据标识查询SNC的交叉路由信息
	 */
	private static void getRoute(){
		MultiLayerSubnetworkMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("MultiLayerSubnetwork", mgrHolder);
			mgr_I = MultiLayerSubnetworkMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[3];
		nameAndStringValue_T[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndStringValue_T[1] = new NameAndStringValue_T("MultiLayerSubnetwork","");
		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.TUNNEL.getValue()+"/49");

		Route_THolder route=new Route_THolder();
		
		try {
			mgr_I.getRoute(nameAndStringValue_T, true, route);
			System.out.println(route.value.length);//如果返回的长度为0，没有个提示？///////////////////////////////
			System.out.println(route.value[0].active);
			System.out.println(route.value[0].ccType);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
		
	}
	
	/**
	 * 删除SNC连接信息
	 */
	private static void deleteSNC(){
		MultiLayerSubnetworkMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("MultiLayerSubnetwork", mgrHolder);
			mgr_I = MultiLayerSubnetworkMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[3];
		nameAndStringValue_T[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndStringValue_T[1] = new NameAndStringValue_T("MultiLayerSubnetwork","");
		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.TUNNEL.getValue()+"/188");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.PW.getValue()+"/");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.CES.getValue()+"/13");
		
		try {
			mgr_I.deleteSNC(nameAndStringValue_T);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
	}
	

	
	
	
	
	
	
//////////////////////////////////////////一下为以太网业务测试////////////////////////////////////////	
	/**
	 * 查全部端到端以太网业务y
	 */
	private static void getAllFDFrs(){
		FlowDomainMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("FlowDomain", mgrHolder);
			mgr_I = FlowDomainMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
//		ELINE(9),
//		ETREE(10),
//		ELAN(11) fdName!=null&&fdName.length>2&&fdName[0].equals(CorbaConfig.getInstanse().getCorbaEmsName())&&fdName[1].equals("1"))
		
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[3];
		nameAndStringValue_T[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndStringValue_T[1] = new NameAndStringValue_T("MultiLayerSubnetwork","1");
		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.TUNNEL.getValue()+"/188");
		
		short[] connectivityRateList=new short[0];
		
		FDFrList_THolder fdfrList =new FDFrList_THolder();//  输出参数，查询的结果返回值	
		FDFrIterator_IHolder fdfrIt=new FDFrIterator_IHolder();	
		
		try {System.out.println("以太网端到端业务测试！！！！！！！");
			mgr_I.getAllFDFrs(nameAndStringValue_T, 5, connectivityRateList, fdfrList, fdfrIt);
			
			if (fdfrList.value != null && fdfrList.value.length > 0) {
				for (int i = 0; i < fdfrList.value.length; i++) {
					System.out.println(fdfrList.value[i].name[1].value);
				}
			}
			if (fdfrIt.value != null) {
				while (fdfrIt.value.next_n(5, fdfrList)) {
					for (int i = 0; i < fdfrList.value.length; i++) {
						System.out.println(fdfrList.value[i].name[1].value);
					}
				}
				for (int i = 0; i < fdfrList.value.length; i++) {
					System.out.println(fdfrList.value[i].name[1].value);
				}
				fdfrIt.value.destroy();
			}
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
	}
	
	/**
	 * 查询指定以太网业务y
	 */
	private static void getFDFr(){
		
		FlowDomainMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("FlowDomain", mgrHolder);
			mgr_I = FlowDomainMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[3];
		nameAndStringValue_T[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndStringValue_T[1] = new NameAndStringValue_T("MultiLayerSubnetwork","1");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ELAN.getValue()+"/7");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ELINE.getValue()+"/6");
		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ETHSERVICE.getValue()+"/11");
		FlowDomainFragment_THolder fdfr = new FlowDomainFragment_THolder();
		
		try {System.out.println("某一业务测试");
			mgr_I.getFDFr(nameAndStringValue_T,fdfr);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
	}
	
	/**
	 * 根据标识查询一条以太网业务的路由信息y
	 */
	private static void getFDFrRoute(){
		FlowDomainMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("FlowDomain", mgrHolder);
			mgr_I = FlowDomainMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[3];
		nameAndStringValue_T[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndStringValue_T[1] = new NameAndStringValue_T("MultiLayerSubnetwork","1");
		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ETHSERVICE.getValue()+"/7");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ELINE.getValue()+"/6");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ETREE.getValue()+"/11");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",3+"/11");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection","3"+"/");
		FDFrRoute_THolder route=new FDFrRoute_THolder();
		
		try {
			mgr_I.getFDFrRoute(nameAndStringValue_T,route);
			System.out.println(route.value.length);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
	}
	
	/**
	 * 删除以太网业务y
	 */
	private static void deactivateAndDeleteFDFr(){
		FlowDomainMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("FlowDomain", mgrHolder);
			mgr_I = FlowDomainMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[3];
		nameAndStringValue_T[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndStringValue_T[1] = new NameAndStringValue_T("MultiLayerSubnetwork","1");
		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ETHSERVICE.getValue()+"/7");

		StringHolder errorReason = new StringHolder();
		
		try {
			mgr_I.deactivateAndDeleteFDFr(nameAndStringValue_T,errorReason);//
			System.out.println(errorReason.value);
			System.out.println(errorReason.value);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
	}
	
	
	/**
	 * 去激活以太网业务y
	 */
	private static void deactivateFDFr(){
		FlowDomainMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("FlowDomain", mgrHolder);
			mgr_I = FlowDomainMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[3];
		nameAndStringValue_T[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndStringValue_T[1] = new NameAndStringValue_T("MultiLayerSubnetwork","1");
		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ETHSERVICE.getValue()+"/1");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ELINE.getValue()+"/6");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ETREE.getValue()+"/11");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",3+"/11");

		FlowDomainFragment_THolder fdfr = new FlowDomainFragment_THolder();
		
		try {
			mgr_I.deactivateFDFr(nameAndStringValue_T, fdfr);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
		
	}
	
	/**
	 * 激活以太网业务 y
	 */
	private static void activateFDFr(){
		FlowDomainMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("FlowDomain", mgrHolder);
			mgr_I = FlowDomainMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[3];
		nameAndStringValue_T[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndStringValue_T[1] = new NameAndStringValue_T("MultiLayerSubnetwork","1");
		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ETHSERVICE.getValue()+"/1");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ELINE.getValue()+"/7");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ETREE.getValue()+"/11");

		FlowDomainFragment_THolder fdfr = new FlowDomainFragment_THolder();
		
		try {System.out.println("开始激活以太网测试");
			mgr_I.activateFDFr(nameAndStringValue_T,fdfr);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
		
	}
	
	/**
	 * 创建以太网业务
	 */
	private  void createAndActivateFDFr(){
		
		FlowDomainMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("FlowDomain", mgrHolder);
			mgr_I = FlowDomainMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[3];
		nameAndStringValue_T[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndStringValue_T[1] = new NameAndStringValue_T("MultiLayerSubnetwork","1");
		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ETHSERVICE.getValue()+"/1");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ELINE.getValue()+"/1");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ETREE.getValue()+"/1");
		LayeredParameters_T lay=new LayeredParameters_T();
		lay.layer=(short) ELayerRate.ETHSERVICE.getValue();
//		lay.layer=(short) ELayerRate.ELINE.getValue();
//		lay.layer=(short) ELayerRate.ETREE.getValue();
		lay.transmissionParams=nameAndStringValue_T;
		
		FDFrCreateData_T fdCreateData_T=new FDFrCreateData_T();
		fdCreateData_T.name = nameAndStringValue_T;
		fdCreateData_T.userLabel="etree_11";
		fdCreateData_T.forceUniqueness=true;
		fdCreateData_T.owner="DATX/1";
		fdCreateData_T.networkAccessDomain="";
		fdCreateData_T.direction=ConnectionDirection_T.CD_BI;
		fdCreateData_T.administrativeState=AdministrativeState_T.AS_Locked;
		fdCreateData_T.transmissionParams=lay;
		fdCreateData_T.fullRoute=true;
		fdCreateData_T.fdfrType=EServiceType.ELAN.toString();
//		fdCreateData_T.fdfrType=EServiceType.ELINE.toString();
//		fdCreateData_T.fdfrType=EServiceType.ETREE.toString();
		fdCreateData_T.additionalCreationInfo = additionalInfo();
//		createData, mfdfrs,theFDFr, errorReason
		
		MatrixFlowDomainFragment_T[] mfdfrs = new MatrixFlowDomainFragment_T[1];
		mfdfrs = getMaFdfr().value;
		mfdfrs[0].transmissionParams = lay;
		mfdfrs[1].transmissionParams = lay;
		
		
		FlowDomainFragment_THolder theFDFr = new FlowDomainFragment_THolder();
		theFDFr.value = new FlowDomainFragment_T();
		theFDFr.value.name = nameAndStringValue_T;
		theFDFr.value.userLabel = "eline";
		theFDFr.value.nativeEMSName = " ";
		theFDFr.value.owner = "DATX/1";
		theFDFr.value.direction = ConnectionDirection_T.CD_BI;
		theFDFr.value.transmissionParams = new LayeredParameters_T();
		theFDFr.value.transmissionParams.layer = 6;
		theFDFr.value.transmissionParams.transmissionParams = nameAndStringValue_T;
		theFDFr.value.zEnd =  new TPData_T[0];
		theFDFr.value.aEnd =  new TPData_T[0];
		theFDFr.value.networkAccessDomain = " ";
		theFDFr.value.flexible = true;
		theFDFr.value.administrativeState = AdministrativeState_T.AS_Locked;
		theFDFr.value.fdfrState = SNCState_T.SNCS_ACTIVE;
		theFDFr.value.fdfrType = "eline";
		theFDFr.value.additionalInfo = this.getAdditionalInfo();
		
		StringHolder errorReason = new StringHolder();
		
		try {
			mgr_I.createAndActivateFDFr(fdCreateData_T,mfdfrs,theFDFr,errorReason);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
		
	}
	
	/**
	 * 修改以太网业务y
	 */
	private void modifyFDFr(){
		
//		NameAndStringValue_T[] fdfrName,
//		FDFrModifyData_T fdfrModifyData,
//		FlowDomainFragment_THolder newFDFr, StringHolder errorReason
		
		FlowDomainMgr_I mgr_I = null;
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("FlowDomain", mgrHolder);
			mgr_I = FlowDomainMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + mgr_I.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
		NameAndStringValue_T[] nameAndStringValue_T = new NameAndStringValue_T[3];
		nameAndStringValue_T[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndStringValue_T[1] = new NameAndStringValue_T("MultiLayerSubnetwork","1");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ELAN.getValue()+"/1");
		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ETHSERVICE.getValue()+"/1");
//		nameAndStringValue_T[2] = new NameAndStringValue_T("SubnetworkConnection",ELayerRate.ETREE.getValue()+"/1");
	
		FDFrModifyData_T fdfrModifyData = new FDFrModifyData_T();
		fdfrModifyData.userLabel = "eline";
		fdfrModifyData.forceUniqueness = true;
		fdfrModifyData.owner = "DATX/1";
		fdfrModifyData.networkAccessDomain = " ";
		fdfrModifyData.administrativeState = AdministrativeState_T.AS_Locked;
		fdfrModifyData.transmissionParams = new LayeredParameters_T();
		fdfrModifyData.transmissionParams.layer = (short) ELayerRate.ETHSERVICE.getValue();
		fdfrModifyData.transmissionParams.transmissionParams = nameAndStringValue_T;
		fdfrModifyData.tpNamesToRemove =new NameAndStringValue_T[0][0];
		fdfrModifyData.aEndTPNames = new NameAndStringValue_T[0][0];
		fdfrModifyData.zEndTPNames =new NameAndStringValue_T[0][0];
		fdfrModifyData.internalTPNames = new NameAndStringValue_T[0][0];
		fdfrModifyData.additionalModificationInfo = this.getAdditionalInfo();
		
		FlowDomainFragment_THolder newFDFr = new FlowDomainFragment_THolder();
		newFDFr.value = new FlowDomainFragment_T();
		newFDFr.value.name = nameAndStringValue_T;
		newFDFr.value.userLabel = "eline";
		newFDFr.value.nativeEMSName = " ";
		newFDFr.value.owner = "DATX/1";
		newFDFr.value.direction = ConnectionDirection_T.CD_BI;
		newFDFr.value.transmissionParams = new LayeredParameters_T();
		newFDFr.value.transmissionParams.layer = 6;
		newFDFr.value.transmissionParams.transmissionParams = nameAndStringValue_T;
		newFDFr.value.zEnd =  new TPData_T[0];
		newFDFr.value.aEnd =  new TPData_T[0];
		newFDFr.value.networkAccessDomain = " ";
		newFDFr.value.flexible = true;
		newFDFr.value.administrativeState = AdministrativeState_T.AS_Locked;
		newFDFr.value.fdfrState = SNCState_T.SNCS_ACTIVE;
		newFDFr.value.fdfrType = "eline";
		newFDFr.value.additionalInfo = this.getAdditionalInfo();
		
		StringHolder errorReason = new StringHolder();
		
		try {
			mgr_I.modifyFDFr(nameAndStringValue_T,fdfrModifyData,newFDFr,errorReason);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,T2.class);
		}
		
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
			ExceptionManage.dispose(e,T2.class);
			return false;
		} catch (InvalidName e) {
			ExceptionManage.dispose(e,T2.class);
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
			ExceptionManage.dispose(e,T2.class);
		} catch (WrongPolicy e) {
			ExceptionManage.dispose(e,T2.class);
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
	
	/**
	 * 设置MatrixFlowDomainFragmentList_THolder
	 * @return
	 */
	private  MatrixFlowDomainFragmentList_THolder getMaFdfr(){
		MatrixFlowDomainFragmentList_THolder maFdfr=new MatrixFlowDomainFragmentList_THolder();
		/*
		 * 测试时  ： 注意：：：  eline  
		 * 				maFdfr.value=new MatrixFlowDomainFragment_T[1];
		 * 		etree,														elan 时，由于有多条数据，所以 看情况new
		 * maFdfr.value=new MatrixFlowDomainFragment_T[2];  maFdfr.value=new MatrixFlowDomainFragment_T[3];
		 */
		maFdfr.value=new MatrixFlowDomainFragment_T[2];
		maFdfr.value[0]=new MatrixFlowDomainFragment_T();
		maFdfr.value[0].direction= ConnectionDirection_T.CD_BI;
//		maFdfr.value[0].transmissionParams=this.getLayeredParameters_T();
		maFdfr.value[0].aEnd=this.getaAndZname(1,53);
		maFdfr.value[0].zEnd=this.getaAndZname(2,73);
		maFdfr.value[0].flexible=true;
		maFdfr.value[0].active=true;
		maFdfr.value[0].mfdfrType="ELINE";
		maFdfr.value[0].additionalInfo=this.getAdditionalInfo();
		maFdfr.value[0].additionalInfo[38] = new NameAndStringValue_T(this.SERVICEBYPWID, "3/50");// 激活
		
		maFdfr.value[1]=new MatrixFlowDomainFragment_T();
		maFdfr.value[1].direction= ConnectionDirection_T.CD_BI;
//		maFdfr.value[1].transmissionParams=this.getLayeredParameters_T();
		maFdfr.value[1].aEnd=this.getaAndZname(1,53);
		maFdfr.value[1].zEnd=this.getaAndZname(3,202);
		maFdfr.value[1].flexible=true;
		maFdfr.value[1].active=true;
		maFdfr.value[1].mfdfrType="ETREE";
		maFdfr.value[1].additionalInfo=getAdditionalInfo();
		maFdfr.value[1].additionalInfo[38] = new NameAndStringValue_T(this.SERVICEBYPWID, "3/48");// 激活
		
//		maFdfr.value[2]=new MatrixFlowDomainFragment_T();
//		maFdfr.value[2].direction= ConnectionDirection_T.CD_BI;
//		maFdfr.value[2].transmissionParams=this.getLayeredParameters_T();
//		maFdfr.value[2].aEnd=this.getaAndZname(1,53);
//		maFdfr.value[2].zEnd=this.getaAndZname(2,73);
//		maFdfr.value[2].flexible=true;
//		maFdfr.value[2].active=true;
//		maFdfr.value[2].mfdfrType="ELAN";
//		maFdfr.value[2].additionalInfo=this.getAdditionalInfo();
//		maFdfr.value[2].additionalInfo[38] = new NameAndStringValue_T(this.SERVICEBYPWID, "2/45");// 激活
		
		return maFdfr;
	}
	/**
	 * 创建snc /以太网 等 附加信息
	 * @return
	 */
	private NameAndStringValue_T[] additionalInfo() {
		NameAndStringValue_T[] additionalInfo = new NameAndStringValue_T[31];

		additionalInfo[0] = new NameAndStringValue_T(A2ZEIR, "0");// 路径类型
		additionalInfo[1] = new NameAndStringValue_T(Z2AEIR, "0");// 路径类型
		additionalInfo[2] = new NameAndStringValue_T(QOSTYPE, "lLSP");// 路径类型
		// qos描述信息
		additionalInfo[3] = new NameAndStringValue_T(A2ZCIR, "0");// 路径类型
		additionalInfo[4] = new NameAndStringValue_T(Z2ACIR, "0");// 路径类型
		additionalInfo[5] = new NameAndStringValue_T(A2ZPIR, "0");// 路径类型
		additionalInfo[6] = new NameAndStringValue_T(Z2APIR, "0");// 路径类型
		additionalInfo[7] = new NameAndStringValue_T(A2ZCBS, "-1");// 路径类型
		additionalInfo[8] = new NameAndStringValue_T(Z2ACBS, "-1");// 路径类型
		additionalInfo[9] = new NameAndStringValue_T(A2ZPBS, "0");// 路径类型
		additionalInfo[10] = new NameAndStringValue_T(Z2APBS, "0");// 路径类型
		additionalInfo[11] = new NameAndStringValue_T(A2ZEBS, "-1");// 路径类型
		additionalInfo[12] = new NameAndStringValue_T(Z2AEBS, "-1");// 路径类型
		//固定
		additionalInfo[13] = new NameAndStringValue_T(QOSCOS, "EF");// 路径类型
		additionalInfo[15] = new NameAndStringValue_T(A2ZVLANID, "1");// vlanid=下话增加VLAN																	
		additionalInfo[16] = new NameAndStringValue_T(A2ZEXITRULE, "0"); // 出口规则，关联code表主键		
		additionalInfo[17] = new NameAndStringValue_T(A2ZVLANPRI, "0");// vlanpri=下话增加VLAN		
		additionalInfo[18] = new NameAndStringValue_T(A2ZPORTMODEL, "0");// 端口模式，关联code表主键
		// Z端标识Z2A 开头
		additionalInfo[19] = new NameAndStringValue_T(Z2AVLANID, "1");// vlanid=下话增加VLAN		
		additionalInfo[20] = new NameAndStringValue_T(Z2AEXITRULE, "0"); // 出口规则，关联code表主键		
		additionalInfo[21] = new NameAndStringValue_T(Z2AVLANPRI, "0");// vlanpri=下话增加VLAN																	// PRI
		additionalInfo[22] = new NameAndStringValue_T(Z2APORTMODEL, "0");// 端口模式，关联code表主键
	//	additionalInfo[23] = new NameAndStringValue_T(CREATETIME, "2014-05-06 15:13:10");// 创建时间
		
		/**
		 * 新建，修改 PW 时用到这2个属性
		 */
		additionalInfo[14] = new NameAndStringValue_T(this.PWBYTUNNELID, ELayerRate.TUNNEL.getValue()+"/52");// 使用的PW路径标识
		additionalInfo[23] = new NameAndStringValue_T(this.PWTYPE, "1");// 使用的PW类型
		

		
		additionalInfo[25] = new NameAndStringValue_T(FDFRRATE, ELayerRate.PW.getValue() + "");// 业务层速率
		additionalInfo[26] = new NameAndStringValue_T(CREATEUSER, "admin");// 用户名称
	
		additionalInfo[27] = new NameAndStringValue_T(SRCINLABEL, "1006");
		additionalInfo[28] = new NameAndStringValue_T(SRCOUTLABEL, "1001");
		additionalInfo[29] = new NameAndStringValue_T(DESTINLABEL, "1003");
		additionalInfo[30] = new NameAndStringValue_T(DESTOUTLABEL, "1004");
		additionalInfo[24] = new NameAndStringValue_T("isActive", EActiveStatus.ACTIVITY.getValue() + "");
		return additionalInfo;
	}
	
	/**
	 * 为 A  ，Z端 赋值（网元ID,AC   id）
	 * @param siteId
	 * @param acId
	 * @return
	 */
	private NameAndStringValue_T[][] getaAndZname(int siteId,int acId){
		NameAndStringValue_T[][] name=new NameAndStringValue_T[1][3];
		name[0]=new NameAndStringValue_T[3];
		name[0][0]=new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		name[0][1]=new NameAndStringValue_T("ManagedElement",siteId+"");
		name[0][2]=new NameAndStringValue_T("PTP",acId+"");
		return name;
	}
	
	/**
	 *以太网 获取附加信息
	 * 
	 * @return
	 */
	private NameAndStringValue_T[] getAdditionalInfo() {
		NameAndStringValue_T[] additionalInfo = new NameAndStringValue_T[39];

		additionalInfo[0] = new NameAndStringValue_T(A2ZEIR, "0");// 路径类型
		additionalInfo[1] = new NameAndStringValue_T(Z2AEIR, "0");// 路径类型
		additionalInfo[2] = new NameAndStringValue_T(QOSTYPE, "L2");// 路径类型
		// qos描述信息
		additionalInfo[3] = new NameAndStringValue_T(A2ZCIR, "0");// 路径类型
		additionalInfo[4] = new NameAndStringValue_T(Z2ACIR, "0");// 路径类型
		additionalInfo[5] = new NameAndStringValue_T(A2ZPIR, "0");// 路径类型
		additionalInfo[6] = new NameAndStringValue_T(Z2APIR, "0");// 路径类型
		additionalInfo[7] = new NameAndStringValue_T(A2ZCBS, "-1");// 路径类型
		additionalInfo[8] = new NameAndStringValue_T(Z2ACBS, "-1");// 路径类型
		additionalInfo[9] = new NameAndStringValue_T(A2ZPBS, "0");// 路径类型
		additionalInfo[10] = new NameAndStringValue_T(Z2APBS, "0");// 路径类型
		additionalInfo[11] = new NameAndStringValue_T(A2ZEBS, "-1");// 路径类型
		additionalInfo[12] = new NameAndStringValue_T(Z2AEBS, "-1");// 路径类型
		additionalInfo[13] = new NameAndStringValue_T(QOSCOS, "EF");// 路径类型
		additionalInfo[14] = new NameAndStringValue_T(QOSNAME, "1267");// 路径类型
		additionalInfo[15] = new NameAndStringValue_T(A2ZVLANID, "1");// vlanid=下话增加VLAN																	
		additionalInfo[16] = new NameAndStringValue_T(A2ZEXITRULE, "0"); // 出口规则，关联code表主键		
		additionalInfo[17] = new NameAndStringValue_T(A2ZVLANPRI, "0");// vlanpri=下话增加VLAN		
		additionalInfo[18] = new NameAndStringValue_T(A2ZPORTMODEL, "0");// 端口模式，关联code表主键
		// Z端标识Z2A 开头
		additionalInfo[19] = new NameAndStringValue_T(Z2AVLANID, "1");// vlanid=下话增加VLAN		
		additionalInfo[20] = new NameAndStringValue_T(Z2AEXITRULE, "0"); // 出口规则，关联code表主键		
		additionalInfo[21] = new NameAndStringValue_T(Z2AVLANPRI, "0");// vlanpri=下话增加VLAN																	// PRI
		additionalInfo[22] = new NameAndStringValue_T(Z2APORTMODEL, "0");// 端口模式，关联code表主键
		additionalInfo[23] = new NameAndStringValue_T(CREATETIME, "2014-05-06 15:13:10");// 创建时间
		additionalInfo[24] = new NameAndStringValue_T(this.CREATEUSER, "admin");// 创建时间
		
	
		// pw 用到的
		additionalInfo[25] = new NameAndStringValue_T(FDFRRATE, ELayerRate.PW.getValue() + "");// 业务层速率
		additionalInfo[26] = new NameAndStringValue_T("e", "admin");// 用户名称
	
		//tunnel   用到的属性
		additionalInfo[27] = new NameAndStringValue_T(SRCINLABEL, "1212");
		additionalInfo[28] = new NameAndStringValue_T(SRCOUTLABEL, "1213");
		additionalInfo[29] = new NameAndStringValue_T(DESTINLABEL, "1213");
		additionalInfo[30] = new NameAndStringValue_T(DESTOUTLABEL, "1212");
		additionalInfo[31] = new NameAndStringValue_T("isActive", EActiveStatus.ACTIVITY.getValue() + "");
		
		/**
		 * 新建，修改 PW 时用到这2个属性
		 */
		additionalInfo[33] = new NameAndStringValue_T(this.PWBYTUNNELID, "45");// 使用的PW路径标识
		additionalInfo[32] = new NameAndStringValue_T(this.PWTYPE, "1");// 使用的PW类型
		
		/**
		 * 新建，修改CES 用到下面4个属性
		 */
		additionalInfo[37] = new NameAndStringValue_T(this.CESBYPWID, "31");// 使用的pw路径标识
		additionalInfo[34] = new NameAndStringValue_T(this.CESTYPE, "2");// 使用的ces类型
		additionalInfo[35] = new NameAndStringValue_T(this.CLIENTID, "0");// 使用的PW类型
		additionalInfo[36] = new NameAndStringValue_T(this.FULLROUTE, "1");// 激活
		
		additionalInfo[38] = new NameAndStringValue_T(this.SERVICEBYPWID, "2/41");// 激活
		//以太网业务   关联PW 主键 ，etree,elan等 业务 有多条数据： 所以：需要在外层重写[38] 
		return additionalInfo;
	}
	
	//路由信息
	private RouteDescriptor_T[] getRoutDescriptor() {
		RouteDescriptor_T[] routeDescriptor = new RouteDescriptor_T[1];
		routeDescriptor[0] = new RouteDescriptor_T();
		routeDescriptor[0].id = "18";
		routeDescriptor[0].intended = " ";
		routeDescriptor[0].actualState = " ";
		routeDescriptor[0].administrativeState = " ";
		routeDescriptor[0].inUseBy = " ";
		routeDescriptor[0].exclusive = " ";
		routeDescriptor[0].routeXCs = getCrossConnect_T();
		routeDescriptor[0].additionalInfo = getAdditionalInfo();
		return routeDescriptor;
	}

	
	/**
	 * 获取  路由信息
	*		getCrossConnect_T
	 * @return
	 */
	private CrossConnect_T[] getCrossConnect_T() {
		/**
		 * 没有中间路由时 ——-既没有XC    new CrossConnect_T[1];
		 *   若有 则 new CrossConnect_T[XC数值];
		 *   --------------------------------
		 *   注意：：*****
		 *   	在给路由详细信息时 ：   aEndNameList   zEndNameList
		 *     网元值相同，标签不同
		 */
		CrossConnect_T[] crossConnect_T = new CrossConnect_T[2];
		
		crossConnect_T[0] = new CrossConnect_T();
		crossConnect_T[0].active = false;
		crossConnect_T[0].direction = ConnectionDirection_T.CD_BI;
		crossConnect_T[0].ccType = SNCType_T.ST_ADD_DROP_A;
		crossConnect_T[0].aEndNameList =new NameAndStringValue_T[1][2];
		crossConnect_T[0].zEndNameList = new NameAndStringValue_T[1][2];
		crossConnect_T[0].aEndNameList[0][0] = new NameAndStringValue_T("ManagedElement", 8+ "");
		crossConnect_T[0].aEndNameList[0][1] = new NameAndStringValue_T("PTP",179 + "");		
		crossConnect_T[0].zEndNameList[0][0] = new NameAndStringValue_T("ManagedElement",8 + "");
		crossConnect_T[0].zEndNameList[0][1] = new NameAndStringValue_T("PTP",179+ "");
	
		crossConnect_T[0].additionalInfo=new NameAndStringValue_T[4];
		crossConnect_T[0].additionalInfo[0] = new NameAndStringValue_T(SRCINLABEL, "1001");
		crossConnect_T[0].additionalInfo[1] = new NameAndStringValue_T(SRCOUTLABEL, "1006");
		crossConnect_T[0].additionalInfo[2] = new NameAndStringValue_T(DESTINLABEL, "1005");
		crossConnect_T[0].additionalInfo[3] = new NameAndStringValue_T(DESTOUTLABEL, "1002");
		
		crossConnect_T[1] = new CrossConnect_T();
		crossConnect_T[1].active = false;
		crossConnect_T[1].direction = ConnectionDirection_T.CD_BI;
		crossConnect_T[1].ccType = SNCType_T.ST_ADD_DROP_A;
		crossConnect_T[1].aEndNameList =new NameAndStringValue_T[1][2];
		crossConnect_T[1].zEndNameList = new NameAndStringValue_T[1][2];
		crossConnect_T[1].aEndNameList[0][0] = new NameAndStringValue_T("ManagedElement", 6+ "");
		crossConnect_T[1].aEndNameList[0][1] = new NameAndStringValue_T("PTP",195 + "");		
		crossConnect_T[1].zEndNameList[0][0] = new NameAndStringValue_T("ManagedElement",6 + "");
		crossConnect_T[1].zEndNameList[0][1] = new NameAndStringValue_T("PTP",195+ "");
	
		crossConnect_T[1].additionalInfo=new NameAndStringValue_T[4];
		crossConnect_T[1].additionalInfo[0] = new NameAndStringValue_T(SRCINLABEL, "1002");
		crossConnect_T[1].additionalInfo[1] = new NameAndStringValue_T(SRCOUTLABEL, "1005");
		crossConnect_T[1].additionalInfo[2] = new NameAndStringValue_T(DESTINLABEL, "1004");
		crossConnect_T[1].additionalInfo[3] = new NameAndStringValue_T(DESTOUTLABEL, "1003");
//		
//		crossConnect_T[2] = new CrossConnect_T();
//		crossConnect_T[2].active = false;
//		crossConnect_T[2].direction = ConnectionDirection_T.CD_BI;
//		crossConnect_T[2].ccType = SNCType_T.ST_ADD_DROP_A;
//		crossConnect_T[2].aEndNameList =new NameAndStringValue_T[1][2];
//		crossConnect_T[2].zEndNameList = new NameAndStringValue_T[1][2];
//		crossConnect_T[2].aEndNameList[0][0] = new NameAndStringValue_T("ManagedElement", 2+ "");
//		crossConnect_T[2].aEndNameList[0][1] = new NameAndStringValue_T("PTP",100 + "");		
//		crossConnect_T[2].zEndNameList[0][0] = new NameAndStringValue_T("ManagedElement",3 + "");
//		crossConnect_T[2].zEndNameList[0][1] = new NameAndStringValue_T("PTP",132+ "");
//	
//		crossConnect_T[2].additionalInfo=new NameAndStringValue_T[4];
//		crossConnect_T[2].additionalInfo[0] = new NameAndStringValue_T(SRCINLABEL, "1213");
//		crossConnect_T[2].additionalInfo[1] = new NameAndStringValue_T(SRCOUTLABEL, "1212");
//		crossConnect_T[2].additionalInfo[2] = new NameAndStringValue_T(DESTINLABEL, "1212");
//		crossConnect_T[2].additionalInfo[3] = new NameAndStringValue_T(DESTOUTLABEL, "1213");
		return crossConnect_T;
	}

//	
//	private FlowDomainFragment_T getFlowDomainFragment_T() {
//		FlowDomainFragment_T flowDomainFragment_T = new FlowDomainFragment_T();
//		flowDomainFragment_T.name = this.getName();
//		flowDomainFragment_T.userLabel = "eline";
//		flowDomainFragment_T.nativeEMSName = " ";
//		flowDomainFragment_T.owner = "DATX/1";
//		flowDomainFragment_T.direction = ConnectionDirection_T.CD_BI;
//		flowDomainFragment_T.transmissionParams = new LayeredParameters_T();
//		flowDomainFragment_T.transmissionParams.layer = 6;
//		flowDomainFragment_T.transmissionParams.transmissionParams = this.getName();
//		flowDomainFragment_T.zEnd =  new TPData_T[0];
//		flowDomainFragment_T.aEnd =  new TPData_T[0];
//		flowDomainFragment_T.networkAccessDomain = " ";
//		flowDomainFragment_T.flexible = true;
//		flowDomainFragment_T.administrativeState = AdministrativeState_T.AS_Locked;
//		flowDomainFragment_T.fdfrState = SNCState_T.SNCS_ACTIVE;
//		flowDomainFragment_T.fdfrType = "eline";
//		flowDomainFragment_T.additionalInfo = this.getAdditionalInfo();
//		return flowDomainFragment_T;
//	}
	// FlowDomain
	protected  String SRCINLABEL = "SrcInLabel"; // 源入标签
	protected  String SRCOUTLABEL = "SrcOutLabel"; // 源出标签
	protected  String DESTINLABEL = "DestInLabel"; // 宿入标签
	protected  String DESTOUTLABEL = "DestOutLabel"; // 宿出标签
	protected  String QOSTYPE = "qosType";
	protected  String QOSNAME = "qosName";
	protected  String QOSCOS = "qosCos";
	protected  String A2ZCIR = "A2ZCIR";// 正向CIR
	protected  String Z2ACIR = "Z2ACIR";// 反向 CIR
	protected  String A2ZPIR = "A2ZPIR";// 正向CIR
	protected  String Z2APIR = "Z2APIR";// 反向 CIR
	protected  String A2ZCBS = "A2ZCBS";// 正向CIR
	protected  String Z2ACBS = "Z2ACBS";// 反向 CIR
	protected  String A2ZPBS = "A2ZPBS";// 正向CIR
	protected  String Z2APBS = "Z2APBS";// 反向 CIR
	protected  String A2ZEBS = "A2ZEBS";// 正向CIR
	protected  String Z2AEBS = "Z2AEBS";// 反向 CIR
	protected  String A2ZEIR = "A2ZEIR";// 正向CIR
	protected  String Z2AEIR = "Z2AEIR";// 反向 CIR
	protected  String A2ZVLANID = "A2ZvlanId";// A端 VlanID
	protected  String Z2AVLANID = "Z2AvlanId";// z端 VlanID
	protected  String A2ZEXITRULE = "A2ZexitRule";// A端 出口规则，关联code表
	protected  String Z2AEXITRULE = "Z2AexitRule";// z端 出口规则，关联code表
	protected  String A2ZVLANPRI = "A2Zvlanpri";
	protected  String Z2AVLANPRI = "Z2Avlanpri";
	protected  String A2ZPORTMODEL = "A2ZportModel";// A端 端口模式，关联code表
	protected  String Z2APORTMODEL = "Z2AportModel";// Z端 端口模式，关联code表
	protected  String FDFRRATE = "fdfrRate";// 业务层速率
	protected  String SERVICEBYPWID = "serviceByPwId";// 以太网业务关联的PW 主键ID
	protected  String CREATETIME = "CreateTime";// 创建人
	protected  String CREATEUSER = "createuser";// 创建时间	
	
	//pw
	protected  final String PWTYPE="pwType";//pw类型
	protected  final String PWBYTUNNELID="ServerConnections";//pw承载的tunnel主键id
	
	//ces
	protected final String CESBYPWID="cesByPwID";//ces 关联的pw的id
	protected final String CESTYPE="cesType";// ces类型
	protected final String CLIENTID="clientId";//关联客户id
	protected  final String FULLROUTE="fullroute";//路由是否完整
}

