package com.nms.drivechenxiao.analysis.protocols;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.elsp.ExptocosObject;

/**端口exp业务映射 里面的 elsp里的 输入
 * **/
public class AnalysisExptocos extends CxtOpLump{
	public List<ExptocosObject> analysisExptocos(byte[] command, CXNEObject CXNEObject){
		List<ExptocosObject> msplist =new ArrayList<ExptocosObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		msplist = super.analysisTabble("Exptocos", t);		
		
		return msplist;
	}
	
	//生成查询命令
	                          
	public byte[] selectExptocos( int session, int seqid){
		String pathr ="ne/protocols/mpls/elsp/exptocos" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] updateExptocos(ExptocosObject exptocos, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/mpls/elsp/exptocos" ));
		List<CxtAtomType> listype = new ArrayList<CxtAtomType>();
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp0"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptocos.getExp0() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp1"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptocos.getExp1() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp2"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptocos.getExp2() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp3"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptocos.getExp3() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp4"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptocos.getExp4() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp5"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptocos.getExp5() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp6"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptocos.getExp6() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp7"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, exptocos.getExp7() ));
		
		  CxtATTable tabtype = getCxtATTable(listype.size() / 2, listype);
		
		  cxtOpItems.add(mset(exptocos.getName(), tabtype));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] createExptocos(ExptocosObject exptocos, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/mpls/elsp/exptocos" ));
		List<CxtAtomType> listype = new ArrayList<CxtAtomType>();
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp0"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, exptocos.getExp0() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp1"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, exptocos.getExp1() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp2"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, exptocos.getExp2() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp3"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, exptocos.getExp3() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp4"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, exptocos.getExp4() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp5"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, exptocos.getExp5() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp6"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, exptocos.getExp6() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "exp7"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, exptocos.getExp7() ));
		
		  CxtATTable tabtype = getCxtATTable(listype.size() / 2, listype);
		
		  cxtOpItems.add(create(exptocos.getName(), tabtype));
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}	
	//生成查询命令
    
	public byte[] deleteExptocos(ExptocosObject exptocos, int session, int seqid){
		String pathr ="ne/protocols/mpls/elsp/exptocos" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(delete(exptocos.getName())) ;
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
