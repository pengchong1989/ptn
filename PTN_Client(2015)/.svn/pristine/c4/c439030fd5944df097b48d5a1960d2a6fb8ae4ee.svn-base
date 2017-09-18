package com.nms.ui.ptn.ne.statusData.protectStatus.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.ProtectStatus;
import com.nms.db.bean.ptn.SiteStatusInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.statusData.protectStatus.controller.ProtectStatusController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class ProtectStatusPanel extends ContentView<ProtectStatus>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6085820139836807260L;
	private PtnButton jButton;
	private ProtectStatusPanel view;
	public ProtectStatusPanel() {
		super("protectStatus",RootFactory.CORE_MANAGE);
		view = this;
		init();
		add();
	}
	
	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_LSPPROTECT_STATUS)));
		setLayout();
		try {
			getController().refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	@Override
	public List<JButton> setAddButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		jButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SELECT),true);
		needRemoveButtons.add(jButton);
		return needRemoveButtons;
	}
	
	public void add(){
		jButton.addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SiteService_MB siteService = null;
				SiteInst siteInst = null;
				DispatchUtil siteDispatch = null;
				SiteStatusInfo result = null;
				try {
					siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
					siteInst = siteService.select(ConstantUtil.siteId);
					siteInst.setStatusMark(19);
					siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
					result = siteDispatch.siteStatus(siteInst);
					if(null!=result&&result.getProtectStatusList() != null){
						DialogBoxUtil.succeedDialog(null, ResultString.CONFIG_SELECT);
						initValue(result.getProtectStatusList());
						view.updateUI();
						view.initData(result.getProtectStatusList());
						this.insertOpeLog(EOperationLogType.LSPPROTECT.getValue(), ResultString.CONFIG_SELECT, null,null);				
					}else{
						DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_FIND_DETAIL_FAIL));
						this.insertOpeLog(EOperationLogType.LSPPROTECT.getValue(), ResourceUtil.srcStr(StringKeysTip.TIP_FIND_DETAIL_FAIL), null,null);
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				} finally {
					UiUtil.closeService_MB(siteService);
				}
			}

			private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
			   AddOperateLog.insertOperLog(jButton, operationType, result, oldMac, newMac, ConstantUtil.siteId,ResourceUtil.srcStr(StringKeysPanel.PANEL_LSPPROTECT_STATUS),"");
			}
			
			@Override
			public boolean checking() {
				// TODO Auto-generated method stub
				return true;
			}
		});
	}
	
	public void initValue(List<ProtectStatus> protectStatusList){
		TunnelService_MB tunnelService = null;
		Tunnel standTunnel = null;
		Tunnel mianTunnel = null;
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			if(protectStatusList != null){
				for(ProtectStatus protectStatus : protectStatusList){
					//在这里抓异常 可以防止每条tunnel LSP 状态信息 各不影响
					try {
						standTunnel = new Tunnel();
						standTunnel.setTunnelId(Integer.parseInt(protectStatus.getName()));
						standTunnel = tunnelService.selectBySiteIdAndProtectId(ConstantUtil.siteId,Integer.parseInt(protectStatus.getName()));
						mianTunnel = new Tunnel();
						mianTunnel.setProtectTunnelId(standTunnel.getTunnelId());
						mianTunnel = tunnelService.select_nojoin(mianTunnel).get(0);
						
						protectStatus.setName(mianTunnel.getTunnelName());
						if(mianTunnel.getASiteId() == ConstantUtil.siteId){
							protectStatus.setMainPort(portService.getPortname(mianTunnel.getAPortId()));
							protectStatus.setStandPort(portService.getPortname(standTunnel.getAPortId()));
						}else{
							protectStatus.setMainPort(portService.getPortname(mianTunnel.getZPortId()));
							protectStatus.setStandPort(portService.getPortname(standTunnel.getZPortId()));
						}
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(portService);
		}
	}
	
	private void setLayout() {
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		panelLayout.rowHeights = new int[] {400, 10};
		panelLayout.rowWeights = new double[] {0, 0};
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(this.getContentPanel(), c);
		this.add(this.getContentPanel());
	}
	
	@Override
	public void setController() {
		controller = new ProtectStatusController(this);
	} 
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getUpdateButton());
		needRemoveButtons.add(getSynchroButton());
		needRemoveButtons.add(getRefreshButton());
		return needRemoveButtons;
	}
	
}
