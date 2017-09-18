package com.nms.corba.test.CorbaTest_sy;

import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import protection.ProtectionCommand_T;
import protection.ProtectionGroupIterator_IHolder;
import protection.ProtectionGroupList_THolder;
import protection.ProtectionMgr_I;
import protection.ProtectionMgr_IHelper;
import protection.SwitchData_THolder;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

/**
 * 测试保护组
 * @author sy
 *
 */
public class Test_ProtectionMgr extends TestBase{
	ProtectionMgr_I protectionMgr_Impl=null;//保护组接口类
	public Test_ProtectionMgr(){
		super();
	}
	public static void main(String args[]){
		Test_ProtectionMgr test_ProtectionMgr=new Test_ProtectionMgr();
		test_ProtectionMgr.performProtectionCommand();
		// 查询，修改友好名称，人工倒换， 测试完成
		System.out.println("测试完成！！！");
	}
	public void  getAllProtectionGroups(){
		this.getManager();		
		try {
			ProtectionGroupList_THolder ph=new ProtectionGroupList_THolder();
			ProtectionGroupIterator_IHolder pgpIt=new ProtectionGroupIterator_IHolder();
			protectionMgr_Impl.getAllProtectionGroups(getName(), 10, ph, pgpIt);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 修改保护组名称
	 */
	public void setUserLabel(){
		this.getManager();	
		try {
			protectionMgr_Impl.setUserLabel(getName(), "t_3_text", true);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	/**
	 * 人工保护倒换
	 */
	public void performProtectionCommand(){
		this.getManager();	
		SwitchData_THolder switchData=new SwitchData_THolder();
		try {
			protectionMgr_Impl.performProtectionCommand(ProtectionCommand_T.PC_MANUAL_SWITCH, super.getName(), getName(), getPTP(), switchData);
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
			super.connnect.emsSession.getManager("Protection", common_IHolder);
			protectionMgr_Impl=ProtectionMgr_IHelper.narrow(common_IHolder.value);
		} catch (ProcessingFailureException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * //决定倒换到工作侧还是保护侧的唯一标示，名称为长度为3的数组，每个元素不为空，并且值固定
	 * @return
	 */
	public NameAndStringValue_T[] getPTP(){
		NameAndStringValue_T[] nameAndSringValue_T=new NameAndStringValue_T[3];
		nameAndSringValue_T[0]=new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getCorbaEmsName());
		nameAndSringValue_T[1]=new NameAndStringValue_T("ManagedElement","5");
		nameAndSringValue_T[2]=new NameAndStringValue_T("PTP","23");
		return nameAndSringValue_T;
	}
}
