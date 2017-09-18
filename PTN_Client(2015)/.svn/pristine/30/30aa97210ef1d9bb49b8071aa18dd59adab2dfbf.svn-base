package com.nms.ui.frame;

import java.io.Serializable;

import twaver.Node;

import com.nms.db.enums.EJobStatus;
import com.nms.ui.manager.ExceptionManage;

public abstract class ViewDataObj extends Node implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9006504697347622754L;
	private boolean dataDownLoad; //是否是数据下载操作
	public ViewDataObj() {
	}

	public void putClientProperty() {
		putObjectProperty();
	}

	public abstract void putObjectProperty();

	/**
	 * 根据数据库的工作状态代码转义成字符串
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	public String getJobStatus(String jobStatus) {

		if (null == jobStatus || "".equals(jobStatus)) {
			return "";
		}

		String[] jobStatusArray = null;
		String result="";
		try {
			jobStatusArray=jobStatus.split(",");
			
			for(String str : jobStatusArray){
				result+=EJobStatus.forms(Integer.parseInt(str))+",";
			}
			if(null!=result && !"".equals(result)){
				result=result.substring(0,result.length()-1);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		return result;
	}

	public boolean isDataDownLoad() {
		return dataDownLoad;
	}

	public void setDataDownLoad(boolean dataDownLoad) {
		this.dataDownLoad = dataDownLoad;
	}
}
