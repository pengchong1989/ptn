package com.nms.db.bean.system;

import java.util.List;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.model.system.FieldService_MB;
import com.nms.model.system.NetService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class Field extends ViewDataObj{
	private static final long serialVersionUID = 1L;
	private int id;
	private String fieldName;
	private int fieldX;
	private int fieldY;
	private List<SiteInst> siteInstList;
	private int mSiteId;
	private String objectType;
	private int parentId;
	private String parentName;
	private List<String> workIP;
	private WorkIps workIps;
	private int isDeleteTopo=0;	//主拓扑刷新时，验证是否为删除动作  0=不是删除  1=删除     kk
	private int netWorkId;//最外层拓扑id
	private int groupId;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public int getFieldX() {
		return fieldX;
	}

	public void setFieldX(int fieldX) {
		this.fieldX = fieldX;
	}

	public int getFieldY() {
		return fieldY;
	}

	public void setFieldY(int fieldY) {
		this.fieldY = fieldY;
	}

	public List<SiteInst> getSiteInstList() {
		return siteInstList;
	}

	public void setSiteInstList(List<SiteInst> siteInstList) {
		this.siteInstList = siteInstList;
	}

	public int getmSiteId() {
		return mSiteId;
	}

	public void setmSiteId(int mSiteId) {
		this.mSiteId = mSiteId;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void putObjectProperty() {
		this.getClientProperties().put("id", this.getId());
		if(this.getObjectType().equals("field")){
			this.getClientProperties().put("fieldName", this.getFieldName());
			this.getClientProperties().put("groupId", this.getGroupId());
			this.getClientProperties().put("parentName", getNetName(this.getId()));
		}else{
			this.getClientProperties().put("subName", this.getFieldName());
			this.getClientProperties().put("groupName", this.getSubFieldName(this.getParentId()));
			this.getClientProperties().put("parentName", getNetName(this.getParentId()));
		}
	}
	
	private String getSubFieldName(int fieldId){
		String str = "";
		FieldService_MB fieldService = null;
		try {
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			Field field = fieldService.selectByFieldId(fieldId).get(0);
			str = field.getFieldName();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(fieldService);
		}
		return str;
	}
	private String getNetName(int fieldId){
		String str = "";
		FieldService_MB fieldService = null;
		NetService_MB netService = null;
		try {
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			netService = (NetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.NETWORKSERVICE);
			Field field = fieldService.selectByFieldId(fieldId).get(0);
			NetWork netWork = new NetWork();
			netWork.setNetWorkId(field.getNetWorkId());
			netWork = netService.select(netWork).get(0);
			str = netWork.getNetWorkName();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(fieldService);
			UiUtil.closeService_MB(netService);
		}
		return str;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public List<String> getWorkIP() {
		return workIP;
	}

	public void setWorkIP(List<String> workIP) {
		this.workIP = workIP;
	}

	public WorkIps getWorkIps() {
		return workIps;
	}

	public void setWorkIps(WorkIps workIps) {
		this.workIps = workIps;
	}

	public int getIsDeleteTopo() {
		return isDeleteTopo;
	}

	public void setIsDeleteTopo(int isDeleteTopo) {
		this.isDeleteTopo = isDeleteTopo;
	}

	public int getNetWorkId() {
		return netWorkId;
	}

	public void setNetWorkId(int netWorkId) {
		this.netWorkId = netWorkId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	
}
