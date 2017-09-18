package com.nms.corba.ninterface.impl.notification.cvthander;

import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.Map;

import notifications.NVList_THelper;
import notifications.NameAndAnyValue_T;
import notifications.ObjectType_T;

import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;

import topologicalLink.TopologicalLink_T;
import topologicalLink.TopologicalLink_THelper;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.CorbaServer;
import com.nms.corba.ninterface.framework.notify.CorbaNotifyCvtHandler;
import com.nms.corba.ninterface.framework.notify.INotifyProcess;
import com.nms.corba.ninterface.impl.resource.tool.CorbaResConvertTool;
import com.nms.db.bean.path.Segment;
import com.nms.service.notify.Message;
import com.nms.ui.manager.ExceptionManage;

/**
 * 拓扑连接的创建/删除/修改通知
 * @author xuxx
 */
public class Segment_CvtHandler extends CorbaNotifyCvtHandler implements INotifyProcess {

	@Override
	public ObjectType_T getObjectType() {
		return ObjectType_T.OT_TOPOLOGICAL_LINK;
	}

	/**
	 * 修改的属性消息的处理
	 * @param msg 上报的消息
	 * @return 修改的属性列表
	 */
	@SuppressWarnings("unchecked")
	private NameAndAnyValue_T[] getAttributeList(Message msg) {
		NameAndAnyValue_T[] attributeList = new NameAndAnyValue_T[2];
		//判断消息体的类型
		if (null == msg.getSubEventName() || "".equals(msg.getSubEventName())) {
			return null;
		}
		if (null != ((Map<String, String>)msg.getMsgBody()).get("userLabel")) {
			attributeList = new NameAndAnyValue_T[2];
			attributeList[0] = new NameAndAnyValue_T("userLabel", CorbaServer.getInstanse().createAny());
			attributeList[0].value.insert_string(((Map<String, String>)msg.getMsgBody()).get("userLabel"));
			attributeList[1] = new NameAndAnyValue_T("nativeEMSName", CorbaServer.getInstanse().createAny());
			attributeList[1].value.insert_string(((Map<String, String>)msg.getMsgBody()).get("userLabel"));
		}
		return attributeList;
	}
	
	/**
	 * 属性修改消息上报
	 * @param msg 上报消息
	 * @param idlEvent 可过滤事件体
	 */
	protected void convertObjectAttributeChg(Message msg,StructuredEvent idlEvent){
		idlEvent.filterable_data = new Property[5];

		NameAndStringValue_T[] SegName = new NameAndStringValue_T[3];
		SegName = getSegmentNameChg(msg);
		if (null == SegName) {
			idlEvent = null;
			return;
		}
		idlEvent.filterable_data[0] = filterable_objectName(SegName);
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
	 * 删除的上报
	 * @param msg 上报消息
	 * @param idlEvent 可过滤事件体
	 */
	protected void convertObjectDeletion(Message msg, StructuredEvent idlEvent) {

		NameAndStringValue_T[] SegName = new NameAndStringValue_T[3];
		SegName = getSegmentName(msg);
		if (null == SegName) {
			idlEvent = null;
			return;
		}
		idlEvent.filterable_data = new Property[4];
		idlEvent.filterable_data[0] = filterable_objectName(SegName);
		idlEvent.filterable_data[1] = filterable_objectType(getObjectType());
		idlEvent.filterable_data[2] = filterable_emsTime();
		idlEvent.filterable_data[3] = filterable_objectTypeQualifier("");
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
		idlEvent.remainder_of_body.insert_string("");
			
	}

	/**
	 * 新建消息上报
	 * @param msg 上报消息
	 * @param idlEvent 可过滤事件体
	 */
	protected void convertObjectCreation(Message msg, StructuredEvent idlEvent) {

		NameAndStringValue_T[] segName;
		segName = getSegmentName(msg);
		if (null == segName) {
			idlEvent = null;
			return;
		}
		idlEvent.filterable_data = new Property[4];
		idlEvent.filterable_data[0] = filterable_objectName(segName);
		idlEvent.filterable_data[1] = filterable_objectType(getObjectType());
		idlEvent.filterable_data[2] = filterable_emsTime();
		idlEvent.filterable_data[3] = filterable_objectTypeQualifier("");
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();

		convertTool(msg,idlEvent);
	}

	/**
	 * 新建，删除拓扑连接名称
	 * @param msg
	 * @return
	 */
	private NameAndStringValue_T[] getSegmentName(Message msg) {
		if (null == msg.getSubEventName() || "".equals(msg.getSubEventName())) {
			return null;
		}
		NameAndStringValue_T[] SegName = new NameAndStringValue_T[2];
		SegName[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		SegName[1] = new NameAndStringValue_T("TopologicalLink",((Segment)msg.getMsgBody()).getId()+"");
		
		return SegName;
	}

	/**
	 * 修改属性时赋值拓扑对象名称
	 * @param msg 上报的信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private NameAndStringValue_T[] getSegmentNameChg(Message msg) {
		if (null == msg.getSubEventName() || "".equals(msg.getSubEventName())) {
			return null;
		}
		NameAndStringValue_T[] SegName = new NameAndStringValue_T[2];
		SegName[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		SegName[1] = new NameAndStringValue_T("TopologicalLink",((Map<String, String>)msg.getMsgBody()).get("id"));
		
		return SegName;
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
		TopologicalLink_T link = new TopologicalLink_T();
		CorbaResConvertTool convertTool = new CorbaResConvertTool();
		try {
			convertTool.corbaTopologicalLinkConvrtTool((Segment)msg.getMsgBody(),link);
		} catch (ProcessingFailureException e) {
			idlEvent = null;
			ExceptionManage.dispose(e,this.getClass());
		}
		TopologicalLink_THelper.insert(idlEvent.remainder_of_body, link);
	}
}
