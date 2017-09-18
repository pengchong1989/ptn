package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.ETHOAMAllObject;
import com.nms.drive.service.bean.ETHOAMObject;
import com.nms.drive.service.impl.CoderUtils;

/**
 * @author zk 解析以太网OAM
 */

public class AnalysisETHOamConfig extends AnalysisBase {
	private int dataCount = 194;// 每个数据块的长度
	private int NEhead = 5;// NE头长度
	private int controlPanelHead = 101;// 盘头长度
	private int dataBlockHead = 25;// 配置块头信息长度
	private int clauses = 1;// 条目数长度

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
	public byte[] analysisGlobalObjectToCommands(ETHOAMAllObject oamInfoList, String configXml)
			throws Exception {
		// 获取根目录
		DriveRoot globalDrivRoot = super.LoadDriveXml(configXml);
		String file = globalDrivRoot.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
		globalDrivRoot.getDriveAttributeList().get(0).setValue(oamInfoList.getEthoamObjectList().size() + "");
		globalDrivRoot.getDriveAttributeList().get(0).getListRootList().clear();// 清除本身自带的一个ListRoot

		for (int k = 0; k < oamInfoList.getEthoamObjectList().size(); k++) {
			DriveRoot globalDrivRootOther = super.LoadDriveXml(file);
			ListRoot listroot = new ListRoot();
			listroot.setDriveAttributeList(globalDrivRootOther.getDriveAttributeList());
			for (int i = 0; i < globalDrivRootOther.getDriveAttributeList().size(); i++) {
				DriveAttribute driveAttribute = listroot.getDriveAttributeList().get(i);
				// 属性赋值
				lspObjectTODriveAttribute(driveAttribute, oamInfoList.getEthoamObjectList().get(k), k + 1);
			}
			globalDrivRoot.getDriveAttributeList().get(0).getListRootList().add(listroot);
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
	 * @param commands命令
	 * @param configXml配置XML
	 * @return PwObject
	 */
	public List<ETHOAMObject> analysisCommandToObject(byte[] commands,String configXml) throws Exception {
		
		List<ETHOAMObject> ethOamObjectList = new ArrayList<ETHOAMObject>();
		DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
		String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
		// 起始长度
		int start = clauses;

		// 条目数
		int count = (commands.length - clauses) / dataCount;
		DriveRoot driveRoot=null;
		for (int j = 0; j < count; j++) {
			byte[] a = new byte[dataCount];
			System.arraycopy(commands, start + j * dataCount, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			try {
				driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(file);
				ETHOAMObject ethOamObject = new ETHOAMObject();
				StringBuffer sb_MdName = new StringBuffer();// 用于拼接mdName
				StringBuffer sb_MaName = new StringBuffer();// 用于拼接MaName
				StringBuffer sb_TstPurposeMepMac = new StringBuffer();// TST目的MEP
				// MAC1
				StringBuffer sb_mac1 = new StringBuffer();// MAC 1地址
				StringBuffer sb_mac2 = new StringBuffer();// MAC 2地址
				StringBuffer sb_mac3 = new StringBuffer();// MAC 3地址
				StringBuffer sb_mac4 = new StringBuffer();// MAC 4地址
				// 将 driveRoot 信息 赋值 PwObject 对象中
				for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
					DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(i);
					// 赋值
					DriveAttributeOamInfoObject(ethOamObject, driveAttribute,sb_MdName,sb_MaName, sb_TstPurposeMepMac, sb_mac1, sb_mac2,sb_mac3, sb_mac4);
				}
				ethOamObjectList.add(ethOamObject);
			} catch (Exception e) {
				throw e;
			}finally{
				
			}
		}
		return ethOamObjectList;
	}

	private void DriveAttributeOamInfoObject(ETHOAMObject ethOamObject,DriveAttribute driveAttribute, StringBuffer sbMdName,
			  StringBuffer sbMaName, StringBuffer sbTstPurposeMepMac,StringBuffer sbMac1, StringBuffer sbMac2, StringBuffer sbMac3,
			StringBuffer sbMac4) {

		if ("条目ID".equals(driveAttribute.getDescription())) {
			ethOamObject.setItemNumber(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("以太网OAM使能".equals(driveAttribute.getDescription())) {
			ethOamObject.setThernetOAMEnabl(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("默认MD LEVEL".equals(driveAttribute.getDescription())) {
			ethOamObject.setMdLevel(driveAttribute.getValue());
		}
		if ("MP属性".equals(driveAttribute.getDescription())) {
			ethOamObject.setMpLable(Integer.parseInt(driveAttribute.getValue()));
		}
		/**
		 * sb_megicc.append(driveAttribute.getValue());
		 */
		if ("MD Name1".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name2".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name3".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name4".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name5".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name6".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name7".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name8".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name9".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name10".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name11".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name12".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name13".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name14".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name15".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name16".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name17".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name18".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name19".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name20".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name21".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name22".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name23".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name24".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name25".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name26".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name27".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name28".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name29".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name30".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name31".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name32".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name33".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name34".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name35".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name36".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name37".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name38".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name39".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name40".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name41".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name42".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MD Name43".equals(driveAttribute.getDescription())) {
			sbMdName.append((char)Integer.parseInt(driveAttribute.getValue()));
			ethOamObject.setMdName(sbMdName.toString().trim());
		}
		if ("MD Level".equals(driveAttribute.getDescription())) {
			ethOamObject.setMdLevel(driveAttribute.getValue());
		}
		if ("MA Name1".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name2".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name3".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name4".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name5".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name6".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name7".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name8".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name9".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name10".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name11".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name12".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name13".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name14".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name15".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name16".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name17".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name18".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name19".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name20".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));		
			}
		if ("MA Name21".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name22".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name23".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name24".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name25".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name26".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name27".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name28".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name29".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name30".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name31".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name32".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name33".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name34".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name35".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name36".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name37".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name38".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name39".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name40".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name41".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name42".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MA Name43".equals(driveAttribute.getDescription())) {
			sbMaName.append((char)Integer.parseInt(driveAttribute.getValue()));
			ethOamObject.setMaName(sbMaName.toString().trim());
		}
		if ("CCM发送间隔".equals(driveAttribute.getDescription())) {
			ethOamObject.setCcmsend(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("VLAN".equals(driveAttribute.getDescription())) {
			ethOamObject.setVlan(driveAttribute.getValue());
		}
		if ("MEP ID".equals(driveAttribute.getDescription())) {
			ethOamObject.setMepId(driveAttribute.getValue());
		}
		if ("MEP类型".equals(driveAttribute.getDescription())) {
			ethOamObject.setMepType(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("槽位号".equals(driveAttribute.getDescription())) {
			ethOamObject.setSlot(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("端口号".equals(driveAttribute.getDescription())) {
			ethOamObject.setPort(driveAttribute.getValue());
		}
		if ("CCM发送使能".equals(driveAttribute.getDescription())) {
			ethOamObject.setCcmSendEnable(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("CCM接受使能".equals(driveAttribute.getDescription())) {
			ethOamObject.setCcmReceiveEnable(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("CCM优先级".equals(driveAttribute.getDescription())) {
			ethOamObject.setCcmPriority(driveAttribute.getValue());
		}
		if ("LBM数据TLV类型".equals(driveAttribute.getDescription())) {
			ethOamObject.setLbmTlvType(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("LBM数据TLV长度".equals(driveAttribute.getDescription())) {
			ethOamObject.setLbmTlvTypeLength(driveAttribute.getValue());
		}
		if ("LBM优先级".equals(driveAttribute.getDescription())) {
			ethOamObject.setLbmPriority(driveAttribute.getValue());
		}
		if ("LBM丢弃适用性".equals(driveAttribute.getDescription())) {
			ethOamObject.setLbmDiscard(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("LTM优先级".equals(driveAttribute.getDescription())) {
			ethOamObject.setLtmPriority(driveAttribute.getValue());
		}
		if ("AIS发送使能".equals(driveAttribute.getDescription())) {
			ethOamObject.setAisSendEnabel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("客户MD Level".equals(driveAttribute.getDescription())) {
			ethOamObject.setClientMdLevel(driveAttribute.getValue());
		}
		if ("AIS优先级".equals(driveAttribute.getDescription())) {
			ethOamObject.setAisPriority(driveAttribute.getValue());
		}
		if ("LCK发送使能".equals(driveAttribute.getDescription())) {
			ethOamObject.setLckSendEnabel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("LCK优先级".equals(driveAttribute.getDescription())) {
			ethOamObject.setLckPriority(driveAttribute.getValue());
		}
		if ("AIS/LCK发送周期".equals(driveAttribute.getDescription())) {
			ethOamObject.setAisLckSendCycle(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("TST发送使能".equals(driveAttribute.getDescription())) {
			ethOamObject.setTstSendEnabel(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("TST发送MDLevel".equals(driveAttribute.getDescription())) {
			ethOamObject.setTstSendLevel(driveAttribute.getValue());
		}
		if ("TST目的MEP MAC1".equals(driveAttribute.getDescription())) {
			sbTstPurposeMepMac.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("TST目的MEP MAC2".equals(driveAttribute.getDescription())) {
			sbTstPurposeMepMac.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("TST目的MEP MAC3".equals(driveAttribute.getDescription())) {
			sbTstPurposeMepMac.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("TST目的MEP MAC4".equals(driveAttribute.getDescription())) {
			sbTstPurposeMepMac.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("TST目的MEP MAC5".equals(driveAttribute.getDescription())) {
			sbTstPurposeMepMac.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("TST目的MEP MAC6".equals(driveAttribute.getDescription())) {
			sbTstPurposeMepMac.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase());
			ethOamObject.setTstPurposeMepMac(sbTstPurposeMepMac.toString());
		}
		if ("TST优先级".equals(driveAttribute.getDescription())) {
			ethOamObject.setTstPriority(driveAttribute.getValue());
		}
		if ("发送方式".equals(driveAttribute.getDescription())) {
			ethOamObject.setSendWay(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("TST丢弃适用性".equals(driveAttribute.getDescription())) {
			ethOamObject.setTstDiscard(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("TST数据TLV类型".equals(driveAttribute.getDescription())) {
			ethOamObject.setTstTlvType(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("TST数据TLV长度".equals(driveAttribute.getDescription())) {
			ethOamObject.setTstTlvLength(driveAttribute.getValue());
		}
		if ("TST发送周期".equals(driveAttribute.getDescription())) {
			ethOamObject.setTstSendCycle(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("LM使能".equals(driveAttribute.getDescription())) {
			ethOamObject.setLmENable(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("LM优先级".equals(driveAttribute.getDescription())) {
			ethOamObject.setLmPriority(driveAttribute.getValue());
		}
		if ("LM发送周期".equals(driveAttribute.getDescription())) {
			ethOamObject.setLmSendCycle(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("DM使能".equals(driveAttribute.getDescription())) {
			ethOamObject.setDmENable(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("DM优先级".equals(driveAttribute.getDescription())) {
			ethOamObject.setDmPriority(driveAttribute.getValue());
		}
		if ("DM发送周期".equals(driveAttribute.getDescription())) {
			ethOamObject.setDmSendCycle(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("Remote MEP ID 1".equals(driveAttribute.getDescription())) {
			ethOamObject.setRemoteMepId1(driveAttribute.getValue());
		}
		if ("MAC 1地址1".equals(driveAttribute.getDescription())) {
			// Integer.toHexString 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式
			sbMac1.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 1地址2".equals(driveAttribute.getDescription())) {
			sbMac1.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 1地址3".equals(driveAttribute.getDescription())) {
			sbMac1.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 1地址4".equals(driveAttribute.getDescription())) {
			sbMac1.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 1地址5".equals(driveAttribute.getDescription())) {
			sbMac1.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 1地址6".equals(driveAttribute.getDescription())) {
			sbMac1.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase());
			ethOamObject.setMacAddress1(sbMac1.toString());
		}
		if ("Remote MEP ID 2".equals(driveAttribute.getDescription())) {
			ethOamObject.setRemoteMepId2(driveAttribute.getValue());
		}
		if ("MAC 2地址1".equals(driveAttribute.getDescription())) {
			sbMac2.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 2地址2".equals(driveAttribute.getDescription())) {
			sbMac2.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 2地址3".equals(driveAttribute.getDescription())) {
			sbMac2.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 2地址4".equals(driveAttribute.getDescription())) {
			sbMac2.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 2地址5".equals(driveAttribute.getDescription())) {
			sbMac2.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 2地址6".equals(driveAttribute.getDescription())) {
			sbMac2.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase());
			ethOamObject.setMacAddress2(sbMac2.toString());
		}
		if ("Remote MEP ID 3".equals(driveAttribute.getDescription())) {
			ethOamObject.setRemoteMepId3(driveAttribute.getValue());
		}
		if ("MAC 3地址1".equals(driveAttribute.getDescription())) {
			sbMac3.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 3地址2".equals(driveAttribute.getDescription())) {
			sbMac3.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 3地址3".equals(driveAttribute.getDescription())) {
			sbMac3.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 3地址4".equals(driveAttribute.getDescription())) {
			sbMac3.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 3地址5".equals(driveAttribute.getDescription())) {
			sbMac3.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 3地址6".equals(driveAttribute.getDescription())) {
			sbMac3.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase());
			ethOamObject.setMacAddress3(sbMac3.toString());
		}
		if ("Remote MEP ID 4".equals(driveAttribute.getDescription())) {
			ethOamObject.setRemoteMepId4(driveAttribute.getValue());
		}
		if ("MAC 4地址1".equals(driveAttribute.getDescription())) {
			sbMac4.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 4地址2".equals(driveAttribute.getDescription())) {
			sbMac4.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 4地址3".equals(driveAttribute.getDescription())) {
			sbMac4.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 4地址4".equals(driveAttribute.getDescription())) {
			sbMac4.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 4地址5".equals(driveAttribute.getDescription())) {
			sbMac4.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase()+"-");
		}
		if ("MAC 4地址6".equals(driveAttribute.getDescription())) {
			sbMac4.append(Integer.toHexString(Integer.parseInt(driveAttribute.getValue())/16).toUpperCase()+""+Integer.toHexString(Integer.parseInt(driveAttribute.getValue())%16).toUpperCase());
			ethOamObject.setMacAddress4(sbMac4.toString());
		}
		if ("MIP生成规则".equals(driveAttribute.getDescription())) {
			ethOamObject.setMipCreate(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MIP槽位号".equals(driveAttribute.getDescription())) {
			ethOamObject.setMipSlot(Integer.parseInt(driveAttribute.getValue()));
		}
		if ("MIP端口号".equals(driveAttribute.getDescription())) {
			ethOamObject.setMipPort(driveAttribute.getValue());
		}

	}

	private void lspObjectTODriveAttribute(DriveAttribute driveAttribute,ETHOAMObject ethOamInfo, int length) {

		if ("条目ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(length+ "");
		}
		if ("以太网OAM使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getThernetOAMEnabl() + "");
		}
		if ("默认MD LEVEL".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMdMLevel());
		}
		if ("MP属性".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMpLable() + "");
		}
		if ("MD Name1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[0])+"");
		}
		if ("MD Name2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[1])+"");
		}
		if ("MD Name3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[2])+"");
		}
		if ("MD Name4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[3])+"");
		}
		if ("MD Name5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[4])+"");
		}
		if ("MD Name6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[5])+"");
		}
		if ("MD Name7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[6])+"");
		}
		if ("MD Name8".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[7])+"");
		}
		if ("MD Name9".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[8])+"");
		}
		if ("MD Name10".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[9])+"");
		}
		if ("MD Name11".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[10])+"");
		}
		if ("MD Name12".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[11])+"");
		}
		if ("MD Name13".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[12])+"");
		}
		if ("MD Name14".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[13])+"");
		}
		if ("MD Name15".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[14])+"");
		}
		if ("MD Name16".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[15])+"");
		}
		if ("MD Name17".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[16])+"");
		}
		if ("MD Name18".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[17])+"");
		}
		if ("MD Name19".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[18])+"");
		}
		if ("MD Name20".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[19])+"");
		}
		if ("MD Name21".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[20])+"");
		}
		if ("MD Name22".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[21])+"");
		}
		if ("MD Name23".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[22])+"");
		}
		if ("MD Name24".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[23])+"");
		}
		if ("MD Name25".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[24])+"");
		}
		if ("MD Name26".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[25])+"");
		}
		if ("MD Name27".equals(driveAttribute.getDescription())) {
		driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[26])+"");
		}
		if ("MD Name28".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[27])+"");
		}
		if ("MD Name29".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[28])+"");
		}
		if ("MD Name30".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[29])+"");
		}
		if ("MD Name31".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[30])+"");
		}
		if ("MD Name32".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[31])+"");
		}
		if ("MD Name33".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[32])+"");
		}
		if ("MD Name34".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[33])+"");
		}
		if ("MD Name35".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[34])+"");
		}
		if ("MD Name36".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[35])+"");
		}
		if ("MD Name37".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[36])+"");
		}
		if ("MD Name38".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[37])+"");
		}
		if ("MD Name39".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[38])+"");
		}
		if ("MD Name40".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[39])+"");
		}
		if ("MD Name41".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[40])+"");
		}
		if ("MD Name42".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[41])+"");
		}
		if ("MD Name43".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMdName()).split(",")[42])+"");
		}
		if ("MD Level".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMdLevel() + "");
		}
		if ("MA Name1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[0])+"");
		}
		if ("MA Name2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[1])+"");
		}
		if ("MA Name3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[2])+"");
		}
		if ("MA Name4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[3])+"");
		}
		if ("MA Name5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[4])+"");
		}
		if ("MA Name6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[5])+"");
		}
		if ("MA Name7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[6])+"");
		}
		if ("MA Name8".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[7])+"");
		}
		if ("MA Name9".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[8])+"");
		}
		if ("MA Name10".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[9])+"");
		}
		if ("MA Name11".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[10])+"");
		}
		if ("MA Name12".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[11])+"");
		}
		if ("MA Name13".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[12])+"");
		}
		if ("MA Name14".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[13])+"");
		}
		if ("MA Name15".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[14])+"");
		}
		if ("MA Name16".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[15])+"");
		}
		if ("MA Name17".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[16])+"");
		}
		if ("MA Name18".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[17])+"");
		}
		if ("MA Name19".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[18])+"");
		}
		if ("MA Name20".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[19])+"");
		}
		if ("MA Name21".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[20])+"");
		}
		if ("MA Name22".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[21])+"");
		}
		if ("MA Name23".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[22])+"");
		}
		if ("MA Name24".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[23])+"");
		}
		if ("MA Name25".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[24])+"");
		}
		if ("MA Name26".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[25])+"");
		}
		if ("MA Name27".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[26])+"");
		}
		if ("MA Name28".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[27])+"");
		}
		if ("MA Name29".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[28])+"");
		}
		if ("MA Name30".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[29])+"");
		}
		if ("MA Name31".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[30])+"");
		}
		if ("MA Name32".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[31])+"");
		}
		if ("MA Name33".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[32])+"");
		}
		if ("MA Name34".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[33])+"");
		}
		if ("MA Name35".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[34])+"");
		}
		if ("MA Name36".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[35])+"");
		}
		if ("MA Name37".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[36])+"");
		}
		if ("MA Name38".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[37])+"");
		}
		if ("MA Name39".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[38])+"");
		}
		if ("MA Name40".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[39])+"");
		}
		if ("MA Name41".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[40])+"");
		}
		if ("MA Name42".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[41])+"");
		}
		if ("MA Name43".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue((getName(ethOamInfo.getMaName()).split(",")[42])+"");
		}
		if ("CCM发送间隔".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getCcmsend() + "");
		}
		if ("VLAN".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getVlan() + "");
		}
		if ("MEP ID".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMepId());
		}
		if ("MEP类型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMepType() + "");
		}
		if ("槽位号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getSlot() + "");
		}
		if ("端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getPort());
		}
		if ("CCM发送使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getCcmSendEnable() + "");
		}
		if ("CCM接受使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getCcmReceiveEnable() + "");
		}
		if ("CCM优先级".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getCcmPriority());
		}
		if ("LBM数据TLV类型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getLbmTlvType() + "");
		}
		if ("LBM数据TLV长度".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getLbmTlvTypeLength());
		}
		if ("LBM优先级".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getLbmPriority());
		}
		if ("LBM丢弃适用性".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getLbmDiscard() + "");
		}
		if ("LTM优先级".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getLtmPriority());
		}
		if ("AIS发送使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getAisSendEnabel() + "");
		}
		if ("客户MD Level".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getClientMdLevel());
		}
		if ("AIS优先级".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getAisPriority());
		}
		if ("LCK发送使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getLckSendEnabel() + "");
		}
		if ("LCK优先级".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getLckPriority());
		}
		if ("AIS/LCK发送周期".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getAisLckSendCycle() + "");
		}
		if ("TST发送使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getTstSendEnabel() + "");
		}
		if ("TST发送MDLevel".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getTstSendLevel());
		}
		if ("TST目的MEP MAC1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getTstPurposeMepMac().split("-")[0]);
		}
		if ("TST目的MEP MAC2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getTstPurposeMepMac().split("-")[1]);
		}
		if ("TST目的MEP MAC3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getTstPurposeMepMac().split("-")[2]);
		}
		if ("TST目的MEP MAC4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getTstPurposeMepMac().split("-")[3]);
		}
		if ("TST目的MEP MAC5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getTstPurposeMepMac().split("-")[4]);
		}
		if ("TST目的MEP MAC6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getTstPurposeMepMac().split("-")[5]);
		}
		if ("TST优先级".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getTstPriority());
		}
		if ("发送方式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getSendWay() + "");
		}
		if ("TST丢弃适用性".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getTstDiscard() + "");
		}
		if ("TST数据TLV类型".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getTstTlvType() + "");
		}
		if ("TST数据TLV长度".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getTstTlvLength());
		}
		if ("TST发送周期".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getTstSendCycle() + "");
		}
		if ("LM使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getLmENable() + "");
		}
		if ("LM优先级".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getLmPriority());
		}
		if ("LM发送周期".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getLmSendCycle() + "");
		}
		if ("DM使能".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getDmENable() + "");
		}
		if ("DM优先级".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getDmPriority());
		}
		if ("DM发送周期".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getDmSendCycle() + "");
		}
		if ("Remote MEP ID 1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getRemoteMepId1());
		}
		if ("MAC 1地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress1().split("-")[0]);
		}
		if ("MAC 1地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress1().split("-")[1]);
		}
		if ("MAC 1地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress1().split("-")[2]);
		}
		if ("MAC 1地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress1().split("-")[3]);
		}
		if ("MAC 1地址5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress1().split("-")[4]);
		}
		if ("MAC 1地址6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress1().split("-")[5]);
		}
		if ("Remote MEP ID 2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getRemoteMepId2());
		}
		if ("MAC 2地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress2().split("-")[0]);
		}
		if ("MAC 2地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress2().split("-")[1]);
		}
		if ("MAC 2地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress2().split("-")[2]);
		}
		if ("MAC 2地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress2().split("-")[3]);
		}
		if ("MAC 2地址5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress2().split("-")[4]);
		}
		if ("MAC 2地址6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress2().split("-")[5]);
		}
		if ("Remote MEP ID 3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getRemoteMepId3());
		}
		if ("MAC 3地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress3().split("-")[0]);
		}
		if ("MAC 3地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress3().split("-")[1]);
		}
		if ("MAC 3地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress3().split("-")[2]);
		}
		if ("MAC 3地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress3().split("-")[3]);
		}
		if ("MAC 3地址5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress3().split("-")[4]);
		}
		if ("MAC 3地址6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress3().split("-")[5]);
		}
		if ("Remote MEP ID 4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getRemoteMepId4());
		}
		if ("MAC 4地址1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress4().split("-")[0]);
		}
		if ("MAC 4地址2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress4().split("-")[1]);
		}
		if ("MAC 4地址3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress4().split("-")[2]);
		}
		if ("MAC 4地址4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress4().split("-")[3]);
		}
		if ("MAC 4地址5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress4().split("-")[4]);
		}
		if ("MAC 4地址6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMacAddress4().split("-")[5]);
		}
		if ("MIP生成规则".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMipCreate() + "");
		}
		if ("MIP槽位号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMipSlot() + "");
		}
		if ("MIP端口号".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(ethOamInfo.getMipPort());
		}
	}

	/**
	 * 生成43位MD name/ma name
	 * 
	 * @param meg
	 * @return
	 */
	public String getName(String meg) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i <meg.length(); i++) {
			char c = meg.charAt(i);
			int b = c;
			if (i == 42) {
				str.append(b);
			} else {
				str.append(b + ",");
			}
		}
		while (str.toString().split(",").length < 43) {
			str.append("0,");
		}
		return str.toString();
	}
	/**
	 *将String转成char 在转成String 
	 * @param str
	 * @return
	 */
}
