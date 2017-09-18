package com.nms.ui.ptn.statistics.slot;

import java.util.List;

import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.ExportExcel;
import com.nms.model.util.Services;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ListingFilter;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTab;

/**
 * 槽位统计的事件处理类
 * @author sy
 *
 */
public class SlotStatisticsController extends AbstractController {

	private SlotStatisticsPanel view;
	
	public SlotStatisticsController(SlotStatisticsPanel slotStatisticsPanel) {
		this.setView(slotStatisticsPanel);
	}

	@Override
	public void refresh() throws Exception {
		this.searchAndrefreshdata();
		
	}
	
	//导出统计数据保存到excel
	@Override
	public void export() throws Exception {
		List<SlotInst> infos = null;
		String result;
		ExportExcel export=null;
		// 得到页面信息
		try {
			infos =  this.view.getTable().getAllElement();
			export=new ExportExcel();
			//得到bean的集合转为  String[]的List
			List<String[]> beanData=export.tranListString(infos,"slotinfoTable");
			//导出页面的信息-Excel
			result=export.exportExcel(beanData, "slotinfoTable");
			//添加操作日志记录
			this.insertOpeLog(EOperationLogType.SLOTPORT.getValue(),ResultString.CONFIG_SUCCESS, null, null);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			infos = null;
			result=null;
			export=null;
		}
	}
	// 页面初始化数据、点击刷新按钮刷新数据
	private void searchAndrefreshdata() {
		List<SlotInst> infos = null;
		SlotService_MB slotService = null;
		ListingFilter filter=null;
		try {
			slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			filter=new ListingFilter();
			infos = (List<SlotInst>) filter.filterList(slotService.selectbystatistics());
			this.view.clear();
			this.view.initData(infos);
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			infos = null;
			UiUtil.closeService_MB(slotService);
			filter=null;
		}
	}
	
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
		AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac, 0,ResourceUtil.srcStr(StringKeysTab.TAB_SLOTTNFO),"");		
	}
	public void setView(SlotStatisticsPanel view) {
		this.view = view;
	}

	public SlotStatisticsPanel getView() {
		return view;
	};
}
