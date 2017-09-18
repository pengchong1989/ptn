package com.nms.service.impl.cx;


import java.util.List;

import com.nms.db.bean.ptn.clock.TimeManageInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.PtpObject;
import com.nms.model.ptn.clock.TimeManageInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * 网元PTP配置
 * @author sy
 *
 */
public class PtpSiteServiceImpl extends CXOperationBase implements OperationServiceI {

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
		TimeManageInfo timeManageInfo=null;
		String result = null;	
		OperationObject operationObject=null;
		timeManageInfo=(TimeManageInfo) object;
		if (null != timeManageInfo) {
			SiteUtil siteUtil=new SiteUtil();
			if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(timeManageInfo.getSiteId())&&0==siteUtil.SiteTypeUtil(timeManageInfo.getSiteId())) {
					operationObject = this.convertOperation(operationObject, timeManageInfo, TypeAndActionUtil.ACTION_SAVEANDUPDATE);
					super.sendAction(operationObject);
					operationObject = super.verification(operationObject);
					if (operationObject.isSuccess()) {
						result = operationObject.getCxActionObjectList().get(0).getStatus();
					} else {
						result = super.getErrorMessage(operationObject);
					}
			}else{
				result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		}			
		operationObject = null;
		timeManageInfo = null;		
		return result;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		
		String result = null;
		TimeManageInfo timeManageInfo=null;
		OperationObject operationObject = null;
		TimeManageInfoService_MB timeManageInfoService = null;
		
		try {
			timeManageInfo =  new TimeManageInfo();
			timeManageInfo.setSiteId(siteId);
			timeManageInfoService = (TimeManageInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.TimeManageInfoService);
			SiteUtil siteUtil=new SiteUtil();
			if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(siteId)&&0==siteUtil.SiteTypeUtil(timeManageInfo.getSiteId())) {
				operationObject = this.convertOperation(operationObject, timeManageInfo, TypeAndActionUtil.ACTION_SYNCHRO);
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
//					timeManageInfoService.update(timeManageInfo);
					this.getTimeManageInfoObject(operationObject.getCxActionObjectList().get(0).getPtpObjectList(), siteId);
				}
			}
		} catch (Exception e) {
			throw e;
		}finally{
			timeManageInfo = null;
			operationObject=null;
			UiUtil.closeService_MB(timeManageInfoService);
		}
		return result;
	}
	/**
	 * 查询设备
	 * @param siteIds
	 * @return
	 * @throws Exception
	 */
	public TimeManageInfo excutionSelect(int siteIds) throws Exception {
		TimeManageInfo timeManageInfo=null;
		String result = null;
		OperationObject operationObject=null;		
		SiteUtil siteUtil=new SiteUtil();
		if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(siteIds)&&0==siteUtil.SiteTypeUtil(siteIds)) {
			operationObject = this.getOperationObject(siteIds);
//					operationObject = this.convertOperation(operationObject, frequencyInfo_neClock,TypeAndActionUtil.ACTION_SELECT);					
				super.sendAction(operationObject);					
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {		
					timeManageInfo=getTimeManageInfo(operationObject.getCxActionObjectList().get(0).getPtpObjectList());
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {						
					result = super.getErrorMessage(operationObject);
				}
			
		}else{
			result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		}
		operationObject = null;
		return timeManageInfo;
	}
	/**
	 *根据ID  封装对象
	 * @param ID
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(int id) throws Exception {
		OperationObject operationObject = null;
		CXActionObject cxActionObject = null;
		CXNEObject cxneObject = null;
		operationObject = new OperationObject();
		cxneObject = super.getCXNEObject(id);
		cxActionObject = new CXActionObject();
		cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
		cxActionObject.setType(TypeAndActionUtil.TYPE_TIMEMANAGER);
		cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
		cxActionObject.setCxNeObject(cxneObject);
		operationObject.getCxActionObjectList().add(cxActionObject);
		
		return operationObject;
	}
	private OperationObject convertOperation(OperationObject operationObject, TimeManageInfo timeManageInfo, String action) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(timeManageInfo.getSiteId()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_TIMEMANAGER);
			cxActionObject.setAction(action);
			cxActionObject.setPtpObject((PtpObject)this.getPtpObject(timeManageInfo));

			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			throw e;
		}
		return operationObject;
	}


		/**获取陈晓驱动层端口对象 
		 * @throws Exception **/
	public PtpObject getPtpObject(TimeManageInfo timeManageInfo) throws Exception{
		PtpObject ptpObject = null;
		if (timeManageInfo == null) {
			throw new Exception("timeManageInfo is null");
		}
		try {
			
			ptpObject =new PtpObject();
			//属性
			ptpObject.setBmcenable(1==timeManageInfo.getModel()?"true":"false");
			//ptpObject.setBmcenable(UiUtil.getCodeById(timeManageInfo.getModel()).getCodeValue());
			ptpObject.setPriority1(timeManageInfo.getPriorOne()+"");//优先级  1  ， 2
			ptpObject.setPriority2(timeManageInfo.getPriorTwo()+"");
			ptpObject.setClockclass(timeManageInfo.getClockType()+"");//时钟类型
			ptpObject.setVariance(timeManageInfo.getClockVariance()+"");//时钟方差			
			ptpObject.setClockaccuracy(timeManageInfo.getClockPrecision()+"");//时钟精度
			//ptpObject.setVariance(timeManageInfo.getManufacturerOUI()+"");
			ptpObject.setDomain(timeManageInfo.getClockRegion()+"");
			ptpObject.setTcdomain1(timeManageInfo.getClockRegionOne()+"");
			ptpObject.setTcdomain2(timeManageInfo.getClockRegionTwo()+"");
			ptpObject.setTcdomain3(timeManageInfo.getClockRegionThree()+"");
			ptpObject.setTcdomain4(timeManageInfo.getClockRegionFour()+"");
			ptpObject.setTcdomain2enable(timeManageInfo.getClockRegionTwoJbox()==0 ? "false":"true");
			ptpObject.setTcdomain3enable(timeManageInfo.getClockRegionThreeJbox()==0 ? "false":"true");
			ptpObject.setTcdomain4enable(timeManageInfo.getClockRegionFourJbox()==0 ? "false":"true");
			ptpObject.setSlaveonly(1==timeManageInfo.getFollowModel()?"true":"false");
			if(0!=timeManageInfo.getTodsendTime()){
				ptpObject.setTodtxtype(UiUtil.getCodeById(timeManageInfo.getTodsendTime()).getCodeValue());//239
			}
			if(0!=timeManageInfo.getClockRegionDelay()){
				ptpObject.setTcdelaymechanism(UiUtil.getCodeById(timeManageInfo.getClockRegionDelay()).getCodeValue());//透传时钟延时机制
			}
			//235
			ptpObject.setOffsetfrommaster("0000000080000000");
			ptpObject.setFilterenable("true");
			
			//状态
			ptpObject.setClkid(timeManageInfo.getTimeID());
			ptpObject.setClocktype(timeManageInfo.getTimeType());
			ptpObject.setPrntid(timeManageInfo.getFtimeID());
			ptpObject.setMeanpathdelay(timeManageInfo.getFtimePort());
			ptpObject.setStepsremoved(timeManageInfo.getLeapNumber());
			ptpObject.setTimesoffset(timeManageInfo.getSystemVarianceValue());
			ptpObject.setCurrenttodsel(timeManageInfo.getTodState());
			ptpObject.setGmclkid(timeManageInfo.getzTimeID());
			ptpObject.setGmclockclass(timeManageInfo.getzTimeTpye());
			ptpObject.setGmclockaccuracy(timeManageInfo.getzTimePrecision());
			ptpObject.setGmvariance(timeManageInfo.getzTimeVariance());
			ptpObject.setGmprio1(timeManageInfo.getzTimePriorOne());
			ptpObject.setGmprio2(timeManageInfo.getzTimePriorTwo());
			
		} catch (Exception e) {
			throw e;
		}
		
		return ptpObject;
	}
	/**
	 * 与数据库同步
	 * 
	 * @author sy
	 * 
	 * @param ptpObjectList
	 * @param siteId 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void getTimeManageInfoObject(List<PtpObject> ptpObjectList, int siteId) throws Exception {
		
		if (null == ptpObjectList) {
			throw new Exception("ptpObjectList is null");
		}
		
		TimeManageInfo timeManageInfo=null;
		SynchroUtil synchroUtil = new SynchroUtil();
		for (PtpObject ptpObject : ptpObjectList) {
			timeManageInfo=new TimeManageInfo();
			//属性
			timeManageInfo.setSiteId(siteId);
			timeManageInfo.setModel(ptpObject.getBmcenable().equals("true")? 234:233);
			timeManageInfo.setPriorOne(Integer.parseInt(ptpObject.getPriority1()));//优先级  1  ， 2
			timeManageInfo.setPriorTwo(Integer.parseInt(ptpObject.getPriority2()));
			timeManageInfo.setClockType(Integer.parseInt(ptpObject.getClockclass()));//时钟类型
			timeManageInfo.setClockVariance(Integer.parseInt(ptpObject.getVariance()));//时钟方差	
			timeManageInfo.setClockPrecision(Integer.parseInt(ptpObject.getClockaccuracy()));//时钟精度
			//timeManageInfo.setManufacturerOUI(Integer.parseInt());//OUI厂商
			timeManageInfo.setClockRegion(Integer.parseInt(ptpObject.getDomain()));
			timeManageInfo.setClockRegionOne(Integer.parseInt(ptpObject.getTcdomain1()));
			timeManageInfo.setClockRegionTwo(Integer.parseInt(ptpObject.getTcdomain2()));
			timeManageInfo.setClockRegionThree(Integer.parseInt(ptpObject.getTcdomain3()));
			timeManageInfo.setClockRegionFour(Integer.parseInt(ptpObject.getTcdomain4()));
			if("false".equals(ptpObject.getTcdomain2enable())){
				timeManageInfo.setClockRegionTwoJbox(0);
			}else{
				timeManageInfo.setClockRegionTwoJbox(1);
			}
			if("false".equals(ptpObject.getTcdomain3enable())){
				timeManageInfo.setClockRegionThreeJbox(0);
			}else{
				timeManageInfo.setClockRegionThreeJbox(1);
			}
			if("false".equals(ptpObject.getTcdomain4enable())){
				timeManageInfo.setClockRegionFourJbox(0);
			}else{
				timeManageInfo.setClockRegionFourJbox(1);
			}
			timeManageInfo.setFollowModel("true".equals(ptpObject.getSlaveonly())?238:237);
			timeManageInfo.setClockRegionDelay(UiUtil.getCodeByValue("passThroughClockDelayMechanism", ptpObject.getTcdelaymechanism()).getId());
			timeManageInfo.setTodsendTime(UiUtil.getCodeByValue("todTransmissionTimeType", ptpObject.getTodtxtype()).getId());
			
			//状态
			timeManageInfo.setTimeID(ptpObject.getClkid());
			timeManageInfo.setTimeType(ptpObject.getClocktype());
			timeManageInfo.setFtimeID(ptpObject.getPrntid());
			timeManageInfo.setFtimePort(ptpObject.getMeanpathdelay());
			timeManageInfo.setLeapNumber(ptpObject.getStepsremoved());
			timeManageInfo.setSystemVarianceValue(ptpObject.getTimesoffset());
			timeManageInfo.setTodState(ptpObject.getCurrenttodsel());
			timeManageInfo.setzTimeID(ptpObject.getGmclkid());
			timeManageInfo.setzTimeTpye(ptpObject.getGmclockclass());
			timeManageInfo.setzTimePrecision(ptpObject.getGmclockaccuracy());
			timeManageInfo.setzTimeVariance(ptpObject.getGmvariance());
			timeManageInfo.setzTimePriorOne(ptpObject.getGmprio1());
			timeManageInfo.setzTimePriorTwo(ptpObject.getGmprio2());
			synchroUtil.PtpSiteSynchro(timeManageInfo, siteId);
		}
	}
	/**
	 * 读取设备
	 * @param ptpObjectList
	 * @return  TimeManageInfo
	 * @throws Exception
	 */
	private TimeManageInfo getTimeManageInfo(List<PtpObject> ptpObjectList) throws Exception {
		
		if (null == ptpObjectList) {
			throw new Exception("ptpObjectList is null");
		}
		
		TimeManageInfo timeManageInfo=null;
		for (PtpObject ptpObject : ptpObjectList) {
			timeManageInfo=new TimeManageInfo();
			//属性			
			timeManageInfo.setModel(ptpObject.getBmcenable().equals("true")? 234:233);
			timeManageInfo.setPriorOne(Integer.parseInt(ptpObject.getPriority1()));//优先级  1  ， 2
			timeManageInfo.setPriorTwo(Integer.parseInt(ptpObject.getPriority2()));
			timeManageInfo.setClockType(Integer.parseInt(ptpObject.getClockclass()));//时钟类型
			timeManageInfo.setClockVariance(Integer.parseInt(ptpObject.getVariance()));//时钟方差		
			timeManageInfo.setClockPrecision(Integer.parseInt(ptpObject.getClockaccuracy()));
			//timeManageInfo.setManufacturerOUI(Integer.parseInt());
			timeManageInfo.setClockRegion(Integer.parseInt(ptpObject.getDomain()));
			timeManageInfo.setClockRegionOne(Integer.parseInt(ptpObject.getTcdomain1()));
			timeManageInfo.setClockRegionTwo(Integer.parseInt(ptpObject.getTcdomain2()));
			timeManageInfo.setClockRegionThree(Integer.parseInt(ptpObject.getTcdomain3()));
			timeManageInfo.setClockRegionFour(Integer.parseInt(ptpObject.getTcdomain4()));
			if("false".equals(ptpObject.getTcdomain2enable())){
				timeManageInfo.setClockRegionTwoJbox(0);
			}else{
				timeManageInfo.setClockRegionTwoJbox(1);
			}
			if("false".equals(ptpObject.getTcdomain3enable())){
				timeManageInfo.setClockRegionThreeJbox(0);
			}else{
				timeManageInfo.setClockRegionThreeJbox(1);
			}
			if("false".equals(ptpObject.getTcdomain4enable())){
				timeManageInfo.setClockRegionFourJbox(0);
			}else{
				timeManageInfo.setClockRegionFourJbox(1);
			}
			timeManageInfo.setFollowModel("true".equals(ptpObject.getSlaveonly())?238:237);//跟随模式
			timeManageInfo.setClockRegionDelay(UiUtil.getCodeByValue("passThroughClockDelayMechanism", ptpObject.getTcdelaymechanism()).getId());//遗传延时机制
			timeManageInfo.setTodsendTime(UiUtil.getCodeByValue("todTransmissionTimeType", ptpObject.getTodtxtype()).getId());
			//状态
			timeManageInfo.setTimeID(ptpObject.getClkid());
			timeManageInfo.setTimeType(ptpObject.getClocktype());
			timeManageInfo.setFtimeID(ptpObject.getPrntid());
			timeManageInfo.setFtimePort(ptpObject.getMeanpathdelay());
			timeManageInfo.setLeapNumber(ptpObject.getStepsremoved());
			timeManageInfo.setSystemVarianceValue(ptpObject.getTimesoffset());
			timeManageInfo.setTodState(ptpObject.getCurrenttodsel());
			timeManageInfo.setzTimeID(ptpObject.getGmclkid());
			timeManageInfo.setzTimeTpye(ptpObject.getGmclockclass());
			timeManageInfo.setzTimePrecision(ptpObject.getGmclockaccuracy());
			timeManageInfo.setzTimeVariance(ptpObject.getGmvariance());
			timeManageInfo.setzTimePriorOne(ptpObject.getGmprio1());
			timeManageInfo.setzTimePriorTwo(ptpObject.getGmprio2());
//			SynchroUtil.PtpSiteSynchro(timeManageInfo, siteId);
		}
		return timeManageInfo;
	}
}
