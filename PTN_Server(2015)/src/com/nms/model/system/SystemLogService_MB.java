package com.nms.model.system;


import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.system.SystemLog;
import com.nms.db.dao.system.SystemLogMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;


/**
 *系统日志
 * @author sy
 *
 */
public class SystemLogService_MB extends ObjectService_Mybatis{
	private SystemLogMapper mapper = null;
 
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;	
	}
	
	public SystemLogMapper getMapper() {
		return mapper;
	}

	public void setMapper(SystemLogMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 新增 SystemLog(操作日志表)
	 * 
	 * @param operationLog
	 *            实体
	 * @throws Exception
	 */	
	
	public int insertSystemLog(SystemLog systemLog)throws Exception{
		if (systemLog == null) {
			throw new Exception("operationLog is null");
		}
		int result=0;
		try {
			String date=DateUtil.getDate(DateUtil.FULLTIME);
			systemLog.setStartTime(date);
			result=mapper.insert(systemLog);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	/**
	 * 查询   操作日志
	 * 条件（SystemLog）
	 * @return List<operationLog>集合
	 * @throws Exception
	 */
	
	public List<SystemLog> select(SystemLog systemLog) throws Exception {
		List<SystemLog> systemLogList = null;		
		try {			
			systemLogList = this.mapper.select(systemLog);
			if(systemLogList == null ){
				systemLogList = new ArrayList<SystemLog>();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return systemLogList;
	}
	
}
