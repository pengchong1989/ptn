package com.nms.drivechenxiao.analysis.ne;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.ccn.CCNObject;
import com.nms.drivechenxiao.service.bean.ospf.interfaces.OSPFinterfacesObject;

public class AnalysisOSPFInterfaces extends CxtOpLump {

	/**
	 * 创建ccnOSPF
	 * 
	 * @param ccnObject
	 * @param ospfinterfacesObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createOSPFInterfacesCCN(CCNObject ccnObject, OSPFinterfacesObject ospfinterfacesObject, int session, int seqid) {
		return OSPFInterfaces("ccn", "create", ccnObject, ospfinterfacesObject, session, seqid);
	}

	/**
	 * 创建mcnOSPF
	 * 
	 * @param mcnObject
	 * @param ospfinterfacesObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createOSPFInterfacesMCN(OSPFinterfacesObject ospfinterfacesObject, int session, int seqid) {
		return OSPFInterfaces("mcn", "create", null, ospfinterfacesObject, session, seqid);
	}

	/**
	 * 修改CCN
	 * 
	 * @param ccnObject
	 * @param ospfinterfacesObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateOSPFInterfacesCCN(CCNObject ccnObject, OSPFinterfacesObject ospfinterfacesObject, int session, int seqid) {
		return OSPFInterfaces("ccn", "update", ccnObject, ospfinterfacesObject, session, seqid);
	}

	/**
	 * 修改MCN
	 * 
	 * @param ospfinterfacesObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateOSPFInterfacesMCN(OSPFinterfacesObject ospfinterfacesObject, int session, int seqid) {
		return OSPFInterfaces("mcn", "update", null, ospfinterfacesObject, session, seqid);
	}

	/**
	 * 删除CCN
	 * 
	 * @param ccnObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteOSPFInterfacesCCN(CCNObject ccnObject, int session, int seqid) {
		return deleteOSPFInterfaces("ccn", ccnObject, session, seqid);
	}

	/**
	 * 删除MCN
	 * 
	 * @param ospfinterfacesObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteOSPFInterfacesMCN(int session, int seqid) {
		return deleteOSPFInterfaces("mcn", null, session, seqid);
	}

	public byte[] selectOSPFInterfacesCCN(CCNObject ccnObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/ccn/" + ccnObject.getName()));
		cxtOpItems.add(get("ospf", 1));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] selectOSPFInterfacesAllCCN(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/ccn"));
		cxtOpItems.add(get(s, 2));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] selectOSPFInterfacesMCN(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/mcn/1"));
		cxtOpItems.add(get("ospf", 1));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public List<OSPFinterfacesObject> analysisOSPFInterfaces(byte[] command, CXNEObject CXNEObject) {
		List<OSPFinterfacesObject> OSPFinterfacesObject = new ArrayList<OSPFinterfacesObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 0, -36, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 9, 32, 0, 0, 0, 4, 97, 114, 101, 97, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 99, 111, 115, 116, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 100, 101, 97, 100, 95, 105, 110, 116, 101, 114, 118, 97, 108, 19, 0, 0, 0, 40, 32, 0, 0, 0, 14, 104, 101, 108, 108, 111, 95, 105, 110, 116, 101, 114, 118, 97, 108, 19, 0, 0, 0, 10, 32, 0, 0, 0, 7, 112, 97, 115, 115, 105, 118, 101, 1, 0, 32, 0, 0, 0, 7, 112, 114, 105, 111, 114, 105, 121, 19, 0, 0, 0, 100, 32, 0, 0, 0, 19, 114, 101, 116, 114, 97, 110, 115, 109, 105, 116, 95, 105, 110, 116, 101, 114, 118, 97, 108, 19, 0, 0, 0, 5, 32, 0, 0, 0, 14, 116, 114, 97, 110, 115, 109,
		//				105, 116, 95, 100, 101, 108, 97, 121, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 116, 121, 112, 101, 19, 0, 0, 0, 1, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		OSPFinterfacesObject = super.analysisTabble("ospfinterfaces", t);
		return OSPFinterfacesObject;
	}

	private byte[] OSPFInterfaces(String operate, String type, CCNObject ccnObject, OSPFinterfacesObject ospfinterfacesObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "hello_interval"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ospfinterfacesObject.getHello_interval()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "dead_interval"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ospfinterfacesObject.getDead_interval()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "retransmit_interval"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ospfinterfacesObject.getRetransmit_interval()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "transmit_delay"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ospfinterfacesObject.getTransmit_delay()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "area"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ospfinterfacesObject.getArea()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ospfinterfacesObject.getType()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "passive"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ospfinterfacesObject.getPassive()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "cost"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ospfinterfacesObject.getCost()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "prioriy"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ospfinterfacesObject.getPrioriy()));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		if ("ccn".equals(operate)) {
			cxtOpItems.add(cd("ne/interfaces/ccn/" + ccnObject.getName()));
		} else if ("mcn".equals(operate)) {
			cxtOpItems.add(cd("ne/interfaces/mcn/1"));
		}
		if ("create".equals(type)) {
			cxtOpItems.add(create("ospf", cxtATTable));
		} else if ("update".equals(type)) {
			cxtOpItems.add(mset("ospf", cxtATTable));
		}
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	private byte[] deleteOSPFInterfaces(String type, CCNObject ccnObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		if ("ccn".equals(type)) {
			cxtOpItems.add(cd("ne/interfaces/ccn/" + ccnObject.getName()));
		} else if ("mcn".equals(type)) {
			cxtOpItems.add(cd("ne/interfaces/mcn/1"));
		}
		cxtOpItems.add(delete("ospf"));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

}
