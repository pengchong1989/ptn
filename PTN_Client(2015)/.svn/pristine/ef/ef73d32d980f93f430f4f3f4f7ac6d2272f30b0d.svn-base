package com.nms.ui.ptn.ne.statusData.view.ethLinkOam;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.oamStatus.InsertLinkOamInfo;
import com.nms.db.bean.ptn.oamStatus.OamStatusInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class EthLinkOamPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//误码事件
	private JLabel errorYardTime;
	private JLabel errorYardCycle;
	private JLabel errorYardValue;
	private JLabel errorYardNumber;
	private JLabel errorYardTotal;
	private JLabel errorYardEvent;
	private JTextField errorYardTimeText;
	private JTextField errorYardCycleText;
	private JTextField errorYardValueText;
	private JTextField errorYardNumberText;
	private JTextField errorYardTotalText;
	private JTextField errorYardEventText;
	
	//错误帧周期事件
	private JLabel errorFrameCycleTime;
	private JLabel errorFrameCycleCycle;
	private JLabel errorFrameCyclevalue;
	private JLabel errorFrameCycleNumber;
	private JLabel errorFrameCycleTotal;
	private JLabel errorFrameCycleEvent;
	private JTextField errorFrameCycleTimeText;
	private JTextField errorFrameCycleCycleText;
	private JTextField errorFrameCyclevalueText;
	private JTextField errorFrameCycleNumberText;
	private JTextField errorFrameCycleTotalText;
	private JTextField errorFrameCycleEventText;
	
	//错误帧事件
	private JLabel errorFrameTime;
	private JLabel errorFrameCycle;
	private JLabel errorFramevalue;
	private JLabel errorFrameNumber;
	private JLabel errorFrameTotal;
	private JLabel errorFrameEvent;
	private JTextField errorFrameTimeText;
	private JTextField errorFrameCycleText;
	private JTextField errorFramevalueText;
	private JTextField errorFrameNumberText;
	private JTextField errorFrameTotalText;
	private JTextField errorFrameEventText;
	
	//错误秒帧事件
	private JLabel errorSecondFrameTime;
	private JLabel errorSecondFrameCycle;
	private JLabel errorSecondFramevalue;
	private JLabel errorSecondFrameNumber;
	private JLabel errorSecondFrameTotal;
	private JLabel errorSecondFrameEvent;
	private JTextField errorSecondFrameTimeText;
	private JTextField errorSecondFrameCycleText;
	private JTextField errorSecondFramevalueText;
	private JTextField errorSecondFrameNumberText;
	private JTextField errorSecondFrameTotalText;
	private JTextField errorSecondFrameEventText;
	
	private PtnButton okButton;
	private JPanel buttonPanel;
	private JLabel lblTitle;
	private JPanel titlePanel;
	private JScrollPane scrollPanel;
	private JPanel contentPanel;
	private EthLinkOamPanel ethLinkOamPanel;
	private JLabel portJLabel;
//	private JTextField idTextField;
	private JComboBox portLabelComboBox;
	/**
	 * 创建一个新的实例
	 */
	public EthLinkOamPanel() {
		ethLinkOamPanel = this;
		init();
		addActionListner();
	}

	/**
	 * 初始化
	 */
	private void init() {
		try {
			initComponents();
			setLayout();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	/**
	 * 初始化控件
	 */
	private void initComponents()  throws Exception {

		lblTitle = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ETH_OAM_QUERY));
		titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createEtchedBorder());
		titlePanel.setSize(60, ConstantUtil.INT_WIDTH_THREE);
		scrollPanel = new JScrollPane();
		contentPanel = new JPanel();
		scrollPanel.setViewportView(contentPanel);
		scrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		okButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SELECT),true,RootFactory.CORE_MANAGE);
		buttonPanel = new JPanel();
		
		//误码事件
		errorYardTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERRORYARDTIME));
		errorYardCycle = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERRORYARDCYCLE));
		errorYardValue = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERRORYARDVALUE));
		errorYardNumber = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERRORNUMBER));
		errorYardTotal = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERRORTOTALNUMBER));
		errorYardEvent = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERRORYARDEVENT));
		errorYardTimeText = new JTextField();
		errorYardTimeText.setEditable(false);
		errorYardCycleText = new JTextField();
		errorYardCycleText.setEditable(false);
		errorYardValueText = new JTextField();
		errorYardValueText.setEditable(false);
		errorYardNumberText = new JTextField();
		errorYardNumberText.setEditable(false);
		errorYardTotalText = new JTextField();
		errorYardTotalText.setEditable(false);
		errorYardEventText = new JTextField();
		errorYardEventText.setEditable(false);
		
		//错误帧周期事件
		errorFrameCycleTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERROR_FRAME_TIME));
		errorFrameCycleCycle = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERROR_FRAME_CYCLE));
		errorFrameCyclevalue = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERROR_FRAME_VALUE));
		errorFrameCycleNumber = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERRORNUMBER));
		errorFrameCycleTotal = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERRORTOTALNUMBER));
		errorFrameCycleEvent = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERROR_FRAME_EVENT));
		errorFrameCycleTimeText = new JTextField();
		errorFrameCycleTimeText.setEditable(false);
		errorFrameCycleCycleText = new JTextField();
		errorFrameCycleCycleText.setEditable(false);
		errorFrameCyclevalueText = new JTextField();
		errorFrameCyclevalueText.setEditable(false);
		errorFrameCycleNumberText = new JTextField();
		errorFrameCycleNumberText.setEditable(false);
		errorFrameCycleTotalText = new JTextField();
		errorFrameCycleTotalText.setEditable(false);
		errorFrameCycleEventText = new JTextField();
		errorFrameCycleEventText.setEditable(false);
		
		//错误帧事件
		errorFrameTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERROR_FRAME_EVENT_TIME));
		errorFrameCycle = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERROR_FRAME_EVENT_CYCLE));
		errorFramevalue = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERROR_FRAME_EVENT_VALUE));
		errorFrameNumber = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERRORNUMBER));
		errorFrameTotal = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERRORTOTALNUMBER));
		errorFrameEvent = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERROR_FRAME_EVENT_NUMBER));
		errorFrameTimeText = new JTextField();
		errorFrameTimeText.setEditable(false);
		errorFrameCycleText = new JTextField();
		errorFrameCycleText.setEditable(false);
		errorFramevalueText = new JTextField();
		errorFramevalueText.setEditable(false);
		errorFrameNumberText = new JTextField();
		errorFrameNumberText.setEditable(false);
		errorFrameTotalText = new JTextField();
		errorFrameTotalText.setEditable(false);
		errorFrameEventText = new JTextField();
		errorFrameEventText.setEditable(false);
		
		//错误秒帧事件
		errorSecondFrameTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERROR_FRAME_SECOND_TIME));
		errorSecondFrameCycle = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERROR_FRAME_SECOND_CYCLE));
		errorSecondFramevalue = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERROR_FRAME_SECOND_VALUE));
		errorSecondFrameNumber = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERRORNUMBER));
		errorSecondFrameTotal = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERRORTOTALNUMBER));
		errorSecondFrameEvent = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ERROR_FRAME_SECOND_NUMBER));
		errorSecondFrameTimeText = new JTextField();
		errorSecondFrameTimeText.setEditable(false);
		errorSecondFrameCycleText = new JTextField();
		errorSecondFrameCycleText.setEditable(false);
		errorSecondFramevalueText = new JTextField();
		errorSecondFramevalueText.setEditable(false);
		errorSecondFrameNumberText = new JTextField();
		errorSecondFrameNumberText.setEditable(false);
		errorSecondFrameTotalText = new JTextField();
		errorSecondFrameTotalText.setEditable(false);
		errorSecondFrameEventText = new JTextField();
		errorSecondFrameEventText.setEditable(false);
		
		// 端口号
		portJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_NUM));// 端口号
		portLabelComboBox = new JComboBox();
		comboBoxData(portLabelComboBox, 1);
//		this.comboBoxSelect(this.portLabelComboBox, this.oamInfo.getOamEthernetInfo().getPort() + "");
//		idTextField = new JTextField();
//		idTextField.setText(1+"");
	}

	// 为 下来列表赋值
		private void comboBoxData(JComboBox jComboBox, int lebel) throws Exception {
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
				// defaultComboBoxModel.addElement(new ControlKeyValue("", "", new
				// PortInst()));
				if (lebel == 1) {
					for (PortInst portInsts : allportInstList) {
						if ((portInsts.getPortType().equalsIgnoreCase("nni") || portInsts.getPortType().equalsIgnoreCase("uni")
								|| portInsts.getPortType().equalsIgnoreCase("NONE") ) && portInsts.getNumber()>0) {
							defaultComboBoxModel.addElement(new ControlKeyValue(portInsts.getID() + "", portInsts.getPortName(), portInsts));
						}
					}
				} else {
					for (PortInst portInsts : allportInstList) {
						if (portInsts.getPortType().equalsIgnoreCase("nni") || portInsts.getPortType().equalsIgnoreCase("uni")
								|| portInsts.getPortType().equalsIgnoreCase("NONE")) {
							defaultComboBoxModel.addElement(new ControlKeyValue(portInsts.getNumber() + "", portInsts.getPortName(), portInsts));
						}
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
	
	
	public void setModel(JComboBox cbBox, Map<Integer, String> keyValuse) {
		DefaultComboBoxModel model = (DefaultComboBoxModel) cbBox.getModel();
		for (Integer key : keyValuse.keySet()) {
			model.addElement(new ControlKeyValue(key.toString(), keyValuse.get(key)));
		}
	}

	/**
	 * 布局管理
	 */
	private void setLayout() {
		// title面板布局
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		// 主面板布局
		layout = new GridBagLayout();
		layout.rowHeights = new int[] { 30, 330, 30 };
		layout.rowWeights = new double[] { 0, 0.7, 0 };
		layout.columnWidths = new int[] { ConstantUtil.INT_WIDTH_TWO };
		layout.columnWeights = new double[] { 1 };
		this.setLayout(layout);
		addComponent(this, scrollPanel, 0, 1, 0, 0.2, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);
		addComponent(this, buttonPanel, 0, 2, 0, 0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER, c);

		// content面板布局
		GridBagLayout contentLayout = new GridBagLayout();
		contentPanel.setLayout(contentLayout);
		contentPanel.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_ETH_OAM_QUERY)));
		Insets insert = new Insets(5, 20, 5, 5);
		
		addComponent(contentPanel, errorYardTime, 0, 0, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorYardTimeText, 1, 0, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorYardCycle, 2, 0, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorYardCycleText, 3, 0, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorYardValue, 0, 1, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorYardValueText, 1, 1, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorYardNumber, 2, 1, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorYardNumberText, 3, 1, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorYardTotal, 0, 2, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorYardTotalText, 1, 2, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorYardEvent, 2, 2, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorYardEventText, 3, 2, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		
		addComponent(contentPanel, errorFrameCycleTime, 0, 3, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameCycleTimeText, 1, 3, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameCycleCycle, 2, 3, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameCycleCycleText, 3, 3, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameCyclevalue, 0, 4, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameCyclevalueText, 1, 4, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameCycleNumber, 2, 4, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameCycleNumberText, 3, 4, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameCycleTotal, 0, 5, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameCycleTotalText, 1, 5, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameCycleEvent, 2, 5, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameCycleEventText, 3, 5, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		
		addComponent(contentPanel, errorFrameTime, 0, 6, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameTimeText, 1, 6, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameCycle, 2, 6, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameCycleText, 3, 6, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFramevalue, 0, 7, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFramevalueText, 1, 7, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameNumber, 2, 7, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameNumberText, 3, 7, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameTotal, 0, 8, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameTotalText, 1, 8, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameEvent, 2, 8, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorFrameEventText, 3, 8, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		
		addComponent(contentPanel, errorSecondFrameTime, 0, 9, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorSecondFrameTimeText, 1, 9, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorSecondFrameCycle, 2, 9, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorSecondFrameCycleText, 3, 9, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorSecondFramevalue, 0, 10, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorSecondFramevalueText, 1, 10, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorSecondFrameNumber, 2, 10, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorSecondFrameNumberText, 3, 10, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorSecondFrameTotal, 0, 11, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorSecondFrameTotalText, 1, 11, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorSecondFrameEvent, 2, 11, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, errorSecondFrameEventText, 3, 11, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		
		addComponent(contentPanel, portJLabel, 0, 12, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		addComponent(contentPanel, portLabelComboBox, 1, 12, 0.2, 0, 1, 1, GridBagConstraints.BOTH, insert, GridBagConstraints.CENTER, c);
		// button面板布局
		GridBagLayout buttonLayout = new GridBagLayout();
		buttonLayout.columnWidths = new int[] { 60, 60, 60, 6 };
		buttonLayout.columnWeights = new double[] { 1.0, 0, 0, 0 };
		buttonLayout.rowHeights = new int[] { 60 };
		buttonLayout.rowWeights = new double[] { 1 };
		buttonPanel.setLayout(buttonLayout);
		addComponent(buttonPanel, okButton, 2, 0, 0, 0, 1, 1, GridBagConstraints.WEST, new Insets(5, 5, 5, 20), GridBagConstraints.WEST, c);
	}

	/**
	 * 布局管理器
	 * @param panel
	 * @param component
	 * @param gridx
	 * @param gridy
	 * @param weightx
	 * @param weighty
	 * @param gridwidth
	 * @param gridheight
	 * @param fill
	 * @param insets
	 * @param anchor
	 * @param gridBagConstraints
	 */
	private void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth, int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
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

	/**
	 * 初始化界面
	 * 
	 * @param info
	 * @throws Exception
	 */
	private void refresh(InsertLinkOamInfo insertLinkOamInfo) throws Exception {
		
			errorYardTimeText.setText(insertLinkOamInfo.getErrorEventTime());
			errorYardCycleText.setText(insertLinkOamInfo.getErrorEventMonitorCycle());
			errorYardValueText.setText(insertLinkOamInfo.getErrorEventThreshold()+"");
			errorYardNumberText.setText(insertLinkOamInfo.getErrorSize()+"");
			errorYardTotalText.setText(insertLinkOamInfo.getErrorECountSize()+"");
			errorYardEventText.setText(insertLinkOamInfo.getErrorEventCountSize()+"");
			
			errorFrameCycleTimeText.setText(insertLinkOamInfo.getErrorFrameCycleTime());
			errorFrameCycleCycleText.setText(insertLinkOamInfo.getErrorFrameMonitorCycle());
			errorFrameCyclevalueText.setText(insertLinkOamInfo.getErrorFrameCycleThreshold()+"");
			errorFrameCycleNumberText.setText(insertLinkOamInfo.getErrorFrameSize()+"");
			errorFrameCycleTotalText.setText(insertLinkOamInfo.getErrorFCountSize()+"");
			errorFrameCycleEventText.setText(insertLinkOamInfo.getErrorFrameCycleCountSize()+"");
			
			errorFrameTimeText.setText(insertLinkOamInfo.getErrorFrameEventTime());
			errorFrameCycleText.setText(insertLinkOamInfo.getErrorFrameEventMonitorCycle());
			errorFramevalueText.setText(insertLinkOamInfo.getErrorFrameEventThreshold()+"");
			errorFrameNumberText.setText(insertLinkOamInfo.getErrorFrameEventSize()+"");
			errorFrameTotalText.setText(insertLinkOamInfo.getErrorFrameCountSize()+"");
			errorFrameEventText.setText(insertLinkOamInfo.getErrorFrameEventCountSize()+"");
			
			errorSecondFrameTimeText.setText(insertLinkOamInfo.getErrorSecondsEventTime());
			errorSecondFrameCycleText.setText(insertLinkOamInfo.getErrorSecondsEventMonitorCycle());
			errorSecondFramevalueText.setText(insertLinkOamInfo.getErrorSecondsEvenThreshold()+"");
			errorSecondFrameNumberText.setText(insertLinkOamInfo.getErrorSecondsEvenSize()+"");
			errorSecondFrameTotalText.setText(insertLinkOamInfo.getErrorSecondsEventCountSize()+"");
			errorSecondFrameEventText.setText(insertLinkOamInfo.getErrorSecondsEvenFrameCountSize()+"");
		
	}

	/**
	 * 查询按钮监听事件
	 */
	private void addActionListner(){
		okButton.addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {

				SiteService_MB siteService = null;
				SiteInst siteInst = null;
				DispatchUtil siteDispatch = null;
				OamStatusInfo result = null;
				try {
					siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
					siteInst = siteService.select(ConstantUtil.siteId);
					siteInst.setStatusMark(55);
					ControlKeyValue selecttunnel = (ControlKeyValue) portLabelComboBox.getSelectedItem();
					PortInst portInst = (PortInst) selecttunnel.getObject();
					siteInst.setParameter(portInst.getNumber());
					siteDispatch = new DispatchUtil(RmiKeys.RMI_SITE);
					result = siteDispatch.oamStatus(siteInst);
					if(result !=null && result.getInsertLinkOamInfo()!= null){
						ethLinkOamPanel.refresh(result.getInsertLinkOamInfo());
						DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
						this.insertOpeLog(EOperationLogType.ETHLINKOAMFIND.getValue(),ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS), null,null);
					}else{
						DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_FIND_DETAIL_FAIL));
						this.insertOpeLog(EOperationLogType.ETHLINKOAMFIND.getValue(), ResourceUtil.srcStr(StringKeysTip.TIP_FIND_DETAIL_FAIL), null,null);
					}
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				} finally {
					UiUtil.closeService_MB(siteService);
				}
			}

			private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
			   AddOperateLog.insertOperLog(okButton, operationType, result, oldMac, newMac, ConstantUtil.siteId,ResourceUtil.srcStr(StringKeysLbl.LBL_ETH_OAM_QUERY),"");				
			}
			
			@Override
			public boolean checking() {
				return true;
			}
		});
	}
	
	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(PtnButton okButton) {
		this.okButton = okButton;
	}

}
