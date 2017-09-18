package com.nms.drivechenxiao.analysis.clock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.PtpObject;
//时间管理 /网元ptp配置
public class AnalysisPtp extends CxtOpLump{
	//解析
	public List<PtpObject> analysisPtp(byte[] command, CXNEObject CXNEObject){
		List<PtpObject> ptplist =new ArrayList<PtpObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		ptplist = super.analysisTabble("Ptp", t);
//for(ClockObject r : clocklist){
//	System.out.println(" -- ring = "+r.toString());
//}		
		return ptplist;
	}
	
	
	//生成查询命令                          
	public byte[] selectPtp(PtpObject ptp, int session, int seqid){
		String pathr ="ne/protocols/ptp" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//生成更新 update命令
	public byte[] updatePtp(PtpObject ptp, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols"));

//		cxtOpItems.add(set("bmcenable", ptp.getBmcenable()) );
//		cxtOpItems.add(set("clkid", ptp.getClkid()));
//		cxtOpItems.add(set("clockaccuracy", ptp.getClockaccuracy() ));
//		cxtOpItems.add(set("clockclass", ptp.getClockclass()));
//		cxtOpItems.add(set("priority1", ptp.getPriority1()));
//		cxtOpItems.add(set("priority2", ptp.getPriority2() ));		
////		cxtOpItems.add(set("oui", "4775756" ));
//		cxtOpItems.add(set("domain", ptp.getDomain() ));
//		cxtOpItems.add(set("tcdomain1", ptp.getTcdomain1() ));
//		cxtOpItems.add(set("tcdomain2", ptp.getTcdomain2()  ));
//		cxtOpItems.add(set("tcdomain2enable", ptp.getTcdomain2enable()));
//		cxtOpItems.add(set("tcdomain3", ptp.getTcdomain3()  ));
//		cxtOpItems.add(set("tcdomain3enable", ptp.getTcdomain3enable()));
//		cxtOpItems.add(set("tcdomain4", ptp.getTcdomain4()  ));
//		cxtOpItems.add(set("tcdomain4enable", ptp.getTcdomain4enable()));
//		cxtOpItems.add(set("tcdelaymechanism", ptp.getTcdelaymechanism() ));
//		cxtOpItems.add(set("slaveonly", ptp.getSlaveonly() ));
//		cxtOpItems.add(set("variance", ptp.getVariance() ));
//		cxtOpItems.add(set("todtxtype", ptp.getTodtxtype() ));
//		
//		cxtOpItems.add(set("two_step", "false" ));
//		cxtOpItems.add(set("timeissync", "false" ));
		//-----
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "clkid"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ptp.getClkid() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "bmcenable"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ptp.getBmcenable() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "priority1"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, ptp.getPriority1() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "clockclass"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, ptp.getClockclass() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "clockaccuracy"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, ptp.getClockaccuracy() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "priority2"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, ptp.getPriority2() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "domain"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, ptp.getDomain() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "tcdomain1"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, ptp.getTcdomain1() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "tcdomain2"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, ptp.getTcdomain2() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "tcdomain2enable"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, ptp.getTcdomain2enable() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "tcdomain3"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, ptp.getTcdomain3() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "tcdomain3enable"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ptp.getTcdomain3enable() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "tcdomain4"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, ptp.getTcdomain4() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "tcdomain4enable"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ptp.getTcdomain4enable() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "tcdelaymechanism"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, ptp.getTcdelaymechanism() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "slaveonly"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ptp.getSlaveonly() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "todtxtype"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, ptp.getTodtxtype() ));
		
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "variance"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, ptp.getTodtxtype() ));
		
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "two_step"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, "0" ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "timeissync"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, "0" ));
		
		
		
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
		//-----
//		cxtOpItems.add(cd("ne"));
//		cxtOpItems.add(set("oui", "4775756" ));
		cxtOpItems.add(mset("ptp", cxtATTable));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	private boolean have(String s){
		if(null == s ||s.equals(""))return false;
		else{ return true;}
	}
}
