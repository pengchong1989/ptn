package com.nms.model.ptn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.Businessid;
import com.nms.db.bean.ptn.CccInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.dao.ptn.BusinessidMapper;
import com.nms.db.dao.ptn.CccInfoMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;


public class CccService_MB extends ObjectService_Mybatis {

	    private CccInfoMapper mapper = null;
		public void setPtnuser(String ptnuser) {
			super.ptnuser = ptnuser;
		}

		public void setSqlSession(SqlSession sqlSession) {
			super.sqlSession = sqlSession;
		}
		
		public CccInfoMapper getMapper() {
			return mapper;
		}

		public void setMapper(CccInfoMapper mapper) {
			this.mapper = mapper;
		}
		
		private BusinessidMapper businessidMapper = null;
		private final static int ISUSEDSTATUS = 1;
		
		/*
		 * 保存ccc业务
		 */
		public List<Integer> insert(CccInfo cccInfo) throws Exception, BusinessIdException {
			
			if (cccInfo== null) {
				throw new Exception("cccinfo is null");
			}
			List<Integer> resultList = new ArrayList<Integer>();		
			int cccid = 0;
			List<CccInfo> DBcccList = null;
			int cccServierId =1;
			List<Integer> max=this.mapper.selectMaxServiceId();
			if(max!=null && max.size()!=0 && max.get(0)!=null){
				cccServierId=max.get(0)+1;				
			}
			Businessid rootXCIdInfo = null;
			AcPortInfoService_MB acService = null;
			SiteService_MB siteService = null;
			
			try {
				this.sqlSession.getConnection().setAutoCommit(false);	
				businessidMapper = this.sqlSession.getMapper(BusinessidMapper.class);
				acService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo, this.sqlSession);
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
					if (cccInfo.getaXcId() == 0) {						
						rootXCIdInfo = businessidMapper.queryList(cccInfo.getaSiteId(),"ccc").get(0);
					} else {
						rootXCIdInfo = businessidMapper.queryByIdValueSiteIdtype(cccInfo.getaXcId(),cccInfo.getaSiteId(),"ccc");
					}
					if (null == rootXCIdInfo) {
						throw new BusinessIdException(siteService.getSiteName(cccInfo.getaSiteId()) + ResourceUtil.srcStr(StringKeysTip.TIP_CCCID));
					}
					Businessid bus=new Businessid();
					bus.setId(rootXCIdInfo.getId());
					bus.setIdStatus(ISUSEDSTATUS);				
					businessidMapper.update(bus);
				
					DBcccList = new ArrayList<CccInfo>();
		
					//如果不等于空  axcId == 业务ID 否则为叶子节点 axcId == 0
					if (null != rootXCIdInfo) {
						cccInfo.setaXcId(rootXCIdInfo.getIdValue());
					}
					if (cccInfo.getId() == 0) {
						cccInfo.setServiceId(cccServierId);
					}							
					DBcccList.add(cccInfo);
				
				for (CccInfo DBInfo : DBcccList) {
					this.mapper.insert(DBInfo);
					cccid = DBInfo.getId();
					DBInfo.setId(cccid);								
					// 被业务使用的acId列表
					analysisAC(DBInfo,acService);
				}
				
				// 离线网元数据下载
				for (CccInfo ccInfo : DBcccList) {
					if (0 != ccInfo.getaSiteId()) {
						super.dateDownLoad(ccInfo.getaSiteId(), ccInfo.getId(), EServiceType.CCC.getValue(), EActionType.INSERT.getValue(), cccInfo.getServiceId() + "", null, 0, 0, null);
					}
					
				}
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (BusinessIdException e) {
				this.sqlSession.getConnection().rollback();
				throw e;
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
				this.sqlSession.getConnection().rollback();
			} finally {
				this.sqlSession.getConnection().setAutoCommit(true);
			}
			return resultList;
		}


		private void analysisAC(CccInfo dBInfo, AcPortInfoService_MB acService) 
		{
			try 
			{
				updateACState(dBInfo.getAmostAcId(),acService,1,true);			
			} catch (Exception e) 
			{
				ExceptionManage.dispose(e, getClass());
			}		
		}

		/**
		 * 释放 和结合AC
		 * @param amostIds
		 * @param acService
		 * @param label 0 表示释放  1 表示结合AC
		 */
		private void updateACState(String amostIds,AcPortInfoService_MB acService,int label,boolean isRelateAc)
		{
			Set<Integer> acSet = new HashSet<Integer>();
			UiUtil uiutil = new UiUtil();
			try {
				acSet = uiutil.getAcIdSets(amostIds);
				if(acSet.size() >0)
				{
					for(Integer acId : acSet)
					{
						if(isRelateAc)
						{
							analysisAc(acService,acId,label);
						}else
						{
							if(!isRelatedAC(acId))
							{
								analysisAc(acService,acId,label);
							}
						}
					}
				}
			} catch (Exception e) 
			{
				ExceptionManage.dispose(e, getClass());
			}finally
			{
				acSet = null;
				uiutil = null;
			}
		}
		
		/**
		 * 在删除之前判断着AC是否存在其他的业务的关联
		 * @param AcId
		 * @return true存在 false 不存在
		 */
		private boolean isRelatedAC(int acId){
			List<String> azAcIds = null;
			Set<Integer> azAcSet = new HashSet<Integer>();
			UiUtil uiUtil = new UiUtil();
			boolean isRelatedAc = false;
			
			try {
				List<CccInfo> ccc=this.mapper.isRelatedAcByEline(acId);
				if(ccc!=null && ccc.size()!=0){
					isRelatedAc=true;
				}
				if(isRelatedAc){
				   return true;
				}else{					
					List<CccInfo> etreeList=this.mapper.isRelatedAc();
					azAcIds = new ArrayList<String>();
					if(etreeList!=null && etreeList.size()!=0){
						for(int i=0;i<etreeList.size();i++){
							if(null!=etreeList.get(i).getAmostAcId() && !"".equals(etreeList.get(i).getAmostAcId())){
								azAcIds.add(etreeList.get(i).getAmostAcId());
							}
							if(null!=etreeList.get(i).getZmostAcId() && !"".equals(etreeList.get(i).getZmostAcId())){
								azAcIds.add(etreeList.get(i).getZmostAcId());
							}
						}
					}
										
					if(null != azAcIds && !azAcIds.isEmpty()){						
						for(String acid : azAcIds){
							azAcSet.addAll(uiUtil.getAcIdSets(acid));
						}
					}
					if(azAcSet.contains(acId)){
						return true;
					}
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e, getClass());
			}finally
			{
				 azAcIds = null;
				 azAcSet = null;
				 uiUtil = null;
			}
			return false;
		}
		
		
		private void analysisAc(AcPortInfoService_MB acService,int acId,int label)
		{
			AcPortInfo acPortInfo;
			try {
				acPortInfo = acService.selectById(acId);
				if(label == 0)
				{
					acPortInfo.setIsUser(0);
				}else
				{
					acPortInfo.setIsUser(1);
				}
				acService.updateUserType(acPortInfo);	
			} catch (Exception e) {
				ExceptionManage.dispose(e, getClass());
			}finally
			{
				acPortInfo = null;
			}
		}
		
		/**
		 * 通过acId,siteId查询line
		 * 
		 * @param acId
		 * @return
		 */
		public List<CccInfo> selectByAcIdAndSiteId(int acId, int siteId) {
			List<CccInfo> cccInfos = null;
			List<CccInfo> cccInsts = null;
			UiUtil uiUtil = null;
			try {
				cccInfos = this.mapper.queryByAcIdAndSiteIdCondition(siteId);
				if(null != cccInfos && !cccInfos.isEmpty())
				{
					uiUtil = new UiUtil();
					cccInsts = new ArrayList<CccInfo>();
					for(CccInfo cccInfo : cccInfos)
					{
					 if((uiUtil.getAcIdSets(cccInfo.getAmostAcId()).contains(acId) && cccInfo.getaSiteId() == siteId) )
					 {
						 cccInsts.add(cccInfo);
					 }
					}
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e, getClass());
			}finally
			{
				cccInfos = null;
				 uiUtil = null;
			}
			return cccInsts;
		}
		
		// 更新业务
		public void update(CccInfo cccInfo) throws Exception {

			if (null == cccInfo) {
				throw new Exception("cccinfo is null");
			}
			AcPortInfoService_MB acService = null;
			try {
				this.sqlSession.getConnection().setAutoCommit(false);			
				acService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo, this.sqlSession);	
					// 修改了端口
					if (cccInfo.getAction() == 1) {				
						// 修改了端口
						if (null != cccInfo.getBeforeAAcList()) {
							// 释放之前的ac对象
							for(AcPortInfo acportInst : cccInfo.getBeforeAAcList())
							{
								if(!isRelatedAC(acportInst.getId())){
									acportInst.setIsUser(0);
									acService.updateUserType(acportInst);
								}
							}
							// 关联新的AC
							updateACState(cccInfo.getAmostAcId(),acService,1,false);
						}					
					} 
					if(cccInfo.getAction()==1 || cccInfo.getAction()==0){
						this.mapper.update(cccInfo);
					}
					 

				// 离线网元数据下载		
				if (0 != cccInfo.getaSiteId()) {
					super.dateDownLoad(cccInfo.getaSiteId(), cccInfo.getServiceId(), EServiceType.CCC.getValue(), EActionType.UPDATE.getValue(), cccInfo.getServiceId() + "", null, 0, 0, null);
				}
				
				if(!this.sqlSession.getConnection().getAutoCommit()){
					this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				this.sqlSession.getConnection().rollback();
				throw e;
			} finally {
				this.sqlSession.getConnection().setAutoCommit(true);
			}

		}
		
		/*
		 * 查询某一条ccc业务(可能包含多条pw)
		 */
		public List<CccInfo> select(CccInfo cccInfo) throws Exception {
			List<CccInfo> cccInfoList = null;

			try {
				cccInfoList = this.mapper.queryByCondition(cccInfo);
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
			return cccInfoList;
		}
		
		public void updateStatusActivate(int siteId, int status) throws Exception {
			try {
				this.mapper.updateStatus(siteId, status);
				this.sqlSession.commit();
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
		}
		
		public void updateStatusActivate(List<Integer> idList, int status) throws Exception {
			try {
				this.mapper.updateStatusIds(idList, status);
				this.sqlSession.commit();
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
		}
		
		/*
		 * 删除一条ccc
		 */
		public int delete(int serviceId) throws Exception {

			int etreeResult = 0;
			List<CccInfo> cccInfoList = null;
			CccInfo cccInfo = null;			
			AcPortInfoService_MB acService = null;
			try {
				businessidMapper = this.sqlSession.getMapper(BusinessidMapper.class);
				acService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo, this.sqlSession);

				// 解除与pw和ac引用关系
				cccInfo = new CccInfo();
				cccInfo.setServiceId(serviceId);
				cccInfo.setServiceType(EServiceType.CCC.getValue());
				cccInfoList = this.select(cccInfo);
				for (CccInfo info : cccInfoList) {							
					// 释放ac 之前判断这条AC是否被其他的业务所使用
					updateACState(info.getAmostAcId(),acService,0,false);				
					// 释放id
					Businessid businessId = new Businessid();
					businessId.setIdStatus(0);
					businessId.setIdValue(info.getaXcId());
					businessId.setType("ccc");
					businessId.setSiteId(info.getaSiteId());
					businessidMapper.updateBusinessid(businessId);	

				}
				offLineAcion(cccInfoList, acService);
				etreeResult = this.mapper.delete(serviceId);
				this.sqlSession.commit();

			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			} finally {
			}
			return etreeResult;

		}

		
		/**
		 * 查询单网元下的所有ccc
		 * 
		 * @param siteId
		 *            网元id
		 * @return
		 * @throws Exception
		 */
		public List<CccInfo> selectNodeBySite(int siteId) throws Exception {

			List<CccInfo> cccInfoList = null;
			
			try {			
				cccInfoList = this.mapper.queryNodeBySite(siteId);			
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally {
			}
			return cccInfoList;
		}
		/**
		 * 离线网元操作
		 * 
		 * @param etreeInfoList
		 * @param pwService
		 * @throws Exception
		 */
		private void offLineAcion(List<CccInfo> cccInfoList, AcPortInfoService_MB acService) throws Exception {
			AcPortInfo acPortInfo;
			List<AcPortInfo> acPortInfoList;
			CccInfo offLineAction = cccInfoList.get(0);			
				if (0 != offLineAction.getaSiteId()) {
					acPortInfo = new AcPortInfo();
					acPortInfo.setId(offLineAction.getaAcId());
					acPortInfoList = acService.selectByCondition(acPortInfo);
					if (null != acPortInfoList && acPortInfoList.size() > 0) {
						acPortInfo = acPortInfoList.get(0);
						super.dateDownLoad(offLineAction.getaSiteId(), offLineAction.getServiceId(), EServiceType.CCC.getValue(), EActionType.DELETE.getValue(), offLineAction.getaXcId() + "", "", acPortInfo.getPortId(), acPortInfo.getAcBusinessId(), TypeAndActionUtil.ACTION_ROOT);
					}
				}						 
		}
		
		/**
		 * 
		 * 同步ccc查询方法
		 * @return
		 * @throws Exception
		 * 
		 * @Exception 异常对象
		 */
		public List<CccInfo> select_synchro(int siteId, int xcid) throws Exception {

			List<CccInfo> cccInfoList = null;
			try {
				cccInfoList = this.mapper.querySynchro(siteId, xcid);

			} catch (Exception e) {
				throw e;
			}
			return cccInfoList;
		}
		
		/**
		 * 单网元名称验证
		 * 
		 * @param afterName
		 * @param beforeName
		 * @param siteId
		 * @return
		 * @throws Exception
		 */
		public boolean nameRepetitionBySingle(String afterName, String beforeName, int siteId) throws Exception {

			int result = this.mapper.query_nameBySingle(afterName, beforeName, siteId);
			if (0 == result) {
				return false;
			} else {
				return true;
			}

		}
}
