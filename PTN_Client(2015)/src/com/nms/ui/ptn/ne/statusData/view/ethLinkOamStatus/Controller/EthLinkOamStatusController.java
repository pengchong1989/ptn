package com.nms.ui.ptn.ne.statusData.view.ethLinkOamStatus.Controller;

import java.awt.event.ActionEvent;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.oamStatus.OamStatusInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
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
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.statusData.view.ethLinkOamStatus.view.EthLinkOamStatusPanel;

public class EthLinkOamStatusController {

private EthLinkOamStatusPanel ethLinkOamStatusPanel ;
	
	public EthLinkOamStatusController(EthLinkOamStatusPanel ethLinkOamStatusPanel){
		this.ethLinkOamStatusPanel = ethLinkOamStatusPanel;
		 addListention();
	}

	//监听事件
	private void addListention() {
		
		ethLinkOamStatusPanel.getFindButton().addActionListener(new MyActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SiteInst siteInst = null;
				SiteService_MB siteService = null;
				DispatchUtil siteDispatchUtil = null;
				OamStatusInfo OamStatusInfo = null;
				
				try {
					siteService = (SiteService_MB)ConstantUtil.serviceFactory.newService_MB(Services.SITE);
					siteInst = siteService.select(ConstantUtil.siteId);
					siteInst.setStatusMark(56);//状态码
					ControlKeyValue selecttunnel = (ControlKeyValue) ethLinkOamStatusPanel.getOamPortComboBox().getSelectedItem();
					PortInst portInst = (PortInst) selecttunnel.getObject();
					siteInst.setParameter(portInst.getNumber());				
					siteDispatchUtil = new DispatchUtil(RmiKeys.RMI_SITE);
					OamStatusInfo = siteDispatchUtil.oamStatus(siteInst);
					if(OamStatusInfo != null && OamStatusInfo.getOamLinkStatusInfo() != null){
						ethLinkOamStatusPanel.refresh(OamStatusInfo.getOamLinkStatusInfo());
						DialogBoxUtil.succeedDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
						this.insertOpeLog(EOperationLogType.ETHLINKOAMSTATUS.getValue(),ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS), null,null);
					}else{
						DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_FIND_DETAIL_FAIL));
						this.insertOpeLog(EOperationLogType.ETHLINKOAMSTATUS.getValue(), ResourceUtil.srcStr(StringKeysTip.TIP_FIND_DETAIL_FAIL), null,null);
					}
				} catch (Exception e2) {
					ExceptionManage.dispose(e2,this.getClass());
				}finally{
					UiUtil.closeService_MB(siteService);
				}
			}

			private void insertOpeLog(int operationType, String result, Object oldMac, Object newMac){
				AddOperateLog.insertOperLog(ethLinkOamStatusPanel.getFindButton(), operationType, result, oldMac, newMac, ConstantUtil.siteId,ResourceUtil.srcStr(StringKeysObj.ETH_OAM_STATUS_OAMLINKSTATUS),"");
			}
			@Override
			public boolean checking() {
				// TODO Auto-generated method stub
				return true;
			}
		});
	}
}
