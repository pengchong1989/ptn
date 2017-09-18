package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.StaticUnicastObject;
import com.nms.drive.service.impl.CoderUtils;
/**
 * 解析 静态单播(0BH)
 * @author 闵何招
 *
 */
public class AnalysisStaticUnicast extends AnalysisBase {
	private int dataCount = 12;// 每个数据块的长度
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int clauses = 1;// 条目数长度

	/**
	 * 根据对象生成字节数组
	 * 
	 * @param List<StaticUnicastObject>
	 * @param configXml配置XML
	 * @return 命令
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(List<StaticUnicastObject> staticUnicastObjects, String configXml)throws Exception {
		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			// 清楚自带的listroot
			driveRoot_config.getDriveAttributeList().get(0).getListRootList().clear();
			// 设置条目数
			driveRoot_config.getDriveAttributeList().get(0).setValue(staticUnicastObjects.size() + "");
			// 按现条目数循环
			for (int i = 0; i < staticUnicastObjects.size(); i++) {
				String[] macAddress = staticUnicastObjects.get(i).getMacAddress().split("-");
				DriveRoot driveRoot_config_1 = super.LoadDriveXml(file);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot_config_1.getDriveAttributeList());
				for (int j = 0; j < driveRoot_config_1.getDriveAttributeList().size(); j++) {
					DriveAttribute driveAttribute = driveRoot_config_1.getDriveAttributeList().get(j);
					// 属性 赋值
					staticUnicastObjectToDriveAttribute(macAddress,staticUnicastObjects.get(i), driveAttribute);
				}
				driveRoot_config.getDriveAttributeList().get(0).getListRootList().add(listRoot);
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
			CoderUtils.print16String(dataCommand);
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
	 * @return List<StaticUnicastObject>
	 */
	public List<StaticUnicastObject> analysisCommandToObject(byte[] commands,
			String configXml) throws Exception {
		List<StaticUnicastObject> staticUnicastObjects = new ArrayList<StaticUnicastObject>();
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		String file = driveRoot_config.getDriveAttributeList().get(0)
				.getListRootList().get(0).getFile();
		// 起始长度
		int start = clauses;
		// 条目数
		int count = (commands.length - clauses)/ dataCount;
		try {
			for (int i = 0; i < count; i++) {
				byte[] a = new byte[dataCount];
				System.arraycopy(commands, start + i * dataCount, a, 0,a.length);
				AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
				analysisCommandByDriveXml.setCommands(a);
				DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(file);
				StaticUnicastObject staticUnicastObject = new StaticUnicastObject();
				// driveRoot赋值 LSPObject 对象中
				for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
					DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(j);
					// 属性赋值
					driveAttributeToStaticUnicastObject(staticUnicastObject,driveAttribute);

				}
				staticUnicastObjects.add(staticUnicastObject);
			}
		} catch (Exception e) {
			throw e;
		}
		return staticUnicastObjects;
	}

	public void staticUnicastObjectToDriveAttribute(String[] macAddress,StaticUnicastObject staticUnicastObject,DriveAttribute driveAttribute) {
		// SU ID 赋值
		if ("SU ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(staticUnicastObject.getSuId() + "");
		}

		// VPLS-VS 选择 赋值
		if ("VPLS-VS 选择".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(staticUnicastObject.getVplsVs() + "");
		}

		// 端口选择
		if ("端口选择".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(staticUnicastObject.getPortChoice() + "");
		}

		// MAC地址1
		if ("MAC地址1".equals(driveAttribute.getDescription())) {
			if(macAddress[0]!=null){
			driveAttribute.setValue(macAddress[0]);
			}
		}

		// MAC地址2
		if ("MAC地址2".equals(driveAttribute.getDescription())) {
			if(macAddress[1]!=null){
			driveAttribute.setValue(macAddress[1]);
			}
		}

		// MAC地址3
		if ("MAC地址3".equals(driveAttribute.getDescription())) {
			if(macAddress[2]!=null){
			driveAttribute.setValue(macAddress[2]);
			}
		}

		// MAC地址4
		if ("MAC地址4".equals(driveAttribute.getDescription())) {
			if(macAddress[3]!=null){
			driveAttribute.setValue(macAddress[3]);
			}
		}

		// MAC地址5
		if ("MAC地址5".equals(driveAttribute.getDescription())) {
			if(macAddress[4]!=null){
			driveAttribute.setValue(macAddress[4]);
			}
		}

		// MAC地址6
		if ("MAC地址6".equals(driveAttribute.getDescription())) {
			if(macAddress[5]!=null){
			driveAttribute.setValue(macAddress[5]);
			}
		}
	}

	public void driveAttributeToStaticUnicastObject(StaticUnicastObject staticUnicastObject,DriveAttribute driveAttribute) {
		// suId属性赋值
		if ("SU ID".equals(driveAttribute.getDescription())) {
			staticUnicastObject.setSuId(Integer.parseInt(driveAttribute.getValue()));
		}

		// vplsVs属性赋值
		if ("VPLS-VS 选择".equals(driveAttribute.getDescription())) {
			staticUnicastObject.setVplsVs(Integer.parseInt(driveAttribute.getValue()));
		}

		// protChoice属性赋值
		if ("端口选择".equals(driveAttribute.getDescription())) {
			staticUnicastObject.setPortChoice(Integer.parseInt(driveAttribute.getValue()));
		}

		// macAddress属性赋值
		if ("MAC地址1".equals(driveAttribute.getDescription())) {
			staticUnicastObject.setMacAddress(CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}

		if ("MAC地址2".equals(driveAttribute.getDescription())) {
			staticUnicastObject.setMacAddress(staticUnicastObject.getMacAddress()+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}

		if ("MAC地址3".equals(driveAttribute.getDescription())) {
			staticUnicastObject.setMacAddress(staticUnicastObject.getMacAddress()+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}

		if ("MAC地址4".equals(driveAttribute.getDescription())) {
			staticUnicastObject.setMacAddress(staticUnicastObject.getMacAddress()+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}

		if ("MAC地址5".equals(driveAttribute.getDescription())) {
			staticUnicastObject.setMacAddress(staticUnicastObject.getMacAddress()+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}

		if ("MAC地址6".equals(driveAttribute.getDescription())) {
			staticUnicastObject.setMacAddress(staticUnicastObject.getMacAddress()+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
	}

}
