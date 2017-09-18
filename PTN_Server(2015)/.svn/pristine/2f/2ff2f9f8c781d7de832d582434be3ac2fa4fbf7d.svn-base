package com.nms.drivechenxiao.test;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		 
		try {
			Socket clientSocket = new Socket("127.0.0.1", 3333);
			// ServerSocket serverSocket = new ServerSocket(9999);
			// Socket clientSocket = serverSocket.accept();
			// clientSocket.setSoTimeout(15000);

			OutputStream outputStream = clientSocket.getOutputStream();
			byte[] sendCommand = new byte[49];
			sendCommand[0] = (byte) CoderUtils.char2ASCII('r');
			sendCommand[1] = (byte) CoderUtils.char2ASCII('m');
			sendCommand[2] = (byte) CoderUtils.char2ASCII('t');
			sendCommand[3] = 0x01;

			byte[] len = CoderUtils.intToBytes(38);
			sendCommand[4] = len[0];
			sendCommand[5] = len[1];
			sendCommand[6] = len[2];
			sendCommand[7] = len[3];

			sendCommand[8] = 0x11;// cxtMsgType

			byte[] svc = CoderUtils.intToBytes(3);
			sendCommand[9] = svc[0];
			sendCommand[10] = svc[1];
			sendCommand[11] = svc[2];
			sendCommand[12] = svc[3];

			sendCommand[13] = (byte) CoderUtils.char2ASCII('o');
			sendCommand[14] = (byte) CoderUtils.char2ASCII('a');
			sendCommand[15] = (byte) CoderUtils.char2ASCII('m');

			byte[] session = CoderUtils.intToBytes(0);
			sendCommand[16] = session[0];
			sendCommand[17] = session[1];
			sendCommand[18] = session[2];
			sendCommand[19] = session[3];

			byte[] seqid = CoderUtils.intToBytes(1234);
			sendCommand[20] = seqid[0];
			sendCommand[21] = seqid[1];
			sendCommand[22] = seqid[2];
			sendCommand[23] = seqid[3];

			sendCommand[24] = 0x00;

			byte[] userCount = CoderUtils.intToBytes(5);
			sendCommand[25] = userCount[0];
			sendCommand[26] = userCount[1];
			sendCommand[27] = userCount[2];
			sendCommand[28] = userCount[3];

			sendCommand[29] = (byte) CoderUtils.char2ASCII('a');
			sendCommand[30] = (byte) CoderUtils.char2ASCII('d');
			sendCommand[31] = (byte) CoderUtils.char2ASCII('m');
			sendCommand[32] = (byte) CoderUtils.char2ASCII('i');
			sendCommand[33] = (byte) CoderUtils.char2ASCII('n');

			byte[] passwordCount = CoderUtils.intToBytes(8);
			sendCommand[34] = passwordCount[0];
			sendCommand[35] = passwordCount[1];
			sendCommand[36] = passwordCount[2];
			sendCommand[37] = passwordCount[3];

			sendCommand[38] = (byte) CoderUtils.char2ASCII('c');
			sendCommand[39] = (byte) CoderUtils.char2ASCII('M');
			sendCommand[40] = (byte) CoderUtils.char2ASCII('P');
			sendCommand[41] = (byte) CoderUtils.char2ASCII('C');
			sendCommand[42] = (byte) CoderUtils.char2ASCII('_');
			sendCommand[43] = (byte) CoderUtils.char2ASCII('p');
			sendCommand[44] = (byte) CoderUtils.char2ASCII('x');
			sendCommand[45] = (byte) CoderUtils.char2ASCII('1');

			outputStream.write(sendCommand);
			System.out.println("发送完成！");

			// DataInputStream dataInStream = new
			// DataInputStream(clientSocket.getInputStream());
			// while (true) {
			// byte[] bytes = new byte[2048];
			// int bbb = dataInStream.available();
			// System.out.println(bbb);
			// int aaa = dataInStream.read(bytes);
			// System.out.println(aaa);
			// String ss = new String(bytes);
			// System.out.println(ss);
			// Thread.sleep(5000);
			// }

			InputStream inputStream = clientSocket.getInputStream();
			int bytecount = 8;
			byte[] bytes = new byte[bytecount];
			while (true) {
				// System.out.println("inputStream.read():" +
				// inputStream.read());
				int aaa = inputStream.read(bytes);
				if (aaa == 8) {
					System.out.print("【P Mark】：" + CoderUtils.ascii2Char(bytes[0]));
					System.out.print(CoderUtils.ascii2Char(bytes[1]));
					System.out.print(CoderUtils.ascii2Char(bytes[2]));
					byte[] wz1 = new byte[4];
					wz1[0] = 0x00;
					wz1[1] = 0x00;
					wz1[2] = 0x00;
					wz1[3] = bytes[3];
					System.out.println(CoderUtils.bytesToInt(wz1));
					byte[] lens = new byte[4];
					lens[0] = bytes[4];
					lens[1] = bytes[5];
					lens[2] = bytes[6];
					lens[3] = bytes[7];
					System.out.println("【len】" + CoderUtils.bytesToInt(lens));

					bytes = new byte[CoderUtils.bytesToInt(lens)];// 根据下一个长度重新实例化数组大小

				} else if (aaa == 29) {
					byte[] test = new byte[4];
					test[0] = 0x00;
					test[1] = 0x00;
					test[2] = 0x00;
					test[3] = bytes[0];
					String bivalue = Integer.toBinaryString(CoderUtils.bytesToInt(test));
					int valuebiint = Integer.parseInt(bivalue, 2);
					System.out.println("【cxtEMsgType】:" + "  0x" + Integer.toHexString(valuebiint));

					byte[] svc2 = new byte[4];
					svc2[0] = bytes[1];
					svc2[1] = bytes[2];
					svc2[2] = bytes[3];
					svc2[3] = bytes[4];
					System.out.println("【svc】:" + CoderUtils.bytesToInt(svc2));

					byte[] session2 = new byte[4];
					session2[0] = bytes[5];
					session2[1] = bytes[6];
					session2[2] = bytes[7];
					session2[3] = bytes[8];
					System.out.println("【session】:" + CoderUtils.bytesToInt(session2));

					byte[] seqid2 = new byte[4];
					seqid2[0] = bytes[9];
					seqid2[1] = bytes[10];
					seqid2[2] = bytes[11];
					seqid2[3] = bytes[12];
					System.out.println("【seqid】:" + CoderUtils.bytesToInt(seqid2));

					byte[] err = new byte[4];
					err[0] = bytes[13];
					err[1] = bytes[14];
					err[2] = bytes[15];
					err[3] = bytes[16];
					System.out.println("【err】:" + CoderUtils.bytesToInt(err));

					byte[] errmsgCont = new byte[4];
					errmsgCont[0] = bytes[17];
					errmsgCont[1] = bytes[18];
					errmsgCont[2] = bytes[19];
					errmsgCont[3] = bytes[20];
					System.out.println("【errmsgCont】:" + CoderUtils.bytesToInt(errmsgCont));

					byte[] errmsg = new byte[8];
					errmsg[0] = bytes[21];
					errmsg[1] = bytes[22];
					errmsg[2] = bytes[23];
					errmsg[3] = bytes[24];
					errmsg[4] = bytes[25];
					errmsg[5] = bytes[26];
					errmsg[6] = bytes[27];
					errmsg[7] = bytes[28];

					System.out.println("【errmsg】：" + new String(errmsg));

				}
			}

			// InputStream inputStream = clientSocket.getInputStream();
			// byte[] bytes = new byte[2048];
			// inputStream.read(bytes);
			// System.out.println(bytes[0]);
			//
			// Thread.sleep(2000);
		} catch (Exception e) {
			ExceptionManage.dispose(e,Test.class);
		}
	}

}
