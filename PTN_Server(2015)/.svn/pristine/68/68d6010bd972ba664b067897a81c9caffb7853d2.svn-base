package com.nms.model.ptn.oam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamLinkInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.bean.ptn.oam.OamMipInfo;
import com.nms.db.dao.ptn.oam.OamLinkInfoMapper;
import com.nms.db.dao.ptn.oam.OamMepInfoMapper;
import com.nms.db.dao.ptn.oam.OamMipInfoMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ExceptionManage;

public class OamInfoService_MB extends ObjectService_Mybatis {
	private OamMepInfoMapper oamMepInfoMapper;
	private OamMipInfoMapper oamMipInfoMapper;
	private OamLinkInfoMapper linkInfoMapper;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	/*
	 * 保存或者更新
	 */
	public int saveOrUpdate(OamInfo oam) throws Exception {
		if (null == oam) {
			throw new Exception("oam is null");
		}
		int result = 0;
		int type = 0;
		try {
			//段
			if (oam.getOamType() == OamTypeEnum.AMEP || oam.getOamType() == OamTypeEnum.ZMEP || oam.getOamType() == OamTypeEnum.MEP) {
				if("SECTION".equals(oam.getOamMep().getObjType())){
					type = EServiceType.ETH.getValue();
				}else{
					type = EServiceType.from(oam.getOamMep().getObjType());
				}
				if (0==oam.getOamMep().getId()) {
					oamMepInfoMapper.insert(oam.getOamMep());
					result = oam.getOamMep().getId();
					//离线网元数据下载
 					super.dateDownLoad(oam.getOamMep().getSiteId(),result,type,EActionType.INSERT.getValue(), "",null,oam.getOamMep().getObjId(),0,OamTypeEnum.MEP.getValue()+"");
				} else {
					oamMepInfoMapper.update(oam.getOamMep());
					//离线网元数据下载
					super.dateDownLoad(oam.getOamMep().getSiteId(),oam.getOamMep().getId(),type, EActionType.UPDATE.getValue(), "",null,oam.getOamMep().getObjId(),0,OamTypeEnum.MEP.getValue()+"");
				}
			} else if (oam.getOamType() == OamTypeEnum.MIP) {
				//tunnel界面修改时，mipID界面没有收集，所以需要做修改
				OamMipInfo oamMipInfo = oamMipInfoMapper.queryMipByCondition(oam.getOamMip());
				if (oamMipInfo != null && oamMipInfo.getId()>0) {
					oamMipInfoMapper.updateByPrimaryKey(oam.getOamMip());
					//离线网元数据下载
 					super.dateDownLoad(oam.getOamMip().getSiteId(),oam.getOamMip().getId(),EServiceType.TUNNEL.getValue(), EActionType.UPDATE.getValue(), "",null,oam.getOamMip().getObjId(),0,OamTypeEnum.MIP.getValue()+"");
				} else {
					oamMipInfoMapper.insert(oam.getOamMip());
					result = oam.getOamMip().getId();
					//离线网元数据下载
 					super.dateDownLoad(oam.getOamMip().getSiteId(),result,EServiceType.TUNNEL.getValue(), EActionType.INSERT.getValue(), "",null,oam.getOamMip().getObjId(),0,OamTypeEnum.MIP.getValue()+"");

				}
			} else {
				OamLinkInfo oamLinkInfo = linkInfoMapper.queryOamLinkInfoByCondition(oam.getOamLinkInfo());
				if(oamLinkInfo == null){
					linkInfoMapper.insert(oam.getOamLinkInfo());
					result = oam.getOamLinkInfo().getId();
					//离线网元数据下载
					super.dateDownLoad(oam.getOamLinkInfo().getSiteId(),result, EServiceType.OAMETHLINK.getValue(), EActionType.INSERT.getValue(), "",null,oam.getOamLinkInfo().getObjId(),0,OamTypeEnum.LINKOAM.getValue()+"");
				}else{
					linkInfoMapper.updateByPrimaryKey(oam.getOamLinkInfo());
					//离线网元数据下载
					super.dateDownLoad(oam.getOamLinkInfo().getSiteId(),result, EServiceType.OAMETHLINK.getValue(), EActionType.UPDATE.getValue(), "",null,oam.getOamLinkInfo().getObjId(),0,OamTypeEnum.LINKOAM.getValue()+"");
				}
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			oam = null;
		}
		return result;

	}

	/*
	 * 删除oam
	 */
	public int delete(OamInfo oam) throws Exception {
		if (null == oam) {
			throw new Exception("oam is null");
		}
		int result = 0;
		int type = 0;
		try {
			if (oam.getOamMep() != null) {
				oamMepInfoMapper.delete(oam.getOamMep());
				//离线网元数据下载
				if("SECTION".equals(oam.getOamMep().getObjType())){
					type = EServiceType.ETH.getValue();
				}else{
					type = EServiceType.from(oam.getOamMep().getObjType());
				}
				super.dateDownLoad(oam.getOamMep().getSiteId(),oam.getOamMep().getId(), type, EActionType.DELETE.getValue(), oam.getOamMep().getServiceId()+"",null,oam.getOamMep().getObjId(),0,OamTypeEnum.AMEP.getValue()+"");
			}
			if (oam.getOamMip() != null) {
				oamMipInfoMapper.delete(oam.getOamMip());
				//离线网元数据下载
				super.dateDownLoad(oam.getOamMip().getSiteId(),oam.getOamMip().getId(), EServiceType.TUNNEL.getValue(), EActionType.DELETE.getValue(), "",null,oam.getOamMip().getObjId(),0,OamTypeEnum.MIP.getValue()+"");
			}
			if (oam.getOamLinkInfo() != null) {
				linkInfoMapper.delete(oam.getOamLinkInfo());
				//离线网元数据下载
				super.dateDownLoad(oam.getOamLinkInfo().getSiteId(),oam.getOamLinkInfo().getId(), EServiceType.OAMETHLINK.getValue(), EActionType.DELETE.getValue(), "",null,oam.getOamLinkInfo().getObjId(),0,OamTypeEnum.LINKOAM.getValue()+"");
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			throw e;
		} finally {
			oam = null;
		}
		return result;
	}
	
	/*
	 * 根据对象类型和对象id查找相应oamInfo
	 */
	public OamInfo queryByCondition(OamInfo oam, OamTypeEnum oamType) throws Exception {
		if (null == oam) {
			throw new Exception("oam is null");
		}
		OamInfo oamInfo = null;
		try {
			oamInfo = new OamInfo();
			if (oamType == OamTypeEnum.AMEP || oamType == OamTypeEnum.ZMEP) {
				oamInfo.setOamMep(oamMepInfoMapper.queryMepByCondition(oam.getOamMep()));
			} else if (oamType == OamTypeEnum.MIP) {
				oamInfo.setOamMip(oamMipInfoMapper.queryMipByCondition(oam.getOamMip()));
			} else {
				OamLinkInfo linkOam = linkInfoMapper.queryOamLinkInfoByCondition(oam.getOamLinkInfo());
				if(linkOam == null){
					linkOam = new OamLinkInfo();
				}
				oamInfo.setOamLinkInfo(linkOam);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			oam = null;
		}
		return oamInfo;
	}
	
	/**
	 * 根据网元id，类型查找所有oammep
	 * @param oamInfo
	 * @param oamType
	 * @return
	 * @throws Exception
	 */
	public List<OamInfo> queryBySiteId(OamInfo oamInfo, OamTypeEnum oamType) throws Exception {
		if (null == oamInfo) {
			throw new Exception("oam is null");
		}
		List<OamMepInfo> oamMepList = null;
		List<OamInfo> oamInfoList = null;
		try {
			oamInfoList = new ArrayList<OamInfo>();
			if (oamType == OamTypeEnum.AMEP || oamType == OamTypeEnum.ZMEP) {
				oamMepList = oamMepInfoMapper.queryMepByTypeAndSiteId(oamInfo.getOamMep());
			}
			for (OamMepInfo oamMepInfo : oamMepList) {
				OamInfo info = new OamInfo();
				info.setOamMep(oamMepInfo);
				oamInfoList.add(info);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			oamMepList = null;
		}
		return oamInfoList;
	}
	
	/**
	 * 通过网元id查询以太网链路oam
	 * @param oam
	 * @return
	 * @throws Exception
	 */
	public List<OamInfo> queryLinkOAMBySiteId(OamInfo oam) throws Exception {
		if (oam == null) {
			throw new Exception("oam  is null");
		}
		List<OamInfo> oamList = null;
		List<OamLinkInfo> oamLinkList = null;
		OamLinkInfo linkInfo = null;
		OamInfo oamInfo = null;
		try {
			oamList = new ArrayList<OamInfo>();
			oamLinkList = new ArrayList<OamLinkInfo>();
			linkInfo = new OamLinkInfo();
			linkInfo.setObjType(oam.getOamLinkInfo().getObjType());
			linkInfo.setSiteId(oam.getOamLinkInfo().getSiteId());
			oamLinkList = linkInfoMapper.queryOamLinkByType(linkInfo);
			for (OamLinkInfo info : oamLinkList) {
				oamInfo = new OamInfo();
				oamInfo.setOamLinkInfo(info);

				oamInfo.setId(info.getId());
				oamList.add(oamInfo);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			linkInfo = null;
			oamInfo = null;
		}

		return oamList;
	}
	
	/*
	 * 根据serviceId查询
	 */
	public List<OamInfo> queryByServiceId(OamInfo oam) throws Exception {
		if (null == oam) {
			throw new Exception("oam is null");
		}
		List<OamInfo> oamList = null;
		List<OamMepInfo> oamMepList = null;
		OamMepInfo oamMep = null;
		OamMipInfo oamMip = null;
		OamInfo oamI = null;
		try {
			oamList = new ArrayList<OamInfo>();
			oamMepList = new ArrayList<OamMepInfo>();
			oamMep = new OamMepInfo();
			oamMep.setServiceId(oam.getOamMep().getServiceId());
			oamMep.setObjType(oam.getOamMep().getObjType());
			oamMep.setSiteId(oam.getOamMep().getSiteId());
			oamMepList = oamMepInfoMapper.queryMepByServiceId(oamMep);
			for (OamMepInfo oamM : oamMepList) {
				oamI = new OamInfo();
				oamI.setOamMep(oamM);
				oamI.setId(oamM.getId());
				oamI.setOamType(OamTypeEnum.MEP);
				oamList.add(oamI);
			}
			oamMip = new OamMipInfo();
			oamMip.setServiceId(oam.getOamMep().getServiceId());
//			oamMip.setSiteId(oam.getOamMep().getSiteId());
			oamMip.setObjType(oam.getOamMep().getObjType());
			List<OamMipInfo> mipInfos = oamMipInfoMapper.queryMipByConditionForList(oamMip);
			for (OamMipInfo oamMipInfo : mipInfos) {
				OamInfo oamInfo = new OamInfo();
				oamInfo.setOamMip(oamMipInfo);
				oamList.add(oamInfo);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			oam = null;
		}

		return oamList;
	}
	
	
	/**
	 *  根据网元ID、serviceId、objectType 修改objId  tunnel保护时用
	 * @author kk
	 * @param   
	 * @return 
	 * @throws Exception 
	 * @Exception 异常对象
	 */
	public void update_mep_objid(Integer objid,Integer siteId,Integer serviceId,String objtype) throws Exception{
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("objid", objid);
		map.put("siteId", siteId);
		map.put("serviceId", serviceId);
		map.put("objtype", objtype);
		this.oamMepInfoMapper.update_mep_objid(map);
		this.sqlSession.commit();
	}
	
	/**
	 * 删除oam
	 */
	public int deleteById(OamInfo oam) throws Exception {
		if (null == oam) {
			throw new Exception("oam is null");
		}
		int result = 0;
		try {
			if (oam.getOamMep() != null) {
				result = oamMepInfoMapper.deleteByPrimaryKey(oam.getOamMep().getId());
			}
			if (oam.getOamMip() != null) {
				result = oamMipInfoMapper.deleteByPrimaryKey(oam.getOamMip().getId());
			}
			if (oam.getOamLinkInfo() != null) {
				result = linkInfoMapper.deleteByPrimaryKey(oam.getOamLinkInfo().getId());
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			throw e;
		} finally {
			oam = null;
		}
		return result;
	}
	
	/**
	 * 条件查询mep
	 * @param mep
	 * @return
	 * @throws Exception
	 */
	public List<OamMepInfo> selectByOamMepInfo(OamMepInfo mep) throws Exception{
		if (null == mep) {
			throw new Exception("oamMep is null");
		}
		List<OamMepInfo> oamMepInfos = oamMepInfoMapper.selectByOamMepInfo(mep);
		return oamMepInfos;
	}
	
	/*
	 * 批量删除oam
	 */
	public int delete(List<OamInfo> oamInfos) throws Exception {
		if (null == oamInfos) {
			throw new Exception("oam is null");
		}
		int result = 0;
		try {
			for(OamInfo oam : oamInfos){
				if (oam.getOamMep() != null) {
					result = oamMepInfoMapper.deleteByPrimaryKey(oam.getOamMep().getId());
				}
				if (oam.getOamMip() != null) {
					result = oamMipInfoMapper.deleteByPrimaryKey(oam.getOamMip().getId());
				}
				if (oam.getOamLinkInfo() != null) {
					result = linkInfoMapper.deleteByPrimaryKey(oam.getOamLinkInfo().getId());
				}
			}
			sqlSession.commit();
		} catch (Exception e) {
			throw e;
		} finally {
			oamInfos = null;
		}
		return result;
	}
	
	public void updateEquipExitStatusForEthLinkOam(int siteId, int status) {
		try {
			this.linkInfoMapper.updateEquipExitStatusForEthLinkOam(siteId,status);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 条件查询
	 * @param oamMep
	 */
	public OamMepInfo queryMep(OamMepInfo oamMep) {
		OamMepInfo oam = null;
		try {
			oam = this.oamMepInfoMapper.queryMepByCondition(oamMep);
			if(oam==null){
				oam=new OamMepInfo();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} 
		return oam;
	}
	
	/**
	 * 条件查询
	 * @param oamMip
	 * @return
	 */
	public OamMipInfo queryMep(OamMipInfo oamMip) {
		OamMipInfo oam = null;
		try {
			oam = this.oamMipInfoMapper.queryMipByCondition(oamMip);
			if(oam==null){
				oam=new OamMipInfo();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return oam;
	}
	
	public OamMepInfoMapper getOamMepInfoMapper() {
		return oamMepInfoMapper;
	}

	public void setOamMepInfoMapper(OamMepInfoMapper oamMepInfoMapper) {
		this.oamMepInfoMapper = oamMepInfoMapper;
	}

	public OamMipInfoMapper getOamMipInfoMapper() {
		return oamMipInfoMapper;
	}

	public void setOamMipInfoMapper(OamMipInfoMapper oamMipInfoMapper) {
		this.oamMipInfoMapper = oamMipInfoMapper;
	}

	public OamLinkInfoMapper getLinkInfoMapper() {
		return linkInfoMapper;
	}

	public void setLinkInfoMapper(OamLinkInfoMapper linkInfoMapper) {
		this.linkInfoMapper = linkInfoMapper;
	}
	
}
