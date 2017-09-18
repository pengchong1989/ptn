package com.nms.drivechenxiao.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.drive.service.impl.MonitorCallbackThread;
import com.nms.drivechenxiao.analysis.AnalysisAlarm;
import com.nms.drivechenxiao.analysis.AnalysisInformation;
import com.nms.drivechenxiao.analysis.AnalysisSlot;
import com.nms.drivechenxiao.analysis.clock.AnalysisClock;
import com.nms.drivechenxiao.analysis.clock.AnalysisClockPort;
import com.nms.drivechenxiao.analysis.clock.AnalysisExtclk;
import com.nms.drivechenxiao.analysis.clock.AnalysisPtp;
import com.nms.drivechenxiao.analysis.clock.AnalysisPtpPort;
import com.nms.drivechenxiao.analysis.clock.AnalysisTod;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisAC;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisACN;
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
import com.nms.drivechenxiao.analysis.proc.AnalysisPersvr;
import com.nms.drivechenxiao.analysis.proc.Analysislog;
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
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.weihu.AnalysisLoopBack;
import com.nms.drivechenxiao.analysis.weihu.AnalysisPower;
import com.nms.drivechenxiao.analysis.weihu.PowerObject;
import com.nms.drivechenxiao.service.bean.acn.ACNObject;
import com.nms.drivechenxiao.service.bean.alarm.AlarmObject;
import com.nms.drivechenxiao.service.bean.ccn.CCNObject;
import com.nms.drivechenxiao.service.bean.clock.ClockObject;
import com.nms.drivechenxiao.service.bean.clock.ClockPortESObject;
import com.nms.drivechenxiao.service.bean.clock.ExtclkObject;
import com.nms.drivechenxiao.service.bean.clock.TodObject;
import com.nms.drivechenxiao.service.bean.lag.LagObject;
import com.nms.drivechenxiao.service.bean.mcn.MCNObject;
import com.nms.drivechenxiao.service.bean.oam.EfmObject;
import com.nms.drivechenxiao.service.bean.ospf.OSPFAREAObject;
import com.nms.drivechenxiao.service.bean.ospf.OSPFObject;
import com.nms.drivechenxiao.service.bean.ospf.interfaces.OSPFinterfacesObject;
import com.nms.drivechenxiao.service.bean.persvr.PersvrObject;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcQosObject;
import com.nms.drivechenxiao.service.bean.portpdh.PdhPortObject;
import com.nms.drivechenxiao.service.bean.protocols.CardProObject;
import com.nms.drivechenxiao.service.bean.protocols.Cos2vlanpriObject;
import com.nms.drivechenxiao.service.bean.protocols.DualObject;
import com.nms.drivechenxiao.service.bean.protocols.MspObject;
import com.nms.drivechenxiao.service.bean.protocols.ProtectLogObject;
import com.nms.drivechenxiao.service.bean.protocols.PtpObject;
import com.nms.drivechenxiao.service.bean.protocols.PtpPortObject;
import com.nms.drivechenxiao.service.bean.protocols.StaticRouteObject;
import com.nms.drivechenxiao.service.bean.protocols.Vlanpri2cngObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.RingObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.elsp.CostoexpObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.elsp.ExptocosObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.llsp.ClrtoexpObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.llsp.ExptoclrObject;
import com.nms.drivechenxiao.service.bean.protsdh.SdhPortObject;
import com.nms.drivechenxiao.service.bean.protsdh.ac.SdhAcObject;
import com.nms.drivechenxiao.service.bean.ptnne.PtnNeObject;
import com.nms.drivechenxiao.service.bean.pweth.PwEthObject;
import com.nms.drivechenxiao.service.bean.pwpdh.PwPdhObject;
import com.nms.drivechenxiao.service.bean.pwsdh.PwSdhObject;
import com.nms.drivechenxiao.service.bean.qos.QosObject;
import com.nms.drivechenxiao.service.bean.redistribute.RedistributeObject;
import com.nms.drivechenxiao.service.bean.service.ces.CesObject;
import com.nms.drivechenxiao.service.bean.service.elan.ELanObject;
import com.nms.drivechenxiao.service.bean.service.eline.ELineObject;
import com.nms.drivechenxiao.service.bean.service.etree.ETreeObject;
import com.nms.drivechenxiao.service.bean.slot.SlotObject;
import com.nms.drivechenxiao.service.bean.slot.SlotTempObject;
import com.nms.drivechenxiao.service.bean.tunnel.LSPObject;
import com.nms.drivechenxiao.service.bean.tunnel.TunnelObject;
import com.nms.drivechenxiao.service.bean.xc.XcObject;
import com.nms.drivechenxiao.service.impl.bean.CXDriveUtilObject;
import com.nms.drivechenxiao.service.impl.bean.CXPtnDataObject;
import com.nms.service.bean.AlarmObjectService;
import com.nms.service.bean.ProtectLogObjectService;
import com.nms.ui.manager.ExceptionManage;

public class CXMonitorCallbackThread extends Thread {

	private boolean isCN = false;
	private CXPtnDirveService cXPtnDirveService = null;
	private CXMonitorResponseThread cXMonitorResponseThread = null;
	private boolean pd = true;
	public static String alarmid_en = "com/nms/drivechenxiao/service/impl/alarmId_en.properties";
	public static String persvrid_en = "com/nms/drivechenxiao/service/impl/persvrId_en.properties";
	public static String ecxtcard = "com/nms/drivechenxiao/service/impl/ecxtcard.properties";
	private String configDrive = "com/nms/drivechenxiao/service/impl/configDrive.properties";
	private AlarmObjectService alarmObjectService = null;//主动上报告警
	private ProtectLogObjectService protectLogObjectService=null;//保护倒换事件的上报

	public void setAlarmObjectService(AlarmObjectService alarmObjectService) {
		this.alarmObjectService = alarmObjectService;
	}

	public void setProtectLogObjectService(
			ProtectLogObjectService protectLogObjectService) {
		this.protectLogObjectService = protectLogObjectService;
	}

	@Override
	public void run() {
		 
		String message = getPropertiesValue(configDrive, "zh_CN");
		if ("true".equals(message)) {
			isCN = true;
		}
		while (pd) {
			try {
				callback();// 回调
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
				cXPtnDirveService.logPrintln(this.getClass(), e, "error");
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void destroy() {
		pd = false;// 停止线程
	}

	/**
	 * 回调函数
	 */
	private void callback() {
		
		ConcurrentHashMap<String, CXDriveUtilObject> cXDriveUtilObjectList = cXMonitorResponseThread.getCXDriveUtilObjectList();
		if (cXDriveUtilObjectList.size() > 0) {
			List<String> removeDrive = new ArrayList<String>();
			CXDriveUtilObject cXDriveUtilObject = null;
			CXPtnDataObject cXPtnDataObject = null;
			for (Map.Entry<String, CXDriveUtilObject> entry : cXDriveUtilObjectList.entrySet()) {
				cXDriveUtilObject = entry.getValue();

				try {
					cXPtnDataObject = analysisCXPtnDataObject(cXDriveUtilObject);
					// System.out.println("回调：" + Integer.valueOf(driveUtilObject.getActionId()) + ":" + ptnDataObject.getStatus());
					cXDriveUtilObject.getOperationObject().returnResult(Integer.valueOf(cXDriveUtilObject.getActionId()), cXPtnDataObject);
				} catch (Exception e) {
					System.out.println("回调方法出错：" + e.toString());
				} finally {
					removeDrive.add(cXDriveUtilObject.getSeqId() + "");
				}
			}

			// 完成后全部删除掉
			for (int i = 0; i < removeDrive.size(); i++) {
				cXMonitorResponseThread.removeCXDriveUtilObject(removeDrive.get(i));
			}
		}
	}

	/**
	 * 解析命令 转换对象
	 * 
	 * @param cXDriveUtilObject
	 * @return
	 */
	private CXPtnDataObject analysisCXPtnDataObject(CXDriveUtilObject cXDriveUtilObject) {
		// 未完成需要调用其他部分
		CXPtnDataObject cXPtnDataObject = new CXPtnDataObject();
		byte[] responseSendCommands = cXDriveUtilObject.getResponseSendCommands();
		String message = "";

		cXPtnDataObject.setObjType(cXDriveUtilObject.getObjType());// 对象类型
		cXPtnDataObject.setOperType(cXDriveUtilObject.getOperType());// 操作类型

		if (cXDriveUtilObject.getDirection() == 0 && responseSendCommands[8] == 0x21 || responseSendCommands[8] == 0x22 || responseSendCommands[8] == 0x23) {

			AnalysisInformation analysisiInformation = new AnalysisInformation();
			
				message = analysisiInformation.AnalysisResponds(responseSendCommands);
						
			if (isCN) {// 是否翻译
				message = getPropertiesValue("com/nms/drivechenxiao/service/impl/returnValue.properties", message);				
				System.out.println(cXDriveUtilObject.getObjType() + ":" + cXDriveUtilObject.getOperType() + " 回调函数：" + message);
			}
			
			if ("配置成功".equals(message) && CXDriveUtilObject.OPER_SELETE.equals(cXDriveUtilObject.getOperType())) {
				// 如果配置成功并且是查询
				if (CXDriveUtilObject.OBJ_ALARM.equals(cXDriveUtilObject.getObjType())) {
					AnalysisAlarm analysisAlarm = new AnalysisAlarm();
					try {
						List<AlarmObject> alarmObjectList = analysisAlarm.analysisSelectAlarm(responseSendCommands, cXDriveUtilObject.getCXNEObject());// 解析之后
						cXPtnDataObject.setAlarmObjectList(alarmObjectList);
					} catch (Exception e) {
						System.out.println("告警解析失败!" + e.toString());
					}
					analysisAlarm = null;
				} else if (CXDriveUtilObject.OBJ_PERFORMANCE.equals(cXDriveUtilObject.getObjType())) {
					AnalysisPersvr analysisPersvr = new AnalysisPersvr();
					try {
						if(48==responseSendCommands[31]){
							message="111";
							message = getPropertiesValue(persvrid_en, message);				
							System.out.println(cXDriveUtilObject.getObjType() + ":" + cXDriveUtilObject.getOperType() + " 回调函数：" + message);
						}else{
							List<PersvrObject> persvrObjectList = analysisPersvr.analysisSelectPersvr(responseSendCommands, cXDriveUtilObject.getCXNEObject());
							cXPtnDataObject.setPersvrObjectList(persvrObjectList);
						}
						
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("性能解析失败!" + e.toString());
					}
					analysisPersvr = null;
				} else if (CXDriveUtilObject.OBJ_SLOT.equals(cXDriveUtilObject.getObjType())) {
					AnalysisSlot analysisSlot = new AnalysisSlot();
					try {
						SlotObject[] slotObjects = analysisSlot.analysisSelectSlot(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setSlotObjects(slotObjects);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("SLOT解析失败!" + e.toString());
					}
					analysisSlot = null;
				} else if (CXDriveUtilObject.OBJ_NE.equals(cXDriveUtilObject.getObjType())) {
					AnalysisNE analysisNE = new AnalysisNE();
					try {
						PtnNeObject ptnNeObject = analysisNE.analysisSelectNe(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setPtnNeObject(ptnNeObject);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("NE解析失败!" + e.toString());
					}
					analysisNE = null;
				} else if (CXDriveUtilObject.OBJ_TUNNEL.equals(cXDriveUtilObject.getObjType())) {
					AnalysisTunnel analysisTunnel = new AnalysisTunnel();
					try {
						List<TunnelObject> tunnelObjectList = analysisTunnel.analysisTunnel(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setTunnelObjectList(tunnelObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("TUNNEL解析失败!" + e.toString());
					}
					analysisTunnel = null;
				} else if (CXDriveUtilObject.OBJ_LSP.equals(cXDriveUtilObject.getObjType())) {
					AnalysisTunnel analysisTunnel = new AnalysisTunnel();
					try {
						List<LSPObject> lspObjectList = analysisTunnel.analysisAllLsp(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setLspObjectList(lspObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Lsp解析失败!" + e.toString());
					}
					analysisTunnel = null;
				} else if (CXDriveUtilObject.OBJ_XC.equals(cXDriveUtilObject.getObjType())) {
					AnalysisXC analysisXC = new AnalysisXC();
					try {
						List<XcObject> xcObjectList = analysisXC.analysisXc(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setXcObject(xcObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("XC解析失败!" + e.toString());
					}
					analysisXC = null;
				} else if (CXDriveUtilObject.OBJ_ETHPW.equals(cXDriveUtilObject.getObjType())) {
					AnalysisPWETH analysisPWETH = new AnalysisPWETH();
					try {
						List<PwEthObject> pwEthObjectList = analysisPWETH.analysisPWETH(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setPwEthObjectList(pwEthObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("PWETH解析失败!" + e.toString());
					}
					analysisPWETH = null;
				} else if (CXDriveUtilObject.OBJ_SDHPW.equals(cXDriveUtilObject.getObjType())) {
					AnalysisPWSDH analysisPWSDH = new AnalysisPWSDH();
					try {
						List<PwSdhObject> pwSdhObjectList = analysisPWSDH.analysisPWSDH(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setPwSdhObjectList(pwSdhObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("XC解析失败!" + e.toString());
					}
					analysisPWSDH = null;
				} else if (CXDriveUtilObject.OBJ_PDHPW.equals(cXDriveUtilObject.getObjType())) {
					AnalysisPWPDH analysisPWPDH = new AnalysisPWPDH();
					try {
						List<PwPdhObject> pwPdhObjectList = analysisPWPDH.analysisPWPDH(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setPwPdhObjectList(pwPdhObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("XC解析失败!" + e.toString());
					}
					analysisPWPDH = null;
				} else if (CXDriveUtilObject.OBJ_QOS.equals(cXDriveUtilObject.getObjType())) {
					AnalysisQOS analysisQOS = new AnalysisQOS();
					try {
						List<QosObject> qosObject = analysisQOS.analysisQos(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setQosObjectList(qosObject);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("QOS解析失败!" + e.toString());
					}
					analysisQOS = null;
				} else if (CXDriveUtilObject.OBJ_ELINE.equals(cXDriveUtilObject.getObjType())) {
					AnalysisELine analysisELine = new AnalysisELine();
					try {
						List<ELineObject> eLineObjectList = analysisELine.analysisELine(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setElineObjectList(eLineObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Eline解析失败!" + e.toString());
					}
					analysisELine = null;
				} else if (CXDriveUtilObject.OBJ_ETHAC.equals(cXDriveUtilObject.getObjType())) {
					AnalysisAC analysisAc = new AnalysisAC();
					try {
						List<AcObject> acObjectList = analysisAc.analysisAc(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setAcObjectList(acObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("ac解析失败!" + e.toString());
					}
					analysisAc = null;
				} else if (CXDriveUtilObject.OBJ_CESPDH.equals(cXDriveUtilObject.getObjType())) {
					AnalysisCes analysisCes = new AnalysisCes();
					try {
						List<CesObject> cesObjectList = analysisCes.analysisCES(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setCesObjectList(cesObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Ces解析失败!" + e.toString());
					}
					analysisCes = null;
				} else if (CXDriveUtilObject.OBJ_ETREE.equals(cXDriveUtilObject.getObjType())) {
					AnalysisETree analysisETree = new AnalysisETree();
					try {
						List<ETreeObject> eTreeObjectList = analysisETree.analysisETree(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setEtreeObjectList(eTreeObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Etree解析失败!" + e.toString());
					}
					analysisETree = null;
				} else if (CXDriveUtilObject.OBJ_QOSAC.equals(cXDriveUtilObject.getObjType())) {
					AnalysisQOS analysisQos = new AnalysisQOS();
					try {
						List<AcQosObject> acQosObjectList = analysisQos.analysisAcQos(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setAcQosObjectList(acQosObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("AcQos解析失败!" + e.toString());
					}
					analysisQos = null;
				} else if (CXDriveUtilObject.OBJ_ELAN.equals(cXDriveUtilObject.getObjType())) {
					AnalysisELAN analysisElan = new AnalysisELAN();
					try {
						List<ELanObject> elanObjectList = analysisElan.analysisELan(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setElanObjectList(elanObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("ELAN解析失败!" + e.toString());
					}
					analysisElan = null;
				} else if (CXDriveUtilObject.OBJ_ETHPORTUNI.equals(cXDriveUtilObject.getObjType())) {
					AnalysisETHPort analysisETHPort = new AnalysisETHPort();
					try {
						EthPortObject ethPortObject = analysisETHPort.analysisETHPortUNI(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setUniObject(ethPortObject.getUni());
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("UNI解析失败!" + e.toString());
					}
					analysisETHPort = null;
				} else if (CXDriveUtilObject.OBJ_ETHPORTNNI.equals(cXDriveUtilObject.getObjType())) {
					AnalysisETHPort analysisETHPort = new AnalysisETHPort();
					try {
						EthPortObject ethPortObject = analysisETHPort.analysisETHPortUNI(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setNniObject(ethPortObject.getNni());
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("NNI解析失败!" + e.toString());
					}
					analysisETHPort = null;
				} else if (CXDriveUtilObject.OBJ_ETHPORT.equals(cXDriveUtilObject.getObjType())) {
					AnalysisETHPort analysisETHPort = new AnalysisETHPort();
					try {
						List<EthPortObject> ethPortObjectList = analysisETHPort.analysisAllETHPort(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setEthPortObjectList(ethPortObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("ethport解析失败!" + e.toString());
					}
					analysisETHPort = null;
				} else if (CXDriveUtilObject.OBJ_CCN.equals(cXDriveUtilObject.getObjType())) {
					AnalysisCCN analysisCCN = new AnalysisCCN();
					try {
						List<CCNObject> ccnList = analysisCCN.analysisCCN(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setCcnObjectList(ccnList);
					} catch (Exception e) {
						System.out.println("CCN解析失败！" + e.toString());
					}
				} else if (CXDriveUtilObject.OBJ_OSPF.equals(cXDriveUtilObject.getObjType())) {
					AnalysisOSPF analysisOSPF = new AnalysisOSPF();
					try {
						OSPFObject ospfObject = analysisOSPF.analysisOSPF(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setOSPFObject(ospfObject);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("ospf解析失败!" + e.toString());
					}
					analysisOSPF = null;
				} else if (CXDriveUtilObject.OBJ_OSPFAREA.equals(cXDriveUtilObject.getObjType())) {
					AnalysisArea analysisArea = new AnalysisArea();
					try {
						List<OSPFAREAObject> ospfAreaObjectList = analysisArea.analysisArea(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setOspfAREAObjectList(ospfAreaObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("ospfarea解析失败!" + e.toString());
					}
					analysisArea = null;
				} else if (CXDriveUtilObject.OBJ_REDISTRIBUTE.equals(cXDriveUtilObject.getObjType())) {
					AnalysisRedistribute analysisRedistribute = new AnalysisRedistribute();
					try {
						List<RedistributeObject> redistributeObjectList = analysisRedistribute.analysisRedistribute(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setRedistributeObjectList(redistributeObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("redistribute解析失败!" + e.toString());
					}
					analysisRedistribute = null;
				} else if (CXDriveUtilObject.OBJ_PDHPORT.equals(cXDriveUtilObject.getObjType())) {
					AnalysisPDHPort analysisPDHPort = new AnalysisPDHPort();
					try {
						List<PdhPortObject> pdhPortObjectList = analysisPDHPort.analysisPDHPort(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setPdhPortObjectList(pdhPortObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("pdhport解析失败!" + e.toString());
					}
					analysisPDHPort = null;
				} else if (CXDriveUtilObject.OBJ_OSPFINTERFACE.equals(cXDriveUtilObject.getObjType())) {
					AnalysisOSPFInterfaces analysisOSPFInterfaces = new AnalysisOSPFInterfaces();
					try {
						List<OSPFinterfacesObject> ospfInterfacesObjectList = analysisOSPFInterfaces.analysisOSPFInterfaces(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setOspfInterfacesObjectList(ospfInterfacesObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("ospfInterfaces解析失败!" + e.toString());
					}
					analysisOSPFInterfaces = null;
				} else if (CXDriveUtilObject.OBJ_MCN.equals(cXDriveUtilObject.getObjType())) {
					AnalysisMCN analysisMCN = new AnalysisMCN();
					try {
						List<MCNObject> mcnObjectList = analysisMCN.analysisMCN(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setMcnObjectList(mcnObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("mcn解析失败!" + e.toString());
					}
					analysisMCN = null;
				} else if (CXDriveUtilObject.OBJ_SDHPORT.equals(cXDriveUtilObject.getObjType())) {
					AnalysisSDHPort analysisSDHPort = new AnalysisSDHPort();
					try {
						List<SdhPortObject> sdhPortObjectList = analysisSDHPort.analysisSDHPort(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setSdhPortObjectList(sdhPortObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("SdhPort解析失败!" + e.toString());
					}
					analysisSDHPort = null;
				} else if (CXDriveUtilObject.OBJ_SDHAC.equals(cXDriveUtilObject.getObjType())) {
					AnalysisSdhAc analysisSdhAc = new AnalysisSdhAc();
					try {
						List<SdhAcObject> sdhAcObjectList = analysisSdhAc.analysisSDHAc(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setSdhAcObjectList(sdhAcObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("SdhAc解析失败!" + e.toString());
					}
					analysisSdhAc = null;
				}else if (CXDriveUtilObject.OBJ_PORTLAG.equals(cXDriveUtilObject.getObjType())) {
					AnalysisLag analysisLag = new AnalysisLag();
					try {
						List<LagObject> lagObjectList = analysisLag.analysisLag(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setLagObjectList(lagObjectList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Lag解析失败!" + e.toString());
					}
					analysisLag = null;
				}else if (CXDriveUtilObject.OBJ_ACN.equals(cXDriveUtilObject.getObjType())) {
					AnalysisACN analysisAcn = new AnalysisACN();
					try {
						List<ACNObject> acnList = analysisAcn.analysisAcn(responseSendCommands);
						cXPtnDataObject.setAcnObjectList(acnList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Lag解析失败!" + e.toString());
					}
					analysisAcn = null;
				}else if (CXDriveUtilObject.OBJ_RING.equals(cXDriveUtilObject.getObjType())) {
					AnalysisRING analysisRing = new AnalysisRING();
					try {
						List<RingObject> ringList = analysisRing.analysisRing(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setRingObjectList(ringList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Ring解析失败!" + e.toString());
					}
					analysisRing = null;
				}else if (CXDriveUtilObject.OBJ_CLOCK.equals(cXDriveUtilObject.getObjType())) {
					AnalysisClock analysisclock = new AnalysisClock();
					try {
						List<ClockObject> ringList = analysisclock.analysisClock(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setClockObjectList(ringList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Clock 解析失败!" + e.toString());
					}
					analysisclock = null;
				}else if (CXDriveUtilObject.OBJ_CLOCKPortES.equals(cXDriveUtilObject.getObjType())) {
					AnalysisClockPort analysisclockport = new AnalysisClockPort();
					try {
						List<ClockPortESObject> ringList = analysisclockport.analysisClockPort(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setClockPortObjectList(ringList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("ClockPort 解析失败!" + e.toString());
					}
					analysisclockport = null;
				}else if (CXDriveUtilObject.OBJ_TOD.equals(cXDriveUtilObject.getObjType())) {
					AnalysisTod analysis = new AnalysisTod();
					try {
						List<TodObject> List = analysis.analysisTod(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setTodObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Tod 解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_PTP.equals(cXDriveUtilObject.getObjType())) {
					AnalysisPtp analysis = new AnalysisPtp();
					try {
						List<PtpObject> List = analysis.analysisPtp(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setPtpObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Ptp 解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_PTPPORT.equals(cXDriveUtilObject.getObjType())) {
					AnalysisPtpPort analysis = new AnalysisPtpPort();
					try {
						List<PtpPortObject> List = analysis.analysisPtpPort(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setPtpPortObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Ptp 解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_EXTCLK.equals(cXDriveUtilObject.getObjType())) {
					AnalysisExtclk analysis = new AnalysisExtclk();
					try {
						List<ExtclkObject> List = analysis.analysisExtclk(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setExtclkObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Ptp 解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_MSP.equals(cXDriveUtilObject.getObjType())) {
					AnalysisMsp analysis = new AnalysisMsp();
					try {
						List<MspObject> List = analysis.analysisMsp(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setMspObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Msp保护  解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_EFM.equals(cXDriveUtilObject.getObjType())) {
					AnalysisEthEfmOam analysis = new AnalysisEthEfmOam();
					try {
						List<EfmObject> List = analysis.analysisEfm(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setEfmObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Msp保护  解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_DUAL.equals(cXDriveUtilObject.getObjType())) {
					AnalysisDual analysis = new AnalysisDual();
					try {
						List<DualObject> List = analysis.analysisDual(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setDualObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("双规保护  解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_CLRTOEXP.equals(cXDriveUtilObject.getObjType())) {
					AnalysisClrtoexp analysis = new AnalysisClrtoexp();
					try {
						List<ClrtoexpObject> List = analysis.analysisClrtoexp(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setClrtoexpObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("CLRTOEXP  解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_COSTOEXP.equals(cXDriveUtilObject.getObjType())) {
					AnalysisCostoexp analysis = new AnalysisCostoexp();
					try {
						List<CostoexpObject> List = analysis.analysisCostoexp(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setCostoexpObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("COSTOEXP  解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_EXPTOCLR.equals(cXDriveUtilObject.getObjType())) {
					AnalysisExptoclr analysis = new AnalysisExptoclr();
					try {
						List<ExptoclrObject> List = analysis.analysisExptoclr(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setExptoclrObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("EXPTOCLR  解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_EXPTOCOS.equals(cXDriveUtilObject.getObjType())) {
					AnalysisExptocos analysis = new AnalysisExptocos();
					try {
						List<ExptocosObject> List = analysis.analysisExptocos(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setExptocosObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("EXPTOCOS  解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_COS2VPN.equals(cXDriveUtilObject.getObjType())) {
					AnalysisCos2vlanpri analysis = new AnalysisCos2vlanpri();
					try {
						List<Cos2vlanpriObject> List = analysis.analysisCos2vlanpri(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setCos2vlanpriObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Cos2vlanpri  解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_VLAN2CNG.equals(cXDriveUtilObject.getObjType())) {
					AnalysisVlanpri2cng analysis = new AnalysisVlanpri2cng();
					try {
						List<Vlanpri2cngObject> List = analysis.analysisVlanpri2cng(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setVlanpri2cngObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("Vlanpri2cng  解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_STATICROUTE.equals(cXDriveUtilObject.getObjType())) {
					AnalysisStaticRoute analysis = new AnalysisStaticRoute();
					try {
						List<StaticRouteObject> List = analysis.analysisStaticRoute(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setStaticRouteObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("StaticRoute  解析失败!" + e.toString());
					}
					analysis = null;
				}
				else if (CXDriveUtilObject.OBJ_SLOTTEMP.equals(cXDriveUtilObject.getObjType())) {//板卡温度管理
					AnalysisSlotTemp analysis = new AnalysisSlotTemp();
					try {
						List<SlotTempObject> List = analysis.analysisSlotTemp(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						cXPtnDataObject.setSlotTempObjectList(List);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("type_slotTemp  解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_POWER.equals(cXDriveUtilObject.getObjType())) {//光功率
					AnalysisPersvr analysisPersvr = new AnalysisPersvr();
					AnalysisPower analysis = new AnalysisPower();
					try {
						List<PersvrObject> persvrObjectList = analysisPersvr.analysisSelectPersvr(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						List<PowerObject> powList=analysis.getPowerObject(persvrObjectList);
						cXPtnDataObject.setPowerObjectList(powList);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("power 解析失败!" + e.toString());
					}
					analysisPersvr = null;
					analysis=null;
				}else if (CXDriveUtilObject.OBJ_CARDPRO.equals(cXDriveUtilObject.getObjType())) {//板卡保护
					AnalysisCard analysis = new AnalysisCard();
					try {
						List<CardProObject> list = analysis.analysisCardPro(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						
						cXPtnDataObject.setCardProObjectList(list);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("cardProtect  解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_PROTECTlOG.equals(cXDriveUtilObject.getObjType())) {//保护倒换事件
					Analysislog analysis = new Analysislog();
					try {
						List<ProtectLogObject> list = analysis.analysisLogquery(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						
						cXPtnDataObject.setProtectLogObject(list);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("protectLog  解析失败!" + e.toString());
					}
					analysis = null;
				}else if (CXDriveUtilObject.OBJ_TMDLOOPBACK.equals(cXDriveUtilObject.getObjType())) {//TMD环回
					AnalysisLoopBack analysis = new AnalysisLoopBack();
					try {
						List<Object> list = analysis.analysisSelectLoopBack(responseSendCommands, cXDriveUtilObject.getCXNEObject());
						
						cXPtnDataObject.setObjectList(list);
					} catch (Exception e) {
						CoreOper.print16String(responseSendCommands);
						System.out.println("loopBack  解析失败!" + e.toString());
					}
					analysis = null;
				}
			}
		} else if (cXDriveUtilObject.getDirection() == 1) {
			message = "配置成功";//cMPC_pxn
//			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			System.out.println(df.format(new Date()));
			System.out.println("........服务器主动推送给我：" + cXDriveUtilObject.getCXNEObject().getNeIp());
			CoreOper.print16String(responseSendCommands);
			if("almchg".equals(getMsgNotifyType(responseSendCommands))){
				System.out.println("告警事件的上报！！！！！");
				AnalysisAlarm analysisAlarm = new AnalysisAlarm();
				try {
					List<AlarmObject> alarmObjectList = analysisAlarm.analysisReportAlarm(responseSendCommands, cXDriveUtilObject.getCXNEObject());// 解析之后
					if (alarmObjectService != null) {
						alarmObjectService.getAlarmObject_cx().addAll(alarmObjectList);
					}
//					System.out.println("服务器主动推送给我告警 : alarmObjectList.size = "+alarmObjectList.size());
					for(AlarmObject a : alarmObjectList){
						System.out.println(a.toString());
					}
				} catch (Exception e) {
					System.out.println("主动上报告警解析失败!" + e.toString());
				}
			analysisAlarm = null;
			}
			/**
			 * 保护倒换事件的上报
			 * 带确定： 只有查询的byte[]，设备主动上报的byte[]无法模拟
			 */
			else if("prtswitch".equals(getMsgNotifyType(responseSendCommands))){
				System.out.println("保护倒换事件的上报！！！！！");
				Analysislog analysisLog=new Analysislog();
				try{
					List<ProtectLogObject> list = analysisLog.analysisHookNotify(responseSendCommands, cXDriveUtilObject.getCXNEObject());
					if(this.protectLogObjectService!=null){
						this.protectLogObjectService.getProtectLogObject_cx().addAll(list);
					}
				}catch(Exception e){
					System.out.println("主动上报保护倒换解析失败!" + e.toString());
				}
				
			}
		}

		cXPtnDataObject.setStatus(message);
		return cXPtnDataObject;
	}
	/**获得MsgNotify类型**/
	public String getMsgNotifyType(byte[] bb){
		byte[] lengByte = new byte[4];
		 System.arraycopy(bb,9,lengByte,0,4);
		 int leng = CoderUtils.bytesToInt(lengByte);
		byte[] ss = new byte[leng];
		 System.arraycopy(bb,13,ss,0,leng);
		 return new String(ss).toString().trim();
	}
	public static String getPropertiesValue(String filePath, String key) {
		String message = "";
		try {
			Properties props = new Properties();
			InputStream propsIs = MonitorCallbackThread.class.getClassLoader().getResourceAsStream(filePath);
			props.load(propsIs);
			String messageTemp = props.getProperty(key);
			if (messageTemp != null && !"".equals(messageTemp)) {
				message = messageTemp;
			} else {
				message = key;// 返回默认值
			}
		} catch (Exception e) {
			message = "匹配出现异常!";
		}
		return message;
	}

	public void setCXPtnDirveService(CXPtnDirveService ptnDirveService) {
		cXPtnDirveService = ptnDirveService;
	}

	public void setCXMonitorResponseThread(CXMonitorResponseThread monitorResponseThread) {
		cXMonitorResponseThread = monitorResponseThread;
	}
//
}
