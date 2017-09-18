package com.nms.corba.ninterface.impl.notification.cvthander;

import globaldefs.NameAndStringValue_T;
import notifications.NVList_THelper;
import notifications.NameAndAnyValue_T;
import notifications.ObjectType_T;

import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.CorbaServer;
import com.nms.corba.ninterface.framework.notify.CorbaNotifyCvtHandler;
import com.nms.corba.ninterface.framework.notify.INotifyProcess;
import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.service.notify.Message;

/**
 * 终端的修改通知 
 * @author xuxx
 */
public class Point_CvtHandler extends CorbaNotifyCvtHandler implements INotifyProcess {

	@Override
	public ObjectType_T getObjectType() {
		return ObjectType_T.OT_PHYSICAL_TERMINATION_POINT;
	}

	/**
	 * 属性修改消息上报
	 * @param msg 上报消息
	 * @param idlEvent 可过滤事件体
	 */
	protected void convertObjectAttributeChg(Message msg,StructuredEvent idlEvent){
		idlEvent.filterable_data = new Property[5];

		NameAndStringValue_T[] meName = new NameAndStringValue_T[3];
		meName = getETHServiceNameChg(msg);
		if (null == meName) {
			idlEvent = null;
			return;
		}
		idlEvent.filterable_data[0] = filterable_objectName(meName);
		idlEvent.filterable_data[1] = filterable_objectType(getObjectType());
		idlEvent.filterable_data[2] = filterable_emsTime();
		idlEvent.filterable_data[3] = filterable_objectTypeQualifier("");
		idlEvent.filterable_data[4] = new Property();
		idlEvent.filterable_data[4].name = new String("attributeList");
		idlEvent.filterable_data[4].value = CorbaServer.getInstanse()
				.createAny();
		NameAndAnyValue_T[] attributeList = getAttributeList(msg);
		if (null == attributeList) {
			idlEvent = null;
			return;
		}
		NVList_THelper.insert(idlEvent.filterable_data[4].value, attributeList);
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
			
	}
	
	/**
	 * 修改属性时根据上报不同信息给对象赋值
	 * @param msg 上报的信息
	 * @return
	 */
	private NameAndStringValue_T[] getETHServiceNameChg(Message msg){
		if (null == msg.getSubEventName() || "".equals(msg.getSubEventName())) {
			return null;
		}
		NameAndStringValue_T[] portName = new NameAndStringValue_T[3];
		portName[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		if (msg.getSubEventName().equals("e1")) {
			portName[1] = new NameAndStringValue_T("ManagedElement",((E1Info)msg.getMsgBody()).getSiteId()+"");
			portName[2] = new NameAndStringValue_T("PTP","/rack=1/shelf=1/slot=" +((E1Info)msg.getMsgBody()).getPortInst().getSlotNumber()+"/port="+((E1Info)msg.getMsgBody()).getPortInst().getPortName());
		}else {
			portName[1] = new NameAndStringValue_T("ManagedElement",((PortInst)msg.getMsgBody()).getSiteId()+"");
			portName[2] = new NameAndStringValue_T("PTP","/rack=1/shelf=1/slot=" +((PortInst)msg.getMsgBody()).getSlotNumber()+"/port=" + ((PortInst)msg.getMsgBody()).getPortName());
		}
		return portName;
	}
	
	/**
	 * 修改的属性消息的处理
	 * @param msg 上报的消息
	 * @return 修改的属性列表
	 */
	private NameAndAnyValue_T[] getAttributeList(Message msg) {
		NameAndAnyValue_T[] attributeList = new NameAndAnyValue_T[2];
		//判断消息体的类型
		if ("".equals(msg.getSubEventName())) {
			return null ;
		}
		
		attributeList[0] = new NameAndAnyValue_T("userLabel", CorbaServer.getInstanse().createAny());
		attributeList[1] = new NameAndAnyValue_T("nativeEMSName", CorbaServer.getInstanse().createAny());
		if (msg.getSubEventName().equals("e1")) {
			attributeList[0].value.insert_string(((E1Info)msg.getMsgBody()).getPortName());
			attributeList[1].value.insert_string(((E1Info)msg.getMsgBody()).getPortName());
		}else {
			attributeList[0].value.insert_string(((PortInst)msg.getMsgBody()).getPortName());
			attributeList[1].value.insert_string(((PortInst)msg.getMsgBody()).getPortName());
		}
		
		return attributeList;
	}
}
