package com.nms.drivechenxiao.analysis.protocols;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.MspObject;
/**msp保护处理**/
public class AnalysisMsp extends CxtOpLump{
	public List<MspObject> analysisMsp(byte[] command, CXNEObject CXNEObject){
		List<MspObject> msplist =new ArrayList<MspObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		msplist = super.analysisTabble("Msp", t);		
		
		return msplist;
	}
	
	//生成查询命令
	                          
	public byte[] selectMsp( int session, int seqid){
		String pathr ="ne/protocols/msp" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] updateMsp(MspObject msp, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/msp" ));
		List<CxtAtomType> lmsptype = new ArrayList<CxtAtomType>();
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "desc"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, msp.getDesc() ));
		if(have(msp.getEnaps())){
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "enaps"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_BOOL, msp.getEnaps() ));}
		if(have(msp.getType())){
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, msp.getType() ));}
		  if(have(msp.getWtrtime())){
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "wtrtime"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getWtrtime() ));}
		  if(have(msp.getHoldofftime())){
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "holdofftime"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getHoldofftime() ));}
		  if(have(msp.getSdthreshold())){
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "sdthreshold"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_BOOL, msp.getSdthreshold() ));}
		  if(have(msp.getApscmd())){
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "apscmd"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, msp.getApscmd() ));}
		  if(have(msp.getSfhighpri())){
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "sfhighpri"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_BOOL, msp.getSfhighpri() ));}
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "sdhighpri"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_BOOL, msp.getSdhighpri() ));
		  if(have(msp.getPrtport())){
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "prtport"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, msp.getPrtport() ));}
		  if(have(msp.getWorkport())){
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "workport"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, msp.getWorkport() ));}
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "apsstat"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getApsstat() ));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "sel"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getSel() ));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "clearcnt"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getClearcnt() ));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "lastxmitkbyte"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getLastxmitkbyte() ));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "lastrecvkbyte"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getLastrecvkbyte() ));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dualid"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getDualid() ));
		  
		  
		  CxtATTable msptype = getCxtATTable(lmsptype.size() / 2, lmsptype);
		
		  cxtOpItems.add(mset(msp.getName(), msptype));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] createMsp(MspObject msp, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/msp" ));
		List<CxtAtomType> lmsptype = new ArrayList<CxtAtomType>();
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "desc"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, msp.getDesc() ));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "enaps"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_BOOL, msp.getEnaps() ));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, msp.getType() ));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "wtrtime"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getWtrtime() ));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "holdofftime"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getHoldofftime() ));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "sdthreshold"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_BOOL, msp.getSdthreshold() ));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "apscmd"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, msp.getApscmd() ));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "sfhighpri"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_BOOL, msp.getSfhighpri() ));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "sdhighpri"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_BOOL, msp.getSdhighpri() ));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "prtport"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, msp.getPrtport() ));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "workport"));
		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, msp.getWorkport() ));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "apsstat"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getApsstat() ));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "sel"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getSel() ));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "clearcnt"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getClearcnt() ));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "lastxmitkbyte"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getLastxmitkbyte() ));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "lastrecvkbyte"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getLastrecvkbyte() ));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_STRING, "dualid"));
//		  lmsptype.add(getCxtAtomType(CxtAtomType.AT_NUM_32, msp.getDualid() ));
		  
		  
		  CxtATTable msptype = getCxtATTable(lmsptype.size() / 2, lmsptype);
		
		  cxtOpItems.add(create(msp.getName(), msptype));
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}	
	//生成查询命令
    
	public byte[] deleteMsp(MspObject msp, int session, int seqid){
		String pathr ="ne/protocols/msp" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(delete(msp.getName())) ;
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
