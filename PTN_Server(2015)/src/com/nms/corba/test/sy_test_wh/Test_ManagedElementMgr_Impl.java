package com.nms.corba.test.sy_test_wh;

import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import managedElement.ManagedElementIterator_IHolder;
import managedElement.ManagedElementList_THolder;
import managedElementManager.ManagedElementMgr_I;
import managedElementManager.ManagedElementMgr_IHelper;

import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

/**
 * 测试网元配置
 * @author sy
 *
 */
public class Test_ManagedElementMgr_Impl extends sy_testBase{
	
	private ManagedElementMgr_I managedElementtMgr_I=null;
	public Test_ManagedElementMgr_Impl(){
		super();
	}
	
	public static void main(String args[]){
		Test_ManagedElementMgr_Impl test=new Test_ManagedElementMgr_Impl();
		test.setAdditionalInfo();
		//test.setAdditionalInfo();
		System.out.println("测试完成！！！");
	}
	private void setTCATPParameter(){
		try {
			this.getManager();
		
			NVSList_THolder additionalInfo=new NVSList_THolder();
			additionalInfo.value=new NameAndStringValue_T[1];
			additionalInfo.value[0]=new NameAndStringValue_T("","test_site_3");
		//	managedElementtMgr_I.setAdditionalInfo(getName(), additionalInfo);
			managedElementtMgr_I.setUserLabel(getName(), "666", true);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 测试修改网元信息
	 */
	private void setAdditionalInfo(){
		try {
			this.getManager();
		
			NVSList_THolder additionalInfo=new NVSList_THolder();
			additionalInfo.value=new NameAndStringValue_T[1];
			additionalInfo.value[0]=new NameAndStringValue_T("NativeEMSName","700a_201");
			managedElementtMgr_I.setAdditionalInfo(getName(), additionalInfo);
			//managedElementtMgr_I.setUserLabel(getName(), "test_site_3", false);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 测试  查询所有网元
	 */
	private void getAllManagedElements(){	
		try {
			this.getManager();
			ManagedElementList_THolder me_THolder=new ManagedElementList_THolder();
			ManagedElementIterator_IHolder meIt=new ManagedElementIterator_IHolder();
			managedElementtMgr_I.getAllManagedElements(10, me_THolder, meIt);
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
			super.connnect.emsSession.getManager("ManagedElement", common_IHolder);
			managedElementtMgr_I=ManagedElementMgr_IHelper.narrow(common_IHolder.value);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
