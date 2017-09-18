package com.nms.db.enums;

public enum EServiceType {
	CES(0),ELINE(1),ELAN(2),ETREE(3),TUNNEL(4),PW(5),SECTION(6),ACPORT(7),
	TMSBUGCONTROLLER(8),TMPBUGCONTROLLER(9),TMCBUGCONTROLLER(10),LINKOAM(11),
	PWNNIBUFFER(12),SUBNET(13),CLIENT(14),CARD(15),OSPF(16),SITE(17),MCN(18),
	ETH(19),PDH(20),SDH(21),SDHSLOTTIME(22),LAG(23),CLOCKSTATUS(24),CLOCKSOURCE(25),
	PTPCONFIG(26),CLOCKPORTCONFIG(27),EXTERNALCLOCK(28),LINECLOCK(29),LOOPPROTECT(30),
	OAM(31),ETHERNET(32),QOS(33),DATADOWNLOAD(34),PWETH(35),PWPDH(36),PWSDH(37),CESPDH(38),
	CESSDH(39),DUAL(40),MSPPROTECT(41),OAMETHLINK(42),TUNNELMIPOAM(43),TUNNELMEPOAM(44),
	SECTION_TEST(45),TUNNEL_TEST(46),PW_TEST(47),WRAPPINGPROTECT(48),PERFORMANCETASK(49),
	QINQ(50),RULENAME(51),CCC(52);
	
	private int value;
	
	private EServiceType(int value) {
		this.value = value;
	}
	
	public int getValue(){
		return value;
	}
	
	public static EServiceType from(int value) {
		for(EServiceType serviceType : EServiceType.values()){
			if(value == serviceType.value)
				return serviceType;
		}
		return null;
	}

	public static int from(String value){
		if(value.equals(EServiceType.CES.toString())){
			return 1;
		}else if(value.equals(EServiceType.ELINE.toString())){
			return 1;
		}else if(value.equals(EServiceType.TUNNEL.toString())){
			return 4;
		}else if(value.equals(EServiceType.PW.toString())){
			return 5;
		}else if(value.equals(EServiceType.ETH.toString())){
			return 19;
		}else if(value.equals(EServiceType.ETREE.toString())){
			return 3;
		}else if(value.equals(EServiceType.ELAN.toString())){
			return 2;
		}else if(value.equals(EServiceType.WRAPPINGPROTECT.toString())){
			return 48;
		}else if(value.equals(EServiceType.PERFORMANCETASK.toString())){
			return 19;
		}else if(value.equals(EServiceType.QINQ.toString())){
			return 50;
		}else if(value.equals(EServiceType.CCC.toString())){
			return 52;
		}
		return 99;
	}
	
	@Override
	public String toString() {
		if(this.value == 0){
			return "CES";
		}else if(this.value == 1){
			return "ELINE";
		}else if(this.value == 2){
			return "ELAN";
		}else if(this.value == 3){
			return "ETREE";
		}else if(this.value == 4){
			return "TUNNEL";
		}else if(this.value == 5){
			return "PW";
		}else if(this.value == 6){
			return "SECTION";
		}else if(this.value == 7){
			return "ACPORT";
		}else if(this.value==8){
			return "TMSBug";
		}else if(this.value==9){
			return "TMPBug";
		}else if(this.value==10){
			return "TMCBug";
		}else if(this.value==11){
			return "LINKOAM";
		}else if(this.value==12){
			return "PWNNIBUFFER";
		}else if(this.value==13){
			return "SUBNET";
		}else if(this.value==14){
			return "CLIENT";
		}else if(this.value==15){
			return "CARD";
		}else if(this.value==16){
			return "OSPF";
		}else if(this.value==17){
			return "SITE";
		}else if(this.value==18){
			return "MCN";
		}else if(this.value==19){
			return "ETH";
		}else if(this.value==20){
			return "PDH";
		}else if(this.value==21){
			return "SDH";
		}else if(this.value==22){
			return "SDHSLOTTIME";
		}else if(this.value==23){
			return "LAG";
		}else if(this.value==24){
			return "CLOCKSTATUS";
		}else if(this.value==25){
			return "CLOCKSOURCE";
		}else if(this.value==26){
			return "PTPCONFIG";
		}else if(this.value==27){
			return "CLOCKPORTCONFIG";
		}else if(this.value==28){
			return "EXTERNALCLOCK";
		}else if(this.value==29){
			return "LINECLOCK";
		}else if(this.value==30){
			return "LOOPPROTECT";
		}else if(this.value==31){
			return "OAM";
		}else if(this.value==32){
			return "ETHERNET";
		}else if(this.value==33){
			return "QOS";
		}else if(this.value==34){
			return "DATADOWNLOAD";
		}else if(this.value==35){
			return "PWETH";
		}else if(this.value==36){
			return "PWPDH";
		}else if(this.value==37){
			return "PWSDH";
		}else if(this.value==38){
			return "CESPDH";
		}else if(this.value==39){
			return "CESSDH";
		}else if(this.value==40){
			return "DUAL";
		}else if(this.value==41){
			return "MSPPROTECT";
		}else if(this.value==42){
			return "OAMETHLINK";
		}else if(this.value==43){
			return "TUNNELMIPOAM";
		}else if(this.value==44){
			return "TUNNELMEPOAM";
		}else if(this.value==45){
			return "SECTION_TEST";
		}else if(this.value==46){
			return "TUNNEL_TEST";
		}else if(this.value==47){
			return "PW_TEST";
		}else if(this.value==48){
			return "WRAPPINGPROTECT";
		}else if(this.value==49){
			return "PERFORMANCETASK";
		}else if(this.value==50){
			return "QINQ";
		}else if(this.value==52){
			return "CCC";
		}
		return "";
	}
}
