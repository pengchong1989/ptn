package com.nms.db.bean.ptn.path.protect;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EActiveStatus;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.ptn.port.DualRelevanceService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

/**
 * 双规保护bean
 * @author dzy
 *
 */
public class DualProtect extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4563576473987071012L;
	private int id;
	private int protectType; //保护    1plus1=0 ,1to1=1
	private int regainModel; //恢复模式   不可恢复=0，可恢复=1
	private int apsEnable ; //使能aps ;  false=0 ,true= 1
	private int role ; //角色 ; 工作=1 ， 保护=2
	private int waitTime; //等待时间 (s)
	private int lagId ;//承载lag的Id
	private int rotateWay; //倒换方式
	private int dualRelevanceGroupId; //关联tunnel 的Id集合
	private int siteId; //网元ID
	private int activeStatus;//激活状态，1=激活，2=未激活 
	private int businessId;
	private int rotateOrder;
	private Tunnel breakoverTunnel = new Tunnel();
	private List<Tunnel>  relevanceTunnelList = new ArrayList<Tunnel>();
	@Override
	public void putObjectProperty() {
		Tunnel tunnel;
		DualRelevanceService_MB dualRelevanceService = null;
		DualRelevance dualRelevanceSel;
		List<DualRelevance> dualRelevanceList;
		List<Tunnel> tunnelList = null;
		TunnelService_MB tunnelService = null;
		try {
			dualRelevanceSel = new DualRelevance();
			dualRelevanceSel.setSiteId(this.getSiteId());
			dualRelevanceSel.setDualGroupId(this.getDualRelevanceGroupId());
			dualRelevanceService  = (DualRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALRELEVANCE);
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			dualRelevanceList = dualRelevanceService.queryByCondition(dualRelevanceSel);
			for(DualRelevance dualRelevance:dualRelevanceList){
				//穿通tunnel
				if(1==dualRelevance.getObjType()){
					tunnel = new Tunnel();
					tunnel.setTunnelId(dualRelevance.getTunnelId());
					tunnelList = tunnelService.selectNodeByTunnelId(tunnel);
				}
			}
			this.putClientProperty("name", "dualProtect_"+this.getId());
			this.putClientProperty("type", UiUtil.getCodeById(this.getProtectType()).getCodeName());
			this.putClientProperty("port", "lag/"+this.getLagId());
			this.putClientProperty("rotateWay", UiUtil.getCodeById(this.getRotateWay()).getCodeName());
			if(null!=tunnelList&&tunnelList.size()>0){
				this.putClientProperty("tunnel", tunnelList.get(0).getTunnelName());
			}
			this.putClientProperty("identity", UiUtil.getCodeById(this.getRole()).getCodeName());
			this.putClientProperty("aps", this.getApsEnable()==1?"使能":"不使能");
			this.putClientProperty("waittime", this.getWaitTime());
			this.putClientProperty("command", ""); //外部命令
			this.putClientProperty("workPort", "lag/"+this.getLagId());
			this.putClientProperty("activeStatus", this.getActiveStatus()==EActiveStatus.ACTIVITY.getValue()?EActiveStatus.ACTIVITY:EActiveStatus.UNACTIVITY);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(dualRelevanceService);
		}
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProtectType() {
		return protectType;
	}
	public void setProtectType(int protectType) {
		this.protectType = protectType;
	}
	public int getRegainModel() {
		return regainModel;
	}
	public void setRegainModel(int regainModel) {
		this.regainModel = regainModel;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}
	public int getLagId() {
		return lagId;
	}
	public void setLagId(int lagId) {
		this.lagId = lagId;
	}
	public int getRotateWay() {
		return rotateWay;
	}
	public void setRotateWay(int rotateWay) {
		this.rotateWay = rotateWay;
	}
	public List<Tunnel> getRelevanceTunnelList() {
		return relevanceTunnelList;
	}
	public void setRelevanceTunnelList(List<Tunnel> relevanceTunnelList) {
		this.relevanceTunnelList = relevanceTunnelList;
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
	
	public int getDualRelevanceGroupId() {
		return dualRelevanceGroupId;
	}
	public void setDualRelevanceGroupId(int dualRelevanceGroupId) {
		this.dualRelevanceGroupId = dualRelevanceGroupId;
	}
	public int getApsEnable() {
		return apsEnable;
	}
	public void setApsEnable(int apsEnable) {
		this.apsEnable = apsEnable;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getRotateOrder() {
		return rotateOrder;
	}
	public void setRotateOrder(int rotateOrder) {
		this.rotateOrder = rotateOrder;
	}
	public int getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

}
