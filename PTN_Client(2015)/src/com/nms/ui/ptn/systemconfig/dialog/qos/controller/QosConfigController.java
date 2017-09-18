package com.nms.ui.ptn.systemconfig.dialog.qos.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.nms.db.enums.EPwType;
import com.nms.db.enums.EServiceType;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.ptn.systemconfig.dialog.qos.action.QosConfigAction;
import com.nms.ui.ptn.systemconfig.dialog.qos.dialog.QosCommonConfig;

public class QosConfigController {

	public QosConfigAction qosConfigAction = new QosConfigAction();

	private  String objType;

	private  Object obj;

	private  EPwType choosePwType;

	private  int qosHasConfiged;
	private boolean isNetwork = true;

	public  Object getObj() {
		return obj;
	}

	public  void setObj(Object obj) {
		this.obj = obj;
	}

	/**
	 * 
	 * @param qosConfigDialog
	 * @param qosConfigController
	 * @param isNetwork
	 * @param panelOrDialog 
	 * 		新建或者修改时 操作qos 传入 新建或者修改的对话框对象，右键修改qos 传入 主界面panel对象，做刷新只用
	 */
	public QosConfigController(QosCommonConfig qosConfigDialog, QosConfigController qosConfigController, boolean isNetwork,Object panelOrDialog) {
		this.setNetwork(isNetwork);
		this.objType=qosConfigController.getObjType();
		this.obj=qosConfigController.getObj();
		this.choosePwType=qosConfigController.getChoosePwType();
		if (objType.equals(EServiceType.SECTION.toString())) {
			qosConfigAction.setSectionTableDatas(qosConfigDialog);
		} else {
			if (objType.equals(EServiceType.PW.toString())) {
				// if (this.isNetwork()) {
				// setChoosePwType(AddPDialog.getDialog().getChoosePwType());
				// } else {
				// setChoosePwType(PwAddDialog.getPwAddDialog().getChoosePwType());
				// }
				if (getChoosePwType() != EPwType.ETH) {
					qosConfigDialog.getQosTypeComboBox().setEnabled(false);
				}
			}
			qosConfigAction.setTableDatas(qosConfigDialog,this);
			qosConfigAction.initNameList(qosConfigDialog);
		}
		addListeners(qosConfigDialog,this,panelOrDialog);
	}

	public QosConfigController() {
	}

	/**
	 * 添加事件
	 * @param qosConfigDialog
	 * @param qosController
	 * @param panelOrDialog
	 * 	新建或者修改时 操作qos 传入 新建或者修改的对话框对象，右键修改qos 传入 主界面panel对象，做刷新只用
	 */
	private void addListeners(final QosCommonConfig qosConfigDialog,final QosConfigController qosController,final Object panelOrDialog) {
		qosConfigDialog.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				qosConfigDialog.dispose();
			}
		});
		qosConfigDialog.getNameList().addItemListener(new java.awt.event.ItemListener() {
			@Override
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				if (evt.getStateChange() == 1) {
					qosConfigAction.freshQosTable(qosConfigDialog, (ControlKeyValue) qosConfigDialog.getNameList().getSelectedItem(),QosConfigController.this);
				}
			}
		});
		qosConfigDialog.getQosTypeComboBox().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				qosConfigAction.setTableDatas(qosConfigDialog,qosController);
				qosConfigAction.qosIsELSP(qosConfigDialog,qosController);
			}
		});
		qosConfigDialog.getSaveButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					qosConfigAction.addQosInfo(qosConfigDialog, getObjType(), obj, isNetwork(),panelOrDialog);
					qosConfigDialog.dispose();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}finally{
					
				}
			}

		});
		qosConfigDialog.getCancelButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				qosConfigDialog.dispose();
			}

		});
		qosConfigDialog.getQosTable().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				qosConfigAction.keepCosConsistent(qosConfigDialog,QosConfigController.this);
			}
		});
		qosConfigDialog.getTableScrollPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent et) {
				if (qosConfigDialog.getQosTable().getEditorComponent() == null) {
					return;
				}
				if (qosConfigDialog.getQosTable().getSelectedColumn() > 1) {
					qosConfigAction.commitTable(qosConfigDialog.getQosTable());
				}
				if (qosConfigDialog.getQosTable().getSelectedColumn() == 3 ||qosConfigDialog.getQosTable().getSelectedColumn() == 5) {
					qosConfigAction.setDataIsConsistent(qosConfigDialog);
				}
			}
		});
		qosConfigDialog.getQosTable().addMouseListener(new MouseAdapter() {
			/*
			 * @Override public void mouseExited(MouseEvent arg0) { qosConfigAction .keepCosConsistent(QosCommonConfig.qosConfigDialog); }
			 * 
			 * @Override public void mouseReleased(MouseEvent et) { qosConfigAction .keepCosConsistent(QosCommonConfig.qosConfigDialog); }
			 */
			@Override
			public void mouseEntered(MouseEvent et) {
				if (qosConfigDialog.getQosTable().getEditorComponent() == null) {
					return;
				}
				if (qosConfigDialog.getQosTable().getSelectedColumn() > 1) {
					qosConfigAction.commitTable(qosConfigDialog.getQosTable());
				}
				if (qosConfigDialog.getQosTable().getSelectedColumn() == 3 || qosConfigDialog.getQosTable().getSelectedColumn() == 5) {
					qosConfigAction.setDataIsConsistent(qosConfigDialog);
				}
			}
		});
		qosConfigDialog.getSectionAQosQueueComboBox().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				qosConfigAction.qosIsSP(qosConfigDialog, evt);
			}
		});
		qosConfigDialog.getSectionZQosQueueComboBox().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				qosConfigAction.qosIsSP(qosConfigDialog, evt);
			}
		});
		qosConfigDialog.getSectionAQosTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent et) {
				if (qosConfigDialog.getSectionAQosTable().getEditorComponent() == null) {
					return;
				}
				if (qosConfigDialog.getSectionAQosTable().getSelectedColumn() > 1) {
					qosConfigAction.commitSectionTable(qosConfigDialog.getSectionAQosTable(),"a");
				}
			}
		});
		qosConfigDialog.getSectionZQosTable().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent et) {
				if (qosConfigDialog.getSectionZQosTable().getEditorComponent() == null) {
					return;
				}
				if (qosConfigDialog.getSectionZQosTable().getSelectedColumn() > 1) {
					qosConfigAction.commitSectionTable(qosConfigDialog.getSectionZQosTable(),"z");
				}
			}
		});
	}

	/*
	 * 打开qos配置界面
	 */
	public void openQosConfig(QosConfigController controller, String objType, Object obj, EPwType ePwType,Object panelOrDialog) {
		setObjType(objType);
		setObj(obj);
		try {
			if (null != ePwType) {
				setChoosePwType(ePwType);
			}
			qosConfigAction.openQosConfigAction(controller,panelOrDialog);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public  String getObjType() {
		return objType;
	}

	public  void setObjType(String objType) {
		this.objType = objType;
	}

	public  int getQosHasConfiged() {
		return qosHasConfiged;
	}

	public  void setQosHasConfiged(int qosHasConfiged) {
		this.qosHasConfiged = qosHasConfiged;
	}

	public  EPwType getChoosePwType() {
		return choosePwType;
	}

	public  void setChoosePwType(EPwType choosePwType) {
		this.choosePwType = choosePwType;
	}

	public boolean isNetwork() {
		return isNetwork;
	}

	public void setNetwork(boolean isNetwork) {
		this.isNetwork = isNetwork;
	}

}
