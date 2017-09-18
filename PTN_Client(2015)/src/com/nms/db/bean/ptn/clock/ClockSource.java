package com.nms.db.bean.ptn.clock;
import java.io.Serializable;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
public class ClockSource extends ViewDataObj implements Serializable{
	
	private static final long serialVersionUID = 6831633694593735459L;
	private int id;
	private int siteId;// 关联网元表主键
	private int port;// 端口
	private int clockType;//时钟类型
	private String systemPriorLevel;//系统时钟优先等级
	private String exportPriorLevel;// 导出时钟优先等级
	private String physicsState;// 物理状态
	private String logicState;// 逻辑状态
	private int receiveSSMValue;// 收取SSM设置值
	private int receiveSSMRealityValue;// 收取SSM实际值
	private int SSMSend;// SSM发送使能
	private String selectQuelityLevel;// 选择器质量等级
	private String DNUGroup;// DNU组
	private int recoverModel;// 恢复模式
	private String externalOrder;// 外部命令
	private String manageState;// 管理状态
	private String jobState;// 工作状态
	private int activeStatus;
	/*
	 * 修改，时钟源配置，倒换------3、6
	 */
	private String scslockout="" ; //SCS闭锁bool
	private String ecslockout =""; //ECS闭锁bool

	private String scsmanual="" ;//系统选择;bool , false
	private String ecsmanual ="";//人工选择;bool , false
	private String scsforce ="";//系统强制选择 ,bool , false
	private String ecsforce ="";//人工强制选择 ,bool , false
	
	@Override
	public void putObjectProperty() {
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			this.putClientProperty("id", getId());
			this.putClientProperty("port", portService.getPortname(this.port));
			this.putClientProperty("clockType",UiUtil.getCodeById(getClockType()).getCodeName());
			this.putClientProperty("priority",getSystemPriorLevel());
			String physicalStatusName="";//物理状态
			//System.out.println(getPhysicsState());
			if("0".equals(getPhysicsState())){
				physicalStatusName="失败";
			}else if("1".equals(getPhysicsState())){
				physicalStatusName="正常";
			}else if("2".equals(getPhysicsState())){
				physicalStatusName="等待恢复";
			}else if("3".equals(getPhysicsState())){
				physicalStatusName="无正常信息";
			}
			this.putClientProperty("physicalStatus",physicalStatusName);
			//System.out.println(getLogicState());
			String logicalStatusName="";//逻辑状态
			if("0".equals(getLogicState())){
				logicalStatusName="失败";
			}else if("1".equals(getLogicState())){
				logicalStatusName="正常";
			}else if("2".equals(getLogicState())){
				logicalStatusName="等待恢复";
			}else if("3".equals(getLogicState())){
				logicalStatusName="无正常信息";
			}
			//System.out.println(this.getExternalOrder());
			this.putClientProperty("logicalStatus",logicalStatusName);
			this.putClientProperty("SSMSettingValue",UiUtil.getCodeById(this.getReceiveSSMValue()).getCodeName());
			this.putClientProperty("SSMActualValue",getReceiveSSMRealityValue()==0?"-":" 不可用作同步");
			this.putClientProperty("SSMSendingEnabled",getSSMSend()==0?ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO) : ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED));
			this.putClientProperty("SelectorQualityLevel",getSelectQuelityLevel());
			this.putClientProperty("DNUGroup",getDNUGroup());
			this.putClientProperty("RecoveryMode",this.getRecoverModel()==0?"-":UiUtil.getCodeById(this.getRecoverModel()).getCodeName());
			String order="";
			if(this.getExternalOrder()!=null){
				if(this.getExternalOrder().equals("force")){
					order=ResourceUtil.srcStr(StringKeysBtn.BTL_FORCETUNNEL);
				}else if(this.getExternalOrder().equals("manpower")){
					order=ResourceUtil.srcStr(StringKeysBtn.BTL_MANPOWERTUNNEL);
				}else if(this.getExternalOrder().equals("lock")){
					order=ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_SHUTTING);
				}
			}
			
			this.putClientProperty("External Order",order);
			//this.putClientProperty("manageState",getManageState() .equals("1")?"使能":"不使能");
			//this.putClientProperty("jobState",super.getJobStatus(this.getJobState()));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
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

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getClockType() {
		return clockType;
	}

	public void setClockType(int clockType) {
		this.clockType = clockType;
	}


	public String getSystemPriorLevel() {
		return systemPriorLevel;
	}

	public void setSystemPriorLevel(String systemPriorLevel) {
		this.systemPriorLevel = systemPriorLevel;
	}

	public String getExportPriorLevel() {
		return exportPriorLevel;
	}

	public void setExportPriorLevel(String exportPriorLevel) {
		this.exportPriorLevel = exportPriorLevel;
	}

	public String getPhysicsState() {
		return physicsState;
	}

	public void setPhysicsState(String physicsState) {
		this.physicsState = physicsState;
	}

	public String getLogicState() {
		return logicState;
	}

	public void setLogicState(String logicState) {
		this.logicState = logicState;
	}

	public int getReceiveSSMValue() {
		return receiveSSMValue;
	}

	public void setReceiveSSMValue(int receiveSSMValue) {
		this.receiveSSMValue = receiveSSMValue;
	}

	public int getReceiveSSMRealityValue() {
		return receiveSSMRealityValue;
	}

	public void setReceiveSSMRealityValue(int receiveSSMRealityValue) {
		this.receiveSSMRealityValue = receiveSSMRealityValue;
	}

	public int getSSMSend() {
		return SSMSend;
	}

	public void setSSMSend(int sSMSend) {
		SSMSend = sSMSend;
	}

	public String getSelectQuelityLevel() {
		return selectQuelityLevel;
	}

	public void setSelectQuelityLevel(String selectQuelityLevel) {
		this.selectQuelityLevel = selectQuelityLevel;
	}

	public String getDNUGroup() {
		return DNUGroup;
	}

	public void setDNUGroup(String dNUGroup) {
		DNUGroup = dNUGroup;
	}

	public int getRecoverModel() {
		return recoverModel;
	}

	public void setRecoverModel(int recoverModel) {
		this.recoverModel = recoverModel;
	}

	public String getExternalOrder() {
		return externalOrder;
	}

	public void setExternalOrder(String externalOrder) {
		this.externalOrder = externalOrder;
	}

	public String getManageState() {
		return manageState;
	}

	public void setManageState(String manageState) {
		this.manageState = manageState;
	}

	public String getJobState() {
		return jobState;
	}

	public void setJobState(String jobState) {
		this.jobState = jobState;
	}

	public int getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getScslockout() {
		return scslockout;
	}

	public void setScslockout(String scslockout) {
		this.scslockout = scslockout;
	}

	public String getEcslockout() {
		return ecslockout;
	}

	public void setEcslockout(String ecslockout) {
		this.ecslockout = ecslockout;
	}

	public String getScsmanual() {
		return scsmanual;
	}

	public void setScsmanual(String scsmanual) {
		this.scsmanual = scsmanual;
	}

	public String getEcsmanual() {
		return ecsmanual;
	}

	public void setEcsmanual(String ecsmanual) {
		this.ecsmanual = ecsmanual;
	}

	public String getScsforce() {
		return scsforce;
	}

	public void setScsforce(String scsforce) {
		this.scsforce = scsforce;
	}

	public String getEcsforce() {
		return ecsforce;
	}

	public void setEcsforce(String ecsforce) {
		this.ecsforce = ecsforce;
	}


}
