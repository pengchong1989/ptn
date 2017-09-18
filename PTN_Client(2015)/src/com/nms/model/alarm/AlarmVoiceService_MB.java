package com.nms.model.alarm;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.dao.alarm.AlarmVoiceInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.ptn.alarm.model.AlarmVoiceInfo;

public class AlarmVoiceService_MB extends ObjectService_Mybatis {
	private AlarmVoiceInfoMapper mapper = null;

	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}

	public void setMapper(AlarmVoiceInfoMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 查询所有的声音设置
	 * @throws Exception
	 */
	public List<AlarmVoiceInfo> queryAllVoice() throws Exception{
		return this.mapper.queryAllVoice();
	}
	
	/**
	 * 插入所有的数据
	 * @throws Exception 
	 */
	public int insertAllVoice(AlarmVoiceInfo voice) throws Exception{
		int result = this.mapper.insertAllVoice(voice);
		this.sqlSession.commit();
		return result;
	}
	
	/**
	 * 修改所有的数据
	 * @param voice
	 * @throws Exception 
	 */
	public int updateAllVoice(AlarmVoiceInfo voice) throws Exception{
		int result = this.mapper.updateAllVoice(voice);
		this.sqlSession.commit();
		return result;
	}
}
