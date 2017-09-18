package com.nms.snmp.ninteface.impl.inventory;

import java.util.List;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.snmp.ninteface.framework.TableHandler;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class TPDataTable extends TableHandler{

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

	//查询PW/tunnel的a,z 端属性
	@Override
	public void updateTable(Object obj) {
		Tunnel tunnelInfo = null;
		PwInfo pwInfo = null;
		TunnelService_MB tunnelService = null;
		PwInfoService_MB pwService = null;
		try {
			tunnelService = (TunnelService_MB)ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			pwService = (PwInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			List<Tunnel> tunnelList = tunnelService.select();
			List<PwInfo> pwInfoList = pwService.select();
			//tunnel的a,z 端
			if(tunnelList !=null && tunnelList.size()>0){
				for(int i = 0;i < tunnelList.size(); i++){
				  tunnelInfo = tunnelList.get(i);
				  addRow(new OID(tunnelInfo.getTunnelId()+""), getVariables(tunnelInfo,true));
				  addRow(new OID(tunnelInfo.getTunnelId()+""), getVariables(tunnelInfo,false));
 				}
			}
			//pw的a,z 端
			if(pwInfoList !=null && pwInfoList.size()>0){
				for(int i = 0;i < pwInfoList.size(); i++){
				  pwInfo = pwInfoList.get(i);
				  addRow(new OID(pwInfo.getPwId()+""), getVariables(tunnelInfo,true));
				  addRow(new OID(pwInfo.getPwId()+""), getVariables(tunnelInfo,false));
 				}
			}
		} catch (Exception e) {
		  ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(pwService);
		}
	}

	private Variable[] getVariables(Object object,boolean flag) {
		
		Variable[] variables = new Variable[2];
		try {
			//A端
			if(flag){
				if(object instanceof Tunnel){
					Tunnel tunnel = (Tunnel)object;
					String portName = getPortName(tunnel.getaSiteId(),tunnel.getaPortId());
					variables[0]= new OctetString(portName);
					if(tunnel.getLspParticularList() !=null && tunnel.getLspParticularList().size()>0){
					  variables[1]= new OctetString(tunnel.getLspParticularList().get(0).getFrontLabelValue()+"/"+tunnel.getLspParticularList().get(0).getBackLabelValue());
					}else{
						 variables[1]= new OctetString("");
					}
				}
				else if(object instanceof PwInfo){
					PwInfo pwInfo = (PwInfo)object;
					variables[0]= new OctetString(pwInfo.getTunnelName());
					variables[1]= new OctetString(pwInfo.getInlabelValue()+"/"+pwInfo.getOutlabelValue());
				}
			}
			//Z端
			else{
                if(object instanceof Tunnel){
					Tunnel tunnel = (Tunnel) object;
					String portName = getPortName(tunnel.getzSiteId(),tunnel.getzPortId());
					variables[0] = new OctetString(portName);
					if(tunnel.getLspParticularList() != null && tunnel.getLspParticularList().size()>0){
						variables[1] = new OctetString(tunnel.getLspParticularList().get(tunnel.getLspParticularList().size() - 1).getFrontLabelValue() + "/"+
								tunnel.getLspParticularList().get(tunnel.getLspParticularList().size() - 1).getBackLabelValue());
					}else{
						variables[1] = new OctetString("");
					}
				}
				else if(object instanceof PwInfo){
					PwInfo pwInfo = (PwInfo) object;
					variables[0] = new OctetString(pwInfo.getTunnelName());
                    variables[1] = new OctetString(pwInfo.getInlabelValue()+"/"+pwInfo.getOutlabelValue());
				}
			}
		 } catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return variables;
	}
	
	
	private String getPortName(int siteId , int portId){
		PortService_MB portService = null;
		try {
			portService = (PortService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			PortInst portInfo = new PortInst();
			portInfo.setSiteId(siteId);
			portInfo.setPortId(portId);
			List<PortInst> portList = portService.select(portInfo);
			if(portList != null && portList.size()>0){
				return portList.get(0).getPortName();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
		return "";
	}
	
}
