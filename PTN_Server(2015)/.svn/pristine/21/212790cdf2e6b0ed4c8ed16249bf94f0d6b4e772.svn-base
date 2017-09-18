package com.nms.drive.analysis.datablock.status;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.PwStatusObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisPwStatus extends AnalysisBase{
	

	/**
	 * 命令转对象
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<PwStatusObject> analysisCommandsToPortConfigObject(byte[] commands ,String configXml) throws Exception{
		List<PwStatusObject> pwStatuss = new ArrayList<PwStatusObject>();
		int number = commands[24]*256+CoderUtils.bytesToInt(commands[25]);
		for (int i = 0; i < number; i++) {
			PwStatusObject pwStatus = new PwStatusObject();
			byte[] a = new byte[18];
			System.arraycopy(commands, 26+a.length*i, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {
				DriveAttribute driveattribute = driveRoot_config.getDriveAttributeList().get(j);
				driveattributeToObject(driveattribute, pwStatus);
			}
			pwStatuss.add(pwStatus);
		}
		return pwStatuss;
	}
	
	public void driveattributeToObject(DriveAttribute driveattribute,PwStatusObject pwStatus){
		if("PW ID".equals(driveattribute.getDescription())){
			pwStatus.setPwName(driveattribute.getValue());
		}else if("入标签 ".equals(driveattribute.getDescription())){
			pwStatus.setInlable(Integer.parseInt(driveattribute.getValue()));
		}else if("出标签".equals(driveattribute.getDescription())){
			pwStatus.setOutlable(Integer.parseInt(driveattribute.getValue()));
		}else if("LSP ID".equals(driveattribute.getDescription())){
			pwStatus.setTunnelName(driveattribute.getValue());
		}else if("CV使能".equals(driveattribute.getDescription())){
			pwStatus.setCvEnable(Integer.parseInt(driveattribute.getValue()));
		}else if("CV周期".equals(driveattribute.getDescription())){
			pwStatus.setCvCircle(Integer.parseInt(driveattribute.getValue()));
		}else if("LOC".equals(driveattribute.getDescription())){
			pwStatus.setIsLoc(Integer.parseInt(driveattribute.getValue()));
		}else if("RDI".equals(driveattribute.getDescription())){
			pwStatus.setIsRdi(Integer.parseInt(driveattribute.getValue()));
		}
	}
}
