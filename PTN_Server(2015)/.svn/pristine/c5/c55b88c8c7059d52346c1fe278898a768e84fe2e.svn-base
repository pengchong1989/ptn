package com.nms.drivechenxiao.test;

import java.io.InputStream;
import java.util.List;

import com.nms.drivechenxiao.analysis.interfaces.AnalysisQOS;
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.network.TcpNetwork;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcQosObject;
import com.nms.drivechenxiao.test.core.SendCommand;

public class TestForSendReciveCommand {
	private CoreOper CO = new CoreOper();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestForSendReciveCommand t = new TestForSendReciveCommand();
		t.test("200.0.0.202");
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
		
		synchronized (this) {
			try {				
				TcpNetwork tcpNetwork = new TcpNetwork();
				tcpNetwork.connect(ip, 3333);
				SendCommand sendCommand = new SendCommand();
				sendCommand.setTcpNetwork(tcpNetwork);
				byte[] command = getLoginBytes("admin", "cMPC_pxn", 0, 1, 1); // 网元账号密码
				sendCommand.write(command);
				resPonse(tcpNetwork);//接收登录成功
				
//				AnalysisNE aNe = new AnalysisNE();
//				sendCommand.write(aNe.getNE(1,3));		
//				PtnNeObject ptnNe = aNe.analysisSelectNe(resPonse(tcpNetwork), null);//生成对象
				AnalysisQOS aqos = new AnalysisQOS();
				for(int i=66 ; i<128 ; i++){					
					AcQosObject qosObject = new AcQosObject();
					qosObject.setName("l2"+i);
					qosObject.setType("ETHAC_L2");
					qosObject.setCbs("1");
					qosObject.setCir("0");
					qosObject.setCos("af1");
					qosObject.setEbs("-1");
					qosObject.setEir("0");
					qosObject.setSeq("0");
					sendCommand.write(aqos.createACQOSByte(qosObject, i, 3));
					l("-----"+CoreOper.print16String(resPonse(tcpNetwork)) );
				}
//				l(resPonse(tcpNetwork))
				
				sendCommand = null;
				tcpNetwork.close();
				tcpNetwork = null;
			} catch (Exception e) {
				System.out.println(" _NetWorkUtil.getNE error :"+e.getMessage());
			}
		}
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
}
