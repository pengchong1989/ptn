package com.nms.corba.ninterface;

import org.omg.CORBA.StringHolder;

import subnetworkConnection.SNCCreateData_T;
import subnetworkConnection.SNCModifyData_T;
import subnetworkConnection.SubnetworkConnection_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.service.FlowDomainMgr_Impl;
import com.nms.corba.ninterface.impl.service.MultiLayerSubnetworkMgr_Impl;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.enums.EActiveStatus;
import com.nms.ui.manager.ExceptionManage;

import flowDomainFragment.FDFrIterator_IHolder;
import flowDomainFragment.FDFrList_THolder;
import flowDomainFragment.FDFrModifyData_T;
import flowDomainFragment.FDFrRoute_THolder;
import flowDomainFragment.FlowDomainFragment_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

public class TestCorba {
	public void testFDFr(){
		
		try {
			
			FDFrList_THolder fdfrList=new FDFrList_THolder();
			FDFrIterator_IHolder fdfrIt=new FDFrIterator_IHolder();
			
			FlowDomainFragment_THolder flowDomainFragment_T=new FlowDomainFragment_THolder();
			FlowDomainMgr_Impl flowDomainMgr_Impl=new FlowDomainMgr_Impl(null);
			
			FDFrRoute_THolder fdFrRoute_THolder=new FDFrRoute_THolder();
			
			NameAndStringValue_T[] nameAndStringValues = new NameAndStringValue_T[3];
			NameAndStringValue_T nameAndStringValue_T = new NameAndStringValue_T();
			nameAndStringValue_T.name = "1";
			nameAndStringValue_T.value = "1";
			nameAndStringValues[0] = nameAndStringValue_T;

			nameAndStringValue_T = new NameAndStringValue_T();
			nameAndStringValue_T.name = "2";
			nameAndStringValue_T.value = "2";
			nameAndStringValues[1] = nameAndStringValue_T;

			nameAndStringValue_T = new NameAndStringValue_T();
			nameAndStringValue_T.name = "3";
			nameAndStringValue_T.value = "11/1";
			nameAndStringValues[2] = nameAndStringValue_T;
			
			StringHolder stringHolder=new StringHolder();
			
			FDFrModifyData_T fdfrModifyData=new FDFrModifyData_T();
			fdfrModifyData.userLabel="kk_test_modify";
			
			
//			//测试查询全部
//			flowDomainMgr_Impl.getAllFDFrs(null, 100, layerArray, fdfrList, fdfrIt);
			
//			//测试查询单个
//			flowDomainMgr_Impl.getFDFr(nameAndStringValues, flowDomainFragment_T);
			
//			//测试查询路由信息
//			flowDomainMgr_Impl.getFDFrRoute(nameAndStringValues, fdFrRoute_THolder);
			
//			//测试去激活
//			flowDomainMgr_Impl.deactivateFDFr(nameAndStringValues, flowDomainFragment_T);
			
//			//测试激活
//			flowDomainMgr_Impl.activateFDFr(nameAndStringValues, flowDomainFragment_T);
			
			//测试删除
			flowDomainMgr_Impl.deactivateAndDeleteFDFr(nameAndStringValues, stringHolder);
			
//			//测试修改
//			flowDomainMgr_Impl.modifyFDFr(nameAndStringValues, fdfrModifyData, null, null, null, null, flowDomainFragment_T, stringHolder);
			
//			//测试创建
//			FDFrCreateData_T createData = new FDFrCreateData_T();
//			createData.userLabel="kk_test_create";
//			createData.fdfrType=EServiceType.ELAN.toString();
//			
//			MatrixFlowDomainFragmentList_THolder mfdfrs=new MatrixFlowDomainFragmentList_THolder();
//			mfdfrs.value=new MatrixFlowDomainFragment_T[3];
//			
//			MatrixFlowDomainFragment_T matrixFlowDomainFragment=new MatrixFlowDomainFragment_T();
//			matrixFlowDomainFragment.active=true;
//			matrixFlowDomainFragment.mfdfrType=EServiceType.ELAN.toString();
//			matrixFlowDomainFragment.aEnd=this.createName(2, 73);
//			matrixFlowDomainFragment.zEnd=this.createName(3, 105);
//			matrixFlowDomainFragment.additionalInfo=this.additionalInfo(7);
//			
//			
//			MatrixFlowDomainFragment_T matrixFlowDomainFragment2=new MatrixFlowDomainFragment_T();
//			matrixFlowDomainFragment2.active=true;
//			matrixFlowDomainFragment2.mfdfrType=EServiceType.ELAN.toString();
//			matrixFlowDomainFragment2.aEnd=this.createName(2, 73);
//			matrixFlowDomainFragment2.zEnd=this.createName(1, 49);
//			matrixFlowDomainFragment2.additionalInfo=this.additionalInfo(8);
//			
//			MatrixFlowDomainFragment_T matrixFlowDomainFragment3=new MatrixFlowDomainFragment_T();
//			matrixFlowDomainFragment3.active=true;
//			matrixFlowDomainFragment3.mfdfrType=EServiceType.ELAN.toString();
//			matrixFlowDomainFragment3.aEnd=this.createName(3, 105);
//			matrixFlowDomainFragment3.zEnd=this.createName(1, 49);
//			matrixFlowDomainFragment3.additionalInfo=this.additionalInfo(9);
//			
//			
//			mfdfrs.value[0]=matrixFlowDomainFragment;
//			mfdfrs.value[1]=matrixFlowDomainFragment2;
//			mfdfrs.value[2]=matrixFlowDomainFragment3;
//			
//			flowDomainMgr_Impl.createAndActivateFDFr(createData, null, null, null, null, mfdfrs, null, flowDomainFragment_T, null, null, stringHolder);
			
			System.out.println("测试完成");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
	private NameAndStringValue_T[][] createName(int siteid,int portId){
		NameAndStringValue_T[][] name=new NameAndStringValue_T[1][];
		NameAndStringValue_T[] names=new NameAndStringValue_T[3];
		
		names[0] = new NameAndStringValue_T("EMS", "TEST");
		names[1] = new NameAndStringValue_T("ManagedElement", siteid+"");
		names[2] = new NameAndStringValue_T("PTP",portId+"");
		
		name[0]=names;
		
		return name;
	}
	
	
	public void testSnc(){
		MultiLayerSubnetworkMgr_Impl multiLayerSubnetworkMgr_Impl=new MultiLayerSubnetworkMgr_Impl(new ICorbaSession());
		NameAndStringValue_T[] nameAndStringValues = new NameAndStringValue_T[3];
		NameAndStringValue_T nameAndStringValue_T = new NameAndStringValue_T();
		nameAndStringValue_T.name = "1";
		nameAndStringValue_T.value = "1";
		nameAndStringValues[0] = nameAndStringValue_T;

		nameAndStringValue_T = new NameAndStringValue_T();
		nameAndStringValue_T.name = "2";
		nameAndStringValue_T.value = "2";
		nameAndStringValues[1] = nameAndStringValue_T;

		nameAndStringValue_T = new NameAndStringValue_T();
		nameAndStringValue_T.name = "3";
		nameAndStringValue_T.value = "6/38";
		nameAndStringValues[2] = nameAndStringValue_T;
		Short[] layer=new Short[1];
		layer[0]=8;
		try {
			SNCModifyData_T sncUpdate=new SNCModifyData_T();
			sncUpdate.userLabel="666666";
			
			
			SNCCreateData_T sncCreateData_T=new SNCCreateData_T();
			sncCreateData_T.layerRate=6;
			sncCreateData_T.userLabel="77777";
			NameAndStringValue_T[] addinfo = new NameAndStringValue_T[6];
			sncCreateData_T.additionalCreationInfo=addinfo;
			addinfo[0]=new NameAndStringValue_T();
			addinfo[0].name="fullroute";
			addinfo[0].value =EActiveStatus.ACTIVITY.getValue()+"" ;

			addinfo[2]=new NameAndStringValue_T();
			addinfo[2].name="createuser";
			addinfo[2].value="songyang";
			addinfo[3]=new NameAndStringValue_T();
			addinfo[3].name="CreateTime";
			addinfo[3].value="1921-12-01";
			addinfo[4]=new NameAndStringValue_T();
			addinfo[4].name="clientId";
			addinfo[4].value="1";
		

			addinfo[1]=new NameAndStringValue_T();
			addinfo[1].name="cesType";
			addinfo[1].value="5";
			addinfo[5]=new NameAndStringValue_T();
			addinfo[5].name="cesByPwID";
			addinfo[5].value="40";
			sncCreateData_T.aEnd=new NameAndStringValue_T[1][2];
			sncCreateData_T.aEnd[0]=new NameAndStringValue_T[2];
			sncCreateData_T.aEnd[0][0]=new NameAndStringValue_T("ManageElement",7+"");
			sncCreateData_T.aEnd[0][1]=new NameAndStringValue_T("ctp",390+"");
			sncCreateData_T.zEnd=new NameAndStringValue_T[1][2];
			sncCreateData_T.zEnd[0]=new NameAndStringValue_T[2];
			sncCreateData_T.zEnd[0][0]=new NameAndStringValue_T("ManageElement",4+"");
			sncCreateData_T.zEnd[0][1]=new NameAndStringValue_T("ctp",505+"");

			SubnetworkConnection_THolder subnetworkConnection_THolder=new SubnetworkConnection_THolder();
			StringHolder stringHolder= new StringHolder();
//			//测试去激活
			//multiLayerSubnetworkMgr_Impl.deactivateSNC(nameAndStringValues, null, null, null, subnetworkConnection_THolder, stringHolder);
			
//			//测试激活
			//multiLayerSubnetworkMgr_Impl.activateSNC(nameAndStringValues, null, null, null, subnetworkConnection_THolder, stringHolder);
			//
			//测试删除
		//	multiLayerSubnetworkMgr_Impl.deleteSNC(nameAndStringValues, null);
			//新建
			multiLayerSubnetworkMgr_Impl.createSNC(sncCreateData_T, subnetworkConnection_THolder, stringHolder);
		//	multiLayerSubnetworkMgr_Impl.getAllSubnetworkConnections(nameAndStringValues,layer, 10, new SubnetworkConnectionList_THolder(), new SNCIterator_IHolder());
			//测试修改
			//multiLayerSubnetworkMgr_Impl.modifySNC(nameAndStringValues, null, sncUpdate, null, null, null, null, subnetworkConnection_THolder, stringHolder);
			System.out.println("测试完成");
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	private NameAndStringValue_T[] additionalInfo(int pwId){
		NameAndStringValue_T[] additionalInfo=new NameAndStringValue_T[27];
		
		additionalInfo[0] = new NameAndStringValue_T(this.A2ZEIR, "0");// 路径类型
		additionalInfo[1] = new NameAndStringValue_T(this.Z2AEIR, "0");// 路径类型
		additionalInfo[2] = new NameAndStringValue_T(this.QOSTYPE,"L2");// 路径类型
		// qos描述信息
		additionalInfo[3] = new NameAndStringValue_T(this.A2ZCIR, "0");// 路径类型
		additionalInfo[4] = new NameAndStringValue_T(this.Z2ACIR,"0");// 路径类型
		additionalInfo[5] = new NameAndStringValue_T(this.A2ZPIR, "0");// 路径类型
		additionalInfo[6] = new NameAndStringValue_T(this.Z2APIR,"0");// 路径类型
		additionalInfo[7] = new NameAndStringValue_T(this.A2ZCBS, "0");// 路径类型
		additionalInfo[8] = new NameAndStringValue_T(this.Z2ACBS, "0");// 路径类型
		additionalInfo[9] = new NameAndStringValue_T(this.A2ZPBS, "0");// 路径类型
		additionalInfo[10] = new NameAndStringValue_T(this.Z2APBS, "0");// 路径类型
		additionalInfo[11] = new NameAndStringValue_T(this.A2ZEBS, "0");// 路径类型
		additionalInfo[12] = new NameAndStringValue_T(this.Z2AEBS, "0");// 路径类型
		additionalInfo[13] = new NameAndStringValue_T(this.QOSCOS, "EF");// 路径类型
		additionalInfo[14] = new NameAndStringValue_T(this.QOSNAME, "");// 路径类型
		
		additionalInfo[15] = new NameAndStringValue_T(this.A2ZVLANID,"1");// vlanid=下话增加VLAN ID
		additionalInfo[16] = new NameAndStringValue_T(this.A2ZEXITRULE, "0"); // 出口规则，关联code表主键 = 下话TAG行为
		additionalInfo[17] = new NameAndStringValue_T(this.A2ZVLANPRI, "0");// vlanpri=下话增加VLAN PRI
		additionalInfo[18] = new NameAndStringValue_T(this.A2ZPORTMODEL, "0");// 端口模式，关联code表主键

		// Z端标识Z2A 开头
		additionalInfo[19] = new NameAndStringValue_T(this.Z2AVLANID, "1");// vlanid=下话增加VLAN ID
		additionalInfo[20] = new NameAndStringValue_T(this.Z2AEXITRULE, "0"); // 出口规则，关联code表主键 = 下话TAG行为
		additionalInfo[21] = new NameAndStringValue_T(this.Z2AVLANPRI, "0");// vlanpri=下话增加VLAN PRI
		additionalInfo[22] = new NameAndStringValue_T(this.Z2APORTMODEL, "0");// 端口模式，关联code表主键

		additionalInfo[23] = new NameAndStringValue_T(this.CREATETIME, "2014-4-21 15:13:10");// 创建时间
		additionalInfo[24] = new NameAndStringValue_T(this.SERVICEBYPWID, pwId + "");// 使用的PW路径标识
		additionalInfo[25] = new NameAndStringValue_T(this.FDFRRATE, ELayerRate.ETHSERVICE.getValue()+"");// 业务层速率
		additionalInfo[26] = new NameAndStringValue_T(this.CREATEUSER, "admin");// 用户名称
		
		return additionalInfo;
	}
	
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
	
	private final String A2ZVLANID = "A2ZvlanId";// A端 VlanID
	private final String Z2AVLANID = "Z2AvlanId";// z端 VlanID
	private final String A2ZEXITRULE = "A2ZexitRule";// A端 出口规则，关联code表
	private final String Z2AEXITRULE = "Z2AexitRule";// z端 出口规则，关联code表
	private final String A2ZVLANPRI = "A2Zvlanpri";
	private final String Z2AVLANPRI = "Z2Avlanpri";
	private final String A2ZPORTMODEL = "A2ZportModel";// A端 端口模式，关联code表
	private final String Z2APORTMODEL = "Z2AportModel";// Z端 端口模式，关联code表
	private final String FDFRRATE = "fdfrRate";// 业务层速率
	private final String SERVICEBYPWID = "serviceByPwId";// 以太网业务关联的PW 主键ID
	private final String CREATETIME="CreateTime";//创建人
	private  final String CREATEUSER="createuser";//创建时间

}
