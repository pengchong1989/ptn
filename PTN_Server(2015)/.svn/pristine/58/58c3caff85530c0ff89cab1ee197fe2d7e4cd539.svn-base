package com.nms.service.impl.dispatch;

import com.nms.db.enums.EManufacturer;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.AcnCXServiceImpl;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * 晨晓acn模块。 因为ccn功能暂时不用，所以此类暂时没用上 
 * @author Administrator
 *
 */
public class AcnDispatch extends DispatchBase {

	/**
	 * 查询出全部的ACN并删除
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public String selectAndDelete(int siteId) throws Exception {
		AcnCXServiceImpl acnCXServiceImpl = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (super.getManufacturer(siteId) == EManufacturer.CHENXIAO.getValue()) {
				acnCXServiceImpl = new AcnCXServiceImpl();
				result = acnCXServiceImpl.selectAndDelete(siteId);
			} else {
				// 武汉
			}

		} catch (Exception e) {
			throw e;
		} finally {
			acnCXServiceImpl = null;
		}
       return result;
	}

}
