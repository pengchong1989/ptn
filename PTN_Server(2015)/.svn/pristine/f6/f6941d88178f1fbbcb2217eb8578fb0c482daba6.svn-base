package com.nms.drivechenxiao.test;

import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.drivechenxiao.analysis.AnalysisAlarm;
import com.nms.drivechenxiao.analysis.AnalysisAlmcfg;
import com.nms.drivechenxiao.analysis.clock.AnalysisClock;
import com.nms.drivechenxiao.analysis.clock.AnalysisClockPort;
import com.nms.drivechenxiao.analysis.clock.AnalysisExtclk;
import com.nms.drivechenxiao.analysis.clock.AnalysisPtp;
import com.nms.drivechenxiao.analysis.clock.AnalysisPtpPort;
import com.nms.drivechenxiao.analysis.clock.AnalysisTod;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisETHPort;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisL2;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisPWSDH;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisQOS;
import com.nms.drivechenxiao.analysis.interfaces.AnalysisSDHPort;
import com.nms.drivechenxiao.analysis.ne.AnalysisArea;
import com.nms.drivechenxiao.analysis.ne.AnalysisRoute;
import com.nms.drivechenxiao.analysis.oam.AnalysisEthEfmOam;
import com.nms.drivechenxiao.analysis.oam.AnalysisOamTest;
import com.nms.drivechenxiao.analysis.proc.AnalysisPersvr;
import com.nms.drivechenxiao.analysis.proc.Analysislog;
import com.nms.drivechenxiao.analysis.protocols.AnalysisCard;
import com.nms.drivechenxiao.analysis.protocols.AnalysisExptocos;
import com.nms.drivechenxiao.analysis.protocols.AnalysisMsp;
import com.nms.drivechenxiao.analysis.protocols.AnalysisStaticRoute;
import com.nms.drivechenxiao.analysis.service.AnalysisCes;
import com.nms.drivechenxiao.analysis.service.AnalysisELAN;
import com.nms.drivechenxiao.analysis.service.AnalysisELine;
import com.nms.drivechenxiao.analysis.service.AnalysisETree;
import com.nms.drivechenxiao.analysis.slot.AnalysisSlotTemp;
import com.nms.drivechenxiao.analysis.tool.CoreOper;
import com.nms.drivechenxiao.analysis.weihu.AnalysisLoopBack;
import com.nms.drivechenxiao.analysis.weihu.AnalysisPower;
import com.nms.drivechenxiao.analysis.weihu.PowerObject;
import com.nms.drivechenxiao.network.TcpNetwork;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.alarm.AlarmObject;
import com.nms.drivechenxiao.service.bean.almcfg.AlmObject;
import com.nms.drivechenxiao.service.bean.almcfg.AlmcfgObject;
import com.nms.drivechenxiao.service.bean.clock.ClockObject;
import com.nms.drivechenxiao.service.bean.clock.ClockPortESObject;
import com.nms.drivechenxiao.service.bean.clock.ExtclkObject;
import com.nms.drivechenxiao.service.bean.clock.TodObject;
import com.nms.drivechenxiao.service.bean.cmap.L2Object;
import com.nms.drivechenxiao.service.bean.oam.EfmObject;
import com.nms.drivechenxiao.service.bean.oam.OamObject;
import com.nms.drivechenxiao.service.bean.oam.OamTestObject;
import com.nms.drivechenxiao.service.bean.persvr.PersvrObject;
import com.nms.drivechenxiao.service.bean.porteth.EthPortObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcObject;
import com.nms.drivechenxiao.service.bean.porteth.ac.AcQosObject;
import com.nms.drivechenxiao.service.bean.portpdh.PdhPortObject;
import com.nms.drivechenxiao.service.bean.portpdh.ac.PdhAcObject;
import com.nms.drivechenxiao.service.bean.protocols.CardProObject;
import com.nms.drivechenxiao.service.bean.protocols.MspObject;
import com.nms.drivechenxiao.service.bean.protocols.ProtectLogObject;
import com.nms.drivechenxiao.service.bean.protocols.PtpObject;
import com.nms.drivechenxiao.service.bean.protocols.PtpPortObject;
import com.nms.drivechenxiao.service.bean.protocols.StaticRouteObject;
import com.nms.drivechenxiao.service.bean.protocols.mpls.elsp.ExptocosObject;
import com.nms.drivechenxiao.service.bean.protsdh.SdhPortObject;
import com.nms.drivechenxiao.service.bean.pweth.PwEthObject;
import com.nms.drivechenxiao.service.bean.pwpdh.PwPdhObject;
import com.nms.drivechenxiao.service.bean.pwsdh.PwSdhObject;
import com.nms.drivechenxiao.service.bean.service.ces.CesObject;
import com.nms.drivechenxiao.service.bean.service.elan.ELanObject;
import com.nms.drivechenxiao.service.bean.service.eline.ELineObject;
import com.nms.drivechenxiao.service.bean.service.etree.ETreeObject;
import com.nms.drivechenxiao.service.bean.slot.SlotTempObject;
import com.nms.drivechenxiao.service.bean.xc.Route;
import com.nms.drivechenxiao.test.core.SendCommand;
import com.nms.ui.manager.ExceptionManage;

public class TestForRoute {
	private CoreOper CO = new CoreOper();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestForRoute tfr = new TestForRoute();
		try {
			tfr.testIt("20.0.0.202");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			ExceptionManage.dispose(e,TestForRoute.class);
		}
	}
	public void testIt(String ip) throws Exception{
		lg("start...");
//		String name = "extclk.3.1";
//		name = name.substring(name.indexOf(".")+1, name.length());
//		lg("---- name="+name);
		TcpNetwork tcpNetwork = new TcpNetwork();
		tcpNetwork.connect(ip, 3333);
		SendCommand sendCommand = new SendCommand();
		sendCommand.setTcpNetwork(tcpNetwork);
		byte[] command = getLoginBytes("admin", "cMPC_pxn", 0, 1, 1); // 网元账号密码
		sendCommand.write(command);
		lg(" login ed ..");
		resPonse(tcpNetwork);//接收登录成功
		//testAlmcfg(sendCommand,tcpNetwork);
	//	testSlotTemp(sendCommand,tcpNetwork);
		//testPtp(sendCommand,tcpNetwork);
		//testPtpPort(sendCommand,tcpNetwork);
		testLog(sendCommand,tcpNetwork);
		
		
	}
	//测试 告警配置
	private void testAlmcfg(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisAlmcfg ana = new AnalysisAlmcfg();
		
		sendCommand.write(ana.getGetAll(1, 3));		
		List<AlmcfgObject> la = ana.analysisAlmcfg(resPonse(tcpNetwork), null);
		
		AlmObject alm = new AlmObject();
		alm.setName("CES_AIS");
		alm.setNumberValue("5162");
		
//		sendCommand.write(ana.updateAlmcfg(alm, 1, 3));
//		resPonse(tcpNetwork);
//		System.out.println(la.toString());
	}
	/**获得MsgNotify类型**/
	public String getMsgNotifyType(byte[] bb){
		byte[] lengByte = new byte[4];
		 System.arraycopy(bb,9,lengByte,0,4);
		 int leng = CoderUtils.bytesToInt(lengByte);
		byte[] ss = new byte[leng];
		 System.arraycopy(bb,13,ss,0,leng);
		 return new String(ss).toString().trim();
	}
	//测试log方法
	private void testLog(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		Analysislog an = new Analysislog();
		
//		AnalysisAlarm alarm = new AnalysisAlarm(); 
//		sendCommand.write(alarm.getGetAll(1, 3));
		
		sendCommand.write(an.selectlogquery(1, 3));
		CXNEObject c=new CXNEObject();
		byte[] b=resPonse(tcpNetwork);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CoreOper.print16String(b);
		System.out.println(getMsgNotifyType(b));;	
		List<ProtectLogObject> p=an.analysisLogquery(b,c);
		if(p!=null&&p.size()>0){
			for(ProtectLogObject pp:p){
				System.out.println("type : "+pp.getType());
				System.out.println(" obj : "+pp.getObj());
				System.out.println("cares : "+pp.getCarse());
				System.out.println("desc :: "+pp.getDesc());
				System.out.println("time: "+df.format(new Date(Long.parseLong(pp.getTime()))));	
			}
		}else {
			System.out.println("解析失败！！！！！");
		}
	}	
	//测试静态路由;
	private void testStaticRoute(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisStaticRoute as = new AnalysisStaticRoute();
//		sendCommand.write(as.selectStaticRoute(1, 3));	
//		List<StaticRouteObject> la= as.analysisStaticRoute(resPonse(tcpNetwork), null);
		
		StaticRouteObject sr = new StaticRouteObject();
		sr.setName("20.0.0.5\\32");
		sr.setNexthop("20.0.0.35");		
		sr.setIfname("mcn/1");
		sr.setDistance("1");
//		sendCommand.write(as.deleteStaticRoute(sr, 1, 3));
		sendCommand.write(as.createStaticRoute(sr, 1, 3));
//		sendCommand.write(as.updateStaticRoute(sr, 1, 3));
		resPonse(tcpNetwork);
		
	}
	
	//测试 告警
	private void testAlarm(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisAlarm aa = new AnalysisAlarm();
		sendCommand.write(aa.getGetAll(1, 3));
		
		List<AlarmObject> la = aa.analysisSelectAlarm(resPonse(tcpNetwork), null);
	}
	//测试 性能 .
	private void testPersvr(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisPersvr anp = new AnalysisPersvr();
		
		sendCommand.write(anp.getPersvr("m15" ,1, 3));
//		List<PersvrObject> liP = anp.analysisSelectPersvr(resPonse(tcpNetwork), null);
		
		resPonse(tcpNetwork);
	}
	//测试 for 端口 使能 .
	private void testPort(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisETHPort aneth = new AnalysisETHPort();
		EthPortObject eth = new EthPortObject();
		eth.setName("ge.3.1");
		eth.setRole("nni");
		eth.setAdmin("down");
		sendCommand.write(aneth.setPort(eth, 1, 3));
		resPonse(tcpNetwork);
		
		
	}
	//测试  llsp(Clrtoexp,Exptoclr) ;else (Costoexp,Exptocos)
	private void testprotocols(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
//		AnalysisClrtoexp llspclr = new AnalysisClrtoexp();
//		AnalysisExptoclr llspexp = new AnalysisExptoclr();
//		AnalysisCostoexp elspcos = new AnalysisCostoexp();
		AnalysisExptocos exptocos = new AnalysisExptocos();

//		sendCommand.write(aqos.selectAcQOSByte("",1, 3));
//		List<AcQosObject> l = aqos.analysisAcQos(resPonse(tcpNetwork), null);
		
		ExptocosObject cxo = new ExptocosObject();
		cxo.setExp0("3");
		cxo.setExp1("1");
		cxo.setExp2("2");
		cxo.setExp3("0");
		cxo.setExp4("5");
		cxo.setExp5("4");
		cxo.setExp6("6");
		cxo.setExp7("7");
		cxo.setName("3");
		
		
		sendCommand.write( exptocos.createExptocos(cxo, 1, 3) );
//		sendCommand.write( exptocos.updateExptocos(cxo, 1, 3) );
		resPonse(tcpNetwork);
		
	}
	//测试 流细分l2
	private void testpmapAC(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisQOS aqos = new AnalysisQOS();
		
//		sendCommand.write(aqos.selectAcQOSByte("",1, 3));
//		List<AcQosObject> l = aqos.analysisAcQos(resPonse(tcpNetwork), null);
		
		AcQosObject ac = new AcQosObject();
		ac.setName("vlanpri259");
		ac.setType("ETHAC_VLANPRI");
		ac.setSeq("0");
		ac.setCos("af1");
		ac.setCir("0");
		ac.setCbs("1");
		ac.setEir("0");
		ac.setEbs("-1");
		ac.setColoraware("false");
		ac.setPir("0");
		
		//--
		List<AcQosObject> qosList = new ArrayList<AcQosObject>();
		AcQosObject acx = new AcQosObject();
		acx.setName("6");
		acx.setType("ETHAC_VLANPRI");
		acx.setSeq("0");
		acx.setCos("af1");
		acx.setCir("0");
		acx.setCbs("1");
		acx.setEir("0");
		acx.setEbs("-1");
		acx.setColoraware("true");
		acx.setPir("0");
		qosList.add(acx);
//		qosList.add(ac);
		ac.setXFAcQosList(qosList);
		
		sendCommand.write(aqos.createByteACQOS(ac, 1, 3));
//		sendCommand.write(aqos.deleteByteACQOS(ac, 1, 3));
		resPonse(tcpNetwork);
		
	}
	//测试OAM,eth链路oam
	private void testEthEfm(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisEthEfmOam efmoam = new AnalysisEthEfmOam();
//		sendCommand.write(efmoam.selectEfm(1, 3));
//		List<EfmObject> l = efmoam.analysisEfm(resPonse(tcpNetwork), null);
		
		EfmObject efm = new EfmObject();
		efm.setPortname("xg.3.2");
		efm.setEnable("1");
		efm.setWorkmode("1");
		efm.setRmtloopback("1");
		efm.setLinkevent("0");
		efm.setVarretr("0");
		efm.setMaxoampdu("1518");
		efm.setOui("0");
		efm.setVsi("0");
		efm.setLpbtimeout("5");
		efm.setErrfrmsecondsthreshold("1");
		efm.setFrequecy("1");
		efm.setLosttime("5");
		
		sendCommand.write(efmoam.createEfm(efm, 1, 3));
//		sendCommand.write(efmoam.updateEfm(efm, 1, 3));
		resPonse(tcpNetwork);
	}
	//测试msp保护
	private void testMsp(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisMsp anmsp = new AnalysisMsp();
//		sendCommand.write(anmsp.selectMsp(1, 3));
//		List<MspObject> lmsp = anmsp.analysisMsp(resPonse(tcpNetwork), null);
		
		MspObject msp = new MspObject();
		msp.setName("1");
//		msp.setType("0");//11 ;00
//		msp.setWorkport("stm1.4.1");//
//		msp.setPrtport("stm1.4.2");
//		msp.setEnaps("0");//01;00
//		msp.setWtrtime("60");//13;00 00 00 00
//		msp.setHoldofftime("0");//13;00 00 00 20
//		msp.setSdthreshold("0");//01;00
//		msp.setSfhighpri("0");//01;00
		msp.setApscmd("lock::protection");//
		
		
//		sendCommand.write(anmsp.createMsp(msp, 1, 3));
////		sendCommand.write(anmsp.deleteMsp(msp, 1, 3));
		sendCommand.write(anmsp.updateMsp(msp, 1, 3));
		resPonse(tcpNetwork);
	}
	//
	
	//测试 流细分l2
	private void testcmapl2(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisL2 anl2 = new AnalysisL2();
//		sendCommand.write(anl2.selectL2(1, 3));
//		List<L2Object> l2l = anl2.analysisL2(resPonse(tcpNetwork), null);
		
		L2Object l2 = new L2Object();
		l2.setName("l2180");//20,
		l2.setSpvlan("0");//13, 00 00 00 00
		l2.setSpvlanmask("0");//13,00000000
		l2.setCevlan("0");//13
		l2.setCevlanmask("0");//13
		l2.setSpvlanpri("0");//13
		l2.setSpvlanprimask("0");//13
		l2.setCevlanpri("0");//13
		l2.setCevlanprimask("0");//13
//		l2.setType("0");
//		l2.setRef("0");
		l2.setEthtype("0");//13,00000000
		l2.setEthtypemask("0");//13,00000000
		l2.setDstmac("0");//14,00 00 00 00 00 00 00 00
		l2.setDstmacmask("0");//14,00 00 00 00 00 00 00 00
		l2.setSrcmac("0");//14,00 00 00 00 00 00 00 00
		l2.setSrcmacmask("0");//14,00 00 00 00 00 00 00 00
		
		sendCommand.write(anl2.createL2b(l2, 1, 3));
		resPonse(tcpNetwork);
	}
	
	//测试pwsdh
	private void testpwsdh(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisPWSDH to = new AnalysisPWSDH();
		PwSdhObject p = new PwSdhObject();
		p.setAdmin("up");
		p.setCarrierif("trnnel/10");
		p.setClockrecoverymode("");
		p.setCos("ef");
		p.setInlabel("111");
		p.setName("1");
		p.setOam(null);
		p.setOutlabel("112");
		p.setPayload("2");
		p.setPeer("0.0.0.0");
		sendCommand.write(to.createPWSDH(p, null, 1, 3));
		
		resPonse(tcpNetwork);
	}
	//测试eth
	private void testSDHPort(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisSDHPort to = new AnalysisSDHPort();
		sendCommand.write(to.selectAllSdhPort( 1, 3));
//		resPonse(tcpNetwork);
		List<SdhPortObject> ltod = to.analysisSDHPort(resPonse(tcpNetwork), null);
	}
	//测试eth
	private void testETHPort(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisETHPort to = new AnalysisETHPort();
		sendCommand.write(to.selectAllEthPort( 1, 3));
//		resPonse(tcpNetwork);
		List<EthPortObject> ltod = to.analysisAllETHPort(resPonse(tcpNetwork), null);
	}
	//测试clockport
	private void testClockPort(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisClockPort to = new AnalysisClockPort();
//		sendCommand.write(to.selectClockPort( 1, 3));
//		sendCommand.write(testCommand( 1, 3));
//		resPonse(tcpNetwork);
//		List<ClockPortESObject> ltod = to.analysisClockPort(resPonse(tcpNetwork), null);
//		lg(ltod.toString());
		ClockPortESObject cp = new ClockPortESObject();
//		cp.setScspri("0");
//		cp.setEcspri("0");
//		cp.setScslockout("false");
//		cp.setEcslockout("false");
		cp.setDnugroup("0");
//		cp.setForcelevel("1");
		cp.setPortname("ge.3.1");
//		cp.setDnugroup("0");
		cp.setSsmoutputenable("false");
		
//		sendCommand.write(to.createClockPort(cp, 1, 3) );
		sendCommand.write(to.updateClockPort(cp, 1, 3));
		lg(resPonse(tcpNetwork).toString());
	}
	private void testCommand(){
		
	}
	//测试 extclk 外时钟
	private void testExtclk(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisExtclk to = new AnalysisExtclk();
		sendCommand.write(to.selectExtclk(null, 1, 3));
		List<ExtclkObject> ltod = to.analysisExtclk(resPonse(tcpNetwork), null);
		lg(ltod.toString());
	}
	//测试 ptp
	private void testPtpPort(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisPtpPort to = new AnalysisPtpPort();
		sendCommand.write(to.selectPtpPort( 1, 3));
		//List<PtpPortObject> ltod = to.analysisPtpPort(resPonse(tcpNetwork), null);
		List<PtpPortObject> ltod = to.analysisPtpPort(resPonse(tcpNetwork), null);
		for(PtpPortObject pt: ltod){
			System.out.println(pt.getPortadmin());
			System.out.println(pt.getPortname());
		}
//		lg(ltod.toString());
		//-----
		PtpPortObject pp = new PtpPortObject();
		pp.setPortname("ge.3.1");
		pp.setEnable("true");
		pp.setPorttype("1");
		pp.setDelaymechanism("1");
		pp.setVlanid("1");
		pp.setOperationmode("0");
		pp.setDelayoffset("0");
		
		pp.setAnncintv("-3");
		pp.setAnnctmo("4");
		pp.setSyncintv("-4");
		pp.setDelreqintv("0");
		pp.setPdelreqintv("0");
				
		pp.setTwo_step("true");
		List<String> portarr = new ArrayList<String>();
		portarr.add("ge.3.3");
		portarr.add("ge.3.4");
		pp.setPortlist(portarr);
		
//		sendCommand.write(to.deletePtpPort(pp, 1, 3) );
//		sendCommand.write(to.updatePtpPort(pp, 1, 3) );
//		sendCommand.write(to.createPtpPort(pp, 1, 3));
//		lg(resPonse(tcpNetwork).toString());
	}
	private void testPtp(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisPtp to = new AnalysisPtp();
//		sendCommand.write(to.selectPtp(null, 1, 3));
//		List<PtpObject> ltod = to.analysisPtp(resPonse(tcpNetwork), null);
//		lg(ltod.toString());
		//----------------
		PtpObject p = new PtpObject();
		
		p.setBmcenable("true");         //--
		p.setClkid("48df4c0014000023");//--
		p.setClockaccuracy("254");      //--
		p.setClockclass("187");        //--
		p.setPriority1("128");        //--
		p.setPriority2("128");        //--
		
		p.setDomain("0");    //-
		p.setTcdomain1("0"); //-
		p.setTcdomain2("2"); //-
		p.setTcdomain2enable("true"); //-
		p.setTcdomain3("2");        //-
		p.setTcdomain3enable("true"); //-
		p.setTcdomain4("3");           //-
		p.setTcdomain4enable("true"); //-
		p.setTcdelaymechanism("1");   //-
		p.setSlaveonly("false");      //-
		p.setVariance("7777");       //-
		p.setTodtxtype("0");      //-
		
		p.setFilterenable("true");
		p.setGmclkid("48df4c001000023");
		p.setGmclockclass("187");
		p.setGmprio1("128");
		p.setGmprio2("128");
		p.setGmvariance("2400");
		p.setMeanpathdelay("0");
		p.setOffsetfrommaster("0000000080000000");		
		p.setPrntid("48df4c00140000230000");
		
		p.setStepsremoved("0");
		
		p.setTimesoffset(null);
		
		p.setFilterenable("true");
		p.setClocktype("2");
		p.setCurrenttodsel("0");
		
		sendCommand.write(to.updatePtp(p, 1, 3) );
		resPonse(tcpNetwork);
	}
	//测试 时钟源配置/状态
	private void testTod(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisTod to = new AnalysisTod();
		sendCommand.write(to.selectTod(null, 1, 3));
		List<TodObject> ltod = to.analysisTod(resPonse(tcpNetwork), null);
		lg(ltod.toString());
	}
	//测试 for Clock
	private void testClock(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisClock cl = new AnalysisClock();
//		sendCommand.write(cl.selectClock(null,1,3));
//		List<ClockObject> Lcl = cl.analysisClock(resPonse(tcpNetwork), null);
//		lg(Lcl.toString());
		
		ClockObject co = new ClockObject();
		co.setSsmmode("true");//01,00
		co.setScswtr("5");//13,00000005
		co.setEcswtr("5");//13,00 00 00 05
		co.setFreerunlevel("4");//11,04
		co.setUnknownlevel("5");//11,05
		co.setOperationmode("0");//11,00
		co.setExtclkdrvmode("0");//11,00
		co.setSquelchmin("5");//11,05
		co.setSCSWorkState("freerun");
		co.setECSWorkState("freerun");
//		co.setSCSSelectSource("");
//		co.setECSSelectSource("");
//		co.getIeee1588().setPhysicalState("");
//		co.getIeee1588().setSCSLogicalState("");
//		co.getIeee1588().setSCSQL("");
		
		sendCommand.write(cl.updateClock(co, 1, 3) );
		resPonse(tcpNetwork);
		
	}
	//测试 for Route
	private void testRoute(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisRoute ar = new AnalysisRoute();
		sendCommand.write(ar.getRoute(1,3));
		List<Route> Lroute = ar.analysisAllRoute(resPonse(tcpNetwork), null);
		lg(Lroute);
		for(Route r: Lroute){
			String ips = r.getName().substring(0, r.getName().indexOf("\\"));
			lg(ips);
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
			byte[] tempBytes = new byte[9999999];
			byte[] commandTemp = null;
			int readByteCount = 0;
			while (true) {
				readByteCount = inputStream.read(tempBytes);
				commandTemp = new byte[command.length + readByteCount];
				System.arraycopy(command, 0, commandTemp, 0, command.length);
				System.arraycopy(tempBytes, 0, commandTemp, command.length, readByteCount);
				command = commandTemp;
				if (command != null && command.length > 8) {
lg("接收: "+CoreOper.print16String(command));					
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
	}private void lg_(String s){
		System.out.print(s);
	}private void lg(List<Route> lp){
		for(Route p : lp){
			lg(p.toString());
		}
	}
	//测试温度管理
	private void testSlotTemp(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
		AnalysisSlotTemp an = new AnalysisSlotTemp();
		SlotTempObject t=new SlotTempObject();
		t.setId(1);
		t.setTemphighthr("83");
		t.setTemplowthr("21");
		//sendCommand.write(an.updateTemp(t,0, 93399));
		sendCommand.write(an.selectTemp(t,0, 98988));
		List<SlotTempObject> Lroute = an.analysisSlotTemp(resPonse(tcpNetwork), null);
		if(Lroute!=null&&Lroute.size()>0){
			for(int i=0;i<Lroute.size();i++){
				System.out.println("success!!!");
				System.out.println(Lroute.get(i).getHisHighTemp());
				System.out.println(Lroute.get(i).getOnline());
				System.out.println(Lroute.get(i).getCurTemp());
				System.out.println(Lroute.get(i).getTemphighthr());
				System.out.println(Lroute.get(i).getTemplowthr());
			}
		}else{
			System.out.println(" null");
			//System.out.println(Lroute.get(0).getCurTemp());
		}

	}	
	/**
	 *  测试  eline
	 * @param sendCommand
	 * @param tcpNetwork
	 * @throws Exception
	 */
	public void eline(SendCommand sendCommand,TcpNetwork tcpNetwork)throws Exception{
		AnalysisELine t=new AnalysisELine();
		ELineObject eline=new ELineObject();
		eline.setName("1");
		eline.setAdmin("test");
		eline.setSvctype("1");
		
		PwEthObject pw=new PwEthObject();
		pw.setName("1");
		pw.getEline().setType("eline");
		pw.getEline().setVpn("1");
		pw.setIdentify("1");
		
		AcObject ac=new AcObject();
		ac.setName("2");
		ac.getEline().setType("eline");
		ac.getEline().setVpn("1");
		ac.setIdentify("2");
		
		AcObject ac1=new AcObject();
		ac1.setName("2");
		ac1.getEline().setType("eline");
		ac1.getEline().setVpn("1");
		ac1.setIdentify("2");
		
		EthPortObject eth=new EthPortObject();
		eth.setPortType("eth");
		eth.setName("fx.1.1");
		eth.setIdentify("fx.1.1");
		
		EthPortObject eth1=new EthPortObject();
		eth1.setPortType("eth");
		eth1.setName("fx.1.1");
		eth1.setIdentify("fx.1.1");
		
		OamObject oam=new OamObject();
		ac.setOam(oam);
	//sendCommand.write(t.deletetest(  eth1,ac1,0, 9907));
		sendCommand.write(t.updateEline( eline,ac,pw,  eth,0, 9907));
	}
	/**
	 * 测试  ces
	 * @param sendCommand
	 * @param tcpNetwork
	 * @throws Exception
	 */
	public void ces(SendCommand sendCommand,TcpNetwork tcpNetwork)throws Exception{
		AnalysisCes t=new AnalysisCes();
		CesObject cesObject=new CesObject();
		cesObject.setName("1");
		cesObject.setAdmin("down");
		cesObject.setPwnum("3");
		
		//pw
		PwPdhObject pwSdhObject=new PwPdhObject();
		pwSdhObject.setName("1");
		pwSdhObject.getCes().setType("ces");
		pwSdhObject.getCes().setVpn("1");
		pwSdhObject.setIdentify("1");
		
		//ac
		PdhAcObject pdhAcObject=new PdhAcObject();
		pdhAcObject.setName("e1.1.1");
		pdhAcObject.getCesServiceObject().setType("ces");
		pdhAcObject.getCesServiceObject().setVpn("1");
		pdhAcObject.setIdentify("e1.1.1");
		
		//端口
		PdhPortObject pdhPortObject=new PdhPortObject();
		//eth.setPortType("eth");
		pdhPortObject.setName("e1.1.1");
		pdhPortObject.setIdentify("e1.1.1");
		sendCommand.write(t.updatePdhCes( cesObject,  pdhAcObject,  pwSdhObject,  pdhPortObject,  0, 8908));
	}
	public void etree(SendCommand sendCommand,TcpNetwork tcpNetwork)throws Exception{
		AnalysisETree t=new AnalysisETree();
		ETreeObject etreeObject=new ETreeObject();
		etreeObject.setName("2");
		etreeObject.setFlag(true);
		
		PwEthObject pwEthObject=new PwEthObject();
		pwEthObject.setName("4");
		pwEthObject.setIdentify("1");
		pwEthObject.getEtree().setLearn("true");
		pwEthObject.getEtree().setRole("branch");
		
		List<PwEthObject> p1=new ArrayList<PwEthObject>();
	//	p1.add(pwEthObject);
		etreeObject.setPwDelete(p1);
		List<PwEthObject> p2=new ArrayList<PwEthObject>();
		PwEthObject p22=new PwEthObject();
		p22.setName("5");
		p22.getEtree().setType("etree");
		p22.getEtree().setVpn("1");
		p22.getEtree().setRole("branch");
		//p2.add(p22);
		etreeObject.setPwInsert(p2);
		
		List<PwEthObject> p3=new ArrayList<PwEthObject>();
		PwEthObject p331=new PwEthObject();
		p331.setName("1");
		p331.getEtree().setRole("branch");
		
		PwEthObject p33=new PwEthObject();
		p33.setName("5");
		p33.getEtree().setRole("root");
		//p3.add(p33);
	//	p3.add(p331);
		AcObject acObject=new AcObject();
		//acObject.setName("4");
		acObject.setIdentify("6");
	//	acObject.getEtree().setRole("root");
		acObject.getEtree().setVpn("1");
		acObject.getEtree().setType("etree");
		EthPortObject ethPortObject =new EthPortObject();
		ethPortObject.setPortType("eth");
		ethPortObject.setName("");
		ethPortObject.setIdentify("ge.1.3");
		ethPortObject.setPrevious("eth");
		
		
		sendCommand.write(t.updateEtree(etreeObject, p3, acObject, ethPortObject, 0, 8970));
	}
	public void elan(SendCommand sendCommand,TcpNetwork tcpNetwork)throws Exception{
		AnalysisELAN t=new AnalysisELAN();
		ELanObject elanObject=new ELanObject();
		elanObject.setName("1");
		elanObject.setAdmin("1");
		elanObject.setSvctype("1");
		elanObject.setBcastctrl("true");
		//elanObject.setLearnrule("1");
		elanObject.setDlfctrl("true");
		
		PwEthObject pwEthObject=new PwEthObject();
		pwEthObject.setName("4");
		pwEthObject.setIdentify("1");
		
		pwEthObject.getEtree().setLearn("true");
		
		List<PwEthObject> p1=new ArrayList<PwEthObject>();
		p1.add(pwEthObject);
		elanObject.setPwDelete(p1);
		List<PwEthObject> p2=new ArrayList<PwEthObject>();
		PwEthObject p22=new PwEthObject();
		p22.setName("5");
		p22.getEtree().setType("etree");
		p22.getEtree().setVpn("1");
		p22.getEtree().setRole("branch");
		p2.add(p22);
		elanObject.setPwInsert(p2);
		
		List<PwEthObject> p3=new ArrayList<PwEthObject>();
		PwEthObject p331=new PwEthObject();
		p331.setName("1");
		p331.getEtree().setRole("branch");
		
		PwEthObject p33=new PwEthObject();
		p33.setName("5");
		p33.getEtree().setRole("root");
		p3.add(p331);
		AcObject acObject=new AcObject();
		acObject.setName("4");
		acObject.setIdentify("4");
		acObject.getEtree().setRole("root");
		acObject.getEtree().setVpn("1");
		acObject.getEtree().setType("etree");
		EthPortObject ethPortObject =new EthPortObject();
		ethPortObject.setPortType("eth");
		ethPortObject.setName("ge.10.10");
		ethPortObject.setIdentify("ge.10.10");
		
		
		sendCommand.write(t.updateElan(elanObject, p3, ethPortObject, acObject, 0, 7809));
	}
	//测试 光功率
		private void power(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
			List<PortInst> port=new ArrayList<PortInst>();
			PortInst p1=new PortInst();
			p1.setPortName("ge.1.1");
			PortInst p2=new PortInst();
			p2.setPortName("ge.1.2");
			PortInst p3=new PortInst();
			p3.setPortName("ge.1.3");
			List<String> list=new ArrayList<String>();
			list.add("ge.1.1");
			list.add("ge.1.2");
			list.add("ge.1.3");
			Map<Integer,List<String>> m=new HashMap<Integer, List<String>>();
			
			
			//Map<Integer,List<String>> m1=new HashMap<Integer, List<String>>();
			List<String> list1=new ArrayList<String>();
			list1.add("ge.10.1");
			list1.add("ge.10.2");
			m.put(10, list1);
		//	m.put(1,list );
		//	port.add(p1);
			port.add(p2);
			port.add(p3);
			PowerObject power=new PowerObject();
			power.setPortMap(m);
			
			AnalysisPower ar = new AnalysisPower();
			sendCommand.write(ar.select(power, 0, 9547));	
			List<PersvrObject> per=ar.analysisSelectPersvr(resPonse(tcpNetwork),null);
			List<PowerObject> po=ar.getPowerObject(per);
			for(PowerObject pp:po){
				System.out.println(pp.getName());
				System.out.println(pp.getLb());
				System.out.println(pp.getLt());
				System.out.println(pp.getOop());
				System.out.println(pp.getIop());
				
				
			}
//			List<PersvrObject> persver=new ArrayList<PersvrObject>();
//			PersvrObject p1=new PersvrObject();
//			p1.setObjId("ge.3.1");
//			p1.setPerid("LB");
//			p1.setValue("5");
//			PersvrObject p2=new PersvrObject();
//			p2.setObjId("ge.3.1");
//			p2.setPerid("LB1");
//			p2.setValue("5");
//			PersvrObject p3=new PersvrObject();
//			p3.setObjId("ge.3.1");
//			p3.setPerid("LT");
//			p3.setValue("105");
//			PersvrObject p4=new PersvrObject();
//			p4.setObjId("ge.3.2");
//			p4.setPerid("LB");
//			p4.setValue("5");
//			PersvrObject p5=new PersvrObject();
//			p5.setObjId("ge.3.2");
//			p5.setPerid("OOP");
//			p5.setValue("123");
//			PersvrObject p6=new PersvrObject();
//			p6.setObjId("ge.3.2");
//			p6.setPerid("IOP");
//			p6.setValue("234");
//			persver.add(p1);
//			persver.add(p2);
//			persver.add(p3);
//			persver.add(p4);
//			persver.add(p5);
//			persver.add(p6);
			//List<PowerObject> po=ar.powerObject(persver);
//			for(PowerObject pow:po){
//				System.out.println(pow.getName());
//				System.out.println(pow.getLb());
//				System.out.println(pow.getLt());
//				System.out.println(pow.getOop());
//				System.out.println(pow.getIop());
//				
//			}
		}
		//测试cardP
		private void testCard(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
			AnalysisCard to = new AnalysisCard();
			CardProObject cp=new CardProObject();
			cp.setName("3");
			cp.setApscmd("forceswitch::protection");
			sendCommand.write(to.selectCardPro(cp,0, 8795));
			//sendCommand.write(to.updateCardPro(cp,0, 8795));
		//	resPonse(tcpNetwork);
			List<CardProObject> c=to.analysisCardPro(resPonse(tcpNetwork),null);
			for(CardProObject a: c){
				System.out.println(a.getName()+"dddddddd");
				System.out.println(a.getCard1());
				System.out.println(a.getCard2());
				System.out.println(a.getApscmd());
				System.out.println(a.getSel());
				
			}
			//List<SdhPortObject> ltod = to.analysisSDHPort(resPonse(tcpNetwork), null);
		}
		//测试oamtest
		private void oamTest(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
			AnalysisOamTest to = new AnalysisOamTest();
			OamTestObject o=new OamTestObject();
			o.setOamType("4");
			o.setCnt("1");
			o.setDatalen("0");
			o.setIfoutofsvc("0");
			o.setType("eth");
			o.setName("xg.10.1/1");
			sendCommand.write(to.selectOamTest(o, 0, 7867));
			//sendCommand.write(to.updateCardPro(cp,0, 8795));
			List<OamTestObject> c=to.analysisOamTe(resPonse(tcpNetwork));
			for(OamTestObject a: c){
				System.out.println(a.getResult()+"dddddddd");
				
			}
			//List<SdhPortObject> ltod = to.analysisSDHPort(resPonse(tcpNetwork), null);
		}
		//测试 Area
		private void testArea(SendCommand sendCommand,TcpNetwork tcpNetwork) throws Exception{
			AnalysisArea ana = new AnalysisArea();
			AnalysisLoopBack loopBack=new AnalysisLoopBack();
			List<PdhPortObject> l=new ArrayList<PdhPortObject>();
			List<Object > objectList=new ArrayList<Object>();
			PdhPortObject p=new PdhPortObject();
			p.setName("e1.1.5");
			//objectList.add(p);
			p.setName("e1.1.1");
			SdhPortObject s=new SdhPortObject();
			s.setName("stm1.3.1");
			s.setLoopback("2");
			objectList.add(s);
			SdhPortObject s1=new SdhPortObject();
			s1.setName("stm1.3.2");
			s1.setLoopback("0");
			objectList.add(s1);
			SdhPortObject s2=new SdhPortObject();
			s2.setName("stm1.4.2");
			s2.setLoopback("0");
			objectList.add(s2);
			SdhPortObject s3=new SdhPortObject();
			s3.setName("stm1.4.1");
			s3.setLoopback("0");
			objectList.add(s3);
			ExtclkObject e1=new ExtclkObject();
			e1.setName("3.1");
			e1.setLoopback("2");
			ExtclkObject e2=new ExtclkObject();
			e1.setName("4.1");
			e1.setLoopback("2");
			objectList.add(e1);
			objectList.add(e2);
			//p.setLoopback("0");
			
			sendCommand.write(loopBack.selectLoopBack(0, 3348));
			CXNEObject c=new CXNEObject();
			//resPonse(tcpNetwork);
			List<Object> objectList1=loopBack.analysisSelectLoopBack(resPonse(tcpNetwork),c);
			if(objectList1!=null&&objectList1.size()>0){
				for(Object obj:objectList1){
					if(obj instanceof PdhPortObject){
						PdhPortObject pdh=(PdhPortObject) obj;
						System.out.println("Name : "+pdh.getName());
						System.out.println("LoopBack : "+pdh.getLoopback());
						System.out.println("Oper : "+pdh.getOper());
					}else if(obj instanceof SdhPortObject){
						SdhPortObject sdh=(SdhPortObject) obj;
						System.out.println("Name : "+sdh.getName());
						System.out.println("LoopBack : "+sdh.getLoopback());
						System.out.println("Oper : "+sdh.getOper());
					}else if(obj instanceof ExtclkObject){
						ExtclkObject ex=(ExtclkObject) obj;
						System.out.println("Name : "+ex.getName());
						System.out.println("LoopBack : "+ex.getLoopback());
						System.out.println("Oper : "+ex.getOper());
					}else{
						System.out.println("转换对象： 未找到相应对象类别！！");
							
					}
				}
			}else{
				System.out.println(" 未找到数据");
			}
//			sendCommand.write(ana.updateAlmcfg(alm, 1, 3));
//			resPonse(tcpNetwork);
//			System.out.println(la.toString());
		}
}
