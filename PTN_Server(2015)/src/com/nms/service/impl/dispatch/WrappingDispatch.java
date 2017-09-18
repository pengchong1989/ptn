package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.path.protect.LoopProtectInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.model.ptn.path.protect.WrappingProtectService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.WrappingCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.WrappingWHServiceImpl;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class WrappingDispatch extends DispatchBase implements DispatchInterface {
	
	/**
	 * 新建
	 */
	@SuppressWarnings("unchecked")
	public String excuteInsert(Object object) throws Exception {
		LoopProtectInfo loopProtectInfo = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		WrappingProtectService_MB wrappingProtectService = null;
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		int insertResult=0;
		List<LoopProtectInfo> protectInfos = null;
		try {
			wrappingProtectService = (WrappingProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WRAPPINGPROTECT);
			protectInfos = (List<LoopProtectInfo> )object;
			
			//插入数据
			insertResult = wrappingProtectService.insert(protectInfos);
			
			if(protectInfos.get(0).getActiveStatus()== EActiveStatus.ACTIVITY.getValue()){
			// 下发武汉的
				int manufacturer = super.getManufacturer(protectInfos.get(0).getSiteId());
				if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
					result_wh = "配置成功";
					// 下发晨晓的
					operationServiceI_cx = new WrappingCXServiceImpl();
					result_cx = operationServiceI_cx.excutionInsert(protectInfos);
				}else{
					result_cx = "配置成功";
					operationServiceI_wh = new WrappingWHServiceImpl();
					result_wh = operationServiceI_wh.excutionInsert(protectInfos);
				}
					// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
					if (insertResult>0&&ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
						result = ResultString.CONFIG_SUCCESS;
						
					} else {
						// 删除晨晓和武汉的数据
						protectInfos = new ArrayList<LoopProtectInfo>();
						protectInfos.add(loopProtectInfo);
						operationServiceI_wh.excutionDelete(protectInfos);
						// 如果晨晓的成功。 错误消息返回武汉的
						operationServiceI_cx.excutionDelete(protectInfos);
						if (ResultString.CONFIG_SUCCESS.equals(result_cx)) {
							result = result_wh;
						} else {
							result = result_cx;
						}
					}
			}else{
				result = ResultString.CONFIG_SUCCESS;
			}
		} catch (BusinessIdException e) {
			return e.getMessage();
		} catch (Exception e) {
			wrappingProtectService.deleteByLoopId(protectInfos);
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(wrappingProtectService);
			operationServiceI_wh = null;
			operationServiceI_cx = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			String str = getNotOnlineSiteIdNames(protectInfos);
			if(str.equals("")){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
		} else {
			return result;
		}
	}
	
	/**
	 * 修改
	 */
	@SuppressWarnings("unchecked")
	public String excuteUpdate(Object object) throws Exception {
		int insertResult=0;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		WrappingProtectService_MB wrappingProtectService = null;
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		List<LoopProtectInfo> protectInfos = null;
		try {
			wrappingProtectService = (WrappingProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WRAPPINGPROTECT);
			protectInfos = (List<LoopProtectInfo> )object;
			//如果是激活
			if(protectInfos.get(0).getActiveStatus()== EActiveStatus.ACTIVITY.getValue()){
				//修改数据库
				insertResult = wrappingProtectService.update(protectInfos);
				int manufacturer = super.getManufacturer(protectInfos.get(0).getSiteId());
				if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
					// 下发晨晓的
					result_wh = ResultString.CONFIG_SUCCESS;
					operationServiceI_cx = new WrappingCXServiceImpl();
					result_cx = operationServiceI_cx.excutionUpdate(protectInfos);
				}else{
					// 下发武汉的
					result_cx = ResultString.CONFIG_SUCCESS;
					operationServiceI_wh = new WrappingWHServiceImpl();
					result_wh = operationServiceI_wh.excutionUpdate(protectInfos);
				}
				// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
				if (0<insertResult&&ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
					result = ResultString.CONFIG_SUCCESS;
				} else {
					result = result_cx;
					result = result_wh;
				}
			}
		} catch (BusinessIdException e) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_BUSINESSID_NULL);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(wrappingProtectService);
			operationServiceI_wh = null;
			operationServiceI_cx = null;
			result_wh = null;
			result_cx = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			String str = getNotOnlineSiteIdNames(protectInfos);
			if(str.equals("")){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
		} else {
			return result;
		}
	}
	
	/**
	 * 删除
	 */
	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception{
		List<LoopProtectInfo> protectInfos = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		WrappingProtectService_MB wrappingProtectService = null;
		List<LoopProtectInfo> deleteLoopprotetc = null;
		try {
			protectInfos = (List<LoopProtectInfo>)object;
			wrappingProtectService = (WrappingProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WRAPPINGPROTECT);
		
			//获得删除相关的环成员
			deleteLoopprotetc = getDeleteLoopProtectInfo(protectInfos);
			//操作数据库
			wrappingProtectService.deleteByLoopId(deleteLoopprotetc);
			if (protectInfos != null && protectInfos.size() > 0 ) {
				if(protectInfos.get(0).getActiveStatus()== EActiveStatus.ACTIVITY.getValue()){
					int manufacturer = super.getManufacturer(protectInfos.get(0).getSiteId());
					if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
						// 下发晨晓的
						result_wh = ResultString.CONFIG_SUCCESS;
						operationServiceI_cx = new WrappingCXServiceImpl();
						result_cx = operationServiceI_cx.excutionDelete(deleteLoopprotetc);
					}else{
						// 下发武汉的
						result_cx = ResultString.CONFIG_SUCCESS;
						operationServiceI_wh = new WrappingWHServiceImpl();
						result_wh = operationServiceI_wh.excutionDelete(deleteLoopprotetc);
						}
					if (ResultString.CONFIG_SUCCESS.equals(result_cx) || ResultString.CONFIG_SUCCESS.equals(result_wh)) {
						result = ResultString.CONFIG_SUCCESS;
					}
				}else {
					result = ResultString.CONFIG_SUCCESS;
				}
				
			}else {
				throw new Exception("objectList is null");
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			protectInfos = null;
			UiUtil.closeService_MB(wrappingProtectService);
			operationServiceI_cx = null;
			operationServiceI_wh = null;
			result_wh = null;
			result_cx = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			String str = getNotOnlineSiteIdNames(deleteLoopprotetc);
			if(str.equals("")){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
		} else {
			return result;
		}
	}
	
	/**
	 * 获得删除相关的环成员
	 * @param loopProtectInfoList
	 * @return
	 */
	private List<LoopProtectInfo> getDeleteLoopProtectInfo(List<LoopProtectInfo> loopProtectInfoList){
		List<LoopProtectInfo> loopProtectInfos = null;
		WrappingProtectService_MB wrappingProtectService = null;
		try {
			loopProtectInfos = new ArrayList<LoopProtectInfo>();
			wrappingProtectService = (WrappingProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WRAPPINGPROTECT);
			for(LoopProtectInfo loopProtectInfo : loopProtectInfoList){
				LoopProtectInfo info = new LoopProtectInfo();
				info.setLoopId(loopProtectInfo.getLoopId());
				for(LoopProtectInfo info2 : wrappingProtectService.select(info)){
					loopProtectInfos.add(info2);
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(wrappingProtectService);
		}
		return loopProtectInfos;
	}
	/**
	 * 同步
	 * @param siteId   网元ID
	 * @throws Exception
	 */
	public String synchro(int siteId) throws Exception {
		OperationServiceI operationServiceI = null;
		LoopProtectInfo loopProtectInfo = new LoopProtectInfo();
		loopProtectInfo.setSiteId(siteId);
		try {
			SiteUtil siteUtil=new SiteUtil();
			if(0==siteUtil.SiteTypeUtil(siteId)){
				if (super.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()) {
			//		operationServiceI = new AcCXServiceImpl();
				} else {
					operationServiceI = new WrappingCXServiceImpl();
				}
				return (String)operationServiceI.synchro(siteId);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return ResultString.QUERY_FAILED;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private String getNotOnlineSiteIdNames(List<LoopProtectInfo> loopProtectInfoList) throws Exception {
		List<Integer> siteIds = new ArrayList<Integer>();
		String str = "";
		for (LoopProtectInfo loopProtectInfo : loopProtectInfoList) {
			if (!siteIds.contains(loopProtectInfo.getSiteId())) {
				siteIds.add(loopProtectInfo.getSiteId());
			}
		}
		WhImplUtil whImplUtil = new WhImplUtil();
		str = whImplUtil.getNeNames(siteIds);
		return str;
	}
}
