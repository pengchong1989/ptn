package com.nms.db.fac.f;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.nms.ui.manager.ExceptionManage;

public class DButil {

	private Statement stmt = null;

	/**
	 * @param args
	 */

	public int executeSql(String sql, Connection conn) {
		Lg.lg(sql);
		Lg.lg("in do_nc");
		// Statement stmt = null;
		// PreparedStatement prep = null;
		try {
			// Lg.lg("1+"+System.currentTimeMillis());
			stmt = conn.createStatement();
			// Lg.lg("2+"+System.currentTimeMillis());
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			Lg.lg(this.getClass().getName() + e.getMessage());
			return -1;
		}
		// finally {
		// if (stmt != null) {
		// try {
		// stmt.close();
		// } catch (SQLException e) {
		// ExceptionManage.dispose(e,this.getClass());
		// }
		// }
		// stmt = null;
		// }
		return 0;
	}

	/**
	 * 释放资源
	 */
	public void close() {
		// TXC
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
		stmt = null;
	}
	/**
	 * 判断是否被锁
	 * @return ture 被锁定
	 * @return false 没锁
	 * **/
	public Boolean IsClock(int ClockId, String TablesName, Connection conn) {
		String IdName;
		Boolean re = false;
		if ((IdName = this.getEntityIdname(TablesName)) == null) {
			re= false;
		}
		String sql = "select * from " + TablesName + " where " + IdName + "=" + ClockId + " and LOCK_BY is not null";
		Lg.lg("IsClock sql = " + sql);
		try{
			ResultSet rs = conn.createStatement().executeQuery(sql);
			if (rs.next()) {
				re= true;
			} else {
				re= false;
			}
		}catch(Exception e){
			Lg.lg("IsClock Exception : "+e.getMessage());
		}
		return re;
	}
	/**
	 * 锁定对象表
	 * @throws SQLException 
	 * @return -8 锁定表错误
	 * @return -1 该字段已经被锁定
	 * */
	public int ClockIt(int ClockId, String TablesName, String LockBy, Connection conn) {
		String IdName;
		if ((IdName = this.getEntityIdname(TablesName)) == null) {
			return -8;
		}
		if(IsClock(ClockId,TablesName,conn)){
			return  -1;
		}
		String sql = "update " + TablesName + " set LOCK_TS=getdate(), LOCK_BY = '" + LockBy + "' where " + IdName + "=" + ClockId;
		return this.executeSql(sql, conn);
	}
	/**
	 * 解锁对象表
	 * */
	public int UnClockIt(int ClockId, String TablesName, String LOCK_BY, Connection conn) {
		String IdName;
		if ((IdName = this.getEntityIdname(TablesName)) == null) {
			return -8;
		}
		String sql = "update " + TablesName + " set LOCK_TS=null, LOCK_BY = null where " + IdName + "=" + ClockId;
		return this.executeSql(sql, conn);
	}
	/**
	 * 字符串自动加 引号
	 * */
	public String SB(String Str) {
		if (null == Str || "null".equals(Str)) {
			return Str;
		} else {
			return new StringBuffer().append("'").append(Str).append("'").toString();
		}
	}
	
	/**
	 * 把字符串加单引号
	 * @param str 字符串
	 * @return 加完单引号的字符串
	 * @throws Exception 
	 */
	public static String toSingleMark(String str) throws Exception{
		if(null==str || "".equals(str)){
			throw new Exception("str is null");
		}
		return "'"+str+"'";
	}
	
	/**
	 * Int 返回字符串
	 * */
	public String SB(int Str) {
		return String.valueOf(Str);
	}
	/**
	 * 字符模糊查询 自动加 % 号
	 * */
	public String SL(String s) {
		return new StringBuffer().append("%").append(s).append("%").toString();
	}
	/**
	 * 得到表的最新 ID
	 * */
	public int getNewId(String TablesName, Connection conn) {
		if (getEntityIdname(TablesName) == null) {
			return -1;
		}
		String sqld = "select max(" + getEntityIdname(TablesName) + ") from " + TablesName + "";
		int getnewID = -1;
		try {
			ResultSet rs = conn.createStatement().executeQuery(sqld);
			if (rs.next()) {
				getnewID = rs.getInt(1);
			}
		} catch (SQLException e) {
			Lg.lg(e.getMessage());
		}
		return getnewID;
	}
	/**
	 * 通过表名称 得到表的 唯一 关键字
	 * */
	public String getEntityIdname(String tablesname) {
		if ("site_inst".equals(tablesname))
			return "site_inst_id";
		if ("site_domain_map".equals(tablesname))
			return "site_inst_id";
		if ("equip_inst".equals(tablesname))
			return "equip_inst_id";
		if ("slot_inst".equals(tablesname))
			return "slot_inst_id";
		if ("card_inst".equals(tablesname))
			return "card_inst_id";
		if ("port_inst".equals(tablesname))
			return "port_inst_id";
		if ("circ_inst".equals(tablesname))
			return "circ_inst_id";
		if ("circ_path_inst".equals(tablesname))
			return "circ_path_inst_id";
		if ("circ_path_attr_settings".equals(tablesname))
			return "circ_path_inst_id";
		if ("val_customer".equals(tablesname))
			return "cust_inst_id";
		if ("val_attr_group".equals(tablesname))
			return "group_inst_id";
		if ("val_attr_name".equals(tablesname))
			return "val_attr_inst_id";
		if ("user_inst".equals(tablesname))
			return "user_id";
		if ("domain_inst".equals(tablesname))
			return "domain_inst_id";
		else
			return null;
	}
	/**
	 * 通过UDA自定义表的 ID 查询名字 
	 * @throws SQLException 
	 * */
	public String getUdaName(int val_attr_inst_id, Connection conn){
		String sqld = "select attr_name from VAL_ATTR_NAME where val_attr_inst_id = "+val_attr_inst_id ;
		ResultSet rs = null;
		String attr_name = null;
		Statement stmts = null;
		try {
			stmts = conn.createStatement();
			rs = stmts.executeQuery(sqld);
			if (rs.next()) {
				attr_name = rs.getString(1);
			}
		} catch (SQLException e) {
			Lg.lg(e.getMessage());
		}finally{
			try{
			if(rs != null )rs.close();
			if(stmts != null)stmts.close();
			}catch(Exception e){
				Lg.lg("DButil.getUdaName Exception:"+e.getMessage());
			}
		}
		return attr_name;
	}
	public static void main(String[] args) {
	}

}
