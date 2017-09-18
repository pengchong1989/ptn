package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.system.Field;
import com.nms.drive.service.bean.ManagementObject;
import com.nms.drive.service.bean.NE;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.WorkSpace;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.system.FieldService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class AdministrateConfigWHServiceImpl extends WHOperationBase implements OperationServiceI{

	@Override
	public String excutionDelete(List objectList) throws Exception {
		 
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		FieldService_MB fieldService = null;
		List<SiteInst> siteInstList = null;
		ManagementObject managementObject = new ManagementObject();
		List<NE> nelList = new ArrayList<NE>();
		
		OperationObject operationObject = new OperationObject();
		OperationObject operationObjectResult = null;
		OperationObject currectTimeOperationObject = null;
		SiteService_MB siteService = null;
		String manageIp = null;
		
		try {
			Field field = (Field) object;
			List<WorkSpace> workSpaces = new ArrayList<WorkSpace>();
			int number = 1;
			for(String workIP: field.getWorkIP()){
				WorkSpace workSpace = new WorkSpace();
				workSpace.setWorkSpaceIp(workIP);
				workSpace.setWorkSpaceNo(number);
				workSpaces.add(workSpace);
				number++;
			}
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			SiteInst siteinst = new SiteInst();
			siteinst.setFieldID(field.getId());
			siteInstList = siteService.selectByFieldId(siteinst);//通过域id查询该域所有网元，包含子网下的网元
				for(SiteInst siteInst : siteInstList){//遍历该域下所有网元
					if(siteInst.getManufacturer() == 0){
						NE ne = new NE();
						ne.setNEAddr(field.getGroupId()+","+siteInst.getSite_Hum_Id());
						ne.setNEType(siteInst.getIsGateway()==1?1:2);
						ne.setNESwitch(Integer.parseInt(siteInst.getSwich()));
						ne.setNEIp(siteInst.getCellDescribe());
						ne.setNECardReserveType(128);
//						ne.setNEIp(site)
//						ne.setNEEthGateway(ethGateway)
						if(siteInst.getIsGateway()==1){
							managementObject.setNeSwitch(Integer.parseInt(siteInst.getSwich()));
							manageIp = siteInst.getCellDescribe();
							field.setmSiteId(siteInst.getSite_Inst_Id());
						}
						nelList.add(ne);
						siteinst = siteInst;
					}
				}
			managementObject.setManageDataTag(1);
			managementObject.setNes(nelList);
			managementObject.setWorkSpaces(workSpaces);
			long l = System.currentTimeMillis();
			Date date = new Date(l);
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			managementObject.setYear(calendar.get(calendar.YEAR)+"");
			managementObject.setMonth((calendar.get(calendar.MONTH) + 1)+"");
			managementObject.setDay(calendar.get(calendar.DAY_OF_MONTH)+"");
			managementObject.setHour(calendar.get(calendar.HOUR_OF_DAY)+"");
			managementObject.setMinute(calendar.get(calendar.MINUTE)+"");
			managementObject.setSecond(calendar.get(calendar.SECOND)+"");
			operationObject = this.getOperationObject(managementObject,siteinst);
			super.sendAction(operationObject);
			operationObjectResult = super.verification(operationObject);
			if(operationObjectResult.isSuccess()){
				fieldService.saveOrUpdate(field);
				currectTimeOperationObject = getOperationObject(field.getGroupId(),manageIp,"currecttime");
				super.sendAction(currectTimeOperationObject);
				return operationObjectResult.getActionObjectList().get(0).getStatus(); 
			}else{
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(fieldService);
			UiUtil.closeService_MB(siteService);
		}
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		 
		return null;
	}
	public SiteInst getSiteInst(SiteInst siteInst){
		
		return siteInst;
		
	}
	
	private OperationObject getOperationObject(ManagementObject managementObject,SiteInst siteInst) throws Exception {
		OperationObject operationObject=null;
		ActionObject actionObject=null;		
		NEObject neObject = null;
		try {
			operationObject=new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();	
				actionObject=new ActionObject();
				actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				actionObject.setType("AdministrateConfig");
				actionObject.setManagementObject(managementObject);
				neObject = new NEObject();
				neObject = whImplUtil.siteIdToNeObject(siteInst.getSite_Inst_Id());
				actionObject.setNeObject(neObject);
				operationObject.getActionObjectList().add(actionObject);
		
		} catch (Exception e) {
			throw e;
		}finally{
			actionObject=null;
		}
		return operationObject;
	}
	@Override
	public Object synchro(int siteId) {
		return null;
	}
	
	/**
	 * 获取operationobject对象
	 * @param siteId
	 * @return operationObject
	 * @throws Exception
	 */
	private OperationObject getOperationObject(int groupId,String manageIp,String type) throws Exception{
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			
			neObject = new NEObject();
			neObject.setNeAddress(groupId*256+255);
			neObject.setManageIP(manageIp);
			neObject.setL(System.currentTimeMillis());
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType(type);
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			throw e;
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}
	
}
