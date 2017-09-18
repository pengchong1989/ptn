package com.nms.db.bean.ptn.qos;

import com.nms.ui.frame.ViewDataObj;

public class MappingRelation  extends ViewDataObj{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2732124756155200869L;
	private int id;
	private int	tableId;
	private int	siteId;
	private int	portId;
	private int	businessId;

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getTableId() {
		return tableId;
	}


	public void setTableId(int tableId) {
		this.tableId = tableId;
	}


	public int getSiteId() {
		return siteId;
	}


	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}


	public int getPortId() {
		return portId;
	}


	public void setPortId(int portId) {
		this.portId = portId;
	}


	public int getBusinessId() {
		return businessId;
	}


	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	@Override
	public void putObjectProperty() {
		// TODO Auto-generated method stub
		
	}
}
