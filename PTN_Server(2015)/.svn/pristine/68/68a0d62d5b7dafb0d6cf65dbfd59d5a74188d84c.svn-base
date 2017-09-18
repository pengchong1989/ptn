package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.enums.OamTypeEnum;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.TMSOAMInfoObject;
import com.nms.drive.service.bean.TMSOAMObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class TmsOamWHServiceImpl extends WHOperationBase implements OperationServiceI{
	
	private int enable = 1;
	@Override
	public String excutionDelete(List objectList) throws Exception {
		OamInfo oamInfo = null;
		OamInfoService_MB oamInfoService = null;
		List<Integer> siteIdList = null;
		Map<Integer, ActionObject> operationObjectBefore=null;
		OperationObject operationObject = null;
		OperationObject operationObjectResult=null;
		List<OamInfo> oamInfoList = null;
		enable = 0;
		try {
			oamInfoList = objectList;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(oamInfoList.get(0).getOamMep().getSiteId());
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
//			operationObjectBefore = this.getOperationBefore(siteIdList);
			for(OamInfo info : oamInfoList){
				oamInfoService.deleteById(info);
			}
			operationObject = this.getOperationObject(oamInfoList.get(0));
			super.sendAction(operationObject);
			operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()) {
				super.clearLock(siteIdList);
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				boolean flag = super.rollback(operationObjectResult, operationObjectBefore);
				super.clearLock(siteIdList);
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(oamInfoService);
		}
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		OamInfo oamInfo = null;
		OamInfoService_MB oamInfoService = null;
		List<Integer> siteIdList = null;
		Map<Integer, ActionObject> operationObjectBefore=null;
		OperationObject operationObject = null;
		OperationObject operationObjectResult=null;
		List<OamInfo> oamInfoList = null;
		try {
			oamInfo = (OamInfo)object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(oamInfo.getOamMep().getSiteId());
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
//			operationObjectBefore = this.getOperationBefore(siteIdList);
			oamInfoService.saveOrUpdate(oamInfo);
			operationObject = this.getOperationObject(oamInfo);
			super.sendAction(operationObject);
			operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()) {
				super.clearLock(siteIdList);
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				boolean flag = super.rollback(operationObjectResult, operationObjectBefore);
//				if (flag) {
//					System.out.println("回滚成功");
//				} else {
//					System.out.println("回滚失败");
//				}
				super.clearLock(siteIdList);
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		OamInfo oamInfo = null;
		List<Integer> siteIdList = null;
		OperationObject operationObject = null;
		OperationObject operationObjectResult = null;
		try {
			oamInfo = (OamInfo)object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(oamInfo.getOamMep().getSiteId());
			operationObject = this.getOperationObject(oamInfo);
			super.sendAction(operationObject);
			operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()) {
				super.clearLock(siteIdList);
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				super.clearLock(siteIdList);
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return null;
	}

	/**
	 * 获取下发之前之前
	 * @param siteIdList 
	 * @return
	 * @throws Exception
	 */
//	private Map<Integer, ActionObject> getOperationBefore(List<Integer> siteIdList) throws Exception{
//		
//		Map<Integer, ActionObject> operationBefore=null;
//		OperationObject operationObject= null;
//		try {
//			operationBefore=new HashMap<Integer, ActionObject>();
//			operationObject=this.getOperationObject(siteIdList);
//			for(ActionObject actionObject : operationObject.getActionObjectList()){
//				operationBefore.put(actionObject.getNeObject().getNeAddress(), actionObject);
//			}
//		} catch (Exception e) {
//			throw e;
//		}finally{
//			operationObject=null;
//		}
//		return operationBefore;
//	}
	
	private OperationObject getOperationObject(OamInfo oamInfo) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;

		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
//			for (int siteId : siteIdList) {
				neObject = whImplUtil.siteIdToNeObject(oamInfo.getOamMep().getSiteId());
				actionObject = new ActionObject();
				actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.setType("segmentOam");
				actionObject.setTmsOamObject(this.gettmsOamObject(oamInfo));
				operationObject.getActionObjectList().add(actionObject);
//			}

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

	private TMSOAMObject gettmsOamObject(OamInfo oamInfo)  {
		
		TMSOAMObject tmsoamObject = new TMSOAMObject();
		List<TMSOAMInfoObject> tmsOamInfoList = new ArrayList<TMSOAMInfoObject>();
		TMSOAMInfoObject tmsOAMInfoObject = new TMSOAMInfoObject();
		PortService_MB portService  = null;
		
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			PortInst portInst = new PortInst();
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
			if(oamInfo.getOamMep().getLspTc() == 0){
				tmsOAMInfoObject.setTc(7);
			}else{
				tmsOAMInfoObject.setTc(Integer.parseInt(UiUtil.getCodeById(oamInfo.getOamMep().getLspTc()).getCodeValue()));
			}
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
			tmsOAMInfoObject.setMepId(oamInfo.getOamMep().getLocalMepId());
			tmsOAMInfoObject.setEqualMepId(oamInfo.getOamMep().getRemoteMepId());
			tmsOamInfoList.add(tmsOAMInfoObject);
			tmsoamObject.setTmsOamInfoList(tmsOamInfoList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
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
	 *生成6位meg
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
	 *  cycle的转换
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
		OperationObject operaObj = new OperationObject();
		try {
			operaObj = this.getSynchroOperationObject(siteId);//封装下发数据
			super.sendAction(operaObj);// 下发数据
			super.verification(operaObj);// 验证是否下发成功
			if (operaObj.isSuccess()) {
				/* 成功，则与数据库进行同步 */
				for (ActionObject actionObject : operaObj.getActionObjectList()) {
					this.synchro_db(actionObject.getTmsoamObjects(), siteId);
				}
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operaObj = null;
		}
		return "超时";
	}
	
	/**
	 *  封装下发数据
	 * 
	 * @param siteId
	 * @return operationObject
	 * @throws Exception
	 */
	private OperationObject getSynchroOperationObject(int siteId) throws Exception {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("tmsOamSynchro");
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			actionObject = null;
		}
		return operationObject;
	}
	
	/**
	 *转换后的oam信息与数据库进行对比
	 * @param tmsoamObjects
	 * @param siteId
	 */
	private void synchro_db(List<TMSOAMObject> tmsoamObjects,int siteId){
		for(TMSOAMObject tmsoamObject : tmsoamObjects){
			List<OamInfo> infos = this.getOaminfo(tmsoamObject.getTmsOamInfoList(), siteId);
			try {
				SynchroUtil synchroUtil = new SynchroUtil();
				synchroUtil.updateOam(infos);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
		
	}
	
	/**
	 * 转换oam信息
	 * @param tmsOamInfoList
	 * @param siteId
	 * @return
	 */
	private List<OamInfo> getOaminfo(List<TMSOAMInfoObject> tmsOamInfoList,int siteId){
		List<OamInfo> oamInfos = null;
		PortInst portInst = null;
		PortService_MB portService = null;
		OamInfo oamInfo = null;
		OamMepInfo oamMepInfo = null;
		OamInfoService_MB oamInfoService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			oamInfos = new ArrayList<OamInfo>();
			for(TMSOAMInfoObject tmsOAMInfoObject : tmsOamInfoList){
				OamMepInfo mepInfo = new OamMepInfo();
				oamInfo = new OamInfo();
				oamInfo.setOamType(OamTypeEnum.MEP);
				oamMepInfo = new OamMepInfo();
				portInst = new PortInst();
				portInst.setSiteId(siteId);
				portInst.setNumber(tmsOAMInfoObject.getPortNumber());
				portInst = portService.select(portInst).get(0);
				oamMepInfo.setObjId(portInst.getPortId());
				oamMepInfo.setSiteId(siteId);
				oamMepInfo.setLocalMepId(tmsOAMInfoObject.getMepId());
				oamMepInfo.setRemoteMepId(tmsOAMInfoObject.getEqualMepId());
				oamMepInfo.setCv("1".equals(tmsOAMInfoObject.getCvEnable()));
				oamMepInfo.setCvCycle(getCycleCode(tmsOAMInfoObject.getCvCircle()));
				oamMepInfo.setAps("1".equals(tmsOAMInfoObject.getApsEnable()));
				oamMepInfo.setSsm("1".equals(tmsOAMInfoObject.getSsmEnable()));
				oamMepInfo.setSccTest("1".equals(tmsOAMInfoObject.getSccTestEnable()));
				oamMepInfo.setLm(tmsOAMInfoObject.getLm()==1);
				oamMepInfo.setMel(tmsOAMInfoObject.getMel());
				oamMepInfo.setLspTc((UiUtil.getCodeByValue("TC", tmsOAMInfoObject.getTc()+"")).getId());
				oamMepInfo.setMegIcc(synchroMeg(tmsOAMInfoObject.getMegIcc()));
				oamMepInfo.setMegUmc(synchroMeg(tmsOAMInfoObject.getMegUmc()));
				oamMepInfo.setObjType("SECTION");
				oamMepInfo.setVlanEnable(tmsOAMInfoObject.getVlanEnable());
				oamMepInfo.setOutVlanValue(tmsOAMInfoObject.getVlanValue());
				oamMepInfo.setTpId(tmsOAMInfoObject.getTpId());
				oamMepInfo.setSourceMac(tmsOAMInfoObject.getSourceMac());
				oamMepInfo.setEndMac(tmsOAMInfoObject.getEndMac());
				oamMepInfo.setLocalMepId(tmsOAMInfoObject.getMepId());
				oamMepInfo.setRemoteMepId(tmsOAMInfoObject.getEqualMepId());
				oamInfo.setOamMep(oamMepInfo);
				mepInfo = oamInfoService.queryMep(oamMepInfo);
				if(mepInfo.getId()>0){
					oamMepInfo.setId(mepInfo.getId());
				}
				oamInfos.add(oamInfo);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			portInst = null;
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(oamInfoService);
		}
		return oamInfos;
	
	}
	public String synchroMeg(String meg) {
		StringBuffer stringBuffer = new StringBuffer();
		String[] strs = meg.split(",");
		for (String str : strs) {
			char c = (char) Integer.parseInt(str);
			stringBuffer.append(c);
		}
		return stringBuffer.toString();
	}
	
	/**
	 *同步连通性周期
	 * @param cycle
	 * @return
	 */
	public int getCycleCode(String cycle) {
		int codeId = 0;
		if ("001".equals(cycle)) {
			codeId = 87;
		} else if ("010".equals(cycle)) {
			codeId = 88;
		} else if ("011".equals(cycle)) {
			codeId = 89;
		} else if ("100".equals(cycle)) {
			codeId = 90;
		}
		return codeId;
	}
	
}
