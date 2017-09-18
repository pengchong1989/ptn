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
import com.nms.drivechenxiao.service.bean.service.elan.ELanObject;

public class AnalysisELAN extends CxtOpLump {

	/**
	 * 创建ELAN
	 * 
	 * @param elanObject
	 * @param acObject
	 * @param pwEthObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createELAN(ELanObject elanObject, EthPortObject ethPortObject, AcObject acObject, List<PwEthObject> pwEthObject, int session, int seqid) {

		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtOpItem> a = createELANInterface(elanObject, session, seqid);
		byte[] command = null;
		if (pwEthObject.size() == 1) {
			List<CxtOpItem> b = createELANPw(pwEthObject.get(0), session, seqid);
			List<CxtOpItem> c = createELANAc(acObject, ethPortObject, session, seqid);
			cxtOpItems.add(begin(3));
			cxtOpItems.addAll(a);
			cxtOpItems.addAll(b);
			cxtOpItems.addAll(c);
			cxtOpItems.add(commit());
			command = getCommandBytes(cxtOpItems);
		} else {
			cxtOpItems.add(begin(3));
			cxtOpItems.addAll(a);
			for(PwEthObject pwtem : pwEthObject){
				cxtOpItems.addAll(createELANPw(pwtem, session, seqid));
			}
//			List<CxtOpItem> b = createELANPw(pwEthObject.get(0), session, seqid);
//			List<CxtOpItem> c = createELANPw(pwEthObject.get(1), session, seqid);		
//			cxtOpItems.addAll(b);
//			cxtOpItems.addAll(c);
			List<CxtOpItem> d = createELANAc(acObject, ethPortObject, session, seqid);
			cxtOpItems.addAll(d);
			cxtOpItems.add(commit());
			command = getCommandBytes(cxtOpItems);
		}
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 删除ELAN
	 * 
	 * @param elanObject
	 * @param acObject
	 * @param pwEthObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteELAN(ELanObject elanObject, EthPortObject ethPortObject, AcObject acObject, List<PwEthObject> pwEthObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName() + "/" + acObject.getName()));
		cxtOpItems.add(delete("service"));
		for(PwEthObject pwtem : pwEthObject){
			cxtOpItems.add(cd("ne/interfaces/pweth/" + pwtem.getName()));
			cxtOpItems.add(delete("service"));
		}
//		cxtOpItems.add(cd("ne/interfaces/pweth/" + pwEthObject.get(0).getName()));
//		cxtOpItems.add(delete("service"));
//		cxtOpItems.add(cd("ne/interfaces/pweth/" + pwEthObject.get(1).getName()));
//		cxtOpItems.add(delete("service"));
		cxtOpItems.add(cd("ne/l2vpn/elan"));
		cxtOpItems.add(delete(elanObject.getName()));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] selectELan(ELanObject elanObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/l2vpn/elan" + elanObject.getName()));
		cxtOpItems.add(get(s, 1));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] selectAllELan(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/l2vpn/elan"));
		cxtOpItems.add(get(s, 2));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public List<ELanObject> analysisELan(byte[] command, CXNEObject CXNEObject) {
		List<ELanObject> elanObject = new ArrayList<ELanObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 0, -21, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 10, 32, 0, 0, 0, 5, 97, 99, 110, 117, 109, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 7, 112, 116, 110, 32, 86, 80, 78, 32, 0, 0, 0, 6, 100, 117, 97, 108, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 110, 97, 109, 101, 19, 0, 18, 0, 1, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 1, 32, 0, 0, 0, 8, 112, 111, 114, 116, 108, 105, 115, 116, 48, 0, 0, 0, 2, 19, 1, 1, 0, 1, 32, 0, 0, 0, 8, 102, 101, 46, 49, 46, 49, 47, 49, 19, 2, 1, 0, 1, 19, 2, 1, 0, 1, 32, 0, 0, 0, 5, 112, 119, 110, 117, 109, 19, 0, 0, 0, 1, 32, 0,
		//				0, 0, 7, 115, 118, 99, 116, 121, 112, 101, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 118, 112, 110, 105, 100, 19, 0, 18, 0, 1, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		elanObject = super.analysisTabble(1, "elan", t);
		return elanObject;
	}

	/*
	 * 创建elan接口
	 */
	private List<CxtOpItem> createELANInterface(ELanObject elanObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, elanObject.getAdmin()));
		CxtATTable cxtATTable = getCxtATTable(1, cxtAtomTypes);

		cxtOpItems.add(cd("ne/l2vpn/elan"));
		cxtOpItems.add(create(elanObject.getName(), cxtATTable));

		return cxtOpItems;
	}

	/*
	 * 绑定pw
	 */
	private List<CxtOpItem> createELANPw(PwEthObject pwEthObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "_"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pwEthObject.getElan().getType()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vpn"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "elan/" + pwEthObject.getElan().getVpn()));
		CxtATTable cxtATTable = getCxtATTable(2, cxtAtomTypes);

		cxtOpItems.add(cd("ne/interfaces/pweth/" + pwEthObject.getName()));
		cxtOpItems.add(create("service", cxtATTable));

		return cxtOpItems;
	}

	/*
	 * 绑定ac
	 */
	private List<CxtOpItem> createELANAc(AcObject acObject, EthPortObject ethPortObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();		
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "_"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getElan().getType()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vpn"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "elan/" + acObject.getElan().getVpn()));
		CxtATTable cxtATTable = getCxtATTable(2, cxtAtomTypes);

		cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName() + "/" + acObject.getName()));
		cxtOpItems.add(create("service", cxtATTable));

		return cxtOpItems;
	}
	/**
	 * 更新 elan
	 * @param elanObject
	 * @param pwEthObjectList
	 * @param ethPortObject
	 * @param acObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateElan(ELanObject elanObject,List<PwEthObject> pwEthObjectList,EthPortObject ethPortObject,AcObject acObject,int session,int seqid){
		List<CxtOpItem> cxtOpItems=new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		
//		cxtOpItems.add(cd("ne/l2vpn/elan/"+elanObject.getName()));
//		cxtOpItems.add(set("admin",setAdminState(elanObject.getAdmin())));
//		cxtOpItems.add(set("limitaddr",elanObject.getLimitaddr()));
//		cxtOpItems.add(set("learnrule",setState(elanObject.getLearnrule())));
//		cxtOpItems.add(set("bcastctrl",setBool(elanObject.getBcastctrl())));
//		cxtOpItems.add(set("dlfctrl",setBool(elanObject.getDlfctrl())));
//		cxtOpItems.add(set("svctype",elanObject.getSvctype()));
		
		List<CxtOpItem> cxtPw=updatePwEthObject(elanObject, pwEthObjectList, session, seqid);
		cxtOpItems.addAll(cxtPw);
		List<CxtOpItem> cxtAc=updateAcObject(ethPortObject, acObject, session, seqid);
		cxtOpItems.addAll(cxtAc);
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
		 *   只有端口，和ac都未修改时，与elan业务关联的ac才没有修改
		 *     否则： 修改与etree关联的ac 
		 */
		/*
		 * 删除修改elan前的 ac
		 */
		if(ethPortObject.getName()==null||ethPortObject.getName().equals("")||ethPortObject.getName().equals("0")
				||acObject.getName().equals("")||acObject.getName()==null||acObject.getName().equals("0")
				||ethPortObject.getPortType()==null||ethPortObject.getPortType().equals("")||ethPortObject.getPortType().equals("0")){
			cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPrevious() + "/" + ethPortObject.getIdentify() + "/" + acObject.getIdentify()));
			cxtOpItems.add(delete("service"));
		}
		/*
		 * 修改elan后，新增的ac
		 */
		if(ethPortObject.getIdentify()==null||ethPortObject.getIdentify().equals("0")||ethPortObject.getIdentify().equals("")
				||acObject.getIdentify()==null||acObject.getIdentify().equals("")||acObject.getIdentify().equals("0")
				||ethPortObject.getPrevious()==null||ethPortObject.getPrevious().equals("")||ethPortObject.getPrevious().equals("0")){
			List<CxtOpItem> ac=createELANAc(acObject,ethPortObject,session,seqid);
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
			//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, acObject.getElan().getLearn()));
			//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "spw"));				
			//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, acObject.getElan().getSpw()));
			CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
			cxtOpItems.add(mset("service",null));				
		}else{
			//跟换ac，先删除，在添加
			cxtOpItems.add(cd("ne/interfaces/" + ethPortObject.getPrevious() + "/" + ethPortObject.getIdentify() + "/" + acObject.getIdentify()));
			cxtOpItems.add(delete("service"));
			List<CxtOpItem> ac=createELANAc(acObject,ethPortObject,session,seqid);
			cxtOpItems.addAll(ac);
		}
		return cxtOpItems;
	}
	/*
	 * 更新pw
	 */
	public List<CxtOpItem> updatePwEthObject(ELanObject elanObject,List<PwEthObject> pwUpdate,int session,int seqid ){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		/*
		 * 删除pw
		 */
		if(elanObject.getPwDelete()!=null&&elanObject.getPwDelete().size()>0){
			for(PwEthObject pwEthObject:elanObject.getPwDelete()){
				cxtOpItems.add(cd("ne/interfaces/pweth/" + pwEthObject.getName()));
 				cxtOpItems.add(delete("service"));
			}
		}
		/*
		 * 新增pw
		 */
		if(elanObject.getPwInsert()!=null&&elanObject.getPwInsert().size()>0){
			for(PwEthObject pwEthObject:elanObject.getPwInsert()){
				List<CxtOpItem> cxtAddPw=createELANPw(pwEthObject, session, seqid);
				cxtOpItems.addAll(cxtAddPw);
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
				//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, pwEthObject.getElan().getLearn()));
			//	cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "spw"));
				//cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pwEthObject.getElan().getSpw()));
				CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
				cxtOpItems.add(mset("service",null));				
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
	public String setBool(String bo){
		if("1".equals(bo)){
			return "true";
		}else {
			return "false";
		}
	}
	
}
