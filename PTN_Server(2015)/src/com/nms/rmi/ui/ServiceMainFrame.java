package com.nms.rmi.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultTreeModel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.nms.model.util.CodeConfigItem;
import com.nms.rmi.thread.StopThread;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.IconNode;
import com.nms.ui.manager.IconNodeRenderer;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * 服务端主创建
 * 
 * @author kk
 * 
 */
public class ServiceMainFrame extends JFrame {

	// 本窗口启动时的宽度和宽度
	private final int WINDOW_WIDTH = 650;
	private final int WINDOW_HEIGHT = 400;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 构造函数
	 */
	public ServiceMainFrame() {
		try {
			serviceMainFrame = this;
			CodeConfigItem	codeConfigItem = CodeConfigItem.getInstance();
			UiUtil.setLogo(this);
			if(codeConfigItem.getLanguage() == 1){
				ResourceUtil.language = "zh_CN";
			}else if(codeConfigItem.getLanguage() == 2){
				ResourceUtil.language = "en_US";
			}
			this.setTitle("PTN-Server");
			this.initComponent();
			this.setLayout();
			this.initTreeData();
			this.addListener();
			this.showWindow();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 添加监听
	 */
	private void addListener() {

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				try {
					setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
					int result = DialogBoxUtil.confirmDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_AFFIRM_EXIT));
					if (result == 0 && ConstantUtil.hasStop == false) {
						new Thread(new StopThread(null)).start();
					}else if(result == 0 && ConstantUtil.hasStop == true){
						System.exit(0);
					}
				} catch (Exception e2) {
					ExceptionManage.dispose(e2,this.getClass());
				}

			}
		});

		this.tree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
			public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
				treeValueChanged(evt);
			}
		});
	}
 

	/**
	 * tree的选中事件
	 */
	private void treeValueChanged(TreeSelectionEvent evt) {
		Class cls = null;
		JPanel jPanel = null;
		try {
			ControlKeyValue controlKeyValue = ((IconNode) evt.getPath().getLastPathComponent()).getControlKeyValue();
			if (null != controlKeyValue.getObject() && !"".equals(controlKeyValue.getObject())) {

				if ("com.nms.rmi.ui.ServiceStartPanel".equals(controlKeyValue.getObject())) {
					
					jPanel = ServiceStartPanel.getServiceStartPanel();					
				} else {
					cls = Class.forName(controlKeyValue.getObject().toString());
					jPanel = (JPanel) cls.newInstance();
				}

				this.splitPane.setRightComponent(jPanel);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			cls = null;
			jPanel = null;
		}
	}

	/**
	 * 控件初始化
	 */
	private void initComponent() {

		this.splitPane = new JSplitPane();
		this.scrollPane_left = new JScrollPane();
		this.tree = new JTree();
		this.scrollPane_left.setViewportView(this.tree);
		this.splitPane.setLeftComponent(this.scrollPane_left);

		this.splitPane.setRightComponent(ServiceStartPanel.getServiceStartPanel());

		this.splitPane.setDividerLocation(this.WINDOW_WIDTH / 4);
		this.tree.setMinimumSize(new Dimension(this.WINDOW_WIDTH / 4, this.WINDOW_HEIGHT));
		this.scrollPane_left.setMinimumSize(new Dimension(this.WINDOW_WIDTH / 4, this.WINDOW_HEIGHT));
	}

	/**
	 * 布局
	 */
	private void setLayout() {

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { this.WINDOW_WIDTH };
		componentLayout.columnWeights = new double[] { 0.5,1 };
		componentLayout.rowHeights = new int[] { this.WINDOW_HEIGHT };
		componentLayout.rowWeights = new double[] { 0.5 ,1};
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		componentLayout.setConstraints(this.splitPane, c);
		this.add(this.splitPane);
	}

	/**
	 * 显示窗口
	 */
	private void showWindow() {
		Dimension dimension = new Dimension(this.WINDOW_WIDTH, this.WINDOW_HEIGHT);
		this.setPreferredSize(dimension);
		this.setMinimumSize(dimension);
		this.setLocation(UiUtil.getWindowWidth(this.getWidth()), UiUtil.getWindowHeight(this.getHeight()));
		this.setVisible(true);
	}

	/**
	 * 初始化tree数据 读xml
	 * 
	 * @throws Exception
	 */
	private void initTreeData() throws Exception {

		IconNode rootNode = null;
		IconNode parentNode = null;
		IconNode childNode = null;
		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document doc = null;
		Element root = null;
		NodeList nodeList = null;
		Element parent = null;
		NodeList childList = null;
		Element child = null;
		// List<UdaGroup> udaGroupList=null;
		String clickClass = null;
		String clickClagg_parent = null;
		try {

			rootNode = new IconNode(null, ResourceUtil.srcStr(StringKeysObj.TREE_FUNCTION), new ControlKeyValue("", ResourceUtil.srcStr(StringKeysObj.TREE_FUNCTION), null));

			// 获得一个新的DocumentBuilderFactory实例
			factory = DocumentBuilderFactory.newInstance();
			// 使用DocumentBuilderFactory构建DocumentBulider
			builder = factory.newDocumentBuilder();
			
			// 根据不同语言，区分不同路径
			String path = "config/service_tree_node.xml";
			if ("en_US".equals(ResourceUtil.language)) {
				path = "config/service_tree_node_en.xml";
			}
			// 使用DocumentBuilder的parse()方法解析文件
			doc = builder.parse(this.getClass().getClassLoader().getResource(path).toString());
			root = doc.getDocumentElement();
			nodeList = root.getElementsByTagName("node");

			for (int i = 0; i < nodeList.getLength(); i++) {
				parent = (Element) nodeList.item(i);
				// 不同厂商设备，调用不同的列表类
				clickClagg_parent = parent.getAttribute("clickNodeClass");

				parentNode = new IconNode(null, parent.getAttribute("nodename"), new ControlKeyValue("", parent.getAttribute("nodename"), clickClagg_parent));

				childList = parent.getElementsByTagName("childNode");
				if (childList.getLength() > 0) {
					for (int j = 0; j < childList.getLength(); j++) {
						child = (Element) childList.item(j);
						clickClass = child.getAttribute("clickNodeClass");
						childNode = new IconNode(null, child.getAttribute("nodename"), new ControlKeyValue("", child.getAttribute("nodename"), clickClass));
						parentNode.add(childNode);
					}
				}
				rootNode.add(parentNode);

			}
			this.tree.setModel(new DefaultTreeModel(rootNode));
			this.tree.setCellRenderer(new IconNodeRenderer());
			this.tree.expandRow(2);
		} catch (Exception e) {
			throw e;
		} finally {
			rootNode = null;
			parentNode = null;
			childNode = null;
			factory = null;
			builder = null;
			doc = null;
			root = null;
			nodeList = null;
			parent = null;
			childList = null;
			child = null;
		}

	}

	public static ServiceMainFrame getServiceMainFrame() {
		
		if (null == serviceMainFrame) {
			synchronized (ServiceMainFrame.class) {
				if (serviceMainFrame == null) {
					serviceMainFrame = new ServiceMainFrame();
				}
			}
		}
		return serviceMainFrame;
	}

	public Map<String, Thread> getThreadMap() {
		return threadMap;
	}

	public void setThreadMap(Map<String, Thread> threadMap) {
		this.threadMap = threadMap;
	}

	public Map<String, Runnable> getThreadRunableMap() {
		return threadRunableMap;
	}

	public void setThreadRunableMap(Map<String, Runnable> threadRunableMap) {
		this.threadRunableMap = threadRunableMap;
	}
	
	
	private JSplitPane splitPane = null;; // 主panel 带左右分隔的
	private JScrollPane scrollPane_left = null; // 左边panel tree
	private JTree tree = null; // 左边树形菜单
	private JComboBox jComboBox;
	public Map<String,Thread> threadMap = new HashMap<String, Thread>();
	public Map<String,Runnable> threadRunableMap = new HashMap<String, Runnable>();
	public static ServiceMainFrame serviceMainFrame;
}
