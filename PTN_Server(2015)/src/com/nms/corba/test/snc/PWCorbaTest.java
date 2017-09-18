package com.nms.corba.test.snc;

import managedElementManager.ManagedElementMgr_I;
import multiLayerSubnetwork.MultiLayerSubnetworkMgr_I;
import multiLayerSubnetwork.MultiLayerSubnetworkMgr_IHelper;

import org.omg.CORBA.StringHolder;

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
import subnetworkConnection.SubnetworkConnection_T;
import subnetworkConnection.SubnetworkConnection_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.corba.test.common.CorbaConnect;
import com.nms.db.enums.EActiveStatus;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import flowDomain.FlowDomainMgr_I;
import flowDomainFragment.FlowDomainFragment_THolder;
import globaldefs.ConnectionDirection_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

/**
 * SNC 测试类
 * @author sy
 *
 */
public class PWCorbaTest extends TestBase {
	private ICorbaSession session = null;
	private ManagedElementMgr_I meMgr = null;// 管理者
	private static EMSMgr_I emsMgr = null;// EMS管理者
	private static CorbaConnect corbaConnect = null;
	// FileTransferMgr_I fileTrans
	private SubnetworkConnection_THolder subnetworkConnection_THolder = new SubnetworkConnection_THolder();
	private StringHolder stringHolder = new StringHolder();
	private MultiLayerSubnetworkMgr_I multiLayerSubnetworkMgr_Impl = null;// SNC
	private FlowDomainFragment_THolder flowDoamin_THolder = new FlowDomainFragment_THolder();
	private FlowDomainMgr_I flowDomainMgr_I = null;// 以太网接口对象
	
	private String pwIdentity=ELayerRate.PW.getValue()+"/3";

	// ELayerRate : 业务枚举类
	public PWCorbaTest() {
		super();
		this.corbaConnect = super.getCorbaConnect();
		this.session = new ICorbaSession();
	}

	public static void main(String[] args) {
		try {
			PWCorbaTest pwCorbaTest = new PWCorbaTest();
//			pwCorbaTest.activateSNC(corbaConnect);
			pwCorbaTest.createSNC(corbaConnect);
//			pwCorbaTest.deleteSNC(corbaConnect);
//			pwCorbaTest.deactivateSNC(corbaConnect);
//			pwCorbaTest.modifySNC(corbaConnect);
//			pwCorbaTest.getSNC(corbaConnect);
//			pwCorbaTest.getAllSnc(corbaConnect);
//			pwCorbaTest.getRoutteSnc(corbaConnect);
//			pwCorbaTest.getSncById(corbaConnect);
			System.out.println("测试完成");
		} catch (Exception e) {
			ExceptionManage.dispose(e,PWCorbaTest.class);
		}
	}

	/**
	 * 删除SNC
	 * 
	 * @param connect
	 */
	public void deleteSNC(CorbaConnect connect) {
		System.out.println("开始测试...");

		try {
			subnetworkConnection_THolder = getManagedElement(connect);
			// 删除
			multiLayerSubnetworkMgr_Impl.deleteSNC(getName(this.pwIdentity));
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 *根据主键查询SNC对象
	 * 
	 * @param connect
	 */
	public void getSNC(CorbaConnect connect) {
		System.out.println("开始测试...");

		try {
			subnetworkConnection_THolder = getManagedElement(connect);
			multiLayerSubnetworkMgr_Impl.getSNC(getName(this.pwIdentity), subnetworkConnection_THolder);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 激活snc
	 * 
	 * @param connect
	 */
	public void activateSNC(CorbaConnect connect) {
		System.out.println("开始测试...");

		try {
			subnetworkConnection_THolder = getManagedElement(connect);
			multiLayerSubnetworkMgr_Impl.activateSNC(getName(this.pwIdentity),subnetworkConnection_THolder, stringHolder);
			System.out.println(stringHolder.value);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 修改snc
	 * 
	 * @param connect
	 */
	public void modifySNC(CorbaConnect connect) {
		System.out.println("开始测试...");

		try {
			subnetworkConnection_THolder = getManagedElement(connect);

			SNCModifyData_T sncModify = new SNCModifyData_T();
			sncModify = getSNCModifyData_T(sncModify);
			multiLayerSubnetworkMgr_Impl.modifySNC(getName(this.pwIdentity), sncModify,subnetworkConnection_THolder,stringHolder);
			
			System.out.println(stringHolder.value);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 新建
	 * 
	 * @param connect
	 */
	public void createSNC(CorbaConnect connect) {
		System.out.println("开始测试...");

		try {
			subnetworkConnection_THolder = getManagedElement(connect);
			//获取新建对象的信息
			SNCCreateData_T createData = getCreateData();
			multiLayerSubnetworkMgr_Impl.createSNC(createData,subnetworkConnection_THolder, stringHolder);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 根据指定的速率查询所有SNC业务
	 * @param connect
	 */

	public void getAllSnc(CorbaConnect connect) {
		System.out.println("开始测试...");

		try {
			subnetworkConnection_THolder = getManagedElement(connect);
			stringHolder.value = " ";
			SNCIterator_IHolder sncI = new SNCIterator_IHolder();
			short[] list = new short[1];
			list[0] = (short) ELayerRate.PW.getValue();
			SubnetworkConnectionList_THolder nameList = new SubnetworkConnectionList_THolder();// getName(),																				// sncI
			nameList.value = new SubnetworkConnection_T[1];
			multiLayerSubnetworkMgr_Impl.getAllSubnetworkConnections(getName(""), list, 10, nameList, sncI);
			
//			for(SubnetworkConnection_T subnetworkConnection_T:nameList.value){
//				System.out.println(subnetworkConnection_T.userLabel);
//			}
//			
//			while(sncI.value.next_n(10, nameList)){
//				for(SubnetworkConnection_T subnetworkConnection_T:nameList.value){
//					System.out.println(subnetworkConnection_T.userLabel);
//				}
//			}
//			
//			for(SubnetworkConnection_T subnetworkConnection_T:nameList.value){
//				System.out.println(subnetworkConnection_T.userLabel);
//			}
			
			System.out.println(nameList.value.length);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 根据指定的速率查询所有SNC业务   ---路由
	 * @param connect
	 */
	public void getRoutteSnc(CorbaConnect connect) {
		System.out.println("开始测试...");

		try {
			subnetworkConnection_THolder = getManagedElement(connect);
			Route_THolder route=new Route_THolder();
			multiLayerSubnetworkMgr_Impl.getRoute(getName(this.pwIdentity), true, route);
			
			System.out.println(route.value.length);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 *根据主键查询SNC对象
	 * @param connect
	 */
	public void getSncById(CorbaConnect connect) {
		System.out.println("开始测试...");

		try {
			subnetworkConnection_THolder = getManagedElement(connect);
			multiLayerSubnetworkMgr_Impl.getSNC(getName(this.pwIdentity), subnetworkConnection_THolder);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}


	/**
	 * 去激活snc
	 * 
	 * @param connect
	 */
	public void deactivateSNC(CorbaConnect connect) {
		System.out.println("开始测试...");

		try {
			subnetworkConnection_THolder = getManagedElement(connect);

			multiLayerSubnetworkMgr_Impl.deactivateSNC(getName(this.pwIdentity), subnetworkConnection_THolder, stringHolder);
			
			System.out.println(stringHolder.value);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	

	private SNCModifyData_T getSNCModifyData_T(SNCModifyData_T sncModify) {
		sncModify.userLabel = "PW_kk_test";
		sncModify.forceUniqueness = true;
		sncModify.owner = "DATAX/1";
		sncModify.direction = ConnectionDirection_T.CD_UNI;
		sncModify.modifyType = "PW";
		sncModify.retainOldSNC = true;
		sncModify.modifyServers_allowed = true;
		sncModify.staticProtectionLevel = StaticProtectionLevel_T.FULLY_PROTECTED;
		sncModify.protectionEffort = ProtectionEffort_T.EFFORT_SAME;
		sncModify.rerouteAllowed = Reroute_T.RR_NA;
		sncModify.networkRouted = NetworkRouted_T.NR_NA;
		sncModify.sncType = SNCType_T.ST_ADD_DROP_Z;
		sncModify.layerRate = 1;
		sncModify.addedOrNewRoute = getRoutDescriptor();
		sncModify.removedRoute = getRoutDescriptor();
		sncModify.fullRoute = true;
		sncModify.neTpSncExclusions = new NameAndStringValue_T[0][0];
		sncModify.neTpInclusions =new NameAndStringValue_T[0][0];
		sncModify.aEnd = new NameAndStringValue_T[0][0];
		sncModify.zEnd = new NameAndStringValue_T[0][0];
		sncModify.additionalCreationInfo = getAdditionalInfo();
		return sncModify;
	}

	private SubnetworkConnection_THolder getSubnetworkConnection_THolder(SubnetworkConnection_THolder subnetworkConnection_THolder) {
		subnetworkConnection_THolder.value = new SubnetworkConnection_T();
		stringHolder.value = " ";
		subnetworkConnection_THolder.value.aEnd = getTPData();
		subnetworkConnection_THolder.value.zEnd = getTPData();
		subnetworkConnection_THolder.value.name = getName("");
		subnetworkConnection_THolder.value.userLabel = " PW";
		subnetworkConnection_THolder.value.owner = "DATAX/1";
		subnetworkConnection_THolder.value.sncState = SNCState_T.SNCS_PENDING;
		subnetworkConnection_THolder.value.nativeEMSName = " ";
		subnetworkConnection_THolder.value.direction = ConnectionDirection_T.CD_UNI;
		subnetworkConnection_THolder.value.sncType = SNCType_T.ST_ADD_DROP_Z;
		subnetworkConnection_THolder.value.rerouteAllowed = Reroute_T.RR_NA;
		subnetworkConnection_THolder.value.networkRouted = NetworkRouted_T.NR_NA;
		subnetworkConnection_THolder.value.additionalInfo = getAdditionalInfo();
		return subnetworkConnection_THolder;
	}

	

	/**
	 *以太网 获取附加信息
	 * 
	 * @return
	 */
	private NameAndStringValue_T[] getAdditionalInfo() {
		NameAndStringValue_T[] addinfo = new NameAndStringValue_T[10];
		addinfo[0] = new NameAndStringValue_T();
		addinfo[0].name = "fullroute";
		addinfo[0].value = EActiveStatus.ACTIVITY.getValue() + "";
		addinfo[2] = new NameAndStringValue_T();
		addinfo[2].name = "createuser";
		addinfo[2].value = "songyang";
		addinfo[3] = new NameAndStringValue_T();
		addinfo[3].name = "CreateTime";
		addinfo[3].value = "1921-12-01";
		addinfo[4] = new NameAndStringValue_T();
		addinfo[4].name = "clientId";
		addinfo[4].value = "1";
		addinfo[1] = new NameAndStringValue_T();
		addinfo[1].name = "cesType";
		addinfo[1].value = "5";
		addinfo[5] = new NameAndStringValue_T();
		addinfo[5].name = "cesByPwID";
		addinfo[5].value = "40";
		addinfo[6] = new NameAndStringValue_T(SRCINLABEL, "1213");
		addinfo[7] = new NameAndStringValue_T(SRCOUTLABEL, "1212");
		addinfo[8] = new NameAndStringValue_T(DESTINLABEL, "1212");
		addinfo[9] = new NameAndStringValue_T(DESTOUTLABEL, "1213");
		return addinfo;
	}

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
	 * 创建SNC 传人的对象
	 * @return
	 */
	private SNCCreateData_T getCreateData() {
//		SNCModifyData_T sncUpdate = new SNCModifyData_T();
//		sncUpdate.userLabel = "PW_kk_ceshi";

		SNCCreateData_T sncCreateData_T = new SNCCreateData_T();
		sncCreateData_T.layerRate = (short) ELayerRate.PW.getValue();
		sncCreateData_T.userLabel = "PW_kk_ceshi";
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
		sncCreateData_T.ccInclusions = getCrossConnect_T();
		sncCreateData_T.neTpInclusions =new NameAndStringValue_T[0][0];
		sncCreateData_T.fullRoute = false;
		sncCreateData_T.neTpSncExclusions = new NameAndStringValue_T[0][0];
		// NameAndStringValue_T[] addinfo =additionalInfo();
		sncCreateData_T.additionalCreationInfo = additionalInfo();
		
		sncCreateData_T.aEnd = new NameAndStringValue_T[1][2];
		sncCreateData_T.aEnd[0] = new NameAndStringValue_T[2];	
//		/**
//		 * PW  pw用FTP   指端口
//		 */
		sncCreateData_T.aEnd[0][0] = new NameAndStringValue_T("ManagedElement", 2+ "");
		sncCreateData_T.aEnd[0][1] = new NameAndStringValue_T("PTP",59 + "");
		
		sncCreateData_T.zEnd = new NameAndStringValue_T[1][2];
		sncCreateData_T.zEnd[0] = new NameAndStringValue_T[2];
		sncCreateData_T.zEnd[0][0] = new NameAndStringValue_T("ManagedElement",3 + "");
		sncCreateData_T.zEnd[0][1] = new NameAndStringValue_T("PTP",122+ "");
		
//		//ces   有4层结构
//		sncCreateData_T.aEnd = new NameAndStringValue_T[1][4];
//		sncCreateData_T.aEnd[0] = new NameAndStringValue_T[4];
//		sncCreateData_T.aEnd[0][0] = new NameAndStringValue_T("EMS", "");
//		sncCreateData_T.aEnd[0][1] = new NameAndStringValue_T("ManagedElement", 3 + "");
//		sncCreateData_T.aEnd[0][2] = new NameAndStringValue_T("PTP",  "");
//		sncCreateData_T.aEnd[0][3] = new NameAndStringValue_T("CTP", "8/108");
//		sncCreateData_T.zEnd = new NameAndStringValue_T[1][4];
//		sncCreateData_T.zEnd[0] = new NameAndStringValue_T[4];
//		sncCreateData_T.zEnd[0][0] = new NameAndStringValue_T("EMS", "");
//		sncCreateData_T.zEnd[0][1] = new NameAndStringValue_T("ManagedElement",2 + "");
//		sncCreateData_T.zEnd[0][2] = new NameAndStringValue_T("PTP",  "");
//		sncCreateData_T.zEnd[0][3] = new NameAndStringValue_T("CTP", "8/75");
		return sncCreateData_T;
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
		CrossConnect_T[] crossConnect_T = new CrossConnect_T[1];
//		return crossConnect_T;
		
		crossConnect_T[0] = new CrossConnect_T();
		crossConnect_T[0].active = true;
		crossConnect_T[0].direction = ConnectionDirection_T.CD_BI;
		crossConnect_T[0].ccType = SNCType_T.ST_ADD_DROP_A;
		crossConnect_T[0].aEndNameList =new NameAndStringValue_T[1][2];
		crossConnect_T[0].zEndNameList = new NameAndStringValue_T[1][2];
		crossConnect_T[0].aEndNameList[0][0] = new NameAndStringValue_T("ManagedElement", 2+ "");
		crossConnect_T[0].aEndNameList[0][1] = new NameAndStringValue_T("PTP",109 + "");		
		crossConnect_T[0].zEndNameList[0][0] = new NameAndStringValue_T("ManagedElement",2 + "");
		crossConnect_T[0].zEndNameList[0][1] = new NameAndStringValue_T("PTP",107+ "");
	
		crossConnect_T[0].additionalInfo=new NameAndStringValue_T[4];
		crossConnect_T[0].additionalInfo[0] = new NameAndStringValue_T(SRCINLABEL, "101");
		crossConnect_T[0].additionalInfo[1] = new NameAndStringValue_T(SRCOUTLABEL, "104");
		crossConnect_T[0].additionalInfo[2] = new NameAndStringValue_T(DESTINLABEL, "103");
		crossConnect_T[0].additionalInfo[3] = new NameAndStringValue_T(DESTOUTLABEL, "102");
		
//		crossConnect_T[1] = new CrossConnect_T();
//		crossConnect_T[1].active = false;
//		crossConnect_T[1].direction = ConnectionDirection_T.CD_BI;
//		crossConnect_T[1].ccType = SNCType_T.ST_ADD_DROP_A;
//		crossConnect_T[1].aEndNameList =new NameAndStringValue_T[1][2];
//		crossConnect_T[1].zEndNameList = new NameAndStringValue_T[1][2];
//		crossConnect_T[1].aEndNameList[0][0] = new NameAndStringValue_T("ManagedElement", 6+ "");
//		crossConnect_T[1].aEndNameList[0][1] = new NameAndStringValue_T("PTP",195 + "");		
//		crossConnect_T[1].zEndNameList[0][0] = new NameAndStringValue_T("ManagedElement",6 + "");
//		crossConnect_T[1].zEndNameList[0][1] = new NameAndStringValue_T("PTP",195+ "");
	
//		crossConnect_T[1].additionalInfo=new NameAndStringValue_T[4];
//		crossConnect_T[1].additionalInfo[0] = new NameAndStringValue_T(SRCINLABEL, "1002");
//		crossConnect_T[1].additionalInfo[1] = new NameAndStringValue_T(SRCOUTLABEL, "1005");
//		crossConnect_T[1].additionalInfo[2] = new NameAndStringValue_T(DESTINLABEL, "1004");
//		crossConnect_T[1].additionalInfo[3] = new NameAndStringValue_T(DESTOUTLABEL, "1003");
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

	/**
	 * 创建snc /以太网 等 附加信息
	 * @return
	 */
	private NameAndStringValue_T[] additionalInfo() {
		NameAndStringValue_T[] additionalInfo = new NameAndStringValue_T[22];
		additionalInfo[0] = new NameAndStringValue_T(A2ZEIR, "0");// 路径类型
		additionalInfo[1] = new NameAndStringValue_T(Z2AEIR, "0");// 路径类型
		additionalInfo[2] = new NameAndStringValue_T(QOSTYPE, "LLSP");// 路径类型
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
		additionalInfo[14] = new NameAndStringValue_T(CREATEUSER, "admin");// 用户名称
		additionalInfo[15] = new NameAndStringValue_T(this.PWBYTUNNELID, ELayerRate.TUNNEL.getValue()+"/74");// 使用的PW路径标识
		additionalInfo[16] = new NameAndStringValue_T(SRCINLABEL, "132");
		additionalInfo[17] = new NameAndStringValue_T(SRCOUTLABEL, "131");
		additionalInfo[18] = new NameAndStringValue_T(DESTINLABEL, "131");
		additionalInfo[19] = new NameAndStringValue_T(DESTOUTLABEL, "132");
		additionalInfo[20] = new NameAndStringValue_T("isActive", EActiveStatus.ACTIVITY.getValue() + "");
		additionalInfo[21] = new NameAndStringValue_T(PWTYPE, "PDH");
		return additionalInfo;
	}

	/**
	 * 获取SNC管理对象
	 * 
	 * @param connect
	 * @return
	 */
	private SubnetworkConnection_THolder getManagedElement(CorbaConnect connect) {
		// 获取管理者
		Common_IHolder mgrHolder = new Common_IHolder();

		// 不同接口管理者名称不同
		try {
			connect.emsSession.getManager("MultiLayerSubnetwork", mgrHolder);
			multiLayerSubnetworkMgr_Impl = MultiLayerSubnetworkMgr_IHelper.narrow(mgrHolder.value);
			subnetworkConnection_THolder = getSubnetworkConnection_THolder(subnetworkConnection_THolder);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		return subnetworkConnection_THolder;
	}

}
