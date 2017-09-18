package com.nms.ui.datamanager.databackup;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.nms.rmi.ui.util.ServerConstant;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;

/**
 * 备份数据库
 * 
 * @author kk
 * 
 */
public class BackupsDatabase {

	/**
	 * 文件对象
	 */
	private File file = null;

	/**
	 * 备份数据库
	 * 
	 * @param tablenames
	 *            要备份的表名
	 * @param filePath
	 *            备份的路径
	 */
	public boolean backups(List<String> tablenames, String filePath) {
		try {
			this.createFile(filePath);
			DataBaseUtil dbUtil = new DataBaseUtil();
			if(tablenames.size() > 0){
				dbUtil.backUpRemote(this.file, tablenames,ConstantUtil.serviceIp);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			return false;
		}
		return true;
	}
//	public void backups(List<String> tablenames, String filePath) {
//
//		List<TableBean> tableBeanList = null;
//		StringBuffer stringBufferTable = null;
//		FileOutputStream fileOutputStream = null;
//		OutputStreamWriter outputStreamWriter = null;
//		Connection con = null;
//		try {
//			con = DBManager.getInstance().getConnection();
//			// 创建文件
//			this.createFile(filePath);
//			fileOutputStream = new FileOutputStream(this.file);
//			outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
//			// 写入文件头部
//			this.writeFileHead(tablenames, outputStreamWriter);
//
//			// 遍历所有表名，转换成建表语句 写入文件中
//			for (String tableName : tablenames) {
//				// 获取创建表结构bean对象
//				tableBeanList = this.getTableBeans(tableName,con);
//				// 转换建表语句
//				stringBufferTable = this.convertTableStr(tableName, tableBeanList);
//				
//				// 先写入文件table，在写入data
//				this.writeTxtFile(stringBufferTable, outputStreamWriter);
//				
//				// 转换数据语句
//				this.getDataStr(tableName, tableBeanList,outputStreamWriter,con);
//			}
//
//		} catch (Exception e) {
//			ExceptionManage.dispose(e,this.getClass());
//		} finally {
//			if(null!= con){
//			    try {
//					con.close();
//				} catch (SQLException e) {
//					ExceptionManage.dispose(e,this.getClass());
//				}
//			}
//			try {
//				if(outputStreamWriter != null){
//					outputStreamWriter.close();
//				}
//				if(fileOutputStream != null ){
//					fileOutputStream.close();
//				}
//			} catch (IOException e) {
//				ExceptionManage.dispose(e,this.getClass());
//			}
//			outputStreamWriter = null;
//			fileOutputStream = null;
//			tableBeanList = null;
//			stringBufferTable = null;
//			con = null;
//		}
//
//	}

	/**
	 * 写文件头
	 * 
	 * @param tablenames
	 *            所有表的集合
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void writeFileHead(List<String> tablenames, OutputStreamWriter outputStreamWriter) throws Exception {
		StringBuffer stringBuffer = null;
		try {
			stringBuffer = new StringBuffer();
			stringBuffer.append("/*\n");
			stringBuffer.append(ServerConstant.FILE_LABEL + "\n\n");
			stringBuffer.append(ServerConstant.FILE_TYPE + "\n");
			stringBuffer.append(ServerConstant.FILE_VERSION + "\n");
			stringBuffer.append(ServerConstant.FILE_TABLENUMBER + tablenames.size() + "\n");
			stringBuffer.append(ServerConstant.FILE_DATE + DateUtil.getDate(DateUtil.FULLTIME) + "\n");
			stringBuffer.append("*/");

			this.writeTxtFile(stringBuffer, outputStreamWriter);
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 创建文件
	 * 
	 * @param filePath
	 *            文件路径
	 * @throws IOException
	 */
	private void createFile(String filePath) throws IOException {

		this.file = new File(filePath + "\\" + this.getFileName());
		File fileSource = new File(filePath);
		if(!fileSource.exists()){
			fileSource.mkdirs();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				throw e;
			}
		}
	}

	/**
	 * 获取文件名 PTN_日期+.sql
	 * 
	 * @return
	 */
	private String getFileName() {

		return "PTN_" + DateUtil.getDate("yyyyMMddHHmmss") + ".sql";

	}

	/**
	 * 写入文件
	 * 
	 * @param stringBuffer
	 *            要写入的字符串
	 * @throws IOException
	 */
	private void writeTxtFile(StringBuffer stringBuffer, OutputStreamWriter outputStreamWriter) throws IOException {

		try {
			for (int i = 0; i < stringBuffer.length(); i++) {
				char c = stringBuffer.charAt(i);
				outputStreamWriter.append(c);
			}
		} catch (IOException e) {
			throw e;
		}
	}

	/**
	 * 把bean 转换成stringBuffer
	 * 
	 * @param tableBeans
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private StringBuffer convertTableStr(String tableName, List<TableBean> tableBeans) throws Exception {
		StringBuffer stringBuffer = new StringBuffer();
		String prikey = null;
		try {

			stringBuffer.append("\n\n");
			stringBuffer.append("-- -------------------------------------------\n");
			stringBuffer.append("-- Table structure for " + tableName + "\n");
			stringBuffer.append("-- -------------------------------------------\n");

			stringBuffer.append("DROP TABLE IF EXISTS `" + tableName + "`;\n");
			stringBuffer.append("CREATE TABLE `" + tableName + "` (\n");

			// 遍历bean 组织建表语句
			for (TableBean tableBean : tableBeans) {
				stringBuffer.append("  ");
				stringBuffer.append(tableBean.getAttributeName());
				stringBuffer.append(" " + tableBean.getAttributeType());
				if (null != tableBean.getIsNull()) {
					stringBuffer.append(" " + tableBean.getIsNull());
				}
				if (null != tableBean.getExtra() && !"".equals(tableBean.getExtra())) {
					stringBuffer.append(" " + tableBean.getExtra());
				}
				if (null != tableBean.getDefaultValue() && !"".equals(tableBean.getDefaultValue())) {
					if (null == tableBean.getIsNull()) {
						stringBuffer.append(" " + tableBean.getDefaultValue());
					}
				}
				stringBuffer.append(",\n");

				if (tableBean.isPri()) {
					prikey = tableBean.getAttributeName();
				}
			}

			if (null != prikey) {
				stringBuffer.append("  PRIMARY KEY (" + prikey + ")\n");
			}
			stringBuffer.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");

		} catch (Exception e) {
			throw e;
		} finally {
			prikey = null;
		}
		return stringBuffer;
	}

	/**
	 * 根据table名称。 获取table语句
	 * 
	 * @param tableName
	 *            表名
	 * @throws Exception 
	 */
	@SuppressWarnings("unused")
	private List<TableBean> getTableBeans(String tableName,Connection con) throws Exception {

		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<TableBean> tableBeanList = null;
		TableBean tableBean = null;
		String sql = null;
		try {  
			sql = "desc `" + tableName + "`";
			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			tableBeanList = new ArrayList<TableBean>();
			while (resultSet.next()) {
				tableBean = new TableBean();
				tableBean.setAttributeName("`" + resultSet.getString("field") + "`");
				tableBean.setAttributeType(resultSet.getString("type"));
				if (null != resultSet.getString("default")) {
					tableBean.setDefaultValue(" DEFAULT '" + resultSet.getString("default") + "'");
				} else {
					tableBean.setDefaultValue(" DEFAULT NULL");
				}
				// 如果此列不可为空， 设置 NOT NULL
				if ("NO".equals(resultSet.getString("null"))) {
					tableBean.setIsNull("NOT NULL");
				}
				tableBean.setExtra(resultSet.getString("extra"));
				// 如果是主键设置此列为主键
				if (null != resultSet.getString("key") && !"".equals(resultSet.getString("key"))) {
					tableBean.setPri(true);
				}
				tableBeanList.add(tableBean);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}finally{
					resultSet = null;
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}finally{
					preparedStatement = null;
				}
			}
			tableBean = null;
			sql = null;
		}
		return tableBeanList;
	}

	/**
	 * 根据table 把table数据转成sql语句
	 * 
	 * @param tableName
	 *            表名
	 * @param tableBeanList
	 *            列集合 有多少个列就循环多少此
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void getDataStr(String tableName, List<TableBean> tableBeanList,OutputStreamWriter outputStreamWriter,Connection con) throws Exception {
		ResultSet resultSet = null;
		StringBuffer stringBuffer = new StringBuffer();
		PreparedStatement preparedStatement = null;
		String sql = null;
		String label = "";
		try {
			sql = "select * from `" + tableName + "`";
			preparedStatement = con.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();

			stringBuffer.append("\n\n");
			stringBuffer.append("-- -------------------------------------------\n");
			stringBuffer.append("-- Records of " + tableName + "\n");
			stringBuffer.append("-- -------------------------------------------\n");
			
			this.writeTxtFile(stringBuffer, outputStreamWriter);
			while (resultSet.next()) {
				stringBuffer=new StringBuffer();
				stringBuffer.append("INSERT INTO `" + tableName + "` VALUES (");
				// 遍历列集合，取索引
				for (int i = 1; i <= tableBeanList.size(); i++) {
					// 如果是第一次循环。前方不加","
					if (i == 1) {
						label = "";
					} else {
						label = ",";
					}
					// 如果值为null 直接存入NULL 而不是'null'
					if (null != resultSet.getObject(i)) {
						stringBuffer.append(label + "'" + resultSet.getObject(i) + "'");
					} else {
						stringBuffer.append(label + "null");
					}
				}
				stringBuffer.append(");\n");
				this.writeTxtFile(stringBuffer, outputStreamWriter);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}finally{
					resultSet = null;
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}finally{
					preparedStatement = null;
				}
			}
			sql = null;
		}
	}
}
