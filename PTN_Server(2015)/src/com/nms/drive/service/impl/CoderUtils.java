package com.nms.drive.service.impl;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.nms.service.impl.util.ResultString;
import com.nms.ui.manager.ExceptionManage;

public class CoderUtils {

	/**
	 * char转换ASCII
	 */
	public static char ascii2Char(int ASCII) {
		return (char) ASCII;
	}
	/**得到mac地址 add by stones 20130725
	 * **/
	public static String MacBy1a(byte[] b){
		String s =Long.toHexString(CoderUtils.a1ToTimeL(b));
		 for(int i=12;i-s.length()>0;i--){
			 s="0"+s;
		 }
		 return s;
	}public static String MacBy13(byte[] b){
//		String s =intTo16String(CoderUtils.a1IntToTimeInt(b));
		String s =intTo16String(CoderUtils.bytesToInt(b));
		 for(int i=12;i-s.length()>0;i--){
			 s="0"+s;
		 }
		 return s;
	}public static long MacTo1a(String mac){
		return Long.parseLong(mac, 16);
	}
	/**
	 * ASCII转换char
	 */
	public static int char2ASCII(char c) {
		return c;
	}
	/**ip : 192.168.200.100
	 * 
	 * **/
	public static long ipTolong(String ip){
		 String[] ips = ip.split("[.]");  
	     long num =   16777216L*Long.parseLong(ips[0]) + 65536L*Long.parseLong(ips[1]) + 256*Long.parseLong(ips[2]) + Long.parseLong(ips[3]);  
	     return num; 
	}
	/**
	 * 处理ip大于200开始 的 1a类型 ip地址
	 * **/
	public static String a1ToIP200_(byte[] in){
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
	/**处理 double型 时间
	 * **/
	public static long a1ToTimeL(byte[] in){
		byte[] b=new byte[8];
		for(int i=0;i<8;i++){b[i]=in[(7-i)] ;}
		long timeL=Math.round(byteToDouble(b));  
		return timeL; 
	}
	/**处理 时间 :
	 * 将in 转为16进制，在整体转为十进制（S）
	 * **/
	public static long a1ToTimeL1(byte[] in){
		byte[] b=new byte[8];
		for(int i=0;i<8;i++){b[i]=in[(7-i)] ;}
		long timeL=Math.round(byteToLong(b));  
		return timeL; 
	}
	public static int a1IntToTimeInt(byte[] in){
		byte[] b=new byte[4];
		for(int i=0;i<4;i++){b[i]=in[(3-i)] ;}
		int timeL=bytesToInt(b);  
		return timeL; 
	}
	/**add by stones on 20130917 **/
	public static String x13ToifName(byte[] b){
		StringBuffer sb = new StringBuffer();
		if(b[1] == 0x11){
			sb.append("fe.");
		}else if(b[1] == 0x14){
			sb.append("fx.");
		}else if(b[1] == 0x12){
			sb.append("ge.");
		}else if(b[1] == 0x60){
			sb.append("tod.");
		}else if(b[1] == 0x50){
			sb.append("extclk.");
		}else if(b[1] == 0x21){
			sb.append("stm1.");
		}else {
			return String.valueOf(bytesToInt(b));
		}
		sb.append(bytesToInt(b[2])).append(".").append(bytesToInt(b[3]));
		return sb.toString();
	}
	public static String x13ToU32Toxpdu(byte[] b){
		StringBuffer sb = new StringBuffer();
		sb.append("0x").append(int2len(bytesToInt(b[0]))).append(int2len(bytesToInt(b[1]))).append(int2len(bytesToInt(b[2]))).append(int2len(bytesToInt(b[3])));
		return sb.toString();
	} 
	private static String int2len(int i){
		if(i<10)return "0"+i;
		else return i+"";
	}
	/**
	 * Int转换byte[]
	 */
	public static byte[] intToBytes(int in) {
		byte[] result = new byte[4];
		for (int i = 3, j = 0; j < 4; i--, j++) {
			result[j] = (byte) ((in >>> (i * 8)) & 0xff);
		}
		return result;
	}

	/**
	 * byte转换Int
	 * 
	 * @param in
	 * @return
	 */
	public static int bytesToInt(byte in) {
		byte[] temp = new byte[4];
		temp[0] = 0x00;
		temp[1] = 0x00;
		temp[2] = 0x00;
		temp[3] = in;

		int result = 0;
		for (int i = 3, j = 0; j < 4; j++, i--) {
			result += ((temp[j] & 0xff) << (i * 8));
		}
		return result;
	}

	/**
	 * byte[]转换Int
	 */
	public static int bytesToInt(byte[] in) {
		int result = 0;
		for (int i = 3, j = 0; j < 4; j++, i--) {
			result += ((in[j] & 0xff) << (i * 8));
		}
		return result;
	}

	/**
	 * Long转换byte[]
	 */
	public static long bytesToLong(byte[] in) {
		long result = 0l;
		for (int i = 7, j = 0; j < 8; j++, i--) {
			result += ((in[j] & 0xff) << (i * 8));
		}
		return result;
	}

	/**
	 * byte[8]转long
	 * 
	 * @param b
	 * @return
	 */
	public static long byte2Long(byte[] b) {
		return ((b[0] & 0xff) << 56) | ((b[1] & 0xff) << 48) | ((b[2] & 0xff) << 40) | ((b[3] & 0xff) << 32) | ((b[4] & 0xff) << 24) | ((b[5] & 0xff) << 16) | ((b[6] & 0xff) << 8) | (b[7] & 0xff);
	}

	/**
	 * long转byte[8]
	 * 
	 * @param a
	 * @return
	 */
	public static byte[] long2Byte(long a) {
		byte[] b = new byte[4 * 2];

		b[0] = (byte) (a >> 56);
		b[1] = (byte) (a >> 48);
		b[2] = (byte) (a >> 40);
		b[3] = (byte) (a >> 32);

		b[4] = (byte) (a >> 24);
		b[5] = (byte) (a >> 16);
		b[6] = (byte) (a >> 8);
		b[7] = (byte) (a >> 0);

		return b;
	}

	/**
	 * 十进制转换二进制
	 */
	public static String convertBinary(int sum) {
		StringBuffer binary = new StringBuffer();
		if (sum == 0 || sum == 1) {
			return sum+"";
		}
		while (sum != 0 && sum != 1) {
			binary.insert(0, sum % 2);
			// println("sum=" + sum + "余数=" + (sum % 2) + "除数=" + sum / 2);
			sum = sum / 2;
			if (sum == 0 || sum == 1) {
				binary.insert(0, sum % 2);
			}
		}
		return binary.toString();
	}

	/**
	 * 二进制转十进制Int
	 */
	public static int convertAlgorism(char[] cars) {
		int result = 0;
		int num = 0;
		for (int i = cars.length - 1; 0 <= i; i--) {
			int temp = 2;
			if (num == 0) {
				temp = 1;
			} else if (num == 1) {
				temp = 2;
			} else {
				for (int j = 1; j < num; j++) {
					temp = temp * 2;
				}
			}
			int sum = Integer.parseInt(String.valueOf(cars[i]));
			result = result + (sum * temp);
			num++;
		}

		return result;
	}

	/**
	 * 二进制转十进制Long
	 */
	public static long convertAlgorismLong(char[] cars) {
		long result = 0;
		long num = 0;
		for (int i = cars.length - 1; 0 <= i; i--) {
			long temp = 2;
			if (num == 0) {
				temp = 1;
			} else if (num == 1) {
				temp = 2;
			} else {
				for (int j = 1; j < num; j++) {
					temp = temp * 2;
				}
			}
			long sum = Long.parseLong(String.valueOf(cars[i]));
			result = result + (sum * temp);
			num++;
		}

		return result;
	}

	/**
	 * 通过INT获得16进制的数
	 * 
	 * @param count10进制的数
	 * @return 16进制的数
	 */
	public static String intTo16String(int count) {
		String bivalue = Integer.toBinaryString(count);
		int valuebiint = Integer.parseInt(bivalue, 2);
		String value16 = Integer.toHexString(valuebiint).toUpperCase();
		return value16;
	}

	/**
	 * 合并两个数组
	 * 
	 * @param a1
	 *            第一个
	 * @param b2
	 *            第二个
	 * @return
	 */
	public static byte[] arraycopy(byte[] a1, byte[] b2) {
		byte[] c3 = new byte[a1.length + b2.length];
		System.arraycopy(a1, 0, c3, 0, a1.length);
		System.arraycopy(b2, 0, c3, a1.length, b2.length);
		return c3;
	}

	/**
	 * 将字节打印16进制输出
	 * 
	 * @param bytes
	 *            字节数组
	 * @return
	 */
	public static String print16String(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("00000000  ");
		int count = 1;
		int index = 1;
		for (int i = 0; i < bytes.length; i++) {
			byte[] temp = new byte[4];
			temp[0] = 0x00;
			temp[1] = 0x00;
			temp[2] = 0x00;
			temp[3] = bytes[i];
			String value16 = CoderUtils.intTo16String(CoderUtils.bytesToInt(temp));
			if (value16.length() == 1) {
				value16 = "0" + value16;
			}
			buffer.append(value16 + " ");
			if (count != 0 && count % 8 == 0) {
				buffer.append(" ");
			}
			if (count != 0 && count % 16 == 0) {
				buffer.append("\r\n");
				String value16Index = CoderUtils.intTo16String(index);
				value16Index += "0";
				int bq = 8 - value16Index.length();
				String tempIndexStr = "";
				for (int j = 0; j < bq; j++) {
					tempIndexStr += "0";
				}
				tempIndexStr += value16Index;
				buffer.append(tempIndexStr + "  ");
				index++;
			}
			count++;
		}
		System.out.println(buffer.toString());
		return buffer.toString();
	}

	/**
	 * long转byte[]
	 * 
	 * @param n
	 * @return
	 */
	public static byte[] longToBytes(long n) {
		byte[] b = new byte[8];
		b[7] = (byte) (n & 0xff);
		b[6] = (byte) (n >> 8 & 0xff);
		b[5] = (byte) (n >> 16 & 0xff);
		b[4] = (byte) (n >> 24 & 0xff);
		b[3] = (byte) (n >> 32 & 0xff);
		b[2] = (byte) (n >> 40 & 0xff);
		b[1] = (byte) (n >> 48 & 0xff);
		b[0] = (byte) (n >> 56 & 0xff);
		return b;
	}

	/**
	 * 将byte[8]转换为时间
	 * 
	 * @param times
	 * @return
	 */
	public static String byteToTime(String type, byte[] times) {
		String timeStr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ByteBuffer buffer = ByteBuffer.wrap(times);

		try {
			if ("14".equalsIgnoreCase(type)) {
				timeStr = sdf.format(buffer.getLong());
			} else if ("1a".equalsIgnoreCase(type)) {
				timeStr = sdf.format(buffer.getDouble());
			}
		} catch (Exception e) {
			// ExceptionManage.dispose(e,this.getClass());
			timeStr = "转换日期失败";
		}

		return timeStr;
	}

	/**
	 * double转byte[]
	 * 
	 * @param d
	 * @return
	 */
	public static byte[] doubleToByte(double d) {
		byte[] b = new byte[8];
		long l = Double.doubleToLongBits(d);
		for (int i = 0; i < 8; i++) {
			b[i] = new Long(l).byteValue();
			l = l >> 8;
		}
		return b;
	}

	/**
	 * byte[]转double
	 * 
	 * @param b
	 * @return
	 */
	public static double byteToDouble(byte[] b) {
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

	/**
	 * long转成日期型(yyyy-MM-dd HH:mm:ss)String
	 * 
	 * @param date
	 * @return
	 */
	public static String longToDate(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * int转换ip地址
	 * @param ip
	 * @return
	 */
	public static String longToIpAddress(long ip) {
		StringBuffer sb = new StringBuffer("");  
		sb.append(String.valueOf((ip >>> 24)));  
		sb.append(".");  
		sb.append(String.valueOf((ip & 0x00FFFFFF) >>> 16));  
		sb.append(".");  
		sb.append(String.valueOf((ip & 0x0000FFFF) >>> 8));  
		sb.append(".");  
		sb.append(String.valueOf((ip & 0x000000FF)));  
		return sb.toString(); 
	}
	public static String longToIpPrefix(long ip) {
		StringBuffer sb = new StringBuffer("");  
		sb.append(String.valueOf((ip  >>> 24) &0xFF ));  
		sb.append(".");  
		sb.append(String.valueOf((ip & 0x0000FF0000) >>> 16));  
		sb.append(".");  
		sb.append(String.valueOf((ip & 0x000000FF00) >>> 8));  
		sb.append(".");  
		sb.append(String.valueOf((ip & 0x00000000FF)));  
		sb.append("\\"); 
		sb.append(String.valueOf((ip  >>> 32)));
		return sb.toString(); 
	}

	/**
	 * 判断字符串是否为数字
	 * @param s
	 * @return
	 */
	public static boolean isNum(String s) {
		return s != "" && s.matches("[0-9]*");
	}

	/**
	 * long类型转成byte数组 
	 * @param number
	 * @return
	 */
	public static byte[] longToByte(long number) {
		long temp = number;
		byte[] b = new byte[8];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Long(temp & 0xff).byteValue();// 将最低位保存在最低位 
			temp = temp >> 8; // 向右移8位 
		}
		return b;
	}

	/**
	 * byte数组转成long 
	 * @param b
	 * @return
	 */
	public static long byteToLong(byte[] b) {
		long s = 0;
		long s0 = b[0] & 0xff;// 最低位 
		long s1 = b[1] & 0xff;
		long s2 = b[2] & 0xff;
		long s3 = b[3] & 0xff;
		long s4 = b[4] & 0xff;// 最低位 
		long s5 = b[5] & 0xff;
		long s6 = b[6] & 0xff;
		long s7 = b[7] & 0xff;

		// s0不变 
		s1 <<= 8;
		s2 <<= 16;
		s3 <<= 24;
		s4 <<= 8 * 4;
		s5 <<= 8 * 5;
		s6 <<= 8 * 6;
		s7 <<= 8 * 7;
		s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
		return s;
	}

	/** 
	 * 注释：int到字节数组的转换！ 
	 * 
	 * @param number 
	 * @return 
	 */
	public static byte[] intToByte(int number) {
		byte[] result = new byte[4];
		for (int i = 3, j = 0; j < 4; i--, j++) {
			result[j] = (byte) ((number >>> (i * 8)) & 0xff);
		}
		return result;
	}

	/** 
	 * 注释：字节数组到int的转换！ 
	 * 
	 * @param b 
	 * @return 
	 */
	public static int byteToInt(byte[] b) {
		int s = 0;
		int s0 = b[0] & 0xff;// 最低位 
		int s1 = b[1] & 0xff;
		int s2 = b[2] & 0xff;
		int s3 = b[3] & 0xff;
		s3 <<= 24;
		s2 <<= 16;
		s1 <<= 8;
		s = s0 | s1 | s2 | s3;
		return s;
	}

	/** 
	 * 注释：short到字节数组的转换！ 
	 * 
	 * @param s 
	 * @return 
	 */
	public static byte[] shortToByte(short number) {
		int temp = number;
		byte[] b = new byte[2];
		for (int i = 0; i < b.length; i++) {
			b[i] = new Integer(temp & 0xff).byteValue();// 将最低位保存在最低位 
			temp = temp >> 8; // 向右移8位 
		}
		return b;
	}

	/** 
	 * 注释：字节数组到short的转换！ 
	 * 
	 * @param b 
	 * @return 
	 */
	public static short byteToShort(byte[] b) {
		short s = 0;
		short s0 = (short) (b[0] & 0xff);// 最低位 
		short s1 = (short) (b[1] & 0xff);
		s1 <<= 8;
		s = (short) (s0 | s1);
		return s;
	}

	public static String longToIpAndMaskAddress(Long ip) {
		StringBuffer sb = new StringBuffer();
		byte[] ipByte = new byte[8];
		ipByte = CoderUtils.longToByte(ip);

		sb.append(bytesToInt(ipByte[2]));
		sb.append(".");
		sb.append(bytesToInt(ipByte[3]));
		sb.append(".");
		sb.append(bytesToInt(ipByte[4]));
		sb.append(".");
		sb.append(bytesToInt(ipByte[5]));
		sb.append("/");

		byte[] mask = new byte[] { ipByte[0], ipByte[1] };
		String s = new String(mask);
		sb.append(s);
		return sb.toString();
	}

	/**
	 * int转6bit二进制字符串
	 * @param i
	 * @return
	 */
	public static String intTo6Binary(int i) {
		StringBuffer s = new StringBuffer();
		String Binary = "";
		s.append(convertBinary(i));
		int length = 7 - s.length();
		if (s.length() < 7) {
			for (int n = 0; n < length; n++) {
				s.insert(0, 0);
			}
		}
		Binary = s.toString();
		return Binary;
	}
	public static String intTo3Binary(int i) {
		StringBuffer s = new StringBuffer();
		String Binary = "";
		s.append(convertBinary(i));
		int length = 3 - s.length();
		if (s.length() < 3) {
			for (int n = 0; n < length; n++) {
				s.insert(0, 0);
			}
		}
		Binary = s.toString();
		return Binary;
	}
	public static String intTo4Binary(int i) {
		StringBuffer s = new StringBuffer();
		String Binary = "";
		s.append(convertBinary(i));
		int length = 4 - s.length();
		if (s.length() < 4) {
			for (int n = 0; n < length; n++) {
				s.insert(0, 0);
			}
		}
		Binary = s.toString();
		return Binary;
	}
	public static String intTo5Binary(int i) {
		StringBuffer s = new StringBuffer();
		String Binary = "";
		s.append(convertBinary(i));
		int length = 5 - s.length();
		if (s.length() < 5) {
			for (int n = 0; n < length; n++) {
				s.insert(0, 0);
			}
		}
		Binary = s.toString();
		return Binary;
	}
	public static String intTo13Binary(int i) {
		StringBuffer s = new StringBuffer();
		String Binary = "";
		s.append(convertBinary(i));
		int length = 5 - s.length();
		if (s.length() < 5) {
			for (int n = 0; n < length; n++) {
				s.insert(0, 0);
			}
		}
		Binary = s.toString();
		return Binary;
	}

	/**
	 * 二进制解析oper
	 * @param Binary
	 * @return
	 */
	public static String analysisOper(String Binary) {
		StringBuffer oper = new StringBuffer();
		boolean isZero = true;
		if ("1".equals(Binary.substring(5, 6))) {
			oper.append("1"); //tsf
			isZero = false;
		}
		if ("1".equals(Binary.substring(4, 5))) {
			if (!isZero)
				oper.append(",");
			oper.append("2"); //linkdown
			isZero = false;
		}
		if ("1".equals(Binary.substring(3, 4))) {
			if (!isZero)
				oper.append(",");
			oper.append("3");//admindown
			isZero = false;
		}
		if ("1".equals(Binary.substring(2, 3))) {
			if (!isZero)
				oper.append(",");
			oper.append("4");//notPresent
			isZero = false;
		}
		if ("1".equals(Binary.substring(0, 1))) {
			if (!isZero)
				oper.append(",");
			oper.append("5");//datalinkdown
			isZero = false;
		}
		if (isZero) {
			oper.append("0");//up
		}
		return oper.toString();
	}
	
	/**
	 * 设备导入配置，返回信息解析
	 * @param status
	 * @return
	 */
	public static String download(int status){
		String str = "";
		if(status == 0){
			return ResultString.CONFIG_SUCCESS;
		}else{
			str = Integer.toBinaryString(status);
			while(str.length()<4){
				str = "0"+str;
			}
			if(Integer.parseInt(str.substring(0,1))==1){
				return "开关值不匹配";
			}else if(Integer.parseInt(str.substring(1,2))==1){
				return "非法配置文件";
			}else if(Integer.parseInt(str.substring(2,3))==1){
				return "配置文件长度错误";
			}else if(Integer.parseInt(str.substring(3,4))==1){
				return "配置类型错误";
			}
			return "";
		}
		
	}
	
	/**
	 * 驱动性能值float转换为界面float
	 * @param f
	 * @return
	 */
	public static float floatToInt(float f1,float f2){
		try {
			String str = getEight((int)f1)+getEight((int)f2);
			int e = Integer.parseInt(new BigInteger(str.substring(1,9), 2).toString());//8位指数
			String str23 = str.substring(9);//最后23位尾数
			float fa = 0 ;
			if(e-127<0){
				String str_float = "1"+str23;
				for (int i = 0; i < 127-e-1; i++) {
					str_float = "0"+str_float;
				}
				BigInteger bigInteger2 = new BigInteger(str_float.substring(0, 8),2);
				fa = (float)Integer.parseInt(bigInteger2.toString())/256;
			}else if(e-127 ==0){
				String str_float = str23.substring(0, 8);
				BigInteger bigInteger2 = new BigInteger(str_float,2);
				float fl = (float)Integer.parseInt(bigInteger2.toString())/256;
				fa = 1+fl;
			}else {
				if(e-127<24){
					String str_integer = "1"+str23.substring(0,e-127);//float正数部分
					BigInteger bigInteger = new BigInteger(str_integer, 2);
					if(23-(e-127)>8){
						String str_float = str23.substring(e-127, e-127+8);
						BigInteger bigInteger2 = new BigInteger(str_float,2);
						float fl = (float)Integer.parseInt(bigInteger2.toString())/256;//负数部分
						fa = Integer.parseInt(bigInteger.toString())+fl;
					}else if(23-(e-127)>4 && 23-(e-127)<9){
						String str_float = str23.substring(e-127, e-127+4);
						BigInteger bigInteger2 = new BigInteger(str_float,2);
						float fl = (float)Integer.parseInt(bigInteger2.toString())*16/256;//负数部分
						fa = Integer.parseInt(bigInteger.toString())+fl;
					}
				}else{
					return (float) 11.1111;
				}
			}
			if(str.subSequence(0, 1).equals("1")){
				fa = -fa;
			}
			return fa;
		} catch (Exception e) {
			ExceptionManage.dispose(e, CoderUtils.class);
			return 0;
		}
		
	}
	
	/**
	 * 十进制转8位二进制
	 * 
	 * @param cycle
	 * @return
	 */
	private static String getEight(int mel) {
		String str = "";
		str = Integer.toBinaryString(mel);
		for (int i = str.length(); i < 16; i++) {
			str = 0 + str;
		}
		return str;
	}
	
	
	public static String byteToString(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		String[] strs = null;
		int count = 1;
		for (int i = 0; i < bytes.length; i++) {
			byte[] temp = new byte[4];
			temp[0] = 0x00;
			temp[1] = 0x00;
			temp[2] = 0x00;
			temp[3] = bytes[i];
			String value16 = CoderUtils.intTo16String(CoderUtils.bytesToInt(temp));
			if (value16.length() == 1) {
				value16 = "0" + value16;
			}
			buffer.append(value16 + " ");
			count++;
		}
		return buffer.toString();
	}
	
	/**
	 * 将byte[]转换字节String数组
	 * 
	 * @param bytes
	 *            字节数组
	 * @return
	 */
	public static String[] byteToStrings(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		String[] strs = null;
		int count = 1;
		for (int i = 0; i < bytes.length; i++) {
			byte[] temp = new byte[4];
			temp[0] = 0x00;
			temp[1] = 0x00;
			temp[2] = 0x00;
			temp[3] = bytes[i];
			String value16 = CoderUtils.intTo16String(CoderUtils.bytesToInt(temp));
			if (value16.length() == 1) {
				value16 = "0" + value16;
			}
			buffer.append(value16 + " ");
			count++;
		}
		strs = buffer.toString().split(" ");
		return strs;
	}
	/**
	 * 将相同属性名的进行赋值
	 * 
	 * @param object赋值对象
	 * @param object2被赋值对象
	 */
	public static void copy(Object object, Object object2) {
		try {
			Map<String, Object> srcMap = new HashMap<String, Object>();
			Field[] srcFields = object.getClass().getDeclaredFields();// 通过反射获取object的所有属性
			for (Field fd : srcFields) {
				fd.setAccessible(true);// 取消访问权限检查
				srcMap.put(fd.getName(), fd.get(object)); // 获取属性值
			}
			Field[] destFields = object2.getClass().getDeclaredFields();// 通过反射获取object2的所有属性

			for (Field fd : destFields) {
				if (srcMap.get(fd.getName()) == null) {
					continue;
				}
				fd.setAccessible(true);// 取消访问权限检查
				if(!fd.getName().equals("serialVersionUID")){//界面的static final属性不赋值
					if (fd.getType() == int.class || fd.getType() == String.class || fd.getType() == long.class || fd.getType() == boolean.class) {
						fd.set(object2, srcMap.get(fd.getName())); // 给属性赋值
					} else {
						Object object3 = fd.getType().newInstance();
						copy(srcMap.get(fd.getName()), object3);
						fd.set(object2, object3); // 给属性赋值
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,CoderUtils.class);
		}
	}
	
	public static String transformMac(String mac){
		
	StringBuffer buffer = new StringBuffer();
		String[] strs = mac.split("-");
		for (int i = 0; i < strs.length; i++) {
			String str = strs[i];
			if(i == strs.length-1){
				buffer.append(transformInt(str));
			}else{
				buffer.append(transformInt(str)+"-");
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 转换MAC
	 * @param str
	 * @return
	 */
	private static int transformInt(String str){
		int first = 0;
		int last = 0;
		str = str.toUpperCase();
		str.subSequence(0,1);
		if("0".equals(str.subSequence(0,1))){
			first = 0;
		}
		if("0".equals(str.substring(1))){
			last = 0;
		}
		if("1".equals(str.subSequence(0,1))){
			first = 1;
		}
		if("1".equals(str.substring(1))){
			last = 1;
		}
		if("2".equals(str.subSequence(0,1))){
			first = 2;
		}
		if("2".equals(str.substring(1))){
			last = 2;
		}
		if("3".equals(str.subSequence(0,1))){
			first = 3;
		}
		if("3".equals(str.substring(1))){
			last = 3;
		}
		if("4".equals(str.subSequence(0,1))){
			first = 4;
		}
		if("4".equals(str.substring(1))){
			last = 4;
		}
		if("5".equals(str.subSequence(0,1))){
			first = 5;
		}
		if("5".equals(str.substring(1))){
			last = 5;
		}
		if("6".equals(str.subSequence(0,1))){
			first = 6;
		}
		if("6".equals(str.substring(1))){
			last = 6;
		}
		if("7".equals(str.subSequence(0,1))){
			first = 7;
		}
		if("7".equals(str.substring(1))){
			last = 7;
		}
		if("8".equals(str.subSequence(0,1))){
			first = 8;
		}
		if("8".equals(str.substring(1))){
			last = 8;
		}
		if("9".equals(str.subSequence(0,1))){
			first = 9;
		}
		if("9".equals(str.substring(1))){
			last = 9;
		}
		if("A".equals(str.subSequence(0,1))){
			first = 10;
		}
		if("A".equals(str.substring(1))){
			last = 10;
		}
		if("B".equals(str.subSequence(0,1))){
			first = 11;
		}
		if("B".equals(str.substring(1))){
			last = 11;
		}
		if("C".equals(str.subSequence(0,1))){
			first = 12;
		}
		if("C".equals(str.substring(1))){
			last = 12;
		}
		if("D".equals(str.subSequence(0,1))){
			first = 13;
		}
		if("D".equals(str.substring(1))){
			last = 13;
		}
		if("E".equals(str.subSequence(0,1))){
			first = 14;
		}
		if("E".equals(str.substring(1))){
			last = 14;
		}
		if("F".equals(str.subSequence(0,1))){
			first = 15;
		}
		if("F".equals(str.substring(1))){
			last = 15;
		}
		return first*16+last;
	}
	
	/**
	 * 同步MAC
	 * 转换16进制MAC
	 * @param str
	 * @return
	 */
	public static String synchTransformMac(String str){
		String s = null;
		Integer integer = Integer.parseInt(str);
		s = (Integer.toHexString(integer)).toUpperCase();
		if(s.length() == 1){
			s= "0"+s;
		}
		return s;
	}
}
