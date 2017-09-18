package com.nms.db.bean.perform;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;

public class CurrentPerforInfo extends PerformanceInfo {
	private static final long serialVersionUID = 2102232785353379272L;
	private Capability capability;
	// 监控周期：15分钟、24小时
	private int monitorCycle;
	// 运行状态：运行、挂起、结束、未开始
	private int runStates;

	public Capability getCapability() {
		return capability;
	}

	public void setCapability(Capability capability) {
		this.capability = capability;
	}

	public int getMonitorCycle() {
		return monitorCycle;
	}

	public void setMonitorCycle(int monitorCycle) {
		this.monitorCycle = monitorCycle;
	}

	public int getRunStates() {
		return runStates;
	}

	public void setRunStates(int runStates) {
		this.runStates = runStates;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		String UnitName="";
		try {
			this.getClientProperties().put("id", this.getId());
			// 网元名称
			this.putClientProperty("siteName", getSiteName());
			// 性能对象
			this.getClientProperties().put("objectName", this.getObjectName()+"/"+this.getObjectType().toString());
//			// 对象类型
//			this.getClientProperties().put("objectType", this.getObjectType().toString());
			if (this.getCapability() != null) {
				// 性能名称                                     
				this.getClientProperties().put("perforName", this.getCapability().getCapabilityname());
				// 性能描述
				if(ResourceUtil.language.equals("zh_CN")){
					this.getClientProperties().put("perforDesc", this.getCapability().getCapabilitydesc());
				}else{
					this.getClientProperties().put("perforDesc", this.getCapability().getCapabilitydesc_en());
				}
				
				UnitName=this.getCapability().getUnitName();
			}
			if( UnitName != null && UnitName.trim().toString().equals("%")){
				if(this.getPerformanceCode() == 159 || this.getPerformanceCode() == 158){
					BigDecimal   b  =   new BigDecimal(getPerformanceValue());  
					float   f1   =  b.setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
					this.getClientProperties().put("perforValue", String.valueOf(f1)+UnitName);
				}else if(this.getPerformanceCode() == 116){
					this.getClientProperties().put("perforValue", getPerformanceValue()+UnitName);
				}
				else{
					this.getClientProperties().put("perforValue", getPerformanceValue()/100+UnitName);
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
				this.getClientProperties().put("perforValue", String.valueOf(f1)+UnitName);
				
				}else if (this.getPerformanceCode() == 98){
					this.getClientProperties().put("perforValue", (UiUtil.getCodeByValue("SFPModel", (int)getPerformanceValue()+"")).getCodeName()+UnitName);
				}else if (this.getPerformanceCode() == 99){
					this.getClientProperties().put("perforValue", (UiUtil.getCodeByValue("SFPConnectorType", (int)getPerformanceValue()+"")).getCodeName()+UnitName);
				}else if (this.getPerformanceCode() == 100){
					this.getClientProperties().put("perforValue", (UiUtil.getCodeByValue("SFPTransMedia", (int)getPerformanceValue()+"")).getCodeName()+UnitName);
				}else if (this.getPerformanceCode() == 144 || this.getPerformanceCode() == 145){
					if((int)getPerformanceValue() == 1){
						this.getClientProperties().put("perforValue", "<5"+UnitName);
					}else{
						this.getClientProperties().put("perforValue", (int)getPerformanceValue()+UnitName);
					}
				}else{
				this.getClientProperties().put("perforValue", getPerformanceValue()+UnitName);
				}
			}
			//开始时间
			this.getClientProperties().put("perforCycleStart", (updateTime(this.getStartTime())));
			//结束时间
			this.getClientProperties().put("perforCycleEnd", (updateTime(this.getPerformanceEndTime())));
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
		if(updateTime==null||updateTime.equals("")){
			return "";
		}
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
