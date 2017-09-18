package com.nms.drivechenxiao.analysis.test;

import com.nms.drivechenxiao.analysis.interfaces.AnalysisETHPort;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.qos.CSObject;
/**测试nni
 * **/
public class test {
	public static void main(String[] args) {
		AnalysisETHPort analysisETHPort = new AnalysisETHPort();
		EthPortObject ethPortObject = new EthPortObject();
		CSObject cs6 = new CSObject();
		cs6.setCir("4000");
		ethPortObject.setName("ge.1.1");
		ethPortObject.setRole("nni");
		ethPortObject.setCs6(cs6);
		analysisETHPort.setPort(ethPortObject, 1, 3);
	}
}
