package com.nms.drivechenxiao.analysis.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcQosObject;
import com.nms.drivechenxiao.service.bean.qos.QosObject;
import com.nms.drivechenxiao.service.bean.qos.eelsp.EELSPQosObject;
import com.nms.drivechenxiao.service.bean.qos.elsp.ELSPQosObject;
import com.nms.drivechenxiao.service.bean.qos.llsp.LLSPQosObject;

public class AnalysisQOS extends CxtOpLump {

	/**
	 * 单独创建QOS
	 * @param qosObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createQOSByte(QosObject qosObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = createQOS(qosObject, session, seqid);
		cxtOpItems.add(0, begin(3));
		cxtOpItems.add(commit());
		return cxtOpItemsToCommand(cxtOpItems, session, seqid);
	}

	/**
	 * 单独删除QOS
	 * @param qosObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteQOSByte(QosObject qosObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = deleteQOS(qosObject, session, seqid);
		cxtOpItems.add(0, begin(3));
		cxtOpItems.add(commit());
		return cxtOpItemsToCommand(cxtOpItems, session, seqid);
	}

	/**
	 * 单独修改QOS
	 * @param qosObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateQOSByte(QosObject qosObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = updateQOS(qosObject, session, seqid);
		cxtOpItems.add(0, begin(3));
		cxtOpItems.add(commit());
		return cxtOpItemsToCommand(cxtOpItems, session, seqid);
	}

	/**
	 * 单独创建ACQOS
	 * @param acQosObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createACQOSByte(AcQosObject acQosObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = createACQOS(acQosObject, session, seqid);
		cxtOpItems.add(0, begin(3));
		cxtOpItems.add(commit());
		return cxtOpItemsToCommand(cxtOpItems, session, seqid);
	}

	/**
	 * 单独修改ACQOS
	 * @param acQosObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateACQOSByte(AcQosObject acQosObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = updateACQOS(acQosObject, session, seqid);
		cxtOpItems.add(0, begin(3));
		cxtOpItems.add(commit());
		return cxtOpItemsToCommand(cxtOpItems, session, seqid);
	}

	/**
	 * 单独删除ACQOS
	 * @param acQosObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteACQOSByte(AcQosObject acQosObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = deleteACQOS(acQosObject, session, seqid);
		cxtOpItems.add(0, begin(3));
		cxtOpItems.add(commit());
		return cxtOpItemsToCommand(cxtOpItems, session, seqid);
	}

	/**
	 * 创建QOS
	 * 
	 * @param qosObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public List<CxtOpItem> createQOS(QosObject qosObject, int session, int seqid) {
		List<CxtOpItem> CxtOpItems = null;
		String type = qosObject.getQosType();
		if (type.equals("EELSP")) {
			CxtOpItems = createQOS("create", (EELSPQosObject) qosObject, session, seqid);
		} else if (type.equals("ELSP")) {
			CxtOpItems = createQOS("create", (ELSPQosObject) qosObject, session, seqid);
		} else if (type.equals("LLSP")) {
			CxtOpItems = createQOS("create", (LLSPQosObject) qosObject, session, seqid);
		}
		return CxtOpItems;
	}

	/**
	 * 删除QOS
	 * 
	 * @param qosObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public List<CxtOpItem> deleteQOS(QosObject qosObject, int session, int seqid) {
		String path = "ne/pmap/mpls";
		String type = qosObject.getQosType();
		String name = "";

		if (type.equals("EELSP")) {
			name = ((EELSPQosObject) qosObject).getName();
		} else if (type.equals("ELSP")) {
			name = ((ELSPQosObject) qosObject).getName();
		} else if (type.equals("LLSP")) {
			name = ((LLSPQosObject) qosObject).getName();
		}

		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(cd(path));
		cxtOpItems.add(delete(name));

		return cxtOpItems;
	}

	/**
	 * 修改QOS
	 * 
	 * @param qosObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public List<CxtOpItem> updateQOS(QosObject qosObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = null;
		if (qosObject.getQosType().equals("EELSP")) {
			cxtOpItems = createQOS("update", (EELSPQosObject) qosObject, session, seqid);
		} else if (qosObject.getQosType().equals("ELSP")) {
			cxtOpItems = createQOS("update", (ELSPQosObject) qosObject, session, seqid);
		} else if (qosObject.getQosType().equals("LLSP")) {
			cxtOpItems = createQOS("update", (LLSPQosObject) qosObject, session, seqid);
		}
		return cxtOpItems;
	}

	public byte[] selectQOSByte(String name, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/pmap/mpls"));
		cxtOpItems.add(get(name, 1));
		return cxtOpItemsToCommand(cxtOpItems, session, seqid);
	}

	public byte[] selectAllQOSByte(int session, int seqid) {
		String s = null;
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/pmap/mpls"));
		cxtOpItems.add(get(s, 1));
		return cxtOpItemsToCommand(cxtOpItems, session, seqid);
	}

	public List<QosObject> analysisQos(byte[] command, CXNEObject CXNEObject) {
		List<QosObject> QosObject = new ArrayList<QosObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 6, -95, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 8, 32, 0, 0, 0, 7, 108, 108, 115, 112, 112, 119, 50, 48, 0, 0, 0, 12, 32, 0, 0, 0, 6, 95, 99, 97, 99, 104, 101, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 3, 99, 111, 115, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 105, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 99, 105, 114, 19, 0, 0, 12, -128, 32, 0, 0, 0, 4, 105, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 101, 105, 114, 19, 0, 0, 12, -128, 32, 0, 0, 0, 4, 105, 112, 105, 114, 19, 0, 0, 25, 0, 32, 0, 0, 0, 4, 111, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 111, 99, 105, 114, 19,
		//				0, 0, 12, -128, 32, 0, 0, 0, 4, 111, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 111, 101, 105, 114, 19, 0, 0, 12, -128, 32, 0, 0, 0, 4, 111, 112, 105, 114, 19, 0, 0, 25, 0, 32, 0, 0, 0, 11, 108, 108, 115, 112, 116, 117, 110, 110, 101, 108, 49, 48, 0, 0, 0, 12, 32, 0, 0, 0, 6, 95, 99, 97, 99, 104, 101, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 3, 99, 111, 115, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 105, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 99, 105, 114, 19, 0, 0, 12, -128, 32, 0, 0, 0, 4, 105, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 101, 105, 114, 19, 0, 0, 12, -128, 32, 0, 0, 0, 4, 105, 112, 105, 114, 19, 0, 0, 25, 0, 32, 0, 0, 0, 4, 111, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 111, 99,
		//				105, 114, 19, 0, 0, 12, -128, 32, 0, 0, 0, 4, 111, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 111, 101, 105, 114, 19, 0, 0, 12, -128, 32, 0, 0, 0, 4, 111, 112, 105, 114, 19, 0, 0, 25, 0, 32, 0, 0, 0, 12, 108, 108, 115, 112, 116, 117, 110, 110, 101, 108, 50, 48, 48, 0, 0, 0, 12, 32, 0, 0, 0, 6, 95, 99, 97, 99, 104, 101, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 3, 99, 111, 115, 19, 0, 0, 0, 5, 32, 0, 0, 0, 4, 105, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 99, 105, 114, 19, 0, 0, 8, 0, 32, 0, 0, 0, 4, 105, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 101, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 105, 112, 105, 114, 19, 0, 0, 8, 0, 32, 0, 0, 0, 4, 111, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4,
		//				111, 99, 105, 114, 19, 0, 0, 8, 0, 32, 0, 0, 0, 4, 111, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 111, 101, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 112, 105, 114, 19, 0, 0, 8, 0, 32, 0, 0, 0, 12, 108, 108, 115, 112, 116, 117, 110, 110, 101, 108, 50, 57, 48, 0, 0, 0, 12, 32, 0, 0, 0, 6, 95, 99, 97, 99, 104, 101, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 3, 99, 111, 115, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 105, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 99, 105, 114, 19, 0, 0, 1, -64, 32, 0, 0, 0, 4, 105, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 101, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 105, 112, 105, 114, 19, 0, 0, 1, -64, 32, 0, 0, 0, 4, 111, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4,
		//				111, 99, 105, 114, 19, 0, 0, 1, -64, 32, 0, 0, 0, 4, 111, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 111, 101, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 112, 105, 114, 19, 0, 0, 1, -64, 32, 0, 0, 0, 12, 108, 108, 115, 112, 116, 117, 110, 110, 101, 108, 51, 48, 48, 0, 0, 0, 12, 32, 0, 0, 0, 6, 95, 99, 97, 99, 104, 101, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 3, 99, 111, 115, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 105, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 99, 105, 114, 19, 0, 0, 1, -64, 32, 0, 0, 0, 4, 105, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 101, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 105, 112, 105, 114, 19, 0, 0, 1, -64, 32, 0, 0, 0, 4, 111, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0,
		//				0, 4, 111, 99, 105, 114, 19, 0, 0, 1, -64, 32, 0, 0, 0, 4, 111, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 111, 101, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 112, 105, 114, 19, 0, 0, 1, -64, 32, 0, 0, 0, 12, 108, 108, 115, 112, 116, 117, 110, 110, 101, 108, 51, 49, 48, 0, 0, 0, 12, 32, 0, 0, 0, 6, 95, 99, 97, 99, 104, 101, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 3, 99, 111, 115, 19, 0, 0, 0, 5, 32, 0, 0, 0, 4, 105, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 105, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 101, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 105, 112, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0,
		//				0, 4, 111, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 111, 101, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 112, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 12, 108, 108, 115, 112, 116, 117, 110, 110, 101, 108, 52, 52, 48, 0, 0, 0, 12, 32, 0, 0, 0, 6, 95, 99, 97, 99, 104, 101, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 3, 99, 111, 115, 19, 0, 0, 0, 5, 32, 0, 0, 0, 4, 105, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 105, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 101, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 105, 112, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0,
		//				4, 111, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 111, 101, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 112, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 12, 108, 108, 115, 112, 116, 117, 110, 110, 101, 108, 52, 54, 48, 0, 0, 0, 12, 32, 0, 0, 0, 6, 95, 99, 97, 99, 104, 101, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 3, 99, 111, 115, 19, 0, 0, 0, 5, 32, 0, 0, 0, 4, 105, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 105, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 105, 101, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 105, 112, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4,
		//				111, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 111, 101, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 112, 105, 114, 19, 0, 0, 0, 0, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		QosObject = super.analysisTabble("qos", t);
		
//for(QosObject e:QosObject){
//			System.out.println("-----AnalysisQOS.line205 . qos = "+e.toString());
//}					
		return QosObject;
	}

	/**
	 * 创建ACQOS
	 * 
	 * @param qosObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public List<CxtOpItem> createACQOS(AcQosObject acQosObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtAtomType> cxtAtomTypes1 = new ArrayList<CxtAtomType>();
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "cos"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getCos()));
		if (acQosObject.getCos().equals("be")) {
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "eir"));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getEir()));
		} else if (acQosObject.getCos().equals("af1") || acQosObject.getCos().equals("af2") || acQosObject.getCos().equals("af3") || acQosObject.getCos().equals("af4")) {
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "cir"));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getCir()));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "eir"));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getEir()));
		} else if (acQosObject.getCos().equals("ef") || acQosObject.getCos().equals("cs6") || acQosObject.getCos().equals("cs7")) {
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "cir"));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getCir()));
		}
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "pir"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getPir()));
		CxtATTable cxtATTable1 = getCxtATTable(cxtAtomTypes1.size() / 2, cxtAtomTypes1);

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "default"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTable1));
		if(acQosObject.getXFAcQosList().size()>=1 ){
			List<AcQosObject> xfqosl = acQosObject.getXFAcQosList();
			for(AcQosObject xfqos : xfqosl){
				//--------
				if(xfqos.getCreatel2()){
					AnalysisL2 anl2 = new AnalysisL2();
					cxtOpItems.add(cd("ne/cmap"));
					cxtOpItems.add(anl2.createL2(xfqos.getL2(), session, seqid));

				}
				if(xfqos.getCreatel3()){
					AnalysisL3 anl3 = new AnalysisL3();
					cxtOpItems.add(cd("ne/cmap"));
					cxtOpItems.add(anl3.createL3(xfqos.getL3(), session, seqid));
				}
		//-------	
				List<CxtAtomType> xfcxtAtomTypes1 = new ArrayList<CxtAtomType>();
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "seq"));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, xfqos.getSeq()));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "cos"));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, xfqos.getCos()));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "cir"));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, xfqos.getCir()));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "cbs"));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, xfqos.getCbs()));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "eir"));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, xfqos.getEir()));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "ebs"));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, xfqos.getEbs()));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "coloraware"));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, xfqos.getColoraware()));

				CxtATTable xfcxtATTable1 = getCxtATTable(xfcxtAtomTypes1.size() / 2, xfcxtAtomTypes1);

				List<CxtAtomType> xfcxtAtomTypes = new ArrayList<CxtAtomType>();
				cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,xfqos.getName()));
				cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, xfcxtATTable1));
			}
		}
		
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
	
		/**
		 * 顾欣瑜_陈晓接口  9:13:46
			cr cmap l31 {srcip ='1.1.1.1'}
			cr acl a1
			cr acl/a1 l31 {action='permit'}
			set interfaces/eth/ge.4.1/uni acl a1
		 * **/
		cxtOpItems.add(cd("ne/pmap/ethac"));
		cxtOpItems.add(create(acQosObject.getName(), cxtATTable));
		
		
		return cxtOpItems;
	}
	
	/**返回命令的acqos**/
	public byte[] createByteACQOS(AcQosObject acQosObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtAtomType> cxtAtomTypes1 = new ArrayList<CxtAtomType>();
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "cos"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getCos()));
		if (acQosObject.getCos().equals("be")) {
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "eir"));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getEir()));
		} else if (acQosObject.getCos().equals("af1") || acQosObject.getCos().equals("af2") || acQosObject.getCos().equals("af3") || acQosObject.getCos().equals("af4")) {
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "cir"));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getCir()));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "eir"));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getEir()));
		} else if (acQosObject.getCos().equals("ef") || acQosObject.getCos().equals("cs6") || acQosObject.getCos().equals("cs7")) {
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "cir"));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getCir()));
		}
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "pir"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getPir()));
		CxtATTable cxtATTable1 = getCxtATTable(cxtAtomTypes1.size() / 2, cxtAtomTypes1);

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "default"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTable1));
		//--
		if(acQosObject.getXFAcQosList().size()>=1 ){
			List<AcQosObject> xfqosl = acQosObject.getXFAcQosList();
			for(AcQosObject xfqos : xfqosl){
				//--------
				if(xfqos.getCreatel2()){
					AnalysisL2 anl2 = new AnalysisL2();
					cxtOpItems.add(cd("ne/cmap"));
					cxtOpItems.add(anl2.createL2(xfqos.getL2(), session, seqid));

				}
				if(xfqos.getCreatel3()){
					AnalysisL3 anl3 = new AnalysisL3();
					cxtOpItems.add(cd("ne/cmap"));
					cxtOpItems.add(anl3.createL3(xfqos.getL3(), session, seqid));
				}
				//-------	
				List<CxtAtomType> xfcxtAtomTypes1 = new ArrayList<CxtAtomType>();
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "seq"));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, acQosObject.getSeq()));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "cos"));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getCos()));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "cir"));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, acQosObject.getCir()));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "cbs"));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, acQosObject.getCbs()));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "eir"));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, acQosObject.getEir()));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "ebs"));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, acQosObject.getEbs()));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "coloraware"));
				xfcxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getColoraware()));

				CxtATTable xfcxtATTable1 = getCxtATTable(xfcxtAtomTypes1.size() / 2, xfcxtAtomTypes1);

				List<CxtAtomType> xfcxtAtomTypes = new ArrayList<CxtAtomType>();
				cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING,xfqos.getName()));
				cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, xfcxtATTable1));
			}
		}
		
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
//--------
//		if(acQosObject.getCreatel2()){
//			AnalysisL2 anl2 = new AnalysisL2();
//			cxtOpItems.add(cd("ne/cmap"));
//			cxtOpItems.add(anl2.createL2(acQosObject.getL2(), session, seqid));
//			/**
//			 * 顾欣瑜_陈晓接口  9:13:46
//				cr cmap l31 {srcip ='1.1.1.1'}
//				cr acl a1
//				cr acl/a1 l31 {action='permit'}
//				set interfaces/eth/ge.4.1/uni acl a1
//			 * **/
//		}
//		if(acQosObject.getCreatel3()){
//			AnalysisL3 anl3 = new AnalysisL3();
//			cxtOpItems.add(cd("ne/cmap"));
//			cxtOpItems.add(anl3.createL3(acQosObject.getL3(), session, seqid));
//		}
//-------		
		cxtOpItems.add(cd("ne/pmap/ethac"));
		cxtOpItems.add(create(acQosObject.getName(), cxtATTable));
		cxtOpItems.add(commit());
		
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 修改ACQOS
	 * 
	 * @param acQosObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public List<CxtOpItem> updateACQOS(AcQosObject acQosObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtAtomType> cxtAtomTypes1 = new ArrayList<CxtAtomType>();
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "cos"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getCos()));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "cir"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getCir()));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "eir"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, acQosObject.getEir()));
		CxtATTable cxtATTable1 = getCxtATTable(cxtAtomTypes1.size() / 2, cxtAtomTypes1);

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "default"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTable1));
		CxtATTable cxtATTable = getCxtATTable(1, cxtAtomTypes);

		cxtOpItems.add(cd("ne/pmap/ethac"));
		cxtOpItems.add(mset(acQosObject.getName(), cxtATTable));

		return cxtOpItems;
	}

	/**
	 * 删除ACQOS
	 * 
	 * @param acQosObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public List<CxtOpItem> deleteACQOS(AcQosObject acQosObject, int session, int seqid) {
		String path = "ne/pmap/ethac";
		String name = (acQosObject.getName());

		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(cd(path));
		cxtOpItems.add(delete(name));

		return cxtOpItems;
	}
	public byte[] deleteByteACQOS(AcQosObject acQosObject, int session, int seqid) {
		String path = "ne/pmap/ethac";
		String name = (acQosObject.getName());

		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(cd(path));
		cxtOpItems.add(delete(name));
		if(acQosObject.isDeletel2()){
			cxtOpItems.add(cd("ne/cmap"));
			cxtOpItems.add(delete(acQosObject.getL2().getName()));
		}
		if(acQosObject.isDeletel3()){
			cxtOpItems.add(cd("ne/cmap"));
			cxtOpItems.add(delete(acQosObject.getL3().getName()));
		}
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	public byte[] selectAcQOSByte(String name, int session, int seqid) {
		String s = null;
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		//如果name为空的话 就查询全部qos  KK
		if(null == name || "".equals(name)){
			cxtOpItems.add(cd("ne/pmap/ethac/"));
		}else{
			cxtOpItems.add(cd("ne/pmap/ethac/"+name));
		}
		cxtOpItems.add(get(s, 2));
		return cxtOpItemsToCommand(cxtOpItems, session, seqid);
	}

	public List<AcQosObject> analysisAcQos(byte[] command, CXNEObject CXNEObject) {
		List<AcQosObject> acQosObject = new ArrayList<AcQosObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 0, -50, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 2, 32, 0, 0, 0, 6, 95, 99, 97, 99, 104, 101, 48, 0, 0, 0, 1, 19, 1, 1, 0, 1, 32, 0, 0, 0, 8, 102, 101, 46, 49, 46, 49, 47, 49, 32, 0, 0, 0, 7, 100, 101, 102, 97, 117, 108, 116, 48, 0, 0, 0, 8, 32, 0, 0, 0, 3, 99, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 2, -128, 32, 0, 0, 0, 10, 99, 111, 108, 111, 114, 97, 119, 97, 114, 101, 1, 0, 32, 0, 0, 0, 3, 99, 111, 115, 19, 0, 0, 0, 1, 32, 0, 0, 0, 3, 101, 98, 115, 19, -1, -1, -1, -1, 32, 0, 0, 0, 3, 101, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 3, 112, 105, 114, 19, 0, 0, 2, -128, 32, 0, 0, 0, 3, 115, 101, 113, 19, 0, 0, 0,
		//				0, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		acQosObject = super.analysisTabble("acqos", t);
		
//for(AcQosObject e:acQosObject){
//	System.out.println("-----AnalysisQOS.line320 . ACqos = "+e.toString());
//}			
		return acQosObject;
	}

	/*
	 * 创建ELSPQOS
	 */
	private List<CxtOpItem> createQOS(String type, ELSPQosObject elsp, int session, int seqid) {
		String path = "ne/pmap/mpls";
		String name = path + "/" + elsp.getName();
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		if ("create".equals(type)) {
			cxtOpItems.add(cd(path));
			cxtOpItems.add(create(elsp.getName(), null));
		}
		cxtOpItems.add(cd(name + "/cs7"));
		cxtOpItems.add(set("icir", elsp.getCs7().getIcir()));
		cxtOpItems.add(set("ocir", elsp.getCs7().getOcir()));
		cxtOpItems.add(cd(name + "/cs6"));
		cxtOpItems.add(set("icir", elsp.getCs6().getIcir()));
		cxtOpItems.add(set("ocir", elsp.getCs6().getOcir()));
		cxtOpItems.add(cd(name + "/ef"));
		cxtOpItems.add(set("icir", elsp.getEf().getIcir()));
		cxtOpItems.add(set("ocir", elsp.getEf().getOcir()));
		cxtOpItems.add(cd(name + "/af2"));
		cxtOpItems.add(set("icir", elsp.getAf2().getIcir()));
		cxtOpItems.add(set("ocir", elsp.getAf2().getOcir()));
		cxtOpItems.add(set("ieir", elsp.getAf2().getIeir()));
		cxtOpItems.add(set("oeir", elsp.getAf2().getOeir()));
		cxtOpItems.add(cd(name + "/af1"));
		cxtOpItems.add(set("icir", elsp.getAf1().getIcir()));
		cxtOpItems.add(set("ocir", elsp.getAf1().getOcir()));
		cxtOpItems.add(set("ieir", elsp.getAf1().getIeir()));
		cxtOpItems.add(set("oeir", elsp.getAf1().getOeir()));
		cxtOpItems.add(cd(name + "/af3"));
		cxtOpItems.add(set("icir", elsp.getAf2().getIcir()));
		cxtOpItems.add(set("ocir", elsp.getAf2().getOcir()));
		cxtOpItems.add(set("ieir", elsp.getAf2().getIeir()));
		cxtOpItems.add(set("oeir", elsp.getAf2().getOeir()));
		cxtOpItems.add(cd(name + "/af4"));
		cxtOpItems.add(set("icir", elsp.getAf1().getIcir()));
		cxtOpItems.add(set("ocir", elsp.getAf1().getOcir()));
		cxtOpItems.add(set("ieir", elsp.getAf1().getIeir()));
		cxtOpItems.add(set("oeir", elsp.getAf1().getOeir()));
		cxtOpItems.add(cd(name + "/be"));
		cxtOpItems.add(set("ieir", elsp.getBe().getIeir()));
		cxtOpItems.add(set("oeir", elsp.getBe().getOeir()));

		return cxtOpItems;
	}

	/*
	 * 创建LLSPQOS
	 */
	private List<CxtOpItem> createQOS(String type, LLSPQosObject llsp, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "cos"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, llsp.getCos()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "icir"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, llsp.getIcir()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "icbs"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, llsp.getIcbs()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ieir"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, llsp.getIeir()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "iebs"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, llsp.getIebs()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ocir"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, llsp.getOcir()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ocbs"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, llsp.getOcbs()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "oeir"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, llsp.getOeir()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "oebs"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, llsp.getOebs()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ipir"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, llsp.getIpir()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "opir"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, llsp.getOpir()));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(cd("ne/pmap/mpls"));
		if ("create".equals(type)) {
			cxtOpItems.add(create(llsp.getName(), cxtATTable));
		} else if ("update".equals(type)) {
			cxtOpItems.add(mset(llsp.getName(), cxtATTable));
		}

		return cxtOpItems;
	}

	/*
	 * 创建EELSPQOS
	 */
	private List<CxtOpItem> createQOS(String type, EELSPQosObject eelsp, int session, int seqid) {
		String path = "ne/pmap/mpls";
		String name = path + "/" + eelsp.getName();
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		if ("create".equals(type)) {
			cxtOpItems.add(cd(path));
			cxtOpItems.add(create(eelsp.getName(), null));
		}
		cxtOpItems.add(cd(name + "/cs7"));
		cxtOpItems.add(set("icir", eelsp.getCs7().getIcir()));
		cxtOpItems.add(set("ocir", eelsp.getCs7().getOcir()));
		cxtOpItems.add(cd(name + "/cs6"));
		cxtOpItems.add(set("icir", eelsp.getCs6().getIcir()));
		cxtOpItems.add(set("ocir", eelsp.getCs6().getOcir()));
		cxtOpItems.add(cd(name + "/ef"));
		cxtOpItems.add(set("icir", eelsp.getEf().getIcir()));
		cxtOpItems.add(set("ocir", eelsp.getEf().getOcir()));
		cxtOpItems.add(cd(name + "/af2"));
		cxtOpItems.add(set("icir", eelsp.getAf2().getIcir()));
		cxtOpItems.add(set("ocir", eelsp.getAf2().getOcir()));
		cxtOpItems.add(set("ieir", eelsp.getAf2().getIeir()));
		cxtOpItems.add(set("oeir", eelsp.getAf2().getOeir()));
		cxtOpItems.add(cd(name + "/af1"));
		cxtOpItems.add(set("icir", eelsp.getAf1().getIcir()));
		cxtOpItems.add(set("ocir", eelsp.getAf1().getOcir()));
		cxtOpItems.add(set("ieir", eelsp.getAf1().getIeir()));
		cxtOpItems.add(set("oeir", eelsp.getAf1().getOeir()));
		cxtOpItems.add(cd(name + "/be"));
		cxtOpItems.add(set("ieir", eelsp.getBe().getIeir()));
		cxtOpItems.add(set("oeir", eelsp.getBe().getOeir()));

		return cxtOpItems;
	}

}
