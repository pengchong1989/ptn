package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisCircleSiteConnectTable extends AnalysisBase {

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

		byte[] neaddress = new byte[3];// 
		byte[] siteConnect = new byte[3];// 网元对象的命令

		System.arraycopy(commands, pointer, neaddress, 0, neaddress.length);
		pointer += neaddress.length;// 移动下标

		int count = CoderUtils.bytesToInt(commands[2]);
		List<NEObject> neObjects = new ArrayList<NEObject>();
		for (int i = 0; i < count; i++) {
			NEObject neObject = new NEObject();
			byte[] neAddress = new byte[4];
			neAddress[0] = 0;
			neAddress[1] = 0;
			neAddress[2] = commands[3+i*3];
			neAddress[3] = commands[4+i*3];
			neObject.setNeAddress(CoderUtils.bytesToInt(neAddress));
			neObject.setNeStatus(commands[5+i*3]);
//			System.arraycopy(commands, pointer, siteConnect, 0, siteConnect.length);
//			pointer += siteConnect.length;
//			neObject = getNeObj(siteConnect, configXml);// 网元对象的命令
			neObjects.add(neObject);
		}

		return neObjects;

	}


	/**
	 * 根据命令获取网元对象
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
}
