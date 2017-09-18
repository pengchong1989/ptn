package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.LoadDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisBase {

	/**
	 * 根据XML格式生成驱动对象
	 * 
	 * @param configXml所在地址
	 * @return 驱动对象
	 * @throws Exception
	 */
	protected DriveRoot LoadDriveXml(String configXml) throws Exception {
		try {
			LoadDriveXml LoadDriveXml = new LoadDriveXml();
			DriveRoot driveRoot_config = LoadDriveXml.loadXmlToBean(configXml);
			return driveRoot_config;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return null;
	}
}
