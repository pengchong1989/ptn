package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.FrequencySourceObject;
import com.nms.ui.manager.ExceptionManage;

/**
 * 时钟状态变长信息
 * @author pc
 *
 */
public class AnalysisClcokChangeStatusTable extends AnalysisBase{
	public List<FrequencySourceObject> analysisCommandsToFrequencyStatusInfo(byte[] commands,String configXml){
		List<FrequencySourceObject> frequencySources = new ArrayList<FrequencySourceObject>();
		FrequencySourceObject frequencySource = null;
		try {
			for (int i = 0; i < 27; i++) {
				frequencySource = new FrequencySourceObject();
				byte[] a = new byte[4];
				System.arraycopy(commands, 24+2 + i * 4, a, 0, a.length);
				AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
				analysisCommandByDriveXml.setCommands(a);
				DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
				for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
					DriveAttribute driveattribute = driveRoot.getDriveAttributeList().get(j);
					// 属性赋值
					DriveAttributeToObject(frequencySource, driveattribute);
				}
				frequencySources.add(frequencySource);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return frequencySources;
	}
	
	public void DriveAttributeToObject(FrequencySourceObject frequencySource,DriveAttribute driveattribute){
	
		if ("时钟源名称".equals(driveattribute.getDescription())) {
			frequencySource.setClockName(Integer.parseInt(driveattribute.getValue()));
		}else if("输入源状态".equals(driveattribute.getDescription())){
			frequencySource.setSourceStatus(Integer.parseInt(driveattribute.getValue()));
		}else if("输入源QL值".equals(driveattribute.getDescription())){
			frequencySource.setSourceQL(Integer.parseInt(driveattribute.getValue()));
		}else if("输出源QL值".equals(driveattribute.getDescription())){
			frequencySource.setOutSourceQl(Integer.parseInt(driveattribute.getValue()));
		}
	}
}
