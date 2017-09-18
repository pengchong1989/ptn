package com.nms.corba.test.CorbaTest_sy;

import globaldefs.ConnectionDirection_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import multiLayerSubnetwork.MultiLayerSubnetworkMgr_I;
import multiLayerSubnetwork.MultiLayerSubnetworkMgr_IHelper;

import org.omg.CORBA.StringHolder;

import subnetworkConnection.CrossConnect_T;
import subnetworkConnection.RouteCreateData_T;
import subnetworkConnection.RouteDescriptor_THolder;
import subnetworkConnection.SNCType_T;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

/**
 * 测试网络业务路由调整
 * @author sy
 *
 */
public class test_MultiLayerSubnetworkMgr_Impl extends TestBase{
	
	private MultiLayerSubnetworkMgr_I multiLayerSub=null;
	public test_MultiLayerSubnetworkMgr_Impl(){
		super();
	}
	
	public static void main(String args[]){
		
		test_MultiLayerSubnetworkMgr_Impl test=new test_MultiLayerSubnetworkMgr_Impl();
		test.getManager();
		//test.removeRoute();
		test.addRoute();
		System.out.println("测试完成");
		
	}
	
	/**
	 * 测试添加路由
	 */
	public void addRoute(){
		
		try {
			RouteCreateData_T routeCreateData=new RouteCreateData_T();
			//routeCreateData.ccInclusions=new CrossConnect_T[1];
			
			
			
			CrossConnect_T[] crossConnect_T=new CrossConnect_T[1];
			crossConnect_T[0]=new CrossConnect_T();
			crossConnect_T[0].direction=ConnectionDirection_T.CD_BI;
			crossConnect_T[0].ccType=SNCType_T.ST_ADD_DROP_A;
			crossConnect_T[0].aEndNameList =new NameAndStringValue_T[1][2];
			crossConnect_T[0].zEndNameList = new NameAndStringValue_T[1][2];
			crossConnect_T[0].aEndNameList[0][0] = new NameAndStringValue_T("ManagedElement", 2+ "");
			crossConnect_T[0].aEndNameList[0][1] = new NameAndStringValue_T("PTP",61 + "");		
			crossConnect_T[0].zEndNameList[0][0] = new NameAndStringValue_T("ManagedElement",2 + "");
			crossConnect_T[0].zEndNameList[0][1] = new NameAndStringValue_T("PTP",61+ "");		
			crossConnect_T[0].additionalInfo=new NameAndStringValue_T[4];
			crossConnect_T[0].additionalInfo[0] = new NameAndStringValue_T(SRCINLABEL, "16");
			crossConnect_T[0].additionalInfo[1] = new NameAndStringValue_T(SRCOUTLABEL, "17");
			crossConnect_T[0].additionalInfo[2] = new NameAndStringValue_T(DESTINLABEL, "18");
			crossConnect_T[0].additionalInfo[3] = new NameAndStringValue_T(DESTOUTLABEL, "19");
			
			
			routeCreateData.ccInclusions=new CrossConnect_T[0];;
			
			routeCreateData.neTpInclusions=new NameAndStringValue_T[2][];
			routeCreateData.neTpInclusions[0]=new NameAndStringValue_T[3];
			routeCreateData.neTpInclusions[0][0]=new NameAndStringValue_T("","");
			routeCreateData.neTpInclusions[0][1]=new NameAndStringValue_T("ASiteid","1");
			routeCreateData.neTpInclusions[0][2]=new NameAndStringValue_T("APortid","29");
			routeCreateData.neTpInclusions[1]=new NameAndStringValue_T[3];
			routeCreateData.neTpInclusions[1][0]=new NameAndStringValue_T("","");
			routeCreateData.neTpInclusions[1][1]=new NameAndStringValue_T("ZSiteid","2");
			routeCreateData.neTpInclusions[1][2]=new NameAndStringValue_T("ZPortid","61");
			routeCreateData.neTpSncExclusions=new NameAndStringValue_T[0][];
			routeCreateData.additionalCreationInfo=new NameAndStringValue_T[4];
			routeCreateData.additionalCreationInfo[0]=new NameAndStringValue_T(this.SRCINLABEL,"17");
			routeCreateData.additionalCreationInfo[1]=new NameAndStringValue_T(this.SRCOUTLABEL,"16");
			routeCreateData.additionalCreationInfo[2]=new NameAndStringValue_T(this.DESTINLABEL,"19");
			routeCreateData.additionalCreationInfo[3]=new NameAndStringValue_T(this.DESTOUTLABEL,"18");
			
			
			RouteDescriptor_THolder routeDescriptor=new RouteDescriptor_THolder();
			StringHolder stringHolder=new StringHolder();
			multiLayerSub.addRoute(getName(), routeCreateData, routeDescriptor, stringHolder);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public void removeRoute(){
		try {
			
			NVSList_THolder nvsList=new NVSList_THolder();
			nvsList.value=new NameAndStringValue_T[0];
			multiLayerSub.removeRoute(getName(), ELayerRate.TUNNEL+"/6", nvsList);
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
			super.connnect.emsSession.getManager("MultiLayerSubnetwork", common_IHolder);
			multiLayerSub=MultiLayerSubnetworkMgr_IHelper.narrow(common_IHolder.value);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	// 交叉路由附加信息KEY值
		private final String SRCINLABEL = "SrcInLabel"; // 源入标签
		private final String SRCOUTLABEL = "SrcOutLabel"; // 源出标签
		private final String DESTINLABEL = "DestInLabel"; // 宿入标签
		private final String DESTOUTLABEL = "DestOutLabel"; // 宿出标签
}
