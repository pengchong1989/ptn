package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.PwObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

/**
 * 
 * PW块解析
 * @author 彭冲
 *
 */
public class AnalysisPWTable extends AnalysisBase {

	private int dataCount = 100;// 每个数据块的长度
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int clauses = 2;// 条目数长度

	/**
	 * 根据对象生成字节数组
	 * 
	 * @param pwObject对象
	 * @param configXml配置XML
	 * @return 命令
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(List<PwObject> pwObjectObjectList, String configXml) throws Exception {
		try {

			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			driveRoot_config.getDriveAttributeList().get(0).getListRootList().clear();// 清除本身自带的一个ListRoot
			driveRoot_config.getDriveAttributeList().get(0).setValue(pwObjectObjectList.size() + "");// 条目数

			for (int j = 0; j < pwObjectObjectList.size(); j++) {// 按条目数循环
				DriveRoot driveRoot_config_1 = super.LoadDriveXml(file);
				ListRoot listroot = new ListRoot();
				listroot.setDriveAttributeList(driveRoot_config_1.getDriveAttributeList());
				String[] megIcc = pwObjectObjectList.get(j).getMegIcc().split(",");
				String[] megUmc = pwObjectObjectList.get(j).getMegUmc().split(",");
				String[] sourceMac = pwObjectObjectList.get(j).getSourceMac().split("-");
				String[] targetMac = pwObjectObjectList.get(j).getTargetMac().split("-");
				// 将 tunnelObject 对象信息 赋值到 driveRoot_config 对象中
				for (int i = 0; i < driveRoot_config_1.getDriveAttributeList().size(); i++) {// 按xml元素循环
					DriveAttribute driveAttribute = driveRoot_config_1.getDriveAttributeList().get(i);
					
					//赋值
					pwObjectTODriveAttribute(pwObjectObjectList.get(j), driveAttribute, megIcc,megUmc,sourceMac,targetMac);
					
				}
				driveRoot_config.getDriveAttributeList().get(0).getListRootList().add(listroot);
			}

			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommands = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
			CoderUtils.print16String(dataCommands);
			return dataCommands;

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return PwObject
	 */
	public List<PwObject> analysisCommandToObject(byte[] commands, String configXml) throws Exception {
		CoderUtils.print16String(commands);
		List<PwObject> pwObjectList = new ArrayList<PwObject>();
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
		// 起始长度
		int start = clauses;

		// 条目数
		int count = (commands.length - clauses) / dataCount;
		for (int j = 0; j < count; j++) {

			byte[] a = new byte[dataCount];
			System.arraycopy(commands, start + j * dataCount, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);

			try {
				DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(file);
				PwObject pwObject = new PwObject();
				StringBuffer sb_megicc = new StringBuffer();//用于拼接megicc
				StringBuffer sb_megumc = new StringBuffer();//用于拼接megumc
				StringBuffer sourceMac = new StringBuffer();
				StringBuffer targetMac = new StringBuffer();
				// 将 driveRoot 信息 赋值 PwObject 对象中
				for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {

					DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(i);
					
					//赋值
					DriveAttributeTOpwObject(pwObject, driveAttribute,sb_megicc,sb_megumc,sourceMac,targetMac);
				}
				pwObjectList.add(pwObject);
			} catch (Exception e) {
				throw e;
			}
		}
		return pwObjectList;
	}

	/**
	 * pwObject赋值给driveAttribute
	 * @param pwObject
	 * @param driveAttribute
	 */
	public void pwObjectTODriveAttribute(PwObject pwObject ,DriveAttribute driveAttribute,String[] megIcc,String[] megUmc,String[] sourceMac,String[] targetMac){
		
		// PW ID赋值
		if ("PW ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwObject.getPwId() + "");
		}
		// PW类型
		if ("PW类型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwObject.getPwType() + "");
		}

		// LSP ID赋值
		if ("LSPID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwObject.getTunnelId() + "");
		}

		// 入PW标签赋值
		if ("入PW标签".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwObject.getInLable() + "");
			/*调试使用***************************************************/
//			System.out.println("下发普通入PW标签:"+pwObject.getInLable());
//			ExceptionManage.dispose(new Exception("下发普通入PW标签:"+pwObject.getInLable()), this.getClass());
			/*end**************************************************/
		}

		// 出PW标签赋值
		if ("出PW标签".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(pwObject.getOutLable() + "");
			/*调试使用***************************************************/
//			System.out.println("下发普通出PW标签:"+pwObject.getOutLable());
//			ExceptionManage.dispose(new Exception("下发普通出PW标签:"+pwObject.getOutLable()), this.getClass());
			/*end**************************************************/
		}
		
		//模式
		if("模式".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getModel()+"");
		}else if("角色".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getRole()+"");
		}
		
		//CBS
		if("CBS".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getCbs()+"");
		}
		//Pbs
		if("PBS".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getPbs()+"");
		}
		//CIR
		if("CIR".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getCir()+"");
		}
		
		//PIR
		if("PIR".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getPir()+"");
		}
		
		//(PHB到VC/VP EXP映射)策略
		if("(PHB到VC/VP EXP映射)策略".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getPhbtoexpstrategy()+"");
		}
		
		//(PHB到VC/VP EXP映射)指配EXP
		if("(PHB到VC/VP EXP映射)指配EXP".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getExp()+"");
		}
		
		//(PHB到VC/VP EXP映射)PHB2EXP_ID
		if("(PHB到VC/VP EXP映射)PHB2EXP_ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getExpid()+"");
		}
		//(VP EXP到PHB映射)策略
		if("(VP EXP到PHB映射)策略".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getExptophbstrategy()+"");
		}
		
		//(VP EXP到PHB映射)指配PHB
		if("(VP EXP到PHB映射)指配PHB".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getPhb()+"");
		}
		//(VP EXP到PHB映射)EXP 2 PHB _ID
		if("(VP EXP到PHB映射)EXP 2 PHB _ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getPhbid()+"");
		}
		
		//VC OAM使能
		if("VC OAM使能".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getVcEnabl()+"");
		}
		//MEL
		if("MEL".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getMel()+"");
		}
		
		//源MEP ID
		if("源MEP ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getSourceMEP()+"");
		}
		//对等MEP ID
		if("对等MEP ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getEquityMEP()+"");
		}
		
		if(megIcc.length>1){
			//MEG ICC1
			if("MEG ICC1".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(megIcc[0]);
			}
			//MEG ICC2
			if("MEG ICC2".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(megIcc[1]);
			}
			//MEG ICC3
			if("MEG ICC3".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(megIcc[2]);
			}
			//MEG ICC4
			if("MEG ICC4".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(megIcc[3]);
			}
			//MEG ICC5
			if("MEG ICC5".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(megIcc[4]);
			}
			//MEG ICC6
			if("MEG ICC6".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(megIcc[5]);
			}
		}
		if(megUmc.length>1){
			//MEG UMC1
			if("MEG UMC1".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(megUmc[0]);
			}
			//MEG UMC2
			if("MEG UMC2".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(megUmc[1]);
			}
			//MEG UMC3
			if("MEG UMC3".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(megUmc[2]);
			}
			//MEG UMC4
			if("MEG UMC4".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(megUmc[3]);
			}
			//MEG UMC5
			if("MEG UMC5".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(megUmc[4]);
			}
			//MEG UMC6
			if("MEG UMC6".equals(driveAttribute.getDescription())){
				driveAttribute.setValue(megUmc[5]);
			}
		}
		//VC OAM设置19
		if("VC OAM设置19".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(cvframe(pwObject)+"");
		}
		//CSF帧配置
		if("CSF帧配置".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(csfframe(pwObject)+"");
		}
		//LM
		if("LM".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getLm()+"");
		}
		//LSPTC
		if("LSPTC".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getLspTc()+"");
		}
		//PWTC
		if("PWTC".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getPwTc()+"");
		}
		if("OAM使能".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getOamEnable()+"");
		}
		//源MAC地址1
		if("源MAC地址1".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(sourceMac[0]);
		}
		//源MAC地址2
		if("源MAC地址2".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(sourceMac[1]);
		}
		//源MAC地址3
		if("源MAC地址3".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(sourceMac[2]);
		}
		//源MAC地址4
		if("源MAC地址4".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(sourceMac[3]);
		}
		//源MAC地址5
		if("源MAC地址5".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(sourceMac[4]);
		}
		//源MAC地址6
		if("源MAC地址6".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(sourceMac[5]);
		}
		//目的MAC地址1
		if("目的MAC地址1".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(targetMac[0]);
		}
		//目的MAC地址2
		if("目的MAC地址2".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(targetMac[1]);
		}
		//目的MAC地址3
		if("目的MAC地址3".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(targetMac[2]);
		}
		//目的MAC地址4
		if("目的MAC地址4".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(targetMac[3]);
		}
		//目的MAC地址5
		if("目的MAC地址5".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(targetMac[4]);
		}
		//目的MAC地址6
		if("目的MAC地址6".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(targetMac[5]);
		}
		//外层VLAN使能
		if("外层VLAN使能".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getVlanEnable()+"");
		}
		//外层VLAN值
		if("外层VLAN值".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getOutVlanValue()+"");
		}
		//外层TP_ID
		else if("外层TP_ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(pwObject.getTp_id()+"");
		}
		
	}
	
	/**
	 * csfframe帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int csfframe(PwObject pwObject){
		StringBuffer csf = new StringBuffer();
		csf.append(pwObject.getCsfreserve());
		csf.append(pwObject.getCsfcycle());
		csf.append(pwObject.getCsfEnabl());	
		char[] result = csf.toString().toCharArray();
		int a=CoderUtils.convertAlgorism(result);
		return a ;
	}
	
	/**
	 * cvframe帧转换为xml格式
	 * @param info
	 * @return
	 */
	private int cvframe(PwObject pwObject){
		StringBuffer cv = new StringBuffer();
		cv.append(pwObject.getCvReserve());
		cv.append(pwObject.getCvCycle());
		cv.append(pwObject.getCvEnabl());		
		char[] result = cv.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a ;
	}
	
	/**
	 * driveAttribute赋值给pwObject
	 * @param pwObject
	 * @param driveAttribute
	 */
	public void DriveAttributeTOpwObject(PwObject pwObject ,DriveAttribute driveAttribute,StringBuffer sb_megumc,StringBuffer sb_megicc,StringBuffer sourceMac,StringBuffer targetMac){
		// PW ID赋值
		if ("PW ID".equals(driveAttribute.getDescription())) {
			pwObject.setPwId(Integer.parseInt(driveAttribute.getValue()));
		}
		// PW类型
		if ("PW类型".equals(driveAttribute.getDescription())) {
			pwObject.setPwType(Integer.parseInt(driveAttribute.getValue()));
		}

		// LSP ID赋值
		if ("LSPID".equals(driveAttribute.getDescription())) {
			pwObject.setTunnelId(Integer.parseInt(driveAttribute.getValue()));
		}

		// 入PW标签赋值
		if ("入PW标签".equals(driveAttribute.getDescription())) {
			pwObject.setInLable(Integer.parseInt(driveAttribute.getValue()));
			/*调试使用***************************************************/
//			System.out.println("同步普通入PW标签:"+pwObject.getInLable());
//			ExceptionManage.dispose(new Exception("同步普通入PW标签:"+pwObject.getInLable()), this.getClass());
			/*end**************************************************/
		}

		// 出PW标签赋值
		if ("出PW标签".equals(driveAttribute.getDescription())) {
			pwObject.setOutLable(Integer.parseInt(driveAttribute.getValue()));
			/*调试使用***************************************************/
//			System.out.println("同步普通出PW标签:"+pwObject.getOutLable());
//			ExceptionManage.dispose(new Exception("同步普通出PW标签:"+pwObject.getOutLable()), this.getClass());
			/*end**************************************************/
		}
		
		//模式
		if("模式".equals(driveAttribute.getDescription())){
			pwObject.setModel(Integer.parseInt(driveAttribute.getValue()));
		}else if("角色".equals(driveAttribute.getDescription())){
			pwObject.setRole(Integer.parseInt(driveAttribute.getValue()));
		}
		
		//CIR
		if("CIR".equals(driveAttribute.getDescription())){
			pwObject.setCir(Integer.parseInt(driveAttribute.getValue()));
		}
		
		//PIR
		if("PIR".equals(driveAttribute.getDescription())){
			pwObject.setPir(Integer.parseInt(driveAttribute.getValue()));
		}
		
		//CBS
		if("CBS".equals(driveAttribute.getDescription())){
			pwObject.setCbs(Integer.parseInt(driveAttribute.getValue()));
		}
		//Pbs
		if("PBS".equals(driveAttribute.getDescription())){
			pwObject.setPbs(Integer.parseInt(driveAttribute.getValue()));
		}
		
		
		//(PHB到VC/VP EXP映射)策略
		if("(PHB到VC/VP EXP映射)策略".equals(driveAttribute.getDescription())){
			pwObject.setPhbtoexpstrategy(Integer.parseInt(driveAttribute.getValue()));
		}
		
		//(PHB到VC/VP EXP映射)指配EXP
		if("(PHB到VC/VP EXP映射)指配EXP".equals(driveAttribute.getDescription())){
			pwObject.setExp(Integer.parseInt(driveAttribute.getValue()));
		}
		
		//(PHB到VC/VP EXP映射)PHB2EXP_ID
		if("(PHB到VC/VP EXP映射)PHB2EXP_ID".equals(driveAttribute.getDescription())){
			pwObject.setExpid(Integer.parseInt(driveAttribute.getValue()));
		}
		//(VP EXP到PHB映射)策略
		if("(VP EXP到PHB映射)策略".equals(driveAttribute.getDescription())){
			pwObject.setExptophbstrategy(Integer.parseInt(driveAttribute.getValue()));
		}
		//(VP EXP到PHB映射)指配PHB
		if("(VP EXP到PHB映射)指配PHB".equals(driveAttribute.getDescription())){
			pwObject.setPhb(Integer.parseInt(driveAttribute.getValue()));
		}
		//(VP EXP到PHB映射)EXP 2 PHB _ID
		if("(VP EXP到PHB映射)EXP 2 PHB _ID".equals(driveAttribute.getDescription())){
			pwObject.setPhbid(Integer.parseInt(driveAttribute.getValue()));
		}
		
		//VC OAM使能
		if("VC OAM使能".equals(driveAttribute.getDescription())){
			pwObject.setVcEnabl(Integer.parseInt(driveAttribute.getValue()));
		}
		//MEL
		if("MEL".equals(driveAttribute.getDescription())){
			pwObject.setMel(Integer.parseInt(driveAttribute.getValue()));
		}
		
		//源MEP ID
		if("源MEP ID".equals(driveAttribute.getDescription())){
			pwObject.setSourceMEP(Integer.parseInt(driveAttribute.getValue()));
		}
		//对等MEP ID
		if("对等MEP ID".equals(driveAttribute.getDescription())){
			pwObject.setEquityMEP(Integer.parseInt(driveAttribute.getValue()));
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
			pwObject.setMegIcc(sb_megicc.toString());
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
			pwObject.setMegUmc(sb_megumc.toString());
		}
		
//		//VC OAM设置19
//		if("VC OAM设置19".equals(driveAttribute.getDescription())){
//			driveAttribute.setValue(cvframe(pwObject)+"");
//		}
//		//CSF帧配置
//		if("CSF帧配置".equals(driveAttribute.getDescription())){
//			driveAttribute.setValue(csfframe(pwObject)+"");
//		}
		//VC OAM设置19
		if("VC OAM设置19".equals(driveAttribute.getDescription())){
			TOcv(pwObject, driveAttribute);
		}
		//CSF帧配置
		if("CSF帧配置".equals(driveAttribute.getDescription())){
			TOcsf(pwObject, driveAttribute);
		}
		//lm
		if("LM".equals(driveAttribute.getDescription())){
			pwObject.setLm(Integer.parseInt(driveAttribute.getValue()));
		}
		//LSPTC
		if("LSPTC".equals(driveAttribute.getDescription())){
			pwObject.setLspTc(Integer.parseInt(driveAttribute.getValue()));
		}
		//PWTC
		if("PWTC".equals(driveAttribute.getDescription())){
			pwObject.setPwTc(Integer.parseInt(driveAttribute.getValue()));
		}
		if("OAM使能".equals(driveAttribute.getDescription())){
			pwObject.setOamEnable(Integer.parseInt(driveAttribute.getValue()));
		}
		//源MAC地址1
		if("源MAC地址1".equals(driveAttribute.getDescription())){
			sourceMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//源MAC地址2
		if("源MAC地址2".equals(driveAttribute.getDescription())){
			sourceMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//源MAC地址3
		if("源MAC地址3".equals(driveAttribute.getDescription())){
			sourceMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//源MAC地址4
		if("源MAC地址4".equals(driveAttribute.getDescription())){
			sourceMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//源MAC地址5
		if("源MAC地址5".equals(driveAttribute.getDescription())){
			sourceMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//源MAC地址6
		if("源MAC地址6".equals(driveAttribute.getDescription())){
			sourceMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue()));
			pwObject.setSourceMac(sourceMac.toString());
		}
		//目的MAC地址1
		if("目的MAC地址1".equals(driveAttribute.getDescription())){
			targetMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//目的MAC地址2
		if("目的MAC地址2".equals(driveAttribute.getDescription())){
			targetMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//目的MAC地址3
		if("目的MAC地址3".equals(driveAttribute.getDescription())){
			targetMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//目的MAC地址4
		if("目的MAC地址4".equals(driveAttribute.getDescription())){
			targetMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//目的MAC地址5
		if("目的MAC地址5".equals(driveAttribute.getDescription())){
			targetMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
		}
		//目的MAC地址6
		if("目的MAC地址6".equals(driveAttribute.getDescription())){
			targetMac.append(CoderUtils.synchTransformMac(driveAttribute.getValue()));
			pwObject.setTargetMac(targetMac.toString());
		}
		//外层VLAN使能
		if("外层VLAN使能".equals(driveAttribute.getDescription())){
			pwObject.setVlanEnable(Integer.parseInt(driveAttribute.getValue()));
		}
		//外层VLAN值
		if("外层VLAN值".equals(driveAttribute.getDescription())){
			pwObject.setOutVlanValue(Integer.parseInt(driveAttribute.getValue()));
		}
		//外层TP_ID
		if("外层TP_ID".equals(driveAttribute.getDescription())){
			pwObject.setTp_id(Integer.parseInt(driveAttribute.getValue()));
		}
	}
	/**
	 * 命令转csf
	 * @param info
	 * @param driveAttribute
	 */
	private void TOcsf(PwObject pwObject,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		pwObject.setCsfreserve(str.substring(0,4));
		pwObject.setCsfcycle(str.substring(4, 7));
		pwObject.setCsfEnabl(str.substring(7));
	}
	/**
	 * 命令转cv
	 * @param info
	 * @param driveAttribute
	 */
	private void TOcv(PwObject pwObject,DriveAttribute driveAttribute){
		String str = CoderUtils.convertBinary(Integer.parseInt(driveAttribute.getValue()));
		str = eightBinary(str);
		pwObject.setCvReserve(str.substring(0,4));
		pwObject.setCvCycle(str.substring(4,7));
		pwObject.setCvEnabl(str.substring(7));
		
		
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
