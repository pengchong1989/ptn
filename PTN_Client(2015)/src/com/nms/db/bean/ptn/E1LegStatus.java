package com.nms.db.bean.ptn;

import com.nms.ui.frame.ViewDataObj;

public class E1LegStatus extends ViewDataObj{
	private static final long serialVersionUID = -5666371829266213603L;
	private int e1LegID;//支路1-4
	private int e1Status;//E1仿真板在位状态 01/00=在位/不在位
	private int e1Model;//E1仿真板模式01/00=成帧/不成帧
	private int tdmClock;//TDM时钟源00/01/02/03=2M线路恢复时钟/网络侧自适应恢复时钟/网络侧差分恢复时钟/网络侧增强型自适应恢复时钟
	private int e1LegStatus;//2M端口使用状态00/01 = 空闲/正在使用
	private int e1LegType;//2M端口类型00/01 = 光接口/电接口
	private int e1legSpeed;//2M端口速率00/01 = E1/STM-N
	private int e1Attr;//2M通道化属性：00/01=通道化/非通道化

	@Override
	public void putObjectProperty() {
		this.putClientProperty("e1LegId",getE1LegID());
		this.putClientProperty("e1Status",getE1Status()==1?"在位":"不在位");
		this.putClientProperty("e1Model",getE1Model()==1?"成帧":"不成帧");
		this.putClientProperty("tdmClock",getTdmClockStatus());
		this.putClientProperty("e1LegStatus",getE1LegStatus()==0?"空闲":"正在使用");
		this.putClientProperty("e1LegType",getE1LegType()==0?"光接口":"电接口");
		this.putClientProperty("e1legSpeed",getE1legSpeed()==0?"E1":"STM-N");
		this.putClientProperty("e1Attr",getE1Attr()==0?"通道化":"非通道化");
	}

	private String getTdmClockStatus() {
		int status = getTdmClock();
		if(status == 0){
			return "2M线路恢复时钟"; 
		}else if(status == 1){
			return "网络侧自适应恢复时钟";
		}else if(status == 2){
			return "网络侧差分恢复时钟";
		}else if(status == 3){
			return "网络侧增强型自适应恢复时钟";
		}
		return "";
	}

	public int getE1LegID() {
		return e1LegID;
	}

	public void setE1LegID(int e1LegID) {
		this.e1LegID = e1LegID;
	}

	public int getE1Status() {
		return e1Status;
	}

	public void setE1Status(int e1Status) {
		this.e1Status = e1Status;
	}

	public int getE1Model() {
		return e1Model;
	}

	public void setE1Model(int e1Model) {
		this.e1Model = e1Model;
	}

	public int getTdmClock() {
		return tdmClock;
	}

	public void setTdmClock(int tdmClock) {
		this.tdmClock = tdmClock;
	}

	public int getE1LegStatus() {
		return e1LegStatus;
	}

	public void setE1LegStatus(int e1LegStatus) {
		this.e1LegStatus = e1LegStatus;
	}

	public int getE1LegType() {
		return e1LegType;
	}

	public void setE1LegType(int e1LegType) {
		this.e1LegType = e1LegType;
	}

	public int getE1legSpeed() {
		return e1legSpeed;
	}

	public void setE1legSpeed(int e1legSpeed) {
		this.e1legSpeed = e1legSpeed;
	}

	public int getE1Attr() {
		return e1Attr;
	}

	public void setE1Attr(int e1Attr) {
		this.e1Attr = e1Attr;
	}
	
}
