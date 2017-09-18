package com.nms.ui.ptn.statistics.sement;

import java.util.List;
import com.nms.db.bean.path.Segment;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.path.SegmentService_MB;
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
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysTab;

public class SegmentStatisticsController extends AbstractController{
	private SegmentStatisticsPanel view;

	SegmentStatisticsController(SegmentStatisticsPanel segmentstatisticsPanel){
		this.setView(segmentstatisticsPanel);
	}
	


	@Override
	public void refresh() throws Exception {
		this.searchAndrefreshdata();
		
	}
	//导出统计数据保存到excel
	@Override
	public void export() throws Exception {
		List<Segment> infos = null;
		String result;
		ExportExcel export=null;
		// 得到页面信息
		try {
			infos =  this.view.getTable().getAllElement();
			export=new ExportExcel();
			//得到bean的集合转为  String[]的List
			List<String[]> beanData=export.tranListString(infos,"segmentinfoTable");
			//导出页面的信息-Excel
			result=export.exportExcel(beanData, "segmentinfoTable");
			//添加操作日志记录
			this.insertOpeLog(EOperationLogType.SITEPHYPATHEXPORT.getValue(),ResultString.CONFIG_SUCCESS, null, null);
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
		List<Segment> infos = null;
		SegmentService_MB segmentService = null;
		ListingFilter filter=null;
		try {
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			filter=new ListingFilter();
			infos = (List<Segment>) filter.filterList(segmentService.select());
			this.view.clear();
			this.view.initData(infos);
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally {
			infos = null;
			UiUtil.closeService_MB(segmentService);
			filter=null;
		}
	}
	
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
		AddOperateLog.insertOperLog(null, operationType, result, oldMac, newMac, 0,ResourceUtil.srcStr(StringKeysTab.TAB_SEGMENTNFO),"");		
	}
	
	public SegmentStatisticsPanel getView() {
		return view;
	}

	public void setView(SegmentStatisticsPanel view) {
		this.view = view;
	}
}


