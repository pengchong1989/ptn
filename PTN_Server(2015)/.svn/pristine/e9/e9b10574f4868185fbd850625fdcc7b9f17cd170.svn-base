package com.nms.rmi.ui.util;


import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import com.champor.license.Features;
import com.champor.license.client.LicenseClient;


/**
 * 许可客户端的辅助类
 * 
 * @author kk
 * 
 */
public class LicenseClientUtil {

	/**
	 * 根据文件路径获取许可对象
	 * 
	 * @param filePath
	 *            文件路径
	 * @return null为没获取到
	 * @throws Exception
	 */
	public Features getFeatures(String filePath,String ip) throws Exception {
		LicenseClient lClient = null;
		Features features = null;
		try {
		lClient = new LicenseClient();
		features = lClient.ReadLicense(filePath, ServerConstant.LICENSEPATH + ServerConstant.LICENSEDLLNAME, ip);
          
		} catch (Exception e) {
			throw e;
		} finally {
			lClient = null;
		}

		return features;
	}

	
	
	public List<String> getIp() throws Exception {
		
	       Enumeration<?> netInterfaces; 
	       ArrayList<NetworkInterface> netlist=new ArrayList<NetworkInterface>();
	       List<String> iplist=new ArrayList<String>();
	       try {
	               netInterfaces = NetworkInterface.getNetworkInterfaces();//获取当前环境下的所有网卡
	               while (netInterfaces.hasMoreElements()) {
	               NetworkInterface ni=(NetworkInterface)netInterfaces.nextElement();
	               if(ni.isLoopback()) 
	                    continue;//过滤 lo网卡
	               netlist.add(0,ni);//倒置网卡顺序
	              }  
	               for(NetworkInterface list:netlist) { //遍历每个网卡	         
	                Enumeration<?> cardipaddress = list.getInetAddresses();//获取网卡下所有ip	                
	                while(cardipaddress.hasMoreElements()){//将网卡下所有ip地址取出
	                    InetAddress ip = (InetAddress) cardipaddress.nextElement();
	                        if(!ip.isLoopbackAddress()){
	                        if(ip.getHostAddress().equalsIgnoreCase("127.0.0.1")){
	                       continue; 
	                      } 
	                    if(ip instanceof Inet6Address)  {   //过滤ipv6地址  add by liming 2013-9-3 
	                       continue; 
	                      }
	                    if(ip instanceof Inet4Address)  {    //返回ipv4地址
	                    	iplist.add(ip.getHostAddress());
	                    }
	                }
	                }
	         
	          }
	             Collections.reverse(iplist); 
	    
	 } catch (SocketException e) {
	 e.printStackTrace();
	 } catch (Exception e) {
	 e.printStackTrace();
	 } 
	 return iplist;
	   } 
}
