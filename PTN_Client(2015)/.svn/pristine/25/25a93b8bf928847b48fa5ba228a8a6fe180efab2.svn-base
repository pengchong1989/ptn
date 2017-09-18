package com.nms.db.bean.equipment.port;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public class PortStm extends ViewDataObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6614597764160752707L;
	private int id;
	private int portid;
	private int jobwavelength;
	private String sfpexpect;
	private String sfpreality;
	private String sfpvender;
	private int status;
	private String type;
	private String name;
	private String Jobstatus;
	private int siteid;

	public PortStm(){
		try {
			this.setSfpexpect(UiUtil.getCodeByValue("sfptype", "1").getId()+"");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPortid() {
		return portid;
	}

	public void setPortid(int portid) {
		this.portid = portid;
	}

	public int getJobwavelength() {
		return jobwavelength;
	}

	public void setJobwavelength(int jobwavelength) {
		this.jobwavelength = jobwavelength;
	}

	public String getSfpexpect() {
		return sfpexpect;
	}

	public void setSfpexpect(String sfpexpect) {
		this.sfpexpect = sfpexpect;
	}

	public String getSfpreality() {
		return sfpreality;
	}

	public void setSfpreality(String sfpreality) {
		this.sfpreality = sfpreality;
	}

	public String getSfpvender() {
		return sfpvender;
	}

	public void setSfpvender(String sfpvender) {
		this.sfpvender = sfpvender;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getJobstatus() {
		return Jobstatus;
	}

	public void setJobstatus(String jobstatus) {
		Jobstatus = jobstatus;
	}

	public int getSiteid() {
		return siteid;
	}

	public void setSiteid(int siteid) {
		this.siteid = siteid;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		String status = null;
		try {
			getClientProperties().put("id", getId());
			getClientProperties().put("portname", this.getName());
			if (this.getStatus() == 0) {
				status = ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO);
			} else {
				status = ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED);
			}
			getClientProperties().put("adminstatus", status);
			getClientProperties().put("type", this.getType());
			if(!"".equals(this.getJobstatus())){
				getClientProperties().put("jobstatus", super.getJobStatus(this.getJobstatus()));
			}
			getClientProperties().put("jobwavelength", this.getJobwavelength());
//			getClientProperties().put("sfpexpect", UiUtil.getCodeById(Integer.parseInt(this.getSfpexpect())).getCodeName());
			getClientProperties().put("sfpreality", this.getSfpreality());
			getClientProperties().put("sfpvender", this.getSfpvender());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			status = null;
		}
	}
}
