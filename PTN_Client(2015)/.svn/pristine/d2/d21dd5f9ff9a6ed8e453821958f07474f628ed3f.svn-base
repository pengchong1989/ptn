package com.nms.db.bean.ptn;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.system.DataBaseInfo;

/**
 * <p>文件名称:DbInfoTask.java</p>
 * <p>文件描述: DbInfoTask数据类</p>
 * <p>版权所有: 版权所有(C)2013-2015</p>
 * <p>公    司: 北京建博信通软件技术有限公司</p>
 * <p>内容摘要: DbInfoTask信息数据</p>
 * <p>其他说明: </p>
 * <p>完成日期: 2015年2月9日</p>
 * <p>修改记录1:</p>
 * <pre>
 *    修改日期：
 *    版 本 号：
 *    修 改 人：
 *    修改内容：
 * </pre>
 * <p>修改记录2：</p>
 * @version 1.0
 * @author zhangkun
 */
public class DbInfoTask {
	
	 /**数据库Id*/
	private int id = 0;
	 /** 监控类型 数据库空间*/
	private boolean mointorTypeDb = false;
	private String mointorTypeDb_bool;//查询时做转换
	/** 监控类型 数据库总空间*/
	private boolean isMointorTotal = false;
	private String isMointorTotal_bool;//查询时做转换
	 /** 监控类型 ：db CPU,内存*/
	private String mointorType = "";
	 /**监控周期 */
	private int miintorCycle = 0;
	 /**数据库总空间使用告警门限 */
	private int totalDbSpace = 0;
	 /**数据库中每张表的空间使用情况 */
	private List<DataBaseInfo> daTableList = new ArrayList<DataBaseInfo>();
	
	//**********代码段:构造方法************************************************************/
	
	public DbInfoTask()
	{
		
	}

	//**********代码段:公共方法************************************************************/
	
	public int getMiintorCycle() 
	{
		return miintorCycle;
	}

	public String getMointorTypeDb_bool() {
		return mointorTypeDb_bool;
	}

	public void setMointorTypeDb_bool(String mointorTypeDbBool) {
		mointorTypeDb_bool = mointorTypeDbBool;
	}

	public String getIsMointorTotal_bool() {
		return isMointorTotal_bool;
	}

	public void setIsMointorTotal_bool(String isMointorTotalBool) {
		isMointorTotal_bool = isMointorTotalBool;
	}

	public boolean isMointorTypeDb()
	{
		return mointorTypeDb;
	}

	public void setMointorTypeDb(boolean mointorTypeDb) 
	{
		this.mointorTypeDb = mointorTypeDb;
	}

	public boolean isMointorTotal() 
	{
		return isMointorTotal;
	}

	public void setMointorTotal(boolean isMointorTotal) 
	{
		this.isMointorTotal = isMointorTotal;
	}

	public void setMiintorCycle(int miintorCycle) 
	{
		this.miintorCycle = miintorCycle;
	}

	public int getTotalDbSpace() 
	{
		return totalDbSpace;
	}

	public void setTotalDbSpace(int totalDbSpace) 
	{
		this.totalDbSpace = totalDbSpace;
	}

	public List<DataBaseInfo> getDaTableList() {
		return daTableList;
	}

	public void setDaTableList(List<DataBaseInfo> daTableList) {
		this.daTableList = daTableList;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getMointorType() 
	{
		return mointorType;
	}

	public void setMointorType(String mointorType) 
	{
		this.mointorType = mointorType;
	}

}
