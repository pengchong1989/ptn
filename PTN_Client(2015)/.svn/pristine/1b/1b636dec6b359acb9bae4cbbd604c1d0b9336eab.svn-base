package com.nms.ui.topology.action;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.topology.NetworkElementPanel;

public class RefreshAction implements Action{

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		
	}

	public Object getValue(String key) {
		return null;
	}

	public boolean isEnabled() {
		return false;
	}

	public void putValue(String key, Object value) {
		
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		
	}

	public void setEnabled(boolean b) {
		
	}

	public void actionPerformed(ActionEvent e) {
		try {
			NetworkElementPanel.getNetworkElementPanel().showTopo(true);
		} catch (Exception e1) {
			ExceptionManage.dispose(e1,this.getClass());
		}
	}

}
