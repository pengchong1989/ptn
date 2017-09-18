package com.nms.drivechenxiao.analysis.oam;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.oam.EfmObject;


/**对应oam 里面的 eth链路 oam 
 * 设备路径为:ne/interfaces/eth/XX.X.X/efm
 * **/
public class AnalysisEthEfmOam extends CxtOpLump{
	//解析
	public List<EfmObject> analysisEfm(byte[] command, CXNEObject CXNEObject){
		List<EfmObject> efmlist =new ArrayList<EfmObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		efmlist = super.analysisTabble("OamEfm", t);
		
		return efmlist;
	}
	
	//生成查询命令
	                          
	public byte[] selectEfm( int session, int seqid){
		String pathr ="ne/interfaces/eth/";
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/eth"));
		List<CxtAtomType> efmporttype = new ArrayList<CxtAtomType>();
		
		efmporttype.add(getCxtAtomType(CxtAtomType.AT_STRING, "efm"));
		efmporttype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "" ));
		
		CxtATTable efmtype = getCxtATTable(efmporttype.size() / 2, efmporttype);
		
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "__noempty"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "__match_^%w+.%d+.%d+$"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, efmtype ));
		
		CxtATTable cxt = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
		
		cxtOpItems.add(get(cxt, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	
	public byte[] createEfm(EfmObject efm, int session, int seqid){
		if(efm.getPortname()==null)return new byte[]{};
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(cd("ne/interfaces/eth/"+efm.getPortname()));
		
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "enable"));//01,01
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, efm.getEnable() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "workmode"));//11,01
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, efm.getWorkmode() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "rmtloopback"));//01,
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, efm.getRmtloopback() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "linkevent"));//01,
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, efm.getLinkevent() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "varretr"));//01,
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, efm.getVarretr() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "maxoampdu"));//13,00 00 05 ee
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, efm.getMaxoampdu() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "oui"));//13,00000000
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, efm.getOui() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vsi"));//14,00 00 00 00 00 00 00 00
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_D, efm.getVsi() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "lpbtimeout"));//13,00000005
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,efm.getLpbtimeout()  ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "errfrmsecondsthreshold"));//13,00000001
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,efm.getErrfrmsecondsthreshold()  ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "frequecy"));//13,00000001
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,efm.getFrequecy()  ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "losttime"));//13,00000005
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,efm.getLosttime()  ));
		
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
		
		cxtOpItems.add(create("efm", cxtATTable));
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);

		return command;
	}
	//生成更新 update命令
	public byte[] updateEfm(EfmObject efm, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/eth/"+efm.getPortname()));
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "enable"));//01,01
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, efm.getEnable() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "workmode"));//11,01
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, efm.getWorkmode() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "rmtloopback"));//01,
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, efm.getRmtloopback() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "linkevent"));//01,
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, efm.getLinkevent() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "varretr"));//01,
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, efm.getVarretr() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "maxoampdu"));//13,00 00 05 ee
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, efm.getMaxoampdu() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "oui"));//13,00000000
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, efm.getOui() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vsi"));//14,00 00 00 00 00 00 00 00
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_D, efm.getVsi() ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "lpbtimeout"));//13,00000005
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,efm.getLpbtimeout()  ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "errfrmsecondsthreshold"));//13,00000001
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,efm.getErrfrmsecondsthreshold()  ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "frequecy"));//13,00000001
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,efm.getFrequecy()  ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "losttime"));//13,00000005
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,efm.getLosttime()  ));
		
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
		
		cxtOpItems.add(mset("efm", cxtATTable));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] deleteEfm(EfmObject efm, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/eth/"+efm.getPortname()));
		cxtOpItems.add(delete("efm") );
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
