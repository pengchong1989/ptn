package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.AclInfoObject;
import com.nms.drive.service.bean.AclObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisAclTable extends AnalysisBase{
	
	private int dataCount = 50;// 每个数据块的长度
	private int clauses = 2;// 条目数长度
	private int checkCode = 11;// 校验码
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
	public byte[] analysisAclToCommands(AclObject aclObject, String configXml)throws Exception {
		// 获取根目录
		DriveRoot globalDrivRoot = super.LoadDriveXml(configXml);
		String file = globalDrivRoot.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
		globalDrivRoot.getDriveAttributeList().get(0).setValue(aclObject.getAclInfoObjectList().size() + "");
		globalDrivRoot.getDriveAttributeList().get(0).getListRootList().clear();// 清除本身自带的一个ListRoot

		for (int k = 0; k < aclObject.getAclInfoObjectList().size(); k++) {
			DriveRoot globalDrivRootOther = super.LoadDriveXml(file);
			ListRoot listroot = new ListRoot();
			listroot.setDriveAttributeList(globalDrivRootOther.getDriveAttributeList());
			
			for (int i = 0; i < globalDrivRootOther.getDriveAttributeList().size(); i++) {
				DriveAttribute driveAttribute = listroot.getDriveAttributeList().get(i);
				// 属性赋值
				aclObjectTODriveAttribute(driveAttribute, aclObject.getAclInfoObjectList().get(k), k + 1);
			}
			globalDrivRoot.getDriveAttributeList().get(0).getListRootList().add(listroot);
		}
		
		// 将集合转换成命令
		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(globalDrivRoot);
		CoderUtils.print16String(dataCommand);
		return dataCommand;
	}
	
	public AclObject analysisCommandsToAclObject(byte[] commands,String configXml) throws Exception {
		CoderUtils.print16String(commands);
		AclObject aclObject = new AclObject();
		List<AclInfoObject> aclInfoList = new ArrayList<AclInfoObject>();
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
		// 起始长度
		int start = clauses;
		// 条目数
		int count = (commands.length - clauses - checkCode)/ dataCount;
		try {
			for (int i = 0; i < count; i++) {
				byte[] a = new byte[dataCount];
				System.arraycopy(commands, start + i * dataCount, a, 0,a.length);
				AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
				analysisCommandByDriveXml.setCommands(a);
				DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(file);
				AclInfoObject aclInfoObject = new AclInfoObject();
				// driveRoot赋值 LSPObject 对象中
				StringBuffer srcIp = new StringBuffer();
				StringBuffer dstIp = new StringBuffer();
				for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
					DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(j);
					// 属性赋值
					driveAttributeToAclInfoObject(aclInfoObject, driveAttribute, srcIp, dstIp);
				}
				aclInfoObject.setSourceIp(srcIp.toString());
				aclInfoObject.setPurposeIp(dstIp.toString());
				aclInfoList.add(aclInfoObject);
			}
			
			aclObject.setAclInfoObjectList(aclInfoList);
		} catch (Exception e) {
//			throw e;
			e.printStackTrace();
		}
		return aclObject;
	}
	
	private void driveAttributeToAclInfoObject(AclInfoObject aclInfoObject, DriveAttribute driveAttribute,StringBuffer srcIp,StringBuffer dstIp)
	{
		if ("动作".equals(driveAttribute.getDescription())) {
			aclInfoObject.setAct(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("入出方向".equals(driveAttribute.getDescription())) {
			aclInfoObject.setDirection(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("端口号".equals(driveAttribute.getDescription())) {
			aclInfoObject.setPortNumber(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("vlanID".equals(driveAttribute.getDescription())) {
			aclInfoObject.setVlanId(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("是否匹配源MAC地址".equals(driveAttribute.getDescription())) {
			aclInfoObject.setIsSourceMac(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("源MAC地址1".equals(driveAttribute.getDescription())) {
			aclInfoObject.setSourceMac(CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		if ("源MAC地址2".equals(driveAttribute.getDescription())) {
			aclInfoObject.setSourceMac(aclInfoObject.getSourceMac()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		if ("源MAC地址3".equals(driveAttribute.getDescription())) {
			aclInfoObject.setSourceMac(aclInfoObject.getSourceMac()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		if ("源MAC地址4".equals(driveAttribute.getDescription())) {
			aclInfoObject.setSourceMac(aclInfoObject.getSourceMac()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		if ("源MAC地址5".equals(driveAttribute.getDescription())) {
			aclInfoObject.setSourceMac(aclInfoObject.getSourceMac()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		if ("源MAC地址6".equals(driveAttribute.getDescription())) {
			aclInfoObject.setSourceMac(aclInfoObject.getSourceMac()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		if ("是否匹配目的MAC地址".equals(driveAttribute.getDescription())) {
			aclInfoObject.setIsPurposeMac(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("目的MAC地址1".equals(driveAttribute.getDescription())) {
			aclInfoObject.setPurposeMac(CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		if ("目的MAC地址2".equals(driveAttribute.getDescription())) {
			aclInfoObject.setPurposeMac(aclInfoObject.getPurposeMac()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		if ("目的MAC地址3".equals(driveAttribute.getDescription())) {
			aclInfoObject.setPurposeMac(aclInfoObject.getPurposeMac()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		if ("目的MAC地址4".equals(driveAttribute.getDescription())) {
			aclInfoObject.setPurposeMac(aclInfoObject.getPurposeMac()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		if ("目的MAC地址5".equals(driveAttribute.getDescription())) {
			aclInfoObject.setPurposeMac(aclInfoObject.getPurposeMac()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		if ("目的MAC地址6".equals(driveAttribute.getDescription())) {
			aclInfoObject.setPurposeMac(aclInfoObject.getPurposeMac()
					+ "-" + CoderUtils.synchTransformMac(driveAttribute.getValue()));
		}
		if ("是否匹配源IP地址".equals(driveAttribute.getDescription())) {
			aclInfoObject.setIsSourceIp(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("源ip地址1".equals(driveAttribute.getDescription())) {
			srcIp.append(driveAttribute.getValue() + ".");
		}
		if ("源ip地址2".equals(driveAttribute.getDescription())) {
			srcIp.append(driveAttribute.getValue() + ".");
		}
		if ("源ip地址3".equals(driveAttribute.getDescription())) {
			srcIp.append(driveAttribute.getValue() + ".");
		}
		if ("源ip地址4".equals(driveAttribute.getDescription())) {
			srcIp.append(driveAttribute.getValue() + "");
		}
		
		if ("是否匹配目的IP地址".equals(driveAttribute.getDescription())) {
			aclInfoObject.setIsPurposeIp(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("目的IP地址1".equals(driveAttribute.getDescription())) {
			dstIp.append(driveAttribute.getValue() + ".");
		}
		if ("目的IP地址2".equals(driveAttribute.getDescription())) {
			dstIp.append(driveAttribute.getValue() + ".");
		}
		if ("目的IP地址3".equals(driveAttribute.getDescription())) {
			dstIp.append(driveAttribute.getValue() + ".");
		}
		if ("目的IP地址4".equals(driveAttribute.getDescription())) {
			dstIp.append(driveAttribute.getValue() + "");
		}
		
		if ("是否匹配cos".equals(driveAttribute.getDescription())) {
			aclInfoObject.setIsMatchCos(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("cos".equals(driveAttribute.getDescription())) {
			aclInfoObject.setCosValue(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("是否匹配DSCP".equals(driveAttribute.getDescription())) {
			aclInfoObject.setIsMatchDSCP(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("DSCP".equals(driveAttribute.getDescription())) {
			aclInfoObject.setDscpValue(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("是否匹配TOS".equals(driveAttribute.getDescription())) {
			aclInfoObject.setIsMatchTOS(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("TOS".equals(driveAttribute.getDescription())) {
			aclInfoObject.setTosValue(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("是否匹配源端口".equals(driveAttribute.getDescription())) {
			aclInfoObject.setIsSourcePort(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("源端口号".equals(driveAttribute.getDescription())) {
			aclInfoObject.setSourcePort(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("是否匹配目的端口".equals(driveAttribute.getDescription())) {
			aclInfoObject.setIsPurposePort(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("目的端口号".equals(driveAttribute.getDescription())) {
			aclInfoObject.setPurposePort(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("规则应用对象".equals(driveAttribute.getDescription())) {
			aclInfoObject.setRuleObject(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("传输层协议类型".equals(driveAttribute.getDescription())) {
			aclInfoObject.setTreatyType(Integer.parseInt(driveAttribute.getValue()));
		}
	}
	
	private void aclObjectTODriveAttribute(DriveAttribute driveAttribute,AclInfoObject aclInfoObject, int length) {

		if ("条目ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(length+ "");
		}
		if ("动作".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getAct()+"");
		}
		if ("入出方向".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getDirection()+"");
		}
		if ("端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getPortNumber() + "");
		}
		if ("vlanID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getVlanId()+"");
		}
		if ("是否匹配源MAC地址".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getIsSourceMac()+"");
		}
		if ("源MAC地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getSourceMac().trim().split("-")[0]);
		}
		if ("源MAC地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getSourceMac().trim().split("-")[1]);
		}
		if ("源MAC地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getSourceMac().trim().split("-")[2]);
		}
		if ("源MAC地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getSourceMac().trim().split("-")[3]);
		}
		if ("源MAC地址5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getSourceMac().trim().split("-")[4]);
		}
		if ("源MAC地址6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getSourceMac().trim().split("-")[5]);
		}
		if ("是否匹配目的MAC地址".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getIsPurposeMac()+"");
		}
		if ("目的MAC地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getPurposeMac().trim().split("-")[0]);
		}
		if ("目的MAC地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getPurposeMac().trim().split("-")[1]);
		}
		if ("目的MAC地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getPurposeMac().trim().split("-")[2]);
		}
		if ("目的MAC地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getPurposeMac().trim().split("-")[3]);
		}
		if ("目的MAC地址5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getPurposeMac().trim().split("-")[4]);
		}
		if ("目的MAC地址6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getPurposeMac().trim().split("-")[5]);
		}
		if ("是否匹配源IP地址".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getIsSourceIp()+"");
		}
		if ("源ip地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getSourceIp().trim().split("\\.")[0]);
		}
		if ("源ip地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getSourceIp().trim().split("\\.")[1]);
		}
		if ("源ip地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getSourceIp().trim().split("\\.")[2]);
		}
		if ("源ip地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getSourceIp().trim().split("\\.")[3]);
		}
		if ("是否匹配目的IP地址".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getIsPurposeIp()+"");
		}
		if ("目的IP地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getPurposeIp().trim().split("\\.")[0]);
		}
		if ("目的IP地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getPurposeIp().trim().split("\\.")[1]);
		}
		if ("目的IP地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getPurposeIp().trim().split("\\.")[2]);
		}
		if ("目的IP地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getPurposeIp().trim().split("\\.")[3]);
		}
		if ("是否匹配cos".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getIsMatchCos()+"");
		}
		if ("cos".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getCosValue()+"");
		}
		if ("是否匹配DSCP".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getIsMatchDSCP()+"");
		}
		if ("DSCP".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getDscpValue()+"");
		}
		if ("是否匹配TOS".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getIsMatchTOS()+"");
		}
		if ("TOS".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getTosValue()+"");
		}
		if ("是否匹配源端口".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getIsSourcePort()+"");
		}
		if ("源端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getSourcePort()+"");
		}
		if ("是否匹配目的端口".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getIsPurposePort()+"");
		}
		if ("目的端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getPurposePort()+"");
		}
		if ("规则应用对象".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getRuleObject()+"");
		}
		//传输层协议类型
		if ("传输层协议类型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(aclInfoObject.getTreatyType()+"");
		}
	}
}
