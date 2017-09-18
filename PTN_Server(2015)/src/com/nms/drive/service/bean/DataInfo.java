package com.nms.drive.service.bean;

public class DataInfo {
	private int TagNum = 0;//配置数据块的标识码
	private String CRCcheck = "";//配置数据块的CRC校验和1,…, 配置数据块的CRC校验和4
	private String  productionTime = "";//配置数据块的生产时间1,…,配置数据块的生成时间7
	public int getTagNum() {
		return TagNum;
	}
	public void setTagNum(int tagNum) {
		TagNum = tagNum;
	}
	public String getCRCcheck() {
		return CRCcheck;
	}
	public void setCRCcheck(String ccheck) {
		CRCcheck = ccheck;
	}
	public String getProductionTime() {
		return productionTime;
	}
	public void setProductionTime(String productionTime) {
		this.productionTime = productionTime;
	}
}
