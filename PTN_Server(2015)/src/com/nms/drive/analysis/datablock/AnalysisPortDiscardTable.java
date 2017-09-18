package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.EthServiceInfoObject;
import com.nms.drive.service.bean.EthServiceObject;

public class AnalysisPortDiscardTable extends AnalysisBase {

	public byte[] analysisObjectToCommands(EthServiceObject ethServiceObject, String configXml) throws Exception {
		DriveRoot driveRoot_sub = super.LoadDriveXml(configXml);
		try {
			ListRoot listRoot = new ListRoot();
			listRoot.setDriveAttributeList(driveRoot_sub.getDriveAttributeList());
			for (int j = 0; j < listRoot.getDriveAttributeList().size(); j++) {
				// 属性赋值
				this.setValueToDriveAttribute(ethServiceObject.getEthServiceInfoObjectList().get(0), listRoot.getDriveAttributeList().get(j));
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_sub);
			return dataCommand;
		} catch (Exception e) {
			throw e;
		}
	}

	public EthServiceObject analysisCommandToObject(byte[] dataCommand, String configXml) throws Exception {
		EthServiceObject ethServiceObject = new EthServiceObject();
		try {
			List<EthServiceInfoObject> ethServiceObjList = new ArrayList<EthServiceInfoObject>();
			byte[] a = new byte[21];
			System.arraycopy(dataCommand, 0, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			try {
				DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
				EthServiceInfoObject obj = new EthServiceInfoObject();
				for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
					DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(i);
					// 赋值
					this.driveAttributeToObject(obj, driveAttribute);
				}
				ethServiceObjList.add(obj);
			} catch (Exception e) {
				throw e;
			}
			ethServiceObject.setEthServiceInfoObjectList(ethServiceObjList);
			return ethServiceObject;
		} catch (Exception e) {
			throw e;
		}
	}

	private void driveAttributeToObject(EthServiceInfoObject obj, DriveAttribute driveAttribute) {
		if ("端口1".equals(driveAttribute.getDescription())) {
			obj.setPortLine1Object(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("端口2".equals(driveAttribute.getDescription())) {
			obj.setPortLine2Object(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("端口3".equals(driveAttribute.getDescription())) {
			obj.setPortLine3Object(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("端口4".equals(driveAttribute.getDescription())) {
			obj.setPortLine4Object(Integer.parseInt(driveAttribute.getValue()));
		}
	}

	private void setValueToDriveAttribute(EthServiceInfoObject ethServiceInfoObject, DriveAttribute driveAttribute) {

		if ("端口1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethServiceInfoObject.getPortLine1Object() + "");
		}
		if ("端口2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethServiceInfoObject.getPortLine2Object() + "");
		}
		if ("端口3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethServiceInfoObject.getPortLine3Object() + "");
		}
		if ("端口4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethServiceInfoObject.getPortLine4Object() + "");
		}
	}

}
