package com.nms.drivechenxiao.analysis.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.RingObject;

public class AnalysisRING extends CxtOpLump{
	//解析
	public List<RingObject> analysisRing(byte[] command, CXNEObject CXNEObject){
		List<RingObject> ringlist =new ArrayList<RingObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		ringlist = super.analysisTabble("Ring", t);
//for(RingObject r : ringlist){
//	System.out.println(" -- ring = "+r.toString());
//}		
		return ringlist;
	}
	
	//生成查询命令
	public byte[] selectRing(RingObject ring, int session, int seqid){
		String pathr =null;
		if(ring==null){pathr = "ne/protocols/mpls/ring" ;}
		else{pathr = "ne/protocols/mpls/ring/"+ring.getName(); }
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//创建
	public byte[] createRing(RingObject ring, int session, int seqid){
		if(ring.getName()==null)return new byte[]{};
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "nodeid"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, ring.getNodeid()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "westnbrid"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, ring.getWestnbrid()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "eastnbrid"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, ring.getEastnbrid()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "westport"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getWestport()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "eastport"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getEastport()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "enaps"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getEnaps()));
		if(have(ring.getWtrtime())){
		  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "wtrtime"));
		  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getWtrtime()));
		}
		if(have(ring.getHoldofftime())){
			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "holdofftime"));
			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getHoldofftime()));
		}
		if(have(ring.getStat())){
			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "stat"));
			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getStat()));
		}		
		if(have(ring.getWestlastrxpdu())){
			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "westlastrxpdu"));
			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getWestlastrxpdu()));
		}
		if(have(ring.getEastlastrxpdu())){
			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "eastlastrxpdu"));
			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getEastlastrxpdu()));
		}
		if(have(ring.getWestlasttxpdu())){
			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "westlasttxpdu"));
			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getWestlasttxpdu()));
		}
		if(have(ring.getEastlasttxpdu())){
			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "eastlasttxpdu"));
			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getEastlasttxpdu()));
		}
		
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
		
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/mpls/ring"));
		cxtOpItems.add(create(ring.getName(), cxtATTable));
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);

		return command;
	}
	//生成更新 update命令
	public byte[] updateRing(RingObject ring, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/mpls/ring/"+ring.getName()));
		if(have(ring.getDesc()))cxtOpItems.add(set("desc", ring.getDesc()));
		if(have(ring.getEnaps()))cxtOpItems.add(set("enaps", ring.getEnaps()));
		if(have(ring.getWtrtime()))cxtOpItems.add(set("wtrtime", ring.getWtrtime()));
		if(have(ring.getHoldofftime()))cxtOpItems.add(set("holdofftime", ring.getHoldofftime()));
		cxtOpItems.add(set("nodeid", ring.getNodeid()));
		cxtOpItems.add(set("westnbrid", ring.getWestnbrid()));
		cxtOpItems.add(set("eastnbrid", ring.getEastnbrid()));
		if(have(ring.getWestport()))cxtOpItems.add(set("westport", ring.getWestport()));
		if(have(ring.getEastport()))cxtOpItems.add(set("eastport", ring.getEastport()));
		if(have(ring.getApscmd()))cxtOpItems.add(set("apscmd", ring.getApscmd()));
		if(have(ring.getStat()))cxtOpItems.add(set("stat", ring.getStat()));
		if(have(ring.getWestlastrxpdu()))cxtOpItems.add(set("westlastrxpdu", ring.getWestlastrxpdu()));
		if(have(ring.getEastlastrxpdu()))cxtOpItems.add(set("eastlastrxpdu", ring.getEastlastrxpdu()));
		if(have(ring.getWestlasttxpdu()))cxtOpItems.add(set("westlasttxpdu", ring.getWestlasttxpdu()));
		if(have(ring.getEastlasttxpdu()))cxtOpItems.add(set("eastlasttxpdu", ring.getEastlasttxpdu()));		
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//删除
	public byte[] deleteRing(RingObject ring, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/mpls/ring"));
		cxtOpItems.add(delete(ring.getName()) );
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	private boolean have(String s){
		if(null == s ||s.equals(""))return false;
		else{ return true;}
	}
}
