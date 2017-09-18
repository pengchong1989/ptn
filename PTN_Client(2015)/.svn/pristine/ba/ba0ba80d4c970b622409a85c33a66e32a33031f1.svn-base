package com.nms.db.bean.equipment.card;

import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

/**
 * 板卡 实体类
 * @author Administrator
 *
 */
public class CardInst extends ViewDataObj {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int slotNum;
	private int id;
	private int siteId;//网元ID
	private int equipId;//设备ID
	private int slotId;//槽位ID
	private String cardName;//板卡名称
	private String cardType;//板卡类型
	private String imagePath;//图片路径
	private int cardx;
	private int cardy;
	private List<PortInst> portList;//端口列表
	private SlotInst slotInst;//槽位
	private int tdmLoopback;
	private String snmpName;//北向厂商友好名称
	private String installedSerialNumber;//安装序列号
	

	public int getSlotNum() {
		return slotNum;
	}

	public void setSlotNum(int slotNum) {
		this.slotNum = slotNum;
	}

	public String getInstalledSerialNumber() {
		return installedSerialNumber;
	}

	public void setInstalledSerialNumber(String installedSerialNumber) {
		this.installedSerialNumber = installedSerialNumber;
	}

	public String getSnmpName() {
		return snmpName;
	}

	public void setSnmpName(String snmpName) {
		this.snmpName = snmpName;
	}

	public int getTdmLoopback() {
		return tdmLoopback;
	}

	public void setTdmLoopback(int tdmLoopback) {
		this.tdmLoopback = tdmLoopback;
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

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getCardx() {
		return cardx;
	}

	public void setCardx(int cardx) {
		this.cardx = cardx;
	}

	public int getCardy() {
		return cardy;
	}

	public void setCardy(int cardy) {
		this.cardy = cardy;
	}

	public List<PortInst> getPortList() {
		return portList;
	}

	public void setPortList(List<PortInst> portList) {
		this.portList = portList;
	}

	public SlotInst getSlotInst() {
		return slotInst;
	}

	public void setSlotInst(SlotInst slotInst) {
		this.slotInst = slotInst;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		SlotService_MB slotService = null;
		SlotInst slotInst = null;
		try {
			slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			slotInst = new SlotInst();
			slotInst.setId(getSlotId());
			slotInst = slotService.select(slotInst).get(0);
			getClientProperties().put("name", getCardName());
			getClientProperties().put("number", slotInst.getNumber());
			getClientProperties().put("cardName", getCardName());
			getClientProperties().put("slotType", slotInst.getSlotType());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(slotService);
		}
	}
	
}
