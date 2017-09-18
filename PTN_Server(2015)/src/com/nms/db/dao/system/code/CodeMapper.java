package com.nms.db.dao.system.code;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.system.code.Code;

public interface CodeMapper {
    int deleteByPrimaryKey(Integer id);

    public int insert(Code code);

    int insertSelective(Code record);

    Code selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Code record);

    int updateByPrimaryKey(Code record);

	public List<Code> queryByCondition(Code code);
	
	/**
	 * 根据条件查询code
	 * 
	 * @param codeCondition
	 *            查询条件
	 * @param connection
	 *            连接数据库
	 * @return code集合
	 * @throws Exception
	 */
	public List<Code> queryByCode(Code codeCondition);
	
	public int update(Code code);
	
	public int delete(@Param("id")Integer id);
}