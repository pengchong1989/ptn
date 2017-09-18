package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EObjectType;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.drivechenxiao.service.bean.oam.OamMipObject;
import com.nms.drivechenxiao.service.bean.oam.OamObject;
import com.nms.drivechenxiao.service.bean.qos.QosObject;
import com.nms.drivechenxiao.service.bean.tunnel.LSPObject;
import com.nms.drivechenxiao.service.bean.tunnel.Protection;
import com.nms.drivechenxiao.service.bean.tunnel.TunnelObject;
import com.nms.drivechenxiao.service.bean.xc.XcIFObject;
import com.nms.drivechenxiao.service.bean.xc.XcObject;
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

public class TunnelCXServiceImpl extends CXOperationBase implements OperationServiceI {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String excutionDelete(List objectList) throws Exception {

		List<Tunnel> tunnelList = null;
		OperationObject operationObject = null;
		String errorMessage = null;
		try {
			tunnelList = objectList;
			operationObject = this.getOperationObject(tunnelList, TypeAndActionUtil.ACTION_DELETE);
			if (operationObject.getCxActionObjectList().size() > 0) {
				super.sendAction(operationObject);
				super.verification(operationObject);
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
			tunnelList = null;
			operationObject = null;
			
		}
		return errorMessage;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {

		Tunnel tunnel = null;
		OperationObject operationObject = null;
		String errorMessage = null;
		QosRelevanceService_MB qosRelevanceService=null;
		try {
			tunnel = (Tunnel) object;
			operationObject = this.getOperationObject(tunnel, TypeAndActionUtil.ACTION_INSERT);
			if (operationObject.getCxActionObjectList().size() > 0) {
				super.sendAction(operationObject);
				super.verification(operationObject);
				if (operationObject.isSuccess()) {
					
					//修改qos状态
					qosRelevanceService=(QosRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSRELEVANCE);
					qosRelevanceService.updateQosStatus(tunnel);
					
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
		Tunnel tunnel = null;
		OperationObject operationObject = null;
		String result = null;
		String action = null;
		QosRelevanceService_MB qosRelevanceService=null;
		try {
			tunnel = (Tunnel) object;
			// 根据激活状态 获取应该下发什么动作
			action = this.getActionUpdate(tunnel);
			if ("".equals(action)) {
				result = ResultString.CONFIG_SUCCESS;
			} else {
				operationObject = this.getOperationObject(tunnel, action);
				if (operationObject.getCxActionObjectList().size() > 0) {
					super.sendAction(operationObject);
					super.verification(operationObject);
					if (operationObject.isSuccess()) {
						//修改qos状态
						qosRelevanceService=(QosRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSRELEVANCE);
						qosRelevanceService.updateQosStatus(tunnel);
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

	/**
	 * 把tunnel转换成OperationObject
	 * 
	 * @param tunnel
	 *            tunnel对象
	 * @param action
	 *            动作
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(Tunnel tunnel, String action) throws Exception {
		if (null == tunnel) {
			throw new Exception("tunnel is null");
		}
		List<Integer> siteIds = null;
		OperationObject operationObject = null;
		List<Integer> projectSiteIds = null;
		try {
			operationObject = new OperationObject();
			SiteUtil siteUtil=new SiteUtil();
			if (tunnel.getIsSingle() == 0) {
				siteIds = this.getSiteIds(tunnel);
				for (Integer siteId : siteIds) {
					if(0==siteUtil.SiteTypeUtil(siteId)){
						if (super.getManufacturer(siteId) != EManufacturer.CHENXIAO.getValue()) {
							continue;
						}
						// 如果网元是晨晓的 转换对象
						if (tunnel.getASiteId() == siteId) {
							operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, tunnel, siteId, "a", action));
						} else if (tunnel.getZSiteId() == siteId) {
							operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, tunnel, siteId, "z", action));
						} else {
							if(!EActionType.UPDATE.toString().equals(action))
								operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, tunnel, siteId, "xc", action));
						}			
					}
				}

				// 转换保护XC下发
				if(!tunnel.getTunnelType().equals("0")){
					if ("2".equals(UiUtil.getCodeById(Integer.parseInt(tunnel.getTunnelType())).getCodeValue())) {
						if (tunnel.getProtectTunnel().getLspParticularList().size() > 1) {
							projectSiteIds = this.getProtectSiteids(tunnel.getProtectTunnel());
							for (int protectSiteid : projectSiteIds) {
								if (super.getManufacturer(protectSiteid) != EManufacturer.CHENXIAO.getValue()) {
									continue;
								}
								if(0==siteUtil.SiteTypeUtil(protectSiteid)){
									operationObject.getCxActionObjectList().add(this.getCXActionObject_protect(operationObject, tunnel.getProtectTunnel(), tunnel, protectSiteid, "xc", action));	
								}
								
							}
						}
					}
				}

			} else {
				// 如果两端端口都为0 说明是xc配置
				if (tunnel.getZPortId() == 0 && tunnel.getAPortId() == 0) {
					if (super.getManufacturer(tunnel.getLspParticularList().get(0).getZSiteId()) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(tunnel.getLspParticularList().get(0).getZSiteId())) {
						operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, tunnel, tunnel.getLspParticularList().get(0).getZSiteId(), "xc", action));
					}
				} else if (tunnel.getZPortId() == 0 && tunnel.getAPortId() != 0&&0==siteUtil.SiteTypeUtil(tunnel.getASiteId())) {
					if (0!=tunnel.getASiteId()&&super.getManufacturer(tunnel.getASiteId()) == EManufacturer.CHENXIAO.getValue()) {
						operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, tunnel, tunnel.getASiteId(), "a", action));
					}
				} else {
					if (0!=tunnel.getZSiteId()&&super.getManufacturer(tunnel.getZSiteId()) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(tunnel.getZSiteId())) {
						operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, tunnel, tunnel.getZSiteId(), "z", action));
					}
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			siteIds = null;
		}
		return operationObject;
	}

	/**
	 * 获取保护的tunnel所有的网元ID 不包括A Z端
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private List<Integer> getProtectSiteids(Tunnel tunnel) {

		List<Integer> siteIds = null;
		try {
			siteIds = new ArrayList<Integer>();

			for (Lsp lspParticular : tunnel.getLspParticularList()) {
				// 如果不是A端 才添加
				if (lspParticular.getASiteId() != tunnel.getASiteId()) {
					if (!siteIds.contains(lspParticular.getASiteId())) {
						siteIds.add(lspParticular.getASiteId());
					}
				}
				// 如果不是Z端 才添加
				if (lspParticular.getZSiteId() != tunnel.getZSiteId()) {
					if (!siteIds.contains(lspParticular.getZSiteId())) {
						siteIds.add(lspParticular.getZSiteId());
					}
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return siteIds;

	}

	/**
	 * 获取一组tunnel的OperationObject
	 * 
	 * @throws Exception
	 */
	private OperationObject getOperationObject(List<Tunnel> tunnelList, String action) throws Exception {
		if (null == tunnelList || tunnelList.size() == 0) {
			throw new Exception("tunnel is null");
		}
		List<Integer> siteIds = null;
		OperationObject operationObject = null;
		List<Integer> projectSiteIds = null;
		try {
			operationObject = new OperationObject();
			SiteUtil siteUtil=new SiteUtil();
			for (Tunnel tunnel : tunnelList) {
				if (tunnel.getIsSingle() == 0) {
					siteIds = this.getSiteIds(tunnel);

					for (Integer siteId : siteIds) {
						if(0==siteUtil.SiteTypeUtil(siteId)){
							// 如果网元是晨晓的 转换对象
							if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(siteId)) {
								if (tunnel.getASiteId() == siteId) {
									operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, tunnel, siteId, "a", action));
								} else if (tunnel.getZSiteId() == siteId) {
									operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, tunnel, siteId, "z", action));
								} else {
									operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, tunnel, siteId, "xc", action));
								}
							}
						}
					}
					// 转换保护XC下发
					if ("2".equals(UiUtil.getCodeById(Integer.parseInt(tunnel.getTunnelType())).getCodeValue())) {
						if (tunnel.getProtectTunnel().getLspParticularList().size() > 1) {
							projectSiteIds = this.getProtectSiteids(tunnel.getProtectTunnel());
							for (int protectSiteid : projectSiteIds) {
								if (super.getManufacturer(protectSiteid) != EManufacturer.CHENXIAO.getValue()) {
									continue;
								}
								operationObject.getCxActionObjectList().add(this.getCXActionObject_protect(operationObject, tunnel.getProtectTunnel(), tunnel, protectSiteid, "xc", action));
							}
						}
					}

				} else {
					// 如果两端端口都为0 说明是xc配置
					if (tunnel.getZPortId() == 0 && tunnel.getAPortId() == 0) {
						if (super.getManufacturer(tunnel.getLspParticularList().get(0).getZSiteId()) == EManufacturer.CHENXIAO.getValue()) {
							operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, tunnel, tunnel.getLspParticularList().get(0).getZSiteId(), "xc", action));
						}
					} else if (tunnel.getZPortId() == 0 && tunnel.getAPortId() != 0) {
						if (super.getManufacturer(tunnel.getASiteId()) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(tunnel.getLspParticularList().get(0).getASiteId())) {
							operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, tunnel, tunnel.getASiteId(), "a", action));
						}
					} else {
						if (super.getManufacturer(tunnel.getZSiteId()) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(tunnel.getLspParticularList().get(0).getZSiteId())) {
							operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, tunnel, tunnel.getZSiteId(), "z", action));
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			siteIds = null;
		}
		return operationObject;
	}

	/**
	 * 获取晨晓的actionobject对象
	 * 
	 * @throws Exception
	 */
	private CXActionObject getCXActionObject(OperationObject operationObject, Tunnel tunnel, int siteId, String type, String action) throws Exception {
		CXActionObject cxActionObject = null;
		try {
			cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(siteId));
			cxActionObject.setAction(action);

			if(tunnel.isDataDownLoad()){
				if(!action.equals(TypeAndActionUtil.ACTION_DELETE)){
					//配置qos
					cxActionObject.setQosObject(this.getqosObject(tunnel, type, siteId));
				}
			}else{
				//配置qos
				cxActionObject.setQosObject(this.getqosObject(tunnel, type, siteId));
			}
			
			
			
			if (!action.equals(TypeAndActionUtil.ACTION_UPDATE)) {
				cxActionObject.setOamObject(this.getOamObject(tunnel, type, action));
			} else {
				cxActionObject.setOamObject(null);
			}
			if (type.equals("xc")) {
				cxActionObject.setType(TypeAndActionUtil.TYPE_XC);
				cxActionObject.setXcObject(this.convertXcObject(tunnel, siteId, cxActionObject));
			} else {
				cxActionObject.setType(TypeAndActionUtil.TYPE_TUNNEL);
				cxActionObject.setTunnelObject(this.convertTunnelObject(tunnel, type, action, cxActionObject,siteId));
				if ("2".equals(UiUtil.getCodeById(Integer.parseInt(tunnel.getTunnelType())).getCodeValue())) {
					cxActionObject.setProtection(this.convertProtecttion(tunnel, siteId));
				} else {
					cxActionObject.setProtection(null);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return cxActionObject;
	}

	/**
	 * 转换保护对象
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private Protection convertProtecttion(Tunnel tunnel, int siteId) {

		String path = "tunnel/";
		for (Lsp lsp : tunnel.getLspParticularList()) {
			if (lsp.getASiteId() == siteId) {
				path += lsp.getAtunnelbusinessid() + "/";
				break;
			}
			if (lsp.getZSiteId() == siteId) {
				path += lsp.getZtunnelbusinessid() + "/";
				break;
			}
		}

		Protection protection = new Protection();
		protection.setEnaps(tunnel.getApsenable() == 0 ? "false" : "true");
		protection.setWtrtime(super.convertWtrtimeSend(tunnel.getWaittime())/30 + "");
		protection.setHoldofftime(super.convertDelaytimeSend(tunnel.getDelaytime()) + "");
		protection.setType("1");
		protection.setWorklsp(path + "1");
		protection.setPrtlsp(path + "2");
		protection.setSdthreshold("true");
		return protection;
	}

	/**
	 * 获取晨晓的actionobject对象 保护
	 * 
	 * @throws Exception
	 */
	private CXActionObject getCXActionObject_protect(OperationObject operationObject, Tunnel tunnel, Tunnel jobTunnel, int siteId, String type, String action) throws Exception {
		CXActionObject cxActionObject = null;
		try {
			cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(siteId));
			cxActionObject.setAction(action);

			if (tunnel.getASiteId() == siteId) {
				cxActionObject.setQosObject(this.getqosObject(tunnel, type, siteId));
			}
			if (tunnel.getZSiteId() == siteId) {
				cxActionObject.setQosObject(this.getqosObject(tunnel, type, siteId));
			}
			if (!action.equals(TypeAndActionUtil.ACTION_UPDATE)) {
				cxActionObject.setOamObject(this.getOamObject(tunnel, type, action));
			} else {
				cxActionObject.setOamObject(null);
			}
			if (type.equals("xc")) {
				cxActionObject.setQosObject(this.getqosObject(tunnel, type, siteId));
				cxActionObject.setType(TypeAndActionUtil.TYPE_XC);
				cxActionObject.setXcObject(this.convertXcObject_project(tunnel, jobTunnel, siteId, cxActionObject));
			} else {
				cxActionObject.setType(TypeAndActionUtil.TYPE_TUNNEL);
				cxActionObject.setTunnelObject(this.convertTunnelObject(tunnel, type, action, cxActionObject,siteId));
			}
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
	private QosObject getqosObject(Tunnel tunnel, String type, int siteId) throws Exception {
		QosObject qosObject = null;
		try {
			qosObject = super.getqosobject("TUNNEL", type, tunnel.getTunnelId(), siteId);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return qosObject;

	}

	private OamObject getOamObject(Tunnel tunnel, String type, String action) throws Exception {
		OamObject oamObject = null;

		if (type.equals("a")) {
			oamObject = super.convertOamObject_mep("TUNNEL", tunnel.getTunnelId(), tunnel.getASiteId(), tunnel.getOamList(), action);
		} else if (type.equals("z")) {
			oamObject = super.convertOamObject_mep("TUNNEL", tunnel.getTunnelId(), tunnel.getZSiteId(), tunnel.getOamList(), action);
		}

		return oamObject;
	}

	/**
	 * 转换成xcObject对象
	 * 
	 * @return
	 * @throws Exception
	 */
	private XcObject convertXcObject(Tunnel tunnel, int siteId, CXActionObject cxActionObject) throws Exception {

		List<Lsp> lspLegList = null;
		XcIFObject[] xcIFObjects = new XcIFObject[2];
		XcIFObject xcIFObject = null;
		XcObject xcObejct = null;
		QosInfo qosInfo=null;
		try {
			xcObejct = new XcObject();
			xcObejct.setLspid("1");
			//取此网元对应的qos
			qosInfo=super.getQosInfo(EServiceType.TUNNEL.toString(), tunnel.getTunnelId(), siteId);
			xcObejct.setQos(qosInfo.getQosname());
			//是否创建qos
			if(qosInfo.getStatus() == 1){
				xcObejct.setCreateQos(true);
			}
			// xcObejct.setQos("llspxc1");
			xcObejct.setRole("normal");
			xcObejct.setAdmin(tunnel.getTunnelStatus() == EActiveStatus.UNACTIVITY.getValue() ? "down" : "up");
			lspLegList = tunnel.getLspParticularList();
			for (Lsp lspParticular : lspLegList) {

				if (lspParticular.getZSiteId() == siteId) {
					xcObejct.setIngress(lspParticular.getZoppositeId());
					// xcObejct.setIngress(super.getSiteAdress(lspParticular.getASiteId()));
					// if (lspParticular.getASiteId() == tunnel.getASiteId()) {
					if (tunnel.getIsSingle() == 0) {
						xcObejct.setTunnelsid(lspParticular.getAtunnelbusinessid() + "");
					} else {
						xcObejct.setTunnelsid("1");
					}
					xcObejct.setName(lspParticular.getZtunnelbusinessid() + "");
					// }
					/** 左端口配置 */
					xcIFObject = new XcIFObject();
					xcIFObject.setName("1");
					xcIFObject.setCarrierif(super.getPortname(lspParticular.getZPortId()));
					xcIFObject.setInlabel(lspParticular.getBackLabelValue() + "");
					xcIFObject.setOutlabel(lspParticular.getFrontLabelValue() + "");
					xcIFObjects[0] = xcIFObject;
				} else if (lspParticular.getASiteId() == siteId) {
					xcObejct.setEgress(lspParticular.getAoppositeId());
					// xcObejct.setEgress(super.getSiteAdress(lspParticular.getZSiteId()));
					// if (lspParticular.getZSiteId() == tunnel.getZSiteId()) {
					if (tunnel.getIsSingle() == 0) {
						xcObejct.setTunneldid(lspParticular.getZtunnelbusinessid() + "");
					} else {
						xcObejct.setTunneldid("1");
					}
					xcObejct.setName(lspParticular.getAtunnelbusinessid() + "");
					// }
					/** 右端口配置 */
					xcIFObject = new XcIFObject();
					xcIFObject.setName("2");
					xcIFObject.setCarrierif(super.getPortname(lspParticular.getAPortId()));
					xcIFObject.setInlabel(lspParticular.getFrontLabelValue() + "");
					xcIFObject.setOutlabel(lspParticular.getBackLabelValue() + "");
					xcIFObjects[1] = xcIFObject;
				}

			}
			xcObejct.setXcIFObjects(xcIFObjects);
			xcObejct.setOamMipObject(convertOammip(xcObejct, siteId,tunnel));

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			lspLegList = null;
			xcIFObjects = null;
			xcIFObject = null;
		}
		return xcObejct;
	}

	private OamMipObject convertOammip(XcObject xcObject, int siteId,Tunnel tunnel) throws Exception {
		for(OamInfo oamInfo:tunnel.getOamList()){
			if(null!=oamInfo.getOamMip()){
				if(siteId == oamInfo.getOamMip().getSiteId()){
					return super.convertOamObject_mip(oamInfo);
				}
			}
		}
		return null;

	}

	/**
	 * 转换成xcObject对象
	 * 
	 * @return
	 * @throws Exception
	 */
	private XcObject convertXcObject_project(Tunnel tunnel, Tunnel jobTunnel, int siteId, CXActionObject cxActionObject) throws Exception {

		List<Lsp> lspLegList = null;
		XcIFObject[] xcIFObjects = new XcIFObject[2];
		XcIFObject xcIFObject = null;
		XcObject xcObejct = null;
		QosInfo qosInfo=null;
		try {
			xcObejct = new XcObject();
			xcObejct.setLspid("1");
			
			//取此网元对应的qos
			qosInfo=super.getQosInfo(EServiceType.TUNNEL.toString(), tunnel.getTunnelId(), siteId);
			xcObejct.setQos(qosInfo.getQosname());
			//是否创建qos
			if(qosInfo.getStatus() == 1){
				xcObejct.setCreateQos(true);
			}
			xcObejct.setRole("normal");
			xcObejct.setAdmin(tunnel.getTunnelStatus() == EActiveStatus.UNACTIVITY.getValue() ? "down" : "up");
			lspLegList = tunnel.getLspParticularList();
			for (Lsp lspParticular : lspLegList) {

				if (lspParticular.getZSiteId() == siteId) {
					xcObejct.setIngress(super.getSiteAdress(lspParticular.getASiteId()));
					if (tunnel.getIsSingle() == 0) {
						if (lspParticular.getASiteId() == tunnel.getASiteId()) {
							int tunnelbusinessId = 0;
							for (Lsp lsp_job : jobTunnel.getLspParticularList()) {
								if (lsp_job.getASiteId() == jobTunnel.getASiteId()) {
									tunnelbusinessId = lsp_job.getAtunnelbusinessid();
									break;
								}
							}
							xcObejct.setTunnelsid(tunnelbusinessId + "");
						} else {
							xcObejct.setTunnelsid(lspParticular.getAtunnelbusinessid() + "");
						}
					} else {
						xcObejct.setTunnelsid("1");
					}
					xcObejct.setName(lspParticular.getZtunnelbusinessid() + "");
					// }
					/** 左端口配置 */
					xcIFObject = new XcIFObject();
					xcIFObject.setName("1");
					xcIFObject.setCarrierif(super.getPortname(lspParticular.getZPortId()));
					xcIFObject.setInlabel(lspParticular.getFrontLabelValue() + "");
					xcIFObject.setOutlabel(lspParticular.getBackLabelValue() + "");
					xcIFObjects[0] = xcIFObject;
				} else if (lspParticular.getASiteId() == siteId) {
					xcObejct.setEgress(super.getSiteAdress(lspParticular.getZSiteId()));
					if (tunnel.getIsSingle() == 0) {
						if (lspParticular.getZSiteId() == tunnel.getZSiteId()) {
							int tunnelbusinessId = 0;
							for (Lsp lsp_job : jobTunnel.getLspParticularList()) {
								if (lsp_job.getZSiteId() == jobTunnel.getZSiteId()) {
									tunnelbusinessId = lsp_job.getZtunnelbusinessid();
									break;
								}
							}
							xcObejct.setTunneldid(tunnelbusinessId + "");
						} else {
							xcObejct.setTunneldid(lspParticular.getZtunnelbusinessid() + "");
						}

					} else {
						xcObejct.setTunneldid("1");
					}
					xcObejct.setName(lspParticular.getAtunnelbusinessid() + "");
					// }
					/** 右端口配置 */
					xcIFObject = new XcIFObject();
					xcIFObject.setName("2");
					xcIFObject.setCarrierif(super.getPortname(lspParticular.getAPortId()));
					xcIFObject.setInlabel(lspParticular.getBackLabelValue() + "");
					xcIFObject.setOutlabel(lspParticular.getFrontLabelValue() + "");
					xcIFObjects[1] = xcIFObject;
				}

			}
			xcObejct.setXcIFObjects(xcIFObjects);
			xcObejct.setOamMipObject(convertOammip(xcObejct, siteId,jobTunnel));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			lspLegList = null;
			xcIFObjects = null;
			xcIFObject = null;
		}
		return xcObejct;
	}

	/**
	 * 转换成tunnelObejct
	 * 
	 * @param tunnel
	 * @return
	 * @throws Exception
	 */
	private TunnelObject convertTunnelObject(Tunnel tunnel, String type, String action, CXActionObject cxActionObject,int siteId) throws Exception {

		if (null == tunnel) {
			throw new Exception("tunnel is null");
		}

		List<Lsp> lspLegList = null;
		Lsp lspParticular = null;
		LSPObject lspObjectMain = null;
		LSPObject lspObjectProtect = null;
		TunnelObject tunnelObject = null;
		QosInfo qosInfo=null;
		try {
			lspLegList = tunnel.getLspParticularList();
			tunnelObject = new TunnelObject();
			if (type.equals("a")) {
				lspParticular = lspLegList.get(0);
				tunnelObject.setName(lspParticular.getAtunnelbusinessid() + "");
				tunnelObject.setPeerid(lspParticular.getAtunnelbusinessid() + "");
				tunnelObject.setRole("ingress");
			} else if (type.equals("z")) {
				lspParticular = lspLegList.get(lspLegList.size() - 1);
				tunnelObject.setName(lspParticular.getZtunnelbusinessid() + "");
				tunnelObject.setPeerid(lspParticular.getZtunnelbusinessid() + "");
				tunnelObject.setRole("egress");
			}
			
			//取此网元对应的qos
			qosInfo=super.getQosInfo(EServiceType.TUNNEL.toString(), tunnel.getTunnelId(), siteId);
			tunnelObject.setQos(qosInfo.getQosname());
			//是否创建qos
			if(qosInfo.getStatus() == 1){
				tunnelObject.setCreateQos(true);
			}
			tunnelObject.setAdmin(tunnel.getTunnelStatus() == EActiveStatus.UNACTIVITY.getValue() ? "down" : "up");
			lspObjectMain = this.convertLSPObject(tunnel, lspParticular, type, "1", action);
			lspObjectProtect = this.convertLspProtect(tunnel, type, action);
			tunnelObject.getLSPObjects()[0] = lspObjectMain;
			tunnelObject.getLSPObjects()[1] = lspObjectProtect;

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			lspLegList = null;
			lspParticular = null;
			lspObjectMain = null;
			lspObjectProtect = null;
		}
		return tunnelObject;
	}

	/**
	 * 获取保护的lsp
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private LSPObject convertLspProtect(Tunnel tunnel, String type, String action) throws Exception {
		LSPObject lspObject = null;
		Tunnel protectTunnel = null;
		Lsp protectLsp = null;
		try {
			// 如果tunnel的类型是1:1
			if ("2".equals(UiUtil.getCodeById(Integer.parseInt(tunnel.getTunnelType())).getCodeValue())) {
				protectTunnel = tunnel.getProtectTunnel();
				if (null != protectTunnel) {
					lspObject = new LSPObject();

					// 获取保护的lsp对象
					for (Lsp lsp : protectTunnel.getLspParticularList()) {
						if ("a".equals(type)) {
							if (lsp.getASiteId() == protectTunnel.getASiteId()) {
								protectLsp = lsp;
								break;
							}
						} else {
							if (lsp.getZSiteId() == protectTunnel.getZSiteId()) {
								protectLsp = lsp;
								break;
							}
						}
					}

					if (null != protectLsp) {
						lspObject = this.convertLSPObject(tunnel, protectLsp, type, "2", action);
					}

				}
			}

		} catch (Exception e) {
			throw e;
		}
		return lspObject;
	}

	/**
	 * 转换驱动层的lspobject对象
	 * 
	 * @param lspParticular
	 *            lsp的leg
	 * @param type
	 *            类型 区分是a端还是z端
	 * @param name
	 *            lspobject的name 工作是1 保护是2
	 * @return
	 * @throws Exception
	 */
	private LSPObject convertLSPObject(Tunnel tunnel, Lsp lspParticular, String type, String name, String action) throws Exception {
		int siteId = 0;
		LSPObject lspObject = null;
		try {
			lspObject = new LSPObject();
			lspObject.setName(name);
			if (type.equals("a")) {

				lspObject.setPeer(lspParticular.getAoppositeId());
				// lspObject.setPeer(super.getSiteAdress(lspParticular.getZSiteId()));
				lspObject.setCarrierif(super.getPortname(lspParticular.getAPortId()));
				lspObject.setInlabel(lspParticular.getFrontLabelValue() + "");
				lspObject.setOutlabel(lspParticular.getBackLabelValue() + "");
				siteId = lspParticular.getASiteId();
			} else if (type.equals("z")) {
				lspObject.setPeer(lspParticular.getZoppositeId());
				// lspObject.setPeer(super.getSiteAdress(lspParticular.getASiteId()));
				lspObject.setCarrierif(super.getPortname(lspParticular.getZPortId()));
				lspObject.setInlabel(lspParticular.getBackLabelValue() + "");
				lspObject.setOutlabel(lspParticular.getFrontLabelValue() + "");
				siteId = lspParticular.getZSiteId();
			}
			lspObject.setAdmin(tunnel.getTunnelStatus() == EActiveStatus.UNACTIVITY.getValue() ? "down" : "up");
			if ("2".equals(name)) {
				lspObject.setOam(super.convertOamObject_mep("TUNNEL", tunnel.getProtectTunnelId(), siteId, tunnel.getProtectTunnel().getOamList(), action));
			} else {
				lspObject.setOam(super.convertOamObject_mep("TUNNEL", tunnel.getTunnelId(), siteId, tunnel.getOamList(), action));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return lspObject;
	}

	/**
	 * 获取此tunnel下所有网元ID
	 * 
	 * @param tunnel
	 *            tunnel对象
	 * @return siteID集合
	 * @throws Exception
	 */
	private List<Integer> getSiteIds(Tunnel tunnel) throws Exception {
		List<Integer> siteIds = null;
		try {
			siteIds = new ArrayList<Integer>();

			for (Lsp lspParticular : tunnel.getLspParticularList()) {
				if (!siteIds.contains(lspParticular.getASiteId())) {
					siteIds.add(lspParticular.getASiteId());
				}
				if (!siteIds.contains(lspParticular.getZSiteId())) {
					siteIds.add(lspParticular.getZSiteId());
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return siteIds;
	}

	/**
	 * 修改时获取action
	 * 
	 * @param tunnelUpdate
	 *            修改的tunnel对象
	 * @return
	 * @throws Exception
	 */
	private String getActionUpdate(Tunnel tunnel) throws Exception {

		try {
			if (tunnel.getBefore_activity() == EActiveStatus.UNACTIVITY.getValue()) {
				if (tunnel.getTunnelStatus() == EActiveStatus.UNACTIVITY.getValue()) {
					return "";
				} else {
					return TypeAndActionUtil.ACTION_INSERT;
				}
			} else {
				if (tunnel.getTunnelStatus() == EActiveStatus.UNACTIVITY.getValue()) {
					return TypeAndActionUtil.ACTION_DELETE;
				} else {
					return TypeAndActionUtil.ACTION_UPDATE;
				}
			}

		} catch (Exception e) {
			throw e;
		}
	}

	// private String convertPeerIp(String ip) {
	//
	// String ipLast = ip.substring(ip.lastIndexOf("."), ip.length());
	//
	// return "20.0.0" + ipLast;
	//
	// }

	/**
	 * tunne同步方法
	 * 
	 * @throws Exception
	 */
	@Override
	public Object synchro(int siteId) throws Exception {
		TunnelService_MB tunnelService = null;
		OperationObject operationObject = new OperationObject();
		QosCXServiceImpl qosCXServiceImpl=null;
		try {
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_TUNNEL, "");
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_XC, "");

			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				//同步前先同步qos
				qosCXServiceImpl=new QosCXServiceImpl();
				qosCXServiceImpl.synchro(siteId);
				//修改tunnel的激活状态
				tunnelService.update_activity(siteId, EActiveStatus.UNACTIVITY.getValue());
				for (CXActionObject cxActionObject : operationObject.getCxActionObjectList()) {
					if (TypeAndActionUtil.TYPE_TUNNEL.equals(cxActionObject.getType())) {
						this.synchro_db(cxActionObject.getTunnelObjectList(), siteId);
					} else {
						this.synchroXc_db(cxActionObject.getXcObjectList(), siteId);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			super.setQosObjectList(null);
			UiUtil.closeService_MB(tunnelService);
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
		TunnelObject tunnelObject = null;

		actionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
		actionObject.setCxNeObject(super.getCXNEObject(siteId));
		actionObject.setType(type);
		actionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
		if (type.equals(TypeAndActionUtil.TYPE_LSP)) {
			tunnelObject = new TunnelObject();
			tunnelObject.setName(tunneName);
			actionObject.setTunnelObject(tunnelObject);
		}
		operationObject.getCxActionObjectList().add(actionObject);

		tunnelObject = null;
	}

	/**
	 * 与数据库做同步
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void synchro_db(List<TunnelObject> tunnelObjectList, int siteId) throws Exception {

		if (null == tunnelObjectList) {
			throw new Exception("tunnelObjectList is null");
		}
		Tunnel tunnel = null;
		String role = null;
		List<LSPObject> lspObjectList = null;
		LSPObject lspObject_job = null; // 工作lsp
		LSPObject lspObject_pro = null; // 保护lsp
		int portId = 0;
		List<Lsp> lspInfoList = null;
		Lsp lspInfo = null;
		OamInfo oamInfo = null;
		SiteRoate siteRoate=null;
		SynchroUtil synchroUtil = new SynchroUtil();
		for (TunnelObject tunnelObject : tunnelObjectList) {
			lspInfoList = new ArrayList<Lsp>();
			lspInfo = new Lsp();
			lspObject_job = null;
			lspObject_pro = null;

			// 查询此tunnel下的lsp
			lspObjectList = this.synchro_lsp(siteId, tunnelObject.getName());
			for (LSPObject lspObject : lspObjectList) {
				if ("1".equals(lspObject.getName())) {
					lspObject_job = lspObject;
				} else {
					lspObject_pro = lspObject;
				}
			}
//System.out.println("line 992 ; siteId="+siteId+" ; tunnelObject.getName()="+tunnelObject.getName()+" ; lspObjectList="+lspObjectList.size());			
			if (null == lspObject_job) {
				throw new Exception("从设备查询工作的lsp出错");
			}
			PortInst portInst = super.getPortByName(siteId, lspObject_job.getCarrierif());
			if (null == portInst) {
				ExceptionManage.logDebug("同步TUNNEL失败，没有找到" + lspObject_job.getCarrierif() + "端口",this.getClass());
				continue;
			}

			portId = portInst.getPortId();

			tunnel = new Tunnel();
			tunnel.setSynchro(true);
			tunnel.setTunnelName("tunnel_" + super.getNowMS());
			tunnel.setTunnelStatus(EActiveStatus.ACTIVITY.getValue());
			tunnel.setJobStatus(tunnelObject.getOper());
			tunnel.setIsSingle(1);
			if (null == lspObject_pro) {
				tunnel.setTunnelType(UiUtil.getCodeByValue("PROTECTTYPE", "1").getId() + "");
			} else {
				tunnel.setTunnelType(UiUtil.getCodeByValue("PROTECTTYPE", "2").getId() + "");
			}

			if ("0".equals(tunnelObject.getRole())) {
				role = "ingress";
				tunnel.setASiteId(siteId);
				tunnel.setAPortId(portId);
				lspInfo.setAoppositeId(lspObject_job.getPeer());
				lspInfo.setASiteId(siteId);
				// lspInfo.setZSiteId(oppositeId);
				lspInfo.setAPortId(portId);
				lspInfo.setAtunnelbusinessid(Integer.parseInt(tunnelObject.getName()));
				lspInfo.setFrontLabelValue(Integer.parseInt(lspObject_job.getInlabel()));
				lspInfo.setBackLabelValue(Integer.parseInt(lspObject_job.getOutlabel()));
			} else {
				role = "egress";
				tunnel.setZSiteId(siteId);
				tunnel.setZPortId(portId);
				lspInfo.setZSiteId(siteId);
				lspInfo.setZoppositeId(lspObject_job.getPeer());
				// lspInfo.setASiteId(oppositeId);
				lspInfo.setZPortId(portId);
				lspInfo.setZtunnelbusinessid(Integer.parseInt(tunnelObject.getName()));
				lspInfo.setFrontLabelValue(Integer.parseInt(lspObject_job.getOutlabel()));
				lspInfo.setBackLabelValue(Integer.parseInt(lspObject_job.getInlabel()));
			}
			lspInfoList.add(lspInfo);
			tunnel.setLspParticularList(lspInfoList);
			tunnel.setQosList(super.qosInfo(tunnelObject.getQos(), siteId));

			if (null != lspObject_pro) {
				this.getProtectTunnel(tunnel, lspObject_pro, tunnelObject.getRole(), siteId);
			}

			if (null != lspObject_job.getOam() && null != lspObject_job.getOam().getMegid() && !"".equals(lspObject_job.getOam().getMegid())) {
				oamInfo = new OamInfo();
				oamInfo = convertOamInfo_mep(lspObject_job.getOam()).get(0);
				oamInfo.setOamType(OamTypeEnum.MEP);
				oamInfo.getOamMep().setObjType(EObjectType.TUNNEL.toString());
				oamInfo.getOamMep().setSiteId(siteId);
				oamInfo.getOamMep().setObjId(Integer.valueOf(tunnelObject.getName()));
				tunnel.getOamList().add(oamInfo);
			}

			if (null != lspObject_pro && null != lspObject_pro.getOam() && null != lspObject_pro.getOam().getMegid() && !"".equals(lspObject_pro.getOam().getMegid())) {
				oamInfo = new OamInfo();
				oamInfo = convertOamInfo_mep(lspObject_pro.getOam()).get(0);
				oamInfo.setOamType(OamTypeEnum.MEP);
				oamInfo.getOamMep().setObjType(EObjectType.TUNNEL.toString());
				oamInfo.getOamMep().setSiteId(siteId);
				oamInfo.getOamMep().setObjId(Integer.valueOf(tunnelObject.getName()));
				tunnel.getProtectTunnel().getOamList().add(oamInfo);
			}
			siteRoate=new SiteRoate();
			siteRoate.setType("tunnel");
			if(tunnelObject.getProtection().getApscmd()!=null&&!tunnelObject.getProtection().getApscmd().equals("")){
				siteRoate.setRoate(Integer.parseInt(tunnelObject.getProtection().getApscmd()));
			}
			
			siteRoate.setSiteId(siteId);
			// System.out.println("ok.......");
			synchroUtil.tunnelSynchro(tunnel, role, siteId);
		}

		tunnel = null;
		role = null;
		lspObjectList = null;
		lspObject_job = null; // 工作lsp
		lspObject_pro = null; // 保护lsp
		lspInfoList = null;
		lspInfo = null;
		siteRoate=null;
	}

	/**
	 * 
	 * 
	 * 获取保护的tunnel
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void getProtectTunnel(Tunnel tunnel, LSPObject lspObject, String role, int siteId) throws Exception {

		Tunnel tunnel_protect = new Tunnel();
		Lsp lspInfo = new Lsp();
		int portId = 0;
		List<Lsp> lspInfoList = new ArrayList<Lsp>();
		;

		PortInst portInst = super.getPortByName(siteId, lspObject.getCarrierif());
		if (null == portInst) {
			ExceptionManage.logDebug("同步TUNNEL保护失败，没有找到" + lspObject.getCarrierif() + "端口",this.getClass());
			return;
		}
		portId = portInst.getPortId();
		tunnel_protect.setASiteId(tunnel.getASiteId());
		tunnel_protect.setZSiteId(tunnel.getZSiteId());
		tunnel_protect.setTunnelType("0");

		if ("0".equals(role)) {
			lspInfo.setASiteId(siteId);
			// lspInfo.setZSiteId(oppositeId);
			lspInfo.setAoppositeId(lspObject.getPeer());
			lspInfo.setAPortId(portId);
			lspInfo.setAtunnelbusinessid(0);
			lspInfo.setFrontLabelValue(Integer.parseInt(lspObject.getInlabel()));
			lspInfo.setBackLabelValue(Integer.parseInt(lspObject.getOutlabel()));
		} else {
			lspInfo.setZSiteId(siteId);
			// lspInfo.setASiteId(oppositeId);
			lspInfo.setZoppositeId(lspObject.getPeer());
			lspInfo.setZPortId(portId);
			lspInfo.setZtunnelbusinessid(0);
			lspInfo.setFrontLabelValue(Integer.parseInt(lspObject.getOutlabel()));
			lspInfo.setBackLabelValue(Integer.parseInt(lspObject.getInlabel()));
		}

		lspInfoList.add(lspInfo);
		tunnel_protect.setLspParticularList(lspInfoList);
		tunnel_protect.setIsSingle(1);

		tunnel.setProtectTunnel(tunnel_protect);

	}

	/**
	 * 与数据库做同步 xc
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void synchroXc_db(List<XcObject> xcObjectList, int siteId) throws Exception {

		if (null == xcObjectList) {
			throw new Exception("xcObjectList is null");
		}

		Tunnel tunnel = null;
		String role = "xc";
		int portId_left = 0;
		int portId_right = 0;
		List<Lsp> lspInfoList = null;
		Lsp lspInfo_left = null;
		Lsp lspInfo_right = null;
		XcIFObject xcIFObject_left = null;
		XcIFObject xcIFObject_right = null;
		SynchroUtil synchroUtil = new SynchroUtil();
		for (XcObject xcObject : xcObjectList) {
			lspInfoList = new ArrayList<Lsp>();
			lspInfo_left = new Lsp();
			lspInfo_right = new Lsp();
			xcIFObject_left = xcObject.getXcIFObjects()[0];
			xcIFObject_right = xcObject.getXcIFObjects()[1];

			PortInst portInst = super.getPortByName(siteId, xcIFObject_left.getCarrierif());
			if (null == portInst) {
				ExceptionManage.logDebug("同步TUNNEL失败，没有找到" + xcIFObject_left.getCarrierif() + "端口",this.getClass());
				continue;
			}
			portId_left = portInst.getPortId();

			portInst = super.getPortByName(siteId, xcIFObject_right.getCarrierif());
			if (null == portInst) {
				ExceptionManage.logDebug("同步TUNNEL失败，没有找到" + xcIFObject_right.getCarrierif() + "端口",this.getClass());
				continue;
			}
			portId_right = portInst.getPortId();

			// 创建数据库tunnel对象
			tunnel = new Tunnel();
			tunnel.setTunnelName("tunnel_" + super.getNowMS());
			tunnel.setTunnelStatus(EActiveStatus.ACTIVITY.getValue());
			tunnel.setJobStatus(xcObject.getOper());
			tunnel.setIsSingle(1);
			tunnel.setTunnelType(UiUtil.getCodeByValue("PROTECTTYPE", "1").getId() + "");

			// 创建左端的LSP
			lspInfo_left.setZSiteId(siteId);
			lspInfo_left.setZPortId(portId_left);
			lspInfo_left.setZtunnelbusinessid(Integer.parseInt(xcObject.getName()));
			lspInfo_left.setFrontLabelValue(Integer.parseInt(xcIFObject_left.getOutlabel()));
			lspInfo_left.setBackLabelValue(Integer.parseInt(xcIFObject_left.getInlabel()));
			// lspInfo_left.setASiteId(siteId);
			lspInfo_left.setZoppositeId(xcObject.getIngress());
			lspInfoList.add(lspInfo_left);

			// 创建左端的LSP
			lspInfo_right.setASiteId(siteId);
			lspInfo_right.setAPortId(portId_right);
			lspInfo_right.setAtunnelbusinessid(Integer.parseInt(xcObject.getName()));
			lspInfo_right.setFrontLabelValue(Integer.parseInt(xcIFObject_right.getInlabel()));
			lspInfo_right.setBackLabelValue(Integer.parseInt(xcIFObject_right.getOutlabel()));
			// lspInfo_right.setZSiteId(siteId);
			lspInfo_right.setAoppositeId(xcObject.getEgress());
			lspInfoList.add(lspInfo_right);

			tunnel.setLspParticularList(lspInfoList);
			tunnel.setQosList(super.qosInfo(xcObject.getQos(), siteId));
			if (null != xcObject.getOamMipObject() && (!"".equals(xcObject.getOamMipObject().getMegid()) && null != xcObject.getOamMipObject().getMegid())) {
				OamInfo oamInfo = new OamInfo();
				oamInfo = convertOamInfo_mip(xcObject.getOamMipObject());
				oamInfo.setOamType(OamTypeEnum.MIP);
				oamInfo.getOamMip().setObjType(EObjectType.TUNNEL.toString());
				oamInfo.getOamMip().setSiteId(siteId);
				oamInfo.getOamMip().setObjId(Integer.valueOf(xcObject.getName()));
				tunnel.getOamList().add(oamInfo);
			}
			// System.out.println("ok.......");
			synchroUtil.tunnelSynchro(tunnel, role, siteId);
		}

		tunnel = null;
		role = null;
		lspInfoList = null;
		lspInfo_left = null;
		lspInfo_right = null;
		xcIFObject_left = null;
		xcIFObject_right = null;
	}

	/**
	 * 与设备同步LSP
	 * 
	 * synchro_lsp(这里用一句话描述这个方法的作用)
	 * 
	 * @author kk
	 * 
	 * @param TunnelObject
	 *            设备上的tunnel名称
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private List<LSPObject> synchro_lsp(int siteId, String tunnelname) throws Exception {
		OperationObject operationObject = new OperationObject();
		List<LSPObject> lspObject = null;
		try {
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_LSP, tunnelname);

			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				lspObject = operationObject.getCxActionObjectList().get(0).getLspObjectList();
			}
		} catch (Exception e) {
			throw e;
		}
		return lspObject;
	}
}
