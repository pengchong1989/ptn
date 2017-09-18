package com.nms.corba.ninterface.framework;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNotifyChannelAdmin.EventChannelFactory;
import org.omg.CosNotifyChannelAdmin.EventChannelFactoryHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManager;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;

import com.nms.corba.ninterface.util.FileTools;
import com.nms.ui.manager.ExceptionManage;

public class CorbaServer {

	private static Logger LOG = Logger.getLogger(CorbaServer.class.getName());

	private static CorbaServer instance = new CorbaServer();

	private String[] orbArgs = null;

	private ORB orb = null;

	private NamingContextExt namingservice = null;

	private POA corbaPoa = null;

	private POAManager corbaPoaManager = null;

	private EventChannelFactory corbaEventChannelFactory = null;

	private int iPoaCount = 0;
	
	public CorbaServer() {

	}

	String getUserPoaName() {
		String strName = new String("corba" + iPoaCount++);
		return strName;
	}

	public static CorbaServer getInstanse() {
		return instance;
	}

	public boolean init() {
		LOG.info("CorbaServer.init begin.");

		if (!initOrbArgs()) {
			return false;
		}

		if (!initOrb()) {
			return false;
		}

		if (!initNameCtx()) {
			return false;
		}

		if (!initPOA()) {
			return false;
		}

		if (!initEventChannelFactory()) {
			return false;
		}

		LOG.info("CorbaServer.init end.");

		return true;
	}

	public void runOrb() {
		try {
			corbaPoaManager.activate();
		} catch (AdapterInactive e) {
			LOG.error("corba poa manager inactive." + e.getLocalizedMessage());
			return;
		} catch (Exception e) {
			LOG.error("corba poa manager activate unkonw exception.");
			return;
		}

		for (int i = 0; i < 10; i++) {
			new OrbThread(orb).start();
		}
	}

	public NamingContextExt getNameContext() {
		return namingservice;
	}

	public Any createAny() {
		if (null == orb) {
			return null;
		}
		return orb.create_any();
	}

	public POA getRootPOA() {
		return corbaPoa;
	}

	public ORB getORB() {
		return orb;
	}

	public EventChannelFactory getEvenChannelFactory() {
		return corbaEventChannelFactory;
	}

	public EventChannelFactory getEventChannelFactory() {
		return corbaEventChannelFactory;
	}

	private boolean initOrbArgs() {
		LOG.info("initOrbArgs succssful begin.");

		int iBindMode = CorbaConfig.getInstanse().getBindMode();
		if ((0 != iBindMode) && (1 != iBindMode) && (2 != iBindMode)) {
			LOG.error("[initOrbArgs]initOrbArgs iBindMode error.");
			return false;
		}

		if (0 == iBindMode) {
			orbArgs = new String[11];
		} else if (1 == iBindMode) {
			orbArgs = new String[13];
			orbArgs[8] = new String("0");
			orbArgs[11] = new String("-ORBEndpoint");
			orbArgs[12] = new String("iiop://"
					+ CorbaConfig.getInstanse().getHostname());
			if (CorbaConfig.getInstanse().isFixCorbaServicePort()) {
				String[] tmp163_158 = orbArgs;
				tmp163_158[12] = (tmp163_158[12] + ":" + CorbaConfig
						.getInstanse().getCorbaServicePort());
			}
		} else if (2 == iBindMode) {
			orbArgs = new String[13];
			orbArgs[8] = new String("1");
			orbArgs[11] = new String("-ORBEndpoint");
			orbArgs[12] = new String("iiop://"
					+ CorbaConfig.getInstanse().getComputerIp());
			if (CorbaConfig.getInstanse().isFixCorbaServicePort()) {
				String[] tmp295_290 = orbArgs;
				tmp295_290[12] = (tmp295_290[12] + ":" + CorbaConfig
						.getInstanse().getCorbaServicePort());
			}
		} else {
			LOG.error("[initOrbArgs]bind mode should be 0, ior; 1, localhost; 2, ip.");
			return false;
		}

		orbArgs[0] = new String("-ORBDebuglevel");
		orbArgs[1] = new String(CorbaConfig.getInstanse()
				.getOrbDebugLevel());
		orbArgs[2] = new String("-ORBDebug");
		orbArgs[3] = new String("-ORBSvcConf");

		orbArgs[4] = CorbaServer.class.getClassLoader().getResource("config/corba/kernel.conf").toString();
		orbArgs[5] = new String("-ORBInitRef");
		orbArgs[6] = new String("NameService="
				+ CorbaConfig.getInstanse().getNameServiceConfig());
		orbArgs[7] = new String("-ORBDottedDecimalAddresses");
		orbArgs[9] = new String("-ORBInitRef");
		orbArgs[10] = new String("NotificationService="
				+ CorbaConfig.getInstanse().getNotifyServiceConfig());

		for (int i = 0; i < orbArgs.length; i++) {
			LOG.info("[initOrbArgs]corba init parameter " + i + " is: "
					+ orbArgs[i]);
		}

		LOG.info("[initOrbArgs]initOrbArgs succssful begin.");
		return true;
	}

	private boolean initOrb() {
		LOG.info("[initOrb]initOrb succssful begin.");
		Properties props = new Properties();
		try {			
			String strOrbPropertyName = CorbaServer.class.getClassLoader().getResource("config/corba/orb.properties").toString().substring(6);
			LOG.info("[initOrb]orb.properties path is " + strOrbPropertyName);

			props.load(new FileInputStream(new File(strOrbPropertyName)));

			int iBindMode = CorbaConfig.getInstanse().getBindMode();
			if (1 == iBindMode) {
				props.put("OAIAddr", CorbaConfig.getInstanse().getHostname());
			} else if (2 == iBindMode) {
				props.put("OAIAddr", CorbaConfig.getInstanse().getComputerIp());
			}
			props.put("org.omg.CORBA.ORBClass", "org.jacorb.orb.ORB");
			props.put("org.omg.CORBA.ORBSingletonClass", "org.jacorb.orb.ORBSingleton");

			if (CorbaConfig.getInstanse().isFixCorbaServicePort()) {
				props.put("OAPort", CorbaConfig.getInstanse()
						.getCorbaServicePort());
			}		
			props.put("jacorb.dns.enable", CorbaConfig.getInstanse()
					.getdns_enable());

			for (Iterator iter = props.keySet().iterator(); iter.hasNext();) {
				java.lang.Object t = iter.next();
				LOG.info("[initOrbParameter]JACORB init parameter: " + t
						+ " == " + props.getProperty(t.toString()));
			}

			orb = ORB.init(orbArgs, props);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			LOG.error("[initOrb]initOrb failed.");
			return false;
		}

		LOG.info("[initOrb]initOrb successful end.");

		return true;
	}

	private boolean initNameCtx() {
		LOG.info("initNameCtx succssful begin.");

		if (null == orb) {
			LOG.error("orb is null");
			return false;
		}

		try {
			org.omg.CORBA.Object obj = orb
					.resolve_initial_references("NameService");

			namingservice = NamingContextExtHelper.narrow(obj);

			if (null == namingservice) {
				LOG.error("namingservice is null");
				return false;
			}

			String strIOR = orb.object_to_string(obj);
			LOG.debug("IOR is : " + strIOR);
			System.out.println(strIOR);

			String strFileName = CorbaServer.class.getClassLoader().getResource("").toString().substring(6)+
					 File.separator + "corba.ior";
			
			FileTools.writeFile(strFileName, strIOR);
		} catch (InvalidName e) {
			ExceptionManage.dispose(e,this.getClass());
			LOG.error("initNameCtx exception.");
			return false;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			LOG.error("initNameCtx exception.");
			return false;
		}

		LOG.info("initNameCtx succssful end.");

		return true;
	}

	private boolean initPOA() {
		LOG.info("initPOA succssful begin.");

		if (null == orb) {
			LOG.error("orb is null");
			return false;
		}

		try {
			org.omg.CORBA.Object obj = orb
					.resolve_initial_references("RootPOA");

			corbaPoa = POAHelper.narrow(obj);

			corbaPoaManager = corbaPoa.the_POAManager();

			if (null == corbaPoaManager) {
				LOG.error("corbaPoaManager is null");
				return false;
			}

		} catch (InvalidName e) {
			ExceptionManage.dispose(e,this.getClass());
			LOG.error("initPOA exception.");
			return false;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			LOG.error("initPOA exception.");
			return false;
		}

		LOG.info("initPOA succssful end.");

		return true;
	}

	private boolean initEventChannelFactory() {
		LOG.info("initEventChannelFactory succssful begin.");

		if (null == orb) {
			LOG.error("orb is null");
			return false;
		}

		try {
			org.omg.CORBA.Object obj = orb
					.resolve_initial_references("NotificationService");

			corbaEventChannelFactory = EventChannelFactoryHelper
					.narrow(obj);

			if (null == corbaEventChannelFactory) {
				LOG.error("corbaPoaManager is null");
				return false;
			}

		} catch (InvalidName e) {
			ExceptionManage.dispose(e,this.getClass());
			LOG.error("initPOA exception.");
			return false;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			LOG.error("initPOA exception.");
			return false;
		}

		LOG.info("initEventChannelFactory succssful end.");

		return true;
	}
}
