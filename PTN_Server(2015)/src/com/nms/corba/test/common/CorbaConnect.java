package com.nms.corba.test.common;

import nmsSession.NmsSession_I;
import nmsSession.NmsSession_IPOA;
import nmsSession.NmsSession_IPOATie;

import org.omg.CORBA.SystemException;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import session.Session_I;

import com.nms.ui.manager.ExceptionManage;
import common.Common_IHolder;

import emsMgr.EMSMgr_I;
import emsMgr.EMSMgr_IHelper;
import emsSession.EmsSession_I;
import emsSession.EmsSession_IHolder;
import emsSession.EmsSession_IPackage.managerNames_THolder;
import emsSessionFactory.EmsSessionFactory_I;
import emsSessionFactory.EmsSessionFactory_IHelper;
import fileTransfer.FileTransferMgr_I;
import globaldefs.ProcessingFailureException;

public class CorbaConnect {
	public static EmsSession_I emsSession = null;
	static org.omg.CORBA.ORB orb = null;
	static NamingContext nameContext = null;
	static POA rootPOA = null;
	
    //ems管理者
	EMSMgr_I emsMgr = null;
	//FileTransfer管理器
	FileTransferMgr_I fileTransferMgr;
	public CorbaConnect()
	{
		
	}
	
	public boolean isConnect()
	{
		if(init())
		{
			System.out.println("CorbaConnect 启动正常.........");
			
			return true;
		}
		else
		{
			System.out.println("CorbaConnect 启动异常.........");
			return false;
		}
	}
	
	private boolean init() {
		// 建立连接登录、建立消息通道
		if (!initOrb()) {
			return false;
		}

		if (!initNameService()) {
			return false;
		}
		// 开始准备登陆并且获取EmsSession!
		EmsSessionFactory_I emsFactory = getEmsSessionFactory();
		if (emsFactory == null) {
			return false;
		}

		if (!initAndActiveRootPOA()) {
			return false;
		}

		NmsSession_I nmsSession = getNmsSession_I();
		if(nmsSession == null){
			return false;
		}
		System.out.println("NMSsession ---" + nmsSession.toString());
		
		EmsSession_IHolder sessionHolder = new EmsSession_IHolder();
		try {
			System.out.println("获取EmsSession引用对象");
			emsFactory.getEmsSession("admin", "admin", nmsSession, sessionHolder);
			emsSession = sessionHolder.value;
			System.out.println("EMSsession ---" + emsSession.toString());

		} catch (ProcessingFailureException ex) {
			System.out
					.println("获取EmsSession引用对象，异常！---ProcessingFailureException---");
			System.out.println("可能是用户名或者密码错误，或者权限不够，或者已登陆的用户还未退出！");
			System.out.println(ex.toString());
		}

		// 获得所支持的管理器
		try {
			managerNames_THolder mgrHolder = new managerNames_THolder();
			emsSession.getSupportedManagers(mgrHolder);
			String[] manages = mgrHolder.value;
			for (int i = 0; i < manages.length; i++) {
				System.out.println("管理器--Manager " + i + "  " + manages[i]);
			}
		} catch (ProcessingFailureException pfe) {
			System.out.println("获得所支持的管理器,异常！---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		
//		try {
//			System.out.println("初始化 FileTransfer 管理器!");
//			Common_IHolder mgrHolder = new Common_IHolder();
//			emsSession.getManager("FileTransfer", mgrHolder);
//			fileTransferMgr = FileTransferMgr_IHelper.narrow(mgrHolder.value);
//			System.out.println("FileTransfer_Manager To String ---" + fileTransferMgr.toString());
//		} catch (ProcessingFailureException pfe) {
//			System.out.println("初始化 FileTransferMgr_IHelper 管理器异常!---ProcessingFailureException---");
//			System.out.println(pfe.toString());
//		}
		
		try {
			System.out.println("初始化 EMS 管理器!");
			Common_IHolder mgrHolder = new Common_IHolder();
			emsSession.getManager("EMS", mgrHolder);
			emsMgr = EMSMgr_IHelper.narrow(mgrHolder.value);
			System.out.println("EMS_Manager To String ---" + emsMgr.toString());
		} catch (ProcessingFailureException pfe) {
			System.out
					.println("初始化 EMS 管理器异常!---ProcessingFailureException---");
			System.out.println(pfe.toString());
		}
		return true;
	}
	
	private boolean initOrb() {
		try {
			String[] args = new String[2];
			args[0] = "-ORBInitRef";
			args[1] = "NameService=corbaloc::" + "127.0.0.1" + ":" + "8801"
					+ "/NameService";
			for (int i = 0; i < args.length; i++) {
				System.out.println("初始化ORB对象的启动参数为： arg[" + i + "] = "
						+ args[i]);
			}
			orb = org.omg.CORBA.ORB.init(args, null);
			System.out.println("成功初始化ORB对象!－－－－Initialize Orb");
		} catch (SystemException ex) {
			System.out.println("初始化ORB对象异常！");
			System.out.println(ex.getMessage());
		}
		if (orb == null) {
			System.out.println("orb == null");
			return false;
		}
		return true;
	}

	private boolean initNameService() {

		try {
			org.omg.CORBA.Object objRef = orb
					.resolve_initial_references("NameService");
			nameContext = NamingContextHelper.narrow(objRef);
			System.out.println("成功获取取名字服务!－－－－Get Nameservice reference");
		} catch (org.omg.CORBA.ORBPackage.InvalidName ex) {
			System.out.println("取名字服务索引异常!");
			System.out.println(ex.getMessage());
			return false;
		}

		return true;
	}

	private EmsSessionFactory_I getEmsSessionFactory() {

		EmsSessionFactory_I emsFactory = null;
		NameComponent[] name = new NameComponent[1];
		name[0] = new NameComponent("DATAX/INM200", "EMSFactory");
		org.omg.CORBA.Object obj = null;
		try {
			obj = nameContext.resolve(name);
			emsFactory = EmsSessionFactory_IHelper.narrow(obj);
			System.out
					.println("成功获取EMSSession工厂!   Get Reference to EMSSessionFactory");
		} catch (NotFound ex) {
			System.out.println("取EMSSession工厂异常!---NotFound---");
			System.out.println(ex.getMessage());
		} catch (org.omg.CosNaming.NamingContextPackage.InvalidName ex) {
			System.out.println("取EMSSession工厂异常!---InvalidName---");
			System.out.println(ex.getMessage());
		} catch (org.omg.CosNaming.NamingContextPackage.CannotProceed ex) {
			System.out.println("取EMSSession工厂异常!---CannotProceed---");
			System.out.println(ex.getMessage());
		}

		return emsFactory;
	}

	private boolean initAndActiveRootPOA() {
		try {
			rootPOA = POAHelper.narrow(orb
					.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();
		} catch (AdapterInactive e) {
			ExceptionManage.dispose(e,this.getClass());
			return false;
		} catch (InvalidName e) {
			ExceptionManage.dispose(e,this.getClass());
			return false;
		}
		return true;
	}

	private NmsSession_I getNmsSession_I() {
		NmsSession_I session = null;
		try {
			NmsSessionImpl nmsSessionImpl = new NmsSessionImpl();
			rootPOA.activate_object(nmsSessionImpl);
			System.out.println("创建NmsSession并且托管给POA!");
			NmsSession_IPOATie tie = new NmsSession_IPOATie(nmsSessionImpl,
					rootPOA);
			System.out.println("在orb上激活NmsSession对象!");
			session = tie._this(orb);
		} catch (ServantAlreadyActive e) {
			ExceptionManage.dispose(e,this.getClass());
		} catch (WrongPolicy e) {
			ExceptionManage.dispose(e,this.getClass());
		}

		return session;
	}
	
	public class NmsSessionImpl extends NmsSession_IPOA {

		@Override
		public void eventLossOccurred(String startTime, String notificationId) {
			// TODO Auto-generated method stub

		}

		@Override
		public void eventLossCleared(String endTime) {
			// TODO Auto-generated method stub

		}

		@Override
		public void alarmLossOccurred(String startTime, String notificationId) {
			// TODO Auto-generated method stub

		}

		@Override
		public Session_I associatedSession() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void ping() {
			// TODO Auto-generated method stub
			System.out.println("ems ping nms");
		}

		@Override
		public void endSession() {
			// TODO Auto-generated method stub

		}

	}
}
