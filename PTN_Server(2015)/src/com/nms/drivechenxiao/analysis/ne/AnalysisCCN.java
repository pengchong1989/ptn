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

public class AnalysisCCN extends CxtOpLump {
	/**
	 * 创建CCN
	 * 
	 * @param ccnObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createCCN(CCNObject ccnObject, int session, int seqid) {
		return CCN("create", ccnObject, session, seqid);
	}

	/**
	 * 修改CCN
	 * 
	 * @param ccnObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateCCN(CCNObject ccnObject, int session, int seqid) {
		return CCN("update", ccnObject, session, seqid);
	}

	/**
	 * 删除CCN
	 * 
	 * @param ccnObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteCCN(CCNObject ccnObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/ccn"));
		cxtOpItems.add(delete(ccnObject.getName()));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//生成查询命令
	public byte[] selectCCN(CCNObject ccnObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/ccn"));
		cxtOpItems.add(get(ccnObject.getName(), 1));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//生成查询命令
	public byte[] selectAllCCN(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/ccn"));
		cxtOpItems.add(get(s, 2));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//解析
	public List<CCNObject> analysisCCN(byte[] command, CXNEObject CXNEObject) {
		List<CCNObject> ccnObject = new ArrayList<CCNObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 1, 17, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 1, 32, 0, 0, 0, 1, 49, 48, 0, 0, 0, 12, 32, 0, 0, 0, 6, 97, 99, 116, 105, 118, 101, 19, 8, 18, 1, 3, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 8, 100, 97, 116, 97, 108, 105, 110, 107, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 0, 0, 2, 1, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 0, 0, 2, 1, 32, 0, 0, 0, 2, 105, 112, 26, 66, 56, -56, 1, 2, 2, 0, 0, 32, 0, 0, 0, 3, 109, 116, 117, 19, 0, 0, 5, 120, 32, 0, 0, 0,
		//				4, 111, 112, 101, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 115, 112, 102, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 4, 112, 101, 101, 114, 19, 20, 0, 0, 35, 32, 0, 0, 0, 3, 114, 101, 102, 19, 0, 0, 0, 0, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		ccnObject = super.analysisTabble("ccn", t);
		return ccnObject;
	}

	private byte[] CCN(String type, CCNObject ccnObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ccnObject.getAdmin()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "mtu"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ccnObject.getMtu()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ip"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ccnObject.getIp()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "peer"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ccnObject.getPeer()));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/ccn"));
		if ("create".equals(type)) {
			cxtOpItems.add(create(ccnObject.getName(), cxtATTable));
		} else if ("update".equals(type)) {
			cxtOpItems.add(mset(ccnObject.getName(), cxtATTable));
		}
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
}
