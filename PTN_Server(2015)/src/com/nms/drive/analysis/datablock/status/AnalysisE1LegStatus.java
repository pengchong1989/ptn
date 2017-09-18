package com.nms.drive.analysis.datablock.status;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.E1LegStatusObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisE1LegStatus extends AnalysisBase{
	String subXmlPath = "com/nms/drive/analysis/xmltool/file/status/E1仿真状态配置块_SUB.xml";
	/**
	 * 命令转对象
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<E1LegStatusObject> analysisCommandsToPortConfigObject(byte[] commands ,String configXml) throws Exception{
		List<E1LegStatusObject> e1LegStatusList = new ArrayList<E1LegStatusObject>();
		CoderUtils.print16String(commands);
		int number = commands[57];
		byte[] a = new byte[34];
		System.arraycopy(commands, 24, a, 0, a.length);
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(a);
		DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
		for (int i = 0; i < number; i++) {
			E1LegStatusObject e1LegStatus = new E1LegStatusObject();
			for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {
				DriveAttribute driveattribute = driveRoot_config.getDriveAttributeList().get(j);
				driveattributeToObject(driveattribute, e1LegStatus);
			}
			byte[] a1 = new byte[14];
			System.arraycopy(commands, 24+a.length+a1.length*i, a1, 0, a1.length);
			analysisCommandByDriveXml.setCommands(a1);
			DriveRoot driveRoot_config_sub = analysisCommandByDriveXml.analysisDriveAttrbute(subXmlPath);
			for (int k = 0; k < driveRoot_config_sub.getDriveAttributeList().size(); k++) {
				DriveAttribute driveattribute = driveRoot_config_sub.getDriveAttributeList().get(k);
				driveattributeToE1LegObject(driveattribute, e1LegStatus);
			}
			e1LegStatusList.add(e1LegStatus);
		}		
		return e1LegStatusList;
	}
	
	private void driveattributeToE1LegObject(DriveAttribute driveattribute,
			E1LegStatusObject e1Leg) {
		if("端口名称".equals(driveattribute.getDescription())){
			e1Leg.setE1LegID(Integer.parseInt(driveattribute.getValue()));
		}else if("2M端口使用状态".equals(driveattribute.getDescription())){
			e1Leg.setE1LegStatus(Integer.parseInt(driveattribute.getValue()));
		}else if("2M端口类型".equals(driveattribute.getDescription())){
			e1Leg.setE1LegType(Integer.parseInt(driveattribute.getValue()));
		}else if("2M端口速率".equals(driveattribute.getDescription())){
			e1Leg.setE1legSpeed(Integer.parseInt(driveattribute.getValue()));
		}else if("2M通道化属性".equals(driveattribute.getDescription())){
			e1Leg.setE1Attr(Integer.parseInt(driveattribute.getValue()));
		}
	}

	private void driveattributeToObject(DriveAttribute driveattribute, E1LegStatusObject e1Leg){
		if("E1仿真板在位状态".equals(driveattribute.getDescription())){
			e1Leg.setE1Status(Integer.parseInt(driveattribute.getValue()));
		}else if("E1仿真板模式".equals(driveattribute.getDescription())){
			e1Leg.setE1Model(Integer.parseInt(driveattribute.getValue()));
		}else if("TDM时钟源".equals(driveattribute.getDescription())){
			e1Leg.setTdmClock(Integer.parseInt(driveattribute.getValue()));
		}
	}
}
