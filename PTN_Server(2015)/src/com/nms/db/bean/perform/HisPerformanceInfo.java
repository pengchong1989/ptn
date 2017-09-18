package com.nms.db.bean.perform;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.nms.db.enums.EMonitorCycle;
import com.nms.db.enums.ERunStates;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;


public class HisPerformanceInfo extends PerformanceInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 长期性能任务id
	private int taskId;
	private Capability capability;
	
	// 监控周期：15分钟、24小时
	private EMonitorCycle monitorCycle;
	// 运行状态：运行、挂起、结束、未开始
	private ERunStates runStates;
	// 是否有效
	private int useAble;
	// 结束时间
	private String endTime;
	/**
	 * objectType
	 * 只作为转储时，应用
	 */
	private int unObjectType;
	
	private int monitor;//粒度周期     ；    1 ： 15min,         2: 24h
	

	public int getMonitor() {
		return monitor;
	}

	public void setMonitor(int monitor) {
		this.monitor = monitor;
	}

	public HisPerformanceInfo() {
	}

	public int getUnObjectType() {
		return unObjectType;
	}

	public void setUnObjectType(int unObjectType) {
		this.unObjectType = unObjectType;
	}

	public Capability getCapability() {
		return capability;
	}

	public void setCapability(Capability capability) {
		this.capability = capability;
	}

	public EMonitorCycle getMonitorCycle() {
		return monitorCycle;
	}

	public void setMonitorCycle(EMonitorCycle monitorCycle) {
		this.monitorCycle = monitorCycle;
	}

	public ERunStates getRunStates() {
		return runStates;
	}

	public void setRunStates(ERunStates runStates) {
		this.runStates = runStates;
	}

	public int getUseAble() {
		return useAble;
	}

	public void setUseAble(int useAble) {
		this.useAble = useAble;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		String unitName="";
		try {
			super.getClientProperties().put("id", this.getId());
			// 网元名称
			super.putClientProperty("siteName", getSiteName());
			// 性能对象
			super.getClientProperties().put("objectName", this.getObjectName()+"/"+this.getObjectType().toString());
			// 对象类型
//			super.getClientProperties().put("objectType", this.getObjectType().toString());
			if (this.getCapability() != null) {
				// 性能类型
				super.getClientProperties().put("perforType", this.getCapability().getCapabilitytype());
				// 描述
				if(ResourceUtil.language.equals("zh_CN")){
					this.getClientProperties().put("perforDesc", this.getCapability().getCapabilitydesc());
				}else{
					this.getClientProperties().put("perforDesc", this.getCapability().getCapabilitydesc_en());
				}
				unitName=this.getCapability().getUnitName();
				//性能参数
				super.getClientProperties().put("perforName", this.getCapability().getCapabilityname());
			}
			if( unitName != null && unitName.trim().toString().equals("%")){
				if(this.getPerformanceCode() == 159 || this.getPerformanceCode() == 158){
					BigDecimal   b  =   new BigDecimal(getPerformanceValue());  
					float   f1   =  b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
					this.getClientProperties().put("perforValue", String.valueOf(f1)+unitName);
				}else if(this.getPerformanceCode() == 116){
					this.getClientProperties().put("perforValue", getPerformanceValue()+unitName);
				}
				else{
					this.getClientProperties().put("perforValue", getPerformanceValue()/100+unitName);
				}
			}else{
				if(this.getPerformanceCode() == 80 
				   ||this.getPerformanceCode() == 81
				   ||this.getPerformanceCode() == 82 
				   ||this.getPerformanceCode() == 83
				   ||this.getPerformanceCode() == 84
				   ||this.getPerformanceCode() == 85
				   ||this.getPerformanceCode() == 89
				   ||this.getPerformanceCode() == 153)
				{
//					  float  b   =  (float)(Math.round(getPerformanceValue*1000))/1000;(这里的100就是2位小数点,如果要其它位,如4位,这里两个100改成10000)
//					  float   f   =  34.232323;  
					// float   f1   =  b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();  
					//   b.setScale(2,  BigDecimal.ROUND_HALF_UP) 表明四舍五入，保留两位小数
				BigDecimal   b  =   new BigDecimal(getPerformanceValue());  
				float   f1   =  b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
				this.getClientProperties().put("perforValue", String.valueOf(f1)+unitName);
				
				}else if (this.getPerformanceCode() == 98){
					this.getClientProperties().put("perforValue", (UiUtil.getCodeByValue("SFPModel", (int)getPerformanceValue()+"")).getCodeName()+unitName);
				}else if (this.getPerformanceCode() == 99){
					this.getClientProperties().put("perforValue", (UiUtil.getCodeByValue("SFPConnectorType", (int)getPerformanceValue()+"")).getCodeName()+unitName);
				}else if (this.getPerformanceCode() == 100){
					this.getClientProperties().put("perforValue", (UiUtil.getCodeByValue("SFPTransMedia", (int)getPerformanceValue()+"")).getCodeName()+unitName);
				}else if (this.getPerformanceCode() == 144 || this.getPerformanceCode() == 145){
					if((int)getPerformanceValue() == 1){
						this.getClientProperties().put("perforValue", "<5"+unitName);
					}else{
						this.getClientProperties().put("perforValue", (int)getPerformanceValue()+unitName);
					}
				}else{
				this.getClientProperties().put("perforValue", getPerformanceValue()+unitName);
				}
			}			
//			//性能参数
//			super.getClientProperties().put("perforName", getPerformanceName());
			
			// 监控周期
//			if (this.getMonitorCycle() != null) {
//				super.getClientProperties().put("perforCycle", this.getMonitorCycle().toString());
//			}
			// 运行状态 
			if (this.getRunStates() != null) {
				super.getClientProperties().put("perforRunStates", this.getRunStates().toString());
			}  
			
//			super.getClientProperties().put("perforCycleStart",this.getStartTime());
			// 开始时间
			if (this.getStartTime()!=null && !"".equals(this.getStartTime())) {
					super.getClientProperties().put("perforCycleStart",updateTime(this.getStartTime()));
				}
			// 结束时间
			if (this.getPerformanceEndTime()!=null && !"".equals(this.getPerformanceEndTime())) {
					super.getClientProperties().put("endTime",updateTime(this.getPerformanceEndTime()));
				}
			
			
			// 是否有效
			if (getUseAble() == 1) {
				super.getClientProperties().put("useable", Boolean.FALSE);
			} else {
				super.getClientProperties().put("useable", Boolean.TRUE);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 修改时间的格式：yyyy-MM-dd HH:mm:ss
	 * @param updateTime
	 * @return
	 */
	private String updateTime(String updateTime){
		String newTime=null;
		SimpleDateFormat sdf=null;
		try {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		     newTime= sdf.format(sdf.parse(updateTime));
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return  newTime;
	}
}
