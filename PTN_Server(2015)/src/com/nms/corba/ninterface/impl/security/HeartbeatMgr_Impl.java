package com.nms.corba.ninterface.impl.security;

import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import heartbeatManager.HeartbeatMgr_IPOA;

import org.apache.log4j.Logger;
import org.omg.CORBA.IntHolder;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

public class HeartbeatMgr_Impl extends HeartbeatMgr_IPOA {
	private Logger LOG = Logger.getLogger(HeartbeatMgr_Impl.class.getName());
	private ICorbaSession userSession = null;

	public HeartbeatMgr_Impl(ICorbaSession userSession) {
		// TODO Auto-generated constructor stub
		this.userSession = userSession;
	}

	@Override
	public void getHeartbeatParameter(NameAndStringValue_T[] name,
			IntHolder reportInterval) throws ProcessingFailureException {
		// TODO Auto-generated method stub
		// 验证用户权限 无权限抛异常 配置查看
		AuthorityManager.checkAuthority(this.userSession,
				RootFactory.DEPLOY_SELECT);
		LOG.info("[getHeartbeatParameter]begin.");
		if (name.length != 1) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT,
					"invalid input paramer,please input ems name!");
		}
		if (name[0].name.equals("EMS")
				&& name[0].value.equals(CorbaConfig.getInstanse()
						.getCorbaEmsName())) {
			reportInterval.value = CorbaConfig.getInstanse()
					.getHeartBeatInterval();
			LOG.info("[getHeartbeatParameter]end.");
			return;
		}

		throw new ProcessingFailureException(
				ExceptionType_T.EXCPT_INVALID_INPUT,
				"invalid input paramer,please input ems name!");
	}

	@Override
	public void setHeartbeatParameter(NameAndStringValue_T[] name,
			int reportInterval) throws ProcessingFailureException {
		// TODO Auto-generated method stub
		// 验证用户权限 无权限抛异常 配置管理
		AuthorityManager.checkAuthority(this.userSession,
				RootFactory.DEPLOY_MANAGE);
		LOG.info("[setHeartbeatParameter]begin.");
		if (1 != name.length) {
			LOG.error("[setHeartbeatParameter]invalid input name, name size should be one.");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT,
					"Not correct input ems name.");
		}

		int iInterval = reportInterval;
		LOG.info("[setHeartbeatParameter]reportInterval is " + iInterval);

		if (iInterval < 0) {
			LOG.info("[setHeartbeatParameter]reportInterval should not be negtive.");
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT,
					"reportInterval should not be negtive.");
		}

		if (name[0].name.equals("EMS")
				&& name[0].value.equals(CorbaConfig.getInstanse()
						.getCorbaEmsName())) {
			CorbaConfig.getInstanse().setHeartBeatInterval(iInterval);
			LOG.info("[setHeartbeatParameter]end.");
			return;
		}

		throw new ProcessingFailureException(
				ExceptionType_T.EXCPT_INVALID_INPUT,
				"invalid input paramer,please input ems name!");
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
