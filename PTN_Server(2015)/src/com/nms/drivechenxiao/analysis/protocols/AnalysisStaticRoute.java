package com.nms.drivechenxiao.analysis.protocols;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.StaticRouteObject;
/**有关静态路由的操作
 * **/
public class AnalysisStaticRoute extends CxtOpLump{
	public List<StaticRouteObject> analysisStaticRoute(byte[] command, CXNEObject CXNEObject){
		List<StaticRouteObject> list =new ArrayList<StaticRouteObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		list = super.analysisTabble("staticRoute", t);		
		
		return list;
	}
	
	//生成查询命令
	                          
	public byte[] selectStaticRoute( int session, int seqid){
		String pathr ="ne/protocols/static_route" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] updateStaticRoute(StaticRouteObject staticRoute, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/static_route" ));
		List<CxtAtomType> listype = new ArrayList<CxtAtomType>();
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "nexthop"));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, staticRoute.getNexthop() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ifname"));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, staticRoute.getIfname() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "distance"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, staticRoute.getDistance() ));		
		
		  CxtATTable tabtype = getCxtATTable(listype.size() / 2, listype);
		
		  cxtOpItems.add(mset(staticRoute.getName(), tabtype));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] createStaticRoute(StaticRouteObject staticRoute, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/static_route" ));
		List<CxtAtomType> listype = new ArrayList<CxtAtomType>();
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "nexthop"));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, staticRoute.getNexthop() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "ifname"));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, staticRoute.getIfname() ));
		listype.add(getCxtAtomType(CxtAtomType.AT_STRING, "distance"));
		listype.add(getCxtAtomType(CxtAtomType.AT_NUM_8, staticRoute.getDistance() ));		
		
		  CxtATTable tabtype = getCxtATTable(listype.size() / 2, listype);
		
		  cxtOpItems.add(create(staticRoute.getName(), tabtype));
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}	
	//生成查询命令
    
	public byte[] deleteStaticRoute(StaticRouteObject staticRoute, int session, int seqid){
		String pathr ="ne/protocols/static_route" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(delete(staticRoute.getName())) ;
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
