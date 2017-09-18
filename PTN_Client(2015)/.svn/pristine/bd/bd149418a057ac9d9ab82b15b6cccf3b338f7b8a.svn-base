package com.nms.db.bean.ptn.path.protect;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.ui.frame.ViewDataObj;

/**
 * 双规保护tunnel关联 bean
 * @author dzy
 *
 */
public class DualRelevance extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4563576473987071012L;
	private int id;
	private int objId; //双规ID
	private int objType; //tunnel 类型  穿通或关联
	private int dualGroupId; //双规关系表分组ID
	private int siteId ; //网元ID
	private int tunnelId ; //tunnel ID
	private Tunnel breakoverTunnel = new Tunnel();
	private List<Tunnel>  relevanceTunnelList = new ArrayList<Tunnel>();
	@Override
	public void putObjectProperty() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getObjId() {
		return objId;
	}
	public void setObjId(int objId) {
		this.objId = objId;
	}
	public int getDualGroupId() {
		return dualGroupId;
	}
	public void setDualGroupId(int dualGroupId) {
		this.dualGroupId = dualGroupId;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public Tunnel getBreakoverTunnel() {
		return breakoverTunnel;
	}
	public void setBreakoverTunnel(Tunnel breakoverTunnel) {
		this.breakoverTunnel = breakoverTunnel;
	}
	public List<Tunnel> getRelevanceTunnelList() {
		return relevanceTunnelList;
	}
	public void setRelevanceTunnelList(List<Tunnel> relevanceTunnelList) {
		this.relevanceTunnelList = relevanceTunnelList;
	}
	public int getObjType() {
		return objType;
	}
	public void setObjType(int objType) {
		this.objType = objType;
	}
	public int getTunnelId() {
		return tunnelId;
	}
	public void setTunnelId(int tunnelId) {
		this.tunnelId = tunnelId;
	}
	
}
