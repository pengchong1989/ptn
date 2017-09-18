package com.nms.model.ptn.port;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.Businessid;
import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.bean.ptn.path.protect.DualProtect;
import com.nms.db.bean.ptn.path.protect.DualRelevance;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.dao.ptn.BusinessidMapper;
import com.nms.db.dao.ptn.SiteRoateMapper;
import com.nms.db.dao.ptn.path.tunnel.TunnelMapper;
import com.nms.db.dao.ptn.port.DualProtectMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EDualProtectType;
import com.nms.db.enums.EServiceType;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;

public class DualProtectService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private DualProtectMapper dualProtectMapper;

	public DualProtectMapper getDualProtectMapper() {
		return dualProtectMapper;
	}

	public void setDualProtectMapper(DualProtectMapper dualProtectMapper) {
		this.dualProtectMapper = dualProtectMapper;
	}
	
	/**
	 * 通过网元ID查询
	 * @param siteId 网元ID
	 * @return
	 */
	public List<DualProtect> selectBySite(int siteId) {
		TunnelMapper tunnelMapper = null;
		DualProtect dualProtectSel = null;
		dualProtectSel = new DualProtect();
		dualProtectSel.setSiteId(siteId);
		List<DualProtect> dualProtectList = null;
		Tunnel BreakoverTunnel;
		Tunnel relevanceTunnel;
		List<Tunnel> relevanceTunnelList;
		DualRelevance dualRelevanceSel;
		List<DualRelevance> dualRelevanceList;
		List<Tunnel> relevanceTunnelListSel;
		DualRelevanceService_MB dualRelevanceServiceMB = null;
		try {
			tunnelMapper = sqlSession.getMapper(TunnelMapper.class);
			dualRelevanceServiceMB = (DualRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALRELEVANCE, this.sqlSession);
			relevanceTunnelList = new ArrayList<Tunnel>();
			BreakoverTunnel = new Tunnel();
			relevanceTunnel = new Tunnel();
			dualProtectList =  this.dualProtectMapper.queryByCondition(dualProtectSel);
			//添加没一个 穿通 和 关联Tunnel
			for(DualProtect dualProtect:dualProtectList){
				dualRelevanceSel = new DualRelevance();
				dualRelevanceSel.setSiteId(dualProtect.getSiteId());
				dualRelevanceSel.setObjId(dualProtect.getId());
				dualRelevanceSel.setDualGroupId(dualProtect.getDualRelevanceGroupId());
				dualRelevanceList = dualRelevanceServiceMB.queryByCondition(dualRelevanceSel);
				for(DualRelevance dualRelevance:dualRelevanceList){
					//如果是穿通tunnel
					if(EDualProtectType.BREAKOVER.getValue()==dualRelevance.getObjType()){
						BreakoverTunnel = tunnelMapper.queryBySiteIdAndTunnelId(dualRelevance.getSiteId(), dualRelevance.getTunnelId());
						dualProtect.setBreakoverTunnel(BreakoverTunnel);
					}else{//如果是关联tunnel
						relevanceTunnel.setTunnelId(dualRelevance.getTunnelId());
						relevanceTunnelListSel = tunnelMapper.queryByCondition_nojoin(relevanceTunnel);
						if(null!=relevanceTunnelListSel&&relevanceTunnelListSel.size()==1){
							relevanceTunnelList.add(relevanceTunnelListSel.get(0));
						}
					}
				}
				dualProtect.setRelevanceTunnelList(relevanceTunnelList);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
//			UiUtil.closeService(dualRelevanceService);
		}
		return dualProtectList;
	}
	
	/**
	 * 通过条件查询
	 * @param siteId 网元ID
	 * @return
	 */
	public List<DualProtect> queryByCondition(DualProtect condition) {
		TunnelMapper tunnelMapper = null;
		DualProtect dualProtectSel = condition;
		List<DualProtect> dualProtectList = null;
		Tunnel BreakoverTunnel;
		Tunnel relevanceTunnel;
		DualRelevance dualRelevanceSel;
		List<Tunnel> relevanceTunnelList;
		List<DualRelevance> dualRelevanceList;
		List<Tunnel> relevanceTunnelListSel;
		DualRelevanceService_MB dualRelevanceService = null;
		try {
			dualRelevanceService = (DualRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALRELEVANCE, this.sqlSession);
			relevanceTunnelList = new ArrayList<Tunnel>();
			BreakoverTunnel = new Tunnel();
			relevanceTunnel = new Tunnel();
			dualProtectList =  this.dualProtectMapper.queryByCondition(dualProtectSel);
			tunnelMapper = sqlSession.getMapper(TunnelMapper.class);
			//添加没一个 穿通 和 关联Tunnel
			for(DualProtect dualProtect:dualProtectList){
				dualRelevanceSel = new DualRelevance();
				dualRelevanceSel.setSiteId(dualProtect.getSiteId());
				dualRelevanceSel.setObjId(dualProtect.getId());
				dualRelevanceSel.setDualGroupId(dualProtect.getDualRelevanceGroupId());
				dualRelevanceList = dualRelevanceService.queryByCondition(dualRelevanceSel);
				for(DualRelevance dualRelevance:dualRelevanceList){
					//如果是穿通tunnel
					if(EDualProtectType.BREAKOVER.getValue()==dualRelevance.getObjType()){
						BreakoverTunnel = tunnelMapper.queryBySiteIdAndServiceId(dualRelevance.getSiteId(), dualRelevance.getTunnelId());
						dualProtect.setBreakoverTunnel(BreakoverTunnel);
					}
					//如果是关联tunnel
					else{
						relevanceTunnel.setTunnelId(dualRelevance.getTunnelId());
						relevanceTunnelListSel = tunnelMapper.queryByCondition_nojoin(relevanceTunnel);
						if(null!=relevanceTunnelListSel&&relevanceTunnelListSel.size()==1){
							relevanceTunnelList.add(relevanceTunnelListSel.get(0));
						}
					}
				}
				dualProtect.setRelevanceTunnelList(relevanceTunnelList);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
//			UiUtil.closeService(dualRelevanceService);
		}
		return dualProtectList;
	}
	
	/**
	 * 添加修改方法
	 * @param dualProtect 双规保护对象
	 * @return
	 */
	public int saveOrUpdate(DualProtect dualProtect) {
		DualRelevance dualRelevance;
		int dualId = 0;
		SiteRoate siteRoate=null;
		SiteRoateMapper siteRoateMapper=null;
		DualRelevanceService_MB dualRelevanceService = null;
		try {
			dualRelevanceService = (DualRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALRELEVANCE, this.sqlSession);
			dualRelevance = new DualRelevance();
			//新建双规保护时，添加双规的倒换
			siteRoateMapper=sqlSession.getMapper(SiteRoateMapper.class);
			siteRoate=new SiteRoate();
			siteRoate.setType("dual");
			siteRoate.setSiteId(dualProtect.getSiteId());
			//tunnel  关联数据保存
			dualRelevance.setObjId(dualId);
			dualRelevance.setSiteId(dualProtect.getSiteId());
			dualRelevance.setDualGroupId(dualProtect.getBreakoverTunnel().getTunnelId());
			dualProtect.setDualRelevanceGroupId(dualProtect.getBreakoverTunnel().getTunnelId());
			if(0==dualProtect.getId()){
				Businessid businessid = null;
				BusinessidMapper businessidMapper = sqlSession.getMapper(BusinessidMapper.class);

				// 如果没有businessId 说明是界面插入，需要从businessid管理表中取businessid
				if (dualProtect.getBusinessId() == 0) {
					businessid = businessidMapper.queryList(dualProtect.getSiteId(), "dual").get(0);
				} else {
					businessid = businessidMapper.queryByIdValueSiteIdtype(dualProtect.getBusinessId(), dualProtect.getSiteId(), "dual");
				}

				// 如果没有查询到。 抛给页面一个异常
				if (businessid == null) {
					throw new BusinessIdException("dualBusinessId is null");
				}
				dualProtect.setBusinessId(businessid.getIdValue());
				// 修改此id为已用状态
				businessid.setIdStatus(1);
				businessidMapper.update(businessid);
				
				this.dualProtectMapper.insert(dualProtect);
				dualId = dualProtect.getId();
				/*
				 * 新增双规保护成功时，新增 双规的倒换信息
				 */
				if(dualId>0){
					siteRoate.setTypeId(dualId);
					siteRoate.setRoate(-1);
					siteRoateMapper.insert(siteRoate);
				}

				//离线网元数据下载
				super.dateDownLoad(dualProtect.getSiteId(),dualId, EServiceType.DUAL.getValue(), EActionType.INSERT.getValue());
			}else{
				dualId = this.dualProtectMapper.updateByPrimaryKey(dualProtect);
				siteRoate.setRoate(dualProtect.getRotateOrder());
				dualRelevanceService.delete(dualRelevance);
				//离线网元数据下载
				super.dateDownLoad(dualProtect.getSiteId(),dualId, EServiceType.DUAL.getValue(), EActionType.UPDATE.getValue());
			}
			//tunnel  关联数据保存
			dualRelevance.setObjId(dualId);
			dualRelevance.setSiteId(dualProtect.getSiteId());
			dualRelevance.setDualGroupId(dualProtect.getBreakoverTunnel().getTunnelId());
			//穿通tunnel  数据保存
			dualRelevance.setObjType(EDualProtectType.BREAKOVER.getValue());
			dualRelevance.setTunnelId(dualProtect.getBreakoverTunnel().getTunnelId());
			dualRelevanceService.saveOrUpdate(dualRelevance);
			//关联tunnel  数据保存
			for(Tunnel tunnel:dualProtect.getRelevanceTunnelList()){
				dualRelevance.setObjType(EDualProtectType.RELEVANCE.getValue());
				dualRelevance.setTunnelId(tunnel.getTunnelId());
				dualRelevanceService.saveOrUpdate(dualRelevance);
			}
			sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
//			UiUtil.closeService(dualRelevanceService);
		}
		return dualId;
	}
	
	/**
	 * 删除方法
	 * @param dualProtect 双规保护对象
	 */
	public void delete(DualProtect dualProtect) {
		DualRelevance dualRelevance;
		int result=0;
		SiteRoateMapper siteRoateMapper=null;
		SiteRoate siteRoate=null;
		DualRelevanceService_MB dualRelevanceService = null;
		try {
			dualRelevanceService = (DualRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALRELEVANCE, this.sqlSession);
			dualRelevance = new DualRelevance();
			siteRoateMapper=sqlSession.getMapper(SiteRoateMapper.class);
			this.dualProtectMapper.deleteByPrimaryKey(dualProtect.getId());
			//删除双规的关系表
			dualRelevance.setDualGroupId(dualProtect.getDualRelevanceGroupId());
			result=dualRelevanceService.delete(dualRelevance);
			/**
			 * 删除双规保护时：删除双规的倒换
			 */
			if(result>0){
				siteRoate=new SiteRoate();
				siteRoate.setType("dual");
				siteRoate.setTypeId(dualProtect.getId());
				if(dualProtect.getSiteId()>0){
					siteRoate.setSiteId(dualProtect.getSiteId());
				}
				siteRoateMapper.delete(siteRoate);
			}
			// 修改此Businessid为未用状态
			BusinessidMapper businessidMapper = sqlSession.getMapper(BusinessidMapper.class);
			Businessid businessid = businessidMapper.queryByIdValueSiteIdtype(dualProtect.getBusinessId(), dualProtect.getSiteId(), "dual");
			businessid.setIdStatus(1);
			businessidMapper.update(businessid);
			
			//离线网元数据下载
			super.dateDownLoad(dualProtect.getSiteId(),dualProtect.getId(), EServiceType.DUAL.getValue(), EActionType.DELETE.getValue(), dualProtect.getBusinessId()+"",null,0,0,null);
			sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
//			UiUtil.closeService(dualRelevanceService);
		}
	}
	
	/**
	 * 修改状态（设备是否存在）
	 * @param siteId 网元ID
	 * @param status 状态值
	 */
	public void updateActiveStatus(int siteId, int status) {
		try {
			this.dualProtectMapper.updateActiveStatus(siteId,status);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
