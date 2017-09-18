package com.nms.drivechenxiao.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.nms.drivechenxiao.analysis.AnalysisAlarm;
import com.nms.drivechenxiao.analysis.AnalysisHookNotify;
import com.nms.drivechenxiao.analysis.AnalysisLogin;
import com.nms.drivechenxiao.analysis.AnalysisSlot;
import com.nms.drivechenxiao.analysis.clock.AnalysisClock;
import com.nms.drivechenxiao.analysis.clock.AnalysisClockPort;
import com.nms.drivechenxiao.analysis.clock.AnalysisExtclk;
import com.nms.drivechenxiao.analysis.clock.AnalysisPtp;
import com.nms.drivechenxiao.analysis.clock.AnalysisPtpPort;
import com.nms.drivechenxiao.analysis.clock.AnalysisTod;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisAC;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisACN;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisApscmd;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisETHPort;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisLag;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisPDHPort;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisPWETH;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisPWPDH;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisPWSDH;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisQOS;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisRING;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisSDHPort;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisSdhAc;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisTunnel;
import com.nms.drivechenxiao.analysis.ne.AnalysisArea;
import com.nms.drivechenxiao.analysis.ne.AnalysisCCN;
import com.nms.drivechenxiao.analysis.ne.AnalysisMCN;
import com.nms.drivechenxiao.analysis.ne.AnalysisNE;
import com.nms.drivechenxiao.analysis.ne.AnalysisOSPF;
import com.nms.drivechenxiao.analysis.ne.AnalysisOSPFInterfaces;
import com.nms.drivechenxiao.analysis.ne.AnalysisRedistribute;
import com.nms.drivechenxiao.analysis.oam.AnalysisEthEfmOam;
import com.nms.drivechenxiao.analysis.oam.AnalysisOAM;
//import com.nms.drivechenxiao.analysis.proc.AnalysisPersvr;
import com.nms.drivechenxiao.analysis.protocols.AnalysisCard;
import com.nms.drivechenxiao.analysis.protocols.AnalysisClrtoexp;
import com.nms.drivechenxiao.analysis.protocols.AnalysisCos2vlanpri;
import com.nms.drivechenxiao.analysis.protocols.AnalysisCostoexp;
import com.nms.drivechenxiao.analysis.protocols.AnalysisDual;
import com.nms.drivechenxiao.analysis.protocols.AnalysisExptoclr;
import com.nms.drivechenxiao.analysis.protocols.AnalysisExptocos;
import com.nms.drivechenxiao.analysis.protocols.AnalysisMsp;
import com.nms.drivechenxiao.analysis.protocols.AnalysisStaticRoute;
import com.nms.drivechenxiao.analysis.protocols.AnalysisVlanpri2cng;
import com.nms.drivechenxiao.analysis.service.AnalysisCes;
import com.nms.drivechenxiao.analysis.service.AnalysisELAN;
import com.nms.drivechenxiao.analysis.service.AnalysisELine;
import com.nms.drivechenxiao.analysis.service.AnalysisETree;
import com.nms.drivechenxiao.analysis.service.AnalysisXC;
import com.nms.drivechenxiao.analysis.slot.AnalysisSlotTemp;
import com.nms.drivechenxiao.analysis.weihu.AnalysisPower;
import com.nms.drivechenxiao.network.TcpNetwork;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.impl.CXPtnDirveService;
import com.nms.drivechenxiao.service.impl.bean.CXDriveUtilObject;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;

public class CXDriveService extends CXPtnDirveService implements CXDriveServiceI {

	/**
	 * 初始化驱动服务
	 * 
	 * @throws Exception
	 */
	public void init(CXNEObject cxNeObject) throws Exception {
		super.init(cxNeObject);

	}

	/**
	 * 销毁
	 */
	public void destroy(CXNEObject cxNeObject) {
		super.destroy(cxNeObject);
	}
	
	/**
	 * 登入
	 * 
	 * @param CXNEObject网元对象
	 */
	public void login(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject

		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXNEObject);// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisLogin analysisLogin = new AnalysisLogin();
		int seqId = super.getSeqId();
		byte[] loginByte = analysisLogin.getLoginBytes(cXNEObject, 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(loginByte);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisLogin = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_NE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_LOGIN);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 退出
	 * 
	 * @param CXNEObject网元对象
	 */
	public void logout(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXNEObject);// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisLogin analysisLogin = new AnalysisLogin();
		int seqId = super.getSeqId();
		byte[] loginByte = analysisLogin.getLogOutBytes(cXNEObject, 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(loginByte);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisLogin = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_NE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_LOGOUT);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 注册监听
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void configHookNotify(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisHookNotify analysisHookNotify = new AnalysisHookNotify();
		int seqId = super.getSeqId();
		byte[] hookNotifyBytes = analysisHookNotify.configHookNotify(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(hookNotifyBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisHookNotify = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OPER_HOOKNOTIFY);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_HOOKNOTIFY);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void createClockPort(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getClockPortESObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisClockPort anC = new AnalysisClockPort();
		int seqId = super.getSeqId();
		byte[] create = anC.createClockPort(cXActionObject.getClockPortESObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(create);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anC = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CLOCKPortES);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 创建Ring 环 
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void createRing(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getRingObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisRING anRing = new AnalysisRING();
		int seqId = super.getSeqId();
		byte[] createRing = anRing.createRing(cXActionObject.getRingObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createRing);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anRing = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_RING);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void createPtpPortOjbect(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPtpPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPtpPort anptpPort = new AnalysisPtpPort();
		int seqId = super.getSeqId();
		byte[] createptpPort = anptpPort.createPtpPort(cXActionObject.getPtpPortObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createptpPort);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anptpPort = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PTPPORT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 创建TOD 
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void createTod(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getTodObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisTod atod = new AnalysisTod();
		int seqId = super.getSeqId();
		byte[] createTod = atod.createTod(cXActionObject.getTodObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createTod);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		atod = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_TOD);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void createVlanpri2cng(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getVlanpri2cngObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisVlanpri2cng an = new AnalysisVlanpri2cng();
		int seqId = super.getSeqId();
		byte[] cr = an.createVlanpri2cng(cXActionObject.getVlanpri2cngObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(cr);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_VLAN2CNG);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void createCos2vlanpri(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getCos2vlanpriObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCos2vlanpri an = new AnalysisCos2vlanpri();
		int seqId = super.getSeqId();
		byte[] create = an.createCos2vlanpri(cXActionObject.getCos2vlanpriObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(create);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_COS2VPN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void createExptocos(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getExptocosObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisExptocos atod = new AnalysisExptocos();
		int seqId = super.getSeqId();
		byte[] cre = atod.createExptocos(cXActionObject.getExptocosObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(cre);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		atod = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_EXPTOCOS);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void createExptoclr(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getExptoclrObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisExptoclr atod = new AnalysisExptoclr();
		int seqId = super.getSeqId();
		byte[] cre = atod.createExptoclr(cXActionObject.getExptoclrObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(cre);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		atod = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_EXPTOCLR);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void createCostoexp(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getCostoexpObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCostoexp an = new AnalysisCostoexp();
		int seqId = super.getSeqId();
		byte[] create = an.createCostoexp(cXActionObject.getCostoexpObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(create);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_COSTOEXP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**端口exp业务映射 里面的 llsp里的 输出
	 * **/
	public void createClrtoexp(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getClrtoexpObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisClrtoexp an = new AnalysisClrtoexp();
		int seqId = super.getSeqId();
		byte[] createTod = an.createClrtoexp(cXActionObject.getClrtoexpObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createTod);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CLRTOEXP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 创建双规保护 
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void createDual(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getDualObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisDual Dual = new AnalysisDual();
		int seqId = super.getSeqId();
		byte[] create = Dual.createDual(cXActionObject.getDualObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(create);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		Dual = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_DUAL);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 创建段（QOS OAM）
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void createSegment(OperationObject operationObject, CXActionObject cXActionObject) {

		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisETHPort analysisETHPort = new AnalysisETHPort();
		int seqId = super.getSeqId();
		byte[] createSegment = analysisETHPort.setSegment(cXActionObject.getEthPortObject(), cXActionObject.getOamObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createSegment);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisETHPort = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SEMENT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 创建Msp保护 
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void createMsp(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getMspObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisMsp anmsp = new AnalysisMsp();
		int seqId = super.getSeqId();
		byte[] create = anmsp.createMsp(cXActionObject.getMspObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(create);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anmsp = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_MSP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 更新段（QOS OAM）
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void updateSegment(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisETHPort analysisETHPort = new AnalysisETHPort();
		int seqId = super.getSeqId();
		byte[] updateSegment = analysisETHPort.updateSegment(cXActionObject.getEthPortObject(), cXActionObject.getOamObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateSegment);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisETHPort = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SEMENT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 删除段（OAM）
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void deleteSegment(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisETHPort analysisETHPort = new AnalysisETHPort();
		int seqId = super.getSeqId();
		byte[] deleteSegment = analysisETHPort.deleteSegmentOAM(cXActionObject.getEthPortObject(), cXActionObject.getOamObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteSegment);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisETHPort = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SEMENT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 更新ETH端口
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 */
	public void updatePortETH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject

		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisETHPort analysisETHPort = new AnalysisETHPort();
		int seqId = super.getSeqId();
		byte[] setPortBytes = analysisETHPort.setPort(cXActionObject.getEthPortObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(setPortBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisETHPort = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETHPORT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 查询ETH端口
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 */
	public void selectPortETH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject

		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisETHPort analysisETHPort = new AnalysisETHPort();
		int seqId = super.getSeqId();
		byte[] setPortBytes = analysisETHPort.selectAllEthPort(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(setPortBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisETHPort = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETHPORT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 更新SDH端口
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 */
	public void updatePortSDH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject

		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisSDHPort analysisSDHPort = new AnalysisSDHPort();
		int seqId = super.getSeqId();
		byte[] setPortBytes = analysisSDHPort.setSDHPort(cXActionObject.getSdhPortObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(setPortBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisSDHPort = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SDHPORT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 更新PDH端口
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 */
	public void updatePortPDH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject

		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPDHPort analysisPDHPort = new AnalysisPDHPort();
		int seqId = super.getSeqId();
		byte[] setPortBytes = analysisPDHPort.setPDHPort(cXActionObject.getPdhPortObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(setPortBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPDHPort = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PDHPORT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 查询PDH端口
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 */
	public void selectPortPDH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject

		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPDHPort analysisPDHPort = new AnalysisPDHPort();
		int seqId = super.getSeqId();
		byte[] setPortBytes = analysisPDHPort.selectPDHPort(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(setPortBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPDHPort = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PDHPORT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 更新ETH端口UNI
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param UNIObjectUNI对象
	 */
	public void updateUNIPortETH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject, UNIObject
		// UNIObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getUNIObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisETHPort analysisETHPort = new AnalysisETHPort();
		int seqId = super.getSeqId();
		byte[] setPortBytes = analysisETHPort.setPortUNI(cXActionObject.getEthPortObject(), cXActionObject.getUNIObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(setPortBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisETHPort = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETHPORTUNI);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 查询ETH端口UNI
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param UNIObjectUNI对象
	 */
	public void selectUNIPortETH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject, UNIObject
		// UNIObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getUNIObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisETHPort analysisETHPort = new AnalysisETHPort();
		int seqId = super.getSeqId();
		byte[] setPortBytes = analysisETHPort.selectETHPortUNI(cXActionObject.getEthPortObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(setPortBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisETHPort = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETHPORTUNI);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 更新ETH端口NNI
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param NNIObjectNNI对象
	 */
	public void updateNNIPortETH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject, NNIObject
		// NNIObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getNNIObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisETHPort analysisETHPort = new AnalysisETHPort();
		int seqId = super.getSeqId();
		byte[] setPortBytes = analysisETHPort.setPortNNI(cXActionObject.getEthPortObject(), cXActionObject.getNNIObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(setPortBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisETHPort = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETHPORTNNI);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询ETH端口NNI
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param NNIObjectNNI对象
	 */
	public void selectNNIPortETH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject, NNIObject
		// NNIObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getNNIObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisETHPort analysisETHPort = new AnalysisETHPort();
		int seqId = super.getSeqId();
		byte[] setPortBytes = analysisETHPort.selectETHPortNNI(cXActionObject.getEthPortObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(setPortBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisETHPort = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETHPORTNNI);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建ETHAC
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void createACPortETH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject,AcQosObject,
		// AcObject AcObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getAcObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisAC analysisAC = new AnalysisAC();
		int seqId = super.getSeqId();
		byte[] createAcBytes = analysisAC.createAc(cXActionObject.getEthPortObject(), cXActionObject.getAcObject(), cXActionObject.getAcQosObject(), cXActionObject.getOamObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createAcBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisAC = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETHAC);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 更新ETHAC
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void updateACPortETH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject,AcQosObject,
		// AcObject AcObject

		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getAcObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisAC analysisAC = new AnalysisAC();
		int seqId = super.getSeqId();
		byte[] updateAcBytes = analysisAC.updateAc(cXActionObject.getEthPortObject(), cXActionObject.getAcObject(), cXActionObject.getAcQosObject(), cXActionObject.getOamObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateAcBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisAC = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETHAC);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 删除Ring 环
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void deleteRing(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getRingObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisRING anRing = new AnalysisRING();
		int seqId = super.getSeqId();
		byte[] createRing = anRing.deleteRing(cXActionObject.getRingObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createRing);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anRing = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_RING);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void deleteClockPort(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getClockPortESObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisClockPort anC = new AnalysisClockPort();
		int seqId = super.getSeqId();
		byte[] createRing = anC.deleteClockPort(cXActionObject.getClockPortESObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createRing);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anC = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CLOCKPortES);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void deleteptpPortObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPtpPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPtpPort an = new AnalysisPtpPort();
		int seqId = super.getSeqId();
		byte[] de = an.deletePtpPort(cXActionObject.getPtpPortList(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(de);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PTPPORT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void deleteTod(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getTodObject());// 赋值对象 -----
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisTod atod = new AnalysisTod();
		int seqId = super.getSeqId();
		byte[] deleteTod = atod.deleteTod(cXActionObject.getTodObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteTod);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		atod = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_TOD);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void deleteStaticRoute(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getStaticRouteObject());// 赋值对象 -----
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisStaticRoute an = new AnalysisStaticRoute();
		int seqId = super.getSeqId();
		byte[] deleteTod = an.deleteStaticRoute(cXActionObject.getStaticRouteObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteTod);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_STATICROUTE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void deleteVlanpri2cng(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getVlanpri2cngObject());// 赋值对象 -----
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisVlanpri2cng an = new AnalysisVlanpri2cng();
		int seqId = super.getSeqId();
		byte[] deleteTod = an.deleteVlanpri2cng(cXActionObject.getVlanpri2cngObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteTod);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_VLAN2CNG);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void deleteCos2vlanpri(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getCos2vlanpriObject());// 赋值对象 -----
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCos2vlanpri an = new AnalysisCos2vlanpri();
		int seqId = super.getSeqId();
		byte[] delete = an.deleteCos2vlanpri(cXActionObject.getCos2vlanpriObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(delete);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_COS2VPN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void deleteExptocos(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getExptocosObject());// 赋值对象 -----
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisExptocos atod = new AnalysisExptocos();
		int seqId = super.getSeqId();
		byte[] dele = atod.deleteExptocos(cXActionObject.getExptocosObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(dele);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		atod = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_EXPTOCOS);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void deleteExptoclr(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getExptoclrObject());// 赋值对象 -----
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisExptoclr atod = new AnalysisExptoclr();
		int seqId = super.getSeqId();
		byte[] del = atod.deleteExptoclr(cXActionObject.getExptoclrObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(del);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		atod = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_EXPTOCLR);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void deleteCostoexp(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getCostoexpObject());// 赋值对象 -----
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCostoexp an = new AnalysisCostoexp();
		int seqId = super.getSeqId();
		byte[] delete = an.deleteCostoexp(cXActionObject.getCostoexpObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(delete);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_COSTOEXP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void deleteClrtoexp(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getClrtoexpObject());// 赋值对象 -----
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisClrtoexp an = new AnalysisClrtoexp();
		int seqId = super.getSeqId();
		byte[] deleteTod = an.deleteClrtoexp(cXActionObject.getClrtoexpObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteTod);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CLRTOEXP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void deleteDual(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getDualObject());// 赋值对象 -----
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisDual Dual = new AnalysisDual();
		int seqId = super.getSeqId();
		byte[] delete = Dual.deleteDual(cXActionObject.getDualObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(delete);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		Dual = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_DUAL);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 删除 Msp保护
	 * **/
	public void deleteMsp(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getMspObject());// 赋值对象 -----
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisMsp ana = new AnalysisMsp();
		int seqId = super.getSeqId();
		byte[] delete = ana.deleteMsp(cXActionObject.getMspObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(delete);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		ana = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_MSP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * update Ring 环
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void updateRing(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getRingObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisRING anRing = new AnalysisRING();
		int seqId = super.getSeqId();
		byte[] update = anRing.updateRing(cXActionObject.getRingObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(update);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anRing = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_RING);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void updateVlanpri2cng(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getVlanpri2cngObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisVlanpri2cng an = new AnalysisVlanpri2cng();
		int seqId = super.getSeqId();
		byte[] update = an.updateVlanpri2cng(cXActionObject.getVlanpri2cngObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(update);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_VLAN2CNG);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void updateCostoexp(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getCostoexpObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCostoexp an = new AnalysisCostoexp();
		int seqId = super.getSeqId();
		byte[] update = an.updateCostoexp(cXActionObject.getCostoexpObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(update);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_COSTOEXP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void updateEthEfmOam(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEfmObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisEthEfmOam ana = new AnalysisEthEfmOam();
		int seqId = super.getSeqId();
		byte[] update = ana.updateEfm(cXActionObject.getEfmObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(update);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		ana = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_EFM);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	
	public void updateClockPort(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getClockPortESObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisClockPort anRing = new AnalysisClockPort();
		int seqId = super.getSeqId();
		byte[] update = anRing.updateClockPort(cXActionObject.getClockPortESObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(update);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anRing = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CLOCKPortES);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	//外时钟修改,单独修改端口的1级属性,类似clock修改,跟clockport修改用1个bean
	public void updateExtclkPort(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getClockPortESObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisClockPort anRing = new AnalysisClockPort();
		int seqId = super.getSeqId();
		byte[] update = anRing.updateExtclkPort(cXActionObject.getClockPortESObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(update);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anRing = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CLOCKPortES);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void updatePtp(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPtpObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPtp anPtp = new AnalysisPtp();
		int seqId = super.getSeqId();
		byte[] update = anPtp.updatePtp(cXActionObject.getPtpObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(update);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anPtp = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PTP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void updateClrtoexp(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getClrtoexpObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisClrtoexp an = new AnalysisClrtoexp();
		int seqId = super.getSeqId();
		byte[] update = an.updateClrtoexp(cXActionObject.getClrtoexpObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(update);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CLRTOEXP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void updateExptoclr(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getExptoclrObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisExptoclr an = new AnalysisExptoclr();
		int seqId = super.getSeqId();
		byte[] update = an.updateExptoclr(cXActionObject.getExptoclrObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(update);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_EXPTOCLR);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void updateExptocos(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getExptocosObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisExptocos an = new AnalysisExptocos();
		int seqId = super.getSeqId();
		byte[] update = an.updateExptocos(cXActionObject.getExptocosObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(update);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_EXPTOCOS);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void updatePtpPortOjbect(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPtpPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPtpPort an = new AnalysisPtpPort();
		int seqId = super.getSeqId();
		byte[] update = an.updatePtpPort(cXActionObject.getPtpPortObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(update);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PTPPORT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void updateExtclk(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getExtclkObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisExtclk anRing = new AnalysisExtclk();
		int seqId = super.getSeqId();
		byte[] update = anRing.updateExtclk(cXActionObject.getExtclkObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(update);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anRing = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_EXTCLK);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 建立 Ring 环
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void selectRing(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getRingObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisRING anRing = new AnalysisRING();
		int seqId = super.getSeqId();
		byte[] createRing = anRing.selectRing(null, 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createRing);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anRing = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_RING);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void selectStaticRoute(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getStaticRouteObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisStaticRoute an = new AnalysisStaticRoute();
		int seqId = super.getSeqId();
		byte[] createRing = an.selectStaticRoute( 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createRing);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_STATICROUTE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void selectVlanpri2cng(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getVlanpri2cngObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisVlanpri2cng an = new AnalysisVlanpri2cng();
		int seqId = super.getSeqId();
		byte[] se = an.selectVlanpri2cng( 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(se);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_VLAN2CNG);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void selectExptoclr(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getExptoclrObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisExptoclr anRing = new AnalysisExptoclr();
		int seqId = super.getSeqId();
		byte[] sel = anRing.selectExptoclr( 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(sel);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anRing = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_EXPTOCLR);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void selectCos2vlanpri(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getCos2vlanpriObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCos2vlanpri an = new AnalysisCos2vlanpri();
		int seqId = super.getSeqId();
		byte[] se = an.selectCos2vlanpri( 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(se);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_COS2VPN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void selectCostoexp(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getCostoexpObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCostoexp an = new AnalysisCostoexp();
		int seqId = super.getSeqId();
		byte[] se = an.selectCostoexp( 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(se);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_COSTOEXP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void selectEthEfmOam(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEfmObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisEthEfmOam analysis = new AnalysisEthEfmOam();
		int seqId = super.getSeqId();
		byte[] create = analysis.selectEfm( 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(create);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysis = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_EFM);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	
	public void selectClockPort(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getClockPortESObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisClockPort anC = new AnalysisClockPort();
		int seqId = super.getSeqId();
		byte[] select = anC.selectClockPort(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(select);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anC = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CLOCKPortES);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void selectPtpPortObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPtpPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPtpPort an = new AnalysisPtpPort();
		int seqId = super.getSeqId();
		byte[] select = an.selectPtpPort(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(select);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PTPPORT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void selectExtclk(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getExtclkObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisExtclk anex = new AnalysisExtclk();
		int seqId = super.getSeqId();
		byte[] createRing = anex.selectExtclk(null, 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createRing);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anex = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_EXTCLK);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * update Clock
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void updateClock(OperationObject operationObject, CXActionObject cXActionObject) {		
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getClockObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisClock clock = new AnalysisClock();
		int seqId = super.getSeqId();
		byte[] createRing = clock.updateClock(cXActionObject.getClockObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createRing);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		clock = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CLOCK);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void selectClock(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getClockObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisClock clock = new AnalysisClock();
		int seqId = super.getSeqId();
		byte[] createRing = clock.selectClock(null, 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createRing);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		clock = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CLOCK);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void selectClrtoexp(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getClrtoexpObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisClrtoexp an = new AnalysisClrtoexp();
		int seqId = super.getSeqId();
		byte[] createRing = an.selectClrtoexp( 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createRing);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CLRTOEXP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void selectExptocos(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getExptocosObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisExptocos an = new AnalysisExptocos();
		int seqId = super.getSeqId();
		byte[] se = an.selectExptocos( 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(se);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_EXPTOCOS);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void selectPtp(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPtpObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPtp anPtp = new AnalysisPtp();
		int seqId = super.getSeqId();
		byte[] createRing = anPtp.selectPtp(null, 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createRing);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		anPtp = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PTP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 删除ETHAC
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void deleteACPortETH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject,
		// AcQosObject,AcObject AcObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getAcObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisAC analysisAC = new AnalysisAC();
		int seqId = super.getSeqId();
		byte[] deleteAcBytes = analysisAC.deleteAc(cXActionObject.getEthPortObject(), cXActionObject.getAcObject(), cXActionObject.getAcQosObject(), cXActionObject.getOamObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteAcBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisAC = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETHAC);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询ETHAC
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void selectACPortETH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject,
		// AcQosObject,AcObject AcObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getAcObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisAC analysisAC = new AnalysisAC();
		int seqId = super.getSeqId();
		byte[] deleteAcBytes = analysisAC.selectAc(cXActionObject.getEthPortObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteAcBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisAC = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETHAC);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建SDHAC
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void createACPortSDH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject,AcQosObject,
		// AcObject AcObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getAcObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisSdhAc analysisSdhAc = new AnalysisSdhAc();
		int seqId = super.getSeqId();
		byte[] createAcBytes = analysisSdhAc.createSDHAc(cXActionObject.getSdhPortObject(), cXActionObject.getSdhAcObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createAcBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisSdhAc = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SDHAC);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 更新SDHAC
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void updateACPortSDH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject,AcQosObject,
		// AcObject AcObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getAcObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisSdhAc analysisSdhAc = new AnalysisSdhAc();
		int seqId = super.getSeqId();
		byte[] updateAcBytes = analysisSdhAc.updateSDHAc(cXActionObject.getSdhPortObject(), cXActionObject.getSdhAcObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateAcBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisSdhAc = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SDHAC);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 删除SDHAC
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void deleteACPortSDH(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject,
		// AcQosObject,AcObject AcObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getAcObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisSdhAc analysisSdhAc = new AnalysisSdhAc();
		int seqId = super.getSeqId();
		byte[] deleteAcBytes = analysisSdhAc.deleteSDHAc(cXActionObject.getSdhPortObject(), cXActionObject.getSdhAcObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteAcBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisSdhAc = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SDHAC);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建Tunnel
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * @param tunnelObject
	 */
	public void createTunnelObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, TunnelObject tunnelObject

		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisTunnel analysisTunnel = new AnalysisTunnel();
		int seqId = super.getSeqId();
		byte[] createTunnelBytes = analysisTunnel.createTunnel(cXActionObject.getQosObject(), cXActionObject.getTunnelObject(), cXActionObject.getProtection(), cXActionObject.getOamObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createTunnelBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisTunnel = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_TUNNEL);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 更新Tunnel
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * @param tunnelObject
	 */
	public void updateTunnelObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, TunnelObject tunnelObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisTunnel analysisTunnel = new AnalysisTunnel();
		int seqId = super.getSeqId();
		byte[] createTunnelBytes = analysisTunnel.updateTunnel(cXActionObject.getQosObject(), cXActionObject.getTunnelObject(), cXActionObject.getProtection(), cXActionObject.getOamObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createTunnelBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisTunnel = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_TUNNEL);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 删除Tunnel
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * @param tunnelObject
	 */
	public void deleteTunnelObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, TunnelObject tunnelObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisTunnel analysisTunnel = new AnalysisTunnel();
		int seqId = super.getSeqId();
		byte[] createTunnelBytes = analysisTunnel.deleteTunnel(cXActionObject.getQosObject(), cXActionObject.getTunnelObject(), cXActionObject.getOamObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createTunnelBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisTunnel = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_TUNNEL);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询全部tunnel
	 * 
	 * @author kk
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void selectTunnel(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, TunnelObject tunnelObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisTunnel analysisTunnel = new AnalysisTunnel();
		int seqId = super.getSeqId();
		byte[] createTunnelBytes = analysisTunnel.selectAllTuunel(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createTunnelBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisTunnel = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_TUNNEL);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建XC
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void createXC(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject,AcQosObject,
		// AcObject AcObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getXcObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisXC analysisXC = new AnalysisXC();
		int seqId = super.getSeqId();
		byte[] createXcBytes = analysisXC.createXc(cXActionObject.getXcObject(), cXActionObject.getQosObject(), cXActionObject.getXcObject().getOamMipObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createXcBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisXC = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_XC);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}
	public void createStaticRoute(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject,AcQosObject,
		// AcObject AcObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getStaticRouteObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisStaticRoute an = new AnalysisStaticRoute();
		int seqId = super.getSeqId();
		byte[] crea = an.createStaticRoute(cXActionObject.getStaticRouteObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(crea);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_STATICROUTE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}
	/**
	 * 创建对应oam 里面的 eth链路 oam 
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void createEthEfmOam(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject,AcQosObject,
		// AcObject AcObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEfmObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisEthEfmOam analysis = new AnalysisEthEfmOam();
		int seqId = super.getSeqId();
		byte[] createXcBytes = analysis.createEfm(cXActionObject.getEfmObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createXcBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysis = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_EFM);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}
	/**
	 * 更新 Msp保护
	 * **/
	public void updateMsp(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getMspObject());// 赋值对象 -----
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisMsp ana = new AnalysisMsp();
		int seqId = super.getSeqId();
		byte[] update = ana.updateMsp(cXActionObject.getMspObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(update);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		ana = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_MSP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 更新XC
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void updateXC(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject,AcQosObject,
		// AcObject AcObject

		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getXcObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisXC analysisXC = new AnalysisXC();
		int seqId = super.getSeqId();
		byte[] updateXcBytes = analysisXC.updateXc(cXActionObject.getXcObject(), cXActionObject.getQosObject(), cXActionObject.getXcObject().getOamMipObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateXcBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisXC = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_XC);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}
	public void updateDual(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject,AcQosObject,
		// AcObject AcObject

		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getDualObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisDual Dual = new AnalysisDual();
		int seqId = super.getSeqId();
		byte[] updateXcBytes = Dual.updateDual(cXActionObject.getDualObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateXcBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		Dual = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_DUAL);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}
	/**
	 * 初始化设备XC
	 * 
	 * 
	 */
	public void clearNE(OperationObject operationObject, CXActionObject cXActionObject) {
		
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getXcObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisNE analysisNE = new AnalysisNE();
		int seqId = super.getSeqId();
		byte[] clearXcBytes = analysisNE.clearNE(cXActionObject.getPtnNeObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(clearXcBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisNE = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_XC);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
		operationObject.setSuccess(true);
	    operationObject.getCxActionObjectList().get(0).setStatus("配置成功");
	}

	/**
	 * 删除XC
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 * @param AcObjectAC对象
	 */
	public void deleteXC(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject,
		// AcQosObject,AcObject AcObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getXcObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisXC analysisXC = new AnalysisXC();
		int seqId = super.getSeqId();
		byte[] deleteXcBytes = analysisXC.deleteXc(cXActionObject.getXcObject(), cXActionObject.getQosObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteXcBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisXC = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_XC);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}
	public void deleteEthEfmOam(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject,
		// AcQosObject,AcObject AcObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEfmObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisEthEfmOam analysis = new AnalysisEthEfmOam();
		int seqId = super.getSeqId();
		byte[] deleteXcBytes = analysis.deleteEfm(cXActionObject.getEfmObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteXcBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysis = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_EFM);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 查询全部xc
	 * 
	 * @author kk
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void selectXc(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, TunnelObject tunnelObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisXC analysisXC = new AnalysisXC();
		int seqId = super.getSeqId();
		byte[] createTunnelBytes = analysisXC.selectAllXc(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createTunnelBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisXC = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_XC);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建ETH PW
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * @param pwEthObject
	 */
	public void createPwEthObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPwEthObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPWETH analysisPWETH = new AnalysisPWETH();
		int seqId = super.getSeqId();
		byte[] createPWETHBytes = analysisPWETH.createPWETH(cXActionObject.getQosObject(), cXActionObject.getPwEthObject(), cXActionObject.getPwEthObject().getOam(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createPWETHBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPWETH = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETHPW);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 更新ETH PW
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * @param pwEthObject
	 */
	public void updatePwEthObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPwEthObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPWETH analysisPWETH = new AnalysisPWETH();
		int seqId = super.getSeqId();
		byte[] updatePWETHBytes = analysisPWETH.updatePWETH(cXActionObject.getQosObject(), cXActionObject.getPwEthObject(), cXActionObject.getPwEthObject().getOam(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updatePWETHBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPWETH = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETHPW);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 删除ETH PW
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * @param pwEthObject
	 */
	public void deletePwEthObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPwEthObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPWETH analysisPWETH = new AnalysisPWETH();
		int seqId = super.getSeqId();
		byte[] updatePWETHBytes = analysisPWETH.deletePWETH(cXActionObject.getQosObject(), cXActionObject.getPwEthObject(), cXActionObject.getPwEthObject().getOam(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updatePWETHBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPWETH = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETHPW);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询全部PwEthObject
	 * 
	 * @author kk
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void selectPwEthObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, TunnelObject tunnelObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPWETH analysisPWETH = new AnalysisPWETH();
		int seqId = super.getSeqId();
		byte[] createTunnelBytes = analysisPWETH.selectAllPWETH(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createTunnelBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPWETH = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETHPW);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建PDH PW
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * @param pwEthObject
	 */
	public void createPwPdhObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPwPdhObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPWPDH analysisPWPDH = new AnalysisPWPDH();
		int seqId = super.getSeqId();
		byte[] createPWPDHBytes = analysisPWPDH.createPWPDH(cXActionObject.getPwPdhObject(), cXActionObject.getPwPdhObject().getOam(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createPWPDHBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPWPDH = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PDHPW);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 更新PDH PW
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * @param pwEthObject
	 */
	public void updatePwPdhObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPwPdhObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPWPDH analysisPWPDH = new AnalysisPWPDH();
		int seqId = super.getSeqId();
		byte[] updatePWPDHBytes = analysisPWPDH.updatePWPDH(cXActionObject.getPwPdhObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updatePWPDHBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPWPDH = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PDHPW);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 删除PDH PW
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * @param pwEthObject
	 */
	public void deletePwPdhObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPwPdhObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPWPDH analysisPWPDH = new AnalysisPWPDH();
		int seqId = super.getSeqId();
		byte[] updatePWPDHBytes = analysisPWPDH.deletePWPDH(cXActionObject.getPwPdhObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updatePWPDHBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPWPDH = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PDHPW);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询全部PwpPhObject
	 * 
	 * @author kk
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void selectPwPdhObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, TunnelObject tunnelObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPWPDH analysisPWPDH = new AnalysisPWPDH();
		int seqId = super.getSeqId();
		byte[] createTunnelBytes = analysisPWPDH.selectAllPWPDH(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createTunnelBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPWPDH = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PDHPW);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建SDH PW
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * @param pwEthObject
	 */
	public void createPwSdhObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPwSdhObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPWSDH analysisPWSDH = new AnalysisPWSDH();
		int seqId = super.getSeqId();
		byte[] createPWSDHBytes = analysisPWSDH.createPWSDH(cXActionObject.getPwSdhObject(), cXActionObject.getPwSdhObject().getOam(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createPWSDHBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPWSDH = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SDHPW);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 更新SDH PW
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * @param pwEthObject
	 */
	public void updatePwSdhObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPwSdhObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPWSDH analysisPWSDH = new AnalysisPWSDH();
		int seqId = super.getSeqId();
		byte[] updatePWSDHBytes = analysisPWSDH.updatePWSDH(cXActionObject.getPwSdhObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updatePWSDHBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPWSDH = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PDHPW);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}

	/**
	 * 删除SDH PW
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * @param pwEthObject
	 */
	public void deletePwSdhObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPwSdhObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPWSDH analysisPWSDH = new AnalysisPWSDH();
		int seqId = super.getSeqId();
		byte[] updatePWSDHBytes = analysisPWSDH.deletePWSDH(cXActionObject.getPwSdhObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updatePWSDHBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPWSDH = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SDHPW);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询全部PwSdhObject
	 * 
	 * @author kk
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void selectPwSdhObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, TunnelObject tunnelObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPWSDH analysisPWSDH = new AnalysisPWSDH();
		int seqId = super.getSeqId();
		byte[] createTunnelBytes = analysisPWSDH.selectAllPWSDH(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createTunnelBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPWSDH = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SDHPW);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建ELine业务
	 * 
	 * @param CXNEObject网元对象
	 * @param ELineObjectELine对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectPW对象
	 * @param QosObjectQOS对象
	 */
	public void createELineObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ELineObject ELineObject, AcObject AcObject,
		// PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getElineObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisELine analysisELine = new AnalysisELine();
		int seqId = super.getSeqId();
		byte[] createElineBytes = analysisELine.createEline(cXActionObject.getElineObject(), cXActionObject.getAcObject(), cXActionObject.getPwEthObject(), cXActionObject.getEthPortObject(), cXActionObject.getAcObject().getOam(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createElineBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisELine = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ELINE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 删除ELine业务
	 * 
	 * @param CXNEObject网元对象
	 * @param ELineObjectELine对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectPW对象
	 * @param QosObjectQOS对象
	 */
	public void deleteELineObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ELineObject ELineObject, AcObject AcObject,
		// PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getElineObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisELine analysisELine = new AnalysisELine();
		int seqId = super.getSeqId();
		byte[] deleteElineBytes = analysisELine.deleteEline(cXActionObject.getElineObject(), cXActionObject.getAcObject(), cXActionObject.getPwEthObject(), cXActionObject.getEthPortObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteElineBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisELine = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ELINE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询ELine业务
	 * 
	 * @param CXNEObject网元对象
	 * @param ELineObjectELine对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectPW对象
	 * @param QosObjectQOS对象
	 */
	public void selectELineObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ELineObject ELineObject, AcObject AcObject,
		// PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getElineObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisELine analysisELine = new AnalysisELine();
		int seqId = super.getSeqId();
		byte[] deleteElineBytes = analysisELine.selectAllELine(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteElineBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisELine = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ELINE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建ELan业务
	 * 
	 * @param CXNEObject网元对象
	 * @param ELanObjectELan对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectPW对象
	 * @param QosObjectQOS对象
	 */
	public void createELanObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ELanObject ELanObject, AcObject AcObject,
		// PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getElanObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisELAN analysisELAN = new AnalysisELAN();
		int seqId = super.getSeqId();
		byte[] createELanBytes = analysisELAN.createELAN(cXActionObject.getElanObject(), cXActionObject.getEthPortObject(), cXActionObject.getAcObject(), cXActionObject.getPwEthObjectList(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createELanBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisELAN = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ELAN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 删除ELan业务
	 * 
	 * @param CXNEObject网元对象
	 * @param ELanObjectELan对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectPW对象
	 * @param QosObjectQOS对象
	 */
	public void deleteELanObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ELanObject ELanObject, AcObject AcObject,
		// PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getElanObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisELAN analysisELAN = new AnalysisELAN();
		int seqId = super.getSeqId();
		byte[] createELanBytes = analysisELAN.deleteELAN(cXActionObject.getElanObject(), cXActionObject.getEthPortObject(), cXActionObject.getAcObject(), cXActionObject.getPwEthObjectList(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createELanBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisELAN = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ELAN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询ELan业务
	 * 
	 * @param CXNEObject网元对象
	 * @param ELanObjectELan对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectPW对象
	 * @param QosObjectQOS对象
	 */
	public void selectELanObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ELanObject ELanObject, AcObject AcObject,
		// PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getElanObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisELAN analysisELAN = new AnalysisELAN();
		int seqId = super.getSeqId();
		byte[] createELanBytes = analysisELAN.selectAllELan(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createELanBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisELAN = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ELAN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建ETree业务
	 * 
	 * @param CXNEObject网元对象
	 * @param ETreeObjectETree对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectListPW对象
	 * @param QosObjectQOS对象
	 */
	public void createETreeObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ETreeObject ETreeObject, AcObject AcObject,
		// List<PwEthObject> pwEthObjectList
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEtreeObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisETree analysisETree = new AnalysisETree();
		int seqId = super.getSeqId();
		byte[] createETreeBytes = analysisETree.createETree(cXActionObject.getEtreeObject(), cXActionObject.getEthPortObject(), cXActionObject.getAcObject(), cXActionObject.getPwEthObjectList(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createETreeBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisETree = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETREE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建ETree业务
	 * 
	 * @param CXNEObject网元对象
	 * @param ETreeObjectETree对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectListPW对象
	 * @param QosObjectQOS对象
	 */
	public void deleteETreeObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ETreeObject ETreeObject, AcObject AcObject,
		// List<PwEthObject> pwEthObjectList
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEtreeObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisETree analysisETree = new AnalysisETree();
		int seqId = super.getSeqId();
		byte[] createETreeBytes = analysisETree.deleteETree(cXActionObject.getEtreeObject(), cXActionObject.getEthPortObject(), cXActionObject.getAcObject(), cXActionObject.getPwEthObjectList(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createETreeBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisETree = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETREE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询ETree业务
	 * 
	 * @param CXNEObject网元对象
	 * @param ETreeObjectETree对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectListPW对象
	 * @param QosObjectQOS对象
	 */
	public void selectETreeObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ETreeObject ETreeObject, AcObject AcObject,
		// List<PwEthObject> pwEthObjectList
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEtreeObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisETree analysisETree = new AnalysisETree();
		int seqId = super.getSeqId();
		byte[] createETreeBytes = analysisETree.selectAllETree(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createETreeBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisETree = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETREE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建PDH CES业务
	 * 
	 * @param operationObject网元对象
	 * @param cXActionObject对象
	 */
	public void createPDHCESObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getCesObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCes analysisCes = new AnalysisCes();
		int seqId = super.getSeqId();
		byte[] createCESBytes = analysisCes.createPdhCes(cXActionObject.getCesObject(), cXActionObject.getPwPdhObject(), cXActionObject.getPdhPortObject(), cXActionObject.getPdhAcObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createCESBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisCes = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CESPDH);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 删除PDH CES业务
	 * 
	 * @param operationObject网元对象
	 * @param cXActionObject对象
	 */
	public void deletePDHCESObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getCesObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCes analysisCes = new AnalysisCes();
		int seqId = super.getSeqId();
		byte[] deleteCesBytes = analysisCes.deletePDHCes(cXActionObject.getCesObject(), cXActionObject.getPwPdhObject(), cXActionObject.getPdhPortObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteCesBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisCes = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CESPDH);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询PDH CES业务
	 * 
	 * @param operationObject网元对象
	 * @param cXActionObject对象
	 */
	public void selectPDHCESObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getCesObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCes analysisCes = new AnalysisCes();
		int seqId = super.getSeqId();
		byte[] deleteCesBytes = analysisCes.selectAllCes(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteCesBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisCes = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CESPDH);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建SDH CES业务
	 * 
	 * @param operationObject网元对象
	 * @param cXActionObject对象
	 */
	public void createSDHCESObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getCesObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCes analysisCes = new AnalysisCes();
		int seqId = super.getSeqId();
		byte[] createCESBytes = analysisCes.createSdhCes(cXActionObject.getCesObject(), cXActionObject.getPwSdhObject(), cXActionObject.getSdhPortObject(), cXActionObject.getSdhAcObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createCESBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisCes = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CESSDH);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 删除SDH CES业务
	 * 
	 * @param operationObject网元对象
	 * @param cXActionObject对象
	 */
	public void deleteSDHCESObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getCesObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCes analysisCes = new AnalysisCes();
		int seqId = super.getSeqId();
		byte[] createCESBytes = analysisCes.deleteSDHCes(cXActionObject.getCesObject(), cXActionObject.getPwSdhObject(), cXActionObject.getSdhPortObject(), cXActionObject.getSdhAcObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createCESBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisCes = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CESSDH);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 插卡
	 * 
	 * @param operationObject网元对象
	 * @param cXActionObject对象
	 */
	public void createCardObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getSlotObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisSlot analysisSlot = new AnalysisSlot();
		int seqId = super.getSeqId();
		byte[] createCardBytes = analysisSlot.setSlot(cXActionObject.getSlotObject().getId(), cXActionObject.getSlotObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createCardBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisSlot = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SLOT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询槽位信息
	 * 
	 * @param operationObject网元对象
	 * @param cXActionObject对象
	 */
	public void seleteAllSlotObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPtnNeObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisSlot analysisSlot = new AnalysisSlot();
		int seqId = super.getSeqId();
		byte[] getAllSlotBytes = analysisSlot.getAllSlot(cXActionObject.getPtnNeObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(getAllSlotBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisSlot = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SLOT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询所有告警
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void seleteAlarmAll(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEtreeObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisAlarm analysisAlarm = new AnalysisAlarm();
		int seqId = super.getSeqId();
		byte[] selsetAlarm = analysisAlarm.getGetAll(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(selsetAlarm);
		cXDriveUtilObject.setCXNEObject(cXNEObject);// 赋值网元对象
		analysisAlarm = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ALARM);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询所有当前性能
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void seletePersvrAll(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEtreeObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

//		AnalysisPersvr analysisPersvr = new AnalysisPersvr();
//		int seqId = super.getSeqId();
//		byte[] selsetPersvr = analysisPersvr.getPersvr(cXActionObject.getSlot(), cXActionObject.getPersvrTime(), 0, seqId);
//		cXDriveUtilObject.setSeqId(seqId + "");
//		cXDriveUtilObject.setSendCommands(selsetPersvr);
//		cXDriveUtilObject.setCXNEObject(cXNEObject);// 赋值网元对象
//		analysisPersvr = null;
//
//		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PERFORMANCE);
//		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);
//
//		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**查询历史性能数据
	 * ip : 目标机器 ip地址
	 * mh : m15 / h24
	 * filename : 类似 m15-201207130730.per ; h24-20120712.per
	 * **/
	public void seletePersvrHis(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEtreeObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数
//
//		AnalysisPersvr analysisPersvr = new AnalysisPersvr();
//		int seqId = super.getSeqId(); //persvrobjectList
//		cXActionObject.setPersvrobjectList( analysisPersvr.analysisPersvrFile(cXNEObject.getNeIp(),cXActionObject.getPersvrTime(),cXActionObject.getPersvrFileName()) );
////		byte[] selsetPersvr = analysisPersvr.getPersvr(cXActionObject.getSlot(), cXActionObject.getPersvrTime(), 0, seqId);
////		cXDriveUtilObject.setSeqId(seqId + "");
////		cXDriveUtilObject.setSendCommands(selsetPersvr);
////		cXDriveUtilObject.setCXNEObject(cXNEObject);// 赋值网元对象
//		analysisPersvr = null;
//
//		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PERFORMANCE);
//		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);
//
//		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 修改NE信息
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void updateNE(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPtnNeObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisNE analysisNE = new AnalysisNE();
		int seqId = super.getSeqId();
		byte[] selsetNE = analysisNE.setNE(cXActionObject.getPtnNeObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(selsetNE);
		cXDriveUtilObject.setCXNEObject(cXNEObject);// 赋值网元对象
		analysisNE = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_NE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询NE信息
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void seleteNE(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(null);// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisNE analysisNE = new AnalysisNE();
		int seqId = super.getSeqId();
		byte[] selsetNE = analysisNE.getNE(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(selsetNE);
		cXDriveUtilObject.setCXNEObject(cXNEObject);// 赋值网元对象
		analysisNE = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_NE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建OSPF
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void createOSPFObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOSPF analysisOSPF = new AnalysisOSPF();
		int seqId = super.getSeqId();
		byte[] createOspf = analysisOSPF.createOSPF(cXActionObject.getOSPFObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOspf);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisOSPF = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPF);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 修改OSPF
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void updateOSPFObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOSPF analysisOSPF = new AnalysisOSPF();
		int seqId = super.getSeqId();
		byte[] updateOspf = analysisOSPF.updateOSPF(cXActionObject.getOSPFObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateOspf);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisOSPF = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPF);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 删除OSPF
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void deleteOSPFObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOSPF analysisOSPF = new AnalysisOSPF();
		int seqId = super.getSeqId();
		byte[] deleteOspf = analysisOSPF.deleteOSPF(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteOspf);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisOSPF = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPF);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建OSPFAREA
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void createOSPFAREAObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisArea AnalysisArea = new AnalysisArea();
		int seqId = super.getSeqId();
		byte[] createAREA = AnalysisArea.createAREA(cXActionObject.getOSPFAREAObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createAREA);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		AnalysisArea = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPFAREA);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 *更新OSPFAREA
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void updateOSPFAREAObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisArea AnalysisArea = new AnalysisArea();
		int seqId = super.getSeqId();
		byte[] createAREA = AnalysisArea.updateAREA(cXActionObject.getOSPFAREAObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createAREA);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		AnalysisArea = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPFAREA);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 删除OSPFAREA
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void deleteOSPFAREAObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisArea AnalysisArea = new AnalysisArea();
		int seqId = super.getSeqId();
		byte[] createAREA = AnalysisArea.deleteAREA(cXActionObject.getOSPFAREAObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createAREA);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		AnalysisArea = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPFAREA);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建或更新重分发
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void createAndUpdateOSPFRedistributeObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisRedistribute analysisRedistribute = new AnalysisRedistribute();
		int seqId = super.getSeqId();
		byte[] createRedistribute = analysisRedistribute.setRedistribute(cXActionObject.getRedistributeObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createRedistribute);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisRedistribute = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_REDISTRIBUTE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 删除重分发
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void deleteOSPFRedistributeObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisRedistribute analysisRedistribute = new AnalysisRedistribute();
		int seqId = super.getSeqId();
		byte[] createRedistribute = analysisRedistribute.deleteRedistribute(cXActionObject.getRedistributeObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createRedistribute);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisRedistribute = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_REDISTRIBUTE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 更新qos
	 * 
	 * @author kk
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void updateQos(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisQOS analysisQOS = new AnalysisQOS();
		int seqId = super.getSeqId();
		byte[] createAREA = analysisQOS.updateQOSByte(cXActionObject.getQosObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createAREA);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisQOS = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_QOS);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void updateStaticRoute(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getStaticRouteObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisStaticRoute an = new AnalysisStaticRoute();
		int seqId = super.getSeqId();
		byte[] up = an.updateStaticRoute(cXActionObject.getStaticRouteObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(up);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_STATICROUTE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void updateCos2vlanpri(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getCos2vlanpriObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCos2vlanpri an = new AnalysisCos2vlanpri();
		int seqId = super.getSeqId();
		byte[] up = an.updateCos2vlanpri(cXActionObject.getCos2vlanpriObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(up);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		an = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_COS2VPN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 查询全部qos
	 * 
	 * @author kk
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void selectQos(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisQOS analysisQOS = new AnalysisQOS();
		int seqId = super.getSeqId();
		byte[] createAREA = analysisQOS.selectAllQOSByte(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createAREA);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisQOS = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_QOS);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 修改MCN
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void updateMcn(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisMCN analysisMCN = new AnalysisMCN();
		int seqId = super.getSeqId();
		byte[] updateMcn = analysisMCN.setMCN(cXActionObject.getMcnObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateMcn);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisMCN = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_MCN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建CCN
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void createCcn(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCCN analysisCCN = new AnalysisCCN();
		int seqId = super.getSeqId();
		byte[] createCcn = analysisCCN.createCCN(cXActionObject.getCcnObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createCcn);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisCCN = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CCN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 删除CCN
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void deleteCcn(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCCN analysisCCN = new AnalysisCCN();
		int seqId = super.getSeqId();
		byte[] createCcn = analysisCCN.deleteCCN(cXActionObject.getCcnObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createCcn);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisCCN = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CCN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 修改ccn
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void updateCcn(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCCN analysisCCN = new AnalysisCCN();
		int seqId = super.getSeqId();
		byte[] createCcn = analysisCCN.updateCCN(cXActionObject.getCcnObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createCcn);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisCCN = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CCN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	public void createOspfInterfaceCcn(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOSPFInterfaces analysisOSPFInterfaces = new AnalysisOSPFInterfaces();
		int seqId = super.getSeqId();
		byte[] createOspfInterface = analysisOSPFInterfaces.createOSPFInterfacesCCN(cXActionObject.getCcnObject(), cXActionObject.getOSPFInterfacesObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOspfInterface);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		createOspfInterface = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPFINTERFACE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	public void deleteOspfInterfaceCcn(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOSPFInterfaces analysisOSPFInterfaces = new AnalysisOSPFInterfaces();
		int seqId = super.getSeqId();
		byte[] createOspfInterface = analysisOSPFInterfaces.deleteOSPFInterfacesCCN(cXActionObject.getCcnObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOspfInterface);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		createOspfInterface = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPFINTERFACE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	public void selectCcn(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCCN analysisCCN = new AnalysisCCN();
		int seqId = super.getSeqId();
		byte[] selectCCN = analysisCCN.selectAllCCN(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(selectCCN);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		selectCCN = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CCN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	public void updateOspfInterfaceCcn(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOSPFInterfaces analysisOSPFInterfaces = new AnalysisOSPFInterfaces();
		int seqId = super.getSeqId();
		byte[] createOspfInterface = analysisOSPFInterfaces.updateOSPFInterfacesCCN(cXActionObject.getCcnObject(), cXActionObject.getOSPFInterfacesObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOspfInterface);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		createOspfInterface = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPFINTERFACE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	public void updateOspfInterfaceMcn(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOSPFInterfaces analysisOSPFInterfaces = new AnalysisOSPFInterfaces();
		int seqId = super.getSeqId();
		byte[] createOspfInterface = analysisOSPFInterfaces.updateOSPFInterfacesMCN(cXActionObject.getOSPFInterfacesObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOspfInterface);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		createOspfInterface = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPFINTERFACE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	public void deleteOspfInterfaceMcn(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOSPFInterfaces analysisOSPFInterfaces = new AnalysisOSPFInterfaces();
		int seqId = super.getSeqId();
		byte[] createOspfInterface = analysisOSPFInterfaces.deleteOSPFInterfacesMCN(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOspfInterface);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		createOspfInterface = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPFINTERFACE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	public void createOspfInterfaceMcn(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOSPFInterfaces analysisOSPFInterfaces = new AnalysisOSPFInterfaces();
		int seqId = super.getSeqId();
		byte[] createOspfInterface = analysisOSPFInterfaces.createOSPFInterfacesMCN(cXActionObject.getOSPFInterfacesObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOspfInterface);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		createOspfInterface = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPFINTERFACE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	public void createLag(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisLag analysisLag = new AnalysisLag();
		int seqId = super.getSeqId();
		byte[] createOspfInterface = analysisLag.createLag(cXActionObject.getEthObjectList(), cXActionObject.getLagObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOspfInterface);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		createOspfInterface = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PORTLAG);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	public void deleteLag(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisLag analysisLag = new AnalysisLag();
		int seqId = super.getSeqId();
		byte[] createOspfInterface = analysisLag.deleteLag(cXActionObject.getEthObjectList(), cXActionObject.getLagObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOspfInterface);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		createOspfInterface = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PORTLAG);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	public void updateLag(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisLag analysisLag = new AnalysisLag();
		int seqId = super.getSeqId();
		byte[] createOspfInterface = analysisLag.updateLag(cXActionObject.getEthObjectList(), cXActionObject.getLagObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOspfInterface);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		createOspfInterface = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PORTLAG);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询lag
	 * 
	 * @author kk
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void selectLag(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, TunnelObject tunnelObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisLag analysisLag = new AnalysisLag();
		int seqId = super.getSeqId();
		byte[] selectLag = analysisLag.selectLag(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(selectLag);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisLag = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PORTLAG);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 根据tunnel查询LSP
	 * 
	 * @author kk
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void selectLspByTunnel(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, TunnelObject tunnelObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisTunnel analysisTunnel = new AnalysisTunnel();
		int seqId = super.getSeqId();
		byte[] createTunnelBytes = analysisTunnel.selectAllLSP(cXActionObject.getTunnelObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createTunnelBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisTunnel = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_LSP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 根据acqos名称查询qos
	 * 
	 * @author kk
	 * 
	 * @param CXNEObject网元对象
	 * @param QosObjectQOS对象
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public void selectAcqos(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, QosObject QosObject, TunnelObject tunnelObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getEthPortObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisQOS analysisQos = new AnalysisQOS();
		int seqId = super.getSeqId();
		byte[] createTunnelBytes = analysisQos.selectAcQOSByte(cXActionObject.getAcObject().getQos(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createTunnelBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisQos = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_QOSAC);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询OSPF信息
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void selectOSPFObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(null);// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOSPF analysisOSPF = new AnalysisOSPF();
		int seqId = super.getSeqId();
		byte[] selsetAnalysisOSPF = analysisOSPF.selectOSPF(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(selsetAnalysisOSPF);
		cXDriveUtilObject.setCXNEObject(cXNEObject);// 赋值网元对象
		analysisOSPF = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPF);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询OspfAREAObject信息
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void selectOspfAREAObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(null);// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisArea analysisArea = new AnalysisArea();
		int seqId = super.getSeqId();
		byte[] selsetAnalysisOSPFArea = analysisArea.selectArea(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(selsetAnalysisOSPFArea);
		cXDriveUtilObject.setCXNEObject(cXNEObject);// 赋值网元对象
		analysisArea = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPFAREA);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询RedistributeObject信息
	 * 
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void selectRedistributeObject(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(null);// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisRedistribute analysisRedistribute = new AnalysisRedistribute();
		int seqId = super.getSeqId();
		byte[] selsetAnalysisRedistribute = analysisRedistribute.selectRedistribute(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(selsetAnalysisRedistribute);
		cXDriveUtilObject.setCXNEObject(cXNEObject);// 赋值网元对象
		analysisRedistribute = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_REDISTRIBUTE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询OSPFMCN接口
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void selectOspfInterfaceMCN(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(null);// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOSPFInterfaces analysisOSPFInterfaces = new AnalysisOSPFInterfaces();
		int seqId = super.getSeqId();
		byte[] selsetAnalysisOSPFInterfaces = analysisOSPFInterfaces.selectOSPFInterfacesMCN(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(selsetAnalysisOSPFInterfaces);
		cXDriveUtilObject.setCXNEObject(cXNEObject);// 赋值网元对象
		analysisOSPFInterfaces = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPFINTERFACE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询OSPFCCN接口
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void selectOspfInterfaceCCN(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(null);// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOSPFInterfaces analysisOSPFInterfaces = new AnalysisOSPFInterfaces();
		int seqId = super.getSeqId();
		byte[] selsetAnalysisOSPFInterfaces = analysisOSPFInterfaces.selectOSPFInterfacesAllCCN(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(selsetAnalysisOSPFInterfaces);
		cXDriveUtilObject.setCXNEObject(cXNEObject);// 赋值网元对象
		analysisOSPFInterfaces = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OSPFINTERFACE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询MCN
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void selectMcn(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(null);// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisMCN analysisMcn = new AnalysisMCN();
		int seqId = super.getSeqId();
		byte[] selsetAnalysisMcn = analysisMcn.selectMCN(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(selsetAnalysisMcn);
		cXDriveUtilObject.setCXNEObject(cXNEObject);// 赋值网元对象
		analysisMcn = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_MCN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询PortStm
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void selectPortStm(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(null);// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisSDHPort analysisSDHPort = new AnalysisSDHPort();
		int seqId = super.getSeqId();
		byte[] selsetAnalysisSDHPort = analysisSDHPort.selectAllSdhPort(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(selsetAnalysisSDHPort);
		cXDriveUtilObject.setCXNEObject(cXNEObject);// 赋值网元对象
		analysisSDHPort = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SDHPORT);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 查询PortStmTimesLot
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void selectPortStmTimesLot(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(null);// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisSdhAc analysisSdhAc = new AnalysisSdhAc();
		int seqId = super.getSeqId();
		byte[] selsetAnalysisSdhAc = analysisSdhAc.selectAllSDHAc(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(selsetAnalysisSdhAc);
		cXDriveUtilObject.setCXNEObject(cXNEObject);// 赋值网元对象
		analysisSdhAc = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SDHAC);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建OAM
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void createOam(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOAM analysisOam = new AnalysisOAM();
		int seqId = super.getSeqId();
		byte[] createOam = analysisOam.createOAMByte(cXActionObject.getOamType(), cXActionObject.getOamPath(), cXActionObject.getOamObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOam);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		createOam = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OAM);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 修改OAM
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void updateOam(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOAM analysisOam = new AnalysisOAM();
		int seqId = super.getSeqId();
		byte[] createOam = analysisOam.updateOAMByte(cXActionObject.getOamType(), cXActionObject.getOamPath(), cXActionObject.getOamObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOam);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		createOam = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OAM);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 创建MipOam
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void createMipOam(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOAM analysisOam = new AnalysisOAM();
		int seqId = super.getSeqId();
		byte[] createOam = analysisOam.createMipOAMByte(cXActionObject.getOamPath(), cXActionObject.getOamMipObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOam);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		createOam = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_MIPOAM);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_CREATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 修改MipOam
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void updateMipOam(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOAM analysisOam = new AnalysisOAM();
		int seqId = super.getSeqId();
		byte[] createOam = analysisOam.updateMipOAMByte(cXActionObject.getOamPath(), cXActionObject.getOamMipObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOam);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		createOam = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_MIPOAM);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * 删除OAM
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void deleteOam(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisOAM analysisOam = new AnalysisOAM();
		int seqId = super.getSeqId();
		byte[] createOam = analysisOam.deleteOAMByte(cXActionObject.getOamType(), cXActionObject.getOamPath(), cXActionObject.getOamObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(createOam);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		createOam = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_OAM);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}

	/**
	 * tunnel倒换命令
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void setApscmd(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisApscmd analysisApscmd = new AnalysisApscmd();
		int seqId = super.getSeqId();
		byte[] setApscmd = analysisApscmd.setApscmd(cXActionObject.getTunnelObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(setApscmd);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		setApscmd = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_APSCMD);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**ACN add by stones**/
	public void selectACN(OperationObject operationObject, CXActionObject cXActionObject){
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getOSPFAREAObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisACN analysisACN = new AnalysisACN();
		int seqId = super.getSeqId();
		byte[] selectACN = analysisACN.selectAllACN(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(selectACN);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		selectACN = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ACN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 查询 Msp保护
	 * **/
	public void selectMsp(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//--
		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getMspObject());// 赋值对象 -----
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisMsp ana = new AnalysisMsp();
		int seqId = super.getSeqId();
		byte[] select = ana.selectMsp( 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(select);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		ana = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_MSP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void selectDual(OperationObject operationObject, CXActionObject cXActionObject) {
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getDualObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisDual Dual = new AnalysisDual();
		int seqId = super.getSeqId();
		byte[] select = Dual.selectDual(0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(select);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		Dual = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_DUAL);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	public void deleteAllACN(OperationObject operationObject, CXActionObject cXActionObject){
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPwEthObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisACN acn = new AnalysisACN();
		int seqId = super.getSeqId();
		byte[] deleteAllAcnBytes = acn.deleteAllACN(cXActionObject.getAcnObjectList(), 0, seqId);//.deletePWETH(cXActionObject.getQosObject(), cXActionObject.getPwEthObject(), cXActionObject.getPwEthObject().getOam(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(deleteAllAcnBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		acn = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ACN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_DELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 得到 tcpNetworkList所有连接的 不可到达(isConnected = false)的连接的ip list 
	 * add 20130718
	 * **/
	public List<String> getTcpNetworkList(){
		List<String> neIpList = new ArrayList<String>();
		for(Map.Entry<String,TcpNetwork> m :super.tcpNetworkList.entrySet()){
			String mip = m.getKey();
			TcpNetwork tn = m.getValue();
			if(!tn.isLive()){
				neIpList.add(mip);
			}
		}
		return neIpList;
	}
	public void removeTcp(String ip){
		super.tcpNetworkList.remove(ip);
	}
//	/**
//	 * update 网元PTP配置
//	 * 
//	 * @param PtpObject网元对象
//	 * @param ethPortObject端口对象
//	 * @param AcObjectAC对象
//	 */
//	public void updatePtpObject(OperationObject operationObject, CXActionObject cXActionObject) {
//		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//		//--
//		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
//		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPtpObject());// 赋值对象
//		cXDriveUtilObject.setStates(0);// 待发送状态
//		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
//		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
//		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数
//
//		AnalysisRING anRing = new AnalysisRING();
//		int seqId = super.getSeqId();
//		byte[] createRing = anRing.selectRing(null, 0, seqId);
//		cXDriveUtilObject.setSeqId(seqId + "");
//		cXDriveUtilObject.setSendCommands(createRing);
//		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
//		anRing = null;
//
//		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PTPSITEOBJECT);
//		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SAVE);
//
//		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
//	}
//	/**
//	 * 网元时钟属性
//	 * 
//	 * @param ClockObject网元对象
//	 * @param ethPortObject端口对象
//	 * @param AcObjectAC对象
//	 */
//	public void updateClockObject(OperationObject operationObject, CXActionObject cXActionObject) {
//		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
//		//--
//		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
//		cXDriveUtilObject.setPtnDataObject(cXActionObject.getClockObject());// 赋值对象
//		cXDriveUtilObject.setStates(0);// 待发送状态
//		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
//		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
//		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数
//
//		AnalysisRING anRing = new AnalysisRING();
//		int seqId = super.getSeqId();
//		byte[] createRing = anRing.selectRing(null, 0, seqId);
//		cXDriveUtilObject.setSeqId(seqId + "");
//		cXDriveUtilObject.setSendCommands(createRing);
//		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
//		anRing = null;
//
//		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PTPSITEOBJECT);
//		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SAVE);
//
//		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
//	}
//	/**
//	 * 建立 ClockObject
//	 * 
//	 * @param CXNEObject网元对象
//	 * @param ethPortObject端口对象
//	 * @param AcObjectAC对象
//	 */
//	public void savePtpObject(OperationObject operationObject, CXActionObject cXActionObject) {
//		CXNEObject cXNEObject = cXActionObject.getCxNeObject();
////--
//		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
//		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPtpObject());// 赋值对象
//		cXDriveUtilObject.setStates(0);// 待发送状态
//		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
//		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
//		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数
//
//		AnalysisRING anRing = new AnalysisRING();
//		int seqId = super.getSeqId();
//		byte[] createRing = anRing.selectRing(null, 0, seqId);
//		cXDriveUtilObject.setSeqId(seqId + "");
//		cXDriveUtilObject.setSendCommands(createRing);
//		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
//		anRing = null;
//
//		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_PTPSITEOBJECT);
//		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SAVE);
//
//		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
//	}
	/**
	 * 更新 温度管理 （高温，低温门限）
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 */
	public void updateSlotTemp(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject

		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getSlotTempObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisSlotTemp analysisSlotTemp = new AnalysisSlotTemp();
		int seqId = super.getSeqId();
		byte[] setPortBytes = analysisSlotTemp.updateTemp(cXActionObject.getSlotTempObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(setPortBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisSlotTemp = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SLOTTEMP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}
	/**
	 * 查询 温度管理 （高温，低温门限）
	 * 
	 * @param CXNEObject网元对象
	 * @param ethPortObject端口对象
	 */
	public void selectSlotTemp(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, EthPortObject ethPortObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getSlotTempObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisSlotTemp analysisSlotTemp = new AnalysisSlotTemp();
		int seqId = super.getSeqId();
		byte[] setPortBytes = analysisSlotTemp.selectTemp(cXActionObject.getSlotTempObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(setPortBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisSlotTemp = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_SLOTTEMP);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);

	}
	/**
	 * updateELine业务
	 * 
	 * @param CXNEObject网元对象
	 * @param ELineObjectELine对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectPW对象
	 * @param QosObjectQOS对象
	 */
	public void updateELineObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ELineObject ELineObject, AcObject AcObject,
		// PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getElineObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisELine analysisELine = new AnalysisELine();
		int seqId = super.getSeqId();
		byte[] updateElineBytes = analysisELine.updateEline(cXActionObject.getElineObject(), cXActionObject.getAcObject(), cXActionObject.getPwEthObject(), cXActionObject.getEthPortObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateElineBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisELine = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ELINE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * updatePdhCes业务
	 * 
	 * @param CXNEObject网元对象
	 * @param ELineObjectELine对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectPW对象
	 * @param QosObjectQOS对象
	 */
	public void updatePdhCesObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ELineObject ELineObject, AcObject AcObject,
		// PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getElineObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCes analysisCes = new AnalysisCes();
		int seqId = super.getSeqId();
		byte[] updateCesBytes = analysisCes.updatePdhCes(cXActionObject.getCesObject(), cXActionObject.getPdhAcObject(), cXActionObject.getPwPdhObject(), cXActionObject.getPdhPortObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateCesBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisCes = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CES);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * updateSdhCes业务
	 * 
	 * @param CXNEObject网元对象
	 * @param ELineObjectELine对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectPW对象
	 * @param QosObjectQOS对象
	 */
	public void updateSdhCesObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ELineObject ELineObject, AcObject AcObject,
		// PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getElineObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCes analysisCes = new AnalysisCes();
		int seqId = super.getSeqId();
		byte[] updateCesBytes = analysisCes.updateSdhCes(cXActionObject.getCesObject(), cXActionObject.getSdhAcObject(), cXActionObject.getPwSdhObject(), cXActionObject.getSdhPortObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateCesBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisCes = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CES);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * updateEtree业务
	 * 
	 * @param CXNEObject网元对象
	 * @param ELineObjectELine对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectPW对象
	 * @param QosObjectQOS对象
	 */
	public void updateEtreeObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ELineObject ELineObject, AcObject AcObject,
		// PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getElineObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisETree analysisEtree = new AnalysisETree();
		int seqId = super.getSeqId();
		byte[] updateCesBytes = analysisEtree.updateEtree(cXActionObject.getEtreeObject(), cXActionObject.getPwUpdate(), cXActionObject.getAcObject(), cXActionObject.getEthPortObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateCesBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisEtree = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ETREE);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 查询光功率
	 * 
	 * @param CXNEObject网元对象
	 * @param ELineObjectELine对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectPW对象
	 * @param QosObjectQOS对象
	 */
	public void selectPowerObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ELineObject ELineObject, AcObject AcObject,
		// PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPowerObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPower analysisPower = new AnalysisPower();
		int seqId = super.getSeqId();
		byte[] updateCesBytes = analysisPower.select(cXActionObject.getPowerObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateCesBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPower = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_POWER);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 刷新板卡保护
	 * 
	 * @param CXNEObject网元对象
	 * @param ELineObjectELine对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectPW对象
	 * @param QosObjectQOS对象
	 */
	public void selectCardProObject(OperationObject operationObject, CXActionObject cXActionObject) {
		// CXNEObject CXNEObject, ELineObject ELineObject, AcObject AcObject,
		// PwEthObject pwEthObject
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getCardProObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisCard analysisCard = new AnalysisCard();
		int seqId = super.getSeqId();
		byte[] updateCesBytes = analysisCard.selectCardPro(cXActionObject.getCardProObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateCesBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisCard = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CARDPRO);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 更新板卡保护
	 * -------倒换
	 * 
	 * @param CXNEObject网元对象
	 * @param ELineObjectELine对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectPW对象
	 * @param QosObjectQOS对象
	 */
	public void updateCardProObject(OperationObject operationObject, CXActionObject cXActionObject) {
		
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPwEthObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPWETH analysisPwVlan = new AnalysisPWETH();
		int seqId = super.getSeqId();
		byte[] updateCesBytes = analysisPwVlan.updatePwVlan(cXActionObject.getPwEthObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateCesBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisPwVlan = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_CARDPRO);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_SELETE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 更新ELAN 业务
	 * -------倒换
	 * 
	 * @param CXNEObject网元对象
	 * @param ELineObjectELine对象
	 * @param AcObjectAC对象
	 * @param pwEthObjectPW对象
	 * @param QosObjectQOS对象
	 */
	public void updateElan(OperationObject operationObject, CXActionObject cXActionObject) {
		
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getElanObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisELAN analysisELAN = new AnalysisELAN();
		int seqId = super.getSeqId();
		byte[] updateCesBytes = analysisELAN.updateElan(cXActionObject.getElanObject(), cXActionObject.getPwUpdate(), cXActionObject.getEthPortObject(), cXActionObject.getAcObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateCesBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisELAN = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ELAN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
	/**
	 * 更新 pw下的 Vlan
	 * @param operationObject
	 * @param cXActionObject
	 */
	public void updatePwVlan(OperationObject operationObject, CXActionObject cXActionObject) {
		
		CXNEObject cXNEObject = cXActionObject.getCxNeObject();

		CXDriveUtilObject cXDriveUtilObject = new CXDriveUtilObject();
		cXDriveUtilObject.setPtnDataObject(cXActionObject.getPwEthObject());// 赋值对象
		cXDriveUtilObject.setStates(0);// 待发送状态
		cXDriveUtilObject.setSendDate(new Date());// 赋值发送时间
		cXDriveUtilObject.setActionId(cXActionObject.getActionId() + "");// 操作ID
		cXDriveUtilObject.setOperationObject(operationObject);// 赋值回调函数

		AnalysisPWETH analysisELAN = new AnalysisPWETH();
		int seqId = super.getSeqId();
		byte[] updateCesBytes = analysisELAN.updatePwVlan(cXActionObject.getPwEthObject(), 0, seqId);
		cXDriveUtilObject.setSeqId(seqId + "");
		cXDriveUtilObject.setSendCommands(updateCesBytes);
		cXActionObject.setCxNeObject(cXNEObject);// 赋值网元对象
		analysisELAN = null;

		cXDriveUtilObject.setObjType(CXDriveUtilObject.OBJ_ELAN);
		cXDriveUtilObject.setOperType(CXDriveUtilObject.OPER_UPDATE);

		super.cXMonitorSendCommandThreadMap.get(cXNEObject.getNeIp()).addDriveUtilObject(cXDriveUtilObject);
	}
}
