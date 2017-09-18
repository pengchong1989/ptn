package com.nms.drive.analysis.datablock;

import com.nms.drive.analysis.xmltool.AnalysisCommandByDriveXml;
import com.nms.drive.analysis.xmltool.AnalysisDriveXmlToCommand;
import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.service.bean.ClockObject;
import com.nms.drive.service.impl.CoderUtils;

/**
 * 时钟配置块
 * 
 * @author luolei
 * 
 */
public class AnalysisClockTable extends AnalysisBase {
	
	/**
	 * ClockObject对象转化为命令
	 * 
	 * @param clockObject
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public byte[] analysisClockObjectToCommands(ClockObject clockObject, String configXml) throws Exception {
		try {
			DriveRoot driveRoot_config = super.LoadDriveXml(configXml);
			DriveAttribute driveAttribute = null;
			
			String[] priS  = clockObject.getClockPRIS().split("/");// 时钟优先级排列
			String[] outSelectS = clockObject.getOutSelectS().split("/");// 输出时钟选择
			String[] inQLValueS = clockObject.getClockInQLValueS().split("/");// 输入源QL值GE1
			String[] outQLValueS = clockObject.getClockOutQLValueS().split("/");// 输出源的QL值GE1
			
			for (int i = 0; i < driveRoot_config.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot_config.getDriveAttributeList().get(i);
				
				assignDriveAttribute(driveAttribute, clockObject,priS,outSelectS,inQLValueS,outQLValueS);// 赋值
			}
			AnalysisDriveXmlToCommand analysisDriveXmlToCommand = new AnalysisDriveXmlToCommand();
			byte[] commands = analysisDriveXmlToCommand.analysisCommands(driveRoot_config);
			CoderUtils.print16String(commands);
			return commands;

		} catch (Exception e) {

			throw e;
		}

	}

	/**
	 * 命令转化为ClockObject对象
	 * 
	 * @param commands
	 * @param configXml
	 * @return
	 * @throws Exception
	 */
	public ClockObject analysisCommandsToClockObject(byte[] commands, String configXml) throws Exception {
		int start = 0;
		byte[] clockByte = new byte[172];
		System.arraycopy(commands, start, clockByte, 0, clockByte.length);
		AnalysisCommandByDriveXml analysisCommandByDriveXml = new AnalysisCommandByDriveXml();
		analysisCommandByDriveXml.setCommands(clockByte);
		
		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb3 = new StringBuffer();
		StringBuffer sb4 = new StringBuffer();
		try {
			DriveRoot driveRoot = analysisCommandByDriveXml.analysisDriveAttrbute(configXml);
			DriveAttribute driveAttribute = null;
			ClockObject clockObject = new ClockObject();
			for (int i = 0; i < driveRoot.getDriveAttributeList().size(); i++) {
				driveAttribute = driveRoot.getDriveAttributeList().get(i);
				assignDriveAttribute(clockObject, driveAttribute, sb1, sb2, sb3, sb4);// 赋值
			}
			return clockObject;
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 给driveRoot赋值
	 * 
	 * @param driveAttribute
	 * @param clockObject
	 */
	private void assignDriveAttribute(DriveAttribute driveAttribute, ClockObject clockObject,String[] s1,String[] s2,String[] s3,String[] s4) {
		if ("时钟工作模式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(clockObject.getClockWorkMode() + "");
		} else if ("QL使能选择式".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(clockObject.getQlEnable() + "");
		} else if ("时钟优先级排列1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[0] + "");
		} else if ("时钟优先级排列2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[1] + "");
		} else if ("时钟优先级排列3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[2] + "");
		} else if ("时钟优先级排列4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[3] + "");
   		} else if ("时钟优先级排列5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[4] + "");
		} else if ("时钟优先级排列6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[5] + "");
		} else if ("时钟优先级排列7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[6] + "");
		} else if ("时钟优先级排列8".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[7]+ "");
		} else if ("时钟优先级排列9".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[8] + "");
		} else if ("时钟优先级排列10".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[9] + "");
		} else if ("时钟优先级排列11".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[10]+ "");
		} else if ("时钟优先级排列12".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[11]+ "");
		} else if ("时钟优先级排列13".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[12]+ "");
		} else if ("时钟优先级排列14".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[13]+ "");
		} else if ("时钟优先级排列15".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[14]+ "");
		} else if ("时钟优先级排列16".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[15]+ "");
		} else if ("时钟优先级排列17".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[16] + "");
		} else if ("时钟优先级排列18".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[17]+ "");
		} else if ("时钟优先级排列19".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[18]+ "");
		} else if ("时钟优先级排列20".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[19]+ "");
		} else if ("时钟优先级排列21".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[20]+ "");
		} else if ("时钟优先级排列22".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[21] + "");
		} else if ("时钟优先级排列23".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[22] + "");
		} else if ("时钟优先级排列24".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[23]+ "");
		} else if ("时钟优先级排列25".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[24]+ "");
		} else if ("时钟优先级排列26".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[25] + "");
		} else if ("时钟优先级排列27".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[26]+ "");
		} else if ("时钟优先级排列28".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[27]+ "");
		} else if ("时钟优先级排列29".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[28]+ "");
		} else if ("时钟优先级排列30".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[29]+ "");
		} else if ("时钟优先级排列31".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[30] + "");
		} else if ("时钟优先级排列32".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s1[31] + "");
		} else if ("外时钟输入选择".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(clockObject.getOuterClockInSelect() + "");
		} else if ("外时钟输出使能选择1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(clockObject.getOuterClockOutSelect1() + "");
		} else if ("外时钟输出使能选择2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(clockObject.getOuterClockOutSelect2() + "");
		} else if ("输出时钟选择1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[0] + "");
		} else if ("输出时钟选择2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[1] + "");
		} else if ("输出时钟选择3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[2] + "");
		} else if ("输出时钟选择4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[3]+ "");
		} else if ("输出时钟选择5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[4]+ "");
		} else if ("输出时钟选择6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[5]+ "");
		} else if ("输出时钟选择7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[6]+ "");
		} else if ("输出时钟选择8".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[7] + "");
		} else if ("输出时钟选择9".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[8] + "");
		} else if ("输出时钟选择10".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[9] + "");
		} else if ("输出时钟选择11".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[10] + "");
		} else if ("输出时钟选择12".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[11] + "");
		} else if ("输出时钟选择13".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[12] + "");
		} else if ("输出时钟选择14".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[13] + "");
		} else if ("输出时钟选择15".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[14] + "");
		} else if ("输出时钟选择16".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[15] + "");
		} else if ("输出时钟选择17".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[16]+ "");
		} else if ("输出时钟选择18".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[17] + "");
		} else if ("输出时钟选择19".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[18] + "");
		} else if ("输出时钟选择20".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[19] + "");
		} else if ("输出时钟选择21".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[20] + "");
		} else if ("输出时钟选择22".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[21] + "");
		} else if ("输出时钟选择23".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[22] + "");
		} else if ("输出时钟选择24".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[23] + "");
		} else if ("输出时钟选择25".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[24] + "");
		} else if ("输出时钟选择26".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[25] + "");
		} else if ("输出时钟选择27".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[26]+ "");
		} else if ("输出时钟选择28".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[27] + "");
		} else if ("输出时钟选择29".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[28] + "");
		} else if ("输出时钟选择30".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[29] + "");
		} else if ("输出时钟选择31".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[30] + "");
		} else if ("输出时钟选择32".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s2[31] + "");
		} else if ("SM门限外时钟".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(clockObject.getSmOuter() + "");
		} else if ("SM门限系统时钟".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(clockObject.getSmSystem() + "");
		} else if ("QL使用SA选择输入源SA选择1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(clockObject.getQlIn() + "");
		} else if ("QL使用SA选择输出源SA选择2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(clockObject.getQlOut()+ "");
		} else if ("输入源QL值外时钟".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[0] + "");
		} else if ("输入源QL值GE1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[1] + "");
		} else if ("输入源QL值GE2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[2] + "");
		} else if ("输入源QL值GE3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[3] + "");
		} else if ("输入源QL值GE4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[4]+ "");
		} else if ("输入源QL值GE5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[5]+ "");
		} else if ("输入源QL值GE6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[6]+ "");
		} else if ("输入源QL值GE7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[7] + "");
		} else if ("输入源QL值GE8".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[8] + "");
		} else if ("输入源QL值GE9".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[9] + "");
		} else if ("输入源QL值GE10".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[10] + "");
		} else if ("输入源QL值GE2.1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[11] + "");
		} else if ("输入源QL值GE2.2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[12] + "");
		} else if ("输入源QL值GE2.3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[13] + "");
		} else if ("输入源QL值GE2.4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[14] + "");
		} else if ("输入源QL值GE2.5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[15] + "");
		}else if ("输入源QL值GE2.6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[16] + "");
		}else if ("输入源QL值GE2.7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[17] + "");
		}else if ("输入源QL值GE2.8".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[18] + "");
		}else if ("输入源QL值GE3.1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[19] + "");
		}else if ("输入源QL值GE3.2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[20] + "");
		}else if ("输入源QL值GE3.3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[21] + "");
		}else if ("输入源QL值GE3.4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[22] + "");
		}else if ("输入源QL值GE3.5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[23] + "");
		}else if ("输入源QL值GE3.6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[24] + "");
		}else if ("输入源QL值GE3.7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[25] + "");
		}else if ("输入源QL值GE3.8".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s3[26] + "");
		}
		
		else if ("输出源QL值外时钟".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[0] + "");
		} else if ("输出源QL值GE1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[1] + "");
		} else if ("输出源QL值GE2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[2] + "");
		} else if ("输出源QL值GE3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[3] + "");
		} else if ("输出源QL值GE4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[4]+ "");
		} else if ("输出源QL值GE5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[5]+ "");
		} else if ("输出源QL值GE6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[6]+ "");
		} else if ("输出源QL值GE7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[7] + "");
		} else if ("输出源QL值GE8".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[8] + "");
		} else if ("输出源QL值GE9".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[9] + "");
		} else if ("输出源QL值GE10".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[10] + "");
		} else if ("输出源QL值GE2.1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[11] + "");
		} else if ("输出源QL值GE2.2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[12] + "");
		} else if ("输出源QL值GE2.3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[13] + "");
		} else if ("输出源QL值GE2.4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[14] + "");
		} else if ("输出源QL值GE2.5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[15] + "");
		}else if ("输出源QL值GE2.6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[16] + "");
		}else if ("输出源QL值GE2.7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[17] + "");
		}else if ("输出源QL值GE2.8".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[18] + "");
		}else if ("输出源QL值GE3.1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[19] + "");
		}else if ("输出源QL值GE3.2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[20] + "");
		}else if ("输出源QL值GE3.3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[21] + "");
		}else if ("输出源QL值GE3.4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[22] + "");
		}else if ("输出源QL值GE3.5".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[23] + "");
		}else if ("输出源QL值GE3.6".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[24] + "");
		}else if ("输出源QL值GE3.7".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[25] + "");
		}else if ("输出源QL值GE3.8".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(s4[26] + "");
		} 
		else if ("时钟输入源等待恢复时间".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(clockObject.getClockInResumeTime() + "");
		} else if ("等待恢复时间开关1".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tocommand1(clockObject) + "");
		} else if ("等待恢复时间开关2".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tocommand2(clockObject) + "");
		} else if ("等待恢复时间开关3".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tocommand3(clockObject) + "");
		} else if ("等待恢复时间开关4".equals(driveAttribute.getDescription())) {
			driveAttribute.setValue(tocommand4(clockObject) + "");
		} 
	}

	/**
	 *  等待恢复时间开关属性  转命令
	 */
	public int tocommand1(ClockObject c){
		String s = c.getR17()+c.getR16()+c.getR15()+c.getR14()+c.getR13()+c.getR12()+c.getR11()+c.getR10();
		s = eightBinary(s);
		char[] result = s.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a;
	}
	public int tocommand2(ClockObject c){
		String s =c.getR25()+c.getR24()+c.getR23()+c.getR22()+c.getR21()+c.getR20()+c.getR19()+c.getR18();
		s = eightBinary(s);
		char[] result = s.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a;
	}
	public int tocommand3(ClockObject c){
		String s = c.getR35()+c.getR34()+c.getR33()+c.getR32()+c.getR31()+c.getR28()+c.getR27()+c.getR26();
		s = eightBinary(s);
		char[] result = s.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a;
	}
	public int tocommand4(ClockObject c){
		String s = c.getR38()+c.getR37()+c.getR36();
		s = eightBinary(s);
		char[] result = s.toString().toCharArray();
		int a = CoderUtils.convertAlgorism(result);
		return a;
	}
	/**
	 * 将字符串补足8位
	 * @param str
	 */
	private String eightBinary(String str){
		String s = null;
		StringBuffer sb = new StringBuffer();
		if(str.length()<8){			
			for (int i = str.length(); i < 8; i++) {
				sb.append("0");
			}		
		}
		sb.append(str);
		s = sb.toString();
		return s;
	}
	
	/**
	 * 给ClockObject对象赋值
	 * 同步用的
	 * @param clockObject
	 * @param driveAttribute
	 */
	private void assignDriveAttribute(ClockObject c, DriveAttribute driveAttribute,StringBuffer s1,StringBuffer s2,StringBuffer s3,StringBuffer s4) {
		if ("时钟工作模式".equals(driveAttribute.getDescription())) {
			c.setClockWorkMode(Integer.parseInt(driveAttribute.getValue()));
		} else if ("QL使能选择式".equals(driveAttribute.getDescription())) {
			c.setQlEnable(Integer.parseInt(driveAttribute.getValue()));
		} else if ("时钟优先级排列1".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列2".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列3".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列4".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列5".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列6".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列7".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列8".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列9".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列10".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列11".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列12".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列13".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列14".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列15".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列16".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列17".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列18".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列19".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列20".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列21".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列22".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列23".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		}else if ("时钟优先级排列24".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列25".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列26".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列27".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列28".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列29".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列30".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列31".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue()+"/");
		} else if ("时钟优先级排列32".equals(driveAttribute.getDescription())) {
			s1.append(driveAttribute.getValue());
			c.setClockPRIS(s1.toString());
		} else if ("外时钟输入选择".equals(driveAttribute.getDescription())) {
			c.setOuterClockInSelect(Integer.parseInt(driveAttribute.getValue()));
		} else if ("外时钟输出使能选择1".equals(driveAttribute.getDescription())) {
			c.setOuterClockOutSelect1(Integer.parseInt(driveAttribute.getValue()));
		} else if ("外时钟输出使能选择2".equals(driveAttribute.getDescription())) {
			c.setOuterClockOutSelect2(Integer.parseInt(driveAttribute.getValue()));
		} else if ("输出时钟选择1".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择2".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择3".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择4".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择5".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择6".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择7".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择8".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择9".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择10".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择11".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择12".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择13".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择14".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择15".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择16".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择17".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择18".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择19".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择20".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择21".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择22".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择23".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择24".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择25".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择26".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择27".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择28".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择29".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择30".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择31".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue()+"/");
		} else if ("输出时钟选择32".equals(driveAttribute.getDescription())) {
			s2.append(driveAttribute.getValue());
			c.setOutSelectS(s2.toString());
			
		} else if ("SM门限外时钟".equals(driveAttribute.getDescription())) {
			c.setSmOuter(Integer.parseInt(driveAttribute.getValue()));
		} else if ("SM门限系统时钟".equals(driveAttribute.getDescription())) {
			c.setSmSystem(Integer.parseInt(driveAttribute.getValue()));
		} else if ("QL使用SA选择输入源SA选择1".equals(driveAttribute.getDescription())) {
			c.setQlIn(Integer.parseInt(driveAttribute.getValue()));
		} else if ("QL使用SA选择输出源SA选择2".equals(driveAttribute.getDescription())) {
			c.setQlOut(Integer.parseInt(driveAttribute.getValue()));
			
		} else if ("输入源QL值外时钟".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE1".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE2".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE3".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE4".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE5".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE6".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE7".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE8".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE9".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE10".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE2.1".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE2.2".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE2.3".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE2.4".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE2.5".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE2.6".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE2.7".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		} else if ("输入源QL值GE2.8".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		}else if ("输入源QL值GE3.1".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		}else if ("输入源QL值GE3.2".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		}else if ("输入源QL值GE3.3".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		}else if ("输入源QL值GE3.4".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		}else if ("输入源QL值GE3.5".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		}else if ("输入源QL值GE3.6".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		}else if ("输入源QL值GE3.7".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue()+"/");
		}else if ("输入源QL值GE3.8".equals(driveAttribute.getDescription())) {
			s3.append(driveAttribute.getValue());
			c.setClockInQLValueS(s3.toString());
		}  else if ("输出源QL值外时钟".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		}  else if ("输出源QL值GE1".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE2".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE3".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE4".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE5".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE6".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE7".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE8".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE9".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE10".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE2.1".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE2.2".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE2.3".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE2.4".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE2.5".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		}else if ("输出源QL值GE2.6".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE2.7".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE2.8".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE3.1".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE3.2".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE3.3".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE3.4".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE3.5".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE3.6".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");	
		} else if ("输出源QL值GE3.7".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue()+"/");
		} else if ("输出源QL值GE3.8".equals(driveAttribute.getDescription())) {
			s4.append(driveAttribute.getValue());
			c.setClockOutQLValueS(s4.toString());
		} else if ("时钟输入源等待恢复时间".equals(driveAttribute.getDescription())) {
			c.setClockInResumeTime(Integer.parseInt(driveAttribute.getValue()));
		} else if ("等待恢复时间开关1".equals(driveAttribute.getDescription())) {
			TOcv1(c, driveAttribute);
		} else if ("等待恢复时间开关2".equals(driveAttribute.getDescription())) {
			TOcv2(c, driveAttribute);
		} else if ("等待恢复时间开关3".equals(driveAttribute.getDescription())) {
			TOcv3(c, driveAttribute);
		} else if ("等待恢复时间开关4".equals(driveAttribute.getDescription())) {
			TOcv4(c, driveAttribute);
		}
	}

	/**
	 * 命令转 等待恢复时间开关 属性
	 * @param info
	 * @param driveAttribute
	 */
	private void TOcv1(ClockObject info,DriveAttribute driveAttribute){
		String str = eightBinary(Integer.toBinaryString(Integer.parseInt(driveAttribute.getValue())));
		info.setR17(str.substring(0,1));
		info.setR16(str.substring(1,2));
		info.setR15(str.substring(2,3));
		info.setR14(str.substring(3,4));
		info.setR13(str.substring(4,5));
		info.setR12(str.substring(5,6));
		info.setR11(str.substring(6,7));
		info.setR10(str.substring(7,8));
		
	}
	private void TOcv2(ClockObject info,DriveAttribute driveAttribute){
		String str = eightBinary(Integer.toBinaryString(Integer.parseInt(driveAttribute.getValue())));
		info.setR25(str.substring(0,1));
		info.setR24(str.substring(1,2));
		info.setR23(str.substring(2,3));
		info.setR22(str.substring(3,4));
		info.setR21(str.substring(4,5));
		info.setR20(str.substring(5,6));
		info.setR19(str.substring(6,7));
		info.setR18(str.substring(7,8));
	}
	private void TOcv3(ClockObject info,DriveAttribute driveAttribute){
		String str = eightBinary(Integer.toBinaryString(Integer.parseInt(driveAttribute.getValue())));
		info.setR35(str.substring(0,1));
		info.setR34(str.substring(1,2));
		info.setR33(str.substring(2,3));
		info.setR32(str.substring(3,4));
		info.setR31(str.substring(4,5));
		info.setR28(str.substring(5,6));
		info.setR27(str.substring(6,7));
		info.setR26(str.substring(7,8));
	}
	private void TOcv4(ClockObject info,DriveAttribute driveAttribute){
		String str = eightBinary(Integer.toBinaryString(Integer.parseInt(driveAttribute.getValue())));
		info.setR38(str.substring(5,6));
		info.setR37(str.substring(6,7));
		info.setR36(str.substring(7,8));
	}

}