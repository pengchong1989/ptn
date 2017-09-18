package com.nms.model.ptn.path.protect;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.Businessid;
import com.nms.db.bean.ptn.path.protect.PwProtect;
import com.nms.db.bean.system.Field;
import com.nms.db.dao.ptn.BusinessidMapper;
import com.nms.db.dao.ptn.path.protect.PwProtectMapper;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.system.FieldService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.ServiceFactory;
import com.nms.model.util.Services;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.util.Mybatis_DBManager;

public class PwProtectService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private PwProtectMapper PwProtectMapper;

	public PwProtectMapper getPwProtectMapper() {
		return PwProtectMapper;
	}

	public void setPwProtectMapper(PwProtectMapper PwProtectMapper) {
		this.PwProtectMapper = PwProtectMapper;
	}
	
	
	/**
	 * 条件查询
	 * @param pwProtect
	 * @return
	 */
	public List<PwProtect> select(PwProtect pwProtect){
		List<PwProtect> pwProtects = null;
		pwProtects = PwProtectMapper.queryByCondition(pwProtect);
		return pwProtects;
	}
	
	public static void main(String[] args) {
		try {
			Mybatis_DBManager.init("10.18.1.10");
			ConstantUtil.serviceFactory = new ServiceFactory();
			PwProtectService_MB protectServiceMB = (PwProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PWPROTECT);
			PwProtectService_MB protectServiceMB2 = (PwProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PWPROTECT);
			Field field = new Field();
			FieldService_MB fieldServiceMB = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			
			PwProtect pwProtect3 = protectServiceMB.getPwProtectMapper().selectByPrimaryKey(7);
			System.out.println("3++++"+pwProtect3.getBackType());
			PwProtect pwProtect = new PwProtect();
			pwProtect.setId(7);
			pwProtect.setBackType(14);
			protectServiceMB2.getPwProtectMapper().updateByPrimaryKey(pwProtect);
			protectServiceMB2.getSqlSession().commit();
			field.setGroupId(34);
			field.setId(3);
			fieldServiceMB.getMapper().updateField(field);
			fieldServiceMB.getSqlSession().commit();
//			fieldServiceMB.getMapper().updateByPrimaryKey(record);
			PwProtectService_MB protectServiceMB4 = (PwProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PWPROTECT);
			PwProtect pwProtect4 = protectServiceMB4.getPwProtectMapper().selectByPrimaryKey(7);
			protectServiceMB.setPwProtectMapper(protectServiceMB.getSqlSession().getMapper(PwProtectMapper.class));
			PwProtect pwProtect2 = protectServiceMB.getPwProtectMapper().selectByPrimaryKey(7);
			System.out.println("2++++"+pwProtect2.getBackType());
			System.out.println("4++++"+pwProtect4.getBackType());
			System.out.println(protectServiceMB2.getPwProtectMapper() == protectServiceMB.getPwProtectMapper());
			System.out.println(protectServiceMB2.getSqlSession() == protectServiceMB4.getSqlSession());
			System.out.println(fieldServiceMB.getMapper().selectByPrimaryKey(3).getGroupId());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
	
	/**
	 * 新增
	 * @param pwProtect
	 * @return
	 */
	public int insert(PwProtect pwProtect){
		Businessid businessid = null;
		SiteService_MB siteService = null;
		int result = 0;
		BusinessidMapper businessidMapper = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
			businessidMapper = sqlSession.getMapper(BusinessidMapper.class);
			if(pwProtect.getBusinessId() == 0)
			{
				businessid = businessidMapper.queryList(pwProtect.getSiteId(), "pwProtect").get(0);
			}else
			{
				businessid = businessidMapper.queryByIdValueSiteIdtype(pwProtect.getBusinessId(), pwProtect.getSiteId(), "pwProtect");
			}
			if(businessid == null){
				throw new BusinessIdException(siteService.getSiteName(pwProtect.getSiteId()) + ResourceUtil.srcStr(StringKeysTip.TIP_PWPROTECTID));
			}
			businessid.setIdStatus(1);
			businessidMapper.update(businessid);
			pwProtect.setBusinessId(businessid.getIdValue());
			result=PwProtectMapper.insert(pwProtect);
			sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
//			UiUtil.closeService(siteService);
		}
		return result;
	}
	
	/**
	 * 修改
	 * @param pwProtect
	 * @return
	 */
	public int update(PwProtect pwProtect){
		try {
			PwProtectMapper.updateByPrimaryKey(pwProtect);
			sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return 0;
	}
	
	/**
	 * 根据网元id删除
	 * @param pwProtect
	 * @return
	 */
	public int delete(PwProtect pwProtect){
		BusinessidMapper businessidMapper = null;
		try {
			// 释放id
			businessidMapper = sqlSession.getMapper(BusinessidMapper.class);
			Businessid businessId = new Businessid();
			businessId.setIdStatus(0);
			businessId.setIdValue(pwProtect.getBusinessId());
			businessId.setType("pwProtect");
			businessId.setSiteId(pwProtect.getSiteId());
			businessidMapper.updateBusinessid(businessId);
			
			PwProtectMapper.deleteByPrimaryKey(pwProtect.getId());
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return 0;
	}
	
}
