package com.nms.ui.ptn.ne.eth.view.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.qos.QosQueueService_MB;
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
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.eth.view.PortBasicPanel;
import com.nms.ui.ptn.ne.eth.view.dialog.cx.PortEthCXDailog;
import com.nms.ui.ptn.ne.eth.view.dialog.cx.PortNniCXDialog;
import com.nms.ui.ptn.ne.eth.view.dialog.cx.PortSfpCXDialog;
import com.nms.ui.ptn.ne.eth.view.dialog.cx.PortUniCXDialog;
import com.nms.ui.ptn.ne.eth.view.dialog.wh.PortAttrWHDialog;
import com.nms.ui.ptn.ne.eth.view.dialog.wh.PortEthWHDialog;

/**
 * 
 * 项目名称：WuHanPTN2012 类名称：PortMainDialog 类描述： 修改端口端口部分主界面 创建人：kk 创建时间：2013-7-15 上午11:51:35 修改人：kk 修改时间：2013-7-15 上午11:51:35 修改备注：
 * 
 * @version
 * 
 */
public class PortMainDialog extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3596523489185526946L;
	private JTabbedPane tabbedPane; // tab控件
	private PtnButton btnSave; // 确定按钮
	private JButton btnCanel; // 取消按钮
	private JPanel panelBtn; // 按钮布局
	private JScrollPane scrollPane;
	private PortEthCXDailog portEthCXDailog; // 晨晓eth界面
	private PortUniCXDialog portUniCXDialog; // 晨晓uni界面
	private PortNniCXDialog portNniCXDialog; // 晨晓nni界面
	private PortSfpCXDialog portSfpCXDialog; // 晨晓sfp界面
	private PortEthWHDialog portEthWHDialog; // 武汉eth界面
	private PortAttrWHDialog portAttrWHDialog; // 武汉attr界面
	private PortInst portInst = null;
	private PortBasicPanel portBasicPanel = null;

	/**
	 * 构造方法
	 * 
	 * @param portInst
	 *            端口BEAN 修改用
	 * @param portBasicPanel
	 *            端口panel 刷新用
	 */
	public PortMainDialog(PortInst portInst, PortBasicPanel portBasicPanel) {
		try {
			this.portInst = portInst;
			this.portBasicPanel=portBasicPanel;
			this.setModal(true);
			this.initComponent();
			this.setLayout();
			this.initData();
			this.addListener();
			UiUtil.showWindow(this, 800, 400);
		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}


	
	/**
	 * 添加监听
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * 
	 * @Exception 异常对象
	 */
	private void addListener() {
		if(null!=portEthCXDailog){
		ControlKeyValue type = (ControlKeyValue) this.portEthCXDailog.getCmbType().getSelectedItem();
		if("UNI".equals(type.getName())){
			this.tabbedPane.setEnabledAt(1,false);
			portUniCXDialog.getCmbOuterVlanTpId().setSelectedIndex(2);
			this.portEthCXDailog.getTxtFrame().setText("1518");
			
		}else if("NNI".equals(type.getName())){
			this.tabbedPane.setEnabledAt(2,false);
			this.portEthCXDailog.getPortInst().getPortAttr().setMaxFrameSize("1600");
			this.portEthCXDailog.getTxtFrame().setText("1600");
		}else{
			this.tabbedPane.setEnabledAt(1,false);
			this.tabbedPane.setEnabledAt(2,false);
			this.portEthCXDailog.getTxtFrame().setText("1518");
		}
		this.portEthCXDailog.getCmbType().addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				
				if(evt.getStateChange() == 1){
					if("NNI".equals(evt.getItem().toString())){
						tabbedPane.setEnabledAt(1,true);
						tabbedPane.setEnabledAt(2,false);
						portEthCXDailog.getTxtFrame().setText("1600");
					}else if("UNI".equals(evt.getItem().toString())){
						tabbedPane.setEnabledAt(1,false);
						tabbedPane.setEnabledAt(2,true);
					}else{
						tabbedPane.setEnabledAt(1,false);
						tabbedPane.setEnabledAt(2,false);
					}
				}
				
			}
		});}
		this.btnCanel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				dispose();
			}
		});

		this.btnSave.addActionListener(new MyActionListener() {

			@Override
			public boolean checking() {
				return true;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					btnSaveListener();
				} catch (Exception e1) {
					ExceptionManage.dispose(e1,this.getClass());
				}
				
			}
		});
	}

	/**
	 * 保存按钮事件
	 * 
	 * @author kk
	 * 
	 * @param
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void btnSaveListener() throws Exception {
		boolean flag = true;
		SiteService_MB siteService = null;
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			siteService =(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if (siteService.getManufacturer(ConstantUtil.siteId) == EManufacturer.WUHAN.getValue()) {
				// 收集武汉数据
				this.portEthWHDialog.getPortInst(this.portInst);
				this.portAttrWHDialog.getPortInst(this.portInst);
				//如果此端口下的QoS队列为空,则配置默认值
				if(!this.hasQoSQueue(this.portInst.getPortId())){
					flag = false;
					this.portInst.setQosQueues(this.getQosQueue(this.portInst.getPortId()));
				}
			} else {
				// 收集晨晓数据
				this.portEthCXDailog.getPortInst(this.portInst);
				this.portSfpCXDialog.getPortInst(this.portInst);
				this.portNniCXDialog.getPortInst(this.portInst);
				this.portUniCXDialog.getPortInst(this.portInst);
			}
			
			String resultStr = null;
			PortInst condition = new PortInst();
			condition.setPortId(this.portInst.getPortId());
			PortInst portInstBefore = portService.select(condition).get(0);
			DispatchUtil dispatchUtil=new DispatchUtil(RmiKeys.RMI_PORT);
			resultStr = dispatchUtil.excuteUpdate(this.portInst);
			//如果是武汉设备,并且此端口没有QoS队列,则下发默认值
			if(siteService.getManufacturer(ConstantUtil.siteId) == EManufacturer.WUHAN.getValue()
					&& !flag && resultStr.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				DispatchUtil qosqueuedispatch = new DispatchUtil(RmiKeys.RMI_QOSQUEUE);
				resultStr = qosqueuedispatch.excuteUpdate(portInst);
			}
			DialogBoxUtil.succeedDialog(this, resultStr);
			//添加日志记录
			if(portInstBefore.getPortAttr().getExitLimit() == null || portInstBefore.getPortAttr().getExitLimit().equals("-1")){
				portInstBefore.getPortAttr().setExitLimit(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_EXPORT_SPEED_NO_LIMIT));
			}
			if(portInst.getPortAttr().getExitLimit() == null || portInst.getPortAttr().getExitLimit().equals("-1")){
				portInst.getPortAttr().setExitLimit(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT_EXPORT_SPEED_NO_LIMIT));
			}
			AddOperateLog.insertOperLog(btnSave, EOperationLogType.ETHUPDATE.getValue(), resultStr, 
					portInstBefore, this.portInst, ConstantUtil.siteId, this.portInst.getPortName(), "ethPort");
			this.portBasicPanel.getController().refresh();
			this.dispose();
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(portService);
		}
	}

	/**
	 * 给端口配置默认qos队列
	 */
	private List<QosQueue> getQosQueue(int portId) {
		List<QosQueue> qosQueueList = new ArrayList<QosQueue>();
		for (int i = 0; i < 8; i++) {
			QosQueue queue = new QosQueue();
			queue.setServiceId(0);
			queue.setSiteId(ConstantUtil.siteId);
			queue.setObjType("SECTION");
			queue.setCos(i);
			queue.setCir(0);
			queue.setWeight(1);
			queue.setQueueType("SP");
			queue.setGreenLowThresh(50);
			queue.setGreenHighThresh(90);
			queue.setWredEnable(Boolean.FALSE);
			queue.setMostBandwidth(ResourceUtil.srcStr(StringKeysObj.QOS_UNLIMITED));
			queue.setDisCard(50);
			queue.setObjId(portId);
			qosQueueList.add(queue);
		}
		return qosQueueList;
	}

	/**
	 * 验证此端口下是否有qos队列
	 * @return true/false 有/没有
	 */
	private boolean hasQoSQueue(int portId) {
		QosQueueService_MB qosQueueService = null;
		try {
			qosQueueService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue);
			QosQueue qosQueue = new QosQueue();
			qosQueue.setObjId(portId);
			qosQueue.setObjType(EServiceType.SECTION.toString());
			List<QosQueue> portQosList = qosQueueService.queryByCondition(qosQueue);
			if(portQosList.size() == 0){
				return false;
			}else{
				return true;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(qosQueueService);
		}
		return false;
	}



	/**
	 * 初始化数据
	 * 
	 * @throws Exception
	 */
	private void initData() throws Exception {
		SiteService_MB siteService = null;
		try {
			 siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			// 武汉和晨晓设备 显示不同界面
			if (siteService.getManufacturer(ConstantUtil.siteId) == EManufacturer.WUHAN.getValue()) {
				this.portEthWHDialog = new PortEthWHDialog(portInst);
				this.portAttrWHDialog = new PortAttrWHDialog(portInst);
				// 给TAB添加panel
				this.tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.LBL_ETH_ATTR), this.portEthWHDialog);
				scrollPane.setViewportView(this.portAttrWHDialog);
				this.tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.LBL_HIGH_ATTR),scrollPane);
			} else {
				this.portEthCXDailog = new PortEthCXDailog(portInst);
				this.portUniCXDialog = new PortUniCXDialog(portInst);
				this.portNniCXDialog = new PortNniCXDialog(portInst);
				this.portSfpCXDialog = new PortSfpCXDialog(portInst);
				
				// 给TAB添加panel
				this.tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.LBL_ETH_ATTR), this.portEthCXDailog);
				this.tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.LBL_NNI_ATTR), this.portNniCXDialog);
				this.tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.LBL_UNI_ATTR), this.portUniCXDialog);
				this.tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.LBL_SFP_ATTR), this.portSfpCXDialog);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
	}

	/**
	 * 初始化控件
	 */
	private void initComponent() throws Exception {
		scrollPane = new JScrollPane();
		this.tabbedPane = new JTabbedPane();
		this.btnCanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.btnSave = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true);
		this.panelBtn = new JPanel();
	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
		this.setButtonLayout();

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 500 };
		componentLayout.columnWeights = new double[] { 0.1, 0 };
		componentLayout.rowHeights = new int[] { 330, 40 };
		componentLayout.rowWeights = new double[] { 0, 0 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.tabbedPane, c);
		this.add(this.tabbedPane);

		c.gridy = 1;
		componentLayout.setConstraints(this.panelBtn, c);
		this.add(this.panelBtn);
	}

	/**
	 * 设置按钮布局
	 */
	private void setButtonLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 650, 50 };
		componentLayout.columnWeights = new double[] { 0, 0 };
		componentLayout.rowHeights = new int[] { 40 };
		componentLayout.rowWeights = new double[] { 0 };
		this.panelBtn.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.btnSave, c);
		this.panelBtn.add(this.btnSave);

		c.gridx = 1;
		c.anchor = GridBagConstraints.CENTER;
		componentLayout.setConstraints(this.btnCanel, c);
		this.panelBtn.add(this.btnCanel);

	}

}
