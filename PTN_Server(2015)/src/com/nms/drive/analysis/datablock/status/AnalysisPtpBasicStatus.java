package com.nms.drive.analysis.datablock.status;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.PtpBasicStatusObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisPtpBasicStatus extends AnalysisBase{
	private StringBuffer localTime = new StringBuffer();
	private StringBuffer syncSourceTime = new StringBuffer();
	private StringBuffer syncTime = new StringBuffer();

	/**
	 * 命令转对象
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public List<PtpBasicStatusObject> analysisCommandsToPtpBasicConfigObject(byte[] commands ,String configXml) throws Exception{
		
		List<PtpBasicStatusObject> ptpPortStatusList = new ArrayList<PtpBasicStatusObject>();
		int number = commands[24]*256+CoderUtils.bytesToInt(commands[25]);
		for (int i = 0; i < number; i++) {
			PtpBasicStatusObject ptpBasicStatus = new PtpBasicStatusObject();
			byte[] a = new byte[200];
			System.arraycopy(commands, 26+a.length*i, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {
				DriveAttribute driveattribute = driveRoot_config.getDriveAttributeList().get(j);
				driveattributeToObject(driveattribute, ptpBasicStatus);
			}
			ptpPortStatusList.add(ptpBasicStatus);
		}
		return ptpPortStatusList;
	}
	
	public void driveattributeToObject(DriveAttribute driveattribute,PtpBasicStatusObject ptpBasicStatus){
		
		if("ptp模式".equals(driveattribute.getDescription())){
			ptpBasicStatus.setPtpModel(Integer.parseInt(driveattribute.getValue()));
		}else if("时钟模型".equals(driveattribute.getDescription())){
			ptpBasicStatus.setClockModel(Integer.parseInt(driveattribute.getValue()));
		}else if("Slave_Only模式".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSlaveOnlyModel(Integer.parseInt(driveattribute.getValue()));
		}else if("输入时延补偿属性".equals(driveattribute.getDescription())){
			ptpBasicStatus.setInCompensation(Integer.parseInt(driveattribute.getValue()));
		}else if("输入时延补偿值".equals(driveattribute.getDescription())){
			ptpBasicStatus.setInDelay(Integer.parseInt(driveattribute.getValue()));
		}else if("输出时延补偿属性".equals(driveattribute.getDescription())){
			ptpBasicStatus.setOutCompensation(Integer.parseInt(driveattribute.getValue()));
		}else if("输出时延补偿值".equals(driveattribute.getDescription())){
			ptpBasicStatus.setOutDelay(Integer.parseInt(driveattribute.getValue()));
		}else if("时间信息接口".equals(driveattribute.getDescription())){
			ptpBasicStatus.setTimeInfoIt(Integer.parseInt(driveattribute.getValue()));
		}else if("Sync报文发包频率".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSyncFreq(Integer.parseInt(driveattribute.getValue()));
		}else if("Delay_Req报文发包频率".equals(driveattribute.getDescription())){
			ptpBasicStatus.setDelayFreq(Integer.parseInt(driveattribute.getValue()));
		}else if("Announce报文发包频率".equals(driveattribute.getDescription())){
			ptpBasicStatus.setAnnounceFreq(Integer.parseInt(driveattribute.getValue()));
		}else if("Delay_Req 报文超时系数".equals(driveattribute.getDescription())){
			ptpBasicStatus.setDelayOver(Integer.parseInt(driveattribute.getValue()));
		}else if("Announce报文超时系数".equals(driveattribute.getDescription())){
			ptpBasicStatus.setAnnounceOver(Integer.parseInt(driveattribute.getValue()));
		}else if("DomainNumber".equals(driveattribute.getDescription())){
			ptpBasicStatus.setDomainNumbe(Integer.parseInt(driveattribute.getValue()));
		}else if("本地源时钟模式".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSrcclockModel(Integer.parseInt(driveattribute.getValue()));
		}else if("本地源时钟ID".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSrcclockId(Integer.parseInt(driveattribute.getValue()));
		}else if("本地源时钟类型".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSrcclocktype(Integer.parseInt(driveattribute.getValue()));
		}else if("本地源时钟优先级1".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSrcclockpri1(Integer.parseInt(driveattribute.getValue()));
		}else if("本地源时钟优先级2".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSrcclockpri2(Integer.parseInt(driveattribute.getValue()));
		}else if("本地源时钟等级".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSrcclockLevel(Integer.parseInt(driveattribute.getValue()));
		}else if("本地源时钟精度".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSrcclockaccuray(Integer.parseInt(driveattribute.getValue()));
		}else if("本地ptp秒".equals(driveattribute.getDescription())){
			ptpBasicStatus.setPtpSecond(Integer.parseInt(driveattribute.getValue()));
		}else if("年".equals(driveattribute.getDescription())){
			localTime.append(driveattribute.getValue() + "-");			
		}else if("月".equals(driveattribute.getDescription())){
			localTime.append(driveattribute.getValue() + "-");	
		}else if("日".equals(driveattribute.getDescription())){
			localTime.append(driveattribute.getValue() + " ");
		}else if("时".equals(driveattribute.getDescription())){
			localTime.append(driveattribute.getValue() + ":");
		}else if("分".equals(driveattribute.getDescription())){
			localTime.append(driveattribute.getValue() + ":");
		}else if("秒".equals(driveattribute.getDescription())){
			localTime.append(driveattribute.getValue());
			ptpBasicStatus.setLocalTime(localTime.toString());
		}else if("同步源时钟ID".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSyncSrcclockId(Integer.parseInt(driveattribute.getValue()));		
		}else if("Master端口ID".equals(driveattribute.getDescription())){
			ptpBasicStatus.setMasterId(Integer.parseInt(driveattribute.getValue()));		
		}else if("Slave端口ID".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSlaveId(Integer.parseInt(driveattribute.getValue()));		
		}else if("同步源时钟类型".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSyncSrcclocktype(Integer.parseInt(driveattribute.getValue()));		
		}else if("同步源时钟优先级1".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSyncSrcclockpri1(Integer.parseInt(driveattribute.getValue()));		
		}else if("同步源时钟优先级2".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSyncSrcclockpri2(Integer.parseInt(driveattribute.getValue()));		
		}else if("同步源时钟等级".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSyncSrcclockLevel(Integer.parseInt(driveattribute.getValue()));		
		}else if("同步源时钟精度".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSyncSrcclockaccuray(Integer.parseInt(driveattribute.getValue()));		
		}else if("同步源时钟ptp秒".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSyncSourcePtpSecond(Integer.parseInt(driveattribute.getValue()));		
		}else if("年1".equals(driveattribute.getDescription())){
			syncSourceTime.append(driveattribute.getValue() + "-");			
		}else if("月1".equals(driveattribute.getDescription())){
			syncSourceTime.append(driveattribute.getValue() + "-");	
		}else if("日1".equals(driveattribute.getDescription())){
			syncSourceTime.append(driveattribute.getValue() + " ");
		}else if("时1".equals(driveattribute.getDescription())){
			syncSourceTime.append(driveattribute.getValue() + ":");
		}else if("分1".equals(driveattribute.getDescription())){
			syncSourceTime.append(driveattribute.getValue() + ":");
		}else if("秒1".equals(driveattribute.getDescription())){
			syncSourceTime.append(driveattribute.getValue());
			ptpBasicStatus.setLocalTime(syncSourceTime.toString());
		}else if("同步时间ptp秒".equals(driveattribute.getDescription())){
			ptpBasicStatus.setSyncPtpSecond(Integer.parseInt(driveattribute.getValue()));		
		}else if("年2".equals(driveattribute.getDescription())){
			syncTime.append(driveattribute.getValue() + "-");			
		}else if("月2".equals(driveattribute.getDescription())){
			syncTime.append(driveattribute.getValue() + "-");	
		}else if("日2".equals(driveattribute.getDescription())){
			syncTime.append(driveattribute.getValue() + " ");
		}else if("时2".equals(driveattribute.getDescription())){
			syncTime.append(driveattribute.getValue() + ":");
		}else if("分2".equals(driveattribute.getDescription())){
			syncTime.append(driveattribute.getValue() + ":");
		}else if("秒2".equals(driveattribute.getDescription())){
			syncTime.append(driveattribute.getValue());
			ptpBasicStatus.setLocalTime(syncTime.toString());
		}else if("时间偏移属性".equals(driveattribute.getDescription())){
			ptpBasicStatus.setTimeSkewAttribute(Integer.parseInt(driveattribute.getValue()));		
		}else if("时间偏移量".equals(driveattribute.getDescription())){
			ptpBasicStatus.setTimeSkew(Integer.parseInt(driveattribute.getValue()));		
		}
		
	}
}
