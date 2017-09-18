package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.TMSOAMInfoObject;
import com.nms.drive.service.bean.TMSOAMObject;
import com.nms.drive.service.impl.CoderUtils;

/**
 * TMS OAM配置块(1AH)
 * 
 * @author hulei
 * 
 */
public class AnalysisTMSOAMTable extends AnalysisBase {
	/**
	 * TMSOAMObject对象转换为命令
	 * 
	 * @param tmsOamObject
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisTMSOAMObjectToCommands(TMSOAMObject tmsOamObject, String TMSOAMInfoXml) throws Exception {
		List<TMSOAMInfoObject> tmsOamInfoList = tmsOamObject.getTmsOamInfoList();

		TMSOAMInfoObject tmsInfoObject = null;
		try {
			// 存放所有的DriveRoot对象
			List<DriveRoot> driveRootList = new ArrayList<DriveRoot>();
			for (int i = 0; i < tmsOamInfoList.size(); i++) {
				tmsInfoObject = tmsOamInfoList.get(i);
				// 获取 TMSOAMObj中TMSOAMInfoObj的DriveRoot对象
				DriveRoot tMSOAMInfoObjDriveRoot = this.getTMSOAMInfoObjDriveRoot(tmsInfoObject, TMSOAMInfoXml);
				driveRootList.add(tMSOAMInfoObjDriveRoot);
			}

			// 生成最终需要处理的DriveRoot对象
			DriveRoot tmsOAMDriveRoot = this.getTMSOAMObjDriveRoot(driveRootList);
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] commands = analysisDriveXmlToCommand.analysisCommands(tmsOAMDriveRoot);
			CoderUtils.print16String(commands);
			return commands;

		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 命令转换为TMSOAMObject对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public TMSOAMObject analysisCommandsToTMSOAMObject(byte[] commands, String configXml) throws Exception {
		TMSOAMObject tmsOamObject = new TMSOAMObject();
		TMSOAMInfoObject tmsInfoObject = null;
		byte[] tmsOamInfoByte = new byte[50];

		int pointer = 0;// 下标
		pointer = 0;
		int tmsOamData = tmsOamInfoByte.length;// 一个tmsOamObject的长度
		int tmsOamInfoCount = (commands.length - pointer ) / tmsOamData;
		try {

			for (int i = 0; i < tmsOamInfoCount; i++) {
				System.arraycopy(commands, pointer, tmsOamInfoByte, 0, tmsOamInfoByte.length);
				pointer += tmsOamInfoByte.length;
				tmsInfoObject = this.getTMSOAMInfoObj(tmsOamInfoByte, configXml);
				tmsOamObject.getTmsOamInfoList().add(tmsInfoObject);
			}
		} catch (Exception e) {
			throw e;
		}
		return tmsOamObject;
	}

	/**
	 * 获取TMSOAMObj的DriveRoot对象
	 * 
	 * @return
	 */
	private DriveRoot getTMSOAMObjDriveRoot(List<DriveRoot> driveRootList) {
		DriveRoot driveRoot = null;
		DriveRoot driveRoot_config = new DriveRoot();
		for (int i = 0; i < driveRootList.size(); i++) {
			driveRoot = driveRootList.get(i);
			for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
				driveRoot_config.getDriveAttributeList().add(driveRoot.getDriveAttributeList().get(j));
			}
		}
		return driveRoot_config;

	}

	/**
	 * 获取 TMSOAMObj中TMSOAMInfoObj的DriveRoot对象
	 * 
	 * @param tmsOamObject
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	private DriveRoot getTMSOAMInfoObjDriveRoot(TMSOAMInfoObject tmsInfoObject, String TMSOAMInfoXml) throws Exception {
		DriveRoot driveRoot = super.LoadDriveXml(TMSOAMInfoXml);
		DriveAttribute driveAttribute = null;
		String[] megIcc = spiltMeg(tmsInfoObject.getMegIcc());
		String[] megUmc = spiltMeg(tmsInfoObject.getMegUmc());
		String[] sourceMac = tmsInfoObject.getSourceMac().split("-");
		String[] targetMac = tmsInfoObject.getEndMac().split("-");
		for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
			driveAttribute = driveRoot.getDriveAttributeList().get(i);
			this.assignTMSOAMInfoObjDriveAttribute(driveAttribute, tmsInfoObject, megIcc, megUmc, sourceMac, targetMac);
		}
		return driveRoot;

	}

	/**
	 * 根据命令生成TMSOAMInfoObject对象
	 * 
	 * @param commands
	 * @param tmsOamInfoXMl
	 * @return
	 * @throws Exception
	 */
	private TMSOAMInfoObject getTMSOAMInfoObj(byte[] commands, String tmsOamInfoXMl) throws Exception {
		TMSOAMInfoObject infoObject = new TMSOAMInfoObject();
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(commands);
		try {
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(tmsOamInfoXMl);
			DriveAttribute driveAttribute = null;
			StringBuffer megIccSb = new StringBuffer();
			StringBuffer megUmcSb = new StringBuffer();
			StringBuffer sourceMac = new StringBuffer();
			StringBuffer targetMac = new StringBuffer();
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				this.assignTMSOAMInfoObject(infoObject, driveAttribute, megIccSb, megUmcSb, sourceMac, targetMac);// 赋值
			}
			return infoObject;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 命令转换为TMSOAMObject中的Reserve对象
	 * 
	 * @param commands
	 * @param tmsOamReserveXml
	 * @return
	 */
	@SuppressWarnings("unused")
	private Integer getTMSOAMReserve(byte[] commands, String tmsOamReserveXml) {
		// 全部都是备用，暂不处理
		return 0;

	}

	/**
	 * 给TMSOAMInfoObjDriveAttribute对象赋值
	 * 
	 * @param driveAttribute
	 * @param tmsOamObject
	 */

	private void assignTMSOAMInfoObjDriveAttribute(DriveAttribute driveAttribute, 
			TMSOAMInfoObject infoObject, String[] megIcc, String[] megUmc, String[] sourceMac, String[] targetMac) {
		if ("端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(infoObject.getPortNumber() + "");
		}else if ("TMS OAM使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(infoObject.getVsOamEnable() + "");
		} else if ("MEG ICC1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(megIcc[0]);
		} else if ("MEG ICC2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(megIcc[1]);
		} else if ("MEG ICC3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(megIcc[2]);
		} else if ("MEG ICC4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(megIcc[3]);
		} else if ("MEG ICC5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(megIcc[4]);
		} else if ("MEG ICC6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(megIcc[5]);
		} else if ("MEG UMC1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(megUmc[0]);
		} else if ("MEG UMC2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(megUmc[1]);
		} else if ("MEG UMC3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(megUmc[2]);
		} else if ("MEG UMC4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(megUmc[3]);
		} else if ("MEG UMC5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(megUmc[4]);
		} else if ("MEG UMC6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(megUmc[5]);
		} else if ("备用1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(infoObject.getReserve1() + "");
		} else if ("源MEP ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(infoObject.getMepId() + "");
		} else if ("对等MEP ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(infoObject.getEqualMepId() + "");
		} else if ("CV帧".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(cvBitToInt(infoObject) + "");
		} else if ("MEL".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(infoObject.getMel() + "");
		} else if ("TC".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(infoObject.getTc() + "");
		}else if ("LM".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(infoObject.getLm() + "");
		}else if("源MAC地址1".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(sourceMac[0]);
		}else if("源MAC地址2".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(sourceMac[1]);
		}else if("源MAC地址3".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(sourceMac[2]);
		}else if("源MAC地址4".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(sourceMac[3]);
		}else if("源MAC地址5".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(sourceMac[4]);
		}else if("源MAC地址6".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(sourceMac[5]);
		}else if("目的MAC地址1".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(targetMac[0]);
		}else if("目的MAC地址2".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(targetMac[1]);
		}else if("目的MAC地址3".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(targetMac[2]);
		}else if("目的MAC地址4".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(targetMac[3]);
		}else if("目的MAC地址5".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(targetMac[4]);
		}else if("目的MAC地址6".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(targetMac[5]);
		}else if("增加外层VLAN使能".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(infoObject.getVlanEnable()+"");
		}else if("外层VLAN值".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(infoObject.getVlanValue()+"");
		}else if("外层TP_ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(infoObject.getTpId()+"");
		}
	}

	/**
	 * 给TMSOAMInfoObject对象赋值
	 * 
	 * @param infoObject
	 * @param driveAttribute
	 */
	private void assignTMSOAMInfoObject(TMSOAMInfoObject infoObject, DriveAttribute driveAttribute, StringBuffer megIccSb,
							StringBuffer megUmcSb, StringBuffer sourceMac, StringBuffer targetMac) {
		
		if ("端口号".equals(driveAttribute.getDescription())) {
			infoObject.setPortNumber(Integer.parseInt(driveAttribute.getValue()));
		}else if ("TMS OAM使能".equals(driveAttribute.getDescription())) {
			infoObject.setVsOamEnable(Integer.parseInt(driveAttribute.getValue()));
		} else if ("MEG ICC1".equals(driveAttribute.getDescription())) {
			megIccSb.append(driveAttribute.getValue()).append(",");
		} else if ("MEG ICC2".equals(driveAttribute.getDescription())) {
			megIccSb.append(driveAttribute.getValue()).append(",");
		} else if ("MEG ICC3".equals(driveAttribute.getDescription())) {
			megIccSb.append(driveAttribute.getValue()).append(",");
		} else if ("MEG ICC4".equals(driveAttribute.getDescription())) {
			megIccSb.append(driveAttribute.getValue()).append(",");
		} else if ("MEG ICC5".equals(driveAttribute.getDescription())) {
			megIccSb.append(driveAttribute.getValue()).append(",");
		} else if ("MEG ICC6".equals(driveAttribute.getDescription())) {
			megIccSb.append(driveAttribute.getValue());
			infoObject.setMegIcc(megIccSb.toString());// 赋值
		} else if ("MEG UMC1".equals(driveAttribute.getDescription())) {
			megUmcSb.append(driveAttribute.getValue()).append(",");
		} else if ("MEG UMC2".equals(driveAttribute.getDescription())) {
			megUmcSb.append(driveAttribute.getValue()).append(",");
		} else if ("MEG UMC3".equals(driveAttribute.getDescription())) {
			megUmcSb.append(driveAttribute.getValue()).append(",");
		} else if ("MEG UMC4".equals(driveAttribute.getDescription())) {
			megUmcSb.append(driveAttribute.getValue()).append(",");
		} else if ("MEG UMC5".equals(driveAttribute.getDescription())) {
			megUmcSb.append(driveAttribute.getValue()).append(",");
		} else if ("MEG UMC6".equals(driveAttribute.getDescription())) {
			megUmcSb.append(driveAttribute.getValue());
			infoObject.setMegUmc(megUmcSb.toString());// 赋值
		} else if ("备用1".equals(driveAttribute.getDescription())) {
			// 不用处理
		} else if ("源MEP ID".equals(driveAttribute.getDescription())) {
			infoObject.setMepId(Integer.parseInt(driveAttribute.getValue()));
		} else if ("对等MEP ID".equals(driveAttribute.getDescription())) {
			infoObject.setEqualMepId(Integer.parseInt(driveAttribute.getValue()));
		} else if ("CV帧".equals(driveAttribute.getDescription())) {
			String cvBitString = cvInttoBitString(Integer.parseInt(driveAttribute.getValue()));
			infoObject.setSccTestEnable(cvBitString.substring(0, 1));
			infoObject.setSsmEnable(cvBitString.substring(1, 2));
			infoObject.setApsEnable(cvBitString.substring(2, 3));
			infoObject.setCvReserve(cvBitString.substring(3, 4));
			infoObject.setCvCircle(cvBitString.substring(4, 7));
			infoObject.setCvEnable(cvBitString.substring(7));
		} else if ("MEL".equals(driveAttribute.getDescription())) {
			infoObject.setMel(Integer.parseInt(driveAttribute.getValue()));
		} else if ("LM".equals(driveAttribute.getDescription())) {
			infoObject.setLm(Integer.parseInt(driveAttribute.getValue()));
		}else if ("TC".equals(driveAttribute.getDescription())) {
			infoObject.setTc(Integer.parseInt(driveAttribute.getValue()));
		}
		//源MAC地址1
		else  if("源MAC地址1".equals(driveAttribute.getDescription())){
			sourceMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//源MAC地址2
		else if("源MAC地址2".equals(driveAttribute.getDescription())){
			sourceMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//源MAC地址3
		else if("源MAC地址3".equals(driveAttribute.getDescription())){
			sourceMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//源MAC地址4
		else if("源MAC地址4".equals(driveAttribute.getDescription())){
			sourceMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//源MAC地址5
		else if("源MAC地址5".equals(driveAttribute.getDescription())){
			sourceMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//源MAC地址6
		else if("源MAC地址6".equals(driveAttribute.getDescription())){
			sourceMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue()));
			infoObject.setSourceMac(sourceMac.toString());
		}
		//目的MAC地址1
		else if("目的MAC地址1".equals(driveAttribute.getDescription())){
			targetMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//目的MAC地址2
		else if("目的MAC地址2".equals(driveAttribute.getDescription())){
			targetMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//目的MAC地址3
		else if("目的MAC地址3".equals(driveAttribute.getDescription())){
			targetMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//目的MAC地址4
		else if("目的MAC地址4".equals(driveAttribute.getDescription())){
			targetMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//目的MAC地址5
		else if("目的MAC地址5".equals(driveAttribute.getDescription())){
			targetMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//目的MAC地址6
		else if("目的MAC地址6".equals(driveAttribute.getDescription())){
			targetMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue()));
			infoObject.setEndMac(targetMac.toString());
		}
		//增加外层VLAN使能
		else if("增加外层VLAN使能".equals(driveAttribute.getDescription())){
			infoObject.setVlanEnable(Integer.parseInt(driveAttribute.getValue()));
		}
		//外层VLAN值
		else if("外层VLAN值".equals(driveAttribute.getDescription())){
			infoObject.setVlanValue(Integer.parseInt(driveAttribute.getValue()));
		}
		//外层TP_ID
		else if("外层TP_ID".equals(driveAttribute.getDescription())){
			infoObject.setTpId(Integer.parseInt(driveAttribute.getValue()));
		}

	}

	/**
	 * 补足4个对象
	 * 
	 * @param tmsOamInfoList
	 * @return
	 */
	private List<TMSOAMInfoObject> addTMSOAMInfoObj(List<TMSOAMInfoObject> tmsOamInfoList) {
		while (tmsOamInfoList.size() < 4) {
			TMSOAMInfoObject infoObject = new TMSOAMInfoObject();
			infoObject.setVsOamEnable(0);
			infoObject.setMegIcc("1,2,3,4,5,6");
			infoObject.setMegUmc("7,8,9,10,11,12");
			infoObject.setReserve1(0);
			infoObject.setMepId(1);
			infoObject.setEqualMepId(1);
			infoObject.setCvEnable("0");
			infoObject.setCvCircle("001");
			infoObject.setCvReserve("0");
			infoObject.setApsEnable("0");
			infoObject.setSsmEnable("0");
			infoObject.setSccTestEnable("0");
			infoObject.setMel(7);
			infoObject.setReserve2(0);
			tmsOamInfoList.add(infoObject);
		}
		return tmsOamInfoList;
	}

	/**
	 * 切割MEG ICC 或MEG UMC
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	private String[] spiltMeg(String str) throws Exception {
		String[] strs = str.split(",");
		if (strs.length != 6) {
			throw new Exception("传入MEG ICC 或MEG UMC错误");
		}
		return strs;
	}

	/**
	 * cv的所有bit转化为int值
	 * 
	 * @param infoObject
	 * @return
	 */
	private int cvBitToInt(TMSOAMInfoObject infoObject) {
		StringBuffer sb = new StringBuffer();
		sb.append(infoObject.getSccTestEnable()).append(infoObject.getSsmEnable()).append(infoObject.getApsEnable()).append(infoObject.getCvReserve()).append(infoObject.getCvCircle()).append(infoObject.getCvEnable());
		char[] cvChar = sb.toString().toCharArray();
		int cvInt = CoderUtils.convertAlgorism(cvChar);
		return cvInt;

	}

	/**
	 * 十进制转换为bit字符串
	 * 
	 * @param driveAttribute
	 * @return
	 */
	private String cvInttoBitString(Integer cvInt) {
		String str = CoderUtils.convertBinary(cvInt);
		StringBuffer sb = new StringBuffer();
		if (str.length() < 8) {
			for (int i = str.length(); i < 8; i++) {
				sb.append("0");
			}

		}
		sb.append(str);
		return sb.toString();

	}

}
