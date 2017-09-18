package com.nms.corba.test.snc;

import globaldefs.NameAndStringValue_T;
import subnetworkConnection.TPData_T;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.corba.test.common.CorbaConnect;

public class TestBase {
	
	private CorbaConnect corbaConnect=null;
	
	public TestBase(){
		corbaConnect=new CorbaConnect();
		corbaConnect.isConnect();
	}

	public CorbaConnect getCorbaConnect() {
		return corbaConnect;
	}
	public TPData_T[] getTPData() {
		TPData_T[] tp = new TPData_T[0];
//		tp[0] = new TPData_T();
//		tp[0].tpName = getName();
//		tp[0].tpMappingMode = TerminationMode_T.TM_NA;
//		tp[0].ingressTrafficDescriptorName = getName();
//		tp[0].egressTrafficDescriptorName = getName();
//		tp[0].transmissionParams = new LayeredParameters_T[1];
//		tp[0].transmissionParams[0] = new LayeredParameters_T();
//		tp[0].transmissionParams[0].layer = 1;
//		tp[0].transmissionParams[0].transmissionParams = getName();
		return tp;
	}
	/**
	 * 获取名称
	 * 
	 * @return
	 */
	public  NameAndStringValue_T[] getName(String identity) {
		NameAndStringValue_T[] nameAndStringValues = null;
		if("".equals(identity)){
			nameAndStringValues = new NameAndStringValue_T[2];
			nameAndStringValues[0] = new NameAndStringValue_T("EMS", "DATAX/1");
			nameAndStringValues[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "1");
		}else{
			nameAndStringValues = new NameAndStringValue_T[3];
			nameAndStringValues[0] = new NameAndStringValue_T("EMS", "DATAX");
			nameAndStringValues[1] = new NameAndStringValue_T("MultiLayerSubnetwork", "1");
			nameAndStringValues[2] = new NameAndStringValue_T("SubnetworkConnection", ELayerRate.TUNNEL.getValue()+"/1");
		}
		
		return nameAndStringValues;
	}
	// FlowDomain
		protected final String SRCINLABEL = "SrcInLabel"; // 源入标签
		protected final String SRCOUTLABEL = "SrcOutLabel"; // 源出标签
		protected final String DESTINLABEL = "DestInLabel"; // 宿入标签
		protected final String DESTOUTLABEL = "DestOutLabel"; // 宿出标签
		protected final String QOSTYPE = "QosType";
		protected final String QOSNAME = "QosName";
		protected final String QOSCOS = "QosCos";
		protected final String A2ZCIR = "A2ZCIR";// 正向CIR
		protected final String Z2ACIR = "Z2ACIR";// 反向 CIR
		protected final String A2ZPIR = "A2ZPIR";// 正向CIR
		protected final String Z2APIR = "Z2APIR";// 反向 CIR
		protected final String A2ZCBS = "A2ZCBS";// 正向CIR
		protected final String Z2ACBS = "Z2ACBS";// 反向 CIR
		protected final String A2ZPBS = "A2ZPBS";// 正向CIR
		protected final String Z2APBS = "Z2APBS";// 反向 CIR
		protected final String A2ZEBS = "A2ZEBS";// 正向CIR
		protected final String Z2AEBS = "Z2AEBS";// 反向 CIR
		protected final String A2ZEIR = "A2ZEIR";// 正向CIR
		protected final String Z2AEIR = "Z2AEIR";// 反向 CIR
		protected final String A2ZVLANID = "A2ZvlanId";// A端 VlanID
		protected final String Z2AVLANID = "Z2AvlanId";// z端 VlanID
		protected final String A2ZEXITRULE = "A2ZexitRule";// A端 出口规则，关联code表
		protected final String Z2AEXITRULE = "Z2AexitRule";// z端 出口规则，关联code表
		protected final String A2ZVLANPRI = "A2Zvlanpri";
		protected final String Z2AVLANPRI = "Z2Avlanpri";
		protected final String A2ZPORTMODEL = "A2ZportModel";// A端 端口模式，关联code表
		protected final String Z2APORTMODEL = "Z2AportModel";// Z端 端口模式，关联code表
		protected final String FDFRRATE = "fdfrRate";// 业务层速率
		protected final String SERVICEBYPWID = "ServerConnections";// 以太网业务关联的PW 主键ID
		protected final String CREATETIME = "CreateTime";// 创建人
		protected final String CREATEUSER = "Createuser";// 创建时间	
		
		//pw
		protected  final String PWTYPE="pwType";//pw类型
		protected  final String PWBYTUNNELID="ServerConnections";//pw承载的tunnel主键id
		
		//ces
		protected final String CESBYPWID="ServerConnections";//ces 关联的pw的id
		protected final String CESTYPE="CesType";// ces类型
		protected final String CLIENTNAME="ClientName";//关联客户id
		protected  final String FULLROUTE="FullRoute";//路由是否完整
		// 管理器--Manager 0 TrailNtwProtection
		// 管理器--Manager 1 FileTransfer
		// 管理器--Manager 2 ManagedElement
		// 管理器--Manager 3 TransmissionDescriptor
		// 管理器--Manager 4 EMS
		// 管理器--Manager 5 Protection
		// 管理器--Manager 6 Maintenance
		// 管理器--Manager 7 EquipmentInventory
		// 管理器--Manager 8 Security
		// 管理器--Manager 9 ClockSource
		// 管理器--Manager 10 FlowDomain
		// 管理器--Manager 11 HeartBeat
		// 管理器--Manager 12 PerformanceManagement
		// 管理器--Manager 13 MultiLayerSubnetwork
}
