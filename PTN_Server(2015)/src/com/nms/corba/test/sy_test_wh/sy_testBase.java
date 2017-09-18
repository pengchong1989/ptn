package com.nms.corba.test.sy_test_wh;

import globaldefs.NameAndStringValue_T;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.test.common.CorbaConnect;


public class sy_testBase {
	protected CorbaConnect connnect=null;
	public sy_testBase(){
		connnect=new CorbaConnect();
		connnect.isConnect();
	}
	/**
	 * 设置名称
	 * @return
	 */
	public NameAndStringValue_T[] getName(){
		NameAndStringValue_T[] nameAndSringValue_T=new NameAndStringValue_T[2];
		nameAndSringValue_T[0]=new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndSringValue_T[1]=new NameAndStringValue_T("ManagedElement","5");
		//nameAndSringValue_T[2]=new NameAndStringValue_T("PGP","8/19");
//		NameAndStringValue_T[] nameAndSringValue_T=new NameAndStringValue_T[3];
//		nameAndSringValue_T[0]=new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
//		nameAndSringValue_T[1]=new NameAndStringValue_T("MultiLayerSubnetwork","1");
//		nameAndSringValue_T[2]=new NameAndStringValue_T("SubnetworkConnection","1/42");
		return nameAndSringValue_T;
	}
	private final String SRCINLABEL = "SrcInLabel"; // 源入标签
	private final String SRCOUTLABEL = "SrcOutLabel"; // 源出标签
	private final String DESTINLABEL = "DestInLabel"; // 宿入标签
	private final String DESTOUTLABEL = "DestOutLabel"; // 宿出标签
//	管理器--Manager 0  FileTransfer
//	管理器--Manager 1  TrailNtwProtection
//	管理器--Manager 2  ManagedElement
//	管理器--Manager 3  TransmissionDescriptor
//	管理器--Manager 4  EMS
//	管理器--Manager 5  Protection
//	管理器--Manager 6  Maintenance
//	管理器--Manager 7  EquipmentInventory
//	管理器--Manager 8  Security
//	管理器--Manager 9  ClockSource
//	管理器--Manager 10  Time
//	管理器--Manager 11  FlowDomain
//	管理器--Manager 12  HeartBeat
//	管理器--Manager 13  PerformanceManagement
//	管理器--Manager 14  MultiLayerSubnetwork
}
