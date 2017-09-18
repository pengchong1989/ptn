package com.nms.ui.ptn.ne.ces.view;

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

import com.nms.db.bean.client.Client;
import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortStm;
import com.nms.db.bean.equipment.port.PortStmTimeslot;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.ECesType;
import com.nms.db.enums.EManagerStatus;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EPwType;
import com.nms.db.enums.EServiceType;
import com.nms.model.client.ClientService_MB;
import com.nms.model.equipment.port.E1InfoService_MB;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.port.PortStmService_MB;
import com.nms.model.equipment.port.PortStmTimeslotService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
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
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.VerifyNameUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;

public class CesEditDialog extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 96267622523962139L;
	private CesInfo cesInfo;
	private CesPanel cesPanel;
	PortStmTimeslot portStmTimeslot ; //时隙端口 是修改是显示端口使用
	public CesEditDialog(CesInfo cesInfo, CesPanel cesPanel) {
		try {
			this.cesInfo = cesInfo;
			this.cesPanel = cesPanel;
			this.initCompoent();
			this.setLayout();
			this.initData();
			this.addListener();
			
			if(ResourceUtil.language.equals("zh_CN")){
				UiUtil.showWindow(this, 400, 390);
			}else{
				UiUtil.showWindow(this, 450, 390);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void initData() throws Exception {
		PortStmTimeslotService_MB portStmTimeslotServiceMB = null;
		try {
			portStmTimeslotServiceMB = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);
			super.getComboBoxDataUtil().comboBoxData(this.cmbtype, "CESNODETYPE");
			initPwData();
			this.cmbStruct.setEnabled(false);
			if (null == this.cesInfo) {
				this.cesInfo = new CesInfo();
				this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_CES));
				initPortData();
				this.cesInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
				this.cesInfo.setCreateUser(ConstantUtil.user.getUser_Name());
			} else {
				this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_UPDATE_CES));
				this.txtname.setText(this.cesInfo.getName());
				this.cmbtype.setEnabled(false);
				this.cmbpw.setEnabled(false);
				this.chkactivate.setSelected(this.cesInfo.getActiveStatus()==1?true:false);
				int portId = 0;
				if(null!=this.cesInfo){
					portId = this.cesInfo.getaSiteId() == ConstantUtil.siteId ? this.cesInfo.getaAcId() : this.cesInfo.getzAcId();
				}
				if(this.cesInfo.getCestype()==2){
					super.getComboBoxDataUtil().comboBoxSelect(this.cmbtype, "164");
					initPortData();
					super.getComboBoxDataUtil().comboBoxSelect(this.cmbport,portId+"");
				}else{
					super.getComboBoxDataUtil().comboBoxSelect(this.cmbtype, "165");
					initPortData();
					
					portStmTimeslot = portStmTimeslotServiceMB.selectById(portId);
					super.getComboBoxDataUtil().comboBoxSelect(this.cmbport,portStmTimeslot.getPortid()+"");
					initTimeoutData();
					super.getComboBoxDataUtil().comboBoxSelect(this.cmbtimeout,portStmTimeslot.getId()+"");
					super.getComboBoxDataUtil().comboBoxSelect(this.cmbpw,this.cesInfo.getPwId()+"");
				}
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portStmTimeslotServiceMB);
		}
	}

	/**
	 * 添加监听
	 */
	private void addListener() {
		this.btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CesEditDialog.this.dispose();
			}
		});
		this.cmbtype.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				if (evt.getStateChange() == 1) {
					try {
						initPwData();
						initPortData();
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				}
			}
		});
//		this.cmbport.addItemListener(new java.awt.event.ItemListener() {
//			public void itemStateChanged(java.awt.event.ItemEvent evt) {
//				if (evt.getStateChange() == 1) {
//					try {
//						initTimeoutData();
//					} catch (Exception e) {
//						ExceptionManage.dispose(e,this.getClass());
//					}
//				}
//			}
//		});
		
		this.cmbStruct.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				if (evt.getStateChange() == 1) {
					try {
						initTimeoutData();
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
				
				return true;
			}
		});
		this.autoNamingBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				autoNamingActionPerformed();
				
			}
		});
//		this.cmbpw.addItemListener(new ItemListener() {
//
//			@Override
//			public void itemStateChanged(ItemEvent evt) {
//				if (evt.getStateChange() == 1) {
//					pwSelected();
//				}
//			}
//		});
	}
	
	/**
	 * 如果是选择的pdh类型,当pw改变时,e1端口也要变
	 * pw和e1端口通过pw入标签绑定
	 */
//	private void pwSelected() {
//		ControlKeyValue controlKeyValue = (ControlKeyValue) this.cmbtype.getSelectedItem();
//		Code code = (Code) controlKeyValue.getObject();
//		if (ECesType.forms(Integer.parseInt(code.getCodeValue())) == ECesType.PDH) {
//			controlKeyValue = (ControlKeyValue) this.cmbpw.getSelectedItem();
//			PwInfo pw = (PwInfo) controlKeyValue.getObject();
//			this.setE1Info(pw);
//		}
//	}
//
//	private void setE1Info(PwInfo pw) {
//		E1InfoService e1Service = null;
//		try {
//			this.cmbport.removeAllItems();
//			E1Info e1 = new E1Info();
//			e1.setSiteId(ConstantUtil.siteId);
//			if(pw.getASiteId() == ConstantUtil.siteId){
//				e1.setPwLabel(pw.getInlabelValue());
//			}else{
//				e1.setPwLabel(pw.getOutlabelValue());
//			}
//			e1Service = (E1InfoService) ConstantUtil.serviceFactory.newService(Services.E1Info);
//			List<E1Info> e1List = e1Service.selectByCondition(e1);
//			if(e1List != null && e1List.size() > 0){
//				DefaultComboBoxModel defaultComboBoxModel = new DefaultComboBoxModel();
//				e1 = e1List.get(0);
//				boolean flag = false;
//				if(null == this.cesInfo || this.cesInfo.getPwId() == 0){
//					if(e1.getPortInst().getIsEnabled_code() == EManagerStatus.ENABLED.getValue() &&
//							e1.getPortInst().getIsOccupy() == 0){
//						flag = true;
//					}
//				}else{
//					flag = true;
//				}
//				if(flag){
//					defaultComboBoxModel.addElement(new ControlKeyValue(e1.getPortId() + "", 
//							e1.getPortName(), e1));
//					this.cmbport.setModel(defaultComboBoxModel);
//				}
//			}
//		}catch (Exception e) {
//			ExceptionManage.dispose(e, this.getClass());
//		}finally{
//			UiUtil.closeService(e1Service);
//		}
//	}

	/**
	 * 自动命名
	 */
	private void autoNamingActionPerformed() {
		CesInfo cesInfo ;
		String autoNaming;
		ControlKeyValue controlKeyValue_type;
		try {
			controlKeyValue_type=(ControlKeyValue) this.cmbtype.getSelectedItem();
			cesInfo= new CesInfo();
			cesInfo.setIsSingle(1);
			cesInfo.setaSiteId(ConstantUtil.siteId);
			cesInfo.setCestype(Integer.parseInt(((Code)controlKeyValue_type.getObject()).getCodeValue()));
			AutoNamingUtil autoNamingUtil=new AutoNamingUtil();
			autoNaming = (String) autoNamingUtil.autoNaming(cesInfo, null, null);
			txtname.setText(autoNaming);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 保存方法
	 * @throws Exception 
	 */
	private void saveInfo() throws Exception {
		ControlKeyValue controlKeyValue_type=null;
		ControlKeyValue controlKeyValue_port=null;
		ControlKeyValue controlKeyValue_timeslot=null;
		ControlKeyValue controlKeyValue_pw=null;
		int type=0;
		PwInfo pwinfo=null;
		DispatchUtil cesDispatch=null;
		List<CesInfo> cesInfoList=null;
		String resultStr=null;
		String beforeName=null;
		PortService_MB portServiceMB = null;
		PortStmTimeslotService_MB portStmTimeslotServiceMB = null;
		try {
			portStmTimeslotServiceMB = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);
			portServiceMB = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			if (!this.isFull()) {
				return;
			}
			
			//验证名称是否存在
			if(this.cesInfo.getId()!=0){
				beforeName=this.cesInfo.getName();
			}
			VerifyNameUtil verifyNameUtil=new VerifyNameUtil();
			if(verifyNameUtil.verifyNameBySingle(EServiceType.CES.getValue(), this.txtname.getText().trim(), beforeName,ConstantUtil.siteId)){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST));
				return;
			}
			
			controlKeyValue_type=(ControlKeyValue) this.cmbtype.getSelectedItem();
			controlKeyValue_port=(ControlKeyValue) this.cmbport.getSelectedItem();
			controlKeyValue_timeslot=(ControlKeyValue) this.cmbtimeout.getSelectedItem();
			controlKeyValue_pw=(ControlKeyValue) this.cmbpw.getSelectedItem();
			type=Integer.parseInt(((Code)controlKeyValue_type.getObject()).getCodeValue());
			pwinfo=(PwInfo) controlKeyValue_pw.getObject();
			
			this.cesInfo.setName(this.txtname.getText().trim());
			this.cesInfo.setServiceType(EServiceType.CES.getValue());
			this.cesInfo.setCestype(type);
			this.cesInfo.setIsSingle(1);
			this.cesInfo.setActiveStatus(this.chkactivate.isSelected()?EActiveStatus.ACTIVITY.getValue():EActiveStatus.UNACTIVITY.getValue());
			this.cesInfo.setPwId(Integer.parseInt(controlKeyValue_pw.getId()));
			this.cesInfo.setPwName(((PwInfo)controlKeyValue_pw.getObject()).getPwName());
			if(pwinfo.getASiteId()==ConstantUtil.siteId){
				this.cesInfo.setaSiteId(ConstantUtil.siteId);
//				this.cesInfo.setzSiteId(pwinfo.getZSiteId());
				if(type==ECesType.PDH.getValue()){
					if(this.cesInfo.getaAcId()!=Integer.parseInt(controlKeyValue_port.getId())){
						this.cesInfo.setBeforeAPort(portServiceMB.selectPortybyid(this.cesInfo.getaAcId()));
						this.cesInfo.setAction(1);
					}
					this.cesInfo.setaAcId(Integer.parseInt(controlKeyValue_port.getId()));
				}else{
					if(this.cesInfo.getaAcId()!=Integer.parseInt(controlKeyValue_port.getId())){
						this.cesInfo.setBeforeAPortStmTime(portStmTimeslotServiceMB.selectById(this.cesInfo.getaAcId()));
						this.cesInfo.setAction(1);
					}
					this.cesInfo.setaAcId(Integer.parseInt(controlKeyValue_timeslot.getId()));
				}
			}else{
				this.cesInfo.setzSiteId(ConstantUtil.siteId);
//				this.cesInfo.setaSiteId(pwinfo.getZSiteId());
				if(type==ECesType.PDH.getValue()){
					this.cesInfo.setzAcId(Integer.parseInt(controlKeyValue_port.getId()));
				}else{
					this.cesInfo.setzAcId(Integer.parseInt(controlKeyValue_timeslot.getId()));
				}
			}
			
			cesDispatch=new DispatchUtil(RmiKeys.RMI_CES);
			cesInfoList=new ArrayList<CesInfo>();
			cesInfoList.add(this.cesInfo);
			
			if(this.cesInfo.getId()==0){
				resultStr=cesDispatch.excuteInsert(cesInfoList);
				//添加日志记录
				this.getAcName(this.cesInfo);
				AddOperateLog.insertOperLog(btnSave, EOperationLogType.CESINSERT.getValue(), resultStr, 
						 null, this.cesInfo, cesInfo.getaSiteId()==0?cesInfo.getzSiteId():cesInfo.getaSiteId(), this.cesInfo.getName(), "ces");
			}else{
				CesInfo cesBefore = this.getCesBefore(this.cesInfo);
				resultStr=cesDispatch.excuteUpdate(this.cesInfo);
				//添加日志记录
				this.getAcName(this.cesInfo);
				AddOperateLog.insertOperLog(btnSave, EOperationLogType.CESUPDATE.getValue(), resultStr, 
						cesBefore, this.cesInfo, cesInfo.getaSiteId()==0?cesInfo.getzSiteId():cesInfo.getaSiteId(), this.cesInfo.getName(), "ces");
			}
			DialogBoxUtil.succeedDialog(this, resultStr);
			
			this.cesPanel.getController().refresh();
			this.dispose();
			
		} catch (Exception e) {
			throw e;
		} finally{
			controlKeyValue_type=null;
			controlKeyValue_port=null;
			controlKeyValue_timeslot=null;
			controlKeyValue_pw=null;
			pwinfo=null;
			UiUtil.closeService_MB(portServiceMB);
			UiUtil.closeService_MB(portStmTimeslotServiceMB);
		}
	}
	
	private CesInfo getCesBefore(CesInfo cesInfo){
		CesInfoService_MB cesService = null;
		PwInfoService_MB pwService = null;
		ClientService_MB clientService = null;
		CesInfo before = null;
		try {
			clientService = (ClientService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CLIENTSERVICE);
			pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			cesService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			before = new CesInfo();
			before.setPwId(cesInfo.getPwId());
			before = cesService.filterSingle(before, ConstantUtil.siteId).get(0);
			before.setPwName(pwService.selectByPwId(before.getPwId()).getPwName());
			if(before.getClientId() > 0){
				List<Client> clientList = clientService.select(before.getClientId());
				if(clientList != null && clientList.size() > 0){
					before.setClientName(clientList.get(0).getName());
				}
			}
			this.getAcName(before);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(cesService);
			UiUtil.closeService_MB(pwService);
			UiUtil.closeService_MB(clientService);
		}
		return before;
	}
	
	private void getAcName(CesInfo ces) throws Exception {
		if(ces.getaSiteId() > 0){
			if(ces.getCestype() == ECesType.PDH.getValue()){
				ces.setaAcName(initPDHPort(ces.getaSiteId(),ces.getaAcId()).getPortName());
			}else if(ces.getCestype() == ECesType.SDH.getValue()){
				ces.setaAcName(initSDHPort(ces.getaSiteId(),ces.getaAcId()).getTimeslotname());
			}
		}else{
			if(ces.getCestype() == ECesType.PDH.getValue()){
				ces.setzAcName(initPDHPort(ces.getzSiteId(),ces.getzAcId()).getPortName());
			}else if(ces.getCestype() == ECesType.SDH.getValue()){
				ces.setzAcName(initSDHPort(ces.getzSiteId(),ces.getzAcId()).getTimeslotname());
			}
		}
	}
	
	/**
	 * 查询pdh端口
	 * @param siteId 网元ID
	 * @param portId 端口ID
	 * @return
	 * @throws Exception
	 */
	private PortInst initPDHPort(int siteId ,int portId) throws Exception {
		PortInst portInst = new PortInst();
		PortService_MB portServiceMB = null;
		try {
			portServiceMB = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInst.setPortId(portId);
			portInst = portServiceMB.select(portInst).get(0);
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portServiceMB);
		}
		return portInst;
	}
	
	/**
	 * 查询sdh端口
	 * @param siteId 网元ID
	 * @param portId 端口ID
	 * @return
	 * @throws Exception
	 */
	private PortStmTimeslot initSDHPort(int siteId ,int portId) throws Exception {
		PortStmTimeslotService_MB portStmTimeslotService = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);
		PortStmTimeslot p = null;
		try {
			p = portStmTimeslotService.selectById(portId);
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portStmTimeslotService);
		}
		return p;
	}

	/**
	 * 验证是否完整
	 * @throws Exception 
	 */
	private boolean isFull() throws Exception{
		
		boolean flag = true;
		ControlKeyValue controlKeyValue=null;
		int type=0;
		try {
			if( null == this.cmbport.getSelectedItem()){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_MUSTNETWORK_BEFORE));
				flag=false;
			}else if(null == this.cmbpw.getSelectedItem()){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_PW));
				flag=false;
			}else{
				controlKeyValue=(ControlKeyValue) this.cmbtype.getSelectedItem();
				type=Integer.parseInt(((Code)controlKeyValue.getObject()).getCodeValue());
				if(type==ECesType.SDH.getValue()){
					if(null==this.cmbtimeout.getSelectedItem()){
						DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_TIMESLOT));
						flag=false;
					}
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return flag;
		
		
	}

	/**
	 * 初始化pw下拉列表数据
	 * 
	 * @throws Exception
	 */
	private void initPwData() throws Exception {

		PwInfoService_MB pwInfoServiceMB = null;
		List<PwInfo> pwinfoList = new ArrayList<PwInfo>();
		ControlKeyValue controlKeyValue = null;
		int type = 0;
		DefaultComboBoxModel boxModel = null;
		PwInfo pwInfoSel;
		try {
			pwInfoSel = new PwInfo();
			controlKeyValue = (ControlKeyValue) this.cmbtype.getSelectedItem();
			type = Integer.parseInt(((Code) controlKeyValue.getObject()).getCodeValue());
			//添加自身pw
			pwInfoServiceMB = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			if(null!=this.cesInfo&&this.cesInfo.getPwId()>0){
				pwInfoSel.setPwId(this.cesInfo.getPwId());
				pwInfoSel = pwInfoServiceMB.selectBypwid_notjoin(pwInfoSel);
				pwinfoList.add(pwInfoSel);
			}
//			pwinfoList = pwInfoService.selectNodeBySiteid(ConstantUtil.siteId);
			pwinfoList.addAll(pwInfoServiceMB.getAvailable(ConstantUtil.siteId,EPwType.forms(type)));
			boxModel = new DefaultComboBoxModel();
			if (null != pwinfoList && pwinfoList.size() > 0) {
				for (PwInfo pwInfo : pwinfoList) {
					boxModel.addElement(new ControlKeyValue(pwInfo.getPwId() + "", pwInfo.getPwName(), pwInfo));
//					if (pwInfo.getRelatedServiceId() == 0) {
//						if (type == ECesType.PDH.getValue()) {
//							if (pwInfo.getASiteId() == ConstantUtil.siteId) {
//								if (pwInfo.getType().getValue() == EPwType.PDH.getValue() || pwInfo.getType().getValue() == EPwType.PDH_SDH.getValue()) {
//									boxModel.addElement(new ControlKeyValue(pwInfo.getPwId() + "", pwInfo.getPwName(), pwInfo));
//								}
//							} else {
//								if (pwInfo.getType().getValue() == EPwType.PDH.getValue() || pwInfo.getType().getValue() == EPwType.SDH_PDH.getValue()) {
//									boxModel.addElement(new ControlKeyValue(pwInfo.getPwId() + "", pwInfo.getPwName(), pwInfo));
//								}
//							}
//						} else {
//							if (pwInfo.getASiteId() == ConstantUtil.siteId) {
//								if (pwInfo.getType().getValue() == EPwType.SDH.getValue() || pwInfo.getType().getValue() == EPwType.SDH_PDH.getValue()) {
//									boxModel.addElement(new ControlKeyValue(pwInfo.getPwId() + "", pwInfo.getPwName(), pwInfo));
//								}
//							} else {
//								if (pwInfo.getType().getValue() == EPwType.SDH.getValue() || pwInfo.getType().getValue() == EPwType.PDH_SDH.getValue()) {
//									boxModel.addElement(new ControlKeyValue(pwInfo.getPwId() + "", pwInfo.getPwName(), pwInfo));
//								}
//							}
//						}
//					}
				}
				//如果是pdh类型的pw,则需要给端口加载一个默认值
//				if(type == EPwType.PDH.getValue() && pwinfoList.size() > 0){
//					ControlKeyValue ck = (ControlKeyValue) boxModel.getElementAt(0);
//					this.setE1Info((PwInfo) ck.getObject());
//				}
			}
			this.cmbpw.setModel(boxModel);
			
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(pwInfoServiceMB);
			pwinfoList = null;
			controlKeyValue = null;
			boxModel = null;
		}

	}

	/**
	 * 初始化时隙下拉列表数据
	 * 
	 * @throws Exception
	 */
	private void initTimeoutData() throws Exception {
		ControlKeyValue controlKeyValue = null;
		Code code = null;
		PortStmTimeslotService_MB portStmTimeslotServiceMB = null;
		ControlKeyValue controlKeyValue_port = null;
		List<PortStmTimeslot> portStmTimeslotList = null;
		DefaultComboBoxModel defaultComboBoxModel = null;
		if(cmbStruct.getSelectedIndex()==0)
		{
			cmbtimeout.setEnabled(false);
		}
		else if(cmbStruct.getSelectedIndex()==1)
		{
			cmbtimeout.setEnabled(true);
		}
		try {
			controlKeyValue = (ControlKeyValue) this.cmbtype.getSelectedItem();
			code = (Code) controlKeyValue.getObject();

			if (ECesType.forms(Integer.parseInt(code.getCodeValue())) == ECesType.SDH) {

				if(null != this.cmbport.getSelectedItem()){
					controlKeyValue_port = (ControlKeyValue) this.cmbport.getSelectedItem();

					portStmTimeslotServiceMB = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);
					portStmTimeslotList = portStmTimeslotServiceMB.selectbyportId(Integer.parseInt(controlKeyValue_port.getId()));

					defaultComboBoxModel = new DefaultComboBoxModel();
					if (null != portStmTimeslotList && portStmTimeslotList.size() > 0) {
						for (PortStmTimeslot portStmTimeslot : portStmTimeslotList) {
							if (portStmTimeslot.getManagerStatus() == EManagerStatus.ENABLED.getValue() && portStmTimeslot.getIsUsed() == 0) {
								defaultComboBoxModel.addElement(new ControlKeyValue(portStmTimeslot.getId() + "", portStmTimeslot.getTimeslotnumber(), portStmTimeslot));
							}
							if(null!=this.portStmTimeslot&&portStmTimeslot.getId()==this.portStmTimeslot.getId()){
								defaultComboBoxModel.addElement(new ControlKeyValue(portStmTimeslot.getId() + "", portStmTimeslot.getTimeslotnumber(), portStmTimeslot));
							}
						}
					}
					this.cmbtimeout.setModel(defaultComboBoxModel);
				}
				
			}
		} catch (Exception e) {
			throw e;
		} finally {
			controlKeyValue = null;
			code = null;
			UiUtil.closeService_MB(portStmTimeslotServiceMB);
			controlKeyValue_port = null;
			portStmTimeslotList = null;
			defaultComboBoxModel = null;
		}

	}

	/**
	 * 初始化端口下拉列表数据
	 * 
	 * @throws Exception
	 */
	private void initPortData() throws Exception {

		ControlKeyValue controlKeyValue = null;
		Code code = null;
		try {
			controlKeyValue = (ControlKeyValue) this.cmbtype.getSelectedItem();
			code = (Code) controlKeyValue.getObject();

			if (ECesType.forms(Integer.parseInt(code.getCodeValue())) == ECesType.PDH) {
				this.pdhPortData();
				this.cmbport.setEnabled(true);
			} else {
				this.cmbport.setEnabled(true);
				this.sdhPortData();
			}

		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * PDH端口数据绑定
	 * 
	 * @throws Exception
	 */
	private void pdhPortData() throws Exception {
		E1InfoService_MB eInfoServiceMB = null;
		E1Info einfo_select = null;
		List<E1Info> eInfoList = null;
		DefaultComboBoxModel defaultComboBoxModel = null;
		try {
			this.cmbtimeout.setModel(new DefaultComboBoxModel());

			eInfoServiceMB = (E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);
			einfo_select = new E1Info();
			einfo_select.setSiteId(ConstantUtil.siteId);
			eInfoList = eInfoServiceMB.selectByCondition(einfo_select);

			defaultComboBoxModel = new DefaultComboBoxModel();
			if (null != eInfoList && eInfoList.size() > 0) {
				int portId = 0;
				if(null!=this.cesInfo){
					portId = this.cesInfo.getaSiteId() == ConstantUtil.siteId ? this.cesInfo.getaAcId() : this.cesInfo.getzAcId();
				}
				for (E1Info eInfo : eInfoList) {
					if (eInfo.getPortInst().getIsEnabled_code() == EManagerStatus.ENABLED.getValue() && eInfo.getPortInst().getIsOccupy() == 0) {
						defaultComboBoxModel.addElement(new ControlKeyValue(eInfo.getPortId() + "", eInfo.getPortName(), eInfo));
					}
					if(null!=this.cesInfo&&portId==eInfo.getPortId()){
						defaultComboBoxModel.addElement(new ControlKeyValue(eInfo.getPortId() + "", eInfo.getPortName(), eInfo));
					}
				}
			}
			this.cmbport.setModel(defaultComboBoxModel);

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(eInfoServiceMB);
			einfo_select = null;
			eInfoList = null;
			defaultComboBoxModel = null;
		}
	}

	/**
	 * sdh端口列表
	 * 
	 * @throws Exception
	 */
	private void sdhPortData() throws Exception {

		PortStmService_MB portStmServiceMB = null;
		List<PortStm> portStmList = null;
		DefaultComboBoxModel defaultComboBoxModel = null;
		try {
			portStmServiceMB = (PortStmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTM);
			portStmList = portStmServiceMB.queryBySiteid(ConstantUtil.siteId);

			defaultComboBoxModel = new DefaultComboBoxModel();
			if (null != portStmList && portStmList.size() > 0) {
				for (PortStm portStm : portStmList) {
					defaultComboBoxModel.addElement(new ControlKeyValue(portStm.getPortid() + "", portStm.getName(), portStm));
				}
			}
			this.cmbport.setModel(defaultComboBoxModel);

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portStmServiceMB);
			portStmList = null;
			defaultComboBoxModel = null;
		}

	}

	/**
	 * 初始化控件
	 * @throws Exception 
	 */
	private void initCompoent() throws Exception {
		this.lblMessage=new JLabel();
		this.btnSave = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true);
		this.btnCanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.lblname = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
		this.txtname = new PtnTextField(true,PtnTextField.STRING_MAXLENGTH,this.lblMessage,this.btnSave,this);
		this.lbltype = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_SERVICE_TYPE));
		this.cmbtype = new JComboBox();
		this.lblport = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOAD_PORT));
		this.cmbport = new JComboBox();
		this.lbltimeout = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOAD_TIMESLOT));
		this.cmbtimeout = new JComboBox();
		cmbtimeout.setEnabled(false);
		this.lblpw = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOAD_PW));
		this.cmbpw = new JComboBox();
		this.lblactivate = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ACTIVITY_STATUS));
		chkactivate = new JCheckBox();
		this.autoNamingBtn = new JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_NAME));
		this.lblStruct = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_STRUCT_SUPPORT));
		this.cmbStruct = new JComboBox();
		cmbStruct.addItem(ResourceUtil.srcStr(StringKeysObj.CMB_NOSTRUCT_OPTION));
		cmbStruct.addItem(ResourceUtil.srcStr(StringKeysObj.CMB_STRUCT_OPTION));
		cmbStruct.setSelectedIndex(0);
	}

	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 50, 200, 50 };
		componentLayout.columnWeights = new double[] { 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 25, 40, 40, 40, 40, 40, 40, 40, 15, 40, 15 };
		componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0, 0, 0, 0, 0,0, 0.2 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.insets = new Insets(5, 5, 5, 5);
		componentLayout.setConstraints(this.lblMessage, c);
		this.add(this.lblMessage);
		
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 15, 5);
		componentLayout.setConstraints(this.lblname, c);
		this.add(this.lblname);
		c.gridx = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.txtname, c);
		this.add(this.txtname);
		
		c.gridx = 2;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.autoNamingBtn, c);
		this.add(this.autoNamingBtn);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lbltype, c);
		this.add(this.lbltype);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.cmbtype, c);
		this.add(this.cmbtype);

		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblpw, c);
		this.add(this.lblpw);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.cmbpw, c);
		this.add(this.cmbpw);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblStruct, c);
		this.add(this.lblStruct);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.cmbStruct, c);
		this.add(this.cmbStruct);
		
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lbltimeout, c);
		this.add(this.lbltimeout);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.cmbtimeout, c);
		this.add(this.cmbtimeout);

		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblport, c);
		this.add(this.lblport);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.cmbport, c);
		this.add(this.cmbport);

		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblactivate, c);
		this.add(this.lblactivate);
		c.gridx = 1;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.chkactivate, c);
		this.add(this.chkactivate);

		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		c.gridx = 1;
		c.gridy = 9;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.btnSave, c);
		this.add(this.btnSave);
		c.gridx = 2;
		componentLayout.setConstraints(this.btnCanel, c);
		this.add(this.btnCanel);
	}

	private JLabel lblname;
	private JTextField txtname;
	private JLabel lbltype;
	private JComboBox cmbtype;
	private JLabel lblport;
	private JComboBox cmbport;
	private JLabel lbltimeout;
	private JComboBox cmbtimeout;
	private JLabel lblpw;
	private JComboBox cmbpw;
	private JLabel lblactivate;
	private JCheckBox chkactivate;
	private PtnButton btnSave;
	private JButton btnCanel;
	private JLabel lblMessage;
	private JButton autoNamingBtn;
	private JLabel lblStruct;
	private JComboBox cmbStruct;
}
