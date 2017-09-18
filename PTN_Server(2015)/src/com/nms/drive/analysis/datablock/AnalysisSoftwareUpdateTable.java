package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.SoftwareUpdate;
import com.nms.drive.service.impl.CoderUtils;


/**
 * 
 * @author 彭冲
 * 软件升级解析
 *
 */
public class AnalysisSoftwareUpdateTable extends AnalysisBase {


	/**
	 * 根据对象生成字节数组
	 * 
	 * @param tunnelObject对象
	 * @param configXml配置XML
	 * @return 命令
	 * @throws Exception
	 */
	public byte[] AnalysisObjectToCommand(SoftwareUpdate softwareUpdate, String configXml) throws Exception {
		
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		for (int i = 0; i < driveRoot_config.getDriveAttributeList().size(); i++) {
			DriveAttribute driveAttribute = driveRoot_config.getDriveAttributeList().get(i);
			objectToDriveAttribute(softwareUpdate,driveAttribute);
		}
		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
		byte[] data = new byte[dataCommand.length+softwareUpdate.getDatabag().length];
		data = CoderUtils.arraycopy(dataCommand, softwareUpdate.getDatabag());
		System.out.println("升级长度"+data.length);
		return data;
		
	}

	private void objectToDriveAttribute(SoftwareUpdate softwareUpdate, DriveAttribute driveAttribute) {
		
		//NE地址
		if("NE地址".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(softwareUpdate.getNeObject().getNeAddress()+"");
		}
		//盘类型
		if("盘类型".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(softwareUpdate.getNeObject().getControlPanelType()+"");
		}
		//盘组号
		if("盘组号".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(softwareUpdate.getNeObject().getControlPanelGroupId()+"");
		}
		//盘地址
		if("盘地址".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(softwareUpdate.getNeObject().getControlPanelAddress()+"");
		}
		//偏移量
		if("偏移量".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(softwareUpdate.getExcursion()+"");
		}
		//下载数据包长度
		if("下载数据包长度".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(softwareUpdate.getDataLong()+"");
			
		}
		//文件总长
		if("文件总长".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(softwareUpdate.getFileLong()+"");
		}
	}

}
