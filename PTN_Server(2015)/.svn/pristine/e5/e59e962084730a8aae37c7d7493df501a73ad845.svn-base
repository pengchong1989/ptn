package com.nms.corba.ninterface.impl.notification.cvthander;

import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import notifications.NVList_THelper;
import notifications.NameAndAnyValue_T;
import notifications.ObjectType_T;

import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;

import subnetworkConnection.CrossConnect_T;
import subnetworkConnection.CrossConnect_THelper;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.CorbaServer;
import com.nms.corba.ninterface.framework.notify.CorbaNotifyCvtHandler;
import com.nms.corba.ninterface.framework.notify.INotifyProcess;
import com.nms.corba.ninterface.impl.resource.tool.CorbaResConvertTool;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.service.notify.Message;
import com.nms.ui.manager.ExceptionManage;

/**
 * 上报LSP标签交换改变通知(tunnel)
 * 上报PW标签交换改变通知（单点pw）
 * 上报TDM仿真业务改变通知（ces单点）
 * @author xuxx
 */
public class CrossConnect_CvtHandler extends CorbaNotifyCvtHandler implements INotifyProcess {

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
		//判断消息体的类型
		if (null == msg.getSubEventName() || "".equals(msg.getSubEventName())) {
			return null;
		}
		if (null != ((Map<String, String>)msg.getMsgBody()).get("userLabel")){
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

		NameAndStringValue_T[] ETHName = new NameAndStringValue_T[3];
			ETHName = getETHServiceNameChg(msg);
		if (ETHName == null) {
			idlEvent = null;
			return ;
		}
		idlEvent.filterable_data[0] = filterable_objectName(ETHName);
		idlEvent.filterable_data[1] = filterable_objectType(getObjectType());
		idlEvent.filterable_data[2] = filterable_emsTime();
		idlEvent.filterable_data[3] = filterable_objectTypeQualifier("OT_CROSSCONNECTION");
		idlEvent.filterable_data[4] = new Property();
		idlEvent.filterable_data[4] .name = new String("attributeList");
		idlEvent.filterable_data[4] .value = CorbaServer.getInstanse().createAny();
		NameAndAnyValue_T[]  attributeList = getAttributeList(msg);
		if (null == attributeList) {
			idlEvent = null;
			return ;
		}
		NVList_THelper.insert(idlEvent.filterable_data[4] .value, attributeList);
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
			
	}
    
	/**
	 * 删除的上报
	 * @param msg 上报消息
	 * @param idlEvent 可过滤事件体
	 */
	protected void convertObjectDeletion(Message msg, StructuredEvent idlEvent) {

		NameAndStringValue_T[] ETHName = new NameAndStringValue_T[3];
		ETHName = getETHServiceName(msg);
		if (null == ETHName) {
			idlEvent = null;
			return ;
		}
		idlEvent.filterable_data = new Property[4];
		idlEvent.filterable_data[0] = filterable_objectName(ETHName);
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

		NameAndStringValue_T[] ETHName;
		ETHName = getETHServiceName(msg);
		if (null == ETHName) {
			idlEvent = null;
			return ;
		}
		idlEvent.filterable_data = new Property[4];
		idlEvent.filterable_data[0] = filterable_objectName(ETHName);
		idlEvent.filterable_data[1] = filterable_objectType(getObjectType());
		idlEvent.filterable_data[2] = filterable_emsTime();
		idlEvent.filterable_data[3] = filterable_objectTypeQualifier("");
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
		
		convertTool(msg,idlEvent);
	}
	
	/**
	 * 状态改变上报
	 * @param msg 上报消息
	 * @param idlEvent 可过滤事件体
	 */
	protected void convertObjectStateChg(Message msg, StructuredEvent idlEvent) {
		idlEvent.filterable_data = new Property[5];
		NameAndStringValue_T[] ETHName = new NameAndStringValue_T[3];
			ETHName = getETHServiceNameChg(msg);
		if (ETHName == null) {
			idlEvent = null;
			return ;
		}
		idlEvent.filterable_data[0] = filterable_objectName(ETHName);
		idlEvent.filterable_data[1] = filterable_objectType(getObjectType());
		idlEvent.filterable_data[2] = filterable_emsTime();
		idlEvent.filterable_data[3] = filterable_objectTypeQualifier("OT_CROSSCONNECTION");
		idlEvent.filterable_data[4] = new Property();
		idlEvent.filterable_data[4].name = new String("stateList");
		idlEvent.filterable_data[4].value = CorbaServer.getInstanse().createAny();

		NameAndAnyValue_T[]  stateList = getStateList(msg);
		if (null == stateList) {
			idlEvent = null;
			return ;
		}
		NVList_THelper.insert(idlEvent.filterable_data[4].value, stateList);
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
	}
	
	/**
	 * 获得上报对象改变后的状态
	 * @param msg
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private NameAndAnyValue_T[] getStateList(Message msg){
		NameAndAnyValue_T[] stateList = null;
		//判断消息体的类型
		if (null == msg.getSubEventName() || "".equals(msg.getSubEventName())) {
			return null;
		}
		if (null != ((Map<String, String>)msg.getMsgBody()).get("status")) {
			stateList = new NameAndAnyValue_T[1];
			stateList[0] = new NameAndAnyValue_T("status", CorbaServer.getInstanse().createAny());
			if (((Map<String, String>)msg.getMsgBody()).get("status").equals("1")) {
				stateList[0].value.insert_string("ACTIVITY");
			}else if (((Map<String, String>)msg.getMsgBody()).get("status").equals("0")) {
				stateList[0].value.insert_string("NONE");
			}else {
				stateList[0].value.insert_string("UNACTIVITY");
			}
		}
		
		return stateList;
	}
	
	/**
	 * 根据上报不同信息给对象赋值
	 * @param msg 上报的信息
	 * @return
	 */
	private NameAndStringValue_T[] getETHServiceName(Message msg) {
		if (null == msg.getSubEventName() || "".equals(msg.getSubEventName())) {
			return null;
		}
		NameAndStringValue_T[] ETHName = new NameAndStringValue_T[3];
		int siteId = 0;
		ETHName[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		if ((ELayerRate.TUNNEL.getValue()+"").equals(msg.getSubEventName())) {
			if (((Tunnel)msg.getMsgBody()).getASiteId() > 0) {
				siteId = ((Tunnel)msg.getMsgBody()).getASiteId();
			}else if (((Tunnel)msg.getMsgBody()).getZSiteId() > 0) {
				siteId = ((Tunnel)msg.getMsgBody()).getZSiteId();
			}
			ETHName[1] = new NameAndStringValue_T("ManagedElement",siteId+"");
			ETHName[2] = new NameAndStringValue_T("CrossConnection",ELayerRate.TUNNEL.getValue() + "/" + ((Tunnel)msg.getMsgBody()).getTunnelId());
		}else if ((ELayerRate.PW.getValue()+"").equals(msg.getSubEventName())) {
			if (((PwInfo)msg.getMsgBody()).getASiteId() > 0) {
				siteId = ((PwInfo)msg.getMsgBody()).getASiteId();
			}else if (((PwInfo)msg.getMsgBody()).getZSiteId() > 0) {
				siteId = ((PwInfo)msg.getMsgBody()).getZSiteId();
			}
			ETHName[1] = new NameAndStringValue_T("ManagedElement",siteId+"");
			ETHName[2] = new NameAndStringValue_T("CrossConnection",ELayerRate.PW.getValue() + "/" + ((PwInfo)msg.getMsgBody()).getPwId());
		}else if ((ELayerRate.CES.getValue()+"").equals(msg.getSubEventName())) {
			if (((CesInfo)msg.getMsgBody()).getaSiteId() > 0) {
				siteId = ((CesInfo)msg.getMsgBody()).getaSiteId();
			}else if (((CesInfo)msg.getMsgBody()).getzSiteId() > 0) {
				siteId = ((CesInfo)msg.getMsgBody()).getzSiteId();
			}
			ETHName[1] = new NameAndStringValue_T("ManagedElement",siteId+"");
			ETHName[2] = new NameAndStringValue_T("CrossConnection",ELayerRate.CES.getValue() + "/" + ((CesInfo)msg.getMsgBody()).getId());
		}
		return ETHName;
	}

	/**
	 * 修改属性时根据上报不同信息给对象赋值
	 * @param msg 上报的信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private NameAndStringValue_T[] getETHServiceNameChg(Message msg) {
		if (null == msg.getSubEventName() || "".equals(msg.getSubEventName()) 
				||null == ((Map<String, String>)msg.getMsgBody()).get("siteId")) {
			return null;
		}
		NameAndStringValue_T[] ETHName = new NameAndStringValue_T[3];
		ETHName[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		ETHName[1] = new NameAndStringValue_T("ManagedElement",((Map<String, String>)msg.getMsgBody()).get("siteId"));
		if ((ELayerRate.TUNNEL.getValue()+"").equals(msg.getSubEventName())) {
			ETHName[2] = new NameAndStringValue_T("CrossConnection",ELayerRate.TUNNEL.getValue() + "/" + ((Map<String, String>)msg.getMsgBody()).get("id"));
		}else if ((ELayerRate.PW.getValue()+"").equals(msg.getSubEventName())) {
			ETHName[2] = new NameAndStringValue_T("CrossConnection",ELayerRate.PW.getValue() + "/" + ((Map<String, String>)msg.getMsgBody()).get("id"));
		}else if ((ELayerRate.CES.getValue()+"").equals(msg.getSubEventName())) {
			ETHName[2] = new NameAndStringValue_T("CrossConnection",ELayerRate.CES.getValue() + "/" + ((Map<String, String>)msg.getMsgBody()).get("id"));
		}
		
		return ETHName;
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
		CorbaResConvertTool corbaResConvertTool=new CorbaResConvertTool();
		try {
			if ((ELayerRate.TUNNEL.getValue()+"").equals(msg.getSubEventName())) {
				List<Tunnel> tunnelList = new ArrayList<Tunnel>();
				tunnelList.add((Tunnel)msg.getMsgBody());
				List<CrossConnect_T> crossConnectTunnel = new ArrayList<CrossConnect_T>();
					crossConnectTunnel = corbaResConvertTool.tunnelDATAXToCorba(tunnelList);
				if (null == crossConnectTunnel || crossConnectTunnel.size() == 0) {
					idlEvent = null;
					return ;
				}
				CrossConnect_THelper.insert(idlEvent.remainder_of_body, crossConnectTunnel.get(0));
			}else if ((ELayerRate.PW.getValue()+"").equals(msg.getSubEventName())) {
				CrossConnect_T crossConnectPw = corbaResConvertTool.convertCrossConnect((PwInfo)msg.getMsgBody());
				CrossConnect_THelper.insert(idlEvent.remainder_of_body, crossConnectPw);
			}else if ((ELayerRate.CES.getValue()+"").equals(msg.getSubEventName())) {
				CrossConnect_T crossConnectCes = corbaResConvertTool.convertCrossConnect((CesInfo)msg.getMsgBody());
				CrossConnect_THelper.insert(idlEvent.remainder_of_body, crossConnectCes);
			}
		} catch (ProcessingFailureException e) {
			idlEvent = null;
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
