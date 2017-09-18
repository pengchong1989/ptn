package com.nms.corba.ninterface.util;

import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

import java.util.List;

import com.nms.db.bean.system.loginlog.LoginLog;
import com.nms.db.bean.system.user.UserInst;
import com.nms.model.system.loginlog.LoginLogServiece_Mb;
import com.nms.model.system.user.UserInstServiece_Mb;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.UiUtil;

/**
 * corba登陆时，验证相关信息
 * @author pc
 *
 */
public class LoginConfirmation {
	
	/**
	 * 
	 * 暂时只验证用户名和密码，与数据库数据进行对比
	 * @param userName 用户名
	 * @param password 密码
	 * @return 
	 * @throws ProcessingFailureException 
	 */
	public boolean login(String userName, String password) throws ProcessingFailureException{
		
		UserInstServiece_Mb userInstServiece = null;
		UserInst userInst = null; // 用户对象
		List<UserInst> userInfoList = null; // 用户集合
		try {
			// 验证用户名和密码是否填写
			if (userName.length() == 0) {
				return false;
			}
			
			// 初始化用户服务层
			userInstServiece = (UserInstServiece_Mb) ConstantUtil.serviceFactory.newService_MB(Services.UserInst);
			
			// 根据用户名查询用户
			userInst = new UserInst();
			userInst.setUser_Name(userName);
			userInst.setUser_Pass(password);
			userInfoList = userInstServiece.select(userInst);

			// 如果没有查询到说明账户名不存在
			if (null == userInfoList || userInfoList.size() == 0) {
				return false;
			}
//			checkLoginStatus(userInfoList);
			
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"login ex.");
		} finally{
			UiUtil.closeService_MB(userInstServiece);
		}
		
		return true;
	}

	/**
	 * 验证用户是否登录
	 * @param userInfoList
	 * @return
	 * @throws Exception
	 */
	private void checkLoginStatus(List<UserInst> userInfoList) throws Exception {
		boolean loginflag = false; // 用户是否已经登陆
		LoginLog loginlog;
		// 查询是否为登陆用户
		LoginLogServiece_Mb loginLogServiece = null;
		try {
			loginLogServiece = (LoginLogServiece_Mb)ConstantUtil.serviceFactory.newService_MB(Services.LOGINLOGSERVIECE);
			loginlog = new LoginLog();
			loginlog.setUser_id(userInfoList.get(0).getUser_Id());
			loginflag = loginLogServiece.findLoginLog(loginlog);
			if (loginflag) 
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"User is logged in");
		} catch (Exception e) {
		    throw e;
		}finally{
			UiUtil.closeService_MB(loginLogServiece);
		}
	}
}
