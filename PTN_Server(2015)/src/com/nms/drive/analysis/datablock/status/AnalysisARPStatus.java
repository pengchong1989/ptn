package com.nms.drive.analysis.datablock.status;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.ARPObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisARPStatus extends AnalysisBase{
	/**
	 * 命令转对象
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<ARPObject> analysisCommandsToConfigObject(byte[] commands ,String configXml) throws Exception{
		List<ARPObject> statusList = new ArrayList<ARPObject>();
		int number = commands[24]*256+CoderUtils.bytesToInt(commands[25]);
		for (int i = 0; i < number; i++) {
			ARPObject status = new ARPObject();
			byte[] a = new byte[31];
			System.arraycopy(commands, 30+a.length*i, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			StringBuffer macSB = new StringBuffer();
			StringBuffer sourceIPSB = new StringBuffer();
			StringBuffer targetIPSB = new StringBuffer();
			for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {
				DriveAttribute driveAttribute = driveRoot_config.getDriveAttributeList().get(j);
				this.assignARPObject(status, driveAttribute, macSB, sourceIPSB, targetIPSB);
			}
			statusList.add(status);
		}
		return statusList;
	}
	
	private void assignARPObject(ARPObject arpObject, DriveAttribute driveAttribute, StringBuffer sb, StringBuffer sourceIPSB, StringBuffer targetIPSB) {
		if ("PW保护ID".equals(driveAttribute.getDescription())) {
			arpObject.setPwProtectId(Integer.parseInt(driveAttribute.getValue()));
		} else if ("源MAC地址1".equals(driveAttribute.getDescription())) {
			sb.append(CoderUtils.synchTransformMac(driveAttribute.getValue()));
		} else if ("源MAC地址2".equals(driveAttribute.getDescription())) {
			sb.append("-"+CoderUtils.synchTransformMac(driveAttribute.getValue()));
		} else if ("源MAC地址3".equals(driveAttribute.getDescription())) {
			sb.append("-"+CoderUtils.synchTransformMac(driveAttribute.getValue()));
		} else if ("源MAC地址4".equals(driveAttribute.getDescription())) {
			sb.append("-"+CoderUtils.synchTransformMac(driveAttribute.getValue()));
		} else if ("源MAC地址5".equals(driveAttribute.getDescription())) {
			sb.append("-"+CoderUtils.synchTransformMac(driveAttribute.getValue()));
		} else if ("源MAC地址6".equals(driveAttribute.getDescription())) {
			arpObject.setSourceMac(sb.append("-"+CoderUtils.synchTransformMac(driveAttribute.getValue())).toString());
		} else if ("是否有VLAN".equals(driveAttribute.getDescription())) {
			arpObject.setVlanEnabled(Integer.parseInt(driveAttribute.getValue()));
		} else if ("VLAN值".equals(driveAttribute.getDescription())) {
			arpObject.setVlanId(Integer.parseInt(driveAttribute.getValue()));
		} else if ("VLAN PRI".equals(driveAttribute.getDescription())) {
			arpObject.setVlanPri(Integer.parseInt(driveAttribute.getValue()));
		} else if ("源IP地址1".equals(driveAttribute.getDescription())) {
			sourceIPSB.append(driveAttribute.getValue());
		} else if ("源IP地址2".equals(driveAttribute.getDescription())) {
			sourceIPSB.append("."+driveAttribute.getValue());
		} else if ("源IP地址3".equals(driveAttribute.getDescription())) {
			sourceIPSB.append("."+driveAttribute.getValue());
		} else if ("源IP地址4".equals(driveAttribute.getDescription())) {
			arpObject.setSourceIp(sourceIPSB.append("."+driveAttribute.getValue()).toString());
		} else if ("目的IP地址1".equals(driveAttribute.getDescription())) {
			targetIPSB.append(driveAttribute.getValue());
		} else if ("目的IP地址2".equals(driveAttribute.getDescription())) {
			targetIPSB.append("."+driveAttribute.getValue());
		} else if ("目的IP地址3".equals(driveAttribute.getDescription())) {
			targetIPSB.append("."+driveAttribute.getValue());
		} else if ("目的IP地址4".equals(driveAttribute.getDescription())) {
			arpObject.setTargetIp(targetIPSB.append("."+driveAttribute.getValue()).toString());
		} else if ("ARP报文是否使能".equals(driveAttribute.getDescription())) {
			arpObject.setEnabled(Integer.parseInt(driveAttribute.getValue()));
		} else if ("ARPID".equals(driveAttribute.getDescription())) {
			arpObject.setArpId(Integer.parseInt(driveAttribute.getValue()));
		} 
	}
}
