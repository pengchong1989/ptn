package com.nms.ui.ptn.clock.view.cx.time;

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

public class TabPanelOneStatusTCX extends JPanel {

	private static final long serialVersionUID = 4298159953005669616L;

	private GridBagLayout gridBagLayout = null;

	private JLabel clockID = null;

	private JTextField clockID_ = null;

	private JLabel clockType = null;

	private JTextField clockType_ = null;

	private JLabel parentClockID = null;

	private JTextField parentClockID_ = null;

	private JLabel parentCLockPort = null;

	private JTextField parentCLockPort_ = null;

	private JLabel hops = null;

	private JTextField hops_ = null;

	private JLabel SystemTimeAnd1588Difference = null;

	private JTextField SystemTimeAnd1588Difference_ = null;

	private JLabel currentTODStatus = null;

	private JTextField currentTODStatus_ = null;

	private JLabel grandParentClockID = null;

	private JTextField grandParentClockID_ = null;

	private JLabel grandParentClockType = null;

	private JTextField grandParentClockType_ = null;

	private JLabel grandParentClockAccuracy = null;

	private JTextField grandParentClockAccuracy_ = null;

	private JLabel grandParentClockVariance = null;

	private JTextField grandParentClockVariance_ = null;

	private JLabel grandParentClockPriority1 = null;

	private JTextField grandParentClockPriority1_ = null;

	private JLabel grandParentClockPriority2 = null;

	private JTextField grandParentClockPriority2_ = null;

	public TabPanelOneStatusTCX() {

		try {

			init();
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void init() throws Exception {

		try {
			clockID = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK_ID));
			clockID_ = new JTextField();
			clockID_.setEnabled(false);
			clockType = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK_TYPE));
			clockType_ = new JTextField();
			clockType_.setEnabled(false);
			parentClockID = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PARENT_CLOCK_ID));
			parentClockID_ = new JTextField();
			parentClockID_.setEnabled(false);
			parentCLockPort = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PARENT_CLOCK_PORT));
			parentCLockPort_ = new JTextField();
			parentCLockPort_.setEnabled(false);
			hops = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_HOPS));
			hops_ = new JTextField();
			hops_.setEnabled(false);
			SystemTimeAnd1588Difference = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_1588_AND_SYSTEM_TIME_DIFFERENCE));
			SystemTimeAnd1588Difference_ = new JTextField();
			SystemTimeAnd1588Difference_.setEnabled(false);
			currentTODStatus = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CURRENT_TOD_STATUS));
			currentTODStatus_ = new JTextField();
			currentTODStatus_.setEnabled(false);
			grandParentClockID = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_GRAND_PARENT_CLOCK_ID));
			grandParentClockID_ = new JTextField();
			grandParentClockID_.setEnabled(false);
			grandParentClockType = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_GRAND_PARENT_CLOCK_TYPE));
			grandParentClockType_ = new JTextField();
			grandParentClockType_.setEnabled(false);
			grandParentClockAccuracy = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_GRAND_PARENT_CLOCK_ACCURACY));
			grandParentClockAccuracy_ = new JTextField();
			grandParentClockAccuracy_.setEnabled(false);
			grandParentClockVariance = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_GRAND_PARENT_CLOCK_VARIANCE));
			grandParentClockVariance_ = new JTextField();
			grandParentClockVariance_.setEnabled(false);
			grandParentClockPriority1 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_GRAND_PARENT_CLOCK_PRIORITY_1));
			grandParentClockPriority1_ = new JTextField();
			grandParentClockPriority1_.setEnabled(false);
			grandParentClockPriority2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_GRAND_PARENT_CLOCK_PRIORITY_2));
			grandParentClockPriority2_ = new JTextField();
			grandParentClockPriority2_.setEnabled(false);

			gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[] { 100, 200, 100, 200 };
			gridBagLayout.columnWeights = new double[] { 0, 0, 0, 0 };
			gridBagLayout.rowHeights = new int[] { 21, 21, 21, 21, 21, 21, 21 };
			gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0, 0, 0 };

			this.add(clockID);
			this.add(clockID_);
			this.add(clockType);
			this.add(clockType_);
			this.add(parentClockID);
			this.add(parentClockID_);
			this.add(parentCLockPort);
			this.add(parentCLockPort_);
			this.add(hops);
			this.add(hops_);
			this.add(SystemTimeAnd1588Difference);
			this.add(SystemTimeAnd1588Difference_);
			this.add(currentTODStatus);
			this.add(currentTODStatus_);
			this.add(grandParentClockID);
			this.add(grandParentClockID_);
			this.add(grandParentClockType);
			this.add(grandParentClockType_);
			this.add(grandParentClockAccuracy);
			this.add(grandParentClockAccuracy_);
			this.add(grandParentClockVariance);
			this.add(grandParentClockVariance_);
			this.add(grandParentClockPriority1);
			this.add(grandParentClockPriority1_);
			this.add(grandParentClockPriority2);
			this.add(grandParentClockPriority2_);

			this.setBackground(Color.WHITE);
			this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_NE_CLOCK_STATUS)));
			this.setGridBagLayout();/* Tab页一 网元时钟状态/属性 -> 状态布局 */
			this.setLayout(gridBagLayout);
		} catch (Exception e) {

			throw e;
		}
	}

	/**
	 * <p>
	 * Tab页一 网元时钟状态/属性 -> 属性布局
	 * </p>
	 * 
	 * @throws Exception
	 */
	private void setGridBagLayout() throws Exception {

		GridBagConstraints gridBagConstraints = null;
		try {

			gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(5, 10, 0, 10);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.anchor=GridBagConstraints.WEST;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(clockID, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(clockType, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(parentClockID, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(parentCLockPort, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(hops, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(SystemTimeAnd1588Difference, gridBagConstraints);

			gridBagConstraints.insets = new Insets(5, 10, 10, 10);
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 6;
			gridBagLayout.setConstraints(currentTODStatus, gridBagConstraints);
			/** ************************************************************************** */

			gridBagConstraints.insets = new Insets(5, 0, 0, 30);
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(clockID_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(clockType_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(parentClockID_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(parentCLockPort_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(hops_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(SystemTimeAnd1588Difference_, gridBagConstraints);

			gridBagConstraints.insets = new Insets(5, 0, 10, 30);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 6;
			gridBagLayout.setConstraints(currentTODStatus_, gridBagConstraints);
			/** ********************************************************************************* */

			gridBagConstraints.insets = new Insets(5, 150, 0, 0);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.anchor=GridBagConstraints.WEST;
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(grandParentClockID, gridBagConstraints);

			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(grandParentClockType, gridBagConstraints);

			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(grandParentClockAccuracy, gridBagConstraints);

			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(grandParentClockVariance, gridBagConstraints);

			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(grandParentClockPriority1, gridBagConstraints);
			
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(grandParentClockPriority2, gridBagConstraints);
			/******************************************************************************/
			gridBagConstraints.insets = new Insets(5, 10, 0, 10);
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(grandParentClockID_, gridBagConstraints);

			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(grandParentClockType_, gridBagConstraints);

			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(grandParentClockAccuracy_, gridBagConstraints);

			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(grandParentClockVariance_, gridBagConstraints);

			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(grandParentClockPriority1_, gridBagConstraints);
			
			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(grandParentClockPriority2_, gridBagConstraints);
		} catch (Exception e) {

			throw e;
		} finally {

			gridBagConstraints = null;
		}
	}

	public GridBagLayout getGridBagLayout() {
		return gridBagLayout;
	}

	public void setGridBagLayout(GridBagLayout gridBagLayout) {
		this.gridBagLayout = gridBagLayout;
	}

	public JLabel getClockID() {
		return clockID;
	}

	public void setClockID(JLabel clockID) {
		this.clockID = clockID;
	}

	public JTextField getClockID_() {
		return clockID_;
	}

	public void setClockID_(JTextField clockID) {
		clockID_ = clockID;
	}

	public JLabel getClockType() {
		return clockType;
	}

	public void setClockType(JLabel clockType) {
		this.clockType = clockType;
	}

	public JTextField getClockType_() {
		return clockType_;
	}

	public void setClockType_(JTextField clockType) {
		clockType_ = clockType;
	}

	public JLabel getParentClockID() {
		return parentClockID;
	}

	public void setParentClockID(JLabel parentClockID) {
		this.parentClockID = parentClockID;
	}

	public JTextField getParentClockID_() {
		return parentClockID_;
	}

	public void setParentClockID_(JTextField parentClockID) {
		parentClockID_ = parentClockID;
	}

	public JLabel getParentCLockPort() {
		return parentCLockPort;
	}

	public void setParentCLockPort(JLabel parentCLockPort) {
		this.parentCLockPort = parentCLockPort;
	}

	public JTextField getParentCLockPort_() {
		return parentCLockPort_;
	}

	public void setParentCLockPort_(JTextField parentCLockPort) {
		parentCLockPort_ = parentCLockPort;
	}

	public JLabel getHops() {
		return hops;
	}

	public void setHops(JLabel hops) {
		this.hops = hops;
	}

	public JTextField getHops_() {
		return hops_;
	}

	public void setHops_(JTextField hops) {
		hops_ = hops;
	}

	public JLabel getSystemTimeAnd1588Difference() {
		return SystemTimeAnd1588Difference;
	}

	public void setSystemTimeAnd1588Difference(JLabel systemTimeAnd1588Difference) {
		SystemTimeAnd1588Difference = systemTimeAnd1588Difference;
	}

	public JTextField getSystemTimeAnd1588Difference_() {
		return SystemTimeAnd1588Difference_;
	}

	public void setSystemTimeAnd1588Difference_(
			JTextField systemTimeAnd1588Difference) {
		SystemTimeAnd1588Difference_ = systemTimeAnd1588Difference;
	}

	public JLabel getCurrentTODStatus() {
		return currentTODStatus;
	}

	public void setCurrentTODStatus(JLabel currentTODStatus) {
		this.currentTODStatus = currentTODStatus;
	}

	public JTextField getCurrentTODStatus_() {
		return currentTODStatus_;
	}

	public void setCurrentTODStatus_(JTextField currentTODStatus) {
		currentTODStatus_ = currentTODStatus;
	}

	public JLabel getGrandParentClockID() {
		return grandParentClockID;
	}

	public void setGrandParentClockID(JLabel grandParentClockID) {
		this.grandParentClockID = grandParentClockID;
	}

	public JTextField getGrandParentClockID_() {
		return grandParentClockID_;
	}

	public void setGrandParentClockID_(JTextField grandParentClockID) {
		grandParentClockID_ = grandParentClockID;
	}

	public JLabel getGrandParentClockType() {
		return grandParentClockType;
	}

	public void setGrandParentClockType(JLabel grandParentClockType) {
		this.grandParentClockType = grandParentClockType;
	}

	public JTextField getGrandParentClockType_() {
		return grandParentClockType_;
	}

	public void setGrandParentClockType_(JTextField grandParentClockType) {
		grandParentClockType_ = grandParentClockType;
	}

	public JLabel getGrandParentClockAccuracy() {
		return grandParentClockAccuracy;
	}

	public void setGrandParentClockAccuracy(JLabel grandParentClockAccuracy) {
		this.grandParentClockAccuracy = grandParentClockAccuracy;
	}

	public JTextField getGrandParentClockAccuracy_() {
		return grandParentClockAccuracy_;
	}

	public void setGrandParentClockAccuracy_(JTextField grandParentClockAccuracy) {
		grandParentClockAccuracy_ = grandParentClockAccuracy;
	}

	public JLabel getGrandParentClockVariance() {
		return grandParentClockVariance;
	}

	public void setGrandParentClockVariance(JLabel grandParentClockVariance) {
		this.grandParentClockVariance = grandParentClockVariance;
	}

	public JTextField getGrandParentClockVariance_() {
		return grandParentClockVariance_;
	}

	public void setGrandParentClockVariance_(JTextField grandParentClockVariance) {
		grandParentClockVariance_ = grandParentClockVariance;
	}

	public JLabel getGrandParentClockPriority1() {
		return grandParentClockPriority1;
	}

	public void setGrandParentClockPriority1(JLabel grandParentClockPriority1) {
		this.grandParentClockPriority1 = grandParentClockPriority1;
	}

	public JTextField getGrandParentClockPriority1_() {
		return grandParentClockPriority1_;
	}

	public void setGrandParentClockPriority1_(JTextField grandParentClockPriority1) {
		grandParentClockPriority1_ = grandParentClockPriority1;
	}

	public JLabel getGrandParentClockPriority2() {
		return grandParentClockPriority2;
	}

	public void setGrandParentClockPriority2(JLabel grandParentClockPriority2) {
		this.grandParentClockPriority2 = grandParentClockPriority2;
	}

	public JTextField getGrandParentClockPriority2_() {
		return grandParentClockPriority2_;
	}

	public void setGrandParentClockPriority2_(JTextField grandParentClockPriority2) {
		grandParentClockPriority2_ = grandParentClockPriority2;
	}
	
}
