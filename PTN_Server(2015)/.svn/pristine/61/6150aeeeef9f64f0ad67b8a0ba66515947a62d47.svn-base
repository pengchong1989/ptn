package com.nms.util;

import com.nms.drive.service.impl.CoderUtils;

public class TEST {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 
		byte[] sssss = new byte[11];
		sssss[0] = 0x0A;
		sssss[1] = 0x6E;
		sssss[2] = 0x6F;
		sssss[3] = 0x74;
		sssss[4] = 0x20;
		sssss[5] = 0x6C;
		sssss[6] = 0x6F;
		sssss[7] = 0x67;
		sssss[8] = 0x69;
		sssss[9] = 0x6E;
		sssss[10] = 0x21;
		String aaa = new String(sssss);
//		System.out.println("0 = "+aaa);
		
		long l = 8835251044356L;
		System.out.println("0 = "+CoderUtils.longToIpAddress(Long.valueOf(l)));
//		 mac: 0609.1e00.0004
//		 00000000  42 98 24 78 00 00 10 00  
		 byte[] b=new byte[]{(byte)0x42,(byte)0x98,(byte)0x24,(byte)0x78,(byte)0x00,(byte)0x00,(byte)0x10,(byte)0x00} ;
		 l("a1totime = "+CoderUtils.MacBy1a(b));
		 
		 byte[] bi=new byte[]{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
		 l("a113toInt = "+CoderUtils.MacBy13(b));
	}
	public static void l(String s){System.out.println(s);}
	public static void l(long s){System.out.println(s);}
	public static void l(byte[] sb){
		for(int i=0;i<sb.length;i++){
			byte[] temp = new byte[4];
			temp[0] = 0x00;
			temp[1] = 0x00;
			temp[2] = 0x00;
			temp[3] = sb[i];
			String value16 = CoderUtils.intTo16String(CoderUtils.bytesToInt(temp));
			l("sb["+i+"] = "+value16);
		}
	}
}
