package com.nms.model.equipment.shlef;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.shelf.EquipInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.dao.equipment.shelf.EquipInstMapper;
import com.nms.db.dao.equipment.slot.SlotInstMapper;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;

public class EquipInstService_MB extends ObjectService_Mybatis {
	private EquipInstMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(EquipInstMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 查询全部
	 * 
	 * @return List<EquipInst> 集合
	 * @throws Exception
	 */
	public List<EquipInst> select() throws Exception {
		EquipInst equipInst = null;
		List<EquipInst> equipinstList = null;
		SlotInst slotinst = null;
		SlotInstMapper	slotInstMapper  = null;
		List<SlotInst> slotinstList = null;
		try {
			equipInst = new EquipInst();
			equipinstList = new ArrayList<EquipInst>();
			equipinstList = this.mapper.queryByCondition(equipInst);
			if (null != equipinstList && equipinstList.size() != 0) {
				for (int i = 0; i < equipinstList.size(); i++) {
					slotinst = new SlotInst();
					slotInstMapper =this.sqlSession.getMapper(SlotInstMapper.class);
					slotinst.setEquipId(equipinstList.get(i).getId());
					slotinstList = new ArrayList<SlotInst>();
					slotinstList = slotInstMapper.queryByCondition(slotinst);
					equipinstList.get(i).setSlotlist(slotinstList);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			equipInst  = null;
			slotinst = null;
			slotInstMapper = null;
			slotinstList = null;
		}
	
		return equipinstList;
	}
	
	/**
	 * 根据条件查询
	 * 
	 * @param portattr
	 * @return
	 * @throws Exception
	 */
	public List<EquipInst> select(EquipInst equipinst) throws Exception {
		List<EquipInst> equipinstList = null;
		SlotInst slotinst = null;
		List<SlotInst> slotinstList  = null;
		SlotService_MB slotService = null;
		try {
			equipinstList = new ArrayList<EquipInst>();
			equipinstList = this.mapper.queryByCondition(equipinst);
			if (null != equipinstList && equipinstList.size() != 0) {
				slotService=(SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT, this.sqlSession);
				for (int i = 0; i < equipinstList.size(); i++) {
					slotinst = new SlotInst();
					slotinst.setEquipId(equipinstList.get(i).getId());
					slotinstList = slotService.select(slotinst);
					equipinstList.get(i).setSlotlist(slotinstList);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally{
			slotinst = null;
			slotinstList = null;
//			UiUtil.closeService(slotService);
		}
	
		return equipinstList;
	}
}
