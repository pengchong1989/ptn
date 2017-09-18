package com.nms.corba.test.resource;

import flowDomain.FlowDomainMgr_I;
import flowDomain.FlowDomainMgr_IHelper;
import flowDomainFragment.MatrixFlowDomainFragmentList_THolder;
import flowDomainFragment.MatrixFlowDomainFragment_T;
import flowDomainFragment.MatrixFlowDomainFragment_THolder;
import globaldefs.ConnectionDirection_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import transmissionParameters.LayeredParameters_T;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.corba.test.snc.TestBase;
import com.nms.db.enums.EServiceType;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

/**
 * 单网元以太网业务测试用例
 * @author kk
 *
 */
public class SingleNetworkTest extends TestBase{

	public static void main(String[] args){
		SingleNetworkTest singleNetworkTest=new SingleNetworkTest();
		singleNetworkTest.getMeMFDFrsTest();
//		singleNetworkTest.getMFDFrTest();
//		singleNetworkTest.deactivateMFDFr();
//		singleNetworkTest.activateMFDFr();
//		singleNetworkTest.deleteMFDFrTest();
//		singleNetworkTest.createMFDFr();
//		singleNetworkTest.setUserLabel();
//		singleNetworkTest.setAdditionalInfo();
	}
	
	public void setAdditionalInfo(){
		try {
			NVSList_THolder nvsList_THolder=new NVSList_THolder(this.getAdditionalInfo_update());
			this.getFDFrInterface().setAdditionalInfo(this.getMFDFRName("1"), nvsList_THolder);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public void setUserLabel(){
		try {
			this.getFDFrInterface().setUserLabel(this.getMFDFRName("2"), "kk_test_ELINE_update",true);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	
	public void getMeMFDFrsTest(){
		try {
			MatrixFlowDomainFragmentList_THolder mfdfrList=new MatrixFlowDomainFragmentList_THolder();
			this.getFDFrInterface().getMeMFDFrs(this.getMEName(), mfdfrList);
			
			System.out.println(mfdfrList.value.length);
		} catch (ProcessingFailureException e) {
			System.out.println(e.errorReason);
		}
	}
	
	public void getMFDFrTest(){
		try {
			MatrixFlowDomainFragment_THolder mfdfr=new MatrixFlowDomainFragment_THolder();
			this.getFDFrInterface().getMFDFr(this.getMFDFRName("5"), mfdfr);
			
			System.out.println(mfdfr.value.mfdfrType);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public void deactivateMFDFr(){
		try {
			this.getFDFrInterface().deactivateMFDFr(this.getmfdfrNameList());
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public void activateMFDFr(){
		try {
			this.getFDFrInterface().activateMFDFr(this.getmfdfrNameList());
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public void deleteMFDFrTest(){
		try {
			this.getFDFrInterface().deleteMFDFr(this.getmfdfrNameList());
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public void createMFDFr(){
		try {
			MatrixFlowDomainFragment_THolder theMfdfr=new MatrixFlowDomainFragment_THolder();
			
			this.getFDFrInterface().createMFDFr(this.getCreateData(), theMfdfr);
		} catch (ProcessingFailureException e) {
			System.out.println(e.errorReason);
		}
	}
	
	private MatrixFlowDomainFragment_T getCreateData(){
		
		MatrixFlowDomainFragment_T mfdfr=new MatrixFlowDomainFragment_T();
		mfdfr.active=true;
		mfdfr.direction = ConnectionDirection_T.CD_BI;
		mfdfr.mfdfrType = EServiceType.ELINE.toString();
		mfdfr.flexible = true;
		mfdfr.transmissionParams = new LayeredParameters_T((short)ELayerRate.ETHSERVICE.getValue(), new NameAndStringValue_T[0]);
		mfdfr.aEnd = new NameAndStringValue_T[][] { this.getPTPName("fx.1.4") };
		mfdfr.zEnd = new NameAndStringValue_T[][] { this.getPWName("6")};
		
		mfdfr.additionalInfo = this.convertAdditionalInfo(mfdfr.mfdfrType);
		return mfdfr;
	}
	
	private NameAndStringValue_T[] convertAdditionalInfo(String type){
		NameAndStringValue_T[] nameAndStringValueArray=new NameAndStringValue_T[22];
		
		nameAndStringValueArray[0] = new NameAndStringValue_T(this.USERLABEL, "11kk_test_"+type); // 友好名称
		nameAndStringValueArray[1] = new NameAndStringValue_T(this.NATIVEEMSNAME, "kk_test_"+type); // 本地名称
		nameAndStringValueArray[2] = new NameAndStringValue_T(this.MATRIXFLOWDOMAINFRAGMENT, ""); // 业务标识
		nameAndStringValueArray[3] = new NameAndStringValue_T(this.VLANID, "1");// vlanid=下话增加VLAN ID
		nameAndStringValueArray[4] = new NameAndStringValue_T(this.EXITRULE, "0"); // 出口规则，关联code表主键 = 下话TAG行为
		nameAndStringValueArray[5] = new NameAndStringValue_T(this.VLANPRI,"0");// vlanpri=下话增加VLAN PRI
		nameAndStringValueArray[6] = new NameAndStringValue_T(this.PORTMODEL, "0");// 端口模式，关联code表主键
		nameAndStringValueArray[7] = new NameAndStringValue_T(this.HORIZONTALDIVISION, "false");// 水平分割，false=关 true=开
		nameAndStringValueArray[8] = new NameAndStringValue_T(this.MACADDRESSLEARN, "false");// mac地址使能，false=不使能 true=使能
		nameAndStringValueArray[9] = new NameAndStringValue_T(this.ISBROADCAST, "false");
		nameAndStringValueArray[10] = new NameAndStringValue_T(this.BROADCAST, "0");
		nameAndStringValueArray[11] = new NameAndStringValue_T(this.ISUNICAST, "false");
		nameAndStringValueArray[12] = new NameAndStringValue_T(this.UNICAST, "0");
		nameAndStringValueArray[13] = new NameAndStringValue_T(this.ISMULTICAST, "false");
		nameAndStringValueArray[14] = new NameAndStringValue_T(this.MULTICAST, "0");
		nameAndStringValueArray[15] = new NameAndStringValue_T(super.QOSTYPE, "L2");
		nameAndStringValueArray[16] = new NameAndStringValue_T(super.QOSCOS, "AF1");
		nameAndStringValueArray[17] = new NameAndStringValue_T(this.CIR, "0");
		nameAndStringValueArray[18] = new NameAndStringValue_T(this.CBS, "-1");
		nameAndStringValueArray[19] = new NameAndStringValue_T(this.EIR, "0");
		nameAndStringValueArray[20] = new NameAndStringValue_T(this.EBS, "-1");
		nameAndStringValueArray[21] = new NameAndStringValue_T(this.PIR, "0");
		
		return nameAndStringValueArray;
	}
	
	
	private NameAndStringValue_T[][] getmfdfrNameList(){
		NameAndStringValue_T[][] mfdfrNameList=new NameAndStringValue_T[][]{this.getMFDFRName("4")};
		return mfdfrNameList;
	}
	
	/**
	 * 获取ManagedElementMgr_I接口
	 * 
	 * @return
	 */
	private FlowDomainMgr_I getFDFrInterface() {
		FlowDomainMgr_I flowDomainMgr_I = null;
		try {
			Common_IHolder Common_IHolder = new Common_IHolder();
			super.getCorbaConnect().emsSession.getManager("FlowDomain", Common_IHolder);
			flowDomainMgr_I = FlowDomainMgr_IHelper.narrow(Common_IHolder.value);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return flowDomainMgr_I;
	}
	
	private NameAndStringValue_T[] getMEName() {
		NameAndStringValue_T[] name = new NameAndStringValue_T[2];
		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
		name[1] = new NameAndStringValue_T("ManagedElement", "2");
		return name;
	}
	
	private NameAndStringValue_T[] getMFDFRName(String name) {
		NameAndStringValue_T[] nameAndStringValueArray = new NameAndStringValue_T[3];
		nameAndStringValueArray[0] = new NameAndStringValue_T("EMS", "DATAX/1");
		nameAndStringValueArray[1] = new NameAndStringValue_T("ManagedElement", "1");
		nameAndStringValueArray[2] = new NameAndStringValue_T("MatrixFlowDomainFragment", name);
		return nameAndStringValueArray;
	}
	
	private NameAndStringValue_T[] getPTPName(String ptpName) {
		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
		name[1] = new NameAndStringValue_T("ManagedElement", "2");
		name[2] = new NameAndStringValue_T("PTP", "/rack=1/shelf=1/slot=1/port="+ptpName);
		return name;
	}
	
	private NameAndStringValue_T[] getPWName(String pwId) {
		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
		name[0] = new NameAndStringValue_T("EMS", "DATAX/1");
		name[1] = new NameAndStringValue_T("ManagedElement", "2");
		name[2] = new NameAndStringValue_T("CrossConnection", ELayerRate.PW.getValue() + "/"+pwId);
		return name;
	}
	
	private NameAndStringValue_T[] getAdditionalInfo_update(){
		NameAndStringValue_T[] nameAndStringValueArray=new  NameAndStringValue_T[1];
		nameAndStringValueArray[0]=new NameAndStringValue_T(this.ISBROADCAST,"false");
		return nameAndStringValueArray;
	}
	
	
	private final String VLANID = "VlanId";
	private final String EXITRULE = "ExitRule";
	private final String VLANPRI = "Vlanpri";
	private final String PORTMODEL = "PortModel"; // 端口模式
	private final String HORIZONTALDIVISION = "HorizontalDivision"; // 水平分割
	private final String MACADDRESSLEARN = "MacAddressLearn"; // MAC地址学习
	private final String ISBROADCAST = "IsBroadcast"; // 广播状态
	private final String BROADCAST = "Broadcast";// 广播门限
	private final String ISUNICAST = "IsUnicast"; // 单 播状态
	private final String UNICAST = "Unicast";// 单 播门限
	private final String ISMULTICAST = "IsMulticast"; // 组播状态
	private final String MULTICAST = "Multicast";// 组播门限
	private final String CIR = "Cir";
	private final String CBS = "Cbs";
	private final String EIR = "Eir";
	private final String EBS = "Ebs";
	private final String PIR = "Pir";
	private final String USERLABEL = "UserLabel";
	private final String MATRIXFLOWDOMAINFRAGMENT="MatrixFlowDomainFragment";	//单网元以太网业务名称
	private final String NATIVEEMSNAME = "NativeEMSName";	//本地名称
}
