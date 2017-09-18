package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.SingleObject;
import com.nms.ui.manager.ExceptionManage;
/**
 * 解析 单卡基本信息
 * @author 闵何招
 *
 */
public class AnalysisSingleCardBasicInformation extends AnalysisBase {
	private int dataCount = 99;// 每个数据块的长度
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度

	/**
	 * 根据对象生成字节数组
	 * 
	 * @param SingleObject对象
	 * @param configXml配置XML
	 * @return 命令
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(SingleObject singleObject,
			String configXml) throws Exception {
		try {
			String[] time = singleObject.getTime().split(",");
			String[] mode = singleObject.getMode().split(",");
			String[] bdinffx = singleObject.getBdinffx().split(",");
			String[] configFlag = singleObject.getConfigFlag().split(",");
			String[] singleType = singleObject.getSingleType().split(",");
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			for (int i = 0; i < driveRoot_config.getDriveAttributeList().size(); i++) {
				DriveAttribute driveAttribute = driveRoot_config
						.getDriveAttributeList().get(i);
				// 赋值
				singleObjectToDriveAttribute(time, mode, bdinffx, configFlag,
						singleType, singleObject, driveAttribute);
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand
					.analysisCommands(driveRoot_config);
			return dataCommand;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return SingleObject
	 */
	public SingleObject analysisCommandToObject(byte[] commands,
			String configXml) throws Exception {
		SingleObject singleObject = new SingleObject();
		// 起始长度
		int start = NEhead + controlPanelHead + dataBlockHead;
		byte[] a = new byte[dataCount];
		System.arraycopy(commands, start, a, 0, a.length);
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(a);
		try {
			DriveRoot driveRoot = analysisCommandByDriveXml
					.analysisDriveAttrbute(configXml);
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {

				DriveAttribute driveAttribute = driveRoot
						.getDriveAttributeList().get(i);
				// 赋值
				driveAttributeToSingleObject(singleObject, driveAttribute);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return singleObject;

	}

	/**
	 * SingleObject赋值给driveAttribute
	 * 
	 * @param time,mode,bdinffx,configFlag,singleType,singleObject
	 * @param driveAttribute
	 */
	public void singleObjectToDriveAttribute(String[] time, String[] mode,
			String[] bdinffx, String[] configFlag, String[] singleType,
			SingleObject singleObject, DriveAttribute driveAttribute) {

		// Time1 赋值
		if ("Time1".equals(driveAttribute.getDescription())) {
			if (time[0] != null) {
				driveAttribute.setValue(time[0]);
			}
		}

		// Time2 赋值
		if ("Time2".equals(driveAttribute.getDescription())) {
			if (time[1] != null) {
			driveAttribute.setValue(time[1]);
			}
		}

		// Time3 赋值
		if ("Time3".equals(driveAttribute.getDescription())) {
			if (time[2] != null) {
			driveAttribute.setValue(time[2]);
			}
		}

		// Time4 赋值
		if ("Time4".equals(driveAttribute.getDescription())) {
			if (time[3] != null) {
			driveAttribute.setValue(time[3]);
			}
		}

		// Time5 赋值
		if ("Time5".equals(driveAttribute.getDescription())) {
			if (time[4] != null) {
			driveAttribute.setValue(time[4]);
			}
		}

		// Time6 赋值
		if ("Time6".equals(driveAttribute.getDescription())) {
			if (time[5] != null) {
			driveAttribute.setValue(time[5]);
			}
		}

		// Time7赋值
		if ("Time7".equals(driveAttribute.getDescription())) {
			if (time[6] != null) {
			driveAttribute.setValue(time[6]);
			}
		}

		// ACT 赋值
		if ("ACT".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(singleObject.getAct() + "");
		}

		// MODE1赋值
		if ("MODE1".equals(driveAttribute.getDescription())) {
			if (mode[0] != null) {
			driveAttribute.setValue(mode[0]);
			}
		}

		// MODE2赋值
		if ("MODE2".equals(driveAttribute.getDescription())) {
			if (mode[1] != null) {
			driveAttribute.setValue(mode[1]);
			}
		}

		// MODE3赋值
		if ("MODE3".equals(driveAttribute.getDescription())) {
			if (mode[2] != null) {
			driveAttribute.setValue(mode[2]);
			}
		}

		// MODE4赋值
		if ("MODE4".equals(driveAttribute.getDescription())) {
			if (mode[3] != null) {
			driveAttribute.setValue(mode[3]);
			}
		}

		// MODE5赋值
		if ("MODE5".equals(driveAttribute.getDescription())) {
			if (mode[4] != null) {
			driveAttribute.setValue(mode[4]);
			}
		}

		// MODE6赋值
		if ("MODE6".equals(driveAttribute.getDescription())) {
			if (mode[5] != null) {
			driveAttribute.setValue(mode[5]);
			}
		}

		// BDINFFX1赋值
		if ("BDINFFX1".equals(driveAttribute.getDescription())) {
			if (bdinffx[0] != null) {
			driveAttribute.setValue(bdinffx[0]);
			}
		}

		// BDINFFX2赋值
		if ("BDINFFX2".equals(driveAttribute.getDescription())) {
			if (bdinffx[1] != null) {
			driveAttribute.setValue(bdinffx[1]);
			}
		}

		// BDINFFX3赋值
		if ("BDINFFX3".equals(driveAttribute.getDescription())) {
			if (bdinffx[2] != null) {
			driveAttribute.setValue(bdinffx[2]);
			}
		}

		// BDINFFX4赋值
		if ("BDINFFX4".equals(driveAttribute.getDescription())) {
			if (bdinffx[3] != null) {
			driveAttribute.setValue(bdinffx[3]);
			}
		}

		// BDINFFX5赋值
		if ("BDINFFX5".equals(driveAttribute.getDescription())) {
			if (bdinffx[4] != null) {
			driveAttribute.setValue(bdinffx[4]);
			}
		}

		// BDINFFX6赋值
		if ("BDINFFX6".equals(driveAttribute.getDescription())) {
			if (bdinffx[5] != null) {
			driveAttribute.setValue(bdinffx[5]);
			}
		}

		// BDINFFX7赋值
		if ("BDINFFX7".equals(driveAttribute.getDescription())) {
			if (bdinffx[6] != null) {
			driveAttribute.setValue(bdinffx[6]);
			}
		}

		// BDINFFX8赋值
		if ("BDINFFX8".equals(driveAttribute.getDescription())) {
			if (bdinffx[7] != null) {
			driveAttribute.setValue(bdinffx[7]);
			}
		}

		// BDINFFX9赋值
		if ("BDINFFX9".equals(driveAttribute.getDescription())) {
			if (bdinffx[8] != null) {
			driveAttribute.setValue(bdinffx[8]);
			}
		}

		// BDINFFX10赋值
		if ("BDINFFX10".equals(driveAttribute.getDescription())) {
			if (bdinffx[9] != null) {
			driveAttribute.setValue(bdinffx[9]);
			}
		}
		// 配置块扩展标志1 赋值
		if ("配置块扩展标志1".equals(driveAttribute.getDescription())) {
			if (configFlag[0] != null) {
			driveAttribute.setValue(configFlag[0]);
			}
		}

		// 配置块扩展标志2 赋值
		if ("配置块扩展标志2".equals(driveAttribute.getDescription())) {
			if (configFlag[1] != null) {
			driveAttribute.setValue(configFlag[1]);
			}
		}

		// 配置块扩展标志3 赋值
		if ("配置块扩展标志3".equals(driveAttribute.getDescription())) {
			if (configFlag[2] != null) {
			driveAttribute.setValue(configFlag[2]);
			}
		}

		// 配置块扩展标志4 赋值
		if ("配置块扩展标志4".equals(driveAttribute.getDescription())) {
			if (configFlag[3] != null) {
			driveAttribute.setValue(configFlag[3]);
			}
		}

		// 配置块扩展类型 赋值
		if ("配置块扩展类型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(singleObject.getConfigType() + "");
		}
		// 配置块条目表偏移 赋值
		if ("配置块条目表偏移".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(singleObject.getConfigCount() + "");
		}

		// 单卡类型和地址1 赋值
		if ("单卡类型和地址1".equals(driveAttribute.getDescription())) {
			if (singleType[0] != null) {
			driveAttribute.setValue(singleType[0]);
			}
		}

		// 单卡类型和地址2 赋值
		if ("单卡类型和地址2".equals(driveAttribute.getDescription())) {
			if (singleType[1] != null) {
			driveAttribute.setValue(singleType[1]);
			}
		}

		// 单卡类型和地址3 赋值
		if ("单卡类型和地址3".equals(driveAttribute.getDescription())) {
			if (singleType[2] != null) {
			driveAttribute.setValue(singleType[2]);
			}
		}

		// 单卡类型和地址4 赋值
		if ("单卡类型和地址4".equals(driveAttribute.getDescription())) {
			if (singleType[3] != null) {
			driveAttribute.setValue(singleType[3]);
			}
		}

		// 单卡类型和地址5 赋值
		if ("单卡类型和地址5".equals(driveAttribute.getDescription())) {
			if (singleType[4] != null) {
			driveAttribute.setValue(singleType[4]);
			}
		}

		// 单卡类型和地址6 赋值
		if ("单卡类型和地址6".equals(driveAttribute.getDescription())) {
			if (singleType[5] != null) {
			driveAttribute.setValue(singleType[5]);
			}
		}
	}

	/**
	 * driveAttribute赋值给SingleObject
	 * 
	 * @param singleObject
	 * @param driveAttribute
	 */
	public void driveAttributeToSingleObject(SingleObject singleObject,
			DriveAttribute driveAttribute) {
		// time 赋值
		if ("Time1".equals(driveAttribute.getDescription())) {
			singleObject.setTime(driveAttribute.getValue());
		}

		if ("Time2".equals(driveAttribute.getDescription())) {
			singleObject.setTime(singleObject.getTime() + ","
					+ driveAttribute.getValue());
		}

		if ("Time3".equals(driveAttribute.getDescription())) {
			singleObject.setTime(singleObject.getTime() + ","
					+ driveAttribute.getValue());
		}

		if ("Time4".equals(driveAttribute.getDescription())) {
			singleObject.setTime(singleObject.getTime() + ","
					+ driveAttribute.getValue());
		}

		if ("Time5".equals(driveAttribute.getDescription())) {
			singleObject.setTime(singleObject.getTime() + ","
					+ driveAttribute.getValue());
		}

		if ("Time6".equals(driveAttribute.getDescription())) {
			singleObject.setTime(singleObject.getTime() + ","
					+ driveAttribute.getValue());
		}

		if ("Time7".equals(driveAttribute.getDescription())) {
			singleObject.setTime(singleObject.getTime() + ","
					+ driveAttribute.getValue());
		}

		// act 赋值
		if ("ACT".equals(driveAttribute.getDescription())) {
			singleObject.setAct(Integer.parseInt(driveAttribute.getValue()));
		}

		// mode赋值
		if ("MODE1".equals(driveAttribute.getDescription())) {
			singleObject.setMode(driveAttribute.getValue());
		}

		if ("MODE2".equals(driveAttribute.getDescription())) {
			singleObject.setMode(singleObject.getMode() + ","
					+ driveAttribute.getValue());
		}

		if ("MODE3".equals(driveAttribute.getDescription())) {
			singleObject.setMode(singleObject.getMode() + ","
					+ driveAttribute.getValue());
		}

		if ("MODE4".equals(driveAttribute.getDescription())) {
			singleObject.setMode(singleObject.getMode() + ","
					+ driveAttribute.getValue());
		}

		if ("MODE5".equals(driveAttribute.getDescription())) {
			singleObject.setMode(singleObject.getMode() + ","
					+ driveAttribute.getValue());
		}

		if ("MODE6".equals(driveAttribute.getDescription())) {
			singleObject.setMode(singleObject.getMode() + ","
					+ driveAttribute.getValue());
		}

		// bdinffx赋值
		if ("BDINFFX1".equals(driveAttribute.getDescription())) {
			singleObject.setBdinffx(driveAttribute.getValue());
		}

		if ("BDINFFX2".equals(driveAttribute.getDescription())) {
			singleObject.setBdinffx(singleObject.getBdinffx() + ","
					+ driveAttribute.getValue());
		}

		if ("BDINFFX3".equals(driveAttribute.getDescription())) {
			singleObject.setBdinffx(singleObject.getBdinffx() + ","
					+ driveAttribute.getValue());
		}

		if ("BDINFFX4".equals(driveAttribute.getDescription())) {
			singleObject.setBdinffx(singleObject.getBdinffx() + ","
					+ driveAttribute.getValue());
		}

		if ("BDINFFX5".equals(driveAttribute.getDescription())) {
			singleObject.setBdinffx(singleObject.getBdinffx() + ","
					+ driveAttribute.getValue());
		}

		if ("BDINFFX6".equals(driveAttribute.getDescription())) {
			singleObject.setBdinffx(singleObject.getBdinffx() + ","
					+ driveAttribute.getValue());
		}

		if ("BDINFFX7".equals(driveAttribute.getDescription())) {
			singleObject.setBdinffx(singleObject.getBdinffx() + ","
					+ driveAttribute.getValue());
		}

		if ("BDINFFX8".equals(driveAttribute.getDescription())) {
			singleObject.setBdinffx(singleObject.getBdinffx() + ","
					+ driveAttribute.getValue());
		}

		if ("BDINFFX9".equals(driveAttribute.getDescription())) {
			singleObject.setBdinffx(singleObject.getBdinffx() + ","
					+ driveAttribute.getValue());
		}

		if ("BDINFFX10".equals(driveAttribute.getDescription())) {
			singleObject.setBdinffx(singleObject.getBdinffx() + ","
					+ driveAttribute.getValue());
		}
		// configFlag 赋值
		if ("配置块扩展标志1".equals(driveAttribute.getDescription())) {
			singleObject.setConfigFlag(driveAttribute.getValue());
		}

		if ("配置块扩展标志2".equals(driveAttribute.getDescription())) {
			singleObject.setConfigFlag(singleObject.getConfigFlag() + ","
					+ driveAttribute.getValue());
		}

		if ("配置块扩展标志3".equals(driveAttribute.getDescription())) {
			singleObject.setConfigFlag(singleObject.getConfigFlag() + ","
					+ driveAttribute.getValue());
		}

		if ("配置块扩展标志4".equals(driveAttribute.getDescription())) {
			singleObject.setConfigFlag(singleObject.getConfigFlag() + ","
					+ driveAttribute.getValue());
		}

		// configType 赋值
		if ("配置块扩展类型".equals(driveAttribute.getDescription())) {
			singleObject.setConfigType(Integer.parseInt(driveAttribute
					.getValue()));
		}
		// configCount 赋值
		if ("配置块条目表偏移".equals(driveAttribute.getDescription())) {
			singleObject.setConfigCount(Integer.parseInt(driveAttribute
					.getValue()));
		}

		// singleType 赋值
		if ("单卡类型和地址1".equals(driveAttribute.getDescription())) {
			singleObject.setSingleType(driveAttribute.getValue());
		}

		if ("单卡类型和地址2".equals(driveAttribute.getDescription())) {
			singleObject.setSingleType(singleObject.getSingleType() + ","
					+ driveAttribute.getValue());
		}

		if ("单卡类型和地址3".equals(driveAttribute.getDescription())) {
			singleObject.setSingleType(singleObject.getSingleType() + ","
					+ driveAttribute.getValue());
		}

		if ("单卡类型和地址4".equals(driveAttribute.getDescription())) {
			singleObject.setSingleType(singleObject.getSingleType() + ","
					+ driveAttribute.getValue());
		}

		if ("单卡类型和地址5".equals(driveAttribute.getDescription())) {
			singleObject.setSingleType(singleObject.getSingleType() + ","
					+ driveAttribute.getValue());
		}

		if ("单卡类型和地址6".equals(driveAttribute.getDescription())) {
			singleObject.setSingleType(singleObject.getSingleType() + ","
					+ driveAttribute.getValue());
		}
	}
}
