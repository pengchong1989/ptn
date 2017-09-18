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
import com.nms.drivechenxiao.service.bean.qos.QosObject;
import com.nms.drivechenxiao.service.bean.tunnel.LSPObject;
import com.nms.drivechenxiao.service.bean.tunnel.Protection;
import com.nms.drivechenxiao.service.bean.tunnel.TunnelObject;

public class AnalysisTunnel extends CxtOpLump {

	/**
	 * 创建Tunnel
	 * 
	 * @param qosObject
	 * @param tunnelObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createTunnel(QosObject qosObject, TunnelObject tunnelObject, Protection protection, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtOpItem> b = createTunnelInterface("create", tunnelObject, seqid, seqid);
		List<CxtOpItem> c = createLSP("create", tunnelObject, oamObject, seqid, seqid);
		cxtOpItems.add(begin(3));
		if (qosObject != null && tunnelObject.isCreateQos()) {
			List<CxtOpItem> a = super.createQOS(qosObject, session, seqid);
			cxtOpItems.addAll(a);
		}
		cxtOpItems.addAll(b);
		cxtOpItems.addAll(c);
		if (protection != null) {
			List<CxtOpItem> d = createProtection(tunnelObject, protection, seqid, seqid);
			cxtOpItems.addAll(d);
		}
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 删除Tunnel
	 * 
	 * @param qosObject
	 * @param tunnelObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteTunnel(QosObject qosObject, TunnelObject tunnelObject, OamObject oamObject, int session, int seqid) {
		String path = "ne/interfaces/tunnel";
		String name = tunnelObject.getName();

		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(path));
		cxtOpItems.add(delete(name));
		if (qosObject != null && !"".equals(qosObject.getQosType())) {
			if (tunnelObject.isDeleteQos()) {
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
	 * 修改Tunnel
	 * 
	 * @param qosObject
	 * @param tunnelObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateTunnel(QosObject qosObject, TunnelObject tunnelObject, Protection protection, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtOpItem> b = createTunnelInterface("update", tunnelObject, seqid, seqid);
		List<CxtOpItem> c = createLSP("update", tunnelObject, oamObject, seqid, seqid);
		cxtOpItems.add(begin(3));
		//kk
		if(tunnelObject.isCreateQos()){
			List<CxtOpItem> a = super.createQOS(qosObject, session, seqid);
			cxtOpItems.addAll(a);
		}
		cxtOpItems.addAll(b);
		cxtOpItems.addAll(c);
		if (protection != null) {
			List<CxtOpItem> d = createProtection(tunnelObject, protection, seqid, seqid);
			cxtOpItems.addAll(d);
		}
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 查询所有tunnel
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] selectAllTuunel(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/tunnel"));
		cxtOpItems.add(get(s, 1));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 查询tunnel
	 * @param tunnelObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] selectTuunel(TunnelObject tunnelObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/tunnel/" + tunnelObject.getName()));
		cxtOpItems.add(get(s, 1));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 查询所有lsp
	 * @param tunnelObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] selectAllLSP(TunnelObject tunnelObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/tunnel/" + tunnelObject.getName()));
		cxtOpItems.add(get(s, 2));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 查询lsp
	 * @param tunnelObject
	 * @param lspObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] selectLSP(TunnelObject tunnelObject, LSPObject lspObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/tunnel/" + tunnelObject.getName() + "/" + lspObject.getName()));
		cxtOpItems.add(get(s, 1));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 解析tunnel
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public List<TunnelObject> analysisTunnel(byte[] command, CXNEObject CXNEObject) {
//System.out.println("in analysisTunnel : command[] = "+CoreOper.print16String(command));	
		List<TunnelObject> tunnelObject = new ArrayList<TunnelObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 2, -50, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 20, 32, 0, 0, 0, 1, 49, 48, 0, 0, 0, 18, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 9, 99, 97, 114, 114, 105, 101, 114, 105, 102, 19, 8, 18, 1, 1, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 5, 104, 119, 114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 104, 119, 114, 101, 115, 114, 101, 102, 32, 0, 0, 0, 31, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 32, 0, 0, 0, 2, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 4, 0,
		//				29, 1, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 4, 0, 29, 1, 32, 0, 0, 0, 7, 105, 110, 108, 97, 98, 101, 108, 19, 0, 0, 0, 33, 32, 0, 0, 0, 8, 108, 98, 108, 104, 119, 114, 101, 115, 19, 0, 0, 0, 2, 32, 0, 0, 0, 6, 111, 110, 114, 105, 110, 103, 1, 0, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 111, 117, 116, 108, 97, 98, 101, 108, 19, 0, 0, 0, 20, 32, 0, 0, 0, 4, 112, 101, 101, 114, 19, 20, 0, 0, 35, 32, 0, 0, 0, 6, 112, 101, 101, 114, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 10, 112, 101, 114, 112, 114, 111, 102, 105, 108, 101, 32, 0, 0, 0, 6, 109, 112, 108, 115, 46, 48, 32, 0, 0, 0, 3, 114, 101, 102, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 115, 100, 116, 104, 114, 19, 0, 0, 0, 3, 32, 0, 0, 0, 6, 95, 99, 97, 99, 104, 101, 48, 0, 0, 0, 0, 32, 0, 0, 0,
		//				5, 95, 112, 114, 105, 118, 48, 0, 0, 0, 0, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 6, 100, 117, 97, 108, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 104, 119, 114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 104, 119, 114, 101, 115, 114, 101, 102, 32, 0, 0, 0, 31, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 32, 0, 0, 0, 2, 105, 100, 19, 0, 0, 0, 29, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 0, 2, 0, 29, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 0, 2, 0, 29, 32, 0, 0, 0, 10, 108, 115, 112, 113, 111, 115, 116, 121, 112, 101, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 108, 115, 112, 119, 19, 4, 0,
		//				29, 1, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 111, 119, 110, 101, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 10, 112, 97, 115, 115, 116, 117, 110, 110, 101, 108, 1, 0, 32, 0, 0, 0, 6, 112, 101, 101, 114, 105, 100, 19, 0, 0, 0, 29, 32, 0, 0, 0, 3, 113, 111, 115, 32, 0, 0, 0, 12, 108, 108, 115, 112, 116, 117, 110, 110, 101, 108, 51, 57, 32, 0, 0, 0, 3, 114, 101, 102, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 114, 111, 108, 101, 19, 0, 0, 0, 0, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
//System.out.println("in analysisTunnel : t[] = "+CoreOper.print16String(t));			
		tunnelObject = super.analysisTabble("tunnel", t);
		return tunnelObject;
	}

	/**
	 * 解析所有lsp
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public List<LSPObject> analysisAllLsp(byte[] command, CXNEObject CXNEObject) {
		List<LSPObject> lspObject = new ArrayList<LSPObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 2, -51, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 20, 32, 0, 0, 0, 1, 49, 48, 0, 0, 0, 18, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 9, 99, 97, 114, 114, 105, 101, 114, 105, 102, 19, 8, 18, 1, 1, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 5, 104, 119, 114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 104, 119, 114, 101, 115, 114, 101, 102, 32, 0, 0, 0, 31, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 32, 0, 0, 0, 2, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 4, 0,
		//				1, 1, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 4, 0, 1, 1, 32, 0, 0, 0, 7, 105, 110, 108, 97, 98, 101, 108, 19, 0, 0, 0, 17, 32, 0, 0, 0, 8, 108, 98, 108, 104, 119, 114, 101, 115, 19, 0, 0, 0, 2, 32, 0, 0, 0, 6, 111, 110, 114, 105, 110, 103, 1, 0, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 1, 32, 0, 0, 0, 8, 111, 117, 116, 108, 97, 98, 101, 108, 19, 0, 0, 0, 16, 32, 0, 0, 0, 4, 112, 101, 101, 114, 19, 20, 0, 0, 5, 32, 0, 0, 0, 6, 112, 101, 101, 114, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 10, 112, 101, 114, 112, 114, 111, 102, 105, 108, 101, 32, 0, 0, 0, 6, 109, 112, 108, 115, 46, 48, 32, 0, 0, 0, 3, 114, 101, 102, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 115, 100, 116, 104, 114, 19, 0, 0, 0, 3, 32, 0, 0, 0, 6, 95, 99, 97, 99, 104, 101, 48, 0, 0, 0, 0, 32, 0, 0, 0, 5,
		//				95, 112, 114, 105, 118, 48, 0, 0, 0, 0, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 6, 100, 117, 97, 108, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 104, 119, 114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 104, 119, 114, 101, 115, 114, 101, 102, 32, 0, 0, 0, 31, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 32, 0, 0, 0, 2, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 0, 2, 0, 1, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 0, 2, 0, 1, 32, 0, 0, 0, 10, 108, 115, 112, 113, 111, 115, 116, 121, 112, 101, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 108, 115, 112, 119, 19, 4, 0, 1, 1,
		//				32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 1, 32, 0, 0, 0, 5, 111, 119, 110, 101, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 10, 112, 97, 115, 115, 116, 117, 110, 110, 101, 108, 1, 0, 32, 0, 0, 0, 6, 112, 101, 101, 114, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 3, 113, 111, 115, 32, 0, 0, 0, 11, 108, 108, 115, 112, 116, 117, 110, 110, 101, 108, 49, 32, 0, 0, 0, 3, 114, 101, 102, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 114, 111, 108, 101, 19, 0, 0, 0, 0, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
//System.out.println("in AllLsp : t[] = "+CoreOper.print16String(t));	
		lspObject = super.analysisTabble("alllsp", t);
		return lspObject;
	}

	/**
	 * 解析lsp 
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public List<LSPObject> analysisLsp(byte[] command, CXNEObject CXNEObject) {
		List<LSPObject> lspObject = new ArrayList<LSPObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 2, -34, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 20, 32, 0, 0, 0, 1, 49, 48, 0, 0, 0, 18, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 9, 99, 97, 114, 114, 105, 101, 114, 105, 102, 19, 8, 18, 1, 1, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 5, 104, 119, 114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 104, 119, 114, 101, 115, 114, 101, 102, 32, 0, 0, 0, 31, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 32, 0, 0, 0, 2, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 4, 0,
		//				1, 1, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 4, 0, 1, 1, 32, 0, 0, 0, 7, 105, 110, 108, 97, 98, 101, 108, 19, 0, 0, 0, 17, 32, 0, 0, 0, 8, 108, 98, 108, 104, 119, 114, 101, 115, 19, 0, 0, 0, 2, 32, 0, 0, 0, 6, 111, 110, 114, 105, 110, 103, 1, 0, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 1, 32, 0, 0, 0, 8, 111, 117, 116, 108, 97, 98, 101, 108, 19, 0, 0, 0, 16, 32, 0, 0, 0, 4, 112, 101, 101, 114, 19, 20, 0, 0, 5, 32, 0, 0, 0, 6, 112, 101, 101, 114, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 10, 112, 101, 114, 112, 114, 111, 102, 105, 108, 101, 32, 0, 0, 0, 6, 109, 112, 108, 115, 46, 48, 32, 0, 0, 0, 3, 114, 101, 102, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 115, 100, 116, 104, 114, 19, 0, 0, 0, 3, 32, 0, 0, 0, 6, 95, 99, 97, 99, 104, 101, 48, 0, 0, 0, 1, 19, 2, 1, 0, 3,
		//				32, 0, 0, 0, 7, 112, 119, 101, 116, 104, 47, 51, 32, 0, 0, 0, 5, 95, 112, 114, 105, 118, 48, 0, 0, 0, 0, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 3, 112, 116, 110, 32, 0, 0, 0, 6, 100, 117, 97, 108, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 104, 119, 114, 101, 115, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 104, 119, 114, 101, 115, 114, 101, 102, 32, 0, 0, 0, 31, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 44, 48, 32, 0, 0, 0, 2, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 7, 105, 102, 105, 110, 100, 101, 120, 19, 0, 2, 0, 1, 32, 0, 0, 0, 6, 105, 102, 110, 97, 109, 101, 19, 0, 2, 0, 1, 32, 0, 0, 0, 10, 108, 115, 112, 113, 111, 115, 116, 121, 112, 101, 19,
		//				0, 0, 0, 1, 32, 0, 0, 0, 4, 108, 115, 112, 119, 19, 4, 0, 1, 1, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 1, 32, 0, 0, 0, 5, 111, 119, 110, 101, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 10, 112, 97, 115, 115, 116, 117, 110, 110, 101, 108, 1, 0, 32, 0, 0, 0, 6, 112, 101, 101, 114, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 3, 113, 111, 115, 32, 0, 0, 0, 11, 108, 108, 115, 112, 116, 117, 110, 110, 101, 108, 49, 32, 0, 0, 0, 3, 114, 101, 102, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 114, 111, 108, 101, 19, 0, 0, 0, 0, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		lspObject = super.analysisTabble("lsp", t);
		return lspObject;
	}

	/*
	 * 创建Tunnel接口
	 */
	private List<CxtOpItem> createTunnelInterface(String type, TunnelObject tunnelObject, int session, int seqid) {
		String path = "ne/interfaces/tunnel";

		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "peerid"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, tunnelObject.getPeerid()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "role"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, tunnelObject.getRole()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "qos"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, tunnelObject.getQos()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, tunnelObject.getAdmin()));
		CxtATTable cxtATTable1 = getCxtATTable(4, cxtAtomTypes);

		cxtOpItems.add(cd(path));
		if ("create".equals(type)) {
			cxtOpItems.add(create(tunnelObject.getName(), cxtATTable1));
		} else if ("update".equals(type)) {
			cxtOpItems.add(mset(tunnelObject.getName(), cxtATTable1));
		}

		return cxtOpItems;
	}

	/*
	 * 创建LSP
	 */
	private List<CxtOpItem> createLSP(String type, TunnelObject tunnelObject, OamObject oamObject, int session, int seqid) {

		String path = "ne/interfaces/tunnel/" + tunnelObject.getName();
		LSPObject lsp1 = null;
		LSPObject lsp2 = null;
		CxtATTable cxtATTable1 = null;
		CxtATTable cxtATTable2 = null;

		if (tunnelObject.getLSPObjects() != null && tunnelObject.getLSPObjects()[0] != null) {
			lsp1 = tunnelObject.getLSPObjects()[0];

			List<CxtAtomType> cxtAtomTypes1 = new ArrayList<CxtAtomType>();
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, lsp1.getAdmin()));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "peer"));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, lsp1.getPeer()));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "carrierif"));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, lsp1.getCarrierif()));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "inlabel"));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, lsp1.getInlabel()));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "outlabel"));
			cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, lsp1.getOutlabel()));
			cxtATTable1 = getCxtATTable(cxtAtomTypes1.size() / 2, cxtAtomTypes1);
		}

		if (tunnelObject.getLSPObjects().length > 1 && tunnelObject.getLSPObjects()[0] != null && tunnelObject.getLSPObjects()[1] != null) {
			lsp2 = tunnelObject.getLSPObjects()[1];

			List<CxtAtomType> cxtAtomTypes2 = new ArrayList<CxtAtomType>();
			cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
			cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, lsp1.getAdmin()));
			cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, "peer"));
			cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, lsp2.getPeer()));
			cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, "carrierif"));
			cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, lsp2.getCarrierif()));
			cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, "inlabel"));
			cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, lsp2.getInlabel()));
			cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, "outlabel"));
			cxtAtomTypes2.add(getCxtAtomType(CxtAtomType.AT_STRING, lsp2.getOutlabel()));
			cxtATTable2 = getCxtATTable(cxtAtomTypes2.size() / 2, cxtAtomTypes2);
		}

		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		if (lsp1 != null) {
			cxtOpItems.add(cd(path));
			if ("create".equals(type)) {
				cxtOpItems.add(create(lsp1.getName(), cxtATTable1));
				if (lsp1.getOam() != null) {
					String OAMPath = "ne/interfaces/tunnel/" + tunnelObject.getName() + "/" + lsp1.getName();
					cxtOpItems.addAll(createOAM("tunnel", OAMPath, lsp1.getOam(), session, seqid));
				}
			} else if ("update".equals(type)) {
				cxtOpItems.add(mset(lsp1.getName(), cxtATTable1));
				if (lsp1.getOam() != null) {
					String OAMPath = "ne/interfaces/tunnel/" + tunnelObject.getName() + "/" + lsp1.getName();
					cxtOpItems.addAll(updateOAM("tunnel", OAMPath, lsp1.getOam(), session, seqid));
				}
			}

		}
		if (lsp2 != null) {
			cxtOpItems.add(cd(path));
			if ("create".equals(type)) {
				cxtOpItems.add(create(lsp2.getName(), cxtATTable2));
				if (lsp2.getOam() != null) {
					String OAMPath = "ne/interfaces/tunnel/" + tunnelObject.getName() + "/" + lsp2.getName();
					cxtOpItems.addAll(createOAM("tunnel", OAMPath, lsp2.getOam(), session, seqid));
				}
			} else if ("update".equals(type)) {
				cxtOpItems.add(mset(lsp2.getName(), cxtATTable2));
				if (lsp2.getOam() != null) {
					String OAMPath = "ne/interfaces/tunnel/" + tunnelObject.getName() + "/" + lsp2.getName();
					cxtOpItems.addAll(updateOAM("tunnel", OAMPath, lsp2.getOam(), session, seqid));
				}
			}
		}
		return cxtOpItems;
	}

	private List<CxtOpItem> createProtection(TunnelObject tunnelObject, Protection protection, int session, int seqid) {
		String path = "ne/interfaces/tunnel/" + tunnelObject.getName();

		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "desc"));
		//		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, tunnelObject.getPeerid()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "enaps"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, protection.getEnaps()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "type"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, protection.getType()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "wtrtime"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, protection.getWtrtime()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "holdofftime"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, protection.getHoldofftime()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "sdthreshold"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, protection.getSdthreshold()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "worklsp"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, protection.getWorklsp()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "prtlsp"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, protection.getPrtlsp()));//tunnel/2/2
		CxtATTable cxtATTable1 = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(cd(path));
		cxtOpItems.add(mset("protection", cxtATTable1));

		return cxtOpItems;
	}
}