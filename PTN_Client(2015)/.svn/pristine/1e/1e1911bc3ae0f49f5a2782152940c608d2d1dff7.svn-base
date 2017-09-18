package com.nms.ui.ptn.business.dialog.cespath;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;

/**
 * cesPathPanel面板中baseInfoTable表对象
 * @author Administrator
 *
 */
public class CesTableObj {

	private int id;
	private int num;
	private String name;
	private String activeStatus;
	private String pwName;
	private String asiteName;
	private String zsiteName;
	private String aportName;
	private String zportName;
	private String createTime;
	private String createUser;

	public CesTableObj() {
	}

	public CesTableObj(int id, int num, String name, String activeStatus, String pwName, String asiteName, String zsiteName, String aportName, String zportName, String createTime, String createUser) {
		this.id = id;
		this.num = num;
		this.name = name;
		this.activeStatus = activeStatus;
		this.pwName = pwName;
		this.asiteName = asiteName;
		this.zsiteName = zsiteName;
		this.aportName = aportName;
		this.zportName = zportName;
		this.createTime = createTime;
		this.createUser = createUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getPwName() {
		return pwName;
	}

	public void setPwName(String pwName) {
		this.pwName = pwName;
	}

	public String getAsiteName() {
		return asiteName;
	}

	public void setAsiteName(String asiteName) {
		this.asiteName = asiteName;
	}

	public String getZsiteName() {
		return zsiteName;
	}

	public void setZsiteName(String zsiteName) {
		this.zsiteName = zsiteName;
	}

	public String getAportName() {
		return aportName;
	}

	public void setAportName(String aportName) {
		this.aportName = aportName;
	}

	public String getZportName() {
		return zportName;
	}

	public void setZportName(String zportName) {
		this.zportName = zportName;
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

	//返回table表头列名
	public  String[] getColumnIdentifiers() {
		return new String[] { ResourceUtil.srcStr(StringKeysObj.ORDER_NUM), ResourceUtil.srcStr(StringKeysObj.STRING_ID), ResourceUtil.srcStr(StringKeysLbl.LBL_NAME), ResourceUtil.srcStr(StringKeysLbl.LBL_ACTIVITY_STATUS), ResourceUtil.srcStr(StringKeysLbl.LBL_PW_NAME), ResourceUtil.srcStr(StringKeysObj.STRING_A_SITE_NAME), ResourceUtil.srcStr(StringKeysObj.STRING_Z_SITE_NAME), ResourceUtil.srcStr(StringKeysObj.STRING_A_PORT_NAME), ResourceUtil.srcStr(StringKeysObj.STRING_Z_PORT_NAME), ResourceUtil.srcStr(StringKeysObj.STRING_CREATE_TIME), ResourceUtil.srcStr(StringKeysObj.STRING_FOUNDER) };
	}

	public Object[] getValueObject() {
		return new Object[] { this.getId(), this.getNum(), this.getName(), this.getActiveStatus(), this.getPwName(), this.getAsiteName(), this.getZsiteName(), this.getAportName(), this.getZportName(), this.getCreateTime(), this.getCreateUser() };
	}

	public  boolean[] getCellEditable() {
		return new boolean[] { false, false, false, false, false, false, false, false, false, false, false };
	}

}
