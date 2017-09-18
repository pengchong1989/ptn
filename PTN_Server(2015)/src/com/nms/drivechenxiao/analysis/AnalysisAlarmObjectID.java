package com.nms.drivechenxiao.analysis;

import com.nms.drive.service.impl.CoderUtils;
/**告警有关处理
 * **/
public class AnalysisAlarmObjectID {

	public static void main(String[] arg) {
		byte[] bbb = new byte[] { 0x00, 0x20, 0x00, 0x09 };
		// 1100100000000000001010
		// 00000000001010
		// xc/10/2
		System.out.println(analysisAlarmObjectID(bbb));
	}

	public static String analysisAlarmObjectID(byte[] alarmIdBytes) {
		String name = "";

		int alarmIdInt = CoderUtils.bytesToInt(alarmIdBytes);
		String alarmIdBinary = CoderUtils.convertBinary(alarmIdInt);
		int labelPrefix = 32 - alarmIdBinary.length();

		switch (labelPrefix) {

		case 4:
			name = analysisAlarmID_Port(alarmIdBytes);// 物理接口 port 端口带类型
			break;
		case 5:
			name = analysisAlarmID_Lsp(alarmIdBytes);// LSP
			break;
		case 6:
			name = analysisAlarmID_Pw(alarmIdBytes);// PW
			break;
		case 7:
			name = analysisAlarmID_Ac(alarmIdBytes);// AC
			break;
		case 8:// PON LLID
			name = alarmIdInt + ":PON LLID";
			break;
		case 10:// XC_IF XC_IF
			name = analysisAlarmID_XCIF(alarmIdBytes);
			break;
		case 11:// VPN
			name = alarmIdInt + ":VPN";
			break;
		case 12:
			name = analysisAlarmID_Card(alarmIdBytes);// card 单板带类型
			break;
		case 13:
			name = analysisAlarmID_Slot(alarmIdBytes);// slot 槽位
			break;
		case 14:
			name = analysisAlarmID_tunnel(alarmIdBytes);// 逻辑接口 TUNNEL
			break;
		case 15:
			name = analysisAlarmID_xc(alarmIdBytes);// XC XC
			break;
		case 16:// VLAN VLAN
			name = alarmIdInt + ":VLAN VLAN";
			break;
		case 17:// DUAL DUAL
			name = alarmIdInt + ":DUAL DUAL";
			break;
		case 18:// MSP MSP
			name = alarmIdInt + ":MSP MS";
			break;
		case 19:// RING RING
			name = alarmIdInt + ":RING RING";
			break;
		case 20:// CFP CFP
			name = alarmIdInt + ":CFP CFP";
			break;
		case 21:// MCN
			name = alarmIdInt + ":MCN";
			break;
		case 22:// CCN
			name = alarmIdInt + ":CCN";
			break;
		case 23:// LAG
			name = alarmIdInt + ":LAG";
			break;
		case 24:// track
			name = alarmIdInt + ":track";
			break;
		case 25:// L3VPN桥接口 vethl2/vethl3
			name = alarmIdInt + ":L3VPN桥接口 vethl2/vethl3";
			break;
		case 31:// 网元对象
			name = alarmIdInt + ":网元对象";
			break;

		default:
			name = "未知对象";
			break;
		}
		return name;
	}

	private static String analysisAlarmID_Port(byte[] alarmIdBytes) {
		String type = CoderUtils.convertBinary(CoderUtils.bytesToInt(alarmIdBytes[1]));
		// System.out.println(type);
		// System.out.println(type.substring(0, 4 - (8 - type.length())));
		int portType = CoderUtils.convertAlgorism(type.substring(0, 4 - (8 - type.length())).toCharArray());
		// System.out.println(type.substring(type.length() - 4));
		int subType = CoderUtils.convertAlgorism(type.substring(type.length() - 4).toCharArray());

		String portTyoeStr = "";
		switch (portType) {
		case 1:
			portTyoeStr = "eth";
			break;
		case 2:
			portTyoeStr = "sdh";
			break;
		case 3:
			portTyoeStr = "pdh";
			break;
		case 4:
			portTyoeStr = "xpon";
			break;
		case 5:
			portTyoeStr = "外时钟";
			break;
		case 6:
			portTyoeStr = "TOD";
			break;
		case 7:
			portTyoeStr = "io";
			break;
		default:
			portTyoeStr = "未知类型";
			break;
		}

		String subPortTyoeStr = "";

		if ("eth".equalsIgnoreCase(portTyoeStr)) {
			switch (subType) {
			case 1:
				subPortTyoeStr = "fe";
				break;
			case 2:
				subPortTyoeStr = "ge";
				break;
			case 3:
				subPortTyoeStr = "xg";
				break;
			case 4:
				subPortTyoeStr = "fx";
				break;
			default:
				subPortTyoeStr = "未知类型";
				break;
			}
		} else if ("pdh".equalsIgnoreCase(portTyoeStr)) {
			switch (subType) {
			case 1:
				subPortTyoeStr = "e1";
				break;
			case 2:
				subPortTyoeStr = "t1";
				break;
			case 3:
				subPortTyoeStr = "ima";
				break;
			default:
				subPortTyoeStr = "未知类型";
				break;
			}
		} else if ("sdh".equalsIgnoreCase(portTyoeStr)) {
			subPortTyoeStr = "stm1";
		} else if ("xpon".equalsIgnoreCase(portTyoeStr)) {
			switch (subType) {
			case 1:
				subPortTyoeStr = "gepon";
				break;
			case 2:
				subPortTyoeStr = "10gepon";
				break;
			case 3:
				subPortTyoeStr = "gpon";
				break;
			default:
				subPortTyoeStr = "未知类型";
				break;
			}
		} else if ("外时钟".equalsIgnoreCase(portTyoeStr)) {
			subPortTyoeStr = "extclk";
		} else if ("TOD".equalsIgnoreCase(portTyoeStr)) {
			subPortTyoeStr = "tod";
		} else if ("io".equalsIgnoreCase(portTyoeStr)) {
			switch (subType) {
			case 1:
				subPortTyoeStr = "in";
				break;
			case 2:
				subPortTyoeStr = "out";
				break;
			default:
				subPortTyoeStr = "未知类型";
				break;
			}
		}

		int card = CoderUtils.bytesToInt(alarmIdBytes[2]);
		int port = CoderUtils.bytesToInt(alarmIdBytes[3]);

		// System.out.println(portTyoeStr + "/" + subPortTyoeStr + "." + card + "." + port);
		String name = portTyoeStr + "/" + subPortTyoeStr + "." + card + "." + port;

		return name;
	}

	private static String analysisAlarmID_Lsp(byte[] alarmIdBytes) {
		byte[] tunnelIdBytes = new byte[4];
		tunnelIdBytes[0] = 0x00;
		tunnelIdBytes[1] = 0x00;
		tunnelIdBytes[2] = alarmIdBytes[1];
		tunnelIdBytes[3] = alarmIdBytes[2];
		int tunnelId = CoderUtils.bytesToInt(tunnelIdBytes);
		int lspId = CoderUtils.bytesToInt(alarmIdBytes[3]);
		String name = "tunnel/" + tunnelId + "/" + lspId;
		return name;
	}

	private static String analysisAlarmID_Pw(byte[] alarmIdBytes) {

		int pwType = CoderUtils.bytesToInt(alarmIdBytes[1]);
		String pwTypeStr = "";
		switch (pwType) {
		case 1:
			pwTypeStr = "eth";
			break;
		case 2:
			pwTypeStr = "sdh";
			break;
		case 3:
			pwTypeStr = "pdh";
			break;
		case 4:
			pwTypeStr = "l3vpnpeer";
			break;
		case 5:
			pwTypeStr = "peergrp";
			break;
		default:
			pwTypeStr = "未知类型";
			break;
		}

		byte[] pwIdBytes = new byte[4];
		pwIdBytes[0] = 0x00;
		pwIdBytes[1] = 0x00;
		pwIdBytes[2] = alarmIdBytes[2];
		pwIdBytes[3] = alarmIdBytes[3];
		int pwId = CoderUtils.bytesToInt(pwIdBytes);

		String name = "pw" + pwTypeStr + "/" + pwId;
		return name;
	}

	private static String analysisAlarmID_Ac(byte[] alarmIdBytes) {

		int acType = CoderUtils.bytesToInt(alarmIdBytes[1]);
		String pwTypeStr = "";
		switch (acType) {
		case 1:
			pwTypeStr = "eth";
			break;
		case 2:
			pwTypeStr = "sdh";
			break;
		case 3:
			pwTypeStr = "pdh";
			break;
		default:
			pwTypeStr = "未知类型";
			break;
		}

		byte[] acIdBytes = new byte[4];
		acIdBytes[0] = 0x00;
		acIdBytes[1] = 0x00;
		acIdBytes[2] = alarmIdBytes[2];
		acIdBytes[3] = alarmIdBytes[3];
		int acId = CoderUtils.bytesToInt(acIdBytes);

		String name = "aclist/" + pwTypeStr + "/" + acId;
		return name;
	}

	private static String analysisAlarmID_Card(byte[] alarmIdBytes) {
		int cardtype = CoderUtils.bytesToInt(alarmIdBytes[2]);
		int card = CoderUtils.bytesToInt(alarmIdBytes[3]);

		String name = "card/" + card + "[" + cardtype + "]";
		return name;
	}

	private static String analysisAlarmID_Slot(byte[] alarmIdBytes) {
		int slottype = CoderUtils.bytesToInt(alarmIdBytes[2]);
		int slot = CoderUtils.bytesToInt(alarmIdBytes[3]);

		String name = "slot/" + slot + "[" + slottype + "]";
		return name;
	}

	private static String analysisAlarmID_tunnel(byte[] alarmIdBytes) {
		int tunnel = CoderUtils.bytesToInt(alarmIdBytes[3]);

		String name = "tunnel/" + tunnel;
		return name;
	}

	private static String analysisAlarmID_xc(byte[] alarmIdBytes) {
		int xc = CoderUtils.bytesToInt(alarmIdBytes[3]);

		String name = "xc/" + xc;
		return name;
	}

	private static String analysisAlarmID_XCIF(byte[] alarmIdBytes) {
		int alarmIdInt = CoderUtils.bytesToInt(alarmIdBytes);
		String alarmIdBinary = CoderUtils.convertBinary(alarmIdInt);
		// System.out.println(alarmIdBinary);
		// System.out.println(alarmIdBinary.length());
		// System.out.println(alarmIdBinary.length() - 18);
		String valueBinary = alarmIdBinary.substring(alarmIdBinary.length() - 18, alarmIdBinary.length());
		// System.out.println(valueBinary);
		String xcStr = valueBinary.substring(0, 16);
		String xcifStr = valueBinary.substring(16, valueBinary.length());
		// System.out.println(xcStr);
		// System.out.println(xcifStr);

		int xc = CoderUtils.convertAlgorism(xcStr.toCharArray());
		int xcif = CoderUtils.convertAlgorism(xcifStr.toCharArray());
		String name = "xc/" + xc + "/" + xcif;
		return name;
	}
}
