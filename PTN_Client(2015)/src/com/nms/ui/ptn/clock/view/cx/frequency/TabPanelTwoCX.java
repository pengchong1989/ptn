package com.nms.ui.ptn.clock.view.cx.frequency;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import com.nms.db.bean.ptn.clock.ClockSource;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.ptn.clock.controller.FrequencyPanelControllerCX;
import com.nms.ui.ptn.clock.view.cx.dialog.ClockRotateDialog;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class TabPanelTwoCX extends ContentView<ClockSource> {

	private static final long serialVersionUID = 2646398118432124219L;

	private JSplitPane splitPane = null;

	private TabPanelTwoBottomCX tabPanelTwoBottomCX = null;

	private GridBagLayout gridBagLayout = null;

	private JButton rotate = null;
	
	private TabPanelTwoCX tabPanelTwoCX=null;

	public TabPanelTwoCX() {
		super("clockSource",RootFactory.CORE_MANAGE);
		try {
			init();
			tabPanelTwoCX=this;
			super.getController().refresh();/* 加载页面数据 */
			addListeners();
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
	}


	@Override
	public void setController() {

		controller = new FrequencyPanelControllerCX(this);

	}

	@Override
	public Dimension setDefaultSize() {
		return new Dimension(160, ConstantUtil.INT_WIDTH_THREE);
	}

private void addListeners() {
		
		getTable().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mouseClicked(MouseEvent evt) {
                 if(SwingUtilities.isLeftMouseButton(evt) && getSelect() != null){
                	 if(null!=getSelect().getJobState()){
                		 tabPanelTwoBottomCX.getWorkingStatus_().setText(String.valueOf(getSelect().getJobState()));
                	 }
                	 if(null!=getSelect().getManageState()){
                		 tabPanelTwoBottomCX.getManagingStatus_().setText(String.valueOf(getSelect().getManageState()));
                	 }
                	 
                 }
			}
		});
	}

	/**
	 * <p>
	 * 添加‘倒换’按钮
	 * </p>
	 */
	@Override
	public List<JButton> setAddButtons() {

		this.rotate = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_ROTATE),RootFactory.CORE_MANAGE);
		this.rotate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ClockRotateDialog(tabPanelTwoCX);
			}
		});
		List<JButton> needButtons = new ArrayList<JButton>();
		needButtons.add(this.rotate);
		return needButtons;
	}

	/**
	 * <p>
	 * 去掉不需要用到的按钮‘search’
	 * </p>
	 */
	@Override
	public List<JButton> setNeedRemoveButtons() {

		List<JButton> needButtons = new ArrayList<JButton>();
		needButtons.add(super.getSearchButton());
		return needButtons;
	}

	private void init() throws Exception {

		int high = 0;
		try {

			tabPanelTwoBottomCX = new TabPanelTwoBottomCX();
			tabPanelTwoBottomCX.getWorkingStatus_().setEditable(false);
			tabPanelTwoBottomCX.getManagingStatus_().setEditable(false);
			
			splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
			gridBagLayout = new GridBagLayout();

			splitPane.setOneTouchExpandable(true);
			high = Double.valueOf(Toolkit.getDefaultToolkit().getScreenSize().getHeight()).intValue() / 2;
			splitPane.setDividerLocation(high - 65);
			splitPane.setTopComponent(this.getContentPanel());
			splitPane.setBottomComponent(tabPanelTwoBottomCX);
			setGridBagLayout();
			this.setLayout(gridBagLayout);
			this.add(splitPane);
			this.setBackground(Color.WHITE);
		} catch (Exception e) {

			throw e;
		}
	}

	private void setGridBagLayout() throws Exception {

		GridBagConstraints gridBagConstraints = null;
		try {

			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.gridheight = 1;
			gridBagConstraints.gridwidth = 1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagLayout.setConstraints(splitPane, gridBagConstraints);
		} catch (Exception e) {

			throw e;
		}
	}

	
	public static void main(String[] args) {

		try {
			TabPanelTwoCX frequencyPanelNew = new TabPanelTwoCX();
			JFrame frame = new JFrame();
			frame.setSize(500, 400);
			frame.setContentPane(frequencyPanelNew);
			frame.setVisible(true);
		} catch (Exception e) {

			ExceptionManage.dispose(e,TabPanelTwoCX.class);
		}
	}
}
