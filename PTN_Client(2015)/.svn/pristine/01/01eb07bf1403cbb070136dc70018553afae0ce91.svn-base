package com.nms.ui.ptn.safety.roleManage;


import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

import com.nms.db.bean.system.roleManage.RoleRelevance;
import com.nms.model.system.roleManage.RoleRelevanceService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
/**
 * 权限验证
 */
public class RoleRoot {
	public RoleRoot(){
		try {
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	private  List<RoleRelevance> roleRelevanceList=null;
	/**
	 * 查找用户  角色
	 */
	public  List<RoleRelevance>  rootPrivy(){
		RoleRelevanceService_MB relevanceService = null;
		try {
			relevanceService = (RoleRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ROLERELEVANCESERVICE);
			//检测   本次登陆的用户 什么角色
			
			//用户   角色  权限
			if(ConstantUtil.user .getRoleInfo_id()>0){
				//   此用户     为  什么角色
					RoleRelevance roleRelevance=new RoleRelevance();
					roleRelevance.setInfoId(ConstantUtil.user .getRoleInfo_id());
					/**
					 * //查表 ，   此 角色的权限集合
					 * 通过   infoId==ID   查找  权限
					 */
					roleRelevanceList=relevanceService.select(roleRelevance);							
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(relevanceService);
		}
		return roleRelevanceList;
	
	}
	/**
	 * 权限验证  （菜单 ） 
	 * @param menuItem    菜单条   
	 * @param label		权限标签 		
	 */
	public  Boolean setItemEnbale(Object object,int label){
		List<RoleRelevance> roleRelevanceList=ConstantUtil.roleRelevanceList;
		boolean flag=false;
		if(roleRelevanceList!=null){
			for(int i=0;i<roleRelevanceList.size();i++){
				RoleRelevance roleRelevance=roleRelevanceList.get(i);
				
				// 标签验证： 权限集合中有  此  label 
				
				if(label==roleRelevance.getLabel()){
					flag=true;			
					break;
			 	}
				}	
		}
		if(object instanceof JMenuItem){
			JMenuItem menuItem=(JMenuItem)object;
			menuItem.setEnabled(flag);
		}
		else if(object instanceof JLabel){    //（图标）——
			JLabel lbl=(JLabel)object;
			lbl.setVisible(true);
			
		}else if(object instanceof JButton){
			JButton bt=(JButton)object;
			bt.setEnabled(flag);
		}
		return flag;
	}
	/**
	 * 权限验证  : 比对 传人参数 （权限标签）是否  存在于      登陆用户的权限之中
	 * @param label  标签 与 此角色的   可操作权限集合 验证
	 */
	public  boolean root(int label){
		//默认 为false   不可操作此按钮
		boolean flag=false;
		List<RoleRelevance> roleRelevanceList=ConstantUtil.roleRelevanceList;
		if(roleRelevanceList!=null){
			for(int i=0;i<roleRelevanceList.size();i++){
				RoleRelevance roleRelevance=roleRelevanceList.get(i);
				// 标签验证： 权限集合中有  此  label 可以操作  
				 
				if(label==roleRelevance.getLabel()){
					flag=true;
				}
			}			
		}
		return flag;
	}
}