package com.nms.snmp.ninteface.impl.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.ptn.path.protect.LoopProtectInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.model.ptn.path.protect.WrappingProtectService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.TunnelDispatch;
import com.nms.service.impl.dispatch.WrappingDispatch;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.snmp.ninteface.framework.TableHandler;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class TrailNtwProtectionTable extends TableHandler {

	@Override
	public Object getInterfaceData(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setInterfaceData(List<VariableBinding> vbList) {
		String id = "";
		String index = "";
		String oid = "";
		String[] oids = null;
		DispatchInterface dispathTunnel = null;
		DispatchInterface dispathLoopProtect = null;
		String value = null;
		String waitTime = null;
		String delayTime = null;
		Tunnel tunnel = null;
		LoopProtectInfo loopProtectInfo = null;
		List<LoopProtectInfo> loopProtectList = null;
		try {
			loopProtectList = new ArrayList<LoopProtectInfo>();
			dispathTunnel = new TunnelDispatch();
			dispathLoopProtect = new WrappingDispatch();
			Map<Integer, Object> protectMap  = getAllProtect_t();
			if(vbList != null && vbList.size()>0){
			  for(VariableBinding vb : vbList){
				oid = vb.getOid().toString();
				oids = oid.trim().split("\\.");
				id =oids[oids.length-1];
				index = oids[oids.length-2];
				for(Integer indexId : protectMap.keySet()){
					if(indexId == Integer.parseInt(id)){
						if(index.equals("3")){
							Object object = protectMap.get(indexId);
							if(object instanceof Tunnel){
								tunnel = (Tunnel)object;
								tunnel.setTunnelName(vb.getVariable().toString().split(";")[1]);
								dispathTunnel.excuteUpdate(tunnel);
							}else if(object instanceof LoopProtectInfo){
								loopProtectList.clear();
								loopProtectInfo = (LoopProtectInfo)object;
								loopProtectInfo.setName(vb.getVariable().toString().split(";")[1]);
								loopProtectList.add(loopProtectInfo);
								dispathLoopProtect.excuteUpdate(loopProtectList);
							}
						}else if(index.equals("13")){
							Object object = protectMap.get(indexId);
							value = vb.getVariable().toString().split(";")[1];
							//index 获取的数据格式应该跟回给snmp客户端一样的 "WaitTime:"+p.getWaitTime()+"/"+"DelayTime:"+p.getDelaytime()  12/9
							waitTime = value.substring(0,value.indexOf("/"));
							delayTime = value.substring(value.indexOf("/")+1,value.length());
							if(object instanceof Tunnel){
								tunnel = (Tunnel)object;
								tunnel.setWaittime(Integer.parseInt(waitTime));
								tunnel.setDelaytime(Integer.parseInt(delayTime));
								dispathTunnel.excuteUpdate(tunnel);
							}else if(object instanceof LoopProtectInfo){
								loopProtectList.clear();
								loopProtectInfo = (LoopProtectInfo)object;
								loopProtectInfo.setWaittime(Integer.parseInt(waitTime));
								loopProtectInfo.setDelaytime(Integer.parseInt(delayTime));
								loopProtectList.add(loopProtectInfo);
								dispathLoopProtect.excuteUpdate(loopProtectList);
							}
						}
					}
				}
			}
		  }
		} catch (Exception e) {
		  ExceptionManage.dispose(e,this.getClass());
	  }finally{
		  id = null;
		  index = null;
		  oid = null;
		  oids = null;
		  dispathTunnel = null;
		  dispathLoopProtect = null;
		  value = null;
		  waitTime = null;
		  delayTime = null;
		  tunnel = null;
		  loopProtectInfo = null;
		  loopProtectList = null;
	  }
	 return true;
	}

	@Override
	public void setTable(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		for (VariableBinding vb : vbList) {
			moTable.setValue(vb);
		}
	}

	@Override
	public void updateTable(Object obj) {
		TunnelService_MB tunnelService = null;
		WrappingProtectService_MB protectService = null;
		try {
			int i = 1;
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			List<Tunnel> tunnelList = tunnelService.select();
			for (Tunnel tunnel : tunnelList) {
				if(!tunnel.getTunnelType().equals("185")){
					Variable[] rowValues = new Variable[] {
						new OctetString(tunnel.getTunnelId()+""),
						new OctetString(tunnel.getTunnelName()),
						new OctetString(tunnel.getTunnelName()),
						new OctetString("1:1"),
						new OctetString("UNKNOWN"),
						new OctetString(ELayerRate.TUNNEL.getValue()+""),//层速率
						new OctetString("open"),
						new OctetString(tunnel.getShowSiteAname()+"/"+tunnel.getAPortId()),
						new OctetString(tunnel.getShowSiteZname()+"/"+tunnel.getZPortId()),
						new OctetString(tunnel.getTunnelName()),
						new OctetString(tunnel.getProtectTunnel().getTunnelName()),
						new OctetString("waitTime:"+tunnel.getWaittime()+"/"+"delayTime:"+tunnel.getDelaytime()),//TNP参数
						new OctetString(tunnel.getTunnelStatus()==1? "activity":"unactivity"),//附加信息
					};
					addRow(new OID(""+i), rowValues);
					i++;
				}
			}
			protectService = (WrappingProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WRAPPINGPROTECT);
			List<LoopProtectInfo> protectList = protectService.select();
			for (LoopProtectInfo p : protectList) {
				Variable[] rowValues = new Variable[] {
					new OctetString(p.getId()+""),
					new OctetString(p.getName()),
					new OctetString(p.getName()),
					new OctetString("Ring Wrapping_V2"),
					new OctetString("UNKNOWN"),
					new OctetString((short) ELayerRate.PGP.getValue()+""),//层速率
					new OctetString("open"),
					new OctetString(p.getNodeId()+"/"+p.getWestPort()),
					new OctetString(p.getTargetNodeId()+"/"+p.getEastPort()),
					new OctetString(""),//工作路径列表(环网保护不填)
					new OctetString(""),//保护路径(环网保护不填)
					new OctetString("waitTime:"+p.getWaittime()+"/"+"delayTime:"+p.getDelaytime()),//TNP参数
					new OctetString(p.getActiveStatus()==1? "activity":"unactivity"),//附加信息
				};
				addRow(new OID(""+i), rowValues);
				i++;
			}
		} catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(protectService);
		}
	}
	
	/**
	 * @return 所有的保护组
	 */
	public Map<Integer,Object> getAllProtect_t(){
		int i = 1;
		Map<Integer, Object> protectMap = new HashMap<Integer, Object>();
		TunnelService_MB tunnelService = null;
		WrappingProtectService_MB protectService = null;
		try {
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			List<Tunnel> tunnelList = tunnelService.select();
			protectService = (WrappingProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WRAPPINGPROTECT);
			List<LoopProtectInfo> protectList = protectService.select();
			for (Tunnel tunnel : tunnelList) {
				if(!tunnel.getTunnelType().equals("185")){
					protectMap.put(i++, tunnel);
				}
			 }
			for (LoopProtectInfo p : protectList) {
				protectMap.put(i++, p);
			}
		} catch (Exception e) {
		  ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(protectService);
			UiUtil.closeService_MB(tunnelService);
		}
		return protectMap;
	}
}
