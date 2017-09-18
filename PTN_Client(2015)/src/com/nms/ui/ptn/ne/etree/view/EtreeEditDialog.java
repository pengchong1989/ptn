package com.nms.ui.ptn.ne.etree.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.nms.db.bean.ptn.CommonBean;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.eth.VplsInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.system.code.Code;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EPwType;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
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
import com.nms.ui.manager.keys.StringKeysOperaType;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;

public class EtreeEditDialog extends PtnDialog {
	private static final long serialVersionUID = -6800612781484665905L;
	private EtreeInfo etreeInfo;
	private EtreePanel etreePanel;
	private List<EtreeInfo> etreeInfoListForUpdate;
	private int rootAcId = 0; // 根ACID 修改界面加载时，给此属性赋值。修改时通过此属性验证是否对根AC做了改变
	private AcPortInfo etreeAc;
	private String nameBefore = null;//日志记录需要
	
	public EtreeEditDialog(EtreeInfo etreeInfo, EtreePanel etreePanel) {

		try {
			this.etreeInfo = etreeInfo;
			this.etreePanel = etreePanel;
			this.initComponent();
			this.setLayout();
			this.listPwData();
			this.listAcData();
			this.addListener();
			this.initData();
			if(ResourceUtil.language.equals("zh_CN")){
				UiUtil.showWindow(this, 510, 450);
			}else{
				UiUtil.showWindow(this, 550, 450);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 初始化表单数据
	 * @throws Exception 
	 */
	private void initData() throws Exception {
		try {
			super.getComboBoxDataUtil().comboBoxData(this.cmbtype, "ETREETYPE");
			//初始化ac
			if (null == this.etreeInfo) {
				this.etreeInfo = new EtreeInfo();
				this.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_CREATE_ETREE));
			}else{
				this.setTitle(ResourceUtil.srcStr(StringKeysOperaType.BTN_ETREE_UPDATE));
				this.txtname.setText(this.etreeInfo.getName());
				this.nameBefore = this.etreeInfo.getName();
				if(0!=this.etreeInfo.getRootSite()){
					super.getComboBoxDataUtil().comboBoxSelect(this.cmbtype,  "173");
				}else{
					super.getComboBoxDataUtil().comboBoxSelect(this.cmbtype, "174");
				}
//				this.btnLeft.setEnabled(false);
//				this.btnRight.setEnabled(false);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 添加监听
	 */
	private void addListener() {
		this.btnCanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EtreeEditDialog.this.dispose();
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
		// 自动命名事件
		jButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButtonActionPerformed(evt);
			}
		});
		this.btnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					btnRightAction(listPw,ListSelectPw);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});
		this.btnLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					btnRightAction(ListSelectPw,listPw);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});
		
		this.btnAcRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					btnRightAction(listAC,listSelectAC);
				} catch (Exception e) {
					e.printStackTrace();
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});
		
		this.btnAcLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					btnRightAction(listSelectAC,listAC);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		});
	}

	/**
	 * 向右选择pw按钮事件
	 * 
	 * @throws Exception
	 */
	private void btnRightAction(JList sourceList,JList purposeList) throws Exception {
		int index = 0;
		DefaultListModel defaultListModel = null;
		DefaultListModel defaultListModel_select = null;
		try {
			index = sourceList.getSelectedIndex();
			if (index >= 0) {
				defaultListModel_select = (DefaultListModel) purposeList.getModel();
				defaultListModel = (DefaultListModel) sourceList.getModel();
				defaultListModel_select.addElement(defaultListModel.getElementAt(index));
				defaultListModel.removeElementAt(index);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			defaultListModel = null;
			defaultListModel_select = null;
		}
	}

	/**
	 * 绑定pw的list数据
	 * 
	 * @throws Exception
	 */
	private void listPwData() throws Exception {
		PwInfoService_MB pwInfoService = null;
		List<PwInfo> pwInfoList = null;
		DefaultListModel defaultListModel = null;
		DefaultListModel defaultListModelSel = null;
		EtreeInfoService_MB etreeService = null;
		PwInfo pwInfoSel;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			pwInfoList = pwInfoService.getAvailable(ConstantUtil.siteId,EPwType.ETH);
			etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			defaultListModel = new DefaultListModel();
			//可用PW
			if (null != pwInfoList && pwInfoList.size() > 0) {
				for (PwInfo pwInfo : pwInfoList) {
					defaultListModel.addElement(new ControlKeyValue(pwInfo.getPwId() + "", pwInfo.getPwName(), pwInfo));
				}
			}
			this.listPw.setModel(defaultListModel);
			
			//选中PW
			if(null!=this.etreeInfo){
				this.etreeInfoListForUpdate =  etreeService.selectByServiceId(this.etreeInfo.getServiceId());
				defaultListModelSel = new DefaultListModel();
				pwInfoList = new ArrayList<PwInfo>();
				for (EtreeInfo etreeInfo : etreeInfoListForUpdate) {
					pwInfoSel = new PwInfo();
					pwInfoSel.setPwId(etreeInfo.getPwId());
					pwInfoSel = pwInfoService.selectBypwid_notjoin(pwInfoSel);
					pwInfoList.add(pwInfoSel);
				}
				if (null != pwInfoList && pwInfoList.size() > 0) {
					for (PwInfo pwInfo : pwInfoList) {
						defaultListModelSel.addElement(new ControlKeyValue(pwInfo.getPwId() + "", pwInfo.getPwName(), pwInfo));
					}
					this.ListSelectPw.setModel(defaultListModelSel);
				}
			}else{
				this.ListSelectPw.setModel(new DefaultListModel());
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(etreeService);
			UiUtil.closeService_MB(pwInfoService);
			pwInfoList = null;
			defaultListModel = null;
		}
	}

	private void listAcData() 
	{
		AcPortInfoService_MB acInfoService = null;
		List<AcPortInfo> acportInfoList = null;
		DefaultListModel defaultListModel = null;
		DefaultListModel defaultListModelSel = null;
		List<EtreeInfo> etreeInfoList = null;
		EtreeInfoService_MB etreeService = null;
		List<Integer> acIdList = null;
		Set<Integer> acSet = null;
		UiUtil uiUtil = null;
		try 
		{
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acportInfoList = acInfoService.selectBySiteId(ConstantUtil.siteId);
			defaultListModel = new DefaultListModel();
			if (acportInfoList != null && acportInfoList.size() > 0) {
				for (AcPortInfo acPortInfo : acportInfoList) {
					if (acPortInfo.getIsUser() == 0) {
						defaultListModel.addElement(new ControlKeyValue(acPortInfo.getId() + "", acPortInfo.getName(), acPortInfo));
					}
				}
			}
			this.listAC.setModel(defaultListModel);
			//选中的AC
			if(null!=this.etreeInfo){
				uiUtil = new UiUtil();
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				etreeInfoList =  etreeService.selectByServiceId(this.etreeInfo.getServiceId());
				defaultListModelSel = new DefaultListModel();
				acSet = new HashSet<Integer>();
				acIdList = new ArrayList<Integer>();
				for (EtreeInfo etreeInfo : etreeInfoList) {
					acSet.addAll(uiUtil.getAcIdSets(etreeInfo.getAmostAcId()));
					acSet.addAll(uiUtil.getAcIdSets(etreeInfo.getZmostAcId()));
				}
				acIdList = new ArrayList<Integer>(acSet);
				acportInfoList = acInfoService.select(acIdList);
				if (acportInfoList != null && acportInfoList.size() > 0) {
					for (AcPortInfo acPortInfo : acportInfoList) {
						defaultListModelSel.addElement(new ControlKeyValue(acPortInfo.getId() + "", acPortInfo.getName(), acPortInfo));
					}
				}				
				this.listSelectAC.setModel(defaultListModelSel);
			}else{
				this.listSelectAC.setModel(new DefaultListModel());
			}
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			UiUtil.closeService_MB(acInfoService);
			UiUtil.closeService_MB(etreeService);
			acportInfoList = null;
			defaultListModel = null;
			acportInfoList = null;
			defaultListModelSel = null;
			etreeInfoList = null;
			acIdList = null;
		}
	}
	
	// 自动命名事件
	private void jButtonActionPerformed(java.awt.event.ActionEvent evt) {
		try {

			etreeInfo.setIsSingle(1);
			etreeInfo.setaSiteId(ConstantUtil.siteId);
			AutoNamingUtil autoNamingUtil=new AutoNamingUtil();
			String autoNaming = (String) autoNamingUtil.autoNaming(etreeInfo, null, null);
			txtname.setText(autoNaming);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}

	}

	/**
	 * 保存方法
	 * 
	 * @throws Exception
	 */
	private void saveInfo() throws Exception {

		List<EtreeInfo> etreeInfoList = null;
		Code code_type = null;
		List<Integer> acIdList = null;
		DefaultListModel defaultListModel = null;
		DefaultListModel defaultListModelAc = null;
		EtreeInfo etreeInfo = null;
		PwInfo pwinfo = null;
		DispatchUtil etreeDispatch = null;
		String resultStr = null;
		String beforeName = null;
		List<AcPortInfo> useAcPortList = null;
		try {
			if (!this.isFull()) {
				return;
			}

			// 验证名称是否存在
			if (this.etreeInfo.getId() != 0) {
				beforeName = this.etreeInfo.getName();
			}

			VerifyNameUtil verifyNameUtil=new VerifyNameUtil();
			if (verifyNameUtil.verifyNameBySingle(EServiceType.ETREE.getValue(), this.txtname.getText().trim(), beforeName, ConstantUtil.siteId)) {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST));
				return;
			}

			if (!this.isSelectPw()) {
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_PW_ERROR));
				return;
			}
			
			defaultListModel = (DefaultListModel) this.ListSelectPw.getModel();
			if(defaultListModel.getSize()>64)
			{
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_EXCEEDPWNUMBER));
				return;
			}
			defaultListModelAc  = (DefaultListModel) this.listSelectAC.getModel();
			if(defaultListModelAc.getSize()>10)
			{
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_EXCEEDACNUMBER));
				return;
			}
			
			etreeInfoList = new ArrayList<EtreeInfo>();
			code_type = (Code) ((ControlKeyValue) this.cmbtype.getSelectedItem()).getObject();
			acIdList = new ArrayList<Integer>();
			useAcPortList = new ArrayList<AcPortInfo>();
			getAllAcId(acIdList,useAcPortList);
			
			
			for(int i = 0; i < defaultListModel.getSize(); i++){
				pwinfo = (PwInfo) ((ControlKeyValue) defaultListModel.getElementAt(i)).getObject();
				etreeInfo = new EtreeInfo();
				etreeInfo.setName(this.txtname.getText().trim());				
				etreeInfo.setServiceType(EServiceType.ETREE.getValue());
				etreeInfo.setActiveStatus(this.chkactivate.isSelected() ? EActiveStatus.ACTIVITY.getValue() : EActiveStatus.UNACTIVITY.getValue());
				etreeInfo.setIsSingle(1);
				etreeInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
				etreeInfo.setCreateUser(ConstantUtil.user.getUser_Name());
				etreeInfo.setPwId(pwinfo.getPwId());
				etreeInfo.setPwName(pwinfo.getPwName());
				if ("root".endsWith(code_type.getCodeValue())) {
					etreeInfo.setRootSite(ConstantUtil.siteId);
					etreeInfo.setaSiteId(ConstantUtil.siteId);
					etreeInfo.setAmostAcId(acIdList.toString().subSequence(1, acIdList.toString().length() -1).toString());
				} else {
					etreeInfo.setBranchSite(ConstantUtil.siteId);
					etreeInfo.setzSiteId(ConstantUtil.siteId);
					etreeInfo.setZmostAcId(acIdList.toString().subSequence(1, acIdList.toString().length() -1).toString());
				}
				etreeInfoList.add(etreeInfo);
			}
			etreeDispatch = new DispatchUtil(RmiKeys.RMI_ETREE);
			if(0==this.etreeInfo.getServiceId()){
				resultStr = etreeDispatch.excuteInsert(etreeInfoList);
				//日志记录
				VplsInfo vplsInfo = this.getVplsBefore(etreeInfoList, 1);
				AddOperateLog.insertOperLog(btnSave, EOperationLogType.ETREEINSERT.getValue(), resultStr, null, vplsInfo, ConstantUtil.siteId, vplsInfo.getVplsName(), "etree");
			}else{
				integrateEtreeList(etreeInfoList); //整理修改数据
//				checkUpdateRoot(); //验证当根ac修改时 ，是否有修改操作
				VplsInfo vplsBefore = this.getVplsBefore(null, 0);
				List<Integer> pwdList_before = this.getPwIdList_before(vplsBefore.getEtreeInfoList());
				resultStr = etreeDispatch.excuteUpdate(this.etreeInfoListForUpdate);
				nameBefore = this.txtname.getText();
				VplsInfo vplsInfo = this.getVplsBefore(null, 0);
				List<Integer> pwdList = this.getPwIdList_before(vplsInfo.getEtreeInfoList());
				if(pwdList_before.size() > pwdList.size()){
					this.sortEtreeList(vplsBefore.getEtreeInfoList(), pwdList);
				}else{
					this.sortEtreeList(vplsInfo.getEtreeInfoList(), pwdList_before);
				}
				AddOperateLog.insertOperLog(btnSave, EOperationLogType.ETREEUPDATE.getValue(), resultStr, vplsBefore, vplsInfo, ConstantUtil.siteId, vplsInfo.getVplsName(), "etree");
			}
			DialogBoxUtil.succeedDialog(this, resultStr);
			//添加日志记录
			this.etreePanel.getController().refresh();
			this.dispose();
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
	 * 需要将List<EtreeInfo>排序，便于日志记录时比对
	 * 如：修改前是pw1,pw2,pw3三条pw，就有3条数据，修改后是pw1,pw3两条pw,有2条数据，需要将之前的集合调整为pw1,pw3,pw2的顺序
	 * @param etreeInfoList需要调整顺序的集合
	 * @param pwIdList调整的顺序
	 */
	private void sortEtreeList(List<EtreeInfo> etreeInfoList, List<Integer> pwdList) {
		List<EtreeInfo> etreeList = new ArrayList<EtreeInfo>();
		for (int pwId : pwdList) {
			for (EtreeInfo etreeInfo : etreeInfoList) {
				if(etreeInfo.getPwId() == pwId){
					etreeList.add(etreeInfo);
					break;
				}
			}
		}
		for (EtreeInfo etreeInfo : etreeInfoList) {
			if(!pwdList.contains(etreeInfo.getPwId())){
				etreeList.add(etreeInfo);
			}
		}
		etreeInfoList.clear();
		etreeInfoList.addAll(etreeList);
	}

	private List<Integer> getPwIdList_before(List<EtreeInfo> etreeInfoList) {
		List<Integer> pwIdList = new ArrayList<Integer>();
		for (EtreeInfo etreeInfo : etreeInfoList) {
			pwIdList.add(etreeInfo.getBranchSite());
		}
		return pwIdList;
	}

	/**
	 * 获取修改前的业务数据，便于日志记录
	 * @param type 0/1 需要查询/不需要查询
	 * @return
	 */
	private VplsInfo getVplsBefore(List<EtreeInfo> etreeInfoList, int type) {
		EtreeInfoService_MB service = null;
		SiteService_MB siteService = null;
		PwInfoService_MB pwService = null;
		VplsInfo vplsInfo = new VplsInfo();
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if(type == 0){
				pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
				service = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				etreeInfoList = service.selectAll(EServiceType.ETREE.getValue(), this.nameBefore);
			}
			Code code_type = (Code) ((ControlKeyValue) this.cmbtype.getSelectedItem()).getObject();
			if ("root".equals(code_type.getCodeValue())) {
				for (EtreeInfo root : etreeInfoList) {
					root.setRootName(siteService.getSiteName(root.getRootSite()));
					root.setBranchName(null);
					root.setAcNameList(this.getAcNameList(root.getAmostAcId()));
					if(type == 0){
						PwInfo pwCondition = new PwInfo();
						pwCondition.setPwId(root.getPwId());
						root.setPwName(pwService.selectBypwid_notjoin(pwCondition).getPwName());
					}
				}
			}else{
				for (EtreeInfo leaf : etreeInfoList) {
					leaf.setAcNameList(this.getAcNameList(leaf.getZmostAcId()));
					leaf.setRootName(null);
					leaf.setBranchName(siteService.getSiteName(leaf.getBranchSite()));
					if(type == 0){
						PwInfo pwCondition = new PwInfo();
						pwCondition.setPwId(leaf.getPwId());
						leaf.setPwName(pwService.selectBypwid_notjoin(pwCondition).getPwName());
					}
				}
			}
			vplsInfo.setVplsName(etreeInfoList.get(0).getName());
			vplsInfo.setActiveStatus(etreeInfoList.get(0).getActiveStatus());
			vplsInfo.setEtreeInfoList(etreeInfoList);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(pwService);
			UiUtil.closeService_MB(service);
			UiUtil.closeService_MB(siteService);
		}
		return vplsInfo;
	}
	
	/**
	 * 根据acId数组获取ac名称
	 * @param amostAcId
	 * @return
	 */
	private List<CommonBean> getAcNameList(String amostAcId) {
		AcPortInfoService_MB acService = null;
		List<CommonBean> acNameList = null;
		try {
			if(amostAcId != null){
				acService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
				List<Integer> acIdList = new ArrayList<Integer>();
				if(amostAcId.length() > 1){
					for (String id : amostAcId.split(",")) {
						acIdList.add(Integer.parseInt(id.trim()));
					}
				}else{
					acIdList.add(Integer.parseInt(amostAcId));
				}
				acNameList = new ArrayList<CommonBean>();
				List<AcPortInfo> acList = acService.select(acIdList);
				for (AcPortInfo acInfo : acList) {
					CommonBean acName = new CommonBean();
					acName.setAcName(acInfo.getName());
					acNameList.add(acName);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(acService);
		}
		return acNameList;
	}
	
	private void getAllAcId(List<Integer> acIdList,List<AcPortInfo> useAcPortList) throws Exception 
	{
		DefaultListModel defaultListModel = null;
		try 
		{
			defaultListModel = (DefaultListModel) this.listSelectAC.getModel();
			for (int i = 0; i < defaultListModel.getSize(); i++) 
			{
				acIdList.add(Integer.parseInt(((ControlKeyValue) defaultListModel.getElementAt(i)).getId()));
				useAcPortList.add((AcPortInfo)((ControlKeyValue) defaultListModel.getElementAt(i)).getObject());
			}
		} catch (Exception e) 
		{
			throw e;
		}finally
		{
			defaultListModel = null;
		}
	}
	
	/**
	 * 修改时，把界面新收集的etree和修改之前的etree进行整合
	 * 
	 * @param etreeInfoList_new
	 *            界面新收集的etree集合
	 * @throws Exception
	 */
	private void integrateEtreeList(List<EtreeInfo> etreeInfoList_new) throws Exception {

		EtreeInfo etreeInfo_update = null; // 匹配后的etree对象

		if(null == this.etreeInfoListForUpdate){
			return;
		}
		// 先把所有修改的etree数据改成删除状态，之后如果匹配到数据，会把状态改成其他状态
		for (EtreeInfo etreeInfo : this.etreeInfoListForUpdate) {
			etreeInfo.setAction(3);
		}

		// 便利所有新收集的数据，
		for (EtreeInfo etreeInfo_new : etreeInfoList_new) {
			etreeInfo_update = this.findEtree(etreeInfo_new);
			// 如果找到的etree不为null 说明有匹配项
			if (null != etreeInfo_update) {
				this.integrateEtree(etreeInfo_update, etreeInfo_new);
			} else {
				// 如果为null 说明没有匹配项目，是新增操作。
				// 把创建时间和创建人修改成以前的数据
				etreeInfo_new.setCreateTime(this.etreeInfoListForUpdate.get(0).getCreateTime());
				etreeInfo_new.setCreateUser(this.etreeInfoListForUpdate.get(0).getCreateUser());
				etreeInfo_new.setServiceId(this.etreeInfoListForUpdate.get(0).getServiceId());
				etreeInfo_new.setaXcId(this.etreeInfoListForUpdate.get(0).getaXcId());
				etreeInfo_new.setAction(2); // 把此etree记录标识为新增数据

				// 把此数据添加到要修改的etree集合中。
				this.etreeInfoListForUpdate.add(etreeInfo_new);
			}
		}
	}
	
	/**
	 * 整合两个etree对象。 把新的etree值传入要修改的etree对象中
	 * 
	 * @param etreeInfo_update
	 *            要修改的etree对象
	 * @param etreeInfo_new
	 *            界面新收集的etree对象
	 * @throws Exception
	 */
	private void integrateEtree(EtreeInfo etreeInfo_update, EtreeInfo etreeInfo_new) throws Exception {
		PwInfoService_MB pwInfoService = null;
		PwInfo pwinfo = null;
		AcPortInfoService_MB acInfoService = null;
		try {
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			// 如果修改了pwId 取之前的pw对象，并且更新etreeInfo_update中的pw、beforepw、action=1字段
			if (etreeInfo_update.getPwId() != etreeInfo_new.getPwId()) {
				pwinfo = new PwInfo();
				pwinfo.setPwId(etreeInfo_update.getPwId());
				etreeInfo_update.setBeforePw(pwInfoService.queryByPwId(pwinfo));
				etreeInfo_update.setPwId(etreeInfo_new.getPwId());
				etreeInfo_update.setAction(1);
			}
			// 如果修改了根端口 取之前的根端口对象，并且更新etreeInfo_update中的aAcId、BeforeRootAc、action=1字段
			if(null != etreeInfo_update.getAmostAcId()&& !isSame(etreeInfo_update.getAmostAcId(),etreeInfo_new.getAmostAcId()))
			{
				setBerforeAAcList(etreeInfo_update,etreeInfo_new.getAmostAcId(),1,etreeInfo_update.getAmostAcId());
				etreeInfo_update.setAmostAcId(etreeInfo_new.getAmostAcId());
				etreeInfo_update.setAction(1);
			}
			// 如果修改了叶子端口 取之前的叶子端口对象，并且更新etreeInfo_update中的zAcId、BeforeBranchAc、action=1字段
			if (null != etreeInfo_update.getZmostAcId()&&!isSame(etreeInfo_update.getZmostAcId(),etreeInfo_new.getZmostAcId())) 
			{
				setBerforeAAcList(etreeInfo_update,etreeInfo_new.getZmostAcId(),2,etreeInfo_update.getZmostAcId());
				etreeInfo_update.setZmostAcId(etreeInfo_new.getZmostAcId());
				etreeInfo_update.setAction(1);
			}
			// 赋其他修改参数
			etreeInfo_update.setName(etreeInfo_new.getName());
			etreeInfo_update.setActiveStatus(etreeInfo_new.getActiveStatus());
			etreeInfo_update.setClientId(etreeInfo_new.getClientId());
			// 如果action还等于3 说明上面三个条件没有成立，此时给此属性赋0=没有改变pw、根ac、叶ac
			if (etreeInfo_update.getAction() == 3) {
				etreeInfo_update.setAction(0);
			}

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(pwInfoService);
			UiUtil.closeService_MB(acInfoService);
		}
	}

	
	/**
	 * 给修改以前的AC赋值
	 * @param elanInfoAction
	 * @param mostAcId
	 * @param acIdList
	 * @param  label ==1:添加root节点 2添加支节点
	 */
	private void setBerforeAAcList(EtreeInfo etreeInfo_update,String mostAcId,int label,String oldMostACId) 
	{
		String[] acIds = oldMostACId.split(",");
		String[] acIdsUdate = mostAcId.split(",");
		Set<Integer> acSet = null;
		List<Integer> acList = null;
		AcPortInfoService_MB acInfoService = null;
		try {
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acSet = new HashSet<Integer>();
			acList = new ArrayList<Integer>();
			for(String acId : acIds)
			{
				if(!isExist(acId.trim(),acIdsUdate))
				{
					acSet.add(Integer.parseInt(acId.trim()));
				}
			}
			acList = new ArrayList<Integer>(acSet);
			if(acList.size() >0 && label ==1 )
			{
				etreeInfo_update.setBeforeAAcList(acInfoService.select(acList));
			}else if(acList.size() >0 && label ==2 )
			{
				etreeInfo_update.setBeforeZAcList(acInfoService.select(acList));
			}else if((acIdsUdate.length > acIds.length)&&acList.isEmpty())
			{
				if(label ==1)
				{
					etreeInfo_update.setBeforeAAcList(new ArrayList<AcPortInfo>());
				}else
				{
					etreeInfo_update.setBeforeZAcList(new ArrayList<AcPortInfo>());
				}
			}
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}finally
		{
			UiUtil.closeService_MB(acInfoService);
			acSet = null;
			acList = null;
			acIds = null;
		}
	}
	
	
	private boolean isExist(String trim, String[] acIdsUdate) {
		boolean flag = false;
		try 
		{
			for(String acId : acIdsUdate)
			{
				if(acId.trim().equals(trim))
				{
					flag = true;
					break;
				}
				
			}
			
		} catch (Exception e) 
		{
			ExceptionManage.dispose(e, getClass());
		}
		return flag;
	}
	
	 private boolean isSame(String updateAcString,String newAcString)
	  {
		  boolean flag = false;
		  UiUtil uiutil = null;
		  Set<Integer> updateAcSet = null;
		  Set<Integer> newAcSet = null;
		  try 
		  {
	        uiutil = new UiUtil();
	        updateAcSet = uiutil.getAcIdSets(updateAcString);
	        newAcSet = uiutil.getAcIdSets(newAcString);
	        if(updateAcSet.size() == newAcSet.size())
	        {
	        	newAcSet.removeAll(updateAcSet);
	 	        if(newAcSet.size() == 0)
	 	        {
	 	        	flag = true;
	 	        }
	        }
		  } catch (Exception e) 
		  {
			ExceptionManage.dispose(e, getClass());
		 }finally
		 {
			  uiutil = null;
			  updateAcSet = null;
			  newAcSet = null;
		 }
		return flag;
	  }
	 
	/**
	 * 通过新的etree数据中的rootsite和branchsite去要修改的etree集合中找。
	 * 
	 * @param etreeInfo_new
	 *            新的etree数据
	 * @return 如果找到了，把找到的etree返回。 如果没找到，返回null
	 */
	private EtreeInfo findEtree(EtreeInfo etreeInfo_new) {

		for (EtreeInfo etreeInfo : this.etreeInfoListForUpdate) {
			// 如果根网元和叶网元都相等，说明不是新增和删除操作。
			if (etreeInfo.getPwId() == etreeInfo_new.getPwId() ) {
				return etreeInfo;
			}
		}
		return null;
	}
	
	
	/**
	 * 验证etree中是否修改了根端口。 问题描述：如果叶子节点没有保留，全部删除后。 添加新叶子，此时。
	 * etree集合中的action都是2或者3，没有action=1 就没办法修改根的端口 此方法解决以上问题，
	 * 验证条件如下：
	 *      1.如果集合中的action没有1或者0的情况下。 找出action=2的第一条记录。 
	 *      2.验证此条记录的根端口是否与修改之前的根端口相等。 
	 *      3.如果不相等，把之前的端口对象查询出来，给此etree记录的BeforeRootAc赋值。
	 * 
	 * @throws Exception
	 */
	private void checkUpdateRoot() throws Exception {
		AcPortInfoService_MB acInfoService = null;
		try {
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			int etreeIndex = -1; // etree新增对象的索引。 如果集合中有action为0或1的情况。 此索引为-1 不操作。否则操作etree新增对象
			for (int i = 0; i < this.etreeInfoListForUpdate.size(); i++) {
				if (this.etreeInfoListForUpdate.get(i).getAction() == 0 || this.etreeInfoListForUpdate.get(i).getAction() == 1) {
					etreeIndex = -1;
					break;
				} else {
					if (this.etreeInfoListForUpdate.get(i).getAction() == 2) {
						etreeIndex = i;
					}
				}
			}
//			if (etreeIndex >= 0) {
//				if (this.etreeInfoListForUpdate.get(etreeIndex).getaAcId() != this.rootAcId) {
////					this.etreeInfoListForUpdate.get(etreeIndex).setBeforeRootAc(acInfoService.selectById(this.rootAcId));
//				}
//			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(acInfoService);
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
		DefaultListModel defaultListModel = null;
		DefaultListModel defaultListacModel = null;
		try {
			defaultListModel = (DefaultListModel) this.ListSelectPw.getModel();
			defaultListacModel = (DefaultListModel) this.listSelectAC.getModel();
			if (defaultListModel.getSize() == 0) 
			{
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_PW));
			flag = false;
			}else if(defaultListacModel.size() == 0)
			{
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_AC));
				flag = false;
			}
		} catch (Exception e) {
			throw e;
		} finally {
			defaultListModel = null;
			defaultListacModel= null;
		}

		return flag;
	}

	/**
	 * 验证选中的pw条数是否正确
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean isSelectPw() throws Exception {

		Code code_type = null;
		DefaultListModel defaultListModel = null;
		boolean flag = true;
		try {
			code_type = (Code) ((ControlKeyValue) this.cmbtype.getSelectedItem()).getObject();
			defaultListModel = (DefaultListModel) this.ListSelectPw.getModel();

			if ("root".equals(code_type.getCodeValue())) {
				if (defaultListModel.size()== 0) {
					flag = false;
				}
			} else {
				if (defaultListModel.size() != 1) {
					flag = false;
				}
			}

		} catch (Exception e) {
			throw e;
		} finally {
			code_type = null;
			defaultListModel = null;
		}
		return flag;
	}

	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagLayout componentLayout = new GridBagLayout();
		componentLayout.columnWidths = new int[] { 50, 150, 50, 100, 50 };
		componentLayout.columnWeights = new double[] { 0, 0, 0 };
		componentLayout.rowHeights = new int[] { 25, 40, 40, 40, 40, 40, 40, 40, 15, 40, 15 };
		componentLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0, 0, 0, 0, 0, 0, 0.2 };
		this.setLayout(componentLayout);

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 6;
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
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 15, 5);
		componentLayout.setConstraints(this.txtname, c);
		this.add(this.txtname);
		c.gridx = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.jButton, c);
		this.add(this.jButton);
		c.gridx = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 35, 15, 5);
		componentLayout.setConstraints(this.lbltype, c);
		this.add(this.lbltype);
		c.gridx = 4;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 15, 5);
		componentLayout.setConstraints(this.cmbtype, c);
		this.add(this.cmbtype);
		
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 4;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblAC, c);
		this.add(this.lblAC);
		c.gridx = 1;
		componentLayout.setConstraints(this.slpAC, c);
		this.add(this.slpAC);
		c.gridx = 3;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.slpSelectAC, c);
		this.add(this.slpSelectAC);

		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.btnAcRight, c);
		this.add(this.btnAcRight);
		c.gridy = 4;
		componentLayout.setConstraints(this.btnAcLeft, c);
		this.add(this.btnAcLeft);
		

//		c.gridx = 0;
//		c.gridy = 2;
//		c.gridwidth = 1;
//		componentLayout.setConstraints(this.lblport, c);
//		this.add(this.lblport);
//		c.gridx = 1;
//		componentLayout.setConstraints(this.cmbport, c);
//		this.add(this.cmbport);
//		c.gridx = 2;
//		componentLayout.setConstraints(this.lblac, c);
//		this.add(this.lblac);
//		c.gridx = 3;
//		c.gridwidth = 2;
//		componentLayout.setConstraints(this.cmbac, c);
//		this.add(this.cmbac);
		
		
		

		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.lblactivate, c);
		this.add(this.lblactivate);
		c.gridx = 1;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		componentLayout.setConstraints(this.chkactivate, c);
		this.add(this.chkactivate);

		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 7;
		c.gridheight = 4;
		componentLayout.setConstraints(this.lblPw, c);
		this.add(this.lblPw);
		c.gridx = 1;
		componentLayout.setConstraints(this.slpPw, c);
		this.add(this.slpPw);
		c.gridx = 3;
		c.gridwidth = 2;
		componentLayout.setConstraints(this.slpSelectPw, c);
		this.add(this.slpSelectPw);

		c.fill = GridBagConstraints.NONE;
		c.gridx = 2;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 1;
		componentLayout.setConstraints(this.btnRight, c);
		this.add(this.btnRight);
		c.gridy = 8;
		componentLayout.setConstraints(this.btnLeft, c);
		this.add(this.btnLeft);

		c.anchor = GridBagConstraints.EAST;
		c.gridx = 3;
		c.gridy = 11;
		componentLayout.setConstraints(this.btnSave, c);
		this.add(this.btnSave);
		c.gridx = 4;
		componentLayout.setConstraints(this.btnCanel, c);
		this.add(this.btnCanel);

	}

	/**
	 * 初始化控件
	 * 
	 * @throws Exception
	 */
	private void initComponent() throws Exception {
		this.lblMessage = new JLabel();
		this.btnSave = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true);
		this.btnCanel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		this.lblname = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));

		jButton = new javax.swing.JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_NAME));
		this.txtname = new PtnTextField(true, PtnTextField.STRING_MAXLENGTH, this.lblMessage, this.btnSave, this);
		this.lblactivate = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_ACTIVITY_STATUS));
		this.chkactivate = new JCheckBox();
		
//		this.lblac = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOAD_AC));
//		this.cmbac = new JComboBox();
//		this.lblport = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOAD_PORT));
//		this.cmbport = new JComboBox();
		
		/*********添加多AC端口 2015-3-10 张坤***********/
		lblAC = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOAD_AC));
		listAC = new JList();
		slpAC = new JScrollPane();
		slpAC.setViewportView(listAC); 
		listSelectAC = new JList();
		slpSelectAC = new JScrollPane();
		slpSelectAC.setViewportView(listSelectAC);	
		btnAcLeft = new JButton("<<");
		btnAcRight = new JButton(">>");
		
		
		this.lbltype = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE));
		this.cmbtype = new JComboBox();
		this.lblPw = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOAD_PW));
		this.listPw = new JList();
		this.slpPw = new JScrollPane();
		this.slpPw.setViewportView(listPw);
		this.ListSelectPw = new JList();
		this.slpSelectPw = new JScrollPane();
		this.slpSelectPw.setViewportView(ListSelectPw);
		this.btnLeft = new JButton("<<");
		this.btnRight = new JButton(">>");
		if(null!=this.etreeInfo){
			this.chkactivate.setSelected(this.etreeInfo.getActiveStatus()==1?true:false);
			this.cmbtype.setEnabled(false);
		}
	}

	/**
	 * 修改时添加自身AC
	 */
//	private void addAcForUpdate() {
//		if(null == this.etreeAc)
//			return;
//		if(this.etreeAc.getPortId()>0)
//			if(((ControlKeyValue)this.cmbport.getSelectedItem()).getId().equals(this.etreeAc.getPortId()+""))
//				((DefaultComboBoxModel)cmbac.getModel()).addElement(new ControlKeyValue(this.etreeAc.getId() + "", this.etreeAc.getName(), this.etreeAc));
//		
//		if(this.etreeAc.getLagId()>0)
//			if(((ControlKeyValue)this.cmbport.getSelectedItem()).getId().equals(this.etreeAc.getLagId()+""))
//				((DefaultComboBoxModel)cmbac.getModel()).addElement(new ControlKeyValue(this.etreeAc.getId() + "", this.etreeAc.getName(), this.etreeAc));
//	}
	
	private JLabel lblname;
	private JTextField txtname;
//	private JLabel lblport;
	private JButton jButton;
//	private JComboBox cmbport;
//	private JLabel lblac;
//	private JComboBox cmbac;
	private JLabel lblactivate;
	private JCheckBox chkactivate;
	private JLabel lbltype;
	private JComboBox cmbtype;
	private PtnButton btnSave;
	private JButton btnCanel;
	private JLabel lblPw;
	private JScrollPane slpPw;
	private JList listPw;
	private JScrollPane slpSelectPw;
	private JList ListSelectPw;
	private JButton btnLeft;
	private JButton btnRight;
	private JLabel lblMessage;
	/***********多AC**************/
	private JLabel lblAC;
	private JScrollPane slpAC;
	private JList listAC;
	private JScrollPane slpSelectAC;
	private JList listSelectAC;
	private JButton btnAcLeft;
	private JButton btnAcRight;
}
