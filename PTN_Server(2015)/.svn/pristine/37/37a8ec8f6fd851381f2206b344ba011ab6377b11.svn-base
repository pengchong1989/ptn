package com.nms.drive.analysis.xmltool.test;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.LoadDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.ui.manager.ExceptionManage;

public class TESTAnalysisCommandByDriveXml {

	public static void main(String[] args) {
		try {
			// byte[] realityByte = new byte[] { 2, 2, (byte) 243, 125, (byte)
			// 221, 66, 94, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00, 00,
			// 00, 00, (byte) 243, 125, (byte) 221, 66, 94, 00, 00, 00, 00, 00,
			// 00, 00, 00, 00, 00, 00, 00, 00, 00, 00 };

			String xmlpath = "src\\com\\ptn\\drive\\analysis\\xmltool\\file\\TUNNEL.xml";
			DriveRoot driveRoot_config = LoadDriveXml(xmlpath);
			AnalysisCommandByDriveXml AnalysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			AnalysisCommandByDriveXml.print(driveRoot_config.getDriveAttributeList());

		} catch (Exception e) {
			ExceptionManage.dispose(e,TESTAnalysisCommandByDriveXml.class);
		}
	}

	/**
	 * 根据XML格式生成驱动对象
	 * 
	 * @param configXml所在地址
	 * @return 驱动对象
	 * @throws Exception
	 */
	public static DriveRoot LoadDriveXml(String configXml) throws Exception {
		try {
			LoadDriveXml LoadDriveXml = new LoadDriveXml();
			DriveRoot driveRoot_config = LoadDriveXml.loadXmlToBean(configXml);
			return driveRoot_config;
		} catch (Exception e) {
			throw e;
		}
	}

}
