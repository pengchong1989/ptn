package com.nms.service.impl.wh;

import java.util.ArrayList;
import java.util.List;

import com.nms.db.bean.ptn.oam.OamEthernetInfo;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.drive.service.bean.ETHOAMAllObject;
import com.nms.drive.service.bean.ETHOAMObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.model.ptn.oam.OamEthreNetService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.SynchroUtil;
import com.nms.service.impl.util.TypeAndActionUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.SiteLockTypeUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class EthOAMConfigWHServiceImpl extends WHOperationBase implements OperationServiceI {
	/**
	 *删除
	 */
	@Override
	public String excutionDelete(List objectList) throws Exception {
		OamEthreNetService_MB oamInfoService = null;
		List<Integer> siteIdList = null;
		OperationObject operationObject = null;
		OperationObject operationObjectResult = null;
		List<OamInfo> oamInfoList = null;
		List<OamEthernetInfo> oamEthernetInfoUpdata = null;
		List<OamEthernetInfo> oamEthFromObjectList=null;
		OamEthernetInfo oamEthernetInfo = null;
		
		try {
			
			oamEthFromObjectList=new ArrayList<OamEthernetInfo>();
			oamEthernetInfo = new OamEthernetInfo();
			oamInfoList = objectList;
			siteIdList = new ArrayList<Integer>();
			oamEthernetInfoUpdata = new ArrayList<OamEthernetInfo>();
			for(OamInfo oamInfo:oamInfoList){
				if(oamInfo.getOamEthernetInfo()!=null){
					oamEthFromObjectList.add(oamInfo.getOamEthernetInfo());
					siteIdList.add(oamInfo.getOamEthernetInfo().getSiteId());
				}
			}
			oamEthernetInfo=oamEthFromObjectList.get(0);
			oamInfoService = (OamEthreNetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OAMETHERNET);
			
			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,oamEthernetInfo);
			if(null!=siteCheck){
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(oamInfoList.get(0).getOamEthernetInfo().getSiteId());
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			// 锁着网元
			super.lockSite(siteIdList, SiteLockTypeUtil.ETHCOAM_INSERT);
			for(OamEthernetInfo OamEthernetInfoOther:oamEthFromObjectList){
				// 删除 先将数据库中的要删除的数据取出来
				oamEthernetInfoUpdata .addAll(oamInfoService.queryByNeID(OamEthernetInfoOther));
				// 在删除数据
				oamInfoService.delete(OamEthernetInfoOther);
			}
			// 下发设备
			operationObject = this.getOperationObject(oamEthernetInfo.getSiteId(),"ethOAMConfig");
			super.sendAction(operationObject);
			operationObjectResult = super.verification(operationObject);
			if (operationObjectResult.isSuccess()) {
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				// 如果失败 将还原删除的数据
				for(OamEthernetInfo OamEthInfo:oamEthernetInfoUpdata){
					oamInfoService.insert(OamEthInfo);
				}
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			super.clearLock(siteIdList);
			UiUtil.closeService_MB(oamInfoService);
			siteIdList = null;
			operationObject = null;
			oamInfoList = null;
			oamEthernetInfoUpdata = null;
			oamEthernetInfo = null;
		}
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		List<OamInfo> oamInfoList = (List<OamInfo>) object;
		List<Integer> siteIdList = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		OamEthreNetService_MB oamEthreNetService = null;
		OamEthernetInfo oamEthernetInfo = null;
		List<OamEthernetInfo> oamEthernetInfoUpdata = null;
		int inserResule = 0;
		try {
			oamEthernetInfo = new OamEthernetInfo();
			oamEthernetInfoUpdata = new ArrayList<OamEthernetInfo>();
			oamEthreNetService = (OamEthreNetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OAMETHERNET);
			siteIdList = new ArrayList<Integer>();
			oamEthernetInfo = oamInfoList.get(0).getOamEthernetInfo();
			siteIdList.add(oamEthernetInfo.getSiteId());
			// 锁着网元
			super.lockSite(siteIdList, SiteLockTypeUtil.ETHCOAM_INSERT);

			//虚拟网元操作
			SiteUtil siteUtil = new SiteUtil();
			String siteCheck =(String) siteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SAVEANDUPDATE,oamEthernetInfo);
			if(null!=siteCheck){
				List<Integer> siteList = new ArrayList<Integer>();
				siteList.add(oamEthernetInfo.getSiteId());
				WhImplUtil whImplUtil = new WhImplUtil();
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+whImplUtil.getNeNames(siteList)+ResultString.NOT_ONLINE_SUCCESS;
			}
			// 创建时，先入库 在查询所有的下设备
			if (oamEthernetInfo.getId() <= 0) {
				inserResule = oamEthreNetService.insert(oamEthernetInfo);
			}
			// 更新
			else {
				oamEthernetInfoUpdata = oamEthreNetService.queryByNeID(oamEthernetInfo);
				oamEthreNetService.update(oamEthernetInfo);
			}
			// 下设备
			operationObjectAfter = this.getOperationObject(oamEthernetInfo.getSiteId(),"ethOAMConfig");
			super.sendAction(operationObjectAfter);
			operationObjectResult = super.verification(operationObjectAfter);
			if (operationObjectResult.isSuccess()) {
				super.clearLock(siteIdList);
				return operationObjectResult.getActionObjectList().get(0).getStatus();
			} else {
				// 下设备失败时删除新建的数据
				if (oamEthernetInfo.getId() <= 0) {
					OamEthernetInfo other = new OamEthernetInfo();
					other.setSiteId(oamEthernetInfo.getSiteId());
					other.setId(inserResule);
					oamEthreNetService.delete(other);
				}
				// 下设备失败时把已经跟新的数据还原
				else {
					oamEthreNetService.update(oamEthernetInfoUpdata.get(0));
				}
				super.clearLock(siteIdList);
				return super.getErrorMessage(operationObjectResult);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			oamInfoList = null;
			super.clearLock(siteIdList);
			oamInfoList = null;
			siteIdList = null;
			operationObjectAfter = null;
			UiUtil.closeService_MB(oamEthreNetService);
			oamEthernetInfo = null;
			oamEthernetInfoUpdata = null;
		}
	}

	private OperationObject getOperationObject(int siteId,String type) {
		OperationObject operationObject = null;
		NEObject neObject = null;
		ActionObject actionObject = null;
		ETHOAMAllObject ethOAMBugControlObject = null;
		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType(type);
			ethOAMBugControlObject = new ETHOAMAllObject();
			ethOAMBugControlObject.setEthoamObjectList(this.setTmcOamBugContrlList(siteId));
			actionObject.setEthoamAllObject(ethOAMBugControlObject);
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			ethOAMBugControlObject = null;
			neObject = null;
			actionObject = null;
		}
		return operationObject;
	}

	private List<ETHOAMObject> setTmcOamBugContrlList(int siteId) {
		OamEthreNetService_MB oamEthreNetService = null;
		List<ETHOAMObject> ethoamList=null;
		List<OamEthernetInfo> OamEthernetInfoOher = null;
		OamEthernetInfo oamEthernetInfo=null;
		try {
			ethoamList=new ArrayList<ETHOAMObject>();
			oamEthernetInfo=new OamEthernetInfo();
			oamEthernetInfo.setSiteId(siteId);
			OamEthernetInfoOher=new ArrayList<OamEthernetInfo>();
			oamEthreNetService = (OamEthreNetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OAMETHERNET);
			OamEthernetInfoOher=oamEthreNetService.queryByNeIDSide(oamEthernetInfo);
			
			for (OamEthernetInfo oamEthernetInfo2 : OamEthernetInfoOher) {
				ETHOAMObject ethoamInfo=new ETHOAMObject();
				ethoamInfo.setThernetOAMEnabl(oamEthernetInfo2.getThernetOAMEnabl());
				ethoamInfo.setMdMLevel(oamEthernetInfo2.getMdMLevel());
				ethoamInfo.setMpLable(oamEthernetInfo2.getMpLable());
				ethoamInfo.setMdName(oamEthernetInfo2.getMdName());
				ethoamInfo.setMdLevel(oamEthernetInfo2.getMdLevel());
				ethoamInfo.setMaName(oamEthernetInfo2.getMaName());
				ethoamInfo.setCcmsend(oamEthernetInfo2.getCcmsend());
				ethoamInfo.setVlan(oamEthernetInfo2.getVlan());
				ethoamInfo.setMepId(oamEthernetInfo2.getMepId());
				ethoamInfo.setMepType(oamEthernetInfo2.getMepType());
				ethoamInfo.setPort(oamEthernetInfo2.getPort());
				ethoamInfo.setCcmSendEnable(oamEthernetInfo2.getCcmSendEnable());
				ethoamInfo.setCcmReceiveEnable(oamEthernetInfo2.getCcmReceiveEnable());
				ethoamInfo.setCcmPriority(oamEthernetInfo2.getCcmPriority());
				ethoamInfo.setLbmTlvType(oamEthernetInfo2.getLbmTlvType());
				ethoamInfo.setLbmTlvTypeLength(oamEthernetInfo2.getLbmTlvTypeLength());
				ethoamInfo.setLbmPriority(oamEthernetInfo2.getLbmPriority());
				ethoamInfo.setLbmDiscard(oamEthernetInfo2.getLbmDiscard());
				ethoamInfo.setLtmPriority(oamEthernetInfo2.getLtmPriority());
				ethoamInfo.setAisSendEnabel(oamEthernetInfo2.getAisSendEnabel());
				ethoamInfo.setClientMdLevel(oamEthernetInfo2.getClientMdLevel());
				ethoamInfo.setAisPriority(oamEthernetInfo2.getAisPriority());
				ethoamInfo.setLckSendEnabel(oamEthernetInfo2.getLckSendEnabel());
				ethoamInfo.setLckPriority(oamEthernetInfo2.getLckPriority());
				ethoamInfo.setAisLckSendCycle(oamEthernetInfo2.getAisLckSendCycle());
				ethoamInfo.setTstSendEnabel(oamEthernetInfo2.getTstSendEnabel());
				ethoamInfo.setTstSendLevel(oamEthernetInfo2.getTstSendLevel());
				ethoamInfo.setTstPriority(oamEthernetInfo2.getTstPriority());
				ethoamInfo.setSendWay(oamEthernetInfo2.getSendWay());
				ethoamInfo.setTstDiscard(oamEthernetInfo2.getTstDiscard());
				ethoamInfo.setTstTlvType(oamEthernetInfo2.getTstTlvType());
				ethoamInfo.setTstTlvLength(oamEthernetInfo2.getTstTlvLength());
				ethoamInfo.setTstSendCycle(oamEthernetInfo2.getTstSendCycle());
				ethoamInfo.setLmENable(oamEthernetInfo2.getLmENable());
				ethoamInfo.setLmPriority(oamEthernetInfo2.getLmPriority());
				ethoamInfo.setLmSendCycle(oamEthernetInfo2.getLmSendCycle());
				ethoamInfo.setDmENable(oamEthernetInfo2.getDmENable());
				ethoamInfo.setDmPriority(oamEthernetInfo2.getDmPriority());
				ethoamInfo.setDmSendCycle(oamEthernetInfo2.getDmSendCycle());
				ethoamInfo.setRemoteMepId1(oamEthernetInfo2.getRemoteMepId1());
				ethoamInfo.setRemoteMepId2(oamEthernetInfo2.getRemoteMepId2());
				ethoamInfo.setRemoteMepId3(oamEthernetInfo2.getRemoteMepId3());
				ethoamInfo.setRemoteMepId4(oamEthernetInfo2.getRemoteMepId4());
				ethoamInfo.setMipCreate(oamEthernetInfo2.getMipCreate());
				ethoamInfo.setMipPort(oamEthernetInfo2.getMipPort());
				ethoamInfo.setMipSlot(oamEthernetInfo2.getMipSlot());
				ethoamInfo.setSlot(oamEthernetInfo2.getSlot());
				ethoamInfo.setItemNumber(oamEthernetInfo2.getItemNumber());
				ethoamInfo.setMacAddress1(CoderUtils.transformMac(oamEthernetInfo2.getMacAddress1()));
				ethoamInfo.setMacAddress2(CoderUtils.transformMac(oamEthernetInfo2.getMacAddress2()));
				ethoamInfo.setMacAddress3(CoderUtils.transformMac(oamEthernetInfo2.getMacAddress3()));
				ethoamInfo.setMacAddress4(CoderUtils.transformMac(oamEthernetInfo2.getMacAddress4()));
				ethoamInfo.setTstPurposeMepMac(CoderUtils.transformMac(oamEthernetInfo2.getTstPurposeMepMac()));
				ethoamList.add(ethoamInfo);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(oamEthreNetService);
			OamEthernetInfoOher = null;
			oamEthernetInfo=null;
		}
		
		return ethoamList;
	}
	
	@Override
	public String excutionUpdate(Object object) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object synchro(int siteId) throws Exception {
			OperationObject operationObject = new OperationObject();
			OperationObject operationObjectResult = null;
			try {
				operationObject = this.getOperationObject(siteId,"ethOamSynchro");// 封装下发数据
				super.sendAction(operationObject);
				operationObjectResult = super.verification(operationObject);
				if (operationObjectResult.isSuccess()) {// 返回成功
					for (ActionObject actionObject : operationObjectResult.getActionObjectList()) {
						this.synchroETHoam_db(actionObject, siteId);// tunnel,与数据库进行同步
					}

				}
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			} finally {
				
			}
		return ResultString.CONFIG_SUCCESS;
	}
	/**
	 * 与数据库进行同步
	 * 
	 * @param tunnelObjectList
	 * @param siteId
	 */
	private void synchroETHoam_db(ActionObject actionObject, int siteId) {
		List<OamEthernetInfo> OamInfoList = null;            
		OamInfoList = getETHoamInfo(actionObject.getEthoamAllObject().getEthoamObjectList(), siteId);
		OamEthreNetService_MB oamInfoService = null;
		try {
			//先删除所有的数据
			oamInfoService = (OamEthreNetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OAMETHERNET);
			oamInfoService.deleteBySiteId(siteId);
			//在跟新数据库
			for (OamEthernetInfo oamInfo : OamInfoList) {
				try {
					oamInfo.setSiteId(siteId);
					SynchroUtil.ethOamSynchro(oamInfo);
				} catch (Exception e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}
		} catch (Exception e1) {
			ExceptionManage.dispose(e1, getClass());
		}finally{
			UiUtil.closeService_MB(oamInfoService);
		}
		
	}
	/**
	 * 封装ETHoam信息
	 * 
	 * @param tunnelObjectList
	 * @param siteId
	 * @return
	 */
	public List<OamEthernetInfo> getETHoamInfo(List<ETHOAMObject> ethOamObjectList, int siteId) {
		List<OamEthernetInfo> oamEthList = new ArrayList<OamEthernetInfo>();

		for (ETHOAMObject oamInfo : ethOamObjectList) {
			OamEthernetInfo oamEthernetInfo =new OamEthernetInfo();
			oamEthernetInfo.setSiteId(ConstantUtil.siteId);
			oamEthernetInfo.setThernetOAMEnabl(oamInfo.getThernetOAMEnabl());
			oamEthernetInfo.setMdMLevel(oamInfo.getMdMLevel());
			oamEthernetInfo.setMpLable(oamInfo.getMpLable());
			oamEthernetInfo.setMdName(oamInfo.getMdName());
			oamEthernetInfo.setMdLevel(oamInfo.getMdLevel());
			oamEthernetInfo.setMaName(oamInfo.getMaName());
			oamEthernetInfo.setCcmsend(oamInfo.getCcmsend());
			oamEthernetInfo.setVlan(oamInfo.getVlan());
			oamEthernetInfo.setMepId(oamInfo.getMepId());
			oamEthernetInfo.setMepType(oamInfo.getMepType());
			oamEthernetInfo.setPort(oamInfo.getPort());
			oamEthernetInfo.setCcmSendEnable(oamInfo.getCcmSendEnable());
			oamEthernetInfo.setCcmReceiveEnable(oamInfo.getCcmReceiveEnable());
			oamEthernetInfo.setCcmPriority(oamInfo.getCcmPriority());
			oamEthernetInfo.setLbmTlvType(oamInfo.getLbmTlvType());
			oamEthernetInfo.setLbmTlvTypeLength(oamInfo.getLbmTlvTypeLength());
			oamEthernetInfo.setLbmPriority(oamInfo.getLbmPriority());
			oamEthernetInfo.setLbmDiscard(oamInfo.getLbmDiscard());
			oamEthernetInfo.setLtmPriority(oamInfo.getLtmPriority());
			oamEthernetInfo.setAisSendEnabel(oamInfo.getAisSendEnabel());
			oamEthernetInfo.setClientMdLevel(oamInfo.getClientMdLevel());
			oamEthernetInfo.setAisPriority(oamInfo.getAisPriority());
			oamEthernetInfo.setLckSendEnabel(oamInfo.getLckSendEnabel());
			oamEthernetInfo.setLckPriority(oamInfo.getLckPriority());
			oamEthernetInfo.setAisLckSendCycle(oamInfo.getAisLckSendCycle());
			oamEthernetInfo.setTstSendEnabel(oamInfo.getTstSendEnabel());
			oamEthernetInfo.setTstSendLevel(oamInfo.getTstSendLevel());
			oamEthernetInfo.setTstPurposeMepMac(oamInfo.getTstPurposeMepMac());
			oamEthernetInfo.setTstPriority(oamInfo.getTstPriority());
			oamEthernetInfo.setSendWay(oamInfo.getSendWay());
			oamEthernetInfo.setTstDiscard(oamInfo.getTstDiscard());
			oamEthernetInfo.setTstTlvType(oamInfo.getTstTlvType());
			oamEthernetInfo.setTstTlvLength(oamInfo.getTstTlvLength());
			oamEthernetInfo.setTstSendCycle(oamInfo.getTstSendCycle());
			oamEthernetInfo.setLmENable(oamInfo.getLmENable());
			oamEthernetInfo.setLmPriority(oamInfo.getLmPriority());
			oamEthernetInfo.setLmSendCycle(oamInfo.getLmSendCycle());
			oamEthernetInfo.setDmENable(oamInfo.getDmENable());
			oamEthernetInfo.setDmPriority(oamInfo.getDmPriority());
			oamEthernetInfo.setDmSendCycle(oamInfo.getDmSendCycle());
			oamEthernetInfo.setRemoteMepId1(oamInfo.getRemoteMepId1());
			oamEthernetInfo.setMacAddress1(oamInfo.getMacAddress1());
			oamEthernetInfo.setRemoteMepId2(oamInfo.getRemoteMepId2());
			oamEthernetInfo.setMacAddress2(oamInfo.getMacAddress2());
			oamEthernetInfo.setRemoteMepId3(oamInfo.getRemoteMepId3());
			oamEthernetInfo.setMacAddress3(oamInfo.getMacAddress3());
			oamEthernetInfo.setRemoteMepId4(oamInfo.getRemoteMepId4());
			oamEthernetInfo.setMacAddress4(oamInfo.getMacAddress4());
			oamEthernetInfo.setMipCreate(oamInfo.getMipCreate());
			oamEthernetInfo.setMipPort(oamInfo.getMipPort());
			oamEthernetInfo.setMipSlot(oamInfo.getMipSlot());
			oamEthernetInfo.setSlot(oamInfo.getSlot());
			oamEthernetInfo.setItemNumber(oamInfo.getItemNumber());
			oamEthList.add(oamEthernetInfo);
		}
		
		return oamEthList;
	}
}
