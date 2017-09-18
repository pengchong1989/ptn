package com.nms.db.bean.ptn.path.qinq;

import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.enums.EActiveStatus;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class QinqInst extends ViewDataObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4257320966748545383L;
	private int qinqId;//主键id
	private String qinqName;//qinq名称
	private String tpId;//TPID  0x8100/0x9100/0x88a8
	private int aSiteId;//a端网元id
	private int zSiteId;//z端网元id
	private int aPortId;//a端端口id
	private int zPortId;//z端端口id
	private int aAcPortId;//a端uni端口id
	private int zAcPortId;//z端uni端口id
	private int acVlanIdRule;//UNI口的VLAN标签处理规则:增加(增加/删除/透传=0/1/2)
	private int minVlanId;//vlanId下限  1~4094 
	private int maxVlanId;//vlanId上限  1~4094（大于或者等于下限）
	private String createTime;
	private String createUser;
	private int vlanId;//运营商vlanId  1~4094 
	private int qinqStatus;//qinq状态   0/1 = 未激活/激活
	private int basedInVlanId;//是否基于内层vlanId  否/是=0/1
	private List<QinqChildInst> qinqChildInst = null; 
	
	@Override
	public void putObjectProperty() {
		try{
			this.putClientProperty("qinqId", getQinqId());
			this.putClientProperty("qinqName", getQinqName());
			this.putClientProperty("tpId",getTpId());
			this.putClientProperty("aSiteId",this.getSiteNameBySiteId(getaSiteId()));
			this.putClientProperty("zSiteId",this.getSiteNameBySiteId(getzSiteId()));
			this.putClientProperty("aAcPortId",this.getPortNameByPortId(getaAcPortId(),getaSiteId()));
			this.putClientProperty("zAcPortId",this.getPortNameByPortId(getzAcPortId(),getzSiteId()));
			this.putClientProperty("createTime",DateUtil.strDate(getCreateTime(), DateUtil.FULLTIME));
			this.putClientProperty("createUser",getCreateUser());
			this.putClientProperty("vlanId",getVlanId());
			this.putClientProperty("qinqStatus",getQinqStatus() == 1 ? EActiveStatus.ACTIVITY.toString() : EActiveStatus.UNACTIVITY.toString());
			this.putClientProperty("basedInVlanId",getBasedInVlanId() == 0 ? Boolean.TRUE : Boolean.FALSE);	
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private String getPortNameByPortId(int portId, int siteId) throws Exception {
		PortService_MB portService = null;
		List<PortInst> portList = null;
		PortInst portinst = null;
		String portName = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portinst = new PortInst();
			portinst.setPortId(portId);
			portinst.setSiteId(siteId);
			portList = portService.select(portinst);
			if(portList != null && portList.size() == 1){
				portName = portList.get(0).getPortName();
			}else{
				portName = portId+"";
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
			portList = null;
			portinst = null;
		}
		
		return portName;
	}

	private String getSiteNameBySiteId(int siteId) throws Exception {
		SiteService_MB siteService = null;
		String siteName = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteName = siteService.select(siteId).getCellId();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return siteName;
	}

	public int getQinqId() {
		return qinqId;
	}

	public void setQinqId(int qinqId) {
		this.qinqId = qinqId;
	}

	public String getQinqName() {
		return qinqName;
	}

	public void setQinqName(String qinqName) {
		this.qinqName = qinqName;
	}

	public String getTpId() {
		return tpId;
	}

	public void setTpId(String tpId) {
		this.tpId = tpId;
	}

	public int getaSiteId() {
		return aSiteId;
	}

	public void setaSiteId(int aSiteId) {
		this.aSiteId = aSiteId;
	}

	public int getzSiteId() {
		return zSiteId;
	}

	public void setzSiteId(int zSiteId) {
		this.zSiteId = zSiteId;
	}

	public int getaPortId() {
		return aPortId;
	}

	public void setaPortId(int aPortId) {
		this.aPortId = aPortId;
	}

	public int getzPortId() {
		return zPortId;
	}

	public void setzPortId(int zPortId) {
		this.zPortId = zPortId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public int getVlanId() {
		return vlanId;
	}

	public void setVlanId(int vlanId) {
		this.vlanId = vlanId;
	}

	public int getQinqStatus() {
		return qinqStatus;
	}

	public void setQinqStatus(int qinqStatus) {
		this.qinqStatus = qinqStatus;
	}

	public List<QinqChildInst> getQinqChildInst() {
		return qinqChildInst;
	}

	public void setQinqChildInst(List<QinqChildInst> qinqChildInst) {
		this.qinqChildInst = qinqChildInst;
	}

	public int getaAcPortId() {
		return aAcPortId;
	}

	public void setaAcPortId(int aAcPortId) {
		this.aAcPortId = aAcPortId;
	}

	public int getzAcPortId() {
		return zAcPortId;
	}

	public void setzAcPortId(int zAcPortId) {
		this.zAcPortId = zAcPortId;
	}

	public int getMinVlanId() {
		return minVlanId;
	}

	public void setMinVlanId(int minVlanId) {
		this.minVlanId = minVlanId;
	}

	public int getMaxVlanId() {
		return maxVlanId;
	}

	public void setMaxVlanId(int maxVlanId) {
		this.maxVlanId = maxVlanId;
	}

	public int getBasedInVlanId() {
		return basedInVlanId;
	}

	public void setBasedInVlanId(int basedInVlanId) {
		this.basedInVlanId = basedInVlanId;
	}

	public int getAcVlanIdRule() {
		return acVlanIdRule;
	}

	public void setAcVlanIdRule(int acVlanIdRule) {
		this.acVlanIdRule = acVlanIdRule;
	}
}
