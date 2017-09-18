package com.nms.rmi.ui.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 恢复文件时 验证文件
 * 
 * @author kk
 * 
 */
public class RecoverFileChecking {

	// 各个需要验证的行号
	private final int LINE_LABEL = 2; // 第二行 代表label
	private final int LINE_TYPE_3 = 3; // 第3行 代表type
	private final int LINE_TYPE_4 = 4; // 第4行 代表type(2.0.7之前的版本)
	private final int LINE_TYPE_7 = 7; // 第7行 代表type(兼容工具导出的sql语句) 
	private final int LINE_VERSION = 5; // 第五行 代表版本
	private final int LINE_TABLENUMBER = 6; // 第六行 代表table数量
	private List<String> tableNames = null; // 所有表名
	private final String TABLENAME_LABEL = "CREATE"; // 识别此行有表名的标识
	private int isPTN = 2;//1/2 正确的sql/不正确的sql

	/**
	 * 验证文件是否与选择的恢复类型相匹配
	 * 
	 * @return true 匹配 false 不匹配
	 * @throws Exception
	 */
	public boolean checkingFile(List<String> tableNames, String filePath) throws Exception {
		BufferedReader reader = null;
		boolean result = true;
		try {
			this.tableNames = tableNames;

			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
			String tempString = null;
			int lineNum = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 验证文件信息 版本、标识、table数量等
				if(lineNum < 20){
					if (!this.checkingFileInfo(tempString, lineNum)) {
						result = false;
						break;
					}
				}
				if (!this.checkingTableName(tempString)) {
					result = false;
					break;
				}
				lineNum++;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			reader.close();
			reader = null;
		}
		return result;
	}

	/**
	 * 根据行号验证文件信息
	 * 
	 * @param str
	 *            此行的文本
	 * @param lineNum
	 *            此行的行号
	 * @return true=通过验证 false=没有通过
	 * @throws Exception
	 */
	private boolean checkingFileInfo(String str, int lineNum) {
		boolean result = true;
		try {
//			if (lineNum == this.LINE_LABEL) {// 如果行号为label， 比较字符串是否与 ServerConstant.FILE_LABEL相同
//				if (!ServerConstant.FILE_LABEL.equals(str)) {
//					result = false;
//				}
//			} else if (lineNum == this.LINE_TYPE) { // 如果行号为type， 比较字符串是否与 ServerConstant.FILE_TYPE相同
//				if (!ServerConstant.FILE_TYPE.equals(str)) {
//					result = false;
//				}
//			} else if (lineNum == this.LINE_VERSION) { // 如果行号为VERSION， 比较字符串是否与 ServerConstant.FILE_VERSION相同
//				if (!ServerConstant.FILE_VERSION.equals(str)) {
//					result = false;
//				}
//			} else 
//				if (lineNum == this.LINE_TABLENUMBER) { // 如果行号为TABLENUMBER，比较数字是否与tableNames.size()是否相等
//				// 如果数字前的字符串不相等。直接返回false
//				if (!ServerConstant.FILE_TABLENUMBER.equals(str.substring(0, ServerConstant.FILE_TABLENUMBER.length()))) {
//					result = false;
//				} else {
//					// 如果相等，比较table的数量
//					// 获取文件上的数量，既 冒号后面的所有数字
//					int num = Integer.parseInt(str.substring(str.indexOf(":") + 1, str.length()).trim());
//					if (num != this.tableNames.size()) {
//						result = false;
//					}
//				}
//			}
			if(lineNum < 10){
				if(str.contains("ptn") || str.contains("PTN")){
					isPTN = 1;
				}
			}else{
				if(isPTN == 2){
					return false;
				}
			}
//			if(lineNum == this.LINE_TYPE_3){
//				if(!str.contains("ptn") && !str.contains("PTN")){
//					SOURCESQL = 2;
//				}
//			}
//			if(lineNum == this.LINE_TYPE_4){
//				if(!str.contains("ptn") && !str.contains("PTN") && this.SOURCESQL == 2){
//					SOURCESQL = 2;
//				}else{
//					SOURCESQL = 1;
//				}
//			}
//			if (lineNum == this.LINE_TYPE_7 && SOURCESQL == 2) { // 如果行号为type， 比较字符串是否与 ServerConstant.FILE_TYPE相同
//				if(!str.contains("ptn") && !str.contains("PTN")){
//					result = false;
//				}
//			}
		} catch (Exception e) {
			result = false;
		}
		return result;
	}

	/**
	 * 根据str验证此行是否有create字样。 如果有。 取表名 验证表名是否在tableNames中
	 * 
	 * @param str
	 * @return ture=通过 false=没通过
	 * @throws Exception
	 */
	private boolean checkingTableName(String str) throws Exception {
		String tableName = null;
		boolean flag = true;
		try {
			// 此行字符数大于"CREATE"字符数，才做验证
			if (str.length() > this.TABLENAME_LABEL.length()) {
				// 如果str的前6个字符为"CREATE"
				if (this.TABLENAME_LABEL.equals(str.substring(0, this.TABLENAME_LABEL.length()))) {

					// 取``之间的字符串为表名
					tableName = str.substring(str.indexOf("`") + 1, str.lastIndexOf("`"));
					if (!this.tableNames.contains(tableName)) {
						flag = false;
					}
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			tableName = null;
		}
		return flag;
	}

	/**
	 * 判断是否是服务器导出的数据
	 * true/false 是服务器/是工具
	 */
	public boolean isSourceSql(List<String> tableNames, String filePath) throws Exception {
		BufferedReader reader = null;
		boolean result = true;
		try {
			this.tableNames = tableNames;

			reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
			String tempString = null;
			int lineNum = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				if(lineNum == this.LINE_TYPE_7){
					if(tempString.contains("ptn") || tempString.contains("PTN")){
						return false;
					}else{
						return true;
					}
				}
				lineNum++;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			reader.close();
			reader = null;
		}
		return result;
	}
}
