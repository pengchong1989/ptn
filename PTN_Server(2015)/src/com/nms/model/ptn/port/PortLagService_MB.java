package com.nms.model.ptn.port;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.Businessid;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.dao.equipment.port.PortInstMapper;
import com.nms.db.dao.ptn.BusinessidMapper;
import com.nms.db.dao.ptn.port.PortLagInfoMapper;
import com.nms.db.dao.ptn.qos.QosQueueMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;

public class PortLagService_MB extends ObjectService_Mybatis{
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	private PortLagInfoMapper mapper;

	public PortLagInfoMapper getPortLagInfoMapper() {
		return mapper;
	}

	public void setPortLagInfoMapper(PortLagInfoMapper portLagInfoMapper) {
		this.mapper = portLagInfoMapper;
	}

	public List<PortLagInfo> selectPortByCondition(PortLagInfo portLagInfo) {
		List<PortLagInfo> portLagInfoList = new ArrayList<PortLagInfo>();
		try {
			portLagInfoList = this.mapper.queryByCondition(portLagInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return portLagInfoList;
	}

	public void updateLag(PortLagInfo portLagInfo) {
		boolean flag_qos_insert = false;
		try {
			SiteService_MB siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
			PortInstMapper portInstMapper = this.sqlSession.getMapper(PortInstMapper.class);
			QosQueueMapper qosQueueMapper = this.sqlSession.getMapper(QosQueueMapper.class);
			// 查询所有此lag修改之前的数据 修改端口用
			PortLagInfo portLagInfo_select = new PortLagInfo();
			portLagInfo_select.setId(portLagInfo.getId());
			List<PortLagInfo> portLagInfoList = this.selectByCondition(portLagInfo_select);
			if (null != portLagInfoList && portLagInfoList.size() == 1) {
				portLagInfo_select = portLagInfoList.get(0);
				for (PortInst portInst : portLagInfo_select.getPortList()) {
					portInst.setLagId(0);
					if (siteService.getManufacturer(portInst.getSiteId()) == EManufacturer.CHENXIAO.getValue()) {
						portInst.setPortType("NONE");
					}
					portInstMapper.update(portInst);
				}
			}
			// 修改端口状态
			for (PortInst portinst : portLagInfo.getPortList()) {
				portinst.setPortType(portLagInfo.getRole());
				portinst.setLagId(portLagInfo.getId());
				portInstMapper.update(portinst);
			}
			// 修改QOS 先查询此lag的qos是否存在
			QosQueue qosQueue_select = new QosQueue();
			qosQueue_select.setObjId(portLagInfo.getId());
			qosQueue_select.setObjType("LAG");
			List<QosQueue> qosQueueList = qosQueueMapper.queryByCondition(qosQueue_select);
			if (null == qosQueueList || qosQueueList.size() == 0) {
				flag_qos_insert = true;
			}
			// 遍历此lag的qos 如果不存在就新建 如果存在就修改
			for (QosQueue qosQueue : portLagInfo.getQosQueueList()) {
				qosQueue.setObjId(portLagInfo.getId());
				if (flag_qos_insert) {
					qosQueueMapper.insert(qosQueue);
				} else {
					for (QosQueue qosQueueSelect : qosQueueList) {
						if (qosQueueSelect.getCos() == qosQueue.getCos()) {
							qosQueue.setId(qosQueueSelect.getId());
							break;
						}
					}
					qosQueueMapper.update(qosQueue);
				}
			}
			// 修改LAG
			this.mapper.update(portLagInfo);
			// 离线网元数据下载
			super.dateDownLoad(portLagInfo.getSiteId(), portLagInfo.getId(), EServiceType.LAG.getValue(), EActionType.UPDATE.getValue(), portLagInfo.getLagID() + "", null, 0, 0, null);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * 查找端口高级配置
	 * @param portLagInfo
	 * @return
	 * @throws Exception
	 */
	public List<PortLagInfo> selectByCondition(PortLagInfo portLagInfo) throws Exception {
		List<PortLagInfo> portLagInfoList = new ArrayList<PortLagInfo>();
		try {
			PortInstMapper portInstMapper = this.sqlSession.getMapper(PortInstMapper.class);
			QosQueueMapper qosQueueMapper = this.sqlSession.getMapper(QosQueueMapper.class);
			portLagInfoList = this.mapper.queryByCondition(portLagInfo);
			for (PortLagInfo portLagInfoSelect : portLagInfoList) {
				// 设置此lag的所有端口
				PortInst portInst = new PortInst();
				portInst.setLagId(portLagInfoSelect.getId());
				portLagInfoSelect.setPortList(portInstMapper.queryByCondition(portInst));
				// 设置此lag下的qos
				QosQueue qosQueue = new QosQueue();
				qosQueue.setObjId(portLagInfoSelect.getId());
				qosQueue.setObjType("LAG");
				portLagInfoSelect.setQosQueueList(qosQueueMapper.queryByCondition(qosQueue));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return portLagInfoList;
	}

	public List<PortLagInfo> selectLAGByCondition(PortLagInfo portLagInfo) {
		List<PortLagInfo> portLagInfoList = new ArrayList<PortLagInfo>();
		try {
			portLagInfoList = this.mapper.queryByCondition(portLagInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return portLagInfoList;
	}

	public int updateStatus(PortLagInfo portLagInfo) {
		return this.mapper.update(portLagInfo);
	}
	
	/**
	 * 根据lag主键查询lag名称。 显示用
	 * 
	 * @author kk
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public String getLagName(int lagId) throws Exception {
		PortLagInfo portLagInfo = null;
		List<PortLagInfo> portlagInfoList = null;
		try {

			portLagInfo = new PortLagInfo();
			portLagInfo.setId(lagId);
			portlagInfoList = this.selectByCondition(portLagInfo);

			if (null != portlagInfoList && portlagInfoList.size() == 1) {
				return "lag/" + portlagInfoList.get(0).getLagID();
			} else {
				throw new Exception("查询lag出错");
			}
		} catch (Exception e) {
			throw e;
		}
	}
	
	public int selectLagCountByNeId(int NeId) throws Exception {
		return mapper.selectCountByNeId(NeId);
	}
	
	public int saveOrUpdate(PortLagInfo portLagInfo) throws Exception {
		int i = 0;
		PortInst portInfo_select = null;
		List<PortInst> portList = null;
		BusinessidMapper businessidMapper = null;
		PortService_MB portService = null;
		if (portLagInfo == null) {
			throw new Exception("portLagInfo为空");
		}
		try {
			businessidMapper = sqlSession.getMapper(BusinessidMapper.class);
			
			// 取从id管理表中取设备ID
			Businessid lagBusinessId = null;
			if (portLagInfo.getLagID() == 0) { // 如果等于0 去ID管理表查一个新值 否则去ID管理表查id对象。
				lagBusinessId = businessidMapper.queryList(portLagInfo.getSiteId(), "lag").get(0);
			} else {
				lagBusinessId = businessidMapper.queryByIdValueSiteIdtype(portLagInfo.getLagID(), portLagInfo.getSiteId(), "lag");
			}
			if (lagBusinessId == null) {
				throw new BusinessIdException("lagBusinessId is null");
			}
			portLagInfo.setLagID(lagBusinessId.getIdValue());
			// 修改状态
			lagBusinessId.setIdStatus(1);
			businessidMapper.update(lagBusinessId);
			if (portLagInfo.getId() > 0) {
				mapper.update(portLagInfo);
				i = portLagInfo.getId();
				// 离线网元数据下载
				super.dateDownLoad(portLagInfo.getSiteId(), i, EServiceType.LAG.getValue(), EActionType.UPDATE.getValue(), portLagInfo.getLagID() + "", null, portLagInfo.getPortId(), 0, null);
			} else {
				mapper.insert(portLagInfo);
				i = portLagInfo.getId();
				portLagInfo.setId(i);
				// 离线网元数据下载
				super.dateDownLoad(portLagInfo.getSiteId(), i, EServiceType.LAG.getValue(), EActionType.INSERT.getValue(), portLagInfo.getLagID() + "", null, portLagInfo.getPortId(), 0, null);
			}
			// 修改lag时,修改端口
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT, this.sqlSession);
			portInfo_select = new PortInst();
			portInfo_select.setLagId(portLagInfo.getId());
			portInfo_select.setSiteId(portLagInfo.getSiteId());
			portList = portService.select(portInfo_select);
			if (null != portList && portList.size() > 0) {
				for (PortInst portInst : portList) {
					portInst.setLagId(0);
					portService.getMapper().update(portInst);
				}
			}
			// 修改所选端口的lagid和角色
			for (String number : portLagInfo.getPortLagMember().split(",")) {
				if (Integer.parseInt(number) > 0) {
					PortInst portInst = new PortInst();
					portInst.setNumber(Integer.parseInt(number));
					portInst.setSiteId(portLagInfo.getSiteId());
					portInst = portService.getMapper().queryByCondition(portInst).get(0);
					portInst.setLagId(i);
					portService.getMapper().update(portInst);
				}
			}
			
			sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
		}
		return 0;
	}
	
	public void delete(PortLagInfo portLagInfo) throws Exception {
		PortInst portInst = null;
		PortInstMapper portInstMapper = null;
		List<PortInst> portInstList = null;
		QosQueueMapper qosQueueMapper = null;
		QosQueue qosQueue = null;
		Businessid businessid = null;
		BusinessidMapper businessidMapper = null;
		PortService_MB portService = null;
		SiteService_MB siteService = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE, this.sqlSession);
			businessidMapper = sqlSession.getMapper(BusinessidMapper.class);
			qosQueueMapper = sqlSession.getMapper(QosQueueMapper.class);
			portInstMapper = sqlSession.getMapper(PortInstMapper.class);
			
			// 离线网元数据下载
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT, this.sqlSession);
			PortInst portInstSel = new PortInst();
			portInstSel.setLagId(portLagInfo.getId());
			portInstList = portService.select(portInstSel);
			if (portInstList.size() > 0) {
				portInst = portInstList.get(0);
			}
			super.dateDownLoad(portLagInfo.getSiteId(), portLagInfo.getId(), EServiceType.LAG.getValue(), EActionType.DELETE.getValue(), portLagInfo.getLagID() + "", null, portInst.getPortId(), 0, null);

			if (portLagInfo.getId() > 0) {
				// 查询此lag下的所有port
				portInst = new PortInst();
				portInst.setLagId(portLagInfo.getId());
				portInst.setSiteId(portLagInfo.getSiteId());
				portInstList = portInstMapper.queryByCondition(portInst);

				// 遍历port 把角色改成NONE lagid改成0
				for (PortInst portUpdate : portInstList) {
					portUpdate.setLagId(0);
					if (siteService.getManufacturer(portUpdate.getSiteId()) == EManufacturer.CHENXIAO.getValue()) {
						portUpdate.setPortType("NONE");
					}
					portInstMapper.update(portUpdate);
				}

				businessid = new Businessid();
				businessid.setIdStatus(0);
				businessid.setType("lag");
				businessid.setSiteId(portLagInfo.getSiteId());
				businessid.setIdValue(portLagInfo.getLagID());
				businessidMapper.updateBusinessid(businessid);

				mapper.deleteByPrimaryKey(portLagInfo.getId());

				qosQueue = new QosQueue();
				qosQueue.setObjId(portLagInfo.getId());
				qosQueue.setObjType("LAG");
				qosQueueMapper.delete(qosQueue);
			}

			sqlSession.commit();
		} catch (SQLException e) {
			sqlSession.rollback();
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			portInstMapper = null;
			qosQueueMapper = null;
			businessidMapper = null;
		}
	}
	
	public int insertLag(PortLagInfo portLagInfo) throws Exception, BusinessIdException {
		int i = 0;
		PortInstMapper portInstMapper = null;
		QosQueueMapper qosQueueMapper = null;
		BusinessidMapper businessidMapper = null;
		try {
			businessidMapper = sqlSession.getMapper(BusinessidMapper.class);
			portInstMapper = sqlSession.getMapper(PortInstMapper.class);
			qosQueueMapper = sqlSession.getMapper(QosQueueMapper.class);

			// 取从id管理表中取设备ID
			Businessid lagBusinessId = null;
			if (portLagInfo.getLagID() == 0) { // 如果等于0 去ID管理表查一个新值 否则去ID管理表查id对象。
				lagBusinessId = businessidMapper.queryList(portLagInfo.getSiteId(), "lag").get(0);
			} else {
				lagBusinessId = businessidMapper.queryByIdValueSiteIdtype(portLagInfo.getLagID(), portLagInfo.getSiteId(), "lag");
			}

			if (lagBusinessId == null) {
				throw new BusinessIdException("lagBusinessId is null");
			}
			portLagInfo.setLagID(lagBusinessId.getIdValue());
			// 修改状态
			lagBusinessId.setIdStatus(1);
			businessidMapper.update(lagBusinessId);

			mapper.insert(portLagInfo);
			i = portLagInfo.getId();

			// 修改所选端口的lagid和角色
			for (PortInst portinst : portLagInfo.getPortList()) {
				portinst.setPortType(portLagInfo.getRole());
				portinst.setLagId(i);

				portInstMapper.update(portinst);
			}

			// 新建qos
			if (null != portLagInfo.getQosQueueList() && portLagInfo.getQosQueueList().size() > 0) {
				for (QosQueue qosQueue : portLagInfo.getQosQueueList()) {
					qosQueue.setObjId(i);
					qosQueueMapper.insert(qosQueue);
				}
			}

			// 离线网元数据下载
			super.dateDownLoad(portLagInfo.getSiteId(), i, EServiceType.LAG.getValue(), EActionType.INSERT.getValue(), portLagInfo.getLagID() + "", null, 0, 0, null);
			sqlSession.commit();

		} catch (BusinessIdException e) {
			sqlSession.rollback();
			throw e;
		} catch (Exception e) {
			sqlSession.rollback();
			ExceptionManage.dispose(e, this.getClass());
		} finally {
		}
		return i;
	}
	
	public void delete(List<PortLagInfo> portLagInfoList) throws Exception {
		for (int i = 0; i < portLagInfoList.size(); i++) {
			this.delete(portLagInfoList.get(i));
		}
	}
	
	public int updateActiveStatus(int siteId, int value) throws Exception {
		return mapper.updateActiveStatus(siteId, value);

	}
	
	/**
	 * 同步中搜索lag
	 * 
	 * @param portLagInfo
	 * @return
	 * @throws Exception
	 */
	public List<PortLagInfo> selectByConditionForSynchro(PortLagInfo portLagInfo) throws Exception {
		List<PortLagInfo> portLagInfoList = new ArrayList<PortLagInfo>();
		PortInst portInst = null;
		PortInstMapper portInstMapper = null;
		QosQueueMapper qosQueueMapper = null;
		QosQueue qosQueue = null;
		try {
			portInstMapper = sqlSession.getMapper(PortInstMapper.class);
			qosQueueMapper = sqlSession.getMapper(QosQueueMapper.class);
			portLagInfoList = mapper.selectByConditionForSynchro(portLagInfo);
			for (PortLagInfo portLagInfoSelect : portLagInfoList) {

				// 设置此lag的所有端口
				portInst = new PortInst();
				portInst.setLagId(portLagInfoSelect.getId());
				portLagInfoSelect.setPortList(portInstMapper.queryByCondition(portInst));

				// 设置此lag下的qos
				qosQueue = new QosQueue();
				qosQueue.setObjId(portLagInfoSelect.getId());
				qosQueue.setObjType("LAG");

				portLagInfoSelect.setQosQueueList(qosQueueMapper.queryByCondition(qosQueue));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return portLagInfoList;
	}
	
}
