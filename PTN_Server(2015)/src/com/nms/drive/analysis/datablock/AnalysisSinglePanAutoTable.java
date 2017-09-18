package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.DataInfo;
import com.nms.drive.service.bean.SinglePan;
import com.nms.ui.manager.ExceptionManage;

/**
 * 单盘自动发现信息
 */

public class AnalysisSinglePanAutoTable extends AnalysisBase{
	/**
	 * 根据字节数组生成单盘信息对象
	 * @param command
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public SinglePan analysisCommandToObject(byte[] command,String configXml)throws Exception{
		String configFile="com/nms/drive/analysis/xmltool/file/配置块数据信息.xml";
		String panFile="com/nms/drive/analysis/xmltool/file/单盘补充信息.xml";
		SinglePan singlePan = new SinglePan();
		try {
			//起始长度
			int start = 9;
			byte[] a = new byte[start];
			System.arraycopy(command, 0, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			//前面部分赋值
			DriveAttributeToSinglePan1(driveRoot,singlePan);
				
			//配置块数据信息赋值
			//中间长度
			int mid = command.length-9-75;//12
			int dataCount = 12;
			int count = mid/dataCount;
			byte[] b = new byte[mid];
			System.arraycopy(command, start, b, 0, b.length);
			for (int j = 0; j < count; j++) {
				AnalysisCommandByDriveXml analysisCommandByDriveXml1 = new AnalysisCommandByDriveXml();
				analysisCommandByDriveXml1.setCommands(b);
				DriveRoot driveRoot1 = analysisCommandByDriveXml1.analysisDriveAttrbute(configFile);
				DriveAttributeToSinglePan2(driveRoot1,singlePan);
			}
			//单盘补充信息赋值
			//末尾长度
			int end = 75;
			byte[] c = new byte[end];
			System.arraycopy(command, start+mid, c, 0, c.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml2 = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml2.setCommands(c);
			DriveRoot driveRoot2 = analysisCommandByDriveXml2.analysisDriveAttrbute(panFile);
			DriveAttributeToSinglePan3(driveRoot2,singlePan);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return singlePan;
	}
	//给对象SinglePan3赋值
	private void DriveAttributeToSinglePan3(DriveRoot driveRoot,SinglePan singlePan) {
		for (int k = 0; k < driveRoot.getDriveAttributeList().size(); k++) {
		DriveAttribute 	driveAttribute = driveRoot.getDriveAttributeList().get(k);
		StringBuffer sb3 = new StringBuffer();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
		if("盘号1".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号2".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号3".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号4".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号5".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号6".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号7".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号8".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号9".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号10".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号11".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号12".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号13".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号14".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号15".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号16".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号17".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号18".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号19".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
		}
		if("盘号20".equals(driveAttribute.getDescription())){
			sb3.append(driveAttribute.getValue());
			singlePan.setPanNum(sb3.toString());
		}
		StringBuffer sb4 = new StringBuffer();
		if("版号1".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号2".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号3".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号4".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号5".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号6".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号7".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号8".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号9".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号10".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号11".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号12".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号13".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号14".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号15".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号16".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号17".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号18".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号19".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
		}
		if("版号20".equals(driveAttribute.getDescription())){
			sb4.append(driveAttribute.getValue());
			singlePan.setVersionNum(sb4.toString());
		}
		StringBuffer sb5 = new StringBuffer();
		if("制盘时间1".equals(driveAttribute.getDescription())){
			sb5.append(driveAttribute.getValue());
		}
		if("制盘时间2".equals(driveAttribute.getDescription())){
			sb5.append(driveAttribute.getValue());
		}
		if("制盘时间3".equals(driveAttribute.getDescription())){
			sb5.append(driveAttribute.getValue());
		}
		if("制盘时间4".equals(driveAttribute.getDescription())){
			sb5.append(driveAttribute.getValue());
		}
		if("制盘时间5".equals(driveAttribute.getDescription())){
			sb5.append(driveAttribute.getValue());
		}
		if("制盘时间6".equals(driveAttribute.getDescription())){
			sb5.append(driveAttribute.getValue());
		}
		if("制盘时间7".equals(driveAttribute.getDescription())){
			sb5.append(driveAttribute.getValue());
		}
		if("制盘时间8".equals(driveAttribute.getDescription())){
			sb5.append(driveAttribute.getValue());
		}
		if("制盘时间9".equals(driveAttribute.getDescription())){
			sb5.append(driveAttribute.getValue());
		}
		if("制盘时间10".equals(driveAttribute.getDescription())){
			sb5.append(driveAttribute.getValue());
		}
		if("制盘时间11".equals(driveAttribute.getDescription())){
			sb5.append(driveAttribute.getValue());
			singlePan.setPanTime(sb5.toString());
		}	
		StringBuffer sb6 = new StringBuffer();
		if("BMU软件版本1".equals(driveAttribute.getDescription())){
			sb6.append(driveAttribute.getValue());
		}
		if("BMU软件版本2".equals(driveAttribute.getDescription())){
			sb6.append(driveAttribute.getValue());
		}
		if("BMU软件版本3".equals(driveAttribute.getDescription())){
			sb6.append(driveAttribute.getValue());
		}
		if("BMU软件版本4".equals(driveAttribute.getDescription())){
			sb6.append(driveAttribute.getValue());
		}
		if("BMU软件版本5".equals(driveAttribute.getDescription())){
			sb6.append(driveAttribute.getValue());
		}
		if("BMU软件版本6".equals(driveAttribute.getDescription())){
			sb6.append(driveAttribute.getValue());
		}
		if("BMU软件版本7".equals(driveAttribute.getDescription())){
			sb6.append(driveAttribute.getValue());
		}
		if("BMU软件版本8".equals(driveAttribute.getDescription())){
			sb6.append(driveAttribute.getValue());
		}
		if("BMU软件版本9".equals(driveAttribute.getDescription())){
			sb6.append(driveAttribute.getValue());
		}
		if("BMU软件版本10".equals(driveAttribute.getDescription())){
			sb6.append(driveAttribute.getValue());
		}
		if("BMU软件版本11".equals(driveAttribute.getDescription())){
			sb6.append(driveAttribute.getValue());
		}
		if("BMU软件版本12".equals(driveAttribute.getDescription())){
			sb6.append(driveAttribute.getValue());
		}
		if("BMU软件版本13".equals(driveAttribute.getDescription())){
			sb6.append(driveAttribute.getValue());
			singlePan.setBMUversion(sb6.toString());
		}
		StringBuffer sb7 = new StringBuffer();
		if("BMU软件生成时间1".equals(driveAttribute.getDescription())){
			sb7.append(driveAttribute.getValue());
		}
		if("BMU软件生成时间2".equals(driveAttribute.getDescription())){
			sb7.append(driveAttribute.getValue());
		}
		if("BMU软件生成时间3".equals(driveAttribute.getDescription())){
			sb7.append(driveAttribute.getValue());
		}
		if("BMU软件生成时间4".equals(driveAttribute.getDescription())){
			sb7.append(driveAttribute.getValue());
		}
		if("BMU软件生成时间5".equals(driveAttribute.getDescription())){
			sb7.append(driveAttribute.getValue());
		}
		if("BMU软件生成时间6".equals(driveAttribute.getDescription())){
			sb7.append(driveAttribute.getValue());
		}
		if("BMU软件生成时间7".equals(driveAttribute.getDescription())){
			sb7.append(driveAttribute.getValue());
		}
		if("BMU软件生成时间8".equals(driveAttribute.getDescription())){
			sb7.append(driveAttribute.getValue());
		}
		if("BMU软件生成时间9".equals(driveAttribute.getDescription())){
			sb7.append(driveAttribute.getValue());
		}
		if("BMU软件生成时间10".equals(driveAttribute.getDescription())){
			sb7.append(driveAttribute.getValue());
		}
		if("BMU软件生成时间11".equals(driveAttribute.getDescription())){
			sb7.append(driveAttribute.getValue());
			singlePan.setBMUtime(sb7.toString());
		}
		}
	}
	//给对象配置块数据信息赋值
	private void DriveAttributeToSinglePan2(DriveRoot driveRoot,SinglePan singlePan) {
		DataInfo dataInfo = new DataInfo();
		for (int l = 0; l < driveRoot.getDriveAttributeList().size(); l++) {
			DriveAttribute driveAttribute = driveRoot.getDriveAttributeList().get(l);
			if("标识码".equals(driveAttribute.getDescription())){
				dataInfo.setTagNum(Integer.parseInt(driveAttribute.getValue()));
			}
			StringBuffer sb8 = new StringBuffer();
			if("CRC校验和1".equals(driveAttribute.getDescription())){
				sb8.append(driveAttribute.getValue());
			}
			if("CRC校验和2".equals(driveAttribute.getDescription())){
				sb8.append(driveAttribute.getValue());
			}
			if("CRC校验和3".equals(driveAttribute.getDescription())){
				sb8.append(driveAttribute.getValue());
			}
			if("CRC校验和4".equals(driveAttribute.getDescription())){
				sb8.append(driveAttribute.getValue());
				dataInfo.setCRCcheck(sb8.toString());
			}
			StringBuffer sb9 = new StringBuffer();
			if("生产时间1".equals(driveAttribute.getDescription())){
				sb9.append(driveAttribute.getValue());
			}
			if("生产时间2".equals(driveAttribute.getDescription())){
				sb9.append(driveAttribute.getValue());
			}
			if("生产时间3".equals(driveAttribute.getDescription())){
				sb9.append(driveAttribute.getValue());
			}
			if("生产时间4".equals(driveAttribute.getDescription())){
				sb9.append(driveAttribute.getValue());
			}
			if("生产时间5".equals(driveAttribute.getDescription())){
				sb9.append(driveAttribute.getValue());
			}
			if("生产时间6".equals(driveAttribute.getDescription())){
				sb9.append(driveAttribute.getValue());
			}
			if("生产时间7".equals(driveAttribute.getDescription())){
				sb9.append(driveAttribute.getValue());
				dataInfo.setProductionTime(sb9.toString());
			}
			List<DataInfo> list = new ArrayList<DataInfo>();
			list.add(dataInfo);
			singlePan.setDataInfoList(list);
		}
	}
	//给对象SinglePan1赋值
	private void DriveAttributeToSinglePan1(DriveRoot driveRoot,SinglePan singlePan) {
		DriveAttribute driveAttribute = null;
		for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
			driveAttribute = driveRoot.getDriveAttributeList().get(i);
			StringBuffer sb = new StringBuffer();
			if("NE地址1".equals(driveAttribute.getDescription())){
				sb.append(driveAttribute.getValue());
			}
			if("NE地址2".equals(driveAttribute.getDescription())){
				sb.append(driveAttribute.getValue());
				singlePan.setNEAddr(sb.toString());
			}
			StringBuffer sb1 = new StringBuffer();
			if("盘类型1".equals(driveAttribute.getDescription())){
				sb1.append(driveAttribute.getValue());
			}
			if("盘类型2".equals(driveAttribute.getDescription())){
				sb1.append(driveAttribute.getValue());
			}
			if("盘类型3".equals(driveAttribute.getDescription())){
				sb1.append(driveAttribute.getValue());
			}
			if("盘类型4".equals(driveAttribute.getDescription())){
				sb1.append(driveAttribute.getValue());
				singlePan.setPanType(sb1.toString());
			}
			StringBuffer sb2 = new StringBuffer();
			if("盘地址1".equals(driveAttribute.getDescription())){
				sb2.append(driveAttribute.getValue());
			}
			if("盘地址2".equals(driveAttribute.getDescription())){
				sb2.append(driveAttribute.getValue());
				singlePan.setPanAddr(sb2.toString());
			}
			if("配置数据块总数".equals(driveAttribute.getDescription())){
				singlePan.setConfigCount(Integer.parseInt(driveAttribute.getValue()));
			}
		}
	}
}
