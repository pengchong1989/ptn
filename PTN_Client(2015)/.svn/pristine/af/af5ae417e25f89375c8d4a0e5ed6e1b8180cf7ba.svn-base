package com.nms.db.dao.system.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.system.user.UserField;
import com.nms.db.bean.system.user.UserInst;



public interface UserFieldMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(UserField record);

    UserField selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserField record);

    int updateByPrimaryKey(UserField record);
    
    public List<UserField> queryByUserInst(@Param("user")UserInst userName);
    
    public int insert(@Param("userField")UserField userField);
    
    /**
	 * 通过条件查询
	 * @param elaninfocondition 查询条件
	 * @return List<ElineInfo>集合
	 */
	public List<UserField> queryByCondition(@Param("userid")Integer userId);
	
	/**
	 * 通过主键删除userfield
	 * @param userId 主键
	 * @return 删除的记录数
	 */
	public int deleteByUserId(@Param("userId")int userid);
	
	public int deleteByField(int fieldId);
	
	public List<UserField> queryByUserField(@Param("userField")UserField userField);
}