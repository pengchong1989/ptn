package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.MacManageObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

/**
 * mac管理1解析
 * @author Administrator
 *
 */
public class AnalysisMacTable extends AnalysisBase{
	/**
	 * 根据对象生成字节数组
	 */
	public byte[] analysisObjectToCommand(List<MacManageObject> macList, String configXml) throws Exception {

		try {
			DriveRoot driveRoot = super.LoadDriveXml(configXml);
			driveRoot.getDriveAttributeList().get(0).setValue(macList.size()+"");
			String file = driveRoot.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			driveRoot.getDriveAttributeList().get(0).getListRootList().clear();
			for (int i = 0; i < macList.size(); i++) {
				DriveRoot driveRoot_sub = super.LoadDriveXml(file);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot_sub.getDriveAttributeList());
				for (int j = 0; j < listRoot.getDriveAttributeList().size(); j++) {
					setValueToDriveAttribute(macList.get(i),listRoot.getDriveAttributeList().get(j));
				}
				driveRoot.getDriveAttributeList().get(0).getListRootList().add(listRoot);
			}	
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot);
			CoderUtils.print16String(dataCommand);
			return dataCommand;
		} catch (Exception e) {
			throw e;
		}
	}

	private void setValueToDriveAttribute(MacManageObject mac,DriveAttribute drive) {
		if(drive.getDescription().equals("端口号")){
			drive.setValue(mac.getPortId()+"");
		}				
		if(drive.getDescription().equals("Vlan值")){
			drive.setValue(mac.getVlanId()+"");
		}				
		if(drive.getDescription().equals("黑名单MAC1")){
			drive.setValue(mac.getMac().split("-")[0]);
		}
		if(drive.getDescription().equals("黑名单MAC2")){
			drive.setValue(mac.getMac().split("-")[1]);
		}
		if(drive.getDescription().equals("黑名单MAC3")){
			drive.setValue(mac.getMac().split("-")[2]);
		}
		if(drive.getDescription().equals("黑名单MAC4")){
			drive.setValue(mac.getMac().split("-")[3]);
		}
		if(drive.getDescription().equals("黑名单MAC5")){
			drive.setValue(mac.getMac().split("-")[4]);
		}
		if(drive.getDescription().equals("黑名单MAC6")){
			drive.setValue(mac.getMac().split("-")[5]);
		}
	}

	
	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return MacManageObject
	 */
	public List<MacManageObject> analysisToCommandsToMacManageObject(byte[] commands, String configXml) throws Exception {
		List<MacManageObject> macManageObjectList = new ArrayList<MacManageObject>();	
		// 条目数
		byte[] Count = new byte[4];
		Count[0] = 0;
		Count[1] = 0;
		Count[2] = commands[0];
		Count[3] = commands[1];
		int count = CoderUtils.bytesToInt(Count);		
		int pointer = 2;
		for (int j = 0; j < count; j++) {
			MacManageObject obj = new MacManageObject();
			String file_mac = "com/nms/drive/analysis/xmltool/file/MAC管理(23H)_sub.xml";
			setMacAttribute(commands,pointer, file_mac,obj);
			pointer+=10;
			macManageObjectList.add(obj);
		}
		return  macManageObjectList;
	}

	private void setMacAttribute(byte[] commands,int pointer,String file,MacManageObject obj){
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
				if(driveAttribute.getDescription().equals("Vlan值")){
					obj.setVlanId(Integer.parseInt(driveAttribute.getValue()));			
				}
				if(driveAttribute.getDescription().equals("端口号")){
					obj.setPortId(Integer.parseInt(driveAttribute.getValue()));			
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
                	obj.setMac(macc);
				}
               
               
			}
			 obj.setMac(macc);								
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}		    			
}

	
	
}