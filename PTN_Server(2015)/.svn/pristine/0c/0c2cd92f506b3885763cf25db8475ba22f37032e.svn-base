package com.nms.drivechenxiao.analysis.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.cmap.L3Object;
/**流细分 l3类型
 * **/
public class AnalysisL3 extends CxtOpLump{
	//解析
	public List<L3Object> analysisL3(byte[] command, CXNEObject CXNEObject){
		List<L3Object> l3list =new ArrayList<L3Object>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		l3list = super.analysisTabble("cmapl3", t);
		
		return l3list;
	}
	
	//生成查询命令                          
	public byte[] selectL3(int session, int seqid){
		String pathr ="ne/cmap" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "__noempty"));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "__match_^l3+%d+$"));
		
		  List<CxtAtomType> l3ltype = new ArrayList<CxtAtomType>();
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlan"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlan"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanpri"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanprimask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanpri"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanprimask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstip"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstipmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcip"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcipmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "pid"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "pidmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dscp"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ref"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dscpmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstport"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstportmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcport"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcportmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));


		  CxtATTable l3type = getCxtATTable(l3ltype.size() / 2, l3ltype);
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, l3type ));
		
		CxtATTable cxt = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
		
		cxtOpItems.add(get(cxt, 2)) ;
		
//		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//创建
	public CxtOpItem createL3(L3Object l3,int session, int seqid){
		String pathr ="ne/cmap" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
//		cxtOpItems.add(begin(3));
//		cxtOpItems.add(cd(pathr));
		
		List<CxtAtomType> l3ltype = new ArrayList<CxtAtomType>();
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlan"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSpvlan() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSpvlanmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlan"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getCevlan() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getCevlanmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanpri"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSpvlanpri() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanprimask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSpvlanprimask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanpri"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getCevlanpri() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanprimask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getCevlanmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getType() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstip"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getDstip() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstipmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getDstipmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcip"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSrcip() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcipmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSrcipmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "pid"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getPid() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "pidmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getPidmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dscp"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getDscp() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ref"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getRef() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dscpmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getDscpmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstport"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getDstport() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstportmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getDstportmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcport"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSrcport() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcportmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSrcportmask() ));
		  
		  CxtATTable l3type = getCxtATTable(l3ltype.size() / 2, l3ltype);
		
//		cxtOpItems.add(create(l3.getName(),l3type)) ;
//		cxtOpItems.add(commit());
//		
//		byte[] command = getCommandBytes(cxtOpItems);
//		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
//		return command;
		  
		  return create(l3.getName(),l3type);
	}
	//生成更新 update命令
	public byte[] updateL3(L3Object l3,int session, int seqid){
		String pathr ="ne/cmap" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		
		List<CxtAtomType> l3ltype = new ArrayList<CxtAtomType>();
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlan"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSpvlan() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSpvlanmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlan"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getCevlan() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getCevlanmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanpri"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSpvlanpri() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanprimask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSpvlanprimask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanpri"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getCevlanpri() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanprimask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getCevlanmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getType() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstip"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getDstip() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstipmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getDstipmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcip"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSrcip() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcipmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSrcipmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "pid"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getPid() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "pidmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getPidmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dscp"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getDscp() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ref"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getRef() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dscpmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getDscpmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstport"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getDstport() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstportmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getDstportmask() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcport"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSrcport() ));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcportmask"));
		  l3ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l3.getSrcportmask() ));

		  
		  CxtATTable l3type = getCxtATTable(l3ltype.size() / 2, l3ltype);
		
		cxtOpItems.add(mset(l3.getName(),l3type)) ;
		cxtOpItems.add(commit());

		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//删除
	public byte[] deleteL3(L3Object l3,int session, int seqid){
		String pathr ="ne/cmap" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(delete(l3.getName())) ;
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
}
