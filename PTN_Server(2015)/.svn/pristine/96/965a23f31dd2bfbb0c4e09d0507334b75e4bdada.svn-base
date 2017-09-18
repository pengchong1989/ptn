package com.nms.model.equipment.slot;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.dao.equipment.slot.SlotInstMapper;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;

public class SlotService_MB extends ObjectService_Mybatis {
	private SlotInstMapper mapper = null;
	
	public void setPtnuser(String ptnuser) {
		super.ptnuser = ptnuser;
	}

	public void setSqlSession(SqlSession sqlSession) {
		super.sqlSession = sqlSession;
	}
	
	public void setMapper(SlotInstMapper mapper) {
		this.mapper = mapper;
	}
	
	/**
	 * 根据条件查询
	 * 
	 * @param slotinst
	 *            查询条件
	 * @return List<SlotInst> 集合
	 * @throws Exception
	 */
	public List<SlotInst> select(SlotInst slotinst) throws Exception {
		List<SlotInst> slotinstList = null;
		CardInst cardInst=null;
		CardService_MB cardService=null;
		List<CardInst> cardInstList=null;

		try {
			slotinstList = new ArrayList<SlotInst>();
			slotinstList = this.mapper.queryByCondition(slotinst);			
			if(slotinst!=null && slotinstList.size()>0){
				cardService=(CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD, this.sqlSession);
				for(int i = 0 ; i < slotinstList.size() ; i++){
					cardInst=new CardInst();
					cardInst.setSlotId(slotinstList.get(i).getId());
					cardInstList= new ArrayList<CardInst>();
					cardInstList=cardService.select(cardInst);
					
					if(cardInstList!=null && cardInstList.size()==1){
						cardInstList.get(0).setSlotInst(slotinstList.get(i));
						slotinstList.get(i).setCardInst(cardInstList.get(0));
					}else{
						slotinstList.get(i).setCardInst(null);
					}
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
		return slotinstList;
	}
	
	
	/**
	 * 统计查询
	 * @return List<SlotInst> 集合
	 * @throws Exception
	 */
	public List<SlotInst> selectbystatistics() throws Exception {
		List<SlotInst> slotinstlist = null;
		try {
			slotinstlist = new ArrayList<SlotInst>();
			slotinstlist = this.mapper.queryStatistics();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return slotinstlist;
	}

	public SlotInst select(int portId) {
		SlotInst slotInst = null;
		try {
			List<SlotInst> slotList = this.mapper.queryByPortId(portId);
			if(slotList != null && slotList.size() > 0){
				return slotList.get(0);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return slotInst;
	}
	
	public int selectNumber(CardInst cardinst, int siteId) throws Exception{
		// TODO Auto-generated method stub
		int result=0;
		try {
		result = this.mapper.querryByType(cardinst.getSlotId(),siteId);	
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return result;
	}
	
	/**
	 * 查询全部
	 * 
	 * @return List<SlotInst> 集合
	 * @throws Exception
	 */
	public List<SlotInst> select() throws Exception {
		List<SlotInst> slotinstlist = null;
		try {
			SlotInst slotinst = new SlotInst();
			slotinstlist = mapper.queryByCondition(slotinst);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return slotinstlist;
	}
	
	public void updateMasterMacAddress(SlotInst slotInst) throws Exception{
		try {
			this.mapper.updateMasterAddress(slotInst);
			this.sqlSession.commit();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
