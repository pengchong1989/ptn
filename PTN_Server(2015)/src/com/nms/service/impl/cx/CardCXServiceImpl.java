package com.nms.service.impl.cx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.drivechenxiao.service.bean.ptnne.PtnNeObject;
import com.nms.drivechenxiao.service.bean.slot.SlotObject;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.xmlbean.EquipmentType;

public class CardCXServiceImpl extends CXOperationBase implements OperationServiceI {
	@SuppressWarnings("unchecked")
	@Override
	public String excutionDelete(List objectList) throws Exception {

		List<CardInst> cardInstList = null;
		OperationObject operationObject = null;
		CardInst cardInst = null;
		String result=null;
		CardService_MB cardService = null;
		try {
			cardInstList = objectList;
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			if (cardInstList == null || cardInstList.size() != 1) {
				throw new Exception("list is null");
			}
			cardInst = cardInstList.get(0);
			if("FAN".equals(cardInst.getCardName())){
				if(!cardInst.isDataDownLoad()){
					cardService.delete(cardInst);
				}
				result=ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				cardInst.setCardName("NONE");
				operationObject = this.convertOperation(operationObject, cardInst, TypeAndActionUtil.ACTION_INSERT);
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
					if(!cardInst.isDataDownLoad()){
						cardService.delete(cardInst);
					}
					result=  operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					result = super.getErrorMessage(operationObject);
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(cardService);
			cardInstList = null;
			operationObject = null;
			cardInst = null;
		}
		return result;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		CardService_MB cardService = null;
		CardInst cardInst = null;
		OperationObject operationObject = null;
		String result=null;
		try {
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			cardInst = (CardInst) object;
//			if("FAN".equals(cardInst.getCardName())){
//				if(!cardInst.isDataDownLoad()){
//					cardService.saveOrUpdate(cardInst);
//				}
//				result=ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
//			}else{
				operationObject = this.convertOperation(operationObject, cardInst, TypeAndActionUtil.ACTION_INSERT);
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
					if(!cardInst.isDataDownLoad()){
						cardService.saveOrUpdate(cardInst);
					}
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					result = super.getErrorMessage(operationObject);
				}
//			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(cardService); 
			cardInst = null;
			operationObject = null;
		}
		return result;
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		return null;
	}

	public Map<Integer, String> matching(int siteId) throws Exception {
		OperationObject operationObject = null;
		Map<Integer, String> map = null;
		SlotObject[] slotObjectArray = null;
		try {
			operationObject = this.convertOperation(operationObject, siteId);
			super.sendAction(operationObject);
			operationObject = super.verification(operationObject);
			if (operationObject.isSuccess()) {
				map = new HashMap<Integer, String>();
				slotObjectArray = operationObject.getCxActionObjectList().get(0).getSlotObjectArray();
				for (int i = 0; i < slotObjectArray.length; i++) {
					map.put(i + 1, slotObjectArray[i].getType());
				}
				
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
			slotObjectArray = null;
		}
		return map;
	}

	private OperationObject convertOperation(OperationObject operationObject, CardInst cardInst, String action) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(cardInst.getSiteId()));
			cxActionObject.setAction(action);
			cxActionObject.setType(TypeAndActionUtil.TYPE_CARD);
			cxActionObject.setSlotObject(this.convertSlotObject(cardInst));
			operationObject.getCxActionObjectList().add(cxActionObject);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}

	private OperationObject convertOperation(OperationObject operationObject, int siteId) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(siteId));
			cxActionObject.setAction(TypeAndActionUtil.ACTION_SELECT);
			cxActionObject.setType(TypeAndActionUtil.TYPE_CARD);
			cxActionObject.setPtnNeObject(this.convertPtnNeObject(siteId));

			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}

	private PtnNeObject convertPtnNeObject(int siteId) throws Exception {
		PtnNeObject ptnNeObject = new PtnNeObject();

		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		List<EquipmentType> equipmentTypeList = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = siteService.select(siteId);

			equipmentTypeList =ConstantUtil.equipmentTypeList;
			for (EquipmentType equipmentType : equipmentTypeList) {
				if (equipmentType.getTypeName().equals(siteInst.getCellType())) {
					ptnNeObject.setType(equipmentType.getCxEquipmentName());
					break;
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}

		return ptnNeObject;
	}

	private SlotObject convertSlotObject(CardInst cardInst) {
		SlotObject slotObject = new SlotObject();
		slotObject.setId(cardInst.getSlotInst().getNumber() + "");
		slotObject.setType(cardInst.getCardName());
		return slotObject;
	}

	@Override
	public Object synchro(int siteId) {
		return null;
	}
}
