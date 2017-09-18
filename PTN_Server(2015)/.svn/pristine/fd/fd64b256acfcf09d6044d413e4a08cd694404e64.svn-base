package com.nms.drivechenxiao.analysis.clock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.clock.ClockObject;
import com.nms.drivechenxiao.service.bean.clock.Ieee1588Object;
//频率管理/ 网元时钟状态属性
public class AnalysisClock extends CxtOpLump{
	//解析
	public List<ClockObject> analysisClock(byte[] command, CXNEObject CXNEObject){
		List<ClockObject> clocklist =new ArrayList<ClockObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		clocklist = super.analysisTabble("Clock", t);
		
		if(clocklist.size()==2){
			ClockObject cl = (clocklist.get(1));
			Ieee1588Object i15 = ((ClockObject)(clocklist.get(0))).getIeee1588();			
			cl.setIeee1588(i15);
			clocklist.clear();
			clocklist.add(cl);
		}
		return clocklist;
	}
	
	//生成查询命令
	                          
	public byte[] selectClock(ClockObject clock, int session, int seqid){
		String pathr ="ne/clock" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	
//	public byte[] createRing(ClockObject clock, int session, int seqid){
//		if(ring.getName()==null)return new byte[]{};
//		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
//		
//		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "nodeid"));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, ring.getNodeid()));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "westnbrid"));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, ring.getWestnbrid()));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "eastnbrid"));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, ring.getEastnbrid()));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "westport"));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getWestport()));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "eastport"));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getEastport()));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "enaps"));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getEnaps()));
//		if(have(ring.getWtrtime())){
//		  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "wtrtime"));
//		  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getWtrtime()));
//		}
//		if(have(ring.getHoldofftime())){
//			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "holdofftime"));
//			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getHoldofftime()));
//		}
//		if(have(ring.getStat())){
//			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "stat"));
//			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getStat()));
//		}		
//		if(have(ring.getWestlastrxpdu())){
//			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "westlastrxpdu"));
//			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getWestlastrxpdu()));
//		}
//		if(have(ring.getEastlastrxpdu())){
//			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "eastlastrxpdu"));
//			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getEastlastrxpdu()));
//		}
//		if(have(ring.getWestlasttxpdu())){
//			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "westlasttxpdu"));
//			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getWestlasttxpdu()));
//		}
//		if(have(ring.getEastlasttxpdu())){
//			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "eastlasttxpdu"));
//			  cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, ring.getEastlasttxpdu()));
//		}
//		
//		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
//		
//		cxtOpItems.add(begin(3));
//		cxtOpItems.add(cd("ne/protocols/mpls/ring"));
//		cxtOpItems.add(create(ring.getName(), cxtATTable));
//		cxtOpItems.add(commit());
//		byte[] command = getCommandBytes(cxtOpItems);
//		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
//
//		return command;
//	}
	//生成更新 update命令
	public byte[] updateClock(ClockObject clock, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/clock"));
		if(have(clock.getSsmmode()))cxtOpItems.add(set("ssmmode", clock.getSsmmode()));//
		if(have(clock.getScswtr()))cxtOpItems.add(set("scswtr", clock.getScswtr()));//
		if(have(clock.getEcswtr()))cxtOpItems.add(set("ecswtr", clock.getEcswtr()));//
		if(have(clock.getFreerunlevel()))cxtOpItems.add(set("freerunlevel", setlevel(clock.getFreerunlevel()) ));//自由震荡级别
		if(have(clock.getUnknownlevel()))cxtOpItems.add(set("unknownlevel", setlevel(clock.getUnknownlevel()) ));//未知映射等级
		if(have(clock.getOperationmode()))cxtOpItems.add(set("operationmode", setoperationmode(clock.getOperationmode()) ));// 操作模式
		if(have(clock.getExtclkdrvmode()))cxtOpItems.add(set("extclkdrvmode", setextclkdrvmode(clock.getExtclkdrvmode()) ));//导出时钟模式
		if(have(clock.getSquelchmin()))cxtOpItems.add(set("squelchmin", setlevel(clock.getSquelchmin() )));//外时钟压制门限
//		if(have(clock.getSCSWorkState()))cxtOpItems.add(set("SCSWorkState", setState(clock.getSCSWorkState()) ));
//		if(have(clock.getECSWorkState()))cxtOpItems.add(set("ECSWorkState", setState(clock.getECSWorkState()) ));
//		if(have(clock.getSCSSelectSource()))cxtOpItems.add(set("SCSSelectSource", clock.getSCSSelectSource()));
		//if(have(clock.getECSSelectSource()))cxtOpItems.add(set("ECSSelectSource", clock.getECSSelectSource()));
//		if(have(clock.getIeee1588())){
//			cxtOpItems.add(cd("ne/clock/1588v2"));
//			if(have(clock.getIeee1588().getPhysicalState()))cxtOpItems.add(set("PhysicalState", setieState(clock.getIeee1588().getPhysicalState()) ));
//			if(have(clock.getIeee1588().getSCSLogicalState()))cxtOpItems.add(set("SCSLogicalState", setieState(clock.getIeee1588().getSCSLogicalState()) ));
//			if(have(clock.getIeee1588().getSCSQL()))cxtOpItems.add(set("SCSQL", setlevel(clock.getIeee1588().getSCSQL() ) ));
//			
//		}
		
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
//	public byte[] deleteRing(RingObject ring, int session, int seqid){
//		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
//		cxtOpItems.add(begin(3));
//		cxtOpItems.add(cd("ne/protocols/mpls/ring"));
//		cxtOpItems.add(delete(ring.getName()) );
//		cxtOpItems.add(commit());
//		
//		byte[] command = getCommandBytes(cxtOpItems);
//		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
//		return command;
//	}
	//类型转换
	private boolean have(String s){
		if(null == s ||s.equals(""))return false;
		else{ return true;}
	}
	//类型转换
	private boolean have(Ieee1588Object s){
		if(null == s )return false;
		else{ return true;}
	}
	//类型转换
	private String setlevel(String free){
		if("1".equals(free))return "prc";
		if("2".equals(free))return "ssua";
		if("3".equals(free))return "ssub";
		if("4".equals(free))return "sec";
		if("5".equals(free))return "dnu";
		if("6".equals(free))return "inv";
		if("255".equals(free))return "auto";
		else return "sec";
	}
	//类型转换
	private String setoperationmode(String mode){
		if("0".equals(mode))return "auto";
		if("1".equals(mode))return "freerun";
		if("2".equals(mode))return "holdover";
		else return "auto";
	}
	//类型转换
	private String setextclkdrvmode(String mode){
		if("0".equals(mode))return "setg";
		if("1".equals(mode))return "ecs";
		else return "setg";
	}
	//类型转换
	private String setState(String state){
		if("0".equals(state))return "freerun";
		if("1".equals(state))return "prelock";
		if("2".equals(state))return "lock";
		if("3".equals(state))return "holdover";
		if("4".equals(state))return "outoflock";
		else return "freerun";
	}
	//类型转换
	private String setieState(String state){
		if("0".equals(state))return "fail";
		if("1".equals(state))return "normal";
		if("2".equals(state))return "wtr";
		if("3".equals(state))return "na";
		else return "fail";
	}
	//生成测试数据
	private byte[] getTestClock(){
		byte[] cl = new byte[]{(byte)0x72, (byte)0x6D ,(byte)0x74 ,(byte)0x01 ,(byte)0x00 ,(byte)0x00 ,(byte)0x01 ,(byte)0xD6  ,(byte)0x22 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00,  
				(byte)0x01, (byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x03 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00  ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00,  
				(byte)0x03, (byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00  ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00,  
				(byte)0x00, (byte)0x30 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x0D ,(byte)0x20 ,(byte)0x00  ,(byte)0x00 ,(byte)0x00 ,(byte)0x06 ,(byte)0x31 ,(byte)0x35 ,(byte)0x38 ,(byte)0x38 ,(byte)0x76,  
				(byte)0x32, (byte)0x30 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x08 ,(byte)0x20 ,(byte)0x00  ,(byte)0x00 ,(byte)0x00 ,(byte)0x0D ,(byte)0x50 ,(byte)0x68 ,(byte)0x79 ,(byte)0x73 ,(byte)0x69,  
				(byte)0x63, (byte)0x61 ,(byte)0x6C ,(byte)0x53 ,(byte)0x74 ,(byte)0x61 ,(byte)0x74 ,(byte)0x65  ,(byte)0x13 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x20 ,(byte)0x00 ,(byte)0x00,  
				(byte)0x00, (byte)0x0F ,(byte)0x53 ,(byte)0x43 ,(byte)0x53 ,(byte)0x4C ,(byte)0x6F ,(byte)0x67  ,(byte)0x69 ,(byte)0x63 ,(byte)0x61 ,(byte)0x6C ,(byte)0x53 ,(byte)0x74 ,(byte)0x61 ,(byte)0x74,  
				(byte)0x65, (byte)0x13 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x20 ,(byte)0x00  ,(byte)0x00 ,(byte)0x00 ,(byte)0x05 ,(byte)0x53 ,(byte)0x43 ,(byte)0x53 ,(byte)0x51 ,(byte)0x4C,  
				(byte)0x13, (byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x06 ,(byte)0x20 ,(byte)0x00 ,(byte)0x00  ,(byte)0x00 ,(byte)0x0A ,(byte)0x66 ,(byte)0x6F ,(byte)0x72 ,(byte)0x63 ,(byte)0x65 ,(byte)0x6C,  
				(byte)0x65, (byte)0x76 ,(byte)0x65 ,(byte)0x6C ,(byte)0x13 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00  ,(byte)0xFF ,(byte)0x20 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x08 ,(byte)0x73 ,(byte)0x63,  
				(byte)0x73, (byte)0x66 ,(byte)0x6F ,(byte)0x72 ,(byte)0x63 ,(byte)0x65 ,(byte)0x01 ,(byte)0x00  ,(byte)0x20 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x0A ,(byte)0x73 ,(byte)0x63 ,(byte)0x73,  
				(byte)0x6C, (byte)0x6F ,(byte)0x63 ,(byte)0x6B ,(byte)0x6F ,(byte)0x75 ,(byte)0x74 ,(byte)0x01  ,(byte)0x00 ,(byte)0x20 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x09 ,(byte)0x73 ,(byte)0x63,  
				(byte)0x73, (byte)0x6D ,(byte)0x61 ,(byte)0x6E ,(byte)0x75 ,(byte)0x61 ,(byte)0x6C ,(byte)0x01  ,(byte)0x00 ,(byte)0x20 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x06 ,(byte)0x73 ,(byte)0x63,  
				(byte)0x73, (byte)0x70 ,(byte)0x72 ,(byte)0x69 ,(byte)0x13 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00  ,(byte)0x00 ,(byte)0x20 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x0F ,(byte)0x45 ,(byte)0x43,  
				(byte)0x53, (byte)0x53 ,(byte)0x65 ,(byte)0x6C ,(byte)0x65 ,(byte)0x63 ,(byte)0x74 ,(byte)0x53  ,(byte)0x6F ,(byte)0x75 ,(byte)0x72 ,(byte)0x63 ,(byte)0x65 ,(byte)0x20 ,(byte)0x00 ,(byte)0x00,  
				(byte)0x00, (byte)0x04 ,(byte)0x6E ,(byte)0x6F ,(byte)0x6E ,(byte)0x65 ,(byte)0x20 ,(byte)0x00  ,(byte)0x00 ,(byte)0x00 ,(byte)0x0C ,(byte)0x45 ,(byte)0x43 ,(byte)0x53 ,(byte)0x57 ,(byte)0x6F,  
				(byte)0x72, (byte)0x6B ,(byte)0x53 ,(byte)0x74 ,(byte)0x61 ,(byte)0x74 ,(byte)0x65 ,(byte)0x13  ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x04 ,(byte)0x20 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00,  
				(byte)0x0F, (byte)0x53 ,(byte)0x43 ,(byte)0x53 ,(byte)0x53 ,(byte)0x65 ,(byte)0x6C ,(byte)0x65  ,(byte)0x63 ,(byte)0x74 ,(byte)0x53 ,(byte)0x6F ,(byte)0x75 ,(byte)0x72 ,(byte)0x63 ,(byte)0x65,  
				(byte)0x20, (byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x06 ,(byte)0x67 ,(byte)0x65 ,(byte)0x2E  ,(byte)0x33 ,(byte)0x2E ,(byte)0x31 ,(byte)0x20 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x0C,  
				(byte)0x53, (byte)0x43 ,(byte)0x53 ,(byte)0x57 ,(byte)0x6F ,(byte)0x72 ,(byte)0x6B ,(byte)0x53  ,(byte)0x74 ,(byte)0x61 ,(byte)0x74 ,(byte)0x65 ,(byte)0x13 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00,  
				(byte)0x02, (byte)0x20 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x06 ,(byte)0x65 ,(byte)0x63  ,(byte)0x73 ,(byte)0x77 ,(byte)0x74 ,(byte)0x72 ,(byte)0x13 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00,  
				(byte)0x05, (byte)0x20 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x0D ,(byte)0x65 ,(byte)0x78  ,(byte)0x74 ,(byte)0x63 ,(byte)0x6C ,(byte)0x6B ,(byte)0x64 ,(byte)0x72 ,(byte)0x76 ,(byte)0x6D,  
				(byte)0x6F, (byte)0x64 ,(byte)0x65 ,(byte)0x13 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00  ,(byte)0x20 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x0C ,(byte)0x66 ,(byte)0x72 ,(byte)0x65,  
				(byte)0x65, (byte)0x72 ,(byte)0x75 ,(byte)0x6E ,(byte)0x6C ,(byte)0x65 ,(byte)0x76 ,(byte)0x65  ,(byte)0x6C ,(byte)0x13 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x04 ,(byte)0x20 ,(byte)0x00,  
				(byte)0x00, (byte)0x00 ,(byte)0x0D ,(byte)0x6F ,(byte)0x70 ,(byte)0x65 ,(byte)0x72 ,(byte)0x61  ,(byte)0x74 ,(byte)0x69 ,(byte)0x6F ,(byte)0x6E ,(byte)0x6D ,(byte)0x6F ,(byte)0x64 ,(byte)0x65,  
				(byte)0x13, (byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x20 ,(byte)0x00 ,(byte)0x00  ,(byte)0x00 ,(byte)0x06 ,(byte)0x73 ,(byte)0x63 ,(byte)0x73 ,(byte)0x77 ,(byte)0x74 ,(byte)0x72,  
				(byte)0x13, (byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x05 ,(byte)0x20 ,(byte)0x00 ,(byte)0x00  ,(byte)0x00 ,(byte)0x0A ,(byte)0x73 ,(byte)0x71 ,(byte)0x75 ,(byte)0x65 ,(byte)0x6C ,(byte)0x63,  
				(byte)0x68, (byte)0x6D ,(byte)0x69 ,(byte)0x6E ,(byte)0x13 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00  ,(byte)0x05 ,(byte)0x20 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x07 ,(byte)0x73 ,(byte)0x73,  
				(byte)0x6D, (byte)0x6D ,(byte)0x6F ,(byte)0x64 ,(byte)0x65 ,(byte)0x01 ,(byte)0x00 ,(byte)0x20  ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x0C ,(byte)0x75 ,(byte)0x6E ,(byte)0x6B ,(byte)0x6E,  
				(byte)0x6F, (byte)0x77 ,(byte)0x6E ,(byte)0x6C ,(byte)0x65 ,(byte)0x76 ,(byte)0x65 ,(byte)0x6C  ,(byte)0x13 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x05 ,(byte)0x00 };
		return cl;
	}
	public static void main(String[] s){
		AnalysisClock ac = new AnalysisClock();
		ac.analysisClock(ac.getTestClock(), null);
	}
}
