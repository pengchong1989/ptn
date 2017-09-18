package com.nms.ui.ptn.oam.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.ptn.oam.view.dialog.OamInfoDialog;

/**
 * OAM基本信息
 * @author dzy
 *
 */
public class OamBasicPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1671523470920047670L;
	/**
	 * 每行的行高
	 */
	private final int LINEHEIGHT = 30;
	/**
	 * 主界面传入的bean
	 */
	private List<OamInfo> oamInfoList = null;
	
	private OamInfoDialog oamInfoDialog; //主页面

	/**
	 * 创建一个新的实例
	 * 
	 * @param oamInfo
	 *            bean对象。 当为null时 为新建操作
	 * 
	 */
	public OamBasicPanel(OamInfoDialog oamInfoDialog,List<OamInfo> oamInfoList) {

		try {
			this.oamInfoList = oamInfoList;
			this.oamInfoDialog = oamInfoDialog;
			this.initComponent();
			this.setLayout();
			this.addListener();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	private void addListener() {
		this.chbCvCircleEnbled.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(chbCvCircleEnbled.isSelected()){
					cmbCvCircle.setEnabled(true);
				}else{
					cmbCvCircle.setEnabled(false);
				}
			}
		});
	}

	/**
	 * 初始化控件
	 * 
	 * @throws Exception
	 */
	private void initComponent() throws Exception {
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTab.TAB_BASIC_INFO)));
		lblMessage = new JLabel();
		this.lblName = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
		this.lblMegLvl = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_MEG_LEVLE));
		this.lblCvCircleEnbled = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_CONNECT_TEST));
		this.lblCvCircle = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_CONNECT_TEST_PERIOD));

		this.cmbName = new JComboBox();
		this.txtMegLvl = new PtnTextField(true, PtnTextField.TYPE_INT, 7, this.lblMessage, this.oamInfoDialog.getBtnSave(), this.oamInfoDialog);
		this.txtMegLvl.setText("7");
		this.txtMegLvl.setCheckingMaxValue(true);
		this.txtMegLvl.setMaxValue(7);
		this.txtMegLvl.setCheckingMinValue(true);
		this.txtMegLvl.setMinValue(0);
		
		this.chbCvCircleEnbled = new JCheckBox();
		this.cmbCvCircle = new JComboBox();
	}

	/**
	 * 设置布局
	 */
	private void setLayout() {

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 110, 250};
		componentLayout.columnWeights = new double[] { 0, 0.1,0, 0.1 };
		componentLayout.rowHeights = new int[] {20, LINEHEIGHT, LINEHEIGHT, LINEHEIGHT, LINEHEIGHT };
		componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0};
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 5, 0, 5);

		// 第0行 提示信息
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.lblMessage, c);
		this.add(this.lblMessage);
				
		// 第1行 名称
		c.gridx = 0;
		c.gridy = 1;
		componentLayout.setConstraints(this.lblName, c);
		this.add(this.lblName);
		c.gridx = 1;
		componentLayout.setConstraints(this.cmbName, c);
		this.add(this.cmbName);

		// 第2行 MEG等级
		c.gridx = 0;
		c.gridy = 2;
		componentLayout.setConstraints(this.lblMegLvl, c);
		this.add(this.lblMegLvl);
		c.gridx = 1;
		componentLayout.setConstraints(this.txtMegLvl, c);
		this.add(this.txtMegLvl);
				

		// 第3行 连通性检查周期使能
		c.gridx = 0;
		c.gridy = 3;
		componentLayout.setConstraints(this.lblCvCircleEnbled, c);
		this.add(this.lblCvCircleEnbled);
		c.gridx = 1;
		componentLayout.setConstraints(this.chbCvCircleEnbled, c);
		this.add(this.chbCvCircleEnbled);
				
		// 第4行 连通性检查周期
		c.gridx = 0;
		c.gridy = 4;
		componentLayout.setConstraints(this.lblCvCircle, c);
		this.add(this.lblCvCircle);
		c.gridx = 1;
		componentLayout.setConstraints(this.cmbCvCircle, c);
		this.add(this.cmbCvCircle);
	}

	/**
	 * 获取页面信息 
	 */
	public  void getBasicOam(List<OamInfo> oamInfoList){
		if(null!=oamInfoList&&oamInfoList.size()>0){
			for (OamInfo oamInfo : oamInfoList) {
				if(null!=oamInfo.getOamMep()){
					oamInfo.getOamMep().setMel(Integer.parseInt(this.txtMegLvl.getText()));
					oamInfo.getOamMep().setCv(this.chbCvCircleEnbled.isSelected());
					oamInfo.getOamMep().setCvCycle(Integer.parseInt(((ControlKeyValue)this.cmbCvCircle.getSelectedItem()).getId()));
				}
			}
		}
	}
	
	private JLabel lblMessage; // 提示信息
	private JLabel lblName; // 名称label
	private JComboBox cmbName; // 名称
	private JLabel lblMegLvl; // MEG等级label
	private PtnTextField txtMegLvl;// MEG等级
	private JLabel lblCvCircleEnbled; // 连通性检查周期使能label
	private JCheckBox chbCvCircleEnbled; // 连通性检查周期使能
	private JLabel lblCvCircle; // 连通性检查周期(ms)label
	private JComboBox cmbCvCircle; // 连通性检查周期(ms)


	public PtnTextField getTxtMegLvl() {
		return txtMegLvl;
	}

	public JCheckBox getChbCvCircleEnbled() {
		return chbCvCircleEnbled;
	}

	public void setChbCvCircleEnbled(JCheckBox chbCvCircleEnbled) {
		this.chbCvCircleEnbled = chbCvCircleEnbled;
	}

	public JComboBox getCmbCvCircle() {
		return cmbCvCircle;
	}

	public void setCmbCvCircle(JComboBox cmbCvCircle) {
		this.cmbCvCircle = cmbCvCircle;
	}


	public void setTxtMegLvl(PtnTextField txtMegLvl) {
		this.txtMegLvl =  txtMegLvl;
	}

	public JLabel getLblMessage() {
		return lblMessage;
	}

	public void setLblMessage(JLabel lblMessage) {
		this.lblMessage = lblMessage;
	}

	public JComboBox getCmbName() {
		return cmbName;
	}

	public void setCmbName(JComboBox cmbName) {
		this.cmbName = cmbName;
	}
}
