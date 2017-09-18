package com.nms.ui.ptn.business.dialog.cespath.modal;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortStmTimeslot;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class CesPortInfo extends ViewDataObj{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5538055075904852789L;
	private PortInst e1PortInst;
	private PortStmTimeslot portStmTimeSlot;
	
	public PortInst getE1PortInst() {
		return e1PortInst;
	}

	public void setE1PortInst(PortInst e1PortInst) {
		this.e1PortInst = e1PortInst;
	}


	public PortStmTimeslot getPortStmTimeSlot() {
		return portStmTimeSlot;
	}

	public void setPortStmTimeSlot(PortStmTimeslot portStmTimeSlot) {
		this.portStmTimeSlot = portStmTimeSlot;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			if(this.e1PortInst != null ) {
				this.getClientProperties().put("id", this.e1PortInst);
				this.getClientProperties().put("name",  this.e1PortInst.getPortName());
//				this.getClientProperties().put("ces_sendjtwo",  "-");
//				this.getClientProperties().put("ces_sendvfive", "-");			
			}
			if(this.portStmTimeSlot != null) {
				this.getClientProperties().put("id", this.portStmTimeSlot);
				this.getClientProperties().put("name",  this.portStmTimeSlot.getTimeslotnumber());
				this.getClientProperties().put("ces_sendjtwo", this.portStmTimeSlot.getSendjtwo() );
				if(null!= this.portStmTimeSlot.getSendvfive() && !"0".equals(this.portStmTimeSlot.getSendvfive())){
					getClientProperties().put("ces_sendvfive", UiUtil.getCodeById(Integer.parseInt(this.portStmTimeSlot.getSendvfive())).getCodeName());
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}

}
