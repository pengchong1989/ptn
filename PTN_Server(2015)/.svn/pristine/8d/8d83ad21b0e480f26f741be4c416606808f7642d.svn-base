package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.CccObject;
import com.nms.drive.service.bean.CccUNIObject;
import com.nms.drive.service.bean.VpwsBuffer;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisCccTable extends AnalysisBase {
	private String cccPath = "com/nms/drive/analysis/xmltool/file/CCC_sub.xml";
	private String cccUNIPath = "com/nms/drive/analysis/xmltool/file/CCC_(Lan)成员端口.xml";
	private String vpwsPath = "com/nms/drive/analysis/xmltool/file/CCC_流数目.xml";
	
	/**
	 * 根据Ccc生成命令
	 * 
	 * @param eTreeList
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisCccToCommand(List<CccObject> cccList, String configXml) throws Exception {
		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			// 设置条目数
			driveRoot_config.getDriveAttributeList().get(0).setValue(cccList.size() + "");
			// 清除自带的listRoot
			driveRoot_config.getDriveAttributeList().get(0).getListRootList().clear();

			CccObject cccObject = null;

			ListRoot cccListRoot = null;
			ListRoot cccUNIListRoot = null;
			ListRoot vpwsListRoot = null;
			

			List<CccUNIObject> cccUNIList = null;
			List<VpwsBuffer> vpwsList = null;
			

			for (int i = 0; i < cccList.size(); i++) {

				cccObject = cccList.get(i);
				cccUNIList = cccObject.getCccUNIObjectList();
				List<ListRoot> cccUNIListRootList = new ArrayList<ListRoot>();// 建立eTreeUNIListRoot的集合
				for (int j = 0; j < cccUNIList.size(); j++) {
					CccUNIObject cccUNIObject = cccUNIList.get(j);

					vpwsList = cccUNIObject.getVpwsBufferList();

					List<ListRoot> vpwsListRootList = new ArrayList<ListRoot>();// 建立vpwsListRoot的集合
					for (int k = 0; k < vpwsList.size(); k++) {
						VpwsBuffer vpwsBuffer = vpwsList.get(k);
						// 生成vpwsListRoot
						vpwsListRoot = getVpwsBufferListRoot(vpwsBuffer, vpwsPath);

						vpwsListRootList.add(vpwsListRoot);// 添加vpwsListRoot入集合
					}
					// 生成treeUNIListRoot
					cccUNIListRoot = getCccUNIListRoot(cccObject,cccUNIObject, cccUNIPath, vpwsListRootList);

					cccUNIListRootList.add(cccUNIListRoot);// 添加cccUNIListRoot入集合
				}

				
				// 生成treeListRoot
				cccListRoot = getCccListRoot(cccObject, cccPath, cccUNIListRootList);

				// 生成最终需要处理的DriveRoot对象
				driveRoot_config.getDriveAttributeList().get(0).getListRootList().add(cccListRoot);
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] commands = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
			CoderUtils.print16String(commands);
			return commands;

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			throw e;
		}

	}

	/**
	 * 根据命令生成CccObject对象的集合
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	public List<CccObject> analysisCommandsToCccObject(byte[] commands, String configXml) {
		List<CccObject> cccObjList = new ArrayList<CccObject>();		
		// 起始长度
		int start = 0;
		// 条目数
		byte[] counts = { 0x00, 0x00, 0, commands[start]};
		int count = CoderUtils.bytesToInt(counts);
		int pointer = 0; // 当前commands集合的下标
		byte[] cccTiaomuCommands = new byte[1];// 存放条目数的集合
		byte[] cccCommands = new byte[8];// 存放eTreeObj命令的集合
		byte[] cccUNICommands = new byte[40];// 存放eTreeUNIObj命令的集合
		byte[] vpwsCommands = new byte[60];// 存放vpwsObject命令的集合
		

		pointer = start;// 移动下标获取条目数

		System.arraycopy(commands, pointer, cccTiaomuCommands, 0, cccTiaomuCommands.length);
		pointer += cccTiaomuCommands.length;
		int cccUNICount = 0;
		int vpwsCount = 0;		
		CccObject cccObject = null;
		CccUNIObject cccUNIObject = null;
		VpwsBuffer vpwsBuffer = null;
		
		try {
			for (int i = 0; i < count; i++) {// 对条目数循环，得出ETreeObj

				cccObject = new CccObject();
				// 将下标移动至treeUNIObj部分
				byte[] cccObjCommands_1 = new byte[6];
				System.arraycopy(commands, pointer, cccObjCommands_1, 0, cccObjCommands_1.length);
				pointer += 6;

				byte[] cccObjCommands_2 = new byte[1];
				System.arraycopy(commands, pointer, cccObjCommands_2, 0, cccObjCommands_2.length);
				pointer += 1;

				cccUNICount = CoderUtils.bytesToInt(cccObjCommands_2[0]);// 获取treeUNICount
				for (int k = 0; k < cccUNICount; k++) {
					System.arraycopy(commands, pointer, cccUNICommands, 0, cccUNICommands.length);
					pointer += cccUNICommands.length;// 移动下标
					cccUNIObject = getCccUNIObj(cccObject,cccUNICommands, cccUNIPath);

					vpwsCount = cccUNIObject.getVpwsBufferCount();
					for (int l = 0; l < vpwsCount; l++) {
						System.arraycopy(commands, pointer, vpwsCommands, 0, vpwsCommands.length);
						pointer += vpwsCommands.length;// 移动下标
						vpwsBuffer = getVpwsBufferObj(vpwsCommands, vpwsPath);
						cccUNIObject.getVpwsBufferList().add(vpwsBuffer);
					}

					cccObject.getCccUNIObjectList().add(cccUNIObject);// 将treeUNIObj添加入eTreeUNIList集合
				}
				cccCommands = CoderUtils.arraycopy(cccObjCommands_1, cccObjCommands_2);

				cccObject = getCccObj(cccObject, cccCommands, cccPath);				
				cccObjList.add(cccObject);

			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return cccObjList;

	}

	/**
	 * 获取eTreelistRoot
	 * 
	 * @param eTreeObj
	 * @param vplsPath
	 * @return
	 */
	private ListRoot getCccListRoot(CccObject cccObj, String cccPath, List<ListRoot> cccUNIListRootList) {
		ListRoot listRoot = new ListRoot();
		try {
			DriveRoot driverRoot = super.LoadDriveXml(cccPath);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driverRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driverRoot.getDriveAttributeList().get(i);
				if ("VS ID".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccObj.getVplsId() + "");
				}
				if ("使能".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccObj.getEnable() + "");
				}
				if ("MAC地址表容量".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccObj.getMacCount() + "");
				}
				if ("(LAN口成员列表)成员端口数".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccObj.getCccUNIObjectList().size() + "");
					driveAttribute.getListRootList().clear();// 清除自带的listRoot
					// 添加cccUNIListRoot
					for (int j = 0; j < cccUNIListRootList.size(); j++) {
						driveAttribute.getListRootList().add(cccUNIListRootList.get(j));
					}
				}				
			}
			listRoot.setDriveAttributeList(driverRoot.getDriveAttributeList());
		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		return listRoot;

	}

	/**
	 * 获取(LAN口)端口的ListRoot对象
	 * 
	 * @param treeUNIObject
	 * @param eTreeUNIXml
	 * @return
	 */
	private ListRoot getCccUNIListRoot(CccObject cccObject,CccUNIObject cccUNIObject, String cccUNIXml, List<ListRoot> vpwsListRootList) {
		ListRoot listRoot = new ListRoot();
		try {
			DriveRoot driveRoot = super.LoadDriveXml(cccUNIXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("槽位号".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getSlot() + "");
				}
				if ("端口号".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getPort() + "");
				}
				if ("LAN口-ID".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getLanPortId() + "");
				}
				
				if ("(端口)水平分割".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getPortSplitHorizon() + "");
				}
				if ("TMC流量监管使能".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getVCTrafficPolicing() + "");
				}
				if ("(端口)模式".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getModel() + "");
				}
				if ("(端口)CIR".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getCir() + "");
				}
				if ("(端口)PIR".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getPir() + "");
				}
				if ("(端口)CM".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getCm() + "");
				}
				if ("(端口)CBS".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getCbs() + "");
				}
				if ("(端口)PBS".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getPbs() + "");
				}
				if ("(端口)上话TAG识别".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getUpTagRecognition() + "");
				}
				if ("(端口)下话TAG行为".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getDownTagBehavior() + "");
				}
				if ("(端口)下话增加VLAN ID".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getDownTagValnId() + "");
				}
				if ("(端口)下话增加VLAN PRI".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getDownTagValnPri() + "");
				}
				if ("(端口)MAC地址学习".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getMacAddresslearn() + "");
				}
				if ("流数目".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(cccUNIObject.getVpwsBufferList().size() + "");

					driveAttribute.getListRootList().clear();// 清除自带的listRoot

					for (int j = 0; j < vpwsListRootList.size(); j++) {

						driveAttribute.getListRootList().add(vpwsListRootList.get(j));// 添加vpwsListRoot入UNIListRoot
					}

				}
			}
			listRoot.setDriveAttributeList(driveRoot.getDriveAttributeList());
			// listRoot.getDriveAttributeList().addAll(driveRoot.getDriveAttributeList());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		return listRoot;
	}

	/**
	 * 获取流数目的ListRoot对象
	 * 
	 * @param vpwsBuffer
	 * @param vpwsXml
	 * @return
	 */
	private ListRoot getVpwsBufferListRoot(VpwsBuffer vpwsBuffer, String vpwsXml) {
		ListRoot listRoot = new ListRoot();
		try {

			String[] sourceMacs = vpwsBuffer.getSourceMac().split("-");// 源MAC地址
			String[] targetMacs = vpwsBuffer.getTargetMac().split("-");// 目的MAC地址
			String[] sourceIPs = vpwsBuffer.getSourceIP().split("\\.");// 源IP地址
			// 16进制转化10进制
			for (int i = 0; i < sourceMacs.length; i++) {
				sourceMacs[i] = Integer.parseInt(sourceMacs[i], 16) + "";
			}
			for (int i = 0; i < targetMacs.length; i++) {
				targetMacs[i] = Integer.parseInt(targetMacs[i], 16) + "";
			}

			if (sourceIPs.length == 4) {// Ipv4
				sourceIPs = new String[] { null, null, sourceIPs[0], sourceIPs[1], sourceIPs[2], sourceIPs[3] };
			}

			String[] targetIPs = vpwsBuffer.getTargetIP().split("\\.");// 目的IP地址
			if (targetIPs.length == 1) {// targetIp为空
				targetIPs = new String[] { null, null, null, null, null, null };
			}
			if (targetIPs.length == 4) { // Ipv4
				targetIPs = new String[] { null, null, targetIPs[0], targetIPs[1], targetIPs[2], targetIPs[3] };
			}

			DriveRoot driveRoot = super.LoadDriveXml(vpwsXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("流ID".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(vpwsBuffer.getVpwsBufferID() + "");
				}
				if ("流使能".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(vpwsBuffer.getBufferEnable() + "");
				}
				if ("PW标签".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(vpwsBuffer.getPwLable() + "");
				}
				if ("VLAN ID".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(vpwsBuffer.getVlanId() + "");
				}
				if ("源MAC地址1".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(sourceMacs[0]);
				}
				if ("源MAC地址2".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(sourceMacs[1]);
				}
				if ("源MAC地址3".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(sourceMacs[2]);
				}
				if ("源MAC地址4".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(sourceMacs[3]);
				}
				if ("源MAC地址5".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(sourceMacs[4]);
				}
				if ("源MAC地址6".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(sourceMacs[5]);
				}
				if ("目的MAC地址1".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(targetMacs[0]);
				}
				if ("目的MAC地址2".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(targetMacs[1]);
				}
				if ("目的MAC地址3".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(targetMacs[2]);
				}
				if ("目的MAC地址4".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(targetMacs[3]);
				}
				if ("目的MAC地址5".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(targetMacs[4]);
				}
				if ("目的MAC地址6".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(targetMacs[5]);
				}
				if ("802.1P".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(vpwsBuffer.get_802_1P() + "");
				}
				if ("源IP地址1".equals(driveAttribute.getDescription())) {
					if (sourceIPs[0] != null) {
						driveAttribute.setValue(sourceIPs[0]);
					}
				}
				if ("源IP地址2".equals(driveAttribute.getDescription())) {
					if (sourceIPs[1] != null) {
						driveAttribute.setValue(sourceIPs[1]);
					}
				}
				if ("源IP地址3".equals(driveAttribute.getDescription())) {
					if (sourceIPs[2] != null) {
						driveAttribute.setValue(sourceIPs[2]);
					}
				}
				if ("源IP地址4".equals(driveAttribute.getDescription())) {
					if (sourceIPs[3] != null) {
						driveAttribute.setValue(sourceIPs[3]);
					}
				}
				if ("源IP地址5".equals(driveAttribute.getDescription())) {
					if (sourceIPs[4] != null) {
						driveAttribute.setValue(sourceIPs[4]);
					}
				}
				if ("源IP地址6".equals(driveAttribute.getDescription())) {
					if (sourceIPs[5] != null) {
						driveAttribute.setValue(sourceIPs[5]);
					}
				}
				if ("目的IP地址1".equals(driveAttribute.getDescription())) {
					if (targetIPs[0] != null) {
						driveAttribute.setValue(targetIPs[0]);

					}
				}
				if ("目的IP地址2".equals(driveAttribute.getDescription())) {
					if (targetIPs[1] != null) {
						driveAttribute.setValue(targetIPs[1]);

					}
				}
				if ("目的IP地址3".equals(driveAttribute.getDescription())) {
					if (targetIPs[2] != null) {
						driveAttribute.setValue(targetIPs[2]);

					}
				}
				if ("目的IP地址4".equals(driveAttribute.getDescription())) {
					if (targetIPs[3] != null) {
						driveAttribute.setValue(targetIPs[3]);

					}
				}
				if ("目的IP地址5".equals(driveAttribute.getDescription())) {
					if (targetIPs[4] != null) {
						driveAttribute.setValue(targetIPs[4]);
					}
				}
				if ("目的IP地址6".equals(driveAttribute.getDescription())) {
					if (targetIPs[5] != null) {
						driveAttribute.setValue(targetIPs[5]);
					}
				}
				if ("IP DSCP".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(vpwsBuffer.getIpDscp() + "");
				}
				if ("(流)模式".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(vpwsBuffer.getModel() + "");
				}
				if ("(流)CIR".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(vpwsBuffer.getCir() + "");
				}
				if ("(流)PIR".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(vpwsBuffer.getPir() + "");
				}
				if ("(流)CM".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(vpwsBuffer.getCm() + "");
				}
				if ("(流)CBS".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(vpwsBuffer.getCbs() + "");
				}
				if ("(流)PBS".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(vpwsBuffer.getPbs() + "");
				}
				if ("策略".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(vpwsBuffer.getStrategy() + "");
				}
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
			listRoot.setDriveAttributeList(driveRoot.getDriveAttributeList());
			// listRoot.getDriveAttributeList().addAll(driveRoot.getDriveAttributeList());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return listRoot;
	}


	/**
	 * 根据命令生成CccObj对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private CccObject getCccObj(CccObject cccObject, byte[] commands, String configXml) {

		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("VS ID".equals(driveAttribute.getDescription())) {
					cccObject.setVplsId(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("使能".equals(driveAttribute.getDescription())) {
					cccObject.setEnable(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("MAC地址表容量".equals(driveAttribute.getDescription())) {
					cccObject.setMacCount(Integer.parseInt(driveAttribute.getValue()));
				}
				
				if ("(LAN口成员列表)成员端口数".equals(driveAttribute.getDescription())) {
					cccObject.setCccUNIObjCount(Integer.parseInt(driveAttribute.getValue()));
				}

			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return cccObject;
	}

	/**
	 * 根据命令获取ETreeUNIObj对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private CccUNIObject getCccUNIObj(CccObject cccObject,byte[] commands, String configXml) {
		CccUNIObject cccUNIObject = new CccUNIObject();

		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("槽位号".equals(driveAttribute.getDescription())) {
					cccUNIObject.setSlot(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("端口号".equals(driveAttribute.getDescription())) {
					cccUNIObject.setPort(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("LAN口-ID".equals(driveAttribute.getDescription())) {
					cccUNIObject.setLanPortId(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)水平分割".equals(driveAttribute.getDescription())) {
					cccUNIObject.setPortSplitHorizon(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("TMC流量监管使能".equals(driveAttribute.getDescription())) {
					cccUNIObject.setVCTrafficPolicing(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)模式".equals(driveAttribute.getDescription())) {
					cccUNIObject.setModel(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)CIR".equals(driveAttribute.getDescription())) {
					cccUNIObject.setCir(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)PIR".equals(driveAttribute.getDescription())) {
					cccUNIObject.setPir(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)CM".equals(driveAttribute.getDescription())) {
					cccUNIObject.setCm(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)CBS".equals(driveAttribute.getDescription())) {
					cccUNIObject.setCbs(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)PBS".equals(driveAttribute.getDescription())) {
					cccUNIObject.setPbs(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)上话TAG识别".equals(driveAttribute.getDescription())) {
					cccUNIObject.setUpTagRecognition(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)下话TAG行为".equals(driveAttribute.getDescription())) {
					cccUNIObject.setDownTagBehavior(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)下话增加VLAN ID".equals(driveAttribute.getDescription())) {
					cccUNIObject.setDownTagValnId(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)下话增加VLAN PRI".equals(driveAttribute.getDescription())) {
					cccUNIObject.setDownTagValnPri(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)MAC地址学习".equals(driveAttribute.getDescription())) {
					cccUNIObject.setMacAddresslearn(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("流数目".equals(driveAttribute.getDescription())) {
					cccUNIObject.setVpwsBufferCount(Integer.parseInt(driveAttribute.getValue()));
				}
			}
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}

		return cccUNIObject;

	}

	/**
	 * 根据命令获取VpwsBufferObject对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private VpwsBuffer getVpwsBufferObj(byte[] commands, String configXml) {
		VpwsBuffer vpwsBuffer = new VpwsBuffer();

		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			DriveAttribute driveAttribute = null;
			StringBuffer sourceMacBuffer = new StringBuffer();
			StringBuffer targetMacBuffer = new StringBuffer();
			StringBuffer sourceIPBuffer = new StringBuffer();
			StringBuffer targetIPBuffer = new StringBuffer();
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("流ID".equals(driveAttribute.getDescription())) {
					vpwsBuffer.setVpwsBufferID(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("流使能".equals(driveAttribute.getDescription())) {
					vpwsBuffer.setBufferEnable(Integer.parseInt(driveAttribute.getValue()));
				}				
				if ("VLAN ID".equals(driveAttribute.getDescription())) {
					vpwsBuffer.setVlanId(Integer.parseInt(driveAttribute.getValue()));
				}
				String mac = "";
				try{
					mac = Integer.toHexString(Integer.parseInt(driveAttribute.getValue()));
					if(mac.length() == 1){
						mac = "0"+mac;
					}
				}catch(Exception e){
				}
				if ("源MAC地址1".equals(driveAttribute.getDescription())) {
					sourceMacBuffer.append(mac + "-");
				}
				if ("源MAC地址2".equals(driveAttribute.getDescription())) {
					sourceMacBuffer.append(mac + "-");
				}
				if ("源MAC地址3".equals(driveAttribute.getDescription())) {
					sourceMacBuffer.append(mac + "-");
				}
				if ("源MAC地址4".equals(driveAttribute.getDescription())) {
					sourceMacBuffer.append(mac + "-");
				}
				if ("源MAC地址5".equals(driveAttribute.getDescription())) {
					sourceMacBuffer.append(mac + "-");
				}
				if ("源MAC地址6".equals(driveAttribute.getDescription())) {
					sourceMacBuffer.append(mac);
					vpwsBuffer.setSourceMac(sourceMacBuffer.toString());// 源MAC地址赋值
				}
				if ("目的MAC地址1".equals(driveAttribute.getDescription())) {
					targetMacBuffer.append(mac + "-");
				}
				if ("目的MAC地址2".equals(driveAttribute.getDescription())) {
					targetMacBuffer.append(mac + "-");
				}
				if ("目的MAC地址3".equals(driveAttribute.getDescription())) {
					targetMacBuffer.append(mac + "-");
				}
				if ("目的MAC地址4".equals(driveAttribute.getDescription())) {
					targetMacBuffer.append(mac + "-");
				}
				if ("目的MAC地址5".equals(driveAttribute.getDescription())) {
					targetMacBuffer.append(mac + "-");
				}
				if ("目的MAC地址6".equals(driveAttribute.getDescription())) {
					targetMacBuffer.append(mac);
					vpwsBuffer.setTargetMac(targetMacBuffer.toString());// 目的MAC地址赋值
				}
				if ("802.1P".equals(driveAttribute.getDescription())) {
					vpwsBuffer.set_802_1P(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("源IP地址1".equals(driveAttribute.getDescription())) {
					sourceIPBuffer.append("");
				}
				if ("源IP地址2".equals(driveAttribute.getDescription())) {
					sourceIPBuffer.append("");
				}
				if ("源IP地址3".equals(driveAttribute.getDescription())) {
					sourceIPBuffer.append(driveAttribute.getValue()).append(".");
				}
				if ("源IP地址4".equals(driveAttribute.getDescription())) {
					sourceIPBuffer.append(driveAttribute.getValue()).append(".");
				}
				if ("源IP地址5".equals(driveAttribute.getDescription())) {
					sourceIPBuffer.append(driveAttribute.getValue()).append(".");
				}
				if ("源IP地址6".equals(driveAttribute.getDescription())) {
					sourceIPBuffer.append(driveAttribute.getValue());
					vpwsBuffer.setSourceIP(sourceIPBuffer.toString());// 源Ip地址赋值
				}
				if ("目的IP地址1".equals(driveAttribute.getDescription())) {
					targetIPBuffer.append("");
				}
				if ("目的IP地址2".equals(driveAttribute.getDescription())) {
					targetIPBuffer.append("");
				}
				if ("目的IP地址3".equals(driveAttribute.getDescription())) {
					targetIPBuffer.append(driveAttribute.getValue()).append(".");
				}
				if ("目的IP地址4".equals(driveAttribute.getDescription())) {
					targetIPBuffer.append(driveAttribute.getValue()).append(".");
				}
				if ("目的IP地址5".equals(driveAttribute.getDescription())) {
					targetIPBuffer.append(driveAttribute.getValue()).append(".");
				}
				if ("目的IP地址6".equals(driveAttribute.getDescription())) {
					targetIPBuffer.append(driveAttribute.getValue());
					vpwsBuffer.setTargetIP(targetIPBuffer.toString());// 目的IP地址赋值
				}
				if ("IP DSCP".equals(driveAttribute.getDescription())) {
					vpwsBuffer.setIpDscp(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(流)模式".equals(driveAttribute.getDescription())) {
					vpwsBuffer.setModel(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(流)CIR".equals(driveAttribute.getDescription())) {
					vpwsBuffer.setCir(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(流)PIR".equals(driveAttribute.getDescription())) {
					vpwsBuffer.setPir(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(流)CM".equals(driveAttribute.getDescription())) {
					vpwsBuffer.setCm(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(流)CBS".equals(driveAttribute.getDescription())) {
					vpwsBuffer.setCbs(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(流)PBS".equals(driveAttribute.getDescription())) {
					vpwsBuffer.setPbs(Integer.parseInt(driveAttribute.getValue()));
				}

				if ("策略".equals(driveAttribute.getDescription())) {
					vpwsBuffer.setStrategy(Integer.parseInt(driveAttribute.getValue()));
				}

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
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		return vpwsBuffer;
	}	
}
