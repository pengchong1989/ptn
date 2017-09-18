package com.nms.ui.ptn.performance.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.nms.db.bean.perform.HisPerformanceInfo;
import com.nms.drive.analysis.AnalysisObjectService;
import com.nms.drive.service.bean.PerformanceObject;
import com.nms.model.perform.HisPerformanceService_Mb;
import com.nms.model.util.Services;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.wh.PerformanceWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class PerformanceTimerThread  extends Thread{

	private boolean  isRun = false;
	private AnalysisObjectService analysisObjectService = null;
	
	public PerformanceTimerThread()
	{
		analysisObjectService = new AnalysisObjectService();
		this.setName("performancTask");
		this.start();
	}
	
	public void stopThread()
	{
		this.isRun = false;
	}
	
	
	public void startThread()
	{
		this.isRun = true;
	}
	
	
	@Override
	public void run()
	{
		while(isRun)
		{
			//处理数据
			analysisPerformancData();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void analysisPerformancData() {
		HisPerformanceService_Mb hisPerformanceService = null;
		try {
			hisPerformanceService = (HisPerformanceService_Mb) ConstantUtil.serviceFactory.newService_MB(Services.HisPerformance);
			ConcurrentHashMap<String, byte[]> performanceDataMap = ConstantUtil.PerformanceObjectService.getHisPerformanceMap();
			byte[] data ;
			if(!performanceDataMap.isEmpty())
			{
				for(String str : performanceDataMap.keySet())
				{
					data = performanceDataMap.get(str);
					performanceDataMap.remove(str);
					analysisInsertDb(data,hisPerformanceService);
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			UiUtil.closeService_MB(hisPerformanceService);
		}
		
	}

	private void analysisInsertDb(byte[] data,HisPerformanceService_Mb hisPerformanceService) {
		List<ActionObject> actionObjectList = null;
		ActionObject actionObject = null;
		OperationObject operationObject = null;
		PerformanceWHServiceImpl performanceWHServiceImpl = null;
		try {
			performanceWHServiceImpl = new PerformanceWHServiceImpl();
			PerformanceObject performanceObject = analysisObjectService.AnalysisCommadsToPerformanceObjectBySite(data);
			actionObjectList = new ArrayList<ActionObject>();
			actionObject = new ActionObject();
			actionObject.setPerformanceObject(performanceObject);
			actionObjectList.add(actionObject);
			operationObject = new OperationObject();
			operationObject.setActionObjectList(actionObjectList);
			List<HisPerformanceInfo> hisPerformance = performanceWHServiceImpl.insertHisPerformanceInfo(operationObject, "");
			hisPerformanceService.insertAll(hisPerformance);
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			 actionObjectList = null;
			 actionObject = null;
			 operationObject = null;
			 performanceWHServiceImpl = null;
		}
	}
}
