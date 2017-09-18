package com.nms.drivechenxiao.service.bean.protocols.mpls.elsp;

import java.util.ArrayList;
import java.util.List;
/**端口exp业务映射 里面的 elsp里的 输出
 * **/
public class CostoexpObject {
	private String name; //默认0,; int32 ;[1,1] ;
	private List<String> ref = new ArrayList<String>(); //引用clrtoexp接口
	private String be;//业务等级be到exp到映射 ; int32;[0,7] ;0
	private String af1; //业务等级af1到exp到映射 ; int32;[0,7] ;1
	private String af2; //业务等级af2到exp到映射 ; int32;[0,7] ;2
	private String af3; //业务等级af3到exp到映射 ; int32;[0,7] ;3
	private String af4; //业务等级af4到exp到映射 ; int32;[0,7] ;4
	private String ef; //业务等级ef到exp到映射 ; int32;[0,7] ;5
	private String cs6; //业务等级cs6到exp到映射 ; int32;[0,7] ;6
	private String cs7; //业务等级cs7到exp到映射 ; int32;[0,7] ;7
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getRef() {
		return ref;
	}
	public void setRef(List<String> ref) {
		this.ref = ref;
	}
	public String getBe() {
		return be;
	}
	public void setBe(String be) {
		this.be = be;
	}
	public String getAf1() {
		return af1;
	}
	public void setAf1(String af1) {
		this.af1 = af1;
	}
	public String getAf2() {
		return af2;
	}
	public void setAf2(String af2) {
		this.af2 = af2;
	}
	public String getAf3() {
		return af3;
	}
	public void setAf3(String af3) {
		this.af3 = af3;
	}
	public String getAf4() {
		return af4;
	}
	public void setAf4(String af4) {
		this.af4 = af4;
	}
	public String getEf() {
		return ef;
	}
	public void setEf(String ef) {
		this.ef = ef;
	}
	public String getCs6() {
		return cs6;
	}
	public void setCs6(String cs6) {
		this.cs6 = cs6;
	}
	public String getCs7() {
		return cs7;
	}
	public void setCs7(String cs7) {
		this.cs7 = cs7;
	}
	
	
}
