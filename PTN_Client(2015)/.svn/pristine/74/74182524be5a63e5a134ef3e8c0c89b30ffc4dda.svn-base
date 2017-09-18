package com.nms.db.dao.system;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.system.DataBaseInfo;



public interface PtndbInstMapper {
	public DataBaseInfo slectTableInfo(@Param("tableName")String tableName, @Param("label")int label);

	public int countTableInfo(@Param("tableName")String tableName);

	public List<DataBaseInfo> selectDbInfo(int pathId);

	public void insert(DataBaseInfo dataBaseInfo);

	public void update(DataBaseInfo dataBaseInfo);
}