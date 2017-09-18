package com.nms.drivechenxiao.test.core;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.clock.AnalysisClock;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisQOS;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisRING;
import com.nms.drivechenxiao.analysis.ne.AnalysisRoute;
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcQosObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.RingObject;
import com.nms.drivechenxiao.test.core.ui.HzPtnMainUI;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class HzMain extends CxtOpLump {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		HzPtnMainUI HzPtnMainUI = new HzPtnMainUI();
		HzPtnMainUI.setLocation(UiUtil.getWindowWidth(HzPtnMainUI.getWidth()), UiUtil.getWindowHeight(HzPtnMainUI.getHeight()));
//		UiUtil.uiStyle(HzPtnMainUI);
		HzPtnMainUI.setVisible(true);
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				ExceptionManage.dispose(e,HzMain.class);
			}
		}
	}

	public byte[] getLoginBytes(String user, String password, int logoutBool, int session, int seqid) {
		byte[] msg = new byte[0];
		byte[] logout = new byte[1];
		if (logoutBool == 0) {
			logout[0] = 0x00;
		} else {
			logout[0] = 0x01;
		}

		msg = arraycopy(msg, logout);
		msg = arraycopy(msg, getCxtString(user));
		msg = arraycopy(msg, getCxtString(password));
		byte[] command = getHeader(msg.length, CoreOper.ECXTMSG_REQ_LOGIN, session, seqid, true);
		command = arraycopy(command, msg);

		// print16String(command);
		return command;
	}

	/**
	 * 设置NE属性
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setNE(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne"));
		cxtOpItems.add(set("id", "20.0.0.5")); // 网元id
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 插卡
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setSlot(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/slot/1/lg"));
		cxtOpItems.add(set("type", "ACTS_B"));// 可设置类型ACTS/ACTP_B/XCTPS/ACTS_B
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 设置端口属性
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setPort(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/eth/ge.3.1"));		
		cxtOpItems.add(set("role", "nni"));// 可设置属性 值nni/uni
		cxtOpItems.add(set("admin", "down"));// 可设置属性值up/down
		
//		cxtOpItems.add(set("sfpexptype", "1000BASE_SX"));//SFP期望类型 0 unknown;1 Empty;2 RateMatch;
//		cxtOpItems.add(set("framelen", "1600"));//最大帧长 >=1600
		
//		cxtOpItems.add(set("speed", "neg100m"));//1=100m; 2=1g ;3=10g ;4=neg100m ;5=neg1g ;6=2g5
//		cxtOpItems.add(setmac("mac", (CoderUtils.MacTo1a("48df2c000041"))));
//		cxtOpItems.add(set("xgwan", "true"));
		
//		cxtOpItems.add(setNum("iused", "128"));
//		cxtOpItems.add(set("slowproto_tocpu", "true"));//慢协议 true
//		cxtOpItems.add(set("fc", "true")); //流控 true
//		cxtOpItems.add(set("xgwan", "true")); //true
//		cxtOpItems.add(set("oused", "10000"));
//		cxtOpItems.add(set("", ""));
//		cxtOpItems.add(set("", ""));
		
//		cxtOpItems.add(cd("ne/interfaces/eth/fe.1.1/uni"));
//		cxtOpItems.add(set("iclrmode", "vlanpri2cng")); // ETHAC进来的颜色模式(信任CFI/信任VLAN优先级) **
//		cxtOpItems.add(set("oclrmode", "trustcng")); // ETHAC出去的颜色模式(色盲(blind)/信任)) **
//		cxtOpItems.add(set("cos2vlanpri", "0")); // 细分ETHAC出去的流  **
////----
//		cxtOpItems.add(set("vlanmode", "access"));// 以太网VLAN模式  access ; trunk ; hybrid
//		cxtOpItems.add(set("tpid", "8100")); // OUTER VLAN的TPID 8100; 88a8 ; 9100 
//		cxtOpItems.add(set("encap", "1q"));// 以太网封装   1q ; 1ad 
//		cxtOpItems.add(set("bcastlimit", "5000"));// 广播报文抑制
//		cxtOpItems.add(setNum("egbwlimit", "100000"));// 未知单播报文抑制
//		cxtOpItems.add(set("mcastlimit", "7000"));// 组播报文抑制
		
//---pdh
//		cxtOpItems.add(cd("ne/interfaces/pdh/e1.1.1"));
//		cxtOpItems.add(set("admin", "down"));// 可设置属性值up/down
//		cxtOpItems.add(set("linecode", "ami"));// **linecode 0=hdb3 ;2=ami **/
//---		
//		cxtOpItems.add(cd("ne/interfaces/eth/ge.1.4"));
//		cxtOpItems.add(set("ringid", "1"));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 创建OSPF
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createOSPF(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols"));
		cxtOpItems.add(create("ospf", null));
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 创建AREA
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createAREA(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/ospf"));
		cxtOpItems.add(create("area.0", null));
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 创建MCN
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setMCN(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "mtu"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "1500"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ip"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "100.1.1.151/24")); // MCN规划ip
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/mcn"));
		cxtOpItems.add(mset("1", cxtATTable));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 创建CCN
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createCCN(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "up"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "mtu"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "1400"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ip"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "200.1.2.2/24")); // CCN规划ip
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "peer"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "20.0.0.35")); // 对端网元id
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/ccn"));
		cxtOpItems.add(create("1", cxtATTable));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * OSPF接口
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createOSPFInterfaces(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/ccn/1")); // MCN与CCN创建OSPF接口
		cxtOpItems.add(create("ospf", null));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * MCN重分发
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setRedistribute(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes2 = new ArrayList<CxtAtomType>();
		cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, "enable"));
		cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_BOOL, 0));
		CxtATTable cxtATTable2 = getCxtATTable(cxtAtomTypes2.size() / 2, cxtAtomTypes2);

		List<CxtAtomType> cxtAtomTypes1 = new ArrayList<CxtAtomType>();
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "metrictype"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "type2"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "metric"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "-1"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "distribute_list"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTable2));
		CxtATTable cxtATTable1 = getCxtATTable(cxtAtomTypes1.size() / 2, cxtAtomTypes1);

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "mcn"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTable1));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/ospf"));
		cxtOpItems.add(mset("redistribute", cxtATTable));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] Lag(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "psc"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, 0));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "role"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, 1));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 1));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "desc"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "CXTIF"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "mac"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_64, 111));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "schmode"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "1"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "recover"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, true));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,
		// "permitpktloop"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, true));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "framelen"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "64"));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		List<CxtAtomType> cxtAtomTypesPort = new ArrayList<CxtAtomType>();
		cxtAtomTypesPort.add(getCxtAtomType(CxtAtomType.AT_STRING, "wtrtime"));
		cxtAtomTypesPort.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 5));
		cxtAtomTypesPort.add(getCxtAtomType(CxtAtomType.AT_STRING, "lagid"));
		cxtAtomTypesPort.add(getCxtAtomType(CxtAtomType.AT_NUM_64, 1));
		CxtATTable cxtATTablePort = getCxtATTable(cxtAtomTypesPort.size() / 2, cxtAtomTypesPort);

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/lag"));
		cxtOpItems.add(create("1", cxtATTable));
		for (int i = 0; i < 2; i++) {
			cxtOpItems.add(cd("ne/interfaces/eth/ge.3." + (i + 3)));
			cxtOpItems.add(create("lag", cxtATTablePort));
		}
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] Lag(String type, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "psc"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, "1"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "role"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, "1"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, "1"));
		//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "desc"));
		//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, lagObject.getDesc()));
		//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "mac"));
		//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_64, lagObject.getMac()));
		//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "iused"));
		//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, "1"));
		//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "oused"));
		//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, "1"));
		//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "schmode"));
		//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, lagObject.getSchmode()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "recover"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "permitpktloop"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "framelen"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, "64"));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		List<CxtAtomType> cxtAtomTypesPort = new ArrayList<CxtAtomType>();
		//		cxtAtomTypesPort.add(getCxtAtomType(CxtAtomType.AT_STRING, "wtrtime"));
		//		cxtAtomTypesPort.add(getCxtAtomType(CxtAtomType.AT_STRING, ethPort.getLag().getWtrtime()));
		cxtAtomTypesPort.add(getCxtAtomType(CxtAtomType.AT_STRING, "lagid"));
		cxtAtomTypesPort.add(getCxtAtomType(CxtAtomType.AT_STRING, "1"));
		CxtATTable cxtATTablePort = getCxtATTable(cxtAtomTypesPort.size() / 2, cxtAtomTypesPort);

		cxtOpItems.add(begin(3));
		if ("create".equals(type)) {
			cxtOpItems.add(cd("ne/interfaces/lag"));
			cxtOpItems.add(create("1", cxtATTable));
			cxtOpItems.add(cd("ne/interfaces/eth/fx.1.1"));
			cxtOpItems.add(create("lag", cxtATTablePort));
			cxtOpItems.add(cd("ne/interfaces/eth/fx.1.2"));
			cxtOpItems.add(create("lag", cxtATTablePort));
		} else if ("update".equals(type)) {
			cxtOpItems.add(cd("ne/interfaces/lag"));
			cxtOpItems.add(mset("1", cxtATTable));
		}

		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	
	public byte[] selectAllEthPort(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

//		cxtOpItems.add(begin(3));
//		cxtOpItems.add(cd("ne/interfaces/tunnel"));
//		cxtOpItems.add(get("6", 2));

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/tunnel/1/protection")) ;//
//		cxtOpItems.add(set("apscmd", "forceswitch::work"));
		cxtOpItems.add(set("apscmd", "forceswitch::protection"));
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	/**
	 * add by stonesLi for ACN test
	 * **/
	public byte[] operForACN(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

//		cxtOpItems.add(begin(3));
//		cxtOpItems.add(cd("ne/interfaces/tunnel"));
//		cxtOpItems.add(get("6", 2));
System.out.println("--ACN start -- ");
		cxtOpItems.add(begin(4));
		cxtOpItems.add(cd("ne/interfaces/acn"));
		cxtOpItems.add(delete("1")) ;
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] selectAllTuunel(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/tunnel"));
		cxtOpItems.add(get(s, 1));
//		cxtOpItems.add(begin(3));
//		cxtOpItems.add(cd("ne/interfaces/acn"));
//		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] selectAllAcn(int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/acn"));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] selectTunnelProtection(int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/tunnel/4/protection"));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] setportUni(int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/eth/fe.1.2"));
		cxtOpItems.add(set("role", "uni"));// 可设置属性 值nni/uni
		cxtOpItems.add(set("admin", "up"));// 可设置属性值up/down
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	
	public byte[] operForETHAC(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/eth/fe.1.1"));
		cxtOpItems.add(delete("1"));
		cxtOpItems.add(commit());
		
		//deleteACQOS start
		cxtOpItems.add(cd("ne/pmap/ethac"));
		cxtOpItems.add(delete("l241"));
		cxtOpItems.add(commit());
		//deleteACQOS end .
		//
		
		//
		
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//测试 for 网元自动发现.
	public byte[] operForFindID(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne"));
		cxtOpItems.add(get("ver", 1));
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//测试for 初始化设备
	public byte[] operForClearID(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
//		cxtOpItems.add(cd("ne"));
//		cxtOpItems.add(call("getdblabel()"));
//		cxtOpItems.add(call("ne.dropdb","1"));
//		cxtOpItems.add(call("getdblabel"));

//---------测试修改用户密码
		cxtOpItems.add(call("ne.proc.secu.passwd","admin,,,"));
//		cxtOpItems.add(call("ne.proc.secu.alluser"));
//--------		
		cxtOpItems.add(commit());		
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//测试 Route
	public byte[] operForRoute(int session, int seqid) {
		AnalysisRoute ar = new AnalysisRoute();
		return ar.getRoute(session, seqid);
		
	}
	//测试 Ring 环.
	public byte[] operForRing(int session, int seqid) {
		AnalysisRING ar = new AnalysisRING();
		//查询 
//		return ar.selectRing(null, session, seqid);		
		//更新
		RingObject rin = new RingObject();
		rin.setName("2");
		rin.setEnaps("false");
		rin.setWtrtime("120");
		rin.setHoldofftime("1");
		rin.setNodeid("5");
		rin.setWestnbrid("2");
		rin.setEastnbrid("3");
		rin.setWestport("ge.1.2");//这2个字段不能下发，下发需要关联cmd参数
		rin.setEastport("ge.1.1");//这2个字段不能下发，下发需要关联cmd参数
//		rin.setApscmd("forceswitch::east");
		rin.setStat("2"); //
		rin.setWestlastrxpdu(null); //0x00000000
		rin.setEastlastrxpdu(null); //0x02020000
		rin.setWestlasttxpdu(null); //0x01010000
		rin.setEastlasttxpdu(null); //0x01010000
		
//		return ar.updateRing(rin, session, seqid);
//		return ar.deleteRing(rin, session, seqid);
		return ar.createRing(rin, session, seqid);
	}
	public byte[] openForQos(int session, int seqid) {
		AnalysisQOS aqos = new AnalysisQOS();
		AcQosObject qosObject = new AcQosObject();
		qosObject.setName("l266");
		qosObject.setType("ETHAC_L2");
		qosObject.setCbs("1");
		qosObject.setCir("0");
		qosObject.setCos("af1");
		qosObject.setEbs("-1");
		qosObject.setEir("0");
		qosObject.setSeq("0");
		
		
		 
		return aqos.createACQOSByte(qosObject, session, seqid);
	}
	public byte[] openForClock(int session, int seqid) {
		AnalysisClock clock = new AnalysisClock();
		return clock.selectClock(null, session, seqid);
	}
	
}
