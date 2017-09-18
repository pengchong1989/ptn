package com.nms.drivechenxiao.analysis.protocols;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.llsp.ClrtoexpObject;

/**端口exp业务映射 里面的 llsp里的 输出
 * **/
public class AnalysisClrtoexp extends CxtOpLump{
	public List<ClrtoexpObject> analysisClrtoexp(byte[] command, CXNEObject CXNEObject){
		List<ClrtoexpObject> msplist =new ArrayList<ClrtoexpObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		msplist = super.analysisTabble("Clrtoexp", t);		
		
		return msplist;
	}
	
	//生成查询命令
	                          
	public byte[] selectClrtoexp( int session, int seqid){
		String pathr ="ne/protocols/mpls/llsp/clrtoexp" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] updateClrtoexp(ClrtoexpObject clrtoexp, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/mpls/llsp/clrtoexp" ));
		List<CxtAtomType> listype = new ArrayList<CxtAtomType>();
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "yellow"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, clrtoexp.getYellow() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "green"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, clrtoexp.getGreen() ));

		  CxtATTable tabtype = getCxtATTable(listype.size() / 2, listype);
		
		  cxtOpItems.add(mset(clrtoexp.getName(), tabtype));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] createClrtoexp(ClrtoexpObject clrtoexp, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/mpls/llsp/clrtoexp" ));
		List<CxtAtomType> listype = new ArrayList<CxtAtomType>();
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "yellow"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, clrtoexp.getYellow() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "green"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, clrtoexp.getGreen() ));

		  CxtATTable tabtype = getCxtATTable(listype.size() / 2, listype);
		
		  cxtOpItems.add(create(clrtoexp.getName(), tabtype));
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}	
	//生成查询命令
    
	public byte[] deleteClrtoexp(ClrtoexpObject clrtoexp, int session, int seqid){
		String pathr ="ne/protocols/mpls/llsp/clrtoexp" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(delete(clrtoexp.getName())) ;
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
