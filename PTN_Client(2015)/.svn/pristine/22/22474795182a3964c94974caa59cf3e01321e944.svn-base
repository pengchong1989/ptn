package com.nms.ui.ptn.ne.pwnni.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.pw.PwNniInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnFileChooser;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.ne.pwnni.PwVlanMainDialog;

/**
 * 武汉网元 pw的VLAN配置界面
 * @author sy
 *
 */
public class PwVlanWHDialog extends PtnPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7274852051432661504L;
	
	private PwInfo info = null;//pw 对象
	private PwVlanMainDialog pwMainPanel;//pwVlan主界面
	/**
	 * false Z 端
	 * 默认  true  A  端
	 */
	private boolean flag=true;
	private PwNniInfo pwNniBuffer = null;
	/**
	 * 实例化一个新的（ 武汉Vlan界面）的实例
	 * @param info
	 *   PW 对象
   	 * @param flag
	 *  true  A 端
	 *  false Z 端
	 * @throws Exception
	 */
	public PwVlanWHDialog(PwInfo info,boolean flag,PwVlanMainDialog pwMainPanel) throws Exception {
		try {
			this.pwMainPanel = pwMainPanel;
			this.flag=flag;
			this.info = info;
			initComponent();
			setLayout();
			this.addListener();
			initData();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 添加监听
	 */
	private void addListener() {
		this.cmbExitRule.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == 1){
					int id = ((Code)((ControlKeyValue)e.getItem()).getObject()).getId();
					if(id == 567 || id == 568){
						lblVlan_Pri.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_REPLACE_VLAN_PRI_UP));//上话替换VLAN PRI
						lblVlan_Id.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_REPLACE_VLAN_ID_UP));//上话替换VLAN ID
					}else{
						lblVlan_Pri.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_ADD_VLANPRI_UP));
						lblVlan_Id.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_ADD_VLANID_UP));
					}
				}
			}
		});
		
	}
	/**
	 * 初始化组件
	 * @throws Exception
	 */
	private void initComponent() throws Exception  {
		this.lblVC_Enabled = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CONTROLENABLE));//控制字使能
		this.lblVlan_Id = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ADD_VLANID_UP));//"上话增加VLAN ID"
		this.lblLevelDivision = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_HORIZONAL));//"水平分割"
		this.lblVlan_Pri = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ADD_VLANPRI_UP));//"上话增加VLAN PRI"
		this.lblTag = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TAG_DOWM_WUHAN));//"下话TAG识别"
		this.lblMac = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MAC_LEARN));//"MAC地址学习"
		this.lblExitRule = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TAG_ACTION_WUHAN));//"上话tag行为"
		
		this.cmbVC_Enabled = new JComboBox();  
		super.getComboBoxDataUtil().comboBoxData(this.cmbVC_Enabled, "ENABLEDSTATUE");
		this.cmbLevelDivision = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbLevelDivision, "VCTRAFFICPOLICING");
		this.cmbTag = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbTag, "TAGRECOGNITION");
		this.cmbMac = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbMac, "MACLEARN");
		this.cmbExitRule = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.cmbExitRule, "PORTTAGBEHAVIOR");
		
		this.txtVlan_Id = new PtnTextField(true,PtnTextField.TYPE_INT,PtnTextField.INT_MAXLENGTH, pwMainPanel.getLblMessage(), pwMainPanel.getBtnSave(),this.pwMainPanel);
		txtVlan_Id.setCheckingMaxValue(true);
		txtVlan_Id.setCheckingMinValue(true);
		txtVlan_Id.setMaxValue(4095);
		txtVlan_Id.setMinValue(1);
		this.txtVlan_Pri =  new PtnTextField(true,PtnTextField.TYPE_INT,1, pwMainPanel.getLblMessage(), pwMainPanel.getBtnSave(),this.pwMainPanel);
		txtVlan_Pri.setCheckingMaxValue(true);
		txtVlan_Pri.setCheckingMinValue(true);
		txtVlan_Pri.setMaxValue(7);
		txtVlan_Pri.setMinValue(0);
	}
	/**
	 * 设置主界面布局
	 * @throws Exception
	 */
	private void setLayout() throws Exception{
		GridBagLayout componentLayout = new GridBagLayout();// 网格布局
		componentLayout.columnWidths = new int[] {25,40,5,25,40};
		componentLayout.columnWeights = new double[] { 0, 0.1, 0, 0, 0.2 };
		componentLayout.rowHeights = new int[] { 40, 40, 40, 40,10};
		componentLayout.rowWeights = new double[] {0,0,0,0,0.1};
		this.setLayout(componentLayout);
		
		GridBagConstraints gridCon = new GridBagConstraints();
		gridCon.fill = GridBagConstraints.HORIZONTAL;
		gridCon.insets = new Insets(5, 5, 5, 5);
		gridCon.anchor = GridBagConstraints.CENTER;
		
		//第一行
		gridCon.gridx = 0;
		gridCon.gridy = 0;
		componentLayout.setConstraints(this.lblVC_Enabled, gridCon);
		this.add(this.lblVC_Enabled);
		gridCon.gridx = 1;
		componentLayout.setConstraints(this.cmbVC_Enabled, gridCon);
		this.add(this.cmbVC_Enabled);
		
		gridCon.gridx = 3;
		componentLayout.setConstraints(this.lblVlan_Id, gridCon);
		this.add(this.lblVlan_Id);
		gridCon.gridx = 4;
		componentLayout.setConstraints(this.txtVlan_Id, gridCon);
		this.add(this.txtVlan_Id);
		
		//第二行
		gridCon.gridx = 0;
		gridCon.gridy = 1;
		componentLayout.setConstraints(this.lblLevelDivision, gridCon);
		this.add(this.lblLevelDivision);
		gridCon.gridx = 1;
		componentLayout.setConstraints(this.cmbLevelDivision, gridCon);
		this.add(this.cmbLevelDivision);
		
		gridCon.gridx = 3;
		componentLayout.setConstraints(this.lblVlan_Pri, gridCon);
		this.add(this.lblVlan_Pri);
		gridCon.gridx = 4;
		componentLayout.setConstraints(this.txtVlan_Pri, gridCon);
		this.add(this.txtVlan_Pri);
		
		//第三行
		gridCon.gridx = 0;
		gridCon.gridy = 2;
		componentLayout.setConstraints(this.lblTag, gridCon);
		this.add(this.lblTag);
		gridCon.gridx = 1;
		componentLayout.setConstraints(this.cmbTag, gridCon);
		this.add(this.cmbTag);
		
		gridCon.gridx = 3;
		componentLayout.setConstraints(this.lblMac, gridCon);
		this.add(this.lblMac);
		gridCon.gridx = 4;
		componentLayout.setConstraints(this.cmbMac, gridCon);
		this.add(this.cmbMac);
		
		//第四行
		gridCon.gridx = 0;
		gridCon.gridy = 3;
		componentLayout.setConstraints(this.lblExitRule, gridCon);
		this.add(this.lblExitRule);
		gridCon.gridx = 1;
		
		componentLayout.setConstraints(this.cmbExitRule, gridCon);
		this.add(this.cmbExitRule);

	}
	/**
	 * 初始化数据
	 * @throws Exception
	 */
	private void initData()throws Exception {
		if (this.flag) {//A
			this.pwNniBuffer = this.info.getaPwNniInfo();// 后期会分A,Z端
		} else {//z
			this.pwNniBuffer = this.info.getzPwNniInfo();// 后期会分A,Z端
		}		
		try {
			if(this.pwNniBuffer!=null){
				super.getComboBoxDataUtil().comboBoxSelect(cmbVC_Enabled,pwNniBuffer.getControlEnable()+"");
				super.getComboBoxDataUtil().comboBoxSelect(cmbLevelDivision,pwNniBuffer.getHorizontalDivision()+"");
				super.getComboBoxDataUtil().comboBoxSelect(cmbTag,pwNniBuffer.getTagAction()+"");
				super.getComboBoxDataUtil().comboBoxSelect(cmbMac,pwNniBuffer.getMacAddressLearn()+"");
				super.getComboBoxDataUtil().comboBoxSelect(cmbExitRule,pwNniBuffer.getExitRule()+"");
				
				if("".equals(pwNniBuffer.getSvlan())||null==pwNniBuffer.getSvlan()){
					txtVlan_Id.setText("0");
				}else{
					txtVlan_Id.setText(pwNniBuffer.getSvlan());
				}
				if("".equals(pwNniBuffer.getVlanpri()) || null==pwNniBuffer.getVlanpri()){
					txtVlan_Pri.setText("0");
				}else{
					txtVlan_Pri.setText(pwNniBuffer.getVlanpri());
				}
			}		
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 提取 页面信息
	 * @param pwNniInfo
	 * @return
	 * @throws Exception
	 */
	public PwNniInfo getPwNniInfo(PwNniInfo	pwNniInfo)throws Exception {
		
		if(null==pwNniInfo){
			throw new Exception("pwNniInfo is null");
		}
		
		ControlKeyValue keyValue = null;
		
		try {
			keyValue = (ControlKeyValue)this.cmbVC_Enabled.getSelectedItem();
			pwNniBuffer.setControlEnable(Integer.parseInt(keyValue.getId()));
			keyValue = (ControlKeyValue)this.cmbLevelDivision.getSelectedItem();
			pwNniBuffer.setHorizontalDivision(Integer.parseInt(keyValue.getId()));
			keyValue = (ControlKeyValue)this.cmbTag.getSelectedItem();
			pwNniBuffer.setTagAction(Integer.parseInt(keyValue.getId()));
			keyValue = (ControlKeyValue)this.cmbMac.getSelectedItem();
			pwNniBuffer.setMacAddressLearn(Integer.parseInt(keyValue.getId()));
			keyValue = (ControlKeyValue)this.cmbExitRule.getSelectedItem();
			pwNniBuffer.setExitRule(Integer.parseInt(keyValue.getId()));
			pwNniBuffer.setSvlan(txtVlan_Id.getText());
			pwNniBuffer.setVlanpri(txtVlan_Pri.getText());
			keyValue = null;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			keyValue = null;
		}
		return pwNniBuffer;
	}
		
	private JLabel lblVC_Enabled;// vc流量监管使能
	private JLabel lblVlan_Id;// 下话增加VLAN ID
	private JLabel lblLevelDivision;// 水平分割
	private JLabel lblVlan_Pri;// 下话增加VLAN PRI
	private JLabel lblTag;// TAG识别
	private JLabel lblMac;// MAC地址学习
	private JLabel lblExitRule;// 出口规则

	private JComboBox cmbVC_Enabled;//vc流量监管使能下拉框
	private JComboBox cmbLevelDivision;//水平分割下拉框
	private JComboBox cmbTag;//tag识别下拉框
	private JComboBox cmbMac;//mac地址学习下拉框
	private JComboBox cmbExitRule;//出口规则下拉框
	private PtnTextField txtVlan_Id;//VlanId文本框
	private PtnTextField txtVlan_Pri;//VlanPri文本框

}
