package com.nms.snmp.ninteface.impl.trap;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;

import com.nms.db.bean.alarm.CurrentAlarmInfo;
import com.nms.service.notify.Message;
import com.nms.snmp.ninteface.framework.trap.INotifyHandler;
import com.nms.snmp.ninteface.framework.trap.SnmpNotifyMgr;
//import com.nms.snmp.ninteface.mib.DataxAlarmModel;

public class AlarmTrap implements INotifyHandler  {

	@Override
	public void handleAndTrapMsg(Message msg) {
//		//extends CorbaNotifyCvtHandler implements INotifyProcess
//		CurrentAlarmInfo alarm = (CurrentAlarmInfo) msg.getMsgBody();
//		DataxAlarmModel alarmMib = SnmpNotifyMgr.getInstance().getAgent().getAlarmMib();
//		VariableBinding[] vbs = new VariableBinding[18];
//		//转换数据oid                        oidAlarmindex
//		OID alarmIndexOid = new OID(alarmMib.oidAlarmindex); 
//		alarmIndexOid.append(alarmMib.colAlarmIndex);
//		Integer32 alarmIndex = new Integer32(2);
//		vbs[0] = new VariableBinding(alarmIndexOid, alarmIndex);
//		//告警匹配ID
//		OID alarmIdOid = new OID(alarmMib.oidTrapVarAlarmId);
//		alarmIdOid.append(alarmMib.colAlarmID);
//		String alarmName = alarm.getId()+"";
//		vbs[1] = new VariableBinding(alarmIdOid, new OctetString(alarmName));
//		//告警源标识
//		OID alarmObjectOid = new OID(alarmMib.oidTrapVarAlarmObjectName);
//		alarmObjectOid.append(alarmMib.colObjectName);
//		String alarmSourceType = alarm.getObjectName();
//		vbs[2] = new VariableBinding(alarmObjectOid,  new OctetString(alarmSourceType));
//		//厂商告警标题
//		OID alarmNativeProbableCause = new OID(alarmMib.oidTrapNativeProbableCause);
//		alarmNativeProbableCause.append(alarmMib.colNativeProbableCause);
//		 String nativeProbableCause = "";
//		 String probableCauseQualifier = "";
//		if(alarm.getWarningLevel()!= null){
//			if(alarm.getWarningLevel().getWarningname()!= null ){
//				nativeProbableCause = alarm.getWarningLevel().getWarningname();
//				probableCauseQualifier =alarm.getWarningLevel().getId() + "";
//			}
//		}
//		vbs[3] = new VariableBinding(alarmNativeProbableCause,  new OctetString(nativeProbableCause));
//		//告警唯一定位的辅助描述符
//		OID alarmProbableCauseQualifier = new OID(alarmMib.oidTrapProbableCauseQualifier);
//		alarmProbableCauseQualifier.append(alarmMib.colProbableCauseQualifier);
//		vbs[4] = new VariableBinding(alarmProbableCauseQualifier,  new OctetString(probableCauseQualifier));
//		
//		//告警产生对象类型
//		OID alarmObjectType = new OID(alarmMib.oidTrapVarAlarmObjectType);
//		alarmObjectType.append(alarmMib.colObjectType);
//		vbs[5] = new VariableBinding(alarmObjectType,  new OctetString(alarmObjectType(alarm.getObjectType().getValue())));
//		
//		//对象类型限定符
//		OID alarmObjectTypeQualifier = new OID(alarmMib.oidTrapVarAlarmObjectTypeQualifier);
//		alarmObjectTypeQualifier.append(alarmMib.colObjectTypeQualifier);
//		vbs[6] = new VariableBinding(alarmObjectTypeQualifier,  new OctetString(alarmObjectType(alarm.getObjectType().getValue())));
//		
//		//EmsTime 网管时间
//		OID alarmEsmTime = new OID(alarmMib.oidTrapVarAlarmEmsTime);
//		alarmEsmTime.append(alarmMib.colEmsTime);
//		String esmTime = "";
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//		if(alarm.getClearedTime() != null){
//			esmTime =dateFormat.format(alarm.getClearedTime());
//		}else{
//			if(alarm.getAlarmTime() != null){
//				esmTime = alarm.getAlarmTime();
//			}else{
//				esmTime = dateFormat.format(new Date());
//			}
//		}
//		vbs[7] = new VariableBinding(alarmEsmTime, new OctetString(esmTime));
//		
//		//NeTime 网元时间
//		OID alarmNeTime = new OID(alarmMib.oidTrapVarAlarmNeTime);
//		alarmNeTime.append(alarmMib.colNeTime);
//		String neTime = "";
//		if(alarm.getClearedTime() != null){
//			neTime =dateFormat.format(alarm.getClearedTime());
//		}else{
//			if(alarm.getAlarmTime() != null){
//				neTime = alarm.getAlarmTime();
//			}else{
//				neTime = dateFormat.format(new Date());
//			}
//		}
//		vbs[8] = new VariableBinding(alarmNeTime, new OctetString(neTime));
//		
//		//速率层次
//		OID layerRate = new OID(alarmMib.oidTrapVarAlarmlayerRate);
//		layerRate.append(alarmMib.colLayerRate);
//		vbs[9] = new VariableBinding(layerRate, new OctetString(alarmLayer(alarm.getObjectType().getValue())));
//		
//		//告警可能原因
//		OID alarmProbableCauseOid = new OID(alarmMib.oidTrapVarAlarmlaProbableCauseOid);
//		alarmProbableCauseOid.append(alarmMib.colProbableCause);
//		String alarmDesc = alarm.getWarningLevel().getWarningmayreason();
//		if(alarmDesc == null ||alarmDesc.equals("null") ){
//			alarmDesc = "";
//		}
//		vbs[10] = new VariableBinding(alarmProbableCauseOid, new OctetString(alarmDesc));
//		
//		//告警是否影响业务
//		OID alarmServiceAffectingOid = new OID(alarmMib.oidTrapVarAlarmlaServiceAffectingOid);
//		alarmServiceAffectingOid.append(alarmMib.colServiceAffecting);
//		vbs[11] = new VariableBinding(alarmServiceAffectingOid, new OctetString(isServiceAffection(alarm.getObjectType().getValue())));
//		
//		//告警级别
//		OID alarmPerceivedSeverityOid = new OID(alarmMib.oidTrapVarAlarmlPerceivedSeverityOid);
//		alarmPerceivedSeverityOid.append(alarmMib.colPerceivedSeverity);
//        //网管数据与设备的告警等级是反的 网管上面5:代表	"紧急告警";4:"主要告警" 3:"次要告警"；2:提示告警
//		
//	   String type = "" ;
//	   switch (alarm.getWarningLevel_temp()) {
//		case 0:
//			type = "PS_CLEARED";
//			break;
//		case 1:
//			type = "PS_INDETERMINATE";
//			break;
//		case 2:
//			type = "PS_WARNING";
//			break;
//		case 3:
//			type = "PS_MINOR";
//			break;
//		case 4:
//			type = "PS_MAJOR";
//			break;
//		case 5:
//			type = "PS_CRITICAL";
//			break;
//		default:
//			type = null;
//			break;
//		}
//		vbs[12] = new VariableBinding(alarmPerceivedSeverityOid, new OctetString(type));
//		
//		//告警详细原因（可选字段）
//		OID alarmSpecificProblemsOid = new OID(alarmMib.oidTrapVarAlarmlSpecificProblemsOid);
//		alarmSpecificProblemsOid.append(alarmMib.colSpecificProblems);
//		 alarmDesc = alarm.getWarningLevel().getWarningdescribe();
//		if(alarmDesc == null ||alarmDesc.equals("null") ){
//			alarmDesc = "";
//		}
//		vbs[13] = new VariableBinding(alarmSpecificProblemsOid, new OctetString(""));
//		
//		//故障处理的建议列表（可选字段）
//		OID alarmProposedRepairActionsOid = new OID(alarmMib.oidTrapVarAlarmlProposedRepairActionsOid);
//		alarmProposedRepairActionsOid.append(alarmMib.colProposedRepairActions);
//	     String	alarmAdvice = alarm.getWarningLevel().getWarningadvice();
//		if(alarmAdvice == null ||alarmAdvice.equals("null") ){
//			alarmAdvice = "";
//		}
//		vbs[14] = new VariableBinding(alarmProposedRepairActionsOid, new OctetString(""));
//		
//		//告警类型
//		OID alarmEventTypeOid = new OID(alarmMib.oidTrapVarAlarmlEventTypeOid);
//		alarmEventTypeOid.append(alarmMib.colEventType); //getWarningLevel().getWarningtype()
//		if(alarm.getWarningLevel() != null){
//			vbs[15] = new VariableBinding(alarmEventTypeOid, new OctetString(getAlarmType(alarm.getWarningLevel().getWarningtype())));
//		}else{
//			vbs[15] = new VariableBinding(alarmEventTypeOid, new OctetString(getAlarmType(0)));
//		}
//		
//		//告警是否确认
//		OID alarmAcknowledgeIndicationOid = new OID(alarmMib.oidTrapVarAlarmAcknowledgeIndicationOid);
//		alarmAcknowledgeIndicationOid.append(alarmMib.colAcknowledgeIndication);
//		vbs[16] = new VariableBinding(alarmAcknowledgeIndicationOid, new OctetString(getAlarmStatus(alarm.getAckTime())));
//		
//		//告警确定时间
//		OID alarmAcknowledgeTimeOid = new OID(alarmMib.oidTrapVarAlarmAcknowledgeTimeOid);
//		alarmAcknowledgeTimeOid.append(alarmMib.colAcknowledgeTime);
//		
//		vbs[17] = new VariableBinding(alarmAcknowledgeTimeOid, new OctetString(""));
//		
//		alarmMib.dataxAlarmEvent(SnmpNotifyMgr.getInstance().getAgent().getNotificationOriginator(), new OctetString(), vbs);
}
	
	//获取告警状态
	/**
	 * 
	 * AI_EVENT_ACKNOWLEDGED(艾事件已确认), AI_EVENT_UNACKNOWLEDGED(艾未确认事件), AI_NA
	 */
	private  String getAlarmStatus(Date ackTime) {
		if(ackTime != null){
			return "AI_EVENT_ACKNOWLEDGED";
		}else{
			return "AI_EVENT_UNACKNOWLEDGED";
		}
	}
	
	//获取层速率
	public String alarmLayer(int value){
		
		if(value == 3 ||value == 6 ) 
			return "LR_MPLS_TP_LSP";
		else if(value == 4) 
			return "LR_MPLS_TP_PW";
		else if(value == 5) 
			return "LR_MPLS_TP_Section";
		else
			return "LR_Not_Applicable";
		
	}
	/**
	 * 告警产生对象类型
	 * @param value
	 * @return
	 */
	public String alarmObjectType(int value){
		if(value == 1)
			return "OT_MANAGED_ELEMENT";
		else if(value == 2) 
			return "OT_MANAGED_ELEMENT";
		else if(value == 3) 
			return "OT_SUBNETWORK_CONNECTION";
		else if(value == 4) 
			return "OT_SUBNETWORK_CONNECTION";
		else if(value == 5) 
			return "OT_PHYSICAL_TERMINATION_POINT";
		else if(value == 6) 
			return "OT_PROTECTION_GROUP";
		else if(value == 7)
			return "OT_EQUIPMENT";
		else if(value == 8)
			return "OT_TOPOLOGICAL_LINK";
		else if(value == 9)
			return "OT_TRAFFIC_DESCRIPTOR";
		else if(value == 10)
			return "OT_TRAFFIC_DESCRIPTOR";
		else if(value == 11)
			return "OT_EQUIPMENT_HOLDER";
		else if(value == 12)
			return "OT_TRAFFIC_DESCRIPTOR";
		else if(value == 13)
			return "OT_SUBNETWORK_CONNECTION";
		else if(value == 14)
			return "CI_TEMP";
		else if(value == 15)
			return "OT_PROTECTION_GROUP";
		else if(value == 16)
			return "OT_TRAFFIC_DESCRIPTOR";
		else if(value == 17)
			return "MSP";
		else if(value == 18)
			return "OT_TRAFFIC_DESCRIPTOR";
		else if(value == 19)
			return "MCN";
		else if(value == 20)
			return "CCN";
		else if(value == 21)
			return "OT_PROTECTION_GROUP";
		else if(value == 22)
			return "SLOT";
		else if(value == 23)
			return "T_CONNECTION_TERMINATION_POINT";
		else if(value == 24)
			return "OT_EQUIPMENT_HOLDER";
		else if(value == 25)
			return "OT_EQUIPMENT_HOLDER";
		else if(value == 26)
			return "OT_EQUIPMENT_HOLDER";
		else
			return "OT_EQUIPMENT_HOLDER";
	}
	/**
	 * 用于判断告警是否对业务产生影响
	 * @param value
	 * @return
	 */
	private String isServiceAffection(int value){
		if(value == 1)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 2) 
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 3) 
			return "SA_SERVICE_AFFECTING";
		else if(value == 4) 
			return "SA_SERVICE_AFFECTING";
		else if(value == 5) 
			return "SA_SERVICE_AFFECTING";
		else if(value == 6) 
			return "SA_SERVICE_AFFECTING";
		else if(value == 7)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 8)
			return "SA_SERVICE_AFFECTING";
		else if(value == 9)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 10)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 11)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 12)
			return "SA_SERVICE_AFFECTING";
		else if(value == 13)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 14)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 15)
			return "SA_SERVICE_AFFECTING";
		else if(value == 16)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 17)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 18)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 19)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 20)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 21)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 22)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 23)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 24)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 25)
			return "SA_NON_SERVICE_AFFECTING";
		else if(value == 26)
			return "SA_NON_SERVICE_AFFECTING";
		else
			return "SA_UNKNOWN";
	}
	
	/**
	 * 告警类型
	 */
	private String getAlarmType(int value) {
		String str = null;
		switch (value) {
		case 1:
			str ="communicationsAlarm";
			break;
		case 2:
			str = "qualityofServiceAlarm";
			break;
		case 3:
			str = "equipmentAlarm";
			break;
		case 4:
			str = "processingErrorAlarm";
			break;
		case 5:
			str = "environmentalAlarm";
			break;
		default:
			str = "communicationsAlarm";
			break;
		}
		return str;
	}
	
}
