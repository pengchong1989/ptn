package com.nms.drive.service.bean;

/**
 * 
 * TunnelObject ：tunnel
 * @author 彭冲
 *
 */
public class TunnelObject {

	private int tunnelId = 0;// ID:0-512
	private int lspqosType = 0;// LSP类型:0/1=E-LSP/L-LSP
	private int lspModel = 0;// LSP模型:0/1=统一模型/管道模型
	private String qosName = "";// QOS映射名称
	private int serviceType = 0;// 业务类型:0/1/2=工作业务/保护业务/额外业务
	private int bindingLsp = 0;// 0/1=否/是(网管界面不显示)
	private LSPObject foreLSP = new LSPObject();// 前向LSP
	private LSPObject afterLSP = new LSPObject();// 后向LSP
	
	private String oam_resore = "0";//备用,0
	private String oam_tmp = "1";// 0/1 = MIP/MEP
	private String oam_mel = "000";//mel :0/1/2/3/4/5/6/7
	private String oam_bao = "00";//保留 ，00
	
	private String megIcc = "49,50,51,52,53,54";// MEG ICC :123456 (字符方式显示,左侧字符对齐)
	private String megUmc = "55,56,57,97,98,99";// MEG UMC:789ABC (字符方式显示,左侧字符对齐)
	private int sourceMEP = 0;// 源MEP ID:0-1-8191 界面10进制显示(字节26是数据高位)
	private int equityMEP = 0;// 对等MEP ID:0-1-8191 界面10进制显示(字节28是数据高位)
	private int aspEnabl = 0;//ASP 使能：0/1 =不使能/使能
	
	private String cvEnabl = "0";// CV帧使能：0/1=不使能/使能
	private String cvCycle = "001";// CV帧发送周期:001/010/011/100=3.33ms/10ms/100ms/1s
	private String cvReserve = "0000";//CV备用
	private int lmEnabl = 0;//lm使能
	private int sncpID;
	private int role = 0;//a,z,xc=0,1,2
	private int oamEnable;//oam使能
	
	public int getLmEnabl() {
		return lmEnabl;
	}

	public void setLmEnabl(int lmEnabl) {
		this.lmEnabl = lmEnabl;
	}

	public String getOam_resore() {
		return oam_resore;
	}

	public void setOam_resore(String oamResore) {
		oam_resore = oamResore;
	}

	public String getOam_tmp() {
		return oam_tmp;
	}

	public void setOam_tmp(String oamTmp) {
		oam_tmp = oamTmp;
	}

	public String getOam_mel() {
		return oam_mel;
	}

	public void setOam_mel(String oamMel) {
		oam_mel = oamMel;
	}

	public String getOam_bao() {
		return oam_bao;
	}

	public void setOam_bao(String oamBao) {
		oam_bao = oamBao;
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

	public int getAspEnabl() {
		return aspEnabl;
	}

	public void setAspEnabl(int aspEnabl) {
		this.aspEnabl = aspEnabl;
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


	public int getTunnelId() {
		return tunnelId;
	}

	public void setTunnelId(int tunnelId) {
		this.tunnelId = tunnelId;
	}

	public int getLspqosType() {
		return lspqosType;
	}

	public void setLspqosType(int lspqosType) {
		this.lspqosType = lspqosType;
	}

	public int getLspModel() {
		return lspModel;
	}

	public void setLspModel(int lspModel) {
		this.lspModel = lspModel;
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	public int getBindingLsp() {
		return bindingLsp;
	}

	public void setBindingLsp(int bindingLsp) {
		this.bindingLsp = bindingLsp;
	}

	public String getQosName() {
		return qosName;
	}

	public void setQosName(String qosName) {
		this.qosName = qosName;
	}

	public LSPObject getForeLSP() {
		return foreLSP;
	}

	public void setForeLSP(LSPObject foreLSP) {
		this.foreLSP = foreLSP;
	}

	public LSPObject getAfterLSP() {
		return afterLSP;
	}

	public void setAfterLSP(LSPObject afterLSP) {
		this.afterLSP = afterLSP;
	}

	public int getSncpID() {
		return sncpID;
	}

	public void setSncpID(int sncpID) {
		this.sncpID = sncpID;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getOamEnable() {
		return oamEnable;
	}

	public void setOamEnable(int oamEnable) {
		this.oamEnable = oamEnable;
	}

}
