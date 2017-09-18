package com.nms.ui.ptn.ne.group.view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import com.nms.drive.service.impl.CoderUtils;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTitle;

public class PortChoiceDialog extends PtnDialog{
	
	private static final long serialVersionUID = 1L;
	private JCheckBox lan1CheckBox;
	private JCheckBox lan2CheckBox;
	private JCheckBox lan3CheckBox;
	private JCheckBox lan4CheckBox;
	private JCheckBox lan5CheckBox;
	private JCheckBox lan6CheckBox;
	private JCheckBox lan7CheckBox;
	private JCheckBox lan8CheckBox;
	private JCheckBox lan9CheckBox;
	private JCheckBox lan10CheckBox;
	private JCheckBox eLan1CheckBox;
	private JCheckBox eLan2CheckBox;
	private JCheckBox eLan3CheckBox;
	private JCheckBox eLan4CheckBox;
	private JCheckBox eLan5CheckBox;
	private JCheckBox eLan6CheckBox;
	private JCheckBox eLan7CheckBox;
	private JCheckBox eLan8CheckBox;
	private JCheckBox eLan9CheckBox;
	private JCheckBox eLan10CheckBox;
	private JCheckBox eLan11CheckBox;
	private JCheckBox eLan12CheckBox;
	private JCheckBox eLan13CheckBox;
	private JCheckBox eLan14CheckBox;
	private JCheckBox eLan15CheckBox;
	private JCheckBox eLan16CheckBox;
	private JCheckBox eLan17CheckBox;
	private JCheckBox eLan18CheckBox;
	private JCheckBox eLan19CheckBox;
	private JCheckBox eLan20CheckBox;
	private JCheckBox eLan21CheckBox;
	private JCheckBox eLan22CheckBox;
	private JCheckBox eLan23CheckBox;
	private JCheckBox eLan24CheckBox;
	private JCheckBox eLan25CheckBox;
	private JCheckBox eLan26CheckBox;
	private JCheckBox eLan27CheckBox;
	private JCheckBox eLan28CheckBox;
	private JCheckBox eLan29CheckBox;
	private JCheckBox eLan30CheckBox;
	private JCheckBox eLan31CheckBox;
	private JCheckBox eLan32CheckBox;
	private JCheckBox eLan33CheckBox;
	private JCheckBox eLan34CheckBox;
	private JCheckBox eLan35CheckBox;
	private JCheckBox eLan36CheckBox;
	private JCheckBox eLan37CheckBox;
	private JCheckBox eLan38CheckBox;
	private JCheckBox eLan39CheckBox;
	private JCheckBox eLan40CheckBox;
	private JCheckBox eLan41CheckBox;
	private JCheckBox eLan42CheckBox;
	private JCheckBox eLan43CheckBox;
	private JCheckBox eLan44CheckBox;
	private JCheckBox eLan45CheckBox;
	private JCheckBox eLan46CheckBox;
	private JCheckBox eLan47CheckBox;
	private JCheckBox eLan48CheckBox;
	private JCheckBox eLan49CheckBox;
	private JCheckBox eLan50CheckBox;
	private JCheckBox eLan51CheckBox;
	private JCheckBox eLan52CheckBox;
	private JCheckBox eLan53CheckBox;
	private JCheckBox eLan54CheckBox;
	private JCheckBox eLan55CheckBox;
	private JCheckBox eLan56CheckBox;
	private JCheckBox eLan57CheckBox;
	private JCheckBox eLan58CheckBox;
	private JCheckBox eLan59CheckBox;
	private JCheckBox eLan60CheckBox;
	private JCheckBox eLan61CheckBox;
	private JCheckBox eLan62CheckBox;
	private JCheckBox eLan63CheckBox;
	private JCheckBox eLan64CheckBox;
	private JButton confirm;
	private JButton cancel;
	private JPanel buttonPanel;
	private JPanel contentPanel;
	
	public PortChoiceDialog(String actionCommand) {
		this.setModal(true);
		this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_PORT_SELECT));
		// 初始化控件
		initComponent();
		// 界面布局
		setQLDialogLayout();
	}
	
	private void initComponent() {
		lan1CheckBox = new JCheckBox("LAN1");
		lan2CheckBox = new JCheckBox("LAN2");
		lan3CheckBox = new JCheckBox("LAN3");
		lan4CheckBox = new JCheckBox("LAN4");
		lan5CheckBox = new JCheckBox("LAN5");
		lan6CheckBox = new JCheckBox("LAN6");
		lan7CheckBox = new JCheckBox("LAN7");
		lan8CheckBox = new JCheckBox("LAN8");
		lan9CheckBox = new JCheckBox("LAN9");
		lan10CheckBox = new JCheckBox("LAN10");
		eLan1CheckBox = new JCheckBox("eLAN1");
		eLan2CheckBox = new JCheckBox("eLAN2");
		eLan3CheckBox = new JCheckBox("eLAN3");
		eLan4CheckBox = new JCheckBox("eLAN4");
		eLan5CheckBox = new JCheckBox("eLAN5");
		eLan6CheckBox = new JCheckBox("eLAN6");
		eLan7CheckBox = new JCheckBox("eLAN7");
		eLan8CheckBox = new JCheckBox("eLAN8");
		eLan9CheckBox = new JCheckBox("eLAN9");
		eLan10CheckBox = new JCheckBox("eLAN10");
		eLan11CheckBox = new JCheckBox("eLAN11");
		eLan12CheckBox = new JCheckBox("eLAN12");
		eLan13CheckBox = new JCheckBox("eLAN13");
		eLan14CheckBox = new JCheckBox("eLAN14");
		eLan15CheckBox = new JCheckBox("eLAN15");
		eLan16CheckBox = new JCheckBox("eLAN16");
		eLan17CheckBox = new JCheckBox("eLAN17");
		eLan18CheckBox = new JCheckBox("eLAN18");
		eLan19CheckBox = new JCheckBox("eLAN19");
		eLan20CheckBox = new JCheckBox("eLAN20");
		eLan21CheckBox = new JCheckBox("eLAN21");
		eLan22CheckBox = new JCheckBox("eLAN22");
		eLan23CheckBox = new JCheckBox("eLAN23");
		eLan24CheckBox = new JCheckBox("eLAN24");
		eLan25CheckBox = new JCheckBox("eLAN25");
		eLan26CheckBox = new JCheckBox("eLAN26");
		eLan27CheckBox = new JCheckBox("eLAN27");
		eLan28CheckBox = new JCheckBox("eLAN28");
		eLan29CheckBox = new JCheckBox("eLAN29");
		eLan30CheckBox = new JCheckBox("eLAN30");
		eLan31CheckBox = new JCheckBox("eLAN31");
		eLan32CheckBox = new JCheckBox("eLAN32");
		eLan33CheckBox = new JCheckBox("eLAN33");
		eLan34CheckBox = new JCheckBox("eLAN34");
		eLan35CheckBox = new JCheckBox("eLAN35");
		eLan36CheckBox = new JCheckBox("eLAN36");
		eLan37CheckBox = new JCheckBox("eLAN37");
		eLan38CheckBox = new JCheckBox("eLAN38");
		eLan39CheckBox = new JCheckBox("eLAN39");
		eLan40CheckBox = new JCheckBox("eLAN40");
		eLan41CheckBox = new JCheckBox("eLAN41");
		eLan42CheckBox = new JCheckBox("eLAN42");
		eLan43CheckBox = new JCheckBox("eLAN43");
		eLan44CheckBox = new JCheckBox("eLAN44");
		eLan45CheckBox = new JCheckBox("eLAN45");
		eLan46CheckBox = new JCheckBox("eLAN46");
		eLan47CheckBox = new JCheckBox("eLAN47");
		eLan48CheckBox = new JCheckBox("eLAN48");
		eLan49CheckBox = new JCheckBox("eLAN49");
		eLan50CheckBox = new JCheckBox("eLAN50");
		eLan51CheckBox = new JCheckBox("eLAN51");
		eLan52CheckBox = new JCheckBox("eLAN52");
		eLan53CheckBox = new JCheckBox("eLAN53");
		eLan54CheckBox = new JCheckBox("eLAN54");
		eLan55CheckBox = new JCheckBox("eLAN55");
		eLan56CheckBox = new JCheckBox("eLAN56");
		eLan57CheckBox = new JCheckBox("eLAN57");
		eLan58CheckBox = new JCheckBox("eLAN58");
		eLan59CheckBox = new JCheckBox("eLAN59");
		eLan60CheckBox = new JCheckBox("eLAN60");
		eLan61CheckBox = new JCheckBox("eLAN61");
		eLan62CheckBox = new JCheckBox("eLAN62");
		eLan63CheckBox = new JCheckBox("eLAN63");
		eLan64CheckBox = new JCheckBox("eLAN64");
		
		confirm = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		buttonPanel = new JPanel();
		contentPanel = new JPanel();
	}
	
	private void setQLDialogLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] {30, 30, 30, 30, 30, 30, 30 };
		layout.columnWeights = new double[] {0, 0, 0, 0, 0, 0, 0, 0};
		layout.rowHeights = new int[] {20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		layout.rowWeights = new double[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		GridBagConstraints c = new GridBagConstraints();
		contentPanel.setLayout(layout);
		
		addComponent(contentPanel, lan1CheckBox, 0, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, lan2CheckBox, 0, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, lan3CheckBox, 0, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, lan4CheckBox, 0, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, lan5CheckBox, 0, 5, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, lan6CheckBox, 0, 6, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, lan7CheckBox, 0, 7, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, lan8CheckBox, 0, 8, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, lan9CheckBox, 0, 9, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, lan10CheckBox, 0, 10, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		
		addComponent(contentPanel, eLan1CheckBox, 1, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan2CheckBox, 1, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan3CheckBox, 1, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan4CheckBox, 1, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan5CheckBox, 1, 5, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan6CheckBox, 1, 6, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan7CheckBox, 1, 7, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan8CheckBox, 1, 8, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan9CheckBox, 1, 9, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan10CheckBox, 1, 10, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan11CheckBox, 2, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan12CheckBox, 2, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan13CheckBox, 2, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan14CheckBox, 2, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan15CheckBox, 2, 5, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan16CheckBox, 2, 6, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan17CheckBox, 2, 7, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan18CheckBox, 2, 8, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan19CheckBox, 2, 9, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan20CheckBox, 2, 10, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan21CheckBox, 3, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan22CheckBox, 3, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan23CheckBox, 3, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan24CheckBox, 3, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan25CheckBox, 3, 5, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan26CheckBox, 3, 6, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan27CheckBox, 3, 7, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan28CheckBox, 3, 8, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan29CheckBox, 3, 9, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan30CheckBox, 3, 10, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan31CheckBox, 4, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan32CheckBox, 4, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan33CheckBox, 4, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan34CheckBox, 4, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan35CheckBox, 4, 5, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan36CheckBox, 4, 6, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan37CheckBox, 4, 7, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan38CheckBox, 4, 8, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan39CheckBox, 4, 9, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan40CheckBox, 4, 10, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan41CheckBox, 5, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan42CheckBox, 5, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan43CheckBox, 5, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan44CheckBox, 5, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan45CheckBox, 5, 5, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan46CheckBox, 5, 6, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan47CheckBox, 5, 7, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan48CheckBox, 5, 8, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan49CheckBox, 5, 9, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan50CheckBox, 5, 10, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan51CheckBox, 6, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan52CheckBox, 6, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan53CheckBox, 6, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan54CheckBox, 6, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan55CheckBox, 6, 5, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan56CheckBox, 6, 6, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan57CheckBox, 6, 7, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan58CheckBox, 6, 8, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan59CheckBox, 6, 9, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan60CheckBox, 6, 10, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan61CheckBox, 7, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan62CheckBox, 7, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan63CheckBox, 7, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, eLan64CheckBox, 7, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.NORTHWEST, c);
		
		addComponent(contentPanel, buttonPanel, 4, 11, 1.0, 0.1, 4, 1,
				GridBagConstraints.BOTH, new Insets(15, 5, 10, 10), GridBagConstraints.NORTHWEST, c);
		
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.CENTER);
		buttonPanel.setLayout(flowLayout);
		buttonPanel.add(confirm);
		buttonPanel.add(cancel);
		
		this.add(contentPanel);
	}

	/**
	 * 初始化界面的值
	 */
	public void init(String portChoice) {
		
	}
	/**
	 * 获取界面的值
	 */
	public String get() {
		StringBuffer str1 = new StringBuffer();
		str1.append(lan8CheckBox.isSelected()?1:0);
		str1.append(lan7CheckBox.isSelected()?1:0);
		str1.append(lan6CheckBox.isSelected()?1:0);
		str1.append(lan5CheckBox.isSelected()?1:0);
		str1.append(lan4CheckBox.isSelected()?1:0);
		str1.append(lan3CheckBox.isSelected()?1:0);
		str1.append(lan2CheckBox.isSelected()?1:0);
		str1.append(lan1CheckBox.isSelected()?1:0);
		char[] result1 = str1.toString().toCharArray();
		int a1 = CoderUtils.convertAlgorism(result1);
		
		StringBuffer str2 = new StringBuffer();

		str2.append(lan10CheckBox.isSelected()?1:0);
		str2.append(lan9CheckBox.isSelected()?1:0);
		char[] result2 = str2.toString().toCharArray();
		int a2 = CoderUtils.convertAlgorism(result2);
		
		StringBuffer str3 = new StringBuffer();
		str3.append(eLan8CheckBox.isSelected()?1:0);
		str3.append(eLan7CheckBox.isSelected()?1:0);
		str3.append(eLan6CheckBox.isSelected()?1:0);
		str3.append(eLan5CheckBox.isSelected()?1:0);
		str3.append(eLan4CheckBox.isSelected()?1:0);
		str3.append(eLan3CheckBox.isSelected()?1:0);
		str3.append(eLan2CheckBox.isSelected()?1:0);
		str3.append(eLan1CheckBox.isSelected()?1:0);
		char[] result3 = str3.toString().toCharArray();
		int a3 = CoderUtils.convertAlgorism(result3);
		
		StringBuffer str4 = new StringBuffer();
		str4.append(eLan16CheckBox.isSelected()?1:0);
		str4.append(eLan15CheckBox.isSelected()?1:0);
		str4.append(eLan14CheckBox.isSelected()?1:0);
		str4.append(eLan13CheckBox.isSelected()?1:0);
		str4.append(eLan12CheckBox.isSelected()?1:0);
		str4.append(eLan11CheckBox.isSelected()?1:0);
		str4.append(eLan10CheckBox.isSelected()?1:0);
		str4.append(eLan9CheckBox.isSelected()?1:0);
		char[] result4 = str4.toString().toCharArray();
		int a4 = CoderUtils.convertAlgorism(result4);
		
		StringBuffer str5 = new StringBuffer();
		str5.append(eLan24CheckBox.isSelected()?1:0);
		str5.append(eLan23CheckBox.isSelected()?1:0);
		str5.append(eLan22CheckBox.isSelected()?1:0);
		str5.append(eLan21CheckBox.isSelected()?1:0);
		str5.append(eLan20CheckBox.isSelected()?1:0);
		str5.append(eLan19CheckBox.isSelected()?1:0);
		str5.append(eLan18CheckBox.isSelected()?1:0);
		str5.append(eLan17CheckBox.isSelected()?1:0);
		char[] result5 = str5.toString().toCharArray();
		int a5 = CoderUtils.convertAlgorism(result5);
		
		StringBuffer str6 = new StringBuffer();
		str6.append(eLan32CheckBox.isSelected()?1:0);
		str6.append(eLan31CheckBox.isSelected()?1:0);
		str6.append(eLan30CheckBox.isSelected()?1:0);
		str6.append(eLan29CheckBox.isSelected()?1:0);
		str6.append(eLan28CheckBox.isSelected()?1:0);
		str6.append(eLan27CheckBox.isSelected()?1:0);
		str6.append(eLan26CheckBox.isSelected()?1:0);
		str6.append(eLan25CheckBox.isSelected()?1:0);
		char[] result6 = str6.toString().toCharArray();
		int a6 = CoderUtils.convertAlgorism(result6);
		
		StringBuffer str7 = new StringBuffer();
		str7.append(eLan40CheckBox.isSelected()?1:0);
		str7.append(eLan39CheckBox.isSelected()?1:0);
		str7.append(eLan38CheckBox.isSelected()?1:0);
		str7.append(eLan37CheckBox.isSelected()?1:0);
		str7.append(eLan36CheckBox.isSelected()?1:0);
		str7.append(eLan35CheckBox.isSelected()?1:0);
		str7.append(eLan34CheckBox.isSelected()?1:0);
		str7.append(eLan33CheckBox.isSelected()?1:0);
		char[] result7 = str7.toString().toCharArray();
		int a7 = CoderUtils.convertAlgorism(result7);
		
		StringBuffer str8 = new StringBuffer();
		str8.append(eLan48CheckBox.isSelected()?1:0);
		str8.append(eLan47CheckBox.isSelected()?1:0);
		str8.append(eLan46CheckBox.isSelected()?1:0);
		str8.append(eLan45CheckBox.isSelected()?1:0);
		str8.append(eLan44CheckBox.isSelected()?1:0);
		str8.append(eLan43CheckBox.isSelected()?1:0);
		str8.append(eLan42CheckBox.isSelected()?1:0);
		str8.append(eLan41CheckBox.isSelected()?1:0);
		char[] result8 = str8.toString().toCharArray();
		int a8 = CoderUtils.convertAlgorism(result8);
		
		StringBuffer str9 = new StringBuffer();
		str9.append(eLan56CheckBox.isSelected()?1:0);
		str9.append(eLan55CheckBox.isSelected()?1:0);
		str9.append(eLan54CheckBox.isSelected()?1:0);
		str9.append(eLan53CheckBox.isSelected()?1:0);
		str9.append(eLan52CheckBox.isSelected()?1:0);
		str9.append(eLan51CheckBox.isSelected()?1:0);
		str9.append(eLan50CheckBox.isSelected()?1:0);
		str9.append(eLan49CheckBox.isSelected()?1:0);
		char[] result9 = str9.toString().toCharArray();
		int a9 = CoderUtils.convertAlgorism(result9);
		
		StringBuffer str10 = new StringBuffer();
		str10.append(eLan64CheckBox.isSelected()?1:0);
		str10.append(eLan63CheckBox.isSelected()?1:0);
		str10.append(eLan62CheckBox.isSelected()?1:0);
		str10.append(eLan61CheckBox.isSelected()?1:0);
		str10.append(eLan60CheckBox.isSelected()?1:0);
		str10.append(eLan59CheckBox.isSelected()?1:0);
		str10.append(eLan58CheckBox.isSelected()?1:0);
		str10.append(eLan57CheckBox.isSelected()?1:0);
		char[] result10 = str10.toString().toCharArray();
		int a10 = CoderUtils.convertAlgorism(result10);
		
		StringBuffer str = new StringBuffer();
		str.append(a1 + ",");
		str.append(a2 + ",");
		str.append(a3 + ",");
		str.append(a4 + ",");
		str.append(a5 + ",");
		str.append(a6 + ",");
		str.append(a7 + ",");
		str.append(a8 + ",");
		str.append(a9 + ",");
		str.append(a10);
		
		return str.toString();
	}
	
	public JButton getConfirm() {
		return confirm;
	}

	public void setConfirm(JButton confirm) {
		this.confirm = confirm;
	}

	public JButton getCancel() {
		return cancel;
	}

	public void setCancel(JButton cancel) {
		this.cancel = cancel;
	}

}
