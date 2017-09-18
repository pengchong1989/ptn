package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.status.OamMepInfoObject;
import com.nms.drive.service.impl.CoderUtils;

/**
 *function:将接入链路以太网oam对端MEP信息 转换成相应的驱动对象
 * @author zk
 */

public class AnalysisOamMepInfoObjectTable extends AnalysisBase{
	
	
	
   /**
    * 根据字节数组生成对象
    * @param dataCommand 自己数组
    *  @param configXml 协议地址
    * @return
    * @throws Exception
    */
	public OamMepInfoObject analysisCommonToObject(byte[] dataCommand,String configXml) throws Exception{
		AnalysisCommandByDriveXml analysisCommandByDriveXml = null;
		DriveRoot driveRoot_config = null;
		OamMepInfoObject oamMepInfoObject =null;
		try {
			oamMepInfoObject = new OamMepInfoObject();
			//起始长度
			byte[] a = new byte[dataCommand.length-6];
			System.arraycopy(dataCommand,6, a,0,a.length);
			analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
			analysisCommandByDriveXml.setCommands(a);
			driveRoot_config = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			StringBuffer mepMacAddress = new StringBuffer();
			StringBuffer sbMdName = new StringBuffer();
			StringBuffer sbMaName = new StringBuffer();
			for(int i = 0, y = driveRoot_config.getDriveAttributeList().size(); i<y; i++){
				DriveAttribute driveAttribute = driveRoot_config.getDriveAttributeList().get(i);
				//赋值
				DriveAttriuteToOamMepInfoObject(oamMepInfoObject,driveAttribute,mepMacAddress,sbMdName,sbMaName);
			}
			
		} catch (Exception e) {
			throw e;
		}
		return oamMepInfoObject;
	}

	/**
	 * function:将命令对应相应的配置文件转成对应的驱动对象
	 * @param oamMepInfoObject 驱动对象
	 * @param driveAttribute 通过配置文件盒命令吗解析出来的设备数据
	 */
   private void DriveAttriuteToOamMepInfoObject(OamMepInfoObject oamMepInfoObject, DriveAttribute driveAttribute,StringBuffer mepMacAddress,StringBuffer sbMdName, StringBuffer sbMaName) {
	   //MEPID
	   if("MEPID".equals(driveAttribute.getDescription())){
		   oamMepInfoObject.setMepId(driveAttribute.getValue());
	   }
      //MEPMAC地址1
	   if("MEPMAC地址1".equals(driveAttribute.getDescription())){
		   mepMacAddress.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
	   }
	   //MEPMAC地址2
	   if("MEPMAC地址2".equals(driveAttribute.getDescription())){
		   mepMacAddress.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
	   }
	   //MEPMAC地址3
	   if("MEPMAC地址3".equals(driveAttribute.getDescription())){
		   mepMacAddress.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
	   }
	   //MEPMAC地址4
	   if("MEPMAC地址4".equals(driveAttribute.getDescription())){
		   mepMacAddress.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
	   }
	   //MEPMAC地址5
	   if("MEPMAC地址5".equals(driveAttribute.getDescription())){
		   mepMacAddress.append(CoderUtils.synchTransformMac(driveAttribute.getValue())+"-");
	   }
	   //MEPMAC地址6
	   if("MEPMAC地址6".equals(driveAttribute.getDescription())){
		   mepMacAddress.append(CoderUtils.synchTransformMac(driveAttribute.getValue()));
		   oamMepInfoObject.setMepMac(mepMacAddress.toString());
	   }
	   
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
			oamMepInfoObject.setMdName(sbMdName.toString().trim());
		}
		if ("MD Level".equals(driveAttribute.getDescription())) {
			oamMepInfoObject.setMdLevel(driveAttribute.getValue());
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
			oamMepInfoObject.setMaName((sbMaName.toString().trim()));
		}
  }
}
