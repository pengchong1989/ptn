package com.nms.drive.analysis.datablock.status;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.VpwsStatusObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisVPWSStatus extends AnalysisBase{
	

	/**
	 * 命令转对象
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<VpwsStatusObject> analysisCommandsToPortConfigObject(byte[] commands ,String configXml) throws Exception{
		List<VpwsStatusObject> vpwsStatusList = new ArrayList<VpwsStatusObject>();
		int number = commands[24]*256+CoderUtils.bytesToInt(commands[25]);
		for (int i = 0; i < number; i++) {
			VpwsStatusObject vpwsStatus = new VpwsStatusObject();
			StringBuffer buffer1 = new StringBuffer();
			StringBuffer buffer2 = new StringBuffer();
			byte[] a = new byte[75];
			System.arraycopy(commands, 26+a.length*i, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {
				DriveAttribute driveattribute = driveRoot_config.getDriveAttributeList().get(j);
				driveattributeToObject(driveattribute, vpwsStatus,buffer1,buffer2);
			}
			vpwsStatusList.add(vpwsStatus);
		}
		return vpwsStatusList;
	}
	
	public void driveattributeToObject(DriveAttribute driveattribute,VpwsStatusObject vpwsStatus,StringBuffer buffer1,StringBuffer buffer2){
		if("VPWS ID".equals(driveattribute.getDescription())){
			vpwsStatus.setVpwsID(Integer.parseInt(driveattribute.getValue()));
		}else if("流ID".equals(driveattribute.getDescription())){
			vpwsStatus.setBufferID(Integer.parseInt(driveattribute.getValue()));
		}else if("UNI".equals(driveattribute.getDescription())){
			vpwsStatus.setUniPortid(Integer.parseInt(driveattribute.getValue()));
		}else if("业务匹配类型".equals(driveattribute.getDescription())){
			vpwsStatus.setVpwsType(driveattribute.getValue());
		}else if("第一匹配值(1)".equals(driveattribute.getDescription())){
			buffer1.append(driveattribute.getValue()+",");
		}else if("第一匹配值(2)".equals(driveattribute.getDescription())){
			buffer1.append(driveattribute.getValue()+",");
		}else if("第一匹配值(3)".equals(driveattribute.getDescription())){
			buffer1.append(driveattribute.getValue()+",");
		}else if("第一匹配值(4)".equals(driveattribute.getDescription())){
			buffer1.append(driveattribute.getValue()+",");
		}else if("第一匹配值(5)".equals(driveattribute.getDescription())){
			buffer1.append(driveattribute.getValue()+",");
		}else if("第一匹配值(6)".equals(driveattribute.getDescription())){
			buffer1.append(driveattribute.getValue());
			vpwsStatus.setVpwsValue1(buffer1.toString());
		}else if("第二匹配值(1)".equals(driveattribute.getDescription())){
			buffer2.append(driveattribute.getValue()+",");
		}else if("第二匹配值(2)".equals(driveattribute.getDescription())){
			buffer2.append(driveattribute.getValue()+",");
		}else if("第二匹配值(3)".equals(driveattribute.getDescription())){
			buffer2.append(driveattribute.getValue()+",");
		}else if("第二匹配值(4)".equals(driveattribute.getDescription())){
			buffer2.append(driveattribute.getValue()+",");
		}else if("第二匹配值(5)".equals(driveattribute.getDescription())){
			buffer2.append(driveattribute.getValue()+",");
		}else if("第二匹配值(6)".equals(driveattribute.getDescription())){
			buffer2.append(driveattribute.getValue());
			vpwsStatus.setVpwsValue2(buffer2.toString());
		}else if("NNI".equals(driveattribute.getDescription())){
			vpwsStatus.setNniPortid(Integer.parseInt(driveattribute.getValue()));
		}else if("出TUNNEL标签".equals(driveattribute.getDescription())){
			vpwsStatus.setOutTunnelLable(Integer.parseInt(driveattribute.getValue()));
		}else if("出PW标签".equals(driveattribute.getDescription())){
			vpwsStatus.setOutPwLable(Integer.parseInt(driveattribute.getValue()));
		}else if("入TUNNEL标签".equals(driveattribute.getDescription())){
			vpwsStatus.setInTunnelLable(Integer.parseInt(driveattribute.getValue()));
		}else if("入PW标签".equals(driveattribute.getDescription())){
			vpwsStatus.setInPwlable(Integer.parseInt(driveattribute.getValue()));
		}else if("PW ID".equals(driveattribute.getDescription())){
			vpwsStatus.setPwId(Integer.parseInt(driveattribute.getValue()));
		}
	}
}
