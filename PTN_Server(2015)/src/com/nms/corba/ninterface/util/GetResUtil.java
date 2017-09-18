package com.nms.corba.ninterface.util;

import java.util.List;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.ServiceFactory;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class GetResUtil {

	public static List<SiteInst> getAllDATAXManagedElements() throws Exception {
		SiteService_MB ss = null;
		try {	
			//初始化服务
			if(ConstantUtil.serviceFactory == null){
				ServiceFactory serviceFactory = new ServiceFactory();
//				Properties properties = new Properties();
//				properties.put(ServiceFactory.HOSTNAME, "127.0.0.");
//				properties.put(ServiceFactory.PTNUSER, "admin");
//				serviceFactory.startup(properties);
				ConstantUtil.serviceFactory = serviceFactory;
			}
			
			// 初始化site服务层
			ss = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			List<SiteInst> siteInstList = ss.select();
			return siteInstList;
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,LoginConfirmation.class);
		} finally {
			UiUtil.closeService_MB(ss);
		}
		return null;

	}

}
