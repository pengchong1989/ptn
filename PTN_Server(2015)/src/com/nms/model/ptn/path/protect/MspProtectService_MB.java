package com.nms.model.ptn.path.protect;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.Businessid;
import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.bean.ptn.path.protect.MspProtect;
import com.nms.db.dao.ptn.BusinessidMapper;
import com.nms.db.dao.ptn.SiteRoateMapper;
import com.nms.db.dao.ptn.path.protect.MspProtectMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.ERotateType;
import com.nms.db.enums.EServiceType;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ExceptionManage;

public class MspProtectService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private MspProtectMapper MspProtectMapper;

	public MspProtectMapper getMspProtectMapper() {
		return MspProtectMapper;
	}

	public void setMspProtectMapper(MspProtectMapper MspProtectMapper) {
		this.MspProtectMapper = MspProtectMapper;
	}
	

	public List<MspProtect> selectBySite(int siteId) {
		// 设置查询条件
		MspProtect mspProtect = new MspProtect();
		mspProtect.setSiteId(siteId);
		return this.MspProtectMapper.query(mspProtect);
	}
	
	/**
	 * 通过条件查询
	 * @param mspProtect 条件对象
	 * @return
	 * @throws Exception
	 */
	public List<MspProtect> select(MspProtect mspProtect) throws Exception {
		return  this.MspProtectMapper.query(mspProtect);
	}
	
	public int saveOrUpdate(MspProtect mspProtect) throws BusinessIdException, Exception {
		int result = 0;
		SiteRoate siteRoate=null;
		SiteRoateMapper siteRoateMapper=null;
		Businessid businessid = null;
		BusinessidMapper businessidMapper=null;
		try{
			this.sqlSession.getConnection().setAutoCommit(false);
			siteRoate=new SiteRoate();
			siteRoate.setType("msp");
			siteRoate.setSiteId(mspProtect.getSiteId());
			siteRoateMapper=this.sqlSession.getMapper(SiteRoateMapper.class);
			// 如果主键为0 说明应做插入操作
			if(0!=mspProtect.getRotateOrder()){
				if(mspProtect.getRotateOrder()!=ERotateType.CLEAR.getValue() && mspProtect.getRotateOrder()!=ERotateType.LOCK.getValue()){
					if(mspProtect.getRotateOrder()==ERotateType.FORCESWORK.getValue()||mspProtect.getRotateOrder()==ERotateType.MANUALWORK.getValue()
							||mspProtect.getRotateOrder()==ERotateType.PRACTICEJOB.getValue()){
						mspProtect.setNowWorkPortId(mspProtect.getWorkPortId());
					}else{
						mspProtect.setNowWorkPortId(mspProtect.getProtectPortId());
					}
				}
			}
			if (mspProtect.getId() == 0) {
				
				 businessidMapper = this.sqlSession.getMapper(BusinessidMapper.class);
				// 如果没有businessId 说明是界面插入，需要从businessid管理表中取businessid
				if (mspProtect.getBusinessId() == 0) {
					businessid = businessidMapper.queryList(mspProtect.getSiteId(), "msp").get(0);
				} else {
					businessid = businessidMapper.queryByIdValueSiteIdtype(mspProtect.getBusinessId(), mspProtect.getSiteId(), "msp");
				}

				// 如果没有查询到。 抛给页面一个异常
				if (businessid == null) {
					throw new BusinessIdException("acSerBusinessId is null");
				}
				mspProtect.setBusinessId(businessid.getIdValue());

				// 修改此id为已用状态
				Businessid bs=new Businessid();
				bs.setId(businessid.getId());
				bs.setIdStatus(1);
				businessidMapper.update(bs);

				// 新增msp
				this.MspProtectMapper.insert(mspProtect);
				result = mspProtect.getId();
				/*
				 * 新增msp 成功时，新增 msp的倒换信息
				 */
				if(result>0){
					siteRoate.setTypeId(result);
					siteRoate.setRoate(mspProtect.getRotateOrder());
					siteRoateMapper.insert(siteRoate);
				}

				//离线网元数据下载
				super.dateDownLoad(mspProtect.getSiteId(),result, EServiceType.MSPPROTECT.getValue(), EActionType.INSERT.getValue(), mspProtect.getBusinessId()+"",null,0,0,null);
				
				
			} else {
				result = this.MspProtectMapper.update(mspProtect);
				if(result>0){
					siteRoate.setRoate(mspProtect.getRotateOrder());
					siteRoateMapper.update(siteRoate);
				}
				//离线网元数据下载
				super.dateDownLoad(mspProtect.getSiteId(),mspProtect.getId(), EServiceType.MSPPROTECT.getValue(), EActionType.UPDATE.getValue(),mspProtect.getBusinessId()+"",null,0,0,null);
			}
			if(!(this.sqlSession.getConnection().getAutoCommit())){
				sqlSession.commit();
			}
		} catch (Exception e) {
			sqlSession.rollback();
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			sqlSession.getConnection().setAutoCommit(true);
			// 释放对象
			businessid = null;
			siteRoateMapper = null;
			businessidMapper=null;
		}
		return result;

	}

	/**
	 * 根据条件删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void delete(MspProtect mspProtect) throws Exception {
		
		SiteRoateMapper siteRoateMapper=null;
		SiteRoate siteRoate=null;
		int result=0;
		BusinessidMapper businessidMapper=null;
		try{
			this.sqlSession.getConnection().setAutoCommit(false);
			// 修改此Businessid为未用状态
			businessidMapper = this.sqlSession.getMapper(BusinessidMapper.class);
			Businessid businessid = businessidMapper.queryByIdValueSiteIdtype(mspProtect.getBusinessId(), mspProtect.getSiteId(), "msp");
			Businessid bs=new Businessid();
			bs.setId(businessid.getId());
			bs.setIdStatus(0);
			businessidMapper.update(bs);
			//删除MSP数据
			result=this.MspProtectMapper.delete(mspProtect);
			
			/**
			 * 根据网元删除msp 成功时
			 *   删除 有关此网元msp 的倒换信息
			 */
			siteRoate=new SiteRoate();
			siteRoate.setType("msp");
			siteRoateMapper=this.sqlSession.getMapper(SiteRoateMapper.class);
			if(result>0){				
				//根据主键删除
				siteRoate.setSiteId(mspProtect.getSiteId());
				siteRoate.setTypeId(mspProtect.getId());									
				siteRoateMapper.delete(siteRoate);			
			}
			//离线网元数据下载
			super.dateDownLoad(mspProtect.getSiteId(),mspProtect.getId(), EServiceType.MSPPROTECT.getValue(), EActionType.DELETE.getValue(), mspProtect.getBusinessId()+"",mspProtect.getNowWorkPortId()+"",0,0,null);
		} catch (Exception e) {
			sqlSession.rollback();
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			sqlSession.getConnection().setAutoCommit(true);
			siteRoate=null;
			siteRoateMapper=null;
		}
	}

	/**
	 * 根据网元删除
	 * 
	 * @param siteId
	 *            网元id
	 * @throws Exception
	 */
	public void deleteBySiteId(int siteId) throws Exception {
		SiteRoateMapper siteRoateMapper=null;
		SiteRoate siteRoate=null;
		int result=0;
		try{
			this.sqlSession.getConnection().setAutoCommit(false);
			result=this.MspProtectMapper.deleteBySiteId(siteId);
			/**
			 * 根据网元删除msp 成功时
			 *   删除 有关此网元msp 的倒换信息
			 */
			if(result>0){
				siteRoate=new SiteRoate();
				siteRoate.setSiteId(siteId);
				siteRoate.setType("msp");
				siteRoateMapper=this.sqlSession.getMapper(SiteRoateMapper.class);
				siteRoateMapper.delete(siteRoate);
			}
		} catch (Exception e) {
			sqlSession.rollback();
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			sqlSession.getConnection().setAutoCommit(true);
			siteRoate=null;
			siteRoateMapper=null;
		}
		
	}

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 *            主键
	 * @return
	 * @throws Exception
	 */
	public MspProtect selectById(int id) throws Exception {
		// 设置查询条件
		MspProtect mspProtect = null;
		mspProtect = new MspProtect();
		mspProtect.setId(id);

		List<MspProtect> mspProtectList = this.MspProtectMapper.query(mspProtect);

		if (null != mspProtectList && mspProtectList.size() == 1) {
			return mspProtectList.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 修改设备是否存在的状态
	 * @param siteId 网元ID
	 * @param status	状态值
	 */
	public void updateActiveStatus(int siteId, int status) {
		try {
			this.MspProtectMapper.updateActiveStatus(siteId,status);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
