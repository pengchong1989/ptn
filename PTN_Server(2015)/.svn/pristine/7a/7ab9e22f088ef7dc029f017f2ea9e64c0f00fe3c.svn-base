package com.nms.drivechenxiao.analysis.protocols;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.DualObject;

/**双规保护处理**/
public class AnalysisDual extends CxtOpLump{
	public List<DualObject> analysisDual(byte[] command, CXNEObject CXNEObject){
		List<DualObject> duallist =new ArrayList<DualObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		duallist = super.analysisTabble("Dual", t);		
		
		return duallist;
	}
	
	//生成查询命令
	                          
	public byte[] selectDual( int session, int seqid){
		String pathr ="ne/protocols/dual" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] updateDual(DualObject dual, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/dual" ));
		List<CxtAtomType> ldualtype = new ArrayList<CxtAtomType>();

		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "enaps"));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_BOOL, dual.getEnaps() ));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, dual.getType() ));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "wtrtime"));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.getWtrtime()  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "apscmd"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.get  ));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dualif"));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, dual.getDualif()  ));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "iswork"));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_BOOL, dual.getIswork()  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "passtunel"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.get  ));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "swmanner"));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, dual.getSwmanner()  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "pwdrop"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.get  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ref"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.get  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "sel"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.get  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "clearcnt"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.getClearcnt()  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "mspid"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.getMspid()  ));
		  
		  CxtATTable dualtype = getCxtATTable(ldualtype.size() / 2, ldualtype);
		
		  cxtOpItems.add(mset(dual.getName(), dualtype));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] createDual(DualObject dual, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/dual" ));
		List<CxtAtomType> ldualtype = new ArrayList<CxtAtomType>();
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "enaps"));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_BOOL, dual.getEnaps() ));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, dual.getType() ));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "wtrtime"));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.getWtrtime()  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "apscmd"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.get  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dualif"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, dual.getDualif()  ));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "iswork"));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_BOOL, dual.getIswork()  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "passtunel"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.get  ));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "swmanner"));
		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, dual.getSwmanner()  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "pwdrop"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.get  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ref"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.get  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "sel"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.get  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "clearcnt"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.getClearcnt()  ));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_STRING, "mspid"));
//		ldualtype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, dual.getMspid()  ));
		  
		  
		  CxtATTable dualtype = getCxtATTable(ldualtype.size() / 2, ldualtype);
		
		  cxtOpItems.add(create(dual.getName(), dualtype));
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}	
	//生成查询命令
    
	public byte[] deleteDual(DualObject dual, int session, int seqid){
		String pathr ="ne/protocols/dual" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(delete(dual.getName())) ;
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
