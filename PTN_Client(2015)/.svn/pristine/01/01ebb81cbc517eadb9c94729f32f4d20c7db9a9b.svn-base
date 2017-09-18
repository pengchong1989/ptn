package com.nms.ui.ptn.portlag;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.CommonBean;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.ptn.port.PortLagService_MB;
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
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.portlag.view.AddLagDialog;
import com.nms.ui.ptn.portlag.view.PortLagDialog;
import com.nms.ui.ptn.portlag.view.PortLagPanel;

public class PortLagController extends AbstractController {

	private PortLagPanel panel;
	private AbstractLagDialog portLagDialog;
	private PortLagInfo portLagInfo = new PortLagInfo();
	private PortLagInfo lagInfo = new PortLagInfo();
	public PortLagController(PortLagPanel panel) {
		this.panel = panel;
	}

	@Override
	public void refresh() throws Exception {

		List<PortLagInfo> portLagInfoList = null;
		try {
			portLagInfoList = getAllPortLagInfo();
			this.panel.clear();
			this.panel.initData(portLagInfoList);
			this.panel.updateUI();

		} catch (Exception e) {
			throw e;
		} finally {
			portLagInfoList = null;
		}

	}

	private List<PortLagInfo> getAllPortLagInfo() {
		PortLagService_MB lagService = null;
		PortLagInfo portLagInfo = null;
		List<PortLagInfo> portLagInfoList = null;
		try {
			lagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			portLagInfo = new PortLagInfo();
			portLagInfo.setSiteId(ConstantUtil.siteId);
			portLagInfoList = lagService.selectByCondition(portLagInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(lagService);
		}
		return portLagInfoList;

	}

	// 创建
	@Override
	public void openCreateDialog() throws Exception {
		SiteService_MB siteService = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if (siteService.getManufacturer(ConstantUtil.siteId) == EManufacturer.WUHAN.getValue()) {
				portLagInfo = new PortLagInfo();
				String siteType = siteService.getSiteType(ConstantUtil.siteId);
				if(siteType.contains("710")){
					if (getAllPortLagInfo().size() == 8) {
						DialogBoxUtil.errorDialog(PortLagController.this.panel, ResourceUtil.srcStr(StringKeysTip.TIP_8_LAG));
						return;
					}
				}else{
					if (getAllPortLagInfo().size() == 4) {
						DialogBoxUtil.errorDialog(PortLagController.this.panel, ResourceUtil.srcStr(StringKeysTip.TIP_4_LAG));
						return;
					}
				}
				portLagDialog = new PortLagDialog(this.panel);
				if(ResourceUtil.language.equals("zh_CN")){
					portLagDialog.setSize(440, 450);
				}else{
					portLagDialog.setSize(730, 450);
				}
				click(portLagDialog);
			} else {
				portLagDialog = new AddLagDialog(this.panel);
				portLagDialog.setSize(750, 450);
			}
			portLagDialog.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_PORT_AGGREGATION));
			portLagDialog.initData(null);
			portLagDialog.setLocation(UiUtil.getWindowWidth(portLagDialog.getWidth()), UiUtil.getWindowHeight(portLagDialog.getHeight()));
			portLagDialog.setVisible(true);
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}

	}

	@Override
	public void delete() throws Exception {
		List<PortLagInfo> infos = null;
		String error = "";
		try {
			infos = this.panel.getAllSelect();

					DispatchUtil portLagDispatch = new DispatchUtil(RmiKeys.RMI_PORTLAG);
					error = portLagDispatch.excuteDelete(infos);
					//添加日志记录
					PtnButton deleteButton=(PtnButton) this.panel.getDeleteButton();
					deleteButton.setOperateKey(EOperationLogType.PORTLAGDELETE.getValue());
					int operationResult=0;
					if(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(error)){
						operationResult=1;
					}else{
						operationResult=2;
					}
					deleteButton.setResult(operationResult);
					DialogBoxUtil.succeedDialog(this.panel, error);
					this.refresh();
					this.panel.getLagPortTablePanel().clear();
					this.panel.getTabbedPane().setSelectedIndex(0);
					this.panel.getTabbedPane().setEnabledAt(1, false);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			infos = null;
		}
	}

	@Override
	public boolean deleteChecking() {
		List<PortLagInfo> infos = null;
		boolean flag = false;
		List<Integer> siteIds = null;
		try {
			infos = this.panel.getAllSelect();
			//判断是否为在线脱管网元
			SiteUtil siteUtil = new SiteUtil();
			if(1==siteUtil.SiteTypeOnlineUtil(ConstantUtil.siteId)){
				WhImplUtil wu = new WhImplUtil();
				siteIds = new ArrayList<Integer>();
				siteIds.add(ConstantUtil.siteId);
				String str=wu.getNeNames(siteIds);
				DialogBoxUtil.errorDialog(PortLagController.this.panel, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_DELETEONLINE)+""+str+ResourceUtil.srcStr(StringKeysTip.TIP_ONLINENOT_DELETEONLINE));
				return false;  		    		
				}
			if (!isUsed(infos)) {
				DialogBoxUtil.errorDialog(PortLagController.this.panel, ResourceUtil.srcStr(StringKeysTip.TIP_LAG_USE));
				return false;
			}
			flag = true;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			infos = null;
			siteIds = null;
		}
		return flag;
	}

	/**
	 * 批量删除LAG时，判断LAG是否被引用
	 * 
	 * @param infos 如果true 表示被使用  false:未被使用
	 * @return
	 */
	public boolean isUsed(List<PortLagInfo> infos) {
		boolean flag = false;
		AcPortInfoService_MB acInfoService = null;
		try {
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			for (PortLagInfo portLagInfo : infos) {
				AcPortInfo acPortInfo = new AcPortInfo();
				acPortInfo.setSiteId(portLagInfo.getSiteId());
				acPortInfo.setLagId(portLagInfo.getId());
				List<AcPortInfo> acPortInfos = acInfoService.selectByCondition(acPortInfo);
				if (acPortInfos != null && acPortInfos.size()>0) {
					return false;
				}
			}
			flag = true;
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(acInfoService);
		}
		return flag;
	}

	// 修改
	@Override
	public void openUpdateDialog() throws Exception {
		SiteService_MB siteService = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if (siteService.getManufacturer(ConstantUtil.siteId) == EManufacturer.WUHAN.getValue()) {
				List<PortLagInfo> infos = null;
				try {
					infos = this.panel.getAllSelect();
					if (infos == null || infos.size() == 0) {
						DialogBoxUtil.errorDialog(this.panel, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE));
					} else {
//					if (infos.get(0).getIsUsed() == 0 ? false : true) {
//						DialogBoxUtil.errorDialog(PortLagController.this.panel, ResourceUtil.srcStr(StringKeysTip.TIP_LAG_USE));
//						return;
//					}
						portLagInfo = infos.get(0);
						lagInfo = infos.get(0);
						portLagDialog = new PortLagDialog(this.panel, infos.get(0));
						
						if(ResourceUtil.language.equals("zh_CN")){
							portLagDialog.setSize(440, 450);
						}else{
							portLagDialog.setSize(730, 450);
						}
						
						
						click(portLagDialog);
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			} else {
				portLagDialog = new AddLagDialog("update", this.panel);
				portLagDialog.setSize(750, 450);
			}
			portLagDialog.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_PORT_AGGREGATION));
			portLagDialog.initData(this.panel.getSelect());
//			portLagDialog.setLocation(UiUtil.getWindowWidth(portLagDialog.getWidth()), UiUtil.getWindowHeight(portLagDialog.getHeight()));
			UiUtil.showWindow(portLagDialog, portLagDialog.getWidth(), portLagDialog.getHeight());
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
	};

	// 点击事件
	public void click(AbstractLagDialog portLagDialog2) {
		// 关联设置
		((PortLagDialog) portLagDialog2).getRelevanceInstall().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(ResourceUtil.language.equals("zh_CN")){
					((PortLagDialog) portLagDialog).getRelevanceInstallDialog().setSize(580, 320);
				}else{
					((PortLagDialog) portLagDialog).getRelevanceInstallDialog().setSize(900, 320);
				}
				((PortLagDialog) portLagDialog).getRelevanceInstallDialog().setLocation(UiUtil.getWindowWidth(((PortLagDialog) portLagDialog).getRelevanceInstallDialog().getWidth()), UiUtil.getWindowHeight(((PortLagDialog) portLagDialog).getRelevanceInstallDialog().getHeight()));
				((PortLagDialog) portLagDialog).getRelevanceInstallDialog().getConfirm().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							((PortLagDialog) portLagDialog).getRelevanceInstallDialog().get(lagInfo);
							((PortLagDialog) portLagDialog).getRelevanceInstallDialog().setVisible(false);
						} catch (Exception e) {
							ExceptionManage.dispose(e,this.getClass());
						}
					}
				});
				((PortLagDialog) portLagDialog).getRelevanceInstallDialog().getCancel().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						((PortLagDialog) portLagDialog).getRelevanceInstallDialog().setVisible(false);
					}
				});
				((PortLagDialog) portLagDialog).getRelevanceInstallDialog().setVisible(true);
			}
		});

		// 端口成员
		((PortLagDialog) portLagDialog2).getPortMember().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				((PortLagDialog) portLagDialog).getPortMemberDialog().setVisible(true);
			}
		});

		((PortLagDialog) portLagDialog).getPortMemberDialog().getConfirm().addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							if (!((PortLagDialog) portLagDialog).getPortMemberDialog().checkPortMember()) {
								((PortLagDialog) portLagDialog).setHasPortnumber(false);
								DialogBoxUtil.errorDialog(((PortLagDialog) portLagDialog).getPortMemberDialog(), ResourceUtil.srcStr(StringKeysTip.TIP_MUST_PORT_MEMBERS));
							} else {
								if(!((PortLagDialog) portLagDialog).getPortMemberDialog().checkPortSameSpeed()){
									DialogBoxUtil.errorDialog(((PortLagDialog) portLagDialog).getPortMemberDialog(), ResourceUtil.srcStr(StringKeysTip.TIP_SAME_SPEED_PROT));
								}else{
									((PortLagDialog) portLagDialog).setHasPortnumber(true);
									lagInfo.setPortLagMember(((PortLagDialog) portLagDialog).getPortMemberDialog().get());
									String portNameArr = ((PortLagDialog) portLagDialog).getPortMemberDialog().getPortName();
									lagInfo.setPortLagMember1Log(portNameArr.split(",")[0]);
									lagInfo.setPortLagMember2Log(portNameArr.split(",")[1]);
									lagInfo.setPortLagMember3Log(portNameArr.split(",")[2]);
									lagInfo.setPortLagMember4Log(portNameArr.split(",")[3]);
									((PortLagDialog) portLagDialog).getPortMemberDialog().setVisible(false);
								}
							}
						} catch (Exception e) {
							ExceptionManage.dispose(e,this.getClass());
						}
					}
		});
		((PortLagDialog) portLagDialog).getPortMemberDialog().getCancel().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((PortLagDialog) portLagDialog).getPortMemberDialog().setVisible(false);
			}
		});
		
		// 出口队列调度策略
		((PortLagDialog) portLagDialog2).getExportQueue().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				((PortLagDialog) portLagDialog).getExportQueueDialog().setSize(600, 400);
				((PortLagDialog) portLagDialog).getExportQueueDialog().setLocation(UiUtil.getWindowWidth(((PortLagDialog) portLagDialog).getExportQueueDialog().getWidth()), UiUtil.getWindowHeight(((PortLagDialog) portLagDialog).getExportQueueDialog().getHeight()));
				((PortLagDialog) portLagDialog).getExportQueueDialog().init(portLagInfo.getExportQueue());


				((PortLagDialog) portLagDialog).getExportQueueDialog().getConfirm().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							lagInfo.setExportQueue(((PortLagDialog) portLagDialog).getExportQueueDialog().get());
							((PortLagDialog) portLagDialog).getExportQueueDialog().setVisible(false);
						} catch (Exception e) {
							ExceptionManage.dispose(e,this.getClass());
						}
					}
				});

				
				((PortLagDialog) portLagDialog).getExportQueueDialog().getCancel().addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						((PortLagDialog) portLagDialog).getExportQueueDialog().setVisible(false);
					}
				});
				((PortLagDialog) portLagDialog).getExportQueueDialog().setVisible(true);
			}
		});
		((PortLagDialog) portLagDialog).getOkButton().addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(!((PortLagDialog) portLagDialog).isHasPortnumber()){
						DialogBoxUtil.errorDialog(PortLagController.this.panel, ResourceUtil.srcStr(StringKeysTip.TIP_MUST_PORT_MEMBERS));
						return;
					}
					portLagInfo = ((PortLagDialog) portLagDialog).get(portLagInfo);// 收集所有数据
					portLagInfo.setPortLagMember(lagInfo.getPortLagMember());
					portLagInfo.setExportQueue(lagInfo.getExportQueue());
					portLagInfo.setVlanRelating(lagInfo.getVlanRelating());
					portLagInfo.setRelatingSet(lagInfo.getRelatingSet());
					portLagInfo.setFountainMAC(lagInfo.getFountainMAC());
					portLagInfo.setAimMAC(lagInfo.getAimMAC());
					portLagInfo.setFountainIP(lagInfo.getFountainIP());
					portLagInfo.setAimIP(lagInfo.getAimIP());
					portLagInfo.setPwSet(lagInfo.getPwSet());
					portLagInfo.setIpDSCPSet(lagInfo.getIpDSCPSet());
					portLagInfo.setPortLagMember1Log(lagInfo.getPortLagMember1Log().contains("null")?"":lagInfo.getPortLagMember1Log());
					portLagInfo.setPortLagMember2Log(lagInfo.getPortLagMember2Log().contains("null")?"":lagInfo.getPortLagMember2Log());
					portLagInfo.setPortLagMember3Log(lagInfo.getPortLagMember3Log().contains("null")?"":lagInfo.getPortLagMember3Log());
					portLagInfo.setPortLagMember4Log(lagInfo.getPortLagMember4Log().contains("null")?"":lagInfo.getPortLagMember4Log());
					
					DispatchUtil portLagDispatch = new DispatchUtil(RmiKeys.RMI_PORTLAG);
					String result = "";
					int operationLogResult = 0;
					PortLagInfo lagInfoBefore = null;
					if (portLagInfo.getId() > 0) {
						//查询修改前的数据便于日志记录
						PortService_MB portService = null;
						PortLagService_MB lagService = null;
						try {
							portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
							lagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
							lagInfoBefore = new PortLagInfo();
							lagInfoBefore.setId(portLagInfo.getId());
							lagInfoBefore = lagService.selectLAGByCondition(lagInfoBefore).get(0);
							lagInfoBefore.setMainLagPortLog(portService.getPortname(lagInfoBefore.getFlowControl()));
							lagInfoBefore.setStandLagPortLog(portService.getPortname(lagInfoBefore.getFloodBate()));
							List<Integer> numberList = new ArrayList<Integer>();
							numberList.add(Integer.parseInt(lagInfoBefore.getPortLagMember().split(",")[0]));
							if(numberList.get(0) > 0){
								lagInfoBefore.setPortLagMember1Log(portService.getAllPortNameByNumber(numberList, ConstantUtil.siteId).get(0));
							}
							numberList.clear();
							numberList.add(Integer.parseInt(lagInfoBefore.getPortLagMember().split(",")[1]));
							if(numberList.get(0) > 0){
								lagInfoBefore.setPortLagMember2Log(portService.getAllPortNameByNumber(numberList, ConstantUtil.siteId).get(0));
							}
							numberList.clear();
							numberList.add(Integer.parseInt(lagInfoBefore.getPortLagMember().split(",")[2]));
							if(numberList.get(0) > 0){
								lagInfoBefore.setPortLagMember3Log(portService.getAllPortNameByNumber(numberList, ConstantUtil.siteId).get(0));
							}
							numberList.clear();
							numberList.add(Integer.parseInt(lagInfoBefore.getPortLagMember().split(",")[3]));
							if(numberList.get(0) > 0){
								lagInfoBefore.setPortLagMember4Log(portService.getAllPortNameByNumber(numberList, ConstantUtil.siteId).get(0));
							}
							lagInfoBefore.setPriorityList(getProtocolSwitchList(lagInfoBefore.getExportQueue()));
							lagInfoBefore.setPortEnable(lagInfoBefore.getLagMode()==0?1:0);
						} catch (Exception e) {
							ExceptionManage.dispose(e,this.getClass());
						} finally {
							UiUtil.closeService_MB(portService);
							UiUtil.closeService_MB(lagService);
						}
						
						result = portLagDispatch.excuteUpdate(portLagInfo);
						operationLogResult = EOperationLogType.PORTLAGUPDATE.getValue();
					} else {
						result = portLagDispatch.excuteInsert(portLagInfo);
						operationLogResult = EOperationLogType.PORTLAGINSERT.getValue();
					}
					//添加日志记录
					portLagInfo.setPortEnable(portLagInfo.getLagMode()==0?1:0);
					portLagInfo.setPriorityList(getProtocolSwitchList(portLagInfo.getExportQueue()));
					String operateObjName = "";
					if(portLagInfo.getMainLagPortLog() != null){
						operateObjName = ResourceUtil.srcStr(StringKeysLbl.MAIN_LAG)+":"+portLagInfo.getMainLagPortLog();
					}else{
						operateObjName = ResourceUtil.srcStr(StringKeysLbl.STAND_LAG)+":"+portLagInfo.getStandLagPortLog();
					}
					AddOperateLog.insertOperLog(((PortLagDialog) portLagDialog).getOkButton(), operationLogResult, result, lagInfoBefore,
							portLagInfo, ConstantUtil.siteId, operateObjName, "lag");
					
					DialogBoxUtil.succeedDialog(PortLagController.this.panel, result);
					((PortLagDialog)portLagDialog).getRelevanceInstallDialog().dispose();
					((PortLagDialog)portLagDialog).getExportQueueDialog().dispose();
					((PortLagDialog)portLagDialog).getPortMemberDialog().dispose();
					portLagDialog.dispose();
					refresh();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}finally{
				}

			}
			
			@Override
			public boolean checking() {
				
				return true;
			}
		});
		((PortLagDialog) portLagDialog).getCancelButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				portLagDialog.dispose();
				((PortLagDialog)portLagDialog).getRelevanceInstallDialog().dispose();
				((PortLagDialog)portLagDialog).getExportQueueDialog().dispose();
				((PortLagDialog)portLagDialog).getPortMemberDialog().dispose();
			}
		});
		((PortLagDialog) portLagDialog).addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if(portLagDialog != null){
					((PortLagDialog)portLagDialog).getRelevanceInstallDialog().dispose();
					((PortLagDialog)portLagDialog).getExportQueueDialog().dispose();
					((PortLagDialog)portLagDialog).getPortMemberDialog().dispose();
					portLagDialog.dispose();
					portLagDialog = null;
				}
			}
		});
	}
	
	private List<CommonBean> getProtocolSwitchList(String exportQueue) {
		String[] protocolSwitchArr = exportQueue.split(",");
		List<CommonBean> protocolSwitchList = new ArrayList<CommonBean>();
		for (String arrValue : protocolSwitchArr) {
			CommonBean cb = new CommonBean();
			cb.setProtocolSwitch(arrValue.split("-")[0]);
			cb.setPriority(arrValue.split("-")[1]);
			cb.setProtocolSwitch(Integer.parseInt(arrValue.split("-")[0]) == 1?"1":"2");
			protocolSwitchList.add(cb);
		}
		return protocolSwitchList;
	}

	/**
	 * 选中一条记录后，查看详细信息
	 */
	@Override
	public void initDetailInfo() {
		try {
			initPortInfos();
			setQosQueueTableDatas();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	@SuppressWarnings("unchecked")
	private void initPortInfos() throws Exception {
		PortLagInfo portLagInfo = null;
		try {
			portLagInfo = this.panel.getSelect();
			this.panel.getLagPortTablePanel();
			this.panel.getLagPortTablePanel().initData(portLagInfo.getPortList());
			this.panel.getLagPortTablePanel().updateUI();
		} catch (Exception e) {
			throw e;
		} finally {
		}
	}

	private void setQosQueueTableDatas() throws Exception {

		PortLagInfo portLagInfo = null;
		try {
			portLagInfo = this.panel.getSelect();

			this.panel.getQosQueuePanel().getPortQosTableModel().getDataVector().clear();
			this.panel.getQosQueuePanel().getPortQosTableModel().fireTableDataChanged();
			Object data[] = new Object[] {};
			int rowCount = 0;
			if (!portLagInfo.getQosQueueList().isEmpty()) {
				for (QosQueue qosQueue : portLagInfo.getQosQueueList()) {
					data = new Object[] { ++rowCount, QosCosLevelEnum.from(qosQueue.getCos()), qosQueue.getCir(), qosQueue.getWeight(), qosQueue.getGreenLowThresh(), qosQueue.getGreenHighThresh(), qosQueue.getGreenProbability(), qosQueue.getYellowLowThresh(), qosQueue.getYellowHighThresh(), qosQueue.getYellowProbability(), qosQueue.isWredEnable(), qosQueue.getMostBandwidth() };
					this.panel.getQosQueuePanel().getPortQosTableModel().addRow(data);
				}
			} else {
				this.panel.getQosQueuePanel().getPortQosTableModel().getDataVector().clear();
				this.panel.getQosQueuePanel().getPortQosTableModel().fireTableDataChanged();

				for (QosCosLevelEnum level : QosCosLevelEnum.values()) {
					data = new Object[] { ++rowCount, level.toString(), 0, 16, 96, 128, 100, 64, 96, 100, Boolean.TRUE, ResourceUtil.srcStr(StringKeysObj.QOS_UNLIMITED) };
					this.panel.getQosQueuePanel().getPortQosTableModel().addRow(data);
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void synchro() {
		DispatchUtil portLagDispatch = null;
		try {
			portLagDispatch = new DispatchUtil(RmiKeys.RMI_PORTLAG);
			String result = portLagDispatch.synchro(ConstantUtil.siteId);
			DialogBoxUtil.succeedDialog(null, result);
			//添加日志记录
			PtnButton deleteButton=(PtnButton) this.panel.getSynchroButton();
			deleteButton.setOperateKey(EOperationLogType.PORTLAGSYNCHRO.getValue());
			deleteButton.setResult(1);
			this.refresh();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			portLagDispatch = null;
		}
	}
	
	/**
	 * 验证端口成员
	 * @return
	 */
	private boolean checkPortMember(){
		for(String string: lagInfo.getPortLagMember().split(",")){
			if(Integer.parseInt(string)>0){
				return false;
			}
		}
		return true;
	}

	public PortLagInfo getPortLagInfo() {
		return portLagInfo;
	}

	public void setPortLagInfo(PortLagInfo portLagInfo) {
		this.portLagInfo = portLagInfo;
	};
	
}
