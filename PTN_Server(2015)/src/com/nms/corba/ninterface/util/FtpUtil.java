package com.nms.corba.ninterface.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;

import com.nms.ui.manager.ExceptionManage;

public class FtpUtil {
	private FTPClient ftpClient = new FTPClient();
	private final static Logger LOG = Logger.getLogger(FtpUtil.class);

	/**
	 * 连接到FTP服务器
	 * 
	 * @param hostname
	 *            主机名
	 * @param port
	 *            端口
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 是否连接成功
	 * @throws IOException
	 */
	private boolean connect(String hostname, int port, String username,
			String password) throws IOException {
		ftpClient.connect(hostname, port);
		ftpClient.setControlEncoding("GBK");
		if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
			if (ftpClient.login(username, password)) {
				return true;
			}
		}
		disconnect();
		return false;
	}

	/**
	 * 断开与远程服务器的连接
	 * 
	 * @throws IOException
	 */
	private void disconnect() throws IOException {
		if (ftpClient.isConnected()) {
			ftpClient.disconnect();
		}
	}

	public boolean push2FtpServer(FtpInfo ftpInfo) {
		try {
			if (!connect(ftpInfo.ip, Integer.parseInt(ftpInfo.port),
					ftpInfo.user, ftpInfo.passWord)) {
				return false;
			}

			if (upload(ftpInfo.localFileName, ftpInfo.filePathAndName)) {
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
			return false;
		}
		return false;
	}

	// remote格式必须: /ftp/test/alarm.csv 或 /alarm.csv;
	private Boolean upload(String local, String remote) {
		FileInputStream fis = null;
		File srcFile = null;
		try {
			// 设置PassiveMode传输
			ftpClient.enterLocalPassiveMode();
			// 设置以二进制流的方式传输
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.setControlEncoding("GBK");
			ftpClient.setBufferSize(1024);

			// 对远程目录的处理
			if (remote.contains("/")) {
				String remoteFileName = remote.substring(remote
						.lastIndexOf("/") + 1);
				// 创建服务器远程目录结构，创建失败直接返回
				if (!CreateDirecroty(remote)) {
					return false;
				}
				srcFile = new File(local);
				fis = new FileInputStream(srcFile);
				ftpClient.storeFile(remoteFileName, fis);
				return true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			IOUtils.closeQuietly(fis);
			// 删除本地生成的文件
			FileTools.deleteFile(srcFile);
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				ExceptionManage.dispose(e,this.getClass());
				throw new RuntimeException("关闭FTP连接发生异常！", e);
			}
		}
		return false;
	}

	/**
	 * 递归创建远程服务器目录
	 * 
	 * @param remote
	 *            远程服务器文件绝对路径
	 * @param ftpClient
	 *            FTPClient对象
	 * @return 目录创建是否成功
	 * @throws IOException
	 */
	private boolean CreateDirecroty(String remote) throws IOException {
		String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
		if (!directory.equalsIgnoreCase("/")
				&& !ftpClient.changeWorkingDirectory(new String(directory
						.getBytes("GBK"), "iso-8859-1"))) {
			// 如果远程目录不存在，则递归创建远程服务器目录
			int start = 0;
			int end = 0;
			if (directory.startsWith("/")) {
				start = 1;
			} else {
				start = 0;
			}
			end = directory.indexOf("/", start);
			while (true) {
				String subDirectory = new String(remote.substring(start, end)
						.getBytes("GBK"), "iso-8859-1");
				if (!ftpClient.changeWorkingDirectory(subDirectory)) {
					if (ftpClient.makeDirectory(subDirectory)) {
						ftpClient.changeWorkingDirectory(subDirectory);
					} else {
						System.out.println("创建目录失败");
						return false;
					}
				}
				start = end + 1;
				end = directory.indexOf("/", start);
				// 检查所有目录是否创建完毕
				if (end <= start) {
					break;
				}
			}
		}
		return true;
	}

	public class FtpInfo {
		// 远程ip
		public String ip;
		// 远程port
		public String port;
		// remote 文件目录及文件名称
		public String filePathAndName;
		// 本地待上传文件路径及名称
		public String localFileName;
		// ftp 用户
		public String user;
		// ftp 密码
		public String passWord;

		public FtpInfo(String userName, String pw) {
			user = userName;
			passWord = pw;
		}
	}
}
