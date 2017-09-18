package com.nms.ui.ptn.ne.stauni.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import com.nms.db.bean.ptn.path.StaticUnicastInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.SingleSpreadService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.CheckingUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.ne.stauni.view.AddUnicastDialog;
import com.nms.ui.ptn.ne.stauni.view.StaticUniPanel;

public class StaticUniController extends AbstractController {

	private StaticUniPanel view;
	AddUnicastDialog addUnicastDialog = null;
	private StaticUnicastInfo uniInfo = new StaticUnicastInfo();

	public StaticUniController(StaticUniPanel view) {
		this.view = view;
	}

	@Override
	public void refresh() throws Exception {
		List<StaticUnicastInfo> uniInfoList = null;
		try {
			uniInfoList = getAllUniInfo();
			view.clear();
			view.initData(uniInfoList);
			view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			uniInfoList = null;
		}
	}

	private List<StaticUnicastInfo> getAllUniInfo() {
		SingleSpreadService_MB uniService = null;
		StaticUnicastInfo info = null;
		List<StaticUnicastInfo> infoList = null;
		try {
			uniService = (SingleSpreadService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SINGELSPREAD);
			info = new StaticUnicastInfo();
			info.setSiteId(ConstantUtil.siteId);
			infoList = uniService.query(info.getSiteId());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(uniService);
		}
		return infoList;
	}

	/**
	 * 同步
	 */
	@Override
	public void synchro() {
		DispatchUtil staticDispatch = null;
		try {
			staticDispatch = new DispatchUtil(RmiKeys.RMI_STATICUNI);
			String result = staticDispatch.synchro(ConstantUtil.siteId);
			DialogBoxUtil.succeedDialog(null, result);
			//添加日志记录
			AddOperateLog.insertOperLog(null, EOperationLogType.SYNCSTATICUNI.getValue(), result,
					null, null, ConstantUtil.siteId, ResourceUtil.srcStr(StringKeysPanel.PANEL_STATIC_MANAGE), null);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			staticDispatch = null;
		}
	}
	
	// 创建
	@Override
	public void openCreateDialog() throws Exception {
		SiteService_MB siteService = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if (siteService.getManufacturer(ConstantUtil.siteId) == EManufacturer.WUHAN.getValue()) {
				addUnicastDialog = new AddUnicastDialog(this.view,"create");
				addUnicastDialog.setSize(400, 300);
				click(addUnicastDialog);
			}
			addUnicastDialog.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_STATIC));
			addUnicastDialog.setLocation(UiUtil.getWindowWidth(addUnicastDialog.getWidth()), UiUtil.getWindowHeight(addUnicastDialog.getHeight()));
			addUnicastDialog.setVisible(true);
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
	}

	@Override
	public void openUpdateDialog() throws Exception {
		List<StaticUnicastInfo> infos = null;
		try {
			infos = this.view.getAllSelect();
			if (infos == null || infos.size() == 0) {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			} else {
				
				addUnicastDialog = new AddUnicastDialog(this.view, infos.get(0),"modify");
				addUnicastDialog.setSize(400, 300);
				uniInfo = infos.get(0);
				click(addUnicastDialog);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		addUnicastDialog.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_MODIFY_STATIC));
		addUnicastDialog.setLocation(UiUtil.getWindowWidth(addUnicastDialog.getWidth()), UiUtil.getWindowHeight(addUnicastDialog.getHeight()));
		addUnicastDialog.setVisible(true);

	}

	@Override
	public void delete() throws Exception {
		List<StaticUnicastInfo> infos = null;
		List<Integer> siteIds = null;
		try {
			infos = this.view.getAllSelect();
			SiteUtil siteUtil = new SiteUtil();
			if(1==siteUtil.SiteTypeOnlineUtil(ConstantUtil.siteId)){
				WhImplUtil wu = new WhImplUtil();
				siteIds = new ArrayList<Integer>();
				siteIds.add(ConstantUtil.siteId);
				String str1=wu.getNeNames(siteIds);
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_DELETEONLINE)+""+str1+ResourceUtil.srcStr(StringKeysTip.TIP_ONLINENOT_DELETEONLINE));
				return;  		    		
				}
			String str = null;
			DispatchUtil uniDispatch = new DispatchUtil(RmiKeys.RMI_STATICUNI);
			str = uniDispatch.excuteDelete(infos);
			DialogBoxUtil.succeedDialog(this.view, str); 
			for (StaticUnicastInfo info : infos) {
				AddOperateLog.insertOperLog(null, EOperationLogType.DELSTATICUNI.getValue(), str,
						null, null, ConstantUtil.siteId, ResourceUtil.srcStr(StringKeysPanel.PANEL_STATIC_MANAGE), null);
			}
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			infos = null;
			siteIds = null;
		}
	}

	// 点击事件
	public void click(final AddUnicastDialog addUnicastDialog) {
		(addUnicastDialog).getOkButton().addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!(addUnicastDialog.getMacText().getText().matches(CheckingUtil.MAC_REGULAR))){
					DialogBoxUtil.errorDialog(addUnicastDialog, ResourceUtil.srcStr(StringKeysTip.TIP_MACERROR));
				}else if(!addUnicastDialog.vplsExist()){
					DialogBoxUtil.errorDialog(addUnicastDialog, ResourceUtil.srcStr(StringKeysTip.TIP_NO_VPLS));
				}else{
					try {
						StaticUnicastInfo unicastBefore = new StaticUnicastInfo();
						unicastBefore.setAcPortNameLog(addUnicastDialog.getAcNameLogBefore());
						unicastBefore.setMacAddress(uniInfo.getMacAddress());
						uniInfo = addUnicastDialog.pageSetValue(uniInfo);
						uniInfo.setVplsNameLog(null);
						String result = null;
						DispatchUtil uniDispatch = new DispatchUtil(RmiKeys.RMI_STATICUNI);
						if(addUnicastDialog.getType().equals("modify")) 
						{
							result = uniDispatch.excuteUpdate(uniInfo);
							AddOperateLog.insertOperLog(addUnicastDialog.getOkButton(), EOperationLogType.UPDATESTATICUNI.getValue(), result, 
									unicastBefore, uniInfo, ConstantUtil.siteId, ResourceUtil.srcStr(StringKeysPanel.PANEL_STATIC_MANAGE), "unicast");
						}else if (addUnicastDialog.getType().equals("create"))
						{
							result = uniDispatch.excuteInsert(uniInfo);
							AddOperateLog.insertOperLog(addUnicastDialog.getOkButton(), EOperationLogType.NEWSTATICUNI.getValue(), result, 
									null, uniInfo, ConstantUtil.siteId, ResourceUtil.srcStr(StringKeysPanel.PANEL_STATIC_MANAGE), "unicast");
						}
						DialogBoxUtil.succeedDialog(view, result);
						addUnicastDialog.dispose();
						refresh();
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				}
			}

			@Override
			public boolean checking() {
				return true;
			}
		});
		(addUnicastDialog).getCancelButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addUnicastDialog.dispose();
			}
		});
	}

	public StaticUniPanel getView() {
		return view;
	}

	public void setView(StaticUniPanel view) {
		this.view = view;
	}

	public StaticUnicastInfo getUniInfo() {
		return uniInfo;
	}

	public void setUniInfo(StaticUnicastInfo uniInfo) {
		this.uniInfo = uniInfo;
	}

}
