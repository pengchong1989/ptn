package com.nms.model.alarm;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.alarm.TCAAlarm;
import com.nms.db.dao.alarm.CurrentAlarmInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public class TCAAlarmService_MB extends ObjectService_Mybatis {
	private CurrentAlarmInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}

	public void setMapper(CurrentAlarmInfoMapper mapper) {
		this.mapper = mapper;
	}
	
	public List<TCAAlarm> selectAll() throws Exception {
		List<TCAAlarm> tcaAlarmList = this.mapper.queryTCAAlarm(new TCAAlarm());
		if(tcaAlarmList != null && tcaAlarmList.size() > 0){
			for (TCAAlarm tcaAlarm : tcaAlarmList) {
				if (!"en_US".equals(ResourceUtil.language)) {
					tcaAlarm.setRemark_en(tcaAlarm.getRemark_zh());
				}
				tcaAlarm.setGranularity(this.getGranularity(tcaAlarm.getGranularity()));
				tcaAlarm.setThreshold(this.getGranularity(tcaAlarm.getThreshold()));
			}
		}
		return tcaAlarmList;
	}
	
	/**
	 * 根据perid获取粒度
	 * @param perid
	 * @return
	 * @throws Exception
	 */
	private String getGranularity(String perid) throws Exception {
		try {
			if ("1".equals(perid) || "2".equals(perid)) {
				return ResourceUtil.srcStr(StringKeysObj.OBJ_15_MINUTES);
			} else {
				return ResourceUtil.srcStr(StringKeysObj.OBJ_24_HOURS);
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
