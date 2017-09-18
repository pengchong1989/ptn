package com.nms.snmp.ninteface.framework;

import java.util.List;

import org.snmp4j.agent.mo.MOMutableTableModel;
import org.snmp4j.agent.mo.MOTable;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

public abstract class TableHandler {
	public MOTable moTable = null;
	public TableHandler(){
	}
	
	//更新表
	public void updateMOTable(MOTable mo, List<VariableBinding> vbList) {
		this.moTable = mo;
		cleanTable();
		Object obj = getInterfaceData(vbList);
		updateTable(obj);
	}
	
	//set
	public void setMoTable(MOTable mo, List<VariableBinding> vbList){
		this.moTable = mo;
		boolean bl = setInterfaceData(vbList);
		if(bl){
			setTable(vbList);
		}
	}
	
	public  void cleanTable(){
	    MOMutableTableModel model = (MOMutableTableModel) moTable.getModel();
	    model.clear();  
	}
	public  void  addRow(OID index ,Variable[] rowValues){
		moTable.addNewRow(index, rowValues);
	}

	public abstract void setTable(List<VariableBinding> vbList);
	
	public abstract boolean setInterfaceData(List<VariableBinding> vbList);
	
	public abstract Object getInterfaceData(List<VariableBinding> vbList);
	
	public abstract void updateTable(Object obj);


}
