package com.nms.db.bean.ptn;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class PtpBasicStatus extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8938720721107597590L;
	private int ptpModel;//ptp模式
	private int clockModel;//时钟模型	
	private int SlaveOnlyModel;//Slave_Only模式	
	private int inCompensation;//输入时延补偿属性	
	private int inDelay;//输入时延补偿值
	private int outCompensation;  //输出时延补偿属性
	private int outDelay;    //输出时延补偿值
	private int TimeInfoIt;  //时间信息接口
	private int syncFreq;  //Sync报文发包频率
	private int delayFreq; //Delay_Req报文发包频率
	private int announceFreq;  //Announce报文发包频率
	private int delayOver;    //Delay_Req 报文超时系数
	private int announceOver;  //Announce报文超时系数
	private int domainNumbe;   //DomainNumber
	private int srcclockModel;//本地源时钟模式
	private int srcclockId;//本地源时钟ID
	private int srcclocktype;//本地源时钟类型
	private int srcclockpri1; //本地源时钟优先级1
	private int srcclockpri2; //本地源时钟优先级2
	private int srcclockLevel;//本地源时钟等级
	private int srcclockaccuray;//本地源时钟精度
    private int ptpSecond;//本地ptp秒   
	private String localTime;//本地源时钟时间
	private int syncSrcclockId;//同步源时钟ID
	private int masterId;//Master端口ID
	private int slaveId;//Slave端口ID
	private int syncSrcclocktype;//同步源时钟类型	
	private int syncSrcclockpri1; //同步源时钟优先级1
	private int syncSrcclockpri2; //同步源时钟优先级2
	private int syncSrcclockLevel;//同步源时钟等级
	private int syncSrcclockaccuray;//同步源时钟精度
    private int syncSourcePtpSecond;//同步源时钟ptp秒   
	private String syncSourceTime;//同步源时钟时间
    private int syncPtpSecond;//同步时间ptp秒   
	private String syncTime;//同步时间
	private int timeSkewAttribute;//时间偏移属性
	private int timeSkew;//时间偏移量
	
	
	public int getPtpModel() {
		return ptpModel;
	}
	
	public void setPtpModel(int ptpModel) {
		this.ptpModel = ptpModel;
	}
	
	public int getClockModel() {
		return clockModel;
	}
	
	public void setClockModel(int clockModel) {
		this.clockModel = clockModel;
	}
	public int getSlaveOnlyModel() {
		return SlaveOnlyModel;
	}

	public void setSlaveOnlyModel(int slaveOnlyModel) {
		SlaveOnlyModel = slaveOnlyModel;
	}

	public int getInCompensation() {
		return inCompensation;
	}

	public void setInCompensation(int inCompensation) {
		this.inCompensation = inCompensation;
	}
	
	public int getInDelay() {
		return inDelay;
	}

	public void setInDelay(int inDelay) {
		this.inDelay = inDelay;
	}

	public int getOutCompensation() {
		return outCompensation;
	}

	public void setOutCompensation(int outCompensation) {
		this.outCompensation = outCompensation;
	}

	public int getOutDelay() {
		return outDelay;
	}

	public void setOutDelay(int outDelay) {
		this.outDelay = outDelay;
	}

	public int getTimeInfoIt() {
		return TimeInfoIt;
	}

	public void setTimeInfoIt(int timeInfoIt) {
		TimeInfoIt = timeInfoIt;
	}

	public int getSyncFreq() {
		return syncFreq;
	}

	public void setSyncFreq(int syncFreq) {
		this.syncFreq = syncFreq;
	}

	public int getDelayFreq() {
		return delayFreq;
	}

	public void setDelayFreq(int delayFreq) {
		this.delayFreq = delayFreq;
	}

	public int getAnnounceFreq() {
		return announceFreq;
	}

	public void setAnnounceFreq(int announceFreq) {
		this.announceFreq = announceFreq;
	}

	public int getDelayOver() {
		return delayOver;
	}

	public void setDelayOver(int delayOver) {
		this.delayOver = delayOver;
	}

	public int getAnnounceOver() {
		return announceOver;
	}

	public void setAnnounceOver(int announceOver) {
		this.announceOver = announceOver;
	}

	public int getDomainNumbe() {
		return domainNumbe;
	}

	public void setDomainNumbe(int domainNumbe) {
		this.domainNumbe = domainNumbe;
	}

	public int getSrcclockModel() {
		return srcclockModel;
	}

	public void setSrcclockModel(int srcclockModel) {
		this.srcclockModel = srcclockModel;
	}

	public int getSrcclockId() {
		return srcclockId;
	}

	public void setSrcclockId(int srcclockId) {
		this.srcclockId = srcclockId;
	}
	
	public int getSrcclocktype() {
		return srcclocktype;
	}

	public void setSrcclocktype(int srcclocktype) {
		this.srcclocktype = srcclocktype;
	}

	public int getSrcclockpri1() {
		return srcclockpri1;
	}

	public void setSrcclockpri1(int srcclockpri1) {
		this.srcclockpri1 = srcclockpri1;
	}

	public int getSrcclockpri2() {
		return srcclockpri2;
	}

	public void setSrcclockpri2(int srcclockpri2) {
		this.srcclockpri2 = srcclockpri2;
	}

	public int getSrcclockLevel() {
		return srcclockLevel;
	}

	public void setSrcclockLevel(int srcclockLevel) {
		this.srcclockLevel = srcclockLevel;
	}

	public int getSrcclockaccuray() {
		return srcclockaccuray;
	}

	public void setSrcclockaccuray(int srcclockaccuray) {
		this.srcclockaccuray = srcclockaccuray;
	}
	
	public int getPtpSecond() {
		return ptpSecond;
	}

	public void setPtpSecond(int ptpSecond) {
		this.ptpSecond = ptpSecond;
	}

	public String getLocalTime() {
		return localTime;
	}

	public void setLocalTime(String localTime) {
		this.localTime = localTime;
	}

	public int getSyncSrcclockId() {
		return syncSrcclockId;
	}

	public void setSyncSrcclockId(int syncSrcclockId) {
		this.syncSrcclockId = syncSrcclockId;
	}

	public int getMasterId() {
		return masterId;
	}

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public int getSlaveId() {
		return slaveId;
	}

	public void setSlaveId(int slaveId) {
		this.slaveId = slaveId;
	}

	public int getSyncSrcclocktype() {
		return syncSrcclocktype;
	}

	public void setSyncSrcclocktype(int syncSrcclocktype) {
		this.syncSrcclocktype = syncSrcclocktype;
	}

	public int getSyncSrcclockpri1() {
		return syncSrcclockpri1;
	}

	public void setSyncSrcclockpri1(int syncSrcclockpri1) {
		this.syncSrcclockpri1 = syncSrcclockpri1;
	}

	public int getSyncSrcclockpri2() {
		return syncSrcclockpri2;
	}

	public void setSyncSrcclockpri2(int syncSrcclockpri2) {
		this.syncSrcclockpri2 = syncSrcclockpri2;
	}

	public int getSyncSrcclockLevel() {
		return syncSrcclockLevel;
	}

	public void setSyncSrcclockLevel(int syncSrcclockLevel) {
		this.syncSrcclockLevel = syncSrcclockLevel;
	}

	public int getSyncSrcclockaccuray() {
		return syncSrcclockaccuray;
	}

	public void setSyncSrcclockaccuray(int syncSrcclockaccuray) {
		this.syncSrcclockaccuray = syncSrcclockaccuray;
	}

	public int getSyncPtpSecond() {
		return syncPtpSecond;
	}

	public void setSyncPtpSecond(int syncPtpSecond) {
		this.syncPtpSecond = syncPtpSecond;
	}

	public String getSyncTime() {
		return syncTime;
	}

	public void setSyncTime(String syncTime) {
		this.syncTime = syncTime;
	}

	public int getTimeSkewAttribute() {
		return timeSkewAttribute;
	}

	public void setTimeSkewAttribute(int timeSkewAttribute) {
		this.timeSkewAttribute = timeSkewAttribute;
	}

	public int getTimeSkew() {
		return timeSkew;
	}

	public void setTimeSkew(int timeSkew) {
		this.timeSkew = timeSkew;
	}

	public String getSyncSourceTime() {
		return syncSourceTime;
	}
	
	public void setSyncSourceTime(String syncSourceTime) {
		this.syncSourceTime = syncSourceTime;
	}
	public int getSyncSourcePtpSecond() {
		return syncSourcePtpSecond;
	}

	public void setSyncSourcePtpSecond(int syncSourcePtpSecond) {
		this.syncSourcePtpSecond = syncSourcePtpSecond;
	}

	@Override
	public void putObjectProperty() {

		try {
			this.putClientProperty("clockModel", (UiUtil.getCodeByValue("clockModel", this.getClockModel()+"")).getCodeName());
			this.putClientProperty("SlaveOnlyModel", this.getInCompensation()==0?"Disable":"Enable");
		    this.putClientProperty("inCompensation", this.getInCompensation()==0?"+":"-");
		    this.putClientProperty("inDelay",this.getInDelay());
		    this.putClientProperty("outCompensation", this.getOutCompensation()==0?"+":"-");
		    this.putClientProperty("outDelay",this.getOutDelay());
		    this.putClientProperty("TimeInfoIt", (UiUtil.getCodeByValue("TimeInfoIt", this.getTimeInfoIt()+"")).getCodeName());
		    this.putClientProperty("syncFreq", (UiUtil.getCodeByValue("SYNCSENDFREC", this.getSyncFreq()+"")).getCodeName());
		    this.putClientProperty("delayFreq", (UiUtil.getCodeByValue("DELAYSENDFREC", this.getDelayFreq()+"")).getCodeName());
		    this.putClientProperty("announceFreq", (UiUtil.getCodeByValue("DELAYSENDFREC", this.getAnnounceFreq()+"")).getCodeName());
		    this.putClientProperty("delayOver",this.getDelayOver());
		    this.putClientProperty("announceOver",this.getAnnounceOver());
		    this.putClientProperty("domainNumbe",this.getDomainNumbe());
		    if(getPtpModel()==0){
			   this.putClientProperty("ptpModel", "Disable");
		    }else if(getPtpModel()==1){
			   this.putClientProperty("ptpModel", "BMC");
		    }else if(getPtpModel()==2){
			   this.putClientProperty("ptpModel", "Manual");
		    }		
		    this.putClientProperty("timeSkew",this.getTimeSkew());
		    this.putClientProperty("timeSkewAttribute", this.getTimeSkewAttribute()==0?"+":"-");
		    this.putClientProperty("syncTime", this.getSyncTime());
		    this.putClientProperty("syncPtpSecond",this.getSyncPtpSecond());	
		    this.putClientProperty("syncSourceTime", this.getSyncSourceTime());
		    this.putClientProperty("syncSourcePtpSecond", this.getSyncSourcePtpSecond());		
		    this.putClientProperty("syncSrcclockaccuray", (UiUtil.getCodeByValue("srcclockaccuray", Integer.toHexString(this.getSyncSrcclockaccuray())+"")).getCodeName());
		    this.putClientProperty("syncSrcclockLevel", this.getSyncSrcclockLevel());
		    this.putClientProperty("syncSrcclockpri1", this.getSyncSrcclockpri1());
		    this.putClientProperty("syncSrcclockpri2", this.getSyncSrcclockpri2());
		    this.putClientProperty("syncSrcclocktype", (UiUtil.getCodeByValue("srcclockType", Integer.toHexString(this.getSyncSrcclocktype())+"")).getCodeName());
		    this.putClientProperty("slaveId", Integer.toHexString(this.getSlaveId()));
		    this.putClientProperty("masterId", Integer.toHexString(this.getMasterId()));
		    this.putClientProperty("syncSrcclockId", Integer.toHexString(this.getSyncSrcclockId()));
		    this.putClientProperty("localTime", this.getLocalTime());
		    this.putClientProperty("ptpSecond", this.getPtpSecond());
		    this.putClientProperty("srcclockaccuray", (UiUtil.getCodeByValue("srcclockaccuray", Integer.toHexString(this.getSrcclockaccuray())+"")).getCodeName());
		    this.putClientProperty("srcclockLevel", this.getSrcclockLevel());
		    this.putClientProperty("srcclockpri1", this.getSrcclockpri1());
		    this.putClientProperty("srcclockpri2", this.getSrcclockpri2());
		    this.putClientProperty("srcclocktype", (UiUtil.getCodeByValue("srcclockType", Integer.toHexString(this.getSrcclocktype())+"")).getCodeName());
		    this.putClientProperty("srcclockId", Integer.toHexString(this.getSrcclockId()));
		    this.putClientProperty("srcclockModel", this.getTimeSkewAttribute()==0?"Disable":"Enable");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
				
	}
	
	
}
