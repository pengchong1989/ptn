package com.nms.drivechenxiao.analysis.oam;

import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.oam.OamMipObject;
import com.nms.drivechenxiao.service.bean.oam.OamObject;
//oam
/**oam管理里面的 以太网业务oam,
 * 对应设备目录为: ne/interfaces/eth/XX.X.X/oam
 * **/
public class AnalysisOAM extends CxtOpLump {

	/**
	 * 单独创建OAM
	 * @param type
	 * @param cdPath
	 * @param oamObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createOAMByte(String type, String cdPath, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = createOAM(type, cdPath, oamObject, session, seqid);
		cxtOpItems.add(0, begin(3));
		cxtOpItems.add(commit());
		return cxtOpItemsToCommand(cxtOpItems, session, seqid);
	}

	/**
	 * 单独修改OAM
	 * @param type
	 * @param cdPath
	 * @param oamObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateOAMByte(String type, String cdPath, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = updateOAM(type, cdPath, oamObject, session, seqid);
		cxtOpItems.add(0, begin(3));
		cxtOpItems.add(commit());
		return cxtOpItemsToCommand(cxtOpItems, session, seqid);
	}

	/**
	 * 单独删除OAM
	 * @param type
	 * @param cdPath
	 * @param oamObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] deleteOAMByte(String type, String cdPath, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = deleteOAM(type, cdPath, oamObject, session, seqid);
		cxtOpItems.add(0, begin(3));
		cxtOpItems.add(commit());
		return cxtOpItemsToCommand(cxtOpItems, session, seqid);
	}

	/**
	 * 单独创建MipOam
	 * @param type
	 * @param cdPath
	 * @param oamObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] createMipOAMByte(String cdPath, OamMipObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = createMipOAM1("create", cdPath, oamObject, session, seqid);
		cxtOpItems.add(0, begin(3));
		cxtOpItems.add(commit());
		return cxtOpItemsToCommand(cxtOpItems, session, seqid);
	}

	/**
	 * 单独修改MipOam
	 * @param cdPath
	 * @param oamObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] updateMipOAMByte(String cdPath, OamMipObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = createMipOAM1("update", cdPath, oamObject, session, seqid);
		cxtOpItems.add(0, begin(3));
		cxtOpItems.add(commit());
		return cxtOpItemsToCommand(cxtOpItems, session, seqid);
	}

	/**
	 * 创建MipOam
	 * @param cdPath
	 * @param oamObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public List<CxtOpItem> createMipOAM(String cdPath, OamMipObject oamObject, int session, int seqid) {
		return createMipOAM1("create", cdPath, oamObject, session, seqid);
	}

	/**
	 * 修改MipOam
	 * @param cdPath
	 * @param oamObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public List<CxtOpItem> updateMipOAM(String cdPath, OamMipObject oamObject, int session, int seqid) {
		return createMipOAM1("update", cdPath, oamObject, session, seqid);
	}

	/**
	 * 创建OAM
	 * 
	 * @param type
	 * @param cdPath
	 * @param oamObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	@Override
	public List<CxtOpItem> createOAM(String type, String cdPath, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems = createOAM1(type, "create", cdPath, oamObject, session, seqid);
		return cxtOpItems;
	}

	/**
	 * 修改OAM
	 * 
	 * @param type
	 * @param cdPath
	 * @param oamObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	@Override
	public List<CxtOpItem> updateOAM(String type, String cdPath, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems = createOAM1(type, "update", cdPath, oamObject, session, seqid);
		return cxtOpItems;
	}

	/**
	 * 删除OAM
	 * 
	 * @param ethPortObject
	 * @param oamObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	@Override
	public List<CxtOpItem> deleteOAM(String type, String cdPath, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();
		cxtOpItems.add(cd(cdPath));
		if (type != null && type.equals("ETH")) {
			cxtOpItems.add(delete("tpoam"));
		} else {
			cxtOpItems.add(delete("oam"));
		}

		return cxtOpItems;
	}

	/*
	 * 
	 */
	private List<CxtOpItem> createOAM1(String classtype, String type, String cdPath, OamObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "megid"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, oamObject.getMegid()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "mepid"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, oamObject.getMepid()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "peer"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, oamObject.getPeer()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "lvl"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, Integer.valueOf(oamObject.getLvl())));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "cvintvl"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, oamObject.getCvintvl()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "lpbtimeout"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, Integer.valueOf(oamObject.getLpbtimeout())));
//				cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "encsf"));
//				cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, Boolean.valueOf(oamObject.getEncsf())));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "iflck"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_BOOL, oamObject.getIflck()));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(cd(cdPath));
		if ("create".equals(type)) {
			if (type != null && classtype.equals("ETH")) {
				cxtOpItems.add(create("tpoam", cxtATTable));
			} else {
				cxtOpItems.add(create("oam", cxtATTable));
			}
		} else if ("update".equals(type)) {
			if (type != null && classtype.equals("ETH")) {
				cxtOpItems.add(mset("tpoam", cxtATTable));
			} else {
				cxtOpItems.add(mset("oam", cxtATTable));
			}
		}

		return cxtOpItems;
	}

	private List<CxtOpItem> createMipOAM1(String type, String cdPath, OamMipObject oamObject, int session, int seqid) {
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes1 = new ArrayList<CxtAtomType>();
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, "1"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, oamObject.getAMepId()));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, "2"));
		cxtAtomTypes1.add(getCxtAtomType(CxtAtomType.AT_NUM_32, oamObject.getZMepId()));
		CxtATTable cxtATTable1 = getCxtATTable(cxtAtomTypes1.size() / 2, cxtAtomTypes1);

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "megid"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, oamObject.getMegid()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "mipid"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, oamObject.getMipid()));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "mepids"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_TABLE, cxtATTable1));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);

		cxtOpItems.add(cd(cdPath));
		if ("create".equals(type)) {
			cxtOpItems.add(create("oam", cxtATTable));
		} else if ("update".equals(type)) {
			cxtOpItems.add(mset("oam", cxtATTable));
		}

		return cxtOpItems;
	}

}
