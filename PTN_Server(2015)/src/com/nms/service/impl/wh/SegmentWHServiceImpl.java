package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.enums.EManufacturer;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.TMSOAMInfoObject;
import com.nms.drive.service.bean.TMSOAMObject;
import com.nms.model.equipment.port.PortService_MB;
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
import com.nms.ui.manager.UiUtil;

public class SegmentWHServiceImpl extends WHOperationBase implements OperationServiceI {
	private int enable =1;//tmsoam使能状态，默认使能
	@Override
	public String excutionInsert(Object object) throws Exception {
		Segment segment = null;
		PortInst aPortInst = null;
		PortInst zPortInst = null;
		PortService_MB portService = null;
		String reult = ResultString.CONFIG_TIMEOUT;
		OperationObject operationObject = null;
		List<Integer> siteIdList = null;
		Map<Integer,List<QosQueue>> qosMap = null;
		List<QosQueue> qosQueues = null;
		List<OamInfo> oamInfos = null;
		OperationObject operationObjectResult=null;
		try {
			siteIdList = new ArrayList<Integer>();
			segment = (Segment) object;
			aPortInst = new PortInst();
			zPortInst = new PortInst();
			aPortInst.setPortId(segment.getAPORTID());
			zPortInst.setPortId(segment.getZPORTID());
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			List<PortInst> aPortList = portService.select(aPortInst);
			List<PortInst> zPortList = portService.select(zPortInst);
			aPortInst = aPortList.get(0);
			zPortInst = zPortList.get(0);
			siteIdList.add(aPortInst.getSiteId());
			siteIdList.add(zPortInst.getSiteId());
			qosMap = segment.getQosMap();
			if(qosMap.values() != null && qosMap.values().size()>0) {
				qosQueues = new ArrayList<QosQueue>();
				for(List<QosQueue> qosQueue : qosMap.values()){
					if(qosQueue != null && qosQueue.size()>0)
						qosQueues.addAll(qosQueue);
				}
				
			}
			oamInfos = segment.getOamList();
//			reult = (new StringBuffer()).append("A 端网元").append(aPortInstResult).append("   ").append("Z 端网元").append(zPortInstResult).toString();
//			this.insertDao(segment,qosQueues,oamInfos);
			operationObject = this.getOperationObject(oamInfos);
			if(operationObject.getActionObjectList().size()>0){
				super.sendAction(operationObject);
				operationObjectResult=super.verification(operationObject);
				if(operationObjectResult.isSuccess()){
					return operationObjectResult.getActionObjectList().get(0).getStatus(); 
				}
			}else{
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
			segment = null;
			aPortInst = null;
			zPortInst = null;
			qosMap = null;
			qosQueues = null;
			oamInfos = null;
		}
		return reult;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		Segment segment = null;
		Map<Integer,List<QosQueue>> qosMap = null;
		List<QosQueue> qosQueues = null;
		List<OamInfo> oamInfos = null;
		OperationObject operationObject = null;
		OperationObject operationObjectResult=null;
		try {
			segment = (Segment) object;
			qosMap = segment.getQosMap();
			if(qosMap.values() != null && qosMap.values().size()>0) {
				qosQueues = new ArrayList<QosQueue>();
				for(List<QosQueue> qosQueue : qosMap.values()){
					if(qosQueue != null && qosQueue.size()>0)
						qosQueues.addAll(qosQueue);
				}
			}
			oamInfos = segment.getOamList();
			operationObject = this.getOperationObject(oamInfos);
			if(operationObject.getActionObjectList().size()>0){
				super.sendAction(operationObject);
				operationObjectResult=super.verification(operationObject);
				if(operationObjectResult.isSuccess()){
//					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus(); 
				}
			}else{
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			qosMap = null;
			qosQueues = null;
			oamInfos = null;
		}
		return null;
	}
	
	@SuppressWarnings( { "rawtypes", "unused", "unchecked" })
	@Override
	public String excutionDelete(List objectList) throws Exception {
		List<Segment> segments = null;
		List<Integer> siteIdList = null;
		Map<Integer, ActionObject> operationObjectBefore = null;
		OperationObject operationObject = null;
		OperationObject operationObjectResult=null;
		List<OamInfo> oamInfos = null;
		enable =0;//删除段，把相应的oam去使能
		try {
			segments = objectList;
			oamInfos = new ArrayList<OamInfo>();
			for(Segment segment : segments){
				oamInfos.addAll(segment.getOamList());
			}
			operationObject = this.getOperationObject(oamInfos);
			if(operationObject.getActionObjectList().size()>0){
				super.sendAction(operationObject);
				operationObjectResult=super.verification(operationObject);
				if(operationObjectResult.isSuccess()){
//					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus(); 
				}
			}else{
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		} finally {

		}
		return null;
	}


	private OperationObject getOperationObject(List<OamInfo> oamInfos) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;

		try {
			operationObject = new OperationObject();
			SiteUtil siteUtil=new SiteUtil();
			WhImplUtil whImplUtil = new WhImplUtil();
			for (OamInfo oamInfo : oamInfos) {
				if(EManufacturer.WUHAN.getValue() == super.getManufacturer(oamInfo.getOamMep().getSiteId())){
					actionObject = new ActionObject();
					if("0".equals(siteUtil.SiteTypeUtil(oamInfo.getOamMep().getSiteId())+"")){
						neObject = whImplUtil.siteIdToNeObject(oamInfo.getOamMep().getSiteId());
						actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
						actionObject.setNeObject(neObject);
						actionObject.setType("segmentOam");
						actionObject.setTmsOamObject(this.gettmsOamObject(oamInfo));
						operationObject.getActionObjectList().add(actionObject);
					}
					
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}

	/**
	 * 根据网元id把tmsoam绑定到对应的端口上
	 * 
	 * @param siteId
	 * @return
	 * @throws Exception
	 */

	private TMSOAMObject gettmsOamObject(OamInfo oamInfo) throws Exception {
		TMSOAMObject tmsoamObject = new TMSOAMObject();
		List<TMSOAMInfoObject> tmsOamInfoList = new ArrayList<TMSOAMInfoObject>();
		TMSOAMInfoObject tmsOAMInfoObject = new TMSOAMInfoObject();
		PortInst portInst = null;
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInst = new PortInst();
			portInst.setSiteId(oamInfo.getOamMep().getSiteId());
			portInst.setPortId(oamInfo.getOamMep().getObjId());
			portInst = portService.select(portInst).get(0);
			tmsOAMInfoObject.setPortNumber(portInst.getNumber());
			tmsOAMInfoObject.setVsOamEnable(enable);
			this.getMegIccAndMegUmc(tmsOAMInfoObject, oamInfo.getOamMep().getMegIcc(),oamInfo.getOamMep().getMegUmc());
			tmsOAMInfoObject.setMepId(oamInfo.getOamMep().getLocalMepId());
			tmsOAMInfoObject.setEqualMepId(oamInfo.getOamMep().getRemoteMepId());
			tmsOAMInfoObject.setCvEnable(oamInfo.getOamMep().isCv() ? "1" : "0");
			tmsOAMInfoObject.setCvCircle(getCycle(oamInfo.getOamMep().getCvCycle()));
			tmsOAMInfoObject.setApsEnable(oamInfo.getOamMep().isAps() ? "1" : "0");
			tmsOAMInfoObject.setSsmEnable(oamInfo.getOamMep().isSsm() ? "1" : "0");
			tmsOAMInfoObject.setSccTestEnable(oamInfo.getOamMep().isSccTest() ? "1" : "0");
			tmsOAMInfoObject.setLm(oamInfo.getOamMep().isLm() ? 1 : 0);
			tmsOAMInfoObject.setMel(oamInfo.getOamMep().getMel());
			tmsOAMInfoObject.setTc(Integer.parseInt(UiUtil.getCodeById(oamInfo.getOamMep().getLspTc()).getCodeValue()));
			tmsOAMInfoObject.setVlanEnable(oamInfo.getOamMep().getVlanEnable());
			tmsOAMInfoObject.setVlanValue(oamInfo.getOamMep().getOutVlanValue() == 0 ? 2 : oamInfo.getOamMep().getOutVlanValue());
			tmsOAMInfoObject.setTpId(oamInfo.getOamMep().getTpId());
			if(oamInfo.getOamMep().getSourceMac() == null || oamInfo.getOamMep().getSourceMac().equals("")){
				oamInfo.getOamMep().setSourceMac("00-00-00-00-00-01");
			}
			tmsOAMInfoObject.setSourceMac(transformMac(oamInfo.getOamMep().getSourceMac()));
			if(oamInfo.getOamMep().getEndMac() == null || oamInfo.getOamMep().getEndMac().equals("")){
				oamInfo.getOamMep().setEndMac("00-00-00-00-00-01");
			}
			tmsOAMInfoObject.setEndMac(transformMac(oamInfo.getOamMep().getEndMac()));
			tmsOamInfoList.add(tmsOAMInfoObject);
			tmsoamObject.setTmsOamInfoList(tmsOamInfoList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			portInst = null;
			UiUtil.closeService_MB(portService);
		}
		return tmsoamObject;
	}
	
	private String transformMac(String mac) {
		StringBuffer buffer = new StringBuffer();
		String[] strs = mac.split("-");
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			if(i == strs.length-1){
				buffer.append(transformInt(str));
			}else{
				buffer.append(transformInt(str)+"-");
			}
		}
		return buffer.toString();
	}

	private int transformInt(String str){
		int first = 0;
		int last = 0;
		str.subSequence(0,1);
		if("0".equals(str.subSequence(0,1))){
			first = 0;
		}
		if("0".equals(str.substring(1))){
			last = 0;
		}
		if("1".equals(str.subSequence(0,1))){
			first = 1;
		}
		if("1".equals(str.substring(1))){
			last = 1;
		}
		if("2".equals(str.subSequence(0,1))){
			first = 2;
		}
		if("2".equals(str.substring(1))){
			last = 2;
		}
		if("3".equals(str.subSequence(0,1))){
			first = 3;
		}
		if("3".equals(str.substring(1))){
			last = 3;
		}
		if("4".equals(str.subSequence(0,1))){
			first = 4;
		}
		if("4".equals(str.substring(1))){
			last = 4;
		}
		if("5".equals(str.subSequence(0,1))){
			first = 5;
		}
		if("5".equals(str.substring(1))){
			last = 5;
		}
		if("6".equals(str.subSequence(0,1))){
			first = 6;
		}
		if("6".equals(str.substring(1))){
			last = 6;
		}
		if("7".equals(str.subSequence(0,1))){
			first = 7;
		}
		if("7".equals(str.substring(1))){
			last = 7;
		}
		if("8".equals(str.subSequence(0,1))){
			first = 8;
		}
		if("8".equals(str.substring(1))){
			last = 8;
		}
		if("9".equals(str.subSequence(0,1))){
			first = 9;
		}
		if("9".equals(str.substring(1))){
			last = 9;
		}
		if("A".equals(str.subSequence(0,1))){
			first = 10;
		}
		if("A".equals(str.substring(1))){
			last = 10;
		}
		if("B".equals(str.subSequence(0,1))){
			first = 11;
		}
		if("B".equals(str.substring(1))){
			last = 11;
		}
		if("C".equals(str.subSequence(0,1))){
			first = 12;
		}
		if("C".equals(str.substring(1))){
			last = 12;
		}
		if("D".equals(str.subSequence(0,1))){
			first = 13;
		}
		if("D".equals(str.substring(1))){
			last = 13;
		}
		if("E".equals(str.subSequence(0,1))){
			first = 14;
		}
		if("E".equals(str.substring(1))){
			last = 14;
		}
		if("F".equals(str.subSequence(0,1))){
			first = 15;
		}
		if("F".equals(str.substring(1))){
			last = 15;
		}
		return first*16+last;
	}


	/**
	 * 生成megumcID
	 * @param tunnelObject
	 * @param megId
	 */
	public void getMegIccAndMegUmc(TMSOAMInfoObject tmsOAMInfoObject, String megICC,String megUCC) {
		tmsOAMInfoObject.setMegIcc(getMeg(megICC));
		tmsOAMInfoObject.setMegUmc(getMeg(megUCC));
	}
	
	
	/**
	 * 生成6位meg
	 * 
	 * @param meg
	 * @return
	 */
	private String getMeg(String meg) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < meg.length(); i++) {
			char c = meg.charAt(i);
			int b = c;

			if (i == 5) {
				str.append(b);
			} else {
				str.append(b + ",");
			}
		}
		for (int i = 0; i < 6-meg.length(); i++) {
			str.append("0,");
		}
		return str.toString();
	}
	
	/**
	 * cycle的转换
	 * 
	 * @param cycle
	 * @return
	 */
	public String getCycle(double cycle) {
		String str = "";
		if (cycle == 87) {
			str = "001";
		} else if (cycle == 88) {
			str = "010";
		} else if (cycle == 89) {
			str = "011";
		} else if (cycle == 90) {
			str = "100";
		}
		return str;
	}


	@Override
	public Object synchro(int siteId) {
		return null;
	}
}
