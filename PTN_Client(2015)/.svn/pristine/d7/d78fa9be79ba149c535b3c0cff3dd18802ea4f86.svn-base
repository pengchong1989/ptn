package com.nms.ui.ptn.safety;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import com.nms.db.bean.system.loginlog.UserLock;
import com.nms.model.system.loginlog.UserLockServiece_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

/**
 * 显示用户锁定信息面板
 * 
 * @author Administrator
 * 
 */

public class UserLockPanel extends JPanel {
	private static final long serialVersionUID = 7244399063021284941L;
	private ViewDataTable<UserLock> table;
	private final String TABLENAME = "UserLockTable";
	private JScrollPane scrollPane;// 滚动
	private UserInfoPanel userinfoPanel = null;

	public UserLockPanel(UserInfoPanel userinfoPanel) {
		this.userinfoPanel = userinfoPanel;
		initComponents();
		setLayout();
		this.addListeners();
	}
	

	private void initComponents() {
		table = new ViewDataTable<UserLock>(TABLENAME);
		table.getTableHeader().setResizingAllowed(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		table.setTableHeaderPopupMenuFactory(null);
		table.setTableBodyPopupMenuFactory(null);
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	private void setLayout() {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		GridBagLayout qosGridBagLayout = new GridBagLayout();
		this.setLayout(qosGridBagLayout);
		addComponent(this, scrollPane, 0, 0, 0.5, 1.0, 0, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 0), GridBagConstraints.NORTHWEST, gridBagConstraints);
	}

	private void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
	}

	private void initData(List<UserLock> userList) {
		if (userList != null && userList.size() > 0) {
			table.initData(userList);
		}
	}

	/**
	 * 添加监听
	 */
	private void addListeners() {

		userinfoPanel.getTable().addElementClickedActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (userinfoPanel.getSelect() == null) {
					// 清除详细面板数据
					table.clear();
				} else {
					UserLockServiece_MB userlockServiece = null;
					try {
						userlockServiece = (UserLockServiece_MB) ConstantUtil.serviceFactory.newService_MB(Services.USERLOCKSERVIECE);
						UserLock userlock = new UserLock();
						userlock.setUser_id(userinfoPanel.getSelect().getUser_Id());
						List<UserLock> userlocklist = userlockServiece.selectUserLock(userlock);
						table.clear();
						initData(userlocklist);

					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}finally{
						UiUtil.closeService_MB(userlockServiece);
					}
				}
			}
		});

	}
}
