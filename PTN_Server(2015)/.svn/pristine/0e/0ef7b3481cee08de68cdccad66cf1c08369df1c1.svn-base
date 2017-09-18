package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.wh.TunnelProtectWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class TunnelProtectDispatch extends DispatchBase implements DispatchInterface{

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		Tunnel tunnel = (Tunnel) object;
		Tunnel beforeTunnel = null;
		Tunnel protectTunnel = null;
		TunnelService_MB tunnelService = null;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<Tunnel> tunnels = new ArrayList<Tunnel>();
		try {
			tunnels.add(tunnel);
			protectTunnel = tunnel.getProtectTunnel();
			protectTunnel.setTunnelType(185+"");
			tunnel.setTunnelType("185");
			tunnel.setProtectTunnel(null);
			tunnel.setProtectTunnelId(0);
			protectTunnel.setDelaytime(0);
			protectTunnel.setWaittime(0);
			protectTunnel.setProtectBack(0);
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			beforeTunnel = tunnelService.selectId(tunnel.getTunnelId());
			tunnelService.update(tunnel);
			tunnelService.update(protectTunnel);
			operationServiceI = new TunnelProtectWHServiceImpl();
			result = operationServiceI.excutionDelete(tunnels);
			if(!result.equals(ResultString.CONFIG_SUCCESS)){
				tunnelService.update(beforeTunnel);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(tunnelService);
		}
		return result;
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		Tunnel tunnel = (Tunnel) object;
		Tunnel beforeWorkTunnel = null;
		Tunnel beforeprotectTunnel = null;
		TunnelService_MB tunnelService = null;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			beforeWorkTunnel = tunnelService.selectByID(tunnel.getTunnelId());
			beforeprotectTunnel = tunnelService.selectByID(tunnel.getProtectTunnel().getTunnelId());
			tunnelService.update(tunnel);
			operationServiceI = new TunnelProtectWHServiceImpl();
			result = operationServiceI.excutionInsert(tunnel);
			if(!result.equals(ResultString.CONFIG_SUCCESS)){
				tunnelService.update(beforeWorkTunnel);
				tunnelService.update(beforeprotectTunnel);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(tunnelService);
		}
		return result;
	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		Tunnel tunnel = (Tunnel) object;
		Tunnel beforeTunnel = null;
		TunnelService_MB tunnelService = null;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<Tunnel> tunnels = new ArrayList<Tunnel>();
		try {
			tunnels.add(tunnel);
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			beforeTunnel = tunnelService.selectId(tunnel.getTunnelId());
			tunnelService.update(tunnel);
			operationServiceI = new TunnelProtectWHServiceImpl();
			result = operationServiceI.excutionDelete(tunnels);
			if(!result.equals(ResultString.CONFIG_SUCCESS)){
				tunnelService.update(beforeTunnel);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(tunnelService);
		}
		return result;
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		return null;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
