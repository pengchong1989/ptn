package com.nms.model.perform;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.perform.Capability;
import com.nms.db.dao.perform.CapabilityMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class CapabilityService_MB extends ObjectService_Mybatis {
	private CapabilityMapper mapper = null;

	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}

	public CapabilityMapper getMapper() {
		return mapper;
	}

	public void setMapper(CapabilityMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 查询全部
	 * 
	 * @return List<Capability> 集合
	 * @throws Exception
	 */
	public List<Capability> select() throws Exception {
		List<Capability> capabilityList = null;

		try {
			Capability capability = new Capability();
			capability.setManufacturer(1);
			capabilityList = mapper.queryByCondition(capability);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return capabilityList;
		
	}
	
	/**
	 * 关联查询 
	 * 		通过 网元ID，端口名称，- performanceCode
	 * 		查找 CapabilityName
	 * @param hisPerformanceInfo
	 * @return
	 * @throws Exception
	 */
	
	public List<Capability> selectCapaName(Capability capability) throws Exception{
		if(capability==null){
			throw new Exception("capability is null");
		}
		List<Capability> capabilityList= new ArrayList<Capability>();				
		capabilityList=this.mapper.queryByCapaName(capability);
		return capabilityList;
	}
	
	/**
	 * 根据条件查询
	 * @param capability 查询条件
	 * @return List<Capability> 集合
	 * @throws Exception
	 */
	public List<Capability> select(Capability capability) throws Exception {
		List<Capability> capabilityList=null;
		
		try {
			capabilityList=mapper.queryByCondition(capability);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return capabilityList;
	}
	
	/**
	 *  新增或修改capability对象，通过capability.getId()来判断是修改还是新增
	 * @param capability 实体
	 * @return  执行成功的记录数
	 * @throws Exception
	 */
	public int saveOrUpdate(Capability capability) throws Exception {

		if (capability == null) {
			throw new Exception("capability is null");
		}

		int result = 0;
		try {

			if (capability.getId() == 0) {
				result = this.mapper.insert(capability);
			} else {
				result = this.mapper.updateByPrimaryKey(capability);
			}
			sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
}
