package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.OamTypeEnum;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.TMCOAMBugControlInfoObject;
import com.nms.drive.service.bean.TMCOAMBugControlObject;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class TMCOAMCongigWHServiceImpl extends WHOperationBase implements OperationServiceI {

	@Override
	public String excutionDelete(List objectList) throws Exception {
//		OamInfo oamInfo = null;
		OamInfoService_MB oamInfoService = null;
		List<Integer> siteIdList = null;
		Map<Integer, ActionObject> operationObjectBefore=null;
		OperationObject operationObject = null;
		OperationObject operationObjectResult=null;
		List<OamInfo> oamInfoList = null;
		Map<Integer, OamInfo> oamInfoBack = new HashMap<Integer, OamInfo>();
		List<OamInfo> oamInfoListBack = new ArrayList<OamInfo>();
		try {
			oamInfoList = objectList;
			siteIdList = new ArrayList<Integer>();
			for(OamInfo oamInfo : oamInfoList){
				siteIdList.add(oamInfo.getOamMep().getSiteId());
			}
//			siteId = oamInfoList.get(0).getOamMep().getSiteId();
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
//			operationObjectBefore = this.getOperationBefore(siteIdList);
			super.lockSite(siteIdList, SiteLockTypeUtil.TMSOAM_INSERT);
			for(OamInfo info : oamInfoList){
				oamInfoService.deleteById(info);
			}
//			oamInfoList = oamInfoService.queryBySiteId(oamInfoList.get(0), OamTypeEnum.AMEP);
			for (int siteId : siteIdList) {
				operationObject = this.getOperationObject(siteId);
				super.sendAction(operationObject);
				operationObjectResult = super.verification(operationObject);
				if (!operationObjectResult.isSuccess()) {
					//回滚
					for (OamInfo oamInfo : oamInfoList) {
						this.excutionInsert(oamInfo);
					}
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
				oamInfoListBack.add(oamInfoBack.get(siteId));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(oamInfoService);
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		OamInfo oamInfo = (OamInfo)object;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		try {
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(oamInfo.getOamMep().getSiteId());
			super.lockSite(siteIdList, SiteLockTypeUtil.TMSOAM_INSERT);
			operationObjectAfter = this.getOperationObject(oamInfo.getOamMep().getSiteId());
			super.sendAction(operationObjectAfter);
			operationObjectResult = super.verification(operationObjectAfter);
			if (operationObjectResult.isSuccess()) {
				super.clearLock(siteIdList);
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				super.clearLock(siteIdList);
				return super.getErrorMessage(operationObjectResult);
			}
		} finally {
			super.clearLock(siteIdList);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public String excutionInsert(Object object) throws Exception {
		OamInfo oamInfo = (OamInfo) object;
		OamInfoService_MB oamService = null;
		List<Integer> siteIdList = null;
		List<OamInfo> oamInfoList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		try {
			oamService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(oamInfo.getOamMep().getSiteId());
			super.lockSite(siteIdList, SiteLockTypeUtil.TMCOAM_INSERT);
			oamService.saveOrUpdate(oamInfo);
//			oamInfoList = oamService.queryBySiteId(oamInfo, OamTypeEnum.AMEP);
			operationObjectAfter = this.getOperationObject(oamInfo.getOamMep().getSiteId());
			super.sendAction(operationObjectAfter);
			operationObjectResult = super.verification(operationObjectAfter);
			if (operationObjectResult.isSuccess()) {
				super.clearLock(siteIdList);
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				//数据回滚
				oamService.delete(oamInfo);
				super.clearLock(siteIdList);
				return super.getErrorMessage(operationObjectResult);
			}

		} catch (Exception e) {
			throw e;
		}finally{
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(oamService);
		}

	}

	private OperationObject getOperationObject(int siteId) {
		OperationObject operationObject = null;
		NEObject neObject = null;
		ActionObject actionObject = null;
		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			SiteUtil siteUtil = new SiteUtil();
			if("0".equals(siteUtil.SiteTypeUtil(siteId)+"")){
				actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.setType("tmcOAMConfig");
				actionObject.setTmcOAMBugControlObject(this.getTMCOAMBugControlObkectList(siteId));
			}else{
				actionObject.setStatus(ResultString.CONFIG_SUCCESS);
			}
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;

	}

	private TMCOAMBugControlObject getTMCOAMBugControlObkectList(int siteId) throws Exception {
		TMCOAMBugControlObject tmcoamBugControlObject = null;
		List<TMCOAMBugControlInfoObject> infoObjectList = null;
		TMCOAMBugControlInfoObject infoObject = null;
		TunnelService_MB tunnelService = null;
		Tunnel tunnel = null;
		PwInfoService_MB pwInfoService = null;
		PwInfo pwInfo = null;
		Lsp lsp = null;
		OamInfoService_MB oamInfoService = null;
		List<OamInfo> oamInfoList = null;
		OamInfo oam = null;
		OamMepInfo mep = null;
		int id = 1;

		try {
			oam = new OamInfo();
			mep = new OamMepInfo();
			mep.setObjType("PW_TEST");
			mep.setSiteId(siteId);
			oam.setOamMep(mep);
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			oamInfoList = oamInfoService.queryBySiteId(oam, OamTypeEnum.AMEP);
			tmcoamBugControlObject = new TMCOAMBugControlObject();
			infoObjectList = new ArrayList<TMCOAMBugControlInfoObject>();
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			for (OamInfo oamInfo : oamInfoList) {
				infoObject = new TMCOAMBugControlInfoObject();

				pwInfo = new PwInfo();
				pwInfo.setPwId(oamInfo.getOamMep().getObjId());
				pwInfo = pwInfoService.selectBypwid_notjoin(pwInfo);
				tunnel = new Tunnel();
				tunnel.setTunnelId(pwInfo.getTunnelId());
				tunnel = tunnelService.select_nojoin(tunnel).get(0);
				
				if(tunnel.getASiteId() == oamInfo.getOamMep().getSiteId()){
					lsp = tunnel.getLspParticularList().get(0);
				}else{
					lsp = tunnel.getLspParticularList().get(tunnel.getLspParticularList().size()-1);
				}
				
				int lspId = oamInfo.getOamMep().getSiteId() == lsp.getASiteId() ? lsp.getAtunnelbusinessid() : 
					lsp.getZtunnelbusinessid();
				infoObject.setLspId(lspId);
				System.out.println("LSP Id  " + lspId);
				int pwId = oamInfo.getOamMep().getSiteId() == pwInfo.getASiteId() ? pwInfo.getApwServiceId() : 
					pwInfo.getZpwServiceId();
				infoObject.setPwid(pwId);
				System.out.println("PW Id " + pwId);
				infoObject.setLspTC(getMel(oamInfo.getOamMep().getLspTc()));
				System.out.println("lsptc  " + oamInfo.getOamMep().getLspTc());
				infoObject.setPwTC(getMel(oamInfo.getOamMep().getPwTc()));
				infoObject.setResrveTC("00");
				System.out.println("PWTC   " + oamInfo.getOamMep().getPwTc() + "");
				infoObject.setMel(oamInfo.getOamMep().getMel());
				System.out.println("mel  " + oamInfo.getOamMep().getMel());
				infoObject.setLoopbackEnable(oamInfo.getOamMep().isRingEnable() ? "1" : "0");
				System.out.println("环回帧发送使能  " + (oamInfo.getOamMep().isRingEnable() ? "1" : "0"));
				infoObject.setLoopbackCycle(getCycle(oamInfo.getOamMep().getRingCycle()));
				System.out.println("环回帧发送周期  " + getCycle(oamInfo.getOamMep().getRingCycle()));
				infoObject.setLoopbackTest(oamInfo.getOamMep().getRingTestWay() + "");
				System.out.println("环回测试方式  " + oamInfo.getOamMep().getRingTestWay());
				infoObject.setLoopbackTLV(getLoopBackTLV(oamInfo.getOamMep().getOffLineTestTLV()));
				System.out.println("离线方式测试TLV类型  " + getLoopBackTLV(oamInfo.getOamMep().getOffLineTestTLV()));
				infoObject.setLoopback_tlvLength(oamInfo.getOamMep().getRingTLVLength());
				System.out.println("(环回帧配置)TLV长度   " + oamInfo.getOamMep().getRingTLVLength());
				infoObject.setLoopback_tlvtest(oamInfo.getOamMep().getRingTLVInfo());
				System.out.println("(环回帧配置)数据TLV测试内容: " + oamInfo.getOamMep().getRingTLVInfo());
				infoObject.setTstEnable(oamInfo.getOamMep().isTstEnable() ? "1" : "0");
				System.out.println(" TST帧发送使能 " + (oamInfo.getOamMep().isTstEnable() ? "1" : "0"));
				infoObject.setTstCycle(getCycle(oamInfo.getOamMep().getTstCycle()));
				System.out.println("TST帧发送周期  " + getCycle(oamInfo.getOamMep().getTstCycle()));
				infoObject.setTstTLV(getLoopBackTLV(oamInfo.getOamMep().getTstTLVType()));
				System.out.println("测试TLV类型: " + getLoopBackTLV(oamInfo.getOamMep().getTstTLVType()));
				infoObject.setTst_tlvLength(oamInfo.getOamMep().getTstTLVLength());
				System.out.println("(TST帧配置)TLV长度:" + oamInfo.getOamMep().getTstTLVLength());
				infoObject.setLckEnable(oamInfo.getOamMep().isLck() ? 1 : 0);
				System.out.println("LCK帧发送使能  " + (oamInfo.getOamMep().isLck() ? 1 : 0));
				infoObject.setLmEnable(oamInfo.getOamMep().isLm() ? "1" : "0");
				System.out.println("LM帧发送使能: " + (oamInfo.getOamMep().isLm() ? "1" : "0"));
				infoObject.setLmCycle(oamInfo.getOamMep().getLmCycle() + "");
				System.out.println("LM帧发送周期  " + oamInfo.getOamMep().getLmCycle() + "");
				infoObject.setDmEnable(oamInfo.getOamMep().isDm() ? "1" : "0");
				System.out.println(" DM帧发送使能: " + (oamInfo.getOamMep().isDm() ? "1" : "0"));
				infoObject.setDmCycle(oamInfo.getOamMep().getDmCycle() + "");
				System.out.println("DM帧发送周期：" + oamInfo.getOamMep().getDmCycle() + "");
				infoObject.setLbTTL(oamInfo.getOamMep().getLbTTL());
				System.out.println("lbTTL: " + oamInfo.getOamMep().getLbTTL());
				infoObject.setSubclausesId(id);
				infoObject.setSourceMEP(oamInfo.getOamMep().getLocalMepId());
				System.out.println("本端mepId: "+oamInfo.getOamMep().getLocalMepId());
				infoObject.setEquityMEP(oamInfo.getOamMep().getRemoteMepId());
				System.out.println("远端mepId: "+oamInfo.getOamMep().getRemoteMepId());
				infoObjectList.add(infoObject);
				id++;
			}
			tmcoamBugControlObject.setTmcOamBugControlObjectlist(infoObjectList);
			return tmcoamBugControlObject;
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(oamInfoService);
			UiUtil.closeService_MB(pwInfoService);
			UiUtil.closeService_MB(tunnelService);
		}
	}

	private String getCycle(int cycle) {
		String str = "";
		if (cycle == 1) {
			str = "001";
		} else if (cycle == 10) {
			str = "010";
		} else if (cycle == 11) {
			str = "011";
		} else if (cycle == 100) {
			str = "100";
		}
		return str;
	}

	/**
	 * cycle的转换
	 * 
	 * @param cycle
	 * @return
	 */
	public String getMel(int mel) {
		String str = "";
		str = Integer.toBinaryString(mel);
		for (int i = str.length(); i < 3; i++) {
			str = 0 + str;
		}
		return str;
	}
	
	private String getLoopBackTLV(int value) {
		String str = "";
		if (value == 1) {
			str = "01";
		} else if (value == 11) {
			str = "11";
		}
		return str;
	}

	@Override
	public Object synchro(int siteId) {
		return null;
	}
}
