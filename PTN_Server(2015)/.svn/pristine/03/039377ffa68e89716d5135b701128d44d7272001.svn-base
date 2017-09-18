package com.nms.drivechenxiao.analysis.ne;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.mcn.MCNObject;

public class AnalysisMCN extends CxtOpLump {
	/**
	 * 设置MCN
	 * 
	 * @param mcnObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setMCN(MCNObject mcnObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "mtu"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, mcnObject.getMtu()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ip"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, mcnObject.getIp()));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/mcn"));
		cxtOpItems.add(mset("1", cxtATTable));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//生成查询命令
	public byte[] selectMCN(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/mcn"));
		cxtOpItems.add(get("1", 1));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//解析
	public List<MCNObject> analysisMCN(byte[] command, CXNEObject CXNEObject) {
		List<MCNObject> mcnObject = new ArrayList<MCNObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 0, -104, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 7, 32, 0, 0, 0, 3, 97, 114, 112, 48, 0, 0, 0, 0, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 0, 0, 4, 1, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 0, 0, 4, 1, 32, 0, 0, 0, 2, 105, 112, 26, 66, 64, 5, 0, -128, -1, 0, 0, 32, 0, 0, 0, 3, 109, 116, 117, 19, 0, 0, 5, -36, 32, 0, 0, 0, 3, 114, 101, 102, 19, 0, 0, 0, 0, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		mcnObject = super.analysisTabble("mcn", t);
		return mcnObject;
	}
}
