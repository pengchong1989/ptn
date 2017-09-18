package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.EthLoopInfo;
import com.nms.drive.service.PtnServiceEnum;
import com.nms.drive.service.bean.EthLoopObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.model.ptn.EthLoopServcie_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;

public class EthLoopWHServiceImpl extends WHOperationBase implements OperationServiceI{

	@Override
	public String excutionDelete(List objectList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		EthLoopInfo thLoopInfo = null;
		EthLoopServcie_MB ethLoopServcie = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		EthLoopObject ethLoopObject = null;
		List<EthLoopObject> ethLoopObjectList = null;
		try {
			ethLoopServcie = (EthLoopServcie_MB) ConstantUtil.serviceFactory.newService_MB(Services.ETHLOOPSERVICE);
			thLoopInfo = (EthLoopInfo) object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(thLoopInfo.getSiteId());
			/** 锁住该网元 */
			super.lockSite(siteIdList, SiteLockTypeUtil.ETHLOOP_INSERT);
			SiteUtil siteUtil = new SiteUtil();
			if("0".equals(siteUtil.SiteTypeUtil(thLoopInfo.getSiteId())+"")){////非失连网元、非虚拟网元下发设备s
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(thLoopInfo.getSiteId());
				ethLoopObject = this.getEthLoopObject(thLoopInfo);
				ethLoopObjectList = new ArrayList<EthLoopObject>();
				ethLoopObjectList.add(ethLoopObject);
				operationObjectAfter = new OperationObject();
				super.sendAction(operationObjectAfter, neObject, ethLoopObjectList, PtnServiceEnum.ETHLOOP);//下发以太网环回
				operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
				if (operationObjectResult.isSuccess()) {
					ethLoopServcie.save(thLoopInfo);
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {//失败
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				ethLoopServcie.save(thLoopInfo);
				return ResultString.CONFIG_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(ethLoopServcie);
			thLoopInfo = null;
			siteIdList = null;
			operationObjectAfter = null;
			operationObjectResult = null;
			neObject = null;
			ethLoopObject = null;
			ethLoopObjectList = null;
		}
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		EthLoopInfo thLoopInfo = null;
		EthLoopServcie_MB ethLoopServcie = null;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		NEObject neObject = null;
		EthLoopObject ethLoopObject = null;
		List<EthLoopObject> EthLoopObjectList = null;
		try {
			ethLoopServcie = (EthLoopServcie_MB) ConstantUtil.serviceFactory.newService_MB(Services.ETHLOOPSERVICE);
			thLoopInfo = (EthLoopInfo) object;
			siteIdList = new ArrayList<Integer>();
			siteIdList.add(thLoopInfo.getSiteId());
			/** 锁住该网元 */
			super.lockSite(siteIdList, SiteLockTypeUtil.ETHLOOP_UPDATE);
			SiteUtil siteUtil=new SiteUtil();
			if("0".equals(siteUtil.SiteTypeUtil(thLoopInfo.getSiteId())+"")){////非失连网元、非虚拟网元下发设备s
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(thLoopInfo.getSiteId());
				ethLoopObject = this.getEthLoopObject(thLoopInfo);
				EthLoopObjectList = new ArrayList<EthLoopObject>();
				EthLoopObjectList.add(ethLoopObject);
				operationObjectAfter = new OperationObject();
				super.sendAction(operationObjectAfter, neObject, EthLoopObjectList, PtnServiceEnum.ETHLOOP);//下发以太网环回
				operationObjectResult = super.verification(operationObjectAfter);//获取设备返回信息
				if (operationObjectResult.isSuccess()) {
					ethLoopServcie.update(thLoopInfo);
					super.clearLock(siteIdList);
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {//失败
					super.clearLock(siteIdList);
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				ethLoopServcie.update(thLoopInfo);
				return ResultString.CONFIG_SUCCESS;
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			 super.clearLock(siteIdList);
			 UiUtil.closeService_MB(ethLoopServcie);
			 thLoopInfo = null;
			 siteIdList = null;
			 operationObjectAfter = null;
		     operationObjectResult = null;
			 neObject = null;
			 ethLoopObject = null;
			 EthLoopObjectList = null;
		}
		return null;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 数据库信息向驱动信息转化
	 * @param siteId
	 * @param allConfigInfo
	 * @return
	 */
	private EthLoopObject getEthLoopObject(EthLoopInfo thLoopInfo) {
		EthLoopObject ethLoopObject = new EthLoopObject();
		ethLoopObject.setLoopEnableObject(thLoopInfo.getLoopEnable());
		ethLoopObject.setLoopRuleObject(thLoopInfo.getLoopRule());
		ethLoopObject.setPortNumberObject(thLoopInfo.getPortNumber());
		
		if(!thLoopInfo.getMacAddress().equals("")){
			ethLoopObject.setMacAddress(CoderUtils.transformMac(thLoopInfo.getMacAddress()));
		}
		if(!thLoopInfo.getVlanVaue().equals("")){
			ethLoopObject.setVlan(thLoopInfo.getVlanVaue());
		}
		if(!thLoopInfo.getIp().equals("")){
			ethLoopObject.setIpAddress(thLoopInfo.getIp());
		}
		return ethLoopObject;
	}

}
