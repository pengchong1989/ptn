package com.nms.model.equipment.shlef;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.ibatis.session.SqlSession;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.shelf.EquipInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.bean.ptn.SmartFan;
import com.nms.db.bean.ptn.ecn.OSPFAREAInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.qos.QosMappingAttr;
import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.db.bean.system.Field;
import com.nms.db.bean.system.user.UserInst;
import com.nms.db.dao.alarm.CurrentAlarmInfoMapper;
import com.nms.db.dao.alarm.HisAlarmInfoMapper;
import com.nms.db.dao.equipment.card.CardInstMapper;
import com.nms.db.dao.equipment.port.E1InfoMapper;
import com.nms.db.dao.equipment.port.Port2LayerAttrMapper;
import com.nms.db.dao.equipment.port.PortAttrMapper;
import com.nms.db.dao.equipment.port.PortInstMapper;
import com.nms.db.dao.equipment.port.PortStmMapper;
import com.nms.db.dao.equipment.port.PortStmTimeslotMapper;
import com.nms.db.dao.equipment.shelf.EquipInstMapper;
import com.nms.db.dao.equipment.shelf.SiteInstMapper;
import com.nms.db.dao.equipment.slot.SlotInstMapper;
import com.nms.db.dao.perform.PerformanceTaskInfoMapper;
import com.nms.db.dao.ptn.ARPMapper;
import com.nms.db.dao.ptn.AclInstMapper;
import com.nms.db.dao.ptn.AllConfigInfoMapper;
import com.nms.db.dao.ptn.BfdInfoMapper;
import com.nms.db.dao.ptn.BlackListMacMapper;
import com.nms.db.dao.ptn.BlackWhiteMacNameMapper;
import com.nms.db.dao.ptn.CccInfoMapper;
import com.nms.db.dao.ptn.EthLoopMapper;
import com.nms.db.dao.ptn.EthServiceInstMapper;
import com.nms.db.dao.ptn.L2cpInstMapper;
import com.nms.db.dao.ptn.LabelInfoMapper;
import com.nms.db.dao.ptn.MacLearningLimitMapper;
import com.nms.db.dao.ptn.MacStudyAddressMapper;
import com.nms.db.dao.ptn.PtpPortMapper;
import com.nms.db.dao.ptn.SiteRoateMapper;
import com.nms.db.dao.ptn.SmartFanMapper;
import com.nms.db.dao.ptn.TimeSyncMapper;
import com.nms.db.dao.ptn.clock.FrequencyInfoMapper;
import com.nms.db.dao.ptn.ecn.CCNMapper;
import com.nms.db.dao.ptn.ecn.MCNMapper;
import com.nms.db.dao.ptn.ecn.OSPFAREAInfoMapper;
import com.nms.db.dao.ptn.ecn.OSPFInfoMapper;
import com.nms.db.dao.ptn.ecn.OSPFInterfaceMapper;
import com.nms.db.dao.ptn.ecn.OSPFinfo_whMapper;
import com.nms.db.dao.ptn.ecn.OspfRedistributeMapper;
import com.nms.db.dao.ptn.oam.OamEthernetInfoMapper;
import com.nms.db.dao.ptn.oam.OamLinkInfoMapper;
import com.nms.db.dao.ptn.oam.OamMepInfoMapper;
import com.nms.db.dao.ptn.oam.OamMipInfoMapper;
import com.nms.db.dao.ptn.path.StaticUnicastInfoMapper;
import com.nms.db.dao.ptn.path.pw.PwNniInfoMapper;
import com.nms.db.dao.ptn.port.AcPortInfoMapper;
import com.nms.db.dao.ptn.port.AcbufferMapper;
import com.nms.db.dao.ptn.port.PortLagInfoMapper;
import com.nms.db.dao.ptn.qos.QosInfoMapper;
import com.nms.db.dao.ptn.qos.QosMappingAttrMapper;
import com.nms.db.dao.ptn.qos.QosMappingModeMapper;
import com.nms.db.dao.ptn.qos.QosQueueMapper;
import com.nms.db.dao.ptn.qos.QosRelevanceMapper;
import com.nms.db.dao.system.FieldMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.ptn.BusinessidService_MB;
import com.nms.model.ptn.LabelInfoService_MB;
import com.nms.model.ptn.SmartFanService_MB;
import com.nms.model.ptn.ecn.MCNService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.path.protect.WrappingProtectService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.LspInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.ptn.qos.QosMappingModeAttrService_MB;
import com.nms.model.system.FieldService_MB;
import com.nms.model.system.OperationLogService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.util.EquimentDataUtil;
import com.nms.ui.manager.xmlbean.CardXml;

public class SiteService_MB extends ObjectService_Mybatis{
	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}

	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}
	
	private SiteInstMapper siteInstMapper;
	

	public SiteInstMapper getSiteInstMapper() {
		return siteInstMapper;
	}

	public void setSiteInstMapper(SiteInstMapper siteInstMapper) {
		this.siteInstMapper = siteInstMapper;
	}
	
	
	
	
	/**
	 * 主键id查询
	 * @param siteInstId
	 * @return
	 */
	public SiteInst selectById(int siteInstId){
		SiteInst siteInst = siteInstMapper.selectByPrimaryKey(siteInstId);
		return siteInst;
	}
	
	/**
	 * 新增或修改siteInst对象，通过siteInst.getId()来判断是修改还是新增
	 * 
	 * @param siteInst
	 *            实体
	 * @return 执行成功的记录数
	 * @throws Exception
	 */
	public String saveOrUpdate(SiteInst siteInst) throws Exception {
		String result = null;
		if (siteInst == null) {
			throw new Exception("siteInst is null");
		}

		EquipInst equipInst = null;
		EquipInstMapper equipMentMapper=null;
		List<SlotInst> slotInstList = null;
		SlotInstMapper slotInstMapper = null;
		BusinessidService_MB businessidService = null;
		Field field = null;
		MCNService_MB mcnService = null;
		QosMappingModeMapper qosMapModeMapper = null;
		QosMappingModeAttrService_MB qosMappingModeAttrService = null;
		int exp_phb = 0;
		int phb_exp = 0;
//		int phb_pri = 0;
		List<QosMappingAttr> qosMappingAttrsphb = null;
		List<QosMappingAttr> qosMappingAttrspri = null;
		List<QosMappingAttr> qosMappingAttrs = null;
		OSPFAREAInfoMapper ospfareaMapper = null;
		OSPFAREAInfo info = null;
		List<Field> fieldList = null;
		Field parentField;
		FieldMapper fieldMapper = null;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);
			fieldMapper = this.sqlSession.getMapper(FieldMapper.class);	
			qosMapModeMapper = this.sqlSession.getMapper(QosMappingModeMapper.class);
			qosMappingModeAttrService = (QosMappingModeAttrService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosMappingModeAttrService, this.sqlSession);
			if (siteInst.getSite_Inst_Id() == 0) {
				// connection.setAutoCommit(false);
				this.siteInstMapper.insert(siteInst);
				int siteId = siteInst.getSite_Inst_Id();						
				siteInst.setSite_Inst_Id(siteId);
				// 如果网元为M类型，则更新对应的域表
				if (siteInst.getIsGateway() == 1) {
					field = new Field();
					field.setId(siteInst.getFieldID());
					fieldList = new ArrayList<Field>();
					fieldList = fieldMapper.queryByConditions(field);
					// 如果没查询出结果，说明是子网 通过子网对象的parentID查询出域对象。进行修改
					if (fieldList.size() == 0) {
						field = fieldMapper.getSiteParent(siteInst);
						if (null != field) {
							parentField = new Field();
							parentField.setId(field.getParentId());
							field = fieldMapper.queryByConditions(parentField).get(0);
							field.setmSiteId(siteId);
							field.setObjectType("field");
							fieldMapper.updateField(field);
						}
					} else {
						field = fieldList.get(0);
						field.setmSiteId(siteId);
						field.setObjectType("field");
						fieldMapper.updateField(field);
					}

				}
				equipInst = siteInst.getEquipInst();
				equipInst.setSiteId(siteId);
				equipMentMapper = this.sqlSession.getMapper(EquipInstMapper.class);
				equipMentMapper.insert(equipInst);
				int equipId = equipInst.getId();
				
				slotInstMapper = this.sqlSession.getMapper(SlotInstMapper.class);
				slotInstList = equipInst.getSlotlist();
				for (SlotInst slotInst : slotInstList) {
					slotInst.setEquipId(equipId);
					slotInst.setSiteId(siteId);
					slotInstMapper.insert(slotInst);					
				}
				
				businessidService = (BusinessidService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BUSINESSID, this.sqlSession);
				businessidService.save(siteInst);
								
				//在新建网元的时候给网元分配一批标签备用
				this.matchingLabel(siteInst);
				//在新建网元的时候插入风扇
				this.insertOrResetFan(siteInst, 0);
				
				//如果是武汉。插入qosmodel默认数据
				if(this.getManufacturer(siteId) == EManufacturer.WUHAN.getValue()){
					QosMappingMode qosMappingMode = this.addPHBModel("config/QosModel.xml", "qosModel", siteId);
					QosMappingMode qosMappingEXP_PHB = this.addPHBModel("config/QosModel.xml", "EXP_PHB", siteId);
//					QosMappingMode qosMappingEXP_PRI = this.addPHBModel("config/QosModel.xml", "PHB_PRI", siteId);
					qosMappingAttrsphb = this.addPHBTable("config/QosTable.xml", "qosTablePHB", siteId);
					qosMappingAttrs = this.addPHBTable("config/QosTableEXP.xml", "qosTableEXP", siteId);
					qosMappingAttrspri = this.addPHBTable("config/QosTablePRI.xml", "qosTablePRI", siteId);
					for (int i = 0; i < 15; i++) {
						// PHB到TMC/TMP EXP映射表
						qosMappingMode.setName("table" + (i + 1));
						qosMapModeMapper.insert(qosMappingMode);
						phb_exp = qosMappingMode.getId();
						for (QosMappingAttr qosMappingAttr : qosMappingAttrsphb) {
							qosMappingAttr.setPhbId(phb_exp);
							qosMappingAttr.setId(0);
						}
						qosMappingModeAttrService.saveOrUpdate(qosMappingAttrsphb);
						// TMP EXP到PHB映射表
						qosMappingEXP_PHB.setName("table" + (i + 1));
						qosMapModeMapper.insert(qosMappingEXP_PHB);
						exp_phb = qosMappingEXP_PHB.getId();
						for (QosMappingAttr qosMappingAttr : qosMappingAttrs) {
							qosMappingAttr.setPhbId(exp_phb);
							qosMappingAttr.setId(0);
						}
						qosMappingModeAttrService.saveOrUpdate(qosMappingAttrs);
						// TMP PDH到PRI映射表
//						qosMappingEXP_PRI.setName("table" + (i + 1));
//						phb_pri = mappingModeDao.insert(qosMappingEXP_PRI, connection);
//						for (QosMappingAttr qosMappingAttr : qosMappingAttrspri) {			
//							qosMappingAttr.setPhbId(phb_pri);
//						}
//						qosMappingModeAttrService.saveOrUpdate(qosMappingAttrspri);
					}
					
					//等于1时说明snmp服务开启,每次新建网元后需要调用snmp的性能采集功能
//					if(CodeConfigItem.getInstance().getSnmpStartOrClose() == 1){
//						new PerformanceTrap().collectPM(siteId);
//					}
				}else{
					mcnService = (MCNService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MCN, this.sqlSession);
					mcnService.insertMcn(siteId+"","10.1.1.254/32",1500);
					
					// 插入AREA默认数据
					ospfareaMapper =this.sqlSession.getMapper(OSPFAREAInfoMapper.class);
					info = new OSPFAREAInfo();
					info.setNeId(siteId + "");
					info.setArea_range("0");
					ospfareaMapper.insert(info);
				}

			} else {
				if (1 == siteInst.getIsGateway()) {
					result = chechIsGateway(siteInst);
					if (null != result && !"".equals(result)) {
						return result;
					}
				}
				this.siteInstMapper.update(siteInst);
				field = new Field();
				field.setId(siteInst.getFieldID());
				field.setObjectType("field");
				fieldList = fieldMapper.queryByConditions(field);
				if (null != fieldList && fieldList.size() == 1) {
					field = fieldList.get(0);
					if (siteInst.getIsGateway() == 1) {// 对于类型为M，则设置
						field.setmSiteId(siteInst.getSite_Inst_Id());
					} else if (field.getmSiteId() == siteInst.getSite_Inst_Id()) {// 如果将M类型改为A类型
						field.setmSiteId(0);
					}
					fieldMapper.updateField(field);
				}
			}
			if(!(this.sqlSession.getConnection().getAutoCommit())){
				sqlSession.commit();
			}
		} catch (Exception e) {
			sqlSession.rollback();
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			sqlSession.getConnection().setAutoCommit(true);
		}
		return result;
	}
	
	
	/**
	 * type=0 给武汉设备并且只有710系列插入风扇
	 * type=1 风扇数据还原成默认值
	 * @param cellType 
	 */
	private void insertOrResetFan(SiteInst site, int type) {
		try {
			int count = 3;
			int manufacturer = site.getManufacturer();
			String siteType = site.getCellType();
			if(manufacturer == 0 && siteType.contains("710")){
				SmartFanService_MB fanService = (SmartFanService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SMARTFNASERVICE, this.sqlSession);
				if(type == 0){
					for (int i = 0; i < count; i++) {
						SmartFan fan = new SmartFan();
						fan.setSiteId(site.getSite_Inst_Id());
						fan.setWorkType(1);
						fan.setGearLevel(0);
						fanService.save(fan);
					}
				}else{
					List<SmartFan> fanList = fanService.selectAll(site.getSite_Inst_Id());
					for (SmartFan fan : fanList) {
						fanService.update(fan);
					}
				}
				
//				UiUtil.closeService(fanService);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	
	
	/**
	 * 给网元分配一批标签备用
	 * @param siteId
	 * @param cellEdition 
	 */
	private void matchingLabel(SiteInst site) {
		LabelInfoService_MB labelService = null;
		try {
			int manufacturer = site.getManufacturer();
			int siteId = site.getSite_Inst_Id();
			labelService = (LabelInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LABELINFO, this.sqlSession);
			//武汉设备
			if(manufacturer == 0){
				//目前设备的芯片不支持同一端口的lsp的入标签和该端口上的pw的入标签一样,所以要用下面的代码
				labelService.matchingLabel(siteId, "WH");
				//如果以后芯片支持同一端口的lsp的入标签和该端口上的pw的入标签一样,就用下面的代码,把上面的代码关掉
//				labelService.matchingLabel(siteId, "TUNNEL");
//				labelService.matchingLabel(siteId, "PW");
			}else{
				//晨晓设备
				labelService.matchingLabel(siteId, "CX");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
//			UiUtil.closeService(labelService);
		}
	}
	
	/**
	 * 移动网关网元时验证域下是否有网关网元
	 * 
	 * @param siteInst
	 * @return
	 * @throws Exception
	 */
	private String chechIsGateway(SiteInst siteInst) throws Exception {
		String result = null;
		FieldMapper fieldMapper = this.sqlSession.getMapper(FieldMapper.class);
		Field field = new Field();
		field.setId(siteInst.getFieldID());
		field = (Field) fieldMapper.queryByConditionAll(field, "").get(0);
		List<Field> subnetList = new ArrayList<Field>();
		// 如果上级是域
		if ("field".equals(field.getObjectType())) {
			subnetList.add(field);
			result = check(field, siteInst);
		} else {
			List<Field> parentFieldList = new ArrayList<Field>();
			Field parefield = new Field();
			parefield.setId(field.getParentId());
			parentFieldList = fieldMapper.queryByConditions(parefield);
			if (parentFieldList.size() > 0) {
				Field parentField = parentFieldList.get(0);
				result = check(parentField, siteInst);
			}
		}
		return result;
	}
	
	/**
	 * 验证的具体方法
	 * 
	 * @param field
	 *            域
	 * @param siteInst
	 * @return
	 * @throws Exception
	 */
	private String check(Field field, SiteInst siteInst) throws Exception {
		String result = null;
		List<SiteInst> siteList = new ArrayList<SiteInst>();
		SiteInst siteinst;
		FieldMapper fieldMapper = null;
		List<Field> subnetList = new ArrayList<Field>();
		SiteInst siteinstField = new SiteInst();
		try {
			fieldMapper = this.sqlSession.getMapper(FieldMapper.class);
			subnetList = new ArrayList<Field>();
			subnetList = fieldMapper.querySiteByCondition(field);
			if (subnetList.size() > 0) { 
				// 遍历所有子网下的所有网元
				for (Field field1 : subnetList) {
					siteinst = new SiteInst();
					siteinst.setFieldID(field1.getId());
					siteList = this.select(siteinst);
					if (siteList.size() > 0) {
						for (SiteInst siteInst1 : siteList) {
							if (siteInst1.getIsGateway() == 1 && !siteInst1.getCellDescribe().equals(siteInst.getCellDescribe())) {
								return result = ResourceUtil.srcStr(StringKeysTip.TIP_GATEWAY_EXIT);
							}
						}
					}
				}
			}
			// 遍历域下的网元
			siteinstField.setFieldID(field.getId());
			siteList = this.select(siteinstField);
			if (siteList.size() > 0) {
				for (SiteInst siteInst1 : siteList) {
					if(siteInst1.getSite_Inst_Id() != siteInst.getSite_Inst_Id()){
						if (siteInst1.getIsGateway() == 1 && !siteInst1.getCellDescribe().equals(siteInst.getCellDescribe())) {
							return result = ResourceUtil.srcStr(StringKeysTip.TIP_GATEWAY_EXIT);
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
		}
		return result;
	}

	
	
	/**
	 * 通过主键删除site_inst
	 * 
	 * @param id
	 *            主键
	 * @return 删除的记录数
	 * @throws Exception
	 */
	public int delete(SiteInst site) throws Exception {

		int result = 0;
		List<Field> fieldList = null;
		FieldMapper fieldMapper = null;
		Field parentField = null;
		try {
			fieldMapper = this.sqlSession.getMapper(FieldMapper.class);
			if (site.getIsGateway() == 1) {
				Field field = new Field();
				field.setId(site.getFieldID());
				fieldList = new ArrayList<Field>();
				fieldList = fieldMapper.queryByConditions(field);
				if (fieldList != null && fieldList.size() > 0) {
					field = fieldList.get(0);
				} else {
					field = fieldMapper.getSiteParent(site);
					if (null != field) {
						parentField = new Field();
						parentField.setId(field.getParentId());
						field = fieldMapper.queryByConditions(parentField).get(0);
					}
				}

				field.setmSiteId(0);
				field.setObjectType("field");
				fieldMapper.updateField(field);
			}

			// 清楚网管上的数据
			this.clearData(site,this.sqlSession, true);
			result = this.siteInstMapper.delete(site.getSite_Inst_Id());
			if(!(this.sqlSession.getConnection().getAutoCommit())){
				sqlSession.commit();
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return result;

	}
	
	
	
	/**
	 * 查询全部SiteInst
	 * 
	 * @return List<SiteInst>集合
	 * @throws Exception
	 */
	public List<SiteInst> select() throws Exception {
		List<SiteInst> siteinstList = null;
		SiteInst siteInst = null;
		try {
			siteInst = new SiteInst();
			siteinstList = new ArrayList<SiteInst>();
			siteinstList = this.siteInstMapper.queryByCondition(siteInst);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			siteInst = null;
		}
		return siteinstList;
	}
	
	
	
	/**
	 * 通过用户ID   （用户权限）查找域 --网元
	 * @param userInst
	 * @return siteinstList
	 * @throws Exception
	 */
	public List<SiteInst> selectRootSite(UserInst userInst) throws Exception {
		List<SiteInst> siteinstList = null;
		try {
			siteinstList = new ArrayList<SiteInst>();
			siteinstList = this.siteInstMapper.queryRootSiteByUserId(userInst);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return siteinstList;
	}
	
	/**
	 * 统计网元的数量
	 * @return siteinstList
	 * @throws Exception
	 */
	public List<SiteInst> queryBySiteCount() throws Exception {
		List<SiteInst> siteinstList = null;
		try {
			siteinstList = new ArrayList<SiteInst>();
			siteinstList = this.siteInstMapper.queryBySiteCount();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return siteinstList;
	}
	
	/**
	 * 通过hum_id来查询有多少个域或子网
	 * @param humId
	 * @return
	 * @throws Exception
	 */
	public List<Integer> selectFieldfromhumId(int humId) throws Exception {
		
		List<Integer> fieldList = null;
		try {
			fieldList = new ArrayList<Integer>();
			fieldList = this.siteInstMapper.queryByhumId(humId);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return fieldList;
	}
	
	
	/**
	 * 根据条件查询siteinst
	 * 
	 * @param siteinst
	 *            查询条件
	 * @return List<SiteInst> 集合
	 * @throws Exception
	 */
	public List<SiteInst> select(SiteInst siteinst) throws Exception {
		List<SiteInst> siteinstList = null;
		EquipInstService_MB equipInstService = null;
		EquipInst equipinst = null;
		List<EquipInst> equipInstList = null;

		try {
			siteinstList = new ArrayList<SiteInst>();
			siteinstList = this.siteInstMapper.queryByCondition(siteinst);
			if (siteinstList.size() == 1) {
				equipInstService = (EquipInstService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Equip, this.sqlSession);
				equipinst = new EquipInst();
				equipinst.setSiteId(siteinstList.get(0).getSite_Inst_Id());
				equipInstList = new ArrayList<EquipInst>();
				equipInstList = equipInstService.select(equipinst);
				if (equipInstList != null && equipInstList.size() == 1) {
					siteinstList.get(0).setEquipInst(equipInstList.get(0));
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
		}
		return siteinstList;
	}
	
	
	
	/**
	 * 批量更新
	 * 
	 * @param fieldList
	 * @throws Exception
	 */
	public void updateBatch(List<SiteInst> siteinstList) throws Exception {
		if (siteinstList == null) {
			throw new Exception("siteinstList is null");
		}
		try {
			for(int i=0;i<siteinstList.size();i++){
			    this.siteInstMapper.update(siteinstList.get(i));			   			
			}
			 if(!(this.sqlSession.getConnection().getAutoCommit())){
					sqlSession.commit();
				}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	/**
	 * 根据主键id批量更新网元在线状态
	 * @param siteinstList
	 * @throws Exception
	 */
	public void updateBatchOnline(List<SiteInst> siteinstList) throws Exception {
		if (siteinstList == null) {
			throw new Exception("siteinstList is null");
		}
		try {
			for(int i=0;i<siteinstList.size();i++){
			    this.siteInstMapper.updateOnline(siteinstList.get(i));			   			
			}
			 if(!(this.sqlSession.getConnection().getAutoCommit())){
					sqlSession.commit();
				}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	public void updateSite(SiteInst siteInst) throws Exception {
		if (siteInst == null) {
			throw new Exception("siteInst is null");
		}
		try {
			this.siteInstMapper.update(siteInst);
			// 离线数据下载
			super.dateDownLoad(siteInst.getSite_Inst_Id(), siteInst.getSite_Inst_Id(), EServiceType.SITE.getValue(), EActionType.UPDATE.getValue());
		    this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	/**
	 * 根据主键查询该网元是否失连
	 * 
	 * @param siteId
	 *            网元ID
	 * @return 网元是否失连
	 * @throws Exception
	 */
	public int queryNeStatus(int siteId) throws Exception {
		int isConnected = 0;
		try {
			isConnected = this.siteInstMapper.queryNeStatus(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return isConnected;
	}

	/**
	 * 根据主键查询一条记录
	 * 
	 * @param siteId
	 *            网元ID
	 * @return 网元对象
	 * @throws Exception
	 */
	public SiteInst select(Integer siteId) throws Exception {
		SiteInst siteInst = null;
		try {
			siteInst = new SiteInst();
			siteInst = siteInstMapper.selectByPrimaryKey(siteId);	
			if(siteInst==null){
				siteInst = new SiteInst();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return siteInst;
	}
	
	/**
	 * 通过IP更新网元信息
	 * @param siteIp
	 * @param loginStatus
	 * @throws Exception
	 */
	public void updateByIp(String siteIp, int loginStatus) throws Exception {
		try {
			this.siteInstMapper.updateLoginStatusByIp(siteIp, loginStatus);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	/**
	 * 初始化网元
	 * 
	 * @param id
	 *            主键
	 * @return
	 * @throws Exception
	 */
	public int initializtionSite(SiteInst site) throws Exception {
		int result = 0;
		try {
			// 清楚网管上的数据
			this.clearData(site, this.sqlSession, false);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return result;

	}
	
	
	/**
	 * 验证此网元是否可以删除
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
	public int isDeleteSite(SiteInst siteInst) throws Exception {
		int returnValue = 0;
		SegmentService_MB segmentService = null;
		List<Segment> segmentList = null;
		TunnelService_MB lspService = null;
		List<Tunnel> lspList = null;
		try {
			// 验证是否存在段
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT, this.sqlSession);
			segmentList = new ArrayList<Segment>();
			segmentList = segmentService.selectBySite(siteInst.getSite_Inst_Id());
			if (segmentList.size() > 0) {
				return returnValue = 1;
			}

			// 验证是否存在tunnel
			lspService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel, this.sqlSession);
			lspList = new ArrayList<Tunnel>();			
			lspList = lspService.selectWHNodesBySiteId(siteInst.getSite_Inst_Id());
			if (lspList.size() > 0) {
				for (Tunnel tunnel : lspList) {
					if (tunnel.getIsSingle() == 0) {
						return returnValue = 2;
					}
				}
				return returnValue;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			segmentList = null;
			lspList = null;
		}
		return returnValue;

	}
	

	
	/**
	 * 清楚跟网元相关联数据 删除网元和初始化网元可调用
	 * 
	 * @param site
	 *            网元对象
	 * @param connection
	 *            数据库连接
	 * @param isDelete
	 *            是否为删除。 因为删除会清空businessid表等数据
	 * @author kk
	 * @throws Exception
	 */
	private void clearData(SiteInst site, SqlSession sqlsession, boolean isDelete) throws Exception {
		BusinessidService_MB businessidService = null;
		AcPortInfoMapper acMapper = null;
		PwNniInfoMapper pwNniMapper = null;
		PortInstMapper portInstMapper = null;
		SlotInstMapper slotInstMapper = null;
		CardInstMapper cardInstMapper = null;
		EquipInstMapper equipInstMapper = null;
		QosQueueMapper qosQueueMapper = null;
		QosInfoMapper qosInfoMapper = null;
		QosRelevanceMapper qosRelevanceMapper = null;
		OSPFAREAInfoMapper oSPFAREAInfoMapper = null;
		E1InfoMapper e1InfoMapper = null;
		LabelInfoMapper labelInfoMapper = null;
		PortStmMapper portStmMapper = null;
		PortStmTimeslotMapper portStmTimeslotMapper = null;
		CurrentAlarmInfoMapper currentAlarmInfoMapper = null;
		HisAlarmInfoMapper hisAlarmInfoMapper = null;
		CCNMapper ccnMapper = null;
		OSPFInfoMapper oSPFInfoMapper = null;
		OspfRedistributeMapper ospfRedistributeMapper = null;
		OSPFInterfaceMapper oSPFInterfaceMapper = null;
		PortAttrMapper portAttrMapper = null;
		QosMappingModeMapper mappingModeMapper = null;
		QosMappingAttrMapper qosMappingAttrMapper = null;
		AcbufferMapper acbufferMapper = null;
		MCNMapper mCNMapper = null;
		Port2LayerAttrMapper port2LayeAttrMapper = null;

		AllConfigInfoMapper allConfigInfoMapper = null;
		FrequencyInfoMapper frequencyInfoMapper = null;
		WrappingProtectService_MB wrappingProtectService = null;
		LspInfoService_MB lspService = null;
		TunnelService_MB tunnelService = null;
		OamMepInfoMapper oamMepInfoMapper = null;
		PortLagInfoMapper portLagInfoMapper = null;
		SiteRoateMapper siteRoateMapper=null;
		SiteRoate siteRoate=null;
		PwInfoService_MB pwInfoService = null;
		ElineInfoService_MB elineService = null;
		EtreeInfoService_MB etreeService = null;
		SegmentService_MB segmentService = null;
		ElanInfoService_MB elanInfoService = null;
		CesInfoService_MB cesInfoService = null;
		PerformanceTaskInfoMapper performanceTaskMapper = null;//长期性能任务
		AclInstMapper aclInfoMapper = null;
		EthServiceInstMapper ethServiceMapper = null;
		BlackWhiteMacNameMapper blackAndWhiteMac = null;
		OamEthernetInfoMapper oamEthernetInfoMapper = null;
		CccInfoMapper cccInfoMapper=null;
		BfdInfoMapper bfdInfoMapper=null;
		StaticUnicastInfoMapper  staticUnicastInfoMapper=null;
		MacLearningLimitMapper macLearningLimitMapper=null;
		EthLoopMapper ethLoopMapper=null;
		BlackListMacMapper blackListMacMapper=null;
		L2cpInstMapper l2cpInstMapper=null;
		MacStudyAddressMapper macStudyAddressMapper=null;
		TimeSyncMapper timeSyncMapper=null;
		PtpPortMapper ptpPortMapper=null;
		OamMipInfoMapper oamMipInfoMapper=null;
		OamLinkInfoMapper oamLinkInfoMapper=null;
		ARPMapper arpMapper = null;
		OperationLogService_MB operationLogService = null;
		OSPFinfo_whMapper OSPFinfo_whMapper = null;
		try {

			businessidService = (BusinessidService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BUSINESSID, sqlsession);
			
			// 如果是删除网元时调用。网元默认数据清空
			if (isDelete) {
				// 删业务Id表

				businessidService.delete(site.getSite_Inst_Id());

				// 删除exp，phb映射表
				mappingModeMapper = sqlsession.getMapper(QosMappingModeMapper.class);
				qosMappingAttrMapper = sqlsession.getMapper(QosMappingAttrMapper.class);
				mappingModeMapper.deleteBySiteId(site.getSite_Inst_Id());
				qosMappingAttrMapper.deleteBySiteId(site.getSite_Inst_Id());

				// 删除槽
				slotInstMapper = sqlsession.getMapper(SlotInstMapper.class);
				slotInstMapper.deleteBySiteId(site.getSite_Inst_Id());

				// 机架
				equipInstMapper =sqlsession.getMapper(EquipInstMapper.class);
				equipInstMapper.deleteBySiteId(site.getSite_Inst_Id());

				//删除长期性能任务
				performanceTaskMapper=sqlsession.getMapper(PerformanceTaskInfoMapper.class);
				performanceTaskMapper.deleteBySiteId(site.getSite_Inst_Id());
				
				// 板卡
				cardInstMapper =sqlsession.getMapper(CardInstMapper.class);
				cardInstMapper.deleteBySiteId(site.getSite_Inst_Id());
				
				//删除风扇
				SmartFanMapper fanMapper =sqlsession.getMapper(SmartFanMapper.class);
				fanMapper.deleteBySiteId(site.getSite_Inst_Id());
				
				// 删除label
				labelInfoMapper =sqlsession.getMapper(LabelInfoMapper.class);
				labelInfoMapper.deleteBySite(site.getSite_Inst_Id());
				
				
				
			} else { // 如果是初始化网元调用，把对应的双边数据拆开后清空
				// 删除相应环网保护
				wrappingProtectService = (WrappingProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WRAPPINGPROTECT, sqlsession);
				wrappingProtectService.initializtionSite(site.getSite_Inst_Id());
				// 刪除ces
				cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo, sqlsession);
				cesInfoService.initializtionSite(site.getSite_Inst_Id());
				// 删除eline
				elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline, sqlsession);
				elineService.initializtionSite(site.getSite_Inst_Id());
				// 删除etree
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo, sqlsession);
				etreeService.initializtionSite(site.getSite_Inst_Id());
				// 删除elan
				elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo, sqlsession);
				elanInfoService.initializtionSite(site.getSite_Inst_Id());
				// 删除相应tunnel
				tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel, sqlsession);
				tunnelService.initializtionSite(site.getSite_Inst_Id());
				// 删除相应的lspinfo
				lspService = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO, sqlsession);
				lspService.initializtionSite(site.getSite_Inst_Id());
				// 删除segment
				segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT, sqlsession);
				segmentService.initializtionSite(site.getSite_Inst_Id());
				// 删除pw
				pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo, sqlsession);
				pwInfoService.initializtionSite(site.getSite_Inst_Id());
				businessidService.initializtionSite(site.getSite_Inst_Id());
				
			}
			
			// 删除AC
			acMapper =sqlsession.getMapper(AcPortInfoMapper.class);
			acMapper.deleteBySiteId(site.getSite_Inst_Id());
			acbufferMapper =sqlsession.getMapper(AcbufferMapper.class);
			acbufferMapper.deleteBySiteId(site.getSite_Inst_Id());


//			// 删除端口属性
			portAttrMapper =sqlsession.getMapper(PortAttrMapper.class);
			portAttrMapper.deleteBySite(site.getSite_Inst_Id());
			
			port2LayeAttrMapper = sqlsession.getMapper(Port2LayerAttrMapper.class);
			port2LayeAttrMapper.deleteBySiteId(site.getSite_Inst_Id());
//
//			// 删除端口
			portInstMapper =sqlsession.getMapper(PortInstMapper.class);
			portInstMapper.deleteBySiteId(site.getSite_Inst_Id());

			// 删除e1
			e1InfoMapper =sqlsession.getMapper(E1InfoMapper.class);
			e1InfoMapper.deleteBySite(site.getSite_Inst_Id());

			// qosQueue
			qosQueueMapper = sqlsession.getMapper(QosQueueMapper.class);
			qosQueueMapper.deleteBySiteId(site.getSite_Inst_Id());
			
			//qosInfo
			qosInfoMapper = sqlsession.getMapper(QosInfoMapper.class);
			qosInfoMapper.delete(site.getSite_Inst_Id());
			
			//qosRelevance
			qosRelevanceMapper =sqlsession.getMapper(QosRelevanceMapper.class);
			qosRelevanceMapper.delete(site.getSite_Inst_Id());

			// 删除ospfarea
			oSPFAREAInfoMapper =sqlsession.getMapper(OSPFAREAInfoMapper.class);
			oSPFAREAInfoMapper.deleteBySiteId(site.getSite_Inst_Id()+"");

			// 删除stm配置
			portStmMapper =sqlsession.getMapper(PortStmMapper.class);
			portStmMapper.deleteBySite(site.getSite_Inst_Id());

			// 删除stm时隙配置
			portStmTimeslotMapper =sqlsession.getMapper(PortStmTimeslotMapper.class);
			portStmTimeslotMapper.deleteBySite(site.getSite_Inst_Id());

			// 删除当前告警
			currentAlarmInfoMapper =sqlsession.getMapper(CurrentAlarmInfoMapper.class);
			currentAlarmInfoMapper.deleteBySiteId(site.getSite_Inst_Id());

			// 删除历史告警
			hisAlarmInfoMapper =sqlsession.getMapper(HisAlarmInfoMapper.class);
			hisAlarmInfoMapper.deleteBySiteId(site.getSite_Inst_Id());

			// 删除CCN
			ccnMapper =sqlsession.getMapper(CCNMapper.class);
			ccnMapper.deleteBySiteId(site.getSite_Inst_Id()+"");

			// 删除OSPF
			oSPFInfoMapper =sqlsession.getMapper(OSPFInfoMapper.class);
			oSPFInfoMapper.deleteBySiteId(site.getSite_Inst_Id()+"");

			// 删除重分发
			ospfRedistributeMapper =sqlsession.getMapper(OspfRedistributeMapper.class);
			ospfRedistributeMapper.deleteByNeId(site.getSite_Inst_Id());

			// 删除ospf接口
			oSPFInterfaceMapper = sqlsession.getMapper(OSPFInterfaceMapper.class);
			oSPFInterfaceMapper.deleteBySiteId(site.getSite_Inst_Id()+"");

			// 删除MCN
			mCNMapper =sqlsession.getMapper(MCNMapper.class);
			mCNMapper.deleteBySite(site.getSite_Inst_Id()+"");

			// 全局配置块
			allConfigInfoMapper = sqlSession.getMapper(AllConfigInfoMapper.class);
			allConfigInfoMapper.delete(site.getSite_Inst_Id());

			// 删除时钟I表
			frequencyInfoMapper = sqlSession.getMapper(FrequencyInfoMapper.class);
			frequencyInfoMapper.delete(site.getSite_Inst_Id());

			// 删除相应oam
			oamMepInfoMapper = sqlSession.getMapper(OamMepInfoMapper.class);
			oamMepInfoMapper.deleteBySiteId(site.getSite_Inst_Id());

			// 删除lag
			portLagInfoMapper = sqlsession.getMapper(PortLagInfoMapper.class);
			portLagInfoMapper.deleteBysiteId(site.getSite_Inst_Id());

			// 删除保护倒换
			siteRoateMapper = sqlsession.getMapper(SiteRoateMapper.class);
			siteRoate=new SiteRoate();
			siteRoate.setSiteId(site.getSite_Inst_Id());
			siteRoateMapper.delete(siteRoate);

			// 删除pwnnibufer
			pwNniMapper =sqlsession.getMapper(PwNniInfoMapper.class);
			pwNniMapper.deleteBySiteId(site.getSite_Inst_Id());
			
			//删除ACL
			aclInfoMapper = sqlsession.getMapper(AclInstMapper.class);
			aclInfoMapper.deleteBySiteId(site.getSite_Inst_Id());
			
			//删除以太网业务配置块
			ethServiceMapper =sqlsession.getMapper(EthServiceInstMapper.class);
			ethServiceMapper.deleteAllBySite(site.getSite_Inst_Id());
		    //删除黑白名单
			blackAndWhiteMac =sqlsession.getMapper(BlackWhiteMacNameMapper.class);
			blackAndWhiteMac.deleteBySiteId(site.getSite_Inst_Id());
			//删除以太网OAM
			oamEthernetInfoMapper =sqlsession.getMapper(OamEthernetInfoMapper.class);
			oamEthernetInfoMapper.deleteBySiteId(site.getSite_Inst_Id());
			//删除CCC
			cccInfoMapper =sqlsession.getMapper(CccInfoMapper.class);
			cccInfoMapper.deleteBySiteId(site.getSite_Inst_Id());
			//删除BFD
			bfdInfoMapper =sqlsession.getMapper(BfdInfoMapper.class);
			bfdInfoMapper.deleteBySiteId(site.getSite_Inst_Id());
			//删除静态单播
			staticUnicastInfoMapper=sqlsession.getMapper(StaticUnicastInfoMapper.class);
			staticUnicastInfoMapper.deleteBySiteId(site.getSite_Inst_Id());
			//MAC学习数目限制
			macLearningLimitMapper=sqlsession.getMapper(MacLearningLimitMapper.class);
			macLearningLimitMapper.deleteBySiteId(site.getSite_Inst_Id());
			//删除以太网环回
			ethLoopMapper=sqlsession.getMapper(EthLoopMapper.class);
			ethLoopMapper.deleteBySiteId(site.getSite_Inst_Id());
			//删除MAC的黑白名单地址
			blackListMacMapper=sqlsession.getMapper(BlackListMacMapper.class);
			blackListMacMapper.deleteBySiteId(site.getSite_Inst_Id());
			//删除L2CP
			l2cpInstMapper=sqlsession.getMapper(L2cpInstMapper.class);
			l2cpInstMapper.delete(site.getSite_Inst_Id());
			//删除二层MAC
			macStudyAddressMapper=sqlsession.getMapper(MacStudyAddressMapper.class);
			macStudyAddressMapper.deleteBySiteId(site.getSite_Inst_Id());
			//删除时间同步
			timeSyncMapper=sqlsession.getMapper(TimeSyncMapper.class);
			ptpPortMapper=sqlsession.getMapper(PtpPortMapper.class);
			timeSyncMapper.deleteBySiteId(site.getSite_Inst_Id());
			ptpPortMapper.deletePtpBySiteId(site.getSite_Inst_Id());
			//删除OAM mip
			oamMipInfoMapper=sqlsession.getMapper(OamMipInfoMapper.class);
			oamMipInfoMapper.deleteBySiteId(site.getSite_Inst_Id());
			//删除OAM Link
			oamLinkInfoMapper=sqlsession.getMapper(OamLinkInfoMapper.class);
			oamLinkInfoMapper.deleteBySiteId(site.getSite_Inst_Id());
			//删除arp
			arpMapper = sqlsession.getMapper(ARPMapper.class);
			arpMapper.deleteBySiteId(site.getSite_Inst_Id());
			//删除用户操作日志
			operationLogService = (OperationLogService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OPERATIONLOGSERVIECE, sqlsession);
			operationLogService.deleteBySiteId(site.getSite_Inst_Id());
			
			//网元初始化时只需清除板卡和端口的数据 
			// 先将所属网元的板卡删除
			if(!isDelete){
				//不删除风扇数据,还原成默认值
				this.insertOrResetFan(site, 1);
				// 不删除label,还原成默认值
				labelInfoMapper =sqlsession.getMapper(LabelInfoMapper.class);
				labelInfoMapper.updateLabelStatus(site.getSite_Inst_Id());
				
				List<CardInst> cardList = new ArrayList<CardInst>();
				CardService_MB cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD,sqlsession);
				SlotService_MB  slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT, sqlsession);
				EquimentDataUtil equimentDataUtil = new EquimentDataUtil();
				CardInst cardinst = new CardInst();
				cardinst.setSiteId(site.getSite_Inst_Id());
				cardList = cardService.select(cardinst);
				List<SlotInst> slotInstList =new ArrayList<SlotInst>();
				List<CardXml> cardXmlList = new ArrayList<CardXml>();
				SlotInst slotInst = new SlotInst();
				slotInst.setSiteId(site.getSite_Inst_Id());
				slotInstList = slotService.select(slotInst);

				for(CardInst cardInfo : cardList){
					   cardService.delete(cardInfo.getId());
					   cardInfo.setId(0);
					   back: for(SlotInst slot : slotInstList){
						    cardXmlList = equimentDataUtil.getCardMenu(slot.getSlotType());
						    for(CardXml cardXml : cardXmlList){
						    	if(cardInfo.getCardName().equals(cardXml.getName())){
						    		cardInfo.setSlotInst(slot);
						    		cardService.saveOrUpdate(equimentDataUtil.setCard(cardInfo,cardXml.getXmlPath()));
						    		break back;
						    }
						}
					}
				}
			}
			
			OSPFinfo_whMapper = sqlsession.getMapper(OSPFinfo_whMapper.class);
			OSPFinfo_whMapper.deleteBySiteId(site.getSite_Inst_Id());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			throw e;
		} finally {
			acMapper = null;
			pwNniMapper = null;
			portInstMapper = null;
			slotInstMapper = null;
			cardInstMapper = null;
			equipInstMapper = null;
			qosQueueMapper = null;
			qosInfoMapper = null;
			qosRelevanceMapper = null;
			oSPFAREAInfoMapper = null;
			e1InfoMapper = null;
			labelInfoMapper = null;
			portStmMapper = null;
			portStmTimeslotMapper = null;
			currentAlarmInfoMapper = null;
			hisAlarmInfoMapper = null;
			ccnMapper = null;
			oSPFInfoMapper = null;
			ospfRedistributeMapper = null;
			oSPFInterfaceMapper = null;
			portAttrMapper = null;
			mappingModeMapper = null;
			qosMappingAttrMapper = null;
			acbufferMapper = null;
			mCNMapper = null;
			port2LayeAttrMapper = null;
			allConfigInfoMapper = null;
			frequencyInfoMapper = null;
			oamMepInfoMapper = null;
			portLagInfoMapper = null;
			siteRoateMapper=null;
			siteRoate=null;
			performanceTaskMapper = null;//长期性能任务
			aclInfoMapper = null;
			ethServiceMapper = null;
			blackAndWhiteMac = null;
			oamEthernetInfoMapper = null;
			cccInfoMapper=null;
			bfdInfoMapper=null;
			staticUnicastInfoMapper=null;
			macLearningLimitMapper=null;
			ethLoopMapper=null;
			blackListMacMapper=null;
		    l2cpInstMapper=null;
		    macStudyAddressMapper=null;
		    ptpPortMapper=null;
		    timeSyncMapper=null;
		    oamMipInfoMapper=null;
		    oamLinkInfoMapper=null;
		    OSPFinfo_whMapper = null;
		}
	}
	
	
	
	/**
	 * 根据登陆状态查询
	 * 
	 * @param loginStatus
	 * @return
	 * @throws Exception
	 */
	public List<SiteInst> selectByLogin(int loginStatus) throws Exception {
		return this.siteInstMapper.queryByLogin(loginStatus);
	}
	
	
	/**
	 * 通过域id查询该域所有网元，包含子网下的网元
	 * 
	 * @param fieldId
	 * @return
	 */
	public List<SiteInst> selectByFieldId(SiteInst siteInst) {
		List<SiteInst> siteInstList = null;
		Field field = new Field();
		field.setId(siteInst.getFieldID());
		FieldMapper fieldMapper = null;
		SiteInst subNetSiteInst = null;
		List<SiteInst> subNetSiteInstList = null;
		try {
			// 查询域下所有网元
			siteInstList = new ArrayList<SiteInst>();
			siteInstList = this.siteInstMapper.queryByCondition(siteInst);//
			// 查询域下所有子网
			List<Field> subnetList = new ArrayList<Field>();
			fieldMapper = this.sqlSession.getMapper(FieldMapper.class);
			subnetList = fieldMapper.querySiteByCondition(field);
			if (subnetList.size() > 0) {
				// 遍历所有子网下的所有网元
				for (Field field1 : subnetList) {
					subNetSiteInst = new SiteInst();
					subNetSiteInst.setFieldID(field1.getId());
					subNetSiteInstList = new ArrayList<SiteInst>();
					subNetSiteInstList = this.select(subNetSiteInst);
					if (subNetSiteInstList.size() > 0) {
						for (SiteInst inst : subNetSiteInstList) {
							siteInstList.add(inst);
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
//			UiUtil.closeService(siteService);
			 field = null;
			 fieldMapper = null;
			 subNetSiteInst = null;
			 subNetSiteInstList = null;
		}
		return siteInstList;
	}


	
	
	/**
	 * 通过设备上报的ne地址查询网元
	 * 
	 * @param siteInst
	 * @return
	 */
	public SiteInst selectByNeAddress(SiteInst siteInst) {
		List<SiteInst> siteInsts = null;
		SiteInst inst = null;
		try {
			siteInsts = new ArrayList<SiteInst>();
			siteInsts = this.siteInstMapper.queryByNeaddress(siteInst);
			if (siteInsts != null && siteInsts.size() > 0) {
				inst = siteInsts.get(0);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return inst;
	}
	
	
	
	/**
	 * 根据网元集合对象获取网元ID集合
	 * 
	 * @param siteList
	 *            网元对象集合
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getSiteIdList(List<SiteInst> siteList) throws Exception {
		List<Integer> siteIdList = null;
		try {
			siteIdList = new ArrayList<Integer>();

			for (SiteInst siteInst : siteList) {
				if (!siteIdList.contains(siteInst.getSite_Inst_Id())) {
					siteIdList.add(siteInst.getSite_Inst_Id());
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return siteIdList;
	}
	
	
	/**
	 * 查询网元类型
	 * 
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public String getSiteType(int siteId) throws Exception {
		SiteInst siteInst = null;
		String result = null;
		try {
			siteInst = this.select(siteId);
			if (null != siteInst) {
				result = siteInst.getCellType();
			} else {
				result = "";
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return result;
	}
	
	/**
	 * 查询网元ID
	 * 
	 * @param siteId
	 * @return
	 * @throws Exception
	 */
	public String getSiteID(int siteId) throws Exception {
		SiteInst siteInst = null;
		String result = null;
		try {
			siteInst = this.select(siteId);
			if (null != siteInst) {
				result = siteInst.getCellDescribe();
			} else {
				result = "";
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return result;
	}
	
	/**
	 * 获取网元名称
	 * @param siteInstId
	 * @return
	 */
	public String getSiteName(int siteInstId){
		String siteName ="";
		SiteInst siteInst = siteInstMapper.selectByPrimaryKey(siteInstId);
		if(siteInst != null && siteInst.getSite_Inst_Id()>0){
			siteName = siteInst.getCellId();
		}
		return siteName;
	}
			
	
	/**
	 * 新建qos模板
	 * 
	 * @param xmlPath
	 * @param slotInst
	 * @throws Exception
	 */
	private QosMappingMode addPHBModel(String xmlPath, String type, int siteId) throws Exception {

		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document doc = null;
		org.w3c.dom.Element root = null;
		NodeList nodeList = null;
		QosMappingMode qosMappingMode = null;
		org.w3c.dom.Element parent = null;

		try {

			factory = DocumentBuilderFactory.newInstance();
			// 使用DocumentBuilderFactory构建DocumentBulider
			builder = factory.newDocumentBuilder();
			// 使用DocumentBuilder的parse()方法解析文件
			doc = builder.parse(SiteService_MB.class.getClassLoader().getResource(xmlPath).toString());
			root = doc.getDocumentElement();
			nodeList = root.getElementsByTagName(type);
			if (nodeList.getLength() == 1) {
				parent = (org.w3c.dom.Element) nodeList.item(0);
				qosMappingMode = new QosMappingMode();
				qosMappingMode.setSiteId(siteId);
				qosMappingMode.setTypeName(parent.getAttribute("typeName"));
				qosMappingMode.setType(Integer.parseInt(parent.getAttribute("type")));
				qosMappingMode.setName(parent.getAttribute("tName"));
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			factory = null;
			builder = null;
			doc = null;
			root = null;
			nodeList = null;
			parent = null;
		}
		return qosMappingMode;
	}
	
	/**
	 * 新建qos模板
	 * 
	 * @param xmlPath
	 * @param slotInst
	 * @throws Exception
	 */
	private List<QosMappingAttr> addPHBTable(String xmlPath, String type, int siteId) throws Exception {

		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document doc = null;
		org.w3c.dom.Element root = null;
		NodeList nodeList = null;
		QosMappingAttr qosMappingAttr = null;
		org.w3c.dom.Element parent = null;
		NodeList childList = null;
		org.w3c.dom.Element child = null;
		List<QosMappingAttr> qosMappingAttrList = null;

		try {
			qosMappingAttrList = new ArrayList<QosMappingAttr>();
			factory = DocumentBuilderFactory.newInstance();
			// 使用DocumentBuilderFactory构建DocumentBulider
			builder = factory.newDocumentBuilder();
			// 使用DocumentBuilder的parse()方法解析文件
			doc = builder.parse(SiteService_MB.class.getClassLoader().getResource(xmlPath).toString());
			root = doc.getDocumentElement();
			nodeList = root.getElementsByTagName(type);
			if (nodeList.getLength() == 1) {
				parent = (org.w3c.dom.Element) nodeList.item(0);
				childList = parent.getElementsByTagName("table");
				if (childList.getLength() > 0) {
					for (int j = 0; j < childList.getLength(); j++) {
						child = (org.w3c.dom.Element) childList.item(j);
						qosMappingAttr = new QosMappingAttr();
						qosMappingAttr.setSiteId(siteId);
						qosMappingAttr.setGrade(child.getAttribute("grade"));
						qosMappingAttr.setName(child.getAttribute("name"));
						qosMappingAttr.setValue(Integer.parseInt(child.getAttribute("value")));
						qosMappingAttrList.add(qosMappingAttr);
					}
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			factory = null;
			builder = null;
			doc = null;
			root = null;
			nodeList = null;
			parent = null;
			childList = null;
			child = null;
		}
		return qosMappingAttrList;
	}
	

	/**
	 * 根据网元ID获取网元厂商
	 * 
	 * @param siteId
	 *            网元主键
	 * @return 厂商
	 * @throws Exception
	 */
	public int getManufacturer(int siteId) throws Exception {
		int manufacturer = 0;
		SiteInst siteInst = null;
		try {
			siteInst = this.select(siteId);
			if (siteInst == null) {
				throw new Exception("根据ID查询网元出错");
			}
			if(siteInst.getCellEditon() != null){
				manufacturer = Integer.parseInt(UiUtil.getCodeById(Integer.parseInt(siteInst.getCellEditon())).getCodeValue());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			siteInst = null;
		}
		return manufacturer;
	}
	
	/**
	 * 通过M网元ip，查找该M下，所有网元
	 * @param managerIp
	 * @return
	 */
	public List<SiteInst> querySitesByIp(String managerIp){
		List<SiteInst> siteInsts = null;
		SiteInst siteInst = new SiteInst();
		siteInst.setCellDescribe(managerIp);
		FieldMapper fieldMapper =null;
		Field field = new Field();
        List<Field> fieldList = null;
		List<SiteInst> mSiteInst = null;
		try {
			mSiteInst = new ArrayList<SiteInst>();
			mSiteInst = this.siteInstMapper.queryByCondition(siteInst);
			if(null != mSiteInst && !mSiteInst.isEmpty())
			{
				siteInst = mSiteInst.get(0);
				field.setmSiteId(siteInst.getSite_Inst_Id());
				fieldList = new ArrayList<Field>();
				fieldMapper = this.sqlSession.getMapper(FieldMapper.class);
				fieldList = fieldMapper.queryByConditions(field);
				if(null != fieldList && !fieldList.isEmpty()){
					field = fieldList.get(0);
					siteInst = new SiteInst();
					siteInst.setFieldID(field.getId());
					siteInsts = this.selectByFieldId(siteInst);
				}else{
					siteInsts = new ArrayList<SiteInst>();
				}
			}else{
				siteInsts = new ArrayList<SiteInst>();
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally
		{
			 fieldList = null;
			 mSiteInst = null;
			 fieldMapper = null;
		}
		return siteInsts;
	}
	
	
	
	/**
	 * 查询武汉所有在线网元
	 * @param siteInst
	 * @return
	 */
	public List<SiteInst> selectSiteInsts(){
		List<SiteInst> siteInsts = null;
		try {
			siteInsts = new ArrayList<SiteInst>();
			siteInsts = this.siteInstMapper.queryWHByCondition();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return siteInsts;
	}
	
	public List<SiteInst> selectBySn(String sn){
		List<SiteInst> siteInsts = null;
		try {
			siteInsts = new ArrayList<SiteInst>();
			siteInsts = this.siteInstMapper.queryBySn(sn);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return siteInsts;
	}
	
	/**
	 * 查询所有M网元
	 * @return
	 */
	public List<SiteInst> selectByISmsite(){
		List<SiteInst> siteInsts = null;
		try {
			siteInsts = new ArrayList<SiteInst>();
			siteInsts = this.siteInstMapper.queryByIsMsite();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return siteInsts;
	}
	
	/**
	 * 查询域下所有的网元
	 * @return
	 */
	public List<SiteInst> selectByFileId(int fileId){
		List<SiteInst> siteInsts = null;
		FieldService_MB fieldService = null;
		List<Integer> fieldList = null;
		try {
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field, this.sqlSession);
			fieldList = new ArrayList<Integer>();
			fieldList = fieldService.selectByFieldIds(fileId);
			if(null != fieldList && !fieldList.isEmpty())
			{
				siteInsts = new ArrayList<SiteInst>();
				siteInsts = this.siteInstMapper.queryByFields(fieldList);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally
		{
			fieldList = null;
		}
		return siteInsts;
	}
	
	public static void main(String[] args) {
		
	}
	
}
