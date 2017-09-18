package com.nms.ui.ptn.performance.controller;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.perform.CurrentPerforInfo;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EObjectType;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.path.pw.MsPwInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.LspInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.alarm.service.CSVUtil;
import com.nms.ui.ptn.performance.model.CurrentPerformanceFilter;
import com.nms.ui.ptn.performance.util.PerformanceOfflineUitl;
import com.nms.ui.ptn.performance.view.CurrentPerforFilterDialog;
import com.nms.ui.ptn.performance.view.CurrentPerformancePanel;

/**
 * 当前性能控制?
 * 
 * @author lp
 * 
 */
public class CurrentPerformanceController extends AbstractController {

	private CurrentPerformancePanel view;
	private CurrentPerformanceFilter filter;
	private List<CurrentPerforInfo> cureenPerformanceFilterList = null;

	public CurrentPerformanceController(CurrentPerformancePanel view) {
		this.view = view;
	}

	// 清空过滤条件
	@Override
	public void clearFilter() {
		filter = null;
		this.view.clear();
	}

	// 打开设置过滤对话?
	@Override
	public void openFilterDialog() {
		final CurrentPerforFilterDialog filterDialog = new CurrentPerforFilterDialog();
		filterDialog.getConfirm().addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				try {
					if (filterDialog.validateParams()) {
						CurrentPerformanceController.this.setFilter(filterDialog);
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e, CurrentPerformanceController.class);
				}
			}
			@Override
			public boolean checking() {
				return true;
			}
		});
		UiUtil.showWindow (filterDialog, 530, 560);
	}

	private void setFilter(CurrentPerforFilterDialog dialog) {
		PerformanceOfflineUitl performanceOfflineUitl = new PerformanceOfflineUitl();
		try {
			filter = dialog.get();
			cureenPerformanceFilterList = new ArrayList<CurrentPerforInfo>();
			if (filter != null) {
				 queryPerforByFilter();
			} else {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_FILTER));
				return;
			}
			// 添加日志记录
			AddOperateLog.insertOperLog(dialog.getConfirm(), EOperationLogType.CURRENTPERFORMANCESELECT.getValue(),null);

			this.view.clear();
			this.view.initData(cureenPerformanceFilterList);
			this.view.updateUI();
			DialogBoxUtil.succeedDialog(dialog, performanceOfflineUitl.getPerformanceResult(filter));
			dialog.dispose();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
			dialog.dispose();
		} finally{
			performanceOfflineUitl = null;
		}
	}

	// 从后台查询性能?
	@Override
	public void refresh() {
		PerformanceOfflineUitl performanceOfflineUitl = new PerformanceOfflineUitl();
		try {
			if (cureenPerformanceFilterList != null && cureenPerformanceFilterList.size() > 0) {
				cureenPerformanceFilterList.clear();
			}
			if (filter == null) {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_FILTER));
				return;
			} else {
			    queryPerforByFilter();
			}
			this.view.clear();
//			this.view.initData(removeRepeatedType(cureenPerformanceFilterList));
			this.view.initData(cureenPerformanceFilterList);
			DialogBoxUtil.succeedDialog(null, performanceOfflineUitl.getPerformanceResult(filter));
			this.view.updateUI();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			performanceOfflineUitl = null;
		}
	}

	/**
	 * function:通过一些过滤条件来获取相应的数据
	 * @param infos
	 */
	private boolean setFilterCurrentPerformance(CurrentPerforInfo currentPerformanceInfo) {
		boolean flag = false;
		try {
			String[] filrertypes = filter.getTypeStr().trim().split(",");
			if (currentPerformanceInfo.getCapability() != null) {
				for (int i = 0; i < filrertypes.length; i++) {
					try {
						if (filrertypes[i].equals(currentPerformanceInfo.getCapability().getCapabilitytype())) {
							for (String strType : filter.getCapabilityNameList()) {
								if (filter.getFilterZero() > 0) {
									if (currentPerformanceInfo.getCapability().getCapabilitydesc().equalsIgnoreCase(strType)
											&& currentPerformanceInfo.getPerformanceValue() != 0
											&& currentPerformanceInfo.getObjectName() != null) {
										return true;
									}
								} else {
									if (currentPerformanceInfo.getCapability().getCapabilitydesc().equalsIgnoreCase(strType)
											&& currentPerformanceInfo.getObjectName() != null
									) {
										return true;
									}
								}
							}
						}
					} catch (Exception e) {
						ExceptionManage.dispose(e, getClass());
					}
				}
			}else{
				ExceptionManage.infor("Capability()== null", getClass());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return flag;
	}

	// 界面零值过滤
	@Override
	public void filterZero() {
		List<CurrentPerforInfo> cureenPerformanceInfos = null;
		try {
			cureenPerformanceInfos = new ArrayList<CurrentPerforInfo>();
			if (filter == null) {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_FILTER));
				return;
			} else {
				for (CurrentPerforInfo hisPerformanceInfo : cureenPerformanceFilterList) {
					// 将数据为零的数据去掉
					if (hisPerformanceInfo.getPerformanceValue() != 0) {
						cureenPerformanceInfos.add(hisPerformanceInfo);
					}
				}
				cureenPerformanceFilterList.clear();
				cureenPerformanceFilterList.addAll(cureenPerformanceInfos);
			}
			this.view.clear();
			this.view.initData(cureenPerformanceFilterList);
			this.view.updateUI();

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			cureenPerformanceInfos = null;
		}
	};

	// 导出
	@Override
	public void export() throws Exception {
		CSVUtil csvUtil = null;
		String path = null;
		String[] s = {};
		UiUtil uiUtil = new UiUtil();
		int reult = 0;
		try {
			if (filter == null) {
				DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_FILTER));
				return;
			} else {
				csvUtil = new CSVUtil();
				if(ResourceUtil.language.equals("zh_CN")){
					path = csvUtil.showChooserWindow("save", "选择文件", s);
				}else{
					path = csvUtil.showChooserWindow("save", "Select File", s);
				}
				if(path != null && !"".equals(path)){
					String csvFilePath = path + ".csv";
					if(uiUtil.isExistAlikeFileName(csvFilePath)){
						reult = DialogBoxUtil.confirmDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_FILEPATHHASEXIT));
						if(reult == 1){
							return ;
						}
					}
					csvUtil.WriteCsvCureenPerformace(csvFilePath, cureenPerformanceFilterList);
				}
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			uiUtil = null;
			csvUtil = null;
			path = null;
		}
	};

	private void queryPerforByFilter() throws Exception {
		DispatchUtil dispatch = null;
		PortService_MB portInfoService = null;
		SegmentService_MB segmentService = null;
		LspInfoService_MB lspInfoService = null;
		TunnelService_MB tunnelService = null;
		PwInfoService_MB pwInfoService = null;
		ElineInfoService_MB elineInfoService = null;
		EtreeInfoService_MB etreeInfoService = null;
		ElanInfoService_MB elanInfoService = null;
		CesInfoService_MB cesInfoService = null;
		// key为网元数据库id，value为槽位的集合
		List<CurrentPerforInfo> queryPerforList = null;
		try {
			dispatch = new DispatchUtil(RmiKeys.RMI_PERFORMANCE);
			queryPerforList = dispatch.executeQueryCurrPerforByFilter(filter);
			if (filter.getObjectType() != null && filter.getObjectType() == EObjectType.SLOTINST) {
				// 设备只支持网元的性能查询，所以查询后的性能数据根据槽位过滤
				if (queryPerforList != null && queryPerforList.size() > 0) {
					for (int i = 0; i < filter.getSlotInsts().size(); i++) {
						int slotInstId = filter.getSlotInsts().get(i);
						for (CurrentPerforInfo info : queryPerforList) {
							if (info.getMasterCardAddress() == slotInstId) {
								if(setFilterCurrentPerformance(info)){
									cureenPerformanceFilterList.add(info);		
								}
							}
						}
					}
				}
			} else if (filter.getObjectType() != null && filter.getObjectType() == EObjectType.PORT) {
				// 设备只支持网元的性能查询，所以查询后的性能数据根据端口业务id过滤
				if (queryPerforList != null && queryPerforList.size() > 0) {
					portInfoService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
					segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
					tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
					lspInfoService = (LspInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LSPINFO);
					pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
					elineInfoService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
					etreeInfoService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
					elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
					cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
					for (int i = 0; i < filter.getPortInsts().size(); i++) {
						int portInstId = filter.getPortInsts().get(i);					
						PortInst portInst = portInfoService.selectPortybyid(portInstId);
						List <Segment> segmentList = segmentService.selectBySegmentPortId(portInstId);
						List <Tunnel> tunnelList = null;
						List <Lsp> lspList = null;
						List <PwInfo> pwList = null;
						List <ElineInfo> elineInfoList = null;
						List <EtreeInfo> etreeInfoList = null;
						List <ElanInfo> elanInfoList = null;
						List <CesInfo> cesInfoList = null;
						List <Integer> tunnelIds = new ArrayList<Integer>();
						List <Integer> pwIds = new ArrayList<Integer>();
						if(null != segmentList && segmentList.size() > 0){
							tunnelList = tunnelService.selectByPortIdAndSiteId(portInst.getSiteId(), portInstId);
							lspList = lspInfoService.selectByPort(portInstId);
							if(null != tunnelList && tunnelList.size() > 0){
								for(Tunnel tunnel : tunnelList){
									tunnelIds.add(tunnel.getTunnelId());
								}
								pwList = pwInfoService.selectPwInfoByTunnelId(tunnelIds);
								if(null != pwList && pwList.size() > 0){
									for(PwInfo pwInfo : pwList){
										pwIds.add(pwInfo.getPwId());
									}
									elineInfoList = elineInfoService.selectElineByPwId(pwIds);
									etreeInfoList = etreeInfoService.selectEtreeByPwId(pwIds);
									elanInfoList = elanInfoService.selectElanbypwid(pwIds);
									cesInfoList = cesInfoService.selectCesByPwId(pwIds);
								}
							}
						}										
						for (CurrentPerforInfo info : queryPerforList) {
							//port
							if(info.getObjectType() == EObjectType.PORT || info.getObjectType() == EObjectType.E1){
								if(info.getObjectId() == portInst.getNumber()){
									if(setFilterCurrentPerformance(info)){
										cureenPerformanceFilterList.add(info);
									}
								}
							}
							//段层
							if(info.getObjectType() == EObjectType.SEGMENT){
								if(null != segmentList && segmentList.size() > 0){
									for(Segment segment : segmentList){
										if(info.getObjectId() == segment.getId()){
											if(setFilterCurrentPerformance(info)){
												cureenPerformanceFilterList.add(info);
											}
										}
									}
								}
							}
							//TMP通道
							if(info.getObjectType() == EObjectType.TUNNEL){
								if(null != tunnelList && tunnelList.size() > 0){
									for(Tunnel tunnel : tunnelList){
										if(info.getObjectId() == tunnel.getAprotectId() ||info.getObjectId() == tunnel.getZprotectId()){
											if(setFilterCurrentPerformance(info)){
												cureenPerformanceFilterList.add(info);
											}
										}
									}
								}
								if(null != lspList && lspList.size() > 0){
									for(Lsp lsp : lspList){
										if(info.getObjectId() == lsp.getAlspbusinessid() ||info.getObjectId() == lsp.getZlspbusinessid()){
											if(setFilterCurrentPerformance(info)){
												cureenPerformanceFilterList.add(info);
											}
										}
									}
								}
							}
							//pw
							if(info.getObjectType() == EObjectType.PW){
								if(null != pwList && pwList.size() > 0){
									for(PwInfo pwInfo : pwList){
										if(info.getObjectId() == pwInfo.getApwServiceId() ||info.getObjectId() == pwInfo.getZpwServiceId()){
											if(setFilterCurrentPerformance(info)){
												cureenPerformanceFilterList.add(info);
											}
										}
									}
								}
							}
							//eline   ces
							if(info.getObjectType() == EObjectType.VPWS){
								if(null != elineInfoList && elineInfoList.size() > 0){
									for(ElineInfo elineInfo : elineInfoList){
										if(info.getObjectId() == elineInfo.getServiceId()){
											if(setFilterCurrentPerformance(info)){
												cureenPerformanceFilterList.add(info);
											}
										}
									}
								}
								if(null != cesInfoList && cesInfoList.size() > 0){
									for(CesInfo cesInfo : cesInfoList){
										if(info.getObjectId() == cesInfo.getServiceId()){
											if(setFilterCurrentPerformance(info)){
												cureenPerformanceFilterList.add(info);
											}
										}
									}
								}
							}
							//elan   etree
							if(info.getObjectType() == EObjectType.VPLS){
								if(null != etreeInfoList && etreeInfoList.size() > 0){
									for(EtreeInfo etreeInfo : etreeInfoList){
										if(info.getObjectId() == etreeInfo.getServiceId()){
											if(setFilterCurrentPerformance(info)){
												cureenPerformanceFilterList.add(info);
											}
										}
									}
								}
								if(null != elanInfoList && elanInfoList.size() > 0){
									for(ElanInfo elanInfo : elanInfoList){
										if(info.getObjectId() == elanInfo.getServiceId()){
											if(setFilterCurrentPerformance(info)){
												cureenPerformanceFilterList.add(info);
											}
										}
									}
								}
							}														
						}
					}
					
				}

			} else {
				if (queryPerforList != null && queryPerforList.size() > 0) {
					for (CurrentPerforInfo info : queryPerforList) {
						if(setFilterCurrentPerformance(info)){
							cureenPerformanceFilterList.add(info);
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
			throw e;
		} finally {
			dispatch = null;
			queryPerforList = null;
			UiUtil.closeService_MB(portInfoService);
			UiUtil.closeService_MB(segmentService);
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(lspInfoService);
			UiUtil.closeService_MB(pwInfoService);
			UiUtil.closeService_MB(elineInfoService);
			UiUtil.closeService_MB(etreeInfoService);
			UiUtil.closeService_MB(elanInfoService);
			UiUtil.closeService_MB(cesInfoService);
		}
	}

	public CurrentPerformancePanel getView() {
		return view;
	}
}
