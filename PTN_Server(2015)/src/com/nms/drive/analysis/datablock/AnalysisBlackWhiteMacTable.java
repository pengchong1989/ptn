package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.BlackWhiteMacObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisBlackWhiteMacTable extends AnalysisBase{

	
	/**
	 * 根据对象生成字节数组
	 * 
	 * @param tunnelObject对象
	 * @param configXml配置XML
	 * @return 命令
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(List<BlackWhiteMacObject> blackWhiteMacObjectList, String configXml) throws Exception {
		
		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			driveRoot_config.getDriveAttributeList().get(0).getListRootList().clear();// 清除本身自带的一个ListRoot
			driveRoot_config.getDriveAttributeList().get(0).setValue(blackWhiteMacObjectList.size() + "");// 条目数

			for (int j = 0; j < blackWhiteMacObjectList.size(); j++) {

				DriveRoot driveRoot_config_1 = super.LoadDriveXml(file);
				ListRoot listroot = new ListRoot();
				listroot.setDriveAttributeList(driveRoot_config_1.getDriveAttributeList());
				// 将 BlackWhiteMacObject 对象信息 赋值到 driveRoot_config 对象中
				for (int i = 0; i < driveRoot_config_1.getDriveAttributeList().size(); i++) {
					DriveAttribute driveAttribute = listroot.getDriveAttributeList().get(i);
					// 属性赋值
					ObjectToDriveAttribute(blackWhiteMacObjectList.get(j), driveAttribute,j+1);
				}
				driveRoot_config.getDriveAttributeList().get(0).getListRootList().add(listroot);
			}
			// 将集合转换成命令
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
			CoderUtils.print16String(dataCommand);
			return dataCommand;

		} catch (Exception e) {
			throw e;
		}
	}

	private void ObjectToDriveAttribute(BlackWhiteMacObject blackWhiteMacObject, DriveAttribute driveAttribute,int length) throws Exception{
		try {
			if("条目".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(length+"");
			}
			if ("Vsid".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(blackWhiteMacObject.getVsId()+"");
			}
			if ("名单模式".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(blackWhiteMacObject.getNameList()+"");
			}if ("黑名单MAC1".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(blackWhiteMacObject.getMacAddress().split("-")[0]);
			}if ("黑名单MAC2".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(blackWhiteMacObject.getMacAddress().split("-")[1]);
			}if ("黑名单MAC3".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(blackWhiteMacObject.getMacAddress().split("-")[2]);
			}if ("黑名单MAC4".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(blackWhiteMacObject.getMacAddress().split("-")[3]);
			}if ("黑名单MAC5".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(blackWhiteMacObject.getMacAddress().split("-")[4]);
			}if ("黑名单MAC6".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(blackWhiteMacObject.getMacAddress().split("-")[5]);
			}
		} catch (Exception e) {
			throw e;
		}				
	}
	
	
	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return BlackWhiteMacObject
	 */
	public List<BlackWhiteMacObject> analysisToCommandsToBlackWhiteMacManageObject(byte[] commands, String configXml) throws Exception {
		List<BlackWhiteMacObject> blackWhiteMacObjectList = new ArrayList<BlackWhiteMacObject>();	
		// 条目数
		byte[] Count = new byte[4];
		Count[0] = 0;
		Count[1] = 0;
		Count[2] = commands[0];
		Count[3] = commands[1];
		int count = CoderUtils.bytesToInt(Count);		
		int pointer = 2;
		
		for (int j = 0; j < count; j++) {
			BlackWhiteMacObject obj = new BlackWhiteMacObject();
			String file_mac = "com/nms/drive/analysis/xmltool/file/MAC地址的黑白名单(24H)_sub.xml";
			setMacAttribute(commands,pointer, file_mac,obj);
			pointer+=10;
			blackWhiteMacObjectList.add(obj);
		}
		return  blackWhiteMacObjectList;
	}

	private void setMacAttribute(byte[] commands,int pointer,String file,BlackWhiteMacObject obj){
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			String macc=null;		
			byte[] macs = new byte[10];	
			System.arraycopy(commands, pointer, macs, 0, macs.length);
			analysisCommandByDriveXml.setCommands(macs);
			DriveRoot driveRoot;
			try {
				driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(file);
				DriveAttribute driveAttribute = null;										
				for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
					driveAttribute = driveRoot.getDriveAttributeList().get(j);
					if(driveAttribute.getDescription().equals("Vsid")){
						obj.setVsId(Integer.parseInt(driveAttribute.getValue()));			
					}
					if(driveAttribute.getDescription().equals("名单模式")){
						obj.setNameList(Integer.parseInt(driveAttribute.getValue()));			
					}
					if(driveAttribute.getDescription().equals("黑名单MAC1")){
						macc=CoderUtils.synchTransformMac(driveAttribute.getValue());				
					}
					if(driveAttribute.getDescription().equals("黑名单MAC2")){
						macc=macc+"-"+CoderUtils.synchTransformMac(driveAttribute.getValue());
					}
					if(driveAttribute.getDescription().equals("黑名单MAC3")){
						macc=macc+"-"+CoderUtils.synchTransformMac(driveAttribute.getValue());
					}
	                if(driveAttribute.getDescription().equals("黑名单MAC4")){
	                	macc=macc+"-"+CoderUtils.synchTransformMac(driveAttribute.getValue());
					}
	                if(driveAttribute.getDescription().equals("黑名单MAC5")){
	                	macc=macc+"-"+CoderUtils.synchTransformMac(driveAttribute.getValue());
					}
	                if(driveAttribute.getDescription().equals("黑名单MAC6")){
	                	macc=macc+"-"+CoderUtils.synchTransformMac(driveAttribute.getValue());
	                	obj.setMacAddress(macc);
					}
	               
	               
				}
								
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}		    			
	}

}
