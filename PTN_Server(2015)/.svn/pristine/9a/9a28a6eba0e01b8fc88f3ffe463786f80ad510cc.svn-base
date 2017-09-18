package com.nms.corba.test;

import topologicalLink.TLCreateData_T;
import topologicalLink.TopologicalLink_T;
import topologicalLink.TopologicalLink_THolder;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.test.common.CorbaConnect;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import emsMgr.EMSMgr_IHelper;
import globaldefs.ConnectionDirection_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

/**
 * @author guoqc
 * 测试 创建/删除段
 */
public class TopoLogicalLinkTest {
	private EMSMgr_I emsMgrI;
	
	public TopoLogicalLinkTest() throws ProcessingFailureException{
		Common_IHolder common = new Common_IHolder();
		CorbaConnect.emsSession.getManager("EMS", common);
		emsMgrI = EMSMgr_IHelper.narrow(common.value);
	}
	
	
	public static void main(String[] args) throws ProcessingFailureException {
		CorbaConnect c = new CorbaConnect();
		c.isConnect();
		TopoLogicalLinkTest topoTest = new TopoLogicalLinkTest();
		topoTest.createTopologicalLink();//创建段
		topoTest.deleteTopologicalLink();//删除段
	}
	
	/**
	 * 创建段
	 * @throws ProcessingFailureException 
	 */
	private void createTopologicalLink() throws ProcessingFailureException{
		TLCreateData_T createData = new TLCreateData_T();
		createData.userLabel = "segment1";//段名称
		createData.rate = 0;//
		createData.direction = ConnectionDirection_T.CD_BI;
		createData.owner = "";
		createData.aEndTP = new NameAndStringValue_T[4];
		createData.aEndTP[0] = new NameAndStringValue_T("", "1");//siteId
		createData.aEndTP[1] = new NameAndStringValue_T("", "1");//slotNumber
		createData.aEndTP[2] = new NameAndStringValue_T("", "1");//portId
		createData.aEndTP[3] = new NameAndStringValue_T("workModel", "autoNegotiation");//工作模式
		createData.zEndTP = new NameAndStringValue_T[4];
		createData.zEndTP[0] = new NameAndStringValue_T("", "2");//siteId
		createData.zEndTP[1] = new NameAndStringValue_T("", "1");//slotNumber
		createData.zEndTP[2] = new NameAndStringValue_T("", "13");//portId
		createData.zEndTP[3] = new NameAndStringValue_T("workModel", "autoNegotiation");//工作模式
		NameAndStringValue_T[] qosList = new NameAndStringValue_T[15];
		int i = 0;
		qosList[i++] = new NameAndStringValue_T("", "");
		qosList[i++] = new NameAndStringValue_T("", "");
		qosList[i++] = new NameAndStringValue_T("", "");
		qosList[i++] = new NameAndStringValue_T("", "SP+DWRR");//qosType
		qosList[i++] = new NameAndStringValue_T("", "1");//cos
		qosList[i++] = new NameAndStringValue_T("", "0");//cir
		qosList[i++] = new NameAndStringValue_T("", "16");//weight
		qosList[i++] = new NameAndStringValue_T("", "50");//greenLowThresh
		qosList[i++] = new NameAndStringValue_T("", "90");//greenHighThresh
		qosList[i++] = new NameAndStringValue_T("", "100");//greenProbability
		qosList[i++] = new NameAndStringValue_T("", "64");//yellowLowThresh
		qosList[i++] = new NameAndStringValue_T("", "96");//yellowHighThresh
		qosList[i++] = new NameAndStringValue_T("", "100");//yellowProbability
		qosList[i++] = new NameAndStringValue_T("", "0");//wredEnable
		qosList[i++] = new NameAndStringValue_T("", "0");//mostBandwidth
		createData.additionalCreationInfo = qosList;
		TopologicalLink_THolder topoLink = new TopologicalLink_THolder();
		topoLink.value = new TopologicalLink_T();
		topoLink.value.aEndTP = new NameAndStringValue_T[3];
		topoLink.value.zEndTP = new NameAndStringValue_T[3];
		topoLink.value.additionalInfo = new NameAndStringValue_T[15];
		emsMgrI.createTopologicalLink(createData, topoLink);
	}

	/**
	 * 删除段
	 * @throws ProcessingFailureException 
	 */
	private void deleteTopologicalLink() throws ProcessingFailureException{
		NameAndStringValue_T[] deleteData = new NameAndStringValue_T[2];
		deleteData[0] = new NameAndStringValue_T(CorbaConfig.getInstanse().getEmsType(),
				(CorbaConfig.getInstanse().getCorbaEmsName()));
		deleteData[1] = new NameAndStringValue_T("", "1");
		emsMgrI.deleteTopologicalLink(deleteData);
	}
}
