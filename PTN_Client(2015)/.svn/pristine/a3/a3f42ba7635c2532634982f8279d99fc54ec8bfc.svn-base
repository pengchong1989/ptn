package com.nms.model.perform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.bean.perform.PerformanceTaskInfo;
import com.nms.db.dao.perform.PerformanceTaskInfoMapper;
import com.nms.db.enums.EObjectType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.performance.model.PerformanceTaskFilter;

public class PerformanceTaskService_MB extends ObjectService_Mybatis {
	private PerformanceTaskInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(PerformanceTaskInfoMapper mapper) {
		this.mapper = mapper;
	}

	public PerformanceTaskInfoMapper getMapper() {
		return mapper;
	}
	
	/**
	 * 根据过滤条件查询
	 * 
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<PerformanceTaskInfo> queryByFilter(PerformanceTaskFilter filter) throws Exception {
		List<PerformanceTaskInfo> taskInfoList = null;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			
			map.put("objectType", filter.getObjectType().getValue());
			if(filter.getSiteInsts() != null && filter.getSiteInsts().size()>0){
				map.put("siteIds", filter.getSiteInsts());
			}else{
				map.put("siteIds", null);
			}
			if(filter.getSlotInsts() != null && filter.getSlotInsts().size()>0){
				map.put("slotIds", filter.getSlotInsts());
			}
			if(filter.getMonitorCycle() != null){
				map.put("monitorcycle", filter.getMonitorCycle().getValue());
			}else{
				map.put("monitorcycle", null);
			}
			taskInfoList = mapper.queryByFilter(map);
			// 将网元和板卡信息封装到对象中
			this.wrapPerformanceTask(taskInfoList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return taskInfoList;
	}
	
	/**
	 * 封装性能任务对象
	 * 
	 * @throws Exception
	 */
	public void wrapPerformanceTask(List<PerformanceTaskInfo> taskInfoList) throws Exception {
		if (taskInfoList != null) {
			SiteService_MB siteServiceMB = null;
			SlotService_MB slotServiceMB = null;
			List<SiteInst> siteList = null;
			List<SlotInst> slotList = null;
			SlotInst slotInst = null;
			try {
				siteServiceMB = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
				slotServiceMB = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT, this.sqlSession);
				for (PerformanceTaskInfo task : taskInfoList) {
					// 将网元和板卡信息封装到对象中
					SiteInst siteInst = task.getSiteInst();
					siteList = siteServiceMB.select(siteInst);
					if (siteList != null && siteList.size() > 0) {
						siteInst = siteList.get(0);
						task.setSiteInst(siteInst);
					}
					if (task.getObjectType() != null && task.getObjectType() == EObjectType.SITEINST) {
						task.setObjectName(EObjectType.SITEINST.toString() + "/" + task.getSiteInst().getCellId());
					} else if (task.getObjectType() != null && task.getObjectType() == EObjectType.SLOTINST) {
						slotInst = new SlotInst();
						slotInst.setId(task.getObjectId());
						slotList = slotServiceMB.select(slotInst);
						if (slotList != null && slotList.size() > 0) {
							slotInst = slotList.get(0);
							task.setObjectName(EObjectType.SLOTINST.toString() + "/" + slotInst.getId());
						}
					}
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally {
			}
		}
	}
	
	/**
	 * 获取所有长期性能任务
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PerformanceTaskInfo> select() throws Exception {
		List<PerformanceTaskInfo> taskInfoList = null;
		try {
			PerformanceTaskInfo condition = new PerformanceTaskInfo();
			taskInfoList = mapper.queryByCondition(condition,null,null,null,null);
			this.wrapPerformanceTask(taskInfoList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return taskInfoList;
	}
	
	/**
	 * 名字是否重复
	 * @param afterName
	 * @param beforeName
	 * @return
	 */
	public boolean nameRepetition(String afterName, String beforeName) {
		int result = 0;
		try {
			result = this.mapper.query_name(afterName, beforeName);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if(0== result){
			return false;
		}else{
			return true;
		}
	}
	
}
