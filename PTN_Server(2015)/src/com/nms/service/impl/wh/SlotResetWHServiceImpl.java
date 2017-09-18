package com.nms.service.impl.wh;

import java.util.List;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.drive.service.bean.NEObject;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class SlotResetWHServiceImpl extends WHOperationBase implements OperationServiceI{

	@Override
	public String excutionDelete(List objectList) throws Exception {
		 
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		OperationObject operationObject = null;
		CardInst cardInst = null;
		OperationObject operationObjectResult = null;
		cardInst = (CardInst) object;
		operationObject = this.getOperationObject(cardInst);
		super.sendAction(operationObject);
		operationObjectResult = super.verification(operationObject);
		if (operationObjectResult.isSuccess()) {
			return operationObjectResult.getActionObjectList().get(0).getStatus();
		}
		return super.getErrorMessage(operationObjectResult);
	}

	@Override
	public String excutionUpdate(Object object) throws Exception {
		 
		return null;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		 
		return null;
	}

	public OperationObject getOperationObject(CardInst cardInst){
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
        SlotService_MB slotService = null;
		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(cardInst.getSiteId());
			slotService=(SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			if("SP16".equals(cardInst.getCardName())){
			    neObject.setControlPanelType(118489105);				
			    int number=slotService.selectNumber(cardInst, cardInst.getSiteId());
			    if(number == 2){
				    neObject.setControlPanelGroupId(1);
				    neObject.setControlPanelAddress(2);
			    }
			    if(number == 3){
				    neObject.setControlPanelGroupId(1);
				    neObject.setControlPanelAddress(3);
			   }
		    }
			//盘地址，盘组号等协议部商定板卡类型后修改,目前暂时写死
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType("slotreset");
			operationObject.getActionObjectList().add(actionObject);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			actionObject = null;
			neObject = null;
			UiUtil.closeService_MB(slotService);
		}
		return operationObject;
	}
}
