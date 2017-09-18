package com.nms.corba.test.snc;

import managedElementManager.ManagedElementMgr_I;

import org.omg.CORBA.StringHolder;

import performance.AdministrativeState_T;
import subnetworkConnection.SNCState_T;
import subnetworkConnection.SubnetworkConnection_THolder;
import transmissionParameters.LayeredParameters_T;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.corba.test.common.CorbaConnect;
import com.nms.db.enums.EServiceType;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import flowDomain.FlowDomainMgr_I;
import flowDomain.FlowDomainMgr_IHelper;
import flowDomainFragment.FDFrCreateData_T;
import flowDomainFragment.FDFrIterator_IHolder;
import flowDomainFragment.FDFrList_THolder;
import flowDomainFragment.FDFrModifyData_T;
import flowDomainFragment.FDFrRoute_THolder;
import flowDomainFragment.FlowDomainFragment_T;
import flowDomainFragment.FlowDomainFragment_THolder;
import flowDomainFragment.MatrixFlowDomainFragment_T;
import globaldefs.ConnectionDirection_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesList_THolder;
import globaldefs.ProcessingFailureException;

/**
 * 以太网测试类
 * 
 * @author sy
 * 
 */
public class FlowDomainTest extends TestBase {
	private ICorbaSession session = null;
	private ManagedElementMgr_I meMgr = null;// 管理者
	private static EMSMgr_I emsMgr = null;// EMS管理者
	private static CorbaConnect corbaConnect = null;
	// FileTransferMgr_I fileTrans
	private SubnetworkConnection_THolder subnetworkConnection_THolder = new SubnetworkConnection_THolder();
	private StringHolder stringHolder = new StringHolder();
	private FlowDomainFragment_THolder flowDoamin_THolder = new FlowDomainFragment_THolder();
	private FlowDomainMgr_I flowDomainMgr_I = null;// 以太网接口对象

	private String fdfrName = ELayerRate.ETHSERVICE.getValue() + "/17";

	public FlowDomainTest() {
		super();
		this.corbaConnect = super.getCorbaConnect();
		this.session = new ICorbaSession();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FlowDomainTest fdfr = new FlowDomainTest();
//		 fdfr.getAllFDFrs_Test(corbaConnect);
//		 fdfr.getFDFr_Test(corbaConnect);
		 fdfr.createFlowDomain(corbaConnect);
//		 fdfr.activateFlowDomainMgr_I(corbaConnect);
//		 fdfr.deleteFlowDomain(corbaConnect);
//		fdfr.createFlowDomain(corbaConnect);
		// fdfr.modifyFlowDomain(corbaConnect);
		// fdfr.deactivateFlowDomainMgr_I(corbaConnect);
		System.out.print("测试完成");

	}

	/**
	 * 查询所有以太网业务
	 * 
	 * @param connect
	 */
	public void getAllFDFrs_Test(CorbaConnect connect) {
		System.out.println("开始测试...");
		try {
			flowDoamin_THolder = getFlowDomain(connect);
			short[] connectivityRateList = new short[0];
			FDFrList_THolder fdfrList = new FDFrList_THolder();// 输出参数，查询的结果返回值
			FDFrIterator_IHolder fdfrIt = new FDFrIterator_IHolder();
			flowDomainMgr_I.getAllFDFrs(getName(""), 10, connectivityRateList, fdfrList, fdfrIt);

			System.out.println(fdfrList.value.length);
		} catch (ProcessingFailureException e) {
			System.out.println(e.errorReason);
		}
	}

	/**
	 * 根据主键查询对应的以太网业务
	 * 
	 * @param connect
	 */
	public void getFDFr_Test(CorbaConnect connect) {
		System.out.println("开始测试...");
		try {
			flowDoamin_THolder = getFlowDomain(connect);
			flowDomainMgr_I.getFDFr(this.getFdName("3"), flowDoamin_THolder);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 根据主键查询一条业务的完整路由，主要是etree和elan
	 * 
	 * @param connect
	 */
	public void getFDFrRoute_Test(CorbaConnect connect) {
		System.out.println("开始测试...");
		try {
			flowDoamin_THolder = getFlowDomain(connect);
			FDFrRoute_THolder route = new FDFrRoute_THolder();
			flowDomainMgr_I.getFDFrRoute(this.getFdName("3"), route);

			System.out.println(route.value.length);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 获取以太网管理对象
	 * 
	 * @param connect
	 * @return
	 */
	private FlowDomainFragment_THolder getFlowDomain(CorbaConnect connect) {
		// 获取管理者
		Common_IHolder mgrHolder = new Common_IHolder();
		// FlowDomainFragment_THolder flowDoamin_THolder=null;
		// 不同接口管理者名称不同
		try {
			connect.emsSession.getManager("FlowDomain", mgrHolder);
			flowDomainMgr_I = FlowDomainMgr_IHelper.narrow(mgrHolder.value);
			flowDoamin_THolder = getFlowDomainFragment_THolder(flowDoamin_THolder);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		return flowDoamin_THolder;
	}

	/**
	 * 去激活以太网
	 * 
	 * @param connect
	 */
	public void deactivateFlowDomainMgr_I(CorbaConnect connect) {
		System.out.println("开始测试...");
		try {
			flowDoamin_THolder = getFlowDomain(connect);
			flowDomainMgr_I.deactivateFDFr(getName(this.fdfrName), flowDoamin_THolder);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 删除以太网
	 * 
	 * @param connect
	 */
	public void deleteFlowDomain(CorbaConnect connect) {
		System.out.println("开始测试...");
		try {
			flowDoamin_THolder = getFlowDomain(connect);
			stringHolder.value = " ";
			// 删除
			flowDomainMgr_I.deactivateAndDeleteFDFr(getName(this.fdfrName), stringHolder);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 创建以太网
	 * 
	 * @param connect
	 */
	public void createFlowDomain(CorbaConnect connect) {
		System.out.println("开始测试...");

		try {
			flowDoamin_THolder = getFlowDomain(connect);
			NamingAttributesList_THolder internalTPs = new NamingAttributesList_THolder();
			internalTPs.value = new NameAndStringValue_T[0][0];
			FDFrCreateData_T createData = this.getFDCreateDate_T();
			// 创建
			flowDomainMgr_I.createAndActivateFDFr(createData, this.getMaFdfr(), flowDoamin_THolder, stringHolder);
			
			System.out.println(stringHolder.value);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 激活以太网
	 * 
	 * @param connect
	 */
	public void activateFlowDomainMgr_I(CorbaConnect connect) {
		System.out.println("开始测试...");

		try {
			flowDoamin_THolder = getFlowDomain(connect);
			flowDomainMgr_I.activateFDFr(getName(this.fdfrName), flowDoamin_THolder);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 修改以太网
	 * 
	 * @param connect
	 */
	public void modifyFlowDomain(CorbaConnect connect) {
		System.out.println("开始测试...");

		try {
			flowDoamin_THolder = getFlowDomain(connect);
			FDFrModifyData_T fdfrModifyData = getFDFrModifyData_T();
			flowDomainMgr_I.modifyFDFr(getName(this.fdfrName), fdfrModifyData, flowDoamin_THolder, stringHolder);

		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 测试新建 ： 为新建对象赋值
	 * 
	 * @return
	 */
	private FDFrCreateData_T getFDCreateDate_T() {
		FDFrCreateData_T fdCreateData_T = new FDFrCreateData_T();
		fdCreateData_T.name = super.getName("");
		fdCreateData_T.userLabel = "elan_kk_test";
		fdCreateData_T.forceUniqueness = true;
		fdCreateData_T.owner = "DATX/1";
		fdCreateData_T.networkAccessDomain = "";
		fdCreateData_T.direction = ConnectionDirection_T.CD_BI;
		fdCreateData_T.administrativeState = AdministrativeState_T.AS_Locked;
		fdCreateData_T.transmissionParams = this.getLayeredParameters_T();
		fdCreateData_T.fullRoute = true;
		fdCreateData_T.fdfrType = EServiceType.ELAN.toString();

		fdCreateData_T.additionalCreationInfo = new NameAndStringValue_T[0];
		return fdCreateData_T;
	}

	/**
	 * 设置LayeredParameters_T
	 * 
	 * @return
	 */
	private LayeredParameters_T getLayeredParameters_T() {
		LayeredParameters_T lay = new LayeredParameters_T();
		lay.layer = 9;
		lay.transmissionParams = super.getName("");
		return lay;
	}

	/**
	 * 设置 参数 此参数为out行，不可为null
	 * 
	 * @return
	 */
	private NamingAttributesList_THolder getNameintAttribute() {
		NamingAttributesList_THolder name = new NamingAttributesList_THolder();
		name.value = new NameAndStringValue_T[0][0];
		return name;
	}

	/**
	 * 设置MatrixFlowDomainFragmentList_THolder
	 * 
	 * @return
	 */
	private MatrixFlowDomainFragment_T[] getMaFdfr() {
		/*
		 * 测试时 ： 注意：：： eline maFdfr.value=new MatrixFlowDomainFragment_T[1]; etree, elan 时，由于有多条数据，所以 看情况new maFdfr.value=new MatrixFlowDomainFragment_T[2]; maFdfr.value=new MatrixFlowDomainFragment_T[3];
		 */
		MatrixFlowDomainFragment_T[] maFdfrs = new MatrixFlowDomainFragment_T[3];
		maFdfrs[0] = new MatrixFlowDomainFragment_T();
		maFdfrs[0].direction = ConnectionDirection_T.CD_BI;
		maFdfrs[0].transmissionParams = this.getLayeredParameters_T();
		maFdfrs[0].aEnd = this.getaAndZname(2, "/rack=1/shelf=1/slot=2/port=fe.1.3");
		maFdfrs[0].zEnd = this.getaAndZname(3, "/rack=1/shelf=1/slot=3/port=fe.1.3");//____/rack=1/shelf=1/slot=/port=
		maFdfrs[0].flexible = true;
		maFdfrs[0].active = true;
		maFdfrs[0].mfdfrType = "ELAN";
		maFdfrs[0].additionalInfo = this.getAdditionalInfo("3");

		 maFdfrs[1]=new MatrixFlowDomainFragment_T();
		 maFdfrs[1].direction= ConnectionDirection_T.CD_BI;
		 maFdfrs[1].transmissionParams=this.getLayeredParameters_T();
		 maFdfrs[1].aEnd=this.getaAndZname(1,"/rack=1/shelf=1/slot=1/port=fe.1.3");
		 maFdfrs[1].zEnd=this.getaAndZname(2,"/rack=1/shelf=1/slot=2/port=fe.1.3");
		 maFdfrs[1].flexible=true;
		 maFdfrs[1].active=true;
		 maFdfrs[1].mfdfrType="ELAN";
		 maFdfrs[1].additionalInfo=this.getAdditionalInfo("5");

		 maFdfrs[2]=new MatrixFlowDomainFragment_T();
		 maFdfrs[2].direction= ConnectionDirection_T.CD_BI;
		 maFdfrs[2].transmissionParams=this.getLayeredParameters_T();
		 maFdfrs[2].aEnd=this.getaAndZname(3,"/rack=1/shelf=1/slot=3/port=fe.1.3");
		 maFdfrs[2].zEnd=this.getaAndZname(1,"/rack=1/shelf=1/slot=1/port=fe.1.3");
		 maFdfrs[2].flexible=true;
		 maFdfrs[2].active=true;
		 maFdfrs[2].mfdfrType="ELAN";
		 maFdfrs[2].additionalInfo=this.getAdditionalInfo("4");
		// maFdfrs[2].additionalInfo[38] = new NameAndStringValue_T(this.SERVICEBYPWID, "2/45");// 激活

		return maFdfrs;
	}

	/**
	 * 为 A ，Z端 赋值（网元ID,AC id）
	 * 
	 * @param siteId
	 * @param acId
	 * @return
	 */
	private NameAndStringValue_T[][] getaAndZname(int siteId, String portName) {
		NameAndStringValue_T[][] name = new NameAndStringValue_T[1][3];
		name[0] = new NameAndStringValue_T[3];
		name[0][0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		name[0][1] = new NameAndStringValue_T("ManagedElement", siteId + "");
		name[0][2] = new NameAndStringValue_T("PTP", portName );
		return name;
	}

	/**
	 * 获取 以太网 对象
	 * 
	 * @param flowDomainFragment_THolder
	 * @return
	 */
	private FlowDomainFragment_THolder getFlowDomainFragment_THolder(FlowDomainFragment_THolder flowDomainFragment_THolder) {
		// FlowDomainFragment_THolder flowDomainFragment_THolder=new
		// FlowDomainFragment_THolder();
		flowDomainFragment_THolder.value = this.getFlowDomainFragment_T();
		return flowDomainFragment_THolder;
	}

	private FlowDomainFragment_T getFlowDomainFragment_T() {
		FlowDomainFragment_T flowDomainFragment_T = new FlowDomainFragment_T();
		flowDomainFragment_T.name = this.getName("");
		flowDomainFragment_T.userLabel = "eline";
		flowDomainFragment_T.nativeEMSName = " ";
		flowDomainFragment_T.owner = "DATX/1";
		flowDomainFragment_T.direction = ConnectionDirection_T.CD_BI;
		flowDomainFragment_T.transmissionParams = new LayeredParameters_T();
		flowDomainFragment_T.transmissionParams.layer = 6;
		flowDomainFragment_T.transmissionParams.transmissionParams = this.getName("");
		flowDomainFragment_T.zEnd = this.getTPData();
		flowDomainFragment_T.aEnd = this.getTPData();
		flowDomainFragment_T.networkAccessDomain = " ";
		flowDomainFragment_T.flexible = true;
		flowDomainFragment_T.administrativeState = AdministrativeState_T.AS_Locked;
		flowDomainFragment_T.fdfrState = SNCState_T.SNCS_ACTIVE;
		flowDomainFragment_T.fdfrType = "eline";
		flowDomainFragment_T.additionalInfo = this.getAdditionalInfo("0");
		return flowDomainFragment_T;
	}

	/**
	 * 获取以太网 修改对象
	 * 
	 * @return
	 */
	private FDFrModifyData_T getFDFrModifyData_T() {
		FDFrModifyData_T fdfrModifyDate = new FDFrModifyData_T();
		fdfrModifyDate.userLabel = "elan_kk_test";
		fdfrModifyDate.forceUniqueness = true;
		fdfrModifyDate.owner = "DATX/1";
		fdfrModifyDate.networkAccessDomain = " ";
		fdfrModifyDate.administrativeState = AdministrativeState_T.AS_Locked;
		fdfrModifyDate.transmissionParams = new LayeredParameters_T();
		fdfrModifyDate.transmissionParams.layer = 10;
		fdfrModifyDate.transmissionParams.transmissionParams = this.getName("");
		fdfrModifyDate.tpNamesToRemove = new NameAndStringValue_T[0][0];
		fdfrModifyDate.aEndTPNames = new NameAndStringValue_T[0][0];
		fdfrModifyDate.zEndTPNames = new NameAndStringValue_T[0][0];
		fdfrModifyDate.internalTPNames = new NameAndStringValue_T[0][0];
		fdfrModifyDate.additionalModificationInfo = this.getAdditionalInfo("");
		return fdfrModifyDate;
	}

	/**
	 * 以太网 获取附加信息
	 * 
	 * @return
	 */
	private NameAndStringValue_T[] getAdditionalInfo(String pwid) {
		NameAndStringValue_T[] additionalInfo = new NameAndStringValue_T[25];
		// ac的qos信息
		additionalInfo[0] = new NameAndStringValue_T(A2ZEIR, "0");// 路径类型
		additionalInfo[1] = new NameAndStringValue_T(Z2AEIR, "0");// 路径类型
		additionalInfo[2] = new NameAndStringValue_T(QOSTYPE, "L2");// 路径类型
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
		// AC 的vlan属性
		additionalInfo[14] = new NameAndStringValue_T(A2ZVLANID, "1");// vlanid=下话增加VLAN
		additionalInfo[15] = new NameAndStringValue_T(A2ZEXITRULE, "0"); // 出口规则，关联code表主键
		additionalInfo[16] = new NameAndStringValue_T(A2ZVLANPRI, "0");// vlanpri=下话增加VLAN
		additionalInfo[17] = new NameAndStringValue_T(A2ZPORTMODEL, "0");// 端口模式，关联code表主键
		// Z端标识Z2A 开头
		additionalInfo[18] = new NameAndStringValue_T(Z2AVLANID, "1");// vlanid=下话增加VLAN
		additionalInfo[19] = new NameAndStringValue_T(Z2AEXITRULE, "0"); // 出口规则，关联code表主键
		additionalInfo[20] = new NameAndStringValue_T(Z2AVLANPRI, "0");// vlanpri=下话增加VLAN // PRI
		additionalInfo[21] = new NameAndStringValue_T(Z2APORTMODEL, "0");// 端口模式，关联code表主键
		additionalInfo[22] = new NameAndStringValue_T(this.CREATEUSER, "admin");// 创建时间
		additionalInfo[23] = new NameAndStringValue_T(this.CLIENTNAME, "");// 客户名称
		additionalInfo[24] = new NameAndStringValue_T(this.SERVICEBYPWID, ELayerRate.PW.getValue() + "/"+pwid);// 以太网业务关联的PW 主键
		return additionalInfo;
	}
	
	public  NameAndStringValue_T[] getFdName(String identity) {
		NameAndStringValue_T[] nameAndStringValues = null;
		if("".equals(identity)){
			nameAndStringValues = new NameAndStringValue_T[2];
			nameAndStringValues[0] = new NameAndStringValue_T("EMS", "DATAX/1");
			nameAndStringValues[1] = new NameAndStringValue_T("FlowDomain", "1");
		}else{
			nameAndStringValues = new NameAndStringValue_T[3];
			nameAndStringValues[0] = new NameAndStringValue_T("EMS", "DATAX/1");
			nameAndStringValues[1] = new NameAndStringValue_T("FlowDomain", "1");
			nameAndStringValues[2] = new NameAndStringValue_T("FlowDomainFragment", identity);
		}
		
		return nameAndStringValues;
	}
}
