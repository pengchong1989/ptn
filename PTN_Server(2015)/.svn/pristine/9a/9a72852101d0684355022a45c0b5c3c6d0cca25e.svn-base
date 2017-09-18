package com.nms.drive.analysis.datablock;


import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.ETHOAM;
import com.nms.drive.service.bean.ETHOAMInfo;
import com.nms.drive.service.impl.CoderUtils;

/**
 * 
 * 以太网OAM解析
 * @author 彭冲
 *
 */
public class AnalysisETHOAMTable extends AnalysisBase {
	
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int filecount_sub = 45;// 以太网OAM配置_SUB.xml的长度
	private int files = 5;// 以太网OAM配置.xml的长度
	
	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return commands
	 */
	public byte[] analysisETHOAMInfoToCommand(ETHOAM ethOAM,String configXml) throws Exception {
		
		// 获得以太网OAM配置.xml
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		int count = driveRoot_config.getDriveAttributeList().size();//获得以太网OAM配置.xml元素个数
		for (int i = 0; i < count; i++) {
			
			DriveAttribute driveAttribute = new DriveAttribute();
			driveAttribute = driveRoot_config.getDriveAttributeList().get(i);
			
			if("环回帧超时时间".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(ethOAM.getTimeOut()+"");
			}
			if("MEL".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(ethOAM.getMel()+"");
			}
			if("条目总数".equals(driveAttribute.getDescription())){
				
				int filecount = ethOAM.getETHOAMInfoList().size();// 获得条目数
				driveAttribute.setValue(filecount+"");
				String file = driveAttribute.getListRootList().get(0).getFile();
				driveAttribute.getListRootList().clear();// 清除本身自带的一个ListRoot			
				
				for (int j = 0; j < filecount;j++) {// 按条目数循环
					
					DriveRoot driveRoot_config_1 = super.LoadDriveXml(file);
					ListRoot listroot = new ListRoot();
					listroot.setDriveAttributeList(driveRoot_config_1.getDriveAttributeList());
					String[] strs_targetMac = ethOAM.getETHOAMInfoList().get(j).getTargetMac().split("-");	
					StringBuffer  sb = new StringBuffer();
					sb.append(ethOAM.getETHOAMInfoList().get(j).getMegIcc()+",");
					sb.append(ethOAM.getETHOAMInfoList().get(j).getMegUmc());
					String[] meg = sb.toString().split(",");
					
					// 将 ETHOAMInfo 对象信息 赋值到 driveRoot_config 对象中
					for (int k = 0; k < driveRoot_config_1.getDriveAttributeList().size(); k++) {// 按xml元素循环
						
						DriveAttribute driveAttribute_sub = driveRoot_config_1.getDriveAttributeList().get(k);						
						//赋值
						ETHOAMInfoTODriveAttribute(ethOAM.getETHOAMInfoList().get(j), driveAttribute_sub,strs_targetMac,meg);

					}
					driveAttribute.getListRootList().add(listroot);		
				}
			}
		}
		
		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
		return dataCommand;
	}
	
	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return ETHOAM
	 * @throws Exception 
	 */
	 
	public ETHOAM analysisCommandInfoToETHOAM(byte[] commands,String configXml) throws Exception{
		
		ETHOAM ethOAM = new ETHOAM();//获得ETHOAM对象
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);//获得以太网OAM配置(0DH).xml的元素
		String file = driveRoot_config.getDriveAttributeList().get(driveRoot_config.getDriveAttributeList().size()-1).getListRootList().get(0).getFile();//获得以太网OAM配置(0DH)_SUB.xml路径
		// 起始长度
		int start = NEhead + controlPanelHead + dataBlockHead;
		
		byte[] a = new byte[files];
		byte[] b = new byte[filecount_sub];
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		AnalysisCommandByDriveXml analysisCommandByDriveXml_SUB = new AnalysisCommandByDriveXml();
		System.arraycopy(commands, start, a, 0, a.length);		
		analysisCommandByDriveXml.setCommands(a);
		driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
		for (int i = 0; i <driveRoot_config.getDriveAttributeList().size(); i++) {//以太网OAM配置(0DH).xml的元素遍历
			
			
			DriveAttribute driveAttribute = new DriveAttribute();
			driveAttribute = driveRoot_config.getDriveAttributeList().get(i);
			if("环回帧超时时间".equals(driveAttribute.getDescription())){
				ethOAM.setTimeOut(Integer.parseInt(driveAttribute.getValue()));
			}
			if("MEL".equals(driveAttribute.getDescription())){ 
				ethOAM.setMel(Integer.parseInt(driveAttribute.getValue()));
			}
			if("条目总数".equals(driveAttribute.getDescription())){
				//条目数
				byte[] counts = {0x00,0x00,0x00,commands[start+files-1]};
				int filecount = CoderUtils.bytesToInt(counts);
				for (int j = 0; j < filecount; j++) {//按ETHOAMInfo条目数遍历
					
					ETHOAMInfo ethOAMInfo = new ETHOAMInfo();//创建ETHOAMInfo对象
					
					System.arraycopy(commands, start+files+j*filecount_sub, b, 0, b.length);					
					analysisCommandByDriveXml_SUB.setCommands(b);
					DriveRoot driveRoot_config_sub =analysisCommandByDriveXml_SUB.analysisDriveAttrbute(file) ;//将对应命令放入xml后解析得到以太网OAM配置(0DH)_SUB.xml的元素
					StringBuffer sb_targetMac = new StringBuffer();//用于拼接目的mac
					StringBuffer sb_megicc = new StringBuffer();//用于拼接megicc
					StringBuffer sb_megumc = new StringBuffer();//用于拼接megumc
				
					for (int k = 0; k < driveRoot_config_sub.getDriveAttributeList().size(); k++) {//按以太网OAM配置_SUB.xml元素个数遍历
						
						DriveAttribute driveAttribute_sub = new DriveAttribute();
						driveAttribute_sub = driveRoot_config_sub.getDriveAttributeList().get(k);
						
						//对ethOAMInfo进行赋值
						DriveAttributeTOETHOAMInfo(ethOAMInfo, driveAttribute_sub,sb_targetMac,sb_megicc,sb_megumc);
					}
					ethOAM.getETHOAMInfoList().add(ethOAMInfo);
				}				
			}
		}
		return ethOAM;	
	}
	/**
	 * 对象赋值给xml
	 * @param info
	 * @param driveAttribute
	 * @param strs_targetMac
	 * @param meg
	 */
	private void ETHOAMInfoTODriveAttribute(ETHOAMInfo info, DriveAttribute driveAttribute,String[] strs_targetMac,String[] meg) {
		//LSP ID
		if("LSP ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(info.getLspId()+"");
		}
		//PW ID
		if("PW ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(info.getPwId()+"");
		}
		//槽位号
		if("槽位号".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(info.getSlot()+"");
		}
		//端口号
		if("端口号".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(info.getPort()+"");
		}
		//源MEP ID
		if("源MEP ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(info.getSourceMEP()+"");
		}
		//对等MEP ID
		if("对等MEP ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(info.getEquityMEP()+"");
		}
		//(TST帧配置)TLV长度
		if("(TST帧配置)TLV长度".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(info.getTlvLength()+"");
		}
		//CCM帧配置
		if("CCM帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(CCMframe(info)+"");
		}
		//环回帧配置
		if("环回帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(Loopback(info)+"");
		}
		//TST帧配置
		if("TST帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(TST(info)+"");
		}
		//LM帧配置
		if("LM帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(LM(info)+"");
		}
		//DM帧配置
		if("DM帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(DM(info)+"");
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
		if(meg.length>1){
			//MEG ICC1
			if("MEG ICC1".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(meg[0]);
			}
			//MEG ICC2
			if("MEG ICC2".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(meg[1]);
			}
			//MEG ICC3
			if("MEG ICC3".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(meg[2]);
			}
			//MEG ICC4
			if("MEG ICC4".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(meg[3]);
			}
			//MEG ICC5
			if("MEG ICC5".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(meg[4]);
			}
			//MEG ICC6
			if("MEG ICC6".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(meg[5]);
			}
			//MEG UMC1
			if("MEG UMC1".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(meg[6]);
			}
			//MEG UMC2
			if("MEG UMC2".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(meg[7]);
			}
			//MEG UMC3
			if("MEG UMC3".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(meg[8]);
			}
			//MEG UMC4
			if("MEG UMC4".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(meg[9]);
			}
			//MEG UMC5
			if("MEG UMC5".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(meg[10]);
			}
			//MEG UMC6
			if("MEG UMC6".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(meg[11]);
			}
		}
	}
	/**
	 * CCM帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int CCMframe(ETHOAMInfo info){
		StringBuffer ccm_sb = new StringBuffer();
		ccm_sb.append(info.getCcmReserve());
		ccm_sb.append(info.getLckEnable());
		ccm_sb.append(info.getCcmCycle());
		ccm_sb.append(info.getCcmEnable());	
		char[] result = ccm_sb.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
	}
	/**
	 * Loopback帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int Loopback(ETHOAMInfo tmpoamimfo){
		StringBuffer loopback = new StringBuffer();
		loopback.append(tmpoamimfo.getLoopbackReserve());
		loopback.append(tmpoamimfo.getLoopbackTLV());
		loopback.append(tmpoamimfo.getLoopbackTest());
		loopback.append(tmpoamimfo.getLoopbackCycle());
		loopback.append(tmpoamimfo.getLoopbackEnable());		
		char[] result = loopback.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
	}
	/**
	 * TST帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int TST(ETHOAMInfo tmpoamimfo){
		StringBuffer tst = new StringBuffer();
		tst.append(tmpoamimfo.getTstReserve2());
		tst.append(tmpoamimfo.getTstTLV());
		tst.append(tmpoamimfo.getTstReserve1());
		tst.append(tmpoamimfo.getTstCycle());
		tst.append(tmpoamimfo.getTstEnable());
		char[] result = tst.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
		
	}
	/**
	 * LM帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int LM(ETHOAMInfo tmpoamimfo){
		StringBuffer lm = new StringBuffer();
		lm.append(tmpoamimfo.getLmReserve());
		lm.append(tmpoamimfo.getLmCycle());
		lm.append(tmpoamimfo.getLmEnable());
		char[] result = lm.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
		
	}
	/**
	 * DM帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int DM(ETHOAMInfo ethoamimfo){
		StringBuffer dm = new StringBuffer();
		dm.append(ethoamimfo.getDmReserve());
		dm.append(ethoamimfo.getDmCycle());
		dm.append(ethoamimfo.getDmEnable());		
		char[] result = dm.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
		
	}
	/**
	 * DriveAttribute复制到ETHOAMInfo
	 * @param info
	 * @param driveAttribute
	 * @param sb_targetMac
	 * @param sb_megicc
	 * @param sb_megumc
	 */
	private void DriveAttributeTOETHOAMInfo(ETHOAMInfo info, DriveAttribute driveAttribute, StringBuffer sb_targetMac, StringBuffer sb_megicc, StringBuffer sb_megumc) {
		//LSP ID
		if("LSP ID".equals(driveAttribute.getDescription())){
			info.setLspId(Integer.parseInt(driveAttribute.getValue()));
		}
		//PW ID
		if("PW ID".equals(driveAttribute.getDescription())){
			info.setPwId(Integer.parseInt(driveAttribute.getValue()));
		}
		//槽位号
		if("槽位号".equals(driveAttribute.getDescription())){
			info.setSlot(Integer.parseInt(driveAttribute.getValue()));
		}
		//端口号
		if("端口号".equals(driveAttribute.getDescription())){
			info.setPort(Integer.parseInt(driveAttribute.getValue()));
		}
		//源MEP ID
		if("源MEP ID".equals(driveAttribute.getDescription())){
			info.setSourceMEP(Integer.parseInt(driveAttribute.getValue()));
		}
		//对等MEP ID
		if("对等MEP ID".equals(driveAttribute.getDescription())){
			info.setEquityMEP(Integer.parseInt(driveAttribute.getValue()));
		}
		//(TST帧配置)TLV长度
		if("(TST帧配置)TLV长度".equals(driveAttribute.getDescription())){
			info.setTlvLength(Integer.parseInt(driveAttribute.getValue()));
		}
		//CCM帧配置
		if("CCM帧配置".equals(driveAttribute.getDescription())){
			TOccm(info, driveAttribute);
		}
		//环回帧配置
		if("环回帧配置".equals(driveAttribute.getDescription())){
			TOloopback(info, driveAttribute);
		}
		//TST帧配置
		if("TST帧配置".equals(driveAttribute.getDescription())){
			TOTst(info, driveAttribute);
		}
		//LM帧配置
		if("LM帧配置".equals(driveAttribute.getDescription())){
			TOlm(info, driveAttribute);
		}
		//DM帧配置
		if("DM帧配置".equals(driveAttribute.getDescription())){
			TOdm(info, driveAttribute);
		}
		// 目的MAC地址1
		if ("目的MAC地址1".equals(driveAttribute.getDescription())) {
			sb_targetMac.append(driveAttribute.getValue() + "-");
		}

		// 目的MAC地址2
		if ("目的MAC地址2".equals(driveAttribute.getDescription())) {
			sb_targetMac.append(driveAttribute.getValue() + "-");
		}

		// 目的MAC地址3
		if ("目的MAC地址3".equals(driveAttribute.getDescription())) {
			sb_targetMac.append(driveAttribute.getValue() + "-");
		}

		// 目的MAC地址4
		if ("目的MAC地址4".equals(driveAttribute.getDescription())) {
			sb_targetMac.append(driveAttribute.getValue() + "-");
		}

		// 目的MAC地址5
		if ("目的MAC地址5".equals(driveAttribute.getDescription())) {
			sb_targetMac.append(driveAttribute.getValue() + "-");
		}

		// 目的MAC地址6
		if ("目的MAC地址6".equals(driveAttribute.getDescription())) {
			sb_targetMac.append(driveAttribute.getValue());
			info.setTargetMac(sb_targetMac.toString());
		}
		// MEG ICC1
		if ("MEG ICC1".equals(driveAttribute.getDescription())) {
			sb_megicc.append(driveAttribute.getValue() + ",");
		}

		// MEG ICC2
		if ("MEG ICC2".equals(driveAttribute.getDescription())) {
			sb_megicc.append(driveAttribute.getValue() + ",");
		}

		// MEG ICC3
		if ("MEG ICC3".equals(driveAttribute.getDescription())) {
			sb_megicc.append(driveAttribute.getValue() + ",");
		}

		// MEG ICC4
		if ("MEG ICC4".equals(driveAttribute.getDescription())) {
			sb_megicc.append(driveAttribute.getValue() + ",");
		}

		// MEG ICC5
		if ("MEG ICC5".equals(driveAttribute.getDescription())) {
			sb_megicc.append(driveAttribute.getValue() + ",");
		}

		// MEG ICC6
		if ("MEG ICC6".equals(driveAttribute.getDescription())) {
			sb_megicc.append(driveAttribute.getValue());
			info.setMegIcc(sb_megicc.toString());
		}
		// MEG UMC1
		if ("MEG UMC1".equals(driveAttribute.getDescription())) {
			sb_megumc.append(driveAttribute.getValue() + ",");
		}

		// MEG UMC2
		if ("MEG UMC2".equals(driveAttribute.getDescription())) {
			sb_megumc.append(driveAttribute.getValue() + ",");
		}

		// MEG UMC3
		if ("MEG UMC3".equals(driveAttribute.getDescription())) {
			sb_megumc.append(driveAttribute.getValue() + ",");
		}

		// MEG UMC4
		if ("MEG UMC4".equals(driveAttribute.getDescription())) {
			sb_megumc.append(driveAttribute.getValue() + ",");
		}

		// MEG UMC5
		if ("MEG UMC5".equals(driveAttribute.getDescription())) {
			sb_megumc.append(driveAttribute.getValue() + ",");
		}

		// MEG UMC6
		if ("MEG UMC6".equals(driveAttribute.getDescription())) {
			sb_megumc.append(driveAttribute.getValue());
			info.setMegUmc(sb_megumc.toString());
		}
		
	}
	/**
	 * 命令转ccm
	 * @param info
	 * @param driveAttribute
	 */
	private void TOccm(ETHOAMInfo info,DriveAttribute driveAttribute){
		
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		info.setCcmReserve(str.substring(0,3));
		info.setLckEnable(str.substring(3,4));
		info.setCcmEnable(str.substring(4,7));
		info.setCcmCycle(str.substring(7));
	}
	/**
	 * 命令转loopback
	 * @param info
	 * @param driveAttribute
	 */
	private void TOloopback(ETHOAMInfo thmOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		thmOAMInfo.setLoopbackReserve(str.substring(0,1));
		thmOAMInfo.setLoopbackTLV(str.substring(1,3));
		thmOAMInfo.setLoopbackTest(str.substring(3,4));
		thmOAMInfo.setLoopbackCycle(str.substring(4,7));
		thmOAMInfo.setLoopbackEnable(str.substring(7));		
	}
	/**
	 * 命令转tst
	 * @param info
	 * @param driveAttribute
	 */
	private void TOTst(ETHOAMInfo thmOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		thmOAMInfo.setTstReserve2(str.substring(0,1));
		thmOAMInfo.setTstTLV(str.substring(1,3));
		thmOAMInfo.setTstReserve1(str.substring(3, 4));
		thmOAMInfo.setTstCycle(str.substring(4,7));
		thmOAMInfo.setTstEnable(str.substring(7));
	}
	/**
	 * 命令转lm
	 * @param info
	 * @param driveAttribute
	 */
	private void TOlm(ETHOAMInfo thmOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		thmOAMInfo.setLmReserve(str.substring(0,6));
		thmOAMInfo.setLmCycle(str.substring(6, 7));
		thmOAMInfo.setLmEnable(str.substring(7));
	}
	/**
	 * 命令转dm
	 * @param info
	 * @param driveAttribute
	 */
	private void TOdm(ETHOAMInfo thmOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		thmOAMInfo.setDmReserve(str.substring(0,6));
		thmOAMInfo.setDmCycle(str.substring(6,7));
		thmOAMInfo.setDmEnable(str.substring(7));
	}
	/**
	 * 将字符串补足8位
	 * @param str
	 */
	private String eightBinary(String str){
		String s = null;
		StringBuffer sb = new StringBuffer();
		if(str.length()<8){			
			for (int i = str.length(); i < 8; i++) {
				sb.append("0");
			}		
		}
		sb.append(str);
		s = sb.toString();
		return s;
	}
}
