package com.nms.model.system;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class DataCompareService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}

	
	public void dataCompare(List<String> sqlList) throws SQLException{
		
		PreparedStatement preparedStatement = null;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			for(String sql : sqlList){
				preparedStatement = this.sqlSession.getConnection().prepareStatement(sql);
				preparedStatement.executeUpdate();
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (Exception e) {
					ExceptionManage.dispose(e, getClass());
				}finally{
					preparedStatement = null;
				}
			}
			this.sqlSession.getConnection().setAutoCommit(true);
		}
	}
}
