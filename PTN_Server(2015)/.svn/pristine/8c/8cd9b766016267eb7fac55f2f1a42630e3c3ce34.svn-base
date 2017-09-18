package com.nms.drivechenxiao.analysis;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;

public class AnalysisHookNotify extends CxtOpLump {

	public byte[] configHookNotify(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 1));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "almchg"));
		 cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 2));
		 cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "prtswitch"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 3));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "cfgdbchg"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 4));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "seculog"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 5));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ethlinkoam"));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 6));
		// cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "oamlpb"));
		CxtAtomType op2Attr1 = getCxtAtomType(CxtAtomType.AT_TABLE, getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes));
		CxtAtomType op2Attr2 = getCxtAtomType(CxtAtomType.AT_NUM_32, 1);
		cxtOpItems.add(getCxtOpItem(13, op2Attr1, op2Attr2));

		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS,command, session, seqid);
		// CoreOper.print16String(command);
		return command;
	}

}
