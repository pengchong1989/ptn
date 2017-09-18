package com.nms.model.ptn.path.eth;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.dao.ptn.path.eth.EtreeInfoMapper;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class EtreeInfoService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private EtreeInfoMapper mapper;

	public EtreeInfoMapper getEtreeInfoMapper() {
		return mapper;
	}

	public void setEtreeInfoMapper(EtreeInfoMapper EtreeInfoMapper) {
		this.mapper = EtreeInfoMapper;
	}

	/**
	 * @throws Exception
	 */
	public Map<Integer, List<EtreeInfo>> selectBySiteAndisSingle(int siteId,int isSingle) throws Exception {
		return this.convertListToMap(this.mapper.selectBySiteAndisSingle(siteId, isSingle));
	}
	
	/**
	 * 把所有etree按组转为map 
	 * @param etreeInfoList
	 * @return key为组id  value为此组下的etree对象
	 * @throws Exception
	 */
	private Map<Integer, List<EtreeInfo>> convertListToMap(List<EtreeInfo> etreeInfoList) {
		Map<Integer, List<EtreeInfo>> etreeInfoMap = new HashMap<Integer, List<EtreeInfo>>();
		try {
			if(null!=etreeInfoList && etreeInfoList.size()>0){
				for (EtreeInfo etreeInfo : etreeInfoList) {
					int serviceId = etreeInfo.getServiceId();
					if (etreeInfoMap.get(serviceId) == null) {
						List<EtreeInfo> etreeInfoList_map = new ArrayList<EtreeInfo>();
						etreeInfoMap.put(serviceId, etreeInfoList_map);
					}
					etreeInfoMap.get(serviceId).add(etreeInfo);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return etreeInfoMap;
	}
	
	/**
	 * 查询所有业务
	 * 
	 * @param serviceId
	 * @return
	 */
	public List<EtreeInfo> selectAll(int serviceType,String name) {
		List<EtreeInfo> etreeInfoList = null;
		try {
		   etreeInfoList = new ArrayList<EtreeInfo>();
		   etreeInfoList = this.mapper.queryAllEtree(serviceType,name);		
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return etreeInfoList;
	}
	
	/**
	 * 查询所有业务
	 * 
	 * @param serviceId,name,brabchSite
	 * @return
	 */
	public List<EtreeInfo> selectAcIds(int serviceType,String name,int branchSite) {
		List<EtreeInfo> etreeInfoList = null;    
		try {
		   etreeInfoList = new ArrayList<EtreeInfo>();
		   etreeInfoList = this.mapper.queryAllEtrees(serviceType,name,branchSite);		
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return etreeInfoList;
	}

	public Map<Integer, List<EtreeInfo>> filterSelect(EtreeInfo etreeInfo) {
		Map<Integer, List<EtreeInfo>> etreeInfoMap = new HashMap<Integer, List<EtreeInfo>>();
		List<EtreeInfo> etreeServiceList = null;
		AcPortInfoService_MB acService = null;
		List<Integer> acIds = null;
		try {
			acService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo,this.sqlSession);
			etreeServiceList = this.mapper.filterSelect(etreeInfo);
			
			if(etreeInfo.getAportId() >0)
			{
				acIds = acService.acByPort(etreeInfo.getAportId(), etreeInfo.getaSiteId());
			}
			
			for (EtreeInfo etreeInfo_result : etreeServiceList) {
				etreeInfo_result.setCreateTime(DateUtil.strDate(etreeInfo_result.getCreateTime(), DateUtil.FULLTIME));
				//存在通过端口查询
				if(acIds != null)
				{
					if(!acIds.isEmpty())
					{
						if(acByFilter(acIds,etreeInfo_result.getAmostAcId()) || acByFilter(acIds,etreeInfo_result.getZmostAcId()))
						{
							etreeByFilter(etreeInfo_result,etreeInfoMap,etreeServiceList);
						}	
					}
				}else
				{
					etreeByFilter(etreeInfo_result,etreeInfoMap,etreeServiceList);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return etreeInfoMap;
	}

	private void etreeByFilter(EtreeInfo etreeInfo_result,Map<Integer, List<EtreeInfo>> etreeInfoMap,List<EtreeInfo> etreeServiceList)
	{
		int serviceId = etreeInfo_result.getServiceId();
		if (etreeInfoMap.get(serviceId) == null) {
			List<EtreeInfo> etreeInfoList = new ArrayList<EtreeInfo>();
			for (EtreeInfo etree : etreeServiceList) {
				if (etree.getServiceId() == serviceId) {
					etreeInfoList.add(etree);
				}
			}
			etreeInfoMap.put(serviceId, etreeInfoList);
		}
	}
	
	/**
	 * 查询a/Z端是否满足查询条件
	 * @param acIds
	 * @param etreeInfo_result
	 * @return
	 */
	private boolean acByFilter(List<Integer> acIds,String mostAcId)
	{
	 Set<Integer> acList = null;
	 UiUtil uiutil  = null;
		try {
			uiutil = new UiUtil();
			acList = uiutil.getAcIdSets(mostAcId);
			for(Integer acId : acList)
			{
				if(acIds.contains(acId))
				{
					return true;
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return false;
	}

	public List<EtreeInfo> select(EtreeInfo etreeInfo) {
		List<EtreeInfo> etreeInfoList = null;
		try {
			etreeInfoList = this.mapper.queryByServiceId(etreeInfo.getServiceId());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return etreeInfoList;
	}

	public List<EtreeInfo> selectByServiceId(int serviceId) {
		List<EtreeInfo> etreeInfoList = null;
		try {
			etreeInfoList = this.mapper.queryByServiceId(serviceId);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return etreeInfoList;
	}

	public void search(List<SiteInst> siteInstList) throws Exception {
		List<Integer> siteIdList = null;
		Map<Integer, List<EtreeInfo>> map = null;
		Map<Integer, List<EtreeInfo>> map_create = null; // 要创建的map
		int createKey = 1; // 创建的map中key标识
		SiteService_MB siteService=null;
		PwInfoService_MB pwService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
			pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo, this.sqlSession);
			siteIdList = siteService.getSiteIdList(siteInstList);
			map_create = new HashMap<Integer, List<EtreeInfo>>();
			for (SiteInst siteInst : siteInstList) {
				// 查出此网元所有根节点
				map = this.convertMap(this.mapper.queryByRoot(siteInst.getSite_Inst_Id()));

				// 遍历每一组根节点。寻找叶子节点
				for (int serviceId : map.keySet()) {
					this.putCrateMap(map_create, createKey, map.get(serviceId), siteIdList);
					createKey++;
				}
			}
			// 如果map中有值，就循环创建每一组etree。
			if (map_create.keySet().size() > 0) {
				for (int key : map_create.keySet()) {
					this.createEtree(map_create.get(key), pwService);
				}
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 把list转换成map serivceId为key
	 * @param etreeList 要转换的集合
	 * @throws Exception
	 */
	private Map<Integer, List<EtreeInfo>> convertMap(List<EtreeInfo> etreeList) throws Exception {
		Map<Integer, List<EtreeInfo>> map = null;
		List<EtreeInfo> etreeInfoList = null;
		try {
			map = new HashMap<Integer, List<EtreeInfo>>();

			for (EtreeInfo etreeInfo : etreeList) {
				if (null == map.get(etreeInfo.getServiceId())) {
					etreeInfoList = new ArrayList<EtreeInfo>();
					etreeInfoList.add(etreeInfo);

					map.put(etreeInfo.getServiceId(), etreeInfoList);
				} else {
					map.get(etreeInfo.getServiceId()).add(etreeInfo);
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			etreeInfoList = null;
		}
		return map;
	}

	
	/**
	 * 根据根节点，查询出叶子节点，验证叶子节点是否在siteids集合中，如果在，把此组etree放到map中
	 * 
	 * @param map_create
	 *            创建的map集合
	 * @param key
	 *            创建的map中key
	 * @param rootEtrees
	 *            根节点
	 * @param siteIds
	 *            网元集合
	 * @throws Exception
	 */
	private void putCrateMap(Map<Integer, List<EtreeInfo>> map_create, int key, List<EtreeInfo> rootEtrees, List<Integer> siteIds) throws Exception {
		List<EtreeInfo> etreeInfoList = new ArrayList<EtreeInfo>();
		boolean flag = true;
		try {
			List<EtreeInfo> etreeList = this.selectEtreeByPwId(this.getPwIds(rootEtrees));
			if(etreeList != null && etreeList.size() > 0){
				for (EtreeInfo etreeInfo : etreeList) {
					if(etreeInfo.getBranchSite() > 0){
						etreeInfoList.add(etreeInfo);
					}
				}
			}
			// 遍历查询出的etree集合
			//根节点必须与叶子节点数量完全对应
			if(etreeInfoList.size() == rootEtrees.size()){
				for (EtreeInfo etreeInfo : etreeInfoList) {
					// 如果是叶子节点，并且网元集合中不存在此网元，说明此次网元选择不足，不能合并
					if (etreeInfo.getBranchSite() > 0 && !siteIds.contains(etreeInfo.getBranchSite())) {
						flag = false;
						break;
					}
				}
			}else{
				flag = false;
			}
			// 如果通过验证，可以合并，把此组etree数据放到map中，统一做合并
			if (flag) {
				map_create.put(key, etreeList);
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<EtreeInfo> selectEtreeByPwId(List<Integer> pwIds) throws Exception {
		List<EtreeInfo> list = null;
		try {
			list = this.mapper.queryByPwIdCondition(pwIds);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return list;
	}
	
	/**
	 * 获取etree中的所有pwid
	 * @param etreeInfoList etree集合
	 * @throws Exception
	 */
	private List<Integer> getPwIds(List<EtreeInfo> etreeInfoList) throws Exception {
		List<Integer> pwIds = null;
		try {
			pwIds = new ArrayList<Integer>();
			for (EtreeInfo etreeInfo : etreeInfoList) {
				pwIds.add(etreeInfo.getPwId());
			}
		} catch (Exception e) {
			throw e;
		}
		return pwIds;
	}

	/**
	 * 合并并且创建etree数据
	 * @param etreeInfoList
	 * @throws Exception
	 */
	private void createEtree(List<EtreeInfo> etreeInfoList, PwInfoService_MB pwService) throws Exception {
		EtreeInfo branchEtree = null;
		List<EtreeInfo> etreeInfoList_create = null;
		int serviceId = 1;
		String etreeName = null;
		try {
			etreeInfoList_create = new ArrayList<EtreeInfo>();
			for (EtreeInfo etreeInfo : etreeInfoList) {
				if (etreeInfo.getRootSite() > 0) {
					// 取叶子节点
					branchEtree = this.getBranch(etreeInfo, etreeInfoList);
					// 根据根节点和叶节点组成一个etree对象
					if(branchEtree != null){
						etreeInfoList_create.add(this.combination(etreeInfo, branchEtree));
					}
				}
			}
			if (etreeInfoList_create.size() > 0) {
				// 取serviceid
				Integer maxId = this.mapper.selectMaxServiceId();
				if(maxId != null){
					serviceId = maxId + 1;
				}
				// 设置etree名称
				etreeName = "etree_" + new Date().getTime();
				for (EtreeInfo etreeInfo : etreeInfoList_create) {
					etreeInfo.setServiceId(serviceId);
					etreeInfo.setName(etreeName);
					// 插入
					this.mapper.insert(etreeInfo);
//					etreeInfo.setId(etreeId);
					// 修改pw关联
					pwService.setUser(etreeInfo.getPwId(), etreeInfo);
				}
				// 删除原有的数据
				for (EtreeInfo etreeInfo : etreeInfoList) {
					this.mapper.deleteByID(etreeInfo.getId());
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 根据根节点 找出对应的叶子节点
	 * @param rootEtree  根节点
	 * @param etreeInfoList
	 * @throws Exception
	 */
	private EtreeInfo getBranch(EtreeInfo rootEtree, List<EtreeInfo> etreeInfoList) throws Exception {
		EtreeInfo branchEtree = null;
		try {
			for (EtreeInfo etreeInfo : etreeInfoList) {
				if (etreeInfo.getPwId() == rootEtree.getPwId() && etreeInfo.getId() != rootEtree.getId()) {
					branchEtree = etreeInfo;
					return branchEtree;
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return branchEtree;
	}

	/**
	 * 组合根和叶为一个etree对象。
	 * @param rootEtree 根对象
	 * @param branchEtree 叶对象
	 * @throws Exception
	 */
	private EtreeInfo combination(EtreeInfo rootEtree, EtreeInfo branchEtree) throws Exception {
		EtreeInfo etreeInfo = new EtreeInfo();
		try {
			etreeInfo.setServiceType(rootEtree.getServiceType());
			etreeInfo.setActiveStatus(rootEtree.getActiveStatus());
			etreeInfo.setPwId(rootEtree.getPwId());
			etreeInfo.setaXcId(rootEtree.getaXcId());
			etreeInfo.setzXcId(branchEtree.getzXcId());
			etreeInfo.setRootSite(rootEtree.getRootSite());
			etreeInfo.setaSiteId(rootEtree.getRootSite());
			etreeInfo.setzSiteId(branchEtree.getBranchSite());
			etreeInfo.setBranchSite(branchEtree.getBranchSite());
			etreeInfo.setaAcId(rootEtree.getaAcId());
			etreeInfo.setAmostAcId(rootEtree.getAmostAcId());
			etreeInfo.setZmostAcId(branchEtree.getZmostAcId());
			etreeInfo.setzAcId(branchEtree.getzAcId());
			etreeInfo.setCreateUser(branchEtree.getCreateUser());
			etreeInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
			etreeInfo.setJobStatus(rootEtree.getJobStatus());
		} catch (Exception e) {
			throw e;
		}
		return etreeInfo;
	}

	public Map<String, List<EtreeInfo>> selectNodeBySite(int siteId) {
		Map<String, List<EtreeInfo>> etreeInfoMap = new HashMap<String, List<EtreeInfo>>();
		List<EtreeInfo> etreeServiceList = null;

		try {
			etreeServiceList = this.mapper.queryAllBySite(siteId);
			for (EtreeInfo etreeInfo : etreeServiceList) {
				int serviceId = etreeInfo.getServiceId();
				if (etreeInfoMap.get(serviceId + "/" + etreeInfo.getServiceType()) == null) {
					List<EtreeInfo> etreeInfoList = new ArrayList<EtreeInfo>();
					for (EtreeInfo etree : etreeServiceList) {
						if (etree.getServiceId() == serviceId) {
							etreeInfoList.add(etree);
						}
					}
					etreeInfoMap.put(serviceId + "/" + etreeInfoList.get(0).getServiceType(), etreeInfoList);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return etreeInfoMap;
	}
	
	/**
	 * 根据网元查询此网元下所有单网元eline业务
	 * @param siteId 网元主键
	 * @return 
	 * @throws Exception
	 */
	public Map<Integer, List<EtreeInfo>> selectBySite_node(int siteId) throws Exception{
		return this.convertListToMap(this.mapper.selectBySiteAndisSingle(siteId, 1));
	}
	
	/**
	 * 根据网元查询此网元下所有网络eline业务
	 * @param siteId 网元主键
	 * @return 
	 * @throws Exception
	 */
	public Map<Integer, List<EtreeInfo>> selectBySite_network(int siteId) throws Exception{
		return this.convertListToMap(this.mapper.selectBySiteAndisSingle(siteId, 0));
	}

	/**
	 * 验证名字是否重复
	 * @author kk
	 * @param afterName
	 *            修改之后的名字
	 * @param beforeName
	 *            修改之前的名字
	 * @return
	 * @throws Exception
	 * @Exception 异常对象
	 */
	public boolean nameRepetition(String afterName, String beforeName) throws Exception {
		int result = this.mapper.query_name(afterName, beforeName);
		if (0 == result) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * 单网元名称验证
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
	
	/*
	 * 查询所有的etree业务(每一条可能包含多条业务)
	 */
	public Map<Integer, List<EtreeInfo>> select() throws Exception {
		Map<Integer, List<EtreeInfo>> etreeInfoMap = new HashMap<Integer, List<EtreeInfo>>();
		List<EtreeInfo> etreeServiceList = null;

		try {
			etreeServiceList = new ArrayList<EtreeInfo>();
			etreeServiceList = this.mapper.queryAll();
			if(etreeServiceList!=null && etreeServiceList.size()>0){
				for (EtreeInfo etreeInfo : etreeServiceList) {
					int serviceId = etreeInfo.getServiceId();
					if (etreeInfoMap.get(serviceId) == null) {
						List<EtreeInfo> etreeInfoList = new ArrayList<EtreeInfo>();
						for (EtreeInfo etree : etreeServiceList) {
							if (etree.getServiceId() == serviceId) {
								etreeInfoList.add(etree);
							}
						}
						etreeInfoMap.put(serviceId, etreeInfoList);
					}
			}

			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return etreeInfoMap;
	}
	
	/**
	 * 通过acId,siteId查询line
	 * 
	 * @param acId
	 * @return
	 */
	public List<EtreeInfo> selectEtreeOrElanByAcIdAndSiteId(int acId, int siteId) {
		List<EtreeInfo> etreeInfos = null;
		List<EtreeInfo> etreeInsts = null;
		UiUtil uiUtil = null;
		try {
			etreeInfos = this.mapper.queryByAcIdAndSiteIdCondition(acId, siteId);
			if(null != etreeInfos && !etreeInfos.isEmpty())
			{
				uiUtil = new UiUtil();
				etreeInsts = new ArrayList<EtreeInfo>();
				for(EtreeInfo etreeInfo : etreeInfos)
				{
				 if((uiUtil.getAcIdSets(etreeInfo.getAmostAcId()).contains(acId) && etreeInfo.getaSiteId() == siteId) 
					||(uiUtil.getAcIdSets(etreeInfo.getZmostAcId()).contains(acId)&& etreeInfo.getzSiteId() == siteId))
				 {
					 etreeInsts.add(etreeInfo);
				 }
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			 etreeInfos = null;
			 uiUtil = null;
		}
		return etreeInsts;
	}
	
}
