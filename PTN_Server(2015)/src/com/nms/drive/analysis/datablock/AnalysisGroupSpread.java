package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.GroupSpreadObject;

/**
 * 
 * 解析静态组播XML将对象转命令和命令转对象
 * 
 * @author Administrator zhangkun
 * 
 */
public class AnalysisGroupSpread extends AnalysisBase {
	private int dataCount = 22;// 每个数据块的长度
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int clauses = 1;// 条目数长度

	// 解析XML转换成命令
	public byte[] analysisObjectToCommand(
			List<GroupSpreadObject> GroupSpreadObjectList, String configXml)
			throws Exception {
		// 获取根目录
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		// 获取file目录地址
		String file = driveRoot_config.getDriveAttributeList().get(0)
				.getListRootList().get(0).getFile();
		// 清除自带的ListRoot
		driveRoot_config.getDriveAttributeList().get(0).getListRootList()
				.clear();
		// 设置DriveRoot的条目数
		driveRoot_config.getDriveAttributeList().get(0).setValue(
				GroupSpreadObjectList.size() + "");
		// 根据条目数来循环
		for (int i = 0, j = GroupSpreadObjectList.size(); i < j; i++) {
			DriveRoot driveRoot_config_1 = super.LoadDriveXml(file);
			ListRoot listRoot = new ListRoot();
			listRoot.setDriveAttributeList(driveRoot_config_1
					.getDriveAttributeList());
			for (int x = 0, y = driveRoot_config_1.getDriveAttributeList()
					.size(); x < y; x++) {
				DriveAttribute driveAttribute = driveRoot_config_1
						.getDriveAttributeList().get(x);
				// 赋值
				GroupSpreadObjectToDriveAttribte(driveAttribute,
						GroupSpreadObjectList.get(i));
			}
			driveRoot_config.getDriveAttributeList().get(0).getListRootList()
					.add(listRoot);
		}
		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] dataCommand = analysisDriveXmlToCommand
				.analysisCommands(driveRoot_config);
//		CoderUtils.print16String(dataCommand);
		return dataCommand;
	}

	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands
	 *            命令
	 * @param configXml
	 *            配置XML
	 * @return
	 * @throws Exception
	 */
	public List<GroupSpreadObject> analysisCommandToObject(byte[] commands,
			String configXml) throws Exception {
		List<GroupSpreadObject> groupSpreadObjectsList = new ArrayList<GroupSpreadObject>();
		DriveRoot driveRoot = super.LoadDriveXml(configXml);
		String file = driveRoot.getDriveAttributeList().get(0)
				.getListRootList().get(0).getFile();
		// 起始长度
		int start = NEhead + controlPanelHead + dataBlockHead + clauses;
		// 条目数
		int count = (commands.length - NEhead - controlPanelHead - dataBlockHead)
				/ dataCount;
		for (int i = 0; i < count; i++) {
			byte[] dataCommands = new byte[dataCount];
			System.arraycopy(commands, start + i * dataCount, dataCommands, 0,
					dataCount);

			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(dataCommands);

			DriveRoot driveRoot_config = analysisCommandByDriveXml
					.analysisDriveAttrbute(file);
			GroupSpreadObject groupSpreadObject = new GroupSpreadObject();

			for (int j = 0, l = driveRoot_config.getDriveAttributeList().size(); j < l; j++) {
				DriveAttribute driveAttribute = driveRoot_config
						.getDriveAttributeList().get(j);
				// 赋值
				DriveAttributeToGroupSpread(groupSpreadObject, driveAttribute);
			}
			groupSpreadObjectsList.add(groupSpreadObject);
		}
		return groupSpreadObjectsList;

	}

	private void DriveAttributeToGroupSpread(
			GroupSpreadObject groupSpreadObject, DriveAttribute driveAttribute) {
		// 给"SM ID" 赋值
		if ("SM ID".equals(driveAttribute.getDescription())) {
			groupSpreadObject.setSMID(Integer.parseInt(driveAttribute
					.getValue()));
		}
		// 给"VPLS-VS 选择"赋值
		if ("VPLS-VS 选择".equals(driveAttribute.getDescription())) {
			groupSpreadObject.setVpls_Vs(Integer.parseInt(driveAttribute
					.getValue()));
		}
		// 给“端口选择1”赋值1
		if (("端口选择1").equals(driveAttribute.getDescription())) {
			groupSpreadObject.setChannelProt(driveAttribute.getValue());
		}
		// 给“端口选择”赋值
		if (driveAttribute.getDescription().contains("端口选择")) {
			for (int i = 2; i < 11; i++) {
				if (("端口选择" + i).equals(driveAttribute.getDescription())) {
					groupSpreadObject.setChannelProt(groupSpreadObject
							.getChannelProt()
							+ "," + driveAttribute.getValue());
				}
			}
		}
		// 当MAC地址1 赋值
		if (("MAC地址1").equals(driveAttribute.getDescription())) {
			groupSpreadObject.setAddressMAC(driveAttribute.getValue());
		}
		// 给“MAC地址”赋值
		if (driveAttribute.getDescription().contains("MAC地址")) {
			for (int i = 2; i < 7; i++) {
				if (("MAC地址" + i).equals(driveAttribute.getDescription())) {
					groupSpreadObject.setAddressMAC(groupSpreadObject
							.getAddressMAC()
							+ "-" + driveAttribute.getValue());
				}

			}

		}

	}

	private void GroupSpreadObjectToDriveAttribte(
			DriveAttribute driveAttribute, GroupSpreadObject groupSpreadObject) {
		// 给"SM ID" 赋值
		if ("SM ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(groupSpreadObject.getSMID() + "");
		}
		// 给"VPLS-VS 选择"赋值
		if ("VPLS-VS 选择".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(groupSpreadObject.getVpls_Vs() + "");
		}
		// 给“端口选择”赋值
		if (driveAttribute.getDescription().contains("端口选择")) {
			for (int i = 1; i < 11; i++) {
				if (("端口选择" + i).equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(groupSpreadObject.getChannelProt()
							.split(",")[i - 1]);
				}
			}
		}
		// 给“MAC地址”赋值
		if (driveAttribute.getDescription().contains("MAC地址")) {
			for (int i = 1; i < 7; i++) {
				if (("MAC地址" + i).equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(groupSpreadObject.getAddressMAC()
							.split("-")[i - 1]);
				}
			}

		}

	}
}
