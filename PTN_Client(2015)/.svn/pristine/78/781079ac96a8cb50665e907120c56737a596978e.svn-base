/*
 * NeConfigPanel.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.systemManage.monitor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import twaver.Node;
import twaver.TDataBox;
import twaver.tree.ElementNode;
import twaver.tree.TTree;

import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.systemManage.monitor.view.DataBaseRightPanel;

/**
 *
 * 
 * @author zr
 */
public class SystemMontorConfigPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private DataBaseRightPanel rightPanel;
    private JSplitPane jspiltPanel;
    private JScrollPane jscrollPane;
    private TDataBox box;
	private TTree tree;
	
	public SystemMontorConfigPanel() {
		init();
	}

	private void init() {
		this.initComponents();
		addListener();
	}
	private void initComponents() {
		jspiltPanel = new JSplitPane();
		jscrollPane = new JScrollPane();
		box = new TDataBox(ResourceUtil.srcStr(StringKeysTip.EQNAME));
		createDate(box);
		tree = new TTree(box);
		tree.expandAll();
		jscrollPane.setViewportView(tree);
		jspiltPanel.setLeftComponent(jscrollPane);
		rightPanel = new DataBaseRightPanel(1);
		jspiltPanel.setRightComponent(rightPanel);
		setViewLayout();
	}

	private void setViewLayout() {
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jspiltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jspiltPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE));
	}

	
	//创建一个左面板的图
	private void createDate(TDataBox box2) {
		try {
			Properties props = new Properties();
			InputStream propsIs = SystemMontorConfigPanel.class.getClassLoader().getResourceAsStream("config/config.properties");
			props.load(propsIs);
			String ipAndPortNumber = props.getProperty("jdbc.url").replace("{?}", ConstantUtil.serviceIp);
			Node nodePar = new Node();
			nodePar.setName(ResourceUtil.srcStr(StringKeysTip.DATABSENAME));
			nodePar.setIcon("/com/nms/ui/images/tree/tree_service.png");
			Node node = new Node();
			node.setIcon("/com/nms/ui/images/tree/tree_service.png");
			node.setName("SQLService@"+ipAndPortNumber.substring(ipAndPortNumber.indexOf("//")+2, ipAndPortNumber.lastIndexOf("/")));
			node.setParent(nodePar);
			node.setSelected(true);
			box2.addElement(nodePar);
			box2.addElement(node);
			
			Node servicePath = new Node();
			servicePath.setName(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_USERSERVICE1));
			servicePath.setIcon("/com/nms/ui/images/tree/tree_service.png");
			Node serviceChild = new Node();
			serviceChild.setName(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_USERSERVICE)+"("+ ConstantUtil.serviceIp+")");
			serviceChild.setIcon("/com/nms/ui/images/tree/tree_service.png");
			serviceChild.setParent(servicePath);
			box2.addElement(serviceChild);
			box2.addElement(servicePath);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
	}
	
	
	/**
	 * 增加监听事件
	 */
	private void addListener() {
		tree.addTreeNodeClickedActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ElementNode node =  (ElementNode) e.getSource();
				if(node.getElement().getName().equals(ResourceUtil.srcStr(StringKeysTip.MOINTOR_LABEL_USERSERVICE)+"("+ ConstantUtil.serviceIp+")")){
					rightPanel = new DataBaseRightPanel(2);
					jspiltPanel.setRightComponent(rightPanel);
				}else if(node.getElement().getName().contains("SQLService@")){
					rightPanel = new DataBaseRightPanel(1);
					jspiltPanel.setRightComponent(rightPanel);
				}
			}
		});
	}
}