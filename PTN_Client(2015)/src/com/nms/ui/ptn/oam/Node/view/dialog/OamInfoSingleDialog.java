package com.nms.ui.ptn.oam.Node.view.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.oam.view.OamMipPanel;
import com.nms.ui.ptn.oam.view.dialog.OamInfoDialog;

/**
 * OAM配置页面
 * @author dzy
 *
 */
public class OamInfoSingleDialog extends OamInfoDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5496068244612048395L;
	private OamInfo oamInfo; //单网元的oam对象
	/**
	 * 创建一个实例
	 * @param oamInfo oam对象
	 * @param busiType	业务类型
	 * @param modal
	 */
	public OamInfoSingleDialog(OamInfo oamInfo, String busiType,boolean modal) {
		
		super(oamInfo, busiType, modal,new JDialog());
		try {
			this.oamInfo = oamInfo; 
			this.initComponentSingle();
			this.setLayoutSingle();
			this.initData();
			this.addListener();
			this.getController().init(null);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * 监听事件
	 */
	private void addListener() {
		cmbOamType.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (Integer.parseInt(((ControlKeyValue) (cmbOamType.getSelectedItem())).getId()) == OamTypeEnum.MEP.getValue()) {
					oamMipPanel.setVisible(false);
					oamPanel.setVisible(true);
				} else {
					oamMipPanel.setVisible(true);
					oamPanel.setVisible(false);
				}
			}
		});
	}

	/**
	 * 布局
	 */
	private void setLayoutSingle() {
		super.setButtonLayout();
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 50,150 };
		componentLayout.columnWeights = new double[] { 0, 0.1 };
		componentLayout.rowHeights = new int[] {50,300,50 };
		componentLayout.rowWeights = new double[] { 0.0, 0.1, 0.0 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5, 15, 5, 10);

		// 第1行 label 类型
		c.gridx = 0;
		c.gridy = 0;
		componentLayout.setConstraints(this.lblOamType, c);
		this.add(this.lblOamType);
		
		// 第1行 cmb 类型
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.cmbOamType, c);
		this.add(this.cmbOamType);
		
		c.insets = new Insets(0, 5, 0, 5);
		// 第2行 MEP面板
		c.gridx = 0;
		c.gridy = 1;
		componentLayout.setConstraints(super.oamPanel, c);
		this.add(super.oamPanel);
		
		// 第2行 MIP面板
		c.gridx = 0;
		c.gridy = 1;
		componentLayout.setConstraints(this.oamMipPanel, c);
		this.add(this.oamMipPanel);
				
		// 第3行 按钮面板
		c.gridx = 0;
		c.gridy = 2;
		componentLayout.setConstraints(super.panelButton, c);
		this.add(super.panelButton);
		
		
	}
	/**
	 * 初始化数据
	 * @throws Exception 
	 */
	private void initData() throws Exception {
		initOamTypeCombox(this.cmbOamType);
		if(null!=this.oamInfo){
			this.cmbOamType.setEnabled(false);
			if(null!=this.oamInfo.getOamMep()){
				oamMipPanel.setVisible(false);
				oamPanel.setVisible(true);
				this.cmbOamType.setSelectedIndex(0);
			}
			if(null!=this.oamInfo.getOamMip()){
				oamMipPanel.setVisible(true);
				oamPanel.setVisible(false);
				this.cmbOamType.setSelectedIndex(1);
			}
		}
	}


	/**
	 * 初始化控件
	 */
	private void initComponentSingle() {
		this.lblOamType = new JLabel(ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_TYPE));
		this.cmbOamType = new JComboBox();
		this.oamMipPanel = new OamMipPanel(this,this.oamInfo);
		this.oamMipPanel.setVisible(false);
		if(!EServiceType.TUNNEL.toString().equals(super.getBusiType()))
			this.cmbOamType.setEnabled(false);
	}
	
	/**
	 * 初始化oam类型下拉列表
	 * @param comboBox
	 */
	private void initOamTypeCombox(JComboBox comboBox) {
		DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) comboBox.getModel();
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		map.put(OamTypeEnum.MEP.getValue(), OamTypeEnum.MEP.toString() + "    ");
		map.put(OamTypeEnum.MIP.getValue(), OamTypeEnum.MIP.toString() + "    ");
		for (Integer key : map.keySet()) {
			defaultComboBoxModel.addElement(new ControlKeyValue(key + "", map.get(key)));
		}
	} 
	
	private JLabel lblOamType; 			//oam类型 lbl
	private JComboBox cmbOamType;		//oam类型
	private OamMipPanel oamMipPanel;	//mip面板

	public OamInfo getOamInfo() {
		return oamInfo;
	}

	public void setOamInfo(OamInfo oamInfo) {
		this.oamInfo = oamInfo;
	}

	public JComboBox getCmbOamType() {
		return cmbOamType;
	}

	public OamMipPanel getOamMipPanel() {
		return oamMipPanel;
	}

	public void setOamMipPanel(OamMipPanel oamMipPanel) {
		this.oamMipPanel = oamMipPanel;
	}
}
