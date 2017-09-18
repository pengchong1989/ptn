package com.nms.ui.topology.action;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import twaver.Element;
import twaver.MovableFilter;
import twaver.network.TNetwork;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.topology.NetworkElementPanel;

/**
 * 锁定拓扑按钮
 * 
 * @author kk
 * 
 */
public class LockTopoAction implements Action {

	/**
	 * 锁定拓扑事件
	 */
	private MovableFilter movableFilter = new MovableFilter() {
		@Override
		public boolean isMovable(Element element) {
			return false;
		}
	};
	
	private final String PNG_PATH="/com/nms/ui/images/topo/";
	private final String LOCK_PNG_NAME="lock.png";
	private final String CLEAR_LOCK_PNG_NAME="lock_open.png";

	@Override
	public void actionPerformed(ActionEvent actionEvent) {

		// 获取主拓扑的network对象
		TNetwork tNetwork = NetworkElementPanel.getNetworkElementPanel().getNetwork();
		// 获取network中所有的移动事件
		@SuppressWarnings("unchecked")
		List<MovableFilter> movableFilterList = tNetwork.getMovableFilters();
		// 是否存在锁定拓扑事件。 true为存在 反之false
		boolean flag = false;
		// 锁定/解锁按钮对象
		JButton btn = null;
		//按钮图标路径
		String png=null;
		//按钮图标对象
		Icon menuIconPlace = null;
		//按钮提示语
		String tipText=null;
		try {
			// 遍历主拓扑工具按钮栏中所有按钮
			for (Component component : tNetwork.getToolbar().getComponents()) {
				// 找出锁定/解锁按钮对象
				if (component instanceof JButton) {
					btn = (JButton) component;
					// 如果按钮的动作等于this 说明是锁定/解锁按钮
					if (btn.getAction() == this) {
						break;
					}
				}
			}

			// 找出拓扑中是否存在锁定拓扑事件
			for (int i = 0; i < movableFilterList.size(); i++) {
				if (movableFilterList.get(i) == this.movableFilter) {
					flag = true;
					break;
				}
			}
			// 如果存在，说明是解锁操作。把事件移除，并且把按钮改为锁定状态
			if (flag) {
				tNetwork.removeMovableFilter(this.movableFilter);
				png=PNG_PATH+LOCK_PNG_NAME;
				tipText=ResourceUtil.srcStr(StringKeysBtn.LOCK_UPDATE);
			} else {
				// 如果不存在，说明是锁定操作，把锁定事件添加到拓扑中，并且把按钮改为解锁状态
				tNetwork.addMovableFilter(this.movableFilter);
				png=PNG_PATH+CLEAR_LOCK_PNG_NAME;
				tipText=ResourceUtil.srcStr(StringKeysBtn.CLEAR_UPDATE);
			}
			
			//设置按钮的提示语和图标
			menuIconPlace = new ImageIcon(this.getClass().getResource(png));
			btn.setIcon(menuIconPlace);
			btn.setToolTipText(tipText);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			tNetwork = null;
			movableFilterList = null;
			btn = null;
			png=null;
			tipText=null;
			menuIconPlace = null;
		}
	}

	@Override
	public Object getValue(String key) {
		return null;
	}

	@Override
	public void putValue(String key, Object value) {

	}

	@Override
	public void setEnabled(boolean b) {

	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {

	}

	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {

	}

}
