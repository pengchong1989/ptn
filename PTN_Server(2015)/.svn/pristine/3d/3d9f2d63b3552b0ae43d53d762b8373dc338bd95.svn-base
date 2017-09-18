package com.nms.snmp.ninteface.impl.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigTrap {
	/**
	 * 获取所有配置文件列表
	 */
	public List<String> getCmFileList(){
		List<String> fileList = new ArrayList<String>();
		this.getCmFileList("snmpData\\NRM", fileList);
		return fileList;
	}
	
	private void getCmFileList(String filePath, List<String> fileList) {
		File dir = new File(filePath);
		File[] fileArr = dir.listFiles();
		if(fileArr != null){
			for (File file : fileArr) {
				String fileName = file.getAbsolutePath();
				if(!fileList.contains(fileName)){
					fileList.add(fileName);
				}
			}
		}
	}

	/**
	 * 获取单个文件信息
	 */
	public String getCmFile(String fileName){
		File dir = new File("snmpData\\NRM\\"+fileName);
		if(dir.exists()){
			return dir.getAbsolutePath();
		}
		return "";
	}
	
	public static void main(String[] args) {
		String file = new ConfigTrap().getCmFile("EMS.xml");
		System.out.println(file);
	}
}
