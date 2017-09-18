package com.nms.corba.ninterface.impl.system;

import notifications.QueryAlarmFilter_T;

import org.apache.log4j.Logger;
import org.omg.CORBA.Any;

import performance.PMTPSelect_T;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.resource.proxy.CorbaAlarmProxy;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

import fileTransfer.FTPFileType_T;
import fileTransfer.FileTransferMgr_IPOA;
import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

public class FileTransferMgr_Impl extends FileTransferMgr_IPOA {
	private static Logger LOG = Logger.getLogger(FileTransferMgr_Impl.class.getName());
	private ICorbaSession userSession = null;

	public FileTransferMgr_Impl(ICorbaSession userSession) {
		// TODO Auto-generated constructor stub
		this.userSession = userSession;
	}
	
	@Override
	public void requireFileTransfer(FTPFileType_T fileType, String destination,
			String userName, String passWord, Any queryCondition)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

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
	
	@Override
	public void requireHistoryAlarmFileTransfer(String destination, String userName, String passWord,
													QueryAlarmFilter_T queryCondition)
											    		throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.ALARM_MANAGE);
		try {
			CorbaAlarmProxy proxy = new CorbaAlarmProxy(this.userSession);
			proxy.hisAlarmFileTransfer(destination, userName, passWord, queryCondition);
		} catch (Exception e) {
			LOG.error(e);
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "happens inner error");
		}
	}

	@Override
	public void requireHistoryPMFileTransfer(String destination,
			String userName, String passWord, PMTPSelect_T[] pmTPSelectList,
			String[] pmParameters, String startTime, String endTime,
			boolean forceUpload) throws ProcessingFailureException {
		// TODO Auto-generated method stub
		
	}

}
