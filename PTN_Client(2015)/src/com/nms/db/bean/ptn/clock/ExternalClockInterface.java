package com.nms.db.bean.ptn.clock;

import java.io.Serializable;

import com.nms.db.enums.EActiveStatus;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class ExternalClockInterface extends ViewDataObj implements Serializable{

	private static final long serialVersionUID = 9196025030228039887L;

	private int id;
	private int siteId;//关联ID
	private String interfaceName;//接口名
	private int managingStatus;//管理状态
	private String workingStatus;//工作状态
	private int interfaceMode;//接口模式
	private int inputImpedance;//输入阻抗(Ω)
	private int sanBits;//SAN比特
	private String timeOffsetCompensation;//时间偏移补偿(ns)
	private int activeStatus;//激活状态
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getSiteId() {
		return siteId;
	}


	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}


	public String getInterfaceName() {
		return interfaceName;
	}


	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}


	public int getManagingStatus() {
		return managingStatus;
	}


	public void setManagingStatus(int managingStatus) {
		this.managingStatus = managingStatus;
	}


	public String getWorkingStatus() {
		return workingStatus;
	}


	public void setWorkingStatus(String workingStatus) {
		this.workingStatus = workingStatus;
	}


	public int getInterfaceMode() {
		return interfaceMode;
	}


	public void setInterfaceMode(int interfaceMode) {
		this.interfaceMode = interfaceMode;
	}

	public int getInputImpedance() {
		return inputImpedance;
	}

	public void setInputImpedance(int inputImpedance) {
		this.inputImpedance = inputImpedance;
	}


	public int getSanBits() {
		return sanBits;
	}


	public void setSanBits(int sanBits) {
		this.sanBits = sanBits;
	}


	public String getTimeOffsetCompensation() {
		return timeOffsetCompensation;
	}


	public void setTimeOffsetCompensation(String timeOffsetCompensation) {
		this.timeOffsetCompensation = timeOffsetCompensation;
	}


	@Override
	public void putObjectProperty() {
		
		try {
			this.putClientProperty("id", this.getId());
			this.putClientProperty("siteId", this.getSiteId());
			this.putClientProperty("interfaceName", this.getInterfaceName());
		    this.putClientProperty("managingStatus", UiUtil.getCodeById(this.getManagingStatus()).getCodeName());
		   // this.putClientProperty("workingStatus", UiUtil.getCodeById(Integer.parseInt(this.getWorkingStatus())).getCodeName());
		    this.putClientProperty("workingStatus", super.getJobStatus(this.getWorkingStatus()));
//			this.putClientProperty("port", UiUtil.getCodeById(this.getPort()).getCodeName());		
			this.putClientProperty("interfaceMode", UiUtil.getCodeById(this.getInterfaceMode()).getCodeName());		
			if(this.getInputImpedance()==0){
				this.putClientProperty("inputImpedance","-");
			}else{
				this.putClientProperty("inputImpedance", UiUtil.getCodeById(this.getInputImpedance()).getCodeName());	
			}
			if(this.getSanBits()==0){
				this.putClientProperty("sanBits","-");		
			}else{
				this.putClientProperty("sanBits", UiUtil.getCodeById(this.getSanBits()).getCodeName());		
			}
			if(null==this.getTimeOffsetCompensation()){
				this.putClientProperty("timeOffsetCompensation","-");		
			}else{
				this.putClientProperty("timeOffsetCompensation", this.getTimeOffsetCompensation());		
			}
			this.putClientProperty("activeStatus", activeStatus==EActiveStatus.ACTIVITY.getValue()?true:false);		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}		
	}
	public int getActiveStatus() {
		return activeStatus;
	}


	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}


	

}
