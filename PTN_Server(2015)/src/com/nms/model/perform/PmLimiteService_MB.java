package com.nms.model.perform;


import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.perform.PmValueLimiteInfo;
import com.nms.db.dao.perform.PmValueLimiteInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class PmLimiteService_MB extends ObjectService_Mybatis{
	private PmValueLimiteInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	public PmValueLimiteInfoMapper getMapper() {
		return mapper;
	}

	public void setMapper(PmValueLimiteInfoMapper mapper) {
		this.mapper = mapper;
	}
	
		
	/**
	 * 按条件查询
	 * @param pmValueLimiteInfo
	 * @return
	 */
	public PmValueLimiteInfo select(PmValueLimiteInfo pmValueLimiteInfo)
	{
		PmValueLimiteInfo pmllimite = null;
		try
		{
			pmllimite = new PmValueLimiteInfo();
			pmllimite = this.mapper.queryByCondition(pmValueLimiteInfo);
		}
		catch (Exception e)
		{
			ExceptionManage.dispose(e,this.getClass());
		}
		return pmllimite;
	}
	
	/**
	 * 新建或修改
	 * @param pmllimite
	 * @return
	 * @throws Exception
	 */
	public int saveOrUpdate(PmValueLimiteInfo pmllimite) throws Exception
	{
		if (pmllimite == null)
		{
			throw new Exception("pmllimite is null");
		}

		int result = 0;
		try
		{	
			PmValueLimiteInfo pminfo = select(pmllimite);
			if(pminfo != null)
			{
				result = this.mapper.update(pmllimite);
			}else
			{
				result = this.mapper.insert(pmllimite);
			}
			this.sqlSession.commit();
		}
		catch (Exception e)
		{
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	/**
	 * 新建
	 * @param pmllimite
	 * @return
	 * @throws Exception
	 */
	public int insert(PmValueLimiteInfo pmllimite) throws Exception
	{
		if (pmllimite == null)
		{
			throw new Exception("pmllimite is null");
		}

		int result = 0;

		try
		{	
			result = this.mapper.insert(pmllimite);
			this.sqlSession.commit();
		}
		catch (Exception e)
		{
			ExceptionManage.dispose(e,this.getClass());
		}

		return result;
	}
	/**
	 * 新建
	 * @param update
	 * @return
	 * @throws Exception
	 */
	public int update(PmValueLimiteInfo pmllimite) throws Exception
	{
		if (pmllimite == null)
		{
			throw new Exception("pmllimite is null");
		}

		int result = 0;

		try
		{	
			result = this.mapper.update(pmllimite);
			this.sqlSession.commit();
		}
		catch (Exception e)
		{
			ExceptionManage.dispose(e,this.getClass());
		}

		return result;
	}
}
