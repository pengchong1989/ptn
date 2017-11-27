package com.nms.drive.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.shelf.EquipInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.drive.analysis.AnalysisObjectService;
import com.nms.drive.service.bean.AlarmObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.OSPFinfoWhObeject;
import com.nms.drive.service.bean.PerformanceObject;
import com.nms.drive.service.bean.UpgradeManageObject;
import com.nms.drive.service.impl.bean.CommandHand;
import com.nms.drive.service.impl.bean.DriveUtilObject;
import com.nms.drive.service.impl.bean.PDUHand;
import com.nms.drive.service.impl.bean.PtnDataObject;
import com.nms.jms.common.OpviewMessage;
import com.nms.jms.jmsMeanager.JmsUtil;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.util.CodeConfigItem;
import com.nms.model.util.Services;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.AlarmObjectService;
import com.nms.service.bean.OperationObject;
import com.nms.service.bean.PerformanceObjectService;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.wh.SiteWHServiceImpl;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.util.EquimentDataUtil;
import com.nms.ui.manager.xmlbean.EquipmentType;

public class MonitorCallbackThread extends Thread {

	private PtnDirveService ptnDirveService = null;
	private boolean pd = true;
	private AlarmObjectService alarmObjectService = null;
	private PerformanceObjectService performanceObjectService = null;
	
	public void setAlarmObjectService(AlarmObjectService alarmObjectService) {
		this.alarmObjectService = alarmObjectService;
	}
	
	public void setPerformanceObjectService(PerformanceObjectService performanceObjectService) {
		this.performanceObjectService = performanceObjectService;
	}
	
	public MonitorCallbackThread()
	{
		this.setName("MonitorCallbackThread");
	}

	@Override
	public void run() {

		while (pd) {
			try {
				callback();// 回调
			} catch (Exception e) {
				e.printStackTrace();
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
	 * 
	 * @throws Exception
	 */
	private void callback() {
		ConcurrentHashMap<String, DriveUtilObject> driveUtilObjectList = ConstantUtil.recive_commmandListMap;
		if (driveUtilObjectList != null && driveUtilObjectList.size() > 0) {
			DriveUtilObject driveUtilObject = null;
			PtnDataObject ptnDataObject = null;
			Iterator<String> it = ConstantUtil.recive_commmandListMap.keySet().iterator();
			String uuid = "";
			while (it.hasNext()) {
				try {
					
					uuid = (String) it.next();
					ExceptionManage.infor("UUID"+uuid, this.getClass());
					driveUtilObject = driveUtilObjectList.get(uuid);
					ptnDataObject = analysisPtnDataObject(driveUtilObject, uuid);
					driveUtilObject.getOperationObject().returnResult(Integer.valueOf(driveUtilObject.getOperID()), ptnDataObject);
					it.remove();
				} catch (Exception e) {
					System.out.println("回调方法出错：" + e.toString());
					ExceptionManage.dispose(e, this.getClass());

				}

			}
		}
	}

	/**
	 * 解析命令 转换对象
	 * 
	 * @param driveUtilObject
	 * @return
	 * @throws Exception
	 */
	private PtnDataObject analysisPtnDataObject(DriveUtilObject driveUtilObject, String uuiAndIp) {
		PtnDataObject ptnDataObject = new PtnDataObject();
		try {
			// 未完成需要调用其他部分
			byte[] responseSendCommands = driveUtilObject.getResponseSendCommands();
	        //16字节UDP头
			byte[] pduHand = new byte[] { responseSendCommands[0], responseSendCommands[1], responseSendCommands[2], responseSendCommands[3], responseSendCommands[4], responseSendCommands[5], responseSendCommands[6], responseSendCommands[7], responseSendCommands[8], responseSendCommands[9], responseSendCommands[10], responseSendCommands[11], responseSendCommands[12], responseSendCommands[13], responseSendCommands[14], responseSendCommands[15] };
			//21相应的命令头
			byte[] commandHand = new byte[] { responseSendCommands[16], responseSendCommands[17], responseSendCommands[18], responseSendCommands[19], responseSendCommands[20], responseSendCommands[21], responseSendCommands[22], responseSendCommands[23], responseSendCommands[24], responseSendCommands[25], responseSendCommands[26], responseSendCommands[27], responseSendCommands[28], responseSendCommands[29], responseSendCommands[30], responseSendCommands[31], responseSendCommands[32], responseSendCommands[33], responseSendCommands[34], responseSendCommands[35], responseSendCommands[36] };

			// PDUHand pduHandObj = getPDUHand(pduHand);
			CommandHand commandHandObj = getCommandHand(commandHand);
			if (commandHandObj.getLength() < 500000) {// 防止设备上报错误干扰数据
				byte[] dataBody = new byte[commandHandObj.getLength()];
				System.arraycopy(responseSendCommands, 37, dataBody, 0, commandHandObj.getLength());
				
				if ("CG13".equalsIgnoreCase(commandHandObj.getCommandLable())) {// 设备配置
					CG13(ptnDataObject, dataBody, pduHand);
				} else if ("AS21".equalsIgnoreCase(commandHandObj.getCommandLable())) {// WS向M查询某NE所有盘告警即时状态
					AS21(ptnDataObject, dataBody, pduHand);
				} else if ("AS22".equalsIgnoreCase(commandHandObj.getCommandLable())) {// WS向M查询某NE某盘当前告警
					AS22(ptnDataObject, dataBody, pduHand);
				} else if ("PM11".equalsIgnoreCase(commandHandObj.getCommandLable())) {// WS向M查询某NE所有盘性能
					PM11(ptnDataObject, dataBody, pduHand);
				} else if ("PM12".equalsIgnoreCase(commandHandObj.getCommandLable())) {// WS向M查询某NE某盘性能
					PM12(ptnDataObject, dataBody, pduHand);
				} else if ("AS11".equalsIgnoreCase(commandHandObj.getCommandLable())) {// M向WS报告M（A向M报告A）管辖范围内新发生和消失的告警
					AS11(ptnDataObject, dataBody, pduHand, uuiAndIp);
				} else if ("UD11".equalsIgnoreCase(commandHandObj.getCommandLable())) {// WS向某NE下发软件升级
					UD11(ptnDataObject, dataBody);
				} else if ("CG21".equalsIgnoreCase(commandHandObj.getCommandLable())) {// WS查询某NE某盘活动配置参数
					CG21(ptnDataObject, dataBody);
				} else if ("CM11".equalsIgnoreCase(commandHandObj.getCommandLable())) {// WS向M下达控制命令
					CM11(ptnDataObject, dataBody);
				} else if ("AS31".equalsIgnoreCase(commandHandObj.getCommandLable())) {// WS向M下达查询网元属性
					AS31(ptnDataObject, dataBody);
				} else if ("CG31".equalsIgnoreCase(commandHandObj.getCommandLable())) {// ws向m下达初始化设备
					String message = "配置成功";
					ptnDataObject.setStatus(message);
				} else if ("CG11".equalsIgnoreCase(commandHandObj.getCommandLable())) {// WS向M管理配置
					CG13(ptnDataObject, dataBody, pduHand);
				} else if ("TS12".equalsIgnoreCase(commandHandObj.getCommandLable())) {// M向WS报告M（A向M报告A）管辖范围内网元连接状态
					TS12(ptnDataObject, dataBody, pduHand, commandHand, uuiAndIp);
				} else if ("CG22".equalsIgnoreCase(commandHandObj.getCommandLable())) {// M向WS上载命令
					ptnDataObject.setStatus("配置成功");
					ptnDataObject.setReturnPtnObj(dataBody);
				} else if ("CG23".equalsIgnoreCase(commandHandObj.getCommandLable())) {// M向WS上载命令
					String message = CoderUtils.download(dataBody[0]);
					ptnDataObject.setStatus(message);
				} else if ("PM41".equalsIgnoreCase(commandHandObj.getCommandLable())) {// WS向M查询网元某状态
					AnalysisObjectService analysisObjectService = null;
					try {
						if (dataBody.length > 0) {
							analysisObjectService = new AnalysisObjectService();
							ActionObject actionObject = analysisObjectService.AnalysisSiteStatusTable(dataBody);
							ptnDataObject.setStatus("配置成功");
							ptnDataObject.setReturnPtnObj(actionObject);
						}
					} catch (Exception e) {

						ExceptionManage.dispose(e, this.getClass());
					} finally {
						analysisObjectService = null;
					}
				} else if ("CG41".equalsIgnoreCase(commandHandObj.getCommandLable())) {// M向WS网元校时
					CG41(ptnDataObject, dataBody, pduHand, commandHand);
				} else if ("CM12".equalsIgnoreCase(commandHandObj.getCommandLable())) {// WS向M网元校时
					CM12(ptnDataObject, dataBody, pduHand, commandHand);
				} else if ("TS54".equalsIgnoreCase(commandHandObj.getCommandLable())) {// oam性能监视
					TS54(ptnDataObject, dataBody, pduHand, commandHand);
				} else if ("UD23".equalsIgnoreCase(commandHandObj.getCommandLable())) {// 软件摘要
					UD23(ptnDataObject, dataBody, pduHand, commandHand);
				} else if ("UD12".equalsIgnoreCase(commandHandObj.getCommandLable())) {// 取消软件升级-返回结果
					UD12(ptnDataObject, dataBody, pduHand, commandHand);
				} else if ("NC41".equalsIgnoreCase(commandHandObj.getCommandLable())) {// 查询本网元SN
					NC41(ptnDataObject, dataBody, pduHand, commandHand);
				} else if ("JC51".equalsIgnoreCase(commandHandObj.getCommandLable())) {// 查询相邻网元SN
					JC51(ptnDataObject, dataBody, pduHand, commandHand);
				} else if ("JC52".equalsIgnoreCase(commandHandObj.getCommandLable())) {// 设置网元IP
					JC52(ptnDataObject, dataBody, pduHand, commandHand);
				} else if ("JC53".equalsIgnoreCase(commandHandObj.getCommandLable())) {// 查询远程网元IP
					JC53(ptnDataObject, dataBody, pduHand, commandHand);
				} else if ("TS63".equalsIgnoreCase(commandHandObj.getCommandLable())) {// 设置网元IP
					TS63(ptnDataObject, dataBody, pduHand, commandHand);
				}else if ("JC54".equalsIgnoreCase(commandHandObj.getCommandLable())) {// 设置网元IP
					JC54(ptnDataObject, dataBody);
				}else if("AS32".equalsIgnoreCase(commandHandObj.getCommandLable())){
					AS32(ptnDataObject, commandHand, dataBody, uuiAndIp);
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		
		return ptnDataObject;
	}



	private PDUHand getPDUHand(byte[] pduHand) {
		PDUHand PDUHand = new PDUHand();
		String returnStr = "";
		/*
		 * 【网管PDU标签】
		 */
		char f = CoderUtils.ascii2Char(CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, pduHand[0] }));
		char h = CoderUtils.ascii2Char(CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, pduHand[1] }));
		char m = CoderUtils.ascii2Char(CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, pduHand[2] }));
		char p = CoderUtils.ascii2Char(CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, pduHand[3] }));
		returnStr += "\r\n【网管PDU标签】" + f + h + m + p;
		/*
		 * 【协议版本】
		 */
		int xybb = CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, pduHand[4] });
		returnStr += "\r\n【协议版本】" + xybb;

		/*
		 * 【PDU类型标签】 Request-PDU = E1; Response-PDU = E2; Report-PDU = E3;
		 * Confirm-PDU = E4; Broadcast-PDU = E5;
		 */
		int pduTypeTab = CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, pduHand[5] });
		PDUHand.setPduTypeTab(pduTypeTab);
		String pduTypeTabStr = "";
		if (pduTypeTab == 0xe1) {
			pduTypeTabStr = "Request-PDU";
		} else if (pduTypeTab == 0xe2) {
			pduTypeTabStr = "Response-PDU";
		} else if (pduTypeTab == 0xe3) {
			pduTypeTabStr = "Report-PDU";
		} else if (pduTypeTab == 0xe4) {
			pduTypeTabStr = "Confirm-PDU";
		} else if (pduTypeTab == 0xe5) {
			pduTypeTabStr = "Broadcast-PDU";
		}
		returnStr += "\r\n【PDU类型标签】" + pduTypeTabStr;

		/*
		 * 【通信类型】 “通信类型”字段（1字节）表示管理实体间信息交互的方式和发起方，分配代码如下： WS－M：由WS发出的数据，类型代码为1；
		 * M－WS：由M发出的数据，类型代码为2； M－A：由M发出的数据，类型代码为3； A－M：由A发出的数据，类型代码为4；
		 * A－BMU：由A发出的数据，类型代码为5； BMU－A：由BMU发出的数据，类型代码为6；
		 * A—BMU广播：由A向所管辖BMU发起的广播型数据，类型代码为7； PC－A：由PC机发出的数据（用串口通信），代码类型为8；
		 * A－PC：由A向PC发出的数据（用串口通信），类型代码为9； PC－A：由PC机发出的数据（用以太网通信），类型代码为0xA；
		 * A－PC：由PC机发出的数据（用以太网通信），类型代码为0xB； ACU－BMU：由ACU向BMU发出的数据，类型代码为0xE；
		 * BMU－ACU：由BMU向ACU发出的数据，类型代码为0xD； ACU－NMU：由ACU向NMU发出的数据，类型代码为0xE；
		 * NMU－ACU：由NMU向ACU发出的数据，类型代码为0xF。
		 */
		int txlx = CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, pduHand[6] });
		PDUHand.setSocketType(txlx);
		returnStr += "\r\n【通信类型】" + txlx;

		/*
		 * 【下一个PDU头】
		 */
		int xygpdut = CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, pduHand[7] });
		returnStr += "\r\n【下一个PDU头】" + xygpdut;

		/*
		 * 【会晤序列号】
		 */
		int uuid = CoderUtils.bytesToInt(new byte[] { pduHand[8], pduHand[9], pduHand[10], pduHand[11] });
		returnStr += "\r\n【会晤序列号】" + uuid;

		/*
		 * 【PDU信息体的长度】
		 */
		int length = CoderUtils.bytesToInt(new byte[] { pduHand[12], pduHand[13], pduHand[14], pduHand[15] });
		PDUHand.setLength(length);
		returnStr += "\r\n【PDU信息体的长度】" + length;

		// System.out.print(returnStr);
		return PDUHand;
	}

	private CommandHand getCommandHand(byte[] commandHand) {
		CommandHand commandHandObj = new CommandHand();

		String returnStr = "";
		/*
		 * 【命令码】
		 */
		char a0 = CoderUtils.ascii2Char(CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, commandHand[0] }));
		char a1 = CoderUtils.ascii2Char(CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, commandHand[1] }));
		String a2 = CoderUtils.intTo16String(CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, commandHand[2] }));
		String a3 = CoderUtils.intTo16String(CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, commandHand[3] }));
		returnStr += "\r\n【命令码】" + a0 + a1 + a2 + a3;
		commandHandObj.setCommandLable(a0 + "" + a1 + "" + a2 + "" + a3);

		/*
		 * 【方向】 “方向”字段表达的信息在PDU头的“通信类型”字段中有更明确的表达，
		 * 为保持协议的前后兼容性，在NMU（M）向WS发出的数据中改字段固定填“0x28”， NMU（M）接收的数据中不解释该字段。
		 */
		int fx = CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, commandHand[4] });
		commandHandObj.setDirection(fx);
		returnStr += "\r\n【方向】" + fx;

		/*
		 * 【对象范围】
		 */
		int dxfw = CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, commandHand[5] });
		returnStr += "\r\n【对象范围】" + dxfw;

		/*
		 * 【对象标识号】
		 * 
		 * “对象标识号1”：D0（BIT0）：该帧是否支持压缩传送方式（0／l=不支持／支持）
		 * D1（BITl）：该帧长度之后的内容是否是压缩数据（0／1=非压缩／压缩） 因NMU肯定支持数据压缩传送方式，因此BIT0固定填1。
		 * 
		 * “对象标识4”字段说明使用的协议类型和协议数据类，其中： D7：说明使用的协议类型，确定后面数据解释的方式
		 * D7＝0/1＝按现有WS－MAU协议解释/按ASON定义的WS－NMU协议解释 D0～D3说明数据类型，分为如下几类： 1——配置数据
		 * 2——告警数据 3——性能数据 A——透明帧数据
		 * 
		 * D7 ..... D0
		 */
		int dxbs1 = CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, commandHand[6] });
		String dxbs2 = CoderUtils.convertBinary(CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, commandHand[7] }));
		String dxbs3 = CoderUtils.convertBinary(CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, commandHand[8] }));
		int dxbs4 = CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, commandHand[9] });
		returnStr += "\r\n【对象标识号】" + dxbs1 + "," + dxbs2 + "," + dxbs3 + "," + dxbs4;

		/*
		 * 【流水号】 字段填PDU头中“会晤系列号”字段的最后一个字节。
		 */
		int id = CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, commandHand[10] });
		returnStr += "\r\n【流水号】" + id;

		/*
		 * 【状态】 “状态1”字段在申请配置的“JC040E”命令时按如下方式解释： D5～D7未用，
		 * D4——地址参数申请位，“0”=地址对，“1”=地址错； D3——时间参数申请位，“0”=不申请，“1”=申请；
		 * D2——结构参数申请位，“0”=不申请，“1”=申请； D1——设备参数申请位，“0”=不申请，“1”=申请；
		 * D0——管理参数申请位，“0”=不申请，“1”=申请
		 */
		String zt1 = CoderUtils.convertBinary(CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, commandHand[11] }));
		int zt2 = CoderUtils.bytesToInt(new byte[] { 0x00, 0x00, 0x00, commandHand[12] });
		returnStr += "\r\n【状态】" + zt1 + "," + zt2;

		/*
		 * 【备用】
		 */
		commandHand[13] = 0x00;// 未用
		commandHand[14] = 0x00;// 未用
		commandHand[15] = 0x00;// 未用
		commandHand[16] = 0x00;// 未用
		returnStr += "\r\n【备用】" + 0;

		/*
		 * 【数据长度】
		 */
		int length = CoderUtils.bytesToInt(new byte[] { commandHand[17], commandHand[18], commandHand[19], commandHand[20] });
		returnStr += "\r\n【数据长度】" + length;
		commandHandObj.setLength(length);

		// System.out.println(returnStr);
		return commandHandObj;
	}

	/**
	 * 设备配置
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 */
	public void CG13(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand) {
		String message = "";
		try {
			ExceptionManage.infor("CG11收到", MonitorCallbackThread.class);
			Properties props = new Properties();
			InputStream propsIs = MonitorCallbackThread.class.getClassLoader().getResourceAsStream("com/nms/drive/service/impl/returnValue.properties");
			props.load(propsIs);
			message = props.getProperty(CoderUtils.bytesToInt(dataBody[0]) + "");
		} catch (Exception e) {
			message = "匹配出现异常！";
		}
		ptnDataObject.setStatus(message);
	}

	/**
	 * WS向M查询某NE所有盘告警即时状态
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 */
	public void AS21(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand) {
		try {
			if (CodeConfigItem.getInstance().getAlarmPoll() == 1) {
				int neAddress = dataBody[0] * 256 + 255;// ne地址做key
				alarmObjectService.getConcurrentHashMap().put(System.currentTimeMillis()+"", dataBody);// 将主动查询的告警放入特殊的存储对象中
				ptnDataObject.setStatus("配置成功");
			} else {
				AnalysisObjectService analysisObjectService = new AnalysisObjectService();
				AlarmObject alarmObject = analysisObjectService.AnalysisCommadsToAllReportAlarm(dataBody);
				ptnDataObject.setReturnPtnObj(alarmObject);
				ptnDataObject.setStatus("配置成功");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
		}
	}

	private void AS32(PtnDataObject ptnDataObject, byte[] pduHand, byte[] dataBody, String uuiAndIp) {
		try {
//			byte[] dataCmd = new byte[pduHand.length+dataBody.length];
//			System.arraycopy(pduHand, 0, dataCmd, 0, pduHand.length);
//			System.arraycopy(dataBody, 0, dataCmd, pduHand.length, dataBody.length);
			//向设备发送确认帧
			String ip = uuiAndIp.split("/")[1];
//			byte[] uuid = new byte[4];
			ActionObject actionObject = new ActionObject();
			NEObject neObject = new NEObject();
			byte[] neAddress = new byte[4];
			neAddress[0] = 0;
			neAddress[1] = 0;
			neAddress[2] = dataBody[0];
			neAddress[3] = dataBody[1];
			neObject.setNeAddress(CoderUtils.bytesToInt(neAddress));
			neObject.setManageIP(ip);
			actionObject.setNeObject(neObject);
			WHOperationBase opeBase = new WHOperationBase();
			List<ActionObject> acList = new ArrayList<ActionObject>();
			acList.add(actionObject);
			actionObject.setActionId(opeBase.getActionId(acList));
//			uuid[0] = pduHand[8];
//			uuid[1] = pduHand[9];
//			uuid[2] = pduHand[10];
//			uuid[3] = pduHand[11];
//			dataCmd[4] = CoderUtils.intToBytes(136)[3];
//			dataCmd[20] = (byte) (CoderUtils.bytesToInt(dataCmd[20])+1);
//			ConstantUtil.driveService.confirmAlarmFrame(new OperationObject(), actionObject, CoderUtils.bytesToInt(uuid), ip, dataCmd);
			//查询指定网元的告警
//			ConstantUtil.driveService.queryAllAlarmObject(new OperationObject(), actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	/**
	 * WS向M查询某NE某盘当前告警
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 */
	public void AS22(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand) {
		try {
			alarmObjectService.getConcurrentHashMap().put(System.currentTimeMillis() + "", dataBody);// 将主动查询的告警放入特殊的存储对象中
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
		}
	}

	/**
	 * WS向M查询某NE所有盘性能
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 */
	public void PM11(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand) {
		AnalysisObjectService analysisObjectService;
		try {
			//查询的当前性能
			if(ConstantUtil.isCurrentPerformnace == true)
			{
				analysisObjectService = new AnalysisObjectService();
				PerformanceObject performanceObject = analysisObjectService.AnalysisCommadsToPerformanceObjectBySite(dataBody);
				ptnDataObject.setStatus("配置成功");
				ptnDataObject.setReturnPtnObj(performanceObject);	
			}
			//查询的历史性能
			else
			{
				performanceObjectService.getHisPerformanceMap().put(System.currentTimeMillis()+"", dataBody);
				ptnDataObject.setStatus("配置成功");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			analysisObjectService = null;
		}
	}

	/**
	 * WS向M查询某NE某盘性能
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 */
	public void PM12(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand) {
		AnalysisObjectService analysisObjectService;
		try {
			analysisObjectService = new AnalysisObjectService();
			PerformanceObject performanceObject = analysisObjectService.AnalysisCommadsToPerformanceObjectBySite(dataBody);
			ptnDataObject.setStatus("配置成功");
			ptnDataObject.setReturnPtnObj(performanceObject);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			System.out.println(e.toString());
		} finally {
			analysisObjectService = null;
		}
	}

	/**
	 * M向WS报告M（A向M报告A）管辖范围内新发生和消失的告警
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 */
	public void AS11(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand, String uuidAndIp) {
		try {
			String ip = uuidAndIp.split("/")[1];
			AnalysisObjectService analysisObjectService = new AnalysisObjectService();
			AlarmObject alarmObject = analysisObjectService.AnalysisCommadsToAllReportAlarm(dataBody);
			ptnDataObject.setStatus("配置成功");
			ptnDataObject.setReturnPtnObj(alarmObject);
			alarmObject.setNeIP(ip);
			List<AlarmObject> alarmList = new ArrayList<AlarmObject>();
			alarmList.add(alarmObject);
			OpviewMessage opviewMessage = new OpviewMessage();
			opviewMessage.setOccuredTime(System.currentTimeMillis());
			opviewMessage.setMessageSource("Alarm");
			opviewMessage.setObject(alarmList);
			JmsUtil.sendService(opviewMessage);
			
			byte[] uuid = new byte[4];
			ActionObject actionObject = new ActionObject();
			NEObject neObject = new NEObject();
			neObject.setNeAddress(alarmObject.getNeAddress());
			actionObject.setNeObject(neObject);
			uuid[0] = pduHand[8];
			uuid[1] = pduHand[9];
			uuid[2] = pduHand[10];
			uuid[3] = pduHand[11];
			
			ConstantUtil.driveService.confirmAlarmFrame(new OperationObject(), actionObject, CoderUtils.bytesToInt(uuid), ip);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * WS向某NE下发软件升级
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 */
	public void UD11(PtnDataObject ptnDataObject, byte[] dataBody) {
		try {
			String message = "配置成功";
			System.out.println(dataBody.length);// 测试设备返回信息
			ptnDataObject.setStatus(message);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * WS查询某NE某盘活动配置参数
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 */
	public void CG21(PtnDataObject ptnDataObject, byte[] dataBody) {
		AnalysisObjectService analysisObjectService;
		try {
			analysisObjectService = new AnalysisObjectService();
			ActionObject actionObject = analysisObjectService.AnalysisCommadsToAllBusiness(dataBody);
			ptnDataObject.setStatus("配置成功");
			ptnDataObject.setReturnPtnObj(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			analysisObjectService = null;
		}
	}

	/**
	 * WS向M下达控制命令
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 */
	public void CM11(PtnDataObject ptnDataObject, byte[] dataBody) {
		String message = "配置成功";
		try {
			if(dataBody.length>1){
				ptnDataObject.setStatus(message);
				ActionObject actionObject = new ActionObject();
				actionObject.setBs(dataBody);
				ptnDataObject.setReturnPtnObj(actionObject);
			}else if(dataBody.length==1){
				ptnDataObject.setStatus(dataBody[0]==1?"配置成功":"配置失败");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		ptnDataObject.setStatus(message);
	}

	/**
	 * WS向M下达查询网元属性
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 */
	public void AS31(PtnDataObject ptnDataObject, byte[] dataBody) {
		String message = "配置成功";
		try {
			AnalysisObjectService analysisObjectService = new AnalysisObjectService();
			NEObject neObject = analysisObjectService.AnalysisCommadsToSiteAttribute(dataBody);
			ptnDataObject.setStatus("配置成功");
			ptnDataObject.setReturnPtnObj(neObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		ptnDataObject.setStatus(message);
	}

	/**
	 * M向WS请求配置（校时，发管理配置、设备配置、校时数据等）
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 */
	public void CG41(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand, byte[] commandHand) {
		try {
			ptnDataObject.setStatus("配置成功");
			byte[] uuid = new byte[4];
			ActionObject actionObject = new ActionObject();
			NEObject neObject = new NEObject();
			neObject.setNeAddress(dataBody[0] * 256 + 255);
			actionObject.setNeObject(neObject);
			uuid[0] = pduHand[8];
			uuid[1] = pduHand[9];
			uuid[2] = pduHand[10];
			uuid[3] = pduHand[11];
			ConstantUtil.driveService.confirmFrame(new OperationObject(), actionObject, CoderUtils.bytesToInt(uuid));
			SiteWHServiceImpl siteWHServiceImpl = new SiteWHServiceImpl();
			siteWHServiceImpl.requestConfig(dataBody[0], commandHand);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * WS向M下发校时
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 */
	public void CM12(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand, byte[] commandHand) {
		String message = "配置成功";
		try {
			Properties props = new Properties();
			InputStream propsIs = MonitorCallbackThread.class.getClassLoader().getResourceAsStream("com/nms/drive/service/impl/returnValue.properties");
			props.load(propsIs);
			// message =
			// props.getProperty(CoderUtils.bytesToInt(dataBody[0]) + "");
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		ptnDataObject.setStatus(message);
	}

	/**
	 * M向WS报告M（A向M报告A）管辖范围内网元连接状态
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 */
	public void TS12(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand, byte[] commandHand, String uuiAndIp) {
		AnalysisObjectService analysisObjectService = null;
		List<NEObject> neObjects = null;
		Integer mID = null;
		SiteService_MB siteService = null;
		List<SiteInst> siteInsts = new ArrayList<SiteInst>();
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			String ip = uuiAndIp.split("/")[1];
			mID = ConstantUtil.mSiteMap.get((CoderUtils.bytesToInt(dataBody[0])*256+CoderUtils.bytesToInt(dataBody[1]))+"/"+ip);
			if (mID != null) {
				analysisObjectService = new AnalysisObjectService();
				neObjects = analysisObjectService.AnalysisCommadsToCircleSiteObject(dataBody);
				ptnDataObject.setStatus("配置成功");
				ptnDataObject.setReturnPtnObj(neObjects);
				HashMap<Integer,Integer> m_aSite = ConstantUtil.m_aSiteMap.get(mID);
				if(m_aSite!= null){
					for (NEObject object : neObjects) {
						Integer siteId = m_aSite.get(object.getNeAddress());
						if(siteId != null){
							SiteInst inst = new SiteInst();
							inst.setSite_Inst_Id(siteId);
							inst.setLoginstatus(object.getNeStatus() == 0 ? 0 : 1);
							siteInsts.add(inst);
						}
					}
				}
				
				if (siteInsts.size() > 0) {
					siteService.updateBatchOnline(siteInsts);
				}
				ConstantUtil.siteStausMap.put(ip, System.currentTimeMillis());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	/**
	 * oam性能监视
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 */
	public void TS54(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand, byte[] commandHand) {
		AnalysisObjectService analysisObjectService;
		try {
			analysisObjectService = new AnalysisObjectService();
			ActionObject actionObject = analysisObjectService.AnalysisOamStatusTable(dataBody);
			ptnDataObject.setStatus("配置成功");
			ptnDataObject.setReturnPtnObj(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			analysisObjectService = null;
		}
	}

	public void UD23(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand, byte[] commandHand) {
		AnalysisObjectService analysisObjectService;
		try {
			if(dataBody.length>100){
				analysisObjectService = new AnalysisObjectService();
				List<UpgradeManageObject> upgradeManageObjects = analysisObjectService.AnalysisUpgradeManageObject(dataBody);
				ptnDataObject.setReturnPtnObj(upgradeManageObjects);
				ptnDataObject.setStatus("配置成功");
			}else{
				int status = CoderUtils.bytesToInt(dataBody[5]);
				if(status == 1){
					ptnDataObject.setStatus("摘要错误");
				}else if(status ==2){
					ptnDataObject.setStatus("配置成功");
				}else if(status ==3){
					ptnDataObject.setStatus("升级错误，软件版本和设备型号不匹配");
				}else if(status ==4){
					ptnDataObject.setStatus("升级错误，软件版本和板卡类型不匹配");
				}else if(status ==5){
					ptnDataObject.setStatus("升级错误，软件数据存储中");
				}
			}
			
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			analysisObjectService = null;
		}
	}

	public void UD12(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand, byte[] commandHand) {
		int result = dataBody[5];
		if (result == 1) {
			ptnDataObject.setStatus("配置成功");
		} else {
			ptnDataObject.setStatus("");
		}
	}

	private void NC41(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand, byte[] commandHand) {
		CoderUtils.print16String(dataBody);
		NEObject neObject = new NEObject();
		String[] strings = CoderUtils.byteToStrings(dataBody);
		if(dataBody.length == 36){
			StringBuffer sn = new StringBuffer();
			for (int i = 0; i < Integer.parseInt(strings[3], 16); i++) {
				char c = (char) Integer.parseInt(strings[4 + i], 16);
				sn.append(c);
			}
			neObject.setSn(sn.toString());
			ptnDataObject.setStatus("配置成功");
			ptnDataObject.setReturnPtnObj(neObject);
		}else if(dataBody.length == 12 || dataBody.length == 14 ||dataBody.length == 16){
			ptnDataObject.setStatus("配置成功");
		}else if(dataBody[0] == 9){
			try {
				ptnDataObject.setStatus("配置成功");
				ActionObject actionObject = new ActionObject();
				int portnum = CoderUtils.bytesToInt(dataBody[6]);
				List<OSPFinfoWhObeject> ospFinfoWhObejects = new ArrayList<OSPFinfoWhObeject>();
				for (int i = 0; i < portnum; i++) {
					OSPFinfoWhObeject ospFinfoWhObeject = new OSPFinfoWhObeject();
					ospFinfoWhObeject.setIp(CoderUtils.bytesToInt(dataBody[7+i*12])+"."+CoderUtils.bytesToInt(dataBody[8+i*12])+"."+CoderUtils.bytesToInt(dataBody[9+i*12])+"."+CoderUtils.bytesToInt(dataBody[10+i*12]));
					ospFinfoWhObeject.setMask(CoderUtils.bytesToInt(dataBody[12+i*12])+"");
					ospFinfoWhObeject.setPortType(CoderUtils.bytesToInt(dataBody[14+i*12]));
					ospFinfoWhObeject.setVlanValues(CoderUtils.bytesToInt(dataBody[15+i*12])*256+CoderUtils.bytesToInt(dataBody[16+i*12]));
					ospFinfoWhObeject.setFolw(CoderUtils.bytesToInt(dataBody[17+i*12]));
					ospFinfoWhObeject.setPortModel(CoderUtils.bytesToInt(dataBody[18+i*12]));
					ospFinfoWhObeject.setOspfType(11);
					ospFinfoWhObejects.add(ospFinfoWhObeject);
				}
				int fieldnum = CoderUtils.bytesToInt(dataBody[10+portnum*12]);
				for (int i = 0; i < fieldnum; i++) {
					OSPFinfoWhObeject ospFinfoWhObeject = new OSPFinfoWhObeject();
					ospFinfoWhObeject.setIp(CoderUtils.bytesToInt(dataBody[11+portnum*12+i*8])+"."+CoderUtils.bytesToInt(dataBody[12+portnum*12+i*8])+"."+CoderUtils.bytesToInt(dataBody[13+portnum*12+i*8])+"."+CoderUtils.bytesToInt(dataBody[14+portnum*12+i*8]));
					ospFinfoWhObeject.setPortType(CoderUtils.bytesToInt(dataBody[16+i*8+portnum*12]));
					ospFinfoWhObeject.setEnable(CoderUtils.bytesToInt(dataBody[18+i*8+portnum*12]));
					ospFinfoWhObeject.setOspfType(23);
					ospFinfoWhObejects.add(ospFinfoWhObeject);
				}
				actionObject.setOspFinfoWhObejects(ospFinfoWhObejects);
				ptnDataObject.setStatus("配置成功");
				ptnDataObject.setReturnPtnObj(actionObject);
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
			
		}else if(dataBody.length==2){
			if(CoderUtils.bytesToInt(dataBody[0])==1 && CoderUtils.bytesToInt(dataBody[1])==1){
				ptnDataObject.setStatus("配置成功");
			}
		}else{
			ptnDataObject.setStatus("配置失败");
		}
		
	}

	private void JC51(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand, byte[] commandHand) {
		AnalysisObjectService analysisObjectService = new AnalysisObjectService();
		try {
			List<NEObject> neObjects = analysisObjectService.AnalysisCommandToSNObject(dataBody);
			ptnDataObject.setStatus("配置成功");
			ptnDataObject.setReturnPtnObj(neObjects);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	private void JC52(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand, byte[] commandHand) {
		if(CoderUtils.bytesToInt(dataBody[2]) == 1){
			ptnDataObject.setStatus("配置成功");
		}else if(CoderUtils.bytesToInt(dataBody[2]) == 2){
			ptnDataObject.setStatus("数据长度超出范围");
		}else if(CoderUtils.bytesToInt(dataBody[2]) == 3){
			ptnDataObject.setStatus("设备正忙");
		}else if(CoderUtils.bytesToInt(dataBody[2]) == 4){
			ptnDataObject.setStatus("设置出错");
		}else if(CoderUtils.bytesToInt(dataBody[2]) == 5){
			ptnDataObject.setStatus("设置配置超时");
		}
	}

	private void JC53(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand, byte[] commandHand) {
		CoderUtils.print16String(dataBody);
		if(dataBody.length == 39){
			NEObject neObject = new NEObject();
			String[] strings = CoderUtils.byteToStrings(dataBody);
			StringBuffer sn = new StringBuffer();
			for (int i = 0; i < Integer.parseInt(strings[6], 16); i++) {
				char c = (char) Integer.parseInt(strings[7 + i], 16);
				sn.append(c);
			}
			neObject.setSn(sn.toString());
			ptnDataObject.setStatus("配置成功");
			ptnDataObject.setReturnPtnObj(neObject);
		}else if(dataBody.length == 19|| dataBody.length ==27){
			ptnDataObject.setStatus("配置成功");
		}else{
			ptnDataObject.setStatus("配置失败");
		}
	}
	
	
	/**
	 * DHCP功能，暂时给杜芳测试用，集采过后单独用个线程调用
	 * 
	 * @param ptnDataObject
	 * @param dataBody
	 * @param pduHand
	 * @param commandHand
	 */
	private void TS63(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand, byte[] commandHand) {
		CoderUtils.print16String(dataBody);
		int type = 0;// 设备类型
		byte[] types = new byte[4];
		types[0] = 0;
		types[1] = 0;
		types[2] = dataBody[1];
		types[3] = dataBody[2];
		type = CoderUtils.bytesToInt(types);
		System.out.println(type);

		int isMsite = dataBody[3];// A/M
		System.out.println(isMsite);

		byte[] ip_byte = new byte[4];
		ip_byte[0] = dataBody[4];
		ip_byte[1] = dataBody[5];
		ip_byte[2] = dataBody[6];
		ip_byte[3] = dataBody[7];
		String[] ips = CoderUtils.byteToStrings(ip_byte);
		// 本网元ip
		String ip = Integer.parseInt(ips[0], 16) + "." + Integer.parseInt(ips[1], 16) + "." + Integer.parseInt(ips[2], 16) + "." + Integer.parseInt(ips[3], 16);
		System.out.println(ip);

		// 所属M网元ip
		byte[] mip_byte = new byte[4];
		mip_byte[0] = dataBody[8];
		mip_byte[1] = dataBody[9];
		mip_byte[2] = dataBody[10];
		mip_byte[3] = dataBody[11];
		String[] mips = CoderUtils.byteToStrings(mip_byte);
		String mIP = Integer.parseInt(mips[0], 16) + "." + Integer.parseInt(mips[1], 16) + "." + Integer.parseInt(mips[2], 16) + "." + Integer.parseInt(mips[3], 16);
		System.out.println(mIP);
		byte[] sn_byte = new byte[12];
		System.arraycopy(dataBody, 13, sn_byte, 0, sn_byte.length);
		String sn = CoderUtils.byteToString(sn_byte);// sn序列号
		System.out.println(sn);
		SiteService_MB siteService = null;
		List<SiteInst> insts = null;
		SiteInst siteInst = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			insts = siteService.selectBySn(sn);// 先通过SN查询网元是否存在
			if (insts != null && insts.size() > 0) {
				siteInst = insts.get(0);
				if (!siteInst.getCellDescribe().equals(ip)) {// 网元ip发生变化
					siteInst.setCellDescribe(ip);
					siteInst.setSwich((Integer.parseInt(ip.split("\\.")[2]) * 256 + Integer.parseInt(ip.split("\\.")[3])) + "");
					// 验证所属M网元是否发生变化

					siteService.saveOrUpdate(siteInst);
				}

			} else {// SN查询网元不存在，再通过ip查询
				siteInst = new SiteInst();
				siteInst.setCellDescribe(ip);
				insts = siteService.select(siteInst);
				if (insts != null && insts.size() > 0) {// 存在，sn入库
					siteInst = insts.get(0);
					siteInst.setSn(sn);
					siteService.saveOrUpdate(siteInst);
				} else {// 不存在，自动创建网元
					siteInst = new SiteInst();
					siteInst.setCellType(type + "");
					int siteId = getSiteId(mIP);
					SiteInst mInst = new SiteInst();
					mInst.setCellDescribe(mIP);
					if (isMsite == 2) {// A网元
						if (siteId > 0) {
							mInst = siteService.select(mInst).get(0);
							EquimentDataUtil equimentDataUtil = new EquimentDataUtil();
							EquipmentType equipmentType = null;
							equipmentType = equimentDataUtil.getEquipmentType(transformCellType(siteInst, Integer.parseInt(siteInst.getCellType())));

							if (equipmentType != null) {
								siteInst.setEquipInst(this.getEquipInst(equipmentType.getXmlPath()));
							}
							siteInst.setCellDescribe(ip);
							siteInst.setSn(sn);
							siteInst.setCellId(ip);
							siteInst.setCellEditon(33 + "");
							siteInst.setSiteType(369);
							siteInst.setSite_Hum_Id(siteId + "");
							siteInst.setCreateUser("admin");
							siteInst.setCellTimeZone(mInst.getCellTimeZone());
							siteInst.setSwich((Integer.parseInt(ip.split("\\.")[2]) * 256 + Integer.parseInt(ip.split("\\.")[3])) + "");
							siteInst.setFieldID(mInst.getFieldID());
							siteService.saveOrUpdate(siteInst);
							addCard(siteInst, equimentDataUtil, siteInst.getCellType());
						}
					} else {// M网元新建，待设备实现

					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	private void JC54(PtnDataObject ptnDataObject, byte[] dataBody){
		CoderUtils.print16String(dataBody);
		if(dataBody[2] ==1){
			int index = 8+dataBody[7]+2;
			byte[] ospfs = new byte[dataBody.length-index];
			System.arraycopy(dataBody, index, ospfs, 0, ospfs.length);
			CoderUtils.print16String(ospfs);
			ActionObject actionObject = new ActionObject();
			int portnum = CoderUtils.bytesToInt(ospfs[3]);
			List<OSPFinfoWhObeject> ospFinfoWhObejects = new ArrayList<OSPFinfoWhObeject>();
			for (int i = 0; i < portnum; i++) {
				OSPFinfoWhObeject ospFinfoWhObeject = new OSPFinfoWhObeject();
				ospFinfoWhObeject.setIp(CoderUtils.bytesToInt(ospfs[4+i*12])+"."+CoderUtils.bytesToInt(ospfs[5+i*12])+"."+CoderUtils.bytesToInt(ospfs[6+i*12])+"."+CoderUtils.bytesToInt(ospfs[7+i*12]));
				ospFinfoWhObeject.setMask(CoderUtils.bytesToInt(ospfs[9+i*12])+"");
				ospFinfoWhObeject.setPortType(CoderUtils.bytesToInt(ospfs[11+i*12]));
				ospFinfoWhObeject.setVlanValues(CoderUtils.bytesToInt(ospfs[12+i*12])*256+CoderUtils.bytesToInt(ospfs[13+i*12]));
				ospFinfoWhObeject.setFolw(CoderUtils.bytesToInt(ospfs[14+i*12]));
				ospFinfoWhObeject.setPortModel(CoderUtils.bytesToInt(ospfs[15+i*12]));
				ospFinfoWhObeject.setOspfType(11);
				ospFinfoWhObejects.add(ospFinfoWhObeject);
			}
			int fieldnum = CoderUtils.bytesToInt(ospfs[7+portnum*12]);
			for (int i = 0; i < fieldnum; i++) {
				OSPFinfoWhObeject ospFinfoWhObeject = new OSPFinfoWhObeject();
				ospFinfoWhObeject.setIp(CoderUtils.bytesToInt(ospfs[8+portnum*12+i*8])+"."+CoderUtils.bytesToInt(ospfs[9+portnum*12+i*8])+"."+CoderUtils.bytesToInt(ospfs[10+portnum*12+i*8])+"."+CoderUtils.bytesToInt(ospfs[11+portnum*12+i*8]));
				ospFinfoWhObeject.setPortType(CoderUtils.bytesToInt(ospfs[13+i*8+portnum*12]));
				ospFinfoWhObeject.setEnable(CoderUtils.bytesToInt(ospfs[15+i*8+portnum*12]));
				ospFinfoWhObeject.setOspfType(23);
				ospFinfoWhObejects.add(ospFinfoWhObeject);
			}
			actionObject.setOspFinfoWhObejects(ospFinfoWhObejects);
			ptnDataObject.setStatus("配置成功");
			ptnDataObject.setReturnPtnObj(actionObject);
		}else if(CoderUtils.bytesToInt(dataBody[2]) == 2){
			ptnDataObject.setStatus("数据长度超出范围");
		}else if(CoderUtils.bytesToInt(dataBody[2]) == 3){
			ptnDataObject.setStatus("设备正忙");
		}else if(CoderUtils.bytesToInt(dataBody[2]) == 4){
			ptnDataObject.setStatus("获取配置出错");
		}else if(CoderUtils.bytesToInt(dataBody[2]) == 5){
			ptnDataObject.setStatus("获取配置超时");
		}
	}
	
	private int getSiteId(String mIP) {
		SiteService_MB siteService = null;
		List<SiteInst> insts = null;
		List<Integer> ids = new ArrayList<Integer>();
		List<Integer> needRemove = new ArrayList<Integer>();
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			insts = siteService.querySitesByIp(mIP);
			if (insts.size() > 0) {// 所属m网元存在
				for (int i = 1; i < 255; i++) {
					ids.add(i);
				}
				for (SiteInst inst : insts) {
					needRemove.add(Integer.parseInt(inst.getSite_Hum_Id()));
				}
				ids.removeAll(needRemove);
				if (ids.size() > 0) {
					return ids.get(0);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return 0;
	}

	private String transformCellType(SiteInst siInst, int value) {
		if (value == 100) {
			siInst.setCellType("710A");
		} else if (value == 101) {
			siInst.setCellType("710A");
		} else if (value == 102) {
			siInst.setCellType("710A");
		} else if (value == 200) {
			siInst.setCellType("703A");
		} else if (value == 201) {
			siInst.setCellType("703B");
		} else if (value == 203) {
			siInst.setCellType("703-1A");
		} else if (value == 204) {
			siInst.setCellType("703-2A");
		} else if (value == 205) {
			siInst.setCellType("703-1D");
		} else if (value == 206) {
			siInst.setCellType("703-2D");
		} else if (value == 207) {
			siInst.setCellType("703-1A");
		} else if (value == 208) {
			siInst.setCellType("703-2A-2");
		} else if (value == 209) {
			siInst.setCellType("703-4A");
		} else if (value == 210) {
			siInst.setCellType("703-5A");
		}
		return siInst.getCellType();
	}

	private void addCard(SiteInst siteInst, EquimentDataUtil equimentDataUtil, String type) {
		SlotService_MB slotService = null;
		CardService_MB cardService = null;
		CardInst cardInst = null;
		try {
			slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			SlotInst slotInst = new SlotInst();
			slotInst.setSiteId(siteInst.getSite_Inst_Id());
			if ("100".equals(type)) {
				siteInst.setCellType("710A");
				slotInst.setBestCardName("MCU1");
				slotInst = slotService.select(slotInst).get(0);
				cardInst = equimentDataUtil.addCard("config/topo/card/soz/card_710_mc.xml", slotInst);
			} else if ("101".equals(type)) {
				siteInst.setCellType("710B");
				slotInst.setBestCardName("MCU");
				slotInst = slotService.select(slotInst).get(0);
				cardInst = equimentDataUtil.addCard("config/topo/card/soz/card_710b_mcu.xml", slotInst);
			} else if ("102".equals(type)) {
				siteInst.setCellType("710A");
				slotInst.setBestCardName("MCU1");
				slotInst = slotService.select(slotInst).get(0);
				cardInst = equimentDataUtil.addCard("config/topo/card/soz/card_710_mc2.xml", slotInst);
			} else if ("203".equals(type)) {
				siteInst.setCellType("703-1A");
				slotInst.setBestCardName("703-1A_CARD");
				slotInst = slotService.select(slotInst).get(0);
				cardInst = equimentDataUtil.addCard("config/topo/card/szt/card_703-1A.xml", slotInst);
			} else if ("204".equals(type)) {
				siteInst.setCellType("703-2A");
				slotInst.setBestCardName("703-2A_CARD");
				slotInst = slotService.select(slotInst).get(0);
				cardInst = equimentDataUtil.addCard("config/topo/card/szt/card_703-2A.xml", slotInst);
			} else if ("209".equals(type)) {
				siteInst.setCellType("703-4A");
				slotInst.setBestCardName("703-4A_CARD");
				slotInst = slotService.select(slotInst).get(0);
				cardInst = equimentDataUtil.addCard("config/topo/card/szt/card_703-4A.xml", slotInst);
			} else if ("210".equals(type)) {
				siteInst.setCellType("703-5A");
				slotInst.setBestCardName("703-5A_CARD");
				slotInst = slotService.select(slotInst).get(0);
				cardInst = equimentDataUtil.addCard("config/topo/card/szt/card_703-5A.xml", slotInst);
			}
			cardService.saveOrUpdate(cardInst);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(slotService);
			UiUtil.closeService_MB(cardService);
		}

	}

	/**
	 * 读取XML获取机架和槽
	 * 
	 * @return
	 * @throws Exception
	 */
	private EquipInst getEquipInst(String xmlPath) throws Exception {

		EquipInst equipInst = null;
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document doc = null;
		org.w3c.dom.Element root = null;
		NodeList nodeList = null;
		Element parent = null;
		NodeList childList = null;
		Element child = null;
		List<SlotInst> slotInstList = null;
		SlotInst slotInst = null;

		try {
			equipInst = new EquipInst();
			factory = DocumentBuilderFactory.newInstance();
			// 使用DocumentBuilderFactory构建DocumentBulider
			builder = factory.newDocumentBuilder();
			// 使用DocumentBuilder的parse()方法解析文件
			doc = builder.parse(this.getClass().getClassLoader().getResource(xmlPath).toString());
			root = doc.getDocumentElement();
			nodeList = root.getElementsByTagName("equipment");

			for (int i = 0; i < nodeList.getLength(); i++) {
				parent = (org.w3c.dom.Element) nodeList.item(i);

				equipInst.setImagePath(parent.getAttribute("imagePath"));
				equipInst.setEquipx(Integer.parseInt(parent.getAttribute("x")));
				equipInst.setEquipy(Integer.parseInt(parent.getAttribute("y")));

				slotInstList = new ArrayList<SlotInst>();
				childList = parent.getElementsByTagName("slot");
				for (int j = 0; j < childList.getLength(); j++) {
					child = (Element) childList.item(j);
					slotInst = new SlotInst();
					slotInst.setImagePath(child.getAttribute("imagePath"));
					slotInst.setSlotx(Integer.parseInt(child.getAttribute("x")));
					slotInst.setSloty(Integer.parseInt(child.getAttribute("y")));
					slotInst.setSlotType(child.getAttribute("type"));
					slotInst.setBestCardName(child.getAttribute("bestCardName"));
					slotInst.setMasterCardAddress(child.getAttribute("masterCardAddress"));
					if (child.getAttribute("number").length() > 0) {
						slotInst.setNumber(Integer.parseInt(child.getAttribute("number")));
					}
					slotInstList.add(slotInst);
				}
				equipInst.setSlotlist(slotInstList);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			factory = null;
			builder = null;
			doc = null;
			root = null;
			nodeList = null;
			parent = null;
			childList = null;
			child = null;
			slotInstList = null;
			slotInst = null;
		}
		return equipInst;
	}

	public void setPtnDirveService(PtnDirveService ptnDirveService) {
		this.ptnDirveService = ptnDirveService;
	}
	
	public void CM11(PtnDataObject ptnDataObject, byte[] dataBody, byte[] pduHand, byte[] commandHand) {
		AnalysisObjectService analysisObjectService;
		try {
			analysisObjectService = new AnalysisObjectService();
			ActionObject actionObject = analysisObjectService.AnalysisOamStatusTable(dataBody);
			ptnDataObject.setStatus("配置成功");
			ptnDataObject.setReturnPtnObj(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			analysisObjectService = null;
		}
	}
}
