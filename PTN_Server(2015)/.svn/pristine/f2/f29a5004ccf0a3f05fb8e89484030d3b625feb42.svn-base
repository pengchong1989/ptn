package com.nms.corba.test;

import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import managedElementManager.ManagedElementMgr_I;
import managedElementManager.ManagedElementMgr_IHelper;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.test.common.CorbaConnect;
import common.Common_IHolder;

/**
 * @author guoqc
 * 测试 激活/去激活/删除单点pw
 */
public class PwInfoTest {
	private ManagedElementMgr_I manageI;
	public PwInfoTest() throws ProcessingFailureException{
		Common_IHolder common = new Common_IHolder();
		CorbaConnect.emsSession.getManager("ManagedElement", common);
		manageI = ManagedElementMgr_IHelper.narrow(common.value);
	}
	
	public static void main(String[] args) throws ProcessingFailureException {
		CorbaConnect c = new CorbaConnect();
		c.isConnect();
		PwInfoTest pwTest = new PwInfoTest();
		pwTest.activateCrossConnections();//激活单点pw
		pwTest.deactivateCrossConnections();//去激活单点pw
		pwTest.deleteCrossConnections();//删除单点pw
	}
	
	/**
	 * 激活单点pw
	 * @throws ProcessingFailureException 
	 */
	private void activateCrossConnections() throws ProcessingFailureException{
		NameAndStringValue_T[] pwInfo = new NameAndStringValue_T[4];
		pwInfo[0] = new NameAndStringValue_T((CorbaConfig.getInstanse().getEmsType()),
				(CorbaConfig.getInstanse().getCorbaEmsName()));//厂商信息
		pwInfo[1] = new NameAndStringValue_T("", "");//
		pwInfo[2] = new NameAndStringValue_T("", "2");//层速率
		pwInfo[3] = new NameAndStringValue_T("", "PWETH/703_20140505093511");//pw名称
		manageI.activateCrossConnections(pwInfo);
	}
	
	/**
	 * 去激活单点pw
	 * @throws ProcessingFailureException 
	 */
	private void deactivateCrossConnections() throws ProcessingFailureException{
		NameAndStringValue_T[] pwInfo = new NameAndStringValue_T[4];
		pwInfo[0] = new NameAndStringValue_T((CorbaConfig.getInstanse().getEmsType()), 
				(CorbaConfig.getInstanse().getCorbaEmsName()));//厂商信息
		pwInfo[1] = new NameAndStringValue_T("", "");//
		pwInfo[2] = new NameAndStringValue_T("", "2");//层速率
		pwInfo[3] = new NameAndStringValue_T("", "PWETH/703_20140505093511");//pw名称
		manageI.deactivateCrossConnections(pwInfo);
	}
	
	/**
	 * 删除单点pw
	 * @throws ProcessingFailureException 
	 */
	private void deleteCrossConnections() throws ProcessingFailureException{
		NameAndStringValue_T[] pwInfo = new NameAndStringValue_T[4];
		pwInfo[0] = new NameAndStringValue_T((CorbaConfig.getInstanse().getEmsType()), 
				(CorbaConfig.getInstanse().getCorbaEmsName()));//厂商信息
		pwInfo[1] = new NameAndStringValue_T("", "");//
		pwInfo[2] = new NameAndStringValue_T("", "2");//层速率
		pwInfo[3] = new NameAndStringValue_T("", "PWETH/703_20140505093511");//pw名称
		manageI.deleteCrossConnections(pwInfo);
	}
}
