package com.nms.corba.ninterface.impl.resource.proxy;

import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.omg.CORBA.StringHolder;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.util.DateTimeUtil;

	public class CorbaTimeMgrProxy {
	
		/**
		 * 查询厂商网管系统的当前时间
		 * @param emsName 网管系统标示符
		 * @param emsTime 查询的时间
		 * @throws ProcessingFailureException
		 */
		public void getEMSTime(NameAndStringValue_T[] emsName, StringHolder emsTime)
				throws ProcessingFailureException {
			//判断网管系统不为空，name和value都符合命名规则
			if (null == emsName || emsName.length == 0 || !emsName[0].value.equals(CorbaConfig.getInstanse().getCorbaEmsName())
					|| !emsName[0].name.equals("EMS")) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			}
			Date date = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			emsTime.value = sdf.format(date);
		}
		
		/**
		 * 设置单个厂商网管系统的当前时间
		 * @param emsName 网管系统标示符
		 * @param time 设置的时间值
		 * @return true 修改成功 ；false 反之
		 * @throws ProcessingFailureException
		 */
		public boolean setEMSTime(NameAndStringValue_T[] emsName, String time)
			throws ProcessingFailureException {
			//判断网管系统不为空，name和value都符合命名规则
			if (null == emsName || emsName.length == 0 || !emsName[0].value.equals(CorbaConfig.getInstanse().getCorbaEmsName())
					|| !emsName[0].name.equals("EMS")||null == time||"".equals(time.trim())) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			}
			String timeCvt = DateTimeUtil.dateFormat(time);
			String ymd = timeCvt.substring(0, timeCvt.indexOf(" "));//年月日
			String[] arr = ymd.split("-");//记录年月日值的数组
			ymd = "";
			for (int j = 0; j < arr.length; j++) {
				if (j!=0 && arr[j].length() <2) {
					arr[j] = "0"+arr[j];
				}
				ymd += arr[j]+"-";
			}
			arr = null;
			String kms = timeCvt.substring(timeCvt.indexOf(" ")+1);//时分秒
			arr = kms.split(":");//记录时分秒数组
			kms = "";
			for (int j = 0; j < arr.length; j++) {
				if (arr[j].length() <2) {
					arr[j] = "0"+arr[j];
				}
				kms += arr[j]+":";
			}
			arr = null;
			
			String osName = System.getProperty("os.name");
			String cmd = "";
			try {
				if (osName.matches("^(?i)Windows.*$")) {
					//windows 系统
					cmd = " cmd /c time "+kms.substring(0, kms.lastIndexOf(":"));
					Runtime.getRuntime().exec(cmd);
					cmd = " cmd /c date "+ymd.substring(0, ymd.lastIndexOf("-"));
					Runtime.getRuntime().exec(cmd);
				}else {
					// Linux 系统   
					cmd = " date -s "+ymd.replaceAll("[^0-9]", "");
					Runtime.getRuntime().exec(cmd);
					cmd = "  date -s "+kms.substring(0, kms.lastIndexOf(":"));
					Runtime.getRuntime().exec(cmd);
				}
			} catch (IOException e) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"setEMSTime ex.");
			}  
			return true;
		}
}
