package com.nms.corba.ninterface.impl.resource.proxy;

import equipment.EquipmentHolder_T;
import equipment.EquipmentOrHolderIterator_IHolder;
import equipment.EquipmentOrHolderList_THolder;
import equipment.EquipmentOrHolder_T;
import equipment.EquipmentOrHolder_THolder;
import equipment.Equipment_T;
import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesIterator_IHolder;
import globaldefs.NamingAttributesList_THolder;
import globaldefs.ProcessingFailureException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.resource.EquipmentOrHolderIterator_Impl;
import com.nms.corba.ninterface.impl.resource.NamingAttributesIterator_Impl;
import com.nms.corba.ninterface.impl.resource.tool.EquipmentConertTool;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.UiUtil;
/**
 * 设备信息代理类
 * @author Administrator
 *
 */
public class EquipmentProxy
{
	private Logger LOG = Logger.getLogger(EquipmentProxy.class.getName());
	private ICorbaSession session;
	public EquipmentProxy(ICorbaSession userSession){
		this.session = userSession;
	}
	
	//获取包含指定PTP的单板的名字列表
	public void getAllSupportingEquipmentNames(NameAndStringValue_T[] ptpOrMfdName,
			NamingAttributesList_THolder nameList)
	{
		nameList.value = new NameAndStringValue_T[10][];
		if(nameList==null)
		{ 
			nameList = new NamingAttributesList_THolder();
		}
		
	}
	
	/**
	 * 获取指定设备信息
	 * @param equipmentOrHolderName		设备或者容器标识符
	 * @param equip		设备信息
	 * @throws ProcessingFailureException
	 */
	public void getEquipment(NameAndStringValue_T[] equipmentOrHolderName,
							 	EquipmentOrHolder_THolder equip) throws ProcessingFailureException{
		EquipmentConertTool equipmentConertTool;
		int id = 0;
		Map<Integer, SlotInst> slotInstMap = null;
		Map<Integer,CardInst> cardMap = null;
		int type = 0;
		int siteId;
		String name;
		CardInst cardInst;
		SlotInst slotInst;
		try {
			slotInst = new SlotInst();
			cardInst = new CardInst();
			siteId = Integer.parseInt(equipmentOrHolderName[1].value);
			name = equipmentOrHolderName[2].value;
			 if(4 == equipmentOrHolderName.length){	//设备
				if(!CheckParameterUtil.checkEquipmentName(equipmentOrHolderName))
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
				type = 1;
			}else if(3 == equipmentOrHolderName.length){			//设备容器
				if(!CheckParameterUtil.checkEquipmentHolderName(equipmentOrHolderName))
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
				if(name.toLowerCase().contains("slot")){
					type = 2;
				}else if(name.toLowerCase().contains("shelf")){
					type = 3;
				}else if(name.toLowerCase().contains("rack")){
					type = 4;
				}
			}
			equipmentConertTool = new EquipmentConertTool();
			if(null == equip.value)
				equip.value = new EquipmentOrHolder_T();
			if(1 == type || 2 == type){
				id = Integer.parseInt(name.substring(name.indexOf("slot")+4).trim().substring(1).trim());
				slotInstMap = getSlotInstMap(siteId,id);
				slotInst = slotInstMap.get(slotInstMap.entrySet().iterator().next().getKey());
				if(null==slotInstMap||slotInstMap.size()==0)
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
				cardMap = getCardMap(siteId,slotInst.getId());
				cardInst = cardMap.get(cardMap.entrySet().iterator().next().getKey());
				
			}
			if(1 == type){ //设备
				//数据转换
				if(null==cardMap || cardMap.size()==0)
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
				equip.value.equip(new Equipment_T());
				//数据转换
				equipmentConertTool.convertEquipment_T(cardInst, slotInst,equip.value.equip());
			}else if(2 == type){	//槽位
				equip.value.holder(new EquipmentHolder_T());
				equipmentConertTool.convertEquipmentHolder_T(slotInst, cardMap, equip.value.holder());
			}else if(3 == type){ //子架
				equip.value.holder(new EquipmentHolder_T());
				//数据转换
				equipmentConertTool.convertShelf2Corba(siteId, equip.value.holder());
			}else if(4 == type){	//机架
				equip.value.holder(new EquipmentHolder_T());
				//数据转换
				equipmentConertTool.convertRack2Corba(siteId, equip.value.holder());
			}
			
		} catch (ProcessingFailureException e) {
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getEquipment ex.");
		}
	
	}

	/**
	 * 获取所有设备信息
	 * @param meOrHolderName	网元名称活EMS名称
	 * @param howMany	指定每次迭代的个数
	 * @param eqList	设备信息对象
	 * @param eqIt		迭代器
	 * @throws ProcessingFailureException 
	 */
	public void getAllEquipment(NameAndStringValue_T[] meOrHolderName, int howMany,
					EquipmentOrHolderList_THolder eqList,
					EquipmentOrHolderIterator_IHolder eqIt) throws ProcessingFailureException {
		EquipmentConertTool equipmentConertTool = new EquipmentConertTool();
		int type = 0; //0:查询网元下或者机架下 1：查询子架下 2：查询槽位下
		String name;
		int neId = 0;
		Map<Integer, SlotInst> slotInstMap;
		Map<Integer,CardInst> cardMap;
		int slotNum = 0;
		try {
			 if(2 == meOrHolderName.length){	//设备
				 if(!CheckParameterUtil.checkMeName(meOrHolderName))
						throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
				type = 0;
				
			}else if(3 == meOrHolderName.length){			//设备容器
				if(!CheckParameterUtil.checkEquipmentHolderName(meOrHolderName))
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
				name = meOrHolderName[2].value;
				if(name.toLowerCase().contains("slot")){
					type = 2;
					slotNum = Integer.parseInt(name.substring(name.indexOf("slot")+4).trim().substring(1).trim());
				}else if(name.toLowerCase().contains("shelf")){
					type = 1;
				}else if(name.toLowerCase().contains("rack")){
					type = 0;
				}
			}
			neId = Integer.parseInt(meOrHolderName[1].value);
			slotInstMap = getSlotInstMap(neId,slotNum);
			cardMap = getCardMap(neId,slotNum == 0? 0:slotInstMap.get(slotInstMap.entrySet().iterator().next().getKey()).getId());
			if((null==slotInstMap||slotInstMap.size()==0) && (null==cardMap || cardMap.size()==0)){
				eqList.value = new EquipmentOrHolder_T[0];
				return;
			}
			eqList.value = new EquipmentOrHolder_T[slotInstMap.size()+cardMap.size()+2-type];
			equipmentConertTool.convertEquipmentList2Corba(neId,slotInstMap,cardMap, eqList.value,type);
			EquipmentOrHolderIterator_Impl iter = new EquipmentOrHolderIterator_Impl(this.session);
			iter.setIterator(eqIt, eqList, howMany);
		} catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getAllEquipment ex.");
		}
	}

	/**
	 * 获取槽位Map
	 * @param neId		网元ID
	 * @return
	 * @throws ProcessingFailureException 
	 */
	private Map<Integer, SlotInst> getSlotInstMap(int neId,int nummber) throws ProcessingFailureException {
		SlotInst slotInst;
		List<SlotInst> slotInstList = null;
		SlotService_MB slotService = null;
		Map<Integer,SlotInst> slotInstMap;
		try {
			slotInst = new SlotInst();
			slotInstMap = new HashMap<Integer, SlotInst>();
			slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			if(0!=neId)
				slotInst.setSiteId(neId);
			if(0!=nummber)
				slotInst.setNumber(nummber);	
			slotInstList = slotService.select(slotInst);
			if(slotInstList.size()==0)
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			for (SlotInst slot : slotInstList) {
				slotInstMap.put(slot.getId(), slot);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,	"getCardMap ex.");
		} finally {
			UiUtil.closeService_MB(slotService);
		}
		return slotInstMap;
	}

	/**
	 * 返回板卡集合
	 * @param neId		网元ID
	 * @param Id		槽位ID
	 * @return
	 * @throws ProcessingFailureException
	 */
	private Map<Integer,CardInst> getCardMap(int neId,int id) throws ProcessingFailureException {
		CardInst card;
		List<CardInst> cardList = null;
		CardService_MB cardService = null;
		Map<Integer,CardInst> cardMap;
		try {
			card = new CardInst();
			cardMap = new HashMap<Integer, CardInst>();
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			if(0!=neId)
				card.setSiteId(neId);
			if(0!=id)
				card.setSlotId(id);	
			cardList = cardService.select(card);
			for (CardInst cardInst : cardList) {
				cardMap.put(cardInst.getSlotId(), cardInst);
			}
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,	"getCardMap ex.");
		} finally {
			UiUtil.closeService_MB(cardService);
		}
		return cardMap;
	}

	/**
	 * @author guoqc
	 * 获取网元或支架中包含的所有的子设备的名称,如没有子设备即获取所有网元的名称
	 * 入参  meOrHolderName 厂商信息
	 * 入参  how_many 指定每次迭代的个数
	 * 出参  nameList 设备板卡信息
	 * 出参  nameIt 迭代器
	 * @throws Exception 
	 */
	public void getAllEquipmentNames(NameAndStringValue_T[] meOrHolderName, int howMany,
										NamingAttributesList_THolder nameList,
											NamingAttributesIterator_IHolder nameIt) throws Exception {
		int neId;
		CardInst cardInst;
		CardService_MB cardService = null;
		try {
			if(!CheckParameterUtil.checkMeName(meOrHolderName))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			cardInst = new CardInst();
			neId = Integer.parseInt(meOrHolderName[1].value);
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			cardInst.setSiteId(neId);
			List<CardInst> cardList = cardService.select(cardInst);
			//数据转换
			nameList.value = new NameAndStringValue_T[cardList.size()][1];
			this.convertEquipmentName2Corba(cardList, nameList);
			//迭代
			NamingAttributesIterator_Impl iter = new NamingAttributesIterator_Impl(this.session);
			iter.setIterator(nameIt, nameList, howMany);
		
		}  catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,	"getAllEquipmentNames ex.");
		} finally {
			UiUtil.closeService_MB(cardService);
		}
	}
	
	/**
	 * 数据转换
	 */
	private void convertEquipmentName2Corba(List<CardInst> cardList, NamingAttributesList_THolder nameList) {
		for (int i = 0; i < cardList.size(); i++) {
			nameList.value[i][0] = new NameAndStringValue_T("CardName", cardList.get(i).getCardName());
		}
	}

	public static void main(String[] args) throws Exception {
		NameAndStringValue_T[] arg = new NameAndStringValue_T[1];
		NameAndStringValue_T n = new NameAndStringValue_T();
		n.value = (CorbaConfig.getInstanse().getCorbaEmsName());
		arg[0] = n;
		EquipmentOrHolder_THolder equip = new EquipmentOrHolder_THolder();
		new EquipmentProxy(new ICorbaSession()).getEquipment(arg, equip);
		NameAndStringValue_T[] add = equip.value.equip().additionalInfo;
		for (int i = 0; i < add.length; i++) {
			System.out.println(add[i].name + " : " + add[i].value);
		}
	}
}
