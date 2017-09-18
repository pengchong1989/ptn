package com.nms.snmp.ninteface.impl.inventory;

import java.util.List;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.Services;
import com.nms.snmp.ninteface.framework.TableHandler;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class EquipmentHolderTable extends TableHandler {

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
		try {
			slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			List<SlotInst> slotList = slotService.select();
			for (SlotInst slot : slotList) {
				String isUsed = slot.getCardId() == 0 ? "EMPTY":"INSTALLED_AND_EXPECTED";
				Variable[] rowValues = new Variable[] {
					new OctetString(slot.getId()+""),
					new OctetString(slot.getSnmpName()),
					new OctetString(slot.getSlotType()+"_"+slot.getId()),
					new OctetString("slot"),
					//枚举：
					//EMPTY, INSTALLED_AND_EXPECTED,EXPECTED_AND_NOT_INSTALLED,
					//INSTALLED_AND_NOT_EXPECTED,MISMATCH_OF_INSTALLED_AND_EXPECTED, 
					//UNAVAILABLE,UNKNOWN
					new OctetString(isUsed),//机槽使用状态
				};
				addRow(new OID(""+slot.getId()), rowValues);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(slotService);
		}
	}
}
