package com.nms.drivechenxiao.analysis.service;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcObject;
import com.nms.drivechenxiao.service.bean.pweth.PwEthObject;
import com.nms.drivechenxiao.service.bean.service.etree.ETreeObject;

public class AnalysisETree extends CxtOpLump {

	/**
	 * 创建ETree
	 * 
	 * @param etreeObject
	 * @param acObject
	 * @param pwEthObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createETree(ETreeObject etreeObject, EthPortObject ethPortObject, AcObject acObject, List<PwEthObject> pwEthObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtOpItem> cxtOpItemListETreeInterface = createETreeInterface(etreeObject, session, seqid);

		if (pwEthObject.size() == 1) {
			List<CxtOpItem> cxtOpItemListETreePw = createETreePw(pwEthObject.get(0), session, seqid);
			List<CxtOpItem> cxtOpItemListETreeAc = createETreeAc(acObject, ethPortObject, session, seqid);

			cxtOpItems.add(begin(3));
			cxtOpItems.addAll(cxtOpItemListETreeInterface);
			cxtOpItems.addAll(cxtOpItemListETreePw);
			cxtOpItems.addAll(cxtOpItemListETreeAc);
			cxtOpItems.add(commit());

		} else {
			cxtOpItems.add(begin(3));
			cxtOpItems.addAll(cxtOpItemListETreeInterface);
//			List<CxtOpItem> cxtOpItemListETreePw1 = createETreePw(pwEthObject.get(0), session, seqid);
//			List<CxtOpItem> cxtOpItemListETreePw2 = createETreePw(pwEthObject.get(1), session, seqid);
			for(PwEthObject pwtem : pwEthObject){
				cxtOpItems.addAll(createETreePw(pwtem, session, seqid));
			}						
//			cxtOpItems.addAll(cxtOpItemListETreePw1);
//			cxtOpItems.addAll(cxtOpItemListETreePw2);
			List<CxtOpItem> cxtOpItemListETreeAc = createETreeAc(acObject, ethPortObject, session, seqid);
			cxtOpItems.addAll(cxtOpItemListETreeAc);
			cxtOpItems.add(commit());
		}

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 删除ETree
	 * 
	 * @param etreeObject
	 * @param acObject
	 * @param pwEthObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteETree(ETreeObject etreeObject, EthPortObject ethPortObject, AcObject acObject, List<PwEthObject> pwEthObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName() + "/" + acObject.getName()));
		cxtOpItems.add(delete("service"));
		if (pwEthObject.size() == 1) {
			cxtOpItems.add(cd("ne/interfaces/pweth/" + pwEthObject.get(0).getName()));
			cxtOpItems.add(delete("service"));
		} else {
			for(PwEthObject pwtem : pwEthObject){
				cxtOpItems.add(cd("ne/interfaces/pweth/" + pwtem.getName()));
				cxtOpItems.add(delete("service"));
			}
//			cxtOpItems.add(cd("ne/interfaces/pweth/" + pwEthObject.get(0).getName()));
//			cxtOpItems.add(delete("service"));
//			cxtOpItems.add(cd("ne/interfaces/pweth/" + pwEthObject.get(1).getName()));
//			cxtOpItems.add(delete("service"));
		}
		cxtOpItems.add(cd("ne/l2vpn/etree"));
		cxtOpItems.add(delete(etreeObject.getName()));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] selectETree(ETreeObject etreeObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/l2vpn/etree" + etreeObject.getName()));
		cxtOpItems.add(get(s, 1));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] selectAllETree(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/l2vpn/etree"));
		cxtOpItems.add(get(s, 2));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public List<ETreeObject> analysisETree(byte[] command, CXNEObject CXNEObject) {
		List<ETreeObject> etreeObject = new ArrayList<ETreeObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 1, -5, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 1, 32, 0, 0, 0, 1, 49, 48, 0, 0, 0, 20, 32, 0, 0, 0, 5, 97, 99, 110, 117, 109, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 0, 32, 0, 0, 0, 9, 98, 99, 97, 115, 116, 99, 116, 114, 108, 1, 0, 32, 0, 0, 0, 6, 98, 114, 97, 110, 99, 104, 48, 0, 0, 0, 1, 19, 1, 1, 0, 1, 32, 0, 0, 0, 8, 103, 101, 46, 51, 46, 49, 47, 49, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 7, 112, 116, 110, 32, 86, 80, 78, 32, 0, 0, 0, 7, 100, 108, 102, 99, 116, 114, 108, 1, 0, 32, 0, 0, 0, 6, 100, 117, 97, 108, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 100, 121, 102, 100, 98, 48, 0, 0, 0, 1, 32,
		//				0, 0, 0, 2, 109, 99, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 9, 108, 101, 97, 114, 110, 114, 117, 108, 101, 19, 0, 0, 0, 0, 32, 0, 0, 0, 9, 108, 105, 109, 105, 116, 97, 100, 100, 114, 19, -1, -1, -1, -1, 32, 0, 0, 0, 9, 109, 99, 97, 115, 116, 99, 116, 114, 108, 1, 0, 32, 0, 0, 0, 6, 109, 101, 109, 99, 110, 116, 19, 0, 0, 0, 2, 32, 0, 0, 0, 4, 110, 97, 109, 101, 19, 0, 19, 0, 1, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 5, 32, 0, 0, 0, 8, 112, 111, 114, 116, 108, 105, 115, 116, 48, 0, 0, 0, 2, 19, 1, 1, 0, 1, 32, 0, 0, 0, 8, 103, 101, 46, 51, 46, 49, 47, 49, 19, 2, 1, 0, 6, 19, 2, 1, 0, 6, 32, 0, 0, 0, 5, 112, 119, 110, 117, 109, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 114, 111, 111, 116, 19, 2, 1, 0, 6, 32, 0, 0, 0, 4, 115, 102,
		//				100, 98, 48, 0, 0, 0, 2, 32, 0, 0, 0, 2, 109, 99, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 2, 117, 99, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 95, 95, 95, 32, 0, 0, 0, 3, 46, 46, 46, 32, 0, 0, 0, 7, 115, 118, 99, 116, 121, 112, 101, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 118, 112, 110, 105, 100, 19, 0, 19, 0, 1, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		etreeObject = super.analysisTabble(1, "etree", t);
		return etreeObject;
	}

	/*
	 * 创建etree接口
	 */
	private List<CxtOpItem> createETreeInterface(ETreeObject etreeObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, etreeObject.getAdmin()));
		CxtATTable cxtATTable = getCxtATTable(1, cxtAtomTypes);

		cxtOpItems.add(cd("ne/l2vpn/etree"));
		cxtOpItems.add(create(etreeObject.getName(), cxtATTable));

		return cxtOpItems;
	}

	/*
	 * 绑定pw
	 */
	private List<CxtOpItem> createETreePw(PwEthObject pwEthObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "_"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pwEthObject.getEtree().getType()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vpn"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "etree/" + pwEthObject.getEtree().getVpn()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "role"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pwEthObject.getEtree().getRole()));
		CxtATTable cxtATTable = getCxtATTable(3, cxtAtomTypes);

		cxtOpItems.add(cd("ne/interfaces/pweth/" + pwEthObject.getName()));
		cxtOpItems.add(create("service", cxtATTable));

		return cxtOpItems;
	}

	/*
	 * 绑定ac
	 */
	private List<CxtOpItem> createETreeAc(AcObject acObject, EthPortObject ethPortObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "_"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getEtree().getType()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vpn"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "etree/" + acObject.getEtree().getVpn()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "role"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getEtree().getRole()));
		CxtATTable cxtATTable = getCxtATTable(3, cxtAtomTypes);

		cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName() + "/" + acObject.getName()));
		cxtOpItems.add(create("service", cxtATTable));

		return cxtOpItems;
	}
	/**
	 * 更新 etree
	 *      一个 ac对应多个pw
	 * @param etreeObject
	 *          存有 删除pw和新建的pw
	 * @param pwUpdate
	 *    要修改 的 pw的 集合（不包括删除，或者新建的）
	 * @param acObject
	 *    ac
	 * @param ethPortObject
	 *    端口
	 * @param flag
	 *     注：：！！
	 *     true  -----修改前 ac 作为跟节点
	 *     false  -------修改前    pw作为跟节点或者没有跟节点
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateEtree(ETreeObject etreeObject,List<PwEthObject> pwUpdate,AcObject acObject,EthPortObject ethPortObject,int session,int seqid){
		
		/**
		 *修改   跟/叶子时 
		 *   必须先修改  ：  将跟 转为叶子 的节点
		 *   pw-pw 无顺序
		 *   ac-pw   有序
		 *    
		 */
		List<CxtOpItem> cxtOpItems=new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		
//		cxtOpItems.add(cd("ne/l2vpn/etree/"+etreeObject.getName()));
//		cxtOpItems.add(set("admin",setAdminState(etreeObject.getAdmin())));
//		cxtOpItems.add(set("limitaddr",(etreeObject.getLimitaddr())));
//		cxtOpItems.add(set("learnrule",setState(etreeObject.getLearnrule())));
//		cxtOpItems.add(set("bcastctrl",(etreeObject.getBcastctrl())));
//		//cxtOpItems.add(set("macstctrl",setBool(etreeObject.getMcastctrl())));
//		cxtOpItems.add(set("dlfctrl",(etreeObject.getDlfctrl())));
//		cxtOpItems.add(set("svctype",etreeObject.getSvctype()));
		
		/*
		 * 修改etree 前 ac作为跟
		 *     则先处理ac 
		 */
		if(etreeObject.isFlag()){
			List<CxtOpItem> cxtAc=updateAcObject(ethPortObject, acObject, session, seqid);
			cxtOpItems.addAll(cxtAc);
			List<CxtOpItem> cxtPw=updatePwEthObject(etreeObject, pwUpdate, session, seqid);
			cxtOpItems.addAll(cxtPw);			
		}
		/*
		 * 修改前 ac不是 跟，则 pw为跟或者无跟
		 *    先处理pw,在处理ac
		 */
		else{
			List<CxtOpItem> cxtPw=updatePwEthObject(etreeObject, pwUpdate, session, seqid);
			cxtOpItems.addAll(cxtPw);
			List<CxtOpItem> cxtAc=updateAcObject(ethPortObject, acObject, session, seqid);
			cxtOpItems.addAll(cxtAc);
		}
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	/*
	 * 更新ac
	 */
	public List<CxtOpItem> updateAcObject(EthPortObject ethPortObject,AcObject acObject,int session,int seqid ){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		/**
		 * 判断   更改前后的 ac,端口
		 *   只有端口，和ac都未修改时，与etree业务关联的ac才没有修改
		 *     否则： 修改与etree关联的ac 
		 */
		/*
		 * 删除修改etree前的 ac
		 */
		if(ethPortObject.getName()==null||ethPortObject.getName().equals("")||ethPortObject.getName().equals("0")
				||acObject.getName().equals("")||acObject.getName()==null||acObject.getName().equals("0")
				||ethPortObject.getPortType()==null||ethPortObject.getPortType().equals("")||ethPortObject.getPortType().equals("0")){
			cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPrevious() + "/" + ethPortObject.getIdentify() + "/" + acObject.getIdentify()));
			cxtOpItems.add(delete("service"));
		}
		/*
		 * 修改etree后，新增的ac
		 */
		if(ethPortObject.getIdentify()==null||ethPortObject.getIdentify().equals("0")||ethPortObject.getIdentify().equals("")
				||acObject.getIdentify()==null||acObject.getIdentify().equals("")||acObject.getIdentify().equals("0")
				||ethPortObject.getPrevious()==null||ethPortObject.getPrevious().equals("")||ethPortObject.getPrevious().equals("0")){
			List<CxtOpItem> ac=createETreeAc(acObject,ethPortObject,session,seqid);
			cxtOpItems.addAll(ac);
		}
		/*
		 * 只有，端口类型，端口名称，ac名称 修改前后都相同时，，ac才未  删除或者新增（即重置ac）
		 */
		if(ethPortObject.getName().equals(ethPortObject.getIdentify())&&acObject.getName().equals(acObject.getIdentify())&&ethPortObject.getPrevious().equals(ethPortObject.getPortType())){			
			/*
			 * ac 没有更改，重置ac的属性 *（跟/叶子）
			 */
			cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName() + "/" + acObject.getName()));
			List<CxtAtomType> cxtAtomTypes=new ArrayList<CxtAtomType>();
			//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "learn"));
			//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, acObject.getEtree().getLearn()));
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "role"));				
			cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getEtree().getRole()));
			CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
			cxtOpItems.add(mset("service",cxtATTable));				
		}else{
			//此网元更换ac   ：  先删除，在新建
			cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPrevious() + "/" + ethPortObject.getIdentify() + "/" + acObject.getIdentify()));
			cxtOpItems.add(delete("service"));
			List<CxtOpItem> ac=createETreeAc(acObject,ethPortObject,session,seqid);
			cxtOpItems.addAll(ac);
		}
		return cxtOpItems;
	}
	/*
	 * 更新pw
	 */
	public List<CxtOpItem> updatePwEthObject(ETreeObject etreeObject,List<PwEthObject> pwUpdate,int session,int seqid ){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		/*
		 * 删除pw
		 */
		if(etreeObject.getPwDelete()!=null&&etreeObject.getPwDelete().size()>0){
			for(PwEthObject pwEthObject:etreeObject.getPwDelete()){
				cxtOpItems.add(cd("ne/interfaces/pweth/" + pwEthObject.getName()));
 				cxtOpItems.add(delete("service"));
			}
		}
		/*
		 * 新增pw
		 */
		if(etreeObject.getPwInsert()!=null&&etreeObject.getPwInsert().size()>0){
			for(PwEthObject pwEthObject:etreeObject.getPwInsert()){
				List<CxtOpItem> pw=createETreePw(pwEthObject,session,seqid);
				cxtOpItems.addAll(pw);
			}
		}
		/*
		 * 更新pw
		 */
		if(pwUpdate!=null&&pwUpdate.size()>0){			
			for(PwEthObject pwEthObject:pwUpdate){			
				cxtOpItems.add(cd("ne/interfaces/pweth/" + pwEthObject.getName()));
				List<CxtAtomType> cxtAtomTypes=new ArrayList<CxtAtomType>();
				//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "learn"));
				//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, pwEthObject.getEtree().getLearn()));
				cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "role"));
				cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pwEthObject.getEtree().getRole()));
				CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
				cxtOpItems.add(mset("service",cxtATTable));				
			}
		}
		return cxtOpItems;
	}
	/**
	 * learnrule类型转换
	 * @param state
	 * @return
	 */
	public String setState(String state){
		if("0".equals(state)){
			return "discard";
		} 
		if("1".equals(state)){
			return "broadcast";
		}
		else
			return "discard";
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
