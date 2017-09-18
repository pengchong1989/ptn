package com.nms.ui.ptn.ne.eth.view.dialog.cx;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortUniAttr;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.ne.eth.view.dialog.base.PortUniDialog;

/**
*    
* 项目名称：WuHanPTN2012   
* 类名称：PortUniCXDialog   
* 类描述：   端口UNI属性晨晓界面
* 创建人：kk   
* 创建时间：2013-7-15 上午11:53:54   
* 修改人：kk   
* 修改时间：2013-7-15 上午11:53:54   
* 修改备注：   
* @version    
*
 */
public class PortUniCXDialog extends PortUniDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6853077107037850183L;
	private JPanel upPanel;
	private JPanel downPanel;
	private PortInst portInst;
	
	/**
	 * 创建一个新的实例
	 * @param portInst
	 * 			端口
	 */
	public PortUniCXDialog(PortInst portInst) throws Exception{
		try{
		this.portInst=portInst;
		initComponent();
		setUpLayout();
		setDownLayout();
		setLayout();
		addListener();
		if(null!=portInst){
			initData();
			}
		}catch(Exception e){
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 仅用于显示，不能更改数据
	 * @throws Exception
	 */
	public void lock()throws Exception {
		try {
			cmbEthernetPackaging.setEnabled(false);
			cmbVlanTpId.setEnabled(false);
			cmbOuterVlanTpId.setEnabled(false);
			cmbVlanMode.setEnabled(false);
			txtBroadcast.setEditable(false);
			txtUnicast.setEditable(false);
			txtMulticast.setEditable(false);
			txtVlanId.setEditable(false);
			txtVlanPri.setEditable(false);
			lblBroadcast.setEnabled(false);
			lblMulticast.setEnabled(false);
			lblUnicast.setEnabled(false);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	/**
	 * 添加监听
	 */
	private void addListener() {	
		if(this.lblBroadcast.isSelected()){
			txtBroadcast.setEnabled(true);
		}else{
			txtBroadcast.setEnabled(false);
		}
		if(this.lblUnicast.isSelected()){
			txtUnicast.setEnabled(true);
		}else{
			txtUnicast.setEnabled(false);
		}
		if(this.lblMulticast.isSelected()){
			txtMulticast.setEnabled(true);
		}else{
			txtMulticast.setEnabled(false);
		}
		this.lblBroadcast.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				if(evt.getStateChange()==1){
					txtBroadcast.setEnabled(true);
					txtBroadcast.setText("15000000");
				}else{
					txtBroadcast.setEnabled(false);
					txtBroadcast.setText("");
				}
			}
		});
		this.lblUnicast.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				if(evt.getStateChange()==1){
					txtUnicast.setEnabled(true);
					txtUnicast.setText("15000000");
				}else{
					txtUnicast.setEnabled(false);
					txtUnicast.setText("");
				}
			}
		});
		this.lblMulticast.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				if(evt.getStateChange()==1){
					txtMulticast.setEnabled(true);
					txtMulticast.setText("15000000");
				}else{
					txtMulticast.setEnabled(false);
					txtMulticast.setText("");
				}
			}
		});
	}
	
	/**
	 * 初始化数据
	 */	
	private void initData(){
		PortUniAttr portUniAttr=portInst.getPortAttr().getPortUniAttr();
		super.getComboBoxDataUtil().comboBoxSelect(cmbEthernetPackaging,portUniAttr.getEthernetPackaging()+"");
		super.getComboBoxDataUtil().comboBoxSelect(cmbVlanTpId, portUniAttr.getVlanTpId()+"");
		super.getComboBoxDataUtil().comboBoxSelect(cmbOuterVlanTpId, portUniAttr.getOuterVlanTpId()+"");
		super.getComboBoxDataUtil().comboBoxSelect(cmbVlanMode, portUniAttr.getVlanMode()+"");
		this.lblBroadcast.setSelected(portUniAttr.getIsBroadcast()== 1?true:false);
		this.lblUnicast.setSelected(portUniAttr.getIsUnicast()==1?true:false);
		this.lblMulticast.setSelected(portUniAttr.getIsMulticast()==1?true:false);
		this.txtVlanId.setText(portUniAttr.getVlanId());
		this.txtVlanPri.setText(portUniAttr.getVlanPri());
		this.txtBroadcast.setText(portUniAttr.getBroadcast());
		this.txtMulticast.setText(portUniAttr.getMulticast());
		this.txtUnicast.setText(portUniAttr.getUnicast());
	}
	
	/*设置上面布局*/
	private void setUpLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 100,230,20,100,230 };
		componentLayout.columnWeights = new double[] { 0, 0,0,0 };
		componentLayout.rowHeights = new int[] { 20,20,20,20 };
		componentLayout.rowWeights = new double[] { 0,0,0,0 };
		this.upPanel.setLayout(componentLayout);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.anchor = GridBagConstraints.CENTER;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblEthernetPackaging, c);
		this.upPanel.add(this.lblEthernetPackaging);
		
		c.gridx = 1;
		componentLayout.setConstraints(this.cmbEthernetPackaging, c);
		this.upPanel.add(this.cmbEthernetPackaging);
		
		c.gridx = 3;
		componentLayout.setConstraints(this.lblVlanId, c);
		this.upPanel.add(this.lblVlanId);
		
		c.gridx = 4;
		componentLayout.setConstraints(this.txtVlanId, c);
		this.upPanel.add(this.txtVlanId);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblVlanTpId, c);
		this.upPanel.add(this.lblVlanTpId);
		
		c.gridx = 1;
		componentLayout.setConstraints(this.cmbVlanTpId, c);
		this.upPanel.add(this.cmbVlanTpId);
		
		c.gridx = 3;
		componentLayout.setConstraints(this.lblVlanPri, c);
		this.upPanel.add(this.lblVlanPri);
		
		c.gridx = 4;
		componentLayout.setConstraints(this.txtVlanPri, c);
		this.upPanel.add(this.txtVlanPri);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblOuterVlanTpId, c);
		this.upPanel.add(this.lblOuterVlanTpId);
		
		c.gridx = 1;
		componentLayout.setConstraints(this.cmbOuterVlanTpId, c);
		this.upPanel.add(this.cmbOuterVlanTpId);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblVlanMode, c);
		this.upPanel.add(this.lblVlanMode);
		
		c.gridx = 1;
		componentLayout.setConstraints(this.cmbVlanMode, c);
		this.upPanel.add(this.cmbVlanMode);
	}
	
	/*设置下面布局*/
	private void setDownLayout() {
		
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 100,230 ,330};
		componentLayout.columnWeights = new double[] { 0, 0,0};
		componentLayout.rowHeights = new int[] { 20,20,20};
		componentLayout.rowWeights = new double[] { 0,0,0 };
		this.downPanel.setLayout(componentLayout);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.anchor = GridBagConstraints.CENTER;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblBroadcast, c);
		this.downPanel.add(this.lblBroadcast);
		
		c.gridx=1;
		componentLayout.setConstraints(this.txtBroadcast, c);
		this.downPanel.add(this.txtBroadcast);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblUnicast, c);
		this.downPanel.add(this.lblUnicast);
		
		c.gridx=1;
		componentLayout.setConstraints(this.txtUnicast, c);
		this.downPanel.add(this.txtUnicast);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblMulticast, c);
		this.downPanel.add(this.lblMulticast);
		
		c.gridx=1;
		componentLayout.setConstraints(this.txtMulticast, c);
		this.downPanel.add(this.txtMulticast);
		this.downPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_MES_CONTROL)));
	}
	
	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 640 };
		componentLayout.columnWeights = new double[] { 0 ,0};
		componentLayout.rowHeights = new int[] { 140,140 };
		componentLayout.rowWeights = new double[] { 0};
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.upPanel, c);
		this.add(this.upPanel);

		c.gridy=1;
		componentLayout.setConstraints(this.downPanel, c);
		this.add(this.downPanel);
	}
	
	/**
	 * 初始化控件
	 * @throws Exception
	 */
	private void initComponent() throws Exception {
		upPanel=new JPanel();
		downPanel=new JPanel();
		this.lblEthernetPackaging=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ETHERNET_PACKAGE));
		this.cmbEthernetPackaging=new JComboBox();
		this.lblVlanTpId=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_TPID));
		this.cmbVlanTpId=new JComboBox();
		this.lblOuterVlanTpId=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUTER_VLAN_ID));
		this.cmbOuterVlanTpId=new JComboBox();
		this.lblVlanMode=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ETHERNET_VLAN_MODEL));
		this.cmbVlanMode=new JComboBox();
		this.lblBroadcast=new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_BROADCAST_SUPPRESSION));
		this.txtBroadcast=new JTextField();
		this.lblUnicast=new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_UNICAST_SUPPRESSION));
		this.txtUnicast=new JTextField();
		this.lblMulticast=new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_MULTICAST_SUPPRESSION));
		this.txtMulticast=new JTextField();
		this.lblVlanId=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DEFAULT_VLAN_ID));
		txtVlanId=new JTextField();
		this.lblVlanPri=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DEFAULT_VLAN_PRIORITY ));
		this.txtVlanPri=new JTextField();
		super.getComboBoxDataUtil().comboBoxData(this.cmbEthernetPackaging, "LAGNETWRAP");
		super.getComboBoxDataUtil().comboBoxData(this.cmbVlanTpId, "LAGVLANTPID");
		super.getComboBoxDataUtil().comboBoxData(this.cmbOuterVlanTpId, "LAGOUTERTPID");
		super.getComboBoxDataUtil().comboBoxData(this.cmbVlanMode, "LAGNETVLANMODE");
	}
	
	/**
	 * 获取端口对象
	 * @param portInst
	 * 			端口对象
	 * @throws Exception
	 */
	public void getPortInst(PortInst portInst) throws Exception{
		if (null == portInst) {
			throw new Exception("portInst is null");
		}
		ControlKeyValue EthernetPackaging = (ControlKeyValue) this.cmbEthernetPackaging.getSelectedItem();
		ControlKeyValue VlanTpId = (ControlKeyValue) this.cmbVlanTpId.getSelectedItem();
		ControlKeyValue OuterVlanTpId = (ControlKeyValue) this.cmbOuterVlanTpId.getSelectedItem();
		ControlKeyValue VlanMode=(ControlKeyValue) this.cmbVlanMode.getSelectedItem();
		portInst.getPortAttr().getPortUniAttr().setEthernetPackaging(Integer.parseInt(EthernetPackaging.getId()));
		portInst.getPortAttr().getPortUniAttr().setVlanTpId(Integer.parseInt(VlanTpId.getId()));
		portInst.getPortAttr().getPortUniAttr().setOuterVlanTpId(Integer.parseInt(OuterVlanTpId.getId()));
		portInst.getPortAttr().getPortUniAttr().setVlanMode((Integer.parseInt(VlanMode.getId())));
		portInst.getPortAttr().getPortUniAttr().setVlanId(this.txtVlanId.getText());
		portInst.getPortAttr().getPortUniAttr().setVlanPri(this.txtVlanPri.getText());
		portInst.getPortAttr().getPortUniAttr().setIsBroadcast(this.lblBroadcast.isSelected()==true?1:0);
		portInst.getPortAttr().getPortUniAttr().setIsMulticast(this.lblMulticast.isSelected()==true?1:0);
		portInst.getPortAttr().getPortUniAttr().setIsUnicast(this.lblUnicast.isSelected()==true?1:0);
		portInst.getPortAttr().getPortUniAttr().setBroadcast(this.txtBroadcast.getText());
		portInst.getPortAttr().getPortUniAttr().setMulticast(this.txtMulticast.getText());
		portInst.getPortAttr().getPortUniAttr().setUnicast(this.txtUnicast.getText());
	}
	private JLabel lblEthernetPackaging;//以太网封装 对应code表主键
	private JComboBox cmbEthernetPackaging;
	private JLabel lblVlanTpId;//运营商vlantpid 对应code表主键
	private JComboBox cmbVlanTpId;
	private JLabel lblOuterVlanTpId;//outer vlan tp id，对应code表主键
	private JComboBox cmbOuterVlanTpId;
	private JLabel lblVlanMode;//以太网vlan模式，对应code表主键
	private JComboBox cmbVlanMode;
	private JCheckBox lblBroadcast;//广播报文抑制
	private JTextField txtBroadcast;
	private JCheckBox lblUnicast;//单播报文抑制
	private JTextField txtUnicast;
	private JCheckBox lblMulticast;//组播报文抑制
	private JTextField txtMulticast;
	private JLabel lblVlanId;//缺省vlanid
	private JTextField txtVlanId;
	private JLabel lblVlanPri;//缺省vlan优先级
	private JTextField txtVlanPri;

	public JComboBox getCmbOuterVlanTpId() {
		return cmbOuterVlanTpId;
	}

	public void setCmbOuterVlanTpId(JComboBox cmbOuterVlanTpId) {
		this.cmbOuterVlanTpId = cmbOuterVlanTpId;
	}

}
