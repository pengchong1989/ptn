package com.nms.ui.ptn.ne.eline.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EPwType;
import com.nms.db.enums.EServiceType;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.ptn.qos.QosInfoService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.AutoNamingUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.Tools;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.VerifyNameUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.safety.roleManage.RootFactory;

public class ElineEditDialog extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4874286301782700229L;
	private ElinePanel elinePanel;
	private ElineInfo elineInfo;
	private AcPortInfo eLineAc;
	public ElineEditDialog(ElineInfo elineInfo, ElinePanel elinePanel) {
		try {
			this.elineInfo = elineInfo;
			this.setElinePanel(elinePanel);
			this.initComponent();
			this.setLayout();
			this.addListener();
			this.initData();
			UiUtil.showWindow(this, 400, 310);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 初始化数据
	 * @throws Exception 
	 */
	private void initData() throws Exception {
		Tools tool = new Tools();
		AcPortInfoService_MB acInfoServiceMB = null;
		try {
			super.getComboBoxDataUtil().initPortData(this.cmbPort);
			this.initPwData();
			super.getComboBoxDataUtil().initAcData(this.cmbPort, this.cmbAc);
			if (null == this.elineInfo) {
				this.elineInfo = new ElineInfo();
				this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_ELINE));
			} else {
				this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_ELINE));
				this.chbActivate.setSelected(this.elineInfo.getActiveStatus()==1?true:false);
				this.txtName.setText(this.elineInfo.getName());
				super.getComboBoxDataUtil().comboBoxSelect(this.cmbPw,this.elineInfo.getPwId()+"");
				acInfoServiceMB = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
				if(0 != this.elineInfo.getaAcId()){
					this.eLineAc = acInfoServiceMB.selectById(this.elineInfo.getaAcId());
				}else{
					this.eLineAc = acInfoServiceMB.selectById(this.elineInfo.getzAcId());
				}
				//区分端口类型初始化端口
//				UiUtil.comboBoxSelect(this.cmbPort, eLineAc);
				tool.initPortAndLagByAcForCMB(this.cmbPort,this.eLineAc); 
//				//将修改对应的ac添加到对应的AC下拉列表中
				if(!super.getComboBoxDataUtil().isExistSameOption(cmbAc,this.eLineAc.getId()+"")){
					DefaultComboBoxModel defaultComboBoxModel = (DefaultComboBoxModel) this.cmbAc.getModel();
					defaultComboBoxModel.addElement(new ControlKeyValue(this.eLineAc.getId() + "", this.eLineAc.getName(), eLineAc));
				}
				super.getComboBoxDataUtil().comboBoxSelect(this.cmbAc,this.eLineAc.getId()+"");
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(acInfoServiceMB);
		}
	}

	/**
	 * 添加监听
	 */
	private void addListener() {
		this.btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ElineEditDialog.this.dispose();
			}
		});
		jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonActionPerformed(evt);
			}
		});
		this.cmbPort.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				if (evt.getStateChange() == 1) {
					try {
						getComboBoxDataUtil().initAcData(cmbPort, cmbAc);
						addAcForUpdate();
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				}
			}
		});

		this.btnSave.addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					saveInfo();
				} catch (Exception e1) {
					ExceptionManage.dispose(e1,this.getClass());
				}
			}
			@Override
			public boolean checking() {
				return check();
			}
		});
	}
	
	private boolean check(){
		boolean flag = false;
		String beforeName = null;
		QosInfoService_MB qosInfoServiceMB = null;
		ControlKeyValue controlKeyValue_ac = null;
		ControlKeyValue controlKeyValue_pw = null;
		PwInfo pwinfo = null;
		try {
			if (!this.isFull()) {
				return false;
			}
			if (this.elineInfo.getId() != 0) {
				beforeName = this.elineInfo.getName();
			}
			
			VerifyNameUtil verifyNameUtil=new VerifyNameUtil();
			if (verifyNameUtil.verifyNameBySingle(EServiceType.ELINE.getValue(), this.txtName.getText().trim(), beforeName, ConstantUtil.siteId)) {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST));
				return false;
			}
			
			//验证pw和ac的qos是否匹配。
			controlKeyValue_ac = (ControlKeyValue) this.cmbAc.getSelectedItem();
			controlKeyValue_pw = (ControlKeyValue) this.cmbPw.getSelectedItem();
			pwinfo = (PwInfo) controlKeyValue_pw.getObject();
			qosInfoServiceMB=(QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
			List<AcPortInfo> acPortInfoList=new ArrayList<AcPortInfo>();
			acPortInfoList.add((AcPortInfo) controlKeyValue_ac.getObject());
			if(!qosInfoServiceMB.checkPwAndAcQos(pwinfo, pwinfo.getQosList(),acPortInfoList)){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_PW_AC_QOS_NOT_MATCHING));
				return false;
			}
			flag = true;
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally{
			UiUtil.closeService_MB(qosInfoServiceMB);
		}
		return flag;
	}
	private void initPwData() throws Exception {
		PwInfoService_MB pwInfoServiceMB = null;
		List<PwInfo> pwInfoList = null;
		DefaultComboBoxModel defaultComboBoxModel = null;
		PwInfo pwInfoSel;
		try {
			pwInfoSel = new PwInfo();
			pwInfoServiceMB = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwInfoList = pwInfoServiceMB.getAvailable(ConstantUtil.siteId,EPwType.ETH);

			defaultComboBoxModel = new DefaultComboBoxModel();
			if(null!=this.elineInfo&&this.elineInfo.getPwId()>0){
				pwInfoSel.setPwId(this.elineInfo.getPwId());
				pwInfoSel = pwInfoServiceMB.selectBypwid_notjoin(pwInfoSel);
				pwInfoList.add(pwInfoSel);
			}
			
			if (null != pwInfoList && pwInfoList.size() > 0) {
				for (PwInfo pwInfo : pwInfoList) {
					defaultComboBoxModel.addElement(new ControlKeyValue(pwInfo.getPwId() + "", pwInfo.getPwName(), pwInfo));
				}
			}
			this.cmbPw.setModel(defaultComboBoxModel);

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(pwInfoServiceMB);
			pwInfoList = null;
			defaultComboBoxModel = null;
		}
	}

	// 自动命名事件
	private void jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			ElineInfo eline = new ElineInfo();
			eline.setIsSingle(1);
			eline.setaSiteId(ConstantUtil.siteId);
			AutoNamingUtil autoNamingUtil=new AutoNamingUtil();
			String autoNaming = (String) autoNamingUtil.autoNaming(eline, null, null);
			txtName.setText(autoNaming);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 保存
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void saveInfo() throws Exception {

		ControlKeyValue controlKeyValue_port = null;
		ControlKeyValue controlKeyValue_ac = null;
		ControlKeyValue controlKeyValue_pw = null;
		PwInfo pwinfo = null;
		DispatchUtil elineDispatch = null;
		String resultStr = null;
		AcPortInfoService_MB acPortInfoServiceMB = null;
		ElineInfoService_MB elineService = null;
		try {
			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			acPortInfoServiceMB = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			controlKeyValue_port = (ControlKeyValue) this.cmbPort.getSelectedItem();
			controlKeyValue_ac = (ControlKeyValue) this.cmbAc.getSelectedItem();
			controlKeyValue_pw = (ControlKeyValue) this.cmbPw.getSelectedItem();
			pwinfo = (PwInfo) controlKeyValue_pw.getObject();
			
			this.elineInfo.setName(this.txtName.getText().trim());
			this.elineInfo.setActiveStatus(this.chbActivate.isSelected() == true ? EActiveStatus.ACTIVITY.getValue() : EActiveStatus.UNACTIVITY.getValue());
			this.elineInfo.setPwId(pwinfo.getPwId());
			this.elineInfo.setPwName(pwinfo.getPwName());
			this.elineInfo.setIsSingle(1);
			this.elineInfo.setServiceType(EServiceType.ELINE.getValue());
			this.elineInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
			this.elineInfo.setCreateUser(ConstantUtil.user.getUser_Name());

			// 判断是a还是z端
			if (pwinfo.getASiteId() == ConstantUtil.siteId) {
				this.elineInfo.setaSiteId(ConstantUtil.siteId);
				this.elineInfo.setaAcId(Integer.parseInt(controlKeyValue_ac.getId()));
				this.elineInfo.setAportId(Integer.parseInt(controlKeyValue_port.getId()));
				this.elineInfo.setaAcName(((AcPortInfo)controlKeyValue_ac.getObject()).getName());
			}else{
				this.elineInfo.setzSiteId(ConstantUtil.siteId);
				this.elineInfo.setzAcId(Integer.parseInt(controlKeyValue_ac.getId()));
				this.elineInfo.setZportId(Integer.parseInt(controlKeyValue_port.getId()));
				this.elineInfo.setzAcName(((AcPortInfo)controlKeyValue_ac.getObject()).getName());
			}
			
			//如果修改a段ac 给BeforeAAc赋值
//			if(0!=this.elineInfo.getaAcId()&&this.elineInfo.getaAcId()!=Integer.parseInt(controlKeyValue_ac.getId())){
////				this.elineInfo.setBeforeAAc(acInfoService.selectById(this.elineInfo.getaAcId()));
//				beforeAcportList = new ArrayList<AcPortInfo>();
//				beforeAcportList.add(acInfoService.selectById(this.elineInfo.getaAcId()));
//				this.elineInfo.setBeforeAAcList(beforeAcportList);
//				this.elineInfo.setAction(1);
//			}
			// this.elineInfo.setzSiteId(pwinfo.getZSiteId());
			// } else {
			// this.elineInfo.setzSiteId(ConstantUtil.siteId);
			// this.elineInfo.setzAcId(Integer.parseInt(controlKeyValue_ac.getId()));
			// this.elineInfo.setZportId(Integer.parseInt(controlKeyValue_port.getId()));
			// this.elineInfo.setaSiteId(pwinfo.getASiteId());
			// }

			elineDispatch = new DispatchUtil(RmiKeys.RMI_ELINE);
			if (this.elineInfo.getId() == 0) {
				resultStr = elineDispatch.excuteInsert(this.elineInfo);
				AddOperateLog.insertOperLog(btnSave, EOperationLogType.ELINEINSERT.getValue(), resultStr, 
						null, elineInfo, elineInfo.getaSiteId()==0?elineInfo.getzSiteId():elineInfo.getaSiteId(), elineInfo.getName(), "eline");
			} else {
				ElineInfo elineBefore = new ElineInfo();
				elineBefore.setId(elineInfo.getId());
				elineBefore = ((List<ElineInfo>)elineService.selectElineByCondition(elineBefore)).get(0);
				elineBefore.setaAcName(acPortInfoServiceMB.selectById(elineBefore.getaAcId()).getName());
				elineBefore.setzAcName(acPortInfoServiceMB.selectById(elineBefore.getzAcId()).getName());
				resultStr = elineDispatch.excuteUpdate(this.elineInfo);
				elineBefore.setPwName(pwinfo.getPwName());
				AddOperateLog.insertOperLog(btnSave, EOperationLogType.ELINEUPDATE.getValue(), resultStr, 
						elineBefore, elineInfo, elineInfo.getaSiteId()==0?elineInfo.getzSiteId():elineInfo.getaSiteId(), elineInfo.getName(), "eline");
			}
			DialogBoxUtil.succeedDialog(this, resultStr);
			//添加日志记录
			int operationResult=0;
			if(resultStr.contains(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				operationResult=1;
			}else{
				operationResult=2;
			}
			btnSave.setResult(operationResult);

			this.elinePanel.getController().refresh();
			this.dispose();

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(acPortInfoServiceMB);
		}
	}

	/**
	 * 验证是否填写完整
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean isFull() throws Exception {

		boolean flag = true;
		try {
			if (null == this.cmbPort.getSelectedItem()) {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE));
				flag = false;
			} else if (null == this.cmbAc.getSelectedItem()) {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_AC));
				flag = false;
			} else if (null == this.cmbPw.getSelectedItem()) {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_PW));
				flag = false;
			}

		} catch (Exception e) {
			throw e;
		}
		return flag;
	}

	/**
	 * 初始化控件
	 * 
	 * @throws Exception
	 */
	private void initComponent() throws Exception {
		this.lblMessage = new JLabel();
//		this.btnSave = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),false);
		btnSave = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true,RootFactory.COREMODU,this);
		this.btnCanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		jButton = new javax.swing.JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_NAME));
		this.lblName = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
		this.txtName = new PtnTextField(true, PtnTextField.STRING_MAXLENGTH, this.lblMessage, this.btnSave, this);
		this.lblPort = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOAD_PORT));
		this.cmbPort = new JComboBox();
		this.lblAc = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOAD_AC));
		this.cmbAc = new JComboBox();
		this.lblPw = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOAD_PW));
		this.cmbPw = new JComboBox();
		this.lblActivate = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ACTIVITY_STATUS));
		this.chbActivate = new JCheckBox();
		if(null!=this.elineInfo){
			this.cmbPw.setEnabled(false);
		}
	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 50, 200, 50 };
		componentLayout.columnWeights = new double[] { 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 25, 40, 40, 40, 40, 40, 15, 40, 15 };
		componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0, 0, 0, 0, 0.2 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.insets = new Insets(1, 5, 5, 5);
		componentLayout.setConstraints(this.lblMessage, c);
		this.add(this.lblMessage);

		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 15, 5);
		componentLayout.setConstraints(this.lblName, c);
		this.add(this.lblName);

		c.gridx = 1;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.txtName, c);
		this.add(this.txtName);
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 15, 5);
		componentLayout.setConstraints(this.jButton, c);
		this.add(this.jButton);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblPort, c);
		this.add(this.lblPort);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.cmbPort, c);
		this.add(this.cmbPort);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblAc, c);
		this.add(this.lblAc);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.cmbAc, c);
		this.add(this.cmbAc);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblPw, c);
		this.add(this.lblPw);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.cmbPw, c);
		this.add(this.cmbPw);

		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblActivate, c);
		this.add(this.lblActivate);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.chbActivate, c);
		this.add(this.chbActivate);

		c.anchor = GridBagConstraints.EAST;
		c.gridx = 1;
		c.gridy = 7;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.btnSave, c);
		this.add(this.btnSave);
		c.gridx = 2;
		componentLayout.setConstraints(this.btnCanel, c);
		this.add(this.btnCanel);

	}
	
	/**
	 * 修改时添加自身AC
	 */
	private void addAcForUpdate() {
		if(null == this.eLineAc)
			return;
		if(this.eLineAc.getPortId()>0)
			if(((ControlKeyValue)this.cmbPort.getSelectedItem()).getId().equals(this.eLineAc.getPortId()+""))
				((DefaultComboBoxModel)cmbAc.getModel()).addElement(new ControlKeyValue(this.eLineAc.getId() + "", this.eLineAc.getName(), this.eLineAc));
		
		if(this.eLineAc.getLagId()>0)
			if(((ControlKeyValue)this.cmbPort.getSelectedItem()).getId().equals(this.eLineAc.getLagId()+""))
				((DefaultComboBoxModel)cmbAc.getModel()).addElement(new ControlKeyValue(this.eLineAc.getId() + "", this.eLineAc.getName(), this.eLineAc));
	}
	
	public ElinePanel getElinePanel() {
		return elinePanel;
	}

	public void setElinePanel(ElinePanel elinePanel) {
		this.elinePanel = elinePanel;
	}

	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblPort;
	private JComboBox cmbPort;
	private JLabel lblAc;
	private JComboBox cmbAc;
	private JLabel lblPw;
	private JComboBox cmbPw;
	private JLabel lblActivate;
	private JCheckBox chbActivate;
	private PtnButton btnSave;
	private JButton btnCanel;
	private JButton jButton;
	private JLabel lblMessage;
}
