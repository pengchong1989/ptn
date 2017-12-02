package com.nms.db.dao.system;

import java.util.List;
import com.nms.db.bean.system.SystemLog;



public interface SystemLogMapper {

    public int insert(SystemLog systemLog);
	public List<SystemLog> select(SystemLog systemLog);

}