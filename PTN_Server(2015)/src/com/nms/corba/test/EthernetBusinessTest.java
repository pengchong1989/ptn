package com.nms.corba.test;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.test.common.CorbaConnect;
import com.nms.db.enums.EServiceType;
import common.Common_IHolder;

import flowDomain.FlowDomainMgr_I;
import flowDomain.FlowDomainMgr_IHelper;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

/**
 * @author guoqc
 * 测试 去激活单点以太网业务
 */
public class EthernetBusinessTest {
	private FlowDomainMgr_I flowDomainI;
	
	public EthernetBusinessTest() throws ProcessingFailureException{
		Common_IHolder common = new Common_IHolder();
		CorbaConnect.emsSession.getManager("FlowDomain", common);
		flowDomainI = FlowDomainMgr_IHelper.narrow(common.value);
	}
	
	public static void main(String[] args) throws ProcessingFailureException {
		CorbaConnect c = new CorbaConnect();
		c.isConnect();
		EthernetBusinessTest ethTest = new EthernetBusinessTest();
		ethTest.deactivateMFDFr();//去激活单点以太网业务
	}
	
	/**
	 * 去激活单点以太网业务
	 */
	private void deactivateMFDFr() throws ProcessingFailureException{
		NameAndStringValue_T[][] objName = new NameAndStringValue_T[1][4];
		objName[0][0] = new NameAndStringValue_T((CorbaConfig.getInstanse().getEmsType()), 
				(CorbaConfig.getInstanse().getCorbaEmsName()));//厂商信息
		objName[0][1] = new NameAndStringValue_T("", "1");//siteId
		objName[0][2] = new NameAndStringValue_T("", EServiceType.ELINE.toString());//type
		objName[0][3] = new NameAndStringValue_T("", "1");//主键id
		flowDomainI.deactivateMFDFr(objName);
	}
}
