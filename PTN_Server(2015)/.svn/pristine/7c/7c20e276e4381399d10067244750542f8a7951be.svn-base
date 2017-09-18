package com.nms.model.equipment.port;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.port.PortAttr;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.dao.equipment.port.PortAttrMapper;
import com.nms.db.dao.equipment.port.PortInstMapper;
import com.nms.db.dao.equipment.shelf.SiteInstMapper;
import com.nms.db.dao.ptn.port.PortLagInfoMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EQosDirection;
import com.nms.db.enums.EServiceType;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.ptn.path.tunnel.LspInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.ptn.qos.QosInfoService_MB;
import com.nms.model.ptn.qos.QosQueueService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public class PortService_MB extends ObjectService_Mybatis {
	private PortInstMapper mapper = null;

	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}

	public PortInstMapper getMapper() {
		return mapper;
	}

	public void setMapper(PortInstMapper mapper) {
		this.mapper = mapper;
	}

	/**
	 * 新增或修改portinst对象，通过portinst.getportId()来判断是修改还是新增
	 * 
	 * @param portinst
	 * @return
	 * @throws Exception
	 */
	public int saveOrUpdate(PortInst portinst) throws Exception {
		if (portinst == null) {
			throw new Exception("portinst is null");
		}
		int result = 0;
		try {
			if (portinst.getPortId() == 0) {
				SiteInstMapper siteMapper = this.sqlSession.getMapper(SiteInstMapper.class);
				SiteInst site = new SiteInst();
				site.setSite_Inst_Id(portinst.getSiteId());
				List<SiteInst> siteList = siteMapper.queryByCondition(site);
				if (siteList != null && siteList.size() == 1) {
					if (siteList.get(0).getManufacturer() == 0) {
						if (portinst.getNumber() > 0 && !portinst.getPortName().contains("e1")) {
							portinst.setIsEnabled_code(1);
						}
					}
				}
				result = this.mapper.insert(portinst);
				// 离线网元数据下载
				super.dateDownLoad(portinst.getSiteId(), result, EServiceType.ETH.getValue(), EActionType.UPDATE.getValue(), portinst.getPortId() + "", null, 0, 0, null);
			} else {
				result = this.mapper.update(portinst);
				// 离线网元数据下载
				super.dateDownLoad(portinst.getSiteId(), portinst.getPortId(), EServiceType.ETH.getValue(), EActionType.UPDATE.getValue(), portinst.getPortId() + "", null, 0, 0, null);
				if (null != portinst.getLagInfo()) {
					PortLagInfoMapper portLagMapper = this.sqlSession.getMapper(PortLagInfoMapper.class);
					if (portinst.getLagInfo().getId() > 0) {
						portLagMapper.update(portinst.getLagInfo());
					} else {
						PortLagInfo portLagInfo = new PortLagInfo();
						portLagInfo = portinst.getLagInfo();
						portLagInfo.setSiteId(portinst.getSiteId());
						portLagInfo.setPortId(portinst.getPortId());
						portLagMapper.insert(portLagInfo);
					}
				}
				// 修改端口属性表
				if (portinst.getPortAttr() != null) {
					PortAttrMapper portAttrMapper = this.sqlSession.getMapper(PortAttrMapper.class);
					portAttrMapper.update(portinst.getPortAttr());
				}
			}
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return result;
	}

	/**
	 * 根据条件查询portinst
	 * 
	 * @param portinst
	 *            查询条件
	 * @return List<PortInst> 集合
	 * @throws Exception
	 */
	public List<PortInst> select(PortInst portinst) throws Exception {
		List<PortInst> portinstList = null;
		try {
			portinstList = new ArrayList<PortInst>();
			portinstList = this.mapper.queryByCondition(portinst);
			if(portinstList!=null ){
				for (int i = 0; i < portinstList.size(); i++) {
					if (portinstList.get(i).getPortType().equals("stm-1")) {
						portinstList.get(i).setChildPortList(this.mapper.quertyChildPort(portinstList.get(i).getPortId()));
					} else {
						portinstList.get(i).setChildPortList(new ArrayList<PortInst>());
					}
					this.setPortAttr(portinstList.get(i));
					List<QosQueue> qosQueues = this.findQosQueueByPortId(portinstList.get(i).getPortId());
					if (qosQueues != null && qosQueues.size() > 0) {
						portinstList.get(i).setQosQueues(qosQueues);
					} else {
						this.setPortQos(portinstList.get(i));
					}
				}
			}else{
				portinstList = new ArrayList<PortInst>();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return portinstList;
	}

	private void setPortAttr(PortInst portInst) throws Exception {
		PortAttr portAttr = null;
		try {
			PortAttrService_MB portAttrService = (PortAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PortAttr, super.sqlSession);
			portAttr = portAttrService.select_portId(portInst.getPortId());
			portInst.setPortAttr(portAttr);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 根据端口ID和类型查询qos队列
	 * 
	 * @param portId
	 * @return List<QosQueue>
	 * @throws Exception
	 */
	private List<QosQueue> findQosQueueByPortId(int portId) throws Exception {
		List<QosQueue> list = null;
		try {
			QosQueueService_MB qosQueueService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue, super.sqlSession);
			QosQueue qos = new QosQueue();
			qos.setObjId(portId);
			qos.setObjType("SECTION");
			list = qosQueueService.queryByCondition(qos);
		} catch (Exception e) {
			throw e;
		}
		return list;
	}

	private void setPortQos(PortInst portInst) throws Exception {
		List<QosQueue> qosQueueList = new ArrayList<QosQueue>();
		try {
			for (int i = 0; i < 8; i++) {
				QosQueue qosQueue = new QosQueue();
				qosQueue.setServiceId(0);
				qosQueue.setCos(i);
				qosQueue.setSiteId(portInst.getSiteId());
				qosQueue.setObjType("SECTION");
				qosQueue.setQueueType("SP");
				qosQueue.setMostBandwidth(ResourceUtil.srcStr(StringKeysObj.QOS_UNLIMITED));
				qosQueue.setWeight(1);
				qosQueue.setDisCard(50);
				qosQueue.setWredEnable(false);
				qosQueue.setGreenHighThresh(90);
				qosQueue.setGreenLowThresh(50);
				qosQueueList.add(qosQueue);
			}
			portInst.setQosQueues(qosQueueList);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 查询端口剩余带宽
	 * 
	 * @param portId
	 * @return
	 * @throws Exception
	 */
	public Map<Integer, Integer> getResidueQos(int portId) throws Exception {
		QosQueueService_MB qosQueueServiceMB = null;
		QosQueue qosQueue = null;
		List<QosQueue> portQosList = null;
		Map<Integer, Integer> mapResult = null;
		try {
			// 根据端口和类型查询出此端口的qos集合
			qosQueueServiceMB = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue, this.sqlSession);
			qosQueue = new QosQueue();
			qosQueue.setObjId(portId);
			qosQueue.setObjType(EServiceType.SECTION.toString());
			portQosList = qosQueueServiceMB.queryByCondition(qosQueue);

			// 获取已用带宽和
			mapResult = this.getSumQos(portId);

			for (QosQueue qosQueueSelect : portQosList) {
				if (null == mapResult.get(qosQueueSelect.getCos())) {
					mapResult.put(qosQueueSelect.getCos(), qosQueueSelect.getCir());
				} else {
					mapResult.put(qosQueueSelect.getCos(), qosQueueSelect.getCir() - mapResult.get(qosQueueSelect.getCos()));
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			// UiUtil.closeService(qosQueueService);
		}
		return mapResult;
	}

	/**
	 * 查询端口下已用的总带宽
	 * 
	 * @param portId
	 * @return
	 * @throws Exception
	 */
	public Map<Integer, Integer> getSumQos(int portId) throws Exception {
		LspInfoService_MB lspServiceMB = null;
		List<Lsp> lspList = null;
		QosInfoService_MB qosInfoServiceMB = null;
		List<QosInfo> qosInfoList = null;
		Map<Integer, Integer> mapResult = null;
		try {
			mapResult = new HashMap<Integer, Integer>();
			qosInfoServiceMB = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo, this.sqlSession);

			// 查询此端口下的所有lsp
			lspServiceMB = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO, this.sqlSession);
			lspList = lspServiceMB.selectByPort(portId);

			for (Lsp lsp : lspList) {
				// 查询此lsp对应的tunnel的qos
				qosInfoList = qosInfoServiceMB.getQosByObj(EServiceType.TUNNEL.toString(), lsp.getTunnelId());
				if (null != qosInfoList && qosInfoList.size() > 0) {
					// 遍历QOS 根据端口比较。添加到map集合中
					for (QosInfo qosInfoSelect : qosInfoList) {
						// 取前向
						if (lsp.getAPortId() == portId) {
							if (Integer.parseInt(qosInfoSelect.getDirection()) == EQosDirection.FORWARD.getValue()) {
								this.setMap(mapResult, qosInfoSelect);
							}

						} else {
							// 取后向
							if (Integer.parseInt(qosInfoSelect.getDirection()) == EQosDirection.BACKWARD.getValue()) {
								this.setMap(mapResult, qosInfoSelect);
							}
						}
					}
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
		}
		return mapResult;
	}

	/**
	 * 通过qos给map赋值
	 * 
	 * @param map
	 * @param qosInfo
	 * @throws Exception
	 */
	private void setMap(Map<Integer, Integer> map, QosInfo qosInfo) throws Exception {
		if (null == map) {
			throw new Exception("map is null");
		}
		if (null == qosInfo) {
			throw new Exception("qosInfo is null");
		}
		try {

			// 如果为null 直接赋值，否则做带宽和
			if (null == map.get(qosInfo.getCos())) {
				map.put(qosInfo.getCos(), qosInfo.getCir());
			} else {
				map.put(qosInfo.getCos(), map.get(qosInfo.getCos()) + qosInfo.getCir());
			}

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 通过端口ID查询
	 * 
	 * @param portid
	 *            端口ID
	 * @return
	 * @throws Exception
	 */
	public PortInst selectPortybyid(int portid) throws Exception {
		PortInst port = null;
		try {
			port = new PortInst();
			port = this.mapper.selectPortybyid(portid);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return port;
	}

	public List<PortInst> getAllPortByIdsAndSiteId(List<Integer> ids, int siteId) throws Exception {
		return this.mapper.quertyAllPortByIdsAndSiteId(ids, siteId);
	}

	public String getPortname(int portId) {
		PortInst portInst = null;
		List<PortInst> portInstList = null;
		String result = null;
		try {
			portInst = new PortInst();
			portInst.setPortId(portId);
			portInstList = this.select(portInst);
			if (null == portInstList || portInstList.size() != 1) {
				result = "";
			} else {
				result = portInstList.get(0).getPortName();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return result;
	}

	public List<PortInst> selectNniPortname(int siteId) throws Exception {

		List<PortInst> portInstList = new ArrayList<PortInst>();
		try {
			portInstList = mapper.quertyNNIPortbySiteId(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return portInstList;
	}

	/**
	 * 根据网元和业务类型查询e1端口和UNI端口
	 * 
	 * @param portId
	 * @return
	 * @throws Exception
	 */
	public List<PortInst> selectE1Portname(int siteId, String type) throws Exception {

		List<PortInst> portInstList = new ArrayList<PortInst>();
		try {
			portInstList = this.mapper.quertyUniOrE1PortbySiteId(siteId, type);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return portInstList;
	}

	public List<String> getAllPortNameByNumber(List<Integer> ids, int siteId) throws Exception {
		return this.mapper.quertyAllPortNameByNumber(ids, siteId);
	}

	
	/**
	 * 更加 条件 
	 * 		查询 端口（排除system）
	 * @param siteId
	 * @param PortName
	 * @return
	 * @throws Exception
	 */
	public List<PortInst> selectPortbyName(PortInst portInst) throws Exception {
		List<PortInst> portinstList = null;
		try {
			portinstList = new ArrayList<PortInst>();
			portinstList = this.mapper.queryByIsOccupy(portInst);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return portinstList;
	}


	/**
	 * 初始化端口信息（武汉）
	 * 
	 * @param siteId
	 */
	public void initializtionSite(int siteId) {
		PortInst portInst = null;
		List<PortInst> portInsts = null;
		try {
			portInst = new PortInst();
			portInst.setSiteId(siteId);
			portInsts = select(portInst);
			if (portInsts != null && portInsts.size() > 0) {
				for (PortInst inst : portInsts) {
					if ("NNI".equals(inst.getPortType()) || "UNI".equals(inst.getPortType())) {
						inst.setPortType("NONE");
						// inst.setIsOccupy(0);
						// inst.setIsEnabled_code(0);
						saveOrUpdate(inst);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			portInst = null;
			portInsts = null;
		}

	}


	public List<PortInst> selectPortbySiteandPortname(int siteId, String PortName) {
		List<PortInst> portinstList = null;
		try {
			portinstList = this.mapper.quertyPortbySiteandName(siteId, PortName);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return portinstList;
	}

	/**
	 * 修改使能状态
	 * @param siteId
	 * @param status
	 * @param portType
	 * @throws Exception
	 */
	public void updateActiveStatus(int siteId, int status, String portType) throws Exception {

		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			this.mapper.updateStatus(siteId, status, portType);
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
	
	/**
	 * 同步时修改portinst
	 * 
	 * @author kk
	 * 
	 * @param portInst
	 *            端口对象
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public void update_synchro(PortInst portInst) throws Exception {
		this.mapper.update_synchro(portInst);
		PortAttrMapper portAttrMapper =this.sqlSession.getMapper(PortAttrMapper.class);
		if (null != portInst.getPortAttr()) {
			portAttrMapper.update(portInst.getPortAttr());
		}
       this.sqlSession.commit();
	}

	/**
	 * 修改状态
	 * @param portInst
	 * 				端口
	 * @throws Exception
	 */
	public void update(PortInst portInst) throws Exception {
		try {
			this.mapper.updatestatus(portInst);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	
	/**
	 * 查询以太网端口是否被占用， 要查询段、tunnel、ac、lag
	 * @param portInst 端口信息
	 * @return true=占用  false=空闲
	 * @throws Exception 
	 */
	public boolean getPortUse(PortInst portInst) throws Exception{
		boolean flag=true;	//默认占用
		AcPortInfoService_MB acInfoService=null;
		AcPortInfo acPortInfo=null;
		List<AcPortInfo> acPortInfoList=null;
		SegmentService_MB segmentService=null;
		List<Segment> segmentList=null;
		LspInfoService_MB lspService=null;
		List<Lsp> lspList=null;
		try {
			if("NONE".equals(portInst.getPortType())){
				flag=false;
			}else{
				//验证有没有被lag使用
				if(portInst.getLagId() == 0){
					flag=false;
				}else{
					//如果是UNI 验证是否创建了AC
					if("UNI".equals(portInst.getPortType())){
						acInfoService=(AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo, this.sqlSession);
						acPortInfo=new AcPortInfo();
						acPortInfo.setPortId(portInst.getPortId());
						acPortInfoList = acInfoService.selectByCondition(acPortInfo);
						//没查到。 空闲
						if(null==acPortInfoList || acPortInfoList.size()==0){
							flag=false;
						}
					}else if("NNI".equals(portInst.getPortType())){
						//如果是NNI 验证是否创建了段和tunnel
						segmentService=(SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT, this.sqlSession);
						segmentList = segmentService.selectBySegmentPortId(portInst.getPortId());
						//没查到段，去查tunnel
						if(null==segmentList||segmentList.size()==0){
							lspService=(LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO, this.sqlSession);
							lspList = lspService.selectByPort(portInst.getPortId());
							//没查到 空闲
							if(null==lspList||lspList.size()==0){
								flag=true;
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			throw e;
		} finally{
			acPortInfo=null;
			acPortInfoList=null;
			segmentList=null;
			lspList=null;
		}
		return flag;
	}
}
