package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EActiveStatus;
import com.nms.model.ptn.SiteRoateService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.cx.RotateCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.wh.ProtectRorateWHServicveImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * tunnel倒换调度类：将武汉，陈晓一起处理                                      修改时间 ----------2014-4-1
 * @author sy
 *
 */
public class ProtectRotateDispatch implements DispatchInterface{
	
	/**
	 * 保护强制倒换
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public String excuteUpdate(Object object) throws Exception{
		List<SiteRoate> siteRoateList=null;
		RotateCXServiceImpl cx_roate=null;
		ProtectRorateWHServicveImpl wh_roate=null;
		String wh_result="";
		String cx_result="";
		String result =  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		SiteRoateService_MB siteRoateService=null;
		SiteRoate siteRoate=null;
		List<SiteRoate> beforeRoateList=null;
		List<SiteRoate> roteList=null;
		try {
			siteRoateList=(List) object;
			beforeRoateList=new ArrayList<SiteRoate>();
			roteList=new ArrayList<SiteRoate>();
			siteRoateService=(SiteRoateService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITEROATE);
//			//虚拟网元操作
//			String siteCheck =(String) SiteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_ROTATE,tunnel);
//			if(null!=siteCheck){
//				return siteCheck;
//			}
			//查找修改之前的 数据，做回滚用
			roteList=this.siteRoateUpdate(siteRoateList);
			//下发晨晓的
			if(roteList!=null&&roteList.size()>0){
				cx_roate=new RotateCXServiceImpl();
				cx_result = cx_roate.excutionUpdate(roteList);
				wh_roate=new ProtectRorateWHServicveImpl();
				wh_result=wh_roate.excutionUpdate(roteList);
				// 如果两次下发都成功。就插入直接返回 否则获取失败消息。 并回滚已经成功的
				if (ResultString.CONFIG_SUCCESS.equals(wh_result)
						&& ResultString.CONFIG_SUCCESS.equals(cx_result)) {
					siteRoateService.update(siteRoateList);
					result = ResultString.CONFIG_SUCCESS;
				} else {
					/**
					 * 陈晓操作成功，回滚到修改之前的信息，返回武汉错误信息
					 */
					if (ResultString.CONFIG_SUCCESS.equals(cx_result)) {
						cx_roate.excutionUpdate(beforeRoateList);
						return wh_result;
					}else if(ResultString.CONFIG_SUCCESS.equals(wh_result)){
						wh_roate.excutionUpdate(beforeRoateList);
						return cx_result;
					}
				}
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteRoateService);
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
	
	
	
	/**
	 * tunnel转为倒换对象，以便存库
	 * @param tunnel
	 * @return 
	 * @return
	 * @throws Exception 
	 */
	private List<SiteRoate> siteRoateUpdate(List<SiteRoate> siteRoateList) throws Exception {
		List<SiteRoate> roteList=null;
		SiteRoate siteRoate=null;
		SiteRoateService_MB siteRoateService=null;
		List<SiteRoate> beforeRoateList=null;
		beforeRoateList=new ArrayList<SiteRoate>();
		siteRoateList=(List) siteRoateList;
		roteList=new ArrayList<SiteRoate>();
		try {
			siteRoateService=(SiteRoateService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITEROATE);
			if(siteRoateList!=null&&siteRoateList.size()>0){
				SiteUtil siteUtil=new SiteUtil();
			 if(siteRoateList.get(0).getType().equals("tunnel")){
				for(SiteRoate rote:siteRoateList){
					if(0==siteUtil.SiteTypeUtil(rote.getSiteId())&&rote.getTunnel().getTunnelStatus()==EActiveStatus.ACTIVITY.getValue()){
						roteList.add(rote);
						siteRoate=siteRoateService.select(rote);	
						siteRoate.setTunnel(rote.getTunnel());
						siteRoate.setSiteRoate(rote.getSiteRoate());
						beforeRoateList.add(siteRoate);
					}else{
						siteRoateService.update(rote);
					}					
				}
			 }else if(siteRoateList.get(0).getType().equals("pw")){
				 for(SiteRoate rote:siteRoateList){
						if(0==siteUtil.SiteTypeUtil(rote.getSiteId())&&rote.getDualInfo().getActiveStatus()==EActiveStatus.ACTIVITY.getValue()){
							roteList.add(rote);
							siteRoate=siteRoateService.select(rote);	
							siteRoate.setDualInfo(rote.getDualInfo());
							siteRoate.setSiteRoate(rote.getSiteRoate());
							beforeRoateList.add(siteRoate);
						}else{
							siteRoateService.update(rote);
						}					
					} 
			 }
			}
		}catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteRoateService);
		}
		
		return roteList; 
	  
	}
	/**
	 * tunnel转为倒换对象，以便存库
	 * @param tunnel
	 * @return
	 * @throws Exception
	 */
	public List<SiteRoate> getRoate(Tunnel tunnel)throws Exception{
		List<SiteRoate> siteRoateList=null;
		SiteRoate siteRoate=null;
		try{
			siteRoate=new SiteRoate();
			siteRoate.setType("tunnel");
			siteRoate.setTypeId(tunnel.getTunnelId());
			siteRoateList=new ArrayList<SiteRoate>();
			if(tunnel!=null){
				
				if(tunnel.getASiteId()>0){
					siteRoate.setSiteId(tunnel.getASiteId());
					siteRoate.setRoate(tunnel.getRotate_a());
					siteRoateList.add(siteRoate);
				}
				if(tunnel.getZSiteId()>0){
					siteRoate.setSiteId(tunnel.getZSiteId());
					siteRoate.setRoate(tunnel.getRotate_z());
					siteRoateList.add(siteRoate);
				}
			}
		}catch (Exception e) {
			throw e;
		}finally{
			siteRoate=null;
		}
		return siteRoateList;
		
	}
	/**
	 * 通过tunnel 对象，查找倒换前的tunnel 对象（包括倒换命令值）
	 * @param tunnel
	 * @return
	 * @throws Exception
	 */
	public Tunnel getBeforeTunnel(Tunnel tunnel) throws Exception{
		Tunnel info;
		SiteRoate siteRoate=null;
		SiteRoateService_MB siteRoateService=null;
		try{
			info=new Tunnel();
			siteRoateService=(SiteRoateService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITEROATE);
			siteRoate=new SiteRoate();
			siteRoate.setType("tunnel");
			siteRoate.setTypeId(tunnel.getTunnelId());
			if(tunnel.getASiteId()>0){
				siteRoate.setSiteId(tunnel.getASiteId());
				siteRoate=	siteRoateService.select(siteRoate);
				info.setRotate_a(siteRoate.getRoate());
				
			}
			if(tunnel.getZSiteId()>0){
				siteRoate.setSiteId(tunnel.getZSiteId());
				siteRoate=	siteRoateService.select(siteRoate);
				info.setRotate_z(siteRoate.getRoate());
			}
		}catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteRoateService);
		}
		return info;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
