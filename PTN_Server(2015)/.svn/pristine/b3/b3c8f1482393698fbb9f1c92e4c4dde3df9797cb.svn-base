package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.Port2LayerObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisPort2LayerAttrTable extends AnalysisBase {
	/**
	 * 根据对象生成字节数组
	 * @param configXml配置XML
	 * @return 命令
	 * @throws Exception 
	 */
	public byte[] analysisObjectToCommands(List<Port2LayerObject> portList, String configXml) {
		DriveRoot driveRoot_config;
		byte[] dataCommands = null;
		try {
			driveRoot_config = super.LoadDriveXml(configXml);
			String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			driveRoot_config.getDriveAttributeList().get(0).getListRootList().clear();// 清除本身自带的一个ListRoot
			driveRoot_config.getDriveAttributeList().get(0).setValue(portList.size() + "");// 条目数
			for (int j = 0; j < portList.size(); j++) {// 按条目数循环
				DriveRoot driveRoot_config_1 = super.LoadDriveXml(file);
				ListRoot listroot = new ListRoot();
				listroot.setDriveAttributeList(driveRoot_config_1.getDriveAttributeList());
				for (int i = 0; i < driveRoot_config_1.getDriveAttributeList().size(); i++) {// 按xml元素循环
					DriveAttribute driveAttribute = driveRoot_config_1.getDriveAttributeList().get(i);
					//赋值
					objectTODriveAttribute(portList.get(j), driveAttribute);
				}
				driveRoot_config.getDriveAttributeList().get(0).getListRootList().add(listroot);
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			dataCommands = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
		} catch (Exception e) {
			e.printStackTrace();
		}
		CoderUtils.print16String(dataCommands);
		return dataCommands;
	}

	private void objectTODriveAttribute(Port2LayerObject port2LayerObject, DriveAttribute driveAttribute) throws Exception {
		if(driveAttribute.getDescription().equals("端口号")){
			driveAttribute.setValue(port2LayerObject.getPortNumber()+"");
		}else if(driveAttribute.getDescription().equals("MAC地址学习使能")){
			driveAttribute.setValue(port2LayerObject.getMacEnable()+"");
		}else if(driveAttribute.getDescription().equals("MAC地址学习条目数")){
			driveAttribute.setValue(port2LayerObject.getMacCount()+"");
		}else if(driveAttribute.getDescription().equals("TPID")){
			driveAttribute.setValue(port2LayerObject.getTpIdType()+"");
		}else if(driveAttribute.getDescription().equals("端口类型")){
			driveAttribute.setValue(port2LayerObject.getPortType()+"");
		}else if(driveAttribute.getDescription().equals("PVID")){
			driveAttribute.setValue(port2LayerObject.getPvid()+"");
		}else if(driveAttribute.getDescription().equals("QINQ使能")){
			driveAttribute.setValue(port2LayerObject.getQinqEnable()+"");
		}else if(driveAttribute.getDescription().equals("所加入VLAN的数目")){
			driveAttribute.setValue(port2LayerObject.getVlanCount()+"");
			setVlanObject(driveAttribute, port2LayerObject.getVlanCount(), port2LayerObject.getVlans().split(","));
		}else if(driveAttribute.getDescription().equals("QINQ模式")){
			driveAttribute.setValue(port2LayerObject.getQinqModel()+"");
		}else if(driveAttribute.getDescription().equals("QINQ配置数目")){
			driveAttribute.setValue(port2LayerObject.getQinqCount()+"");
			setQinqObject(driveAttribute, port2LayerObject.getQinqCount(), port2LayerObject.getQinqs().split(","));
		}
	}
	
	private void setVlanObject(DriveAttribute driveAttribute,int count,String[] vlans) {
		List<ListRoot> listroots = new ArrayList<ListRoot>();
		try {
			String file = driveAttribute.getListRootList().get(0).getFile();
			driveAttribute.getListRootList().clear();
			for (int i = 0; i < count; i++) {
				DriveRoot driveRoot_config = super.LoadDriveXml(file);
				ListRoot listroot = new ListRoot();
				listroot.setDriveAttributeList(driveRoot_config.getDriveAttributeList());
				for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {// 按xml元素循环
					DriveAttribute driveAttribute_vlan = driveRoot_config.getDriveAttributeList().get(j);
					//赋值
					if(driveAttribute_vlan.getDescription().equals("VLAN ID")){
						driveAttribute_vlan.setValue(vlans[i].split(":")[0]);
					}else if(driveAttribute_vlan.getDescription().equals("VLAN TAG")){
						driveAttribute_vlan.setValue(vlans[i].split(":")[1].equals("TAG")?"0":"1");
					}
				}
				listroots.add(listroot);
			}
			driveAttribute.setListRootList(listroots);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void setQinqObject(DriveAttribute driveAttribute,int count,String[] qinqs) {
		List<ListRoot> listroots = new ArrayList<ListRoot>();
		try {
			String file = driveAttribute.getListRootList().get(0).getFile();
			driveAttribute.getListRootList().clear();
			for (int i = 0; i < count; i++) {
				DriveRoot driveRoot_config = super.LoadDriveXml(file);
				ListRoot listroot = new ListRoot();
				listroot.setDriveAttributeList(driveRoot_config.getDriveAttributeList());
				for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {// 按xml元素循环
					DriveAttribute driveAttribute_vlan = driveRoot_config.getDriveAttributeList().get(j);
					//赋值
					if(driveAttribute_vlan.getDescription().equals("内层VLAN上限")){
						driveAttribute_vlan.setValue(qinqs[i].split(":")[0]);
					}else if(driveAttribute_vlan.getDescription().equals("内层VLAN下限")){
						driveAttribute_vlan.setValue(qinqs[i].split(":")[1]);
					}else if(driveAttribute_vlan.getDescription().equals("外层VLAN")){
						driveAttribute_vlan.setValue(qinqs[i].split(":")[2]);
					}
				}
				listroots.add(listroot);
			}
			driveAttribute.setListRootList(listroots);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 解析命令为对象
	 */
	public List<Port2LayerObject> analysisCommandToObject(byte[] commands, String configXml) throws Exception {
		CoderUtils.print16String(commands);
		List<Port2LayerObject> portObjList = new ArrayList<Port2LayerObject>();
		// 条目数
		int count = CoderUtils.bytesToInt(commands[0]);
		int pointer = 1;
		for (int j = 0; j < count; j++) {
			try {
				Port2LayerObject obj = new Port2LayerObject();
				
				//基本属性
				obj.setPortNumber(CoderUtils.bytesToInt(commands[pointer]));
				obj.setMacEnable(CoderUtils.bytesToInt(commands[pointer+1]));
				byte[] macCount = new byte[4];
				macCount[0] = 0;
				macCount[1] = 0;
				macCount[2] = commands[pointer+2];
				macCount[3] = commands[pointer+3];
				
				
				
				obj.setMacCount(CoderUtils.bytesToInt(macCount));
				obj.setTpIdType(CoderUtils.bytesToInt(commands[pointer+4]));
				obj.setPortType(CoderUtils.bytesToInt(commands[pointer+5]));
				byte[] pvid = new byte[4];
				pvid[0] = 0;
				pvid[1] = 0;
				pvid[2] = commands[pointer+6];
				pvid[3] = commands[pointer+7];
				obj.setPvid(CoderUtils.bytesToInt(pvid));
				obj.setQinqEnable(CoderUtils.bytesToInt(commands[pointer+8]));
				pointer += 20;
				
				//vlan属性
				String file_vlan = "com/nms/drive/analysis/xmltool/file/端口二层属性(3eH)_vlan_sub.xml";
				byte[] vlanCountByte = new byte[4];
				vlanCountByte[0] = 0;
				vlanCountByte[1] = 0;
				vlanCountByte[2] = commands[pointer];
				vlanCountByte[3] = commands[pointer+1];
				obj.setVlanCount(CoderUtils.bytesToInt(vlanCountByte));
				pointer += 2;
				setVlansAttribute(commands,pointer, file_vlan, obj.getVlanCount(), obj);
				pointer += 11*obj.getVlanCount();
				
				
				obj.setQinqModel(CoderUtils.bytesToInt(commands[pointer]));
				pointer += 5;
				
				//qinq属性
				String file_qinq = "com/nms/drive/analysis/xmltool/file/端口二层属性(3eH)_qinq_sub.xml";
				obj.setQinqCount(CoderUtils.bytesToInt(commands[pointer]));
				pointer += 1;
				setQinqsAttribute(commands,pointer, file_qinq, obj.getQinqCount(), obj);
				pointer += 10*obj.getQinqCount();
				
				portObjList.add(obj);
			} catch (Exception e) {
				throw e;
			}
		}
		return portObjList;
	}
	
	private void setQinqsAttribute(byte[] commands, int pointer, String fileQinq, int qinqCount, Port2LayerObject obj) {
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		StringBuffer qinqstring = new StringBuffer();
		for (int i = 0; i < qinqCount; i++) {
			byte[] qinqs = new byte[10];
			System.arraycopy(commands, pointer+10*i, qinqs, 0, qinqs.length);
			analysisCommandByDriveXml.setCommands(qinqs);
			DriveRoot driveRoot;
			StringBuffer qinq = new StringBuffer();
			try {
				driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(fileQinq);
				DriveAttribute driveAttribute = null;
				for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
					driveAttribute = driveRoot.getDriveAttributeList().get(j);
					if(driveAttribute.getDescription().equals("内层VLAN上限")){
						qinq.append(driveAttribute.getValue()+":");
					}else if(driveAttribute.getDescription().equals("内层VLAN下限")){
						qinq.append(driveAttribute.getValue()+":");
					}else if(driveAttribute.getDescription().equals("外层VLAN")){
						qinq.append(driveAttribute.getValue()+":");
					}
				}
				qinqstring.append(qinq+",");
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
		}
		obj.setQinqs(qinqstring.toString());
		
	}

	private void setVlansAttribute(byte[] commands,int pointer,String file,int count,Port2LayerObject port2LayerObject){
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		StringBuffer vlanstring = new StringBuffer();
		for (int i = 0; i < count; i++) {
			byte[] vlans = new byte[11];
			System.arraycopy(commands, pointer+11*i, vlans, 0, vlans.length);
			analysisCommandByDriveXml.setCommands(vlans);
			DriveRoot driveRoot;
			StringBuffer vlan = new StringBuffer();
			try {
				driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(file);
				DriveAttribute driveAttribute = null;
				for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
					driveAttribute = driveRoot.getDriveAttributeList().get(j);
					if(driveAttribute.getDescription().equals("VLAN ID")){
						vlan.append(driveAttribute.getValue()+":");
					}else if(driveAttribute.getDescription().equals("VLAN TAG")){
						vlan.append(driveAttribute.getValue().equals("0")?"TAG":"UNTAG");
					}
				}
				vlanstring.append(vlan+",");
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
		}
		port2LayerObject.setVlans(vlanstring.toString());
	}
}
