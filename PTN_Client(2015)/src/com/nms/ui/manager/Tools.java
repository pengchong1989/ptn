package com.nms.ui.manager;

import javax.swing.JComboBox;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.PortLagInfo;
/**
 * 工具类
 * @author dzy
 *
 */
public class Tools {
	
	/**
	 * 通过ac初始化下拉列表选中
	 * @param jComboBox		下拉列表对象
	 * @param ac	ac对象
	 */
	public  void initPortAndLagByAcForCMB(JComboBox jComboBox, AcPortInfo ac) {
		for (int i = 0; i < jComboBox.getItemCount(); i++) {
			if(0 != ac.getPortId()){
				if (((ControlKeyValue) jComboBox.getItemAt(i)).getId().equals(ac.getPortId()+"")&&
						((ControlKeyValue) jComboBox.getItemAt(i)).getObject() instanceof PortInst) {
					jComboBox.setSelectedIndex(i);
					return;
				}
			}
			if(0 != ac.getLagId()){
				if (((ControlKeyValue) jComboBox.getItemAt(i)).getId().equals(ac.getLagId()+"")&&
						((ControlKeyValue) jComboBox.getItemAt(i)).getObject() instanceof PortLagInfo) {
					jComboBox.setSelectedIndex(i);
					return;
				}
			}
		}
	}
}
