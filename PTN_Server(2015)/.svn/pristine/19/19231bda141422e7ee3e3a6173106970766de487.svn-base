package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.enums.EManufacturer;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.CardCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.CardDispatchI;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.wh.CardWHServiceImpl;
import com.nms.service.notify.Message.MessageType;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class CardDispatch extends DispatchBase implements CardDispatchI {
	
	public String excuteInsert(Object object) throws Exception {
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		CardInst cardInst = null;
		try {
			if (object == null) {
				throw new Exception("cardInst is null");
			}
			cardInst=(CardInst) object;
			
			// 虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE, cardInst);
			if (null != siteCheck) {
				super.notifyCorba("card", MessageType.CREATION, cardInst, "card", ResultString.CONFIG_SUCCESS);
				return siteCheck;
			}
			manufacturer = super.getManufacturer(cardInst.getSiteId());

			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new CardWHServiceImpl();
			} else {
				operationServiceI = new CardCXServiceImpl();
			}
			result = operationServiceI.excutionInsert(cardInst);
			super.notifyCorba("card", MessageType.CREATION, cardInst, "card", result);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
			return result;
	}

	public String excuteDelete(Object object) throws Exception {
		
		int manufacturer = 0;
		OperationServiceI operationServiceI = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		CardInst cardInst = null;
		CardService_MB cardService = null;
		try {
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			
			if (object == null) {
				throw new Exception("cardInst is null");
			}
			cardInst=(CardInst) object;
			manufacturer = super.getManufacturer(cardInst.getSiteId());

			// 虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE, cardInst);
			if (null != siteCheck) {
				super.notifyCorba("card", MessageType.DELETION, cardInst, "card", ResultString.CONFIG_SUCCESS);
				return siteCheck;
			}
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				operationServiceI = new CardWHServiceImpl();
			} else {
				operationServiceI = new CardCXServiceImpl();
			}
			List<CardInst> cardInstList = new ArrayList<CardInst>();
			cardInstList.add(cardInst);
			result = operationServiceI.excutionDelete(cardInstList);
			super.notifyCorba("card", MessageType.DELETION, cardInst, "card", result);

			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				cardService.delete(cardInst);
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(cardService);
		}
		return result;
	}

	public Map<Integer, String> matchingCard(int siteId) throws Exception {

		Map<Integer, String> map = null;

		int manufacturer = 0;
		try {

			// 虚拟网元操作
			SiteUtil siteUtil=new SiteUtil();
			String siteCheck = (String) siteUtil.irtualSiteSynchroAction(TypeAndActionUtil.ACTION_SYNCHRO, siteId);
			if (null != siteCheck) {
				return map;
			}

			manufacturer = super.getManufacturer(siteId);

			if (manufacturer == EManufacturer.WUHAN.getValue()) {

			} else {
				CardCXServiceImpl operationServiceI = new CardCXServiceImpl();
				map = operationServiceI.matching(siteId);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return map;
	}

	@Override
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		return null;
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		return null;
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
