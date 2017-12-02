package com.nms.db.bean.system;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysBtn;

/**
 * 操作日志  
 * @author Administrator
 *
 */
public class SystemLog extends ViewDataObj {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String startTime;
	private String operationType;//操作类型
	private int operationResult;//操作结果	
	private String operationObjName;
	
	public String getOperationObjName() {
		return operationObjName;
	}
	public void setOperationObjName(String operationObjName) {
		this.operationObjName = operationObjName;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public int getOperationResult() {
		return operationResult;
	}
	public void setOperationResult(int operationResult) {
		this.operationResult = operationResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
			getClientProperties().put("id", getId());
			getClientProperties().put("startTime", this.getStartTime());
			getClientProperties().put("operationType", this.getOperationType());
			String resultName="";
			if(1==this.getOperationResult()){
				resultName=ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_ISUCCESS);
				
			}else if(0==this.getOperationResult()){
				resultName=ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT_FALSE);
			}
			getClientProperties().put("operationResult",resultName);
	}
	
}
