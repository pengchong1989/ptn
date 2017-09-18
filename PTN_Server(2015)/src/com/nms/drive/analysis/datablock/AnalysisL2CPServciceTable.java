package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.L2CPinfoObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisL2CPServciceTable extends AnalysisBase{
	
	/**
	 * 将驱动对象按照协议解析成命令
	 * @param L2CPinfoObject
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisAclToCommands(L2CPinfoObject L2CPinfoObject,String configXml)throws Exception{
		try {
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			for(int i = 0; i< driveRoot.getDriveAttributeList().size() ; i++){
				DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(i);
				//属性赋值
				L2cpObjectToDriveAttribute(driveAttribute,L2CPinfoObject);
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] command = analysisDriveXmlToCommand.analysisCommands(driveRoot);
			return command;
		} catch (Exception e) {
			throw e;
		}
	}

	//将驱动对象按照协议解析成命令
	private void L2cpObjectToDriveAttribute(DriveAttribute driveAttribute,L2CPinfoObject l2cPinfoObject) {
		
		if("L2CP使能".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(l2cPinfoObject.getL2cpEnable()+"");
		}
		else if("BPDU协议".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(l2cPinfoObject.getBpduTreaty()+"");
		}
		else if("LLDP协议".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(l2cPinfoObject.getLldpTreaty()+"");
		}
		else if("LACP协议".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(l2cPinfoObject.getLacpTreaty()+"");
		}
		else if("IEEE802.1x协议".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(l2cPinfoObject.getIeeeTreaty()+"");
		}
		else if("MAC地址1".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(l2cPinfoObject.getMacAddress().split("-")[0]+"");
		}
		else if("MAC地址2".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(l2cPinfoObject.getMacAddress().split("-")[1]+"");
		}
		else if("MAC地址3".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(l2cPinfoObject.getMacAddress().split("-")[2]+"");
		}
		else if("MAC地址4".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(l2cPinfoObject.getMacAddress().split("-")[3]+"");
		}
		else if("MAC地址5".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(l2cPinfoObject.getMacAddress().split("-")[4]+"");
		}
		else if("MAC地址6".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(l2cPinfoObject.getMacAddress().split("-")[5]+"");
		}
		else if("指配协议号1".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(l2cPinfoObject.getTreatyNumber().split("-")[0]+"");
		}
		else if("指配协议号2".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(l2cPinfoObject.getTreatyNumber().split("-")[1]+"");
		}
	}
	
	
	
	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public L2CPinfoObject analysisCommandsToL2cpInfoObject(byte[] commands,String configXml) throws Exception {
		L2CPinfoObject l2cpObject = new L2CPinfoObject();
		// 起始长度
		byte[] a = new byte[26];
		System.arraycopy(commands, 0, a, 0, a.length);
		CoderUtils.print16String(commands);
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(a);
		DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);

		for (int i = 0, y = driveRoot_config.getDriveAttributeList().size(); i < y; i++) {
			DriveAttribute driveAttribute = driveRoot_config
					.getDriveAttributeList().get(i);
			// 赋值
			DriveAttributeTOL2CPinfoObject(l2cpObject, driveAttribute);
		}

		return l2cpObject;
	}

   private void DriveAttributeTOL2CPinfoObject(L2CPinfoObject l2cPinfoObject,DriveAttribute driveAttribute) {
		
		if("L2CP使能".equals(driveAttribute.getDescription())){
			l2cPinfoObject.setL2cpEnable(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("BPDU协议".equals(driveAttribute.getDescription())){
			l2cPinfoObject.setBpduTreaty(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("LLDP协议".equals(driveAttribute.getDescription())){
			l2cPinfoObject.setLldpTreaty(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("LACP协议".equals(driveAttribute.getDescription())){
			l2cPinfoObject.setLacpTreaty(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("IEEE802.1x协议".equals(driveAttribute.getDescription())){
			l2cPinfoObject.setIeeeTreaty(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("MAC地址1".equals(driveAttribute.getDescription())){
			l2cPinfoObject.setMacAddress(CoderUtils.synchTransformMac(driveAttribute.getValue()));		
		}
		else if("MAC地址2".equals(driveAttribute.getDescription())){
			l2cPinfoObject.setMacAddress(l2cPinfoObject.getMacAddress()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		else if("MAC地址3".equals(driveAttribute.getDescription())){
			l2cPinfoObject.setMacAddress(l2cPinfoObject.getMacAddress()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		else if("MAC地址4".equals(driveAttribute.getDescription())){
			l2cPinfoObject.setMacAddress(l2cPinfoObject.getMacAddress()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		else if("MAC地址5".equals(driveAttribute.getDescription())){
			l2cPinfoObject.setMacAddress(l2cPinfoObject.getMacAddress()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		else if("MAC地址6".equals(driveAttribute.getDescription())){
			l2cPinfoObject.setMacAddress(l2cPinfoObject.getMacAddress()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		else if("指配协议号1".equals(driveAttribute.getDescription())){
			l2cPinfoObject.setTreatyNumber(CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		else if("指配协议号2".equals(driveAttribute.getDescription())){
			l2cPinfoObject.setTreatyNumber(l2cPinfoObject.getTreatyNumber()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
	}
}
