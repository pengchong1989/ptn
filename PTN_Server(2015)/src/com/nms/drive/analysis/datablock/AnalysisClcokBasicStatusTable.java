package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.FrequencyStatusObject;
import com.nms.ui.manager.ExceptionManage;

/**
 * 时钟状态基本信息
 * @author pc
 *
 */
public class AnalysisClcokBasicStatusTable extends AnalysisBase{
	public FrequencyStatusObject analysisCommandsToFrequencyStatusInfo(byte[] commands,String configXml){
		FrequencyStatusObject frequencyStatusInfo = new FrequencyStatusObject();
		byte[] a = new byte[15];
		System.arraycopy(commands, 24, a, 0, a.length);
		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			for (int i = 0; i < driveRoot_config.getDriveAttributeList().size(); i++) {
				DriveAttribute driveattribute = driveRoot_config.getDriveAttributeList().get(i);
				// 属性赋值
				DriveAttributeToObject(frequencyStatusInfo, driveattribute);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return frequencyStatusInfo;
	}
	
	public void DriveAttributeToObject(FrequencyStatusObject frequencyStatusInfo,DriveAttribute driveattribute){
	
		if ("时钟工作状态".equals(driveattribute.getDescription())) {
			frequencyStatusInfo.setClockStatus(Integer.parseInt(driveattribute.getValue()));
		}else if("外时钟输出状态".equals(driveattribute.getDescription())){
			frequencyStatusInfo.setOutclockStatus(Integer.parseInt(driveattribute.getValue()));
		}else if("外时钟输出类型".equals(driveattribute.getDescription())){
			frequencyStatusInfo.setOutclockType(Integer.parseInt(driveattribute.getValue()));
		}else if("外时钟输出方式".equals(driveattribute.getDescription())){
			frequencyStatusInfo.setOutclockMode(Integer.parseInt(driveattribute.getValue()));
		}else if("输入源SA选择".equals(driveattribute.getDescription())){
			frequencyStatusInfo.setQlInSa(Integer.parseInt(driveattribute.getValue()));
		}else if("输出源SA选择".equals(driveattribute.getDescription())){
			frequencyStatusInfo.setQlOutSa(Integer.parseInt(driveattribute.getValue()));
		}else if("QL使能状态".equals(driveattribute.getDescription())){
			frequencyStatusInfo.setQlEnable(Integer.parseInt(driveattribute.getValue()));
		}else if("当前锁定源".equals(driveattribute.getDescription())){
			frequencyStatusInfo.setLockSource(Integer.parseInt(driveattribute.getValue()));
		}else if("当前锁定源S1值".equals(driveattribute.getDescription())){
			frequencyStatusInfo.setLockSourceS1(Integer.parseInt(driveattribute.getValue()));
		}else if("当前锁定源类型".equals(driveattribute.getDescription())){
			frequencyStatusInfo.setLockSourceType(Integer.parseInt(driveattribute.getValue()));
		}else if("当前锁定源状态".equals(driveattribute.getDescription())){
			frequencyStatusInfo.setLockSourceStatus(Integer.parseInt(driveattribute.getValue()));
		}else if("外时钟SSM门限".equals(driveattribute.getDescription())){
			frequencyStatusInfo.setSsmOut(Integer.parseInt(driveattribute.getValue()));
		}else if("系统时钟SSM门限".equals(driveattribute.getDescription())){
			frequencyStatusInfo.setSsmSystem(Integer.parseInt(driveattribute.getValue()));
		}
	}
}
