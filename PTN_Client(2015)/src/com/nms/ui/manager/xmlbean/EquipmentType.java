package com.nms.ui.manager.xmlbean;

public class EquipmentType {
	private String typeName;
	private String xmlPath;
	private String imagePath;
	private String cxEquipmentName;
	private int manufacturer;
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getXmlPath() {
		return xmlPath;
	}
	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getCxEquipmentName() {
		return cxEquipmentName;
	}
	public void setCxEquipmentName(String cxEquipmentName) {
		this.cxEquipmentName = cxEquipmentName;
	}
	public int getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(int manufacturer) {
		this.manufacturer = manufacturer;
	}
	
}
