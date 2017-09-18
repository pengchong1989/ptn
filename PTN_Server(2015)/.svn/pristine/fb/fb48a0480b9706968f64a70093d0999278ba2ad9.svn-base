package com.nms.model.system;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.system.OperationDataLog;
import com.nms.db.bean.system.OperationLog;
import com.nms.db.dao.system.OperationLogMapper;
import com.nms.db.dao.system.operationDataLogMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;

/**
 * 操作日志
 * @author sy
 *
 */
public class OperationLogService_MB extends ObjectService_Mybatis{
	private OperationLogMapper mapper = null;
    private operationDataLogMapper dataLogMapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
		this.dataLogMapper = (operationDataLogMapper) this.sqlSession.getMapper(operationDataLogMapper.class);
	}
	
	public OperationLogMapper getMapper() {
		return mapper;
	}

	public void setMapper(OperationLogMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 新增 operation_log(操作日志表)
	 * 
	 * @param operationLog
	 *            实体
	 * @throws Exception
	 */	
	public int insertOperationLog(OperationLog operationLog)throws Exception{
		if (operationLog == null) {
			throw new Exception("operationLog is null");
		}
		int result=0;
		try {
			String date=DateUtil.getDate(DateUtil.FULLTIME);
			operationLog.setStartTime(date);
			operationLog.setIP(InetAddress.getLocalHost().getHostAddress());
			result=mapper.insert(operationLog);
			for (OperationDataLog log : operationLog.getDataLogList()) {
				log.setOpeLogId(operationLog.getId());
				this.dataLogMapper.insert(log);
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	/**
	 * 查询   操作日志
	 * 条件（operationLog）
	 * @return List<operationLog>集合
	 * @throws Exception
	 */
	public List<OperationLog> select(OperationLog operationLog) throws Exception {
        int isSelect=0;
		List<OperationLog> operationLogList = null;		
		try {	
			if(operationLog.isSelect()){
				isSelect=1;
			}
			operationLogList = new ArrayList<OperationLog>();
			operationLogList = this.mapper.select(operationLog,isSelect);
			if(operationLogList != null && operationLogList.size() > 0){
				for (OperationLog log : operationLogList) {
					List<OperationDataLog> dataLogList = this.dataLogMapper.selectByOpeLogId(log.getId());
					if(dataLogList != null && dataLogList.size() > 0){
						log.setDataLogList(dataLogList);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationLogList;
	}
	
	/**
	 * 修改   operation_log表（结束时间，操作结果）
	 * @param operationLog
	 * @return
	 * @throws Exception
	 */
	public int update(OperationLog operationLog) throws Exception{
		if (operationLog == null) {
			throw new Exception("operationLog is null");
		}
		int result=0;
		try {
			operationLog.setOverTime(DateUtil.getDate(DateUtil.FULLTIME));
			result=this.mapper.updateOperationLog(operationLog);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	/**
	 * 查看 操作日志表  多少条记录
	 * 返回   result 条
	 * @return
	 * @throws Exception
	 */
	public int selectCount()throws Exception{
		int result=0;
		result=this.mapper.selectOperationLogCount();
		return result;
	}
	
	/**
	 * 根据 -- 操作日志记录表--主键集合，批量删除
	 * 
	 * @param idList
	 * @return
	 * @throws Exception
	 */
	public int delete(List<Integer> idList) throws Exception {
		int result = 0;
		try {
			if (idList == null || idList.size() == 0) {
				return 0;
			}
			result = this.mapper.delete(idList);
			for (int id : idList) {
				this.dataLogMapper.deleteByLogId(id);
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	/**
	 * 移除  某一时间 以前的  操作日志 信息
	 * @param removeTime
	 * @return
	 * @throws Exception
	 */
	public int delete(String removeTime)throws Exception{
		int result=0;
		try {
			List<Integer> idList = this.mapper.selectByTime(removeTime);
			result = this.mapper.deleteByTime(removeTime);
			if(idList != null && idList.size() > 0){
				for (int id : idList) {
					this.dataLogMapper.deleteByLogId(id);
				}
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	/**
	 * 根据网元去删除操作日志
	 * @param siteId
	 * @return
	 */
	public int deleteBySiteId(int siteId){
		int result = 0;
		try {
			List<Integer> idList = this.mapper.selectBySiteId(siteId);
			result = this.delete(idList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
}
