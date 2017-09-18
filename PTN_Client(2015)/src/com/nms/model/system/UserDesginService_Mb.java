package com.nms.model.system;


import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.system.UserDesginInfo;
import com.nms.db.dao.system.UserDesginInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;


public class UserDesginService_Mb extends ObjectService_Mybatis{
	 private UserDesginInfoMapper userDesginMapper=null;	
	 
	 public UserDesginInfoMapper getMapper() {
		return userDesginMapper;
	 }
	 
	 public void setMapper(UserDesginInfoMapper userDesginMapper) {
		this.userDesginMapper = userDesginMapper;
	 }
	 
	 public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	 }
	 
	 public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	 }


	
	/**
	 * 批量插入
	 * @param userDesginfo
	 * @throws Exception 
	 */
	public void save(UserDesginInfo userDesginfo) throws Exception{	
		try {
			if(userDesginfo.getId()>0){
				this.userDesginMapper.update(userDesginfo);
			}else{
				this.userDesginMapper.insert(userDesginfo);
			}
			sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 查詢
	 * @param userDesginfo
	 * @throws Exception 
	 */
	public UserDesginInfo select(String userName) throws Exception{
		UserDesginInfo userDesginfo=null;	
		try{				
			userDesginfo=this.userDesginMapper.select(userName);
			if(userDesginfo==null){
				userDesginfo = new UserDesginInfo();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
			return userDesginfo;
	}
}
