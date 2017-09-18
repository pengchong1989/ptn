package com.nms.corba.ninterface.impl.resource.tool;

import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import multiLayerSubnetwork.MultiLayerSubnetwork_T;
import multiLayerSubnetwork.SubnetworkList_THolder;
import multiLayerSubnetwork.Topology_T;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.corba.ninterface.util.SystemTool;

/**
 * 子网信息转换工具类
 *
 */
public class CorbaEMSMgrConvertTool extends CorbaConvertBase{


	/**
	 * 根据配置文件转换相应子网信息
	 * @param localProperties 读取的配置文件
	 * @param sList 转换后的子网信息对象
	 * @throws Exception 读取文件转换有误则抛出异常
	 */
	public static void convertEMSMgrCorba(Properties localProperties, SubnetworkList_THolder sList) throws Exception{
		try {
			sList.value = new MultiLayerSubnetwork_T[1];
			sList.value[0] = new MultiLayerSubnetwork_T();
			sList.value[0].name = new NameAndStringValue_T[2];
			sList.value[0].name[0] = new NameAndStringValue_T();
			sList.value[0].name[0].name = "EMS";//子网标示符
			sList.value[0].name[0].value = CorbaConfig.getInstanse().getCorbaEmsName();
			sList.value[0].name[1] = new NameAndStringValue_T();
			sList.value[0].name[1].name = "MultiLayerSubnetwork";//子网名称
			if (!localProperties.getProperty("SUBNETWORK_TOPO_TYPE").toString().matches("[0-9]+")|| !localProperties.getProperty("SUBNETWORK_ID").toString().matches("[0-9]+")) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"数值获取错误");
			}
			sList.value[0].name[1].value =  new String(localProperties.getProperty("SUBNETWORK_ID").toString().getBytes("ISO-8859-1"),"GB2312");
			sList.value[0].nativeEMSName=new String(localProperties.getProperty("SUBNETWORK_FRIENDLY_NAME").toString().getBytes("ISO-8859-1"),"GB2312");//子网友好名称
			sList.value[0].userLabel = new String(localProperties.getProperty("SUBNETWORK_FRIENDLY_NAME").toString().getBytes("ISO-8859-1"),"GB2312");//子网友好名称
			sList.value[0].owner = new String(localProperties.getProperty("SUBNETWORK_SUPPLIER_NAME").toString().getBytes("ISO-8859-1"),"GB2312");//供应商名称
			sList.value[0].subnetworkType = Topology_T.from_int(Integer.parseInt(localProperties.getProperty("SUBNETWORK_TOPO_TYPE").toString()));//子网拓扑类型
			sList.value[0].additionalInfo = new NameAndStringValue_T[1];
			sList.value[0].additionalInfo[0] = new NameAndStringValue_T();
			sList.value[0].additionalInfo[0].name = "macLocation";//子网物理地址			 
			String os = SystemTool.getOSName();  
			String mac = "";
		    if (os.equals("windows 7")) {  
		        mac = SystemTool.getMACAddress();  
		    } else if (os.startsWith("windows")) {  
		        // 本地是windows  
		        mac = SystemTool.getWindowsMACAddress();  
		    } else {  
		        // 本地是非windows系统 一般就是unix  
		        mac = SystemTool.getUnixMACAddress();  
		    }  
			sList.value[0].additionalInfo[0].value = mac;
			sList.value[0].supportedRates = new short[]{(short)ELayerRate.TUNNEL.getValue(),(short)ELayerRate.TOPOLOGICALLINK.getValue(),(short)ELayerRate.PW.getValue()};
			
		} catch (UnsupportedEncodingException e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"内部执行出错 赋值错误");
		} 
	}

}
