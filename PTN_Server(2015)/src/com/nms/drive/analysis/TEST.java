package com.nms.drive.analysis;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.AlarmInfoObject;
import com.nms.drive.service.bean.AlarmLineObject;
import com.nms.drive.service.bean.AlarmMasterCardObject;
import com.nms.drive.service.bean.AlarmObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class TEST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		byte[] commands = new byte[6];
		commands[5] = 0x02;

		byte[] commandsPan = new byte[14];
		commandsPan[13] = 0x02;

		byte[] commandsXL = new byte[6];
		commandsXL[5] = 0x03;

		byte[] commandsSJ = new byte[9];
		commandsSJ[8] = 0x00;

		commands = CoderUtils.arraycopy(commands, commandsPan);

		commands = CoderUtils.arraycopy(commands, commandsXL);
		commands = CoderUtils.arraycopy(commands, commandsSJ);
		commands = CoderUtils.arraycopy(commands, commandsSJ);
		commands = CoderUtils.arraycopy(commands, commandsSJ);

		commands = CoderUtils.arraycopy(commands, commandsXL);
		commands = CoderUtils.arraycopy(commands, commandsSJ);
		commands = CoderUtils.arraycopy(commands, commandsSJ);
		commands = CoderUtils.arraycopy(commands, commandsSJ);

		// ///////////////////////////////////////

		commands = CoderUtils.arraycopy(commands, commandsPan);

		commands = CoderUtils.arraycopy(commands, commandsXL);
		commands = CoderUtils.arraycopy(commands, commandsSJ);
		commands = CoderUtils.arraycopy(commands, commandsSJ);
		commands = CoderUtils.arraycopy(commands, commandsSJ);

		commands = CoderUtils.arraycopy(commands, commandsXL);
		commands = CoderUtils.arraycopy(commands, commandsSJ);
		commands = CoderUtils.arraycopy(commands, commandsSJ);
		commands = CoderUtils.arraycopy(commands, commandsSJ);

		TEST TEST = new TEST();
		try {
			AlarmObject alarmObject = TEST.analysisCommandToAlarm(commands);
			System.out.println(alarmObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,TEST.getClass());
		}

	}

	public AlarmObject analysisCommandToAlarm(byte[] commands) throws Exception {
		String configXml1 = "src\\com\\ptn\\drive\\analysis\\xmltool\\file\\告警NE下所有盘配置.xml";
		String configXml2 = "src\\com\\ptn\\drive\\analysis\\xmltool\\file\\告警NE下单盘配置.xml";
		String configXml3 = "src\\com\\ptn\\drive\\analysis\\xmltool\\file\\告警NE下单盘配置_SUB.xml";
		String configXml4 = "src\\com\\ptn\\drive\\analysis\\xmltool\\file\\告警NE下单盘配置_SUB_SUB.xml";
		byte[] neBuffer = new byte[6];// NE部分字节流数组
		System.arraycopy(commands, 0, neBuffer, 0, 6);// 截取命令中想要的部份
		AlarmObject alarmObject = getAlarmObject(configXml1, neBuffer);// 生成告警对象
		int alarmMasterCardCount = alarmObject.getAlarmMasterCardCount();// 盘长度

		int bufferIndex = 6;// 起始点
		byte[] masterCardBuffer = new byte[14];// 单盘部分字节流数组
		byte[] lineObjectBuffer = new byte[6];// 线路部分字节流数组
		byte[] alarmInfoBuffer = new byte[9];// 线路信息字节流数组
		AlarmMasterCardObject alarmMasterCardObject = null;// 单盘对象
		AlarmLineObject alarmLineObject = null;// 线路对象
		AlarmInfoObject alarmInfoObject = null;// 线路信息对象
		int alarmLineCount = 0;
		int lineInfoCount = 0;
		for (int i = 0; i < alarmMasterCardCount; i++) {// 有几个盘循环几次
			System.arraycopy(commands, bufferIndex, masterCardBuffer, 0, 14);// 从bufferIndex点取出14个字节是盘字节流
			alarmMasterCardObject = getAlarmMasterCardObject(configXml2, masterCardBuffer);// 生成对象
			alarmObject.getAlarmMasterCardObjectList().add(alarmMasterCardObject);// 添加到盘列表中
			bufferIndex = bufferIndex + 14;// 更新起始点

			alarmLineCount = alarmMasterCardObject.getAlarmLineCount();// 线路长度
			for (int j = 0; j < alarmLineCount; j++) {// 有几个线路循环几次
				System.arraycopy(commands, bufferIndex, lineObjectBuffer, 0, 6);// 从bufferIndex点取出6个字节是线路字节流
				alarmLineObject = getAlarmLineObject(configXml3, lineObjectBuffer);
				alarmMasterCardObject.getAlarmLineObjectList().add(alarmLineObject);// 添加到线路列表中
				bufferIndex = bufferIndex + 6;// 更新起始点

				lineInfoCount = alarmLineObject.getLineInfoCount();
				for (int k = 0; k < lineInfoCount; k++) {
					System.arraycopy(commands, bufferIndex, alarmInfoBuffer, 0, 9);// 从bufferIndex点取出6个字节是线路数据字节流
					alarmInfoObject = getAlarmInfoObject(configXml4, alarmInfoBuffer);
					alarmLineObject.getAlarmInfoObjectList().add(alarmInfoObject);// 添加线路信息列表中
					bufferIndex = bufferIndex + 9;// 更新起始点
				}
			}
		}
		return alarmObject;
	}

	/**
	 * 获得告警对象
	 * 
	 * @param fileName
	 * @param neBuffer
	 * @return
	 * @throws Exception
	 */
	private AlarmObject getAlarmObject(String fileName, byte[] neBuffer) throws Exception {
		AlarmObject alarmObject = new AlarmObject();
		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(neBuffer);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(fileName);

			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				// 很多赋值
				if ("告警盘的总数".equals(driveAttribute.getDescription())) {
					alarmObject.setAlarmMasterCardCount(Integer.valueOf(driveAttribute.getValue()));
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return alarmObject;
	}

	/**
	 * 获得盘对象
	 * 
	 * @param fileName
	 * @param masterCardBuffer
	 * @return
	 * @throws Exception
	 */
	private AlarmMasterCardObject getAlarmMasterCardObject(String fileName, byte[] masterCardBuffer) throws Exception {
		AlarmMasterCardObject alarmMasterCardObject = new AlarmMasterCardObject();
		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(masterCardBuffer);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(fileName);

			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				// 很多赋值
				if ("盘数据长度".equals(driveAttribute.getDescription())) {
					alarmMasterCardObject.setAlarmLineCount(Integer.valueOf(driveAttribute.getValue()));
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return alarmMasterCardObject;

	}

	/**
	 * 获得线路对象
	 * 
	 * @param fileName
	 * @param lineObjectBuffer
	 * @return
	 * @throws Exception
	 */
	private AlarmLineObject getAlarmLineObject(String fileName, byte[] lineObjectBuffer) throws Exception {
		AlarmLineObject alarmLineObject = new AlarmLineObject();
		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(lineObjectBuffer);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(fileName);

			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				// 很多赋值
				if ("线路数据长度".equals(driveAttribute.getDescription())) {
					alarmLineObject.setLineInfoCount(Integer.valueOf(driveAttribute.getValue()));
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return alarmLineObject;

	}

	/**
	 * 告警信息
	 * 
	 * @param fileName
	 * @param alarmInfoBuffer
	 * @return
	 * @throws Exception
	 */
	private AlarmInfoObject getAlarmInfoObject(String fileName, byte[] alarmInfoBuffer) throws Exception {
		AlarmInfoObject alarmInfoObject = new AlarmInfoObject();
		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(alarmInfoBuffer);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(fileName);

			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				// 很多赋值
				if ("告警代码".equals(driveAttribute.getDescription())) {
					alarmInfoObject.setAlarmCode(Integer.valueOf(driveAttribute.getValue()));
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return alarmInfoObject;

	}

}
