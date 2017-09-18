package com.nms.model.ptn.oam;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.oam.OamEthernetInfo;
import com.nms.db.dao.ptn.oam.OamEthernetInfoMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class OamEthreNetService_MB extends ObjectService_Mybatis {
	private OamEthernetInfoMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(OamEthernetInfoMapper mapper1) {
		this.mapper = sqlSession.getMapper(OamEthernetInfoMapper.class);
	}
	
	/**
	 * 查询
	 * @param oamEthernetInfo 单查询
	 * @return 
	 * @throws Exception
	 */
	public List<OamEthernetInfo> queryByNeIDSide(OamEthernetInfo oamEthernetInfo) throws Exception {
		if (oamEthernetInfo == null) {
			throw new Exception("oamEthernetInfo is null");
		}
		List<OamEthernetInfo> ccnList = null;
		try {
			ccnList = this.mapper.queryOamLinkInfoByConditionSide(oamEthernetInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ccnList;
	}  
	
	public void saveOrUpdate(OamEthernetInfo oamInfo) throws Exception{
		
		if (oamInfo == null) {
			throw new Exception("oamEthernetInfo is null");
		}
		try {
			
			if(!checkOamMepIsExist(oamInfo)){
				 this.mapper.insert(oamInfo);
			}else{
				this.mapper.update(oamInfo);
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
	}
	
	/*
	 * 判断该ethOam是否存在
	 */
	public boolean checkOamMepIsExist(OamEthernetInfo oamInfo) {
		List<OamEthernetInfo> list=null;
		try {
			list = this.queryByNeID(oamInfo);
			if (list!=null&&list.size()>0) {
				return true;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
		return false;
	}
	
	public List<OamEthernetInfo> queryByNeID(OamEthernetInfo oamEthernetInfo) throws Exception {
		if (oamEthernetInfo == null) {
			throw new Exception("oamEthernetInfo is null");
		}
		List<OamEthernetInfo> ccnList = new ArrayList<OamEthernetInfo>();
		try {
			ccnList = this.mapper.queryOamLinkInfoByCondition(oamEthernetInfo);
			if(ccnList == null){
				return new ArrayList<OamEthernetInfo>();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ccnList;
	}
}
