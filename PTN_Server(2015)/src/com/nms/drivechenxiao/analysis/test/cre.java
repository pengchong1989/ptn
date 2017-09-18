package com.nms.drivechenxiao.analysis.test;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;

public class cre extends CxtOpLump{
	
	public byte[] createElinePw( int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "name"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "tom"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "age"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "20"));
		CxtATTable cxtATTable = getCxtATTable(2, cxtAtomTypes);

		cxtOpItems.add(cd("ne/interfaces/"));
		cxtOpItems.add(create("service", cxtATTable));
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
		
	}
}
