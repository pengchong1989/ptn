package com.nms.drive.hztest;

import com.nms.drive.service.impl.CoderUtils;

public class TEST1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 
		System.out.println(CoderUtils.ascii2Char(110));// 6E
		System.out.println(CoderUtils.ascii2Char(98));// 6E
		System.out.println(CoderUtils.ascii2Char(114));// 6E
		System.out.println(CoderUtils.ascii2Char(19));// 6E
		// byte[] bytes = new byte[1000];
		// for (int i = 0; i < bytes.length; i++) {
		// bytes[i] = (byte) i;
		// }
		// TEST1 test1 = new TEST1();
		// test1.print16String(bytes);

	}

	public String print16String(byte[] bytes) {
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
			String value16 = intTo16String(CoderUtils.bytesToInt(temp));
			if (value16.length() == 1) {
				value16 = "0" + value16;
			}
			buffer.append(value16 + " ");
			if (count != 0 && count % 8 == 0) {
				buffer.append(" ");
			}
			if (count != 0 && count % 16 == 0) {
				buffer.append("\r\n");
				String value16Index = intTo16String(index);
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
	 * 通过INT获得16进制的数
	 * 
	 * @param count10进制的数
	 * @return 16进制的数
	 */
	private String intTo16String(int count) {
		String bivalue = Integer.toBinaryString(count);
		int valuebiint = Integer.parseInt(bivalue, 2);
		String value16 = Integer.toHexString(valuebiint).toUpperCase();
		return value16;
	}
}
