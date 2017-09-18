package com.nms.model.ptn.path.protect;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.Businessid;
import com.nms.db.bean.ptn.path.protect.LoopProtectInfo;
import com.nms.db.dao.ptn.BusinessidMapper;
import com.nms.db.dao.ptn.path.protect.LoopProtectInfoMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EServiceType;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
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
	
}
