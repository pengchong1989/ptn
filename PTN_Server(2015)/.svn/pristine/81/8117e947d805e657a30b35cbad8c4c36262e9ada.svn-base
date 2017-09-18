package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.EthLoopObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisEthLoopTable extends AnalysisBase{
	/**
	 * 解析XML转换成命令
	 * 
	 * @param globalObject
	 *            对象
	 * @param configXml
	 *            文件目录地址
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisEthLoopObjectToCommands(EthLoopObject ethLoopObject,String configXml) throws Exception {
		// 获取根目录
		DriveRoot globalDrivRoot = super.LoadDriveXml(configXml);
		
		for (int i = 0, j = globalDrivRoot.getDriveAttributeList().size(); i < j; i++) {
			DriveAttribute driveAttribute = globalDrivRoot.getDriveAttributeList().get(i);
			// 属性赋值
			ethLoopObjectTODriveAttribute(driveAttribute, ethLoopObject);
		}
		// 将集合转换成命令
		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(globalDrivRoot);
		CoderUtils.print16String(dataCommand);
		
		return dataCommand;
	}
	
	private void ethLoopObjectTODriveAttribute(DriveAttribute driveAttribute,EthLoopObject ethLoopObject) {
		// 环回使能
		if ("环回使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethLoopObject.getLoopEnableObject()+"");
		}
		// 端口号
		if ("端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethLoopObject.getPortNumberObject()+"");
		}
		// 环回规则
		if ("环回规则".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethLoopObject.getLoopRuleObject()+"");
		}
		// MAC地址1
		if ("MAC地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethLoopObject.getMacAddress().split("-")[0]+"");
		}
		// MAC地址2
		if ("MAC地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethLoopObject.getMacAddress().split("-")[1]+"");
		}
		// MAC地址3
		if ("MAC地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethLoopObject.getMacAddress().split("-")[2]+"");
		}
		// MAC地址4
		if ("MAC地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethLoopObject.getMacAddress().split("-")[3]+"");
		}
		// MAC地址5
		if ("MAC地址5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethLoopObject.getMacAddress().split("-")[4]+"");
		}
		// MAC地址6
		if ("MAC地址6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethLoopObject.getMacAddress().split("-")[5]+"");
		}
		// Vlan
		if ("Vlan".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethLoopObject.getVlan());
		}
		// IP地址1
		if ("IP地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethLoopObject.getIpAddress().split("\\.")[0]);
		}
		// IP地址2
		if ("IP地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethLoopObject.getIpAddress().split("\\.")[1]);
		}
		// IP地址3
		if ("IP地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethLoopObject.getIpAddress().split("\\.")[2]);
		}
		// IP地址4
		if ("IP地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethLoopObject.getIpAddress().split("\\.")[3]);
		}
		
	}
}
