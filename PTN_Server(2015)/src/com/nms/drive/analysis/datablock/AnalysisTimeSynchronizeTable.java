package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.PTPPortObject;
import com.nms.drive.service.bean.TimeSynchronizeObject;

/**
 * 时间同步配置块(1CH)
 * 
 * @author hulei
 * 
 */
public class AnalysisTimeSynchronizeTable extends AnalysisBase {
	private String ptpPortObjPath = "com/nms/drive/analysis/xmltool/file/时间同步配置块(1CH)_sub.xml";

	/**
	 * 将TimeSynchronizeObject转换为命令
	 * 
	 * @param timeSynchronizeObject
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisTimeSynchronizeObjectToCommands(TimeSynchronizeObject timeSynchronizeObject, String configXml) throws Exception {
		List<PTPPortObject> ptpPortObjectList = timeSynchronizeObject.getPtpPortList();
		try {

			List<ListRoot> ptpListRootList = new ArrayList<ListRoot>();
			DriveRoot driveRoot = null;
			ListRoot listRoot = null;
			for (int i = 0; i < ptpPortObjectList.size(); i++) {
				// 获取PTPPortDriveRoot的对象
				driveRoot = getPTPPortDriveRoot(ptpPortObjectList.get(i), ptpPortObjPath);
				listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot.getDriveAttributeList());
				ptpListRootList.add(listRoot);
			}
			// 获取TimeSynchronzieDriveRoot的对象
			DriveRoot driveRoot_config = getTimeSynchronzieDriveRoot(timeSynchronizeObject, configXml, ptpListRootList);
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			// 生成命令
			byte[] commands = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
			// CoderUtils.print16String(commands);
			return commands;

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 将命令转换为TimeSynchronizeObject
	 * 
	 * @param timeSynchronizeObject
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public TimeSynchronizeObject analysisCommandsToTimeSynchronizeObject(byte[] commands, String configXml) throws Exception {
		int NEhead = 5;// NE头长度
		int controlPanelHead = 101;// 盘头长度
		int dataBlockHead = 25;// 配置块头信息长度

		int pointer = 0;// 当前commands下标
		pointer = NEhead + controlPanelHead + dataBlockHead;
		byte[] timeSyncByte = new byte[61];// 存放timeSynchronizeObject的命令
		byte[] ptpPortByte = new byte[20];// 存放ptpPortByte的命令

		System.arraycopy(commands, pointer, timeSyncByte, 0, timeSyncByte.length);
		pointer += timeSyncByte.length;// 移动下标
		try {
			// 生成timeSynchronizeObject对象
			TimeSynchronizeObject timeSynchronizeObject = getTimeSynchronizeObject(timeSyncByte, configXml);
			int ptpPortCount = timeSynchronizeObject.getPtpPortCount();// 获取条目数

			PTPPortObject ptpPortObject = null;
			for (int i = 0; i < ptpPortCount; i++) {
				System.arraycopy(commands, pointer, ptpPortByte, 0, ptpPortByte.length);
				pointer += ptpPortByte.length;// 移动下标
				// 生成ptpPortObject对象
				ptpPortObject = getPTPPortObject(ptpPortByte, ptpPortObjPath);
				// 添加ptpPortObject入timeSynchronizeObject
				timeSynchronizeObject.getPtpPortList().add(ptpPortObject);
			}

			return timeSynchronizeObject;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 获取TimeSynchronzieObject的DriveRoot对象
	 * 
	 * @param timeSynchronizeObject
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	private DriveRoot getTimeSynchronzieDriveRoot(TimeSynchronizeObject timeSynchronizeObject, String configXml, List<ListRoot> ptpListRootList) throws Exception {
		try {
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			DriveAttribute driveAttribute = null;
			String[] soruceClockIds = timeSynchronizeObject.getSourcesClockId().split(",");
			if (soruceClockIds.length == 1) {// soruceClockIds 为空
				soruceClockIds = new String[] { null, null, null, null, null, null, null, null };
			}
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				assignTimeSynchronzieDriveAttribute(driveAttribute, timeSynchronizeObject, ptpListRootList, soruceClockIds);// 赋值
			}
			return driveRoot;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取ptpPortObject的DriveRoot对象
	 * 
	 * @param ptpPortObject
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	private DriveRoot getPTPPortDriveRoot(PTPPortObject ptpPortObject, String configXml) throws Exception {
		try {
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				assignPTPPortDriveAttribute(driveAttribute, ptpPortObject);// 賦值
			}
			return driveRoot;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 根据命令获取timeSynchronizeObject对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	private TimeSynchronizeObject getTimeSynchronizeObject(byte[] commands, String configXml) throws Exception {
		TimeSynchronizeObject timeSynchronizeObject = new TimeSynchronizeObject();
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(commands);
		try {
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			DriveAttribute driveAttribute = null;
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				assignTimeSynchronzieObject(timeSynchronizeObject, driveAttribute, sb);// 给timeSynchronizeObject赋值
			}
		} catch (Exception e) {
			throw e;
		}

		return timeSynchronizeObject;

	}

	/**
	 * 根据命令获取PTPPortObject对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	private PTPPortObject getPTPPortObject(byte[] commands, String configXml) throws Exception {
		PTPPortObject ptpPortObject = new PTPPortObject();
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(commands);
		try {
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				assignPTPPortObject(ptpPortObject, driveAttribute);// 给ptpPortObj赋值
			}
		} catch (Exception e) {
			throw e;
		}

		return ptpPortObject;
	}

	/**
	 * 给TimeSynchronzieObject的DriveRoot对象赋值
	 * 
	 * @param driveAttribute
	 * @param timeSynchronizeObject
	 */
	private void assignTimeSynchronzieDriveAttribute(DriveAttribute driveAttribute, TimeSynchronizeObject timeSynchronizeObject, List<ListRoot> ptpListRootList, String[] soruceClockIds) {
		if ("PTP模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getPtpPattern() + "");
		} else if ("时钟模型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getClockModel() + "");
		} else if ("源时钟模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getSourcesClockModel() + "");
		} else if ("源时钟ID1".equals(driveAttribute.getDescription())) {
			if (soruceClockIds[0] != null) {
				driveAttribute.setValue(soruceClockIds[0]);
			}
		} else if ("源时钟ID2".equals(driveAttribute.getDescription())) {
			if (soruceClockIds[1] != null) {
				driveAttribute.setValue(soruceClockIds[1]);
			}
		} else if ("源时钟ID3".equals(driveAttribute.getDescription())) {
			if (soruceClockIds[2] != null) {
				driveAttribute.setValue(soruceClockIds[2]);
			}
		} else if ("源时钟ID4".equals(driveAttribute.getDescription())) {
			if (soruceClockIds[3] != null) {
				driveAttribute.setValue(soruceClockIds[4]);
			}
		} else if ("源时钟ID5".equals(driveAttribute.getDescription())) {
			if (soruceClockIds[4] != null) {
				driveAttribute.setValue(soruceClockIds[4]);
			}
		} else if ("源时钟ID6".equals(driveAttribute.getDescription())) {
			if (soruceClockIds[5] != null) {
				driveAttribute.setValue(soruceClockIds[5]);
			}
		} else if ("源时钟ID7".equals(driveAttribute.getDescription())) {
			if (soruceClockIds[6] != null) {
				driveAttribute.setValue(soruceClockIds[6]);
			}
		} else if ("源时钟ID8".equals(driveAttribute.getDescription())) {
			if (soruceClockIds[7] != null) {
				driveAttribute.setValue(soruceClockIds[7]);
			}
		} else if ("源时钟类型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getSourcesClockType() + "");
		} else if ("源时钟优先级1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getSourcesClockPriority_1() + "");
		} else if ("源时钟优先级2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getSourcesClockPriority_2() + "");
		} else if ("源时钟等级".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getSourcesClockGrade() + "");
		} else if ("源时钟精度".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getSourcesClockPrecision() + "");
		} else if ("备用1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getReserve_1() + "");
		} else if ("Slave_Only模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getSlaveOnlyPattern() + "");
		} else if ("(输入时延补偿)补偿属性".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getInputTimeDelayOffsetAttribute() + "");
		} else if ("(输入时延补偿)时延补偿值".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getInputTimeDelayOffsetValue() + "");
		} else if ("(输出时延补偿)补偿属性".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getOutputTimeDelayOffsetAttribute() + "");
		} else if ("(输出时延补偿)时延补偿值".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getOutputTimeDelayOffsetValue() + "");
		} else if ("同步周期".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getSynchronizePeriod() + "");
		} else if ("通告周期".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getAnnouncePeriod() + "");
		} else if ("时间信息接口".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getTimeInformationPort() + "");
		} else if ("备用2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getReserve_2() + "");
		} else if ("PTP端口配置条目数".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSynchronizeObject.getPtpPortList().size() + "");
			driveAttribute.getListRootList().clear();// 清除自带的listRoot
			for (int i = 0; i < ptpListRootList.size(); i++) {
				driveAttribute.getListRootList().add(ptpListRootList.get(i));// 添加ptpListRoot
			}
		}
	}

	/**
	 * 给PTPPortObject的DriveRoot对象赋值
	 * 
	 * @param driveAttribute
	 * @param ptpPortObject
	 */
	private void assignPTPPortDriveAttribute(DriveAttribute driveAttribute, PTPPortObject ptpPortObject) {
		if ("索引".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortObject.getIndex() + "");
		} else if ("端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortObject.getPort() + "");
		} else if ("工作模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortObject.getWorkPattern() + "");
		} else if ("端口ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortObject.getPortId() + "");
		} else if ("线路不对称时延属性".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortObject.getLineAsymmetricTimeDelayAttribute() + "");
		} else if ("线路不对称时延补偿值".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortObject.getLineAsymmetricTimeDelayValue() + "");
		} else if ("延时机制".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortObject.getTimeDelayMechanism() + "");
		} else if ("消息模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortObject.getMessagePattern() + "");
		} else if ("备用".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortObject.getReserve() + "");
		}
	}

	/**
	 * 给TimeSynchronzieObject对象赋值
	 * 
	 * @param timeSynchronizeObject
	 * @param driveAttribute
	 */
	private void assignTimeSynchronzieObject(TimeSynchronizeObject timeSynchronizeObject, DriveAttribute driveAttribute, StringBuffer sb) {
		if ("PTP模式".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setPtpPattern(Integer.parseInt(driveAttribute.getValue()));
		} else if ("时钟模型".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setClockModel(Integer.parseInt(driveAttribute.getValue()));
		} else if ("源时钟模式".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setSourcesClockModel(Integer.parseInt(driveAttribute.getValue()));
		} else if ("源时钟ID1".equals(driveAttribute.getDescription())) {
			sb.append(driveAttribute.getValue()).append(",");
		} else if ("源时钟ID2".equals(driveAttribute.getDescription())) {
			sb.append(driveAttribute.getValue()).append(",");
		} else if ("源时钟ID3".equals(driveAttribute.getDescription())) {
			sb.append(driveAttribute.getValue()).append(",");
		} else if ("源时钟ID4".equals(driveAttribute.getDescription())) {
			sb.append(driveAttribute.getValue()).append(",");
		} else if ("源时钟ID5".equals(driveAttribute.getDescription())) {
			sb.append(driveAttribute.getValue()).append(",");
		} else if ("源时钟ID6".equals(driveAttribute.getDescription())) {
			sb.append(driveAttribute.getValue()).append(",");
		} else if ("源时钟ID7".equals(driveAttribute.getDescription())) {
			sb.append(driveAttribute.getValue()).append(",");
		} else if ("源时钟ID8".equals(driveAttribute.getDescription())) {
			sb.append(driveAttribute.getValue());
			timeSynchronizeObject.setSourcesClockId(sb.toString());
		} else if ("源时钟类型".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setSourcesClockType(Integer.parseInt(driveAttribute.getValue()));
		} else if ("源时钟优先级1".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setSourcesClockPriority_1(Integer.parseInt(driveAttribute.getValue()));
		} else if ("源时钟优先级2".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setSourcesClockPriority_2(Integer.parseInt(driveAttribute.getValue()));
		} else if ("源时钟等级".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setSourcesClockGrade(Integer.parseInt(driveAttribute.getValue()));
		} else if ("源时钟精度".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setSourcesClockPrecision(Integer.parseInt(driveAttribute.getValue()));
		} else if ("备用1".equals(driveAttribute.getDescription())) {
			// 备用不赋值
		} else if ("Slave_Only模式".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setSlaveOnlyPattern(Integer.parseInt(driveAttribute.getValue()));
		} else if ("(输入时延补偿)补偿属性".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setInputTimeDelayOffsetAttribute(Integer.parseInt(driveAttribute.getValue()));
		} else if ("(输入时延补偿)时延补偿值".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setInputTimeDelayOffsetValue(Integer.parseInt(driveAttribute.getValue()));
		} else if ("(输出时延补偿)补偿属性".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setOutputTimeDelayOffsetAttribute(Integer.parseInt(driveAttribute.getValue()));
		} else if ("(输出时延补偿)时延补偿值".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setOutputTimeDelayOffsetValue(Integer.parseInt(driveAttribute.getValue()));
		} else if ("同步周期".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setSynchronizePeriod(Integer.parseInt(driveAttribute.getValue()));
		} else if ("通告周期".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setAnnouncePeriod(Integer.parseInt(driveAttribute.getValue()));
		} else if ("时间信息接口".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setTimeInformationPort(Integer.parseInt(driveAttribute.getValue()));
		} else if ("备用2".equals(driveAttribute.getDescription())) {
			// 备用不赋值
		} else if ("PTP端口配置条目数".equals(driveAttribute.getDescription())) {
			timeSynchronizeObject.setPtpPortCount(Integer.parseInt(driveAttribute.getValue()));
		}

	}

	/**
	 * 给PTPPortObject对象赋值
	 * 
	 * @param timeSynchronizeObject
	 * @param driveAttribute
	 */
	private void assignPTPPortObject(PTPPortObject ptpPortObject, DriveAttribute driveAttribute) {
		if ("索引".equals(driveAttribute.getDescription())) {
			ptpPortObject.setIndex(Integer.parseInt(driveAttribute.getValue()));
		} else if ("端口号".equals(driveAttribute.getDescription())) {
			ptpPortObject.setPort(Integer.parseInt(driveAttribute.getValue()));
		} else if ("工作模式".equals(driveAttribute.getDescription())) {
			ptpPortObject.setWorkPattern(Integer.parseInt(driveAttribute.getValue()));
		} else if ("端口ID".equals(driveAttribute.getDescription())) {
			ptpPortObject.setPortId(Integer.parseInt(driveAttribute.getValue()));
		} else if ("线路不对称时延属性".equals(driveAttribute.getDescription())) {
			ptpPortObject.setLineAsymmetricTimeDelayAttribute(Integer.parseInt(driveAttribute.getValue()));
		} else if ("线路不对称时延补偿值".equals(driveAttribute.getDescription())) {
			ptpPortObject.setLineAsymmetricTimeDelayValue(Integer.parseInt(driveAttribute.getValue()));
		} else if ("延时机制".equals(driveAttribute.getDescription())) {
			ptpPortObject.setTimeDelayMechanism(Integer.parseInt(driveAttribute.getValue()));
		} else if ("消息模式".equals(driveAttribute.getDescription())) {
			ptpPortObject.setMessagePattern(Integer.parseInt(driveAttribute.getValue()));
		} else if ("备用".equals(driveAttribute.getDescription())) {
			// 不用赋值
		}
	}

}
