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
import com.nms.drivechenxiao.service.bean.ptnne.PtnNeObject;

public class AnalysisNE extends CxtOpLump {

	/**
	 * 初始化网元 
	 * 
	 * **/
	public byte[] clearNE(PtnNeObject ptnNeObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(call("ne.dropdb","1"));
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	/**
	 * 设置网元属性
	 * 
	 * @param ptnNeObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setNE(PtnNeObject ptnNeObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne"));
		cxtOpItems.add(set("id", ptnNeObject.getId()));
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 查询网元属性
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] getNE(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ver"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "id"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "oui"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "icccode"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "tpoamchntype"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "desc"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "timezone"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "topalmlvl"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne"));
		cxtOpItems.add(get(cxtATTable, 1));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);

		return command;
	}

	/**
	 * 解析查询ne
	 * 
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public PtnNeObject analysisSelectNe(byte[] command, CXNEObject CXNEObject) {
//		 byte[] tt = new
//		 byte[]{114,109,116,1,0,0,0,-46,34,0,0,0,0,0,0,0,1,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,48,0,0,0,9,32,0,0,0,4,100,101,115,99,32,0,0,0,6,112,116,110,32,78,69,32,0,0,0,7,105,99,99,99,111,100,101,32,0,0,0,3,112,116,110,32,0,0,0,2,105,100,19,20,0,0,-53,32,0,0,0,3,111,117,105,19,0,72,-33,28,32,0,0,0,8,116,105,109,101,122,111,110,101,19,0,0,0,8,32,0,0,0,9,116,111,112,97,108,109,108,118,108,19,0,0,0,5,32,0,0,0,12,116,112,111,97,109,99,104,110,116,121,112,101,19,0,0,127,-6,32,0,0,0,4,116,121,112,101,32,0,0,0,6,99,120,116,50,48,98,32,0,0,0,3,118,101,114,32,0,0,0,6,118,50,46,48,46,52,0,};
//System.out.println("line88 -- "+print16String(command));		
		byte[] tt = command;
		int start = 49;
		int count = tt.length - 50;
		PtnNeObject ptnNeObject = new PtnNeObject();
		List<PtnNeObject> ptnNeObjectList = new ArrayList<PtnNeObject>();
		byte[] t = new byte[count];
		System.arraycopy(tt, start, t, 0, count);
		ptnNeObjectList = super.analysisTabble("ne", t);
		ptnNeObject = ptnNeObjectList.get(0);
//System.out.println("AnalysisSelectNe . line 97 : "+ptnNeObject.toString());		
		return ptnNeObject;
	}

	/**
	 * 查询网元时间
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] getGetAll(int session, int seqid) {
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 1));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 1));
		CxtATTable cxtATTable = getCxtATTable(1, cxtAtomTypes);

		CxtAtomType cxtAtomType = new CxtAtomType();
		cxtAtomType.setCxtATTable(cxtATTable);
		cxtAtomType.setType("AT_TABLE");

		byte[] command = new byte[0];
		byte[] cxtString = getCxtString("ne.getdatetime");
		byte[] cxtTable = getCxtAtomType(cxtAtomType);
		command = CoderUtils.arraycopy(command, cxtString);
		command = CoderUtils.arraycopy(command, cxtTable);

		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_ACALL, command, session, seqid);
		return command;
	}

	/**
	 * 解析查询网元时间
	 * 
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public String analysisSelectNeTime(byte[] command, CXNEObject CXNEObject) {
		byte[] timeByte = new byte[] { command[30], command[31], command[32], command[33], command[34], command[35], command[36], command[37], command[38] };
		String timeString = CoderUtils.byteToTime("1a", timeByte);
		return timeString;
	}
}
