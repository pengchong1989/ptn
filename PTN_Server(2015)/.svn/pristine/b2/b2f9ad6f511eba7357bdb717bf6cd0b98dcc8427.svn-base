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
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisEthServciceTable extends AnalysisBase {
	
	
	public byte[] analysisAclToCommands(EthServiceObject ethServiceObject, String configXml) throws Exception {
		try {
			
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			String file = driveRoot.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			driveRoot.getDriveAttributeList().get(0).setValue(ethServiceObject.getEthServiceInfoObjectList().size()+"");
			driveRoot.getDriveAttributeList().get(0).getListRootList().clear();// 清除本身自带的一个ListRoot
			
			for (int i = 0; i < ethServiceObject.getEthServiceInfoObjectList().size(); i++) {
				DriveRoot driveRoot_sub = super.LoadDriveXml(file);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot_sub.getDriveAttributeList());
				for (int j = 0; j < listRoot.getDriveAttributeList().size(); j++) {
					// 属性赋值
					this.setValueToDriveAttribute(ethServiceObject.getEthServiceInfoObjectList().get(i),listRoot.getDriveAttributeList().get(j),i+1);
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
	
	public List<EthServiceInfoObject> analysisCommandToObject(byte[] dataCommand,String configXml) throws Exception {
		try {
			List<EthServiceInfoObject> ethServiceObjList = new ArrayList<EthServiceInfoObject>();
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			// 起始长度
			int start = 2;
			// 条目数
			int count = (dataCommand.length - start-11) / 10;
			for (int j = 0; j < count; j++) {
				byte[] a = new byte[10];
				System.arraycopy(dataCommand, start + j * 10, a, 0, a.length);
				AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
				analysisCommandByDriveXml.setCommands(a);
				try {
					DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(file);
					EthServiceInfoObject obj = new EthServiceInfoObject();
					for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
						DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(i);
						//赋值
						this.driveAttributeToObject(obj, driveAttribute);
					}
					ethServiceObjList.add(obj);
				} catch (Exception e) {
					throw e;
				}
			}
			return ethServiceObjList;
		} catch (Exception e) {
			throw e;
		}
	}
	
	

	private void driveAttributeToObject(EthServiceInfoObject obj,DriveAttribute driveAttribute) {
		if ("vlanID".equals(driveAttribute.getDescription())) {
			obj.setVlanIdObject(Integer.parseInt(driveAttribute.getValue()));
		}
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
		if ("条目ID".equals(driveAttribute.getDescription())) {
			obj.setNum(Integer.parseInt(driveAttribute.getValue()));
		}
	}

	private void setValueToDriveAttribute(EthServiceInfoObject ethServiceInfoObject,DriveAttribute driveAttribute, int length) {
		
		if ("条目ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(length+ "");
		}
		if ("vlanID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethServiceInfoObject.getVlanIdObject()+"");
		}
		if ("端口1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethServiceInfoObject.getPortLine1Object()+"");
		}
		if ("端口2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethServiceInfoObject.getPortLine2Object()+"");
		}
		if ("端口3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethServiceInfoObject.getPortLine3Object()+"");
		}
		if ("端口4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethServiceInfoObject.getPortLine4Object()+"");
		}
	}
}
