package com.nms.ui.ptn.ne.group.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import com.nms.db.bean.ptn.path.GroupSpreadInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.GroupSpreadService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.CheckingUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.ne.group.view.GroupSpreadDialog;
import com.nms.ui.ptn.ne.group.view.GroupSpreadPanel;
import com.nms.ui.ptn.ne.group.view.PortChoiceDialog;

public class GroupSpreadController extends AbstractController {

	private GroupSpreadPanel view;
	GroupSpreadDialog groupSpreadDialog = null;
	private GroupSpreadInfo groupInfo = new GroupSpreadInfo();

	public GroupSpreadController(GroupSpreadPanel view) {
		this.view = view;
	}

	@Override
	public void refresh() throws Exception {
		List<GroupSpreadInfo> groupInfoList = null;
		try {
			groupInfoList = getAllGroupInfo();
			view.clear();
			view.initData(groupInfoList);
			view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			groupInfoList = null;
		}
	}

	private List<GroupSpreadInfo> getAllGroupInfo() {
		GroupSpreadService_MB groupService = null;
		GroupSpreadInfo groupInfo = null;
		List<GroupSpreadInfo> infoList = null;
		try {
			groupService = (GroupSpreadService_MB) ConstantUtil.serviceFactory.newService_MB(Services.GROUPSPREAD);
			groupInfo = new GroupSpreadInfo();
			groupInfo.setSiteId(ConstantUtil.siteId);
			infoList = groupService.query(groupInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(groupService);
		}
		return infoList;
	}

	@Override
	public void openCreateDialog() throws Exception {
		SiteService_MB siteService = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if (siteService.getManufacturer(ConstantUtil.siteId) == EManufacturer.WUHAN.getValue()) {
				groupSpreadDialog = new GroupSpreadDialog(this.view);
				groupSpreadDialog.setSize(400, 300);
				click(groupSpreadDialog);
			}
			groupSpreadDialog.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_GROUPSPREAD));
			groupSpreadDialog.setLocation(UiUtil.getWindowWidth(groupSpreadDialog.getWidth()), UiUtil.getWindowHeight(groupSpreadDialog.getHeight()));
			groupSpreadDialog.setVisible(true);
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
	}

	@Override
	public void openUpdateDialog() throws Exception {
		List<GroupSpreadInfo> infos = null;
		try {
			infos = this.view.getAllSelect();
			if (infos == null || infos.size() == 0) {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			} else {
				groupSpreadDialog = new GroupSpreadDialog(this.view, infos.get(0));
				groupSpreadDialog.setSize(400, 300);
				groupInfo = infos.get(0);
				click(groupSpreadDialog);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		groupSpreadDialog.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_GROUPSPREAD));
		groupSpreadDialog.setLocation(UiUtil.getWindowWidth(groupSpreadDialog.getWidth()), UiUtil.getWindowHeight(groupSpreadDialog.getHeight()));
		groupSpreadDialog.setVisible(true);
	}

	@Override
	public void delete() throws Exception {
		List<GroupSpreadInfo> infos = null;
		try {
			infos = this.view.getAllSelect();
			String str = null;
			DispatchUtil groupDispatch = new DispatchUtil(RmiKeys.RMI_GROUPSPREAD);
			str = groupDispatch.excuteDelete(infos);
			DialogBoxUtil.succeedDialog(this.view, str);
			for (GroupSpreadInfo groupSpreadInfo : infos) {
				AddOperateLog.insertOperLog(null, EOperationLogType.GROUPSPREADDELETE.getValue(), str,
						null, null, ConstantUtil.siteId, groupSpreadInfo.getSmId()+"", null);
			}
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	public void click(GroupSpreadDialog groupSpreadDialog2) {
		// 端口成员
		(groupSpreadDialog2).getPortChoiceButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final PortChoiceDialog dialog = new PortChoiceDialog(arg0.getActionCommand());
				dialog.setSize(700, 450);
				dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));
				dialog.init(groupInfo.getPortChoice());
//				dialog.addWindowListener(new WindowAdapter() {
//					@Override
//					public void windowClosed(WindowEvent e) {
//						dialog.dispose();
//					}
//				});
				dialog.getConfirm().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							groupInfo.setPortChoice(dialog.get());
							DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
							dialog.dispose();
						} catch (Exception e) {
							ExceptionManage.dispose(e,this.getClass());
						}
					}
				});
				dialog.getCancel().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						dialog.dispose();
					}
				});
				dialog.setVisible(true);
			}
		});

		(groupSpreadDialog).getOkButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!(groupSpreadDialog.getMacAddText().getText().matches(CheckingUtil.MAC_REGULAR))){
					DialogBoxUtil.errorDialog(groupSpreadDialog, ResourceUtil.srcStr(StringKeysTip.TIP_MACERROR));
				}else if(!groupSpreadDialog.vplsExist()){
					DialogBoxUtil.errorDialog(groupSpreadDialog, ResourceUtil.srcStr(StringKeysTip.TIP_NO_VPLS));
				}else{
					try {
						groupInfo = groupSpreadDialog.get(groupInfo);
						String result = null;
						DispatchUtil groupDispatch = new DispatchUtil(RmiKeys.RMI_GROUPSPREAD);
						if (groupInfo.getId() > 0) {
						result = groupDispatch.excuteUpdate(groupInfo);
					} else {
						result = groupDispatch.excuteInsert(groupInfo);
					}
						DialogBoxUtil.succeedDialog(view, result);
						groupSpreadDialog.dispose();
						refresh();
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				}
			}
		});
		(groupSpreadDialog).getCancelButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				groupSpreadDialog.dispose();
			}
		});
	}

	public GroupSpreadPanel getView() {
		return view;
	}

	public void setView(GroupSpreadPanel view) {
		this.view = view;
	}

	public GroupSpreadInfo getGroupInfo() {
		return groupInfo;
	}

	public void setGroupInfo(GroupSpreadInfo groupInfo) {
		this.groupInfo = groupInfo;
	}

}
