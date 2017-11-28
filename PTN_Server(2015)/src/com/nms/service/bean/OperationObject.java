package com.nms.service.bean;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.service.bean.AlarmObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.PerformanceObject;
import com.nms.drive.service.bean.TMCTunnelProtectObject;
import com.nms.drive.service.bean.UpgradeManageObject;
import com.nms.drive.service.impl.bean.PtnDataObject;
import com.nms.drivechenxiao.service.impl.bean.CXPtnDataObject;
import com.nms.service.impl.util.TypeAndActionUtil;

/**
 * 下发设备与回调对象
 * 
 * @author Administrator
 * 
 */
public class OperationObject {

	private boolean isSuccess;// 设备返回是否成功标志
	private List<ActionObject> actionObjectList = new ArrayList<ActionObject>();// 武汉驱动事件对象
	private List<CXActionObject> cxActionObjectList = new ArrayList<CXActionObject>();// 晨晓驱动事件对象
	private int errorLabel = 0;// 用于区分 不成功的标志(0:表示 设备返回来的不成功，1:表示超时而导致的不成功)

	public List<ActionObject> getActionObjectList() {
		return actionObjectList;
	}

	public void setActionObjectList(List<ActionObject> actionObjectList) {
		this.actionObjectList = actionObjectList;
	}

	public List<CXActionObject> getCxActionObjectList() {
		return cxActionObjectList;
	}

	public void setCxActionObjectList(List<CXActionObject> cxActionObjectList) {
		this.cxActionObjectList = cxActionObjectList;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public int getErrorLabel() {
		return errorLabel;
	}

	public void setErrorLabel(int errorLabel) {
		this.errorLabel = errorLabel;
	}

	
	public void returnResult(int actionId, PtnDataObject ptnDataObject) 
	{
		for (ActionObject actionObject : this.getActionObjectList())
		{
			if (actionObject.getActionId() == actionId) 
			{
				actionObject.setStatus(ptnDataObject.getStatus());
				if (ptnDataObject.getReturnPtnObj() != null) 
				{
					if (actionObject.getType() != null && actionObject.getType().equals("queryPerforBySite")) {
						//当前性能
						if (ptnDataObject.getReturnPtnObj() instanceof PerformanceObject) {
							PerformanceObject obj = (PerformanceObject) ptnDataObject.getReturnPtnObj();
							actionObject.setPerformanceObject(obj);
						}
					} else if (actionObject.getType() != null && actionObject.getType().equals("hisPerformanceBySite")) {
						//历史性能
						if (ptnDataObject.getReturnPtnObj() instanceof PerformanceObject) {
							PerformanceObject obj = (PerformanceObject) ptnDataObject.getReturnPtnObj();
							actionObject.setPerformanceObject(obj);
						}
					} else if (actionObject.getType() != null && actionObject.getType().equals("alarm")) {
						AlarmObject obj = (AlarmObject) ptnDataObject.getReturnPtnObj();
						actionObject.setAlarmObject(obj);
					} else if (actionObject.getType() != null && actionObject.getType().equals("queryCurrAlarmBySite")) {
						//查询告警
						if (ptnDataObject.getReturnPtnObj() instanceof AlarmObject) {
							AlarmObject obj = (AlarmObject) ptnDataObject.getReturnPtnObj();
							actionObject.setAlarmObject(obj);
						}
					} else if (actionObject.getType() != null && actionObject.getType().equals("siteAttribute")) {
						//查询网元状态
						if (ptnDataObject.getReturnPtnObj() instanceof NEObject) {
							NEObject neObject = (NEObject) ptnDataObject.getReturnPtnObj();
							actionObject.setNeObject(neObject);
						}
					}else if (actionObject.getType() != null && actionObject.getType().equals("queryLocaltionSn")) {
						//查询本IP的SN
						if (ptnDataObject.getReturnPtnObj() instanceof NEObject) {
							NEObject neObject = (NEObject) ptnDataObject.getReturnPtnObj();
							actionObject.setNeObject(neObject);
						}
					} else if (actionObject.getType() != null && actionObject.getType().equals("uploadConfig")) {
						//数据上载
						if(ptnDataObject.getReturnPtnObj() instanceof byte[]){
							byte[] bs = (byte[]) ptnDataObject.getReturnPtnObj();
							actionObject.setBs(bs);
						}
					} else if (actionObject.getType() != null && actionObject.getType().equals("queryAdjoinSn")) {
						//查询相邻SN
						if(ptnDataObject.getReturnPtnObj() instanceof List){
							List<NEObject> neObjects = (List<NEObject>) ptnDataObject.getReturnPtnObj();
							actionObject.setNeObjects(neObjects);
						}
					} else if (actionObject.getType() != null && actionObject.getType().equals("querySoftwareSummary")) {
						//查询软件摘要
						if(ptnDataObject.getReturnPtnObj() instanceof List){
							List<UpgradeManageObject> upgradeManageObjects = (List<UpgradeManageObject>) ptnDataObject.getReturnPtnObj();
							actionObject.setUpgradeManageObjects(upgradeManageObjects);
						}	
					} else if (actionObject.getType() != null && actionObject.getType().equals("queryRemoteSn")) {
						//查询本IP的SN
						if (ptnDataObject.getReturnPtnObj() instanceof NEObject) {
							NEObject neObject = (NEObject) ptnDataObject.getReturnPtnObj();
							actionObject.setNeObject(neObject);
						}
					} else if (actionObject.getType() != null && actionObject.getType().equals("macvlan")) {
						//查询vlan mac
						if (ptnDataObject.getReturnPtnObj() instanceof ActionObject) {
							ActionObject actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
							actionObject.setBs(actionObject2.getBs());
						}
					} else if(ptnDataObject.getReturnPtnObj() != null && ptnDataObject.getReturnPtnObj() instanceof ActionObject){
						//同步返回值,查询分块状态
						synchroResult(actionObject, ptnDataObject);
					}
				}
			}
		}
	}
	
	/**
	 * 同步的返回值
	 * @param actionObject
	 * @param ptnDataObject
	 */
	private void synchroResult(ActionObject actionObject,PtnDataObject ptnDataObject){
		if (actionObject.getType() != null && actionObject.getType().equals("tunnelSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setTunnelObjectList(actionObject2.getTunnelObjectList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("elanSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setEtreeObjectList(actionObject2.getEtreeObjectList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("pwSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setPwObjectList(actionObject2.getPwObjectList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("elineSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setElineObjectList(actionObject2.getElineObjectList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("etreeSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setEtreeObjectList(actionObject2.getEtreeObjectList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("tunnelProtectSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setLSPProtectionList(actionObject2.getLSPProtectionList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("e1Synchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setE1Object(actionObject2.getE1Object());
		} else if (actionObject.getType() != null && actionObject.getType().equals("portLagSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setPortLAGList(actionObject2.getPortLAGList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("portSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setProtObjectList(actionObject2.getProtObjectList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("allStatus")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setSiteStatusObject(actionObject2.getSiteStatusObject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("tmsOamSynchro")) {
			ActionObject actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setTmsoamObjects(actionObject2.getTmsoamObjects());
		} else if (actionObject.getType() != null && actionObject.getType().equals("ethlinkOamSynchro")) {
			ActionObject actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setEthLinkOAMObject(actionObject2.getEthLinkOAMObject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("ethOamSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setEthoamAllObject(actionObject2.getEthoamAllObject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("oamStatus")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setOamStatusObject(actionObject2.getOamStatusObject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("smartFanSynchro")) {
			ActionObject actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setSmartFanObjectList(actionObject2.getSmartFanObjectList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("ethServiceSynchro")) {
			ActionObject actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setEthServiceObject(actionObject2.getEthServiceObject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("mspwSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setMsPwObjects(actionObject2.getMsPwObjects());
		} else if (actionObject.getType() != null && actionObject.getType().equals("portPriSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setProtObjectList(actionObject2.getProtObjectList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("portDiscardSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setEthServiceObject(actionObject2.getEthServiceObject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("staticUniSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setStaticUnicast(actionObject2.getStaticUnicast());
		} else if (actionObject.getType() != null && actionObject.getType().equals("allConfigSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setGlobalObject(actionObject2.getGlobalObject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("dualSynchro")) {
		    List<TMCTunnelProtectObject>  TMCTunnelProtectObjectList = ((ActionObject)ptnDataObject.getReturnPtnObj()).getTmcTunnelProectList();
		    actionObject.setTmcTunnelProectList(TMCTunnelProtectObjectList);
		} else if (actionObject.getType() != null && actionObject.getType().equals("clockFrequSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setClockObject(actionObject2.getClockObject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("phbMexpSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setPhbToEXPObject(actionObject2.getPhbToEXPObject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("exp2phbSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setExpToPHBObject(actionObject2.getExpToPHBObject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("pmlimiteSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setPmValueLimiteObject(actionObject2.getPmValueLimiteObject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("port2LayerSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setPort2LayerObjectList(actionObject2.getPort2LayerObjectList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("aclSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setAclObject(actionObject2.getAclObject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("secondMacStudySynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setSecondMacStudyObjectList(actionObject2.getsecondMacStudyObjectList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("loopProtectSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setRoundProtectionObject(actionObject2.getRoundProtectionObject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("timeSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setTimesyncobject(actionObject2.getTimesyncobject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("l2cpSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setL2CPinfoObject(actionObject2.getL2CPinfoObject());
		} else if (actionObject.getType() != null && actionObject.getType().equals("blackmaclistSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setMacManageObjectList(actionObject2.getMacManageObjectList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("blackwhitemacSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setBlackWhiteMacObjectList(actionObject2.getBlackWhiteMacObjectList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("cccSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setCccObjectList(actionObject2.getCccObjectList());
		} else if (actionObject.getType() != null && actionObject.getType().equals("bfdSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setBfdObjectList(actionObject2.getBfdObjectList());
		}else if (actionObject.getType() != null && actionObject.getType().equals("arpSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setArpObjectList(actionObject2.getArpObjectList());
		}else if (actionObject.getType() != null && actionObject.getType().equals("ospfSynchro")) {
			ActionObject actionObject2 = new ActionObject();
			actionObject2 = (ActionObject) ptnDataObject.getReturnPtnObj();
			actionObject.setOspFinfoWhObejects(actionObject2.getOspFinfoWhObejects());
		}
	}
	
	public void returnResult(int actionId, CXPtnDataObject cXPtnDataObject) {
		if (this.getActionObjectList() == null) {
			System.out.println("this.getActionObjectList() is null");
		}

		for (CXActionObject cxActionObject : this.getCxActionObjectList()) {
			if (cxActionObject.getActionId() == actionId) {
				if (cxActionObject.getType() != null) {
					if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_ALARM)) {
						cxActionObject.setAlarmObjectList(cXPtnDataObject.getAlarmObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PERFORMANCE)) {
						cxActionObject.setPersvrobjectList(cXPtnDataObject.getPersvrObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_CARD)) {
						cxActionObject.setSlotObjectArray(cXPtnDataObject.getSlotObjects());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_SITE)) {
						cxActionObject.setPtnNeObject(cXPtnDataObject.getPtnNeObject());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_TUNNEL)) {
						cxActionObject.setTunnelObjectList(cXPtnDataObject.getTunnelObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_LSP)) {
						cxActionObject.setLspObjectList(cXPtnDataObject.getLspObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_XC)) {
						cxActionObject.setXcObjectList(cXPtnDataObject.getXcObject());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PWETH)) {
						cxActionObject.setPwEthObjectList(cXPtnDataObject.getPwEthObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PWPDH)) {
						cxActionObject.setPwPdhObjectList(cXPtnDataObject.getPwPdhObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PWSDH)) {
						cxActionObject.setPwSdhObjectList(cXPtnDataObject.getPwSdhObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_QOS)) {
						cxActionObject.setQosObjectList(cXPtnDataObject.getQosObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_ELINE)) {
						cxActionObject.setElineObjectList(cXPtnDataObject.getElineObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_AC)) {
						cxActionObject.setAcObjectList(cXPtnDataObject.getAcObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_CESPDH)) {
						cxActionObject.setCesObjectList(cXPtnDataObject.getCesObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_ETREE)) {
						cxActionObject.setEtreeObjectList(cXPtnDataObject.getEtreeObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_ACQOS)) {
						cxActionObject.setAcQosObjectList(cXPtnDataObject.getAcQosObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_ELAN)) {
						cxActionObject.setElanObjectList(cXPtnDataObject.getElanObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_UNI)) {
						cxActionObject.setUNIObject(cXPtnDataObject.getUniObject());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_NNI)) {
						cxActionObject.setNNIObject(cXPtnDataObject.getNniObject());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PORT)) {
						cxActionObject.setEthPortObjectList(cXPtnDataObject.getEthPortObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_CCN)) {
						cxActionObject.setCcnObjectList(cXPtnDataObject.getCcnObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_OSPF)) {
						cxActionObject.setOSPFObject(cXPtnDataObject.getOSPFObject());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_OSPFAREA)) {
						cxActionObject.setOspfAREAObjectList(cXPtnDataObject.getOspfAREAObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_REDISTRIBUTE)) {
						cxActionObject.setRedistributeObjectList(cXPtnDataObject.getRedistributeObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_E1)) {
						cxActionObject.setPdhPortObjectList(cXPtnDataObject.getPdhPortObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_OSPFINTERFACE) || cxActionObject.getType().equals(TypeAndActionUtil.TYPE_OSPFINTERFACEMCN) || cxActionObject.getType().equals(TypeAndActionUtil.TYPE_OSPFINTERFACECCN)) {
						cxActionObject.setOspfInterfacesObjectList(cXPtnDataObject.getOspfInterfacesObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_MCN)) {
						cxActionObject.setMcnObjectList(cXPtnDataObject.getMcnObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PORTSTM)) {
						cxActionObject.setSdhPortObjectList(cXPtnDataObject.getSdhPortObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PORTSTMTIMESLOT)) {
						cxActionObject.setSdhAcObjectList(cXPtnDataObject.getSdhAcObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PORTLAG)) {
						cxActionObject.setLagObjectList(cXPtnDataObject.getLagObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_LOOPPROTECT)) {
						cxActionObject.setRingObjectList(cXPtnDataObject.getRingObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_TIMEMANAGER)) {
						cxActionObject.setPtpObjectList(cXPtnDataObject.getPtpObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_FREQUENCY_INFO)) {
						cxActionObject.setTodObjectList(cXPtnDataObject.getTodObjectList());
						cxActionObject.setClockObjectList(cXPtnDataObject.getClockObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_EXTERNALCLOCKINTERF)) {
						cxActionObject.setExtclkObjectList(cXPtnDataObject.getExtclkObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_CLOCKSOURCE_CONFIG)) {
						cxActionObject.setClockPortESObjectList(cXPtnDataObject.getClockPortObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_PORTCONFIG)) {
						cxActionObject.setPtpPortList(cXPtnDataObject.getPtpPortObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_MSPPROTECT)) {
						cxActionObject.setMapObjectList(cXPtnDataObject.getMspObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_ETHLINKOAM)) {
						cxActionObject.setEfmObjectList(cXPtnDataObject.getEfmObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_DUALPROTECT)) {
						cxActionObject.setDualObjectList(cXPtnDataObject.getDualObjectList());
					} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_EXPMAPPINGLLSPINPUT)) {
						if (cXPtnDataObject.getExptoclrObjectList().size() > 0) {
							cxActionObject.setExptoclrObjectList(cXPtnDataObject.getExptoclrObjectList());
						}
						if (cXPtnDataObject.getClrtoexpObjectList().size() > 0) {
							cxActionObject.setClrtoexpObjectList(cXPtnDataObject.getClrtoexpObjectList());
						}
						if (cXPtnDataObject.getExptocosObjectList().size() > 0) {
							cxActionObject.setExptocosObjectList(cXPtnDataObject.getExptocosObjectList());
						}
						if (cXPtnDataObject.getCostoexpObjectList().size() > 0) {
							cxActionObject.setCostoexpObjectList(cXPtnDataObject.getCostoexpObjectList());
						}
						if (cXPtnDataObject.getVlanpri2cngObjectList().size() > 0) {
							cxActionObject.setVlanpri2cngObjectList(cXPtnDataObject.getVlanpri2cngObjectList());
						}
						if (cXPtnDataObject.getCos2vlanpriObjectList().size() > 0) {
							cxActionObject.setCos2vlanpriObjectList(cXPtnDataObject.getCos2vlanpriObjectList());
						}
					}
					cxActionObject.setStatus(cXPtnDataObject.getStatus());
					break;
				} else if (cxActionObject.getType().equals(TypeAndActionUtil.TYPE_SLOTTEMP)) {
					cxActionObject.setSlotTempList(cXPtnDataObject.getSlotTempObjectList());
				}
			}
		}
	}
}
