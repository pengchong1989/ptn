package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.SmartFanObject;

public class AnalysisSmartFanTable extends AnalysisBase {
	public byte[] analysisObjectToCommands(List<SmartFanObject> fanObjectList, String configXml) throws Exception {
		try {
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			driveRoot.getDriveAttributeList().get(0).setValue(fanObjectList.size()+"");
			String file = driveRoot.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			driveRoot.getDriveAttributeList().get(0).getListRootList().clear();
			for (int i = 0; i < fanObjectList.size(); i++) {
				DriveRoot driveRoot_sub = super.LoadDriveXml(file);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot_sub.getDriveAttributeList());
				for (int j = 0; j < listRoot.getDriveAttributeList().size(); j++) {
					this.setValueToDriveAttribute(fanObjectList.get(i),listRoot.getDriveAttributeList().get(j));
				}
				driveRoot.getDriveAttributeList().get(0).getListRootList().add(listRoot);
			}	
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot);
			return dataCommand;
		} catch (Exception e) {
			throw e;
		}
	}

	private void setValueToDriveAttribute(SmartFanObject fan, DriveAttribute drive) {
		if(drive.getDescription().equals("条目Id")){
			drive.setValue(fan.getId()+"");
		}
		if(drive.getDescription().equals("风扇模式")){
			drive.setValue(fan.getWorkType()+"");
		}
		if(drive.getDescription().equals("风扇档位")){
			drive.setValue(fan.getGearLevel()+"");
		}
	}
	
	/**
	 * 解析命令为对象
	 */
	public List<SmartFanObject> analysisCommandToObject(byte[] commands, String configXml) throws Exception {
		List<SmartFanObject> fanObjList = new ArrayList<SmartFanObject>();
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
		// 起始长度
		int start = 1;
		// 条目数
		int count = (commands.length - start-11) / 5;
		for (int j = 0; j < count; j++) {
			byte[] a = new byte[5];
			System.arraycopy(commands, start + j * 5, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			try {
				DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(file);
				SmartFanObject obj = new SmartFanObject();
				for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
					DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(i);
					//赋值
					this.driveAttributeToObject(obj, driveAttribute);
				}
				fanObjList.add(obj);
			} catch (Exception e) {
				throw e;
			}
		}
		return fanObjList;
	}

	private void driveAttributeToObject(SmartFanObject obj, DriveAttribute driveAttribute) {
		if("风扇模式".equals(driveAttribute.getDescription())){
			obj.setWorkType(Integer.parseInt(driveAttribute.getValue()));
		}
		if("风扇档位".equals(driveAttribute.getDescription())){
			obj.setGearLevel(Integer.parseInt(driveAttribute.getValue()));
		}
	}
}
