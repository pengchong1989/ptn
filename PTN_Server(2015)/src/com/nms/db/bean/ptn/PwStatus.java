package com.nms.db.bean.ptn;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysObj;

public class PwStatus extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7885914329915014623L;
	private String pwName;
	private String tunnelName;
	private int inlable;
	private int outlable;
	private int cvEnable;
	private int cvCircle;
	private int isLoc;
	private int isRdi;
	private int siteId;
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public String getPwName() {
		return pwName;
	}
	public void setPwName(String pwName) {
		this.pwName = pwName;
	}
	public String getTunnelName() {
		return tunnelName;
	}
	public void setTunnelName(String tunnelName) {
		this.tunnelName = tunnelName;
	}
	public int getInlable() {
		return inlable;
	}
	public void setInlable(int inlable) {
		this.inlable = inlable;
	}
	public int getOutlable() {
		return outlable;
	}
	public void setOutlable(int outlable) {
		this.outlable = outlable;
	}
	public int getCvEnable() {
		return cvEnable;
	}
	public void setCvEnable(int cvEnable) {
		this.cvEnable = cvEnable;
	}
	public int getCvCircle() {
		return cvCircle;
	}
	public void setCvCircle(int cvCircle) {
		this.cvCircle = cvCircle;
	}
	public int getIsLoc() {
		return isLoc;
	}
	public void setIsLoc(int isLoc) {
		this.isLoc = isLoc;
	}
	public int getIsRdi() {
		return isRdi;
	}
	public void setIsRdi(int isRdi) {
		this.isRdi = isRdi;
	}
	@Override
	public void putObjectProperty() {
		this.putClientProperty("pwname",getPwName());
		this.putClientProperty("inlable", getInlable());
		this.putClientProperty("outlable", getOutlable());
		this.putClientProperty("lspname", getTunnelName());
		this.putClientProperty("cvenalbe", getCvEnable()==0? ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO) : ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED));
		this.putClientProperty("cvcircle", cvCircle(getCvCircle()));
		this.putClientProperty("loc", getIsLoc()==0?ResourceUtil.srcStr(StringKeysObj.LSP_TYPE_NO):ResourceUtil.srcStr(StringKeysObj.LSP_TYPE_YES));
		this.putClientProperty("rdi", getIsRdi()==0?ResourceUtil.srcStr(StringKeysObj.LSP_TYPE_NO):ResourceUtil.srcStr(StringKeysObj.LSP_TYPE_YES));
		
	}
	
	public String cvCircle(int cvCircle){
		String str = "";
		if(cvCircle == 1){
			str = "3.3ms";
		}else if(cvCircle == 2){
			str = "10ms";
		}else if(cvCircle == 3){
			str = "100ms";
		}else if(cvCircle == 4){
			str = "1000ms";
		}
		return str;
	}
}
