package com.nms.drivechenxiao.analysis;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.almcfg.AlmObject;
import com.nms.drivechenxiao.service.bean.almcfg.AlmcfgObject;
/**
 * 配置修改 告警设置
 * 路径: ne/almcfg
 * **/
public class AnalysisAlmcfg extends CxtOpLump{
/**紧急告警 = 101
 * 主要告警 = 100
 * 次要告警 = 011
 * 提示告警 = 010
 * 未确定告警 = 001
1e93 次要告警,否,9,15,
1111 01001 0 011
1e95 紧急告警,否,9,15,
1111 01001 0 101
1e94 主要告警,否,9,15,
1111 01001 0 100
1e92 提示告警,否,9,15,
1111 01001 0 010
1e91 未确定告警,否,9,15,
1111 010010 0 001
 * **/
	/**
	 * 查询所有告警设置
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] getGetAll(int session, int seqid) {
		String pathr ="ne/almcfg" ;
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(pathr));
		cxtOpItems.add(get(s, 2)) ;
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 解析查询告警设置
	 * 
	 * @param command
	 * @return
	 */
	public List<AlmcfgObject> analysisAlmcfg(byte[] command, CXNEObject CXNEObject) {
//System.out.println("!!! alarmselect !!! = "+CoreOper.print16String(command));		
		byte[] tt = command;
		List<AlmcfgObject> almcfgObjectList = new ArrayList<AlmcfgObject>();
		int start = 49;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		almcfgObjectList = super.analysisTabble("almcfg", t);
		return almcfgObjectList;
	}	
	/**
	 * 告警设置
	 * 在设置告警过程中,需要将AlmcfgObject里面属性的值赋予到 AlmObject中
	 * 然后调用 setNumberValue(*,*,*,*,*)或者 getNumberValueAlm()方法
	 * @param command
	 * @return
	 */
	public byte[] updateAlmcfg(AlmObject alm, int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/almcfg"));
		cxtOpItems.add(set(alm.getName(), alm.getNumberValue()));
		
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
}
