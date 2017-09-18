package com.nms.db.dao.system.code;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.system.code.CodeGroup;



public interface CodeGroupMapper {
    int deleteByPrimaryKey(Integer id);

    public int insert(CodeGroup codeGroup);

    int insertSelective(CodeGroup record);

    CodeGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CodeGroup record);

    int updateByPrimaryKey(CodeGroup record);
    
    /**
	 * 通过条件查询CodeGroup
	 * 
	 * @param codegroupCondition
	 *            查询条件
	 * @param connection
	 *            数据库链接
	 * @return CodeGroup集合
	 * @throws Exception
	 */
	public List<CodeGroup> queryByCondition(CodeGroup codegroupCondition);
	
	public List<CodeGroup> queryByConditionall();
	
	public int update(CodeGroup codeGroup);
	
	public int delete(@Param("id")Integer id);
}