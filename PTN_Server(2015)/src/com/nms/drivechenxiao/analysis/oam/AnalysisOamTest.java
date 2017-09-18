package com.nms.drivechenxiao.analysis.oam;

import java.util.ArrayList;
import java.util.List;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.oam.OamTestObject;

/**
 * OAM  测试功能
 * @author sy
 *
 */
public class AnalysisOamTest extends  CxtOpLump{
	/**
	 * oam测试
	 * @param oamTestObject
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] selectOamTest(OamTestObject oamTestObject,int session,int seqid){
		String path="ne/interfaces/";
		if("1".equals(oamTestObject.getOamType())){
			//段      端口名称   -----ge.3.1  --测试通过
			path +=oamTestObject.getType()+"/"+oamTestObject.getName()+"/tpoam.test";
		}else if("2".equals(oamTestObject.getOamType())){
			//   tunnel      tunnel/1/  oam.test          --测试通过
			path +=oamTestObject.getType()+"/"+oamTestObject.getName()+"/oam.test";
		}else if("3".equals(oamTestObject.getOamType())){
			//   pw     pweth/1/  oam.test               --测试通过
			path +=oamTestObject.getType()+"/"+oamTestObject.getName()+"/oam.test";
		}else if("4".equals(oamTestObject.getOamType())){
			//测试通过
			//  eline(以太网业务)      端口名称  +oam的名称    ---    eth/     ge.2.2/2/    oam.test---
			path +=oamTestObject.getType()+"/"+oamTestObject.getName()+"/oam.test";
		}else {
			path="";
		}
		
		List<CxtOpItem> cxtOpItems=new ArrayList<CxtOpItem>();
		cxtOpItems.add(begin(3));
		String oamtest="cnt="+oamTestObject.getCnt()+","+"datalen="+oamTestObject.getDatalen()+","+"ifoutofsvc="+oamTestObject.getIfoutofsvc();
		List<CxtAtomType> cxtAtomTypes=new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 1));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, oamtest));
		CxtATTable cxtATTable = getCxtATTable(cxtAtomTypes.size()/2, cxtAtomTypes);

		CxtAtomType cxtAtomType = new CxtAtomType();
		cxtAtomType.setCxtATTable(cxtATTable);
		cxtAtomType.setType("AT_TABLE");
		byte[] command = new byte[0];
		byte[] cxtString = getCxtString(path);
		byte[] cxtTable = getCxtAtomType(cxtAtomType);
		command = CoderUtils.arraycopy(command, cxtString);
		command = CoderUtils.arraycopy(command, cxtTable);
		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_ACALL, command, session, seqid);
		return command;
	}
	/**
	 * 解析
	 * @param command
	 * @return
	 */
	public List<OamTestObject> analysisOamTe(byte[] command){
		int start=31;
		List<OamTestObject> oamList=new ArrayList<OamTestObject>();
		byte[] tt = command;
		byte[] t = new byte[tt.length - start];
		System.arraycopy(tt, start, t, 0, tt.length - start);
		oamList = super.analysisTabble("oamtest", t);
		return oamList;
	}
	
}
