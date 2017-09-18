package com.nms.corba.test.CorbaTest_sy;

import globaldefs.ProcessingFailureException;

import java.util.Date;

import org.omg.CORBA.StringHolder;

import timeMgr.TimeMgr_I;
import timeMgr.TimeMgr_IHelper;

import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

/**
 * 厂商时间
 * @author sy
 *
 */
public class Test_TimeMgr extends TestBase {

	TimeMgr_I timeMgr_I=null;
	public Test_TimeMgr(){
		super();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new Date());
		Test_TimeMgr time=new Test_TimeMgr();
		time.setEMSTime();
		//测试完成！！
		System.out.println("测试完成！！");
	}
	/**
	 * 查询
	 */
	public void getEMSTime(){
		this.getManager();
		StringHolder arg1=new StringHolder();
		try {
			timeMgr_I.getEMSTime(getName(), arg1);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 设置网元厂商时间
	 */
	public void setEMSTime(){
		this.getManager();
		try {
			timeMgr_I.setEMSTime(getName(), "1999-11-11 12:12:12");
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
			super.connnect.emsSession.getManager("Time", common_IHolder);
			timeMgr_I=TimeMgr_IHelper.narrow(common_IHolder.value);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}

}
