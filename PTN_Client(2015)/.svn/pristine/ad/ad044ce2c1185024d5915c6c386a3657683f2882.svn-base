package com.nms.ui.ptn.ne.statusData.view.vpwsStatus;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.SiteStatusInfo;
import com.nms.db.bean.ptn.VpwsStatus;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
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
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.statusData.view.pwStatus.controller.PwStatusController;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class VpwsStatusPanel extends ContentView<VpwsStatus>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5372849005767322621L;
	private PtnButton jButton;
	private VpwsStatusPanel view;
	public VpwsStatusPanel() {
		super("vpwsStatus",RootFactory.CORE_MANAGE);
		view = this;
		init();
		add();
	}


	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_VPWS_STATUS)));
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
					siteInst.setStatusMark(29);
					siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
					result = siteDispatch.siteStatus(siteInst);
					if(null!=result&&result.getVpwsStatusList()!= null){
						DialogBoxUtil.succeedDialog(null, ResultString.CONFIG_SELECT);
						initValue(result.getVpwsStatusList());
						view.updateUI();
						view.initData(result.getVpwsStatusList());
						this.insertOpeLog(EOperationLogType.SERVICESTATES.getValue(),  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS), null,null);											
					}else{
						DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_FIND_DETAIL_FAIL));
						this.insertOpeLog(EOperationLogType.SERVICESTATES.getValue(),  ResourceUtil.srcStr(StringKeysTip.TIP_FIND_DETAIL_FAIL), null,null);	
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				} finally {
					UiUtil.closeService_MB(siteService);
				}
			}

			private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){				
				AddOperateLog.insertOperLog(jButton, operationType, result, oldMac, newMac, ConstantUtil.siteId,ResourceUtil.srcStr(StringKeysPanel.PANEL_VPWS_STATUS),"");				
			}
			
			@Override
			public boolean checking() {
				// TODO Auto-generated method stub
				return true;
			}
		});
	}
	
	public void initValue(List<VpwsStatus> vpwsStatusList){
		PwInfoService_MB pwInfoService = null;
		PortService_MB portService = null;
		PortInst portInst = null;
		PwInfo pwInfo = null;
		ElineInfoService_MB elineService = null;
		ElineInfo elineInfo = null;
		List<ElineInfo> elineInfos = null;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			for(VpwsStatus vpwsStatus : vpwsStatusList){
				pwInfo = pwInfoService.selectBysiteIdAndServiceId(ConstantUtil.siteId, vpwsStatus.getPwId()).get(0);
				vpwsStatus.setPwName(pwInfo.getPwName());
				elineInfos = elineService.selectNodeBySiteAndServiceId(ConstantUtil.siteId, vpwsStatus.getVpwsID());
				if(elineInfos != null && elineInfos.size()>0){
					elineInfo = elineInfos.get(0);
					vpwsStatus.setVpwsName(elineInfo.getName());
					if(vpwsStatus.getUniPortid()>0){
						portInst = new PortInst();
						portInst.setSiteId(ConstantUtil.siteId);
						portInst.setNumber(vpwsStatus.getUniPortid());
						if(vpwsStatus.getUniPortid()>=101){
							if(vpwsStatus.getUniPortid() ==101){
								 vpwsStatus.setUniPortName("LAG-1");
							}else if(vpwsStatus.getUniPortid() ==102){
								 vpwsStatus.setUniPortName("LAG-2");
							}else if(vpwsStatus.getUniPortid() ==103){
								 vpwsStatus.setUniPortName("LAG-3");
							}else if(vpwsStatus.getUniPortid() ==104){
								 vpwsStatus.setUniPortName("LAG-4");
							}else if(vpwsStatus.getUniPortid() ==105){
								 vpwsStatus.setUniPortName("LAG-5");
							}
						}else{
						   portInst = portService.select(portInst).get(0);
						   vpwsStatus.setUniPortName(portInst.getPortName());
						}
					}
					if(vpwsStatus.getNniPortid()>0){
						portInst = new PortInst();
						portInst.setSiteId(ConstantUtil.siteId);
						portInst.setNumber(vpwsStatus.getNniPortid());
						portInst = portService.select(portInst).get(0);
						vpwsStatus.setNniPortName(portInst.getPortName());
					}
					setVpwsValue(vpwsStatus);
				}
				
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(pwInfoService);
			portInst = null;
			pwInfo = null;
			elineInfo = null;
		}
	}
	
	/**
	 * 业务类型及匹配值赋值
	 * @param vpwsStatus
	 */
	public void setVpwsValue(VpwsStatus vpwsStatus){
		if("0".equals(vpwsStatus.getVpwsType())){
			vpwsStatus.setVpwsType("PORT");
			vpwsStatus.setVpwsValue1("");
			vpwsStatus.setVpwsValue2("");
		}else if("1".equals(vpwsStatus.getVpwsType())){
			vpwsStatus.setVpwsType("VLAN");
			vpwsStatus.setVpwsValue1((Integer.parseInt((vpwsStatus.getVpwsValue1().split(","))[4])*256+Integer.parseInt((vpwsStatus.getVpwsValue1().split(","))[5]))+"");
			vpwsStatus.setVpwsValue2("");
		}else if("2".equals(vpwsStatus.getVpwsType())){
			vpwsStatus.setVpwsType("VLAN PRI");
			vpwsStatus.setVpwsValue1((Integer.parseInt((vpwsStatus.getVpwsValue1().split(","))[4])*256+Integer.parseInt((vpwsStatus.getVpwsValue1().split(","))[5]))+"");
			vpwsStatus.setVpwsValue2("");
		}else if("64".equals(vpwsStatus.getVpwsType())){
			vpwsStatus.setVpwsType("PW");
			vpwsStatus.setVpwsValue1((Integer.parseInt((vpwsStatus.getVpwsValue1().split(","))[4])*256+Integer.parseInt((vpwsStatus.getVpwsValue1().split(","))[5]))+"");
			vpwsStatus.setVpwsValue2("");
		}else if("128".equals(vpwsStatus.getVpwsType())){
			vpwsStatus.setVpwsType("DSCP");
			vpwsStatus.setVpwsValue1((Integer.parseInt((vpwsStatus.getVpwsValue1().split(","))[4])*256+Integer.parseInt((vpwsStatus.getVpwsValue1().split(","))[5]))+"");
			vpwsStatus.setVpwsValue2("");
		}else if("4".equals(vpwsStatus.getVpwsType())){
			vpwsStatus.setVpwsType(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_MAC));
			String str = vpwsStatus.getVpwsValue1().replaceAll(",", "-");
			vpwsStatus.setVpwsValue1(str);
			vpwsStatus.setVpwsValue2("");
		}else if("8".equals(vpwsStatus.getVpwsType())){
			vpwsStatus.setVpwsType(ResourceUtil.srcStr(StringKeysLbl.LBL_PURPOSE_MAC));
			String str = vpwsStatus.getVpwsValue1().replaceAll(",", "-");
			vpwsStatus.setVpwsValue1(str);
			vpwsStatus.setVpwsValue2("");
		}else if("16".equals(vpwsStatus.getVpwsType())){
			vpwsStatus.setVpwsType(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_IP));
			String[] str = vpwsStatus.getVpwsValue1().split(",");
			vpwsStatus.setVpwsValue1(str[2]+"."+str[3]+"."+str[4]+"."+str[4]);
			vpwsStatus.setVpwsValue2("");
		}else if("32".equals(vpwsStatus.getVpwsType())){
			vpwsStatus.setVpwsType(ResourceUtil.srcStr(StringKeysLbl.LBL_PURPOSE_IP));
			String[] str = vpwsStatus.getVpwsValue1().split(",");
			vpwsStatus.setVpwsValue1(str[2]+"."+str[3]+"."+str[4]+"."+str[4]);
			vpwsStatus.setVpwsValue2("");
		}else if("3".equals(vpwsStatus.getVpwsType())){
			vpwsStatus.setVpwsType("VLAN+VLAN PRI");
			vpwsStatus.setVpwsValue1((Integer.parseInt((vpwsStatus.getVpwsValue1().split(","))[4])*256+Integer.parseInt((vpwsStatus.getVpwsValue1().split(","))[5]))+"");
			vpwsStatus.setVpwsValue2((Integer.parseInt((vpwsStatus.getVpwsValue2().split(","))[4])*256+Integer.parseInt((vpwsStatus.getVpwsValue2().split(","))[5]))+"");
		}else if("160".equals(vpwsStatus.getVpwsType())){
			vpwsStatus.setVpwsType(ResourceUtil.srcStr(StringKeysLbl.LBL_PURPOSE_IP)+"+"+"DSCP");
			String[] str = vpwsStatus.getVpwsValue1().split(",");
			vpwsStatus.setVpwsValue1(str[2]+"."+str[3]+"."+str[4]+"."+str[4]);
			vpwsStatus.setVpwsValue2((Integer.parseInt((vpwsStatus.getVpwsValue2().split(","))[4])*256+Integer.parseInt((vpwsStatus.getVpwsValue2().split(","))[5]))+"");
		}else if("40".equals(vpwsStatus.getVpwsType())){
			vpwsStatus.setVpwsType(ResourceUtil.srcStr(StringKeysLbl.LBL_PURPOSE_MAC)+"+"+ResourceUtil.srcStr(StringKeysLbl.LBL_PURPOSE_IP));
			String strMac = vpwsStatus.getVpwsValue1().replaceAll(",", "-");
			vpwsStatus.setVpwsValue1(strMac);
			String[] str = vpwsStatus.getVpwsValue2().split(",");
			vpwsStatus.setVpwsValue2(str[2]+"."+str[3]+"."+str[4]+"."+str[4]);
		}else if("20".equals(vpwsStatus.getVpwsType())){
			vpwsStatus.setVpwsType(ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_MAC)+"+"+ResourceUtil.srcStr(StringKeysLbl.LBL_SOURCE_IP));
			String strMac = vpwsStatus.getVpwsValue1().replaceAll(",", "-");
			vpwsStatus.setVpwsValue1(strMac);
			String[] str = vpwsStatus.getVpwsValue2().split(",");
			vpwsStatus.setVpwsValue2(str[2]+"."+str[3]+"."+str[4]+"."+str[4]);
		}else{
			vpwsStatus.setVpwsType(ResourceUtil.srcStr(StringKeysLbl.LBL_OTHER_TYPE));
			String str = vpwsStatus.getVpwsValue1().replaceAll(",", " ");
			vpwsStatus.setVpwsValue1(str);
			str = vpwsStatus.getVpwsValue2().replaceAll(",", " ");
			vpwsStatus.setVpwsValue2(str);
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
		controller = new PwStatusController();
		
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
