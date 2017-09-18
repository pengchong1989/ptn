package com.nms.db.bean.ptn.ecn;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;

public class OSPFAREAInfo extends ViewDataObj {
	// TXC

	/**
	 * 
	 */
	private static final long serialVersionUID = -2221159579922022603L;
	private int id ;
	private String neId = "";
	private String area_range = "";
	private String type = "";
	private int summary;
	private int metric;//默认metric
	private int status ;// 0 未激活，库有，设备没有， 1 jihuo
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getMetric() {
		return metric;
	}

	public void setMetric(int metric) {
		this.metric = metric;
	}

	public int getSummary() {
		return summary;
	}

	public void setSummary(int summary) {
		this.summary = summary;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNeId() {
		return neId;
	}

	public void setNeId(String neId) {
		this.neId = neId;
	}

	public String getArea_range() {
		return area_range;
	}

	public void setArea_range(String area_range) {
		this.area_range = area_range;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("id", this.getId());
			getClientProperties().put("ospfid", this.getArea_range());
			getClientProperties().put("Metric", this.getMetric());//metric
		
			getClientProperties().put("areatype",this.getType().toUpperCase());
			getClientProperties().put("NoSummary", this.getSummary()==1?true:false);
			getClientProperties().put("status", this.getStatus()==1?true:false);
//			if(this.getSummary()==1){
//				getClientProperties().put("NoSummary",true);
//			}else {
//				getClientProperties().put("NoSummary",false);
//			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

}
