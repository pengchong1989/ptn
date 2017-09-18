package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.TMCTunnelProtectObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisTMCTunnelProtectTable extends AnalysisBase {

	/**
	 * 解析TMCTunnelProtectObject对象成命令
	 * 
	 * @param tmcTunnelProtectObjList
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisTMCTunnelProtectObjToCommand(List<TMCTunnelProtectObject> tmcTunnelProtectObjList, String configXml) throws Exception {
		try {
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			String tmcTunnelObjPath = driveRoot.getDriveAttributeList().get(0).getListRootList().get(0).getFile();

			// 条目数赋值
			driveRoot.getDriveAttributeList().get(0).setValue(tmcTunnelProtectObjList.size() + "");
			// 清除自带的ListRoot
			driveRoot.getDriveAttributeList().get(0).getListRootList().clear();

			DriveRoot tmcTunnelProtectDriveRoot = null;
			DriveAttribute driveAttribute = null;

			TMCTunnelProtectObject tmcTunnelProtectObject = null;
			for (int i = 0; i < tmcTunnelProtectObjList.size(); i++) {// 遍历List<TMCTunnelProtectObject>集合
				tmcTunnelProtectDriveRoot = super.LoadDriveXml(tmcTunnelObjPath);
				tmcTunnelProtectObject = tmcTunnelProtectObjList.get(i);

				for (int j = 0; j < tmcTunnelProtectDriveRoot.getDriveAttributeList().size(); j++) {
					driveAttribute = tmcTunnelProtectDriveRoot.getDriveAttributeList().get(j);
					assignTMCDriveAttribute(driveAttribute, tmcTunnelProtectObject);// 赋值
				}
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(tmcTunnelProtectDriveRoot.getDriveAttributeList());
				// 生成最终需处理的driveRoot
				driveRoot.getDriveAttributeList().get(0).getListRootList().add(listRoot);
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] commands = analysisDriveXmlToCommand.analysisCommands(driveRoot);
			CoderUtils.print16String(commands);
			return commands;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 解析命令成TMCTunnelProtectObject对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<TMCTunnelProtectObject> analysisCommandToTMCTunnelProtectObj(byte[] commands, String configXml) throws Exception {
		List<TMCTunnelProtectObject> tmcList = new ArrayList<TMCTunnelProtectObject>();
		int start = 2;
		int dataCount = 22;
		int calues = 0;
		AnalysisCommandByDriveXml analysisCommandByDriveXml = null;
		TMCTunnelProtectObject TMCTunnelProtectObject = null;
		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			String tmcTunnelProPath = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			calues =  (commands.length - start)/dataCount;
			for(int i =0;i<calues;i++)
			{
				byte[] a = new byte[dataCount];
				System.arraycopy(commands, start + i * dataCount, a, 0, a.length);
				 analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
				 analysisCommandByDriveXml.setCommands(a);
				 TMCTunnelProtectObject = new TMCTunnelProtectObject();
				 DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(tmcTunnelProPath);
				 DriveAttribute driveAttribute = null;
				 for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
						driveAttribute = driveRoot.getDriveAttributeList().get(j);
						assignTMCTunnelProtectObj(TMCTunnelProtectObject, driveAttribute);
					}
					tmcList.add(TMCTunnelProtectObject);
				 
			}
			return tmcList;

		} catch (Exception e) {
			throw e;
		}finally
		{
			 analysisCommandByDriveXml = null;
			 TMCTunnelProtectObject = null;
		}

	}

	/**
	 * 给driveAttribute赋值
	 * 
	 * @param driveAttribute
	 * @param tmcTunnelProtectObject
	 */
	private void assignTMCDriveAttribute(DriveAttribute driveAttribute, TMCTunnelProtectObject tmcTunnelProtectObject) {
		if ("条目id".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tmcTunnelProtectObject.getTmcTunnelId() + "");
		} else if ("保护类型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tmcTunnelProtectObject.getProtectionType() + "");
		} else if ("主用槽位".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tmcTunnelProtectObject.getMainSlot() + "");
		} else if ("主用端口".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tmcTunnelProtectObject.getMainPort() + "");
		} else if ("主用LSP ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tmcTunnelProtectObject.getMainLspId() + "");
		} else if ("主用PW ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tmcTunnelProtectObject.getMainPwId() + "");
		} else if ("备用槽位".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tmcTunnelProtectObject.getStandbySlot() + "");
		} else if ("备用端口".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tmcTunnelProtectObject.getStnadbyPort() + "");
		} else if ("备用LSP ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tmcTunnelProtectObject.getStandbyLspId() + "");
		} else if ("备用PW ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tmcTunnelProtectObject.getStandbyPwId() + "");
		} else if ("拖延时间".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tmcTunnelProtectObject.getProtractedTime() + "");
		} else if ("返回类型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tmcTunnelProtectObject.getReturnType() + "");
		} else if ("备用".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tmcTunnelProtectObject.getReserve() + "");
		}

	}

	/**
	 * 给TMCTunnelProtectObject赋值
	 * 
	 * @param driveAttribute
	 * @param tmcTunnelProtectObject
	 */
	private void assignTMCTunnelProtectObj(TMCTunnelProtectObject tmcObject, DriveAttribute driveAttribute) {
		if ("条目id".equals(driveAttribute.getDescription())) {
			tmcObject.setTmcTunnelId(Integer.parseInt(driveAttribute.getValue()));
		} else if ("保护类型".equals(driveAttribute.getDescription())) {
			tmcObject.setProtectionType(Integer.parseInt(driveAttribute.getValue()));
		} else if ("主用槽位".equals(driveAttribute.getDescription())) {
			tmcObject.setMainSlot(Integer.parseInt(driveAttribute.getValue()));
		} else if ("主用端口".equals(driveAttribute.getDescription())) {
			tmcObject.setMainPort(Integer.parseInt(driveAttribute.getValue()));
		} else if ("主用LSP ID".equals(driveAttribute.getDescription())) {
			tmcObject.setMainLspId(Integer.parseInt(driveAttribute.getValue()));
		} else if ("主用PW ID".equals(driveAttribute.getDescription())) {
			tmcObject.setMainPwId(Integer.parseInt(driveAttribute.getValue()));
		} else if ("备用槽位".equals(driveAttribute.getDescription())) {
			tmcObject.setStandbySlot(Integer.parseInt(driveAttribute.getValue()));
		} else if ("备用端口".equals(driveAttribute.getDescription())) {
			tmcObject.setStnadbyPort(Integer.parseInt(driveAttribute.getValue()));
		} else if ("备用LSP ID".equals(driveAttribute.getDescription())) {
			tmcObject.setStandbyLspId(Integer.parseInt(driveAttribute.getValue()));
		} else if ("备用PW ID".equals(driveAttribute.getDescription())) {
			tmcObject.setStandbyPwId(Integer.parseInt(driveAttribute.getValue()));
		} else if ("拖延时间".equals(driveAttribute.getDescription())) {
			tmcObject.setProtractedTime(Integer.parseInt(driveAttribute.getValue()));
		} else if ("返回类型".equals(driveAttribute.getDescription())) {
			tmcObject.setReturnType(Integer.parseInt(driveAttribute.getValue()));
		} else if ("备用".equals(driveAttribute.getDescription())) {

		}

	}
}
