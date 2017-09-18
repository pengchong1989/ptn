package com.nms.ui.ptn.business.tunnel;

import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Util;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.BfdInfoService_MB;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.ptn.qos.QosInfoService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.filter.impl.TunnelFilterDialog;
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
import com.nms.ui.ptn.basicinfo.dialog.segment.SearchSegmentDialog;
import com.nms.ui.ptn.business.dialog.tunnel.AddTunnelPathDialog;
import com.nms.ui.ptn.systemconfig.dialog.qos.ComparableSort;

/**
 * @author lepan
 */
public class TunnelBusinessController extends AbstractController {
	private final TunnelBusinessPanel view;
	private Tunnel filterCondition = null;// tunnel的过滤条件
	private int total;
	private int now = 1;
	private List<Tunnel> infos = null;
	public TunnelBusinessController(TunnelBusinessPanel view) {
		this.view = view;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void refresh() throws Exception {
		
		TunnelService_MB tunnelServiceMB = null;
		ListingFilter filter = null;
		List<Tunnel> needs = new ArrayList<Tunnel>();
		try {
			tunnelServiceMB = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			filter = new ListingFilter();
			// 判断若filtercondition 已被清空 ，则新创建一个新的查询条件，以供查询方法是用
			if (null == filterCondition) {
				filterCondition = new Tunnel();
			}
			infos = (List<Tunnel>) filter.filterList(tunnelServiceMB.filterSelect(this.filterCondition));
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
			tunnelServiceMB.setOnlyTunnelLsp(needs, false);
			this.view.clear();
			this.view.initData(needs);
			if (view.getOamTable() != null) {
				view.getOamTable().clear();
			}
			if (view.getTopoPanel() != null) {
				view.getTopoPanel().clear();
			}
			if (view.getSchematize_panel() != null) {
				view.getSchematize_panel().clear();
			}
			this.view.getQosPanel().clear();
			this.view.getLspNetworkTablePanel().clear();
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(tunnelServiceMB);
			filter = null;
		}
	}

	@Override
	public void delete() throws Exception {
		List<Tunnel> infos = null;
		DispatchUtil tunnelServiceImpl = null;
//		boolean flag = true;
		try {
			// int result = DialogBoxUtil.confirmDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_IS_DELETE));
			// if (result != 0) {
			// //因为没有deleteChecking方法，所有做个判断
			// return;
			// }
			infos = this.view.getAllSelect();
			// 删除tunnel之前先验证该tunnel是否有按需oam，没有才可删除，否则提示不能删除
//			for (Tunnel tunnel : infos) {
//				// 如果为true，说明该条tunnel有按需oam，不能删除
//				if (checkIsOam(tunnel)) {
//					flag = false;
//					break;
//				}
//			}
//			if (!flag) {
//				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_CLEAN_OAM));
//				return;
//			}

			tunnelServiceImpl = new DispatchUtil(RmiKeys.RMI_TUNNEL);
			String message = tunnelServiceImpl.excuteDelete(infos);
			// 添加日志记录
			for(Tunnel tunnel : infos){
				AddOperateLog.insertOperLog(null, EOperationLogType.TUNNELDELETE.getValue(), message,
						null, null, -1, tunnel.getTunnelName(), null);
			}
			DialogBoxUtil.succeedDialog(this.view, message);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			infos = null;
			tunnelServiceImpl = null;
		}
	}

	/**
	 * 删除前的验证
	 * 
	 * @return true 验证成功， false 验证失败
	 * @throws Exception
	 */
	@Override
	public boolean deleteChecking() throws Exception {
		List<Tunnel> infos = null;
		boolean flag = false;
		boolean onlineFlag = false;
		List<Integer> allSiteIds =null;
		List<Integer> siteIds =null;
		try {
			infos = this.view.getAllSelect();
			//判断是否有在线网元托管，存在不允许删除
			SiteUtil siteUtil = new SiteUtil();
			allSiteIds = new ArrayList<Integer>();
			siteIds = new ArrayList<Integer>();			
			allSiteIds=getNotOnlineSiteIds(infos);
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
				return false;
			}
			
			
			
			// 删除tunnel之前先验证该tunnel是否有按需oam，没有才可删除，否则提示不能删除
			for (Tunnel tunnel : infos) {
				// 如果为true，说明该条tunnel有按需oam，不能删除
				if (checkIsOam(tunnel)) {
					flag = true;
					break;
				}
			}
			if (flag) {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_CLEAN_OAM));
				return false;
			}

			// 删除tunnel之前先验证该tunnel是否有bfd，没有才可删除，否则提示不能删除
			for (Tunnel tunnel : infos) {
				// 如果为true，说明该条tunnel有bfd，不能删除
				if (checkIsBfd(tunnel)) {
					flag = true;
					break;
				}
			}
			if (flag) {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_CLEAN_BFD));
				return false;
			}
			
			flag = true;
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		} finally {
			infos=null;
			allSiteIds=null;
			siteIds= null;
		}
		return flag;
	}
	
	private boolean checkIsBfd(Tunnel tunnel) {
		boolean flag=false;
		BfdInfoService_MB bfdService = null;
		int aSiteId=0;
		int lspId=0;
		List<Integer> lspIds=null;
		List<Integer> lspIds2=null;
		try {			
			bfdService = (BfdInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.BFDMANAGEMENT);
			lspIds=new ArrayList<Integer>();
			lspIds2=new ArrayList<Integer>();
			//a端
			aSiteId=tunnel.getaSiteId();
			lspIds=bfdService.queryLspIds(aSiteId, 1);
			lspIds2=bfdService.queryLspIds(aSiteId,2);
			for(int i=0;i<tunnel.getLspParticularList().size();i++){
				if(aSiteId==tunnel.getLspParticularList().get(i).getASiteId()){
					lspId=tunnel.getLspParticularList().get(i).getAtunnelbusinessid();
					if(lspIds.contains(lspId)||lspIds2.contains(lspId)){
						flag=true;
					    return flag;
					}					   
				}
				if(aSiteId==tunnel.getLspParticularList().get(i).getZSiteId()){
					lspId=tunnel.getLspParticularList().get(i).getZtunnelbusinessid();
					if(lspIds.contains(lspId)||lspIds2.contains(lspId)){
						flag=true;
					    return flag;
					}
				}
			}			
		//z端	
			aSiteId=tunnel.getzSiteId();
			lspIds=bfdService.queryLspIds(aSiteId, 1);
			lspIds2=bfdService.queryLspIds(aSiteId,2);
			for(int i=0;i<tunnel.getLspParticularList().size();i++){
				if(aSiteId==tunnel.getLspParticularList().get(i).getASiteId()){
					lspId=tunnel.getLspParticularList().get(i).getAtunnelbusinessid();
					if(lspIds.contains(lspId)||lspIds2.contains(lspId)){
						flag=true;
					    return flag;
					}					   
				}
				if(aSiteId==tunnel.getLspParticularList().get(i).getZSiteId()){
					lspId=tunnel.getLspParticularList().get(i).getZtunnelbusinessid();
					if(lspIds.contains(lspId)||lspIds2.contains(lspId)){
						flag=true;
					    return flag;
					}
				}
			}
			//保护
			if(tunnel.getProtectTunnel()!=null){
				//a端
				aSiteId=tunnel.getProtectTunnel().getaSiteId();
				lspIds=bfdService.queryLspIds(aSiteId, 1);
				lspIds2=bfdService.queryLspIds(aSiteId,2);
				for(int i=0;i<tunnel.getProtectTunnel().getLspParticularList().size();i++){
					if(aSiteId==tunnel.getProtectTunnel().getLspParticularList().get(i).getASiteId()){
						lspId=tunnel.getProtectTunnel().getLspParticularList().get(i).getAtunnelbusinessid();
						if(lspIds.contains(lspId)||lspIds2.contains(lspId)){
							flag=true;
						    return flag;
						}					   
					}
					if(aSiteId==tunnel.getProtectTunnel().getLspParticularList().get(i).getZSiteId()){
						lspId=tunnel.getProtectTunnel().getLspParticularList().get(i).getZtunnelbusinessid();
						if(lspIds.contains(lspId)||lspIds2.contains(lspId)){
							flag=true;
						    return flag;
						}
					}
				}			
			//z端	
				aSiteId=tunnel.getProtectTunnel().getzSiteId();
				lspIds=bfdService.queryLspIds(aSiteId, 1);
				lspIds2=bfdService.queryLspIds(aSiteId,2);
				for(int i=0;i<tunnel.getProtectTunnel().getLspParticularList().size();i++){
					if(aSiteId==tunnel.getProtectTunnel().getLspParticularList().get(i).getASiteId()){
						lspId=tunnel.getProtectTunnel().getLspParticularList().get(i).getAtunnelbusinessid();
						if(lspIds.contains(lspId)||lspIds2.contains(lspId)){
							flag=true;
						    return flag;
						}					   
					}
					if(aSiteId==tunnel.getProtectTunnel().getLspParticularList().get(i).getZSiteId()){
						lspId=tunnel.getProtectTunnel().getLspParticularList().get(i).getZtunnelbusinessid();
						if(lspIds.contains(lspId)||lspIds2.contains(lspId)){
							flag=true;
						    return flag;
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		finally
		{
			lspIds=null;
			lspIds2=null;
			UiUtil.closeService_MB(bfdService);
		}
		return flag;
	}
	
	
	private boolean checkIsOam(Tunnel tunnel) {
		OamMepInfo mep = null;
		boolean flag = false;
		OamInfoService_MB oamInfoServiceMB = null;
		try {
			mep = new OamMepInfo();
			mep.setObjId(tunnel.getTunnelId());
			mep.setObjType("TUNNEL_TEST");
			oamInfoServiceMB = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			flag = oamInfoServiceMB.existByObjIdAndType(mep);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(oamInfoServiceMB);
		}
		return flag;
	}

	@Override
	public void openCreateDialog() throws Exception {
		AddTunnelPathDialog addTunnelPathDialog = null;
		try {
			addTunnelPathDialog = new AddTunnelPathDialog(this.view, true, null);
			UiUtil.showWindow(addTunnelPathDialog, UiUtil.getWindowWidth(addTunnelPathDialog.getWidth()), UiUtil.getWindowHeight(addTunnelPathDialog.getHeight()));
			// addTunnelPathDialog.setLocation(UiUtil.getWindowWidth(addTunnelPathDialog.getWidth()), UiUtil.getWindowHeight(addTunnelPathDialog.getHeight()));
			// addTunnelPathDialog.setVisible(true);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			addTunnelPathDialog = null;
		}
	}

	/**
	 * 选中一条记录后，查看详细信息
	 */
	@Override
	public void initDetailInfo() {
		TunnelService_MB tunnelServiceMB = null;
		try {
			this.initLspData();
			tunnelServiceMB = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			List<Tunnel> tunnels = new ArrayList<Tunnel>();
			tunnels.add(view.getSelect());
			tunnelServiceMB.setOtherInfomationforTunnel(tunnels);
			this.initQosInfos();
			this.initOamInfos();
			this.initTopoPanel();
			this.initSchematizePanel();
			this.initOamMainInfoPanel();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(tunnelServiceMB);
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
		Tunnel tunnel = null;
		try {
			tunnel = view.getSelect();
			this.view.getSchematize_panel().clear();
			this.view.getSchematize_panel().initData(tunnel);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	private void initTopoPanel() {
		Tunnel tunnel = null;
		try {
			tunnel = view.getSelect();
			view.getTopoPanel().clear();
			view.getTopoPanel().initData(tunnel);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	@SuppressWarnings("unchecked")
	private void initOamInfos() throws Exception {
		OamInfoService_MB oamInfoServiceMB = null;
		Tunnel tunnel = null;
		List<OamInfo> oamList = null;
		try {
			tunnel = this.view.getSelect();
			oamList = new ArrayList<OamInfo>();
			oamList = tunnel.getOamList();
			ComparableSort sort = new ComparableSort();
//			oamList = (List<OamInfo>) sort.compare(oamList);
			this.view.getOamTable().clear();
			this.view.getOamTable().initData(oamList);
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(oamInfoServiceMB);
			tunnel = null;
			oamList = null;
		}
	}

	// 增加保护的LSP显示
	private void initLspData() throws Exception {
		Tunnel tunnel = null;
		List<Lsp> lsppro = new ArrayList<Lsp>();
		List<Lsp> lsp = new ArrayList<Lsp>();
		List<Lsp> lists = new ArrayList<Lsp>();
		Tunnel protunnel = new Tunnel();
		TunnelService_MB tunnelServiceMB = null;
		try {
			tunnel = this.view.getSelect();
			tunnelServiceMB = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			List<Tunnel> tunnels = new ArrayList<Tunnel>();
			tunnels.add(tunnel);
//			tunnelServiceMB.setOnlyTunnelLsp(tunnels, false);
			protunnel = tunnel.getProtectTunnel();
			for (Lsp lsp1 : tunnel.getLspParticularList()) {
				lsp1.setPosition(tunnel.getPosition());
			}
			this.convertLspData(tunnel, "work");
			if (protunnel != null) {
				for (Lsp lsp2 : protunnel.getLspParticularList()) {
					lsp2.setPosition(tunnel.getPosition());
				}
				this.convertLspData(protunnel, "protect");
			}
			this.view.getLspNetworkTablePanel();
			lsp = tunnel.getLspParticularList();
			for (Lsp lspwork : lsp) {
				lspwork.setPosition(tunnel.getPosition());
				lists.add(lspwork);
			}

			if (protunnel != null) {
				lsppro = protunnel.getLspParticularList();
			}
			if (lsppro.size() > 0) {
				for (Lsp lspprotect : lsppro) {
					lspprotect.setPosition(protunnel.getPosition());
					lists.add(lspprotect);
				}
			}
			this.view.getLspNetworkTablePanel().initData(lists);
			this.view.updateUI();

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(tunnelServiceMB);
			tunnel = null;
			lsppro = null;
			lsp = null;
		}
	}

	/**
	 * 转换lsp对象 给lsp所需要的列赋值
	 * 
	 * @param tunnel
	 * @throws Exception
	 */
	private void convertLspData(Tunnel tunnel, String type) throws Exception {
		String asiteName = null;
		String zsiteName = null;
		String aportName = null;
		String zportName = null;
		SiteService_MB siteServiceMB = null;
		PortService_MB portServiceMB = null;
		try {
			siteServiceMB = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			portServiceMB = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			for (Lsp lsp : tunnel.getLspParticularList()) {
				asiteName = siteServiceMB.getSiteName(lsp.getASiteId());
				zsiteName = siteServiceMB.getSiteName(lsp.getZSiteId());
				aportName = portServiceMB.getPortname(lsp.getAPortId());
				zportName = portServiceMB.getPortname(lsp.getZPortId());

				lsp.putClientProperty("id", lsp.getId());
				lsp.putClientProperty("lsptype", type);
				lsp.putClientProperty("lspname", asiteName + "/" + aportName + "-" + zsiteName + "/" + zportName);
				lsp.putClientProperty("asiteName", asiteName);
				lsp.putClientProperty("zsiteName", zsiteName);
				lsp.putClientProperty("aportName", aportName);
				lsp.putClientProperty("zportName", zportName);
				lsp.putClientProperty("inlabel", lsp.getFrontLabelValue());
				lsp.putClientProperty("outlabel", lsp.getBackLabelValue());
				lsp.putClientProperty("position", tunnel.getPosition() == 1 ? true : false);

			}
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(siteServiceMB);
			UiUtil.closeService_MB(portServiceMB);
		}
	}

	@SuppressWarnings("unchecked")
	private void initQosInfos() throws Exception {
		QosInfoService_MB qosInfoServiceMB = null;
		List<QosInfo> qosList = null;
		Tunnel tunnel = null;
		try {
			tunnel = this.view.getSelect();
			qosList = tunnel.getQosList();
			ComparableSort sort = new ComparableSort();
			qosList = (List<QosInfo>) sort.compare(qosList);
			this.view.getQosPanel().clear();
			this.view.getQosPanel().initData(qosList);
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(qosInfoServiceMB);
		}
	}
	
	/**
	 * 初始化oam关键信息
	 */
	private void initOamMainInfoPanel() {
		Tunnel tunnel = this.view.getSelect();
		List<Tunnel> tunnelList = new ArrayList<Tunnel>();
		tunnelList.add(tunnel);
		this.view.getOamMainInfoPanel().clear();
		this.view.getOamMainInfoPanel().initData(tunnelList);
		this.view.updateUI();
	}

	@Override
	public void openUpdateDialog() throws Exception {
		try {
			Tunnel info = this.view.getSelect();
			if (info == null) {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			} else {
				AddTunnelPathDialog addTunnelPathDialog = new AddTunnelPathDialog(this.view, true, info);
				UiUtil.showWindow(addTunnelPathDialog, UiUtil.getWindowWidth(addTunnelPathDialog.getWidth()), UiUtil.getWindowHeight(addTunnelPathDialog.getHeight()));
				// addTunnelPathDialog.setLocation();
				// addTunnelPathDialog.setVisible(true);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * 执行搜索按钮动作
	 * **/
	@Override
	public void search() {
		try {
			new SearchSegmentDialog(this.view);

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * 清楚过滤 按原有查询显示
	 * 
	 * @author xxx
	 */
	@Override
	public void clearFilter() throws Exception {
		this.filterCondition = null;
		this.refresh();
	}

	/**
	 * 打开过滤对话框，进行过滤条件设置
	 * 
	 * @author xxx
	 */
	@Override
	public void openFilterDialog() throws Exception {
		new TunnelFilterDialog(this.filterCondition);
		this.refresh();
	}

	public void setFilterCondition(Tunnel filterCondition) {
		this.filterCondition = filterCondition;
	}

	public Tunnel getFilterCondition() {
		return filterCondition;
	}
	
	/**取出该tunnel上所有网元的siteid
	 * @param tunnelList
	 * @return
	 * @throws Exception
	 */
	public List<Integer> getNotOnlineSiteIds(List<Tunnel> tunnelList) throws Exception {
		List<Integer> siteIds = null;
		DispatchBase dispatchbase = new DispatchBase();
		TunnelService_MB tunnelServiceMB = null;
		Tunnel protectTunnel = null;
		try {
			siteIds = new ArrayList<Integer>();
			tunnelServiceMB = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			for (Tunnel tunnel : tunnelList) {
				if (tunnel.getProtectTunnelId() > 0) {
					protectTunnel = new Tunnel();
					protectTunnel.setTunnelId(tunnel.getProtectTunnelId());
					protectTunnel = tunnelServiceMB.select_nojoin(protectTunnel).get(0);
					//Tunnel protectTunnel =tunnelServiceMB.selectByID(tunnel.getProtectTunnelId());
					for (Lsp lspParticular : protectTunnel.getLspParticularList()) {
						if (lspParticular.getASiteId() > 0) {
							if (!siteIds.contains(lspParticular.getASiteId()) && dispatchbase.getManufacturer(lspParticular.getASiteId()) == EManufacturer.WUHAN.getValue()) {
								siteIds.add(lspParticular.getASiteId());
							}
						}
						if (lspParticular.getZSiteId() > 0) {
							if (!siteIds.contains(lspParticular.getZSiteId()) && lspParticular.getZPortId() > 0 && dispatchbase.getManufacturer(lspParticular.getZSiteId()) == EManufacturer.WUHAN.getValue()) {
								siteIds.add(lspParticular.getZSiteId());
							}
						}
					}

				}

				for (Lsp lspParticular : tunnel.getLspParticularList()) {
					if (lspParticular.getASiteId() > 0) {
						if (!siteIds.contains(lspParticular.getASiteId()) && dispatchbase.getManufacturer(lspParticular.getASiteId()) == EManufacturer.WUHAN.getValue()) {
							siteIds.add(lspParticular.getASiteId());
						}
					}
					if (lspParticular.getZSiteId() > 0) {
						if (!siteIds.contains(lspParticular.getZSiteId()) && lspParticular.getZPortId() > 0 && dispatchbase.getManufacturer(lspParticular.getZSiteId()) == EManufacturer.WUHAN.getValue()) {
							siteIds.add(lspParticular.getZSiteId());
						}
					}
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			tunnelServiceMB.close();
		}		
		return siteIds;
	}		

	private void flipRefresh(){
		view.getCurrPageLabel().setText(now+"");
		TunnelService_MB tunnelServiceMB = null;
    	try {
    		tunnelServiceMB = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
    		List<Tunnel> needTunnels = null;
    		if(now*ConstantUtil.flipNumber>infos.size()){
    			needTunnels = infos.subList((now-1)*ConstantUtil.flipNumber, infos.size());
    		}else{
    			needTunnels = infos.subList((now-1)*ConstantUtil.flipNumber, now*ConstantUtil.flipNumber);
    		}
    		
    		tunnelServiceMB.setOnlyTunnelLsp(needTunnels, false);
    		this.view.clear();
    		this.view.initData(needTunnels);
    		if (view.getOamTable() != null) {
    			view.getOamTable().clear();
    		}
    		if (view.getTopoPanel() != null) {
    			view.getTopoPanel().clear();
    		}
    		if (view.getSchematize_panel() != null) {
    			view.getSchematize_panel().clear();
    		}
    		this.view.getQosPanel().clear();
    		this.view.getLspNetworkTablePanel().clear();
    		this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(tunnelServiceMB);
		}
	}
	
	@Override
    public void prevPage()throws Exception{
    	now = now-1;
    	if(now == 1){
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
		now = now+1;
		if(now == total){
			view.getNextPageBtn().setEnabled(false);
		}
		view.getPrevPageBtn().setEnabled(true);
		flipRefresh();
	}
}
