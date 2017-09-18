package com.nms.drive.analysis.datablock;

import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.PwBufferManage;
import com.nms.drive.service.bean.PwQueueAndBufferManage;
import com.nms.drive.service.bean.PwQueueManage;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisPwManageTable extends AnalysisBase {

	/**
	 * PwQueueAndBufferManage对象转换为命令
	 * 
	 * @param pwManage
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisPwQueueAndBufferManageToCommands(PwQueueAndBufferManage pwManage, String configXml) throws Exception {
		try {
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			DriveAttribute driveAttribute = null;

			List<PwQueueManage> queueManageList = pwManage.getPwQueueManageList();
			List<PwBufferManage> bufferManageList = pwManage.getPwBufferManageList();
			PwQueueManage queueManage = null;
			PwBufferManage bufferManage = null;
			while (queueManageList.size() < 8) {//不足7补足
				queueManage = new PwQueueManage();
				queueManage.setModel(0);
				queueManage.setWeight(1);
				queueManageList.add(queueManage);
			}
			while (bufferManageList.size() < 8) {//不足7补足
				bufferManage = new PwBufferManage();
				bufferManage.setModel(0);
				bufferManage.setStartLimit(50);
				bufferManage.setEndLimit(100);
				bufferManageList.add(bufferManage);
			}

			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				assignDriveAttribute(driveAttribute, pwManage);// 给driverAttribute赋值
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] commands = analysisDriveXmlToCommand.analysisCommands(driveRoot);
			// CoderUtils.print16String(commands);
			return commands;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			throw e;
		}

	}

	/**
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	public PwQueueAndBufferManage analysisCommandsToPwManage(byte[] commands, String configXml) {

		int NEhead = 5;// NE头长度
		int controlPanelHead = 101;// 盘头长度
		int dataBlockHead = 25;// 配置块头信息长度

		int start = NEhead + controlPanelHead + dataBlockHead;

		byte[] dataCommands = new byte[50];
		System.arraycopy(commands, start, dataCommands, 0, dataCommands.length);

		PwQueueAndBufferManage pwManage = new PwQueueAndBufferManage();
		PwQueueManage queueManage = null;
		PwBufferManage bufferManage = null;

		for (int i = 0; i < 8; i++) {
			queueManage = new PwQueueManage();
			pwManage.getPwQueueManageList().add(queueManage);
		}
		for (int i = 0; i < 8; i++) {
			bufferManage = new PwBufferManage();
			pwManage.getPwBufferManageList().add(bufferManage);
		}

		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(dataCommands);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				assignPwManageObj(pwManage, driveAttribute);// 赋值
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		return pwManage;

	}

	/**
	 * driveAttribute对象赋值
	 * 
	 * @param driveAttribute
	 * @param pwManage
	 * @param count
	 */
	private void assignDriveAttribute(DriveAttribute driveAttribute, PwQueueAndBufferManage pwManage) {
		if ("(出口队列调度策略)(队列0)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(0).getModel() + "");
		}
		if ("(队列0)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(0).getWeight() + "");
		}
		if ("(出口队列调度策略)(队列1)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(1).getModel() + "");
		}
		if ("(队列1)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(1).getWeight() + "");
		}
		if ("(出口队列调度策略)(队列2)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(2).getModel() + "");
		}
		if ("(队列2)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(2).getWeight() + "");
		}
		if ("(出口队列调度策略)(队列3)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(3).getModel() + "");
		}
		if ("(队列3)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(3).getWeight() + "");
		}
		if ("(出口队列调度策略)(队列4)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(4).getModel() + "");
		}
		if ("(队列4)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(4).getWeight() + "");
		}
		if ("(出口队列调度策略)(队列5)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(5).getModel() + "");
		}
		if ("(队列5)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(5).getWeight() + "");
		}
		if ("(出口队列调度策略)(队列6)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(6).getModel() + "");
		}
		if ("(队列6)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(6).getWeight() + "");
		}
		if ("(出口队列调度策略)(队列7)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(7).getModel() + "");
		}
		if ("(队列7)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwQueueManageList().get(7).getWeight() + "");
		}
		if ("(队列缓存管理策略)(队列0)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(0).getModel() + "");
		}
		if ("(队列0)START门限".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(0).getStartLimit() + "");
		}
		if ("(队列0)END门限".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(0).getEndLimit() + "");
		}
		if ("(队列缓存管理策略)(队1列)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(1).getModel() + "");
		}
		if ("(队列)START门1限".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(1).getStartLimit() + "");
		}
		if ("(队列)END门1限".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(1).getEndLimit() + "");
		}
		if ("(队列缓存管理策略)(队2列)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(2).getModel() + "");
		}
		if ("(队列)START门限2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(2).getStartLimit() + "");
		}
		if ("(队列)END门限2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(2).getEndLimit() + "");
		}
		if ("(队列缓存管理策略)(队列3)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(3).getModel() + "");
		}
		if ("(队列)START门限3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(3).getStartLimit() + "");
		}
		if ("(队列)END门限3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(3).getEndLimit() + "");
		}
		if ("(队列缓存管理策略)(队列4)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(4).getModel() + "");
		}
		if ("(队列)START门限4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(4).getStartLimit() + "");
		}
		if ("(队列)END门限4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(4).getEndLimit() + "");
		}
		if ("(队列缓存管理策略)(队列5)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(5).getModel() + "");
		}
		if ("(队列)START门限5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(5).getStartLimit() + "");
		}
		if ("(队列)END门限5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(5).getEndLimit() + "");
		}
		if ("(队列缓存管理策略)(队列6)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(6).getModel() + "");
		}
		if ("(队列)START门限6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(6).getStartLimit() + "");
		}
		if ("(队列)END门限6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(6).getEndLimit() + "");
		}
		if ("(队列缓存管理策略)(队列7)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(7).getModel() + "");
		}
		if ("(队列)START门限7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(7).getStartLimit() + "");
		}
		if ("(队列)END门限7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getPwBufferManageList().get(7).getEndLimit() + "");
		}
		if ("PW层出口限速".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getExitSpeedLimit() + "");
		}
		if ("备用".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwManage.getReserve() + "");
		}

	}

	/**
	 * PwQueueAndBufferManage对象赋值
	 * 
	 * @param driveAttribute
	 * @param pwManage
	 * @param count
	 */
	private void assignPwManageObj(PwQueueAndBufferManage pwManage, DriveAttribute driveAttribute) {

		if ("(出口队列调度策略)(队列0)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(0).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列0)权重".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(0).setWeight(Integer.parseInt(driveAttribute.getValue()));

		}
		if ("(出口队列调度策略)(队列1)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(1).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列1)权重".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(1).setWeight(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(出口队列调度策略)(队列2)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(2).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列2)权重".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(2).setWeight(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(出口队列调度策略)(队列3)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(3).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列3)权重".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(3).setWeight(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(出口队列调度策略)(队列4)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(4).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列4)权重".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(4).setWeight(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(出口队列调度策略)(队列5)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(5).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列5)权重".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(5).setWeight(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(出口队列调度策略)(队列6)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(6).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列6)权重".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(6).setWeight(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(出口队列调度策略)(队列7)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(7).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列7)权重".equals(driveAttribute.getDescription())) {
			pwManage.getPwQueueManageList().get(7).setWeight(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列缓存管理策略)(队列0)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(0).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列0)START门限".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(0).setStartLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列0)END门限".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(0).setEndLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列缓存管理策略)(队1列)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(1).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)START门1限".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(1).setStartLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)END门1限".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(1).setEndLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列缓存管理策略)(队2列)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(2).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)START门限2".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(2).setStartLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)END门限2".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(2).setEndLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列缓存管理策略)(队列3)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(3).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)START门限3".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(3).setStartLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)END门限3".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(3).setEndLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列缓存管理策略)(队列4)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(4).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)START门限4".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(4).setStartLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)END门限4".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(4).setEndLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列缓存管理策略)(队列5)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(5).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)START门限5".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(5).setStartLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)END门限5".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(5).setEndLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列缓存管理策略)(队列6)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(6).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)START门限6".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(6).setStartLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)END门限6".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(6).setEndLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列缓存管理策略)(队列7)模式".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(7).setModel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)START门限7".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(7).setStartLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)END门限7".equals(driveAttribute.getDescription())) {
			pwManage.getPwBufferManageList().get(7).setEndLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("PW层出口限速".equals(driveAttribute.getDescription())) {
			pwManage.setExitSpeedLimit(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("备用".equals(driveAttribute.getDescription())) {

		}

	}

}
