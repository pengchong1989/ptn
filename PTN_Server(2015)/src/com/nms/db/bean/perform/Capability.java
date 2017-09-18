package com.nms.db.bean.perform;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ResourceUtil;


@SuppressWarnings("serial")
public class Capability extends ViewDataObj{
	private int id;
	
	private String capabilitytype;
	private String capabilityname;
	private String capabilitydesc;	
	private String capabilitydisp;
	private String capabilitydesc_en;
	private int capabilitycode;
	private String capabilitycounter;
	private String UnitName;//性能值的单位
	private int manufacturer;  //1表示武汉，2表示晨晓
	
	@Override
	public String toString(){
		return new StringBuffer()
		.append("id=")
		.append(id)
		
		.append(";capabilitytype=")
		.append(capabilitytype)
		
		
		.append(";capabilityname=")
		.append(capabilityname)
		
		.append(";capabilitydesc=")
		.append(capabilitydesc)
		
		.append(";capabilitydesc_en=")
		.append(capabilitydesc_en)
		
		.append(";capabilitydisp=")
		.append(capabilitydisp)
		
		.append(";capabilitycode=")
		.append(capabilitycode)
		
		.append(";capabilitycounter=")
		.append(capabilitycounter)
		
		.append(";UnitName=").append(manufacturer)
		.append(";manufacturer=").append(UnitName)
		.toString();
		
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCapabilitydesc_en() {
		return capabilitydesc_en;
	}

	public void setCapabilitydesc_en(String capabilitydesc_en) {
		this.capabilitydesc_en = capabilitydesc_en;
	}

	public String getCapabilitytype() {
		return capabilitytype;
	}

	public void setCapabilitytype(String capabilitytype) {
		this.capabilitytype = capabilitytype;
	}

	public String getCapabilitydesc() {
		return capabilitydesc;
	}

	public void setCapabilitydesc(String capabilitydescribe) {
		this.capabilitydesc = capabilitydescribe;
	}

	public String getCapabilitydisp() {
		return capabilitydisp;
	}

	public void setCapabilitydisp(String capabilitydisp) {
		this.capabilitydisp = capabilitydisp;
	}
	/*
	public String getCapabilityaffect() {
		return capabilityaffect;
	}

	public void setCapabilityaffect(String capabilityaffect) {
		this.capabilityaffect = capabilityaffect;
	}

	public String getCapabilitycause() {
		return capabilitycause;
	}

	public void setCapabilitycause(String capabilitycause) {
		this.capabilitycause = capabilitycause;
	}

	public String getCapabilityobject() {
		return capabilityobject;
	}

	public void setCapabilityobject(String capabilityobject) {
		this.capabilityobject = capabilityobject;
	}

	public String getCapabilityremark() {
		return capabilityremark;
	}

	public void setCapabilityremark(String capabilityremark) {
		this.capabilityremark = capabilityremark;
	}
	*/
	
	public String getCapabilityname() {
		return capabilityname;
	}

	public void setCapabilityname(String capabilityname) {
		this.capabilityname = capabilityname;
	}

	

	public int getCapabilitycode() {
		return capabilitycode;
	}

	public void setCapabilitycode(int capabilitycode) {
		this.capabilitycode = capabilitycode;
	}

	public String getCapabilitycounter() {
		return capabilitycounter;
	}

	public void setCapabilitycounter(String capabilitycounter) {
		this.capabilitycounter = capabilitycounter;
	}

	public int getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(int manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getUnitName() {
		return UnitName;
	}

	public void setUnitName(String unitName) {
		UnitName = unitName;
	}

	@Override
	public void putObjectProperty() {
		this.putClientProperty("id", getId());
		this.putClientProperty("type", this.getCapabilitytype());
		this.putClientProperty("parameter", this.getCapabilityname());
		if(ResourceUtil.language.equals("zh_CN")){
			this.putClientProperty("explain", this.getCapabilitydesc());
		}else{
			this.putClientProperty("explain", this.getCapabilitydesc_en());
		}
		
		this.putClientProperty("desc", this.getCapabilitydisp());
	}
	
}
