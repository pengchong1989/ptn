package com.nms.drivechenxiao.service.bean.almcfg;

import com.nms.drive.service.impl.CoderUtils;

/**level 告警级别
	 * isAlarm 是否上报告警,0 不上报,1 上报
	 * delaytime 上报延迟时间
	 * cleardelaytime 消失延迟时间**/
public class AlmObject {
	private String name; //告警项名称,对应Almcfg内各个字段
	private String level ;//告警级别
	private String isAlarm ;//是否上报告警,0 不上报,1 上报
	private String delaytime ;//上报延迟时间
	private String cleardelaytime ;//消失延迟时间**/
	private String NumberValue ;//告警设置数字指标
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumberValue() {
		return NumberValue;
	}
	public void setNumberValue(String numberValue) {
		NumberValue = numberValue;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getIsAlarm() {
		return isAlarm;
	}
	public void setIsAlarm(String isAlarm) {
		this.isAlarm = isAlarm;
	}
	public String getDelaytime() {
		return delaytime;
	}
	public void setDelaytime(String delaytime) {
		this.delaytime = delaytime;
	}
	public String getCleardelaytime() {
		return cleardelaytime;
	}
	public void setCleardelaytime(String cleardelaytime) {
		this.cleardelaytime = cleardelaytime;
	}
	/**输入参数: level 告警级别
	 * isAlarm 是否上报告警,0 不上报,1 上报
	 * delaytime 上报延迟时间
	 * cleardelaytime 消失延迟时间
	 * **/
	//设置告警指标转变内部数值
	public void setNumberValue(String level,String isAlarm, String delaytime, String  cleardelaytime){
		StringBuffer nb = new StringBuffer();
		nb.append(CoderUtils.intTo4Binary(Integer.valueOf(cleardelaytime)));
		nb.append(CoderUtils.intTo5Binary(Integer.valueOf(delaytime)));
		nb.append(isAlarm);
		nb.append(CoderUtils.intTo3Binary(Integer.valueOf(level)));
		this.NumberValue = CoderUtils.convertAlgorism(nb.toString().toCharArray())+"" ;
	}//得到告警指标从内部数值
	public AlmObject getNumberValueAlm(){
		String nb = CoderUtils.intTo13Binary(Integer.valueOf(this.NumberValue));
		AlmObject alm = new AlmObject();
		alm.setCleardelaytime(CoderUtils.convertAlgorism(nb.substring(0, 4).toString().toCharArray())+"");
		alm.setDelaytime(CoderUtils.convertAlgorism(nb.substring(5, 9).toString().toCharArray())+"");
		alm.setIsAlarm(CoderUtils.convertAlgorism(nb.substring(10, 10).toString().toCharArray())+"");
		alm.setLevel(CoderUtils.convertAlgorism(nb.substring(11, 13).toString().toCharArray())+"");
		return alm;
	}
	
}
