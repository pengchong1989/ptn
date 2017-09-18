package com.nms.snmp.ninteface.impl.inventory;

import java.util.List;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.Services;
import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.snmp.ninteface.framework.TableHandler;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class EquiporHolderTable extends TableHandler {

	@Override
	public Object getInterfaceData(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setInterfaceData(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setTable(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateTable(Object obj) {
		SlotService_MB slotService = null;
		CardService_MB cardService = null;
		try {
			int i = 1;
			slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			List<CardInst> cardList = cardService.select();
			for (CardInst card : cardList) {
				SlotInst slot = new SlotInst();
				slot.setId(card.getSlotId());
				int number = slotService.select(slot).get(0).getNumber();
				Variable[] rowValues = new Variable[] {
					new OctetString(card.getSnmpName()),//equipName
					new OctetString(card.getId()+""),//equipUserLabel
					new OctetString(card.getCardName()),//equipNativeEMSName
					new OctetString(card.getCardName()),//installedEquipmentObjectType 物理设备实际安装的单板的具体型号
					new OctetString("V3.0"),//installedPartNumber 单板硬件版本
					new OctetString(""+number),//installedSerialNumber 安装的设备序列号
					//枚举，取值如下：IN_SERVICE,OUT_OF_SERVICE,OUT_OF_SERVICE_BY_MAINTENANCE,SERV_NA
					new OctetString("IN_SERVICE"),//serviceState 单板使用状态（可用，不可用）
					new OctetString(SnmpConfig.getInstanse().getSoftwareVersion()),//installedVersion 单板软件版本
					new OctetString(""),//holderName
					new OctetString(""),//holderUserLabel
					new OctetString(""),//holderNativeEMSName
					new OctetString(""),//holderType
					new OctetString(""),//holderState
				};
				addRow(new OID(""+(i++)), rowValues);
			}
			List<SlotInst> slotList = slotService.select();
			for (SlotInst slot : slotList) {
				String isUsed = slot.getCardId() == 0 ? "EMPTY":"INSTALLED_AND_EXPECTED";
				Variable[] rowValues = new Variable[] {
					new OctetString(""),
					new OctetString(""),
					new OctetString(""),
					new OctetString(""),
					new OctetString(""),
					new OctetString(""),
					new OctetString(""),
					new OctetString(""),
					new OctetString(slot.getSnmpName()),
					new OctetString(slot.getId()+""),
					new OctetString(slot.getSlotType()+"_"+slot.getId()),
					new OctetString("slot"),
					//枚举：
					//EMPTY, INSTALLED_AND_EXPECTED,EXPECTED_AND_NOT_INSTALLED,
					//INSTALLED_AND_NOT_EXPECTED,MISMATCH_OF_INSTALLED_AND_EXPECTED, 
					//UNAVAILABLE,UNKNOWN
					new OctetString(isUsed),//机槽使用状态
				};
				addRow(new OID(""+(i++)), rowValues);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(slotService);
			UiUtil.closeService_MB(cardService);
		}
	}

}
