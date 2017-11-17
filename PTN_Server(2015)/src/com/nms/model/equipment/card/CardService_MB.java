package com.nms.model.equipment.card;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.bean.equipment.port.Port2LayerAttr;
import com.nms.db.bean.equipment.port.PortAttr;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortStm;
import com.nms.db.bean.equipment.port.PortStmTimeslot;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.dao.equipment.card.CardInstMapper;
import com.nms.db.dao.equipment.port.E1InfoMapper;
import com.nms.db.dao.equipment.port.Port2LayerAttrMapper;
import com.nms.db.dao.equipment.port.PortAttrMapper;
import com.nms.db.dao.equipment.port.PortInstMapper;
import com.nms.db.dao.equipment.port.PortStmMapper;
import com.nms.db.dao.equipment.port.PortStmTimeslotMapper;
import com.nms.db.dao.equipment.shelf.SiteInstMapper;
import com.nms.db.dao.equipment.slot.SlotInstMapper;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EJobStatus;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;

public class CardService_MB extends ObjectService_Mybatis {
	private CardInstMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(CardInstMapper mapper) {
		this.mapper = mapper;
	}
	
	private static final int LAST_MAX = 5;
	private static final int MIDDLE_MAX = 7;
	
	/**
	 * 根据条件查询
	 * 
	 * @param cardinst
	 *            查询条件
	 * @return List<CardInst> 集合
	 * @throws Exception
	 */
	public List<CardInst> select(CardInst cardinst) throws Exception {
		List<CardInst> cardinstList = null;
		PortInst portinst = null;
		PortService_MB portService = null;
		List<PortInst> portinstList = null;
		try {
			cardinstList = new ArrayList<CardInst>();
			cardinstList = this.mapper.queryByCondition(cardinst);
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT, this.sqlSession);
			if (null != cardinstList && cardinstList.size() != 0) {
				for (int i = 0; i < cardinstList.size(); i++) {
					portinst = new PortInst();
					portinst.setCardId(cardinstList.get(i).getId());
					portinstList = portService.select(portinst);
					cardinstList.get(i).setPortList(portinstList);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			cardinst = null;
			portinst = null;
//			UiUtil.closeService(portService);
			portinstList = null;
		}

		return cardinstList;
	}

	/**
	 * 新增或修改cardinst对象，通过cardinst.getCard_Inst_Id()来判断是修改还是新增
	 * 
	 * @param cardinst
	 *            实体
	 * @throws Exception
	 */
	public void saveOrUpdate(CardInst cardinst) throws Exception {

		if (cardinst == null) {
			throw new Exception("cardinst is null");
		}

		PortInst portinst = null;
		PortInstMapper portInstMapper = null;
		List<PortInst> portinstlist = null;
		SlotInst slotInst = null;
		SlotInstMapper slotInstMapper = null;
		List<SlotInst> slotList = null;
		PortStmMapper portStmMapper = null;
		PortStmTimeslotMapper portStmTimeslotMapper = null;
		E1InfoMapper e1Mapper = null;
		int legId = 1;
		int cardId = 0;
		SiteInstMapper siteInstMapper = null;
		SiteInst site = null;
		List<SiteInst> siteList = null;
		CardInstMapper cardInstMapper =null;
		try {			
			this.sqlSession.getConnection().setAutoCommit(false);
			cardInstMapper= this.sqlSession.getMapper(CardInstMapper.class);
			portStmMapper = this.sqlSession.getMapper(PortStmMapper.class);
			portStmTimeslotMapper =this.sqlSession.getMapper(PortStmTimeslotMapper.class);
			e1Mapper =this.sqlSession.getMapper(E1InfoMapper.class);
			if (cardinst.getId() == 0) {
				this.mapper.insert(cardinst);
				cardId =cardinst.getId();
				portinstlist = cardinst.getPortList();
				portInstMapper = this.sqlSession.getMapper(PortInstMapper.class);
				Port2LayerAttrMapper port2LayerMapper =this.sqlSession.getMapper(Port2LayerAttrMapper.class);
				siteInstMapper = this.sqlSession.getMapper(SiteInstMapper.class);
				site = new SiteInst();
				site.setSite_Inst_Id(cardinst.getSiteId());
				siteList = siteInstMapper.queryByCondition(site);
				if(siteList != null && siteList.size() == 1){
					if(siteList.get(0).getManufacturer() == 0){
						for (int i = 0; i < portinstlist.size(); i++) {
							portinstlist.get(i).setIsEnabledLaser(1);
							if(portinstlist.get(i).getNumber()>0 && !portinstlist.get(i).getPortName().contains("e1")){
								portinstlist.get(i).setIsEnabled_code(1);
							}
						}
					}
				}
				
				for (int i = 0; i < portinstlist.size(); i++) {
					portinst = portinstlist.get(i);
					portinst.setCardId(cardId);
					portinst.setJobStatus(EJobStatus.TSF.getValue()+"");
					portInstMapper.insert(portinst);
					int portId = portinst.getPortId();
					if("NONE".equals(portinst.getPortType())){
						//插入portattr属性
						PortAttr portAttr=new PortAttr();
						portAttr.setPortId(portId);
						portAttr.setSiteId(portinst.getSiteId());
						portAttr.setWorkModel(70);
						portAttr.setFluidControl(27);
						portAttr.getPortUniAttr().setBroadcast(1000000+"");
						portAttr.getPortUniAttr().setIsBroadcast(85);
						portAttr.getPortUniAttr().setMulticast(1000000+"");
						portAttr.getPortUniAttr().setIsMulticast(85);
						portAttr.getPortUniAttr().setUnicast(1000000+"");
						portAttr.getPortUniAttr().setIsUnicast(85);
						portAttr.getPortUniAttr().setVlanRelevance(50);
						portAttr.getPortUniAttr().setEightIpRelevance(50);
						portAttr.getPortUniAttr().setSourceMacRelevance(50);
						portAttr.getPortUniAttr().setDestinationMacRelevance(50);
						portAttr.getPortUniAttr().setSourceIpRelevance(50);
						portAttr.getPortUniAttr().setDestinationIpRelevance(50);
						portAttr.getPortUniAttr().setPwRelevance(50);
						portAttr.getPortUniAttr().setDscpRelevance(50);
						portAttr.getPortUniAttr().setSourceTcpPortRelevance(0);
						portAttr.getPortUniAttr().setEndTcpPortRelevance(0);
						portAttr.getPortUniAttr().setTosRelevance(0);
						PortAttrMapper portAttrMapper=this.sqlSession.getMapper(PortAttrMapper.class);
						portAttrMapper.insert(portAttr);
						
						//插入端口2层属性
						Port2LayerAttr attr = new Port2LayerAttr();
						attr.setSiteId(portinst.getSiteId());
						attr.setPortId(portId);
						attr.setPortNumber(portinst.getNumber());
						attr.setMacEnable(1);
						attr.setMacCount(512);
						attr.setTpIdType(0);
						attr.setVlanCount(0);
						attr.setPortType(1);
						attr.setPvid(1);
						attr.setQinqEnable(0);
						attr.setQinqModel(0);
						attr.setVlans("");
						attr.setQinqs("");
						if (attr.getId() == 0) {
							port2LayerMapper.insert(attr);
						}else{
							port2LayerMapper.update(attr);
						}
						
					}
					
					//插入stm1
					if (portinst.getPortType().equals("STM1")) {
						PortStm portStm = new PortStm();
						portStm.setPortid(portId);
						portStm.setSiteid(portinst.getSiteId());
						portStmMapper.insert(portStm);
						int portstmId = portStm.getPortid();
						int last = 3;
						int middle = 1;
						int front = 1;
						for (int j = 1; j <= 63; j++) {
							PortStmTimeslot portStmTimeslot = new PortStmTimeslot();
							portStmTimeslot.setPortid(portId);
							portStmTimeslot.setPortstmid(portstmId);
							portStmTimeslot.setManagerStatus(0);
							portStmTimeslot.setTimeslotnumber(portinst.getPortName() + "/" + j);
							portStmTimeslot.setSiteId(portinst.getSiteId());

							if (last > LAST_MAX) {
								last = 3;
								middle++;
								if (middle > MIDDLE_MAX) {
									middle = 1;
									front++;
								}
							}

							portStmTimeslot.setTimeslotname("1.0." + front + "." + middle + "." + last);
							portStmTimeslotMapper.insert(portStmTimeslot);
							last++;
						}

					} else if (portinst.getPortType().equals("e1")) {
						E1Info e1Info = new E1Info();
						e1Info.setCardId(cardId);
						e1Info.setSiteId(portinst.getSiteId());
						e1Info.setPortId(portId);
						e1Info.setPortName(portinst.getPortName());
						e1Info.setLegId(legId);
						e1Mapper.insert(e1Info);
						legId++;
					}
					// if (portinst.getChildPortList() != null && portinst.getChildPortList().size() > 0) {
					// for (PortInst childPort : portinst.getChildPortList()) {
					// childPort.setCardId(cardId);
					// childPort.setParentId(portId);
					// }
					// }
				}

				slotInst = new SlotInst();
				slotInst.setId(cardinst.getSlotId());

				slotInstMapper =this.sqlSession.getMapper(SlotInstMapper.class);
				slotList = slotInstMapper.queryByCondition(slotInst);

				if (slotList != null && slotList.size() != 0) {
					slotInst = slotList.get(0);
					slotInst.setCardId(cardId);
					slotInstMapper.update(slotInst);
				} else {
					throw new Exception("关联SLOT失败");
				}
				
				//离线数据下载
				super.dateDownLoad(cardinst.getSiteId(),cardId,EServiceType.CARD.getValue(),EActionType.INSERT.getValue() ,cardinst.getSlotInst().getNumber()+"",null,0,0,null);
			} else {
				cardInstMapper.update(cardinst);
				//离线数据下载
				super.dateDownLoad(cardinst.getSiteId(),cardinst.getId(),EServiceType.CARD.getValue(),EActionType.UPDATE.getValue() ,cardinst.getSlotInst().getNumber()+"",null,0,0,null);
			}
			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.commit();
			}
		} catch (Exception e) {
			this.sqlSession.rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
			portinst = null;
			portInstMapper = null;
			portinstlist = null;
			slotInst = null;
			slotInstMapper = null;
			slotList = null;
			portStmMapper = null;
			e1Mapper = null;
			siteInstMapper = null;
			site = null;
			siteList = null;
		}
	}
	
	public List<Integer> select(CardInst cardinst, SiteInst siteInst) throws Exception{
		List<Integer> card=null;
		try {
		     card = (List<Integer>) new ArrayList<Integer>();
		     if(cardinst.getCardName().equals("E1支路卡")){
			     card = this.mapper.querryBySiteIdAndType(siteInst.getSite_Inst_Id());	
             }else{
        	    card = this.mapper.querrysBySiteIdAndType(siteInst.getSite_Inst_Id());	         	
             }		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return card;
	}
		
	/**
	 * 根据网元id查询所有板卡，无需关联其他表
	 * @param siteId
	 * @return
	 */
	public List<CardInst> selectBySiteId(int siteId){
		List<CardInst> cardInsts = null;
		CardInst cardinst = new CardInst();
		cardinst.setSiteId(siteId);
		try {
			cardInsts = new ArrayList<CardInst>();
			cardInsts = this.mapper.queryByCondition(cardinst);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return cardInsts;
	}
	
	/**
	 * 查询全部
	 * 
	 * @return List<CardInst>集合
	 * @throws Exception
	 */
	public List<CardInst> select() throws Exception {
		CardInst cardinst = null;
		List<CardInst> cardinstList = null;
		PortInst portinst = null;
		List<PortInst> portinstList = null;
		PortService_MB portService = null;
		try {
			cardinst = new CardInst();
			cardinstList = mapper.queryByCondition(cardinst);
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT, this.sqlSession);
			if (null != cardinstList && cardinstList.size() != 0) {
				for (int i = 0; i < cardinstList.size(); i++) {
					portinst = new PortInst();
					portinst.setCardId(cardinstList.get(i).getId());

					portinstList = portService.select(portinst);
					cardinstList.get(i).setPortList(portinstList);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			cardinst = null;
			portinst = null;
			portinstList = null;
//			UiUtil.closeService(portService);
		}
		return cardinstList;
	}
	
	/**
	 * 根据主键删除
	 * 
	 * @param id
	 *            主键
	 * @return 删除的记录数
	 * @throws Exception
	 */
	public int delete(int id) throws Exception {

		int result = 0;

		try {
			result = this.mapper.delete(id);
			this.sqlSession.commit();
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;

	}

	
	/**
	 * 删除card port 修改slot的cardID为0
	 * 
	 * @param cardInst
	 *            card对象
	 * @throws Exception
	 */
	public void delete(CardInst cardInst) throws Exception {

		int result = 0;
		PortInstMapper portInstMapper = null;
		PortInst portInst = null;
		SlotInstMapper slotInstMapper = null;
		SlotInst slotInst = null;
		E1InfoMapper e1Mapper = null;
		E1Info e1Info = new E1Info();
		PortAttrMapper portAttrMapper=null;
		Port2LayerAttrMapper port2LayerMapper = null;
		try {
			this.sqlSession.getConnection().setAutoCommit(false);

			slotInstMapper = this.sqlSession.getMapper(SlotInstMapper.class);
			slotInst = cardInst.getSlotInst();
			slotInst.setCardId(0);
			slotInstMapper.update(slotInst);

			portInstMapper = this.sqlSession.getMapper(PortInstMapper.class);
			portInst = new PortInst();
			portInst.setCardId(cardInst.getId());
			
			List<PortInst> portInsts = portInstMapper.queryByCondition(portInst);
			portAttrMapper = this.sqlSession.getMapper(PortAttrMapper.class);
			port2LayerMapper = this.sqlSession.getMapper(Port2LayerAttrMapper.class);
			if(portInsts!=null){
				for(PortInst inst : portInsts){
					portAttrMapper.deleteByPortId(inst.getPortId());
					port2LayerMapper.deleteByPortId(inst.getPortId());
				}
			}
			//必须先把port上的属性删掉才能删port
			portInstMapper.delete(portInst);
			
			e1Mapper = this.sqlSession.getMapper(E1InfoMapper.class);
			e1Info.setCardId(cardInst.getId());
			e1Mapper.delete(e1Info);
			result = this.mapper.delete(cardInst.getId());
			
			//离线数据下载
			super.dateDownLoad(cardInst.getSiteId(),cardInst.getId(),EServiceType.CARD.getValue(),EActionType.DELETE.getValue() ,slotInst.getNumber()+"",null,0,0,null);

			if(!this.sqlSession.getConnection().getAutoCommit()){
				this.sqlSession.getConnection().commit();
			}
		} catch (Exception e) {
			this.sqlSession.getConnection().rollback();
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			this.sqlSession.getConnection().setAutoCommit(true);
			slotInst = null;
			portInstMapper = null;
			portInst = null;
			slotInstMapper = null;
			slotInst = null;
			e1Mapper = null;			
			portAttrMapper=null;
			port2LayerMapper = null;
		}
	}
	
	public List<CardInst> select_north(){
		return this.mapper.select_north();
	}
}
