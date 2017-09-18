package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.E1LegObject;
import com.nms.drive.service.bean.E1Object;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisE1Table extends AnalysisBase {
	private String e1LegXml = "com/nms/drive/analysis/xmltool/file/E1仿真配置(18H)_sub.xml";

	public byte[] analysisE1ObjectToCommands(E1Object e1Object, String configXml) throws Exception {

		List<E1LegObject> e1LegList = e1Object.getE1LegObjectList();
		try {

			DriveRoot e1LegDriveRoot = null;
			ListRoot listRoot = null;
			List<ListRoot> e1LegListRootList = new ArrayList<ListRoot>();
			for (int i = 0; i < e1LegList.size(); i++) {
				E1LegObject e1LegObj = e1LegList.get(i);
				e1LegDriveRoot = getE1LegDirveRoot(e1LegObj, e1LegXml);
				listRoot = new ListRoot();
				listRoot.setDriveAttributeList(e1LegDriveRoot.getDriveAttributeList());
				e1LegListRootList.add(listRoot);
			}

			DriveRoot e1DriveRoot = getE1DriveRoot(e1Object, configXml, e1LegListRootList);// 生成E1DriveRoot对象
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommands = analysisDriveXmlToCommand.analysisCommands(e1DriveRoot);
			return dataCommands;

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 根据命令生成E1Obj对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	public E1Object analysisCommandsToE1Object(byte[] commands, String configXml) {
		int pointer = 0;// 当前commands下标
		pointer = 0;

		byte[] e1Buffer = new byte[4];// e1Object对象的命令
		byte[] e1LegBuffer = new byte[13];// e1LegObject对象的命令

		System.arraycopy(commands, pointer, e1Buffer, 0, e1Buffer.length);
		pointer += e1Buffer.length;// 移动下标
		E1Object e1Obj = getE1Object(e1Buffer, configXml);// 获取e1Object对象

		int e1LegCount = e1Obj.getE1LegObjectCount();
		E1LegObject e1LegObject = null;
		for (int i = 0; i < e1LegCount; i++) {
			System.arraycopy(commands, pointer, e1LegBuffer, 0, e1LegBuffer.length);
			pointer += e1LegBuffer.length;
			e1LegObject = getE1LegObj(e1LegBuffer, e1LegXml);// 获取e1LegObj对象
			e1Obj.getE1LegObjectList().add(e1LegObject);
		}

		return e1Obj;

	}

	/**
	 * 获取E1Obj的DriveRoot对象
	 * 
	 * @param e1Obj
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	private DriveRoot getE1DriveRoot(E1Object e1Obj, String configXml, List<ListRoot> e1LegListRootList) throws Exception {
		try {
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("E1支路输出时钟源选择".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(e1Obj.getE1LegOutPutClockSource() + "");
				}
				if ("TDM时钟源".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(e1Obj.getTdmClockSource() + "");
				}
				if ("RTP使能".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(e1Obj.getRtpEnable() + "");
				}
				if ("条目数".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(e1Obj.getE1LegObjectList().size() + "");
					driveAttribute.getListRootList().clear();// 清除自带的listRoot
					for (int j = 0; j < e1LegListRootList.size(); j++) {
						driveAttribute.getListRootList().add(e1LegListRootList.get(j));
					}
				}
			}
			return driveRoot;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取E1LegObj的DriveRoot对象
	 * 
	 * @param e1LegObj
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	private DriveRoot getE1LegDirveRoot(E1LegObject e1LegObj, String configXml) throws Exception {
		try {
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("2M支路屏蔽".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(e1LegObj.getLegShield() + "");
				}
				if ("2M支路使能".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(e1LegObj.getLegEnable() + "");
				}
				if ("2M支路ID".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(e1LegObj.getLegId() + "");
				}
				if ("缓存时间控制使能".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(e1LegObj.getPrestoreTimeEnable() + "");
				}
				if ("缓存时间".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(e1LegObj.getPrestoreTime() + "");
				}
				if ("封装帧个数".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(e1LegObj.getPinCount() + "");
				}
				if ("pw标签".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(e1LegObj.getLegId() + "");
				}
				if ("备用".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(e1LegObj.getReserve() + "");
				}
			}
			return driveRoot;
		} catch (Exception e) {
			throw e;

		}

	}

	/**
	 * 根据命令获取E1Object对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private E1Object getE1Object(byte[] commands, String configXml) {
		E1Object e1Obj = new E1Object();

		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);

			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("E1支路输出时钟源选择".equals(driveAttribute.getDescription())) {
					e1Obj.setE1LegOutPutClockSource(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("TDM时钟源".equals(driveAttribute.getDescription())) {
					e1Obj.setTdmClockSource(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("RTP使能".equals(driveAttribute.getDescription())) {
					e1Obj.setRtpEnable(Integer.parseInt(driveAttribute.getValue()));

				}
				if ("条目数".equals(driveAttribute.getDescription())) {
					e1Obj.setE1LegObjectCount(Integer.parseInt(driveAttribute.getValue()));

				}

			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return e1Obj;

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
