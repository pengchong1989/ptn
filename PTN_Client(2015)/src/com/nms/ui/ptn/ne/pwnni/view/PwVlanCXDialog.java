package com.nms.ui.ptn.ne.pwnni.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.pw.PwNniInfo;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.ne.pwnni.PwVlanMainDialog;

/**
 * pw vlan 陈晓显示界面
 * @author sy
 *
 */
public class PwVlanCXDialog extends PtnPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PwInfo info = null;
	private PwNniInfo pwNniBuffer = null;
	/**
	 * false Z 端
	 * 默认  true  A  端
	 */
	private boolean flag=true;
	private PwVlanMainDialog pwMainPanel;//pwVlan主界面
	/**
	 * 实例化一个新的（ 武汉Vlan界面）的实例
	 * @param info
	 *   PW 对象
   	 * @param flag
	 *  true  A 端
	 *  false Z 端
	 * @throws Exception
	 */
	public PwVlanCXDialog(PwInfo info,boolean flag,PwVlanMainDialog pwMainPanel) throws Exception {
		try {
			this.pwMainPanel=pwMainPanel;
			this.flag=flag;
			this.info = info;
			initComponent();
			setLayout();
			initData();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public void getPwInfo(PwInfo info) {
		

	}
	/**
	 * 初始化数据
	 */
	public void initData(){
		
		if (this.flag) {//A 端
			this.pwNniBuffer = this.info.getaPwNniInfo();// 后期会分A,Z端
		} else {//Z  嗲吗
			this.pwNniBuffer = this.info.getzPwNniInfo();// 后期会分A,Z端
		}
		try {
			super.getComboBoxDataUtil().comboBoxData(cmbExitRule, "exitRule");
			super.getComboBoxDataUtil().comboBoxData(cmbTpID, "LAGVLANTPID");
			if (this.pwNniBuffer != null) {
				super.getComboBoxDataUtil().comboBoxSelect(cmbExitRule, this.pwNniBuffer.getExitRule()+"");
				super.getComboBoxDataUtil().comboBoxSelect(cmbTpID, this.pwNniBuffer.getTpid()+"");
				this.txtSVlan.setText(this.pwNniBuffer.getSvlan());
				this.txtVlanPri.setText(this.pwNniBuffer.getVlanpri());
			} else {
				super.getComboBoxDataUtil().comboBoxData(cmbExitRule, "exitRule");
				super.getComboBoxDataUtil().comboBoxData(cmbTpID, "LAGVLANTPID");
				this.txtSVlan.setText("1");
				this.txtVlanPri.setText("0");
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();// 网格布局
		componentLayout.columnWidths = new int[] {  50, 150};
		componentLayout.columnWeights = new double[] {  0.1, 0.2};
		componentLayout.rowHeights = new int[] { 40, 40, 40, 40,0 };
		componentLayout.rowWeights = new double[] { 0, 0, 0, 0,0.1};
		this.setLayout(componentLayout);

		GridBagConstraints gridCon = new GridBagConstraints();
		gridCon.fill = GridBagConstraints.HORIZONTAL;
		gridCon.insets = new Insets(5, 5, 5, 5);
		gridCon.anchor = GridBagConstraints.CENTER;

		gridCon.gridx = 0;
		gridCon.gridy = 0;
		gridCon.gridheight = 1;
		gridCon.gridwidth = 1;
		componentLayout.setConstraints(this.lblExitRule, gridCon);
		this.add(this.lblExitRule);
		gridCon.gridx = 1;
		componentLayout.setConstraints(this.cmbExitRule, gridCon);
		this.add(this.cmbExitRule);

		gridCon.gridx = 0;
		gridCon.gridy = 1;
		componentLayout.setConstraints(this.lblSVlan, gridCon);
		this.add(this.lblSVlan);
		gridCon.gridx = 1;

		componentLayout.setConstraints(this.txtSVlan, gridCon);
		this.add(this.txtSVlan);

		gridCon.gridx = 0;
		gridCon.gridy = 2;
		componentLayout.setConstraints(this.lblTpID, gridCon);
		this.add(this.lblTpID);
		gridCon.gridx = 1;
		componentLayout.setConstraints(this.cmbTpID, gridCon);
		this.add(this.cmbTpID);

		gridCon.gridx = 0;
		gridCon.gridy = 3;
	
		componentLayout.setConstraints(this.lblSVlanPri, gridCon);
		this.add(this.lblSVlanPri);
		gridCon.gridx = 1;
		
		gridCon.fill = GridBagConstraints.HORIZONTAL;
		gridCon.insets = new Insets(5, 5, 5, 5);
		gridCon.anchor = GridBagConstraints.CENTER;
		componentLayout.setConstraints(this.txtVlanPri, gridCon);
		this.add(this.txtVlanPri);
	}

	private void initComponent() throws Exception {
		this.lblExitRule = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_EXIT_RULE));
		this.cmbExitRule = new JComboBox();
		this.lblSVlan = new JLabel("S-VLan");
		this.txtSVlan = new PtnTextField(true, PtnTextField.TYPE_INT, 4, pwMainPanel.getLblMessage(), pwMainPanel.getBtnSave(), this.pwMainPanel);
		txtSVlan.setCheckingMaxValue(true);
		txtSVlan.setCheckingMinValue(true);
		txtSVlan.setMaxValue(4094);
		txtSVlan.setMinValue(1);
		this.lblTpID = new JLabel("TPID");
		this.cmbTpID = new JComboBox();
		this.lblSVlanPri = new JLabel("S-VLan Pri");
		this.txtVlanPri =  new PtnTextField(true, PtnTextField.TYPE_INT, 1, pwMainPanel.getLblMessage(), pwMainPanel.getBtnSave(), this.pwMainPanel);
		txtVlanPri.setCheckingMaxValue(true);
		txtVlanPri.setCheckingMinValue(true);
		txtVlanPri.setMaxValue(7);
		txtVlanPri.setMinValue(0);
	}

	private JLabel lblExitRule;// 出口规则
	private JLabel lblSVlan;// S-VLan
	private JLabel lblTpID;// TPID
	private JLabel lblSVlanPri;// S-Vlan Pri

	private JComboBox cmbExitRule;
	private PtnTextField txtSVlan;
	private JComboBox cmbTpID;
	private PtnTextField txtVlanPri;

	
	public PwInfo getInfo() {
		return info;
	}

	public void setInfo(PwInfo info) {
		this.info = info;
	}

	public PwNniInfo getPwNniBuffer() {
		return pwNniBuffer;
	}

	public void setPwNniBuffer(PwNniInfo pwNniBuffer) {
		this.pwNniBuffer = pwNniBuffer;
	}

	public JLabel getLblExitRule() {
		return lblExitRule;
	}

	public void setLblExitRule(JLabel lblExitRule) {
		this.lblExitRule = lblExitRule;
	}

	public JLabel getLblSVlan() {
		return lblSVlan;
	}

	public void setLblSVlan(JLabel lblSVlan) {
		this.lblSVlan = lblSVlan;
	}

	public JLabel getLblTpID() {
		return lblTpID;
	}

	public void setLblTpID(JLabel lblTpID) {
		this.lblTpID = lblTpID;
	}

	public JLabel getLblSVlanPri() {
		return lblSVlanPri;
	}

	public void setLblSVlanPri(JLabel lblSVlanPri) {
		this.lblSVlanPri = lblSVlanPri;
	}

	public JComboBox getCmbExitRule() {
		return cmbExitRule;
	}

	public void setCmbExitRule(JComboBox cmbExitRule) {
		this.cmbExitRule = cmbExitRule;
	}

	
	public JComboBox getCmbTpID() {
		return cmbTpID;
	}

	public void setCmbTpID(JComboBox cmbTpID) {
		this.cmbTpID = cmbTpID;
	}

	

	public PtnTextField getTxtSVlan() {
		return txtSVlan;
	}

	public void setTxtSVlan(PtnTextField txtSVlan) {
		this.txtSVlan = txtSVlan;
	}

	public PtnTextField getTxtVlanPri() {
		return txtVlanPri;
	}

	public void setTxtVlanPri(PtnTextField txtVlanPri) {
		this.txtVlanPri = txtVlanPri;
	}

}
