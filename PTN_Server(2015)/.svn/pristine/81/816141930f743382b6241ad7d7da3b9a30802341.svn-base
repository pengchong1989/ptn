package com.nms.drive.analysis.datablock;

import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.ResponsePan;

/**
 * 回答单盘信息配置解析
 * @author Administrator
 *
 */
public class AnalysisResponsePanTable extends AnalysisBase {
	/**
	 * 根据对象生成字节数组
	 * @param responsePan
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(ResponsePan responsePan,String configXml)throws Exception{
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		List<DriveAttribute> list = driveRoot_config.getDriveAttributeList();
		//赋值
		objToDriveAttribute(list,responsePan);
		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] datacommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
		return datacommand;
	}

	private void objToDriveAttribute(List<DriveAttribute> list,ResponsePan responsePan) {
		for (int i = 0; i < list.size(); i++) {
			DriveAttribute driveAttribute = list.get(i);
			String[] s1 = responsePan.getNEAddr().split(",");
			if("NE地址1".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(s1[0]);
			}
			if("NE地址2".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(s1[1]);
			}
			String[] s2 = responsePan.getPanType().split(",");
			if("盘类型1".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(s2[0]);
			}
			if("盘类型2".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(s2[1]);
			}
			if("盘类型3".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(s2[2]);
			}
			if("盘类型4".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(s2[3]);
			}
			String[] s3 = responsePan.getPanAddr().split(",");
			if("盘地址1".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(s3[0]);
			}
			if("盘地址2".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(s3[1]);
			}
		}
	}
}
