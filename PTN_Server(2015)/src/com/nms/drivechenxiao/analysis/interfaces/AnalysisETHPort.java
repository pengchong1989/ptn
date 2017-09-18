package com.nms.drivechenxiao.analysis.interfaces;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.oam.OamObject;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.nni.NNIObject;
import com.nms.drivechenxiao.service.bean.porteth.uni.UNIObject;

public class AnalysisETHPort extends CxtOpLump {

	/**
	 * 设置端口固有属性
	 * 
	 * @param ethPortObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setPort(EthPortObject ethPortObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String path = "ne/interfaces/eth/" + ethPortObject.getName();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(path));
		cxtOpItems.add(set("role", ethPortObject.getRole()));
		cxtOpItems.add(set("admin", ethPortObject.getAdmin()));
		// cxtOpItems.add(set("mac", ethPortObject.getMac()));
		// add by 1308
		//

		//
		// if(have(ethPortObject.getSfptype()))cxtOpItems.add(set("sfptype",ethPortObject.getSfptype())); //SFP实际类型 //lsp不让改
		// if(have(ethPortObject.getSfpvendor()))cxtOpItems.add(set("sfpvendor",ethPortObject.getSfpvendor())); //sfp厂家信息//lsp不让改
		// if(have(ethPortObject.getWavelength()))cxtOpItems.add(set("wavelength",ethPortObject.getWavelength())); //工作波长 //lsp不让改
		// if(have(ethPortObject.getAspeed()))cxtOpItems.add(set("aspeed",ethPortObject.getAspeed()));//实际速率 //lsp不让改
		// --
		String sfpexptypeC = getSfpexptypeS(ethPortObject.getSfpexptype());
		if (have(sfpexptypeC))
			cxtOpItems.add(set("sfpexptype", sfpexptypeC)); // SFP期望类型 对应code表主键

		// framelen 属性在700e设备上总是出错，特此暂时屏蔽700e设备此属性下发。20130830
		if (!ethPortObject.getNeType().equals("cxt20a")) {
			if (have(ethPortObject.getFramelen()))
				cxtOpItems.add(set("framelen", ethPortObject.getFramelen())); // 最大帧长=武汉MTU --
		}

		String speedS = getSpeedS(ethPortObject.getSpeed());
		if (have(speedS))
			cxtOpItems.add(set("speed", speedS));// 设置端口速率，对应code表主键

		// if(have(speedS))cxtOpItems.add(set("speed","1g"));

		if (have(ethPortObject.getMac()))
			cxtOpItems.add(setmac("mac", (CoderUtils.MacTo1a(ethPortObject.getMac()))));// mac地址 必须 是 1a的8位数字才能被设备识别。特此转换

		// if(!ethPortObject.getNeType().equals("cxt20a")){//700e设备无xgwan属性
		// if(have(ethPortObject.getXgwan()))cxtOpItems.add(set("xgwan",ethPortObject.getXgwan())); //启动10G WAN
		// if(have(ethPortObject.getSlowproto_tocpu()))cxtOpItems.add(set("slowproto_tocpu",ethPortObject.getSlowproto_tocpu()));
		// if(have(ethPortObject.getFc()))cxtOpItems.add(set("fc",ethPortObject.getFc())); //流控=武汉802.3流控
		// }

		// if(have(ethPortObject.getPermitpktloop()))cxtOpItems.add(set("permitpktloop",ethPortObject.getPermitpktloop()));//是否启动报文环回
		// if(have(ethPortObject.getIused()))cxtOpItems.add(set("iused",ethPortObject.getIused()));//入口限速
		// if(have(ethPortObject.getOused()))cxtOpItems.add(set("oused",ethPortObject.getOused()));//出口限速

		// System.out.println("analysisethport.setPort . ethPortObject = "+ethPortObject.toString());
		// System.out.println("analysisethport.setPort . sfpexptype="+sfpexptypeC+" ;speedS="+speedS+" ;framelen="+ethPortObject.getFramelen()+" ;mac="+ethPortObject.getMac()+" ;xgwan="+ethPortObject.getXgwan()+" ; slowproto_tocpu="+ethPortObject.getSlowproto_tocpu()+" ;fc="+ethPortObject.getFc()+" ;___ethPortObject = "+ethPortObject.toString());
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	private boolean have(String haveIt) {
		if ("".equals(haveIt) || haveIt.equals(null)) {
			return false;
		} else
			return true;
	}

	/**
	 * 设置uni端口属性
	 * 
	 * @param ethPortObject
	 * @param object
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setPortUNI(EthPortObject ethPortObject, UNIObject object, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String path = "ne/interfaces/eth/" + ethPortObject.getName();
		cxtOpItems.add(begin(11));
		cxtOpItems.add(cd(path + "/uni"));
		cxtOpItems.add(set("iclrmode", object.getIclrmode())); // ETHAC进来的颜色模式(信任CFI/信任VLAN优先级) **
		cxtOpItems.add(set("oclrmode", object.getOclrmode())); // ETHAC出去的颜色模式(色盲(blind)/信任)) **
		cxtOpItems.add(set("cos2vlanpri", object.getCos2vlanpri())); // 细分ETHAC出去的流 **
		// ----
		if (!ethPortObject.getNeType().equals("cxt20a")) {// 700e设备无xgwan属性
			String vlanmodeS = getvlanmodeS(object.getVlanmode());
			if (have(vlanmodeS))
				cxtOpItems.add(set("vlanmode", vlanmodeS));// 以太网VLAN模式
		}
		String sdtpidS = getSdtpidS(object.getSdtpid());
		if (have(sdtpidS))
			cxtOpItems.add(set("sdtpid", sdtpidS));// 运营商VLAN的TPID

		String tpidS = getSdtpidS(object.getTpid());
		if (have(tpidS))
			cxtOpItems.add(set("tpid", tpidS)); // OUTER VLAN的TPID

		String encapS = getEncapS(object.getEncap());
		cxtOpItems.add(set("encap", encapS));// 以太网封装

//		 cxtOpItems.add(set("pvid", object.getPvid()));
		 cxtOpItems.add(set("pvpri", object.getPvpri()));
		// sdtpid
		cxtOpItems.add(set("bcastlimit", object.getBcastlimit()));// 广播报文抑制
		cxtOpItems.add(set("mcastlimit", object.getMcastlimit()));// 组播报文抑制
		cxtOpItems.add(set("dlflimit", object.getDlflimit()));// 未知单播报文抑制
		// System.out.println("analysisethport.setUni . UNIobject : "+object.toString()+" ; ethPortObject="+ethPortObject.toString());

		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 设置nni端口属性
	 * 
	 * @param ethPortObject
	 * @param object
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setPortNNI(EthPortObject ethPortObject, NNIObject object, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String path = "ne/interfaces/eth/" + ethPortObject.getName();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(path + "/nni"));
		// ----
		cxtOpItems.add(set("pvid", object.getPvid()));// NNI接口 VLAN ID
		// cxtOpItems.add(set("pvid", object.getCcnEnableString()));
		if (have(object.getLlspexptoclr())) {
			cxtOpItems.add(setNum("llspexptoclr", object.getLlspexptoclr()));
		}
		if (have(object.getLlspclrtoexp())) {
			cxtOpItems.add(setNum("llspclrtoexp", object.getLlspclrtoexp()));
		}
		if (have(object.getElspcostoexp())) {
			cxtOpItems.add(setNum("elspexptocos", object.getElspcostoexp()));
		}
		if (have(object.getElspexptocos())) {
			cxtOpItems.add(setNum("elspcostoexp", object.getElspexptocos()));
		}
		cxtOpItems.add(cd(path + "/nbr"));
		if (have(ethPortObject.getNbr().getSmac()))
			cxtOpItems.add(setmac("smac", (CoderUtils.MacTo1a(ethPortObject.getNbr().getSmac()))));
		// mac , ifidx 2个属性 是只读属性，不让下发
		// if(have(ethPortObject.getNbr().getMac()))cxtOpItems.add(setmac("mac",(CoderUtils.MacTo1a(ethPortObject.getNbr().getMac())) ));
		// if(have(ethPortObject.getNbr().getIfidx()))cxtOpItems.add(set("ifidx", ethPortObject.getNbr().getIfidx()));
		if (have(ethPortObject.getNbr().getCcn()))
			cxtOpItems.add(set("ccn", ethPortObject.getNbr().getCcn()));

		cxtOpItems.add(commit());
		// System.out.println("analysisethport.setNni . object : "+object.toString()+" ;ethportObject="+ethPortObject.toString());
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 设置段属性
	 * 
	 * @param ethPortObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] setSegment(EthPortObject ethPortObject, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		byte[] command = null;
		String path = "ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName();
		cxtOpItems.add(begin(3));
		// if ("cxt20a".equals(ethPortObject.getNeType())) {
		// cxtOpItems.add(cd(path + "/af2"));
		// cxtOpItems.add(set("cir", ethPortObject.getAf2().getCir()));
		// cxtOpItems.add(cd(path + "/af1"));
		// cxtOpItems.add(set("cir", ethPortObject.getAf1().getCir()));
		// cxtOpItems.add(cd(path + "/be"));
		// cxtOpItems.add(set("yellowwredstart", ethPortObject.getBe().getYellowwredstart()));
		// cxtOpItems.add(cd(path + "/cs3"));
		// cxtOpItems.add(set("cir", ethPortObject.getCs3().getCir()));
		// } else {
		cxtOpItems.add(cd(path + "/cs7"));
		cxtOpItems.add(set("cir", ethPortObject.getCs7().getCir()));
		cxtOpItems.add(cd(path + "/cs6"));
		cxtOpItems.add(set("cir", ethPortObject.getCs6().getCir()));
		cxtOpItems.add(cd(path + "/ef"));
		cxtOpItems.add(set("cir", ethPortObject.getEf().getCir()));
		cxtOpItems.add(cd(path + "/af4"));
		cxtOpItems.add(set("cir", ethPortObject.getAf4().getCir()));
		cxtOpItems.add(cd(path + "/af3"));
		cxtOpItems.add(set("cir", ethPortObject.getAf3().getCir()));
		cxtOpItems.add(cd(path + "/af2"));
		cxtOpItems.add(set("cir", ethPortObject.getAf2().getCir()));
		cxtOpItems.add(cd(path + "/af1"));
		cxtOpItems.add(set("cir", ethPortObject.getAf1().getCir()));
		cxtOpItems.add(cd(path + "/be"));
		cxtOpItems.add(set("yellowwredstart", ethPortObject.getBe().getYellowwredstart()));
		// }
		if (oamObject != null) {
			String type = "";
			if ("lag".equals(ethPortObject.getPortType())) {
				type = "LAG";
			} else {
				type = "ETH";
			}
			List<CxtOpItem> a = super.createOAM(type, path, oamObject, session, seqid);
			cxtOpItems.addAll(a);
			cxtOpItems.add(commit());
			command = getCommandBytes(cxtOpItems);
			command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		} else {
			cxtOpItems.add(commit());
			command = getCommandBytes(cxtOpItems);
			command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		}
		return command;
	}

	/**
	 * 修改段属性
	 * 
	 * @param ethPortObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateSegment(EthPortObject ethPortObject, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String path = "ne/interfaces/" + ethPortObject.getPortType() + "/" + ethPortObject.getName();
		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd(path + "/cs7"));
		cxtOpItems.add(set("cir", ethPortObject.getCs7().getCir()));
		cxtOpItems.add(cd(path + "/cs6"));
		cxtOpItems.add(set("cir", ethPortObject.getCs6().getCir()));
		cxtOpItems.add(cd(path + "/ef"));
		cxtOpItems.add(set("cir", ethPortObject.getEf().getCir()));
		cxtOpItems.add(cd(path + "/af4"));
		cxtOpItems.add(set("cir", ethPortObject.getAf4().getCir()));
		cxtOpItems.add(cd(path + "/af3"));
		cxtOpItems.add(set("cir", ethPortObject.getAf3().getCir()));
		cxtOpItems.add(cd(path + "/af2"));
		cxtOpItems.add(set("cir", ethPortObject.getAf2().getCir()));
		cxtOpItems.add(cd(path + "/af1"));
		cxtOpItems.add(set("cir", ethPortObject.getAf1().getCir()));
		cxtOpItems.add(cd(path + "/be"));
		cxtOpItems.add(set("yellowwredstart", ethPortObject.getBe().getYellowwredstart()));
		if (oamObject != null) {
			cxtOpItems.addAll(updateOAM("ETH", path, oamObject, session, seqid));
		}
		cxtOpItems.add(commit());

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 删除段OAM
	 * 
	 * @param ethPortObject
	 * @param oamObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteSegmentOAM(EthPortObject ethPortObject, OamObject oamObject, int session, int seqid) {
		String path = "ne/interfaces/eth/" + ethPortObject.getName();
		List<CxtOpItem> CxtOpItems = new ArrayList<CxtOpItem>();
		CxtOpItems.add(begin(3));
		CxtOpItems.addAll(super.deleteOAM("ETH", path, oamObject, session, seqid));
		CxtOpItems.add(commit());
		byte[] command = getCommandBytes(CxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 查询ETH端口
	 * 
	 * @param ethPortObjectList
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] selectETHPort(List<EthPortObject> ethPortObjectList, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "admin"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "oper"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ifname"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "role"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "aspeed"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "speed"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "mac"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "framelen"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "fc"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "wavelength"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "sfptype"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "sfpvendor"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "xgwan"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "permitpktloop"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "sfpexptype"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		for (int i = 0; i < ethPortObjectList.size(); i++) {
			cxtOpItems.add(cd("ne/interfaces/eth/" + ethPortObjectList.get(i).getName()));
			cxtOpItems.add(get(cxtATTable, 1));
		}

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);

		return command;
	}

	/**
	 * 查询所有eth端口
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] selectAllEthPort(int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		String s = null;

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/eth"));
		cxtOpItems.add(get(s, 2));

		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);
		return command;
	}

	/**
	 * 解析查询ETH端口
	 * 
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public List<EthPortObject> analysisETHPort(byte[] command, CXNEObject CXNEObject) {
		List<EthPortObject> ethPortObject = new ArrayList<EthPortObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		ethPortObject = super.analysisTabble("ethport", t);
		return ethPortObject;
	}

	public List<EthPortObject> analysisAllETHPort(byte[] command, CXNEObject CXNEObject) {
		List<EthPortObject> ethPortObject = new ArrayList<EthPortObject>();
		int start = 49;
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		ethPortObject = super.analysisTabble("allethport", t);
		// for(EthPortObject e:ethPortObject){
		// System.out.println("-------------"+e.toString());
		// }
		return ethPortObject;
	}

	/**
	 * 查询端口UNI
	 * 
	 * @param ethPortObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] selectETHPortUNI(EthPortObject ethPortObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes1 = new ArrayList<CxtAtomType>();
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "encap"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "pvid"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "pvpri"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "tpid"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "sdtpid"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "vlanmode"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "bcastlimit"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		CxtATTable cxtATTable1 = getCxtATTable(cxtAtomTypes1.size() / 2, cxtAtomTypes1);

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "uni"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTable1));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/eth/" + ethPortObject.getName()));
		cxtOpItems.add(get(cxtATTable, 1));
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);

		return command;
	}

	/**
	 * 解析查询端口uni
	 * 
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public EthPortObject analysisETHPortUNI(byte[] command, CXNEObject CXNEObject) {
		List<EthPortObject> ethPortObject = new ArrayList<EthPortObject>();
		int start = 49;
		byte[] tt = command;
		// byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 0, -84, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 117, 110, 105, 48, 0, 0, 0, 7, 32, 0, 0, 0, 10, 98, 99, 97, 115, 116, 108, 105, 109, 105, 116, 19, -1, -1, -1, -1, 32, 0, 0, 0, 5, 101, 110, 99, 97, 112, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 112, 118, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 5, 112, 118, 112, 114, 105, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 115, 100, 116, 112, 105, 100, 19, 0, 0, 0, 1, 32, 0, 0, 0, 4, 116, 112, 105, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 8, 118, 108, 97, 110, 109, 111, 100, 101, 19, 0, 0, 0, 3, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		ethPortObject = super.analysisTabble("uni", t);
		// System.out.println(ethPortObject.get(0).getUni().getEncap());
		// System.out.println(ethPortObject.get(0).getUni().getPvid());
		// System.out.println(ethPortObject.get(0).getUni().getPvpri());
		// System.out.println(ethPortObject.get(0).getUni().getTpid());
		// System.out.println(ethPortObject.get(0).getUni().getSdtpid());
		// System.out.println(ethPortObject.get(0).getUni().getVlanmode());
		// System.out.println(ethPortObject.get(0).getUni().getBcastlimit());
		return ethPortObject.get(0);
	}

	/**
	 * 查询端口NNI
	 * 
	 * @param ethPortObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] selectETHPortNNI(EthPortObject ethPortObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes1 = new ArrayList<CxtAtomType>();
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "smac"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "stat"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "mac"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "ifidx"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "neid"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "ccn"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		CxtATTable cxtATTable1 = getCxtATTable(cxtAtomTypes1.size() / 2, cxtAtomTypes1);

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "nbr"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTable1));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/eth/" + ethPortObject.getName()));
		cxtOpItems.add(get(cxtATTable, 1));
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);

		return command;
	}

	/**
	 * 解析查询端口nni
	 * 
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public EthPortObject analysisETHPortNNI(byte[] command, CXNEObject CXNEObject) {
		List<EthPortObject> ethPortObject = new ArrayList<EthPortObject>();
		int start = 49;
		byte[] tt = command;
		// byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 0, -112, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 1, 32, 0, 0, 0, 3, 110, 98, 114, 48, 0, 0, 0, 6, 32, 0, 0, 0, 3, 99, 99, 110, 1, 1, 32, 0, 0, 0, 5, 105, 102, 105, 100, 120, 19, 8, 18, 3, 1, 32, 0, 0, 0, 3, 109, 97, 99, 26, 66, -128, -48, -96, 0, 1, 24, 0, 32, 0, 0, 0, 4, 110, 101, 105, 100, 19, 20, 0, 0, 35, 32, 0, 0, 0, 4, 115, 109, 97, 99, 19, 0, 0, 0, 0, 32, 0, 0, 0, 4, 115, 116, 97, 116, 19, 0, 0, 0, 1, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		ethPortObject = super.analysisTabble("nni", t);
		// System.out.println(ethPortObject.get(0).getNbr().getCcn());
		// System.out.println(ethPortObject.get(0).getNbr().getIfidx());
		// System.out.println(ethPortObject.get(0).getNbr().getMac());
		// System.out.println(ethPortObject.get(0).getNbr().getNeid());
		// System.out.println(ethPortObject.get(0).getNbr().getSmac());
		// System.out.println(ethPortObject.get(0).getNbr().getStat());
		return ethPortObject.get(0);
	}

	public byte[] selectETHPortQOS(EthPortObject ethPortObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypesAf = new ArrayList<CxtAtomType>();
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "cir"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "greenwredstart"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "greenwredend"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "yellowwredstart"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "yellowwredend"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "used"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "weight"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "greendroprate"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "yellowdroprate"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "wreden"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "maxbw"));
		cxtAtomTypesAf.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		CxtATTable cxtATTableAf = getCxtATTable(cxtAtomTypesAf.size() / 2, cxtAtomTypesAf);

		List<CxtAtomType> cxtAtomTypescs = new ArrayList<CxtAtomType>();
		cxtAtomTypescs.add(getCxtAtomType(CxtAtomType.AT_STRING, "cir"));
		cxtAtomTypescs.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypescs.add(getCxtAtomType(CxtAtomType.AT_STRING, "greenwredstart"));
		cxtAtomTypescs.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypescs.add(getCxtAtomType(CxtAtomType.AT_STRING, "greenwredend"));
		cxtAtomTypescs.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypescs.add(getCxtAtomType(CxtAtomType.AT_STRING, "used"));
		cxtAtomTypescs.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypescs.add(getCxtAtomType(CxtAtomType.AT_STRING, "greendroprate"));
		cxtAtomTypescs.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypescs.add(getCxtAtomType(CxtAtomType.AT_STRING, "wreden"));
		cxtAtomTypescs.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		CxtATTable cxtATTableCs = getCxtATTable(cxtAtomTypescs.size() / 2, cxtAtomTypescs);

		List<CxtAtomType> cxtAtomTypesBe = new ArrayList<CxtAtomType>();
		cxtAtomTypesBe.add(getCxtAtomType(CxtAtomType.AT_STRING, "yellowwredstart"));
		cxtAtomTypesBe.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypesBe.add(getCxtAtomType(CxtAtomType.AT_STRING, "yellowwredend"));
		cxtAtomTypesBe.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypesBe.add(getCxtAtomType(CxtAtomType.AT_STRING, "weight"));
		cxtAtomTypesBe.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypesBe.add(getCxtAtomType(CxtAtomType.AT_STRING, "yellowdroprate"));
		cxtAtomTypesBe.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		cxtAtomTypesBe.add(getCxtAtomType(CxtAtomType.AT_STRING, "wreden"));
		cxtAtomTypesBe.add(getCxtAtomType(CxtAtomType.AT_STRING, "true"));
		CxtATTable cxtATTableBe = getCxtATTable(cxtAtomTypesBe.size() / 2, cxtAtomTypesBe);

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "af1"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTableAf));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "af2"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTableAf));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "af3"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTableAf));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "af4"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTableAf));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "be"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTableBe));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "ef"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTableCs));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "cs6"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTableCs));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "cs7"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTableCs));
		CxtATTable cxtATTableAf1 = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(begin(3));
		cxtOpItems.add(cd("ne/interfaces/eth/" + ethPortObject.getName()));
		cxtOpItems.add(get(cxtATTableAf1, 1));
		byte[] command = getCommandBytes(cxtOpItems);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_OPS, command, session, seqid);

		return command;
	}

	public EthPortObject analysisETHPortQOS(byte[] command, CXNEObject CXNEObject) {
		List<EthPortObject> ethPortObjectQOSList = new ArrayList<EthPortObject>();
		EthPortObject ethPortObject = new EthPortObject();
		int start = 49;
		byte[] tt = command;
		// byte[] tt = new byte[] { 114, 109, 116, 1, 0, 0, 5, -111, 34, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 48, 0, 0, 0, 8, 32, 0, 0, 0, 3, 97, 102, 49, 48, 0, 0, 0, 11, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, -6, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 5, 109, 97, 120, 98, 119, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0, 0, 6, 119, 114,
		// 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, 96, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 64, 32, 0, 0, 0, 3, 97, 102, 50, 48, 0, 0, 0, 11, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 5, 109, 97, 120, 98, 119, 19, -1, -1, -1,
		// -1, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, 96, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 64, 32, 0, 0, 0, 3, 97, 102, 51, 48, 0, 0, 0, 11, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0,
		// 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 5, 109, 97, 120, 98, 119, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, 96, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 64, 32, 0, 0, 0, 3, 97, 102, 52, 48, 0, 0, 0, 11, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112,
		// 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 5, 109, 97, 120, 98, 119, 19, -1, -1, -1, -1, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, 96, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 64, 32, 0, 0,
		// 0, 2, 98, 101, 48, 0, 0, 0, 5, 32, 0, 0, 0, 6, 119, 101, 105, 103, 104, 116, 19, 0, 0, 0, 16, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 14, 121, 101, 108, 108, 111, 119, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 13, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -64, 32, 0, 0, 0, 15, 121, 101, 108, 108, 111, 119, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 64, 32, 0, 0, 0, 3, 99, 115, 54, 48, 0, 0, 0, 6, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101,
		// 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 3, 99, 115, 55, 48, 0, 0, 0, 6, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101, 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 32, 0, 0, 0, 2, 101, 102, 48, 0, 0, 0, 6, 32, 0, 0, 0, 3, 99, 105, 114, 19, 0, 0, 0, 0, 32, 0, 0, 0, 13, 103, 114, 101,
		// 101, 110, 100, 114, 111, 112, 114, 97, 116, 101, 19, 0, 0, 0, 100, 32, 0, 0, 0, 12, 103, 114, 101, 101, 110, 119, 114, 101, 100, 101, 110, 100, 19, 0, 0, 0, -128, 32, 0, 0, 0, 14, 103, 114, 101, 101, 110, 119, 114, 101, 100, 115, 116, 97, 114, 116, 19, 0, 0, 0, 96, 32, 0, 0, 0, 4, 117, 115, 101, 100, 19, 0, 0, 0, 0, 32, 0, 0, 0, 6, 119, 114, 101, 100, 101, 110, 1, 1, 0, };
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		ethPortObjectQOSList = super.analysisTabble("ethportqos", t);
		ethPortObject.setAf1(ethPortObjectQOSList.get(0).getAf1());
		ethPortObject.setAf2(ethPortObjectQOSList.get(1).getAf2());
		ethPortObject.setAf3(ethPortObjectQOSList.get(2).getAf3());
		ethPortObject.setAf4(ethPortObjectQOSList.get(3).getAf4());
		ethPortObject.setBe(ethPortObjectQOSList.get(4).getBe());
		ethPortObject.setCs6(ethPortObjectQOSList.get(5).getCs6());
		ethPortObject.setCs7(ethPortObjectQOSList.get(6).getCs7());
		ethPortObject.setEf(ethPortObjectQOSList.get(7).getEf());
		// System.out.println(ethPortObject.get(0).getNbr().getCcn());
		// System.out.println(ethPortObject.get(0).getNbr().getIfidx());
		// System.out.println(ethPortObject.get(0).getNbr().getMac());
		// System.out.println(ethPortObject.get(0).getNbr().getNeid());
		// System.out.println(ethPortObject.get(0).getNbr().getSmac());
		// System.out.println(ethPortObject.get(0).getNbr().getStat());
		return ethPortObject;
	}

	/**
	 * 转换由val得到 下发需要字符串
	 * **/
	private String getSfpexptypeS(String sfpexptype) {
		if ("0".equals(sfpexptype))
			return "unknown";
		if ("1".equals(sfpexptype))
			return "Empty";
		if ("2".equals(sfpexptype))
			return "RateMatch";
		if ("6".equals(sfpexptype))
			return "1000BASE_SX";
		if ("7".equals(sfpexptype))
			return "1000BASE_LX";
		if ("8".equals(sfpexptype))
			return "1000BASE_EX";
		if ("9".equals(sfpexptype))
			return "1000BASE_ZX";
		if ("10".equals(sfpexptype))
			return "1000BASE_T";
		return "";
	}

	private String getSpeedS(String speed) {
		if ("0".equals(speed))
			return "100m";
		if ("1".equals(speed))
			return "1g";
		if ("2".equals(speed))
			return "10g";
		if ("3".equals(speed))
			return "neg100m";
		if ("4".equals(speed))
			return "neg1g";
		if ("5".equals(speed))
			return "2g5";
		return "";
	}

	private String getvlanmodeS(String vlan) {
		if ("1".equals(vlan))
			return "access";
		if ("2".equals(vlan))
			return "trunk";
		if ("3".equals(vlan))
			return "hybrid";
		return "";

	}

	private String getSdtpidS(String sdtpid) {
		if ("0".equals(sdtpid))
			return "8100";
		if ("1".equals(sdtpid))
			return "88a8";
		if ("3".equals(sdtpid))
			return "9100";
		return "";
	}

	private String getEncapS(String encap) {
		if ("1".equals(encap))
			return "1q";
		if ("2".equals(encap))
			return "1ad";
		return "";
	}
}
