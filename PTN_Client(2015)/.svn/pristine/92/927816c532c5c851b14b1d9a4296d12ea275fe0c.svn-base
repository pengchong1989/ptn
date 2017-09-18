package com.nms.ui.ptn.ne.pri.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.ne.pri.controller.PriMainContorller;
import com.nms.ui.ptn.safety.roleManage.RootFactory;


public class PriMainPanel extends ContentView<QosMappingMode>{
	
	private static final long serialVersionUID = -7071818928958422496L;
	private JSplitPane splitPane;
	protected JTabbedPane tabbedPane;
	private PriPanel updateExpDialog;
	private JScrollPane sllPanelTab_attr;
	private JPanel panel;
	
	public PriMainPanel() throws Exception {
		
		super("priMapping",RootFactory.CORE_MANAGE);
		initComponent();
		init();
		setLayout();
		addListeners();
		tabbedPane.add(ResourceUtil.srcStr(StringKeysTitle.TIT_PRI_MAPPING),sllPanelTab_attr);
		
	}

	private void addListeners() {
		getTable().addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent evt) {
	
				if (SwingUtilities.isLeftMouseButton(evt) && getSelect() != null) {
					try {
						updateExpDialog=new PriPanel(getSelect());
						sllPanelTab_attr.setViewportView(updateExpDialog);
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	public void init() throws Exception{
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_PRI_MAPPING)));
		try {
			getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	private void initComponent() throws Exception {
		updateExpDialog=new PriPanel(null);
		panel = new JPanel();
		tabbedPane = new JTabbedPane();
		tabbedPane.setMaximumSize(new Dimension(1000,500));
		tabbedPane.add( updateExpDialog);
		splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setOneTouchExpandable(true);
		splitPane.setTopComponent(this.getContentPanel());
		splitPane.setBottomComponent(tabbedPane);
		int high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
		splitPane.setDividerLocation(high - 65);
		this.sllPanelTab_attr=new JScrollPane();
		sllPanelTab_attr.setViewportView(updateExpDialog);
	}

	public void setLayout(){
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(splitPane, c);
		this.add(splitPane);
	}
	
	@Override
	public void setController() {
		controller = new PriMainContorller(this);
	}
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needButtons = new ArrayList<JButton>();
		needButtons.add(getAddButton());
		needButtons.add(getDeleteButton());
		needButtons.add(getSearchButton());
		needButtons.add(getSynchroButton());
		return needButtons;
	}

	@Override
	public void setTablePopupMenuFactory() {
		setMenuFactory(null);
	}
		
	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}
	
	

	
}
