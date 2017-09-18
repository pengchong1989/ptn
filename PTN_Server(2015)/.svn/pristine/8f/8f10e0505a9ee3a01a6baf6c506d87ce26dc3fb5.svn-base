package com.nms.corba.ninterface.framework;

import nmsSession.NmsSession_I;

import org.apache.log4j.Logger;

import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.corba.ninterface.util.LoginConfirmation;

import emsSession.EmsSession_I;
import emsSession.EmsSession_IHolder;
import emsSessionFactory.EmsSessionFactory_IPOA;
import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

public class EmsSessionFactoryImpl extends EmsSessionFactory_IPOA {

	static Logger LOG = Logger.getLogger(CorbaBooster.class.getName());

	@Override
	public void getEmsSession(String user, String password,
			NmsSession_I client, EmsSession_IHolder emsSessionInterface)
			throws ProcessingFailureException {
		try {
			LOG.info("[getEmsSession] begin.");
			LoginConfirmation loginConfirmation = new LoginConfirmation();
			boolean isSuccessful= loginConfirmation.login(user, password);
			if(!isSuccessful){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"login on failed.");
			}
			
			EmsSession_I session = CorbaSessionMgr.getInstanse().createEmsSession(
					user, client);
			if (null == session) {
				LOG.error("[getEmsSession] failed.");
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"create ems session failed.");
			}
	
			emsSessionInterface.value = session;
	
			LOG.info("[getEmsSession]end.");
			//登陆成功添加用户权限
			if (isSuccessful) {
				AuthorityManager authorityManager = new AuthorityManager();
				authorityManager.addRoleRelevance_global(user);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"login ex.");
		} 
	}

	@Override
	public String getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

}
