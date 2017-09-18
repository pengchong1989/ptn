package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.NniObject;
import com.nms.drive.service.bean.ProtObject;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisWANTable extends AnalysisBase {
	private int NEhead = 5;
	private int controlPanelHead = 101;
	private int dataBlockHead = 25;
	private int dataLength = 120;
	private int nniLength = 30;

	/**
	 * NNI对象转换为命令
	 * 
	 * @param proObjectList
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisWANtoCommand(List<ProtObject> proObjectList, String configXml) throws Exception {
		try {
			// 对proObjectList处理
			if (proObjectList.size() > 4) {
				throw new Exception("nniObject 只有4个");
			}
			while (proObjectList.size() < 4) {
				ProtObject proObject1 = new ProtObject();
				proObject1.getNniObject().setPortEnable(1);
				proObjectList.add(proObject1);
			}

			DriveRoot root = new DriveRoot();// 存放NNI对象的所有DriveAttribute
			// 对端口集合遍历
			DriveRoot driveRoot_config = null;
			for (int i = 0; i < proObjectList.size(); i++) {
				driveRoot_config = super.LoadDriveXml(configXml);
				for (int j = 0; j < driveRoot_config.getDriveAttributeList().size(); j++) {
					DriveAttribute driveAttributeNNI = driveRoot_config.getDriveAttributeList().get(j);
					if (("(WAN1)端口使能").equals(driveAttributeNNI.getDescription())) {
						driveAttributeNNI.setValue(proObjectList.get(i).getNniObject().getPortEnable() + "");
					}
					if ("(WAN1)工作模式".equals(driveAttributeNNI.getDescription())) {
						driveAttributeNNI.setValue(proObjectList.get(i).getNniObject().getWorkPattern() + "");
					}
					if ("(WAN1)802.3流控".equals(driveAttributeNNI.getDescription())) {
						driveAttributeNNI.setValue(proObjectList.get(i).getNniObject().getBuffer802_3Enable() + "");
					}
					if ("(WAN1)MTU".equals(driveAttributeNNI.getDescription())) {
						driveAttributeNNI.setValue(proObjectList.get(i).getNniObject().getMtu() + "");
					}
					if ("(WAN1)备用".equals(driveAttributeNNI.getDescription())) {
						driveAttributeNNI.setValue(proObjectList.get(i).getNniObject().getReserve() + "");
					}
					root.getDriveAttributeList().add(driveAttributeNNI);
				}
			}

			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(root);
			// CoderUtils.print16String(dataCommand);
			return dataCommand;

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			throw e;
		}

	}

	/**
	 * 命令转换为NNI对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	public List<ProtObject> analysisCommandToWAN(byte[] commands, String configXml) throws Exception {
		List<ProtObject> proObjectList = new ArrayList<ProtObject>();
		int start = NEhead + controlPanelHead + dataBlockHead;// 命令固定长度
		int nniObjCount = dataLength / nniLength;// nniObj的总个数
		ProtObject protObject = null;
		NniObject nniObject = null;

		// 对命令中获取的nniObject数量进行循环，生成相应的ProObject
		for (int i = 0; i < nniObjCount; i++) {
			byte[] nniByte = new byte[nniLength];// nni命令数据长度
			System.arraycopy(commands, start + i * nniLength, nniByte, 0, nniByte.length);

			// 将命令解析成driveRoot对象
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(nniByte);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			protObject = new ProtObject();
			nniObject = new NniObject();
			// 对获取到的DriveRoot循环
			for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
				DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(j);

				if ("(WAN1)端口使能".equals(driveAttribute.getDescription())) {
					nniObject.setPortEnable(Integer.parseInt(driveAttribute.getValue()));

				}
				if ("(WAN1)工作模式".equals(driveAttribute.getDescription())) {
					nniObject.setWorkPattern(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(WAN1)802.3流控".equals(driveAttribute.getDescription())) {
					nniObject.setBuffer802_3Enable(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(WAN1)MTU".equals(driveAttribute.getDescription())) {
					nniObject.setMtu(Integer.parseInt(driveAttribute.getValue()));
				}
				if ("(WAN1)备用".equals(driveAttribute.getDescription())) {

				}

			}

			protObject.setNniObject(nniObject);// 将nniObject放入protObject中
			proObjectList.add(protObject);// 将protObject存入集合
		}

		return proObjectList;

	}

}
