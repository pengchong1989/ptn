package com.nms.ui.ptn.ne.eth.view;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.system.code.Code;
import com.nms.db.bean.system.code.CodeGroup;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.portlag.view.ExportQueueDialog;

public class PortConfigPanel extends PtnPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8747763750088698069L;
	private JComboBox portStatusComboBox;//
	private JLabel buffer;//802.3流控:0/1=不使能/使能(界面不显示)
	private JComboBox bufferComboBox;//
	private JLabel mtuLabel;//MTU:(1518-1548-9600)界面十进制显示(界面不显示)	
	private JTextField mtuText;//
	private JButton exportQueue;//出口队列策略按钮		
	private JLabel portLimitationLabel ;// 端口出口限速 
	private JTextField portLimitationText;
	private JLabel broadcastBateLabel ;// 广播包抑制 (0)/1=(关)/开 
	private JComboBox broadcastBateComboBox;
	private JLabel broadcastFluxLabel ;// 广播包流量 
	private JTextField broadcastFluxText;
	private JLabel groupBroadcastBateLabel ;// 组播包抑制  
	private JComboBox groupBroadcastBateComboBox;
	private JLabel groupBroadcastFluxLabel ;// 组播包流量 
	private JTextField groupBroadcastFluxText;
	private JLabel floodBateLabel ;// 洪泛包抑制 (0)/1=(关)/开 
	private JComboBox floodBateComboBox;
	private JLabel floodFluxLabel ;// 洪泛包流量
	private JTextField floodFluxText;
	private JPanel contentPanel;
	private PortInst portinst;
	public PortConfigPanel(PortInst portInst){
		init();
		portinst = portInst;
		setValue();
		addListener();
	}
	/**
	 * 添加监听事件
	 */
	private void addListener() {
		 
		exportQueue.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {


				final ExportQueueDialog dialog = new ExportQueueDialog(arg0.getActionCommand());
				dialog.setSize(600, 400);
				dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));
				dialog.init(portinst.getLagInfo().getExportQueue());
				
//				dialog.addWindowListener(new WindowAdapter() {
//					@Override
//					public void windowClosed(WindowEvent e) {
//						dialog.dispose();
//					}
//				});

				dialog.getConfirm().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							portinst.getLagInfo().setExportQueue(dialog.get());
							DialogBoxUtil.succeedDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_SAVE_SUCCEED));
							dialog.dispose();
						} catch (Exception e) {
							ExceptionManage.dispose(e,this.getClass());
						}
					}
				});

				dialog.getCancel().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						dialog.dispose();
					}
				});
				dialog.setVisible(true);
			
				
			}
			
		});
	}
	
	/**
	 * 初始化数据
	 */
	private void setValue() {
		 
		try {
			if(portinst.getLagInfo() != null){
				this.comboBoxSelect(portStatusComboBox, "ENABLEDSTATUE", portinst.getLagInfo().getPortEnable());
				this.comboBoxSelect(bufferComboBox, "ENABLEDSTATUE", portinst.getLagInfo().getFlowControl());
				mtuText.setText(portinst.getLagInfo().getMtu()+"");
				this.comboBoxSelect(broadcastBateComboBox, "VCTRAFFICPOLICING", portinst.getLagInfo().getBroadcastBate());
				this.comboBoxSelect(groupBroadcastBateComboBox, "VCTRAFFICPOLICING", portinst.getLagInfo().getGroupBroadcastBate());
				this.comboBoxSelect(floodBateComboBox, "VCTRAFFICPOLICING", portinst.getLagInfo().getFloodBate());
				portLimitationText.setText(portinst.getLagInfo().getPortLimitation()+"");
				broadcastFluxText.setText(portinst.getLagInfo().getBroadcastFlux()+"");
				groupBroadcastFluxText.setText(portinst.getLagInfo().getGroupBroadcastFlux()+"");
				floodFluxText.setText(portinst.getLagInfo().getFloodFlux()+"");
			}else{
				this.comboBoxSelect(portStatusComboBox, "ENABLEDSTATUE", 1);
				this.comboBoxSelect(bufferComboBox, "ENABLEDSTATUE", 1);
				mtuText.setText(1518+"");
				this.comboBoxSelect(broadcastBateComboBox, "VCTRAFFICPOLICING", 0);
				this.comboBoxSelect(groupBroadcastBateComboBox, "VCTRAFFICPOLICING", 0);
				this.comboBoxSelect(floodBateComboBox, "VCTRAFFICPOLICING", 0);
				portLimitationText.setText(1000000+"");
				broadcastFluxText.setText(1000+"");
				groupBroadcastFluxText.setText(1000+"");
				floodFluxText.setText(1000+"");
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}
	
	/**
	 * 下拉列表初始化
	 * @param jComboBox
	 * @param identity
	 * @param value
	 * @throws Exception
	 */
	private void comboBoxSelect(JComboBox jComboBox, String identity, int value) throws Exception {

		CodeGroup codeGroup = null;
		List<Code> codeList = null;
		DefaultComboBoxModel defaultComboBoxModel = null;
		try {
			codeGroup = UiUtil.getCodeGroupByIdentity(identity);
			codeList = codeGroup.getCodeList();
			for (Code obj : codeList) {
				if (Integer.valueOf(obj.getCodeValue()) == value) {
					defaultComboBoxModel = (DefaultComboBoxModel) jComboBox.getModel();
					defaultComboBoxModel.setSelectedItem(new ControlKeyValue(obj.getId() + "", obj.getCodeName(), obj));
					jComboBox.setModel(defaultComboBoxModel);
					break;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			codeList = null;
			defaultComboBoxModel = null;
		}
	}
	
	/**
	 *  初始化
	 **/
	private void init() {
		initComponents();
		setLayout();
		
	}
	/**
	 * 初始化界面控件
	 */
	private void initComponents() {
		try {
//		portStatus = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_ENABLED));
		portStatusComboBox = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(portStatusComboBox, "ENABLEDSTATUE");
		buffer = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_802_3));
		bufferComboBox = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(bufferComboBox, "ENABLEDSTATUE");
		mtuLabel = new JLabel("MTU");
		mtuText = new JTextField("1518");
		exportQueue = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_OUT_QUEUE_STRATEGY));
		portLimitationLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_EXPORT_SPEED_LIMIT));
		portLimitationText = new JTextField("1000000");
		broadcastBateLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_BROADCAST_RESTRAIN));
		broadcastBateComboBox = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(broadcastBateComboBox, "VCTRAFFICPOLICING");
		broadcastFluxLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_BROADCAST_FLOW));
		broadcastFluxText = new JTextField("0");
		groupBroadcastBateLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MULTICAST_RESTRAIN));
		groupBroadcastBateComboBox = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(groupBroadcastBateComboBox, "VCTRAFFICPOLICING");
		groupBroadcastFluxLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_MULTICAST_FLOW));
		groupBroadcastFluxText = new JTextField("0");
		floodBateLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_FLOODED_RESTRAIN));
		floodBateComboBox = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(floodBateComboBox, "VCTRAFFICPOLICING");
		floodFluxLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_FLOODED_FLOW));
		floodFluxText = new JTextField("0");
		contentPanel = new JPanel();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	/**
	 * 界面布局
	 */
	private void setLayout() {
		// title面板布局
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		layout = new GridBagLayout();
		layout.columnWidths = new int[] { 50, 100, 50, 180};
		layout.columnWeights = new double[] { 0, 0, 0, 0 };
		layout.rowHeights = new int[] { 30, 30, 30, 30, 30 ,30, 30, 30, 30};
		layout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		contentPanel.setLayout(layout);
		addComponent(contentPanel, broadcastBateLabel, 0, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(20, 80, 10, 5), GridBagConstraints.CENTER, c);
		addComponent(contentPanel, broadcastBateComboBox, 1, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(20, 10, 10, 20), GridBagConstraints.CENTER, c);
	
		addComponent(contentPanel, portLimitationLabel, 0, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 80, 10, 5), GridBagConstraints.CENTER, c);
		addComponent(contentPanel, portLimitationText, 1, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 20), GridBagConstraints.CENTER, c);
		
		addComponent(contentPanel, buffer, 0, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 80, 10, 5), GridBagConstraints.CENTER, c);
		addComponent(contentPanel, bufferComboBox, 1, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 20), GridBagConstraints.CENTER, c);
		
		addComponent(contentPanel, mtuLabel, 0, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 80, 10, 5), GridBagConstraints.CENTER, c);
		addComponent(contentPanel, mtuText, 1, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 20), GridBagConstraints.CENTER, c);
		
		addComponent(contentPanel, floodFluxLabel, 0, 5, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 80, 10, 5), GridBagConstraints.CENTER, c);
		addComponent(contentPanel, floodFluxText, 1, 5, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 20), GridBagConstraints.CENTER, c);
		
		addComponent(contentPanel, broadcastFluxLabel, 2, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(20, 50, 10, 1), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, broadcastFluxText, 3, 1, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(20, 10, 10, 80), GridBagConstraints.NORTHWEST, c);
		
		addComponent(contentPanel, groupBroadcastBateLabel, 2, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 50, 10, 1), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, groupBroadcastBateComboBox, 3, 2, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 80), GridBagConstraints.NORTHWEST, c);
		
		addComponent(contentPanel, groupBroadcastFluxLabel, 2, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 50, 10, 1), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, groupBroadcastFluxText, 3, 3, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 80), GridBagConstraints.NORTHWEST, c);
		
		addComponent(contentPanel, floodBateLabel, 2, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 50, 10, 1), GridBagConstraints.NORTHWEST, c);
		addComponent(contentPanel, floodBateComboBox, 3, 4, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 10, 10, 80), GridBagConstraints.NORTHWEST, c);
		
//		addComponent(contentPanel, , 2, 5, 1.0, 0.001, 1, 1,
//				GridBagConstraints.BOTH, new Insets(10, 50, 10, 1), GridBagConstraints.NORTHWEST, c);
//		addComponent(contentPanel, , 3, 5, 1.0, 0.001, 1, 1,
//				GridBagConstraints.BOTH, new Insets(10, 10, 10, 80), GridBagConstraints.NORTHWEST, c);
		
		addComponent(contentPanel, exportQueue, 0, 6, 1.0, 0.001, 1, 1,
				GridBagConstraints.BOTH, new Insets(10, 80, 10, 5), GridBagConstraints.CENTER, c);

		
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		this.add(contentPanel);
	}	
	
	public JComboBox getPortStatusComboBox() {
		return portStatusComboBox;
	}

	public void setPortStatusComboBox(JComboBox portStatusComboBox) {
		this.portStatusComboBox = portStatusComboBox;
	}

	public JLabel getBuffer() {
		return buffer;
	}

	public void setBuffer(JLabel buffer) {
		this.buffer = buffer;
	}

	public JComboBox getBufferComboBox() {
		return bufferComboBox;
	}

	public void setBufferComboBox(JComboBox bufferComboBox) {
		this.bufferComboBox = bufferComboBox;
	}

	public JLabel getMtuLabel() {
		return mtuLabel;
	}

	public void setMtuLabel(JLabel mtuLabel) {
		this.mtuLabel = mtuLabel;
	}

	public JTextField getMtuText() {
		return mtuText;
	}

	public void setMtuText(JTextField mtuText) {
		this.mtuText = mtuText;
	}

	public JButton getExportQueue() {
		return exportQueue;
	}

	public void setExportQueue(JButton exportQueue) {
		this.exportQueue = exportQueue;
	}

	public JLabel getPortLimitationLabel() {
		return portLimitationLabel;
	}

	public void setPortLimitationLabel(JLabel portLimitationLabel) {
		this.portLimitationLabel = portLimitationLabel;
	}

	public JTextField getPortLimitationText() {
		return portLimitationText;
	}

	public void setPortLimitationText(JTextField portLimitationText) {
		this.portLimitationText = portLimitationText;
	}

	public JLabel getBroadcastBateLabel() {
		return broadcastBateLabel;
	}

	public void setBroadcastBateLabel(JLabel broadcastBateLabel) {
		this.broadcastBateLabel = broadcastBateLabel;
	}

	public JComboBox getBroadcastBateComboBox() {
		return broadcastBateComboBox;
	}

	public void setBroadcastBateComboBox(JComboBox broadcastBateComboBox) {
		this.broadcastBateComboBox = broadcastBateComboBox;
	}

	public JLabel getBroadcastFluxLabel() {
		return broadcastFluxLabel;
	}

	public void setBroadcastFluxLabel(JLabel broadcastFluxLabel) {
		this.broadcastFluxLabel = broadcastFluxLabel;
	}

	public JTextField getBroadcastFluxText() {
		return broadcastFluxText;
	}

	public void setBroadcastFluxText(JTextField broadcastFluxText) {
		this.broadcastFluxText = broadcastFluxText;
	}

	public JLabel getGroupBroadcastBateLabel() {
		return groupBroadcastBateLabel;
	}

	public void setGroupBroadcastBateLabel(JLabel groupBroadcastBateLabel) {
		this.groupBroadcastBateLabel = groupBroadcastBateLabel;
	}

	public JComboBox getGroupBroadcastBateComboBox() {
		return groupBroadcastBateComboBox;
	}

	public void setGroupBroadcastBateComboBox(JComboBox groupBroadcastBateComboBox) {
		this.groupBroadcastBateComboBox = groupBroadcastBateComboBox;
	}

	public JLabel getGroupBroadcastFluxLabel() {
		return groupBroadcastFluxLabel;
	}

	public void setGroupBroadcastFluxLabel(JLabel groupBroadcastFluxLabel) {
		this.groupBroadcastFluxLabel = groupBroadcastFluxLabel;
	}

	public JTextField getGroupBroadcastFluxText() {
		return groupBroadcastFluxText;
	}

	public void setGroupBroadcastFluxText(JTextField groupBroadcastFluxText) {
		this.groupBroadcastFluxText = groupBroadcastFluxText;
	}

	public JLabel getFloodBateLabel() {
		return floodBateLabel;
	}

	public void setFloodBateLabel(JLabel floodBateLabel) {
		this.floodBateLabel = floodBateLabel;
	}

	public JComboBox getFloodBateComboBox() {
		return floodBateComboBox;
	}

	public void setFloodBateComboBox(JComboBox floodBateComboBox) {
		this.floodBateComboBox = floodBateComboBox;
	}

	public JLabel getFloodFluxLabel() {
		return floodFluxLabel;
	}

	public void setFloodFluxLabel(JLabel floodFluxLabel) {
		this.floodFluxLabel = floodFluxLabel;
	}

	public JTextField getFloodFluxText() {
		return floodFluxText;
	}

	public void setFloodFluxText(JTextField floodFluxText) {
		this.floodFluxText = floodFluxText;
	}

}

