package com.nms.ui.ptn.ne.statusData.view.ethLinkOamStatus.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.oamStatus.OamLinkStatusInfo;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.ptn.ne.statusData.view.ethLinkOamStatus.Controller.EthLinkOamStatusController;

/**
 *function:接入链路以太网oam状态查询 
 * @author zk
 */
public class EthLinkOamStatusPanel extends PtnPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7350188062681560498L;
	
	private JLabel oamPortLabel ; //本端OAM端口
	private JComboBox oamPortComboBox ;
	private JLabel oamConsultLabel ; //本端OAM协商态
	private JComboBox oamConsultComboBox ;
	private JLabel oamLinkStateLabel ; //本端OAM链路状态
	private JComboBox oamLinkStateComboBox ;
	private JLabel oamPortFaultStateLabel ; //本端OAM端口fault状态
	private JComboBox oamPortFaultStateComboBox ;
	private JLabel loopFailLabel ; //本端环回命令超时失败
	private JComboBox loopFailComboBox ;
	private JLabel loopAnalysisLabel ; //本端环回解析状态
	private JComboBox loopAnalysisComboBox ;
	private JLabel remoteLoopAnalysisLabel ; //远端环回解析状态
	private JComboBox remoteLoopAnalysisComboBox ;
	private PtnButton findButton ;
    private JPanel centerJPanel	;
    private JPanel buttonJpanel ; 
//	private JLabel id;
//	private JTextField idTextField ; 
	private EthLinkOamStatusController ethLinkOamStatusController ;
	
	public EthLinkOamStatusPanel(){
		init();
//		addListention();
		setsetLayout();
	}


	private void init() {
		
		try {
			
			this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysObj.ETH_OAM_STATUS_OAMLINKSTATUS)));
			oamPortLabel = new JLabel(ResourceUtil.srcStr(StringKeysObj.ETH_LOOPPORT));
			oamPortComboBox = new JComboBox();
//			oamPortComboBox.setEnabled(false);
			comboBoxData(oamPortComboBox);
			oamConsultLabel = new JLabel(ResourceUtil.srcStr(StringKeysObj.ETH_OAM_STATUS_CONSULT));
			oamConsultComboBox = new JComboBox();
			oamConsultComboBox.setEnabled(false);
			super.getComboBoxDataUtil().comboBoxData(oamConsultComboBox, "OAMNegotiatedState");
			oamLinkStateLabel = new JLabel(ResourceUtil.srcStr(StringKeysObj.ETH_OAM_STATUS_LINKSTATE));
			oamLinkStateComboBox = new JComboBox();
			oamLinkStateComboBox.setEnabled(false);
			super.getComboBoxDataUtil().comboBoxData(oamLinkStateComboBox, "OAMlinkStatus");
			oamPortFaultStateLabel = new JLabel(ResourceUtil.srcStr(StringKeysObj.ETH_OAM_STATUS_FAULT));
			oamPortFaultStateComboBox = new JComboBox();
			oamPortFaultStateComboBox.setEnabled(false);
			super.getComboBoxDataUtil().comboBoxData(oamPortFaultStateComboBox, "OAMPORTFAULTSTATUS");
			loopFailLabel = new JLabel(ResourceUtil.srcStr(StringKeysObj.ETH_OAM_STATUS_FAIL));
			loopFailComboBox = new JComboBox();
			loopFailComboBox.setEnabled(false);
			super.getComboBoxDataUtil().comboBoxData(loopFailComboBox, "OAMEnabled");
			loopAnalysisLabel = new JLabel(ResourceUtil.srcStr(StringKeysObj.ETH_OAM_STATUS_LOOP));
			loopAnalysisComboBox = new JComboBox();
			loopAnalysisComboBox.setEnabled(false);
			super.getComboBoxDataUtil().comboBoxData(loopAnalysisComboBox, "localAnaysisLoopStatus");
			remoteLoopAnalysisLabel = new JLabel(ResourceUtil.srcStr(StringKeysObj.ETH_OAM_STATUS_LOOP2));
			remoteLoopAnalysisComboBox = new JComboBox();
			remoteLoopAnalysisComboBox.setEnabled(false);
			super.getComboBoxDataUtil().comboBoxData(remoteLoopAnalysisComboBox, "localAnaysisLoopStatus");
			findButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SELECT),true);
			ethLinkOamStatusController = new EthLinkOamStatusController(this);
//			id = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NMUBER_ID));
//			idTextField = new JTextField();
//			idTextField.setText(1+"");
			
			centerJPanel = new JPanel();
			buttonJpanel = new JPanel();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	private void setsetLayout() {
		//主面板
		GridBagConstraints	gridBagConstraints = new GridBagConstraints();
		GridBagLayout	gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[]{100,60,100};
		gridBagLayout.rowWeights = new double[]{0,0.1};
		gridBagLayout.columnWidths = new int[]{300};
		gridBagLayout.columnWeights = new double[]{1};
		this.setLayout(gridBagLayout);
		addComponent(this, centerJPanel, 0, 1, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5,5,5,5), GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(this, buttonJpanel, 0, 2, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5,5,5,5), GridBagConstraints.CENTER, gridBagConstraints);
       //centerJpanel 面板布局
		gridBagLayout = new GridBagLayout();
		centerJPanel.setLayout(gridBagLayout);
		centerJPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysObj.ETH_OAM_STATUS_OAMLINKSTATUS)));
		Insets insert = new Insets(0, 50, 10, 20); 
		addComponent(centerJPanel, oamPortLabel, 0, 0, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(centerJPanel, oamPortComboBox, 1, 0, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(centerJPanel, oamConsultLabel, 2, 0,0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(centerJPanel, oamConsultComboBox, 3, 0, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(centerJPanel, oamLinkStateLabel, 0, 1, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(centerJPanel, oamLinkStateComboBox, 1, 1, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(centerJPanel, oamPortFaultStateLabel, 2, 1, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(centerJPanel, oamPortFaultStateComboBox, 3, 1, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(centerJPanel, loopFailLabel, 0, 2, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(centerJPanel, loopFailComboBox, 1, 2, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(centerJPanel, loopAnalysisLabel, 2, 2, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(centerJPanel, loopAnalysisComboBox, 3, 2, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
//		addComponent(centerJPanel, id, 0, 3, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
//		addComponent(centerJPanel, idTextField, 1, 3, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
//		addComponent(centerJPanel, remoteLoopAnalysisLabel, 2, 3, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
//		addComponent(centerJPanel, remoteLoopAnalysisComboBox, 3, 3, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(centerJPanel, remoteLoopAnalysisLabel, 0, 3, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
		addComponent(centerJPanel, remoteLoopAnalysisComboBox, 1, 3, 0.1, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, gridBagConstraints);
		// 按钮布局
		GridBagLayout buttonLayout = new GridBagLayout();
		buttonLayout.columnWidths = new int[]{60,60,60,6};
		buttonLayout.columnWeights = new double[]{1.0,0,0,0};
		buttonLayout.rowHeights = new int[]{60};
		buttonLayout.rowWeights = new double[]{1};
		buttonJpanel.setLayout(buttonLayout);
		addComponent(buttonJpanel, findButton, 2, 0, 0, 0, 1, 1, GridBagConstraints.WEST, new Insets(5,5,5,20), GridBagConstraints.WEST, gridBagConstraints);
		
}

	// 为端口 下来列表赋值
	private void comboBoxData(JComboBox jComboBox) throws Exception {
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
			throw e;
		} finally {
			defaultComboBoxModel = null;
			UiUtil.closeService_MB(portService);
			portInst = null;
			allportInstList = null;
		}
	}

	public void refresh( OamLinkStatusInfo OamLinkStatusInfoList )throws Exception{
		
		try {
//			if(OamLinkStatusInfoList != null){
//				本端OAM端口                 
				this.comboBoxSelectByValue(oamPortComboBox, OamLinkStatusInfoList.getPortNumber()+"");
				//本端OAM协商态            
				super.getComboBoxDataUtil().comboBoxSelectByValue(oamConsultComboBox, OamLinkStatusInfoList.getOamConsult()+"");
				//本端OAM链路状态      
				super.getComboBoxDataUtil().comboBoxSelectByValue(oamLinkStateComboBox, OamLinkStatusInfoList.getOamLinkStatus()+"");
				//本端OAM端口fault状态  
				super.getComboBoxDataUtil().comboBoxSelectByValue(oamPortFaultStateComboBox, OamLinkStatusInfoList.getOamPortFaultStatus()+"");
				//本端环回命令超时失败  
				super.getComboBoxDataUtil().comboBoxSelectByValue(loopFailComboBox, OamLinkStatusInfoList.getLoopFailfalse()+"");
				//本端环回解析状态   
				super.getComboBoxDataUtil().comboBoxSelectByValue(loopAnalysisComboBox, OamLinkStatusInfoList.getLoopAnalysisStatus()+"");
				//远端环回解析状态
				super.getComboBoxDataUtil().comboBoxSelectByValue(remoteLoopAnalysisComboBox, OamLinkStatusInfoList.getRemoteLoopAnalysisStatus()+"");
//			}
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	/**
	 * 下拉列表选中
	 * 
	 * @param jComboBox
	 *            下拉列表对象
	 * @param values
	 *            port的number
	 */
	private  void comboBoxSelectByValue(JComboBox jComboBox, String values) {
		ControlKeyValue controlKeyValue = null;
		String value = null;
		for (int i = 0; i < jComboBox.getItemCount(); i++) {
			controlKeyValue = (ControlKeyValue) jComboBox.getItemAt(i);
			value = ((PortInst) controlKeyValue.getObject()).getNumber()+"";
			if (value.equals(values)) {
				jComboBox.setSelectedIndex(i);
				return;
			}
		}
	}
	
	
//	private void addListention() {
//		this.idTextField.addFocusListener(new FocusListener() {
//			
//			@Override
//			public void focusLost(FocusEvent e) {
//					/* 区间是否为1-6 */ 
//				String errorMess = ResourceUtil.srcStr(StringKeysLbl.LBL_PARMATE_LABEL);//条目数id在1到6之间
//				if(!idTextField.getText().trim().equals("")&& idTextField.getText().length()<8){
//					int inputNumber = Integer.parseInt(idTextField.getText());
//					if (inputNumber < 1 || inputNumber > 26) {
//						JOptionPane.showMessageDialog(null, errorMess);
//						idTextField.setText("1");
//					}
//				}else{
//					JOptionPane.showMessageDialog(null, errorMess);
//					idTextField.setText("1");
//				}
//			}
//			@Override
//			public void focusGained(FocusEvent e) {
//			}
//		});
//		
//	}
	
	public PtnButton getFindButton() {
		return findButton;
	}

	public void setFindButton(PtnButton findButton) {
		this.findButton = findButton;
	}

//	public JTextField getIdTextField() {
//		return idTextField;
//	}
//
//	public void setIdTextField(JTextField idTextField) {
//		this.idTextField = idTextField;
//	}
	public JComboBox getOamPortComboBox() {
	    return oamPortComboBox;
    }

    public void setOamPortComboBox(JComboBox oamPortComboBox) {
	    this.oamPortComboBox = oamPortComboBox;
    }
}
