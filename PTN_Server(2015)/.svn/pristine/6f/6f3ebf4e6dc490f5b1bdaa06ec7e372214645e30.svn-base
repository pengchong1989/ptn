package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EQosDirection;
import com.nms.db.enums.EServiceType;
import com.nms.drive.service.bean.LSPObject;
import com.nms.drive.service.bean.TunnelObject;
import com.nms.model.ptn.qos.QosInfoService_MB;
import com.nms.model.ptn.qos.QosRelevanceService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class QosWHServiceImpl extends WHOperationBase implements OperationServiceI {

	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		QosInfo qosInfo = null;
		QosInfoService_MB qosInfoService = null;
		try {
			qosInfo = (QosInfo) objectList.get(0);
			if (qosInfo != null) {
				qosInfoService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
//				qosInfoService.delete(qosInfo);
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(qosInfoService);
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String excutionInsert(Object object) throws Exception {
		List<QosInfo> qosInfoList = new ArrayList<QosInfo>();
		QosInfoService_MB qosInfoService = null;
		try {
			qosInfoList =  (List<QosInfo>) object;
			if (qosInfoList .size()>0) {
				qosInfoService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
//				qosInfoService.saveOrUpdate(qosInfoList);
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(qosInfoService);
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String excutionUpdate(Object object) throws Exception {
		List<QosInfo> qosInfoList = new ArrayList<QosInfo>();
		QosInfoService_MB qosInfoService = null;
		try {
			qosInfoList =  (List<QosInfo>) object;
			if (qosInfoList .size() > 0) {
				qosInfoService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
//				qosInfoService.saveOrUpdate(qosInfoList);
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(qosInfoService);
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
	}
	
	public String excutionUpdate_qos(List<QosInfo> qosInfoList, Object object) throws Exception {

		String result = null;
		QosRelevanceService_MB qosRelevanceService = null;
		Tunnel tunnel = null;
		PwInfo pwInfo=null;
		OperationServiceI operationServiceI=null;
		try {
			//先修改数据库
			qosRelevanceService = (QosRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSRELEVANCE);
			if (object instanceof Tunnel) {
				tunnel = (Tunnel) object;
				tunnel.setQosList(qosInfoList);
				qosRelevanceService.save(qosRelevanceService.getList(tunnel));
				
				operationServiceI=new TunnelWHServiceImpl();
				
			}else if(object instanceof PwInfo){
				pwInfo=(PwInfo) object;
				pwInfo.setQosList(qosInfoList);
				qosRelevanceService.save(qosRelevanceService.getList(pwInfo));
				
				operationServiceI=new PwWHServiceImpl();
			}

			result = operationServiceI.excutionUpdate(object);
			
//			 operationObject = this.convertOperationObject(qosInfoList, object);
//
//			if (operationObject.getCxActionObjectList().size() > 0) {
//				super.sendAction(operationObject);
//				super.verification(operationObject);
//
//				if (operationObject.isSuccess()) {
//					result = operationObject.getCxActionObjectList().get(0).getStatus();
//				} else {
//					result = super.getErrorMessage(operationObject);
//				}
//			} else {
//				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
//			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(qosRelevanceService);
		}
		return result;
	
	}
	
	/**
	 * 根据网元ID,aPortId把对应的qos封装到tunnelObject中
	 * 
	 * @param oamInfo
	 * @return
	 * @throws Exception
	 */
	public TunnelObject getQosInfo(TunnelObject tunnelObject, int siteId, int tunnelbusinessid) throws Exception {

		QosInfoService_MB qosInfoService = null;
		List<QosInfo> qosInfoList = new ArrayList<QosInfo>();
		QosInfo qosInfo = null;
		try {
			qosInfoService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
			qosInfoList = qosInfoService.getQosByObj(EServiceType.ACPORT.toString(), tunnelbusinessid);//根据网元id和aprotid查询qos
			
			if (qosInfoList .size()>0) {
				tunnelObject = this.getTunnelObject(tunnelObject, qosInfoList);//把qos封装到对应的tunnelObject中
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(qosInfoService);
		}
		return tunnelObject;
	}

	/**
	 * tunnelObject的赋值
	 * 
	 * @param tunnelObject
	 * @param oamInfo
	 * @return
	 */
	public TunnelObject getTunnelObject(TunnelObject tunnelObject, List<QosInfo> qosInfoList) {

		for(QosInfo qosInfo : qosInfoList){
			if(Integer.parseInt(qosInfo.getDirection()) == EQosDirection.FORWARD.getValue()){//判断qos是前向还是后向
				LSPObject foreLSP = new LSPObject();
				foreLSP.setBandwidthEnable(1);
//				foreLSP.setCbs(qosInfo.getCbs());
//				foreLSP.setPbs(qosInfo.getPbs());
				foreLSP.setCir(qosInfo.getCir());
				foreLSP.setPir(qosInfo.getPir());
				tunnelObject.setForeLSP(foreLSP);
			}
			if(Integer.parseInt(qosInfo.getDirection()) == EQosDirection.BACKWARD.getValue()){
				LSPObject afterLSP = new LSPObject();
				afterLSP.setBandwidthEnable(1);
//				afterLSP.setCbs(qosInfo.getCbs());
//				afterLSP.setPbs(qosInfo.getPbs());
				afterLSP.setCir(qosInfo.getCir());
				afterLSP.setPir(qosInfo.getPir());
				tunnelObject.setAfterLSP(afterLSP);
			}
		}

		return tunnelObject;
	}
	@Override
	public Object synchro(int siteId) {
		return null;
	}
}
