package com.nms.db.bean.system;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ExceptionManage;
/**
 * Bean 类
 * 与本地unload.xml 对应
 * @author sy
 *
 */
public class UnLoading extends ViewDataObj {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int unloadType;//转储数据类型
	private int cellType;//激活状态
	private int unloadLimit;//存储时限
	private int spillEntry;//导出条目
	private int holdEntry;//保留条目
	private int unloadMod;//转储方式(0为sql文件,1为exl文件)
	private String fileWay;//文件路径
	private int id;
	private int isAuto;//是否自动转储 0:不设置；1:自动转储
	private String autoStartTime;//转储开始时间
	private int timeInterval;//时间间隔
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUnloadType() {
		return unloadType;
	}

	public void setUnloadType(int unloadType) {
		this.unloadType = unloadType;
	}

	public int getCellType() {
		return cellType;
	}

	public void setCellType(int cellType) {
		this.cellType = cellType;
	}

	public int getUnloadLimit() {
		return unloadLimit;
	}

	public void setUnloadLimit(int unloadLimit) {
		this.unloadLimit = unloadLimit;
	}

	public int getSpillEntry() {
		return spillEntry;
	}

	public void setSpillEntry(int spillEntry) {
		this.spillEntry = spillEntry;
	}

	public int getHoldEntry() {
		return holdEntry;
	}

	public void setHoldEntry(int holdEntry) {
		this.holdEntry = holdEntry;
	}

	public int getUnloadMod() {
		return unloadMod;
	}

	public void setUnloadMod(int unloadMod) {
		this.unloadMod = unloadMod;
	}

	public String getFileWay() {
		return fileWay;
	}

	public void setFileWay(String fileWay) {
		this.fileWay = fileWay;
	}

	public UnLoading() {
		super();
	}

	public int getIsAuto() {
		return isAuto;
	}

	public void setIsAuto(int isAuto) {
		this.isAuto = isAuto;
	}

	public String getAutoStartTime() {
		return autoStartTime;
	}

	public void setAutoStartTime(String autoStartTime) {
		this.autoStartTime = autoStartTime;
	}
	
	public int getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(int timeInterval) {
		this.timeInterval = timeInterval;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			
			getClientProperties().put("id",this.getID());
			//getClientProperties().put("unloadType",this.getUnloadType());
			getClientProperties().put("unloadLimit",this.getUnloadLimit());
			getClientProperties().put("spillEntry",this.getSpillEntry());
			getClientProperties().put("holdEntry",this.getHoldEntry());
			getClientProperties().put("fileWay",this.getFileWay());
			if(this.getUnloadType()==1){
				getClientProperties().put("unloadType",UnLoadFactory.trans(1));
			}else if(this.getUnloadType()==2){
				getClientProperties().put("unloadType",UnLoadFactory.trans(2));
			}else if(this.getUnloadType()==4){
				getClientProperties().put("unloadType",UnLoadFactory.trans(4));
			}else if(this.getUnloadType()==3){
				getClientProperties().put("unloadType",UnLoadFactory.trans(3));
			}
			if(this.getCellType()==0){
				getClientProperties().put("cellType",true);
			}else {
				getClientProperties().put("cellType",false);
			}
			if(this.getUnloadMod()==0){
				getClientProperties().put("unloadMod","sql文件");
			}else {
				getClientProperties().put("unloadMod","Exl文件");
			}
			//自动转储配置
			getClientProperties().put("isAuto",this.getIsAuto());
			getClientProperties().put("autoTime", this.getAutoStartTime());
			getClientProperties().put("timeInterval", this.getTimeInterval());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
