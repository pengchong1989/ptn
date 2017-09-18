package com.nms.drivechenxiao.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ExceptionManage;

public class TestForIp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestForIp te = new TestForIp();
		te.tet("104757002647");
		
	}
	public void tet(String longs){
		String ips= CoderUtils.longToIpAddress(Long.valueOf(longs));
		System.out.println("------- ips="+ips);
	}
	public void TestForIps() {
		l("start ..");
		long l = System.currentTimeMillis();
		List<String> f = findIp("20.0.0.200");
		long u = System.currentTimeMillis()-l ;
		l(" time used : "+u);
		l(" f.size = "+f.size()+" ;");
		l("");
		l(f);
//		l(" ++ "+isUsedIPAddress("20.0.0.20"));
		l("end ..");
	}

	public List<String> findIp(String ip){
		if(!isIp(ip)) return new ArrayList<String>();
		String[] sb=ip.split("\\.");
		List<String> temIp =new ArrayList<String>();
		List<String> haveIp =new ArrayList<String>();
		for(int i=1;i<=254;i++){
			if(!sb[3].equals(i+"")){
				String t =new StringBuffer().append(sb[0]).append(".").append(sb[1]).append(".").append(sb[2]).append(".").append(i).toString();
				if(isUsedIPAddress(t.trim())){
					temIp.add(t);
				}
//				l_(" i = "+i);				
			}
		}
		for(String ips : temIp){
			if(haveIp(ips,3333,100)){
				haveIp.add(ips);
			}
		}//校验是否开通 3333网管接口.
		return haveIp;
	}
	public boolean isIp(String ip){
		String[] sb=ip.split("\\.");
		if(sb.length==4){return true;}
		else{return false;}
	}
	//测试ip
	public boolean haveIp(String ip,int port,int timeout){
		Socket clientSocket = new Socket();
		try {
			clientSocket.setReuseAddress(true);
//			SocketAddress remoteAddrL=new InetSocketAddress("localhost",3333);
//			clientSocket.bind(remoteAddrL);
			SocketAddress remoteAddr=new InetSocketAddress(ip,port);
			clientSocket.connect(remoteAddr,timeout);
			remoteAddr =null;			
			clientSocket.close();
			return true;			
		} catch (Exception e) {
			clientSocket = null;
			return false;
		}
	}
	private void l(Boolean s){
		System.out.println(s);
	}private void l(String s){
		System.out.println(s);
	}private void l_(String s){
		System.out.print(s);
	}
	private void l(List<String> l){
		for(String t : l){
			l(t);
		}
	}
	private void l(String[] s){
		for(int i=0;i<s.length;i++){
			l_(s[i]+" ; ");
		}l("");
	}
	//压力测试
	public void test(String ip) {
		boolean b ;
		long timel;
		long timee;
		for(int i=0;i<200 ;i++){
			try {
				timel=System.currentTimeMillis();
//				b=InetAddress.getByName(ip).isReachable(3000);
				b=haveIp(ip,3333,100);
				timee=System.currentTimeMillis();
				l("i="+i+" ; t="+(timee-timel)+" ; "+b);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				ExceptionManage.dispose(e,this.getClass());
			} 
		}
	}
	//ping 测试 机器是否存在 .//由于java sock通信需要多次握手，不适合检测机器是否存在
	public boolean isUsedIPAddress(String ip) {         
		   synchronized (this) {          
		       Process process = null;      
		       BufferedReader bufReader = null;      
		       String bufReadLineString = null;      
		       try {      
		           process = Runtime.getRuntime().exec( "ping " + ip + " -w 100 -n 1");      
		           bufReader = new BufferedReader(new InputStreamReader(process.getInputStream()));      
		           for (int i = 0; i < 6 && bufReader != null; i++) {      
		               bufReader.readLine();      
		           }      
		           bufReadLineString = bufReader.readLine();           
		           if (bufReadLineString == null) {      
		               process.destroy();      
		               return false;      
		           }      
		           if (bufReadLineString.indexOf("timed out") > 0     
		                   || bufReadLineString.length() < 17     
		                   || bufReadLineString.indexOf("unreachable") > 0 
		                   || bufReadLineString.indexOf("invalid") > 0) {      
		               process.destroy();      
		               return false;      
		           }      
		       } catch (IOException e) {      
		           ExceptionManage.dispose(e,this.getClass());      
		       }      
		       process.destroy();      
		       return true;      
		  }
	}	   
}
