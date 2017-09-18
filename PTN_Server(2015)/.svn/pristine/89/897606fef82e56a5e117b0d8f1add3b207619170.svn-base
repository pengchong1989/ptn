package com.nms.corba.test.managedElementMgr;

import managedElementManager.ManagedElementMgr_I;
import topologicalLink.TopologicalLinkIterator_IHolder;
import topologicalLink.TopologicalLinkList_THolder;
import topologicalLink.TopologicalLink_T;
import topologicalLink.TopologicalLink_THolder;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.corba.test.common.CorbaConnect;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import emsMgr.EMSMgr_IHelper;
import fileTransfer.FileTransferMgr_I;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

public class TopoTest {
	// 网元管理者
	ManagedElementMgr_I meMgr = null;
	// ems管理者
	EMSMgr_I emsMgr = null;
	FileTransferMgr_I fileTransferMgr = null;

	public static void main(String[] args) {
		try {
			CorbaConnect connect = new CorbaConnect();
			if (connect.isConnect()) {
				TopoTest topoTest = new TopoTest();
//				topoTest.getTopo(connect);
				 topoTest.setTopoUserLabel(connect);

			}
		} catch (ProcessingFailureException e) {
			System.out.println(e.errorReason);
		}
	}

	/**
	 * 查询拓扑
	 * 
	 * @throws ProcessingFailureException
	 */
	public void getTopo(CorbaConnect connect) throws ProcessingFailureException {
		// 获取管理者
		Common_IHolder mgrHolder = new Common_IHolder();
		// 不同接口管理者名称不同
		connect.emsSession.getManager("EMS", mgrHolder);
		EMSMgr_I EMSMgr_I = EMSMgr_IHelper.narrow(mgrHolder.value);
		TopologicalLink_THolder topo = new TopologicalLink_THolder();
		TopologicalLinkList_THolder topoList = new TopologicalLinkList_THolder();
		TopologicalLinkIterator_IHolder topoIt = new TopologicalLinkIterator_IHolder();
		NameAndStringValue_T[] name = new NameAndStringValue_T[1];
		name[0] = new NameAndStringValue_T();
		name[0].name = "EMS";// 子网标示符
		name[0].value = "DATAX/1";

		EMSMgr_I.getAllTopLevelTopologicalLinks(1, topoList, topoIt);

		for(TopologicalLink_T topologicalLink_T:topoList.value){
			System.out.println(topologicalLink_T.userLabel);
		}
		
		while(topoIt.value.next_n(1, topoList)){
			for(TopologicalLink_T topologicalLink_T:topoList.value){
				System.out.println(topologicalLink_T.userLabel);
			}
		}
		
		for(TopologicalLink_T topologicalLink_T:topoList.value){
			System.out.println(topologicalLink_T.userLabel);
		}

//		EMSMgr_I.getTopLevelTopologicalLink(this.getTopName("1000"), topo);
//		System.out.println("测试完成");
	}

	/**
	 * 查询拓扑
	 * 
	 * @throws ProcessingFailureException
	 */
	public void setTopoUserLabel(CorbaConnect connect) throws ProcessingFailureException {
		// 获取管理者
		Common_IHolder mgrHolder = new Common_IHolder();
		// 不同接口管理者名称不同
		connect.emsSession.getManager("EMS", mgrHolder);
		EMSMgr_I EMSMgr_I = EMSMgr_IHelper.narrow(mgrHolder.value);

		EMSMgr_I.setUserLabel(this.getTopName("3"), "1234", false);
		System.out.println("测试完成");
	}

	private NameAndStringValue_T[] getTopName(String topId) {
		NameAndStringValue_T[] topName = new NameAndStringValue_T[2];
		topName[0] = new NameAndStringValue_T("EMS", "DATAX/1");
		topName[1] = new NameAndStringValue_T("TopologicalLink", ELayerRate.TOPOLOGICALLINK.getValue() + "/" + topId);
		return topName;
	}
}
