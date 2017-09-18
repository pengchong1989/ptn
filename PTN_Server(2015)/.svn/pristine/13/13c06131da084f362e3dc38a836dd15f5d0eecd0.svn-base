package com.nms.corba.ninterface.impl.resource;

import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import clockSource.ClockLinkIterator_IHolder;
import clockSource.ClockLinkList_THolder;
import clockSource.ClockSourceList_THolder;
import clockSource.ClockSource_IPOA;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.resource.proxy.CorbaClockProxy;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

/**
 * 时钟源
 */
public class ClockSource_Impl extends ClockSource_IPOA {
	
	private ICorbaSession userSession = null;	//用户Session
	
	/**
	 * 创建一个实例
	 * @param userSession
	 */
	public ClockSource_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}
	
	/**
	 * 通过网元查询时钟源
	 *  @param meName 网元标识
	 * 	@param clockSourceList	时钟源信息集合
	 *  @throws ProcessingFailureException 
	 */
	@Override
	public void getMEClockSource(NameAndStringValue_T[] csName,
			ClockSourceList_THolder clockSourceList)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 配置查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.DEPLOY_SELECT);
		CorbaClockProxy corbaClockProxy = new CorbaClockProxy(this.userSession);
		corbaClockProxy.getMEClockSource(csName, clockSourceList);
	}

	@Override
	public void getAllClockLink(int how_many,
			ClockLinkList_THolder clockLinkList, ClockLinkIterator_IHolder clIt)
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
		//验证用户权限 无权限抛异常 配置管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.DEPLOY_MANAGE);
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
