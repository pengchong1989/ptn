package com.nms.ui.ptn.clock.view.cx.frequency;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;

public class TabPanelOneAttrCX extends PtnPanel {

	private static final long serialVersionUID = 790875912258327515L;

	private JLabel enableSelectedProtocol = null;

	private JLabel systemClockWaitingForRecoveryTime = null;

	private JLabel exportClockWaitingForRecoveryTime = null;

	private JLabel operationMode = null;

	private JComboBox enableSelectedProtocol_ = null;

	private JComboBox operationMode_ = null;

	private JSpinner systemClockWaitingForRecoveryTime_ = null;

	private JSpinner exportClockWaitingForRecoveryTime_ = null;

	private SpinnerModel systemClockWaitingForRecoveryTime_spinnerModel = null;

	private SpinnerModel exportClockWaitingForRecoveryTime_spinnerModel = null;

	private JLabel freeOscillationLevel = null;

	private JLabel qualityUnknownReflectLevel = null;

	private JLabel exportClockMode = null;

	private JLabel externalClockSupressThreshold = null;

	private JComboBox freeOscillationLevel_ = null;

	private JComboBox qualityUnknownReflectLevel_ = null;

	private JComboBox exportClockMode_ = null;

	private JComboBox externalClockSupressThreshold_ = null;

	private GridBagLayout gridBagLayout = null;

	public TabPanelOneAttrCX() {

		try {
			
			

			init();
			addItemListenerForCombox();/* 为‘启用选择协议下拉框添加事件’；当为‘优先级’，外时钟压制门限下拉框不可用；当为‘SSM’，可用 */
		} catch (Exception e) {

			ExceptionManage.dispose(e,this.getClass());
		} 
	}

	private void init() throws Exception {

		try {

			/* 数字初始化 */
			systemClockWaitingForRecoveryTime_spinnerModel = new SpinnerNumberModel(5, 0, 12, 1);
			exportClockWaitingForRecoveryTime_spinnerModel = new SpinnerNumberModel(5, 0, 12, 1);
			systemClockWaitingForRecoveryTime_ = new JSpinner(systemClockWaitingForRecoveryTime_spinnerModel);
			exportClockWaitingForRecoveryTime_ = new JSpinner(exportClockWaitingForRecoveryTime_spinnerModel);

			enableSelectedProtocol = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ENABLE_SELECTED_PROTOCOL));
			systemClockWaitingForRecoveryTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SYSTEM_CLOCK_WAITING_FOR_RECOVERY_TIME));
			exportClockWaitingForRecoveryTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_EXPORT_CLOCK_WAITING_FOR_RECOVERY_TIME));
			operationMode = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OPERATION_MODE));
			freeOscillationLevel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_FREE_OSCILLATION_LEVEL));
			qualityUnknownReflectLevel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_QUALITY_UNKNOWN_REFLECT_LEVEL));
			exportClockMode = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_EXPORT_CLOCK_MODE));
			externalClockSupressThreshold = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_EXTERNAL_CLOCK_SUPRESS_THRESHOLD));

			enableSelectedProtocol_ = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.enableSelectedProtocol_, "enableSelectedProtocol");
			
			operationMode_ = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.operationMode_, "operationMode");
			freeOscillationLevel_ = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.freeOscillationLevel_, "freeOscillationLevel");
			qualityUnknownReflectLevel_ = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.qualityUnknownReflectLevel_, "qualityUnknownReflectLevel");
			exportClockMode_ = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.exportClockMode_, "exportClockMode");
			externalClockSupressThreshold_ = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.externalClockSupressThreshold_, "qualityUnknownReflectLevel");
			externalClockSupressThreshold_.setEnabled(false);

			gridBagLayout = new GridBagLayout();
			gridBagLayout.columnWidths = new int[] { 100, 250, 100, 250 };
			gridBagLayout.columnWeights = new double[] { 0, 0, 0, 0 };
			gridBagLayout.rowHeights = new int[] { 21, 21, 21, 21 };
			gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };

			this.setBackground(Color.WHITE);
			this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysObj.STRING_ATTRIBUTE)));
			this.add(enableSelectedProtocol);
			this.add(systemClockWaitingForRecoveryTime);
			this.add(exportClockWaitingForRecoveryTime);
			this.add(operationMode);
			this.add(enableSelectedProtocol_);
			this.add(operationMode_);
			this.add(systemClockWaitingForRecoveryTime_);
			this.add(exportClockWaitingForRecoveryTime_);
			this.add(freeOscillationLevel);
			this.add(qualityUnknownReflectLevel);
			this.add(exportClockMode);
			this.add(externalClockSupressThreshold);
			this.add(freeOscillationLevel_);
			this.add(qualityUnknownReflectLevel_);
			this.add(exportClockMode_);
			this.add(externalClockSupressThreshold_);
			this.setGridBagLayout();/* Tab页一 网元时钟状态/属性 -> 属性布局 */
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
			gridBagConstraints.insets = new Insets(5, 10, 5, 0);
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(enableSelectedProtocol, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(systemClockWaitingForRecoveryTime, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(exportClockWaitingForRecoveryTime, gridBagConstraints);

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(operationMode, gridBagConstraints);

			gridBagConstraints.insets = new Insets(5, 150, 5, 0);
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(freeOscillationLevel, gridBagConstraints);

			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(qualityUnknownReflectLevel, gridBagConstraints);

			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(exportClockMode, gridBagConstraints);

			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(externalClockSupressThreshold, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.insets = new Insets(5, 10, 5, 0);
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagLayout.setConstraints(enableSelectedProtocol_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(systemClockWaitingForRecoveryTime_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(exportClockWaitingForRecoveryTime_, gridBagConstraints);

			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(operationMode_, gridBagConstraints);

			gridBagConstraints.insets = new Insets(5, 10, 5, 10);
			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(freeOscillationLevel_, gridBagConstraints);

			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(qualityUnknownReflectLevel_, gridBagConstraints);

			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(exportClockMode_, gridBagConstraints);

			gridBagConstraints.gridx = 3;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(externalClockSupressThreshold_, gridBagConstraints);
		} catch (Exception e) {

			throw e;
		} finally {

			gridBagConstraints = null;
		}
	}

	/**
	 * <p>
	 * 为‘启用选择协议下拉框添加事件’；当为‘优先级’，外时钟压制门限下拉框不可用；当为‘SSM’，可用
	 * </p>
	 * 
	 * @throws Exception
	 */
	private void addItemListenerForCombox() throws Exception {

		try {

			enableSelectedProtocol_.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent itemEvent) {

					String name=null;
					if (itemEvent.getStateChange() == 1) {
						try {
							
							name = ((ControlKeyValue)itemEvent.getItem()).getName();
							if("SSM".equalsIgnoreCase(name)){
								externalClockSupressThreshold_.setEnabled(true);
								
							}else{
								getComboBoxDataUtil().comboBoxSelectByValue(externalClockSupressThreshold_,"0");
								externalClockSupressThreshold_.setEnabled(false);
							}
						} catch (Exception e) {
							ExceptionManage.dispose(e,this.getClass());
						}
					}
				}
			});
		} catch (Exception e) {

			throw e;
		}
	}

	public JComboBox getEnableSelectedProtocol_() {
		return enableSelectedProtocol_;
	}

	public void setEnableSelectedProtocol_(JComboBox enableSelectedProtocol) {
		enableSelectedProtocol_ = enableSelectedProtocol;
	}

	public JComboBox getOperationMode_() {
		return operationMode_;
	}

	public void setOperationMode_(JComboBox operationMode) {
		operationMode_ = operationMode;
	}

	public JSpinner getSystemClockWaitingForRecoveryTime_() {
		return systemClockWaitingForRecoveryTime_;
	}

	public void setSystemClockWaitingForRecoveryTime_(
			JSpinner systemClockWaitingForRecoveryTime) {
		systemClockWaitingForRecoveryTime_ = systemClockWaitingForRecoveryTime;
	}

	public JSpinner getExportClockWaitingForRecoveryTime_() {
		return exportClockWaitingForRecoveryTime_;
	}

	public void setExportClockWaitingForRecoveryTime_(
			JSpinner exportClockWaitingForRecoveryTime) {
		exportClockWaitingForRecoveryTime_ = exportClockWaitingForRecoveryTime;
	}

	public JComboBox getFreeOscillationLevel_() {
		return freeOscillationLevel_;
	}

	public void setFreeOscillationLevel_(JComboBox freeOscillationLevel) {
		freeOscillationLevel_ = freeOscillationLevel;
	}

	public JComboBox getQualityUnknownReflectLevel_() {
		return qualityUnknownReflectLevel_;
	}

	public void setQualityUnknownReflectLevel_(JComboBox qualityUnknownReflectLevel) {
		qualityUnknownReflectLevel_ = qualityUnknownReflectLevel;
	}

	public JComboBox getExportClockMode_() {
		return exportClockMode_;
	}

	public void setExportClockMode_(JComboBox exportClockMode) {
		exportClockMode_ = exportClockMode;
	}

	public JComboBox getExternalClockSupressThreshold_() {
		return externalClockSupressThreshold_;
	}

	public void setExternalClockSupressThreshold_(
			JComboBox externalClockSupressThreshold) {
		externalClockSupressThreshold_ = externalClockSupressThreshold;
	}

	public SpinnerModel getSystemClockWaitingForRecoveryTime_spinnerModel() {
		return systemClockWaitingForRecoveryTime_spinnerModel;
	}

	public void setSystemClockWaitingForRecoveryTime_spinnerModel(
			SpinnerModel systemClockWaitingForRecoveryTimeSpinnerModel) {
		systemClockWaitingForRecoveryTime_spinnerModel = systemClockWaitingForRecoveryTimeSpinnerModel;
	}

	public SpinnerModel getExportClockWaitingForRecoveryTime_spinnerModel() {
		return exportClockWaitingForRecoveryTime_spinnerModel;
	}

	public void setExportClockWaitingForRecoveryTime_spinnerModel(
			SpinnerModel exportClockWaitingForRecoveryTimeSpinnerModel) {
		exportClockWaitingForRecoveryTime_spinnerModel = exportClockWaitingForRecoveryTimeSpinnerModel;
	}
	
}
