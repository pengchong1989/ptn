package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.PHBToEXPObject;
import com.nms.drive.service.impl.CoderUtils;

/**
 * 解析 PHB到TMCTMP EXP映射表(09H)
 * @author 罗磊
 * 
 */
public class AnalysisPHBToEXPTable extends AnalysisBase {


	/**
	 * PHBToEXP 对象 生成 命令
	 * @param phbToEXPList
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(List<PHBToEXPObject> phbToEXPList, String configXml) throws Exception {
		try {
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			String file = driveRoot.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			// 清除本身自带的一个ListRoot
			driveRoot.getDriveAttributeList().get(0).getListRootList().clear();
			// 条目数
			driveRoot.getDriveAttributeList().get(0).setValue(phbToEXPList.size() + "");
			// 按条目数循环
			for (int j = 0; j < phbToEXPList.size(); j++) {
				DriveRoot driveRoot1 = super.LoadDriveXml(file);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot1.getDriveAttributeList());
				// 将对象赋值到driveRoot对象中
				for (int i = 0; i < driveRoot1.getDriveAttributeList().size(); i++) {
					DriveAttribute driveAttribute = driveRoot1.getDriveAttributeList().get(i);
					// 赋值
					phbObjectToDriveAttribute(phbToEXPList.get(j), driveAttribute);
				}
				driveRoot.getDriveAttributeList().get(0).getListRootList().add(listRoot);
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot);
			CoderUtils.print16String(dataCommand);
			return dataCommand;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 命令 生成 PHBToEXP 对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<PHBToEXPObject> analysisCommandToObject(byte[] commands, String configXml) throws Exception {
		List<PHBToEXPObject> phbList = new ArrayList<PHBToEXPObject>();
		DriveRoot driveRoot = super.LoadDriveXml(configXml);
		String file = driveRoot.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
		int dataCount = 17;// 字节总数
		int count = (commands.length  - 1) / dataCount;// （命令长度-条目长度）/ 对象字节总数
		try {
			for (int j = 0; j < count; j++) {
				byte[] a = new byte[dataCount];
				System.arraycopy(commands, 1 + j * dataCount, a, 0, a.length);
				AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
				analysisCommandByDriveXml.setCommands(a);

				DriveRoot drive_Root = analysisCommandByDriveXml.analysisDriveAttrbute(file);
				PHBToEXPObject phbToEXPObject = new PHBToEXPObject();
				for (int i = 0; i < drive_Root.getDriveAttributeList().size(); i++) {
					DriveAttribute driveAttribute = drive_Root.getDriveAttributeList().get(i);
					// 赋值
					driveAttributeToPHBObject(phbToEXPObject, driveAttribute);
				}
				phbList.add(phbToEXPObject);
			}
		} catch (Exception e) {
			throw e;
		}
		return phbList;
	}

	/**
	 * 赋值给driveAttribute
	 * 
	 * @param phbObject
	 * @param driverAttribute
	 */
	public void phbObjectToDriveAttribute(PHBToEXPObject phbObject, DriveAttribute driverAttribute) {
		if ("PHB2EXP_ID".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getPhb2EXPID() + "");
		}
		if ("(BE)VC EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getBeVCexp() + "");
		}
		if ("(BE)VP EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getBeVPexp() + "");
		}
		if ("(AF1)VC EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getAf1VC() + "");
		}
		if ("(AF1)VP EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getAf1VP() + "");
		}
		if ("(AF2)VC EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getAf2VC() + "");
		}
		if ("(AF2)VP EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getAf2VP() + "");
		}
		if ("(AF3)VC EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getAf3VC() + "");
		}
		if ("(AF3)VP EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getAf3VP() + "");
		}
		if ("(AF4)VC EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getAf4VC() + "");
		}
		if ("(AF4)VP EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getAf4VP() + "");
		}
		if ("(EF)VC EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getEfVC() + "");
		}
		if ("(EF)VP EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getEfVP() + "");
		}
		if ("(CS6)VC EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getCs6VC() + "");
		}
		if ("(CS6)VP EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getCs6VP() + "");
		}
		if ("(CS7)VC EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getCs7VC() + "");
		}
		if ("(CS7)VP EXP".equals(driverAttribute.getDescription())) {
			driverAttribute.setValue(phbObject.getCs7VP() + "");
		}
	}

	/**
	 * 赋值给对象
	 * 
	 * @param phbToEXPObject
	 * @param driverAttribute
	 */
	public void driveAttributeToPHBObject(PHBToEXPObject phbToEXPObject, DriveAttribute driverAttribute) {
		if ("PHB2EXP_ID".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setPhb2EXPID(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(BE)VC EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setBeVCexp(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(BE)VP EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setBeVPexp(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(AF1)VC EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setAf1VC(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(AF1)VP EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setAf1VP(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(AF2)VC EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setAf2VC(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(AF2)VP EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setAf2VP(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(AF3)VC EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setAf3VC(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(AF3)VP EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setAf3VP(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(AF4)VC EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setAf4VC(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(AF4)VP EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setAf4VP(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(EF)VC EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setEfVC(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(EF)VP EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setEfVP(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(CS6)VC EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setCs6VC(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(CS6)VP EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setCs6VP(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(CS7)VC EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setCs7VC(Integer.parseInt(driverAttribute.getValue()));
		}
		if ("(CS7)VP EXP".equals(driverAttribute.getDescription())) {
			phbToEXPObject.setCs7VP(Integer.parseInt(driverAttribute.getValue()));
		}
	}
}
