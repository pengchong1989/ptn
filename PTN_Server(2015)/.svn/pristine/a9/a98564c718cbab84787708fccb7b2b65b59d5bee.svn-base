package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.QinQObject;

/**
 * qinq解析
 * @param dataCommand
 * @param configXml
 * @return
 */
public class AnalysisQinQTable extends AnalysisBase {

	private int dataCount = 20;// 每个数据块的长度
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int clauses = 2;// 条目数长度
	
	/**
	 * 根据对象生成字节数组
	 * 
	 * @param qinqObjectList对象
	 * @param configXml配置XML
	 * @return 命令
	 * @throws Exception 
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(List<QinQObject> qinqObjectList,String configXml) throws Exception {
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
		driveRoot_config.getDriveAttributeList().get(0).getListRootList().clear();// 清除本身自带的一个ListRoot
		driveRoot_config.getDriveAttributeList().get(0).setValue(qinqObjectList.size() + "");// 条目数

		for (int j = 0; j < qinqObjectList.size(); j++) {// 按条目数循环
			DriveRoot driveRoot_config_1 = super.LoadDriveXml(file);
			ListRoot listroot = new ListRoot();
			listroot.setDriveAttributeList(driveRoot_config_1.getDriveAttributeList());
			
			// 将 qinqObject 对象信息 赋值到 driveRoot_config 对象中
			for (int i = 0; i < driveRoot_config_1.getDriveAttributeList().size(); i++) {// 按xml元素循环
				DriveAttribute driveAttribute = driveRoot_config_1.getDriveAttributeList().get(i);
				//赋值
				qinqObjectTODriveAttribute(qinqObjectList.get(j), driveAttribute);
				
			}
			driveRoot_config.getDriveAttributeList().get(0).getListRootList().add(listroot);
		}

		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] dataCommands = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
		return dataCommands;
	}
	
	private void qinqObjectTODriveAttribute(QinQObject qinQObject,DriveAttribute driveAttribute) {
		// 类型基于内层VLAN标签
		if ("类型基于内层VLAN标签".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(qinQObject.getBasedInVlanId() + "");
		}
		//QinQ ID
		if("QinQ ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(qinQObject.getQinQId() +"");
		}
		//TPID
		if("TPID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(qinQObject.getTpId() +"");
		}
		//VLAN Port1端口号
		if("VLAN Port1端口号".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(qinQObject.getaPortId() +"");
		}
		//port1VLAN标签处理
		if("port1VLAN标签处理".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(qinQObject.getaPortVlanIdRule() +"");
		}
		//Port1匹配vlan标签下限
		if("Port1匹配vlan标签下限".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(qinQObject.getMinVlanId() +"");
		}
		//Port1匹配vlan标签上限
		if("Port1匹配vlan标签上限".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(qinQObject.getMaxVlanId() +"");
		}
		//VLAN Port2端口号
		if("VLAN Port2端口号".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(qinQObject.getzPortId() +"");
		}
		//port2VLAN标签处理
		if("port2VLAN标签处理".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(qinQObject.getzPortVlanIdRule() +"");
		}
		//VLAN ID
		if("VLAN ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(qinQObject.getVlanId() +"");
		}
		
	}

	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return qinqObject
	 * @throws Exception 
	 */
	public List<QinQObject> analysisCommandToObject(byte[] commands,String configXml) throws Exception {
		List<QinQObject> qinqObjectList = new ArrayList<QinQObject>();
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
		// 起始长度
		int start = clauses;

		// 条目数
		int count = (commands.length - clauses) / dataCount;
		for (int j = 0; j < count; j++) {

			byte[] a = new byte[dataCount];
			System.arraycopy(commands, start + j * dataCount, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);

			try {
				DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(file);
				QinQObject qinqObject = new QinQObject();
				// 将 driveRoot 信息 赋值 qinqObject 对象中
				for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {

					DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(i);
					
					//赋值
					DriveAttributeTOQinQObject(qinqObject, driveAttribute);
				}
				qinqObjectList.add(qinqObject);
			} catch (Exception e) {
				throw e;
			}
		}
		return qinqObjectList;
	}

	private void DriveAttributeTOQinQObject(QinQObject qinqObject,DriveAttribute driveAttribute) {
		//类型基于内层VLAN标签
		if("类型基于内层VLAN标签".equals(driveAttribute.getDescription())){
			qinqObject.setBasedInVlanId(Integer.parseInt(driveAttribute.getValue()));
		}
		//QinQ ID
		if("QinQ ID".equals(driveAttribute.getDescription())){
			qinqObject.setQinQId(Integer.parseInt(driveAttribute.getValue()));
		}
		//TPID
		if("TPID".equals(driveAttribute.getDescription())){
			qinqObject.setTpId(driveAttribute.getValue());
		}
		//VLAN Port1端口号
		if("VLAN Port1端口号".equals(driveAttribute.getDescription())){
			qinqObject.setaPortId(Integer.parseInt(driveAttribute.getValue()));
		}
		//port1VLAN标签处理
		if("port1VLAN标签处理".equals(driveAttribute.getDescription())){
			qinqObject.setaPortVlanIdRule(Integer.parseInt(driveAttribute.getValue()));
		}
		//Port1匹配vlan标签下限
		if("Port1匹配vlan标签下限".equals(driveAttribute.getDescription())){
			qinqObject.setMinVlanId(Integer.parseInt(driveAttribute.getValue()));
		}
		//Port1匹配vlan标签上限
		if("Port1匹配vlan标签上限".equals(driveAttribute.getDescription())){
			qinqObject.setMaxVlanId(Integer.parseInt(driveAttribute.getValue()));
		}
		//VLAN Port2端口号
		if("VLAN Port2端口号".equals(driveAttribute.getDescription())){
			qinqObject.setzPortId(Integer.parseInt(driveAttribute.getValue()));
		}
		//port2VLAN标签处理
		if("port2VLAN标签处理".equals(driveAttribute.getDescription())){
			qinqObject.setzPortVlanIdRule(Integer.parseInt(driveAttribute.getValue()));
		}
		//VLAN ID
		if("VLAN ID".equals(driveAttribute.getDescription())){
			qinqObject.setVlanId(Integer.parseInt(driveAttribute.getValue()));
		}
		
	}

}
