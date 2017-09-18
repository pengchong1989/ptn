package com.nms.drivechenxiao.analysis;
/**解析处理
 * **/
public class AnalysisInformation {

	public String AnalysisResponds(byte[] Responds) {
		String info = "";
		String s = new String(Responds);

		if (Responds[8] == 33) {
			byte[] b = new byte[Responds[28]];
			System.arraycopy(Responds, 29, b, 0, Responds.length - 29);
			s = new String(b);
			info = s;
		} else if (Responds[8] == 34 || Responds[8] == 35) {
			if (Responds[21] == 0) {
				info = "1";
			} else {
				byte[] b = new byte[Responds.length - 29];
				System.arraycopy(Responds, 29, b, 0, Responds.length - 29);
				s = new String(b);
				// s = s.substring(29);
				String oneS = s.substring(0, 3);
				String lastS = s.substring(3);
				int i = lastS.indexOf(oneS);
				info = s.substring(i + 3);
			}
		}
		if ("".equals(info)) {
			info = "0";
		}
		return info;
	}
}
