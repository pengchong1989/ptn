package com.nms.drive.analysis.datablock.status;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.WrappingStatusObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisWrappingStatus extends AnalysisBase{
	

	/**
	 * 命令转对象
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<WrappingStatusObject> analysisCommandsToPortConfigObject(byte[] commands ,String configXml) throws Exception{
		List<WrappingStatusObject> wrappingStatusList = new ArrayList<WrappingStatusObject>();
		int number = commands[24]*256+CoderUtils.bytesToInt(commands[25]);
		for (int i = 0; i < number; i++) {
			WrappingStatusObject wrappingStatus = new WrappingStatusObject();
			StringBuffer buffer1 = new StringBuffer();
			StringBuffer buffer2 = new StringBuffer();
			byte[] a = new byte[47];
			System.arraycopy(commands, 26+a.length*i, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {
				DriveAttribute driveattribute = driveRoot_config.getDriveAttributeList().get(j);
				driveattributeToObject(driveattribute, wrappingStatus,buffer1,buffer2);
			}
			wrappingStatusList.add(wrappingStatus);
		}
		return wrappingStatusList;
	}
	
	public void driveattributeToObject(DriveAttribute driveattribute,WrappingStatusObject vpwsStatus,StringBuffer buffer1,StringBuffer buffer2){
		if("环ID".equals(driveattribute.getDescription())){
			vpwsStatus.setWrappingId(Integer.parseInt(driveattribute.getValue()));
		}else if("西向端口".equals(driveattribute.getDescription())){
			vpwsStatus.setWestPort(Integer.parseInt(driveattribute.getValue()));
		}else if("东向端口".equals(driveattribute.getDescription())){
			vpwsStatus.setEastPort(Integer.parseInt(driveattribute.getValue()));
		}else if("保护类型".equals(driveattribute.getDescription())){
			vpwsStatus.setProtectType(Integer.parseInt(driveattribute.getValue()));
		}else if("倒换状态".equals(driveattribute.getDescription())){
			vpwsStatus.setRorateStatus(Integer.parseInt(driveattribute.getValue()));
		}else if("环网节点总数".equals(driveattribute.getDescription())){
			vpwsStatus.setTotalNumber(Integer.parseInt(driveattribute.getValue()));
		}else if("本站节点id".equals(driveattribute.getDescription())){
			vpwsStatus.setLocalNumber(Integer.parseInt(driveattribute.getValue()));
		}else if("西向线路告警".equals(driveattribute.getDescription())){
			vpwsStatus.setWestAlarm(Integer.parseInt(driveattribute.getValue()));
		}else if("东向线路告警".equals(driveattribute.getDescription())){
			vpwsStatus.setEastAlarm(Integer.parseInt(driveattribute.getValue()));
		}else if("西向收APS信息".equals(driveattribute.getDescription())){
			vpwsStatus.setWestReceiveAps(Integer.parseInt(driveattribute.getValue()));
		}else if("东向收APS信息".equals(driveattribute.getDescription())){
			vpwsStatus.setEastReceiveAps(Integer.parseInt(driveattribute.getValue()));
		}else if("西向发APS信息".equals(driveattribute.getDescription())){
			vpwsStatus.setWestSendAps(Integer.parseInt(driveattribute.getValue()));
		}else if("东向发APS信息".equals(driveattribute.getDescription())){
			vpwsStatus.setEastSendAps(Integer.parseInt(driveattribute.getValue()));
		}else if("拖延时间".equals(driveattribute.getDescription())){
			vpwsStatus.setDelayTime(Integer.parseInt(driveattribute.getValue()));
		}else if("返回类型".equals(driveattribute.getDescription())){
			vpwsStatus.setBackType(Integer.parseInt(driveattribute.getValue()));
		}
	}
}
