package com.nms.snmp.ninteface.impl.ftp;

import java.util.List;

import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.snmp.ninteface.util.FtpTransUtil;
import com.nms.ui.manager.ExceptionManage;

public class FtpTransFile4SNMP {
	
	private List<String> transFileList;
	
	public FtpTransFile4SNMP(List<String> transFileList) {
		this.transFileList = transFileList;
	}

	public FtpTransFile4SNMP() {

	}

	public static void main(String[] args) throws Exception {
		new FtpTransFile4SNMP().FileTranfer();
	}
	
	/**
	 * 历史性能文件,历史告警文件,网管配置文件的传输（FTP）
	 * @throws Exception 
	 */
	public boolean FileTranfer() throws Exception{
		
		FtpTransUtil ftp = new FtpTransUtil();
		FtpInfo ftpInfo = new FtpInfo(SnmpConfig.getInstanse().getUserName(), SnmpConfig.getInstanse().getPassWord());
		if(!FtpDestinationParse(ftpInfo)){
			throw new Exception("destination is invalid input!");
		}
		boolean status = false;
		try {
			// 将本地指定的文件通过ftp上传到指定路径
			status = ftp.push2FtpServer(ftpInfo, this.transFileList);
		}catch(Exception e){
			ExceptionManage.dispose(e, this.getClass());
		}
		return status;
	}
	
	private boolean FtpDestinationParse(FtpInfo ftpInfo) {
		try {
			// 解析destination ip
			ftpInfo.ip = SnmpConfig.getInstanse().getRemoteIp();
			// ftp端口
			ftpInfo.port = SnmpConfig.getInstanse().getRemotePort();
			// 远程文件路径及名称
			ftpInfo.filePathAndName = SnmpConfig.getInstanse().getRemoteFilePath();
			// 本地文件名称
			ftpInfo.localFileName = "snmpData";
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
