
package com.nms.ui.ptn.alarm.controller;

import java.util.List;

import com.nms.db.bean.alarm.OamEvent;
import com.nms.db.bean.system.OperationLog;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.system.OperationLogService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.alarm.service.CSVUtil;
import com.nms.ui.ptn.alarm.view.SiteOamEventPanel;

/**
 * 当前告警事件处理类
 * 
 * @author 
 * 
 */
public class SiteOamEventController extends AbstractController{
	private  SiteOamEventPanel view;
	private List<OamEvent> currInfos;

	public SiteOamEventController(SiteOamEventPanel view) {
		this.view = view;
	}

	/**
	 * 刷新按钮事件处理方法 先设置过滤条件后，才能显示刷新结果
	 */
	public void refresh() throws Exception{
//		SiteOamEventService Service = null;
//		List<OamEvent> oList = null;
//		try {
//			Service = (SiteOamEventService) ConstantUtil.serviceFactory.newService(Services.OAMEVENT);
//			oList = Service.select(ConstantUtil.siteId);
//			this.view.clear();
//			this.view.initData(oList);
//		} catch (Exception e) {
//			throw e;
//		} finally {
//			UiUtil.closeService(Service);
//		}
	}
	
	/**
	 *导出功能 
	 * @throws Exception
	 */
	public void export(){
		CSVUtil csvUtil=null;
		String[] s={};
		String path=null;
		OperationLog operationLog=null;
		OperationLogService_MB operationService=null;
		try{
			csvUtil=new CSVUtil();
	        operationLog=new OperationLog();
			operationLog.setOperationType(EOperationLogType.HISALARMEXPORT.getValue());
			path=csvUtil.showChooserWindow("save","选择文件",s);
			operationLog.setStartTime(DateUtil.getDate(DateUtil.FULLTIME));	
			csvUtil.WriteOamEvenCsv(path,currInfos);
			operationLog.setOverTime(DateUtil.getDate(DateUtil.FULLTIME));
			operationLog.setOperationResult(1);
			operationLog.setUser_id(ConstantUtil.user.getUser_Id());
			operationService=(OperationLogService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OPERATIONLOGSERVIECE);				
			operationService.insertOperationLog(operationLog);
		}catch(Exception e){
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(operationService);
		}
	}
	
	
	/**
	 * 设置试图
	 * @return
	 */
	public SiteOamEventPanel getView() {
		return view;
	}
}
