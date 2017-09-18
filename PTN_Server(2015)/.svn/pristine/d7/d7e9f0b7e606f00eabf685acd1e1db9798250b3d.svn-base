package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.UpgradeManageObject;

public class AnalysisUpgradeManageObject extends AnalysisBase{
	
	public List<UpgradeManageObject> analysisUpgradeManager(byte[] commands,String configXml) throws Exception{
		List<UpgradeManageObject> upgradeManageObjects = new ArrayList<UpgradeManageObject>();
		byte[] a = new byte[107];
		byte[] b = new byte[256];
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		AnalysisCommandByDriveXml analysisCommandByDriveXml_SUB = new AnalysisCommandByDriveXml();
		System.arraycopy(commands, 0, a, 0, a.length);		
		analysisCommandByDriveXml.setCommands(a);
		DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
		int controlPanelType = 0;
		int controlPanelGroupId = 0;
		int controlPanelAddress = 0;
		for (int i = 0; i <driveRoot_config.getDriveAttributeList().size(); i++) {
			
			DriveAttribute driveAttribute = new DriveAttribute();
			driveAttribute = driveRoot_config.getDriveAttributeList().get(i);
			if("盘类型".equals(driveAttribute.getDescription())){
				controlPanelType = Integer.parseInt(driveAttribute.getValue());
			}else if("盘组号".equals(driveAttribute.getDescription())){
				controlPanelGroupId = Integer.parseInt(driveAttribute.getValue());
			}else if("盘地址".equals(driveAttribute.getDescription())){
				controlPanelAddress = Integer.parseInt(driveAttribute.getValue());
			}else if("软件数".equals(driveAttribute.getDescription())){
				int count = Integer.parseInt(driveAttribute.getValue());
				String file = "com/nms/drive/analysis/xmltool/file/软件信息.xml";
				for (int j = 0; j < count; j++) {
					UpgradeManageObject upgradeManageObject = new UpgradeManageObject();
					upgradeManageObject.setControlPanelAddress(controlPanelAddress);
					upgradeManageObject.setControlPanelGroupId(controlPanelGroupId);
					upgradeManageObject.setControlPanelType(controlPanelType);
					System.arraycopy(commands, 107+j*256, b, 0, b.length);					
					analysisCommandByDriveXml_SUB.setCommands(b);
					DriveRoot driveRoot_config_sub =analysisCommandByDriveXml_SUB.analysisDriveAttrbute(file);
					StringBuffer stringBuffer = new StringBuffer();
					for (int k = 0; k < driveRoot_config_sub.getDriveAttributeList().size(); k++) {
						DriveAttribute driveAttribute_sub = new DriveAttribute();
						driveAttribute_sub = driveRoot_config_sub.getDriveAttributeList().get(k);
						if("软件型号类型".equals(driveAttribute_sub.getDescription())){
							upgradeManageObject.setSoftwareType(Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("CRC校验码".equals(driveAttribute_sub.getDescription())){
							upgradeManageObject.setCrc(Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间1".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间2".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间3".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间4".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间5".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间6".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间7".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间8".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间9".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间10".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间11".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间12".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间13".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间14".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间15".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
						}else if("时间16".equals(driveAttribute_sub.getDescription())){
							stringBuffer.append((char)Integer.parseInt(driveAttribute_sub.getValue()));
							upgradeManageObject.setTime(stringBuffer.toString());
						}
					}
					upgradeManageObjects.add(upgradeManageObject);
				}
			}
		}
		return upgradeManageObjects;
	}
}
