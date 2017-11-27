package com.nms.drive.analysis.datablock;


import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.TMCOAMBugControlInfoObject;
import com.nms.drive.service.bean.TMCOAMBugControlObject;
import com.nms.drive.service.impl.CoderUtils;

/**
 * 
 * TMC OAM故障管理配置 解析
 * @author 彭冲
 *
 */
public class AnalysisTMCOAMBugControlTable extends AnalysisBase {
	
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int filecount_sub = 60;// TMC OAM故障管理配置(0FH)_SUB.xml的长度
	private int files = 5;// TMC OAM故障管理配置(0FH).xml的长度
	
	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return commands
	 */
	public byte[] analysisTMCOAMBugControlToCommand(TMCOAMBugControlObject tmcoamBugControlObject,String configXml) throws Exception {
		
		// TMC OAM故障管理配置(0FH).xml
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		int count = driveRoot_config.getDriveAttributeList().size();//获得TMC OAM故障管理配置(0FH).xml元素个数
		for (int i = 0; i < count; i++) {
			
			DriveAttribute driveAttribute = new DriveAttribute();
			driveAttribute = driveRoot_config.getDriveAttributeList().get(i);
			
			if("VC OAM条目数".equals(driveAttribute.getDescription())){
				
				int filecount = tmcoamBugControlObject.getTmcOamBugControlObjectlist().size();// 获得条目数
				driveAttribute.setValue(filecount+"");
				String file = driveAttribute.getListRootList().get(0).getFile();
				driveAttribute.getListRootList().clear();// 清除本身自带的一个ListRoot			
				
				for (int j = 0; j < filecount;j++) {// 按条目数循环
					
					DriveRoot driveRoot_config_1 = super.LoadDriveXml(file);
					ListRoot listroot = new ListRoot();
					listroot.setDriveAttributeList(driveRoot_config_1.getDriveAttributeList());	
					StringBuffer  sb = new StringBuffer();
					sb.append(tmcoamBugControlObject.getTmcOamBugControlObjectlist().get(j).getMegIcc()+",");
					sb.append(tmcoamBugControlObject.getTmcOamBugControlObjectlist().get(j).getMegUmc());
					String[] meg = sb.toString().split(",");
					
					// 将 TMCOAMBugControlInfo 对象信息 赋值到 driveRoot_config 对象中
					for (int k = 0; k < driveRoot_config_1.getDriveAttributeList().size(); k++) {// 按xml元素循环
						
						DriveAttribute driveAttribute_sub = driveRoot_config_1.getDriveAttributeList().get(k);						
						//赋值
						TMCOAMBugControlInfoTODriveAttribute(tmcoamBugControlObject.getTmcOamBugControlObjectlist().get(j), driveAttribute_sub,meg);

					}
					driveAttribute.getListRootList().add(listroot);		
				}
			}
		}
		
		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
//		CoderUtils.print16String(dataCommand);
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
	 
	public TMCOAMBugControlObject analysisCommandInfoToTMCOAMBugControl(byte[] commands,String configXml) throws Exception{
		
		TMCOAMBugControlObject tmcoamBugControlObject = new TMCOAMBugControlObject();//获得TMCOAMBugControlObject对象
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);//获得TMC OAM故障管理配置(0FH).xml的元素
		
		// 起始长度
		int start = NEhead + controlPanelHead + dataBlockHead;
		
		byte[] a = new byte[files];
		byte[] b = new byte[filecount_sub];
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		AnalysisCommandByDriveXml analysisCommandByDriveXml_SUB = new AnalysisCommandByDriveXml();
		
		for (int i = 0; i <driveRoot_config.getDriveAttributeList().size(); i++) {//以TMC OAM故障管理配置(0FH).xml的元素遍历
			
			System.arraycopy(commands, start, a, 0, a.length);		
			analysisCommandByDriveXml.setCommands(a);
			DriveAttribute driveAttribute = new DriveAttribute();
			driveAttribute = driveRoot_config.getDriveAttributeList().get(i);
			if("VC OAM条目数".equals(driveAttribute.getDescription())){
				//条目数
				byte[] counts = {0x00,0x00,0x00,commands[start+files-1]};
				int filecount = CoderUtils.bytesToInt(counts);
				for (int j = 0; j < filecount; j++) {//按TMCOAMBugControlInfoObject条目数遍历
					
					TMCOAMBugControlInfoObject tmcOAMInfo = new TMCOAMBugControlInfoObject();//创建ETHOAMInfo对象
					String file = driveAttribute.getListRootList().get(0).getFile();//获TMC OAM故障管理配置(0FH)_SUB.xml路径
					System.arraycopy(commands, start+files+j*filecount_sub, b, 0, b.length);					
					analysisCommandByDriveXml_SUB.setCommands(b);
					DriveRoot driveRoot_config_sub =analysisCommandByDriveXml_SUB.analysisDriveAttrbute(file) ;//将对应命令放入xml后解析得到TMC OAM故障管理配置(0FH)_SUB.xml的元素
					StringBuffer sb_megicc = new StringBuffer();//用于拼接megicc
					StringBuffer sb_megumc = new StringBuffer();//用于拼接megumc
				
					for (int k = 0; k < driveRoot_config_sub.getDriveAttributeList().size(); k++) {//按TMC OAM故障管理配置(0FH)_SUB.xml元素个数遍历
						
						DriveAttribute driveAttribute_sub = new DriveAttribute();
						driveAttribute_sub = driveRoot_config_sub.getDriveAttributeList().get(k);
						
						//对tpmOAMInfo进行赋值
						DriveAttributeTOTMCOAMBugControlInfo(tmcOAMInfo, driveAttribute_sub,sb_megicc,sb_megumc);
					}
					tmcoamBugControlObject.getTmcOamBugControlObjectlist().add(tmcOAMInfo);
				}				
			}
		}
		return tmcoamBugControlObject;		
	}
	/**
	 * 对象赋值给xml
	 * @param info
	 * @param driveAttribute
	 * @param strs_targetMac
	 * @param meg
	 */
	private void TMCOAMBugControlInfoTODriveAttribute(TMCOAMBugControlInfoObject tmcoaminfo, DriveAttribute driveAttribute,String[] meg) {
		
		//PW ID
		if("PW ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmcoaminfo.getPwid()+"");		
		}
		//入PW标签
		if("入PW标签".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmcoaminfo.getInPwLable()+"");
		}
		//出PW标签
		if("出PW标签".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmcoaminfo.getOutPwLable()+"");
		}
		//MEL
		if("MEL".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmcoaminfo.getMel()+"");
		}
		//LSP ID
		if("LSP ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmcoaminfo.getLspId()+"");		
		}
		//条目ID
		if("条目ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmcoaminfo.getSubclausesId()+"");
		}
		//源MEP ID
		if("源MEP ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmcoaminfo.getSourceMEP()+"");
		}
		//对等MEP ID
		if("对等MEP ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmcoaminfo.getEquityMEP()+"");
		}
		//(TST帧配置)TLV长度
		if("(TST帧配置)TLV长度".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmcoaminfo.getTst_tlvLength()+"");
		}
		//数据TLV测试内容
		if("数据TLV测试内容".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmcoaminfo.getLoopback_tlvtest()+"");
		}
		//(环回帧配置)TLV长度
		if("(环回帧配置)TLV长度".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmcoaminfo.getLoopback_tlvLength()+"");
		}
		//LCK帧发送使能
		if("LCK帧发送使能".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmcoaminfo.getLckEnable()+"");
		}
		//环回帧配置
		if("环回帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(Loopback(tmcoaminfo)+"");
		}
		//TST帧配置
		if("TST帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(TST(tmcoaminfo)+"");
		}
		//LM帧配置
		if("LM帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(LM(tmcoaminfo)+"");
		}
		//DM帧配置
		if("DM帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(DM(tmcoaminfo)+"");
		}
		//(VC条目)字节16
		if("(VC条目)字节16".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(TC(tmcoaminfo)+"");
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
		if("LB TTL".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmcoaminfo.getLbTTL()+"");
		}
		if("dm".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmcoaminfo.getDmlength()+"");
		}
		
	}
	
	/**
	 * Loopback帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int Loopback(TMCOAMBugControlInfoObject tmcoaminfo){
		StringBuffer loopback = new StringBuffer();
		loopback.append(tmcoaminfo.getLoopbackReserve());
		loopback.append(tmcoaminfo.getLoopbackTLV());
		loopback.append(tmcoaminfo.getLoopbackTest());
		loopback.append(tmcoaminfo.getLoopbackCycle());
		loopback.append(tmcoaminfo.getLoopbackEnable());
		char[] result = loopback.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
	}
	/**
	 * TST帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int TST(TMCOAMBugControlInfoObject tmcoaminfo){
		StringBuffer tst = new StringBuffer();
		tst.append(tmcoaminfo.getTstReserve2());
		tst.append(tmcoaminfo.getTstTLV());
		tst.append(tmcoaminfo.getTstReserve1());
		tst.append(tmcoaminfo.getTstCycle());
		tst.append(tmcoaminfo.getTstEnable());
		char[] result = tst.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
		
	}
	/**
	 * LM帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int LM(TMCOAMBugControlInfoObject tmcoaminfo){
		StringBuffer lm = new StringBuffer();	
		lm.append(tmcoaminfo.getLmReserve());
		lm.append(tmcoaminfo.getLmCycle());
		lm.append(tmcoaminfo.getLmEnable());
		char[] result = lm.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
		
	}
	/**
	 * DM帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int DM(TMCOAMBugControlInfoObject tmcoaminfo){
		StringBuffer dm = new StringBuffer();	
		dm.append(tmcoaminfo.getDmReserve());
		dm.append(tmcoaminfo.getDmCycle());
		dm.append(tmcoaminfo.getDmEnable());	
		char[] result = dm.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
		
	}
	/**
	 * TC帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int TC(TMCOAMBugControlInfoObject tmcoaminfo){
		StringBuffer dm = new StringBuffer();	
		dm.append(tmcoaminfo.getResrveTC());
		dm.append(tmcoaminfo.getPwTC());
		dm.append(tmcoaminfo.getLspTC());	
		char[] result = dm.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
		
	}
	/**
	 * DriveAttribute复制到TMCOAMBugControlInfo
	 * @param info
	 * @param driveAttribute
	 * @param sb_targetMac
	 * @param sb_megicc
	 * @param sb_megumc
	 */
	private void DriveAttributeTOTMCOAMBugControlInfo(TMCOAMBugControlInfoObject tmcOAMInfo, DriveAttribute driveAttribute, StringBuffer sb_megicc, StringBuffer sb_megumc) {
		
		//PW ID
		if("PW ID".equals(driveAttribute.getDescription())){
			tmcOAMInfo.setPwid(Integer.parseInt(driveAttribute.getValue()));	
		}
		//入PW标签
		if("入PW标签".equals(driveAttribute.getDescription())){
			tmcOAMInfo.setInPwLable(Integer.parseInt(driveAttribute.getValue()));
		}
		//出PW标签
		if("出PW标签".equals(driveAttribute.getDescription())){
			tmcOAMInfo.setOutPwLable(Integer.parseInt(driveAttribute.getValue()));
		}
		//MEL
		if("MEL".equals(driveAttribute.getDescription())){
			tmcOAMInfo.setMel(Integer.parseInt(driveAttribute.getValue()));
		}
		//LSP ID
		if("LSP ID".equals(driveAttribute.getDescription())){
			tmcOAMInfo.setLspId(Integer.parseInt(driveAttribute.getValue()));
		}
		//条目ID
		if("条目ID".equals(driveAttribute.getDescription())){
			tmcOAMInfo.setSubclausesId(Integer.parseInt(driveAttribute.getValue()));
		}
		//(TST帧配置)TLV长度
		if("(TST帧配置)TLV长度".equals(driveAttribute.getDescription())){
			tmcOAMInfo.setTst_tlvLength(Integer.parseInt(driveAttribute.getValue()));
		}
		//数据TLV测试内容
		if("数据TLV测试内容".equals(driveAttribute.getDescription())){
			tmcOAMInfo.setLoopback_tlvtest(Integer.parseInt(driveAttribute.getValue()));
		}
		//(环回帧配置)TLV长度
		if("(环回帧配置)TLV长度".equals(driveAttribute.getDescription())){
			tmcOAMInfo.setLoopback_tlvLength(Integer.parseInt(driveAttribute.getValue()));
		}
		//LCK帧发送使能
		if("LCK帧发送使能".equals(driveAttribute.getDescription())){
			tmcOAMInfo.setLckEnable(Integer.parseInt(driveAttribute.getValue()));
		}
		//源MEP ID
		if("源MEP ID".equals(driveAttribute.getDescription())){
			tmcOAMInfo.setSourceMEP(Integer.parseInt(driveAttribute.getValue()));
		}
		//对等MEP ID
		if("对等MEP ID".equals(driveAttribute.getDescription())){
			tmcOAMInfo.setEquityMEP(Integer.parseInt(driveAttribute.getValue()));
		}
		//环回帧配置
		if("环回帧配置".equals(driveAttribute.getDescription())){
			TOloopback(tmcOAMInfo, driveAttribute);
		}
		//TST帧配置
		if("TST帧配置".equals(driveAttribute.getDescription())){
			TOTst(tmcOAMInfo, driveAttribute);
		}
		//LM帧配置
		if("LM帧配置".equals(driveAttribute.getDescription())){
			TOlm(tmcOAMInfo, driveAttribute);
		}
		//DM帧配置
		if("DM帧配置".equals(driveAttribute.getDescription())){
			TOdm(tmcOAMInfo, driveAttribute);
		}
		//(VC条目)字节16
		if("(VC条目)字节16".equals(driveAttribute.getDescription())){
			TOtc(tmcOAMInfo, driveAttribute);
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
			tmcOAMInfo.setMegIcc(sb_megicc.toString());
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
			tmcOAMInfo.setMegUmc(sb_megumc.toString());
		}
		if("LB TTL".equals(driveAttribute.getDescription())){
			tmcOAMInfo.setLbTTL(Integer.parseInt(driveAttribute.getValue()));
		}
		
		if("dm".equals(driveAttribute.getDescription())){
			tmcOAMInfo.setDmlength(Integer.parseInt(driveAttribute.getValue()));
		}
	}
	/**
	 * 命令转loopback
	 * @param info
	 * @param driveAttribute
	 */
	private void TOloopback(TMCOAMBugControlInfoObject tpmOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		tpmOAMInfo.setLoopbackReserve(str.substring(0,1));
		tpmOAMInfo.setLoopbackTLV(str.substring(1,3));
		tpmOAMInfo.setLoopbackTest(str.substring(3,4));
		tpmOAMInfo.setLoopbackCycle(str.substring(4,7));
		tpmOAMInfo.setLoopbackEnable(str.substring(7));		
	}
	/**
	 * 命令转tst
	 * @param info
	 * @param driveAttribute
	 */
	private void TOTst(TMCOAMBugControlInfoObject tpmOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		tpmOAMInfo.setTstReserve2(str.substring(0,1));
		tpmOAMInfo.setTstTLV(str.substring(1,3));
		tpmOAMInfo.setTstReserve1(str.substring(3, 4));
		tpmOAMInfo.setTstCycle(str.substring(4,7));
		tpmOAMInfo.setTstEnable(str.substring(7));
	}
	/**
	 * 命令转lm
	 * @param info
	 * @param driveAttribute
	 */
	private void TOlm(TMCOAMBugControlInfoObject tpmOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		tpmOAMInfo.setLmReserve(str.substring(0,6));
		tpmOAMInfo.setLmCycle(str.substring(6, 7));
		tpmOAMInfo.setLmEnable(str.substring(7));
	}
	/**
	 * 命令转dm
	 * @param info
	 * @param driveAttribute
	 */
	private void TOdm(TMCOAMBugControlInfoObject tpmOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		tpmOAMInfo.setDmReserve(str.substring(0,6));
		tpmOAMInfo.setDmCycle(str.substring(6,7));
		tpmOAMInfo.setDmEnable(str.substring(7));
	}
	/**
	 * 命令转tc
	 * @param info
	 * @param driveAttribute
	 */
	private void TOtc(TMCOAMBugControlInfoObject tmcOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		tmcOAMInfo.setResrveTC(str.substring(0,2));
		tmcOAMInfo.setPwTC(str.substring(2,5));
		tmcOAMInfo.setLspTC(str.substring(5));
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
