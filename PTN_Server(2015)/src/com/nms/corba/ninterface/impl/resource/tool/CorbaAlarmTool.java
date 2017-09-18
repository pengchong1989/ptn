package com.nms.corba.ninterface.impl.resource.tool;

import globaldefs.NVSList_THelper;
import globaldefs.NameAndStringValue_T;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import notifications.AlarmType_T;
import notifications.AlarmType_THelper;
import notifications.ObjectType_T;
import notifications.ObjectType_THelper;
import notifications.PerceivedSeverity_T;
import notifications.PerceivedSeverity_THelper;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.omg.CosNotification.EventHeader;
import org.omg.CosNotification.EventType;
import org.omg.CosNotification.FixedEventHeader;
import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;

import aSAP.AssignedSeverity_T;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.CorbaServer;
import com.nms.db.bean.alarm.CurrentAlarmInfo;

public class CorbaAlarmTool {
	static Logger LOG = Logger.getLogger(CorbaAlarmTool.class.getName());

	public static void convertAlarmListDATAXToCorba(List<CurrentAlarmInfo> alarmList,
			StructuredEvent[] idlEventArray) {
		if(alarmList == null || alarmList.isEmpty()){
			return;
		}
		int size = alarmList.size();
		for (int i = 0; i < size; i++) {
			try{
				idlEventArray[i] = converAlarmDATAXtoCorba(alarmList.get(i));
			}catch(Exception ex){
				LOG.error(ex, ex.getCause());
			}
		}
	}

	public static StructuredEvent converAlarmDATAXtoCorba(
			CurrentAlarmInfo alarm) {
		StructuredEvent idlEvent = new StructuredEvent();
		idlEvent.header = new EventHeader();
		idlEvent.header.fixed_header = new FixedEventHeader();
		idlEvent.header.fixed_header.event_name = new String("");
		idlEvent.header.fixed_header.event_type = new EventType();
		idlEvent.header.fixed_header.event_type.domain_name = new String("tmf_mtnm");
		idlEvent.header.fixed_header.event_type.type_name = new String("NT_ALARM");
		idlEvent.header.variable_header = new Property[0];
		convertAlarm2Event(alarm, idlEvent);
		return idlEvent;
	}

	public static void convertAlarm2Event(CurrentAlarmInfo alarm,
			StructuredEvent idlEvent) {
		idlEvent.filterable_data = new Property[18];
		idlEvent.filterable_data[0] = new Property();
		idlEvent.filterable_data[0].name = "alarmId";
		idlEvent.filterable_data[0].value = CorbaServer.getInstanse().createAny();
		//alarmID
		StringBuffer alarmId = new StringBuffer(alarm.getObjectName());
		//ObjectName+getObjectType+AlarmCode+AlarmLevel+RaisedTime;
		alarmId.append(alarm.getObjectType()).append(alarm.getAlarmCode())
		.append(alarm.getAlarmLevel()).append(alarm.getWarningLevel().getWarningname())
		.append(alarm.getRaisedTime());
		String corbaAlarmId = DigestUtils.md2Hex(alarmId.toString());
		
		idlEvent.filterable_data[0].value.insert_string(String.valueOf(corbaAlarmId));		
		idlEvent.filterable_data[1] = new Property();
		idlEvent.filterable_data[1].name = "objectType";
		idlEvent.filterable_data[1].value = CorbaServer.getInstanse().createAny();
		ObjectType_THelper.insert(idlEvent.filterable_data[1].value,ObjectType_T.OT_AID);

		idlEvent.filterable_data[2] = new Property();
		idlEvent.filterable_data[2].name = "emsTime";
		idlEvent.filterable_data[2].value = CorbaServer.getInstanse().createAny();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTime = sdf.format(date);
		idlEvent.filterable_data[2].value.insert_string(currentTime);

		idlEvent.filterable_data[3] = new Property();
		idlEvent.filterable_data[3].name = "objectTypeQualifier";
		idlEvent.filterable_data[3].value = CorbaServer.getInstanse().createAny();
		idlEvent.filterable_data[3].value.insert_string("OT_AID");

		// 告警类型
		idlEvent.filterable_data[4] = new Property();
		idlEvent.filterable_data[4].name = "alarmType";
		idlEvent.filterable_data[4].value = CorbaServer.getInstanse().createAny();
		notifications.AlarmType_T alarmType = convertAlarmType2Corba(alarm.getWarningLevel().getWarningtype());
		AlarmType_THelper.insert(idlEvent.filterable_data[4].value, alarmType);
		// 告警等级
		idlEvent.filterable_data[5] = new Property();
		idlEvent.filterable_data[5].name = "perceivedSeverity";
		idlEvent.filterable_data[5].value = CorbaServer.getInstanse().createAny();
		notifications.PerceivedSeverity_T perceivedSeverity = convertPerceivedSeverity2Corba(alarm.getAlarmLevel());
		PerceivedSeverity_THelper.insert(idlEvent.filterable_data[5].value,perceivedSeverity);
		// 告警状态
		idlEvent.filterable_data[6] = new Property();
		idlEvent.filterable_data[6].name = "alarmStatus";
		idlEvent.filterable_data[6].value = CorbaServer.getInstanse().createAny();
		String alarmStatus = getAlarmStatus(alarm.getAckUser(),alarm.getClearedTime());
		idlEvent.filterable_data[6].value.insert_string(alarmStatus);
		
		//告警源
		idlEvent.filterable_data[7] = new Property();
		idlEvent.filterable_data[7].name = "objectName";
		idlEvent.filterable_data[7].value = CorbaServer.getInstanse().createAny();
		// alarm_source
		NameAndStringValue_T[] objName = convertAlarmObjName2Corba(alarm.getSiteId(), alarm.getSlotNumber(), alarm.getObjectType().toString(), alarm.getObjectName());
		NVSList_THelper.insert(idlEvent.filterable_data[7].value, objName);

		// 告警源对象类型
		idlEvent.filterable_data[8] = new Property();
		idlEvent.filterable_data[8].name = "alarmSourceType";
		idlEvent.filterable_data[8].value = CorbaServer.getInstanse().createAny();
		String alarmSourceType = alarm.getObjectType().toString();
		idlEvent.filterable_data[8].value.insert_string(alarmSourceType);

		// 告警 层速率
		idlEvent.filterable_data[9] = new Property();
		idlEvent.filterable_data[9].name = "layerRate";
		idlEvent.filterable_data[9].value = CorbaServer.getInstanse().createAny();
		String layerRate = getLayerRate(alarm.getObjectName());
		idlEvent.filterable_data[9].value.insert_string(layerRate);

		// 告警原因
		idlEvent.filterable_data[10] = new Property();
		idlEvent.filterable_data[10].name = "probableCause";
		idlEvent.filterable_data[10].value = CorbaServer.getInstanse().createAny();
		idlEvent.filterable_data[10].value.insert_string(alarm.getWarningLevel().getWarningname());

		// 网元时间
		idlEvent.filterable_data[11] = new Property();
		idlEvent.filterable_data[11].name = "NETime";
		idlEvent.filterable_data[11].value = CorbaServer.getInstanse().createAny();
		idlEvent.filterable_data[11].value.insert_string(currentTime);

		// raiseTime
		idlEvent.filterable_data[12] = new Property();
		idlEvent.filterable_data[12].name = "raiseTime";
		idlEvent.filterable_data[12].value = CorbaServer.getInstanse().createAny();
		String raiseTime = sdf.format(alarm.getRaisedTime());
		idlEvent.filterable_data[12].value.insert_string(raiseTime);

		// clearTime
		idlEvent.filterable_data[13] = new Property();
		idlEvent.filterable_data[13].name = "clearTime";
		idlEvent.filterable_data[13].value = CorbaServer.getInstanse().createAny();
		String clearTime = "";
		if(alarm.getClearedTime()!=null){
			clearTime = sdf.format(alarm.getClearedTime());
		}
		idlEvent.filterable_data[13].value.insert_string(clearTime);

		// 是否影响业务，暂时填写SA_UNKNOWN
		idlEvent.filterable_data[14] = new Property();
		idlEvent.filterable_data[14].name = "serviceAffecting";
		idlEvent.filterable_data[14].value = CorbaServer.getInstanse().createAny();
		idlEvent.filterable_data[14].value.insert_string("SA_UNKNOWN");

		// 相关告警
		idlEvent.filterable_data[15] = new Property();
		idlEvent.filterable_data[15].name = "CorrelatedAlarmIds";
		idlEvent.filterable_data[15].value = CorbaServer.getInstanse().createAny();
		String emsALarm = String.valueOf(alarm.getId());
		idlEvent.filterable_data[15].value.insert_string(emsALarm);

		// 可能告警原因
		idlEvent.filterable_data[16] = new Property();
		idlEvent.filterable_data[16].name = "Description";
		idlEvent.filterable_data[16].value = CorbaServer.getInstanse().createAny();
		String alarmDesc =alarm.getWarningLevel().getWarningdescribe();
		if(alarmDesc == null){
			alarmDesc="";
		}
		idlEvent.filterable_data[16].value.insert_string(alarmDesc);
		
		idlEvent.filterable_data[17] = new Property();
		idlEvent.filterable_data[17].name = "additionalInfo";
		idlEvent.filterable_data[17].value = CorbaServer.getInstanse().createAny();

		NameAndStringValue_T[] additionalInfo = new NameAndStringValue_T[2];
		additionalInfo[0] = new NameAndStringValue_T();
		additionalInfo[0].name = "ackUser";
		String ackUser = "";
		if(alarm.getAckUser() != null){
			ackUser = alarm.getAckUser();
		}
		additionalInfo[0].value = ackUser;
		additionalInfo[1] = new NameAndStringValue_T();
		additionalInfo[1].name = "ackTime";
		String ackTime = "";
		if(alarm.getAckTime() != null){
			ackTime = sdf.format(alarm.getAckTime());
		}
		additionalInfo[1].value = ackTime;

		NVSList_THelper.insert(idlEvent.filterable_data[17].value,additionalInfo);

		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
	}
	
	private static String getLayerRate(String objectName) {
		// TODO Auto-generated method stub
		return "1";
	}

	//告警源
	private static NameAndStringValue_T[] convertAlarmObjName2Corba(int siteId,
			int slotNum, String objectType, String objectId) {
		NameAndStringValue_T[] alarmSource = null;
		NameAndStringValue_T  ems = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		StringBuffer objValue = new StringBuffer("/rack=1/shelf=1/slot=");
		if(objectType.equals("SITEINST")){
			alarmSource = new NameAndStringValue_T[2];
			alarmSource[0] = ems;
			alarmSource[1] = new NameAndStringValue_T("ManagedElement", String.valueOf(siteId));
		}else if(objectType.equals("SLOTINST") || objectType.equals("SLOT")){
			alarmSource = new NameAndStringValue_T[3];
			alarmSource[0] = ems;
			alarmSource[1] = new NameAndStringValue_T("ManagedElement", String.valueOf(siteId));
			objValue.append(slotNum);
			alarmSource[2] = new NameAndStringValue_T("EquipmentHolder", objValue.toString());
		}else if(objectType.equals("PORT")|| objectType.equals("LAG")){
			alarmSource = new NameAndStringValue_T[3];
			alarmSource[0] = ems;
			alarmSource[1] = new NameAndStringValue_T("ManagedElement", String.valueOf(siteId));
			objValue.append(slotNum).append("/port=").append(objectId);
			alarmSource[2] = new NameAndStringValue_T("PTP", objValue.toString());
		}else{
			alarmSource = new NameAndStringValue_T[1];
			alarmSource[0] = ems;
		}
		return alarmSource;
	}

	private static String getAlarmStatus(String ackUser, Date clearedTime) {

		if (ackUser != null && clearedTime == null) {
			return "AckedUncleared";
		}
		if (ackUser != null && clearedTime != null) {
			return "AckedCleared";
		}
		if (ackUser == null && clearedTime != null) {
			return "UnackedCleared";
		}
		if (ackUser == null && clearedTime == null) {
			return "UnackedUncleared";
		}
		return "UnackedUncleared";
	}

	public static Integer ConvertSeverityCorba2DataX(
			PerceivedSeverity_T perceivedSeverity) {
		switch (perceivedSeverity.value()) {
		case PerceivedSeverity_T._PS_INDETERMINATE:
			return 1;
		case PerceivedSeverity_T._PS_CRITICAL:
			return 5;
		case PerceivedSeverity_T._PS_MAJOR:
			return 4;
		case PerceivedSeverity_T._PS_MINOR:
			return 3;
		case PerceivedSeverity_T._PS_WARNING:
			return 2;
		case PerceivedSeverity_T._PS_CLEARED:
			return 0;
		default:
			break;
		}
		return 5;
	}

	public static PerceivedSeverity_T convertPerceivedSeverity2Corba(
			int alarmSeverity) {
		switch (alarmSeverity) {
		case 1:
			return PerceivedSeverity_T.PS_INDETERMINATE;
		case 5:
			return PerceivedSeverity_T.PS_CRITICAL;
		case 4:
			return PerceivedSeverity_T.PS_MAJOR;
		case 3:
			return PerceivedSeverity_T.PS_MINOR;
		case 2:
			return PerceivedSeverity_T.PS_WARNING;
		case 0:
			return PerceivedSeverity_T.PS_CLEARED;
		default:
			break;
		}
		return PerceivedSeverity_T.PS_INDETERMINATE;
	}

	public static AlarmType_T convertAlarmType2Corba(int alarmType) {
		switch (alarmType) {
		case 1:
			return AlarmType_T.AT_COMMUNICATIONSALARM;
		case 2:
			return AlarmType_T.AT_QOSALARM;
		case 3:
			return AlarmType_T.AT_EQUIPMENTALARM;

		case 4:
			return AlarmType_T.AT_PROCESSINGERRORALARM;

		case 5:
			return AlarmType_T.AT_ENVIRONMENTALALARM;

		default:
			break;
		}
		return AlarmType_T.AT_COMMUNICATIONSALARM;
	}

	public static Integer ConvertAlarmTypeCorba2DataX(AlarmType_T alarmType) {
		switch (alarmType.value()) {
		case AlarmType_T._AT_COMMUNICATIONSALARM:
			return 1;
		case AlarmType_T._AT_QOSALARM:
			return 2;
		case AlarmType_T._AT_EQUIPMENTALARM:
			return 3;
		case AlarmType_T._AT_PROCESSINGERRORALARM:
			return 4;
		case AlarmType_T._AT_ENVIRONMENTALALARM:
			return 5;
		case AlarmType_T._AT_NETWORKSECURITYALARM:
			return 1;
		case AlarmType_T._AT_CONNECTIONALARM:
			return 1;
		default:
			break;
		}
		return 1;
	}

	public static AssignedSeverity_T convertAssignedSeverity2Corba(
			int alarmSeverity) {
		switch (alarmSeverity) {
		case 1:
			return AssignedSeverity_T.AS_INDETERMINATE;
		case 5:
			return AssignedSeverity_T.AS_CRITICAL;
		case 4:
			return AssignedSeverity_T.AS_MAJOR;
		case 3:
			return AssignedSeverity_T.AS_MINOR;
		case 2:
			return AssignedSeverity_T.AS_WARNING;
		case 0:
			return AssignedSeverity_T.AS_NONALARMED;
		default:
			break;
		}
		return AssignedSeverity_T.AS_INDETERMINATE;
	}

	public static int convertAssignedSeverityCorba2DataX(
			AssignedSeverity_T  assignedSeverity) {
		switch (assignedSeverity.value()) {
		case AssignedSeverity_T._AS_INDETERMINATE:
			return 1;
		case AssignedSeverity_T._AS_CRITICAL:
			return 5;
		case AssignedSeverity_T._AS_MAJOR:
			return 4;
		case AssignedSeverity_T._AS_MINOR:
			return 3;
		case AssignedSeverity_T._AS_WARNING:
			return 2;
		case AssignedSeverity_T._AS_NONALARMED:
			return 0;
		default:
			break;
		}
		return 5;
	}

}
