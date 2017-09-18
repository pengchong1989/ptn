package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.MsPwObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

/**
 * 
 * @author 彭冲 MSPW解析
 * 
 */
public class AnalysisMSPWTable extends AnalysisBase {

	private int dataCount = 130;// 每个数据块的长度
	private int clauses = 2;// 条目数长度

	/**
	 * 根据对象生成字节数组
	 * 
	 * @param tunnelObject对象
	 * @param configXml配置XML
	 * @return 命令
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(List<MsPwObject> msPwObjects, String configXml) throws Exception {

		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			
			String file = driveRoot_config.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			driveRoot_config.getDriveAttributeList().get(0).getListRootList().clear();// 清除本身自带的一个ListRoot
			driveRoot_config.getDriveAttributeList().get(0).setValue(msPwObjects.size() + "");// 条目数
			for (int j = 0; j < msPwObjects.size(); j++) {

				DriveRoot driveRoot_config_1 = super.LoadDriveXml(file);
				ListRoot listroot = new ListRoot();
				listroot.setDriveAttributeList

				(driveRoot_config_1.getDriveAttributeList());

				for (int i = 0; i < driveRoot_config_1.getDriveAttributeList().size(); i++) {
					DriveAttribute driveAttribute = listroot.getDriveAttributeList().get(i);
					ObjectToDriveAttribute(msPwObjects.get(j),driveAttribute);
				}
				driveRoot_config.getDriveAttributeList().get(0).getListRootList().add(listroot);
			}

			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] dataCommand = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
			CoderUtils.print16String(dataCommand);
			return dataCommand;

		} catch (Exception e) {
			throw e;
		}
	}

	public void ObjectToDriveAttribute(MsPwObject msPwObject, DriveAttribute driveAttribute) {
		if (driveAttribute.getDescription().equals("mspwID")) {
			driveAttribute.setValue(msPwObject.getMspwId() + "");
		}else if (driveAttribute.getDescription().equals("前向LSP id")) {
			driveAttribute.setValue(msPwObject.getFrontTunnelId() + "");
		}else if (driveAttribute.getDescription().equals("后向LSP id")) {
			driveAttribute.setValue(msPwObject.getBackTunnelId() + "");
		}else if (driveAttribute.getDescription().equals("前向入pw标签")) {
			driveAttribute.setValue(msPwObject.getFrontInlabel() + "");
			/*调试使用***************************************************/
//			System.out.println("下发多段前向入PW标签:"+msPwObject.getFrontInlabel());
//			ExceptionManage.dispose(new Exception("下发多段前向入PW标签:"+msPwObject.getFrontInlabel()), this.getClass());
			/*end**************************************************/
		}else if (driveAttribute.getDescription().equals("前向出pw标签")) {
			driveAttribute.setValue(msPwObject.getFrontOutlabel() + "");
			/*调试使用***************************************************/
//			System.out.println("下发多段前向出PW标签:"+msPwObject.getFrontOutlabel());
//			ExceptionManage.dispose(new Exception("下发多段前向出PW标签:"+msPwObject.getFrontOutlabel()), this.getClass());
			/*end**************************************************/
		}else if (driveAttribute.getDescription().equals("后向入pw标签")) {
			driveAttribute.setValue(msPwObject.getBackInlabel() + "");
			/*调试使用***************************************************/
//			System.out.println("下发多段后向入PW标签:"+msPwObject.getBackInlabel());
//			ExceptionManage.dispose(new Exception("下发多段后向入PW标签:"+msPwObject.getBackInlabel()), this.getClass());
			/*end**************************************************/
		}else if (driveAttribute.getDescription().equals("后向出pw标签")) {
			driveAttribute.setValue(msPwObject.getBackOutlabel() + "");
			/*调试使用***************************************************/
//			System.out.println("下发多段后向出PW标签:"+msPwObject.getBackOutlabel());
//			ExceptionManage.dispose(new Exception("下发多段后向出PW标签:"+msPwObject.getBackOutlabel()), this.getClass());
			/*end**************************************************/
		}else if (driveAttribute.getDescription().equals("MIP ID")) {
			driveAttribute.setValue(msPwObject.getMipId()+ "");
		}
	}

	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return TunnelObject
	 */
	public List<MsPwObject> analysisCommandToObject(byte[] commands, String configXml)throws Exception {
		List<MsPwObject> msPwObjects = new ArrayList<MsPwObject>();
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
			MsPwObject msPwObject = new MsPwObject();
			try {
				DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(file);
				// 将 driveRoot 信息 赋值 TunnelObject 对象中
				for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {

					DriveAttribute driveattribute = driveRoot.getDriveAttributeList().get(i);
					DriveAttributeToObject(msPwObject, driveattribute);
				}

			} catch (Exception e) {
				throw e;
			}
			msPwObjects.add(msPwObject);
		}
		return msPwObjects;
	}

	private void DriveAttributeToObject(MsPwObject msPwObject, DriveAttribute driveAttribute) {
		if (driveAttribute.getDescription().equals("mspwID")) {
			msPwObject.setMspwId(Integer.parseInt(driveAttribute.getValue()));
		}else if (driveAttribute.getDescription().equals("前向LSP id")) {
			msPwObject.setFrontTunnelId(Integer.parseInt(driveAttribute.getValue()));
		}else if (driveAttribute.getDescription().equals("后向LSP id")) {
			msPwObject.setBackTunnelId(Integer.parseInt(driveAttribute.getValue()));
		}else if (driveAttribute.getDescription().equals("前向入pw标签")) {
			msPwObject.setFrontInlabel(Integer.parseInt(driveAttribute.getValue()));
			/*调试使用***************************************************/
//			System.out.println("同步多段前向入PW标签:"+msPwObject.getFrontInlabel());
//			ExceptionManage.dispose(new Exception("同步多段前向入PW标签:"+msPwObject.getFrontInlabel()), this.getClass());
			/*end**************************************************/
		}else if (driveAttribute.getDescription().equals("前向出pw标签")) {
			msPwObject.setFrontOutlabel(Integer.parseInt(driveAttribute.getValue()));
			/*调试使用***************************************************/
//			System.out.println("同步多段前向出PW标签:"+msPwObject.getFrontOutlabel());
//			ExceptionManage.dispose(new Exception("同步多段前向出PW标签:"+msPwObject.getFrontOutlabel()), this.getClass());
			/*end**************************************************/
		}else if (driveAttribute.getDescription().equals("后向入pw标签")) {
			msPwObject.setBackInlabel(Integer.parseInt(driveAttribute.getValue()));
			/*调试使用***************************************************/
//			System.out.println("同步多段后向入PW标签:"+msPwObject.getBackInlabel());
//			ExceptionManage.dispose(new Exception("同步多段后向入PW标签:"+msPwObject.getBackInlabel()), this.getClass());
			/*end**************************************************/
		}else if (driveAttribute.getDescription().equals("后向出pw标签")) {
			msPwObject.setBackOutlabel(Integer.parseInt(driveAttribute.getValue()));
			/*调试使用***************************************************/
//			System.out.println("同步多段后向出PW标签:"+msPwObject.getBackOutlabel());
//			ExceptionManage.dispose(new Exception("同步多段后向出PW标签:"+msPwObject.getBackOutlabel()), this.getClass());
			/*end**************************************************/
		}else if (driveAttribute.getDescription().equals("MIP ID")) {
			msPwObject.setMipId(Integer.parseInt(driveAttribute.getValue()));
		}
	}
}
