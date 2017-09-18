package com.nms.ui.ptn.ne.pdh.view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import twaver.table.editor.SpinnerNumberEditor;

import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EPwType;
import com.nms.model.equipment.port.E1InfoService_MB;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
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
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysPanel;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

/**
 * PDH端口
 * @author Administrator
 *
 */
public class E1Panel extends PtnPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3075846442039020356L;
	private JPanel e1InfoPanel;
	private JLabel outputClockSrcLabel;
	private JComboBox outputClockSrcComboBox;
	private JLabel tdmClocksrcLabel;
	private JComboBox tdmClocksrcComboBox;
	private JLabel rtpEnableLabel;
	private JComboBox rtpEnableComboBox;
	private JLabel fzTypeLabel;
	private JComboBox fzTypeComboBox;
	private JLabel frameFormatLabel;
	private JComboBox frameFormatComboBox;
	private JLabel complexFrameJLabel;
	private JComboBox complexFrameComboBox;
	private JTable legTable;
	private JScrollPane legJScrollPanel;
	private PtnButton okButton;
	private PtnButton synchroButton;
	private JPanel buttonJPanel;
	private int E1LEGCONSTANT = 16;
	private List<E1Info> elInfoList;
	/**
	 * 创建一个新的实例
	 * @throws Exception
	 */
	public E1Panel() throws Exception {
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			PortInst portInst = new PortInst();
			portInst.setPortType("e1");
			portInst.setSiteId(ConstantUtil.siteId);
			E1LEGCONSTANT = portService.select(portInst).size();
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(portService);
		}
	}

	/**
	 * 初始化方法
	 * @throws Exception
	 */
	public void init() throws Exception {
		initComponents();
		configE1LegTableUI();
		UiUtil.jTableColumsHide(legTable, 1);
		setLayout();
		try {
			super.getComboBoxDataUtil().comboBoxData(this.outputClockSrcComboBox, "E1LEGOUTPUTCLOCKSRC");
			super.getComboBoxDataUtil().comboBoxData(this.tdmClocksrcComboBox, "TDMCLOCKSRC");
			super.getComboBoxDataUtil().comboBoxData(this.rtpEnableComboBox, "ENABLEDSTATUE");
			super.getComboBoxDataUtil().comboBoxData(this.fzTypeComboBox, "FZTYPE");
            super.getComboBoxDataUtil().comboBoxData(this.frameFormatComboBox, "frameformat");			
			super.getComboBoxDataUtil().comboBoxData(this.complexFrameComboBox, "complexFrame");	
			addListeners();
			this.tableData();
			
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 初始化控件
	 */
	private void initComponents() {
		this.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysPanel.PANEL_PDH_PORT_MANAGE)));
		e1InfoPanel = new JPanel();
		
		legJScrollPanel = new JScrollPane();
		legJScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		legJScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		// E1信息
		outputClockSrcLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_E1_SUBCIRCUIT));
		outputClockSrcComboBox = new JComboBox();

		tdmClocksrcLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TDM_CLOCK));
		tdmClocksrcComboBox = new JComboBox();

		rtpEnableLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_RTP_ENABLE));
		rtpEnableComboBox = new JComboBox();
		
		fzTypeLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_FZ_TYPE));
		fzTypeComboBox = new JComboBox();
		
		frameFormatLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_FRAME_FORMAT));
		frameFormatComboBox = new JComboBox();
	    complexFrameJLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_COMPLEX_FRAME));
		complexFrameComboBox = new JComboBox();
	
		
		// 支路表格
		legTable = new JTable();
		legTable.getTableHeader().setResizingAllowed(true);
		legTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		legTable.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "  ", ResourceUtil.srcStr(StringKeysObj.ORDER_NUM), ResourceUtil.srcStr(StringKeysObj.LINE_NAME), ResourceUtil.srcStr(StringKeysObj.SPUR_TRACK_SHIELD), ResourceUtil.srcStr(StringKeysObj.SPUR_TRACK_ENABLE), ResourceUtil.srcStr(StringKeysObj.CACHE_TIME_ENABLE), ResourceUtil.srcStr(StringKeysObj.CACHE_TIME), ResourceUtil.srcStr(StringKeysObj.PACKAGING_NUM), ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_REVERSAL) }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1583249404349686087L;
			@SuppressWarnings("rawtypes")
			Class[] types = new Class[] { java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class ,java.lang.String.class};

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				if(columnIndex == 1 || columnIndex == 2){
					return false;
				}
				return true;
			}
		}

		);

		legJScrollPanel.setViewportView(legTable);
		okButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true,RootFactory.CORE_MANAGE);
		synchroButton = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SYNCHRO),true,RootFactory.CORE_MANAGE);
		buttonJPanel = new JPanel();
	}

	/**
	 * 页面布局
	 */
	public void setLayout() {
		setUserInfoLayout();
		setButtonPaneLayout();
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		GridBagLayout contentLayout = new GridBagLayout();
		this.setLayout(contentLayout);
		contentLayout.columnWeights = new double[] { 1.0 };
		contentLayout.rowHeights = new int[] { 260, 40, 20, 80 };
		contentLayout.rowWeights = new double[] { 0.5, 0, 0, 0.2 };
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(10, 0, 10, 0);
		c.fill = GridBagConstraints.BOTH;
		contentLayout.setConstraints(legJScrollPanel, c);
		this.add(legJScrollPanel);
		c.gridy = 1;
		c.insets = new Insets(0, 0, 10, 0);
		contentLayout.setConstraints(e1InfoPanel, c);
		this.add(e1InfoPanel);
		c.gridy = 2;
		c.insets = new Insets(0, 0, 10, 0);
		contentLayout.setConstraints(buttonJPanel, c);
		this.add(buttonJPanel);
	}

	/**
	 * 用户信息布局
	 */
	public void setUserInfoLayout() {
		GridBagLayout userInfoLayout = new GridBagLayout();
		userInfoLayout.columnWidths = new int[] { 60, 80, 60, 120, 40 };
		userInfoLayout.columnWeights = new double[] { 0.05, 0.05, 0.05, 0.1, 0.1 };
		userInfoLayout.rowHeights = new int[] { 20, 20 };
		userInfoLayout.rowWeights = new double[] { 0, 0 };
		e1InfoPanel.setLayout(userInfoLayout);
		GridBagConstraints c = null;
		c = new GridBagConstraints();
		c.insets = new Insets(5, 10, 5, 10);
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		userInfoLayout.setConstraints(outputClockSrcLabel, c);
		e1InfoPanel.add(outputClockSrcLabel);

		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5, 20, 5, 10);
		userInfoLayout.setConstraints(outputClockSrcComboBox, c);
		e1InfoPanel.add(outputClockSrcComboBox);

		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5, 30, 5, 10);
		userInfoLayout.setConstraints(rtpEnableLabel, c);
		e1InfoPanel.add(rtpEnableLabel);
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 40, 5, 10);
		userInfoLayout.setConstraints(rtpEnableComboBox, c);
		e1InfoPanel.add(rtpEnableComboBox);

		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 10, 5, 10);
		userInfoLayout.setConstraints(tdmClocksrcLabel, c);
		e1InfoPanel.add(tdmClocksrcLabel);

		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 20, 5, 10);
		userInfoLayout.setConstraints(tdmClocksrcComboBox, c);
		e1InfoPanel.add(tdmClocksrcComboBox);
		
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 30, 5, 10);
		userInfoLayout.setConstraints(fzTypeLabel, c);
		e1InfoPanel.add(fzTypeLabel);
		
		c.gridx = 3;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 40, 5, 10);
		userInfoLayout.setConstraints(fzTypeComboBox, c);
		e1InfoPanel.add(fzTypeComboBox);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5, 10, 5, 10);
		userInfoLayout.setConstraints(frameFormatLabel, c);
		e1InfoPanel.add(frameFormatLabel);
		
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(5, 20, 5, 10);
		userInfoLayout.setConstraints(frameFormatComboBox, c);
		e1InfoPanel.add(frameFormatComboBox);
		
		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 30, 5, 10);
		userInfoLayout.setConstraints(complexFrameJLabel, c);
		e1InfoPanel.add(complexFrameJLabel);
		
		c.gridx = 3;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(10, 40, 5, 10);
		userInfoLayout.setConstraints(complexFrameComboBox, c);
		e1InfoPanel.add(complexFrameComboBox);
		
		

	}

	/**
	 * 按钮布局
	 */
	public void setButtonPaneLayout() {
		buttonJPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 80, 10));
		buttonJPanel.add(synchroButton);
		buttonJPanel.add(okButton);
	}

	/**
	 * 表格数据初始化
	 * @throws Exception
	 */
	public void tableData() throws Exception {
		E1InfoService_MB e1InfoService = null;
		E1Info e1Info = null;
		try {
			e1InfoService = (E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);
			e1Info = new E1Info();
			e1Info.setSiteId(ConstantUtil.siteId);
			elInfoList = e1InfoService.selectByCondition(e1Info);
			if (elInfoList.size() > 0) {
				refreshUIData(elInfoList);
			} else {
				setDefaultTable();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(e1InfoService);
		}
	}

	/**
	 *让表格中的封装帧个数，缓存时间(ms)，pw标签成为下拉列表的格式
	 * 
	 * @throws Exception
	 */
	private void configE1LegTableUI() throws Exception {
		
		JComboBox pinCountCombox = new JComboBox();
		for (int i = 0; i < 4; i++) {
			int j = 4;
			j = j*(1+i);
			pinCountCombox.addItem(j);
		}
		TableColumn pinCountColumn = legTable.getColumn(ResourceUtil.srcStr(StringKeysObj.PACKAGING_NUM));
		pinCountColumn.setCellEditor(new DefaultCellEditor(pinCountCombox));

		TableColumn prestoreTimeColumn = legTable.getColumn(ResourceUtil.srcStr(StringKeysObj.CACHE_TIME));
		prestoreTimeColumn.setCellEditor(new SpinnerNumberEditor("3", "7"));

//		TableColumn pwLabelColumn = legTable.getColumn(ResourceUtil.srcStr(StringKeysObj.PW_LABEL));
//
//		JComboBox pwLableCombox = new JComboBox();
//		pwLableCombox.setEditable(true);
//		this.comboxDataPwLabel(pwLableCombox, ConstantUtil.siteId);
//		pwLabelColumn.setCellEditor(new DefaultCellEditor(pwLableCombox));
		
		TableColumn alarmReversalColumn = legTable.getColumn(ResourceUtil.srcStr(StringKeysLbl.LBL_ALARM_REVERSAL));
		JComboBox alarmReversalComboBox = new JComboBox();
		alarmReversalComboBox.setEditable(true);
		alarmReversalComboBox.addItem(ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO));
		alarmReversalComboBox.addItem(ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED));
		alarmReversalColumn.setCellEditor(new DefaultCellEditor(alarmReversalComboBox));
	}

	/**
	 * 查询该网元下的所有PW标签
	 * 
	 * @throws Exception
	 */
	private void comboxDataPwLabel(JComboBox pwLableCombox, int siteId) throws Exception {
		PwInfoService_MB pwInfoService = null;
		List<PwInfo> pwInfoList = null;
		DefaultComboBoxModel defaultComboBoxModel = null;
		// PwInfo
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwInfoList = pwInfoService.selectNodeBySiteid(ConstantUtil.siteId);
			defaultComboBoxModel = new DefaultComboBoxModel();
			for (PwInfo pwInfo : pwInfoList) {
				if (pwInfo.getType() == EPwType.PDH) {
					if (pwInfo.getASiteId() == siteId) {
						defaultComboBoxModel.addElement(pwInfo.getInlabelValue());
					} else {
						defaultComboBoxModel.addElement(pwInfo.getOutlabelValue());
					}
				}
			}
			pwLableCombox.setModel(defaultComboBoxModel);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(pwInfoService);
		}
	}

	/**
	 * 查询数据库中E1信息，显示表中
	 * 
	 * @param e1InfoList
	 * @throws Exception
	 */
	private void refreshUIData(List<E1Info> e1InfoList) throws Exception {
		super.getComboBoxDataUtil().comboBoxSelect(this.outputClockSrcComboBox, getComboBoxSelectId(this.outputClockSrcComboBox, e1InfoList.get(0).getE1LegOutPutClockSource()));
		super.getComboBoxDataUtil().comboBoxSelect(this.tdmClocksrcComboBox, getComboBoxSelectId(this.tdmClocksrcComboBox, e1InfoList.get(0).getTdmClockSource()));
		super.getComboBoxDataUtil().comboBoxSelect(this.rtpEnableComboBox, getComboBoxSelectId(this.rtpEnableComboBox, e1InfoList.get(0).getRtpEnable()));
		super.getComboBoxDataUtil().comboBoxSelect(this.fzTypeComboBox, getComboBoxSelectId(this.fzTypeComboBox, e1InfoList.get(0).getFzType()));	
		super.getComboBoxDataUtil().comboBoxSelect(this.frameFormatComboBox, getComboBoxSelectId(this.frameFormatComboBox, e1InfoList.get(0).getFrameformat()));
		super.getComboBoxDataUtil().comboBoxSelect(this.complexFrameComboBox, getComboBoxSelectId(this.complexFrameComboBox, e1InfoList.get(0).getComplexFrame()));
		
		if(this.fzTypeComboBox.getSelectedIndex()==0){
			this.frameFormatComboBox.setEnabled(false);
			this.complexFrameComboBox.setEnabled(false);
			
		}else{
			this.frameFormatComboBox.setEnabled(true);
			this.complexFrameComboBox.setEnabled(true);
			
		}
		CesInfoService_MB cesInfoService = null;
		List<Integer> integers = new ArrayList<Integer>();
		try {
			cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			List<CesInfo> cesInfos = cesInfoService.selectNodeBySite(ConstantUtil.siteId);
			for(CesInfo cesInfo :cesInfos){
				if(cesInfo.getaSiteId() == ConstantUtil.siteId){
					integers.add(cesInfo.getaAcId());
				}else if(cesInfo.getzSiteId() == ConstantUtil.siteId){
					integers.add(cesInfo.getzAcId());
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(cesInfoService);
		}
		DefaultTableModel defaultTableModel = null;
		defaultTableModel = (DefaultTableModel) legTable.getModel();
		defaultTableModel.getDataVector().clear();
		defaultTableModel.fireTableDataChanged();
		Object[] obj = null;
		if (e1InfoList != null && !e1InfoList.isEmpty()) {
			for (int i = 0; i < E1LEGCONSTANT; i++) {
			//初始值
				obj = new Object[] { e1InfoList.get(i), i+1, ResourceUtil.srcStr(StringKeysObj.LINE) + (i+1), 
						e1InfoList.get(i).getLegShield() == 0 ? Boolean.FALSE : Boolean.TRUE,
						e1InfoList.get(i).getLegEnable() == 0 ? Boolean.FALSE : Boolean.TRUE, 
						e1InfoList.get(i).getPrestoreTimeEnable() == 0 ? Boolean.FALSE : Boolean.TRUE, 
						setPrestoreTime(e1InfoList.get(i)),
						e1InfoList.get(i).getPinCount(),
						e1InfoList.get(i).getIsAlarmRevesal() == 1?ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO):ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED)
				};
				defaultTableModel.addRow(obj);    
				if(integers.contains(e1InfoList.get(i).getPortId())){
					legTable.getCellRenderer(i,4).getTableCellRendererComponent(legTable, legTable.getValueAt(i,4), false, false, i, 4).setEnabled(false);
				}
			}
			legTable.setModel(defaultTableModel);
		} else {
			setDefaultTable();
		}
	}
	/**
	 * 选取默认值填入支路信息表中
	 */
	private int setPrestoreTime(E1Info e1Info) {
		if(e1Info.getPrestoreTimeEnable()==0){
			return 0;
		}else{
			return e1Info.getPrestoreTime();
		}
	}
	
	/**
	 * 选取默认值填入支路信息表中
	 */
	private void setDefaultTable() {
		DefaultTableModel defaultTableModel = null;
		defaultTableModel = (DefaultTableModel) legTable.getModel();
		defaultTableModel.getDataVector().clear();
		defaultTableModel.fireTableDataChanged();
		Object[] obj = null;
		for (int i = 1; i <= E1LEGCONSTANT; i++) {
			obj = new Object[] { null, i, ResourceUtil.srcStr(StringKeysObj.LINE) + i, new Boolean(false), new Boolean(false), new Boolean(false), 0, 4, 0,ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO)};
			defaultTableModel.addRow(obj);
		}
		legTable.setModel(defaultTableModel);
	}
	
	/**
	 * 下拉菜单初始化
	 * @param jComboBox
	 * @param codeValue
	 * @return
	 */
	public String getComboBoxSelectId(JComboBox jComboBox, Integer codeValue) {
		for (int i = 0; i < jComboBox.getItemCount(); i++) {
			if (((Code) ((ControlKeyValue) jComboBox.getItemAt(i)).getObject()).getCodeValue().equals(codeValue.toString())) {
				return ((ControlKeyValue) jComboBox.getItemAt(i)).getId();
			}
		}
		return null;
	}

	/**
	 * 监听事件
	 */
	private void addListeners() {
		this.legTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (legTable.getSelectedColumn() > 1) {
					commitTable(legTable);
				}
				if (legTable.isEditing()) {
					// 返回活动单元格编辑器；如果该表当前没有被编辑，则返回 null。
					legTable.getCellEditor().stopCellEditing();
				}
			}
		});

		// 下发配置
		this.okButton.addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<E1Info>  e1InfoList = collectData();
				E1InfoService_MB e1InfoService = null;
				try {
					e1InfoService = (E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);
					E1Info e1Condition = new E1Info();
					e1Condition.setSiteId(ConstantUtil.siteId);
					List<E1Info> elInfoListBefore = e1InfoService.selectByCondition(e1Condition);
					DispatchUtil e1Dispatch = new DispatchUtil(RmiKeys.RMI_E1);
					String str = e1Dispatch.excuteUpdate(e1InfoList);
					DialogBoxUtil.succeedDialog(null, str);
					for (int i = 0; i < e1InfoList.size(); i++) {
						int alarmValueBefore = elInfoListBefore.get(i).getIsAlarmRevesal();
						int alarmValue = e1InfoList.get(i).getIsAlarmRevesal();
						elInfoListBefore.get(i).setIsAlarmRevesal(alarmValueBefore==1?0:1);
						e1InfoList.get(i).setIsAlarmRevesal(alarmValue==1?0:1);
						AddOperateLog.insertOperLog(okButton, EOperationLogType.E1PORTUPDATE.getValue(), str, 
								elInfoListBefore.get(i), e1InfoList.get(i), ConstantUtil.siteId, e1InfoList.get(i).getPortName(), "e1Port");
					}
					tableData();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				} finally {
					UiUtil.closeService_MB(e1InfoService);
				}
			}

			@Override
			public boolean checking() {
				return true;
			}
		});
		// 同步配置
		this.synchroButton.addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
					DispatchUtil e1Dispatch = null;
				try {
					e1Dispatch = new DispatchUtil(RmiKeys.RMI_E1);
					String result = e1Dispatch.synchro(ConstantUtil.siteId);
					DialogBoxUtil.succeedDialog(null, result);
					AddOperateLog.insertOperLog(null, EOperationLogType.E1PORTSYS.getValue(), result, 
							null, null, ConstantUtil.siteId, "PDH", null);
					tableData();
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}

			@Override
			public boolean checking() {
				// TODO Auto-generated method stub
				return true;
			}
		});
		//下拉框点击事件
		this.fzTypeComboBox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				if((fzTypeComboBox.getSelectedIndex()==0)){				
				frameFormatComboBox.setEnabled(false);
				complexFrameComboBox.setEnabled(false);
				
				
				}else{
					
					frameFormatComboBox.setEnabled(true);
					complexFrameComboBox.setEnabled(true);
				}
			}
			
		});
	}

	/**
	 * 收集表格数据
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected List<E1Info> collectData() {

		ControlKeyValue e1LegOutPutClockSrcCK = (ControlKeyValue) this.outputClockSrcComboBox.getSelectedItem();
		ControlKeyValue tdmClockSrcCK = (ControlKeyValue) this.tdmClocksrcComboBox.getSelectedItem();
		ControlKeyValue rtpEnableCK = (ControlKeyValue) this.rtpEnableComboBox.getSelectedItem();
		ControlKeyValue fzTypeCK = (ControlKeyValue) this.fzTypeComboBox.getSelectedItem();	
		ControlKeyValue frameFormatCK = (ControlKeyValue) this.frameFormatComboBox.getSelectedItem();
	    ControlKeyValue complexFrameCK = (ControlKeyValue) this.complexFrameComboBox.getSelectedItem();
	
		// 收集线路数据
		DefaultTableModel e1legTableModel = (DefaultTableModel) legTable.getModel();
		Vector vector = e1legTableModel.getDataVector();
		Iterator it = vector.iterator();
		int i = 1;
		while (it.hasNext()) {
			Vector temp = (Vector) it.next();
			elInfoList.get(i - 1).setSiteId(ConstantUtil.siteId);
			elInfoList.get(i - 1).setE1LegOutPutClockSource(Integer.parseInt(((Code) e1LegOutPutClockSrcCK.getObject()).getCodeValue()));
			elInfoList.get(i - 1).setTdmClockSource(Integer.parseInt(((Code) tdmClockSrcCK.getObject()).getCodeValue()));
			elInfoList.get(i - 1).setRtpEnable(Integer.parseInt(((Code) rtpEnableCK.getObject()).getCodeValue()));
			elInfoList.get(i - 1).setFzType(Integer.parseInt(((Code) fzTypeCK.getObject()).getCodeValue()));							
			elInfoList.get(i - 1).setFrameformat(Integer.parseInt(((Code) frameFormatCK.getObject()).getCodeValue()));
			elInfoList.get(i - 1).setComplexFrame(Integer.parseInt(((Code) complexFrameCK.getObject()).getCodeValue()));
			if (temp.get(0) != null) {
				elInfoList.get(i - 1).setId(((E1Info) temp.get(0)).getId());
			}
			if (temp.get(3) != null) {
				elInfoList.get(i - 1).setLegShield((Boolean) temp.get(3) ? 1 : 0);
			}
			if (temp.get(4) != null) {
				elInfoList.get(i - 1).setLegEnable((Boolean) temp.get(4) ? 1 : 0);
			}
			if (temp.get(5) != null) {
				elInfoList.get(i - 1).setPrestoreTimeEnable((Boolean) temp.get(5) ? 1 : 0);
			}
			if (temp.get(6) != null) {
				elInfoList.get(i - 1).setPrestoreTime(Integer.parseInt(temp.get(6).toString()));
			}
			if (temp.get(7) != null) {
				elInfoList.get(i - 1).setPinCount(Integer.parseInt(temp.get(7).toString()));
			}
			if (temp.get(8) != null) {
				if(temp.get(8).toString().equals(ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED_NO))){
					elInfoList.get(i - 1).setIsAlarmRevesal(1);
				}else if(temp.get(8).toString().equals(ResourceUtil.srcStr(StringKeysObj.ALLCONFIG_FID_ENABLED))){
					elInfoList.get(i - 1).setIsAlarmRevesal(2);
				}
			}
			i++;
		}
		return elInfoList;
	}

	/*
	 * 使表格数据瞬间变化
	 */
	public void commitTable(JTable table) {
		int selectR = -1;
		int selectC = -1;
		int newValue = 0;
		JSpinner spinner = null;
		try {
			if(table.getSelectedColumn()==5){
				if(((Boolean)table.getValueAt(table.getSelectedRow(), table.getSelectedColumn())).booleanValue()){
					table.setValueAt(4, table.getSelectedRow(),  table.getSelectedColumn()+1);
				}else{
					table.setValueAt(0, table.getSelectedRow(),  table.getSelectedColumn()+1);
				}
			}
			if (table.getEditorComponent() != null) {
				selectR = table.getSelectedRow();
				selectC = table.getSelectedColumn();

				if (table.getEditorComponent() instanceof JSpinner) {
					spinner = (JSpinner) table.getEditorComponent();
					if(selectC==6&&((Boolean)table.getValueAt(selectR, selectC-1)).booleanValue()){
						JTextField ff = ((JSpinner.NumberEditor) (spinner.getComponents()[2])).getTextField();
						String value = ff.getText();
						((DefaultEditor) spinner.getEditor()).getTextField().setText(value);
						for (char di : value.replace(",", "").toCharArray()) {
							if (!Character.isDigit(di)) {
								return;
							}
						}
						if ("".equals(value.replace(",", ""))) {
							newValue = 1;
						} else if (Long.parseLong(value.replace(",", "")) >= 255) {
							newValue = 255;
						} else if (Long.parseLong(value.replace(",", "")) <= 1) {
							newValue = 1;
						} else {
							newValue = Integer.parseInt(value.replace(",", ""));
						}
						if ( selectC == 6) {
							spinner.setModel(new SpinnerNumberModel(newValue, 1, 255, 1));
						} 
						spinner.commitEdit();
						if (table.isEditing()) {
							table.getCellEditor().stopCellEditing();
						}
					}else{
						table.setValueAt(0, selectR, selectC);
					}

				} 
			}
		} catch (Exception e) {
			((DefaultEditor) spinner.getEditor()).getTextField().setText(spinner.getValue() + "");
			ExceptionManage.dispose(e,this.getClass());
		}

	}

}
