package com.nms.ui.ptn.performance.util;

import com.nms.db.enums.EObjectType;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.performance.model.CurrentPerformanceFilter;

public class PerformanceOfflineUitl {
	/**
	 * 判断是否为离线网元
	 * @return
	 */
	public String getPerformanceResult(CurrentPerformanceFilter filter){
		String result = "";
		WhImplUtil whImpleUtil = null;
		try {
			if(filter != null){
				whImpleUtil = new WhImplUtil();
				if(filter.getObjectType() == EObjectType.SITEINST ||filter.getObjectType() == EObjectType.SLOTINST){
					result = whImpleUtil.getNeNames(filter.getSiteInsts());
				}
			}
			if(result.equals("")){
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				result= ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+result+ResultString.NOT_ONLINE_SUCCESS;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}
}
