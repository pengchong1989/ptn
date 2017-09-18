package com.nms.db.bean.perform;

import java.io.Serializable;

public class PmValueLimiteInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8573734596617752729L;
	private int id;//数据库主键id
	private int highTemp;//高温门限
	private int crcError;//crc个数门限
	private int lossNum;//丢包个数门限
	private int receiveBadWrap;//收坏包个数门限
	private int tmsWorsen;//TMS通道信号劣化门限
	private int tmsLose;//TMS通道信号失效门限
	private int align;//对其错门限
	private int siteId;
	private int lowTemp;//低温门限
	private int tmpWorsen;//TMP通道信号劣化门限
	private int tmpLose;//TMP通道信号失效门限
	private int tmcWorsen;//TMC通道信号劣化门限
	private int tmcLose;//TMC通道信号失效门限
	
	
	
	//PDH 2M物理接口
	private int es;//PDH 2M物理接口2M误码秒	
	private int SES;//2M严重误码秒
//	private int UAS;//2M不可用秒
	private int BBE;//背景误码块
	private int CSES;//连续严重误码秒
	//光模块
	private int SFPLaserBias;//激光器偏流		
	private int SFPTxPOW;//光模块发射功率
	private int SFPRxPOW;//光模块接收功率
	private int SFPTemp;//光模块温度
	private int SFPAlarmTemp;//光模块警告温度

	//隧道维护点
	private int FEES;//远端误码秒
	private int FESES;//远端严重误码秒
	private int FEUAS;//远端不可用秒
	private int FECSES;//远端连续严重误码秒
	//以太网CIP端口
	private int cipReBandwidth;//接收方向宽带利用率
	private int cipSeBandwidth;//发送方向宽带利用率

	//以太网oam
	private int EthPackLosNear;//以太网oam近端丢包率
	private int EthPackLosFar;//以太网oam远端丢包率
	private int EthPackDelayMs;//以太网oam时延


	//段
	private int TmsRxCv;//段接收CV包统计
	private int TmsTxCv;//段发送CV包统计

	
	//FE端口
	private int FeJOT2BadDatagram;//FE端口OT2错报文数
	private int FeReBadPaPerecent;//FE端口接收错包率
	private int FeSeBadPaPerecent;//FE端口发送错包率
	private int SendLong;//FE发送超长帧数
	private int FeRxRatio;//FE端口接收带宽利用率
	private int RxAlErr;//FE端口接收对齐错帧数
	private int RxErr;//FE端口接收校验错帧数
	private int TxErr;//FE端口发送校验错帧数
	private int RxLost;//FE端口丢弃帧数
	private int RxErrAir;//FE端口接收错误的广播帧数
	private int RxErrKb;//FE端口接收错误的字节数
	private int TxErrKb;//FE端口发送错误的字节数
	private int RxJabber;//FE端口接收Jabber帧数
	private int TxJabber;//FE端口发送Jabber帧数

//GE端口
	
	private int GeJOT2BadDatagram;//GE端口OT2错报文数
	private int GeReBadPaPerecent;//GE端口接收错包率
	private int GeSeBadPaPerecent;//GE端口发送错包率
	private int GeSendLong;//GE发送超长帧数	
	private int GeRxRatio;//GE端口接收带宽利用率
	private int GeRxAlErr;//GE端口接收对齐错帧数
	private int GeRxErr;//GE端口接收校验错帧数
	private int GeTxErr;//GE端口发送校验错帧数
	private int GeRxLost;//GE端口丢弃帧数
	private int GeRxErrAir;//GE端口接收错误的广播帧数
	private int GeRxErrKb;//GE端口接收错误的字节数
	private int GeTxErrKb;//GE端口发送错误的字节数
	private int GeRxJabber;//GE端口接收Jabber帧数
	private int GeTxJabber;//GE端口发送Jabber帧数

	
	
	
	
	
	public int getEs() {
		return es;
	}

	public void setEs(int es) {
		this.es = es;
	}

	public int getSES() {
		return SES;
	}

	public void setSES(int ses) {
		SES = ses;
	}

	
	public int getBBE() {
		return BBE;
	}

	public void setBBE(int bbe) {
		BBE = bbe;
	}

	public int getCSES() {
		return CSES;
	}

	public void setCSES(int cses) {
		CSES = cses;
	}

	public int getSFPLaserBias() {
		return SFPLaserBias;
	}

	public void setSFPLaserBias(int laserBias) {
		SFPLaserBias = laserBias;
	}

	public int getSFPTxPOW() {
		return SFPTxPOW;
	}

	public void setSFPTxPOW(int txPOW) {
		SFPTxPOW = txPOW;
	}

	public int getSFPRxPOW() {
		return SFPRxPOW;
	}

	public void setSFPRxPOW(int rxPOW) {
		SFPRxPOW = rxPOW;
	}

	public int getSFPTemp() {
		return SFPTemp;
	}

	public void setSFPTemp(int temp) {
		SFPTemp = temp;
	}

	public int getSFPAlarmTemp() {
		return SFPAlarmTemp;
	}

	public void setSFPAlarmTemp(int alarmTemp) {
		SFPAlarmTemp = alarmTemp;
	}

	public int getFEES() {
		return FEES;
	}

	public void setFEES(int fees) {
		FEES = fees;
	}

	public int getFESES() {
		return FESES;
	}

	public void setFESES(int feses) {
		FESES = feses;
	}

	public int getFEUAS() {
		return FEUAS;
	}

	public void setFEUAS(int feuas) {
		FEUAS = feuas;
	}

	public int getFECSES() {
		return FECSES;
	}

	public void setFECSES(int fecses) {
		FECSES = fecses;
	}

	public int getCipReBandwidth() {
		return cipReBandwidth;
	}

	public void setCipReBandwidth(int cipReBandwidth) {
		this.cipReBandwidth = cipReBandwidth;
	}

	public int getCipSeBandwidth() {
		return cipSeBandwidth;
	}

	public void setCipSeBandwidth(int cipSeBandwidth) {
		this.cipSeBandwidth = cipSeBandwidth;
	}

	public int getEthPackLosNear() {
		return EthPackLosNear;
	}

	public void setEthPackLosNear(int ethPackLosNear) {
		EthPackLosNear = ethPackLosNear;
	}

	public int getEthPackLosFar() {
		return EthPackLosFar;
	}

	public void setEthPackLosFar(int ethPackLosFar) {
		EthPackLosFar = ethPackLosFar;
	}

	public int getEthPackDelayMs() {
		return EthPackDelayMs;
	}

	public void setEthPackDelayMs(int ethPackDelayMs) {
		EthPackDelayMs = ethPackDelayMs;
	}

	public int getTmsRxCv() {
		return TmsRxCv;
	}

	public void setTmsRxCv(int tmsRxCv) {
		TmsRxCv = tmsRxCv;
	}

	public int getTmsTxCv() {
		return TmsTxCv;
	}

	public void setTmsTxCv(int tmsTxCv) {
		TmsTxCv = tmsTxCv;
	}

	public int getFeJOT2BadDatagram() {
		return FeJOT2BadDatagram;
	}

	public void setFeJOT2BadDatagram(int feJOT2BadDatagram) {
		FeJOT2BadDatagram = feJOT2BadDatagram;
	}

	public int getFeReBadPaPerecent() {
		return FeReBadPaPerecent;
	}

	public void setFeReBadPaPerecent(int feReBadPaPerecent) {
		FeReBadPaPerecent = feReBadPaPerecent;
	}

	public int getFeSeBadPaPerecent() {
		return FeSeBadPaPerecent;
	}

	public void setFeSeBadPaPerecent(int feSeBadPaPerecent) {
		FeSeBadPaPerecent = feSeBadPaPerecent;
	}

	public int getSendLong() {
		return SendLong;
	}

	public void setSendLong(int sendLong) {
		SendLong = sendLong;
	}

	public int getFeRxRatio() {
		return FeRxRatio;
	}

	public void setFeRxRatio(int feRxRatio) {
		FeRxRatio = feRxRatio;
	}

	public int getRxAlErr() {
		return RxAlErr;
	}

	public void setRxAlErr(int rxAlErr) {
		RxAlErr = rxAlErr;
	}

	public int getRxErr() {
		return RxErr;
	}

	public void setRxErr(int rxErr) {
		RxErr = rxErr;
	}

	public int getTxErr() {
		return TxErr;
	}

	public void setTxErr(int txErr) {
		TxErr = txErr;
	}

	public int getRxLost() {
		return RxLost;
	}

	public void setRxLost(int rxLost) {
		RxLost = rxLost;
	}

	public int getRxErrAir() {
		return RxErrAir;
	}

	public void setRxErrAir(int rxErrAir) {
		RxErrAir = rxErrAir;
	}

	public int getRxErrKb() {
		return RxErrKb;
	}

	public void setRxErrKb(int rxErrKb) {
		RxErrKb = rxErrKb;
	}

	public int getTxErrKb() {
		return TxErrKb;
	}

	public void setTxErrKb(int txErrKb) {
		TxErrKb = txErrKb;
	}

	public int getRxJabber() {
		return RxJabber;
	}

	public void setRxJabber(int rxJabber) {
		RxJabber = rxJabber;
	}

	public int getTxJabber() {
		return TxJabber;
	}

	public void setTxJabber(int txJabber) {
		TxJabber = txJabber;
	}

	public int getGeJOT2BadDatagram() {
		return GeJOT2BadDatagram;
	}

	public void setGeJOT2BadDatagram(int geJOT2BadDatagram) {
		GeJOT2BadDatagram = geJOT2BadDatagram;
	}

	public int getGeReBadPaPerecent() {
		return GeReBadPaPerecent;
	}

	public void setGeReBadPaPerecent(int geReBadPaPerecent) {
		GeReBadPaPerecent = geReBadPaPerecent;
	}

	public int getGeSeBadPaPerecent() {
		return GeSeBadPaPerecent;
	}

	public void setGeSeBadPaPerecent(int geSeBadPaPerecent) {
		GeSeBadPaPerecent = geSeBadPaPerecent;
	}

	public int getGeSendLong() {
		return GeSendLong;
	}

	public void setGeSendLong(int geSendLong) {
		GeSendLong = geSendLong;
	}

	public int getGeRxRatio() {
		return GeRxRatio;
	}

	public void setGeRxRatio(int geRxRatio) {
		GeRxRatio = geRxRatio;
	}

	public int getGeRxAlErr() {
		return GeRxAlErr;
	}

	public void setGeRxAlErr(int geRxAlErr) {
		GeRxAlErr = geRxAlErr;
	}

	public int getGeRxErr() {
		return GeRxErr;
	}

	public void setGeRxErr(int geRxErr) {
		GeRxErr = geRxErr;
	}

	public int getGeTxErr() {
		return GeTxErr;
	}

	public void setGeTxErr(int geTxErr) {
		GeTxErr = geTxErr;
	}

	public int getGeRxLost() {
		return GeRxLost;
	}

	public void setGeRxLost(int geRxLost) {
		GeRxLost = geRxLost;
	}

	public int getGeRxErrAir() {
		return GeRxErrAir;
	}

	public void setGeRxErrAir(int geRxErrAir) {
		GeRxErrAir = geRxErrAir;
	}

	public int getGeRxErrKb() {
		return GeRxErrKb;
	}

	public void setGeRxErrKb(int geRxErrKb) {
		GeRxErrKb = geRxErrKb;
	}

	public int getGeTxErrKb() {
		return GeTxErrKb;
	}

	public void setGeTxErrKb(int geTxErrKb) {
		GeTxErrKb = geTxErrKb;
	}

	public int getGeRxJabber() {
		return GeRxJabber;
	}

	public void setGeRxJabber(int geRxJabber) {
		GeRxJabber = geRxJabber;
	}

	public int getGeTxJabber() {
		return GeTxJabber;
	}

	public void setGeTxJabber(int geTxJabber) {
		GeTxJabber = geTxJabber;
	}

	public int getLossNum() {
		return lossNum;
	}

	public void setLossNum(int lossNum) {
		this.lossNum = lossNum;
	}

	public int getReceiveBadWrap() {
		return receiveBadWrap;
	}

	public void setReceiveBadWrap(int receiveBadWrap) {
		this.receiveBadWrap = receiveBadWrap;
	}

	public int getTmsWorsen() {
		return tmsWorsen;
	}

	public void setTmsWorsen(int tmsWorsen) {
		this.tmsWorsen = tmsWorsen;
	}

	public int getTmsLose() {
		return tmsLose;
	}

	public void setTmsLose(int tmsLose) {
		this.tmsLose = tmsLose;
	}

	public int getAlign() {
		return align;
	}

	public void setAlign(int align) {
		this.align = align;
	}

	public int getCrcError() {
		return crcError;
	}

	public void setCrcError(int crcError) {
		this.crcError = crcError;
	}

	public int getHighTemp() {
		return highTemp;
	}

	public void setHighTemp(int highTemp) {
		this.highTemp = highTemp;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getLowTemp() {
		return lowTemp;
	}

	public void setLowTemp(int lowTemp) {
		this.lowTemp = lowTemp;
	}

	public int getTmpWorsen() {
		return tmpWorsen;
	}

	public void setTmpWorsen(int tmpWorsen) {
		this.tmpWorsen = tmpWorsen;
	}

	public int getTmpLose() {
		return tmpLose;
	}

	public void setTmpLose(int tmpLose) {
		this.tmpLose = tmpLose;
	}

	public int getTmcWorsen() {
		return tmcWorsen;
	}

	public void setTmcWorsen(int tmcWorsen) {
		this.tmcWorsen = tmcWorsen;
	}

	public int getTmcLose() {
		return tmcLose;
	}

	public void setTmcLose(int tmcLose) {
		this.tmcLose = tmcLose;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
