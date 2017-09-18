package com.nms.corba.test.CorbaTest_sy;

import globaldefs.ProcessingFailureException;
import securityManager.SecurityMgr_I;
import securityManager.SecurityMgr_IHelper;

import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

/**
 * 修改用户口令
 * @author sy
 *
 */
public class Test_SecurityMgr_Impl extends TestBase {
	SecurityMgr_I securityMgr_I=null;
	public Test_SecurityMgr_Impl(){
		super();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test_SecurityMgr_Impl securi=new Test_SecurityMgr_Impl();
		securi.modifyPassword();
		//测试完成！！！！！！！！！！
		System.out.println("测试完成");

	}
	/**
	 * 修改用户口令
	 */
	/**
	 * 修改用户口令
	 * @param userName 用户名
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 * @param confirmPassword 确定新密码
	 * @return true 修改成功 false 反之
	 * @throws ProcessingFailureException
	 */
	public void modifyPassword(){
		this.getManager();
		try {
			securityMgr_I.modifyPassword("songyang", "1234qwer", "1q2w3e4r", "1q2w3e4r");
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 获取管理者信息
	 */
	public void getManager(){
		Common_IHolder common_IHolder=new Common_IHolder();
		try {
			super.connnect.emsSession.getManager("Security", common_IHolder);
			securityMgr_I=SecurityMgr_IHelper.narrow(common_IHolder.value);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}

}
