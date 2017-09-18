package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.clock.LineClockInterface;
import com.nms.db.enums.EManufacturer;
import com.nms.model.ptn.clock.TimeLineClockInterfaceService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.LineClockInterfaceCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * 线路时钟接口设备调度
 * @author dzy
 *
 */
public class LineClockInterfaceDispatch  extends DispatchBase implements DispatchInterface{
	/**
	 * 修改线路时钟接口
	 * @param clockSource
	 * @return
	 * @throws Exception
	 */
	public String excuteUpdate(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<LineClockInterface> beforeClockSource;
		LineClockInterface lineClockInterface=null;
		TimeLineClockInterfaceService_MB timeLineClockInterfaceService = null;
		try {
			if (object == null) {
				throw new Exception("clockSource is null");
			}
			lineClockInterface=(LineClockInterface) object;
			timeLineClockInterfaceService = (TimeLineClockInterfaceService_MB)ConstantUtil.serviceFactory.newService_MB(Services.TimeLineClockInterfaceService);
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,lineClockInterface);
			if(null!=siteCheck){
				return siteCheck;
			}
			beforeClockSource = new ArrayList<LineClockInterface>(); 
			beforeClockSource = timeLineClockInterfaceService.select(lineClockInterface.getId());
			int clockSourceId = timeLineClockInterfaceService.update(lineClockInterface);
			
			manufacturer = super.getManufacturer(lineClockInterface.getSiteId());

			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				
			} else {
				operationServiceI = new LineClockInterfaceCXServiceImpl();
			}
			result = operationServiceI.excutionUpdate(lineClockInterface);
			if (clockSourceId >0 && ResultString.CONFIG_SUCCESS.equals(result)) {
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				timeLineClockInterfaceService.update(beforeClockSource.get(0));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(timeLineClockInterfaceService);
			operationServiceI = null;
			beforeClockSource=null;
		}
		return result;
	}
	/**
	 * 同步
	 * 
	 * @author dzy
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	@SuppressWarnings("unchecked")
	public String synchro(int siteId) throws Exception {
		OperationServiceI operationServiceI = null;
		int manufacturer = 0;
		try {
			//虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO,siteId);
			if(null!=siteCheck){
				return siteCheck;
			}

			manufacturer = super.getManufacturer(siteId);
			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				operationServiceI = new LineClockInterfaceCXServiceImpl();
			}
			return (String)operationServiceI.synchro(siteId);

		} catch (Exception e) {
			
		}finally{
			operationServiceI = null;
		}
		
		return ResultString.QUERY_FAILED;

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
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
