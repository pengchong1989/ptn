package com.nms.model.ptn;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.EthServiceInfo;
import com.nms.db.dao.ptn.EthServiceInstMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class EthService_MB extends ObjectService_Mybatis {

	private EthServiceInstMapper mapper = null;

	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}

	public EthServiceInstMapper getMapper() {
		return mapper;
	}

	public void setMapper(EthServiceInstMapper mapper) {
		this.mapper = mapper;
	}
	
	
	/**
	 * 查询该网元下所有信息
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public List<EthServiceInfo> select(int siteId) throws Exception{
		List<EthServiceInfo> ethServiceInfoList = null;
		if(siteId == 0){
			throw new Exception("siteId is null");
		}   
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			ethServiceInfoList = new ArrayList<EthServiceInfo>();
			ethServiceInfoList = this.mapper.queryBySiteId(siteId);
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return ethServiceInfoList;
	}
	
	
	/**
	 * 新增
	 * @param ethServiceInfo
	 * @throws Exception
	 */
	public int save(List<EthServiceInfo> ethServiceInfos) throws Exception{
		
		if(ethServiceInfos == null){
			throw new Exception("ethServiceInfos is null");
		}
		int result = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			for(int i=0;i<ethServiceInfos.size();i++){
			   result = this.mapper.insert(ethServiceInfos.get(i));
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return result;
	}
	
	/**
	 * 更新
	 * @param ethServiceInfo
	 * @throws Exception
	 */
	public void update(EthServiceInfo ethServiceInfo) throws Exception{
		if(ethServiceInfo == null){
			throw new Exception("ethServiceInfo is null");
		}
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.update(ethServiceInfo);
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			this.sqlSession.getConnection().setAutoCommit(true);
		}
	}
	
	/**
	 *删除
	 * @param ethServiceInfo
	 * @throws Exception
	 */
	public void delete(EthServiceInfo ethServiceInfo) throws Exception{
		if(ethServiceInfo == null){
			throw new Exception("ethServiceInfo is null");
		}
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.delete(ethServiceInfo);
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			this.sqlSession.getConnection().setAutoCommit(true);
		}
	}
	
	/**
	 *批量删除
	 * @param ethServiceInfo
	 * @throws Exception
	 */
	public void delete(List<EthServiceInfo> ethServiceInfos) throws Exception{
		if(ethServiceInfos == null){
			throw new Exception("ethServiceInfo is null");
		}
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			for(EthServiceInfo ethInfo : ethServiceInfos){
				this.mapper.delete(ethInfo);
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			this.sqlSession.getConnection().setAutoCommit(true);
		}
	}
	
	/**
	 * 根据网元删除所属网元的数据
	 * @param ethServiceInfo
	 * @throws Exception
	 */
	public void deleteBySiteId(int siteId)throws Exception {
		if (siteId == 0) {
			throw new Exception("siteId is null");
		}
		try {
			this.mapper.deleteAllBySite(siteId);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 查询
	 * 
	 * @param ethServiceInfo
	 * @return
	 * @throws Exception
	 */
	public List<EthServiceInfo> queryByNeID(EthServiceInfo ethServiceInfo) throws Exception {
		if (ethServiceInfo == null) {
			throw new Exception("ethServiceInfo is null");
		}
		List<EthServiceInfo> ethServiceInfoList = null;
		try {
			ethServiceInfoList = this.mapper.queryOamLinkInfoByCondition(ethServiceInfo);
			if(ethServiceInfoList==null){
				ethServiceInfoList=new ArrayList<EthServiceInfo>();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ethServiceInfoList;
	}
}
