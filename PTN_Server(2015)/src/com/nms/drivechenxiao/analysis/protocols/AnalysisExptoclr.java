package com.nms.drivechenxiao.analysis.protocols;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.llsp.ExptoclrObject;

/**端口exp业务映射 里面的 llsp里的 输入
 * **/
public class AnalysisExptoclr extends CxtOpLump{
	public List<ExptoclrObject> analysisExptoclr(byte[] command, CXNEObject CXNEObject){
		List<ExptoclrObject> list =new ArrayList<ExptoclrObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		list = super.analysisTabble("Exptoclr", t);		
		
		return list;
	}
	
	//生成查询命令
	                          
	public byte[] selectExptoclr( int session, int seqid){
		String pathr ="ne/protocols/mpls/llsp/exptoclr" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] updateExptoclr(ExptoclrObject exptoclr, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/mpls/llsp/exptoclr" ));
		List<CxtAtomType> listype = new ArrayList<CxtAtomType>();
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp0"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp0() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp1"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp1() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp2"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp2() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp3"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp3() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp4"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp4() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp5"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp5() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp6"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp6() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp7"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp7() ));
		
		  CxtATTable tabtype = getCxtATTable(listype.size() / 2, listype);
		
		  cxtOpItems.add(mset(exptoclr.getName(), tabtype));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] createExptoclr(ExptoclrObject exptoclr, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/mpls/llsp/exptoclr" ));
		List<CxtAtomType> listype = new ArrayList<CxtAtomType>();
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp0"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp0() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp1"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp1() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp2"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp2() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp3"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp3() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp4"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp4() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp5"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp5() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp6"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp6() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp7"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptoclr.getExp7() ));
		
		  CxtATTable tabtype = getCxtATTable(listype.size() / 2, listype);
		
		  cxtOpItems.add(create(exptoclr.getName(), tabtype));
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}	
	//生成查询命令
    
	public byte[] deleteExptoclr(ExptoclrObject exptoclr, int session, int seqid){
		String pathr ="ne/protocols/mpls/llsp/exptoclr" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(delete(exptoclr.getName())) ;
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
