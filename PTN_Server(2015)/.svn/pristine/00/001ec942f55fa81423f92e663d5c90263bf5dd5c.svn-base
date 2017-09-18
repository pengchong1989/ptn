package com.nms.corba.ninterface.impl.service;

import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import maintenanceOps.CurrentMaintenanceOperationIterator_IHolder;
import maintenanceOps.CurrentMaintenanceOperationList_THolder;
import maintenanceOps.CurrentMaintenanceOperation_T;
import maintenanceOps.MEGCreateData_T;
import maintenanceOps.MEGList_THolder;
import maintenanceOps.MEG_THolder;
import maintenanceOps.MaintenanceMgr_IPOA;
import maintenanceOps.MaintenanceOperationMode_T;
import maintenanceOps.OAMLinkLBResult_THolder;

import org.apache.log4j.Logger;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.resource.proxy.TestOamProxy;
import com.nms.corba.ninterface.impl.service.proxy.CorbaMaintenanceProxy;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

public class MaintenanceMgr_Impl extends MaintenanceMgr_IPOA {
	private Logger LOG = Logger.getLogger(MaintenanceMgr_Impl.class.getName());
	private ICorbaSession userSession = null;

	public MaintenanceMgr_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}
	
	@Override
	public void performMaintenanceOperation(
			CurrentMaintenanceOperation_T maintenanceOperation,
			MaintenanceOperationMode_T maintenanceOperationMode)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
	}

	@Override
	public void getActiveMaintenanceOperations(
			NameAndStringValue_T[] tpOrMeName,
			int how_many,
			CurrentMaintenanceOperationList_THolder currentMaintenanceOperationList,
			CurrentMaintenanceOperationIterator_IHolder cmoIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub
		//验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
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
	
	/**
	 *@author guoqc 
	 *主动OAM : 使能/禁止CC联通性功能，含段，LSP，PW等
	 *按需OAM : 使能/禁止锁定功能（LCK）
	 *主动OAM : 启动以太网业务OAM性能测量功能
	 *入参  @param objectName : name=EMS value=”DataX”
				 			  name=”主键id”  value=”主键id”
	 *入参  @param additionalInfo
	 *              业务类型,需要操作的功能
	 *@throw ProcessingFailureException
	 */
	@Override
	public void setAdditionalInfo(NameAndStringValue_T[] objectName,
								 	NVSList_THolder additionalInfo) 
										throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
    	CorbaMaintenanceProxy maintenanceMgr = new CorbaMaintenanceProxy();
		maintenanceMgr.setAdditionalInfo(objectName, additionalInfo);
	}
	
	/**
	 * function:创建一条OAM 包含:(段/pw/tunnel层的OAM)
	 * @param paramMEGCreateData_T
	 *             创建OAM 包含:(段/pw/tunnel层的OAM)的所有信息
	 * @param paramNVSList_THolder
	 *             需要返回的数据（OAM的信息）
	 *  @throw ProcessingFailureException
	 *           
	 */
	@Override
	public void createMEG(MEGCreateData_T createData, NVSList_THolder megName)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaMaintenanceProxy maintenanceMgr = null;
        try {
      	  maintenanceMgr = new CorbaMaintenanceProxy();
      	  if(4 == createData.megType){
      		maintenanceMgr.createETHOAM(createData, megName);
      	  }else{
      		maintenanceMgr.createMEG(createData, megName);
      	  }
		} catch (ProcessingFailureException e) {
			throw e;
		}finally{
			maintenanceMgr = null;
		}
	}

	/**
	 * function:查询一条OAM 包含:(段/pw/tunnel层的OAM)
	 * @param paramArrayOfNameAndStringValue_T
	 *             查询OAM所需要的信息 包含:(段/pw/tunnel层的OAM)的所有信息
	 * @param paramMEG_THolder
	 *             需要返回的数据（OAM的信息）
	 *  @throw ProcessingFailureException
	 *           
	 */
	@Override
	public void getMEG(NameAndStringValue_T[] megName, MEG_THolder meg)throws ProcessingFailureException {
		
		//验证用户权限 无权限抛异常 业务查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		CorbaMaintenanceProxy maintenanceMgr = null;
		
        try {
	      	maintenanceMgr = new CorbaMaintenanceProxy();
			maintenanceMgr.getMEG(megName, meg);
		} catch (ProcessingFailureException e) {
			throw e;
		}finally{
			maintenanceMgr = null;
		}
	}

	/**
	 * @author guoqc
	 * 启动LB功能(按需OAM)
	 * 入参  megName 厂商信息/主键id
	 * 入参  destMEP 暂时不用此参数
	 * 入参  lbType  暂时不用此参数
	 * 入参  enableLB  是否使能LB 如使能需填写paramArrayOfNameAndStringValue_T3，不使能不需填写
	 * 入参  additionalInfo 环回帧周期，离线TLV类型，环回测试方式，环回TLV长度，TLV测试内容
	 * 出参  lbResult 以上输入参数的返回结果
	 */
	@Override
	public void setMEGLB(NameAndStringValue_T[] megName, NameAndStringValue_T[] destMEP,
							int lbType, int enableLB, NameAndStringValue_T[] additionalInfo,
								NVSList_THolder lbResult)throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		TestOamProxy testOamProxy = new TestOamProxy();
		testOamProxy.setMEGLB(megName, enableLB, additionalInfo, lbResult);
	}

	/**
	 * @author guoqc
	 * 以太网OAM链路发现功能
	 * 入参  portName  厂商信息/主键id
	 * 入参  discoveryMode  工作模式（1=主动，2=被动）
	 * 入参  enableDiscovery  OAM协议是否使能 1=使能，0=未使能
	 */
	@Override
	public void setOAMLinkDiscovery(NameAndStringValue_T[] portName,
										int discoveryMode, int enableDiscovery)
			   								throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaMaintenanceProxy maintenanceMgr = new CorbaMaintenanceProxy();
  	  	maintenanceMgr.setOAMLinkDiscovery(portName, discoveryMode, enableDiscovery);
	}

	/**
	 * @author guoqc
	 * 以太网OAM链路环回
	 * 入参  portName 端口标识符
	 * 入参  linkName  
	 * 入参  enableLB  是否支持远端环回 1=是 0=否
	 * 出参  oamlinkLBResult  返回以上结果
	 */
	@Override
	public void setOAMLinkLB(NameAndStringValue_T[] portName,
								NameAndStringValue_T[] linkName, int enableLB,
									OAMLinkLBResult_THolder oamlinkLBResult)
							 			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		CorbaMaintenanceProxy maintenanceMgr = new CorbaMaintenanceProxy();
  	  	maintenanceMgr.setOAMLinkLB(portName, linkName, enableLB, oamlinkLBResult);
	}

	/**
	 * @author guoqc
	 * 启动OAM性能测量功能(按需OAM)
	 * 入参  megName  厂商信息/主键id
	 * 入参  collectParam LM发送使能，如使能，LM发送周期1s，100ms,DM发送使能，如使能，DM发送周期1s，100ms
	 * 入参  enableCollection 
	 * 出参  collectResult 以上输入参数的返回结果
	 */
	@Override
	public void setMEGPerfCollection(NameAndStringValue_T[] megName,
				NameAndStringValue_T[] collectParam, 
				NVSList_THolder collectResult)
				throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 业务管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		TestOamProxy testOamProxy = new TestOamProxy();
		testOamProxy.setMEGPerfCollection(megName, collectParam, collectResult);
	}

	@Override
	public void getAllMEG(NameAndStringValue_T[] meName, int[] megTypeList,
			MEGList_THolder megList) throws ProcessingFailureException {
		// TODO Auto-generated method stub
		
	}
}
