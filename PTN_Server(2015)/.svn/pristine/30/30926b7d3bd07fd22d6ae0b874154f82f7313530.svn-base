package com.nms.corba.test.managedElementMgr;

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
import com.nms.db.enums.ECesType;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

/**
 * 测试 单点 ces
 * @author sy
 *
 */
public class TestNodeCes  extends TestBase{
//	交叉连接
//	CrossConnection	1.name=”EMS”;value”=<CompanyName/EMSname>  
//	2.name=”ManagedElement”;value”=<ManagedElementName>  
//	3.name=”CrossConnection”;value”=<CrossConnectionName>
	ManagedElementMgr_I managedElementMgr=null;
	public TestNodeCes(){
		super();
	}
	public static void main(String argas[]){
		TestNodeCes test=new TestNodeCes();
//		test.deactivateCrossConnections();
//		test.activateCrossConnections();
//		test.deleteCrossConnections();
//		test.createCrossConnections();
//		test.getCrossConnection();
//		test.getAllCrossConnections();
		test.setUserLabel();
		System.out.println("测试完成！！");
		
		
	}
	private void setUserLabel(){
		this.getManager();
		try {
			CrossConnect_T creossConnect=this.getCrossConnect();;//in
			CrossConnect_THolder crossConnect=new CrossConnect_THolder();//out
			managedElementMgr.setUserLabel(getCCNName(), "test_12_s_12", true);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 测试 ： 	 创建  单点下  ces
	 */
	private void createCrossConnections(){
		this.getManager();
		try {
			CrossConnect_T creossConnect=this.getCrossConnect();;//in
			CrossConnect_THolder crossConnect=new CrossConnect_THolder();//out
			managedElementMgr.createCrossConnections(getCCNName(), (short) 8, creossConnect, crossConnect);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 测试 	单点： 去激活ces
	 * 	测试成功
	 */
	private  void deactivateCrossConnections(){
		this.getManager();
		try {
			managedElementMgr.deactivateCrossConnections(getCCNName());
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 测试 	单点：激活ces
	 * 	测试成功
	 */
	private void activateCrossConnections(){
		this.getManager();
		try {
			managedElementMgr.activateCrossConnections(getCCNName());
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 根据指定  	速率   查询单点下相应类型的所有数据
	 */
	private void getAllCrossConnections(){
		
		this.getManager();
		try {
			short[] lays=new short[]{8};
			CrossConnectList_THolder crossConnectinList=new CrossConnectList_THolder();
			CCIterator_IHolder ccIt=new CCIterator_IHolder();
			managedElementMgr.getAllCrossConnections(getCCNName(), lays, 10, crossConnectinList, ccIt);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 根据  速率/主键ID  查询单点下 ces
	 */
	private void getCrossConnection(){
		this.getManager();
		try {
			CrossConnect_THolder crossConnection=new CrossConnect_THolder();//out
			managedElementMgr.getCrossConnection(getCCNName(), crossConnection);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 测试： 	删除    单点下ces
	 * 	测试成功
	 */
	private void deleteCrossConnections(){
		this.getManager();
		try {
			managedElementMgr.deleteCrossConnections(getCCNName());
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 测试： 	 单点下 ces的  命名
	 * @return
	 */
	private NameAndStringValue_T[] getCCNName(){
		NameAndStringValue_T[] nameAndSringValue_T=new NameAndStringValue_T[3];
		nameAndSringValue_T[0]=new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndSringValue_T[1]=new NameAndStringValue_T("ManagedElement","1");
		nameAndSringValue_T[2]=new NameAndStringValue_T("CrossConnection","8/12");
		return nameAndSringValue_T;
	}
	/**
	 * 设置  新建ces信息
	 * @return
	 */
	private CrossConnect_T getCrossConnect(){
		CrossConnect_T cross=new CrossConnect_T();
		cross.active=true;
		cross.direction=ConnectionDirection_T.CD_BI;//双向
		cross.ccType=SNCType_T.ST_ADD_DROP_A;
		cross.aEndNameList=new NameAndStringValue_T[1][];
		//根据  给定类型，确定A,或者Z端基层机构	PDH,PDHSDH为3层结构： SDH,SDHPDH为4层就够
		cross.aEndNameList[0]=new NameAndStringValue_T[3];
		cross.aEndNameList[0][0]=new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		cross.aEndNameList[0][1]=new NameAndStringValue_T("ManagedElement",1+"");
		cross.aEndNameList[0][2]=new NameAndStringValue_T("PTP","5");
		//cross.aEndNameList[0][3]=new NameAndStringValue_T("CTP","1");
		cross.zEndNameList=new NameAndStringValue_T[0][];
		cross.additionalInfo=new NameAndStringValue_T[5];
		cross.additionalInfo[0]=new NameAndStringValue_T(this.USERLABEL,"789789789");//名称
		cross.additionalInfo[1]=new NameAndStringValue_T(this.CREATEUSER,"sy_te1");//创建人
		cross.additionalInfo[2]=new NameAndStringValue_T(this.CESTYPE,ECesType.PDH.getValue()+"");//类型
		cross.additionalInfo[3]=new NameAndStringValue_T(this.CESBYPWID,ELayerRate.PW.getValue()+"/11");//创建ces关联的pw
		cross.additionalInfo[4]=new NameAndStringValue_T(this.FDFRRATE,ELayerRate.CES.getValue()+"");//层速率

		return cross;
	}
	/**
	 * 获取管理者信息
	 */
	public void getManager(){
		Common_IHolder common_IHolder=new Common_IHolder();
		try {
			super.getCorbaConnect().emsSession.getManager("ManagedElement", common_IHolder);
			managedElementMgr=ManagedElementMgr_IHelper.narrow(common_IHolder.value);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public String USERLABEL="USERLABEL";
	private final String FDFRRATE = "FDFRRATE";// 业务层速率
	protected final String CREATETIME="CreateTime";//创建时间
	protected  final String CREATEUSER="createuser";//创建人
	protected final String ISACTIVE="isActive";//是否为激活状态
	private final String owner = CorbaConfig.getInstanse().getCorbaEmsName();//拥有者
	protected final String CLIENTNAME="clientName";//客户名称
	protected final String CESBYPWID="cesByPwID";//ces 关联的pw的id
	protected final String CESTYPE="cesType";// ces类型
}
