package com.nms.drive.analysis.datablock.status;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.BfdStatusObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisBfdStatus extends AnalysisBase{

	/**
	 * 命令转对象
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<BfdStatusObject> analysisCommandsToBfdConfigObject(byte[] commands ,String configXml) throws Exception{
		
		List<BfdStatusObject> bfdStatusList = new ArrayList<BfdStatusObject>();
		int number = commands[24]*256+CoderUtils.bytesToInt(commands[25]);
		for (int i = 0; i < number; i++) {
			BfdStatusObject bfdStatus = new BfdStatusObject();
			byte[] a = new byte[50];
			System.arraycopy(commands, 26+a.length*i, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {
				DriveAttribute driveattribute = driveRoot_config.getDriveAttributeList().get(j);
				driveattributeToObject(driveattribute, bfdStatus);
			}
			bfdStatusList.add(bfdStatus);
		}
		return bfdStatusList;
	}
	
	public void driveattributeToObject(DriveAttribute driveattribute,BfdStatusObject bfdStatus){
		
		if("BFDID".equals(driveattribute.getDescription())){
			bfdStatus.setBfdId(Integer.parseInt(driveattribute.getValue()));
		}else if("本端会话标识符".equals(driveattribute.getDescription())){
			bfdStatus.setMySid(Integer.parseInt(driveattribute.getValue()));
		}else if("对端会话标识符".equals(driveattribute.getDescription())){
			bfdStatus.setPeerSid(Integer.parseInt(driveattribute.getValue()));
		}else if("BFD状态".equals(driveattribute.getDescription())){
			bfdStatus.setBfdStatus(Integer.parseInt(driveattribute.getValue()));
		}else if("BFD发送包数".equals(driveattribute.getDescription())){
			bfdStatus.setBfdSend(Integer.parseInt(driveattribute.getValue()));
		}else if("BFD接收包数".equals(driveattribute.getDescription())){
			bfdStatus.setBfdRe(Integer.parseInt(driveattribute.getValue()));
		}else if("BFD报文接收时间间隔".equals(driveattribute.getDescription())){
			bfdStatus.setBfdReWorkingPeriod(Integer.parseInt(driveattribute.getValue()));
		}else if("BFD报文发送时间间隔".equals(driveattribute.getDescription())){
			bfdStatus.setBfdSeWorkingPeriod(Integer.parseInt(driveattribute.getValue()));
		}else if("BFD报文发送时间间隔".equals(driveattribute.getDescription())){
			bfdStatus.setBfdSeWorkingPeriod(Integer.parseInt(driveattribute.getValue()));
		}else if("BFD类型".equals(driveattribute.getDescription())){
			bfdStatus.setBfdType(Integer.parseInt(driveattribute.getValue()));
		}else if("端口号".equals(driveattribute.getDescription())){
			bfdStatus.setPortId(Integer.parseInt(driveattribute.getValue()));
		}else if("LSP标签".equals(driveattribute.getDescription())){
			bfdStatus.setLspLabel(Integer.parseInt(driveattribute.getValue()));
		}else if("PW标签".equals(driveattribute.getDescription())){
			bfdStatus.setPwLabel(Integer.parseInt(driveattribute.getValue()));
		}else if("LSP ID".equals(driveattribute.getDescription())){
			bfdStatus.setLspId(Integer.parseInt(driveattribute.getValue()));
		}else if("PW ID".equals(driveattribute.getDescription())){
			bfdStatus.setPwId(Integer.parseInt(driveattribute.getValue()));
		}		
	}
}
