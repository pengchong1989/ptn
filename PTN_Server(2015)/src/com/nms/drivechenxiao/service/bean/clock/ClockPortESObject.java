package com.nms.drivechenxiao.service.bean.clock;
//时钟管理     频率管理   时钟源配置状态  
public class ClockPortESObject {
	private String portname;// 端口
	private String clockType;//时钟类型
	//-----以下2个是端口属性
	private String admin  ; //manageState;// 管理状态
	private String oper   ;//jobState;// 工作状态
	private String dnugroup;//port// DNU组 int [0,255]
	private String ssmoutputenable ; //port//SSMSend;// SSM发送使能  bool
	private String internal;// 外部命令
	private String speed ; //速率 100m=0,1g=1,10=2,neg100m=3,neg1g=4,2g5=5
	//----以下几个是端口下clock属性
	private Boolean isClock = false;
	private String scspri ;//SCS优先级 int [0,255] 系统优先级
	private String ecspri ;//ECS优先级  int [0,255] 导出优先级
	private String scslockout ; //SCS闭锁bool
	private String ecslockout ; //ECS闭锁bool
	private String forcelevel ; //强制等级 ;选择器质量等级; 1:prc 2:ssua 3:ssub 4:sec 5:dnu 6:auto ------ 收SSM设置值
//	g_811时钟 对应 ql_prc
//	g_812本地时钟 对应 ql_ssu-a
//	g_811转接时钟 对应 ql_ssu-b
//	g_813时钟 对应 ql_sec
//	不可用作同步 对应  ql_inv
//	实际线路接收值 对应  ql_dnu
	private String PhysicalState ; //物理状态 ; fail=0 失败; normal=1 正常; wtr=2 等待恢复; na=3 无状态信息
	private String SCSLogicalState ; //SCS逻辑状态 ; fail=0 ; normal=1 ; wtr=2 ; na=3
	private String CSLogicalState ; //ECS逻辑状态 ; fail=0 ; normal=1 ; wtr=2 ; na=3
	private String SSMCode ; //SSM值 收取SSM设置值    ------收SSM实际值
	private String SCSQL ; //SCS质量等级  ----预留待定,
	private String ECSQL ; //ECS质量等级  -----选择器质量等级

	//更新.1106
//	/添加
	private String scsmanual ;//系统选择;bool , false
	private String ecsmanual ;//人工选择;bool , false
	private String scsforce ;//系统强制选择 ,bool , false
	private String ecsforce ;//人工强制选择 ,bool , false
	
	//-------------------------
//	private String //systemPriorLevel;//系统时钟优先等级
//	private String exportPriorLevel;// 导出时钟优先等级
//	private String physicsState;// 物理状态
//	private String logicState;// 逻辑状态
//	private int receiveSSMValue;// 收取SSM设置值
//	private int receiveSSMRealityValue;// 收取SSM实际值
	
//	private String selectQuelityLevel;// 选择器质量等级
//	private String manageState;// 管理状态
//	private String jobState;// 工作状态	
	
	
	private String recoverModel;// 恢复模式  //这个是只有PDH口才有的  
										//PDH下面的clock下面的recoverymode ; edm=0 (从输入tdm信号中恢复),emu=1(从方针业务中恢复) ,
	//
	
	
	public String getPortname() {
		return portname;
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

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public Boolean getIsClock() {
		return isClock;
	}

	public void setIsClock(Boolean isClock) {
		this.isClock = isClock;
	}

	public void setPortname(String portname) {
		this.portname = portname;
	}

	public String getClockType() {
		return clockType;
	}

	public void setClockType(String clockType) {
		this.clockType = clockType;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public String getDnugroup() {
		return dnugroup;
	}

	public void setDnugroup(String dnugroup) {
		this.dnugroup = dnugroup;
	}

	public String getSsmoutputenable() {
		return ssmoutputenable;
	}

	public void setSsmoutputenable(String ssmoutputenable) {
		this.ssmoutputenable = ssmoutputenable;
	}

	public String getInternal() {
		return internal;
	}

	public void setInternal(String internal) {
		this.internal = internal;
	}

	public String getScspri() {
		return scspri;
	}

	public void setScspri(String scspri) {
		this.scspri = scspri;
	}

	public String getEcspri() {
		return ecspri;
	}

	public void setEcspri(String ecspri) {
		this.ecspri = ecspri;
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

	public String getForcelevel() {
		return forcelevel;
	}

	public void setForcelevel(String forcelevel) {
		this.forcelevel = forcelevel;
	}

	public String getPhysicalState() {
		return PhysicalState;
	}

	public void setPhysicalState(String physicalState) {
		PhysicalState = physicalState;
	}

	public String getSCSLogicalState() {
		return SCSLogicalState;
	}

	public void setSCSLogicalState(String logicalState) {
		SCSLogicalState = logicalState;
	}

	public String getCSLogicalState() {
		return CSLogicalState;
	}

	public void setCSLogicalState(String logicalState) {
		CSLogicalState = logicalState;
	}

	public String getSSMCode() {
		return SSMCode;
	}

	public void setSSMCode(String code) {
		SSMCode = code;
	}

	public String getSCSQL() {
		return SCSQL;
	}

	public void setSCSQL(String scsql) {
		SCSQL = scsql;
	}

	public String getECSQL() {
		return ECSQL;
	}

	public void setECSQL(String ecsql) {
		ECSQL = ecsql;
	}

	public String getRecoverModel() {
		return recoverModel;
	}

	public void setRecoverModel(String recoverModel) {
		this.recoverModel = recoverModel;
	}


	
	
}
