package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.PmValueLimiteObject;
import com.nms.drive.service.impl.CoderUtils;

/**
 * 
 * @author 彭冲
 * 性能门限解析
 *
 */
public class AnalysisPmLimiteTable extends AnalysisBase {
	private int clauses = 51;// 条目数长度

	/**
	 * 根据对象生成字节数组
	 * 
	 * @param tunnelObject对象
	 * @param configXml配置XML
	 * @return 命令
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(PmValueLimiteObject pmValueLimiteObject , String configXml) throws Exception {// 获取根目录
		DriveRoot globalDrivRoot = super.LoadDriveXml(configXml);
		for (int i = 0, j = globalDrivRoot.getDriveAttributeList().size(); i < j; i++) {
			DriveAttribute driveAttribute = globalDrivRoot
					.getDriveAttributeList().get(i);
			// 属性赋值
			objectTODriveAttribute(pmValueLimiteObject,driveAttribute);
		}
		// 将集合转换成命令
		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] dataCommand = analysisDriveXmlToCommand
				.analysisCommands(globalDrivRoot);
		CoderUtils.print16String(dataCommand);
		return dataCommand;
		}

	public void objectTODriveAttribute(PmValueLimiteObject pmValueLimiteObject,DriveAttribute driveAttribute){
		//CRC校验错门限
		if("CRC校验错门限".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pmValueLimiteObject.getCrcError()+"");
		}
		
		//丢包个数门限
		if("丢包个数门限".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pmValueLimiteObject.getLossNum()+"");
		}
		//收坏包数门限
		if("收坏包数门限".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pmValueLimiteObject.getReceiveBadWrap()+"");
		}
		//对齐错门限
		if("对齐错门限".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pmValueLimiteObject.getAlign()+"");
		}
		// TMP通道信号劣化门限
		if (driveAttribute.getDescription().equals("TMP通道信号劣化门限")) {
			driveAttribute.setValue(pmValueLimiteObject.getTmpWorsen() + "");
		}

		// TMP通道信号失效门限
		if (driveAttribute.getDescription().equals("TMP通道信号失效门限")) {
			driveAttribute.setValue(pmValueLimiteObject.getTmpLose() + "");
		}

		// TMC通道信号劣化门限
		if (driveAttribute.getDescription().equals("TMC通道信号劣化门限")) {
			driveAttribute.setValue(pmValueLimiteObject.getTmcWorsen() + "");
		}

		// TMC通道信号失效门限
		if (driveAttribute.getDescription().equals("TMC通道信号失效门限")) {
			driveAttribute.setValue(pmValueLimiteObject.getTmcLose() + "");
		}

		// TMS通道信号劣化门限
		if (driveAttribute.getDescription().equals("TMS通道信号劣化门限")) {
			driveAttribute.setValue(pmValueLimiteObject.getTmsWorsen() + "");
		}

		// TMS通道信号失效门限
		if (driveAttribute.getDescription().equals("TMS通道信号失效门限")) {
			driveAttribute.setValue(pmValueLimiteObject.getTmsLose() + "");
		}

		// 设备温度过高门限
		if (driveAttribute.getDescription().equals("设备温度过高门限")) {
			driveAttribute.setValue(pmValueLimiteObject.getHighTemp() + "");
		}

		//设备温度过低门限
		if (driveAttribute.getDescription().equals("设备温度过低门限")) {
			driveAttribute.setValue(pmValueLimiteObject.getLowTemp() + "");
		}
	}
	
	public PmValueLimiteObject analysisCommandsToGlobalObject(byte[] commands,
			String configXml) throws Exception {
		PmValueLimiteObject pmLimiteObject = new PmValueLimiteObject();
		// 起始长度
		byte[] a = new byte[clauses];
		System.arraycopy(commands, 0, a, 0, a.length);
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(a);
		DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);

		for (int i = 0, y = driveRoot_config.getDriveAttributeList().size(); i < y; i++) {
			DriveAttribute driveAttribute = driveRoot_config
					.getDriveAttributeList().get(i);
			// 赋值
			DriveAttributeTOPmObject(pmLimiteObject, driveAttribute);
		}

		return pmLimiteObject;
	}
	
	private void DriveAttributeTOPmObject(PmValueLimiteObject pmLimiteObject, DriveAttribute driveAttribute)
	{
		// CRC校验错门限
		if ("CRC校验错门限".equals(driveAttribute.getDescription())) {
			pmLimiteObject.setCrcError(Integer.parseInt((driveAttribute.getValue())));
		}
		
		// 丢包个数门限
		if ("丢包个数门限".equals(driveAttribute.getDescription())) {
			pmLimiteObject.setLossNum(Integer.parseInt((driveAttribute.getValue())));
		}
		
		// 收坏包数门限
		if ("收坏包数门限".equals(driveAttribute.getDescription())) {
			pmLimiteObject.setReceiveBadWrap(Integer.parseInt((driveAttribute.getValue())));
		}
		
		// 对齐错门限
		if ("对齐错门限".equals(driveAttribute.getDescription())) {
			pmLimiteObject.setAlign(Integer.parseInt((driveAttribute.getValue())));
		}
		
		// TMP通道信号劣化门限
		if ("TMP通道信号劣化门限".equals(driveAttribute.getDescription())) {
			pmLimiteObject.setTmpWorsen(Integer.parseInt((driveAttribute.getValue())));
		}
		
		// TMP通道信号失效门限
		if ("TMP通道信号失效门限".equals(driveAttribute.getDescription())) {
			pmLimiteObject.setTmpLose(Integer.parseInt((driveAttribute.getValue())));
		}
		
		// TMC通道信号劣化门限
		if ("TMC通道信号劣化门限".equals(driveAttribute.getDescription())) {
			pmLimiteObject.setTmcWorsen(Integer.parseInt((driveAttribute.getValue())));
		}
		
		// TMC通道信号失效门限
		if ("TMC通道信号失效门限".equals(driveAttribute.getDescription())) {
			pmLimiteObject.setTmcLose(Integer.parseInt((driveAttribute.getValue())));
		}
		
		// TMS通道信号劣化门限
		if ("TMS通道信号劣化门限".equals(driveAttribute.getDescription())) {
			pmLimiteObject.setTmsWorsen(Integer.parseInt((driveAttribute.getValue())));
		}
		
		// TMS通道信号失效门限
		if ("TMS通道信号失效门限".equals(driveAttribute.getDescription())) {
			pmLimiteObject.setTmsLose(Integer.parseInt((driveAttribute.getValue())));
		}
		
		// 设备温度过高门限
		if ("设备温度过高门限".equals(driveAttribute.getDescription())) {
			pmLimiteObject.setHighTemp(Integer.parseInt((driveAttribute.getValue())));
		}
		
		// 设备温度过低门限
		if ("设备温度过低门限".equals(driveAttribute.getDescription())) {
			pmLimiteObject.setLowTemp(Integer.parseInt((driveAttribute.getValue())));
		}
	}
}
