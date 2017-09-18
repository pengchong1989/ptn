package com.nms.drive.analysis.datablock;


import com.nms.db.bean.ptn.clock.PtpPortinfo;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.TimeSyncObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisTimeSyncTable extends AnalysisBase {
	private String ptpPortObjPath = "com/nms/drive/analysis/xmltool/file/时间同步配置块(1CH)_sub.xml";	
	
	public byte[] analysisTimeSyncObjectToCommands(TimeSyncObject timeSyncObject, String configXml)throws Exception {
		
		// 获取根目录
		DriveRoot timeSyncDrivRoot = super.LoadDriveXml(configXml);
		timeSyncDrivRoot.getDriveAttributeList().get(0).getListRootList().clear();// 清除本身自带的一个ListRoot

		

		for (int i = 0, y = timeSyncDrivRoot.getDriveAttributeList().size(); i < y; i++) {
			DriveAttribute driveAttribute = timeSyncDrivRoot.getDriveAttributeList().get(i);
			// 赋值
			attribute2TimeSyncObject(driveAttribute,timeSyncObject);
		}
		// 将集合转换成命令
		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(timeSyncDrivRoot);
		CoderUtils.print16String(dataCommand);
		return dataCommand;

	}
	

	
	private void attribute2TimeSyncObject(DriveAttribute driveAttribute, TimeSyncObject timeSyncObj)
	{	
		if ("PTP模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getPtpModel()+"");		
		}
		else if ("时钟模型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getClockModel()+"");
		}
		else if ("源时钟模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getSrcclockModel()+"");
		}
		
		else if ("源时钟ID1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(Integer.parseInt(timeSyncObj.getSrcclockId1(),16)+"");
			
		}
		else if ("源时钟ID2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(Integer.parseInt(timeSyncObj.getSrcclockId2(),16)+"");
			
		}
		else if ("源时钟ID3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(Integer.parseInt(timeSyncObj.getSrcclockId3(),16)+"");
			
		}	
		else if ("源时钟类型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getSrcclocktype()+"");
		}
		else if ("源时钟优先级1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getSrcclockpri1()+"");
		}
		else if ("源时钟优先级2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getSrcclockpri2()+"");
		}
		else if ("源时钟等级".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getSrcclockLevel()+"");
		}
		else if ("源时钟精度".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getSrcclockaccuray()+"");
		}
		else if ("Slave_Only模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getSlaveOnlyModel()+"");
		}
		else if ("输入补偿属性".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getInCompensation()+"");
		}
		else if ("输入时延补偿值".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getInDelay()+"");
		}
		else if ("输出补偿属性".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getOutCompensation()+"");
		}
		else if ("输出时延补偿值".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getOutDelay()+"");		
		}
		else if ("同步周期".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getSynCicle()+"");
		}
		else if ("通告周期".equals(driveAttribute.getDescription())) {	
			driveAttribute.setValue(timeSyncObj.getNoteCicle()+"");
		}
		else if ("时间信息接口".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getTimeInfoIt()+"");
		}		
		else if ("Sync报文发包频率".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getSyncFreq()+"");
		}
		else if ("Delay_Req报文发包频率".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getDelayFreq()+"");
		}
		else if ("Announce报文发包频率".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getAnnounceFreq()+"");
		}
		else if ("Delay_Req 报文超时系数".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getDelayOver()+"");
		}
		else if ("Announce报文超时系数".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getAnnounceOver()+"");
		}
		else if ("DomainNumber".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(timeSyncObj.getDomainNumbe()+"");
		}else if ("PTP端口配置条目数".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(timeSyncObj.getPtpPortinfo().size()+ "");
			String file = driveAttribute.getListRootList().get(0).getFile();
			driveAttribute.getListRootList().clear();// 清除自带的listRoot
			for (int i = 0; i < timeSyncObj.getPtpPortinfo().size(); i++) {
				DriveRoot driveRoot;
				try {
					driveRoot = super.LoadDriveXml(file);
					ListRoot listRoot = new ListRoot();
					listRoot.setDriveAttributeList(driveRoot.getDriveAttributeList());			
					for (int j = 0; j < listRoot.getDriveAttributeList().size(); j++) {
					ptpPortObject2DriveAttribute(listRoot.getDriveAttributeList().get(j), timeSyncObj.getPtpPortinfo().get(i));
					}
					driveAttribute.getListRootList().add(listRoot);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		} 
			
		
	}
	

	private void ptpPortObject2DriveAttribute(DriveAttribute driveAttribute,PtpPortinfo ptpPortinfo )
	{
		if ("索引".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortinfo.getIndexId()+"");
		}
		else if ("端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortinfo.getPortNum()+"");
		}
		else if ("工作模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortinfo.getWorkModel()+"");
		}
		else if ("端口ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortinfo.getPortId()+"");
		}
		else if ("线路不对称时延属性".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortinfo.getLine()+"");
		}
		else if ("线路不对称时延补偿值".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortinfo.getLineCpn()+"");
		}
		else if ("延时机制".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortinfo.getDelayMeca()+"");
		}
		else if ("消息模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ptpPortinfo.getMessageMode()+"");
		}
	}
	public TimeSyncObject analysisCommandsToTimesyncObject(byte[] commands,String configXml) throws Exception {
		CoderUtils.print16String(commands);
		int pointer = 0;// 当前commands下标
		byte[] timeSyncByte = new byte[61];// 存放timeSynchronizeObject的命令
		byte[] ptpPortByte = new byte[20];// 存放ptpPortByte的命令
		System.arraycopy(commands, pointer, timeSyncByte, 0, timeSyncByte.length);	
		
		try {
			// 生成timeSynchronizeObject对象
			TimeSyncObject timeSyncObject = getTimeSynchronizeObject(commands, configXml);
			pointer += 60;// 移动下标
			int ptpPortCount = timeSyncObject.getPtpNum();// 获取条目数

			PtpPortinfo ptpPortinfo = null;
			for (int i = 0; i < ptpPortCount; i++) {
				System.arraycopy(commands, pointer+1, ptpPortByte, 0, ptpPortByte.length );				
				// 生成ptpPortObject对象
				ptpPortinfo = getPTPPortObject(ptpPortByte, ptpPortObjPath);
				pointer += 20;// 移动下标
				// 添加ptpPortObject入timeSynchronizeObject
				timeSyncObject.getPtpPortinfo().add(ptpPortinfo);
			}
	

		     return  timeSyncObject;
		} catch (Exception e) {
			throw e;
		}
		
	}

		/**
		 * 根据命令获取PTPPortObject对象
		 * 
		 * @param commands
		 * @param configXml
		 * @return
		 * @throws Exception
		 */
		private PtpPortinfo getPTPPortObject(byte[] commands, String configXml) throws Exception {
			PtpPortinfo ptpPortinfo = new PtpPortinfo();
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			try {
				DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
				DriveAttribute driveAttribute = null;
				for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
					driveAttribute = driveRoot.getDriveAttributeList().get(i);
					assignPTPPortObject(ptpPortinfo, driveAttribute);// 给ptpPortObj赋值
				}
			} catch (Exception e) {
				throw e;
			}

			return ptpPortinfo;
		}

		
		/**
		 * 根据命令获取timeSynchronizeObject对象
		 * 
		 * @param commands
		 * @param configXml
		 * @return
		 * @throws Exception
		 */
		private TimeSyncObject getTimeSynchronizeObject(byte[] commands, String configXml) throws Exception {			
			TimeSyncObject timeSynchronizeObject = new TimeSyncObject();
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();	
			analysisCommandByDriveXml.setCommands(commands);
			try {
				DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
				DriveAttribute driveAttribute = null;
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
					driveAttribute = driveRoot.getDriveAttributeList().get(i);
					assignTimeSynchronzieObject(timeSynchronizeObject, driveAttribute, sb);// 给timeSynchronizeObject赋值					
				}
			} catch (Exception e) {
				throw e;
			}

			return timeSynchronizeObject;

		}
		/**
		 * 给TimeSynchronzieObject对象赋值
		 * 
		 * @param timeSynchronizeObject
		 * @param driveAttribute
		 */
		private void assignTimeSynchronzieObject(TimeSyncObject timeSynchronizeObject, DriveAttribute driveAttribute, StringBuffer sb) {						
			if ("PTP模式".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setPtpModel(Integer.parseInt(driveAttribute.getValue()));	
			}
			else if ("时钟模型".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setClockModel(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("源时钟模式".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setSrcclockModel(Integer.parseInt(driveAttribute.getValue()));
			}
			
			else if ("源时钟ID1".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setSrcclockId1(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())));
				
			}
			else if ("源时钟ID2".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setSrcclockId2(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())));
				
			}
			else if ("源时钟ID3".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setSrcclockId3(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())));
				
			}	
			else if ("源时钟类型".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setSrcclocktype(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("源时钟优先级1".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setSrcclockpri1(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("源时钟优先级2".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setSrcclockpri2(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("源时钟等级".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setSrcclockLevel(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("源时钟精度".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setSrcclockaccuray(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("Slave_Only模式".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setSlaveOnlyModel(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("输入补偿属性".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setInCompensation(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("输入时延补偿值".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setInDelay(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("输出补偿属性".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setOutCompensation(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("输出时延补偿值".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setOutDelay(Integer.parseInt(driveAttribute.getValue()));		
			}
			else if ("同步周期".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setSynCicle(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("通告周期".equals(driveAttribute.getDescription())) {	
				timeSynchronizeObject.setNoteCicle(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("时间信息接口".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setTimeInfoIt(Integer.parseInt(driveAttribute.getValue()));
			}		
			else if ("Sync报文发包频率".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setSyncFreq(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("Delay_Req报文发包频率".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setDelayFreq(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("Announce报文发包频率".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setAnnounceFreq(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("Delay_Req 报文超时系数".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setDelayOver(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("Announce报文超时系数".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setAnnounceOver(Integer.parseInt(driveAttribute.getValue()));
			}
			else if ("DomainNumber".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setDomainNumbe(Integer.parseInt(driveAttribute.getValue()));
		   } else if ("PTP端口配置条目数".equals(driveAttribute.getDescription())) {
				timeSynchronizeObject.setPtpNum(Integer.parseInt(driveAttribute.getValue()));
			}
			
		}

		/**
		 * 给PTPPortObject对象赋值
		 * 
		 * @param timeSynchronizeObject
		 * @param driveAttribute
		 */
		private void assignPTPPortObject(PtpPortinfo ptpPortObject, DriveAttribute driveAttribute) {
			if ("索引".equals(driveAttribute.getDescription())) {
				ptpPortObject.setIndexId(Integer.parseInt(driveAttribute.getValue()));
			} else if ("端口号".equals(driveAttribute.getDescription())) {
				ptpPortObject.setPortNum(Integer.parseInt(driveAttribute.getValue()));
			} else if ("工作模式".equals(driveAttribute.getDescription())) {
				ptpPortObject.setWorkModel(Integer.parseInt(driveAttribute.getValue()));
				System.out.println(ptpPortObject.getWorkModel()+"gongzuo");
			} else if ("端口ID".equals(driveAttribute.getDescription())) {
				ptpPortObject.setPortId(Integer.parseInt(driveAttribute.getValue()));
			} else if ("线路不对称时延属性".equals(driveAttribute.getDescription())) {
				ptpPortObject.setLine(Integer.parseInt(driveAttribute.getValue()));
			} else if ("线路不对称时延补偿值".equals(driveAttribute.getDescription())) {
				ptpPortObject.setLineCpn(Integer.parseInt(driveAttribute.getValue()));
			} else if ("延时机制".equals(driveAttribute.getDescription())) {
				ptpPortObject.setDelayMeca(Integer.parseInt(driveAttribute.getValue()));
			} else if ("消息模式".equals(driveAttribute.getDescription())) {
				ptpPortObject.setMessageMode(Integer.parseInt(driveAttribute.getValue()));				
			} 
		}
}
