package com.nms.drive.analysis.datablock;


import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.TMPOAMBugControlInfoObject;
import com.nms.drive.service.bean.TMPOAMBugControlObject;
import com.nms.drive.service.impl.CoderUtils;

/**
 * 
 * @author 彭冲
 * TMP OAM故障管理配置 解析
 *
 */
public class AnalysisTMPOAMBugControlTable extends AnalysisBase {
	
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int filecount_sub = 60;// TMP OAM故障管理配置(0FH)_SUB.xml的长度
	private int files = 5;// TMP OAM故障管理配置(0FH).xml的长度
	
	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return commands
	 */
	public byte[] analysisTMPOAMBugControlToCommand(TMPOAMBugControlObject tmpoamim,String configXml) throws Exception {
		
		// TMP OAM故障管理配置(0FH).xml
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		int count = driveRoot_config.getDriveAttributeList().size();//获得TMP OAM故障管理配置(0FH).xml元素个数
		for (int i = 0; i < count; i++) {
			
			DriveAttribute driveAttribute = new DriveAttribute();
			driveAttribute = driveRoot_config.getDriveAttributeList().get(i);
			
			if("VP OAM条目数".equals(driveAttribute.getDescription())){
				
				int filecount = tmpoamim.getTmpOamBugControlObjectlist().size();// 获得条目数
				driveAttribute.setValue(filecount+"");
				String file = driveAttribute.getListRootList().get(0).getFile();
				driveAttribute.getListRootList().clear();// 清除本身自带的一个ListRoot			
				
				for (int j = 0; j < filecount;j++) {// 按条目数循环
					
					DriveRoot driveRoot_config_1 = super.LoadDriveXml(file);
					ListRoot listroot = new ListRoot();
					listroot.setDriveAttributeList(driveRoot_config_1.getDriveAttributeList());	
					StringBuffer  sb = new StringBuffer();
					sb.append(tmpoamim.getTmpOamBugControlObjectlist().get(j).getMegIcc()+",");
					sb.append(tmpoamim.getTmpOamBugControlObjectlist().get(j).getMegUmc());
					String[] meg = sb.toString().split(",");
					
					// 将 TMPOAMBugControlInfo 对象信息 赋值到 driveRoot_config 对象中
					for (int k = 0; k < driveRoot_config_1.getDriveAttributeList().size(); k++) {// 按xml元素循环
						
						DriveAttribute driveAttribute_sub = driveRoot_config_1.getDriveAttributeList().get(k);						
						//赋值
						TMPOAMBugControlInfoTODriveAttribute(tmpoamim.getTmpOamBugControlObjectlist().get(j), driveAttribute_sub,meg);

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
	 
	public TMPOAMBugControlObject analysisCommandInfoToTMPOAMBugControlObject(byte[] commands,String configXml) throws Exception{
		
		TMPOAMBugControlObject tmpoamim = new TMPOAMBugControlObject();//获得TMPOAMBugControlObject对象
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);//获得TMP OAM故障管理配置(0FH).xml的元素
		
		// 起始长度
		int start = NEhead + controlPanelHead + dataBlockHead;
		
		byte[] a = new byte[files];
		byte[] b = new byte[filecount_sub];
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		AnalysisCommandByDriveXml analysisCommandByDriveXml_SUB = new AnalysisCommandByDriveXml();
		
		for (int i = 0; i <driveRoot_config.getDriveAttributeList().size(); i++) {//以TMP OAM故障管理配置(0FH).xml的元素遍历
			
			System.arraycopy(commands, start, a, 0, a.length);		
			analysisCommandByDriveXml.setCommands(a);
			DriveAttribute driveAttribute = new DriveAttribute();
			driveAttribute = driveRoot_config.getDriveAttributeList().get(i);

			if("VP OAM条目数".equals(driveAttribute.getDescription())){
				//条目数
				byte[] counts = {0x00,0x00,0x00,commands[start+files-1]};
				int filecount = CoderUtils.bytesToInt(counts);
				for (int j = 0; j < filecount; j++) {//按TMPOAMBugControlInfoObject条目数遍历
					
					TMPOAMBugControlInfoObject tpmOAMInfo = new TMPOAMBugControlInfoObject();//创建ETHOAMInfo对象
					String file = driveAttribute.getListRootList().get(0).getFile();//获TMP OAM故障管理配置(0FH)_SUB.xml路径
					System.arraycopy(commands, start+files+j*filecount_sub, b, 0, b.length);					
					analysisCommandByDriveXml_SUB.setCommands(b);
					DriveRoot driveRoot_config_sub =analysisCommandByDriveXml_SUB.analysisDriveAttrbute(file) ;//将对应命令放入xml后解析得到TMP OAM故障管理配置(0FH)_SUB.xml的元素
					StringBuffer sb_megicc = new StringBuffer();//用于拼接megicc
					StringBuffer sb_megumc = new StringBuffer();//用于拼接megumc
				
					for (int k = 0; k < driveRoot_config_sub.getDriveAttributeList().size(); k++) {//按TMP OAM故障管理配置(0FH)_SUB.xml元素个数遍历
						
						DriveAttribute driveAttribute_sub = new DriveAttribute();
						driveAttribute_sub = driveRoot_config_sub.getDriveAttributeList().get(k);
						
						//对tpmOAMInfo进行赋值
						DriveAttributeTOTMPOAMInfo(tpmOAMInfo, driveAttribute_sub,sb_megicc,sb_megumc);
					}
					tmpoamim.getTmpOamBugControlObjectlist().add(tpmOAMInfo);
				}				
			}
		}
		return tmpoamim;		
	}
	/**
	 * 对象赋值给xml
	 * @param info
	 * @param driveAttribute
	 * @param strs_targetMac
	 * @param meg
	 */
	private void TMPOAMBugControlInfoTODriveAttribute(TMPOAMBugControlInfoObject tmpoamimfo, DriveAttribute driveAttribute,String[] meg) {
		//LSP ID
		if("LSP  ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmpoamimfo.getLspId()+"");
			
		}
		//条目ID
		if("条目ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmpoamimfo.getSubclausesId()+"");
		}
		//TC-MEL
		if("TC-MEL".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tcmel(tmpoamimfo)+"");
		}
		//源MEP ID
		if("源MEP ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmpoamimfo.getSourceMEP()+"");
		}
		//对等MEP ID
		if("对等MEP ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmpoamimfo.getEquityMEP()+"");
		}
		//(TST帧配置)TLV长度
		if("(TST帧配置)TLV长度".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmpoamimfo.getTst_tlvLength()+"");
		}
		//数据TLV测试内容
		if("数据TLV测试内容".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmpoamimfo.getLoopback_tlvtest()+"");
		}
		//(环回帧配置)TLV长度
		if("(环回帧配置)TLV长度".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmpoamimfo.getLoopback_tlvLength()+"");
		}
		//LCK帧发送使能
		if("LCK帧发送使能".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmpoamimfo.getLckEnable()+"");
		}
		//环回帧配置
		if("环回帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(Loopback(tmpoamimfo)+"");
		}
		//TST帧配置
		if("TST帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(TST(tmpoamimfo)+"");
		}
		//LM帧配置
		if("LM帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(LM(tmpoamimfo)+"");
		}
		//DM帧配置
		if("DM帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(DM(tmpoamimfo)+"");
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
		//lt 使能
		if("lt 使能".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmpoamimfo.getLtEnable()+"");
		}
		//lt exp
		if("lt exp".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmpoamimfo.getLtEXP()+"");
		}
		//lt ttl
		if("lt ttl".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmpoamimfo.getLtTTL()+"");
		}
		//LB TTL
		if("LB TTL".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmpoamimfo.getLbTTL()+"");
		}
	}
	
	/**
	 * Loopback帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int tcmel(TMPOAMBugControlInfoObject tmpoamimfo){
		StringBuffer loopback = new StringBuffer();
		loopback.append(tmpoamimfo.getMel());
		loopback.append("00");
		loopback.append(tmpoamimfo.getTc());		
		char[] result = loopback.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
	}
	
	/**
	 * Loopback帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int Loopback(TMPOAMBugControlInfoObject tmpoamimfo){
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
	private int TST(TMPOAMBugControlInfoObject tmpoamimfo){
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
	private int LM(TMPOAMBugControlInfoObject tmpoamimfo){
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
	private int DM(TMPOAMBugControlInfoObject tmpoamimfo){
		StringBuffer dm = new StringBuffer();
		dm.append(tmpoamimfo.getDmReserve());
		dm.append(tmpoamimfo.getDmCycle());
		dm.append(tmpoamimfo.getDmEnable());		
		char[] result = dm.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
		
	}
	/**
	 * DriveAttribute复制到TMPOAMInfo
	 * @param info
	 * @param driveAttribute
	 * @param sb_targetMac
	 * @param sb_megicc
	 * @param sb_megumc
	 */
	private void DriveAttributeTOTMPOAMInfo(TMPOAMBugControlInfoObject tmpOAMInfo, DriveAttribute driveAttribute, StringBuffer sb_megicc, StringBuffer sb_megumc) {
		
		//LSP ID
		if("LSP  ID".equals(driveAttribute.getDescription())){
			tmpOAMInfo.setLspId(Integer.parseInt(driveAttribute.getValue()));
		}
		//条目ID
		if("条目ID".equals(driveAttribute.getDescription())){
			tmpOAMInfo.setSubclausesId(Integer.parseInt(driveAttribute.getValue()));
		}
		//(TST帧配置)TLV长度
		if("(TST帧配置)TLV长度".equals(driveAttribute.getDescription())){
			tmpOAMInfo.setTst_tlvLength(Integer.parseInt(driveAttribute.getValue()));
		}
		//数据TLV测试内容
		if("数据TLV测试内容".equals(driveAttribute.getDescription())){
			tmpOAMInfo.setLoopback_tlvtest(Integer.parseInt(driveAttribute.getValue()));
		}
		//(环回帧配置)TLV长度
		if("(环回帧配置)TLV长度".equals(driveAttribute.getDescription())){
			tmpOAMInfo.setLoopback_tlvLength(Integer.parseInt(driveAttribute.getValue()));
		}
		//LCK帧发送使能
		if("LCK帧发送使能".equals(driveAttribute.getDescription())){
			tmpOAMInfo.setLckEnable(Integer.parseInt(driveAttribute.getValue()));
		}
		//源MEP ID
		if("源MEP ID".equals(driveAttribute.getDescription())){
			tmpOAMInfo.setSourceMEP(Integer.parseInt(driveAttribute.getValue()));
		}
		//对等MEP ID
		if("对等MEP ID".equals(driveAttribute.getDescription())){
			tmpOAMInfo.setEquityMEP(Integer.parseInt(driveAttribute.getValue()));
		}
		//环回帧配置
		if("环回帧配置".equals(driveAttribute.getDescription())){
			TOloopback(tmpOAMInfo, driveAttribute);
		}
		//TST帧配置
		if("TST帧配置".equals(driveAttribute.getDescription())){
			TOTst(tmpOAMInfo, driveAttribute);
		}
		//LM帧配置
		if("LM帧配置".equals(driveAttribute.getDescription())){
			TOlm(tmpOAMInfo, driveAttribute);
		}
		//DM帧配置
		if("DM帧配置".equals(driveAttribute.getDescription())){
			TOdm(tmpOAMInfo, driveAttribute);
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
			tmpOAMInfo.setMegIcc(sb_megicc.toString());
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
			tmpOAMInfo.setMegUmc(sb_megumc.toString());
		}
		//lt 使能
		if("lt 使能".equals(driveAttribute.getDescription())){
			tmpOAMInfo.setLtEnable(Integer.parseInt(driveAttribute.getValue()));
		}
		//lt exp
		if("lt exp".equals(driveAttribute.getDescription())){
			tmpOAMInfo.setLtEXP(Integer.parseInt(driveAttribute.getValue()));
		}
		//lt ttl
		if("lt ttl".equals(driveAttribute.getDescription())){
			tmpOAMInfo.setLtTTL(Integer.parseInt(driveAttribute.getValue()));
		}
		//lb ttl
		if("LB TTL".equals(driveAttribute.getDescription())){
			tmpOAMInfo.setLbTTL(Integer.parseInt(driveAttribute.getValue()));
		}
	}
	/**
	 * 命令转loopback
	 * @param info
	 * @param driveAttribute
	 */
	private void TOloopback(TMPOAMBugControlInfoObject tmpOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		tmpOAMInfo.setLoopbackReserve(str.substring(0,1));
		tmpOAMInfo.setLoopbackTLV(str.substring(1,3));
		tmpOAMInfo.setLoopbackTest(str.substring(3,4));
		tmpOAMInfo.setLoopbackCycle(str.substring(4,7));
		tmpOAMInfo.setLoopbackEnable(str.substring(7));		
	}
	/**
	 * 命令转tst
	 * @param info
	 * @param driveAttribute
	 */
	private void TOTst(TMPOAMBugControlInfoObject tmpOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		tmpOAMInfo.setTstReserve2(str.substring(0,1));
		tmpOAMInfo.setTstTLV(str.substring(1,3));
		tmpOAMInfo.setTstReserve1(str.substring(3, 4));
		tmpOAMInfo.setTstCycle(str.substring(4,7));
		tmpOAMInfo.setTstEnable(str.substring(7));
	}
	/**
	 * 命令转lm
	 * @param info
	 * @param driveAttribute
	 */
	private void TOlm(TMPOAMBugControlInfoObject tmpOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		tmpOAMInfo.setLmReserve(str.substring(0,6));
		tmpOAMInfo.setLmCycle(str.substring(6, 7));
		tmpOAMInfo.setLmEnable(str.substring(7));
	}
	/**
	 * 命令转dm
	 * @param info
	 * @param driveAttribute
	 */
	private void TOdm(TMPOAMBugControlInfoObject tmpOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		tmpOAMInfo.setDmReserve(str.substring(0,6));
		tmpOAMInfo.setDmCycle(str.substring(6,7));
		tmpOAMInfo.setDmEnable(str.substring(7));
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
