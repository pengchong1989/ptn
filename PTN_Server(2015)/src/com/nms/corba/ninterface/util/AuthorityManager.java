package com.nms.corba.ninterface.util;

import globaldefs.ExceptionType_T;
import globaldefs.ProcessingFailureException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.db.bean.system.roleManage.RoleRelevance;
import com.nms.db.bean.system.user.UserInst;
import com.nms.model.system.roleManage.RoleRelevanceService_MB;
import com.nms.model.system.user.UserInstServiece_Mb;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.UiUtil;

/**
 * 权限管理_Corba
 * @author dzy
 *
 */
public class AuthorityManager {
	
	public static Map<String,List<RoleRelevance>> authorityMap = new HashMap<String, List<RoleRelevance>>(); //权限关系集合 key=用户名 ，value = 角色关系
	
	/**
	 * 判断用户是否有权限
	 * @param userName	用户名
	 * @param authority 确定操作的权限 参考RootFactory
	 * @throws ProcessingFailureException CORBA异常 
	 */
	public static void checkAuthority(ICorbaSession userSession,int authority) throws ProcessingFailureException{
		//检验用户名若为空则没有登录
		if (null == userSession || null == userSession.getUserName()|| "".equals(userSession.getUserName())) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"The userSession is invalid");
		}
		
		boolean flag = false;//验证用户权限标记，true表示有此权限
		//通过用户名拿到authorityMap 角色关系 如果没有拿到 抛出没有登录 异常
		List<RoleRelevance> roleRelevanceList = authorityMap.get(userSession.getUserName());
		if (null == roleRelevanceList) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"The entity is not found.");
		}
		//比较authority权限和  客户的用户权限  判断是否有权限如果没有权限 抛出没有权限异常
		RoleRelevance roleRelevance = null;
		for(int i=0;i<roleRelevanceList.size();i++){
			roleRelevance = roleRelevanceList.get(i);
			/**
			 * 标签验证： 权限集合中有  此  authority 
			 * 可以操作  
			 */
			if(authority==roleRelevance.getLabel()){
				flag = true;
				break ;
			}
		}
		
		//无权限跑出异常
		if (!flag) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_ACCESS_DENIED,"The entity is not found.");
		}
	}
	
	/**
	 * 把用户和权限 添加到authorityMap中
	 * @param userName 用户名
	 * @return
	 */
	public void addRoleRelevance_global(String userName) throws ProcessingFailureException{
		
		//验证authorityMap是否存在 不存在则添加
		if (authorityMap.get(userName) == null) {
			
			//通过用户名得到 用户的权限LIST 
			RoleRelevanceService_MB  relevanceService=null;
			UserInstServiece_Mb userInstServiece = null;
			UserInst userInst = null; // 用户对象
			List<UserInst> userInfoList = null; // 用户集合
			List<RoleRelevance> roleRelevanceList=null;
			try {
				userInstServiece = (UserInstServiece_Mb) ConstantUtil.serviceFactory.newService_MB(Services.UserInst);
				userInst = new UserInst();
				userInst.setUser_Name(userName);
				userInfoList = userInstServiece.select(userInst);//此用户已经登录成功，故再次不需再判断用户集合，直接获取即可
				if (userInfoList.size() != 0) {
					if (userInfoList.get(0).getRoleInfo_id() == 0) {
						throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
					}
					RoleRelevance roleRelevance=new RoleRelevance();
					/**
					 * //查表 ，   此 角色的权限集合
					 * 通过   infoId==ID   查找  权限
					 */
					roleRelevance.setInfoId(userInfoList.get(0).getRoleInfo_id());
					relevanceService = (RoleRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ROLERELEVANCESERVICE);
					roleRelevanceList=relevanceService.select(roleRelevance);	
					if (null == roleRelevanceList) {
						throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
					}
					//存放到authorityMap中
					authorityMap.put(userName, roleRelevanceList);
				}
			} catch (Exception e) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"addRoleRelevance_global ex.");
			} finally {
				UiUtil.closeService_MB(relevanceService);
				UiUtil.closeService_MB(userInstServiece);
			}
		}
	}
}
