package com.nms.ui.ptn.ne.BFDManagement.controller;



import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import com.nms.db.bean.ptn.BfdInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.BfdInfoService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.BFDManagement.view.BfdDialog;
import com.nms.ui.ptn.ne.BFDManagement.view.BfdPanel;

public class BfdController extends AbstractController {

	private BfdPanel view;
	BfdDialog bfdDialog = null;
	private BfdInfo bfdInfo = new BfdInfo();

	public BfdController(BfdPanel view) {
		this.view = view;
	}

	@Override
	public void refresh() throws Exception {
		List<BfdInfo> bfdInfoList = null;
		try {
			bfdInfoList = getAllBfdInfo();
			view.clear();
			view.initData(bfdInfoList);
			view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			bfdInfoList = null;
		}
	}

	private List<BfdInfo> getAllBfdInfo() {
		BfdInfoService_MB bfdService = null;
		BfdInfo info = null;
		List<BfdInfo> infoList = null;
		try {
			bfdService = (BfdInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BFDMANAGEMENT);
			info = new BfdInfo();
			info.setSiteId(ConstantUtil.siteId);
			infoList = bfdService.selectByCondition(info);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(bfdService);
		}
		return infoList;
	}

	/**
	 * 同步
	 */
	@Override
	public void synchro() {
		DispatchUtil bfdDispatch = null;
		try {
			bfdDispatch = new DispatchUtil(RmiKeys.RMI_BFD);
			String result = bfdDispatch.synchro(ConstantUtil.siteId);
			DialogBoxUtil.succeedDialog(null, result);
			//添加日志记录
			AddOperateLog.insertOperLog(null, EOperationLogType.DELETEBFD.getValue(), result, 
					null, null, ConstantUtil.siteId, ResourceUtil.srcStr(StringKeysPanel.PANEL_BFDINFO), null);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			bfdDispatch = null;
		}
	}
	
	// 创建
	@Override
	public void openCreateDialog() throws Exception {
		SiteService_MB siteService = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if (siteService.getManufacturer(ConstantUtil.siteId) == EManufacturer.WUHAN.getValue()) {
				bfdDialog = new BfdDialog(this.view,null,this);
				bfdDialog.setLocation(UiUtil.getWindowWidth(bfdDialog.getWidth()) - 200, UiUtil.getWindowHeight(bfdDialog.getHeight()) / 2 - 8);
				if(ResourceUtil.language.equals("zh_CN")){
					bfdDialog.setSize(new Dimension(750, 410));
				}else{
					bfdDialog.setSize(new Dimension(880, 450));
				}
				
				bfdDialog.setVisible(true);
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
	}

	@Override
	public void openUpdateDialog() throws Exception {
		List<BfdInfo> infos = null;
		try {
			infos = this.view.getAllSelect();
			if (infos == null || infos.size() == 0) {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			} else {
				
				bfdDialog = new BfdDialog(this.view, infos.get(0),this);
				bfdDialog.setSize(750, 410);				
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		bfdDialog.setLocation(UiUtil.getWindowWidth(bfdDialog.getWidth()) - 200, UiUtil.getWindowHeight(bfdDialog.getHeight()) / 2 - 8);
		bfdDialog.setVisible(true);

	}

	@Override
	public void delete() throws Exception {
		List<BfdInfo> infos = null;
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
			DispatchUtil bfdDispatch = new DispatchUtil(RmiKeys.RMI_BFD);
			str = bfdDispatch.excuteDelete(infos);
			DialogBoxUtil.succeedDialog(this.view, str); 
			for (BfdInfo info : infos) {
				AddOperateLog.insertOperLog(null, EOperationLogType.DELETEBFD.getValue(), str, 
						null, null, ConstantUtil.siteId, info.getName(), null);
			}
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			infos = null;
			siteIds = null;
		}
	}

	
	public BfdPanel getView() {
		return view;
	}

	public void setView(BfdPanel view) {
		this.view = view;
	}

	public BfdInfo getBfdInfo() {
		return bfdInfo;
	}

	public void setBfdInfo(BfdInfo bfdInfo) {
		this.bfdInfo = bfdInfo;
	}

}
