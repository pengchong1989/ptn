package com.nms.corba.ninterface.impl.resource.proxy;

import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

import java.util.List;

import com.nms.db.bean.system.user.UserInst;
import com.nms.model.system.user.UserInstServiece_Mb;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.UiUtil;

public class CorbaSecurityMgrProxy {

	/**
	 * 修改用户口令
	 * @param userName 用户名
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @param confirmPassword 确定新密码
	 * @return true 修改成功 false 反之
	 * @throws ProcessingFailureException
	 */
	public void modifyPassword(String userName, String oldPassword,
			String newPassword, String confirmPassword)
			throws ProcessingFailureException {
		//判断输入的用户名
		if (null == userName ||"".equals(userName.trim())) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"用户名错误");
		}
		//密码的判断
		if ( null ==oldPassword || null == newPassword || null == confirmPassword
				|| "".equals(oldPassword.trim()) || "".equals(newPassword.trim()) || "".equals(confirmPassword.trim())) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"密码错误");
		}
		//新密码不为空并且和确认密码相同
		if (!newPassword.equals(confirmPassword)) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"新和确认密码不一致 ");
		}
		modifyPassword(userName,oldPassword,newPassword);
	}
	
	/**
	 * 查询数据库修改用户密码
	 * @param userName 用户名
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @return true 修改成功
	 * @throws ProcessingFailureException 
	 */
	private void modifyPassword(String userName, String oldPassword,
			String newPassword) throws ProcessingFailureException {
		
		UserInst userInst = null; // 用户对象
		UserInstServiece_Mb userInstServiece = null;
		List<UserInst> userInfoList = null; // 用户集合
		
		try {
			userInstServiece = (UserInstServiece_Mb) ConstantUtil.serviceFactory.newService_MB(Services.UserInst);
			userInst = new UserInst();
			userInst.setUser_Name(userName);
			userInfoList = userInstServiece.select(userInst);
			if (null == userInfoList || userInfoList.size() == 0) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"无此用户");
			}
			userInst = userInfoList.get(0);
			if (!userInst.getUser_Pass().equals(oldPassword)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"用户名密码错误");
			}
			userInst.setUser_Pass(newPassword);
			userInstServiece.saveOrUpdate(userInst);
		}  catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"modifyPassword ex.");
		} finally {
			UiUtil.closeService_MB(userInstServiece);
		}
	}

}
