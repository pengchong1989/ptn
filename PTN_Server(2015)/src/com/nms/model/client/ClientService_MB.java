package com.nms.model.client;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.client.Client;
import com.nms.db.dao.client.ClientMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class ClientService_MB extends ObjectService_Mybatis {
	private ClientMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(ClientMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 *  新增或修改
	 * @param client  新增或修改的对象
	 * @param connection
	 * @return
	 */
	public int saveOrUpdate(Client client){
		int result = 0;
		try {
			if(0==client.getId()){
				result = this.mapper.insert(client);
			
			}else{
				result = this.mapper.update(client);
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	/**
	 * 刷新
	 * @return
	 */
	public List<Client> refresh() {
		List<Client> result = null;
		try {
			result = new ArrayList<Client>();
			result = this.mapper.select();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	/**
	 * 删除
	 * @param client 删除条件
	 * @param connection
	 * @return
	 */
	public boolean delete(Client client){
		int result = 0;
		try {
			result = this.mapper.delete(client);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if(0== result){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 名字是否重复
	 * @param afterName
	 * @param beforeName
	 * @return
	 */
	public boolean nameRepetition(String afterName, String beforeName) {
		int result = 0;
		try {
			result = this.mapper.query_name(afterName, beforeName);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if(0== result){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 查询
	 * @param client 条件查询
	 * @param connection
	 * @return
	 */
	public List<Client> select(int clientID){
		List<Client> result = null;
		Client client=null;
		try {
			client=new Client();
			client.setId(clientID);
			result = this.query(client);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	/**
	 * 根据条件查询
	 * @param client 客户对象
	 * @param connection
	 * @return
	 */
	public List<Client> query(Client client){
		List<Client> result = new ArrayList<Client>();
		try {
			if(client==null)
				client=new Client();
			    result = this.mapper.selectClient(client);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
}
