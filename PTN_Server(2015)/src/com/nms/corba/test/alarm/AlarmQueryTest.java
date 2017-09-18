package com.nms.corba.test.alarm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import managedElementManager.ManagedElementMgr_I;
import notifications.AlarmType_T;
import notifications.EventIterator_IHolder;
import notifications.EventList_THolder;
import notifications.PerceivedSeverity_T;
import notifications.QueryAlarmFilter_T;

import org.omg.CosNotification.StructuredEvent;

import com.nms.corba.test.common.CorbaConnect;
import com.nms.db.bean.alarm.HisAlarmInfo;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.ptn.alarm.AlarmTools;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import emsMgr.EMSMgr_IHelper;
import fileTransfer.FileTransferMgr_I;
import fileTransfer.FileTransferMgr_IHelper;
import globaldefs.Duration_T;
import globaldefs.NameAndStringValue_T;

public class AlarmQueryTest
{
	//网元管理者
	ManagedElementMgr_I meMgr = null;
    //ems管理者
	EMSMgr_I emsMgr = null;
	FileTransferMgr_I fileTransferMgr = null;
	public static void main(String args[]) {
		try {
			CorbaConnect connect = new CorbaConnect();
			if(connect.isConnect())
			{
				new AlarmQueryTest().requireHistoryAlarmFileTransfer(connect);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,AlarmQueryTest.class);
		}
	}
	
	public void requireHistoryAlarmFileTransfer(CorbaConnect connect) {
		try {
			
			//获取管理者
			Common_IHolder mgrHolder = new Common_IHolder();
			//不同接口管理者名称不同
			connect.emsSession.getManager("FileTransfer", mgrHolder);
			fileTransferMgr = FileTransferMgr_IHelper.narrow(mgrHolder.value);
			
			String destination  = "127.0.0.1|/hisalarm.txt";
			String userName = "admin";
			String passWord = "admin";
			QueryAlarmFilter_T queryCondition = new QueryAlarmFilter_T();
			queryCondition.alarmTypeSelect = new AlarmType_T[]{};
			queryCondition.alarmSourceSelect = new NameAndStringValue_T[][]{};
			queryCondition.clearTimeDuration = new Duration_T();
			queryCondition.clearTimeDuration.fromTime = new String("2014-04-18 11:45:49");
			queryCondition.clearTimeDuration.toTime = new String("2014-05-18 11:45:49");
			queryCondition.perceivedSeveritySelect = new PerceivedSeverity_T[1];
			queryCondition.perceivedSeveritySelect[0] = PerceivedSeverity_T.from_int(1);
			queryCondition.probableCauseSelect =new String[1];
			queryCondition.probableCauseSelect[0]="";
			queryCondition.raiseTimeDuration = new Duration_T();
			queryCondition.raiseTimeDuration.fromTime = new String("2014-04-18 11:45:49");
			queryCondition.raiseTimeDuration.toTime = new String("2014-06-18 11:45:49");
			
			fileTransferMgr.requireHistoryAlarmFileTransfer(destination,
					userName, passWord,  queryCondition);
		}
		catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public void getAllActiveAlarmsByCond(CorbaConnect connect) {
		try {
			
			//获取管理者
			Common_IHolder mgrHolder = new Common_IHolder();
			//不同接口管理者名称不同
			connect.emsSession.getManager("EMS", mgrHolder);
			emsMgr = EMSMgr_IHelper.narrow(mgrHolder.value);
			
			String startTime="2014-04-18 11:45:49";
			String endTime="2014-05-18 11:45:49";
			NameAndStringValue_T[][] objectName= new NameAndStringValue_T[1][1];
			objectName[0][0] = new NameAndStringValue_T();
			String[]excludeProbCauseList={"",""}; 
			PerceivedSeverity_T[] excludeSeverityList={};
			AlarmType_T type[] = {AlarmType_T.from_int(0)};
			int how_many = 1;
			EventList_THolder eventList=new EventList_THolder();
			eventList.value = new StructuredEvent[11]; 
			EventIterator_IHolder eventIt = new EventIterator_IHolder();
			
			emsMgr.getAllActiveAlarmsByCond(startTime, endTime, objectName, 
											excludeProbCauseList, excludeSeverityList, type, 
											how_many,eventList, eventIt);
			
			System.out.println("查询当前告警OK");
			if(eventList.value != null && eventList.value.length > 0)
			{
				//打印eventList在的告警
				printEventList(eventList);
			}
			
			if(eventIt.value != null)
			{
				while(eventIt.value.next_n(1, eventList))
				{
					printEventList(eventList);
				}
				
				printEventList(eventList);
				
				eventIt.value.destroy();
			}
			
		}
		catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public void printEventList(EventList_THolder eventList)
	{
		for(int i = 0; i < eventList.value.length; i++)
		{
			StringBuffer sb = new StringBuffer();
			sb.append("第"+i+"条告警");
			sb.append(eventList.value[i].filterable_data[0].name + " ");	
			sb.append(eventList.value[i].filterable_data[0].value.extract_string() + " ");
			sb.append(eventList.value[i].filterable_data[1].name + " ");	
			sb.append(eventList.value[i].filterable_data[1].value.extract_long() + " ");
			sb.append(eventList.value[i].filterable_data[2].name + " ");	
			sb.append(eventList.value[i].filterable_data[2].value.extract_string() + " ");
			sb.append(eventList.value[i].filterable_data[3].name + " ");	
			sb.append(eventList.value[i].filterable_data[3].value.extract_string() + " ");
			sb.append(eventList.value[i].filterable_data[4].name + " ");	
			sb.append(eventList.value[i].filterable_data[4].value.extract_long() + " ");
			sb.append(eventList.value[i].filterable_data[5].name + " ");	
			sb.append(eventList.value[i].filterable_data[5].value.extract_string() + " ");
			
			System.out.println(sb.toString());
		}
	}
	/**
	 * 将历史告警转换成String集合
	 * @param hisAlarmList
	 * @return 
	 */
	private List<String> convertHisAlarmfToString(List<HisAlarmInfo> hisAlarmList) {
		List<String> listResult = new ArrayList<String>();
		AlarmTools alarmTools=new AlarmTools();
		for (int i = 0; i < hisAlarmList.size(); i++) {
			HisAlarmInfo his = hisAlarmList.get(i);
			if(his != null){
				StringBuffer br = new StringBuffer();
				try {
					String s = br.append(i+1 + "  ")
					.append("告警级别="+this.getAlarmLevel(alarmTools.	getAlarmSeverity(his.getWarningLevel_temp()).toString()) + " ")
					.append("网元名称="+his.getSiteName() + " ")
					.append("告警源="+his.getObjectType()+"/"+his.getObjectName() + " ")
					.append("告警名称="+his.getWarningLevel().getWarningname() + " ")
					.append("告警描述="+his.getAlarmDesc() + " ")
					.append("告警类型="+this.getWarningType(his.getWarningLevel().getWarningtype()) + " ")
					.append("告警发生时间="+this.convertTime(his.getRaisedTime()) + " ")
					.append("告警确认时间="+this.convertTime(his.getAckTime()) + " ")
					.append("告警清除时间="+this.convertTime(his.getClearedTime()) + " ")
					.append("告警备注="+his.getCommonts() == null ? "":his.getCommonts())
					.toString();
					
					listResult.add(s);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		}

		return listResult;
	}
	
	
	/**
	 * 将英文告警级别转换成中文
	 */
	private String getAlarmLevel(String alarmLevel) {
		if("CRITICAL".equals(alarmLevel)){
			return ResourceUtil.srcStr(StringKeysLbl.LBL_UrgencyAlarm);
		}else if("MAJOR".equals(alarmLevel)){
			return ResourceUtil.srcStr(StringKeysLbl.LBL_MajorAlarm);
		}else if("MINOR".equals(alarmLevel)){
			return ResourceUtil.srcStr(StringKeysLbl.LBL_MinorAlarm);
		}else if("WARNING".equals(alarmLevel)){
			return ResourceUtil.srcStr(StringKeysLbl.LBL_PromptAlarm);
		}
		return "";
	}
	
	/**
	 * 获取告警类型
	 */
	private String getWarningType(int warningtype) {
		if(warningtype == 0){
			return ResourceUtil.srcStr(StringKeysObj.STRING_COMMUNICATION_ALARM);
		}
		return "";
	}
	
	/**
	 * 转换时间类型
	 * Mon Apr 28 10:44:50 CST 2014 转换成
	 * 2014-04-28 10:44:50
	 */
	private String convertTime(Date raisedTime) {
		if(raisedTime != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(raisedTime);// String类型转成date类型
		}
		return "";
	}
}
