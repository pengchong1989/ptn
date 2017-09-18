package com.nms.db.bean.ptn.clock;


import com.nms.ui.frame.ViewDataObj;

public class FrequencyStatusInfo extends ViewDataObj{
	/**
	 * 时钟基本信息
	 */
	private static final long serialVersionUID = 4124909743428593695L;
	private int id;
	private int clockStatus;//时钟工作状态
	private int outclockStatus;//外时钟输出状态
	private int outclockType;//外时钟输出类型
	private int outclockMode;//外时钟输出方式
	private int qlInSa;//输入源sa选择
	private int qlOutSa;//输出源sa选择
	private int qlEnable;//ql使能状态
	private int lockSource;//锁定源
	private int lockSourceS1;//锁定源值s1
	private int lockSourceType;//锁定源值类型
	private int lockSourceStatus;//锁定源值状态
	private int ssmOut;//ssm外时钟门限
	private int ssmSystem;//ssm系统时钟门限
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getClockStatus() {
		return clockStatus;
	}
	public void setClockStatus(int clockStatus) {
		this.clockStatus = clockStatus;
	}
	public int getOutclockStatus() {
		return outclockStatus;
	}
	public void setOutclockStatus(int outclockStatus) {
		this.outclockStatus = outclockStatus;
	}
	public int getOutclockType() {
		return outclockType;
	}
	public void setOutclockType(int outclockType) {
		this.outclockType = outclockType;
	}
	public int getOutclockMode() {
		return outclockMode;
	}
	public void setOutclockMode(int outclockMode) {
		this.outclockMode = outclockMode;
	}
	public int getQlEnable() {
		return qlEnable;
	}
	public void setQlEnable(int qlEnable) {
		this.qlEnable = qlEnable;
	}
	public int getLockSource() {
		return lockSource;
	}
	public void setLockSource(int lockSource) {
		this.lockSource = lockSource;
	}
	public int getLockSourceS1() {
		return lockSourceS1;
	}
	public void setLockSourceS1(int lockSourceS1) {
		this.lockSourceS1 = lockSourceS1;
	}
	public int getLockSourceType() {
		return lockSourceType;
	}
	public void setLockSourceType(int lockSourceType) {
		this.lockSourceType = lockSourceType;
	}
	public int getLockSourceStatus() {
		return lockSourceStatus;
	}
	public void setLockSourceStatus(int lockSourceStatus) {
		this.lockSourceStatus = lockSourceStatus;
	}
	public int getQlInSa() {
		return qlInSa;
	}
	public void setQlInSa(int qlInSa) {
		this.qlInSa = qlInSa;
	}
	public int getQlOutSa() {
		return qlOutSa;
	}
	public void setQlOutSa(int qlOutSa) {
		this.qlOutSa = qlOutSa;
	}
	public int getSsmOut() {
		return ssmOut;
	}
	public void setSsmOut(int ssmOut) {
		this.ssmOut = ssmOut;
	}
	public int getSsmSystem() {
		return ssmSystem;
	}
	public void setSsmSystem(int ssmSystem) {
		this.ssmSystem = ssmSystem;
	}
	@Override
	public void putObjectProperty() {
		
		
	}
	
}
