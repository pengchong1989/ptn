package com.nms.snmp.ninteface.impl.inventory;

import java.util.List;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.nms.snmp.ninteface.framework.TableHandler;

public class FlowDomainTable extends TableHandler{

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
		// TODO Auto-generated method stub
		Variable[] rowValues = new Variable[] {
				new OctetString("FlowDomain = 1"),
			};
			addRow(new OID("1"), rowValues);
	}

}
