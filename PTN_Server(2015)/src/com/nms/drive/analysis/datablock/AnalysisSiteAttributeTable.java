package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.NEObject;

public class AnalysisSiteAttributeTable extends AnalysisBase {
	private StringBuffer totalTime = new StringBuffer();
	private StringBuffer siteTime = new StringBuffer();
	private StringBuffer softWare = new StringBuffer();
	private StringBuffer hardWare = new StringBuffer();
	private StringBuffer bootTime = new StringBuffer();
	private StringBuffer fpgaTime = new StringBuffer();
	private StringBuffer plateNumber = new StringBuffer();
	private StringBuffer cardNumber = new StringBuffer();
	private StringBuffer createPlateNumber = new StringBuffer();
	private StringBuffer programmeTime = new StringBuffer();
//	private String neMac;
	

	/**
	 * 命令转换为site对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 */
	public NEObject analysisCommandToSite(byte[] commands, String configXml) throws Exception {
		NEObject neObject = new NEObject();
		DriveAttribute driveAttribute = null;
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(commands);
		DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
		for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
			driveAttribute = driveRoot.getDriveAttributeList().get(i);
			setSiteAttribute(driveAttribute, neObject);
		}
		return neObject;

	}

	private void setSiteAttribute(DriveAttribute driveAttribute, NEObject neObject) {		
		if ("ne地址".equals(driveAttribute.getDescription())) {
			int neaddress = Integer.parseInt(driveAttribute.getValue());
			neObject.setFieldID(neaddress / 256);
			neObject.setSite_Hum_Id(neaddress % 256 + "");
		}
		if ("累计时".equals(driveAttribute.getDescription())) {
			totalTime.append(driveAttribute.getValue() + "小时");
		} else if ("累计分".equals(driveAttribute.getDescription())) {
			totalTime.append(driveAttribute.getValue() + "分");
		} else if ("累计秒".equals(driveAttribute.getDescription())) {
			totalTime.append(driveAttribute.getValue() + "秒");
			neObject.setTotalTime(totalTime.toString());
		} else if ("当前年".equals(driveAttribute.getDescription())) {
			siteTime.append(driveAttribute.getValue() + "-");
		} else if ("当前月".equals(driveAttribute.getDescription())) {
			siteTime.append(timeForm(driveAttribute.getValue()) + "-");
		} else if ("当前日".equals(driveAttribute.getDescription())) {
			siteTime.append(timeForm(driveAttribute.getValue()) + " ");
		} else if ("当前时".equals(driveAttribute.getDescription())) {
			siteTime.append(timeForm(driveAttribute.getValue()) + ":");
		} else if ("当前分".equals(driveAttribute.getDescription())) {
			siteTime.append(timeForm(driveAttribute.getValue()) + ":");
		} else if ("当前秒".equals(driveAttribute.getDescription())) {
			siteTime.append(timeForm(driveAttribute.getValue()));
			neObject.setCellTime(siteTime.toString());
		} else if ("软件版本号(1)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("软件版本号(2)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("软件版本号(3)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("软件版本号(4)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("软件版本号(5)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("软件版本号(6)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("软件版本号(7)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("软件版本号(8)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("软件版本号(9)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("软件版本号(10)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("软件版本号(11)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("软件版本号(12)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("软件版本号(13)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("软件版本号(14)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("软件版本号(15)".equals(driveAttribute.getDescription())) {
			softWare.append((char) Integer.parseInt(driveAttribute.getValue()));
			neObject.setSoftEdition(softWare.toString());
		} else if ("卡号(1)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("卡号(2)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("卡号(3)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("卡号(4)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("卡号(5)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("卡号(6)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("卡号(7)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("卡号(8)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("卡号(9)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("卡号(10)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("卡号(11)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("卡号(12)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("卡号(13)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("卡号(14)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("卡号(15)".equals(driveAttribute.getDescription())) {
			cardNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
			neObject.setCardNumber(cardNumber.toString());
		} else if ("盘号(1)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("盘号(2)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("盘号(3)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("盘号(4)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("盘号(5)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("盘号(6)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("盘号(7)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("盘号(8)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("盘号(9)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("盘号(10)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("盘号(11)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("盘号(12)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("盘号(13)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("盘号(14)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("盘号(15)".equals(driveAttribute.getDescription())) {
			plateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
			neObject.setPlateNumber(plateNumber.toString());
		} else if ("制盘时间(1)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("制盘时间(2)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("制盘时间(3)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("制盘时间(4)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("制盘时间(5)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("制盘时间(6)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("制盘时间(7)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("制盘时间(8)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("制盘时间(9)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("制盘时间(10)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("制盘时间(11)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("制盘时间(12)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("制盘时间(13)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("制盘时间(14)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("制盘时间(15)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
			neObject.setCreatePlateNumber(createPlateNumber.toString());
		}else if ("编程时间(1)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("编程时间(2)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("编程时间(3)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("编程时间(4)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("编程时间(5)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("编程时间(6)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("编程时间(7)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("编程时间(8)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("编程时间(9)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("编程时间(10)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("编程时间(11)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("编程时间(12)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("编程时间(13)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("编程时间(14)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("编程时间(15)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		}else if ("编程时间(16)".equals(driveAttribute.getDescription())) {
			programmeTime.append((char) Integer.parseInt(driveAttribute.getValue()));
			neObject.setProgrammeTime(programmeTime.toString());
		}else if ("BOOT编译时间(1)".equals(driveAttribute.getDescription())) {
			bootTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("BOOT编译时间(2)".equals(driveAttribute.getDescription())) {
			bootTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("BOOT编译时间(3)".equals(driveAttribute.getDescription())) {
			bootTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("BOOT编译时间(4)".equals(driveAttribute.getDescription())) {
			bootTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("BOOT编译时间(5)".equals(driveAttribute.getDescription())) {
			bootTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("BOOT编译时间(6)".equals(driveAttribute.getDescription())) {
			bootTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("BOOT编译时间(7)".equals(driveAttribute.getDescription())) {
			bootTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("BOOT编译时间(8)".equals(driveAttribute.getDescription())) {
			bootTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("BOOT编译时间(9)".equals(driveAttribute.getDescription())) {
			bootTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("BOOT编译时间(10)".equals(driveAttribute.getDescription())) {
			createPlateNumber.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("BOOT编译时间(11)".equals(driveAttribute.getDescription())) {
			bootTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("BOOT编译时间(12)".equals(driveAttribute.getDescription())) {
			bootTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("BOOT编译时间(13)".equals(driveAttribute.getDescription())) {
			bootTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("BOOT编译时间(14)".equals(driveAttribute.getDescription())) {
			bootTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("BOOT编译时间(15)".equals(driveAttribute.getDescription())) {
			bootTime.append((char) Integer.parseInt(driveAttribute.getValue()));
			neObject.setBootTime(bootTime.toString());
		}else if ("FPGA编译时间(1)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("FPGA编译时间(2)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("FPGA编译时间(3)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("FPGA编译时间(4)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("FPGA编译时间(5)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("FPGA编译时间(6)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("FPGA编译时间(7)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("FPGA编译时间(8)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("FPGA编译时间(9)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("FPGA编译时间(10)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("FPGA编译时间(11)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("FPGA编译时间(12)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("FPGA编译时间(13)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("FPGA编译时间(14)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
		} else if ("FPGA编译时间(15)".equals(driveAttribute.getDescription())) {
			fpgaTime.append((char) Integer.parseInt(driveAttribute.getValue()));
			neObject.setFpgaTime(fpgaTime.toString());
		}
//		else if ("设备MAC(1)".equals(driveAttribute.getDescription())) {
//			neMac=CoderUtils.synchTransformMac(driveAttribute.getValue());			
//		}else if ("设备MAC(2)".equals(driveAttribute.getDescription())) {
//			neMac=neMac+":"+CoderUtils.synchTransformMac(driveAttribute.getValue());			
//		}else if ("设备MAC(3)".equals(driveAttribute.getDescription())) {
//			neMac=neMac+":"+CoderUtils.synchTransformMac(driveAttribute.getValue());			
//		}else if ("设备MAC(4)".equals(driveAttribute.getDescription())) {
//			neMac=neMac+":"+CoderUtils.synchTransformMac(driveAttribute.getValue());			
//		}else if ("设备MAC(5)".equals(driveAttribute.getDescription())) {
//			neMac=neMac+":"+CoderUtils.synchTransformMac(driveAttribute.getValue());			
//		}else if ("设备MAC(6)".equals(driveAttribute.getDescription())) {
//			neMac=neMac+":"+CoderUtils.synchTransformMac(driveAttribute.getValue());
//			neObject.setNeMAC(neMac);
//		}
	}
	
	private String timeForm(String str){
		String time = "";
		if(str.length() == 1){
			time = "0"+str;
		}else{
			time = str;
		}
		return time;
	}
}