package com.nms.drive.analysis.datablock;


import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisNeObjectSNTable extends AnalysisBase {
	
	
//	public static void main(String[] args) {
//		byte[] c = {(byte) 255,(byte) 255,00,04,00,00,00,02,12,55,55,55,55,55,55,55,55,55,55,55,55,00,01,07,03,00,70,00,01,00,00,00,(byte) 204,00,00,00,00,00,00,00,01,01,80,12,55,38,39,55,38,39,55,38,39,55,38,39,00,00,07,03,00,70,00,01,00,00,00,(byte) 204,00,00,00,00,00,00,00,8,01,80,00,01,00,00,00,02,00,00,00,03,00,00};
//		AnalysisNeObjectSNTable analysisNeObjectSNTable = new AnalysisNeObjectSNTable();
//		try {
//			List<NEObject> neObjects = analysisNeObjectSNTable.analysisCommandInfoToNEObjectS(c, "com/nms/drive/analysis/xmltool/file/相邻网元SN.xml");
//			System.out.println(neObjects.size());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	/**
	 * 根据字节数组生成对象
	 * 
	 * @param commands命令
	 * @param configXml配置XML
	 * @return ETHOAM
	 * @throws Exception 
	 */
	 
	public List<NEObject> analysisCommandInfoToNEObjectS(byte[] commands,String configXml) throws Exception{
		List<NEObject> neObjects = null;
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		neObjects = new ArrayList<NEObject>();
		int index = 4;
		while(index != commands.length){
			
			//端口号
			byte[] p = new byte[4];
			p[0] = 0;
			p[1] = 0;
			p[2] = commands[index];
			p[3] = commands[1+index];
			int port = CoderUtils.bytesToInt(p);
			//邻居个数
			byte[] n = new byte[4];
			n[0] = 0;
			n[1] = 0;
			n[2] = commands[2+index];
			n[3] = commands[3+index];
			int number = CoderUtils.bytesToInt(n);
			index += 4;
			for (int i = 0; i < number; i++) {
				NEObject neObject = new NEObject();
				neObject.setLinkPort((port)+1+"-");
				byte[] b = new byte[35];
				StringBuffer sn = new StringBuffer();
				System.arraycopy(commands, index, b, 0, b.length);					
				analysisCommandByDriveXml.setCommands(b);
				DriveRoot driveRoot =analysisCommandByDriveXml.analysisDriveAttrbute(configXml) ;
				for (int j = 0; j < driveRoot.getDriveAttributeList().size(); j++) {
					DriveAttribute driveAttribute = new DriveAttribute();
					driveAttribute = driveRoot.getDriveAttributeList().get(j);
					DriveAttributeTONEObject(sn,driveAttribute,neObject);
				}
				neObjects.add(neObject);
				index = index+35;
			}
		}
		return neObjects;
	}

	private void DriveAttributeTONEObject(StringBuffer sn, DriveAttribute driveAttribute, NEObject neObject) {
		if("SN1".equals(driveAttribute.getDescription())){
			sn.append((char)Integer.parseInt(driveAttribute.getValue()));
		}else if("SN2".equals(driveAttribute.getDescription())){
			sn.append((char)Integer.parseInt(driveAttribute.getValue()));
		}else if("SN3".equals(driveAttribute.getDescription())){
			sn.append((char)Integer.parseInt(driveAttribute.getValue()));
		}else if("SN4".equals(driveAttribute.getDescription())){
			sn.append((char)Integer.parseInt(driveAttribute.getValue()));
		}else if("SN5".equals(driveAttribute.getDescription())){
			sn.append((char)Integer.parseInt(driveAttribute.getValue()));
		}else if("SN6".equals(driveAttribute.getDescription())){
			sn.append((char)Integer.parseInt(driveAttribute.getValue()));
		}else if("SN7".equals(driveAttribute.getDescription())){
			sn.append((char)Integer.parseInt(driveAttribute.getValue()));
		}else if("SN8".equals(driveAttribute.getDescription())){
			sn.append((char)Integer.parseInt(driveAttribute.getValue()));
		}else if("SN9".equals(driveAttribute.getDescription())){
			sn.append((char)Integer.parseInt(driveAttribute.getValue()));
		}else if("SN10".equals(driveAttribute.getDescription())){
			sn.append((char)Integer.parseInt(driveAttribute.getValue()));
		}else if("SN11".equals(driveAttribute.getDescription())){
			sn.append((char)Integer.parseInt(driveAttribute.getValue()));
		}else if("SN12".equals(driveAttribute.getDescription())){
			sn.append((char)Integer.parseInt(driveAttribute.getValue()));
			neObject.setSn(sn.toString());
		}else if("邻居端口号".equals(driveAttribute.getDescription())){
			neObject.setLinkPort(neObject.getLinkPort()+(Integer.parseInt(driveAttribute.getValue())+1));
		}else if("设备类型".equals(driveAttribute.getDescription())){
			neObject.setCellType(driveAttribute.getValue());
		}
	}

}
