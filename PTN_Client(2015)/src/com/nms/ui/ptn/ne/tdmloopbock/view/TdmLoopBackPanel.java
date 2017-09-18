package com.nms.ui.ptn.ne.tdmloopbock.view;


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.bean.equipment.port.TdmLoopInfo;
import com.nms.db.bean.ptn.SiteRoate;
import com.nms.model.ptn.SiteRoateService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ContentView;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.tdmloopbock.controller.TdmLoopBackController;
import com.nms.ui.ptn.ne.tdmloopbock.view.dialog.TdmLoopConfigDialog;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class TdmLoopBackPanel extends ContentView<E1Info> {

	private static final long serialVersionUID = 1L;
	private PtnButton lineLoopButton;
	private PtnButton equipmentButton;
	
	public TdmLoopBackPanel() {	
		super("whE1",RootFactory.CORE_MANAGE);
		this.init();
		this.addActionListener();
		this.getRefreshButton().doClick();
	}

	public void init() {
		getContentPanel().setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_SOLT_TDMLOOPBACK)));
		setLayout();
	}
	
	public void addActionListener(){
		//线路环回
		lineLoopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (getAllSelect().size() == 0) {
					DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE));
				} else if (getAllSelect().size() >1){
					DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
				}else {
					SiteRoateService_MB siteRoate=null;
					E1Info e1 = getAllSelect().get(0);
					TdmLoopInfo tdm =null;
					SiteRoate roate=null;
					try {
						siteRoate =(SiteRoateService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITEROATE);
						roate=new SiteRoate();
						roate.setType("TdmLoopLine");
						roate.setSiteId(ConstantUtil.siteId);
						roate.setRoate(e1.getLegId());
						List<SiteRoate> sroate=siteRoate.querSiteRoateByRoate(roate);
						if(sroate!=null && sroate.size()==1){
						    tdm = new TdmLoopInfo();
						    tdm.setLegId(sroate.get(0).getTypeId());
						    tdm.setLoopType(0);
						    tdm.setSiteId(ConstantUtil.siteId);
						    tdm.setSwitchStatus(sroate.get(0).getSiteRoate());
						    tdm.setE1Id(sroate.get(0).getRoate());
						}
						new TdmLoopConfigDialog(e1, "线路环回", 0,tdm);
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}finally{
						UiUtil.closeService_MB(siteRoate);
						roate=null;
					}
				}
			}
		});
		//设备环回
		equipmentButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				 
				if (getAllSelect().size() == 0) {
					DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_MORE));
				} else if (getAllSelect().size() >1){
					DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
				}else {				
					SiteRoateService_MB siteRoate=null;
					E1Info e1 = getAllSelect().get(0);
					TdmLoopInfo tdm =null;
					SiteRoate roate=null;
					try {
						siteRoate =(SiteRoateService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITEROATE);
						roate=new SiteRoate();
						roate.setType("TdmLoopEquip");
						roate.setSiteId(ConstantUtil.siteId);
						roate.setRoate(e1.getLegId()-1);
						List<SiteRoate> sroate=siteRoate.querSiteRoateByRoate(roate);
						if(sroate!=null && sroate.size()==1){
						    tdm = new TdmLoopInfo();
						    tdm.setLegId(e1.getLegId());
						    tdm.setLoopType(1);
						    tdm.setSiteId(ConstantUtil.siteId);
						    tdm.setSwitchStatus(sroate.get(0).getSiteRoate());
						    tdm.setE1Id(sroate.get(0).getRoate());
						}				
						new TdmLoopConfigDialog(e1, "设备环回", 1,tdm);
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}finally{
						UiUtil.closeService_MB(siteRoate);
						roate=null;					
					}
				}
			}
		});
	}
	
	public void setLayout() {
		GridBagLayout panelLayout = new GridBagLayout();
		this.setLayout(panelLayout);
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
		controller = new TdmLoopBackController(this);
	}
	
	@Override
	public List<JButton> setNeedRemoveButtons() {
		List<JButton> needRemoveButtons = new ArrayList<JButton>();
		needRemoveButtons.add(getSearchButton());
		needRemoveButtons.add(getAddButton());
		needRemoveButtons.add(getDeleteButton());
		needRemoveButtons.add(getSynchroButton());
		needRemoveButtons.add(getUpdateButton());
		needRemoveButtons.add(getFilterButton());
		needRemoveButtons.add(getClearFilterButton());
		needRemoveButtons.add(getFiterZero());
		needRemoveButtons.add(getExportButton());
		needRemoveButtons.add(getInportButton());
		return needRemoveButtons;
	}
	
	@Override
	public List<JButton> setAddButtons(){
		List<JButton> needAddButtons = new ArrayList<JButton>();
		lineLoopButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_LINELOOP),false,RootFactory.CORE_MANAGE);
		equipmentButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_EQUIPMENTLOOP),false,RootFactory.CORE_MANAGE);
		needAddButtons.add(lineLoopButton);
		needAddButtons.add(equipmentButton);
		return needAddButtons;
	}
}

