package com.nms.db.bean.equipment.port;

import java.io.Serializable;

/**
*    
* 项目名称：WuHanPTN2012   
* 类名称：PortNniAttr   
* 类描述：   端口属性nni部分
* 创建人：kk   
* 创建时间：2013-7-16 上午10:01:58   
* 修改人：kk   
* 修改时间：2013-7-16 上午10:01:58   
* 修改备注：   
* @version    
*
 */
@SuppressWarnings("serial")
public class PortNniAttr implements Serializable {

	private String staticMac;//静态MAC地址
	private String neighbourId;//邻居网元ID
	private String operMac;//对端接口mac地址
	private String operId;//对端接口ID
	private int neighbourFind;//邻居发现状态 对应code表主键
	private int ccnEnable;//ccn承载使能 0=false 1=true
	private String nniVlanid;//缺省vlanid
	private String nniVlanpri;//缺省vlan优先级
	private int stat=0;	//邻居自动发现状态   0=自动发现中  1=完成发现   2=光纤错连  用此字段来验证是否连接段，1为连接，其余为未连接
	
	@Override
	public String toString(){
		StringBuffer sb=new StringBuffer().append(" staticMac=").append(staticMac)
		.append(" ;neighbourId=").append(neighbourId).append(" ;operMac=").append(operMac)
		.append(" ;operId=").append(operId).append(" ;neighbourFind=").append(neighbourFind)
		.append(" ;ccnEnable=").append(ccnEnable).append(" ;nniVlanid=").append(nniVlanid)
		.append(" ;nniVlanpri=").append(nniVlanpri);
		return sb.toString();
	}
	
	public String getStaticMac() {
		return staticMac;
	}
	public void setStaticMac(String staticMac) {
		this.staticMac = staticMac;
	}
	public String getNeighbourId() {
		return neighbourId;
	}
	public void setNeighbourId(String neighbourId) {
		this.neighbourId = neighbourId;
	}
	public String getOperMac() {
		return operMac;
	}
	public void setOperMac(String operMac) {
		this.operMac = operMac;
	}
	public String getOperId() {
		return operId;
	}
	public void setOperId(String operId) {
		this.operId = operId;
	}
	public int getNeighbourFind() {
		return neighbourFind;
	}
	public void setNeighbourFind(int neighbourFind) {
		this.neighbourFind = neighbourFind;
	}
	public int getCcnEnable() {
		return ccnEnable;
	}
	public void setCcnEnable(int ccnEnable) {
		this.ccnEnable = ccnEnable;
	}
	public String getNniVlanid() {
		return nniVlanid;
	}
	public void setNniVlanid(String nniVlanid) {
		this.nniVlanid = nniVlanid;
	}
	public String getNniVlanpri() {
		return nniVlanpri;
	}
	public void setNniVlanpri(String nniVlanpri) {
		this.nniVlanpri = nniVlanpri;
	}

	public int getStat() {
		return stat;
	}

	public void setStat(int stat) {
		this.stat = stat;
	}
}
