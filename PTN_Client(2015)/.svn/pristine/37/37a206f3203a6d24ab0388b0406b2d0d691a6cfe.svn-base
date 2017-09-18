package com.nms.ui.ptn.alarm.tca;

import java.util.List;

import com.nms.db.bean.alarm.TCAAlarm;
import com.nms.model.alarm.TCAAlarmService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ListingFilter;
import com.nms.ui.manager.UiUtil;

/**
 * tca告警按钮控制类
 * 
 * @author kk
 * 
 */
public class TCAAlarmController extends AbstractController {

	private TCAAlarmPanel tcaAlarmPanel;

	public TCAAlarmController(TCAAlarmPanel tcaAlarmPanel) {
		this.tcaAlarmPanel = tcaAlarmPanel;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void refresh() throws Exception {
		TCAAlarmService_MB tcaAlarmService = null;
		List<TCAAlarm> tcaAlarmList = null;
		ListingFilter listingFilter=null;
		try {
			listingFilter=new ListingFilter();
			tcaAlarmService = (TCAAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.TCAALARM);
			tcaAlarmList = (List<TCAAlarm>) listingFilter.filterList(tcaAlarmService.selectAll());
			
			this.tcaAlarmPanel.clear();
			this.tcaAlarmPanel.initData(tcaAlarmList);

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(tcaAlarmService);
			tcaAlarmList = null;
			listingFilter=null;
		}
	}

}
