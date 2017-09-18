package com.nms.corba.ninterface.impl.notification;

import globaldefs.NVSList_THelper;
import globaldefs.NameAndStringValue_T;

import java.text.SimpleDateFormat;
import java.util.Date;

import notifications.FileTransferStatus_T;
import notifications.FileTransferStatus_THelper;
import notifications.NVList_THelper;
import notifications.NameAndAnyValue_T;
import notifications.ObjectType_T;
import notifications.ObjectType_THelper;

import org.omg.CosNotification.EventHeader;
import org.omg.CosNotification.EventType;
import org.omg.CosNotification.FixedEventHeader;
import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;

import com.nms.corba.ninterface.framework.CorbaServer;
import com.nms.corba.ninterface.framework.CorbaSessionAcessTool;

public class CorbaNotifyReport {

	public static void emsAttributeChg(NameAndStringValue_T[] objectName,
			String name, String attributeValue) {
		StructuredEvent idlEvent = new StructuredEvent();
		idlEvent.header = new EventHeader();
		idlEvent.header.fixed_header = new FixedEventHeader();
		idlEvent.header.fixed_header.event_name = new String("");
		idlEvent.header.fixed_header.event_type = new EventType();
		idlEvent.header.fixed_header.event_type.domain_name = new String("tmf_mtnm");
		idlEvent.header.fixed_header.event_type.type_name = new String("NT_ATTRIBUTE_VALUE_CHANGE"); 
		idlEvent.header.variable_header = new Property[0];
		
		idlEvent.filterable_data = new Property[5];
		idlEvent.filterable_data[0] = new Property();
		idlEvent.filterable_data[0].name = new String("objectName");
		idlEvent.filterable_data[0].value = CorbaServer.getInstanse().createAny();
		NVSList_THelper.insert(idlEvent.filterable_data[0].value, objectName);
		idlEvent.filterable_data[1] = new Property();
		idlEvent.filterable_data[1].name = new String("objectType");
		idlEvent.filterable_data[1].value = CorbaServer.getInstanse().createAny();
		ObjectType_THelper.insert(idlEvent.filterable_data[1].value, ObjectType_T.OT_EMS);
		idlEvent.filterable_data[2] = new Property();
		idlEvent.filterable_data[2].name = new String("emsTime");
		idlEvent.filterable_data[2].value = CorbaServer.getInstanse().createAny();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTime = sdf.format(date);
		idlEvent.filterable_data[2].value.insert_string(currentTime);
		idlEvent.filterable_data[3] = new Property();
		idlEvent.filterable_data[3].name = new String("objectTypeQualifier");
		idlEvent.filterable_data[3].value = CorbaServer.getInstanse().createAny();
		idlEvent.filterable_data[3].value.insert_string("");
		idlEvent.filterable_data[4] = new Property();
		idlEvent.filterable_data[4] .name = new String("attributeList");
		idlEvent.filterable_data[4] .value = CorbaServer.getInstanse().createAny();
		NameAndAnyValue_T[]  attributeList = new NameAndAnyValue_T[1];
		attributeList[0] = new NameAndAnyValue_T();
		attributeList[0].value = CorbaServer.getInstanse().createAny();
		attributeList[0].name = name;
		attributeList[0].value.insert_string(attributeValue);
		
		NVList_THelper.insert(idlEvent.filterable_data[4] .value, attributeList);
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
		CorbaSessionAcessTool.BroadcastPublishEvent(idlEvent);
	}
	/**
	 * 子网信息修改上报
	 */
	public static void multilayerSubnetworkAttributeChg(NameAndStringValue_T[] subnetName,String key,String value){
		StructuredEvent idlEvent = new StructuredEvent();
		idlEvent.header = new EventHeader();
		idlEvent.header.fixed_header = new FixedEventHeader();
		idlEvent.header.fixed_header.event_name = new String("");
		idlEvent.header.fixed_header.event_type = new EventType();
		idlEvent.header.fixed_header.event_type.domain_name = new String("tmf_mtnm");
		idlEvent.header.fixed_header.event_type.type_name = new String("NT_ATTRIBUTE_VALUE_CHANGE"); 
		idlEvent.header.variable_header = new Property[0];
		idlEvent.filterable_data = new Property[5];
		idlEvent.filterable_data[0] = new Property();
		idlEvent.filterable_data[0].name = new String("objectName");
		idlEvent.filterable_data[0].value = CorbaServer.getInstanse().createAny();
		NVSList_THelper.insert(idlEvent.filterable_data[0].value, subnetName);
		idlEvent.filterable_data[1] = new Property();
		idlEvent.filterable_data[1].name = new String("objectType");
		idlEvent.filterable_data[1].value = CorbaServer.getInstanse().createAny();
		ObjectType_THelper.insert(idlEvent.filterable_data[1].value, ObjectType_T.OT_MULTILAYER_SUBNETWORK);
		idlEvent.filterable_data[2] = new Property();
		idlEvent.filterable_data[2].name = new String("emsTime");
		idlEvent.filterable_data[2].value = CorbaServer.getInstanse().createAny();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String currentTime = sdf.format(date);
		idlEvent.filterable_data[2].value.insert_string(currentTime);
		idlEvent.filterable_data[3] = new Property();
		idlEvent.filterable_data[3].name = new String("objectTypeQualifier");
		idlEvent.filterable_data[3].value = CorbaServer.getInstanse().createAny();
		idlEvent.filterable_data[3].value.insert_string("");
		idlEvent.filterable_data[4] = new Property();
		idlEvent.filterable_data[4] .name = new String("attributeList");
		idlEvent.filterable_data[4] .value = CorbaServer.getInstanse().createAny();
		NameAndAnyValue_T[]  attributeList = new NameAndAnyValue_T[1];
		attributeList[0] = new NameAndAnyValue_T();
		attributeList[0].value = CorbaServer.getInstanse().createAny();
		if (key.equals("SUBNETWORK_MAC")){
			//修改物理地址
			attributeList[0].name = "macLocation";
			attributeList[0].value.insert_string(value);
		}else {
			//修改友好名称
			attributeList[0].name = "userLabel";
			attributeList[0].value.insert_string(value);
		}
		
		NVList_THelper.insert(idlEvent.filterable_data[4] .value, attributeList);
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
		CorbaSessionAcessTool.BroadcastPublishEvent(idlEvent);
	}
	public static void fileTransferNotify(String fileName, boolean status) {
		StructuredEvent idlEvent = new StructuredEvent();
		idlEvent.header = new EventHeader();
		idlEvent.header.fixed_header = new FixedEventHeader();
		idlEvent.header.fixed_header.event_name = new String("");
		idlEvent.header.fixed_header.event_type = new EventType();
		idlEvent.header.fixed_header.event_type.domain_name = new String("tmf_mtnm");
		idlEvent.header.fixed_header.event_type.type_name = new String("NT_FILE_TRANSFER_STATUS"); 
		idlEvent.header.variable_header = new Property[0];
		
		idlEvent.filterable_data = new Property[4];
		idlEvent.filterable_data[0] = new Property();
		idlEvent.filterable_data[0].name = new String("fileName");
		idlEvent.filterable_data[0].value = CorbaServer.getInstanse().createAny();
		idlEvent.filterable_data[0].value.insert_string(fileName);
		
		idlEvent.filterable_data[1] = new Property();
		idlEvent.filterable_data[1].name = new String("transferStatus");
		idlEvent.filterable_data[1].value = CorbaServer.getInstanse().createAny();
		
		idlEvent.filterable_data[2] = new Property();
		idlEvent.filterable_data[2].name = new String("percentComplete");
		idlEvent.filterable_data[2].value = CorbaServer.getInstanse().createAny();
		idlEvent.filterable_data[3] = new Property();
		idlEvent.filterable_data[3].name = new String("failureReason");
		idlEvent.filterable_data[3].value = CorbaServer.getInstanse().createAny();
		//文件上傳成功
		if(status){
			FileTransferStatus_THelper.insert(idlEvent.filterable_data[1].value, FileTransferStatus_T.FT_COMPLETED);
			idlEvent.filterable_data[2].value.insert_short((short) 100);
			idlEvent.filterable_data[3].value.insert_string("");
		}else{
			FileTransferStatus_THelper.insert(idlEvent.filterable_data[1].value, FileTransferStatus_T.FT_FAILED);
			idlEvent.filterable_data[2].value.insert_short((short) 0);
			idlEvent.filterable_data[3].value.insert_string("file transfer failed");
		}
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
		CorbaSessionAcessTool.BroadcastPublishEvent(idlEvent);
	}

}
