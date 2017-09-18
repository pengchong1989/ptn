package com.nms.db.bean.equipment.manager;

import java.util.List;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class UpgradeManage extends ViewDataObj {

	private static final long serialVersionUID = -4136070652679516999L;
	private int id;// 序号
	private int cardId;// 板卡类型
	private String logicalCardType;// 逻辑板类型
	private String physicalCardType;// 物理板类型
	private String workingCard;// 实际工作板卡
	private String workingVersions;// 运行版本
	private String configurateVersions;// 配置版本
	private int softwareType;//软件类型
	private int crc;//CRC校验
	private String time;//编译时间
	private int controlPanelType = 50332320;// 盘类型
	private int controlPanelGroupId = 1;// 盘组号
	private int controlPanelAddress = 1;// 盘地址
	private String siteName;//网元名称
	
	public int getSoftwareType() {
		return softwareType;
	}

	public void setSoftwareType(int softwareType) {
		this.softwareType = softwareType;
	}

	public int getCrc() {
		return crc;
	}

	public void setCrc(int crc) {
		this.crc = crc;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getLogicalCardType() {
		return logicalCardType;
	}

	public void setLogicalCardType(String logicalCardType) {
		this.logicalCardType = logicalCardType;
	}

	public String getPhysicalCardType() {
		return physicalCardType;
	}

	public void setPhysicalCardType(String physicalCardType) {
		this.physicalCardType = physicalCardType;
	}

	

	public String getWorkingCard() {
		return workingCard;
	}

	public void setWorkingCard(String workingCard) {
		this.workingCard = workingCard;
	}

	public String getWorkingVersions() {
		return workingVersions;
	}

	public void setWorkingVersions(String workingVersions) {
		this.workingVersions = workingVersions;
	}

	public String getConfigurateVersions() {
		return configurateVersions;
	}

	public void setConfigurateVersions(String configurateVersions) {
		this.configurateVersions = configurateVersions;
	}

	
	
	public int getControlPanelType() {
		return controlPanelType;
	}

	public void setControlPanelType(int controlPanelType) {
		this.controlPanelType = controlPanelType;
	}

	public int getControlPanelGroupId() {
		return controlPanelGroupId;
	}

	public void setControlPanelGroupId(int controlPanelGroupId) {
		this.controlPanelGroupId = controlPanelGroupId;
	}

	public int getControlPanelAddress() {
		return controlPanelAddress;
	}

	public void setControlPanelAddress(int controlPanelAddress) {
		this.controlPanelAddress = controlPanelAddress;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("id", getId());
			getClientProperties().put("equipmentType",getSiteName());
			getClientProperties().put("cardId", getSlotName(getControlPanelAddress()));
			getClientProperties().put("softwarType", getSoftwarType(getSoftwareType()));
			getClientProperties().put("crc",Integer.toHexString(getCrc()));
			getClientProperties().put("time",getTime());
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
	}

	private String getSoftwarType(int softwar){
		String type = "";
		if(softwar == 1 || softwar == 5){
			type = "data_rom";
		}else if(softwar == 2|| softwar == 7){
			type = "data_boot";
		}else if(softwar == 3|| softwar == 8){
			type = "data_fpga";
		}else if(softwar == 4|| softwar == 9){
			type = "e1_fpga";
		}else{
			type = "patch";
		}
		return type;
	}
	
	private String getSlotName(int slotNumber){
		String cardName = "";
		CardService_MB cardService = null;
		SlotService_MB slotService = null;
		try {
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			SlotInst slotInst = new SlotInst();
			slotInst.setSiteId(ConstantUtil.siteId);
			slotInst.setNumber(slotNumber);
			List<SlotInst> slotInsts = slotService.select(slotInst);
			if(slotInsts.size()>0){
				CardInst card = new CardInst();
				card.setSiteId(ConstantUtil.siteId);
				card.setSlotId(slotInsts.get(0).getId());
				List<CardInst> cardList = cardService.select(card);
				if(cardList.size() > 0){
					cardName = cardList.get(0).getCardName();
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(cardService);
			UiUtil.closeService_MB(slotService);
		}
		return cardName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
	
}