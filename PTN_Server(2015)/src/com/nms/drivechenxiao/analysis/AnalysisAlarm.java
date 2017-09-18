package com.nms.drivechenxiao.analysis;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.alarm.AlarmObject;
/**告警有关处理
 * **/
public class AnalysisAlarm extends CxtOpLump {

	/**
	 * 查询所有告警
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] getGetAll(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(callAlmsvr(null));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 解析查询告警
	 * 
	 * @param command
	 * @return
	 */
	public List<AlarmObject> analysisSelectAlarm(byte[] command, CXNEObject CXNEObject) {
//System.out.println("!!! alarmselect !!! = "+CoreOper.print16String(command));		
		byte[] tt = command;
		List<AlarmObject> alarmObjectList = new ArrayList<AlarmObject>();
		int start = 58;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		alarmObjectList = super.analysisTabble("alarm", t);
		return alarmObjectList;
	}

	/**
	 * 解析主动上报告警
	 * 
	 * @param command
	 * @return
	 */
	public List<AlarmObject> analysisReportAlarm(byte[] command, CXNEObject CXNEObject) {
//System.out.println("!!! alarmReport !!! = "+CoreOper.print16String(command));
		List<AlarmObject> alarmObjectList = new ArrayList<AlarmObject>();
		int count = 49;
		int start = 32;
		byte[] tt = command;
		for (int i = 0; i < tt[27]; i++) {
//System.out.println("start="+start+" ;count="+count+" ;i="+i);			
			List<AlarmObject> alarmList = new ArrayList<AlarmObject>();
			int sStart = start - 4;
			byte[] st = new byte[4];
			System.arraycopy(tt, sStart, st, 0, 4);
//System.out.println(" -- st="+CoderUtils.bytesToInt(st));
			byte[] t = new byte[count];
			System.arraycopy(tt, start, t, 0, count);
//System.out.println(" -- ==");			
			alarmList = super.analysisTabble("alarm", t);
			for (int j = 0; j < alarmList.size(); j++) {
				alarmList.get(j).setCXNEObject(CXNEObject);
				alarmList.get(j).setStatus(String.valueOf(CoderUtils.ascii2Char(CoderUtils.bytesToInt(st))));
			}
			alarmObjectList.addAll(alarmList);
//			start = start + count + 5 + 4;
			start = start + count + 1 + 4;
		}
		return alarmObjectList;
	}

}
