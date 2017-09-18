package com.nms.ui.ptn.business.tunnel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.model.ptn.LabelInfoService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnSpinner;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysMenu;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class UpdateBatchLabelDialog extends PtnDialog{
	private static final long serialVersionUID = 6313565415735552099L;
	private JLabel labelStepLbl;//递增步长
	private PtnSpinner labelSpinner;
	private PtnButton saveBtn;//确认
	private JButton cancelBtn;//取消
	private JPanel buttonPanel;
	private List<Tunnel> tunnelList = null;//被选中的tunnel
	int labelStep = 1;//默认步长是1
	
	public UpdateBatchLabelDialog(List<Tunnel> allSelect) {
		this.tunnelList = allSelect;
		this.setTitle(ResourceUtil.srcStr(StringKeysMenu.MENU_BATCH_UPDATE_LABEL));
		this.initComponent();
		this.setLayout();
		this.addListener();
		UiUtil.showWindow(this, 250, 200);
	}

		private void initComponent() {
		this.labelStepLbl = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_RISE_STEP));
		this.labelSpinner = new PtnSpinner(1, 1, 100, 1);
		this.saveBtn = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),false,RootFactory.CORE_MANAGE);
		this.cancelBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.buttonPanel = new JPanel();
	}

	private void setLayout() {
		this.setButtonLayout();
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.rowHeights = new int[] {30, 30, 30};
		componentLayout.columnWidths = new int[] { 30, 70, 50};
	    componentLayout.columnWeights = new double[] { 0, 0, 0 };
		componentLayout.rowWeights = new double[] { 0.1, 0, 0, 0 };
		this.setLayout(componentLayout);
	
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.NONE;
		//第一行
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.insets = new Insets(30, 10, 15, 10);
		componentLayout.setConstraints(this.labelStepLbl, c);
		this.add(this.labelStepLbl);
		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(30, 10, 15, 30);
		componentLayout.setConstraints(this.labelSpinner, c);
		this.add(this.labelSpinner);
		//第二行
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.buttonPanel, c);
		this.add(this.buttonPanel);
	}
	
	private void setButtonLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {50, 50, 50};
		componentLayout.columnWeights = new double[] {0.1, 0, 0};
		componentLayout.rowHeights = new int[] {30};
		componentLayout.rowWeights = new double[] {0};
		this.setLayout(componentLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.saveBtn, c);
		buttonPanel.add(this.saveBtn);
		c.gridx=2;
		componentLayout.setConstraints(this.cancelBtn, c);
		buttonPanel.add(this.cancelBtn);
		
	}

	private void addListener() {
		this.saveBtn.addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveAction();
			}

			@Override
			public boolean checking() {
				return false;
			}
		});			
	
		this.cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	/**
	 * 以第一条tunnel为标准,只对是相同路径的tunnel进行操作
	 * 更改标签后，验证标签是否可用，不可用提示用户
	 */
	private void saveAction() {
		//获取步长
		try {
			this.labelStep = Integer.parseInt(this.labelSpinner.getTxtData());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		//筛选符合条件的tunnel
		Tunnel firstTunnel = this.tunnelList.get(0);
		List<Tunnel> usableTunnelList = new ArrayList<Tunnel>();//符合要求的tunnel集合
		for (int i = 1; i < this.tunnelList.size(); i++) {
			Tunnel tunnel = this.tunnelList.get(i);
			boolean flag = this.filterTunnelList(firstTunnel, tunnel);
			if(flag){
				if(firstTunnel.getProtectTunnel() != null){
					flag = this.filterTunnelList(firstTunnel.getProtectTunnel(), tunnel.getProtectTunnel());
					if(flag){
						usableTunnelList.add(tunnel);
					}
				}else{
					usableTunnelList.add(tunnel);
				}
			}
		}
		//验证标签是否可用并修改tunnel
		LabelInfoService_MB labelServiceMB = null;
		try {
			labelServiceMB = (LabelInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LABELINFO);
			DispatchUtil dispatchUtil = new DispatchUtil(RmiKeys.RMI_TUNNEL);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < usableTunnelList.size(); i++) {
				Tunnel tunnel = usableTunnelList.get(i);
				boolean flag = this.checkLabelStep1(tunnel, firstTunnel, i, labelServiceMB);
				if(flag){
					if(tunnel.getProtectTunnel() != null){
						flag = this.checkLabelStep1(tunnel.getProtectTunnel(), firstTunnel.getProtectTunnel(), i, labelServiceMB);
					}
				}
				if(flag){
					String result = dispatchUtil.excuteUpdate(tunnel);
					if(result.contains(ResultString.CONFIG_SUCCESS)){
						if(i < usableTunnelList.size()-1){
							sb.append(tunnel.getTunnelName()+",");
						}else{
							sb.append(tunnel.getTunnelName());
						}
					}
				}
			}
			String result = "";
			if(sb.toString().length() == 0){
				result = ResultString.CONFIG_FAILED;
			}else{
				result = sb.toString()+ResultString.CONFIG_SUCCESS;
			}
			DialogBoxUtil.succeedDialog(this, result);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(labelServiceMB);
			this.dispose();
		}
	}

	private boolean checkLabelStep1(Tunnel tunnel, Tunnel firstTunnel, int i, LabelInfoService_MB labelInfoServiceMB) {
		List<Lsp> lspList = tunnel.getLspParticularList();
		boolean flag = true;
		for (int j = 0; j < lspList.size(); j++) {
			Lsp firstLsp = firstTunnel.getLspParticularList().get(j);
			Lsp currLsp = lspList.get(j);
			int oldFrontLabel = currLsp.getFrontLabelValue();
			int oldBackLabel = currLsp.getBackLabelValue();
			currLsp.setFrontLabelValue(firstLsp.getFrontLabelValue()+(i+1)*this.labelStep);
			if(currLsp.getFrontLabelValue() > 1048575){
				flag = false;
				break;
			}
			currLsp.setBackLabelValue(firstLsp.getBackLabelValue()+(i+1)*this.labelStep);
			if(currLsp.getBackLabelValue() > 1048575){
				flag = false;
				break;
			}
			if(oldBackLabel != currLsp.getBackLabelValue()){
				flag = this.checkLabelStep2(labelInfoServiceMB, currLsp.getBackLabelValue(), currLsp.getASiteId(), 
					currLsp.getZPortId(), currLsp.getZSiteId());
			}
			if(flag){
				if(oldFrontLabel != currLsp.getFrontLabelValue()){
					flag = this.checkLabelStep2(labelInfoServiceMB, currLsp.getFrontLabelValue(), currLsp.getZSiteId(),
							currLsp.getAPortId(), currLsp.getASiteId());
					if(!flag){
						break;
					}
				}
			}
		}
		return flag;
	}

	private boolean checkLabelStep2(LabelInfoService_MB labelInfoServiceMB, int labelValue, int aSiteId, int zPortId, int zSiteId) {
		try {
			boolean flag = labelInfoServiceMB.isUsedLabel(labelValue, aSiteId, "TUNNEL");
			if (flag) {
				flag = labelInfoServiceMB.checkingOutLabel(labelValue, zPortId, zSiteId, "TUNNEL");
			}
			return flag;
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return false;
	}

	/**
	 * 筛选中符合要求的tunnel
	 */
	private boolean filterTunnelList(Tunnel firstTunnel, Tunnel tunnel) {
		boolean flag = true;
		String tunnelType = firstTunnel.getTunnelType();
		if(tunnelType.equals(tunnel.getTunnelType())){
			if(tunnel.getaSiteId() == firstTunnel.getaSiteId() && tunnel.getzSiteId() == firstTunnel.getzSiteId()){
				List<Lsp> lspList = tunnel.getLspParticularList();
				if(lspList.size() == firstTunnel.getLspParticularList().size()){
					for (int j = 0; j < lspList.size(); j++) {
						Lsp firstLsp = firstTunnel.getLspParticularList().get(j);
						if(lspList.get(j).getASiteId() != firstLsp.getASiteId() || lspList.get(j).getZSiteId() != firstLsp.getZSiteId()){
							flag = false;
							break;
						}
					}
					return flag;
				}
			}
		}
		return false;
	}
}
