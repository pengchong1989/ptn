package com.nms.corba.test.CorbaTest_sy;

import globaldefs.ProcessingFailureException;
import clockSource.ClockSourceList_THolder;
import clockSource.ClockSource_I;
import clockSource.ClockSource_IHelper;

import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

/**
 * 测试时钟
 * @author sy
 *
 */
public class Test_ClockSource_Impl extends TestBase{
	ClockSource_I clockSource=null;
	public Test_ClockSource_Impl(){
		super();
	}
	
	public static void main(String args[]){
		
		Test_ClockSource_Impl clockSource=new Test_ClockSource_Impl();
		clockSource.getManager();
		clockSource.getMEClockSource();
		System.out.println("测试完成");
	}
	/**
	 * 通过网元查询时钟源
	 */
	public void getMEClockSource(){
		
		
		try {
			ClockSourceList_THolder clockSourceList=new ClockSourceList_THolder();
			
			clockSource.getMEClockSource(getName(), clockSourceList);
			System.out.print("");
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 修改时钟源
	 */
	public void setUserLabel(){
		
	}
	/**
	 * 获取管理者信息
	 */
	public void getManager(){
		Common_IHolder common_IHolder=new Common_IHolder();
		try {
			super.connnect.emsSession.getManager("ClockSource", common_IHolder);
			clockSource=ClockSource_IHelper.narrow(common_IHolder.value);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
