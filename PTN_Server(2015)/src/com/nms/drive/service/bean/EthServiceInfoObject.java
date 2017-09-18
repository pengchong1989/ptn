package com.nms.drive.service.bean;

/**
 * EHTSERVICE 数据驱动
 * @author Administrator
 *
 */
public class EthServiceInfoObject {
	
	private int num;//条目id
	private int vlanIdObject;//Vlan id： 2-4094
	private int portLine1Object; //字节4：bit0/bit1/…/bit7=port1/port2/…port8
	private int portLine2Object;//字节5：bit0/bit1/…/bit7=port9/port10/…port16
	private int portLine3Object;//字节6：bit0/bit1/…/bit7=port17/port10/…port24
	private int portLine4Object;//字节7：bit0/bit1/…/bit7=port25/port26/
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getVlanIdObject() {
		return vlanIdObject;
	}
	public void setVlanIdObject(int vlanIdObject) {
		this.vlanIdObject = vlanIdObject;
	}
	public int getPortLine1Object() {
		return portLine1Object;
	}
	public void setPortLine1Object(int portLine1Object) {
		this.portLine1Object = portLine1Object;
	}
	public int getPortLine2Object() {
		return portLine2Object;
	}
	public void setPortLine2Object(int portLine2Object) {
		this.portLine2Object = portLine2Object;
	}
	public int getPortLine3Object() {
		return portLine3Object;
	}
	public void setPortLine3Object(int portLine3Object) {
		this.portLine3Object = portLine3Object;
	}
	public int getPortLine4Object() {
		return portLine4Object;
	}
	public void setPortLine4Object(int portLine4Object) {
		this.portLine4Object = portLine4Object;
	}
	
}
