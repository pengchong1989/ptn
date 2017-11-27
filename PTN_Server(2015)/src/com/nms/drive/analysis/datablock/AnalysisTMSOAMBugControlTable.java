package com.nms.drive.analysis.datablock;


import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.TMSOAMBugControlInfoObject;
import com.nms.drive.service.bean.TMSOAMBugControlObject;
import com.nms.drive.service.impl.CoderUtils;

/**
 * 
 * @author 彭冲
 * TMP OAM故障管理配置 解析
 *
 */
public class AnalysisTMSOAMBugControlTable extends AnalysisBase {
	
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int filecount_sub = 45;// TMS OAM故障管理配置(0FH)_SUB.xml的长度
	private int files = 5;// TMS OAM故障管理配置(0FH).xml的长度
	
	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return commands
	 */
	public byte[] analysisTMSOAMBugControlToCommand(TMSOAMBugControlObject tmsoamim,String configXml) throws Exception {
		
		// TMS OAM故障管理配置(0FH).xml
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		int count = driveRoot_config.getDriveAttributeList().size();//获得TMS OAM故障管理配置(0FH).xml元素个数
		for (int i = 0; i < count; i++) {
			
			DriveAttribute driveAttribute = new DriveAttribute(); 
			driveAttribute = driveRoot_config.getDriveAttributeList().get(i); 
			
			if("VS OAM条目数".equals(driveAttribute.getDescription())){
				
				int filecount = tmsoamim.getTmsOamBugControlObjectlist().size();// 获得条目数
				driveAttribute.setValue(filecount+"");
				String file = driveAttribute.getListRootList().get(0).getFile();
				driveAttribute.getListRootList().clear();// 清除本身自带的一个ListRoot			
				
				for (int j = 0; j < filecount;j++) {// 按条目数循环
					
					DriveRoot driveRoot_config_1 = super.LoadDriveXml(file);
					ListRoot listroot = new ListRoot();
					listroot.setDriveAttributeList(driveRoot_config_1.getDriveAttributeList());	
					StringBuffer  sb = new StringBuffer();
					sb.append(tmsoamim.getTmsOamBugControlObjectlist().get(j).getMegIcc()+",");
					sb.append(tmsoamim.getTmsOamBugControlObjectlist().get(j).getMegUmc());
					String[] meg = sb.toString().split(",");
					
					// 将 TMSOAMBugControlInfo 对象信息 赋值到 driveRoot_config 对象中
					for (int k = 0; k < driveRoot_config_1.getDriveAttributeList().size(); k++) {// 按xml元素循环
						
						DriveAttribute driveAttribute_sub = driveRoot_config_1.getDriveAttributeList().get(k);						
						//赋值
						TMSOAMBugControlInfoTODriveAttribute(tmsoamim.getTmsOamBugControlObjectlist().get(j), driveAttribute_sub,meg);

					}
					driveAttribute.getListRootList().add(listroot);		
				}
			}
		}
		AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
		byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
		CoderUtils.print16String(dataCommand);
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
	 
	public TMSOAMBugControlObject analysisCommandInfoToTMSOAMBugControlObject(byte[] commands,String configXml) throws Exception{
		
		TMSOAMBugControlObject tmsoamim = new TMSOAMBugControlObject();//获得TMSOAMBugControlObject对象
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);//获得TMS OAM故障管理配置(0FH).xml的元素
		
		// 起始长度
		int start = NEhead + controlPanelHead + dataBlockHead;
		
		byte[] a = new byte[files];
		byte[] b = new byte[filecount_sub];
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		AnalysisCommandByDriveXml analysisCommandByDriveXml_SUB = new AnalysisCommandByDriveXml();
		
		for (int i = 0; i <driveRoot_config.getDriveAttributeList().size(); i++) {//以TMS OAM故障管理配置(0FH).xml的元素遍历
			
			System.arraycopy(commands, start, a, 0, a.length);		
			analysisCommandByDriveXml.setCommands(a);
			DriveAttribute driveAttribute = new DriveAttribute();
			driveAttribute = driveRoot_config.getDriveAttributeList().get(i);

			if("VS OAM条目数".equals(driveAttribute.getDescription())){
				//条目数
				byte[] counts = {0x00,0x00,0x00,commands[start+files-1]};
				int filecount = CoderUtils.bytesToInt(counts);
				for (int j = 0; j < filecount; j++) {//按TMPOAMBugControlInfoObject条目数遍历
					
					TMSOAMBugControlInfoObject tmsOAMInfo = new TMSOAMBugControlInfoObject();//创建TMSOAMBugControlInfoObject对象
					String file = driveAttribute.getListRootList().get(0).getFile();//获TMS OAM故障管理配置(0FH)_SUB.xml路径
					System.arraycopy(commands, start+files+j*filecount_sub, b, 0, b.length);					
					analysisCommandByDriveXml_SUB.setCommands(b);
					DriveRoot driveRoot_config_sub =analysisCommandByDriveXml_SUB.analysisDriveAttrbute(file) ;//将对应命令放入xml后解析得到TMS OAM故障管理配置(0FH)_SUB.xml的元素
					StringBuffer sb_megicc = new StringBuffer();//用于拼接megicc
					StringBuffer sb_megumc = new StringBuffer();//用于拼接megumc
				
					for (int k = 0; k < driveRoot_config_sub.getDriveAttributeList().size(); k++) {//按TMS OAM故障管理配置(0FH)_SUB.xml元素个数遍历
						
						DriveAttribute driveAttribute_sub = new DriveAttribute();
						driveAttribute_sub = driveRoot_config_sub.getDriveAttributeList().get(k);
						
						//对tmsOAMInfo进行赋值
						DriveAttributeTOTMSOAMInfo(tmsOAMInfo, driveAttribute_sub,sb_megicc,sb_megumc);
					}
					tmsoamim.getTmsOamBugControlObjectlist().add(tmsOAMInfo);
				}				
			}
		}
		return tmsoamim;		
	}
	/**
	 * 对象赋值给xml
	 * @param info
	 * @param driveAttribute
	 * @param strs_targetMac
	 * @param meg
	 */
	private void TMSOAMBugControlInfoTODriveAttribute(TMSOAMBugControlInfoObject tmsoamimfo, DriveAttribute driveAttribute,String[] meg) {
		//槽位号
		if("槽位号".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmsoamimfo.getSlot()+"");			
		}
		//端口号
		if("端口号".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmsoamimfo.getPort()+"");			
		}
		//条目ID
		if("条目ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmsoamimfo.getSubclausesId()+"");
		}
		//源MEP ID
		if("源MEP ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmsoamimfo.getSourceMEP()+"");
		}
		//对等MEP ID
		if("对等MEP ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmsoamimfo.getEquityMEP()+"");
		}
		//(TST帧配置)TLV长度
		if("(TST帧配置)TLV长度".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmsoamimfo.getTst_tlvLength()+"");
		}
		//数据TLV测试内容
		if("数据TLV测试内容".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmsoamimfo.getLoopback_tlvtest()+"");
		}
		//(环回帧配置)TLV长度
		if("(环回帧配置)TLV长度".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmsoamimfo.getLoopback_tlvLength()+"");
		}
		//LCK帧发送使能
		if("LCK帧发送使能".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmsoamimfo.getLckEnable()+"");
		}
		//环回帧配置
		if("环回帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(Loopback(tmsoamimfo)+"");
		}
		//MEL
		if("MEL".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmsoamimfo.getEml()+"");
		}
		//TST帧配置
		if("TST帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(TST(tmsoamimfo)+"");
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
		//LM帧配置
		if("LM帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(LM(tmsoamimfo)+"");
		}
		//DM帧配置
		if("DM帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(DM(tmsoamimfo)+"");
		}
		//TC
		if("TC".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmsoamimfo.getTc()+"");
		}
		//LB TTL
		if("LB TTL".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmsoamimfo.getLbTTL()+"");
		}
		
		if("dm".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(tmsoamimfo.getDmlength()+"");
		}
	}
	/**
	 * LM帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int LM(TMSOAMBugControlInfoObject tmpoamimfo){
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
	private int DM(TMSOAMBugControlInfoObject tmpoamimfo){
		StringBuffer dm = new StringBuffer();
		dm.append(tmpoamimfo.getDmReserve());
		dm.append(tmpoamimfo.getDmCycle());
		dm.append(tmpoamimfo.getDmEnable());		
		char[] result = dm.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
		
	}
	
	/**
	 * 命令转lm
	 * @param info
	 * @param driveAttribute
	 */
	private void TOlm(TMSOAMBugControlInfoObject tmpOAMInfo,DriveAttribute driveAttribute){
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
	private void TOdm(TMSOAMBugControlInfoObject tmpOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		tmpOAMInfo.setDmReserve(str.substring(0,6));
		tmpOAMInfo.setDmCycle(str.substring(6,7));
		tmpOAMInfo.setDmEnable(str.substring(7));
	}
	
	/**
	 * Loopback帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int Loopback(TMSOAMBugControlInfoObject tmsoamimfo){
		StringBuffer loopback = new StringBuffer();
		loopback.append(tmsoamimfo.getLoopbackReserve());
		loopback.append(tmsoamimfo.getLoopbackTLV());
		loopback.append(tmsoamimfo.getLoopbackTest());
		loopback.append(tmsoamimfo.getLoopbackCycle());
		loopback.append(tmsoamimfo.getLoopbackEnable());		
		char[] result = loopback.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
	}
	/**
	 * TST帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int TST(TMSOAMBugControlInfoObject tmsoamimfo){
		StringBuffer tst = new StringBuffer();
		tst.append(tmsoamimfo.getTstReserve2());
		tst.append(tmsoamimfo.getTstTLV());
		tst.append(tmsoamimfo.getTstReserve1());
		tst.append(tmsoamimfo.getTstCycle());
		tst.append(tmsoamimfo.getTstEnable());
		char[] result = tst.toString().toCharArray();
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
	private void DriveAttributeTOTMSOAMInfo(TMSOAMBugControlInfoObject tmspmOAMInfo, DriveAttribute driveAttribute, StringBuffer sb_megicc, StringBuffer sb_megumc) {
		
		//槽位号
		if("槽位号".equals(driveAttribute.getDescription())){
			tmspmOAMInfo.setSlot(Integer.parseInt(driveAttribute.getValue()));			
		}
		//端口号
		if("端口号".equals(driveAttribute.getDescription())){
			tmspmOAMInfo.setPort(Integer.parseInt(driveAttribute.getValue()));			
		}
		//条目ID
		if("条目ID".equals(driveAttribute.getDescription())){
			tmspmOAMInfo.setSubclausesId(Integer.parseInt(driveAttribute.getValue()));
		}
		//(TST帧配置)TLV长度
		if("(TST帧配置)TLV长度".equals(driveAttribute.getDescription())){
			tmspmOAMInfo.setTst_tlvLength(Integer.parseInt(driveAttribute.getValue()));
		}
		//MEL
		if("MEL".equals(driveAttribute.getDescription())){
//			driveAttribute.setValue(tmsoamimfo.getEml()+"");
			tmspmOAMInfo.setEml(Integer.parseInt(driveAttribute.getValue()));
		}
		//数据TLV测试内容
		if("数据TLV测试内容".equals(driveAttribute.getDescription())){
			tmspmOAMInfo.setLoopback_tlvtest(Integer.parseInt(driveAttribute.getValue()));
		}
		//(环回帧配置)TLV长度
		if("(环回帧配置)TLV长度".equals(driveAttribute.getDescription())){
			tmspmOAMInfo.setLoopback_tlvLength(Integer.parseInt(driveAttribute.getValue()));
		}
		//LCK帧发送使能
		if("LCK帧发送使能".equals(driveAttribute.getDescription())){
			tmspmOAMInfo.setLckEnable(Integer.parseInt(driveAttribute.getValue()));
		}
		//源MEP ID
		if("源MEP ID".equals(driveAttribute.getDescription())){
			tmspmOAMInfo.setSourceMEP(Integer.parseInt(driveAttribute.getValue()));
		}
		//对等MEP ID
		if("对等MEP ID".equals(driveAttribute.getDescription())){
			tmspmOAMInfo.setEquityMEP(Integer.parseInt(driveAttribute.getValue()));
		}
		//环回帧配置
		if("环回帧配置".equals(driveAttribute.getDescription())){
			TOloopback(tmspmOAMInfo, driveAttribute);
		}
		//TST帧配置
		if("TST帧配置".equals(driveAttribute.getDescription())){
			TOTst(tmspmOAMInfo, driveAttribute);
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
			tmspmOAMInfo.setMegIcc(sb_megicc.toString());
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
			tmspmOAMInfo.setMegUmc(sb_megumc.toString());
		}
		//LM帧配置
		if("LM帧配置".equals(driveAttribute.getDescription())){
			TOlm(tmspmOAMInfo, driveAttribute);
		}
		//DM帧配置
		if("DM帧配置".equals(driveAttribute.getDescription())){
			TOdm(tmspmOAMInfo, driveAttribute);
		}
		//lb TTL
		if("LB TTL".equals(driveAttribute.getDescription())){
			tmspmOAMInfo.setLbTTL(Integer.parseInt(driveAttribute.getValue()));
		}
		
		if("dm".equals(driveAttribute.getDescription())){
			tmspmOAMInfo.setDmlength(Integer.parseInt(driveAttribute.getValue()));
		}
	}
	/**
	 * 命令转loopback
	 * @param info
	 * @param driveAttribute
	 */
	private void TOloopback(TMSOAMBugControlInfoObject tmsOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		tmsOAMInfo.setLoopbackReserve(str.substring(0,1));
		tmsOAMInfo.setLoopbackTLV(str.substring(1,3));
		tmsOAMInfo.setLoopbackTest(str.substring(3,4));
		tmsOAMInfo.setLoopbackCycle(str.substring(4,7));
		tmsOAMInfo.setLoopbackEnable(str.substring(7));		
	}
	/**
	 * 命令转tst
	 * @param info
	 * @param driveAttribute
	 */
	private void TOTst(TMSOAMBugControlInfoObject tmsOAMInfo,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		tmsOAMInfo.setTstReserve2(str.substring(0,1));
		tmsOAMInfo.setTstTLV(str.substring(1,3));
		tmsOAMInfo.setTstReserve1(str.substring(3, 4));
		tmsOAMInfo.setTstCycle(str.substring(4,7));
		tmsOAMInfo.setTstEnable(str.substring(7));
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
