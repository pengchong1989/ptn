package com.nms.corba.ninterface.impl.security;

import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import securityManager.SecurityMgr_IPOA;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.resource.proxy.CorbaSecurityMgrProxy;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

public class SecurityMgr_Impl extends SecurityMgr_IPOA {
	private ICorbaSession userSession = null;

	public SecurityMgr_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}
	
	/**
	 * 修改用户口令
	 */
	@Override
	public boolean modifyPassword(String userName, String oldPassword,
			String newPassword, String confirmPassword)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 安全管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.SATY_MANAGE);
		CorbaSecurityMgrProxy proxy = new CorbaSecurityMgrProxy();
		proxy.modifyPassword(userName, oldPassword, newPassword, confirmPassword);
		return true;
	}

	@Override
	public void setNativeEMSName(NameAndStringValue_T[] objectName,
			String nativeEMSName) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUserLabel(NameAndStringValue_T[] objectName,
			String userLabel, boolean enforceUniqueness)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOwner(NameAndStringValue_T[] objectName, String owner)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getCapabilities(CapabilityList_THolder capabilities)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAdditionalInfo(NameAndStringValue_T[] objectName,
			NVSList_THolder additionalInfo) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

}
