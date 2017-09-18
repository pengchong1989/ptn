package com.nms.corba.ninterface.impl.performance;

import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesIterator_IHolder;
import globaldefs.NamingAttributesList_THolder;
import globaldefs.ProcessingFailureException;

import org.omg.CORBA.IntHolder;

import performance.HoldingTime_THolder;
import performance.PMCollectionTaskList_THolder;
import performance.PMCollectionTask_T;
import performance.PMCollectionTask_THolder;
import performance.PMDataIterator_IHolder;
import performance.PMDataList_THolder;
import performance.PMPIterator_IHolder;
import performance.PMPList_THolder;
import performance.PMParameterList_THolder;
import performance.PMTPSelectList_THolder;
import performance.PMTPSelect_T;
import performance.PerformanceManagementMgr_IPOA;
import performance.TCAParameterProfileIterator_IHolder;
import performance.TCAParameterProfileList_THolder;
import performance.TCAParameterProfile_THolder;
import performance.TCAParameter_T;
import performance.TCAParameters_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.util.AuthorityManager;
import com.nms.ui.ptn.safety.roleManage.RootFactory;
import common.CapabilityList_THolder;

public class PerformanceManagementMgr_Impl extends PerformanceManagementMgr_IPOA {
	private ICorbaSession userSession = null;

	public PerformanceManagementMgr_Impl(ICorbaSession userSession) {
		this.userSession = userSession;
	}
	/**
	 * NameAndStringValue_T idl文件没找到该对象
	 */
	@Override
	public void getMEPMcapabilities(NameAndStringValue_T[] meName,
			short layerRate, PMParameterList_THolder pmParameterList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void disablePMData(PMTPSelect_T[] pmTPSelectList,
			PMTPSelectList_THolder failedTPSelectList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void enablePMData(PMTPSelect_T[] pmTPSelectList,
			PMTPSelectList_THolder failedTPSelectList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}
	/**
	 * 根据指定条件，清空性能数据，重新计数: 武汉走适配，陈晓没做
	 */
	@Override
	public void clearPMData(PMTPSelect_T[] pmTPSelectList,
			PMTPSelectList_THolder failedTPSelectList)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 性能查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.PROFOR_MANAGE);
		PerformanceProxy performanceProxy = new PerformanceProxy(this.userSession);
		performanceProxy.clearPMData(pmTPSelectList, failedTPSelectList);

	}

	@Override
	public void getHoldingTime(HoldingTime_THolder holdingTime)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	/**
	 * @author guoqc
	 * 查询性能门限值
	 * 入参  tpName 厂商信息/网元信息
	 * 入参  layerRate 层速率
	 * 入参  granularity 时间粒度（15分钟、24小时、分钟/秒级） 
	 * 出参  tcaParameter 返回查询的结果值
	 */
	@Override
	public void getTCATPParameter(NameAndStringValue_T[] tpName, short layerRate,
									String granularity, TCAParameters_THolder tcaParameter)
										throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 性能查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_SELECT);
		PerformanceProxy performanceProxy = new PerformanceProxy(this.userSession);
		performanceProxy.getTCATPParameter(tpName, layerRate, granularity, tcaParameter);
	}

	/**
	 * @author guoqc
	 * 获取指定条件的历史性能值
	 * 入参 destination 查到的性能数据所要放到的目标文件中，包括路径和文件名
	 *    http://192.168.1.1:8080/temp/file.txt
	 * 入参 userName FTP上传到该文件的用户名
	 * 入参 password FTP上传到该文件的密码
	 * 入参 pmTPSelectList 此参数指定什么样的历史性能数据需要被返回，如为空，所有数据返回，包括各种速率，各种粒度
	 * 入参 pmParameters 只支持为空，即查所有
	 * 入参 startTime 起始时间
	 * 入参 endTime 结束时间
	 * 入参 forceUpload  网管是否从网元上传所有的性能数据 true/false 查询/不查询
	 */
	@Override
	public void getHistoryPMData(String destination, String userName, String password,
									PMTPSelect_T[] pmTPSelectList, String[] pmParameters,
										String startTime, String endTime, boolean forceUpload)
											throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 性能查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.PROFOR_SELECT);
		PerformanceProxy performanceProxy = new PerformanceProxy(this.userSession);
		performanceProxy.getHistoryPMData(destination, userName, password, pmTPSelectList,
											pmParameters, startTime, endTime, forceUpload);
	}

	/**
	 * @author guoqc
	 * 获取所有的当前性能值
	 * 入参 pmTPSelectList 查询条件对象数组
	 * 入参 pmParameters 只支持为空，即查所有
	 * 入参 how_many 个数
	 * 出参 pmDataList 性能值对象
	 * 出参 pmIt 迭代器
	 */
	@Override
	public void getAllCurrentPMData(PMTPSelect_T[] pmTPSelectList,String[] pmParameters, 
										int how_many, PMDataList_THolder pmDataList,
											PMDataIterator_IHolder pmIt) throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 性能查看
		AuthorityManager.checkAuthority(this.userSession, RootFactory.PROFOR_SELECT);
		PerformanceProxy performanceProxy = new PerformanceProxy(this.userSession);
		performanceProxy.getAllCurrentPMData(pmTPSelectList, pmParameters, how_many, pmDataList, pmIt);
	}

	/**
	 * @author guoqc
	 * 设置性能门限值
	 * 入参  tpName 厂商信息/网元信息
	 * 入参  tcaParameters 需要设置的性能门限值
	 */
	@Override
	public void setTCATPParameter(NameAndStringValue_T[] tpName,
									TCAParameters_THolder tcaParameters)
										throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 性能管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.CORE_MANAGE);
		PerformanceProxy performanceProxy = new PerformanceProxy(this.userSession);
		performanceProxy.setTCATPParameter(tpName, tcaParameters);
	}

	@Override
	public void enableTCA(PMTPSelect_T[] pmTPSelectList,
			PMTPSelectList_THolder failedTPSelectList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void disableTCA(PMTPSelect_T[] pmTPSelectList,
			PMTPSelectList_THolder failedTPSelectList)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getProfileAssociatedTPs(NameAndStringValue_T[] profileName,
			int how_many, NamingAttributesList_THolder tpNames,
			NamingAttributesIterator_IHolder nameIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void createTCAParameterProfile(
			NameAndStringValue_T[] managedElementName, short layerRate,
			String userLabel, boolean forceUniqueness, String owner,
			NameAndStringValue_T[] additionalInfo,
			TCAParameter_T[] listOfTCAParameter,
			TCAParameterProfile_THolder tcaParameterProfile)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteTCAParameterProfile(
			NameAndStringValue_T[] tcaParameterProfileName)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getTCAParameterProfile(
			NameAndStringValue_T[] tcaParameterProfileName,
			TCAParameterProfile_THolder tcaParameterProfile)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTCAParameterProfile(
			NameAndStringValue_T[] tcaParameterProfileName,
			TCAParameter_T[] listOfTCAParameter, int how_many,
			TCAParameterProfile_THolder tcaParameterProfile,
			NamingAttributesList_THolder failedTPList,
			NamingAttributesIterator_IHolder nameIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllTCAParameterProfileNames(NameAndStringValue_T[] meName,
			int how_many,
			NamingAttributesList_THolder tcaParameterProfileNames,
			NamingAttributesIterator_IHolder nameIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getTPHistoryPMData(PMTPSelect_T[] pmTPSelectList,
			String[] pmParameters, String startTime, String endTime,
			int how_many, PMDataList_THolder pmDataList,
			PMDataIterator_IHolder pmIt) throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllPMPs(NameAndStringValue_T[] tpOrMeName, int how_many,
			PMPList_THolder pmpList, PMPIterator_IHolder pmpIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllPMPNames(NameAndStringValue_T[] tpOrMeName, int how_many,
			NamingAttributesList_THolder nameList,
			NamingAttributesIterator_IHolder nameIt)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setTCAParameterProfilePointer(NameAndStringValue_T[] tpName,
			NameAndStringValue_T[] addTCAParameterProfile,
			NameAndStringValue_T[] removeTCAParameterProfile)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAllTCAParameterProfiles(NameAndStringValue_T[] meName,
			int how_many,
			TCAParameterProfileList_THolder tcaParameterProfileList,
			TCAParameterProfileIterator_IHolder tcaParameterProfileIt)
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
  
	/**
	 * function:创建性能任务
	 * @param pmCollectionTask
	 *                 创建性能任务的数据
	 *  @param intHolder
	 *                 返回给上一层的数据（默认是当前数据库组件ID）
	 * @throws ProcessingFailureException
	 */
	@Override
	public void createPMCollectionTask(PMCollectionTask_T pmCollectionTask, IntHolder intHolder)
			throws ProcessingFailureException {
		// TODO Auto-generated method stub
		//验证用户权限 无权限抛异常 性能管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.PROFOR_MANAGE);
		PerformanceProxy performanceProxy = new PerformanceProxy(this.userSession);
		performanceProxy.createPMCollectionTask(pmCollectionTask,intHolder);
		
	}

	/**
	 * function:删除性能任务
	 * @param id
	 *         删除性能任务的ID
	 * @throws ProcessingFailureException
	 */
	@Override
	public void deletePMCollectionTask(int id)throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 性能管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.PROFOR_MANAGE);
		PerformanceProxy performanceProxy = new PerformanceProxy(this.userSession);
		performanceProxy.deletePMCollectionTask(id);
	}

	/**
	 * function:查询所有的性能任务
	 * @param pmCollectionTaskList
	 *                 返回给上一层的数据
	 * @throws ProcessingFailureException
	 */
	@Override
	public void getAllPMCollectionTasks(PMCollectionTaskList_THolder pmCollectionTaskList)throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 性能管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.PROFOR_SELECT);
		PerformanceProxy performanceProxy = new PerformanceProxy(this.userSession);
		performanceProxy.getAllPMCollectionTasks(pmCollectionTaskList);
		
	}
	
	/**
	 * function:查询指定的性能任务
	 * @param id
	 *              查询指定性能任务的条件
	 * @param   PMCollectionTask_THolder
	 *             返回给上一层的数据
	 * @throws ProcessingFailureException
	 */
	@Override
	public void getPMCollectionTask(int id, PMCollectionTask_THolder pmCollectionTask_THolder)throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 性能管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.PROFOR_SELECT);
		PerformanceProxy performanceProxy = new PerformanceProxy(this.userSession);
	    performanceProxy.getPMCollectionTask(id,pmCollectionTask_THolder);
	}

	/**
	 * function:修改指定的性能任务
	 * @param id
	 *              查询指定性能任务的条件
	 * @param   pmCollectionTask
	 *             所要修改的新内容
	 * @throws ProcessingFailureException
	 */
	@Override
	public void modifyPMCollectionTask(int id, PMCollectionTask_T pmCollectionTask)
			throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 性能管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.PROFOR_MANAGE);
		PerformanceProxy performanceProxy = new PerformanceProxy(this.userSession);
	    performanceProxy.modifyPMCollectionTask(id,pmCollectionTask);
		
	}
	
	/**
	 * function:恢复指定的性能任务采集
	 * @param id
	 *              查询指定性能任务的条件
	 * @throws ProcessingFailureException
	 */
	@Override
	public void resumePMCollectionTask(int id)throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 性能管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.PROFOR_MANAGE);		
		PerformanceProxy performanceProxy = new PerformanceProxy(this.userSession);
		performanceProxy.resumePMCollectionTask(id);
	}

	/**
	 * function:暂停指定的性能任务采集
	 * @param id
	 *              查询指定性能任务的条件
	 * @throws ProcessingFailureException
	 */
	@Override
	public void suspendPMCollectionTask(int id)throws ProcessingFailureException {
		//验证用户权限 无权限抛异常 性能管理
		AuthorityManager.checkAuthority(this.userSession, RootFactory.PROFOR_MANAGE);		
		PerformanceProxy performanceProxy = new PerformanceProxy(this.userSession);
		performanceProxy.suspendPMCollectionTask(id);
	}

}
