package com.nms.corba.ninterface.impl.performance;

import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.omg.CORBA.IntHolder;

import performance.PMCollectionTaskList_THolder;
import performance.PMCollectionTask_T;
import performance.PMCollectionTask_THolder;
import performance.PMDataIterator_IHolder;
import performance.PMDataList_THolder;
import performance.PMData_T;
import performance.PMTPSelectList_THolder;
import performance.PMTPSelect_T;
import performance.PMThresholdType_T;
import performance.PMThresholdValue_T;
import performance.TCAParameters_T;
import performance.TCAParameters_THolder;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.notification.CorbaNotifyReport;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.corba.ninterface.impl.util.StringUtil;
import com.nms.corba.ninterface.util.DateTimeUtil;
import com.nms.corba.ninterface.util.FileTools;
import com.nms.corba.ninterface.util.FtpUtil;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.perform.Capability;
import com.nms.db.bean.perform.CurrentPerforInfo;
import com.nms.db.bean.perform.HisPerformanceInfo;
import com.nms.db.bean.perform.PerformanceTaskInfo;
import com.nms.db.bean.perform.PmValueLimiteInfo;
import com.nms.db.enums.EMonitorCycle;
import com.nms.db.enums.EObjectType;
import com.nms.db.enums.ERunStates;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.perform.CapabilityService_MB;
import com.nms.model.perform.HisPerformanceService_Mb;
import com.nms.model.perform.PerformanceTaskService_MB;
import com.nms.model.perform.PmLimiteService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.PerformanceDispatch;
import com.nms.service.impl.dispatch.PmLimiteDispatch;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.performance.controller.PerformanceTimerTask;
import com.nms.ui.ptn.performance.model.CurrentPerformanceFilter;

public class PerformanceProxy {
	private Logger LOG = Logger.getLogger(PerformanceProxy.class.getName());
	private ICorbaSession session;
	
	public PerformanceProxy(ICorbaSession userSession) {
		this.session = userSession;
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
	public void getAllCurrentPMData(PMTPSelect_T[] pmTPSelectList, String[] pmParameters,
									  int howMany, PMDataList_THolder pmDataList,
										PMDataIterator_IHolder pmIt)  throws ProcessingFailureException{
		if(howMany==0){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "howMany is 0!");
		}
		List<CurrentPerforInfo> currPerformList=new ArrayList<CurrentPerforInfo>();;
		CurrentPerformanceFilter filter = null;
		PerformanceTool performanceTool=new PerformanceTool();
		List<CorbaPerformance> perforList=null;
		List<SiteInst> siteInstList=null;
		List<Integer> siteIds=null;
		SiteService_MB ss=null;
		String strType ="";
		try {	
			ss = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			//将Corba传人参数转为过滤对象的集合
			perforList=performanceTool.getPerformance(pmTPSelectList, null, null);
			for(CorbaPerformance info:perforList){	//循环集合，循环查询当前性能				
				siteIds=new ArrayList<Integer>();
				filter = new CurrentPerformanceFilter();
				if(info.getSiteId()==-1){//查询所有网元
					siteInstList = ss.select();
					if(UiUtil.isNull(siteInstList)){
						for(SiteInst siteInst: siteInstList){
							siteIds.add(siteInst.getSite_Inst_Id());
						}							
					}						
				}else{//根据给定网元，查找当前性能
					siteIds.add(info.getSiteId());
				}
				
				filter.setSiteInsts(siteIds);
				filter.setObjectType(EObjectType.SITEINST);//暂时只支持网元查询
				filter.setObjectName(info.getPortName());
				strType = performanceTool.getPerformType(filter.getCapabilityIdList(), filter.getCapabilityNameList());
				filter.setTypeStr(strType);
				if(UiUtil.isNull(info.getLayerList())){
					if(info.getLayerList().contains(EMonitorCycle.MIN15.getValue())){
						filter.setMonitorCycle(EMonitorCycle.MIN15);
						currPerformList.addAll( this.getCurrentPerformance(filter));					
					}
					if(info.getLayerList().contains(EMonitorCycle.HOUR24.getValue())){
						filter.setMonitorCycle(EMonitorCycle.HOUR24);
						currPerformList.addAll( this.getCurrentPerformance(filter));						
					}
				}				
			}
			//数据转换
			if(currPerformList.size() > 0){
				pmDataList.value = new PMData_T[currPerformList.size()];			
				performanceTool.convertAllPerf2Corba(currPerformList, pmDataList.value);							
			}
			//迭代
			PMDataIterator_Impl iter = new PMDataIterator_Impl(this.session);
			iter.setIterator(pmIt, pmDataList, howMany);
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : getAllCurrentPMData.ex!");
		} finally {
			UiUtil.closeService_MB(ss);
		}
	}
	
	/**
	 * 查询设备性能值并封装到性能对象中
	 * @param filter
	 * @return cureenPerformanceFilterList
	 */
	private List<CurrentPerforInfo> getCurrentPerformance(CurrentPerformanceFilter filter)throws ProcessingFailureException {
		List<CurrentPerforInfo> currPerformList = new ArrayList<CurrentPerforInfo>();
		try {
			List<CurrentPerforInfo> queryCurrPerformList = this.queryPerforByFilter(filter);
			String[] filterTypeArray = filter.getTypeStr().trim().split(",");
			if(queryCurrPerformList.size() > 0){
				for (CurrentPerforInfo currPerform : queryCurrPerformList) {
					if (currPerform.getCapability() != null && currPerform.getObjectName() != null) {
						Capability capa = currPerform.getCapability();
						for (int i = 0; i < filterTypeArray.length; i++) {
							if (filterTypeArray[i].equals(capa.getCapabilitytype())){
								for(String strType : filter.getCapabilityNameList()){
									if(capa.getCapabilitydesc().equalsIgnoreCase(strType)) {
										currPerformList.add(currPerform);
									}
								}
							}
						}
					}
				}
			}
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
		return currPerformList;
	}
	/**
	 * 根据过滤条件,查询设备性能值
	 * @param filter 
	 * @return
	 * @throws Exception
	 */
	private List<CurrentPerforInfo> queryPerforByFilter(CurrentPerformanceFilter filter) throws ProcessingFailureException{
		// key为网元数据库id，value为槽位的集合
		List<CurrentPerforInfo> currPerformList = new ArrayList<CurrentPerforInfo>();
		try {
			PerformanceDispatch dispatch = new PerformanceDispatch();
			List<CurrentPerforInfo> queryPerforList = dispatch.executeQueryCurrPerforByFilter(filter); 
			if (filter.getObjectType() != null && filter.getObjectType() == EObjectType.SLOTINST) {
				// 设备只支持网元的性能查询，所以查询后的性能数据根据槽位过滤
				if (queryPerforList.size() > 0) {
					for (int i = 0; i < filter.getSlotInsts().size(); i++) {
						int slotInstId = filter.getSlotInsts().get(i);
						for (CurrentPerforInfo info : queryPerforList) {
							if (info.getMasterCardAddress() == slotInstId) {
								currPerformList.add(info);
							}
						}
					}
				}
			} else {
				if (queryPerforList.size() > 0) {
					if(UiUtil.isNull(filter.getObjectName())){//查询出当前性能后，设置端口的过滤
						for(CurrentPerforInfo info:queryPerforList){
							if(info.getObjectName().equals(filter.getObjectName())){
								currPerformList.add(info);
							}
						}
					}else{//没有端口设置条件，根据网元，查当前性能
						currPerformList.addAll(queryPerforList);
					}
					
				}
			}
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
		return currPerformList;
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
	 * @throws ProcessingFailureException 
	 */
	public void getHistoryPMData(String destination, String userName, String password,
									PMTPSelect_T[] pmTPSelectList, String[] pmParameters,
										String startTime, String endTime, boolean forceUpload) throws ProcessingFailureException {
		HisPerformanceService_Mb hisPerService=null;
		try {
			PerformanceTool performanceTool=new PerformanceTool();
			List<CorbaPerformance> perforList=null;
			List<HisPerformanceInfo> hisPerformanceFilterList=new ArrayList<HisPerformanceInfo>();
			if(pmTPSelectList != null && forceUpload == Boolean.TRUE){
				hisPerService=(HisPerformanceService_Mb) ConstantUtil.serviceFactory.newService_MB(Services.HisPerformance);
				perforList=performanceTool.getPerformance(pmTPSelectList, startTime, endTime);
				for(CorbaPerformance info:perforList){
					hisPerformanceFilterList.addAll(hisPerService.queryByCorbaPerfor(info));
				}

				//查询到的历史性能数据，下载到本地，并且通过FTP上传到FTP
				this.HisPMFileTranfer(destination, userName, password, hisPerformanceFilterList);
				
			}
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}finally {
			UiUtil.closeService_MB(hisPerService);
		}
	}

	/**
	 * function:查询所有的性能任务
	 * @param pmCollectionTaskList
	 *                 返回给上一层的数据
	 * @throws ProcessingFailureException
	 */
	public void getAllPMCollectionTasks(PMCollectionTaskList_THolder pmCollectionTaskList)throws ProcessingFailureException{
		try {
			if(pmCollectionTaskList == null){
				pmCollectionTaskList = new PMCollectionTaskList_THolder();
			}
			pmCollectionTaskList.value = getPmCollectionTask(0) ;
		} catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
	}
	
	/**
	 * function:查询指定的性能任务
	 * @param id
	 *              查询指定性能任务的条件
	 * @param   PMCollectionTask_THolder
	 *             返回给上一层的数据
	 * @throws ProcessingFailureException
	 */
	public void getPMCollectionTask(int id, PMCollectionTask_THolder pmCollectionTaskList)throws ProcessingFailureException{
		try {
			if(pmCollectionTaskList == null){
				pmCollectionTaskList = new PMCollectionTask_THolder();
			}
			pmCollectionTaskList.value = getPmCollectionTask(id)[0] ;
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
	}
	
	/**
	 * @param id 对应的数据库主键ID  id==0 查询所有的性能任务 否则根据条件来查询任务
	 * @return
	 * @throws Exception
	 */
		
	private PMCollectionTask_T[] getPmCollectionTask(int id) throws ProcessingFailureException{
		PerformanceTaskService_MB taskService = null;
		List<PerformanceTaskInfo> performanceTaskInfoList = null;
		PMCollectionTask_T[] pmCollectionTask = null;
		PerformanceTaskInfo performanceTaskInfo = null;
		PerformanceTool performanceTool=new PerformanceTool();
		try {
			taskService = (PerformanceTaskService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PerformanceTask);
			performanceTaskInfoList = new ArrayList<PerformanceTaskInfo>();
			if(id == 0){
				performanceTaskInfoList = taskService.select();
			}else{
				performanceTaskInfo = new PerformanceTaskInfo();
				performanceTaskInfo.setId(id);
				performanceTaskInfoList = taskService.select(performanceTaskInfo);
			}
			//将查询的所有性能任务传递给上一层
			if(null != performanceTaskInfoList && performanceTaskInfoList.size()>0){
				pmCollectionTask = new PMCollectionTask_T[performanceTaskInfoList.size()];
				for(int i=0 ;i<performanceTaskInfoList.size();i++){
					performanceTaskInfo = performanceTaskInfoList.get(i);
					pmCollectionTask[i] = performanceTool.converPMCllectionTask_T(performanceTaskInfo);
				}
			}else{//没有性能任务数据，返回默认值
				pmCollectionTask=new PMCollectionTask_T[0];
			}
		} catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		
		}finally{
			UiUtil.closeService_MB(taskService);
			performanceTaskInfoList = null;
			performanceTaskInfo = null;
		}
		return pmCollectionTask;
	}
	
	
	/**
	 * function:创建性能任务
	 * @param pmCollectionTask
	 *                 创建性能任务的数据
	 *  @param intHolder
	 *                 返回给上一层的数据（默认是当前数据库组件ID）
	 * @throws ProcessingFailureException
	 */
	public void  createPMCollectionTask(PMCollectionTask_T pmCollectionTask, IntHolder intHolder)throws ProcessingFailureException{
		PerformanceTaskService_MB taskService = null;
		PerformanceTaskInfo performanceTaskInfo = null;
		int id = 0;
		PerformanceTimerTask task = null;
		try {
			taskService = (PerformanceTaskService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PerformanceTask);
			performanceTaskInfo = new PerformanceTaskInfo();
			performanceTaskInfo.setCreater(this.session.getUserName());
			getPerformanceFromCorba(performanceTaskInfo,pmCollectionTask);
			performanceTaskInfo.setRunstates(ERunStates.RUN);
			id = taskService.saveOrUpdate(performanceTaskInfo);
			performanceTaskInfo.setId(id);
			// 创建定时任务
			task = new PerformanceTimerTask(performanceTaskInfo);
//			PerforTaskThreadFactory.getInstance().createPerforTaskThread(task, performanceTaskInfo.getMonitorCycle().getInterval());
//			PerforTaskThreadFactory.getInstance().createPerforTaskThread();
			//创建成功
			intHolder.value = id ;
			
		}  catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : createPMCollectionTask.ex!");
		
		}finally{
			UiUtil.closeService_MB(taskService);
			 performanceTaskInfo = null;
			 task = null;
		}
	}
	
	/**
	 * function:删除性能任务
	 * @param id
	 *         删除性能任务的ID
	 * @throws ProcessingFailureException
	 */
   public void  deletePMCollectionTask(int id)throws ProcessingFailureException{
	    PerformanceTaskService_MB taskService = null;
		List<PerformanceTaskInfo> performanceTaskInfoList = null;
		PerformanceTaskInfo performanceTaskInfo = null;
		List<Integer> idList = null; 
		try {
			if(id==0){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "id is 0!");
			}
			taskService = (PerformanceTaskService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PerformanceTask);
			performanceTaskInfoList = new ArrayList<PerformanceTaskInfo>();
			performanceTaskInfo = new PerformanceTaskInfo();
			performanceTaskInfo.setId(id);
			performanceTaskInfoList = taskService.select(performanceTaskInfo);
				
			if(null !=performanceTaskInfoList && performanceTaskInfoList.size()>0){
				idList = new ArrayList<Integer>();
				performanceTaskInfo = performanceTaskInfoList.get(0);
				
//				if(PerforTaskThreadFactory.getInstance().getPerformanceTaskThread(performanceTaskInfo.getThreadName())!=null){
//					PerforTaskThreadFactory.getInstance().stopPerformanceThread(performanceTaskInfo.getThreadName());
//				}
//				if(PerforTaskThreadFactory.getInstance().getTaskThread(performanceTaskInfo.getThreadName())!=null){
//					PerforTaskThreadFactory.getInstance().stop(performanceTaskInfo.getThreadName());
//				}
				idList.add(Integer.valueOf(performanceTaskInfo.getId()));
				// 删除数据库中长期性能任务
				taskService.delete(idList);
			}
		}   catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : deletePMCollectionTask.ex!");
		
		}finally{
			UiUtil.closeService_MB(taskService);
			 performanceTaskInfoList = null;
			 performanceTaskInfo = null;
			 idList = null; 
		}
	}
	   
   /**
	 * function:修改指定的性能任务
	 * @param id
	 *              查询指定性能任务的条件
	 * @param   pmCollectionTask
	 *             所要修改的新内容
	 * @throws ProcessingFailureException
	 */
   public void modifyPMCollectionTask(int id, PMCollectionTask_T pmCollectionTask)throws ProcessingFailureException{
	   
	   PerformanceTaskService_MB taskService = null;
	   PerformanceTaskInfo performanceTaskInfo = null;
	   List<PerformanceTaskInfo> performanceTaskInfoList = null;
	   String threadName = "";
	   PerformanceTimerTask task = null;
	   
		try {
			if(id==0){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "id is 0!");
			}
			//验证采集任务的名称
			if(!CheckParameterUtil.checkPMTaskName(pmCollectionTask.taskName)){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "taskName is invalid!");
			}
			//验证 采集任务参数：  网元名称
			if(pmCollectionTask.name==null||pmCollectionTask.name.name==null||!CheckParameterUtil.checkMeName(pmCollectionTask.name.name)){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "taskName is invalid!");
			}
			if(null != pmCollectionTask){				
				taskService = (PerformanceTaskService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PerformanceTask);
				performanceTaskInfo = new PerformanceTaskInfo();
				performanceTaskInfo.setId(id);
				performanceTaskInfoList = new ArrayList<PerformanceTaskInfo>();
				performanceTaskInfoList = taskService.select(performanceTaskInfo);
				
				if(UiUtil.isNull(performanceTaskInfoList) ){
					performanceTaskInfo = performanceTaskInfoList.get(0);
					threadName = performanceTaskInfo.getThreadName();
					getPerformanceFromCorba(performanceTaskInfo,pmCollectionTask);
					performanceTaskInfo.setRunstates(ERunStates.RUN);
					//修改 先将之前的线程给停掉 在重新启动一个新线程来运行这个任务
//					if(PerforTaskThreadFactory.getInstance().getTaskThread(threadName)!=null){
//						PerforTaskThreadFactory.getInstance().stop(threadName);
//					}
					// 保存长期性能任务
					taskService.saveOrUpdate(performanceTaskInfo);
					// 创建定时任务
//					task = new PerformanceTimerTask(performanceTaskInfo);
//					PerforTaskThreadFactory.getInstance().createPerforTaskThread(task, performanceTaskInfo.getMonitorCycle().getInterval());
				}
			}
		}  catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : deletePMCollectionTask.ex!");
		
		}finally{
			UiUtil.closeService_MB(taskService);
		   performanceTaskInfo = null;
		   performanceTaskInfoList = null;
		   threadName = "";
		   task = null;
		}
	}
   
   /**
	 * function:恢复指定的性能任务采集
	 * @param id
	 *              查询指定性能任务的条件
	 * @throws ProcessingFailureException
	 */
   public void resumePMCollectionTask(int id)throws ProcessingFailureException{
	   PerformanceTaskService_MB taskService = null;
	   PerformanceTaskInfo performanceTaskInfo = null;
	   PerformanceTimerTask task = null;
	   List<PerformanceTaskInfo> performanceTaskInfoList = null;
		try {
			if(id==0){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "id is 0!");
			}
			taskService = (PerformanceTaskService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PerformanceTask);
			performanceTaskInfo = new PerformanceTaskInfo();
			performanceTaskInfo.setId(id);
			performanceTaskInfoList = new ArrayList<PerformanceTaskInfo>();
			performanceTaskInfoList = taskService.select(performanceTaskInfo);
			//启动一个线程来采集数据
			if(null != performanceTaskInfoList && performanceTaskInfoList.size()>0){
				performanceTaskInfo = performanceTaskInfoList.get(0);
				performanceTaskInfo.setRunstates(ERunStates.RUN);
				taskService.saveOrUpdate(performanceTaskInfo);
				// 创建定时任务
//				task = new PerformanceTimerTask(performanceTaskInfo);
//				PerforTaskThreadFactory.getInstance().createPerforTaskThread(task, performanceTaskInfo.getMonitorCycle().getInterval());
			}
			
		}   catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : deletePMCollectionTask.ex!");
		
		}finally{
		  UiUtil.closeService_MB(taskService);
		  performanceTaskInfo = null;
		  performanceTaskInfoList = null;
		  task = null;
		}
	}
   
	/**
	 * function:暂停指定的性能任务采集
	 * @param id
	 *              查询指定性能任务的条件
	 * @throws ProcessingFailureException
	 */
   public void suspendPMCollectionTask(int id)throws ProcessingFailureException{
	   
	   PerformanceTaskService_MB taskService = null;
	   PerformanceTaskInfo performanceTaskInfo = null;
	   List<PerformanceTaskInfo> performanceTaskInfoList = null;
		try {
			if(id==0){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "id is 0!");
			}
			taskService = (PerformanceTaskService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PerformanceTask);
			performanceTaskInfo = new PerformanceTaskInfo();
			performanceTaskInfo.setId(id);
			performanceTaskInfoList = new ArrayList<PerformanceTaskInfo>();
			performanceTaskInfoList = taskService.select(performanceTaskInfo);
			
			if(null != performanceTaskInfoList && performanceTaskInfoList.size()>0){
				performanceTaskInfo = performanceTaskInfoList.get(0);
				//暂停任务 并同时跟新数据库数据
//				if(PerforTaskThreadFactory.getInstance().getTaskThread(performanceTaskInfo.getThreadName())!= null){
//					PerforTaskThreadFactory.getInstance().stop(performanceTaskInfo.getThreadName());
//				}
				performanceTaskInfo.setRunstates(ERunStates.HANG);
				taskService.saveOrUpdate(performanceTaskInfo);
			}
			
		} catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : deletePMCollectionTask.ex!");
		
		}finally{
			UiUtil.closeService_MB(taskService);
			performanceTaskInfo = null;
		 	performanceTaskInfoList = null;
		}
	}
   
   /**
	 * function:将corba中性能对象转变成 网管性能对象
	 * @param performanceTaskInfo
	 *              网管性能对象
	 * @param  pmCollectionTask
	 *              corba中性能对象
	 * @throws ProcessingFailureException
	 */
   private void getPerformanceFromCorba( PerformanceTaskInfo performanceTaskInfo ,PMCollectionTask_T pmCollectionTask) throws ProcessingFailureException{
	   String performanceType = "" ;
	   SiteInst siteInfo = null;
	   List<Capability> perforTypeList = null;
	   DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	   if(UiUtil.isNull(pmCollectionTask.beginTime)&&UiUtil.isNull(pmCollectionTask.endTime)){//如果开始时间，结束时间都有值，判断开始时间在结束时间之前
		   if (DateTimeUtil.compare_date(pmCollectionTask.beginTime, pmCollectionTask.endTime) != 1) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,
						"startTime should be less than endTime");
			}
	   }else if(!UiUtil.isNull(pmCollectionTask.beginTime)&&UiUtil.isNull(pmCollectionTask.endTime)){//若果开始为空，结束不为空
		   if (DateTimeUtil.compare_date(df.format(new Date()), pmCollectionTask.endTime) != 1) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,
						"startTime should be less than endTime");
			}
	   }
	   try {
		     //任务名称
			performanceTaskInfo.setTaskName(pmCollectionTask.taskName[1].value);
			//线程名称
			performanceTaskInfo.setThreadName("Thread_" + performanceTaskInfo.getTaskName());
			// 开始时间
			if(UiUtil.isNull(pmCollectionTask.beginTime)){//如果输入开始时间为空，则 默认当前时间为开始时间
				performanceTaskInfo.setCreateTime( DateTimeUtil.dateFormat(pmCollectionTask.beginTime));
			}else{
				performanceTaskInfo.setCreateTime( DateUtil.getDate(DateUtil.FULLTIME));
			}			
			// 结束时间
			performanceTaskInfo.setEndTime(DateTimeUtil.dateFormat(pmCollectionTask.endTime));
			//性能周期
			performanceTaskInfo.setMonitorCycle(EMonitorCycle.forms(Long.parseLong(pmCollectionTask.reportInterval)));
			//性能参数
			if(pmCollectionTask.pMParameterList.length==0){
				perforTypeList=this.getPerformanceType();
				if(UiUtil.isNull(perforTypeList)){
					for(Capability capa:perforTypeList){
						performanceType+=capa.getCapabilitytype()+",";
					}
				}
			}else{
				for(int j = 0; j < pmCollectionTask.pMParameterList.length; j++){
					performanceType += pmCollectionTask.pMParameterList[j]+",";
				}
			}
			
			performanceTaskInfo.setPerforType(performanceType.substring(0, performanceType.length()-1));
			//网元对象
			siteInfo = new SiteInst();
			siteInfo.setSite_Inst_Id(Integer.parseInt(pmCollectionTask.name.name[1].value));
			performanceTaskInfo.setSiteInst(siteInfo);
			//网元级别
			performanceTaskInfo.setObjectType(EObjectType.SITEINST);
			//监控当前15分钟的数据性能
			if(Long.parseLong(pmCollectionTask.reportInterval)==EMonitorCycle.MIN15.getInterval()){
				performanceTaskInfo.setPerformanceType(0);
				performanceTaskInfo.setPerformanceCount(0);
				performanceTaskInfo.setPerformanceBeginCount(0);
			}
			//监控当前24分钟的数据性能
			else{
				performanceTaskInfo.setPerformanceCount(255);
				performanceTaskInfo.setPerformanceBeginCount(0);
				performanceTaskInfo.setPerformanceType(32);
			}
	}  catch(ProcessingFailureException e){
		throw e;
	}
	catch(Exception e){
		throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : deletePMCollectionTask.ex!");
	
	}finally{
		   performanceType = "" ;
		    siteInfo = null;
	}
  }		
		/*
		 * 测试查询当前性能
		 */
		// 初始武汉驱动
//		ConstantUtil.driveService = new DriveService();
//		ConstantUtil.driveService.setAlarmObjectService(ConstantUtil.alarmObjectService);
//		try {
//			ConstantUtil.driveService.init();
//		} catch (Exception e) {
//			
//			ExceptionManage.dispose(e,this.getClass());
//		}
//		new PerformanceProxy(new ICorbaSession()).getAllCurrentPMData(pm, pmParameters, 10, null, null);
	
	/**
	 * @author guoqc
	 * 查询性能门限值
	 * 入参  tpName 厂商信息/网元信息
	 * 入参  layerRate 层速率
	 * 入参  granularity 时间粒度（15分钟、24小时、分钟/秒级） 
	 * 出参  tcaParameter 返回查询的结果值
	 */
	public void getTCATPParameter(NameAndStringValue_T[] tpName,
									short layerRate, String granularity,
										TCAParameters_THolder tcaParameter)throws  ProcessingFailureException{
			
		//验证输入名称
		if(!CheckParameterUtil.checkMeName(tpName)){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"tpName is invalid!");
		}
		int siteId = Integer.parseInt(tpName[1].value);	
		if(siteId<=0){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"tpName is invalid!");
		}
		PmValueLimiteInfo pmLimite = this.getPmValueLimiteInfoBySiteId(siteId);
		if(!UiUtil.isNull(pmLimite)){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"no find data!");
		}
		this.convertPMLimite2Corba(pmLimite, tcaParameter);	
	}

	/**
	 * 通过网元id获取性能门限值
	 */
	private PmValueLimiteInfo getPmValueLimiteInfoBySiteId(int siteId)throws  ProcessingFailureException{
		PmValueLimiteInfo pmLimiteInfo = new PmValueLimiteInfo();
		PmLimiteService_MB pmService = null;
		try {
			pmService = (PmLimiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PmLimiteService);
			pmLimiteInfo.setSiteId(siteId);
			pmLimiteInfo = pmService.select(pmLimiteInfo);
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"Interval error: The process has internal error!");
		}finally{
			UiUtil.closeService_MB(pmService);
		}
		return pmLimiteInfo;
	}

	/**
	 * 将网管性能门限值转换为corba数据
	 */
	private void convertPMLimite2Corba(PmValueLimiteInfo pmLimite,
											TCAParameters_THolder tcaParameter) {
		TCAParameters_T TCA = new TCAParameters_T();
		TCA.layerRate = 0;
		TCA.granularity = "";
		PMThresholdValue_T[] pmThresholdArray = new PMThresholdValue_T[12];
		int i = 0;
		pmThresholdArray[i++] = this.getPMThresholdValue("highTemp", pmLimite.getHighTemp());
		pmThresholdArray[i++] = this.getPMThresholdValue("crcError", pmLimite.getCrcError());
		pmThresholdArray[i++] = this.getPMThresholdValue("lossNum", pmLimite.getLossNum());
		pmThresholdArray[i++] = this.getPMThresholdValue("receiveBadWrap", pmLimite.getReceiveBadWrap());
		pmThresholdArray[i++] = this.getPMThresholdValue("tmsWorsen", pmLimite.getTmsWorsen());
		pmThresholdArray[i++] = this.getPMThresholdValue("tmsLose", pmLimite.getTmsLose());
		pmThresholdArray[i++] = this.getPMThresholdValue("align", pmLimite.getAlign());
		pmThresholdArray[i++] = this.getPMThresholdValue("lowTemp", pmLimite.getLowTemp());
		pmThresholdArray[i++] = this.getPMThresholdValue("tmpWorsen", pmLimite.getTmpWorsen());
		pmThresholdArray[i++] = this.getPMThresholdValue("tmpLose", pmLimite.getTmpLose());
		pmThresholdArray[i++] = this.getPMThresholdValue("tmcWorsen", pmLimite.getTmcWorsen());
		pmThresholdArray[i++] = this.getPMThresholdValue("tmcLose", pmLimite.getTmcLose());
		TCA.tcaTypeValues = pmThresholdArray;
		tcaParameter.value = TCA;
	}
	
	/**
	 * 性能门限值赋值
	 */
	private PMThresholdValue_T getPMThresholdValue(String name, int value) {
		PMThresholdValue_T pm = new PMThresholdValue_T();
		pm.pmParameterName = name;
		pm.pmLocation = "";
		pm.thresholdType = PMThresholdType_T.from_int(0);
		pm.triggerFlag = true;
		pm.value = value;
		pm.unit = "";
		return pm;
	}

	/**
	 * @author guoqc
	 * 设置性能门限值
	 * 入参  tpName 厂商信息/网元信息
	 * 入参  tcaParameters 需要设置的性能门限值
	 * @throws ProcessingFailureException 
	 */
	public void setTCATPParameter(NameAndStringValue_T[] tpName,
									TCAParameters_THolder tcaParameter) throws ProcessingFailureException {
		try{
			if(!CheckParameterUtil.checkMeName(tpName)){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"tpName is invalid!");
			}
			int siteId = Integer.parseInt(tpName[1].value);
			if(siteId<=0){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"tpName is invalid!");
			}
			PmValueLimiteInfo pmLimite = this.getPmValueLimiteInfoBySiteId(siteId);
			if(!UiUtil.isNull(pmLimite)){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"no find data!");
			}
			this.convertCorba2PMLimite(pmLimite, tcaParameter, siteId);
			this.sendData(pmLimite);
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"Internal error:setTCATPParameter.ex!");
		}
		
	}

	/**
	 * 将corba数据转换成网管所需要的数据
	 */
	private void convertCorba2PMLimite(PmValueLimiteInfo pmLimite,
											TCAParameters_THolder tcaParameter, int siteId) {
		TCAParameters_T TCA = tcaParameter.value;
		PMThresholdValue_T[] pmArray = TCA.tcaTypeValues;
		pmLimite.setSiteId(siteId);
		//if(pmArray.length > 11){
			for (int i = 0; i < pmArray.length; i++) {
				PMThresholdValue_T pm = pmArray[i];
				String name = pm.pmParameterName;
				int value = (int) pm.value;
				if("highTemp".equals(name)){
					pmLimite.setHighTemp(value);
				}else if("crcError".equals(name)){
					pmLimite.setCrcError(value);
				}else if("lossNum".equals(name)){
					pmLimite.setLossNum(value);
				}else if("receiveBadWrap".equals(name)){
					pmLimite.setReceiveBadWrap(value);
				}else if("tmsWorsen".equals(name)){
					pmLimite.setTmsWorsen(value);
				}else if("tmsLose".equals(name)){
					pmLimite.setTmsLose(value);
				}else if("align".equals(name)){
					pmLimite.setAlign(value);
				}else if("lowTemp".equals(name)){
					pmLimite.setLowTemp(value);
				}else if("tmpWorsen".equals(name)){
					pmLimite.setTmpWorsen(value);
				}else if("tmpLose".equals(name)){
					pmLimite.setTmpLose(value);
				}else if("tmcWorsen".equals(name)){
					pmLimite.setTmcWorsen(value);
				}else if("tmcLose".equals(name)){
					pmLimite.setTmcLose(value);
				}
			}	
		}
	//}

	/**
	 * 下发数据到设备 
	 * @throws ProcessingFailureException 
	 */
	private void sendData(PmValueLimiteInfo pmLimite) throws ProcessingFailureException {
		try {
			PmLimiteDispatch pmlimitedispatch = new PmLimiteDispatch();
			if (pmLimite.getId() > 0) {
				pmlimitedispatch.excuteUpdate(pmLimite);
			} else {
				pmlimitedispatch.excuteInsert(pmLimite);
			}
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
	}
	public static void main(String args[]){
		String name="ftp/alarm.csv|21";
		System.out.println(name.split("\\/").length);
	}
	/**
	 * 历史性能文件的传输（FTP）
	 * @param destination
	 * 		路径：： 格式：如："127.0.0.1|/ftp/hisperf_XX.dat|21"或"127.0.0.1|/ftp/hisperf.csv"
	 * @param userName
	 * @param passWord
	 * @param hisPerformanceList
	 * @throws ProcessingFailureException
	 */
	private void HisPMFileTranfer(String destination,String userName,String passWord,List<HisPerformanceInfo> hisPerformanceList) throws ProcessingFailureException{
		FtpUtil ftp = new FtpUtil();
		FtpUtil.FtpInfo ftpInfo = ftp.new FtpInfo(userName, passWord);
		if(!StringUtil.FtpDestinationParse(destination, ftpInfo)){
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT,
					"destination is invalid input!");
		}
		String fileName= ftpInfo.localFileName;
		RandomAccessFile out = null;
		try {
			FileTools.writeFileHead(fileName,
					FileTools.getFileHeadStr(FTPHisPerformanceInfo.class));
			out = new RandomAccessFile(fileName, "rw");
			out.seek(out.length());

			if(!hisPerformanceList.isEmpty()){
				FTPHisPerformanceInfo ftpHisPerformance=null;
				int iLength=hisPerformanceList.size();
				for(int i=0;i<iLength;i++){
					ftpHisPerformance=this.getFTPhisPerformance(hisPerformanceList.get(i));
					String lineStr = FileTools.getLineStrFromFtpInfo(ftpHisPerformance);
					if (lineStr != null) {
						out.write(lineStr.getBytes("GBK"));
					}
				}
			}
			// 将本地文件通过ftp上传到指定路径
			boolean status = ftp.push2FtpServer(ftpInfo);
			CorbaNotifyReport.fileTransferNotify(fileName, status);
		} catch (FileNotFoundException e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "NO Find File!");
		} catch (IOException e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					LOG.error(e.getMessage());
				}
			}
		}
	}
	/**
	 * 历史性能对象转为 FTP传输历史性能文件的对象
	 * @param info
	 * @return
	 * @throws ProcessingFailureException 
	 */
	private FTPHisPerformanceInfo getFTPhisPerformance(HisPerformanceInfo info) throws ProcessingFailureException{
		PortService_MB portService=null;
		List<PortInst> portInstList=null;
		FTPHisPerformanceInfo ftpHisPerformance=null;
		String portName="";
		String unitName="";
		try {
			portService= (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInstList = portService.selectPortbySiteandPortname(info.getSiteId(), info.getObjectName());
			if(portInstList!=null&&portInstList.size()==1){
				portName="/rack=1/shelf=1/slot=" + portInstList.get(0).getSlotNumber() + "/port=" + info.getObjectName();
			}
			ftpHisPerformance=new FTPHisPerformanceInfo();
			ftpHisPerformance.EMS_Name=CorbaConfig.getInstanse().getCorbaEmsName();
			ftpHisPerformance.ME_Name=String.valueOf(info.getSiteId());
			ftpHisPerformance.PTP_Name=portName;
			ftpHisPerformance.LayerRate="1";
			if(info.getMonitor()==EMonitorCycle.MIN15.getValue()){
				ftpHisPerformance.Granularity="15Min";//性能周期
			}else{
				ftpHisPerformance.Granularity="24Hour";//性能周期
			}		
			ftpHisPerformance.MonitoredTime=DateTimeUtil.converCorbaTime(info.getStartTime(),DateUtil.FULLTIME);//开始时间
			ftpHisPerformance.endTime=DateTimeUtil.converCorbaTime(info.getPerformanceEndTime(),DateUtil.FULLTIME);//结束时间
			if(info.getCapability()!=null){
				ftpHisPerformance.PMParameter=info.getCapability().getCapabilityname();//性能参数
				unitName=info.getCapability().getUnitName();
			}			
			ftpHisPerformance.Location="0";
			String performanceValue="";//性能值
			if(unitName.trim().toString().equals("%")){
				// 性能值
				performanceValue= info.getPerformanceValue()/100+"";
			}else{
				if(info.getPerformanceCode() == 80 
						||info.getPerformanceCode() == 81
						||info.getPerformanceCode() == 82 
						||info.getPerformanceCode() == 83
						||info.getPerformanceCode() == 84
						||info.getPerformanceCode() == 85
						||info.getPerformanceCode() == 89
						||info.getPerformanceCode() == 153)
				{
//					  float  b   =  (float)(Math.round(getPerformanceValue*1000))/1000;(这里的100就是2位小数点,如果要其它位,如4位,这里两个100改成10000)
//					  float   f   =  34.232323;  
					// float   f1   =  b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();  
					//   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
					BigDecimal   b  =   new BigDecimal(info.getPerformanceValue());  
					float   f1   =  b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
					performanceValue=String.valueOf(f1);
				}else{
					performanceValue=String.valueOf(info.getPerformanceValue()).trim().split("\\.")[0];
				}
			}
			ftpHisPerformance.Value=performanceValue;//性能值
			ftpHisPerformance.Unit=unitName;
			ftpHisPerformance.Status="Valid";	
		} catch (Exception e) {			
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(portService);
		}
	
		return ftpHisPerformance;
	}
	/*
	 * 获取所有性能类型
	 */
	private List<Capability> getPerformanceType() throws ProcessingFailureException {
		CapabilityService_MB service = null;
		List<Capability> perforTypeList = null;
		try {
			service = (CapabilityService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Capability);
			perforTypeList = service.select();
			// 过滤相同的指标
			perforTypeList = removeRepeatedType(perforTypeList);
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(service);
		}
		return perforTypeList;
	}
	/**
	 * fanction:过滤相同的指标
	 * 
	 * @param perforTypeList
	 * @return
	 */
	private List<Capability> removeRepeatedType(List<Capability> perforTypeList) {
		List<Capability> NorepeatedCapability = perforTypeList;
		for (int i = 0; i < NorepeatedCapability.size() - 1; i++) {
			for (int j = NorepeatedCapability.size() - 1; j > i; j--) {
				if (NorepeatedCapability.get(j).getCapabilitytype().equals(NorepeatedCapability.get(i).getCapabilitytype())) {
					NorepeatedCapability.remove(j);
				}
			}
		}

		return NorepeatedCapability;
	}
	/**
	 * 根据指定条件，清空性能数据，重新计数: 武汉走适配，陈晓没做呢 ————目前只支持网元
	 * @param pmTPSelectList
	 * @param failedTPSelectList
	 * @throws ProcessingFailureException
	 */
	public void clearPMData(PMTPSelect_T[] pmTPSelectList,
			PMTPSelectList_THolder failedTPSelectList)
			throws ProcessingFailureException {
		String siteId="";
		List<Integer> idList=new ArrayList<Integer>();
		CurrentPerformanceFilter filter=new CurrentPerformanceFilter(); 
		PerformanceDispatch dispatch=new PerformanceDispatch();
		List<SiteInst> siteInstList = null;
		SiteService_MB ss = null;
		try{
			
			if(pmTPSelectList.length>0){
				for(int i=0;i<pmTPSelectList.length;i++){
					if(!CheckParameterUtil.checkMeName(pmTPSelectList[i].name)){
						throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"name is invalid!");
					}
					siteId=pmTPSelectList[i].name[1].value;
					if(!UiUtil.isNull(siteId)||siteId.trim().equals("0")){
						throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"me is invalid!");
					}
					if(!idList.contains(Integer.parseInt(siteId))){
						idList.add(Integer.parseInt(siteId));
					}				
				}
				
			}else{
				ss = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				siteInstList = ss.select();
				if(UiUtil.isNull(siteInstList)){
					for(SiteInst siteInst:siteInstList){
						idList.add(siteInst.getSite_Inst_Id());
					}				
				}
			}
			filter.setSiteInsts(idList);
			String result=dispatch.executeCleanCurrPerforByFilter(filter);
			if (result != null && result.length() > 0) {//失败，返回失败的集合
				failedTPSelectList.value=pmTPSelectList;
			}else{
				// 说明所有的网元性能都清除成功
				failedTPSelectList.value=new PMTPSelect_T[0];
			}
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"Interval error:clearPMData.ex !");
		}finally {
			UiUtil.closeService_MB(ss);
		}
	}
	/**
	 *  FTP传输历史性能文件的对象
	 * @author sy
	 *
	 */
	public class FTPHisPerformanceInfo{
		//文件名称：hisperf_XX.dat
		public String EMS_Name;//EMS名称，填写EMS名值对的值部分。
		public String ME_Name;//网元名称，填写网元名称名值对的值部分最后一层，不需要填写EMS层信息。
		public String PTP_Name;//物理终端点名称，填写物理终端点名值对的值部分最后一层，不需要填写EMS、网元信息。如果是单板上报的性能则在值前面加“Equipment:”后面的端口号始终填为1，例如： 'Equipment:/rack=0/shelf=1/slot=1/1'。
		public String LayerRate;//：层速率信息，填写层速率对应的整型值。如果没有可以为空。
		public String Granularity;//粒度周期，填写"15min"或"24h"。
		public String MonitoredTime;//：性能监测时间，这里的取值同TMF814中的"Period Start Time"字段。
		public String endTime;//结束时间
		public String PMParameter;//表示性能参数。
		public String Location;//表示性能监测位置信息。保留，填为0。
		public String Value;//表示性能值。
		public String Unit;//表示模拟量的单位。
		public String Status;//性能数据的状态，目前填为'Valid'。
		
	}
}