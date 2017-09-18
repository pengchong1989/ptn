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
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcQosObject;

public class AnalysisAC extends CxtOpLump {

	/**
	 * 创建AC
	 * 
	 * @param acObject
	 * @param acQosObject
	 * @param tunnelObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	//创建
	public byte[] createAc(EthPortObject ethPortObject, AcObject acObject, AcQosObject acQosObject, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		//		create interfaces/eth/fe.1.2 1 {mode="port_plus_spvlan",qos='vlanpri20'}   
		List<CxtOpItem> cxtOpItemListAcQos = createAcQos(acQosObject, session, seqid);
		List<CxtOpItem> cxtOpItemListAc = createAc("create", ethPortObject, acObject, session, seqid);

		cxtOpItems.add(begin(3));
		if (!acObject.getEthacout().equals("")) {
			cxtOpItems.add(cd("ne/pmap/ethac"));
			cxtOpItems.add(create(acObject.getEthacout(), null));
		}
		//kk
		if(acObject.isCreateQos()){
			cxtOpItems.addAll(cxtOpItemListAcQos);
		}
		cxtOpItems.addAll(cxtOpItemListAc);
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);

		return command;
	}
	
	/**
	 * 修改AC
	 * 
	 * @param ethPortObject
	 * @param acObject
	 * @param acQosObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateAc(EthPortObject ethPortObject, AcObject acObject, AcQosObject acQosObject, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtOpItem> cxtOpItemListAc = createAc("update", ethPortObject, acObject, session, seqid);
		
		cxtOpItems.add(begin(3));
		
		if(acObject.isCreateQos()){
			AnalysisQOS analysisQOS = new AnalysisQOS();
			List<CxtOpItem> cxtOpItemListAcQos = analysisQOS.createACQOS(acQosObject, session, seqid);
			cxtOpItems.addAll(cxtOpItemListAcQos);
		}
		
		cxtOpItems.addAll(cxtOpItemListAc);
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 删除AC
	 * 
	 * @param ethPortObject
	 * @param acObject
	 * @param acQosObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteAc(EthPortObject ethPortObject, AcObject acObject, AcQosObject acQosObject, OamObject oamObject, int session, int seqid) {
		AnalysisQOS analysisQOS = new AnalysisQOS();
		String path = "ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName();
		String name = acObject.getName();
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));	
		cxtOpItems.add(cd(path));
		cxtOpItems.add(delete(name));
		cxtOpItems.add(commit());
		if (!acObject.getEthacout().equals("")) {
			cxtOpItems.add(cd("ne/pmap/ethac"));
			cxtOpItems.add(delete(acObject.getEthacout()));
			cxtOpItems.add(commit());
		}
		if (null != acQosObject && !"".equals(acQosObject.getName())) {
			if (acObject.isDeleteQos()) {
				List<CxtOpItem> cxtOpItemListACQOS = analysisQOS.deleteACQOS(acQosObject, session, seqid);
				cxtOpItems.addAll(cxtOpItemListACQOS);
			}
		}		
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] selectAc(EthPortObject ethPortObject, AcObject acObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/eth/" + ethPortObject.getName() + "/" + acObject.getName()));
		cxtOpItems.add(get(s, 1));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] selectAc(EthPortObject ethPortObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName()));
		cxtOpItems.add(get(s, 2));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public List<AcObject> analysisAc(byte[] command, CXNEObject CXNEObject) {
		List<AcObject> acObject = new ArrayList<AcObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 11, -119, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 51, 32, 0, 0, 0, 1, 49, 48, 0, 0, 0, 23, 32, 0, 0, 0, 6, 97, 99, 116, 105, 111, 110, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 6, 99, 101, 118, 108, 97, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 6, 100, 117, 97, 108, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 10, 101, 116, 104, 105, 102, 105, 110, 100, 101, 120, 19, 8, 18, 3, 4, 32, 0, 0, 0, 5, 104, 119, 114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 104, 119, 114, 101, 115, 114, 101, 102, 32, 0, 0, 0, 31, 48, 44, 48, 44, 48, 44, 48, 44, 48,
		//				44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 1, 1, 0, 2, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 32, 0, 0, 0, 8, 103, 101, 46, 51, 46, 52, 47, 49, 32, 0, 0, 0, 10, 108, 51, 105, 105, 102, 104, 119, 114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 109, 111, 100, 101, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 1, 32, 0, 0, 0, 10, 112, 101, 114, 112, 114, 111, 102, 105, 108, 101, 32, 0, 0, 0, 6, 109, 112, 108, 115, 46, 48, 32, 0, 0, 0, 3, 113, 111, 115, 32, 0, 0, 0, 8, 118, 108, 97, 110, 112, 114, 105, 53, 32, 0, 0, 0, 8, 113, 111, 115, 104, 119, 114, 101, 115, 19, 0, 0, 1, 0, 32, 0, 0, 0, 3, 114, 101, 102, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 115, 100, 118,
		//				108, 97, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 9, 115, 100, 118, 108, 97, 110, 99, 102, 105, 19, 0, 0, 0, 0, 32, 0, 0, 0, 9, 115, 100, 118, 108, 97, 110, 112, 114, 105, 19, 0, 0, 0, 0, 32, 0, 0, 0, 7, 115, 101, 114, 118, 105, 99, 101, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 6, 115, 112, 118, 108, 97, 110, 19, 0, 0, 0, 2, 32, 0, 0, 0, 7, 118, 116, 104, 119, 114, 101, 115, 19, 0, 0, 0, 8, 32, 0, 0, 0, 6, 95, 99, 97, 99, 104, 101, 48, 0, 0, 0, 0, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 3, 97, 102, 49, 48, 0, 0, 0, 11, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, -6, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119,
		//				114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 5, 109, 97, 120, 98, 119, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, 96, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 64, 32, 0, 0, 0, 3, 97, 102, 50, 48, 0, 0, 0, 11, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0,
		//				32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 5, 109, 97, 120, 98, 119, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, 96, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114,
		//				101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 64, 32, 0, 0, 0, 3, 97, 102, 51, 48, 0, 0, 0, 11, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 5, 109, 97, 120, 98, 119, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0,
		//				13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, 96, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 64, 32, 0, 0, 0, 3, 97, 102, 52, 48, 0, 0, 0, 11, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 5, 109, 97, 120, 98, 119, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0, 0, 6, 119, 114,
		//				101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, 96, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 64, 32, 0, 0, 0, 3, 97, 108, 115, 1, 0, 32, 0, 0, 0, 8, 97, 108, 115, 100, 101, 108, 97, 121, 19, 0, 0, 0, 60, 32, 0, 0, 0, 8, 97, 108, 115, 115, 104, 116, 117, 112, 19, 0, 0, 0, 2, 32, 0, 0, 0, 14, 97, 114, 112, 112, 114, 111, 116, 111, 95, 116, 111, 99, 112, 117, 1, 0, 32, 0, 0, 0, 6, 97, 115, 112, 101, 101, 100, 19, 0, 0, 3, -24, 32, 0, 0, 0, 2, 98, 101, 48, 0, 0, 0, 5, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0,
		//				0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -64, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 64, 32, 0, 0, 0, 3, 99, 115, 54, 48, 0, 0, 0, 6, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19,
		//				0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 3, 99, 115, 55, 48, 0, 0, 0, 6, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 15, 100, 104, 99, 112, 112, 114, 111, 116, 111, 95, 116, 111, 99, 112, 117, 1, 0, 32, 0, 0, 0, 8, 100, 110, 117, 103, 114, 111, 117, 112, 19, 0, 0,
		//				0, 0, 32, 0, 0, 0, 2, 101, 102, 48, 0, 0, 0, 6, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 2, 102, 99, 1, 0, 32, 0, 0, 0, 8, 102, 114, 97, 109, 101, 108, 101, 110, 19, 0, 0, 5, -18, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 8, 18, 3, 4, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 8, 18, 3, 4, 32, 0, 0, 0, 8, 105, 110, 116, 101, 114, 110, 97,
		//				108, 1, 0, 32, 0, 0, 0, 9, 105, 115, 111, 108, 97, 116, 105, 111, 110, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 105, 117, 115, 101, 100, 19, 0, 0, 0, -64, 32, 0, 0, 0, 10, 108, 50, 97, 99, 108, 104, 119, 114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0, 10, 108, 51, 97, 99, 108, 104, 119, 114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0, 3, 109, 97, 99, 26, 66, -96, 52, 40, 0, 0, 70, 0, 32, 0, 0, 0, 6, 109, 105, 114, 114, 111, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 2, 32, 0, 0, 0, 5, 111, 117, 115, 101, 100, 19, 0, 0, -6, 0, 32, 0, 0, 0, 13, 112, 101, 114, 109, 105, 116, 112, 107, 116, 108, 111, 111, 112, 1, 1, 32, 0, 0, 0, 10, 112, 101, 114, 112, 114, 111, 102, 105, 108, 101, 32, 0, 0, 0, 5, 101, 116, 104, 46, 48, 32, 0, 0, 0, 6, 112, 111, 114, 116, 97, 99, 1,
		//				1, 32, 0, 0, 0, 3, 114, 101, 102, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 114, 105, 110, 103, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 114, 111, 108, 101, 19, 0, 0, 0, 1, 32, 0, 0, 0, 7, 115, 99, 104, 109, 111, 100, 101, 19, 0, 0, 0, 1, 32, 0, 0, 0, 10, 115, 102, 112, 101, 120, 112, 116, 121, 112, 101, 19, 0, 0, 0, 2, 32, 0, 0, 0, 7, 115, 102, 112, 116, 121, 112, 101, 19, 0, 0, 0, 10, 32, 0, 0, 0, 9, 115, 102, 112, 118, 101, 110, 100, 111, 114, 32, 0, 0, 0, 43, 110, 61, 79, 69, 77, 44, 32, 112, 110, 61, 83, 70, 80, 45, 84, 44, 32, 115, 110, 61, 50, 48, 48, 56, 49, 48, 49, 55, 48, 48, 57, 56, 57, 44, 32, 114, 101, 118, 61, 65, 49, 32, 32, 32, 0, 0, 0, 15, 115, 108, 111, 119, 112, 114, 111, 116, 111, 95, 116, 111, 99, 112, 117, 1, 1, 32, 0, 0, 0, 5, 115, 112, 101, 101, 100, 19, 0,
		//				0, 0, 4, 32, 0, 0, 0, 15, 115, 115, 109, 111, 117, 116, 112, 117, 116, 101, 110, 97, 98, 108, 101, 1, 0, 32, 0, 0, 0, 10, 115, 119, 105, 116, 99, 104, 112, 111, 114, 116, 19, 0, 0, 0, 5, 32, 0, 0, 0, 4, 116, 121, 112, 101, 19, 0, 0, 0, 0, 32, 0, 0, 0, 3, 117, 110, 105, 48, 0, 0, 0, 18, 32, 0, 0, 0, 3, 97, 99, 108, 32, 0, 0, 0, 0, 32, 0, 0, 0, 10, 98, 99, 97, 115, 116, 108, 105, 109, 105, 116, 19, -1, -1, -1, -1, 32, 0, 0, 0, 11, 99, 111, 115, 50, 118, 108, 97, 110, 112, 114, 105, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 100, 108, 102, 108, 105, 109, 105, 116, 19, -1, -1, -1, -1, 32, 0, 0, 0, 9, 101, 103, 98, 119, 108, 105, 109, 105, 116, 19, -1, -1, -1, -1, 32, 0, 0, 0, 5, 101, 110, 99, 97, 112, 19, 0, 0, 0, 1, 32, 0, 0, 0, 8, 105, 99, 108, 114, 109, 111, 100, 101, 19, 0, 0, 0, 2,
		//				32, 0, 0, 0, 9, 105, 110, 98, 119, 108, 105, 109, 105, 116, 19, -1, -1, -1, -1, 32, 0, 0, 0, 7, 108, 112, 116, 115, 116, 97, 116, 19, 0, 0, 0, 0, 32, 0, 0, 0, 9, 108, 112, 116, 115, 116, 97, 116, 98, 121, 19, 0, 0, 0, 1, 32, 0, 0, 0, 10, 109, 99, 97, 115, 116, 108, 105, 109, 105, 116, 19, -1, -1, -1, -1, 32, 0, 0, 0, 8, 111, 99, 108, 114, 109, 111, 100, 101, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 112, 118, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 5, 112, 118, 112, 114, 105, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 115, 100, 116, 112, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 116, 112, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 118, 108, 97, 110, 109, 111, 100, 101, 19, 0, 0, 0, 3, 32, 0, 0, 0, 11, 118, 108, 97, 110, 112, 114, 105, 50, 99, 110, 103, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 118, 101,
		//				103, 99, 19, 0, 0, 0, 0, 32, 0, 0, 0, 10, 119, 97, 118, 101, 108, 101, 110, 103, 116, 104, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 120, 103, 119, 97, 110, 1, 0, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		acObject = super.analysisTabble("allac", t);
		return acObject;
	}

	/*
	 * 创建ACQOS
	 */
	private List<CxtOpItem> createAcQos(AcQosObject acQosObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		AnalysisQOS analysisQOS = new AnalysisQOS();
		cxtOpItems = analysisQOS.createACQOS(acQosObject, session, seqid);
		return cxtOpItems;
	}

	/*
	 * 创建AC
	 */
	private List<CxtOpItem> createAc(String type, EthPortObject ethPortObject, AcObject acObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "qos"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getQos()));

		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "spvlan"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, isNul(acObject.getSpvlan()) ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "cevlan"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, isNul(acObject.getCevlan()) ));
		
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "mode"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getMode()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getAdmin()));
		
		String actionS = getEvlanactionS(acObject.getAction());
		if(have(actionS)){
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "action"));
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, actionS));
			if("modify".equals(actionS)){
				cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "sdvlan"));
				cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getSdvlan()));
				cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "sdvlancfi"));
				cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getSdvlancfi()));
				cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "sdvlanpri"));
				cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getSdvlanpri()));
			}
		}
//System.out.println("AnalysisAC.189 . type="+ethPortObject.getPortType()+" ;name="+ethPortObject.getName()+" ;acObject="+acObject.toString());		
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getAdmin()));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getAdmin()));
//-----		
		if (!acObject.getEthacout().equals("")) {
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "outqos"));
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getEthacout()));
		}
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName()));

		if ("create".equals(type)) {
			cxtOpItems.add(create(acObject.getName(), cxtATTable));
		} else if ("update".equals(type)) {
			cxtOpItems.add(mset(acObject.getName(), cxtATTable));
		}

		return cxtOpItems;
	}
	private String getEvlanactionS(String evlan){
		if("0".equals(evlan))return "nochange";
		if("1".equals(evlan))return "add";
		if("2".equals(evlan))return "modify";
		if("3".equals(evlan))return "remove";
		if("4".equals(evlan))return "modify_add";
		return "";
	}
	private boolean have(String haveIt){
		if("".equals(haveIt)||haveIt.equals(null)){return false;}
		else return true;		
	}
	public String isNul(String value){
		if ("".equals(value) || value == null) {
			value = "0";
		}
		return value;
	}
}
