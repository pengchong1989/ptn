package com.nms.corba.ninterface.framework;

import mtnmVersion.Version_I;
import mtnmVersion.Version_IHelper;

import org.apache.log4j.Logger;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.PortableServer.POA;

import com.nms.corba.ninterface.framework.notify.CorbaNotifyHanderMgr;
import com.nms.corba.ninterface.framework.notify.CorbaNotifyMgr;
import com.nms.ui.manager.ExceptionManage;

import emsSessionFactory.EmsSessionFactory_I;
import emsSessionFactory.EmsSessionFactory_IHelper;

public class CorbaBooster {
	static Logger LOG = Logger.getLogger(CorbaBooster.class.getName());
	private static CorbaBooster instance = new CorbaBooster();
	private EmsSessionFactory_I sessionFactory_I;
	private Version_I version_I;

	private CorbaBooster() {

	}

	public static CorbaBooster getInstanse() {
		return instance;
	}

	public void init() {
		LOG.info("Init all config begin.");

		// 读corba配置文件
		CorbaConfig.getInstanse().init();

		// 初始化管理者
		CorbaManagerObjMgr.getInstanse().init();

		// 初始化通知转换配置
		CorbaNotifyHanderMgr.getInstance().init();
		
		//启动通知上报
		CorbaNotifyMgr.getInstance().init();
		
		// 初始化orb等corba服务
		if (!initCorbaFramework()) {
			LOG.info("corba boot failed, please check the config........");
			for (int i = 0; i < 10; i++) {
				try {
					Thread.sleep(2000L);
				} catch (InterruptedException e) {
					ExceptionManage.dispose(e,this.getClass());
				}

			}
			LOG.info("Corba init failed, quit..........");
			//System.exit(0);
		}

		LOG.info("Corba boot successfully.");

	}

	private boolean initCorbaFramework() {
		LOG.info("Corba boot begin.");

		CorbaSessionMgr.getInstanse();

		LOG.info("Init Corba Orb.");
		if (!CorbaServer.getInstanse().init()) {
			LOG.error("CorbaServer InitailCorba failed.");
			return false;
		}

		LOG.info("BindEmsSessionFactory.");
		if (!bindEmsSessionFactory()) {
			LOG.error("BindEmsSessionFactory failed.");
			return false;
		}

		LOG.info("bindCorbaVersion.");
		if (!bindCorbaVersion()) {
			LOG.error("bindCorbaVersion failed.");
			return false;
		}

		LOG.info("Run Orb.");
		CorbaServer.getInstanse().runOrb();

		LOG.info("Corba boot end.");
		return true;
	}

	private boolean bindEmsSessionFactory() {
		LOG.info("[bindEmsSessionFactory]begin.");
		try {
			NamingContextExt context = CorbaServer.getInstanse()
					.getNameContext();

			if (null == context) {
				LOG.error("[bindEmsSessionFactory]name contex is null");
				return false;
			}

			POA rootPOA = CorbaServer.getInstanse().getRootPOA();

			if (null == rootPOA) {
				LOG.error("[bindEmsSessionFactory]poa is null");
				return false;
			}

			EmsSessionFactoryImpl sessionFactory = new EmsSessionFactoryImpl();

			byte[] oid = rootPOA.activate_object(sessionFactory);

			sessionFactory_I = EmsSessionFactory_IHelper.narrow(rootPOA
					.id_to_reference(oid));

			NameComponent[] name = new NameComponent[1];

			name[0] = new NameComponent();
			name[0].id = new String(CorbaConfig.getInstanse()
					.getEmsSessionFactoryName());
			name[0].kind = new String("EMSFactory");
			context.rebind(name, sessionFactory_I);

			LOG.info("[bindEmsSessionFactory]Bind BindEmsSessionFactory id = "
					+ name[0].id + ", kind = EMSFactory");
		} catch (Exception ex) {
			LOG.error("[bindEmsSessionFactory]exception");
			return false;
		}

		LOG.info("[bindEmsSessionFactory]end.");

		return true;
	}

	private boolean bindCorbaVersion() {
		LOG.info("[bindCorbaVersion]begin.");
		try {
			NamingContextExt context = CorbaServer.getInstanse()
					.getNameContext();

			if (null == context) {
				LOG.error("[bindCorbaVersion]name contex is null");
				return false;
			}

			POA rootPOA = CorbaServer.getInstanse().getRootPOA();

			if (null == rootPOA) {
				LOG.error("[bindCorbaVersion]poa is null");
				return false;
			}

			VersionImpl version = new VersionImpl();

			byte[] oid = rootPOA.activate_object(version);

			version_I = Version_IHelper.narrow(rootPOA
					.id_to_reference(oid));

			NameComponent[] name = new NameComponent[1];

			name[0] = new NameComponent();
			name[0].id = new String(CorbaConfig.getInstanse()
					.getEmsSessionFactoryName());
			name[0].kind = new String("Version");
			context.rebind(name, version_I);

			LOG.info("[bindCorbaVersion]Bind versionImpl id = " + name[0].id
					+ ", kind = Version");
		} catch (Exception ex) {
			LOG.error("[bindCorbaVersion] exception");
			return false;
		}

		LOG.info("[bindCorbaVersion] end.");

		return true;
	}

}
