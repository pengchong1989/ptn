package com.nms.db.bean.ptn;

import com.nms.db.bean.ptn.path.eth.DualInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.ui.frame.ViewDataObj;

/**
 * 网元下所有类型的 倒换命令bean
 * @author sy
 *
 */
public class SiteRoate extends ViewDataObj {

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int siteId;
	private int typeId;//某个类型对应的 名称(): 注意：** tunnel存放的是保护ID
	private int roate;// 倒换命令 默认-1没有倒换命令； 0,1,2,3对应基本4个命令
	private String type;//类型
	/*
	 * 选中的tunnel对象。 倒换时，以便获取设备上tunnel的 名称
	 */
	private Tunnel tunnel;
	
//	private PwProtect pwProtect;
//	
//	public PwProtect getPwProtect() {
//		return pwProtect;
//	}
//
//	public void setPwProtect(PwProtect pwProtect) {
//		this.pwProtect = pwProtect;
//	}

	private DualInfo dualInfo;
	public DualInfo getDualInfo() {
		return dualInfo;
	}

	public void setDualInfo(DualInfo dualInfo) {
		this.dualInfo = dualInfo;
	}

	private int siteRoate;//武汉的 网元倒换：  陈晓没有此选项
	
	
	

	public Tunnel getTunnel() {
		return tunnel;
	}

	public void setTunnel(Tunnel tunnel) {
		this.tunnel = tunnel;
	}

	public int getSiteRoate() {
		return siteRoate;
	}

	public void setSiteRoate(int siteRoate) {
		this.siteRoate = siteRoate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getRoate() {
		return roate;
	}

	public void setRoate(int roate) {
		this.roate = roate;
	}

	@Override
	public void putObjectProperty() {
				
	}

}
