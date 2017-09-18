package com.nms.drive.analysis.datablock.status;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.PortStatusObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisPortStatus extends AnalysisBase{

	/**
	 * 命令转对象
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<PortStatusObject> analysisCommandsToPortConfigObject(byte[] commands ,String configXml) throws Exception{
		
		List<PortStatusObject> portStatusList = new ArrayList<PortStatusObject>();
		int number = commands[24]*256+CoderUtils.bytesToInt(commands[25]);
		for (int i = 0; i < number; i++) {
			PortStatusObject portStatus = new PortStatusObject();
			byte[] a = new byte[12];
			System.arraycopy(commands, 26+a.length*i, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {
				DriveAttribute driveattribute = driveRoot_config.getDriveAttributeList().get(j);
				driveattributeToObject(driveattribute, portStatus);
			}
			portStatusList.add(portStatus);
		}
		return portStatusList;
	}
	
	public void driveattributeToObject(DriveAttribute driveattribute,PortStatusObject portStatus){
		
		if("端口号".equals(driveattribute.getDescription())){
			portStatus.setPortNumber(Integer.parseInt(driveattribute.getValue()));
		}else if("LINK".equals(driveattribute.getDescription())){
			portStatus.setStatus(Integer.parseInt(driveattribute.getValue()));
		}else if("端口模式".equals(driveattribute.getDescription())){
			portStatus.setModel(Integer.parseInt(driveattribute.getValue()));
		}else if("收包数".equals(driveattribute.getDescription())){
			portStatus.setReceivePackage(Integer.parseInt(driveattribute.getValue()));
		}else if("发包数".equals(driveattribute.getDescription())){
			portStatus.setLaunchPackage(Integer.parseInt(driveattribute.getValue()));
		}else if("环回状态".equals(driveattribute.getDescription())){
			portStatus.setLoopState(Integer.parseInt(driveattribute.getValue()));
		}
		
	}
}
