package com.nms.snmp.ninteface.impl.performance;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import com.nms.snmp.ninteface.impl.ftp.FtpTransFile4SNMP;
import com.nms.ui.manager.ExceptionManage;

public class PerformanceTrapTimerTask extends TimerTask {
	//根据长期性能任务id去采集
	private List<Integer> taskIdList = new ArrayList<Integer>();
	//采集粒度 1/2 15min/24hour
	private int type = 0;
	public PerformanceTrapTimerTask(List<Integer> taskIdList, int type) {
		this.taskIdList = taskIdList;
		this.type = type;
	}

	@Override
	public void run() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		HisPerformanceConvertXml convert = new HisPerformanceConvertXml();
		try {
			String startTime = format.format(System.currentTimeMillis());
			if(this.type == 1){
				String min = startTime.substring(10, 12);
				min = (Integer.parseInt(min)/15)*15+"";
				if(min.equals("0")){
					min = "00";
				}
				startTime = startTime.substring(0, 10)+min+"00";
			}else{
//				startTime = startTime.substring(0, 8)+"000000";
			}
			//20140801164500  2014-08-01 16:45:00
			startTime = startTime.substring(0, 4)+"-"+startTime.substring(4, 6)+"-"+
						startTime.substring(6, 8)+" "+startTime.substring(8, 10)+
						":"+startTime.substring(10, 12)+":"+startTime.substring(12, 14);
			String endTime = startTime;
			String[] happenedTime = {startTime, endTime};
			String filePath = convert.getHisPerformanceXml(happenedTime, taskIdList, type);
			//调用工具上传文件
			if(!"".equals(filePath)){
				List<String> transFileList = new ArrayList<String>();
				transFileList.add(filePath+".zip");
				FtpTransFile4SNMP ftp = new FtpTransFile4SNMP(transFileList);
				try {
					ftp.FileTranfer();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
