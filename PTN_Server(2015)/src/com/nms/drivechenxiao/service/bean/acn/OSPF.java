package com.nms.drivechenxiao.service.bean.acn;
/**
 * OSPF
 * @author Administrator
 *
 */
public class OSPF {
	private int  hello_interval;
	private int  dead_interval;
	private int  retransmit_interval;
	private int  transmit_delay;
	private boolean  passive;
	private int  cost;
	private int  prioriy;
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
	
}
