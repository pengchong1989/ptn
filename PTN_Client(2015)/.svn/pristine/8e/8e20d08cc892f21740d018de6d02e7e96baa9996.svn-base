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
import com.nms.db.bean.equipment.port.PortNniAttr;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.ne.eth.view.dialog.base.PortNniDialog;

/**
*    
* 项目名称：WuHanPTN2012   
* 类名称：PortNniCXDialog   
* 类描述：   端口NNI信息晨晓界面
* 创建人：kk   
* 创建时间：2013-7-15 上午11:53:13   
* 修改人：kk   
* 修改时间：2013-7-15 上午11:53:13   
* 修改备注：   
* @version    
*
 */
public class PortNniCXDialog extends PortNniDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = -279583159466674179L;
	private JPanel leftPanel;//左边面板
	private JPanel rightPanel;//右边面板
	private PortInst portInst;
	/**
	 * 创建一个新的实例
	 */
	public PortNniCXDialog(){
		
	}
	/**
	 * 创建一个新的实例
	 * @param portInst
	 * 			端口
	 * @throws Exception
	 */
	public PortNniCXDialog(PortInst portInst)throws Exception{
		try{
		this.portInst=portInst;
		initComponent();
		setLeftLayout();
		setRightLayout();
		setLayout();
		if(null!=portInst){
		initData();
		}}catch(Exception e){
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 仅用于显示，不能更改数据
	 * @throws Exception
	 */
	public void lock()throws Exception {
		try {
			txtStaticMac.setEditable(false);
			chbCcnEnable.setEnabled(false);
			txtNniVlanid.setEditable(false);
			txtNniVlanpri.setEditable(false);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 初始化数据
	 */	
	private void initData(){
		PortNniAttr portNniAttr=portInst.getPortAttr().getPortNniAttr();		
		this.txtStaticMac.setText(portNniAttr.getStaticMac());
		this.txtNeighbourId.setText(portNniAttr.getNeighbourId());
		this.txtOperMac.setText(portNniAttr.getOperMac());
		this.txtOperId.setText(portNniAttr.getOperId());
		super.getComboBoxDataUtil().comboBoxSelect(cmbNeighbourFind,portNniAttr.getNeighbourFind()+"");
		this.chbCcnEnable.setSelected(portNniAttr.getCcnEnable()== 1?true:false);
		if(portNniAttr.getNniVlanid()!=null&&Integer.parseInt(portNniAttr.getNniVlanid())>0){
			this.txtNniVlanid.setText(portNniAttr.getNniVlanid());//  [1,4094]
		}
		this.txtNniVlanpri.setText(portNniAttr.getNniVlanpri());
		
	}
	
	
	/**
	 *设置左面板布局 
	 */
	private void setLeftLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 100,220 };
		componentLayout.columnWeights = new double[] { 0, 0 };
		componentLayout.rowHeights = new int[] { 40,40,40,40,40,40 };
		componentLayout.rowWeights = new double[] { 0,0,0,0,0,0 };
		this.leftPanel.setLayout(componentLayout);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.anchor = GridBagConstraints.CENTER;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblStaticMac, c);
		this.leftPanel.add(this.lblStaticMac);
		
		c.gridx = 1;
		componentLayout.setConstraints(this.txtStaticMac, c);
		this.leftPanel.add(this.txtStaticMac);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblNeighbourId, c);
		this.leftPanel.add(this.lblNeighbourId);
		
		c.gridx = 1;
		componentLayout.setConstraints(this.txtNeighbourId, c);
		this.leftPanel.add(this.txtNeighbourId);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblOperMac, c);
		this.leftPanel.add(this.lblOperMac);
		
		c.gridx = 1;
		componentLayout.setConstraints(this.txtOperMac, c);
		this.leftPanel.add(this.txtOperMac);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblOperId, c);
		this.leftPanel.add(this.lblOperId);
		
		c.gridx = 1;
		componentLayout.setConstraints(this.txtOperId, c);
		this.leftPanel.add(this.txtOperId);
		
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblNeighbourFind, c);
		this.leftPanel.add(this.lblNeighbourFind);
		
		c.gridx = 1;
		componentLayout.setConstraints(this.cmbNeighbourFind, c);
		this.leftPanel.add(this.cmbNeighbourFind);
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblCcnEnable, c);
		this.leftPanel.add(this.lblCcnEnable);
		
		c.gridx = 1;
		componentLayout.setConstraints(this.chbCcnEnable, c);
		this.leftPanel.add(this.chbCcnEnable);
		this.leftPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_NEI_INFO)));
		
		
	}
	
	/**
	 *设置右面板布局 
	 */
	private void setRightLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 100,220 };
		componentLayout.columnWeights = new double[] { 0, 0 };
		componentLayout.rowHeights = new int[] { 40,40,40,40,40,40 };
		componentLayout.rowWeights = new double[] { 0,0,0,0,0,0 };
		this.rightPanel.setLayout(componentLayout);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		c.anchor = GridBagConstraints.CENTER;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblNniVlanid, c);
		this.rightPanel.add(this.lblNniVlanid);
		
		c.gridx = 1;
		componentLayout.setConstraints(this.txtNniVlanid, c);
		this.rightPanel.add(this.txtNniVlanid);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblNniVlanpri, c);
		this.rightPanel.add(this.lblNniVlanpri);
		
		c.gridx = 1;
		componentLayout.setConstraints(this.txtNniVlanpri, c);
		this.rightPanel.add(this.txtNniVlanpri);
		this.rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_VALN_INFO))));
	}
	
	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 320,320 };
		componentLayout.columnWeights = new double[] { 0 ,0};
		componentLayout.rowHeights = new int[] { 300 };
		componentLayout.rowWeights = new double[] { 0};
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.leftPanel, c);
		this.add(this.leftPanel);

		c.gridx=1;
		componentLayout.setConstraints(this.rightPanel, c);
		this.add(this.rightPanel);
	}

	/**
	 * 初始化控件
	 * @throws Exception
	 */
	private void initComponent() throws Exception {
		leftPanel=new JPanel();
		rightPanel=new JPanel();
		this.lblStaticMac=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_STATIC_MAC));
		this.txtStaticMac=new JTextField();
		this.lblNeighbourId=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NEI_ID));
		this.txtNeighbourId=new JTextField();
		this.txtNeighbourId.setEnabled(false);
		this.lblOperMac=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OPER_MAC));
		this.txtOperMac=new JTextField();
		this.txtOperMac.setEnabled(false);
		this.lblOperId=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OPER_ID));
		this.txtOperId=new JTextField();
		this.txtOperId.setEnabled(false);
		this.lblNeighbourFind=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NEI_FIND));
		this.cmbNeighbourFind=new JComboBox();
		this.cmbNeighbourFind.setEnabled(false);
		this.lblCcnEnable=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CCN_ENABLE));
		this.chbCcnEnable=new JCheckBox();
		this.chbCcnEnable.setEnabled(false);
		this.lblNniVlanid=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DEFAULT_VLAN_ID));
		this.txtNniVlanid=new JTextField("1");//默认值为  1
		this.lblNniVlanpri=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DEFAULT_VLAN_PRIORITY));
		this.txtNniVlanpri=new JTextField();
	}
	
	/**
	 * 获取portInst对象
	 * 
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public void getPortInst(PortInst portInst) throws Exception {

		if (null == portInst) {
			throw new Exception("portInst is null");
		}
		portInst.getPortAttr().getPortNniAttr().setStaticMac(this.txtStaticMac.getText());
		portInst.getPortAttr().getPortNniAttr().setNeighbourId(this.txtNeighbourId.getText());
		portInst.getPortAttr().getPortNniAttr().setOperMac(this.txtOperMac.getText());
		portInst.getPortAttr().getPortNniAttr().setOperId(this.txtOperId.getText());
		portInst.getPortAttr().getPortNniAttr().setCcnEnable(this.chbCcnEnable.isSelected()==true?1:0);
		portInst.getPortAttr().getPortNniAttr().setNniVlanid(this.txtNniVlanid.getText());
		portInst.getPortAttr().getPortNniAttr().setNniVlanpri(this.txtNniVlanpri.getText());
	}
	
	private JLabel lblStaticMac;//静态MAC地址
	private JTextField txtStaticMac;
	private JLabel lblNeighbourId;//邻居网元ID
	private JTextField txtNeighbourId;
	private JLabel lblOperMac;//对端接口mac地址
	private JTextField txtOperMac;
	private JLabel lblOperId;//对端接口ID
	private JTextField txtOperId;
	private JLabel lblNeighbourFind;//邻居发现状态 对应code表主键
	private JComboBox cmbNeighbourFind;
	private JLabel lblCcnEnable;//ccn承载使能 0=false 1=true
	private JCheckBox chbCcnEnable;
	private JLabel lblNniVlanid;//缺省vlanid
	private JTextField txtNniVlanid;
	private JLabel lblNniVlanpri;//缺省vlan优先级
	private JTextField txtNniVlanpri;
}
