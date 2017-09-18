package com.nms.ui.topology.util;

import java.util.List;

import twaver.Card;
import twaver.Port;
import twaver.Rack;
import twaver.Slot;
import twaver.TDataBox;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.UiUtil;

/**
 * 创建网元面板拓扑
 * 
 * @author kk
 * 
 */
public class CreateNeTopo {

	private TDataBox tDataBox = null;
	private CreateElementUtil createElementUtil = null;
	private int siteId = 0;

	/**
	 * 创建一个新的实例
	 * 
	 * @param dataBox
	 *            twaver数据对象
	 */
	public CreateNeTopo(TDataBox dataBox, int siteId) {
		this.tDataBox = dataBox;
		this.createElementUtil = new CreateElementUtil();
		this.siteId = siteId;
	}

	/**
	 * 创建拓扑界面
	 * 
	 * @throws Exception
	 */
	public void createTopo() throws Exception {
		SiteService_MB siteService = null;
		SiteInst siteInst = null;
		Rack rack = null;
		Slot slot = null;
		Card card = null;
		Port port = null;
		List<SiteInst> siteInstList = null;
		try {
			this.tDataBox.clear();

			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = new SiteInst();
			siteInst.setSite_Inst_Id(this.siteId);
			siteInstList = siteService.select(siteInst);

			if (null != siteInstList && siteInstList.size() == 1) {
				siteInst = siteInstList.get(0);

				// 创建机架
				rack = this.createElementUtil.createRack(siteInst.getEquipInst());
				this.tDataBox.addElement(rack);

				// 创建槽位
				for (SlotInst slotInst : siteInst.getEquipInst().getSlotlist()) {
					slot = this.createElementUtil.createSlot(slotInst, rack);
					this.tDataBox.addElement(slot);

					// 创建板卡
					if (null != slotInst.getCardInst()) {
						card = this.createElementUtil.createCard(slotInst.getCardInst(), slot,false);
						this.tDataBox.addElement(card);

						// 创建端口
						if (slotInst.getCardInst().getPortList() != null) {
							for (PortInst portInst : slotInst.getCardInst().getPortList()) {
								port = this.createElementUtil.createPort(portInst, card);
								port.setName("");
								this.tDataBox.addElement(port);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}
}
