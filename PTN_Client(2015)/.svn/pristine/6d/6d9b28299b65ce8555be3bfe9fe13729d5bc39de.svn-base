package com.nms.ui.topology.routebusiness.view;

import java.awt.Font;

import javax.swing.JTextArea;

import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * ping功能
 * @author guoqc
 */
public class PingCmdDialog extends PtnDialog {
	private static final long serialVersionUID = -5099293329201732528L;
	private JTextArea textArea = null;
	private String ip;
	public PingCmdDialog(String ip) {
		try {
			this.ip = ip;
			this.initComponent();
			this.execCommand();
			UiUtil.showWindow(this, 600, 400);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 初始化控件
	 */
	private void initComponent() {
		this.setTitle(ResourceUtil.srcStr(StringKeysMenu.MENU_PING));
		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
		this.textArea.setFont(new Font("宋体", Font.PLAIN, 16));
		this.add(this.textArea);
	}
	
    private void execCommand() {  
    	try {  
    		new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						String result = "";
						try {
							DispatchUtil dispatch = new DispatchUtil(RmiKeys.RMI_SITE);
							result = (String) dispatch.pingCMD(ip);
						} catch (Exception e) {
							ExceptionManage.dispose(e, this.getClass());
						}
						if(!"".equals(result)){
							String[] resultArr = result.split("/");
							for (String s : resultArr) {
								textArea.append(s+"\r\n"+"\r\n");
								Thread.sleep(700);
							}
						}else{
							textArea.append(ResourceUtil.srcStr(StringKeysTip.TIP_SERVER_CONNECTION_EXCEPTION));
						}
					} catch (InterruptedException e) {
						ExceptionManage.dispose(e, this.getClass());
					}  
				}
			}).start();
        } catch (Exception e) {  
        	ExceptionManage.dispose(e, this.getClass());  
        } 
    }  
}
