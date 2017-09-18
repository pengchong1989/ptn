package com.nms.ui.ptn.ne.statusData.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.oamStatus.OamStatusInfo;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * 端口MAC地址学习数目
 * @author Administrator
 *
 */
 public class PortMacStudy extends JPanel{

	 private JLabel portLabel;//端口号
	 private JComboBox portBox;
	 private JLabel macCountLable;//mac学习数目
	 private JTextField portMaccount;//端口mac学习数目
	 private JButton findButton ;
	 private JPanel buttonJpan;//按钮布局
	 
	 public PortMacStudy(){
		 init();
		 setsetLayout();
		 addActionListner();
	 }

	private void init() {
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysObj.PORT_STUDYCOUNT)));
		portLabel = new JLabel(ResourceUtil.srcStr(StringKeysObj.ETH_LOOPPORT));
		portBox = new JComboBox();
		comboBoxData(portBox);
		macCountLable = new JLabel(ResourceUtil.srcStr(StringKeysObj.STUDYMACCOUNT));
		buttonJpan = new JPanel();
		portMaccount = new JTextField();
		portMaccount.setEditable(false);
		findButton = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SELECT));
	}
	
	/**
	 * 设置布局
	 */
	public void setsetLayout() {
		setButtonLayout();
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] {10,150,20,150};
		layout.columnWeights = new double[] { 0, 0, 0, 0,0.4 };
		layout.rowHeights = new int[] {100};
		layout.rowWeights = new double[] { 0 };
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		this.setLayout(layout);
		// 操作菜单按钮布局
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(portLabel, c);
		this.add(portLabel);
		
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(0, 5, 0, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(portBox, c);
		this.add(portBox); 
		
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(macCountLable, c);
		this.add(macCountLable);
		
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(0, 5, 0, 5);
		c.fill = GridBagConstraints.HORIZONTAL;
		layout.setConstraints(portMaccount, c);
		this.add(portMaccount); 
		
		c.fill = GridBagConstraints.NONE;
		c.gridx = 3;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 50, 0, 0);
		layout.setConstraints(buttonJpan, c);
		this.add(buttonJpan); 
	}
	
	private void setButtonLayout() {
		
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] {20,20};
		layout.columnWeights = new double[] { 0, 0};
		layout.rowHeights = new int[] {15};
		layout.rowWeights = new double[] { 0 };
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		buttonJpan.setLayout(layout);
		
		// 操作菜单按钮布局
		c.fill = GridBagConstraints.EAST;
		c.gridx = 4;
		c.gridy = 0;
		c.gridheight = 1;
		c.insets = new Insets(5, 5, 5, 0);
		layout.setConstraints(findButton, c);
		buttonJpan.add(findButton);
		
	}

	// 为端口 下来列表赋值
	private void comboBoxData(JComboBox jComboBox){
		DefaultComboBoxModel defaultComboBoxModel = null;
		PortService_MB portService = null;
		PortInst portInst = null;
		List<PortInst> allportInstList = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInst = new PortInst();
			portInst.setSiteId(ConstantUtil.siteId);
			portInst.setPortType(ConstantUtil.portType);
			allportInstList = portService.select(portInst);
			defaultComboBoxModel = (DefaultComboBoxModel) jComboBox.getModel();
				for (PortInst portInsts : allportInstList) {
					if (portInsts.getPortType().equalsIgnoreCase("nni") || portInsts.getPortType().equalsIgnoreCase("uni")|| portInsts.getPortType().equalsIgnoreCase("NONE")) {
						defaultComboBoxModel.addElement(new ControlKeyValue(portInsts.getID() + "", portInsts.getPortName(), portInsts));
					}
				}
			jComboBox.setModel(defaultComboBoxModel);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			defaultComboBoxModel = null;
			UiUtil.closeService_MB(portService);
			portInst = null;
			allportInstList = null;
		}
	}

	private void addActionListner() {
//		 MacStudyBean
		findButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				confirm();
			}
		});
	}

	/**
	 * 保存
	 */
	private void confirm() {
		
		SiteInst siteInfo = null;
		DispatchUtil siteDispatch = null;
		SiteService_MB siteServie = null;
		try {
			ControlKeyValue portKeyValue = (ControlKeyValue) portBox.getSelectedItem();
			if(portKeyValue != null){
				siteServie = (SiteService_MB)ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
				siteInfo = siteServie.select(ConstantUtil.siteId);
				siteInfo.setStatusMark(64);
				siteInfo.setParameter(((PortInst)portKeyValue.getObject()).getNumber());
				OamStatusInfo oamStatusInfo	= siteDispatch.oamStatus(siteInfo);
				if(oamStatusInfo !=null && oamStatusInfo.getMacStudyBean()!= null){
					DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
					portMaccount.setText(oamStatusInfo.getMacStudyBean().getVlanId()+"");
				}else{
					DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_FIND_DETAIL_FAIL));
				}
//				if(oamStatusInfo.getMacStudyBean()!= null){
//					portMaccount.setText(oamStatusInfo.getMacStudyBean().getVlanId()+"");
//				}else{
//					portMaccount.setText(0+"");
//				}
			}else{
				DialogBoxUtil.succeedDialog(null,"请先配置板卡");
				return ;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,PortMacStudy.class);
		} finally{
		     siteInfo = null;
		     siteDispatch = null;
		     UiUtil.closeService_MB(siteServie);
		}
	}
}
