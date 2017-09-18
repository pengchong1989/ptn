package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.RoundProtectionObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;
/**
 * 解析   环网保护
 * @author 肖成
 *
 */
public class AnalysisRoundProtectionTable extends AnalysisBase{
	private int dataLength = 20;// 每个数据块的长度  
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int clauses = 1;// 条目数长度
	
	/**
	 * 
	 * @param roundProtectionObjcetList   对象集合
	 * @param xmlpath  xml 文件路径
	 * @return  命令
	 */
	public byte[] analysisRoundProtectionObjcetToCommands(List<RoundProtectionObject> roundProtectionObjcetList ,String xmlpath){
		//返回的命令
		byte[] commands = null;
		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(xmlpath);
			String path = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			driveRoot_config.getDriveAttributeList().get(0).getListRootList().clear();
			driveRoot_config.getDriveAttributeList().get(0).setValue(roundProtectionObjcetList.size()+"");
			
			for(int j=0;j<roundProtectionObjcetList.size();j++){
				// 解析xml文件   得到根元素  DriveRoot
				DriveRoot driveRoot = super.LoadDriveXml(path);
				// 得到DriveRoot 下的所有  DriveRootAttribute
				List<DriveAttribute> driveAttributeList = driveRoot.getDriveAttributeList();
				
				RoundProtectionObject roundProtectionObject = roundProtectionObjcetList.get(j);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot.getDriveAttributeList());
				
				for(int k=0;k<driveAttributeList.size();k++){
					DriveAttribute driveAttribute = driveAttributeList.get(k);
					//调用  赋值 方法  将对象的值赋给 xml
					roundProtectionTODriveAttribute(roundProtectionObject,driveAttribute);
				}
				driveRoot_config.getDriveAttributeList().get(0).getListRootList().add(listRoot);
			}
			// 将xml文件的值转成命令
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			commands = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return commands;
	}
	
	
	
	
	/**
	 * 将对象的值 赋给xml
	 * @param roundProtectionObject
	 * @param driveAttribute
	 */
	public void roundProtectionTODriveAttribute(RoundProtectionObject roundProtectionObject ,DriveAttribute driveAttribute){
		if("保护类型".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getProtectType()+"");
		}
	
		if("西向槽位".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getWestSlot()+"");
		}
		
		if("西向端口".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getWestProt()+"");
		}
	
		if("东向端口".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getEastPort()+"");
		}
		
		if("东向槽位".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getEastSlot()+"");
		}
	
		if("环网节点总数".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getNodeCount()+"");
		}
		
		if("本站节点ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getNodeID()+"");
		}
	
		if("逻辑环ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getLogicRoundID()+"");
		}
		
		if("拖延时间".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getDelayTime()+"");
		}
	
		if("返回类型".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getReturnType()+"");
		}
		
		if("环网ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getRoundID()+"");
		}
		if("目的节点id".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getTargetNodeId()+"");
		}
		if("西向lspID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getWestLspId()+"");
		}
		if("东向lspID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getEastLspId()+"");
		}
		if("等待恢复时间".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(roundProtectionObject.getWaitTime()+"");
		}
	}
	
	
	/**
	 * 将命令转成对象
	 * @param commands 命令
	 * @param xmlPath  XML路径
	 * @return  对象集合
	 */
	public List<RoundProtectionObject> analysisCommandToRoundProtectionObject(byte[] commands,String xmlPath){
		List<RoundProtectionObject> roundProtectionObjectList = new ArrayList<RoundProtectionObject>();
		//数据块起始位置
		int start = 0;
		//数据块的数量
		int dataCount = (commands.length -NEhead - controlPanelHead - dataBlockHead)/dataLength;
		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(xmlPath);
			String path = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			
			//循环每个数据块  将数据块的值赋值给  对象
			for(int i=0;i<dataCount;i++){
				byte[] data = new byte[dataLength];
				//
				System.arraycopy(commands,start*i+start, data, 0, dataLength);
				//给xml文件中的属性赋值
				AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
				analysisCommandByDriveXml.setCommands(data);
				//解析xml文件   将xml文件的值赋给对象
				DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(path);
				List<DriveAttribute> driveAttributeList = driveRoot.getDriveAttributeList();
				
				RoundProtectionObject roundProtectionObject = new RoundProtectionObject();
				for(int j=0;j<driveAttributeList.size();j++){
					DriveAttribute driveAttribute = driveAttributeList.get(j);
					driveAttributeTODriveAttributeRoundProtection(roundProtectionObject ,driveAttribute);
				}
				//将对象加入对象集合中
				roundProtectionObjectList.add(roundProtectionObject);
			}
		} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
		}
		return roundProtectionObjectList;
	}
	
	
	/**
	 * 将 xml 中 Attribute的值赋给对象
	 * @param roundProtectionObject  对象
	 * @param driveAttribute
	 */
	public void driveAttributeTODriveAttributeRoundProtection(RoundProtectionObject roundProtectionObject ,DriveAttribute driveAttribute){
		if("保护类型".equals(driveAttribute.getDescription())){
			roundProtectionObject.setProtectType(Integer.parseInt(driveAttribute.getValue()));
		}
	
		if("西向槽位".equals(driveAttribute.getDescription())){
			roundProtectionObject.setWestSlot(Integer.parseInt(driveAttribute.getValue()));
		}
		
		if("西向端口".equals(driveAttribute.getDescription())){
			roundProtectionObject.setWestProt(Integer.parseInt(driveAttribute.getValue()));
		}
	
		if("东向端口".equals(driveAttribute.getDescription())){
			roundProtectionObject.setEastPort(Integer.parseInt(driveAttribute.getValue()));
		}
		
		if("东向槽位".equals(driveAttribute.getDescription())){
			roundProtectionObject.setEastSlot(Integer.parseInt(driveAttribute.getValue()));
		}
	
		if("环网节点总数".equals(driveAttribute.getDescription())){
			roundProtectionObject.setNodeCount(Integer.parseInt(driveAttribute.getValue()));
		}
		
		if("本站节点ID".equals(driveAttribute.getDescription())){
			roundProtectionObject.setNodeID(Integer.parseInt(driveAttribute.getValue()));
		}
	
		if("逻辑环ID".equals(driveAttribute.getDescription())){
			roundProtectionObject.setLogicRoundID(Integer.parseInt(driveAttribute.getValue()));
		}
		
		if("拖延时间".equals(driveAttribute.getDescription())){
			roundProtectionObject.setDelayTime(Integer.parseInt(driveAttribute.getValue()));
		}
	
		if("返回类型".equals(driveAttribute.getDescription())){
			roundProtectionObject.setReturnType(Integer.parseInt(driveAttribute.getValue()));
		}
		
		if("环网ID".equals(driveAttribute.getDescription())){
			roundProtectionObject.setRoundID(Integer.parseInt(driveAttribute.getValue()));
		}
		if("目的节点id".equals(driveAttribute.getDescription())){
			roundProtectionObject.setTargetNodeId(Integer.parseInt(driveAttribute.getValue()));
		}
		if("西向lspID".equals(driveAttribute.getDescription())){
			roundProtectionObject.setWestLspId(Integer.parseInt(driveAttribute.getValue()));
		}
		if("东向lspID".equals(driveAttribute.getDescription())){
			roundProtectionObject.setEastLspId(Integer.parseInt(driveAttribute.getValue()));
		}
		if("等待恢复时间".equals(driveAttribute.getDescription())){
			roundProtectionObject.setWaitTime(Integer.parseInt(driveAttribute.getValue()));
		}
	}


	
	public List<RoundProtectionObject> analysisCommandsToLoopProRotateObject(byte[] commands,String configXml) throws Exception {
		CoderUtils.print16String(commands);
		List<RoundProtectionObject> roundProtectionObjectList = new ArrayList<RoundProtectionObject>();
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
		// 起始长度
		int start = 1;
		// 条目数
		//int dataCount = (commands.length -NEhead - controlPanelHead - dataBlockHead)/dataLength;
		int count = (commands.length  - 11-1)/ dataLength;
		try {
			for (int i = 0; i < count; i++) {
				byte[] a = new byte[dataLength];
				System.arraycopy(commands, start + i * dataLength, a, 0,a.length);
				AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
				analysisCommandByDriveXml.setCommands(a);
				DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(file);
				RoundProtectionObject roundProtectionObject = new RoundProtectionObject();			
				for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
					DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(j);
					// 属性赋值
					driveAttributeTODriveAttributeRoundProtection(roundProtectionObject, driveAttribute);					
				}
				roundProtectionObjectList.add(roundProtectionObject);
				
			}
			
			
		} catch (Exception e) {
//			throw e;
			e.printStackTrace();
		}
		return roundProtectionObjectList;
	}
	
}
