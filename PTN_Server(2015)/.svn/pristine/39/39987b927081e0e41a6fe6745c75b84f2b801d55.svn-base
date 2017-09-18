package com.nms.service.impl.wh;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.manager.UpgradeManage;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.ARPInfo;
import com.nms.db.bean.ptn.BfdStatus;
import com.nms.db.bean.ptn.E1LegStatus;
import com.nms.db.bean.ptn.LagPortStateInfo;
import com.nms.db.bean.ptn.MacStudyBean;
import com.nms.db.bean.ptn.PortStatus;
import com.nms.db.bean.ptn.ProtectStatus;
import com.nms.db.bean.ptn.PtpBasicStatus;
import com.nms.db.bean.ptn.PtpPortStatus;
import com.nms.db.bean.ptn.PwProtectStatus;
import com.nms.db.bean.ptn.PwStatus;
import com.nms.db.bean.ptn.SiteStatusInfo;
import com.nms.db.bean.ptn.TunnelStatus;
import com.nms.db.bean.ptn.VpwsStatus;
import com.nms.db.bean.ptn.WrappingStatus;
import com.nms.db.bean.ptn.lagStatusInfo;
import com.nms.db.bean.ptn.clock.FrequencySource;
import com.nms.db.bean.ptn.clock.FrequencyStatusInfo;
import com.nms.db.bean.ptn.oamStatus.InsertLinkOamInfo;
import com.nms.db.bean.ptn.oamStatus.OamLinkStatusInfo;
import com.nms.db.bean.ptn.oamStatus.OamMepInst;
import com.nms.db.bean.ptn.oamStatus.OamPingFrame;
import com.nms.db.bean.ptn.oamStatus.OamStatusInfo;
import com.nms.db.bean.ptn.oamStatus.OamTraceHops;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.bean.system.Field;
import com.nms.drive.service.bean.ARPObject;
import com.nms.drive.service.bean.NEObject;
import com.nms.drive.service.bean.UpgradeManageObject;
import com.nms.drive.service.bean.status.BfdStatusObject;
import com.nms.drive.service.bean.status.E1LegStatusObject;
import com.nms.drive.service.bean.status.FrequencySourceObject;
import com.nms.drive.service.bean.status.LagPortObject;
import com.nms.drive.service.bean.status.LagStatusObject;
import com.nms.drive.service.bean.status.OamPingFrameObject;
import com.nms.drive.service.bean.status.OamStatusObject;
import com.nms.drive.service.bean.status.OamTraceHopsObject;
import com.nms.drive.service.bean.status.PortStatusObject;
import com.nms.drive.service.bean.status.ProtectStatusObject;
import com.nms.drive.service.bean.status.PtpBasicStatusObject;
import com.nms.drive.service.bean.status.PtpPortStatusObject;
import com.nms.drive.service.bean.status.PwProtectStatusObject;
import com.nms.drive.service.bean.status.PwStatusObject;
import com.nms.drive.service.bean.status.SiteStatusObject;
import com.nms.drive.service.bean.status.TunnelStatusObject;
import com.nms.drive.service.bean.status.VpwsStatusObject;
import com.nms.drive.service.bean.status.WrappingStatusObject;
import com.nms.drive.service.impl.CoderUtils;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.system.FieldService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.bean.ActionObject;
import com.nms.service.bean.OperationObject;
import com.nms.service.impl.base.WHOperationBase;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class SiteWHServiceImpl extends WHOperationBase implements OperationServiceI {
	@SuppressWarnings("rawtypes")
	@Override
	public String excutionDelete(List objectList) throws Exception {
		return null;
	}

	@Override
	public String excutionInsert(Object object) throws Exception {
		SiteInst siteInst = null;
		SiteService_MB siteService = null;
		FieldService_MB fieldService = null;
		List<Field> fields = null;
		try {
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = (SiteInst) object;
			siteService.saveOrUpdate(siteInst);	
			if(siteInst.getIsGateway() == 1){
				WhImplUtil whImplUtil = new WhImplUtil();
				int neaddress = whImplUtil.siteIdToNeObject(siteInst.getSite_Inst_Id()).getNeAddress();
				ConstantUtil.mSiteMap.put(neaddress+"/"+siteInst.getCellDescribe(), siteInst.getSite_Inst_Id());
				HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
				map.put(neaddress,siteInst.getSite_Inst_Id());
				ConstantUtil.m_aSiteMap.put(siteInst.getSite_Inst_Id(),map);
			}else{
				fields = fieldService.selectByFieldId(siteInst.getFieldID());
				if(fields.size()>0){
					if("field".equals(fields.get(0).getObjectType())){//对应域
						Integer neaddress = fields.get(0).getGroupId()*256+Integer.parseInt(siteInst.getSite_Hum_Id());
						//获取M网元ip
						SiteInst msiteInst = new SiteInst();
						msiteInst = siteService.select(fields.get(0).getmSiteId());
						ConstantUtil.m_aSiteMap.get(msiteInst.getSite_Inst_Id()).put(neaddress,siteInst.getSite_Inst_Id());
					}else if("subnet".equals(fields.get(0).getObjectType())){//对应子网
						
						//获取域id
						Field field = new Field();
						field.setId(fields.get(0).getParentId());
						fields = fieldService.select(field);
						if(fields.size()>0){
							if("field".equals(fields.get(0).getObjectType())){
								//获取M网元ip
								SiteInst msiteInst = new SiteInst();
								msiteInst = siteService.select(fields.get(0).getmSiteId());
								Integer neaddress = fields.get(0).getGroupId()*256+Integer.parseInt(siteInst.getSite_Hum_Id());
								ConstantUtil.m_aSiteMap.get(msiteInst.getSite_Inst_Id()).put(neaddress,siteInst.getSite_Inst_Id());
							}
						}
					}
				}
			}
			return ResultString.CONFIG_SUCCESS;
			
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(fieldService);
		}
	}

	/**
	 * 网元校时
	 * @param object
	 * @return
	 */
	public String currectTime(Object object){
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		SiteInst siteInst = null;
		try {
			siteInst = (SiteInst) object;				
				operationObjectAfter = this.getOperationObject(siteInst,"currecttime");
				//下发配置
				if (operationObjectAfter.getActionObjectList().size() > 0) {
					super.sendAction(operationObjectAfter);
					operationObjectResult = super.verification(operationObjectAfter);
					if(operationObjectResult.isSuccess()){
						//下发成功,返回状态
						return operationObjectResult.getActionObjectList().get(0).getStatus();
					}else{
						//下发失败,回滚
						return super.getErrorMessage(operationObjectResult);
					}
				}			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return ResultString.CONFIG_SUCCESS+","+siteInst.getCellId()+ResultString.NOT_ONLINE_SUCCESS;
	}
	
	/**
	 * 服务器轮询给设备校时，不需要调用SiteDispatch,不需要返回值
	 */
	public void circulateCurrectTime(SiteInst siteInst){
		OperationObject operationObjectAfter = null;
		try {
			operationObjectAfter = this.getOperationObject(siteInst,"currecttime");
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}
	/**
	 * 清除设备配置
	 * @param object
	 * @return
	 */
	public String clearSite(Object object){
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		SiteInst siteInst = null;
		SiteService_MB siteService = null;
		try {
		   siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
		   siteInst = (SiteInst) object;	
		   SiteUtil siteUtil=new SiteUtil();
		   if(siteUtil.SiteTypeUtil(siteInst.getSite_Inst_Id()) == 0){
			   operationObjectAfter = this.getSiteOperationObject(siteInst.getSite_Inst_Id(),"clearSite");
			   //下发配置
			   if (operationObjectAfter.getActionObjectList().size() > 0) {
				   super.sendAction(operationObjectAfter);
				   operationObjectResult = super.verification(operationObjectAfter);
				   if(operationObjectResult.isSuccess()){
					   //下发成功,返回状态
//					   siteService.initializtionSite(siteInst);
					   return operationObjectResult.getActionObjectList().get(0).getStatus();
				   }
			   }			
		   }else{
			   return ResultString.CONFIG_SUCCESS+","+siteInst.getCellId()+ResultString.NOT_ONLINE_SUCCESS;
		   }
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return null;
	}
//	/**
//	 * 清除网管数据
//	 * @param object
//	 * @return
//	 */
//	public String clearData(Object object){
//		DBManager.dbClear();
//		return "配置成功";
//	}
	

	@Override
	public String excutionUpdate(Object object) throws Exception {
		SiteInst siteInst = null;
		List<SiteInst> siteInstList = null;
		SiteService_MB siteService = null;
		SiteInst beforeSiteInst = null;
		Integer beforeNe = null;
		Integer nowNe = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = (SiteInst) object;
			beforeSiteInst = siteService.selectById(siteInst.getSite_Inst_Id());
			beforeNe = getNeAddress(beforeSiteInst);
			nowNe = getNeAddress(siteInst);
			siteService.saveOrUpdate(siteInst);
			if(beforeSiteInst.getIsGateway() == 1 && siteInst.getIsGateway() == 1){
				if(beforeNe != nowNe){
					ConstantUtil.mSiteMap.remove(beforeNe+"/"+beforeSiteInst.getCellDescribe());
					ConstantUtil.mSiteMap.put(nowNe+"/"+siteInst.getCellDescribe(),siteInst.getSite_Inst_Id());
					ConstantUtil.m_aSiteMap.get(siteInst.getSite_Inst_Id()).remove(beforeNe);
					ConstantUtil.m_aSiteMap.get(siteInst.getSite_Inst_Id()).put(nowNe,siteInst.getSite_Inst_Id());
				}
			}else if(beforeSiteInst.getIsGateway() == 0 && siteInst.getIsGateway() == 1){
				SiteInst mSiteInst = getMsiteInst(beforeSiteInst.getSite_Inst_Id());
				if(mSiteInst != null){
					HashMap<Integer, Integer> map = ConstantUtil.m_aSiteMap.get(mSiteInst.getSite_Inst_Id());
					if(map != null){
						map.remove(beforeNe);
					}
				}
				
				ConstantUtil.mSiteMap.put(nowNe+"/"+siteInst.getCellDescribe(),siteInst.getSite_Inst_Id());
				setM_Amap(siteInst);
			}else if(beforeSiteInst.getIsGateway() == 1 && siteInst.getIsGateway() == 0){
				ConstantUtil.mSiteMap.remove(beforeNe+"/"+beforeSiteInst.getCellDescribe());
				ConstantUtil.m_aSiteMap.remove(beforeSiteInst.getSite_Inst_Id());
				
				SiteInst mSiteInst = getMsiteInst(siteInst.getSite_Inst_Id());
				if(mSiteInst != null){
					HashMap<Integer, Integer> map = ConstantUtil.m_aSiteMap.get(mSiteInst.getSite_Inst_Id());
					if(map != null){
						map.put(nowNe,siteInst.getSite_Inst_Id());
					}
				}
				
			}else if(beforeSiteInst.getIsGateway() == 0 && siteInst.getIsGateway() == 0){
				if(beforeNe != nowNe){
					SiteInst mSiteInst = getMsiteInst(beforeSiteInst.getSite_Inst_Id());
					if(mSiteInst != null){
						ConstantUtil.m_aSiteMap.get(mSiteInst.getSite_Inst_Id()).remove(beforeNe);
						ConstantUtil.m_aSiteMap.get(mSiteInst.getSite_Inst_Id()).put(nowNe,siteInst.getSite_Inst_Id());
					}
					
				}
			}
			
			siteInstList = new ArrayList<SiteInst>();
			siteInstList.add(siteInst);
			return ResultString.CONFIG_SUCCESS;
			
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	private void setM_Amap(SiteInst siteInst){
		SiteService_MB siteServiceMB = null;
		FieldService_MB fieldService = null;
		try {
			siteServiceMB = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			List<SiteInst> asiteInsts = siteServiceMB.querySitesByIp(siteInst.getCellDescribe());
			HashMap<Integer, Integer> ne_id = new HashMap<Integer, Integer>();
			asiteInsts.add(siteInst);
			for(SiteInst asiteInst:asiteInsts){
				List<Field> fields = fieldService.selectByFieldId(asiteInst.getFieldID());
				if(fields.size()>0){
					if("field".equals(fields.get(0).getObjectType())){//对应域
						//获得真实ne地址
						ne_id.put(fields.get(0).getGroupId()*256+Integer.parseInt(asiteInst.getSite_Hum_Id()), asiteInst.getSite_Inst_Id());
					}else if("subnet".equals(fields.get(0).getObjectType())){//对应子网
						
						//获取域id
						Field field = new Field();
						field.setId(fields.get(0).getParentId());
						fields = fieldService.select(field);
						if(fields.size()>0){
							if("field".equals(fields.get(0).getObjectType())){
								ne_id.put(fields.get(0).getGroupId()*256+Integer.parseInt(asiteInst.getSite_Hum_Id()), asiteInst.getSite_Inst_Id());
							}
						}
					}
				}
			}
			ConstantUtil.m_aSiteMap.put(siteInst.getSite_Inst_Id(), ne_id);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(fieldService);
			UiUtil.closeService_MB(siteServiceMB);
		}
		
	}
	private Integer getNeAddress(SiteInst siteInst){
		Integer neaddress = 0;
		SiteService_MB siteServiceMB = null;
		FieldService_MB fieldServiceMB = null;
		List<Field> fields = null;
		try {
			siteServiceMB = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			fieldServiceMB = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			fields = fieldServiceMB.selectByFieldId(siteInst.getFieldID());
			if(fields.size()>0){
				if("field".equals(fields.get(0).getObjectType())){//对应域
					//获得真实ne地址
					neaddress = fields.get(0).getGroupId()*256+Integer.parseInt(siteInst.getSite_Hum_Id());
				}else if("subnet".equals(fields.get(0).getObjectType())){//对应子网
					
					//获取域id
					Field field = new Field();
					field.setId(fields.get(0).getParentId());
					fields = fieldServiceMB.select(field);
					if(fields.size()>0){
						if("field".equals(fields.get(0).getObjectType())){
							neaddress = fields.get(0).getGroupId()*256+Integer.parseInt(siteInst.getSite_Hum_Id());
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(fieldServiceMB);
			UiUtil.closeService_MB(siteServiceMB);
		}
		return neaddress;
	}


	private SiteInst getMsiteInst(int siteId) throws Exception{
		
		SiteInst siteInst=null;
		FieldService_MB fieldService = null;
		List<Field> fields = null;
		SiteInst msiteInst = null;
		Field field = null;
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			siteInst = siteService.select(siteId);
			fields = fieldService.selectByFieldId(siteInst.getFieldID());
			if(fields.size()>0){
				if("field".equals(fields.get(0).getObjectType())){//对应域
					//获取M网元ip
					msiteInst = new SiteInst();
					msiteInst = siteService.select(fields.get(0).getmSiteId());
					return msiteInst;
				}else if("subnet".equals(fields.get(0).getObjectType())){//对应子网
					
					//获取域id
					field = new Field();
					field.setId(fields.get(0).getParentId());
					fields = fieldService.select(field);
					if(fields.size()>0){
						if("field".equals(fields.get(0).getObjectType())){
							//获取M网元ip
							msiteInst = new SiteInst();
							msiteInst = siteService.select(fields.get(0).getmSiteId());
							return msiteInst;
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, WhImplUtil.class);
		}finally{
			UiUtil.closeService_MB(fieldService);
			UiUtil.closeService_MB(siteService);
			siteInst=null;
			fields = null;
			field = null;
		}
		return msiteInst;
	}
	
	@Override
	public Object synchro(int siteId) {
		return null;
	}

	/**
	 * 获取网元属性
	 * @param siteId
	 * @return
	 */
	public SiteInst select(int siteId) {
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		SiteInst siteInst = null;
		try {				
			SiteUtil siteUtil=new SiteUtil();
			if(siteUtil.SiteTypeUtil(siteId) == 0){
				operationObjectAfter = this.getSiteOperationObject(siteId,"siteAttribute");
				//下发配置
				
				if (operationObjectAfter.getActionObjectList().size() > 0) {
					super.sendAction(operationObjectAfter);
					operationObjectResult = super.verification(operationObjectAfter);
					if(operationObjectResult.isSuccess()){
						//下发成功,返回状态
						siteInst = this.neObjectToSiteInst(siteId,operationObjectResult.getActionObjectList().get(0).getNeObject());
					}
				}	
			}
					
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObjectAfter = null;
			operationObjectResult = null;
		}
		return siteInst;
	}
	
	/**
	 * 网元上载
	 * @param siteInst
	 * @return
	 */
	public byte[] uploadConfig(SiteInst siteInst){
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		byte[] reault = null;
		try {				
			operationObjectAfter = this.getSiteOperationObject(siteInst.getSite_Inst_Id(),"uploadConfig");
			//下发配置
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if(operationObjectResult.isSuccess()){
					//下发成功,返回状态
					reault = operationObjectResult.getActionObjectList().get(0).getBs();
				}
			}			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObjectAfter = null;
			operationObjectResult = null;
		}
		return reault;
	}
	
	/**
	 * 网元下载
	 * @param siteInst
	 * @return
	 */
	public String downloadConfig(SiteInst siteInst){
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		String reault = null;
		try {				
			operationObjectAfter = this.getOperationObject(siteInst,"downloadConfig");
			//下发配置
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if(operationObjectResult.isSuccess()){
					//下发成功,返回状态
					return operationObjectResult.getActionObjectList().get(0).getStatus();
				} else {
					return super.getErrorMessage(operationObjectResult);
				}
			}else{
				return ResultString.CONFIG_SUCCESS+","+siteInst.getCellId()+ResultString.NOT_ONLINE_SUCCESS;
			}		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObjectAfter = null;
			operationObjectResult = null;
		}
		return reault;
	}
	
	/**
	 * 查询网元某状态块
	 * @param siteInst
	 * @return
	 */
	public SiteStatusInfo siteStatus(SiteInst siteInst){
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		SiteStatusInfo siteStatusInfo = null;
		try {				
			
			operationObjectAfter = this.getOperationObject(siteInst,"allStatus");

			//下发配置
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
					if(operationObjectResult.isSuccess()){
						//下发成功,返回状态
						siteStatusInfo = getSiteStatusInfo(siteInst.getSite_Inst_Id(),operationObjectResult.getActionObjectList().get(0).getSiteStatusObject());
						return siteStatusInfo;
					} else {
//						return super.getErrorMessage(operationObjectResult);
					}
			}		
		
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObjectAfter = null;
			operationObjectResult = null;
		}
		return siteStatusInfo;
	}
	
	/**
	 * M网元向网管请求配置
	 */
	public void requestConfig(int fieldId,byte[] commandHand){
		OperationObject operationObject = new OperationObject();
		int status = commandHand[11];
		String str = Integer.toBinaryString(status);
		for (int i = str.length(); i < 8; i++) {
			str = 0 + str;
		}
		int currecttime = Integer.parseInt(str.substring(4, 5));
		if(currecttime == 1){//M网元向网管请求校时配置
			try {
				operationObject = getOperationObject(fieldId, "currecttime");
				super.sendAction(operationObject);
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
		}
	}
	/**
	 * 获得校时的operationobject对象
	 * @param siteInstId
	 * @return
	 */
	private OperationObject getSiteOperationObject(int siteId,String type) {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(siteId);
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType(type);
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}

	private OperationObject getSiteSNOperationObject(SiteInst siteInst,String type) {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(siteInst.getSite_Inst_Id());
			neObject.setManageIP(siteInst.getCellDescribe());
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType(type);
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}
	
	/**
	 * 获取operationobject对象
	 * @param siteId
	 * @return operationObject
	 * @throws Exception
	 */
	private OperationObject getOperationObject(SiteInst siteInst,String type) throws Exception{
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		SiteUtil siteUtil = null;
		WhImplUtil whImplUtil = null;
		try {
			operationObject = new OperationObject();
			siteUtil = new SiteUtil();
			if(siteUtil.SiteTypeUtil(siteInst.getSite_Inst_Id()) == 0){
				whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(siteInst.getSite_Inst_Id());
				neObject.setFile(siteInst.getFile());
				neObject.setStatusMark(siteInst.getStatusMark());
				neObject.setNeStatus(siteInst.getParameter());
				neObject.setIsCreateDiscardFlow(siteInst.getIsCreateDiscardFlow());
				neObject.setL(siteInst.getL());
				actionObject = new ActionObject();
				actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.setType(type);
				actionObject.setBs(siteInst.getBs());
				operationObject.getActionObjectList().add(actionObject);
			}
		} finally {
			actionObject = null;
			neObject = null;
			siteUtil = null;
			whImplUtil = null;
		}
		return operationObject;
	}
	
	/**
	 * 根据fieldID对该域所有网元校时
	 * @param siteId
	 * @return operationObject
	 * @throws Exception
	 */
	private OperationObject getOperationObject(int fieldId,String type) throws Exception{
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		Field field = null;
		List<Field> fields = null;
		FieldService_MB fieldService = null;
		SiteService_MB siteService = null;
		SiteInst msiteInst = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			operationObject = new OperationObject();
			
			neObject = new NEObject();
			neObject.setNeAddress(fieldId*256+255);
			neObject.setL(System.currentTimeMillis());
			field = new Field();
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			field.setGroupId(fieldId);
			fields = fieldService.select(field);
			
			for(Field field2 : fields){
				if("field".equals(fields.get(0).getObjectType())){
					//获取M网元ip
					msiteInst = new SiteInst();
					msiteInst = siteService.select(field2.getmSiteId());
					neObject.setManageIP(msiteInst.getCellDescribe());
				}
			}
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType(type);
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(fieldService);
			UiUtil.closeService_MB(siteService);
		}
		return operationObject;
	}
	
	/**
	 * 封装界面状态对象
	 * @param siteStatusObject
	 * @return
	 * @throws Exception 
	 */
	private SiteStatusInfo getSiteStatusInfo(int siteId, SiteStatusObject siteStatusObject) throws Exception{
		SiteStatusInfo siteStatusInfo = new SiteStatusInfo();
		List<TunnelStatus> tunnelStatusList = null;
		List<PortStatus> portStatusList = null;
		List<PwStatus> pwStatusList = null;
		List<ProtectStatus> protectStatusList = null;
		List<VpwsStatus> vpwsStatusList = null;
		FrequencyStatusInfo frequencyStatusInfo = null;
		List<FrequencySource> frequencySourcesList = null;
		List<WrappingStatus> wrappingStatusList = null;
		List<E1LegStatus> e1LegStatusList = null;
		List<PwProtectStatus> pwProtectStatusList = null;
		List<lagStatusInfo> lagStatusList=null;
		List<PtpPortStatus> ptpPortStatusList=null;
		List<PtpBasicStatus> ptpBasicStatusList=null;
		List<BfdStatus> bfdStatusList=null;
		List<ARPInfo> arpStatusList = null;
		if(siteStatusObject.getTunnelStatusListObject() != null){
			tunnelStatusList = new ArrayList<TunnelStatus>();
			for(TunnelStatusObject tunnelStatusObject : siteStatusObject.getTunnelStatusListObject()){//Tunnel
				TunnelStatus tunnelStatus = new TunnelStatus();
				CoderUtils.copy(tunnelStatusObject, tunnelStatus);
				tunnelStatusList.add(tunnelStatus);
			}
			siteStatusInfo.setTunnelStatusList(tunnelStatusList);
		}
		
		if(siteStatusObject.getPortStatusListObject() != null){
			portStatusList = new ArrayList<PortStatus>();
			for(PortStatusObject portStatusObject : siteStatusObject.getPortStatusListObject()){//端口
				PortStatus portStatus = new PortStatus();
				CoderUtils.copy(portStatusObject, portStatus);
				portStatusList.add(portStatus);
			}
			siteStatusInfo.setPortStatusList(portStatusList);
		}
		
		if(siteStatusObject.getPwStatusObject() != null){
			pwStatusList = new ArrayList<PwStatus>();
			for(PwStatusObject pwStatusObject : siteStatusObject.getPwStatusObject()){//pw
				PwStatus pwStatus = new PwStatus();
				CoderUtils.copy(pwStatusObject, pwStatus);
				pwStatusList.add(pwStatus);
			}
			siteStatusInfo.setPwStatus(pwStatusList);
		}
		
		if(siteStatusObject.getProtectStatusListObject() != null){
			protectStatusList = new ArrayList<ProtectStatus>();
			for(ProtectStatusObject protectStatusObject : siteStatusObject.getProtectStatusListObject()){//lsp保护
				ProtectStatus protectStatus = new ProtectStatus();
				CoderUtils.copy(protectStatusObject, protectStatus);
				protectStatusList.add(protectStatus);
			}
			siteStatusInfo.setProtectStatusList(protectStatusList);
		}
		
		if(siteStatusObject.getVpwsStatusListObject() != null){
			vpwsStatusList = new ArrayList<VpwsStatus>();
			for(VpwsStatusObject vpwsStatusObject : siteStatusObject.getVpwsStatusListObject()){//vpws业务
				VpwsStatus vpwsStatus = new VpwsStatus();
				CoderUtils.copy(vpwsStatusObject, vpwsStatus);
				if(isElineType(siteId, vpwsStatus))
				{
					vpwsStatusList.add(vpwsStatus);
				}
			}
			siteStatusInfo.setVpwsStatusList(vpwsStatusList);
		}
		
		if(siteStatusObject.getFrequencyStatusObject() != null){
			//时钟基本信息
			frequencyStatusInfo = new FrequencyStatusInfo();
			CoderUtils.copy(siteStatusObject.getFrequencyStatusObject(),frequencyStatusInfo);
			siteStatusInfo.setFrequencyStatusInfo(frequencyStatusInfo);
		}
		
		if(siteStatusObject.getFrequencySourcesObject() != null){
			frequencySourcesList = new ArrayList<FrequencySource>();
			for(FrequencySourceObject frequencySourceObject : siteStatusObject.getFrequencySourcesObject()){//时钟变长信息
				FrequencySource frequencySource = new FrequencySource();
				CoderUtils.copy(frequencySourceObject, frequencySource);
				frequencySourcesList.add(frequencySource);
			}
			siteStatusInfo.setFrequencySources(frequencySourcesList);
		}
		
		if(siteStatusObject.getWrappingStatusObjectList() != null){//环保护信息
			wrappingStatusList = new ArrayList<WrappingStatus>();
			for(WrappingStatusObject wrappingStatusObject: siteStatusObject.getWrappingStatusObjectList()){
				WrappingStatus wrappingStatus = new WrappingStatus();
				CoderUtils.copy(wrappingStatusObject,wrappingStatus);
				wrappingStatusList.add(wrappingStatus);
			}
			siteStatusInfo.setWrappingStatusList(wrappingStatusList);
		}
		
		if(siteStatusObject.getE1LegStatusObjectList() != null){//e1仿真状态查询
			e1LegStatusList = new ArrayList<E1LegStatus>();
			for (E1LegStatusObject e1LegObj : siteStatusObject.getE1LegStatusObjectList()) {
				E1LegStatus e1Leg = new E1LegStatus();
				e1Leg.setE1LegID(e1LegObj.getE1LegID());
				e1Leg.setE1Status(e1LegObj.getE1Status());
				e1Leg.setE1Model(e1LegObj.getE1Model());
				e1Leg.setTdmClock(e1LegObj.getTdmClock());
				e1Leg.setE1LegStatus(e1LegObj.getE1LegStatus());
				e1Leg.setE1LegType(e1LegObj.getE1LegType());
				e1Leg.setE1legSpeed(e1LegObj.getE1legSpeed());
				e1Leg.setE1Attr(e1LegObj.getE1Attr());
				e1LegStatusList.add(e1Leg);
			}
			siteStatusInfo.setE1LegStatusList(e1LegStatusList);
		}
		if(siteStatusObject.getPwProtectStatusObjectList() != null){
			pwProtectStatusList = new ArrayList<PwProtectStatus>();
			for(PwProtectStatusObject pwProtectStatusObject : siteStatusObject.getPwProtectStatusObjectList()){//pwprotect
				PwProtectStatus pwProtectStatus = new PwProtectStatus();
				CoderUtils.copy(pwProtectStatusObject, pwProtectStatus);
				pwProtectStatusList.add(pwProtectStatus);
			}
			siteStatusInfo.setPwProtectStatusList(pwProtectStatusList);
		}
		
		if(siteStatusObject.getLagStatusObjectList() != null){
			lagStatusList = new ArrayList<lagStatusInfo>();
			for(LagStatusObject lagStatusObject : siteStatusObject.getLagStatusObjectList()){//lag
				lagStatusInfo lagStatus = new lagStatusInfo();
				ChangeObj2Info(lagStatusObject, lagStatus);
				lagStatusList.add(lagStatus);
			}
			siteStatusInfo.setLagStatusList(lagStatusList);
		}
		
		if(siteStatusObject.getPtpPortStatusObjectList() != null){
			ptpPortStatusList = new ArrayList<PtpPortStatus>();
			for(PtpPortStatusObject ptpPortStatusObject : siteStatusObject.getPtpPortStatusObjectList()){//PTP端口信息
				PtpPortStatus ptpPortStatus = new PtpPortStatus();
				CoderUtils.copy(ptpPortStatusObject, ptpPortStatus);
				ptpPortStatusList.add(ptpPortStatus);
			}
			siteStatusInfo.setPtpPortStatusList(ptpPortStatusList);
		}
		
		if(siteStatusObject.getPtpBasicStatusObjectList() != null){
			ptpBasicStatusList = new ArrayList<PtpBasicStatus>();
			for(PtpBasicStatusObject ptpBasicStatusObject : siteStatusObject.getPtpBasicStatusObjectList()){//PTP端口信息
				PtpBasicStatus ptpBasicStatus = new PtpBasicStatus();
				CoderUtils.copy(ptpBasicStatusObject, ptpBasicStatus);
				ptpBasicStatusList.add(ptpBasicStatus);
			}
			siteStatusInfo.setPtpBaiscStatusList(ptpBasicStatusList);
		}
		if(siteStatusObject.getBfdStatusListObject() != null){
			bfdStatusList = new ArrayList<BfdStatus>();
			for(BfdStatusObject bfdStatusObject : siteStatusObject.getBfdStatusListObject()){//bfd
				BfdStatus bfdStatus = new BfdStatus();
				CoderUtils.copy(bfdStatusObject, bfdStatus);
				bfdStatusList.add(bfdStatus);
			}
			siteStatusInfo.setBfdStatusList(bfdStatusList);
		}
		if(siteStatusObject.getArpStatusObjectList() != null){
			arpStatusList = new ArrayList<ARPInfo>();
			for (ARPObject arpObj : siteStatusObject.getArpStatusObjectList()) {//arp
				ARPInfo info = new ARPInfo();
				CoderUtils.copy(arpObj, info);
				arpStatusList.add(info);
			}
			siteStatusInfo.setArpStatusList(arpStatusList);
		}
		return siteStatusInfo;
	}
	
	private void ChangeObj2Info(LagStatusObject lagStatusObject,lagStatusInfo lagStatus)
	{
		lagStatus.setLacpSwitch(lagStatusObject.getLacpSwitch());
		lagStatus.setSyspri(lagStatusObject.getSyspri());
		lagStatus.setLagId(lagStatusObject.getLagId());
		lagStatus.setWorkMode(lagStatusObject.getWorkMode());
		lagStatus.setLagMode(lagStatusObject.getLagMode());
		lagStatus.setLagNum(lagStatusObject.getLagNum());
		lagStatus.setReturnType(lagStatusObject.getReturnType());
		lagStatus.setWaitTime(lagStatusObject.getWaitTime());
		List<LagPortStateInfo> portList = new ArrayList<LagPortStateInfo>();
		for(LagPortObject port:lagStatusObject.getLagPorts())
		{
			LagPortStateInfo  portInfo = new LagPortStateInfo(); 
			portInfo.setLocalPri(port.getLocalPri());
			portInfo.setPortLacp(port.getLocalPri());
			portInfo.setPortNum(port.getPortNum());
			portInfo.setPortStatus(port.getPortStatus());
			portList.add(portInfo);
		}
		lagStatus.setLagPorts(portList);
	}
	
	//
	private boolean isElineType(int siteId, VpwsStatus vpws)
	{
		boolean flag = false;
		ElineInfoService_MB elineService = null;
		try {
			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			flag = elineService.isElineVPWS(siteId, vpws.getVpwsID());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(elineService);
		}
		
		return flag;
	}
	
	/**
	 * 转换siteinst
	 * @param neObject
	 * @return
	 */
	private SiteInst neObjectToSiteInst(int siteId,NEObject neObject){
		SiteInst siteInst = new SiteInst();
		SiteService_MB siteService= null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInst = siteService.select(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		siteInst.setSite_Hum_Id(neObject.getSite_Hum_Id());
		siteInst.setTotalTime(neObject.getTotalTime());
		siteInst.setCellTime(neObject.getCellTime());
		siteInst.setSoftEdition(neObject.getSoftEdition());
		siteInst.setCardNumber(neObject.getCardNumber());
		siteInst.setPlateNumber(neObject.getPlateNumber());
		siteInst.setCreatePlateNumber(neObject.getCreatePlateNumber());
		siteInst.setProgrammeTime(neObject.getProgrammeTime());
		siteInst.setBootTime(neObject.getBootTime());
		siteInst.setFpgaTime(neObject.getFpgaTime());
        siteInst.setNeMAC(neObject.getNeMAC());
		return siteInst;
	}
	
	/**
	 * 查询oam某状态块
	 * @param siteInst
	 * @return
	 */
	public OamStatusInfo oamStatus(SiteInst siteInst){
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		OamStatusObject oamStatusObject = null;
		OamStatusInfo oamStatusInfo = null;
		try {				
			operationObjectAfter = this.getOperationObject(siteInst,"oamStatus");
			//下发配置
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
					if(operationObjectResult.isSuccess()){
						//下发成功,返回状态
						oamStatusObject = operationObjectResult.getActionObjectList().get(0).getOamStatusObject();
						oamStatusInfo = new OamStatusInfo();
						transmitOamStatusInfo(oamStatusObject, oamStatusInfo);
						return oamStatusInfo;
					} 
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObjectAfter = null;
			operationObjectResult = null;
		}
		return oamStatusInfo;
	}
	
	public String createOrDeleteDiscardFlow(SiteInst siteInst){
		
		OperationObject operationObjectAfter = null ;
		OperationObject operationObjectResult = null;
		
		try {
			operationObjectAfter = this.getOperationObject(siteInst, siteInst.getControlType());
			if(operationObjectAfter.getActionObjectList().size() > 0){
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if(operationObjectResult.isSuccess()){
					return  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}else{
					return  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FALSE);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 operationObjectAfter = null ;
			 operationObjectResult = null;
		}
		return  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
	}
	
	/**
	 * 驱动数据向数据库转换
	 * @param oamStatusObject
	 * @param oamStatusInfo
	 */
	private void transmitOamStatusInfo(OamStatusObject oamStatusObject,OamStatusInfo oamStatusInfo){
		List<OamPingFrame> oamPingFrames;
		List<OamTraceHops> oamTraceHopList;
		InsertLinkOamInfo insertLinkOamInfo;
		OamLinkStatusInfo oamLinkStatusInfo;
		OamMepInst oamMepInst;
		if(oamStatusObject.getInsertLinkOamObject() != null){
			insertLinkOamInfo = new InsertLinkOamInfo();
			CoderUtils.copy(oamStatusObject.getInsertLinkOamObject(), insertLinkOamInfo);
			oamStatusInfo.setInsertLinkOamInfo(insertLinkOamInfo);
		}else if(oamStatusObject.getOamPingFrameObjectList() != null){
			oamPingFrames = new ArrayList<OamPingFrame>();
			for(OamPingFrameObject pingFrameObejct : oamStatusObject.getOamPingFrameObjectList()){
				OamPingFrame oamPingFrame = new OamPingFrame();
				CoderUtils.copy(pingFrameObejct, oamPingFrame);
				oamPingFrames.add(oamPingFrame);
			}
			oamStatusInfo.setOamPingFrames(oamPingFrames);
		}else if(oamStatusObject.getOamTraceHopsObjectList() != null){
			oamTraceHopList = new ArrayList<OamTraceHops>();
			for(OamTraceHopsObject oamTraceHopsObject : oamStatusObject.getOamTraceHopsObjectList()){
				OamTraceHops oamTraceHops = new OamTraceHops();
				CoderUtils.copy(oamTraceHopsObject, oamTraceHops);
				oamTraceHopList.add(oamTraceHops);
			}
			oamStatusInfo.setOamTraceHops(oamTraceHopList);
		}else if(oamStatusObject.getOamLinkStatusInfoObject() != null){
			oamLinkStatusInfo = new OamLinkStatusInfo();
			CoderUtils.copy(oamStatusObject.getOamLinkStatusInfoObject(), oamLinkStatusInfo);
			oamStatusInfo.setOamLinkStatusInfo(oamLinkStatusInfo);
		}else if(oamStatusObject.getOamMepInfoObject() != null){
			oamMepInst = new OamMepInst();
			CoderUtils.copy(oamStatusObject.getOamMepInfoObject(), oamMepInst);
			oamStatusInfo.setOamMepInst(oamMepInst);
		}else if(oamStatusObject.getManStydyObject() != null){
			MacStudyBean macStudyObject = new MacStudyBean();
			macStudyObject.setVlanId(oamStatusObject.getManStydyObject().getVlanId());
			oamStatusInfo.setMacStudyBean(macStudyObject);
		}
	}
	
	/**
	 * 查询软件摘要，
	 * @param siteInst
	 * @return
	 */
	public List<UpgradeManage> querySummary(SiteInst siteInst){
		OperationObject operationObjectAfter = null ;
		OperationObject operationObjectResult = null;
		List<UpgradeManageObject> upgradeManageObjects = null;
		List<UpgradeManage> upgradeManages = null;
		try {
			operationObjectAfter = this.getSummaryOperationObject(siteInst, "querySoftwareSummary");
			if(operationObjectAfter.getActionObjectList().size() > 0){
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if(operationObjectResult.isSuccess()){
					upgradeManageObjects = new ArrayList<UpgradeManageObject>();
					upgradeManageObjects = operationObjectResult.getActionObjectList().get(0).getUpgradeManageObjects();
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 operationObjectAfter = null ;
			 operationObjectResult = null;
		}
		upgradeManages = getUpgradeManages(upgradeManageObjects,siteInst.getCellId());
		return  upgradeManages;
	}
	
	/**
	 * 下发软件摘要
	 * @param siteInst
	 * @return
	 */
	public String sendSummary(SiteInst siteInst){
		OperationObject operationObjectAfter = null ;
		OperationObject operationObjectResult = null;
		String result ="";
		try {
			operationObjectAfter = this.getSummaryOperationObject(siteInst, "querySoftwareSummary");
			if(operationObjectAfter.getActionObjectList().size() > 0){
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if(operationObjectResult.getActionObjectList().get(0).getStatus() == null ||
						operationObjectResult.getActionObjectList().get(0).getStatus().equals("")){
					return  ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FALSE);
				}
				result =  operationObjectResult.getActionObjectList().get(0).getStatus();
				return  result;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 operationObjectAfter = null ;
			 operationObjectResult = null;
		}
		return  result;
	
	}
	private OperationObject getSummaryOperationObject(SiteInst siteInst, String string) throws Exception {

		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			if(true){//软件升级过程中，需一直查询摘要判断是否能升级下一个文件
				WhImplUtil whImplUtil = new WhImplUtil();
				neObject = whImplUtil.siteIdToNeObject(siteInst.getSite_Inst_Id());
				neObject.setFile(siteInst.getFile());
				neObject.setStatusMark(siteInst.getStatusMark());
				neObject.setNeStatus(siteInst.getParameter());
				neObject.setIsCreateDiscardFlow(siteInst.getIsCreateDiscardFlow());
				neObject.setControlPanelAddress(Integer.parseInt(siteInst.getCardNumber()));
				if(neObject.getControlPanelAddress() == 2 || neObject.getControlPanelAddress() == 3){
					neObject.setControlPanelType(118489105);
				}
				neObject.setL(siteInst.getL());
				actionObject = new ActionObject();
				actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
				actionObject.setNeObject(neObject);
				actionObject.setType(string);
				actionObject.setBs(siteInst.getBs());
				operationObject.getActionObjectList().add(actionObject);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	
	}

	/**
	 * 转换软件版本信息
	 * @param upgradeManageObjects
	 * @return
	 */
	private List<UpgradeManage> getUpgradeManages(List<UpgradeManageObject> upgradeManageObjects,String siteName){
		List<UpgradeManage> upgradeManages = null;
		if(upgradeManageObjects != null){
			upgradeManages = new ArrayList<UpgradeManage>();
			for(UpgradeManageObject upgradeManageObject:upgradeManageObjects){
				UpgradeManage upgradeManage = new UpgradeManage();
				CoderUtils.copy(upgradeManageObject, upgradeManage);
				upgradeManage.setSiteName(siteName);
				upgradeManages.add(upgradeManage);
				
			}
		}
		return upgradeManages;
	}
	
	/**
	 *取消软件升级 
	 * @param siteInst
	 * @return
	 */
	public String cancelSoftware(SiteInst siteInst){
		
		OperationObject operationObjectAfter;
		OperationObject operationObjectResult; 
		String result ="";
		
		try {
			operationObjectAfter = this.getOperationObject(siteInst, "cancelSoftWare");
			
			if(operationObjectAfter.getActionObjectList().size() > 0){
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if(operationObjectResult.isSuccess()){
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}else{
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FALSE);
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		}
		return result;
	}
	
	/**
	 * 查询SN(包含本地或相邻)
	 * @param siteInst
	 * @return
	 */
	public List<SiteInst> querySn(SiteInst siteInst,int isLocation){
		List<SiteInst> insts = null;
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		SiteService_MB siteService = null;
		try {		
			insts = new ArrayList<SiteInst>();
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if(isLocation ==1){
				operationObjectAfter = this.getSiteSNOperationObject(siteInst,"queryLocaltionSn");
				//下发配置
				if (operationObjectAfter.getActionObjectList().size() > 0) {
					super.sendAction(operationObjectAfter);
					operationObjectResult = super.verification(operationObjectAfter,8000);
					if(operationObjectResult.isSuccess()){
						//下发成功,返回状态
						siteInst.setSn(operationObjectResult.getActionObjectList().get(0).getNeObject().getSn());
						insts.add(siteInst);
					}
				}
				siteService.updateBatch(insts);
			}else if(isLocation==2){
				operationObjectAfter = this.getSiteSNOperationObject(siteInst,"queryAdjoinSn");
				//下发配置
				if (operationObjectAfter.getActionObjectList().size() > 0) {
					super.sendAction(operationObjectAfter);
					operationObjectResult = super.verification(operationObjectAfter,8000);
					if(operationObjectResult.isSuccess()){
						//下发成功,返回状态
						getAdjoinSn(siteInst.getCellDescribe(),insts,operationObjectResult.getActionObjectList().get(0).getNeObjects());
					}
				}	
			}else if(isLocation==3){
				operationObjectAfter = this.getSiteSNOperationObject(siteInst,"queryRemoteSn");
				//下发配置
				if (operationObjectAfter.getActionObjectList().size() > 0) {
					super.sendAction(operationObjectAfter);
					operationObjectResult = super.verification(operationObjectAfter,8000);
					if(operationObjectResult.isSuccess()){
						//下发成功,返回状态
						siteInst.setSn(operationObjectResult.getActionObjectList().get(0).getNeObject().getSn());
						insts.add(siteInst);
					}
				}
				siteService.updateBatch(insts);
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationObjectAfter = null;
			operationObjectResult = null;
			UiUtil.closeService_MB(siteService);
		}
		return insts;
	}
	
	
	private void getAdjoinSn(String rootIP,List<SiteInst> insts,List<NEObject> neObjects){
		for(NEObject neObject : neObjects){
			SiteInst siteInst = new SiteInst();
			siteInst.setCellType(neObject.getCellType());
			siteInst.setSn(neObject.getSn());
			siteInst.setCellDescribe("0.0.0.0");
			siteInst.setRootIP(rootIP);
			siteInst.setCellEditon(33+"");
			siteInst.setSiteType(369);
			siteInst.setRack(1);
			siteInst.setShelf(1);
			siteInst.setLinkPort(neObject.getLinkPort());
			insts.add(siteInst);
		}
	}
	
	/***
	 * 设置网元IP或sn
	 * @param siteInst
	 * @return
	 */
	public String setSiteIP(SiteInst siteInst){
		String result = "";
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if(siteInst.getType() == 1){
				operationObjectAfter = this.getSiteIPOperationObject(siteInst,"setIP");
			}else if(siteInst.getType() == 2){
				operationObjectAfter = this.getSiteIPOperationObject(siteInst,"setremoteIP");
			}else if(siteInst.getType() == 3){
				operationObjectAfter = this.getSiteIPOperationObject(siteInst,"setSN");
			}else if(siteInst.getType() == 4){
				operationObjectAfter = this.getSiteIPOperationObject(siteInst,"setRemoteSN");
			}
			
			//下发配置
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if(operationObjectResult.isSuccess()){
					//下发成功,返回状态
					siteService.saveOrUpdate(siteInst);
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}else{
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FALSE);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		
		return result;
	}
	
	private OperationObject getSiteIPOperationObject(SiteInst siteInst,String type) {
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		try {
			operationObject = new OperationObject();
			WhImplUtil whImplUtil = new WhImplUtil();
			neObject = whImplUtil.siteIdToNeObject(siteInst.getSite_Inst_Id());
			if("setRemoteSN".equals(type)){
				neObject.setManageIP(siteInst.getCellDescribe());
			}else{
				neObject.setManageIP(siteInst.getRootIP());
			}
			neObject.setSn(siteInst.getSn());
			neObject.setNeIP(siteInst.getCellDescribe());
			actionObject = new ActionObject();
			actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
			actionObject.setNeObject(neObject);
			actionObject.setType(type);
			operationObject.getActionObjectList().add(actionObject);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			actionObject = null;
			neObject = null;
		}
		return operationObject;
	}
	
	/**
	 * 拓扑自动发现
	 * @return
	 */
	public List<Segment> topologicalLinkFound(List<SiteInst> siteInsts,int netWorkId,List<Segment> segment){

		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		SiteService_MB siteService = null;
		Map<String,List<SiteInst>> neighbour = new HashMap<String,List<SiteInst>>();
		List<SiteInst> insts = null;
		List<SiteInst> insts2 = null;
		FieldService_MB fieldService = null;
		List<Field> fields = null;
		List<Field> fieldList = null;
		Map<String,SiteInst> site_sn = new HashMap<String,SiteInst>();
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			if(siteInsts != null){//根据网元，实现段的搜索
				for(SiteInst siteInst :siteInsts){
					if(siteInst.getSn() != null && siteInst.getSn().length()>0 && siteInst.getManageSatus() == 0){
						insts = this.querySn(siteInst, 2);
						if(insts != null && insts.size()>0){
							neighbour.put(siteInst.getSn(), insts);
						}
						site_sn.put(siteInst.getSn(),siteInst);
					}
				}
			}else{//拓扑右键，发现所有物理连接
				fields = fieldService.queryByNetWorkid(netWorkId);//域id查询所有组
				for(Field field : fields){
					fieldList = fieldService.selectByFieldId(field.getId());//组id查询相应的组或者子网
					for(Field field2 : fieldList){
						SiteInst siteInst = new SiteInst();
						siteInst.setFieldID(field2.getId());
						insts2 = siteService.selectByFieldId(siteInst);//组或子网相应的网元
						for(SiteInst inst :insts2){
							if(inst.getSn() != null && inst.getSn().length()>0 && siteInst.getManageSatus() == 0){
								insts = this.querySn(inst, 2);
								if(insts != null && insts.size()>0){
									neighbour.put(inst.getSn(), insts);
								}
								site_sn.put(inst.getSn(),inst);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(fieldService);
		}
		
		List<String> list = filterData(neighbour);//过滤连接信息
		updateSegment(segment,list,site_sn);//连接信息入库
		return segment;
	}
	
	
	/**
	 * 过滤连接信息
	 */
	private List<String> filterData(Map<String,List<SiteInst>> neighbour){
		List<String> list = new ArrayList<String>();
		List<SiteInst> siteInsts = new ArrayList<SiteInst>();
		boolean b = true;
		for(String string : neighbour.keySet()){
			siteInsts = neighbour.get(string);
			for(SiteInst siteInst : siteInsts){
				if(neighbour.get(siteInst.getSn()) != null){
					String linkString = siteInst.getLinkPort();
					linkString = string+","+linkString.split("-")[0]+"-"+siteInst.getSn()+","+linkString.split("-")[1];
					for(String linkPort: list){
						if((linkPort.split("-")[0].equals(siteInst.getLinkPort().split("-")[0]) && linkPort.split("-")[1].equals(siteInst.getLinkPort().split("-")[1]))
								||(linkPort.split("-")[0].equals(siteInst.getLinkPort().split("-")[1]) && linkPort.split("-")[1].equals(siteInst.getLinkPort().split("-")[0]))){
							b = false;
							break;
						}
					}
					if(b){
						list.add(linkString);
					}
					b = true;
				}
			}
		}
		return list;
	}
	
	/**
	 * 物理连接入库
	 * @param list
	 */
	private void updateSegment(List<Segment> segmentss,List<String> list,Map<String,SiteInst> site_sn){
		SegmentService_MB segmentService = null;
		SiteService_MB siteService = null;
		String[] port_sn;
		List<Segment> segments = null;
		Segment segment = null;
		PortService_MB portService = null;
		List<PortInst> portInsts = null;
		PortWHServiceImpl portWHServiceImpl=null;	
		Map<Integer, List<QosQueue>> oldqosMap = null;
		List<QosQueue> infoList = null;
		try {
			oldqosMap = new HashMap<Integer, List<QosQueue>>();
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			for(String string : list){
				port_sn = string.split("-");
				//A端
				PortInst aportinst = new PortInst();				
				aportinst.setSiteId(site_sn.get(port_sn[0].split(",")[0]).getSite_Inst_Id());
				aportinst.setNumber(Integer.parseInt(port_sn[0].split(",")[1]));	
				portInsts = portService.select(aportinst);			
				if(portInsts.size()>0){
					aportinst = portInsts.get(0);							
					Segment seg = new Segment();
					seg.setASITEID(site_sn.get(port_sn[0].split(",")[0]).getSite_Inst_Id());
					seg.setAPORTID(aportinst.getPortId());
					segments = segmentService.selectBySiteIdAndPort(seg);
					if(segments.size()>0){
						continue;
					}
				}else{
					return;
				}
				//Z端
				PortInst zportinst = new PortInst();
				zportinst.setSiteId(site_sn.get(port_sn[1].split(",")[0]).getSite_Inst_Id());
				zportinst.setNumber(Integer.parseInt(port_sn[1].split(",")[1]));				
				portInsts = portService.select(zportinst);							
				if(portInsts.size()>0){
					zportinst = portInsts.get(0);					
					Segment seg = new Segment();
					seg.setASITEID(site_sn.get(port_sn[1].split(",")[0]).getSite_Inst_Id());
					seg.setAPORTID( zportinst.getPortId());
					segments = segmentService.selectBySiteIdAndPort(seg);
					if(segments.size()>0){
						continue;
					}
				}else{
					return;
				}
				segment = new Segment();
				segment.setNAME(site_sn.get(port_sn[0].split(",")[0]).getCellId()+"_"+aportinst.getPortName()+"---"+site_sn.get(port_sn[1].split(",")[0]).getCellId()+"_"+zportinst.getPortName());
				segment.setASITEID(aportinst.getSiteId());
				segment.setAPORTID(aportinst.getPortId());
				segment.setZSITEID(zportinst.getSiteId());
				segment.setZPORTID(zportinst.getPortId());	
				aportinst.setPortType("NNI");
				aportinst.setIsOccupy(1);
				if(Integer.parseInt(aportinst.getPortAttr().getMaxFrameSize())<1600){
					aportinst.getPortAttr().setMaxFrameSize("1600");
				}

				zportinst.setPortType("NNI");
				zportinst.setIsOccupy(1);
				if(Integer.parseInt(zportinst.getPortAttr().getMaxFrameSize())<1600){
					zportinst.getPortAttr().setMaxFrameSize("1600");
				}
				segment.setaPortInst(aportinst);
				segment.setzPortInst(zportinst);
				segment.setSpeedSegment(70+"");
				//未修改之前的
				PortInst oldaportinst = new PortInst();
				oldaportinst.setSiteId(aportinst.getSiteId());
				oldaportinst.setPortId(aportinst.getPortId());
				segment.setoldaPortInst(portService.select(oldaportinst).get(0));
				oldaportinst.setSiteId(zportinst.getSiteId());
				oldaportinst.setPortId(zportinst.getPortId());
				segment.setoldzPortInst(portService.select(oldaportinst).get(0));	
				//QOSQUEUES
				List<QosQueue> qosQueues = new ArrayList<QosQueue>();
				List<QosQueue> aqosQueues = new ArrayList<QosQueue>();
				List<QosQueue> zqosQueues = new ArrayList<QosQueue>();
				aqosQueues=getQosQueues(aportinst);
				zqosQueues=getQosQueues(zportinst);
				qosQueues.addAll(aqosQueues);
				qosQueues.addAll(zqosQueues);				
				oldqosMap = new HashMap<Integer, List<QosQueue>>();
				
				for (QosQueue qosQueue : aqosQueues) {
					if (oldqosMap.get(qosQueue.getSiteId()) == null) {
						infoList = new ArrayList<QosQueue>();
						for (QosQueue info : aqosQueues) {
							if (info.getSiteId() == qosQueue.getSiteId()) {
								info.setSiteName(siteService.select(segment.getASITEID()).getCellId());								
								infoList.add(info);
							}

						}
						oldqosMap.put(qosQueue.getSiteId(), infoList);
					}
				}
				for (QosQueue qosQueue : zqosQueues) {
					if (oldqosMap.get(qosQueue.getSiteId()) == null) {
						infoList = new ArrayList<QosQueue>();
						for (QosQueue info : zqosQueues) {
							if (info.getSiteId() == qosQueue.getSiteId()) {
								info.setSiteName(siteService.select(segment.getZSITEID()).getCellId());
								infoList.add(info);
							}

						}
						oldqosMap.put(qosQueue.getSiteId(), infoList);
					}
				}
				segment.setQosMap(oldqosMap);							
				segmentss.add(segment);
				portWHServiceImpl=new PortWHServiceImpl();
				portWHServiceImpl.excutionUpdate(aportinst);								
				portWHServiceImpl.excutionUpdate(zportinst);
				
			
				
				segmentService.saveOrUpdate(segment, qosQueues, null);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(segmentService);
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(siteService);
			portWHServiceImpl=null;
		}
	}
	
	/**
	 * 默认qos信息
	 * @param siteId
	 * @return
	 */
	private List<QosQueue> getQosQueues(PortInst portInst){
		List<QosQueue> qosQueues = new ArrayList<QosQueue>();
		for (int i = 0; i < 8; i++) {
			QosQueue qosQueue = new QosQueue();
			qosQueue.setCir(0);
			qosQueue.setCos(i);
			qosQueue.setObjType("SECTION");
			qosQueue.setQueueType("SP+DWRR");
			qosQueue.setMostBandwidth("无限制");
			qosQueue.setSiteId(portInst.getSiteId());
			qosQueue.setObjId(portInst.getPortId());
			qosQueue.setGreenHighThresh(50);
			qosQueue.setGreenLowThresh(90);
			qosQueue.setGreenProbability(100);
			qosQueue.setYellowHighThresh(64);
			qosQueue.setYellowLowThresh(96);
			qosQueue.setYellowProbability(100);
			qosQueue.setWeight(16);
			qosQueue.setWredEnable(false);
			qosQueues.add(qosQueue);
		}
		return qosQueues;
	}
	
	/**
	 * 定时重启网元
	 * @param siteInsts
	 * @return
	 * @throws RemoteException
	 */
	public String taskRboot(SiteInst siteInst) throws RemoteException{
		String result = "";
		OperationObject operationObjectAfter = null;
		OperationObject operationObjectResult = null;
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			long rbootTime = siteInst.getL();
//			//校时
			siteInst.setL(System.currentTimeMillis());
			this.circulateCurrectTime(siteInst);
			siteInst.setL(rbootTime);
			operationObjectAfter = this.getOperationObject(siteInst,"taskCurrecttime");
			//清空网元的摘要信息
			siteInst.setBs(null);
			siteInst.setResult("");
			siteInst.setAllFileName("");
			siteInst.setFileName("");
			//下发配置
			if (operationObjectAfter.getActionObjectList().size() > 0) {
				super.sendAction(operationObjectAfter);
				operationObjectResult = super.verification(operationObjectAfter);
				if(operationObjectResult.isSuccess()){
					//下发成功,返回状态
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
				}else{
					result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FALSE);
				}
			}else{
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		
		return result;
	}
	
	@SuppressWarnings("unused")
	private OperationObject getOperationObject(List<SiteInst> siteInsts,String type) throws Exception{
		OperationObject operationObject = null;
		NEObject neObject = null;
		SiteUtil siteUtil = null;
		WhImplUtil whImplUtil = null;
		try {
			operationObject = new OperationObject();
			siteUtil = new SiteUtil();
			for(SiteInst siteInst : siteInsts){
				if(siteUtil.SiteTypeUtil(siteInst.getSite_Inst_Id()) == 0){
					whImplUtil = new WhImplUtil();
					neObject = whImplUtil.siteIdToNeObject(siteInst.getSite_Inst_Id());
					neObject.setFile(siteInst.getFile());
					neObject.setStatusMark(siteInst.getStatusMark());
					neObject.setNeStatus(siteInst.getParameter());
					neObject.setIsCreateDiscardFlow(siteInst.getIsCreateDiscardFlow());
					neObject.setL(siteInst.getL());
					ActionObject actionObject = new ActionObject();
					actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
					actionObject.setNeObject(neObject);
					actionObject.setType(type);
					actionObject.setBs(siteInst.getBs());
					operationObject.getActionObjectList().add(actionObject);
				}
			}
			
		} finally {
			neObject = null;
			siteUtil = null;
			whImplUtil = null;
		}
		return operationObject;
	}
	
	public String routeIn() throws Exception{
		String result = "";
		OperationObject operationObjectResult = null;
		OperationObject operationObjectAfter = getOperationObject();
		//下发配置
		if (operationObjectAfter.getActionObjectList().size() > 0) {
			super.sendAction(operationObjectAfter);
			operationObjectResult = super.verification(operationObjectAfter,8000);
			if(operationObjectResult.isSuccess()){
				//下发成功,返回状态
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FALSE);
			}
		}else{
			result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
		}
		return result;
	}
	
	/**
	 * 获取operationobject对象
	 * @param siteId
	 * @return operationObject
	 * @throws Exception
	 */
	private OperationObject getOperationObject() throws Exception{
		OperationObject operationObject = null;
		ActionObject actionObject = null;
		NEObject neObject = null;
		operationObject = new OperationObject();
		neObject = new NEObject();
		neObject.setManageIP("255.255.255.255");
		actionObject = new ActionObject();
		actionObject.setActionId(super.getActionId(operationObject.getActionObjectList()));
		actionObject.setNeObject(neObject);
		actionObject.setType("routeIn");
		operationObject.getActionObjectList().add(actionObject);	
		return operationObject;
	}
}
