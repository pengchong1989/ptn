package com.nms.service.impl.wh;

import java.util.List;

import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.WHOperationBase;

public class ProtectionWHServiceImpl extends WHOperationBase implements OperationServiceI{

	@SuppressWarnings("unused")
//	private ProtectInfoService protectService=null;
//	{
//		try {
//			protectService=(ProtectInfoService) ConstantUtil.serviceFactory.newService(Services.Protection);
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		}
//	}
	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		return null;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		return null;
	}

	@Override
	public Object synchro(int siteId) {
		return null;
	}
}
