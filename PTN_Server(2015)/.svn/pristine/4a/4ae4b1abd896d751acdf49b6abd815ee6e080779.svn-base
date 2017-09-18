package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.PortLAGbuffer;
import com.nms.drive.service.bean.PortLAGexitQueue;
import com.nms.drive.service.bean.PortSeniorConfig;

/**
 * 
 * 高级端口解析
 * @author 彭冲
 *
 */
public class AnalysisPortSeniorConfigTable extends AnalysisBase {

	private int dataCount = 100;// 每个数据块的长度
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度

	/**
	 * 根据对象生成字节数组
	 * 
	 * @param PortSeniorConfig对象
	 * @param configXml配置XML
	 * @return 命令
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(List<PortSeniorConfig> PortSeniorConfigObjectlist, String configXml) throws Exception {

		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			for (int i = 1; i < PortSeniorConfigObjectlist.size() + 1; i++) {
				String[] priority = PortSeniorConfigObjectlist.get(i-1).getPriority().split(",");
				for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {
					DriveAttribute driveAttrebute = new DriveAttribute();
					driveAttrebute = driveRoot_config.getDriveAttributeList().get(j);
					// 赋值
					PortSeniorConfigToDriveAttribute(PortSeniorConfigObjectlist.get(i-1), driveAttrebute, i,priority);
				}
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
			// CoderUtils.print16String(dataCommand);
			return dataCommand;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return PortSeniorConfig
	 */
	public PortSeniorConfig analysisCommandToObject(byte[] commands, String configXml) throws Exception {
		PortSeniorConfig portSeniorConfigObject = new PortSeniorConfig();

		// 起始长度
		int start =0;

		// 端口数
		int count = (commands.length) / dataCount;
			byte[] a = new byte[dataCount];
			System.arraycopy(commands, 0, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			PortSeniorConfig portSeniorConfig = new PortSeniorConfig();		
			// (出口队列调度策略)
			List<PortLAGexitQueue> exitQueueList = new ArrayList<PortLAGexitQueue>();// (出口队列调度策略)
			for(int j=0;j<8;j++){
				PortLAGexitQueue portLAGexitQueue = new PortLAGexitQueue();
				exitQueueList.add(portLAGexitQueue);
			}
			portSeniorConfig.setExitQueueList(exitQueueList);
			//(队列缓存管理策略)
			List<PortLAGbuffer> bufferList = new ArrayList<PortLAGbuffer>();
			for(int j=0;j<8;j++){
				PortLAGbuffer portLAGbuffer = new PortLAGbuffer();
				bufferList.add(portLAGbuffer);
			}
			portSeniorConfig.setBufferList(bufferList);
			DriveRoot driveRoot = new DriveRoot();
			StringBuffer sb = new StringBuffer();
			try {
				driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);

				// 将 driveRoot 信息 赋值 PortSeniorConfig 对象中
				for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
					// 赋值
					DriveAttributeToPortSeniorConfig(portSeniorConfig, driveRoot.getDriveAttributeList().get(j),sb);
				}
			} catch (Exception e) {
				throw e;
			}
		return portSeniorConfigObject;
	}

	/**
	 * PortSeniorConfig值赋给driveAttribute
	 * 
	 * @param UniObject
	 * @param driveAttribute
	 */
	public void PortSeniorConfigToDriveAttribute(PortSeniorConfig portSeniorConfig, DriveAttribute driveattribute, int i,String[] priority) {
		
		//优先级赋值
		PriorityTODriveAttribute(driveattribute, i, priority);
		
		//出口策略赋值
		PortLAGexitQueueTODriveAttribute(portSeniorConfig, driveattribute, i);

		//队列缓存管理策略赋值
		PortLAGbufferTODriveAttribute(portSeniorConfig, driveattribute, i);
		
		//其他的一些赋值
		OtherTODriveAttribute(portSeniorConfig, driveattribute, i);
	}
	
	/**
	 * 优先级属性TOdriveattribute
	 */
	public void PriorityTODriveAttribute(DriveAttribute driveattribute, int i,String[] priority){
		
		if(i<23){
			// (LAN1)优先级0
			String str1 = "(LAN" + i + ")优先级0";
			if (str1.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[0]);
			}

			// (LAN1)优先级1
			String str2 = "(LAN" + i + ")优先级1";
			if (str2.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[1]);
			}

			// (LAN1)优先级2
			String str3 = "(LAN" + i + ")优先级2";
			if (str3.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[2]);
			}

			// (LAN1)优先级3
			String str4 = "(LAN" + i + ")优先级3";
			if (str4.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[3]);
			}

			// (LAN1)优先级4
			String str5 = "(LAN" + i + ")优先级4";
			if (str5.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[4]);
			}

			// (LAN1)优先级5
			String str6 = "(LAN" + i + ")优先级5";
			if (str6.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[5]);
			}

			// (LAN1)优先级6
			String str7 = "(LAN" + i + ")优先级6";
			if (str7.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[6]);
			}

			// (LAN1)优先级7
			String str8 = "(LAN" + i + ")优先级7";
			if (str8.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[7]);
			}
		}else{
			// (WAN1)优先级0
			String str1 = "(WAN" + i + ")优先级0";
			if (str1.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[0]);
			}

			// (WAN1)优先级1
			String str2 = "(WAN" + i + ")优先级1";
			if (str2.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[1]);
			}

			// (WAN1)优先级2
			String str3 = "(WAN" + i + ")优先级2";
			if (str3.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[2]);
			}

			// (WAN1)优先级3
			String str4 = "(WAN" + i + ")优先级3";
			if (str4.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[3]);
			}

			// (WAN1)优先级4
			String str5 = "(WAN" + i + ")优先级4";
			if (str5.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[4]);
			}

			// (WAN1)优先级5
			String str6 = "(WAN" + i + ")优先级5";
			if (str6.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[5]);
			}

			// (WAN1)优先级6
			String str7 = "(WAN" + i + ")优先级6";
			if (str7.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[6]);
			}

			// (WAN1)优先级7
			String str8 = "(WAN" + i + ")优先级7";
			if (str8.equals(driveattribute.getDescription())) {
				driveattribute.setValue(priority[7]);
			}
		}
		
	}
	
	/**
	 * 出口策略赋值
	 * @param PortSeniorConfig
	 * @param driveattribute
	 * @param i
	 */
	public void PortLAGexitQueueTODriveAttribute(PortSeniorConfig portSeniorConfig, DriveAttribute driveattribute, int i){
		
		if(i<23){
			// (LAN1)(出口队列调度策略)(队列0)模式
			String str0_mode = "(LAN" + i + ")(出口队列调度策略)(队列0)模式";
			if (str0_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(0).getMode()+"");
			}
			//(LAN1)(队列0)权重
			String str0_weight = "(LAN" + i + ")(队列0)权重";
			if (str0_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(0).getPopedom()+"");
			}
			
			// (LAN1)(出口队列调度策略)(队列1)模式
			String str1_mode = "(LAN" + i + ")(出口队列调度策略)(队列1)模式";
			if (str1_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(1).getMode()+"");
			}
			//(LAN1)(队列1)权重
			String str1_weight = "(LAN" + i + ")(队列1)权重";
			if (str1_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(1).getPopedom()+"");
			}
			
			// (LAN1)(出口队列调度策略)(队列2)模式
			String str2_mode = "(LAN" + i + ")(出口队列调度策略)(队列2)模式";
			if (str2_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(2).getMode()+"");
			}
			//(LAN1)(队列2)权重
			String str2_weight = "(LAN" + i + ")(队列2)权重";
			if (str2_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(2).getPopedom()+"");
			}

			// (LAN1)(出口队列调度策略)(队列3)模式
			String str3_mode = "(LAN" + i + ")(出口队列调度策略)(队列3)模式";
			if (str3_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(3).getMode()+"");
			}
			//(LAN1)(队列3)权重
			String str3_weight = "(LAN" + i + ")(队列3)权重";
			if (str3_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(3).getPopedom()+"");
			}

			// (LAN1)(出口队列调度策略)(队列4)模式
			String str4_mode = "(LAN" + i + ")(出口队列调度策略)(队列4)模式";
			if (str4_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(4).getMode()+"");
			}
			//(LAN1)(队列4)权重
			String str4_weight = "(LAN" + i + ")(队列4)权重"; 
			if (str4_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(4).getPopedom()+"");
			}

			// (LAN1)(出口队列调度策略)(队列5)模式
			String str5_mode = "(LAN" + i + ")(出口队列调度策略)(队列5)模式";
			if (str5_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(5).getMode()+"");
			}
			//(LAN1)(队列5)权重
			String str5_weight = "(LAN" + i + ")(队列5)权重"; 
			if (str5_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(5).getPopedom()+"");
			}

			// (LAN1)(出口队列调度策略)(队列6)模式
			String str6_mode = "(LAN" + i + ")(出口队列调度策略)(队列6)模式";
			if (str6_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(6).getMode()+"");
			}
			//(LAN1)(队列6)权重
			String str6_weight = "(LAN" + i + ")(队列6)权重"; 
			if (str6_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(6).getPopedom()+"");
			}

			// (LAN1)(出口队列调度策略)(队列7)模式
			String str7_mode = "(LAN" + i + ")(出口队列调度策略)(队列7)模式";
			if (str7_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(7).getMode()+"");
			}
			//(LAN1)(队列7)权重
			String str7_weight = "(LAN" + i + ")(队列7)权重"; 
			if (str7_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(7).getPopedom()+"");
			}
		}else{
			// (WAN1)(出口队列调度策略)(队列0)模式
			String str0_mode = "(WAN" + i + ")(出口队列调度策略)(队列0)模式";
			if (str0_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(0).getMode()+"");
			}
			//(WAN1)(队列0)权重
			String str0_weight = "(WAN" + i + ")(队列0)权重";
			if (str0_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(0).getPopedom()+"");
			}
			
			// (WAN1)(出口队列调度策略)(队列1)模式
			String str1_mode = "(WAN" + i + ")(出口队列调度策略)(队列1)模式";
			if (str1_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(1).getMode()+"");
			}
			//(WAN1)(队列1)权重
			String str1_weight = "(WAN" + i + ")(队列1)权重";
			if (str1_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(1).getPopedom()+"");
			}
			
			// (WAN1)(出口队列调度策略)(队列2)模式
			String str2_mode = "(WAN" + i + ")(出口队列调度策略)(队列2)模式";
			if (str2_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(2).getMode()+"");
			}
			//(WAN1)(队列2)权重
			String str2_weight = "(WAN" + i + ")(队列2)权重";
			if (str2_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(2).getPopedom()+"");
			}

			// (WAN1)(出口队列调度策略)(队列3)模式
			String str3_mode = "(WAN" + i + ")(出口队列调度策略)(队列3)模式";
			if (str3_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(3).getMode()+"");
			}
			//(WAN1)(队列3)权重
			String str3_weight = "(WAN" + i + ")(队列3)权重";
			if (str3_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(3).getPopedom()+"");
			}

			// (WAN1)(出口队列调度策略)(队列4)模式
			String str4_mode = "(WAN" + i + ")(出口队列调度策略)(队列4)模式";
			if (str4_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(4).getMode()+"");
			}
			//(WAN1)(队列4)权重
			String str4_weight = "(WAN" + i + ")(队列4)权重"; 
			if (str4_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(4).getPopedom()+"");
			}

			// (WAN1)(出口队列调度策略)(队列5)模式
			String str5_mode = "(WAN" + i + ")(出口队列调度策略)(队列5)模式";
			if (str5_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(5).getMode()+"");
			}
			//(WAN1)(队列5)权重
			String str5_weight = "(WAN" + i + ")(队列5)权重"; 
			if (str5_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(5).getPopedom()+"");
			}

			// (WAN1)(出口队列调度策略)(队列6)模式
			String str6_mode = "(WAN" + i + ")(出口队列调度策略)(队列6)模式";
			if (str6_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(6).getMode()+"");
			}
			//(WAN1)(队列6)权重
			String str6_weight = "(WAN" + i + ")(队列6)权重"; 
			if (str6_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(6).getPopedom()+"");
			}

			// (WAN1)(出口队列调度策略)(队列7)模式
			String str7_mode = "(WAN" + i + ")(出口队列调度策略)(队列7)模式";
			if (str7_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(7).getMode()+"");
			}
			//(WAN1)(队列7)权重
			String str7_weight = "(WAN" + i + ")(队列7)权重"; 
			if (str7_weight.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getExitQueueList().get(7).getPopedom()+"");
			}
		}
	}
	
	/**
	 * 队列缓存管理策略赋值
	 * @param portSeniorConfig
	 * @param driveattribute
	 * @param i
	 */
	public void PortLAGbufferTODriveAttribute(PortSeniorConfig portSeniorConfig, DriveAttribute driveattribute, int i){
		
		if(i<23){
			// (LAN1)(队列缓存管理策略)(队列0)模式
			String str0_mode = "(LAN" + i + ")(队列缓存管理策略)(队列0)模式";
			if (str0_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(0).getMode()+"");
			}
			//(LAN1)(队列0)START门限
			String str0_startthreshold = "(LAN" + i + ")(队列0)START门限";
			if (str0_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(0).getStartThreshold()+"");
			}
			//(LAN1)(队列0)END门限
			String str0_endthreshold = "(LAN" + i + ")(队列0)END门限";
			if (str0_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(0).getEndThreshold()+"");
			}
			// (LAN1)(队列缓存管理策略)(队列1)模式
			String str1_mode = "(LAN" + i + ")(队列缓存管理策略)(队列1)模式";
			if (str1_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(1).getMode()+"");
			}
			//(LAN1)(队列1)START门限
			String str1_startthreshold = "(LAN" + i + ")(队列1)START门限";
			if (str1_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(1).getStartThreshold()+"");
			}
			//(LAN1)(队列1)END门限
			String str1_endthreshold = "(LAN" + i + ")(队列1)END门限";
			if (str1_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(1).getEndThreshold()+"");
			}
			
			
			// (LAN1)(队列缓存管理策略)(队列2)模式
			String str2_mode = "(LAN" + i + ")(队列缓存管理策略)(队列2)模式";
			if (str2_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(2).getMode()+"");
			}
			//(LAN1)(队列2)START门限
			String str2_startthreshold = "(LAN" + i + ")(队列2)START门限";
			if (str2_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(2).getStartThreshold()+"");
			}
			//(LAN1)(队列2)END门限
			String str2_endthreshold = "(LAN" + i + ")(队列2)END门限";
			if (str2_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(2).getEndThreshold()+"");
			}
			

			// (LAN1)(队列缓存管理策略)(队列3)模式
			String str3_mode = "(LAN" + i + ")(队列缓存管理策略)(队列3)模式";
			if (str3_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(3).getMode()+"");
			}
			//(LAN1)(队列3)START门限
			String str3_startthreshold = "(LAN" + i + ")(队列3)START门限";
			if (str3_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(3).getStartThreshold()+"");
			}
			//(LAN1)(队列3)END门限
			String str3_endthreshold = "(LAN" + i + ")(队列3)END门限";
			if (str3_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(3).getEndThreshold()+"");
			}
			

			// (LAN1)(队列缓存管理策略)(队列4)模式
			String str4_mode = "(LAN" + i + ")(队列缓存管理策略)(队列4)模式";
			if (str4_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(4).getMode()+"");
			}
			//(LAN1)(队列4)START门限
			String str4_startthreshold = "(LAN" + i + ")(队列4)START门限"; 
			if (str4_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(4).getStartThreshold()+"");
			}
			//(LAN1)(队列4)END门限
			String str4_endthreshold = "(LAN" + i + ")(队列4)END门限";
			if (str4_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(4).getEndThreshold()+"");
			}
			

			// (LAN1)(队列缓存管理策略)(队列5)模式
			String str5_mode = "(LAN" + i + ")(队列缓存管理策略)(队列5)模式";
			if (str5_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(5).getMode()+"");
			}
			//(LAN1)(队列5)START门限
			String str5_startthreshold = "(LAN" + i + ")(队列5)START门限"; 
			if (str5_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(5).getStartThreshold()+"");
			}
			//(LAN1)(队列5)END门限
			String str5_endthreshold = "(LAN" + i + ")(队列5)END门限";
			if (str5_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(5).getEndThreshold()+"");
			}
			

			// (LAN1)(队列缓存管理策略)(队列6)模式
			String str6_mode = "(LAN" + i + ")(队列缓存管理策略)(队列6)模式";
			if (str6_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(6).getMode()+"");
			}
			//(LAN1)(队列6)START门限
			String str6_startthreshold = "(LAN" + i + ")(队列6)START门限"; 
			if (str6_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(6).getStartThreshold()+"");
			}
			//(LAN1)(队列6)END门限
			String str6_endthreshold = "(LAN" + i + ")(队列6)END门限";
			if (str6_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(6).getEndThreshold()+"");
			}
			

			// (LAN1)(队列缓存管理策略)(队列7)模式
			String str7_mode = "(LAN" + i + ")(队列缓存管理策略)(队列7)模式";
			if (str7_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(7).getMode()+"");
			}
			//(LAN1)(队列7)START门限
			String str7_startthreshold = "(LAN" + i + ")(队列7)START门限"; 
			if (str7_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(7 ).getStartThreshold()+"");
			}
			//(LAN1)(队列7)END门限
			String str7_endthreshold = "(LAN" + i + ")(队列7)END门限";
			if (str7_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(7).getEndThreshold()+"");
			}
		}else{
			// (WAN1)(队列缓存管理策略)(队列0)模式
			String str0_mode = "(WAN" + i + ")(队列缓存管理策略)(队列0)模式";
			if (str0_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(0).getMode()+"");
			}
			//(WAN1)(队列0)START门限
			String str0_startthreshold = "(WAN" + i + ")(队列0)START门限";
			if (str0_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(0).getStartThreshold()+"");
			}
			//(WAN1)(队列0)END门限
			String str0_endthreshold = "(WAN" + i + ")(队列0)END门限";
			if (str0_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(0).getEndThreshold()+"");
			}
			
			
			// (WAN1)(队列缓存管理策略)(队列1)模式
			String str1_mode = "(WAN" + i + ")(队列缓存管理策略)(队列1)模式";
			if (str1_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(1).getMode()+"");
			}
			//(WAN1)(队列1)START门限
			String str1_startthreshold = "(WAN" + i + ")(队列1)START门限";
			if (str1_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(1).getStartThreshold()+"");
			}
			//(WAN1)(队列1)END门限
			String str1_endthreshold = "(WAN" + i + ")(队列1)END门限";
			if (str1_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(1).getEndThreshold()+"");
			}
			
			
			// (WAN1)(队列缓存管理策略)(队列2)模式
			String str2_mode = "(WAN" + i + ")(队列缓存管理策略)(队列2)模式";
			if (str2_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(2).getMode()+"");
			}
			//(WAN1)(队列2)START门限
			String str2_startthreshold = "(WAN" + i + ")(队列2)START门限";
			if (str2_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(2).getStartThreshold()+"");
			}
			//(WAN1)(队列2)END门限
			String str2_endthreshold = "(WAN" + i + ")(队列2)END门限";
			if (str2_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(2).getEndThreshold()+"");
			}
			

			// (WAN1)(队列缓存管理策略)(队列3)模式
			String str3_mode = "(WAN" + i + ")(队列缓存管理策略)(队列3)模式";
			if (str3_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(3).getMode()+"");
			}
			//(WAN1)(队列3)START门限
			String str3_startthreshold = "(WAN" + i + ")(队列3)START门限";
			if (str3_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(3).getStartThreshold()+"");
			}
			//(WAN1)(队列3)END门限
			String str3_endthreshold = "(WAN" + i + ")(队列3)END门限";
			if (str3_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(3).getEndThreshold()+"");
			}
			

			// (WAN1)(队列缓存管理策略)(队列4)模式
			String str4_mode = "(WAN" + i + ")(队列缓存管理策略)(队列4)模式";
			if (str4_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(4).getMode()+"");
			}
			//(WAN1)(队列4)START门限
			String str4_startthreshold = "(WAN" + i + ")(队列4)START门限"; 
			if (str4_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(4).getStartThreshold()+"");
			}
			//(WAN1)(队列0)END门限
			String str4_endthreshold = "(WAN" + i + ")(队列4)END门限";
			if (str4_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(4).getEndThreshold()+"");
			}
			

			// (WAN1)(队列缓存管理策略)(队列5)模式
			String str5_mode = "(WAN" + i + ")(队列缓存管理策略)(队列5)模式";
			if (str5_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(5).getMode()+"");
			}
			//(WAN1)(队列5)START门限
			String str5_startthreshold = "(WAN" + i + ")(队列5)START门限"; 
			if (str5_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(5).getStartThreshold()+"");
			}
			//(WAN1)(队列5)END门限
			String str5_endthreshold = "(WAN" + i + ")(队列5)END门限";
			if (str5_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(5).getEndThreshold()+"");
			}
			

			// (WAN1)(队列缓存管理策略)(队列6)模式
			String str6_mode = "(WAN" + i + ")(队列缓存管理策略)(队列6)模式";
			if (str6_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(6).getMode()+"");
			}
			//(WAN1)(队列6)START门限
			String str6_startthreshold = "(WAN" + i + ")(队列6)START门限"; 
			if (str6_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(6).getStartThreshold()+"");
			}
			//(WAN1)(队列6)END门限
			String str6_endthreshold = "(WAN" + i + ")(队列6)END门限";
			if (str6_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(6).getEndThreshold()+"");
			}
			

			// (WAN1)(队列缓存管理策略)(队列7)模式
			String str7_mode = "(WAN" + i + ")(队列缓存管理策略)(队列7)模式";
			if (str7_mode.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(7).getMode()+"");
			}
			//(WAN1)(队列7)START门限
			String str7_startthreshold = "(WAN" + i + ")(队列7)START门限"; 
			if (str7_startthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(7 ).getStartThreshold()+"");
			}
			//(WAN1)(队列7)END门限
			String str7_endthreshold = "(WAN" + i + ")(队列7)END门限";
			if (str7_endthreshold.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBufferList().get(7).getEndThreshold()+"");
			}
		}
		
	}
	
	/**
	 * 其他的一些赋值
	 * @param portSeniorConfig
	 * @param driveattribute
	 */
	public void OtherTODriveAttribute(PortSeniorConfig portSeniorConfig, DriveAttribute driveattribute,int i){
		
		if(i<23){
			// (LAN1)出口限速
			String str1 = "(LAN" + i + ")出口限速";
			if (str1.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getPortLimitation()+"");
			}

			// (LAN1)广播包抑制
			String str2 = "(LAN" + i + ")广播包抑制";
			if (str2.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBroadcastBate()+"");
			}

			// (LAN1)广播包流量
			String str3 = "(LAN" + i + ")广播包流量";
			if (str3.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBroadcastFlux()+"");
			}

			// (LAN1)组播包抑制
			String str4 = "(LAN" + i + ")组播包抑制";
			if (str4.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getGroupBroadcastBate()+"");
			}

			// (LAN1)组播包流量
			String str5 = "(LAN" + i + ")组播包流量";
			if (str5.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getGroupBroadcastFlux()+"");
			}

			// (LAN1)洪泛包抑制
			String str6 = "(LAN" + i + ")洪泛包抑制";
			if (str6.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getFloodBate()+"");
			}

			// (LAN1)洪泛包流量
			String str7 = "(LAN" + i + ")洪泛包流量";
			if (str7.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getFloodFlux()+"");
			}
		}else{
			// (WAN1)出口限速
			String str1 = "(WAN" + i + ")出口限速";
			if (str1.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getPortLimitation()+"");
			}

			// (WAN1)广播包抑制
			String str2 = "(WAN" + i + ")广播包抑制";
			if (str2.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBroadcastBate()+"");
			}

			// (WAN1)广播包流量
			String str3 = "(WAN" + i + ")广播包流量";
			if (str3.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getBroadcastFlux()+"");
			}

			// (WAN1)组播包抑制
			String str4 = "(WAN" + i + ")组播包抑制";
			if (str4.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getGroupBroadcastBate()+"");
			}

			// (WAN1)组播包流量
			String str5 = "(WAN" + i + ")组播包流量";
			if (str5.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getGroupBroadcastFlux()+"");
			}

			// (WAN1)洪泛包抑制
			String str6 = "(WAN" + i + ")洪泛包抑制";
			if (str6.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getFloodBate()+"");
			}

			// (WAN1)洪泛包流量
			String str7 = "(WAN" + i + ")洪泛包流量";
			if (str7.equals(driveattribute.getDescription())) {
				driveattribute.setValue(portSeniorConfig.getFloodFlux()+"");
			}
		}
	}

	/**
	 * driveAttribute值赋给PortSeniorConfig
	 * 
	 * @param PortSeniorConfig
	 * @param driveAttribute
	 */	
	public void DriveAttributeToPortSeniorConfig(PortSeniorConfig portSeniorConfig, DriveAttribute driveattribute ,StringBuffer sb) {
		
		//driveattributeTO优先级
		DriveAttributeTOPriority(portSeniorConfig, driveattribute,sb);
		
		//driveattributeTO出口策略
		DriveAttributeTOPortLAGexitQueue(portSeniorConfig, driveattribute);
		
		//driveattributeTO队列缓存管理策略
		DriveAttributeTOPortLAGbuffer(portSeniorConfig, driveattribute);
		
		//driveattributeTO其他属性
		DriveAttributeTOOther(portSeniorConfig, driveattribute);
	}
	
	/**
	 * driveattributeTO优先级
	 * @param PortSeniorConfig
	 * @param driveattribute
	 * @param i
	 * @param sb
	 */
	public void DriveAttributeTOPriority(PortSeniorConfig portSeniorConfig,DriveAttribute driveattribute,StringBuffer sb){

			// (LAN1)优先级0
			String str1 = "(LAN1)优先级0";
			if (str1.equals(driveattribute.getDescription())) {
				sb.append(driveattribute.getValue()+",");
			}

			// (LAN1)优先级1
			String str2 = "(LAN1)优先级1";
			if (str2.equals(driveattribute.getDescription())) {
				sb.append(driveattribute.getValue()+",");
			}

			// (LAN1)优先级2
			String str3 = "(LAN1)优先级2";
			if (str3.equals(driveattribute.getDescription())) {
				sb.append(driveattribute.getValue()+",");
			}

			// (LAN1)优先级3
			String str4 = "(LAN1)优先级3";
			if (str4.equals(driveattribute.getDescription())) {
				sb.append(driveattribute.getValue()+",");
			}

			// (LAN1)优先级4
			String str5 = "(LAN1)优先级4";
			if (str5.equals(driveattribute.getDescription())) {
				sb.append(driveattribute.getValue()+",");
			}

			// (LAN1)优先级5
			String str6 = "(LAN1)优先级5";
			if (str6.equals(driveattribute.getDescription())) {
				sb.append(driveattribute.getValue()+",");
			}

			// (LAN1)优先级6
			String str7 = "(LAN1)优先级6";
			if (str7.equals(driveattribute.getDescription())) {
				sb.append(driveattribute.getValue()+",");				
			}

			// (LAN1)优先级7
			String str8 = "(LAN1)优先级7";
			if (str8.equals(driveattribute.getDescription())) {
				sb.append(driveattribute.getValue());
				portSeniorConfig.setPriority(sb.toString());
			}
	}
		
	/**
	 * driveattributeTO出口策略
	 * @param PortSeniorConfig		
	 * @param driveattribute
	 * @param i
	 */
	public void DriveAttributeTOPortLAGexitQueue(PortSeniorConfig portSeniorConfig, DriveAttribute driveattribute){
		
			// (LAN1)(出口队列调度策略)(队列0)模式
			String str0_mode = "(LAN1)(出口队列调度策略)(队列0)模式";
			if (str0_mode.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(0).setMode(Integer.parseInt(driveattribute.getValue()));
			}
			//(LAN1)(队列0)权重
			String str0_weight = "(LAN1)(队列0)权重";
			if (str0_weight.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(0).setPopedom(Integer.parseInt(driveattribute.getValue()));
			}
			
			// (LAN1)(出口队列调度策略)(队列1)模式
			String str1_mode = "(LAN1)(出口队列调度策略)(队列1)模式";
			if (str1_mode.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(1).setMode(Integer.parseInt(driveattribute.getValue()));
			}
			//(LAN1)(队列1)权重
			String str1_weight = "(LAN1)(队列1)权重";
			if (str1_weight.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(1).setPopedom(Integer.parseInt(driveattribute.getValue()));
			}
			
			// (LAN1)(出口队列调度策略)(队列2)模式
			String str2_mode = "(LAN1)(出口队列调度策略)(队列2)模式";
			if (str2_mode.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(2).setMode(Integer.parseInt(driveattribute.getValue()));
			}
			//(LAN1)(队列2)权重
			String str2_weight = "(LAN1)(队列2)权重";
			if (str2_weight.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(2).setPopedom(Integer.parseInt(driveattribute.getValue()));
			}

			// (LAN1)(出口队列调度策略)(队列3)模式
			String str3_mode = "(LAN1)(出口队列调度策略)(队列3)模式";
			if (str3_mode.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(3).setMode(Integer.parseInt(driveattribute.getValue()));
			}
			//(LAN1)(队列3)权重
			String str3_weight = "(LAN1)(队列3)权重";
			if (str3_weight.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(3).setPopedom(Integer.parseInt(driveattribute.getValue()));
			}

			// (LAN1)(出口队列调度策略)(队列4)模式
			String str4_mode = "(LAN1)(出口队列调度策略)(队列4)模式";
			if (str4_mode.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(4).setMode(Integer.parseInt(driveattribute.getValue()));
			}
			//(LAN1)(队列4)权重
			String str4_weight = "(LAN1)(队列4)权重"; 
			if (str4_weight.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(4).setPopedom(Integer.parseInt(driveattribute.getValue()));
			}

			// (LAN1)(出口队列调度策略)(队列5)模式
			String str5_mode = "(LAN1)(出口队列调度策略)(队列5)模式";
			if (str5_mode.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(5).setMode(Integer.parseInt(driveattribute.getValue()));
			}
			//(LAN1)(队列5)权重
			String str5_weight = "(LAN1)(队列5)权重"; 
			if (str5_weight.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(5).setPopedom(Integer.parseInt(driveattribute.getValue()));
			}

			// (LAN1)(出口队列调度策略)(队列6)模式
			String str6_mode = "(LAN1)(出口队列调度策略)(队列6)模式";
			if (str6_mode.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(6).setMode(Integer.parseInt(driveattribute.getValue()));
			}
			//(LAN1)(队列6)权重
			String str6_weight = "(LAN1)(队列6)权重"; 
			if (str6_weight.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(6).setPopedom(Integer.parseInt(driveattribute.getValue()));
			}

			// (LAN1)(出口队列调度策略)(队列7)模式
			String str7_mode = "(LAN1)(出口队列调度策略)(队列7)模式";
			if (str7_mode.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(7).setMode(Integer.parseInt(driveattribute.getValue()));
			}
			//(LAN1)(队列7)权重
			String str7_weight = "(LAN1)(队列7)权重"; 
			if (str7_weight.equals(driveattribute.getDescription())) {
				portSeniorConfig.getExitQueueList().get(7).setPopedom(Integer.parseInt(driveattribute.getValue()));
			}
	}

	/**
	 * driveattributeTO队列缓存管理策略
	 * @param PortSeniorConfig
	 * @param driveattribute
	 * @param i
	 */
	public void DriveAttributeTOPortLAGbuffer(PortSeniorConfig portSeniorConfig, DriveAttribute driveattribute){
		
		// (LAN1)(队列缓存管理策略)(队列0)模式
		String str0_mode = "(LAN1)(队列缓存管理策略)(队列0)模式";
		if (str0_mode.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(0).setMode(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列0)START门限
		String str0_startthreshold = "(LAN1)(队列0)START门限";
		if (str0_startthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(0).setStartThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列0)END门限
		String str0_ENDthreshold = "(LAN1)(队列0)END门限";
		if (str0_ENDthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(0).setEndThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		
		
		// (LAN1)(队列缓存管理策略)(队列1)模式
		String str1_mode = "(LAN1)(队列缓存管理策略)(队列1)模式";
		if (str1_mode.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(1).setMode(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列1)START门限
		String str1_startthreshold = "(LAN1)(队列1)START门限";
		if (str1_startthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(1).setStartThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列1)END门限
		String str1_ENDthreshold = "(LAN1)(队列1)END门限";
		if (str1_ENDthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(1).setEndThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		
		
		// (LAN1)(队列缓存管理策略)(队列2)模式
		String str2_mode = "(LAN1)(队列缓存管理策略)(队列2)模式";
		if (str2_mode.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(2).setMode(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列2)START门限
		String str2_startthreshold = "(LAN1)(队列2)START门限";
		if (str2_startthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(2).setStartThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列2)END门限
		String str2_ENDthreshold = "(LAN1)(队列2)END门限";
		if (str2_ENDthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(2).setEndThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		

		// (LAN1)(队列缓存管理策略)(队列3)模式
		String str3_mode = "(LAN1)(队列缓存管理策略)(队列3)模式";
		if (str3_mode.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(3).setMode(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列3)START门限
		String str3_startthreshold = "(LAN1)(队列3)START门限";
		if (str3_startthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(3).setStartThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列3)END门限
		String str3_ENDthreshold = "(LAN1)(队列3)END门限";
		if (str3_ENDthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(3).setEndThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		

		// (LAN1)(队列缓存管理策略)(队列4)模式
		String str4_mode = "(LAN1)(队列缓存管理策略)(队列4)模式";
		if (str4_mode.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(4).setMode(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列4)START门限
		String str4_startthreshold = "(LAN1)(队列4)START门限"; 
		if (str4_startthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(4).setStartThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列4)END门限
		String str4_ENDthreshold = "(LAN1)(队列4)END门限";
		if (str4_ENDthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(4).setEndThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		

		// (LAN1)(队列缓存管理策略)(队列5)模式
		String str5_mode = "(LAN1)(队列缓存管理策略)(队列5)模式";
		if (str5_mode.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(5).setMode(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列5)START门限
		String str5_startthreshold = "(LAN1)(队列5)START门限"; 
		if (str5_startthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(5).setStartThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列5)END门限
		String str5_ENDthreshold = "(LAN1)(队列5)END门限";
		if (str5_ENDthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(5).setEndThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		

		// (LAN1)(队列缓存管理策略)(队列6)模式
		String str6_mode = "(LAN1)(队列缓存管理策略)(队列6)模式";
		if (str6_mode.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(6).setMode(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列6)START门限
		String str6_startthreshold = "(LAN1)(队列6)START门限"; 
		if (str6_startthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(6).setStartThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列6)END门限
		String str6_ENDthreshold = "(LAN1)(队列6)END门限";
		if (str6_ENDthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(6).setEndThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		

		// (LAN1)(队列缓存管理策略)(队列7)模式
		String str7_mode = "(LAN1)(队列缓存管理策略)(队列7)模式";
		if (str7_mode.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(7).setMode(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列7)START门限
		String str7_startthreshold = "(LAN1)(队列7)START门限"; 
		if (str7_startthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(7).setStartThreshold(Integer.parseInt(driveattribute.getValue()));
		}
		//(LAN1)(队列7)END门限
		String str7_ENDthreshold = "(LAN1)(队列7)END门限";
		if (str7_ENDthreshold.equals(driveattribute.getDescription())) {
			portSeniorConfig.getBufferList().get(7).setEndThreshold(Integer.parseInt(driveattribute.getValue()));
		}
	}
	
	/**
	 * driveattributeTO其他属性
	 * @param PortSeniorConfig
	 * @param driveattribute
	 * @param i
	 */
	public void DriveAttributeTOOther(PortSeniorConfig portSeniorConfig, DriveAttribute driveattribute){
		
			// (LAN1)出口限速
			String str1 = "(LAN1)出口限速";
			if (str1.equals(driveattribute.getDescription())) {
				portSeniorConfig.setPortLimitation(Integer.parseInt(driveattribute.getValue()));
			}

			// (LAN1)广播包抑制
			String str2 = "(LAN1)广播包抑制";
			if (str2.equals(driveattribute.getDescription())) {
				portSeniorConfig.setBroadcastBate(Integer.parseInt(driveattribute.getValue()));
			}

			// (LAN1)广播包流量
			String str3 = "(LAN1)广播包流量";
			if (str3.equals(driveattribute.getDescription())) {
				portSeniorConfig.setBroadcastFlux(Integer.parseInt(driveattribute.getValue()));
			}

			// (LAN1)组播包抑制
			String str4 = "(LAN1)组播包抑制";
			if (str4.equals(driveattribute.getDescription())) {
				portSeniorConfig.setGroupBroadcastBate(Integer.parseInt(driveattribute.getValue()));
			}

			// (LAN1)组播包流量
			String str5 = "(LAN1)组播包流量";
			if (str5.equals(driveattribute.getDescription())) {
				portSeniorConfig.setGroupBroadcastFlux(Integer.parseInt(driveattribute.getValue()));
			}

			// (LAN1)洪泛包抑制
			String str6 = "(LAN1)洪泛包抑制";
			if (str6.equals(driveattribute.getDescription())) {
				portSeniorConfig.setFloodBate(Integer.parseInt(driveattribute.getValue()));
			}

			// (LAN1)洪泛包流量
			String str7 = "(LAN1)洪泛包流量";
			if (str7.equals(driveattribute.getDescription())) {
				portSeniorConfig.setFloodFlux(Integer.parseInt(driveattribute.getValue()));
			}		
	}
}
