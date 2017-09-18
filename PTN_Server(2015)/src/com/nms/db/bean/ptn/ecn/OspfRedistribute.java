package com.nms.db.bean.ptn.ecn;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;

public class OspfRedistribute extends ViewDataObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4167312208249111681L;
	private String id = "";
	private String neId = "";
	private String redistribute_type = "";
	private String type = "";
	private String metrictype = "";
	private int metric = 0;
	private boolean enable = false;
	private int status;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getNeId() {
		return neId;
	}

	public void setNeId(String neId) {
		this.neId = neId;
	}

	public String getRedistribute_type() {
		return redistribute_type;
	}

	public void setRedistribute_type(String redistribute_type) {
		this.redistribute_type = redistribute_type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMetrictype() {
		return metrictype;
	}

	public void setMetrictype(String metrictype) {
		this.metrictype = metrictype;
	}

	public int getMetric() {
		return metric;
	}

	public void setMetric(int metric) {
		this.metric = metric;
	}

	public boolean getEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		 
		try {
			getClientProperties().put("type", this.getRedistribute_type().toUpperCase());
			getClientProperties().put("routetype", this.getMetrictype().toUpperCase());
			getClientProperties().put("Metric", this.getMetric());
			getClientProperties().put("open", this.getEnable());
			getClientProperties().put("soucre", this.getType());
			getClientProperties().put("status", this.getStatus()==0?false:true);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}

	}

}
