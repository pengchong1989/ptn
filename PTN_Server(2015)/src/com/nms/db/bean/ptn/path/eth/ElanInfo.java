package com.nms.db.bean.ptn.path.eth;

import com.nms.db.bean.ptn.path.ServiceInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public class ElanInfo extends ServiceInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 914694620818845689L;
	private int axcId;
	private int zxcId;
	private int pwBusinessId;
	private int aAcBusinessId;
    private int role;//角色
	
	public int getAxcId() {
		return axcId;
	}

	public void setAxcId(int axcId) {
		this.axcId = axcId;
	}

	public int getZxcId() {
		return zxcId;
	}

	public void setZxcId(int zxcId) {
		this.zxcId = zxcId;
	}

	@Override
	public void putObjectProperty() {
		try {
			this.putClientProperty("id", getId());
			this.putClientProperty("elanName", getName());
			this.putClientProperty("activeStates", getActiveStatus() == 1 ? EActiveStatus.ACTIVITY.toString() : EActiveStatus.UNACTIVITY.toString());
			this.putClientProperty("creater", getCreateUser());
			this.putClientProperty("createTime", DateUtil.strDate(getCreateTime(), DateUtil.FULLTIME));
			this.putClientProperty("issingle", this.getIsSingle() == 0 ? ResourceUtil.srcStr(StringKeysObj.OBJ_NO) : ResourceUtil.srcStr(StringKeysObj.OBJ_YES));
			this.putClientProperty("jobstatus", super.getJobStatus(this.getJobStatus()));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public int getPwBusinessId() {
		return pwBusinessId;
	}

	public void setPwBusinessId(int pwBusinessId) {
		this.pwBusinessId = pwBusinessId;
	}

	public int getaAcBusinessId() {
		return aAcBusinessId;
	}

	public void setaAcBusinessId(int aAcBusinessId) {
		this.aAcBusinessId = aAcBusinessId;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
}
