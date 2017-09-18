package com.nms.drive.analysis.datablock.status;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.TunnelStatusObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisTunnelStatus extends AnalysisBase{
	

	/**
	 * 命令转对象
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<TunnelStatusObject> analysisCommandsToPortConfigObject(byte[] commands ,String configXml) throws Exception{
		List<TunnelStatusObject> tunnelStatusList = new ArrayList<TunnelStatusObject>();
		int number = commands[24]*256+CoderUtils.bytesToInt(commands[25]);
		for (int i = 0; i < number; i++) {
			TunnelStatusObject tunnelStatus = new TunnelStatusObject();
			byte[] a = new byte[15];
			System.arraycopy(commands, 26+a.length*i, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {
				DriveAttribute driveattribute = driveRoot_config.getDriveAttributeList().get(j);
				driveattributeToObject(driveattribute, tunnelStatus);
			}
			tunnelStatusList.add(tunnelStatus);
		}
		return tunnelStatusList;
	}
	
	public void driveattributeToObject(DriveAttribute driveattribute,TunnelStatusObject tunnelStatus){
		if("LSP ID".equals(driveattribute.getDescription())){
			tunnelStatus.setTunnelID(Integer.parseInt(driveattribute.getValue()));
		}else if("节点类型".equals(driveattribute.getDescription())){
			tunnelStatus.setNodeType(Integer.parseInt(driveattribute.getValue()));
		}else if("入端口号".equals(driveattribute.getDescription())){
			tunnelStatus.setInPort(Integer.parseInt(driveattribute.getValue()));
		}else if("入标签".equals(driveattribute.getDescription())){
			tunnelStatus.setInLable(Integer.parseInt(driveattribute.getValue()));
		}else if("出端口号".equals(driveattribute.getDescription())){
			tunnelStatus.setOutPort(Integer.parseInt(driveattribute.getValue()));
		}else if("出标签".equals(driveattribute.getDescription())){
			tunnelStatus.setOutLable(Integer.parseInt(driveattribute.getValue()));
		}
	}
}
