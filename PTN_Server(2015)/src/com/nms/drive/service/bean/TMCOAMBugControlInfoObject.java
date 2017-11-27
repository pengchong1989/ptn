package com.nms.drive.service.bean;

/**
 * 
 * TMCOAMBugControlInfoObject ：TMC OAM故障管理配置Info
 * @author 彭冲
 *
 */
public class TMCOAMBugControlInfoObject {
	private int lspId = 0;// LSP ID:0-512(0表示无效表项)
	private int subclausesId = 0;// 条目id自增
	private int pwid = 0;//PW ID:0-1024(0表示无效表项)
	private int inPwLable = 0;// 入PW标签
	private int outPwLable = 0;// 出PW标签
	
	private String lspTC = "000";//LSP TC:0-7	
	private String pwTC = "000";//PW TC:0-7
	private String resrveTC = "00";//备用 0
	
	private String megIcc = "1,2,3,4,5,6";// MEG ICC :123456 (字符方式显示,左侧字符对齐)
	private String megUmc = "7,8,9,10,11,12";// MEG UMC:789ABC (字符方式显示,左侧字符对齐)
	private int mel = 0;//MEL ：0/1/2..6/7=0/1/2..6/7

	private int SourceMEP = 0;// 源MEP ID:0-1-8191 界面10进制显示(字节26是数据高位)
	private int equityMEP = 0;// 对等MEP ID:0-1-8191 界面10进制显示(字节28是数据高位)

	private String loopbackEnable = "0";// BIT0 环回帧发送使能:0/1=不使能/使能
	private String loopbackCycle = "001";// BIT3-BIT1环回帧发送周期:001/010/011/100=3.33ms/10ms/100ms/1s
	private String loopbackTest = "0";// BIT4 环回测试方式:0/1=在线/离线
	private String loopbackTLV = "01";// BIT6-BIT5 离线方式测试TLV类型:01/11=全0/伪随机码
	private String loopbackReserve = "0";// BIT7 备用00

	private int loopback_tlvLength = 0;// (环回帧配置)TLV长度 1―65535
	private int loopback_tlvtest = 0;// (环回帧配置)数据TLV测试内容:0-255

	private String tstEnable = "0";// TST帧配置 字节1 BIT0 TST帧发送使能:0/1=不使能/使能
	private String tstCycle = "001";// BIT3-BIT1 TST帧发送周期:001/010/011/100=3.33ms/10ms/100ms/1s
	private String tstReserve1 = "0";// BIT4 备用00
	private String tstTLV = "01";// BIT6-BIT5 测试TLV类型:01/11=全0/伪随机码
	private String tstReserve2 = "0";// BIT7 备用00

	private int tst_tlvLength = 25;// (TST帧配置)TLV长度:25―65535 (字节42是数据高位)
	private int lckEnable = 0;// LCK帧发送使能0/1=不使能/使能

	private String lmEnable = "0";// BIT0 LM帧发送使能: 0/1=不使能/使能
	private String lmCycle = "0";// BIT1 LM帧发送周期：0/1=1s/100ms
	private String lmReserve = "000000";// BIT2-BIT7 备用00

	private String dmEnable = "0";// DM帧配置 BIT0 DM帧发送使能: 0/1=不使能/使能
	private String dmCycle = "0";// BIT1 DM帧发送周期：0/1=1s/100ms
	private String dmReserve = "000000";// BIT2- BIT7 备用00
	private int lbTTL;//lb TTL
	private int dmlength;
	public int getPwid() {
		return pwid;
	}

	public void setPwid(int pwid) {
		this.pwid = pwid;
	}

	public int getInPwLable() {
		return inPwLable;
	}

	public void setInPwLable(int inPwLable) {
		this.inPwLable = inPwLable;
	}

	public int getOutPwLable() {
		return outPwLable;
	}

	public void setOutPwLable(int outPwLable) {
		this.outPwLable = outPwLable;
	}

	public String getLspTC() {
		return lspTC;
	}

	public void setLspTC(String lspTC) {
		this.lspTC = lspTC;
	}

	public String getPwTC() {
		return pwTC;
	}

	public void setPwTC(String pwTC) {
		this.pwTC = pwTC;
	}

	public String getResrveTC() {
		return resrveTC;
	}

	public void setResrveTC(String resrveTC) {
		this.resrveTC = resrveTC;
	}

	public int getMel() {
		return mel;
	}

	public void setMel(int mel) {
		this.mel = mel;
	}
	
	public int getLspId() {
		return lspId;
	}

	public void setLspId(int lspId) {
		this.lspId = lspId;
	}

	public int getSubclausesId() {
		return subclausesId;
	}

	public void setSubclausesId(int subclausesId) {
		this.subclausesId = subclausesId;
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
		return SourceMEP;
	}

	public void setSourceMEP(int sourceMEP) {
		SourceMEP = sourceMEP;
	}

	public int getEquityMEP() {
		return equityMEP;
	}

	public void setEquityMEP(int equityMEP) {
		this.equityMEP = equityMEP;
	}

	public String getLoopbackEnable() {
		return loopbackEnable;
	}

	public void setLoopbackEnable(String loopbackEnable) {
		this.loopbackEnable = loopbackEnable;
	}

	public String getLoopbackCycle() {
		return loopbackCycle;
	}

	public void setLoopbackCycle(String loopbackCycle) {
		this.loopbackCycle = loopbackCycle;
	}

	public String getLoopbackTest() {
		return loopbackTest;
	}

	public void setLoopbackTest(String loopbackTest) {
		this.loopbackTest = loopbackTest;
	}

	public String getLoopbackTLV() {
		return loopbackTLV;
	}

	public void setLoopbackTLV(String loopbackTLV) {
		this.loopbackTLV = loopbackTLV;
	}

	public String getLoopbackReserve() {
		return loopbackReserve;
	}

	public void setLoopbackReserve(String loopbackReserve) {
		this.loopbackReserve = loopbackReserve;
	}

	public int getLoopback_tlvLength() {
		return loopback_tlvLength;
	}

	public void setLoopback_tlvLength(int loopback_tlvLength) {
		this.loopback_tlvLength = loopback_tlvLength;
	}

	public int getLoopback_tlvtest() {
		return loopback_tlvtest;
	}

	public void setLoopback_tlvtest(int loopback_tlvtest) {
		this.loopback_tlvtest = loopback_tlvtest;
	}

	public String getTstEnable() {
		return tstEnable;
	}

	public void setTstEnable(String tstEnable) {
		this.tstEnable = tstEnable;
	}

	public String getTstCycle() {
		return tstCycle;
	}

	public void setTstCycle(String tstCycle) {
		this.tstCycle = tstCycle;
	}

	public String getTstReserve1() {
		return tstReserve1;
	}

	public void setTstReserve1(String tstReserve1) {
		this.tstReserve1 = tstReserve1;
	}

	public String getTstTLV() {
		return tstTLV;
	}

	public void setTstTLV(String tstTLV) {
		this.tstTLV = tstTLV;
	}

	public String getTstReserve2() {
		return tstReserve2;
	}

	public void setTstReserve2(String tstReserve2) {
		this.tstReserve2 = tstReserve2;
	}

	public int getTst_tlvLength() {
		return tst_tlvLength;
	}

	public void setTst_tlvLength(int tst_tlvLength) {
		this.tst_tlvLength = tst_tlvLength;
	}

	public int getLckEnable() {
		return lckEnable;
	}

	public void setLckEnable(int lckEnable) {
		this.lckEnable = lckEnable;
	}

	public String getLmEnable() {
		return lmEnable;
	}

	public void setLmEnable(String lmEnable) {
		this.lmEnable = lmEnable;
	}

	public String getLmCycle() {
		return lmCycle;
	}

	public void setLmCycle(String lmCycle) {
		this.lmCycle = lmCycle;
	}

	public String getLmReserve() {
		return lmReserve;
	}

	public void setLmReserve(String lmReserve) {
		this.lmReserve = lmReserve;
	}

	public String getDmEnable() {
		return dmEnable;
	}

	public void setDmEnable(String dmEnable) {
		this.dmEnable = dmEnable;
	}

	public String getDmCycle() {
		return dmCycle;
	}

	public void setDmCycle(String dmCycle) {
		this.dmCycle = dmCycle;
	}

	public String getDmReserve() {
		return dmReserve;
	}

	public void setDmReserve(String dmReserve) {
		this.dmReserve = dmReserve;
	}

	public int getLbTTL() {
		return lbTTL;
	}

	public void setLbTTL(int lbTTL) {
		this.lbTTL = lbTTL;
	}

	public int getDmlength() {
		return dmlength;
	}

	public void setDmlength(int dmlength) {
		this.dmlength = dmlength;
	}
	
	
}
