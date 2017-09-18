package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortStmTimeslot;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.ECesType;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EPwType;
import com.nms.db.enums.EServiceType;
import com.nms.drivechenxiao.service.bean.portpdh.PdhPortObject;
import com.nms.drivechenxiao.service.bean.portpdh.ac.PdhAcObject;
import com.nms.drivechenxiao.service.bean.protsdh.SdhPortObject;
import com.nms.drivechenxiao.service.bean.protsdh.ac.SdhAcObject;
import com.nms.drivechenxiao.service.bean.pwpdh.PwPdhObject;
import com.nms.drivechenxiao.service.bean.pwpdh.service.CesServiceObject;
import com.nms.drivechenxiao.service.bean.pwsdh.PwSdhObject;
import com.nms.drivechenxiao.service.bean.service.PortList;
import com.nms.drivechenxiao.service.bean.service.ces.CesObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.port.PortStmTimeslotService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
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
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class CesCXServiceImpl extends CXOperationBase implements OperationServiceI {
	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		List<CesInfo> cesInfos = null;
		OperationObject operationObject = null;
		String errorMessage = null;
		CesInfoService_MB cesInfoService = null;
		try {
			cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			cesInfos = objectList;
			operationObject = this.getOperationObject(cesInfos, TypeAndActionUtil.ACTION_DELETE);
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
			cesInfoService.delete(cesInfos);
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			cesInfos = null;
			operationObject = null;
			UiUtil.closeService_MB(cesInfoService);
		}
		return errorMessage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String excutionInsert(Object object) throws Exception {
		List<CesInfo> cesInfos = null;
		OperationObject operationObject = null;
		String errorMessage = null;
		CesInfoService_MB cesInfoService = null;
		try {
			cesInfos = (List<CesInfo>) object;
			cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			operationObject = this.getOperationObject(cesInfos, TypeAndActionUtil.ACTION_INSERT);
			if (operationObject.getCxActionObjectList().size() > 0) {
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
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
			cesInfoService.delete(cesInfos);
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(cesInfoService);
			operationObject = null;
			cesInfos=null;
		}
		return errorMessage;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		CesInfo cesInfo = null;
		List<CesInfo> cesList;
		OperationObject operationObject = null;
		String result = null;
		String action=null;	//下发类型。 新建或删除
		int aPortId = 0;
		int zPortId = 0;
		CesInfoService_MB cesInfoService = null;
		try {
			cesInfo = (CesInfo) object;
			cesList = new ArrayList<CesInfo>();
			cesList.add(cesInfo);
			cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			//如果是激活直接操作
			if(cesInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue()){
				if(cesInfo.getBeforeActiveStatus() == EActiveStatus.UNACTIVITY.getValue()){
					action=TypeAndActionUtil.ACTION_INSERT;
				}else{
					action=TypeAndActionUtil.ACTION_UPDATE;
				}
			}else{ 
				if(cesInfo.getBeforeActiveStatus() == EActiveStatus.ACTIVITY.getValue()){
					//未激活还原数据  进行删除操作
					if(null!=cesInfo.getBeforeAPort()){
						aPortId = cesInfo.getaAcId();
						cesInfo.setaAcId(cesInfo.getBeforeAPort().getPortId());
					}
					if(null!=cesInfo.getBeforeZPort()){
						zPortId = cesInfo.getzAcId();
						cesInfo.setzAcId(cesInfo.getBeforeZPort().getPortId());
					}
					action=TypeAndActionUtil.ACTION_DELETE;
				}else{
					return result = ResultString.CONFIG_SUCCESS;
				}
			}
			operationObject = this.getOperationObject(cesList, action);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				cesInfoService.update(cesList);
				if(operationObject.getCxActionObjectList()!=null&&operationObject.getCxActionObjectList().size()>0){
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				}else{//A Z端都为虚拟网元，
					return result = ResultString.CONFIG_SUCCESS;
				}
				
			} else {
				result = super.getErrorMessage(operationObject);
			}
			if(0!=aPortId){
				cesInfo.setaAcId(aPortId);
			}
			if(0!=zPortId){
				cesInfo.setzAcId(zPortId);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(cesInfoService);
			cesInfo = null;
			operationObject = null;
			cesList = null;
			action = null;
		}
		return result;
	}

	/**
	 * 获取OperationObject对象
	 * 
	 * @param elineInfo
	 * @param action
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(List<CesInfo> cesInfos, String action) throws Exception {
		OperationObject operationObject = null;
		String type = null;
		int siteId=0;
		try {
			operationObject = new OperationObject();
			SiteUtil siteUtil=new SiteUtil();
			for (CesInfo cesInfo : cesInfos) {
				if (cesInfo.getIsSingle() == 0) {
					if (super.getManufacturer(cesInfo.getaSiteId()) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(cesInfo.getaSiteId())) {
						operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, cesInfo, action, "a"));
					}
					if (super.getManufacturer(cesInfo.getzSiteId()) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(cesInfo.getzSiteId())) {
						operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, cesInfo, action, "z"));
					}
				} else {
					if (cesInfo.getaSiteId() != 0) {
						type = "a";
						siteId=cesInfo.getaSiteId();
					} else {
						type = "z";
						siteId=cesInfo.getzSiteId();
					}
					if (super.getManufacturer(siteId) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(siteId)) {
						operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, cesInfo, action, type));
					}
				}

			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			type=null;
		}
		return operationObject;
	}

	private CXActionObject getCXActionObject(OperationObject operationObject, CesInfo cesInfos, String action, String type) throws Exception {
		CXActionObject cxActionObject = null;
		int siteId = 0;
		int cestype = 0;
		int portid = 0;
		try {
			cestype = this.getCesType(cesInfos, type);
			cxActionObject = new CXActionObject();

			if (type.equals("a")) {
				siteId = cesInfos.getaSiteId();
				portid = cesInfos.getaAcId();
			} else {
				siteId = cesInfos.getzSiteId();
				portid = cesInfos.getzAcId();
			}

			if (cestype == ECesType.PDH.getValue()) {
				cxActionObject.setType(TypeAndActionUtil.TYPE_CESPDH);
				cxActionObject.setPdhAcObject(convertPdhAcObject(cesInfos, type));
				cxActionObject.setPdhPortObject(convertPdhPortObject(super.getPortname(portid),cesInfos,type));
				dataDownLoadPDH(cesInfos,cxActionObject,action,type,cestype);
				cxActionObject.setCesObject(convertCesObject(cesInfos, type));
				/*cxActionObject.setPwPdhObject(convertPwPdhObject(cesInfos, type));*/
			} else {
				
				cxActionObject.setSdhPortObject(convertSdhPortObject(portid,cesInfos,type));
				cxActionObject.setSdhAcObject(convertSdhAcObject(portid, cesInfos, type));
				cxActionObject.setType(TypeAndActionUtil.TYPE_CESSDH);
				cxActionObject.setCesObject(convertCesObject(cesInfos, type));
				dataDownLoadSDH(cesInfos,cxActionObject,action,type,cestype);
		//		cxActionObject.setPwSdhObject(convertPwSdhObject(cesInfos, type));
				
			}

			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setAction(action);
			cxActionObject.setCxNeObject(super.getCXNEObject(siteId));

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return cxActionObject;
	}


	private int getCesType(CesInfo cesInfo, String type) {

		if (cesInfo.getCestype() == ECesType.PDHSDH.getValue()) {
			if ("a".equals(type)) {
				return ECesType.PDH.getValue();
			} else {
				return ECesType.SDH.getValue();
			}
		} else if (cesInfo.getCestype() == ECesType.SDHPDH.getValue()) {
			if ("z".equals(type)) {
				return ECesType.PDH.getValue();
			} else {
				return ECesType.SDH.getValue();
			}
		} else {
			return cesInfo.getCestype();
		}

	}

	/**
	 * 获得pdhpw端口对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private PwPdhObject convertPwPdhObject(CesInfo cesInfo, String type) throws Exception {
		if (null == cesInfo) {
			throw new Exception("cesInfo is null");
		}

		PwInfo pwInfo = null;
		PwPdhObject pwpdhObject = null;
		try {

			pwpdhObject = new PwPdhObject();

			pwInfo = getpwservice(cesInfo);

			if (type.equals("a")) {
				pwpdhObject.setName(pwInfo.getApwServiceId() + "");
				pwpdhObject.setIdentify(pwInfo.getApwServiceId() + "");
			} else {
				pwpdhObject.setName(pwInfo.getZpwServiceId() + "");
				pwpdhObject.setIdentify(pwInfo.getZpwServiceId() + "");
			}

			pwpdhObject.setCes(convertCesserviceObject(cesInfo, type));

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return pwpdhObject;
	}

	private PwInfo getpwservice(CesInfo cesInfo) throws Exception {
		PwInfoService_MB pwInfoService = null;
		PwInfo pwinfo = null;
		try {
			pwinfo = new PwInfo();
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwinfo.setPwId(cesInfo.getPwId());
			pwinfo = pwInfoService.selectBypwid_notjoin(pwinfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(pwInfoService);
		}

		return pwinfo;
	}

	/**
	 * 获得sdhpw端口对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private PwSdhObject convertPwSdhObject(CesInfo cesInfo, String type) throws Exception {
		if (null == cesInfo) {
			throw new Exception("cesInfo is null");
		}

		PwInfo pwInfo = null;
		PwSdhObject pwsdhobject = null;
		try {
			pwsdhobject = new PwSdhObject();
			pwInfo = getpwservice(cesInfo);

			if (type.equals("a")) {
				pwsdhobject.setName(pwInfo.getApwServiceId() + "");
				pwsdhobject.setIdentify(pwInfo.getApwServiceId() + "");
			} else {
				pwsdhobject.setName(pwInfo.getZpwServiceId() + "");
				pwsdhobject.setIdentify(pwInfo.getZpwServiceId() + "");
			}
			pwsdhobject.setCes(convertCesserviceObject(cesInfo, type));

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			pwInfo = null;
		}
		return pwsdhobject;
	}

	/**
	 * 获得pdhac对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private PdhAcObject convertPdhAcObject(CesInfo cesInfo, String type) throws Exception {
		if (null == cesInfo) {
			throw new Exception("cesInfo is null");
		}

		int portId = 0;
		PdhAcObject pdhObject = null;
		try {

			if (type.equals("a")) {
				portId = cesInfo.getaAcId();
			} else {
				portId = cesInfo.getzAcId();
			}

			pdhObject = new PdhAcObject();

			pdhObject.setName(super.getPortname(portId));
			pdhObject.setCesServiceObject(convertCesserviceObject(cesInfo, type));

			if("a".equals(type)){
				if(null!=cesInfo.getBeforeAPort()){
					pdhObject.setIdentify(super.getPortname(cesInfo.getBeforeAPort().getPortId()));
				}else{
					pdhObject.setIdentify(super.getPortname(cesInfo.getaAcId()));
				}
			}
			if("z".equals(type)){
				if(null!=cesInfo.getBeforeZPort()){
					pdhObject.setIdentify(super.getPortname(cesInfo.getBeforeZPort().getPortId()));
				}else{
					pdhObject.setIdentify(super.getPortname(cesInfo.getzAcId()));
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return pdhObject;
	}

	/**
	 * 获得sdhac对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private SdhAcObject convertSdhAcObject(int timeSlotId, CesInfo cesInfo, String type) throws Exception {

		PortStmTimeslot portStmTimeslot = null;
		PortStmTimeslotService_MB portStmTimeslotService = null;
		SdhAcObject sdhAcObject = null;
		try {

			sdhAcObject = new SdhAcObject();
			portStmTimeslotService = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);
			portStmTimeslot = portStmTimeslotService.selectById(timeSlotId);

			sdhAcObject.setName(portStmTimeslot.getTimeslotname());
			sdhAcObject.setCesServiceObject(convertCesserviceObject(cesInfo, type));
			if(null!=cesInfo.getBeforeAPortStmTime()&&"a".equals(type)){
				sdhAcObject.setIdentify(super.getPortname(cesInfo.getBeforeAPortStmTime().getPortid()));
			}
			if(null!=cesInfo.getBeforeZPortStmTime()&&"z".equals(type)){
				sdhAcObject.setIdentify(super.getPortname(cesInfo.getBeforeZPortStmTime().getPortid()));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portStmTimeslotService);
		}
		return sdhAcObject;
	}

	/**
	 * 获得ces对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private CesObject convertCesObject(CesInfo cesInfo, String type) throws Exception {
		if (null == cesInfo) {
			throw new Exception("cesInfo is null");
		}
		CesObject cesobject = null;
		try {
			cesobject = new CesObject();
			cesobject.setAdmin(cesInfo.getActiveStatus() == EActiveStatus.UNACTIVITY.getValue() ? "down" : "up");

			if (type.equals("a")) {
				cesobject.setName(cesInfo.getAxcId() + "");
			} else {
				cesobject.setName(cesInfo.getZxcId() + "");
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return cesobject;
	}

	/**
	 * 获得pdhces对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private PdhPortObject convertPdhPortObject(String portname,CesInfo cesInfo,String type) throws Exception {
		if (null == portname) {
			throw new Exception("cesInfo is null");
		}
		PdhPortObject pdhportobject = null;
		try {
			pdhportobject = new PdhPortObject();
			pdhportobject.setName(portname);
			
			if("a".equals(type)){
				if(null!=cesInfo.getBeforeAPort()){
					pdhportobject.setIdentify(super.getPortname(cesInfo.getBeforeAPort().getPortId()));
				}else{
					pdhportobject.setIdentify(super.getPortname(cesInfo.getaAcId()));
				}
			}
			if("z".equals(type)){
				if(null!=cesInfo.getBeforeZPort()&&"z".equals(type)){
					pdhportobject.setIdentify(super.getPortname(cesInfo.getBeforeZPort().getPortId()));
				}else{
					pdhportobject.setIdentify(super.getPortname(cesInfo.getzAcId()));
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return pdhportobject;
	}

	/**
	 * 获得sdh端口对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private SdhPortObject convertSdhPortObject(int portid,CesInfo cesInfo,String type) throws Exception {
		SdhPortObject sdhportobject = null;
		PortStmTimeslotService_MB portStmTimeslotService = null;
		PortStmTimeslot portStmTimeslot = null;
		try {
			portStmTimeslotService = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);
			portStmTimeslot = portStmTimeslotService.selectById(portid);

			sdhportobject = new SdhPortObject();
			sdhportobject.setName(super.getPortname(portStmTimeslot.getPortid()));

			if(null!=cesInfo.getBeforeAPortStmTime()&&"a".equals(type)){
				sdhportobject.setIdentify(super.getPortname(cesInfo.getBeforeAPortStmTime().getPortid()));
			}
			if(null!=cesInfo.getBeforeZPortStmTime()&&"z".equals(type)){
				sdhportobject.setIdentify(super.getPortname(cesInfo.getBeforeZPortStmTime().getPortid()));
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portStmTimeslotService);
			portStmTimeslot = null;
		}
		return sdhportobject;
	}

	/**
	 * 获得ces业务对象
	 * 
	 * @param pwInfo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private CesServiceObject convertCesserviceObject(CesInfo cesInfo, String type) throws Exception {
		if (null == cesInfo) {
			throw new Exception("cesInfo is null");
		}
		CesServiceObject cesServiceObject = null;
		try {
			cesServiceObject = new CesServiceObject();
			cesServiceObject.setType("ces");
			if (type.equals("a")) {
				cesServiceObject.setVpn(cesInfo.getAxcId() + "");
			} else {
				cesServiceObject.setVpn(cesInfo.getZxcId() + "");
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return cesServiceObject;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		CesInfoService_MB cesInfoService = null;
		try {
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_CESPDH);
			// this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_CESSDH);
			cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				cesInfoService.updateActiveStatus(siteId, EActiveStatus.UNACTIVITY.getValue());
				this.synchro_db(operationObject.getCxActionObjectList(), siteId);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(cesInfoService);
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

		actionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
		actionObject.setCxNeObject(super.getCXNEObject(siteId));
		actionObject.setType(type);
		actionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
		SiteUtil siteUtil=new SiteUtil();
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

		PortStmTimeslotService_MB portStmTimeslotService = null;
		PwInfoService_MB pwInfoService = null;
		PortService_MB portService = null;

		try {

			portStmTimeslotService = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);

			CesInfo cesInfo = null;
			List<PwInfo> pwInfoList=null;
			SynchroUtil SynchroUtil = new SynchroUtil();
			for (CXActionObject cxActionObject : cxActionObjectList) {
				List<CesObject> cesObjectList = cxActionObject.getCesObjectList();
				for (CesObject cesObject : cesObjectList) {
					cesInfo = new CesInfo();
					// 判断cesobject中的portList对象的acname属性， 如果是pdh/e1.1.1 格式，说明是pdh类型，给ces的type属性赋pdh acid 通过 siteid+e1.1.1去port_inst表中查询主键
					// 如果是stm1/10..... 格式，说明是sdh类型，给ces的type属性赋sdh。 acid 通过stm1去port_stm表查主键，在通过 sdh主键+10... 去port_stm_timeslot查询。得到主键
					// pw会返回sdhpw/1 sdhpw是pw的类型， 1为pw的serviceId 去pw表中通过 类型+siteid+serviceid 查询 pw主键
					PortList portList = cesObject.getPortList();
					String ac = portList.getAc();
					String pw = portList.getPw();
					String[] acValues = ac.split("/");
					String[] pwValues = pw.split("/");
					if ("pdh".equals(acValues[0])) {
						cesInfo.setCestype(ECesType.PDH.getValue());
						PortInst portInst = new PortInst();
						portInst.setSiteId(siteId);
						portInst.setPortName(acValues[1]);
						List<PortInst> portInstList = portService.select(portInst);
						if (null != portInstList && portInstList.size() > 0) {
							cesInfo.setaAcId(portInstList.get(0).getPortId());
						}
					} else {
						cesInfo.setCestype(ECesType.SDH.getValue());
						PortInst portInst = new PortInst();
						portInst.setSiteId(siteId);
						portInst.setPortName(acValues[0]);
						List<PortInst> portInstList = portService.select(portInst);
						if (null != portInstList && portInstList.size() > 0) {
							List<PortStmTimeslot> portStmTimeslotList = portStmTimeslotService.selectBySiteIdAndPortIdAndName(siteId, portInstList.get(0).getPortId(), acValues[1]);
							cesInfo.setaAcId(portStmTimeslotList.get(0).getId());
						}
					}
					int pwType = 0;
					if ("pwpdh".equals(pwValues[0])) {
						pwType = EPwType.PDH.getValue();
					} else if ("pwsdh".equals(pwValues[0])) {
						pwType = EPwType.SDH.getValue();
					} else if ("pweth".equals(pwValues[0])) {
						pwType = EPwType.ETH.getValue();
					}
					if(pwValues.length>1&&UiUtil.isNull(pwValues[1])){
						pwInfoList = pwInfoService.select_synchro(siteId, Integer.parseInt(pwValues[1]), pwType);
					}
					
					if (null != pwInfoList && pwInfoList.size() > 0) {
						cesInfo.setPwId(pwInfoList.get(0).getPwId());
					} else {
						ExceptionManage.logDebug("ces，未同步PW，同步失败",this.getClass());
						continue;
					}
					
					// if (TypeAndActionUtil.TYPE_CESPDH.equals(cxActionObject.getType())) {
					// cesInfo.setCestype(ECesType.PDH.getValue());
					// } else if (TypeAndActionUtil.TYPE_CESSDH.equals(cxActionObject.getType())) {
					// cesInfo.setCestype(ECesType.SDH.getValue());
					// }
					cesInfo.setAxcId(Integer.parseInt(cesObject.getName()));
					cesInfo.setName("ces_" + super.getNowMS());
					cesInfo.setaSiteId(siteId);
					cesInfo.setIsSingle(1);
					cesInfo.setServiceType(EServiceType.CES.getValue());
					cesInfo.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
					cesInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
					SynchroUtil.CesSynchro(cesInfo, siteId);
				}
			}
		} catch (Exception e) {
             throw e;
		}finally{
			UiUtil.closeService_MB(portStmTimeslotService);
			UiUtil.closeService_MB(pwInfoService);
			UiUtil.closeService_MB(portService);

		}
	}

	private void dataDownLoadPDH(CesInfo cesInfos,
			CXActionObject cxActionObject, String action, String type,int cestype) throws Exception {
		
		if(cesInfos.isDataDownLoad()&&action.equals(TypeAndActionUtil.ACTION_DELETE)){
			PwPdhObject pwpdhObject = new PwPdhObject();
			pwpdhObject.setName(cesInfos.getPwId()+"");
			cxActionObject.setPwPdhObject(pwpdhObject);
		}else{
			cxActionObject.setPwPdhObject(convertPwPdhObject(cesInfos, type));
		}
	}
	
	private void dataDownLoadSDH(CesInfo cesInfos,
			CXActionObject cxActionObject, String action, String type,int cestype) throws Exception {
		
		if(cesInfos.isDataDownLoad()&&action.equals(TypeAndActionUtil.ACTION_DELETE)){
			PwSdhObject pwSdhObject = new PwSdhObject();
			pwSdhObject.setName(cesInfos.getPwId()+"");
			cxActionObject.setPwSdhObject(pwSdhObject);
		
		}else{
			cxActionObject.setPwSdhObject(convertPwSdhObject(cesInfos, type));
		}
	}
}
