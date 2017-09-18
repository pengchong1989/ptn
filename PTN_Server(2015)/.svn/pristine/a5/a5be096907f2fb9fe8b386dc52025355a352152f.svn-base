package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.List;

import com.nms.db.bean.system.Field;
import com.nms.model.system.FieldService_MB;
import com.nms.model.system.WorkIpsService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.wh.AdministrateConfigWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class AdministrateConfigDispath extends DispatchBase  implements DispatchInterface{

	public String excuteInsert(Object object) throws Exception {

		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		WorkIpsService_MB  workIpsService = null;
		Field field = (Field) object;
		FieldService_MB fieldService = null;
		try {
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			List<Field> fieldList = fieldService.selectByFieldId(field.getId());
			if(fieldList != null && fieldList.size()>0){
				if(fieldList.get(0).getmSiteId() == 0)
				{
					return ResourceUtil.srcStr(StringKeysTip.TIP_NOMSITE);
				}
			}
			workIpsService = (WorkIpsService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WORKIPSSERVICE);
			operationServiceI = new AdministrateConfigWHServiceImpl();
			result = operationServiceI.excutionInsert(object);
			if (ResultString.CONFIG_SUCCESS.equals(result) && field.getWorkIps() != null) {
				workIpsService.saveOrUpdate(field.getWorkIps());
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(workIpsService);
			UiUtil.closeService_MB(fieldService);
		}
		return result;
	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		return null;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public String currectTime(SiteInst siteInst) throws Exception{
//		int manufacturer ;
//		OperationServiceI operationServiceI = null;
//		String result = null;
//		if (siteInst == null) {
//			throw new Exception("acPortInfo is null");
//		}
//
//		try {
//			manufacturer = super.getManufacturer(siteInst.getSite_Inst_Id());
//
//			if (manufacturer == EManufacturer.WUHAN.getValue()) {
//				operationServiceI=new AdministrateConfigWHServiceImpl();
//			} else {
////				operationServiceI = new SiteCXServiceImpl();
//			}
//
//			result = operationServiceI.excutionInsert(siteInst);
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		} finally {
//			operationServiceI = null;
//		}
//		if (ResultString.CONFIG_SUCCESS.equals(result)) {
//			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
//		} else {
//			return result;
//		}
//	}
}
