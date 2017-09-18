package com.nms.model.path;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.dao.path.SegmentMapper;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.ptn.qos.QosQueueService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class SegmentService_MB extends ObjectService_Mybatis {
	private SegmentMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}

	public SegmentMapper getMapper() {
		return mapper;
	}

	public void setMapper(SegmentMapper mapper) {
		this.mapper = mapper;
	}
	
	
	/**
	 * 计算两个端口的端口速率是否一样
	 * @param portInst_a 端口A
	 * @param portInst_z 端口Z
	 * @return true 速率相同 false 速率不同
	 * @throws Exception
	 */
	public boolean comparePortSpeed(PortInst portInst_a, PortInst portInst_z) throws Exception {
		if (null == portInst_a && null == portInst_z) {
			throw new Exception("portInst is null");
		}
		boolean flag = false;
		try {
			int speed_a = this.getPortSpeed(portInst_a);
			int speed_z = this.getPortSpeed(portInst_z);
			if (speed_a == speed_z) {
				flag = true;
			}
		} catch (Exception e) {
			throw e;
		}
		return flag;
	}
	
	/**
	 * 根据端口获取端口速率
	 * @param portInst 端口对象
	 * @return 速率 100 1000 以MB为单位
	 * @throws Exception
	 */
	private int getPortSpeed(PortInst portInst) throws Exception {
		String speed = null;
		int beginIndex = 0;
		int result = 0;
		try {
			// 此字段有值，说明是晨晓的端口
			if (portInst.getPortAttr().getPortSpeed() != 0) {
				// 速率为code名称
				speed = UiUtil.getCodeById(portInst.getPortAttr().getPortSpeed()).getCodeName();
				// 根据速率，取出第一个为int类型的索引 如 自协商1G 索引指向1
				for (int i = 0; i < speed.length(); i++) {
					if (Character.isDigit(speed.charAt(i))) {
						beginIndex = i;
						break;
					}
				}
				// 取出速率单位。 单位放在最后一个字符
				String unit = speed.substring(speed.length() - 1, speed.length());
				// 根据int类型开始索引取出速率
				speed = speed.substring(beginIndex, speed.length() - 1);
				result = Integer.parseInt(speed);
				// 如果单位是G 把速率转换为M 因为武汉速率都为整数。 所以在这里乘以1000而不是1024
				if ("G".equals(unit)) {
					result = result * 1000;
				}
			} else if (portInst.getPortAttr().getWorkModel() != 0) { // 如果此字段有值 说明是武汉端口 武汉端口的速率格式为 100M 1000M 自协商 等
				// 速率为code名称
				speed = UiUtil.getCodeById(portInst.getPortAttr().getWorkModel()).getCodeName();
				// 取出M的索引
				int unitIndex = speed.indexOf('M');
				// 如果M的索引为-1 说明是自协商 需要根据端口类型验证
				// fe=100M ge、fx=1000M xg=10000M
				if (unitIndex == -1) {
					// 取端口类型 fe、ge、fx、xg
					String portType = portInst.getPortName().substring(0, 2);
					if ("fe".equals(portType)) {
						result = 100;
					} else if ("xg".equals(portType)) {
						result = 10000;
					} else if ("ge".equals(portType) || "fx".equals(portType)) {
						result = 1000;
					}
				} else {
					result = Integer.parseInt(speed.substring(0, unitIndex));
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	/**
	 * 根据segment.getID()判断修改还是增加
	 * 
	 * @param segment
	 *            实体
	 * @return 执行成功的记录数
	 * @throws Exception
	 */
	public int saveOrUpdate(Segment segment, List<QosQueue> qosQueues, List<OamInfo> oamInfos) throws Exception {
		if (segment == null) {
			throw new Exception("segment is null");
		}
		int result = 0;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			if (segment.getId() == 0) {
				segment.setCREATTIME(DateUtil.getDate(DateUtil.FULLTIME));
				this.mapper.insert(segment);
				result = segment.getId();
				updatePortOccupy(segment, 1);
				// 保存段的qos信息
				this.saveQosQueue(qosQueues, result);
				// 保存段的oam信息
				this.saveOam(oamInfos, segment, result);
			} else {
				result = segment.getId();
				this.mapper.update(segment);
				// 修改段的qos信息
				this.saveQosQueue(qosQueues, result);
				// 修改段的oam信息
				this.saveOam(oamInfos, segment, result);
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return result;
	}
	
	/**
	 * 保存段的qos信息
	 */
	private void saveQosQueue(List<QosQueue> qosQueues, int result) throws Exception {
		QosQueueService_MB qosService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue, this.sqlSession);
		if (qosQueues != null && qosQueues.size() > 0) {
			qosService.saveOrUpdate(qosQueues);
		}
	}
	
	/**
	 * 保存段的oam信息
	 */
	private void saveOam(List<OamInfo> oamInfos, Segment segment, int result) throws Exception {
		OamInfoService_MB oamService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo, this.sqlSession);
		if (oamInfos != null && oamInfos.size() > 0) {
			if(oamInfos.get(0).getOamMep().getSiteId() == segment.getASITEID()){
				oamInfos.get(0).getOamMep().setObjId(segment.getAPORTID());
				oamInfos.get(1).getOamMep().setObjId(segment.getZPORTID());
			}else if(oamInfos.get(0).getOamMep().getSiteId() == segment.getZSITEID()){
				oamInfos.get(0).getOamMep().setObjId(segment.getZPORTID());
				oamInfos.get(1).getOamMep().setObjId(segment.getAPORTID());
			}
			for (OamInfo oamInfo : oamInfos) {
				OamMepInfo mep = oamInfo.getOamMep();
				if (mep != null) {
					mep.setServiceId(result);
					oamService.saveOrUpdate(oamInfo);
				}
			}
		}
	}
	
	/**
	 * 通过主键修改
	 * 
	 * @param ID
	 *            主键
	 * @return 删除的记录数
	 * @throws Exception
	 */
	public int deleteByIds(List<Segment> segments) throws Exception {
		int result = 0;
		QosQueueService_MB qosService = null;
		OamInfoService_MB oamService = null;
		OamMepInfo oamMepInfo = null;
		QosQueue qos = null;
		OamInfo oamInfo = null;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			for (Segment segment : segments) {
				// 删除段信息
				result = this.mapper.deletById(segment.getId());
				// 修改端口属性
				updatePortOccupy(segment, 0);

				// 删除段上的qos
				qosService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue, this.sqlSession);
				oamService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo, this.sqlSession);
				qos = new QosQueue();
				qos.setServiceId(segment.getId());
				qos.setObjType("SECTION");
				qosService.deleteByServiceId(qos);
				// 删除段上的oam
				oamMepInfo = new OamMepInfo();
				oamInfo = new OamInfo();
				oamInfo.setOamMep(oamMepInfo);
				oamMepInfo.setServiceId(segment.getId());
				oamMepInfo.setObjType("SECTION");
				oamService.delete(oamInfo);
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return result;
	}
	
	public void updatePortOccupy(Segment segment, int isoccupy) throws Exception {
		PortService_MB portService = null;
		List<PortInst> port=null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			PortInst portinst = new PortInst();
			portinst.setPortId(segment.getAPORTID());
			PortInst updateportinst = null;
			port=portService.select(portinst);
			if(port != null && port.size()>0){
				updateportinst = port.get(0);
				updateportinst.setIsOccupy(isoccupy);
				portService.saveOrUpdate(updateportinst);
			}
			portinst = new PortInst();
			portinst.setPortId(segment.getZPORTID());
			port=portService.select(portinst);
			if(port != null && port.size()>0){
				updateportinst = port.get(0);
				updateportinst.setIsOccupy(isoccupy);
				portService.saveOrUpdate(updateportinst);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
			port=null;
		}
	}
	
	
	/**
	 * 通过主键修改
	 * 
	 * @param ID
	 *            主键
	 * @return 删除的记录数
	 * @throws Exception
	 */
	public int delete(int ID) throws Exception {
		int result = 0;
		QosQueueService_MB qosService = null;
		OamInfoService_MB oamService = null;
		OamMepInfo oamMepInfo = null;
		Segment segment = new Segment();
		QosQueue qos = null;
		OamInfo oamInfo = null;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			segment.setId(ID);
			segment = this.select(segment).get(0);
			// 删除段信息
			result = this.mapper.deletById(ID);
			// 修改端口属性
			updatePortOccupy(segment, 0);

			// 删除段上的qos
			qosService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue, this.sqlSession);
			oamService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo, this.sqlSession);
			qos = new QosQueue();
			qos.setServiceId(ID);
			qos.setObjType("SECTION");
			qosService.deleteByServiceId(qos);
			// 删除段上的oam
			oamMepInfo = new OamMepInfo();
			oamInfo = new OamInfo();
			oamInfo.setOamMep(oamMepInfo);
			oamMepInfo.setServiceId(ID);
			oamMepInfo.setObjType("SECTION");
			oamService.delete(oamInfo);
			
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
		}
		return result;
	}
	
	/**
	 * 根据条件查询
	 * 
	 * @param segment
	 *            查询条件
	 * @return List<Segment> 集合
	 * @throws Exception
	 */
	public List<Segment> select(Segment segment) throws Exception {
		List<Segment> segmentList = null;
		OamInfo oamInfo = null;
		OamInfoService_MB infoService = null;
		List<OamInfo> oamInfos = null;
		try {
			segmentList = this.mapper.queryByCondition(segment);
			infoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo, this.sqlSession);
			if(segmentList != null && segmentList.size()>0){
				for(Segment segment1 : segmentList){
					oamInfo = new OamInfo();
					OamMepInfo oamMepInfo = new OamMepInfo();
					oamMepInfo.setServiceId(segment1.getId());
					oamMepInfo.setObjType("SECTION");
					oamInfo.setOamMep(oamMepInfo);
					oamInfos = infoService.queryByServiceId(oamInfo);
					segment1.setOamList(oamInfos);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			segment = null;
		}
		return segmentList;
	}

	/**
	 * 根据网元ID查询
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public List<Segment> selectBySite(int siteId) throws Exception {
		return this.mapper.query_site(siteId);
	}
	
	/**
	 * 根据siteId和portId去查询
	 * 获取参数时取segmentCondition的aSiteId和aPortId
	 * @param segmentCondition
	 * @return
	 * @throws Exception
	 */
	public List<Segment> selectBySiteIdAndPort(Segment segmentCondition) throws Exception {
		return this.mapper.queryBySiteIdAndPortId(segmentCondition);
	}
	
	
	/**
	 * 根据两端端口ID查询数据
	 * @param portid_one
	 *            其中一个端口主键
	 * @param portid_two
	 *            第二个端口主键
	 * @param connection
	 *            数据库连接
	 * @return
	 * @throws Exception
	 */
	public List<Segment> select_search(int portid_one, int portid_two) throws Exception {
		List<Segment> segmentList =this.mapper.query_search(portid_one, portid_two);				
		return segmentList;
	}
	
	public List<Segment> selectBySegmentPortId(int portId) throws Exception {
		return this.mapper.query_SegmentPortId(portId);
	}
	
	/**
	 * 查询全部
	 * 
	 * @return List<Segment>集合
	 * @throws Exception
	 */
	public List<Segment> select() throws Exception {
		List<Segment> segmentList = null;
		Segment segment = null;
		OamInfoService_MB infoService = null;
		OamInfo oamInfo = null;
		List<OamInfo> oamInfos = null;
		try {
			segment = new Segment();
			infoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo, this.sqlSession);
			segmentList = this.mapper.queryByCondition(segment);
			if(segmentList != null && segmentList.size()>0){
				for(Segment segment1 : segmentList){
					oamInfo = new OamInfo();
					OamMepInfo oamMepInfo = new OamMepInfo();
					oamMepInfo.setServiceId(segment1.getId());
					oamMepInfo.setObjType("SECTION");
					oamInfo.setOamMep(oamMepInfo);
					oamInfos = infoService.queryByServiceId(oamInfo);
					segment1.setOamList(oamInfos);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
		return segmentList;
	}
	
	public List<Segment> queryByCondition(Segment segment) throws Exception {
		List<Segment> segmentList = null;
		OamInfoService_MB infoService = null;
		OamInfo oamInfo = null;
		List<OamInfo> oamInfos = null;
		try {
			infoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo, this.sqlSession);
			segmentList = mapper.queryByCondition(segment);
			if(segmentList != null && segmentList.size()>0){
				for(Segment segment1 : segmentList){
					oamInfo = new OamInfo();
					OamMepInfo oamMepInfo = new OamMepInfo();
					oamMepInfo.setServiceId(segment1.getId());
					oamMepInfo.setObjType("SECTION");
					oamInfo.setOamMep(oamMepInfo);
					oamInfos = infoService.queryByServiceId(oamInfo);
					segment1.setOamList(oamInfos);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
//			UiUtil.closeService(infoService);
		}
		return segmentList;
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
	 * 通过网元id，初始化相关段
	 * @param siteId
	 * @throws SQLException
	 */
	public void initializtionSite(int siteId) throws SQLException{
		List<Segment> segments = null;
		try {
			segments = this.selectBySite(siteId);
			if(segments != null && segments.size()>0){
				this.deleteByIds(segments);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
}
