package com.nms.drivechenxiao.test.core;

import com.nms.drive.service.impl.CoderUtils;


public class TestStone {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestStone().t();
	}
	public void t(){
		byte[] b = new byte[]{(byte)0x08,(byte)0x12 };
//		l("0 = "+CoderUtils.ascii2Char(CoderUtils.bytesToInt(b[0])) );
//		l("1 = "+CoderUtils.ascii2Char(CoderUtils.bytesToInt(b[1])) );
		
		l("----");
//		l(Integer.valueOf("ge"));
		l("ge".getBytes());
//		l(Integer.decode("g").intValue());
		
	}
	public void p(){
		//CoreOper c = new CoreOper();
		byte[] b = new byte[]{(byte)0x41 , (byte)0xE9 , (byte)0x19 , (byte)0x19 , (byte)0x19 , (byte)0x00  , (byte)0x00 , (byte)0x00};
		char[] b1 = new char[]{(char)0x41 , (char)0xE9 , (char)0x19 , (char)0x19 , (char)0x19 , (char)0x20  , (char)0x00 , (char)0x00};
		byte[] ips = new byte[]{(byte)0x42 , (byte)0x38 , (byte)0x64 , (byte)0x01 , (byte)0x01 , (byte)0x97 , (byte)0x00 , (byte)0x00 };
//		byte[] ips = new byte[]{(byte)0x42 , (byte)0x38 , (byte)0x64 , (byte)0x01 , (byte)0x01 , (byte)0x97 , (byte)0x00 , (byte)0x00 };
//		c.print16String(b);
		l("ip : "+ip2long("200.200.200.200"));
//		l(b);
//		double dd=3368601800D;
//		byte[] bd = doubleToByte(dd);
//		l(bd);
//		l(dd);
//		l(b2yteToDouble(bd));
//		long ll=Math.round(dd); //100.1.1.151/24  0x1a : .b[]=00000000  42 40 05 00 80 FF 00 00   1677787543
		l("************ "+CoderUtils.a1ToTimeL(ips)+" **** "+CoderUtils.longToIpPrefix(Long.valueOf(CoderUtils.a1ToTimeL(ips))));
		l("************ "+CoderUtils.a1ToTimeL(ips)+" **** "+CoderUtils.longToIpPrefix(1677787543L));
		long ipL=100;
		l("^^^^^^ ip = "+String.valueOf((ipL & 0x000FF) ) +" ; ");
		l("ip === "+CoderUtils.a1ToIP200_(ips)); //1a类型ip解析
		byte[] bip = new byte[]{(byte)0x14,(byte)0x00,(byte)0x00,(byte)0xca}; //14 00 00 CA 00
		String s=String.valueOf(CoderUtils.bytesToInt(bip));		
		String ip=	CoderUtils.longToIpAddress(Long.valueOf(String.valueOf(CoderUtils.bytesToInt(bip))));//13类型ip解析
		
		String ip2=	CoderUtils.longToIpAddress(Long.valueOf(String.valueOf(CoderUtils.a1ToTimeL(b)))); //1a类型ip解析
		
		l("line 30 ip:"+ip+" ; ip2:"+ip2);
	}
	public byte[] to1a(byte[] b){
		if(b.length<8){
			byte[] tem = new byte[]{(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00};
			System.arraycopy(b, 0, tem, 3,4);
			return tem;
		}else{
			return b;
		}
	}
	
	public String bytetoip(byte[] bi){
		byte[] ip = new byte[8];
		ip[0] = bi[7];
		ip[1] = bi[6];
		ip[2] = bi[5];
		ip[3] = bi[4];
		ip[4] = bi[3];
		ip[5] = bi[2];
		ip[6] = bi[1];
		ip[7] = bi[0];
		
		double d ;
		d = byteToDouble(ip);
		//d = 3360l;
		
		l("d  : "+d);
		l("bi : "+byteToDouble(bi));
		d = b2yteToDouble(doubleToByte(d));
		l("d2  : "+d);
		return d+"";
	}
	
	public static byte[] doubleToByte(double d){
		  byte[] b=new byte[8];
		  long l=Double.doubleToLongBits(d);
		  for(int i=0;i < 8;i++){
		   b[i]=new Long(l).byteValue();
		   l=l>>8;
		  }
		  return b;
		 }

		 //  字节到浮点转换
	public static double byteToDouble(byte[] b){
		  long l;
		  l=b[0];
		  l&=0xff;
		  l|=((long)b[1]<<8);
		  l&=0xffff;
		  l|=((long)b[2]<<16);
		  l&=0xffffff;
		  l|=((long)b[3]<<24);
		  l&=0xffffffffl;
		  l|=((long)b[4]<<32);
		  l&=0xffffffffffl;
		  l|=((long)b[5]<<40);
		  l&=0xffffffffffffl;
		  l|=((long)b[6]<<48);
		  l|=((long)b[7]<<56);
		  return Double.longBitsToDouble(l);
		 }

		public  double b2yteToDouble(byte[] b) {
			long m;
			m = b[0];
			m &= 0xff;
			m |= ((long) b[1] << 8);
			m &= 0xffff;
			m |= ((long) b[2] << 16);
			m &= 0xffffff;
			m |= ((long) b[3] << 24);
			m &= 0xffffffffl;
			m |= ((long) b[4] << 32);
			m &= 0xffffffffffl;
			m |= ((long) b[5] << 40);
			m &= 0xffffffffffffl;
			m |= ((long) b[6] << 48);
			m &= 0xffffffffffffffl;
			m |= ((long) b[7] << 56);
			return Double.longBitsToDouble(m);
		}  
	public long ip2long(String ip) {  
        String[] ips = ip.split("[.]");  
       long num =   16777216L*Long.parseLong(ips[0]) + 65536L*Long.parseLong(ips[1]) + 256*Long.parseLong(ips[2]) + Long.parseLong(ips[3]);  
       return num;  
    }  
	public static String a1ToIP200(byte[] in){
		byte[] b=new byte[8];
		for(int i=0;i<8;i++){b[i]=in[(7-i)] ;}
		long ipL=Math.round(byteToDouble(b));
		b=null;
		StringBuffer sb = new StringBuffer("");  
		sb.append(String.valueOf((ipL >>> 24)));  
		sb.append(".");  
		sb.append(String.valueOf((ipL & 0x00FFFFFF) >>> 16));  
		sb.append(".");  
		sb.append(String.valueOf((ipL & 0x0000FFFF) >>> 8));  
		sb.append(".");  
		sb.append(String.valueOf((ipL & 0x000000FF)));  
		return sb.toString(); 
	}
	public void l(String s){
		System.out.println(s);
	}public void l(long s){
		System.out.println(s);
	}
	public void l(Double s){
		System.out.println(s);
	}public void l(byte[] s){
		for(int i=0;i<s.length;i++){
			byte[] temp = new byte[4];
			temp[0] = 0x00;
			temp[1] = 0x00;
			temp[2] = 0x00;
			temp[3] = s[i];
			String value16 = CoderUtils.intTo16String(CoderUtils.bytesToInt(temp));
			System.out.print(" byte["+i+"] = "+value16);
		}
		System.out.println("");
	}
}
