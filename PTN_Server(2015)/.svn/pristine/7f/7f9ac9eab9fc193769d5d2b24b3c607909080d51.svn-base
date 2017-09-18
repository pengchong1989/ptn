package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EActiveStatus;
import com.nms.drive.service.PtnServiceEnum;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.TunnelProtection;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class TunnelProtectWHServiceImpl extends WHOperationBase implements OperationServiceI{

	@Override
	public String excutionDelete(List objectList) throws Exception {
		Tunnel tunnel = (Tunnel)objectList.get(0);
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		try {
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(tunnel.getaSiteId());
			siteIdList.add(tunnel.getzSiteId());
			SiteUtil siteUtil=new SiteUtil();
			WhImplUtil whImplUtil = new WhImplUtil();
			for(Integer siteId : siteIdList){
				if(siteUtil.SiteTypeUtil(siteId) == 0){//非失连网元、非虚拟网元下发设备
					List<TunnelProtection> protectTunnelObjectList = new ArrayList<TunnelProtection>();
					neObject = whImplUtil.siteIdToNeObject(siteId);
					getProtectObjects(siteId, protectTunnelObjectList);
					operationObjectAfter = new OperationObject();
					super.sendAction(operationObjectAfter, neObject, protectTunnelObjectList,PtnServiceEnum.LSP_PROTECT);//下发1:1保护配置
					operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
					if (!(operationObjectResult.isSuccess())) {//失败
						return super.getErrorMessage(operationObjectResult);
					}
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ResultString.CONFIG_SUCCESS;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		Tunnel tunnel = (Tunnel)object;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		try {
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(tunnel.getaSiteId());
			siteIdList.add(tunnel.getzSiteId());
			SiteUtil siteUtil=new SiteUtil();
			WhImplUtil whImplUtil = new WhImplUtil();
			for(Integer siteId : siteIdList){
				if(siteUtil.SiteTypeUtil(siteId) == 0){//非失连网元、非虚拟网元下发设备
					List<TunnelProtection> protectTunnelObjectList = new ArrayList<TunnelProtection>();
					neObject = whImplUtil.siteIdToNeObject(siteId);
					getProtectObjects(siteId, protectTunnelObjectList);
					operationObjectAfter = new OperationObject();
					super.sendAction(operationObjectAfter, neObject, protectTunnelObjectList,PtnServiceEnum.LSP_PROTECT);//下发1:1保护配置
					operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
					if (!(operationObjectResult.isSuccess())) {//失败
						return super.getErrorMessage(operationObjectResult);
					}
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ResultString.CONFIG_SUCCESS;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		Tunnel tunnel = (Tunnel)object;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		try { 
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(tunnel.getaSiteId());
			siteIdList.add(tunnel.getzSiteId());
			SiteUtil siteUtil=new SiteUtil();
			WhImplUtil whImplUtil = new WhImplUtil();
			for(Integer siteId : siteIdList){
				if(siteUtil.SiteTypeUtil(siteId) == 0){//非失连网元、非虚拟网元下发设备
					List<TunnelProtection> protectTunnelObjectList = new ArrayList<TunnelProtection>();
					neObject = whImplUtil.siteIdToNeObject(siteId);
					getProtectObjects(siteId, protectTunnelObjectList);
					operationObjectAfter = new OperationObject();
					super.sendAction(operationObjectAfter, neObject, protectTunnelObjectList,PtnServiceEnum.LSP_PROTECT);//下发1:1保护配置
					operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
					if (!(operationObjectResult.isSuccess())) {//失败
						return super.getErrorMessage(operationObjectResult);
					}
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ResultString.CONFIG_SUCCESS;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	private void getProtectObjects(int siteId, List<TunnelProtection> protectTunnelObjectList){
		TunnelService_MB tunnelService = null;
		List<Tunnel> activeList = null;
		List<Tunnel> tunnelList = null;
		List<Tunnel> tunnels = null;
		Tunnel mainTunnel = null;
		try {
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnelList = tunnelService.selectWHNodesBySiteId(siteId);
			activeList = new ArrayList<Tunnel>();

			for (Tunnel obj : tunnelList) {
				if (obj.getTunnelStatus() == EActiveStatus.ACTIVITY.getValue())
					activeList.add(obj);
			}
			for (int i = 0; i < activeList.size(); i++) {
				Tunnel tunnel = activeList.get(i);
				mainTunnel = new Tunnel();
				if (tunnel.getLspParticularList().size() == 1) {
					if ("0".equals(tunnel.getTunnelType())) {// 封装保护tunnel和对应的保护配置块
						tunnels = new ArrayList<Tunnel>();
						TunnelProtection protectTunnelObject = new TunnelProtection();
						mainTunnel.setProtectTunnelId(tunnel.getTunnelId());
						tunnels.add(tunnel);// 保护tunnel
						mainTunnel = tunnelService.select_nojoin(mainTunnel).get(0);
						tunnels.add(mainTunnel);// 主用tunnel
						this.getProtectTunnelObject(siteId, tunnels, protectTunnelObject);
						protectTunnelObjectList.add(protectTunnelObject);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(tunnelService);
		}
	}
	
	/**
	 * 下发设备流程
	 * 封装保护配置块
	 * @param siteId
	 * @param tunnelList
	 * @param protectTunnelObject
	 * @return
	 * @throws Exception
	 */
	private TunnelProtection getProtectTunnelObject(int siteId, List<Tunnel> tunnelList, TunnelProtection protectTunnelObject) throws Exception {
		PortService_MB portService = null;
		PortInst mainportInst = null;
		PortInst standportInst = null;
		Tunnel mainTunnel = tunnelList.get(1);
		Tunnel standTunnel = tunnelList.get(0);
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			if (mainTunnel.getASiteId() == siteId) {//A端
				mainportInst = new PortInst();
				mainportInst.setPortId(mainTunnel.getAPortId());
				mainportInst = portService.select(mainportInst).get(0);//主用port

				standportInst = new PortInst();
				standportInst.setPortId(standTunnel.getAPortId());
				standportInst = portService.select(standportInst).get(0);//备用port

				for (Lsp particular : mainTunnel.getLspParticularList()) {//遍历主用tunnel
					if (particular.getASiteId() == siteId) {
						protectTunnelObject.setMainLspID(particular.getAtunnelbusinessid());
					}

				}

				for (Lsp particular : standTunnel.getLspParticularList()) {//遍历保护tunnel
					if (particular.getASiteId() == siteId) {
						protectTunnelObject.setStandbyLspID(particular.getAtunnelbusinessid());
					}
				}
				protectTunnelObject.setLspLogicId(standTunnel.getAprotectId());
			}

			if (mainTunnel.getZSiteId() == siteId) {//Z端
				mainportInst = new PortInst();
				mainportInst.setPortId(mainTunnel.getZPortId());
				if(portService.select(mainportInst) != null && portService.select(mainportInst).size()>0){
					mainportInst = portService.select(mainportInst).get(0);//主用port
				}
				
				standportInst = new PortInst();
				standportInst.setPortId(standTunnel.getZPortId());
				if(portService.select(standportInst) != null && portService.select(standportInst).size()>0){
					standportInst = portService.select(standportInst).get(0);//备用port
				}

				for (Lsp particular : mainTunnel.getLspParticularList()) {//遍历主用tunnel
					if (particular.getZSiteId() == siteId) {
						protectTunnelObject.setMainLspID(particular.getZtunnelbusinessid());
					}
				}

				for (Lsp particular : standTunnel.getLspParticularList()) {//遍历保护tunnel
					if (particular.getZSiteId() == siteId) {
						protectTunnelObject.setStandbyLspID(particular.getZtunnelbusinessid());
					}
				}
				protectTunnelObject.setLspLogicId(standTunnel.getZprotectId());
			}
			if ("493".equals(mainTunnel.getTunnelType())) {// SNCP保护
				protectTunnelObject.setProtectionType(3);
			} else {// 普通1:1保护
				protectTunnelObject.setProtectionType(2);
			}
			protectTunnelObject.setId(mainTunnel.getTunnelId());
			protectTunnelObject.setMainPort(mainportInst.getNumber());
			protectTunnelObject.setObjProtectType(0 + "");
			protectTunnelObject.setMainSlot(10);
			protectTunnelObject.setProtractedTime(mainTunnel.getDelaytime());
			protectTunnelObject.setStandbySlot(10);
			protectTunnelObject.setStandbyPort(standportInst.getNumber());
			protectTunnelObject.setReturnType(mainTunnel.getProtectBack());
			protectTunnelObject.setOperationType(0);
			protectTunnelObject.setWaittime(mainTunnel.getWaittime());
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
		}
		return protectTunnelObject;
	}
}
