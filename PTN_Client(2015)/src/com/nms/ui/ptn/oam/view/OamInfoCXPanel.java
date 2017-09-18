package com.nms.ui.ptn.oam.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.ui.manager.CheckingUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnPanel;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.oam.view.dialog.OamInfoDialog;

/**
 * CX OAM 配置面板
 * 
 * @author dzy
 * 
 */
public class OamInfoCXPanel extends PtnPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -61622206476103381L;

	private final int LINEHEIGHT = 30; // 每行的行高
	private int[] LINEHEIGHTS = new int[9]; // 高度控制数组
	private String busiType; // 业务类型
	private String neDirect; // 网元方向
	private int isSingle; // 业务侧类型 1=单网元 0=网络层
	private OamInfoDialog oamInfoDialog; // 主窗口
	private int height; // 高度
	private int id; // Id

	/**
	 * 创建一个实例
	 * 
	 * @param oamInfoDialog
	 *            主窗口
	 * @param busiType
	 *            业务类型
	 * @param neDirect
	 *            区分网元AZ端
	 * @param isSingle
	 *            业务侧类型 1=单网元 0=网络层
	 */
	public OamInfoCXPanel(OamInfoDialog oamInfoDialog, String busiType,
			String neDirect, int isSingle) {

		try {
			this.busiType = busiType;
			this.neDirect = neDirect;
			this.isSingle = isSingle;
			this.oamInfoDialog = oamInfoDialog;
			this.initComponents(busiType);
			this.setLayOut(busiType);
			this.addListener();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * 初始化控件
	 * 
	 * @param BusiType
	 *            业务类型
	 * @throws Exception
	 */
	public void initComponents(String BusiType) throws Exception {

		int count = 7;
		this.lblPromptInfo = new JLabel();
		this.lblMegId = new JLabel("MEGID");
		this.txtMegId = new PtnTextField(false, PtnTextField.TYPE_STRING, 7,this.lblPromptInfo, this.oamInfoDialog.getBtnSave(),this.oamInfoDialog);
		this.txtMegId.setCheckingMaxValue(true);
		this.txtMegId.setMaxValue(7);
		this.txtMegId.setCheckingMinValue(true);
		this.txtMegId.setMinValue(0);
		this.lblMepId = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_MEPID));
		
		this.txtMepId = new PtnTextField(true, PtnTextField.TYPE_INT, 4,this.lblPromptInfo, this.oamInfoDialog.getBtnSave(),this.oamInfoDialog);
		this.txtMepId.setText("2");
		this.txtMepId.setCheckingMaxValue(true);
		this.txtMepId.setMaxValue(8191);
		this.txtMepId.setCheckingMinValue(true);
		this.txtMepId.setMinValue(0);
		
		this.lblRemoteMepId = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_FAR_POINT_ID));
		this.txtRemoteMepId = new PtnTextField(true, PtnTextField.TYPE_INT, 4,this.lblPromptInfo, this.oamInfoDialog.getBtnSave(),this.oamInfoDialog);
		this.txtRemoteMepId.setText("3");
		this.txtRemoteMepId.setCheckingMaxValue(true);
		this.txtRemoteMepId.setMaxValue(8191);
		this.txtRemoteMepId.setCheckingMinValue(true);
		this.txtRemoteMepId.setMinValue(0);

		this.chbNELock = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_LCK));
		this.chbLMEnable = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_LM));
		this.chbDMEnable = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_DM));
		this.chbCSFEnable = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_CSF));
		this.lblLoopBackOverTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_OAM_LOOP_TIME));
		this.txtLoopBackOverTime = new PtnTextField(false,PtnTextField.TYPE_INT, 2, this.lblPromptInfo,this.oamInfoDialog.getBtnSave(), this.oamInfoDialog);

		if (EServiceType.PW.toString().equals(BusiType))
			count++;

		if (1 == this.isSingle) {
			count++;
		}
		
		// 动态加载高度
		this.LINEHEIGHTS[0] = 20;
		this.height += 50;

		for (int i = 1; i < count; i++) {
			this.LINEHEIGHTS[i] = this.LINEHEIGHT;
			this.height += 30;
		}

	}

	/**
	 * 页面布局
	 * 
	 * @param BusiType
	 *            业务类型
	 */
	public void setLayOut(String BusiType) {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 100, 80 };
		componentLayout.columnWeights = new double[] { 0, 0.1 };
		componentLayout.rowHeights = LINEHEIGHTS;
		componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0, 0,
				0, 0, 0, 0 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 5, 0, 5);

		super.addComponent(this, lblPromptInfo, 0, 0, 0, 0, 2, 1, c.fill,c.insets, c.anchor, c);
		super.addComponent(this, lblMegId, 0, 1, 0, 0, 1, 1, c.fill, c.insets,c.anchor, c);
		super.addComponent(this, txtMegId, 1, 1, 0, 0, 1, 1, c.fill, c.insets,c.anchor, c);
		super.addComponent(this, lblMepId, 0, 2, 0, 0, 1, 1, c.fill, c.insets,c.anchor, c);
		super.addComponent(this, txtMepId, 1, 2, 0, 0, 1, 1, c.fill, c.insets,c.anchor, c);
		if (1 == this.isSingle) {
			super.addComponent(this, lblRemoteMepId, 0, 3, 0, 0, 1, 1, c.fill,c.insets, c.anchor, c);
			super.addComponent(this, txtRemoteMepId, 1, 3, 0, 0, 1, 1, c.fill,c.insets, c.anchor, c);
		}
		super.addComponent(this, this.lblLoopBackOverTime, 0,3 + this.isSingle, 0, 0, 1, 1, c.fill, c.insets, c.anchor, c);
		super.addComponent(this, this.txtLoopBackOverTime, 1,3 + this.isSingle, 0, 0, 1, 1, c.fill, c.insets, c.anchor, c);
		super.addComponent(this, this.chbNELock, 0, 4 + this.isSingle, 0, 0, 1,1, c.fill, c.insets, c.anchor, c);
		super.addComponent(this, this.chbLMEnable, 0, 5 + this.isSingle, 0, 0,1, 1, c.fill, c.insets, c.anchor, c);
		super.addComponent(this, this.chbDMEnable, 0, 6 + this.isSingle, 0, 0,1, 1, c.fill, c.insets, c.anchor, c);

		// 如果是PW业务添加csf 属性
		if (EServiceType.PW.toString().equals(BusiType)) {
			super.addComponent(this, this.chbCSFEnable, 0, 7 + this.isSingle,0, 0, 1, 1, c.fill, c.insets, c.anchor, c);
		}
	}

	/**
	 * 获取页面信息
	 */
	public void getCXOam(OamInfo oamInfo) {
		if (1 == this.isSingle&& Integer.parseInt(this.txtMepId.getText()) == (Integer.parseInt(this.txtRemoteMepId.getText()))) {
			DialogBoxUtil.succeedDialog(this.oamInfoDialog,ResourceUtil.srcStr(StringKeysTip.TIP_MEPID_DIFFERENT));
			return;
		}
		if(null == oamInfo.getOamMep())
			oamInfo.setOamMep(new OamMepInfo());
		oamInfo.getOamMep().setId(this.id);
		oamInfo.getOamMep().setMegId(Integer.parseInt(this.txtMegId.getText()));
		oamInfo.getOamMep().setLocalMepId(Integer.parseInt(this.txtMepId.getText()));
		oamInfo.getOamMep().setRemoteMepId((Integer.parseInt(this.txtRemoteMepId.getText())));
		oamInfo.getOamMep().setLpbOutTime(Integer.parseInt(this.txtLoopBackOverTime.getText()));
		oamInfo.getOamMep().setLck(this.chbNELock.isSelected());
		oamInfo.getOamMep().setLm(this.chbLMEnable.isSelected());
		oamInfo.getOamMep().setDm(this.chbDMEnable.isSelected());
		oamInfo.getOamMep().setCsfEnable(this.chbCSFEnable.isSelected());
		if ("a".equals(this.neDirect)) {
			oamInfo.setOamType(OamTypeEnum.AMEP);
		} else if ("z".equals(this.neDirect)) {
			oamInfo.setOamType(OamTypeEnum.ZMEP);
		} else {
			oamInfo.setOamType(OamTypeEnum.MEP);
		}
		oamInfo.setNeDirect(neDirect);
		if (EServiceType.SECTION.toString().equals(this.busiType)) {
			oamInfo.getOamMep().setObjType(EServiceType.SECTION.toString());
		} else if (EServiceType.TUNNEL.toString().equals(this.busiType)) {
			oamInfo.getOamMep().setObjType(EServiceType.TUNNEL.toString());
		} else if (EServiceType.PW.toString().equals(this.busiType)) {
			oamInfo.getOamMep().setObjType(EServiceType.PW.toString());
		} else if (EServiceType.ELINE.toString().equals(this.busiType)) {
			oamInfo.getOamMep().setObjType(EServiceType.ELINE.toString());
		}
	}

	/**
	 * 监听事件
	 */
	private void addListener() {
		this.txtLoopBackOverTime.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (null != txtLoopBackOverTime.getText()
						&& !"".equals(txtLoopBackOverTime.getText())
						&& CheckingUtil.checking(txtLoopBackOverTime.getText(),
								CheckingUtil.NUMBER_REGULAR)
						&& txtLoopBackOverTime.getText().length() < 3) {
					if (Integer.parseInt(txtLoopBackOverTime.getText()) < 3
							|| Integer.parseInt(txtLoopBackOverTime.getText()) > 10) {
						lblPromptInfo.setText(ResourceUtil
								.srcStr(StringKeysTip.TIP_LOOPBACKOVERTIME_SCOPE));
						lblPromptInfo.setForeground(Color.RED);
						oamInfoDialog.getBtnSave().setEnabled(false);
					}
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {

			}
		});
	}

	private JLabel lblPromptInfo; // 提示信息
	private JLabel lblMegId; // MegId label
	private PtnTextField txtMegId; // MegId
	private JLabel lblMepId; // 维护点ID label
	private PtnTextField txtMepId; // 维护点ID
	private JLabel lblRemoteMepId; // 对端维护点ID label
	private PtnTextField txtRemoteMepId; // 对端维护点ID
	private JLabel lblLoopBackOverTime; // 环回超时时间 label
	private PtnTextField txtLoopBackOverTime; // 环回超时时间
	private JCheckBox chbNELock; // 是否锁定
	private JCheckBox chbLMEnable; // LM使能
	private JCheckBox chbDMEnable; // DM使能
	private JCheckBox chbCSFEnable; // CSF使能

	public PtnTextField getTxtMegId() {
		return txtMegId;
	}

	public void setTxtMegId(PtnTextField txtMegId) {
		this.txtMegId = txtMegId;
	}

	public PtnTextField getTxtMepId() {
		return txtMepId;
	}

	public void setTxtMepId(PtnTextField txtMepId) {
		this.txtMepId = txtMepId;
	}

	public JCheckBox getChbNELock() {
		return chbNELock;
	}

	public void setChbNELock(JCheckBox chbNELock) {
		this.chbNELock = chbNELock;
	}

	public JCheckBox getChbLMEnable() {
		return chbLMEnable;
	}

	public void setChbLMEnable(JCheckBox chbLMEnable) {
		this.chbLMEnable = chbLMEnable;
	}

	public JCheckBox getChbDMEnable() {
		return chbDMEnable;
	}

	public void setChbDMEnable(JCheckBox chbDMEnable) {
		this.chbDMEnable = chbDMEnable;
	}

	public JCheckBox getChbCSFEnable() {
		return chbCSFEnable;
	}

	public void setChbCSFEnable(JCheckBox chbCSFEnable) {
		this.chbCSFEnable = chbCSFEnable;
	}

	public PtnTextField getTxtLoopBackOverTime() {
		return txtLoopBackOverTime;
	}

	public void setTxtLoopBackOverTime(PtnTextField txtLoopBackOverTime) {
		this.txtLoopBackOverTime = txtLoopBackOverTime;
	}

	public JLabel getLblPromptInfo() {
		return lblPromptInfo;
	}

	public void setLblPromptInfo(JLabel lblPromptInfo) {
		this.lblPromptInfo = lblPromptInfo;
	}

	public PtnTextField getTxtRemoteMepId() {
		return txtRemoteMepId;
	}

	public void setTxtRemoteMepId(PtnTextField txtRemoteMepId) {
		this.txtRemoteMepId = txtRemoteMepId;
	}

	public int getHeight() {
		return height;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
