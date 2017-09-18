package com.nms.ui.ptn.basicinfo.dialog.site;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTab;

/**
 * 网元搜索
 * @author Dzy
 *
 */
public class SiteSearchDialog extends PtnDialog{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 6727432452360995481L;
		private JButton canelButton;
		private SiteSearchTablePanel siteSearchTablePanel;
		public SiteSearchDialog() {
			try {
				initComponents();
				setLayout();
				this.addListener();
				UiUtil.showWindow(this, 800, 500);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
		
		/**
		 * 初始化
		 * @throws Exception
		 */
		private void initComponents() throws Exception {
			try {
				this.setTitle(ResourceUtil.srcStr(StringKeysTab.TAB_SITESEARCH));
				this.canelButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
				siteSearchTablePanel = new SiteSearchTablePanel();
			} catch (Exception e) {;
				throw e;
			}
			
		}
		/**
		 * 添加监听事件
		 */
		private void addListener() {
			this.canelButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					canelButtonActionPerformed(e);
				}
			});
		}

		/**
		 * 布局管理
		 */
		private void setLayout() {
			GridBagLayout componentLayout = new GridBagLayout();
			componentLayout.columnWidths = new int[] { 700,100 };
			componentLayout.columnWeights = new double[] { 0, 0, 0 };
			componentLayout.rowHeights = new int[] { 430,20};
			componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0, 0, 0.2 };
			this.setLayout(componentLayout);

			GridBagConstraints c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			
			c.gridx = 0;
			c.gridy = 0;
			c.gridheight = 1;
			c.gridwidth = 3;
			c.insets = new Insets(5, 5, 5, 5);
			componentLayout.setConstraints(this.siteSearchTablePanel, c);
			this.add(this.siteSearchTablePanel);
			
		
			c.anchor = GridBagConstraints.EAST;
			c.gridx = 1;
			c.gridy = 1;
			c.insets = new Insets(0, 5, 0, 25);
			componentLayout.setConstraints(this.canelButton, c);
			this.add(this.canelButton);
			
		}
		
		/**
		 * 取消按钮
		 * @param evt
		 */
		private void canelButtonActionPerformed(java.awt.event.ActionEvent evt) {
			this.dispose();
		}

		public SiteSearchTablePanel getSiteSearchTablePanel() {
			return siteSearchTablePanel;
		}


}
