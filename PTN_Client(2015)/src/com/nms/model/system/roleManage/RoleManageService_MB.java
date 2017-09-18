package com.nms.model.system.roleManage;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.nms.db.bean.system.roleManage.RoleInfo;
import com.nms.db.bean.system.roleManage.RoleManage;
import com.nms.db.dao.system.roleManage.RoleManageMapper;
import com.nms.model.util.ObjectService_Mybatis;
/**
 *角色权限  业务
 * @author Administrator
 *
 */
public class RoleManageService_MB extends ObjectService_Mybatis{

    private RoleManageMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public RoleManageMapper getMapper() {
		return mapper;
	}

	public void setMapper(RoleManageMapper mapper) {
		this.mapper = mapper;
	}

	List<RoleManage> infoList=null;

	/**
	 * 查看
	 */
	public List<RoleManage> select(RoleManage roleManage) throws Exception {
		infoList= new ArrayList<RoleManage>();
		infoList=this.mapper.select(roleManage);		 
		return infoList;
	}
	
	public List<RoleManage> byRoleInfoSelect(RoleInfo roleInfo)throws Exception{
		if(null==roleInfo){
			throw new Exception("roleInfo is null");
		}
		List<RoleManage> roleInfoList=new ArrayList<RoleManage>();				
		roleInfoList=this.mapper.byRoleInfoSelect(roleInfo);
		return roleInfoList;
	}


}
