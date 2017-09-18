package com.nms.drivechenxiao.service.bean.porteth.ac;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.nms.drivechenxiao.service.bean.cmap.L2Object;
import com.nms.drivechenxiao.service.bean.cmap.L3Object;

public class AcQosObject {

	private String name = "";// 
	private String type = "";// ETHAC_L2,ETHAC_L3,ETHAC_VLANPRI 
	private String seq = "";// 匹配优先级 ;int32 ;[0,1000] ; 1
	private String cos = "";// 业务等级(cs7/cs6/ef/af4/af3/af2/af1/be);
	private String cir = "";// 承诺信息速率 ; int32 ;[0,10000000] ; 0
	private String cbs = "";// 承诺突发长度 ; int32 ; -1 |[4096,256000000] ; -1 
	private String eir = "";// 额外速率 ; int32 ;[0,10000000] ; 0
	private String ebs = "";// 额外突发长度 ; int32 ; -1 |[4096,256000000] ; -1
	private String coloraware = "";// 测量是色盲还是色敏感 ; bool ; ; false
	private String pir = "";// 最大峰值速率 ; int32 ; [0,20000000] ; 0
	private boolean createl2 = false; //是否创建l2细分流模板
	private boolean createl3 = false; //是否创建l3细分流模板
	private boolean deletel2 = false; //是否创建l2细分流模板
	private boolean deletel3 = false; //是否创建l3细分流模板
	private L2Object l2 =new L2Object(); 
	private L3Object l3 =new L3Object(); 
	private HashMap<String,AcQosObject> XFAcQosList = new HashMap<String,AcQosObject>();//流细分 
	
		
	public List<AcQosObject> getXFAcQosList() {
		List<AcQosObject> listAcQos = new ArrayList<AcQosObject>();
		for(AcQosObject ac : XFAcQosList.values()){
			listAcQos.add(ac);
		}
		return listAcQos;
	}
	public HashMap<String,AcQosObject> getXFAcQosListM() {
		return XFAcQosList;
	}
	public void setXFAcQosList(List<AcQosObject> acQosList) {
		for(AcQosObject acqos : acQosList){
			XFAcQosList.put(acqos.getName(), acqos);
		}
	}
	
	public boolean isDeletel2() {
		return deletel2;
	}
	public void setDeletel2(boolean deletel2) {
		this.deletel2 = deletel2;
	}
	public boolean isDeletel3() {
		return deletel3;
	}
	public void setDeletel3(boolean deletel3) {
		this.deletel3 = deletel3;
	}
	public void setXFAcQosList(HashMap<String, AcQosObject> acQosList) {
		XFAcQosList = acQosList;
	}

	public L2Object getL2() {
		return l2;
	}

	public void setL2(L2Object l2) {
		this.l2 = l2;
	}

	public L3Object getL3() {
		return l3;
	}

	public void setL3(L3Object l3) {
		this.l3 = l3;
	}

	public boolean getCreatel2() {
		return createl2;
	}

	public void setCreatel2(boolean createl2) {
		this.createl2 = createl2;
	}

	public boolean getCreatel3() {
		return createl3;
	}

	public void setCreatel3(boolean createl3) {
		this.createl3 = createl3;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getCos() {
		return cos;
	}

	public void setCos(String cos) {
		this.cos = cos;
	}

	public String getCir() {
		return cir;
	}

	public void setCir(String cir) {
		this.cir = cir;
	}

	public String getCbs() {
		return cbs;
	}

	public void setCbs(String cbs) {
		this.cbs = cbs;
	}

	public String getEir() {
		return eir;
	}

	public void setEir(String eir) {
		this.eir = eir;
	}

	public String getEbs() {
		return ebs;
	}

	public void setEbs(String ebs) {
		this.ebs = ebs;
	}

	public String getColoraware() {
		return coloraware;
	}

	public void setColoraware(String coloraware) {
		this.coloraware = coloraware;
	}

	public String getPir() {
		return pir;
	}

	public void setPir(String pir) {
		this.pir = pir;
	}

}
