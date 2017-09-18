package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 时间同步配置块(1CH)
 * 
 * @author hulei
 * 
 */
public class TimeSynchronizeObject {
	private int ptpPattern = 0; // "PTP模式" value="0"
	private int clockModel = 0; // 时钟模型" value="1"
	private int sourcesClockModel = 0; // 源时钟模式" value="0"
	private String sourcesClockId = "255,255,255,255,255,255,255,255"; // 源时钟ID" 源时钟ID： 0-FFFFFFFFFFFFFFFFH (界面十六进制输入)
	private int sourcesClockType = 0; // 源时钟类型" value="0"
	private int sourcesClockPriority_1 = 255;// 源时钟优先级1" value="255"
	private int sourcesClockPriority_2 = 255; // 源时钟优先级2" value="255"
	private int sourcesClockGrade = 0; // 源时钟等级" value="255"
	private int sourcesClockPrecision = 0; // 源时钟精度" value="20"
	private int reserve_1 = 0; // 备用1" value="0"
	private int slaveOnlyPattern = 0; // Slave_Only模式" value="0"
	private int inputTimeDelayOffsetAttribute = 0; // (输入时延补偿)补偿属性" value="0"
	private int inputTimeDelayOffsetValue = 0;// (输入时延补偿)时延补偿值" value="0"
	private int outputTimeDelayOffsetAttribute = 0; // (输出时延补偿)补偿属性" value="0"
	private int outputTimeDelayOffsetValue = 0; // (输出时延补偿)时延补偿值" value="0"
	private int synchronizePeriod = 0; // 同步周期" value="0"
	private int announcePeriod = 0; // 通告周期" value="0"
	private int timeInformationPort = 0; // 时间信息接口" value="0"
	private int reserve_2 = 0; // 备用" value="0"
	private int ptpPortCount = 0; // PTP端口配置条目数" value="0"
	private List<PTPPortObject> ptpPortList = new ArrayList<PTPPortObject>();

	public int getPtpPattern() {
		return ptpPattern;
	}

	public void setPtpPattern(int ptpPattern) {
		this.ptpPattern = ptpPattern;
	}

	public int getClockModel() {
		return clockModel;
	}

	public void setClockModel(int clockModel) {
		this.clockModel = clockModel;
	}

	public int getSourcesClockModel() {
		return sourcesClockModel;
	}

	public void setSourcesClockModel(int sourcesClockModel) {
		this.sourcesClockModel = sourcesClockModel;
	}

	public String getSourcesClockId() {
		return sourcesClockId;
	}

	public void setSourcesClockId(String sourcesClockId) {
		this.sourcesClockId = sourcesClockId;
	}

	public int getSourcesClockType() {
		return sourcesClockType;
	}

	public void setSourcesClockType(int sourcesClockType) {
		this.sourcesClockType = sourcesClockType;
	}

	public int getSourcesClockPriority_1() {
		return sourcesClockPriority_1;
	}

	public void setSourcesClockPriority_1(int sourcesClockPriority_1) {
		this.sourcesClockPriority_1 = sourcesClockPriority_1;
	}

	public int getSourcesClockPriority_2() {
		return sourcesClockPriority_2;
	}

	public void setSourcesClockPriority_2(int sourcesClockPriority_2) {
		this.sourcesClockPriority_2 = sourcesClockPriority_2;
	}

	public int getSourcesClockGrade() {
		return sourcesClockGrade;
	}

	public void setSourcesClockGrade(int sourcesClockGrade) {
		this.sourcesClockGrade = sourcesClockGrade;
	}

	public int getSourcesClockPrecision() {
		return sourcesClockPrecision;
	}

	public void setSourcesClockPrecision(int sourcesClockPrecision) {
		this.sourcesClockPrecision = sourcesClockPrecision;
	}

	public int getReserve_1() {
		return reserve_1;
	}

	public void setReserve_1(int reserve_1) {
		this.reserve_1 = reserve_1;
	}

	public int getSlaveOnlyPattern() {
		return slaveOnlyPattern;
	}

	public void setSlaveOnlyPattern(int slaveOnlyPattern) {
		this.slaveOnlyPattern = slaveOnlyPattern;
	}

	public int getInputTimeDelayOffsetAttribute() {
		return inputTimeDelayOffsetAttribute;
	}

	public void setInputTimeDelayOffsetAttribute(int inputTimeDelayOffsetAttribute) {
		this.inputTimeDelayOffsetAttribute = inputTimeDelayOffsetAttribute;
	}

	public int getInputTimeDelayOffsetValue() {
		return inputTimeDelayOffsetValue;
	}

	public void setInputTimeDelayOffsetValue(int inputTimeDelayOffsetValue) {
		this.inputTimeDelayOffsetValue = inputTimeDelayOffsetValue;
	}

	public int getOutputTimeDelayOffsetAttribute() {
		return outputTimeDelayOffsetAttribute;
	}

	public void setOutputTimeDelayOffsetAttribute(int outputTimeDelayOffsetAttribute) {
		this.outputTimeDelayOffsetAttribute = outputTimeDelayOffsetAttribute;
	}

	public int getOutputTimeDelayOffsetValue() {
		return outputTimeDelayOffsetValue;
	}

	public void setOutputTimeDelayOffsetValue(int outputTimeDelayOffsetValue) {
		this.outputTimeDelayOffsetValue = outputTimeDelayOffsetValue;
	}

	public int getSynchronizePeriod() {
		return synchronizePeriod;
	}

	public void setSynchronizePeriod(int synchronizePeriod) {
		this.synchronizePeriod = synchronizePeriod;
	}

	public int getAnnouncePeriod() {
		return announcePeriod;
	}

	public void setAnnouncePeriod(int announcePeriod) {
		this.announcePeriod = announcePeriod;
	}

	public int getTimeInformationPort() {
		return timeInformationPort;
	}

	public void setTimeInformationPort(int timeInformationPort) {
		this.timeInformationPort = timeInformationPort;
	}

	public int getReserve_2() {
		return reserve_2;
	}

	public void setReserve_2(int reserve_2) {
		this.reserve_2 = reserve_2;
	}

	public List<PTPPortObject> getPtpPortList() {
		return ptpPortList;
	}

	public void setPtpPortList(List<PTPPortObject> ptpPortList) {
		this.ptpPortList = ptpPortList;
	}

	public int getPtpPortCount() {
		return ptpPortCount;
	}

	public void setPtpPortCount(int ptpPortCount) {
		this.ptpPortCount = ptpPortCount;
	}

}
