package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.List;
import com.nms.db.bean.equipment.port.TdmLoopInfo;
import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.enums.EManufacturer;
import com.nms.model.ptn.SiteRoateService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.wh.TdmLoopBackWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class TdmLoopBackDispatch extends DispatchBase implements DispatchInterface {
	public String excuteInsert(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		SiteRoateService_MB siteRoateService=null;
		SiteRoate siteRoate=null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			siteRoateService=(SiteRoateService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITEROATE);
			if (object == null) {
				throw new Exception("cardInst is null");
			}
			TdmLoopInfo tdm = (TdmLoopInfo) object;			
		
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			if(siteUtil.isNeOnLine(tdm.getSiteId()))
			{			
				return ResourceUtil.srcStr(StringKeysTip.TIP_UP_DOWN);
			}else{
				siteRoate= new SiteRoate();
				if(tdm.getLoopType()==0){
					siteRoate.setType("TdmLoopLine");
				}else{
					siteRoate.setType("TdmLoopEquip");
				}
				siteRoate.setRoate(tdm.getE1Id());
				siteRoate.setSiteId(tdm.getSiteId());
			    siteRoate.setTypeId(tdm.getLegId());
				if(tdm.getLegId()==4){
					SiteRoate si=new SiteRoate();
					si.setSiteId(tdm.getSiteId());
					si.setType(siteRoate.getType());
					siteRoateService.delete(si);
					for(int i=1;i<5;i++){
						siteRoate.setTypeId(4);
						siteRoate.setRoate(i);
						siteRoate.setSiteRoate(tdm.getSwitchStatus());
						siteRoateService.insert(siteRoate);
					}					
				}else{
					List<SiteRoate> old=siteRoateService.querSiteRoateByRoate(siteRoate);
					if(old!=null && old.size()==1){				
			    		siteRoate.setSiteRoate(tdm.getSwitchStatus());			    		
						siteRoateService.updateSiteRoateTDM(siteRoate);
						
					}else{
						siteRoate.setRoate(tdm.getSwitchStatus());
						siteRoateService.insert(siteRoate);
					}
				}
				
				
			}
			manufacturer = super.getManufacturer(tdm.getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new TdmLoopBackWHServiceImpl();
			} else {
				// operationServiceI = new AcCXServiceImpl();
			}
			result = operationServiceI.excutionInsert(tdm);		   
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(siteRoateService);
		}
		return result;
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
