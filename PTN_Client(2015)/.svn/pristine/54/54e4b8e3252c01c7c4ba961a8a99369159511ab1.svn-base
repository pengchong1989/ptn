package com.nms.ui.ptn.ne.ethernetLoop.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.EthLoopInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.EthLoopServcie_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.ne.ethernetLoop.view.EthernetLoopPanel;

 public class EthLoopController {
	 
 private EthernetLoopPanel ethernetLoopPanel ;
 
 public EthLoopController(EthernetLoopPanel ethernetLoopPanel){
	 this.ethernetLoopPanel =  ethernetLoopPanel;
	 addListention();
	 try {
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
 }

 
	/**
	 * 初始化界面值
	 */
	private void init() {
		EthLoopServcie_MB ethLoopServcie = null;
		List<EthLoopInfo> EthLoopInfoList = null;
		EthLoopInfo thLoopInfo = null;
		try {
			ethLoopServcie = (EthLoopServcie_MB) ConstantUtil.serviceFactory.newService_MB(Services.ETHLOOPSERVICE);
			EthLoopInfoList = ethLoopServcie.select(ConstantUtil.siteId);
			if(EthLoopInfoList != null && EthLoopInfoList.size()>0){
				thLoopInfo = EthLoopInfoList.get(0);
				this.ethernetLoopPanel.setThLoopInfo(thLoopInfo);
				this.ethernetLoopPanel.refresh(thLoopInfo);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(ethLoopServcie);
		}
	}
	private void addListention() {
		
		this.ethernetLoopPanel.getConfirm().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EthLoopServcie_MB ethLoopServcie = null;
				String result = "";
				EthLoopInfo thLoopInfo = null;
				DispatchUtil ethLoopDispatch = null;
				List<EthLoopInfo> ethLoopInfoList=null;
				EthLoopInfo ethLoop = null;
				try {
					thLoopInfo = new EthLoopInfo();
					thLoopInfo = EthLoopController.this.ethernetLoopPanel.get(thLoopInfo);
					ethLoopDispatch = new DispatchUtil(RmiKeys.RMI_EHTLOOP);
					ethLoopServcie = (EthLoopServcie_MB) ConstantUtil.serviceFactory.newService_MB(Services.ETHLOOPSERVICE);
					ethLoopInfoList=ethLoopServcie.select(thLoopInfo.getSiteId());
					if(ethLoopInfoList!=null && ethLoopInfoList.size()>0){
						result = ethLoopDispatch.excuteUpdate(thLoopInfo);
						thLoopInfo.setPortName(this.selectProt(thLoopInfo.getPortNumber()+"", ConstantUtil.siteId, 0));
						ethLoop=ethLoopInfoList.get(0);
						ethLoop.setPortName(this.selectProt(ethLoop.getPortNumber()+"", ConstantUtil.siteId, 0));
						this.insertOpeLog(EOperationLogType.ETHLOOPSERVICEUPDATE.getValue(), result, ethLoop,thLoopInfo);
					}else{
						thLoopInfo.setPortName(this.selectProt(thLoopInfo.getPortNumber()+"", ConstantUtil.siteId, 0));
						result = ethLoopDispatch.excuteInsert(thLoopInfo);
						this.insertOpeLog(EOperationLogType.ETHLOOPSERVICEINSERT.getValue(), result, null,thLoopInfo);
					}
					ethernetLoopPanel.refresh(thLoopInfo);
					DialogBoxUtil.succeedDialog(ethernetLoopPanel, result);
					
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}finally{
					UiUtil.closeService_MB(ethLoopServcie);
					result = null;
					thLoopInfo = null;
					ethLoopDispatch = null;
					ethLoopInfoList=null;
				}
			}

	  private void insertOpeLog(int operationType, String result, EthLoopInfo oldMac, EthLoopInfo newMac){	
		AddOperateLog.insertOperLog(ethernetLoopPanel.getConfirm(), operationType, result, oldMac, newMac, newMac.getSiteId(),ResourceUtil.srcStr(StringKeysLbl.LBL_ETH_LOOP),"EthLoopInfo");		
	}
	  
	  
	  private String selectProt(String number,int siteId,int portId) {
			PortService_MB portService = null;
			PortInst portInst = null;
			List<PortInst> allportInstList = null;
			String portName = null;
			try {
				allportInstList=new ArrayList<PortInst>();
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				portInst = new PortInst();
				portInst.setSiteId(siteId);
				portInst.setNumber(Integer.parseInt(number));
				portInst.setPortId(portId);
				allportInstList = portService.select(portInst);
				if(allportInstList!=null&&allportInstList.size()>0){
					 portName = allportInstList.get(0).getPortName();
				}
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally {
				UiUtil.closeService_MB(portService);
				portInst = null;
				allportInstList = null;
			}
			return portName;
		}
		});
	}
	

}
