package com.nms.corba.ninterface.impl.resource.proxy;

import globaldefs.Duration_T;
import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import notifications.AlarmType_T;
import notifications.EventIterator_IHolder;
import notifications.EventList_THolder;
import notifications.PerceivedSeverity_T;
import notifications.QueryAlarmFilter_T;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.omg.CosNotification.StructuredEvent;

import aSAP.ASAPCreateModifyData_T;
import aSAP.ASAPIterator_IHolder;
import aSAP.ASAPList_THolder;
import aSAP.ASAP_T;
import aSAP.ASAP_THolder;
import aSAP.AlarmSeverityAssignment_T;
import aSAP.AssignedSeverity_T;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.notification.CorbaNotifyReport;
import com.nms.corba.ninterface.impl.resource.EventIterator_Impl;
import com.nms.corba.ninterface.impl.resource.tool.CorbaAlarmTool;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.corba.ninterface.impl.util.StringUtil;
import com.nms.corba.ninterface.util.DateTimeUtil;
import com.nms.corba.ninterface.util.FileTools;
import com.nms.corba.ninterface.util.FtpUtil;
import com.nms.db.bean.alarm.CurrentAlarmInfo;
import com.nms.db.bean.alarm.HisAlarmInfo;
import com.nms.db.bean.alarm.WarningLevel;
import com.nms.model.alarm.CurAlarmService_MB;
import com.nms.model.alarm.HisAlarmService_MB;
import com.nms.model.alarm.WarningLevelService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class CorbaAlarmProxy {

	static Logger LOG = Logger.getLogger(CorbaAlarmProxy.class.getName());
	private ICorbaSession session = null;

	public CorbaAlarmProxy(ICorbaSession userSession) {
		this.session = userSession;
	}

	public void getAllASAPs(int howMany, ASAPList_THolder aSAPList,
								ASAPIterator_IHolder asapIt) throws ProcessingFailureException {
		WarningLevelService_MB warnService = null;
		try {
			warnService = (WarningLevelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WarningLevel);
			List<WarningLevel> warnList = warnService.select();
			//数据转换
			if(warnList.size() > 0){
				aSAPList.value = new ASAP_T[1];
				aSAPList.value[0]= new ASAP_T();
				convertAllWarning2Corba(warnList, aSAPList.value[0]);
			}
		} catch (Exception e) {
			LOG.error(e);
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, e.getMessage());
		} finally {
			UiUtil.closeService_MB(warnService);
		}
	}

	private void convertAllWarning2Corba(List<WarningLevel> warnList, ASAP_T aSAP_t) {
		aSAP_t.name = new NameAndStringValue_T[2];
		aSAP_t.name[0] = new NameAndStringValue_T("EMS", (CorbaConfig.getInstanse().getCorbaEmsName()));
		aSAP_t.name[1] = new NameAndStringValue_T("ASAP", "1");
		aSAP_t.nativeEMSName = "ASAP_1";
		aSAP_t.userLabel = "ASAP_1";
		aSAP_t.owner = "DATAX";
		aSAP_t.notModifiable = false;
		AlarmSeverityAssignment_T[] alarmLevelList = new AlarmSeverityAssignment_T[warnList.size()];
		for (int i = 0; i < warnList.size(); i++) {
			WarningLevel warn = warnList.get(i);
			alarmLevelList[i] = new AlarmSeverityAssignment_T();
			alarmLevelList[i].probableCause = warn.getWarningname();//告警原因
			alarmLevelList[i].probableCauseQualifier = String.valueOf(warn.getWarningcode());//告警码
			alarmLevelList[i].nativeProbableCause = warn.getWarningdescribe();//告警名称

			alarmLevelList[i].serviceAffecting = AssignedSeverity_T.AS_INDETERMINATE;
			//本地告警级别
			alarmLevelList[i].serviceNonAffecting = CorbaAlarmTool.convertAssignedSeverity2Corba(warn.getWarninglevel_temp());
			//协议告警级别
			alarmLevelList[i].serviceIndependentOrUnknown = CorbaAlarmTool.convertAssignedSeverity2Corba(warn.getWarninglevel());
		}
		aSAP_t.alarmSeverityAssignmentList = alarmLevelList;
		aSAP_t.additionalInfo = new NameAndStringValue_T[0];
	}
	
	public void modifyASAP(NameAndStringValue_T[] aSAPName, ASAPCreateModifyData_T aSAPModifyData,
							ASAP_THolder newASAP, NVSList_THolder additionalInfo) throws ProcessingFailureException {
		if(!CheckParameterUtil.checkASAPName(aSAPName)){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "aSAPName is invalid!");
		}
		if(aSAPModifyData.alarmSeverityAssignmentList == null){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "alarmSeverityAssignmentList is null!");
		}
		WarningLevelService_MB warnService = null;
		try {
			warnService = (WarningLevelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WarningLevel);
			aSAP.AlarmSeverityAssignment_T[] corbaAsapList= aSAPModifyData.alarmSeverityAssignmentList;
			int size = corbaAsapList.length;
			for (int i = 0; i < size; i++) {
				int level = CorbaAlarmTool.convertAssignedSeverityCorba2DataX(corbaAsapList[i].serviceNonAffecting);
				int code = Integer.parseInt(corbaAsapList[i].probableCauseQualifier);//
				String probableCause = corbaAsapList[i].probableCause;//告警原因
			
				WarningLevel warn = new WarningLevel();
				warn.setWarningname(probableCause);
				warn.setWarningcode(code);
				List<WarningLevel> warnList = warnService.select(warn);
				for(int j=0; j < warnList.size(); j++) {
					warn = warnList.get(j);
					warn.setWarninglevel_temp(level);
					warn.setWarningname(probableCause);
					warnService.saveOrUpdate(warn);
				}
			}
			
			List<WarningLevel> warningList = warnService.select();
			newASAP.value = new ASAP_T();
			convertAllWarning2Corba(warningList, newASAP.value);
			additionalInfo.value = new NameAndStringValue_T[0];
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "internal error");
		} finally {
			UiUtil.closeService_MB(warnService);
		}
	}

	public void getASAP(NameAndStringValue_T[] aSAPName, ASAP_THolder anASAP) throws ProcessingFailureException {
		if(!CheckParameterUtil.checkASAPName(aSAPName)){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "aSAPName is invalid!");
		}
		WarningLevelService_MB warnService = null;
		try {
			warnService = (WarningLevelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WarningLevel);
			List<WarningLevel> warnList = warnService.select();
			if (warnList.size() > 0) {
				anASAP.value  = new ASAP_T();
				convertAllWarning2Corba(warnList, anASAP.value);
			}
		} catch (Exception e) {
			LOG.error(e);
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "occur inner error!");
		} finally {
			UiUtil.closeService_MB(warnService);
		}
	}
	
	// 按条件查询当前告警
	public void getCurrentAlarmsByCond(String startTime, String endTime,
			NameAndStringValue_T[][] objectNameList,
			String[] inludeProbCauseList,
			PerceivedSeverity_T[] includeSeverityList,
			AlarmType_T[] includeAlarmTypeList, int how_many,
			EventList_THolder eventList, EventIterator_IHolder eventIt)
			throws ProcessingFailureException {
		if (DateTimeUtil.compare_date(startTime, endTime) != 1) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,
					"startTime should be less than endTime");
		}
		String formTime = DateTimeUtil.dateFormat(startTime);
		String toTime = DateTimeUtil.dateFormat(endTime);
		if(formTime == null || toTime == null){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,
					"startTime and endTime can't be empty");
		}
		CurAlarmService_MB service = null;
		try {
			// 告警源
			List<String> siteIdList = alarmObjName2Sites(objectNameList);

			// 告警等级解析
			List<Integer> alarmSeverityList = alarmSeverity2DataX(includeSeverityList);

			// 告警类型
			List<Integer> alarmTypeList = alarmType2DataX(includeAlarmTypeList);
			// 在数据库中查询所有的当前告警
			service = (CurAlarmService_MB) ConstantUtil.serviceFactory
					.newService_MB(Services.CurrentAlarm);
			// 告警产生时间段/告警源/告警等级/告警类型 过滤
			List<CurrentAlarmInfo> currInfos = service.selectCurrentAlarmByCond(formTime, toTime,
					siteIdList, alarmSeverityList, alarmTypeList,inludeProbCauseList);
			if (currInfos.isEmpty()) {
				eventList.value = new StructuredEvent[0];
				return;
			}
			eventList.value = new StructuredEvent[currInfos.size()];
			CorbaAlarmTool.convertAlarmListDATAXToCorba(currInfos,
					eventList.value);
			// 设置过滤器
			EventIterator_Impl iter = new EventIterator_Impl(this.session);
			iter.setIterator(eventIt, eventList, how_many);

		} catch (Exception e) {
			LOG.error(e.getMessage());
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "occur inner error!");
		} finally {
			UiUtil.closeService_MB(service);
		}
	}

	// 告警源解析
	public List<String> alarmObjName2Sites(
			NameAndStringValue_T[][] objectNameList) {

		List<String> siteIdList = new ArrayList<String>();
		int objSize = objectNameList.length;
		if (objSize > 0) {
			for (int i = 0; i < objSize; i++) {
				// 网元
				if (objectNameList[i].length >= 2) {
					String siteId = objectNameList[i][1].value;
					siteIdList.add(siteId);
				}
			}
		}
		return siteIdList;
	}

	// 告警等级解析
	public List<Integer> alarmSeverity2DataX(
			PerceivedSeverity_T[] includeSeverityList) {
		List<Integer> alarmSeverityList = new ArrayList<Integer>();
		int num = includeSeverityList.length;
		if (num > 0) {
			for (int j = 0; j < num; j++) {
				Integer alarmSeverity = CorbaAlarmTool
						.ConvertSeverityCorba2DataX(includeSeverityList[j]);
				alarmSeverityList.add(alarmSeverity);
			}
		}
		return alarmSeverityList;
	}

	// 告警类型
	public List<Integer> alarmType2DataX(AlarmType_T[] includeAlarmTypeList) {
		List<Integer> alarmTypeList = new ArrayList<Integer>();
		int count = includeAlarmTypeList.length;
		if (count > 0) {
			for (int k = 0; k < count; k++) {
				Integer alarmType = CorbaAlarmTool
						.ConvertAlarmTypeCorba2DataX(includeAlarmTypeList[k]);
				alarmTypeList.add(alarmType);
			}
		}
		return alarmTypeList;
	}

	// 获取时间字符串
	private String getTimeStr(Date raisedTime) {
		if (raisedTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			return sdf.format(raisedTime);
		}
		return "";
	}

	// 历史告警文件传输
	public void hisAlarmFileTransfer(String destination, String userName,
			String passWord, QueryAlarmFilter_T queryCondition)
			throws ProcessingFailureException {
		FtpUtil ftp = new FtpUtil();
		FtpUtil.FtpInfo ftpInfo = ftp.new FtpInfo(userName, passWord);
		if(!StringUtil.FtpDestinationParse(destination, ftpInfo)){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,
					"destination is invalid input!");
		}
		String fileName= ftpInfo.localFileName;
		RandomAccessFile out = null;
		HisAlarmService_MB service = null;
		try {
			FileTools.writeFileHead(fileName,
					FileTools.getFileHeadStr(FTPHisAlarmInfo.class));
			out = new RandomAccessFile(fileName, "rw");
			out.seek(out.length());
			// 告警源
			List<String> siteIdList = alarmObjName2Sites(queryCondition.alarmSourceSelect);
			// 告警等级解析
			List<Integer> alarmSeverityList = alarmSeverity2DataX(queryCondition.perceivedSeveritySelect);
			// 告警类型
			List<Integer> alarmTypeList = alarmType2DataX(queryCondition.alarmTypeSelect);
			String raiseTimeDuration = getTimeDuration(queryCondition.raiseTimeDuration);
			String clearTimeDuration = getTimeDuration(queryCondition.clearTimeDuration);
			// 告警原因
			String[] probableCauseArray = queryCondition.probableCauseSelect;
			// 告警产生时间段/告警源/告警等级/告警类型/告警原因 过滤
			service = (HisAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.HisAlarm);
			List<HisAlarmInfo> hisAlarmList = service.selectHisAlarmByCond(
					raiseTimeDuration, clearTimeDuration, siteIdList,
					alarmSeverityList, alarmTypeList, probableCauseArray);
			if (!hisAlarmList.isEmpty()) {
				FTPHisAlarmInfo hisAlarm = null;
				int iLength = hisAlarmList.size();
				for (int i = 0; i < iLength; i++) {
					hisAlarm = convertHisAlarm2Ftp(hisAlarmList.get(i));
					String lineStr = FileTools.getLineStrFromFtpInfo(hisAlarm);
					if (lineStr != null) {
						out.write(lineStr.getBytes("GBK"));
					}
				}
			}
			// 将本地文件通过ftp上传到指定路径		
			boolean status = ftp.push2FtpServer(ftpInfo);
			CorbaNotifyReport.fileTransferNotify(ftpInfo.filePathAndName, status);

		} catch (FileNotFoundException e) {
			ExceptionManage.dispose(e,this.getClass());
		} catch (IOException e) {
			ExceptionManage.dispose(e,this.getClass());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT,
					e.getMessage());
		} finally {
			UiUtil.closeService_MB(service);
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					LOG.error(e.getMessage());
				}
			}
		}
	}

	// 时间段
	public String getTimeDuration(Duration_T timeDuration) throws ProcessingFailureException {
		String fromTime = DateTimeUtil.dateFormat(timeDuration.fromTime);
		String toTime = DateTimeUtil.dateFormat(timeDuration.toTime);
		if(fromTime == null || fromTime == null){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,
					"startTime and endTime can't be empty");
		}
	
		DateTimeUtil.dateFormat(timeDuration.fromTime);
		DateTimeUtil.dateFormat(timeDuration.toTime);
		return fromTime+ "|" + toTime;
	}

	// 转换历史告警FTP对象
	private FTPHisAlarmInfo convertHisAlarm2Ftp(HisAlarmInfo hisAlarm) {
		StringBuffer alarmId = new StringBuffer(hisAlarm.getObjectName());
		//ObjectName+getObjectType+AlarmCode+AlarmLevel+RaisedTime;
		alarmId.append(hisAlarm.getObjectType()).append(hisAlarm.getAlarmCode())
		.append(hisAlarm.getAlarmLevel()).append(hisAlarm.getWarningLevel().getWarningname())
		.append(hisAlarm.getRaisedTime());
		String corbaAlarmId = DigestUtils.md2Hex(alarmId.toString());
		System.out.println(corbaAlarmId);
		
		FTPHisAlarmInfo ftpHisAlarm = new FTPHisAlarmInfo();
		ftpHisAlarm.EMS_Name = CorbaConfig.getInstanse().getCorbaEmsName();
		ftpHisAlarm.ME_Name = String.valueOf(hisAlarm.getSiteId());
		
		StringBuffer objValue = new StringBuffer("/rack=1/shelf=1/slot=");
		int slotNum = hisAlarm.getSlotNumber();
		if(hisAlarm.getObjectType().toString().contains("SLOT")){
			ftpHisAlarm.SLOT_Name = objValue.append(slotNum).toString();
			ftpHisAlarm.PTP_Name = "";
		}else{
			ftpHisAlarm.SLOT_Name = "";
			objValue.append(slotNum).append("/port=").append(hisAlarm.getObjectName());
			ftpHisAlarm.PTP_Name = objValue.toString();
		}

		ftpHisAlarm.ObjectType = hisAlarm.getObjectType().toString();
		ftpHisAlarm.AlarmId = corbaAlarmId;
		ftpHisAlarm.ProbableCause = hisAlarm.getWarningLevel().getWarningname();
		ftpHisAlarm.AlarmType =	CorbaAlarmTool.convertAlarmType2Corba(hisAlarm.getWarningLevel().getWarningtype()).toString();
		ftpHisAlarm.PerceivedSeverity = CorbaAlarmTool.convertPerceivedSeverity2Corba(hisAlarm
				.getWarningLevel().getWarninglevel()).toString();
		ftpHisAlarm.RaiseTime = getTimeStr(hisAlarm.getRaisedTime());
		ftpHisAlarm.ClearTime = getTimeStr(hisAlarm.getClearedTime());
		ftpHisAlarm.AckTime = getTimeStr(hisAlarm.getAckTime());
		String ackUser = hisAlarm.getAckUser();
		ftpHisAlarm.AckUser = ackUser ==null? "": ackUser;
		ftpHisAlarm.EmsAlarmId = String.valueOf(hisAlarm.getId());
		ftpHisAlarm.AlarmDesc =  hisAlarm.getWarningLevel().getWarningnote();
		ftpHisAlarm.AckState = hisAlarm.getAckUser() == null ? "UnAcked"
				: "Acked";
		ftpHisAlarm.ClearState = "Cleared";
		ftpHisAlarm.IsAffectService = "false";
		ftpHisAlarm.LayerRate = "1";
		ftpHisAlarm.NE_Time = getTimeStr(hisAlarm.getClearedTime());
		ftpHisAlarm.EMS_Time = getTimeStr(hisAlarm.getClearedTime());
		ftpHisAlarm.DESC = hisAlarm.getWarningLevel().getWarningdescribe();
		ftpHisAlarm.AlarmSourceId = String.valueOf(hisAlarm.getObjectId());
		return ftpHisAlarm;
	}

	// 定义中间FTP历史告警数据，便于写文件
	public class FTPHisAlarmInfo {
		public String EMS_Name;
		public String ME_Name;
		public String SLOT_Name;
		public String PTP_Name;
		public String ObjectType;
		public String LayerRate;
		public String AlarmId;
		public String ProbableCause;
		public String PerceivedSeverity;
		public String AlarmType;
		public String AlarmDesc;
		public String RaiseTime;
		public String ClearTime;
		public String AckUser;
		public String AckTime;
		public String AckState;
		public String ClearState;
		public String NE_Time;
		public String EMS_Time;
		public String EmsAlarmId;
		public String DESC;
		public String IsAffectService;
		public String AlarmSourceId;
		public FTPHisAlarmInfo() {

		}
	}
}
