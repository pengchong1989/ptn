package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.E1LegObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisSiteConnectTable extends AnalysisBase {

	/**
	 * 根据命令生成E1Obj对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	public List<NEObject> analysisCommandsToSiteObject(byte[] commands, String configXml) {

		int pointer = 0;// 当前commands下标
		pointer = 0;

		byte[] neaddress = new byte[3];// e1Object对象的命令
		byte[] siteConnect = new byte[10];// e1LegObject对象的命令

		System.arraycopy(commands, pointer, neaddress, 0, neaddress.length);
		pointer += neaddress.length;// 移动下标

		int count = CoderUtils.bytesToInt(commands[2]);
		List<NEObject> neObjects = new ArrayList<NEObject>();
		NEObject neObject = null;
		for (int i = 0; i < count; i++) {
			System.arraycopy(commands, pointer, siteConnect, 0, siteConnect.length);
			pointer += siteConnect.length;
			neObject = getNeObj(siteConnect, configXml);// 获取e1LegObj对象
			neObjects.add(neObject);
		}

		return neObjects;

	}


	/**
	 * 根据命令获取E1Object对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private NEObject getNeObj(byte[] commands, String configXml) {
		NEObject neObject = new NEObject();

		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);

			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("NE地址".equals(driveAttribute.getDescription())) {
					neObject.setNeAddress(Integer.parseInt(driveAttribute.getValue()));
				}if ("网元状态".equals(driveAttribute.getDescription())) {
					neObject.setNeStatus(Integer.parseInt(driveAttribute.getValue()));
				}

			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return neObject;

	}

	/**
	 * 根据命令获取E1LegObj对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private E1LegObject getE1LegObj(byte[] commands, String configXml) {
		E1LegObject e1LegObj = new E1LegObject();

		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);

			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("2M支路屏蔽".equals(driveAttribute.getDescription())) {
					e1LegObj.setLegShield(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("2M支路使能".equals(driveAttribute.getDescription())) {
					e1LegObj.setLegEnable(Integer.parseInt(driveAttribute.getValue()));

				}
				if ("2M支路ID".equals(driveAttribute.getDescription())) {
					e1LegObj.setLegId(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("缓存时间控制使能".equals(driveAttribute.getDescription())) {
					e1LegObj.setPrestoreTimeEnable(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("缓存时间".equals(driveAttribute.getDescription())) {
					e1LegObj.setPrestoreTime(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("封装帧个数".equals(driveAttribute.getDescription())) {
					e1LegObj.setPinCount(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("pw标签".equals(driveAttribute.getDescription())) {
					e1LegObj.setPwLable(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("备用".equals(driveAttribute.getDescription())) {

				}

			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return e1LegObj;

	}
}
