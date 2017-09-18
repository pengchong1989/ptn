package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.ETreeNNIObject;
import com.nms.drive.service.bean.ETreeObject;
import com.nms.drive.service.bean.ETreeUNIObject;
import com.nms.drive.service.bean.VpwsBuffer;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisETreeTable extends AnalysisBase {
	private String vplsPath = "com/nms/drive/analysis/xmltool/file/VPLS-VS_sub.xml";
	private String eTreeUNIPath = "com/nms/drive/analysis/xmltool/file/VPLS-VS_(Lan)成员端口.xml";
	private String vpwsPath = "com/nms/drive/analysis/xmltool/file/VPLS-VS_流数目.xml";
	private String eTreeNNiPath = "com/nms/drive/analysis/xmltool/file/VPLS-VS_(仿Lan)成员端口.xml";

	/**
	 * 根据ETree生成命令
	 * 
	 * @param eTreeList
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisETreeToCommand(List<ETreeObject> eTreeList, String configXml) throws Exception {
		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			// 设置条目数
			driveRoot_config.getDriveAttributeList().get(0).setValue(eTreeList.size() + "");
			// 清除自带的listRoot
			driveRoot_config.getDriveAttributeList().get(0).getListRootList().clear();

			ETreeObject treeObject = null;

			ListRoot eTreeListRoot = null;
			ListRoot treeUNIListRoot = null;
			ListRoot vpwsListRoot = null;
			ListRoot eTreeNNIListRoot = null;

			List<ETreeUNIObject> eTreeUNIList = null;
			List<VpwsBuffer> vpwsList = null;
			List<ETreeNNIObject> eTreeNNIList = null;

			for (int i = 0; i < eTreeList.size(); i++) {

				treeObject = eTreeList.get(i);
				eTreeUNIList = treeObject.getETreeUNIObjectList();
				List<ListRoot> eTreeUNIListRootList = new ArrayList<ListRoot>();// 建立eTreeUNIListRoot的集合
				for (int j = 0; j < eTreeUNIList.size(); j++) {
					ETreeUNIObject treeUNIObject = eTreeUNIList.get(j);

					vpwsList = treeUNIObject.getVpwsBufferList();

					List<ListRoot> vpwsListRootList = new ArrayList<ListRoot>();// 建立vpwsListRoot的集合
					for (int k = 0; k < vpwsList.size(); k++) {
						VpwsBuffer vpwsBuffer = vpwsList.get(k);
						// 生成vpwsListRoot
						vpwsListRoot = getVpwsBufferListRoot(vpwsBuffer, vpwsPath);

						vpwsListRootList.add(vpwsListRoot);// 添加vpwsListRoot入集合
					}
					// 生成treeUNIListRoot
					treeUNIListRoot = getETreeUNIListRoot(treeObject,treeUNIObject, eTreeUNIPath, vpwsListRootList);

					eTreeUNIListRootList.add(treeUNIListRoot);// 添加eTreeUNIListRoot入集合
				}

				// eTreeNNIListRoot = new ListRoot();
				eTreeNNIList = treeObject.getETreeNNIObjectList();
				List<ListRoot> eTreeNNIListRootList = new ArrayList<ListRoot>();// 建立eTreeNNIListRoot的集合
				for (int j = 0; j < eTreeNNIList.size(); j++) {
					ETreeNNIObject treeNNIObject = eTreeNNIList.get(j);
					// 生成treeUNIListRoot
					eTreeNNIListRoot = getETreeNNIListRoot(treeNNIObject, eTreeNNiPath);

					eTreeNNIListRootList.add(eTreeNNIListRoot);// 添加eTreeNNIListRoot入集合

				}
				// 生成treeListRoot
				eTreeListRoot = getETreeListRoot(treeObject, vplsPath, eTreeUNIListRootList, eTreeNNIListRootList);

				// 生成最终需要处理的DriveRoot对象
				driveRoot_config.getDriveAttributeList().get(0).getListRootList().add(eTreeListRoot);
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] commands = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
			return commands;

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			throw e;
		}

	}

	/**
	 * 根据命令生成ETreeObject对象的集合
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	public List<ETreeObject> analysisCommandsToETreeObject(byte[] commands, String configXml) {
		List<ETreeObject> eTreeObjList = new ArrayList<ETreeObject>();
		int NEhead = 5;// NE头长度
		int controlPanelHead = 101;// 盘头长度
		int dataBlockHead = 25;// 配置块头信息长度

		// 起始长度
		int start = 0;
		// 条目数
		byte[] counts = { 0x00, 0x00, 0, commands[start]};
		int count = CoderUtils.bytesToInt(counts);
		int pointer = 0; // 当前commands集合的下标
		byte[] eTreeTiaomuCommands = new byte[1];// 存放条目数的集合
		byte[] eTreeCommands = new byte[8];// 存放eTreeObj命令的集合
		byte[] eTreeUNICommands = new byte[40];// 存放eTreeUNIObj命令的集合
		byte[] vpwsCommands = new byte[60];// 存放vpwsObject命令的集合
		byte[] eTreeNNICommands = new byte[90];// 存放eTreeNNIObj命令的集合

		pointer = start;// 移动下标获取条目数

		System.arraycopy(commands, pointer, eTreeTiaomuCommands, 0, eTreeTiaomuCommands.length);
		pointer += eTreeTiaomuCommands.length;

		int tiaomuCount = 0;
		int treeUNICount = 0;
		int vpwsCount = 0;
		int treeNNICount = 0;

		ETreeObject treeObject = null;
		ETreeUNIObject treeUNIObject = null;
		VpwsBuffer vpwsBuffer = null;
		ETreeNNIObject treeNNIObject = null;
		try {
			for (int i = 0; i < count; i++) {// 对条目数循环，得出ETreeObj

				treeObject = new ETreeObject();
				// 将下标移动至treeUNIObj部分
				byte[] treeObjCommands_1 = new byte[6];
				System.arraycopy(commands, pointer, treeObjCommands_1, 0, treeObjCommands_1.length);
				pointer += 6;

				byte[] treeObjCommands_2 = new byte[1];
				System.arraycopy(commands, pointer, treeObjCommands_2, 0, treeObjCommands_2.length);
				pointer += 1;

				treeUNICount = CoderUtils.bytesToInt(treeObjCommands_2[0]);// 获取treeUNICount
				for (int k = 0; k < treeUNICount; k++) {
					System.arraycopy(commands, pointer, eTreeUNICommands, 0, eTreeUNICommands.length);
					pointer += eTreeUNICommands.length;// 移动下标
					treeUNIObject = getETreeUNIObj(treeObject,eTreeUNICommands, eTreeUNIPath);

					vpwsCount = treeUNIObject.getVpwsBufferCount();
					for (int l = 0; l < vpwsCount; l++) {
						System.arraycopy(commands, pointer, vpwsCommands, 0, vpwsCommands.length);
						pointer += vpwsCommands.length;// 移动下标
						vpwsBuffer = getVpwsBufferObj(vpwsCommands, vpwsPath);
						treeUNIObject.getVpwsBufferList().add(vpwsBuffer);
					}

					treeObject.getETreeUNIObjectList().add(treeUNIObject);// 将treeUNIObj添加入eTreeUNIList集合
				}
				byte[] eTreeCommands_3 = new byte[1];
				System.arraycopy(commands, pointer, eTreeCommands_3, 0, eTreeCommands_3.length);
				pointer += eTreeCommands_3.length;

				eTreeCommands = CoderUtils.arraycopy(treeObjCommands_1, treeObjCommands_2);
				eTreeCommands = CoderUtils.arraycopy(eTreeCommands, eTreeCommands_3);

				treeObject = getETreeObj(treeObject, eTreeCommands, vplsPath);

				treeNNICount = treeObject.getETreeNNIObjCount();// 获取treeNNICount
				for (int k = 0; k < treeNNICount; k++) {
					System.arraycopy(commands, pointer, eTreeNNICommands, 0, eTreeNNICommands.length);
					pointer += eTreeNNICommands.length;
					treeNNIObject = getETreeNNIObj(eTreeNNICommands, eTreeNNiPath);

					treeObject.getETreeNNIObjectList().add(treeNNIObject);
				}

				eTreeObjList.add(treeObject);

			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return eTreeObjList;

	}

	/**
	 * 获取eTreelistRoot
	 * 
	 * @param eTreeObj
	 * @param vplsPath
	 * @return
	 */
	private ListRoot getETreeListRoot(ETreeObject eTreeObj, String vplsPath, List<ListRoot> treeUNIListRootList, List<ListRoot> treeNNIListRootList) {
		ListRoot listRoot = new ListRoot();
		try {
			DriveRoot driverRoot = super.LoadDriveXml(vplsPath);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driverRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driverRoot.getDriveAttributeList().get(i);
				if ("VS ID".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(eTreeObj.getVplsId() + "");
				}
				if ("使能".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(eTreeObj.getEnable() + "");
				}
				if ("MAC地址表容量".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(eTreeObj.getMacCount() + "");
				}else if ("角色".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(eTreeObj.getRole() + "");
				}
				if ("(LAN口成员列表)成员端口数".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(eTreeObj.getETreeUNIObjectList().size() + "");

					driveAttribute.getListRootList().clear();// 清除自带的listRoot

					// driveAttribute.getListRootList().add(treeUNIListRoot);//
					// 添加treeUNIListRoot
					for (int j = 0; j < treeUNIListRootList.size(); j++) {
						driveAttribute.getListRootList().add(treeUNIListRootList.get(j));
					}
				}
				if ("(仿真LAN口组)成员端口数".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(eTreeObj.getETreeNNIObjectList().size() + "");

					driveAttribute.getListRootList().clear();// 清除自带的listRoot

					// driveAttribute.getListRootList().add(treeNNIListRoot);//
					// 添加treeNNIListRoot
					for (int j = 0; j < treeNNIListRootList.size(); j++) {
						driveAttribute.getListRootList().add(treeNNIListRootList.get(j));
					}
				}

			}
			listRoot.setDriveAttributeList(driverRoot.getDriveAttributeList());
			// listRoot.getDriveAttributeList().addAll((driverRoot.getDriveAttributeList()));
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
	private ListRoot getETreeUNIListRoot(ETreeObject eTreeObject,ETreeUNIObject treeUNIObject, String eTreeUNIXml, List<ListRoot> vpwsListRootList) {
		ListRoot listRoot = new ListRoot();
		try {
			DriveRoot driveRoot = super.LoadDriveXml(eTreeUNIXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("槽位号".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getSlot() + "");
				}
				if ("端口号".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getPort() + "");
				}
				if ("LAN口-ID".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getLanPortId() + "");
				}
				if ("业务类型".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(eTreeObject.getType() + "");
				}
				if ("(端口)水平分割".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getPortSplitHorizon() + "");
				}
				if ("VC流量监管使能".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getVCTrafficPolicing() + "");
				}
				if ("(端口)模式".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getModel() + "");
				}
				if ("(端口)CIR".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getCir() + "");
				}
				if ("(端口)PIR".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getPir() + "");
				}
				if ("(端口)CM".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getCm() + "");
				}
				if ("(端口)CBS".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getCbs() + "");
				}
				if ("(端口)PBS".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getPbs() + "");
				}
				if ("(端口)上话TAG识别".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getUpTagRecognition() + "");
				}
				if ("(端口)下话TAG行为".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getDownTagBehavior() + "");
				}
				if ("(端口)下话增加VLAN ID".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getDownTagValnId() + "");
				}
				if ("(端口)下话增加VLAN PRI".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getDownTagValnPri() + "");
				}
				if ("(端口)MAC地址学习".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getMacAddresslearn() + "");
				}
				if ("流数目".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeUNIObject.getVpwsBufferList().size() + "");

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
	 * 获取(仿Lan口)端口的ListRoot对象
	 * 
	 * @param treeNNIObject
	 * @param eTreeNNIXml
	 * @return
	 */
	private ListRoot getETreeNNIListRoot(ETreeNNIObject treeNNIObject, String eTreeNNIXml) {
		ListRoot listRoot = new ListRoot();
		try {
			DriveRoot driveRoot = super.LoadDriveXml(eTreeNNIXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("仿真LAN-ID".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeNNIObject.getLanPortId() + "");
				}
				if ("PW ID".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeNNIObject.getPwID() + "");
				}
				if ("(仿真LAN口组)下话TAG识别".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeNNIObject.getDownTagRecognition() + "");
				}
				if ("(仿真LAN口组)上话TAG行为".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeNNIObject.getUpTagBehavior() + "");
				}
				if ("(仿真LAN口组)上话增加VLAN ID".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeNNIObject.getUpTagValnId() + "");
				}
				if ("(仿真LAN口组)上话增加VLAN PRI".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeNNIObject.getUpTagValnPri() + "");
				}
				if ("(仿真LAN口组)MAC地址学习".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeNNIObject.getMacAddresslearn() + "");
				}
				if ("(仿真LAN口组)水平分割".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeNNIObject.getPortSplitHorizon() + "");
				}if ("控制字使能".equals(driveAttribute.getDescription())) {
					driveAttribute.setValue(treeNNIObject.getControlEnabl() + "");
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
	 * 根据命令生成ETreeObj对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private ETreeObject getETreeObj(ETreeObject treeObject, byte[] commands, String configXml) {
		// ETreeObject treeObject = new ETreeObject();

		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("VS ID".equals(driveAttribute.getDescription())) {
					treeObject.setVplsId(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("使能".equals(driveAttribute.getDescription())) {
					treeObject.setEnable(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("MAC地址表容量".equals(driveAttribute.getDescription())) {
					treeObject.setMacCount(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("角色".equals(driveAttribute.getDescription())) {
					treeObject.setRole(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(LAN口成员列表)成员端口数".equals(driveAttribute.getDescription())) {
					treeObject.setETreeUNIObjCount(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(仿真LAN口组)成员端口数".equals(driveAttribute.getDescription())) {
					treeObject.setETreeNNIObjCount(Integer.parseInt(driveAttribute.getValue()));
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return treeObject;
	}

	/**
	 * 根据命令获取ETreeUNIObj对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private ETreeUNIObject getETreeUNIObj(ETreeObject eTreeObject,byte[] commands, String configXml) {
		ETreeUNIObject treeUNIObject = new ETreeUNIObject();

		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("槽位号".equals(driveAttribute.getDescription())) {
					treeUNIObject.setSlot(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("端口号".equals(driveAttribute.getDescription())) {
					treeUNIObject.setPort(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("LAN口-ID".equals(driveAttribute.getDescription())) {
					treeUNIObject.setLanPortId(Integer.parseInt(driveAttribute.getValue()));
				}if ("业务类型".equals(driveAttribute.getDescription())) {
					eTreeObject.setType(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)水平分割".equals(driveAttribute.getDescription())) {
					treeUNIObject.setPortSplitHorizon(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("VC流量监管使能".equals(driveAttribute.getDescription())) {
					treeUNIObject.setVCTrafficPolicing(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)模式".equals(driveAttribute.getDescription())) {
					treeUNIObject.setModel(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)CIR".equals(driveAttribute.getDescription())) {
					treeUNIObject.setCir(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)PIR".equals(driveAttribute.getDescription())) {
					treeUNIObject.setPir(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)CM".equals(driveAttribute.getDescription())) {
					treeUNIObject.setCm(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)CBS".equals(driveAttribute.getDescription())) {
					treeUNIObject.setCbs(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)PBS".equals(driveAttribute.getDescription())) {
					treeUNIObject.setPbs(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)上话TAG识别".equals(driveAttribute.getDescription())) {
					treeUNIObject.setUpTagRecognition(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)下话TAG行为".equals(driveAttribute.getDescription())) {
					treeUNIObject.setDownTagBehavior(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)下话增加VLAN ID".equals(driveAttribute.getDescription())) {
					treeUNIObject.setDownTagValnId(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)下话增加VLAN PRI".equals(driveAttribute.getDescription())) {
					treeUNIObject.setDownTagValnPri(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(端口)MAC地址学习".equals(driveAttribute.getDescription())) {
					treeUNIObject.setMacAddresslearn(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("流数目".equals(driveAttribute.getDescription())) {
					treeUNIObject.setVpwsBufferCount(Integer.parseInt(driveAttribute.getValue()));
				}
			}
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}

		return treeUNIObject;

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
				if ("PW标签".equals(driveAttribute.getDescription())) {
					vpwsBuffer.setPwLable(Integer.parseInt(driveAttribute.getValue()));
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

	/**
	 * 根据命令获取ETreeNNIObj对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	private ETreeNNIObject getETreeNNIObj(byte[] commands, String configXml) {
		ETreeNNIObject treeNNIObject = new ETreeNNIObject();

		try {
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(commands);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			DriveAttribute driveAttribute = null;
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				if ("仿真LAN-ID".equals(driveAttribute.getDescription())) {
					treeNNIObject.setLanPortId(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("PW ID".equals(driveAttribute.getDescription())) {
					treeNNIObject.setPwID(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(仿真LAN口组)下话TAG识别".equals(driveAttribute.getDescription())) {
					treeNNIObject.setDownTagRecognition(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(仿真LAN口组)上话TAG行为".equals(driveAttribute.getDescription())) {
					treeNNIObject.setUpTagBehavior(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(仿真LAN口组)上话增加VLAN ID".equals(driveAttribute.getDescription())) {
					treeNNIObject.setUpTagValnId(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(仿真LAN口组)上话增加VLAN PRI".equals(driveAttribute.getDescription())) {
					treeNNIObject.setUpTagValnPri(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(仿真LAN口组)MAC地址学习".equals(driveAttribute.getDescription())) {
					treeNNIObject.setMacAddresslearn(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(仿真LAN口组)水平分割".equals(driveAttribute.getDescription())) {
					treeNNIObject.setPortSplitHorizon(Integer.parseInt(driveAttribute.getValue()));
				}if ("控制字使能".equals(driveAttribute.getDescription())) {
					treeNNIObject.setControlEnabl(Integer.parseInt(driveAttribute.getValue()));
				}

			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return treeNNIObject;

	}

}
