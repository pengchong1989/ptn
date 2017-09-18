package com.nms.drivechenxiao.analysis.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.lag.LagObject;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;

public class AnalysisLag extends CxtOpLump {

	/**
	 * 创建lag
	 * @param ethList
	 * @param lagObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createLag(List<EthPortObject> ethList, LagObject lagObject, int session, int seqid) {
		return Lag("create", ethList, lagObject, session, seqid);
	}

	/**
	 * 修改lag
	 * @param ethList
	 * @param lagObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateLag(List<EthPortObject> ethList, LagObject lagObject, int session, int seqid) {
		return Lag("update", ethList, lagObject, session, seqid);
	}

	/**
	 * 删除lag
	 * @param ethList
	 * @param lagObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteLag(List<EthPortObject> ethList, LagObject lagObject, int session, int seqid) {

		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		
		cxtOpItems.add(cd("ne/interfaces/lag/"+lagObject.getName()+"/cs7"));
		cxtOpItems.add(set("cir", "0"));
		cxtOpItems.add(cd("ne/interfaces/lag/"+lagObject.getName() + "/cs6"));
		cxtOpItems.add(set("cir", "0"));
		cxtOpItems.add(cd("ne/interfaces/lag/"+lagObject.getName() + "/ef"));
		cxtOpItems.add(set("cir", "0"));
		cxtOpItems.add(cd("ne/interfaces/lag/"+lagObject.getName() + "/af4"));
		cxtOpItems.add(set("cir", "0"));
		cxtOpItems.add(cd("ne/interfaces/lag/"+lagObject.getName() + "/af3"));
		cxtOpItems.add(set("cir", "0"));
		cxtOpItems.add(cd("ne/interfaces/lag/"+lagObject.getName() + "/af2"));
		cxtOpItems.add(set("cir", "0"));
		cxtOpItems.add(cd("ne/interfaces/lag/"+lagObject.getName() + "/af1"));
		cxtOpItems.add(set("cir", "0"));
//		cxtOpItems.add(cd("ne/interfaces/lag/"+lagObject.getName() + "/be"));
//		cxtOpItems.add(set("yellowwredstart", "0"));
		
		for (int i = 0; i < ethList.size(); i++) {
			cxtOpItems.add(cd("ne/interfaces/eth/" + ethList.get(i).getName()));
			cxtOpItems.add(delete("lag"));
		}		
		
		cxtOpItems.add(cd("ne/interfaces/lag"));
		cxtOpItems.add(delete(lagObject.getName()));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 查询lag
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] selectLag(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/lag"));
		cxtOpItems.add(get(s, 2));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 解析lag
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public List<LagObject> analysisLag(byte[] command, CXNEObject CXNEObject) {
		List<LagObject> lagObject = new ArrayList<LagObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 8, -122, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 1, 32, 0, 0, 0, 1, 49, 48, 0, 0, 0, 34, 32, 0, 0, 0, 6, 95, 99, 97, 99, 104, 101, 48, 0, 0, 0, 0, 32, 0, 0, 0, 6, 97, 99, 116, 105, 118, 101, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 3, 97, 102, 49, 48, 0, 0, 0, 11, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0,
		//				0, 96, 32, 0, 0, 0, 5, 109, 97, 120, 98, 119, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, 96, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 64, 32, 0, 0, 0, 3, 97, 102, 50, 48, 0, 0, 0, 11, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119,
		//				114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 5, 109, 97, 120, 98, 119, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, 96, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 64, 32, 0, 0, 0, 3, 97, 102, 51, 48, 0, 0, 0, 11, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0,
		//				32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 5, 109, 97, 120, 98, 119, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, 96, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114,
		//				101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 64, 32, 0, 0, 0, 3, 97, 102, 52, 48, 0, 0, 0, 11, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 5, 109, 97, 120, 98, 119, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0,
		//				13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, 96, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 64, 32, 0, 0, 0, 14, 97, 114, 112, 112, 114, 111, 116, 111, 95, 116, 111, 99, 112, 117, 1, 0, 32, 0, 0, 0, 2, 98, 101, 48, 0, 0, 0, 5, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -64, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, -128, 32, 0, 0, 0, 3, 99, 115, 54,
		//				48, 0, 0, 0, 6, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 3, 99, 115, 55, 48, 0, 0, 0, 6, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101,
		//				101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 15, 100, 104, 99, 112, 112, 114, 111, 116, 111, 95, 116, 111, 99, 112, 117, 1, 0, 32, 0, 0, 0, 6, 100, 117, 97, 108, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 2, 101, 102, 48, 0, 0, 0, 6, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96,
		//				32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 8, 102, 114, 97, 109, 101, 108, 101, 110, 19, 0, 0, 5, -18, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 0, 0, 1, 1, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 0, 0, 1, 1, 32, 0, 0, 0, 9, 105, 115, 111, 108, 97, 116, 105, 111, 110, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 105, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 3, 109, 97, 99, 19, 20, 0, 0, -54, 32, 0, 0, 0, 6, 109, 101, 109, 98, 101, 114, 48, 0, 0, 0, 2, 19, 8, 18, 1, 3, 19, 8, 18, 1, 3, 19, 8, 18, 1, 4, 19, 8, 18, 1, 4, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 1, 32, 0, 0, 0, 5, 111, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 112, 101, 114, 109, 105, 116, 112, 107,
		//				116, 108, 111, 111, 112, 1, 1, 32, 0, 0, 0, 6, 112, 111, 114, 116, 97, 99, 1, 0, 32, 0, 0, 0, 3, 112, 115, 99, 19, 0, 0, 0, 1, 32, 0, 0, 0, 7, 114, 101, 99, 111, 118, 101, 114, 1, 1, 32, 0, 0, 0, 3, 114, 101, 102, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 114, 111, 108, 101, 19, 0, 0, 0, 1, 32, 0, 0, 0, 7, 115, 99, 104, 109, 111, 100, 101, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 116, 121, 112, 101, 19, 0, 0, 0, 1, 32, 0, 0, 0, 3, 117, 110, 105, 48, 0, 0, 0, 18, 32, 0, 0, 0, 3, 97, 99, 108, 32, 0, 0, 0, 0, 32, 0, 0, 0, 10, 98, 99, 97, 115, 116, 108, 105, 109, 105, 116, 19, -1, -1, -1, -1, 32, 0, 0, 0, 11, 99, 111, 115, 50, 118, 108, 97, 110, 112, 114, 105, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 100, 108, 102, 108, 105, 109, 105, 116, 19, -1, -1, -1, -1, 32, 0, 0, 0, 9, 101, 103, 98, 119, 108, 105,
		//				109, 105, 116, 19, -1, -1, -1, -1, 32, 0, 0, 0, 5, 101, 110, 99, 97, 112, 19, 0, 0, 0, 1, 32, 0, 0, 0, 8, 105, 99, 108, 114, 109, 111, 100, 101, 19, 0, 0, 0, 0, 32, 0, 0, 0, 9, 105, 110, 98, 119, 108, 105, 109, 105, 116, 19, -1, -1, -1, -1, 32, 0, 0, 0, 7, 108, 112, 116, 115, 116, 97, 116, 19, 0, 0, 0, 0, 32, 0, 0, 0, 9, 108, 112, 116, 115, 116, 97, 116, 98, 121, 19, 0, 0, 0, 1, 32, 0, 0, 0, 10, 109, 99, 97, 115, 116, 108, 105, 109, 105, 116, 19, -1, -1, -1, -1, 32, 0, 0, 0, 8, 111, 99, 108, 114, 109, 111, 100, 101, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 112, 118, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 5, 112, 118, 112, 114, 105, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 115, 100, 116, 112, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 116, 112, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 118,
		//				108, 97, 110, 109, 111, 100, 101, 19, 0, 0, 0, 3, 32, 0, 0, 0, 11, 118, 108, 97, 110, 112, 114, 105, 50, 99, 110, 103, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 119, 111, 114, 107, 19, 0, 0, 0, 0, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		lagObject = super.analysisTabble("lag", t);
		return lagObject;
	}

	private byte[] Lag(String type, List<EthPortObject> ethList, LagObject lagObject, int session, int seqid) {
		EthPortObject ethPort = new EthPortObject();
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypesUni = new ArrayList<CxtAtomType>();
		//		cxtAtomTypesPort.add(getCxtAtomType(CxtAtomType.AT_STRING, "wtrtime"));
		//		cxtAtomTypesPort.add(getCxtAtomType(CxtAtomType.AT_STRING, ethPort.getLag().getWtrtime()));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_STRING, "pvid"));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_NUM_32, lagObject.getUni().getPvid()));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_STRING, "pvpri"));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_NUM_32, lagObject.getUni().getPvpri()));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_STRING, "encap"));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_NUM_32, lagObject.getUni().getEncap()));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_STRING, "sdtpid"));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_NUM_32, lagObject.getUni().getSdtpid()));
		//		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_STRING, "tpid"));
		//		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_NUM_32, lagObject.getUni().getTpid()));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanmode"));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_NUM_32, lagObject.getUni().getVlanmode()));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_STRING, "bcastlimit"));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_NUM_32, lagObject.getUni().getBcastlimit()));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_STRING, "dlflimit"));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_NUM_32, lagObject.getUni().getDlflimit()));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_STRING, "mcastlimit"));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_NUM_32, lagObject.getUni().getMcastlimit()));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_STRING, "inbwlimit"));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_NUM_32, lagObject.getUni().getInbwlimit()));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_STRING, "egbwlimit"));
		cxtAtomTypesUni.add(getCxtAtomType(CxtAtomType.AT_NUM_32, lagObject.getUni().getEgbwlimit()));
		CxtATTable cxtATTableUni = getCxtATTable(cxtAtomTypesUni.size() / 2, cxtAtomTypesUni);

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "psc"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_8, lagObject.getPsc()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "role"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, lagObject.getRole()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, lagObject.getAdmin()));
		//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "desc"));
		//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, lagObject.getDesc()));
		//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "mac"));
		//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_64, lagObject.getMac()));
		//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "schmode"));
		//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, lagObject.getSchmode()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "recover"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, lagObject.getRecover()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "permitpktloop"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, lagObject.getPermitpktloop()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "framelen"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, lagObject.getFramelen()));
		if ("uni".equals(lagObject.getRole())) {
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "uni"));
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTableUni));
		}
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		List<CxtAtomType> cxtAtomTypesPort = new ArrayList<CxtAtomType>();
		//		cxtAtomTypesPort.add(getCxtAtomType(CxtAtomType.AT_STRING, "wtrtime"));
		//		cxtAtomTypesPort.add(getCxtAtomType(CxtAtomType.AT_STRING, ethPort.getLag().getWtrtime()));
		cxtAtomTypesPort.add(getCxtAtomType(CxtAtomType.AT_STRING, "lagid"));
		cxtAtomTypesPort.add(getCxtAtomType(CxtAtomType.AT_STRING, lagObject.getName()));
		CxtATTable cxtATTablePort = getCxtATTable(cxtAtomTypesPort.size() / 2, cxtAtomTypesPort);

		cxtOpItems.add(begin(3));
		if ("create".equals(type)) {
			cxtOpItems.add(cd("ne/interfaces/lag"));
			cxtOpItems.add(create(lagObject.getName(), cxtATTable));
			for (int i = 0; i < ethList.size(); i++) {
				ethPort = ethList.get(i);
				cxtOpItems.add(cd("ne/interfaces/eth/" + ethPort.getName()));
				cxtOpItems.add(create("lag", cxtATTablePort));
			}
		} else if ("update".equals(type)) {
			cxtOpItems.add(cd("ne/interfaces/lag"));
			cxtOpItems.add(mset(lagObject.getName(), cxtATTable));
			for (int i = 0; i < lagObject.getMenber().getPortName().size(); i++) {
				String ethName = lagObject.getMenber().getPortName().get(i);
				cxtOpItems.add(cd("ne/interfaces/eth/" + ethName));
				cxtOpItems.add(delete("lag"));
			}
			for (int i = 0; i < ethList.size(); i++) {
				ethPort = ethList.get(i);
				cxtOpItems.add(cd("ne/interfaces/eth/" + ethPort.getName()));
				cxtOpItems.add(create("lag", cxtATTablePort));
			}
		}

		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

}
