package com.nms.db.bean.ptn.ecn;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

/**
 * ccn 管理 bean
 * @author sy
 *
 */
public class CCN extends ViewDataObj {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String neId = "";
	private String ccnName = "";
	private String admin = "";
	private String oper = "";
	private String ip = "";
	private String peer = "";
	private int mtu;
	private String portname;
	/**
	 * 激活状态
	 * 0 未激活
	 * 1 激活
	 */
	private int status;
	

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCcnName() {
		return ccnName;
	}

	public void setCcnName(String ccnName) {
		this.ccnName = ccnName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNeId() {
		return neId;
	}

	public void setNeId(String neId) {
		this.neId = neId;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPeer() {
		return peer;
	}

	public void setPeer(String peer) {
		this.peer = peer;
	}

	public int getMtu() {
		return mtu;
	}

	public void setMtu(int mtu) {
		this.mtu = mtu;
	}
	
	public String getPortname() {
		return portname;
	}

	public void setPortname(String portname) {
		this.portname = portname;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {

			getClientProperties().put("id", this.getId());
			getClientProperties().put("portname", "CCN/" + this.getCcnName());
			if(!"".equals(this.getAdmin())){
				getClientProperties().put("adminstatus", UiUtil.getCodeById(Integer.parseInt(this.getAdmin())).getCodeName());
			}
			if(!"".equals(this.getOper())){
				getClientProperties().put("workstatus",  super.getJobStatus(this.getOper()));
			}
			getClientProperties().put("interfacesip", this.getIp());
			getClientProperties().put("peerip", this.getPeer());
			getClientProperties().put("max", this.getMtu());
			getClientProperties().put("peer", this.getPortname());
			getClientProperties().put("status", this.getStatus()==0?false:true);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
