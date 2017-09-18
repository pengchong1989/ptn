package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.perform.Capability;
import com.nms.db.bean.perform.CurrentPerforInfo;
import com.nms.db.bean.perform.HisPerformanceInfo;
import com.nms.db.bean.perform.PerformanceTaskInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.perform.CapabilityService_MB;
import com.nms.model.perform.HisPerformanceService_Mb;
import com.nms.model.perform.PerformanceTaskService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.ServiceMainFrame;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.PerformanceCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.PerformanceDispatchI;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.wh.PerformanceWHServiceImpl;
import com.nms.service.notify.Message.MessageType;
import com.nms.ui.PerforTaskThreadFactory;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.performance.model.CurrentPerformanceFilter;
import com.nms.ui.ptn.performance.model.PerformanceTaskFilter;

public class PerformanceDispatch extends DispatchBase implements PerformanceDispatchI {
	// 性能类型
	private static  Map<String, Capability> performanceMap = new HashMap<String, Capability>();
	
	public PerformanceDispatch() {
//		performanceMap.clear();
//		setCapability();
	}
	/**
	 * 根据网元数据库id列表，查询当前性能数据
	 * 
	 * @param siteIds
	 *            网元数据库id列表
	 * @return
	 * @throws Exception
	 */
	public List<CurrentPerforInfo> executeQueryCurrPerforByFilter(CurrentPerformanceFilter filter) throws Exception {
		
		List<CurrentPerforInfo> currPerforInfoList = null;
		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		Capability capability = null;
		try {
			currPerforInfoList = this.queryCurrPerforBySites(filter);
			//查询性能类型
			if(performanceMap == null || performanceMap.isEmpty()){
				setCapability();
			}
			if (currPerforInfoList != null && currPerforInfoList.size() > 0) {
				// 根据设备查询性能数据PerformanceInfo，封装成当前性能对象CurrentPerforInfo
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				siteInst = siteService.select(currPerforInfoList.get(0).getSiteId());
				for (CurrentPerforInfo info : currPerforInfoList) {
				  if (UiUtil.getCodeById(Integer.parseInt(siteInst.getCellEditon())).getCodeName().equals("700+系列")) {
						capability = performanceMap.get(1 + "/" + info.getPerformanceCode());// 1表示武汉
					} else {
						capability = performanceMap.get(2 + "/" + info.getPerformanceCode()); // 2表示晨晓
					}
					info.setCapability(capability);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return currPerforInfoList;
	}

	/**
	 * 长期性能任务，服务端启动时，启动此模块	
	 * @param siteIds
	 * @return
	 * @throws Exception
	 */
	public String executeQueryHisPerforByTask(PerformanceTaskInfo taskInfo) throws Exception {
		List<HisPerformanceInfo> hisPerforInfoList = null;
		// 保存数据库的历史性能
		HisPerformanceService_Mb hisPerformanceService = null;
		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		Capability capability = null;
		List<String> performanceType  = new ArrayList<String>();
		try {
			hisPerforInfoList = this.queryHisPerforBySites(taskInfo);
			
			if(performanceMap == null || performanceMap.isEmpty()){
				setCapability();
			}
			//解析长期性能的类型
			String[] perforType = taskInfo.getPerforType().split(",");
			if(perforType != null && perforType.length >0)
			{
				for(String str : perforType)
				{
					performanceType.add(str);
				}
			}
			if (hisPerforInfoList != null && !hisPerforInfoList.isEmpty()) {
				hisPerformanceService = (HisPerformanceService_Mb) ConstantUtil.serviceFactory.newService_MB(Services.HisPerformance);
				// 根据设备查询性能数据PerformanceInfo，封装成历史性能对象HisPerformanceInfo
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				siteInst = siteService.select(hisPerforInfoList.get(0).getSiteId());
				for (HisPerformanceInfo info : hisPerforInfoList) {
					try {
						// 返回的性能对象与当前管理的网元对应不上就不存库
						if (info.getObjectName() != null) {
							info.setTaskId(taskInfo.getId());
						}
					    if (UiUtil.getCodeById(Integer.parseInt(siteInst.getCellEditon())).getCodeName().equals("700+系列")) {
							capability = performanceMap.get(1 + "/" + info.getPerformanceCode());// 1表示武汉
						} else {
							capability = performanceMap.get(2 + "/" + info.getPerformanceCode()); // 2表示晨晓
						}
					    info.setCapability(capability);
						// 保存数据库中
						if( null !=info.getObjectName() 
							&& !info.getObjectName().equals("")
							&& info.getCapability()!= null
							&&performanceType.contains(info.getCapability().getCapabilitytype()))
						{
						  hisPerformanceService.saveOrUpdate(info);	
						}
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			UiUtil.closeService_MB(hisPerformanceService);
			UiUtil.closeService_MB(siteService);
			hisPerforInfoList = null;
			siteInst = null;
			capability = null;
		}
		return  "";
	}
	
	
	
	/**
	 * 长期性能任务，服务端启动时，启动此模块	
	 * @param siteIds
	 * @return
	 * @throws Exception
	 */
	public void executeQueryTaskCommand(List<PerformanceTaskInfo> taskInfo) throws Exception {
		 this.queryHisPerforBySites(taskInfo);
	}
	
	
	

	/**
	 * 根据网元数据库id列表，查询当前性能数据
	 * 
	 * @param siteIds
	 * @returnT
	 * @throws Exception
	 */
	private List<CurrentPerforInfo> queryCurrPerforBySites(CurrentPerformanceFilter filter) throws Exception {
		List<CurrentPerforInfo> perforInfoList = new ArrayList<CurrentPerforInfo>();
		List<Integer> whSiteIdList = new ArrayList<Integer>();
		List<String> whslotIdList = new ArrayList<String>();
		List<String> cxslotIdList = new ArrayList<String>();
		List<Integer> cxSiteIdList = new ArrayList<Integer>();
		int manufacturer = 0;
		PerformanceWHServiceImpl whOperationServiceI = null;
		PerformanceCXServiceImpl cxOperationServiceI = null;
		List<Integer> siteIds = null;
		try {
			siteIds = new ArrayList<Integer>();
			// 根据网元数据库id，查询当前性能
			Set<Integer> siteIdList = new HashSet<Integer>();
			if (null != filter.getSiteInsts()) {
				SiteUtil siteUtil = new SiteUtil();
				for (Integer siteId : filter.getSiteInsts()) {
					if(siteUtil.SiteTypeUtil(siteId)==0){//真实网元
					siteIdList.add(siteId);
				}
				}
			}
			if (siteIdList != null && siteIdList.size() > 0) {
				siteIds.addAll(siteIdList);
			}
			for (Integer id : siteIds) {
				manufacturer = super.getManufacturer(id);
				if (manufacturer == EManufacturer.WUHAN.getValue()) {
					whSiteIdList.add(id);
				} else {
					cxSiteIdList.add(id);
				}
			}

			// 根据网元数据库id+板卡的盘地址来查询当前板卡的性能
			if (null != filter.getSite_slotInsts()) {
				for (String site_slot : filter.getSite_slotInsts()) {
					manufacturer = super.getManufacturer(Integer.valueOf(site_slot.split("/")[0]));
					if (manufacturer == EManufacturer.WUHAN.getValue()) {
						whslotIdList.add(site_slot);
					} else {
						cxslotIdList.add(site_slot);
					}
				}
			}
			
			whOperationServiceI = new PerformanceWHServiceImpl();
			// 查询根据网元ID来查询性能，并入库
			if (whSiteIdList != null && whSiteIdList.size() > 0) {
					for (Integer integer : siteIds) {
					  try {
						List<CurrentPerforInfo> perforInfo = new ArrayList<CurrentPerforInfo>();
						perforInfo = whOperationServiceI.queryCurrPerforBySites(integer,filter.getPerformanceCount(),filter.getPerformanceBeginCount(),filter.getPerformanceBeginDataTime(),filter.getPerformanceType());
						if (perforInfo != null) {
							perforInfoList.addAll(perforInfo);
						}
					  } catch (Exception e) {
						ExceptionManage.dispose(e, getClass());
					 }
					}
			}
			cxOperationServiceI = new PerformanceCXServiceImpl();
			if (cxSiteIdList != null && cxSiteIdList.size() > 0) {
				filter.setSiteInsts(cxSiteIdList);
				perforInfoList.addAll(cxOperationServiceI.selectPerformance(filter));
			}

			// 查询根据网元ID+板卡的盘地址来查询性能，并入库
			if (whslotIdList != null && whslotIdList.size() > 0) {
				for (String site_slot : filter.getSite_slotInsts()) {
					try {
						List<CurrentPerforInfo> perforInfo = new ArrayList<CurrentPerforInfo>();
						perforInfo = whOperationServiceI.queryCurrPerforByCard(Integer.valueOf(site_slot.split("/")[0]), site_slot.split("/")[1],filter.getPerformanceCount(),filter.getPerformanceBeginCount(),filter.getPerformanceBeginDataTime(),filter.getPerformanceType());
						if (perforInfo != null) {
							perforInfoList.addAll(perforInfo);
						}
					} catch (Exception e) {
						ExceptionManage.dispose(e, getClass());
					}
				}
			}
			// 程晓的板卡性能查询没做???
			 if(cxSiteIdList!=null && cxSiteIdList.size()>0){
//					List<CurrentPerforInfo> perforInfo = new ArrayList<CurrentPerforInfo>();
//					perforInfo = cxOperationServiceI.selectPerformance(filter);
//					if (perforInfo != null) {
//						perforInfoList.addAll(perforInfo);

			//	}
			 }
			 
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return perforInfoList;
	}

	/**
	 * 根据网元数据库id列表，查询历史性能数据 2013-8-17 张坤修改
	 * @param siteIds
	 * @return
	 * @throws Exception
	 */
	private List<HisPerformanceInfo> queryHisPerforBySites(PerformanceTaskInfo taskInfo) throws Exception {
		List<HisPerformanceInfo> perforInfoList = new ArrayList<HisPerformanceInfo>();
		List<Integer> whSiteIdList = new ArrayList<Integer>();
		List<Integer> cxSiteIdList = new ArrayList<Integer>();
		int manufacturer = 0;
		PerformanceWHServiceImpl whOperationServiceI = null;
		PerformanceCXServiceImpl cxOperationServiceI = null;
		List<Integer> siteIds = null;
		try {
			siteIds = new ArrayList<Integer>();
			siteIds.add(taskInfo.getSiteInst().getSite_Inst_Id());
			for (Integer id : siteIds) {
				manufacturer = super.getManufacturer(id);
				if (manufacturer == 0) {
					whSiteIdList.add(id);
				} else {
					cxSiteIdList.add(id);
				}
			}
			whOperationServiceI = new PerformanceWHServiceImpl();
			// 查询性能，并入库
			if (whSiteIdList != null && whSiteIdList.size() > 0) {
				for (Integer integer : whSiteIdList) {
				try {
					//网元的历史性能
					if (taskInfo.getSlotCard() == null || taskInfo.getSlotCard().equals("")) {
						List<HisPerformanceInfo> perforInfo = new ArrayList<HisPerformanceInfo>();
//						perforInfo = whOperationServiceI.queryHisPerforBySite(integer,taskInfo.getPerformanceCount(),taskInfo.getPerformanceBeginCount(),taskInfo.getPerformanceBeginDataTime(),taskInfo.getPerformanceType());
						perforInfo = whOperationServiceI.queryHisPerforBySite(integer,taskInfo);
						if (perforInfo != null) {
							perforInfoList.addAll(perforInfo);
						}
					} else {
						//板卡的历史性能
						List<HisPerformanceInfo> perforInfo = new ArrayList<HisPerformanceInfo>();
						perforInfo = whOperationServiceI.queryHisPerforByCard(integer, taskInfo.getSlotCard(),taskInfo.getPerformanceCount(),taskInfo.getPerformanceBeginCount(),taskInfo.getPerformanceBeginDataTime(),taskInfo.getPerformanceType());
						if (perforInfo != null) {
							perforInfoList.addAll(perforInfo);
						}
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
				
				}
			}
			cxOperationServiceI = new PerformanceCXServiceImpl();
			if (cxSiteIdList != null && cxSiteIdList.size() > 0) {
				perforInfoList.addAll(cxOperationServiceI.selectPerformanceHis(siteIds,taskInfo));
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return perforInfoList;
	}

	
	
	/**
	 * 根据网元数据库id列表，查询历史性能数据 2013-8-17 张坤修改
	 * @param siteIds
	 * @return
	 * @throws Exception
	 */
	private void queryHisPerforBySites(List<PerformanceTaskInfo> taskInfo) throws Exception {
		PerformanceWHServiceImpl whOperationServiceI = null;
		try {
			whOperationServiceI = new PerformanceWHServiceImpl();
			for(PerformanceTaskInfo taskInst : taskInfo)
			{
				try {
					whOperationServiceI.queryHisPerforTask(taskInst.getSiteInst().getSite_Inst_Id(),taskInst);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally
		{
			whOperationServiceI = null;
		}
	}
	/**
	 * 将历史性能信息保存到数据库中
	 * 
	 * @param hisPerforInfoList
	 * @throws Exception
	 */
	private void saveHisPerforInfos(List<HisPerformanceInfo> hisPerforInfoList) throws Exception {
		HisPerformanceService_Mb hisPerformanceService = null;
		try {
			hisPerformanceService = (HisPerformanceService_Mb) ConstantUtil.serviceFactory.newService_MB(Services.HisPerformance);
			for (HisPerformanceInfo hisPerforInfo : hisPerforInfoList) {
//				if (!isExit(hisPerforInfo))
					hisPerformanceService.saveOrUpdate(hisPerforInfo);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(hisPerformanceService);
		}
	}

	/**
	 * 判断数据库中历史性能数据是否已经存在
	 * 
	 * @param hisPerforInfo
	 * @return
	 */
	private boolean isExit(HisPerformanceInfo hisPerforInfo) throws Exception {
		boolean flag = false;
		HisPerformanceService_Mb hisPerformanceService = null;
		HisPerformanceInfo condition = null;
		List<HisPerformanceInfo> infoList = null;
		try {
			hisPerformanceService = (HisPerformanceService_Mb) ConstantUtil.serviceFactory.newService_MB(Services.HisPerformance);
			condition = new HisPerformanceInfo();
			condition.setSiteId(hisPerforInfo.getSiteId());
			condition.setSlotId(hisPerforInfo.getSlotId());
			condition.setPerformanceCode(hisPerforInfo.getPerformanceCode());
			condition.setPerformanceValue(hisPerforInfo.getPerformanceValue());
			condition.setPerformanceTime(hisPerforInfo.getPerformanceTime());
			condition.setObjectId(hisPerforInfo.getObjectId());
			condition.setObjectName(hisPerforInfo.getObjectName());
			condition.setObjectType(hisPerforInfo.getObjectType());
			condition.setTaskId(hisPerforInfo.getTaskId());
			condition.setIsCardOrSite(hisPerforInfo.getIsCardOrSite());
			infoList = hisPerformanceService.select(condition);
			if (infoList != null && infoList.size() > 0) {
				flag = true;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(hisPerformanceService);
			infoList = null;
			condition = null;
		}
		return flag;
	}
	@Override
	/**
	 * function:清除设备的性能
	 */
	public String executeCleanCurrPerforByFilter(CurrentPerformanceFilter filter) throws RemoteException, Exception {
		List<Integer> whSiteIdList = new ArrayList<Integer>();
		List<String> whslotIdList = new ArrayList<String>();
		int manufacturer = 0;
		PerformanceWHServiceImpl whOperationServiceI = null;
		List<Integer> siteIds = null;
		String sb = null;
		String result = null;
		try {
			siteIds = new ArrayList<Integer>();
			// 根据网元数据库id，清除当前性能
			Set<Integer> siteIdList = new HashSet<Integer>();
			if (null != filter.getSiteInsts()) {
				for (Integer siteId : filter.getSiteInsts()) {
					siteIdList.add(siteId);
				}
			}
			if (siteIdList != null && siteIdList.size() > 0) {
				siteIds.addAll(siteIdList);
			}
			for (Integer id : siteIds) {
				manufacturer = super.getManufacturer(id);
				if (manufacturer == EManufacturer.WUHAN.getValue()) {
					whSiteIdList.add(id);
				} else {
					sb="fail";
				}
			}

			// 根据网元数据库id+板卡的盘地址来清除当前板卡的性能
			if (null != filter.getSite_slotInsts()) {
				for (String site_slot : filter.getSite_slotInsts()) {
					manufacturer = super.getManufacturer(Integer.valueOf(site_slot.split("/")[0]));
					if (manufacturer == EManufacturer.WUHAN.getValue()) {
						whslotIdList.add(site_slot);
					} else {
					}
				}
			}
			whOperationServiceI = new PerformanceWHServiceImpl();
			// 查询根据网元ID来清除性能
			if (whSiteIdList != null && whSiteIdList.size() > 0) {
				for (Integer integer : whSiteIdList) {
				 try {
					result = whOperationServiceI.cleanCurrPerforBySites(integer);
					if (result != null && result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL))) {
						sb += integer+",";
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e, getClass());
				}
			  }
			}
			// 查询根据网元ID+板卡的盘地址来清除性能
			if (whslotIdList != null && whslotIdList.size() > 0) {
				for (String site_slot : filter.getSite_slotInsts()) {
					try {
						result = whOperationServiceI.cleanCurrPerforByCard(Integer.valueOf(site_slot.split("/")[0]), site_slot.split("/")[1]);
						if (result != null && result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL))) {
							sb += Integer.valueOf(site_slot.split("/")[0])+",";
						}
					} catch (Exception e) {
						ExceptionManage.dispose(e, getClass());
					}
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
		if(sb != null && sb.length() > 0){
			sb = sb.substring(0, sb.length()-1);
		}
		return sb;
	}
	
	private void setCapability() {
		CapabilityService_MB capabilityService = null;
		List<Capability> capabilities = null;
		try {
			capabilityService = (CapabilityService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Capability);
			capabilities = capabilityService.select();
			for (Capability capability : capabilities) {
				performanceMap.put(capability.getManufacturer() + "/" + capability.getCapabilitycode(), capability);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(capabilityService);
			capabilities = null;
		}
	}
	
	/**
	 * 修改长期性能任务
	 */
	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		PerformanceTaskService_MB taskService = null;
		PerformanceTaskInfo taskInfo = null;
		PerformanceTaskInfo info = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		Map<String, String> attributeList = new HashMap<String, String>();
		try {
			taskService = (PerformanceTaskService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PerformanceTask);
			taskInfo = (PerformanceTaskInfo) object;
			info = taskService.selectById(taskInfo.getId());
			int id = taskService.saveOrUpdate(taskInfo);
			taskInfo.setId(id);
			if (id > 0 ) {
				result = ResultString.CONFIG_SUCCESS;
			}
			
			if (result.equals(ResultString.CONFIG_SUCCESS)) {
				//判断修改的属性上报
				if (!info.getTaskName().equals(taskInfo.getTaskName())) {
					attributeList.put("userLabel", info.getTaskName());
				}
				if (info.getTaskLabel() != taskInfo.getTaskLabel()) {
					attributeList.put("taskLabel", info.getTaskLabel()+"");
				}
				if (info.getRunstates() != taskInfo.getRunstates()) {
					attributeList.put("runstates", info.getRunstates().toString());
				}
				if (info.getSiteInst() != taskInfo.getSiteInst()) {
					attributeList.put("siteInst", info.getSiteInst().getSite_Inst_Id()+"");
				}
				if (info.getObjectId() != taskInfo.getObjectId()) {
					attributeList.put("objectId", info.getObjectId()+"");
				}
				if (info.getObjectType() != taskInfo.getObjectType()) {
					attributeList.put("objectType", info.getObjectType().toString());
				}
				if (!info.getPerforType().equals(taskInfo.getPerforType())) {
					attributeList.put("perforType", info.getPerforType().toString());
				}
				attributeList.put("id", info.getId()+"");
				super.notifyCorba("performance", MessageType.ATTRIBUTECHG, attributeList, "", result);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			taskInfo = null;
			UiUtil.closeService_MB(taskService);
		}
		return result;
	}
	
	/**
	 * 新建长期性能任务
	 */
	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		PerformanceTaskService_MB taskService = null;
		PerformanceTaskInfo taskInfo = null;
		String result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			taskService = (PerformanceTaskService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PerformanceTask);
			taskInfo = (PerformanceTaskInfo) object;
			int id = taskService.saveOrUpdate(taskInfo);
			if (id > 0 ) {
				result = ResultString.CONFIG_SUCCESS;
			}
			//判断是否存在线程在处理任务
			Map<String, Thread> threadMap = ServiceMainFrame.getServiceMainFrame().getThreadMap();
			
			if(threadMap != null && threadMap.size()>0 ){
			   if(!threadMap.containsKey("performanceTaskThreadName")){
				  PerforTaskThreadFactory.getInstance().createPerforTaskThread();
	    	   }
			}else{
				PerforTaskThreadFactory.getInstance().createPerforTaskThread();
			}
			
			if (result.equals(ResultString.CONFIG_SUCCESS)) {
				super.notifyCorba("performance", MessageType.CREATION, taskInfo, "", result);
			}
						
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			taskInfo = null;
			UiUtil.closeService_MB(taskService);
		}
		return result;
	}
	
	/**
	 * 删除长期性能任务
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		
		PerformanceTaskService_MB service = null;
		List<PerformanceTaskInfo> infoList = null;
		List<Integer> idList = null;
		String result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<PerformanceTaskInfo> taskList = new ArrayList<PerformanceTaskInfo>();
		try {
			idList = new ArrayList<Integer>();
			infoList = (List<PerformanceTaskInfo>) object;
			service = (PerformanceTaskService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PerformanceTask);
			for (PerformanceTaskInfo info : infoList) {
				idList.add(Integer.valueOf(info.getId()));
			}
			int n = service.delete(idList);
			if (n > 0) {
				result = ResultString.CONFIG_SUCCESS;
			}
			taskList = service.select();
			if(taskList == null || taskList.size() == 0){
				Map<String, Thread> threadMap = ServiceMainFrame.getServiceMainFrame().getThreadMap();
				if(threadMap.containsKey("performanceTaskThreadName")){
					PerforTaskThreadFactory.getInstance().stopPerformanceThread("performanceTaskThreadName");
				}
			}
			if (result.equals(ResultString.CONFIG_SUCCESS)) {
				for (PerformanceTaskInfo info : infoList) {
					super.notifyCorba("performance", MessageType.DELETION, info, "", result);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(service);
			idList = null;
			infoList = null;
		}
		
		return result;
	}
	
	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		return null;
	}
	/**
	 * function:根据条件查询所有的长期性能任务
	 * 
	 */
	@Override
	public  List<PerformanceTaskInfo> getAllPerformanceTask(Object object){
		
		 List<PerformanceTaskInfo> taskList = null;
		 PerformanceTaskService_MB service = null;
		 PerformanceTaskFilter filter = null;
		 
		 try {
			taskList = new ArrayList<PerformanceTaskInfo>();
			filter = (PerformanceTaskFilter)object;
			service = (PerformanceTaskService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PerformanceTask);
			if (filter != null) {
				taskList = service.queryByFilter(filter);
			} else {
				taskList = service.select();
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(service);
		    filter = null;
		}
		return taskList;
	}
	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		return null;
	}
	
}
