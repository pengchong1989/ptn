package com.nms.service.impl.cx;

import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamLinkInfo;
import com.nms.drivechenxiao.service.bean.oam.EfmObject;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.CXActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.CXOperationBase;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.ui.manager.BusinessIdException;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
/**
 * 以太网链路OAM实现类
 * @author dzy
 *
 */
public class ETHLinkOamNodeCXServiceImpl extends CXOperationBase implements OperationServiceI {
	/**
	 * 添加以太网链路OAM
	 */
	@Override
	public String excutionInsert(Object object) throws Exception, BusinessIdException {
		OperationObject operationObject = null;
		OamInfo oamInfo = null;
		String result = null;
		try {
			oamInfo = (OamInfo) object;
			operationObject = this.convertOperation(operationObject,oamInfo, TypeAndActionUtil.ACTION_INSERT);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {
				result = super.getErrorMessage(operationObject);
			}
		} catch (BusinessIdException e) {
			throw e;
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
			oamInfo = null;
		}
		return result;
	}
	
	/**
	 * 修改以太网链路OAM
	 */
	@Override
	public String excutionUpdate(Object object) throws Exception {
		OperationObject operationObject = null;
		OamInfo oamInfo = null;
		String result = null;
		try {
			oamInfo = (OamInfo) object;
			operationObject = this.convertOperation(operationObject,oamInfo, TypeAndActionUtil.ACTION_UPDATE);
			super.sendAction(operationObject);
			super.verification(operationObject);
			if (operationObject.isSuccess()) {
				result = operationObject.getCxActionObjectList().get(0).getStatus();
			} else {		
				result = super.getErrorMessage(operationObject);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
			oamInfo = null;
		}
		return result;
	}
	/**
	 * 删除以太网链路OAM
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String excutionDelete(List objectList) throws Exception {
		OperationObject operationObject = null;
		List<OamInfo> oamInfoList = null;
		String result = null;
		try {
			oamInfoList = objectList;
			for(OamInfo oamInfo:oamInfoList){
				operationObject = this.convertOperation(operationObject,oamInfo, TypeAndActionUtil.ACTION_DELETE);
				super.sendAction(operationObject);
				operationObject = super.verification(operationObject);
				if (operationObject.isSuccess()) {
					result = operationObject.getCxActionObjectList().get(0).getStatus();
				} else {
					result = super.getErrorMessage(operationObject);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObject = null;
			oamInfoList = null;
		}
		return result;
	}
	/**
	 * 同步以太网链路OAM
	 */
	@Override
	public Object synchro(int siteId) throws Exception {
		String result = null;
		OperationObject operationObject = null;
		OamInfo oamInfo =  new OamInfo();
		OamLinkInfo oamLinkInfo = new OamLinkInfo();
		oamLinkInfo.setSiteId(siteId);
		oamInfo.setOamLinkInfo(oamLinkInfo);
		OamInfoService_MB oamInfoService = null;
		try {
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			operationObject = this.convertOperation(operationObject, oamInfo, TypeAndActionUtil.ACTION_SYNCHRO);
			super.sendAction(operationObject);
			operationObject = super.verification(operationObject);
			if (operationObject.isSuccess()) {
				//修改状态，设置成设备上没有
				oamInfoService.updateEquipExitStatusForEthLinkOam(siteId, 1);
				this.getEthLinkOamObject(operationObject.getCxActionObjectList().get(0).getEfmObjectList(), siteId);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
		oamInfo = null;
		return result;
	}
	/**
	 * 获得msp对象
	 * 
	 * @param siteId
	 * @param action
	 * @return
	 * @throws Exception
	 */
	public OperationObject convertOperation(OperationObject operationObject, OamInfo oamInfo, String action) throws Exception {
		operationObject = new OperationObject();
		try {
			CXActionObject cxActionObject = new CXActionObject();
			cxActionObject.setActionId(super.getActionId(operationObject.getCxActionObjectList()));
			cxActionObject.setCxNeObject(super.getCXNEObject(oamInfo.getOamLinkInfo().getSiteId()));
			cxActionObject.setAction(action);
			cxActionObject.setType(TypeAndActionUtil.TYPE_ETHLINKOAM);
			cxActionObject.setEfmObject((EfmObject)this.getEfmObject(oamInfo));
			operationObject.getCxActionObjectList().add(cxActionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return operationObject;
	}
	/**
	 * 转换MspObject对象
	 * @param mspProtect   mspProtect对象
	 * @return 
	 * @throws Exception
	 */
	private EfmObject getEfmObject(OamInfo oamInfo) throws Exception {
		EfmObject efmObject = new EfmObject();
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			OamLinkInfo oamLinkInfo = oamInfo.getOamLinkInfo();
			List<PortInst> portList;
			PortInst portInst = new PortInst();
			if(0!=oamLinkInfo.getObjId()){
				portInst.setPortId(oamLinkInfo.getObjId());
				portList = portService.select(portInst);
				if(null!=portList&&0!=portList.size()){
					portInst = portList.get(0);
				}
			}
			efmObject.setPortname(portInst.getPortName());
			efmObject.setEnable(("false".equals(oamLinkInfo.isOamEnable())?0:1)+"");
			efmObject.setErrsymbperiod(oamLinkInfo.getErrorSymboEventCycle()+"");
			efmObject.setErrsymbthreshold(oamLinkInfo.getErrorSymboEventThreshold()+"");
			efmObject.setErrfrmperiod(oamLinkInfo.getErrorFrameEventCycle()+"");
			efmObject.setErrfrmthreshold(oamLinkInfo.getErrorFrameEventThreshold()+"");
			efmObject.setErrfrmperiodperiond(oamLinkInfo.getErrorFrameCycleEventCycle()+"");
			efmObject.setErrfrmperiondthreshold(oamLinkInfo.getErrorFrameCycleEventThreshold()+"");
			efmObject.setEfffrmsecondsperiod(oamLinkInfo.getErrorFrameSecondEventCycle()+"");
			efmObject.setErrfrmsecondsthreshold(oamLinkInfo.getErrorFrameSecondEventThreshold()+"");
			if(0!=oamLinkInfo.getMode()){
				efmObject.setWorkmode(UiUtil.getCodeById(oamLinkInfo.getMode()).getCodeValue()); 
			}
			
			efmObject.setRmtloopback(oamLinkInfo.getRemoteLoop()+"");
			efmObject.setLpbtimeout(oamLinkInfo.getResponseOutTimeThreshold()+"");
			efmObject.setUnidir(oamLinkInfo.getUnDirection()+"");
			efmObject.setMaxoampdu(oamLinkInfo.getMaxFrameLength()+"");
			efmObject.setLosttime(oamLinkInfo.getLinkfailCycle()+"");
			if(0!=oamLinkInfo.getSendCycle()){
				efmObject.setFrequecy(UiUtil.getCodeById(oamLinkInfo.getSendCycle()).getCodeValue());
			}
			efmObject.setLinkevent(oamLinkInfo.getLinkEvent()+"");
			efmObject.setVarretr(oamLinkInfo.getMib()+"");
			efmObject.setOui(oamLinkInfo.getOrganicId()+"");
			efmObject.setVsi(oamLinkInfo.getFactoryInfo()+"");
			oamLinkInfo.setEquipExit(0);
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
		return efmObject;
	}
	/**
	 * 与数据库同步
	 * 
	 * 
	 * @param mspObjectList  得到的oamLinkInfo结果集
	 * @param siteId  网元id
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	private void getEthLinkOamObject(List<EfmObject> mspObjectList, int siteId) throws Exception {

		if (null == mspObjectList) {
			throw new Exception("mspObjectList is null");
		}
		PortInst portInstWork;
		OamLinkInfo oamLinkInfo ;
		OamInfo oamInfo ;
		PortService_MB portService = null;
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			SynchroUtil synchroUtil = new SynchroUtil();
			for (EfmObject efmObject : mspObjectList) {
				if(efmObject.getisOAM()){
					oamLinkInfo = new OamLinkInfo();
					oamInfo = new OamInfo();
					portInstWork = new PortInst();
					portInstWork.setSiteId(siteId);
					portInstWork.setPortName(efmObject.getPortname());
					portInstWork = portService.select(portInstWork).get(0);
					oamLinkInfo.setObjId(portInstWork.getPortId());
					oamLinkInfo.setOamEnable("0".equals(efmObject.getEnable())?false:true);
					oamLinkInfo.setErrorSymboEventCycle(Integer.parseInt(efmObject.getErrsymbperiod()));
					oamLinkInfo.setErrorSymboEventThreshold(Integer.parseInt(efmObject.getErrsymbthreshold()));
					oamLinkInfo.setErrorFrameEventCycle(Integer.parseInt(efmObject.getErrfrmperiod()));
					oamLinkInfo.setErrorFrameEventThreshold(Integer.parseInt(efmObject.getErrfrmthreshold()));
					oamLinkInfo.setErrorFrameCycleEventCycle(Integer.parseInt(efmObject.getErrfrmperiodperiond()));
					oamLinkInfo.setErrorFrameCycleEventThreshold(Integer.parseInt(efmObject.getErrfrmperiondthreshold()));
					if(null!=efmObject.getEfffrmsecondsperiod()&&!"".equals(efmObject.getEfffrmsecondsperiod())){
						oamLinkInfo.setErrorFrameSecondEventCycle(Integer.parseInt(efmObject.getEfffrmsecondsperiod()));
					}
					oamLinkInfo.setErrorFrameSecondEventThreshold(Integer.parseInt(efmObject.getErrfrmsecondsthreshold()));
					oamLinkInfo.setMode(UiUtil.getCodeByValue("WORKMODELOFOAM", efmObject.getWorkmode()).getId()); 
					oamLinkInfo.setRemoteLoop("false".equals(efmObject.getRmtloopback())?0:1);
					oamLinkInfo.setResponseOutTimeThreshold(Integer.parseInt(efmObject.getLpbtimeout()));
					oamLinkInfo.setUnDirection("false".equals(efmObject.getUnidir())?0:1);
					oamLinkInfo.setMaxFrameLength(Integer.parseInt(efmObject.getMaxoampdu()));
					oamLinkInfo.setLinkfailCycle(Integer.parseInt(efmObject.getLosttime()));
					oamLinkInfo.setSendCycle(UiUtil.getCodeByValue("CONTRACTPERIOD", efmObject.getFrequecy()).getId());
					oamLinkInfo.setLinkEvent("false".equals(efmObject.getLinkevent())?0:1);
					oamLinkInfo.setMib("false".equals(efmObject.getVarretr())?0:1);
					oamLinkInfo.setOrganicId(Integer.parseInt(efmObject.getOui()));
					oamLinkInfo.setFactoryInfo(Integer.parseInt(efmObject.getVsi()));
					oamLinkInfo.setEquipExit(0);
					oamInfo.setOamLinkInfo(oamLinkInfo);
					synchroUtil.oamLinkInfoSynchro(oamInfo, siteId);
				}
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
		}
	}
}
