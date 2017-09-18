package com.nms.model.ptn;

import java.lang.reflect.Field;
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
	 * 根据端口号，查询该端口的二层vlan值
	 * @param siteId
	 * @param portLine
	 * @return
	 */
	public List<Integer> queryBySiteIdAndPortLine(int siteId,String portLine,int num){
		List<Integer> vlanIDs = null;
		List<EthServiceInfo> ethServiceInfo = null;
		try {
			ethServiceInfo = new ArrayList<EthServiceInfo>();
			ethServiceInfo=mapper.queryBySiteIdAndPortLine(siteId,portLine,num);
			vlanIDs = new ArrayList<Integer>();
			if(ethServiceInfo!=null && ethServiceInfo.size()>0){
				for(int i=0;i<ethServiceInfo.size();i++){
					for(Field field:ethServiceInfo.get(i).getClass().getDeclaredFields()){
						if(field.getName().equals(portLine)){
							field.setAccessible( true ); 
							String lineString = Integer.toBinaryString((Integer)field.get(ethServiceInfo.get(i)));
							if(lineString.length()>=num && lineString.substring(lineString.length()-num,lineString.length()-num+1).equals("1")){
								vlanIDs.add(ethServiceInfo.get(i).getVlanId());
							}
						}
					}
					
				}
				
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return vlanIDs;
	}
	
	public int allCount(int siteId) throws Exception
	{
		if(siteId == 0){
			throw new Exception("siteId is null");
		}   
		try {
			return  this.mapper.findAllCount(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return 0;
	}
	
	/**
	 * 通过条件查询
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public boolean  select(List<Integer> valans,int siteId) throws Exception{
		if(siteId == 0){
			throw new Exception("siteId is null");
		}   
		List<EthServiceInfo> ethService = null;
		try {
			ethService = new ArrayList<EthServiceInfo>();
			ethService=this.mapper.queryByCondition(valans,siteId);
			if(ethService!=null && ethService.size()>0){
				return true;
			}							
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return false;
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
	 * 查询该网元下所有信息
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public EthServiceInfo selectById(int id) throws Exception{
		EthServiceInfo ethServiceInfo = null;
		if(id == 0){
			throw new Exception("id is null");
		}   
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			ethServiceInfo = new EthServiceInfo();
			ethServiceInfo = this.mapper.queryById(id);
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return ethServiceInfo;
	}
}
