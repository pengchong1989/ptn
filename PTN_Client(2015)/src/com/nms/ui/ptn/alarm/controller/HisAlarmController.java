package com.nms.ui.ptn.alarm.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.nms.db.bean.alarm.HisAlarmInfo;
import com.nms.db.bean.alarm.WarningLevel;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.alarm.HisAlarmService_MB;
import com.nms.model.alarm.WarningLevelService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ListingFilter;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.alarm.model.CurrentAlarmFilter;
import com.nms.ui.ptn.alarm.view.CurrentAlarmFilterDialog;
import com.nms.ui.ptn.alarm.view.HisAlarmPanel;

/**
 * 历史告警方法处理类
 * 
 * @author lp
 * 
 */
public class HisAlarmController {
	private HisAlarmPanel view;
	private CurrentAlarmFilter filter;
	private List<HisAlarmInfo> hisAlarmList = new ArrayList<HisAlarmInfo>();
	private int direction = 1;//0/1 = 上一页/下一页
	private int totalPage = 1;//总页数
	private int currPage = 1;//当前页数
	private int minId = 0;//当前页面告警数据的最小id
	private int maxId = 0;//当前页面告警数据的最大id
	private int pageCount = 300;//每页大小
	public HisAlarmController(HisAlarmPanel view) {
		this.view = view;
		this.init();
	}

	private void init() {
		this.direction = 1;
		this.totalPage = 1;
		this.currPage = 1;
		this.maxId = 0;
		this.minId = 0;
		this.view.getTotalPageLabel().setText(this.totalPage + "");
		this.view.getCurrPageLabel().setText(this.currPage + "");
	}

	/**
	 * 清空过滤条件
	 */
	public void clearFilter() {
		this.filter = null;
		this.view.clear();
		this.init();
		this.refresh();
	}

	/**
	 * 打开设置过滤对话框
	 */
	public void openFilterDialog() {
		final CurrentAlarmFilterDialog filterDialog = new CurrentAlarmFilterDialog(2);
		if(ResourceUtil.language.equals("zh_CN")){
			filterDialog.setSize(new Dimension(550, 620));
		}else{
			filterDialog.setSize(new Dimension(570, 620));
		}
		filterDialog.setLocation(UiUtil.getWindowWidth(filterDialog.getWidth()),UiUtil.getWindowHeight(filterDialog.getHeight()));
		filterDialog.getConfirm().addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				if (filterDialog.validateParams()) {
					HisAlarmController.this.setFilter(filterDialog);
				}
			}
			@Override
			public boolean checking() {
	
				return true;
			}
		});
//		filterDialog.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosed(WindowEvent e) {
//				filterDialog.dispose();
//			}
//		});
		filterDialog.setVisible(true);
	}

	/**
	 * 设置过滤条件，并显示查询结果
	 */
	private void setFilter(CurrentAlarmFilterDialog dialog) {
		try {
			this.filter = dialog.get();
			this.getAlarmCode();
			this.view.setFilterInfos(dialog.getFilterInfo());
			this.init();
			this.refresh();
			//添加日志记录
			dialog.getConfirm().setOperateKey(EOperationLogType.HISALARMFILTERSELECT.getValue());
			dialog.getConfirm().setResult(1);
			DialogBoxUtil.succeedDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			dialog.dispose();
		}
	}

	private void getAlarmCode() {
		WarningLevelService_MB service = null;
		try {
			service = (WarningLevelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WarningLevel);
			List<WarningLevel> levelList = service.select();
			if(levelList != null){
				levelList = this.filterOAMEvent(levelList);
				for (String type : this.filter.getAlarmTypeList()) {
					for (WarningLevel level : levelList) {
						if(type.equals(level.getWarningnote())){ 
							if(this.filter.getWarningtype() > 0){
								if(level.getWarningtype() == this.filter.getWarningtype())
									this.filter.getAlarmCodeList().add(level.getWarningcode());
							}else{
								this.filter.getAlarmCodeList().add(level.getWarningcode());
							}
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}

	/**
	 * 过滤掉OAM事件
	 */
	private List<WarningLevel> filterOAMEvent(List<WarningLevel> levelList) {
		List<WarningLevel> warningList = new ArrayList<WarningLevel>();
		List<Integer> oamEventCodeList = new ArrayList<Integer>();
		oamEventCodeList.add(35);
		oamEventCodeList.add(68);
		oamEventCodeList.add(69);
		oamEventCodeList.add(70);
		oamEventCodeList.add(71);
		oamEventCodeList.add(211);
		oamEventCodeList.add(212);
		for (WarningLevel level : levelList) {
			if(!oamEventCodeList.contains(level.getWarningcode())){
				warningList.add(level);
			}
		}
		return warningList;
	}

	/**
	 * 过滤掉OAM事件
	 */
	private void filterOAMEvent(WarningLevel level) {
		if(level.getWarningcode() != 35 ||
				level.getWarningcode() != 68 ||
				level.getWarningcode() != 69 ||
				level.getWarningcode() != 70 ||
				level.getWarningcode() != 71 ||
				level.getWarningcode() != 211 ||
				level.getWarningcode() != 212){
			this.filter.getAlarmCodeList().add(level.getWarningcode());
		}
	}

	private List<HisAlarmInfo> queryAlarmByFilter(int direction, int queryId, List<Integer> siteIdList) throws Exception {
		// key为网元数据库id，value为槽位的集合
//		Map<Integer, List<Integer>> siteId2SlotIds = null;
		HisAlarmService_MB service = null;
//		List<HisAlarmInfo> hisAlarmInfos = new ArrayList<HisAlarmInfo>();
//		List<HisAlarmInfo> levAlarmInfos = new ArrayList<HisAlarmInfo>();
//		List<Integer> levelList = new ArrayList<Integer>();
//		DateUtil dataUtil = new DateUtil();
		List<HisAlarmInfo> hisAlarmList = null;
		try {
			service = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
			hisAlarmList = service.selectByPage(direction, queryId, this.filter, siteIdList, this.pageCount);
//			if (filter.getObjectType() != null && filter.getObjectType() == EObjectType.SITEINST) {
//				List<Integer> siteIdList = new ArrayList<Integer>();
//				for (SiteInst site : filter.getSiteInsts()) {
//					siteIdList.add(Integer.valueOf(site.getSite_Inst_Id()));
//				}
//				hisAlarmInfos = service.queryHisBySites(siteIdList);
//			} else if (filter.getObjectType() != null && filter.getObjectType() == EObjectType.SLOTINST) {
//				siteId2SlotIds = new HashMap<Integer, List<Integer>>();
//				// 封装网元与槽位的映射关系
//				for (int i = 0; i < filter.getSlotInsts().size(); i++) {
//					int siteId = filter.getSiteInsts().get(i).getSite_Inst_Id();
//					if (siteId2SlotIds.get(siteId) == null) {
//						siteId2SlotIds.put(siteId, new ArrayList<Integer>());
//					}
//					siteId2SlotIds.get(siteId).add(
//							filter.getSlotInsts().get(i).getId());
//				}
//				for (Integer id : siteId2SlotIds.keySet()) {
//					if (siteId2SlotIds.get(id) != null&& siteId2SlotIds.get(id).size() > 0) {
//						List<HisAlarmInfo> hisAlarmInfoLists = service.queryHisBySlots(id, siteId2SlotIds.get(id));
//						if (hisAlarmInfoLists != null&& hisAlarmInfoLists.size() > 0) {
//							hisAlarmInfos.addAll(hisAlarmInfoLists);
//						}
//					}
//				}
//			}else{
//				hisAlarmInfos.addAll(service.alarmByAlarmLevel(2));
//				//设置级别过滤
//			}
//			levelList = filter.getAlarmLevel();
//			
//			for(HisAlarmInfo alarm : hisAlarmInfos){
//				if(filter.getAlarmState().equals("1")){
//					for(String aramTypeName: filter.getAlarmTypeList()){
//						if(alarm.getWarningLevel().getWarningnote().equalsIgnoreCase(aramTypeName)
//								&& alarm.getAckUser()!= null
//								&& isLevel(alarm.getWarningLevel_temp(),levelList)){
//							checkUpTime(levAlarmInfos,alarm,dataUtil);
//					    }
//					}
//				}else{
//					for(String aramTypeName: filter.getAlarmTypeList()){
//						if(alarm.getWarningLevel().getWarningnote().equalsIgnoreCase(aramTypeName)&&alarm.getAckUser()== null
//							&& isLevel(alarm.getWarningLevel_temp(),levelList)){
//							checkUpTime(levAlarmInfos,alarm,dataUtil);
//						}
//					}
//				}
//			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			throw e;
		} finally {
			UiUtil.closeService_MB(service);
		}
		return hisAlarmList;
//		if(levelList.size() == 0)
//		{
//			return hisAlarmInfos;
//		}
//		else
//		{
//			return levAlarmInfos;
//		}
		
	}

	/**
	 * 刷新监听器处理方法
	 */
	public void refresh() {
		HisAlarmService_MB service = null;
		try {
			service = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
			if(this.maxId == 0){
				this.updateUI(service.selectMaxId()+1, 1);
			}else{
				this.updateUI(this.maxId+1, 3);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}

	private List<Integer> getSiteIdList() {
		List<Integer> siteIdList = new ArrayList<Integer>();
		for (SiteInst site : this.filter.getSiteInsts()) {
			siteIdList.add(Integer.valueOf(site.getSite_Inst_Id()));
		}
		return siteIdList;
	}

	private boolean isLevel(int mylevel, List<Integer> levelList)
	{
		boolean islevel = false;
		for(int level: levelList)
		{
			if(level == mylevel)
			{
				islevel = true;
				break;
			}
		}
		return islevel;
	}
	
	public HisAlarmPanel getView() {
		return view;
	}

	public List<HisAlarmInfo> getHisAlarmInfos() {
		return hisAlarmList;
	}

	public void setHisAlarmInfos(List<HisAlarmInfo> hisAlarmInfos) {
		this.hisAlarmList = hisAlarmInfos;
	}
	
	private void checkUpTime(List<HisAlarmInfo> levAlarmInfos,HisAlarmInfo info,DateUtil dataUtil){
		
		try {
			
             if(validateParamsAll(info,dataUtil)){
            	 levAlarmInfos.add(info);
             }	
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
	}
	
	private boolean validateParamsAll(HisAlarmInfo info,DateUtil dataUtil){
		boolean flag = false;
		try {
			if(filter != null){
				//发生时间
	             if(!validateParamsTime(info.getRaisedTime(),dataUtil,filter.getHappenTime(),filter.getHappenEndTime())){
	            	 return false;
	             }				
	            //确定时间
	             if(!validateParamsTime(info.getAckTime(),dataUtil,filter.getEnsureTime(),filter.getEnsureEndTime())){
	            	 return false;
	             }
	           //清除时间
	             if(!validateParamsTime(info.getClearedTime(),dataUtil,filter.getClearTime(),filter.getClearEndTime())){
	            	 return false;
	             }
	           //告警类型
	             if(filter.getWarningtype() > 0 && (info.getWarningLevel().getWarningtype() != filter.getWarningtype())){
	            	 return false;
	             }
	             
	             if(filter.getEnsureUser() != null){
	            	 if(!filter.getEnsureUser().equals(info.getAckUser())){
	            		 return false;
	            	 }
	             }
			}
			flag = true;
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return flag ; 
	}
	
	private boolean validateParamsTime(Date neTime,DateUtil dataUtil,String timeString,String timeEndString){
		boolean flga = false;
		long startTime = 0l;
		long startEndTime = 0l;
		try {
			if(timeString != null && !"".equals(timeString)){
				 startTime = dataUtil.updateTimeToLong(timeString, DateUtil.FULLTIME);
				 startEndTime = dataUtil.updateTimeToLong(timeEndString, DateUtil.FULLTIME);
				 if(neTime == null|| (startTime > neTime.getTime() || neTime.getTime() > startEndTime)){
					 return false;
				 }
			}
			flga = true;
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return flga;
	}

	public void prevPage() {
		this.direction = 0;
		this.pageTurning();
		if(this.currPage > 1){
			this.currPage -= 1;
		}
		if(this.currPage == 1){
			this.view.getPrevPageBtn().setEnabled(false);
		}
		this.view.getNextPageBtn().setEnabled(true);
		this.view.getCurrPageLabel().setText(this.currPage + "");
	}
	
	public void nextPage() {
		this.direction = 1;
		this.pageTurning();
		if(this.currPage < this.totalPage){
			this.currPage += 1;
		}
		this.view.getPrevPageBtn().setEnabled(true);
		if(this.currPage == this.totalPage){
			this.view.getNextPageBtn().setEnabled(false);
		}
		this.view.getCurrPageLabel().setText(this.currPage + "");
	}
	
	/**
	 * 翻页
	 */
	private void pageTurning(){
		try {
			int id = 0;
			if(this.direction == 0){
				id = this.maxId;
			}else{
				id = this.minId;
			}
			this.updateUI(id, this.direction);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private void updateUI(int id, int type){
		HisAlarmService_MB service = null;
		ListingFilter listFilter = null;
		try {
			this.hisAlarmList.clear();
			listFilter = new ListingFilter();
			service = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
			List<Integer> siteIdList = null;
			if (filter == null) {
				siteIdList = listFilter.getSiteIdListAll();
			}else{
				siteIdList = this.getSiteIdList();
			}
			List<Integer> idList = service.selectCurrAlarmId(this.filter, siteIdList);
			id = this.getCurrAlarmId(idList, this.maxId == 0 ? 0 : this.currPage, type);
			if(id != -1){
				this.hisAlarmList.addAll(service.selectByPage(type == 3?1:this.direction, id, this.filter, siteIdList, this.pageCount));
			}
			int	count = idList.size();
			this.totalPage = count%this.pageCount == 0 ? count/this.pageCount : (count/this.pageCount+1);
			//如果总页数小于当前页数，说明数据被转储，要重新查询
			if(this.totalPage > 0 && this.totalPage < this.currPage){
				this.init();
				this.refresh();
			}
			if(this.totalPage == 0){
				this.totalPage = 1;
			}
			this.view.getTotalPageLabel().setText(this.totalPage + "");
			this.view.getCurrPageLabel().setText(this.currPage + "");
			this.view.getPrevPageBtn().setEnabled(true);
			this.view.getNextPageBtn().setEnabled(true);
			if(this.currPage == 1){
				this.view.getPrevPageBtn().setEnabled(false);
			}
			if(this.currPage == this.totalPage){
				this.view.getNextPageBtn().setEnabled(false);
			}
			if(this.hisAlarmList.size() > 0){
				int minid = this.hisAlarmList.get(0).getId();
				int maxid = this.hisAlarmList.get(this.hisAlarmList.size() - 1).getId();
				if(minid < maxid){
					this.minId = minid;
					this.maxId = maxid;
				}else{
					List<HisAlarmInfo> hisAlarmInfoList = new ArrayList<HisAlarmInfo>();
					for (int i = (this.hisAlarmList.size()-1); i >= 0; i--) {
						hisAlarmInfoList.add(this.hisAlarmList.get(i));
					}
					this.hisAlarmList.clear();
					this.hisAlarmList.addAll(hisAlarmInfoList);
					this.minId = maxid;
					this.maxId = minid;
				}
			}
			this.view.getBox().clear();
			List<HisAlarmInfo> cList = new ArrayList<HisAlarmInfo>();
			for (int i = this.hisAlarmList.size()-1; i >=0; i--) {
				cList.add(this.hisAlarmList.get(i));
			}
			this.hisAlarmList.clear();
			this.hisAlarmList.addAll(cList);
			//以时间排序
			this.sortListByTime(cList);
			this.view.initData(cList);
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void sortListByTime(List<HisAlarmInfo> cList) {
		Collections.sort(cList, new SortClass());
	}
	
	@SuppressWarnings("unchecked")
	private class SortClass implements Comparator{
		public int compare(Object arg0,Object arg1){
			HisAlarmInfo c0 = (HisAlarmInfo)arg0;
			HisAlarmInfo c1 = (HisAlarmInfo)arg1;
		    int flag = c1.getRaisedTime().compareTo(c0.getRaisedTime());
		    return flag;
		}
	}
	
	private int getCurrAlarmId(List<Integer> idList, int currentPage, int type){
		if(!idList.isEmpty()){
			if(currentPage == 0){
				return idList.get(0)+1;
			}else{
				//下一页,要取区间的最小值
				if(type == 1){
					if(idList.size() > pageCount*currentPage){
						return idList.get(pageCount*currentPage-1);
					}else if(idList.size() > pageCount*(currentPage-1)){
						return idList.get(pageCount*(currentPage-1));
					}else{
						return -1;
					}
				}else if(type == 0){
					//上一页，要取区间的最大值
					if(idList.size() > pageCount*(currentPage-1)){
						return idList.get(pageCount*(currentPage-1));
					}else if(currentPage > 1 && idList.size() > pageCount*(currentPage-2)){
						return idList.get(idList.size()-1)-1;
					}else{
						return -1;
					}
				}else{
					//当前页刷新
					if(idList.size() > pageCount*(currentPage-1)){
						if(currentPage == 1){
							return idList.get(0)+1;
						}else{
							return idList.get(pageCount*(currentPage-1)-1);
						}
					}else{
						return -1;
					}
				}
			}
		}else{
			return 0;
		}
	}
}
