package com.nms.ui.ptn.ne.macManagement.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;

import com.nms.db.bean.ptn.MacManagementInfo;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class MacInfoPanel extends ContentView<MacManagementInfo> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8117192637117963685L;
	private JButton deleteAllBtn;
	private JButton deleteBtn;
	private MacInfoPanel view;
	@SuppressWarnings("unused")
	private MacManagementInfo mac;
	public MacInfoPanel() {
		super("macAddressTable", RootFactory.CORE_MANAGE);
		this.setLayout();
		this.addListener();
		view = this;
	}
	
	private void setLayout() {
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.fill = GridBagConstraints.BOTH;
		panelLayout.setConstraints(getContentPanel(), c);
		this.add(getContentPanel());
	}
	
	private void addListener() {
		this.deleteBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<MacManagementInfo> macList = null;
				DispatchUtil macManagementDispatch = null;
				String resultStr = null;
				try {
					if(view.getAllSelect().size() == 1){
						macList = view.getAllSelect();
						for (MacManagementInfo m : macList) {
							//把该端口下的一条mac删除,保留其他信息
							mac = m;
						}
						macManagementDispatch = new DispatchUtil(RmiKeys.RMI_BLACKLISTMAC);
						resultStr = macManagementDispatch.excuteDelete(macList);
						DialogBoxUtil.succeedDialog(view, resultStr);
						view.refresh();
					}else{
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				} finally {
					macList = null;
					macManagementDispatch = null;
					resultStr = null;
				}
			}
		});
		
		this.deleteAllBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<MacManagementInfo> macList = null;
				DispatchUtil macManagementDispatch = null;
				String resultStr = null;
				try {
					macList = view.getTable().getAllElement();
					if(macList == null || macList.size() == 0){
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_NO_DATA));
						return;
					}

					macManagementDispatch = new DispatchUtil(RmiKeys.RMI_BLACKLISTMAC);
					resultStr = macManagementDispatch.excuteDelete(macList);
					DialogBoxUtil.succeedDialog(view, resultStr);
					view.refresh();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				} finally {
					macList = null;
					macManagementDispatch = null;
					resultStr = null;
				}
			}
		});
		
	}
		
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getUpdateButton());
		needRemoveButtons.add(getSynchroButton());
		needRemoveButtons.add(getRefreshButton());
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		return needRemoveButtons;
	}
	
	@Override
	public List<JButton> setAddButtons() {
		List<JButton> needAddButtons = new ArrayList<JButton>();
		deleteAllBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE_ALL));
		deleteBtn = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_DELETE));
		needAddButtons.add(deleteBtn);
		needAddButtons.add(deleteAllBtn);
		return needAddButtons;
	}
	
	/**
	 * 刷新
	 */
	public void refresh() throws Exception {
		this.searchAndRefreshData();
	}
	
	private void searchAndRefreshData() throws Exception {
		List<MacManagementInfo> macList = new ArrayList<MacManagementInfo>();
//		MacManageService service = null;
		try {
//			if(mac != null){
//				service = (MacManageService) ConstantUtil.serviceFactory.newService(Services.MACMANAGE);
//				macList = service.selectByPortId(mac.getPortId());
//			}
			this.clear();
			this.initData(macList);
			this.updateUI();
		} catch (Exception e) {
			throw e;
		} finally {
//			UiUtil.closeService(service);
		}
	}

	@Override
	public void setController() {
		// TODO Auto-generated method stub
		
	}

}
