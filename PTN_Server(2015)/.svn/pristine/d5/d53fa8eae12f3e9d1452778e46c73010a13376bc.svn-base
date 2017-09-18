package com.nms.corba.ninterface.impl.notification.cvthander;

import globaldefs.NameAndStringValue_T;

import java.util.Map;

import managedElement.ManagedElement_T;
import managedElement.ManagedElement_THelper;
import notifications.NVList_THelper;
import notifications.NameAndAnyValue_T;
import notifications.ObjectType_T;

import org.omg.CosNotification.Property;
import org.omg.CosNotification.StructuredEvent;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.CorbaServer;
import com.nms.corba.ninterface.framework.notify.CorbaNotifyCvtHandler;
import com.nms.corba.ninterface.framework.notify.INotifyProcess;
import com.nms.corba.ninterface.impl.manageElement.tool.CorbaManageElementTool;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.service.notify.Message;
import com.nms.ui.manager.ExceptionManage;

public class ManagedElement_CvtHandler extends CorbaNotifyCvtHandler implements INotifyProcess {

	@Override
	public ObjectType_T getObjectType() {
		return ObjectType_T.OT_MANAGED_ELEMENT;
	}

	// 网元属性改变
	@SuppressWarnings("unchecked")
	private NameAndAnyValue_T[] getAttributeList(Message msg) {
		NameAndAnyValue_T[] attributeList = null;
		if (null != (Map<String, String>)msg.getMsgBody()){
			int k = ((Map<String, String>)msg.getMsgBody()).keySet().size();
			attributeList = new NameAndAnyValue_T[k-1];
			int i = 0;
			if (null != ((Map<String, String>)msg.getMsgBody()).get("userLabel")) {
				attributeList[i] = new NameAndAnyValue_T("userLabel", CorbaServer.getInstanse().createAny());
				attributeList[i].value.insert_string(((Map<String, String>)msg.getMsgBody()).get("userLabel"));
				i++;
			}
			if (null != ((Map<String, String>)msg.getMsgBody()).get("createUser")) {
				attributeList[i] = new NameAndAnyValue_T("createUser", CorbaServer.getInstanse().createAny());
				attributeList[i].value.insert_string(((Map<String, String>)msg.getMsgBody()).get("createUser"));
				i++;
			}
			if (null != ((Map<String, String>)msg.getMsgBody()).get("isGateway")) {
				attributeList[i] = new NameAndAnyValue_T("isGateway", CorbaServer.getInstanse().createAny());
				attributeList[i].value.insert_string(((Map<String, String>)msg.getMsgBody()).get("isGateway"));
			}
		}
		return attributeList;
	}

	/**
	 * 网元修改上报
	 */
	protected void convertObjectAttributeChg(Message msg,StructuredEvent idlEvent) {
		idlEvent.filterable_data = new Property[5];

		NameAndStringValue_T[] meName = getManagedElementNameChg(msg);
		idlEvent.filterable_data[0] = filterable_objectName(meName);
		idlEvent.filterable_data[1] = filterable_objectType(getObjectType());
		idlEvent.filterable_data[2] = filterable_emsTime();
		idlEvent.filterable_data[3] = filterable_objectTypeQualifier("");

		idlEvent.filterable_data[4] = new Property();
		idlEvent.filterable_data[4].name = new String("attributeList");
		idlEvent.filterable_data[4].value = CorbaServer.getInstanse()
				.createAny();
		NameAndAnyValue_T[] attributeList = getAttributeList(msg);
		NVList_THelper.insert(idlEvent.filterable_data[4].value, attributeList);
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
		
	}

	protected void convertObjectDeletion(Message msg, StructuredEvent idlEvent) {
		idlEvent.filterable_data = new Property[4];

		NameAndStringValue_T[] meName = getManagedElementName((SiteInst) msg.getMsgBody());
		idlEvent.filterable_data[0] = filterable_objectName(meName);
		idlEvent.filterable_data[1] = filterable_objectType(getObjectType());
		idlEvent.filterable_data[2] = filterable_emsTime();
		idlEvent.filterable_data[3] = filterable_objectTypeQualifier("");
		idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
		idlEvent.remainder_of_body.insert_string("");
	}

	protected void convertObjectCreation(Message msg, StructuredEvent idlEvent) {
		CorbaManageElementTool corbaManageElementTool=new CorbaManageElementTool();
		try {
			idlEvent.filterable_data = new Property[4];
			
			NameAndStringValue_T[] meName = getManagedElementName((SiteInst) msg.getMsgBody());
			idlEvent.filterable_data[0] = filterable_objectName(meName);
			idlEvent.filterable_data[1] = filterable_objectType(getObjectType());
			idlEvent.filterable_data[2] = filterable_emsTime();
			idlEvent.filterable_data[3] = filterable_objectTypeQualifier("");
			idlEvent.remainder_of_body = CorbaServer.getInstanse().createAny();
			ManagedElement_T idlMe = new ManagedElement_T();
			corbaManageElementTool.convertManagedElementDATAX2Corba((SiteInst) msg.getMsgBody(), idlMe);
			ManagedElement_THelper.insert(idlEvent.remainder_of_body, idlMe);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private NameAndStringValue_T[] getManagedElementName(SiteInst site) {
		NameAndStringValue_T[] meName = new NameAndStringValue_T[2];
		meName[0] = new NameAndStringValue_T();
		meName[0].name = new String("EMS");
		meName[0].value = CorbaConfig.getInstanse().getCorbaEmsName();
		meName[1] = new NameAndStringValue_T();
		meName[1].name = "ManagedElement";
		meName[1].value = String.valueOf(site.getSite_Inst_Id());

		return meName;
	}
	
	/**
	 * 网元修改时获得网元名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private NameAndStringValue_T[] getManagedElementNameChg(Message msg) {
		NameAndStringValue_T[] meName = new NameAndStringValue_T[2];
		meName[0] = new NameAndStringValue_T();
		meName[0].name = new String("EMS");
		meName[0].value = CorbaConfig.getInstanse().getCorbaEmsName();
		meName[1] = new NameAndStringValue_T();
		meName[1].name = "ManagedElement";
		meName[1].value = String.valueOf(((Map<String, String>)msg.getMsgBody()).get("id"));
		return meName;
	}

}
