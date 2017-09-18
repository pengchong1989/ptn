package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.enums.EPwType;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.LspInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.ptn.port.PortLagService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class OamCXServiceImpl extends CXOperationBase implements OperationServiceI {
	/**
	 * 获得Operation对象
	 * 
	 * @param siteId
	 * @param action
	 * @return
	 * @throws Exception
	 */
	public OperationObject getOperationObject(Object object, String action) throws Exception {
		CXActionObject cxActionObject = null;
		OamInfo oam = null;
		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();

			oam = (OamInfo) object;
			cxActionObject = this.getCXObject(oam, action);
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			oam = null;
			cxActionObject = null;
		}
		return operationObject;
	}

	/**
	 * 获得CX对象
	 * 
	 * @param oSPFInfo
	 * @param action
	 * @return
	 * @throws Exception
	 */
	private CXActionObject getCXObject(OamInfo oam, String action) throws Exception {

		if (oam == null) {
			throw new Exception("oam is null");
		}

		if (action == null || action.equals("")) {
			throw new Exception("action is  null");
		}

		CXActionObject cxActionObject = null;
		OperationObject operationObject = null;
		int siteId=0;
		try {
			operationObject = new OperationObject();
			cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setAction(action);
			if (oam.getOamType() == OamTypeEnum.MIP) {
				cxActionObject.setType(TypeAndActionUtil.TYPE_MIPOAM);
				cxActionObject.setOamMipObject(convertOamObject_mip(oam));
				siteId=oam.getOamMip().getSiteId();
			} else {
				cxActionObject.setType(TypeAndActionUtil.TYPE_OAM);
				cxActionObject.setOamObject(convertOamObject_mep(oam));
				siteId=oam.getOamMep().getSiteId();
			}
			if (action.equals(TypeAndActionUtil.ACTION_DELETE)) {
				//删除OAM
				getDeleteOamCxActionObject(cxActionObject, oam);
			} else {
//				cxActionObject.setOamPath(oam.getOamPath());
//				cxActionObject.setOamType(oam.getOamObjType());
				getCreatOamCxActionObject(cxActionObject, oam);
	
			}
			cxActionObject.setCxNeObject(super.getCXNEObject(siteId));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
		}
		return cxActionObject;
	}

	/**
	 * 获取新建OAM CX对象
	 * @param cxActionObject
	 * @param oam
	 * @throws Exception
	 */
	private void getCreatOamCxActionObject(CXActionObject cxActionObject, OamInfo oam) throws Exception {
		
		LspInfoService_MB lspService = null;
		TunnelService_MB tunnelService = null;
		AcPortInfoService_MB acInfoService = null;
		ElineInfoService_MB elineService = null;
		PortLagService_MB portLagService = null;
		PortService_MB portService  = null;
		
		try {
			if (TypeAndActionUtil.TYPE_OAM.equals(cxActionObject.getType())) {
				if (oam.getOamMep().getObjType().equals(EServiceType.PW.toString())) {
					//PW
					PwInfo pwInfo = getPwInfoByOam(oam);
					cxActionObject.setOamType("PW");
					oam.getOamMep().setServiceId(pwInfo.getPwId());
					if (oam.getOamMep().getSiteId() == pwInfo.getASiteId()) {
						oam.getOamMep().setObjId(pwInfo.getApwServiceId());
					} else if (oam.getOamMep().getSiteId() == pwInfo.getZSiteId()) {
						oam.getOamMep().setObjId(pwInfo.getZpwServiceId());
					}
					if (EPwType.forms(pwInfo.getType().getValue()) == EPwType.ETH) {
						cxActionObject.setOamType("PWETH");
						cxActionObject.setOamPath("ne/interfaces/pweth/" + oam.getOamMep().getObjId());
					} else if (EPwType.forms(pwInfo.getType().getValue()) == EPwType.PDH) {
						cxActionObject.setOamType("PWPDH");
						cxActionObject.setOamPath("ne/interfaces/pwpdh/" + oam.getOamMep().getObjId());
					} else if (EPwType.forms(pwInfo.getType().getValue()) == EPwType.SDH) {
						cxActionObject.setOamType("PWSDH");
						cxActionObject.setOamPath("ne/interfaces/pwsdh/" + oam.getOamMep().getObjId());
					} else if (EPwType.forms(pwInfo.getType().getValue()) == EPwType.SDH_PDH) {
						if (oam.getOamMep().getSiteId() == pwInfo.getASiteId()) {
							cxActionObject.setOamType("PWSDH");
							cxActionObject.setOamPath("ne/interfaces/pwsdh/" + oam.getOamMep().getObjId());
						} else {
							cxActionObject.setOamType("PWPDH");
							cxActionObject.setOamPath("ne/interfaces/pwpdh/" + oam.getOamMep().getObjId());
						}
					} else if (EPwType.forms(pwInfo.getType().getValue()) == EPwType.PDH_SDH) {
						if (oam.getOamMep().getSiteId() == pwInfo.getASiteId()) {
							cxActionObject.setOamType("PWPDH");
							cxActionObject.setOamPath("ne/interfaces/pwpdh/" + oam.getOamMep().getObjId());
						} else {
							cxActionObject.setOamType("PWSDH");
							cxActionObject.setOamPath("ne/interfaces/pwsdh/" + oam.getOamMep().getObjId());
						}
					}

				} else if (oam.getOamMep().getObjType().equals(EServiceType.TUNNEL.toString())) {
					//TUNNEL
					//LSP
					lspService = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO);
					tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
					Tunnel tunnel = getTunnelInfoByOam(oam);
					Tunnel tunnelProtect = new Tunnel();
					List<Tunnel> tunnelList;
					if("0".equals(tunnel.getTunnelType())){
						tunnelProtect.setProtectTunnelId(tunnel.getTunnelId());
						tunnelList = tunnelService.select_nojoin(tunnelProtect);
						if(null!=tunnelList&&tunnelList.size()>0)
							tunnel = tunnelList.get(0);
					}
					List<Lsp> lspList = lspService.selectBySiteIdAndTunnelId(oam.getOamMep().getSiteId(), tunnel.getTunnelId());
					for (Lsp lsp : lspList) {
						if (oam.getOamMep().getSiteId() == lsp.getASiteId()) {
							if (oam.getOamMep().getObjId() > 0) {
								oam.setOamPath("ne/interfaces/tunnel/" + lsp.getAtunnelbusinessid() + "/1");
								cxActionObject.setOamPath("ne/interfaces/tunnel/" + lsp.getAtunnelbusinessid() + "/1");
							} else {
								oam.setOamPath("ne/interfaces/tunnel/" + lsp.getAtunnelbusinessid() + "/2");
								cxActionObject.setOamPath("ne/interfaces/tunnel/" + lsp.getAtunnelbusinessid() + "/2");
							}
						} else if (oam.getOamMep().getSiteId() == lsp.getZSiteId()) {
							if (oam.getOamMep().getObjId() > 0) {
								oam.setOamPath("ne/interfaces/tunnel/" + lsp.getZtunnelbusinessid() + "/1");
								cxActionObject.setOamPath("ne/interfaces/tunnel/" + lsp.getZtunnelbusinessid() + "/1");
							} else {
								oam.setOamPath("ne/interfaces/tunnel/" + lsp.getZtunnelbusinessid() + "/2");
								cxActionObject.setOamPath("ne/interfaces/tunnel/" + lsp.getZtunnelbusinessid() + "/2");
							}
						}
					}
					
					cxActionObject.setOamType("TUNNEL");
				} else if (oam.getOamMep().getObjType().equals(EServiceType.ELINE.toString())) {
					List<ElineInfo> elineInfoList;
					ElineInfo elineInfo = null;
					try {
						elineInfo = new ElineInfo();
						elineInfo.setId(oam.getOamMep().getServiceId());
						acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
						elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
						elineInfoList = elineService.selectByCondition_nojoin(elineInfo);
						if(null!=elineInfoList&&elineInfoList.size()>0){
							elineInfo = elineInfoList.get(0);
						}
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
					oam.getOamMep().setObjType("ELINE");
					oam.getOamMep().setServiceId(elineInfo.getId());
					if (oam.getOamMep().getSiteId() == elineInfo.getaSiteId()) {
						oam.getOamMep().setObjId(elineInfo.getaXcId());
					} else if (oam.getOamMep().getSiteId() == elineInfo.getzSiteId()) {
						oam.getOamMep().setObjId(elineInfo.getzXcId());
					}

					// ac主键
					int acid = 0;
					if (oam.getOamMep().getSiteId() == elineInfo.getaSiteId()) {
						acid = elineInfo.getaAcId();
						oam.setOamType(OamTypeEnum.AMEP);
					} else if (oam.getOamMep().getSiteId() == elineInfo.getzSiteId()) {
						acid = elineInfo.getzAcId();
						oam.setOamType(OamTypeEnum.ZMEP);
					}
					// 通过主键去数据库查询AC对象
					AcPortInfo acPortInfo = new AcPortInfo();
					List<AcPortInfo> acPortInfoList = new ArrayList<AcPortInfo>();
					acPortInfo.setId(acid);
					acPortInfoList = acInfoService.queryByAcPortInfo(acPortInfo);
					if (null == acPortInfoList || acPortInfoList.size() != 1) {
						throw new Exception("查询AC出错");
					}
					acPortInfo = acPortInfoList.get(0);

					// 创建AC设备节点路径。
					oam.setOamObjType("AC");
					if (acPortInfo.getPortId() > 0) {
						 portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
						oam.setOamPath("ne/interfaces/eth/" + portService.getPortname(acPortInfo.getPortId()) + "/" + acPortInfo.getAcBusinessId());
					} else {
						portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
						PortLagInfo portLagInfo = new PortLagInfo();
						portLagInfo.setId(acPortInfo.getLagId());
						List<PortLagInfo> portLagInfoList = portLagService.selectByCondition(portLagInfo);
						if (null == portLagInfoList || portLagInfoList.size() != 1) {
							throw new Exception("查询lag出错");
						}

						oam.setOamPath("ne/interfaces/lag/" + portLagInfoList.get(0).getLagID() + "/" + acPortInfo.getAcBusinessId());
					}
					cxActionObject.setOamType(oam.getOamType().toString());
					cxActionObject.setOamPath(oam.getOamPath());
				} else if (oam.getOamMep().getObjType().equals(EServiceType.SECTION.toString())) {
					//段
					PortInst portInst = getPortInstByPortId(oam.getOamMep().getObjId());
					cxActionObject.setOamPath("ne/interfaces/eth/" + portInst.getPortName());
					cxActionObject.setOamType("ETH");
					
				}
			} else if (TypeAndActionUtil.TYPE_MIPOAM.equals(cxActionObject.getType())) {
				//XC
				cxActionObject.setOamPath("ne/protocols/mpls/xc/" + oam.getOamMip().getObjId());
				cxActionObject.setOamType("TUNNEL");
			}
		} catch (Exception e) {
          throw e;
		}finally{
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(lspService);
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(portLagService);
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(acInfoService);
		}
	}
	
	/**
	 * 获取删除OAM CX对象
	 * @param cxActionObject
	 * @param oam
	 * @throws Exception
	 */
	private void getDeleteOamCxActionObject(CXActionObject cxActionObject, OamInfo oam) throws Exception {
		if (TypeAndActionUtil.TYPE_OAM.equals(cxActionObject.getType())) {
			if (oam.getOamMep().getObjType().equals(EServiceType.PW.toString())) {
				//PW
				PwInfo pwInfo = getPwInfoByOam(oam);
				String PwType = "";
				if (pwInfo.getType() == EPwType.ETH) {
					PwType = "pweth";
				} else if (pwInfo.getType() == EPwType.PDH || pwInfo.getType() == EPwType.PDH_SDH) {
					PwType = "pwpdh";
				} else if (pwInfo.getType() == EPwType.SDH || pwInfo.getType() == EPwType.SDH_PDH) {
					PwType = "pwsdh";
				}
				cxActionObject.setOamPath("ne/interfaces/" + PwType + "/" + oam.getOamMep().getObjId());
				cxActionObject.setOamType("PW");
			} else if (oam.getOamMep().getObjType().equals(EServiceType.TUNNEL.toString())) {
				//TUNNEL
				//LSP
				Tunnel tunnel = getTunnelInfoByOam(oam);
				if ("0".equals(tunnel.getTunnelType())) {
					//保护
					cxActionObject.setOamPath("ne/interfaces/tunnel/" + oam.getOamMep().getObjId() + "/" + "2");
				} else {
					//工作
					cxActionObject.setOamPath("ne/interfaces/tunnel/" + oam.getOamMep().getObjId() + "/" + "1");
				}
				cxActionObject.setOamType("TUNNEL");
			} else if (oam.getOamMep().getObjType().equals(EServiceType.ELINE.toString())) {
				//ELINE AC
				PortInst portinst = null;
				PortLagInfo portLagInfo = null;
				String portName = "";
				String portType = "";
				AcPortInfo acPortInfo = getAcInfoByOam(oam);
				if (0 != acPortInfo.getPortId()) {
					portinst = getPortInstByPortId(acPortInfo.getPortId());
				} else if (0 != acPortInfo.getLagId()) {
					portLagInfo = getLagInfoByAcInfo(acPortInfo);
				}
				if (portinst != null) {
					portName = portinst.getPortName();
					portType = "eth";
				} else if (portLagInfo != null) {
					portName = portLagInfo.getLagID() + "";
					portType = "lag";
				}
				cxActionObject.setOamPath("ne/interfaces/" + portType + "/" + portName + "/" + acPortInfo.getAcBusinessId());
				cxActionObject.setOamType("ELINE");
			} else if (oam.getOamMep().getObjType().equals(EServiceType.SECTION.toString())) {
				//段
				PortInst portInst = getPortInstByPortId(oam.getOamMep().getObjId());
				cxActionObject.setOamPath("ne/interfaces/eth/" + portInst.getPortName());
				cxActionObject.setOamType("ETH");
			}
		} else if (TypeAndActionUtil.TYPE_MIPOAM.equals(cxActionObject.getType())) {
			//XC
			cxActionObject.setOamPath("ne/protocols/mpls/xc/" + oam.getOamMip().getObjId());
			cxActionObject.setOamType("TUNNEL");
		}

	}

	/**
	 * 通过AC中PortId获得LagInfo
	 * @param acPortInfo
	 * @return
	 * @throws Exception
	 */
	private PortLagInfo getLagInfoByAcInfo(AcPortInfo acPortInfo) throws Exception {
		PortLagInfo portLaginst = new PortLagInfo();
		PortLagService_MB portLagService = null;
		try {
			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			int portId = acPortInfo.getPortId();
			portLaginst.setId(portId);
			List<PortLagInfo> portLagList = portLagService.selectLAGByCondition(portLaginst);
			
			if (portLagList.size() > 0) {
				portLaginst = portLagList.get(0);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portLagService);
		}
		return portLaginst;
	}

	/**
	 * 通过OAM获得AcInfo
	 * @param oam
	 * @return
	 * @throws Exception
	 */
	private AcPortInfo getAcInfoByOam(OamInfo oam) throws Exception {
		ElineInfo eLineInfo = new ElineInfo();
		AcPortInfo acPortInfo = new AcPortInfo();
		ElineInfoService_MB eLineService = null;
		AcPortInfoService_MB acService = null;
		try {
			eLineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			acService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			
			int acId = 0;
			eLineInfo.setId(oam.getOamMep().getServiceId());
			List<ElineInfo> eLineInfoList = eLineService.selectByCondition_nojoin(eLineInfo);
			if (eLineInfoList.size() > 0) {
				eLineInfo = eLineInfoList.get(0);
			}
			if (eLineInfo.getaSiteId() == oam.getOamMep().getSiteId()) {
				acId = eLineInfo.getaAcId();
			} else if (eLineInfo.getzSiteId() == oam.getOamMep().getSiteId()) {
				acId = eLineInfo.getzAcId();
			}
			acPortInfo.setId(acId);
			List<AcPortInfo> acInfoList = acService.selectByCondition(acPortInfo);
			if (acInfoList.size() > 0) {
				acPortInfo = acInfoList.get(0);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(eLineService);
			UiUtil.closeService_MB(acService);
		}
		return acPortInfo;
	}

	/**
	 * 通过PortID获得PortInst
	 * @param PortId
	 * @return
	 * @throws Exception
	 */
	private PortInst getPortInstByPortId(int PortId) throws Exception {
		
		PortService_MB portService = null;
		PortInst portinst = new PortInst();
		
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			int portId = PortId;
			portinst.setPortId(portId);
			List<PortInst> portInstList = portService.select(portinst);
			
			if (portInstList.size() > 0) {
				portinst = portInstList.get(0);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
		return portinst;
	}

	/**
	 * 通过OAM获得TunnelInfo
	 * @param oam
	 * @return
	 * @throws Exception
	 */
	private Tunnel getTunnelInfoByOam(OamInfo oam) throws Exception {
		
		Tunnel tunnel = new Tunnel();
		TunnelService_MB tunnelService = null;
		try {
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			
			tunnel.setTunnelId(oam.getOamMep().getServiceId());
			List<Tunnel> list = tunnelService.select_nojoin(tunnel);
			if (list.size() > 0) {
				tunnel = list.get(0);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(tunnelService);
		}
		return tunnel;
	}

	/**
	 * 通过OAM获得PWInfo
	 * @param oam
	 * @return
	 * @throws Exception
	 */
	private PwInfo getPwInfoByOam(OamInfo oam) throws Exception {
		
		PwInfo pwInfo = new PwInfo();
		PwInfoService_MB pwInfoService = null;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			
			pwInfo.setPwId(oam.getOamMep().getServiceId());
			pwInfo = pwInfoService.selectBypwid_notjoin(pwInfo);
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(pwInfoService);
		}
		return pwInfo;
	}

	@Override
	public String excutionDelete(List objectList) throws Exception {
		OperationObject operationObject = null;
		List<OamInfo> oamList = null;
		String result = null;
		OamInfoService_MB oamInfoService = null;
		try {
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			oamList = (List<OamInfo>) objectList;
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,oamList);
			if(null!=siteCheck){
				return siteCheck;
			}
			for (OamInfo oam : oamList) {
				operationObject = this.getOperationObject(oam, TypeAndActionUtil.ACTION_DELETE);
				super.sendAction(operationObject);
				super.verification(operationObject);

				if (operationObject.isSuccess()) {
					if(!oam.isDataDownLoad()){ //如果是数据下载业务不进行数据库操作
						oamInfoService.delete(oam);
					}
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					result = super.getErrorMessage(operationObject);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
		return result;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		OperationObject operationObject = null;
		OamInfo oam = null;
		String result = null;
		OamInfoService_MB oamInfoService = null;
		try {
			oam = (OamInfo) object;
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE,oam);
			if(null!=siteCheck){
				return siteCheck;
			}
			operationObject = this.getOperationObject(oam, TypeAndActionUtil.ACTION_INSERT);
			super.sendAction(operationObject);
			super.verification(operationObject);

			if (operationObject.isSuccess()) {
				if(!oam.isDataDownLoad()){ //如果是数据下载业务不进行数据库操作
					oamInfoService.saveOrUpdate(oam);
				}
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
		return result;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		OperationObject operationObject = null;
		OamInfo oam = null;
		String result = null;
		OamInfoService_MB oamInfoService = null;
		try {
			oam = (OamInfo) object;
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE,oam);
			if(null!=siteCheck){
				return siteCheck;
			}
			

			operationObject = this.getOperationObject(oam, TypeAndActionUtil.ACTION_UPDATE);
			super.sendAction(operationObject);
			super.verification(operationObject);

			if (operationObject.isSuccess()) {
				if(!oam.isDataDownLoad()){ //如果是数据下载业务不进行数据库操作
					oamInfoService.saveOrUpdate(oam);
				}
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
		return result;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		return null;
	}

}
