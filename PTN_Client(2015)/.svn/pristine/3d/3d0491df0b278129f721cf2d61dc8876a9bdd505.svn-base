package com.nms.ui.ptn.alarm.model;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.enums.EObjectType;
/**
 * 历史告警过滤条件类
 * @author lp
 *
 */
public class HisAlarmFilter {
	private List<SiteInst> siteInsts;    // 监控网元id列表
	private List<SlotInst> slotInsts;    // 槽位id列表
	private EObjectType objectType;            //查询类型：网元、网元+槽位

	public HisAlarmFilter() {
		siteInsts = new ArrayList<SiteInst>();
		slotInsts = new ArrayList<SlotInst>();
	}

	public EObjectType getObjectType() {
		return objectType;
	}

	public void setObjectType(EObjectType objectType) {
		this.objectType = objectType;
	}

	public List<SiteInst> getSiteInsts() {
		return siteInsts;
	}

	public void setSiteInsts(List<SiteInst> siteInsts) {
		this.siteInsts = siteInsts;
	}

	public List<SlotInst> getSlotInsts() {
		return slotInsts;
	}

	public void setSlotInsts(List<SlotInst> slotInsts) {
		this.slotInsts = slotInsts;
	}
}
