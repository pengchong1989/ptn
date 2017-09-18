package com.nms.drivechenxiao.analysis.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.tunnel.TunnelObject;

public class AnalysisApscmd extends CxtOpLump {
	/**
	 * 强制倒换命令
	 * @param tunnelObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setApscmd(TunnelObject tunnelObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/tunnel/" + tunnelObject.getName() + "/protection"));
		cxtOpItems.add(set("apscmd", tunnelObject.getProtection().getApscmd()));
//System.out.println("____  name="+tunnelObject.getName()+" ; Apscmd="+tunnelObject.getProtection().getApscmd());
//   ____  name=1 ; Apscmd=forceswitch::protection
//		cxtOpItems.add(cd("ne/interfaces/tunnel/1/protection")) ;//
//		cxtOpItems.add(set("apscmd", "forceswitch::protection"));
//		                              forceswitch::protection
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
}
