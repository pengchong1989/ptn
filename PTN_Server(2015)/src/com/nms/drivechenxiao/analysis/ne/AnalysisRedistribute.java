package com.nms.drivechenxiao.analysis.ne;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.redistribute.RedistributeObject;

public class AnalysisRedistribute extends CxtOpLump {
	/**
	 * 重分发配置
	 * 
	 * @param redistribute
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setRedistribute(RedistributeObject redistribute, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes2 = new ArrayList<CxtAtomType>();
		cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, "enable"));
		cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_BOOL, redistribute.getEnable()));
		CxtATTable cxtATTable2 = getCxtATTable(cxtAtomTypes2.size() / 2, cxtAtomTypes2);

		List<CxtAtomType> cxtAtomTypes1 = new ArrayList<CxtAtomType>();
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "metrictype"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, redistribute.getMetrictype()));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "metric"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, redistribute.getMetric()));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "distribute_list"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTable2));
		CxtATTable cxtATTable1 = getCxtATTable(cxtAtomTypes1.size() / 2, cxtAtomTypes1);

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		if ("mcn".equals(redistribute.getRedistribute_type())) {
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "mcn"));
		} else if ("default".equals(redistribute.getRedistribute_type())) {
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "default"));
		} else if ("static".equals(redistribute.getRedistribute_type())) {
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "static"));
		}
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

	/**
	 * 删除重分发
	 * @param redistribute
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteRedistribute(RedistributeObject redistribute, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/ospf/redistribute"));
		cxtOpItems.add(delete(redistribute.getRedistribute_type()));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] selectRedistribute(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/ospf/redistribute"));
		cxtOpItems.add(get(s, 3));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public List<RedistributeObject> analysisRedistribute(byte[] command, CXNEObject CXNEObject) {
		List<RedistributeObject> redistributeObjectList = new ArrayList<RedistributeObject>();
		int start = 49;
		//		byte[] tt = command;
		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 1, 41, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 3, 32, 0, 0, 0, 7, 100, 101, 102, 97, 117, 108, 116, 48, 0, 0, 0, 3, 32, 0, 0, 0, 6, 109, 101, 116, 114, 105, 99, 19, -1, -1, -1, -1, 32, 0, 0, 0, 10, 109, 101, 116, 114, 105, 99, 116, 121, 112, 101, 19, 0, 0, 0, 2, 32, 0, 0, 0, 4, 116, 121, 112, 101, 19, 0, 0, 0, 1, 32, 0, 0, 0, 3, 109, 99, 110, 48, 0, 0, 0, 3, 32, 0, 0, 0, 15, 100, 105, 115, 116, 114, 105, 98, 117, 116, 101, 95, 108, 105, 115, 116, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 6, 109, 101, 116, 114, 105, 99, 19, -1, -1, -1, -1, 32, 0, 0, 0, 10, 109, 101, 116, 114, 105, 99, 116, 121,
				112, 101, 19, 0, 0, 0, 1, 32, 0, 0, 0, 6, 115, 116, 97, 116, 105, 99, 48, 0, 0, 0, 3, 32, 0, 0, 0, 15, 100, 105, 115, 116, 114, 105, 98, 117, 116, 101, 95, 108, 105, 115, 116, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 6, 109, 101, 116, 114, 105, 99, 19, -1, -1, -1, -1, 32, 0, 0, 0, 10, 109, 101, 116, 114, 105, 99, 116, 121, 112, 101, 19, 0, 0, 0, 2, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(command, start, t, 0, command.length - start);
		redistributeObjectList = super.analysisTabble("redistribute", t);
		return redistributeObjectList;
	}
}
