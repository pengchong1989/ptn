package com.nms.drivechenxiao.analysis.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.oam.OamObject;
import com.nms.drivechenxiao.service.bean.pweth.PwEthObject;
import com.nms.drivechenxiao.service.bean.qos.QosObject;

public class AnalysisPWETH extends CxtOpLump {

	/**
	 * 创建PW
	 * 
	 * @param qosObject
	 * @param pwEthObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createPWETH(QosObject qosObject, PwEthObject pwEthObject, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String cdPath = "ne/interfaces/pweth/" + pwEthObject.getName();
		List<CxtOpItem> cxtOpItemListQOS = super.createQOS(qosObject, session, seqid);
		List<CxtOpItem> cxtOpItemListPw = createPw("create", pwEthObject, session, seqid);

		cxtOpItems.add(begin(3));
		//kk
		if(pwEthObject.isCreateQos()){
			cxtOpItems.addAll(cxtOpItemListQOS);
		}
		cxtOpItems.addAll(cxtOpItemListPw);
		if (oamObject != null) {
			List<CxtOpItem> cxtOpItemListOAM = super.createOAM("PW", cdPath, pwEthObject.getOam(), session, seqid);
			cxtOpItems.addAll(cxtOpItemListOAM);
		}
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 删除PW
	 * 
	 * @param qosObject
	 * @param pwEthObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deletePWETH(QosObject qosObject, PwEthObject pwEthObject, OamObject oamObject, int session, int seqid) {
		String cdPath = "ne/interfaces/pweth/" + pwEthObject.getName();

		List<CxtOpItem> cxtOpItemListOAM = deleteOAM("PW", cdPath, pwEthObject.getOam(), session, seqid);
		String path = "ne/interfaces/pweth";
		String name = pwEthObject.getName();

		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.addAll(cxtOpItemListOAM);
		cxtOpItems.add(cd(path));
		cxtOpItems.add(delete(name));
		if (qosObject != null && !"".equals(qosObject.getQosType())) {
			if (pwEthObject.isDeleteQos()) {
				List<CxtOpItem> cxtOpItemListQOS = deleteQOS(qosObject, session, seqid);
				cxtOpItems.addAll(cxtOpItemListQOS);
			}
		}
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 修改PW
	 * 
	 * @param qosObject
	 * @param tunnelObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updatePWETH(QosObject qosObject, PwEthObject pwEthObject, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtOpItem> cxtOpItemListPw = createPw("update", pwEthObject, session, seqid);

		cxtOpItems.add(begin(3));
		//kk
		if(pwEthObject.isCreateQos()){
			List<CxtOpItem> a = super.createQOS(qosObject, session, seqid);
			cxtOpItems.addAll(a);
		}
		cxtOpItems.addAll(cxtOpItemListPw);
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//生成查询命令
	public byte[] selectPWETH(PwEthObject pwEthObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/pweth/" + pwEthObject.getName()));
		cxtOpItems.add(get(s, 1));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//生成查询命令
	public byte[] selectAllPWETH(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/pweth"));
		cxtOpItems.add(get(s, 2));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	//解析
	public List<PwEthObject> analysisPWETH(byte[] command, CXNEObject CXNEObject) {
		List<PwEthObject> pwEthObject = new ArrayList<PwEthObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 5, 15, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 3, 32, 0, 0, 0, 1, 51, 48, 0, 0, 0, 23, 32, 0, 0, 0, 5, 95, 112, 114, 105, 118, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 6, 97, 99, 116, 105, 111, 110, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 9, 99, 97, 114, 114, 105, 101, 114, 105, 102, 19, 0, 2, 0, 1, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 2, 1, 0, 3, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 2, 1, 0, 3, 32, 0, 0, 0, 7, 105, 110, 108, 97, 98, 101, 108, 19, 0, 0, 0,
		//				31, 32, 0, 0, 0, 8, 108, 98, 108, 104, 119, 114, 101, 115, 19, 0, 0, 0, 2, 32, 0, 0, 0, 7, 108, 112, 116, 115, 116, 97, 116, 19, 0, 0, 0, 0, 32, 0, 0, 0, 10, 108, 115, 112, 113, 111, 115, 116, 121, 112, 101, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 111, 117, 116, 108, 97, 98, 101, 108, 19, 0, 0, 0, 30, 32, 0, 0, 0, 5, 111, 119, 110, 101, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 112, 101, 101, 114, 19, 20, 0, 0, 35, 32, 0, 0, 0, 10, 112, 101, 114, 112, 114, 111, 102, 105, 108, 101, 32, 0, 0, 0, 6, 109, 112, 108, 115, 46, 48, 32, 0, 0, 0, 3, 113, 111, 115, 32, 0, 0, 0, 8, 108, 108, 115, 112, 112, 119, 49, 52, 32, 0, 0, 0, 8, 113, 111, 115, 104, 119, 114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0, 3, 114, 101, 102, 19, 0, 0, 0, 0, 32, 0, 0,
		//				0, 6, 115, 100, 118, 108, 97, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 9, 115, 100, 118, 108, 97, 110, 112, 114, 105, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 116, 112, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 116, 121, 112, 101, 19, 0, 0, 0, 0, 32, 0, 0, 0, 1, 52, 48, 0, 0, 0, 23, 32, 0, 0, 0, 5, 95, 112, 114, 105, 118, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 6, 97, 99, 116, 105, 111, 110, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 9, 99, 97, 114, 114, 105, 101, 114, 105, 102, 19, 0, 2, 0, 2, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 2, 1, 0, 4, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 2, 1, 0, 4, 32, 0, 0, 0, 7,
		//				105, 110, 108, 97, 98, 101, 108, 19, 0, 0, 0, 33, 32, 0, 0, 0, 8, 108, 98, 108, 104, 119, 114, 101, 115, 19, 0, 0, 0, 2, 32, 0, 0, 0, 7, 108, 112, 116, 115, 116, 97, 116, 19, 0, 0, 0, 0, 32, 0, 0, 0, 10, 108, 115, 112, 113, 111, 115, 116, 121, 112, 101, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 111, 117, 116, 108, 97, 98, 101, 108, 19, 0, 0, 0, 32, 32, 0, 0, 0, 5, 111, 119, 110, 101, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 112, 101, 101, 114, 19, 20, 0, 0, 5, 32, 0, 0, 0, 10, 112, 101, 114, 112, 114, 111, 102, 105, 108, 101, 32, 0, 0, 0, 6, 109, 112, 108, 115, 46, 48, 32, 0, 0, 0, 3, 113, 111, 115, 32, 0, 0, 0, 8, 108, 108, 115, 112, 112, 119, 49, 57, 32, 0, 0, 0, 8, 113, 111, 115, 104, 119, 114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0,
		//				3, 114, 101, 102, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 115, 100, 118, 108, 97, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 9, 115, 100, 118, 108, 97, 110, 112, 114, 105, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 116, 112, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 116, 121, 112, 101, 19, 0, 0, 0, 0, 32, 0, 0, 0, 1, 53, 48, 0, 0, 0, 23, 32, 0, 0, 0, 5, 95, 112, 114, 105, 118, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 6, 97, 99, 116, 105, 111, 110, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 9, 99, 97, 114, 114, 105, 101, 114, 105, 102, 19, 0, 2, 0, 1, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 2, 1, 0, 5, 32, 0, 0, 0, 6, 105, 102, 110, 97,
		//				109, 101, 19, 2, 1, 0, 5, 32, 0, 0, 0, 7, 105, 110, 108, 97, 98, 101, 108, 19, 0, 0, 0, 35, 32, 0, 0, 0, 8, 108, 98, 108, 104, 119, 114, 101, 115, 19, 0, 0, 0, 2, 32, 0, 0, 0, 7, 108, 112, 116, 115, 116, 97, 116, 19, 0, 0, 0, 0, 32, 0, 0, 0, 10, 108, 115, 112, 113, 111, 115, 116, 121, 112, 101, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 111, 117, 116, 108, 97, 98, 101, 108, 19, 0, 0, 0, 34, 32, 0, 0, 0, 5, 111, 119, 110, 101, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 112, 101, 101, 114, 19, 20, 0, 0, 35, 32, 0, 0, 0, 10, 112, 101, 114, 112, 114, 111, 102, 105, 108, 101, 32, 0, 0, 0, 6, 109, 112, 108, 115, 46, 48, 32, 0, 0, 0, 3, 113, 111, 115, 32, 0, 0, 0, 8, 108, 108, 115, 112, 112, 119, 49, 56, 32, 0, 0, 0, 8, 113, 111, 115, 104, 119,
		//				114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0, 3, 114, 101, 102, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 115, 100, 118, 108, 97, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 9, 115, 100, 118, 108, 97, 110, 112, 114, 105, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 116, 112, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 116, 121, 112, 101, 19, 0, 0, 0, 0, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		pwEthObject = super.analysisTabble("ethpw", t);
		return pwEthObject;
	}

	/*
	 * 创建PW
	 */
	private List<CxtOpItem> createPw(String type, PwEthObject pwEthObject, int session, int seqid) {
		String path = "ne/interfaces/pweth";
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "peer"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pwEthObject.getPeer()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "inlabel"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pwEthObject.getInlabel()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "outlabel"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pwEthObject.getOutlabel()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "carrierif"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pwEthObject.getCarrierif()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "qos"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pwEthObject.getQos()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pwEthObject.getAdmin()));
		CxtATTable cxtATTable = getCxtATTable(6, cxtAtomTypes);

		cxtOpItems.add(cd(path));
		if ("create".equals(type)) {
			cxtOpItems.add(create(pwEthObject.getName(), cxtATTable));
		} else if ("update".equals(type)) {
			cxtOpItems.add(mset(pwEthObject.getName(), cxtATTable));
		}

		return cxtOpItems;
	}
	/**
	 * 单独修改pw的 VLAN 口（）
	 * @param pwEthObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updatePwVlan(PwEthObject pwEthObject, int session, int seqid) {
		String path = "ne/interfaces/pweth";
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "action"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, pwEthObject.getAction()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "sdvlan"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, pwEthObject.getSdvlan()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "sdvlanpri"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, pwEthObject.getSdvlanpri()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "tpid"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, pwEthObject.getTpid()));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size()/2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(path));
		cxtOpItems.add(mset(pwEthObject.getName(), cxtATTable));
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	
}
