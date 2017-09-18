package com.nms.rmi.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Date;

import com.champor.license.Features;
import com.nms.jms.common.ApplicationBeanFactory;
import com.nms.jms.jmsMeanager.Broker;
import com.nms.model.util.CodeConfigItem;
import com.nms.rmi.ui.ServiceStartPanel;
import com.nms.rmi.ui.util.LicenseClientUtil;
import com.nms.rmi.ui.util.ServerConstant;
import com.nms.rmi.ui.util.ServiceInitUtil;
import com.nms.snmp.ninteface.framework.AgentServer;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.util.EquimentDataUtil;
import com.nms.util.Mybatis_DBManager;

/**
 * 服务器端启动线程
 * 
 * @author kk
 * 
 */
public class StartThread implements Runnable {

	private ServiceStartPanel serviceStartPanel = null;

	public StartThread(ServiceStartPanel serviceStartPanel) {
		this.serviceStartPanel = serviceStartPanel;
	}

	@Override
	public void run() {
		
		//测试用户是否在管理权限下执行的
		try {
			isRootManager(ServerConstant.LICENSEPATH+ServerConstant.LICENSEFILENAME);
		} catch (Exception e) {
			serviceStartPanel.buttonResult(true, false, ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_MANAGER_IMPORT_FAIL));
			return;
		}
		
		LicenseClientUtil licenseClientUtil=null;
		Features features=null;
		EquimentDataUtil equimentDataUtil=new EquimentDataUtil();
		// 获取数据库连接，如果获取到了，flag=false 并且结束循环
		boolean flag = true;
		try {
			String resultStr = ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RUN); // 返回启动结果
			try {
				   licenseClientUtil = new LicenseClientUtil();
			        features = licenseClientUtil.getFeatures(ServerConstant.LICENSEPATH+ServerConstant.LICENSEFILENAME,ConstantUtil.serviceIp);			     
					if(null != features){							
						Date currTime = new Date();
						Date licenseTime=features.getFeatureList().get(0).getExpiresDate();
						//如果许可的时间正常即可使用，如过期则提示
						if(currTime.getTime() < licenseTime.getTime())
						{
							ServerConstant.features=features;
						}
						else //过期
						{
							resultStr = ResourceUtil.srcStr(StringKeysLbl.TIP_RMI_ROOTKEY);
							serviceStartPanel.buttonResult(true, false, resultStr);
							return;
						}
						
					}

		} catch (IOException e) {
			DialogBoxUtil.errorDialog(null ,ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_IMPORT_FAIL));
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

			
			try {
				//执行启动mysql命令
				String command = "net start MySQL5";
				Runtime.getRuntime().exec(command);
				Thread.sleep(1000);
				// 循环获取mysql连接，如果没获取到，暂停一秒后重新获取 直到获取到。
				while (flag) {
					try {
						// 初始化数据库连接 默认连接本机
						Mybatis_DBManager.init(ServerConstant.localhostIp);
						flag = false;
						break;
					} catch (Exception e) {
						e.printStackTrace();
						Thread.sleep(1000);
					}
				}

			} catch (IOException e) {
				resultStr =  ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RUNMSQL);
				serviceStartPanel.buttonResult(true, false, resultStr);
				return;
			} catch (InterruptedException e) {
				resultStr =  ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RUNMSQL);
				serviceStartPanel.buttonResult(true, false, resultStr);
				return;
			}

			try {
				ServerConstant.registry = LocateRegistry.createRegistry(ConstantUtil.RMI_PORT);
			} catch (RemoteException e) {
				resultStr = ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RUNRMI);
				serviceStartPanel.buttonResult(true, false, resultStr);
				return;
			}
			try {
				new ServiceInitUtil(ServerConstant.registry);
			} catch (Exception e) {
				resultStr =ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_RUNUDP);
				serviceStartPanel.buttonResult(true, false, resultStr);
				return;
			}
			try {
				Broker.init("applicationContext-jms-broker.xml");
				ApplicationBeanFactory.initBeanFactory("applicationContext-jms-send.xml","targetConnectionFactory");
				ApplicationBeanFactory.initBeanFactory("applicationContext-jms-serviceCourse.xml","targetConnectionFactory");
				ExceptionManage.infor("JMS初始化成功", this.getClass());
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
			
			
			
			
			//加载设备类型到内存中。
			equimentDataUtil.loadEquipmentType();
			
//			try {
//				//启动corba服务
//				CorbaBooster.getInstanse().init();
//			} catch (Exception e) {
//				//不处理异常
//			}
			
			//等于1说明是开启snmp服务
			if(CodeConfigItem.getInstance().getSnmpStartOrClose() == 1){
				AgentServer agentServer = new AgentServer();
				agentServer.init(new String[]{});
			}

			serviceStartPanel.buttonResult(true,true,resultStr);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	
	/**
	 * 用于判断是否是在用户管理权限下执行的程序
	 * @param oldPath
	 * @param newPath
	 */
	private void isRootManager(String oldPath) throws IOException {
		InputStream inStream  = null;
		try {
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				 inStream = new FileInputStream(oldPath); // 读入原文件
			}
		} catch (IOException e) {
			ExceptionManage.dispose(e, getClass());
			throw e;
		}finally{
			if(inStream != null){
				try {
					inStream.close();
				} catch (Exception e2) {
					ExceptionManage.dispose(e2, getClass());
				}finally{
					inStream = null;
				}
			}
		}
	}
}
