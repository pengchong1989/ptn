package com.nms.ui.ptn.safety.roleManage.controller;

import java.util.List;

import com.nms.db.bean.system.roleManage.RoleInfo;
import com.nms.db.bean.system.roleManage.RoleManage;
import com.nms.db.bean.system.user.UserInst;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.system.roleManage.RoleInfoService_MB;
import com.nms.model.system.user.UserInstServiece_Mb;
import com.nms.model.util.Services;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.safety.roleManage.RoleInfoLeftPanel;
import com.nms.ui.ptn.safety.roleManage.dialog.AddRoleInfoDialog;

/**
 * 角色列表按钮控制类
 * @author sy
 *
 */
public class RoleInfoController extends AbstractController {
	
	private RoleInfoLeftPanel view;
	/**
	 * 实例化对象
	 * @param view
	 */
	public RoleInfoController(RoleInfoLeftPanel view) {
		this.view = view;		
	}

	/**
	 * 刷新按钮事件
	 */
	public void refresh() {
		List<RoleInfo> roleList = null;
		RoleInfoService_MB infoService = null;
		try {
			infoService = (RoleInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ROLEINFOSERVICE);
			roleList = infoService.select(new RoleInfo());
			this.view.getRightPanel().clear();
			this.view.clear();
			this.view.initData(roleList);
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(infoService);
		}
	}

	// 创建
	public void openCreateDialog() throws Exception {
		new AddRoleInfoDialog(null);
		this.view.getController().refresh();
	}

	// 删除
	public void delete(){
		RoleInfoService_MB roleInfoService = null;
		try {
			List<RoleInfo> roleInfoList = this.view.getAllSelect();
			roleInfoService = (RoleInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ROLEINFOSERVICE);			
			roleInfoService.delete(roleInfoList);
			//添加操作日志记录
			this.insertOpeLog(EOperationLogType.DELETEROLE.getValue(), ResultString.CONFIG_SUCCESS, null, null);
			DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
			this.view.getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(roleInfoService);
		}
	}
	
	private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
		AddOperateLog.insertOperLog(view.getDeleteButton(), operationType, result, oldMac, newMac, 0,ResourceUtil.srcStr(StringKeysTab.TAB_ROLEMANAGE),"");		
	}
	
	/**
	 * 删除前的验证
	 * 
	 * @return true 验证成功， false 验证失败
	 * @throws Exception
	 */
	@Override
	public boolean deleteChecking() {
		boolean flag = false;
		UserInstServiece_Mb userService = null;
		try {
			List<RoleInfo> roleInfoList = this.view.getAllSelect();
			userService = (UserInstServiece_Mb) ConstantUtil.serviceFactory.newService_MB(Services.UserInst);;
			for(int i=0;i<roleInfoList.size();i++){
				RoleInfo roleInfo=roleInfoList.get(i);
				UserInst userinst=new UserInst();
				userinst.setRoleInfo_id(roleInfo.getId());
				List<UserInst> userInst = userService.selectuserid(userinst);
				/**
				 * 判定  ：  若为  系统默认 则不可删除
				 */
				if (roleInfo.getRoleName().equals(ConstantUtil.user.getUser_Name())) {
					DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_USERNAMEBESTOW));
					this.insertOpeLog(EOperationLogType.ROLEMANAGEDELETE1.getValue(), ResultString.CONFIG_FAILED, null, null);
					return false;
				}
				/**
				 * 判定：   若此角色  以有用户在使用，则不可删除
				 */			
				else if(null!=userInst&&userInst.size()>0){
					DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_USER_ROLE_USERING));
					this.insertOpeLog(EOperationLogType.ROLEMANAGEDELETE3.getValue(), ResultString.CONFIG_FAILED, null, null);
					return false;
				}
				else if(roleInfo.getRoleName().equals(ResourceUtil.srcStr(StringKeysTip.TIP_SYSTEM_MANAGE))
						||roleInfo.getRoleName().equals(ResourceUtil.srcStr(StringKeysTip.TIP_SYSTEM_MAINTAIN)) 		
						||roleInfo.getRoleName().equals(ResourceUtil.srcStr(StringKeysTip.TIP_SYSTEM_OPERATE)) 		
						||roleInfo.getRoleName().equals(ResourceUtil.srcStr(StringKeysTip.TIP_SYSTEM_MONITOR)) 
						||roleInfo.getRoleName().equals(ResourceUtil.srcStr(StringKeysTip.TIP_SUSPER_USER))
						||roleInfo.getRoleEnName().equals("System Administrator") 		
						||roleInfo.getRoleEnName().equals("System Maintenance") 		
						||roleInfo.getRoleEnName().equals("System Operator") 
						||roleInfo.getRoleEnName().equals("System Monitor")){
				DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_USER_DEFAULTUSER));
				return false;
			}
			}
			flag = true;
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(userService);
		}
		return flag;
	}
	

	// 修改
	public void openUpdateDialog() throws Exception {
		RoleInfo roleInfo=this.view.getSelect();
		if (roleInfo.getRoleName().equals(ConstantUtil.user.getUser_Name())) {
			DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_USERNAMEBESTOW));
			return ;
		}
		else if (roleInfo.getRoleName().equals(ResourceUtil.srcStr(StringKeysTip.TIP_SYSTEM_MANAGE))
		         ||roleInfo.getRoleName().equals(ResourceUtil.srcStr(StringKeysTip.TIP_SYSTEM_MAINTAIN)) 		
		         ||roleInfo.getRoleName().equals(ResourceUtil.srcStr(StringKeysTip.TIP_SYSTEM_OPERATE)) 		
		         ||roleInfo.getRoleName().equals(ResourceUtil.srcStr(StringKeysTip.TIP_SYSTEM_MONITOR))
		         ||roleInfo.getRoleName().equals(ResourceUtil.srcStr(StringKeysTip.TIP_SUSPER_USER))
		         ||roleInfo.getRoleEnName().equals("System Administrator") 		
				 ||roleInfo.getRoleEnName().equals("System Maintenance") 		
				 ||roleInfo.getRoleEnName().equals("System Operator") 
				 ||roleInfo.getRoleEnName().equals("System Monitor")         
		
		)
		   {
			DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_USER_DEFAULTUSER));
			return ;
		   }
		new AddRoleInfoDialog(roleInfo);
		this.view.getController().refresh();
	}

	/**
	 * 点击table数据刷新界面
	 */
	@Override
	public void initDetailInfo() {
		RoleInfo roleInfo = null;
		List<RoleManage> roleManageList = null;
		try {
			//清除方法
			this.view.getRightPanel().clear();
			//获取点击的角色对象
			roleInfo = this.view.getSelect();
			if (null != roleInfo) {
				//获取此角色的所有权限
				roleManageList = roleInfo.getRoleManageList();
				if (null != roleManageList && roleManageList.size() > 0) {
					/**
					 * 未     roleManageList集合中每个元素添加    角色名（Admainistrator）
					 * 做下次判定之用
					 * 为条件成立，则可以  显示（默认角色Admainistrator的）全部权限
					 * 否则  不会有安全管理权限出现
					 */
					if(roleInfo.getRoleName().equals("Administrator")){
						for(int i=0;i<roleManageList.size();i++){
						//	RoleManage roleManage=roleManageList.get(i);
							roleManageList.get(i).setRoleName(roleInfo.getRoleName());
						}
					}
					this.view.getRightPanel().bindingData(roleManageList);
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			roleInfo = null;
			roleManageList = null;
		}
	}
}
