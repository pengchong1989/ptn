package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.SecondMacStudyObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

/**
 * 时间同步配置块(1CH)
 * 
 * @author taopan
 * 
 */
public class AnalysisSecondMacTable extends AnalysisBase {

	/**
	 * 将SecondMacStudyObject转换为命令
	 * 
	 * @param timeSynchronizeObject
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisSecondMacStudyObjectToCommands(List<SecondMacStudyObject> secondMacStudyObjectList, String configXml) throws Exception {		
		
		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			driveRoot_config.getDriveAttributeList().get(0).getListRootList().clear();// 清除本身自带的一个ListRoot
			driveRoot_config.getDriveAttributeList().get(0).setValue(secondMacStudyObjectList.size() + "");// 条目数

			for (int i = 0; i < secondMacStudyObjectList.size(); i++) {
				DriveRoot driveRoot = super.LoadDriveXml(file);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot.getDriveAttributeList());
				for (int j = 0; j < listRoot.getDriveAttributeList().size(); j++) {
					secondMacObjectDriveAttribute(listRoot.getDriveAttributeList().get(j), secondMacStudyObjectList.get(i));
				}
				driveRoot_config.getDriveAttributeList().get(0).getListRootList().add(listRoot);
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

	/**
	 * 给secondMacStudyObject的DriveRoot对象赋值
	 * 
	 * @param driveAttribute
	 * @param secondMacStudyObject
	 * @throws Exception 
	 */
	private void secondMacObjectDriveAttribute(DriveAttribute driveAttribute, SecondMacStudyObject secondMacStudyObject) throws Exception {
		if ("条目ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(secondMacStudyObject.getNum()+ "");
		} else if ("vlanID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(secondMacStudyObject.getVlan() + "");
		} else if ("端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(secondMacStudyObject.getPortId() + "");
		} else if ("MAC地址数目".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(secondMacStudyObject.getMacNum()+ "");
			String file = driveAttribute.getListRootList().get(0).getFile();
			driveAttribute.getListRootList().clear();// 清除自带的listRoot
			for (int i = 0; i < secondMacStudyObject.getMacNum(); i++) {
				DriveRoot driveRoot = super.LoadDriveXml(file);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot.getDriveAttributeList());
				for (int j = 0; j < listRoot.getDriveAttributeList().size(); j++) {
					assignPTPPortDriveAttribute(listRoot.getDriveAttributeList().get(j), secondMacStudyObject.getMacAddressList().get(i));
				}
				driveAttribute.getListRootList().add(listRoot);
			}
		} 
	}

	/**
	 * 给PTPPortObject的DriveRoot对象赋值
	 * 
	 * @param driveAttribute
	 * @param macObject
	 */
	private void assignPTPPortDriveAttribute(DriveAttribute driveAttribute, String mac) {
		if ("MAC地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(Integer.parseInt(mac.split("-")[0], 16)+"");		
		}
		if ("MAC地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(Integer.parseInt(mac.split("-")[1], 16)+"");		
		}
		if ("MAC地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(Integer.parseInt(mac.split("-")[2], 16)+"");		
		}
		if ("MAC地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(Integer.parseInt(mac.split("-")[3], 16)+"");		
		}
		if ("MAC地址5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(Integer.parseInt(mac.split("-")[4], 16)+"");		
		}
		if ("MAC地址6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(Integer.parseInt(mac.split("-")[5], 16)+"");		
		}
	}

	public List<SecondMacStudyObject> analysisToCommandsToSecondMacStudyObject(byte[] commands,String configXml) throws Exception {
		CoderUtils.print16String(commands);
		List<SecondMacStudyObject> secondMacStudyObjList = new ArrayList<SecondMacStudyObject>();
		// 条目数
		byte[] Count = new byte[4];
		Count[0] = 0;
		Count[1] = 0;
		Count[2] = commands[0];
		Count[3] = commands[1];
		int count = CoderUtils.bytesToInt(Count);		
		int pointer = 2;
		for (int j = 0; j < count; j++) {
			try {
				SecondMacStudyObject obj = new SecondMacStudyObject();								
				//基本属性
				byte[] Num = new byte[4];
				Num[0] = 0;
				Num[1] = 0;
				Num[2] = commands[pointer];
				Num[3] = commands[pointer+1];
				obj.setNum(CoderUtils.bytesToInt(Num));	
				byte[] Vlan = new byte[4];
				Vlan[0] = 0;
				Vlan[1] = 0;
				Vlan[2] = commands[pointer+2];
				Vlan[3] = commands[pointer+3];
				obj.setVlan(CoderUtils.bytesToInt(Vlan));
				obj.setPortId(CoderUtils.bytesToInt(commands[pointer+4]));
				pointer+=10;
				//Mac地址配置
				String file_mac = "com/nms/drive/analysis/xmltool/file/二层静态MAC地址学习配置块(3fH)_sub_sub.xml";
				byte[] macNum = new byte[4];
				macNum[0] = 0;
				macNum[1] = 0;
				macNum[2] = commands[pointer];
				macNum[3] = commands[pointer+1];
				obj.setMacNum(CoderUtils.bytesToInt(macNum));	
				pointer += 2;
				setMacAttribute(commands,pointer, file_mac, obj.getMacNum(), obj);
				pointer +=6*obj.getMacNum();
				secondMacStudyObjList.add(obj);
			} catch (Exception e) {
				throw e;
			}
		}
		return  secondMacStudyObjList;
	}
	
	private void setMacAttribute(byte[] commands,int pointer,String file,int count,SecondMacStudyObject obj){
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		String macc=null;
		List<String> macAddressList = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			byte[] macs = new byte[6];	
			System.arraycopy(commands, pointer+6*i, macs, 0, macs.length);
			analysisCommandByDriveXml.setCommands(macs);
			DriveRoot driveRoot;
			try {
				driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(file);
				DriveAttribute driveAttribute = null;										
				for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
					driveAttribute = driveRoot.getDriveAttributeList().get(j);
					if(driveAttribute.getDescription().equals("MAC地址1")){
						macc=CoderUtils.synchTransformMac(driveAttribute.getValue());				
					}
					if(driveAttribute.getDescription().equals("MAC地址2")){
						macc=macc+"-"+CoderUtils.synchTransformMac(driveAttribute.getValue());
					}
					if(driveAttribute.getDescription().equals("MAC地址3")){
						macc=macc+"-"+CoderUtils.synchTransformMac(driveAttribute.getValue());
					}
                    if(driveAttribute.getDescription().equals("MAC地址4")){
                    	macc=macc+"-"+CoderUtils.synchTransformMac(driveAttribute.getValue());
					}
                    if(driveAttribute.getDescription().equals("MAC地址5")){
                    	macc=macc+"-"+CoderUtils.synchTransformMac(driveAttribute.getValue());
					}
                    if(driveAttribute.getDescription().equals("MAC地址6")){
                    	macc=macc+"-"+CoderUtils.synchTransformMac(driveAttribute.getValue());
					}
                   
                   
				}
				 macAddressList.add(macc);								
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
		}
		    
		obj.setMacAddressList(macAddressList);
	}
	
	public static void main(String[] args) {
		List<SecondMacStudyObject> secondMacStudyObjectList = new ArrayList<SecondMacStudyObject>();
		for (int i = 0; i < 3; i++) {
			SecondMacStudyObject secondMacStudyObject = new SecondMacStudyObject();
			List<String> list = new ArrayList<String>();
			list.add("00-11-aa-bb-cc-dd");
			list.add("00-11-aa-bb-cc-dd");
			list.add("00-11-aa-bb-cc-dd");
			secondMacStudyObject.setNum(i+1);
			secondMacStudyObject.setMacAddressList(list);
			secondMacStudyObject.setMacNum(list.size());
			secondMacStudyObjectList.add(secondMacStudyObject);
		}
		AnalysisSecondMacTable analysisSecondMacTable = new AnalysisSecondMacTable();
		try {
			analysisSecondMacTable.analysisSecondMacStudyObjectToCommands(secondMacStudyObjectList, "com/nms/drive/analysis/xmltool/file/二层静态MAC地址学习配置块(3fH).xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
