package com.nms.db.bean.ptn;

import java.util.List;

import com.nms.db.bean.ptn.path.eth.DualInfo;
import com.nms.db.bean.ptn.path.protect.PwProtect;
import com.nms.model.ptn.path.eth.DualInfoService_MB;
import com.nms.model.ptn.path.protect.PwProtectService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class ARPInfo extends ViewDataObj{

	private static final long serialVersionUID = -2514268440919026855L;
	private int id;//主键id
	private String name;//本地名称
	private int pwProtectId;//PW保护ID
	private String dualName;//双规保护名称
	private String sourceMac;//源MAC地址:0x00 00 00 00 00 01 
	private int vlanEnabled;//VLAN ：0/1=无/有
	private int vlanId;//VLAN值 范围2-4095
	private int vlanPri;//VLAN PRI:0-7
	private String sourceIp;//源IP地址 10.18.2.1
	private String targetIp;//目的IP地址 10.18.3.2
	private int enabled;//arp报文是否使能  0/1=不使能/使能
	private int siteId;
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPwProtectId() {
		return pwProtectId;
	}
	public void setPwProtectId(int pwProtectId) {
		this.pwProtectId = pwProtectId;
	}
	public String getDualName() {
		return dualName;
	}
	public void setDualName(String dualName) {
		this.dualName = dualName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSourceMac() {
		return sourceMac;
	}
	public void setSourceMac(String sourceMac) {
		this.sourceMac = sourceMac;
	}
	public int getVlanEnabled() {
		return vlanEnabled;
	}
	public void setVlanEnabled(int vlanEnabled) {
		this.vlanEnabled = vlanEnabled;
	}
	public int getVlanId() {
		return vlanId;
	}
	public void setVlanId(int vlanId) {
		this.vlanId = vlanId;
	}
	public int getVlanPri() {
		return vlanPri;
	}
	public void setVlanPri(int vlanPri) {
		this.vlanPri = vlanPri;
	}
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getTargetIp() {
		return targetIp;
	}
	public void setTargetIp(String targetIp) {
		this.targetIp = targetIp;
	}
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	@Override
	public void putObjectProperty() {
		this.putClientProperty("id", id);
		this.putClientProperty("name", name);
		this.putClientProperty("dualName", this.getDualProtectName());
		this.putClientProperty("sourceMac", sourceMac);
		this.putClientProperty("vlanEnabled", vlanEnabled);
		this.putClientProperty("vlanId", vlanId);
		this.putClientProperty("vlanPri", vlanPri);
		this.putClientProperty("sourceIp", sourceIp);
		this.putClientProperty("targetIp", targetIp);
		this.putClientProperty("enabled", enabled);
	}
	private Object getDualProtectName() {
		PwProtectService_MB service = null;
		DualInfoService_MB dualService = null;
		try {
			service = (PwProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PWPROTECT);
			PwProtect pwProtect = new PwProtect();
			pwProtect.setSiteId(ConstantUtil.siteId);
			pwProtect.setBusinessId(this.pwProtectId);
			List<PwProtect> pwProtectList = service.select(pwProtect);
			if(pwProtectList != null && pwProtectList.size() > 0){
				dualService = (DualInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALINFO);
				DualInfo dual = dualService.queryById(pwProtectList.get(0).getServiceId());
				if(dual != null){
					return dual.getName();
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(dualService);
			UiUtil.closeService_MB(service);
		}
		return null;
	}
	
}
