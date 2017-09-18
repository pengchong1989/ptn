package com.nms.ui.ptn.ne.eth.view.dialog.base;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.db.enums.EManufacturer;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.control.PtnSpinner;
import com.nms.ui.manager.keys.StringKeysLbl;

/**
 * 
 * 项目名称：WuHanPTN2012 类名称：PortEthDialog 类描述： 端口基本属性基类 创建人：kk 创建时间：2013-7-15
 * 上午11:54:21 修改人：kk 修改时间：2013-7-15 上午11:54:21 修改备注：
 * 
 * @version
 * 
 */
public class PortEthDialog extends PtnPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5850425168219490029L;
	protected GridBagLayout componentLayout = new GridBagLayout();// 网格布局
	protected GridBagConstraints gridCon = new GridBagConstraints();
	
	/**
	 * 创建一个新的实例
	 */
	public PortEthDialog() {
		try {
			this.initComponents();
			this.setLayout();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 初始化控件
	 * 
	 * @throws Exception
	 */
	private void initComponents() throws Exception {
		this.labName = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_NAME));
		this.txtName = new JTextField();
		this.labStatus = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ENABLED_STATUS));
		this.cmbStatus = new JComboBox();
		this.labType = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_TYPE));
		this.cmbType = new JComboBox();
		this.labFrame = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MAX_FRAME_WORDS));
		this.txtFrame = new JTextField();
		this.labFluid = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_802_3));
		this.cmbFluid = new JComboBox();
		this.chbExitSpeed = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_EXPORT_SPEED_LIMIT));
		txtName.setEditable(false);
		super.getComboBoxDataUtil().comboBoxData(this.cmbType, "PORTTYPE");
		super.getComboBoxDataUtil().comboBoxData(this.cmbFluid, "ENABLEDSTATUE");
	}

	/**
	 * 设置布局
	 */
	private void setLayout() throws Exception {
		SiteService_MB siteService = null;
		
		try {
			componentLayout.columnWidths = new int[] { 5, 40, 170, 30, 40, 170, 5 };
			componentLayout.columnWeights = new double[] { 0, 0, 0, 0, 0 };
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if (siteService.getManufacturer(ConstantUtil.siteId) == EManufacturer.WUHAN.getValue()) {
				componentLayout.rowHeights = new int[] { 15, 40, 40, 40, 40, 40,40,30};
				componentLayout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0,0.2};
			} else {
				componentLayout.rowHeights = new int[] { 15, 30, 30, 30, 30, 30, 30, 30, 30 };
				componentLayout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0.2 };
			}
			this.setLayout(componentLayout);
			
			gridCon.fill = GridBagConstraints.HORIZONTAL;
			gridCon.anchor = GridBagConstraints.CENTER;
			gridCon.insets = new Insets(5, 5, 5, 5);
			
			// 第一行
			gridCon.gridx = 1;
			gridCon.gridy = 1;
			componentLayout.setConstraints(this.labName, gridCon);
			this.add(this.labName);
			gridCon.gridx = 2;
			componentLayout.setConstraints(this.txtName, gridCon);
			this.add(this.txtName);
			
			gridCon.gridx = 4;
			componentLayout.setConstraints(this.labStatus, gridCon);
			this.add(this.labStatus);
			gridCon.gridx = 5;
			componentLayout.setConstraints(this.cmbStatus, gridCon);
			this.add(this.cmbStatus);
			
			// 第二行
			gridCon.gridx = 1;
			gridCon.gridy = 2;
			componentLayout.setConstraints(this.labType, gridCon);
			this.add(this.labType);
			gridCon.gridx = 2;
			componentLayout.setConstraints(this.cmbType, gridCon);
			this.add(this.cmbType);
			
			gridCon.gridx = 4;
			componentLayout.setConstraints(this.labFrame, gridCon);
			this.add(this.labFrame);
			gridCon.gridx = 5;
			componentLayout.setConstraints(this.txtFrame, gridCon);
			this.add(this.txtFrame);
			// 第三行
			gridCon.gridx = 1;
			gridCon.gridy = 3;
			componentLayout.setConstraints(this.labFluid, gridCon);
			this.add(this.labFluid);
			gridCon.gridx = 2;
			componentLayout.setConstraints(this.cmbFluid, gridCon);
			this.add(this.cmbFluid);
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
	}
	
	/**
	 * 布局管理
	 * @param panel
	 * @param component
	 * @param gridx
	 * @param gridy
	 * @param gridBagConstraints
	 * @throws Exception
	 */
	protected void addComponent(JPanel panel, JComponent component, int gridx, int gridy, GridBagConstraints gridBagConstraints) throws Exception {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		componentLayout.setConstraints(component, gridCon);
		this.add(component);
	}

	private JLabel labName;// 端口名称
	private JLabel labStatus;// 使能状态
	private JLabel labType;// 端口类型
	private JTextField txtName;// 端口名称文本框
	private JComboBox cmbStatus;// 使能状态下拉列表
	private JComboBox cmbType;// 端口类型下拉列表
	private JLabel labFrame;// 最大帧长
	private JLabel labFluid;// 流控
	private JCheckBox chbExitSpeed;// 出口限速
	private JTextField txtFrame;// 最大帧长文本框
	private JComboBox cmbFluid;// 流控下拉列表
	protected PtnSpinner spnExitSpeed;// 出口限速

	public JTextField getTxtName() {
		return txtName;
	}

	public void setTxtName(JTextField txtName) {
		this.txtName = txtName;
	}

	public JComboBox getCmbStatus() {
		return cmbStatus;
	}

	public void setCmbStatus(JComboBox cmbStatus) {
		this.cmbStatus = cmbStatus;
	}

	public JComboBox getCmbType() {
		return cmbType;
	}

	public void setCmbType(JComboBox cmbType) {
		this.cmbType = cmbType;
	}

	public JTextField getTxtFrame() {
		return txtFrame;
	}

	public void setTxtFrame(JTextField txtFrame) {
		this.txtFrame = txtFrame;
	}

	public JComboBox getCmbFluid() {
		return cmbFluid;
	}

	public void setCmbFluid(JComboBox cmbFluid) {
		this.cmbFluid = cmbFluid;
	}

	public JCheckBox getChbExitSpeed() {
		return chbExitSpeed;
	}

	public void setChbExitSpeed(JCheckBox chbExitSpeed) {
		this.chbExitSpeed = chbExitSpeed;
	}

	public PtnSpinner getSpnExitSpeed() {
		return spnExitSpeed;
	}

	public void setSpnExitSpeed(PtnSpinner spnExitSpeed) {
		this.spnExitSpeed = spnExitSpeed;
	}
}
