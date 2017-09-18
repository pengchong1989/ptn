package com.nms.db.dao.alarm;

import java.util.List;

import com.nms.db.bean.alarm.WarningLevel;


public interface WarningLevelMapper {
	public List<WarningLevel> queryByCondition(WarningLevel warninglevel);

	public int delete(int id);

	public int insert(WarningLevel warningLevel);

	public int update(WarningLevel warningLevel);

	public WarningLevel selectByID(int id);
}