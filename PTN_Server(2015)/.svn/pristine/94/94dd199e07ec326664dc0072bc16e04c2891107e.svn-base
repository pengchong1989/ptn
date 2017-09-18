package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EQosDirection;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.db.enums.QosTemplateTypeEnum;
import com.nms.drivechenxiao.service.bean.pwpdh.PwPdhObject;
import com.nms.drivechenxiao.service.bean.pwsdh.PwSdhObject;
import com.nms.drivechenxiao.service.bean.qos.QosObject;
import com.nms.drivechenxiao.service.bean.qos.elsp.ELSPQosObject;
import com.nms.drivechenxiao.service.bean.qos.llsp.LLSPQosObject;
import com.nms.model.ptn.path.tunnel.LspInfoService_MB;
import com.nms.model.ptn.qos.QosInfoService_MB;
import com.nms.model.ptn.qos.QosRelevanceService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class QosCXServiceImpl extends CXOperationBase implements OperationServiceI {
	/**
	 * 执行修改方法
	 * 
	 * @param qosInfoList
	 *            qos集合
	 * @param object
	 *            tunnel或者pw
	 * @return
	 * @throws Exception
	 */
	public String excutionUpdate_qos(List<QosInfo> qosInfoList, Object object) throws Exception {
		String result = null;
		QosRelevanceService_MB qosRelevanceService = null;
		Tunnel tunnel = null;
		PwInfo pwInfo=null;
		OperationServiceI operationServiceI=null;
		int activatedState=0;
		try {
			//先修改数据库
			qosRelevanceService = (QosRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSRELEVANCE);
			if (object instanceof Tunnel) {
				tunnel = (Tunnel) object;
				tunnel.setQosList(qosInfoList);
				qosRelevanceService.save(qosRelevanceService.getList(tunnel));
				activatedState=tunnel.getTunnelStatus();
				
				operationServiceI=new TunnelCXServiceImpl();
				
			}else if(object instanceof PwInfo){
				pwInfo=(PwInfo) object;
				pwInfo.setQosList(qosInfoList);
				qosRelevanceService.save(qosRelevanceService.getList(pwInfo));
				activatedState=pwInfo.getPwStatus();
				
				operationServiceI=new PwCXServiceImpl();
			}
			
			//如果是激活状态下发设备  否则直接返回
			if(activatedState==EActiveStatus.ACTIVITY.getValue()){
				result = operationServiceI.excutionUpdate(object);
			}else{
				result=ResultString.CONFIG_SUCCESS;
			}
			
//			 operationObject = this.convertOperationObject(qosInfoList, object);
//
//			if (operationObject.getCxActionObjectList().size() > 0) {
//				super.sendAction(operationObject);
//				super.verification(operationObject);
//
//				if (operationObject.isSuccess()) {
//					result = operationObject.getCxActionObjectList().get(0).getStatus();
//				} else {
//					result = super.getErrorMessage(operationObject);
//				}
//			} else {
//				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
//			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(qosRelevanceService);
		}
		return result;
	}

//	/**
//	 * 转换成OperationObject对象
//	 * 
//	 * @param qosInfoList
//	 * @param object
//	 * @return
//	 * @throws Exception
//	 */
//	private OperationObject convertOperationObject(List<QosInfo> qosInfoList, Object object) throws Exception {
//		OperationObject operationObject = null;
//		Tunnel tunnel = null;
//		PwInfo pwInfo = null;
//		try {
//			operationObject = new OperationObject();
//
//			if (object instanceof Tunnel) {
//				tunnel = (Tunnel) object;
//				if (tunnel.getTunnelStatus() == EActiveStatus.ACTIVITY.getValue()) {
//					this.convertTunnel(operationObject, qosInfoList, tunnel);
//				}
//			} else if (object instanceof PwInfo) {
//				pwInfo = (PwInfo) object;
//				if (pwInfo.getPwStatus() == EActiveStatus.ACTIVITY.getValue()) {
//
//					if (pwInfo.getType() == EPwType.ETH) { // eth的pw
//						this.convertEthPw(operationObject, qosInfoList, pwInfo);
//					} else if (pwInfo.getType() == EPwType.PDH) { // pdhpw
//						if (pwInfo.getIsSingle() == 0) { // 如果是网络配置，下发a z两端
//							operationObject.getCxActionObjectList().add(this.convertCxActionObject(operationObject, qosInfoList, pwInfo, pwInfo.getASiteId(), TypeAndActionUtil.TYPE_PWPDH, "a"));// 添加A
//							operationObject.getCxActionObjectList().add(this.convertCxActionObject(operationObject, qosInfoList, pwInfo, pwInfo.getZSiteId(), TypeAndActionUtil.TYPE_PWPDH, "z"));// 添加z
//						} else { // 否则下发一端
//							if (pwInfo.getASiteId() == ConstantUtil.siteId) {
//								operationObject.getCxActionObjectList().add(this.convertCxActionObject(operationObject, qosInfoList, pwInfo, pwInfo.getASiteId(), TypeAndActionUtil.TYPE_PWPDH, "a"));// 添加A
//							} else {
//								operationObject.getCxActionObjectList().add(this.convertCxActionObject(operationObject, qosInfoList, pwInfo, pwInfo.getZSiteId(), TypeAndActionUtil.TYPE_PWPDH, "z"));// 添加z
//							}
//						}
//					} else if (pwInfo.getType() == EPwType.SDH) { // sdhpw
//						if (pwInfo.getIsSingle() == 0) { // 如果是网络配置，下发a z两端
//							operationObject.getCxActionObjectList().add(this.convertCxActionObject(operationObject, qosInfoList, pwInfo, pwInfo.getASiteId(), TypeAndActionUtil.TYPE_PWSDH, "a"));// 添加a
//							operationObject.getCxActionObjectList().add(this.convertCxActionObject(operationObject, qosInfoList, pwInfo, pwInfo.getZSiteId(), TypeAndActionUtil.TYPE_PWSDH, "z"));// 添加z
//						} else {// 否则下发一端
//							if (pwInfo.getASiteId() == ConstantUtil.siteId) {
//								operationObject.getCxActionObjectList().add(this.convertCxActionObject(operationObject, qosInfoList, pwInfo, pwInfo.getASiteId(), TypeAndActionUtil.TYPE_PWSDH, "a"));// 添加a
//							} else {
//								operationObject.getCxActionObjectList().add(this.convertCxActionObject(operationObject, qosInfoList, pwInfo, pwInfo.getZSiteId(), TypeAndActionUtil.TYPE_PWSDH, "z"));// 添加z
//							}
//						}
//					} else if (pwInfo.getType() == EPwType.SDH_PDH) {// SDH_PDH 下发a端为sdh z端为pdh
//						operationObject.getCxActionObjectList().add(this.convertCxActionObject(operationObject, qosInfoList, pwInfo, pwInfo.getASiteId(), TypeAndActionUtil.TYPE_PWSDH, "a"));// 添加a端sdh
//						operationObject.getCxActionObjectList().add(this.convertCxActionObject(operationObject, qosInfoList, pwInfo, pwInfo.getZSiteId(), TypeAndActionUtil.TYPE_PWPDH, "z"));// 添加z端pdh
//					} else if (pwInfo.getType() == EPwType.PDH_SDH) {// PDH_SDH 下发a端为pdh z端为sdh
//						operationObject.getCxActionObjectList().add(this.convertCxActionObject(operationObject, qosInfoList, pwInfo, pwInfo.getASiteId(), TypeAndActionUtil.TYPE_PWPDH, "a"));// 添加a端pdh
//						operationObject.getCxActionObjectList().add(this.convertCxActionObject(operationObject, qosInfoList, pwInfo, pwInfo.getZSiteId(), TypeAndActionUtil.TYPE_PWSDH, "z"));// 添加z端sdh
//					}
//				}
//			}
//
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		} finally {
//			tunnel = null;
//			pwInfo = null;
//		}
//		return operationObject;
//	}

	/**
	 * 转换cxactionObject sdhpw、pdhpw用
	 * 
	 * @param operationObject
	 * @param qosInfoList
	 * @param siteId
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private CXActionObject convertCxActionObject(OperationObject operationObject, List<QosInfo> qosInfoList, PwInfo pwInfo, int siteId, String type, String siteType) throws Exception {

		CXActionObject cxActionObject = null;
		String cos = null;
		try {
			// 获取修改后的cos
			if (null != qosInfoList && qosInfoList.size() > 0) {
				cos = QosCosLevelEnum.from(qosInfoList.get(0).getCos()).toString().toLowerCase();
			}

			cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(type);
			cxActionObject.setAction(TypeAndActionUtil.ACTION_UPDATE);
			cxActionObject.setCxNeObject(super.getCXNEObject(siteId));
			if (type.equals(TypeAndActionUtil.TYPE_PWPDH)) {
				cxActionObject.setPwPdhObject(this.convertPwPdhObject(pwInfo, siteType, TypeAndActionUtil.ACTION_UPDATE, cos));
			} else {
				cxActionObject.setPwSdhObject(this.convertPwSDHObject(pwInfo, siteType, TypeAndActionUtil.ACTION_UPDATE, cos));
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return cxActionObject;

	}

	/**
	 * 转换成pdhpw对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private PwPdhObject convertPwPdhObject(PwInfo pwInfo, String type, String action, String cos) throws Exception {
		List<Lsp> lspParticularList = null;
		LspInfoService_MB lspParticularService = null;
		String tunnelName = "tunnel/";
		PwPdhObject pwPdhObject = null;
		try {
			lspParticularService = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO);
			lspParticularList = new ArrayList<Lsp>();
			pwPdhObject = new PwPdhObject();
			lspParticularList = lspParticularService.selectBytunnelId(pwInfo.getTunnelId());

			if (type.equals("a")) {
				pwPdhObject.setName(pwInfo.getApwServiceId() + "");
				pwPdhObject.setCarrierif(tunnelName + lspParticularList.get(0).getAtunnelbusinessid());
				pwPdhObject.setPeer(super.getSiteAdress(pwInfo.getZSiteId()));
				pwPdhObject.setInlabel(pwInfo.getOutlabelValue() + "");
				pwPdhObject.setOutlabel(pwInfo.getInlabelValue() + "");
				pwPdhObject.setOam(super.convertOamObject_mep("PW", pwInfo.getPwId(), pwInfo.getASiteId(), pwInfo.getOamList(), action));
				pwPdhObject.setCos(cos);
			} else {
				pwPdhObject.setName(pwInfo.getZpwServiceId() + "");
				pwPdhObject.setPeer(super.getSiteAdress(pwInfo.getASiteId()));
				pwPdhObject.setCarrierif(tunnelName + lspParticularList.get(lspParticularList.size() - 1).getZtunnelbusinessid());
				pwPdhObject.setInlabel(pwInfo.getInlabelValue() + "");
				pwPdhObject.setOutlabel(pwInfo.getOutlabelValue() + "");
				pwPdhObject.setOam(super.convertOamObject_mep("PW", pwInfo.getPwId(), pwInfo.getZSiteId(), pwInfo.getOamList(), action));
				pwPdhObject.setCos(cos);
			}
			pwPdhObject.setAdmin(pwInfo.getPwStatus() == EActiveStatus.UNACTIVITY.getValue() ? "down" : "up");
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			lspParticularList = null;
			UiUtil.closeService_MB(lspParticularService);
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
	private PwSdhObject convertPwSDHObject(PwInfo pwInfo, String type, String action, String cos) throws Exception {
		List<Lsp> lspParticularList = null;
		LspInfoService_MB lspParticularService = null;
		String tunnelName = "tunnel/";
		PwSdhObject pwSdhObject = null;
		try {
			lspParticularService = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO);
			lspParticularList = new ArrayList<Lsp>();
			pwSdhObject = new PwSdhObject();
			lspParticularList = lspParticularService.selectBytunnelId(pwInfo.getTunnelId());

			if (type.equals("a")) {
				pwSdhObject.setName(pwInfo.getApwServiceId() + "");
				pwSdhObject.setCarrierif(tunnelName + lspParticularList.get(0).getAtunnelbusinessid());
				pwSdhObject.setPeer(super.getSiteAdress(pwInfo.getZSiteId()));
				pwSdhObject.setInlabel(pwInfo.getOutlabelValue() + "");
				pwSdhObject.setOutlabel(pwInfo.getInlabelValue() + "");
				pwSdhObject.setOam(super.convertOamObject_mep("PW", pwInfo.getPwId(), pwInfo.getASiteId(), pwInfo.getOamList(), action));
				pwSdhObject.setCos(cos);
			} else {
				pwSdhObject.setName(pwInfo.getZpwServiceId() + "");
				pwSdhObject.setPeer(super.getSiteAdress(pwInfo.getASiteId()));
				pwSdhObject.setCarrierif(tunnelName + lspParticularList.get(lspParticularList.size() - 1).getZtunnelbusinessid());
				pwSdhObject.setInlabel(pwInfo.getInlabelValue() + "");
				pwSdhObject.setOutlabel(pwInfo.getOutlabelValue() + "");
				pwSdhObject.setOam(super.convertOamObject_mep("PW", pwInfo.getPwId(), pwInfo.getZSiteId(), pwInfo.getOamList(), action));
				pwSdhObject.setCos(cos);
			}
			pwSdhObject.setAdmin(pwInfo.getPwStatus() == EActiveStatus.UNACTIVITY.getValue() ? "down" : "up");

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			lspParticularList = null;
			UiUtil.closeService_MB(lspParticularService);
			tunnelName = null;
		}

		return pwSdhObject;
	}

//	/**
//	 * 转换tunnel的operationobject
//	 * 
//	 * @param operationObject
//	 * @param qosInfoList
//	 * @param tunnel
//	 * @throws Exception
//	 */
//	private void convertTunnel(OperationObject operationObject, List<QosInfo> qosInfoList, Tunnel tunnel) throws Exception {
//		String qosName = null;
//		String siteType = null;
//		try {
//			// this.setObject(qosInfoList, EServiceType.TUNNEL.toString(), tunnel.getTunnelId());
//			if (tunnel.getIsSingle() == 1) { // 如果为单网元配置 只下发单网元的
//				if (tunnel.getLspParticularList().size() == 1) { // 如果此tunnel只有一个lsp 说明是a端或z端
//					qosName = qosInfoList.get(0).getQosType().toLowerCase() + "tunnel" + tunnel.getTunnelId();
//					if (ConstantUtil.siteId == tunnel.getASiteId()) {
//						siteType = "a";
//					} else {
//						siteType = "z";
//					}
//				} else { // 否则为XC
//					qosName = qosInfoList.get(0).getQosType().toLowerCase() + "xc" + tunnel.getTunnelId();
//					siteType = "xc";
//				}
//				operationObject.getCxActionObjectList().add(this.convertCxActionObject_qos(operationObject, qosInfoList, qosName, ConstantUtil.siteId, siteType));
//			} else {
//				for (int siteid : this.getAllSite(tunnel)) { // 遍历所有tunnel下的网元，逐个下发。
//					if (siteid == tunnel.getASiteId() || siteid == tunnel.getZSiteId()) {
//						qosName = qosInfoList.get(0).getQosType().toLowerCase() + "tunnel" + tunnel.getTunnelId();
//						if (siteid == tunnel.getASiteId()) {
//							siteType = "a";
//						} else {
//							siteType = "z";
//						}
//					} else {
//						qosName = qosInfoList.get(0).getQosType().toLowerCase() + "xc" + tunnel.getTunnelId();
//						siteType = "xc";
//					}
//					operationObject.getCxActionObjectList().add(this.convertCxActionObject_qos(operationObject, qosInfoList, qosName, siteid, siteType));
//				}
//			}
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		} finally {
//			qosName = null;
//			siteType = null;
//		}
//	}

//	/**
//	 * 转换ethpw的qos
//	 * 
//	 * @param operationObject
//	 * @param qosInfoList
//	 * @param pwInfo
//	 * @throws Exception
//	 */
//	private void convertEthPw(OperationObject operationObject, List<QosInfo> qosInfoList, PwInfo pwInfo) throws Exception {
//
//		String qosName = null;
//		String siteType = null;
//		try {
//			// this.setObject(qosInfoList, EServiceType.PW.toString(), pwInfo.getPwId());
//			qosName = qosInfoList.get(0).getQosType().toLowerCase() + "pw" + pwInfo.getPwId();
//			if (pwInfo.getIsSingle() == 1) { // 为单网元配置 只下发单网元的
//				if (ConstantUtil.siteId == pwInfo.getASiteId()) {
//					siteType = "a";
//				} else {
//					siteType = "z";
//				}
//				operationObject.getCxActionObjectList().add(this.convertCxActionObject_qos(operationObject, qosInfoList, qosName, ConstantUtil.siteId, siteType));
//			} else { // 否则下发a端和z端的
//				operationObject.getCxActionObjectList().add(this.convertCxActionObject_qos(operationObject, qosInfoList, qosName, pwInfo.getASiteId(), "a"));
//				operationObject.getCxActionObjectList().add(this.convertCxActionObject_qos(operationObject, qosInfoList, qosName, pwInfo.getZSiteId(), "z"));
//			}
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		} finally {
//			qosName = null;
//			siteType = null;
//		}
//	}

	// /**
	// * 给qosInfoList赋值 objecttype和objectid
	// *
	// * @param qosInfoList
	// * @param objecttype
	// * @param objectid
	// */
	// private void setObject(List<QosInfo> qosInfoList, String objecttype, int objectid) {
	//
	// for (QosInfo qosInfo : qosInfoList) {
	// qosInfo.setObjId(objectid);
	// qosInfo.setObjType(objecttype);
	// qosInfo.setQosname(qosInfo.getQosType().toLowerCase() + objecttype.toLowerCase() + objectid);
	// }
	//
	// }

	/**
	 * 转换成CxActionObject对象 修改qos用
	 * 
	 * @param operationObject
	 * @param qosInfoList
	 * @param qosName
	 * @return
	 * @throws Exception
	 */
	private CXActionObject convertCxActionObject_qos(OperationObject operationObject, List<QosInfo> qosInfoList, String qosName, int siteId, String siteType) throws Exception {

		CXActionObject cxActionObject = null;
		try {
			cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_QOS);
			cxActionObject.setAction(TypeAndActionUtil.ACTION_UPDATE);
			cxActionObject.setCxNeObject(super.getCXNEObject(siteId));
			cxActionObject.setQosObject(super.convertQosObject(qosInfoList, qosName, siteType));

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return cxActionObject;
	}

	/**
	 * 获取tunnel下所有网元ID
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<Integer> getAllSite(Tunnel tunnel) throws Exception {
		List<Integer> siteids = null;
		try {
			siteids = new ArrayList<Integer>();

			for (Lsp lsp : tunnel.getLspParticularList()) { // 遍历tunnel下的所有leg 把leg的a z添加到集合中
				if (!siteids.contains(lsp.getASiteId())) {
					siteids.add(lsp.getASiteId());
				}
				if (!siteids.contains(lsp.getZSiteId())) {
					siteids.add(lsp.getZSiteId());
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return siteids;
	}

	// /**
	// * 修改数据库qosinfo表数据
	// *
	// * @param qosInfoList
	// * @param object
	// * @throws Exception
	// */
	// public void updateQos(List<QosInfo> qosInfoList, Object object) throws Exception {
	// Tunnel tunnel = null;
	// PwInfo pwInfo = null;
	// int objectId = 0;
	// String objectType = null;
	// try {
	// if (object instanceof Tunnel) {
	// tunnel = (Tunnel) object;
	// objectId = tunnel.getTunnelId();
	// objectType = "TUNNEL";
	// } else if (object instanceof PwInfo) {
	// pwInfo = (PwInfo) object;
	// objectId = pwInfo.getPwId();
	// objectType = "PW";
	// }
	//
	// // 给qos的objectid和objecttype赋值
	// for (QosInfo qosInfo : qosInfoList) {
	// qosInfo.setObjId(objectId);
	// qosInfo.setObjType(objectType);
	// }
	// this.qosInfoService.updateQos(qosInfoList);
	//
	// } catch (Exception e) {
	// ExceptionManage.dispose(e,this.getClass());
	// } finally {
	// tunnel = null;
	// pwInfo = null;
	// objectType = null;
	// }
	// }

	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		return null;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		List<QosObject> qosObjectList = null;
		QosInfoService_MB qosInfoService = null;
		try {
			qosInfoService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_QOS, "");
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				qosObjectList = operationObject.getCxActionObjectList().get(0).getQosObjectList();
				// 同步前把所有的状态修改
				qosInfoService.updateStatus(siteId, "path", null, 1);
				this.synchro_db(qosObjectList, siteId);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(qosInfoService);
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
	private void getOperactionObject_select(OperationObject operationObject, int siteId, String type, String tunneName) throws Exception {
		CXActionObject actionObject = new CXActionObject();

		actionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
		actionObject.setCxNeObject(super.getCXNEObject(siteId));
		actionObject.setType(type);
		actionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
		operationObject.getCxActionObjectList().add(actionObject);

	}

	/**
	 * 与数据库进行同步
	 * 
	 * @param qosObjectList
	 * @throws Exception
	 */
	private void synchro_db(List<QosObject> qosObjectList, int siteId) throws Exception {
		List<QosInfo> qosInfoList = null;
		try {
			SynchroUtil synchroUtil = new SynchroUtil();
			for (QosObject qosObject : qosObjectList) {
				if (QosTemplateTypeEnum.LLSP.toString().equals(qosObject.getQosType())) {
					qosInfoList = this.convertLlsp(qosObject, siteId);
				} else if (QosTemplateTypeEnum.ELSP.toString().equals(qosObject.getQosType())) {
					qosInfoList = this.convertElsp(qosObject, siteId);
				}
				if (null != qosInfoList && qosInfoList.size() > 0) {
					synchroUtil.updateQos(qosInfoList);
				}
			}

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 把llsp类型的qos转换成数据库层
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<QosInfo> convertLlsp(QosObject qosObject, int siteId) throws Exception {
		List<QosInfo> qosInfoList = null;
		LLSPQosObject llspQosObject = null;
		try {
			qosInfoList = new ArrayList<QosInfo>();
			llspQosObject = (LLSPQosObject) qosObject;

			qosInfoList.addAll(this.getQosInfo(siteId, llspQosObject.getName(), QosTemplateTypeEnum.LLSP.toString(), Integer.parseInt(llspQosObject.getCos()), llspQosObject.getIcir(), llspQosObject.getOcir(), llspQosObject.getIcbs(), llspQosObject.getOcbs(), llspQosObject.getIeir(), llspQosObject.getOeir(), llspQosObject.getIebs(), llspQosObject.getOebs(), llspQosObject.getIpir(), llspQosObject.getOpir()));

		} catch (Exception e) {
			throw e;
		} finally {
			llspQosObject = null;
		}
		return qosInfoList;
	}

	/**
	 * 把Elsp类型的qos转换成数据库层
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<QosInfo> convertElsp(QosObject qosObject, int siteId) throws Exception {
		List<QosInfo> qosInfoList = null;
		ELSPQosObject elspQosObject = null;
		try {
			qosInfoList = new ArrayList<QosInfo>();
			elspQosObject = (ELSPQosObject) qosObject;

			// EF
			qosInfoList.addAll(this.getQosInfo(siteId, elspQosObject.getName(), QosTemplateTypeEnum.ELSP.toString(), QosCosLevelEnum.EF.getValue(), elspQosObject.getEf().getIcir(), elspQosObject.getEf().getOcir(), elspQosObject.getEf().getIcbs(), elspQosObject.getEf().getOcbs(), "0", "0", "0", "0", elspQosObject.getEf().getIpir(), elspQosObject.getEf().getOpir()));
			// AF1
			qosInfoList.addAll(this.getQosInfo(siteId, elspQosObject.getName(), QosTemplateTypeEnum.ELSP.toString(), QosCosLevelEnum.AF1.getValue(), elspQosObject.getAf1().getIcir(), elspQosObject.getAf1().getOcir(), elspQosObject.getAf1().getIcbs(), elspQosObject.getAf1().getOcbs(), elspQosObject.getAf1().getIeir(), elspQosObject.getAf1().getOeir(), elspQosObject.getAf1().getIebs(), elspQosObject.getAf1().getOebs(), elspQosObject.getAf1().getIpir(), elspQosObject.getAf1().getOpir()));
			// AF2
			qosInfoList.addAll(this.getQosInfo(siteId, elspQosObject.getName(), QosTemplateTypeEnum.ELSP.toString(), QosCosLevelEnum.AF2.getValue(), elspQosObject.getAf2().getIcir(), elspQosObject.getAf2().getOcir(), elspQosObject.getAf2().getIcbs(), elspQosObject.getAf2().getOcbs(), elspQosObject.getAf2().getIeir(), elspQosObject.getAf2().getOeir(), elspQosObject.getAf2().getIebs(), elspQosObject.getAf2().getOebs(), elspQosObject.getAf2().getIpir(), elspQosObject.getAf2().getOpir()));
			// AF3
			qosInfoList.addAll(this.getQosInfo(siteId, elspQosObject.getName(), QosTemplateTypeEnum.ELSP.toString(), QosCosLevelEnum.AF3.getValue(), elspQosObject.getAf3().getIcir(), elspQosObject.getAf3().getOcir(), elspQosObject.getAf3().getIcbs(), elspQosObject.getAf3().getOcbs(), elspQosObject.getAf3().getIeir(), elspQosObject.getAf3().getOeir(), elspQosObject.getAf3().getIebs(), elspQosObject.getAf3().getOebs(), elspQosObject.getAf3().getIpir(), elspQosObject.getAf3().getOpir()));
			// AF4
			qosInfoList.addAll(this.getQosInfo(siteId, elspQosObject.getName(), QosTemplateTypeEnum.ELSP.toString(), QosCosLevelEnum.AF4.getValue(), elspQosObject.getAf4().getIcir(), elspQosObject.getAf4().getOcir(), elspQosObject.getAf4().getIcbs(), elspQosObject.getAf4().getOcbs(), elspQosObject.getAf4().getIeir(), elspQosObject.getAf4().getOeir(), elspQosObject.getAf4().getIebs(), elspQosObject.getAf4().getOebs(), elspQosObject.getAf4().getIpir(), elspQosObject.getAf4().getOpir()));
			// EF
			qosInfoList.addAll(this.getQosInfo(siteId, elspQosObject.getName(), QosTemplateTypeEnum.ELSP.toString(), QosCosLevelEnum.EF.getValue(), elspQosObject.getEf().getIcir(), elspQosObject.getEf().getOcir(), elspQosObject.getEf().getIcbs(), elspQosObject.getEf().getOcbs(), "0", "0", "0", "0", elspQosObject.getEf().getIpir(), elspQosObject.getEf().getOpir()));
			// CS6
			qosInfoList.addAll(this.getQosInfo(siteId, elspQosObject.getName(), QosTemplateTypeEnum.ELSP.toString(), QosCosLevelEnum.CS6.getValue(), elspQosObject.getCs6().getIcir(), elspQosObject.getCs6().getOcir(), elspQosObject.getCs6().getIcbs(), elspQosObject.getCs6().getOcbs(), "0", "0", "0", "0", elspQosObject.getCs6().getIpir(), elspQosObject.getCs6().getOpir()));
			// CS7
			qosInfoList.addAll(this.getQosInfo(siteId, elspQosObject.getName(), QosTemplateTypeEnum.ELSP.toString(), QosCosLevelEnum.CS7.getValue(), elspQosObject.getCs7().getIcir(), elspQosObject.getCs7().getOcir(), elspQosObject.getCs7().getIcbs(), elspQosObject.getCs7().getOcbs(), "0", "0", "0", "0", elspQosObject.getCs7().getIpir(), elspQosObject.getCs7().getOpir()));
		} catch (Exception e) {
			throw e;
		}
		return qosInfoList;
	}

	/**
	 * 转换为qos对象
	 * 
	 * @param qosName
	 *            qos名称
	 * @param qosType
	 *            qos类型
	 * @param cos
	 * @return
	 * @throws Exception
	 */
	private List<QosInfo> getQosInfo(int siteId, String qosName, String qosType, int cos, String icir, String ocir, String icbs, String ocbs, String ieir, String oeir, String iebs, String oebs, String ipir, String opir) throws Exception {
		List<QosInfo> qosInfoList = null;
		QosInfo qosInfo = null;
		try {
			qosInfoList = new ArrayList<QosInfo>();

			qosInfo = new QosInfo();
			qosInfo.setSiteId(siteId);
			qosInfo.setQosType(qosType);
			qosInfo.setCos(cos);
			qosInfo.setDirection(EQosDirection.FORWARD.getValue() + "");
			qosInfo.setCir(Integer.parseInt(icir));
			qosInfo.setCbs(Integer.parseInt(icbs));
			qosInfo.setEir(Integer.parseInt(ieir));
			qosInfo.setEbs(Integer.parseInt(iebs));
			qosInfo.setPir(Integer.parseInt(ipir));
			qosInfo.setQosname(qosName);
			qosInfo.setStatus(2);
			qosInfoList.add(qosInfo);

			qosInfo = new QosInfo();
			qosInfo.setSiteId(siteId);
			qosInfo.setQosType(qosType);
			qosInfo.setCos(cos);
			qosInfo.setDirection(EQosDirection.BACKWARD.getValue() + "");
			qosInfo.setCir(Integer.parseInt(ocir));
			qosInfo.setCbs(Integer.parseInt(ocbs));
			qosInfo.setEir(Integer.parseInt(oeir));
			qosInfo.setEbs(Integer.parseInt(oebs));
			qosInfo.setPir(Integer.parseInt(opir));
			qosInfo.setQosname(qosName);
			qosInfo.setStatus(2);
			qosInfoList.add(qosInfo);
		} catch (Exception e) {
			throw e;
		} finally {
			qosInfo = null;
		}
		return qosInfoList;
	}
}
