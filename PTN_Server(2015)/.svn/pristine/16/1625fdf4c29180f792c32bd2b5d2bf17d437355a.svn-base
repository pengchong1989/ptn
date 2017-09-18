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
import com.nms.service.impl.dispatch.CardDispatch;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.snmp.ninteface.framework.TableHandler;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class EquipmentTable extends TableHandler {

	@Override
	public Object getInterfaceData(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setInterfaceData(List<VariableBinding> vbList) {
		String id = "";
		String oid = "";
		String[] oids = null;
		SlotInst slot  = null;
		List<SlotInst> slotInstList = null;
		CardService_MB cardService = null;
		SlotService_MB slotService = null;
		try {
			DispatchInterface dispath = new CardDispatch();
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			List<CardInst> cardList = cardService.select();
			if(vbList != null && vbList.size()>0){
			 for(VariableBinding vb : vbList){
				oid = vb.getOid().toString(); 
				oids = oid.trim().split("\\.");
				id =oids[oids.length-1];
				if(cardList !=null && cardList.size()>0){
					for(CardInst card : cardList){
						if(card.getId() == Integer.parseInt(id)){
							slot = new SlotInst();
							slot.setId(card.getSlotId());  
							card.setSnmpName(vb.getVariable().toString().split(";")[1]);
							slotInstList = slotService.select(slot);
							if(slotInstList != null && slotInstList.size()>0){
								card.setSlotInst(slotService.select(slot).get(0));
							}
							dispath.excuteInsert(card);
						}
					}
				}
			}
		 }
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(slotService);
			UiUtil.closeService_MB(cardService);
		}
		return true;
	}

	@Override
	public void setTable(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		for(VariableBinding vb : vbList){
			moTable.setValue(vb);
		}
	}

	@Override
	public void updateTable(Object obj) {
		CardService_MB cardService = null;
		SlotService_MB slotService = null;
		try {
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			List<CardInst> cardList = cardService.select();
			for (CardInst card : cardList) {
				SlotInst slot = new SlotInst();
				slot.setId(card.getSlotId());
				int number = slotService.select(slot).get(0).getNumber();
				Variable[] rowValues = new Variable[] {
					new OctetString(card.getId()+""),
					new OctetString(card.getSnmpName()),
					new OctetString(card.getCardName()),
					new OctetString(card.getCardName()),//物理设备实际安装的单板的具体型号
					new OctetString(SnmpConfig.getInstanse().getHardwareVersion()),//单板硬件版本
					new OctetString(""+number),//安装的设备序列号
					//枚举，取值如下：IN_SERVICE,OUT_OF_SERVICE,OUT_OF_SERVICE_BY_MAINTENANCE,SERV_NA
					new OctetString("IN_SERVICE"),//单板使用状态（可用，不可用）
					new OctetString(SnmpConfig.getInstanse().getSoftwareVersion()),//单板软件版本
				};
				addRow(new OID(""+card.getId()), rowValues);
			}
//			EquipmentConvertXml con = new EquipmentConvertXml();
//			con.getEquipmentXml();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(slotService);
			UiUtil.closeService_MB(cardService);
		}
	}

}
