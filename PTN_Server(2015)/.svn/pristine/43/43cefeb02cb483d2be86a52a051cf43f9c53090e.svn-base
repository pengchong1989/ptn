package com.nms.drive.analysis.datablock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.bean.EXPToPHBObject;
import com.nms.drive.service.impl.CoderUtils;

/**
 * 解析 TMP EXP到PHB映射表(0AH)
 * @author 罗磊
 */
public class AnalysisEXPToPHBTable extends AnalysisBase{
	/**
	 * 根据对象生成字节数组
	 * @param expToPHBList 对象
	 * @param configXml 配置XML
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisObjectToCommand(List<EXPToPHBObject> expToPHBList,String configXml)throws Exception{
		try {
			DriveRoot driveRoot1 = super.LoadDriveXml(configXml);
			String file = driveRoot1.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
			// 清除本身自带的一个ListRoot
			driveRoot1.getDriveAttributeList().get(0).getListRootList().clear();
			// 条目数
			driveRoot1.getDriveAttributeList().get(0).setValue(expToPHBList.size()+"");
			for(int i = 0; i<expToPHBList.size(); i++){
				DriveRoot driveRoot11 = super.LoadDriveXml(file);
				ListRoot listRoot = new ListRoot();
				listRoot.setDriveAttributeList(driveRoot11.getDriveAttributeList());
				// 将 对象信息 赋值到 driveRoot1 对象中
				for(int j = 0; j<driveRoot11.getDriveAttributeList().size(); j++){
					DriveAttribute driveAttribute = driveRoot11.getDriveAttributeList().get(j);
					
					objectToAttribute(expToPHBList.get(i),driveAttribute);
				}
				driveRoot1.getDriveAttributeList().get(0).getListRootList().add(listRoot);
			}
			byte[] dataCommand = new AnalysisDriveXmlToCommand().analysisCommands(driveRoot1);
			CoderUtils.print16String(dataCommand);
			return dataCommand;
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * 根据命令生成对象
	 * @param commands 命令 
	 * @param configXml 配置XML
	 * @return
	 * @throws Exception
	 */
	public List<EXPToPHBObject> analysisCommandToObject(byte[] commands,String configXml)throws Exception{
		List<EXPToPHBObject> expToPHBListt = new ArrayList<EXPToPHBObject>();
		DriveRoot driveRoot = super.LoadDriveXml(configXml);
		String file = driveRoot.getDriveAttributeList().get(0).getListRootList().get(0).getFile();
		CoderUtils.print16String(commands);
		int dataCount = 9;
		int count = (commands.length-1-11) / dataCount;
		for(int i = 0; i < count; i++){
			byte[] a = new byte[dataCount];
			System.arraycopy(commands, 1 + i*dataCount, a, 0, a.length);
			AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			DriveRoot driveRoot1 = analysisCommandByDriveXml.analysisDriveAttrbute(file);
			EXPToPHBObject expToPHBObject = new EXPToPHBObject();
		
			for(int j =0; j<driveRoot1.getDriveAttributeList().size(); j++){
				DriveAttribute driveAttribute = driveRoot1.getDriveAttributeList().get(j);
				
				attributeToObject(expToPHBObject,driveAttribute);
			}
			expToPHBListt.add(expToPHBObject);
//			System.out.println(expToPHBObject.getExpPHBID()+","+expToPHBObject.getZero()+","+expToPHBObject.getOne()+","+expToPHBObject.getTwo());
			
		}
		return expToPHBListt;
	}
	
	//赋值
	public void objectToAttribute(EXPToPHBObject expToPHBObject,DriveAttribute driveAttribute){
		if("EXP@PHB_ID".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(expToPHBObject.getExpPHBID()+"");
		}
		if("0".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(expToPHBObject.getZero() +"");
		}
		if("1".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(expToPHBObject.getOne() +"");
		}
		if("2".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(expToPHBObject.getTwo() +"");
		}
		if("3".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(expToPHBObject.getThree() +"");
		}
		if("4".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(expToPHBObject.getFour() +"");
		}
		if("5".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(expToPHBObject.getFive() +"");
		}
		if("6".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(expToPHBObject.getSix() +"");
		}
		if("7".equals(driveAttribute.getDescription())){
			driveAttribute.setValue(expToPHBObject.getSeven() +"");
		}
		
	}
	//赋值
	public void attributeToObject(EXPToPHBObject expToPHBObject,DriveAttribute driveAttribute){
		if("EXP@PHB_ID".equals(driveAttribute.getDescription())){
			expToPHBObject.setExpPHBID(Integer.parseInt(driveAttribute.getValue()));
		}
		if("0".equals(driveAttribute.getDescription())){
			expToPHBObject.setZero(Integer.parseInt(driveAttribute.getValue()));
		}
		if("1".equals(driveAttribute.getDescription())){
			expToPHBObject.setOne(Integer.parseInt(driveAttribute.getValue()));
		}
		if("2".equals(driveAttribute.getDescription())){
			expToPHBObject.setTwo(Integer.parseInt(driveAttribute.getValue()));
		}
		if("3".equals(driveAttribute.getDescription())){
			expToPHBObject.setThree(Integer.parseInt(driveAttribute.getValue()));
		}
		if("4".equals(driveAttribute.getDescription())){
			expToPHBObject.setFour(Integer.parseInt(driveAttribute.getValue()));
		}
		if("5".equals(driveAttribute.getDescription())){
			expToPHBObject.setFive(Integer.parseInt(driveAttribute.getValue()));
		}
		if("6".equals(driveAttribute.getDescription())){
			expToPHBObject.setSix(Integer.parseInt(driveAttribute.getValue()));
		}
		if("7".equals(driveAttribute.getDescription())){
			expToPHBObject.setSeven(Integer.parseInt(driveAttribute.getValue()));
		}
	}
}
