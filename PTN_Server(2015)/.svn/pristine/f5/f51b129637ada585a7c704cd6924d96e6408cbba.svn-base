package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;

import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.enums.EManufacturer;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.wh.ProtectRorateWHServicveImpl;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class ProtectRorateDispatch extends DispatchBase implements DispatchInterface{

	public String excuteDelete(Object object) throws Exception {
		return null;

	}

//	public String excuteInsert(Object object) throws Exception {
//		ProtectRorateInfo protectRorateInfo = null;
//		String result = null;
//		ProtectRorateInfoService protectRorateService = null;
//		OperationServiceI operationServiceI_wh = null;
//		OperationServiceI operationServiceI_cx = null;
//		ProtectRotateDispatch protectRotateDispatch = null;
//		String result_wh = null;
//		String result_cx = null;
//		List<ProtectRorateInfo> protectRorateInfoList = null;
//		try {
//			protectRorateInfo = (ProtectRorateInfo) object;
//			// 虚拟网元操作
//			String siteCheck = (String) SiteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT, protectRorateInfo);
//			if (null != siteCheck) {
//				return siteCheck;
//			}
//			protectRorateService = (ProtectRorateInfoService) ConstantUtil.serviceFactory.newService(Services.PROTECTRORATE);
//			// 下发武汉的
//			operationServiceI_wh = new ProtectRorateWHServicveImpl();
//			result_wh = operationServiceI_wh.excutionInsert(protectRorateInfo);
//			// 下发晨晓的
//			protectRotateDispatch = new ProtectRotateDispatch();
//			result_cx = "配置成功";
////			result_cx = cxRotate(protectRorateInfo);
//			// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
//			if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
//				result = ResultString.CONFIG_SUCCESS;
//			} else {
//
//				protectRorateInfoList = new ArrayList<ProtectRorateInfo>();
//				protectRorateInfoList.add(protectRorateInfo);
//				// 回滚武汉配置
//				operationServiceI_wh.excutionDelete(protectRorateInfoList);
//				// 如果晨晓的成功。 错误消息返回武汉的
//				if (ResultString.CONFIG_SUCCESS.equals(result_cx)) {
//					// 因为晨晓不是全量数据所以在内部做回滚
//					// operationServiceI_cx.excutionDelete(protectRorateInfoList);
//					result = result_wh;
//				} else {
//					result = result_cx;
//				}
//			}
//
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		} finally {
//			operationServiceI_wh = null;
//			operationServiceI_cx = null;
//			result_wh = null;
//			result_cx = null;
//		}
//		if (ResultString.CONFIG_SUCCESS.equals(result)) {
//			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
//		} else {
//			return result;
//		}
//	}

	public String excuteUpdate(Object object) throws Exception {
		SiteRoate protectRorateInfo = null;
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		
		String result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			protectRorateInfo = (SiteRoate) object;
			// 虚拟网元操作
		//	String siteCheck = (String) SiteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE, protectRorateInfo);
		//	if (null != siteCheck) {
		//		return siteCheck;
		//	}
			manufacturer = super.getManufacturer(protectRorateInfo.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new ProtectRorateWHServicveImpl();
				result = operationServiceI.excutionUpdate(object);
			} else {
			//	result = cxRotate(protectRorateInfo);
				
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			protectRorateInfo = null; 
			operationServiceI = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
	}

//	private String cxRotate(ProtectRorateInfo protectRorateInfo) throws Exception {
//		String result;
//		ProtectRotateDispatch protectRotateDispatch = new ProtectRotateDispatch();
//		TunnelService tunnelService =  (TunnelService) ConstantUtil.serviceFactory.newService(Services.Tunnel);
//		Tunnel tunnel =  new Tunnel();
//		tunnel.setTunnelId(protectRorateInfo.getTunnelId());
//		Tunnel resultTunnel = tunnelService.select(tunnel).get(0);
//		if(protectRorateInfo.getSiteId()==resultTunnel.getASiteId()){
//			if(1==protectRorateInfo.getRecoverMain()){
//				resultTunnel.setRotate_a(ERotateType.FORCESWORK.getValue());
//			}
//			if(1==protectRorateInfo.getForceStand()){
//				resultTunnel.setRotate_a(ERotateType.FORCESPRO.getValue());
//			}
//			if(1==protectRorateInfo.getManpowerStand()){
//				resultTunnel.setRotate_a(ERotateType.MANUALPRO.getValue());
//			}
//			if(1==protectRorateInfo.getClear()){
//				resultTunnel.setRotate_a(ERotateType.CLEAR.getValue());
//			}
//		}else{
//			if(1==protectRorateInfo.getRecoverMain()){
//				resultTunnel.setRotate_z(ERotateType.FORCESWORK.getValue());
//			}
//			if(1==protectRorateInfo.getForceStand()){
//				resultTunnel.setRotate_z(ERotateType.FORCESPRO.getValue());
//			}
//			if(1==protectRorateInfo.getManpowerStand()){
//				resultTunnel.setRotate_z(ERotateType.MANUALPRO.getValue());
//			}
//			if(1==protectRorateInfo.getClear()){
//				resultTunnel.setRotate_z(ERotateType.CLEAR.getValue());
//			}
//		}
//		
//		return result = protectRotateDispatch.excuteUpdate(resultTunnel);
//	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		return null;
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
