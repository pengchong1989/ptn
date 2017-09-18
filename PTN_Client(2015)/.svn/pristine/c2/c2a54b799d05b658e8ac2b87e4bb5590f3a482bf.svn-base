package com.nms.ui.ptn.clock.view.cx.frequency;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysPanel;

public class ExportClockPanelCX extends JPanel {

	private static final long serialVersionUID = -7742192168312583875L;

	private GridBagLayout gridBagLayout = null;

	private JLabel workingStatus = null;

	private JLabel actualReferenceSource = null;

	private JTextField workingStatus_ = null;

	private JTextField actualReferenceSource_ = null;

	public ExportClockPanelCX() {

		try {

			init();
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void init() throws Exception {

		try {
			
			gridBagLayout = new GridBagLayout();
			workingStatus = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_JOB_STATUS));
			actualReferenceSource = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ACTUAL_REFERENCE_SOURCE));
			workingStatus_ = new JTextField();
			workingStatus_.setEnabled(false);
			actualReferenceSource_ = new JTextField();
			actualReferenceSource_.setEnabled(false);
			
			this.setBackground(Color.WHITE);
			this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_EXPORT_CLOCK)));
			this.add(workingStatus);
			this.add(actualReferenceSource);
			this.add(workingStatus_);
			this.add(actualReferenceSource_);
			this.setGridBagLayout();/*状态->导出时钟页面布局*/
			this.setLayout(gridBagLayout);
		} catch (Exception e) {

			throw e;
		}
	}
	
	/**
	 * <p>
	 * 状态->系统时钟页面布局
	 * </p>
	 * @throws Exception
	 */
	private void setGridBagLayout() throws Exception {

		GridBagConstraints gridBagConstraints = null;
		try {

			gridBagLayout.columnWidths = new int[] { 50, 300 };
			gridBagLayout.columnWeights = new double[] { 0, 0 };
			gridBagLayout.rowHeights = new int[] { 25, 25 };
			gridBagLayout.rowWeights = new double[] { 0.0, 0.0 };
			
			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(5, 70, 5, 0);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(workingStatus, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(actualReferenceSource, gridBagConstraints);

			gridBagConstraints.insets = new Insets(5, 80, 5, 0);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(workingStatus_, gridBagConstraints);
			
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(actualReferenceSource_, gridBagConstraints);
		} catch (Exception e) {

			throw e;
		} finally {

			gridBagConstraints = null;
		}
	}

	public JTextField getWorkingStatus_() {
		return workingStatus_;
	}

	public void setWorkingStatus_(JTextField workingStatus) {
		workingStatus_ = workingStatus;
	}

	public JTextField getActualReferenceSource_() {
		return actualReferenceSource_;
	}

	public void setActualReferenceSource_(JTextField actualReferenceSource) {
		actualReferenceSource_ = actualReferenceSource;
	}
	
}
