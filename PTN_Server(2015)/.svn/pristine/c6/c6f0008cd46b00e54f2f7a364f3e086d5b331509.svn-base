package com.nms.snmp.ninteface.framework;

import java.util.List;

import org.snmp4j.agent.mo.MOTable;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

public class MOHandlerMgr {

	public static void updateTableMO(MOTable entry, List<VariableBinding> vbList) {
		OID oid = entry.getOID();
		String oidStr = oid.toDottedString();
		TableHandler table = MOTabelMgr.getInstance().getTable(oidStr);
		table.updateMOTable(entry,vbList);
	}

	public static void setTableValue(MOTable entry,	List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		OID oid = entry.getOID();
		String oidStr = oid.toDottedString();
		TableHandler table = MOTabelMgr.getInstance().getTable(oidStr);
		table.setMoTable(entry,vbList);
	}
}
