package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.ETHLinkOAMObject;
import com.nms.drive.service.impl.CoderUtils;
/**
 * 解析 以太网链路OAM(1BH)
 * @author 罗磊
 *
 */
public class AnalysisETHLinkTable extends AnalysisBase{
	/**
	 * 解析以太网链路OAM对象 生成 命令
	 * @param ethLinkList  以太网链路OAM
	 * @param configCml
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(List<ETHLinkOAMObject> ethLinkList,String configXml)throws Exception{
		try {
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			String file = driveRoot.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			driveRoot.getDriveAttributeList().get(0).getListRootList().clear();
			driveRoot.getDriveAttributeList().get(0).setValue(ethLinkList.size()+"");
			// 按条目数循环
			for(int i = 0; i < ethLinkList.size(); i++){
				DriveRoot driveRoot1 = super.LoadDriveXml(file);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot1.getDriveAttributeList());
				//对象信息 赋值到 driveRoot 对象中
				for(int j = 0; j <driveRoot1.getDriveAttributeList().size(); j++){
					DriveAttribute driveAttribute = driveRoot1.getDriveAttributeList().get(j);
					//赋值
					objectToDriveAttribute(ethLinkList.get(i),driveAttribute);
				}
				driveRoot.getDriveAttributeList().get(0).getListRootList().add(listRoot);
			}
			byte[] dataCommand = new AnalysisDriveXmlToCommand().analysisCommands(driveRoot);
			CoderUtils.print16String(dataCommand);
			return dataCommand;
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 解析 命令 生成 以太网链路OAM 对象
	 * @param command
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<ETHLinkOAMObject> analysisCommandToObject(byte[] command,String configXml)throws Exception{
		try {
			
			List<ETHLinkOAMObject> ethLinkList = new ArrayList<ETHLinkOAMObject>();
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			String file = driveRoot.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			int dataCount = 26;
			int count = (command.length  - 1-11) / dataCount; 
			for(int i = 0; i < count; i++){
				byte[] a = new byte[dataCount+11];
				System.arraycopy(command, 1 + i*dataCount, a, 0, a.length);
				AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
				analysisCommandByDriveXml.setCommands(a);
				DriveRoot driveRoot1 = analysisCommandByDriveXml.analysisDriveAttrbute(file);
				ETHLinkOAMObject ethLinkObject = new ETHLinkOAMObject();
			
				for(int j = 0; j < driveRoot1.getDriveAttributeList().size(); j++){
					DriveAttribute driveAttribute = driveRoot1.getDriveAttributeList().get(j);
					driveAttributeToObject(ethLinkObject,driveAttribute);
				}
				ethLinkList.add(ethLinkObject);
			}
			return ethLinkList;
		} catch (Exception e) {
			throw e;
		}
		
	}
	//赋值
	public void objectToDriveAttribute(ETHLinkOAMObject ethLinkObject,DriveAttribute driveAttribute)throws Exception{
		if("条目id".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getCout()+"");
		}
		else if("端口号".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getPort() +"");
		}
		else if("OAM使能".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getOam() +"");
		}
		else if("OAM模式".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getOamModel() +"");
		}
		else if("环回使能".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getLoopBack() +"");
		}
		else if("环回请求超时时间".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getLoopBackTime() +"");
		}
		else if("环回请求回应次数".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getLoopBackNumber() +"");
		}
		else if("对端环回".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getOppositeLoop() +"");
		}
		else if("对端变量获取".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getOppositeVariablel() +"");
		}
		else if("Dying gasp使能".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getDyingGasp() +"");
		}
		else if("本端critical事件使能".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getCritical() +"");
		}
		else if("错误符号事件周期".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getErrorSignCycle() +"");
		}
		else if("错误符号事件门限".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getErrorSignThreshold() +"");
		}
		else if("错误帧事件周期".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getErrorFrameEventCycle() +"");
		}
		else if("错误帧事件门限".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getErrorFrameEventThreshold() +"");
		}
		else if("错误帧周期事件周期".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getErrorFrameCylceEventCycle() +"");
		}
		else if("错误帧周期事件门限".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getErrorFrameCycleEventThreshold() +"");
		}
		else if("错误帧秒事件周期".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getErrorFrameSecondEventCycle() +"");
		}
		else if("错误帧秒事件门限".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getErrorFrameSecondEventThreshold() +"");
		}else if("每秒发送oam帧 上限".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(ethLinkObject.getMaxFrame() +"");
		}
	}
	//赋值
	public void driveAttributeToObject(ETHLinkOAMObject ethLinkObject,DriveAttribute driveAttribute)throws Exception{
		if("条目id".equals(driveAttribute.getDescription())){
			ethLinkObject.setCout(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("端口号".equals(driveAttribute.getDescription())){
			ethLinkObject.setPort(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("OAM使能".equals(driveAttribute.getDescription())){
			ethLinkObject.setOam(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("OAM模式".equals(driveAttribute.getDescription())){
			ethLinkObject.setOamModel(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("环回使能".equals(driveAttribute.getDescription())){
			ethLinkObject.setLoopBack(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("环回请求超时时间".equals(driveAttribute.getDescription())){
			ethLinkObject.setLoopBackTime(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("环回请求回应次数".equals(driveAttribute.getDescription())){
			ethLinkObject.setLoopBackNumber(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("对端环回".equals(driveAttribute.getDescription())){
			ethLinkObject.setOppositeLoop(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("对端变量获取".equals(driveAttribute.getDescription())){
			ethLinkObject.setOppositeVariablel(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("Dying gasp使能".equals(driveAttribute.getDescription())){
			ethLinkObject.setDyingGasp(Integer.parseInt(driveAttribute.getValue()));
		}
		else if("本端critical事件使能".equals(driveAttribute.getDescription())){
			ethLinkObject.setCritical(Integer.parseInt(driveAttribute.getValue()));
		}
		if("错误符号事件周期".equals(driveAttribute.getDescription())){
			ethLinkObject.setErrorSignCycle(Integer.parseInt(driveAttribute.getValue()));
		}
		if("错误符号事件门限".equals(driveAttribute.getDescription())){
			ethLinkObject.setErrorSignThreshold(Integer.parseInt(driveAttribute.getValue()));
		}
		if("错误帧事件周期".equals(driveAttribute.getDescription())){
			ethLinkObject.setErrorFrameEventCycle(Integer.parseInt(driveAttribute.getValue()));
		}
		if("错误帧事件门限".equals(driveAttribute.getDescription())){
			ethLinkObject.setErrorFrameEventThreshold(Integer.parseInt(driveAttribute.getValue()));
		}
		if("错误帧周期事件周期".equals(driveAttribute.getDescription())){
			ethLinkObject.setErrorFrameCylceEventCycle(Integer.parseInt(driveAttribute.getValue()));
		}
		if("错误帧周期事件门限".equals(driveAttribute.getDescription())){
			ethLinkObject.setErrorFrameCycleEventThreshold(Integer.parseInt(driveAttribute.getValue()));
		}
		if("错误帧秒事件周期".equals(driveAttribute.getDescription())){
			ethLinkObject.setErrorFrameSecondEventCycle(Integer.parseInt(driveAttribute.getValue()));
		}
		if("错误帧秒事件门限".equals(driveAttribute.getDescription())){
			ethLinkObject.setErrorFrameSecondEventThreshold(Integer.parseInt(driveAttribute.getValue()));
		} 
		if("每秒发送oam帧 上限".equals(driveAttribute.getDescription())){
			ethLinkObject.setMaxFrame(Integer.parseInt(driveAttribute.getValue()));
		}                 
	}
}
