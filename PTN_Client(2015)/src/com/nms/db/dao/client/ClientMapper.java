package com.nms.db.dao.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.client.Client;


public interface ClientMapper {
    int deleteByPrimaryKey(Integer id);

    

    int insertSelective(Client record);

    Client selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Client record);

    int updateByPrimaryKey(Client record);
    /**
	 * 刷新
	 * @return 
	 */
	public List<Client> select();
	
	//添加
	public int insert(@Param("client")Client client);
	//修改
	public int update(@Param("client")Client client);
	//删除
	public int delete(@Param("client")Client client);
	/**
	 * 查询名字
	 * @param afterName
	 * @param beforeName
	 * @return
	 */
	public int query_name(@Param("afterName")String afterName,@Param("beforeName")String beforeName); 
	
	public List<Client> selectClient(@Param("client")Client client);
}