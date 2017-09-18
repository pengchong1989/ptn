package com.nms.ui.ptn.clock.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import twaver.Element;
import twaver.Node;
import twaver.TDataBox;
import twaver.list.TList;

import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;

/**
 * 频率配置对话框
 * 
 * @author lp
 * 
 */
public class ConfigFrequencyDialog extends PtnDialog {

	private static final long serialVersionUID = -800170517095171465L;
	private Map<String, String> items;
	private TDataBox needChooseBox;
	private TList needChooseList;
	private TDataBox choosedBox;
	private TList choosedList;
	private JButton addButton;
	private JButton addAllButton;
	private JButton removeButton;
	private JButton removeAllButton;
	private JPanel controlPane;
	private JScrollPane needChooseScrollPane;
	private JScrollPane choosedScrollPane;
	private JButton confirm;
	private JButton cancel;

	public ConfigFrequencyDialog(String title, Map<String, String> items) {
		this.setModal(true);
		this.items = items;
		this.setTitle(title);
		init();
	}

	private void init() {
		initComponents();
		setLayout();
		addActionLister();
	}

	@SuppressWarnings("unchecked")
	private void addActionLister() {
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				move(needChooseBox.getSelectionModel().getAllSelectedElement(), needChooseBox, choosedBox);
			}
		});
		addAllButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				move(needChooseBox.getAllElements(), needChooseBox, choosedBox);

			}
		});
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				move(choosedBox.getSelectionModel().getAllSelectedElement(), choosedBox, needChooseBox);
			}
		});
		removeAllButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				move(choosedBox.getAllElements(), choosedBox, needChooseBox);
			}
		});
	}

	/**
	 * 当点击确定后，验证用户操作是否完成
	 * 
	 * @return
	 */
	public boolean validated() {
		boolean flag = false;
		if (needChooseBox.getAllElements() == null || needChooseBox.getAllElements().size() == 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 获取界面数据
	 */
	@SuppressWarnings("unchecked")
	public String getInfo() {
		StringBuilder builder = new StringBuilder();
		List<Element> elements = choosedBox.getAllElements();
		for (Element element : elements) {
			builder.append(element.getUserObject());
		}
		return builder.toString();
	}

	private void move(List<Element> elements, TDataBox source, TDataBox target) {
		Iterator<Element> it = elements.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			if (!element.isSelected()) {
				it.remove();
			}
			source.removeElement(element);
			target.addElement(element);
		}
		if (elements.size() > 0) {
			target.getSelectionModel().setSelection(elements);
		}
	}

	private void setLayout() {
		initControlPane();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 100, 40, 100 };
		layout.columnWeights = new double[] { 0.5, 0, 0.5 };
		layout.rowHeights = new int[] { 150, 20 };
		layout.rowWeights = new double[] { 1.0, 0 };
		this.setLayout(layout);
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(needChooseScrollPane, c);
		this.add(needChooseScrollPane);
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 0, 5, 0);
		layout.setConstraints(controlPane, c);
		this.add(controlPane);
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(choosedScrollPane, c);
		this.add(choosedScrollPane);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.WEST;
		c.insets = new Insets(5, 5, 5, 20);
		layout.setConstraints(confirm, c);
		this.add(confirm);
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.WEST;
		c.insets = new Insets(5, 5, 5, 5);
		layout.setConstraints(cancel, c);
		this.add(cancel);
	}

	private void initControlPane() {
		GridBagLayout controlLayout = new GridBagLayout();
		controlLayout.columnWidths = new int[] { 25 };
		controlLayout.columnWeights = new double[] { 0.0 };
		controlLayout.rowHeights = new int[] { 5, 5, 5, 5 };
		controlLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
		controlPane.setLayout(controlLayout);
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		controlLayout.setConstraints(addButton, c);
		controlPane.add(addButton);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		controlLayout.setConstraints(addAllButton, c);
		controlPane.add(addAllButton);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		controlLayout.setConstraints(removeButton, c);
		controlPane.add(removeButton);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.insets = new Insets(10, 10, 10, 10);
		controlLayout.setConstraints(removeAllButton, c);
		controlPane.add(removeAllButton);
	}

	private void initComponents() {
		needChooseBox = new TDataBox("NeedChooseList");
		needChooseList = new TList(needChooseBox);
		needChooseList.setTListSelectionMode(TList.CHECK_SELECTION);

		choosedBox = new TDataBox("ChoosedList");
		choosedList = new TList(choosedBox);
		choosedList.setTListSelectionMode(TList.CHECK_SELECTION);

		addButton = new JButton(">");
		addAllButton = new JButton(">>");
		removeButton = new JButton("<");
		removeAllButton = new JButton("<<");
		controlPane = new JPanel();
		needChooseScrollPane = new JScrollPane(needChooseList);
		needChooseScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		needChooseScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		needChooseScrollPane.setViewportView(needChooseList);

		choosedScrollPane = new JScrollPane(choosedList);
		choosedScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		choosedScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		choosedScrollPane.setViewportView(choosedList);
		confirm = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		initChooseBox();
	}

	private void initChooseBox() {
		int i = 0;
		for (String key : items.keySet()) {
			Node node = new Node(i++);
			node.setName(items.get(key));
			node.setDisplayName(items.get(key));
			node.setUserObject(key);
			needChooseBox.addElement(node);
		}
	}

	public JButton getConfirm() {
		return confirm;
	}

	public JButton getCancel() {
		return cancel;
	}

}
