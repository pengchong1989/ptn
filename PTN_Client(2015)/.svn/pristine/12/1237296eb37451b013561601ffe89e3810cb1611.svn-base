package com.nms.model.ptn.port;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.db.dao.ptn.port.AcbufferMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class AcBufferService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private AcbufferMapper mapper;

	public AcbufferMapper getAcbufferMapper() {
		return mapper;
	}

	public void setAcbufferMapper(AcbufferMapper acbufferMapper) {
		this.mapper = acbufferMapper;
	}

	public List<Acbuffer> select(int id) {
		List<Acbuffer> buuferInfoList = null;
		try {
			buuferInfoList = this.mapper.query(id);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return buuferInfoList;
	}

	public int saveOrUpdate(List<Acbuffer> bufferinfos) throws Exception {
		if (bufferinfos == null ) {
			throw new Exception("bufferinfos is null");
		}
		int result = 0;
		try {
			for(Acbuffer buffer : bufferinfos) {
				if(buffer.getId() > 0) {//更新流
					result = this.mapper.update(buffer);
				} else { //新建流
					result = this.mapper.insert(buffer);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	public void deletebybufferId(int bufferId) throws Exception {
		try {
			mapper.deleteByPrimaryKey(bufferId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 查询附加流数量
	 * @param acbuffer
	 * @return
	 */
	public java.util.List<Acbuffer> appendBufferCount(Acbuffer acbuffer) {
		List<Acbuffer> buuferInfoList = null;

		try {
			buuferInfoList = mapper.appendBufferCount(acbuffer);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return buuferInfoList;
	}
	
	/**
	 * 
	 * @param acId
	 * @return
	 * @throws Exception
	 */
	public void deletebyAcId(int acId) throws Exception {
		try {
			mapper.deleteByacID(acId);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
