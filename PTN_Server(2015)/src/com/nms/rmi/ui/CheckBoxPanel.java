package com.nms.rmi.ui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.nms.rmi.ui.util.ServerConstant;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;

/**
 * 初始化、备份、恢复数据库的复选框面板 通过isInit参数显示两个界面
 * 
 * @author kk
 * 
 */
public class CheckBoxPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Map<String, JCheckBox> mapChk = new HashMap<String, JCheckBox>(); // 复选框集合 key为文件名 value为复选框对象

	/**
	 * 创建一个新的实例
	 * 
	 * @param isInit
	 *            是否为初始化数据库界面调用
	 */
	public CheckBoxPanel(boolean isInit) {

		if (isInit) {
			this.initComponent();
			this.setLayout();
			this.addListener();
		} else {
			this.initComponentBackup();
			this.setLayoutBackup();
			this.addListenerBackup();
		}

	}
	public CheckBoxPanel(){
		
	}
	/**
	 * 备份或恢复界面 添加监听
	 */
	private void addListenerBackup() {
		this.chkAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 如果chkall选中，其他为不可用 chkall没选中 其他可用
				setChkEnabledBackup(!chkAll.isSelected());
			}
		});
	}

	/**
	 * 添加监听
	 */
	private void addListener() {
		this.chkAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 如果chkall选中，其他为不可用 chkall没选中 其他可用
				setChkEnabled(!chkAll.isSelected());
			}
		});

		this.chkSite.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 选中初始化网元时，自动选中初始化业务、告警、性能。因为这几项都与网元有关联。
				if (chkSite.isSelected()) {
					chkBusiness.setSelected(true);
					chkAlarm.setSelected(true);
					chkPerformance.setSelected(true);
				}
				chkBusiness.setEnabled(!chkSite.isSelected());
				chkAlarm.setEnabled(!chkBusiness.isSelected());
				chkPerformance.setEnabled(!chkBusiness.isSelected());
			}
		});

		this.chkBusiness.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 选中初始业务时，自动选中初始化告警、性能。因为这几项都与业务有关联。
				if (chkBusiness.isSelected()) {
					chkAlarm.setSelected(true);
					chkPerformance.setSelected(true);
				}
				chkAlarm.setEnabled(!chkBusiness.isSelected());
				chkPerformance.setEnabled(!chkBusiness.isSelected());
			}
		});

		this.chkSafety.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				// 选中初始用户时，自动选中初始化日志。因为日志表与用户有关联。
				if (chkSafety.isSelected()) {
					chkLog.setSelected(true);
				}
				chkLog.setEnabled(!chkSafety.isSelected());
			}
		});

	}

	/**
	 * 设置所有chk的可用性
	 * 
	 * @param isEnabled
	 */
	private void setChkEnabled(boolean isEnabled) {
		this.chkAlarm.setEnabled(isEnabled);
		this.chkSafety.setEnabled(isEnabled);
		this.chkLog.setEnabled(isEnabled);
		this.chkBusiness.setEnabled(isEnabled);
		this.chkPerformance.setEnabled(isEnabled);
		this.chkSite.setEnabled(isEnabled);

		// 设置选中状态
		this.chkAlarm.setSelected(!isEnabled);
		this.chkSafety.setSelected(!isEnabled);
		this.chkLog.setSelected(!isEnabled);
		this.chkBusiness.setSelected(!isEnabled);
		this.chkPerformance.setSelected(!isEnabled);
		this.chkSite.setSelected(!isEnabled);
	}

	/**
	 * 备份或恢复界面 设置所有chk的可用性
	 * 
	 * @param isEnabled
	 */
	private void setChkEnabledBackup(boolean isEnabled) {
		this.chkSafety.setEnabled(isEnabled);
		this.chkSite.setEnabled(isEnabled);

		// 设置选中状态
		this.chkSafety.setSelected(!isEnabled);
		this.chkSite.setSelected(!isEnabled);
	}

	/**
	 * 初始化控件
	 */
	private void initComponent() {

		this.chkAll = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_DBTA));
		this.chkAlarm = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_ALARM_DBTA));
		this.chkSafety = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_USER_DBTA));
		this.chkLog = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_LOG_DBTA));
		this.chkBusiness = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_CORE_DBTA));
		this.chkPerformance = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_PERFOR_DBTA));
		this.chkSite = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_SITE_DBTA));

		// 把所有复选框放入集合中初始化按钮用
		this.mapChk.put(ServerConstant.INIT_ALL, chkAll);
		this.mapChk.put(ServerConstant.INIT_ALARM, chkAlarm);
		this.mapChk.put(ServerConstant.INIT_SAFETY, chkSafety);
		this.mapChk.put(ServerConstant.INIT_LOG, chkLog);
		this.mapChk.put(ServerConstant.INIT_BUSINESS, chkBusiness);
		this.mapChk.put(ServerConstant.INIT_PERFORMANCE, chkPerformance);
		this.mapChk.put(ServerConstant.INIT_SITE, chkSite);
	}

	/**
	 * 备份或恢复界面初始化控件
	 */
	private void initComponentBackup() {

		this.chkAll = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_DBTA));
		this.chkSafety = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_USERANDLOG_DBTA));
		this.chkSite = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_RMI_SITEADNALL_DBTA));

		// 把所有复选框放入集合中初始化按钮用
		this.mapChk.put(ServerConstant.BACKUPS_ALL, chkAll);
		this.mapChk.put(ServerConstant.BACKUPS_USER, chkSafety);
		this.mapChk.put(ServerConstant.BACKUPS_SITE, chkSite);
	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 150, 150, 150 };
		componentLayout.columnWeights = new double[] { 0.1, 0.1, 0.1 };
		componentLayout.rowHeights = new int[] { 30, 50, 50, 50, 20 };
		componentLayout.rowWeights = new double[] { 0, 0, 0, 0, 0.1 };
		this.setLayout(componentLayout);

		// 初始化全部复选框
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 15, 0, 0);

		c.gridx = 0;
		c.gridy = 1;
		// c.weightx=3;
		componentLayout.setConstraints(this.chkAll, c);
		this.add(this.chkAll);

		// 初始化网元复选框
//		c.gridy = 2;
//		componentLayout.setConstraints(this.chkSite, c);
//		this.add(this.chkSite);

		// 初始化业务复选框
		c.gridx = 0;
		c.gridy = 2;
		componentLayout.setConstraints(this.chkBusiness, c);
		this.add(this.chkBusiness);

		// 初始化性能复选框
		c.gridx = 1;
		componentLayout.setConstraints(this.chkPerformance, c);
		this.add(this.chkPerformance);

		// 初始化告警复选框
		c.gridx = 2;
		componentLayout.setConstraints(this.chkAlarm, c);
		this.add(this.chkAlarm);

		// 初始化用户复选框
		c.gridx = 0;
		c.gridy = 3;
		componentLayout.setConstraints(this.chkSafety, c);
		this.add(this.chkSafety);

		// 初始化日志复选框
		c.gridx = 1;
		componentLayout.setConstraints(this.chkLog, c);
		this.add(this.chkLog);

	}

	/**
	 * 备份或恢复界面设置布局
	 */
	private void setLayoutBackup() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 150, 150, 150 };
		componentLayout.columnWeights = new double[] { 0.1, 0.1, 0.1 };
		componentLayout.rowHeights = new int[] { 30, 50, 50, 50, 20 };
		componentLayout.rowWeights = new double[] { 0, 0, 0, 0, 0.1 };
		this.setLayout(componentLayout);

		// 初始化全部复选框
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 15, 0, 0);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 3;
		componentLayout.setConstraints(this.chkAll, c);
		this.add(this.chkAll);

		// 初始化网元复选框
		c.gridy = 2;
		componentLayout.setConstraints(this.chkSite, c);
		this.add(this.chkSite);

		// 初始化告警复选框
		c.gridx = 0;
		c.gridy = 3;
		componentLayout.setConstraints(this.chkSafety, c);
		this.add(this.chkSafety);

	}

	/**
	 * 验证是否有选中的复选框
	 * 
	 * @return true 有选中 false 没有选中
	 * @throws Exception
	 */
	public boolean checkingSelect() throws Exception {
		boolean flag = false;
		try {

			for (String key : this.mapChk.keySet()) {
				if (this.mapChk.get(key).isSelected()) {
					flag = true;
					break;
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	/**
	 * 根据选中的checkbox读取xml 获取对应的所有表名
	 * @return
	 * @throws Exception
	 */
	public List<String> getALlSelectTableName() throws Exception {
		List<String> tableNames = null; // 要备份的所有表名
		try {
			tableNames = new ArrayList<String>();
			// 遍历所有复选框，根据key生成备份表名
			for (String key : mapChk.keySet()) {
				if (mapChk.get(key).isSelected()) {
					tableNames.addAll(this.getTableNameForXml(key));
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return tableNames;
	}

	/**
	 * 读取XML 获取key对应的表名
	 * 
	 * @param key
	 *            复选框选中的标识
	 * @return
	 * @throws Exception
	 */
	public List<String> getTableNameForXml(String key) throws Exception {

		DocumentBuilderFactory factory = null;
		DocumentBuilder builder = null;
		Document doc = null;
		NodeList nodeList = null;
		org.w3c.dom.Element element = null;
		List<String> tableNameList = null;
		NodeList nodeList_tablename = null;
		try {
			tableNameList = new ArrayList<String>();

			factory = DocumentBuilderFactory.newInstance();
			// 使用DocumentBuilderFactory构建DocumentBulider
			builder = factory.newDocumentBuilder();
			// 使用DocumentBuilder的parse()方法解析文件
			doc = builder.parse(new File(ServerConstant.BACKUPS_FILE));
			// root = doc.getDocumentElement();
			nodeList = doc.getElementsByTagName("node");

			for (int i = 0; i < nodeList.getLength(); i++) {
				element = (org.w3c.dom.Element) nodeList.item(i);
				// 如果key值与name相等 遍历此node下的所有table 取tablename放入结果集中
				if (key.equals(element.getAttribute("name"))) {
					nodeList_tablename = element.getElementsByTagName("table");
					// 遍历所有table标签
					for (int j = 0; j < nodeList_tablename.getLength(); j++) {
						tableNameList.add(nodeList_tablename.item(j).getTextContent());
					}
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			factory = null;
			builder = null;
			doc = null;
			nodeList = null;
			element = null;
			nodeList_tablename = null;
		}
		return tableNameList;
	}

	private JCheckBox chkAll; // 初始化整个数据库
	private JCheckBox chkSafety; // 初始化用户数据
	private JCheckBox chkAlarm; // 初始化告警
	private JCheckBox chkLog; // 初始化日志
	private JCheckBox chkSite; // 初始化网元
	private JCheckBox chkPerformance; // 初始化性能
	private JCheckBox chkBusiness; // 初始化业务

	public Map<String, JCheckBox> getMapChk() {
		return mapChk;
	}

}
