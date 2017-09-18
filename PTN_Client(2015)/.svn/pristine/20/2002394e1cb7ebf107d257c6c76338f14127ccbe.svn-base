package com.nms.ui.ptn.oam.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.EthService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.oam.view.dialog.OamInfoDialog;

public class SingleVlanConfig extends PtnDialog {
	private static final long serialVersionUID = -8144238274929338386L;
	private OamMepInfo oamMepInfo;
	private JLabel vlanEnable;//外层VLAN使能
	private JLabel outVlanValue;//外层vlan值
	private JLabel tp_id;//外层TP_ID
	private JComboBox vlanEnableComboBox;//
	private JComboBox outVlanValueComboBox;
	//private PtnTextField outVlanValueField;//
	private JComboBox tp_idComboBox;//
	private JPanel panel;	
	private JButton btnSave;//保存按钮
	private JButton btnCancel;//取消按钮
	private JPanel panelBtn;
	private JLabel lblMessage;
	private PortInst portInst;
	private Segment segment;
	private OamInfoDialog oamInfoDialog;
	public SingleVlanConfig(OamMepInfo oamMepInfo, OamInfoDialog oamInfoDialog){
		this.oamInfoDialog = oamInfoDialog;
		Object obj = ((ControlKeyValue)this.oamInfoDialog.getOamPanel().getOamBasicPanel().getCmbName().getSelectedItem()).getObject();
		if(obj instanceof Segment){
			this.segment= (Segment) obj;
		}else{
		    this.portInst =  (PortInst) obj;
		}
		//this.port2LayerAttr = this.getPort2LayerAttr();
		this.oamMepInfo = oamMepInfo;
		this.initComponents();
		this.setLayout();
		this.initData();
		this.addListeners();
		UiUtil.showWindow(this, 350, 300);
	}
	private void initComponents() {
		try {
			this.lblMessage = new JLabel();
			btnSave = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
			vlanEnable = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_ENABLE));
			vlanEnableComboBox = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.vlanEnableComboBox, "ENABLEDSTATUEOAM");
			this.vlanEnableComboBox.setSelectedIndex(0);
			outVlanValue = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OUT_VLAN_VALUE));
			outVlanValueComboBox = new JComboBox();
			tp_id = new JLabel(ResourceUtil.srcStr(StringKeysLbl.TP_ID));
			tp_idComboBox = new JComboBox();
			super.getComboBoxDataUtil().comboBoxData(this.tp_idComboBox, "TP_ID");
			panel = new JPanel();
			btnCancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
			panelBtn = new JPanel();
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		
	}
	private void setLayout(){
		this.setPanleLayout();
		this.setBtnLayout();
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		layout.columnWidths = new int[] { 50,180,40,5 };
		layout.columnWeights = new double[] { 0, 0.1, 0.1, 0 };
		layout.rowHeights = new int[] { 180, 20};
		layout.rowWeights = new double[] { 0, 0 };
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 5);
		c.gridheight = 1;
		c.gridwidth = 1;
		
		c.gridx = 1;
		c.gridy = 0;
		layout.setConstraints(this.panel, c);
		this.add(this.panel);
		
		c.gridy = 1;
		c.gridwidth = 2;
		c.insets = new Insets(20, 5, 5, 5);
		layout.setConstraints(this.panelBtn, c);
		this.add(this.panelBtn);
	}
	
	private void setPanleLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 5,20,140,5 };
		componentLayout.columnWeights = new double[] { 0,0,0,0 };
		componentLayout.rowHeights = new int[] {5,25,25,25,5};
		componentLayout.rowWeights = new double[] {0,0,0,0,0};
		this.panel.setLayout(componentLayout);
		
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(10, 5, 10, 5);
		//第一行
		c.gridx = 1;
		c.gridy = 1;
		componentLayout.setConstraints(this.vlanEnable, c);
		this.panel.add(this.vlanEnable);
		
		c.gridx = 2;
		componentLayout.setConstraints(this.vlanEnableComboBox, c);
		this.panel.add(this.vlanEnableComboBox);
		//第二行
		c.gridx = 1;
		c.gridy = 2;
		componentLayout.setConstraints(this.outVlanValue, c);
		this.panel.add(this.outVlanValue);
		
//		c.gridx = 2;
//		componentLayout.setConstraints(this.outVlanValueField, c);
//		this.panel.add(this.outVlanValueField);
		c.gridx = 2;
		componentLayout.setConstraints(this.outVlanValueComboBox, c);
		this.panel.add(this.outVlanValueComboBox);
		//第三行
		c.gridx = 1;
		c.gridy = 3;
		componentLayout.setConstraints(this.tp_id, c);
		this.panel.add(this.tp_id);
		
		c.gridx = 2;
		componentLayout.setConstraints(this.tp_idComboBox, c);
		this.panel.add(this.tp_idComboBox);
		
	}
	private void setBtnLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 200, 10 };
		componentLayout.columnWeights = new double[] { 0, 0 };
		componentLayout.rowHeights = new int[] { 60 };
		componentLayout.rowWeights = new double[] { 0 };
		this.panelBtn.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		componentLayout.setConstraints(this.btnSave, c);
		this.panelBtn.add(this.btnSave);
		
		c.gridx = 1;
		c.anchor = GridBagConstraints.CENTER;
		componentLayout.setConstraints(this.btnCancel, c);
		this.panelBtn.add(this.btnCancel);
	}
	
	private void initData() {
		
			super.getComboBoxDataUtil().comboBoxSelectByValue(this.vlanEnableComboBox, oamMepInfo.getVlanEnable()+"");
			super.getComboBoxDataUtil().comboBoxSelectByValue(this.tp_idComboBox, oamMepInfo.getTpId()+"");
			//outVlanValueField.setText(oamMepInfo.getVlanValue()+"");			
			List<Integer> vlanIDs = this.querySecondEth();			
			for(Integer vlanid: vlanIDs){				
				outVlanValueComboBox.addItem(vlanid);
			}
			outVlanValueComboBox.addItem(1);
		if(oamMepInfo.getId() > 0){
			outVlanValueComboBox.setSelectedItem(oamMepInfo.getOutVlanValue());
		}else{
			outVlanValueComboBox.setSelectedItem(1);
		}
	}
	
	private void addListeners() {
		this.btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				getData();
				dispose();
			}
		});
		
		this.btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}
	
	private void getData() {
		int index = this.vlanEnableComboBox.getSelectedIndex();
		if(index == 1){
			oamMepInfo.setVlanEnable(1);
		}else{
			oamMepInfo.setVlanEnable(0);
		}
		Object s = outVlanValueComboBox.getSelectedItem();
		oamMepInfo.setOutVlanValue(Integer.parseInt(s.toString()));
		ControlKeyValue atp_id = (ControlKeyValue) this.tp_idComboBox.getSelectedItem();
		oamMepInfo.setTpId(Integer.parseInt(((Code)atp_id.getObject()).getCodeValue()));
	}
	
	/**
	 * 查询该端口下关联的二层业务
	 */
	private List<Integer> querySecondEth(){
		PortService_MB portService = null;				
		EthService_MB ethService = null;
		List<Integer> vlanIDs = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			if(this.segment != null){
				if(segment.getASITEID() == oamMepInfo.getSiteId()){
					this.portInst = portService.selectPortybyid(segment.getAPORTID());					
				}
				if(segment.getZSITEID() == oamMepInfo.getSiteId()){
					this.portInst = portService.selectPortybyid(segment.getZPORTID());					
				}				
			}
			String portLine = "portLine"+(portInst.getNumber()/8+1);
			ethService = (EthService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ETHSERVICE);
			vlanIDs = ethService.queryBySiteIdAndPortLine(portInst.getSiteId(), portLine,this.portInst.getNumber()%8);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(ethService);
			UiUtil.closeService_MB(portService);
		}
		return vlanIDs;
	}
}