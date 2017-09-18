package com.nms.drivechenxiao.analysis.proc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import sun.net.TelnetInputStream;
import sun.net.ftp.FtpClient;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.persvr.PersvrObject;

public class AnalysisPersvr extends CxtOpLump {

	/**
	 * 查询所有性能
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] getPersvr(int slot, String mh, int session, int seqid) {
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 1));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, slot));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 2));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, mh));
		CxtATTable cxtATTable = getCxtATTable(2, cxtAtomTypes);

		CxtAtomType cxtAtomType = new CxtAtomType();
		cxtAtomType.setCxtATTable(cxtATTable);
		cxtAtomType.setType("AT_TABLE");

		byte[] command = new byte[0];
		byte[] cxtString = getCxtString("ne/proc/persvr.getcurper");
		byte[] cxtTable = getCxtAtomType(cxtAtomType);
		command = CoderUtils.arraycopy(command, cxtString);
		command = CoderUtils.arraycopy(command, cxtTable);

		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_ACALL, command, session, seqid);
		return command;
	}
	/**
	 * 清除性能
	 * 
	 * @param session
	 * @param seqid
	 * @return
	 */
	public byte[] clearPersvr(int slot, String mh, int session, int seqid) {
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 1));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, slot));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 2));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, mh));
		CxtATTable cxtATTable = getCxtATTable(2, cxtAtomTypes);

		CxtAtomType cxtAtomType = new CxtAtomType();
		cxtAtomType.setCxtATTable(cxtATTable);
		cxtAtomType.setType("AT_TABLE");

		byte[] command = new byte[0];
		byte[] cxtString = getCxtString("ne/proc/persvr.clearcurper");
		byte[] cxtTable = getCxtAtomType(cxtAtomType);
		command = CoderUtils.arraycopy(command, cxtString);
		command = CoderUtils.arraycopy(command, cxtTable);

		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_ACALL, command, session, seqid);
		return command;
	}

	/**
	 * 解析查询性能
	 * 
	 * @param command
	 * @return
	 */
	public List<PersvrObject> analysisSelectPersvr(byte[] command, CXNEObject CXNEObject) {
		List<PersvrObject> persvrObjectList = new ArrayList<PersvrObject>();
		int count = 44;
		int start = 54;
		int numberLength = 5;
		byte[] tt = command;
		byte[] tableCount = new byte[] { tt[45], tt[46], tt[47], tt[48] };
		int table = CoderUtils.bytesToInt(tableCount);
		for (int i = 0; i < table; i++) {
			byte[] t = new byte[count];
			System.arraycopy(tt, start, t, 0, count);
			persvrObjectList.addAll(super.analysisTabble("persvr", t));
			start = start + count + numberLength;
		}
		for (int j = 0; j < persvrObjectList.size(); j++) {
			persvrObjectList.get(j).setCXNEObject(CXNEObject);
		}
		return persvrObjectList;
	}
	/**
	 * 返回性能历史文件名列表.文件在 /appdisk/export/per
	 * **/
	public byte[] getPersvr(String mh, int session, int seqid) {
		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, 1));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, mh));
		CxtATTable cxtATTable = getCxtATTable(1, cxtAtomTypes);

		CxtAtomType cxtAtomType = new CxtAtomType();
		cxtAtomType.setCxtATTable(cxtATTable);
		cxtAtomType.setType("AT_TABLE");

		byte[] command = new byte[0];
		byte[] cxtString = getCxtString("ne/proc/persvr.gethisper");
		byte[] cxtTable = getCxtAtomType(cxtAtomType);
		command = CoderUtils.arraycopy(command, cxtString);
		command = CoderUtils.arraycopy(command, cxtTable);

		command = getHeaderCommandBytes(CoreOper.ECXTMSG_REQ_ACALL, command, session, seqid);
		return command;
	}
	/**获取历史性能 List<PersvrObject>
	 * ip : 目标机器 ip地址
	 * mh : m15 / h24
	 * filename : 类似 m15-201207130730.per ; h24-20120712.per
	 * **/
	public List<PersvrObject> analysisPersvrFile(String ip,String mh,String filename) {
		List<PersvrObject> listPer = new ArrayList<PersvrObject>();
		try{
			System.out.println("start...");
			FtpClient ftpClient = new FtpClient(ip);  
			ftpClient.login("root", "root");  
			ftpClient.binary(); 
			System.out.println("login success!");
			ftpClient.cd("/appdisk/export/per/"+mh); 
			TelnetInputStream is = null; 
//			ftpClient.
			is = ftpClient.get(filename); 
			BufferedReader br = new BufferedReader(new InputStreamReader(is)); 
			String lines = null;
			while((lines=br.readLine() )!= null){
//				System.out.println(lines);
				if(lines.indexOf("perid,val")<0){					
					String[] sa = lines.split(",");
					PersvrObject po = new PersvrObject();
					po.setObjId(sa[0]);
					po.setPerid(sa[1]);
					po.setValue(sa[2]);
					listPer.add(po);
//					System.out.println(po.toString());
				}
			}
			br.close();
			ftpClient.closeServer(); 
			return listPer;
		}catch(Exception ex){
			System.out.println(ex.getMessage());
			return new ArrayList<PersvrObject>();
		}
	}
	public static void main(String[] s){
		AnalysisPersvr ap = new AnalysisPersvr();
		ap.analysisPersvrFile("20.0.0.35", "h24", "h24-20120712.per");
		
	}
}
