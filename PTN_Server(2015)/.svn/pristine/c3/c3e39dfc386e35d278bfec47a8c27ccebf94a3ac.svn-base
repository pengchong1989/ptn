package com.nms.drivechenxiao.analysis;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.service.bean.CXNEObject;
/**登录调度的等
 * **/
public class AnalysisLogin extends CoreOper {
	public byte[] getLoginBytes(CXNEObject cXNEObject, int session, int seqid) {
		byte[] msg = new byte[0];
		byte[] logout = new byte[1];
		logout[0] = 0x00;

		msg = arraycopy(msg, logout);
		msg = arraycopy(msg, getCxtString(cXNEObject.getAdmin()));
		msg = arraycopy(msg, getCxtString(cXNEObject.getPassword()));
		byte[] command = getHeader(msg.length, CoreOper.ECXTMSG_REQ_LOGIN, session, seqid, true);
		command = arraycopy(command, msg);

		// print16String(command);
		return command;
	}

	public byte[] getLogOutBytes(CXNEObject cXNEObject, int session, int seqid) {
		byte[] msg = new byte[0];
		byte[] logout = new byte[1];
		logout[0] = 0x01;

		msg = arraycopy(msg, logout);
		msg = arraycopy(msg, getCxtString(cXNEObject.getAdmin()));
		msg = arraycopy(msg, getCxtString(cXNEObject.getPassword()));
		byte[] command = getHeader(msg.length, CoreOper.ECXTMSG_REQ_LOGIN, session, seqid, true);
		command = arraycopy(command, msg);

		// print16String(command);
		return command;
	}
}
