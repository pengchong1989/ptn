package com.nms.snmp.ninteface.framework.trap;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;

import com.nms.snmp.ninteface.framework.SnmpConfig;
import com.nms.snmp.ninteface.mib.DataxHeartBeatMib;
import com.nms.ui.manager.ExceptionManage;

public class HeartBeatThread extends Thread {
	private static Logger LOG = Logger.getLogger(HeartBeatThread.class.getName());
	public void run() {
		try {
			sleep(5000L);
		} catch (Exception e1) {
			ExceptionManage.dispose(e1,this.getClass());
		}

		while (true) {
			try {
				int interval = SnmpConfig.getInstanse().getHeartBeatInterval();
				sleep(interval*1000);
				pushHeartBeat();
			} catch (Exception e1) {
				ExceptionManage.dispose(e1,this.getClass());
			}
		}
	}

	private void pushHeartBeat() {
		String emsName = SnmpConfig.getInstanse().getsnmpEmsName();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String strCurrentTime = sdf.format(date);
		int interval = SnmpConfig.getInstanse().getHeartBeatInterval();
		
		DataxHeartBeatMib  heartBeatMib = SnmpNotifyMgr.getInstance().getAgent().getDataxHeartBeatMib();
		
		OID systemNameOid = new OID(heartBeatMib.oidTrapVarSystemName);
		systemNameOid.append(heartBeatMib.colSystemName);
		
		OID sendTimeOid = new OID(heartBeatMib.oidTrapVarSendTime);
		sendTimeOid.append(heartBeatMib.colSendTime);
		
		OID sendInterValOid = new OID(heartBeatMib.oidTrapVarSendInterVal);
		sendInterValOid.append(heartBeatMib.colSendInterVal);
		
		VariableBinding[] vbs = new VariableBinding[3];
		vbs[0] = new VariableBinding(systemNameOid, new OctetString(emsName));
		vbs[1] = new VariableBinding(sendTimeOid, new OctetString(strCurrentTime));
		vbs[2] = new VariableBinding(sendInterValOid, new Integer32(interval));
				
//		heartBeatMib.dataxHeartBeatEvent(SnmpNotifyMgr.getInstance().getAgent().getNotificationOriginator(), new OctetString(), vbs);
//		LOG.info("[pushHeartBeat] successfully");
	}

}
