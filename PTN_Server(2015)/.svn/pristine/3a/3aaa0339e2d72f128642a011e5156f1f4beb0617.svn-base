package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.db.bean.equipment.manager.Upgrade;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.AcCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.wh.SoftwareUpdateWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class SoftwareUpdateDispatch extends DispatchBase implements DispatchInterface{

	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		Upgrade upgrade=null;
		try {
			if (object == null) {
				throw new Exception("upgrade is null");
			}
			upgrade=(Upgrade) object;
			
			manufacturer = super.getManufacturer(upgrade.getSiteId());

			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new SoftwareUpdateWHServiceImpl();
			} else {
				operationServiceI = new AcCXServiceImpl();
			}
			result = operationServiceI.excutionUpdate(upgrade);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
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

}
