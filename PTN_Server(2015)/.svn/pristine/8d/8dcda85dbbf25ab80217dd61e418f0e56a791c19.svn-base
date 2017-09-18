package com.nms.drive.analysis.xmltool;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisCommandByDriveXml {

	private byte[] commands = new byte[0];
	private int startArrayCountTemp = 0;

	/**
	 * 通过配置XML解析字节流数组
	 * 
	 * @param xmlpath
	 * @return
	 * @throws Exception
	 */
	public DriveRoot analysisDriveAttrbute(String xmlpath) throws Exception {
		DriveRoot driveRoot_config = null;
		DriveRoot driveRoot_data = null;
		try {
			driveRoot_data = new DriveRoot();
			LoadDriveXml LoadDriveXml = new LoadDriveXml();
			driveRoot_config = LoadDriveXml.loadXmlToBean(xmlpath);
			List<DriveAttribute> driveAttributeList_config = driveRoot_config.getDriveAttributeList();

			DriveAttribute driveAttribute_config = null;
			DriveAttribute driveAttribute_data = null;
			List<ListRoot> listRootList_config = null;
			List<ListRoot> listRootList2 = null;
			List<ListRoot> listRootList3 = null;
			ListRoot listRoot = null;
			startArrayCountTemp = 0;
			int length = 0;
			byte[] arrayTemp = null;
			for (int i = 0; i < driveAttributeList_config.size(); i++) {
				listRootList3 = new ArrayList<ListRoot>();
				driveAttribute_config = driveAttributeList_config.get(i);
				length = Integer.valueOf(driveAttribute_config.getLength());
				arrayTemp = getStringArray(startArrayCountTemp, length);

				driveAttribute_config.setOffset(startArrayCountTemp + "");
				driveAttribute_data = getDriveAttribute(driveAttribute_config, arrayTemp);

				// System.out.println(driveAttribute_data.getLength() + ":" +
				// driveAttribute_data.getDescription() + ":【" + arrayTemp[0] +
				// "】");
				startArrayCountTemp += length;
				listRootList_config = driveAttribute_config.getListRootList();
				for (int j = 0; j < listRootList_config.size(); j++) {
					listRoot = listRootList_config.get(j);
					listRootList2 = analysisListRoot(listRoot, startArrayCountTemp, Integer.valueOf(arrayTemp[0]));
					if (listRootList2 != null) {
						listRootList3.addAll(listRootList2);
					}
				}
				driveAttribute_data.getListRootList().clear();
				driveAttribute_data.setListRootList(listRootList3);
				driveRoot_data.getDriveAttributeList().add(driveAttribute_data);
			}

		} catch (Exception e) {
			throw e;
		}
		return driveRoot_data;
	}

	/**
	 * 递归循环子嵌套并封装数据对象
	 * 
	 * @param _DriveAttribute
	 * @param listRoot
	 * @param startArrayCount
	 * @param count
	 * @return
	 */
	private List<ListRoot> analysisListRoot(ListRoot listRoot, int startArrayCount, int count) {
		DriveAttribute driveAttribute_config = null;
		DriveAttribute driveAttribute_data = null;
		List<DriveAttribute> driveAttributeList_config = listRoot.getDriveAttributeList();
		List<DriveAttribute> driveAttributeListTemp_data = null;// 临时存放
		List<ListRoot> listRootList_config = null;
		List<ListRoot> listRootList_data = new ArrayList<ListRoot>();
		List<ListRoot> listRootList3 = null;
		List<ListRoot> listRootList4 = null;
		ListRoot listRootTemp_config = null;
		ListRoot listRootTemp_data = null;
		startArrayCountTemp = startArrayCount;
		int length = 0;
		byte[] arrayTemp = null;
		for (int i = 0; i < count; i++) {
			driveAttributeListTemp_data = new ArrayList<DriveAttribute>();
			for (int j = 0; j < driveAttributeList_config.size(); j++) {
				listRootList4 = new ArrayList<ListRoot>();
				driveAttribute_config = driveAttributeList_config.get(j);
				length = Integer.valueOf(driveAttribute_config.getLength());
				arrayTemp = getStringArray(startArrayCountTemp, length);

				driveAttribute_config.setOffset(startArrayCountTemp + "");
				driveAttribute_data = getDriveAttribute(driveAttribute_config, arrayTemp);
				driveAttributeListTemp_data.add(driveAttribute_data);// 命令没一个值

				// System.out.println(driveAttribute_data.getLength() + ":" +
				// driveAttribute_data.getDescription() + ":【" + arrayTemp[0] +
				// "】");
				startArrayCountTemp += length;
				listRootList_config = driveAttribute_config.getListRootList();
				for (int k = 0; k < listRootList_config.size(); k++) {
					listRootTemp_config = listRootList_config.get(k);
					listRootList3 = analysisListRoot(listRootTemp_config, startArrayCountTemp, transformLength(arrayTemp));
					if (listRootList3 != null) {
						listRootList4.addAll(listRootList3);
					}
				}
				driveAttribute_data.getListRootList().clear();
				if (listRootList4.size() > 0) {
					driveAttribute_data.getListRootList().addAll(listRootList4);
				}
			}
			listRootTemp_data = new ListRoot();
			listRootTemp_data.setDriveAttributeList(driveAttributeListTemp_data);
			listRootList_data.add(listRootTemp_data);
		}
		return listRootList_data;
	}

	/**
	 * 根据配置对象生成数据对象
	 * 
	 * @param driveAttribute配置对象
	 * @param CommandbyteArray
	 * @return
	 */
	private DriveAttribute getDriveAttribute(DriveAttribute driveAttribute_config, byte[] CommandbyteArray) {
		DriveAttribute driveAttribute_Data = new DriveAttribute();
		driveAttribute_Data.setOffset(driveAttribute_config.getOffset());
		driveAttribute_Data.setCommandByteArray(CommandbyteArray);
		driveAttribute_Data.setDescription(driveAttribute_config.getDescription());
		driveAttribute_Data.setLength(driveAttribute_config.getLength());
		driveAttribute_Data.setMapping(driveAttribute_config.getMapping());
		driveAttribute_Data.setValue(transformBytesByValue(CommandbyteArray));
		return driveAttribute_Data;
	}

	/**
	 * 将字节转换为字符串
	 * 
	 * @param CommandbyteArray
	 * @return
	 */
	private String transformBytesByValue(byte[] CommandbyteArray) {
		String returnStr = "";
		byte[] valueByte = new byte[4];
		if (CommandbyteArray != null && CommandbyteArray.length == 1) {
			valueByte[0] = 0x00;
			valueByte[1] = 0x00;
			valueByte[2] = 0x00;
			valueByte[3] = CommandbyteArray[0];
			returnStr = "" + CoderUtils.bytesToInt(valueByte);
		} else if (CommandbyteArray != null && CommandbyteArray.length == 2) {
			valueByte[0] = 0x00;
			valueByte[1] = 0x00;
			valueByte[2] = CommandbyteArray[0];
			valueByte[3] = CommandbyteArray[1];
			returnStr = "" + CoderUtils.bytesToInt(valueByte);
		} else if (CommandbyteArray != null && CommandbyteArray.length == 3) {
			valueByte[0] = 0x00;
			valueByte[1] = CommandbyteArray[0];
			valueByte[2] = CommandbyteArray[1];
			valueByte[3] = CommandbyteArray[2];
			returnStr = "" + CoderUtils.bytesToInt(valueByte);
		} else if (CommandbyteArray != null && CommandbyteArray.length == 4) {
			valueByte[0] = CommandbyteArray[0];
			valueByte[1] = CommandbyteArray[1];
			valueByte[2] = CommandbyteArray[2];
			valueByte[3] = CommandbyteArray[3];
			returnStr = "" + CoderUtils.bytesToInt(valueByte);
		} else {
			returnStr = new String(CommandbyteArray);
		}

		return returnStr;

	}

	/**
	 * 转会长度用于循环次数使用
	 * 
	 * @param lengthbyte
	 * @return
	 */
	private int transformLength(byte[] lengthbyte) {
		return Integer.valueOf(lengthbyte[0]);
	}

	/**
	 * 根据范围获得命令字节
	 * 
	 * @param startArrayCount
	 * @param length
	 * @return
	 */
	private byte[] getStringArray(int startArrayCount, int length) {
		byte[] array = new byte[length];
		for (int i = 0; i < length; i++) {
			array[i] = commands[startArrayCount];
			startArrayCount++;
		}
		return array;
	}

	/**
	 * 输出所有解析的参数
	 * 
	 * @param driveAttributeList
	 */
	public void print(List<DriveAttribute> driveAttributeList) {
		String sss = "";
		for (int i = 0; i < driveAttributeList.size(); i++) {
			sss = "【" + driveAttributeList.get(i).getOffset() + "," + (Integer.valueOf(driveAttributeList.get(i).getOffset()) + Integer.valueOf(driveAttributeList.get(i).getLength()) - 1) + "】 : 【" + driveAttributeList.get(i).getDescription() + "】 : 【";
			byte[] dddd = driveAttributeList.get(i).getCommandByteArray();
			sss += aa(dddd[0]);
			for (int j = 1; j < dddd.length; j++) {
				sss += "," + aa(dddd[j]);
			}
			sss += "】 : 【" + driveAttributeList.get(i).getMapping() + "】";
			System.out.println(sss);

			List<ListRoot> listRootList = driveAttributeList.get(i).getListRootList();
			for (int j = 0; j < listRootList.size(); j++) {
				print(listRootList.get(j).getDriveAttributeList());
			}
		}
	}

	/**
	 * 通过driveAttributeList封装Bean
	 */
	private void packageBean() {
		 

	}

	public static int aa(byte b) {
		int a = b;
		if (a < 0) {
			return a + 256;
		} else {
			return a;
		}

	}

	/**
	 * 赋值命令
	 * 
	 * @param commands
	 */
	public void setCommands(byte[] commands) {
		this.commands = commands;
	}

}
