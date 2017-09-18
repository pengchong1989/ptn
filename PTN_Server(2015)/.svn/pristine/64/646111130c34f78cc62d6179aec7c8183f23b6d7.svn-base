package com.nms.service.impl.cx;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.clock.PortConfigInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.drivechenxiao.service.bean.CXNEObject;
import com.nms.drivechenxiao.service.bean.protocols.PtpPortObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.clock.PortDispositionInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
/**
 * 端口配置
 * @author Administrator
 *
 */
public class TimePortConfigCXServiceImpl extends CXOperationBase implements OperationServiceI {

	@Override
	public String excutionInsert(Object object) throws Exception {
		OperationObject operationObject = null;
		String result = null;
		PortConfigInfo portConfigInfo = null;
		try {
			portConfigInfo = (PortConfigInfo) object;
			SiteUtil siteUtil=new SiteUtil();
			if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(portConfigInfo.getSiteId())&&0==siteUtil.SiteTypeUtil(portConfigInfo.getSiteId())) {
			operationObject = this.convertOperation(operationObject, portConfigInfo, TypeAndActionUtil.ACTION_INSERT);
			super.sendAction(operationObject);
			operationObject=super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
				
			} else {
				result = super.getErrorMessage(operationObject);
			}
			}
		} catch (BusinessIdException e) {
			throw e;
		} finally {
			operationObject = null;
			portConfigInfo = null;
		}
		return result;
	}
	@Override
	public String excutionUpdate(Object object) throws Exception {
		OperationObject operationObject = null;
		String result = null;
		PortConfigInfo portConfigInfo = null;
		try {
			
			portConfigInfo = (PortConfigInfo) object;
			SiteUtil siteUtil=new SiteUtil();
			if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(portConfigInfo.getSiteId())&&0==siteUtil.SiteTypeUtil(portConfigInfo.getSiteId())) {
			operationObject = this.convertOperation(operationObject, portConfigInfo, TypeAndActionUtil.ACTION_UPDATE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
			}
		} catch (BusinessIdException e) {
			throw e;
		} finally {
			operationObject = null;
			portConfigInfo = null;
		}
		return result;
	}

	@Override
	public String excutionDelete(List objectList) throws Exception {
		OperationObject operationObject = null;
		String result = null;
		List<PortConfigInfo> portConfigInfoList = null;
		try {
			portConfigInfoList = objectList;
			operationObject = this.convertOperationForList(operationObject, portConfigInfoList, TypeAndActionUtil.ACTION_DELETE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			operationObject = null;
			portConfigInfoList = null;
		}
		return result;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
		String result = null;
		PortConfigInfo portConfigInfo = null;
		OperationObject operationObject = null;
		PortDispositionInfoService_MB portDispositionInfoService=null;
		
		try {
			portConfigInfo = new PortConfigInfo();
			portConfigInfo.setSiteId(siteId);
			portDispositionInfoService=(PortDispositionInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PortDispositionInfoService);
			if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(siteId)) {
				operationObject = this.getOperationObject(siteId);
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
					portDispositionInfoService.update(portConfigInfo);
					this.getPortObject(operationObject.getCxActionObjectList().get(0).getPtpPortList(), siteId);
				}
			}
//			portConfigInfo = new PortConfigInfo();
//			portConfigInfo.setSiteId(siteId);
//			portDispositionInfoService=(PortDispositionInfoService) ConstantUtil.serviceFactory.newService(Services.PortDispositionInfoService);
//			if (EManufacturer.CHENXIAO.getValue() == super.getManufacturer(siteId)) {
//				operationObject = this.convertOperation(operationObject, portConfigInfo, TypeAndActionUtil.ACTION_SYNCHRO);
//				super.sendAction(operationObject);
//				operationObject = super.verification(operationObject);
//				if (operationObject.isSuccess()) {
//					portDispositionInfoService.update(portConfigInfo);
//					this.getPortObject(operationObject.getCxActionObjectList().get(0).getPtpPortList(), siteId);
//				}
//			}
		} catch (Exception e) {
			throw e;
		}finally{
			portConfigInfo = null;
			UiUtil.closeService_MB(portDispositionInfoService);
		}
		return result;
		
	}
	/**
	 *根据ID  封装对象
	 * @return
	 * @throws Exception
	 */
	private OperationObject getOperationObject(int id) throws Exception {
		OperationObject operationObject = null;
		CXActionObject cxActionObject = null;
		CXNEObject cxneObject = null;
		try {
			operationObject = new OperationObject();
			cxneObject = super.getCXNEObject(id);
			cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setType(TypeAndActionUtil.TYPE_PORTCONFIG);
			cxActionObject.setAction(TypeAndActionUtil.ACTION_SYNCHRO);
			cxActionObject.setCxNeObject(cxneObject);
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			throw e;
		}
		return operationObject;
	}
	private OperationObject convertOperation(OperationObject operationObject,
			PortConfigInfo portConfigInfo, String action) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(portConfigInfo.getSiteId()));
			cxActionObject.setAction(action);
			cxActionObject.setType(TypeAndActionUtil.TYPE_PORTCONFIG);
			cxActionObject.setPtpPortObject(this.getPtpPortObject(portConfigInfo));
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			throw e;
		}
		return operationObject;
	}
	
	private OperationObject convertOperationForList(OperationObject operationObject,
			List<PortConfigInfo> portConfigInfoList, String action) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(portConfigInfoList.get(0).getSiteId()));
			cxActionObject.setAction(action);
			cxActionObject.setType(TypeAndActionUtil.TYPE_PORTCONFIG);
			for(PortConfigInfo portConfigInfo:portConfigInfoList){
				cxActionObject.getPtpPortList().add(this.getPtpPortObject(portConfigInfo));
			}
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			throw e;
		}
		return operationObject;
	}

	/**获取陈晓驱动层端口对象 
	 * @throws Exception 
	 * @throws Exception **/
	private PtpPortObject getPtpPortObject(PortConfigInfo portConfigInfo) throws Exception {
		if (portConfigInfo == null) {
			throw new Exception("portConfigInfo is null");
		}
		PtpPortObject ptpPort = null;
		PortService_MB portService = null;
		try {
			ptpPort=new PtpPortObject();
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			ptpPort.setPortname(portService.getPortname(portConfigInfo.getPort()));
			ptpPort.setEnable(portConfigInfo.getPortEnable()==1 ?"false":"true");//端口使能
			//System.out.println(UiUtil.getCodeById(portConfigInfo.getDelayMechanism()).getCodeValue());
			ptpPort.setDelaymechanism(1+"");//延时机制 (e2e) e2e=1端到端 , p2p=2 点到点
			ptpPort.setVlanid(portConfigInfo.getVlanID());//vlan号 (1) ; int [0,4094]
			ptpPort.setPortstate(portConfigInfo.getPortStatus());//端口状态(init); init=1初始化 ,fault=2错误状态
			//UiUtil.getCodeById( portConfigInfo.getOperationMode()).getCodeValue()
			ptpPort.setOperationmode(0+"");//操作模式 (auto) ; auto=0自动选源 ,mast
			ptpPort.setAnncintv(portConfigInfo.getAnncPacketsInterval()+"");//annc时间间隔 (1) int [-4,4]
			ptpPort.setAnnctmo(portConfigInfo.getAnncTimeoutSetting()+"");//annc接收超时(4) int [1,65535]
			ptpPort.setSyncintv(portConfigInfo.getSyncPacketsInterval()+"");//Sync时间间隔 (0) int [-8,1]
			ptpPort.setDelreqintv(portConfigInfo.getDelay_ReqPacketsInterval()+"");//DelayReq时间间隔(0) int [-4,4]
			ptpPort.setPdelreqintv(portConfigInfo.getPdel_ReqPacketsInterval()+"");//pdelreq时间间隔(0) int [-4,4]
			ptpPort.setDelayoffset(portConfigInfo.getLineDelayCompensation());//测出来的延时补偿(0); int  [-10000000,10000000]
			//UiUtil.getCodeById( portConfigInfo.getInterfaceType()).getCodeValue()
			ptpPort.setPorttype( 1+"");//接口类型
			ptpPort.setTwo_step(portConfigInfo.getTimeStampMode()==1? "false": "true");//// 时间戳模式
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
		
		return ptpPort;
	}
	/**
	 * 同步
	 * @param clockPortESObjectList
	 * @param siteId
	 * @throws Exception
	 */
	private void getPortObject(List<PtpPortObject> ptpPortObject, int siteId) throws Exception {

		if (null == ptpPortObject) {
			throw new Exception("ptpPort is null");
		}
		PortInst portInst = null;
		 // 下面if()条件  都是 在同步时 portConfigInfo 的某些值为null 或者  0一面在查  coad表时报错（0）
		List<PortInst> portList = new ArrayList<PortInst>();
		PortService_MB portService = null;
		PortConfigInfo portConfigInfo = null;
		SynchroUtil synchroUtil = new SynchroUtil();
		
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			for (PtpPortObject ptpPort : ptpPortObject) {
				portInst = new PortInst();
				portInst.setPortName(ptpPort.getPortname());
				portList = portService.select(portInst);
				portConfigInfo = new PortConfigInfo();
				portConfigInfo.setSiteId(siteId);
				if(ptpPort.getIsPtp()){
					if(portList.size()>0){  //查看  数据库中是否有  portId  与 设备的端口       字段
						portConfigInfo.setPort(portList.get(0).getPortId());
					}else{//没有
						
					}
					if("true".equals(ptpPort.getEnable())){
						portConfigInfo.setPortEnable(1);
					}
					else{
						portConfigInfo.setPortEnable(0);
					}
					//端口使能
					if(!"".equals(ptpPort.getDelaymechanism())){
						portConfigInfo.setDelayMechanism(UiUtil.getCodeByValue("delayMechanism",ptpPort.getDelaymechanism()).getId());//延时机制 (e2e) e2e=1端到端 , p2p=2 点到点
					}
					
					portConfigInfo.setVlanID(ptpPort.getVlanid());//vlan号 (1) ; int [0,4094]
					portConfigInfo.setPortStatus(ptpPort.getPortstate());//端口状态(init); init=1初始化 ,fault=2错误状态
					if(!"".equals(ptpPort.getOperationmode())){
						portConfigInfo.setOperationMode(UiUtil.getCodeByValue("operationModeT", ptpPort.getOperationmode()).getId());//操作模式 (auto) ; auto=0自动选源 ,mast
					}
					if(!"".equals(ptpPort.getAnncintv())){
						portConfigInfo.setAnncPacketsInterval(UiUtil.getCodeByValue("PTPTimePort", ptpPort.getAnncintv()).getId());//annc时间间隔 (1) int [-4,4]
					}
					portConfigInfo.setAnncTimeoutSetting(ptpPort.getAnnctmo());//annc接收超时(4) int [1,65535]
					if(!("".equals(ptpPort.getSyncintv())||null==ptpPort.getSyncintv())){
						switch(Integer.parseInt(ptpPort.getSyncintv())){
						case -8:
							portConfigInfo.setSyncPacketsInterval(4);
							break;
						case -7:
							portConfigInfo.setSyncPacketsInterval(3);
							break;
						case -6:
							portConfigInfo.setSyncPacketsInterval(2);
							break;
						default:
							portConfigInfo.setSyncPacketsInterval(UiUtil.getCodeByValue("PTPTimePort", ptpPort.getSyncintv()).getId());
							break;
						}
					}
					
					//	portConfigInfo.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
					//portConfigInfo.setSyncPacketsInterval(Integer.parseInt(ptpPort.getSyncintv()));//Sync时间间隔 (0) int [-8,1]
					if(!"".equals(ptpPort.getDelreqintv())){
						portConfigInfo.setDelay_ReqPacketsInterval(UiUtil.getCodeByValue("PTPTimePort", ptpPort.getDelreqintv()).getId());//DelayReq时间间隔(0) int [-4,4]
					}
					if(!"".equals(ptpPort.getPdelreqintv())){
						portConfigInfo.setPdel_ReqPacketsInterval(UiUtil.getCodeByValue("PTPTimePort", ptpPort.getPdelreqintv()).getId());//pdelreq时间间隔(0) int [-4,4]
					}
					
					portConfigInfo.setLineDelayCompensation(ptpPort.getDelayoffset());//测出来的延时补偿(0); int  [-10000000,10000000]
					if(!"".equals(ptpPort.getPorttype())){
						portConfigInfo.setInterfaceType(UiUtil.getCodeByValue("interfaceType", ptpPort.getPorttype()).getId());//接口类型
					}
					
					if("false".equals(ptpPort.getTwo_step())){
						portConfigInfo.setTimeStampMode(0);
					}else{
						portConfigInfo.setTimeStampMode(1);
					}
					synchroUtil.portSynchro(portConfigInfo, siteId);
				}
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
	}
}
