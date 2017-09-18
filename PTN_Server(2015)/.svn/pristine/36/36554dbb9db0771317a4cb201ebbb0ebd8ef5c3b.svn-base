package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.ARPObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisARPTable extends AnalysisBase {

	public byte[] analysisARPObjectToCommands(List<ARPObject> arpObjectList, String configXml) throws Exception {
		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			String file = driveRoot_config.getDriveAttributeList().get(2).getListRootList().get(0).getFile();
			driveRoot_config.getDriveAttributeList().get(2).getListRootList().clear();// 清除本身自带的一个ListRoot
			driveRoot_config.getDriveAttributeList().get(0).setValue(arpObjectList.size() + "");// 条目数
			for (int i = 0; i < arpObjectList.size(); i++) {
				DriveRoot driveRoot = super.LoadDriveXml(file);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot.getDriveAttributeList());
				for (int j = 0; j < listRoot.getDriveAttributeList().size(); j++) {
					this.arpObj2DriveAttribute(listRoot.getDriveAttributeList().get(j), arpObjectList.get(i));
				}
				driveRoot_config.getDriveAttributeList().get(2).getListRootList().add(listRoot);
			}
			// 将集合转换成命令 
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
			CoderUtils.print16String(dataCommand);
			return dataCommand;
		} catch (Exception e) {
			throw e;
		}
	}

	private void arpObj2DriveAttribute(DriveAttribute driveAttribute, ARPObject arpObject) {
		if ("PW保护ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getPwProtectId()+ "");
		} else if ("源MAC地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getSourceMac().split("-")[0] + "");
		} else if ("源MAC地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getSourceMac().split("-")[1] + "");
		} else if ("源MAC地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getSourceMac().split("-")[2] + "");
		} else if ("源MAC地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getSourceMac().split("-")[3] + "");
		} else if ("源MAC地址5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getSourceMac().split("-")[4] + "");
		} else if ("源MAC地址6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getSourceMac().split("-")[5] + "");
		} else if ("是否有VLAN".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getVlanEnabled() + "");
		} else if ("VLAN值".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getVlanId() + "");
		} else if ("VLAN PRI".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getVlanPri() + "");
		} else if ("源IP地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getSourceIp().split("\\.")[0] + "");
		} else if ("源IP地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getSourceIp().split("\\.")[1] + "");
		} else if ("源IP地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getSourceIp().split("\\.")[2] + "");
		} else if ("源IP地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getSourceIp().split("\\.")[3] + "");
		} else if ("目的IP地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getTargetIp().split("\\.")[0] + "");
		} else if ("目的IP地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getTargetIp().split("\\.")[1] + "");
		} else if ("目的IP地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getTargetIp().split("\\.")[2] + "");
		} else if ("目的IP地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getTargetIp().split("\\.")[3] + "");
		} else if ("ARP报文是否使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getEnabled() + "");
		} else if ("ARPID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(arpObject.getArpId() + "");
		} 
	}

	public List<ARPObject> analysisCommandsToARPObject(byte[] commands, String configXml) throws Exception {
		List<ARPObject> arpObjList = new ArrayList<ARPObject>();
		// 条目数
		byte[] Count = new byte[4];
		Count[0] = 0;
		Count[1] = 0;
		Count[2] = commands[0];
		Count[3] = commands[1];
		int count = CoderUtils.bytesToInt(Count);		
		int pointer = 6;
		byte[] arpByte = new byte[31];
		for (int j = 0; j < count; j++) {
			System.arraycopy(commands, pointer+(arpByte.length*j), arpByte, 0, arpByte.length);				
			ARPObject arpObject = this.getArpObject(arpByte, configXml);
			arpObjList.add(arpObject);
		}
		return arpObjList;
	}

	private ARPObject getArpObject(byte[] arpByte, String configXml) throws Exception {
		ARPObject arpObject = new ARPObject();
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(arpByte);
		try {
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			StringBuffer macSB = new StringBuffer();
			StringBuffer sourceIPSB = new StringBuffer();
			StringBuffer targetIPSB = new StringBuffer();
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(i);
				this.assignARPObject(arpObject, driveAttribute, macSB, sourceIPSB, targetIPSB);
			}
		} catch (Exception e) {
			throw e;
		}
		return arpObject;
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
