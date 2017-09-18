package com.nms.model.ptn.path.pw;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.path.pw.PwNniInfo;
import com.nms.db.dao.ptn.path.pw.PwNniInfoMapper;
import com.nms.db.enums.EManufacturer;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;

public class PwNniInfoService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private PwNniInfoMapper mapper;

	public PwNniInfoMapper getPwNniInfoMapper() {
		return mapper;
	}

	public void setPwNniInfoMapper(PwNniInfoMapper PwNniInfoMapper) {
		this.mapper = PwNniInfoMapper;
	}

	public List<PwNniInfo> select(PwNniInfo pwinfo) {
		List<PwNniInfo> pwinfoList = null;
		try {
			pwinfoList = this.mapper.queryByCondition(pwinfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return pwinfoList;
	}

	public int delete(int id) {
		int result = 0;
		try {
			result = this.mapper.delete(id);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	public int updateVlan(List<PwNniInfo> pwNniList) {
		int result = 0;
		try {
			SiteService_MB siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
			if(pwNniList!=null && pwNniList.size()>0){
				for(PwNniInfo info : pwNniList){
					int type = siteService.getManufacturer(info.getSiteId());
					int count = 0;
					if(type == EManufacturer.CHENXIAO.getValue()){
						count = this.mapper.updateVlan4CX(info);
					}else{
						count = this.mapper.updateVlan(info);
					}
					if(count>0){
						result++;
					}
				}				
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	public int updateVlan(PwNniInfo pwinfo) throws Exception {
		if(!(pwinfo.getSiteId()>0&&pwinfo.getPwId()>0)){
			throw new Exception("更新Vlan失败，未找到网元或者PW!!");
		}
		int result = 0;
		try {
			SiteService_MB siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
			int type = siteService.getManufacturer(pwinfo.getSiteId());
			if(type == EManufacturer.CHENXIAO.getValue()){
				result = this.mapper.updateVlan4CX(pwinfo);
			}else{
				result = this.mapper.updateVlan(pwinfo);
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	public int saveOrUpdate(PwNniInfo pwinfo) throws Exception {
		if (pwinfo == null) {
			throw new Exception("pwinfo is null");
		}
		int result = 0;
		try {
			if (pwinfo.getId() == 0) {		
				result = this.mapper.insert(pwinfo);			
			} else {
				result = this.mapper.update(pwinfo);
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}

	public int updateLanId(PwNniInfo pwinfo) throws Exception {
		if(pwinfo.getId()==0){
			throw new Exception("更新PW LANID出错");
		}
		int result = 0;

		try {
			result = this.mapper.updateLanId(pwinfo);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
}
