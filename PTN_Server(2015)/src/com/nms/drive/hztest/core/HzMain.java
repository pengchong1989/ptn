package com.nms.drive.hztest.core;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.hztest.core.bean.CxtAtomType;
import com.nms.drive.hztest.core.bean.CxtOpBuffer;
import com.nms.drive.hztest.core.bean.CxtOpItem;
import com.nms.drive.hztest.core.ui.HzPtnMainUI;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class HzMain extends CoreOper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		HzPtnMainUI HzPtnMainUI = new HzPtnMainUI();
		HzPtnMainUI.setLocation(UiUtil.getWindowWidth(HzPtnMainUI.getWidth()), UiUtil.getWindowHeight(HzPtnMainUI.getHeight()));
//		UiUtil.uiStyle(HzPtnMainUI);
		// HzPtnMainUI.setUndecorated(true);
		// HzPtnMainUI.setExtendedState(Frame.MAXIMIZED_BOTH);
		HzPtnMainUI.setVisible(true);
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				ExceptionManage.dispose(e,HzMain.class);
			}
		}
	}

	public byte[] getLoginBytes(String user, String password, int logoutBool, int session, int seqid) {
		byte[] msg = new byte[0];
		byte[] logout = new byte[1];
		if (logoutBool == 0) {
			logout[0] = 0x00;
		} else {
			logout[0] = 0x01;
		}

		msg = arraycopy(msg, logout);
		msg = arraycopy(msg, getCxtString(user));
		msg = arraycopy(msg, getCxtString(password));
		byte[] command = getHeader(msg.length, CoreOper.ECXTMSG_REQ_LOGIN, session, seqid, true);
		command = arraycopy(command, msg);

		// print16String(command);
		return command;
	}

	public byte[] getHookNotifyBytes(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		CxtAtomType op1Attr1 = getCxtAtomType(CxtAtomType.AT_NUM_32, 3);
		CxtAtomType op1Attr2 = null;
		cxtOpItems.add(getCxtOpItem(1, op1Attr1, op1Attr2));

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "almchg"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "prtswitch"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "cfgdbchg"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "seculog"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ethlinkoam"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "oamlpb"));
		CxtAtomType op2Attr1 = getCxtAtomType(CxtAtomType.AT_TABLE, getCxtATTable(cxtAtomTypes.size(), cxtAtomTypes));
		CxtAtomType op2Attr2 = getCxtAtomType(CxtAtomType.AT_NUM_32, 1);
		cxtOpItems.add(getCxtOpItem(13, op2Attr1, op2Attr2));

		CxtAtomType op3Attr1 = getCxtAtomType(CxtAtomType.AT_NUM_32, 2);
		CxtAtomType op3Attr2 = getCxtAtomType(CxtAtomType.AT_STRING, "1b6007ab-52ae-4827-a774-bc1dfc1f9d37");
		cxtOpItems.add(getCxtOpItem(11, op3Attr1, op3Attr2));

		CxtOpBuffer cxtOpBuffer = getCxtOpBuffer(cxtOpItems.size(), cxtOpItems);
		byte[] cxtOpBufferbyte = getCxtOpBuffer(cxtOpBuffer);
		byte[] command = getHeader(cxtOpBufferbyte.length, CoreOper.ECXTMSG_REQ_OPS, session, seqid, false);
		command = arraycopy(command, cxtOpBufferbyte);

		// print16String(command);
		return command;
	}

	public byte[] getNNIBytes(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		CxtAtomType op1Attr1 = getCxtAtomType(CxtAtomType.AT_NUM_32, 3);
		CxtAtomType op1Attr2 = null;
		cxtOpItems.add(getCxtOpItem(1, op1Attr1, op1Attr2));

		CxtAtomType op2Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, "ne/interfaces/eth/ge.1.1");
		CxtAtomType op2Attr2 = null;
		cxtOpItems.add(getCxtOpItem(2, op2Attr1, op2Attr2));

		CxtAtomType op3Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, "nni");
		CxtAtomType op3Attr2 = getCxtAtomType(CxtAtomType.AT_NUM_32, 0);
		cxtOpItems.add(getCxtOpItem(14, op3Attr1, op3Attr2));

		CxtOpBuffer cxtOpBuffer = getCxtOpBuffer(cxtOpItems.size(), cxtOpItems);
		byte[] cxtOpBufferbyte = getCxtOpBuffer(cxtOpBuffer);
		byte[] command = getHeader(cxtOpBufferbyte.length, CoreOper.ECXTMSG_REQ_OPS, session, seqid, false);
		command = arraycopy(command, cxtOpBufferbyte);

		// print16String(command);
		return command;
	}

}
