package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.path.pw.PwNniInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.model.ptn.path.pw.PwNniInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.PwBufferCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.wh.PwBufferWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class PwBufferDispatch extends DispatchBase implements DispatchInterface{

	@Override
	public String excuteDelete(Object object) throws RemoteException, Exception {
		
		return null;
	}

	@Override
	public String excuteInsert(Object object) throws RemoteException, Exception {
		
		return null;
	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		PwBufferWHServiceImpl pwBufferWHServiceImpl=null;
		PwBufferCXServiceImpl pwBufferCXServiceImpl=null;		
		String cx_result = null;
		String wu_result=null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		List<PwNniInfo> pwNniInfoList = null;
		List<PwNniInfo> pwNniList=null;
		PwNniInfoService_MB pwNniService=null;
		List<PwNniInfo> beforePwNniList=null;//修改之前的 ，做回滚用
		try{
			if(object instanceof List){
				pwNniInfoList = (List<PwNniInfo>) object;	
				beforePwNniList=new ArrayList<PwNniInfo>();
				pwNniService=(PwNniInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwNniBuffer);			
				if(pwNniInfoList!=null&&pwNniInfoList.size()>0){	
					pwNniList=new ArrayList<PwNniInfo>();
					//遍历集合
					SiteUtil siteUtil=new SiteUtil();
					for(PwNniInfo info:pwNniInfoList){
						/**
						 * 取出真是网元，并且pw为-激活状态的
						 */						
						if(0==siteUtil.SiteTypeUtil(info.getSiteId())&&info.getPwStatus() == EActiveStatus.ACTIVITY.getValue()){
							beforePwNniList.addAll(pwNniService.select(info));
							pwNniList.add(info);
						}else{//未激活的直接更新DB；
							pwNniService.updateVlan(info);							
						}
					}
					pwNniService.updateVlan(pwNniList);
					//下发设备的 集合
					if(pwNniList!=null&&pwNniList.size()>0){
						pwBufferWHServiceImpl = new PwBufferWHServiceImpl();
						wu_result = pwBufferWHServiceImpl.excutionUpdate(pwNniList);
						pwBufferCXServiceImpl = new PwBufferCXServiceImpl();
						cx_result = pwBufferCXServiceImpl.excutionUpdate(pwNniList);

						// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
						if (ResultString.CONFIG_SUCCESS.equals(wu_result) && ResultString.CONFIG_SUCCESS.equals(cx_result)) {
							
							result = ResultString.CONFIG_SUCCESS;
						} else {
							/**
							 * 陈晓操作成功，回滚到修改之前的信息，返回武汉错误信息
							 */
							if (ResultString.CONFIG_SUCCESS.equals(cx_result)) {
								pwBufferCXServiceImpl.excutionUpdate(beforePwNniList);
								return wu_result;
							}else if(ResultString.CONFIG_SUCCESS.equals(wu_result)){
								pwBufferWHServiceImpl.excutionUpdate(beforePwNniList);
								return cx_result;
							}
						}
					}else{
						return ResultString.CONFIG_SUCCESS;
					}
				}
			}
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			pwBufferWHServiceImpl = null;
			pwBufferCXServiceImpl = null;
			cx_result = null;
			wu_result = null;
			pwNniInfoList = null;
			pwNniList = null;
			UiUtil.closeService_MB(pwNniService);
			beforePwNniList = null;// 修改之前的 ，做回滚用
		}		
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		} else {
			return result;
		}
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
