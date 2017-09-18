package com.nms.db.bean.ptn;

import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EServiceType;
import com.nms.ui.frame.ViewDataObj;
/**
 * 查询路由业务
 * @author dzy
 *
 */
public class RouteBusiness  extends ViewDataObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1477177180896531994L;
	private int busiId; //业务ID
	private String name;//业务名称
	private	int activeStatus;//激活状态
	private	int type;//业务类型
	private String aSiteName;//A网元名称
	private	String zSiteName;//Z网元名称
	private	String aPortName;//A端口名称
	private	String zPortName;//Z端口名称
	private String createTime;//创建时间

	
	@Override
	@SuppressWarnings("unchecked")
	public void putObjectProperty() {
		getClientProperties().put("name", this.getName());
		getClientProperties().put("activeStatus", EActiveStatus.forms(this.getActiveStatus()));
		getClientProperties().put("type", EServiceType.from(this.getType()));
		getClientProperties().put("aSiteName", this.getaSiteName());
		getClientProperties().put("zSiteName", this.getzSiteName());
		getClientProperties().put("aPortName", this.getaPortName());
		getClientProperties().put("zPortName", this.getzPortName());
		getClientProperties().put("createTime", this.getCreateTime());

	}

	public int getBusiId() {
		return busiId;
	}

	public void setBusiId(int busiId) {
		this.busiId = busiId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getaSiteName() {
		return aSiteName;
	}

	public void setaSiteName(String aSiteName) {
		this.aSiteName = aSiteName;
	}

	public String getzSiteName() {
		return zSiteName;
	}

	public void setzSiteName(String zSiteName) {
		this.zSiteName = zSiteName;
	}

	public String getaPortName() {
		return aPortName;
	}

	public void setaPortName(String aPortName) {
		this.aPortName = aPortName;
	}

	public String getzPortName() {
		return zPortName;
	}

	public void setzPortName(String zPortName) {
		this.zPortName = zPortName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
