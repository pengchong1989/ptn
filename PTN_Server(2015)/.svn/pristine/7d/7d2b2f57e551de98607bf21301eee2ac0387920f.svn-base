package com.nms.service.impl.wh;

import java.util.List;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.OamTypeEnum;
import com.nms.drive.service.bean.TMSOAMInfoObject;
import com.nms.drive.service.bean.TMSOAMObject;
import com.nms.drive.service.bean.TunnelObject;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class OamWHServiceImpl extends WHOperationBase implements OperationServiceI {

	@SuppressWarnings("null")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		OamInfo oamInfo = null;
		OamInfoService_MB oamInfoService = null;
		OperationServiceI operationServiceI = null;
		PwInfoService_MB pwInfoService = null;
		TunnelService_MB tunnelService = null;
		String result = "";
		try {
			oamInfo = (OamInfo) objectList.get(0);
			if (oamInfo != null) {
				oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
				oamInfoService.deleteById(oamInfo);
			}
			if(oamInfo.getOamMep() != null){
				if("SECTION".equals(oamInfo.getOamMep().getObjType())){
					operationServiceI = new TmsOamWHServiceImpl();
					result = operationServiceI.excutionDelete(objectList);
				}else if("TUNNEL".equals(oamInfo.getOamMep().getObjType())){
					tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
					Tunnel tunnel = tunnelService.selectByTunnelIdAndSiteId(oamInfo.getOamMep().getSiteId(), oamInfo.getOamMep().getServiceId());
					operationServiceI = new TunnelWHServiceImpl();
					result = operationServiceI.excutionUpdate(tunnel);
				}else if("PW".equals(oamInfo.getOamMep().getObjType())){
					pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
					PwInfo pwInfoSel = pwInfoService.select(oamInfo.getOamMep().getSiteId(), oamInfo.getOamMep().getObjId());
					operationServiceI = new PwWHServiceImpl();
					result = operationServiceI.excutionUpdate(pwInfoSel);
				}
			}else if(oamInfo.getOamMip() != null){
				tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
				Tunnel tunnel = tunnelService.selectByTunnelIdAndSiteId(oamInfo.getOamMip().getSiteId(), oamInfo.getOamMip().getServiceId());
				operationServiceI = new TunnelWHServiceImpl();
				result = operationServiceI.excutionUpdate(tunnel);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(oamInfoService);
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(pwInfoService);
		}
		return result;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		OamInfo oamInfo = null;
		OamInfoService_MB oamInfoService = null;
		try {
			oamInfo = (OamInfo) object;
			if (oamInfo != null) {
				oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
				oamInfoService.saveOrUpdate(oamInfo);
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		OamInfo oamInfo = null;
		OamInfoService_MB oamInfoService = null;
		try {
			oamInfo = (OamInfo) object;
			if (oamInfo != null) {
				oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
				oamInfoService.saveOrUpdate(oamInfo);
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
	}

	/**
	 * 根据网元ID把该网元下的所有oam封装成tmsOAMObject
	 * 
	 * @param tmsOAMObject
	 * @param siteId
	 * @param aPortId
	 * @return
	 * @throws Exception
	 */
	public TMSOAMObject getOamInfo(TMSOAMObject tmsOAMObject, int siteId, int portId) throws Exception {

		OamInfoService_MB oamInfoService = null;
		OamInfo oamInfo = null;
		OamMepInfo oamMep = null;
		try {
			oamInfo = new OamInfo();
			oamMep = new OamMepInfo();
			oamMep.setObjType("SECTION");
			oamMep.setSiteId(siteId);
			oamMep.setObjId(portId);
			oamInfo.setOamMep(oamMep);
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			oamInfo = oamInfoService.queryByCondition(oamInfo, OamTypeEnum.AMEP);
			if (oamInfo.getOamMep() != null) {
				// tmsOAMObject赋值
				tmsOAMObject = this.getTMSOAMObject(tmsOAMObject, oamInfo);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
		return tmsOAMObject;

	}

	/**
	 * tmsOAMObject的赋值
	 * 
	 * @param tmsOAMObject
	 * @param oamInfo
	 * @return
	 */
	private TMSOAMObject getTMSOAMObject(TMSOAMObject tmsOAMObject, OamInfo oamInfo) {
	
		TMSOAMInfoObject tmsOAMInfoObject = new TMSOAMInfoObject();
		tmsOAMInfoObject.setVsOamEnable(1);
		tmsOAMInfoObject.setMegIcc(oamInfo.getOamMep().getMegIcc());
		tmsOAMInfoObject.setMegUmc(oamInfo.getOamMep().getMegUmc());
		tmsOAMInfoObject.setMepId(oamInfo.getOamMep().getLocalMepId());
		tmsOAMInfoObject.setEqualMepId(oamInfo.getOamMep().getRemoteMepId());
		tmsOAMInfoObject.setCvEnable(oamInfo.getOamMep().isCv() ? "1" : "0");
		tmsOAMInfoObject.setCvCircle(getCycle(oamInfo.getOamMep().getCvCycle()));
		tmsOAMInfoObject.setApsEnable(oamInfo.getOamMep().isAps() ? "1" : "0");
		tmsOAMInfoObject.setSsmEnable(oamInfo.getOamMep().isSsm() ? "1" : "0");
		tmsOAMInfoObject.setSccTestEnable(oamInfo.getOamMep().isSccTest() ? "1" : "0");
		tmsOAMInfoObject.setMel(oamInfo.getOamMep().getMel());
		tmsOAMObject.getTmsOamInfoList().add(tmsOAMInfoObject);
		return tmsOAMObject;
	}

	/**
	 * 根据网元ID把该网元下的所有oam封装成tunnelObject
	 * 
	 * @param oamInfo
	 * @return
	 * @throws Exception
	 */
	public TunnelObject getOamInfo(TunnelObject tunnelObject, int siteId, int serviceId) throws Exception {

		OamInfoService_MB oamInfoService = null;
		OamInfo oamInfo = null;
		OamMepInfo oamMep = null;
		try {
			oamInfo = new OamInfo();
			oamMep = new OamMepInfo();
			oamMep.setObjType("TUNNEL");
			oamMep.setSiteId(siteId);
			oamMep.setObjId(tunnelObject.getTunnelId());
			oamMep.setServiceId(serviceId);
			oamInfo.setOamMep(oamMep);
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			oamInfo = oamInfoService.queryByCondition(oamInfo, OamTypeEnum.AMEP);
			if (oamInfo.getOamMep() != null || oamInfo.getOamMip() != null) {
				// tunnelObject赋值
				tunnelObject = this.getTunnelObject(tunnelObject, oamInfo);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
		return tunnelObject;
	}

	/**
	 * tunnelObject的赋值
	 * 
	 * @param tunnelObject
	 * @param oamInfo
	 * @return
	 */
	public TunnelObject getTunnelObject(TunnelObject tunnelObject, OamInfo oamInfo) {

		tunnelObject.setOam_resore("0");
		tunnelObject.setOam_tmp(oamInfo.getOamType().AMEP.getValue() + "");
		tunnelObject.setOam_mel(oamInfo.getOamMep().getMel() + "");
		tunnelObject.setOam_bao("00");
		tunnelObject.setSourceMEP(oamInfo.getOamMep().getLocalMepId());
		tunnelObject.setEquityMEP(oamInfo.getOamMep().getRemoteMepId());
		tunnelObject.setAspEnabl(oamInfo.getOamMep().isAps() ? 1 : 0);

		tunnelObject.setCvEnabl(oamInfo.getOamMep().isCv() ? "1" : "0");
		tunnelObject.setCvCycle(getCycle(oamInfo.getOamMep().getCvCycle()));
		tunnelObject.setCvReserve("0000");

		return tunnelObject;
	}

	/**
	 * cycle的转换
	 * 
	 * @param cycle
	 * @return
	 */
	public String getCycle(double cycle) {
		String str = "";
		if (cycle == 3.3) {
			str = "001";
		} else if (cycle == 10) {
			str = "010";
		} else if (cycle == 100) {
			str = "011";
		} else if (cycle == 1) {
			str = "100";
		}
		return str;
	}
	@Override
	public Object synchro(int siteId) {
		return null;
	}
}
