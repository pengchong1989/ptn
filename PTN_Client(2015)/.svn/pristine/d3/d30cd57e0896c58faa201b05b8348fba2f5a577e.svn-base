package com.nms.ui.manager.wait;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnMenuItem;

/**
 * 等待进度条的swingworker
 * @author kk
 *
 */
public class WaitSwingworker extends SwingWorker<String, Integer> {

	/**
	 * 按钮点击事件
	 */
	private MyActionListener actionListener = null;	
	private PtnButton ptnButton = null;
	private JDialog dialog;
	private PtnMenuItem ptnMenuItem = null;
	private JPanel panel;

	/**
	 * 创建一个新的实例。
	 * 
	 * @param actionListener 按钮事件
	 */
	public WaitSwingworker(MyActionListener actionListener) {
		
		this.actionListener = actionListener;
	}
	
	public WaitSwingworker(MyActionListener actionListener,PtnButton ptnButton,JDialog dialog) {
		this.actionListener = actionListener;
		this.ptnButton = ptnButton;
		this.dialog = dialog;
		
	}
	
	public WaitSwingworker(MyActionListener actionListener, PtnMenuItem ptnMenuItem, JPanel panel) {
		this.actionListener = actionListener;
		this.ptnMenuItem = ptnMenuItem;
		this.panel = panel;
		
	}

	/**
	 * 执行方法。执行按钮的事件即可
	 */
	@Override
	protected String doInBackground() throws Exception {
		if(this.actionListener.checking()){
			//打开等待进度条
			boolean lable = true;
			try {
				if(this.ptnButton != null){
					if(this.dialog == null){
						ConstantUtil.waitWindowThread.setResume();
					}else{
						ConstantUtil.waitWindowThread.setResume(dialog);
					}
				}
				if(this.ptnMenuItem != null){
					if(this.panel == null){
						ConstantUtil.waitWindowThread.setResume();
					}else{
						ConstantUtil.waitWindowThread.setResume(panel);
					}
				}
				while(lable){
					if(ConstantUtil.waitWindowThread.getLable() == 1){
						lable = false;
					}
				}
				this.actionListener.actionPerformed(null);
			} catch (Exception e) {
				e.printStackTrace();
//				ExceptionManage.dispose(e, getClass());
			}finally{
				lable = true;
				while(lable){
					ConstantUtil.waitWindowThread.setSuspendFlag();
					if(ConstantUtil.waitWindowThread.getLable() == 0){
//					   System.out.println("进度条消失");
//					   ExceptionManage.infor("进度条消失", this.getClass());
					 lable = false;	
					}
				}
			}
		}
		return "";
	}

	/**
	 * 执行完后 把等待对话框关闭
	 */
	@Override
	protected void done() {	
//		ConstantUtil.waitWindowThread.setSuspendFlag();	
		super.done();
		/**
		 * 执行操作日志记录
		 */
//		ptnButton.insertOperationLog();	
	}
}
