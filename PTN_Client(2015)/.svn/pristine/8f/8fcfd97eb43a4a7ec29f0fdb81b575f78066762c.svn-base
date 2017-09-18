package com.nms.ui.ptn.upgradeManage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class AnalysisFile {

	public byte[] read(String filePath) {
		String src = "D:\\src.txt";
		String dest = "D:\\temp1.xml";
		File file = new File(src);
		FileInputStream in = null;
		BufferedReader br = null;
		InputStreamReader isr = null;
		BufferedWriter wr = null;
		FileWriter writer = null;
		int n;
		byte[] a = new byte[0];
		byte[] buf = new byte[1024 * 15];
		char[] charBuf = new char[512 * 15];
		try {
			FileOutputStream fos = new FileOutputStream(new File(dest));
			in = new FileInputStream(file);
			isr = new InputStreamReader(in, "GBK");
			writer = new FileWriter(new File(dest));
			wr = new BufferedWriter(writer);
			while ((n = isr.read(charBuf, 0, 512 * 15)) != -1) {
				// a = CoderUtils.arraycopy(a, buf);
				new String(charBuf).getBytes("GBK");
				wr.write(charBuf, 0, n);
			}
			wr.flush();
		} catch (FileNotFoundException e) {
			ExceptionManage.dispose(e,this.getClass());
		} catch (IOException e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
		return a;

	}

	public static void main(String[] args) throws Exception {
		@SuppressWarnings("unused")
		byte[] bytes = new byte[100];
		bytes = CoderUtils.intToBytes(1);
		System.out.println("------");
	}

}
