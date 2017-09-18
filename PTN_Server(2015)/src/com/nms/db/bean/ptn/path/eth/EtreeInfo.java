package com.nms.db.bean.ptn.path.eth;

import java.util.List;

import com.nms.db.bean.ptn.path.ServiceInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public class EtreeInfo extends ServiceInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4085185658317652996L;
	private int aXcId;//根节点 业务ID
	private int zXcId;//叶子节点业务ID
	private int rootSite;
	private int branchSite;
	private List<AcPortInfo> beforeRootAc=null;	//修改之前的根AC对象
	private List<AcPortInfo> beforeBranchAc=null;	//修改之前的叶子AC对象

	public int getRootSite() {
		return rootSite;
	}

	public void setRootSite(int rootSite) {
		this.rootSite = rootSite;
	}

	public int getBranchSite() {
		return branchSite;
	}

	public void setBranchSite(int branchSite) {
		this.branchSite = branchSite;
	}

	public int getaXcId() {
		return aXcId;
	}

	public void setaXcId(int aXcId) {
		this.aXcId = aXcId;
	}

	public int getzXcId() {
		return zXcId;
	}

	public void setzXcId(int zXcId) {
		this.zXcId = zXcId;
	}
	
	@Override
	public void putObjectProperty() {
		try {
			this.putClientProperty("id", getId());
			this.putClientProperty("etreeName", getName());
			this.putClientProperty("activeStates", getActiveStatus() == 1 ? EActiveStatus.ACTIVITY.toString() : EActiveStatus.UNACTIVITY.toString());
			this.putClientProperty("creater", getCreateUser());
			this.putClientProperty("createTime", DateUtil.strDate(getCreateTime(), DateUtil.FULLTIME));
			this.putClientProperty("issingle", this.getIsSingle()==0 ? ResourceUtil.srcStr(StringKeysObj.OBJ_NO) :ResourceUtil.srcStr(StringKeysObj.OBJ_YES));
			this.putClientProperty("jobstatus", super.getJobStatus(this.getJobStatus()));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public List<AcPortInfo> getBeforeRootAc() {
		return beforeRootAc;
	}

	public void setBeforeRootAc(List<AcPortInfo> beforeRootAc) {
		this.beforeRootAc = beforeRootAc;
	}

	public List<AcPortInfo> getBeforeBranchAc() {
		return beforeBranchAc;
	}

	public void setBeforeBranchAc(List<AcPortInfo> beforeBranchAc) {
		this.beforeBranchAc = beforeBranchAc;
	}

}
