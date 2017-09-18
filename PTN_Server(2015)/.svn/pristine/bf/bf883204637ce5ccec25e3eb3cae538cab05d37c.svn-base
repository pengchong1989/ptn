package com.nms.drive.analysis.datablock.status;


import com.nms.drive.analysis.datablock.AnalysisBase;
import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.InsertLinkOamObject;

public class AnalysisInsertLinkOamTable extends AnalysisBase{
	
	/**
	 * 根据字节数组生成对象
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public InsertLinkOamObject analysisCommandToObject(byte[] commands,String configXml) throws Exception {
		int start = 0;
		AnalysisCommandByDriveXml analysisCommandByDriveXml = null;
		DriveRoot driveRoot_config =null;
		InsertLinkOamObject InsertLinkOamObject = null;
		try {
			InsertLinkOamObject = new InsertLinkOamObject();
			// 起始长度
			start = 0;// 131
			byte[] a = new byte[commands.length-6];
			System.arraycopy(commands, 6, a, 0, a.length);
			analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			
			for (int i = 0, y = driveRoot_config.getDriveAttributeList().size(); i < y; i++) {
				DriveAttribute driveAttribute = driveRoot_config.getDriveAttributeList().get(i);
				// 赋值
				DriveAttributeTOInsertLinkOamObject(InsertLinkOamObject, driveAttribute);
			}
			
		} catch (Exception e) {
			throw e;
		}
		return InsertLinkOamObject;
	}

	private void DriveAttributeTOInsertLinkOamObject(InsertLinkOamObject InsertLinkOamObject,DriveAttribute driveAttribute) throws Exception{
		
		try {
			 // 误码事件发生时间
		    if ("误码事件发生时间".equals(driveAttribute.getDescription())) {
				 InsertLinkOamObject.setErrorEventTime(driveAttribute.getValue());
				}
			 
			// 误码事件监视周期
			if ("误码事件监视周期".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorEventMonitorCycle(driveAttribute.getValue());
			}
			// 误码事件阈值
			if ("误码事件阈值".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorEventThreshold(Integer.parseInt(driveAttribute.getValue()));
			}
			// 误码事件错误数
			if ("误码事件错误数".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorSize(Integer.parseInt(driveAttribute.getValue()));
			}
			// 误码事件错误数
			if ("误码事件错误总数".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorECountSize(Integer.parseInt(driveAttribute.getValue()));
			}
			// 误码事件误码错误事件总数
			if ("误码事件误码错误事件总数".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorEventCountSize(Integer.parseInt(driveAttribute.getValue()));
			}
			
			//-------------------------------------------------
			
			 
			 // 错误帧周期事件发生时间
			 if ("错误帧周期事件发生时间".equals(driveAttribute.getDescription())) {
				 InsertLinkOamObject.setErrorFrameCycleTime(driveAttribute.getValue());
				}
			 
			// 错误帧周期事件监视周期
			if ("错误帧周期事件监视周期".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorFrameMonitorCycle(driveAttribute.getValue());
			}
			// 错误帧周期事件阈值
			if ("错误帧周期事件阈值".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorFrameCycleThreshold(Integer.parseInt(driveAttribute.getValue()));
			}
			// 错误帧周期错误数
			if ("错误帧周期错误数".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorFrameSize(Integer.parseInt(driveAttribute.getValue()));
			}
			// 错误帧周期错误总数
			if ("错误帧周期错误总数".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorFCountSize(Integer.parseInt(driveAttribute.getValue()));
			}
			// 错误帧周期错误帧周期事件总数
			if ("错误帧周期错误帧周期事件总数".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorFrameCycleCountSize(Integer.parseInt(driveAttribute.getValue()));
			}
			
			//-------------------------------------------------
			
			 if ("错误帧事件发生时间".equals(driveAttribute.getDescription())) {
				// 错误帧周期事件发生时间
				 InsertLinkOamObject.setErrorFrameEventTime(driveAttribute.getValue());
				}
			 
			// 错误帧事件监视周期
			if ("错误帧事件监视周期".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorFrameEventMonitorCycle(driveAttribute.getValue());
			}
			// 错误帧事件错误帧事件阈值
			if ("错误帧事件错误帧事件阈值".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorFrameEventThreshold(Integer.parseInt(driveAttribute.getValue()));
			}
			// 错误帧事件错误数
			if ("错误帧事件错误数".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorFrameEventSize(Integer.parseInt(driveAttribute.getValue()));
			}
			// 错误帧事件错误总数
			if ("错误帧事件错误总数".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorFrameCountSize(Integer.parseInt(driveAttribute.getValue()));
			}
			// 错误帧事件错误帧事件总数
			if ("错误帧事件错误帧事件总数".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorFrameEventCountSize(Integer.parseInt(driveAttribute.getValue()));
			}
			
			//---------------------------------
			// 错误帧周期事件发生时间
			 if ("错误秒帧事件发生时间".equals(driveAttribute.getDescription())) {
				 InsertLinkOamObject.setErrorSecondsEventTime(driveAttribute.getValue());
				}
			 
			// 错误秒帧事件监视周期
			if ("错误秒帧事件监视周期".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorSecondsEventMonitorCycle(driveAttribute.getValue());
			}
			//错误秒帧事件阈值
			if ("错误秒帧事件阈值".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorSecondsEvenThreshold(Integer.parseInt(driveAttribute.getValue()));
			}
			// 错误秒帧事件错误数
			if ("错误秒帧事件错误数".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorSecondsEvenSize(Integer.parseInt(driveAttribute.getValue()));
			}
			// 错误秒帧事件错误总数
			if ("错误秒帧事件错误总数".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorSecondsEventCountSize(Integer.parseInt(driveAttribute.getValue()));
			}
			// 错误秒帧事件错误秒帧事件总数
			if ("错误秒帧事件错误秒帧事件总数".equals(driveAttribute.getDescription())) {
				InsertLinkOamObject.setErrorSecondsEvenFrameCountSize(Integer.parseInt(driveAttribute.getValue()));
			}
			
		} catch (Exception e) {
			throw e;
		}
	}

}
