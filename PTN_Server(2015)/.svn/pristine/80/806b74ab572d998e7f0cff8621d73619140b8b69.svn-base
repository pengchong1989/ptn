package com.nms.drivechenxiao.analysis.proc;

import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.tool.CxtOpLump;
import com.nms.drivechenxiao.analysis.tool.bean.CxtATTable;
import com.nms.drivechenxiao.analysis.tool.bean.CxtAtomType;
import com.nms.drivechenxiao.analysis.tool.bean.CxtOpItem;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.ProtectLogObject;
import com.nms.ui.manager.ExceptionManage;

/**
 * （保护）查询日志-------保护倒换事件的上报
 * @author sy
 *
 */
public class Analysislog extends CxtOpLump{
	private static String sqlStr="select * from PRTSWITCH limit 0,1000";
	
	
	public byte[] selectlogquery(int session, int seqid){
		List<CxtOpItem> cxtOpItems = new ArrayList<CxtOpItem>();

		List<CxtAtomType> cxtAtomTypes = new ArrayList<CxtAtomType>();
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, "1"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, "PRTSWITCH" ));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_NUM_32, "2"));
		cxtAtomTypes.add(getCxtAtomType(CxtAtomType.AT_STRING, sqlStr ));
		
		CxtATTable cxt = getCxtATTable(cxtAtomTypes.size() / 2, cxtAtomTypes);
		
		cxtOpItems.add(acallproc( cxt)) ;

		byte[] com = getCxtString("ne/proc/log.query");
		byte[] b1 = new byte[1];
		b1[0]=0x30;
		com = arraycopy(com,b1);
		com = arraycopy(com,getCxtATTable(cxt));
		
		byte[] command = getHeaderOAMCommandBytes(CoreOper.ECXTMSG_REQ_ACALL, com, session, seqid);
		return command;
	}
	/**
	 * 解析查询保护倒换事件
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public List<ProtectLogObject> analysisLogquery(byte[] command, CXNEObject CXNEObject) {
		List<ProtectLogObject> logObject=new ArrayList<ProtectLogObject>();
		int start=260;
		byte[] tt=command;
		byte[] t=new byte[tt.length-start+5];
		byte[] t1=new byte[]{command[32],command[33],command[34],command[35],command[36]};
		System.arraycopy(t1, 0, t, 0, 5);
		System.arraycopy(tt, start, t, 5, tt.length-start);
		logObject=super.analysisTabble("protectLog", t);
		return logObject;
	}
	/**
	 * 解析：保护倒换事件 --主动上报
	 * @param command
	 * @param CXNEObject
	 * @return
	 */
	public List<ProtectLogObject> analysisHookNotify(byte[] command, CXNEObject CXNEObject) {
		List<ProtectLogObject> logObject=new ArrayList<ProtectLogObject>();
		byte[] tabel=new byte[]{0x30,0x00,0x00,0x00,0x02,0x13,0x00,0x00,0x00,0x05};
		int count=8;//中间未知的数据有8个字节--0x00,0x00,0x00,0x01,0x0F,0xE8,0X00,0X00
		int start=26;
		byte[] tt=command;
		byte[] t=new byte[tt.length-start-count+tabel.length];
		System.arraycopy(tabel, 0, t, 0, tabel.length);
		System.arraycopy(tt, start, t, tabel.length, 5+command[30]);
		System.arraycopy(tt, start+5+command[30]+count, t, 5+command[30]+tabel.length, tt.length-start-5-command[30]-count);
		logObject=super.analysisTabble("hooknotify", t);
		return logObject;
	}
	public byte[] getData(){
		byte[] t=new byte[]{
				0x72,0x6D,0x74,0x01,0x0,0x00,0x00,0x7F    ,0x30,0x00,0x00,0x00,0x09,0x70,0x72,0x74,
				0x73,0x77,0x69,0x74,0x63,0x68,0x00,0x00    ,0x00,0x07,0x20,0x00,0x00,0x00,0x03,0x6C,
				0x73,0x70,0x00,0x00,0x00,0x01,0x0F,(byte) 0xE8   ,0x00,0x00,0x30,0x00,0x00,0x00,0x04,0x13,
				0x00,0x00,0x00,0x01,0x13,0x00,0x02,0x00		,0x01,0x13,0x00,0x00,0x00,0x02,0x20,0x00,
				0x00,0x00,0x18,0x73,0x69,0x67,0x6E,0x61		,0x6C,0x53,0x74,0x61,0x74,0x3A,0x3A,0x53,
				0x46,0x3A,0x30,0x78,0x34,0x30,0x30,0x30		,0x31,0x30,0x32,0x13,0x00,0x00,0x00,0x03,
				0x20,0x00,0x00,0x00,0x13,0x73,0x77,0x69		,0x74,0x63,0x68,0x20,0x74,0x6F,0x3A,0x30,
				0x78,0x34,0x30,0x30,0x30,0x31,0x30,0x31		,0x13,0x00,0x00,0x00,0x04,0x14,0x00,0x00,
				(byte) 0x01,0x45,(byte) 0xA5,(byte) 0xE2,(byte) 0x68,(byte) 0x23,0X00
				//1A  42 74 59 30 B7 77 F0 00 
		};
		return t;
	}
	
	public static void main(String args[]){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//16915024676243
		byte[] abc=new byte[]{0x00,0x00, 0x01,0x45,(byte) 0xA5,(byte) 0xE2,(byte) 0x68,(byte) 0x23};
		//byte[] a1=new byte[]{0x00,0x00, 0x01,0x69,165,};
		//long a1=1 69 165 226 104 35
		ByteBuffer buffer = ByteBuffer.wrap(abc);
		System.out.println("时间转换：：###￥+++ "+df.format(buffer.getLong()));
		System.out.println((Long.valueOf("1398646462855")));
		Analysislog l=new Analysislog();
		CXNEObject c=new CXNEObject();
		byte[] b=new byte[]{0x73,0x69,0x67,0x6E,0x61};
		new String(b).toString().trim();
		List<ProtectLogObject> log=l.analysisHookNotify(l.getData(), c);
		if(log!=null&&log.size()>0){
			for(ProtectLogObject lo:log){
				System.out.println(" carse :: "+lo.getCarse());
				System.out.println("desc ::   "+lo.getDesc());
				System.out.println(" obj+::: "+lo.getObj());
				System.out.println(" type :: "+lo.getType());
				try {
					System.out.println("time ::: "+df.parse(lo.getTime()));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					ExceptionManage.dispose(e,Analysislog.class);
				}
			}
		}else{
		//	System.out.println("解析失败！！！！！");
		}
	}
	
	
//	0000000  72 6D 74 01 00 00 00 7F  30 00 00 00 09 70 72 74   rmt.... 0....prt 
//	00000010  73 77 69 74 63 68 00 00  00 07 20 00 00 00 03 6C   switch.. .. ....l 
//	00000020  73 70 00 00 00 01 0F E8  00 00 30 00 00 00 04 13   sp...... ..0..... 
//	00000030  00 00 00 01 13 00 02 00  01 13 00 00 00 02 20 00   ........ ...... . 
//	00000040  00 00 18 73 69 67 6E 61  6C 53 74 61 74 3A 3A 53   ...signa lStat::S 
//	00000050  46 3A 30 78 34 30 30 30  31 30 32 13 00 00 00 03   F:0x4000 102..... 
//	00000060  20 00 00 00 13 73 77 69  74 63 68 20 74 6F 3A 30    ....swi tch to:0 
//	00000070  78 34 30 30 30 31 30 31  13 00 00 00 04 14 00 00   x4000101 ........ 
//	00000080  01 45 96 F6 4C F3 00                               .E..L..
}
