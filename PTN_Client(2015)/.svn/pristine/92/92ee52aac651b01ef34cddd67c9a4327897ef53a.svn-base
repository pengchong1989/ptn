/*
 * AddPWDialog.java
 *
 * Created on __DATE__, __TIME__
 */

package com.nms.ui.ptn.business.dialog.elanpath;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import twaver.Element;
import twaver.Node;
import twaver.network.TNetwork;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.business.dialog.tunnel.TunnelTopoPanel;
import com.nms.ui.ptn.business.elan.ElanBusinessPanel;

/**
 * 
 * @author __USER__
 */
public class UpdateElanDialog extends javax.swing.JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ElanBusinessPanel elanBusinessPanel;
	//private static UpdateElanDialog addelandialog;
	private List<ElanInfo> elanservice = null;
	List<Tunnel> tunnelList = null; //
	List<PwInfo> pwInfoList = new ArrayList<PwInfo>();
	Map<Integer, List<Tunnel>> siteIdAndTunnelsMap = null;
	Map<Integer, List<PwInfo>> tunnelIdAndPwInfoListMap = null;
	List<PortInst> portInstList = new ArrayList<PortInst>();
	Vector<String> vector = new Vector<String>();
	Vector<Object> acPortVector = new Vector<Object>();
	Vector<Object> pwVector = new Vector<Object>();
	Vector<Object> selpwVector = new Vector<Object>();
	private TunnelTopoPanel tunnelTopoPanel = null;
	

	public UpdateElanDialog(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	/** Creates new form AddPWDialog */
	public UpdateElanDialog(ElanBusinessPanel jPanel1, boolean modal, int elanId) {
		this.setModal(modal);
		initComponents();
		this.elanBusinessPanel = jPanel1;
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		JPanel btnJpanel = new JPanel();
		btnJpanel.setLayout(new GridBagLayout());
		leftBtn.setText(ResourceUtil.srcStr(StringKeysBtn.BTN_LEFT_SHIFT));
		leftBtn.setEnabled(false);
		rightBtn.setEnabled(false);
		rightBtn.setText(ResourceUtil.srcStr(StringKeysBtn.BTN_RIGHT_SHIFT));
		addComponent(btnJpanel, leftBtn, 0, 0, 0.0, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 10, 5), GridBagConstraints.NORTH,
				gridBagConstraints);
		addComponent(btnJpanel, rightBtn, 0, 1, 0.0, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(10, 5, 5, 5), GridBagConstraints.NORTH,
				gridBagConstraints);
		
		rightPanel = new JPanel();
		buttomPanel = new JPanel();

		JPanel leftPwPanel = new JPanel();

		leftPwPanel.setLayout(new GridBagLayout());
		JLabel leftPwLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_PW_LIST));
		JScrollPane pwScroll = new JScrollPane();
		pwScroll.setPreferredSize(new Dimension(200, 100));
		pwList = new JList();
		pwScroll.setViewportView(pwList);
		addComponent(leftPwPanel, leftPwLabel, 0, 0, 0.0, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 0, 0, 5), GridBagConstraints.WEST,
				gridBagConstraints);
		addComponent(leftPwPanel, pwScroll, 0, 1, 0.0, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(10, 0, 0, 5), GridBagConstraints.WEST,
				gridBagConstraints);
		
		JPanel rightSelPwPanel = new JPanel();
		rightSelPwPanel.setLayout(new GridBagLayout());
		JLabel rightSelLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OPTED_PW));
		JScrollPane selPwScroll = new JScrollPane();
		selPwList = new JList();
		selPwScroll.setViewportView(selPwList);
		selPwScroll.setPreferredSize(new Dimension(200, 100));
		addComponent(rightSelPwPanel, rightSelLabel, 0, 0, 0.0, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 0, 5), GridBagConstraints.WEST,
				gridBagConstraints);
		addComponent(rightSelPwPanel, selPwScroll, 0, 1, 0.0, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(10, 5, 0, 5), GridBagConstraints.WEST,
				gridBagConstraints);
		
		addComponent(buttomPanel, leftPwPanel, 0, 0, 0.1, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.WEST,
				gridBagConstraints);

		addComponent(buttomPanel, btnJpanel, 0, 1, 0.1, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.CENTER,
				gridBagConstraints);

		addComponent(buttomPanel, rightSelPwPanel, 0, 2, 0.1, 0.0, 1, 1, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), GridBagConstraints.EAST,
				gridBagConstraints);

		
		rightPanel.setLayout(new BorderLayout());
		this.tunnelTopoPanel=new TunnelTopoPanel();
		rightPanel.add(tunnelTopoPanel, BorderLayout.CENTER);
		rightPanel.add(buttomPanel, BorderLayout.SOUTH);
		
		jSplitPane1.setRightComponent(rightPanel);
		setBtnListeners();

		int width = jSplitPane1.getWidth();
		jSplitPane1.setDividerLocation(width / 10 * 3);
	//	addelandialog = this;

		try {

			if (0 != elanId && !"".equals(elanId)) {
				initData(elanId); // 修改elan使初始化数据
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initData(Integer elanId) throws Exception {
		this.elanLabel.setText(ResourceUtil.srcStr(StringKeysLbl.LBL_UPDATE_ELAN));
		this.elanservice = getElanInfo(elanId);
		List<Integer> pwIdList = new ArrayList<Integer>();
		if (elanservice != null) {
			this.nameTextField.setText(this.elanservice.get(0).getName());
			this.isActivateCBox.setSelected(this.elanservice.get(0).getActiveStatus() == 1 ? true : false);

			for (ElanInfo elanInfo : elanservice) {
				pwIdList.add(elanInfo.getPwId());
			}

			pwInfoList = this.getPwInfoListByPwId(pwIdList);
			tunnelList = this.getTunnelByPw();
			siteIdAndTunnelsMap = getSiteIdAndTunnelsMap(tunnelList);
			showTopoByTunnel();
			// 初始化已选择的pw列表
			initPwList();
			// 初始化ac端口
			initAcList();
		}
	}

	private void initAcList() {
		Set<Integer> acIdSet = new HashSet<Integer>();
		List<Integer> acIdList = new ArrayList<Integer>();
		AcPortInfoService_MB acService = null;
		List<AcPortInfo> acInfoList = null;
		ControlKeyValue kv = null;
		try {
			acService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			for (ElanInfo elanInfo : elanservice) {
				acIdSet.add(elanInfo.getaAcId());
				acIdSet.add(elanInfo.getzAcId());
			}
			acIdList.addAll(acIdSet);
			acInfoList = acService.select(acIdList);

			for (AcPortInfo acInfo : acInfoList) {
				kv = new ControlKeyValue(String.valueOf(acInfo.getId()), "SiteId=" + acInfo.getSiteId() + "//" + "acId=" + acInfo.getId(), acInfo);
				acPortVector.add(kv);
			}
			portList.setListData(acPortVector);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(acService);
		}
	}

	private void initPwList() {
		ControlKeyValue kv = null;
		try {
			for (PwInfo pwinfo : pwInfoList) {
				kv = new ControlKeyValue(pwinfo.getPwId() + "", "SiteId" + "(" + pwinfo.getASiteId() + "," + pwinfo.getZSiteId() + ")" + "//"
						+ "pwId=" + pwinfo.getPwId(), pwinfo);
				selpwVector.add(kv);

			}

			selPwList.setListData(selpwVector);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			kv = null;
		}
	}
	/**
	 * 按钮事件
	 */
	private void setBtnListeners() {
		leftBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (pwList.getSelectedValue() != null) {
					Object obj = pwList.getSelectedValue();
					List<Integer> newSiteIdList = new ArrayList<Integer>();
					newSiteIdList.add(((PwInfo) ((ControlKeyValue) obj).getObject()).getASiteId());
					newSiteIdList.add(((PwInfo) ((ControlKeyValue) obj).getObject()).getZSiteId());

					List<Integer> oldSiteIdList = null;
					for (Object pw : selpwVector) {
						oldSiteIdList = new ArrayList<Integer>();
						oldSiteIdList.add(((PwInfo) ((ControlKeyValue) pw).getObject()).getASiteId());
						oldSiteIdList.add(((PwInfo) ((ControlKeyValue) pw).getObject()).getZSiteId());
						if (oldSiteIdList.contains(newSiteIdList)) {
							return;
						}
					}
					selpwVector.add(obj);
					pwVector.remove(obj);
					selPwList.setListData(selpwVector);
					pwList.setListData(pwVector);

				}
			}
		});

		rightBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (selPwList.getSelectedValue() != null) {
					Object obj = selPwList.getSelectedValue();
					selpwVector.remove(obj);
					pwVector.add(obj);
					pwList.setListData(pwVector);
					selPwList.setListData(selpwVector);

				}
			}
		});
	}

	private void addComponent(JPanel panel, JComponent component, int gridx, int gridy, double weightx, double weighty, int gridwidth,
			int gridheight, int fill, Insets insets, int anchor, GridBagConstraints gridBagConstraints) {
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

//	public static UpdateElanDialog getDialog() {
//		if (addelandialog == null) {
//			addelandialog = new UpdateElanDialog(new ElanBusinessPanel(), true, 0);
//		}
//		return addelandialog;
//	}

	private Map<Integer, List<Tunnel>> getSiteIdAndTunnelsMap(List<Tunnel> tunnelList) {
		Map<Integer, List<Tunnel>> map = new HashMap<Integer, List<Tunnel>>();
		Set<Integer> siteIdSet = new HashSet<Integer>();
		List<Tunnel> t = null;
		for (Tunnel tunnel : tunnelList) {
			siteIdSet.add(tunnel.getASiteId());
			siteIdSet.add(tunnel.getZSiteId());
		}
		for (Integer siteId : siteIdSet) {
			t = new ArrayList<Tunnel>();
			for (Tunnel tunnel : tunnelList) {
				if (siteId == tunnel.getASiteId() || siteId == tunnel.getZSiteId()) {
					t.add(tunnel);
				}
			}
			map.put(siteId, t);
		}
		return map;
	}

	/**
	 * 获取ElanInfo
	 * 
	 * @param elanId
	 * @throws Exception
	 */
	private List<ElanInfo> getElanInfo(int elanId) throws Exception {
		ElanInfoService_MB elanInfoservice = null;
		List<ElanInfo> elanInfoList = null;
		try {
			elanInfoservice = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
			elanInfoList = elanInfoservice.select(elanId);
			return elanInfoList;
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(elanInfoservice);
		}
	}

	@SuppressWarnings("unchecked")
	private void showTopoByTunnel() {
		TNetwork network = null;
		List<Element> elements = null;
		Set<Integer> siteIdSet = new HashSet<Integer>();
		try {
			for (PwInfo pw : pwInfoList) {
				siteIdSet.add(pw.getASiteId());
				siteIdSet.add(pw.getZSiteId());
			}
			tunnelTopoPanel.boxData(tunnelList);

			network = tunnelTopoPanel.getNetWork();
			elements = network.getDataBox().getAllElements();
			for (Element element : elements) {
				if (element instanceof Node) {
					Integer siteId = ((SiteInst) element.getUserObject()).getSite_Inst_Id();
					if (siteIdSet.contains(siteId)) {
						element.addAttachment("topoTitle");
					}
				}
			}
		} catch (NumberFormatException e) {
			ExceptionManage.dispose(e,this.getClass());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			network = null;
			elements = null;
		}
	}

	public List<Integer> getTunnelIdList() throws Exception {
		TunnelService_MB tunnelService = null;
		List<Integer> idList = new ArrayList<Integer>();
		try {
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			List<Tunnel> tunnelList = tunnelService.select();
			for (Tunnel tunnel : tunnelList) {
				idList.add(tunnel.getTunnelId());
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(tunnelService);
		}
		return idList;
	}

	/**
	 * 得到所有的Tunnel
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Tunnel> getAllTunnel() throws Exception {
		TunnelService_MB tunnelservice = null;
		List<Tunnel> tunnelList = null;
		try {
			tunnelservice = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnelList = tunnelservice.select();
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(tunnelservice);
		}
		return tunnelList;
	}

	/**
	 * 得到tunnel上有pw的所有tunnel
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Tunnel> getTunnelByPw() throws Exception {
		TunnelService_MB tunnelService = null;
		Set<Tunnel> tunnelSet = new HashSet<Tunnel>();
		List<Tunnel> tunnelList = new ArrayList<Tunnel>();
		try {
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			Tunnel tunnel = null;
			for (PwInfo info : pwInfoList) {
				tunnel = new Tunnel();
				tunnel.setTunnelId(info.getTunnelId());
				tunnelSet.addAll(tunnelService.select(tunnel));
			}
			tunnelList.addAll(tunnelSet);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(tunnelService);
		}
		return tunnelList;
	}

	/**
	 * 得到pw
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PwInfo> getPwInfoListByPwId(List<Integer> pwIdList) throws Exception {
		PwInfoService_MB service = null;
		List<PwInfo> pwInfoList = new ArrayList<PwInfo>();
		PwInfo pwInfo = null;
		try {
			service = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwInfo = new PwInfo();
			for (Integer pwId : pwIdList) {
				pwInfo.setPwId(pwId);
				pwInfoList.addAll(service.select(pwInfo));
			}
			tunnelIdAndPwInfoListMap = getTunnelIdAndPwInfoListMap(pwInfoList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
			pwInfo = null;
		}
		return pwInfoList;
	}

	/**
	 * tunnel与承载在tunnel上没有被其他业务所用的pw列表映射
	 * 
	 * @param pwInfoList
	 * @return
	 */
	public Map<Integer, List<PwInfo>> getTunnelIdAndPwInfoListMap(List<PwInfo> pwInfoList) {
		if (pwInfoList == null) {
			return null;
		}
		Map<Integer, List<PwInfo>> tunnelIdAndPwListMap = new HashMap<Integer, List<PwInfo>>();
		for (PwInfo pwinfo : pwInfoList) {
			Integer tunnelId = pwinfo.getTunnelId();
			List<PwInfo> pwList = null;
			if (tunnelIdAndPwListMap.get(tunnelId) == null) {
				pwList = new ArrayList<PwInfo>();
				for (PwInfo pw : pwInfoList) {
					if (tunnelId == pw.getTunnelId()) {
						pwList.add(pw);
					}
				}
			}
			tunnelIdAndPwListMap.put(tunnelId, pwList);

		}
		return tunnelIdAndPwListMap;
	}

	/**
	 * 下拉列表绑定
	 * 
	 * @param jComboBox
	 * @param defaultValue
	 * @throws Exception
	 */
	public void comboBoxData(JComboBox jComboBox, String defaultValue) throws Exception {
		List<Tunnel> tunnelList = null;
		DefaultComboBoxModel defaultComboBoxModel = null;
		TunnelService_MB tunnelservice = null;
		try {
			tunnelservice = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnelList = tunnelservice.select();
			defaultComboBoxModel = (DefaultComboBoxModel) jComboBox.getModel();
			defaultComboBoxModel.addElement(new ControlKeyValue(defaultValue, ""));
			for (Tunnel tunnel : tunnelList) {
				defaultComboBoxModel.addElement(new ControlKeyValue(tunnel.getTunnelId() + "", tunnel.getTunnelName(), null));
			}
			jComboBox.setModel(defaultComboBoxModel);
		} catch (Exception e) {
			throw e;
		} finally {
			defaultComboBoxModel = null;
			UiUtil.closeService_MB(tunnelservice);
			tunnelList = null;
		}
	}

	/**
	 * 下拉列表选中
	 * 
	 * @param jComboBox
	 *            下拉列表对象
	 * @param selectId
	 *            选中的id
	 */
	public  void comboBoxSelect(JComboBox jComboBox, String selectId) {
		for (int i = 0; i < jComboBox.getItemCount(); i++) {
			if (((ControlKeyValue) jComboBox.getItemAt(i)).getId().equals(selectId)) {
				jComboBox.setSelectedIndex(i);
				return;
			}
		}
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		elanLabel = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		okBtn = new javax.swing.JButton();
		cancelBtn = new javax.swing.JButton();
		jSplitPane1 = new javax.swing.JSplitPane();
		jPanel3 = new javax.swing.JPanel();
		nameLabel = new javax.swing.JLabel();
		nameTextField = new javax.swing.JTextField();
		portLabel = new javax.swing.JLabel();
		activateStatusLabel = new javax.swing.JLabel();
		isActivateCBox = new javax.swing.JCheckBox();
		jScrollPane1 = new javax.swing.JScrollPane();
		portList = new javax.swing.JList();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		elanLabel.setFont(new java.awt.Font("黑体", 0, 14));
		elanLabel.setText("\u65b0\u5efaELAN");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(elanLabel).addContainerGap(714, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(elanLabel).addContainerGap(18, Short.MAX_VALUE)));

		jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		okBtn.setText("\u786e\u8ba4");
		okBtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				okBtnActionPerformed(evt);
			}
		});

		cancelBtn.setText("\u53d6\u6d88");
		cancelBtn.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelBtnActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel2Layout.createSequentialGroup().addContainerGap(657, Short.MAX_VALUE).addComponent(okBtn).addGap(18, 18, 18).addComponent(
						cancelBtn).addGap(7, 7, 7)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel2Layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(
						jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(okBtn).addComponent(cancelBtn))
						.addContainerGap()));

		jSplitPane1.setPreferredSize(new java.awt.Dimension(482, 1002));

		jPanel3.setPreferredSize(new java.awt.Dimension(200, 100));

		nameLabel.setFont(new java.awt.Font("黑体", 0, 14));
		nameLabel.setText("\u540d   \u79f0");

		portLabel.setFont(new java.awt.Font("黑体", 0, 14));
		portLabel.setText("\u7aef   \u53e3");

		activateStatusLabel.setFont(new java.awt.Font("黑体", 0, 14));
		activateStatusLabel.setText("\u6fc0\u6d3b\u72b6\u6001");

		isActivateCBox.setFont(new java.awt.Font("黑体", 0, 14));
		isActivateCBox.setText("\u662f\u5426\u6fc0\u6d3b");

		portList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = {};

			@Override
			public int getSize() {
				return strings.length;
			}

			@Override
			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane1.setViewportView(portList);

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addContainerGap().addGroup(
						jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(nameLabel).addComponent(portLabel,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(
						jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addComponent(jScrollPane1,
								javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE).addComponent(nameTextField)).addContainerGap()).addGroup(
				jPanel3Layout.createSequentialGroup().addComponent(activateStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(isActivateCBox).addGap(56, 56, 56)));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				jPanel3Layout.createSequentialGroup().addGap(27, 27, 27).addGroup(
						jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(nameLabel).addComponent(
								nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(35, 35, 35).addGroup(
						jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(portLabel).addComponent(
								jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)).addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE).addGroup(
						jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(activateStatusLabel).addComponent(
								isActivateCBox)).addGap(185, 185, 185)));

		jSplitPane1.setLeftComponent(jPanel3);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);		
		
		 layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(elanLabel,
		 javax.swing.GroupLayout.DEFAULT_SIZE,
		 javax.swing.GroupLayout.DEFAULT_SIZE,
		 Short.MAX_VALUE).addComponent(jPanel2,
		 javax.swing.GroupLayout.DEFAULT_SIZE,
		 javax.swing.GroupLayout.DEFAULT_SIZE,
		 Short.MAX_VALUE).addComponent(jSplitPane1,
		 javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE));
		 layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(elanLabel,
		 javax.swing.GroupLayout.PREFERRED_SIZE,
		 javax.swing.GroupLayout.DEFAULT_SIZE,
		 javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jSplitPane1,
		 javax.swing.GroupLayout.DEFAULT_SIZE, 487,
		 Short.MAX_VALUE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jPanel2,
		 javax.swing.GroupLayout.PREFERRED_SIZE,
		 javax.swing.GroupLayout.DEFAULT_SIZE,
		 javax.swing.GroupLayout.PREFERRED_SIZE)));

		pack();
	}// </editor-fold>


	private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {

		if (nameTextField.getText().trim().length() == 0) {
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL));
			return;
		}
		try {
			// 收集数据

			for (ElanInfo elanInfo : elanservice) {
				elanInfo.setName(nameTextField.getText());
				elanInfo.setActiveStatus(isActivateCBox.isSelected() ? 1 : 0);
			}


			DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
			this.dispose();

			if (null != this.elanBusinessPanel) {
				this.elanBusinessPanel.getController().refresh();
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			
		}
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				UpdateElanDialog dialog = new UpdateElanDialog(new javax.swing.JFrame(), true);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JLabel activateStatusLabel;
	private javax.swing.JButton cancelBtn;
	private javax.swing.JLabel elanLabel;
	private javax.swing.JCheckBox isActivateCBox;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JSplitPane jSplitPane1;
	private javax.swing.JLabel nameLabel;
	private javax.swing.JTextField nameTextField;
	private javax.swing.JButton okBtn;
	private javax.swing.JLabel portLabel;
	private javax.swing.JList portList;
	// End of variables declaration//GEN-END:variables

	private JPanel rightPanel;
	private JPanel buttomPanel = new JPanel();
	private JList pwList = new JList();

	private JList selPwList = new JList();
	private final JButton leftBtn = new JButton();
	private final JButton rightBtn = new JButton();

}