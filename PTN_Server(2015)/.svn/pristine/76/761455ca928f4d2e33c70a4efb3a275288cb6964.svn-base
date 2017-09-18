package com.nms.drivechenxiao.analysis.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protsdh.SdhPortObject;

public class AnalysisSDHPort extends CxtOpLump {

	/**
	 * 设置SDH端口
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setSDHPort(SdhPortObject sdhPortObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/sdh/" + sdhPortObject.getName()));
		cxtOpItems.add(set("admin", sdhPortObject.getAdmin()));
		
		String sfpexS = getSfpexptype(sdhPortObject.getSfpexptype());
		if(haveIt(sfpexS))cxtOpItems.add(set("sfpexptype",sfpexS));
//		cxtOpItems.add(set("sfpexptype","unknown"));
		cxtOpItems.add(commit());
		
//System.out.println("AnalysisSDHPort.setSDHPort .. sdh="+sdhPortObject.toString());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//生成查询命令
	public byte[] selectAllSdhPort(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/sdh"));
		cxtOpItems.add(get(s, 2));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 解析查询SDH端口
	 * 
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public List<SdhPortObject> analysisSDHPort(byte[] command, CXNEObject CXNEObject) {
		List<SdhPortObject> sdhPortObject = new ArrayList<SdhPortObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		sdhPortObject = super.analysisTabble("sdhport", t);
		return sdhPortObject;
	}
	public String getSfpexptype(String type){
		if("0".equals(type))return "unknown";
		if("1".equals(type))return "Empty";
		if("2".equals(type))return "RateMatch";
		if("3".equals(type))return "S_1_1";
		if("4".equals(type))return "L_1_1";
		if("5".equals(type))return "L_1_2";
		return "";
	}
	public boolean haveIt(String s){
		if("".equals(s)||s.equals(null))return false;
		else return true;
	}
}
