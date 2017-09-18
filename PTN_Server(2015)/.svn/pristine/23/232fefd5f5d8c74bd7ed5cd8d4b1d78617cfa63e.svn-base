package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.protect.MspProtect;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.ERotateType;
import com.nms.drivechenxiao.service.bean.protocols.MspObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.protect.MspProtectService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * MSP保护 转换类
 * @author dzy
 *
 */
public class MspCXServiceImpl extends CXOperationBase implements OperationServiceI {
	/**
	 * 添加MSP
	 * 
	 * @param object  MSP保护 Bean
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	@Override
	public String excutionInsert(Object object) throws Exception, BusinessIdException {
		OperationObject operationObject = null;
		MspProtect mspProtect = null;
		String result = null;
		int mspId = 0;
		MspProtectService_MB mspProtectService = null;
		try {
			mspProtectService = (MspProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MSPPROTECT);
			mspProtect = (MspProtect) object;
			if(!mspProtect.isDataDownLoad()){ //如果是数据下载业务不进行数据库操作
				//保存数据
				mspId = mspProtectService.saveOrUpdate(mspProtect);
				mspProtect.setId(mspId);
			}
			operationObject = this.convertOperation(operationObject,mspProtect, TypeAndActionUtil.ACTION_INSERT);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				if(!mspProtect.isDataDownLoad()){
					//如果下发设备不成功删除数据
					mspProtectService.delete(mspProtect);
				}
				result = super.getErrorMessage(operationObject);
			}
		} catch (BusinessIdException e) {
			throw e;
		} catch (Exception e) {
			mspProtectService.delete(mspProtect);
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(mspProtectService);
		}
		return result;
	}

	@Override
	/**
	 * 修改msp
	 * 
	 * @param object  msp保护 Bean
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public String excutionUpdate(Object object) throws Exception {
		OperationObject operationObject = null;
		MspProtect mspProtect = null;
		String result = null;
		MspProtect mspProtect_before=null;
		MspProtectService_MB mspProtectService = null;
		try {
			mspProtectService = (MspProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MSPPROTECT);
			mspProtect = (MspProtect) object;
			if(!mspProtect.isDataDownLoad()){
				//查询原数据以防下发设备失败
				mspProtect_before = mspProtectService.select(mspProtect).get(0);
				//保存数据
				mspProtectService.saveOrUpdate(mspProtect);
			}
			//激活操作
			if(mspProtect.getMspStatus()==EActiveStatus.ACTIVITY.getValue()){
				operationObject = this.convertOperation(operationObject,mspProtect, TypeAndActionUtil.ACTION_UPDATE);
				super.sendAction(operationObject);
				super.verification(operationObject);
				if (operationObject.isSuccess()) {
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					if(!mspProtect.isDataDownLoad()){
						//如果下发设备把数据修改至改动之前
						mspProtectService.saveOrUpdate(mspProtect_before);
					}
					result = super.getErrorMessage(operationObject);
				}
			}else{
				//未激活操作
				result = StringKeysTip.TIP_CONFIG_SUCCESS;	
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(mspProtectService);
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	/**
	 * 删除msp
     *	 
	 * @param object  msp保护 Bean
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public String excutionDelete(List objectList) throws Exception {
		OperationObject operationObject = null;
		List<MspProtect> mspProtectList = null;
		String result = null;
		MspProtectService_MB mspProtectService = null;
		try {
			mspProtectService = (MspProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MSPPROTECT);
			mspProtectList = objectList;
			for(MspProtect mspProtect:mspProtectList){
				//激活操作
				if(EActiveStatus.ACTIVITY.getValue()==mspProtect.getMspStatus()){
					operationObject = this.convertOperation(operationObject,mspProtect, TypeAndActionUtil.ACTION_DELETE);
					super.sendAction(operationObject);
					operationObject = super.verification(operationObject);
					if (operationObject.isSuccess()) {
						//如果成功删除数据
						if(!mspProtect.isDataDownLoad()){
							mspProtectService.delete(mspProtect);
						}
						result = operationObject.getCxActionObjectList().get(0).getStatus();
					} else {
						result = super.getErrorMessage(operationObject);
					}
				}else{
					//未激活操作
					mspProtectService.delete(mspProtect);
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);	
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(mspProtectService);
		}
		return result;
	}
	
	/**
	 * 同步
	 * 
	 * @param object  msp保护 Bean
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	@Override
	public Object synchro(int siteId) throws Exception {
		String result = null;
		MspProtect mspProtect = null;
		OperationObject operationObject = null;
		mspProtect =  new MspProtect();
		mspProtect.setSiteId(siteId);
		MspProtectService_MB mspProtectService = null;
		try {
			mspProtectService = (MspProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MSPPROTECT);
			operationObject = this.convertOperation(operationObject, mspProtect, TypeAndActionUtil.ACTION_SYNCHRO);
			super.sendAction(operationObject);
			operationObject = super.verification(operationObject);
			if (operationObject.isSuccess()) {
				//修改状态，设置成设备上没有
				mspProtectService.updateActiveStatus(siteId, EActiveStatus.UNACTIVITY.getValue());
				//对数据库进行操作
				this.getMspProtectObject(operationObject.getCxActionObjectList().get(0).getMapObjectList(), siteId);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(mspProtectService);
		}
		return result;
	}
	/**
	 * 获得msp对象
	 * 
	 * @param siteId	网元ID
	 * @param action 操作
	 * @return
	 * @throws Exception
	 */
	public OperationObject convertOperation(OperationObject operationObject, MspProtect mspProtect, String action) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(mspProtect.getSiteId()));
			cxActionObject.setAction(action);
			cxActionObject.setType(TypeAndActionUtil.TYPE_MSPPROTECT);
			cxActionObject.setMspObject((MspObject)this.getMspObject(mspProtect));
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}
	/**
	 * 转换MspObject对象
	 * @param mspProtect   mspProtect对象
	 * @return 设备对象
	 * @throws Exception
	 */
	private MspObject getMspObject(MspProtect mspProtect) throws Exception {
		
		int type = 0;
		PortInst portInstWork;
		PortInst portInstProtect;
		MspObject mspObject = new MspObject();
		PortService_MB portService = null;
		
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			mspObject.setName(mspProtect.getBusinessId()+"");
			mspObject.setDesc("msp/"+mspProtect.getBusinessId());
			mspObject.setEnaps((mspProtect.getApsEnable()==0?false:true)+"");
			if(mspProtect.getApsEnable()==0&&mspProtect.getProtectType()==UiUtil.getCodeByValue("MSPPROTECTTYPE", "1").getId()){
				type = 0;
			}else if(mspProtect.getApsEnable()==0&&mspProtect.getProtectType()==UiUtil.getCodeByValue("MSPPROTECTTYPE", "2").getId()){
				type = 1;
			}else if(mspProtect.getApsEnable()==1&&mspProtect.getProtectType()==UiUtil.getCodeByValue("MSPPROTECTTYPE", "1").getId()){
				type = 2;
			}else if(mspProtect.getApsEnable()==1&&mspProtect.getProtectType()==UiUtil.getCodeByValue("MSPPROTECTTYPE", "2").getId()){
				type = 3;
			}
			mspObject.setType(type+"");
			if(mspProtect.getRotateOrder()!=-1){
				mspObject.setApscmd(ERotateType.forms(mspProtect.getRotateOrder()).toString());
			}
			
			mspObject.setWtrtime(super.convertWtrtimeSend(mspProtect.getWaitTime())+"");
			mspObject.setHoldofftime(super.convertDelaytimeSend(mspProtect.getWaitTime())+"");
			mspObject.setSdthreshold((mspProtect.getSdEnable()==1?true:false)+"");
			if(mspProtect.getSfPriority()==UiUtil.getCodeByValue("HIGHLOW", "1").getId()){
				mspObject.setSfhighpri("true");
			}else{
				mspObject.setSfhighpri("false");
			}
			if(mspProtect.getSdPriority()==UiUtil.getCodeByValue("HIGHLOW", "1").getId()){
				mspObject.setSdhighpri("true");
			}else{
				mspObject.setSdhighpri("false");
			}
			portInstWork = new PortInst();
			portInstProtect = new PortInst();
			if(0!=mspProtect.getWorkPortId()){
				portInstWork.setPortId(mspProtect.getWorkPortId());
				portInstWork = portService.select(portInstWork).get(0);
				mspObject.setWorkport(portInstWork.getPortName());
			}
			if(0!=mspProtect.getProtectPortId()){
				portInstProtect.setPortId(mspProtect.getProtectPortId());
				portInstProtect = portService.select(portInstProtect).get(0);
				mspObject.setPrtport(portInstProtect.getPortName());
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
		return mspObject;
	}
	/**
	 * 与数据库同步
	 * 
	 * 
	 * @param mspObjectList  得到的msp结果集
	 * 
	 * @param siteId  网元id
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void getMspProtectObject(List<MspObject> mspObjectList, int siteId) throws Exception {

		if (null == mspObjectList) {
			throw new Exception("mspObjectList is null");
		}
		PortInst portInstWork;
		PortInst portInstProtect;
		MspProtect mspProtect;
		SynchroUtil synchroUtil = new SynchroUtil();
		PortService_MB portService = null;
		
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			for (MspObject mspObject : mspObjectList) {
				portInstWork = new PortInst();
				portInstProtect = new PortInst();
				portInstWork.setSiteId(siteId);
				portInstWork.setPortName(mspObject.getWorkport());
				portInstWork = portService.select(portInstWork).get(0);
				portInstProtect.setSiteId(siteId);
				portInstProtect.setPortName(mspObject.getPrtport());
				portInstProtect = portService.select(portInstProtect).get(0);
				mspProtect = new MspProtect();
				mspProtect.setBusinessId(Integer.parseInt(mspObject.getName()));
				if("0".equals(mspObject.getType())){
					mspProtect.setProtectType(UiUtil.getCodeByValue("MSPPROTECTTYPE", "1").getId());
					mspProtect.setApsEnable(1);
				}else if("1".equals(mspObject.getType())){
					mspProtect.setProtectType(UiUtil.getCodeByValue("MSPPROTECTTYPE", "2").getId());
					mspProtect.setApsEnable(1);
				}else if("2".equals(mspObject.getType())){
					mspProtect.setProtectType(UiUtil.getCodeByValue("MSPPROTECTTYPE", "1").getId());
					mspProtect.setApsEnable(0);
				}else if("3".equals(mspObject.getType())){
					mspProtect.setProtectType(UiUtil.getCodeByValue("MSPPROTECTTYPE", "2").getId());
					mspProtect.setApsEnable(0);
				}
				mspProtect.setWorkPortId(portInstWork.getPortId());
				mspProtect.setProtectPortId(portInstProtect.getPortId());
				mspProtect.setWaitTime(super.convertWtrtimeRead(Integer.parseInt(mspObject.getWtrtime())));
				mspProtect.setDelayTime(super.convertDelaytimeRead(Integer.parseInt(mspObject.getHoldofftime())));
				mspProtect.setSfPriority("true".equals(mspObject.getSfhighpri())?UiUtil.getCodeByValue("HIGHLOW","1").getId():UiUtil.getCodeByValue("HIGHLOW","2").getId());
				mspProtect.setSdPriority("true".equals(mspObject.getSdhighpri())?UiUtil.getCodeByValue("HIGHLOW","1").getId():UiUtil.getCodeByValue("HIGHLOW","2").getId());
				mspProtect.setSdEnable("true".equals(mspObject.getSdthreshold())?1:0);
				mspProtect.setProtectStatus(mspObject.getApsstat());
				mspProtect.setNowWorkPortId(portInstWork.getPortId());
				mspProtect.setSiteId(siteId);
				mspProtect.setMspStatus(EActiveStatus.ACTIVITY.getValue());
				mspProtect.setRecoveryMode(Integer.parseInt(mspObject.getWtrtime())==0?UiUtil.getCodeByValue("RECOVERYMODE","2").getId():UiUtil.getCodeByValue("RECOVERYMODE","1").getId());
				mspProtect.setName("msp/"+Integer.parseInt(mspObject.getName()));
				if(mspObject.getApscmd()!=null&&!mspObject.getApscmd().equals("")){
					//mspProtect.setRotateOrder(Integer.parseInt(mspObject.getApscmd()));
					mspProtect.setRotateOrder(ERotateType.getByValue(mspObject.getApscmd()));
				}
				synchroUtil.mspProtectSynchro(mspProtect, siteId);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
	}
}
