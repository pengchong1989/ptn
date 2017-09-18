package com.nms.drivechenxiao.analysis.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.acn.ACNObject;
/**
 * 有关acn的操作
 * **/
public class AnalysisACN extends CxtOpLump {
	//删除
	public byte[] deleteACN(ACNObject acn , int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(4));
		cxtOpItems.add(cd("ne/interfaces/acn"));
		cxtOpItems.add(delete(acn.getIfname().substring(4))) ;
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//删除
	public byte[] deleteAllACN(List<ACNObject> Lacn , int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(4));
		cxtOpItems.add(cd("ne/interfaces/acn"));
		for(ACNObject a : Lacn){ 
			cxtOpItems.add(delete(a.getIfname())) ;
		}
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//生成查询命令
	public byte[] selectAllACN( int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/acn"));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//解析
	public List<ACNObject> analysisAcn(byte[] command){
		List<ACNObject> listAcn = new ArrayList<ACNObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		listAcn = super.analysisTabble("ACN", t);
		return listAcn;
	}
}
