package com.nms.model.ptn.path.protect;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.Businessid;
import com.nms.db.bean.ptn.path.protect.LoopProtectInfo;
import com.nms.db.dao.ptn.BusinessidMapper;
import com.nms.db.dao.ptn.path.protect.LoopProtectInfoMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.ptn.BusinessidService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.service.impl.util.SiteUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class WrappingProtectService_MB extends ObjectService_Mybatis {
	private LoopProtectInfoMapper mapper = null;
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(LoopProtectInfoMapper mapper2) {
		this.mapper = sqlSession.getMapper(LoopProtectInfoMapper.class);
	}

	public List<LoopProtectInfo> select(LoopProtectInfo loopCondition) {
		List<LoopProtectInfo> loopProtectInfos = null;
		SegmentService_MB segmentService = null;
		try {
			List<Segment> list = new ArrayList<Segment>();
			loopProtectInfos = this.mapper.queryByCondition(loopCondition);
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT, this.sqlSession);
			if (loopProtectInfos != null && loopProtectInfos.size() > 1) {// 带上对应的西向段
				for (int i = 0; i < loopProtectInfos.size(); i++) {
					Segment condition = new Segment();
					condition.setASITEID(loopProtectInfos.get(i).getSiteId());
					condition.setAPORTID(loopProtectInfos.get(i).getWestPort());
					list = segmentService.selectBySiteIdAndPort(condition);
					if (list.size() > 0) {
						Segment segment = list.get(0);
						loopProtectInfos.get(i).setWestSegment(segment);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return loopProtectInfos;
	}

	public int update(List<LoopProtectInfo> loopList) {
		int result = 0;
		Businessid wrappingSerBusinessId = null;
		LoopProtectInfo loopProtectSelect = null;
		LoopProtectInfo loopProtectResult = null;
		try {
			loopProtectSelect = new LoopProtectInfo();
			// 更新bussinessid和修改环保护数据
			BusinessidMapper busiMapper = this.sqlSession.getMapper(BusinessidMapper.class);
			for (LoopProtectInfo loopProtectInfo : loopList) {
				result = this.mapper.update(loopProtectInfo);
				loopProtectSelect.setSiteId(loopProtectInfo.getSiteId());
				loopProtectSelect.setEastPort(loopProtectInfo.getEastPort());
				loopProtectSelect.setWestPort(loopProtectInfo.getWestPort());
				loopProtectResult = this.select(loopProtectSelect).get(0);
				Businessid condition = new Businessid();
				if (loopProtectResult.getNodeId() != loopProtectInfo.getNodeId()) {
					condition.setIdValue(loopProtectResult.getNodeId());
					condition.setSiteId(loopProtectInfo.getSiteId());
					condition.setType("ringNode");
					wrappingSerBusinessId = busiMapper.query(condition).get(0);
					condition.setId(wrappingSerBusinessId.getId());
					condition.setIdStatus(0);
					busiMapper.update(condition);// 将环id设置为可用
					condition.setIdValue(loopProtectInfo.getNodeId());
					wrappingSerBusinessId = busiMapper.query(condition).get(0);
					condition.setId(wrappingSerBusinessId.getId());
					condition.setIdStatus(1);
					busiMapper.update(condition);// 将环id设置不为可用
				}
				if (loopProtectResult.getWestNodeId() != loopProtectInfo.getWestNodeId()) {
					condition.setIdValue(loopProtectResult.getWestNodeId());
					wrappingSerBusinessId = busiMapper.query(condition).get(0);
					condition.setId(wrappingSerBusinessId.getId());
					condition.setIdStatus(0);
					busiMapper.update(condition);// 将环id设置为可用
					condition.setIdValue(loopProtectInfo.getWestNodeId());
					wrappingSerBusinessId = busiMapper.query(condition).get(0);
					condition.setId(wrappingSerBusinessId.getId());
					condition.setIdStatus(1);
					busiMapper.update(condition);// 将环id设置不为可用
				}
				if (loopProtectResult.getEastNodeId() != loopProtectInfo.getEastNodeId()) {
					condition.setIdValue(loopProtectResult.getEastNodeId());
					wrappingSerBusinessId = busiMapper.query(condition).get(0);
					condition.setId(wrappingSerBusinessId.getId());
					condition.setIdStatus(0);
					busiMapper.update(condition);// 将环id设置为可用
					condition.setIdValue(loopProtectInfo.getEastNodeId());
					wrappingSerBusinessId = busiMapper.query(condition).get(0);
					condition.setId(wrappingSerBusinessId.getId());
					condition.setIdStatus(1);
					busiMapper.update(condition);// 将环id设置不为可用
				}
				//离线网元数据下载
				super.dateDownLoad(loopProtectInfo.getSiteId(),loopProtectInfo.getId(), EServiceType.LOOPPROTECT.getValue(), EActionType.UPDATE.getValue());
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	/**
	 * 查询所有，主面板显示用
	 */
	public List<LoopProtectInfo> select() {
		List<LoopProtectInfo> loopProtectInfos = new ArrayList<LoopProtectInfo>();
		SegmentService_MB segmentServiceMB = null;
		Segment segment = null;
		try {
			loopProtectInfos = mapper.selectAll();
			segmentServiceMB = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT, this.sqlSession);

			if (loopProtectInfos != null && loopProtectInfos.size() > 1) {// 带上对应的西向段
				for (int i = 0; i < loopProtectInfos.size(); i++) {
					segment = new Segment();
					segment.setASITEID(loopProtectInfos.get(i).getSiteId());
					segment.setAPORTID(loopProtectInfos.get(i).getWestPort());
					List<Segment> segments = segmentServiceMB.selectBySiteIdAndPort(segment);
					if(segments != null && segments.size()>0){
						segment = segments.get(0);
						loopProtectInfos.get(i).setWestSegment(segment);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			segment = null;
		}
		return loopProtectInfos;
	}
	
	/**
	 * 名字是否重复
	 * @param afterName
	 * @param beforeName
	 * @return
	 */
	public boolean nameRepetition(String afterName, String beforeName) {
		int result = 0;
		try {
			result = this.mapper.query_name(afterName, beforeName);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		if(0== result){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 环搜索
	 * @param siteInstList
	 *            网元集合
	 * @return
	 * @throws Exception
	 */
	public String searchLoopProtect(List<SiteInst> siteInstList) throws Exception {
		List<LoopProtectInfo> loopProtectListAll = new ArrayList<LoopProtectInfo>();
		List<LoopProtectInfo> loopProtectExitList = new ArrayList<LoopProtectInfo>();
		List<LoopProtectInfo> loopProtectActionList = null;
		LoopProtectInfo loopProtectInfoWest = new LoopProtectInfo();
		List<Segment> segmentList;
		Segment segment = null;
		int loopId;
		int count = 0;
		SegmentService_MB segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT, this.sqlSession);
		for (SiteInst siteInst : siteInstList) { // 查找所选网元下的所有但网元环
			LoopProtectInfo LoopProtectInfoForSearch = new LoopProtectInfo();
			LoopProtectInfoForSearch.setIsSingle(1);
			LoopProtectInfoForSearch.setSiteId(siteInst.getSite_Inst_Id());
			for (LoopProtectInfo loopProtectInfo : this.select(LoopProtectInfoForSearch)) {
				Segment seg = new Segment();
				seg.setASITEID(loopProtectInfo.getSiteId());
				seg.setAPORTID(loopProtectInfo.getWestPort());	
				segmentList=new ArrayList<Segment>();
				segmentList = segmentService.selectBySiteIdAndPort(seg);
				if (segmentList!=null && segmentList.size() != 0) {
					segment = segmentList.get(0);
				}
				loopProtectInfo.setWestSegment(segment);
				loopProtectListAll.add(loopProtectInfo);
			}
		}

		// 生产loopid
		List<LoopProtectInfo> loopProtectInfoLoop = new ArrayList<LoopProtectInfo>();
		loopProtectInfoLoop=this.mapper.queryForLoop();
		
		loopId = loopProtectInfoLoop.get(0).getLoopId() + 1;
		// 循环每一个环
		for (LoopProtectInfo loopProtectInfo : loopProtectListAll) {
			count = 0;
			if (0 != loopProtectExitList.size()) { // 判断是否存在
				for (LoopProtectInfo loopProtectInfoExit : loopProtectExitList) {
					if (loopProtectInfoExit.getId() == loopProtectInfo.getId()) {
						count = 1;
					}
				}
			}
			if (1 != count) {
				// 不存在 搜索 环
				loopProtectActionList = new ArrayList<LoopProtectInfo>();
				loopProtectInfoWest = loopProtectInfo;
				loopProtectActionList = findBeLoopList(loopProtectInfo, loopProtectInfoWest, loopProtectListAll, loopProtectActionList);
				if (loopProtectActionList.size() > 0) {
					for (LoopProtectInfo loopProtectInfoAction : loopProtectActionList) {
						loopProtectInfoAction.setIsSingle(0);
						loopProtectInfoAction.setLoopId(loopId);
						this.mapper.update(loopProtectInfoAction);
						loopProtectExitList.add(loopProtectInfoAction);
					}
				}
			}
		}
		loopProtectListAll = null;
		loopProtectExitList = null;
		loopProtectActionList = null;
		loopProtectInfoWest = null;
		segmentList = null;
//		UiUtil.closeService(segmentService);
		loopProtectInfoLoop = null;
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
	
	}
	
	/**
	 * 查询环
	 * 
	 * @param loopProtectInfo
	 *            本单站环
	 * @param loopProtectListAll
	 *            所有环
	 * @return
	 */
	public List<LoopProtectInfo> findBeLoopList(LoopProtectInfo loopProtectInfo, LoopProtectInfo loopProtectInfoWest, List<LoopProtectInfo> loopProtectListAll, List<LoopProtectInfo> loopProtectActionList) {
		loopProtectInfoWest = this.findWestLoopProtect(loopProtectInfoWest, loopProtectListAll);
		if (null != loopProtectInfoWest) {
			if (loopProtectInfoWest.getId() == loopProtectInfo.getId()) {
				loopProtectActionList.add(loopProtectInfo);
				return loopProtectActionList;
			} else {
				loopProtectActionList.add(loopProtectInfoWest);
				findBeLoopList(loopProtectInfo, loopProtectInfoWest, loopProtectListAll, loopProtectActionList);
			}
		}
		return loopProtectActionList;
	}
	
	/**
	 * 查询西向环
	 * 
	 * @param loopProtectInfo
	 *            本单站环
	 * @param loopProtectListAll
	 *            网元下所有环
	 * @return
	 */
	public LoopProtectInfo findWestLoopProtect(LoopProtectInfo loopProtectInfo, List<LoopProtectInfo> loopProtectListAll) {
		int siteIdWest;
		int portIdWest;
		LoopProtectInfo loopProtectInfoWest  = null;
		List<LoopProtectInfo> loopProtectInfoWestList = new ArrayList<LoopProtectInfo>();
		if (null != loopProtectInfo.getWestSegment()) {
			if (loopProtectInfo.getWestSegment().getASITEID() == loopProtectInfo.getSiteId() && loopProtectInfo.getWestSegment().getAPORTID() == loopProtectInfo.getWestPort()) {
				siteIdWest = loopProtectInfo.getWestSegment().getZSITEID();
				portIdWest = loopProtectInfo.getWestSegment().getZPORTID();
			} else if (loopProtectInfo.getWestSegment().getZSITEID() == loopProtectInfo.getSiteId() && loopProtectInfo.getWestSegment().getZPORTID() == loopProtectInfo.getWestPort()) {
				siteIdWest = loopProtectInfo.getWestSegment().getASITEID();
				portIdWest = loopProtectInfo.getWestSegment().getAPORTID();
			} else {
				return null;
			}
			loopProtectInfoWest = new LoopProtectInfo();
			loopProtectInfoWest.setSiteId(siteIdWest);
			loopProtectInfoWest.setEastPort(portIdWest);
			loopProtectInfoWestList = this.select(loopProtectInfoWest);
			if (loopProtectInfoWestList.size() > 0) {
				for (LoopProtectInfo loopProtectInfoAll : loopProtectListAll) {
					if (loopProtectInfoWestList.get(0).getId() == loopProtectInfoAll.getId()) {
						return loopProtectInfoAll;
					}
				}
			}
		}
		loopProtectInfoWestList = null;
		loopProtectInfoWest = null;
		return null;
	}
	
	/**
	 * 新增
	 * 
	 * @param loopProtectList
	 * @return
	 * @throws SQLException 
	 */
	public int insert(List<LoopProtectInfo> loopProtectList) throws SQLException {

		int result = 0;
		Businessid wrappingSerBusinessId = null;
		List<Integer> siteList = null;
		int loopId;
		int businessid = 0;
		List<LoopProtectInfo> loopProtectInfo1 = null;
		BusinessidService_MB businessidService = null;
		SiteService_MB siteService = null;
		BusinessidMapper businessidMapper = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
			businessidService = (BusinessidService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BUSINESSID, this.sqlSession);
			businessidMapper = sqlSession.getMapper(BusinessidMapper.class);
			
			siteList = new ArrayList<Integer>();
			SiteUtil siteUtil=new SiteUtil();
			if (loopProtectList.size() > 1) {
				addNodeId(loopProtectList);
			}
			for (LoopProtectInfo loopProtectInfo : loopProtectList) {
				siteList.add(loopProtectInfo.getSiteId());
			}
			//从界面来的数据需要查库
			if(loopProtectList.get(0).getLoopId() ==0){
			   businessid = businessidService.select_site(siteList, "ring");// 获取可用的环id
			   result = businessid;
			   if (businessid == 0) {
				   throw new Exception("businessid is full");
			   }
			}
			loopProtectInfo1 = mapper.queryForLoop();
			if (loopProtectInfo1.size() > 0) {
				loopId = loopProtectInfo1.get(0).getLoopId() + 1;
			} else {
				loopId = 1;
			}
			
			// 更新bussinessid和添加环保护数据
			for (LoopProtectInfo loopProtectInfo : loopProtectList) {
				if(loopProtectInfo.getLoopId()==0){
				    	wrappingSerBusinessId = businessidMapper.queryByIdValueSiteIdtype(businessid,loopProtectInfo.getSiteId(), "ring");
					} else {
						wrappingSerBusinessId = businessidMapper.queryByIdValueSiteIdtype(loopProtectInfo.getLoopId(), loopProtectInfo.getSiteId(), "ring");
					}
			//	wrappingSerBusinessId = businessidDao.query(businessid, loopProtectInfo.getSiteId(), "ring", connection);
				wrappingSerBusinessId.setIdStatus(1);
				businessidMapper.update(wrappingSerBusinessId);// 将环id设置为不可用
				if (1 == loopProtectInfo.getIsSingle()) {
					loopProtectInfo.setName("Ring/"+wrappingSerBusinessId.getIdValue());
				}
				if (EManufacturer.CHENXIAO.getValue() == siteService.getManufacturer(loopProtectInfo.getSiteId()) && 0 == siteUtil.SiteTypeUtil(loopProtectInfo.getSiteId())) {
					loopProtectInfo.setLoopId(loopId);
					loopProtectInfo.setLoopBusinessId(wrappingSerBusinessId.getIdValue());
					if (EManufacturer.CHENXIAO.getValue() == siteService.getManufacturer(loopProtectInfo.getSiteId())) {

						if (0 != loopProtectInfo.getNodeId()) {
							wrappingSerBusinessId = businessidMapper.queryByIdValueSiteIdtype(loopProtectInfo.getNodeId(), loopProtectInfo.getSiteId(), "ringNode");
						}
						if (0 != loopProtectInfo.getEastNodeId()) {
							wrappingSerBusinessId = businessidMapper.queryByIdValueSiteIdtype(loopProtectInfo.getEastNodeId(), loopProtectInfo.getSiteId(), "ringNode");
						}
						if (0 != loopProtectInfo.getWestNodeId()) {
							wrappingSerBusinessId = businessidMapper.queryByIdValueSiteIdtype(loopProtectInfo.getWestNodeId(), loopProtectInfo.getSiteId(), "ringNode");
						}
						wrappingSerBusinessId.setIdStatus(1);
						businessidMapper.update(wrappingSerBusinessId);// 将环id设置为不可用
					}
					
				} else {
					loopProtectInfo.setLoopId(wrappingSerBusinessId.getIdValue());
//					if (1 == loopProtectInfo.getIsSingle()) {
//						loopProtectInfo.setName(wrappingSerBusinessId.getIdValue() + "");
//					}
				}
				int id = mapper.insert(loopProtectInfo);
				super.dateDownLoad(loopProtectInfo.getSiteId(),id, EServiceType.LOOPPROTECT.getValue(), EActionType.INSERT.getValue());
				
				sqlSession.commit();
			}
		} catch (Exception e) {
			sqlSession.rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
		return result;
	}
	
	/**
	 * 添加节点ID
	 * @param loopProtectList
	 * 			环对象
	 * @throws Exception
	 */
	private void addNodeId(List<LoopProtectInfo> loopProtectList) throws Exception {
		BusinessidService_MB businessidService = null;
		SegmentService_MB segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT, this.sqlSession);
		List<Segment> segmentWestList = new ArrayList<Segment>();
		List<Segment> segmentEastList = new ArrayList<Segment>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		SiteService_MB siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
		for (LoopProtectInfo loopProtectInfo : loopProtectList) {
			if (EManufacturer.CHENXIAO.getValue() == siteService.getManufacturer(loopProtectInfo.getSiteId())) {
				List<Businessid> ring = new ArrayList<Businessid>();
				businessidService = (BusinessidService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BUSINESSID, this.sqlSession);
				List<Integer> ExitNodeId = new ArrayList<Integer>();
				if (loopProtectList.size() > 0) {
					for (LoopProtectInfo loopProtectInfo1 : loopProtectList) {
						ExitNodeId.add(loopProtectInfo1.getNodeId());
					}
				}
				ring = businessidService.selectAllByTypeForCondition(loopProtectInfo.getSiteId(), "ringnode", ExitNodeId);
				loopProtectInfo.setNodeId(ring.get(0).getIdValue());
			}
			map.put(loopProtectInfo.getSiteId(), loopProtectInfo.getNodeId());
		}
		for (LoopProtectInfo loopProtectInfo : loopProtectList) {
			segmentEastList = segmentService.selectBySegmentPortId(loopProtectInfo.getEastPort());
			int siteIdEast = 0;
			if (segmentEastList.get(0).getASITEID() == loopProtectInfo.getSiteId()) {
				siteIdEast = segmentEastList.get(0).getZSITEID();
			} else {
				siteIdEast = segmentEastList.get(0).getASITEID();
			}
			loopProtectInfo.setEastNodeId((Integer) map.get(siteIdEast));

			int siteIdWest = 0;
			segmentWestList = segmentService.selectBySegmentPortId(loopProtectInfo.getWestPort());
			if (segmentWestList.get(0).getASITEID() == loopProtectInfo.getSiteId()) {
				siteIdWest = segmentWestList.get(0).getZSITEID();
			} else {
				siteIdWest = segmentWestList.get(0).getASITEID();
			}
			loopProtectInfo.setWestNodeId((Integer) map.get(siteIdWest));
		}
	}
	
	/**
	 * 根据环网id批量删除
	 * 
	 * @param loopProtectInfoList
	 * @throws SQLException
	 */
	public void deleteByLoopId(List<LoopProtectInfo> loopProtectInfoList) throws SQLException {
		Businessid wrappingSerBusinessId = null;
		SiteService_MB siteService = null;
		BusinessidMapper businessidMapper = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
			businessidMapper = sqlSession.getMapper(BusinessidMapper.class);
			
			// 更新bussinessid 和删除环保护数据
			if (EManufacturer.CHENXIAO.getValue() == siteService.getManufacturer(loopProtectInfoList.get(0).getSiteId())) {
				LoopProtectInfo loopProtectSelect = new LoopProtectInfo();
				loopProtectSelect.setName(loopProtectInfoList.get(0).getName());
				loopProtectInfoList = this.select(loopProtectSelect);
			}
			for (LoopProtectInfo loopProtectInfo : loopProtectInfoList) {
				if (0 != loopProtectInfo.getLoopId()) {
					wrappingSerBusinessId = businessidMapper.queryByIdValueSiteIdtype(loopProtectInfo.getLoopId(), loopProtectInfo.getSiteId(), "ring");
					wrappingSerBusinessId.setIdStatus(0);
					businessidMapper.update(wrappingSerBusinessId);// 将环id设置为可用
				}
				if (siteService.getManufacturer(loopProtectInfo.getSiteId()) == EManufacturer.CHENXIAO.getValue()) {
					if (0 != loopProtectInfo.getNodeId()) {
						wrappingSerBusinessId = businessidMapper.queryByIdValueSiteIdtype(loopProtectInfo.getNodeId(), loopProtectInfo.getSiteId(), "ringNode");
					}
					if (0 != loopProtectInfo.getEastNodeId()) {
						wrappingSerBusinessId = businessidMapper.queryByIdValueSiteIdtype(loopProtectInfo.getEastNodeId(), loopProtectInfo.getSiteId(), "ringNode");
					}
					if (0 != loopProtectInfo.getWestNodeId()) {
						wrappingSerBusinessId = businessidMapper.queryByIdValueSiteIdtype(loopProtectInfo.getWestNodeId(), loopProtectInfo.getSiteId(), "ringNode");
					}
					wrappingSerBusinessId.setIdStatus(0);
					businessidMapper.update(wrappingSerBusinessId);// 将环id设置为可用
				}
				mapper.deleteByLoopId(loopProtectInfo.getLoopId());
				//离线网元数据下载
				super.dateDownLoad(loopProtectInfo.getSiteId(),loopProtectInfo.getId(), EServiceType.LOOPPROTECT.getValue(), EActionType.INSERT.getValue(),loopProtectInfo.getName()+"",null,0,0,null);
			}
			sqlSession.commit();
		} catch (Exception e) {
			sqlSession.rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
	}
	
	/**
	 * 根据某网元id，初始化相关环保护
	 * 
	 * @param siteId
	 * @throws SQLException
	 */
	public void initializtionSite(int siteId) throws SQLException {
		LoopProtectInfo loopProtectInfo = null;
		List<LoopProtectInfo> loopProtectInfos = null;
		try {
			loopProtectInfo = new LoopProtectInfo();
			loopProtectInfo.setSiteId(siteId);
			loopProtectInfos = this.select(loopProtectInfo);
			if (loopProtectInfos != null && loopProtectInfos.size() > 0) {
				for (LoopProtectInfo info : loopProtectInfos) {
					this.deleteByLoopId(info.getLoopId());
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			loopProtectInfos = null;
			loopProtectInfo = null;
		}

	}
	
	/**
	 * 根据环网id删除,初始化某网元时用到
	 * 
	 * @param loopProtectInfoList
	 * @throws SQLException
	 */
	public void deleteByLoopId(int loopId) throws SQLException {
		try {
			this.mapper.deleteByLoopId(loopId);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} 
	}

	
	/**
	 * 更新状态
	 * @param siteId
	 * 			网元ID
	 * @param status
	 * 			状态
	 * @throws SQLException
	 */
	public void updateActiveStatus(int siteId, int status) throws SQLException {
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.updateStatus(siteId, status);
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
	}
}
