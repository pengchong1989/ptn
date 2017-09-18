package com.nms.corba.ninterface.impl.util;

import com.nms.corba.ninterface.util.FtpUtil.FtpInfo;

public class StringUtil {
	// destination 格式：
	// "127.0.0.1|/alarm.csv|21"或"127.0.0.1|/ftp/alarm.csv"
	public static boolean FtpDestinationParse(String destination,
			FtpInfo ftpInfo) {
		String[] subStrArray = destination.split("\\|");
		if (subStrArray.length != 2 && subStrArray.length != 3) {
			return false;
		}
		// 解析destination ip
		ftpInfo.ip = subStrArray[0];
		// 远程文件路径及名称
		ftpInfo.filePathAndName = subStrArray[1];
		String[] pathArray = ftpInfo.filePathAndName.split("\\/");
		int size = pathArray.length;
		// 本地文件名称
		ftpInfo.localFileName = pathArray[size - 1];
		// ftp端口
		ftpInfo.port = "21";
		if (subStrArray.length == 3) {
			ftpInfo.port = subStrArray[2];
		}
		return true;
	}
}
