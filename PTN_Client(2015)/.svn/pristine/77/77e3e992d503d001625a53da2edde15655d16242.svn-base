package com.nms.ui.ptn.performance.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.perform.CurrentPerforInfo;
import com.nms.db.bean.perform.PathPerformCountInfo;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EMonitorCycle;
import com.nms.db.enums.EObjectType;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.performance.model.CurrentPerformanceFilter;
import com.nms.ui.ptn.performance.util.PerformanceOfflineUitl;

public class PathPerformCountFilterDialog extends PtnDialog {
	private static final long serialVersionUID = 8186959383566893786L;
	private JLabel lblPerformType;//监控对象类型
	private JComboBox cmbPerformType;
	private JLabel  lblMonitorObj;//监控对象
	private JComboBox cmbMonitorObj;
	private JLabel lblCycle;
	private JCheckBox rb15min;
	private JComboBox selectTimeType; 
	private JCheckBox rb24hour;
	private JComboBox selectTimeTypeOther; 
	private ButtonGroup group;
	private JPanel rb15jpanl;
	private JPanel rb24jpanl;
    private JPanel buttonPanel;
    private PtnButton confirm;//确定
	private JButton cancel;
	private JButton clear;
	private PathPerformCountPanel view;
	private CurrentPerformanceFilter filter;
	public PathPerformCountFilterDialog(PathPerformCountPanel view) {
		this.setModal(true);
		this.view = view;
		this.initComponents();
		this.setLayout();
		this.initData();
		this.addListener();
		UiUtil.showWindow(this, 420, 300);
	}
	
	private void initComponents() {
		this.setTitle(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER));
		this.lblPerformType = new JLabel(ResourceUtil.srcStr(StringKeysObj.OBJ_TYPE));
		this.cmbPerformType = new JComboBox();
		this.lblMonitorObj = new JLabel(ResourceUtil.srcStr(StringKeysObj.MONITORING_OBJ));
		this.cmbMonitorObj = new JComboBox();
		this.lblCycle = new JLabel(ResourceUtil.srcStr(StringKeysObj.MONITORING_PERIOD));
		this.group = new ButtonGroup();
		rb15min=new JCheckBox();
		rb15min.setSelected(true);
		selectTimeType=new JComboBox(); 
		selectTimeType.addItem(ResourceUtil.srcStr(StringKeysObj.NOWFifteMINUTES));
		String now=ResourceUtil.srcStr(StringKeysObj.NOWTIMEMINUTES);
		String nowfifte=ResourceUtil.srcStr(StringKeysObj.TIMEMINUTES);
		for(int i=1;i<17;i++){
			selectTimeType.addItem(now+i+nowfifte);
		}
		
		rb24hour=new JCheckBox();
		selectTimeTypeOther=new JComboBox(); 
		selectTimeTypeOther.addItem(ResourceUtil.srcStr(StringKeysObj.NOWHOURS));
		selectTimeTypeOther.addItem(ResourceUtil.srcStr(StringKeysObj.ONETWOHOURS));
		selectTimeTypeOther.setEnabled(false);
		
		group.add(rb15min);
		group.add(rb24hour);
		rb15jpanl=new JPanel();
		rb15jpanl.add(rb15min);
		rb15jpanl.add(selectTimeType);
		rb24jpanl=new JPanel();
		rb24jpanl.add(rb24hour);
		rb24jpanl.add(selectTimeTypeOther);
		this.clear = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER_CLEAR));
		this.confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM),false);
		this.cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.buttonPanel=new JPanel();
		this.buttonPanel.add(confirm);
		this.buttonPanel.add(cancel);
	}
	
	private void setLayout() {
		this.setCompentLayoutButton(buttonPanel,confirm,cancel);
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 40, 40, 40, 40, 80 };
		layout.columnWeights = new double[] { 0, 0, 0, 0, 0.3 };
		layout.rowHeights = new int[] { 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};
		layout.rowWeights = new double[] { 0, 0, 0, 0.3, 0.2,0, 0, 0, 0.2,0,0,0,0};
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(lblPerformType, c);
		this.add(lblPerformType);
		c.gridx = 1;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(cmbPerformType, c);
		this.add(cmbPerformType);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(lblMonitorObj, c);
		this.add(lblMonitorObj);
		
		c.gridx = 1;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(cmbMonitorObj, c);
		this.add(cmbMonitorObj);
		
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(lblCycle, c);
		this.add(lblCycle);
		
		c.gridx = 1;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(rb15jpanl, c);
		this.add(rb15jpanl);
		
		c.gridx = 3;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(rb24jpanl, c);
		this.add(rb24jpanl);
		
		c.gridx = 0;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.NONE;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(clear, c);
		this.add(clear);
		
		c.gridx = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(buttonPanel, c);
		this.add(buttonPanel);
	}

	/**
	 *  按钮所在按钮布局
	 */
	private void setCompentLayoutButton(JPanel jpenl,JButton button1,JButton button2) {
		GridBagConstraints gridBagConstraints=null;
		GridBagLayout gridBagLayout = null;
		try {
			gridBagLayout = new GridBagLayout();
			gridBagConstraints = new GridBagConstraints();
			gridBagLayout.columnWidths=new int[]{15,15};
			gridBagLayout.columnWeights=new double[]{0,0};
			gridBagLayout.rowHeights=new int[]{21};
			gridBagLayout.rowWeights=new double[]{0};
			
			gridBagConstraints.insets=new Insets(5,5,5,5);
			gridBagConstraints= new GridBagConstraints();
			gridBagConstraints.fill=GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(button1, gridBagConstraints);
			
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(button2, gridBagConstraints);
			
			jpenl.setLayout(gridBagLayout);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private void addListener() {
		//监控对象类型
		cmbPerformType.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				performTypeChange();
			}
		});
		
		//保存按钮
		confirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSaveAction();
			}
		});
		
		// 取消按钮
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		//清除按钮
		clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clear();
				
			}
		});
		
		rb24hour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(!rb24hour.isSelected()){
					selectTimeTypeOther.setEnabled(false);
				}else{
					selectTimeTypeOther.setEnabled(true);
					selectTimeType.setEditable(false);
					selectTimeType.setEnabled(false);
				}
			}
		});
		
		rb15min.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(!rb15min.isSelected()){
					selectTimeType.setEnabled(false);
				}else{
					selectTimeType.setEnabled(true);
					selectTimeTypeOther.setEditable(false);
					selectTimeTypeOther.setEnabled(false);
				}
			}
		});
	}


	/**
	 * 初始化监控对象类型
	 */
	private void initData() {
		//监控对象类型包括: TUNNEL/PW/以太网业务/TDM业务
		this.cmbPerformType.addItem(new ControlKeyValue("0", ""));
		this.cmbPerformType.addItem(new ControlKeyValue("1", "TUNNEL"));
		this.cmbPerformType.addItem(new ControlKeyValue("2", "PW"));
		this.cmbPerformType.addItem(new ControlKeyValue("3", "以太网业务"));
		this.cmbPerformType.addItem(new ControlKeyValue("4", "TDM业务"));
	}
	
	/**
	 * 监控对象类型改变时加载对应的监控对象
	 */
	private void performTypeChange() {
		ControlKeyValue selectedItem = (ControlKeyValue) this.cmbPerformType.getSelectedItem();
		int id = Integer.parseInt(selectedItem.getId());
		this.cmbMonitorObj.removeAllItems();
		if(id == 1){
			//TUNNEL
			TunnelService_MB tunnelService = null;
			try {
				tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
				List<Tunnel> tunnelList = tunnelService.select();
				this.initCmbMonitorObj(tunnelList, 1);
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			} finally {
				UiUtil.closeService_MB(tunnelService);
			}
		}else if(id == 2){
			//PW
			PwInfoService_MB pwService = null;
			try {
				pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
				List<PwInfo> pwList = pwService.select();	
				this.initCmbMonitorObj(pwList, 2);
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			} finally {
				UiUtil.closeService_MB(pwService);
			}
		}else if(id == 3){
			//以太网业务
			ElineInfoService_MB elineService = null;
			EtreeInfoService_MB etreeService = null;
			ElanInfoService_MB elanService = null;
			try {
				elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
				List<ElineInfo> elineList = elineService.select();
				this.initCmbMonitorObj(elineList, 3);
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				Map<Integer, List<EtreeInfo>> etreeMap_netWork = etreeService.select();
				this.initCmbMonitorObj(etreeMap_netWork, 4);
				elanService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
				Map<Integer, List<ElanInfo>> elanMap_netWork = elanService.select();
				this.initCmbMonitorObj(elanMap_netWork, 5);
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			} finally {
				UiUtil.closeService_MB(elineService);
				UiUtil.closeService_MB(etreeService);
				UiUtil.closeService_MB(elanService);
			}
		}else if(id == 4){
			//TDM业务
			CesInfoService_MB cesService = null;
			try {
				cesService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
				List<CesInfo> cesList = cesService.select();
				this.initCmbMonitorObj(cesList, 6);
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			} finally {
				UiUtil.closeService_MB(cesService);
			}
		}
	}
	
	/**
	 * 初始化监控对象
	 */
	@SuppressWarnings("unchecked")
	private void initCmbMonitorObj(Object obj, int type) {
		if(type == 1){
			//Tunnel
			List<Tunnel> tunnelList = (List<Tunnel>) obj;
			for (Tunnel tunnel : tunnelList) {
				ControlKeyValue con = new ControlKeyValue(EServiceType.TUNNEL.toString(), tunnel.getTunnelName(), tunnel);
				this.cmbMonitorObj.addItem(con);
			}
		}else if(type == 2){
			//Pw 
			List<PwInfo> pwList = (List<PwInfo>) obj;
			for (PwInfo pw : pwList) {
				ControlKeyValue con = new ControlKeyValue(EServiceType.PW.toString(), pw.getPwName(), pw);
				this.cmbMonitorObj.addItem(con);
			}
		}else if(type == 3){
			//eline
			List<ElineInfo> elineList = (List<ElineInfo>) obj;
			for (ElineInfo eline : elineList) {
				ControlKeyValue con = new ControlKeyValue(EServiceType.ELINE.toString(), eline.getName(), eline);
				this.cmbMonitorObj.addItem(con);
			}
		}else if(type == 4){
			//etree
			Map<Integer, List<EtreeInfo>> etreeList = (Map<Integer, List<EtreeInfo>>) obj;
			for(Integer serviceId : etreeList.keySet()){
				List<EtreeInfo> eList = etreeList.get(serviceId);
				if(eList.size() > 0){
					ControlKeyValue con = new ControlKeyValue(EServiceType.ETREE.toString(), eList.get(0).getName(), eList);
					this.cmbMonitorObj.addItem(con);
				}
			}
		}else if(type == 5){
			//elan
			Map<Integer, List<ElanInfo>> elanList = (Map<Integer, List<ElanInfo>>) obj;
			for(Integer serviceId : elanList.keySet()){
				List<ElanInfo> eList = elanList.get(serviceId);
				if(eList.size() > 0){
					ControlKeyValue con = new ControlKeyValue(EServiceType.ELAN.toString(), eList.get(0).getName(), eList);
					this.cmbMonitorObj.addItem(con);
				}
			}
		}else if(type == 6){
			//ces
			List<CesInfo> cesList = (List<CesInfo>) obj;
			for (CesInfo ces : cesList) {
				ControlKeyValue con = new ControlKeyValue(EServiceType.CES.toString(), ces.getName(), ces);
				this.cmbMonitorObj.addItem(con);
			}
		}
	}
	
	/**
	 * 清除界面数据
	 */
	private void clear(){
		this.cmbPerformType.setSelectedIndex(0);
		this.cmbMonitorObj.removeAllItems();
		group.clearSelection();
		if(selectTimeTypeOther.isEnabled()){
			selectTimeTypeOther.setEnabled(false);
		}
		if(selectTimeType.isEnabled()){
			selectTimeType.setEnabled(false);
		}
	}

	private void btnSaveAction(){
		PerformanceOfflineUitl performanceOfflineUitl = new PerformanceOfflineUitl();
		try {
			boolean flag = this.checkIsFull();
			if(flag){
				this.filter = this.getFilter();
				List<PathPerformCountInfo> performList = new ArrayList<PathPerformCountInfo>();
				if (filter != null) {
					performList = this.getCurrPerformCount();
				} else {
					DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_FILTER));
					return;
				}
				// 添加日志记录
				AddOperateLog.insertOperLog(this.confirm, EOperationLogType.CURRENTPERFORMANCESELECT.getValue(),null);

				this.view.clear();
				this.view.initData(performList);
				this.view.updateUI();
				DialogBoxUtil.succeedDialog(this,performanceOfflineUitl.getPerformanceResult(filter));
				this.dispose();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			performanceOfflineUitl = null;
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<PathPerformCountInfo> getCurrPerformCount() {
		List<PathPerformCountInfo> countList = new ArrayList<PathPerformCountInfo>();
		List<CurrentPerforInfo> currPerformList = new ArrayList<CurrentPerforInfo>();
		List<CurrentPerforInfo> infoList = this.queryPerforByFilter(this.filter);
		this.setFilterCurrentPerformance(infoList, currPerformList, this.filter);
		infoList = this.queryPerforByFilter(this.filter);
		ControlKeyValue conType = (ControlKeyValue) this.cmbPerformType.getSelectedItem();
		ControlKeyValue conObj = (ControlKeyValue) this.cmbMonitorObj.getSelectedItem();
		int index = Integer.parseInt(conType.getId());
		if(index == 2 || index == 3 || index == 4){
			//2是pw, 3是以太网业务, 4是tdm业务
			PwInfoService_MB pwService = null;
			String serviceType = conObj.getId();
			try {
				pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
				if(serviceType.equals(EServiceType.ELINE.toString())){
					ElineInfo eline = (ElineInfo) conObj.getObject();
					try {
						PwInfo pw = new PwInfo();
						pw.setPwId(eline.getPwId());
						pw = pwService.selectBypwid_notjoin(pw);
						this.getPerformData(currPerformList, countList, index, pw, pw.getASiteId(), eline.getName());
						this.getPerformData(currPerformList, countList, index, pw, pw.getZSiteId(),  eline.getName());
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					}
				}else if(serviceType.equals(EServiceType.ETREE.toString())){
					List<EtreeInfo> etreeList = (List<EtreeInfo>) conObj.getObject();
					for (EtreeInfo etree : etreeList) {
						try {
							PwInfo pw = new PwInfo();
							pw.setPwId(etree.getPwId());
							pw = pwService.selectBypwid_notjoin(pw);
							this.getPerformData(currPerformList, countList, index, pw, pw.getASiteId(), etree.getName());
							this.getPerformData(currPerformList, countList, index, pw, pw.getZSiteId(), etree.getName());
						} catch (Exception e) {
							ExceptionManage.dispose(e, this.getClass());
						}
					}
				}else if(serviceType.equals(EServiceType.ELAN.toString())){
					List<ElanInfo> elanList = (List<ElanInfo>) conObj.getObject();
					for (ElanInfo elan : elanList) {
						try {
							PwInfo pw = new PwInfo();
							pw.setPwId(elan.getPwId());
							pw = pwService.selectBypwid_notjoin(pw);
							this.getPerformData(currPerformList, countList, index, pw, pw.getASiteId(), elan.getName());
							this.getPerformData(currPerformList, countList, index, pw, pw.getZSiteId(), elan.getName());
						} catch (Exception e) {
							ExceptionManage.dispose(e, this.getClass());
						}
					}
				}else if(serviceType.equals(EServiceType.CES.toString())){
					CesInfo ces = (CesInfo) conObj.getObject();
					try {
						PwInfo pw = new PwInfo();
						pw.setPwId(ces.getPwId());
						pw = pwService.selectBypwid_notjoin(pw);
						this.getPerformData(currPerformList, countList, index, pw, pw.getASiteId(), ces.getName());
						this.getPerformData(currPerformList, countList, index, pw, pw.getZSiteId(), ces.getName());
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					}
				}else if(serviceType.equals(EServiceType.PW.toString())){
					PwInfo pw = (PwInfo)conObj.getObject();
					this.getPerformData(currPerformList, countList, index, pw, pw.getASiteId(), pw.getPwName());
					this.getPerformData(currPerformList, countList, index, pw, pw.getZSiteId(), pw.getPwName());
				}
			} catch (Exception e1) {
				ExceptionManage.dispose(e1,this.getClass());
			} finally {
				UiUtil.closeService_MB(pwService);
			}
		}else{
			//tunnel
			Tunnel tunnel = (Tunnel) conObj.getObject();
			this.getPerformData(currPerformList, countList, index, tunnel, tunnel.getaSiteId(), tunnel.getTunnelName());
			this.getPerformData(currPerformList, countList, index, tunnel, tunnel.getzSiteId(), tunnel.getTunnelName());
		}
		return countList;
	}
	
	private void getPerformData(List<CurrentPerforInfo> currPerformList,List<PathPerformCountInfo> countList, int index, Object obj, int siteId, String name) {
		PathPerformCountInfo count = new PathPerformCountInfo();
		String objName = "";
		count.setTime(this.getcurrentTime());
		SiteService_MB siteService = null;
		SiteInst site = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			site = siteService.select(siteId);
			if(site != null){
				count.setSiteName(site.getCellId());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		if(index == 1){
			objName = ((Tunnel)obj).getTunnelName();
		}else{
			objName = ((PwInfo)obj).getPwName();
		}
		for(CurrentPerforInfo c : currPerformList){
			if(c.getSiteId() == siteId){
				//tunnel
				if(index == 1){
					if(c.getObjectName().contains(objName)){
						this.getValue(c, count, 57, 58, 59, 60, 49, 50);
					}
				}else{
					if(c.getObjectName().contains(objName)){
						this.getValue(c, count, 65, 66, 69, 70, 55, 56);
					}
				}
			}
		}
		count.setObjectName(name);
		if(site != null && site.getLoginstatus() != 0){
			countList.add(count);		
		}
	}

	private void getValue(CurrentPerforInfo c, PathPerformCountInfo count, int code1, int code2, int code3,
			int code4, int code5, int code6) {
		if(c.getPerformanceCode() == code1){
			//lm近端丢包率
			count.setPacklosr_near(c.getPerformanceValue()+"");
		}else if(c.getPerformanceCode() == code2){
			//lm远端丢包率
			count.setPacklosr_far(c.getPerformanceValue()+"");
		}else if(c.getPerformanceCode() == code3){
			//时延秒
			count.setPmpackdelay_s(c.getPerformanceValue()+"");
		}else if(c.getPerformanceCode() == code4){
			//时延纳秒
			count.setPmpackdelay_ns(c.getPerformanceValue()+"");
		}else if(c.getPerformanceCode() == code5){
			//接收CV包统计
			count.setRx_cv(c.getPerformanceValue()+"");
		}else if(c.getPerformanceCode() == code6){
			//发送CV包统计
			count.setTx_cv(c.getPerformanceValue()+"");
		}
	}

	private String getcurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}

	/**
	 * 查看界面数据是否选择完整,完整返回true,不完整返回false
	 */
	private boolean checkIsFull() {
		if(this.cmbPerformType.getSelectedIndex() == 0){
			DialogBoxUtil.errorDialog(this, "请选择监控对象类型");
			return false;
		}
		if(this.cmbMonitorObj.getSelectedItem() == null){
			DialogBoxUtil.errorDialog(this, "请先新建监控对象");
			return false;
		}
		if (!rb15min.isSelected() && !rb24hour.isSelected()) {
			JOptionPane.showMessageDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_MONITORING_PERIOD));
			return false;
		}
		return true;
	}

	/**
	 *  获取当前选择的过滤对象
	 */
	private CurrentPerformanceFilter getFilter() throws Exception {
		CurrentPerformanceFilter filter = new CurrentPerformanceFilter();
		//根据网元查询
		filter.setSiteInsts(this.getSiteIdList());
		filter.setObjectType(EObjectType.SITEINST);
		// 添加性能类型条件
		ControlKeyValue conType = (ControlKeyValue) this.cmbPerformType.getSelectedItem();
		int index = Integer.parseInt(conType.getId());
		List<Integer> capIdList = new ArrayList<Integer>();//性能类型
		List<String> descList = new ArrayList<String>();//性能类别
		StringBuilder strTypeBuilder = new StringBuilder();
		if(index == 2 || index == 3 || index == 4){
			//pw || 以太网业务 || tdm业务
			capIdList.add(65);
			capIdList.add(66);
			capIdList.add(69);
			capIdList.add(70);
			capIdList.add(55);
			capIdList.add(56);
			descList.add("TMC层LM近端丢包率");
			descList.add("TMC层LM远端丢包率");
			descList.add("TMC时延秒");
			descList.add("TMC时延纳秒");
			descList.add("PW接收CV包统计");
			descList.add("PW发送CV包统计");
		}else if(index == 1){
			//tunnel
			capIdList.add(57);
			capIdList.add(58);
			capIdList.add(59);
			capIdList.add(60);
			capIdList.add(49);
			capIdList.add(50);
			descList.add("TMP层LM近端丢包率");
			descList.add("TMP层LM远端丢包率");
			descList.add("TMP层时延秒");
			descList.add("TMP层时延纳秒");
			descList.add("通路接收CV包统计");
			descList.add("通路发送CV包统计");
		}
		strTypeBuilder.append("TMP/TMC");//性能类型
		filter.getCapabilityIdList().addAll(capIdList);//主键id
		filter.setTypeStr(strTypeBuilder.toString());
		//添加性能类别条件
		filter.getCapabilityNameList().addAll(descList);
		//监控周期
		if (rb24hour.isSelected()) {
			filter.setMonitorCycle(EMonitorCycle.HOUR24);
			if(selectTimeTypeOther.getSelectedIndex()==0){
				filter.setPerformanceCount(255);
				filter.setPerformanceBeginCount(0);
				filter.setPerformanceType(32);
				filter.setPerformanceMonitorTime(porseTime(100));
			}else if(selectTimeTypeOther.getSelectedIndex()==1){
				filter.setPerformanceCount(255);
				filter.setPerformanceBeginCount(1);
				filter.setPerformanceType(33);
				filter.setPerformanceMonitorTime(porseTime(101));
			}
		} else if (rb15min.isSelected()) {
			filter.setMonitorCycle(EMonitorCycle.MIN15);
			filter.setPerformanceType(0);
			if(selectTimeType.getSelectedIndex()==0){
				filter.setPerformanceCount(0);
				filter.setPerformanceBeginCount(0);
				filter.setPerformanceMonitorTime(porseTime(0));
			}else {
				filter.setPerformanceCount(1);
				filter.setPerformanceBeginCount(selectTimeType.getSelectedIndex());
				filter.setPerformanceBeginDataTime(testTime(selectTimeType.getSelectedIndex()));
				filter.setPerformanceMonitorTime(porseTime(selectTimeType.getSelectedIndex()));
			}
		} else {
			filter.setMonitorCycle(null);
		}
		//零值过滤
		filter.setFilterZero(0);
		return filter;
	}
	
	@SuppressWarnings("unchecked")
	private List<Integer> getSiteIdList() {
		List<Integer> siteIdList = new ArrayList<Integer>();
		ControlKeyValue conType = (ControlKeyValue) this.cmbPerformType.getSelectedItem();
		ControlKeyValue conObj = (ControlKeyValue) this.cmbMonitorObj.getSelectedItem();
		int index = Integer.parseInt(conType.getId());
		if(index == 2 || index == 3 || index == 4){
			//2是pw, 3是以太网业务, 4是tdm业务
			PwInfoService_MB pwService = null;
			String serviceType = conObj.getId();
			try {
				pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
				if(serviceType.equals(EServiceType.ELINE.toString())){
					ElineInfo eline = (ElineInfo) conObj.getObject();
					try {
						PwInfo pw = new PwInfo();
						pw.setPwId(eline.getPwId());
						pw = pwService.selectBypwid_notjoin(pw);
						if(!siteIdList.contains(pw.getASiteId())){
							siteIdList.add(pw.getASiteId());
						}
						if(!siteIdList.contains(pw.getZSiteId())){
							siteIdList.add(pw.getZSiteId());
						}
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					}
				}else if(serviceType.equals(EServiceType.ETREE.toString())){
					List<EtreeInfo> etreeList = (List<EtreeInfo>) conObj.getObject();
					for (EtreeInfo etree : etreeList) {
						try {
							PwInfo pw = new PwInfo();
							pw.setPwId(etree.getPwId());
							pw = pwService.selectBypwid_notjoin(pw);
							if(!siteIdList.contains(pw.getASiteId())){
								siteIdList.add(pw.getASiteId());
							}
							if(!siteIdList.contains(pw.getZSiteId())){
								siteIdList.add(pw.getZSiteId());
							}
						} catch (Exception e) {
							ExceptionManage.dispose(e, this.getClass());
						}
					}
				}else if(serviceType.equals(EServiceType.ELAN.toString())){
					List<ElanInfo> elanList = (List<ElanInfo>) conObj.getObject();
					for (ElanInfo elan : elanList) {
						try {
							PwInfo pw = new PwInfo();
							pw.setPwId(elan.getPwId());
							pw = pwService.selectBypwid_notjoin(pw);
							if(!siteIdList.contains(pw.getASiteId())){
								siteIdList.add(pw.getASiteId());
							}
							if(!siteIdList.contains(pw.getZSiteId())){
								siteIdList.add(pw.getZSiteId());
							}
						} catch (Exception e) {
							ExceptionManage.dispose(e, this.getClass());
						}
					}
				}else if(serviceType.equals(EServiceType.CES.toString())){
					CesInfo ces = (CesInfo) conObj.getObject();
					try {
						PwInfo pw = new PwInfo();
						pw.setPwId(ces.getPwId());
						pw = pwService.selectBypwid_notjoin(pw);
						if(!siteIdList.contains(pw.getASiteId())){
							siteIdList.add(pw.getASiteId());
						}
						if(!siteIdList.contains(pw.getZSiteId())){
							siteIdList.add(pw.getZSiteId());
						}
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					}
				}else if(serviceType.equals(EServiceType.PW.toString())){
					PwInfo pw = (PwInfo)conObj.getObject();
					if(!siteIdList.contains(pw.getASiteId())){
						siteIdList.add(pw.getASiteId());
					}
					if(!siteIdList.contains(pw.getZSiteId())){
						siteIdList.add(pw.getZSiteId());
					}
				}
			} catch (Exception e1) {
				ExceptionManage.dispose(e1,this.getClass());
			} finally {
				UiUtil.closeService_MB(pwService);
			}
		}else{
			//tunnel
			Tunnel tunnel = (Tunnel) conObj.getObject();
			
			if(!siteIdList.contains(tunnel.getASiteId())){
				siteIdList.add(tunnel.getASiteId());
			}
			if(!siteIdList.contains(tunnel.getZSiteId())){
				siteIdList.add(tunnel.getZSiteId());
			}
		}
		return siteIdList;
	}

	/**
	 * 根据性能条件查询性能值
	 */
	private List<CurrentPerforInfo> queryPerforByFilter(CurrentPerformanceFilter filter) {
		List<CurrentPerforInfo> queryPerforList = new ArrayList<CurrentPerforInfo>();
		try {
			DispatchUtil dispatch = new DispatchUtil(RmiKeys.RMI_PERFORMANCE);
			queryPerforList = dispatch.executeQueryCurrPerforByFilter(filter);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		return queryPerforList;
	}

	private void setFilterCurrentPerformance(List<CurrentPerforInfo> infos, List<CurrentPerforInfo> currPerformFilterList, CurrentPerformanceFilter filter) {
		String[] filrertypes = filter.getTypeStr().trim().split(",");

		for (CurrentPerforInfo currentPerformanceInfo : infos) {
			if (currentPerformanceInfo.getCapability() != null) {
				for (int i = 0; i < filrertypes.length; i++) {
					if (filrertypes[i].equals(currentPerformanceInfo.getCapability().getCapabilitytype())) {
						for (String strType : filter.getCapabilityNameList()) {
							if (filter.getFilterZero() > 0) {
								if (currentPerformanceInfo.getCapability().getCapabilitydesc().equalsIgnoreCase(strType)
										&& currentPerformanceInfo.getPerformanceValue() != 0
										&& currentPerformanceInfo.getObjectName() != null) {
									currPerformFilterList.add(currentPerformanceInfo);
								}
							} else {
								if (currentPerformanceInfo.getCapability().getCapabilitydesc().equalsIgnoreCase(strType)
										&& currentPerformanceInfo.getObjectName() != null
										) {
									currPerformFilterList.add(currentPerformanceInfo);
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 *处理时间 
	 * @param preseTime
	 * @return
	 */
	private long testTime(int size) {
		long filterTime = 0;
		try {
			long preseTime = new Date().getTime() - size*15*60*1000;
			SimpleDateFormat fat = new SimpleDateFormat("HH:mm");
			SimpleDateFormat fat2 = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat fat3 =  new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = new Date(preseTime);
			String preseTimeYear = fat2.format(date);
			String preseTimeString = fat.format(date);
			String[] str = preseTimeString.split(":");
			int i = Integer.valueOf(str[1]) / 15;
			preseTimeYear = preseTimeYear + " " + str[0] + ":";
			if (i == 0) {
				preseTimeString = preseTimeYear  + "00";
			}
			if (i == 1) {
				preseTimeString = preseTimeYear + "15";
			}
			if (i == 2) {
				preseTimeString = preseTimeYear + "30";
			}
			if (i == 3) {
				preseTimeString = preseTimeYear + "45";
			}
			
			filterTime=fat3.parse(preseTimeString).getTime();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return filterTime;
	}
	
	/**
	 *处理时间 
	 * @param preseTime
	 * @return
	 */
	private String porseTime(int size) {
		SimpleDateFormat fat2 = null;
		String preseTimeString = null;
		try {
			if(size >= 100){
				fat2 = new SimpleDateFormat("yyyy-MM");
				SimpleDateFormat fat = new SimpleDateFormat("d ");
				long preseTime = new Date().getTime() - size*24*60*60*1000;
				Date date = new Date(preseTime);
				String preseTimeYear = fat2.format(date);
				if(size == 100){
					preseTimeString = preseTimeYear ;
				}if(size == 101){
					date = new Date(preseTime);
					preseTimeString = fat.format(date);
					preseTimeString = preseTimeYear+"-"+"";
				}
			}else{
				fat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				long parseTime = testTime(size);
				preseTimeString = fat2.format(new Date(parseTime));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return preseTimeString;
	}
	
}