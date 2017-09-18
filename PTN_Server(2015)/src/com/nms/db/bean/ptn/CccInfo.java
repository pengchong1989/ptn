package com.nms.db.bean.ptn;

import java.util.List;

import com.nms.db.bean.ptn.path.ServiceInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;

public class CccInfo extends ServiceInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4085185658317652996L;
	private int aXcId;//VPSID
	private List<AcPortInfo> beforeAc=null;	//修改之前的根AC对象
	



	public int getaXcId() {
		return aXcId;
	}

	public void setaXcId(int aXcId) {
		this.aXcId = aXcId;
	}

	
	@Override
	public void putObjectProperty() {
		try {
			this.putClientProperty("id", getId());
			this.putClientProperty("cccName", getName());
			this.putClientProperty("activeStates", getActiveStatus() == 1 ? EActiveStatus.ACTIVITY.toString() : EActiveStatus.UNACTIVITY.toString());
			this.putClientProperty("creater", getCreateUser());
			this.putClientProperty("createTime", DateUtil.strDate(getCreateTime(), DateUtil.FULLTIME));			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public List<AcPortInfo> getBeforeAc() {
		return beforeAc;
	}

	public void setBeforeAc(List<AcPortInfo> beforeAc) {
		this.beforeAc = beforeAc;
	}
	
}
