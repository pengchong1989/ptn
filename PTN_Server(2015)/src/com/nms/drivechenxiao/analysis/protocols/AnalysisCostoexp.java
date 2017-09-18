package com.nms.drivechenxiao.analysis.protocols;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.elsp.CostoexpObject;

/**端口exp业务映射 里面的 elsp里的 输出
 * **/
public class AnalysisCostoexp extends CxtOpLump{
	public List<CostoexpObject> analysisCostoexp(byte[] command, CXNEObject CXNEObject){
		List<CostoexpObject> list =new ArrayList<CostoexpObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		list = super.analysisTabble("Costoexp", t);		
		
		return list;
	}
	
	//生成查询命令
	                          
	public byte[] selectCostoexp( int session, int seqid){
		String pathr ="ne/protocols/mpls/elsp/costoexp" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] updateCostoexp(CostoexpObject costoexp, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/mpls/elsp/costoexp" ));
		List<CxtAtomType> listype = new ArrayList<CxtAtomType>();
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "be"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getBe() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af1"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getAf1() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af2"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getAf2() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af3"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getAf3() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af4"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getAf4() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ef"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getEf() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cs6"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getCs6() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cs7"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getCs7() ));
		
		  CxtATTable tabtype = getCxtATTable(listype.size() / 2, listype);
		
		  cxtOpItems.add(mset(costoexp.getName(), tabtype));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] createCostoexp(CostoexpObject costoexp, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/mpls/elsp/costoexp" ));
		List<CxtAtomType> listype = new ArrayList<CxtAtomType>();
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "be"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getBe() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af1"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getAf1() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af2"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getAf2() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af3"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getAf3() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "af4"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getAf4() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ef"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getEf() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cs6"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getCs6() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cs7"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, costoexp.getCs7() ));
		
		  CxtATTable tabtype = getCxtATTable(listype.size() / 2, listype);
		
		  cxtOpItems.add(create(costoexp.getName(), tabtype));
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}	
	//生成查询命令
    
	public byte[] deleteCostoexp(CostoexpObject costoexp, int session, int seqid){
		String pathr ="ne/protocols/mpls/elsp/costoexp" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(delete(costoexp.getName())) ;
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
