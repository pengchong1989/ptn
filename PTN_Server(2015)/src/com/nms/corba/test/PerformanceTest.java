package com.nms.corba.test;

import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import performance.PMDataIterator_IHolder;
import performance.PMDataList_THolder;
import performance.PMTPSelect_T;
import performance.PerformanceManagementMgr_I;
import performance.PerformanceManagementMgr_IHelper;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.test.common.CorbaConnect;
import common.Common_IHolder;

/**
 * @author guoqc
 * 测试查询历史性能和查询当前性能
 */
public class PerformanceTest {
	private PerformanceManagementMgr_I performI;
	public PerformanceTest() throws ProcessingFailureException{
		Common_IHolder common = new Common_IHolder();
		CorbaConnect.emsSession.getManager("PerformanceManagement", common);
		performI = PerformanceManagementMgr_IHelper.narrow(common.value);
	}
	
	public static void main(String[] args) throws ProcessingFailureException {
		CorbaConnect c = new CorbaConnect();
		c.isConnect();
		PerformanceTest performTest = new PerformanceTest();
		performTest.getAllCurrentPMData();//测试查询当前性能
//		performTest.getHistoryPMData();//测试查询历史性能
	}
	
	/**
	 * 查询历史性能
	 */
	private void getHistoryPMData() throws ProcessingFailureException{
		String destination = "D:/hisPerform.txt";//文件存放路径
		String userName = "";//ftp用户名
		String password = "";//ftp密码
		String[] pmParameters = new String[]{};//只支持为空，即查所有
		String startTime = "2014-05-05 09-15-00";//起始时间
		String endTime = "2014-05-05 12-30-00";//结束时间
		boolean forceUpload = true;//网管是否从网元上传所有的性能数据 true/false 查询/不查询
		PMTPSelect_T[] pm = new PMTPSelect_T[1];//此参数指定什么样的历史性能数据需要被返回，如为空，所有数据返回，包括各种速率，各种粒度
		pm[0] = new PMTPSelect_T();
		pm[0].name = new NameAndStringValue_T[2];
		pm[0].name[0] = new NameAndStringValue_T("", (CorbaConfig.getInstanse().getCorbaEmsName()));//厂商信息
		pm[0].name[1] = new NameAndStringValue_T("", "3");//siteId
		pm[0].pMLocationList = new String[2];
		pm[0].pMLocationList[0] = "15min";//15分钟或24小时
		pm[0].pMLocationList[1] = 0+"";//
		pm[0].layerRateList = new short[]{};
		pm[0].granularityList = new String[]{};
		performI.getHistoryPMData(destination, userName, password, pm, 
									pmParameters, startTime, endTime, forceUpload);
	}
	
	/**
	 * 查询当前性能
	 */
	private void getAllCurrentPMData() throws ProcessingFailureException{
		String[] pmParameters = new String[]{};
		PMTPSelect_T[] pm = new PMTPSelect_T[1];
		pm[0] = new PMTPSelect_T();
		pm[0].name = new NameAndStringValue_T[2];
		pm[0].name[0] = new NameAndStringValue_T("", (CorbaConfig.getInstanse().getCorbaEmsName()));//厂商信息
		pm[0].name[1] = new NameAndStringValue_T("", "3");//siteId
		pm[0].pMLocationList = new String[2];
		pm[0].pMLocationList[0] = "15min";//15分钟或24小时
		pm[0].pMLocationList[1] = 0+"";//第几个15分钟或第几个24小时
		pm[0].layerRateList = new short[]{};
		pm[0].granularityList = new String[]{};
		PMDataList_THolder pmDataList = new PMDataList_THolder();
		PMDataIterator_IHolder pmIt = new PMDataIterator_IHolder();
		performI.getAllCurrentPMData(pm, pmParameters, 10, pmDataList, pmIt);
	}
}
