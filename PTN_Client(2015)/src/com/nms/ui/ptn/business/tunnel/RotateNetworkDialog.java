package com.nms.ui.ptn.business.tunnel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

import com.nms.db.bean.ptn.path.protect.ProtectRorateInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.ERotateType;
import com.nms.model.ptn.path.protect.ProtectRorateInfoService_MB;
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
/**
 * @author Administrator
 *function:网络侧保护倒换的界面
 */
public class RotateNetworkDialog extends PtnDialog {

	private static final long serialVersionUID = -413313310276876404L;
	private Tunnel tunnel;
	private TunnelBusinessPanel tunnelBusinessPanel;
	private ProtectRorateInfo protectRorateInfoA=null;
	private ProtectRorateInfo protectRorateInfoZ=null;

	/**
	 * 
	 * 创建一个新的实例 RotateNodeDialog.
	 * 
	 * @param tunnel
	 *            倒换的tunnel对象
	 * @param tunnelPanel
	 *            tunnel列表
	 */
	public RotateNetworkDialog(Tunnel tunnel, TunnelBusinessPanel tunnelBusinessPanel) {
		try {
			this.tunnel = tunnel;
			this.tunnelBusinessPanel = tunnelBusinessPanel;
			this.protectRorateInfoA=new ProtectRorateInfo();
			this.protectRorateInfoZ=new ProtectRorateInfo();
			
			this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_TUNNEL_PROTECT_ROTATE));
			this.initComponent();
			this.setLayout();
			this.initData();
			this.addListener();

			UiUtil.showWindow(this, 400, 340);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}
/**
 * 
 *初始化数据 
 */
	private void initData() {
		ProtectRorateInfoService_MB protectRorateInfoService = null;
		List<ProtectRorateInfo> ListA=null;
		List<ProtectRorateInfo> ListZ=null;
      try {
			ListA=new ArrayList<ProtectRorateInfo>();
			ListZ=new ArrayList<ProtectRorateInfo>();
			protectRorateInfoService = (ProtectRorateInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PROTECTRORATE);
			
			//获取A端口的所有信息
			protectRorateInfoA.setSiteId(this.tunnel.getASiteId());
//			protectRorateInfoA.setTunnelId(this.tunnel.getProtectTunnelId());
			protectRorateInfoA.setTunnelId(this.tunnel.getTunnelId());
			protectRorateInfoA.setTunnelbusinessid(this.tunnel.getProtectTunnel().getLspParticularList().get(0).getAtunnelbusinessid());
			ListA=protectRorateInfoService.queryByProtectRorateInfo(protectRorateInfoA);
			
			if( ListA !=null && ListA.size()>0 ){
			protectRorateInfoA=ListA.get(0);
			
			chkForceJob.setSelected(protectRorateInfoA.getRecoverMain()==1?true:false);
			chkForcePro.setSelected(protectRorateInfoA.getForceStand()==1?true:false);
			chkManpowerJob.setSelected(protectRorateInfoA.getLockMain()==1?true:false);
			chkManpowerPro.setSelected(protectRorateInfoA.getManpowerStand()==1?true:false);
			chkClear.setSelected(protectRorateInfoA.getClear()==1?true:false);
			}
			
			//获取Z端口的所有信息
			protectRorateInfoZ.setSiteId(this.tunnel.getZSiteId());
//			protectRorateInfoZ.setTunnelId(this.tunnel.getProtectTunnelId());
			protectRorateInfoZ.setTunnelId(this.tunnel.getTunnelId());
			protectRorateInfoZ.setTunnelbusinessid(this.tunnel.getProtectTunnel().getLspParticularList().get(0).getZtunnelbusinessid());
			ListZ=protectRorateInfoService.queryByProtectRorateInfo(protectRorateInfoZ);
			
			if(ListZ!=null&&ListZ.size()>0){
				protectRorateInfoZ=ListZ.get(0);
//				chkForceJob_z.setSelected(protectRorateInfoZ.getRecoverMain()==1?true:false);
//				chkForcePro_z.setSelected(protectRorateInfoZ.getForceStand()==1?true:false);
//				chkManpowerJob_z.setSelected(protectRorateInfoZ.getLockMain()==1?true:false);
//				chkManpowerPro_z.setSelected(protectRorateInfoZ.getManpowerStand()==1?true:false);
			}
	} catch (Exception e) {
		ExceptionManage.dispose(e,this.getClass());
	}finally{
		 UiUtil.closeService_MB(protectRorateInfoService);
		 ListA=null;
		 ListZ=null;
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
	 * 
	 * @Exception 异常对象
	 */
	@SuppressWarnings("unused")
	private void btnSaveListener() {
		String result = null;
		DispatchUtil protectRorateDispatch=null;
		TunnelService_MB tunnelService = null;
		try {
			protectRorateDispatch=new DispatchUtil(RmiKeys.RMI_PROTECTRORATE);
			this.setRotate("a");
			this.setRotate("z");
			
			protectRorateInfoA.setRecoverMain(chkForceJob.isSelected()?1:0);
			protectRorateInfoA.setForceStand(chkForcePro.isSelected()?1:0);
			protectRorateInfoA.setLockMain(chkManpowerJob.isSelected()?1:0);
			protectRorateInfoA.setManpowerStand(chkManpowerPro.isSelected()?1:0);
			protectRorateInfoA.setClear(chkClear.isSelected()?1:0);
			protectRorateInfoZ.setRecoverMain(chkForceJob.isSelected()?1:0);
			protectRorateInfoZ.setForceStand(chkForcePro.isSelected()?1:0);
			protectRorateInfoZ.setLockMain(chkManpowerJob.isSelected()?1:0);
			protectRorateInfoZ.setManpowerStand(chkManpowerPro.isSelected()?1:0);
			protectRorateInfoZ.setClear(chkClear.isSelected()?1:0);
//			if(0==SiteUtil.SiteTypeUtil(tunnel.getASiteId())){
				
			if(protectRorateInfoA.getId()>0){
				result = protectRorateDispatch.excuteUpdate(protectRorateInfoA);
			}else{
				result = protectRorateDispatch.excuteInsert(protectRorateInfoA);
			}
//			}
			
//			if(0==SiteUtil.SiteTypeUtil(tunnel.getZSiteId())){
			if(protectRorateInfoZ.getId()>0){
				result = protectRorateDispatch.excuteUpdate(protectRorateInfoZ);
			}else{
				result = protectRorateDispatch.excuteInsert(protectRorateInfoZ);
			}
//			}
		//	updateWorkState()
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			//工作侧赋值操作（工作状态）
			if(result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				if(ERotateType.FORCESWORK.getValue()==this.tunnel.getRotate_a()||
						ERotateType.MANUALWORK.getValue()==this.tunnel.getRotate_a()||
						ERotateType.CLEAR.getValue()==this.tunnel.getRotate_a()){
					Tunnel tunnel =  new Tunnel();
					tunnel.setTunnelId(protectRorateInfoA.getTunnelId());
					Tunnel resultTunnel = tunnelService.select(tunnel).get(0);
					Tunnel protectTunnel = resultTunnel.getProtectTunnel();
					protectTunnel.setPosition(0);
					resultTunnel.setProtectTunnel(protectTunnel);
					resultTunnel.setPosition(1);
					tunnelService.update(resultTunnel);
				}
				//保护侧赋值操作（工作状态）
				if(ERotateType.FORCESPRO.getValue()==this.tunnel.getRotate_a()||
						ERotateType.MANUALPRO.getValue()==this.tunnel.getRotate_a()){
					Tunnel tunnel =  new Tunnel();
					tunnel.setTunnelId(protectRorateInfoA.getTunnelId());
					Tunnel resultTunnel = tunnelService.select(tunnel).get(0);
					Tunnel protectTunnel = resultTunnel.getProtectTunnel();
					protectTunnel.setPosition(1);
					resultTunnel.setProtectTunnel(protectTunnel);
					resultTunnel.setPosition(0);
					tunnelService.update(resultTunnel);
				}
			}
			
			DialogBoxUtil.succeedDialog(this, result);
			this.tunnelBusinessPanel.getController().refresh();
			this.dispose();

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(tunnelService);
		}
	}

	/**
	 * 给tunnel赋倒换值
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void setRotate(String type) throws Exception {
		Enumeration<AbstractButton> elements = null;
		JRadioButton radioButton = null;
		try {
			// 遍历所有radiobutton 获取选中的button
//			if ("a".equals(type)) {
				elements = this.buttonGroup_a.getElements();
//			} 
//			else {
//				elements = this.buttonGroup_z.getElements();
//			}

			while (elements.hasMoreElements()) {
				radioButton = (JRadioButton) elements.nextElement();
				if (radioButton.isSelected()) {
					break;
				}
			}
			if (null != radioButton) {
				this.tunnel.setRotate_a(Integer.parseInt(radioButton.getName()));
//				this.tunnel.setRotate_a(Integer.parseInt(radioButton.getName()));
//				if ("a".equals(type)) {
//					this.tunnel.setRotate_a(Integer.parseInt(radioButton.getName()));
//				} else {
//					this.tunnel.setRotate_a(Integer.parseInt(radioButton.getName()));
//				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			elements = null;
			radioButton = null;
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
		
		this.chkForceJob = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_RECOVERMAIN));
		this.chkForcePro = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_FORCESTAND));
		this.chkManpowerJob = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_LOCKMAIN));
		this.chkManpowerPro = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_MANPOWERSTAND));
		/**
		 * this.chkForceJob = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_FORCEJOB));
		this.chkForcePro = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_FORCEPRO));
		this.chkManpowerJob = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_MANPOWERJOB));
		this.chkManpowerPro = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_MANPOWERPRO));
		 * 
		 */		
//		this.chkShutting = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_SHUTTING));
		this.chkClear = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CLEAR));
		this.lblJoblsp = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_WORKING_LSP));
		
		
		this.txtJoblsp = new PtnTextField();
		this.txtJoblsp.setEditable(false);
		this.btnSave = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
		this.btnCanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		
		this.panelOrder = new JPanel();
		this.panelOrder.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_EXTERNAL_ORDER)));
		this.panelButton = new JPanel();
		this.buttonGroup_a = new ButtonGroup();
		this.buttonGroup_a.add(chkForceJob);
		this.buttonGroup_a.add(chkForcePro);
		this.buttonGroup_a.add(chkManpowerJob);
		this.buttonGroup_a.add(chkManpowerPro);
		this.buttonGroup_a.add(chkClear);
//		this.buttonGroup_a.add(chkShutting);
		// 给单选按钮赋值，取值时用
		this.chkForceJob.setName(ERotateType.FORCESWORK.getValue() + "");
		this.chkForcePro.setName(ERotateType.FORCESPRO.getValue() + "");
		this.chkManpowerJob.setName(ERotateType.MANUALWORK.getValue() + "");
		this.chkManpowerPro.setName(ERotateType.MANUALPRO.getValue() + "");
//		this.chkShutting.setName(ERotateType.LOCK.getValue() + "");
		this.chkClear.setName(ERotateType.CLEAR.getValue() + "");

//		this.chkForceJob_z = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_FORCEJOB));
//		this.chkForcePro_z = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_FORCEPRO));
//		this.chkManpowerJob_z = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_MANPOWERJOB));
//		this.chkManpowerPro_z = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_MANPOWERPRO));
//		this.chkShutting_z = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_CHK_SHUTTING));
//		this.chkClear_z = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CLEAR));
//		this.lblJoblsp_z = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_WORKING_LSP));
//		this.txtJoblsp_z = new PtnTextField();
//		this.txtJoblsp_z.setEditable(false);
//		this.panelOrder_z = new JPanel();
//		this.panelOrder_z.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_ORDER_INSTALL)));
//		this.buttonGroup_z = new ButtonGroup();
//		this.buttonGroup_z.add(chkForceJob_z);
//		this.buttonGroup_z.add(chkForcePro_z);
//		this.buttonGroup_z.add(chkManpowerJob_z);
//		this.buttonGroup_z.add(chkManpowerPro_z);
//		this.buttonGroup_z.add(chkClear_z);
//		this.buttonGroup_z.add(chkShutting_z);
		// 给单选按钮赋值，取值时用
//		this.chkForceJob_z.setName(ERotateType.FORCESWORK.getValue() + "");
//		this.chkForcePro_z.setName(ERotateType.FORCESPRO.getValue() + "");
//		this.chkManpowerJob_z.setName(ERotateType.MANUALWORK.getValue() + "");
//		this.chkManpowerPro_z.setName(ERotateType.MANUALPRO.getValue() + "");
//		this.chkShutting_z.setName(ERotateType.LOCK.getValue() + "");
//		this.chkClear_z.setName(ERotateType.CLEAR.getValue() + "");

		this.panel_a = new JPanel();
//		this.panel_a.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_A_PORT)));
//		this.panel_z = new JPanel();
//		this.panel_z.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_Z_PORT)));
		
		this.chkForceJob.setSelected(true);
//		this.chkForceJob_z.setSelected(true);
	}

	/**
	 * 设置主页面布局 a端
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
		this.setLayout_a();
//		this.setLayout_z();
		this.setLayoutButton();

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {300};
		componentLayout.columnWeights = new double[] { 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 200, 40 };
		componentLayout.rowWeights = new double[] { 0.0, 0.0 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		componentLayout.setConstraints(this.panel_a, c);
		this.add(this.panel_a);

		c.gridx = 0;
		c.gridy = 1;
		componentLayout.setConstraints(this.panelButton, c);
		this.add(this.panelButton);

	}

	/**
	 * 设置主页面布局 a端
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void setLayout_a() {
		this.setLayoutPanel();

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {100,200};
		componentLayout.columnWeights = new double[] { 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 120, 40 };
		componentLayout.rowWeights = new double[] { 0.0, 0.0 };
		this.panel_a.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.panelOrder, c);
		this.panel_a.add(this.panelOrder);

		c.gridy = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblJoblsp, c);
		this.panel_a.add(this.lblJoblsp);

		c.gridx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		componentLayout.setConstraints(this.txtJoblsp, c);
		this.panel_a.add(this.txtJoblsp);

	}

//	/**
//	 * 设置主页面布局 z端
//	 * 
//	 * @author kk
//	 * 
//	 * @param
//	 * 
//	 * @return
//	 * 
//	 * @Exception 异常对象
//	 */
//	private void setLayout_z() {
//		this.setLayoutPanel_z();
//
//		GridBagLayout componentLayout = new GridBagLayout();
//		componentLayout.columnWidths = new int[] { 100, 200 };
//		componentLayout.columnWeights = new double[] { 0, 0, 0 };
//		componentLayout.rowHeights = new int[] { 120, 40 };
//		componentLayout.rowWeights = new double[] { 0.0, 0.0 };
//		this.panel_z.setLayout(componentLayout);
//
//		GridBagConstraints c = new GridBagConstraints();
//		c.fill = GridBagConstraints.BOTH;
//		c.gridx = 0;
//		c.gridy = 0;
//		c.gridheight = 1;
//		c.gridwidth = 2;
//		c.insets = new Insets(5, 5, 5, 5);
//		componentLayout.setConstraints(this.panelOrder_z, c);
//		this.panel_z.add(this.panelOrder_z);
//
//		c.gridy = 1;
//		c.gridwidth = 1;
//		componentLayout.setConstraints(this.lblJoblsp_z, c);
//		this.panel_z.add(this.lblJoblsp_z);
//
//		c.gridx = 1;
//		c.fill = GridBagConstraints.HORIZONTAL;
//		componentLayout.setConstraints(this.txtJoblsp_z, c);
//		this.panel_z.add(this.txtJoblsp_z);
//
//	}

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

//		c.gridx = 0;
//		c.gridy = 3;
//		componentLayout.setConstraints(this.chkShutting, c);
//		this.panelOrder.add(this.chkShutting);
//
		c.gridy = 3;
		c.gridx = 0;
		componentLayout.setConstraints(this.chkClear, c);
		this.panelOrder.add(this.chkClear);

	}

//	/**
//	 * 设置PANEL布局
//	 * 
//	 * @author kk
//	 * 
//	 * @param
//	 * 
//	 * @return
//	 * 
//	 * @Exception 异常对象
//	 */
//	private void setLayoutPanel_z() {
//		GridBagLayout componentLayout = new GridBagLayout();
//		componentLayout.columnWidths = new int[] { 150, 150 };
//		componentLayout.columnWeights = new double[] { 0, 0, 0 };
//		componentLayout.rowHeights = new int[] { 15, 30, 30, 30, 15 };
//		componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
//		this.panelOrder_z.setLayout(componentLayout);
//
//		GridBagConstraints c = new GridBagConstraints();
//		c.fill = GridBagConstraints.BOTH;
//		c.anchor = GridBagConstraints.CENTER;
//		c.gridx = 0;
//		c.gridy = 1;
//		c.gridheight = 1;
//		c.gridwidth = 1;
//		c.insets = new Insets(5, 15, 5, 5);
//		componentLayout.setConstraints(this.chkForceJob_z, c);
//		this.panelOrder_z.add(this.chkForceJob_z);
//
//		c.gridx = 1;
//		componentLayout.setConstraints(this.chkForcePro_z, c);
//		this.panelOrder_z.add(this.chkForcePro_z);
//
//		c.gridx = 0;
//		c.gridy = 2;
//		componentLayout.setConstraints(this.chkManpowerJob_z, c);
//		this.panelOrder_z.add(this.chkManpowerJob_z);
//
//		c.gridx = 1;
//		componentLayout.setConstraints(this.chkManpowerPro_z, c);
//		this.panelOrder_z.add(this.chkManpowerPro_z);
//
//		c.gridx = 0;
//		c.gridy = 3;
//		componentLayout.setConstraints(this.chkShutting_z, c);
//		this.panelOrder_z.add(this.chkShutting_z);
//
//		c.gridx = 1;
//		componentLayout.setConstraints(this.chkClear_z, c);
//		this.panelOrder_z.add(this.chkClear_z);
//
//	}

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
		componentLayout.columnWidths = new int[] { 70, 70 };
		componentLayout.columnWeights = new double[] { 0, 0 };
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

	private JRadioButton chkForceJob;//强制倒换到工作侧
	private JRadioButton chkForcePro;//强制倒换到保护侧
	private JRadioButton chkManpowerJob;//人工倒换到工作侧
	private JRadioButton chkManpowerPro;//人工倒换到保护侧
//	private JRadioButton chkShutting;//闭锁
	private JRadioButton chkClear;//清除
	private JLabel lblJoblsp;
	private JTextField txtJoblsp;
	
	private JButton btnSave;
	private JButton btnCanel;
	private JPanel panelOrder;
	private JPanel panelButton;
	
//	private JRadioButton chkForceJob_z;
//	private JRadioButton chkForcePro_z;
//	private JRadioButton chkManpowerJob_z;
//	private JRadioButton chkManpowerPro_z;
//	private JRadioButton chkShutting_z;
//	private JRadioButton chkClear_z;
//	private JLabel lblJoblsp_z;
//	private JTextField txtJoblsp_z;
//	
//	private JPanel panelOrder_z;
	private JPanel panel_a;
//	private JPanel panel_z;
	private ButtonGroup buttonGroup_a;
//	private ButtonGroup buttonGroup_z;

}