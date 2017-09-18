package com.nms.db.dao.ptn.port;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nms.db.bean.ptn.port.Acbuffer;

public interface AcbufferMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Acbuffer record);

    Acbuffer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Acbuffer record);

    int updateByPrimaryKey(Acbuffer record);

	public List<Acbuffer> query(int id);

	public int update(Acbuffer buffer);

	public int insert(Acbuffer buffer);

	List<Acbuffer> appendBufferCount(Acbuffer acbuffer);

	void deleteByacID(int acId);

	List<Acbuffer> selectForCondition(Acbuffer bufferAction);
	
	public void deleteBySiteId(@Param("siteId")int siteId);
}