package com.nms.ui.ptn.business.dialog.cespath.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.client.Client;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortStmTimeslot;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.enums.ECesType;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EServiceType;
import com.nms.model.client.ClientService_MB;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.port.PortStmTimeslotService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.AutoNamingUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.VerifyNameUtil;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.business.dialog.cespath.AddCesDialog;

public class CesHandlerController {
	private final AddCesDialog dialog;

	public CesHandlerController(AddCesDialog dialog) {
		this.dialog = dialog;
		addListeners();
	}

	private void addListeners() {
		this.dialog.getSubmit().addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				submit();
			}

			@Override
			public boolean checking() {
				return checkValue();
			}
		});
		this.dialog.getServiceTypeCbox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.getPwVector().removeAllElements();
				dialog.getSelpwVector().removeAllElements();
				dialog.getPortTable_a().clear();
				dialog.getPortTable_z().clear();
			}
		});
		this.dialog.getLeftBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (dialog.getSelPwList().getSelectedValue() != null) {
					Object obj = dialog.getSelPwList().getSelectedValue();
					dialog.getSelpwVector().remove(obj);
					dialog.getPwVector().add(obj);
					dialog.getPwList().setListData(dialog.getPwVector());
					dialog.getSelPwList().setListData(dialog.getSelpwVector());
				}
			}
		});

		this.dialog.getRightBtn().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (dialog.getPwList().getSelectedValue() != null) {
					Object obj = dialog.getPwList().getSelectedValue();
					dialog.getPwVector().remove(obj);
					dialog.getSelpwVector().add(obj);
					dialog.getPwList().setListData(dialog.getPwVector());
					dialog.getSelPwList().setListData(dialog.getSelpwVector());
				}
			}
		});
		this.dialog.getAutoNamingButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				autoNamingActionPerformed();
			}
		});
	}
	private void submit() {

		//判断pw承载的端口与A表端口与Z表端口的一致性
		ControlKeyValue kv = null;
		List<Integer> aTablePortIdList = new ArrayList<Integer>();
		List<Integer> zTablePortIdList = new ArrayList<Integer>();
		List<CesInfo> cesInfoList = null;
		List<PwInfo> pwInfoList = null;
		DispatchUtil cesDispatch=null;
		String isSuccess=null;
		try {
			pwInfoList = new ArrayList<PwInfo>();
			cesInfoList = new ArrayList<CesInfo>();

			//验证
//			if (!validateData()) {
//				return ;
//			}
			for (Object obj : this.dialog.getSelpwVector()) {
				if (obj instanceof ControlKeyValue) {
					kv = (ControlKeyValue) obj;
					pwInfoList.add((PwInfo) kv.getObject());
				}
			}
			for (PwInfo pw : pwInfoList) {
				cesInfoList.add(this.dialog.get(pw));
			}
			//a端和z端表中数据
			this.dialog.getAtablePortIdList(aTablePortIdList);
			this.dialog.getZtablePortIdList(zTablePortIdList);

			int length = aTablePortIdList.size();
			for (int i = 0; i < length; i++) {
				if(null!=this.dialog.getCesInfo()&&(aTablePortIdList.get(i)!=this.dialog.getCesInfo().getaAcId()||zTablePortIdList.get(i)!=this.dialog.getCesInfo().getzAcId())){
					//按照业务类型  给 端口和原端口赋值

					if(this.dialog.getCesInfo().getCestype() == ECesType.PDH.getValue()){
						cesInfoList.get(i).setBeforeAPort(initPDHPort(this.dialog.getCesInfo().getaSiteId(),this.dialog.getCesInfo().getaAcId()));
						cesInfoList.get(i).setBeforeZPort(initPDHPort(this.dialog.getCesInfo().getzSiteId(),this.dialog.getCesInfo().getzAcId()));
						cesInfoList.get(i).setAction(1);
						cesInfoList.get(i).setaAcName(cesInfoList.get(i).getBeforeAPort().getPortName());
						cesInfoList.get(i).setzAcName(cesInfoList.get(i).getBeforeZPort().getPortName());
					}
					if(this.dialog.getCesInfo().getCestype() == ECesType.SDH.getValue()){
						cesInfoList.get(i).setBeforeAPortStmTime(initSDHPort(this.dialog.getCesInfo().getaSiteId(),this.dialog.getCesInfo().getaAcId()));
						cesInfoList.get(i).setBeforeZPortStmTime(initSDHPort(this.dialog.getCesInfo().getzSiteId(),this.dialog.getCesInfo().getzAcId()));
						cesInfoList.get(i).setAction(1);
						cesInfoList.get(i).setaAcName(cesInfoList.get(i).getBeforeAPortStmTime().getTimeslotname());
						cesInfoList.get(i).setzAcName(cesInfoList.get(i).getBeforeZPortStmTime().getTimeslotname());
					}	
					if(this.dialog.getCesInfo().getCestype() == ECesType.PDHSDH.getValue()){
						cesInfoList.get(i).setBeforeAPort(initPDHPort(this.dialog.getCesInfo().getaSiteId(),this.dialog.getCesInfo().getaAcId()));
						cesInfoList.get(i).setBeforeZPortStmTime(initSDHPort(this.dialog.getCesInfo().getzSiteId(),this.dialog.getCesInfo().getzAcId()));
						cesInfoList.get(i).setAction(1);
						cesInfoList.get(i).setaAcName(cesInfoList.get(i).getBeforeAPort().getPortName());
						cesInfoList.get(i).setzAcName(cesInfoList.get(i).getBeforeZPortStmTime().getTimeslotname());
					}	
					if(this.dialog.getCesInfo().getCestype() == ECesType.SDHPDH.getValue()){
						cesInfoList.get(i).setBeforeAPortStmTime(initSDHPort(this.dialog.getCesInfo().getaSiteId(),this.dialog.getCesInfo().getaAcId()));
						cesInfoList.get(i).setBeforeZPort(initPDHPort(this.dialog.getCesInfo().getzSiteId(),this.dialog.getCesInfo().getzAcId()));
						cesInfoList.get(i).setAction(1);
						cesInfoList.get(i).setaAcName(cesInfoList.get(i).getBeforeAPortStmTime().getTimeslotname());
						cesInfoList.get(i).setzAcName(cesInfoList.get(i).getBeforeZPort().getPortName());
					}	
					
				}
				cesInfoList.get(i).setaAcId(aTablePortIdList.get(i));
				cesInfoList.get(i).setzAcId(zTablePortIdList.get(i));
			}
			
//			if(this.dialog.getCesInfo()==null||!cesInfoList.get(0).getName().equalsIgnoreCase(this.dialog.getCesInfo().getName())){
//				VerifyNameUtil verifyNameUtil=new VerifyNameUtil();
//				if(verifyNameUtil.verifyName(EServiceType.CES.getValue(), cesInfoList.get(0).getName(), null)){
//					DialogBoxUtil.errorDialog(this.dialog, ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST));
//					return;
//				}
//			}
			
			cesDispatch = new DispatchUtil(RmiKeys.RMI_CES);
			if(this.dialog.getCesInfo()!=null){
				//更新
				CesInfo cesBefore = this.getCesBefore(cesInfoList.get(0));
				isSuccess = cesDispatch.excuteUpdate(cesInfoList.get(0));
				//添加日志记录
				 this.getAcName(cesInfoList.get(0));
				AddOperateLog.insertOperLog(this.dialog.getSubmit(), EOperationLogType.CESUPDATE.getValue(), isSuccess, 
						cesBefore, cesInfoList.get(0), cesInfoList.get(0).getaSiteId(), cesInfoList.get(0).getName(), "ces");
				 AddOperateLog.insertOperLog(this.dialog.getSubmit(), EOperationLogType.CESUPDATE.getValue(), isSuccess, 
						 cesBefore, cesInfoList.get(0), cesInfoList.get(0).getzSiteId(), cesInfoList.get(0).getName(), "ces");
			}else{
				//插入
				for (int j = 0; j < cesInfoList.size(); j++) {
					cesInfoList.get(j).setName(cesInfoList.get(j).getName()+("_"+j));
				}
				 isSuccess = cesDispatch.excuteInsert(cesInfoList);
				//添加日志记录
				 for (CesInfo ces : cesInfoList) {
					 this.getAcName(ces);
					 AddOperateLog.insertOperLog(this.dialog.getSubmit(), EOperationLogType.CESINSERT.getValue(), isSuccess, 
							 null, ces, ces.getaSiteId(), ces.getName(), "ces");
					 AddOperateLog.insertOperLog(this.dialog.getSubmit(), EOperationLogType.CESINSERT.getValue(), isSuccess, 
							 null, ces, ces.getzSiteId(), ces.getName(), "ces");
				}
			}
			DialogBoxUtil.succeedDialog(this.dialog, isSuccess);
			this.dialog.dispose();
			this.dialog.getCesPathPanel().getController().refresh();

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			cesDispatch=null;
			isSuccess=null;
			cesInfoList = null;
			pwInfoList = null;
			kv=null;
			isSuccess=null;
			
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
			before = cesService.selectByid(before);
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
	
	private void getAcName(CesInfo ces) throws Exception{
		if(ces.getCestype() == ECesType.PDH.getValue()){
			ces.setaAcName(initPDHPort(ces.getaSiteId(),ces.getaAcId()).getPortName());
			ces.setzAcName(initPDHPort(ces.getzSiteId(),ces.getzAcId()).getPortName());
		}else if(ces.getCestype() == ECesType.SDH.getValue()){
			ces.setaAcName(initSDHPort(ces.getaSiteId(),ces.getaAcId()).getTimeslotname());
			ces.setzAcName(initSDHPort(ces.getzSiteId(),ces.getzAcId()).getTimeslotname());
		}else if(ces.getCestype() == ECesType.PDHSDH.getValue()){
			ces.setaAcName(initPDHPort(ces.getaSiteId(),ces.getaAcId()).getPortName());
			ces.setzAcName(initSDHPort(ces.getzSiteId(),ces.getzAcId()).getTimeslotname());
		}else if(ces.getCestype() == ECesType.SDHPDH.getValue()){
			ces.setaAcName(initSDHPort(ces.getaSiteId(),ces.getaAcId()).getTimeslotname());
			ces.setzAcName(initPDHPort(ces.getzSiteId(),ces.getzAcId()).getPortName());
		}
	}
	
	/**
	 * 验证数据的正确性
	 * @return
	 */
	private boolean checkValue() {
		boolean flag = false;
		List<CesInfo> cesInfoList = null;
		List<PwInfo> pwInfoList = null;
		//判断pw承载的端口与A表端口与Z表端口的一致性
		ControlKeyValue kv = null;
		try {
			pwInfoList = new ArrayList<PwInfo>();
			cesInfoList = new ArrayList<CesInfo>();
			//验证
			if (!validateData()) {
				return false;
			}
			//验证名称是否已经存在
			for (Object obj : this.dialog.getSelpwVector()) {
				if (obj instanceof ControlKeyValue) {
					kv = (ControlKeyValue) obj;
					pwInfoList.add((PwInfo) kv.getObject());
				}
			}
			for (PwInfo pw : pwInfoList) {
				cesInfoList.add(this.dialog.get(pw));
			}
			if(this.dialog.getCesInfo()==null||!cesInfoList.get(0).getName().equalsIgnoreCase(this.dialog.getCesInfo().getName())){
				VerifyNameUtil verifyNameUtil=new VerifyNameUtil();
				if(verifyNameUtil.verifyName(EServiceType.CES.getValue(), cesInfoList.get(0).getName(), null)){
					DialogBoxUtil.errorDialog(this.dialog, ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST));
					return false;
				}
			}
			
			flag = true;
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return flag;
	}
	/**
	 * 数据验证
	 * @return
	 */
	private boolean validateData() {
		try {
			if (this.dialog.getPortTable_a().getRowCount() == 0) {
				DialogBoxUtil.errorDialog(this.dialog, ResourceUtil.srcStr(StringKeysTip.TIP_APORTTABLEISZERO));
				return false;
			}
			if (this.dialog.getPortTable_a().getRowCount() == 0) {
				DialogBoxUtil.errorDialog(this.dialog, ResourceUtil.srcStr(StringKeysTip.TIP_ZPORTTABLEISZERO));
				return false;
			}
			
			if (this.dialog.getSiteId_a() == this.dialog.getSiteId_z()) {
				DialogBoxUtil.errorDialog(this.dialog, ResourceUtil.srcStr(StringKeysTip.TIP_ZPORTTABLEISZERO));
				return false;
			}
			if (this.dialog.getPortTable_a().getRowCount() != this.dialog.getPortTable_z().getRowCount()) {
				DialogBoxUtil.errorDialog(this.dialog, ResourceUtil.srcStr(StringKeysTip.TIP_ATOZPROTNUMBERISNOTSAME));
				return false;
			}
			int pwNumbers = this.dialog.getPortTable_a().getRowCount();
			if (this.dialog.getSelpwVector().size() != pwNumbers) {
				DialogBoxUtil.errorDialog(this.dialog, ResourceUtil.srcStr(StringKeysTip.TIP_SELECTPWISNOTENOUGH));
				return false;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return true;
	}

	public void refresh() {

	}
	/**
	 * 自动命名
	 */
	public void autoNamingActionPerformed() {
		CesInfo cesInfo=null;
		try {
			cesInfo = new CesInfo();
			cesInfo.setIsSingle(0);
			AutoNamingUtil autoNamingUtil=new AutoNamingUtil();
			String autoNaming = (String) autoNamingUtil.autoNaming(cesInfo, null, null);
			this.dialog.getTfName().setText(autoNaming);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			cesInfo=null;
		}
	}

	/**
	 * 查询pdh端口
	 * @param siteId 网元ID
	 * @param portId 端口ID
	 * @return
	 * @throws Exception
	 */
	public PortInst initPDHPort(int siteId ,int portId) throws Exception {
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
	public PortStmTimeslot initSDHPort(int siteId ,int portId) throws Exception {
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
}
