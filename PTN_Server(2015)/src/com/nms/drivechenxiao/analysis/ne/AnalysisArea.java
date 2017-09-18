package com.nms.drivechenxiao.analysis.ne;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.ospf.OSPFAREAObject;

public class AnalysisArea extends CxtOpLump {
	/**
	 * 创建AREA
	 * @param OSPFAREAObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createAREA(OSPFAREAObject OSPFAREAObject, int session, int seqid) {
		return AREA("create", OSPFAREAObject, session, seqid);
	}

	/**
	 * 修改AREA
	 * @param OSPFAREAObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateAREA(OSPFAREAObject OSPFAREAObject, int session, int seqid) {
		return AREA("update", OSPFAREAObject, session, seqid);
	}

	/**
	 * 删除AREA
	 * @param OSPFAREAObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteAREA(OSPFAREAObject OSPFAREAObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/ospf"));
		cxtOpItems.add(delete("area." + OSPFAREAObject.getNeId()));
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//生成查询命令
	public byte[] selectArea(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		List<CxtAtomType> cxtAtomTypes=new ArrayList<CxtAtomType>();
		
		cxtOpItems.add(begin(3));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size()/2, cxtAtomTypes);
		cxtOpItems.add(cd("ne/protocols/ospf"));
		cxtOpItems.add(get(s, 3));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//解析
	public List<OSPFAREAObject> analysisArea(byte[] command, CXNEObject CXNEObject) {
		List<OSPFAREAObject> OSPFAREAObject = new ArrayList<OSPFAREAObject>();
		int count=56;
		int start = 49;
		//		byte[] tt = command;
		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 1, -108, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 13, 32, 0, 0, 0, 3, 97, 98, 114, 19, 0, 0, 0, 3, 32, 0, 0, 0, 6, 97, 114, 101, 97, 46, 48, 48, 0, 0, 0, 2, 32, 0, 0, 0, 10, 97, 114, 101, 97, 95, 114, 97, 110, 103, 101, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 2, 118, 108, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 17, 99, 111, 109, 112, 97, 116, 105, 98, 108, 101, 114, 102, 99, 49, 53, 56, 51, 1, 0, 32, 0, 0, 0, 9, 100, 101, 102, 109, 101, 116, 114, 105, 99, 19, -1, -1, -1, -1, 32, 0, 0, 0, 8, 100, 105, 115, 116, 97, 110, 99, 101, 19, 0, 0, 0, 110, 32,
				0, 0, 0, 12, 114, 101, 100, 105, 115, 116, 114, 105, 98, 117, 116, 101, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 109, 99, 110, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 12, 114, 101, 102, 98, 97, 110, 100, 119, 105, 100, 116, 104, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 114, 101, 102, 114, 101, 115, 104, 95, 116, 105, 109, 101, 19, 0, 0, 0, 10, 32, 0, 0, 0, 10, 114, 116, 95, 105, 100, 95, 97, 114, 101, 97, 19, 0, 0, 0, 0, 32, 0, 0, 0, 9, 115, 112, 102, 95, 100, 101, 108, 97, 121, 19, 0, 0, 0, -56, 32, 0, 0, 0, 12, 115, 112, 102, 95, 104, 111, 108, 100, 116, 105, 109, 101, 19, 0, 0, 3, -24, 32, 0, 0, 0, 15, 115, 112, 102, 95, 109, 97, 120, 104, 111, 108, 100, 116, 105, 109, 101, 19, 0, 0, 39, 16, 32, 0, 0, 0, 7, 118, 101, 114, 115, 105, 111,
				110, 32, 0, 0, 0, 5, 48, 46, 48, 46, 49, 0, };
		byte[] t = new byte[command.length - start];
		System.arraycopy(command, start, t, 0, command.length - start);
		OSPFAREAObject = super.analysisTabble("ospfarea", t);
		return OSPFAREAObject;
	}

	private byte[] AREA(String type, OSPFAREAObject OSPFAREAObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		CxtATTable cxtATTableType=null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/ospf"));
		if(getType(OSPFAREAObject.getType())!=0){
			List<CxtAtomType> cxtATomTypes=new ArrayList<CxtAtomType>();
			cxtATomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, CoderUtils.ascii2Char(95)));
			cxtATomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, OSPFAREAObject.getType()));
			cxtATomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
			cxtATomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, getType(OSPFAREAObject.getType())));
			cxtATomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "defmetric"));
			cxtATomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, OSPFAREAObject.getDefmetric()));
			cxtATomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "nosummaries"));
			cxtATomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, OSPFAREAObject.getNosummaries()));
			//CxtATTable cxtATTable = getCxtATTable(cxtATomTypes.size()/2, cxtATomTypes);
			
			List<CxtAtomType> cxttype=new ArrayList<CxtAtomType>();
			cxttype.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
			cxttype.add(getCxtAtomType(CxtAtomType.AT_TABLE, getCxtATTable(cxtATomTypes.size()/2,cxtATomTypes)));
			 cxtATTableType = getCxtATTable(cxttype.size()/2, cxttype);
		}	
		
		if ("create".equals(type)) {
			cxtOpItems.add(create("area." + OSPFAREAObject.getNeId(), cxtATTableType));
		} else if ("update".equals(type)) {
			cxtOpItems.add(mset("area." + OSPFAREAObject.getNeId(), cxtATTableType));
		}
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public int getType(String tp){
		if(tp.equals("nssa")||tp.equals("MSSA")){
			return 2;
		}else if(tp.equals("stub")||tp.equals("STUB")){
			return 1;
		}else if(tp.equals("none")||tp.equals("NONE")){
			return 0;
		}else{
			return 2;
		}
	}
}
