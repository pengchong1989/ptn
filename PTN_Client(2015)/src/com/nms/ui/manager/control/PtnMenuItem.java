package com.nms.ui.manager.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.nms.db.bean.system.OperationLog;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.wait.WaitSwingworker;
import com.nms.ui.ptn.safety.roleManage.RoleRoot;

public class PtnMenuItem extends JMenuItem {
	private static final long serialVersionUID = 6444019001937812854L;
	/**
	 * 权限标签    new   PtnButton是  比较，查找 此角色权限存到（内存中  ，常量）；
	 */
	private int rootLabel;
	/**
	 * 是否显示等待对话框。默认为true
	 */
	private boolean isWait = true;
	OperationLog operationLog = null;
	private JPanel panel;

	/**
	 * 记录日志时，操作的key。如果为0时。不记录日志
	 */
	private int operateKey = 0;
	/**
	 * 操作日志结果     int result= 1 成功 ，2 失败
	 * @param text
	 */
	private int result=0;
	/**
	 * 创建一个新的实例
	 * 
	 * @param text
	 *            显示文本
	 */
	public PtnMenuItem(String text) {
		super(text);
	}

	/**
	 * 创建一个新的实例
	 * 
	 * @param text
	 *            显示文本
	 * @param isWait
	 *            是否等待
	 * @param  int rootLabel  
	 * 需要验证  权限的                >0的 整数  
	 *            传人 权限监控标签
	 */
	public PtnMenuItem(String text, boolean isWait) {
		super(text);
		this.setWait(isWait);
	}
	
	/**
	 * 创建一个新的实例
	 * 
	 * @param text
	 *            显示文本
	 * @param isWait
	 *            是否等待
	 * @param  int rootLabel  
	 * 需要验证  权限的                >0的 整数  
	 *            传人 权限监控标签
	 */
	public PtnMenuItem(String text, boolean isWait,int label) {
		super(text);
		this.setWait(isWait);
		this.rootLabel=label;
		this.setEnabled(this.checkRoot(label));
	}
	
	/**
	 * 设置是否显示等待对话框
	 * 
	 * @param isWait
	 *            true=显示 false=不显示
	 */
	public void setWait(boolean isWait) {
		this.isWait = isWait;
	}

	/** -sy
	 * 权限验证  : 比对 传人参数 （权限标签）是否  存在于      登陆用户的权限之中 
	 * @param label		权限标签 		
	 */
	public boolean checkRoot(int label){
		RoleRoot roleRoot = new RoleRoot();
		return roleRoot.root(label);
	}
	
	/**
	 * 给按钮添加事件
	 */
	public void addActionListener(final MyActionListener actionListener) {
		final PtnMenuItem p = this;
		super.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				WaitSwingworker waitSwingworker = null;
				try {
					if (isWait){		
						waitSwingworker = new WaitSwingworker(actionListener, p, panel);		
						waitSwingworker.execute();
						}
					else {
						actionListener.actionPerformed(actionEvent);
					}
					
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				} finally {
					waitSwingworker = null;
				}
			}
		});
	}
}
