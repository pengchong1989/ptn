package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.OamLinkStatusInfoObject;

/**
 * 将对象的命令码转换为相应的对象
 * @author zk
 */
public class AnalysisOamLinkStatusTable extends AnalysisBase{
	
	/**
	 * 根据字节数组生成对象
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public OamLinkStatusInfoObject analysisCommandToObject(byte[] commands,String configXml) throws Exception {
		AnalysisCommandByDriveXml analysisCommandByDriveXml = null;
		DriveRoot driveRoot_config =null;
		OamLinkStatusInfoObject oamLinkStatusInfoObject = null;
		try {
			oamLinkStatusInfoObject = new OamLinkStatusInfoObject();
			// 起始长度
			byte[] a = new byte[commands.length-6];
			System.arraycopy(commands, 6, a, 0, a.length);
			analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			
			for (int i = 0, y = driveRoot_config.getDriveAttributeList().size(); i < y; i++) {
				DriveAttribute driveAttribute = driveRoot_config.getDriveAttributeList().get(i);
				// 赋值
				DriveAttributeTOInsertLinkOamObject(oamLinkStatusInfoObject, driveAttribute);
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			 analysisCommandByDriveXml = null;
			 driveRoot_config =null;
		}
		return oamLinkStatusInfoObject;
	}

	/**
	 * function:将"通过配置文件和命名码 解析出来的设备数据 "转成 "驱动对象"
	 * @param oamLinkStatusInfoObject 驱动对象
	 * @param driveAttribute 通过配置文件和命名码 解析出来的设备数据
	 */
	private void DriveAttributeTOInsertLinkOamObject(OamLinkStatusInfoObject oamLinkStatusInfoObject,DriveAttribute driveAttribute) {
		
		 // 本端OAM端口
	    if ("本端OAM端口".equals(driveAttribute.getDescription())) {
	    	oamLinkStatusInfoObject.setPortNumber(Integer.parseInt(driveAttribute.getValue()));
			}
	    // 本端OAM协商态
	    if ("本端OAM协商态".equals(driveAttribute.getDescription())) {
	    	oamLinkStatusInfoObject.setOamConsult(Integer.parseInt(driveAttribute.getValue()));
			}
	    // 本端OAM链路状态
	    if ("本端OAM链路状态".equals(driveAttribute.getDescription())) {
	    	oamLinkStatusInfoObject.setOamLinkStatus(Integer.parseInt(driveAttribute.getValue()));
			}
	    // 本端OAM端口fault状态
	    if ("本端OAM端口fault状态".equals(driveAttribute.getDescription())) {
	    	oamLinkStatusInfoObject.setOamPortFaultStatus(Integer.parseInt(driveAttribute.getValue()));
			}
	    // 本端环回命令超时失败
	    if ("本端环回命令超时失败".equals(driveAttribute.getDescription())) {
	    	oamLinkStatusInfoObject.setLoopFailfalse(Integer.parseInt(driveAttribute.getValue()));
			}
	    // 本端环回解析状态
	    if ("本端环回解析状态".equals(driveAttribute.getDescription())) {
	    	oamLinkStatusInfoObject.setLoopAnalysisStatus(Integer.parseInt(driveAttribute.getValue()));
			}
	    // 远端环回解析状态
	    if ("远端环回解析状态".equals(driveAttribute.getDescription())) {
	    	oamLinkStatusInfoObject.setRemoteLoopAnalysisStatus(Integer.parseInt(driveAttribute.getValue()));
		}
	}

}
