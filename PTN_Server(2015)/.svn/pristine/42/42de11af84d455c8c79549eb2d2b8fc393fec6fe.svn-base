package com.nms.snmp.ninteface.framework;

import java.io.File;

import org.apache.log4j.BasicConfigurator;
import org.snmp4j.agent.CommandProcessor;
import org.snmp4j.mp.MPv3;
import org.snmp4j.smi.OctetString;

import com.nms.snmp.ninteface.framework.trap.SnmpNotifyMgr;


public class AgentServer {
	
	 public void init(String[] args){
		 String address;
		    if (args.length > 0) {
		      address = args[0];
		    }
		    else {
		      address = "127.0.0.1/161";
		    }
		    BasicConfigurator.configure();
		    MOTabelMgr.getInstance().init();
		   	Agent agent = new Agent(new File("SNMP4JTestAgentBC.cfg"),
		                                           new File("SNMP4JTestAgentConfig.cfg"),
		                                           new CommandProcessor(new OctetString(MPv3.createLocalEngineID())));
		    SnmpNotifyMgr.getInstance().init(agent);
		    agent.start();
		    SnmpNotifyMgr.getInstance().start();
	 }
}

