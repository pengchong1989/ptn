package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.protect.LoopProtectInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.drivechenxiao.service.bean.protocols.mpls.RingObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.BusinessidService_MB;
import com.nms.model.ptn.path.protect.WrappingProtectService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class WrappingCXServiceImpl extends CXOperationBase implements OperationServiceI {


	public WrappingCXServiceImpl() throws Exception {

	}

	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {

		List<LoopProtectInfo> loopProtectInfoList = null;
		OperationObject operationObject = null;
		String result=null;
		try {
			loopProtectInfoList = objectList;
			if (loopProtectInfoList == null ) {
				throw new Exception("list is null");
			}
			SiteUtil siteUtil=new SiteUtil();
			for(LoopProtectInfo loopProtect :loopProtectInfoList){
				if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(loopProtect.getSiteId())&&0==siteUtil.SiteTypeUtil(loopProtect.getSiteId())) {
					operationObject = this.convertOperation(operationObject, loopProtect, TypeAndActionUtil.ACTION_DELETE);
					super.sendAction(operationObject);
					operationObject = super.verification(operationObject);
					if (operationObject.isSuccess()) {
						result=  operationObject.getCxActionObjectList().get(0).getStatus();
					} else {
						result = super.getErrorMessage(operationObject);
					}
				}else {
					result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			loopProtectInfoList = null;
			operationObject = null;
		}
		return result;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {

		OperationObject operationObject = null;
		List<LoopProtectInfo> protectInfos = null;
		String result=null;
		BusinessidService_MB businessidService = null;
		try {
			businessidService = (BusinessidService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BUSINESSID);
			
			protectInfos = (List<LoopProtectInfo>) object;
			SiteUtil siteUtil=new SiteUtil();
			for(LoopProtectInfo loopProtectInfo:protectInfos){
				if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(loopProtectInfo.getSiteId())&&0==siteUtil.SiteTypeUtil(loopProtectInfo.getSiteId())) {
					if(loopProtectInfo.getActiveStatus()== EActiveStatus.ACTIVITY.getValue()){
						operationObject = this.convertOperation(operationObject, loopProtectInfo, TypeAndActionUtil.ACTION_INSERT);
						super.sendAction(operationObject);
						operationObject = super.verification(operationObject);
						if (operationObject.isSuccess()) {
							result = operationObject.getCxActionObjectList().get(0).getStatus();
						} else {
							result = super.getErrorMessage(operationObject);
						}
					}else {
						result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
					}
				}else {
					result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(businessidService);
		}
		return result;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		LoopProtectInfo loopProtectInfo = null;
		OperationObject operationObject = null;
		List<LoopProtectInfo> protectInfos = null;
		String result=null;		
		protectInfos = (List<LoopProtectInfo>) object;
		loopProtectInfo = protectInfos.get(0);
		
		if (null != loopProtectInfo) {
			SiteUtil siteUtil=new SiteUtil();
			if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(loopProtectInfo.getSiteId())&&0==siteUtil.SiteTypeUtil(loopProtectInfo.getSiteId())) {
				if(loopProtectInfo.getActiveStatus()== EActiveStatus.ACTIVITY.getValue()){
					operationObject = this.convertOperation(operationObject, loopProtectInfo, TypeAndActionUtil.ACTION_UPDATE);
					super.sendAction(operationObject);
					operationObject = super.verification(operationObject);
					if (operationObject.isSuccess()) {
						result = operationObject.getCxActionObjectList().get(0).getStatus();
					} else {
						result = super.getErrorMessage(operationObject);
					}
				}else {
					result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}
			}else{
				result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		}
		loopProtectInfo = null;
		operationObject = null;
		return result;
	}
	private OperationObject convertOperation(OperationObject operationObject, LoopProtectInfo loopProtectInfo, String action) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(loopProtectInfo.getSiteId()));
			cxActionObject.setAction(action);
			cxActionObject.setType(TypeAndActionUtil.TYPE_LOOPPROTECT);
			cxActionObject.setRingObject((RingObject)this.getCXRingObject(loopProtectInfo));
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}
	@Override
	public Object synchro(int siteId) {
		String result = null;
		LoopProtectInfo loopProtectInfo = null;
		OperationObject operationObject = null;
		loopProtectInfo =  new LoopProtectInfo();
		loopProtectInfo.setSiteId(siteId);
		WrappingProtectService_MB service = null;
		try {
			service = (WrappingProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WRAPPINGPROTECT);
			if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(siteId)) {
				operationObject = this.convertOperation(operationObject, loopProtectInfo, TypeAndActionUtil.ACTION_SYNCHRO);
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
					service.updateActiveStatus(siteId, EActiveStatus.UNACTIVITY.getValue());
					this.getLoopProtectObject(operationObject.getCxActionObjectList().get(0).getRingObjectList(), siteId);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
		loopProtectInfo = null;
		return result;
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
	private void getLoopProtectObject(List<RingObject> ringObjectList, int siteId) throws Exception {

		if (null == ringObjectList) {
			throw new Exception("ringObjectList is null");
		}
		PortInst portInstWest;
		PortInst portInstEast;
		SynchroUtil synchroUtil = new SynchroUtil();
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			
			for (RingObject ringObject : ringObjectList) {
				LoopProtectInfo loopProtectInfo;
				portInstWest = new PortInst();
				portInstEast = new PortInst();
				portInstWest.setSiteId(siteId);
				portInstWest.setPortName(ringObject.getWestport());
				portInstWest = portService.select(portInstWest).get(0);
				portInstEast.setSiteId(siteId);
				portInstEast.setPortName(ringObject.getEastport());
				portInstEast = portService.select(portInstEast).get(0);
				loopProtectInfo = new LoopProtectInfo();
				loopProtectInfo.setName(ringObject.getName());
				loopProtectInfo.setSiteId(siteId);
				loopProtectInfo.setLoopBusinessId(Integer.parseInt(ringObject.getName()));
				loopProtectInfo.setNodeId(Integer.parseInt(ringObject.getNodeid()));
				loopProtectInfo.setWaittime(super.convertWtrtimeRead(Integer.parseInt(ringObject.getWtrtime())));
				loopProtectInfo.setDelaytime(super.convertDelaytimeRead(Integer.parseInt(ringObject.getHoldofftime())));
				loopProtectInfo.setWestNodeId(Integer.parseInt(ringObject.getWestnbrid()));
				loopProtectInfo.setEastNodeId(Integer.parseInt(ringObject.getEastnbrid()));
				loopProtectInfo.setWestPort(portInstWest.getPortId());
				loopProtectInfo.setEastPort(portInstEast.getPortId());
				loopProtectInfo.setApsenable("true".equals(ringObject.getEnaps())?1:0);
				loopProtectInfo.setProtectType(Integer.parseInt(ringObject.getStat()));
				loopProtectInfo.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
				loopProtectInfo.setIsSingle(1);
				synchroUtil.loopProtectSynchro(loopProtectInfo, siteId);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
	}
	
	/**
	 * 获取陈晓驱动层端口对象 
	 * @throws Exception
	 */
	public RingObject getCXRingObject(LoopProtectInfo loopProtectInfo) throws Exception{
		RingObject cxring = null;
		PortService_MB portService = null;
		PortInst portInstWest;
		PortInst portInstEast;
		if (loopProtectInfo == null) {
			throw new Exception("LoopProtectInfo is null");
		}
		try {
			portInstWest = new PortInst();
			portInstEast = new PortInst();
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			if(0!=loopProtectInfo.getWestPort()){
				portInstWest.setPortId(loopProtectInfo.getWestPort());
				portInstWest.setSiteId(loopProtectInfo.getSiteId());
				portInstWest = portService.select(portInstWest).get(0);
			}
			if(0!=loopProtectInfo.getEastPort()){
				portInstEast.setPortId(loopProtectInfo.getEastPort());
				portInstEast.setSiteId(loopProtectInfo.getSiteId());
				portInstEast = portService.select(portInstEast).get(0);
			}
			cxring = new RingObject();
			cxring.setName(loopProtectInfo.getLoopBusinessId()+"");
			cxring.setNodeid(loopProtectInfo.getNodeId()+"");
//				cxring.setDesc(loopProtectInfo.get)  
			cxring.setWtrtime(super.convertWtrtimeRead(loopProtectInfo.getWaittime())+"");
			cxring.setHoldofftime(super.convertDelaytimeSend(loopProtectInfo.getDelaytime())+"");
			cxring.setWestnbrid(loopProtectInfo.getWestNodeId()+"");
			cxring.setEastnbrid(loopProtectInfo.getEastNodeId()+"");
			cxring.setWestport(portInstWest.getPortName());
			cxring.setEastport(portInstEast.getPortName());
//				cxring.setApscmd(apscmd)
	//		cxring.setStat(loopProtectInfo.getProtectType()+"");
//				cxring.setWestlastrxpdu(loopProtectInfo.);
//				cxring.setEastlastrxpdu();
//				cxring.setWestlasttxpdu();
//				cxring.setEastlasttxpdu();
			cxring.setEnaps(loopProtectInfo.getApsenable()==1?"true":"false");
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
		return cxring;
	}
}
