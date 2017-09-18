package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcObject;
import com.nms.drivechenxiao.service.bean.pweth.PwEthObject;
import com.nms.drivechenxiao.service.bean.service.eline.ELineObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
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
import com.nms.ui.manager.UiUtil;

public class ElineCXServiceImpl extends CXOperationBase implements OperationServiceI {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String excutionDelete(List objectList) throws Exception {

		List<ElineInfo> elineInfoList = null;
		OperationObject operationObject = null;
		String errorMessage = null;
		try {
			elineInfoList = objectList;
			operationObject = this.getOperationObject(elineInfoList, TypeAndActionUtil.ACTION_DELETE);
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
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			elineInfoList = null;
			operationObject = null;
		}
		return errorMessage;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {

		ElineInfo elineInfo = null;
		OperationObject operationObject = null;
		String errorMessage = null;
		try {
			elineInfo = (ElineInfo) object;

			operationObject = this.getOperationObject(elineInfo, TypeAndActionUtil.ACTION_INSERT);
			// 如果有晨晓的配置 下发设备。 否则返回提示
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
			elineInfo = null;
			operationObject = null;
		}
		return errorMessage;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {

		ElineInfo elineInfo = null;
		OperationObject operationObject = null;
		String result = null;
		String action=null;	//下发类型。 新建或删除
		List<Integer> list = new ArrayList<Integer>();
		int aAcId = 0;
		int zAcId = 0;
		try {
			elineInfo = (ElineInfo) object;
			list.add(elineInfo.getPwId());
			
			//如果是激活直接操作
			if(elineInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue()){
				if(elineInfo.getBeforeActiveStatus() == EActiveStatus.UNACTIVITY.getValue()){
					action=TypeAndActionUtil.ACTION_INSERT;
				}else{
					action=TypeAndActionUtil.ACTION_UPDATE;
				}
			}else{ 
				if(elineInfo.getBeforeActiveStatus() == EActiveStatus.ACTIVITY.getValue()){
					//未激活还原数据  进行删除操作
					if(null!=elineInfo.getBeforeAAcList()){
						aAcId = elineInfo.getaAcId();
						elineInfo.setaAcId(elineInfo.getBeforeAAcList().get(0).getId());
					}
					if(null!=elineInfo.getBeforeZAcList()){
						zAcId = elineInfo.getzAcId();
						elineInfo.setzAcId(elineInfo.getBeforeZAcList().get(0).getId());
					}
					action=TypeAndActionUtil.ACTION_DELETE;
				}else{
					return result = ResultString.CONFIG_SUCCESS;
				}
			}
			operationObject = this.getOperationObject(elineInfo, action);
			if (operationObject.getCxActionObjectList().size() > 0) {
				super.sendAction(operationObject);
				super.verification(operationObject);
				if (operationObject.isSuccess()) {
	//				this.elineService.update(elineInfo);
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					result = super.getErrorMessage(operationObject);
				}
			}else {
				result = ResultString.CONFIG_SUCCESS;
			}
			if(0!=aAcId){
				elineInfo.setaAcId(aAcId);
			}
			if(0!=zAcId){
				elineInfo.setzAcId(zAcId);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
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
	private OperationObject getOperationObject(ElineInfo elineInfo, String action) throws Exception {
		OperationObject operationObject = null;
		String type = null;
		int siteId=0;
		try {
			operationObject = new OperationObject();
			SiteUtil siteUtil=new SiteUtil();
			if (elineInfo.getIsSingle() == 0) {
				if (super.getManufacturer(elineInfo.getaSiteId()) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(elineInfo.getaSiteId())) {
					operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, elineInfo, action, "a"));
				}
				if (super.getManufacturer(elineInfo.getaSiteId()) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(elineInfo.getzSiteId())) {
					operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, elineInfo, action, "z"));
				}
			} else {
				if (elineInfo.getaSiteId() != 0) {
					type = "a";
					siteId=elineInfo.getaSiteId();
				} else {
					type = "z";
					siteId=elineInfo.getzSiteId();
				}
				if (super.getManufacturer(siteId) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(siteId)) {
					operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, elineInfo, action, type));
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}

	private OperationObject getOperationObject(List<ElineInfo> elineInfoList, String action) throws Exception {
		OperationObject operationObject = null;
		String type = null;
		int siteid=0;
		try {
			SiteUtil siteUtil=new SiteUtil();
			operationObject = new OperationObject();
			for (ElineInfo elineInfo : elineInfoList) {
				if (elineInfo.getIsSingle() == 0) {
					if (super.getManufacturer(elineInfo.getaSiteId()) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(elineInfo.getaSiteId())) {
						operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, elineInfo, action, "a"));
					}
					if (super.getManufacturer(elineInfo.getaSiteId()) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(elineInfo.getzSiteId())) {
						operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, elineInfo, action, "z"));
					}

				} else {
					if (elineInfo.getaSiteId() != 0) {
						type = "a";
						siteid=elineInfo.getaSiteId();
					} else {
						type = "z";
						siteid=elineInfo.getzSiteId();
					}
					if (super.getManufacturer(siteid) == EManufacturer.CHENXIAO.getValue()&&0==siteUtil.SiteTypeUtil(siteid)) {
						operationObject.getCxActionObjectList().add(this.getCXActionObject(operationObject, elineInfo, action, type));
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}

	/**
	 * 获取CXActionObject对象
	 * 
	 * @param operationObject
	 *            operationObject对象
	 * @param elineInfo
	 *            elineinfo对象 数据库实体bean
	 * @param action
	 *            动作
	 * @param type
	 *            A或者Z
	 * @return
	 * @throws Exception
	 */
	private CXActionObject getCXActionObject(OperationObject operationObject, ElineInfo elineInfo, String action, String type) throws Exception {
		CXActionObject cxActionObject = null;
		int siteId = 0;
		try {
			if (type.equals("a")) {
				siteId = elineInfo.getaSiteId();
			} else {
				siteId = elineInfo.getzSiteId();
			}

			cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setAction(action);
			cxActionObject.setCxNeObject(super.getCXNEObject(siteId));
			cxActionObject.setType(TypeAndActionUtil.TYPE_ELINE);
			cxActionObject.setElineObject(this.convertElineObject(elineInfo, type));
			//数据转换
			offLineSiteAcion( elineInfo,cxActionObject, action,type);
			/*cxActionObject.setPwEthObject(this.convertPwEthObject(elineInfo, type));
			cxActionObject.setAcObject(this.convertAcObject(elineInfo, type, action));
			cxActionObject.setEthPortObject(this.convertEthPortObject(elineInfo, type));*/
			cxActionObject.setOamObject(super.convertOamObject_mep(EServiceType.ELINE.toString(),elineInfo.getId(),siteId,elineInfo.getOamList(),action));

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return cxActionObject;
	}

	/**
	 * 获取ELineObject对象
	 * 
	 * @param elineInfo
	 *            elineinfo对象 数据库实体bean
	 * @return
	 * @throws Exception
	 */
	private ELineObject convertElineObject(ElineInfo elineInfo, String type) throws Exception {

		String elinename = "";
		ELineObject eLineObject = null;
		try {
			eLineObject = new ELineObject();
			if (type.equals("a")) {
				elinename = elineInfo.getaXcId() + "";
			} else {
				elinename = elineInfo.getzXcId() + "";
			}
			eLineObject.setName(elinename);
			eLineObject.setAdmin(elineInfo.getActiveStatus() == EActiveStatus.UNACTIVITY.getValue() ? "down" : "up");

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return eLineObject;
	}

	/**
	 * 获取AcObject
	 * 
	 * @return
	 * @throws Exception
	 */
	private AcObject convertAcObject(ElineInfo elineInfo, String type, String action) throws Exception {

		int acInfoId = 0;
		AcPortInfo acPortInfo = null;
		AcPortInfoService_MB acInfoService = null;
		List<AcPortInfo> acportInfpList = null;
		String elinename = null;
		int siteId = 0;
		AcObject acObject = null;
		try {

			if (type.equals("a")) {
				acInfoId = elineInfo.getaAcId();
				elinename = elineInfo.getaXcId() + "";
				siteId = elineInfo.getaSiteId();
			} else {
				acInfoId = elineInfo.getzAcId();
				elinename = elineInfo.getzXcId() + "";
				siteId = elineInfo.getzSiteId();
			}

			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acPortInfo = new AcPortInfo();
			acPortInfo.setId(acInfoId);
			acportInfpList = acInfoService.queryByAcPortInfo(acPortInfo);

			if (acportInfpList == null || acportInfpList.size() != 1) {
				throw new Exception("查询acPortInfo出错");
			}

			acPortInfo = acportInfpList.get(0);

			acObject = new AcObject();
			acObject.setName(acPortInfo.getAcBusinessId() + "");
			acObject.getEline().setType(TypeAndActionUtil.TYPE_ELINE);
			acObject.getEline().setVpn(elinename);
			acObject.setOam(super.convertOamObject_mep("ELINE", elineInfo.getId(), siteId, elineInfo.getOamList(), action));
			
			if(null!=elineInfo.getBeforeAAcList()&&"a".equals(type)){
				acObject.setIdentify(elineInfo.getBeforeAAcList().get(0).getAcBusinessId()+"");
			}
			if(null!=elineInfo.getBeforeZAcList()&&"z".equals(type)){
				acObject.setIdentify(elineInfo.getBeforeZAcList().get(0).getAcBusinessId()+"");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}
		return acObject;
	}

	private PwEthObject convertPwEthObject(ElineInfo elineInfo, String type) throws Exception {
		PwInfo pwinfo = null;
		String pwObject_name = null;
		String elinename = null;
		PwEthObject pwEthObject = null;
		try {
			pwinfo = super.getPwinfo(elineInfo.getPwId());
			if (type.equals("a")) {
				if(elineInfo.getaSiteId() == pwinfo.getASiteId()){
					pwObject_name = pwinfo.getApwServiceId() + "";
					elinename = elineInfo.getaXcId() + "";
				}else{
					pwObject_name = pwinfo.getZpwServiceId() + "";
					elinename = elineInfo.getzXcId() + "";
				}
			} else {
				if(elineInfo.getzSiteId() == pwinfo.getZSiteId()){
					pwObject_name = pwinfo.getZpwServiceId() + "";
					elinename = elineInfo.getzXcId() + "";
				}else{
					pwObject_name = pwinfo.getApwServiceId() + "";
					elinename = elineInfo.getaXcId() + "";
				}
			}
			pwEthObject = new PwEthObject();
			pwEthObject.setName(pwObject_name);
			pwEthObject.getEline().setType(TypeAndActionUtil.TYPE_ELINE);
			pwEthObject.getEline().setVpn(elinename);
			pwEthObject.setIdentify(pwObject_name);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			pwinfo = null;
			pwObject_name = null;
		}
		return pwEthObject;
	}

	private EthPortObject convertEthPortObject(ElineInfo elineInfo, String type) throws Exception {
		AcPortInfoService_MB acInfoService = null;
		List<AcPortInfo> acportInfpList = null;
		int acInfoId = 0;
		AcPortInfo acPortInfo = null;
		EthPortObject ethPortObject = null;
		AcPortInfo acBefore = null;
		try {

			if (type.equals("a")) {
				acInfoId = elineInfo.getaAcId();
				acBefore = elineInfo.getBeforeAAcList().get(0);
			} else {
				acInfoId = elineInfo.getzAcId();
				acBefore = elineInfo.getBeforeZAcList().get(0);
			}

			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acPortInfo = new AcPortInfo();
			acPortInfo.setId(acInfoId);
			acportInfpList = acInfoService.queryByAcPortInfo(acPortInfo);

			if (acportInfpList == null || acportInfpList.size() != 1) {
				throw new Exception("查询acPortInfo出错");
			}

			acPortInfo = acportInfpList.get(0);

			ethPortObject = new EthPortObject();
			if (acPortInfo.getPortId() > 0) {
				ethPortObject.setName(super.getPortname(acPortInfo.getPortId()));
				ethPortObject.setPortType("eth");
			} else {
				ethPortObject.setName(super.getLagName(acPortInfo.getLagId()));
				ethPortObject.setPortType("lag");
			}
			//修改前端口赋值
			if(1==elineInfo.getAction()&&null != acBefore&&0!=acBefore.getAcBusinessId()){
				if(acBefore.getPortId()>0){
					ethPortObject.setIdentify(super.getPortname(acBefore.getPortId()));
					ethPortObject.setPrevious("eth");
				}else{
					ethPortObject.setIdentify(super.getLagName(acBefore.getLagId()));
					ethPortObject.setPrevious("lag");
				}
				
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}
		return ethPortObject;
	}

	@SuppressWarnings("unused")
	private List<ElineInfo> getElines(ElineInfo elineInfo) {
		List<ElineInfo> elineInfos = null;
		elineInfos = new ArrayList<ElineInfo>();
		elineInfos.add(elineInfo);
		return elineInfos;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		OperationObject operationObject = new OperationObject();
		ElineInfoService_MB elineService = null;
		try {
			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			this.getOperactionObject_select(operationObject, siteId, TypeAndActionUtil.TYPE_ELINE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				elineService.updateActiveStatusByType(siteId, EActiveStatus.UNACTIVITY.getValue(),EServiceType.ELINE.getValue());
				this.synchro_db(operationObject.getCxActionObjectList().get(0).getElineObjectList(), siteId);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(elineService);
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
		operationObject.getCxActionObjectList().add(actionObject);

	}

	/**
	 * 与数据库做同步
	 * 
	 * @author wangwf
	 * 
	 * @param elineObjectList
	 * @param siteId
	 * @throws Exception
	 * @Exception 异常对象
	 */
	private void synchro_db(List<ELineObject> elineObjectList, int siteId) throws Exception {

		if (null == elineObjectList) {
			throw new Exception("elineObjectList is null");
		}

		ElineInfo elineInfo = null;
		PwInfo pwInfo = null;
		AcPortInfo acPortInfo = null;
		SynchroUtil synchroUtil = new SynchroUtil();
		for (ELineObject eLineObject : elineObjectList) {
			elineInfo = new ElineInfo();
			elineInfo.setaXcId(Integer.parseInt(eLineObject.getName()));
			elineInfo.setName("eline_" + super.getNowMS());
			elineInfo.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
			elineInfo.setIsSingle(1);
			elineInfo.setServiceType(EServiceType.ELINE.getValue());
			elineInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
//			elineInfo.setCreateUser(ConstantUtil.user.getUser_Name());
			elineInfo.setaSiteId(siteId);
			elineInfo.setJobStatus(eLineObject.getOper());

			//转换pw对象
			if ("".equals(eLineObject.getPortList().getPw())) {
				ExceptionManage.logDebug("同步eline时，未同步PW，同步失败",this.getClass());
				continue;
			}
			pwInfo = this.getPwInfo(eLineObject.getPortList().getPw(), siteId);
			if (null == pwInfo) {
				ExceptionManage.logDebug("同步eline时，未同步PW，同步失败",this.getClass());
				continue;
			}
			elineInfo.setPwId(pwInfo.getPwId());

			//转换AC对象
			acPortInfo = super.getAcPortInfo(eLineObject.getPortList().getAc(), siteId);
			if (null == acPortInfo) {
				ExceptionManage.logDebug("同步eline时，未同步AC，同步失败",this.getClass());
				continue;
			}
			elineInfo.setaAcId(acPortInfo.getId());

			synchroUtil.elineSynchro(elineInfo, siteId);

			AcCXServiceImpl acCXServiceImpl = new AcCXServiceImpl();
			acCXServiceImpl.synchro_one(eLineObject,siteId);
		}
	}

	/**
	 * 离线网元转换
	 * @param etreeinfo
	 * @param rootcxActionObject
	 * @throws Exception
	 */
	private void offLineSiteAcion(ElineInfo elineInfo,CXActionObject cxActionObject,String action,String type) throws Exception {
		//离线网元操作
		PortService_MB portService = null;
		try {
			if(elineInfo.isDataDownLoad()&&action.equals(TypeAndActionUtil.ACTION_DELETE)){
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				EthPortObject ethportobject = new EthPortObject();
				if(0!=elineInfo.getPwId()){
					PwEthObject pwEthObject = new PwEthObject();
					pwEthObject.setName(elineInfo.getPwId()+"");
					cxActionObject.setPwEthObject(pwEthObject);
				}
				if(0!=elineInfo.getAportId()){
					PortInst portInst = new PortInst(); 
					portInst.setPortId(elineInfo.getAportId());
					portInst =  portService.select(portInst).get(0);
					ethportobject.setName(portInst.getPortName());
					ethportobject.setPortType("eth");
					cxActionObject.setEthPortObject(ethportobject);
				}else{
					ethportobject.setName(elineInfo.getaAcId()+"");
					ethportobject.setPortType("lag");
					cxActionObject.setEthPortObject(ethportobject);
				}
				if(0!=elineInfo.getaAcId()){
					
					AcObject acObject = new AcObject();
					acObject.setName(elineInfo.getaAcId()+"");
					acObject.getEline().setType(TypeAndActionUtil.TYPE_ELINE);
					acObject.getEline().setVpn(elineInfo.getaXcId()+"");
					cxActionObject.setAcObject(acObject);
				}
			}else{//非离线网元操作
				cxActionObject.setPwEthObject(this.convertPwEthObject(elineInfo, type));
				cxActionObject.setAcObject(this.convertAcObject(elineInfo, type, action));
				cxActionObject.setEthPortObject(this.convertEthPortObject(elineInfo, type));
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
	}
}
