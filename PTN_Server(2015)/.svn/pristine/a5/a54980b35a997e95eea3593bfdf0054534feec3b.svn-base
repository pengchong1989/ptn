package com.nms.corba.ninterface.impl.service.proxy;

import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.StringHolder;

import subnetworkConnection.CrossConnect_T;
import subnetworkConnection.RouteCreateData_T;
import subnetworkConnection.RouteDescriptor_THolder;
import subnetworkConnection.Route_THolder;
import subnetworkConnection.SNCCreateData_T;
import subnetworkConnection.SNCIterator_IHolder;
import subnetworkConnection.SNCModifyData_T;
import subnetworkConnection.SubnetworkConnectionList_THolder;
import subnetworkConnection.SubnetworkConnection_T;
import subnetworkConnection.SubnetworkConnection_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.service.SNCIterator_Impl;
import com.nms.corba.ninterface.impl.service.tool.CorbaServiceConvrtTool;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EServiceType;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.ServiceFactory;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.CesDispatch;
import com.nms.service.impl.dispatch.PwDispatch;
import com.nms.service.impl.dispatch.TunnelDispatch;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.VerifyNameUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * corba接口，业务部分代理类
 * 
 * @author kk
 * 
 */
public class CorbaServiceProxy extends CorbaConvertBase{

	ICorbaSession session;
	private ELayerRate layerRate = null;// 获取类型
	private int primaryKey = 0;// 获取类型的主键 id

	public CorbaServiceProxy(ICorbaSession userSession) {
		this.session = userSession;
		
	}

	/**
	 * 激活SNC业务
	 * 
	 * @param nameAndStringValue_T
	 *            数组第三层为 层速率/主键
	 * @param subnetworkConnection_THolder
	 *            激活后，把结果传入此参数中
	 * @param stringHolder
	 *            激活失败原因
	 * @throws Exception
	 */
	public void activateSNC(NameAndStringValue_T[] sncName, SubnetworkConnection_THolder subnetworkConnection_THolder, StringHolder stringHolder) throws ProcessingFailureException {
		PwInfo pwInfo = null;
		Tunnel tunnel = null;
		PwInfoService_MB pwService = null;
		TunnelService_MB tunnelServive = null;
		PwDispatch pwDisPatch = null;
		TunnelDispatch tunnelDisPatch = null;
		CesDispatch cesDispatch=null;
		CesInfoService_MB cesInfoService=null;
		CesInfo cesInfo=null;
		String result = "";
		CorbaServiceConvrtTool corbaServiceConvrtTool = new CorbaServiceConvrtTool();
		try {
			//验证输入名称
			if(!CheckParameterUtil.checkSNCName(sncName)){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"sncName is invalid!");
			}			
			this.layerRate = corbaServiceConvrtTool.getLayerRate(sncName);
			this.primaryKey = corbaServiceConvrtTool.getPrimaryKey(sncName);
			// 处理tunnel
			if (this.layerRate == ELayerRate.TUNNEL) {
				tunnelDisPatch = new TunnelDispatch();
				tunnelServive = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
				tunnel = tunnelServive.selectId(this.primaryKey);
				//验证得到的数据是普通Tunnel
				if(!corbaServiceConvrtTool.checkTunnel(tunnel)){
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Internal error : The process has internal error!");
				}
				tunnel.setTunnelStatus(EActiveStatus.ACTIVITY.getValue());
				result = tunnelDisPatch.excuteUpdate(tunnel);
				subnetworkConnection_THolder.value = corbaServiceConvrtTool.convertSnc(tunnel,false);
			} else if(this.layerRate==ELayerRate.PW){// pw
				pwDisPatch = new PwDispatch();
				pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
				pwInfo = pwService.selectByPwId(this.primaryKey);
				pwInfo.setPwStatus(EActiveStatus.ACTIVITY.getValue());
				result = pwDisPatch.excuteUpdate(pwInfo);
				subnetworkConnection_THolder.value=corbaServiceConvrtTool.convertSnc(pwInfo,false);
			}else if(this.layerRate==ELayerRate.CES){
				cesDispatch=new CesDispatch();
				cesInfoService=(CesInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
				cesInfo=new CesInfo();
				cesInfo.setId(this.primaryKey);
				cesInfo=cesInfoService.selectByid(cesInfo);
				if(!UiUtil.isNull(cesInfo)){
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Internal error : The process has internal error!");
				}
				cesInfo.setActiveStatus(EActiveStatus.ACTIVITY.getValue());
				result=cesDispatch.excuteUpdate(cesInfo);
				subnetworkConnection_THolder.value=corbaServiceConvrtTool.convertSnc(cesInfo);
			}
			// 返回失败信息
			stringHolder.value =corbaServiceConvrtTool.outErrorMessage(result);
		} catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"Internal error : activateSNC ex.!");
		} finally {
			UiUtil.closeService_MB(tunnelServive);
			UiUtil.closeService_MB(pwService);
			UiUtil.closeService_MB(cesInfoService);
			pwInfo = null;
			tunnel = null;
			pwDisPatch = null;
			tunnelDisPatch = null;
			cesDispatch = null;
			cesInfo = null;
		}

	}

	/**
	 * 去激活SNC业务
	 * 
	 * @param nameAndStringValue_T
	 *            数组第三层为 层速率/主键
	 * @param subnetworkConnection_THolder
	 *            去激活后，把结果传入此参数中
	 * @param stringHolder
	 *            去激活失败原因
	 */
	public void deactivateSNC(NameAndStringValue_T[] sncName, SubnetworkConnection_THolder subnetworkConnection_THolder, StringHolder stringHolder)  throws ProcessingFailureException {
		PwInfo pwInfo = null;
		Tunnel tunnel = null;
		TunnelService_MB tunnelServive = null;
		PwDispatch pwDisPatch = null;
		TunnelDispatch tunnelDisPatch = null;
		PwInfoService_MB pwInfoService=null;
		CesDispatch cesDispatch=null;
		CesInfoService_MB cesInfoService=null;
		CesInfo cesInfo=null;
		String result = "";
		CorbaServiceConvrtTool corbaServiceConvrtTool = new CorbaServiceConvrtTool();
		try {
			//验证输入名称
			if(!CheckParameterUtil.checkSNCName(sncName)){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"sncName is invalid!");
			}
			this.layerRate = corbaServiceConvrtTool.getLayerRate(sncName);
			this.primaryKey = corbaServiceConvrtTool.getPrimaryKey(sncName);
			// 处理tunnel
			if (this.layerRate == ELayerRate.TUNNEL) {
				tunnelDisPatch = new TunnelDispatch();
				tunnelServive = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
				tunnel = tunnelServive.selectId(this.primaryKey);
				//验证并且此数据为普通类型的tunnel
				if(!corbaServiceConvrtTool.checkTunnel(tunnel)){
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Internal error : The process has internal error!");
				}
				tunnel.setTunnelStatus(EActiveStatus.UNACTIVITY.getValue());
				result = tunnelDisPatch.excuteUpdate(tunnel);
				subnetworkConnection_THolder.value = corbaServiceConvrtTool.convertSnc(tunnel,false);
			} else if(this.layerRate == ELayerRate.PW){// pw
				pwDisPatch = new PwDispatch();
				pwInfoService=(PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
				pwInfo=pwInfoService.selectByPwId(this.primaryKey);
				pwInfo.setPwStatus(EActiveStatus.UNACTIVITY.getValue());
				result = pwDisPatch.excuteUpdate(pwInfo);
				subnetworkConnection_THolder.value=corbaServiceConvrtTool.convertSnc(pwInfo,false);
			}else if(this.layerRate==ELayerRate.CES){
				cesDispatch=new CesDispatch();
				cesInfoService=(CesInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
				cesInfo=new CesInfo();
				cesInfo.setId(this.primaryKey);
				cesInfo=cesInfoService.selectByid(cesInfo);
				//判断： 通过主键查询，得到的数据不为空，并且数据库确实存在此id的数据
				if(!UiUtil.isNull(cesInfo)){
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Internal error : The process has internal error!");
				}
				cesInfo.setActiveStatus(EActiveStatus.UNACTIVITY.getValue());
				result=cesDispatch.excuteUpdate(cesInfo);
				subnetworkConnection_THolder.value=corbaServiceConvrtTool.convertSnc(cesInfo);
			}else{
				throw new ProcessingFailureException(
						ExceptionType_T.EXCPT_INVALID_INPUT,"sncName is invalid!");
			}
			stringHolder.value =corbaServiceConvrtTool.outErrorMessage(result);
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error: deactivateSNC ex.!");
		} finally {
			pwInfo = null;
			tunnel = null;
			UiUtil.closeService_MB(tunnelServive);
			UiUtil.closeService_MB(pwInfoService);
			UiUtil.closeService_MB(cesInfoService);
			pwDisPatch = null;
			tunnelDisPatch = null;
			cesDispatch = null;
			cesInfo = null;
		}
	}

	/**
	 * 删除SNC业务
	 * 
	 * @param nameAndStringValue_T
	 *            数组第三层为 层速率/主键
	 */
	public void deleteSNC(NameAndStringValue_T[] sncName)  throws ProcessingFailureException {
		List<Tunnel> tunnelList = null;
		List<PwInfo> pwInfoList = null;
		List<CesInfo> cesInfoList=null;
		PwInfo pwInfo;
		Tunnel tunnel = null;
		PwInfoService_MB pwService = null;
		TunnelService_MB tunnelServive = null;
		PwDispatch pwDisPatch = null;
		TunnelDispatch tunnelDisPatch = null;
		CesDispatch cesDispatch=null;
		CesInfoService_MB cesInfoService=null;
		CesInfo cesInfo=null;
		String result = "";
		CorbaServiceConvrtTool corbaServiceConvrtTool = new CorbaServiceConvrtTool();
		try {
			tunnelList = new ArrayList<Tunnel>();
			pwInfoList = new ArrayList<PwInfo>();
			cesInfoList=new ArrayList<CesInfo>();
			//验证输入名称
			if(!CheckParameterUtil.checkSNCName(sncName)){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"sncName is invalid!");
			}
			this.layerRate = corbaServiceConvrtTool.getLayerRate(sncName);
			this.primaryKey = corbaServiceConvrtTool.getPrimaryKey(sncName);
			// 处理tunnel
			if (this.layerRate == ELayerRate.TUNNEL) {
				tunnelDisPatch = new TunnelDispatch();
				tunnelServive = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
				tunnel = tunnelServive.selectId(this.primaryKey);
				if(!corbaServiceConvrtTool.checkTunnel(tunnel)){
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Internal error : The process has internal error!");
				}
				tunnelList.add(tunnel);
				result = tunnelDisPatch.excuteDelete(tunnelList);
			} else if(this.layerRate == ELayerRate.PW){// pw
				pwDisPatch = new PwDispatch();
				pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
				pwInfo = new PwInfo();
				pwInfo.setPwId(this.primaryKey);
				pwInfo = pwService.selectByPwId(primaryKey);
				pwInfoList.add(pwInfo);
				result = pwDisPatch.excuteDelete(pwInfoList);
			}else if(this.layerRate==ELayerRate.CES){
				cesDispatch=new CesDispatch();
				cesInfoService=(CesInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
				cesInfo=new CesInfo();
				cesInfo.setId(this.primaryKey);
				cesInfo=cesInfoService.selectByid(cesInfo);
				if(!UiUtil.isNull(cesInfo)){
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"The process has internal error!");
				}
				cesInfoList.add(cesInfo);
				result=cesDispatch.excuteDelete(cesInfoList);
			}
			// 返回失败信息
			if(!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Internal error : deleteSNC fail!");
			}
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error : deleteSNC ex.!");
		} finally {
			pwInfo = null;
			tunnel = null;
			UiUtil.closeService_MB(tunnelServive);
			UiUtil.closeService_MB(pwService);
			UiUtil.closeService_MB(cesInfoService);
			pwDisPatch = null;
			tunnelDisPatch = null;
			tunnelList = null;
			pwInfoList = null;
			cesInfoList=null;
			cesDispatch = null;
			cesInfo = null;
		}
	}

	/**
	 * 修改SNC业务
	 * 
	 * @param nameAndStringValue_T
	 *            数组第三层为 层速率/主键
	 * @param sncModifyData_T
	 *            修改的参数
	 * @param subnetworkConnection_THolder
	 *            修改后的结果。
	 * @param stringHolder
	 *            修改失败原因
	 */
	public void modifySNC(NameAndStringValue_T[] sncName, SNCModifyData_T sncModifyData_T, SubnetworkConnection_THolder subnetworkConnection_THolder, StringHolder stringHolder)  throws ProcessingFailureException {
		if(!UiUtil.isNull(sncModifyData_T.userLabel)){
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT,
					"userLabel is invalid!");
		}
		PwInfo pwInfo = null;
		Tunnel tunnel = null;
		TunnelDispatch tunnelDispatch=null;
		PwDispatch pwDispatch=null;
		CesDispatch cesDispatch=null;
		PwInfoService_MB pwService = null;
		TunnelService_MB tunnelServive = null;
		CesInfoService_MB cesInfoService=null;
		CesInfo cesInfo=null;
		String result="";
		VerifyNameUtil verifyNameUtil=null;//验证名称是否重复
		CorbaServiceConvrtTool corbaServiceConvrtTool = new CorbaServiceConvrtTool();
		try {
			//验证输入名称
			if(!CheckParameterUtil.checkSNCName(sncName)){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"sncName is invalid!");
			}
			this.layerRate = corbaServiceConvrtTool.getLayerRate(sncName);
			this.primaryKey = corbaServiceConvrtTool.getPrimaryKey(sncName);
			verifyNameUtil=new VerifyNameUtil();
			// 处理tunnel
			if (this.layerRate == ELayerRate.TUNNEL) {
				tunnelDispatch=new TunnelDispatch();
				tunnelServive = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
				tunnel = tunnelServive.selectId(this.primaryKey);
				if(!corbaServiceConvrtTool.checkTunnel(tunnel)){
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Internal error : The process has internal error!");
				}
				//验证名称是否重复
				if(!verifyNameUtil.verifyName(EServiceType.TUNNEL.getValue(), sncModifyData_T.userLabel, tunnel.getTunnelName())){
					//验证通过，修改名称
					tunnel.setTunnelName(sncModifyData_T.userLabel);
					result=tunnelDispatch.excuteUpdate(tunnel);						
				}else{//验证没有通过，返回错误信息
					result=ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
				}
				//修改成功，返回修改后的信息： 修改未通过，返回源信息
				subnetworkConnection_THolder.value = corbaServiceConvrtTool.convertSnc(tunnel,false);

			} else if(this.layerRate == ELayerRate.PW){// pw
				pwDispatch=new PwDispatch();
				pwService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
				pwInfo = pwService.selectByPwId(this.primaryKey);	
				//验证名称是否重复
				if(!verifyNameUtil.verifyName(EServiceType.PW.getValue(), sncModifyData_T.userLabel, pwInfo.getPwName())){
					//验证通过，修改名称
					pwInfo.setPwName(sncModifyData_T.userLabel);
					result=pwDispatch.excuteUpdate(pwInfo);								
				}else{//验证没有通过，返回错误信息
					result=ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
				}
				//修改成功，返回修改后的信息： 修改未通过，返回源信息
				subnetworkConnection_THolder.value = corbaServiceConvrtTool.convertSnc(pwInfo,false);	
			
			}else if(this.layerRate==ELayerRate.CES){//修改CES
				cesDispatch=new CesDispatch();
				cesInfoService=(CesInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
				cesInfo=new CesInfo();
				cesInfo.setId(this.primaryKey);
				cesInfo=cesInfoService.selectByid(cesInfo);
				if(!UiUtil.isNull(cesInfo)){
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Internal error : The process has internal error!");
				}
				//验证名称是否重复
				if(!verifyNameUtil.verifyName(EServiceType.CES.getValue(), sncModifyData_T.userLabel, cesInfo.getName())){
					//验证通过，修改名称
					cesInfo.setName(sncModifyData_T.userLabel);
					result=cesDispatch.excuteUpdate(cesInfo);								
				}else{//验证没有通过，返回错误信息
					result=ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
				}
				//修改成功，返回修改后的信息： 修改未通过，返回源信息
				subnetworkConnection_THolder.value = corbaServiceConvrtTool.convertSnc(cesInfo);
			}
			stringHolder.value=corbaServiceConvrtTool.outErrorMessage(result);
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error :modifySNC ex.!");
		} finally {
			pwInfo = null;
			tunnel = null;
			UiUtil.closeService_MB(tunnelServive);
			UiUtil.closeService_MB(pwService);
			UiUtil.closeService_MB(cesInfoService);
			cesInfo = null;
		}
	}

	/**
	 * 创建SNC业务
	 * 
	 * @param sncCreateData
	 *            要创建的SNC参数
	 * @param subnetworkConnection
	 *            创建后的结果
	 * @param stringHolder
	 *            创建失败原因
	 * @throws Exception
	 */
	public void createSNC(SNCCreateData_T sncCreateData, SubnetworkConnection_THolder subnetworkConnection, StringHolder stringHolder)  throws ProcessingFailureException  {
		if(sncCreateData.layerRate==0){
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT,
					"layerRate is 0");
		}
		Tunnel tunnel=null;
		PwInfo pwInfo=null;
		TunnelService_MB tunnelService=null;
		TunnelDispatch tunnelDispatch=null;
		PwDispatch pwDispatch=null;
		CesDispatch cesDispatch=null;
		PwInfoService_MB pwInfoService=null;
		List<CesInfo> cesInfoList=null;
		CesInfoService_MB cesInfoService=null;
		CesInfo cesInfo=null;
		String result="";
		List<Integer> tunnelIds=new ArrayList<Integer>();
		VerifyNameUtil verifyNameUtil=null;//验证名称是否重复
		CorbaServiceConvrtTool corbaServiceConvrtTool = new CorbaServiceConvrtTool();
		try {
			verifyNameUtil=new VerifyNameUtil();
			// 判断是tunnel速率还是pw速率
			if (sncCreateData.layerRate == ELayerRate.TUNNEL.getValue()) {
				
				//验证名称是否重复
				if(!verifyNameUtil.verifyName(EServiceType.TUNNEL.getValue(), sncCreateData.userLabel,null )){
					//验证通过，可以创建
					// 处理tunnel
					tunnelDispatch=new TunnelDispatch();
					tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
					tunnel=corbaServiceConvrtTool.convertTunnel(sncCreateData,this.session);
					result=tunnelDispatch.excuteInsert(tunnel);
					tunnel=tunnelService.selectId(tunnel.getTunnelId());
					subnetworkConnection.value=corbaServiceConvrtTool.convertSnc(tunnel,false);
				}else{//验证未通过： 名称已经存在
					result=ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
					subnetworkConnection.value=corbaServiceConvrtTool.setDefaultSubnetworkConnection_T();
				}											
			} else if (sncCreateData.layerRate == ELayerRate.PW.getValue()) {
				//验证名称是否重复
				if(!verifyNameUtil.verifyName(EServiceType.PW.getValue(),sncCreateData.userLabel, null )){
					//验证通过，可以创建
					// 处理pw
					pwDispatch=new PwDispatch();
					pwInfoService=(PwInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
					tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
					pwInfo=corbaServiceConvrtTool.convertPwinfo(sncCreateData,this.session);
					tunnelService.selectId(pwInfo.getTunnelId());//给定tunnel是否存在，不存在直接抛异常（service层throw 了）
					tunnelIds.add(pwInfo.getTunnelId());//验证tunnel是否被使用
					int count = pwInfoService.selectPwInfoByTunnelId(tunnelIds).size();
					if (count > 0) {
						throw new ProcessingFailureException(
								ExceptionType_T.EXCPT_INVALID_INPUT,
								"ServerConnections is invalid!");
					}
					result=pwDispatch.excuteInsert(pwInfo);
					pwInfo=pwInfoService.selectBypwid(pwInfo);
					subnetworkConnection.value=corbaServiceConvrtTool.convertSnc(pwInfo,false);		
				}else{//验证未通过： 名称已经存在
					result=ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
					subnetworkConnection.value=corbaServiceConvrtTool.setDefaultSubnetworkConnection_T();
				}														
						
			}else if(sncCreateData.layerRate==ELayerRate.CES.getValue()){
				//验证名称是否重复
				if(!verifyNameUtil.verifyName(EServiceType.CES.getValue(), sncCreateData.userLabel,null)){
					//验证通过，可以创建
					cesInfoList=new ArrayList<CesInfo>();
					cesDispatch=new CesDispatch();
					cesInfoService=(CesInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
					pwInfoService=(PwInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
					cesInfo=corbaServiceConvrtTool.convertCesinfo(sncCreateData,this.session);	
					pwInfo=new PwInfo();
					pwInfo.setPwId(cesInfo.getPwId());
					pwInfo=pwInfoService.selectBypwid(pwInfo);
					//验证通过输入pw主键ID 是否找到pw
					if(pwInfo==null){
						result="ServerConnections is null";
						subnetworkConnection.value=corbaServiceConvrtTool.setDefaultSubnetworkConnection_T();
					}
					//验证 通过输入条件查找的pw 是否可用
					else if(pwInfo.getRelatedServiceId()>0){
						result="ServerConnections is invalid!";
						subnetworkConnection.value=corbaServiceConvrtTool.setDefaultSubnetworkConnection_T();
					}
					//验证pw类型是否与CES类型相同
					else if(pwInfo.getType().getValue()==cesInfo.getCestype()){
						cesInfoList.add(cesInfo);
						result=cesDispatch.excuteInsert(cesInfoList);
						cesInfo=cesInfoService.selectByid(cesInfo);
						subnetworkConnection.value=corbaServiceConvrtTool.convertSnc(cesInfo);	
						
					}else{//pw,ces类型不相同，返回错误信息
						result="CesType is invalid!";
						subnetworkConnection.value=corbaServiceConvrtTool.setDefaultSubnetworkConnection_T();
					}	
				}else{//验证未通过： 名称已经存在
					result=ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST);
					subnetworkConnection.value=corbaServiceConvrtTool.setDefaultSubnetworkConnection_T();
				}									
			}
			stringHolder.value=corbaServiceConvrtTool.outErrorMessage(result);

		}catch(ProcessingFailureException e){
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error : createSNC ex.!");
		}finally{
			tunnel = null;
			pwInfo = null;
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(pwInfoService);
			UiUtil.closeService_MB(cesInfoService);
			tunnelDispatch = null;
			pwDispatch = null;
			cesInfoList = null;
			cesDispatch = null;
			cesInfo = null;
		}
	}

	/**
	 * 根据主键查询SNC对象
	 * 
	 * @param nameAndStringValue_T
	 *            数组第三层为 层速率/主键
	 * @param subnetworkConnection_THolder
	 *            查询后的结果
	 * @throws Exception
	 */
	public  void getSNC(NameAndStringValue_T[] sncName, SubnetworkConnection_THolder subnetworkConnection_THolder)  throws ProcessingFailureException  {
	
		TunnelService_MB tunnelService = null;
		Tunnel tunnel = null;
		PwInfoService_MB pwInfoService = null;
		PwInfo pwinfo = null;
		CesInfoService_MB cesInfoService=null;
		CesInfo cesInfo=null;
		CorbaServiceConvrtTool corbaServiceConvrtTool = new CorbaServiceConvrtTool();
		try {
			//验证输入名称
			if(!CheckParameterUtil.checkSNCName(sncName)){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"sncName is invalid!");
			}
			this.layerRate = corbaServiceConvrtTool.getLayerRate(sncName);
			this.primaryKey = corbaServiceConvrtTool.getPrimaryKey(sncName);
			if (this.layerRate == ELayerRate.TUNNEL) {
				// 根据主键查询tunnel
				tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
				tunnel = tunnelService.selectId(this.primaryKey);
				if(!corbaServiceConvrtTool.checkTunnel(tunnel)){
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"LSP is invalid!");
				}
				// 转换tunnel对象
				subnetworkConnection_THolder.value = corbaServiceConvrtTool.convertSnc(tunnel,true);
				
			} else if(this.layerRate == ELayerRate.PW){
				// 根据主键查询pw
				pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
				pwinfo = pwInfoService.selectByPwId(this.primaryKey);
//				if(!UiUtil.isNull(pwinfo)||!UiUtil.isNull(pwinfo.getPwName())){
//					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"no find data!");
//				}
				// 转换pw对象
				subnetworkConnection_THolder.value = corbaServiceConvrtTool.convertSnc(pwinfo,true);
			}else if(this.layerRate==ELayerRate.CES){
				cesInfoService=(CesInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
				cesInfo=new CesInfo();
				cesInfo.setId(this.primaryKey);
				cesInfo=cesInfoService.selectByid(cesInfo);	
				if(!UiUtil.isNull(cesInfo)){
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT," The process has internal error!");
				}
				// 转换ces对象
				subnetworkConnection_THolder.value = corbaServiceConvrtTool.convertSnc(cesInfo);						
			}
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error : getSNC ex.!");
		} finally {
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(pwInfoService);
			UiUtil.closeService_MB(cesInfoService);
			tunnel = null;
			pwinfo = null;
			cesInfo=null;
		}

	}

	/**
	 * 根据指定的速率查询所有SNC业务
	 * 
	 * @param nameAndStringValue_T
	 * 			名称   2层
	 * @param layers
	 *            速率数组，tunnel和pw同时存在时，需要都返回
	 * @param howMany
	 *            查询数量，迭代查询用
	 * @param sncList
	 *            查询返回结果
	 * @param sncIt
	 *            查询迭代器
	 * @throws Exception
	 */
	public void getAllSubnetworkConnections(NameAndStringValue_T[] subnetName,short[] layers, int howMany, SubnetworkConnectionList_THolder sncList, SNCIterator_IHolder sncIt)  throws ProcessingFailureException  { 
		if(howMany<=0){
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INVALID_INPUT,
					"howMany is invalid!");
		}
		TunnelService_MB tunnelService = null;
		PwInfoService_MB pwInfoService = null;
		CesInfoService_MB cesInfoService=null;
		List<Tunnel> tunnelList = new ArrayList<Tunnel>();
		List<Tunnel> infoList=null;
		List<PwInfo> pwInfoList = new ArrayList<PwInfo>();
		List<CesInfo> cesInfoList=new ArrayList<CesInfo>();
		SNCIterator_Impl sncIterator_Impl = null;
		CorbaServiceConvrtTool corbaServiceConvrtTool = new CorbaServiceConvrtTool();	
		SubnetworkConnection_T[] tunnel_subnetConnection=null;
		SubnetworkConnection_T[] pw_subnetConnection=null;
		SubnetworkConnection_T[] ces_subnetConnection=null;
		boolean flag=false;//标记输入的查询速率数组中速率与 tunnel,pw，ces匹配情况
		List<SubnetworkConnection_T> subnetConnectionList=new ArrayList<SubnetworkConnection_T>();
		
		try {
			//验证输入名称
			if(!CheckParameterUtil.checkMLSubnetName(subnetName)){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"subnetName is invalid!");
			}
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			cesInfoService=(CesInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			if (layers.length==0||corbaServiceConvrtTool.isExitArrays(layers, (short)ELayerRate.TUNNEL.getValue()) ) {
				// 查询出网络侧所有的tunnel
				infoList = tunnelService.select();
				if(UiUtil.isNull(infoList)){
					for(Tunnel info:infoList){//过滤，取出普通类型的tunnel
						if(corbaServiceConvrtTool.checkTunnel(info)){
						//	System.out.println(info.getTunnelId());
							tunnelList.add(info);
						}
					}
				}
				flag=true;
			} 
			if (layers.length==0||corbaServiceConvrtTool.isExitArrays(layers, (short)ELayerRate.PW.getValue())) {
				// 查询出网络侧所有的pw				
				pwInfoList = pwInfoService.select();
				flag=true;
			} 
			if(layers.length==0||corbaServiceConvrtTool.isExitArrays(layers, (short)ELayerRate.CES.getValue())){
				//查询出网络层所有CES					
				cesInfoList=cesInfoService.select();
				flag=true;
			}
			if(!flag){
				throw new ProcessingFailureException(
						ExceptionType_T.EXCPT_INVALID_INPUT,
						"layers is invalid!");
			}
			
			
			if(tunnelList.size()>0){
				tunnel_subnetConnection=new SubnetworkConnection_T[tunnelList.size()];
				subnetConnectionList.addAll(corbaServiceConvrtTool.convertTunnelToSnc(tunnelList, tunnel_subnetConnection));
			}
			if(pwInfoList.size()>0){
				pw_subnetConnection=new SubnetworkConnection_T[pwInfoList.size()];
				subnetConnectionList.addAll(corbaServiceConvrtTool.convertPwToSnc(pwInfoList, pw_subnetConnection));
				
			}
			if(cesInfoList.size()>0){
				ces_subnetConnection=new SubnetworkConnection_T[cesInfoList.size()];
				subnetConnectionList.addAll(corbaServiceConvrtTool.convertCesToSnc(cesInfoList, ces_subnetConnection));
				//subnetConnectionList.add(ces_subnetConnection);
			}
			// 把tunnel、pw,ces集合转换成snc集合对象
		//	sncList.value = new SubnetworkConnection_T[tunnelList.size() + pwInfoList.size()+cesInfoList.size()];
			sncList.value=subnetConnectionList.toArray(new SubnetworkConnection_T[subnetConnectionList.size()]);
			sncIterator_Impl = new SNCIterator_Impl(this.session);
			sncIterator_Impl.setIterator(sncIt, sncList, howMany);

		}catch(ProcessingFailureException e){ 
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Interval error:getAllSubnetworkConnections ex.");
		} finally {
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(pwInfoService);
			UiUtil.closeService_MB(cesInfoService);
			tunnelList = null;
			pwInfoList = null;
			sncIterator_Impl = null;
		}

	}

	/**
	 * 根据指定的速率查询所有SNC业务
	 * 
	 * @param nameAndStringValue
	 *            数组第三层为 层速率/主键
	 * @param route_THolder
	 *            查询路由的结果
	 * @throws Exception
	 */
	public void getRoute(NameAndStringValue_T[] nameAndStringValues, Route_THolder route_THolder)  throws ProcessingFailureException  {
		Tunnel tunnel = null;
		TunnelService_MB tunnelService = null;
		CorbaServiceConvrtTool corbaServiceConvrtTool = new CorbaServiceConvrtTool();
		try {
			//验证输入名称
			if(!CheckParameterUtil.checkSNCName(nameAndStringValues)){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"输入类型错误");
			}
			this.layerRate = corbaServiceConvrtTool.getLayerRate(nameAndStringValues);
			this.primaryKey = corbaServiceConvrtTool.getPrimaryKey(nameAndStringValues);

			// 处理tunnel
			if (this.layerRate == ELayerRate.TUNNEL) {
				tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
				tunnel = tunnelService.selectId(this.primaryKey);

				if (null != tunnel && null != tunnel.getLspParticularList() && tunnel.getLspParticularList().size() > 1) {
					corbaServiceConvrtTool.convertRouteToTunnel(tunnel, route_THolder);
				}else{
					//不存在XC 时，设置默认 值
					route_THolder.value=new CrossConnect_T[0];
				}

			} else {
				throw new ProcessingFailureException(
						ExceptionType_T.EXCPT_INVALID_INPUT,
						"Internal error : The process has internal error!");
			}

		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Interval error:getRoute ex.");
		} finally {
			UiUtil.closeService_MB(tunnelService);
		}
	}

	public static void main(String[] args) {

		ServiceFactory serviceFactory = null;
//		Properties properties = null;

		// 初始化工厂类
		serviceFactory = new ServiceFactory();
//		properties = new Properties();
//		properties.put(ServiceFactory.HOSTNAME, "192.168.200.212");
//		properties.put(ServiceFactory.PTNUSER, "admin");
//		try {
//			ConstantUtil.serviceFactory = serviceFactory;
//		} catch (Exception e1) {
//			ExceptionManage.dispose(e1,this.getClass());
//		}
		ConstantUtil.serviceFactory = serviceFactory;

		ICorbaSession userSession = new ICorbaSession();
		CorbaServiceProxy corbaServiceConvrtTool = new CorbaServiceProxy(userSession);
		NameAndStringValue_T[] nameAndStringValues = new NameAndStringValue_T[3];
		NameAndStringValue_T nameAndStringValue_T = new NameAndStringValue_T();
		nameAndStringValue_T.name = "1";
		nameAndStringValue_T.value = "1";
		nameAndStringValues[0] = nameAndStringValue_T;

		nameAndStringValue_T = new NameAndStringValue_T();
		nameAndStringValue_T.name = "2";
		nameAndStringValue_T.value = "2";
		nameAndStringValues[1] = nameAndStringValue_T;

		nameAndStringValue_T = new NameAndStringValue_T();
		nameAndStringValue_T.name = "3";
		nameAndStringValue_T.value = "6/42";
		nameAndStringValues[2] = nameAndStringValue_T;

		try {
		//	CesInfo cesInfo=(CesInfo) corbaServiceConvrtTool.getSNC(nameAndStringValues,new SubnetworkConnection_THolder());
			//System.out.println(cesInfo.getaSiteId());
			corbaServiceConvrtTool.getRoute(nameAndStringValues, new Route_THolder());
		} catch (Exception e) {
			ExceptionManage.dispose(e,CorbaServiceProxy.class);
		}
	}
	
	/**
	 * 添加网络路由
	 * @param sncName TUNNEL 名称
	 * @param createRoute	添加的保护TUNNEL
	 * @param theRoute 输出的路由信息
	 * @param errorReason	错误原因
	 * @throws ProcessingFailureException 
	 */
	public void addRoute(NameAndStringValue_T[] sncName,
			RouteCreateData_T createRoute, RouteDescriptor_THolder theRoute,
			StringHolder errorReason) throws ProcessingFailureException {
		TunnelService_MB tunnelService = null;
		Tunnel tunnel;
		List<Tunnel> tunnelList;
		TunnelDispatch tunnelDispatch;
		String result = null;
		CorbaServiceConvrtTool corbaServiceConvrtTool;
		CorbaConvertBase corbaConvertBase;
		//验证输入名称
		if(!CheckParameterUtil.checkSNCName(sncName))
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"输入参数无效");
		
		try {
			tunnel = new Tunnel();
			tunnelDispatch=new TunnelDispatch();
			corbaConvertBase = new CorbaConvertBase();
			corbaServiceConvrtTool = new CorbaServiceConvrtTool();
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnel.setTunnelId(corbaConvertBase.getPrimaryKey(sncName));
			tunnelList = tunnelService.select(tunnel);
			if(null == tunnelList || tunnelList.size() != 1)
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"实体没有找到");
			tunnel = tunnelList.get(0);
			tunnel = corbaServiceConvrtTool.convertTunnel(tunnel,createRoute);
			result=tunnelDispatch.excuteUpdate(tunnel);
			corbaServiceConvrtTool.convertTNP(tunnel.getProtectTunnel(),theRoute);//转换TNP对象
			errorReason.value=corbaServiceConvrtTool.outErrorMessage(result);
		}  catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"addRoute");
		} finally {
			UiUtil.closeService_MB(tunnelService);
		}
	}

	/**
	 * 删除网络路由
	 * @param sncName 连接名称
	 * @param routeId	路由标识符
	 * @param result 
	 * @param additionalInfo 附加信息
	 * @throws ProcessingFailureException 
	 */
	public void removeRoute(NameAndStringValue_T[] sncName, String routeId,NVSList_THolder additionalInfo) throws ProcessingFailureException {
		TunnelService_MB tunnelService = null;
		Tunnel tunnel;
		List<Tunnel> tunnelList;
		TunnelDispatch tunnelDispatch;
		String result;
		CorbaConvertBase corbaConvertBase;
		try {
			//验证输入名称
			if(!CheckParameterUtil.checkTNPName(sncName))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"输入参数无效");
			
			corbaConvertBase = new CorbaConvertBase();
			tunnel = new Tunnel();
			tunnelDispatch=new TunnelDispatch();
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);			
			tunnel.setTunnelId(corbaConvertBase.getPrimaryKey(sncName));
			tunnelList = tunnelService.select(tunnel);			
			if(null == tunnelList || tunnelList.size() != 1)
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"实体没有找到");
			
			tunnel = tunnelList.get(0);
			if(!routeId.equals(tunnel.getProtectTunnel().getTunnelId()+""))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"输入参数无效");
			
			tunnel.setTunnelType( UiUtil.getCodeByValue("PROTECTTYPE", "1").getId()+"");// 普通类型
			result = tunnelDispatch.excuteUpdate(tunnel);
			if(null == additionalInfo.value ||additionalInfo.value.length==0){
				additionalInfo.value = new NameAndStringValue_T[1];
				additionalInfo.value[0] = new NameAndStringValue_T();
			}
			additionalInfo.value[0].name = "ErrorMessage";
			additionalInfo.value[0].value = super.outErrorMessage(result);
		}  catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"removeRoute");
		} finally {
			UiUtil.closeService_MB(tunnelService);
		}
	}
}
