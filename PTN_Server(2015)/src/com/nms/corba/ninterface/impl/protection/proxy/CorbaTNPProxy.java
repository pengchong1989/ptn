package com.nms.corba.ninterface.impl.protection.proxy;

import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.StringHolder;

import protection.ProtectionCommand_T;
import protection.ReversionMode_T;
import trailNtwProtection.TNPSwitchData_THolder;
import trailNtwProtection.TrailNtwProtCreateData_T;
import trailNtwProtection.TrailNtwProtModifyData_T;
import trailNtwProtection.TrailNtwProtectionIterator_IHolder;
import trailNtwProtection.TrailNtwProtectionList_THolder;
import trailNtwProtection.TrailNtwProtection_T;
import trailNtwProtection.TrailNtwProtection_THolder;

import com.nms.corba.ninterface.enums.ESwitchDirection;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.protection.TrailNtwProtectionIterator_Impl;
import com.nms.corba.ninterface.impl.protection.tool.CorbaTNPConvrtTool;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.ERotateType;
import com.nms.db.enums.EServiceType;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.ProtectRotateDispatch;
import com.nms.service.impl.dispatch.TunnelDispatch;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.VerifyNameUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * TNP corba代理
 * @author dzy
 *
 */
public class CorbaTNPProxy extends CorbaConvertBase{
	ICorbaSession session;
	private CorbaTNPConvrtTool corbaTNPConvrtTool;
	private ELayerRate layerRate;	// 获取类型
	private int primaryKey;	// 获取类型的主键 id
	public CorbaTNPProxy(ICorbaSession userSession) {
		this.session = userSession;
		this.corbaTNPConvrtTool = new CorbaTNPConvrtTool();
	}
	
	/**
	 * 查询所有路径网络保护（TNP）
	 * @param how_many	首次获取的数目
	 * @param tnpList	首次获取的TNP信息
	 * @param tnpIt		迭代获取TNP接口
	 * @throws ProcessingFailureException 
	 * @throws Exception 
	 */
	public void getAllTNP(NameAndStringValue_T[] resourceName,int how_many,
			TrailNtwProtectionList_THolder tnpList,
			TrailNtwProtectionIterator_IHolder tnpIt) throws ProcessingFailureException{
		List<Tunnel> tunnelList;
		try {
			if(CheckParameterUtil.checkMLSubnetName(resourceName))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			tunnelList = getTunnel(0);
			if(null == tunnelList || tunnelList.size()==0){
				tnpList.value = new TrailNtwProtection_T[0];
				return;
			}
			tnpList.value = new TrailNtwProtection_T[tunnelList.size()];
			this.corbaTNPConvrtTool.convertTNPList(tunnelList,tnpList.value);
			TrailNtwProtectionIterator_Impl iter = new TrailNtwProtectionIterator_Impl(this.session);
			iter.setIterator(tnpIt, tnpList, how_many);
		} catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"CorbaTNPProxy ex.");
		}finally{
			tunnelList = null;
		}
	}
	
	

	/**
	 *  查询路径网络保护（TNP）
	 * @param tnpName	TNP名称
	 * @param tNtwProtection	TNP信息
	 */
	public void getTNP(NameAndStringValue_T[] tnpName,
			TrailNtwProtection_THolder tNtwProtection) throws ProcessingFailureException {
		List<Tunnel> tunnelList;
		if(!CheckParameterUtil.checkTNPName(tnpName))
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		try {
			this.layerRate = this.corbaTNPConvrtTool.getLayerRate(tnpName);
			if(ELayerRate.TUNNEL.getValue() != this.layerRate.getValue())
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			this.primaryKey = this.corbaTNPConvrtTool.getPrimaryKey(tnpName);
			tunnelList = getTunnel(this.primaryKey);
			if(null == tunnelList || tunnelList.size() != 1)
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found");
			
			if(null == tNtwProtection.value)
				tNtwProtection.value = new TrailNtwProtection_T();
			this.corbaTNPConvrtTool.convertTNP(tunnelList.get(0),tNtwProtection.value);
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"CorbaTNPProxy ex.");
		}finally{
			tunnelList = null;
		}
	}
	
	/**
	 * 通过条件获取tunnel
	 * @param id	ID
	 * @param type	Tunnel类型 185=普通，186=1：1
	 * @return	tunnel集合
	 * @throws Exception 
	 */
	private List<Tunnel> getTunnel(int id) throws Exception {
		List<Tunnel> tunnelList = null;
		Tunnel tunnel = new Tunnel();
		TunnelService_MB tunnelService  = null;
		try {
			if(0 != id)
			  tunnel.setTunnelId(id);
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnelList = tunnelService.queryByCondition_joinRotate(tunnel);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			tunnel = null;
			UiUtil.closeService_MB(tunnelService);
		}
		return  tunnelList;
	}
	
	/**
	 * 删除路径网络保护
	 * @param tnpName 删除的TNP标示符
	 * @param keepPGs true：相关的保护组保留，false 相关的保护组删除
	 * @param additionalInfo 附加信息
	 * @param deletedPGList 删除的保护组
	 * @param deletedTNP 删除的TNP
	 * @param errorReason 错误原因
	 * @throws ProcessingFailureException 
	 */
	public void deleteTrailNtwProtection(NameAndStringValue_T[] tnpName,StringHolder errorReason) throws ProcessingFailureException {
		//获取TNP主键
		if(!CheckParameterUtil.checkTNPName(tnpName))
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		this.primaryKey = this.corbaTNPConvrtTool.getPrimaryKey(tnpName);
		List<Tunnel> tunnelList = null;
		TunnelDispatch tunnelDispatch = new TunnelDispatch();
//		SiteRoateService siteRoateService = null;
//		SiteRoate siteRoate = null;
		try {
			int id = this.primaryKey;
			tunnelList = getTunnel(id);
			if (null == tunnelList || tunnelList.size() == 0) {
				//没找到id对应的对象
				errorReason.value = "The entity is not found.";
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
			}
			//删除整个保护组
			String result = tunnelDispatch.excuteDelete(tunnelList);
			if (ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL_TUNNEL_USE).equals(result)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_NOT_IMPLEMENTED,"The tunnel is being used.");
			}
			//给deletedPGList 及 deletedTNP赋值
//			tunnel = tunnelList.get(0);
//			siteRoateService = (SiteRoateService) ConstantUtil.serviceFactory.newService(Services.SITEROATE);
//			siteRoate = new SiteRoate();
//			siteRoate.setType("tunnel");
//			siteRoate.setTypeId(id);
//			siteRoate = siteRoateService.select(siteRoate);
//			int siteroate = 0;
//			if (null != siteRoate) {
//				siteroate = siteRoate.getSiteRoate();
//			}
//			corbaTNPConvrtTool = new CorbaTNPConvrtTool();
			errorReason.value = super.outErrorMessage(result);
//			corbaTNPConvrtTool.setDeletePGsInfo(tunnel,deletedPGList,additionalInfo);
//			corbaTNPConvrtTool.setDeleteTNPInfo(tunnel,tnpName,deletedTNP,siteroate);
		}  catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			errorReason.value = "Internal errer";
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"Internal errer");
		}finally{
			tunnelList = null;
			tunnelDispatch = null;
//			siteRoate = null;
//			siteRoateService = null;
		}
	}

	/**
	 * 修改路径网络保护（TNP）信息
	 * @param tnpName 要修改的TNP标示符
	 * @param tnpModifyData 修改的TNP数据
	 * @param modifiedTNP 修改的TNP
	 * @param errorReason 错误原因
	 * @throws ProcessingFailureException 
	 */
	public void modifyTrailNtwProtection(NameAndStringValue_T[] tnpName,
			TrailNtwProtModifyData_T tnpModifyData,
			TrailNtwProtection_THolder modifiedTNP, StringHolder errorReason) throws ProcessingFailureException {
		//名称验证
		if(!CheckParameterUtil.checkTNPName(tnpName))
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		//获取TNP主键
		this.primaryKey = this.corbaTNPConvrtTool.getPrimaryKey(tnpName);
		TunnelDispatch tunnelDispatch = null;
		Tunnel condition = null;
		List<Tunnel> tunnelList = null;
		int siteRoate = 0;//倒换命令
		boolean flag = false;//是否更新数据库的标志，若没有修改则不更新 为false
		try {
			if (null == tnpModifyData.userLabel && null ==tnpModifyData.reversionMode && null == tnpModifyData.tnpParameters) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			}
			//按照条件查找tunnel
			tunnelList = getTunnel(this.primaryKey);
			if (null == tunnelList|| tunnelList.size() == 0) {
				//没找到id对应的对象
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
			}
			condition = tunnelList.get(0);
			if (null != tnpModifyData.userLabel && !"".equals(tnpModifyData.userLabel)) {
				//首先判断是否重名
				VerifyNameUtil nameUtil = new VerifyNameUtil();
				if (nameUtil.verifyName(EServiceType.TUNNEL.getValue(), tnpModifyData.userLabel, condition.getTunnelName())) {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"UserLabel already exists.");
				}
				//修改tunnel的名称
				condition.setTunnelName(tnpModifyData.userLabel);
				flag = true;
				condition.getProtectTunnel().setTunnelName(tnpModifyData.userLabel+"_protect");//修改相应的保护的名称
			}
			if (null != tnpModifyData.reversionMode && tnpModifyData.reversionMode.value() != 0) {
				//修改返回模式武汉的scnp
				if (tnpModifyData.reversionMode.value() == ReversionMode_T.RM_NON_REVERTIVE.value()) {
					//不返回
					condition.getProtectTunnel().setProtectBack(0);
				}else if (tnpModifyData.reversionMode.value() == ReversionMode_T.RM_REVERTIVE.value()) {
					//返回
					condition.getProtectTunnel().setProtectBack(1);
				}
				flag = true;
			}
			if (flag) {
				tunnelDispatch = new TunnelDispatch();
				tunnelDispatch.excuteUpdate(condition);//修改tunnel
			}
			if (null != tnpModifyData.tnpParameters && tnpModifyData.tnpParameters.length != 0
					&& tnpModifyData.tnpParameters[0].value.matches("[0-9]+") && tnpModifyData.tnpParameters[1].value != null 
					&& !"".equals(tnpModifyData.tnpParameters[1].value)) {
				//修改保护倒换
				TNPSwitchData_THolder tnpSwitchData = new TNPSwitchData_THolder();
				siteRoate = Integer.parseInt(tnpModifyData.tnpParameters[0].value);
				performTNPCommand(ProtectionCommand_T.from_int(siteRoate),tnpName,tnpModifyData.tnpParameters[1].value,tnpSwitchData);
				condition.setRotate_a(siteRoate);
				condition.setRotate_z(siteRoate);
			}
			errorReason.value = "success";
			modifiedTNP.value = new TrailNtwProtection_T();
			this.corbaTNPConvrtTool.convertTNP(condition,modifiedTNP.value);
		} catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			errorReason.value = "Internal errer";
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"Internal errer");
		}finally{
			condition = null;
			tunnelList = null;
			tunnelDispatch = null;
		}
	}

	/**
	 * 人工保护倒换
	 * @param protectionCommand 倒换命令
	 * @param tnpName 倒换对象标识符
	 * @param switchDirection 倒换方向
	 * @param tnpSwitchData 倒换信息通知
	 * @throws ProcessingFailureException 
	 */
	public void performTNPCommand(ProtectionCommand_T protectionCommand,
			NameAndStringValue_T[] tnpName, String switchDirection,
			TNPSwitchData_THolder tnpSwitchData) throws ProcessingFailureException {
		if(!CheckParameterUtil.checkTNPName(tnpName))
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		//获取TNP主键
		this.primaryKey = this.corbaTNPConvrtTool.getPrimaryKey(tnpName);
		TunnelService_MB tunnelService = null;
		Tunnel tunnel = null;
		List<Tunnel> tunnelList = null;
		SiteRoate roateA = null;//倒换对象
		SiteRoate roateZ = null;//倒换对象
		ProtectRotateDispatch protectRotateDispatch = null;
		List<SiteRoate> siteRoateList=null;
		String result = "";
		try {
			protectRotateDispatch = new ProtectRotateDispatch();
			tunnel = new Tunnel();
			tunnel.setTunnelId(this.primaryKey);
			tunnel.setTunnelType("186");
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnelList = tunnelService.select(tunnel);
			protectRotateDispatch = new ProtectRotateDispatch();
			siteRoateList=new ArrayList<SiteRoate>();
			if (null == tunnelList || tunnelList.size() != 1) {
				//没找到id对应的对象
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
			}
			tunnel = tunnelList.get(0);
			//网络端的倒换zSiteId和aSiteId都不得为0
			if (tunnel.getASiteId() == 0 || tunnel.getZSiteId() == 0) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			}
			roateA = new SiteRoate();
			//a端网元信息存于roate中
			//设置倒换命令
			roateA.setRoate(setRoateCommand(protectionCommand,switchDirection));
			roateA.setType("tunnel");
			roateA.setTypeId(tunnel.getTunnelId());
			roateA.setSiteId(tunnel.getASiteId());
			roateA.setTunnel(tunnel);
			roateA.setSiteRoate(0);
			siteRoateList.add(roateA);
			//z端网元信息存于roate中
			roateZ = new SiteRoate();
			roateZ.setRoate(setRoateCommand(protectionCommand,switchDirection));
			roateZ.setType("tunnel");
			roateZ.setTypeId(tunnel.getTunnelId());
			roateZ.setSiteId(tunnel.getZSiteId());
			roateZ.setTunnel(tunnel);
			roateZ.setSiteRoate(0);
			siteRoateList.add(roateZ);
			result=protectRotateDispatch.excuteUpdate(siteRoateList);
			this.corbaTNPConvrtTool.settnpSwitchData(tnpName,tunnel,tnpSwitchData,result);
		}catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"Internal errer");
		}finally{
			UiUtil.closeService_MB(tunnelService);
		}
	}

	/**
	 * 设置网络侧人工保护倒换的倒换命令
	 * @param protectionCommand
	 * @param switchDirection
	 */
	private int setRoateCommand(ProtectionCommand_T protectionCommand, String switchDirection) {
		if (switchDirection.equals(ESwitchDirection.WORK.toString()) && protectionCommand.value() == ProtectionCommand_T.PC_FORCED_SWITCH.value()) {
			//强制倒换到工作侧 ERotateType.FORCESWORK
			return ERotateType.FORCESWORK.getValue();
		}else if (switchDirection.equals(ESwitchDirection.PROTECT.toString()) && protectionCommand.value() == ProtectionCommand_T.PC_FORCED_SWITCH.value() ) {
			//强制倒换到保护侧 ERotateType.FORCESPRO ;
			return ERotateType.FORCESPRO.getValue();
		}else if ( protectionCommand.value() == ProtectionCommand_T.PC_MANUAL_SWITCH.value() &&switchDirection.equals(ESwitchDirection.WORK.toString())) {
			//人工倒换到工作侧 ERotateType.MANUALWORK
			return ERotateType.MANUALWORK.getValue();
		}else if (protectionCommand.value() == ProtectionCommand_T.PC_MANUAL_SWITCH.value() && switchDirection.equals(ESwitchDirection.PROTECT.toString())) {
			//人工倒换到保护侧 ERotateType.MANUALPRO
			return ERotateType.MANUALPRO.getValue();
		}else if (protectionCommand.value() == ProtectionCommand_T.PC_EXERCISER.value() && switchDirection.equals(ESwitchDirection.WORK.toString())) {
			//练习倒换到工作侧 ERotateType.PRACTICEJOB
			return ERotateType.PRACTICEJOB.getValue();
		}else if (protectionCommand.value() == ProtectionCommand_T.PC_EXERCISER.value() && switchDirection.equals(ESwitchDirection.PROTECT.toString())) {
			//练习倒换到保护侧 ERotateType.PRACTICEPRO
			return ERotateType.PRACTICEPRO.getValue();
		}else if (protectionCommand.value() == ProtectionCommand_T.PC_LOCKOUT.value()) {
			// ERotateType.LOCK 锁定
			return ERotateType.LOCK.getValue();
		}else {
			//ERotateType.CLEAR 清除
			return ERotateType.CLEAR.getValue();
		}
	}

	/**
	 * 创建TNP
	 * @param tnpCreateData		创建的TNP对象
	 * @param theTNP	返回TNP对象
	 * @param errorReason	返回参数
	 * @throws ProcessingFailureException 
	 */
	public void createTNP(TrailNtwProtCreateData_T tnpCreateData,
			TrailNtwProtection_THolder theTNP, StringHolder errorReason) throws ProcessingFailureException {
		String result;
		Tunnel tunnel;
		TunnelDispatch tunnelDispatch;
		try {
			tunnelDispatch = new TunnelDispatch();
			if(tnpCreateData.rate != ELayerRate.TUNNEL.getValue()){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"The entity is not found");
			}
			tunnel = this.corbaTNPConvrtTool.ConvrtCreateTNP(tnpCreateData,this.session);
			result = tunnelDispatch.excuteInsert(tunnel);
			if(null == theTNP.value)
				theTNP.value = new TrailNtwProtection_T();
			this.corbaTNPConvrtTool.convertTNP(tunnel, theTNP.value);
			errorReason.value = super.outErrorMessage(result);
		}catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"createTNP ex.");
		}finally{
			tunnel = null;
			tunnelDispatch = null;
			result = null;
		}
	}

}
