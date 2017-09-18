package com.nms.ui.ptn.business.tunnel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnSpinner;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class TnpDialog extends PtnDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2494345412117014821L;
	private JLabel workJLabel;//工作路径
	private PtnTextField workPtnTextField;
	private JLabel asiteJLabel;//A网元名称
	private PtnTextField asitePtnTextField;
	private JLabel protectJLabel;//保护路径
	private JComboBox protectJComboBox;
	private JLabel zsiteJLabel;//Z网元名称
	private PtnTextField zsitePtnTextField;
	private JLabel backJLabel;//是否返回
	private JCheckBox backJCheckBox;
	private JLabel lblwaitTime;
	private PtnSpinner txtWaitTime;//等待恢复时间
	private JLabel lbldelayTime;
	private PtnSpinner txtDelayTime;//拖延时间
	private PtnButton btnConfirm;//确认按钮
	private JButton btnCancel;//取消
	private JPanel butttonPanel;//按钮的布局 
	private Tunnel tunnel;
	private TnpDialog tnpDialog;
	private int isAdd;//新建或删除
	private JLabel apsJLabel;//aps使能
	private JCheckBox apsJCheckBox;
	
	public TnpDialog(Tunnel tunnel){
		tnpDialog = this;
		this.tunnel = tunnel;
		try {
			this.initCompompoments();//组件初始化
			this.setLayout();//布局设置
			this.addListener();//监听事件
			if(tunnel.getProtectTunnel() == null){
				isAdd = 0;
				this.setTitle(ResourceUtil.srcStr(StringKeysMenu.MENU_CREATE_TNP));
			}else{
				isAdd = 1;
				this.setTitle(ResourceUtil.srcStr(StringKeysMenu.MENU_UPDATE_TNP));
				protectJComboBox.setEditable(false);
			}
			this.initDate();//下拉列表等数据初始化
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initCompompoments() throws Exception {
		workJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_WORKING_PATH));
		workPtnTextField = new PtnTextField();
		workPtnTextField.setEditable(false);
		asiteJLabel = new JLabel(ResourceUtil.srcStr(StringKeysObj.STRING_A_SITE_NAME));
		asitePtnTextField = new PtnTextField();
		asitePtnTextField.setEditable(false);
		protectJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_PROTECT_PATH));
		protectJComboBox = new JComboBox();
		zsiteJLabel = new JLabel(ResourceUtil.srcStr(StringKeysObj.STRING_Z_SITE_NAME));
		zsitePtnTextField = new PtnTextField();
		zsitePtnTextField.setEditable(false);
		backJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_BACK));
		backJCheckBox = new JCheckBox();
		lblwaitTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_WAIT_TIME));
		txtDelayTime = new PtnSpinner(10000, 0, 100,ResourceUtil.srcStr(StringKeysLbl.LBL_DELAY_TIME));
		lbldelayTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DELAY_TIME));
		//txtWaitTime = new PtnSpinner(10, 0, 1,ResourceUtil.srcStr(StringKeysLbl.LBL_WAIT_TIME));
		txtWaitTime = new PtnSpinner(PtnSpinner.TYPE_WAITTIME);
		apsJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_APS_ENABLE));
		apsJCheckBox = new JCheckBox();
		btnConfirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true,RootFactory.COREMODU,this);
		btnCancel = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL),true,RootFactory.COREMODU,this);
		butttonPanel = new JPanel();
	}

	private void setLayout() {
		this.setButtonLayout();
		GridBagLayout layout=new GridBagLayout(); 
		layout.columnWidths=new int[]{20,120};
		layout.columnWeights=new double[]{0,0.1};
		layout.rowHeights=new int[]{30};
		layout.rowWeights=new double[]{0};
		this.setLayout(layout);
		GridBagConstraints c=new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets=new Insets(10,8,8,10);
		
		//A网元
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight=1;
		c.gridwidth=1;
		layout.setConstraints(this.asiteJLabel, c);
		this.add(this.asiteJLabel);
		c.gridx=1;
		c.gridwidth=1;
		layout.setConstraints(this.asitePtnTextField, c);
		this.add(this.asitePtnTextField);
		
		//Z网元
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight=1;
		c.gridwidth=1;
		layout.setConstraints(this.zsiteJLabel, c);
		this.add(this.zsiteJLabel);
		c.gridx=1;
		c.gridwidth=1;
		layout.setConstraints(this.zsitePtnTextField, c);
		this.add(this.zsitePtnTextField);
		
		//工作路径
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight=1;
		c.gridwidth=1;
		layout.setConstraints(this.workJLabel, c);
		this.add(this.workJLabel);
		c.gridx=1;
		c.gridwidth=1;
		layout.setConstraints(this.workPtnTextField, c);
		this.add(this.workPtnTextField);
		
		//保护路径
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight=1;
		c.gridwidth=1;
		layout.setConstraints(this.protectJLabel, c);
		this.add(this.protectJLabel);
		c.gridx=1;
		c.gridwidth=1;
		layout.setConstraints(this.protectJComboBox, c);
		this.add(this.protectJComboBox);
		
		//拖延时间
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight=1;
		c.gridwidth=1;
		layout.setConstraints(this.lbldelayTime, c);
		this.add(this.lbldelayTime);
		c.gridx=1;
		c.gridwidth=1;
		layout.setConstraints(this.txtDelayTime, c);
		this.add(this.txtDelayTime);
		
		//等待恢复时间
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight=1;
		c.gridwidth=1;
		layout.setConstraints(this.lblwaitTime, c);
		this.add(this.lblwaitTime);
		c.gridx=1;
		c.gridwidth=1;
		layout.setConstraints(this.txtWaitTime, c);
		this.add(this.txtWaitTime);
		
		//是否返回
		c.gridx = 0;
		c.gridy = 6;
		c.gridheight=1;
		c.gridwidth=1;
		layout.setConstraints(this.backJLabel, c);
		this.add(this.backJLabel);
		c.gridx=1;
		c.gridwidth=1;
		layout.setConstraints(this.backJCheckBox, c);
		this.add(this.backJCheckBox);
		
		//APS
		c.gridx = 0;
		c.gridy = 7;
		c.gridheight=1;
		c.gridwidth=1;
		layout.setConstraints(this.apsJLabel, c);
		this.add(this.apsJLabel);
		c.gridx=1;
		c.gridwidth=1;
		layout.setConstraints(this.apsJCheckBox, c);
		this.add(this.apsJCheckBox);
		
		//按钮
		c.gridx = 0;
		c.gridy = 8;
		c.gridheight=1;
		c.gridwidth=2;
		layout.setConstraints(this.butttonPanel, c);
		this.add(this.butttonPanel);
	}

	/**
	 * 设置按钮布局
	 */
	private void setButtonLayout() {
		GridBagLayout layout=new GridBagLayout(); 
		layout.columnWidths=new int[]{20,100,20,20};
		layout.columnWeights=new double[]{0,0.1,0,0};
		layout.rowHeights=new int[]{20};
		layout.rowWeights=new double[]{0.1};
		this.butttonPanel.setLayout(layout);
		GridBagConstraints c=new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		//1 清除过滤按钮
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight=1;
		c.gridwidth=1;
		c.insets=new Insets(5,5,0,10);
		c.gridx=2;
		layout.setConstraints(this.btnConfirm, c);
		butttonPanel.add(this.btnConfirm);
		c.gridx=3;
		layout.setConstraints(this.btnCancel, c);
		butttonPanel.add(this.btnCancel);
		
	}
	
	private void addListener() {
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tnpDialog.dispose();
			}
		});
		btnConfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnConfirmActionPerformed();
			}
		});
	}

	private void btnConfirmActionPerformed(){
		String result = "";
		TunnelService_MB service = null;
		try {
			service = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			Tunnel tunnel4TNP1 = service.selectId(this.tunnel.getTunnelId());
			ControlKeyValue controlKeyValue = (ControlKeyValue) protectJComboBox.getSelectedItem();
			if(controlKeyValue == null){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NO_PROTECT_PATH));
				return ;
			}
			tunnel.setApsenable(apsJCheckBox.isSelected()?1:0);
			tunnel.setProtectBack(backJCheckBox.isSelected()?0:1);
			tunnel.setWaittime(Integer.parseInt(txtWaitTime.getTxt().getText()));
			tunnel.setDelaytime(Integer.parseInt(txtDelayTime.getTxt().getText()));
			DispatchUtil dispatchUtil = new DispatchUtil(RmiKeys.RMI_TUNNELPROTECT);
			if(isAdd == 0){
				Tunnel protectTunnel = (Tunnel) controlKeyValue.getObject();
				tunnel.setProtectTunnel(protectTunnel);
				tunnel.setTunnelType("186");
				tunnel.setProtectTunnelId(protectTunnel.getTunnelId());
				protectTunnel.setTunnelType("0");
				Tunnel tunnel4TNP2 = service.selectId(protectTunnel.getTunnelId());
				result = dispatchUtil.excuteInsert(tunnel);
				protectTunnel.setTunnelType("186");
				this.insertLog(this.btnConfirm, EOperationLogType.INSERTTNP.getValue(), result, tunnel4TNP2, protectTunnel);
				tunnel.setProtectTunnel(null);
				this.insertLog(this.btnConfirm, EOperationLogType.INSERTTNP.getValue(), result, tunnel4TNP1, tunnel);
			}else{
				result = dispatchUtil.excuteUpdate(tunnel);
				if(tunnel.getProtectTunnel() != null){
					tunnel.getProtectTunnel().setTunnelType(tunnel.getTunnelType());
					tunnel4TNP1.getProtectTunnel().setTunnelType(tunnel.getTunnelType());
					this.insertLog(this.btnConfirm, EOperationLogType.UPDATETNP.getValue(), result, tunnel4TNP1.getProtectTunnel(), tunnel.getProtectTunnel());
				}
				tunnel.setProtectTunnel(null);
				tunnel4TNP1.setProtectTunnel(null);
				this.insertLog(this.btnConfirm, EOperationLogType.UPDATETNP.getValue(), result, tunnel4TNP1, tunnel);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
		DialogBoxUtil.succeedDialog(this, result);
		this.dispose();
	}
	
	private void initDate() throws Exception {
		initTunnel(protectJComboBox);
		asitePtnTextField.setText(tunnel.getShowSiteAname());
		zsitePtnTextField.setText(tunnel.getShowSiteZname());
		workPtnTextField.setText(tunnel.getTunnelName());
		if(tunnel.getProtectTunnelId()>0){
			super.getComboBoxDataUtil().comboBoxSelect(this.protectJComboBox, this.tunnel.getProtectTunnelId() + "");
			backJCheckBox.setSelected(tunnel.getProtectBack() == 1?false:true);
			txtDelayTime.getTxt().setText(tunnel.getDelaytime()+"");
			txtWaitTime.getTxt().setText(tunnel.getWaittime()+"");
			apsJCheckBox.setSelected(tunnel.getApsenable() == 1?true:false);
		}
	}
	
	private void initTunnel(JComboBox jComboBox){
		DefaultComboBoxModel defaultComboBoxModel = null;
		TunnelService_MB tunnelServiceMB = null;
		List<Tunnel> tunnels = null;
		PwInfoService_MB pwInfoService = null;
		try {
			defaultComboBoxModel = new DefaultComboBoxModel();
			tunnelServiceMB = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			pwInfoService= (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			if(isAdd == 0){
				tunnels = tunnelServiceMB.selectByASiteIdAndZSiteId(tunnel.getaSiteId(), tunnel.getzSiteId());
				for(Tunnel tunnel : tunnels){
					List<PwInfo> pwInfos = pwInfoService.selectByTunnelId(tunnel.getTunnelId());
					if(pwInfos.size() == 0 && tunnel.getTunnelType().equals("185")){
						if(tunnel.getaSiteId() == this.tunnel.getaSiteId()){
							if(tunnel.getaPortId() != this.tunnel.getaPortId() && tunnel.getzPortId() != this.tunnel.getzPortId()){
								defaultComboBoxModel.addElement(new ControlKeyValue(tunnel.getTunnelId() + "", tunnel.getTunnelName(), tunnel));
							}
						}else if(tunnel.getzSiteId() == this.tunnel.getaSiteId()){
							if(tunnel.getaPortId() != this.tunnel.getzPortId() && tunnel.getzPortId() != this.tunnel.getaPortId()){
								defaultComboBoxModel.addElement(new ControlKeyValue(tunnel.getTunnelId() + "", tunnel.getTunnelName(), tunnel));
							}
						}
					}
				}
			}else{
				defaultComboBoxModel.addElement(new ControlKeyValue(tunnel.getProtectTunnelId() + "", tunnel.getProtectTunnel().getTunnelName(), tunnel.getProtectTunnel()));
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(tunnelServiceMB);
			UiUtil.closeService_MB(pwInfoService);
		}
		
		jComboBox.setModel(defaultComboBoxModel);
	}
	
	protected void insertLog(PtnButton ptnButton, int operationType, String message, Tunnel tunnelBefore, Tunnel tunnel){
		List<Integer> siteIdList = new ArrayList<Integer>();
		SiteService_MB siteService = null;
		PortService_MB portService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			if(tunnelBefore != null){
				tunnelBefore.setShowSiteAname(siteService.getSiteName(tunnelBefore.getaSiteId()));
				tunnelBefore.setShowSiteZname(siteService.getSiteName(tunnelBefore.getzSiteId()));
				for(Lsp lsp : tunnelBefore.getLspParticularList()){
					lsp.setaSiteName(siteService.getSiteName(lsp.getASiteId()));
					lsp.setzSiteName(siteService.getSiteName(lsp.getZSiteId()));
					lsp.setaPortName(portService.getPortname(lsp.getAPortId()));
					lsp.setzPortName(portService.getPortname(lsp.getZPortId()));
				}
				this.getOamSiteName(tunnelBefore, siteService);
			}
			tunnel.setShowSiteAname(siteService.getSiteName(tunnel.getaSiteId()));
			tunnel.setShowSiteZname(siteService.getSiteName(tunnel.getzSiteId()));
			this.getOamSiteName(tunnel, siteService);
			for(Lsp lsp : tunnel.getLspParticularList()){
				lsp.setaSiteName(siteService.getSiteName(lsp.getASiteId()));
				lsp.setzSiteName(siteService.getSiteName(lsp.getZSiteId()));
				lsp.setaPortName(portService.getPortname(lsp.getAPortId()));
				lsp.setzPortName(portService.getPortname(lsp.getZPortId()));
			}
			for(Lsp lsp : tunnel.getLspParticularList()){
				if(!siteIdList.contains(lsp.getASiteId())){
					siteIdList.add(lsp.getASiteId());
					AddOperateLog.insertOperLog(ptnButton, operationType, message, tunnelBefore, tunnel, lsp.getASiteId(), tunnel.getTunnelName(), "Tunnel");
				}
				if(!siteIdList.contains(lsp.getZSiteId())){
					siteIdList.add(lsp.getZSiteId());
					AddOperateLog.insertOperLog(ptnButton, operationType, message, tunnelBefore, tunnel, lsp.getZSiteId(), tunnel.getTunnelName(), "Tunnel");
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(portService);
		}
	}
	
	private void getOamSiteName(Tunnel tunnel, SiteService_MB siteService){
		List<OamInfo> oamList = tunnel.getOamList();
		if(oamList != null && oamList.size() > 0){
			for (OamInfo oamInfo : oamList) {
				if(oamInfo.getOamMep() != null){
					if(oamInfo.getOamMep().getSiteId() == tunnel.getASiteId()){
						oamInfo.getOamMep().setSiteName(tunnel.getShowSiteAname());
					}else if(oamInfo.getOamMep().getSiteId() == tunnel.getZSiteId()){
						oamInfo.getOamMep().setSiteName(tunnel.getShowSiteZname());
					}
				}
				if(oamInfo.getOamMip() != null){
					oamInfo.getOamMip().setSiteName(siteService.getSiteName(oamInfo.getOamMip().getSiteId()));
				}
			}
		}
	}
}
