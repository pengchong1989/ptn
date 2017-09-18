package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EPwType;
import com.nms.db.enums.EQosDirection;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.db.enums.QosTemplateTypeEnum;
import com.nms.drivechenxiao.service.bean.pweth.PwEthObject;
import com.nms.drivechenxiao.service.bean.pwpdh.PwPdhObject;
import com.nms.drivechenxiao.service.bean.pwsdh.PwSdhObject;
import com.nms.drivechenxiao.service.bean.qos.QosObject;
import com.nms.drivechenxiao.service.bean.qos.llsp.LLSPQosObject;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.LspInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.ptn.qos.QosRelevanceService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class PwCXServiceImpl extends CXOperationBase implements OperationServiceI {
	@Override
	public String excutionInsert(Object object) throws Exception {
		PwInfo pwinfo = null;
		OperationObject operationObject = null;
		String errorMessage = null;
		QosRelevanceService_MB qosRelevanceService=null;
		try {
			pwinfo = (PwInfo) object;
			
			operationObject = this.getOperationObject(pwinfo, TypeAndActionUtil.ACTION_INSERT);
			if (operationObject.getCxActionObjectList().size() > 0) {
				super.sendAction(operationObject);
				super.verification(operationObject);
				if (operationObject.isSuccess()) {
					
					//修改qos状态
					qosRelevanceService=(QosRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSRELEVANCE);
					qosRelevanceService.updateQosStatus(pwinfo);
					
					errorMessage = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					errorMessage = super.getErrorMessage(operationObject);
					operationObject.setCxActionObjectList(super.getSuccess(operationObject, TypeAndActionUtil.ACTION_DELETE));
					super.sendAction(operationObject);
				}
			} else {
				errorMessage = ResultString.CONFIG_SUCCESS;
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(qosRelevanceService);
		}
		return errorMessage;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		PwInfo pwInfo = null;
		OperationObject operationObject = null;
		String result = null;
		String action = null;
		QosRelevanceService_MB qosRelevanceService=null;
		try {
			pwInfo = (PwInfo) object;
			action = this.getActionUpdate(pwInfo);
			if ("".equals(action)) {
				result = ResultString.CONFIG_SUCCESS;
			} else {
				operationObject = this.getOperationObject(pwInfo, action);
				if (operationObject.getCxActionObjectList().size() > 0) {
					super.sendAction(operationObject);
					super.verification(operationObject);
					if (operationObject.isSuccess()) {
						//修改qos状态
						qosRelevanceService=(QosRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSRELEVANCE);
						qosRelevanceService.updateQosStatus(pwInfo);
						// this.pwInfoService.update(pwInfo); kk
						result = operationObject.getCxActionObjectList().get(0).getStatus();
					} else {
						result = super.getErrorMessage(operationObject);
					}
				} else {
					result = ResultString.CONFIG_SUCCESS;
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(qosRelevanceService);
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String excutionDelete(List objectList) throws Exception {
		List<PwInfo> pwInfoList = null;
		OperationObject operationObject = null;
		String errorMessage = null;
		try {
			pwInfoList = objectList;
			operationObject = this.getOperationMoreObject(pwInfoList, TypeAndActionUtil.ACTION_DELETE);

			if (operationObject.getCxActionObjectList().size() > 0) {
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
					
					errorMessage = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					errorMessage = super.getErrorMessage(operationObject);
					operationObject.setCxActionObjectList(super.getSuccess(operationObject, TypeAndActionUtil.ACTION_INSERT));
					super.sendAction(operationObject);
				}
			} else {
				errorMessage = ResultString.CONFIG_SUCCESS;
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			pwInfoList = null;
			operationObject = null;
		}
		return errorMessage;
	}


	/**
	 * 删除pw
	 * 
	 * @param pwInfoList
	 */
	@SuppressWarnings("unused")
	private void deletePwList(List<PwInfo> pwInfoList) {
		PwInfoService_MB pwInfoService = null;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwInfoService.delete(pwInfoList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(pwInfoService);
		}
	}

	/**
	 * 获取OperationObject对象
	 * 
	 * @param elineInfo
	 * @param action
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(PwInfo pwInfo, String action) throws Exception {
		OperationObject operationObject = null;
		String type = null;
		int siteId=0;
		try {
			operationObject = new OperationObject();
			SiteUtil siteUtil=new SiteUtil();
			if (pwInfo.getIsSingle() == 0) {
				// 如果网元是晨晓的 转换对象
				if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(pwInfo.getASiteId())&&0==siteUtil.SiteTypeUtil(pwInfo.getASiteId())) {
					operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, pwInfo, action, "a"));
				}
				if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(pwInfo.getZSiteId())&&0==siteUtil.SiteTypeUtil(pwInfo.getZSiteId())) {
					operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, pwInfo, action, "z"));
				}
			} else {
				if (pwInfo.getASiteId() != 0) {
					type = "a";
					siteId=pwInfo.getASiteId();
				} else if(pwInfo.getZSiteId() != 0){
					type = "z";
					siteId=pwInfo.getZSiteId();
				}
				if(siteId > 0){
					if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(siteId)&&0==siteUtil.SiteTypeUtil(siteId)) {
						operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, pwInfo, action, type));
					}
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{			
			 type = null;
		}
		return operationObject;
	}

	/**
	 * 获取多个OperationObject对象
	 * 
	 * @param elineInfo
	 * @param action
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationMoreObject(List<PwInfo> pwInfoList, String action) throws Exception {
		OperationObject operationObject = null;
		String type = null;
		int siteId=0;
		try {
			operationObject = new OperationObject();
			SiteUtil siteUtil=new SiteUtil();
			for (PwInfo pwInfo : pwInfoList) {

				if (pwInfo.getIsSingle() == 0&&0!=pwInfo.getASiteId()&&0!=pwInfo.getZSiteId()) {
					if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(pwInfo.getASiteId())&&0==siteUtil.SiteTypeUtil(pwInfo.getASiteId())) {
						operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, pwInfo, action, "a"));
					}
					if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(pwInfo.getZSiteId())&&0==siteUtil.SiteTypeUtil(pwInfo.getZSiteId())) {
						operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, pwInfo, action, "z"));
					}
				} else {
					if (pwInfo.getASiteId() != 0) {
						type = "a";
						siteId=pwInfo.getASiteId();
					} else {
						type = "z";
						siteId=pwInfo.getZSiteId();
					}
					if(siteId >0){
						if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(siteId)&&0==siteUtil.SiteTypeUtil(siteId)) {
							operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, pwInfo, action, type));
						}
					}
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			type = null;
		}
		return operationObject;
	}

	private CXActionObject getCXActionObject(OperationObject operationObject, PwInfo pwInfo, String action, String type) throws Exception {
		CXActionObject cxActionObject = null;
		int siteId = 0;
		try {
			cxActionObject = new CXActionObject();
			if ("a".equals(type)) {
				siteId = pwInfo.getASiteId();
			} else {
				siteId = pwInfo.getZSiteId();
			}
			cxActionObject.setQosObject(this.getqosObject(pwInfo, type));
			if (pwInfo.getType() == EPwType.ETH) {
				cxActionObject.setType(TypeAndActionUtil.TYPE_PWETH);
				cxActionObject.setPwEthObject(this.convertPwEthObject(pwInfo, type, action));
				

				
			} else if (pwInfo.getType() == EPwType.PDH) {
				cxActionObject.setType(TypeAndActionUtil.TYPE_PWPDH);
				cxActionObject.setPwPdhObject(this.convertPwPdhObject(pwInfo, type, action, cxActionObject.getQosObject()));
			} else if (pwInfo.getType() == EPwType.SDH) {
				cxActionObject.setType(TypeAndActionUtil.TYPE_PWSDH);
				cxActionObject.setPwSdhObject(this.convertPwSDHObject(pwInfo, type, action, cxActionObject.getQosObject()));
			} else if (pwInfo.getType() == EPwType.SDH_PDH) {
				if ("a".equals(type)) {
					cxActionObject.setType(TypeAndActionUtil.TYPE_PWSDH);
					cxActionObject.setPwSdhObject(this.convertPwSDHObject(pwInfo, type, action, cxActionObject.getQosObject()));
				} else {
					cxActionObject.setType(TypeAndActionUtil.TYPE_PWPDH);
					cxActionObject.setPwPdhObject(this.convertPwPdhObject(pwInfo, type, action, cxActionObject.getQosObject()));
				}
			} else if (pwInfo.getType() == EPwType.PDH_SDH) {
				if ("z".equals(type)) {
					cxActionObject.setType(TypeAndActionUtil.TYPE_PWSDH);
					cxActionObject.setPwSdhObject(this.convertPwSDHObject(pwInfo, type, action, cxActionObject.getQosObject()));
				} else {
					cxActionObject.setType(TypeAndActionUtil.TYPE_PWPDH);
					cxActionObject.setPwPdhObject(this.convertPwPdhObject(pwInfo, type, action, cxActionObject.getQosObject()));
				}
			}

			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setAction(action);
			cxActionObject.setCxNeObject(super.getCXNEObject(siteId));
			cxActionObject.setOamObject(super.convertOamObject_mep(EServiceType.PW.toString(), pwInfo.getPwId(), siteId, pwInfo.getOamList(), action));

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return cxActionObject;
	}

	/**
	 * 获得PWQOS对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private QosObject getqosObject(PwInfo pwInfo, String type) throws Exception {
		QosObject qosObject = null;
		try {
			if (type.equals("a")) {
				qosObject = super.getqosobject("PW", type, pwInfo.getPwId(), pwInfo.getASiteId());
			} else if (type.equals("z")) {
				qosObject = super.getqosobject("PW", type, pwInfo.getPwId(), pwInfo.getZSiteId());
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return qosObject;

	}

	/**
	 * 获得ethpw对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private PwEthObject convertPwEthObject(PwInfo pwInfo, String type, String action) throws Exception {
		List<Lsp> lspParticularList = null;
		LspInfoService_MB lspParticularService = null;
		String tunnelName = "tunnel/";
		PwEthObject pwEthObject = null;
		QosInfo qosInfo=null;
		int siteId=0;
		try {
			lspParticularService = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO);
			lspParticularList = new ArrayList<Lsp>();
			pwEthObject = new PwEthObject();
			if(0!=pwInfo.getTunnelId()){
				lspParticularList = lspParticularService.selectBytunnelId(pwInfo.getTunnelId());
			}
			if (type.equals("a")) {
				pwEthObject.setName(pwInfo.getApwServiceId() + "");
				if(lspParticularList.size()>0){
					pwEthObject.setCarrierif(tunnelName + lspParticularList.get(0).getAtunnelbusinessid());
				}
				//				pwEthObject.setPeer(this.convertPeerIp(super.getSiteAdress(pwInfo.getZSiteId())));
				pwEthObject.setPeer(pwInfo.getAoppositeId());
				pwEthObject.setInlabel(pwInfo.getInlabelValue() + "");
				pwEthObject.setOutlabel(pwInfo.getOutlabelValue() + "");
				pwEthObject.setOam(super.convertOamObject_mep("PW", pwInfo.getPwId(), pwInfo.getASiteId(), pwInfo.getOamList(), action));
				siteId=pwInfo.getASiteId();
			} else {
				pwEthObject.setName(pwInfo.getZpwServiceId() + "");
				//				pwEthObject.setPeer(this.convertPeerIp(super.getSiteAdress(pwInfo.getASiteId())));
				pwEthObject.setPeer(pwInfo.getZoppositeId());
				if(lspParticularList.size()>0){
					pwEthObject.setCarrierif(tunnelName + lspParticularList.get(lspParticularList.size() - 1).getZtunnelbusinessid());
				}
				pwEthObject.setCarrierif(tunnelName + lspParticularList.get(lspParticularList.size() - 1).getZtunnelbusinessid());
				pwEthObject.setInlabel(pwInfo.getOutlabelValue() + "");
				pwEthObject.setOutlabel(pwInfo.getInlabelValue() + "");
				pwEthObject.setOam(super.convertOamObject_mep("PW", pwInfo.getPwId(), pwInfo.getZSiteId(), pwInfo.getOamList(), action));
				siteId=pwInfo.getZSiteId();
			}
			//取此网元对应的qos
			qosInfo=super.getQosInfo(EServiceType.PW.toString(), pwInfo.getPwId(), siteId);
			pwEthObject.setQos(qosInfo.getQosname());
			//是否创建qos
			if(qosInfo.getStatus() == 1){
				pwEthObject.setCreateQos(true);
			}
			pwEthObject.setAdmin(pwInfo.getPwStatus() == EActiveStatus.UNACTIVITY.getValue() ? "down" : "up");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(lspParticularService);
		}
		return pwEthObject;
	}

	/**
	 * 转换成pdhpw对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private PwPdhObject convertPwPdhObject(PwInfo pwInfo, String type, String action, QosObject qosObject) throws Exception {
		List<Lsp> lspParticularList = null;
		LspInfoService_MB lspParticularService = null;
		String tunnelName = "tunnel/";
		LLSPQosObject l = (LLSPQosObject) qosObject;
		PwPdhObject pwPdhObject = null;
		try {
			lspParticularService = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO);
			lspParticularList = new ArrayList<Lsp>();
			pwPdhObject = new PwPdhObject();
			if(0!=pwInfo.getTunnelId()){
				lspParticularList = lspParticularService.selectBytunnelId(pwInfo.getTunnelId());
			}

			if (type.equals("a")) {
				pwPdhObject.setName(pwInfo.getApwServiceId() + "");
				if(lspParticularList.size()>0){
					pwPdhObject.setCarrierif(tunnelName + lspParticularList.get(0).getAtunnelbusinessid());
				}
				
				//				pwPdhObject.setPeer(this.convertPeerIp(super.getSiteAdress(pwInfo.getZSiteId())));
				pwPdhObject.setPeer(pwInfo.getAoppositeId());
				pwPdhObject.setInlabel(pwInfo.getInlabelValue() + "");
				pwPdhObject.setOutlabel(pwInfo.getOutlabelValue() + "");
				pwPdhObject.setOam(super.convertOamObject_mep("PW", pwInfo.getPwId(), pwInfo.getASiteId(), pwInfo.getOamList(), action));
				pwPdhObject.setCos(l.getCos());
			} else {
				pwPdhObject.setName(pwInfo.getZpwServiceId() + "");
				//				pwPdhObject.setPeer(this.convertPeerIp(super.getSiteAdress(pwInfo.getASiteId())));
				pwPdhObject.setPeer(pwInfo.getZoppositeId());
				if(lspParticularList.size()>0){
					pwPdhObject.setCarrierif(tunnelName + lspParticularList.get(lspParticularList.size() - 1).getZtunnelbusinessid());
				}
				
				pwPdhObject.setInlabel(pwInfo.getOutlabelValue() + "");
				pwPdhObject.setOutlabel(pwInfo.getInlabelValue() + "");
				pwPdhObject.setOam(super.convertOamObject_mep("PW", pwInfo.getPwId(), pwInfo.getZSiteId(), pwInfo.getOamList(), action));
				pwPdhObject.setCos(l.getCos());
			}
			if(pwInfo.getPayload()>0){
				pwPdhObject.setPayload(UiUtil.getCodeById(pwInfo.getPayload()).getCodeValue());
			}
			
			// pwPdhObject.setQos("llsppw" + pwInfo.getPwId());
			pwPdhObject.setAdmin(pwInfo.getPwStatus() == EActiveStatus.UNACTIVITY.getValue() ? "down" : "up");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 lspParticularList = null;
			 UiUtil.closeService_MB(lspParticularService);
			 tunnelName = null;
		}
		return pwPdhObject;
	}

	/**
	 * 转换成Sdhpw对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private PwSdhObject convertPwSDHObject(PwInfo pwInfo, String type, String action, QosObject qosObject) throws Exception {
		List<Lsp> lspParticularList = null;
		LspInfoService_MB lspParticularService = null;
		String tunnelName = "tunnel/";
		LLSPQosObject l = (LLSPQosObject) qosObject;
		PwSdhObject pwSdhObject = null;
		try {
			lspParticularService = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO);
			lspParticularList = new ArrayList<Lsp>();
			pwSdhObject = new PwSdhObject();
			if(0!=pwInfo.getTunnelId()){
				lspParticularList = lspParticularService.selectBytunnelId(pwInfo.getTunnelId());
			}
			

			if (type.equals("a")) {
				pwSdhObject.setName(pwInfo.getApwServiceId() + "");
				if(lspParticularList.size()>0){
					pwSdhObject.setCarrierif(tunnelName + lspParticularList.get(0).getAtunnelbusinessid());
				}
				//				pwSdhObject.setPeer(this.convertPeerIp(super.getSiteAdress(pwInfo.getZSiteId())));
				pwSdhObject.setPeer(pwInfo.getAoppositeId());
				pwSdhObject.setInlabel(pwInfo.getInlabelValue() + "");
				pwSdhObject.setOutlabel(pwInfo.getOutlabelValue() + "");
				pwSdhObject.setOam(super.convertOamObject_mep("PW", pwInfo.getPwId(), pwInfo.getASiteId(), pwInfo.getOamList(), action));
				pwSdhObject.setCos(l.getCos());
			} else {
				pwSdhObject.setName(pwInfo.getZpwServiceId() + "");
				//				pwSdhObject.setPeer(this.convertPeerIp(super.getSiteAdress(pwInfo.getASiteId())));
				pwSdhObject.setPeer(pwInfo.getZoppositeId());
				if(lspParticularList.size()>0){
					pwSdhObject.setCarrierif(tunnelName + lspParticularList.get(lspParticularList.size() - 1).getZtunnelbusinessid());
				}
			
				pwSdhObject.setInlabel(pwInfo.getOutlabelValue() + "");
				pwSdhObject.setOutlabel(pwInfo.getInlabelValue() + "");
				pwSdhObject.setOam(super.convertOamObject_mep("PW", pwInfo.getPwId(), pwInfo.getZSiteId(), pwInfo.getOamList(), action));
				pwSdhObject.setCos(l.getCos());
				
			}
			pwSdhObject.setPayload(UiUtil.getCodeById(pwInfo.getPayload()).getCodeValue());
		
			pwSdhObject.setAdmin(pwInfo.getPwStatus() == EActiveStatus.UNACTIVITY.getValue() ? "down" : "up");

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(lspParticularService);
		}

		return pwSdhObject;
	}

	/**
	 * 修改时获取action
	 * 
	 * @param tunnelUpdate
	 *            修改的tunnel对象
	 * @return
	 * @throws Exception
	 */
	private String getActionUpdate(PwInfo pwinfo) throws Exception {

		try {
			if (pwinfo.getBefore_activity() == EActiveStatus.UNACTIVITY.getValue()) {
				if (pwinfo.getPwStatus() == EActiveStatus.UNACTIVITY.getValue()) {
					return "";
				} else {
					return TypeAndActionUtil.ACTION_INSERT;
				}
			} else {
				if (pwinfo.getPwStatus() == EActiveStatus.UNACTIVITY.getValue()) {
					return TypeAndActionUtil.ACTION_DELETE;
				} else {
					return TypeAndActionUtil.ACTION_UPDATE;
				}
			}

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 根据TunnelId查询tunnel对象
	 * 
	 * @param tunnelID
	 * @return
	 * @throws Exception
	 */
	public int getTunnel(int tunnelID) throws Exception {
		Tunnel tunnel = null;
		TunnelService_MB tunnelservice = null;
		List<Tunnel> tunnelList = null;
		int result = 0;
		try {
			tunnelservice = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnel = new Tunnel();
			tunnel.setTunnelId(tunnelID);
			tunnelList = tunnelservice.select(tunnel);
			if (tunnelList == null || tunnelList.size() != 1) {
				throw new Exception("查询pwinfo出错");
			} else {
				result = tunnelList.get(0).getTunnelStatus();
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			tunnel = null;
			tunnelList = null;
			UiUtil.closeService_MB(tunnelservice);
		}
		return result;
	}

	
	@Override
	public Object synchro(int siteId) throws Exception {
		PwInfoService_MB pwInfoService = null;
		OperationObject operationObject = new OperationObject();
		QosCXServiceImpl qosCXServiceImpl=null;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_PWETH);
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_PWPDH);
			if (!"cxt20a".equals(super.getSiteType(siteId))) {
				this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_PWSDH);
			}
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				//同步前先同步qos
				qosCXServiceImpl=new QosCXServiceImpl();
				qosCXServiceImpl.synchro(siteId);
				//修改pw的激活状态
				pwInfoService.updateActiveStatus(siteId, EActiveStatus.UNACTIVITY.getValue());
				this.synchro_db(operationObject.getCxActionObjectList(), siteId);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(pwInfoService);
		}

		return null;
	}

	/**
	 * 获取OperationObject对象 查询用
	 * 
	 * @author kk
	 * 
	 * @param siteId
	 *            网元id
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void getOperactionObject_select(OperationObject operationObject, int siteId, String type) throws Exception {
		CXActionObject actionObject = new CXActionObject();
		SiteUtil siteUtil=new SiteUtil();
		actionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
		actionObject.setCxNeObject(super.getCXNEObject(siteId));
		actionObject.setType(type);
		actionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
		if(0==siteUtil.SiteTypeUtil(siteId)){
			operationObject.getCxActionObjectList().add(actionObject);
		}
	

	}

	/**
	 * 与数据库做同步
	 * 
	 * @author wangwf
	 * 
	 * @param cxActionObjectList
	 * @param siteId
	 * @throws Exception
	 * @Exception 异常对象
	 */
	private void synchro_db(List<CXActionObject> cxActionObjectList, int siteId) throws Exception {

		if (null == cxActionObjectList || cxActionObjectList.size() == 0) {
			throw new Exception("cxActionObjectList is null");
		}
		PwInfo pwinfo = null;
		Lsp lsp = null;
		SynchroUtil synchroUtil = new SynchroUtil();
		for (CXActionObject cxActionObject : cxActionObjectList) {
			if (TypeAndActionUtil.TYPE_PWETH.equals(cxActionObject.getType())) {
				List<PwEthObject> pwEthObjectList = cxActionObject.getPwEthObjectList();
				for (PwEthObject pwEthObject : pwEthObjectList) {
					if(pwEthObject.getCarrierif()!=null&&!pwEthObject.getCarrierif().equals("")){
						lsp = this.getLsp(Integer.parseInt(pwEthObject.getCarrierif()), siteId);
					}
					
					if (null == lsp) {
						ExceptionManage.logDebug("同步PW失败，没有找到tunnel网元",this.getClass());
						continue;
					}
					//					SiteInst siteInst = super.getSiteByIp(pwEthObject.getPeer());
					//					if (null == siteInst) {
					//						ExceptionManage.logDebug("同步PW失败，没有找到" + pwEthObject.getPeer() + "网元");
					//						continue;
					//					}

					pwinfo = new PwInfo();
					pwinfo.setType(EPwType.ETH);
					pwinfo.setQosList(super.qosInfo(pwEthObject.getQos(), siteId));
					pwinfo.setPwStatus(EActiveStatus.ACTIVITY.getValue());
					pwinfo.setIsSingle(1);
					pwinfo.setTunnelId(lsp.getTunnelId());
					pwinfo.setPwName("pw_" + super.getNowMS());
					pwinfo.setJobStatus(pwEthObject.getOper());

					if (lsp.getASiteId() == siteId) {
						pwinfo.setASiteId(siteId);
						pwinfo.setApwServiceId(Integer.parseInt(pwEthObject.getName()));
						//						pwinfo.setZSiteId(siteInst.getSite_Inst_Id());
						pwinfo.setAoppositeId(pwEthObject.getPeer());
						pwinfo.setInlabelValue(Integer.parseInt(pwEthObject.getInlabel()));
						pwinfo.setOutlabelValue(Integer.parseInt(pwEthObject.getOutlabel()));
					} else {
						pwinfo.setZSiteId(siteId);
						pwinfo.setZpwServiceId(Integer.parseInt(pwEthObject.getName()));
						//						pwinfo.setASiteId(siteInst.getSite_Inst_Id());
						pwinfo.setZoppositeId(pwEthObject.getPeer());
						pwinfo.setInlabelValue(Integer.parseInt(pwEthObject.getOutlabel()));
						pwinfo.setOutlabelValue(Integer.parseInt(pwEthObject.getInlabel()));
					}

					if (pwEthObject.getOam().getMegid() != null && !"".equals(pwEthObject.getOam().getMegid())) {
						OamInfo oamInfo = new OamInfo();
						oamInfo = convertOamInfo_mep(pwEthObject.getOam()).get(0);
						oamInfo.setOamType(OamTypeEnum.MEP);
						oamInfo.getOamMep().setObjType(EServiceType.PW.toString());
						oamInfo.getOamMep().setSiteId(siteId);
						pwinfo.getOamList().add(oamInfo);
					}

					synchroUtil.pwInfoSynchro(pwinfo, siteId);
				}
			} else if (TypeAndActionUtil.TYPE_PWPDH.equals(cxActionObject.getType())) {
				List<PwPdhObject> pwPdhObjectList = cxActionObject.getPwPdhObjectList();
				for (PwPdhObject pwPdhObject : pwPdhObjectList) {

					lsp = this.getLsp(Integer.parseInt(pwPdhObject.getCarrierif()), siteId);
					if (null == lsp) {
						ExceptionManage.logDebug("同步PW失败，没有找到tunnel网元",this.getClass());
						continue;
					}
					//					SiteInst siteInst = super.getSiteByIp(pwPdhObject.getPeer());
					//					if (null == siteInst) {
					//						ExceptionManage.logDebug("同步PW失败，没有找到" + pwPdhObject.getPeer() + "网元");
					//						continue;
					//					}

					pwinfo = new PwInfo();
					pwinfo.setType(EPwType.PDH);
					pwinfo.setQosList(this.getQosInfo(Integer.parseInt(pwPdhObject.getCos())));
					pwinfo.setPwStatus(EActiveStatus.ACTIVITY.getValue());
					pwinfo.setIsSingle(1);
					pwinfo.setTunnelId(lsp.getTunnelId());
					pwinfo.setPwName("pw_" + super.getNowMS());
					pwinfo.setJobStatus(pwPdhObject.getOper());

					if (lsp.getASiteId() == siteId) {
						pwinfo.setASiteId(siteId);
						pwinfo.setApwServiceId(Integer.parseInt(pwPdhObject.getName()));
						//						pwinfo.setZSiteId(siteInst.getSite_Inst_Id());
						pwinfo.setAoppositeId(pwPdhObject.getPeer());
						pwinfo.setInlabelValue(Integer.parseInt(pwPdhObject.getInlabel()));
						pwinfo.setOutlabelValue(Integer.parseInt(pwPdhObject.getOutlabel()));
					} else {
						pwinfo.setZSiteId(siteId);
						pwinfo.setZpwServiceId(Integer.parseInt(pwPdhObject.getName()));
						//						pwinfo.setASiteId(siteInst.getSite_Inst_Id());
						pwinfo.setZoppositeId(pwPdhObject.getPeer());
						pwinfo.setInlabelValue(Integer.parseInt(pwPdhObject.getOutlabel()));
						pwinfo.setOutlabelValue(Integer.parseInt(pwPdhObject.getInlabel()));
					}
					
					if (pwPdhObject.getOam().getMegid() != null && !"".equals(pwPdhObject.getOam().getMegid())) {
						OamInfo oamInfo = new OamInfo();
						oamInfo = convertOamInfo_mep(pwPdhObject.getOam()).get(0);
						oamInfo.setOamType(OamTypeEnum.MEP);
						oamInfo.getOamMep().setObjType(EServiceType.PW.toString());
						oamInfo.getOamMep().setSiteId(siteId);
						pwinfo.getOamList().add(oamInfo);
					}

					synchroUtil.pwInfoSynchro(pwinfo, siteId);

				}
			} else if (TypeAndActionUtil.TYPE_PWSDH.equals(cxActionObject.getType())) {
				List<PwSdhObject> pwSdhObjectList = cxActionObject.getPwSdhObjectList();
				for (PwSdhObject pwSdhObject : pwSdhObjectList) {
					lsp = this.getLsp(Integer.parseInt(pwSdhObject.getCarrierif()), siteId);
					if (null == lsp) {
						ExceptionManage.logDebug("同步PW失败，没有找到tunnel网元",this.getClass());
						continue;
					}
					//					SiteInst siteInst = super.getSiteByIp(pwSdhObject.getPeer());
					//					if (null == siteInst) {
					//						ExceptionManage.logDebug("同步PW失败，没有找到" + pwSdhObject.getPeer() + "网元");
					//						continue;
					//					}

					pwinfo = new PwInfo();
					pwinfo.setType(EPwType.SDH);
					pwinfo.setQosList(this.getQosInfo(Integer.parseInt(pwSdhObject.getCos())));
					pwinfo.setPwStatus(EActiveStatus.ACTIVITY.getValue());
					pwinfo.setIsSingle(1);
					pwinfo.setTunnelId(lsp.getTunnelId());
					pwinfo.setPwName("pw_" + super.getNowMS());
					pwinfo.setJobStatus(pwSdhObject.getOper());

					if (lsp.getASiteId() == siteId) {
						pwinfo.setASiteId(siteId);
						pwinfo.setApwServiceId(Integer.parseInt(pwSdhObject.getName()));
						//						pwinfo.setZSiteId(siteInst.getSite_Inst_Id());
						pwinfo.setAoppositeId(pwSdhObject.getPeer());
						pwinfo.setInlabelValue(Integer.parseInt(pwSdhObject.getInlabel()));
						pwinfo.setOutlabelValue(Integer.parseInt(pwSdhObject.getOutlabel()));
					} else {
						pwinfo.setZSiteId(siteId);
						pwinfo.setZpwServiceId(Integer.parseInt(pwSdhObject.getName()));
						//						pwinfo.setASiteId(siteInst.getSite_Inst_Id());
						pwinfo.setZoppositeId(pwSdhObject.getPeer());
						pwinfo.setInlabelValue(Integer.parseInt(pwSdhObject.getOutlabel()));
						pwinfo.setOutlabelValue(Integer.parseInt(pwSdhObject.getInlabel()));
					}
					
					if (pwSdhObject.getOam().getMegid() != null && !"".equals(pwSdhObject.getOam().getMegid())) {
						OamInfo oamInfo = new OamInfo();
						oamInfo = convertOamInfo_mep(pwSdhObject.getOam()).get(0);
						oamInfo.setOamType(OamTypeEnum.MEP);
						oamInfo.getOamMep().setObjType(EServiceType.PW.toString());
						oamInfo.getOamMep().setSiteId(siteId);
						pwinfo.getOamList().add(oamInfo);
					}

					synchroUtil.pwInfoSynchro(pwinfo, siteId);
				}
			}
		}
	}

	/**
	 * 
	 * 
	 * 设置pw对象
	 * 
	 * 
	 * @author wangwf
	 * 
	 * @param businessid
	 * 
	 * @param siteId
	 * 
	 * @param pwinfo
	 * 
	 * @return TunnelId
	 * 
	 * @Exception 异常对象
	 */
	private Lsp getLsp(int businessid, int siteId) throws Exception {

		Tunnel tunnel = new Tunnel();
		List<Lsp> lspParticularList = new ArrayList<Lsp>();
		List<Lsp> lspList = new ArrayList<Lsp>();
		Lsp lsp = new Lsp();
		LspInfoService_MB lspService = null;
		try {
			lspService = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO);
			
			lsp.setAtunnelbusinessid(businessid);
			lspParticularList.add(lsp);
			tunnel.setLspParticularList(lspParticularList);
			lspList = lspService.select_synchro("", siteId, tunnel);
			if (null == lspList || lspList.size() == 0) {
				return null;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(lspService);
			 lsp = null;
		}
		return lspList.get(0);
	}

	/**
	 * 获取pdh和sdh的qos集合
	 * 
	 * @author kk
	 * 
	 * @param cos
	 *            设备返回的策略
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private List<QosInfo> getQosInfo(int cos) {

		List<QosInfo> qosInfoList = new ArrayList<QosInfo>();

		QosInfo qosInfo = new QosInfo();
		qosInfo.setCos(cos);
		qosInfo.setQosType(QosTemplateTypeEnum.LLSP.toString());
		qosInfo.setDirection(EQosDirection.FORWARD.getValue()+"");
		qosInfo.setCir(2448);
		qosInfo.setCbs(-1);
		qosInfo.setEir(0);
		qosInfo.setEbs(0);
		qosInfo.setPir(2448);
		qosInfoList.add(qosInfo);

		qosInfo = new QosInfo();
		qosInfo.setCos(cos);
		qosInfo.setQosType(QosTemplateTypeEnum.LLSP.toString());
		qosInfo.setDirection(EQosDirection.BACKWARD.getValue()+"");
		qosInfo.setCir(2448);
		qosInfo.setCbs(-1);
		qosInfo.setEir(0);
		qosInfo.setEbs(0);
		qosInfo.setPir(2448);
		qosInfoList.add(qosInfo);

		return qosInfoList;

	}
}
