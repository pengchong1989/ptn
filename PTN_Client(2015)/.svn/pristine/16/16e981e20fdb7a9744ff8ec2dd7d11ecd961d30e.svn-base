package com.nms.ui.ptn.systemManage.monitor.view;

import java.io.InputStream;
import java.util.Properties;

import javax.swing.JScrollPane;

import twaver.Node;
import twaver.TDataBox;
import twaver.tree.TTree;

import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;


public class SystemConfigLeftPane extends JScrollPane {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 511589504969568226L;
	private TDataBox box;
	private TTree tree;
	
	
	public SystemConfigLeftPane(){
		init();
	}

	private void init() {
		box = new TDataBox(ResourceUtil.srcStr(StringKeysTip.EQNAME));
		createDate(box);
		tree = new TTree(box);
		tree.expandAll();
		setViewportView(tree);
	}

	//创建一个左面板的图
	private void createDate(TDataBox box2) {
		try {
			Properties props = new Properties();
			InputStream propsIs = SystemConfigLeftPane.class.getClassLoader().getResourceAsStream("config/config.properties");
			props.load(propsIs);
			String ipAndPortNumber = props.getProperty("jdbc.url").replace("{?}", ConstantUtil.serviceIp);
			Node nodePar = new Node();
			nodePar.setName(ResourceUtil.srcStr(StringKeysTip.DATABSENAME));
			nodePar.setIcon("/com/nms/ui/images/tree/tree_service.png");
			Node node = new Node();
			node.setIcon("/com/nms/ui/images/tree/tree_service.png");
			node.setName("SQLService@"+ipAndPortNumber.substring(ipAndPortNumber.indexOf("//")+2, ipAndPortNumber.lastIndexOf("/")));
			node.setParent(nodePar);
			box2.addElement(nodePar);
			box2.addElement(node);
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
	}

	public TDataBox getBox() {
		return box;
	}

	public void setBox(TDataBox box) {
		this.box = box;
	}

	public TTree getTree() {
		return tree;
	}

	public void setTree(TTree tree) {
		this.tree = tree;
	}
}
