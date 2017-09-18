package com.nms.drivechenxiao.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import com.nms.drivechenxiao.analysis.ne.AnalysisNE;
import com.nms.drivechenxiao.analysis.ne.AnalysisRoute;
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.service.bean.ptnne.PtnNeObject;
import com.nms.drivechenxiao.service.bean.xc.Route;
import com.nms.drivechenxiao.test.core.SendCommand;
import com.nms.ui.manager.ExceptionManage;
/**网络处理类
 * **/
public class NetWorkUtil {
	private CoreOper CO = new CoreOper();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		NetWorkUtil nw= new NetWorkUtil();
		String ipin = "200.0.0.203";
//		nw.getNE(ipin);
		List<PtnNeObject> listPtn = nw.getPtnNeObject(ipin);
		nw.lg("size = "+listPtn.size());
		nw.lg(listPtn);
	}
	//ping 测试 机器是否存在 .//由于java sock通信需要多次握手，不适合检测机器是否存在
	private boolean isUsedIPAddress(String ip) {         
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
	//测试ip 是否开通3333网管通信端口
	@SuppressWarnings("unused")
	private boolean haveIp(String ip,int port,int timeout){
		Socket clientSocket = new Socket();
		try {
			clientSocket.setReuseAddress(true);
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
	//校验ip地址 格式规则
	private boolean isIp(String ip){
		String[] sb=ip.split("\\.");
		if(sb.length==4){return true;}
		else{return false;}
	}
	/**局域网搜索。提供一个ip，把所在局域网内，同 ip段所有开网管接口的机器ip地址 返回。
	 * 目前单线程。需要逐个ip扫描时间(timeused=81922ms)。
	 * @param ip
	 * @return
	 */
	public List<PtnNeObject> getPtnNeObject(String ipin){
		if(!isIp(ipin)) return new ArrayList<PtnNeObject>();
		PtnNeObject ptnNZ = getNE(ipin); //主机
		List<Route> routeL = getRoute(ipin);
		
		if(ptnNZ==null||routeL==null)return new ArrayList<PtnNeObject>(); //网管主机连接不上，直接返回空 队列 。
		
		List<String> temIp =new ArrayList<String>();
		List<PtnNeObject> haveIp =new ArrayList<PtnNeObject>();
		haveIp.add(ptnNZ); //加入主机自己
		
		for(Route ro : routeL){
			if("6".equals(ro.getType()) ){// type=6(OSPF) ; 2(CONNECT)  
				String ips = ro.getName().substring(0, ro.getName().indexOf("\\"));
				temIp.add(ips);
			}
		}
		for(String ips : temIp){
			PtnNeObject ptnN = getNE(ips);
			if(ptnN != null){
				haveIp.add(ptnN);
			}
		}//校验是否开通 3333网管接口.
		return haveIp;
		
	}
	/**以下方法由于不再使用ip扫描方式，但兴许往后有价值，所以暂且后面加上“old”
	 * **/
	public List<PtnNeObject> getPtnNeObject_old(String ipin){
		if(!isIp(ipin)) return new ArrayList<PtnNeObject>();
		PtnNeObject ptnNZ = getNE(ipin); //主机
		
		if(ptnNZ==null)return new ArrayList<PtnNeObject>(); //网管主机连接不上，直接返回空 队列 。
		
		String idz = ptnNZ.getId();
		if(!isIp(idz)) return new ArrayList<PtnNeObject>();
		
		String[] sb=idz.split("\\.");
		List<String> temIp =new ArrayList<String>();
		List<PtnNeObject> haveIp =new ArrayList<PtnNeObject>();
		haveIp.add(ptnNZ); //加入主机自己
		
		for(int i=1;i<=254;i++){
			if(!sb[3].equals(i+"")){
				String t =new StringBuffer().append(sb[0]).append(".").append(sb[1]).append(".").append(sb[2]).append(".").append(i).toString();
				if(isUsedIPAddress(t.trim())){
					temIp.add(t);
				}		
			}
		}	
		for(String ips : temIp){
			PtnNeObject ptnN = getNE(ips);
			if(ptnN != null){
				haveIp.add(ptnN);
			}
		}//校验是否开通 3333网管接口.
		return haveIp;
	}
	
	//得到 NE
	private  PtnNeObject getNE(String ip){
		synchronized (this) {
		try {
			TcpNetwork tcpNetwork = new TcpNetwork();
			tcpNetwork.connect(ip, 3333);
			SendCommand sendCommand = new SendCommand();
			sendCommand.setTcpNetwork(tcpNetwork);
			byte[] command = getLoginBytes("admin", "cMPC_pxn", 0, 1, 1); // 网元账号密码
			sendCommand.write(command);
			resPonse(tcpNetwork);//接收登录成功
			AnalysisNE aNe = new AnalysisNE();
			sendCommand.write(aNe.getNE(1,3));		
			PtnNeObject ptnNe = aNe.analysisSelectNe(resPonse(tcpNetwork), null);//生成对象
			aNe=null;
			sendCommand = null;
			tcpNetwork.close();
			tcpNetwork = null;
			return ptnNe;
		} catch (Exception e) {
			System.out.println(" _NetWorkUtil.getNE error :"+e.getMessage());
			return null;
		}
		}
	}
	private byte[] getLoginBytes(String user, String password, int logoutBool, int session, int seqid) {
		byte[] msg = new byte[0];
		byte[] logout = new byte[1];
		if (logoutBool == 0) {
			logout[0] = 0x00;
		} else {
			logout[0] = 0x01;
		}
		msg = CO.arraycopy(msg, logout);
		msg = CO.arraycopy(msg, CO.getCxtString(user));
		msg = CO.arraycopy(msg, CO.getCxtString(password));
		byte[] command = CO.getHeader(msg.length, CoreOper.ECXTMSG_REQ_LOGIN, session, seqid, true);
		command = CO.arraycopy(command, msg);
//		 CO.print16String(command);
		return command;
	}
	
	private byte[] resPonse(TcpNetwork tcpNetwork){
		byte[] command = new byte[0];	
		try {
			InputStream inputStream = tcpNetwork.getInputStream();
			byte[] tempBytes = new byte[1024];
			byte[] commandTemp = null;
			int readByteCount = 0;
			while (true) {
				readByteCount = inputStream.read(tempBytes);
				commandTemp = new byte[command.length + readByteCount];
				System.arraycopy(command, 0, commandTemp, 0, command.length);
				System.arraycopy(tempBytes, 0, commandTemp, command.length, readByteCount);
				command = commandTemp;
				if (command != null && command.length > 8) {
					return command;
				}
			}
		} catch (Exception e) {
			System.out.println(" _NetWorkUtil.response error :"+e.getMessage());
			return new byte[0];
		}
	}
	
	private void lg(String s){
		System.out.println(s);
	}
	@SuppressWarnings("unused")
	private void lg_(String s){
		System.out.print(s);
	}private void lg(List<PtnNeObject> lp){
		for(PtnNeObject p : lp){
			lg(p.toString());
		}
	}
	public List<Route> getRoute(String ip) {
		try{
		TcpNetwork tcpNetwork = new TcpNetwork();
		tcpNetwork.connect(ip, 3333);
		SendCommand sendCommand = new SendCommand();
		sendCommand.setTcpNetwork(tcpNetwork);
		byte[] command = getLoginBytes("admin", "cMPC_pxn", 0, 1, 1); // 网元账号密码
		sendCommand.write(command);
		lg(" login ed ..");
		resPonse(tcpNetwork);//接收登录成功
		AnalysisRoute ar = new AnalysisRoute();
		sendCommand.write(ar.getRoute(1,3));
		List<Route> Lroute = ar.analysisAllRoute(resPonse(tcpNetwork), null);
		ar=null;
		sendCommand = null;
		tcpNetwork.close();
		tcpNetwork = null;
		return Lroute;
		}catch (Exception e) {
			System.out.println(" _NetWorkUtil.response error :"+e.getMessage());
			return null;
		}
	}
	
}
