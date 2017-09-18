package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.drivechenxiao.service.bean.cmap.L2Object;
import com.nms.drivechenxiao.service.bean.cmap.L3Object;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcQosObject;
import com.nms.drivechenxiao.service.bean.service.eline.ELineObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.port.AcBufferService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.ptn.port.PortLagService_MB;
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
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class AcCXServiceImpl extends CXOperationBase implements OperationServiceI {
	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		OperationObject operationObject = null;
		List<AcPortInfo> acportinfolist = null;
		String result = null;
		Map<Integer,List<AcPortInfo>> map;
		try {
			acportinfolist = objectList;
			//按激活状态 ac分类
			map = this.acClassify(acportinfolist);
			//激活操作
			acportinfolist = map.get(EActiveStatus.ACTIVITY.getValue());
			if(null!= acportinfolist&&acportinfolist.size()>0){
				operationObject = this.getOperationMoreObject(acportinfolist, TypeAndActionUtil.ACTION_DELETE);
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
					this.deleteAcList(acportinfolist);
					// for (AcPortInfo acPortInfo : acportinfolist) {
					// this.bufferService.deletebyAcId(acPortInfo.getId());
					// }
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					result = super.getErrorMessage(operationObject);
				}
			}
			//未激活操作
			acportinfolist = map.get(EActiveStatus.UNACTIVITY.getValue());
			if(null!= acportinfolist&&acportinfolist.size()>0){
				this.deleteAcList(acportinfolist);
				result = ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
			acportinfolist = null;
		}
		return result;
	}

	/**
	 * 按激活状态 ac分类
	 * @param acportinfolist  ac集合
	 * @return
	 */
	private Map<Integer, List<AcPortInfo>> acClassify(
			List<AcPortInfo> acportinfolist) {
		Map<Integer, List<AcPortInfo>> map = new HashMap<Integer, List<AcPortInfo>>();
		List<AcPortInfo> activeAcList = new ArrayList<AcPortInfo>();
		List<AcPortInfo> unActiveAcList = new ArrayList<AcPortInfo>();
		for (AcPortInfo acPortInfo : acportinfolist) {
			if(acPortInfo.getAcStatus()==EActiveStatus.ACTIVITY.getValue()){
				activeAcList.add(acPortInfo);
			}
			if(acPortInfo.getAcStatus()==EActiveStatus.UNACTIVITY.getValue()){
				unActiveAcList.add(acPortInfo);
			}
		}
		map.put(EActiveStatus.ACTIVITY.getValue(), activeAcList);
		map.put(EActiveStatus.UNACTIVITY.getValue(), unActiveAcList);
		return map;
	}

	@Override
	public String excutionInsert(Object object) throws Exception, BusinessIdException {
		OperationObject operationObject = null;
		AcPortInfo acPortInfo = null;
		String result = null;
		QosRelevanceService_MB qosRelevanceService = null;
		AcPortInfoService_MB acInfoService = null;
		try {
			acPortInfo = (AcPortInfo) object;
			if(!acPortInfo.isDataDownLoad()){
				acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
				int acid = acInfoService.saveOrUpdate(acPortInfo.getBufferList(), acPortInfo);
				acPortInfo.setId(acid);
			}
			operationObject = this.getOperationObject(acPortInfo, TypeAndActionUtil.ACTION_INSERT);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
					// 修改qos状态
					qosRelevanceService = (QosRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSRELEVANCE);
					qosRelevanceService.updateQosStatus(acPortInfo);
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				if(!acPortInfo.isDataDownLoad()){
					this.deleteAc(acPortInfo);
				}
				result = super.getErrorMessage(operationObject);
			}
		} catch (BusinessIdException e) {
			throw e;
		} catch (Exception e) {
			this.deleteAc(acPortInfo);
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acInfoService);
			UiUtil.closeService_MB(qosRelevanceService);
		}
		return result;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		OperationObject operationObject = null;
		AcPortInfo acPortInfo = null;
		String result = null;
		AcPortInfo acPortInfo_before=null;
		QosRelevanceService_MB qosRelevanceService = null;
		AcPortInfoService_MB acInfoService = null;
		try {
			acPortInfo = (AcPortInfo) object;
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			//激活操作
			if(acPortInfo.getAcStatus()==EActiveStatus.ACTIVITY.getValue()){
				if(!acPortInfo.isDataDownLoad()){
					acPortInfo_before=acInfoService.selectByCondition(acPortInfo).get(0);
					acInfoService.saveOrUpdate(acPortInfo.getBufferList(), acPortInfo);
				}
				operationObject = this.getOperationObject(object, TypeAndActionUtil.ACTION_UPDATE);
				super.sendAction(operationObject);
				super.verification(operationObject);
				if (operationObject.isSuccess()) {
						// 修改qos状态
						qosRelevanceService = (QosRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSRELEVANCE);
						qosRelevanceService.updateQosStatus(acPortInfo);
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					if(!acPortInfo.isDataDownLoad()){
						acInfoService.saveOrUpdate(acPortInfo_before.getBufferList(), acPortInfo_before);
					}
					result = super.getErrorMessage(operationObject);
				}
			}else{
				//未激活操作
				acInfoService.saveOrUpdate(acPortInfo.getBufferList(), acPortInfo);
				// 修改qos状态
				qosRelevanceService = (QosRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSRELEVANCE);
				qosRelevanceService.updateQosStatus(acPortInfo);
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acInfoService);
			UiUtil.closeService_MB(qosRelevanceService);
		}
		return result;
	}

	private void deleteAc(AcPortInfo acPortInfo) throws Exception {
		List<AcPortInfo> acportinfolist = null;
		try {
			acportinfolist = new ArrayList<AcPortInfo>();
			acportinfolist.add(acPortInfo);
			this.deleteAcList(acportinfolist);
			// this.bufferService.deletebyAcId(acPortInfo.getId());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			acportinfolist = null;
		}
	}

	/**
	 * 获得AC对象
	 * 
	 * @param siteId
	 * @param action
	 * @return
	 * @throws Exception
	 */
	public OperationObject getOperationObject(Object object, String action) throws Exception {
		CXActionObject cxActionObject = null;
		AcPortInfo acportinfo = null;
		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();

			acportinfo = (AcPortInfo) object;
			cxActionObject = this.getCXObject(acportinfo, action);
			operationObject.getCxActionObjectList().add(cxActionObject);			

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			acportinfo = null;
			cxActionObject = null;
		}
		return operationObject;
	}

	/**
	 * 获得多个AC对象
	 * 
	 * @param acportInfoList
	 * @param action
	 * @return
	 * @throws Exception
	 */
	public OperationObject getOperationMoreObject(List<AcPortInfo> acportInfoList, String action) throws Exception {
		CXActionObject cxActionObject = null;
		OperationObject operationObject = null;
		try {
			operationObject = new OperationObject();

			for (AcPortInfo acPortInfo : acportInfoList) {
				cxActionObject = this.getCXObject(acPortInfo, action);
				operationObject.getCxActionObjectList().add(cxActionObject);	
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			cxActionObject = null;
		}
		return operationObject;
	}

	/**
	 * 获得CX对象
	 * 
	 * @param acPortInfo
	 * @param action
	 * @return
	 * @throws Exception
	 */
	private CXActionObject getCXObject(AcPortInfo acPortInfo, String action) throws Exception {

		if (acPortInfo == null) {
			throw new Exception("acPortInfo is null");
		}

		if (action == null || action.equals("")) {
			throw new Exception("action is  null");
		}

		CXActionObject cxActionObject = null;
		OperationObject operationObject = null;

		try {
			cxActionObject = new CXActionObject();
			operationObject = new OperationObject();
			cxActionObject.setCxNeObject(super.getCXNEObject(acPortInfo.getSiteId()));
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_AC);
			cxActionObject.setAction(action);
		
			if(!(acPortInfo.isDataDownLoad()&&action.equals(TypeAndActionUtil.ACTION_DELETE))){
				cxActionObject.setAcQosObject(this.getacqosObject(acPortInfo));
				
			}
			cxActionObject.setAcObject(this.getAcObject(acPortInfo, cxActionObject));
			// if (action.equals(TypeAndActionUtil.ACTION_DELETE)) {
			// cxActionObject.getAcObject().setDeleteQos(super.isDeleteQos(cxActionObject.getAcQosObject().getName()));
			// }
			cxActionObject.setEthPortObject(this.getEthPortObject(acPortInfo,action));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
		}
		return cxActionObject;
	}
	/**
	 * 获得ACQOS对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private List<AcQosObject> getAcBufferQosObject(AcPortInfo acportinfo) throws Exception {
		AcQosObject acQosObject = null;
		List<AcQosObject> list = new ArrayList<AcQosObject>();
		
		try {
			for(Acbuffer acbuffer :acportinfo.getBufferList()){
				list.add(this.getBufferObject(acbuffer));
				
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return list;

	}
	/**
	 * 获得ACQOS对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private AcQosObject getacqosObject(AcPortInfo acportinfo) throws Exception {
		AcQosObject acQosObject = null;
		List<AcQosObject> XFAcQosList = new ArrayList<AcQosObject>();
		try {
			acQosObject = super.getacobject(acportinfo);
			for(Acbuffer acbuffer:acportinfo.getSimpleQos().getBufferList()){
				XFAcQosList.add(this.getBufferObject(acbuffer));
			}
			acQosObject.setXFAcQosList(XFAcQosList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return acQosObject;

	}

	private AcQosObject getBufferObject(Acbuffer acbuffer) throws Exception {
		AcQosObject acQosObject = new AcQosObject();
		acQosObject.setName(acbuffer.getQosName());
		if (acbuffer.getPhb() == QosCosLevelEnum.AF1.getValue()) {
			acQosObject.setCos("af1");
		} else if (acbuffer.getPhb() == QosCosLevelEnum.AF2.getValue()) {
			acQosObject.setCos("af2");
		} else if (acbuffer.getPhb() == QosCosLevelEnum.AF3.getValue()) {
			acQosObject.setCos("af3");
		} else if (acbuffer.getPhb() == QosCosLevelEnum.AF4.getValue()) {
			acQosObject.setCos("af4");
		} else if (acbuffer.getPhb() == QosCosLevelEnum.BE.getValue()) {
			acQosObject.setCos("be");
		} else if (acbuffer.getPhb() == QosCosLevelEnum.EF.getValue()) {
			acQosObject.setCos("ef");
		} else if (acbuffer.getPhb() == QosCosLevelEnum.CS6.getValue()) {
			acQosObject.setCos("cs6");
		} else if (acbuffer.getPhb() == QosCosLevelEnum.CS7.getValue()) {
			acQosObject.setCos("cs7");
		}
		acQosObject.setSeq(acbuffer.getSeq()+"");
		acQosObject.setCir(acbuffer.getCir()+"");
		acQosObject.setCbs(acbuffer.getCbs()+"");
		acQosObject.setEir(acbuffer.getEir()+"");
		acQosObject.setEbs(acbuffer.getEbs()+"");
		if(0==acbuffer.getCm()){
			acQosObject.setColoraware("false");
		}else{
			acQosObject.setColoraware("true");
		}
		acQosObject.setPir(acbuffer.getPir()+"");
		
		
		if("L2".equals(UiUtil.getCodeByValue("BUFTYPE", acbuffer.getQosType()+"").getCodeName())){
			acQosObject.setType("ETHAC_L2");//ETHAC_L2,ETHAC_L3,ETHAC_VLANPRI
			acQosObject.setCreatel2(acbuffer.isCreate());
			L2Object l2Object = new L2Object();
			l2Object.setName(acbuffer.getAppendBufferName());
			l2Object.setSpvlan(acbuffer.getOperatorVlanIdValue());
			l2Object.setSpvlanmask(acbuffer.getOperatorVlanIdMask());
			l2Object.setCevlan(acbuffer.getClientVlanIdValue());
			l2Object.setCevlanmask(acbuffer.getClientVlanIdMask());
			l2Object.setSpvlanpri(acbuffer.getOperatorVlanPriorityValue());
			l2Object.setSpvlanprimask(acbuffer.getOperatorVlanPriorityMask());
			l2Object.setCevlanpri(acbuffer.getClientVlanPriorityValue());
			l2Object.setCevlanprimask(acbuffer.getClientVlanPriorityMask());
			l2Object.setType("0");
			l2Object.setRef("0");
			l2Object.setEthtype(acbuffer.getEthernetTypeValue());
			l2Object.setEthtypemask(acbuffer.getEthernetTypeMask());
			l2Object.setDstmac(acbuffer.getTargetMac());
			l2Object.setDstmacmask(acbuffer.getSinkMacLabelMask());
			l2Object.setSrcmac(acbuffer.getSourceMac());
			l2Object.setSrcmacmask(acbuffer.getSourceMacLabelMask());
			acQosObject.setL2(l2Object);
		}
		if("L3".equals(UiUtil.getCodeByValue("BUFTYPE", acbuffer.getQosType()+"").getCodeName())){
			acQosObject.setCreatel3(acbuffer.isCreate());
			acQosObject.setType("ETHAC_L3");//ETHAC_L2,ETHAC_L3,ETHAC_VLANPRI
			L3Object l3Object = new L3Object();
			l3Object.setName(acbuffer.getAppendBufferName());
			l3Object.setSpvlan(acbuffer.getOperatorVlanIdValue());
			l3Object.setSpvlanmask(acbuffer.getOperatorVlanIdMask());
			l3Object.setCevlan(acbuffer.getClientVlanIdValue());
			l3Object.setCevlanmask(acbuffer.getClientVlanIdMask());
			l3Object.setSpvlanpri(acbuffer.getOperatorVlanPriorityValue());
			l3Object.setSpvlanprimask(acbuffer.getOperatorVlanPriorityMask());
			l3Object.setCevlanpri(acbuffer.getClientVlanPriorityValue());
			l3Object.setCevlanprimask(acbuffer.getClientVlanPriorityMask());
			l3Object.setType("1");
			l3Object.setRef("0");
			l3Object.setDstip(acbuffer.getTargetIp());
			l3Object.setDstipmask(acbuffer.getSinkIpLabelMask());
			l3Object.setSrcip(acbuffer.getSourceIp());
			l3Object.setSrcipmask(acbuffer.getSourceIpLabelMask());
			l3Object.setPid(acbuffer.getiPTypeValue());
			l3Object.setPidmask(acbuffer.getiPTypeMask());
			acQosObject.setL3(l3Object);
		}else{
			acQosObject.setType("ETHAC_VLANPRI");//ETHAC_L2,ETHAC_L3,ETHAC_VLANPRI
			acQosObject.setName(acbuffer.getAppendBufferName());
		}
		return acQosObject;
	}

	/**
	 * 获得ETH端口对象
	 * 
	 * @param acPortInfo
	 *            acPortInfo实体
	 * @return
	 * @throws Exception
	 */
	private EthPortObject getEthPortObject(AcPortInfo acPortInfo,String action) throws Exception {
		EthPortObject ethPortObject = new EthPortObject();
		try {
			if (acPortInfo.getPortId() > 0) {
				ethPortObject.setName(super.getPortname(acPortInfo.getPortId()));
				ethPortObject.setPortType("eth");
			} else {
				if(!(acPortInfo.isDataDownLoad()&&action.equals(TypeAndActionUtil.ACTION_DELETE))){
					ethPortObject.setName(super.getLagName(acPortInfo.getLagId()));
				}else{
					ethPortObject.setName(acPortInfo.getLagId()+"");
				}
				ethPortObject.setPortType("lag");
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ethPortObject;
	}

	/**
	 * 获得ETH端口对象
	 * 
	 * @param acPortInfo
	 *            acPortInfo实体
	 * @return
	 * @throws Exception
	 */
	private EthPortObject getEthPortObjectByPortName(PortInst portInst) throws Exception {
		EthPortObject ethPortObject = new EthPortObject();
		try {
			if (portInst.getLagId() == 0) {
				ethPortObject.setName(super.getPortname(portInst.getPortId()));
				ethPortObject.setPortType("eth");
			} else {
				ethPortObject.setName(super.getLagName(portInst.getLagId()));
				ethPortObject.setPortType("lag");
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ethPortObject;
	}

	/**
	 * 获取驱动层ac对象
	 * 
	 * @param acPortInfo
	 *            db层ac对象
	 * @return 驱动层ac对象
	 * @throws Exception
	 */
	private AcObject getAcObject(AcPortInfo acPortInfo, CXActionObject cxActionObject) throws Exception {

		if (acPortInfo == null) {
			throw new Exception("acPortInfo is null");
		}
		AcObject acobject = null;
		String portModel = null;
		QosInfo qosInfo = null;
		try {
			acobject = new AcObject();
			acobject.setName(acPortInfo.getAcBusinessId()+"");
			if(0!=acPortInfo.getPortModel()){
				portModel = UiUtil.getCodeById(acPortInfo.getPortModel()).getCodeValue();

				if ("0".equals(portModel)) {
					acobject.setMode("port");
				} else if ("1".equals(portModel)) {
					acobject.setMode("port_plus_spvlan");
				}
			}
			acobject.setSpvlan(acPortInfo.getOperatorVlanId());
			acobject.setCevlan(acPortInfo.getClientVlanId());
			// -----
			if(0!=acPortInfo.getExitRule()){
				acobject.setAction(UiUtil.getCodeById(acPortInfo.getExitRule()).getCodeValue());
			}
			// -----
			acobject.setSdvlan(acPortInfo.getVlanId());
			acobject.setSdvlanpri(acPortInfo.getVlanpri());
			acobject.setSdvlancfi(acPortInfo.getVlancri());
			// System.out.println("AcCXServiceImpl.line346 . cxActionObject.getAcQosObject().getName() ="+cxActionObject.getAcQosObject().getName());

			qosInfo = super.getQosInfo(EServiceType.ACPORT.toString(), acPortInfo.getId(), acPortInfo.getSiteId());
			acobject.setQos(qosInfo.getQosname());
			// 是否创建qos
			if (qosInfo.getStatus() == 1) {
				acobject.setCreateQos(true);
			}

			// if (super.getSiteType(acPortInfo.getSiteId()).equals("cxt20a")) {
			// acobject.setEthacout("ethacout" + acPortInfo.getAcServiceId());
			// }
			acobject.setAdmin("up");

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return acobject;
	}

	/**
	 * 批量删除AC
	 * 
	 * @param acPortInfosList
	 * @throws Exception
	 */
	public void deleteAcList(List<AcPortInfo> acPortInfosList) throws Exception {
		List<Integer> idList = null;
		int id = 0;
		AcPortInfoService_MB acInfoService = null;
		try {
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			idList = new ArrayList<Integer>();
			for (int i = 0; i < acPortInfosList.size(); i++) {
				id = acPortInfosList.get(i).getId();
				idList.add(id);
			}
			acInfoService.delete(idList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}
	}

	/**
	 * 同步ac
	 * 
	 * @author wangwf
	 * 
	 * @param siteId
	 *            网元id
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		AcPortInfoService_MB acInfoService = null;
		try {
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_AC);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				//同步ac前 先同步qos
				this.getQosInfoList(null, siteId);
				//把ac全部改成未激活状态
				acInfoService.updateActiveStatus(siteId,EActiveStatus.UNACTIVITY.getValue());
				this.synchro_db(operationObject.getCxActionObjectList(), siteId, false);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}

		return null;
	}

	public Object synchro_one(ELineObject eLineObject, int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();

		try {
			this.getOperactionObject_select_one(operationObject, eLineObject.getPortList().getAc(), TypeAndActionUtil.TYPE_AC, siteId);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				operationObject.getCxActionObjectList().get(0).setElineObject(eLineObject);
				this.synchro_db(operationObject.getCxActionObjectList(), siteId, true);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		return null;
	}

	private void getOperactionObject_select_one(OperationObject operationObject, String portName, String type, int siteId) throws Exception {
		CXActionObject actionObject = null;
		EthPortObject ethPortObject = new EthPortObject();
		actionObject = new CXActionObject();
		String[] port_ac = portName.split("/");
		if (port_ac.length != 2 && port_ac.length != 3) {
			throw new Exception("设备返回的acname格式错误");
		} else if (port_ac.length == 2) {
			ethPortObject.setName(port_ac[0]);
			ethPortObject.setPortType("eth");
		} else if (port_ac.length == 3) {
			ethPortObject.setName(port_ac[1]);
			ethPortObject.setPortType("lag");
		}

		actionObject.setEthPortObject(ethPortObject);
		actionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
		actionObject.setCxNeObject(super.getCXNEObject(siteId));
		actionObject.setType(type);
		actionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
		operationObject.getCxActionObjectList().add(actionObject);
	}

	/**
	 * 获取OperationObject对象 查询用
	 * 
	 * @author wangwf
	 * 
	 * @param siteId
	 *            网元id
	 * @param operationObject
	 *            OperationObject对象
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void getOperactionObject_select(OperationObject operationObject, int siteId, String type) throws Exception {
		
		CXActionObject actionObject = null;
		PortService_MB portService = null;
		PortLagService_MB portLagService = null;
		
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			PortInst portInstSelect = new PortInst();
			portInstSelect.setSiteId(siteId);
			portInstSelect.setPortType("uni");
			List<PortInst> portInstList = portService.select(portInstSelect);
			// 查询所有uni端口 过滤掉lag
			// query by siteId and porttype="uni" get portList portName
			for (PortInst portInst : portInstList) {
				if (portInst.getLagId() == 0) {
					actionObject = new CXActionObject();
					actionObject.setEthPortObject(this.getEthPortObjectByPortName(portInst));
					actionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
					actionObject.setCxNeObject(super.getCXNEObject(siteId));
					actionObject.setType(type);
					actionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
					operationObject.getCxActionObjectList().add(actionObject);
				}
				
			}
			// 查询lag
			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			PortLagInfo portLagInfo_select = new PortLagInfo();
			portLagInfo_select.setSiteId(siteId);
			List<PortLagInfo> portLagInfoList = portLagService.selectByCondition(portLagInfo_select);
			
			for (PortLagInfo portLagInfo : portLagInfoList) {
				actionObject = new CXActionObject();
				actionObject.setEthPortObject(this.getEthPortObjectByPortName(portLagInfo.getPortList().get(0)));
				actionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
				actionObject.setCxNeObject(super.getCXNEObject(siteId));
				actionObject.setType(type);
				actionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
				operationObject.getCxActionObjectList().add(actionObject);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(portLagService);
		}
	}

	/**
	 * 与数据库做同步
	 * 
	 * @author wangwf
	 * 
	 * @param cxActionObjectList
	 * @param siteId
	 * @param isEline
	 *            是否为ELINE同步
	 * @throws Exception
	 * @Exception 异常对象
	 */
	private void synchro_db(List<CXActionObject> cxActionObjectList, int siteId, boolean isEline) throws Exception {

		if (null == cxActionObjectList) {
			throw new Exception("acObjectList is null");
		}
		ElineInfoService_MB elineService = null;
		PortLagService_MB portLagService = null;
		try {
			 elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			for (CXActionObject cxActionObject : cxActionObjectList) {
				List<AcObject> acObjectList = cxActionObject.getAcObjectList();
				AcPortInfo acPortInfo = null;
				List<QosInfo> qosInfoList = null;
				// List<QosInfo> qosInfoList=null;
				for (AcObject acObject : acObjectList) {
					acPortInfo = new AcPortInfo();
					acPortInfo.setAcBusinessId(Integer.parseInt(acObject.getName()));
					acPortInfo.setJobStatus(acObject.getOper());
					if ("eth".equals(cxActionObject.getEthPortObject().getPortType())) {
						PortInst portInst = super.getPortByName(siteId, cxActionObject.getEthPortObject().getName());
						if (null == portInst) {
							ExceptionManage.logDebug("同步AC失败，没有找到" + cxActionObject.getEthPortObject().getName() + "端口",this.getClass());
							continue;
						}
						acPortInfo.setPortId(portInst.getPortId());
						acPortInfo.setName(cxActionObject.getEthPortObject().getName() + "_" + super.getNowMS());
					} else {
						portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
						PortLagInfo portLagInfo = new PortLagInfo();
						portLagInfo.setSiteId(siteId);
						portLagInfo.setLagID(Integer.parseInt(cxActionObject.getEthPortObject().getName()));
						List<PortLagInfo> portLagInfoList = portLagService.selectByCondition(portLagInfo);
						if (null == portLagInfoList || portLagInfoList.size() != 1) {
							ExceptionManage.logDebug("同步AC失败，没有找到" + cxActionObject.getEthPortObject().getName() + "LAG",this.getClass());
							continue;
						}
						acPortInfo.setLagId(portLagInfoList.get(0).getId());
						acPortInfo.setName("lag/" + cxActionObject.getEthPortObject().getName() + "_" + super.getNowMS());
					}
					
					acPortInfo.setPortModel(UiUtil.getCodeByValue("portModel", acObject.getMode()).getId());
					// acPortInfo.setManagerEnable(UiUtil.getCodeByValue("", acObject.getAdmin()).getId());
					acPortInfo.setVlanId(acObject.getSdvlan());
					acPortInfo.setSiteId(siteId);
					acPortInfo.setVlancri(acObject.getSdvlancfi());
					acPortInfo.setVlanpri(acObject.getSdvlanpri());
					acPortInfo.setOperatorVlanId(acObject.getSpvlan());
					acPortInfo.setClientVlanId(acObject.getCevlan());
					// -----
					acPortInfo.setExitRule(UiUtil.getCodeByValue("exitRule", acObject.getAction()).getId());
					acPortInfo.setBufType(UiUtil.getCodeByValue("BUFTYPE", acObject.getL3iifhwres()).getId());
					// -----
					qosInfoList = super.qosInfo(acObject.getQos(), siteId);
					if (null != qosInfoList && qosInfoList.size() > 0) {
						acPortInfo.setSimpleQos(qosInfoList.get(0));
					}
					// System.out.println("---AcCXServiceImpl line 571 . name="+acObject.getIfname()+" ; acObject.getQos()="+acObject.getQos());
					String type = acObject.getQos().substring(0, 1);
					if ("l2".equals(type)) {
						acPortInfo.setBufType(UiUtil.getCodeByValue("BUFTYPE", "0").getId());
					} else if ("l3".equals(type)) {
						acPortInfo.setBufType(UiUtil.getCodeByValue("BUFTYPE", "1").getId());
					} else {
						acPortInfo.setBufType(UiUtil.getCodeByValue("BUFTYPE", "2").getId());
					}
					
					if (isEline) {
						if (null != acObject.getOam().getMegid() && !"".equals(acObject.getOam().getMegid())) {
							OamInfo oamInfo = new OamInfo();
							oamInfo = convertOamInfo_mep(acObject.getOam()).get(0);
							oamInfo.setOamType(OamTypeEnum.MEP);
							oamInfo.getOamMep().setObjType(EServiceType.ELINE.toString());
							oamInfo.getOamMep().setSiteId(siteId);
							oamInfo.getOamMep().setObjId(Integer.valueOf(cxActionObject.getElineObject().getName()));
							oamInfo.getOamMep().setServiceId(elineService.select_synchro(siteId, Integer.valueOf(cxActionObject.getElineObject().getName())).get(0).getId());
							acPortInfo.getOamList().add(oamInfo);
						}
					}
					acPortInfo.setAcStatus(EActiveStatus.ACTIVITY.getValue());
					// System.out.println("AcCXServiceInpl.592 . acObject ="+acObject.toString());
					// System.out.println("AcCXServiceInpl.593 . acPortInfo ="+acPortInfo.toString());
					SynchroUtil synchroUtil = new SynchroUtil();
					synchroUtil.acPortInfoSynchro(acPortInfo, siteId);
				}
			}
		} catch (Exception e) {
			throw e;
		  }finally{
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(portLagService);
		}
	}

	/**
	 * 从设备查询AC的qos
	 * 
	 * @author kk
	 * 
	 * @param qosName
	 *            设备qos名称
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private QosInfo getQosInfoList(String qosName, int siteid) throws Exception {
		QosInfo qosInfo = null;
		OperationObject operationObject = null;
		CXActionObject cxActionObject = new CXActionObject();
		QosInfoService_MB qosInfoService=null;
		AcBufferService_MB bufservice = null;
		try {
			bufservice = (AcBufferService_MB) ConstantUtil.serviceFactory.newService_MB(Services.UniBuffer);
			operationObject = new OperationObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_ACQOS);
			cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
			cxActionObject.setCxNeObject(super.getCXNEObject(siteid));
			cxActionObject.setAcObject(this.getAcObject_select(qosName));

			operationObject.getCxActionObjectList().add(cxActionObject);

			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				// 同步前把所有的状态修改
				qosInfoService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
				qosInfoService.updateStatus(siteid, "ac", null, 1);
				this.synchroQos_db(operationObject.getCxActionObjectList().get(0).getAcQosObjectList(), siteid);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(bufservice);
			UiUtil.closeService_MB(qosInfoService);
		}
		return qosInfo;
	}

	/**
	 * 查询acqos 转换驱动层要的acobject对象
	 * 
	 * @author kk
	 * 
	 * @param qosName
	 *            qos名称
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private AcObject getAcObject_select(String qosName) {
		AcObject acObject = new AcObject();
		acObject.setQos(qosName);
		return acObject;
	}

	/**
	 * ac的qos与数据库同步
	 * 
	 * @param acQosObjectList
	 *            设备的acqos集合
	 * @param siteId
	 *            网元ID
	 * @throws Exception
	 */
	private void synchroQos_db(List<AcQosObject> acQosObjectList, int siteId) throws Exception {

		QosInfo qosInfo = null;
		List<QosInfo> qosInfoList = null;
		try {
			SynchroUtil synchroUtil = new SynchroUtil();
			for (AcQosObject acQosObject : acQosObjectList) {
				qosInfo = this.convertQosInfo(acQosObject, siteId);
				qosInfoList = new ArrayList<QosInfo>();
				qosInfoList.add(qosInfo);
				synchroUtil.updateQos(qosInfoList);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			qosInfo = null;
			qosInfoList = null;
		}

	}

	/**
	 * 转换Q OS对象
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private QosInfo convertQosInfo(AcQosObject acQosObject, int siteId) { 
		String qosType=null;
		
		QosInfo qosInfo = new QosInfo();
		qosInfo.setCos(Integer.parseInt(acQosObject.getCos()));
		qosInfo.setCir(Integer.parseInt(acQosObject.getCir()));
		qosInfo.setCbs(Integer.parseInt(acQosObject.getCbs()));
		qosInfo.setEir(Integer.parseInt(acQosObject.getEir()));
		qosInfo.setEbs(Integer.parseInt(acQosObject.getEbs()));
		qosInfo.setPir(Integer.parseInt(acQosObject.getPir()));
		qosInfo.setSeq(Integer.parseInt(acQosObject.getSeq()));
		qosInfo.setQosname(acQosObject.getName());
		qosInfo.setSiteId(siteId);
		qosInfo.setStatus(2);
		try {
			convertQosInfo(acQosObject,qosInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
		qosType=qosInfo.getQosname().substring(0,2);
		if("l2".equals(qosType) || "l3".equals(qosType)){
			qosInfo.setQosType(qosType.toUpperCase());
		}else{
			qosInfo.setQosType("vlanpri");
		}
		
		return qosInfo;
	}

	private void convertQosInfo(AcQosObject acQosObject,QosInfo qosInfo) throws Exception {
		Acbuffer acBuffer = null;
		String qosType=null;
		List<Acbuffer> bufferList = new ArrayList<Acbuffer>();
		for (AcQosObject bufferObj : acQosObject.getXFAcQosList()) {
			acBuffer = new Acbuffer();
			acBuffer.setQosName(bufferObj.getName());
			if(QosCosLevelEnum.AF1.toString().toLowerCase().equals(bufferObj.getCos())){
				acBuffer.setPhb(QosCosLevelEnum.AF1.getValue());
			}else if(QosCosLevelEnum.AF2.toString().toLowerCase().equals(bufferObj.getCos())){
				acBuffer.setPhb(QosCosLevelEnum.AF2.getValue());
			}else if(QosCosLevelEnum.AF3.toString().toLowerCase().equals(bufferObj.getCos())){
				acBuffer.setPhb(QosCosLevelEnum.AF3.getValue());
			}else if(QosCosLevelEnum.AF4.toString().toLowerCase().equals(bufferObj.getCos())){
				acBuffer.setPhb(QosCosLevelEnum.AF4.getValue());
			}else if(QosCosLevelEnum.BE.toString().toLowerCase().equals(bufferObj.getCos())){
				acBuffer.setPhb(QosCosLevelEnum.BE.getValue());
			}else if(QosCosLevelEnum.EF.toString().toLowerCase().equals(bufferObj.getCos())){
				acBuffer.setPhb(QosCosLevelEnum.EF.getValue());
			}else if(QosCosLevelEnum.CS6.toString().toLowerCase().equals(bufferObj.getCos())){
				acBuffer.setPhb(QosCosLevelEnum.CS6.getValue());
			}else if(QosCosLevelEnum.CS7.toString().toLowerCase().equals(bufferObj.getCos())){
				acBuffer.setPhb(QosCosLevelEnum.CS7.getValue());
			}
			acBuffer.setCir(Integer.parseInt(bufferObj.getCir()));
			acBuffer.setCbs(Integer.parseInt(bufferObj.getCbs()));
			acBuffer.setEir(Integer.parseInt(bufferObj.getEir()));
			acBuffer.setEbs(Integer.parseInt(bufferObj.getEbs()));
			acBuffer.setPir(Integer.parseInt(bufferObj.getPir()));
			acBuffer.setSeq(Integer.parseInt(bufferObj.getSeq()));
			if("false".equals(bufferObj.getColoraware())){
				acBuffer.setCm(0);
			}else{
				acBuffer.setCm(1);
			}
			qosType=qosInfo.getQosname().substring(0,2);
			if("l2".equals(qosType)){
				L2Object l2Object = bufferObj.getL2();
				acBuffer.setQosType(((Code)UiUtil.getCodeByValue("BUFTYPE", "0")).getCodeValue());
				acBuffer.setAppendBufferName(l2Object.getName());
				acBuffer.setOperatorVlanIdValue(l2Object.getSpvlan());
				acBuffer.setOperatorVlanIdMask(l2Object.getSpvlanmask());
				acBuffer.setClientVlanIdValue(l2Object.getCevlan());
				acBuffer.setClientVlanIdMask(l2Object.getCevlanmask());
				acBuffer.setOperatorVlanPriorityValue(l2Object.getSpvlanpri());
				acBuffer.setOperatorVlanPriorityMask(l2Object.getSpvlanprimask());
				acBuffer.setClientVlanPriorityValue(l2Object.getCevlanpri());
				acBuffer.setClientVlanPriorityMask(l2Object.getCevlanprimask());
				acBuffer.setEthernetTypeValue(l2Object.getEthtype());
				acBuffer.setEthernetTypeMask(l2Object.getEthtypemask());
				acBuffer.setTargetMac(l2Object.getDstmac());
				acBuffer.setSinkMacLabelMask(l2Object.getDstmacmask());
				acBuffer.setSourceMac(l2Object.getSrcmac());
				acBuffer.setSourceMacLabelMask(l2Object.getSrcmacmask());
			}if( "l3".equals(qosType)){
				acBuffer.setQosType(((Code)UiUtil.getCodeByValue("BUFTYPE", "1")).getCodeValue());
				L3Object l3Object = bufferObj.getL3();
				acBuffer.setAppendBufferName(l3Object.getName());
				acBuffer.setOperatorVlanIdValue(l3Object.getSpvlan());
				acBuffer.setOperatorVlanIdMask(l3Object.getSpvlanmask());
				acBuffer.setClientVlanIdValue(l3Object.getCevlan());
				acBuffer.setClientVlanIdMask(l3Object.getCevlanmask());
				acBuffer.setOperatorVlanPriorityValue(l3Object.getSpvlanpri());
				acBuffer.setOperatorVlanPriorityMask(l3Object.getSpvlanprimask());
				acBuffer.setClientVlanPriorityValue(l3Object.getCevlanpri());
				acBuffer.setClientVlanPriorityMask(l3Object.getCevlanprimask());
				acBuffer.setTargetIp(l3Object.getDstip());
				acBuffer.setSinkIpLabelMask(l3Object.getDstipmask());
				acBuffer.setSourceIp(l3Object.getSrcip());
				acBuffer.setSourceIpLabelMask(l3Object.getSrcipmask());
				acBuffer.setiPTypeValue(l3Object.getPid());
				acBuffer.setiPTypeMask(l3Object.getPidmask());
			}else{
				acBuffer.setQosType(((Code)UiUtil.getCodeByValue("BUFTYPE", "2")).getCodeValue());
				acBuffer.setAppendBufferName(bufferObj.getName());
			}
			bufferList.add(acBuffer);
		}
		qosInfo.setBufferList(bufferList);
	}
}
