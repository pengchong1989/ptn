package com.nms.drivechenxiao.analysis.tool;

import java.util.List;

import com.nms.drivechenxiao.analysis.interfaces.AnalysisQOS;
import com.nms.drivechenxiao.analysis.oam.AnalysisOAM;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.oam.OamObject;
import com.nms.drivechenxiao.service.bean.qos.QosObject;
/**
 * 基本类型
 * **/
public class CxtOpLump extends CoreOper {

	protected CxtOpItem begin(int i) {
		CxtAtomType op1Attr1 = getCxtAtomType(CxtAtomType.AT_NUM_32, i);
		CxtAtomType op1Attr2 = null;
		return getCxtOpItem(1, op1Attr1, op1Attr2);
	}

	protected CxtOpItem cd(String path) {
		CxtAtomType op2Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, path);
		CxtAtomType op2Attr2 = null;
		return getCxtOpItem(2, op2Attr1, op2Attr2);
	}

	protected CxtOpItem set(String attriName, String value) {
		CxtAtomType op4Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, attriName);
		if ("".equals(value) || value == null) {
			value = "0";
		}
		CxtAtomType op4Attr2 = getCxtAtomType(CxtAtomType.AT_STRING, value);
		return getCxtOpItem(4, op4Attr1, op4Attr2);
	}
	protected CxtOpItem setmac(String attriName, long value) {
		CxtAtomType op4Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, attriName);
		
		CxtAtomType op4Attr2 = getCxtAtomType(CxtAtomType.AT_NUM_D, value);
		return getCxtOpItem(4, op4Attr1, op4Attr2);
	}
	protected CxtOpItem setNum(String attriName, String value) {
		CxtAtomType op4Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, attriName);
		if ("".equals(value) || value == null) {
			value = "0";
		}
		CxtAtomType op4Attr2 = getCxtAtomType(CxtAtomType.AT_NUM_32, value);
		return getCxtOpItem(4, op4Attr1, op4Attr2);
	}

	protected CxtOpItem mset(String nodeName, CxtATTable cxtTabble) {
		CxtAtomType op5Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, nodeName);
		CxtAtomType op5Attr2 = null;
		if (cxtTabble != null) {
			op5Attr2 = getCxtAtomType(CxtAtomType.AT_TABLE, cxtTabble);
		}
		return getCxtOpItem(5, op5Attr1, op5Attr2);
	}

	protected CxtOpItem create(String nodeName, CxtATTable cxtTabble) {
		CxtAtomType op6Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, nodeName);
		CxtAtomType op6Attr2 = null;
		if (cxtTabble != null) {
			op6Attr2 = getCxtAtomType(CxtAtomType.AT_TABLE, cxtTabble);
		}
		return getCxtOpItem(6, op6Attr1, op6Attr2);
	}

	protected CxtOpItem replace(String nodeName, CxtATTable cxtTabble) {
		CxtAtomType op7Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, nodeName);
		CxtAtomType op7Attr2 = null;
		if (cxtTabble != null) {
			op7Attr2 = getCxtAtomType(CxtAtomType.AT_TABLE, cxtTabble);
		}
		return getCxtOpItem(7, op7Attr1, op7Attr2);
	}

	protected CxtOpItem delete(String attriName) {
		CxtAtomType op8Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, attriName);
		CxtAtomType op8Attr2 = null;
		return getCxtOpItem(8, op8Attr1, op8Attr2);
	}

	protected CxtOpItem commit() {
		CxtAtomType op11Attr1 = getCxtAtomType(CxtAtomType.AT_NUM_32, 2);
		CxtAtomType op11Attr2 = getCxtAtomType(CxtAtomType.AT_STRING, "$e6590c1e-ad7e-4efd-a28e-9c1b6aaba61d");
		return getCxtOpItem(11, op11Attr1, op11Attr2);
	}

	protected CxtOpItem get(String attriName, int level) {
		CxtAtomType op14Attr1 = null;
		if (attriName != null) {
			op14Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, attriName);
		}
		CxtAtomType op14Attr2 = getCxtAtomType(CxtAtomType.AT_NUM_32, level);
		return getCxtOpItem(14, op14Attr1, op14Attr2);
	}
	protected CxtOpItem get(int ge, int level) {
		CxtAtomType op14Attr1 = null;
//		if (attriName != null) {
//			op14Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, attriName);
//		}
		CxtAtomType op14Attr2 = getCxtAtomType(CxtAtomType.AT_NUM_32, level);
		return getCxtOpItem(14, op14Attr1, op14Attr2);
	}

	protected CxtOpItem get(CxtATTable cxtATTable, int level) {
		CxtAtomType op14Attr1 = null;
		if (cxtATTable != null) {
			op14Attr1 = getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTable);
		}
		CxtAtomType op14Attr2 = getCxtAtomType(CxtAtomType.AT_NUM_32, level);
		return getCxtOpItem(14, op14Attr1, op14Attr2);
	}

	protected CxtOpItem callAlmsvr(CxtATTable cxtTabble) {
		CxtAtomType op4Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, "ne/proc/almsvr.getall");
		CxtAtomType op7Attr2 = null;
		if (cxtTabble != null) {
			op7Attr2 = getCxtAtomType(CxtAtomType.AT_TABLE, cxtTabble);
		}
		return getCxtOpItem(10, op4Attr1, op7Attr2);
	}
/** add by stones for exe comd . 20130905 
 * **/	
	protected CxtOpItem acallproc(CxtATTable cxtTabble) {
//		CxtAtomType op4Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, proc);//"ne/proc/almsvr.getall"
		CxtOpItem cxtOpItem = new CxtOpItem();
		cxtOpItem.setCxtAtomType2(getCxtAtomType(CxtAtomType.AT_TABLE, cxtTabble));
		return cxtOpItem;
	}
	protected CxtOpItem execute(String comd) {
		CxtAtomType op2Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, comd);
		CxtAtomType op2Attr2 = null;
		return getCxtOpItem(9, op2Attr1, op2Attr2);
	}
	/** add by stones for exe comd . 20130905 
	 * **/	
	protected CxtOpItem call(String comd,String comd2) {
			CxtAtomType op2Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, comd);
			CxtAtomType op2Attr2 = getCxtAtomType(CxtAtomType.AT_STRING, comd2);
			return getCxtOpItem(10, op2Attr1, op2Attr2);
	}protected CxtOpItem call(String comd) {
		CxtAtomType op2Attr1 = getCxtAtomType(CxtAtomType.AT_STRING, comd);
		CxtAtomType op2Attr2 = null;
		return getCxtOpItem(10, op2Attr1, op2Attr2);
}
	/*
	 * 创建QOS
	 */
	protected List<CxtOpItem> createQOS(QosObject qosObject, int session, int seqid) {
		AnalysisQOS analysisQOS = new AnalysisQOS();
		List<CxtOpItem> CxtOpItems = analysisQOS.createQOS(qosObject, session, seqid);
		return CxtOpItems;
	}

	/*
	 * 创建OAM
	 */
	protected List<CxtOpItem> createOAM(String type, String path, OamObject oamObject, int session, int seqid) {
		AnalysisOAM analysisOAM = new AnalysisOAM();
		List<CxtOpItem> CxtOpItems = analysisOAM.createOAM(type, path, oamObject, session, seqid);
		return CxtOpItems;
	}

	/*
	 * 删除QOS
	 */
	protected List<CxtOpItem> deleteQOS(QosObject qosObject, int session, int seqid) {
		AnalysisQOS analysisQOS = new AnalysisQOS();
		List<CxtOpItem> CxtOpItems = analysisQOS.deleteQOS(qosObject, session, seqid);
		return CxtOpItems;
	}

	/*
	 * 删除OAM
	 */
	protected List<CxtOpItem> deleteOAM(String type, String cdPath, OamObject oamObject, int session, int seqid) {
		AnalysisOAM analysisOAM = new AnalysisOAM();
		List<CxtOpItem> CxtOpItems = analysisOAM.deleteOAM(type, cdPath, oamObject, session, seqid);
		return CxtOpItems;
	}

	/*
	 * 修改OAM
	 */
	protected List<CxtOpItem> updateOAM(String type, String cdPath, OamObject oamObject, int session, int seqid) {
		AnalysisOAM analysisOAM = new AnalysisOAM();
		List<CxtOpItem> CxtOpItems = analysisOAM.updateOAM(type, cdPath, oamObject, session, seqid);
		return CxtOpItems;
	}

	/*
	 * 修改qos
	 */
	protected List<CxtOpItem> updateQOS(QosObject qosObject, int session, int seqid) {
		AnalysisQOS analysisQOS = new AnalysisQOS();
		List<CxtOpItem> CxtOpItems = analysisQOS.updateQOS(qosObject, session, seqid);
		return CxtOpItems;
	}

}
