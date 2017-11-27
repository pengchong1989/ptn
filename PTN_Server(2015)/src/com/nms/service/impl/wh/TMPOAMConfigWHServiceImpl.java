package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.OamTypeEnum;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.TMPOAMBugControlInfoObject;
import com.nms.drive.service.bean.TMPOAMBugControlObject;
import com.nms.model.ptn.oam.OamInfoService_MB;
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

public class TMPOAMConfigWHServiceImpl extends WHOperationBase implements
		OperationServiceI {

	@Override
	public String excutionDelete(List objectList) throws Exception {
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

	@Override
	public String excutionInsert(Object object) throws Exception {
		OamInfo oamInfo = (OamInfo)object;
		OamInfoService_MB oamService = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		List<OamInfo> oamInfoList = null;
		try {
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(oamInfo.getOamMep().getSiteId());
			super.lockSite(siteIdList, SiteLockTypeUtil.TMPOAM_INSERT);
			oamService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
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
		} finally {
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
				actionObject.setActionId(super.getActionId(operationObject
						.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.setType("tmpOAMConfig");
				actionObject.setTmpOAMBugControlObject(this
						.getTMPOAMBugControlObkectList(siteId));
			}else{
				actionObject.setStatus(ResultString.CONFIG_SUCCESS);
			}
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			neObject = null;
			actionObject = null;
		}

		return operationObject;
	}

	private TMPOAMBugControlObject getTMPOAMBugControlObkectList(int siteId) throws Exception {
		TMPOAMBugControlObject tmpoamBugControlObject = null;
		List<TMPOAMBugControlInfoObject> infoObjectList = null;
		TMPOAMBugControlInfoObject infoObject = null;
		OamInfoService_MB oamInfoService = null;
		List<OamInfo> oamInfoList = null;
		OamInfo oam = null;
		OamMepInfo mep = null;
		int id = 1;
		TunnelService_MB sevice = null;
		try {
			tmpoamBugControlObject = new TMPOAMBugControlObject();
			infoObjectList=new ArrayList<TMPOAMBugControlInfoObject>();
			oam = new OamInfo();
			mep = new OamMepInfo();
			mep.setObjType("TUNNEL_TEST");
			mep.setSiteId(siteId);
			oam.setOamMep(mep);
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			sevice = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			oamInfoList = oamInfoService.queryBySiteId(oam, OamTypeEnum.AMEP);
			for (OamInfo oamInfo : oamInfoList) {
				infoObject = new TMPOAMBugControlInfoObject();
				infoObject.setSubclausesId(id);
				infoObject.setLspId(this.getLspId(oamInfo.getOamMep(), sevice));
				System.out.println("LSP ID  " + this.getLspId(oamInfo.getOamMep(), sevice));
				infoObject.setTc(getMel(oamInfo.getOamMep().getLspTc()));
				System.out.println("tc  " + getMel(oamInfo.getOamMep().getLspTc()));
				infoObject.setSourceMEP(oamInfo.getOamMep().getLocalMepId());
				System.out.println("源mepId "+oamInfo.getOamMep().getLocalMepId());
				infoObject.setEquityMEP(oamInfo.getOamMep().getRemoteMepId());
				System.out.println("对端mepId "+oamInfo.getOamMep().getRemoteMepId());
				infoObject.setMel(getMel(oamInfo.getOamMep().getMel()));
				System.out.println("mel  " + getMel(oamInfo.getOamMep().getMel()));
				infoObject
						.setLoopbackEnable(oamInfo.getOamMep().isRingEnable() ? "1"
								: "0");
				System.out.println("环回帧发送使能  "
						+ (oamInfo.getOamMep().isRingEnable() ? "1" : "0"));
				infoObject.setLoopbackCycle(getCycle(oamInfo.getOamMep()
						.getRingCycle()));
				System.out.println("环回帧发送周期  "
						+ getCycle(oamInfo.getOamMep().getRingCycle()));
				infoObject.setLoopbackTest(oamInfo.getOamMep().getRingTestWay()
						+ "");
				System.out.println("环回测试方式  "
						+ oamInfo.getOamMep().getRingTestWay());
				infoObject.setLoopbackTLV(getLoopBackTLV(oamInfo.getOamMep()
						.getOffLineTestTLV()));
				System.out.println("离线方式测试TLV类型  "
						+ getLoopBackTLV(oamInfo.getOamMep()
								.getOffLineTestTLV()));
				infoObject.setLoopback_tlvLength(oamInfo.getOamMep()
						.getRingTLVLength());
				System.out.println("(环回帧配置)TLV长度   "
						+ oamInfo.getOamMep().getRingTLVLength());
				infoObject.setLoopback_tlvtest(oamInfo.getOamMep()
						.getRingTLVInfo());
				System.out.println("(环回帧配置)数据TLV测试内容: "
						+ oamInfo.getOamMep().getRingTLVInfo());
				infoObject.setTstEnable(oamInfo.getOamMep().isTstEnable() ? "1"
						: "0");
				System.out.println(" TST帧发送使能 "
						+ (oamInfo.getOamMep().isTstEnable() ? "1" : "0"));
				infoObject.setTstCycle(getCycle(oamInfo.getOamMep()
						.getTstCycle()));
				System.out.println("TST帧发送周期  "
						+ getCycle(oamInfo.getOamMep().getTstCycle()));
				infoObject.setTstTLV(getLoopBackTLV(oamInfo.getOamMep()
						.getTstTLVType()));
				System.out.println("测试TLV类型: "
						+ getLoopBackTLV(oamInfo.getOamMep().getTstTLVType()));
				infoObject.setTst_tlvLength(oamInfo.getOamMep()
						.getTstTLVLength());
				System.out.println("(TST帧配置)TLV长度:"
						+ oamInfo.getOamMep().getTstTLVLength());
				infoObject.setLckEnable(oamInfo.getOamMep().isLck() ? 1 : 0);
				System.out.println("LCK帧发送使能  "
						+ (oamInfo.getOamMep().isLck() ? 1 : 0));
				infoObject.setLmEnable(oamInfo.getOamMep().isLm() ? "1" : "0");
				System.out.println("LM帧发送使能: "
						+ (oamInfo.getOamMep().isLm() ? "1" : "0"));
				infoObject.setLmCycle(oamInfo.getOamMep().getLmCycle() + "");
				System.out.println("LM帧发送周期  "
						+ oamInfo.getOamMep().getLmCycle() + "");
				infoObject.setDmEnable(oamInfo.getOamMep().isDm() ? "1" : "0");
				System.out.println(" DM帧发送使能: "
						+ (oamInfo.getOamMep().isDm() ? "1" : "0"));
				infoObject.setDmCycle(oamInfo.getOamMep().getDmCycle() + "");
				System.out.println("DM帧发送周期："
						+ oamInfo.getOamMep().getDmCycle() + "");
				System.out.println("LT使能："+oamInfo.getOamMep().getLtEnable());
				System.out.println("LT EXP："+oamInfo.getOamMep().getLtEXP());
				System.out.println("LT TTL："+oamInfo.getOamMep().getLtTTL());
				infoObject.setLbTTL(oamInfo.getOamMep().getLbTTL());
				System.out.println("lbTTL: " + oamInfo.getOamMep().getLbTTL());
				infoObject.setLtEnable(oamInfo.getOamMep().getLtEnable());
				infoObject.setLtEXP(oamInfo.getOamMep().getLtEXP());
				infoObject.setLtTTL(oamInfo.getOamMep().getLtTTL());
				infoObject.setDmlength(oamInfo.getOamMep().getDmlength());
				System.out.println("dmlenth:"+oamInfo.getOamMep().getDmlength());
				infoObjectList.add(infoObject);
				id++;
			}
			tmpoamBugControlObject
					.setTmpOamBugControlObjectlist(infoObjectList);

			return tmpoamBugControlObject;
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(oamInfoService);
			UiUtil.closeService_MB(sevice);
		}
	}

	private int getLspId(OamMepInfo oamMep, TunnelService_MB sevice) {
		Tunnel tunnel = null;
		List<Tunnel> list = null;
		Lsp lsp = null;
		int lspId = 0;
		try {
			tunnel = new Tunnel();
			tunnel.setTunnelId(oamMep.getObjId());
			list = sevice.selectNodeByTunnelId(tunnel);
			tunnel = list.get(0);
			
			if(tunnel.getASiteId() == oamMep.getSiteId()){
				lsp = tunnel.getLspParticularList().get(0);
			}else{
				lsp = tunnel.getLspParticularList().get(tunnel.getLspParticularList().size()-1);
			}
			
			lspId = oamMep.getSiteId() == lsp.getASiteId() ? lsp.getAtunnelbusinessid() : 
				lsp.getZtunnelbusinessid();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return lspId;
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

	private String getLoopBackTLV(int value) {
		String str = "";
		if (value == 1) {
			str = "01";
		} else if (value == 11) {
			str = "11";
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
	
	@Override
	public Object synchro(int siteId) {
		return null;
	}
}
