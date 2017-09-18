package com.nms.ui.ptn.upgradeManage;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import com.nms.ui.manager.ExceptionManage;

public class AnalysisFileToByte {
	public List<byte[]> readFile(ZipEntry zipEntry,ZipFile zipFile){
		InputStream inputStream = null;
		List<byte[]> bytesList = new ArrayList<byte[]>();
		try {
			inputStream = zipFile.getInputStream(zipEntry);
			byte[] readBytes = new byte[40*1024-(40* 1024 /1450+1)*12-15-16-21];
			int readLength = inputStream.read(readBytes);
			while (readLength != -1)// 读取数据到文件输出流
			{
				byte[] b = new byte[readBytes.length];
				while (readLength < 40*1024-(40* 1024 /1450+1)*12-15-16-21) {//流长度如果大于readBytes的length，就一直读取到readBytes的length
					int l = inputStream.read(readBytes, readLength, 40*1024-(40* 1024 /1450+1)*12-15-16-21 - readLength);
					if(l != -1){
						readLength += l;
					}else{
						break;
					}
						
				 }
				b = Arrays.copyOf(readBytes, readLength);
				bytesList.add(b);

				readLength = inputStream.read(readBytes, 0, readBytes.length);
			
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			try {
				// 关闭相关对象
				if(inputStream != null){
					inputStream.close();
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
		}
		return bytesList;
	}

}