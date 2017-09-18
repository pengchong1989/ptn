package com.nms.corba.ninterface.impl.notification.cvthander;

import equipment.Equipment_T;
import equipment.Equipment_THelper;
import globaldefs.NameAndStringValue_T;
import notifications.ObjectType_T;

import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.CorbaServer;
import com.nms.corba.ninterface.framework.notify.CorbaNotifyCvtHandler;
import com.nms.corba.ninterface.framework.notify.INotifyProcess;
import com.nms.corba.ninterface.impl.resource.tool.EquipmentConertTool;
import com.nms.db.bean.equipment.card.CardInst;
import com.nms.service.notify.Message;
import com.nms.ui.manager.ExceptionManage;

/**
 * 单板创建/删除通知
 * @author xuxx
 */
public class Card_CvtHandler extends CorbaNotifyCvtHandler implements INotifyProcess {

	@Override
	public ObjectType_T getObjectType() {
		return ObjectType_T.OT_EQUIPMENT;
	}
	
	/**
	 * 删除的上报
	 * @param msg 上报消息
	 * @param idlEvent 可过滤事件体
	 */
	protected void convertObjectDeletion(Message msg, StructuredEvent idlEvent) {

		NameAndStringValue_T[] cardName = new NameAndStringValue_T[3];
		cardName = getCardNameChg(msg);
		if (null == cardName) {
			idlEvent = null;
			return ;
		}
		idlEvent.filterable_data = new Property[4];
		idlEvent.filterable_data[0] = filterable_objectName(cardName);
		idlEvent.filterable_data[1] = filterable_objectType(getObjectType());
		idlEvent.filterable_data[2] = filterable_emsTime();
		idlEvent.filterable_data[3] = filterable_objectTypeQualifier("");
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
			
	}

	/**
	 * 新建消息上报
	 * @param msg 上报消息
	 * @param idlEvent 可过滤事件体
	 */
	protected void convertObjectCreation(Message msg, StructuredEvent idlEvent) {

		NameAndStringValue_T[] cardName;
		cardName = getCardNameChg(msg);
		if (null == cardName) {
			idlEvent = null;
			return ;
		}
		idlEvent.filterable_data = new Property[4];
		idlEvent.filterable_data[0] = filterable_objectName(cardName);
		idlEvent.filterable_data[1] = filterable_objectType(getObjectType());
		idlEvent.filterable_data[2] = filterable_emsTime();
		idlEvent.filterable_data[3] = filterable_objectTypeQualifier("");
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
			
		convertTool(msg,idlEvent);
	}

	/**
	 * 赋值拓扑对象名称
	 * @param msg 上报的信息
	 * @return CORBA端CORBA端对象
	 */
	private NameAndStringValue_T[] getCardNameChg(Message msg) {
		if (null == msg.getSubEventName() || "".equals(msg.getSubEventName())) {
			return null;
		}
		NameAndStringValue_T[] cardName = new NameAndStringValue_T[4];
		cardName[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		cardName[1] = new NameAndStringValue_T("ManagedElement",((CardInst)msg.getMsgBody()).getSiteId()+"");
		cardName[2] = new NameAndStringValue_T("EquipmentHolder","/rack=1/shelf=1/slot="+((CardInst)msg.getMsgBody()).getSlotInst().getNumber());
		cardName[3] = new NameAndStringValue_T("Equipment","1");
		return cardName;
	}
				
	/**
	 * 根据具体类型进行转换
	 * @param msg 上报消息
	 * @param idlEvent 可过滤事件体
	 */
	private void convertTool(Message msg, StructuredEvent idlEvent) {
		if (null == msg.getSubEventName() || "".equals(msg.getSubEventName())) {
			idlEvent = null;
			return ;
		}
		Equipment_T equipment = new Equipment_T();
		EquipmentConertTool conertTool = new EquipmentConertTool();
		try {
			conertTool.convertEquipment_T((CardInst)msg.getMsgBody(),((CardInst)msg.getMsgBody()).getSlotInst(),equipment);
		} catch (Exception e) {
			idlEvent = null;
			ExceptionManage.dispose(e,this.getClass());
		}
		Equipment_THelper.insert(idlEvent.remainder_of_body, equipment);
	}
	
}
