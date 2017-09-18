package com.nms.service.impl.cx;


import java.util.List;

import com.nms.db.bean.ptn.clock.FrequencyInfoNeClock;
import com.nms.db.enums.EManufacturer;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.slot.SlotTempObject;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * slotTemp 温度管理
 * @author Administrator
 *
 */
public class SlotTempCXServiceImpl extends CXOperationBase implements OperationServiceI {
	
	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		return null;
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
	//	if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(siteIds)) {
			operationObject = this.getOperationObject(siteIds);
			super.sendAction(operationObject);					
			operationObject = super.verification(operationObject);						
			if (operationObject.isSuccess()) {						
				frequencyInfo_neClock=getFrequencyInfo(operationObject);						
			} 	
	//	}
		operationObject = null;
		return frequencyInfo_neClock;
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
			cxActionObject.setType(TypeAndActionUtil.TYPE_SLOTTEMP);
			cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
			cxActionObject.setCxNeObject(cxneObject);
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			throw e;
		}
		return operationObject;
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
				operationObject = this.convertOperation(operationObject, frequencyInfo_neClock,TypeAndActionUtil.ACTION_UPDATE);					
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

	private OperationObject convertOperation(OperationObject operationObject, FrequencyInfoNeClock frequencyInfo_neClock,String action) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(frequencyInfo_neClock.getSiteId()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_SLOTTEMP);
			cxActionObject.setAction(action);
			cxActionObject.setSlotTempObject((SlotTempObject)this.getSlotTemp(frequencyInfo_neClock));
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}
	/**
	 * *获取陈晓驱动层端口对象 
	 * ClockObject
	 * @param frequencyInfo
	 * @return
	 * @throws Exception
	 */
	public SlotTempObject getSlotTemp(FrequencyInfoNeClock frequencyInfo) throws Exception{
		SlotTempObject slotTempObject=null;
		if (frequencyInfo == null) {
			throw new Exception("frequencyInfo_neClock is null");
		}
		try {
			slotTempObject=new SlotTempObject();
			//属性
			slotTempObject.setTemphighthr("30");
			slotTempObject.setTemplowthr("30");
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
		return slotTempObject;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		// TODO Auto-generated method stub
		return null;
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
				
				SlotTempObject slotTemp =(SlotTempObject) cxActionObject.getSlotTempObject();
				System.out.println(slotTemp.getHisHighTemp());
				System.out.println(slotTemp.getHisLowTemp());
				
			}
		}
			return frequencyInfo;
	}
}
