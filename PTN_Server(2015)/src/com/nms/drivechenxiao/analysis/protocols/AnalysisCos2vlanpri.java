package com.nms.drivechenxiao.analysis.protocols;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.Cos2vlanpriObject;

public class AnalysisCos2vlanpri extends CxtOpLump{
	public List<Cos2vlanpriObject> analysisCos2vlanpri(byte[] command, CXNEObject CXNEObject){
		List<Cos2vlanpriObject> msplist =new ArrayList<Cos2vlanpriObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		msplist = super.analysisTabble("cos2vlanpri", t);		
		
		return msplist;
	}
	
	//生成查询命令
	                          
	public byte[] selectCos2vlanpri( int session, int seqid){
		String pathr ="ne/cos2vlanpri" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] updateCos2vlanpri(Cos2vlanpriObject cc, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/cos2vlanpri" ));
		List<CxtAtomType> listype = new ArrayList<CxtAtomType>();
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "be"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getBe() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af1"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getAf1() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af2"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getAf2() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af3"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getAf3() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af4"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getAf4() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ef"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getEf() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cs6"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getCs6() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cs7"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getCs7() ));
		
		  CxtATTable tabtype = getCxtATTable(listype.size() / 2, listype);
		
		  cxtOpItems.add(mset(cc.getName(), tabtype));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] createCos2vlanpri(Cos2vlanpriObject cc, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/cos2vlanpri" ));
		List<CxtAtomType> listype = new ArrayList<CxtAtomType>();
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "be"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getBe() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af1"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getAf1() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af2"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getAf2() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af3"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getAf3() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af4"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getAf4() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ef"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getEf() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cs6"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getCs6() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cs7"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, cc.getCs7() ));
		
		  CxtATTable tabtype = getCxtATTable(listype.size() / 2, listype);
		
		  cxtOpItems.add(create(cc.getName(), tabtype));
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}	
	//生成查询命令
    
	public byte[] deleteCos2vlanpri(Cos2vlanpriObject cc, int session, int seqid){
		String pathr ="ne/cos2vlanpri" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(delete(cc.getName())) ;
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//类型转换
	private boolean have(String s){
		if(null == s ||s.equals(""))return false;
		else{ return true;}
	}
}
