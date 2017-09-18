package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.ProtObject;
import com.nms.drive.service.bean.UniObject;

/**
 * 
 * LAN口解析
 * @author 彭冲
 *
 */
public class AnalysisLANTable extends AnalysisBase {

	private int dataCount = 30;// 每个数据块的长度
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int reservecount = 60;// 备用字节长度

	/**
	 * 根据对象生成字节数组
	 * 
	 * @param lanObject对象
	 * @param configXml配置XML
	 * @return 命令
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(List<ProtObject> protObjectlist, String configXml) throws Exception {

		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			for (int i = 1; i < protObjectlist.size() + 1; i++) {
				UniObject uniObject = new UniObject();
				uniObject = protObjectlist.get(i - 1).getUniObject();
				for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {

					// 赋值
					LanToDriveAttribute(uniObject, driveRoot_config.getDriveAttributeList().get(j), i);
				}
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
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
	 * @return LanObject
	 */
	public List<ProtObject> analysisCommandToObject(byte[] commands, String configXml) throws Exception {
		List<ProtObject> protObjectList = new ArrayList<ProtObject>();
		ProtObject protObject = null;
		UniObject uniObject = null;
		List<UniObject> uniObjectObjectList = new ArrayList<UniObject>();

		// 起始长度
		int start = NEhead + controlPanelHead + dataBlockHead;

		// 端口数
		int count = (commands.length - NEhead - controlPanelHead - dataBlockHead - reservecount) / dataCount;
		for (int i = 1; i < count + 1; i++) {
			byte[] a = new byte[dataCount];
			System.arraycopy(commands, start + (i - 1) * dataCount, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			uniObject = new UniObject();
			DriveRoot driveRoot = new DriveRoot();
			try {
				driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);

				// 将 driveRoot 信息 赋值 pwObject 对象中
				for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
					// 赋值
					DriveAttributeToLan(uniObject, driveRoot.getDriveAttributeList().get(j));
				}
				protObject = new ProtObject();
				protObject.setUniObject(uniObject);
				protObjectList.add(protObject);
			} catch (Exception e) {
				throw e;
			}
		}
		
		return protObjectList;
	}

	/**
	 * UniObject值赋给driveAttribute
	 * 
	 * @param UniObject
	 * @param driveAttribute
	 */
	public void LanToDriveAttribute(UniObject uniObject, DriveAttribute driveattribute, int i) {

		// (LAN1)VLAN关联设置
		String str1 = "(LAN" + i + ")VLAN关联设置";
		if (str1.equals(driveattribute.getDescription())) {
			driveattribute.setValue(uniObject.getRelevanceVlan() + "");
		}

		// (LAN1)802.1P关联设置
		String str2 = "(LAN" + i + ")802.1P关联设置";
		if (str2.equals(driveattribute.getDescription())) {
			driveattribute.setValue(uniObject.getRelevance802_1P() + "");
		}

		// (LAN1)源MAC地址关联设置
		String str3 = "(LAN" + i + ")源MAC地址关联设置";
		if (str3.equals(driveattribute.getDescription())) {
			driveattribute.setValue(uniObject.getRelevanceSourceMac() + "");
		}

		// (LAN1)目的MAC地址关联设置
		String str4 = "(LAN" + i + ")目的MAC地址关联设置";
		if (str4.equals(driveattribute.getDescription())) {
			driveattribute.setValue(uniObject.getRelevanceTargetMac() + "");
		}

		// (LAN1)源IP地址关联设置
		String str5 = "(LAN" + i + ")源IP地址关联设置";
		if (str5.equals(driveattribute.getDescription())) {
			driveattribute.setValue(uniObject.getRelevanceSourceIP() + "");
		}

		// (LAN1)目的IP地址关联设置
		String str6 = "(LAN" + i + ")目的IP地址关联设置";
		if (str6.equals(driveattribute.getDescription())) {
			driveattribute.setValue(uniObject.getRelevanceTargetIP() + "");
		}

		// (LAN1)PW关联设置
		String str7 = "(LAN" + i + ")PW关联设置";
		if (str7.equals(driveattribute.getDescription())) {
			driveattribute.setValue(uniObject.getRelevancePw() + "");
		}

		// (LAN1)DSCP关联设置
		String str8 = "(LAN" + i + ")DSCP关联设置";
		if (str8.equals(driveattribute.getDescription())) {
			driveattribute.setValue(uniObject.getRelevanceDSCP() + "");
		}

		// (LAN1)端口使能
		String str9 = "(LAN" + i + ")端口使能";
		if (str9.equals(driveattribute.getDescription())) {
			driveattribute.setValue(uniObject.getPortEnable() + "");
		}

		// (LAN1)工作模式
		String str10 = "(LAN" + i + ")工作模式";
		if (str10.equals(driveattribute.getDescription())) {
			driveattribute.setValue(uniObject.getWorkPattern() + "");
		}

		// (LAN1)802.3流控
		String str11 = "(LAN" + i + ")802.3流控";
		if (str11.equals(driveattribute.getDescription())) {
			driveattribute.setValue(uniObject.getBuffer802_3Enable() + "");
		}

		// (LAN1)MTU
		String str12 = "(LAN" + i + ")MTU";
		if (str12.equals(driveattribute.getDescription())) {
			driveattribute.setValue(uniObject.getMtu() + "");
		}
	}

	/**
	 * driveAttribute值赋给UniObject
	 * 
	 * @param UniObject
	 * @param driveAttribute
	 */
	public void DriveAttributeToLan(UniObject uniObject, DriveAttribute driveattribute) {
		// (LAN1)VLAN关联设置
		String str1 = "(LAN1)VLAN关联设置";
		if (str1.equals(driveattribute.getDescription())) {
			uniObject.setRelevanceVlan(Integer.parseInt(driveattribute.getValue()));
		}

		// (LAN1)802.1P关联设置
		String str2 = "(LAN1)802.1P关联设置";
		if (str2.equals(driveattribute.getDescription())) {
			uniObject.setRelevance802_1P(Integer.parseInt(driveattribute.getValue()));
		}

		// (LAN1)源MAC地址关联设置
		String str3 = "(LAN1)源MAC地址关联设置";
		if (str3.equals(driveattribute.getDescription())) {
			uniObject.setRelevanceSourceMac(Integer.parseInt(driveattribute.getValue()));
		}

		// (LAN1)目的MAC地址关联设置
		String str4 = "(LAN1)目的MAC地址关联设置";
		if (str4.equals(driveattribute.getDescription())) {
			uniObject.setRelevanceTargetMac(Integer.parseInt(driveattribute.getValue()));
		}

		// (LAN1)源IP地址关联设置
		String str5 = "(LAN1)源IP地址关联设置";
		if (str5.equals(driveattribute.getDescription())) {
			uniObject.setRelevanceSourceIP(Integer.parseInt(driveattribute.getValue()));
		}

		// (LAN1)目的IP地址关联设置
		String str6 = "(LAN1)目的IP地址关联设置";
		if (str6.equals(driveattribute.getDescription())) {
			uniObject.setRelevanceTargetIP(Integer.parseInt(driveattribute.getValue()));
		}

		// (LAN1)PW关联设置
		String str7 = "(LAN1)PW关联设置";
		if (str7.equals(driveattribute.getDescription())) {
			uniObject.setRelevancePw(Integer.parseInt(driveattribute.getValue()));
		}

		// (LAN1)DSCP关联设置
		String str8 = "(LAN1)DSCP关联设置";
		if (str8.equals(driveattribute.getDescription())) {
			uniObject.setRelevanceDSCP(Integer.parseInt(driveattribute.getValue()));
		}

		// (LAN1)端口使能
		String str9 = "(LAN1)端口使能";
		if (str9.equals(driveattribute.getDescription())) {
			uniObject.setPortEnable(Integer.parseInt(driveattribute.getValue()));
		}

		// (LAN1)工作模式
		String str10 = "(LAN1)工作模式";
		if (str10.equals(driveattribute.getDescription())) {
			uniObject.setWorkPattern(Integer.parseInt(driveattribute.getValue()));
		}

		// (LAN1)802.3流控
		String str11 = "(LAN1)802.3流控";
		if (str11.equals(driveattribute.getDescription())) {
			uniObject.setBuffer802_3Enable(Integer.parseInt(driveattribute.getValue()));
		}

		// (LAN1)MTU
		String str12 = "(LAN1)MTU";
		if (str12.equals(driveattribute.getDescription())) {
			uniObject.setMtu(Integer.parseInt(driveattribute.getValue()));
		}
	}
}
