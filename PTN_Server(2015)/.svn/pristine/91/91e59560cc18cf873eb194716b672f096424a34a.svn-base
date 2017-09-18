package com.nms.drive.analysis.datablock;

import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.MacLearningObject;

/**
 * MAC学习数目限制管理解析
 * @author Administrator
 *
 */
public class AnalysisMacLearningTable extends AnalysisBase{
	/**
	 * 根据对象生成字节数组
	 */
	public byte[] analysisObjectToCommand(List<MacLearningObject> macList, String configXml) throws Exception {

		try {
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			driveRoot.getDriveAttributeList().get(0).setValue(macList.size()+"");
			String file = driveRoot.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			driveRoot.getDriveAttributeList().get(0).getListRootList().clear();
			for (int i = 0; i < macList.size(); i++) {
				DriveRoot driveRoot_sub = super.LoadDriveXml(file);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot_sub.getDriveAttributeList());
				for (int j = 0; j < listRoot.getDriveAttributeList().size(); j++) {
					setValueToDriveAttribute(macList.get(i),listRoot.getDriveAttributeList().get(j));
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

	private void setValueToDriveAttribute(MacLearningObject mac,DriveAttribute drive) {
		if(drive.getDescription().equals("端口号")){
			drive.setValue(mac.getPortId()+"");
		}
		if(drive.getDescription().equals("MAC学习限制模式")){
			drive.setValue(mac.getMacModel()+"");
		}
//		if(drive.getDescription().equals("Vlan值")){
//			drive.setValue(mac.getVlanId()+"");
//		}
		if(drive.getDescription().equals("MAC地址学习限制数目")){
			drive.setValue(mac.getMacCount()+"");
		}
	}

}