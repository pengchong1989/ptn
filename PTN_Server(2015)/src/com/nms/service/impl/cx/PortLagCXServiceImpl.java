package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.drivechenxiao.service.bean.lag.LagObject;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.nni.NNIObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.port.PortLagService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public class PortLagCXServiceImpl extends CXOperationBase implements OperationServiceI {
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
		PortLagInfo portLagInfo = null;
		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();

			portLagInfo = (PortLagInfo) object;
			cxActionObject = this.getCXObject(portLagInfo, action);
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			portLagInfo = null;
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
	private CXActionObject getCXObject(PortLagInfo portLagInfo, String action) throws Exception {

		if (portLagInfo == null) {
			throw new Exception("portLagInfo is null");
		}

		if (action == null || action.equals("")) {
			throw new Exception("action is  null");
		}

		CXActionObject cxActionObject = null;
		OperationObject operationObject = null;

		try {
			operationObject = new OperationObject();
			cxActionObject = new CXActionObject();
			cxActionObject.setCxNeObject(super.getCXNEObject(Integer.valueOf(portLagInfo.getSiteId())));
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_PORTLAG);
			cxActionObject.setAction(action);
			cxActionObject.setEthObjectList(getEthPortList(portLagInfo));
			if(portLagInfo.isDataDownLoad()&&action.equals(TypeAndActionUtil.ACTION_DELETE)){
				LagObject lagObject = new LagObject();
				lagObject.setName(portLagInfo.getLagID()+"");
				cxActionObject.setLagObject(lagObject);
			}else{
				cxActionObject.setLagObject(lagToLagOBJECT(portLagInfo,action));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
		}
		return cxActionObject;
	}

	private LagObject lagToLagOBJECT(PortLagInfo portLagInfo,String action) throws Exception {
		
		LagObject lagObject = new LagObject();
		PortLagService_MB portLagService = null;
		try {
			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			
			if ("1".equals(UiUtil.getCodeById(portLagInfo.getMeangerStatus()).getCodeValue())) {
				lagObject.setAdmin("up");
			} else {
				lagObject.setAdmin("down");
			}
			lagObject.setName(portLagInfo.getLagID() + "");
//		if(!portLagInfo.isDataDownLoad()){
			lagObject.setRole(portLagInfo.getRole().toLowerCase());
			
			if(0!=portLagInfo.getLagMode()){
				lagObject.setPsc(UiUtil.getCodeById(portLagInfo.getLagMode()).getCodeValue());
			}
			// lagObject.setIused(portLagInfo.getInportLimitation() + "");
			// lagObject.setOused(portLagInfo.getPortLimitation() + "");
			lagObject.setFramelen(portLagInfo.getMaxFrameLength() + "");
			lagObject.setPermitpktloop(portLagInfo.getMsgLoop() == 0 ? "false" : "true");
			lagObject.setRecover(portLagInfo.getResumeModel() == 0 ? "false" : "true");
			
			// uni属性
			lagObject.getUni().setInbwlimit(portLagInfo.getInportLimitation() + "");
			lagObject.getUni().setEgbwlimit(portLagInfo.getPortLimitation() + "");
			lagObject.getUni().setPvid(portLagInfo.getVlanIC() + "");
			lagObject.getUni().setPvpri(portLagInfo.getVlanPriority() + "");
			if(0==portLagInfo.getPortId()){
				if("1".equals(UiUtil.getCodeById(portLagInfo.getLblNetWrap()).getCodeValue())){
					lagObject.getUni().setEncap("1");
				}else{
					lagObject.getUni().setEncap("2");
				}
			}else{
				lagObject.getUni().setEncap(UiUtil.getCodeById(portLagInfo.getLblNetWrap()).getCodeValue());
			}
			lagObject.getUni().setSdtpid(UiUtil.getCodeById(portLagInfo.getLblVlanTpId()).getCodeValue());
			// lagObject.getUni().setTpid(UiUtil.getCodeById(portLagInfo.getLblouterTpid()).getCodeValue());
			lagObject.getUni().setVlanmode(UiUtil.getCodeById(portLagInfo.getLblNetVlanMode()).getCodeValue());
			lagObject.getUni().setBcastlimit(portLagInfo.getBroadcastFlux() + "");
			lagObject.getUni().setDlflimit(portLagInfo.getFloodFlux() + "");
			lagObject.getUni().setMcastlimit(portLagInfo.getGroupBroadcastFlux() + "");
			
			if(action.equals(TypeAndActionUtil.ACTION_UPDATE)){
				PortLagInfo portLagInfo_select=new PortLagInfo();
				portLagInfo_select.setId(portLagInfo.getId());
				List<PortLagInfo> portLagInfoList = portLagService.selectByCondition(portLagInfo_select);
				
				List<String> portnameList=new ArrayList<String>();
				if(null!=portLagInfoList && portLagInfoList.size()==1){
					for(PortInst portinst : portLagInfoList.get(0).getPortList()){
						portnameList.add(portinst.getPortName());
					}
				}
				lagObject.getMenber().setPortName(portnameList);
			}
//		}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portLagService);
		}
		return lagObject;
	}

	private List<EthPortObject> getEthPortList(PortLagInfo portLagInfo) {
		NNIObject nniObject;
		List<EthPortObject> ethPortlist = new ArrayList<EthPortObject>();
		for (int i = 0; i < portLagInfo.getPortList().size(); i++) {
			EthPortObject ethPortObject = new EthPortObject();
			ethPortObject.setName(portLagInfo.getPortList().get(i).getPortName());
			nniObject = new NNIObject();
			if(0!=portLagInfo.getExpMappingLLspInput()){
				nniObject.setLlspexptoclr(portLagInfo.getExpMappingLLspInput()+"");
			}
			if(0!=portLagInfo.getExpMappingLLspOutput()){
				nniObject.setLlspclrtoexp(portLagInfo.getExpMappingLLspOutput()+"");
			}
			if(0!=portLagInfo.getExpMappingELspInput()){
				nniObject.setElspexptocos(portLagInfo.getExpMappingELspInput()+"");
			}
			if(0!=portLagInfo.getExpMappingELspOutput()){
				nniObject.setElspcostoexp(portLagInfo.getExpMappingELspOutput()+"");
			}
			ethPortObject.setNni(nniObject);
			ethPortlist.add(ethPortObject);
		}
		return ethPortlist;
	}

	public String excutionDelete(List objectList) throws Exception {
		OperationObject operationObject = null;
		PortLagInfo portLagInfo = null;
		String result = null;
		PortLagService_MB portLagService = null;
		try {
			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			for (int i = 0; i < objectList.size(); i++) {
				portLagInfo = (PortLagInfo) objectList.get(i);
				//未激活操作
				if(!portLagInfo.isDataDownLoad()&&portLagInfo.getLagStatus()==EActiveStatus.UNACTIVITY.getValue()){
					portLagService.delete(portLagInfo);
					result = ResultString.CONFIG_SUCCESS;
				}else{
					//激活操作
					operationObject = this.getOperationObject(portLagInfo, TypeAndActionUtil.ACTION_DELETE);
					super.sendAction(operationObject);
					super.verification(operationObject);
					if (operationObject.isSuccess()) {
						if(!portLagInfo.isDataDownLoad()){
							portLagService.delete(portLagInfo);
						}
						result = operationObject.getCxActionObjectList().get(0).getStatus();
					} else {
						return result = super.getErrorMessage(operationObject);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portLagService);
		}
		return result;
	}

	@Override
	public String excutionInsert(Object object) throws Exception, BusinessIdException {
		OperationObject operationObject = null;
		PortLagInfo portLagInfo = null;
		String result = null;
		int lagId = 0;
		PortLagService_MB portLagService = null;
		try {
			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			portLagInfo = (PortLagInfo) object;
			if(!portLagInfo.isDataDownLoad()){
				lagId = portLagService.insertLag(portLagInfo);
			}
			portLagInfo.setId(lagId);
			operationObject = this.getOperationObject(portLagInfo, TypeAndActionUtil.ACTION_INSERT);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				if(!portLagInfo.isDataDownLoad()){
					List<PortLagInfo> portlagInfoList = new ArrayList<PortLagInfo>();
					portlagInfoList.add(portLagInfo);
					portLagService.delete(portlagInfoList);
				}
				result = super.getErrorMessage(operationObject);
			}
			result = operationObject.getCxActionObjectList().get(0).getStatus();
		} catch (BusinessIdException e) {
			List<PortLagInfo> portlagInfoList = new ArrayList<PortLagInfo>();
			portlagInfoList.add(portLagInfo);
			portLagService.delete(portlagInfoList);
			throw e;
		} catch (Exception e) {
			List<PortLagInfo> portlagInfoList = new ArrayList<PortLagInfo>();
			portlagInfoList.add(portLagInfo);
			portLagService.delete(portlagInfoList);
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portLagService);
		}
		return result;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		PortLagService_MB portLagService = null;
		OperationObject operationObject = null;
		PortLagInfo portLagInfo = null;
		String result = null;
		try {
			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			portLagInfo = (PortLagInfo) object;
			//未激活操作
			if(!portLagInfo.isDataDownLoad()&&portLagInfo.getLagStatus()==EActiveStatus.UNACTIVITY.getValue()){
				portLagService.updateLag(portLagInfo);
				result = ResultString.CONFIG_SUCCESS;
			}else{
				//激活操作
				operationObject = this.getOperationObject(portLagInfo, TypeAndActionUtil.ACTION_UPDATE);
				super.sendAction(operationObject);
				super.verification(operationObject);
				if (operationObject.isSuccess()) {
					if(!portLagInfo.isDataDownLoad()){
						portLagService.updateLag(portLagInfo);
					}
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					result = super.getErrorMessage(operationObject);
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portLagService);
		}

		return result;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		CXActionObject cxActionObject = new CXActionObject();
		PortLagService_MB portLagService = null;
		try {
			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
			cxActionObject.setType(TypeAndActionUtil.TYPE_PORTLAG);
			cxActionObject.setCxNeObject(super.getCXNEObject(siteId));
			operationObject.getCxActionObjectList().add(cxActionObject);

			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				portLagService.updateActiveStatus(siteId,EActiveStatus.UNACTIVITY.getValue());
				this.synchro_db(operationObject.getCxActionObjectList().get(0).getLagObjectList(), siteId);
			}

		} catch (Exception e) {
			 throw e;
		} finally {
			UiUtil.closeService_MB(portLagService);
		}
		return null;
	}

	/**
	 * 转换成数据库对象，与数据库同步
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void synchro_db(List<LagObject> lagObjectList, int siteId) throws Exception {

		if (lagObjectList.size() > 0) {
			PortLagInfo portLagInfo = null;
			List<QosQueue> qosQueueList = null;
			List<PortInst> portInstList = null;
			SynchroUtil synchroUtil = new SynchroUtil();
			for (LagObject lagObject : lagObjectList) {
				portLagInfo = new PortLagInfo();
				portLagInfo.setSiteId(siteId);
				portLagInfo.setType(1);
				portLagInfo.setRole("UNI");
				portLagInfo.setsMac(lagObject.getMac());
				if ("".equals(lagObject.getName())) {
					portLagInfo.setLagID(0);
				} else {
					portLagInfo.setLagID(Integer.parseInt(lagObject.getName()));
				}
				if (!"".equals(lagObject.getAdmin())) {
					portLagInfo.setMeangerStatus(UiUtil.getCodeByValue("ENABLEDSTATUE", lagObject.getAdmin()).getId());
					portLagInfo.setPortEnable(UiUtil.getCodeByValue("ENABLEDSTATUE", lagObject.getAdmin()).getId());
				} else {
					portLagInfo.setMeangerStatus(UiUtil.getCodeByValue("ENABLEDSTATUE", "0").getId());
					portLagInfo.setPortEnable(UiUtil.getCodeByValue("ENABLEDSTATUE", "0").getId());
				}

				if (!"".equals(lagObject.getPsc())) {
					portLagInfo.setLagMode(UiUtil.getCodeByValue("LAGMODE", lagObject.getPsc()).getId());
				} else {
					portLagInfo.setLagMode(UiUtil.getCodeByValue("LAGMODE", "0").getId());
				}

				if (!"".equals(lagObject.getFramelen())) {
					portLagInfo.setMaxFrameLength(Integer.parseInt(lagObject.getFramelen()));
				}

				if (!"".equals(lagObject.getUni().getInbwlimit())) {
					portLagInfo.setInportLimitation(Integer.parseInt(lagObject.getUni().getInbwlimit()));
				}

				if (!"".equals(lagObject.getUni().getEgbwlimit())) {
					portLagInfo.setPortLimitation(Integer.parseInt(lagObject.getUni().getEgbwlimit()));
				}

				if (!"".equals(lagObject.getUni().getPvid())) {
					portLagInfo.setVlanIC(Integer.parseInt(lagObject.getUni().getPvid()));
				}

				if (!"".equals(lagObject.getUni().getPvpri())) {
					portLagInfo.setVlanPriority(Integer.parseInt(lagObject.getUni().getPvpri()));
				}

				if (!"".equals(lagObject.getRecover())) {
					portLagInfo.setResumeModel("true".equals(lagObject.getRecover()) ? 1 : 0);
				}

				if (!"".equals(lagObject.getPermitpktloop())) {
					portLagInfo.setMsgLoop("true".equals(lagObject.getPermitpktloop()) ? 1 : 0);
				}

				if (!"".equals(lagObject.getUni().getEncap())) {
					portLagInfo.setLblNetWrap(UiUtil.getCodeByValue("LAGNETWRAP", lagObject.getUni().getEncap()).getId());
				} else {
					portLagInfo.setLblNetWrap(UiUtil.getCodeByValue("LAGNETWRAP", "1").getId());
				}

				if (!"".equals(lagObject.getUni().getSdtpid())) {
					portLagInfo.setLblVlanTpId(UiUtil.getCodeByValue("LAGVLANTPID", lagObject.getUni().getSdtpid()).getId());
				} else {
					portLagInfo.setLblVlanTpId(UiUtil.getCodeByValue("LAGVLANTPID", "0").getId());
				}

				if (!"".equals(lagObject.getUni().getTpid())) {
					portLagInfo.setLblouterTpid(UiUtil.getCodeByValue("LAGOUTERTPID", lagObject.getUni().getTpid()).getId());
				} else {
					portLagInfo.setLblouterTpid(UiUtil.getCodeByValue("LAGOUTERTPID", "1").getId());
				}

				if (!"".equals(lagObject.getUni().getVlanmode())) {
					portLagInfo.setLblNetVlanMode(UiUtil.getCodeByValue("LAGNETVLANMODE", lagObject.getUni().getVlanmode()).getId());
				} else {
					portLagInfo.setLblNetVlanMode(UiUtil.getCodeByValue("LAGNETVLANMODE", "1").getId());
				}

				if (!"".equals(lagObject.getUni().getDlflimit())) {
					portLagInfo.setFloodFlux(Integer.parseInt(lagObject.getUni().getDlflimit()));
				}

				if (!"".equals(lagObject.getUni().getBcastlimit())) {
					portLagInfo.setBroadcastFlux(Integer.parseInt(lagObject.getUni().getBcastlimit()));
				}

				if (!"".equals(lagObject.getUni().getMcastlimit())) {
					portLagInfo.setGroupBroadcastFlux(Integer.parseInt(lagObject.getUni().getMcastlimit()));
				}

				// 配置端口
				portInstList = new ArrayList<PortInst>();
				for (String portName : lagObject.getMenber().getPortName()) {
					portInstList.add(this.getPortInst(siteId, portName));
				}
				portLagInfo.setPortList(portInstList);

				// 配置QOS
				qosQueueList = new ArrayList<QosQueue>();
				qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.BE.getValue(), "0", lagObject.getBe().getWeight(), "", "", "", lagObject.getBe().getYellowwredstart(), lagObject.getBe().getYellowwredend(), lagObject.getBe().getYellowdroprate(), lagObject.getBe().getWreden(), ""));
				qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.AF1.getValue(), lagObject.getAf1().getCir(), lagObject.getAf1().getWeight(), lagObject.getAf1().getGreenwredstart(), lagObject.getAf1().getGreenwredend(), lagObject.getAf1().getGreendroprate(), lagObject.getAf1().getYellowwredstart(), lagObject.getAf1().getYellowwredend(), lagObject.getAf1().getYellowdroprate(), lagObject.getAf1().getWreden(), lagObject.getAf1().getMaxbw()));
				qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.AF2.getValue(), lagObject.getAf2().getCir(), lagObject.getAf2().getWeight(), lagObject.getAf2().getGreenwredstart(), lagObject.getAf2().getGreenwredend(), lagObject.getAf2().getGreendroprate(), lagObject.getAf2().getYellowwredstart(), lagObject.getAf2().getYellowwredend(), lagObject.getAf2().getYellowdroprate(), lagObject.getAf2().getWreden(), lagObject.getAf2().getMaxbw()));
				qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.AF3.getValue(), lagObject.getAf3().getCir(), lagObject.getAf3().getWeight(), lagObject.getAf3().getGreenwredstart(), lagObject.getAf3().getGreenwredend(), lagObject.getAf3().getGreendroprate(), lagObject.getAf3().getYellowwredstart(), lagObject.getAf3().getYellowwredend(), lagObject.getAf3().getYellowdroprate(), lagObject.getAf3().getWreden(), lagObject.getAf3().getMaxbw()));
				qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.AF4.getValue(), lagObject.getAf4().getCir(), lagObject.getAf4().getWeight(), lagObject.getAf4().getGreenwredstart(), lagObject.getAf4().getGreenwredend(), lagObject.getAf4().getGreendroprate(), lagObject.getAf4().getYellowwredstart(), lagObject.getAf4().getYellowwredend(), lagObject.getAf4().getYellowdroprate(), lagObject.getAf4().getWreden(), lagObject.getAf4().getMaxbw()));
				qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.EF.getValue(), lagObject.getEf().getCir(), "", lagObject.getEf().getGreenwredstart(), lagObject.getEf().getGreenwredend(), lagObject.getEf().getGreendroprate(), "", "", "", lagObject.getEf().getWreden(), ""));
				qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.CS6.getValue(), lagObject.getCs6().getCir(), "", lagObject.getCs6().getGreenwredstart(), lagObject.getCs6().getGreenwredend(), lagObject.getCs6().getGreendroprate(), "", "", "", lagObject.getCs6().getWreden(), ""));
				qosQueueList.add(this.getQosQue(siteId, QosCosLevelEnum.CS7.getValue(), lagObject.getCs7().getCir(), "", lagObject.getCs7().getGreenwredstart(), lagObject.getCs7().getGreenwredend(), lagObject.getCs7().getGreendroprate(), "", "", "", lagObject.getCs7().getWreden(), ""));

				portLagInfo.setQosQueueList(qosQueueList);
				portLagInfo.setLagStatus(EActiveStatus.ACTIVITY.getValue());
//System.out.println("PortLagCXServiceImpl.line 398 . lagObject="+lagObject.toString());
//System.out.println("PortLagCXServiceImpl.line 398 . portLagInfo="+portLagInfo.toString());
				// lag与数据库同步
				synchroUtil.portLagSynchro(portLagInfo);
			}
		}
	}

	/**
	 * 通过端口名和网元ID查询端口对象
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
	private PortInst getPortInst(int siteId, String portName) throws Exception {

		PortService_MB portService = null;
		PortInst portInst = null;
		List<PortInst> portInstList = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInst = new PortInst();
			portInst.setSiteId(siteId);
			portInst.setPortName(portName);

			portInstList = portService.select(portInst);

			if (null != portInstList && portInstList.size() == 1) {
				return portInstList.get(0);
			} else {
				throw new Exception("查询端口出错");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
		}

	}

	/**
	 * 获取端口qos对象
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private QosQueue getQosQue(int siteId, int cos, String cir, String weight, String greenLowThresh, String greenHighThresh, String greenProbability, String yellowLowThresh, String yellowHighThresh, String yellowProbability, String wredEnable, String mostBandwidth) {

		QosQueue qosQueue = new QosQueue();
		qosQueue.setSiteId(siteId);
		qosQueue.setObjType("LAG");
		qosQueue.setQueueType("SP+DWRR");
		qosQueue.setCos(cos);
		if (!"".equals(cir)) {
			qosQueue.setCir(Integer.parseInt(cir));
		}
		if (!"".equals(weight)) {
			qosQueue.setWeight(Integer.parseInt(weight));
		}
		if (!"".equals(greenLowThresh)) {
			qosQueue.setGreenLowThresh(Integer.parseInt(greenLowThresh));
		}
		if (!"".equals(greenHighThresh)) {
			qosQueue.setGreenHighThresh(Integer.parseInt(greenHighThresh));
		}
		if (!"".equals(greenProbability)) {
			qosQueue.setGreenProbability(Integer.parseInt(greenProbability));
		}
		if (!"".equals(yellowLowThresh)) {
			qosQueue.setYellowLowThresh(Integer.parseInt(yellowLowThresh));
		}
		if (!"".equals(yellowHighThresh)) {
			qosQueue.setYellowHighThresh(Integer.parseInt(yellowHighThresh));
		}
		if (!"".equals(yellowProbability)) {
			qosQueue.setYellowProbability(Integer.parseInt(yellowProbability));
		}
		if (!"".equals(wredEnable)) {
			qosQueue.setWredEnable(true);
		}
		if (!"".equals(mostBandwidth)) {
			qosQueue.setMostBandwidth(mostBandwidth);
		} else {
			qosQueue.setMostBandwidth(ResourceUtil.srcStr(StringKeysObj.QOS_UNLIMITED));
		}
		return qosQueue;
	}

}
