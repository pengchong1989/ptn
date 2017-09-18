package com.nms.ui.ptn.event.oamEvent.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nms.db.bean.alarm.HisAlarmInfo;
import com.nms.db.bean.event.OamEventInfo;
import com.nms.model.alarm.HisAlarmService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ListingFilter;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.event.oamEvent.view.OamEventPanel;

public class OamEventController extends AbstractController{

	private OamEventPanel view;
	public OamEventController(OamEventPanel oamEventPanel) {
		view = oamEventPanel;
		try {
			refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 由于武汉设备暂时不支持事件的上报，把oam事件当告警上报给网管
	 * 所以，oam事件界面暂时从历史告警取数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void refresh() throws Exception {
		List<HisAlarmInfo> hisAlarmInfos = null;
		HisAlarmService_MB hisAlarmService = null;
		List<OamEventInfo> oamEventInfos = null;
		OamEventInfo eventInfo = null;
		OamEventInfo eventInfo2 = null;
		DateFormat dateFormat = null;
		Date date = null;
		ListingFilter listingFilter=null;
		oamEventInfos = new ArrayList<OamEventInfo>();
		try {
			hisAlarmService = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
			hisAlarmInfos = hisAlarmService.select();
			if(hisAlarmInfos != null){
				for(HisAlarmInfo hisAlarmInfo : hisAlarmInfos){
					if("OAM_PEER_ERR_SYMBOL".equals(hisAlarmInfo.getWarningLevel().getWarningname())
							||"OAM_PEER_ERR_FRAME".equals(hisAlarmInfo.getWarningLevel().getWarningname())
							||"OAM_PEER_ERR_FRAME_PERIOD".equals(hisAlarmInfo.getWarningLevel().getWarningname())
							||"OAM_PEER_ERR_FRAME_SECOND".equals(hisAlarmInfo.getWarningLevel().getWarningname())
							||"OAM_PEER_DISCOVERY".equals(hisAlarmInfo.getWarningLevel().getWarningname())
							||"OAM_PEER_DYING_GASP".equals(hisAlarmInfo.getWarningLevel().getWarningname())
							||"OAM_LOOPBACK_TIMEOUT".equals(hisAlarmInfo.getWarningLevel().getWarningname())){
						
						date = new Date(hisAlarmInfo.getClearedTime().getTime()-20000);
					eventInfo = new OamEventInfo();
					eventInfo2 = new OamEventInfo();
					eventInfo.setEventStatus(0);
						eventInfo.setSiteName(hisAlarmInfo.getSiteName());
						eventInfo.setSource(hisAlarmInfo.getObjectName());
						eventInfo.setSiteId(hisAlarmInfo.getSiteId());
					dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					eventInfo.setTime(dateFormat.format(date));
						eventInfo.setEventName(hisAlarmInfo.getWarningLevel().getWarningnote());
					eventInfo2.setEventStatus(1);
						eventInfo2.setSiteName(hisAlarmInfo.getSiteName());
						eventInfo2.setSource(hisAlarmInfo.getObjectName());
						eventInfo2.setSiteId(hisAlarmInfo.getSiteId());
						eventInfo2.setTime(dateFormat.format(hisAlarmInfo.getClearedTime()));
						eventInfo2.setEventName(hisAlarmInfo.getWarningLevel().getWarningnote());
					oamEventInfos.add(eventInfo2);	
				}
			}
			}
			listingFilter=new ListingFilter();
			oamEventInfos=(List<OamEventInfo>) listingFilter.filterList(oamEventInfos);
			view.clear();
			view.initData(oamEventInfos);
			view.updateUI();
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(hisAlarmService);
			listingFilter=null;
		}
	}
}
