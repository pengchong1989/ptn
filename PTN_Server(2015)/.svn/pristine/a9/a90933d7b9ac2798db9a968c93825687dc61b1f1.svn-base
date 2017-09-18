package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.PortLAGMember;
import com.nms.drive.service.bean.PortLAGObject;
import com.nms.drive.service.bean.PortLAGbuffer;
import com.nms.drive.service.bean.PortLAGexitQueue;
import com.nms.drive.service.impl.CoderUtils;

/**
 * 解析 端口聚合
 * @author 罗磊
 * 
 */
public class AnalysisPortLAGTable extends AnalysisBase {

	/**
	 * 根据 端口聚合(PortLAG) 对象生成命令
	 * @param portLAGList
	 *            端口聚合对象
	 * @param configXML
	 *            配置XML
	 * @return 命令
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(List<PortLAGObject> portLAGList, String configXml) throws Exception {
		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			int count = driveRoot_config.getDriveAttributeList().size();//获得元素个数
			for (int i = 0; i < count; i++) {
				DriveAttribute driveAttribute = new DriveAttribute();
				driveAttribute = driveRoot_config.getDriveAttributeList().get(i);
				if("条目数".equals(driveAttribute.getDescription())){
					int filecount = portLAGList.size();// 获得条目数
					driveAttribute.setValue(filecount+"");
					String file = driveAttribute.getListRootList().get(0).getFile();
					driveAttribute.getListRootList().clear();// 清除本身自带的一个ListRoot			
					for (int j = 0; j < filecount;j++) {// 按条目数循环
						DriveRoot driveRoot_config_1 = super.LoadDriveXml(file);
						ListRoot listRoot = new ListRoot();
						listRoot.setDriveAttributeList(driveRoot_config_1.getDriveAttributeList());
						String[] pris = portLAGList.get(j).getPri().split(",");// 优先级   ( 0,1,2,3,4,5,6,7)
						for (int k = 0; k < driveRoot_config_1.getDriveAttributeList().size(); k++) {
							DriveAttribute driveAttribute1 = driveRoot_config_1.getDriveAttributeList().get(k);
							// 属性赋值
							ObjectToDriveAttribute(portLAGList.get(j), driveAttribute1,pris);
						}
						driveAttribute.getListRootList().add(listRoot);		
					}
				}
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
			CoderUtils.print16String(dataCommand);
			return dataCommand;
			
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 根据命令 生成 端口聚合 (PortLAG) 对象
	 * @param commands
	 *            命令
	 * @param configXml
	 *            配置XML
	 * @return (PortLAG) 对象
	 * @throws Exception
	 */
	public List<PortLAGObject> analysisCommandToObject(byte[] commands, String configXml) throws Exception {
		List<PortLAGObject> portLAGList = new ArrayList<PortLAGObject>();
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		String file = driveRoot_config.getDriveAttributeList().get(1).getListRootList().get(0).getFile();

		int start = 0;// 命令的起始长度=（ne+盘头+配置）+条目数
		int dataCount = 140;// 每个数据块的长度
		int count = (commands.length - 1) / dataCount;
		StringBuffer pris = new StringBuffer();
		try {
			for (int j = 0; j < count; j++) {
				byte[] a = new byte[dataCount];
				System.arraycopy(commands, 8 + j * dataCount, a, 0, a.length);
				AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
				analysisCommandByDriveXml.setCommands(a);
				DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(file);
				PortLAGObject portLAGObject = new PortLAGObject();
				List<PortLAGMember> memberList = new ArrayList<PortLAGMember>();
				List<PortLAGexitQueue> exitQueueList = new ArrayList<PortLAGexitQueue>();
				List<PortLAGbuffer> bufferList = new ArrayList<PortLAGbuffer>();
				for (int i = 0; i < 8; i++) {
					PortLAGexitQueue portLAGexitQueue = new PortLAGexitQueue();
					PortLAGbuffer portLAGbuffer = new PortLAGbuffer();
					exitQueueList.add(portLAGexitQueue);
					bufferList.add(portLAGbuffer);
				}
				for (int i = 0; i < 4; i++) {
					PortLAGMember portLAGMember = new PortLAGMember();
					memberList.add(portLAGMember);
				}
				portLAGObject.setMemberList(memberList);
				portLAGObject.setExitQueueList(exitQueueList);
				portLAGObject.setBufferList(bufferList);
				for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
					DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(i);
					// 属性赋值
					DriveAttributeToObject(driveAttribute, portLAGObject,pris);
				}

				portLAGList.add(portLAGObject);
			}
		} catch (Exception e) {
			throw e;
		}
		return portLAGList;
	}

	/**
	 * 对象赋值到xml
	 * 
	 * @param portAGObject
	 * @param driveAttribute
	 */
	public void ObjectToDriveAttribute(PortLAGObject portAGObject, DriveAttribute driveAttribute,String[] pris) {
		if ("LAG ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getLagID() + "");
		}
		if ("聚合模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getLagMode() + "");
		}
		// ------------------------
		if ("(聚合成员端口1)槽位号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getMemberList().get(0).getSlot() + "");
		}
		if ("(聚合成员端口1)端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getMemberList().get(0).getPort() + "");
		}
		if ("(聚合成员端口2)槽位号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getMemberList().get(1).getSlot() + "");
		}
		if ("(聚合成员端口2)端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getMemberList().get(1).getPort() + "");
		}
		if ("(聚合成员端口3)槽位号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getMemberList().get(2).getSlot() + "");
		}
		if ("(聚合成员端口3)端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getMemberList().get(2).getPort() + "");
		}
		if ("(聚合成员端口4)槽位号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getMemberList().get(3).getSlot() + "");
		}
		if ("(聚合成员端口4)端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getMemberList().get(3).getPort() + "");
		}
		if ("非负载分担返回模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getPortEnable() + "");
		}
		if ("主聚合成员端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getFlowControl() + "");
		}else if ("主聚合成员槽位号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getMainSlot() + "");
		}
		if ("等待恢复时间".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getMtu() + "");
		}
		if ("VLAN关联设置".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getVlanRelating() + "");
		}
		if ("802.1P关联设置".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getRelatingSet() + "");
		}
		if ("源MAC地址关联设置".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getFountainMAC() + "");
		}
		if ("目的MAC地址关联设置".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getAimMAC() + "");
		}
		if ("源IP地址关联设置".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getFountainIP() + "");
		}
		if ("目的IP地址关联设置".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getAimIP() + "");
		}
		if ("PW关联设置".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getPwSet() + "");
		}
		if ("IP DSCP关联设置".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getIpDSCPSet() + "");
		}
		// ---------
		if ("优先级0".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pris[0]);
		}
		if ("优先级1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pris[1]);
		}
		if ("优先级2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pris[2]);
		}
		if ("优先级3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pris[3]);
		}
		if ("优先级4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pris[4]);
		}
		if ("优先级5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pris[5]);
		}
		if ("优先级6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pris[6]);
		}
		if ("优先级7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pris[7]);
		}
		// ------------
		if ("(出口队列调度策略)(队列0)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(0).getMode() + "");
		}
		if ("(队列0)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(0).getPopedom() + "");
		}
		if ("(出口队列调度策略)(队列1)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(1).getMode() + "");
		}
		if ("(队列1)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(1).getPopedom() + "");
		}
		if ("(出口队列调度策略)(队列2)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(2).getMode() + "");
		}
		if ("(队列2)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(2).getPopedom() + "");
		}
		if ("(出口队列调度策略)(队列3)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(3).getMode() + "");
		}
		if ("(队列3)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(3).getPopedom() + "");
		}
		if ("(出口队列调度策略)(队列4)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(4).getMode() + "");
		}
		if ("(队列4)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(4).getPopedom() + "");
		}
		if ("(出口队列调度策略)(队列5)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(5).getMode() + "");
		}
		if ("(队列5)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(5).getPopedom() + "");
		}
		if ("(出口队列调度策略)(队列6)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(6).getMode() + "");
		}
		if ("(队列6)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(6).getPopedom() + "");
		}
		if ("(出口队列调度策略)(队列7)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(7).getMode() + "");
		}
		if ("(队列7)权重".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getExitQueueList().get(7).getPopedom() + "");
		}
		// -----------------
		if ("(队列缓存管理策略)(队列0)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(0).getMode() + "");
		}
		if ("(队列0)START门限".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(0).getStartThreshold() + "");
		}
		if ("(队列0)END门限".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(0).getEndThreshold() + "");
		}

		if ("(队列缓存管理策略)(队列1)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(1).getMode() + "");
		}
		if ("(队列)START门1限".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(1).getStartThreshold() + "");
		}
		if ("(队列)END门1限".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(1).getEndThreshold() + "");
		}

		if ("(队列缓存管理策略)(队列2)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(2).getMode() + "");
		}
		if ("(队列)START门限2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(2).getStartThreshold() + "");
		}
		if ("(队列)END门限2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(2).getEndThreshold() + "");
		}

		if ("(队列缓存管理策略)(队列3)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(3).getMode() + "");
		}
		if ("(队列)START门限3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(3).getStartThreshold() + "");
		}
		if ("(队列)END门限3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(3).getEndThreshold() + "");
		}

		if ("(队列缓存管理策略)(队列4)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(4).getMode() + "");
		}
		if ("(队列)START门限4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(4).getStartThreshold() + "");
		}
		if ("(队列)END门限4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(4).getEndThreshold() + "");
		}

		if ("(队列缓存管理策略)(队列5)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(5).getMode() + "");
		}
		if ("(队列)START门限5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(5).getStartThreshold() + "");
		}
		if ("(队列)END门限5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(5).getEndThreshold() + "");
		}

		if ("(队列缓存管理策略)(队列6)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(6).getMode() + "");
		}
		if ("(队列)START门限6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(6).getStartThreshold() + "");
		}
		if ("(队列)END门限6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(6).getEndThreshold() + "");
		}

		if ("(队列缓存管理策略)(队列7)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(7).getMode() + "");
		}
		if ("(队列)START门限7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(7).getStartThreshold() + "");
		}
		if ("(队列)END门限7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBufferList().get(7).getEndThreshold() + "");
		}
		// --------------------------
		if ("端口出口限速".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getPortLimitation() + "");
		}
		if ("广播包抑制".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBroadcastBate() + "");
		}
		if ("广播包流量".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBroadcastFlux() + "");
		}
		if ("聚合组工作模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getBroadcastBate() + "");
		}
		if ("组播包流量".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getGroupBroadcastFlux() + "");
		}
		if ("从聚合成员端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getFloodBate() + "");
		}else if ("从聚合成员槽位号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getStandSlot() + "");
		}
		if ("洪泛包流量".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(portAGObject.getFloodFlux() + "");
		}
	}

	/**
	 * xml属性赋值给对象
	 * 
	 * @param driveAttribute
	 * @param portLAGObject
	 */
	public void DriveAttributeToObject(DriveAttribute driveAttribute, PortLAGObject portLAGObject,StringBuffer pris) {
		if ("LAG ID".equals(driveAttribute.getDescription())) {
			portLAGObject.setLagID(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("聚合模式".equals(driveAttribute.getDescription())) {
			portLAGObject.setLagMode(Integer.parseInt(driveAttribute.getValue()));
		}
		// ------------------------
		if ("(聚合成员端口1)槽位号".equals(driveAttribute.getDescription())) {
			portLAGObject.getMemberList().get(0).setSlot(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(聚合成员端口1)端口号".equals(driveAttribute.getDescription())) {
			portLAGObject.getMemberList().get(0).setPort(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(聚合成员端口2)槽位号".equals(driveAttribute.getDescription())) {
			portLAGObject.getMemberList().get(1).setSlot(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(聚合成员端口2)端口号".equals(driveAttribute.getDescription())) {
			portLAGObject.getMemberList().get(1).setPort(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(聚合成员端口3)槽位号".equals(driveAttribute.getDescription())) {
			portLAGObject.getMemberList().get(2).setSlot(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(聚合成员端口3)端口号".equals(driveAttribute.getDescription())) {
			portLAGObject.getMemberList().get(2).setPort(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(聚合成员端口4)槽位号".equals(driveAttribute.getDescription())) {
			portLAGObject.getMemberList().get(3).setSlot(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(聚合成员端口4)端口号".equals(driveAttribute.getDescription())) {
			portLAGObject.getMemberList().get(3).setPort(Integer.parseInt(driveAttribute.getValue()));
		}
		// --------------------------
		if ("非负载分担返回模式".equals(driveAttribute.getDescription())) {
			portLAGObject.setPortEnable(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("主聚合成员端口号".equals(driveAttribute.getDescription())) {
			portLAGObject.setFlowControl(Integer.parseInt(driveAttribute.getValue()));
		}else if ("主聚合成员槽位号".equals(driveAttribute.getDescription())) {
			portLAGObject.setMainSlot(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("等待恢复时间".equals(driveAttribute.getDescription())) {
			portLAGObject.setMtu(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("VLAN关联设置".equals(driveAttribute.getDescription())) {
			portLAGObject.setVlanRelating(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("802.1P关联设置".equals(driveAttribute.getDescription())) {
			portLAGObject.setRelatingSet(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("源MAC地址关联设置".equals(driveAttribute.getDescription())) {
			portLAGObject.setFountainMAC(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("目的MAC地址关联设置".equals(driveAttribute.getDescription())) {
			portLAGObject.setAimMAC(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("源IP地址关联设置".equals(driveAttribute.getDescription())) {
			portLAGObject.setFountainIP(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("目的IP地址关联设置".equals(driveAttribute.getDescription())) {
			portLAGObject.setAimIP(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("PW关联设置".equals(driveAttribute.getDescription())) {
			portLAGObject.setPwSet(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("IP DSCP关联设置".equals(driveAttribute.getDescription())) {
			portLAGObject.setIpDSCPSet(Integer.parseInt(driveAttribute.getValue()));
		}
		// ---------
		if ("优先级0".equals(driveAttribute.getDescription())) {
			pris.append(driveAttribute.getValue()+",");
		}
		if ("优先级1".equals(driveAttribute.getDescription())) {
			pris.append(driveAttribute.getValue()+",");
		}
		if ("优先级2".equals(driveAttribute.getDescription())) {
			pris.append(driveAttribute.getValue()+",");
		}
		if ("优先级3".equals(driveAttribute.getDescription())) {
			pris.append(driveAttribute.getValue()+",");
		}
		if ("优先级4".equals(driveAttribute.getDescription())) {
			pris.append(driveAttribute.getValue()+",");
		}
		if ("优先级5".equals(driveAttribute.getDescription())) {
			pris.append(driveAttribute.getValue()+",");
		}
		if ("优先级6".equals(driveAttribute.getDescription())) {
			pris.append(driveAttribute.getValue()+",");
		}
		if ("优先级7".equals(driveAttribute.getDescription())) {
			pris.append(driveAttribute.getValue());
			portLAGObject.setPri(pris.toString());
		}
		// ------------
		if ("(出口队列调度策略)(队列0)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(0).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列0)权重".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(0).setPopedom(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(出口队列调度策略)(队列1)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(1).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列1)权重".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(1).setPopedom(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(出口队列调度策略)(队列2)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(2).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列2)权重".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(2).setPopedom(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(出口队列调度策略)(队列3)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(3).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列3)权重".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(3).setPopedom(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(出口队列调度策略)(队列4)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(4).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列4)权重".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(4).setPopedom(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(出口队列调度策略)(队列5)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(5).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列5)权重".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(5).setPopedom(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(出口队列调度策略)(队列6)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(6).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列6)权重".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(6).setPopedom(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(出口队列调度策略)(队列7)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(7).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列7)权重".equals(driveAttribute.getDescription())) {
			portLAGObject.getExitQueueList().get(7).setPopedom(Integer.parseInt(driveAttribute.getValue()));
		}
		// -----------------
		if ("(队列缓存管理策略)(队列0)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(0).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列0)START门限".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(0).setStartThreshold(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列0)END门限".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(0).setEndThreshold(Integer.parseInt(driveAttribute.getValue()));
		}

		if ("(队列缓存管理策略)(队列1)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(1).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)START门1限".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(1).setStartThreshold(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)END门1限".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(1).setEndThreshold(Integer.parseInt(driveAttribute.getValue()));
		}

		if ("(队列缓存管理策略)(队列2)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(2).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)START门限2".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(2).setStartThreshold(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)END门限2".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(2).setEndThreshold(Integer.parseInt(driveAttribute.getValue()));
		}

		if ("(队列缓存管理策略)(队列3)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(3).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)START门限3".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(3).setStartThreshold(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)END门限3".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(3).setEndThreshold(Integer.parseInt(driveAttribute.getValue()));
		}

		if ("(队列缓存管理策略)(队列4)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(4).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)START门限4".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(4).setStartThreshold(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)END门限4".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(4).setEndThreshold(Integer.parseInt(driveAttribute.getValue()));
		}

		if ("(队列缓存管理策略)(队列5)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(5).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)START门限5".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(5).setStartThreshold(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)END门限5".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(5).setEndThreshold(Integer.parseInt(driveAttribute.getValue()));
		}

		if ("(队列缓存管理策略)(队列6)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(6).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)START门限6".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(6).setStartThreshold(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)END门限6".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(6).setEndThreshold(Integer.parseInt(driveAttribute.getValue()));
		}

		if ("(队列缓存管理策略)(队列7)模式".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(7).setMode(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)START门限7".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(7).setStartThreshold(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("(队列)END门限7".equals(driveAttribute.getDescription())) {
			portLAGObject.getBufferList().get(7).setEndThreshold(Integer.parseInt(driveAttribute.getValue()));
		}
		// --------------------------
		if ("端口出口限速".equals(driveAttribute.getDescription())) {
			portLAGObject.setPortLimitation(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("广播包抑制".equals(driveAttribute.getDescription())) {
			portLAGObject.setBroadcastBate(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("广播包流量".equals(driveAttribute.getDescription())) {
			portLAGObject.setBroadcastFlux(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("聚合组工作模式".equals(driveAttribute.getDescription())) {
			portLAGObject.setBroadcastBate(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("组播包流量".equals(driveAttribute.getDescription())) {
			portLAGObject.setGroupBroadcastFlux(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("从聚合成员端口号".equals(driveAttribute.getDescription())) {
			portLAGObject.setFloodBate(Integer.parseInt(driveAttribute.getValue()));
		}else if ("从聚合成员槽位号".equals(driveAttribute.getDescription())) {
			portLAGObject.setStandSlot(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("洪泛包流量".equals(driveAttribute.getDescription())) {
			portLAGObject.setFloodFlux(Integer.parseInt(driveAttribute.getValue()));
		}
	}

}
