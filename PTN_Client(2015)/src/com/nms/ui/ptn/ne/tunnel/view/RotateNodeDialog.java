package com.nms.ui.ptn.ne.tunnel.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.ERotateType;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;

public class RotateNodeDialog extends PtnDialog {

	private static final long serialVersionUID = -413313310276876404L;
	private Tunnel tunnel;
	private TunnelPanel tunnelPanel;

	/**
	 * 
	 * 创建一个新的实例 RotateNodeDialog.
	 * 
	 * @param tunnel
	 *            倒换的tunnel对象
	 * @param tunnelPanel
	 *            tunnel列表
	 */
	public RotateNodeDialog(Tunnel tunnel, TunnelPanel tunnelPanel) {
		try {
			this.tunnel = tunnel;
			this.tunnelPanel = tunnelPanel;

			this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_TUNNEL_PROTECT_ROTATE));
			this.initComponent();
			this.setLayout();
			this.addListener();

			UiUtil.showWindow(this, 350, 300);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	/**
	 * 按钮添加事件
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void addListener() {
		this.btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		this.btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSaveListener();
			}
		});
	}

	/**
	 * 保存按钮监听
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void btnSaveListener() {
		Enumeration<AbstractButton> elements = null;
		JRadioButton radioButton = null;
		DispatchUtil protectRotateDispatch = null;
		String result = null;
		TunnelService_MB tunnelServiceMB = null;
		try {
			// 遍历所有radiobutton 获取选中的button
			elements = this.buttonGroup.getElements();
			while (elements.hasMoreElements()) {
				radioButton = (JRadioButton) elements.nextElement();
				if (radioButton.isSelected()) {
					break;
				}
			}
			int ratate=0;
			if (null != radioButton) {
				if (this.tunnel.getASiteId() == ConstantUtil.siteId) {
					this.tunnel.setRotate_a(Integer.parseInt(radioButton.getName()));
					ratate = Integer.parseInt(radioButton.getName());
				} else {
					this.tunnel.setRotate_z(Integer.parseInt(radioButton.getName()));
					ratate = Integer.parseInt(radioButton.getName());
				}
				
				protectRotateDispatch = new DispatchUtil(RmiKeys.RMI_PROTECTROTATE);
				result = protectRotateDispatch.excuteUpdate(this.tunnel);
				tunnelServiceMB = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
				if(result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
					if(4==ratate||6==ratate||1==ratate){
						Tunnel protectTunnel = tunnel.getProtectTunnel();
						List<Lsp> protectLspList = protectTunnel.getLspParticularList();
						List<Lsp> lspList = tunnel.getLspParticularList();
						for(Lsp lsp:lspList){
							lsp.setPosition(1);
						}
						for(Lsp lsp:protectLspList){
							lsp.setPosition(0);
						}
						protectTunnel.setLspParticularList(protectLspList);
						protectTunnel.setPosition(0);
						tunnel.setProtectTunnel(protectTunnel);
						tunnel.setLspParticularList(lspList);
						tunnel.setPosition(1);
						tunnelServiceMB.update(tunnel);
					}
					if(3==ratate||5==ratate){
						Tunnel protectTunnel = tunnel.getProtectTunnel();
						List<Lsp> protectLspList = protectTunnel.getLspParticularList();
						
						List<Lsp> lspList = tunnel.getLspParticularList();
						for(Lsp lsp:lspList){
							lsp.setPosition(0);
						}
						for(Lsp lsp:protectLspList){
							lsp.setPosition(1);
						}
						protectTunnel.setLspParticularList(protectLspList);
						protectTunnel.setPosition(1);
						tunnel.setProtectTunnel(protectTunnel);
						tunnel.setLspParticularList(lspList);
						tunnel.setPosition(0);
						tunnelServiceMB.update(tunnel);
					}
				}
				DialogBoxUtil.succeedDialog(null, result);
				this.tunnelPanel.getController().refresh();
				this.dispose();

			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			elements = null;
			radioButton = null;
			protectRotateDispatch = null;
			UiUtil.closeService_MB(tunnelServiceMB);
		}

	}

	/**
	 * 初始化控件
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void initComponent() {
		this.chkForceJob = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_FORCEJOB));
		this.chkForcePro = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_FORCEPRO));
		this.chkManpowerJob = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_MANPOWERJOB));
		this.chkManpowerPro = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_MANPOWERPRO));
		this.chkShutting = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_SHUTTING));
		this.chkClear = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CLEAR));
		this.lblJoblsp = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_WORKING_LSP));
		this.txtJoblsp = new PtnTextField();
		this.txtJoblsp.setEditable(false);
		this.btnSave = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		this.btnCanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.panelOrder = new JPanel();
		this.panelOrder.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_EXTERNAL_ORDER)));
		this.panelButton = new JPanel();
		this.buttonGroup = new ButtonGroup();
		this.buttonGroup.add(chkForceJob);
		this.buttonGroup.add(chkForcePro);
		this.buttonGroup.add(chkManpowerJob);
		this.buttonGroup.add(chkManpowerPro);
		this.buttonGroup.add(chkClear);
		this.buttonGroup.add(chkShutting);
		this.chkForceJob.setSelected(true);
		// 给单选按钮赋值，取值时用
		this.chkForceJob.setName(ERotateType.FORCESWORK.getValue() + "");
		this.chkForcePro.setName(ERotateType.FORCESPRO.getValue() + "");
		this.chkManpowerJob.setName(ERotateType.MANUALWORK.getValue() + "");
		this.chkManpowerPro.setName(ERotateType.MANUALPRO.getValue() + "");
		this.chkShutting.setName(ERotateType.LOCK.getValue() + "");
		this.chkClear.setName(ERotateType.CLEAR.getValue() + "");
	}

	/**
	 * 设置主页面布局
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void setLayout() {
		this.setLayoutPanel();
		this.setLayoutButton();

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 100, 200 };
		componentLayout.columnWeights = new double[] { 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 120, 40, 40 };
		componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.panelOrder, c);
		this.add(this.panelOrder);

		c.gridy = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblJoblsp, c);
		this.add(this.lblJoblsp);

		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		componentLayout.setConstraints(this.txtJoblsp, c);
		this.add(this.txtJoblsp);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.panelButton, c);
		this.add(this.panelButton);

	}

	/**
	 * 设置PANEL布局
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void setLayoutPanel() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 150, 150 };
		componentLayout.columnWeights = new double[] { 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 15, 30, 30, 30, 15 };
		componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		this.panelOrder.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 15, 5, 5);
		componentLayout.setConstraints(this.chkForceJob, c);
		this.panelOrder.add(this.chkForceJob);

		c.gridx = 1;
		componentLayout.setConstraints(this.chkForcePro, c);
		this.panelOrder.add(this.chkForcePro);

		c.gridx = 0;
		c.gridy = 2;
		componentLayout.setConstraints(this.chkManpowerJob, c);
		this.panelOrder.add(this.chkManpowerJob);

		c.gridx = 1;
		componentLayout.setConstraints(this.chkManpowerPro, c);
		this.panelOrder.add(this.chkManpowerPro);

		c.gridx = 0;
		c.gridy = 3;
		componentLayout.setConstraints(this.chkShutting, c);
		this.panelOrder.add(this.chkShutting);

		c.gridx = 1;
		componentLayout.setConstraints(this.chkClear, c);
		this.panelOrder.add(this.chkClear);

	}

	/***
	 * 按钮布局
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void setLayoutButton() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 230, 70 };
		componentLayout.columnWeights = new double[] { 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 40 };
		componentLayout.rowWeights = new double[] {};
		this.panelButton.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.btnSave, c);
		this.panelButton.add(this.btnSave);

		c.gridx = 1;
		componentLayout.setConstraints(this.btnCanel, c);
		this.panelButton.add(this.btnCanel);
	}

	private JRadioButton chkForceJob;
	private JRadioButton chkForcePro;
	private JRadioButton chkManpowerJob;
	private JRadioButton chkManpowerPro;
	private JRadioButton chkShutting;
	private JRadioButton chkClear;
	private JLabel lblJoblsp;
	private JTextField txtJoblsp;
	private JButton btnSave;
	private JButton btnCanel;
	private JPanel panelOrder;
	private JPanel panelButton;
	private ButtonGroup buttonGroup;

}
