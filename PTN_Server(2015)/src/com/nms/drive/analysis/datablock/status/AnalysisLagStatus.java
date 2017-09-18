package com.nms.drive.analysis.datablock.status;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.LagPortObject;
import com.nms.drive.service.bean.status.LagStatusObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisLagStatus extends AnalysisBase{
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int filecount_sub = 9;// SUB.xml的长度
	private int filecount_sub_sub = 21;// SUB_SUB.xml的长度
	private int number = 2;// 条目的字节长度
	private int start = 3;   // 起始长度
	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return ELineObject
	 */
	public List<LagStatusObject> analysisCommandsToLagObject(byte[] commands, String configXml, String subXml) throws Exception {
		CoderUtils.print16String(commands);
		List<LagStatusObject> lagStatusObjectList = new ArrayList<LagStatusObject>();
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		String file_sub = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
		DriveRoot driveRoot_config_sub = super.LoadDriveXml(file_sub);
		
		// 条目数
		int count = (commands.length-start-number-24)/174;
		AnalysisCommandByDriveXml analysisCommandByDriveXml_sub = new AnalysisCommandByDriveXml();
		AnalysisCommandByDriveXml analysisCommandByDriveXml_sub_sub = new AnalysisCommandByDriveXml();
		byte[] a = new byte[filecount_sub];
		byte[] b = new byte[filecount_sub_sub];
		int length= 0;
		for (int i = 0; i < count; i++) {// 按条目数遍历

			LagStatusObject lagObject = new LagStatusObject();// lag对象
			
			System.arraycopy(commands, 24+number+i*(6+21*8), a, 0, a.length);
			CoderUtils.print16String(a);
			analysisCommandByDriveXml_sub.setCommands(a);
			driveRoot_config_sub = analysisCommandByDriveXml_sub.analysisDriveAttrbute(file_sub);
			int element_sub = driveRoot_config_sub.getDriveAttributeList().size();
			length = length+6;
			for (int j = 0; j < element_sub; j++) {// 按Lag端口状态配置块_SUB1.xml元素个数遍历

				DriveAttribute driveattribute_sub = new DriveAttribute();
				driveattribute_sub = driveRoot_config_sub.getDriveAttributeList().get(j);
				if(i == 0)
				{
					//赋值
					driveattributeToObject(driveattribute_sub,lagObject);
				}
				else if(i>0)
				{
					driveattributeToObject2(driveattribute_sub,lagObject);
				}
			}
			
			DriveRoot driveRoot_sub_sub = super.LoadDriveXml(subXml);
			List<LagPortObject> lagPorts = new ArrayList<LagPortObject>();
			for (int k = 0; k < 8; k++) {// 按端口个数遍历
				LagPortObject lagport = new LagPortObject();// 端口对象
				System.arraycopy(commands, 24+start + number + length, b, 0, b.length);
				analysisCommandByDriveXml_sub_sub.setCommands(b);
				driveRoot_sub_sub = analysisCommandByDriveXml_sub_sub.analysisDriveAttrbute(subXml);
				int element_sub_sub = driveRoot_sub_sub.getDriveAttributeList().size();
				DriveAttribute driveattribute_sub_sub = new DriveAttribute();
				for (int p = 0; p < element_sub_sub; p++) {// 按Lag端口状态配置块_SUB2.xml元素个数遍历
					driveattribute_sub_sub = driveRoot_sub_sub.getDriveAttributeList().get(p);
					//赋值
					driveattributeToPort( driveattribute_sub_sub,lagport);
				}
				lagPorts.add(lagport);
				length = length + filecount_sub_sub;
			}
			lagObject.setLagPorts(lagPorts);
			lagStatusObjectList.add(lagObject);
		}
		return lagStatusObjectList;
	}

	
	public void driveattributeToPort(DriveAttribute driveattribute,LagPortObject PortStatus){
		
		if("端口号".equals(driveattribute.getDescription())){
			PortStatus.setPortNum(Integer.parseInt(driveattribute.getValue()));
		}else if("端口LACP协议".equals(driveattribute.getDescription())){
			PortStatus.setPortLacp(Integer.parseInt(driveattribute.getValue()));
		}else if("端口状态".equals(driveattribute.getDescription())){
			PortStatus.setPortStatus(Integer.parseInt(driveattribute.getValue()));
		}else if("本地端口优先级".equals(driveattribute.getDescription())){
			PortStatus.setLocalPri(Integer.parseInt(driveattribute.getValue()));
		}
	}
	
	public void driveattributeToObject(DriveAttribute driveattribute,LagStatusObject lagPortStatus){
		if("LACP协议开关".equals(driveattribute.getDescription())){
			lagPortStatus.setLacpSwitch(Integer.parseInt(driveattribute.getValue()));
		}else if("系统优先级".equals(driveattribute.getDescription())){
			lagPortStatus.setSyspri(Integer.parseInt(driveattribute.getValue()));
		}else if("LAG ID".equals(driveattribute.getDescription())){
			lagPortStatus.setLagId(Integer.parseInt(driveattribute.getValue()));
		}else if("聚合组工作模式".equals(driveattribute.getDescription())){
			lagPortStatus.setWorkMode(Integer.parseInt(driveattribute.getValue()));
		}else if("聚合模式".equals(driveattribute.getDescription())){
			lagPortStatus.setLagMode(Integer.parseInt(driveattribute.getValue()));
		}else if("聚合组成员数".equals(driveattribute.getDescription())){
			lagPortStatus.setLagNum(Integer.parseInt(driveattribute.getValue()));
		}else if("非负载分担返回类型".equals(driveattribute.getDescription())){
			lagPortStatus.setReturnType(Integer.parseInt(driveattribute.getValue()));
		}else if("等待恢复时间".equals(driveattribute.getDescription())){
			lagPortStatus.setWaitTime(Integer.parseInt(driveattribute.getValue()));
		}
	}
	
	public void driveattributeToObject2(DriveAttribute driveattribute,LagStatusObject lagPortStatus){
		if("LAG ID".equals(driveattribute.getDescription())){
			lagPortStatus.setLagId(Integer.parseInt(driveattribute.getValue()));
		}else if("聚合组工作模式".equals(driveattribute.getDescription())){
			lagPortStatus.setWorkMode(Integer.parseInt(driveattribute.getValue()));
		}else if("聚合模式".equals(driveattribute.getDescription())){
			lagPortStatus.setLagMode(Integer.parseInt(driveattribute.getValue()));
		}else if("聚合组成员数".equals(driveattribute.getDescription())){
			lagPortStatus.setLagNum(Integer.parseInt(driveattribute.getValue()));
		}else if("非负载分担返回类型".equals(driveattribute.getDescription())){
			lagPortStatus.setReturnType(Integer.parseInt(driveattribute.getValue()));
		}else if("等待恢复时间".equals(driveattribute.getDescription())){
			lagPortStatus.setWaitTime(Integer.parseInt(driveattribute.getValue()));
		}
	}
}
