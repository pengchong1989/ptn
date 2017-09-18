package com.nms.ui.ptn.performance.model;

import java.util.List;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.shelf.SiteInst;

public class PerformanceTaskTemp {
	private List<SiteInst> siteInstList;
	private List<CardInst> cardInstList;
	private int cycle;
	private String taskName;
	private int runStates;
	private String createTime;
	
	public List<SiteInst> getSiteInstList() {
		return siteInstList;
	}
	public void setSiteInstList(List<SiteInst> siteInst) {
		this.siteInstList = siteInst;
	}
	public List<CardInst> getCardInstList() {
		return cardInstList;
	}
	public void setCardInstList(List<CardInst> cardInst) {
		this.cardInstList = cardInst;
	}
	public int getCycle() {
		return cycle;
	}
	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public int getRunStates() {
		return runStates;
	}
	public void setRunStates(int runStates) {
		this.runStates = runStates;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
