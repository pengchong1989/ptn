package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.PortObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisPortPriTable extends AnalysisBase {
	/**
	 * 解析XML转换成命令
	 * 
	 * @param globalObject
	 *            对象
	 * @param configXml
	 *            文件目录地址
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisPortPriObjectToCommands(PortObject portObject,String configXml) throws Exception {
		// 获取根目录
		DriveRoot driveRoot = super.LoadDriveXml(configXml);
		
		for (int i = 0, j = driveRoot.getDriveAttributeList().size(); i < j; i++) {
			DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(i);
			// 属性赋值
			ethLoopObjectTODriveAttribute(driveAttribute, portObject);
		}
		// 将集合转换成命令
		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot);
		CoderUtils.print16String(dataCommand);
		
		return dataCommand;
	}
	
	private void ethLoopObjectTODriveAttribute(DriveAttribute driveAttribute,PortObject portObject) {
		
		if ("端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portObject.getUniObject().getPortNumber()+"");
		}
		if ("优先级0".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portObject.getPortSeniorConfig().getPri_Priority().split(" ")[0]);
		}
		if ("优先级1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portObject.getPortSeniorConfig().getPri_Priority().split(" ")[1]);
		}
		if ("优先级2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portObject.getPortSeniorConfig().getPri_Priority().split(" ")[2]);
		}
		if ("优先级3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portObject.getPortSeniorConfig().getPri_Priority().split(" ")[3]);
		}
		if ("优先级4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portObject.getPortSeniorConfig().getPri_Priority().split(" ")[4]);
		}
		if ("优先级5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portObject.getPortSeniorConfig().getPri_Priority().split(" ")[5]);
		}
		if ("优先级6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portObject.getPortSeniorConfig().getPri_Priority().split(" ")[6]);
		}
		if ("优先级7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portObject.getPortSeniorConfig().getPri_Priority().split(" ")[7]);
		}
		
	}
	
	/**
	 * 解析命令为对象
	 */
	public PortObject analysisCommandToObject(byte[] commands, String configXml) throws Exception {
		PortObject portObject = new PortObject();
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(commands);
		StringBuffer priValue = new StringBuffer();
		try {
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(i);
				//赋值
				this.driveAttributeToObject(portObject, driveAttribute,priValue);
			}
		} catch (Exception e) {
			throw e;
		}	
		return portObject;
	}

	private void driveAttributeToObject(PortObject portObject, DriveAttribute driveAttribute,StringBuffer priValue) {
		if("端口号".equals(driveAttribute.getDescription())){
			portObject.getUniObject().setPortNumber(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("优先级0".equals(driveAttribute.getDescription())) {
			priValue.append(driveAttribute.getValue()+" ");
		}
		if ("优先级1".equals(driveAttribute.getDescription())) {
			priValue.append(driveAttribute.getValue()+" ");
		}
		if ("优先级2".equals(driveAttribute.getDescription())) {
			priValue.append(driveAttribute.getValue()+" ");
		}
		if ("优先级3".equals(driveAttribute.getDescription())) {
			priValue.append(driveAttribute.getValue()+" ");
		}
		if ("优先级4".equals(driveAttribute.getDescription())) {
			priValue.append(driveAttribute.getValue()+" ");
		}
		if ("优先级5".equals(driveAttribute.getDescription())) {
			priValue.append(driveAttribute.getValue()+" ");
		}
		if ("优先级6".equals(driveAttribute.getDescription())) {
			priValue.append(driveAttribute.getValue()+" ");
		}
		if ("优先级7".equals(driveAttribute.getDescription())) {
			priValue.append(driveAttribute.getValue());
			portObject.getPortSeniorConfig().setPri_Priority(priValue.toString());
		}
	}
}
