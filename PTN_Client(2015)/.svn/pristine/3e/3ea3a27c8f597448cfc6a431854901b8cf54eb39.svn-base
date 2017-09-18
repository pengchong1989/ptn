package com.nms.model.system.roleManage;


import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.nms.db.bean.system.roleManage.RoleInfo;
import com.nms.db.bean.system.roleManage.RoleManage;
import com.nms.db.bean.system.roleManage.RoleRelevance;
import com.nms.db.dao.system.loginlog.LoginLogMapper;
import com.nms.db.dao.system.roleManage.RoleInfoMapper;
import com.nms.db.dao.system.roleManage.RoleManageMapper;
import com.nms.db.dao.system.roleManage.RoleRelevanceMapper;
import com.nms.model.util.ObjectService_Mybatis;

/**
 * 操作角色 角色——权限 关联表
 * 
 * @author sy
 * 
 */
public class RoleInfoService_MB extends ObjectService_Mybatis {

   private RoleInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public RoleInfoMapper getMapper() {
		return mapper;
	}

	public void setMapper(RoleInfoMapper mapper) {
		this.mapper = mapper;
	}


	List<RoleInfo> infoList = null;
	RoleRelevance roleRelevance = null;
	RoleManage roleManage = null;
	List<RoleRelevance> roleRelevanceList = new ArrayList<RoleRelevance>();

	/**
	 * 更新
	 * 
	 * @param roleInfo
	 *            roleInfo id 修改 角色 getRoleManageList 修改 关联表
	 * @return
	 * @throws Exception
	 */
	public int update(RoleInfo roleInfo) throws Exception {
		if (null == roleInfo) {
			throw new Exception("roleInfo is null");
		}
		int n = 0;
		LoginLogMapper loginLogMapper = null;
		RoleRelevanceMapper roleRelevanceMapper = null;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			roleRelevanceMapper = this.sqlSession.getMapper(RoleRelevanceMapper.class);
			if(roleInfo.getRoleEnName()==null){
				roleInfo.setRoleEnName("");
			}
			n = this.mapper.update(roleInfo);
			// 调用关联删除 角色 ID
			roleRelevanceMapper.delete(roleInfo);
			// 调用关联新增
			if (null != roleInfo.getRoleManageList()) {
				for (int i = 0; i < roleInfo.getRoleManageList().size(); i++) {
					roleManage = roleInfo.getRoleManageList().get(i);
					roleRelevance = new RoleRelevance();
					roleRelevance.setInfoId(roleInfo.getId());
					roleRelevance.setManageId(roleManage.getId());
					roleRelevanceList.add(roleRelevance);
				}
			}
			// 插入关联
			if(roleRelevanceList!=null && roleRelevanceList.size()>0){
			   for(int i=0;i<roleRelevanceList.size();i++){
				   roleRelevanceMapper.insert(roleRelevanceList.get(i));
			   }			
			}
			//强制注销此角色下的在线用户
			loginLogMapper = this.sqlSession.getMapper(LoginLogMapper.class); 
			loginLogMapper.updateByRole(roleInfo.getId());
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			throw e;
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
			// 清楚内存
			loginLogMapper = null;
		}

		return n;
	}

	/**
	 * 删除
	 * 
	 * @param roleInfoList
	 *            ID 删除 角色 批量删除 关联表，
	 * @return
	 * @throws Exception
	 */
	public int delete(List<RoleInfo> roleInfoList) throws Exception {
		if (null == roleInfoList) {
			throw new Exception("roleInfoList is null");
		}
		/**
		 * 根据 roleInfo 角色Id 先 删除 关联表（所有此 infoID ） 在删除 角色
		 */
		int result = 0;
		RoleInfo roleInfo = null;
		RoleRelevanceMapper roleRelevanceMapper = null;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			for (int i = 0; i < roleInfoList.size(); i++) {
				roleInfo = roleInfoList.get(i);
				roleRelevanceMapper = this.sqlSession.getMapper(RoleRelevanceMapper.class);
				roleRelevanceMapper.delete(roleInfo);
				result = this.mapper.delete(roleInfo);
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			throw e;
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
			roleInfo = null;
			// 清楚内存
		}

		return result;
	}

	/**
	 * 查看
	 * 
	 * @param roleInfo
	 *            Id角色
	 * @return 角色集合
	 * 
	 * @throws Exception
	 */
	public List<RoleInfo> select(RoleInfo roleInfo) throws Exception {
		RoleInfo info = null;
		List<RoleManage> roleManageList = null;
		RoleManageMapper roleManageMapper =this.sqlSession.getMapper(RoleManageMapper.class);
		infoList=new ArrayList<RoleInfo>();
		infoList = this.mapper.select(roleInfo);
		// 遍历每个角色
		if (null != infoList) {
			for (int i = 0; i < infoList.size(); i++) {
				info = infoList.get(i);
				// 通过角色 ID 即 关联表中infoId查询关联表
				// 得到 角色 ——权限（关联）集合
				roleManageList=new ArrayList<RoleManage>();
				roleManageList = roleManageMapper.byRoleInfoSelect(info);
				// 遍历 关联表 集合
				// 将 权限 结合 添加 到 此次遍历的角色中

				info.setRoleManageList(roleManageList);
			}
		}
		// 清楚内存
		return infoList;
	}

	/**
	 * 新增
	 * 
	 * @param roleInfo
	 *            角色 ID 角色 权限 集合
	 * @return 新建 角色 个数
	 * @throws Exception
	 */
	public int insert(RoleInfo roleInfo) throws Exception {
		if (null == roleInfo) {
			throw new Exception("roleInfo is null");
		}
		// 新增 角色 反正值（主键ID）
		int result = 0;
		RoleRelevanceMapper roleRelevanceMapper = null;
		try {			
			this.sqlSession.getConnection().setAutoCommit(false);	
			roleRelevanceMapper = this.sqlSession.getMapper(RoleRelevanceMapper.class);
			if(roleInfo.getRoleEnName()==null){
				roleInfo.setRoleEnName("");
			}
			this.mapper.insert(roleInfo);
			result = roleInfo.getId();
			// 遍历菜单
			if (null != roleInfo.getRoleManageList()) {
				for (int i = 0; i < roleInfo.getRoleManageList().size(); i++) {
					roleManage = roleInfo.getRoleManageList().get(i);
					// 创建关联对象
					roleRelevance = new RoleRelevance();
					roleRelevance.setInfoId(result);
					roleRelevance.setManageId(roleManage.getId());
					roleRelevanceList.add(roleRelevance);
				}
			}
			// 插入关联
			if(roleRelevanceList!=null && roleRelevanceList.size()>0){
			   for(int i=0;i<roleRelevanceList.size();i++){
				   roleRelevanceMapper.insert(roleRelevanceList.get(i));
			   }			
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			throw e;
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
			// 清楚内存
			roleRelevance = null;
			roleRelevanceList = null;
			roleRelevanceMapper = null;
		}
		return result;
	}


	/**
	 * 查看
	 * 
	 * @param roleInfo
	 *            Id角色
	 * @return 角色集合
	 * 
	 * @throws Exception
	 */
	public List<RoleInfo> selectNoName(RoleInfo roleInfo) throws Exception {
		if (null == roleInfo) {
			// 新增 时，，，角色不可重名
			infoList = new ArrayList<RoleInfo>();
			infoList = this.mapper.select(roleInfo);
		} else if (roleInfo.getId() > 0) {
			infoList = new ArrayList<RoleInfo>();
			infoList = this.mapper.selectNoName(roleInfo);
		}
		return infoList;
	}

}
