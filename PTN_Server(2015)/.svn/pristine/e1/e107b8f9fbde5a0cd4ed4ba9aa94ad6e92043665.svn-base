package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.IGMPSNOOPINGObject;
import com.nms.ui.manager.ExceptionManage;

/**
 * 解析   IGMP SNOOPING配置(17H)
 * @author 肖成
 *
 */
public class AnalysisIGMPSNOOPINGTable extends AnalysisBase{
	private int dataLength = 20;// 每个数据块的长度  
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int clauses = 2;// 条目数长度
	
	public byte[] analysisIGMPSNOOPINGObjectToCommands(List<IGMPSNOOPINGObject> iGMPSNOOPINGObjectList,String xmlPath){
		byte[] commands = null;
		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(xmlPath);
			// 获得 DriveRoot 下  的 DriveAttribute 下的 ListRoot 的路径
			String subPath = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			//清除原xml中的ListRoot
			driveRoot_config.getDriveAttributeList().get(0).getListRootList().clear();
			//给原xml 的 Attribute 的value 赋 新值
			driveRoot_config.getDriveAttributeList().get(0).setValue(iGMPSNOOPINGObjectList.size()+"");
			
			for(int i=0;i<iGMPSNOOPINGObjectList.size();i++){
				//获取subPath 路径下的 DriveRoot
				DriveRoot driveRoot = super.LoadDriveXml(subPath);
				List<DriveAttribute> driveAttributeList = driveRoot.getDriveAttributeList();
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveAttributeList);
				IGMPSNOOPINGObject iGMPSNOOPINGObject = iGMPSNOOPINGObjectList.get(i);
				for(int j=0;j<driveAttributeList.size();j++){
					DriveAttribute driveAttribute = driveAttributeList.get(j);
					iGMPSNOOPINGObjectToAttribute(iGMPSNOOPINGObject,driveAttribute);
				}
				driveRoot_config.getDriveAttributeList().get(0).getListRootList().add(listRoot);
			}
			// 将xml文件的值转成命令
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			commands = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
//		CoderUtils.print16String(commands);
		return commands;
	}
	
	/**
	 * 将对象的值 赋给XML的属性
	 * @param iGMPSNOOPINGObject  对象的值
	 * @param driveAttribute  xml的属性
	 */
	public void iGMPSNOOPINGObjectToAttribute(IGMPSNOOPINGObject iGMPSNOOPINGObject,DriveAttribute driveAttribute){
		if("条目ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(iGMPSNOOPINGObject.getItemID()+"");
		}
		
		if("VPN ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(iGMPSNOOPINGObject.getVpnID()+"");
		}
		
		if("IGMP SNOOPING".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(iGMPSNOOPINGObject.getIgmpSnooping()+"");
		}
	}
	
	/**
	 * 将命令转成对象
	 * @param commands  命令
	 * @param xmlPath  路径
	 * @return  对象集合
	 */
	public List<IGMPSNOOPINGObject> analysisCommandsToIGMPSNOOPINGObject(byte[] commands,String xmlPath){
		List<IGMPSNOOPINGObject> iGMPSNOOPINGObjectList = new ArrayList<IGMPSNOOPINGObject>();
		//数据块起始位置
		int start = NEhead + controlPanelHead + dataBlockHead + clauses;
		//数据块的数量
		int dataCount = (commands.length -NEhead - controlPanelHead - dataBlockHead)/dataLength;
		try {
			for(int i=0;i<dataCount;i++){
				byte[] data = new byte[dataLength];
				//给数据块字节数组赋值
				System.arraycopy(commands, start+start*i, data, 0, dataLength);
				//将命令的值赋给XML
				AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
				analysisCommandByDriveXml.setCommands(data);
				
				DriveRoot driveRoot_config = super.LoadDriveXml(xmlPath);
				String subPath = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
				
				DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(subPath);
				List<DriveAttribute> driveAttributeList = driveRoot.getDriveAttributeList();
				
				IGMPSNOOPINGObject iGMPSNOOPINGObject = new IGMPSNOOPINGObject();
				//将XML的值赋给对象
				for(int j=0;j<driveAttributeList.size();j++){
					DriveAttribute driveAttribute = driveAttributeList.get(j);
					attributeToIGMPSNOOPINGObject(driveAttribute,iGMPSNOOPINGObject);
				}
				iGMPSNOOPINGObjectList.add(iGMPSNOOPINGObject);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
		return iGMPSNOOPINGObjectList;
	}
	
	/**
	 * 将xml的值赋给对象
	 * @param driveAttribute
	 * @param iGMPSNOOPINGObject
	 */
	public void attributeToIGMPSNOOPINGObject(DriveAttribute driveAttribute,IGMPSNOOPINGObject iGMPSNOOPINGObject){
		if("条目ID".equals(driveAttribute.getDescription())){
			iGMPSNOOPINGObject.setItemID(Integer.parseInt(driveAttribute.getValue()));
		}
		
		if("VPN ID".equals(driveAttribute.getDescription())){
			iGMPSNOOPINGObject.setVpnID(Integer.parseInt(driveAttribute.getValue()));
		}
		
		if("IGMP SNOOPING".equals(driveAttribute.getDescription())){
			iGMPSNOOPINGObject.setIgmpSnooping(Integer.parseInt(driveAttribute.getValue()));
		}
	}
}
