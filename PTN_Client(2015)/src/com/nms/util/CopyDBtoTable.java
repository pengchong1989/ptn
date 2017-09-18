package com.nms.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.nms.db.fac.f.Lg;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.systemManage.TranferData;

/**
 * function:根据数据库表名来获取表的结构
 * @author zk
 */
public class CopyDBtoTable {
	static Connection conn = null;
	private String ip;
	
	public CopyDBtoTable(){
	try {
		ip = InetAddress.getLocalHost().getHostAddress();
	 } catch (UnknownHostException e) {
		ExceptionManage.dispose(e,this.getClass());
	 }
	}
	
	
 public static void main(String[] args) {
	CopyDBtoTable CopyDBtoTable = new CopyDBtoTable();
	CopyDBtoTable.writeDBToFile();
	}
	
	
  public  void writeDBToFile(){
	try {
		List<String> tableList = getTableNameForXml("all");
		writeToFile(tableList);
	} catch (Exception e) {
		ExceptionManage.dispose(e,this.getClass());
	}
}
	/**
	 * @param key
	 *            复选框选中的标识
	 * @return
	 * @throws Exception
	 */
	private   List<String> getTableNameForXml(String key) throws Exception {
		List<String> tableName = new ArrayList<String>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet  = null;
		try {
			String sql = "show tables";
			preparedStatement = geConnection().prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				tableName.add(resultSet.getString("Tables_in_ptn"));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
					ExceptionManage.dispose(e,TranferData.class);
				}finally{
					resultSet = null;
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (Exception e) {
					ExceptionManage.dispose(e,TranferData.class);
				}finally{
					preparedStatement = null;
				}
			}
		}
		return tableName;
	}
	
	private  void writeToFile( List<String> tableNames){
		BufferedWriter bufferedWriter = null;
		FileWriter fileWriter = null;
		List<String> tableDataList = new ArrayList<String>();
		try {
			String fileName ="data";
			File Filelist = new File(fileName); 
			if(!Filelist.exists()){
				Filelist.mkdir();
			}
			fileName="data"+"\\"+"DataBaseStruct.xml";
			File fileData = new File(fileName); 
			if(!fileData.exists()){
				fileData.createNewFile();
			}
			System.out.println(fileData.getAbsolutePath()+"----------文件目录地址");
			fileWriter = new FileWriter(fileData.getAbsolutePath(),true);
			bufferedWriter = new BufferedWriter(fileWriter);
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("<?xml version="+'"'+1.0+'"'+" encoding="+'"'+"utf-8"+'"'+"?>");
			stringBuffer.append("\n");
			stringBuffer.append("<xml-body>");
			tableDataList.add(stringBuffer.toString());
			for(int i = 0; i < tableNames.size(); i++){
				tableDataList.add(converTableData(tableNames.get(i),i));
			}
			tableDataList.add("\n\n");
			tableDataList.add("</xml-body>");
			//将数据写入到指定的文件中
			for(String dbData : tableDataList){
				bufferedWriter.write(dbData);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 // 关闭流		
			 if (null != bufferedWriter) {
				 try {
					 bufferedWriter.close();
				 } catch (Exception e) {
					 ExceptionManage.dispose(e,this.getClass());
				 } finally {
					 bufferedWriter = null;
				}
			 }	
			 fileWriter = null;	
		 }
	}
	
	/**
	 * 根据数据库的表名称来查询数据库表结构并组成相应的数据格式
	 * @param tableName 数据库表名称
	 * @param leble 用来标记是第几个表 当是第一个表时头部将不会有空格
	 * @return 
	 */
	private  String converTableData(String tableName,int leble) {
		StringBuffer stringBuffer = new StringBuffer();
		PreparedStatement preparedStatement  = null;
		ResultSet resultSet = null;
		try {
			String sql = "desc `"+tableName +"`";
			preparedStatement = geConnection().prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			if(leble != 0){
				stringBuffer.append("\n\n");
			}else{
				stringBuffer.append("\n");	
			}
			stringBuffer.append("\t"+"<table tableName="+'"'+tableName+'"'+">"+"\n");
			String name = null;
			String[] typeAndLength = null;
			String type = null;
			String length = null;
			String isAllowNulls = null;
			String isFalse = null;
			String defaultValue = null;//默认值
			while(resultSet.next()){
				name = resultSet.getString("field");
				if(resultSet.getString("type").trim().contains("(")){
					typeAndLength = resultSet.getString("type").trim().split("\\(");
					type = typeAndLength[0];
					length = typeAndLength[1].substring(0,typeAndLength[1].length()-1);
				}else{
					type = resultSet.getString("type").trim();
					length = "0";
				}
				isFalse = resultSet.getString("null").toLowerCase();
				if(isFalse.equals("no")){
					isAllowNulls = "false";
				}else{
					isAllowNulls = "true";
				}
				if(type.equals("int")||type.equals("double")){
					defaultValue = "0";
				}else if(type.equals("varchar")){
					defaultValue = "";
				}else if(type.equals("datetime")){
					//1970 年 1 月 1 日 00:00:00 
					defaultValue = "1970-01-01 00:00:00";
				}
			  stringBuffer.append("\t"+"<attribute name="+'"'+name+'"'+" type="+'"'+type+'"'+" length="+'"'+length+'"'+" decimal="+'"'+0+'"'+" AllowNulls="+'"'+isAllowNulls +'"'+" defaultValue="+ '"'+defaultValue+'"'+"/>"+"\n");
			}
			stringBuffer.append("\t"+"</table>");
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
					ExceptionManage.dispose(e,TranferData.class);
				}finally{
					resultSet = null;
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (Exception e) {
					ExceptionManage.dispose(e,TranferData.class);
				}finally{
					preparedStatement = null;
				}
			}
		}
		return stringBuffer.toString();
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
  public  Connection geConnection() throws Exception{
	  String user;
	  String password;
	  String url;
	  String driver;
	  Properties props = new Properties();
	  try {
		  if (conn == null) {
			  InputStream propsIs = CopyDBtoTable.class.getClassLoader().getResourceAsStream("config/config.properties");
			  props.load(propsIs);
			  user = props.getProperty("jdbc.username");
			  password = props.getProperty("jdbc.password");
			  url = props.getProperty("jdbc.url");
			  url = url.replace("{?}",ip);
			  driver = props.getProperty("jdbc.driverClassName");
			  Lg.lg("user:"+user+"; password:"+password+"; url:"+url+"; driver"+driver);
			  Class.forName(driver);
			  conn = DriverManager.getConnection(url, user, password);
		  }
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	 return conn;
	}	
}
