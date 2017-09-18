package com.nms.db.bean.ptn.qos;

import com.nms.db.enums.EMappingColorEnum;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

@SuppressWarnings("serial")
public class VlanpriToColor extends ViewDataObj{
	private int id; //id主键
	private String name;//名
	private int vlanpri0; //vlanpri0
	private int vlanpri1; //vlanpri1
	private int vlanpri2; //vlanpri2
	private int vlanpri3; //vlanpri3
	private int vlanpri4; //vlanpri4
	private int vlanpri5; //vlanpri5
	private int vlanpri6; //vlanpri6
	private int vlanpri7; //vlanpri7
	
	QosMappingMode qosMappingMode = new QosMappingMode();
	
	@Override
	@SuppressWarnings("unchecked")
	public void putObjectProperty() {
		getClientProperties().put("id", getId());
		getClientProperties().put("name", getName());
		getClientProperties().put("vlanpri0",  "YELLOW".equals(EMappingColorEnum.from(getVlanpri0()).toString())?ResourceUtil.srcStr(StringKeysObj.YELLOW):ResourceUtil.srcStr(StringKeysObj.GREEN));
		getClientProperties().put("vlanpri1",  "YELLOW".equals(EMappingColorEnum.from(getVlanpri1()).toString())?ResourceUtil.srcStr(StringKeysObj.YELLOW):ResourceUtil.srcStr(StringKeysObj.GREEN));
		getClientProperties().put("vlanpri2",  "YELLOW".equals(EMappingColorEnum.from(getVlanpri2()).toString())?ResourceUtil.srcStr(StringKeysObj.YELLOW):ResourceUtil.srcStr(StringKeysObj.GREEN));
		getClientProperties().put("vlanpri3",  "YELLOW".equals(EMappingColorEnum.from(getVlanpri3()).toString())?ResourceUtil.srcStr(StringKeysObj.YELLOW):ResourceUtil.srcStr(StringKeysObj.GREEN));
		getClientProperties().put("vlanpri4",  "YELLOW".equals(EMappingColorEnum.from(getVlanpri4()).toString())?ResourceUtil.srcStr(StringKeysObj.YELLOW):ResourceUtil.srcStr(StringKeysObj.GREEN));
		getClientProperties().put("vlanpri5",  "YELLOW".equals(EMappingColorEnum.from(getVlanpri5()).toString())?ResourceUtil.srcStr(StringKeysObj.YELLOW):ResourceUtil.srcStr(StringKeysObj.GREEN));
		getClientProperties().put("vlanpri6",  "YELLOW".equals(EMappingColorEnum.from(getVlanpri6()).toString())?ResourceUtil.srcStr(StringKeysObj.YELLOW):ResourceUtil.srcStr(StringKeysObj.GREEN));
		getClientProperties().put("vlanpri7",  "YELLOW".equals(EMappingColorEnum.from(getVlanpri7()).toString())?ResourceUtil.srcStr(StringKeysObj.YELLOW):ResourceUtil.srcStr(StringKeysObj.GREEN));
		
		
	}
	public QosMappingMode getQosMappingMode() {
		return qosMappingMode;
	}
	public void setQosMappingMode(QosMappingMode qosMappingMode) {
		this.qosMappingMode = qosMappingMode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	public int getVlanpri0() {
		return vlanpri0;
	}
	public void setVlanpri0(int vlanpri0) {
		this.vlanpri0 = vlanpri0;
	}
	public int getVlanpri1() {
		return vlanpri1;
	}
	public void setVlanpri1(int vlanpri1) {
		this.vlanpri1 = vlanpri1;
	}
	public int getVlanpri2() {
		return vlanpri2;
	}
	public void setVlanpri2(int vlanpri2) {
		this.vlanpri2 = vlanpri2;
	}
	public int getVlanpri3() {
		return vlanpri3;
	}
	public void setVlanpri3(int vlanpri3) {
		this.vlanpri3 = vlanpri3;
	}
	public int getVlanpri4() {
		return vlanpri4;
	}
	public void setVlanpri4(int vlanpri4) {
		this.vlanpri4 = vlanpri4;
	}
	public int getVlanpri5() {
		return vlanpri5;
	}
	public void setVlanpri5(int vlanpri5) {
		this.vlanpri5 = vlanpri5;
	}
	public int getVlanpri6() {
		return vlanpri6;
	}
	public void setVlanpri6(int vlanpri6) {
		this.vlanpri6 = vlanpri6;
	}
	public int getVlanpri7() {
		return vlanpri7;
	}
	public void setVlanpri7(int vlanpri7) {
		this.vlanpri7 = vlanpri7;
	}

}
