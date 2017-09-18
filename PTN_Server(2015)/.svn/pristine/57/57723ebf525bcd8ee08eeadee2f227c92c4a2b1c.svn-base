package com.nms.corba.test;

import subnetworkConnection.TPData_T;
import terminationPoint.TerminationMode_T;
import transmissionParameters.LayeredParameters_T;

import com.nms.corba.test.common.CorbaConnect;
import com.nms.db.enums.EServiceType;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

import flowDomain.FlowDomainMgr_I;
import flowDomain.FlowDomainMgr_IHelper;
import flowDomainFragment.MatrixFlowDomainFragmentList_THolder;
import flowDomainFragment.MatrixFlowDomainFragment_T;
import flowDomainFragment.MatrixFlowDomainFragment_THolder;
import globaldefs.ConnectionDirection_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

public class ServiceTest {

	private FlowDomainMgr_I flowMgr ;
	
	public ServiceTest(){
		try {
			//获取管理者
			Common_IHolder mgr = new Common_IHolder();
			CorbaConnect.emsSession.getManager("FlowDomain", mgr);
			flowMgr = FlowDomainMgr_IHelper.narrow(mgr.value);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * function:创建单点业务
	 * 
	 */
	public void createService(){
		try {
			MatrixFlowDomainFragment_T createServiceData = new MatrixFlowDomainFragment_T();
			TPData_T[] tpData = new TPData_T[1];
			tpData[0] = new TPData_T();
			NameAndStringValue_T[] nameAndStringValueTs = new NameAndStringValue_T[1];
			nameAndStringValueTs[0] = new NameAndStringValue_T("","");
			tpData[0].egressTrafficDescriptorName = nameAndStringValueTs;
			tpData[0].ingressTrafficDescriptorName = nameAndStringValueTs;
			tpData[0].tpName = nameAndStringValueTs;
			tpData[0].tpMappingMode = TerminationMode_T.TM_NA;
			LayeredParameters_T[] layeredParametersTs = new LayeredParameters_T[1];
			layeredParametersTs[0] = new LayeredParameters_T();
			layeredParametersTs[0].layer = 1;
			layeredParametersTs[0].transmissionParams = new NameAndStringValue_T[1];
			layeredParametersTs[0].transmissionParams[0]= new  NameAndStringValue_T("","");
			tpData[0].transmissionParams = layeredParametersTs;
			MatrixFlowDomainFragment_THolder holder = new MatrixFlowDomainFragment_THolder();
			
			createServiceData.aEnd = new NameAndStringValue_T[1][1];
			//网元ID
			createServiceData.aEnd[0][0] = new NameAndStringValue_T("siteID","1");
			
			createServiceData.zEnd = new NameAndStringValue_T[1][1];
			//网元ID
			createServiceData.zEnd[0][0] = new NameAndStringValue_T("siteID","0");
			//激活状态
		
			createServiceData.active = true;
			//业务类型
			createServiceData.mfdfrType = "ELINE";
			//其它一些属性
			createServiceData.direction = ConnectionDirection_T.CD_BI;
			createServiceData.transmissionParams = new LayeredParameters_T();
			createServiceData.transmissionParams.layer = 1;
			createServiceData.transmissionParams.transmissionParams = new NameAndStringValue_T[1];
			createServiceData.transmissionParams.transmissionParams[0]= new  NameAndStringValue_T("","");
			
			NameAndStringValue_T[] addInfo= new NameAndStringValue_T[12];
			//业务名称
			addInfo[0] = new NameAndStringValue_T("serviceName","serviceEline");
			//端口主键ID
			addInfo[1] = new NameAndStringValue_T("PortId","1");
			//广播/组播/未知单播报文抑制门限；
			addInfo[2] = new NameAndStringValue_T("VLANID","2");
			//cir
			addInfo[3] = new NameAndStringValue_T("CIR","2");
			//cbs
			addInfo[4] = new NameAndStringValue_T("cbs","2");
			//cm
			addInfo[5] = new NameAndStringValue_T("cm","2");
			//pir
			addInfo[6] = new NameAndStringValue_T("pir","2");
			//pbs
			addInfo[7] = new NameAndStringValue_T("pbs","2");
			//简单流的类型 L2/L3/vlanpri
			addInfo[8] = new NameAndStringValue_T("FlowType", "L2");
			//水平分割线
			addInfo[9] = new NameAndStringValue_T("open", "1");
			//pw的条数
			addInfo[10] = new NameAndStringValue_T("pwNumber","1");
			//pwID
			addInfo[11] = new NameAndStringValue_T("pwID","1");
			createServiceData.additionalInfo = addInfo;
			//测试接口
			flowMgr.createMFDFr(createServiceData, holder);
			
			System.out.println(holder.value.active+":::激活状态");
			System.out.println(holder.value.additionalInfo[0].value);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * function:激活业务
	 */
	public void activateService(){
		
		try {
			NameAndStringValue_T[][] value = new  NameAndStringValue_T[1][2];
			value[0][0] = new NameAndStringValue_T("serviceID","1");
			value[0][1] = new NameAndStringValue_T("serviceType","ELINE");
			flowMgr.activateMFDFr(value);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	
	public void getAllMeMfdr(){
		NameAndStringValue_T[] meName = new NameAndStringValue_T[3];
		meName[0] = new NameAndStringValue_T("EMS", "DATAX/703B");
		meName[1] = new NameAndStringValue_T("ManagedElement","2");
		meName[2] = new NameAndStringValue_T("ServiceType",EServiceType.ETREE.toString());
		MatrixFlowDomainFragmentList_THolder mfdfrList = new MatrixFlowDomainFragmentList_THolder();
		try {
			flowMgr.getMeMFDFrs(meName, mfdfrList);
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public  static void main(String[] args){
		
		CorbaConnect connect = new CorbaConnect();
		connect.isConnect();
        ServiceTest serviecTest = new ServiceTest();
       //创建业务
//        serviecTest.createService();
        serviecTest.getAllMeMfdr();
		
	}
	
	
	
}
