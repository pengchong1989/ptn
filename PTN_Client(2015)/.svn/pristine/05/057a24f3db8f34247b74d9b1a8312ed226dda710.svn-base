package com.nms.db.bean.ptn.ecn;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;


public class OSPFInterface extends ViewDataObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String neId = "";
	private String interfaceName = "";
	private String area = "";
	private String type = "";
	private int hello_interval;
	private int dead_interval;
	private int retransmit_interval;
	private int transmit_delay;
	private boolean passive;
	private int cost;
	private int prioriy;
	private String authentication_type = "";
	private int activeStatus;

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

	public String getInterfaceName() {
		return interfaceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHello_interval() {
		return hello_interval;
	}

	public void setHello_interval(int hello_interval) {
		this.hello_interval = hello_interval;
	}

	public int getDead_interval() {
		return dead_interval;
	}

	public void setDead_interval(int dead_interval) {
		this.dead_interval = dead_interval;
	}

	public int getRetransmit_interval() {
		return retransmit_interval;
	}

	public void setRetransmit_interval(int retransmit_interval) {
		this.retransmit_interval = retransmit_interval;
	}

	public int getTransmit_delay() {
		return transmit_delay;
	}

	public void setTransmit_delay(int transmit_delay) {
		this.transmit_delay = transmit_delay;
	}

	public boolean isPassive() {
		return passive;
	}

	public void setPassive(boolean passive) {
		this.passive = passive;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getPrioriy() {
		return prioriy;
	}

	public void setPrioriy(int prioriy) {
		this.prioriy = prioriy;
	}

	public String getAuthentication_type() {
		return authentication_type;
	}

	public void setAuthentication_type(String authentication_type) {
		this.authentication_type = authentication_type;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		 
		try {

			getClientProperties().put("id", this.getId());
			getClientProperties().put("interfacesid", this.getInterfaceName());
			getClientProperties().put("ospfareaid", this.getArea());
			
			getClientProperties().put("type", UiUtil.getCodeByValue("OSPFINTERFACETYPE", this.getType()).getCodeName());
			getClientProperties().put("hellointerval", this.getHello_interval());
			getClientProperties().put("deadinterval", this.getDead_interval());
			getClientProperties().put("retransmissioninterval", this.getRetransmit_interval());
			getClientProperties().put("delayed", this.getTransmit_delay());
			getClientProperties().put("passivity", this.isPassive());
			getClientProperties().put("cost", this.getCost());
			getClientProperties().put("priority", this.getPrioriy());
			if( this.getAuthentication_type()==null|| this.getAuthentication_type().equals("")){
				getClientProperties().put("approve", "NONE");
			}else {
				getClientProperties().put("approve", this.getAuthentication_type());
			}
		
			getClientProperties().put("activeStatus", this.getActiveStatus()==1?true:false);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

}
