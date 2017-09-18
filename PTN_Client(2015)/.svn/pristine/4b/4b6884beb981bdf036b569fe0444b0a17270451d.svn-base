package com.nms.db.bean.alarm;

import com.nms.ui.frame.ViewDataObj;
/**OAM 事件 bean
 * **/
public class OamEvent extends ViewDataObj{
	private String id;
	private String envPort ; //端口
	private String env; //事件
	private String envState; //事件状态
	private String isthis; //是否本端
	private String datetime; //时间戳
	private String envCrc; //事件周期
	private String envLimit; //事件门限
	private String times; // 次数
	private String errSum; //错误总数
	private String envSum; //事件总数
	private String update;//上报时间
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEnvPort() {
		return envPort;
	}

	public void setEnvPort(String envPort) {
		this.envPort = envPort;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public String getEnvState() {
		return envState;
	}

	public void setEnvState(String envState) {
		this.envState = envState;
	}

	public String getIsthis() {
		return isthis;
	}

	public void setIsthis(String isthis) {
		this.isthis = isthis;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getEnvCrc() {
		return envCrc;
	}

	public void setEnvCrc(String envCrc) {
		this.envCrc = envCrc;
	}

	public String getEnvLimit() {
		return envLimit;
	}

	public void setEnvLimit(String envLimit) {
		this.envLimit = envLimit;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public String getErrSum() {
		return errSum;
	}

	public void setErrSum(String errSum) {
		this.errSum = errSum;
	}

	public String getEnvSum() {
		return envSum;
	}

	public void setEnvSum(String envSum) {
		this.envSum = envSum;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void putObjectProperty() {
		// TODO Auto-generated method stub
		getClientProperties().put("id", getId());
		getClientProperties().put("oamPort",getEnvPort() );
		getClientProperties().put("oamEvent", getEnv());
		getClientProperties().put("oamEventType", getEnvState());
		getClientProperties().put("isThis", getIsthis());
		getClientProperties().put("oamDate", getDatetime());
		getClientProperties().put("envCyc", getEnvCrc());
		getClientProperties().put("envLimit", getEnvLimit());
		getClientProperties().put("envTimes", getTimes());
		getClientProperties().put("errCon", getErrSum());
		getClientProperties().put("envCon", getEnvSum());
		getClientProperties().put("upDate", getUpdate());
		
	}

}
