package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.V35PortObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisV35PortTable extends AnalysisBase {
	private int dataCount = 50;// 每个数据块的长度
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度

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
	public byte[] analysisObjectToCommands(V35PortObject v35PortObject,String configXml) throws Exception {
		// 获取根目录
		DriveRoot globalDrivRoot = super.LoadDriveXml(configXml);
		
		for (int i = 0, j = globalDrivRoot.getDriveAttributeList().size(); i < j; i++) {
			DriveAttribute driveAttribute = globalDrivRoot.getDriveAttributeList().get(i);
			// 属性赋值
			objectTODriveAttribute(driveAttribute, v35PortObject);
		}
		// 将集合转换成命令
		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(globalDrivRoot);
		CoderUtils.print16String(dataCommand);
		return dataCommand;
	}

	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public V35PortObject analysisCommandsToObject(byte[] commands,
			String configXml) throws Exception {
		V35PortObject v35PortObject = new V35PortObject();
		// 起始长度
		int start = NEhead + controlPanelHead + dataBlockHead;// 131
		byte[] a = new byte[dataCount];
		System.arraycopy(commands, start, a, 0, a.length);
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(a);
		DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);

		for (int i = 0, y = driveRoot_config.getDriveAttributeList().size(); i < y; i++) {
			DriveAttribute driveAttribute = driveRoot_config
					.getDriveAttributeList().get(i);
			// 赋值
			DriveAttributeTOObject(v35PortObject, driveAttribute);
		}

		return v35PortObject;
	}

	
	/**
	 * 对象转命令
	 * @param driveAttribute
	 * @param v35PortObject
	 */
	private void objectTODriveAttribute(DriveAttribute driveAttribute,V35PortObject v35PortObject) {
		if ("V35接入第4路E1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(v35PortObject.getFourthLeg()+"");
		}else if("定时模式".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(v35PortObject.getTimeModel()+"");
		}else if("是否成帧设置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(v35PortObject.getFrame()+"");
		}else if("字节1".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(v35PortObject.getByte1()+"");
		}else if("字节2".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(v35PortObject.getByte2()+"");
		}else if("字节3".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(v35PortObject.getByte3()+"");
		}else if("字节4".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(v35PortObject.getByte4()+"");
		}
	}
	
	/**
	 * 命令转对象
	 * @param globalObject
	 * @param driveAttribute
	 */
	private void DriveAttributeTOObject(V35PortObject v35PortObject,DriveAttribute driveAttribute) {
		if ("V35接入第4路E1".equals(driveAttribute.getDescription())) {
			v35PortObject.setFourthLeg(Integer.parseInt(driveAttribute.getValue()));
		}else if("定时模式".equals(driveAttribute.getDescription())){
			v35PortObject.setTimeModel(Integer.parseInt(driveAttribute.getValue()));
		}else if("是否成帧设置".equals(driveAttribute.getDescription())){
			v35PortObject.setFrame(Integer.parseInt(driveAttribute.getValue()));
		}else if("字节1".equals(driveAttribute.getDescription())){
			v35PortObject.setByte1(Integer.parseInt(driveAttribute.getValue()));
		}else if("字节2".equals(driveAttribute.getDescription())){
			v35PortObject.setByte2(Integer.parseInt(driveAttribute.getValue()));
		}else if("字节3".equals(driveAttribute.getDescription())){
			v35PortObject.setByte3(Integer.parseInt(driveAttribute.getValue()));
		}else if("字节4".equals(driveAttribute.getDescription())){
			v35PortObject.setByte4(Integer.parseInt(driveAttribute.getValue()));
		}
	}
	
}
