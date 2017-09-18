package com.nms.corba.ninterface.framework.notify;

import globaldefs.NVSList_THelper;
import globaldefs.NameAndStringValue_T;

import java.text.SimpleDateFormat;
import java.util.Date;

import notifications.ObjectType_T;
import notifications.ObjectType_THelper;

import org.apache.log4j.Logger;
import org.omg.CosNotification.EventHeader;
import org.omg.CosNotification.EventType;
import org.omg.CosNotification.FixedEventHeader;
import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;

import com.nms.corba.ninterface.framework.CorbaServer;
import com.nms.service.notify.Message;

public abstract class CorbaNotifyCvtHandler implements INotifyCvtHandler {

	protected static Logger LOG = Logger.getLogger(CorbaNotifyCvtHandler.class.getName());

	public StructuredEvent convert(Message msg) {
		if (msg == null) {
			LOG.error("msg is empty!");
			return null;
		}

		StructuredEvent idlEvent = new StructuredEvent();
		idlEvent.header = new EventHeader();
		idlEvent.header.fixed_header = new FixedEventHeader();
		idlEvent.header.fixed_header.event_name = new String("");
		idlEvent.header.fixed_header.event_type = new EventType();
		idlEvent.header.fixed_header.event_type.domain_name = new String("tmf_mtnm");
		idlEvent.header.variable_header = new Property[0];

		switch (msg.getMsgType()) {
		case CREATION:
			idlEvent.header.fixed_header.event_type.type_name = new String("NT_OBJECT_CREATION");
			convertObjectCreation(msg, idlEvent);
			break;
		case DELETION:
			idlEvent.header.fixed_header.event_type.type_name = new String("NT_OBJECT_DELETION");
			convertObjectDeletion(msg, idlEvent);
			break;
		case ATTRIBUTECHG:
			idlEvent.header.fixed_header.event_type.type_name = new String("NT_ATTRIBUTE_VALUE_CHANGE");
			convertObjectAttributeChg(msg, idlEvent);
			break;
		case STATECHG:
			idlEvent.header.fixed_header.event_type.type_name = new String("NT_STATE_VALUE_CHANGE");
			convertObjectStateChg(msg, idlEvent);
			break;

		case ALARM: // alarm
			idlEvent.header.fixed_header.event_type.type_name = new String("NT_ALARM");
			convertAlarm2Event(msg, idlEvent);
			break;
		case TCA: // tca
			idlEvent.header.fixed_header.event_type.type_name = new String("NT_TCA");
			convertTcaAlarm2Event(msg, idlEvent);
			break;
		case PSE: // pse
			idlEvent.header.fixed_header.event_type.type_name = new String("NT_PROTECTION_SWITCH");
			convertPse2Event(msg, idlEvent);
			break;
		case EPSE: // pse
			idlEvent.header.fixed_header.event_type.type_name = new String("NT_EPROTECTION_SWITCH");
			convertEPse2Event(msg, idlEvent);
			break;
		default:
			idlEvent = null;
			break;
		}

		return idlEvent;
	}

	protected void convertEPse2Event(Message msg, StructuredEvent idlEvent) {
		// TODO Auto-generated method stub

	}

	protected void convertPse2Event(Message msg, StructuredEvent idlEvent) {
		// TODO Auto-generated method stub

	}

	protected void convertTcaAlarm2Event(Message msg, StructuredEvent idlEvent) {
		// TODO Auto-generated method stub

	}

	protected void convertAlarm2Event(Message msg, StructuredEvent idlEvent) {
		// TODO Auto-generated method stub

	}

	protected void convertObjectStateChg(Message msg, StructuredEvent idlEvent) {

	}

	protected void convertObjectAttributeChg(Message msg, StructuredEvent idlEvent) {
	}

	protected void convertObjectDeletion(Message msg, StructuredEvent idlEvent) {

	}

	protected void convertObjectCreation(Message msg, StructuredEvent idlEvent) {

	}

	protected Property filterable_objectName(NameAndStringValue_T[] objName) {
		Property filterData = new Property();
		filterData.name = new String("objectName");
		filterData.value = CorbaServer.getInstanse().createAny();
		if (objName == null) {
			objName = new NameAndStringValue_T[0];
		}
		NVSList_THelper.insert(filterData.value, objName);

		return filterData;
	}

	protected Property filterable_objectType(ObjectType_T objectType) {
		Property filterData = new Property();
		filterData.name = new String("objectType");
		filterData.value = CorbaServer.getInstanse().createAny();
		if (objectType == null) {
			objectType = ObjectType_T.OT_AID;
		}
		ObjectType_THelper.insert(filterData.value, objectType);
		return filterData;
	}

	protected Property filterable_emsTime() {
		Property filterData = new Property();
		filterData.name = new String("emsTime");
		filterData.value = CorbaServer.getInstanse().createAny();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTime = sdf.format(date);
		filterData.value.insert_string(currentTime);
		return filterData;
	}

	protected Property filterable_objectTypeQualifier(String objectTypeQualifier) {
		Property filterData = new Property();
		filterData.name = new String("objectTypeQualifier");
		filterData.value = CorbaServer.getInstanse().createAny();
		filterData.value.insert_string(objectTypeQualifier);
		return filterData;
	}

}
