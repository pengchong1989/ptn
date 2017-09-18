package com.nms.db.bean.ptn.path.tunnel;

import java.util.List;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.ui.frame.ViewDataObj;

public class OamMainInfo extends ViewDataObj {
	private static final long serialVersionUID = 2225052801700225070L;
	private int isReverse;//是否返回
	private int waitTime;//等待恢复时间
	private int delayTime;//拖延时间
	private int aMainOamEnable;//A端主用OAM使能
	private int zMainOamEnable;//Z端主用OAM使能
	private int mainCVEnable;//主用连通性检测
	private int aMainAPSEnable;//A端主用APS使能
	private int zMainAPSEnable;//Z端主用APS使能
	private int aReserveOamEnable;//A端备用OAM使能
	private int zReserveOamEnable;//Z端备用OAM使能
	private int reserveCVEnable;//备用连通性检测
	private int aReserveAPSEnable;//A端备用APS使能
	private int zReserveAPSEnable;//Z端备用APS使能
	private String tunnelType;//tunnel类型
	private List<OamInfo> oamInfoList = null;//主用oam
	private List<OamInfo> reserveOamInfoList = null;//备用oam
	private int aSiteId;
	public int getaSiteId() {
		return aSiteId;
	}


	public void setaSiteId(int aSiteId) {
		this.aSiteId = aSiteId;
	}


	public List<OamInfo> getOamInfoList() {
		return oamInfoList;
	}


	public void setOamInfoList(List<OamInfo> oamInfoList) {
		this.oamInfoList = oamInfoList;
	}


	public List<OamInfo> getReserveOamInfoList() {
		return reserveOamInfoList;
	}


	public void setReserveOamInfoList(List<OamInfo> reserveOamInfoList) {
		this.reserveOamInfoList = reserveOamInfoList;
	}

	public String getTunnelType() {
		return tunnelType;
	}


	public void setTunnelType(String tunnelType) {
		this.tunnelType = tunnelType;
	}


	public int getIsReverse() {
		return isReverse;
	}


	public void setIsReverse(int isReverse) {
		this.isReverse = isReverse;
	}


	public int getWaitTime() {
		return waitTime;
	}


	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}


	public int getDelayTime() {
		return delayTime;
	}


	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}


	public int getaMainOamEnable() {
		return aMainOamEnable;
	}


	public void setaMainOamEnable(int aMainOamEnable) {
		this.aMainOamEnable = aMainOamEnable;
	}


	public int getzMainOamEnable() {
		return zMainOamEnable;
	}


	public void setzMainOamEnable(int zMainOamEnable) {
		this.zMainOamEnable = zMainOamEnable;
	}


	public int getMainCVEnable() {
		return mainCVEnable;
	}


	public void setMainCVEnable(int mainCVEnable) {
		this.mainCVEnable = mainCVEnable;
	}


	public int getaMainAPSEnable() {
		return aMainAPSEnable;
	}


	public void setaMainAPSEnable(int aMainAPSEnable) {
		this.aMainAPSEnable = aMainAPSEnable;
	}


	public int getzMainAPSEnable() {
		return zMainAPSEnable;
	}


	public void setzMainAPSEnable(int zMainAPSEnable) {
		this.zMainAPSEnable = zMainAPSEnable;
	}


	public int getaReserveOamEnable() {
		return aReserveOamEnable;
	}


	public void setaReserveOamEnable(int aReserveOamEnable) {
		this.aReserveOamEnable = aReserveOamEnable;
	}


	public int getzReserveOamEnable() {
		return zReserveOamEnable;
	}


	public void setzReserveOamEnable(int zReserveOamEnable) {
		this.zReserveOamEnable = zReserveOamEnable;
	}


	public int getReserveCVEnable() {
		return reserveCVEnable;
	}


	public void setReserveCVEnable(int reserveCVEnable) {
		this.reserveCVEnable = reserveCVEnable;
	}


	public int getaReserveAPSEnable() {
		return aReserveAPSEnable;
	}


	public void setaReserveAPSEnable(int aReserveAPSEnable) {
		this.aReserveAPSEnable = aReserveAPSEnable;
	}


	public int getzReserveAPSEnable() {
		return zReserveAPSEnable;
	}


	public void setzReserveAPSEnable(int zReserveAPSEnable) {
		this.zReserveAPSEnable = zReserveAPSEnable;
	}

	@Override
	public void putObjectProperty() {
		//如果是1:1
		if(this.getTunnelType().equals("186")){
			this.putClientProperty("isReverse", this.getIsReverse() == 0 ? Boolean.FALSE : Boolean.TRUE);//是否返回
			this.putClientProperty("waitTime", this.getWaitTime());//等待恢复时间
			this.putClientProperty("delayTime", this.getDelayTime());//拖延时间
			if(this.getReserveOamInfoList() != null && this.getReserveOamInfoList().size() > 1){
				OamMepInfo aOamMep = this.getReserveOamInfoList().get(0).getOamMep();
				OamMepInfo zOamMep = null;
				if(aOamMep.getSiteId() == this.getaSiteId()){
					zOamMep = this.getReserveOamInfoList().get(1).getOamMep();
				}else{
					aOamMep = this.getReserveOamInfoList().get(1).getOamMep();
					zOamMep = this.getReserveOamInfoList().get(0).getOamMep();
				}
				this.putClientProperty("aReserveOamEnable", aOamMep.isOamEnable());//A端备用OAM使能
				this.putClientProperty("zReserveOamEnable", zOamMep.isOamEnable());//Z端备用OAM使能
				this.putClientProperty("reserveCVEnable", aOamMep.isCv());//备用连通性检测
				this.putClientProperty("aReserveAPSEnable", aOamMep.isAps());//A端备用APS使能
				this.putClientProperty("zReserveAPSEnable", zOamMep.isAps());//Z端备用APS使能
			}
		}
		if(this.getOamInfoList() != null && this.getOamInfoList().size() > 1){
			OamMepInfo aOamMep = this.getOamInfoList().get(0).getOamMep();
			OamMepInfo zOamMep = null;
			if(aOamMep.getSiteId() == this.getaSiteId()){
				zOamMep = this.getOamInfoList().get(1).getOamMep();
			}else{
				aOamMep = this.getOamInfoList().get(1).getOamMep();
				zOamMep = this.getOamInfoList().get(0).getOamMep();
			}
			this.putClientProperty("aMainOamEnable", aOamMep.isOamEnable());//A端主用OAM使能
			this.putClientProperty("zMainOamEnable", zOamMep.isOamEnable());//Z端主用OAM使能
			this.putClientProperty("mainCVEnable", aOamMep.isCv());//主用连通性检测
			this.putClientProperty("aMainAPSEnable", aOamMep.isAps());//A端主用APS使能
			this.putClientProperty("zMainAPSEnable", zOamMep.isAps());//Z端主用APS使能
		}
	}
}
