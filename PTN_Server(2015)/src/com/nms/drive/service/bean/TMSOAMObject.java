package com.nms.drive.service.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * TMS OAM配置块(1AH)
 * 
 * @author hulei
 * 
 */
public class TMSOAMObject {
	// 4个TMSOAMInfoObject对象
	private List<TMSOAMInfoObject> tmsOamInfoList = new ArrayList<TMSOAMInfoObject>();
	private int reserve = 0;// 备用

	public List<TMSOAMInfoObject> getTmsOamInfoList() {
		return tmsOamInfoList;
	}

	public void setTmsOamInfoList(List<TMSOAMInfoObject> tmsOamInfoList) {
		this.tmsOamInfoList = tmsOamInfoList;
	}

	public int getReserve() {
		return reserve;
	}

	public void setReserve(int reserve) {
		this.reserve = reserve;
	}

}
