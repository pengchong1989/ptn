package com.nms.drivechenxiao.analysis.service;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.portpdh.PdhPortObject;
import com.nms.drivechenxiao.service.bean.portpdh.ac.PdhAcObject;
import com.nms.drivechenxiao.service.bean.protsdh.SdhPortObject;
import com.nms.drivechenxiao.service.bean.protsdh.ac.SdhAcObject;
import com.nms.drivechenxiao.service.bean.pwpdh.PwPdhObject;
import com.nms.drivechenxiao.service.bean.pwsdh.PwSdhObject;
import com.nms.drivechenxiao.service.bean.service.ces.CesObject;

public class AnalysisCes extends CxtOpLump {
	/**
	 * 创建PdhCes
	 * 
	 * @param eLineObject
	 * @param acObject
	 * @param pwEthObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createPdhCes(CesObject cesObject, PwPdhObject pwPdhObject, PdhPortObject pdhPortObject, PdhAcObject pdhAcObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtOpItem> a = createCesInterface(cesObject, session, seqid);
		List<CxtOpItem> b = createCesPw(pwPdhObject, session, seqid);
		List<CxtOpItem> c = createCesAc(pdhPortObject, pdhAcObject, session, seqid);

		cxtOpItems.add(begin(3));
		cxtOpItems.addAll(a);
		cxtOpItems.addAll(b);
		cxtOpItems.addAll(c);
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 创建SdhCes
	 * 
	 * @param cesObject
	 * @param pwPdhObject
	 * @param sdhPortObject
	 * @param sdhAcObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createSdhCes(CesObject cesObject, PwSdhObject pwSdhObject, SdhPortObject sdhPortObject, SdhAcObject sdhAcObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtOpItem> a = createCesInterface(cesObject, session, seqid);
		List<CxtOpItem> b = createCesSdhPw(pwSdhObject, session, seqid);
		List<CxtOpItem> c = createCesSdhAc(sdhPortObject, sdhAcObject, session, seqid);

		cxtOpItems.add(begin(3));
		cxtOpItems.addAll(a);
		cxtOpItems.addAll(b);
		cxtOpItems.addAll(c);
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 删除PDHces
	 * 
	 * @param eLineObject
	 * @param acObject
	 * @param pwEthObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deletePDHCes(CesObject cesObject, PwPdhObject pwPdhObject, PdhPortObject pdhPortObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/pwpdh/" + pwPdhObject.getName()));
		cxtOpItems.add(delete("service"));
		cxtOpItems.add(cd("ne/interfaces/pdh/" + pdhPortObject.getName()));
		cxtOpItems.add(delete("service"));
		cxtOpItems.add(cd("ne/ces"));
		cxtOpItems.add(delete(cesObject.getName()));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 删除SDHCES
	 * @param cesObject
	 * @param pwSdhObject
	 * @param sdhPortObject
	 * @param sdhAcObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteSDHCes(CesObject cesObject, PwSdhObject pwSdhObject, SdhPortObject sdhPortObject, SdhAcObject sdhAcObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/pwsdh/" + pwSdhObject.getName()));
		cxtOpItems.add(delete("service"));
		cxtOpItems.add(cd("ne/interfaces/sdh/" + sdhPortObject.getName() + "/" + sdhAcObject.getName()));
		cxtOpItems.add(delete("service"));
		cxtOpItems.add(cd("ne/ces"));
		cxtOpItems.add(delete(cesObject.getName()));
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public byte[] selectAllCes(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/ces"));
		cxtOpItems.add(get(s, 2));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	public List<CesObject> analysisCES(byte[] command, CXNEObject CXNEObject) {
		List<CesObject> cesObject = new ArrayList<CesObject>();
		int start = 49;
		byte[] tt = command;
		//		byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 0, -51, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 1, 32, 0, 0, 0, 1, 49, 48, 0, 0, 0, 8, 32, 0, 0, 0, 5, 97, 99, 110, 117, 109, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 97, 100, 109, 105, 110, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 100, 101, 115, 99, 32, 0, 0, 0, 7, 112, 116, 110, 32, 86, 80, 78, 32, 0, 0, 0, 4, 110, 97, 109, 101, 19, 0, 17, 0, 1, 32, 0, 0, 0, 4, 111, 112, 101, 114, 19, 0, 0, 0, 1, 32, 0, 0, 0, 8, 112, 111, 114, 116, 108, 105, 115, 116, 48, 0, 0, 0, 2, 19, 2, 3, 0, 1, 19, 2, 3, 0, 1, 19, 8, 49, 1, 1, 19, 8, 49, 1, 1, 32, 0, 0, 0, 5, 112, 119, 110, 117, 109, 19, 0, 0, 0, 0, 32, 0, 0, 0, 5, 118, 112, 110, 105, 100, 19, 0, 17, 0, 1, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		cesObject = super.analysisTabble("ces", t);
		return cesObject;
	}

	/*
	 * 创建ces接口
	 */
	private List<CxtOpItem> createCesInterface(CesObject cesObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, cesObject.getAdmin()));
		CxtATTable cxtATTable = getCxtATTable(1, cxtAtomTypes);

		cxtOpItems.add(cd("ne/ces"));
		cxtOpItems.add(create(cesObject.getName(), cxtATTable));

		return cxtOpItems;
	}

	/*
	 * 绑定pw
	 */
	private List<CxtOpItem> createCesPw(PwPdhObject pwPdhObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "_"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pwPdhObject.getCes().getType()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vpn"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ces/" + pwPdhObject.getCes().getVpn()));
		CxtATTable cxtATTable = getCxtATTable(2, cxtAtomTypes);

		cxtOpItems.add(cd("ne/interfaces/pwpdh/" + pwPdhObject.getName()));
		cxtOpItems.add(create("service", cxtATTable));

		return cxtOpItems;
	}

	/*
	 * 绑定SdhPw
	 */
	private List<CxtOpItem> createCesSdhPw(PwSdhObject pwsdhObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "_"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pwsdhObject.getCes().getType()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vpn"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ces/" + pwsdhObject.getCes().getVpn()));
		CxtATTable cxtATTable = getCxtATTable(2, cxtAtomTypes);

		cxtOpItems.add(cd("ne/interfaces/pwsdh/" + pwsdhObject.getName()));
		cxtOpItems.add(create("service", cxtATTable));

		return cxtOpItems;
	}

	/*
	 * 绑定ac
	 */
	private List<CxtOpItem> createCesAc(PdhPortObject pdhPortObject, PdhAcObject pdhAcObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "_"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, pdhAcObject.getCesServiceObject().getType()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vpn"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ces/" + pdhAcObject.getCesServiceObject().getVpn()));
		CxtATTable cxtATTable = getCxtATTable(2, cxtAtomTypes);

		cxtOpItems.add(cd("ne/interfaces/pdh/" + pdhPortObject.getName()));
		cxtOpItems.add(create("service", cxtATTable));

		return cxtOpItems;
	}

	/*
	 * 绑定SdhAc
	 */
	private List<CxtOpItem> createCesSdhAc(SdhPortObject sdhPortObject, SdhAcObject sdhAcObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "_"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, sdhAcObject.getCesServiceObject().getType()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "vpn"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ces/" + sdhAcObject.getCesServiceObject().getVpn()));
		CxtATTable cxtATTable = getCxtATTable(2, cxtAtomTypes);

		cxtOpItems.add(cd("ne/interfaces/sdh/" + sdhPortObject.getName() + "/" + sdhAcObject.getName()));
		cxtOpItems.add(create("service", cxtATTable));

		return cxtOpItems;
	}
	/**
	 * 更新Sdhces
	 * 
	 * @param cesObject
	 * @param sdhAcObject
	 *   通过 ac中 name(修改后)与 identify(修改前) 对比
	 *      相同 ：未修改                                   不相同： 与此ces关联的ac更改
	 * @param pwEthObject
	 *   通过 pw中 name(修改后)与identify(修改前) 对比
	 *      相同 ：未修改                                   不相同： 与此ces关联的ac更改
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateSdhCes(CesObject cesObject, SdhAcObject sdhAcObject, PwSdhObject pwSdhObject, SdhPortObject sdhPortObject, int session, int seqid) {
		try{
			if(cesObject==null){
				throw new Exception("cesObject is null");
			}
			if(sdhAcObject ==null){
				throw new Exception("sdhAcObject is null");
			}
			if(pwSdhObject==null){
				throw new Exception("pwSdhObject is null");
			}
			if(sdhPortObject==null){
				throw new Exception("sdhPortObject is null");
			}
		}catch (Exception e) {
			System.out.println("object is null");
		}
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/ces"+cesObject.getName()));
		cxtOpItems.add(set("admin",cesObject.getAdmin()));
		cxtOpItems.add(set("pwnum",cesObject.getPwnum()));
		/**
		 * 判断   更改前后的 pw名是否相同
		 * pw是否更改
		 */
		
		if(pwSdhObject.getIdentify()!=null&&pwSdhObject.getName()!=null&&!pwSdhObject.getName().equals(pwSdhObject.getIdentify())){
			//删除原有pw中的 service
			cxtOpItems.add(cd("ne/interfaces/pwsdh/" + pwSdhObject.getIdentify()));
			cxtOpItems.add(delete("service"));
			List<CxtOpItem> pw=createCesSdhPw(pwSdhObject,session,seqid);
			cxtOpItems.addAll(pw);
		}	
		/**
		 * 判断   更改前后的 ac,端口
		 *   只有端口，和ac都未修改时，与eline业务关联的ac才没有修改
		 *     否则： 修改与eline关联的ac 
		 */
		if(sdhPortObject.getName()!=null&&sdhPortObject.getIdentify()!=null&&sdhAcObject.getName()!=null&&sdhAcObject.getIdentify()!=null){
			if(sdhPortObject.getName().equals(sdhPortObject.getIdentify())&&sdhAcObject.getName().equals(sdhAcObject.getIdentify())){
				//ac未不修改，不做处理
			}else{
				//修改ac
				//删除原端口下的ac中的 service
				cxtOpItems.add(cd("ne/interfaces/sdh/" + sdhPortObject.getIdentify() + "/" + sdhAcObject.getIdentify()));
				cxtOpItems.add(delete("service"));
				// 为选中的 ac添加 service
				List<CxtOpItem> ac=createCesSdhAc(sdhPortObject,sdhAcObject,session,seqid);
				cxtOpItems.addAll(ac);			
				
			}
		}
		
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}
	/**
	 * 更新Pdhces
	 * 
	 * @param cesObject
	 * @param sdhAcObject
	 *   通过 ac中 name(修改后)与 identify(修改前) 对比
	 *      相同 ：未修改                                   不相同： 与此ces关联的ac更改
	 * @param pwEthObject
	 *   通过 pw中 name(修改后)与identify(修改前) 对比
	 *      相同 ：未修改                                   不相同： 与此ces关联的ac更改
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updatePdhCes(CesObject cesObject, PdhAcObject pdhAcObject, PwPdhObject pwpdhObject, PdhPortObject pdhPortObject, int session, int seqid) {
		
		try{
			if(cesObject==null){
				throw new Exception("cesObject is null");
			}
			if(pdhAcObject ==null){
				throw new Exception("pdhAcObject is null");
			}
			if(pwpdhObject==null){
				throw new Exception("pwpdhObject is null");
			}
			if(pdhPortObject==null){
				throw new Exception("pdhPortObject is null");
			}
		}catch (Exception e) {
			System.out.println("object is null");
		}
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		//修改使能（管理状态）
		cxtOpItems.add(cd("ne/ces/"+cesObject.getName()));
		cxtOpItems.add(set("admin",cesObject.getAdmin()));
		/**
		 * 判断   更改前后的 pw名是否相同
		 * pw是否更改
		 */

		if(pwpdhObject.getIdentify()!=null&&!pwpdhObject.getName().equals(pwpdhObject.getIdentify())){
			//删除原有pw中的 service
			cxtOpItems.add(cd("ne/interfaces/pwpdh/" + pwpdhObject.getIdentify()));
			cxtOpItems.add(delete("service"));
			List<CxtOpItem> pw=createCesPw(pwpdhObject,session,seqid);
			cxtOpItems.addAll(pw);
		}	
		/**
		 * 判断   更改前后的 ac,端口
		 *   只有端口，和ac都未修改时，与eline业务关联的ac才没有修改
		 *     否则： 修改与eline关联的ac 
		 */
// 203测试成功（删除-新建）
		if(pdhPortObject.getName().equals(pdhPortObject.getIdentify())&&pdhAcObject.getName().equals(pdhAcObject.getIdentify())){
			//ac未不修改，不做处理
		}else{
			//修改ac
			//删除原端口下的ac中的 service
			cxtOpItems.add(cd("ne/interfaces/pdh/" + pdhPortObject.getIdentify()));
			cxtOpItems.add(delete("service"));
			// 为选中的 ac添加 service
			List<CxtOpItem> ac=createCesAc(pdhPortObject,pdhAcObject,session,seqid);
			cxtOpItems.addAll(ac);						
		}
		cxtOpItems.add(commit());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

}
