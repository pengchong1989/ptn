package com.nms.corba.ninterface.impl.notification.cvthander;

import globaldefs.NameAndStringValue_T;

import java.util.Map;

import notifications.NVList_THelper;
import notifications.NameAndAnyValue_T;
import notifications.ObjectType_T;

import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;

import performance.PMCollectionTask_T;
import performance.PMCollectionTask_THelper;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.CorbaServer;
import com.nms.corba.ninterface.framework.notify.CorbaNotifyCvtHandler;
import com.nms.corba.ninterface.framework.notify.INotifyProcess;
import com.nms.corba.ninterface.impl.performance.PerformanceTool;
import com.nms.db.bean.perform.PerformanceTaskInfo;
import com.nms.service.notify.Message;

/**
 * 性能任务改变通知
 * @author xuxx
 */
public class Performence_CvtHandler  extends CorbaNotifyCvtHandler implements INotifyProcess{

	@Override
	public ObjectType_T getObjectType() {
		return ObjectType_T.OT_AID;
	}
	
	/**
	 * 修改的属性消息的处理
	 * @param msg 上报的消息
	 * @return 修改的属性列表
	 */
	@SuppressWarnings("unchecked")
	private NameAndAnyValue_T[] getAttributeList(Message msg) {
		NameAndAnyValue_T[] attributeList = null;
		if (null != (Map<String, String>)msg.getMsgBody()){
			int k = ((Map<String, String>)msg.getMsgBody()).size();
			attributeList = new NameAndAnyValue_T[k-1];
			int i = 0;
			if (null != ((Map<String, String>)msg.getMsgBody()).get("userLabel")) {
				attributeList[i] = new NameAndAnyValue_T("userLabel", CorbaServer.getInstanse().createAny());
				attributeList[i].value.insert_string(((Map<String, String>)msg.getMsgBody()).get("userLabel"));
				i++;
			}
			if (null != ((Map<String, String>)msg.getMsgBody()).get("taskLabel")) {
				attributeList[i] = new NameAndAnyValue_T("taskLabel", CorbaServer.getInstanse().createAny());
				attributeList[i].value.insert_string(((Map<String, String>)msg.getMsgBody()).get("taskLabel"));
				i++;
			}
			if (null != ((Map<String, String>)msg.getMsgBody()).get("runstates")) {
				attributeList[i] = new NameAndAnyValue_T("runstates", CorbaServer.getInstanse().createAny());
				attributeList[i].value.insert_string(((Map<String, String>)msg.getMsgBody()).get("runstates"));
				i++;
			}
			if (null != ((Map<String, String>)msg.getMsgBody()).get("siteInst")) {
				attributeList[i] = new NameAndAnyValue_T("siteInst", CorbaServer.getInstanse().createAny());
				attributeList[i].value.insert_string(((Map<String, String>)msg.getMsgBody()).get("siteInst"));
				i++;
			}
			if (null != ((Map<String, String>)msg.getMsgBody()).get("objectId")) {
				attributeList[i] = new NameAndAnyValue_T("objectId", CorbaServer.getInstanse().createAny());
				attributeList[i].value.insert_string(((Map<String, String>)msg.getMsgBody()).get("objectId"));
				i++;
			}
			if (null != ((Map<String, String>)msg.getMsgBody()).get("perforType")) {
				attributeList[i] = new NameAndAnyValue_T("perforType", CorbaServer.getInstanse().createAny());
				attributeList[i].value.insert_string(((Map<String, String>)msg.getMsgBody()).get("perforType"));
			}
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

		NameAndStringValue_T[] performName;
		performName = getPerformNameNameChg(msg);
		if (null == performName) {
			idlEvent = null;
			return;
		}
		idlEvent.filterable_data[0] = filterable_objectName(performName);
		idlEvent.filterable_data[1] = filterable_objectType(getObjectType());
		idlEvent.filterable_data[2] = filterable_emsTime();
		idlEvent.filterable_data[3] = filterable_objectTypeQualifier("OT_PERFORMANCE_TASK");
		idlEvent.filterable_data[4] = new Property();
		idlEvent.filterable_data[4].name = new String("attributeList");
		idlEvent.filterable_data[4].value = CorbaServer.getInstanse().createAny();
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

		NameAndStringValue_T[] performName ;
		performName = getPerformName(msg);
		if (null == performName) {
			idlEvent = null;
			return ;
		}
		idlEvent.filterable_data = new Property[4];
		idlEvent.filterable_data[0] = filterable_objectName(performName);
		idlEvent.filterable_data[1] = filterable_objectType(getObjectType());
		idlEvent.filterable_data[2] = filterable_emsTime();
		idlEvent.filterable_data[3] = filterable_objectTypeQualifier("OT_PERFORMANCE_TASK");
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
		
	}

	/**
	 * 新建消息上报
	 * @param msg 上报消息
	 * @param idlEvent 可过滤事件体
	 */
	protected void convertObjectCreation(Message msg, StructuredEvent idlEvent) {

		NameAndStringValue_T[] ETHName;
		ETHName = getPerformName(msg);
		if (null == ETHName) {
			idlEvent = null;
			return;
		}
		idlEvent.filterable_data = new Property[4];
		idlEvent.filterable_data[0] = filterable_objectName(ETHName);
		idlEvent.filterable_data[1] = filterable_objectType(getObjectType());
		idlEvent.filterable_data[2] = filterable_emsTime();
		idlEvent.filterable_data[3] = filterable_objectTypeQualifier("OT_PERFORMANCE_TASK");
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();

		convertTool(msg,idlEvent);
	}

	/**
	 * 根据上报不同信息给对象赋值
	 * @param msg 上报的信息
	 * @return
	 */
	private NameAndStringValue_T[] getPerformName(Message msg) {
		NameAndStringValue_T[] performName = new NameAndStringValue_T[2];
		performName[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		performName[1] = new NameAndStringValue_T("PMTask",((PerformanceTaskInfo) msg.getMsgBody()).getName()+"");
		return performName;
	}

	/**
	 * 修改属性时根据上报不同信息给对象赋值
	 * @param msg 上报的信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private NameAndStringValue_T[] getPerformNameNameChg(Message msg) {
		NameAndStringValue_T[] performeName = new NameAndStringValue_T[2];
		performeName[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		performeName[1] = new NameAndStringValue_T("PMTask",((Map<String, String>) msg.getMsgBody()).get("userLabel"));
		return performeName;
	}
		
	/**
	 * 根据具体类型进行转换
	 * @param msg 上报消息
	 * @param idlEvent 可过滤事件体
	 */
	private void convertTool(Message msg, StructuredEvent idlEvent) {
		PerformanceTool performanceTool = new PerformanceTool();
		PMCollectionTask_T collectionTask_T = performanceTool.converPMCllectionTask_T((PerformanceTaskInfo)msg.getMsgBody());
		PMCollectionTask_THelper.insert(idlEvent.remainder_of_body, collectionTask_T);
	}
}
