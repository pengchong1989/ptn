package com.nms.drivechenxiao.analysis.service;

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
import com.nms.drivechenxiao.service.bean.pweth.PwEthObject;
import com.nms.drivechenxiao.service.bean.service.eline.ELineObject;

public class AnalysisELine extends CxtOpLump {

	/**
	 * 创建Eline
	 * 
	 * @param eLineObject
	 * @param acObject
	 * @param pwEthObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createEline(ELineObject eLineObject, AcObject acObject, PwEthObject pwEthObject, EthPortObject ethPortObject, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtOpItem> a = createElineInterface(eLineObject, acObject, pwEthObject, session, seqid);
		List<CxtOpItem> b = createElinePw(pwEthObject, session, seqid);
		List<CxtOpItem> c = createElineAc(acObject, ethPortObject, session, seqid);

		cxtOpItems.add(begin(3));

		cxtOpItems.addAll(a);
		cxtOpItems.addAll(b);
		cxtOpItems.addAll(c);
		if (acObject.getOam() != null) {
			String path = "ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName() + "/" + acObject.getName();
		
			String type = "eline";
			List<CxtOpItem> cxtOpItemListOAM = super.createOAM(type, path, oamObject, session, seqid);
			cxtOpItems.addAll(cxtOpItemListOAM);
		}
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 删除Eline
	 * 
	 * @param eLineObject
	 * @param acObject
	 * @param pwEthObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteEline(ELineObject eLineObject, AcObject acObject, PwEthObject pwEthObject, EthPortObject ethPortObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName() + "/" + acObject.getName()));
		if (acObject.getOam() != null) {
			cxtOpItems.add(delete("oam"));
		}
		cxtOpItems.add(delete("service"));
		cxtOpItems.add(cd("ne/interfaces/pweth/" + pwEthObject.getName()));
		cxtOpItems.add(delete("service"));
		cxtOpItems.add(cd("ne/l2vpn/eline"));
		cxtOpItems.add(delete(eLineObject.getName()));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] selectELine(ELineObject eLineObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/l2vpn/eline" + eLineObject.getName()));
		cxtOpItems.add(get(s, 1));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] selectAllELine(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/l2vpn/eline"));
		cxtOpItems.add(get(s, 2));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public List<ELineObject> analysisELine(byte[] command, CXNEObject CXNEObject) {
		List<ELineObject> elineObject = new ArrayList<ELineObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 0, -10, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 1, 32, 0, 0, 0, 1, 49, 48, 0, 0, 0, 10, 32, 0, 0, 0, 5, 97, 99, 110, 117, 109, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 7, 112, 116, 110, 32, 86, 80, 78, 32, 0, 0, 0, 6, 100, 117, 97, 108, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 110, 97, 109, 101, 19, 0, 18, 0, 1, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 5, 32, 0, 0, 0, 8, 112, 111, 114, 116, 108, 105, 115, 116, 48, 0, 0, 0, 2, 19, 1, 1, 0, 2, 32, 0, 0, 0, 8, 103, 101, 46, 51, 46, 52, 47, 49, 19, 2, 1, 0, 1, 19, 2, 1, 0, 1, 32, 0, 0, 0, 5, 112, 119,
		//				110, 117, 109, 19, 0, 0, 0, 0, 32, 0, 0, 0, 7, 115, 118, 99, 116, 121, 112, 101, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 118, 112, 110, 105, 100, 19, 0, 18, 0, 1, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		elineObject = super.analysisTabble("eline", t);
		return elineObject;
	}

	/*
	 * 创建eline接口
	 */
	private List<CxtOpItem> createElineInterface(ELineObject eLineObject, AcObject acObject, PwEthObject pwEthObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, eLineObject.getAdmin()));
		CxtATTable cxtATTable = getCxtATTable(1, cxtAtomTypes);

		cxtOpItems.add(cd("ne/l2vpn/eline"));
		cxtOpItems.add(create(eLineObject.getName(), cxtATTable));

		return cxtOpItems;
	}

	/*
	 * 绑定pw
	 */
	private List<CxtOpItem> createElinePw(PwEthObject pwEthObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "_"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pwEthObject.getEline().getType()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vpn"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "eline/" + pwEthObject.getEline().getVpn()));
		CxtATTable cxtATTable = getCxtATTable(2, cxtAtomTypes);

		cxtOpItems.add(cd("ne/interfaces/pweth/" + pwEthObject.getName()));
		cxtOpItems.add(create("service", cxtATTable));

		return cxtOpItems;
	}

	/*
	 * 绑定ac
	 */
	private List<CxtOpItem> createElineAc(AcObject acObject, EthPortObject ethPortObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "_"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getEline().getType()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vpn"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "eline/" + acObject.getEline().getVpn()));
		CxtATTable cxtATTable = getCxtATTable(2, cxtAtomTypes);

		cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName() + "/" + acObject.getName()));
		cxtOpItems.add(create("service", cxtATTable));

		return cxtOpItems;
	}
	/**
	 * 更新Eline
	 * 
	 * @param eLineObject
	 * @param acObject
	 *   通过 ac中 name(修改后)与 identify(修改前) 对比
	 *      相同 ：未修改                                   不相同： 与此eline关联的ac更改
	 * @param pwEthObject
	 *   通过 pw中 name(修改后)与identify(修改前) 对比
	 *      相同 ：未修改                                   不相同： 与此eline关联的ac更改
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateEline(ELineObject eLineObject, AcObject acObject, PwEthObject pwEthObject, EthPortObject ethPortObject, int session, int seqid) {
		
		//参数不可 为 空
		try {
			if(eLineObject==null){
				throw new Exception("eLineObject is null");
			}
			if(acObject==null){
				throw new Exception("acObejct is null");
			}
			if(pwEthObject==null){
				throw new Exception("pwEthObject is null");
			}
			if(ethPortObject==null){
				throw new Exception("ethPortObject is null");
			}
		} catch (Exception e) {
			System.out.println("object is null");
		}
		
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		//修改使能（管理状态）
//		cxtOpItems.add(cd("ne/l2vpn/eline"+"/"+eLineObject.getName()));
//		cxtOpItems.add(set("admin",setAdminState(eLineObject.getAdmin())));
//		cxtOpItems.add(set("svctype",eLineObject.getSvctype()));
//		/**
//		 * 判断   更改前后的 pw名是否相同
//		 * pw是否更改
//		 */
//	//pw 测试成功（203-删除，创建）
		if(pwEthObject.getName()!=null){
			if(pwEthObject.getIdentify()!=null&&!pwEthObject.getName().equals(pwEthObject.getIdentify())){
				//删除原有pw中的 service
				cxtOpItems.add(cd("ne/interfaces/pweth/" + pwEthObject.getIdentify()));
				cxtOpItems.add(delete("service"));
				//在  指定的PW 新建service 与 此 业务（eline）关联
				List<CxtOpItem> pw=createElinePw(pwEthObject,session,seqid);
				cxtOpItems.addAll(pw);
			}	
		}
		
		/**
		 * 判断   更改前后的 ac,端口
		 *   只有端口，和ac都未修改时，与eline业务关联的ac才没有修改
		 *     否则： 修改与eline关联的ac 
		 */
		if(ethPortObject.getName()!=null&&ethPortObject.getIdentify()!=null&&acObject.getName()!=null&&acObject.getIdentify()!=null){
			if(ethPortObject.getName().equals(ethPortObject.getIdentify())&&acObject.getName().equals(acObject.getIdentify())&&ethPortObject.getPortType().equals(ethPortObject.getPrevious())){
				//ac未不修改，不做处理
			}else{
				//修改ac
				//删除原端口下的ac中的 service
				cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPrevious() + "/" + ethPortObject.getIdentify() + "/" + acObject.getIdentify()));
				cxtOpItems.add(delete("service"));
				// 为选中的 ac添加 service
				List<CxtOpItem> ac=createElineAc(acObject,ethPortObject,session,seqid);
				cxtOpItems.addAll(ac);							
			}
		}		
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	/*
	 * 测试    单独创建ac   -关联eline
	 *    
	 *   若eline都有与之关联的ac，这新建不上
	 * 
	 */
	public byte[] createElineAc1(AcObject acObject, EthPortObject ethPortObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "_"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getEline().getType()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vpn"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "eline/" + acObject.getEline().getVpn()));
		CxtATTable cxtATTable = getCxtATTable(2, cxtAtomTypes);

		cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName() + "/" + acObject.getName()));
		cxtOpItems.add(create("service", cxtATTable));
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	/**
	 * 测试  单独删除 ac
	 *     可以随时删除与eline关联的  ac（）
	 * @param ethPortObject
	 * @param acObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deletetest(EthPortObject ethPortObject,AcObject acObject,int session,int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName() + "/" + acObject.getName()));
		cxtOpItems.add(delete("service"));
		
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	/**
	 * admin类型转换
	 * @param state
	 * @return
	 */
	public String setAdminState(String state){
		if("0".equals(state)){
			return "down";
		} 
		if("1".equals(state)){
			return "up";
		}
		if("2".equals(state)){
			return "test";
		}
		else
			return "down";
	}
}
