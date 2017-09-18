package com.nms.db.dao.system;

import java.util.List;

import com.nms.db.bean.ptn.DbInfoTask;

public interface PtndbInstPathMapper {

	public int insert(DbInfoTask record);

	public int update(DbInfoTask record);

	public List<DbInfoTask> selectDbTask(String mointorType);
}