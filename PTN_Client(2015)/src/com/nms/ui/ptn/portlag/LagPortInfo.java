package com.nms.ui.ptn.portlag;

import com.nms.ui.frame.ViewDataObj;

public class LagPortInfo extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4109583228803881806L;
	private int id;
	private int SiteId;
	private int cardId;
	private String name;
	private String role;
	private String speed;
	private int lacpWtr;
	private int lacpPri;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSiteId() {
		return SiteId;
	}

	public void setSiteId(int siteId) {
		SiteId = siteId;
	}

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public int getLacpWtr() {
		return lacpWtr;
	}

	public void setLacpWtr(int lacpWtr) {
		this.lacpWtr = lacpWtr;
	}

	public int getLacpPri() {
		return lacpPri;
	}

	public void setLacpPri(int lacpPri) {
		this.lacpPri = lacpPri;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		getClientProperties().put("id", getId());
		getClientProperties().put("name", getName());
		getClientProperties().put("role", getRole());
		getClientProperties().put("speed", getSpeed());
		getClientProperties().put("lacpWtr", getLacpWtr());
		getClientProperties().put("lacpPri", getLacpPri());
	}
}
