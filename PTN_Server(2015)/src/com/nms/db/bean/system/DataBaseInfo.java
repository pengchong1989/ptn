package com.nms.db.bean.system;
/**
 * <p>文件名称:DataBaseInfo.java</p>
 * <p>文件描述: DataBaseInfo数据类</p>
 * <p>版权所有: 版权所有(C)2013-2015</p>
 * <p>公    司: 北京建博信通软件技术有限公司</p>
 * <p>内容摘要: 记录数据库的对象</p>
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
public class DataBaseInfo {
	 /** 对应的数据库ID */
	private int id = 0;
	 /** 名称 */
	private String name = "";
	 /** 总大小 */
	private double countSize = 0.0;
	 /** 数据空间 */
	private double dataSize = 0.0;
	 /** 监控类型 */
	private double freeSize = 0.0;
	 /** 索引空间*/
    private double indexSize = 0.0;
    /** 产品名称 */
    private String productName = "";
    /** 数据库版本号 */
    private String ProductVersion = "";
    /** 监控类型 */
	private int mointorType = 0;
	/** 紧急阀值 */
	private String criticalRate = "";
	/** 主要阀值 */
	private String majorRate = "";
	/** 次要阀值 */
	private String minorRate = "";
	/** 提示阀值 */
	private String warningRate = "";
	/**监控对象*/
	private int mointorObject = 0;
	/**监控对象父节点*/
	private int pathId = 0;
	/**监控的级别:数据库=1/磁盘=2/CPU内存 ==3*/
	private int mointorLevel = 0;
	
	private long countMemory = 0l;
	
	private long useMemory = 0l;
	
	//**********代码段:构造方法************************************************************/
    public DataBaseInfo()
    {
    	
    }
    
  //**********代码段:公共方法************************************************************/
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public double getCountSize() 
	{
		return countSize;
	}
	public void setCountSize(double countSize) 
	{
		this.countSize = countSize;
	}
	public double getFreeSize() 
	{
		return freeSize;
	}
	public void setFreeSize(double freeSize) 
	{
		this.freeSize = freeSize;
	}
	public double getDataSize() 
	{
		return dataSize;
	}
	public void setDataSize(double dataSize) 
	{
		this.dataSize = dataSize;
	}
	public double getIndexSize() 
	{
		return indexSize;
	}
	public void setIndexSize(double indexSize) 
	{
		this.indexSize = indexSize;
	}
	public String getProductName()
	{
		return productName;
	}
	public void setProductName(String productName) 
	{
		this.productName = productName;
	}
	public String getProductVersion() 
	{
		return ProductVersion;
	}
	public void setProductVersion(String productVersion) 
	{
		ProductVersion = productVersion;
	}

	public int getMointorType() {
		return mointorType;
	}

	public void setMointorType(int mointorType) {
		this.mointorType = mointorType;
	}

	public String getCriticalRate() {
		return criticalRate;
	}

	public void setCriticalRate(String criticalRate) {
		this.criticalRate = criticalRate;
	}

	public String getMajorRate() {
		return majorRate;
	}

	public void setMajorRate(String majorRate) {
		this.majorRate = majorRate;
	}

	public String getMinorRate() {
		return minorRate;
	}

	public void setMinorRate(String minorRate) {
		this.minorRate = minorRate;
	}

	public String getWarningRate() {
		return warningRate;
	}

	public void setWarningRate(String warningRate) {
		this.warningRate = warningRate;
	}

	public int getMointorObject() 
	{
		return mointorObject;
	}

	public void setMointorObject(int mointorObject) 
	{
		this.mointorObject = mointorObject;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public int getPathId() 
	{
		return pathId;
	}

	public void setPathId(int pathId) 
	{
		this.pathId = pathId;
	}

	public int getMointorLevel()
	{
		return mointorLevel;
	}

	public void setMointorLevel(int mointorLevel) 
	{
		this.mointorLevel = mointorLevel;
	}

	public long getCountMemory() {
		return countMemory;
	}

	public void setCountMemory(long countMemory) {
		this.countMemory = countMemory;
	}

	public long getUseMemory() {
		return useMemory;
	}

	public void setUseMemory(long useMemory) {
		this.useMemory = useMemory;
	}
	
}
