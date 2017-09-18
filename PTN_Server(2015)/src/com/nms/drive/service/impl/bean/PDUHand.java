package com.nms.drive.service.impl.bean;

public class PDUHand {

	/*
	 * 【PDU类型标签】 Request-PDU = E1; Response-PDU = E2; Report-PDU = E3; Confirm-PDU = E4; Broadcast-PDU = E5;
	 */
	private int pduTypeTab = 0;

	/*
	 * 【通信类型】 “通信类型”字段（1字节）表示管理实体间信息交互的方式和发起方，分配代码如下： WS－M：由WS发出的数据，类型代码为1； M－WS：由M发出的数据，类型代码为2； M－A：由M发出的数据，类型代码为3； A－M：由A发出的数据，类型代码为4； A－BMU：由A发出的数据，类型代码为5； BMU－A：由BMU发出的数据，类型代码为6； A—BMU广播：由A向所管辖BMU发起的广播型数据，类型代码为7； PC－A：由PC机发出的数据（用串口通信），代码类型为8； A－PC：由A向PC发出的数据（用串口通信），类型代码为9； PC－A：由PC机发出的数据（用以太网通信），类型代码为0xA； A－PC：由PC机发出的数据（用以太网通信），类型代码为0xB； ACU－BMU：由ACU向BMU发出的数据，类型代码为0xE； BMU－ACU：由BMU向ACU发出的数据，类型代码为0xD； ACU－NMU：由ACU向NMU发出的数据，类型代码为0xE； NMU－ACU：由NMU向ACU发出的数据，类型代码为0xF。
	 */
	private int socketType = 0;

	/*
	 * 【PDU信息体的长度】
	 */
	private int length = 0;

	public int getPduTypeTab() {
		return pduTypeTab;
	}

	public void setPduTypeTab(int pduTypeTab) {
		this.pduTypeTab = pduTypeTab;
	}

	public int getSocketType() {
		return socketType;
	}

	public void setSocketType(int socketType) {
		this.socketType = socketType;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
