package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.ELineObject;
import com.nms.drive.service.bean.VpwsBuffer;
import com.nms.drive.service.impl.CoderUtils;

/**
 * 
 * Eline解析
 * @author 彭冲
 *
 */
public class AnalysisELineTable extends AnalysisBase {

	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int filecount_sub = 134;// VPWS_SUB.xml的长度
	private int filecount_sub_sub = 60;// VPWS_SUB_SUB.xml的长度
	private int tiaomu = 2;// 条目的字节长度

	/**
	 * 根据对象生成字节数组
	 * 
	 * @param ELineObject对象
	 * @param configXml配置XML
	 * @return 命令
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(List<ELineObject> elineObjectList, String configXml) throws Exception {

		// 获得VPWS XML
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		String VPWSFile = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
		driveRoot_config.getDriveAttributeList().get(0).getListRootList().clear();// 清除本身自带的一个ListRoot
		driveRoot_config.getDriveAttributeList().get(0).setValue(elineObjectList.size() + "");// 条目数

		ELineObject elineObject = null;// VPWS对象
		VpwsBuffer vpwsBuffer = null;// 流对象
		List<VpwsBuffer> listvpwsobject =null;// 流对象集合
		DriveRoot liuDriveRoot = null;// 流的XML对象
		String liuFile = "";
		int liuCount = 0;

		for (int i = 0; i < elineObjectList.size(); i++) {
			elineObject = elineObjectList.get(i);
			liuCount = elineObject.getVpwsBufferList().size();
			DriveRoot VPWSDriveRoot = super.LoadDriveXml(VPWSFile);
			List<DriveAttribute> VPWSDriveRoot_DriveAttribute = VPWSDriveRoot.getDriveAttributeList();// 获得流
			ListRoot listRoot = new ListRoot();
			listRoot.setDriveAttributeList(VPWSDriveRoot_DriveAttribute);
			for (int j = 0; j < VPWSDriveRoot_DriveAttribute.size(); j++) {
				DriveAttribute driveattribute = listRoot.getDriveAttributeList().get(j);

				// 很多赋值
				ElineObjecttToDriveAttribute(elineObject, driveattribute);

				if ("流个数".equals(VPWSDriveRoot_DriveAttribute.get(j).getDescription())) {

					VPWSDriveRoot_DriveAttribute.get(j).setValue(liuCount + "");// 赋值
					listvpwsobject = elineObject.getVpwsBufferList();// 流对象
					liuFile = VPWSDriveRoot_DriveAttribute.get(j).getListRootList().get(0).getFile();
					VPWSDriveRoot_DriveAttribute.get(j).getListRootList().clear();// 清楚本身自带的一个ListRoot

					for (int k = 0; k < listvpwsobject.size(); k++) {// 流对象循环
						liuDriveRoot = super.LoadDriveXml(liuFile);
						vpwsBuffer = listvpwsobject.get(k);
						
						 String[] strs_targetIP = vpwsBuffer.getTargetIP().split("\\.");
						 String[] strs_sourceIP = vpwsBuffer.getSourceIP().split("\\.");	
						 
						 String[] strs_sourceMac = vpwsBuffer.getSourceMac().split("-");
						 String[] strs_targetMac = vpwsBuffer.getTargetMac().split("-");						
						// 16进制转化10进制
							for (int p = 0; p < strs_sourceMac.length; p++) {
								strs_sourceMac[p] = Integer.parseInt(strs_sourceMac[p], 16) + "";
							}
							for (int p = 0; p < strs_targetMac.length; p++) {
								strs_targetMac[p] = Integer.parseInt(strs_targetMac[p], 16) + "";
							}
						 for (int l = 0; l < liuDriveRoot.getDriveAttributeList().size(); l++) {// 流元素循环
							DriveAttribute driveattributes = liuDriveRoot.getDriveAttributeList().get(l);
							// 很多赋值
							VpwsBufferToDriveAttribute(vpwsBuffer, driveattributes);
							Mac_IpTODriveAttribute(strs_sourceMac, strs_targetMac, strs_sourceIP, strs_targetIP, driveattributes);
						}
						ListRoot listRoots = new ListRoot();
						listRoots.setDriveAttributeList(liuDriveRoot.getDriveAttributeList());
						VPWSDriveRoot_DriveAttribute.get(j).getListRootList().add(listRoots);
					}
				}
			}

			driveRoot_config.getDriveAttributeList().get(0).getListRootList().add(listRoot);
		}

		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] commands = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
		CoderUtils.print16String(commands);
		return commands;
	}

	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return ELineObject
	 */
	public List<ELineObject> analysisCommandToObject(byte[] commands, String configXml) throws Exception {

		List<ELineObject> elineObjectList = new ArrayList<ELineObject>();

		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		String file_sub = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();

		DriveRoot driveRoot_config_sub = super.LoadDriveXml(file_sub);
		String file_sub_sub = driveRoot_config_sub.getDriveAttributeList().get(driveRoot_config_sub.getDriveAttributeList().size() - 1).getListRootList().get(0).getFile();

		// 起始长度
		int start = 0;
		// 条目数
		byte[] counts = { 0x00, 0x00, commands[start], commands[start + 1] };
		int count = CoderUtils.bytesToInt(counts);
		// 流的个数
		int filecount = 0;
		int filecount1 = 0;

		AnalysisCommandByDriveXml analysisCommandByDriveXml_sub = new AnalysisCommandByDriveXml();
		AnalysisCommandByDriveXml analysisCommandByDriveXml_sub_sub = new AnalysisCommandByDriveXml();
		byte[] a = new byte[filecount_sub];
		byte[] b = new byte[filecount_sub_sub];
		int length= 0;
		for (int i = 0; i < count; i++) {// 按条目数遍历

			ELineObject elineObject = new ELineObject();// VPWS对象
			System.arraycopy(commands, start +length+ tiaomu, a, 0, a.length);
			analysisCommandByDriveXml_sub.setCommands(a);
			driveRoot_config_sub = analysisCommandByDriveXml_sub.analysisDriveAttrbute(file_sub);
			int element_sub = driveRoot_config_sub.getDriveAttributeList().size();
			length = length+134;
			for (int j = 0; j < element_sub; j++) {// 按TUNNELTable_SUB元素个数遍历

				DriveAttribute driveattribute_sub = new DriveAttribute();
				driveattribute_sub = driveRoot_config_sub.getDriveAttributeList().get(j);
				
				//赋值
				DriveAttributeToElineObject(elineObject, driveattribute_sub);

				if (driveattribute_sub.getDescription().equals("流个数")) {

					DriveRoot driveRoot_config_sub_sub = super.LoadDriveXml(file_sub_sub);

					filecount = Integer.parseInt(driveattribute_sub.getValue());
					for (int k = 0; k < filecount; k++) {// 按流个数遍历

						VpwsBuffer vpwsBuffer = new VpwsBuffer();// 流对象
						System.arraycopy(commands, start + tiaomu + length, b, 0, b.length);
						analysisCommandByDriveXml_sub_sub.setCommands(b);
						driveRoot_config_sub_sub = analysisCommandByDriveXml_sub_sub.analysisDriveAttrbute(file_sub_sub);
						int element_sub_sub = driveRoot_config_sub_sub.getDriveAttributeList().size();
						DriveAttribute driveattribute_sub_sub = new DriveAttribute();
						StringBuffer sb_sourceMac = new StringBuffer();
						StringBuffer sb_targetMac = new StringBuffer();
						StringBuffer sb_sourceIP = new StringBuffer();
						StringBuffer sb_targetIP = new StringBuffer();
						for (int p = 0; p < element_sub_sub; p++) {// 按TUNNELTable_SUB_SUB元素个数遍历

							driveattribute_sub_sub = driveRoot_config_sub_sub.getDriveAttributeList().get(p);
							
							//赋值
							DriveAttributeToVpwsBuffer(vpwsBuffer, driveattribute_sub_sub);
							DriveAttributeTOMac_Ip(sb_sourceMac, sb_targetMac, sb_sourceIP, sb_targetIP, driveattribute_sub_sub, vpwsBuffer);
						}
						elineObject.getVpwsBufferList().add(vpwsBuffer);
						filecount1 = 1+filecount1;
						length = length+60;
					}
					
				}
			}
			elineObjectList.add(elineObject);
		}
		return elineObjectList;
	}

	/**
	 * elineObject值赋给driveAttribute
	 * 
	 * @param elineObject
	 * @param driveAttribute
	 */
	public void ElineObjecttToDriveAttribute(ELineObject elineObject, DriveAttribute driveAttribute) {

		// (NNI接口设置)VPWS业务实例ID
		if ("(NNI接口设置)VPWS业务实例ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getVpwsId() + "");
		}
		
		//(NNI接口设置)是否为WRAP业务
		if ("(NNI接口设置)是否为WRAP业务".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getWrap() + "");
		}
		//业务类型
		if ("业务类型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getType() + "");
		}
		//(NNI接口设置)控制字使能
		if ("(NNI接口设置)控制字使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getControlEnabl() + "");
		}
		
		// (NNI接口设置)PW ID
		if ("(NNI接口设置)PW ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getPwIdNNI() + "");
		}
		// (NNI接口设置)下话TAG识别
		if ("(NNI接口设置)下话TAG识别".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getUpTagNNI() + "");
		}

		// (NNI接口设置)上话TAG行为
		if ("(NNI接口设置)上话TAG行为".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getDownTagNNI() + "");
		}

		// (NNI接口设置)上话增加VLAN ID
		if ("(NNI接口设置)上话增加VLAN ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getVlanIdNNI() + "");
		}

		// (NNI接口设置)上话增加VLAN PRI
		if ("(NNI接口设置)上话增加VLAN PRI".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getVlanPriNNI() + "");
		}else if("角色".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(elineObject.getRole() + "");
		}
		
		// (UNI接口设置)槽位号
		if ("(UNI接口设置)槽位号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getSlotUNI() + "");
		}

		// (UNI接口设置)端口号
		if ("(UNI接口设置)端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getProtUNI() + "");
		}

		// (UNI接口设置)模式
		if ("(UNI接口设置)模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getModelUNI() + "");
		}

		// (UNI接口设置)CIR
		if ("(UNI接口设置)CIR".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getCirUNI() + "");
		}

		// (UNI接口设置)PIR
		if ("(UNI接口设置)PIR".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getPirUNI() + "");
		}
		// (UNI接口设置)CM
		if ("(UNI接口设置)CM".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getCm() + "");
		}

		// (UNI接口设置)CBS
		if ("(UNI接口设置)CBS".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getCbs() + "");
		}

		// (UNI接口设置)PBS
		if ("(UNI接口设置)PBS".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getPbs() + "");
		}
		// (UNI接口设置)上话TAG识别能
		if ("(UNI接口设置)上话TAG识别能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getUpTagUNI() + "");
		}

		// (UNI接口设置)下话TAG行为  (UNI接口设置)下话TAG行为
		if ("(UNI接口设置)下话TAG行为".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getDownTagUNI() + "");
		}

		// (UNI接口设置)下话增加VLAN ID
		if ("(UNI接口设置)下话增加VLAN ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getVlanIdUNI() + "");
		}

		// (UNI接口设置)下话增加VLAN PRI  (UNI接口设置)PIR
		if ("(UNI接口设置)下话增加VLAN PRI".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.getVlanPriUNI() + "");
		}
		// (UNI接口设置)VC流量监管使能
		if ("(UNI接口设置)VC流量监管使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(elineObject.gettVCTrafficPolicing() + "");
		}
	}

	/**
	 * VpwsBuffer值赋给driveAttribute
	 * 
	 * @param VpwsBuffer
	 * @param driveAttribute
	 */
	public void VpwsBufferToDriveAttribute(VpwsBuffer vpwsBuffer, DriveAttribute driveAttribute) {

		//流ID
		if("流ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(vpwsBuffer.getVpwsBufferID()+"");
		}
		// 流使能
		if ("流使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(vpwsBuffer.getBufferEnable() + "");
		}

		// PW标签
		if ("PW标签".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(vpwsBuffer.getPwLable() + "");
		}

		// VLAN ID
		if ("VLAN ID".equals(driveAttribute.getDescription())) {
//			System.out.println("vlanid  "+vpwsBuffer.getVlanId());
			driveAttribute.setValue(vpwsBuffer.getVlanId() + "");
		}

		// IP DSCP
		if ("IP DSCP".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(vpwsBuffer.getIpDscp() + "");
		}

		// 模式
		if ("模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(vpwsBuffer.getModel() + "");
		}

		// CIR
		if ("CIR".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(vpwsBuffer.getCir() + "");
		}

		// PIR
		if ("PIR".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(vpwsBuffer.getPir() + "");
		}

		// CM
		if ("CM".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(vpwsBuffer.getCm() + "");
		}

		// CBS
		if ("CBS".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(vpwsBuffer.getCbs() + "");
		}

		// PBS
		if ("PBS".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(vpwsBuffer.getPbs() + "");
		}

		// 策略
		if ("策略".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(vpwsBuffer.getStrategy() + "");
		}

		//802.1P
		if("802.1P".equals(driveAttribute.getDescription())){
          driveAttribute.setValue(vpwsBuffer.get_802_1P()+"");
		}
		// 指配PHB
		if ("指配PHB".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(vpwsBuffer.getPhb() + "");
		}
		//源TCP/UDP端口号
		if ("源TCP/UDP端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(vpwsBuffer.getSourceTcpPortId() + "");
		}
		//宿TCP/UDP端口号
		if ("宿TCP/UDP端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(vpwsBuffer.getEndTcpPortId() + "");
		}
		//IPTOS
		if("IPTOS".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(vpwsBuffer.getIPTOS()+"");
		}
		//端口类型
		if("端口类型".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(vpwsBuffer.getPortType()+"");
		}
	}

	/**
	 * driveAttribute值赋给ELineObject
	 * 
	 * @param ELineObject
	 * @param driveAttribute
	 */
	public void DriveAttributeToElineObject(ELineObject elineObject, DriveAttribute driveAttribute) {

		// (NNI接口设置)VPWS业务实例ID
		if ("(NNI接口设置)VPWS业务实例ID".equals(driveAttribute.getDescription())) {
			elineObject.setVpwsId(Integer.parseInt(driveAttribute.getValue()));
		}
		
		//(NNI接口设置)是否为WRAP业务
		if ("(NNI接口设置)是否为WRAP业务".equals(driveAttribute.getDescription())) {
			elineObject.setWrap(Integer.parseInt(driveAttribute.getValue()));
		}
		//业务类型
		if ("业务类型".equals(driveAttribute.getDescription())) {
			elineObject.setType(Integer.parseInt(driveAttribute.getValue()));
		}else if("角色".equals(driveAttribute.getDescription())){
			elineObject.setRole(Integer.parseInt(driveAttribute.getValue()));
		}
		// (NNI接口设置)PW ID
		if ("(NNI接口设置)PW ID".equals(driveAttribute.getDescription())) {
			elineObject.setPwIdNNI(Integer.parseInt(driveAttribute.getValue()));
		}

		// (NNI接口设置)下话TAG识别
		if ("(NNI接口设置)下话TAG识别".equals(driveAttribute.getDescription())) {
			elineObject.setUpTagNNI(Integer.parseInt(driveAttribute.getValue()));
		}

		// (NNI接口设置)上话TAG行为
		if ("(NNI接口设置)上话TAG行为".equals(driveAttribute.getDescription())) {
			elineObject.setDownTagNNI(Integer.parseInt(driveAttribute.getValue()));
		}

		// (NNI接口设置)上话增加VLAN ID
		if ("(NNI接口设置)上话增加VLAN ID".equals(driveAttribute.getDescription())) {
			elineObject.setVlanIdNNI(Integer.parseInt(driveAttribute.getValue()));
		}

		// (NNI接口设置)上话增加VLAN PRI
		if ("(NNI接口设置)上话增加VLAN PRI".equals(driveAttribute.getDescription())) {
			elineObject.setVlanPriNNI(Integer.parseInt(driveAttribute.getValue()));
		}
		// (UNI接口设置)槽位号
		if ("(UNI接口设置)槽位号".equals(driveAttribute.getDescription())) {
			elineObject.setSlotUNI(Integer.parseInt(driveAttribute.getValue()));
		}

		// (UNI接口设置)端口号
		if ("(UNI接口设置)端口号".equals(driveAttribute.getDescription())) {
			elineObject.setProtUNI(Integer.parseInt(driveAttribute.getValue()));
		}

		// (UNI接口设置)模式
		if ("(UNI接口设置)模式".equals(driveAttribute.getDescription())) {
			elineObject.setModelUNI(Integer.parseInt(driveAttribute.getValue()));
		}

		// (UNI接口设置)CIR
		if ("(UNI接口设置)CIR".equals(driveAttribute.getDescription())) {
			elineObject.setCirUNI(Integer.parseInt(driveAttribute.getValue()));
		}
		// (UNI接口设置)PIR
		if ("(UNI接口设置)PIR".equals(driveAttribute.getDescription())) {
			elineObject.setPirUNI(Integer.parseInt(driveAttribute.getValue()));
		}

		// (UNI接口设置)上话TAG识别能
		if ("(UNI接口设置)上话TAG识别能".equals(driveAttribute.getDescription())) {
			elineObject.setUpTagUNI(Integer.parseInt(driveAttribute.getValue()));
		}
		// (UNI接口设置)下话TAG行为  (UNI接口设置)下话TAG行为
		if ("(UNI接口设置)下话TAG行为".equals(driveAttribute.getDescription())) {
			elineObject.setDownTagUNI(Integer.parseInt(driveAttribute.getValue()));
		}
		// (UNI接口设置)下话增加VLAN ID
		if ("(UNI接口设置)下话增加VLAN ID".equals(driveAttribute.getDescription())) {
			elineObject.setVlanIdUNI(Integer.parseInt(driveAttribute.getValue()));
		}

		// (UNI接口设置)下话增加VLAN PRI
		if ("(UNI接口设置)下话增加VLAN PRI".equals(driveAttribute.getDescription())) {
			elineObject.setVlanPriUNI(Integer.parseInt(driveAttribute.getValue()));
		}
		// (UNI接口设置)VC流量监管使能
		if ("(UNI接口设置)VC流量监管使能".equals(driveAttribute.getDescription())) {
			elineObject.settVCTrafficPolicing(Integer.parseInt(driveAttribute.getValue()));
		}
		
		// (UNI接口设置)CBS
		if ("(UNI接口设置)CBS".equals(driveAttribute.getDescription())) {
			elineObject.setCbs(Integer.parseInt(driveAttribute.getValue()));
		}

		// (UNI接口设置)PBS
		if ("(UNI接口设置)PBS".equals(driveAttribute.getDescription())) {
			elineObject.setPbs(Integer.parseInt(driveAttribute.getValue()));
		}
	}

	/**
	 * driveAttribute值赋给VpwsBuffer
	 * 
	 * @param VpwsBuffer
	 * @param driveAttribute
	 */
	public void DriveAttributeToVpwsBuffer(VpwsBuffer vpwsBuffer, DriveAttribute driveAttribute) {
		
		//流ID
		if("流ID".equals(driveAttribute.getDescription())){
			vpwsBuffer.setVpwsBufferID(Integer.parseInt(driveAttribute.getValue()));
		}
		
		// 流使能
		if ("流使能".equals(driveAttribute.getDescription())) {
			vpwsBuffer.setBufferEnable(Integer.parseInt(driveAttribute.getValue()));
		}
		//802.1P
		if("802.1P".equals(driveAttribute.getDescription())){
			vpwsBuffer.set_802_1P(Integer.parseInt(driveAttribute.getValue()));
		}
		
		

		// PW标签
		if ("PW标签".equals(driveAttribute.getDescription())) {
			vpwsBuffer.setPwLable(Integer.parseInt(driveAttribute.getValue()));
		}

		// VLAN ID
		if ("VLAN ID".equals(driveAttribute.getDescription())) {
			vpwsBuffer.setVlanId(Integer.parseInt(driveAttribute.getValue()));
		}

		// IP DSCP
		if ("IP DSCP".equals(driveAttribute.getDescription())) {
			vpwsBuffer.setIpDscp(Integer.parseInt(driveAttribute.getValue()));
		}

		// 模式
		if ("模式".equals(driveAttribute.getDescription())) {
			vpwsBuffer.setModel(Integer.parseInt(driveAttribute.getValue()));
		}

		// CIR
		if ("CIR".equals(driveAttribute.getDescription())) {
			vpwsBuffer.setCir(Integer.parseInt(driveAttribute.getValue()));
		}

		// PIR
		if ("PIR".equals(driveAttribute.getDescription())) {
			vpwsBuffer.setPir(Integer.parseInt(driveAttribute.getValue()));
		}

		// CM
		if ("CM".equals(driveAttribute.getDescription())) {
			vpwsBuffer.setCm(Integer.parseInt(driveAttribute.getValue()));
		}

		// CBS
		if ("CBS".equals(driveAttribute.getDescription())) {
			vpwsBuffer.setCbs(Integer.parseInt(driveAttribute.getValue()));
		}

		// PBS
		if ("PBS".equals(driveAttribute.getDescription())) {
			vpwsBuffer.setPbs(Integer.parseInt(driveAttribute.getValue()));
		}

		// 策略
		if ("策略".equals(driveAttribute.getDescription())) {
			vpwsBuffer.setStrategy(Integer.parseInt(driveAttribute.getValue()));
		}

		// 指配PHB
		if ("指配PHB".equals(driveAttribute.getDescription())) {
			vpwsBuffer.setPhb(Integer.parseInt(driveAttribute.getValue()));
		}
		//源TCP/UDP端口号
		if ("源TCP/UDP端口号".equals(driveAttribute.getDescription())) {
			vpwsBuffer.setSourceTcpPortId(Integer.parseInt(driveAttribute.getValue()));
		}
		//宿TCP/UDP端口号
		if ("宿TCP/UDP端口号".equals(driveAttribute.getDescription())) {
			vpwsBuffer.setEndTcpPortId(Integer.parseInt(driveAttribute.getValue()));
		}
		//IPTOS
		if("IPTOS".equals(driveAttribute.getDescription())){
			vpwsBuffer.setIPTOS(Integer.parseInt(driveAttribute.getValue()));
		}
		//端口类型
		if("端口类型".equals(driveAttribute.getDescription())){
			vpwsBuffer.setPortType(Integer.parseInt(driveAttribute.getValue()));
		}
	}

	public void Mac_IpTODriveAttribute(String[] strs_sourceMac, String[] strs_targetMac, String[] strs_sourceIP, String[] strs_targetIP, DriveAttribute driveAttribute) {
		
		if(strs_sourceMac.length>1){
			// 源MAC地址1
			if ("源MAC地址1".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_sourceMac[0]);
			}

			// 源MAC地址2
			if ("源MAC地址2".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_sourceMac[1]);
			}

			// 源MAC地址3
			if ("源MAC地址3".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_sourceMac[2]);
			}

			// 源MAC地址4
			if ("源MAC地址4".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_sourceMac[3]);
			}

			// 源MAC地址5
			if ("源MAC地址5".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_sourceMac[4]);
			}

			// 源MAC地址6
			if ("源MAC地址6".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_sourceMac[5]);
			}
		}
		
		if(strs_targetMac.length>1){
			// 目的MAC地址1
			if ("目的MAC地址1".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_targetMac[0]);
			}

			// 目的MAC地址2
			if ("目的MAC地址2".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_targetMac[1]);
			}

			// 目的MAC地址3
			if ("目的MAC地址3".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_targetMac[2]);
			}

			// 目的MAC地址4
			if ("目的MAC地址4".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_targetMac[3]);
			}

			// 目的MAC地址5
			if ("目的MAC地址5".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_targetMac[4]);
			}

			// 目的MAC地址6
			if ("目的MAC地址6".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_targetMac[5]);
			}
		}
		
		if(strs_sourceIP.length>1){
			// 源IP地址1
			if ("源IP地址1".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue("");
			}

			// 源IP地址2
			if ("源IP地址2".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue("");
			}

			// 源IP地址3
			if ("源IP地址3".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_sourceIP[0]);
			}

			// 源IP地址4
			if ("源IP地址4".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_sourceIP[1]);
			}

			// 源IP地址5
			if ("源IP地址5".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_sourceIP[2]);
			}

			// 源IP地址6
			if ("源IP地址6".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_sourceIP[3]);
			}
		}
		
		if(strs_targetIP.length>1){
			// 目的IP地址1
			if ("目的IP地址1".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue("");
			}

			// 目的IP地址2
			if ("目的IP地址2".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue("");
			}

			// 目的IP地址3
			if ("目的IP地址3".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_targetIP[0]);
			}

			// 目的IP地址4
			if ("目的IP地址4".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_targetIP[1]);
			}

			// 目的IP地址5
			if ("目的IP地址5".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_targetIP[2]);
			}

			// 目的IP地址6
			if ("目的IP地址6".equals(driveAttribute.getDescription())) {
				driveAttribute.setValue(strs_targetIP[3]);
			}
		}
		
	}

	public void DriveAttributeTOMac_Ip(StringBuffer sb_sourceMac, StringBuffer sb_targetMac, StringBuffer sb_sourceIP, StringBuffer sb_targetIP, DriveAttribute driveAttribute, VpwsBuffer vpwsBuffer) {
		String mac = "";
		try{
			mac = Integer.toHexString(Integer.parseInt(driveAttribute.getValue()));
			if(mac.length() == 1){
				mac = "0"+mac;
			}
		}catch(Exception e){
		}
		// 源MAC地址1
		if ("源MAC地址1".equals(driveAttribute.getDescription())) {
			sb_sourceMac.append(mac + "-");
		}

		// 源MAC地址2
		if ("源MAC地址2".equals(driveAttribute.getDescription())) {
			sb_sourceMac.append(mac + "-");
		}

		// 源MAC地址3
		if ("源MAC地址3".equals(driveAttribute.getDescription())) {
			sb_sourceMac.append(mac + "-");
		}

		// 源MAC地址4
		if ("源MAC地址4".equals(driveAttribute.getDescription())) {
			sb_sourceMac.append(mac + "-");
		}

		// 源MAC地址5
		if ("源MAC地址5".equals(driveAttribute.getDescription())) {
			sb_sourceMac.append(mac + "-");
		}

		// 源MAC地址6
		if ("源MAC地址6".equals(driveAttribute.getDescription())) {
			sb_sourceMac.append(mac);
			vpwsBuffer.setSourceMac(sb_sourceMac.toString());
		}

		// 目的MAC地址1
		if ("目的MAC地址1".equals(driveAttribute.getDescription())) {
			sb_targetMac.append(mac + "-");
		}

		// 目的MAC地址2
		if ("目的MAC地址2".equals(driveAttribute.getDescription())) {
			sb_targetMac.append(mac + "-");
		}

		// 目的MAC地址3
		if ("目的MAC地址3".equals(driveAttribute.getDescription())) {
			sb_targetMac.append(mac + "-");
		}

		// 目的MAC地址4
		if ("目的MAC地址4".equals(driveAttribute.getDescription())) {
			sb_targetMac.append(mac + "-");
		}

		// 目的MAC地址5
		if ("目的MAC地址5".equals(driveAttribute.getDescription())) {
			sb_targetMac.append(mac + "-");
		}

		// 目的MAC地址6
		if ("目的MAC地址6".equals(driveAttribute.getDescription())) {
			sb_targetMac.append(mac);
			vpwsBuffer.setTargetMac(sb_targetMac.toString());
		}

		// //源IP地址1
		// if("源IP地址1".equals(driveAttribute.getDescription())){
		// sb_sourceIP.append(driveAttribute.getValue()+"\\.");
		// }
		//		
		// //源IP地址2
		// if("源IP地址2".equals(driveAttribute.getDescription())){
		// sb_sourceIP.append(driveAttribute.getValue()+"\\.");
		// }

		// 源IP地址3
		if ("源IP地址3".equals(driveAttribute.getDescription())) {
			sb_sourceIP.append(driveAttribute.getValue() + ".");
		}

		// 源IP地址4
		if ("源IP地址4".equals(driveAttribute.getDescription())) {
			sb_sourceIP.append(driveAttribute.getValue() + ".");
		}

		// 源IP地址5
		if ("源IP地址5".equals(driveAttribute.getDescription())) {
			sb_sourceIP.append(driveAttribute.getValue() + ".");
		}

		// 源IP地址6
		if ("源IP地址6".equals(driveAttribute.getDescription())) {
			sb_sourceIP.append(driveAttribute.getValue());
			vpwsBuffer.setSourceIP(sb_sourceIP.toString());
		}

		// //目的IP地址1
		// if("目的IP地址1".equals(driveAttribute.getDescription())){
		// sb_targetIP.append(driveAttribute.getValue()+"\\.");
		// }
		//		
		// //目的IP地址2
		// if("目的IP地址2".equals(driveAttribute.getDescription())){
		// sb_targetIP.append(driveAttribute.getValue()+"\\.");
		// }

		// 目的IP地址3
		if ("目的IP地址3".equals(driveAttribute.getDescription())) {
			sb_targetIP.append(driveAttribute.getValue() + ".");
		}

		// 目的IP地址4
		if ("目的IP地址4".equals(driveAttribute.getDescription())) {
			sb_targetIP.append(driveAttribute.getValue() + ".");
		}

		// 目的IP地址5
		if ("目的IP地址5".equals(driveAttribute.getDescription())) {
			sb_targetIP.append(driveAttribute.getValue() + ".");
		}

		// 目的IP地址6
		if ("目的IP地址6".equals(driveAttribute.getDescription())) {
			sb_targetIP.append(driveAttribute.getValue());
			vpwsBuffer.setTargetIP(sb_targetIP.toString());
		}
	}
}
