package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.PerformanceDataBlockObject;
import com.nms.drive.service.bean.PerformanceInfoObject;
import com.nms.drive.service.bean.PerformanceLineObject;
import com.nms.drive.service.bean.PerformanceMasterCardObject;
import com.nms.drive.service.bean.PerformanceObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisPerformanceTable extends AnalysisBase {
	private int allDiskSize = 7;
	private int diskSize = 12;
	private int performanceDataBlockSize = 11;
	private int performanceLineSize = 6;
	private int lineInfoSize = 3;

	/**
	 * 解析命令生成包含所有属性的PerformanceObject对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	public PerformanceObject analysiscomandToObjectBySite(byte[] commands, String configXml) {
		String diskXml = "com/nms/drive/analysis/xmltool/file/性能NE下单盘配置.xml";
		String dataBlockXml = "com/nms/drive/analysis/xmltool/file/性能数据块配置.xml";
		String lineXml = "com/nms/drive/analysis/xmltool/file/性能线路块配置.xml";
		String lineInfoXml = "com/nms/drive/analysis/xmltool/file/性能线路数据配置.xml";
		byte[] allDiskBuffer = new byte[allDiskSize];// 存放性能NE下所有配置的命令的集合
		byte[] diskBuffer = new byte[diskSize];// 存放单盘的命令的集合
		byte[] dataBlockBuffer = new byte[performanceDataBlockSize];// 存放性能数据块命令的集合
		byte[] lineBuffer = new byte[performanceLineSize];// 存放性能线路块命令的集合
		byte[] lineInfoBuffer = new byte[lineInfoSize];// 存放性能线路数据命令的集合

		byte[] dataBloceLineBuffer = new byte[4];// 性能数据块前四个字节
		byte[] dataBlockTimeBuffer = new byte[14];// 性能数据块后七个字节
		byte[] endTimeBuffer = new byte[7];// 结束时间7个字节
		int pointer = 0;// 当前commands下标
		System.arraycopy(commands, 0, allDiskBuffer, 0, allDiskSize);
		pointer += allDiskSize;

		// 生成PerformanceObject对象
		PerformanceObject performanceObject = analysisCommandToPerformanceObj(allDiskBuffer, configXml);
		int diskCount = performanceObject.getMasterCardCount();
		int dataBlockCount = 0;// 性能数据块个数
		int lineCount = 0;// 性能线路块个数
		int lineInfoCount = 0;// 性能线路个数
		PerformanceMasterCardObject performanceMasterCardObject = null;
		PerformanceDataBlockObject blockObject = null;
		PerformanceLineObject lineObject = null;
		PerformanceInfoObject infoObject = null;
		for (int i = 0; i < diskCount; i++) {// 盘个数
			System.arraycopy(commands, pointer, diskBuffer, 0, diskSize);

			performanceMasterCardObject = analysisCommandToCardObj(diskBuffer, diskXml);
			performanceObject.getPerformanceMasterCardObjectList().add(performanceMasterCardObject);
			pointer += diskSize;

			dataBlockCount = performanceMasterCardObject.getDataBlockCount(); 
			int a = 0 ;
			while(a != dataBlockCount){//每个盘的长度
				System.arraycopy(commands, pointer, dataBloceLineBuffer, 0, dataBloceLineBuffer.length);

				lineCount = CoderUtils.bytesToInt(dataBloceLineBuffer);
				pointer += dataBloceLineBuffer.length;
				a += dataBloceLineBuffer.length;
				blockObject = new PerformanceDataBlockObject();
				int b = 0;
				if(lineCount>14){
					while (b != (lineCount-14)) {// 每个块的长度
						System.arraycopy(commands, pointer, lineBuffer, 0, performanceLineSize);

						lineObject = analysisCommandToLineObj(lineBuffer, lineXml);
						blockObject.getPerformanceLineObjectList().add(lineObject);
						pointer += performanceLineSize;
						a+= performanceLineSize;
						b+= performanceLineSize;
						lineInfoCount = lineObject.getLineCount();
						for (int l = 0; l < lineInfoCount/3; l++) {// 生成PerformanceLineInfoObject对象
							System.arraycopy(commands, pointer, lineInfoBuffer, 0, lineInfoSize);
							infoObject = analysisCommandToInfoObj(lineInfoBuffer, lineInfoXml);
							if(infoObject != null){
								lineObject.getPerformanceInfoObjectList().add(infoObject);
							}
							pointer += lineInfoSize;
							a += lineInfoSize;
							b += lineInfoSize;
						}
					}
					System.arraycopy(commands, pointer, dataBlockTimeBuffer, 0, dataBlockTimeBuffer.length);
					dataBlockBuffer = CoderUtils.arraycopy(dataBloceLineBuffer, dataBlockTimeBuffer);
					blockObject = analysisCommandToDataBlockObj(blockObject, dataBlockBuffer, dataBlockXml);
					performanceMasterCardObject.getPerformanceDataBlockObjectList().add(blockObject);
					pointer += dataBlockTimeBuffer.length;
					a += dataBlockTimeBuffer.length;
					b += dataBlockTimeBuffer.length;
				}else{
					break;
				}
			}
		}
//		int c1 = 0;
//		for(PerformanceMasterCardObject p : performanceObject.getPerformanceMasterCardObjectList()){
//			List<PerformanceDataBlockObject> p1List = p.getPerformanceDataBlockObjectList();
//			for (PerformanceDataBlockObject p2 : p1List) {
//				for(PerformanceLineObject p3 : p2.getPerformanceLineObjectList()){
//					for (PerformanceInfoObject p4 : p3.getPerformanceInfoObjectList()) {
//						c1++;
//					}
//				}
//			}
//		}
//		System.out.println("count:"+c1);
		return performanceObject;

	}
	public PerformanceObject analysiscomandToObjectByCard(byte[] commands, String configXml) {
		
		String dataBlockXml = "com/nms/drive/analysis/xmltool/file/性能数据块配置.xml";
		String lineXml = "com/nms/drive/analysis/xmltool/file/性能线路块配置.xml";
		String lineInfoXml = "com/nms/drive/analysis/xmltool/file/性能线路数据配置.xml";
		byte[] diskBuffer = new byte[17];// 存放单盘的命令的集合
		byte[] dataBlockBuffer = new byte[performanceDataBlockSize];// 存放性能数据块命令的集合
		byte[] lineBuffer = new byte[performanceLineSize];// 存放性能线路块命令的集合
		byte[] lineInfoBuffer = new byte[lineInfoSize];// 存放性能线路数据命令的集合

		byte[] dataBloceLineBuffer = new byte[4];// 性能数据块前四个字节
		byte[] dataBlockTimeBuffer = new byte[14];// 性能数据块后七个字节
		byte[] endTimeBuffer = new byte[7];// 结束时间7个字节
		
		int pointer = 0;// 当前commands下标

		int dataBlockCount = 0;// 性能数据块个数
		int lineCount = 0;// 性能线路块个数
		int lineInfoCount = 0;// 性能线路个数
		PerformanceDataBlockObject blockObject = null;
		PerformanceLineObject lineObject = null;
		PerformanceInfoObject infoObject = null;
		PerformanceMasterCardObject performanceMasterCardObject = null;
			System.arraycopy(commands, pointer, diskBuffer, 0, diskBuffer.length);

			PerformanceObject performanceObject = analysisCommandToSingleCard(commands, configXml);
			pointer += 17;
			performanceMasterCardObject = performanceObject.getPerformanceMasterCardObjectList().get(0);
			dataBlockCount = performanceMasterCardObject.getDataBlockCount(); 
			int a = 0 ;
			while(a != dataBlockCount){//每个盘的长度
				System.arraycopy(commands, pointer, dataBloceLineBuffer, 0, dataBloceLineBuffer.length);

				lineCount = CoderUtils.bytesToInt(dataBloceLineBuffer);
				pointer += dataBloceLineBuffer.length;
				a += dataBloceLineBuffer.length;
				blockObject = new PerformanceDataBlockObject();
				int b = 0;
				while (b != (lineCount-14)) {// 每个块的长度
					System.arraycopy(commands, pointer, lineBuffer, 0, performanceLineSize);

					lineObject = analysisCommandToLineObj(lineBuffer, lineXml);
					blockObject.getPerformanceLineObjectList().add(lineObject);
					pointer += performanceLineSize;
					a+= performanceLineSize;
					b+= performanceLineSize;
					lineInfoCount = lineObject.getLineCount();
					for (int l = 0; l < lineInfoCount/3; l++) {// 生成PerformanceLineInfoObject对象
						System.arraycopy(commands, pointer, lineInfoBuffer, 0, lineInfoSize);
//						infoObject = analysisCommandToInfoObj(lineInfoBuffer, lineInfoXml);
						lineObject.getPerformanceInfoObjectList().add(infoObject);
						pointer += lineInfoSize;
						a += lineInfoSize;
						b += lineInfoSize;
					}
					
				}
				System.arraycopy(commands, pointer, dataBlockTimeBuffer, 0, dataBlockTimeBuffer.length);

				dataBlockBuffer = CoderUtils.arraycopy(dataBloceLineBuffer, dataBlockTimeBuffer);
				blockObject = analysisCommandToDataBlockObj(blockObject, dataBlockBuffer, dataBlockXml);
				performanceMasterCardObject.getPerformanceDataBlockObjectList().add(blockObject);
				pointer += dataBlockTimeBuffer.length;
				a += dataBlockTimeBuffer.length;
				b += dataBlockTimeBuffer.length;
				
			}

		return performanceObject;

	}
	
	/**
	 * 解析命令成部分PerformanceObject
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private PerformanceObject analysisCommandToPerformanceObj(byte[] commands, String configXml) {
		PerformanceObject performanceObject = new PerformanceObject();
		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			DriveRoot performanceObjRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);

			DriveAttribute performanceAttr = null;
			for (int i = 0; i < performanceObjRoot.getDriveAttributeList().size(); i++) {
				performanceAttr = performanceObjRoot.getDriveAttributeList().get(i);
				if ("性能数据类型".equals(performanceAttr.getDescription())) {
					performanceObject.setPerformanceDataType(Integer.parseInt(performanceAttr.getValue()));
				}
				if ("NE地址".equals(performanceAttr.getDescription())) {
					performanceObject.setNeAddress(Integer.parseInt(performanceAttr.getValue()));
				}
				if ("NE状态".equals(performanceAttr.getDescription())) {
					performanceObject.setNeStatus(Integer.parseInt(performanceAttr.getValue()));
				}
				if ("备用".equals(performanceAttr.getDescription())) {
					// performanceObject.setReserve(Integer.parseInt(performanceAttr.getValue()));
				}
				if ("盘总数".equals(performanceAttr.getDescription())) {
					performanceObject.setMasterCardCount(Integer.parseInt(performanceAttr.getValue()));
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return performanceObject;

	}

	/**
	 * 解析命令成PerformanceMasterCardObject
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private PerformanceMasterCardObject analysisCommandToCardObj(byte[] commands, String configXml) {
		PerformanceMasterCardObject performanceMasterCardObject = new PerformanceMasterCardObject();
		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			DriveRoot cardRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);

			DriveAttribute cardAttr = null;
			for (int i = 0; i < cardRoot.getDriveAttributeList().size(); i++) {
				cardAttr = cardRoot.getDriveAttributeList().get(i);
				if ("盘类型".equals(cardAttr.getDescription())) {
					performanceMasterCardObject.setMasterCard(Integer.parseInt(cardAttr.getValue()));
				}
				else if ("盘地址".equals(cardAttr.getDescription())) {
					performanceMasterCardObject.setMasterCardAddress(Integer.parseInt(cardAttr.getValue()));
				}
				else if ("盘状态".equals(cardAttr.getDescription())) {
					performanceMasterCardObject.setMasterStatue(Integer.parseInt(cardAttr.getValue()));
				}
				else if ("备用".equals(cardAttr.getDescription())) {
					// performanceMasterCardObject.setReserve(Integer.parseInt(cardAttr.getValue()));
				}
				else if ("性能数据块配置".equals(cardAttr.getDescription())) {
					performanceMasterCardObject.setDataBlockCount(Integer.parseInt(cardAttr.getValue()));
				}

			}

		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
		return performanceMasterCardObject;

	}

	/**
	 * 解析命令成PerformanceMasterCardObject
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private PerformanceObject analysisCommandToSingleCard(byte[] commands, String configXml) {
		PerformanceObject performanceObject = new PerformanceObject();
		PerformanceMasterCardObject performanceMasterCardObject = new PerformanceMasterCardObject();
		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			DriveRoot cardRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);

			DriveAttribute cardAttr = null;
			for (int i = 0; i < cardRoot.getDriveAttributeList().size(); i++) {
				cardAttr = cardRoot.getDriveAttributeList().get(i);
				if ("性能数据类型".equals(cardAttr.getDescription())) {
					performanceObject.setPerformanceDataType(Integer.parseInt(cardAttr.getValue()));
				}
				if ("NE地址".equals(cardAttr.getDescription())) {
					performanceObject.setNeAddress(Integer.parseInt(cardAttr.getValue()));
				}
				if ("NE状态".equals(cardAttr.getDescription())) {
					performanceObject.setNeStatus(Integer.parseInt(cardAttr.getValue()));
				}
				if ("备用".equals(cardAttr.getDescription())) {
					// performanceObject.setReserve(Integer.parseInt(performanceAttr.getValue()));
				}
				if ("盘总数".equals(cardAttr.getDescription())) {
					performanceObject.setMasterCardCount(Integer.parseInt(cardAttr.getValue()));
				}
				if ("盘类型".equals(cardAttr.getDescription())) {
					performanceMasterCardObject.setMasterCard(Integer.parseInt(cardAttr.getValue()));
				}
				else if ("盘地址".equals(cardAttr.getDescription())) {
					performanceMasterCardObject.setMasterCardAddress(Integer.parseInt(cardAttr.getValue()));
				}
				else if ("盘状态".equals(cardAttr.getDescription())) {
					performanceMasterCardObject.setMasterStatue(Integer.parseInt(cardAttr.getValue()));
				}
				else if ("备用".equals(cardAttr.getDescription())) {
					// performanceMasterCardObject.setReserve(Integer.parseInt(cardAttr.getValue()));
				}
				else if ("性能数据块配置".equals(cardAttr.getDescription())) {
					performanceMasterCardObject.setDataBlockCount(Integer.parseInt(cardAttr.getValue()));
				}

			}

		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
		performanceObject.getPerformanceMasterCardObjectList().add(performanceMasterCardObject);
		return performanceObject;

	}
	
	/**
	 * 解析命令成PerformanceDataBlockObject
	 * 
	 * @param blockObject
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private PerformanceDataBlockObject analysisCommandToDataBlockObj(PerformanceDataBlockObject blockObject, byte[] commands, String configXml) {
		// PerformanceDataBlockObject blockObject = new PerformanceDataBlockObject();
		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			DriveRoot dataBlockRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);

			DriveAttribute dataBlockAttr = null;

			StringBuffer dataTimeStringBuffer = new StringBuffer();
			StringBuffer endTimeStringBuffer = new StringBuffer();
			for (int i = 0; i < dataBlockRoot.getDriveAttributeList().size(); i++) {
				dataBlockAttr = dataBlockRoot.getDriveAttributeList().get(i);
				if ("性能线路块配置".equals(dataBlockAttr.getDescription())) {
					blockObject.setPerformanceLineCount(Integer.parseInt(dataBlockAttr.getValue()));

				}
				if ("起年".equals(dataBlockAttr.getDescription())) {
					dataTimeStringBuffer.append(dataBlockAttr.getValue()).append("-");
				}
				if ("起月".equals(dataBlockAttr.getDescription())) {
					dataTimeStringBuffer.append(dataBlockAttr.getValue()).append("-");
				}
				if ("起日".equals(dataBlockAttr.getDescription())) {
					dataTimeStringBuffer.append(dataBlockAttr.getValue()).append(" ");
				}
				if ("起时".equals(dataBlockAttr.getDescription())) {
					dataTimeStringBuffer.append(dataBlockAttr.getValue()).append(":");
				}
				if ("起分".equals(dataBlockAttr.getDescription())) {
					dataTimeStringBuffer.append(dataBlockAttr.getValue()).append(":");
				}
				if ("起秒".equals(dataBlockAttr.getDescription())) {
					dataTimeStringBuffer.append(dataBlockAttr.getValue());
					blockObject.setDateTime(dataTimeStringBuffer.toString());// 起始时间赋值
				}if ("结年".equals(dataBlockAttr.getDescription())) {
					endTimeStringBuffer.append(dataBlockAttr.getValue()).append("-");
				}
				if ("结月".equals(dataBlockAttr.getDescription())) {
					endTimeStringBuffer.append(dataBlockAttr.getValue()).append("-");
				}
				if ("结日".equals(dataBlockAttr.getDescription())) {
					endTimeStringBuffer.append(dataBlockAttr.getValue()).append(" ");
				}
				if ("结时".equals(dataBlockAttr.getDescription())) {
					endTimeStringBuffer.append(dataBlockAttr.getValue()).append(":");
				}
				if ("结分".equals(dataBlockAttr.getDescription())) {
					endTimeStringBuffer.append(dataBlockAttr.getValue()).append(":");
				}
				if ("结秒".equals(dataBlockAttr.getDescription())) {
					endTimeStringBuffer.append(dataBlockAttr.getValue());
					blockObject.setEndTime(endTimeStringBuffer.toString());// 结束时间赋值
				}

			}

		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}

		return blockObject;

	}

	/**
	 * 解析命令成PerformanceLineObject
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private PerformanceLineObject analysisCommandToLineObj(byte[] commands, String configXml) {
		PerformanceLineObject lineObject = new PerformanceLineObject();
		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			DriveRoot lineRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);

			DriveAttribute lineAttr = null;
			for (int i = 0; i < lineRoot.getDriveAttributeList().size(); i++) {
				lineAttr = lineRoot.getDriveAttributeList().get(i);
				if ("线路号".equals(lineAttr.getDescription())) {
					lineObject.setLine(Integer.parseInt(lineAttr.getValue()));
				}
				if ("线路数据配置".equals(lineAttr.getDescription())) {
					lineObject.setLineCount(Integer.parseInt(lineAttr.getValue()));

				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		return lineObject;

	}

	/**
	 * 解析命令成PerformanceInfoObject
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private PerformanceInfoObject analysisCommandToInfoObj(byte[] commands, String configXml) {
		int code = CoderUtils.bytesToInt(commands[0]);
		//code等于239，即EF，是扩展性能数据中不需要的部分，不用解析
		if(code != 239){
			PerformanceInfoObject infoObject = new PerformanceInfoObject();
			infoObject.setPerformanceCode(code);
			byte[] value = new byte[4];
			value[0] = 0;
			value[1] = 0;
			value[2] = commands[1];
			value[3] = commands[2];
			infoObject.setPerformanceValue(CoderUtils.bytesToInt(value));
//		PerformanceInfoObject infoObject = new PerformanceInfoObject();
//		try {
//			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
//			analysisCommandByDriveXml.setCommands(commands);
//			DriveRoot infoRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
//
//			DriveAttribute infoAttr = null;
//			
//			for (int i = 0; i < infoRoot.getDriveAttributeList().size(); i++) {
//				infoAttr = infoRoot.getDriveAttributeList().get(i);
//				if ("性能代码".equals(infoAttr.getDescription())) {
//					infoObject.setPerformanceCode(Integer.parseInt(infoAttr.getValue()));
//				}
//				if ("性能(高/低)".equals(infoAttr.getDescription())) {
//					infoObject.setPerformanceValue(Integer.parseInt(infoAttr.getValue()));
//				}
//
//			}
//
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		}
			return infoObject;
		}else{
			return null;
		}
	}
}
