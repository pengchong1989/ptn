package com.nms.corba.test.CorbaTest_sy;

import multiLayerSubnetwork.SubnetworkIterator_IHolder;
import multiLayerSubnetwork.SubnetworkList_THolder;

import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import emsMgr.EMSMgr_IHelper;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

/**
 * 子网
 * @author sy
 *
 */
public class Test_EMSMgr_Impl extends TestBase{
	
	EMSMgr_I emsMgr_I=null;
	public Test_EMSMgr_Impl(){
		super();
	}
	public static void main(String args[]){
		Test_EMSMgr_Impl emsMgr=new Test_EMSMgr_Impl();
		System.out.println("--------");
		emsMgr.setAdditionalInfo();
		//emsMgr.setUserLabel();
		//测试完成！！！！！！！！！！！！！！！！！
		System.out.println("测试完成");
	}
	/**
	 * 查询子网信息
	 */
	public void getAllTopLevelSubnetworks(){
		this.getManager();
		SubnetworkList_THolder sList=new SubnetworkList_THolder();
		SubnetworkIterator_IHolder sIt=new SubnetworkIterator_IHolder();
		try {
			emsMgr_I.getAllTopLevelSubnetworks(10, sList, sIt);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 修改子网名称
	 */
	public void setUserLabel(){
		this.getManager();
		try {
			emsMgr_I.setUserLabel(getName(), "666", true);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 修改物理地址
	 */
	public void setAdditionalInfo(){
		this.getManager();
		
		try {
			NVSList_THolder additionalInfo=new NVSList_THolder();
			additionalInfo.value=new NameAndStringValue_T[1];
			additionalInfo.value[0]=new NameAndStringValue_T("macLocation","u102-102");
			emsMgr_I.setAdditionalInfo(getName(), additionalInfo);
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
			super.connnect.emsSession.getManager("EMS", common_IHolder);
			emsMgr_I=EMSMgr_IHelper.narrow(common_IHolder.value);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
