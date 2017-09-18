package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.SlotResetWHServiceImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class SlotResetDispatch extends DispatchBase implements DispatchInterface{
	
	public String excuteInsert(Object object) throws Exception {
		
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		CardInst slotInst=null;
		try {
			if (object == null) {
				throw new Exception("slotInst is null");
			}
			slotInst=(CardInst) object;
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			if(siteUtil.SiteTypeUtil(slotInst.getSiteId()) != 0){
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(slotInst.getSiteId());
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			
			manufacturer = super.getManufacturer(slotInst.getSiteId());

			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new SlotResetWHServiceImpl();
			} else {
//				operationServiceI = new AcCXServiceImpl();
			}
			result = operationServiceI.excutionInsert(slotInst);
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
	public String excuteUpdate(Object object) throws RemoteException, Exception {
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
