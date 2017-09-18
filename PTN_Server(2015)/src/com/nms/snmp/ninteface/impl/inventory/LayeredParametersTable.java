package com.nms.snmp.ninteface.impl.inventory;

import java.util.List;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.Services;
import com.nms.snmp.ninteface.framework.TableHandler;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class LayeredParametersTable extends TableHandler{

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
	 PortService_MB portService = null;
	 try {
		 portService = (PortService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PORT);
		 PortInst port=new PortInst();
		 List<PortInst> portList = portService.select(port);
		 if(portList != null && portList.size() >0){
			 for(int i = 0; i<portList.size() ; i++){
				 if(!portList.get(i).getPortType().equalsIgnoreCase("system")){
					 Variable[] rowValues = new Variable[]{
							 new OctetString(getLayer(portList.get(i))+""),
							 new OctetString(portList.get(i).getPortName()),
					 }; 
					 addRow(new OID(portList.get(i).getPortId()+""), rowValues);
				 }
			 }
		 }
	 } catch (Exception e) {
    	ExceptionManage.dispose(e,this.getClass());
	 } finally {
		 UiUtil.closeService_MB(portService);
	 }
	
	}

	public short getLayer(PortInst portInst) throws Exception{
		short layer =0;
		String portType = portInst.getPortType();
		try {
			//计算端口的速率
			if("NONE".equals(portType)||"NNI".equals(portType)||"UNI".equals(portType)){
				layer = (short)ELayerRate.PORT.getValue();
			}else if("e1".equals(portType)){
				layer = (short)ELayerRate.TDMPORT.getValue();
			}
			
		} catch (Exception e) {
			throw e;
		}
		return layer;
	}
}
