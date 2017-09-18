package com.nms.db.bean.ptn.qos;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.db.enums.EQosDirection;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.ui.manager.ExceptionManage;

/**
 * qosinfo bean
 * @author kk
 *
 */
public class QosInfo extends QosCommonInfo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1339580926039072310L;
	private int siteId;		//网元ID
	private int groupId;	//自动生成，一组qos，此ID相同
	private int status;		//状态，1=设备不存在  2=设备存在
    private List<Acbuffer> bufferList = new ArrayList<Acbuffer>();
	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("id", this.getId());
			getClientProperties().put("seq", this.getSeq());
			if(null != getDirection() && !"".equals(getDirection())){
				if(Integer.parseInt(getDirection()) == EQosDirection.FORWARD.getValue()){
					getClientProperties().put("direction", EQosDirection.FORWARD.toString());
				}else{
					getClientProperties().put("direction", EQosDirection.BACKWARD.toString());
				}
			}
			
			getClientProperties().put("cos", QosCosLevelEnum.from(getCos()));
			getClientProperties().put("cir", this.getCir());
			getClientProperties().put("cbs", this.getCbs());
			getClientProperties().put("eir", this.getEir());
			getClientProperties().put("ebs", this.getEbs());
			getClientProperties().put("colorSence", this.getColorSence());
			getClientProperties().put("pir", this.getPir());
			getClientProperties().put("pbs", this.getPbs());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}

	public List<Acbuffer> getBufferList() {
		return bufferList;
	}

	public void setBufferList(List<Acbuffer> bufferList) {
		this.bufferList = bufferList;
	}
}
