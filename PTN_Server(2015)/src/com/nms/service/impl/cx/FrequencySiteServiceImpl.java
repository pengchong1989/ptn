package com.nms.service.impl.cx;


import java.util.List;

import com.nms.db.bean.ptn.clock.FrequencyInfoNeClock;
import com.nms.db.enums.EManufacturer;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.clock.ClockObject;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * 网元时钟功能
 * @author Administrator
 *
 */
public class FrequencySiteServiceImpl extends CXOperationBase implements OperationServiceI {
	
	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		FrequencyInfoNeClock frequencyInfo_neClock=null;
		String result = null;
		OperationObject operationObject=null;
		frequencyInfo_neClock = (FrequencyInfoNeClock) object;
		if (null != frequencyInfo_neClock) {	
			SiteUtil siteUtil=new SiteUtil();
			if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(frequencyInfo_neClock.getSiteId())&&0==siteUtil.SiteTypeUtil(frequencyInfo_neClock.getSiteId())) {
				operationObject = this.convertOperation(operationObject, frequencyInfo_neClock,TypeAndActionUtil.ACTION_SAVEANDUPDATE);					
				super.sendAction(operationObject);					
				operationObject = super.verification(operationObject);//有问题					
				if (operationObject.isSuccess()) {	
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {						
					result = super.getErrorMessage(operationObject);
				}
			}
		}else{
			result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		}
		
		frequencyInfo_neClock = null;
		operationObject = null;
		return result;
	}
	/**
	 * 查询设备
	 * @param siteIds
	 * @return
	 * @throws Exception
	 */
	public FrequencyInfoNeClock excutionSelect(int siteIds) throws Exception {
		FrequencyInfoNeClock frequencyInfo_neClock=null;
		OperationObject operationObject=null;
		if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(siteIds)) {
			operationObject = this.getOperationObject(siteIds);
			super.sendAction(operationObject);					
			operationObject = super.verification(operationObject);						
			if (operationObject.isSuccess()) {						
				frequencyInfo_neClock=getFrequencyInfo(operationObject);						
			} 	
		}
		operationObject = null;
		return frequencyInfo_neClock;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		return null;
	}
	/**
	 *根据ID  封装对象
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(int id) throws Exception {
		OperationObject operationObject = null;
		CXActionObject cxActionObject = null;
		CXNEObject cxneObject = null;
		try {
			operationObject = new OperationObject();
			cxneObject = super.getCXNEObject(id);
			cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_FREQUENCY_INFO);
			cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
			cxActionObject.setCxNeObject(cxneObject);
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			throw e;
		}
		return operationObject;
	}
	private OperationObject convertOperation(OperationObject operationObject, FrequencyInfoNeClock frequencyInfo_neClock,String action) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(frequencyInfo_neClock.getSiteId()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_FREQUENCY_INFO);
			cxActionObject.setAction(action);
			cxActionObject.setClockObject((ClockObject)this.getCXClockObject(frequencyInfo_neClock));

			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}
	/**
	 * 读取设备
	 * @param operationObject
	 * @return
	 * @throws Exception
	 */
	private FrequencyInfoNeClock getFrequencyInfo(OperationObject operationObject) throws Exception {
		FrequencyInfoNeClock frequencyInfo=null;
		for (CXActionObject cxActionObject  : operationObject.getCxActionObjectList()) {
			
			if(cxActionObject.getClockObjectList().size()>0){
				
				ClockObject clockObject =(ClockObject) cxActionObject.getClockObjectList().get(0);
				frequencyInfo=new FrequencyInfoNeClock();
				if("true".equals(clockObject.getSsmmode())){
					UiUtil.getCodeByValue("operationMode", "1").getId();
					//frequencyInfo.setStartTreaty(205);
				}else{
					UiUtil.getCodeByValue("operationMode", "0").getId();
					//frequencyInfo.setStartTreaty(204);
				}
				frequencyInfo.setStartTreaty(UiUtil.getCodeByValue("enableSelectedProtocol", "true".equals(clockObject.getSsmmode())?"1":"0").getId());		
				frequencyInfo.setSystemRecoverTime(Integer.parseInt(clockObject.getScswtr()));
				frequencyInfo.setExportRecoverTime(Integer.parseInt(clockObject.getEcswtr()));
				frequencyInfo.setHandleModel(UiUtil.getCodeByValue("operationMode", clockObject.getOperationmode()).getId());
				frequencyInfo.setOscillationLevel(UiUtil.getCodeByValue("freeOscillationLevel", clockObject.getFreerunlevel()).getId());
				frequencyInfo.setQualityLevel(UiUtil.getCodeByValue("externalClockSupressThreshold", clockObject.getUnknownlevel()).getId());
				frequencyInfo.setExportClockModel(UiUtil.getCodeByValue("exportClockMode", clockObject.getExtclkdrvmode()).getId());
				frequencyInfo.setClockSuppress(UiUtil.getCodeByValue("exportClockMode", clockObject.getSquelchmin()).getId());
				//状态
				frequencyInfo.setSystemJobStatus(clockObject.getSCSWorkState());
				frequencyInfo.setSystemSourcce(clockObject.getSCSSelectSource());
				
				
				frequencyInfo.setExportJobStatus(clockObject.getECSWorkState());
				frequencyInfo.setExportSourcce(clockObject.getECSSelectSource());
			}
		}
			return frequencyInfo;
	}
	/**
	 * *获取陈晓驱动层端口对象 
	 * ClockObject
	 * @param frequencyInfo
	 * @return
	 * @throws Exception
	 */
	public ClockObject getCXClockObject(FrequencyInfoNeClock frequencyInfo) throws Exception{
		ClockObject clockObject=null;
		if (frequencyInfo == null) {
			throw new Exception("frequencyInfo_neClock is null");
		}
		try {
			clockObject=new ClockObject();
			//属性
			
			clockObject.setSsmmode(1==Integer.parseInt(UiUtil.getCodeById(frequencyInfo.getStartTreaty()).getCodeValue())?"true":"false");
			clockObject.setScswtr(frequencyInfo.getSystemRecoverTime()+"");
			clockObject.setEcswtr(frequencyInfo.getExportRecoverTime()+"");
			clockObject.setOperationmode(UiUtil.getCodeById(frequencyInfo.getHandleModel()).getCodeValue());
			clockObject.setFreerunlevel(UiUtil.getCodeById(frequencyInfo.getOscillationLevel()).getCodeValue());
			clockObject.setUnknownlevel(UiUtil.getCodeById(frequencyInfo.getQualityLevel()).getCodeValue());
			clockObject.setExtclkdrvmode(UiUtil.getCodeById(frequencyInfo.getExportClockModel()).getCodeValue());
			clockObject.setSquelchmin(UiUtil.getCodeById(frequencyInfo.getClockSuppress()).getCodeValue());
			//状态
			clockObject.setSCSWorkState(frequencyInfo.getSystemJobStatus());//0
			clockObject.setSCSSelectSource(frequencyInfo.getSystemSourcce());//none
			
			
			clockObject.setECSWorkState(frequencyInfo.getExportJobStatus());//4
			clockObject.setECSSelectSource(frequencyInfo.getExportSourcce());//
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
		return clockObject;
	}
}
