package com.nms.drivechenxiao.analysis;

import com.nms.drive.service.impl.CoderUtils;

public class AnalysisMsgNotify {

	
	public static void main(String[] s){
		System.out.println("aa");
		System.out.println(new AnalysisMsgNotify().getMsgNotifyType());
		System.out.println("bb");
	}
	private byte[] getTest(){
		byte[] cl = new byte[]{ (byte)0x72 ,(byte)0x6D ,(byte)0x74 ,(byte)0x01 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x4A  ,(byte)0x30 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x06 ,(byte)0x61 ,(byte)0x6C ,(byte)0x6D   
				,(byte)0x63 ,(byte)0x68 ,(byte)0x67 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x11 ,(byte)0x00  ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x01 ,(byte)0x00 ,(byte)0x00 ,(byte)0x00 ,(byte)0x2B   
				,(byte)0x30};
		return cl;
	}
	public String getMsgNotifyType(){
		byte[] bb = getTest();
		byte[] lengByte = new byte[4];
		 System.arraycopy(bb,9,lengByte,0,4);
		 int leng = CoderUtils.bytesToInt(lengByte);
		byte[] ss = new byte[leng];
		 System.arraycopy(bb,13,ss,0,leng);
		 return new String(ss).toString().trim();
	}
	
}
