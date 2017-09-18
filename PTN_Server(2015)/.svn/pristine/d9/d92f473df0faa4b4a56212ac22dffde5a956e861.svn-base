package com.nms.drive.analysis.datablock.status;


import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.OamTraceHopsObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisEthOAMTraceQueryTable extends AnalysisBase{
	private int dataCount = 16;// 每个数据块的长度
	//起始长度
	int start = 2;
	/**
	 * 根据字节数组生成对象
	 * @param commands命令
	 * @param configXml配置XML
	 */
	public List<OamTraceHopsObject> analysisCommandToObject(byte[] commands,String configXml) throws Exception{
		List<OamTraceHopsObject> traceList = null;
		DriveRoot driveRoot_config = null;
		String file = null;
		AnalysisCommandByDriveXml analysisCommandByDriveXml = null;
		//条目id
		int id = 0;
		//条目数
		int count = 0;
		try {
			count = (commands.length - 2)/dataCount;
			traceList = new ArrayList<OamTraceHopsObject>();
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
					OamTraceHopsObject trace = new OamTraceHopsObject();
					//将driveRoot里面的信息赋值到OamTraceHopsObject对象中
					trace.setId(id);
					for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
						commandToOamPingObject(driveRoot.getDriveAttributeList().get(j),trace,mac);
					}
					traceList.add(trace);
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
		
		return traceList;
	}
	
	/**
	 * driveAttribute赋值给OamPingObject
	 * @param pingObject
	 * @param driveAttribute
	 */
	private void commandToOamPingObject(DriveAttribute driveAttribute,OamTraceHopsObject trace,StringBuffer mac) {
		// 跳数
		if(driveAttribute.getDescription().equals("跳数")){
			trace.setHops(Integer.parseInt(driveAttribute.getValue()));
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
			trace.setFarMac(mac.toString());
		}
		//入口槽位号
		if(driveAttribute.getDescription().equals("入口槽位号")){
			trace.setInSlot(Integer.parseInt(driveAttribute.getValue()));
		}
		//入口端口号
		if(driveAttribute.getDescription().equals("入口端口号")){
			trace.setInPort(Integer.parseInt(driveAttribute.getValue()));
		}
		//入口动作
		if(driveAttribute.getDescription().equals("入口动作")){
			trace.setInAction(Integer.parseInt(driveAttribute.getValue()));
		}
		//转发动作
		if(driveAttribute.getDescription().equals("转发动作")){
			trace.setTransmitAction(Integer.parseInt(driveAttribute.getValue()));
		}
		//是否转发
		if(driveAttribute.getDescription().equals("是否转发")){
			trace.setIsTransmitAction(Integer.parseInt(driveAttribute.getValue()));
		}
		//出口槽位号
		if(driveAttribute.getDescription().equals("出口槽位号")){
			trace.setOutSlot(Integer.parseInt(driveAttribute.getValue()));
		}
		//出口端口号
		if(driveAttribute.getDescription().equals("出口端口号")){
			trace.setOutPort(Integer.parseInt(driveAttribute.getValue()));
		}
		//出口动作
		if(driveAttribute.getDescription().equals("出口动作")){
			trace.setOutAction(Integer.parseInt(driveAttribute.getValue()));
		}
		//是否为MEP
		if(driveAttribute.getDescription().equals("是否为MEP")){
			trace.setIsMep(Integer.parseInt(driveAttribute.getValue()));
		}
	}
}
