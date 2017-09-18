package com.nms.snmp.ninteface.impl.performance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.perform.PerformanceTaskInfo;
import com.nms.db.enums.EMonitorCycle;
import com.nms.db.enums.EObjectType;
import com.nms.db.enums.ERunStates;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.perform.PerformanceTaskService_MB;
import com.nms.model.util.Services;
import com.nms.ui.PerforTaskThreadFactory;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class PerformanceTrap {
	/**
	 * 性能数据采集
	 */
	public void collectPM() {
		PerformanceTaskService_MB taskService = null;
		SiteService_MB siteService = null;
		try {
			taskService = (PerformanceTaskService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PerformanceTask);
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			List<SiteInst> siteList = siteService.select();//查询所有网元
			PerformanceTaskInfo taskCondition = new PerformanceTaskInfo();
			taskCondition.setMonitorCycle(EMonitorCycle.MIN15);
			List<PerformanceTaskInfo> taskList_15min = taskService.select(taskCondition);//查询15min性能任务
//			taskCondition.setMonitorCycle(EMonitorCycle.HOUR24);
//			List<PerformanceTaskInfo> taskList_24hour = taskService.select(taskCondition);//查询24hour性能任务
			List<PerformanceTaskInfo> snmpTaskList = new ArrayList<PerformanceTaskInfo>();//新建的性能任务
			List<Integer> taskIdList = new ArrayList<Integer>();//所有的taskId
			for (SiteInst siteInst : siteList) {
				boolean flag = false;
				int siteId = siteInst.getSite_Inst_Id();
				//根据网元id去查找是否有15min性能任务,没有就创建
				for (PerformanceTaskInfo task : taskList_15min) {
					if(siteId == task.getSiteInst().getSite_Inst_Id()
							&& task.getTaskName().contains("snmp")){
						if(task.getMonitorCycle().getValue() == 1){
							if(!taskIdList.contains(task.getId())){
								taskIdList.add(task.getId());
							}
							flag = true;
							break;
						}
					}
				}
				if(!flag){
					this.createTask(siteId, 1, snmpTaskList);
				}
//				flag = false;
//				//根据网元id去查找是否有24hour性能任务,没有就创建
//				for (PerformanceTaskInfo task : taskList_24hour) {
//					if(siteId == task.getSiteInst().getSite_Inst_Id()
//							&& task.getTaskName().contains("snmp")){
//						if(task.getMonitorCycle().getValue() == 2){
//							if(!taskIdList.contains(task.getId())){
//								taskIdList.add(task.getId());
//							}
//							flag = true;
//							break;
//						}
//					}
//				}
//				if(!flag){
//					this.createTask(siteId, 2, snmpTaskList);
//				}
			}
			//开启长期性能任务
			this.startPerformanceTask(snmpTaskList, taskIdList);
			//开启线程采集历史性能
			new Thread(new PerformanceTrapThread(taskIdList)).start();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(taskService);
			UiUtil.closeService_MB(siteService);
		}
	}
	
	/**
	 * 新建网元时新建长期性能任务
	 * 性能数据采集
	 */
	public void collectPM(int siteId) {
		try {
			List<PerformanceTaskInfo> snmpTaskList = new ArrayList<PerformanceTaskInfo>();//新建的性能任务
			List<Integer> taskIdList = new ArrayList<Integer>();//所有的taskId
			this.createTask(siteId, 1, snmpTaskList);
//			this.createTask(siteId, 2, snmpTaskList);
			//开启长期性能任务
			this.startPerformanceTask(snmpTaskList, taskIdList);
			//开启线程采集历史性能
			new Thread(new PerformanceTrapThread(taskIdList)).start();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 给没有长期性能任务的网元建立任务
	 */
	private void createTask(int siteId, int type, List<PerformanceTaskInfo> snmpTaskList) {
		PerformanceTaskInfo taskInfo = new PerformanceTaskInfo();
		SiteInst site = new SiteInst();
		site.setSite_Inst_Id(siteId);
		taskInfo.setSiteInst(site);
		taskInfo.setObjectId(siteId);
		taskInfo.setObjectType(EObjectType.SITEINST);
		taskInfo.setThreadName(siteId+"_snmp_1"+(new Date().getTime()));
		taskInfo.setTaskName("Thread_"+taskInfo.getThreadName());
		taskInfo.setRunstates(ERunStates.RUN);
		taskInfo.setPerforType("VC12,STM1,PWTDM,PON,PHY,PDH,MPLS,LLID,ETH,TMS,TMP/TMC,PORT,1731");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		taskInfo.setCreateTime(sdf.format(System.currentTimeMillis()+1000));
		//15min
		if(type == 1){
			taskInfo.setPerformanceCount(0);
			taskInfo.setPerformanceType(0);
			taskInfo.setMonitorCycle(EMonitorCycle.MIN15);
		}else{
			//24hour
//			taskInfo.setPerformanceCount(255);
//			taskInfo.setPerformanceType(32);
//			taskInfo.setMonitorCycle(EMonitorCycle.HOUR24);
		}
		taskInfo.setPerformanceBeginCount(0);
		snmpTaskList.add(taskInfo);
	}

	/**
	 * 开启长期性能任务
	 * @param taskIdList 
	 */
	private void startPerformanceTask(List<PerformanceTaskInfo> snmpTaskList, List<Integer> taskIdList) {
		PerformanceTaskService_MB service = null;
		try {
			service = (PerformanceTaskService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PerformanceTask);
			PerforTaskThreadFactory factory = PerforTaskThreadFactory.getInstance();
			for (PerformanceTaskInfo taskInfo : snmpTaskList) {
				int result = service.saveOrUpdate(taskInfo);
				if(taskInfo.getId() == 0){
					taskInfo.setId(result);
					if(!taskIdList.contains(result)){
						taskIdList.add(result);
					}
				}
//				PerformanceTimerTask task = new PerformanceTimerTask(taskInfo);
//				factory.createPerforTaskThread(task, taskInfo.getMonitorCycle().getInterval());
			}
			factory.createPerforTaskThread();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}
}
