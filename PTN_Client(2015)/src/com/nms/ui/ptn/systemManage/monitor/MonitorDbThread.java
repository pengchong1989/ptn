package com.nms.ui.ptn.systemManage.monitor;

import com.nms.db.bean.ptn.DbInfoTask;
import com.nms.db.bean.system.DataBaseInfo;
import com.nms.model.system.DataBaseService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.alarm.AlarmTools;

/**
 * <p>文件名称:MonitorDbThread.java</p>
 * <p>文件描述:监控数据库内存</p>
 * <p>版权所有: 版权所有(C)2013-2015</p>
 * <p>公    司: 北京建博信通软件技术有限公司</p>
 * <p>内容摘要:</p>
 * <p>其他说明: </p>
 * <p>完成日期: 2015年2月11日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author zhangkun
 */
public class MonitorDbThread implements Runnable{

	/***************执行监控事件*******************/
	private String threadName = "";
	
	public MonitorDbThread(String threadName)
	{
		this.threadName = threadName;
	}
	
	
	
	@Override
	public void run()
	{
		analysisDBThread();
	}
	
	
	private  synchronized  void analysisDBThread()
	{
		boolean flag = true;
		while(flag){
			DataBaseService_MB dataBaseService  =  null;
			DbInfoTask dbInfoTask = null;
			try {
				dataBaseService = (DataBaseService_MB)ConstantUtil.serviceFactory.newService_MB(Services.DATABASEINFO);
				dbInfoTask = dataBaseService.selectMoinTableInfo("ptn","1");
				/**************首先是判断数据库的总空间是否达到告警**************************/
				AlarmTools  alarmTools = new AlarmTools(1);
				if(dbInfoTask.isMointorTotal())
				{
					if(dbInfoTask.getTotalDbSpace()*1024 < getAllDBTotal(dbInfoTask))
					{
						/*************产生一条告警**************/	
						alarmTools.createMointorClintAndService( 1052, 2,ResourceUtil.srcStr(StringKeysTip.TIP_LABEL_DBTOTAL),0);
					}
				}
				/***************判断的每张表的告警*************************/
				if(dbInfoTask.isMointorTypeDb())
				{
					if(dbInfoTask != null && dbInfoTask.getDaTableList().size()>0)
					{
						for(DataBaseInfo dataBaseInfo : dbInfoTask.getDaTableList())
						{
							if(dataBaseInfo.getMointorType() == 1)
							{
								double rate = (dataBaseInfo.getDataSize()/dataBaseInfo.getCountSize())*100;
								if(!dataBaseInfo.getCriticalRate().equals("")&&(rate > Double.parseDouble(dataBaseInfo.getCriticalRate()))){
									alarmTools.createMointorClintAndService(1053, 5, dataBaseInfo.getName(),0);
								} 
								if(!dataBaseInfo.getMajorRate().equals("")&&(rate > Double.parseDouble(dataBaseInfo.getMajorRate())))
								{
									alarmTools.createMointorClintAndService(1054, 4, dataBaseInfo.getName(),0);
								}
								if(!dataBaseInfo.getMinorRate().equals("")&& (rate > Double.parseDouble(dataBaseInfo.getMinorRate())))
								{
									alarmTools.createMointorClintAndService(1055, 3, dataBaseInfo.getName(),0);
								}
								if(!dataBaseInfo.getWarningRate().equals("")&& rate > Double.parseDouble(dataBaseInfo.getWarningRate()))
								{
									alarmTools.createMointorClintAndService(1056, 2, dataBaseInfo.getName(),0);
								}
							}
						}
					}
				}
			} catch (Exception e) {
				flag = false;
				ExceptionManage.dispose(e, getClass());
			}finally{
				UiUtil.closeService_MB(dataBaseService);
					try {
					Thread thread = ConstantUtil.threadMap.get(threadName);
					if(thread == null)
					{
						System.out.println("thread == null");
						flag = false;
					}
					else {
						if(thread.isInterrupted())
						{
							flag = false;
						}
						Thread.sleep(dbInfoTask.getMiintorCycle()*1000*60);
					}
					} catch (Exception e) {
						flag = false;
				}
			}
		}
	}
	/**
	 * 获取所有表的总空间大小
	 * @param dbInfoTask
	 * @return
	 */
	private double getAllDBTotal(DbInfoTask dbInfoTask){
		double totalDb = 0;
		try {
			
			if(dbInfoTask.getDaTableList() != null && dbInfoTask.getDaTableList().size() >0){
				for(DataBaseInfo dataBaseInfo : dbInfoTask.getDaTableList()){
					totalDb = totalDb + dataBaseInfo.getDataSize();
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return totalDb;
	}

}
