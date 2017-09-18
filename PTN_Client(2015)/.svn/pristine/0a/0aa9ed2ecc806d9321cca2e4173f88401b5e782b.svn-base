package com.nms.ui.ptn.clock.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.CommonBean;
import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.bean.ptn.clock.FrequencyInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.manager.util.ClockUtil;

public class ClockRorateDialog extends PtnDialog {

	private static final long serialVersionUID = -800170517095171465L;
	private JRadioButton outerClockLabel;
	private JRadioButton ge1;
	private JRadioButton ge2;
	private JRadioButton ge3;
	private JRadioButton ge4;
	private JRadioButton ge5;
	private JRadioButton ge6;
	private JRadioButton ge7;
	private JRadioButton ge8;
	private JRadioButton ge9;
	private JRadioButton ge10;
	private JRadioButton ge11;
	private JRadioButton ge12;
	private JRadioButton ge13;
	private JRadioButton ge14;
	private JRadioButton ge15;
	private JRadioButton ge16;
	private JRadioButton ge17;
	private JRadioButton ge18;
	private JRadioButton ge19;
	private JRadioButton ge20;
	private JRadioButton ge21;
	private JRadioButton ge22;
	private JRadioButton ge23;
	private JRadioButton ge24;
	private JRadioButton ge25;
	private JRadioButton ge26;
	private JRadioButton clear;
	private ButtonGroup buttonGroup;
	private PtnButton saveButton;
	private JButton cancleButton;
	private JPanel contentPanel;
	private JPanel buttonPanel;
	private JScrollPane scrollPanel;
	private JPanel titlePanel;
	private JLabel lblTitle;
	private SiteRoate siteR;
	private FrequencyInfo info = null;
	private ClockRorateDialog clockRorateDialog;
	private List<PortInst> portList = new ArrayList<PortInst>();
	private List<JRadioButton> buttonList = new ArrayList<JRadioButton>();
	
	public ClockRorateDialog(FrequencyInfo frequencyInfo,SiteRoate siteRoate){
		info = frequencyInfo;
		siteR = siteRoate;
		clockRorateDialog = this;
		ClockUtil clockUtil=new ClockUtil();
		this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CLOCKRORATE));
		portList = clockUtil.getFrequencyPorts(ConstantUtil.siteId);
		this.addButtonList();
		this.initComponent();
		this.setLayout();
		this.addListener();
		if(siteR != null){
			this.initValues(siteR);
		}
	}
	
	private void addButtonList() {
		buttonList.add(ge1 = new JRadioButton() );
		buttonList.add(ge2 = new JRadioButton() );
		buttonList.add(ge3 = new JRadioButton() );
		buttonList.add(ge4 = new JRadioButton() );
		buttonList.add(ge5 = new JRadioButton() );
		buttonList.add(ge6 = new JRadioButton() );
		buttonList.add(ge7 = new JRadioButton() );
		buttonList.add(ge8 = new JRadioButton() );
		buttonList.add(ge9 = new JRadioButton() );
		buttonList.add(ge10 = new JRadioButton() );
		buttonList.add(ge11 = new JRadioButton() );
		buttonList.add(ge12 = new JRadioButton() );
		buttonList.add(ge13 = new JRadioButton() );
		buttonList.add(ge14 = new JRadioButton() );
		buttonList.add(ge15 = new JRadioButton() );
		buttonList.add(ge16 = new JRadioButton() );
		buttonList.add(ge17 = new JRadioButton() );
		buttonList.add(ge18 = new JRadioButton() );
		buttonList.add(ge19 = new JRadioButton() );
		buttonList.add(ge20 = new JRadioButton() );
		buttonList.add(ge21 = new JRadioButton() );
		buttonList.add(ge22 = new JRadioButton() );
		buttonList.add(ge23 = new JRadioButton() );
		buttonList.add(ge24 = new JRadioButton() );
		buttonList.add(ge25 = new JRadioButton() );
		buttonList.add(ge26 = new JRadioButton() );
	}

	public void initComponent(){
		titlePanel = new JPanel();
		lblTitle = new JLabel();
		outerClockLabel = new JRadioButton(ResourceUtil.srcStr(StringKeysObj.OUTER_CLOCK));
		for (int i = 0; i < portList.size(); i++) {
			buttonList.get(i).setText(portList.get(i).getPortName());
		}
		clear = new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CLEAR));
		buttonGroup = new ButtonGroup();
		buttonGroup.add(outerClockLabel);
		for (int i = 0; i < portList.size(); i++) {
			buttonGroup.add(buttonList.get(i));
		}
		buttonGroup.add(clear);
		contentPanel = new JPanel();
		contentPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysTitle.TIT_EXTERNAL_ORDER)));
		scrollPanel = new JScrollPane();
		scrollPanel.setViewportView(contentPanel);
		scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		buttonPanel = new JPanel();
		cancleButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		saveButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE));
	}
	
	public void setLayout(){
		// title面板布局
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		layout.rowHeights = new int[] { 10 };
		layout.rowWeights = new double[] { 0 };
		layout.columnWidths = new int[] { 30, ConstantUtil.INT_WIDTH_THREE - 30 };
		layout.columnWeights = new double[] { 0, 1.0 };
		titlePanel.setLayout(layout);
		addComponent(titlePanel, lblTitle, 0, 0, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 20, 5, 5), GridBagConstraints.CENTER, c);
		// 主面板布局
		layout = new GridBagLayout();
		layout.rowHeights = new int[] { 60, 300, 60 };
		layout.rowWeights = new double[] { 0, 0.7, 0 };
		layout.columnWidths = new int[] { ConstantUtil.INT_WIDTH_THREE };
		layout.columnWeights = new double[] { 1 };
		this.setLayout(layout);
		addComponentJDialog(this, titlePanel, 0, 0, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponentJDialog(this, scrollPanel, 0, 1, 0, 0.2, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponentJDialog(this, buttonPanel, 0, 2, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);

		// content面板布局
		setClockRorateButton();
		// button面板布局
		setButtonLayout();
	}
	
	/**
	 * 保存，取消按钮布局
	 */
	private void setButtonLayout() {
		GridBagLayout buttonLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		buttonLayout.columnWidths = new int[] { 60, 60, 60, 6 };
		buttonLayout.columnWeights = new double[] { 1.0, 0, 0, 0 };
		buttonLayout.rowHeights = new int[] { 60 };
		buttonLayout.rowWeights = new double[] { 1 };
		buttonPanel.setLayout(buttonLayout);
		addComponent(buttonPanel, cancleButton, 1, 0, 0, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 5, 5, 20), GridBagConstraints.WEST, c);
		addComponent(buttonPanel, saveButton, 2, 0, 0, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 5, 5, 20), GridBagConstraints.WEST, c);
	}
	
	public void setClockRorateButton(){
		GridBagLayout contentLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPanel.setLayout(contentLayout);
		Insets insert = new Insets(5, 50, 5, 5);
		addComponent(contentPanel, outerClockLabel, 0, 0, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, clear, 1, 0, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		for (int i = 0; i < portList.size(); i++) {
			if(i%2 == 1){
				this.addComponent(contentPanel, buttonList.get(i), 1, (i+1)/2, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
			}else{
				this.addComponent(contentPanel, buttonList.get(i), 0, (i+2)/2, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
			}
		}
	}
	
	public void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
	}
	
	public void addComponentJDialog(JDialog panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.fill = fill;
		gridBagConstraints.insets = insets;
		gridBagConstraints.anchor = anchor;
		panel.add(component, gridBagConstraints);
		
	}
	
	public void addListener(){
		this.cancleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clockRorateDialog.dispose();
			}
		});
		this.saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(info == null){
					info = new FrequencyInfo();
					info.setSiteId(ConstantUtil.siteId);
				}
				if(outerClockLabel.isSelected()){
					info.setClockRarate(0);				
				}
				if(clear.isSelected()){
					info.setClockRarate(30);										
				}else{
					for (int i = 0; i < portList.size(); i++) {
						if(buttonList.get(i).isSelected()){
							info.setClockRarate(portList.get(i).getNumber());
						}
					}
				}
				DispatchUtil clockFrequDispatch;
				try {
					clockFrequDispatch = new DispatchUtil(RmiKeys.RMI_CLOCKFREQU);
					SiteRoate site=new SiteRoate();
					CommonBean newC=new CommonBean(); 
				    if(info.getClockRarate()==30){				    	
				    	newC.setPortName(ResourceUtil.srcStr(StringKeysBtn.BTN_CLEAR));
				    }else{
				    	if(info.getClockRarate()==0){
				    		newC.setPortName(ResourceUtil.srcStr(StringKeysObj.OUTER_CLOCK));
				    	}else{
				    	    newC.setPortName(selectProt(info.getClockRarate(),info.getSiteId()));
				    	}
				    }
				    CommonBean oldC=null; 
				    if(siteR!=null){
				    	oldC=new CommonBean(); 
					    if(siteR.getTypeId()==2){				    	
					    	oldC.setPortName(ResourceUtil.srcStr(StringKeysBtn.BTN_CLEAR));
					    }else{
					    	if(siteR.getSiteRoate()==0){
					    		oldC.setPortName(ResourceUtil.srcStr(StringKeysObj.OUTER_CLOCK));
					    	}else{
					    		oldC.setPortName(selectProt(siteR.getSiteRoate(),siteR.getSiteId()));
					    	}				    	
					    }
				    }
					String result = clockFrequDispatch.clcokRorate(info);
					DialogBoxUtil.succeedDialog(clockRorateDialog, result);
					this.insertOpeLog(EOperationLogType.CLOCKSET.getValue(), result, oldC, newC);			
					clockRorateDialog.dispose();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
			
			
			private void insertOpeLog(int operationType, String result, CommonBean oldClock, CommonBean newClock){
				SiteService_MB service = null;
				try {
					service = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
					String siteName=service.getSiteName(ConstantUtil.siteId);					
				    AddOperateLog.insertOperLog(saveButton, operationType, result, oldClock, newClock, ConstantUtil.siteId,siteName,"ClockRoate");		
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				} finally {
					UiUtil.closeService_MB(service);
				}
			}
			
		});
	}
	
	
	 private String selectProt(int number,int siteId) {
			PortService_MB portService = null;
			PortInst portInst = null;
			List<PortInst> allportInstList = null;
			String portName = null;
			try {
				allportInstList=new ArrayList<PortInst>();
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				portInst = new PortInst();
				portInst.setSiteId(siteId);
				portInst.setNumber(number);
				allportInstList = portService.select(portInst);
				if(allportInstList!=null&&allportInstList.size()>0){
					 portName = allportInstList.get(0).getPortName();
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally {
				UiUtil.closeService_MB(portService);
				portInst = null;
				allportInstList = null;
			}
			return portName;
		}
	
	private void initValues(SiteRoate siteRoate) {
//		if(siteRoate.getTypeId() == 1 && siteRoate.getSiteRoate()==0){
//			this.outerClockLabel.setSelected(true);
//		}
		if(siteRoate.getTypeId() == 2){
			this.clear.setSelected(true);
		}else{
			if(siteRoate.getSiteRoate()==0){
				this.outerClockLabel.setSelected(true);
			}else{
			   int number = 0;
			   for (int i = 0; i < portList.size(); i++) {
				    number = portList.get(i).getNumber();
				    if(siteRoate.getSiteRoate() == number){
					   buttonList.get(i).setSelected(true);
				    }
			   }
		    }
		}
   }
}
