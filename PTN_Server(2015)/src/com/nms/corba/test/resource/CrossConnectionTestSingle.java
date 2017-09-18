package com.nms.corba.test.resource;

import globaldefs.ConnectionDirection_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import managedElementManager.ManagedElementMgr_I;
import managedElementManager.ManagedElementMgr_IHelper;
import subnetworkConnection.CCIterator_IHolder;
import subnetworkConnection.CrossConnectList_THolder;
import subnetworkConnection.CrossConnect_T;
import subnetworkConnection.CrossConnect_THolder;
import subnetworkConnection.SNCType_T;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.corba.test.snc.TestBase;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

/**
 * 单网元tunnel、pw、ces测试类
 * 
 * @author kk
 * 
 */
public class CrossConnectionTestSingle extends TestBase {
	public static void main(String[] args) {
		CrossConnectionTestSingle CrossConnectionTestSingle = new CrossConnectionTestSingle();
//		CrossConnectionTestSingle.getAllCrossConnections();
		 CrossConnectionTestSingle.getCrossConnections();
//		 CrossConnectionTestSingle.deactivateCrossConnections();
//		 CrossConnectionTestSingle.activateCrossConnections();
//		 CrossConnectionTestSingle.deleteCrossConnections();
//		CrossConnectionTestSingle.createTunnelTest();
//		CrossConnectionTestSingle.createPWTest();
//		CrossConnectionTestSingle.createCesTest();
	}

	public CrossConnectionTestSingle() {
		super();
	}

	/**
	 * 测试查询所有交叉连接
	 */
	public void getAllCrossConnections() {
		try {
			short[] connectionRateList = new short[] { (short) ELayerRate.CES.getValue() };
			CrossConnectList_THolder ccList = new CrossConnectList_THolder();
			CCIterator_IHolder ccIt = new CCIterator_IHolder();

			this.getMEInterface().getAllCrossConnections(getMEName(), connectionRateList, 10, ccList, ccIt);

			System.out.println(ccList.value.length);
		} catch (ProcessingFailureException e) {
			System.out.println(e.errorReason);
		}
	}

	/**
	 * 测试查询一个交叉连接
	 */
	public void getCrossConnections() {
		try {
			CrossConnect_THolder crossConnect_THolder = new CrossConnect_THolder();

			this.getMEInterface().getCrossConnection(this.getCCName(), crossConnect_THolder);

			System.out.println(crossConnect_THolder.value == null ? "" : crossConnect_THolder.value.active);

		} catch (ProcessingFailureException e) {
			System.out.println(e.errorReason);
		}
	}

	/**
	 * 测试去激活交叉连接
	 */
	public void deactivateCrossConnections() {
		try {
			this.getMEInterface().deactivateCrossConnections(this.getCCName());
		} catch (ProcessingFailureException e) {
			System.out.println(e.errorReason);
		}
	}

	/**
	 * 测试激活交叉连接
	 */
	public void activateCrossConnections() {
		try {
			this.getMEInterface().activateCrossConnections(this.getCCName());
		} catch (ProcessingFailureException e) {
			System.out.println(e.errorReason);
		}
	}

	/**
	 * 测试删除交叉连接
	 */
	public void deleteCrossConnections() {
		try {
			this.getMEInterface().deleteCrossConnections(this.getCCName());
		} catch (ProcessingFailureException e) {
			System.out.println(e.errorReason);
		}
	}

	/**
	 * 测试创建tunnel
	 */
	public void createTunnelTest() {

		try {
			CrossConnect_T crossConnect_T = this.convertCrossConnect("Atunnel");
			CrossConnect_THolder crossConnect_THolder = new CrossConnect_THolder();

			this.getMEInterface().createCrossConnections(this.getMEName(), (short) ELayerRate.TUNNEL.getValue(), crossConnect_T, crossConnect_THolder);
			
			System.out.println("测试完成");
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 测试创建pw
	 */
	public void createPWTest() {

		try {
			CrossConnect_T crossConnect_T = this.convertCrossConnect("pw");
			CrossConnect_THolder crossConnect_THolder = new CrossConnect_THolder();

			this.getMEInterface().createCrossConnections(this.getMEName(), (short) ELayerRate.PW.getValue(), crossConnect_T, crossConnect_THolder);
			
			System.out.println("测试完成");
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private CrossConnect_T convertCrossConnect(String type) {
		int index=0;
		NameAndStringValue_T[] nameAndStringValueArray= null;
		CrossConnect_T crossConnect = null;
		try {

			crossConnect = new CrossConnect_T();
			crossConnect.active = true;
			crossConnect.direction = ConnectionDirection_T.CD_BI;
			crossConnect.ccType = SNCType_T.ST_SIMPLE;
			

			if("pw".equals(type)){
				
				crossConnect.aEndNameList = new NameAndStringValue_T[1][];
				crossConnect.aEndNameList[0]=this.getSubnetworkConnectionName();
				crossConnect.zEndNameList = new NameAndStringValue_T[0][];
				
				nameAndStringValueArray = new NameAndStringValue_T[17];
				
				nameAndStringValueArray[0] = new NameAndStringValue_T(super.SRCINLABEL, "17");
				nameAndStringValueArray[1] = new NameAndStringValue_T(super.SRCOUTLABEL, "18");
				nameAndStringValueArray[2] = new NameAndStringValue_T(super.PWBYTUNNELID, ELayerRate.TUNNEL.getValue() + "/1");
				nameAndStringValueArray[3] = new NameAndStringValue_T(super.PWTYPE, "PDH");
				nameAndStringValueArray[4] = new NameAndStringValue_T("userLabel", "kk_pw_test");
				index=5;
			}else if("Ztunnel".equals(type)){
				crossConnect.zEndNameList = new NameAndStringValue_T[1][];
				crossConnect.zEndNameList[0] = this.getPTPName("10");
				crossConnect.aEndNameList = new NameAndStringValue_T[0][];
				
				nameAndStringValueArray = new NameAndStringValue_T[16];
				nameAndStringValueArray[0] = new NameAndStringValue_T(super.DESTINLABEL, "16");
				nameAndStringValueArray[1] = new NameAndStringValue_T(super.DESTOUTLABEL, "17");
				nameAndStringValueArray[2] = new NameAndStringValue_T("userLabel", "kk_tunnel_test");
				nameAndStringValueArray[3] = new NameAndStringValue_T("layer", ELayerRate.TUNNEL.getValue() + "");
				
				index=4;
			}else if("Atunnel".equals(type)){
				crossConnect.aEndNameList = new NameAndStringValue_T[1][];
				crossConnect.aEndNameList[0] = this.getPTPName("10");
				crossConnect.zEndNameList = new NameAndStringValue_T[0][];
				
				nameAndStringValueArray = new NameAndStringValue_T[16];
				nameAndStringValueArray[0] = new NameAndStringValue_T(super.SRCINLABEL, "16");
				nameAndStringValueArray[1] = new NameAndStringValue_T(super.SRCOUTLABEL, "17");
				nameAndStringValueArray[2] = new NameAndStringValue_T("userLabel", "kk_tunnel_test");
				nameAndStringValueArray[3] = new NameAndStringValue_T("layer", ELayerRate.TUNNEL.getValue() + "");
				
				index=4;
			}else if("XCtunnel".equals(type)){
				crossConnect.aEndNameList = new NameAndStringValue_T[1][];
				crossConnect.aEndNameList[0] = this.getPTPName("10");
				crossConnect.zEndNameList = new NameAndStringValue_T[1][];
				crossConnect.zEndNameList[0] = this.getPTPName("11");
				
				nameAndStringValueArray = new NameAndStringValue_T[18];
				nameAndStringValueArray[0] = new NameAndStringValue_T(super.SRCINLABEL, "16");
				nameAndStringValueArray[1] = new NameAndStringValue_T(super.SRCOUTLABEL, "17");
				nameAndStringValueArray[2] = new NameAndStringValue_T(super.DESTINLABEL, "18");
				nameAndStringValueArray[3] = new NameAndStringValue_T(super.DESTOUTLABEL, "19");
				nameAndStringValueArray[4] = new NameAndStringValue_T("userLabel", "kk_tunnel_test");
				nameAndStringValueArray[5] = new NameAndStringValue_T("layer", ELayerRate.TUNNEL.getValue() + "");
				
				index=6;
			}
			this.convertAdditionalInfo_qos(nameAndStringValueArray, index);
			crossConnect.additionalInfo=nameAndStringValueArray;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return crossConnect;
	}
	

	/**
	 * 把qos信息转换为附加信息
	 * 
	 * @param qosInfoList
	 *            qos集合
	 * @param nameAndStringValueArray
	 *            已有的附加信息
	 * @param index
	 *            附件信息目前的索引
	 * @param type
	 *            a\z
	 */
	private void convertAdditionalInfo_qos(NameAndStringValue_T[] nameAndStringValueArray, int index) throws ProcessingFailureException {
		nameAndStringValueArray[index++] = new NameAndStringValue_T(super.QOSTYPE, "LLSP");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(super.QOSCOS, "CS6");

		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.INGRESSCIR, "2944");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.INGRESSCBS, "-1");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.INGRESSEIR, "0");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.INGRESSEBS, "-1");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.INGRESSPIR, "0");

		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.EGRESSCIR, "2944");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.EGRESSCBS, "-1");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.EGRESSEIR, "0");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.EGRESSEBS, "-1");
		nameAndStringValueArray[index++] = new NameAndStringValue_T(this.EGRESSPIR, "0");
	}

	/**
	 * 获取ManagedElementMgr_I接口
	 * 
	 * @return
	 */
	private ManagedElementMgr_I getMEInterface() {
		ManagedElementMgr_I managedElementMgr_I = null;
		try {
			Common_IHolder Common_IHolder = new Common_IHolder();
			super.getCorbaConnect().emsSession.getManager("ManagedElement", Common_IHolder);
			managedElementMgr_I = ManagedElementMgr_IHelper.narrow(Common_IHolder.value);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return managedElementMgr_I;
	}
	
	/**
	 * 设置  新建ces信息
	 * @return
	 */
	public void createCesTest(){
		CrossConnect_T cross=new CrossConnect_T();
		cross.active=true;
		cross.direction=ConnectionDirection_T.CD_BI;//双向
		cross.ccType=SNCType_T.ST_ADD_DROP_A;
		cross.aEndNameList=new NameAndStringValue_T[1][];
		//根据  给定类型，确定A,或者Z端基层机构	PDH,PDHSDH为3层结构： SDH,SDHPDH为4层就够
		cross.aEndNameList[0]=new NameAndStringValue_T[3];
		cross.aEndNameList[0][0]=new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		cross.aEndNameList[0][1]=new NameAndStringValue_T("ManagedElement","2");
		cross.aEndNameList[0][2]=new NameAndStringValue_T("PTP","95");
		//cross.aEndNameList[0][3]=new NameAndStringValue_T("CTP","1");
		cross.zEndNameList=new NameAndStringValue_T[0][];
		cross.additionalInfo=new NameAndStringValue_T[4];
		cross.additionalInfo[0]=new NameAndStringValue_T("userLabel","kk_ces_test");//名称
		cross.additionalInfo[1]=new NameAndStringValue_T(this.CESTYPE,"PDH");//类型
		cross.additionalInfo[2]=new NameAndStringValue_T(this.CESBYPWID,ELayerRate.PW.getValue()+"/1");//创建ces关联的pw
		cross.additionalInfo[3]=new NameAndStringValue_T("FDFRRATE",ELayerRate.CES.getValue()+"");//层速率

		CrossConnect_THolder crossConnect_THolder = new CrossConnect_THolder();

		try {
			this.getMEInterface().createCrossConnections(this.getMEName(), (short) ELayerRate.CES.getValue(), cross, crossConnect_THolder);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	

	private NameAndStringValue_T[] getMEName() {
		NameAndStringValue_T[] name = new NameAndStringValue_T[2];
		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
		name[1] = new NameAndStringValue_T("ManagedElement", "2");
		return name;
	}

	private NameAndStringValue_T[] getCCName() {
		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
		name[1] = new NameAndStringValue_T("ManagedElement", "1");
		name[2] = new NameAndStringValue_T("CrossConnection", ELayerRate.CES.getValue() + "/6");
		return name;
	}

	private NameAndStringValue_T[] getPTPName(String ptpName) {
		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
		name[1] = new NameAndStringValue_T("ManagedElement", "2");
		name[2] = new NameAndStringValue_T("PTP", ptpName);
		return name;
	}
	
	private NameAndStringValue_T[] getSubnetworkConnectionName() {
		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
		name[1] = new NameAndStringValue_T("ManagedElement", "2");
		name[2] = new NameAndStringValue_T("SubnetworkConnection", ELayerRate.TUNNEL.getValue() + "/6");
		return name;
	}

	private final String INGRESSCIR = "IngressCIR";
	private final String INGRESSCBS = "IngressCBS";
	private final String INGRESSEIR = "IngressEIR";
	private final String INGRESSEBS = "IngressEBS";
	private final String INGRESSPIR = "IngressPIR";
	private final String EGRESSCIR = "EgressCIR";
	private final String EGRESSCBS = "EgressCBS";
	private final String EGRESSEIR = "EgressEIR";
	private final String EGRESSEBS = "EgressEBS";
	private final String EGRESSPIR = "EgressPIR";
}
