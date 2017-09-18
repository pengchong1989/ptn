package com.nms.corba.ninterface.enums;

/**
 * OAM类型
 * @author dzy
 *
 */
public enum EOAMType {
	SECTION(1),		
	TUNNEL(2),
	PW(3),
	ETHERNET(4),
	ETHLINK(5),
	SECTION_TEST(6),
	TUNNEL_TEST(7),
	PW_TEST(8),
	MEP(9),		
	MIP(10);
	private int value;
	private EOAMType(int value) {
		this.value = value;
	}
	public int getValue(){
		return value;
	}
	public EOAMType from(int value) {
		for(EOAMType eOAMBusiType : EOAMType.values()){
			if(value == eOAMBusiType.value)
				return eOAMBusiType;
		}
		return null;
	}
	
	public static int from(String type){
		if(type.equals(EOAMType.SECTION.toString())){
			return 1;
		}else if(type.equals(EOAMType.TUNNEL.toString())){
			return 2;
		}else if(type.equals(EOAMType.PW.toString())){
			return 3;
		}else if(type.equals(EOAMType.ETHERNET.toString())){
			return 4;
		}else if(type.equals(EOAMType.ETHLINK.toString())){
			return 5;
		}else if(type.equals(EOAMType.SECTION_TEST.toString())){
			return 6;
		}else if(type.equals(EOAMType.TUNNEL_TEST.toString())){
			return 7;
		}else if(type.equals(EOAMType.PW_TEST.toString())){
			return 8;
		}else if(type.equals(EOAMType.MEP.toString())){
			return 9;
		}else if(type.equals(EOAMType.MIP.toString())){
			return 10;
		}else {
			return -1;
		}
	}
	@Override
	public String toString() {
		if(this.value == 1){
			return "SECTION";
		}else if(this.value == 2){
			return "TUNNEL";
		}else if(this.value == 3){
			return "PW";
		}else if(this.value == 4){
			return "ETHERNET";
		}else if(this.value == 5){
			return "ETHLINK";
		}else if(this.value == 6){
			return "SECTION_TEST";
		}else if(this.value == 7){
			return "TUNNEL_TEST";
		}else if(this.value == 8){
			return "PW_TEST";
		}else if(this.value == 9){
			return "MEP";
		}else if(this.value == 10){
			return "MIP";
		}
		return "";
	}
}
