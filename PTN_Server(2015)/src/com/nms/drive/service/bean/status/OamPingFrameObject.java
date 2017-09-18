package com.nms.drive.service.bean.status;


public class OamPingFrameObject{


	private int id;
	private int frameId;
	private int status;
	private String farMac;
	private int bytes;
	private int time;
	private int idNumer;
	
	public int getFrameId() {
		return frameId;
	}

	public void setFrameId(int frameId) {
		this.frameId = frameId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFarMac() {
		return farMac;
	}

	public void setFarMac(String farMac) {
		this.farMac = farMac;
	}

	public int getBytes() {
		return bytes;
	}

	public void setBytes(int bytes) {
		this.bytes = bytes;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdNumer() {
		return idNumer;
	}

	public void setIdNumer(int idNumer) {
		this.idNumer = idNumer;
	}

}
