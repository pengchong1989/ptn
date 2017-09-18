package com.nms.ui.ptn.systemconfig.dialog.qos.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.ptn.qos.QosTemplateInfo;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.ptn.systemconfig.QosTemplatePanel;
import com.nms.ui.ptn.systemconfig.dialog.qos.action.QosTemplateAction;

public class QosManagerController {

	private  QosTemplateAction qosTemplateAction = new QosTemplateAction();

	private QosTemplatePanel qosTemplatePanel = null;

	public QosManagerController(QosTemplatePanel panel) {
		this.qosTemplatePanel = panel;
		addActionListener();
		initData();
	}

	public void addActionListener() {
		this.qosTemplatePanel.getAddButton().addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						qosTemplateAction.openAddQosTemplate(qosTemplatePanel);
						initData();
					}

				});
		this.qosTemplatePanel.getDeleteButton().addActionListener(
				new MyActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						try {
							qosTemplateAction.deleteTemplate(qosTemplatePanel);
							initData();
						} catch (Exception e) {
							ExceptionManage.dispose(e,this.getClass());
						}
					}

					@Override
					public boolean checking() {
						
						return true;
					}

				
				});
		this.qosTemplatePanel.getModifyButton().addActionListener(
				new MyActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						qosTemplateAction
								.openUpdateQosTemplate(qosTemplatePanel);
						initData();
					}

					@Override
					public boolean checking() {
						
						return true;
					}
				});
		this.qosTemplatePanel.getRefreshButton().addActionListener(
				new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						initData();
					}

				});
		this.qosTemplatePanel.getQosResultTable().addMouseListener(
				new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent evt) {
						if (evt.getClickCount() == 1) {
							try {
								qosTemplateAction
										.showDetailQosInfo(qosTemplatePanel);
							} catch (Exception e) {
								ExceptionManage.dispose(e,this.getClass());
							}
						} else if (evt.getClickCount() == 2) {
							qosTemplateAction
									.openUpdateQosTemplate(qosTemplatePanel);
							initData();
						}
					}
				});
	}

	/*
	 * 加载数据到table中
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initData() {
		this.qosTemplatePanel.getQosResultTableModel().setRowCount(0);
		Map<String, List> qosTemplateMap = qosTemplateAction.initData();
		String qosType = null;
		if (qosTemplateMap != null) {
			int i = 0;
			for (String name : qosTemplateMap.keySet()) {
				List<QosTemplateInfo> infos = qosTemplateMap.get(name);
				qosType = infos.get(0).getQosType();
				Object[] data = new Object[] { ++i, name, qosType };
				this.qosTemplatePanel.getQosResultTableModel().addRow(data);
			}
		}
		this.qosTemplatePanel.getDetailTableModel().getDataVector().clear();
		this.qosTemplatePanel.getDetailTableModel().fireTableDataChanged();
	}

	public QosTemplatePanel getQosTemplatePanel() {
		return qosTemplatePanel;
	}

	public void setQosTemplatePanel(QosTemplatePanel qosTemplatePanel) {
		this.qosTemplatePanel = qosTemplatePanel;
	}

}
