package com.nms.drivechenxiao.analysis.tool;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.drivechenxiao.service.bean.persvr.PersvrObject;

/**
 *处理发送命令、响应时，某些属性的转换
 * @author sy
 *
 */
public class UtilOper {
	
	/**
	 * （根据性能类型）过滤性能值
	 * @param persvr
	 *    当前性能 集合
	 * @param portList
	 *    端口集合
	 * @param pidList
	 *   性能类型集合
	 * @return
	 */
	public Map<String,String> getPerVal(List<PersvrObject> persvr,List<PortInst> portList,List<String> pidList){
		Map<String,String> mapPersvr=new HashMap<String, String>();
		
		return mapPersvr;
	}
	/**
	 * 将端口名称改为 int(十6进制)
	 * @param portType
	 * @return
	 */
	public static int stringToInteger(String portType){
		/**
		 * 分割 端口名称  ：  ge.3.20 得到 3个元素的 String数组
		 */
		StringBuffer sb=new StringBuffer();
		/**
		 * 0x08 好像是端口的类型，35的包，显示 eth -0x08 
		 *    其他设备光功率不好用，
		 */
		sb.append(8);
		String[] s=portType.split("\\.");
		int portInt=0;
		if(s.length==3){
			for(int i=0;i<s.length;i++){
				if(i==0){
					if(s[i].contains("ge")){
						sb.append(12);
					}else if(s[i].indexOf("fx")>0){
						sb.append(14);
					}else if(s[i].indexOf("fe")>0){
						sb.append(11);
					}else if(s[i].indexOf("xg")>0){
						sb.append(13);
					}else if(s[i].indexOf("tod")>0){
						sb.append(60);
					}else if(s[i].indexOf("stm1")>0){
						sb.append(21);
					}else if(s[i].indexOf("extclk")>0){
						sb.append(50);
					}else{
						return 0;
					}
				}else{
					if(s[i].length()==1){
						sb.append(0);
						sb.append(s[i]);
					}else{
						sb.append(s[i]);
					}
					
				}
			}
		}
		portInt=Integer.parseInt(sb.toString(),16);
		
		return portInt;
	
	}
}
