package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcObject;
import com.nms.drivechenxiao.service.bean.pweth.PwEthObject;
import com.nms.drivechenxiao.service.bean.service.elan.ELanObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
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
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class ELanCXServiceImpl extends CXOperationBase implements OperationServiceI {
	@SuppressWarnings({ "unchecked"})
	@Override
	public String excutionDelete(List objectList) throws Exception {
		List<ElanInfo> elaninfoList = null;
		OperationObject operationObject = null;
		String errorMessage = null;
		try {
			elaninfoList = objectList;
			operationObject = this.getOperationObject(elaninfoList, TypeAndActionUtil.ACTION_DELETE);
			if (operationObject.getCxActionObjectList().size() > 0) {
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
//					this.elaninfoservice.delete(elaninfoList.get(0).getServiceId());
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
			elaninfoList = null;
			operationObject = null;
		}
		return errorMessage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String excutionInsert(Object object) throws Exception {
		List<ElanInfo> elaninfoList = null;
		OperationObject operationObject = null;
		String errorMessage = null;
		try {
			elaninfoList = (List<ElanInfo>) object;
			operationObject = this.getOperationObject(elaninfoList, TypeAndActionUtil.ACTION_INSERT);
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
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			elaninfoList = null;
			operationObject = null;
		}
		return errorMessage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String excutionUpdate(Object object) throws Exception {
		List<ElanInfo> elaninfoList = null;
		PwInfo pwinfo = null;
		OperationObject operationObject = null;
		String result = null;
		String action=null;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		try {
			elaninfoList = (List<ElanInfo>) object;
			for (ElanInfo elaninfo : elaninfoList) {
				pwinfo = super.getPwinfo(elaninfo.getPwId());

				if (elaninfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue() && pwinfo.getPwStatus() == EActiveStatus.NONE.getValue()) {
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_ACTIVITY);
				}
				if (pwinfo.getRelatedServiceId() != 0) {
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_PW_USE);
				}
			}
			if(elaninfoList.size()>0){
				//如果是激活直接操作
				if(elaninfoList.get(0).getActiveStatus() == EActiveStatus.ACTIVITY.getValue()){
					if(elaninfoList.get(0).getBeforeActiveStatus() == EActiveStatus.UNACTIVITY.getValue()){
						action=TypeAndActionUtil.ACTION_INSERT;
					}else{
						action=TypeAndActionUtil.ACTION_UPDATE;
					}
				}else{ 
					if(elaninfoList.get(0).getBeforeActiveStatus() == EActiveStatus.ACTIVITY.getValue()){
						//未激活还原数据  进行删除操作
						for(ElanInfo elaninfo:elaninfoList){
							if(null!=elaninfo.getBeforeAAcList()){
								map.put(elaninfo.getBeforeAAcList().get(0).getId(), elaninfo.getaAcId());
								elaninfo.setaAcId(elaninfo.getBeforeAAcList().get(0).getId());
								
							}
							if(null != elaninfo.getBeforeZAcList()&& elaninfo.getBeforeZAcList().size()>0 ){
								map.put(elaninfo.getBeforeZAcList().get(0).getId(), elaninfo.getzAcId());
								elaninfo.setzAcId(elaninfo.getBeforeZAcList().get(0).getId());
							}
							action=TypeAndActionUtil.ACTION_DELETE;
						}
					}else{
						return result = ResultString.CONFIG_SUCCESS;
					}
				}
				operationObject = this.getOperationObject(elaninfoList, action);
				if (operationObject.getCxActionObjectList().size() > 0) {
					super.sendAction(operationObject);
					operationObject = super.verification(operationObject);
					if (operationObject.isSuccess()) {
						result = operationObject.getCxActionObjectList().get(0).getStatus();
					} else {
						result = super.getErrorMessage(operationObject);
					}
				} else {
					result = ResultString.CONFIG_SUCCESS;
				}
				
			}
		for(ElanInfo obj :elaninfoList){
			if(null!=map.get(obj.getaAcId())){
				obj.setaAcId(map.get(obj.getaAcId()));
			}
			if(null!=map.get(obj.getzAcId())){
				obj.setzAcId(map.get(obj.getzAcId()));
			}
		}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			elaninfoList = null;
			pwinfo = null;
			operationObject = null;
			action=null;
		}
		return result;
	}

	OperationObject operationObject = null;

	private OperationObject getOperationObject(List<ElanInfo> elaninfoList, String action) throws Exception {
		CXActionObject cxActionObject = null;
		Map<Integer, CXActionObject> map = null;

		try {
			operationObject = new OperationObject();
			map = new HashMap<Integer, CXActionObject>();
			SiteUtil siteUtil=new SiteUtil();
			for (ElanInfo elanInfo : elaninfoList) {
				if (elanInfo.getaSiteId() > 0&&0==siteUtil.SiteTypeUtil(elanInfo.getaSiteId())) {
					if (super.getManufacturer(elanInfo.getaSiteId()) == EManufacturer.CHENXIAO.getValue()) {
						if (null == map.get(elanInfo.getaSiteId())) {
							cxActionObject = new CXActionObject();
							cxActionObject.setCxNeObject(super.getCXNEObject(elanInfo.getaSiteId()));
							cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
							cxActionObject.setAction(action);
							cxActionObject.setType(TypeAndActionUtil.TYPE_ELAN);
							cxActionObject.setElanObject(this.convertELanObject(elanInfo, "a"));
							//判断是否为离线网元数据下载并且操作
							offLineSiteAcion(elanInfo, cxActionObject,action,"a");
						/*	cxActionObject.setEthPortObject(this.convertEthPortObject(elanInfo, "a"));
							cxActionObject.getPwEthObjectList().add(this.convertPwEthObject(elanInfo, "a"));*/
							map.put(elanInfo.getaSiteId(), cxActionObject);
						} else {
							offLineSitePwAction(elanInfo, cxActionObject,map,action,"a");
							if(null!=elanInfo.getBeforeAAcList()){
								map.get(elanInfo.getaSiteId()).setAcObject(this.convertAcObject(elanInfo, "a"));
							}
							/*map.get(elanInfo.getaSiteId()).getPwEthObjectList().add(this.convertPwEthObject(elanInfo, "a"));*/
						}
					}
				}
				if (elanInfo.getzSiteId() > 0&&0==siteUtil.SiteTypeUtil(elanInfo.getzSiteId())) {
					if (super.getManufacturer(elanInfo.getzSiteId()) == EManufacturer.CHENXIAO.getValue()) {
						if (null == map.get(elanInfo.getzSiteId())) {
							cxActionObject = new CXActionObject();
							cxActionObject.setCxNeObject(super.getCXNEObject(elanInfo.getzSiteId()));
							cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
							cxActionObject.setAction(action);
							cxActionObject.setType(TypeAndActionUtil.TYPE_ELAN);
							cxActionObject.setElanObject(this.convertELanObject(elanInfo, "z"));
							//判断是否为离线网元数据下载并且操作
							offLineSiteAcion(elanInfo, cxActionObject,action,"z");
							/*cxActionObject.setEthPortObject(this.convertEthPortObject(elanInfo, "z"));
							cxActionObject.getPwEthObjectList().add(this.convertPwEthObject(elanInfo, "z"));*/
							map.put(elanInfo.getzSiteId(), cxActionObject);
						} else {
							offLineSitePwAction(elanInfo, cxActionObject,map,action,"z");
							/*map.get(elanInfo.getzSiteId()).getPwEthObjectList().add(this.convertPwEthObject(elanInfo, "z"));*/
							if(null!=elanInfo.getBeforeZAcList()){
								map.get(elanInfo.getzSiteId()).setAcObject(this.convertAcObject(elanInfo, "z"));
							}
						}
					}
				}
			}

			for (Map.Entry<Integer, CXActionObject> entrySet : map.entrySet()) {
				operationObject.getCxActionObjectList().add(entrySet.getValue());
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			cxActionObject = null;
			map = null;
		}
		return operationObject;
	}

	/**
	 * 获取AcObject
	 * 
	 * @return
	 * @throws Exception
	 */
	private AcObject convertAcObject(ElanInfo elanInfo, String type) throws Exception {

		AcPortInfo acPortInfo = null;
		AcPortInfoService_MB acInfoService = null;
		List<AcPortInfo> acportInfpList = null;
		AcObject acObject = null;
		AcPortInfo acBefore;
		try {

			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acPortInfo = new AcPortInfo();

			acObject = new AcObject();

			if (type == "a") {
				acPortInfo.setId(elanInfo.getaAcId());
				acObject.getElan().setVpn(elanInfo.getAxcId() + "");
				acportInfpList = acInfoService.queryByAcPortInfo(acPortInfo);
				String name = acportInfpList.get(0).getAcBusinessId() + "";
				acObject.setName(name);
				acObject.getElan().setType(TypeAndActionUtil.TYPE_ELAN);
				acObject.setIdentify(name);
				acBefore = elanInfo.getBeforeAAcList().get(0);
			} else {
				acPortInfo.setId(elanInfo.getzAcId());
				acObject.getElan().setVpn(elanInfo.getZxcId() + "");
				acportInfpList = acInfoService.queryByAcPortInfo(acPortInfo);
				String name = acportInfpList.get(0).getAcBusinessId() + "";
				acObject.setName(name);
				acObject.getElan().setType(TypeAndActionUtil.TYPE_ELAN);
				acObject.setIdentify(name);
				acBefore = elanInfo.getBeforeZAcList().get(0);
			}
			//修改前AC赋值
			if(null!=acBefore){
				acObject.setIdentify(acBefore.getAcBusinessId()+"");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}
		return acObject;
	}

	/**
	 * 获取ELineObject对象
	 * 
	 * @param elineInfo
	 *            elineinfo对象 数据库实体bean
	 * @return
	 * @throws Exception
	 */
	private ELanObject convertELanObject(ElanInfo elanInfo, String type) throws Exception {
		ELanObject elanobject = null;
		try {
			elanobject = new ELanObject();

			if (type == "a") {
				elanobject.setName(elanInfo.getAxcId() + "");
			} else {
				elanobject.setName(elanInfo.getZxcId() + "");
			}

			if (elanInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue()) {
				elanobject.setAdmin("up");
			} else {
				elanobject.setAdmin("down");
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return elanobject;
	}

	/**
	 * 获得pw对象
	 * 
	 * @param etreeinfoList
	 * @param type
	 * @return
	 * @throws Exception
	 */
	private PwEthObject convertPwEthObject(ElanInfo elaninfo, String type) throws Exception {
		PwInfo pwinfo = null;
		PwEthObject pwEthObject = null;

		try {

			pwEthObject = new PwEthObject();
			pwinfo = super.getPwinfo(elaninfo.getPwId());

			if (type.equals("a")) {
				if (elaninfo.getaSiteId() == pwinfo.getASiteId()) {
					pwEthObject.setName(pwinfo.getApwServiceId() + "");
					pwEthObject.getElan().setType(TypeAndActionUtil.TYPE_ELAN);
					pwEthObject.getElan().setVpn(elaninfo.getAxcId() + "");
					pwEthObject.setIdentify(pwinfo.getApwServiceId() + "");
				}

				if (elaninfo.getaSiteId() == pwinfo.getZSiteId()) {
					pwEthObject.setName(pwinfo.getZpwServiceId() + "");
					pwEthObject.getElan().setType(TypeAndActionUtil.TYPE_ELAN);
					pwEthObject.getElan().setVpn(elaninfo.getAxcId() + "");
					pwEthObject.setIdentify(pwinfo.getZpwServiceId() + "");
				}
			}

			if (type.equals("z")) {
				if (elaninfo.getzSiteId() == pwinfo.getASiteId()) {
					pwEthObject.setName(pwinfo.getApwServiceId() + "");
					pwEthObject.getElan().setType(TypeAndActionUtil.TYPE_ELAN);
					pwEthObject.getElan().setVpn(elaninfo.getZxcId() + "");
					pwEthObject.setIdentify(pwinfo.getApwServiceId() + "");
				}

				if (elaninfo.getzSiteId() == pwinfo.getZSiteId()) {
					pwEthObject.setName(pwinfo.getZpwServiceId() + "");
					pwEthObject.getElan().setType(TypeAndActionUtil.TYPE_ELAN);
					pwEthObject.getElan().setVpn(elaninfo.getZxcId() + "");
					pwEthObject.setIdentify(pwinfo.getZpwServiceId() + "");
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			pwinfo = null;
		}
		return pwEthObject;
	}

	private EthPortObject convertEthPortObject(ElanInfo elanInfo, String type) throws Exception {
		int acId = 0;
		AcPortInfoService_MB acInfoService = null;
		List<AcPortInfo> acportInfpList = null;
		AcPortInfo acPortInfo = null;
		EthPortObject ethportobject = null;
		AcPortInfo acBefore = null;
		try {
			ethportobject = new EthPortObject();

			if (type == "a") {
				acId = elanInfo.getaAcId();
				acBefore = elanInfo.getBeforeAAcList().get(0);
			} else {
				acId = elanInfo.getzAcId();
				acBefore = elanInfo.getBeforeZAcList().get(0);
			}

			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acPortInfo = new AcPortInfo();
			acPortInfo.setId(acId);
			acportInfpList = acInfoService.queryByAcPortInfo(acPortInfo);

			acPortInfo = acportInfpList.get(0);
			if(acPortInfo.getPortId()>0){
				ethportobject.setName(super.getPortname(acPortInfo.getPortId()));
				ethportobject.setPortType("eth");
				ethportobject.setIdentify(super.getPortname(acPortInfo.getPortId()));
				ethportobject.setPrevious("eth");
			}else{
				ethportobject.setName(super.getLagName(acPortInfo.getLagId()));
				ethportobject.setPortType("lag");
				ethportobject.setIdentify(super.getLagName(acPortInfo.getLagId()));
				ethportobject.setPrevious("lag");
			}

			//修改前端口赋值
			if(null != acBefore&&0!=acBefore.getAcBusinessId()){
				if(acBefore.getPortId()>0){
					ethportobject.setIdentify(super.getPortname(acBefore.getPortId()));
					ethportobject.setPrevious("eth");
				}else{
					ethportobject.setIdentify(super.getLagName(acBefore.getLagId()));
					ethportobject.setPrevious("lag");
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(acInfoService);
		}

		return ethportobject;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		ElanInfoService_MB elaninfoservice = null;
		try {
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_ELAN);
			elaninfoservice = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				elaninfoservice.updateStatusActivate(siteId, EActiveStatus.UNACTIVITY.getValue());
				this.synchro_db(operationObject.getCxActionObjectList().get(0).getElanObjectList(), siteId);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(elaninfoservice);
		}

		return null;
	}

	/**
	 * 获取OperationObject对象 查询用
	 * 
	 * @author wangwf
	 * 
	 * @param operationObject
	 *            OperationObject对象
	 * @param siteId
	 *            网元id
	 * @param type
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
		operationObject.getCxActionObjectList().add(actionObject);

	}

	/**
	 * 与数据库同步
	 * 
	 * @author wangwf
	 * 
	 * @param eLanObjectList
	 * @param siteId 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void synchro_db(List<ELanObject> eLanObjectList, int siteId) throws Exception {

		if (null == eLanObjectList) {
			throw new Exception("eLanObjectList is null");
		}
		List<ElanInfo> elanInfoList = new ArrayList<ElanInfo>();
		PwInfo pwInfo = null;
		AcPortInfo acPortInfo = null;
		ElanInfoService_MB elanInfoService = null;
		try {
			for (ELanObject eLanObject : eLanObjectList) {
				if (eLanObject.getPortList().getPwList().size() == 0) {
					ExceptionManage.logDebug("设备查询elan，没有查询到pw",this.getClass());
					continue;
				}
				
				ElanInfo elanInfo = null;
				
				if (eLanObject.getPortList().getPwList().size() > 0) {
					String elanName = "elan_" + super.getNowMS();
					for (String pwname : eLanObject.getPortList().getPwList()) {
						elanInfo = new ElanInfo();
						// 转换AC对象
						acPortInfo = super.getAcPortInfo(eLanObject.getPortList().getAc(), siteId);
						if (null == acPortInfo) {
							ExceptionManage.logDebug("同步elan时，未同步AC，同步失败",this.getClass());
							continue;
						}
						elanInfo.setName(elanName);
						elanInfo.setServiceType(EServiceType.ELAN.getValue());
						elanInfo.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
						elanInfo.setIsSingle(1);
						elanInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
						elanInfo.setaAcId(acPortInfo.getId());
						elanInfo.setaSiteId(siteId);
						elanInfo.setAxcId(Integer.parseInt(eLanObject.getName()));
						// 转换pw对象
						pwInfo = this.getPwInfo(pwname, siteId);
						if (null == pwInfo) {
							ExceptionManage.logDebug("同步elan时，未同步PW，同步失败",this.getClass());
							continue;
						}
						elanInfo.setPwId(pwInfo.getPwId());
						elanInfoList.add(elanInfo);
					}
				}
			}
			if (elanInfoList.size() > 0) {
				elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
				SynchroUtil synchroUtil = new SynchroUtil();
				synchroUtil.elanSynchro(elanInfoService,elanInfoList, siteId);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			UiUtil.closeService_MB(elanInfoService);
		}
	}

	/**
	 * 离线网元转换
	 * @param etreeinfo
	 * @param rootcxActionObject
	 * @throws Exception
	 */
	private void offLineSiteAcion(ElanInfo elanInfo,CXActionObject cxActionObject,String action,String type) throws Exception {
		
		PortService_MB portService  = null;
		
		try {
			if(elanInfo.isDataDownLoad()&&action.equals(TypeAndActionUtil.ACTION_DELETE)){
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				EthPortObject ethportobject = new EthPortObject();
				if("a".equals(type)&&0!=elanInfo.getaAcBusinessId()){
					AcObject acObject = new AcObject();
					acObject.setName(elanInfo.getaAcBusinessId()+"");
					cxActionObject.setAcObject(acObject);
				}
				if(0!=elanInfo.getPwBusinessId()){
					PwEthObject pwEthObject = new PwEthObject();
					pwEthObject.setName(elanInfo.getPwBusinessId()+"");
					cxActionObject.getPwEthObjectList().add(pwEthObject);
				}
				if(0!=elanInfo.getAportId()){
					PortInst portInst = new PortInst(); 
					portInst.setPortId(elanInfo.getAportId());
					portInst =  portService.select(portInst).get(0);
					ethportobject.setName(portInst.getPortName());
					ethportobject.setPortType("eth");
					cxActionObject.setEthPortObject(ethportobject);
				}else{
					ethportobject.setPortType("lag");
					ethportobject.setName(elanInfo.getaAcId()+"");
				}
			}else{
				cxActionObject.setAcObject(this.convertAcObject(elanInfo, type));
				cxActionObject.setEthPortObject(this.convertEthPortObject(elanInfo, type));
				cxActionObject.getPwEthObjectList().add(this.convertPwEthObject(elanInfo, type));
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
	}
	
	/**
	 * 离线网元pw对象转换
	 * @param etreeinfo
	 * @param rootcxActionObject
	 * @throws Exception
	 */
	private void offLineSitePwAction(ElanInfo elanInfo,
			CXActionObject rootcxActionObject,Map<Integer, CXActionObject> map ,String action,String type) throws Exception {
		if(elanInfo.isDataDownLoad()&&action.equals(TypeAndActionUtil.ACTION_DELETE)){
			if(0!=elanInfo.getPwId()){
				PwEthObject pwEthObject = new PwEthObject();
				pwEthObject.setName(elanInfo.getPwId()+"");
				if("a".equals(type)){
					map.get(elanInfo.getaSiteId()).getPwEthObjectList().add(pwEthObject);
				}else{
					map.get(elanInfo.getzSiteId()).getPwEthObjectList().add(pwEthObject);
				}
				
			}
		}else{
			if("a".equals(type)){
				map.get(elanInfo.getaSiteId()).getPwEthObjectList().add(this.convertPwEthObject(elanInfo, type));
			}else{
				map.get(elanInfo.getzSiteId()).getPwEthObjectList().add(this.convertPwEthObject(elanInfo, type));
			}
			
		}
	}
	

	/**
	 * 验证业务是否做修改  没修改返回true，修改了返回false
	 * @param etreeinfoList
	 * @return
	 */
	private boolean checkActiveStatus(List<ElanInfo> ElanInfoList) {
		Boolean flag = true;
		//判断是修改 还是激活去激活
		for(ElanInfo elanInfo:ElanInfoList){
			if(0!=elanInfo.getAction()){
				flag = false;
			}
		}
		return flag;
	}
	
}
