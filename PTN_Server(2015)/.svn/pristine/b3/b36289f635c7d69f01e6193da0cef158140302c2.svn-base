package com.nms.drive.analysis.datablock.status;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.PwProtectStatusObject;
import com.nms.drive.service.impl.CoderUtils;


public class AnalysisPwProtectStatus extends AnalysisBase{
	

	/**
	 * 命令转对象
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<PwProtectStatusObject> analysisCommandsToPortConfigObject(byte[] commands ,String configXml) throws Exception{
		List<PwProtectStatusObject> pwProtectStatusList = new ArrayList<PwProtectStatusObject>();
		int number = commands[24]*256+CoderUtils.bytesToInt(commands[25]);
		for (int i = 0; i < number; i++) {
			PwProtectStatusObject pwProtectStatus = new PwProtectStatusObject();
			byte[] a = new byte[40];
			System.arraycopy(commands, 26+a.length*i, a, 0, a.length);
			CoderUtils.print16String(a);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {
				DriveAttribute driveattribute = driveRoot_config.getDriveAttributeList().get(j);
				driveattributeToObject(driveattribute, pwProtectStatus);
			}
			pwProtectStatusList.add(pwProtectStatus);
		}
		
		return pwProtectStatusList;
	}
	
	public void driveattributeToObject(DriveAttribute driveattribute,PwProtectStatusObject pwProtectStatus){
		if("保护类型".equals(driveattribute.getDescription())){
			pwProtectStatus.setProtectType(Integer.parseInt(driveattribute.getValue()));
		}else if("主用端口".equals(driveattribute.getDescription())){
			pwProtectStatus.setMainPort(driveattribute.getValue());
		}else if("主用LSP ID".equals(driveattribute.getDescription())){
			pwProtectStatus.setMainLspName(driveattribute.getValue());
		}else if("主用PW ID".equals(driveattribute.getDescription())){
			pwProtectStatus.setMainPwName(driveattribute.getValue());
		}else if("主用PW告警".equals(driveattribute.getDescription())){
			pwProtectStatus.setMainPwAlarm(Integer.parseInt(driveattribute.getValue()));
		}else if("备用端口".equals(driveattribute.getDescription())){
			pwProtectStatus.setStandPort(driveattribute.getValue());
		}else if("备用LSP ID".equals(driveattribute.getDescription())){
			pwProtectStatus.setStandLspName(driveattribute.getValue());
		}else if("备用PW ID".equals(driveattribute.getDescription())){
			pwProtectStatus.setStandPwName(driveattribute.getValue());
		}else if("备用PW告警".equals(driveattribute.getDescription())){
			pwProtectStatus.setStandPwAlarm(Integer.parseInt(driveattribute.getValue()));
		}else if("倒换状态".equals(driveattribute.getDescription())){
			pwProtectStatus.setRorateStatus(Integer.parseInt(driveattribute.getValue()));
		}else if("拖延时间".equals(driveattribute.getDescription())){
			pwProtectStatus.setDelaytime(Integer.parseInt(driveattribute.getValue()));
		}else if("接受APS".equals(driveattribute.getDescription())){
			pwProtectStatus.setReceiveAps(Integer.parseInt(driveattribute.getValue()));
		}else if("发送APS".equals(driveattribute.getDescription())){
			pwProtectStatus.setLaunchAps(Integer.parseInt(driveattribute.getValue()));
		}else if("返回类型".equals(driveattribute.getDescription())){
			pwProtectStatus.setBackType(Integer.parseInt(driveattribute.getValue()));
		}
	}
}
