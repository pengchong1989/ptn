package com.nms.db.enums;

/**
 * 性能对象类型
 * @author lp
 *
 */
public enum EObjectType implements IntEnum{
	NONE(0),SITEINST(1),SLOTINST(2),TUNNEL(3),PW(4),PORT(5),LSP(6),POWER(7),SEGMENT(8),VPWS(9),VPLS(10),
	CLOCK(11),E1(12),ETHOAM(13),CI_TEMP(14),LAG(15),AC(16),MSP(17),DUAL(18),MCN(19),CCN(20),RING(21),
	SLOT(22),TOD(23),FAN1(24),FAN2(25),FAN3(26),EMSCLIENT(27),MSPW(28),CARDSLOT2TEMP(29),CARDSLOT3TEMP(30),
	CPURATIO(31),TMS_OAM(32);
	private int value;
	
	private EObjectType(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static EObjectType forms(int value) {
		for(EObjectType serviceType : EObjectType.values()){
			if(value == serviceType.value)
				return serviceType;
		}
		return null;
	}
	
	@Override
	public String toString(){
		if(value == 1)
			return "SITEINST";
		else if(value == 2) 
			return "SLOTINST";
		else if(value == 3) 
			return "TUNNEL";
		else if(value == 4) 
			return "PW";
		else if(value == 5) 
			return "PORT";
		else if(value == 6) 
			return "LSP";
		else if(value == 7)
			return "POWER";
		else if(value == 8)
			return "SEGMENT";
		else if(value == 9)
			return "VPWS";
		else if(value == 10)
			return "VPLS";
		else if(value == 11)
			return "CLOCK";
		else if(value == 12)
			return "E1";
		else if(value == 13)
			return "ETHOAM";
		else if(value == 14)
			return "CI_TEMP";
		else if(value == 15)
			return "LAG";
		else if(value == 16)
			return "AC";
		else if(value == 17)
			return "MSP";
		else if(value == 18)
			return "DUAL";
		else if(value == 19)
			return "MCN";
		else if(value == 20)
			return "CCN";
		else if(value == 21)
			return "RING";
		else if(value == 22)
			return "SLOT";
		else if(value == 23)
			return "TOD";
		else if(value == 24)
			return "FAN1";
		else if(value == 25)
			return "FAN2";
		else if(value == 26)
			return "FAN3";
		else if(value == 27)
			return "EMSCLIENT";
		else if(value == 28)
			return "MSPW";
		else if(value == 29)
			return "CARDSLOT2TEMP";
		else if(value == 30)
			return "CARDSLOT3TEMP";
		else if(value == 31)
			return "CPURATIO";
		else if(value == 32){
			return "TMSOAM";
		}
			return null;
	}
}
