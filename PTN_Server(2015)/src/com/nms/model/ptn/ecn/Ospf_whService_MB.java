package com.nms.model.ptn.ecn;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.ecn.OSPFinfo_wh;
import com.nms.db.dao.ptn.ecn.OSPFinfo_whMapper;
import com.nms.model.util.ObjectService_Mybatis;

public class Ospf_whService_MB extends ObjectService_Mybatis{
	
	private OSPFinfo_whMapper finfoWhMapper;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}

	public OSPFinfo_whMapper getFinfoWhMapper() {
		return finfoWhMapper;
	}

	public void setFinfoWhMapper(OSPFinfo_whMapper finfoWhMapper) {
		this.finfoWhMapper = finfoWhMapper;
	}
	
	public int insert(List<OSPFinfo_wh> OSPFinfo_whs){
		int result = 0;
		finfoWhMapper.deleteBySiteId(OSPFinfo_whs.get(0).getSiteId());
		if(OSPFinfo_whs.size() == 1){
			if(OSPFinfo_whs.get(0).isHas()){
				for (int i = 0; i < OSPFinfo_whs.size(); i++) {
					finfoWhMapper.insert(OSPFinfo_whs.get(i));
				}
			}
		}else{
			for (int i = 0; i < OSPFinfo_whs.size(); i++) {
				finfoWhMapper.insert(OSPFinfo_whs.get(i));
			}
		}
		this.sqlSession.commit();
		return result;
	}
	
	public int update(List<OSPFinfo_wh> OSPFinfo_whs){
		int result = 0;
		
		for (int i = 0; i < OSPFinfo_whs.size(); i++) {
			OSPFinfo_whs.get(i).setId(0);
			finfoWhMapper.insert(OSPFinfo_whs.get(i));
		}
		this.sqlSession.commit();
		return result;
	}
	
	public List<OSPFinfo_wh> selectBySiteid(int siteId){
		List<OSPFinfo_wh> ospFinfoWhs = null;
		ospFinfoWhs = this.finfoWhMapper.selectBysiteId(siteId);
		return ospFinfoWhs;
	}
	
	public int deleteBySiteId(int siteId){
		finfoWhMapper.deleteBySiteId(siteId);
		this.sqlSession.commit();
		return 0;
	}
}
