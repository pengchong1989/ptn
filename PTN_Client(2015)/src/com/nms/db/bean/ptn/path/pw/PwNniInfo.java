package com.nms.db.bean.ptn.path.pw;

import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class PwNniInfo extends ViewDataObj {
	
	/**   
	*   
	* @since Ver 1.1   
	*/   
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int pwId;	//关联pw表主键
	private String pwName;//log日志显示时使用
	private int siteId;	//网元id
	private int pwBusinessId;	//pw设备名称，根据pwid和siteid从pw表中取
	private int exitRule;	//出口规则(陈晓)，关联code表主键 = 下话TAG行为（武汉）
	private String svlan;	//svlan=下话增加VLAN ID
	private int tpid;	//tpid，关联code表主键
	private String vlanpri;	//s-vlan pri=下话增加VLAN PRI
	private int horizontalDivision;	//水平分割 关联code表主键
	private int macAddressLearn;	//mac地址学习 关联code表主键
	private int tagAction;	//上话tag识别
	private int controlEnable;	//控制字使能 关联code表主键
	private int status;
	private int lanId=0;
	
	public String getPwName() {
		return pwName;
	}

	public void setPwName(String pwName) {
		this.pwName = pwName;
	}

	public int getLanId() {
		return lanId;
	}

	public void setLanId(int lanId) {
		this.lanId = lanId;
	}
	public int getPwStatus() {
		return status;
	}

	public void setPwStatus(int pwStatus) {
		this.status = pwStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPwId() {
		return pwId;
	}

	public void setPwId(int pwId) {
		this.pwId = pwId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getPwBusinessId() {
		return pwBusinessId;
	}

	public void setPwBusinessId(int pwBusinessId) {
		this.pwBusinessId = pwBusinessId;
	}

	public int getExitRule() {
		return exitRule;
	}

	public void setExitRule(int exitRule) {
		this.exitRule = exitRule;
	}

	public String getSvlan() {
		return svlan;
	}

	public void setSvlan(String svlan) {
		this.svlan = svlan;
	}

	public int getTpid() {
		return tpid;
	}

	public void setTpid(int tpid) {
		this.tpid = tpid;
	}

	public String getVlanpri() {
		return vlanpri;
	}

	public void setVlanpri(String vlanpri) {
		this.vlanpri = vlanpri;
	}

	public int getHorizontalDivision() {
		return horizontalDivision;
	}

	public void setHorizontalDivision(int horizontalDivision) {
		this.horizontalDivision = horizontalDivision;
	}

	public int getMacAddressLearn() {
		return macAddressLearn;
	}

	public void setMacAddressLearn(int macAddressLearn) {
		this.macAddressLearn = macAddressLearn;
	}

	public int getTagAction() {
		return tagAction;
	}

	public void setTagAction(int tagAction) {
		this.tagAction = tagAction;
	}

	public int getControlEnable() {
		return controlEnable;
	}

	public void setControlEnable(int controlEnable) {
		this.controlEnable = controlEnable;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void putObjectProperty() {
		SiteService_MB siteService = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			this.getClientProperties().put("id", this);
			this.getClientProperties().put("sitename", siteService.getSiteName(this.getSiteId()));
			this.getClientProperties().put("exitRule", UiUtil.getCodeById(this.getExitRule()).getCodeName());
			this.getClientProperties().put("svlan", this.getSvlan());
			this.getClientProperties().put("tpid", UiUtil.getCodeById(this.getTpid()).getCodeName());
			this.getClientProperties().put("vlanpri", this.getVlanpri());
			this.getClientProperties().put("horizontalDivision", UiUtil.getCodeById(this.getHorizontalDivision()).getCodeName());
			this.getClientProperties().put("macAddressLearn", UiUtil.getCodeById(this.getMacAddressLearn()).getCodeName());
			this.getClientProperties().put("controlEnable", UiUtil.getCodeById(this.getControlEnable()).getCodeName());
			this.getClientProperties().put("tagAction", UiUtil.getCodeById(this.getTagAction()).getCodeName());
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		
	}

//	private String getPortName(PwNniInfo pwNniInfo) {
//		PortService portService = null;
//		PortInst port = null;
//		try {
//			portService = (PortService) ConstantUtil.serviceFactory.newService(Services.PORT);
//
//			port = new PortInst();
////			port.setPortId(pwNniInfo.getPortId());
//			port.setSiteId(pwNniInfo.getSiteId());
//			port = portService.select(port).get(0);
//
//			return port.getPortName() + "/" + port.getNumber();
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		} finally {
//			portService = null;
//		}
//		return null;
//	}
//
//	private String getPwName(PwNniInfo pwNniInfo) {
//		PwInfoService pwinfoService = null;
//		PwInfo pwinfo = null;
//		try {
//			pwinfoService = (PwInfoService) ConstantUtil.serviceFactory.newService(Services.PwInfo);
//
//			if (pwNniInfo.getPwId() != 0) {
//				pwinfo = new PwInfo();
//				pwinfo.setPwId(pwNniInfo.getPwId());
//				pwinfo = pwinfoService.selectBypwid_notjoin(pwinfo);
//				if (pwinfo.getName() == null) {
//					return null;
//				}
//				return pwinfo.getPwName();
//			} else {
//				return null;
//			}
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		} finally {
//			pwinfoService = null;
//		}
//		return null;
//	}
//
//	private String getSiteName(PwNniInfo pwNniInfo) {
//		SiteService siteService = null;
//		try {
//			siteService = (SiteService) ConstantUtil.serviceFactory.newService(Services.SITE);
//			return siteService.select(pwNniInfo.getSiteId()).getCellDescribe();
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		} finally {
//			siteService = null;
//		}
//		return null;
//	}
//
//	private String getCodeNameByValueAndKey(int value, String str) {
//
//		CodeGroup codeGroup = null;
//		List<Code> codeList = null;
//		try {
//			codeGroup = UiUtil.getCodeGroupByIdentity(str);
//			codeList = codeGroup.getCodeList();
//			for (Code code : codeList) {
//				if (Integer.valueOf(code.getCodeValue()) == value)
//					return code.getCodeName();
//			}
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		} finally {
//			codeGroup = null;
//			codeList = null;
//		}
//		return "";
//	}
}
