package com.nms.corba.ninterface.impl.protection.proxy;

import emsMgr.EMS_T;
import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.List;

import protection.ProtectionCommand_T;
import protection.ProtectionGroupIterator_IHolder;
import protection.ProtectionGroupList_THolder;
import protection.ProtectionGroup_T;
import protection.ProtectionGroup_THolder;
import protection.SwitchData_THolder;

import com.nms.corba.ninterface.enums.EProtectionType;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.protection.ProtectionMgrIterator_Impl;
import com.nms.corba.ninterface.impl.protection.tool.CorbaProtectTool;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.bean.ptn.path.protect.LoopProtectInfo;
import com.nms.db.bean.ptn.path.protect.MspProtect;
import com.nms.db.bean.ptn.path.protect.Protect_Corba;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.ERotateType;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.SiteRoateService_MB;
import com.nms.model.ptn.path.protect.MspProtectService_MB;
import com.nms.model.ptn.path.protect.ProtectServiceCorba_MB;
import com.nms.model.ptn.path.protect.WrappingProtectService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.MspDispatch;
import com.nms.service.impl.dispatch.ProtectRotateDispatch;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.VerifyNameUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * 保护代理
 * @author dzy
 *
 */
public class CorbaProtectProxy extends CorbaConvertBase{

	ICorbaSession session;
	/**
	 * 创建一个新的实例
	 *  @param userSession session
	 */
	public CorbaProtectProxy(ICorbaSession userSession) {
		this.session = userSession;
	}

	/**
	 * 查询保护组信息
	 * @param how_many 数量
	 * @param meList 第一组数据
	 * @param meIt	迭代
	 * @throws ProcessingFailureException
	 */
	public void getAllProtectionGroups(NameAndStringValue_T[] meName,int how_many,
			ProtectionGroupList_THolder pgpList,
			ProtectionGroupIterator_IHolder pgpIt)
			throws ProcessingFailureException {
		List<Protect_Corba> protect_CorbaList;
		ProtectServiceCorba_MB protectService_Corba = null;
		int neId;
		Protect_Corba protect_Corba;
		CorbaProtectTool corbaProtectTool;
		try {
			//验证入参
			if(CheckParameterUtil.checkMeName(meName))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			corbaProtectTool = new CorbaProtectTool();
			neId = Integer.parseInt(meName[1].value);
			protect_Corba = new Protect_Corba();
			protect_Corba.setSiteId(neId);
			protectService_Corba = (ProtectServiceCorba_MB) ConstantUtil.serviceFactory.newService_MB(Services.PROTECT_CORBA);
			protect_CorbaList = protectService_Corba.queryProtectByCondition(protect_Corba);
			if(null==protect_CorbaList||protect_CorbaList.size()==0){
				pgpList.value = new ProtectionGroup_T[0];
				return;
			}
			pgpList.value = new ProtectionGroup_T[protect_CorbaList.size()];
			//转换corba对象
			corbaProtectTool.protectConvertList(protect_CorbaList, pgpList.value);
			ProtectionMgrIterator_Impl iter = new ProtectionMgrIterator_Impl(this.session);
			//迭代
			iter.setIterator(pgpIt, pgpList, how_many);
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"getAllProtectionGroups ex.");
		} finally {
			UiUtil.closeService_MB(protectService_Corba);
		}
	}

	/**
	 * 修改保护组名称
	 * @param objectName 传来网元
	 * @param userLabel 修改后的保护组名称
	 * @param enforceUniqueness 是否唯一
	 * @throws ProcessingFailureException
	 */
	public void setUserLabel(NameAndStringValue_T[] objectName,
			String userLabel, boolean enforceUniqueness)throws ProcessingFailureException {
		//保护组标示符的判断，名称为长度为3的数组，每个数组元素不为空，值固定
		if (!CheckParameterUtil.checkPGPName(objectName)) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		}
		String type = objectName[2].value;
		String prType = type.substring(0,type.indexOf('/'));//哪个类型
		int id = getPrimaryKey(objectName);//修改的id
		changeProtectGroupName(id,prType,userLabel);//修改保护组名称
		
	}
	
	/**
	 * 修改保护组名称
	 * @param id 修改的表中id
	 * @param type 修改的是哪个表
	 * @param userLabel 修改后的名称值
	 * @throws ProcessingFailureException 
	 */
	private static void changeProtectGroupName(int id, String type, String userLabel) throws ProcessingFailureException{
		if (null == type) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		}
		VerifyNameUtil nameUtil = null;
		MspProtectService_MB mspProtectService = null;
		TunnelService_MB tunnelService = null;
		WrappingProtectService_MB protectService = null;
		try {
			nameUtil = new VerifyNameUtil();
			if (type.equals(EProtectionType.MSPPROTECT.getValue()+"")) {
				//修改msp保护
				MspProtect mspProtect = null;
				mspProtectService = (MspProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MSPPROTECT);
				mspProtect = mspProtectService.selectById(id);
				if (null == mspProtect) {
					//没找到id对应的对象
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found");
				}
				//判断是否存在该名称
				if (nameUtil.verifyName(EServiceType.PW.getValue(), userLabel, mspProtect.getName())) {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"UserLabel already exists.");
				}
				mspProtect.setName(userLabel);
				mspProtectService.saveOrUpdate(mspProtect);
				mspProtect = null;
			}else if (type.equals(EProtectionType.TUNNEL.getValue()+"")) {
				//修改lsp1:1保护
				Tunnel condition = null;
				List<Tunnel> tunnelList = null;
				condition = new Tunnel();
				condition.setTunnelId(id);
				tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
				tunnelList = tunnelService.select_nojoin(condition);
				if (null == tunnelList|| tunnelList.size() == 0) {
					//没找到id对应的对象
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found");
				}
				condition = tunnelList.get(0);
				//判断是否存在该名称
				if (nameUtil.verifyName(EServiceType.TUNNEL.getValue(), userLabel, condition.getTunnelName())) {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"UserLabel already exists.");
				}
				condition.setTunnelName(userLabel);
				if (null == condition.getProtectTunnel()) {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found");
				}
				condition.getProtectTunnel().setTunnelName(userLabel+"_protect");
				tunnelService.update(condition);//修改lsp的名称
			}else if (type.equals(EProtectionType.LOOPPROTECT.getValue()+"")) {
				//修改环网保护名称
				LoopProtectInfo loopInfo =null;
				List<LoopProtectInfo> loopList = null;
				loopInfo = new LoopProtectInfo();
				loopInfo.setId(id);
				protectService = (WrappingProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WRAPPINGPROTECT);
				loopList = protectService.select(loopInfo);
				if (null == loopList || loopList.size() == 0) {
					//没找到id对应的对象
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found");
				}
				//判断是否存在该名称
				if (nameUtil.verifyName(EServiceType.LOOPPROTECT.getValue(), userLabel, loopList.get(0).getName())) {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"UserLabel already exists.");
				}
				loopList.get(0).setName(userLabel);
				protectService.update(loopList);
				
				loopList = null;
			}else {
				//以上三种类型以外的为错误
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"setUserLabel ex.");
		}finally{
			UiUtil.closeService_MB(mspProtectService);
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(protectService);
		}
	}
	
	public static boolean getEms(EMS_T value) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 人工保护倒换
	 * @param protectionCommand 倒换类型
	 * @param reliableSinkCtpOrGroupName 倒换对象
	 * @param toTp 决定倒换到工作侧还是保护侧
	 * @param switchData 保护倒换信息通知
	 * @throws ProcessingFailureException 
	 */
	public void performProtectionCommand(ProtectionCommand_T protectionCommand,
			NameAndStringValue_T[] reliableSinkCtpOrGroupName,
			NameAndStringValue_T[] toTp, SwitchData_THolder switchData) throws ProcessingFailureException {
		//倒换对象标示符的判断，名称为长度为3的数组，每个数组元素不为空，值固定
		if (!CheckParameterUtil.checkPGPName(reliableSinkCtpOrGroupName)) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		}
		//决定倒换到工作侧还是保护侧的唯一标示，名称为长度为3的数组，每个元素不为空，并且值固定
		if (!CheckParameterUtil.checkPTPName(toTp)) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		}
		String type=reliableSinkCtpOrGroupName[2].value;
		String strId = type.substring(type.indexOf("/")+1);
		//判断截取的id是否正确
		if (!strId.matches("[0-9]+") || !reliableSinkCtpOrGroupName[1].value.matches("[0-9]+") ) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		}
		int id = Integer.parseInt(strId);//倒换对象的id
		if (type.substring(0,type.indexOf('/')).equals(EProtectionType.MSPPROTECT.getValue()+"")) {
			//进入msp倒换
			int siteId = Integer.parseInt(reliableSinkCtpOrGroupName[1].value);
			performProtectionCommandMsp(siteId,id,protectionCommand, switchData, toTp);
		}else if (type.substring(0,type.indexOf('/')).equals(EProtectionType.TUNNEL.getValue()+"")) {
			//进入tunnel倒换
			performProtectionCommandTunnel(id,protectionCommand, switchData, toTp);
		}else {
			//以上两种类型以外的为错误
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		}
	}
	
	/**
	 * msp人工倒换
	 * @param siteId msp网元id 
	 * @param id 保护组标示中的id 
	 * @param protectionCommand 保护命令
	 * @param switchData 倒换信息通知
	 * @param toTpId 倒换到的端口id
	 * @throws ProcessingFailureException 
	 */
	private void performProtectionCommandMsp(int siteId,int id,ProtectionCommand_T protectionCommand, SwitchData_THolder switchData, NameAndStringValue_T[] toTp) throws ProcessingFailureException{
		String result = null;
		MspDispatch mspDispatch=null;
		MspProtect mspProtect = null; 
		MspProtectService_MB mspProtectService = null;
		SiteRoate siteRoate=null;
		SiteRoateService_MB siteRoateService=null;
		CorbaProtectTool corbaProtectTool;
		List<MspProtect> mspProtects = null;
		try {
			corbaProtectTool = new CorbaProtectTool();
			siteRoateService = (SiteRoateService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITEROATE);
			mspProtectService = (MspProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MSPPROTECT);
			mspProtect = new MspProtect();
			mspProtect.setId(id);
			mspProtect.setSiteId(siteId);
			mspProtects = new ArrayList<MspProtect>();
			mspProtects = mspProtectService.select(mspProtect);
			if (null == mspProtects || mspProtects.size() == 0) {
				//没找到id对应的对象
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found");
			}
			CorbaConvertBase convertBase = new CorbaConvertBase();
			String toTpidStr = convertBase.getValueByKey( toTp, "PTP", mspProtects.get(0).getSiteId());
			int toTpId = Integer.parseInt(toTpidStr);
			int siteRoateOrder = setProtectCommand(mspProtects.get(0).getWorkPortId(),mspProtects.get(0).getProtectPortId(), toTpId, protectionCommand);
			mspProtects.get(0).setRotateOrder(siteRoateOrder);
			mspDispatch=new MspDispatch();
			if (toTpId == mspProtects.get(0).getProtectPortId()) {
				mspProtects.get(0).setNowWorkPortId(mspProtects.get(0).getProtectPortId());
			}else {
				mspProtects.get(0).setNowWorkPortId(mspProtects.get(0).getWorkPortId());
			}
			result = mspDispatch.excuteUpdate(mspProtects.get(0));
			//倒换成功的动作，存储倒换信息
			if(result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))){
				siteRoate=new SiteRoate();
				siteRoate.setType("msp");
				siteRoate.setTypeId(mspProtects.get(0).getId());
				siteRoate.setSiteId(mspProtects.get(0).getSiteId());
				siteRoate.setRoate(siteRoateOrder);
				siteRoateService.update(siteRoate);
			}
			corbaProtectTool.setSwitchData(mspProtects.get(0).getWorkPortId(),mspProtects.get(0).getId(),switchData,id,1,result);
			
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"performProtectionCommand ex.");
		}finally{
			UiUtil.closeService_MB(siteRoateService);
			UiUtil.closeService_MB(mspProtectService);
		}
	}
	
	/**
	 * tunnel人工倒换
	 * @param id 保护组标示中的id 
	 * @param protectionCommand 保护命令
	 * @param switchData 倒换信息通知
	 * @param toTpId 倒换到的端口id
	 * @throws ProcessingFailureException 
	 */
	private void performProtectionCommandTunnel(int id,
			ProtectionCommand_T protectionCommand,
			SwitchData_THolder switchData, NameAndStringValue_T[] toTp) throws ProcessingFailureException {
		TunnelService_MB tunnelService = null;
		Tunnel tunnel = null;
		SiteRoate roate = null;//倒换对象
		String result="";
		ProtectRotateDispatch protectRotateDispatch = null;
		List<SiteRoate> siteRoateList=null;
		int workId;
		int protectId;
		List<Tunnel> tunnelList = null;
		SiteService_MB siteService=null;
		CorbaProtectTool corbaProtectTool;
		String toTpidStr = "";
		try {
			corbaProtectTool = new CorbaProtectTool();
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			protectRotateDispatch = new ProtectRotateDispatch();
			siteRoateList=new ArrayList<SiteRoate>();
			tunnel = new Tunnel();
			tunnel.setTunnelId(id);
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnelList = tunnelService.select_nojoin(tunnel);
			if (null == tunnelList || tunnelList.size() != 1) {
				//没找到id对应的对象
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found");
			}
			tunnel = tunnelList.get(0);
			if (null == tunnel.getProtectTunnel()) {
				//没找到id对应的对象
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found");
			}
			/**
			 * 先判断A 端 网元id,
			 *   >0 : 可能是单网元（新建tunnel的类型为入口），也可能是网络层 ，正常提取数据					
			 *   不大于0 ：（即==0），则必然为单网元，（新建tunnel的类型为出口）
			 */
			roate=new SiteRoate();
			CorbaConvertBase convertBase = new CorbaConvertBase();
			if(tunnel.getASiteId()>0){
				//武汉的 网元倒换
				toTpidStr = convertBase.getValueByKey( toTp, "PTP", tunnel.getASiteId());
				int toTpId = Integer.parseInt(toTpidStr);
				roate.setRoate(setProtectCommand(tunnel.getAPortId(), tunnel.getProtectTunnel().getAPortId(), toTpId,protectionCommand));
				if (siteService.getManufacturer(tunnel.getASiteId()) == EManufacturer.WUHAN.getValue()) {
					roate.setSiteRoate(siteService.getManufacturer(tunnel.getASiteId()));
				}
			}else {
				toTpidStr = convertBase.getValueByKey( toTp, "PTP", tunnel.getZSiteId());
				int toTpId = Integer.parseInt(toTpidStr);
				roate.setRoate(setProtectCommand(tunnel.getZPortId(), tunnel.getProtectTunnel().getZPortId(), toTpId,protectionCommand));
				//武汉的 网元倒换
				if (siteService.getManufacturer(tunnel.getZSiteId()) == EManufacturer.WUHAN.getValue()) {
					roate.setSiteRoate(siteService.getManufacturer(tunnel.getZSiteId()));
				}
			}
			workId = tunnel.getTunnelId();
			protectId = tunnel.getProtectTunnel().getTunnelId();
			setSiteRoate(roate, tunnel);
			
			if(roate!=null){
				siteRoateList.add(roate);
			}
			result=protectRotateDispatch.excuteUpdate(siteRoateList);
			corbaProtectTool.setSwitchData(workId,protectId,switchData,id,0,result);
			
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"performProtectionCommand ex.");
		}finally{
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(siteService);
		}
	}

	/**
	 * 倒换命令bean赋值
	 * @param siteRoate 传来的倒换命令bean
	 * @param object 传来的tunnel 或msp 对象
	 */
	private static void setSiteRoate(SiteRoate siteRoate,Tunnel tunnel){
		siteRoate.setType("tunnel");
		siteRoate.setTypeId(tunnel.getProtectTunnelId());
		if (tunnel.getASiteId()!=0) {
			siteRoate.setSiteId(tunnel.getASiteId());
		}else {
			siteRoate.setSiteId(tunnel.getZSiteId());
		}
		siteRoate.setTunnel(tunnel);
	}
	
	/**
	 * 设置倒换命令，根据参数
	 * @param isTorM isTorM 0 为 tunnel的倒换  1为msp的倒换
	 * @param protectionCommand 倒换命令
	 * @param workPortId 工作侧id
	 * @param protectPortId 保护侧id
	 * @param toTpId 倒换到的端口id
	 */
	private int setProtectCommand(int workPortId,int protectPortId,int toTpId,ProtectionCommand_T protectionCommand){
		if (toTpId == workPortId && protectionCommand.value() == ProtectionCommand_T.PC_FORCED_SWITCH.value()) {
			//强制倒换到工作侧 ERotateType.FORCESWORK
			return ERotateType.FORCESWORK.getValue();
		}else if (toTpId == protectPortId && protectionCommand.value() == ProtectionCommand_T.PC_FORCED_SWITCH.value() ){
			//强制倒换到保护侧 ERotateType.FORCESPRO ;
			return ERotateType.FORCESPRO.getValue();
		}else if (protectionCommand.value() == ProtectionCommand_T.PC_MANUAL_SWITCH.value() && toTpId ==workPortId) {
			//人工倒换到工作侧 ERotateType.MANUALWORK
			return ERotateType.MANUALWORK.getValue();
		}else if (protectionCommand.value() == ProtectionCommand_T.PC_MANUAL_SWITCH.value() && toTpId == protectPortId) {
			//人工倒换到保护侧 ERotateType.MANUALPRO
			return ERotateType.MANUALPRO.getValue();
		}else if (protectionCommand.value() == ProtectionCommand_T.PC_EXERCISER.value() && toTpId == workPortId ) {
			//练习倒换到工作侧 ERotateType.PRACTICEJOB
			return ERotateType.PRACTICEJOB.getValue();
		}else if (protectionCommand.value() == ProtectionCommand_T.PC_EXERCISER.value() && toTpId == protectPortId) {
			//练习倒换到保护侧 ERotateType.PRACTICEPRO
			return ERotateType.PRACTICEPRO.getValue();
		}else if (protectionCommand.value() == ProtectionCommand_T.PC_LOCKOUT.value()) {
			//ERotateType.LOCK 锁定
			return ERotateType.LOCK.getValue();
		}else {
			//ERotateType.CLEAR 清除 
			return ERotateType.CLEAR.getValue();
		}
	}

	/**
	 * 查询指定 保护组
	 * @param pgName	保护组名称	
	 * @param protectionGroup	保护组corba对象
	 * @throws ProcessingFailureException
	 */
	public void getProtectionGroup(NameAndStringValue_T[] pgName,
			ProtectionGroup_THolder protectionGroup) throws ProcessingFailureException {
		List<Protect_Corba> protect_CorbaList;
		ProtectServiceCorba_MB protectService_Corba = null;
		String[] type;
		int id;
		Protect_Corba protect_Corba;
		String neId;
		CorbaProtectTool corbaProtectTool;
		try {
			if(!CheckParameterUtil.checkPGPName(pgName))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			neId = super.getValueByKey(pgName, "ManagedElement");
			type = pgName[2].value.split("/");
			id = super.getPrimaryKey(pgName);
			corbaProtectTool = new CorbaProtectTool();
			protect_Corba = new Protect_Corba();
			protect_Corba.setSiteId(Integer.parseInt(neId));
			protect_Corba.setId(id);
			protect_Corba.setObjType(getObjType(type[0]));
			protectService_Corba = (ProtectServiceCorba_MB) ConstantUtil.serviceFactory.newService_MB(Services.PROTECT_CORBA);
			protect_CorbaList = protectService_Corba.queryProtectByCondition(protect_Corba);
			if(null==protect_CorbaList||protect_CorbaList.size()!=1){
				throw new ProcessingFailureException(ExceptionType_T. EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
			}
			if(null == protectionGroup.value)
				protectionGroup.value = new ProtectionGroup_T();
			corbaProtectTool.convertProtectDataX2Corba(protect_CorbaList.get(0), protectionGroup.value);
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getProtectionGroup ex.");
		} finally {
			UiUtil.closeService_MB(protectService_Corba);
		}
	}

	/**
	 * 获取保护类型
	 * @param objType 	参考EProtectionType
	 * @return
	 */
	private String getObjType(String objType) {
		String result = null;
		if(objType.equals(EProtectionType.TUNNEL.getValue()+""))
			result = EProtectionType.TUNNEL.toString();
		if(objType.equals(EProtectionType.MSPPROTECT.getValue()+""))
			result = EProtectionType.MSPPROTECT.toString();
		if(objType.equals(EProtectionType.LOOPPROTECT.getValue()+""))
			result = EProtectionType.LOOPPROTECT.toString();
		
		return result;
	}

}
