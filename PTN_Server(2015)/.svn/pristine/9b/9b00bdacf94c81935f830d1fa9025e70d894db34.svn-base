package com.nms.corba.ninterface.impl.resource;

import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import org.omg.CORBA.StringHolder;

import timeMgr.TimeMgr_IPOA;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.resource.proxy.CorbaTimeMgrProxy;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

public class TimeMgr_Impl extends TimeMgr_IPOA {
	
	private ICorbaSession userSession = null;

	public TimeMgr_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}
	
	/**
	 * 查询厂商网管系统的当前时间
	 */
	@Override
	public void getEMSTime(NameAndStringValue_T[] emsName, StringHolder emsTime)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 配置查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.DEPLOY_SELECT);
		CorbaTimeMgrProxy timeProxy = new CorbaTimeMgrProxy();
		timeProxy.getEMSTime(emsName, emsTime);
	}

	/**
	 * 设置单个厂商网管系统的当前时间
	 */
	@Override
	public boolean setEMSTime(NameAndStringValue_T[] emsName, String time)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 配置管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.DEPLOY_MANAGE);
		CorbaTimeMgrProxy timeProxy = new CorbaTimeMgrProxy();
		if (timeProxy.setEMSTime(emsName, time)) {
			return true;
		}
		return false;
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
