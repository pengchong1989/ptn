package com.nms.drivechenxiao.analysis.clock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.PtpPortObject;
/**
 * 时间管理 里面的第2个有关接口的页面
 * **/
public class AnalysisPtpPort extends CxtOpLump{
	//解析
	public List<PtpPortObject> analysisPtpPort(byte[] command, CXNEObject CXNEObject){
		List<PtpPortObject> ptplist =new ArrayList<PtpPortObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		ptplist = super.analysisTabble("Ptpport", t);
		return ptplist;
	}
	//生成查询命令
	public byte[] selectPtpPort(int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3))  ;
		cxtOpItems.add(cd("ne/interfaces/eth"));
		List<CxtAtomType> clockporttype = new ArrayList<CxtAtomType>();
		clockporttype.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		clockporttype.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		clockporttype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ptp"));
		clockporttype.add(getCxtAtomType(CxtAtomType.AT_STRING, "" ));
		CxtATTable clocktype = getCxtATTable(clockporttype.size() / 2, clockporttype);
		
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "__noempty"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, "true" ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "__match_^%w+.%d+.%d+$"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, clocktype ));
		
		CxtATTable cxt = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
		
		cxtOpItems.add(get(cxt, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//删除
	public byte[] deletePtpPort(List<PtpPortObject> ptpport, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String path="" ;
		cxtOpItems.add(begin(3))  ;
		for(PtpPortObject ptpPort:ptpport){
			if(ptpPort.getPortname()==null){
				System.out.println("name is null");
			}else {
				if(ptpPort.getPortname().indexOf("ge")>=0||ptpPort.getPortname().indexOf("xg")>=0){
					path = "ne/interfaces/eth/"+ptpPort.getPortname();
					cxtOpItems.add(cd(path));
					cxtOpItems.add(delete("ptp"));
				}else {
					System.out.println("clock create error. ");
					return new byte[]{};
				}	
			}
		}
	
		cxtOpItems.add(commit());;		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//创建
	public byte[] createPtpPort(PtpPortObject ptpport, int session, int seqid){
		if(ptpport==null)return new byte[]{};
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "testdelayoffset"));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,ptpport.getTestdelayoffset()));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "portstate"));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,ptpport.getPortstate()));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ref"));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,ptpport.getRef()));
		//------
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "enable"));// 01,01
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,ptpport.getEnable()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "porttype"));//11,01
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8,ptpport.getPorttype()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "delaymechanism"));//11,01
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8,ptpport.getDelaymechanism()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanid"));//13,00000001
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,ptpport.getVlanid()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "operationmode"));//11,00
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8,ptpport.getOperationmode()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "delayoffset"));//13,00000000
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,ptpport.getDelayoffset()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "anncintv"));//13,fffffffd
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,ptpport.getAnncintv()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "annctmo"));//13,00000004
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,ptpport.getAnnctmo()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "syncintv"));//13,fffffffc
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,ptpport.getSyncintv()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "delreqintv"));//13,00000000
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,ptpport.getDelreqintv()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "pdelreqintv"));//13,00000000
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,ptpport.getPdelreqintv()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "two_step"));//01,01
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,ptpport.getTwo_step()));		
		if(ptpport.getPortlist().size()>0){
			List<String> portl = ptpport.getPortlist();
			List<CxtAtomType> cxtPortlist = new ArrayList<CxtAtomType>();
			for(int i=0; i<portl.size();i++){
				cxtPortlist.add(getCxtAtomType(CxtAtomType.AT_NUM_32,i+1));//01,01
				cxtPortlist.add(getCxtAtomType(CxtAtomType.AT_STRING,portl.get(i)));
			}
			CxtATTable cxtPort = getCxtATTable(cxtPortlist.size() / 2, cxtPortlist);
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "portlist"));//30,XXXX
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtPort));
		}
		
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
		
		cxtOpItems.add(begin(3));		
		String path ;
		if(ptpport.getPortname().indexOf("ge")>=0||ptpport.getPortname().indexOf("xg")>=0){
			path = "ne/interfaces/eth/"+ptpport.getPortname();
		}else {
			System.out.println("ptpport create error. ");
			return new byte[]{};
		}	
		cxtOpItems.add(cd(path));
//		cxtOpItems.add(set("admin", ptpport.getPortadmin() ));
		cxtOpItems.add(create("ptp", cxtATTable));
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);

		return command;
	}
	//生成更新 update命令
	public byte[] updatePtpPort(PtpPortObject ptpport, int session, int seqid){
		if(ptpport==null)return new byte[]{};
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "enable"));// 01,01
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,ptpport.getEnable()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "porttype"));//11,01
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8,ptpport.getPorttype()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "delaymechanism"));//11,01
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8,ptpport.getDelaymechanism()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanid"));//13,00000001
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,ptpport.getVlanid()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "operationmode"));//11,00
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8,ptpport.getOperationmode()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "delayoffset"));//13,00000000
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,ptpport.getDelayoffset()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "anncintv"));//13,fffffffd
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,ptpport.getAnncintv()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "annctmo"));//13,00000004
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,ptpport.getAnnctmo()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "syncintv"));//13,fffffffc
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,ptpport.getSyncintv()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "delreqintv"));//13,00000000
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,ptpport.getDelreqintv()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "pdelreqintv"));//13,00000000
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32,ptpport.getPdelreqintv()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "two_step"));//01,01
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,ptpport.getTwo_step()));	
		if(ptpport.getPortlist().size()>0){
			List<String> portl = ptpport.getPortlist();
			List<CxtAtomType> cxtPortlist = new ArrayList<CxtAtomType>();
			for(int i=0; i<portl.size();i++){
				cxtPortlist.add(getCxtAtomType(CxtAtomType.AT_NUM_32,i+1));//01,01
				cxtPortlist.add(getCxtAtomType(CxtAtomType.AT_STRING,portl.get(i)));
			}
			CxtATTable cxtPort = getCxtATTable(cxtPortlist.size() / 2, cxtPortlist);
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "portlist"));//30,XXXX
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtPort));
		}
				
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
		
		cxtOpItems.add(begin(3));		
		String path ;
		if(ptpport.getPortname().indexOf("ge")>=0||ptpport.getPortname().indexOf("xg")>=0){
			path = "ne/interfaces/eth/"+ptpport.getPortname();
		}
		else {
			System.out.println("ptpport create error. ");
			return new byte[]{};
		}	
		cxtOpItems.add(cd(path));
		if(have(ptpport.getPortadmin()))cxtOpItems.add(set("admin", ptpport.getPortadmin() ));
		
//		cxtOpItems.add(cd(path+"ptp"));
//		cxtOpItems.add(set("scspri", clock.getScspri()));
//		cxtOpItems.add(set("ecspri", clock.getEcspri()));
//		cxtOpItems.add(set("scslockout", clock.getScslockout()));
//		cxtOpItems.add(set("ecslockout", clock.getEcslockout()));
//		cxtOpItems.add(set("forcelevel", getForceleve(clock.getForcelevel()) ));
//		if(clock.getPortname().indexOf("pdh")>=0){
//			cxtOpItems.add(set("recoverymode", getRecoverymode(clock.getEcslockout()) ));
//		}
		cxtOpItems.add(mset("ptp", cxtATTable));
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
