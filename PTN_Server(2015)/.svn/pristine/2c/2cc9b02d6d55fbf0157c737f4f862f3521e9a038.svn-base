package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.GlobalObject;
import com.nms.drive.service.impl.CoderUtils;

/**
 * 解析 全局配置块(03H)
 * 
 * @author 张坤
 * 
 */
public class AnalysisGlobalTable extends AnalysisBase {
	private final int dataCount = 61;// 每个数据块的长度

	/**
	 * 解析XML转换成命令
	 * 
	 * @param globalObject
	 *            对象
	 * @param configXml
	 *            文件目录地址
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisGlobalObjectToCommands(GlobalObject globalObject,String configXml) throws Exception {
		// 获取根目录
		DriveRoot globalDrivRoot = super.LoadDriveXml(configXml);
		
		for (int i = 0, j = globalDrivRoot.getDriveAttributeList().size(); i < j; i++) {
			DriveAttribute driveAttribute = globalDrivRoot.getDriveAttributeList().get(i);
			// 属性赋值
			lspObjectTODriveAttribute(driveAttribute, globalObject);
		}
		// 将集合转换成命令
		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(globalDrivRoot);
		CoderUtils.print16String(dataCommand);
		return dataCommand;
	}

	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public GlobalObject analysisCommandsToGlobalObject(byte[] commands,
			String configXml) throws Exception {
		GlobalObject globalObject = new GlobalObject();
		// 起始长度
		byte[] a = new byte[dataCount];
		System.arraycopy(commands, 0, a, 0, a.length);
		CoderUtils.print16String(commands);
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(a);
		DriveRoot driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);

		for (int i = 0, y = driveRoot_config.getDriveAttributeList().size(); i < y; i++) {
			DriveAttribute driveAttribute = driveRoot_config
					.getDriveAttributeList().get(i);
			// 赋值
			DriveAttributeTOGlobalObject(globalObject, driveAttribute);
		}

		return globalObject;
	}

	private void DriveAttributeTOGlobalObject(GlobalObject globalObject,
			DriveAttribute driveAttribute) {
		// 单盘MAC地址1 赋值
		if ("单卡MAC地址1".equals(driveAttribute.getDescription())) {
			globalObject.setSingleMACAddress(CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		// 单盘MAC地址2 赋值
		if ("单卡MAC地址2".equals(driveAttribute.getDescription())) {
			globalObject.setSingleMACAddress(globalObject.getSingleMACAddress()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		// 单盘MAC地址3 赋值
		if ("单卡MAC地址3".equals(driveAttribute.getDescription())) {
			globalObject.setSingleMACAddress(globalObject.getSingleMACAddress()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		// 单盘MAC地址4 赋值
		if ("单卡MAC地址4".equals(driveAttribute.getDescription())) {
			globalObject.setSingleMACAddress(globalObject.getSingleMACAddress()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		// 单盘MAC地址5 赋值
		if ("单卡MAC地址5".equals(driveAttribute.getDescription())) {
			globalObject.setSingleMACAddress(globalObject.getSingleMACAddress()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		// 单盘MAC地址6 赋值
		if ("单卡MAC地址6".equals(driveAttribute.getDescription())) {
			globalObject.setSingleMACAddress(globalObject.getSingleMACAddress()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		// 地址学习模式 赋值
		if ("地址学习模式".equals(driveAttribute.getDescription())) {
			globalObject.setAddressStudyModel(Integer.parseInt(driveAttribute
					.getValue()));
		}
		// 地址老化开关
		if ("地址老化开关".equals(driveAttribute.getDescription())) {
			globalObject.setAddressAgeSwitch(Integer.parseInt(driveAttribute
					.getValue()));
		}
		// MAC地址老化时间
		if ("MAC地址老化时间".equals(driveAttribute.getDescription())) {
			globalObject.setMACAddressAgeDate(Integer.parseInt(driveAttribute
					.getValue()));
		}
		// 丢包率统计时间间隔
		if ("丢包率统计时间间隔".equals(driveAttribute.getDescription())) {
			globalObject.setThrowWrapDateGap(Integer.parseInt(driveAttribute
					.getValue()));
		}
		// FDI帧
		if ("FDI帧".equals(driveAttribute.getDescription())) {
			changToValue(CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue())), globalObject,"tms");

		}
		// APS等待恢复时间
		if ("APS等待恢复时间".equals(driveAttribute.getDescription())) {
			globalObject.setApsRecoverTime(Integer.parseInt(driveAttribute.getValue()));
		}
		
		// APS模式
		if ("APS模式".equals(driveAttribute.getDescription())) {
			globalObject.setApsModel(Integer.parseInt(driveAttribute.getValue()));
		}
		
		 if ("LACP协议开关".equals(driveAttribute.getDescription())) {
			 globalObject.setLacp(Integer.parseInt(driveAttribute.getValue()));
		 }
		
		 if ("设备优先级".equals(driveAttribute.getDescription())) {
			 globalObject.setEquipmentPriority(Integer.parseInt(driveAttribute.getValue()));
		 }
	    
		 if ("DHCP模式".equals(driveAttribute.getDescription())) {
			 globalObject.setDhcpModel(Integer.parseInt(driveAttribute.getValue()));
		 }
		 
		 if ("环路检测发包开关".equals(driveAttribute.getDescription())) {
				globalObject.setLoopCheck(Integer.parseInt(driveAttribute.getValue()));
		 }
		 
		 if ("SSM帧模式".equals(driveAttribute.getDescription())) {
			globalObject.setSsmModel(Integer.parseInt(driveAttribute.getValue()));
		 }
		 
		// 镜像模式
		if ("镜像模式".equals(driveAttribute.getDescription())) {
			globalObject.setMirrorModel(Integer.parseInt(driveAttribute.getValue()));
		}
		// 被镜像端口
		if ("被镜像端口".equals(driveAttribute.getDescription())) {
			globalObject.setMirrorByPort(Integer.parseInt(driveAttribute.getValue()));
		}
		// 镜像端口
		if ("镜像端口".equals(driveAttribute.getDescription())) {
			globalObject.setMirrorPort(Integer.parseInt(driveAttribute.getValue()));
		}
		
		// MPLS-TP控制字使能
		if ("MPLS-TP控制字使能".equals(driveAttribute.getDescription())) {
			globalObject.setMPLSTPControl(Integer.parseInt(driveAttribute.getValue()));
		}
		 
		// Channel Type
		if ("Channel Type".equals(driveAttribute.getDescription())) {
			globalObject.setChannelType(Integer.parseInt(driveAttribute.getValue()));
		}
		
		// FDI帧
		if ("TMC FDI帧".equals(driveAttribute.getDescription())) {
			changToValue(CoderUtils.convertBinary(Integer
					.parseInt(driveAttribute.getValue())), globalObject,"tmc");
		}

		// 环路检测功能
		if ("环路检测功能".equals(driveAttribute.getDescription())) {
			globalObject.setRoundEnable(Integer.parseInt(driveAttribute.getValue()));
		}
		
		// 二层功能模式开关
		if ("二层功能模式开关".equals(driveAttribute.getDescription())) {
			globalObject.setTwoLayer(Integer.parseInt(driveAttribute.getValue()));
		}
		
		// 基于vlan的MAC学习限制
		if ("基于vlan的MAC学习限制".equals(driveAttribute.getDescription())) {
			globalObject.setVlanMAC(Integer.parseInt(driveAttribute.getValue()));
		}
		// MAC学习限制基于vlan值
		if ("MAC学习限制基于vlan值".equals(driveAttribute.getDescription())) {
			globalObject.setVlanValue(Integer.parseInt(driveAttribute.getValue()));
		}
		// MAC地址学习限制数目
		if ("MAC地址学习限制数目".equals(driveAttribute.getDescription())) {
			globalObject.setMacNumber(Integer.parseInt(driveAttribute.getValue()));
		}
		//掉电告警上联模式
		if ("掉电告警上联模式".equals(driveAttribute.getDescription())) {
			globalObject.setAlarmModel(Integer.parseInt(driveAttribute.getValue()));
		}
		//掉电告警上联端口
		if ("掉电告警上联端口".equals(driveAttribute.getDescription())) {
			globalObject.setAlarmPort(Integer.parseInt(driveAttribute.getValue()));
		}
		//环路避免功能开关
		if ("环路避免功能开关".equals(driveAttribute.getDescription())) {
			globalObject.setLoopAvoid(Integer.parseInt(driveAttribute.getValue()));
		}
	}

	private void lspObjectTODriveAttribute(DriveAttribute driveAttribute,GlobalObject globalObject) {
		// 单盘MAC地址1 赋值
		if ("单卡MAC地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getSingleMACAddress().split(
					"-")[0]);
		}
		// 单盘MAC地址2 赋值
		if ("单卡MAC地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getSingleMACAddress().split("-")[1]);
		}
		// 单盘MAC地址3 赋值
		if ("单卡MAC地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getSingleMACAddress().split(
					"-")[2]);
		}
		// 单盘MAC地址4 赋值
		if ("单卡MAC地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getSingleMACAddress().split(
					"-")[3]);
		}
		// 单盘MAC地址5 赋值
		if ("单卡MAC地址5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getSingleMACAddress().split(
					"-")[4]);
		}
		// 单盘MAC地址6 赋值
		if ("单卡MAC地址6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getSingleMACAddress().split(
					"-")[5]);
		}
		// 地址学习模式 赋值
		if ("地址学习模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getAddressStudyModel() + "");
		}
		// 地址老化开关
		if ("地址老化开关".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getAddressAgeSwitch() + "");
		}
		// MAC地址老化时间
		if ("MAC地址老化时间".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getMACAddressAgeDate() + "");
		}
		// 丢包率统计时间间隔
		if ("丢包率统计时间间隔".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getThrowWrapDateGap() + "");
		}
		// tms FDI帧
		if ("FDI帧".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(oamframe(globalObject,"tms") + "");
		}
		// APS等待恢复时间
		if ("APS等待恢复时间".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getApsRecoverTime()+"");
		}
		// tmc FDI帧
		if ("TMC FDI帧".equals(driveAttribute.getDescription())) {
			int tmc =  oamframe(globalObject,"tmc");
			driveAttribute.setValue(String.valueOf(tmc));
		}
		// APS模式
		if ("APS模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getApsModel() + "");
		}
		// LACP协议开关
		if ("LACP协议开关".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getLacp() + "");
		}
		
		if ("设备优先级".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getEquipmentPriority() + "");
		}
		if ("DHCP模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getDhcpModel() + "");
		}
		if ("环路检测发包开关".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getLoopCheck() + "");
		}
		if ("二层功能模式开关".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getTwoLayer() + "");
		}
		if ("SSM帧模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getSsmModel() + "");
		}
		// 镜像模式
		if ("镜像模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getMirrorModel() + "");
		}
		// 被镜像端口
		if ("被镜像端口".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getMirrorByPort() + "");
		}
		// 镜像端口
		if ("镜像端口".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getMirrorPort() + "");
		}
		// MPLS-TP控制字使能
		if ("MPLS-TP控制字使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getMPLSTPControl() + "");
		}
		// Channel Type
		if ("Channel Type".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getChannelType() + "");

		}
		// 环路检测功能
		if ("环路检测功能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getRoundEnable() + "");
		}
		// 基于vlan的MAC学习限制
		if ("基于vlan的MAC学习限制".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getVlanMAC() + "");
		}
		// MAC学习限制基于vlan值
		if ("MAC学习限制基于vlan值".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getVlanValue() + "");
		}
		// MAC地址学习限制数目
		if ("MAC地址学习限制数目".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getMacNumber() + "");
		}
		//掉电告警上联模式
		if ("掉电告警上联模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getAlarmModel()+"");
		}
		//掉电告警上联端口
		if ("掉电告警上联端口".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getAlarmPort()+"");
		}
		//环路避免功能开关
		if ("环路避免功能开关".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(globalObject.getLoopAvoid()+"");
		}
	}

	/**
	 * oamframe帧转换为xml格式
	 * 
	 * @param info
	 * @return
	 */
//	 convertAlgorism
	 
	private int oamframe(GlobalObject globalObject,String str) {
		StringBuffer ccm_sb = new StringBuffer() ;
		if("tms".equals(str)){
			ccm_sb.append(globalObject.getFDIB4IT7());
			ccm_sb.append(globalObject.getFDIB1IT3());
			ccm_sb.append(globalObject.getFDIBIT0());
		}else{
			ccm_sb.append(globalObject.getTmcFDIB4IT7());
			ccm_sb.append(globalObject.getTmcFDIB1IT3());
			ccm_sb.append(globalObject.getTmcFDIBIT0());
		}
		char[] result = ccm_sb.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a;
	}

	/**
	 * 不足8位补齐
	 * 
	 * @param stringValue
	 * @return
	 */
	public void changToValue(String stringValue, GlobalObject globalObject,String str) {
		StringBuffer br = new StringBuffer();
		int l = stringValue.length();
		if (l < 8) {
			for (; l < 8; l++) {
				br.append("0");
			}
		}
		String stringValue2 = br.append(stringValue).toString();
		if("tms".equals(str)){
			globalObject.setFDIBIT0(Integer.parseInt(stringValue2.substring(7), 2)+"");
			globalObject.setFDIB1IT3(Integer.parseInt(stringValue2.substring(4, 7), 2)+"");
			globalObject.setFDIB4IT7(Integer.parseInt(stringValue2.substring(0,4), 2)+"");
		}else{
			globalObject.setTmcFDIBIT0(Integer.parseInt(stringValue2.substring(7), 2)+"");
			globalObject.setTmcFDIB1IT3(Integer.parseInt(stringValue2.substring(4, 7), 2)+"");
			globalObject.setTmcFDIB4IT7(Integer.parseInt(stringValue2.substring(0,4), 2)+"");
		}
		
		br = null;
		stringValue2 = null;
	}

	/**
	 * 转换16进制MAC
	 * @param str
	 * @return
	 */
	private String transformMac(String str){
		String s = null;
		Integer integer = Integer.parseInt(str);
		s = (Integer.toHexString(integer)).toUpperCase();
		if(s.length() == 1){
			s= "0"+s;
		}
		return s;
	}
	
}
