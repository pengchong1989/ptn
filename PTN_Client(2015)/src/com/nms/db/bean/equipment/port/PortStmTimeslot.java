package com.nms.db.bean.equipment.port;

import com.nms.db.enums.ECheckStatus;
import com.nms.db.enums.EManagerStatus;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class PortStmTimeslot extends ViewDataObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8916587367601061651L;
	private int id;
	private int siteId;
	private int portid;
	private int portstmid;
	private String timeslotnumber;
	private String timeslotname;
	private int managerStatus;
	private String jobstatus;
	private String expectjtwo="ptn";
	private String sendjtwo="ptn";
	private String realityjtwo;
	private int lptim;
	private String expectvfive="Async";
	private String sendvfive;
	private String realityvfive;
	private int checkvfive;
	private int isUsed;

	@Override
	public String toString(){
		return new StringBuffer().append(" id=").append(id).append(" ;siteId=").append(siteId)
		.append(" ;portid=").append(portid).append(" ;portstmid=").append(portstmid)
		.append(" ;timeslotnumber=").append(timeslotnumber).append(" ;timeslotname=").append(timeslotname)
		.append(" ;managerStatus=").append(managerStatus).append(" ;jobstatus=").append(jobstatus)
		.append(" ;expectjtwo=").append(expectjtwo).append(" ;sendjtwo=").append(sendjtwo)
		.append(" ;realityjtwo=").append(realityjtwo).append(" ;lptim=").append(lptim)
		.append(" ;expectvfive=").append(expectvfive).append(" ;sendvfive=").append(sendvfive)
		.append(" ;realityvfive=").append(realityvfive).append(" ;checkvfive=").append(checkvfive)
		.append(" ;isUsed=").append(isUsed).toString();
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

	public int getPortstmid() {
		return portstmid;
	}

	public void setPortstmid(int portstmid) {
		this.portstmid = portstmid;
	}

	public int getManagerStatus() {
		return managerStatus;
	}

	public void setManagerStatus(int managerStatus) {
		this.managerStatus = managerStatus;
	}

	public String getJobstatus() {
		return jobstatus;
	}

	public void setJobstatus(String jobstatus) {
		this.jobstatus = jobstatus;
	}

	public String getExpectjtwo() {
		return expectjtwo;
	}

	public void setExpectjtwo(String expectjtwo) {
		this.expectjtwo = expectjtwo;
	}

	public String getSendjtwo() {
		return sendjtwo;
	}

	public void setSendjtwo(String sendjtwo) {
		this.sendjtwo = sendjtwo;
	}

	public String getRealityjtwo() {
		return realityjtwo;
	}

	public void setRealityjtwo(String realityjtwo) {
		this.realityjtwo = realityjtwo;
	}

	public int getLptim() {
		return lptim;
	}

	public void setLptim(int lptim) {
		this.lptim = lptim;
	}

	public String getExpectvfive() {
		return expectvfive;
	}

	public void setExpectvfive(String expectvfive) {
		this.expectvfive = expectvfive;
	}

	public String getSendvfive() {
		return sendvfive;
	}

	public void setSendvfive(String sendvfive) {
		this.sendvfive = sendvfive;
	}

	public String getRealityvfive() {
		return realityvfive;
	}

	public void setRealityvfive(String realityvfive) {
		this.realityvfive = realityvfive;
	}

	public int getCheckvfive() {
		return checkvfive;
	}

	public void setCheckvfive(int checkvfive) {
		this.checkvfive = checkvfive;
	}

	public String getTimeslotnumber() {
		return timeslotnumber;
	}

	public void setTimeslotnumber(String timeslotnumber) {
		this.timeslotnumber = timeslotnumber;
	}

	public String getTimeslotname() {
		return timeslotname;
	}

	public void setTimeslotname(String timeslotname) {
		this.timeslotname = timeslotname;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {

		try {
			getClientProperties().put("id", getId());
			getClientProperties().put("timeslotnumber", this.getTimeslotnumber());
			getClientProperties().put("managerStatus", EManagerStatus.forms(this.getManagerStatus()));
			if(null!=this.getJobstatus() && !"".equals(this.getJobstatus())){
				getClientProperties().put("jobstatus",super.getJobStatus(this.getJobstatus()));
			}
			getClientProperties().put("expectjtwo", this.getExpectjtwo());
			getClientProperties().put("sendjtwo", this.getSendjtwo());
			getClientProperties().put("realityjtwo", this.getRealityjtwo());
			getClientProperties().put("lptim", EManagerStatus.forms(this.getLptim()));
			getClientProperties().put("expectvfive", this.getExpectvfive());
			if(null!= this.getSendvfive() && !"0".equals(this.getSendvfive())){
				getClientProperties().put("sendvfive", UiUtil.getCodeById(Integer.parseInt(this.getSendvfive())).getCodeName());
			}
			getClientProperties().put("realityvfive", this.getRealityvfive());
			getClientProperties().put("checkvfive", ECheckStatus.forms(this.getCheckvfive()));
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
		
	}

}
