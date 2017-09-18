package com.nms.drivechenxiao.analysis.service;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.oam.AnalysisOAM;
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.oam.OamMipObject;
import com.nms.drivechenxiao.service.bean.qos.QosObject;
import com.nms.drivechenxiao.service.bean.xc.XcObject;

public class AnalysisXC extends CxtOpLump {

	/**
	 * 创建XC
	 * 
	 * @param xcObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createXc(XcObject xcObject, QosObject qosObject, OamMipObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems = createXc1("create", xcObject, qosObject, oamObject, session, seqid);

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 删除XC
	 * 
	 * @param xcObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteXc(XcObject xcObject, QosObject qosObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/mpls/xc"));
		cxtOpItems.add(delete(xcObject.getName()));
		if (qosObject != null && !"".equals(qosObject.getQosType())) {
			if (xcObject.isDeleteQos()) {
				List<CxtOpItem> cxtOpItemListQOS = super.deleteQOS(qosObject, session, seqid);
				cxtOpItems.addAll(cxtOpItemListQOS);
			}
		}
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 修改XC
	 * 
	 * @param xcObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateXc(XcObject xcObject, QosObject qosObject, OamMipObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems = createXc1("update", xcObject, qosObject, oamObject, session, seqid);

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] selectXc(XcObject xcObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/mpls/xc/" + xcObject.getName()));
		cxtOpItems.add(get(s, 1));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] selectAllXc(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/protocols/mpls/xc"));
		cxtOpItems.add(get(s, 3));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public List<XcObject> analysisXc(byte[] command, CXNEObject CXNEObject) {
		List<XcObject> xcObject = new ArrayList<XcObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 3, 14, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 1, 32, 0, 0, 0, 1, 51, 48, 0, 0, 0, 17, 32, 0, 0, 0, 1, 49, 48, 0, 0, 0, 10, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 0, 32, 0, 0, 0, 9, 99, 97, 114, 114, 105, 101, 114, 105, 102, 19, 8, 18, 1, 1, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 0, 32, 0, 13, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 0, 32, 0, 13, 32, 0, 0, 0, 7, 105, 110, 108, 97, 98, 101, 108, 19, 0, 0, 5, 41, 32, 0, 0, 0, 8, 108, 98, 108, 104, 119, 114, 101, 115, 19, 0, 0, 0, 2, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 5, 32, 0, 0, 0, 8, 111, 117, 116, 108, 97, 98, 101, 108, 19, 0, 0, 48,
		//				44, 32, 0, 0, 0, 10, 112, 101, 114, 112, 114, 111, 102, 105, 108, 101, 32, 0, 0, 0, 6, 109, 112, 108, 115, 46, 48, 32, 0, 0, 0, 8, 113, 111, 115, 104, 119, 114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0, 1, 50, 48, 0, 0, 0, 10, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 0, 32, 0, 0, 0, 9, 99, 97, 114, 114, 105, 101, 114, 105, 102, 19, 8, 18, 1, 4, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 0, 32, 0, 14, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 0, 32, 0, 14, 32, 0, 0, 0, 7, 105, 110, 108, 97, 98, 101, 108, 19, 0, 0, 48, -101, 32, 0, 0, 0, 8, 108, 98, 108, 104, 119, 114, 101, 115, 19, 0, 0, 0, 2, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 5, 32, 0, 0, 0, 8, 111, 117, 116, 108, 97, 98, 101, 108, 19, 0, 0, 16, 117, 32, 0, 0, 0, 10, 112, 101,
		//				114, 112, 114, 111, 102, 105, 108, 101, 32, 0, 0, 0, 6, 109, 112, 108, 115, 46, 48, 32, 0, 0, 0, 8, 113, 111, 115, 104, 119, 114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 5, 112, 116, 110, 88, 67, 32, 0, 0, 0, 6, 101, 103, 114, 101, 115, 115, 19, 20, 0, 0, 35, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 0, 1, 0, 3, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 0, 1, 0, 3, 32, 0, 0, 0, 7, 105, 110, 103, 114, 101, 115, 115, 19, 20, 0, 0, -54, 32, 0, 0, 0, 5, 108, 115, 112, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 10, 108, 115, 112, 113, 111, 115, 116, 121, 112, 101, 19, 0, 0, 0, 1, 32, 0, 0, 0, 3, 111, 97, 109, 48, 0, 0, 0, 5, 32, 0, 0, 0, 5, 104, 119, 114, 101, 115, 19, 0,
		//				0, 2, -96, 32, 0, 0, 0, 3, 108, 118, 108, 19, 0, 0, 0, 7, 32, 0, 0, 0, 5, 109, 101, 103, 105, 100, 32, 0, 0, 0, 2, 49, 51, 32, 0, 0, 0, 6, 109, 101, 112, 105, 100, 115, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 5, 109, 105, 112, 105, 100, 19, 0, 0, 0, 32, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 1, 32, 0, 0, 0, 5, 111, 119, 110, 101, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 3, 113, 111, 115, 32, 0, 0, 0, 12, 108, 108, 115, 112, 116, 117, 110, 110, 101, 108, 50, 51, 32, 0, 0, 0, 4, 114, 111, 108, 101, 19, 0, 0, 0, 0, 32, 0, 0, 0, 9, 116, 117, 110, 110, 101, 108, 100, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 9, 116, 117, 110, 110, 101, 108, 115, 105, 100, 19, 0, 0, 0, 1, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		xcObject = super.analysisTabble("xc", t);
		return xcObject;
	}

	/*
	 * 
	 */
	private List<CxtOpItem> createXc1(String type, XcObject xcObject, QosObject qosObject, OamMipObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes1 = new ArrayList<CxtAtomType>();
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "carrierif"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, xcObject.getXcIFObjects()[0].getCarrierif()));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "inlabel"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, xcObject.getXcIFObjects()[0].getInlabel()));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "outlabel"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, xcObject.getXcIFObjects()[0].getOutlabel()));
		CxtATTable cxtATTable1 = getCxtATTable(3, cxtAtomTypes1);

		List<CxtAtomType> cxtAtomTypes2 = new ArrayList<CxtAtomType>();
		cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, "carrierif"));
		cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, xcObject.getXcIFObjects()[1].getCarrierif()));
		cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, "inlabel"));
		cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_NUM_32, xcObject.getXcIFObjects()[1].getInlabel()));
		cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, "outlabel"));
		cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_NUM_32, xcObject.getXcIFObjects()[1].getOutlabel()));
		CxtATTable cxtATTable2 = getCxtATTable(3, cxtAtomTypes2);

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, xcObject.getAdmin()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ingress"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, xcObject.getIngress()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "egress"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, xcObject.getEgress()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "tunnelsid"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, xcObject.getTunnelsid()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "tunneldid"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, xcObject.getTunneldid()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "lspid"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, xcObject.getLspid()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, xcObject.getXcIFObjects()[0].getName()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTable1));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, xcObject.getXcIFObjects()[1].getName()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTable2));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "qos"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, xcObject.getQos()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "role"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, xcObject.getRole()));

		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		if ("create".equals(type)) {
			if (qosObject != null && xcObject.isCreateQos()) {
				List<CxtOpItem> a = super.createQOS(qosObject, session, seqid);
				cxtOpItems.addAll(a);
			}
		} else if ("update".equals(type)) {
			if (qosObject != null && xcObject.isCreateQos()) {
				List<CxtOpItem> a = super.createQOS(qosObject, session, seqid);
				cxtOpItems.addAll(a);
			}
		}

		cxtOpItems.add(cd("ne/protocols/mpls/xc"));
		if ("create".equals(type)) {
			cxtOpItems.add(create(xcObject.getName(), cxtATTable));
			if (oamObject != null) {
				AnalysisOAM analysisOAM = new AnalysisOAM();
				cxtOpItems.addAll(analysisOAM.createMipOAM("ne/protocols/mpls/xc/" + xcObject.getName(), oamObject, session, seqid));
			}
		} else if ("update".equals(type)) {
			cxtOpItems.add(mset(xcObject.getName(), cxtATTable));
			if (oamObject != null) {
				AnalysisOAM analysisOAM = new AnalysisOAM();
				cxtOpItems.addAll(analysisOAM.updateMipOAM("ne/protocols/mpls/xc/" + xcObject.getName(), oamObject, session, seqid));
			}
		}
		cxtOpItems.add(commit());

		return cxtOpItems;
	}
}
