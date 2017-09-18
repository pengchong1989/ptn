package com.nms.drivechenxiao.analysis.clock;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.clock.ExtclkObject;

//时钟接口 /外接时钟接口
public class AnalysisExtclk extends CxtOpLump{
	//解析
	public List<ExtclkObject> analysisExtclk(byte[] command, CXNEObject CXNEObject){
		List<ExtclkObject> list =new ArrayList<ExtclkObject>();
		List<ExtclkObject> listtod =new ArrayList<ExtclkObject>();
		List<ExtclkObject> listex =new ArrayList<ExtclkObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
//		list = super.analysisTabble("extclk", t);
		List<byte[]> lbe = new ArrayList<byte[]>(2);
		bytedelete0x00(t,lbe);
		byte[] btop = lbe.get(0);
		if(btop != null){listtod = super.analysisTabble("extclk", btop); }
		byte[] bex = lbe.get(1);
		if(bex != null){listex = super.analysisTabble("extclk", bex); }
		list.addAll(listtod);
		list.addAll(listex);
//for(ClockObject r : clocklist){
//	System.out.println(" -- ring = "+r.toString());
//}		
		
		return list;
	}
	
	/**if ring==null then select all **/
	//生成查询命令                          
	public byte[] selectExtclk(ExtclkObject extcl, int session, int seqid){
		String pathr ="ne/interfaces/extclk" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/extclk"));
		cxtOpItems.add(get(s, 2)) ;
		cxtOpItems.add(cd("ne/interfaces/tod"));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//创建
	public byte[] createExtclk(ExtclkObject extcl, int session, int seqid){
		return new byte[]{};
	}
	//生成更新 update命令
	public byte[] updateExtclk(ExtclkObject extcl, int session, int seqid){
		if(extcl==null)return new byte[]{};
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,extcl.getAdmin()));
		
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
		
		cxtOpItems.add(begin(3));		
		String path ;
		if(extcl.getName().indexOf("extclk")>=0 ){
			String name = extcl.getName();
			name = name.substring(name.indexOf(".")+1, name.length());
			path = "ne/interfaces/extclk/"+name;
		}
		else if(extcl.getName().indexOf("tod")>=0 ){
			String name = extcl.getName();
			name = name.substring(name.indexOf(".")+1, name.length());
			path = "ne/interfaces/tod/"+name;
		}else{System.out.println("ptpport create error. ");
			return new byte[]{};
		}	
		cxtOpItems.add(cd(path));
		cxtOpItems.add(set("admin", extcl.getAdmin() ));
		if(extcl.getName().indexOf("extclk")>=0 ){
			//extclkmode
			if(have(extcl.getExtclkmode()))cxtOpItems.add(set("extclkmode", extcl.getExtclkmode() ));
			if(have(extcl.getImpedance()))cxtOpItems.add(set("impedance", extcl.getImpedance() ));
			if(have(extcl.getSsmslot()))cxtOpItems.add(set("ssmslot", extcl.getSsmslot() ));
		}
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);

		return command;
	}
	/**
	 * 由于命令里有多步cd然后get(null,2) 的格式,发现设备每次cd间隔11个0x00.所以人为去掉11个0x00,有助于
	 * **/
	public int bytedelete0x00(byte[] arr,List<byte[]> lb){
		int i=0;
		byte[] tem1  ;
		byte[] tem2  ;
		for(;i<arr.length;i++){
			if(arr[i] == (byte)0x00 ){
				for(int q=1; q<=14&&i<arr.length ; q++,i++){					
					
					if(i < arr.length){
						if(arr[i] != 0x00) break;
						if((arr[i]== 0x00 )&& (q == 14 )  ){
							tem1 = java.util.Arrays.copyOfRange(arr, 0,i-9);
							tem2 = java.util.Arrays.copyOfRange(arr, i+2,arr.length) ;
							System.out.println("i = "+i+" ; t1.le="+tem1.length+" ;t2.le="+tem2.length);
							lb.add(0, tem1);
							lb.add(1, tem2);
							return i;
						}
					}
					
				}
			}
		}	
		return i;
	}
	private boolean have(String s){
		if(null == s ||s.equals(""))return false;
		else{ return true;}
	}
}
