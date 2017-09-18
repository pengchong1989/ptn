package com.nms.db.enums;

public enum ETunnelMenu {
	
	SERACH(0),SELECTA(1),SELECTZ(2),MUSTSITE(3),PROTECT(4),CANELPROTECT(5),CANELCONFIG(6),SELECTPROTECT(7),
	MUSTSEGMENT(8),CANCELMUSTSEGMENT(9),MUSTPROSEGMENT(10),CANCELPROMUSTSEGMENT(11),SELECTPATH(12),CANELPATH(13);
	private int value;
	
	private ETunnelMenu(int value) {
		this.value = value;
	}
	public int getValue() {
		return value;
	}

	public static ETunnelMenu forms(int value) {
		for(ETunnelMenu serviceType : ETunnelMenu.values()){
			if(value == serviceType.value)
				return serviceType;
		}
		return null;
	}
}
