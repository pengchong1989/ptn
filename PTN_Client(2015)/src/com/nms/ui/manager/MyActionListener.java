package com.nms.ui.manager;

import java.awt.event.ActionListener;

/**
 * 但验证的监听
 * @author kk
 *
 */
public interface MyActionListener extends ActionListener{
	
	/**
	 * 点击前的验证，所有的验证都加到此方法中
	 * @return true 验证成功  false 验证失败
	 */
	public boolean checking();
	
}
