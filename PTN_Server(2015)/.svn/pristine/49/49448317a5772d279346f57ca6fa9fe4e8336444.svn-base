package com.nms.service.impl.dispatch.rmi.impl;

import java.rmi.RemoteException;
import java.util.List;

import com.champor.license.Feature;
import com.champor.license.Features;
import com.nms.db.bean.system.loginlog.LoginLog;
import com.nms.model.system.loginlog.LoginLogServiece_Mb;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.ServerConstant;
import com.nms.service.impl.dispatch.rmi.RmiInitI;
import com.nms.service.impl.dispatch.rmi.bean.ServiceBean;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

/**
 * 初始化rmi接口实现类
 * 
 * @author kk
 * 
 */
public class RmiInitImpl implements RmiInitI {

	/**
	 * rmi初始化
	 */
	@Override
	public ServiceBean init() throws RemoteException {

		ServiceBean serviceBean = null;
		Features features = null;
		try {
			serviceBean = new ServiceBean();

			// 遍历许可对象，从中获取最大连接数，最大网元数等参数
			features = ServerConstant.features;
			if (null != features) {

				for (Feature feature : features.getFeatureList()) {
					if ("NECount".equals(feature.getName())) { // 最大网元数
						serviceBean.setMaxSiteNumner(Integer.parseInt(feature.getValue()));
					} else if ("ClientCount".equals(feature.getName())) { // 最大连接数
						serviceBean.setMaxConnection(Integer.parseInt(feature.getValue()));
						serviceBean.setDueDate(feature.getExpiresDateStr());
					}
				}
				// 超过最大连接
				if (serviceBean.getMaxConnection() == this.getOnineCount()) {
					// 超过最大连接
					serviceBean.setConnectionResult(2);
				} else {
					// 连接成功
					serviceBean.setConnectionResult(1);
				}

			} else {
				// 没有许可
				serviceBean.setConnectionResult(3);
				
				serviceBean.setConnectionResult(1);
				serviceBean.setMaxConnection(10);
				serviceBean.setMaxSiteNumner(100);
				serviceBean.setDueDate(DateUtil.getDate(DateUtil.FULLTIME));
				}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			features = null;
		}

		return serviceBean;
	}

	/**
	 * 查询当前在线人数
	 * 
	 * @return 在线人数
	 * @throws Exception
	 */
	private int getOnineCount() throws Exception {
		LoginLogServiece_Mb loginLogServiece = null;
		int count = 0;
		List<LoginLog> loginLogList = null;
		try {
			loginLogServiece = (LoginLogServiece_Mb) ConstantUtil.serviceFactory.newService_MB(Services.LOGINLOGSERVIECE);
			loginLogList = loginLogServiece.selectOnLine();
			if (null != loginLogList) {
				count = loginLogList.size();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(loginLogServiece);
		}
		return count;
	}
}
