package com.nms.ui.ptn.portlag;

import java.util.ArrayList;
import java.util.List;

import twaver.Card;
import twaver.Element;
import twaver.Node;
import twaver.Port;
import twaver.TDataBox;
import twaver.tree.LazyLoader;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class LagPortLoader implements LazyLoader {
	private TDataBox box = null;
	private PortLagInfo portLagInfo;

	public LagPortLoader(TDataBox box, PortLagInfo portLagInfo) {
		this.box = box;
		this.portLagInfo = portLagInfo;
	}

	@Override
	public boolean isLeaf(Element element) {
		if (element instanceof Port) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isLoaded(Element element) {
		return (Boolean) element.getUserObject();
	}

	@Override
	public void load(Element element) {
		if (element instanceof Card) {
			createPort((Card) element);
		} else if (element instanceof Node) {
			createCard((Node) element);
		}
		element.setUserObject(true);
	}

	private void createCard(Node parent) {
		SiteInst site = (SiteInst) parent.getBusinessObject();
		CardService_MB service = null;
		List<CardInst> cardList = new ArrayList<CardInst>();
		CardInst cardInst = null;
		try {
			service = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			cardInst = new CardInst();
			cardInst.setSiteId(site.getSite_Inst_Id());
			cardList = service.select(cardInst);
			for (CardInst obj : cardList) {
				if (obj.getId() != 0&&!"FAN".equals(obj.getCardName())&&!"PSU".equals(obj.getCardName())) {
					Card card = new Card();
					card.setSelected(false);
					card.setVisible(true);
					card.setBusinessObject(obj);
					card.setUserObject(false);
					card.setParent(parent);
					card.setName(obj.getCardName());
					box.addElement(card);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
			cardList = null;
			cardInst = null;
		}
	}

	private void createPort(Card parent) {
		CardInst cardInst = (CardInst) parent.getBusinessObject();
		PortInst portInst = null;
		List<PortInst> portList = null;
		PortService_MB service = null;
		try {
			addLagPort(cardInst, parent);
			service = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInst = new PortInst();
			portInst.setSiteId(cardInst.getSiteId());
			portInst.setCardId(cardInst.getId());
			portInst.setPortType("NONE");
			portList = service.select(portInst);
			for (PortInst portinst : portList) {
				addPort(portinst, parent);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			portInst = null;
			portList = null;
			UiUtil.closeService_MB(service);
		}
	}

	/**
	 * 添加lag中的端口
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
	private void addLagPort(CardInst cardInst,Card parent) throws Exception {
		if (null != this.portLagInfo) {

			for (PortInst portInst : this.portLagInfo.getPortList()) {
				if(cardInst.getId() == this.getSlotId(portInst)){
					addPort(portInst, parent);
				}
			}

		}
	}

	/**
	 * 获取槽位对象主键
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private int getSlotId(PortInst portInst) throws Exception {

		CardService_MB cardService = null;
		CardInst cardinst = null;
		List<CardInst> cardInstList = null;
		try {
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			cardinst = new CardInst();
			cardinst.setId(portInst.getCardId());
			cardInstList = cardService.select(cardinst);

			if (cardInstList.size() == 1) {
				return cardInstList.get(0).getId();
			} else {
				return 0;
			}

		} catch (Exception e) {
			throw e;
		} finally{
			UiUtil.closeService_MB(cardService);
			cardinst = null;
			cardInstList = null;
		}
	}
	
	private void addPort(PortInst portinst , Card parent){
		Port port = new Port();
		port.setUserObject(false);
		port.setBusinessObject(portinst);
		port.setName(portinst.getPortName());
		port.setParent(parent);
		port.setSelected(false);
		port.setVisible(true);
		box.addElement(port);
	}
}