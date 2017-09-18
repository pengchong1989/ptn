package com.nms.ui.ptn.business.testoam.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EServiceType;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.business.testoam.TestOamBusinessController;

public class TestOamMainDialog extends PtnDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4984407263775620337L;
	private JTabbedPane tabbedPane; // tab控件
	private PtnButton btnSave; // 确定按钮
	private JButton btnCanel; // 取消按钮
	private JPanel panelBtn; // 按钮布局
	private List<OamInfo> oamList;//主动OAM
	private Segment segment;
	private Tunnel tunnel;
	private PwInfo pw;
	private String type;//类型 段层/tunnel层/pw层
	private SectionOamBusinessDialog aSectionDialog;
	private SectionOamBusinessDialog zSectionDialog;
	private TunnelOamBusinessDialog aTunnelDialog;
	private TunnelOamBusinessDialog zTunnelDialog;
	private PwOamBusinessDialog aPwDialog;
	private PwOamBusinessDialog zPwDialog;
	private AbstractController controller;

	public TestOamMainDialog(List<OamInfo> oamList, Object s, String type) {
		try {
			this.oamList = oamList;
			if(s instanceof Segment){
				segment = (Segment) s;
			}
			if(s instanceof Tunnel){
				tunnel = (Tunnel) s;
			}
			if(s instanceof PwInfo){
				pw = (PwInfo) s;
			}
			this.type = type;
			this.setModal(true);
			this.initComponent();
			this.setLayout();
			this.initData();
			this.addListener();
			this.controller = new TestOamBusinessController(this);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initComponent() {
		this.tabbedPane = new JTabbedPane();
		this.btnCanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.btnSave = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),false);
		this.panelBtn = new JPanel();
	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
		this.setButtonLayout();

		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] {700 };
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
		componentLayout.columnWidths = new int[] { 400, 50 };
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

	/**
	 * 初始化数据
	 * 
	 * @throws Exception
	 */
	private void initData() throws Exception {
		if(type.equals(EServiceType.SECTION.toString())){
			if(oamList.get(0).getOamMep().getSiteId() == this.segment.getASITEID()){
				this.aSectionDialog = new SectionOamBusinessDialog(segment, oamList.get(0), this);
				this.zSectionDialog = new SectionOamBusinessDialog(segment, oamList.get(1), this);
			}else{
				this.aSectionDialog = new SectionOamBusinessDialog(segment, oamList.get(1), this);
				this.zSectionDialog = new SectionOamBusinessDialog(segment, oamList.get(0), this);
			}
			// 给TAB添加panel
			this.tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.LBL_A_OAM), this.aSectionDialog);
			this.tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.LBL_Z_OAM), this.zSectionDialog);
		}
		if(type.equals(EServiceType.TUNNEL.toString())){
			if(oamList.get(0).getOamMep().getSiteId() == this.tunnel.getASiteId()){
				this.aTunnelDialog = new TunnelOamBusinessDialog(tunnel, oamList.get(0), this);
				this.zTunnelDialog = new TunnelOamBusinessDialog(tunnel, oamList.get(1), this);
			}else{
				this.aTunnelDialog = new TunnelOamBusinessDialog(tunnel, oamList.get(1), this);
				this.zTunnelDialog = new TunnelOamBusinessDialog(tunnel, oamList.get(0), this);
			}
			// 给TAB添加panel
			this.tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.LBL_A_OAM), this.aTunnelDialog);
			this.tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.LBL_Z_OAM), this.zTunnelDialog);
		}
		if(type.equals(EServiceType.PW.toString())){
			if(oamList.get(0).getOamMep().getSiteId() == this.pw.getASiteId()){
				this.aPwDialog = new PwOamBusinessDialog(pw, oamList.get(0), this);
				this.zPwDialog = new PwOamBusinessDialog(pw, oamList.get(1), this);
			}else{
				this.aPwDialog = new PwOamBusinessDialog(pw, oamList.get(1), this);
				this.zPwDialog = new PwOamBusinessDialog(pw, oamList.get(0), this);
			}
			// 给TAB添加panel
			this.tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.LBL_A_OAM), this.aPwDialog);
			this.tabbedPane.add(ResourceUtil.srcStr(StringKeysLbl.LBL_Z_OAM), this.zPwDialog);
		}
	}

	private void addListener() {
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

	protected void btnSaveListener() {
		//检查界面数据是否填写完整
		boolean aFlag = false;
		boolean zFlag = false;
		String aResult = "";
		String zResult = "";
		if(type.equals(EServiceType.SECTION.toString())){
			aFlag = this.aSectionDialog.checkIsFull();
			zFlag = this.zSectionDialog.checkIsFull();
		}
		if(type.equals(EServiceType.TUNNEL.toString())){
			aFlag = this.aTunnelDialog.checkIsFull();
			zFlag = this.zTunnelDialog.checkIsFull();
		}
		if(type.equals(EServiceType.PW.toString())){
			aFlag = this.aPwDialog.checkIsFull();
			zFlag = this.zPwDialog.checkIsFull();
		}
		
		if(aFlag == true && zFlag == true){
			try {
				//收集界面数据并下发配置
				if(type.equals(EServiceType.SECTION.toString())){
					aResult = this.aSectionDialog.collectData();
					zResult = this.zSectionDialog.collectData();
				}
				if(type.equals(EServiceType.TUNNEL.toString())){
					aResult = this.aTunnelDialog.collectData();
					zResult = this.zTunnelDialog.collectData();
				}
				if(type.equals(EServiceType.PW.toString())){
					aResult = this.aPwDialog.collectData();
					zResult = this.zPwDialog.collectData();
				}
				
				if(aResult.contains(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)) &&
						zResult.contains(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
					String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+",";
					if(aResult.contains(ResultString.NOT_ONLINE_SUCCESS)){
						aResult = aResult.substring(5, aResult.length());
						result += aResult.substring(0, aResult.length()-6);
					}
					if(zResult.contains(ResultString.NOT_ONLINE_SUCCESS)){
						if(aResult.contains(ResultString.NOT_ONLINE_SUCCESS)){
							result += "、";
						}
						zResult = zResult.substring(5, zResult.length());
						result += zResult.substring(0, zResult.length()-6);
					}
					if(aResult.contains(ResultString.NOT_ONLINE_SUCCESS) 
							|| zResult.contains(ResultString.NOT_ONLINE_SUCCESS)){
						result += ResultString.NOT_ONLINE_SUCCESS;
					}else{
						result = result.substring(0, result.length()-1);
					}				
					DialogBoxUtil.succeedDialog(this, result);
//					if(type.equals(EServiceType.SECTION.toString())){
//						UiUtil.insertOperationLog(EOperationLogType.TESTOAMSEGEMENTUPDATE.getValue(), result);
//					}
//					if(type.equals(EServiceType.TUNNEL.toString())){
//						UiUtil.insertOperationLog(EOperationLogType.TESTOAMTUNNELUPDATE.getValue(), result);
//					}
//					if(type.equals(EServiceType.PW.toString())){
//						UiUtil.insertOperationLog(EOperationLogType.TESTOAMPWUPDATE.getValue(), result);
//					}
				}else{
					DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL));
				}
				this.dispose();
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
		}
		
	}

	public PtnButton getBtnSave() {
		return btnSave;
	}

	public JButton getBtnCanel() {
		return btnCanel;
	}
}
