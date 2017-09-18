package com.nms.snmp.ninteface.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.poi.util.IOUtils;

import com.nms.snmp.ninteface.impl.ftp.FtpInfo;
import com.nms.ui.manager.ExceptionManage;

public class FtpTransUtil {
	private FTPClient ftpClient = new FTPClient();
	private List<String> transFileList = null;//指定的文件上传

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
		this.disconnect();
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

	public boolean push2FtpServer(FtpInfo ftpInfo, List<String> transFileList) throws Exception {
		try {
			this.transFileList = transFileList;
			if (!connect(ftpInfo.ip, Integer.parseInt(ftpInfo.port),
					ftpInfo.user, ftpInfo.passWord)) {
				return false;
			}

			if (this.upload(ftpInfo.localFileName, ftpInfo.filePathAndName)) {
				return true;
			}
		} catch (IOException e) {
			throw new Exception("ftp connection is fail");
		}
		return false;
	}

	/**
	 *  上传所有文件
	 *  remote格式必须: /ftp/test/alarm.xml
	 * @throws Exception 
	 */
	private Boolean upload(String local, String remote) throws Exception {
		FileInputStream fis = null;
		try {
			// 设置PassiveMode传输
			ftpClient.enterLocalPassiveMode();
			// 设置以二进制流的方式传输
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			ftpClient.setControlEncoding("GBK");
			ftpClient.setBufferSize(1024);

			// 对远程目录的处理
			if (remote.contains("/")) {
//				String remoteFileName = remote.substring(remote
//						.lastIndexOf("/") + 1);
				// 创建服务器远程目录结构，创建失败直接返回
				if (!CreateDirecroty(remote)) {
					return false;
				}
				this.uploadFile(new File(local));
				return true;
			}
		} catch (IOException e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			IOUtils.closeQuietly(fis);
			// 删除本地生成的文件
//			FileTools.deleteFile(srcFile);
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
	 * 根据文件目录查询所有文件并逐个上传
	 */
	private void uploadFile(File file) throws Exception{      
        if(file.isDirectory()){           
        	ftpClient.makeDirectory(file.getName());                
        	ftpClient.changeWorkingDirectory(file.getName());      
            String[] files = file.list();             
            for (int i = 0; i < files.length; i++) {      
                File file1 = new File(file.getPath()+"\\"+files[i] );      
                if(file1.isDirectory()){      
                    this.uploadFile(file1);      
                    ftpClient.changeToParentDirectory();      
                }else{            
                	File file2 = new File(file.getPath()+"\\"+files[i]);
                	if(this.transFileList == null){
                		this.putFile(file2);
                	}else{
                		if(this.transFileList.contains(file2.getPath())){
                			this.putFile(file2); 
                		}
                	}
                }                 
            }      
        }else{      
            File file2 = new File(file.getPath());
            if(this.transFileList == null){
            	this.putFile(file2);
            }else{
            	if(this.transFileList.contains(file2.getPath())){
            		this.putFile(file2);
            	}
            }
        }      
    }      

	private void putFile(File file) throws IOException {
		FileInputStream input = new FileInputStream(file);   
		ftpClient.storeFile(file.getName(), input); 
        input.close(); 
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
}
