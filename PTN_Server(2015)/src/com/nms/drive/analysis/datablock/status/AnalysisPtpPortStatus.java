package com.nms.drive.analysis.datablock.status;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.PtpPortStatusObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisPtpPortStatus extends AnalysisBase{

	/**
	 * 命令转对象
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<PtpPortStatusObject> analysisCommandsToPtpPortConfigObject(byte[] commands ,String configXml) throws Exception{
		
		List<PtpPortStatusObject> ptpPortStatusList = new ArrayList<PtpPortStatusObject>();
		int number = commands[24]*256+CoderUtils.bytesToInt(commands[25]);
		for (int i = 0; i < number; i++) {
			PtpPortStatusObject ptpPortStatus = new PtpPortStatusObject();
			byte[] a = new byte[30];
			System.arraycopy(commands, 26+a.length*i, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {
				DriveAttribute driveattribute = driveRoot_config.getDriveAttributeList().get(j);
				driveattributeToObject(driveattribute, ptpPortStatus);
			}
			ptpPortStatusList.add(ptpPortStatus);
		}
		return ptpPortStatusList;
	}
	
	public void driveattributeToObject(DriveAttribute driveattribute,PtpPortStatusObject ptpPortStatus){
		
		if("索引".equals(driveattribute.getDescription())){
			ptpPortStatus.setIndexId(Integer.parseInt(driveattribute.getValue()));
		}else if("槽位号".equals(driveattribute.getDescription())){
			ptpPortStatus.setSlotId(Integer.parseInt(driveattribute.getValue()));
		}else if("端口号".equals(driveattribute.getDescription())){
			ptpPortStatus.setPortNum(Integer.parseInt(driveattribute.getValue()));
		}else if("端口状态".equals(driveattribute.getDescription())){
			ptpPortStatus.setPortStatus(Integer.parseInt(driveattribute.getValue()));
		}else if("端口时钟ID".equals(driveattribute.getDescription())){
			ptpPortStatus.setCouplePortId(Integer.parseInt(driveattribute.getValue()));
		}else if("端口ID".equals(driveattribute.getDescription())){
			ptpPortStatus.setPortId(Integer.parseInt(driveattribute.getValue()));
		}else if("对偶端口ID".equals(driveattribute.getDescription())){
			ptpPortStatus.setCouplePortId(Integer.parseInt(driveattribute.getValue()));
		}else if("线路不对称时延属性".equals(driveattribute.getDescription())){
			ptpPortStatus.setLine(Integer.parseInt(driveattribute.getValue()));
		}else if("线路不对称时延补偿值".equals(driveattribute.getDescription())){
			ptpPortStatus.setLineCpn(Integer.parseInt(driveattribute.getValue()));
		}else if("延时机制".equals(driveattribute.getDescription())){
			ptpPortStatus.setDelayMeca(Integer.parseInt(driveattribute.getValue()));
		}else if("消息模式".equals(driveattribute.getDescription())){
			ptpPortStatus.setMessageMode(Integer.parseInt(driveattribute.getValue()));
		}
		
		
	}
}
