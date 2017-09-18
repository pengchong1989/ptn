package com.nms.corba.test.CorbaTest_sy;

import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import org.omg.CORBA.StringHolder;

import protection.ProtectionCommand_T;
import protection.ProtectionSchemeState_T;
import protection.ReversionMode_T;
import trailNtwProtection.TNPSwitchData_THolder;
import trailNtwProtection.TrailNtwProtCreateData_T;
import trailNtwProtection.TrailNtwProtMgr_I;
import trailNtwProtection.TrailNtwProtMgr_IHelper;
import trailNtwProtection.TrailNtwProtModifyData_T;
import trailNtwProtection.TrailNtwProtectionIterator_IHolder;
import trailNtwProtection.TrailNtwProtectionList_THolder;
import trailNtwProtection.TrailNtwProtection_T;
import trailNtwProtection.TrailNtwProtection_THolder;

import com.nms.corba.ninterface.enums.ESwitchDirection;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

/**
 * 测试TNP
 * @author sy
 *
 */
public class Test_TrailNtwprotmgr_Impl extends TestBase {

	TrailNtwProtMgr_I tnp=null;
	public Test_TrailNtwprotmgr_Impl(){
		super();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test_TrailNtwprotmgr_Impl tc=new Test_TrailNtwprotmgr_Impl();
		tc.getTrailNtwProtection();
		//测试  删除，修改tnp 完成！！！
		System.out.println("测试完成！！");

	}
	/**
	 * 删除tnp
	 */
	public void deleteTrailNtwProtection(){
		this.getManager();
		NVSList_THolder nvsList=new NVSList_THolder();
		nvsList.value=this.getName();
		StringHolder stringHolder=new StringHolder();
		try {
			tnp.deleteTrailNtwProtection(getName(), stringHolder);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 测试创建tnp
	 */
	public void createTrailNtwProtection(){
		this.getManager();
		try {
			TrailNtwProtCreateData_T createTnp=new TrailNtwProtCreateData_T();
			this.getTnpData(createTnp);
			createTnp.rate=(short) ELayerRate.TNP.getValue();
			TrailNtwProtection_THolder tnp_THolder=new TrailNtwProtection_THolder();
			StringHolder stringHolder=new StringHolder();
			//ProtectionGroupType_T
			tnp.createTrailNtwProtection(createTnp, tnp_THolder, stringHolder);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 设置 创建tnp的对象
	 * @param createTnp
	 * @return
	 */
	private TrailNtwProtCreateData_T getTnpData(TrailNtwProtCreateData_T createTnp){
		createTnp.rate=(short) ELayerRate.TNP.getValue();
		
		createTnp.protectionGroupAName=new NameAndStringValue_T[0];
		createTnp.protectionGroupZName=new NameAndStringValue_T[0];
		createTnp.protectionTrail=new NameAndStringValue_T[0][];
		createTnp.workerTrailList=new NameAndStringValue_T[0][][];
		
		createTnp.modifiableAttributes=new TrailNtwProtModifyData_T[2];
		createTnp.modifiableAttributes[0]=new TrailNtwProtModifyData_T();
		createTnp.modifiableAttributes[0].nativeEMSName="tunnel_test1";
		
		createTnp.modifiableAttributes[0].pgATPList=new NameAndStringValue_T[1][];
		createTnp.modifiableAttributes[0].pgATPList[0]=new NameAndStringValue_T[2];
		createTnp.modifiableAttributes[0].pgATPList[0][0]=new NameAndStringValue_T("ManagedElement","5");
		createTnp.modifiableAttributes[0].pgATPList[0][1]=new NameAndStringValue_T("PTP","163");
		
		createTnp.modifiableAttributes[0].pgZTPList=new NameAndStringValue_T[1][];
		createTnp.modifiableAttributes[0].pgZTPList[0]=new NameAndStringValue_T[2];
		createTnp.modifiableAttributes[0].pgZTPList[0][0]=new NameAndStringValue_T("ManagedElement","4");
		createTnp.modifiableAttributes[0].pgZTPList[0][1]=new NameAndStringValue_T("PTP","125");
		
		createTnp.modifiableAttributes[0].additionalInfo=new NameAndStringValue_T[21];
		createTnp.modifiableAttributes[0].additionalInfo[14]=new NameAndStringValue_T(ISACTIVE,"1");
		createTnp.modifiableAttributes[0].additionalInfo[15]=new NameAndStringValue_T(CREATETIME,"1949-4-4 10:10:10");
		createTnp.modifiableAttributes[0].additionalInfo[16]=new NameAndStringValue_T(CREATEUSER,"test_test");
		
		createTnp.modifiableAttributes[0].additionalInfo[17]=new NameAndStringValue_T(SRCINLABEL,"16");
		createTnp.modifiableAttributes[0].additionalInfo[18]=new NameAndStringValue_T(SRCOUTLABEL,"17");
		createTnp.modifiableAttributes[0].additionalInfo[19]=new NameAndStringValue_T(DESTINLABEL,"17");
		createTnp.modifiableAttributes[0].additionalInfo[20]=new NameAndStringValue_T(DESTOUTLABEL,"16");
		
		createTnp.modifiableAttributes[0].additionalInfo[0] = new NameAndStringValue_T(A2ZEIR, "0");// 路径类型
		createTnp.modifiableAttributes[0].additionalInfo[1] = new NameAndStringValue_T(Z2AEIR, "0");// 路径类型
		createTnp.modifiableAttributes[0].additionalInfo[2] = new NameAndStringValue_T(QOSTYPE, "lLSP");// 路径类型
		// qos描述信息
		createTnp.modifiableAttributes[0].additionalInfo[3] = new NameAndStringValue_T(A2ZCIR, "0");// 路径类型
		createTnp.modifiableAttributes[0].additionalInfo[4] = new NameAndStringValue_T(Z2ACIR, "0");// 路径类型
		createTnp.modifiableAttributes[0].additionalInfo[5] = new NameAndStringValue_T(A2ZPIR, "0");// 路径类型
		createTnp.modifiableAttributes[0].additionalInfo[6] = new NameAndStringValue_T(Z2APIR, "0");// 路径类型
		createTnp.modifiableAttributes[0].additionalInfo[7] = new NameAndStringValue_T(A2ZCBS, "-1");// 路径类型
		createTnp.modifiableAttributes[0].additionalInfo[8] = new NameAndStringValue_T(Z2ACBS, "-1");// 路径类型
		createTnp.modifiableAttributes[0].additionalInfo[9] = new NameAndStringValue_T(A2ZPBS, "0");// 路径类型
		createTnp.modifiableAttributes[0].additionalInfo[10] = new NameAndStringValue_T(Z2APBS, "0");// 路径类型
		createTnp.modifiableAttributes[0].additionalInfo[11] = new NameAndStringValue_T(A2ZEBS, "-1");// 路径类型
		createTnp.modifiableAttributes[0].additionalInfo[12] = new NameAndStringValue_T(Z2AEBS, "-1");// 路径类型
		createTnp.modifiableAttributes[0].additionalInfo[13] = new NameAndStringValue_T(QOSCOS, "EF");// 路径类型
		
		//createTnp.modifiableAttributes[0].protectionGroupType=ProtectionGroupType_T.PGT_2_FIBER_BLSR;
		createTnp.modifiableAttributes[0].reversionMode=ReversionMode_T.RM_NON_REVERTIVE;
		createTnp.modifiableAttributes[0].tnpParameters=new NameAndStringValue_T[0];
		
		
		createTnp.modifiableAttributes[1]=new TrailNtwProtModifyData_T();
		createTnp.modifiableAttributes[1].pgATPList=new NameAndStringValue_T[1][];
		createTnp.modifiableAttributes[1].pgATPList[0]=new NameAndStringValue_T[2];
		createTnp.modifiableAttributes[1].pgATPList[0][0]=new NameAndStringValue_T("ManagedElement","5");
		createTnp.modifiableAttributes[1].pgATPList[0][1]=new NameAndStringValue_T("PTP","163");
		
		createTnp.modifiableAttributes[1].pgZTPList=new NameAndStringValue_T[1][];
		createTnp.modifiableAttributes[1].pgZTPList[0]=new NameAndStringValue_T[2];
		createTnp.modifiableAttributes[1].pgZTPList[0][0]=new NameAndStringValue_T("ManagedElement","4");
		createTnp.modifiableAttributes[1].pgZTPList[0][1]=new NameAndStringValue_T("PTP","125");
		
		createTnp.modifiableAttributes[1].additionalInfo=new NameAndStringValue_T[20];
		createTnp.modifiableAttributes[1].additionalInfo[14]=new NameAndStringValue_T(SRCINLABEL,"16");
		createTnp.modifiableAttributes[1].additionalInfo[15]=new NameAndStringValue_T(SRCOUTLABEL,"17");
		createTnp.modifiableAttributes[1].additionalInfo[16]=new NameAndStringValue_T(DESTINLABEL,"17");
		createTnp.modifiableAttributes[1].additionalInfo[17]=new NameAndStringValue_T(DESTOUTLABEL,"16");
		
		createTnp.modifiableAttributes[1].additionalInfo[0] = new NameAndStringValue_T(A2ZEIR, "0");// 路径类型
		createTnp.modifiableAttributes[1].additionalInfo[1] = new NameAndStringValue_T(Z2AEIR, "0");// 路径类型
		createTnp.modifiableAttributes[1].additionalInfo[2] = new NameAndStringValue_T(QOSTYPE, "lLSP");// 路径类型
		// qos描述信息
		createTnp.modifiableAttributes[1].additionalInfo[3] = new NameAndStringValue_T(A2ZCIR, "0");// 路径类型
		createTnp.modifiableAttributes[1].additionalInfo[4] = new NameAndStringValue_T(Z2ACIR, "0");// 路径类型
		createTnp.modifiableAttributes[1].additionalInfo[5] = new NameAndStringValue_T(A2ZPIR, "0");// 路径类型
		createTnp.modifiableAttributes[1].additionalInfo[6] = new NameAndStringValue_T(Z2APIR, "0");// 路径类型
		createTnp.modifiableAttributes[1].additionalInfo[7] = new NameAndStringValue_T(A2ZCBS, "-1");// 路径类型
		createTnp.modifiableAttributes[1].additionalInfo[8] = new NameAndStringValue_T(Z2ACBS, "-1");// 路径类型
		createTnp.modifiableAttributes[1].additionalInfo[9] = new NameAndStringValue_T(A2ZPBS, "0");// 路径类型
		createTnp.modifiableAttributes[1].additionalInfo[10] = new NameAndStringValue_T(Z2APBS, "0");// 路径类型
		createTnp.modifiableAttributes[1].additionalInfo[11] = new NameAndStringValue_T(A2ZEBS, "-1");// 路径类型
		createTnp.modifiableAttributes[1].additionalInfo[12] = new NameAndStringValue_T(Z2AEBS, "-1");// 路径类型
		createTnp.modifiableAttributes[1].additionalInfo[13] = new NameAndStringValue_T(QOSCOS, "EF");// 路径类型
		
		createTnp.modifiableAttributes[1].additionalInfo[18]=new NameAndStringValue_T(CREATETIME,"1949-5-5 10:10:10");
		createTnp.modifiableAttributes[1].additionalInfo[19]=new NameAndStringValue_T(CREATEUSER,"test_test_11");
		
		
		//createTnp.modifiableAttributes[1].protectionGroupType=ProtectionGroupType_T.PGT_2_FIBER_BLSR;
		createTnp.modifiableAttributes[1].reversionMode=ReversionMode_T.RM_NON_REVERTIVE;
		createTnp.modifiableAttributes[1].tnpParameters=new NameAndStringValue_T[0];
		
		return createTnp;
	}
	
	/**
	 * 查询所有路径网络保护（TNP）
	 */
	public void getAllTrailNtwProtections(){
		this.getManager();
		try {
	
			TrailNtwProtectionList_THolder tnp_THolder=new TrailNtwProtectionList_THolder();

			TrailNtwProtectionIterator_IHolder tnpIterator_IHolder=new TrailNtwProtectionIterator_IHolder();
			tnp.getAllTrailNtwProtections(getName(), 10, tnp_THolder, tnpIterator_IHolder);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 查询指定路径网络保护（TNP）
	 */
	public void getTrailNtwProtection(){
		this.getManager();
		try {
			TrailNtwProtection_THolder tnp_THolder=new TrailNtwProtection_THolder();
			tnp.getTrailNtwProtection(getName(), tnp_THolder);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 修改TNP
	 */
	public void modifyTrailNtwprot(){
		this.getManager();
		TrailNtwProtModifyData_T tnpData=new TrailNtwProtModifyData_T();
		tnpData.nativeEMSName="7777";
		tnpData.reversionMode=ReversionMode_T.RM_NON_REVERTIVE;
		tnpData.tnpParameters=this.getName();
		tnpData.additionalInfo=new NameAndStringValue_T[0];
		tnpData.forceUniqueness=false;
		tnpData.pgATPList=new NameAndStringValue_T[0][];
		tnpData.pgZTPList=new NameAndStringValue_T[0][];
		//tnpData.protectionGroupType=ProtectionGroupType_T.PGT_2_FIBER_BLSR;
		tnpData.owner="DATAX/1";
		
		TrailNtwProtection_THolder tnp_THolder=new TrailNtwProtection_THolder();
		tnp_THolder.value=new TrailNtwProtection_T();
		tnp_THolder.value.additionalInfo=new NameAndStringValue_T[0];
		tnp_THolder.value.name=new NameAndStringValue_T[0];
		tnp_THolder.value.pgATPList=new NameAndStringValue_T[0][];
		tnp_THolder.value.pgZTPList=new NameAndStringValue_T[0][];
		tnp_THolder.value.protectionGroupAName=new NameAndStringValue_T[0];
	//	tnp_THolder.value.protectionGroupType=ProtectionGroupType_T.PGT_2_FIBER_BLSR;
		tnp_THolder.value.tnpParameters=new NameAndStringValue_T[0];
		tnp_THolder.value.workerTrailList=new NameAndStringValue_T[0][][];
		tnp_THolder.value.protectionSchemeState=ProtectionSchemeState_T.PSS_AUTOMATIC;
		tnp_THolder.value.reversionMode=ReversionMode_T.RM_NON_REVERTIVE;
		tnp_THolder.value.protectionGroupZName=new NameAndStringValue_T[0];
		tnp_THolder.value.protectionTrail=new NameAndStringValue_T[0][];
		StringHolder stringHolder=new StringHolder();
		try {
			tnp.modifyTrailNtwProtection(getName(), tnpData, tnp_THolder, stringHolder);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 人工倒换
	 */
	public void performTNPCommand(){
		this.getManager();
		NameAndStringValue_T[][][] nameList=new NameAndStringValue_T[0][][];
		NameAndStringValue_T[][] nameAndStringValue=new NameAndStringValue_T[0][];
		TNPSwitchData_THolder tnpSwitchData=new TNPSwitchData_THolder();
		try {
			//ERotateType.MANUALPRO.getValue()
			tnp.performTNPCommand(ProtectionCommand_T.PC_MANUAL_SWITCH, super.getName(),ESwitchDirection.PROTECT.toString(), 
					getName(), getName(), nameList, nameAndStringValue, tnpSwitchData);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 获取管理者信息
	 */
	public void getManager(){
		Common_IHolder common_IHolder=new Common_IHolder();
		try {
			super.connnect.emsSession.getManager("TrailNtwProtection", common_IHolder);
			tnp=TrailNtwProtMgr_IHelper.narrow(common_IHolder.value);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	public final String MULTILAYERSUBNETWORK="MultiLayerSubnetwork";
	public final String SUBNETWORKCONNECTION="SubnetworkConnection";
	public final String EMS="EMS";
	public final String MANAGEELEMENT="ManagedElement";//网元
	public final String PTP="PTP";//端口标识
	public final String CTP="CTP";// ces  的AC标识（如：pdh端口id）
	private  final String QOSTYPE="qosType";
	private final String QOSNAME="qosName";
	private final String QOSCOS="qosCos";
	private  final String A2ZCIR="A2ZCIR";//正向CIR
	private  final String Z2ACIR="Z2ACIR";//反向	CIR
	private  final String A2ZPIR="A2ZPIR";//正向CIR
	private  final String Z2APIR="Z2APIR";//反向	CIR
	private  final String A2ZCBS="A2ZCBS";//正向CIR
	private  final String Z2ACBS="Z2ACBS";//反向	CIR
	private  final String A2ZPBS="A2ZPBS";//正向CIR
	private  final String Z2APBS="Z2APBS";//反向	CIR
	private  final String A2ZEBS="A2ZEBS";//正向CIR
	private  final String Z2AEBS="Z2AEBS";//反向	CIR
	private  final String A2ZEIR="A2ZEIR";//正向CIR
	private  final String Z2AEIR="Z2AEIR";//反向	CIR
	protected final String CREATETIME="CreateTime";//创建时间
	protected  final String CREATEUSER="createuser";//创建人
	protected final String ISACTIVE="isActive";//是否为激活状态
	private final String owner="DATAX";//拥有者
	protected final String CLIENTNAME="clientName";//客户名称
	private final String SRCINLABEL = "SrcInLabel"; // 源入标签
	private final String SRCOUTLABEL = "SrcOutLabel"; // 源出标签
	private final String DESTINLABEL = "DestInLabel"; // 宿入标签
	private final String DESTOUTLABEL = "DestOutLabel"; // 宿出标签

}
