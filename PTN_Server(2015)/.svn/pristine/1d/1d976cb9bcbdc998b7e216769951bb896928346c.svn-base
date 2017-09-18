package com.nms.drive.analysis.datablock.status;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.ProtectStatusObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisLspProetctStatus extends AnalysisBase {

	/**
	 * 命令转对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<ProtectStatusObject> analysisCommandsToPortConfigObject(byte[] commands, String configXml) throws Exception {
		CoderUtils.print16String(commands);
		List<ProtectStatusObject> protectStatusList = new ArrayList<ProtectStatusObject>();
		int number = commands[24] * 256 + CoderUtils.bytesToInt(commands[25]);
		for (int i = 0; i < number; i++) {
			ProtectStatusObject protectStatus = new ProtectStatusObject();
			byte[] a = new byte[20];
			System.arraycopy(commands, 26 + a.length * i, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {
				DriveAttribute driveattribute = driveRoot_config.getDriveAttributeList().get(j);
				driveattributeToObject(driveattribute, protectStatus);
			}
			protectStatusList.add(protectStatus);
		}
		return protectStatusList;
	}

	public void driveattributeToObject(DriveAttribute driveattribute, ProtectStatusObject protectStatus) {
		if ("保护名称".equals(driveattribute.getDescription())) {
			protectStatus.setName(driveattribute.getValue());
		} else if ("保护类型".equals(driveattribute.getDescription())) {
			protectStatus.setProtectType(Integer.parseInt(driveattribute.getValue()));
		} else if ("主用端口".equals(driveattribute.getDescription())) {
			protectStatus.setMainPort(driveattribute.getValue());
		} else if ("主用LSP告警".equals(driveattribute.getDescription())) {
			protectStatus.setMainLspAlarm(Integer.parseInt(driveattribute.getValue()));
		} else if ("备用端口".equals(driveattribute.getDescription())) {
			protectStatus.setStandPort(driveattribute.getValue());
		} else if ("备用LSP告警".equals(driveattribute.getDescription())) {
			protectStatus.setStandLspAlarm(Integer.parseInt(driveattribute.getValue()));
		} else if ("倒换状态".equals(driveattribute.getDescription())) {
			protectStatus.setRorateStatus(Integer.parseInt(driveattribute.getValue()));
		} else if ("拖延时间(ms)".equals(driveattribute.getDescription())) {
			protectStatus.setDelaytime(Integer.parseInt(driveattribute.getValue()));
		} else if ("接收APS".equals(driveattribute.getDescription())) {
			protectStatus.setReceiveAps(Integer.parseInt(driveattribute.getValue()));
		} else if ("发送APS".equals(driveattribute.getDescription())) {
			protectStatus.setLaunchAps(Integer.parseInt(driveattribute.getValue()));
		} else if ("返回类型".equals(driveattribute.getDescription())) {
			protectStatus.setBackType(Integer.parseInt(driveattribute.getValue()));
		} else if ("当前工作路径".equals(driveattribute.getDescription())) {
			protectStatus.setIsWorking(Integer.parseInt(driveattribute.getValue()));
		} else if ("拖延时间高8位".equals(driveattribute.getDescription())) {
			protectStatus.setDelaytime(Integer.parseInt(driveattribute.getValue())*256+protectStatus.getDelaytime());
		}
	}
}
