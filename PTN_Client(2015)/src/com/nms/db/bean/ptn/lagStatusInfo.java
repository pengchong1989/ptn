package com.nms.db.bean.ptn;

import java.util.ArrayList;
import java.util.List;

import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class lagStatusInfo extends ViewDataObj{
	private static final long serialVersionUID = -6316594807688237364L;
	/**
	 * 
	 */
	private int lacpSwitch;  //LACP协议开关
	private int syspri;     //系统优先级
	private int lagId;     //LAG ID
	private int workMode;   //聚合组工作模式
	private int lagMode;    //聚合模式
	private int lagNum;     //聚合组成员数
	private int returnType;  //非负载分担返回类型
	private int waitTime;   //等待恢复时间
	private List<LagPortStateInfo> lagPorts= new ArrayList<LagPortStateInfo>();
	
	
	public List<LagPortStateInfo> getLagPorts() {
		return lagPorts;
	}
	public void setLagPorts(List<LagPortStateInfo> lagPorts) {
		this.lagPorts = lagPorts;
	}
	public int getLacpSwitch() {
		return lacpSwitch;
	}
	public void setLacpSwitch(int lacpSwitch) {
		this.lacpSwitch = lacpSwitch;
	}
	public int getSyspri() {
		return syspri;
	}
	public void setSyspri(int syspri) {
		this.syspri = syspri;
	}
	public int getLagId() {
		return lagId;
	}
	public void setLagId(int lagId) {
		this.lagId = lagId;
	}
	public int getWorkMode() {
		return workMode;
	}
	public void setWorkMode(int workMode) {
		this.workMode = workMode;
	}
	public int getLagMode() {
		return lagMode;
	}
	public void setLagMode(int lagMode) {
		this.lagMode = lagMode;
	}
	public int getLagNum() {
		return lagNum;
	}
	public void setLagNum(int lagNum) {
		this.lagNum = lagNum;
	}
	public int getReturnType() {
		return returnType;
	}
	public void setReturnType(int returnType) {
		this.returnType = returnType;
	}
	public int getWaitTime() {
		return waitTime;
	}
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	private String getClientLagMode()
	{
		String result="";
		int mode = this.getLagMode();
		if(mode==0)
		{
			result=ResourceUtil.srcStr(StringKeysTip.TIP_LAGMODE0);
		}
		else if(mode==1)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_LAGMODE1);
		}
		else if(mode==2)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_LAGMODE2);
		}
		else if(mode==3)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_LAGMODE3);
		}
		else if(mode==4)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_LAGMODE4);
		}
		else if(mode==5)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_LAGMODE5);
		}
		else if(mode==6)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_LAGMODE6);
		}
		else if(mode==7)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_LAGMODE7);
		}
		return result;
	}
	
	private String getClientLagMode(int mode)
	{
		String result="";
		if(mode==0)
		{
			result=ResourceUtil.srcStr(StringKeysTip.TIP_LAGMODE0);
		}
		else if(mode==1)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_WORKMODE1);
		}
		else if(mode==2)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_WORKMODE2);
		}
		else if(mode==3)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_WORKMODE3);
		}
		else if(mode==4)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_WORKMODE4);
		}
		else if(mode==5)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_WORKMODE5);
		}
		
		return result;
	}
	
	public String getClientReturnType()
	{
		String result="";
		int type = this.getReturnType();
		if(type == 0)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_RETURN0);
		}
		else if(type == 1)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_RETURN1);
		}
		return result;
	}
	
	public String getClientSwitch(int type)
	{
		String result="";
		if(type == 0)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_CLOSE);
		}
		else if(type == 1)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_OPEN);
		}
		return result;
	}
	
	public String getClientPortState(int type)
	{
		String result="";
		if(type == 0)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_PORTSTATE0);
		}
		else if(type == 1)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_PORTSTATE1);
		}
		else if(type == 2)
		{
			result=result=ResourceUtil.srcStr(StringKeysTip.TIP_PORTSTATE2);
		}
		return result;
	}
	
	@Override
	public void putObjectProperty() {
		this.putClientProperty("lacpSwitch", getClientSwitch(this.getLacpSwitch()));
		this.putClientProperty("sysPri", this.getSyspri());
		this.putClientProperty("lagId", this.getLagId());
		this.putClientProperty("workMode", getClientLagMode(this.getWorkMode()));
		this.putClientProperty("lagMode", getClientLagMode());
		this.putClientProperty("lagNum", this.getLagNum());
		this.putClientProperty("returnType", getClientReturnType());
		this.putClientProperty("waitTime", this.getWaitTime());
		StringBuffer portNum= new StringBuffer();
		StringBuffer portLacp=new StringBuffer();
		StringBuffer portStatus=new StringBuffer();
		StringBuffer localPort=new StringBuffer();
		List<LagPortStateInfo> ports = getLagPorts();
		for(int i = 0; i<ports.size()-1; i++)
		{
			portNum.append(ports.get(i).getPortNum()+",");
			portLacp.append(getClientSwitch(ports.get(i).getPortLacp())+",");
			portStatus.append(getClientPortState(ports.get(i).getPortStatus())+",");
			localPort.append(ports.get(i).getLocalPri()+",");
		}
		int index = ports.size()-1;
		portNum.append(ports.get(index).getPortNum());
		portLacp.append(getClientSwitch(ports.get(index).getPortLacp()));
		portStatus.append(getClientPortState(ports.get(index).getPortStatus()));
		localPort.append(ports.get(index).getLocalPri());
		
		this.putClientProperty("portNum", portNum.toString());
		this.putClientProperty("portLacp", portLacp.toString());
		this.putClientProperty("portStatus", portStatus.toString());
		this.putClientProperty("localPort", localPort.toString());
		
	}

}
