package com.nms.corba.test;

import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import maintenanceOps.MEGCreateData_T;
import maintenanceOps.MEG_THolder;
import maintenanceOps.MaintenanceMgr_I;
import maintenanceOps.MaintenanceMgr_IHelper;
import subnetworkConnection.TPData_T;
import terminationPoint.TerminationMode_T;
import transmissionParameters.LayeredParameters_T;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.corba.test.common.CorbaConnect;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

public class OamTest {
	private MaintenanceMgr_I meMgr = null;

	public static void main(String[] args) {
		
		CorbaConnect corbaTest = new CorbaConnect();
		corbaTest.isConnect();
		OamTest oamTest = new OamTest();
		
		try {
			
			//创建OAM（段/lsp/tunnel）
			oamTest.createMeg();
			//查询一条OAM维护实体，含段，LSP，PW等
//			oamTest.selectOam();
			//function:修改 使能/禁止CC联通性功能，含段，LSP，PW等
//			oamTest.setAdditionalInfo();
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,OamTest.class);
		}
	}
	
	
	public OamTest(){

		try {
			// 获取管理者
			Common_IHolder mgrHolder = new Common_IHolder();
			// 不同的接口管理名称不同
			CorbaConnect.emsSession.getManager("Maintenance", mgrHolder);
			meMgr = MaintenanceMgr_IHelper.narrow(mgrHolder.value);

		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * function:创建OAM（段/lsp/tunnel）
	 */

	public void createMeg() throws Exception {

		try {
			// 测试创建OAM接口
			MEGCreateData_T megCreateData_T = new MEGCreateData_T();
			// megCreateData_T.megType = 0 表示“段层OAM” 1:表示 “tunnel层OAM”
			// 2:表示“PW层OAM”
			megCreateData_T.megType = 3;
			megCreateData_T.userLabel ="userLabel";
			megCreateData_T.owner = "owner";
			megCreateData_T.level = 5;
			NVSList_THolder nvsList_THolder = new NVSList_THolder();
			// 其它属性
			NameAndStringValue_T[] nameAndStringValue = new NameAndStringValue_T[7];
			// 设置网元ID
			nameAndStringValue[0] = new NameAndStringValue_T("ManagedElement", "1");
			// objId (段中A端口主键ID） (tunnel/PW的业务的业务ID)
			nameAndStringValue[1] = new NameAndStringValue_T("ObjId", "4");
			// mel ID
			nameAndStringValue[2] = new NameAndStringValue_T("MegId", "1");
			nameAndStringValue[3] = new NameAndStringValue_T("MegIcc", "3");
			nameAndStringValue[4] = new NameAndStringValue_T("MegUmc", "2");
			nameAndStringValue[5] = new NameAndStringValue_T("LspTc", "473");
			nameAndStringValue[6] = new NameAndStringValue_T("LpbOutTime", "5");
			megCreateData_T.additionalInfo = nameAndStringValue;
			

			megCreateData_T.tpList = new TPData_T[1];
			megCreateData_T.tpList[0] = new TPData_T();
			megCreateData_T.tpList[0].egressTrafficDescriptorName = new NameAndStringValue_T[1];
			megCreateData_T.tpList[0].egressTrafficDescriptorName[0] = new NameAndStringValue_T("EgressTrafficDescriptorName", "2");
			megCreateData_T.tpList[0].ingressTrafficDescriptorName = new NameAndStringValue_T[1];
			megCreateData_T.tpList[0].ingressTrafficDescriptorName[0] = new NameAndStringValue_T("ingressTrafficDescriptorName", "3");
		
			
			megCreateData_T.relatedObject = new NameAndStringValue_T[3]; 
			megCreateData_T.relatedObject[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
			megCreateData_T.relatedObject[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "1");
			megCreateData_T.relatedObject[2] = new NameAndStringValue_T("SubnetworkConnection", ELayerRate.PW.getValue() + "/" + 20);
		
		/*	megCreateData_T.megType = 4;
			megCreateData_T.relatedObject = new NameAndStringValue_T[3]; 
			megCreateData_T.relatedObject[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
			megCreateData_T.relatedObject[1] = new NameAndStringValue_T("ManagedElement", "5");
			megCreateData_T.relatedObject[2] = new NameAndStringValue_T("PTP", "220");
			
			
			megCreateData_T.additionalInfo = new NameAndStringValue_T[4];
			megCreateData_T.additionalInfo[0] = new NameAndStringValue_T("DmLevel", "3");
			megCreateData_T.additionalInfo[1] = new NameAndStringValue_T("MaName", "ssssss");
			megCreateData_T.additionalInfo[2] = new NameAndStringValue_T("MDName", "ssssss");
			megCreateData_T.additionalInfo[3] = new NameAndStringValue_T("MIP", "220");
			
			megCreateData_T.tpList = new TPData_T[1];
			megCreateData_T.tpList[0] = new TPData_T();
			megCreateData_T.tpList[0].ingressTrafficDescriptorName = new NameAndStringValue_T[1];
			megCreateData_T.tpList[0].ingressTrafficDescriptorName[0] = new NameAndStringValue_T("ingressTrafficDescriptorName", "3");
			megCreateData_T.tpList[0].egressTrafficDescriptorName = new NameAndStringValue_T[0];*/
			
			
			megCreateData_T.tpList[0].transmissionParams = new LayeredParameters_T[0];
			megCreateData_T.tpList[0].tpName = new NameAndStringValue_T[0];
			megCreateData_T.tpList[0].tpMappingMode = TerminationMode_T.TM_NA;
			meMgr.createMEG(megCreateData_T, nvsList_THolder);

			if (null != nvsList_THolder.value) {
				for (NameAndStringValue_T xx : nvsList_THolder.value) {
					System.out.println(xx.value);
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			throw e;
		}
	}

	/**
	 * 
	 * 查询一条OAM维护实体，含段，LSP，PW等
	 */

	public void selectOam() throws Exception {

		try {
			NameAndStringValue_T[] selectOamValue = new NameAndStringValue_T[3];
			// megCreateData_T.megType = 0 表示“段层OAM” 1:表示 “tunnel层OAM”
			// 2:表示“PW层OAM”
			selectOamValue[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
			// 网元ID
			selectOamValue[1] = new NameAndStringValue_T("ManagedElement", "5");
			// objId (段中A端口主键ID） (tunnel/PW的业务的业务ID)
			selectOamValue[2] = new NameAndStringValue_T("MEG", "3/1");
			MEG_THolder paramMEG_THolder = new MEG_THolder();
			// 测试接口
			meMgr.getMEG(selectOamValue, paramMEG_THolder);

			if (null != paramMEG_THolder) {
				for (NameAndStringValue_T oamValue : paramMEG_THolder.value.additionalInfo) {
					System.out.println(oamValue.value);
				}
			}
		} catch (ProcessingFailureException e) {
			ExceptionManage.dispose(e,this.getClass());
			throw e;
		}
	}
	
	/**
	 * function:修改 使能/禁止CC联通性功能，含段，LSP，PW等
	 * 
	 */
	public void setAdditionalInfo() throws Exception{
		
		try {
			
			NameAndStringValue_T[] oamValue = new NameAndStringValue_T[4];
			oamValue[0] = new NameAndStringValue_T("manufactureSession",(CorbaConfig.getInstanse().getCorbaEmsName()));
			//oamValue Value 值 包含     siteID  objType:oam类型  段层/tunnel/pw  objId： TUNNEL
			oamValue[1] = new NameAndStringValue_T("siteInfo", "1,0,1");
			oamValue[2] = new NameAndStringValue_T("cvEnable","true");
			oamValue[3] = new NameAndStringValue_T("cvCle","3.33");
			// 测试接口
			NVSList_THolder nvsList_THolder = new NVSList_THolder();
			NameAndStringValue_T[] value = new NameAndStringValue_T[1];
			value[0] = new NameAndStringValue_T("111","111");
			nvsList_THolder.value = value;
			meMgr.setAdditionalInfo(oamValue, nvsList_THolder);
			
			if(null != nvsList_THolder){
				for(NameAndStringValue_T xx : nvsList_THolder.value){
					System.out.println(xx.value);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
