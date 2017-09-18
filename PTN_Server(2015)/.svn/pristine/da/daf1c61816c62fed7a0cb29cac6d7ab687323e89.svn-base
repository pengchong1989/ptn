package com.nms.drive.analysis.xmltool;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.analysis.xmltool.bean.DriveAttribute;
import com.nms.drive.analysis.xmltool.bean.DriveRoot;
import com.nms.drive.analysis.xmltool.bean.ListRoot;
import com.nms.drive.service.impl.CoderUtils;

public class AnalysisDriveXmlToCommand {

	private List<byte[]> commandList = new ArrayList<byte[]>();
	private byte[] commands = new byte[0];
	private int maxByteArrayCount = 0;

	public byte[] analysisCommands(DriveRoot driveRoot) throws Exception {
		try {
			analysisRootDriveAttribute(driveRoot);
			commands = new byte[maxByteArrayCount];
			byte[] command = null;
			int count = 0;
			for (int i = 0; i < commandList.size(); i++) {
				command = commandList.get(i);
				for (int j = 0; j < command.length; j++) {
					commands[count] = command[j];
					count++;
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return commands;
	}

	/**
	 * 解析属性对象并生成命令
	 * 
	 * @param driveRoot
	 * @throws Exception
	 */
	private void analysisRootDriveAttribute(DriveRoot driveRoot) throws Exception {
		try {
			List<DriveAttribute> driveAttributeListRoot = driveRoot.getDriveAttributeList();
			List<DriveAttribute> childDriveAttributeList = null;
			List<ListRoot> listRootList = null;
			DriveAttribute driveAttributeRoot = null;
			if (driveAttributeListRoot != null) {
				for (int i = 0; i < driveAttributeListRoot.size(); i++) {
					driveAttributeRoot = driveAttributeListRoot.get(i);
					transformValueByBytes(driveAttributeRoot);

					listRootList = driveAttributeRoot.getListRootList();
					for (int j = 0; j < listRootList.size(); j++) {
						childDriveAttributeList = listRootList.get(j).getDriveAttributeList();
						analysisChildDriveAttribute(childDriveAttributeList);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 解析子属性对象并生成命令
	 * 
	 * @param childDriveAttributeList
	 * @throws Exception
	 */
	private void analysisChildDriveAttribute(List<DriveAttribute> childDriveAttributeList) throws Exception {
		try {
			List<DriveAttribute> childDriveAttributeList2 = null;
			DriveAttribute driveAttributeRootChild = null;
			List<ListRoot> listRootList = null;
			for (int i = 0; i < childDriveAttributeList.size(); i++) {
				driveAttributeRootChild = childDriveAttributeList.get(i);
				transformValueByBytes(driveAttributeRootChild);

				listRootList = driveAttributeRootChild.getListRootList();
				for (int j = 0; j < listRootList.size(); j++) {
					childDriveAttributeList2 = listRootList.get(j).getDriveAttributeList();
					analysisChildDriveAttribute(childDriveAttributeList2);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 根据Value值和字节长度，转换为字节数组
	 * 
	 * @param driveAttribute
	 * @throws Exception
	 */
	private void transformValueByBytes(DriveAttribute driveAttribute) throws Exception {
		try {
			// 这个方法以后扩展！
			// 根据关键字对不同的类型的数据进行格式化，封装成对象数组。
			int lengthByte = Integer.valueOf(driveAttribute.getLength());
			maxByteArrayCount += lengthByte;// 记录总命令大小

			int value = 0;
			if (driveAttribute.getValue() != null && !"".equals(driveAttribute.getValue())) {
				value = Integer.parseInt(driveAttribute.getValue());// 这个应该是VALUE测试展示用标记为主,以后要从对象获得并合并
			}

			byte[] valueByte = CoderUtils.intToBytes(value);
			byte[] command = null;
			if (lengthByte == 1) {
				command = new byte[1];
				command[0] = valueByte[3];
			} else if (lengthByte == 2) {
				command = new byte[2];
				command[0] = valueByte[2];
				command[1] = valueByte[3];
			} else if (lengthByte == 3) {
				command = new byte[3];
				command[0] = valueByte[1];
				command[1] = valueByte[2];
				command[2] = valueByte[3];
			} else if (lengthByte == 4) {
				command = new byte[4];
				command[0] = valueByte[0];
				command[1] = valueByte[1];
				command[2] = valueByte[2];
				command[3] = valueByte[3];
			} else {
				command = new byte[lengthByte];
				for (int i = 0; i < command.length; i++) {
					command[i] = 0x00;
				}
			}
			driveAttribute.setCommandByteArray(command);
			commandList.add(command);
		} catch (Exception e) {
			throw e;
		}
	}
}
