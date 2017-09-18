package com.nms.db.bean.ptn.path.protect;

import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EProtectStatusType;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;

/**
 * MSP保护bean
 * 
 * @author kk
 * 
 */
public class MspProtect extends ViewDataObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7452000242678623497L;
	private int id; // 主键id,自增
	private int businessId; // 设备名称，关联businessId表
	private int protectType; // 保护类型，关联code表主键
	private int recoveryMode; // 恢复模式，关联code表主键
	private int workPortId; // 工作端口，关联port表主键
	private int ProtectPortId; // 保护端口，关联port表主键
	private int WaitTime; // 恢复等待时间(min)
	private int delayTime; // 延迟时间(ms)
	private int sfPriority; // Sf优先级 关联code表主键
	private int sdPriority; // SD优先级 关联code表主键
	private int apsEnable; // APS使能 0=false 1=true
	private int sdEnable; // SD使能 0=false 1=true
	private String ProtectStatus; // 保护状态
	private int NowWorkPortId; // 当前工作端口
	private int siteId; // 网元主键
	private int rotateOrder=-1 ; //外部倒换命令   参考ERotateType---(-1 为更创建msp时添加的倒换命令，即默认的，没有倒换操作命令)
	private int mspStatus ; //激活状态  1=激活，2=未激活
	private String name;	//名称
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBusinessId() {
		return businessId;
	}

	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}

	public int getProtectType() {
		return protectType;
	}

	public void setProtectType(int protectType) {
		this.protectType = protectType;
	}

	public int getRecoveryMode() {
		return recoveryMode;
	}

	public void setRecoveryMode(int recoveryMode) {
		this.recoveryMode = recoveryMode;
	}

	public int getWorkPortId() {
		return workPortId;
	}

	public void setWorkPortId(int workPortId) {
		this.workPortId = workPortId;
	}

	public int getProtectPortId() {
		return ProtectPortId;
	}

	public void setProtectPortId(int protectPortId) {
		ProtectPortId = protectPortId;
	}

	public int getWaitTime() {
		return WaitTime;
	}

	public void setWaitTime(int waitTime) {
		WaitTime = waitTime;
	}

	public int getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	public int getSfPriority() {
		return sfPriority;
	}

	public void setSfPriority(int sfPriority) {
		this.sfPriority = sfPriority;
	}

	public int getSdPriority() {
		return sdPriority;
	}

	public void setSdPriority(int sdPriority) {
		this.sdPriority = sdPriority;
	}

	public int getApsEnable() {
		return apsEnable;
	}

	public void setApsEnable(int apsEnable) {
		this.apsEnable = apsEnable;
	}

	public int getSdEnable() {
		return sdEnable;
	}

	public void setSdEnable(int sdEnable) {
		this.sdEnable = sdEnable;
	}

	public String getProtectStatus() {
		return ProtectStatus;
	}

	public void setProtectStatus(String protectStatus) {
		ProtectStatus = protectStatus;
	}

	public int getNowWorkPortId() {
		return NowWorkPortId;
	}

	public void setNowWorkPortId(int nowWorkPortId) {
		NowWorkPortId = nowWorkPortId;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("id", getId());
			getClientProperties().put("name", this.getName());
			getClientProperties().put("type", UiUtil.getCodeById(this.getProtectType()).getCodeName());
			getClientProperties().put("model", UiUtil.getCodeById(this.getRecoveryMode()).getCodeName());
			getClientProperties().put("waitTime", this.getWaitTime());
			getClientProperties().put("delay", this.getDelayTime());
			if(0==this.getRotateOrder()){
				getClientProperties().put("rotateOrder", ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_FORCEJOB));
			}else if(1==this.getRotateOrder()){
				getClientProperties().put("rotateOrder",ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_FORCEPRO));
			}else if(2==this.getRotateOrder()){
				getClientProperties().put("rotateOrder", ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_MANPOWERJOB));
			}else if(3==this.getRotateOrder()){
				getClientProperties().put("rotateOrder",  ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_MANPOWERPRO));
			}else if(4==this.getRotateOrder()){
				getClientProperties().put("rotateOrder",ResourceUtil.srcStr(StringKeysBtn.BTN_CLEAR));
			}else if(5==this.getRotateOrder()){
				getClientProperties().put("rotateOrder",ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_SHUTTING));
			}
			
//			else if(7==this.getRotateOrder()){
//				getClientProperties().put("rotateOrder", "倒换练习到工作侧");
//			}else if(8==this.getRotateOrder()){
//				getClientProperties().put("rotateOrder", "倒换练习到工作侧");
//			}
			getClientProperties().put("sfpriority", UiUtil.getCodeById(this.getSfPriority()).getCodeName());
			getClientProperties().put("sdpriority", UiUtil.getCodeById(this.getSdPriority()).getCodeName());
			getClientProperties().put("apsenabled", this.getApsEnable() == 0 ? false : true);
			getClientProperties().put("sdenabled", this.getSdEnable() == 0 ? false : true);
			if(null!=this.getProtectStatus()&&!"".equals(this.getProtectStatus())){
				getClientProperties().put("protectStatus", EProtectStatusType.from(Integer.parseInt(this.getProtectStatus())));
			}
			getClientProperties().put("activeStatus", this.getMspStatus() == EActiveStatus.ACTIVITY.getValue() ? EActiveStatus.ACTIVITY.toString() : EActiveStatus.UNACTIVITY.toString());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public int getRotateOrder() {
		return rotateOrder;
	}

	public void setRotateOrder(int rotateOrder) {
		this.rotateOrder = rotateOrder;
	}

	public int getMspStatus() {
		return mspStatus;
	}

	public void setMspStatus(int mspStatus) {
		this.mspStatus = mspStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
