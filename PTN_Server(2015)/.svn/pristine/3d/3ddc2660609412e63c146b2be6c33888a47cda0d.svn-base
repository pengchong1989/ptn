package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.ptn.path.protect.DualProtect;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EActiveStatus;
import com.nms.drivechenxiao.service.bean.protocols.DualObject;
import com.nms.model.ptn.port.DualProtectService_MB;
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
 * 双规保护 转换类
 * @author dzy
 *
 */
public class DualCXServiceImpl extends CXOperationBase implements OperationServiceI {
	/**
	 * 添加双规保护
	 * 
	 * @param object  双规保护 Bean
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	@Override
	public String excutionInsert(Object object) throws Exception, BusinessIdException {
		OperationObject operationObject = null;
		DualProtect dualProtect = null;
		String result = null;
		int mspId = 0;
		DualProtectService_MB dualProtectService = null;
		try {
			dualProtectService = (DualProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALPROTECTSERVICE);
			dualProtect = (DualProtect) object;
			if(!dualProtect.isDataDownLoad()){ //如果是数据下载业务不进行数据库操作
				//保存数据
				mspId = dualProtectService.saveOrUpdate(dualProtect);
				dualProtect.setId(mspId);
			}
			operationObject = this.convertOperation(operationObject,dualProtect, TypeAndActionUtil.ACTION_INSERT);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				if(!dualProtect.isDataDownLoad()){
					//如果下发设备不成功删除数据
					dualProtectService.delete(dualProtect);
				}
				result = super.getErrorMessage(operationObject);
			}
		} catch (BusinessIdException e) {
			throw e;
		} catch (Exception e) {
			dualProtectService.delete(dualProtect);
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(dualProtectService);
		}
		return result;
	}

	@Override
	/**
	 * 修改双规保护
	 * 
	 * @param object  双规保护 Bean
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public String excutionUpdate(Object object) throws Exception {
		OperationObject operationObject = null;
		DualProtect dualProtect = null;
		String result = null;
		DualProtect dualProtect_before=null;
		DualProtectService_MB dualProtectService = null;
		try {
			dualProtectService = (DualProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALPROTECTSERVICE);
			dualProtect = (DualProtect) object;
			if(!dualProtect.isDataDownLoad()){
				//查询原数据以防下发设备失败
				dualProtect_before = dualProtectService.queryByCondition(dualProtect).get(0);
				//保存数据
				dualProtectService.saveOrUpdate(dualProtect);
			}
			//如果是激活状态下发设备
			if(1==dualProtect.getActiveStatus()){
				operationObject = this.convertOperation(operationObject,dualProtect, TypeAndActionUtil.ACTION_UPDATE);
				super.sendAction(operationObject);
				super.verification(operationObject);
				if (operationObject.isSuccess()) {
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					if(!dualProtect.isDataDownLoad()){
						//如果下发设备把数据修改至改动之前
						dualProtectService.saveOrUpdate(dualProtect_before);
					}
					result = super.getErrorMessage(operationObject);
				}
			}else{
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(dualProtectService);
		}
		return result;
	}

	/**
	 * 删除双规保护
     *	 
	 * @param object  双规保护 Bean
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String excutionDelete(List objectList) throws Exception {
		OperationObject operationObject = null;
		List<DualProtect> dualProtectList = null;
		String result = null;
		DualProtectService_MB dualProtectService = null;
		try {
			dualProtectService = (DualProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALPROTECTSERVICE);
			dualProtectList = objectList;
			for(DualProtect dualProtect:dualProtectList){
				
				dualProtectService.delete(dualProtect);
				//如果是激活状态下发设备
				if(1==dualProtect.getActiveStatus()){
					operationObject = this.convertOperation(operationObject,dualProtect, TypeAndActionUtil.ACTION_DELETE);
					super.sendAction(operationObject);
					operationObject = super.verification(operationObject);
					if (operationObject.isSuccess()) {
						//如果成功删除数据
						dualProtectService.delete(dualProtect);
						result = operationObject.getCxActionObjectList().get(0).getStatus();
					} else {
						result = super.getErrorMessage(operationObject);
					}
				}else{
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(dualProtectService);
		}
		return result;
	}
	
	/**
	 * 同步
	 * 
	 * @param siteId  网元id
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	@Override
	public Object synchro(int siteId) throws Exception {
		String result = null;
		DualProtect dualProtect = null;
		OperationObject operationObject = null;
		DualProtectService_MB dualProtectService = null;
		try {
			dualProtectService = (DualProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALPROTECTSERVICE);
			dualProtect =  new DualProtect();
			dualProtect.setSiteId(siteId);
			operationObject = this.convertOperation(operationObject, dualProtect, TypeAndActionUtil.ACTION_SYNCHRO);
			super.sendAction(operationObject);
			operationObject = super.verification(operationObject);
			if (operationObject.isSuccess()) {
				//修改状态，设置成设备上没有
				dualProtectService.updateActiveStatus(siteId, 2);
				//对数据库进行操作
				this.getDualProtectObject(operationObject.getCxActionObjectList().get(0).getDualObjectList(), siteId);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(dualProtectService);
		}
		return result;
	}
	
	/**
	 * 获得双规保护对象
	 * @param operationObject
	 * @param dualProtect  双规保护对象
	 * @param action 执行方法
	 * @return
	 * @throws Exception
	 */
	public OperationObject convertOperation(OperationObject operationObject, DualProtect dualProtect, String action) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(dualProtect.getSiteId()));
			cxActionObject.setAction(action);
			cxActionObject.setType(TypeAndActionUtil.TYPE_DUALPROTECT);
			cxActionObject.setDualObject((DualObject)this.getDualObject(dualProtect));
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}
	/**
	 * 转换双规保护对象
	 * @param dualProtect   双规保护对象
	 * @return 设备对象
	 * @throws Exception
	 */
	private DualObject getDualObject(DualProtect dualProtect) throws Exception {
		int type = 0;
		DualObject dualObject = new DualObject();
		if(dualProtect.getApsEnable()==0&&dualProtect.getProtectType()==UiUtil.getCodeByValue("MSPPROTECTTYPE", "1").getId()){
			type = 0;
		}else if(dualProtect.getApsEnable()==0&&dualProtect.getProtectType()==UiUtil.getCodeByValue("MSPPROTECTTYPE", "2").getId()){
			type = 1;
		}else if(dualProtect.getApsEnable()==1&&dualProtect.getProtectType()==UiUtil.getCodeByValue("MSPPROTECTTYPE", "1").getId()){
			type = 2;
		}else if(dualProtect.getApsEnable()==1&&dualProtect.getProtectType()==UiUtil.getCodeByValue("MSPPROTECTTYPE", "2").getId()){
			type = 3;
		}
		dualObject.setType(type+"");
		dualObject.setName(dualProtect.getBusinessId()+"");
		dualObject.setWtrtime(super.convertWtrtimeSend(dualProtect.getWaitTime())+"");
		dualObject.setEnaps((dualProtect.getApsEnable()==1?true:false)+"");
		dualObject.setSwmanner(dualProtect.getRotateWay()+"");
		dualObject.getTunnels().add(dualProtect.getBreakoverTunnel().getTunnelId()+"");
		if(null!=dualProtect.getRelevanceTunnelList()&&dualProtect.getRelevanceTunnelList().size()>0){
			for(Tunnel tunnl:dualProtect.getRelevanceTunnelList()){
				dualObject.getTunnels().add(tunnl.getTunnelId()+"");
			}
		}
		
		//DualObject 缺少 恢复模式 和lag
		return dualObject;
	}
	/**
	 * 与数据库同步
	 * 
	 * 
	 * @param dualObjectList  得到的双规保护结果集
	 * 
	 * @param siteId  网元id
	 * 
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void getDualProtectObject(List<DualObject> dualObjectList, int siteId) throws Exception {

		if (null == dualObjectList) {
			throw new Exception("dualObjectList is null");
		}
		DualProtect dualProtect;
		SynchroUtil synchroUtil = new SynchroUtil();
		for (DualObject dualObject : dualObjectList) {
			dualProtect = new DualProtect();
			if("0".equals(dualObject.getType())){
				dualProtect.setProtectType(UiUtil.getCodeByValue("MSPPROTECTTYPE", "1").getId());
				dualProtect.setApsEnable(1);
			}else if("1".equals(dualObject.getType())){
				dualProtect.setProtectType(UiUtil.getCodeByValue("MSPPROTECTTYPE", "2").getId());
				dualProtect.setApsEnable(1);
			}else if("2".equals(dualObject.getType())){
				dualProtect.setProtectType(UiUtil.getCodeByValue("MSPPROTECTTYPE", "1").getId());
				dualProtect.setApsEnable(0);
			}else if("3".equals(dualObject.getType())){
				dualProtect.setProtectType(UiUtil.getCodeByValue("MSPPROTECTTYPE", "2").getId());
				dualProtect.setApsEnable(0);
			}
			dualProtect.setBusinessId(Integer.parseInt(dualObject.getName()));
			dualProtect.setWaitTime(super.convertWtrtimeRead(Integer.parseInt(dualObject.getWtrtime())));
			dualProtect.setApsEnable("true".equals(dualObject.getEnaps())?1:0);
			dualProtect.setRotateWay(Integer.parseInt(dualObject.getSwmanner()));
			dualProtect.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
			//DualObject 缺少 恢复模式 和lag  并且不确定 tunnel是如果 赋值
		
			synchroUtil.dualProtectSynchro(dualProtect, siteId);
		}
	}
}
