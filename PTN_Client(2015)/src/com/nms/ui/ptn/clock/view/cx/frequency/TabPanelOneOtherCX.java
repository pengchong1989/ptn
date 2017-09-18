package com.nms.ui.ptn.clock.view.cx.frequency;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class TabPanelOneOtherCX extends JPanel {

	private static final long serialVersionUID = 1948808170963086124L;

	private JButton search = null;

	private PtnButton save = null;

	private GridBagLayout gridBagLayout = null;

	public TabPanelOneOtherCX() {

		try {

			init();
//			addButtonListener();/* 按钮添加监听 */
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void init() throws Exception {

		try {
			gridBagLayout = new GridBagLayout();
			search = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SELECT));
			save = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),false,RootFactory.CORE_MANAGE);
			this.setBackground(Color.WHITE);
			this.add(search);
			this.add(save);
			this.setGridBagLayout();/* 按钮布局 */
			this.setLayout(gridBagLayout);
		} catch (Exception e) {

			throw e;
		}
	}

	/**
	 * <p>
	 * 按钮布局
	 * </p>
	 * 
	 * @throws Exception
	 */
	private void setGridBagLayout() throws Exception {

		GridBagConstraints gridBagConstraints = null;
		try {

			gridBagLayout.columnWidths = new int[] { 1, 1 };
			gridBagLayout.columnWeights = new double[] { 0, 0 };
			gridBagLayout.rowHeights = new int[] { 1 };
			gridBagLayout.rowWeights = new double[] { 0.0 };

			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints.fill = GridBagConstraints.BOTH;

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(search, gridBagConstraints);

			gridBagConstraints.insets = new Insets(5, 20, 5, 0);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(save, gridBagConstraints);

		} catch (Exception e) {

			throw e;
		} finally {

			gridBagConstraints = null;
		}
	}
	public JButton getSearch() {
		return search;
	}

	public void setSearch(JButton search) {
		this.search = search;
	}

	public PtnButton getSave() {
		return save;
	}

	public void setSave(PtnButton save) {
		this.save = save;
	}
}
