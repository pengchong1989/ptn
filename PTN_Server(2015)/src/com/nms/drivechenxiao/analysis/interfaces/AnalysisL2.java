package com.nms.drivechenxiao.analysis.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.cmap.L2Object;
/**流细分 l2类型
 * **/
public class AnalysisL2 extends CxtOpLump{
	//解析
	public List<L2Object> analysisL2(byte[] command, CXNEObject CXNEObject){
		List<L2Object> l2list =new ArrayList<L2Object>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		l2list = super.analysisTabble("cmapl2", t);
		
		return l2list;
	}
	
	//生成查询命令                          
	public byte[] selectL2(int session, int seqid){
		String pathr ="ne/cmap" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "__noempty"));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "__match_^l2+%d+$"));
		
		  List<CxtAtomType> l2ltype = new ArrayList<CxtAtomType>();
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlan"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlan"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanpri"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanprimask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanpri"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanprimask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ref"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ethtype"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ethtypemask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstmac"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstmacmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcmac"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcmacmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		  
		  CxtATTable l2type = getCxtATTable(l2ltype.size() / 2, l2ltype);
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, l2type ));
		
		CxtATTable cxt = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
		
		cxtOpItems.add(get(cxt, 2)) ;
		
//		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//创建
	public CxtOpItem createL2(L2Object l2,int session, int seqid){
		String pathr ="ne/cmap" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
//		cxtOpItems.add(begin(3));
//		cxtOpItems.add(cd(pathr));
		
		List<CxtAtomType> l2ltype = new ArrayList<CxtAtomType>();
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlan"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getSpvlan() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getSpvlanmask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlan"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getCevlan() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getCevlanmask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanpri"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getSpvlanpri() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanprimask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getSpvlanprimask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanpri"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getCevlanpri() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanprimask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getCevlanmask() ));
//		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
//		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l2.getType() ));
//		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ref"));
//		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l2.getRef() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ethtype"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getEthtype() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ethtypemask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getEthtypemask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstmac"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_64, l2.getDstmac() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstmacmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_64, l2.getDstmacmask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcmac"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_64, l2.getSrcmac() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcmacmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_64, l2.getSrcmacmask() ));
		  
		  CxtATTable l2type = getCxtATTable(l2ltype.size() / 2, l2ltype);
		
		return create(l2.getName(),l2type) ;
//		cxtOpItems.add(commit());
//		
//		byte[] command = getCommandBytes(cxtOpItems);
//		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
//		return command;
//		return cxtOpItems;
	}
	public byte[] createL2b(L2Object l2,int session, int seqid){
		String pathr ="ne/cmap" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		
		List<CxtAtomType> l2ltype = new ArrayList<CxtAtomType>();
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlan"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getSpvlan() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getSpvlanmask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlan"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getCevlan() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getCevlanmask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanpri"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getSpvlanpri() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanprimask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getSpvlanprimask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanpri"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getCevlanpri() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanprimask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getCevlanmask() ));
//		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
//		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l2.getType() ));
//		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ref"));
//		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l2.getRef() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ethtype"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getEthtype() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ethtypemask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getEthtypemask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstmac"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_64, l2.getDstmac() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstmacmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_64, l2.getDstmacmask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcmac"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_64, l2.getSrcmac() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcmacmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_64, l2.getSrcmacmask() ));
		  
		  CxtATTable l2type = getCxtATTable(l2ltype.size() / 2, l2ltype);
		
		  cxtOpItems.add(create(l2.getName(), l2type));
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//生成更新 update命令
	public byte[] updateL2(L2Object l2,int session, int seqid){
		String pathr ="ne/cmap" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		
		List<CxtAtomType> l2ltype = new ArrayList<CxtAtomType>();
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlan"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getSpvlan() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getSpvlanmask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlan"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getCevlan() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getCevlanmask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanpri"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getSpvlanpri() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlanprimask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getSpvlanprimask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanpri"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getCevlanpri() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlanprimask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getCevlanmask() ));
//		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
//		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l2.getType() ));
//		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ref"));
//		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_BOOL, l2.getRef() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ethtype"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getEthtype() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ethtypemask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, l2.getEthtypemask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstmac"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_64, l2.getDstmac() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dstmacmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_64, l2.getDstmacmask() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcmac"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_64, l2.getSrcmac() ));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_STRING, "srcmacmask"));
		  l2ltype.add(getCxtAtomType(CxtAtomType.AT_NUM_64, l2.getSrcmacmask() ));
		  
		  CxtATTable l2type = getCxtATTable(l2ltype.size() / 2, l2ltype);
		
		cxtOpItems.add(mset(l2.getName(),l2type)) ;
		cxtOpItems.add(commit());

		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//删除
	public byte[] deleteL2(L2Object l2,int session, int seqid){
		String pathr ="ne/cmap" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(delete(l2.getName())) ;
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
}
