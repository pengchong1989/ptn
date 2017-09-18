package com.nms.drivechenxiao.analysis.ne;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.xc.Route;

/**
 * 查询 路由表有关操作
 * **/
public class AnalysisRoute extends CxtOpLump{
	//解析
	public List<Route> analysisAllRoute(byte[] command, CXNEObject CXNEObject){
		List<Route> routeL = new ArrayList<Route>();
		byte[] tt = command;
		int start = 49;
		int count = tt.length - 50;
		byte[] t = new byte[count];
		System.arraycopy(tt, start, t, 0, count);
		
		routeL = super.analysisTabble("route", t);
		
		return routeL;
	}
	//生成查询命令
	public byte[] getRoute(int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/route"));
		cxtOpItems.add(get(s, 2)) ;
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	


}
