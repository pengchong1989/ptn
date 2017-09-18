package com.nms.db.bean.equipment.slot;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;

public class SlotInst extends ViewDataObj {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int siteId;
	private int equipId;
	private int cardId;
	private String imagePath;
	private String slotType;
	private int slotx;
	private int sloty;
	private String bestCardName;
	private CardInst cardInst;
	private int number;
	private String cellid;
	private String cardname;
	private int tdmLoopback; 
	private String masterCardAddress;
	private String snmpName;//北向厂商友好名称
	
	
	public String getSnmpName() {
		return snmpName;
	}

	public void setSnmpName(String snmpName) {
		this.snmpName = snmpName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getEquipId() {
		return equipId;
	}

	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getSlotType() {
		return slotType;
	}

	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}

	public int getSlotx() {
		return slotx;
	}

	public void setSlotx(int slotx) {
		this.slotx = slotx;
	}

	public int getSloty() {
		return sloty;
	}

	public void setSloty(int sloty) {
		this.sloty = sloty;
	}

	public CardInst getCardInst() {
		return cardInst;
	}

	public void setCardInst(CardInst cardInst) {
		this.cardInst = cardInst;
	}

	public String getBestCardName() {
		return bestCardName;
	}

	public void setBestCardName(String bestCardName) {
		this.bestCardName = bestCardName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	public String getCellid() {
		return cellid;
	}

	public void setCellid(String cellid) {
		this.cellid = cellid;
	}
	public String getCardname() {
		return cardname;
	}
	public void setCardname(String cardname) {
		this.cardname = cardname;
	}
	
	
	public int getTdmLoopback() {
		return tdmLoopback;
	}

	public void setTdmLoopback(int tdmLoopback) {
		this.tdmLoopback = tdmLoopback;
	}

	public String getMasterCardAddress() {
		return masterCardAddress;
	}

	public void setMasterCardAddress(String masterCardAddress) {
		this.masterCardAddress = masterCardAddress;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("name", getCellid());
			getClientProperties().put("number", getNumber());
			getClientProperties().put("cardName",getCardname());
			getClientProperties().put("slotType", getSlotType());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
}
