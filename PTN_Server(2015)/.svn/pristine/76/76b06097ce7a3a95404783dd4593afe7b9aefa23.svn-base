package com.nms.corba.ninterface.util;

/**
 * 层速率枚举类
 * 
 * @author kk
 * 
 */
public enum ELayerRate {

	TUNNEL(414),  //对应中国移动规范 LR_MPLS_LSP
	PW(415),	//对应中国移动规范 LR_MPLS_PW
	PORT(96), //对应中兴ican-layerrate-i18n.xml "以太网端口"
	PGP(4),
	MSP(5),
	CTP(7),	//内部使用，没对外
	CES(5), //对应中兴ican-layerrate-i18n.xml "TDM业务"
	CLOCKSOURCE(307),	//对应中兴ican-layerrate-i18n.xml "时钟(源)"
	TNP(15),	//内部使用，没对外
	TOPOLOGICALLINK(401), //对应中国移动规范 LR_MPLS_SECTION
	TDMPORT(307),	//对应中兴ican-layerrate-i18n.xml "PDH 2M物理接口"
	ETHSERVICE(307),	//对应中兴ican-layerrate-i18n.xml "以太网业务"
	LAG(305),
	E1(5);
	
	private int value;

	private ELayerRate(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static ELayerRate from(int value) {
		for (ELayerRate eLayerRate : ELayerRate.values()) {
			if (value == eLayerRate.value)
				return eLayerRate;
		}
		return null;
	}

}
