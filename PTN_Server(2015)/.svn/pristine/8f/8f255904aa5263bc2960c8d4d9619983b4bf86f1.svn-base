package com.nms.drivechenxiao.analysis.protocols;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.Vlanpri2cngObject;

public class AnalysisVlanpri2cng extends CxtOpLump{
	public List<Vlanpri2cngObject> analysisVlanpri2cng(byte[] command, CXNEObject CXNEObject){
		List<Vlanpri2cngObject> list =new ArrayList<Vlanpri2cngObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		list = super.analysisTabble("vlanpri2cng", t);		
		
		return list;
	}
	
	//生成查询命令
	                          
	public byte[] selectVlanpri2cng( int session, int seqid){
		String pathr ="ne/vlanpri2cng" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] updateVlanpri2cng(Vlanpri2cngObject v, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/vlanpri2cng" ));
		List<CxtAtomType> listype = new ArrayList<CxtAtomType>();
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri0"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, v.getVlanpri0() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri1"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32,  v.getVlanpri1() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri2"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32,  v.getVlanpri2() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri3"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32,  v.getVlanpri3() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri4"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32,  v.getVlanpri4() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri5"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32,  v.getVlanpri5() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri6"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32,  v.getVlanpri6() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri7"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32,  v.getVlanpri7() ));
		
		  CxtATTable tabtype = getCxtATTable(listype.size() / 2, listype);
		
		  cxtOpItems.add(mset(v.getName(), tabtype));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] createVlanpri2cng(Vlanpri2cngObject v, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/vlanpri2cng" ));
		List<CxtAtomType> listype = new ArrayList<CxtAtomType>();
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri0"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, v.getVlanpri0() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri1"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32,  v.getVlanpri1() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri2"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32,  v.getVlanpri2() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri3"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32,  v.getVlanpri3() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri4"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32,  v.getVlanpri4() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri5"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32,  v.getVlanpri5() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri6"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32,  v.getVlanpri6() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanpri7"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_32,  v.getVlanpri7() ));
		
		  CxtATTable tabtype = getCxtATTable(listype.size() / 2, listype);
		
		  cxtOpItems.add(create(v.getName(), tabtype));
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}	
	//生成查询命令
    
	public byte[] deleteVlanpri2cng(Vlanpri2cngObject v, int session, int seqid){
		String pathr ="ne/vlanpri2cng" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(delete(v.getName())) ;
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
