package com.nms.db.bean.ptn;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

public class ProtectStatus extends ViewDataObj{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3379995048715536190L;
	private String name;//保护名称
	private int protectType;//保护类型
	private String mainPort;//主用端口
	private int mainLspAlarm;//主用lsp告警
	private String standPort;//备用端口
	private int standLspAlarm;//备用lsp告警
	private int rorateStatus;//倒换状态
	private int delaytime;//拖延时间
	private int receiveAps;//接收APS
	private int launchAps;//发送APS
	private int backType;//返回类型
	private int isWorking;//当前工作路径
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProtectType() {
		return protectType;
	}
	public void setProtectType(int protectType) {
		this.protectType = protectType;
	}
	public String getMainPort() {
		return mainPort;
	}
	public void setMainPort(String mainPort) {
		this.mainPort = mainPort;
	}
	public int getMainLspAlarm() {
		return mainLspAlarm;
	}
	public void setMainLspAlarm(int mainLspAlarm) {
		this.mainLspAlarm = mainLspAlarm;
	}
	public String getStandPort() {
		return standPort;
	}
	public void setStandPort(String standPort) {
		this.standPort = standPort;
	}
	public int getStandLspAlarm() {
		return standLspAlarm;
	}
	public void setStandLspAlarm(int standLspAlarm) {
		this.standLspAlarm = standLspAlarm;
	}
	public int getRorateStatus() {
		return rorateStatus;
	}
	public void setRorateStatus(int rorateStatus) {
		this.rorateStatus = rorateStatus;
	}
	public int getDelaytime() {
		return delaytime;
	}
	public void setDelaytime(int delaytime) {
		this.delaytime = delaytime;
	}
	public int getReceiveAps() {
		return receiveAps;
	}
	public void setReceiveAps(int receiveAps) {
		this.receiveAps = receiveAps;
	}
	public int getLaunchAps() {
		return launchAps;
	}
	public void setLaunchAps(int launchAps) {
		this.launchAps = launchAps;
	}
	public int getBackType() {
		return backType;
	}
	public void setBackType(int backType) {
		this.backType = backType;
	}
	
	public int getIsWorking() {
		return isWorking;
	}
	public void setIsWorking(int isWorking) {
		this.isWorking = isWorking;
	}
	@Override
	public void putObjectProperty() {
		this.putClientProperty("name", this.getName());
		this.putClientProperty("protectType", "1:1");
		this.putClientProperty("mainPort", this.getMainPort());
		this.putClientProperty("mainLspAlarm", this.getMainLspAlarm()== 0?"无":"有");
		this.putClientProperty("standPort", this.getStandPort());
		this.putClientProperty("standLspAlarm", this.getStandLspAlarm()==0?"无":"有");
		this.putClientProperty("delaytime", this.getDelaytime());
		this.putClientProperty("receiveAps", Integer.toHexString(this.getReceiveAps()));
		this.putClientProperty("launchAps", Integer.toHexString(this.getLaunchAps()));
		try {
			this.putClientProperty("rorateStatus",(UiUtil.getCodeByValue("RORATESTATUS", this.getRorateStatus()+"")).getCodeName());
			this.putClientProperty("backType", (UiUtil.getCodeByValue("BACKTYPE", this.getBackType()+"")).getCodeName());
			this.putClientProperty("isWorking", this.getIsWorking()==0?ResourceUtil.srcStr(StringKeysLbl.LBL_WORKING_PATH):ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_PROTECT_PATH));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
}
