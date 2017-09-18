package com.nms.drive.analysis.datablock.status;


import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.OamPingFrameObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisEthOAMPingQueryTable extends AnalysisBase{
	private int dataCount = 12;// 每个数据块的长度
	//起始长度
	int start = 2;
	/**
	 * 根据字节数组生成对象
	 * @param commands命令
	 * @param configXml配置XML
	 */
	public List<OamPingFrameObject> analysisCommandToObject(byte[] commands,String configXml) throws Exception{
		List<OamPingFrameObject> pingList = null;
		DriveRoot driveRoot_config = null;
		String file = null;
		AnalysisCommandByDriveXml analysisCommandByDriveXml = null;
		//条目id
		int id = 0;
		//条目数
		int count = 0;
		try {
			count = (commands.length - 2)/dataCount;
			pingList = new ArrayList<OamPingFrameObject>();
			driveRoot_config = super.LoadDriveXml(configXml);
			file = driveRoot_config.getDriveAttributeList().get(1).getListRootList().get(0).getFile();
			id = Integer.parseInt(driveRoot_config.getDriveAttributeList().get(0).getValue());
			for (int i = 0; i < count; i++) {
				byte[] a = new byte[dataCount];
				StringBuffer mac = new StringBuffer();
				System.arraycopy(commands, start + i * dataCount, a, 0, a.length);
				analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
				analysisCommandByDriveXml.setCommands(a);
				try {
					DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(file);
					OamPingFrameObject ping = new OamPingFrameObject();
					//将driveRoot里面的信息赋值到OamPingFrameObject对象中
					ping.setId(id);
					for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
						commandToOamPingObject(driveRoot.getDriveAttributeList().get(j),ping,mac);
					}
					pingList.add(ping);
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			driveRoot_config = null;
			file = null;
			analysisCommandByDriveXml = null;
		}
		
		return pingList;
	}
	
	/**
	 * driveAttribute赋值给OamPingObject
	 * @param pingObject
	 * @param driveAttribute
	 */
	private void commandToOamPingObject(DriveAttribute driveAttribute,OamPingFrameObject ping,StringBuffer mac) {
		// 帧计数
		if(driveAttribute.getDescription().equals("帧计数")){
			ping.setFrameId(Integer.parseInt(driveAttribute.getValue()));
		}
		//状态
		if(driveAttribute.getDescription().equals("状态")){
			ping.setStatus(Integer.parseInt(driveAttribute.getValue()));
		}
		//远端MAC
		if(driveAttribute.getDescription().equals("远端MAC1")){
			mac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//远端MAC
		if(driveAttribute.getDescription().equals("远端MAC2")){
			mac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//远端MAC
		if(driveAttribute.getDescription().equals("远端MAC3")){
			mac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//远端MAC
		if(driveAttribute.getDescription().equals("远端MAC4")){
			mac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//远端MAC
		if(driveAttribute.getDescription().equals("远端MAC5")){
			mac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//远端MAC
		if(driveAttribute.getDescription().equals("远端MAC6")){
			mac.append(CoderUtils.synchTransformMac(driveAttribute.getValue()));
			ping.setFarMac(mac.toString());
		}
		//字节数
		if(driveAttribute.getDescription().equals("字节数")){
			ping.setBytes(Integer.parseInt(driveAttribute.getValue()));
		}
		//时间
		if(driveAttribute.getDescription().equals("时间")){
			ping.setTime(Integer.parseInt(driveAttribute.getValue()));
		}
	}
}
