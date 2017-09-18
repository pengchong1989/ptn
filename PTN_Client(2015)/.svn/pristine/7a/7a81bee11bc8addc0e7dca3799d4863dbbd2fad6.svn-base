package com.nms.ui.ptn.business.ces;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.client.Client;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortStmTimeslot;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.ECesType;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.client.ClientService_MB;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.port.PortStmTimeslotService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.filter.impl.CesFilterDialog;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.CheckingUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ListingFilter;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.basicinfo.dialog.segment.SearchSegmentDialog;
import com.nms.ui.ptn.business.ces.bean.CesPortTableBean;
import com.nms.ui.ptn.business.dialog.cespath.AddCesDialog;
import com.nms.ui.ptn.business.dialog.cespath.controller.CesHandlerController;

/**
 * 网络侧 -处理CES
 * 
 * @author lepan
 */
public class CesBusinessController extends AbstractController {

	private CesBusinessPanel view;
	private CesInfo cesInfo = null;
	private List<CesInfo> infos = new ArrayList<CesInfo>();
	private int total;
	private int now = 0;
	
	public CesBusinessController(CesBusinessPanel view) {
		this.view = view;
		try {
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * 修改
	 */
	@Override
	public void openUpdateDialog() throws Exception {
		CesInfo infos = null;
		try {
			infos = this.view.getSelect();
			if (infos == null) {
//				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL));
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			} else {
				AddCesDialog updateCesdialog = new AddCesDialog(this.view, true, infos);
				CesHandlerController updateCesController = new CesHandlerController(updateCesdialog);
				updateCesdialog.setSize(1200, 700);
				updateCesdialog.setMinimumSize(new Dimension(1200, 700));

				updateCesdialog.setLocation(UiUtil.getWindowWidth(updateCesdialog.getWidth()), UiUtil.getWindowHeight(updateCesdialog.getHeight()));
				updateCesdialog.setVisible(true);
				updateCesController.refresh();

			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			infos = null;
		}
	}

	/**
	 * 删除
	 */
	@Override
	public void delete() throws Exception {
		List<CesInfo> infos = null;
		boolean onlineFlag = false;
		List<Integer> allSiteIds = null;
		List<Integer> siteIds = null;
		try {
			infos = this.view.getAllSelect();
			////判断是否有在线网元托管，存在不允许删除
			SiteUtil siteUtil = new SiteUtil();
			allSiteIds = new ArrayList<Integer>();
			siteIds = new ArrayList<Integer>();
			for (CesInfo ces : infos) {
				allSiteIds.add(ces.getaSiteId());
				allSiteIds.add(ces.getzSiteId());
			}
			for(int i=0;i<allSiteIds.size();i++){
				if(1==siteUtil.SiteTypeOnlineUtil(allSiteIds.get(i))){
				   siteIds.add(allSiteIds.get(i));			    		
				}
		     }
			if(siteIds !=null && siteIds.size()!=0){
			   onlineFlag = true;
			}
			if(onlineFlag){
				WhImplUtil wu = new WhImplUtil();
				String str=wu.getNeNames(siteIds);
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_DELETEONLINE)+""+str+ResourceUtil.srcStr(StringKeysTip.TIP_ONLINENOT_DELETEONLINE));
				return ;
			}
			
			DispatchUtil cesDispatch = new DispatchUtil(RmiKeys.RMI_CES);
			String isSuccess = cesDispatch.excuteDelete(infos);
			DialogBoxUtil.succeedDialog(this.view, isSuccess);
			// 添加日志记录
			for (CesInfo info : infos) {
				AddOperateLog.insertOperLog(null, EOperationLogType.CESDELETE.getValue(), isSuccess,
						null, null, info.getaSiteId(), info.getName(), null);
				AddOperateLog.insertOperLog(null, EOperationLogType.CESDELETE.getValue(), isSuccess,
						null, null, info.getzSiteId(), info.getName(), null);
			}
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			infos = null;
			allSiteIds= null;
			siteIds = null;
		}
	}

	/**
	 * 新建
	 */
	@Override
	public void openCreateDialog() throws Exception {
		AddCesDialog addCesdialog = new AddCesDialog(this.view, true);
		CesHandlerController AddCesController = new CesHandlerController(addCesdialog);
//		addCesdialog.setSize(800, 690);
		addCesdialog.setMinimumSize(new Dimension(1200, 700));
		addCesdialog.setLocation(UiUtil.getWindowWidth(addCesdialog.getWidth()), UiUtil.getWindowHeight(addCesdialog.getHeight()));
		addCesdialog.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_CES));
		addCesdialog.setVisible(true);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void refresh() throws Exception {
		
		CesInfoService_MB service = null;
		ListingFilter filter = null;
		List<CesInfo> needs = new ArrayList<CesInfo>();
		try {
			filter = new ListingFilter();
			service = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			
			if(null==this.cesInfo){
				this.cesInfo=new CesInfo();
			}
			
			infos = (List<CesInfo>) filter.filterList(service.filterSelect(this.cesInfo));
			
			if(infos.size() ==0){
				now = 0;
				view.getNextPageBtn().setEnabled(false);
				view.getGoToJButton().setEnabled(false);
			}else{
				now =1;
				if (infos.size() % ConstantUtil.flipNumber == 0) {
					total = infos.size() / ConstantUtil.flipNumber;
				} else {
					total = infos.size() / ConstantUtil.flipNumber + 1;
				}
				if (total == 1) {
					view.getNextPageBtn().setEnabled(false);
					view.getGoToJButton().setEnabled(false);
				}else{
					view.getNextPageBtn().setEnabled(true);
					view.getGoToJButton().setEnabled(true);
				}
				if (infos.size() - (now - 1) * ConstantUtil.flipNumber > ConstantUtil.flipNumber) {
					needs = infos.subList((now - 1) * ConstantUtil.flipNumber, ConstantUtil.flipNumber);
				} else {
					needs = infos.subList((now - 1) * ConstantUtil.flipNumber, infos.size() - (now - 1) * ConstantUtil.flipNumber);
				}
			}
			view.getCurrPageLabel().setText(now+"");
			view.getTotalPageLabel().setText(total + "");
			view.getPrevPageBtn().setEnabled(false);
			
			this.view.clear();

			if (view.getTopoPanel() != null) {
				view.getTopoPanel().clear();
			}
			if (view.getClientInfoPanel() != null) {
				view.getClientInfoPanel().clear();
			}
			if (view.getSchematize_panel() != null) {
				view.getSchematize_panel().clear();
			}
			if (view.getCesPortNetworkTablePanel() != null) {
				view.getCesPortNetworkTablePanel().clear();
			}
			if (view.getPwNetworkTablePanel() != null){
				this.view.getPwNetworkTablePanel().clear();
			}
			this.view.initData(needs);
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			filter = null;
			UiUtil.closeService_MB(service);
			filter = null;
		}
	}

	@Override
	public void search() throws Exception {
		try {

			new SearchSegmentDialog(this.view);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * 选中一条记录后，查看详细信息
	 */
	@Override
	public void initDetailInfo() {
		try {
			initTopoPanel();
			this.initPwNetworkTablePanel();
			this.initPortPanel();
			this.initSchematizePanel();
			initClientInfo();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	
	private void initPwNetworkTablePanel() {
		PwInfoService_MB pwService = null;
		try {
			CesInfo cesInfo = this.view.getSelect();
			pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			PwInfo pw = pwService.selectByPwId(cesInfo.getPwId());
			List<PwInfo> pwList = new ArrayList<PwInfo>();
			pwList.add(pw);
			this.view.getPwNetworkTablePanel().initData(pwList);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(pwService);
		}
	}

	/**
	 * 列表点击事件（客户信息）
	 */
	private void initClientInfo() {
		CesInfo cesInfo = null;
		ClientService_MB clientService = null;
		List<Client> clientList = null;
		try {
			clientService = (ClientService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CLIENTSERVICE);
			cesInfo = view.getSelect();
			if (0 != cesInfo.getClientId()) {
				clientList = clientService.select(cesInfo.getClientId());
				this.view.getClientInfoPanel().clear();
				this.view.getClientInfoPanel().initData(clientList);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			cesInfo = null;
			clientList = null;
			UiUtil.closeService_MB(clientService);
		}

	}

	/**
	 * 初始化图形化界面数据
	 * 
	 * @author kk
	 * 
	 * @Exception 异常对象
	 */
	private void initSchematizePanel() {
		CesInfo cesInfo = null;
		try {
			cesInfo = view.getSelect();
			this.view.getSchematize_panel().clear();
			this.view.getSchematize_panel().initData(cesInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			cesInfo = null;
		}
	}

	/**
	 * 绑定端口列表数据
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void initPortPanel() {

		CesInfo cesInfo = null;
		List<CesPortTableBean> cesPortList = null;
		try {
			cesPortList = new ArrayList<CesPortTableBean>();
			cesInfo = view.getSelect();
			if (ECesType.PDH.getValue() == cesInfo.getCestype()) {
				cesPortList.add(this.getPdhPort(cesInfo.getaAcId())); // a端pdh
				cesPortList.add(this.getPdhPort(cesInfo.getzAcId())); // z端pdh
			} else if (ECesType.PDHSDH.getValue() == cesInfo.getCestype()) {
				cesPortList.add(this.getPdhPort(cesInfo.getaAcId())); // a端pdh
				cesPortList.add(this.getSdhPort(cesInfo.getzAcId())); // z端sdh
			} else if (ECesType.SDH.getValue() == cesInfo.getCestype()) {
				cesPortList.add(this.getSdhPort(cesInfo.getaAcId())); // a端sdh
				cesPortList.add(this.getSdhPort(cesInfo.getzAcId())); // z端sdh
			} else if (ECesType.SDHPDH.getValue() == cesInfo.getCestype()) {
				cesPortList.add(this.getSdhPort(cesInfo.getaAcId())); // a端sdh
				cesPortList.add(this.getPdhPort(cesInfo.getzAcId())); // z端pdh
			}
			this.view.getCesPortNetworkTablePanel().clear();
			this.view.getCesPortNetworkTablePanel().initData(cesPortList);

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			cesInfo = null;
			cesPortList = null;
		}
	}

	/**
	 * 
	 * 获取sdh端口bean
	 * 
	 * @author kk
	 * 
	 * @param id
	 *            时隙表主键
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private CesPortTableBean getSdhPort(int id) throws Exception {

		CesPortTableBean cesPortTableBean = null;
		PortStmTimeslot portStmTimeslot = null;
		SiteService_MB siteServiceMB = null;
		PortService_MB portServiceMB = null;
		PortStmTimeslotService_MB portStmTimeslotService = null;
		try {
			portServiceMB = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			siteServiceMB = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			cesPortTableBean = new CesPortTableBean();
			portStmTimeslotService = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);
			portStmTimeslot = portStmTimeslotService.selectById(id);
			cesPortTableBean.setObjectname(portStmTimeslot.getTimeslotnumber());
			cesPortTableBean.setPorttype("SDH");
			cesPortTableBean.setSitename(siteServiceMB.getSiteName(portStmTimeslot.getSiteId()));
			cesPortTableBean.setPortname(portServiceMB.getPortname(portStmTimeslot.getPortid()));
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portServiceMB);
			UiUtil.closeService_MB(siteServiceMB);
			UiUtil.closeService_MB(portStmTimeslotService);
		}
		return cesPortTableBean;
	}

	/**
	 * 
	 * 获取pdh端口bean
	 * 
	 * @author kk
	 * 
	 * @param id
	 *            时隙表主键
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private CesPortTableBean getPdhPort(int id) throws Exception {

		CesPortTableBean cesPortTableBean = null;
		PortService_MB portService = null;
		PortInst portInst = null;
		List<PortInst> portList = null;
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			cesPortTableBean = new CesPortTableBean();
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInst = new PortInst();
			portInst.setPortId(id);
			portList = portService.select(portInst);

			if (null != portList && portList.size() == 1) {
				portInst = portList.get(0);
				cesPortTableBean.setObjectname(portInst.getPortName());
				cesPortTableBean.setPorttype("PDH");
				cesPortTableBean.setSitename(siteService.getSiteName(portInst.getSiteId()));
				cesPortTableBean.setPortname(portInst.getPortName());
			} else {
				cesPortTableBean = null;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(siteService);
		}
		return cesPortTableBean;
	}

	private void initTopoPanel() {
		CesInfo info = null;
		try {
			info = view.getSelect();
			view.getTopoPanel().clear();
			view.getTopoPanel().initData(info);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			info = null;
		}
	}

	/**
	 * 激活处理事件
	 */
	public void doActive() {
		List<CesInfo> infos = null;
		DispatchUtil cesDispatch;
		String result = null;
		try {
			cesDispatch = new DispatchUtil(RmiKeys.RMI_CES);
			infos = this.view.getAllSelect();
			int failCount = 0;
			if (infos != null && infos.size() > 0) {
				for (CesInfo cesInfo : infos) {
					cesInfo.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
					result = cesDispatch.excuteUpdate(cesInfo);
					if(result == null || !result.contains(ResultString.CONFIG_SUCCESS)){
						failCount++;
					}
					//添加日志记录*************************/
					AddOperateLog.insertOperLog(null, EOperationLogType.BUSINESSCESACTIVE.getValue(), result, null, null, -1, cesInfo.getName(), null);
					//************************************/
				}
				result = ResourceUtil.srcStr(StringKeysTip.TIP_BATCH_CREATE_RESULT);
				result = result.replace("{C}", (infos.size()-failCount) + "");
				result = result.replace("{S}", failCount + "");
			}
			String str = this.getOfflineSiteIdNames(infos);
			if(!str.equals("")){
				result += ","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
			DialogBoxUtil.succeedDialog(this.view, result);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			infos = null;
			cesDispatch = null;
		}
	}
	
	private String getOfflineSiteIdNames(List<CesInfo> cesList) throws Exception {
		List<Integer> siteIds = null;
		String str = "";
		try {
			siteIds = new ArrayList<Integer>();
			for (CesInfo ces : cesList) {
				siteIds.add(ces.getaSiteId());
				siteIds.add(ces.getzSiteId());
			}
			str = new WhImplUtil().getNeNames(siteIds);
		} catch (Exception e) {
			throw e;
		}
		return str;
	}

	/**
	 * 去激活处理事件
	 */
	public void doUnActive() {
		List<CesInfo> infos = null;
		DispatchUtil cesDispatch;
		String result = null;
		try {
			cesDispatch = new DispatchUtil(RmiKeys.RMI_CES);
			infos = this.view.getAllSelect();
			int failCount = 0;
			if (infos != null && infos.size() > 0) {
				for (CesInfo cesInfo : infos) {
					cesInfo.setActiveStatus(EActiveStatus.UNACTIVITY.getValue());
					result = cesDispatch.excuteUpdate(cesInfo);
					if(result == null || !result.contains(ResultString.CONFIG_SUCCESS)){
						failCount++;
					}
					//添加日志记录*************************/
					AddOperateLog.insertOperLog(null, EOperationLogType.BUSINESSCESDOACTIVE.getValue(), result, null, null, -1, cesInfo.getName(), null);
					//************************************/
				}
				result = ResourceUtil.srcStr(StringKeysTip.TIP_BATCH_CREATE_RESULT);
				result = result.replace("{C}", (infos.size()-failCount) + "");
				result = result.replace("{S}", failCount + "");
			}
			String str = this.getOfflineSiteIdNames(infos);
			if(!str.equals("")){
				result += ","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
			DialogBoxUtil.succeedDialog(this.view, result);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			infos = null;
			cesDispatch = null;
		}
	}

	@Override
	public void openFilterDialog() throws Exception {
		new CesFilterDialog(this.cesInfo);
		this.refresh();
	}

	// 清除过滤
	public void clearFilter() throws Exception {
		this.cesInfo = null;
		this.refresh();
	}
	
	@Override
	public void prevPage() throws Exception {
		now = now - 1;
		if (now == 1) {
			view.getPrevPageBtn().setEnabled(false);
		}
		view.getNextPageBtn().setEnabled(true);

		flipRefresh();
	}

	@Override
	public void goToAction() throws Exception {

		if (CheckingUtil.checking(view.getGoToTextField().getText(), CheckingUtil.NUM1_9)) {// 判断填写是否为数字
			Integer goi = Integer.parseInt(view.getGoToTextField().getText());
			if(goi>= total){
				goi = total;
				view.getNextPageBtn().setEnabled(false);
			}
			if(goi == 1){
				view.getPrevPageBtn().setEnabled(false);
			}
			if(goi > 1){
				view.getPrevPageBtn().setEnabled(true);
			}
			if(goi<total){
				view.getNextPageBtn().setEnabled(true);
			}
			now = goi;
			flipRefresh();
		}else{
			DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.MESSAGE_NUMBER));
		}
		
	
	}

	@Override
	public void nextPage() throws Exception {
		now = now + 1;
		if (now == total) {
			view.getNextPageBtn().setEnabled(false);
		}
		view.getPrevPageBtn().setEnabled(true);
		flipRefresh();
	}

	private void flipRefresh() {
		view.getCurrPageLabel().setText(now + "");
		List<CesInfo> needs = null;
		if (now * ConstantUtil.flipNumber > infos.size()) {
			needs = infos.subList((now - 1) * ConstantUtil.flipNumber, infos.size());
		} else {
			needs = infos.subList((now - 1) * ConstantUtil.flipNumber, now * ConstantUtil.flipNumber);
		}
		this.view.clear();

		if (view.getTopoPanel() != null) {
			view.getTopoPanel().clear();
		}
		if (view.getClientInfoPanel() != null) {
			view.getClientInfoPanel().clear();
		}
		if (view.getSchematize_panel() != null) {
			view.getSchematize_panel().clear();
		}
		if (view.getCesPortNetworkTablePanel() != null) {
			view.getCesPortNetworkTablePanel().clear();
		}
		if (view.getPwNetworkTablePanel() != null){
			this.view.getPwNetworkTablePanel().clear();
		}
		this.view.initData(needs);
		this.view.updateUI();
	}
	
}
