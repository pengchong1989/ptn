package com.nms.model.perform;


import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.perform.Capability;
import com.nms.db.bean.perform.HisPerformanceInfo;
import com.nms.db.dao.perform.HisPerformanceInfoMapper;
import com.nms.db.enums.EMonitorCycle;
import com.nms.db.enums.EObjectType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.performance.model.HisPerformanceFilter;

public class HisPerformanceService_Mb extends ObjectService_Mybatis {

	private HisPerformanceInfoMapper historyPerformanceMapper = null;

	public HisPerformanceInfoMapper getMapper() {
		return historyPerformanceMapper;
	}

	public void setMapper(HisPerformanceInfoMapper historyPerformanceMapper) {
		this.historyPerformanceMapper = historyPerformanceMapper;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}

	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	/**
	 * 查看 历史告警表多少条记录 返回 result 条
	 * 
	 * @return
	 * @throws Exception
	 */
	public int selectCount() throws Exception {
		int result = 0;
		result = this.historyPerformanceMapper.selectHisPerformanceCount();
		return result;
	}

	/**
	 * 根据历史性能数据主键id，删除历史性能数据
	 * 
	 * @param taskId
	 *            主键id
	 * @return
	 * @throws Exception
	 */
	public int delete(int hisId) throws Exception {
		if (hisId == 0) {
			throw new Exception("hisId is null");
		}
		int resultcesId = 0;
		try {
			resultcesId = this.historyPerformanceMapper.deleteById(hisId);
			sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return resultcesId;
	}

	/**
	 * 根据历史性能数据主键集合，批量删除
	 * 
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	public int delete(List<Integer> idList) throws Exception {
		int result = 0;
		try {
			if (idList == null || idList.size() == 0) {
				return 0;
			}
			result = this.historyPerformanceMapper.deleteByIds(idList);
			sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return result;
	}

	/**
	 * 获取所有历史性能数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HisPerformanceInfo> select() throws Exception {
		List<HisPerformanceInfo> hisInfoList = null;
		try {
			HisPerformanceInfo condition = new HisPerformanceInfo();
			hisInfoList = historyPerformanceMapper.queryByCondition(condition);
			hisInfoList = wrapHisPerformanceInfo(hisInfoList, null);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return hisInfoList;
	}

	/**
	 * 封装历史性能对象
	 */
	private List<HisPerformanceInfo> wrapHisPerformanceInfo(List<HisPerformanceInfo> hisInfoList, HisPerformanceFilter filter) throws Exception {
		List<HisPerformanceInfo> filterHisPerformance = new ArrayList<HisPerformanceInfo>();
		Capability capability = null;
		Map<String, Capability> performancesMap = null;
		String[] filrertypes = null;
		Map<Integer, SiteInst> siteMap = null;
		try {
			if (filter != null) {
				filrertypes = filter.getTypeStr().trim().split(",");
			}
			if (hisInfoList != null) {
				performancesMap = ConstantUtil.capabilityMap;
				siteMap = this.getSiteMap();
				for (HisPerformanceInfo hisInfo : hisInfoList) {
					// 封装历史性能数据
					SiteInst siteInst = siteMap.get(hisInfo.getSiteId());
					if (siteInst != null) {
						hisInfo.setSiteName(siteInst.getCellId());
						if (UiUtil.getCodeById(Integer.parseInt(siteInst.getCellEditon())).getCodeName().equals("700+系列")) {
							capability = performancesMap.get(1 + "/" + hisInfo.getPerformanceCode());// 1表示武汉
						} else {
							capability = performancesMap.get(2 + "/" + hisInfo.getPerformanceCode()); // 2表示晨晓
						}
						hisInfo.setCapability(capability);
						if (hisInfo.getMonitor() == EMonitorCycle.MIN15.getValue()) {
							hisInfo.setMonitorCycle(EMonitorCycle.MIN15);
						} else {
							hisInfo.setMonitorCycle(EMonitorCycle.HOUR24);
						}
					}
					// 根据条件过滤
					if (filter != null) {
						if (capability != null) {
							for (int i = 0; i < filrertypes.length; i++) {
								if (filrertypes[i].equals(capability.getCapabilitytype())) {
									for (String strType : filter.getCapabilityNameList()) {
										if (filter.getFiterZero() > 0) {
											if (capability.getCapabilitydesc().equalsIgnoreCase(strType) && hisInfo.getMonitorCycle() == filter.getMonitorCycle() && hisInfo.getPerformanceValue() != 0 && hisInfo.getObjectName() != null) {
												filterHisPerformance.add(hisInfo);
											}
										} else {
											if (capability.getCapabilitydesc().equalsIgnoreCase(strType) && hisInfo.getMonitorCycle() == filter.getMonitorCycle() && hisInfo.getObjectName() != null) {
												filterHisPerformance.add(hisInfo);
											}
										}
									}
								}
							}
						}
					} else {
						filterHisPerformance.add(hisInfo);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return filterHisPerformance;
	}

	private Map<Integer, SiteInst> getSiteMap() {
		Map<Integer, SiteInst> siteMap = new HashMap<Integer, SiteInst>();
		try {
			SiteService_MB siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
			List<SiteInst> siteList = siteService.select();
			if (siteList != null && !siteList.isEmpty()) {
				for (SiteInst siteInst : siteList) {
					siteMap.put(siteInst.getSite_Inst_Id(), siteInst);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return siteMap;
	}

	/**
	 * 根据时间来获取部分的历史性能数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<HisPerformanceInfo> selectTime(String startTime) throws Exception {
		List<HisPerformanceInfo> hisInfoList = null;
		String endTime = DateUtil.getDate(DateUtil.FULLTIME);
		try {
			hisInfoList = historyPerformanceMapper.queryByConditionTime(startTime, endTime);
			hisInfoList = wrapHisPerformanceInfo(hisInfoList, null);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return hisInfoList;
	}

	public int selectCount(HisPerformanceFilter filter, List<Integer> siteIdList) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("filter", filter);
		if (siteIdList != null && siteIdList.size() > 0) {
			map.put("siteIdList", siteIdList);
		} else {
			map.put("siteIdList", null);
		}
		if(filter != null && filter.getObjectType() != null && filter.getObjectType() == EObjectType.SLOT){
			map.put("slotIdList", filter.getSlotInsts());
		}else{
			map.put("slotIdList", null);
		}
		if(filter != null && filter.getPerformanceCodeList() != null && filter.getPerformanceCodeList().size()>0){
			map.put("performanceCodeList", filter.getPerformanceCodeList());
		}
		return this.historyPerformanceMapper.selectCount(map);
	}

	public List<HisPerformanceInfo> selectByPage(int direction, int id, HisPerformanceFilter filter, List<Integer> siteIdList, int pageCount) {
		List<HisPerformanceInfo> hisInfoList = new ArrayList<HisPerformanceInfo>();
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			map.put("direction", direction);
			map.put("id", id);
			map.put("filter", filter);
			map.put("siteIdList", siteIdList);
			map.put("pageCount", pageCount);
			if(filter != null && filter.getObjectType() != null && filter.getObjectType() == EObjectType.SLOT){
				map.put("slotIdList", filter.getSlotInsts());
			}else{
				map.put("slotIdList", null);
			}
			hisInfoList = this.historyPerformanceMapper.selectByPage(map);
			this.wrapHisPerformanceInfo(hisInfoList);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return hisInfoList;
	}

	private void wrapHisPerformanceInfo(List<HisPerformanceInfo> performanceList) {
		Capability capability = null;
		Map<String, Capability> performancesMap = null;
		Map<Integer, SiteInst> siteMap = null;
		try {
			performancesMap = ConstantUtil.capabilityMap;
			siteMap = this.getSiteMap();
			
			for (HisPerformanceInfo hisInfo : performanceList) {
				// 封装历史性能数据
				SiteInst siteInst = siteMap.get(hisInfo.getSiteId());
				if(siteInst != null){
					hisInfo.setSiteName(siteInst.getCellId());
					if (UiUtil.getCodeById(Integer.parseInt(siteInst.getCellEditon())).getCodeName().equals("700+系列")) {
						capability = performancesMap.get(1 + "/" + hisInfo.getPerformanceCode());// 1表示武汉
					} else {
						capability = performancesMap.get(2 + "/" + hisInfo.getPerformanceCode()); // 2表示晨晓
					}
					hisInfo.setCapability(capability);
					if(hisInfo.getMonitor() == EMonitorCycle.MIN15.getValue()){
						hisInfo.setMonitorCycle(EMonitorCycle.MIN15);
					}else{
						hisInfo.setMonitorCycle(EMonitorCycle.HOUR24);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} 
	}
	
	/**
	 * 性能統計
	 * 		通過  條件 查詢  性能值
	 * @param hisInfo
	 * 		端口，板卡，網元，的 id
	 * @param code
	 * 		選擇結束時間的  code  （0-6）
	 * @param capability
	 * 		關聯關係   
	 * 			通過  CapabilityCode
	 * @param perTask
	 * 		長期性能任務對象
	 * 			取得監控週期
	 * @return
	 * @throws Exception
	 */
	public List<HisPerformanceInfo> selectPerformanceValue(HisPerformanceInfo hisInfo,int code,Capability capability)throws Exception{
		List<HisPerformanceInfo> hisList =null;
		long startTime =0;//开始时间
		long endTime = 0;//结束时间
		long nowTime = new Date().getTime();
		SimpleDateFormat sdf = null;
		String stime=null;
		String etime=null;
		try{
			hisList = new ArrayList<HisPerformanceInfo>();
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			switch (code) {
			case 0:
				//过滤选择框 已做处理（结束时间不可以选择 0）
				break;
			case 1:// 近 一個小時
				startTime = nowTime - 60 * 60 * 1000;
				endTime = nowTime;
				stime=sdf.format(new Date(startTime));
				etime=sdf.format(new Date(endTime));	
				hisList=this.historyPerformanceMapper.queryByHisPerfromance(hisInfo,code, capability,stime,etime);
				break;
			case 2:
				startTime = nowTime - 12* 60 * 60 * 1000;
				endTime = nowTime;
				stime=sdf.format(new Date(startTime));
				etime=sdf.format(new Date(endTime));
				hisList=this.historyPerformanceMapper.queryByHisPerfromance(hisInfo,code, capability,stime,etime);				
				break;
			case 3:
				startTime = nowTime - 24* 60 * 60 * 1000;
				endTime = nowTime;
				stime=sdf.format(new Date(startTime));
				etime=sdf.format(new Date(endTime));	
				hisList=this.historyPerformanceMapper.queryByHisPerfromance(hisInfo,code, capability,stime,etime);
				break;
			case 4://近   7  天
				startTime = nowTime - 7*24* 60 * 60 * 1000;
				endTime = nowTime;
				stime=sdf.format(new Date(startTime));
				etime=sdf.format(new Date(endTime));
				hisList=this.historyPerformanceMapper.queryByHisPerfromance(hisInfo,code, capability,stime,etime);
				break;
			case 5:
				startTime = nowTime - 30L * 24 * 60 * 60 * 1000;
				endTime = nowTime;
				stime=sdf.format(new Date(startTime));
				etime=sdf.format(new Date(endTime));
				hisList=this.historyPerformanceMapper.queryByHisPerfromance(hisInfo,code, capability,stime,etime);
				break;
			case 7:// 自定義    ，，  時間選擇 為完成
				hisList=this.historyPerformanceMapper.queryByHisPerfromance(hisInfo,code, capability,stime,etime);
			default:
				hisList=this.historyPerformanceMapper.queryByHisPerfromance(hisInfo,code, capability,stime,etime);
				break;
			}
			
		}catch(Exception e){
			throw e;
		}
		return hisList;
	}

	/**
	 * 跳转指定页，需要查找指定页的id
	 * @param goDir
	 * @param range
	 * @param id
	 * @param siteIdList 
	 * @param filter 
	 * @return
	 */
	public int queryIdByGoPage(int goDir, int range, int id, HisPerformanceFilter filter, List<Integer> siteIdList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goDir", goDir);
		map.put("range", range);
		map.put("id", id);
		map.put("filter", filter);
		if (siteIdList != null && siteIdList.size() > 0) {
			map.put("siteIdList", siteIdList);
		} else {
			map.put("siteIdList", null);
		}
		if(filter != null && filter.getObjectType() != null && filter.getObjectType() == EObjectType.SLOT){
			map.put("slotIdList", filter.getSlotInsts());
		}else{
			map.put("slotIdList", null);
		}
		if(filter != null && filter.getPerformanceCodeList() != null && filter.getPerformanceCodeList().size()>0){
			map.put("performanceCodeList", filter.getPerformanceCodeList());
		}
		try {
			return this.historyPerformanceMapper.queryIdByGoPage(map);
		} catch (Exception e) {
			return 0;
		}
	}
}