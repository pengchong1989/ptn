package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.AlarmInfoObject;
import com.nms.drive.service.bean.AlarmLineObject;
import com.nms.drive.service.bean.AlarmMasterCardObject;
import com.nms.drive.service.bean.AlarmObject;
import com.nms.drive.service.impl.CoderUtils;

/**
 * 
 * 告警解析
 * @author 彭冲
 *
 */
public class AnalysisAlarmTable extends AnalysisBase {
	
	private int nehead = 6;//NE部分字节长度
	private int masterCard = 14;//单盘部分字节长度
	private int lineObject = 6;//线路部分字节长度
	private int alarmInfo = 9;//告警信息字节长度
	private int head = 131;//头信息字节长度
	/**
	 * 查询所有盘告警
	 * @param commands
	 * @return
	 * @throws Exception
	 */
	public AlarmObject analysisCommandToAllAlarm(byte[] commands) throws Exception {
		AlarmObject alarmObject = new AlarmObject();
		CoderUtils.print16String(commands);
		alarmObject = analysisCommandToObject(commands);
		return alarmObject;
		
	}
	/**
	 * 查询某盘告警
	 * @param commands
	 * @return
	 * @throws Exception
	 */
	public AlarmObject analysisCommandToplateAlarm(byte[] commands) throws Exception{
		//将条目数0x00,0x01插入命令中
		byte[] a = new byte[2+commands.length];
		System.arraycopy(commands, 0, a, 0, head+3);
		a[head+4] = 0;
		a[head+5] = 1;
		System.arraycopy(commands, head+4, a,head+6 ,a.length-137);
		AlarmObject alarmObject = new AlarmObject();
		alarmObject = analysisCommandToObject(a);
		return alarmObject;
		
	}
	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return AlarmObject
	 * @throws Exception 
	 */
	public AlarmObject analysisCommandToObject(byte[] commands) throws Exception{
		String configXml1 = "src/com/nms/drive/analysis/xmltool/file/告警NE下所有盘配置.xml";
		String configXml2 = "src\\com\\ptn\\drive\\analysis\\xmltool\\file\\告警NE下单盘配置.xml";
		String configXml3 = "src\\com\\ptn\\drive\\analysis\\xmltool\\file\\告警NE下单盘配置_SUB.xml";
		String configXml4 = "src\\com\\ptn\\drive\\analysis\\xmltool\\file\\告警NE下单盘配置_SUB_SUB.xml";
		byte[] neBuffer = new byte[nehead];// NE部分字节流数组
		System.arraycopy(commands, 0, neBuffer, 0, neBuffer.length);// 截取命令中想要的部份
		AlarmObject alarmObject = getAlarmObject(configXml1, neBuffer);// 生成告警对象
		int alarmMasterCardCount = alarmObject.getAlarmMasterCardCount();// 盘长度

		int bufferIndex = nehead;// 起始点
		byte[] masterCardBuffer = new byte[masterCard];// 单盘部分字节流数组
		byte[] lineObjectBuffer = new byte[lineObject];// 线路部分字节流数组
		byte[] alarmInfoBuffer = new byte[alarmInfo];// 告警信息字节流数组
		AlarmMasterCardObject alarmMasterCardObject = null;// 单盘对象
		AlarmLineObject alarmLineObject = null;// 线路对象
		AlarmInfoObject alarmInfoObject = null;// 线路信息对象
		int alarmLineCount = 0;
		int lineInfoCount = 0;
		for (int i = 0; i < alarmMasterCardCount; i++) {// 有几个盘循环几次
			System.arraycopy(commands, bufferIndex, masterCardBuffer, 0, masterCardBuffer.length);// 从bufferIndex点取出14个字节是盘字节流
			alarmMasterCardObject = getAlarmMasterCardObject(configXml2, masterCardBuffer);// 生成对象
			alarmObject.getAlarmMasterCardObjectList().add(alarmMasterCardObject);// 添加到盘列表中
			bufferIndex = bufferIndex + masterCard;// 更新起始点

			alarmLineCount = alarmMasterCardObject.getAlarmLineCount();// 线路长度
			int a = 0;
			while(alarmLineCount!=a){
				// 有几个线路循环几次
				System.arraycopy(commands, bufferIndex, lineObjectBuffer, 0, lineObjectBuffer.length);// 从bufferIndex点取出6个字节是线路字节流
				alarmLineObject = getAlarmLineObject(configXml3, lineObjectBuffer);
				alarmMasterCardObject.getAlarmLineObjectList().add(alarmLineObject);// 添加到线路列表中
				bufferIndex = bufferIndex + lineObject;// 更新起始点
				a = lineObject+a;
				lineInfoCount = alarmLineObject.getLineInfoCount();
				for (int k = 0; k < lineInfoCount/9; k++) {
					System.arraycopy(commands, bufferIndex, alarmInfoBuffer, 0, alarmInfoBuffer.length);// 从bufferIndex点取出6个字节是线路数据字节流
					alarmInfoObject = getAlarmInfoObject(configXml4, alarmInfoBuffer);
					alarmLineObject.getAlarmInfoObjectList().add(alarmInfoObject);// 添加线路信息列表中
					bufferIndex = bufferIndex + alarmInfo;// 更新起始点
					a = a+alarmInfo;
				}
			
			}
		}
		return alarmObject;
		
	}
	
	/**
	 * 获得告警对象
	 * 
	 * @param fileName
	 * @param neBuffer
	 * @return
	 * @throws Exception
	 */
	private AlarmObject getAlarmObject(String fileName, byte[] neBuffer) throws Exception {
		AlarmObject alarmObject = new AlarmObject();
		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(neBuffer);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(fileName);

			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				DriveAttributeTOAlarmObject(driveAttribute, alarmObject);//赋值
			}
		} catch (Exception e) {
			throw e;
		}
		return alarmObject;
	}

	/**
	 * AlarmObject对象赋值
	 * @param driveAttribute
	 * @param alarmObject
	 */
	public void DriveAttributeTOAlarmObject(DriveAttribute driveAttribute,AlarmObject alarmObject){
		
		//NE地址
		if("NE地址".equals(driveAttribute.getDescription())){
			alarmObject.setNeAddress(Integer.parseInt(driveAttribute.getValue()));
		}
		
		//NE特征字
		if("NE特征字".equals(driveAttribute.getDescription())){
			alarmObject.setNeFeature(Integer.parseInt(driveAttribute.getValue()));
		}
		
		//告警盘的总数
		if("告警盘的总数".equals(driveAttribute.getDescription())){
			alarmObject.setAlarmMasterCardCount(Integer.parseInt(driveAttribute.getValue()));
		}
		
	}
	
	/**
	 * 获得盘对象
	 * 
	 * @param fileName
	 * @param masterCardBuffer
	 * @return
	 * @throws Exception
	 */
	private AlarmMasterCardObject getAlarmMasterCardObject(String fileName, byte[] masterCardBuffer) throws Exception {
		AlarmMasterCardObject alarmMasterCardObject = new AlarmMasterCardObject();
		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(masterCardBuffer);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(fileName);

			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				DriveAttributeTOAlarmMasterCardObject(driveAttribute, alarmMasterCardObject);//赋值
			}
		} catch (Exception e) {
			throw e;
		}
		return alarmMasterCardObject;

	}
	
	/**
	 * 盘对象赋值
	 * @param driveAttribute
	 * @param alarmMasterCardObject
	 */
	public void DriveAttributeTOAlarmMasterCardObject(DriveAttribute driveAttribute,AlarmMasterCardObject alarmMasterCardObject){
		
		//盘类型
		if("盘类型".equals(driveAttribute.getDescription())){
			alarmMasterCardObject.setMasterCard(Integer.parseInt(driveAttribute.getValue()));
		}
		//盘地址
		if("盘地址".equals(driveAttribute.getDescription())){
			alarmMasterCardObject.setMasterCardAddress(Integer.parseInt(driveAttribute.getValue()));
		}
		//盘特征字
		if("盘特征字".equals(driveAttribute.getDescription())){
			alarmMasterCardObject.setMasterCardFeature(Integer.parseInt(driveAttribute.getValue()));
		}
		//备用
		if("备用".equals(driveAttribute.getDescription())){
			alarmMasterCardObject.setReserve(Integer.parseInt(driveAttribute.getValue()));
		}
		//盘数据长度
		if("盘数据长度".equals(driveAttribute.getDescription())){
			alarmMasterCardObject.setAlarmLineCount(Integer.parseInt(driveAttribute.getValue()));
		}
	}
	/**
	 * 获得线路对象
	 * 
	 * @param fileName
	 * @param lineObjectBuffer
	 * @return
	 * @throws Exception
	 */
	private AlarmLineObject getAlarmLineObject(String fileName, byte[] lineObjectBuffer) throws Exception {
		AlarmLineObject alarmLineObject = new AlarmLineObject();
		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(lineObjectBuffer);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(fileName);

			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				DriveAttributeTOAlarmLineObject(driveAttribute, alarmLineObject);//赋值
			}
		} catch (Exception e) {
			throw e;
		}
		return alarmLineObject;

	}
	
	/**
	 * 线路对象赋值
	 * @param driveAttribute
	 * @param alarmLineObject
	 */
	public void DriveAttributeTOAlarmLineObject(DriveAttribute driveAttribute,AlarmLineObject alarmLineObject){
		
		//线路
		if("线路".equals(driveAttribute.getDescription())){
			alarmLineObject.setLine(Integer.parseInt(driveAttribute.getValue()));
		}
		//线路数据长度
		if("线路数据长度".equals(driveAttribute.getDescription())){
			alarmLineObject.setLineInfoCount(Integer.parseInt(driveAttribute.getValue()));
		}
	}
	/**
	 * 告警信息
	 * 
	 * @param fileName
	 * @param alarmInfoBuffer
	 * @return
	 * @throws Exception
	 */
	private AlarmInfoObject getAlarmInfoObject(String fileName, byte[] alarmInfoBuffer) throws Exception {
		AlarmInfoObject alarmInfoObject = new AlarmInfoObject();
		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(alarmInfoBuffer);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(fileName);

			DriveAttribute driveAttribute = null;
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);			
				DriveAttributeTOAlarmInfoObject(driveAttribute, alarmInfoObject, sb);//赋值
			}
		} catch (Exception e) {
			throw e;
		}
		return alarmInfoObject;

	}

	/**
	 * 告警赋值
	 * @param driveAttribute
	 * @param alarmInfoObject
	 */
	public void DriveAttributeTOAlarmInfoObject(DriveAttribute driveAttribute,AlarmInfoObject alarmInfoObject,StringBuffer sb){
		
		//告警代码
		if("告警代码".equals(driveAttribute.getDescription())){
			alarmInfoObject.setAlarmCode(Integer.parseInt(driveAttribute.getValue()));
		}
		//告警状态
		if("告警状态".equals(driveAttribute.getDescription())){
			alarmInfoObject.setAlarmStatus(Integer.parseInt(driveAttribute.getValue()));
		}
		//年
		if("年".equals(driveAttribute.getDescription())){
			sb.append(driveAttribute.getValue()+"-");
		}
		//月
		if("月".equals(driveAttribute.getDescription())){
			sb.append(driveAttribute.getValue()+"-");
		}
		//日
		if("日".equals(driveAttribute.getDescription())){
			sb.append(driveAttribute.getValue()+" ");
		}
		//时
		if("时".equals(driveAttribute.getDescription())){
			sb.append(driveAttribute.getValue()+":");
		}
		//分
		if("分".equals(driveAttribute.getDescription())){
			sb.append(driveAttribute.getValue()+":");
		}
		//秒
		if("秒".equals(driveAttribute.getDescription())){
			sb.append(driveAttribute.getValue());
			alarmInfoObject.setAlarmDate(sb.toString());
		}
	}
}
