package com.nms.drive.service.bean;

/**
 * 
 * PwObject PW配置块
 * @author 彭冲
 *
 */
public class PwObject {

	private int pwId = 0;// ID:1-512
	private int pwType = 1;//ETH/PDH/SDH=1/2/3
	private int tunnelId = 0;// ID:1-512
	private int inLable = 0;// 入标签
	private int outLable = 0;// 出标签
	private int model = 0;// 模式 (0)/1/2/=(不使能)/trTCM/Modified trTCM)
	private int cir = 0;//CIR
	private int pir = 0;//PIR
	private int cbs = 0;
	private int pbs = 0;
	private int cm = 0;
	private int phbtoexpstrategy = 0;//(PHB到VC/VP EXP映射),(0)/1=(指配EXP)/基于PHB到VC|VP EXP映射表
	private int exp = 0;//(PHB到VC/VP EXP映射)指配EXP:0/1/2/3/4/5/6/7
	private int expid = 1;//(PHB到VC/VP EXP映射)PHB2EXP_ID:1-15
	private int exptophbstrategy = 0;//(VP EXP到PHB映射)策略,(0)/1=(指配PHB)/基于TMP EXP 到PHB映射表
	private int phb = 0;//(VP EXP到PHB映射)指配PHB,(0)/1/2/3/4/5/6/7=(BE)/AF1/AF2/AF3/AF4/EF/CS6/CS7
	private int phbid = 1;//(VP EXP到PHB映射)EXP2PHB_ID:1-15
	private int vcEnabl = 0;//VC OAM使能:(0)/1=(不使能)/使能
	private int mel = 7;//MEL ：0/1/2..6/7=0/1/2..6/7
	private int sourceMEP = 1;//源MEP ID:0-1-8191界面10进制显示
	private int equityMEP = 1;//对等MEP ID:0-1-8191,界面10进制显示
	
	private String cvEnabl = "0";// CV帧使能：0/1=不使能/使能
	private String cvCycle = "001";// CV帧发送周期:001/010/011/100=3.33ms/10ms/100ms/1s
	private String cvReserve = "0000";//CV备用
	
	private String csfEnabl = "0";// CSF帧使能：0/1=不使能/使能
	private String csfcycle = "000";// CSF帧发送周期:001/000=60s/1s
	private String csfreserve = "0000";//CSF备用
	
	private String megIcc = "49,50,51,52,53,54";// MEG ICC :123456 (字符方式显示,左侧字符对齐)
	private String megUmc = "55,56,57,97,98,99";// MEG UMC:789ABC (字符方式显示,左侧字符对齐)
	
	private int lspTc = 0;//lsptc
	private int pwTc = 0;//pwtc
	private int lm = 0;//LM
	
	private int vlanEnable;//外层VLAN使能
	private int outVlanValue;//外层vlan值
	private String sourceMac ="00-00-00-00-00-00";//源mac
	private String targetMac ="00-00-00-00-00-00";//目的mac
	private int tp_id;//外层TP_ID
	private int role;
	private int oamEnable;//oam使能
	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	public int getTp_id() {
		return tp_id;
	}

	public void setTp_id(int tpId) {
		tp_id = tpId;
	}

	public int getLm() {
		return lm;
	}

	public void setLm(int lm) {
		this.lm = lm;
	}

	public int getPwType() {
		return pwType;
	}

	public void setPwType(int pwType) {
		this.pwType = pwType;
	}

	public int getPhbtoexpstrategy() {
		return phbtoexpstrategy;
	}

	public void setPhbtoexpstrategy(int phbtoexpstrategy) {
		this.phbtoexpstrategy = phbtoexpstrategy;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getExpid() {
		return expid;
	}

	public void setExpid(int expid) {
		this.expid = expid;
	}

	public int getExptophbstrategy() {
		return exptophbstrategy;
	}

	public void setExptophbstrategy(int exptophbstrategy) {
		this.exptophbstrategy = exptophbstrategy;
	}

	public int getPhb() {
		return phb;
	}

	public void setPhb(int phb) {
		this.phb = phb;
	}

	public int getPhbid() {
		return phbid;
	}

	public void setPhbid(int phbid) {
		this.phbid = phbid;
	}

	public int getVcEnabl() {
		return vcEnabl;
	}

	public void setVcEnabl(int vcEnabl) {
		this.vcEnabl = vcEnabl;
	}

	public int getMel() {
		return mel;
	}

	public void setMel(int mel) {
		this.mel = mel;
	}

	public int getSourceMEP() {
		return sourceMEP;
	}

	public void setSourceMEP(int sourceMEP) {
		this.sourceMEP = sourceMEP;
	}

	public int getEquityMEP() {
		return equityMEP;
	}

	public void setEquityMEP(int equityMEP) {
		this.equityMEP = equityMEP;
	}

	public String getCvEnabl() {
		return cvEnabl;
	}

	public void setCvEnabl(String cvEnabl) {
		this.cvEnabl = cvEnabl;
	}

	public String getCvCycle() {
		return cvCycle;
	}

	public void setCvCycle(String cvCycle) {
		this.cvCycle = cvCycle;
	}

	public String getCvReserve() {
		return cvReserve;
	}

	public void setCvReserve(String cvReserve) {
		this.cvReserve = cvReserve;
	}

	public String getCsfEnabl() {
		return csfEnabl;
	}

	public void setCsfEnabl(String csfEnabl) {
		this.csfEnabl = csfEnabl;
	}
	
	public String getCsfcycle() {
		return csfcycle;
	}

	public void setCsfcycle(String csfcycle) {
		this.csfcycle = csfcycle;
	}

	public String getCsfreserve() {
		return csfreserve;
	}

	public void setCsfreserve(String csfreserve) {
		this.csfreserve = csfreserve;
	}

	public String getMegIcc() {
		return megIcc;
	}

	public void setMegIcc(String megIcc) {
		this.megIcc = megIcc;
	}

	public String getMegUmc() {
		return megUmc;
	}

	public void setMegUmc(String megUmc) {
		this.megUmc = megUmc;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public int getCir() {
		return cir;
	}

	public void setCir(int cir) {
		this.cir = cir;
	}

	public int getPir() {
		return pir;
	}

	public void setPir(int pir) {
		this.pir = pir;
	}

	public int getPwId() {
		return pwId;
	}

	public void setPwId(int pwId) {
		this.pwId = pwId;
	}

	public int getTunnelId() {
		return tunnelId;
	}

	public void setTunnelId(int tunnelId) {
		this.tunnelId = tunnelId;
	}

	public int getInLable() {
		return inLable;
	}

	public void setInLable(int inLable) {
		this.inLable = inLable;
	}

	public int getOutLable() {
		return outLable;
	}

	public void setOutLable(int outLable) {
		this.outLable = outLable;
	}

	public int getLspTc() {
		return lspTc;
	}

	public void setLspTc(int lspTc) {
		this.lspTc = lspTc;
	}

	public int getPwTc() {
		return pwTc;
	}

	public void setPwTc(int pwTc) {
		this.pwTc = pwTc;
	}

	public int getCbs() {
		return cbs;
	}

	public void setCbs(int cbs) {
		this.cbs = cbs;
	}

	public int getPbs() {
		return pbs;
	}

	public void setPbs(int pbs) {
		this.pbs = pbs;
	}

	public int getCm() {
		return cm;
	}

	public void setCm(int cm) {
		this.cm = cm;
	}

	public String getSourceMac() {
		return sourceMac;
	}

	public void setSourceMac(String sourceMac) {
		this.sourceMac = sourceMac;
	}

	public String getTargetMac() {
		return targetMac;
	}

	public void setTargetMac(String targetMac) {
		this.targetMac = targetMac;
	}

	public int getVlanEnable() {
		return vlanEnable;
	}

	public void setVlanEnable(int vlanEnable) {
		this.vlanEnable = vlanEnable;
	}

	public int getOutVlanValue() {
		return outVlanValue;
	}

	public void setOutVlanValue(int outVlanValue) {
		this.outVlanValue = outVlanValue;
	}

	public int getOamEnable() {
		return oamEnable;
	}

	public void setOamEnable(int oamEnable) {
		this.oamEnable = oamEnable;
	}
	
}
