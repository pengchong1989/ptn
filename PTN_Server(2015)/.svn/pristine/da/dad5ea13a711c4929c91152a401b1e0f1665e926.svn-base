package com.nms.drivechenxiao.analysis.ne;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.ospf.OSPFObject;

public class AnalysisOSPF extends CxtOpLump {
	/**
	 * 创建OSPF
	 * 
	 * @param ptnNeObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createOSPF(OSPFObject ospfObject, int session, int seqid) {
		return OSPF("create", ospfObject, session, seqid);
	}

	/**
	 * 修改OSPF
	 * 
	 * @param ospfObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateOSPF(OSPFObject ospfObject, int session, int seqid) {
		return OSPF("update", ospfObject, session, seqid);
	}

	/**
	 * 删除OSPF
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteOSPF(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols"));
		cxtOpItems.add(delete("ospf"));
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 查询OSPF
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] selectOSPF(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "abr"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "rt_id_area"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "compatiblerfc1583"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "defmetric"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "distance"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "refbandwidth"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "refresh_time"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "spf_delay"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "spf_holdtime"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "spf_maxholdtime"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "version"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/ospf"));
		cxtOpItems.add(get(cxtATTable, 1));
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 解析查询OSPF
	 * 
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public OSPFObject analysisOSPF(byte[] command, CXNEObject CXNEObject) {
		List<OSPFObject> ospfObjectList = new ArrayList<OSPFObject>();
		int start = 49;
		 byte[] tt = command;
//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 1, 17, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 11, 32, 0, 0, 0, 3, 97, 98, 114, 19, 0, 0, 0, 3, 32, 0, 0, 0, 17, 99, 111, 109, 112, 97, 116, 105, 98, 108, 101, 114, 102, 99, 49, 53, 56, 51, 1, 0, 32, 0, 0, 0, 9, 100, 101, 102, 109, 101, 116, 114, 105, 99, 19, -1, -1, -1, -1, 32, 0, 0, 0, 8, 100, 105, 115, 116, 97, 110, 99, 101, 19, 0, 0, 0, 110, 32, 0, 0, 0, 12, 114, 101, 102, 98, 97, 110, 100, 119, 105, 100, 116, 104, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 114, 101, 102, 114, 101, 115, 104, 95, 116, 105, 109, 101, 19, 0, 0, 0, 10, 32, 0, 0, 0, 10, 114, 116, 95, 105, 100, 95, 97, 114, 101, 97, 19, 0, 0, 0, 0, 32, 0, 0, 0, 9, 115, 112, 102,
//				95, 100, 101, 108, 97, 121, 19, 0, 0, 0, -56, 32, 0, 0, 0, 12, 115, 112, 102, 95, 104, 111, 108, 100, 116, 105, 109, 101, 19, 0, 0, 3, -24, 32, 0, 0, 0, 15, 115, 112, 102, 95, 109, 97, 120, 104, 111, 108, 100, 116, 105, 109, 101, 19, 0, 0, 39, 16, 32, 0, 0, 0, 7, 118, 101, 114, 115, 105, 111, 110, 32, 0, 0, 0, 5, 48, 46, 48, 46, 49, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		ospfObjectList = super.analysisTabble("ospf", t);
		return ospfObjectList.get(0);
	}

	private byte[] OSPF(String type, OSPFObject ospfObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "rt_id_area"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ospfObject.getRt_id_area()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "abr"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ospfObject.getAbr()));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "defmetric"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,
		// ospfObject.getDefmetric()));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "version"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,
		// ospfObject.getVersion()));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,
		// "compatiblerfc1583"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL,
		// ospfObject.getCompatiblerfc1583()));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,
		// "refbandwidth"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,
		// ospfObject.getRefbandwidth()));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "distance"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,
		// ospfObject.getDistance()));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,
		// "spf_holdtime"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,
		// ospfObject.getSpf_holdtime()));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,
		// "spf_maxholdtime"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,
		// ospfObject.getSpf_maxholdtime()));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "spf_delay"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,
		// ospfObject.getSpf_delay()));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,
		// "refresh_time"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,
		// ospfObject.getRefresh_time()));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols"));
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

}
