package com.nms.corba.ninterface.impl.protection.tool;

import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.Iterator;
import java.util.List;

import protection.ProtectionCommand_T;
import protection.ProtectionGroupList_THolder;
import protection.ProtectionGroupType_T;
import protection.ProtectionGroup_T;
import protection.ProtectionSchemeState_T;
import protection.ReversionMode_T;
import protection.SwitchReason_T;
import subnetworkConnection.CrossConnect_T;
import trailNtwProtection.TNPSwitchData_T;
import trailNtwProtection.TNPSwitchData_THolder;
import trailNtwProtection.TrailNtwProtCreateData_T;
import trailNtwProtection.TrailNtwProtModifyData_T;
import trailNtwProtection.TrailNtwProtection_T;

import com.nms.corba.ninterface.enums.EOAMType;
import com.nms.corba.ninterface.enums.EProtectionGroupType;
import com.nms.corba.ninterface.enums.ESwitchDirection;
import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.service.tool.CorbaServiceConvrtTool;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.ERotateType;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.VerifyNameUtil;

/**
 * TNP转换类
 * @author dzy
 *
 */
public class CorbaTNPConvrtTool  extends CorbaConvertBase{
	// 交叉路由附加信息KEY值
	private final String SRCINLABEL = "SrcInLabel"; // 源入标签
	private final String SRCOUTLABEL = "SrcOutLabel"; // 源出标签
	private final String DESTINLABEL = "DestInLabel"; // 宿入标签
	private final String DESTOUTLABEL = "DestOutLabel"; // 宿出标签
	/**
	 * 转换TNP集合
	 * @param tunnelList	tunnel集合
	 * @param tnpList	tnp集合
	 * @throws Exception 
	 */
	public void convertTNPList(List<Tunnel> tunnelList, TrailNtwProtection_T[] tnpList) throws Exception {
		int i = 0;
		for(Iterator<Tunnel> iter = tunnelList.iterator(); iter.hasNext();){
			tnpList[i] = new TrailNtwProtection_T();
			this.convertTNP((Tunnel)iter.next(), tnpList[i]);
			i++;
		}
	}

	/**
	 * 转换TNP集合
	 * @param tunnel TUNNEL
	 * @param tnp	TNP
	 * @throws Exception 
	 */
	public void convertTNP(Tunnel tunnel, TrailNtwProtection_T tnp) throws Exception {
		String model = "";
		String position = "";
		tnp.name = super.convertName(ELayerRate.TNP.getValue(), tunnel.getTunnelId(), 0);
		tnp.rate = (short) ELayerRate.TUNNEL.getValue();
		tnp.nativeEMSName = tunnel.getTunnelName();
		tnp.userLabel = tunnel.getTunnelName();
		tnp.owner = super.getOwner();
		tnp.protectionSchemeState = ProtectionSchemeState_T.PSS_UNKNOWN;
		if(0==tunnel.getProtectBack()){
			tnp.reversionMode = ReversionMode_T.RM_REVERTIVE;
		}else if(1==tunnel.getProtectBack()){
			tnp.reversionMode = ReversionMode_T.RM_NON_REVERTIVE;
		}else{
			tnp.reversionMode = ReversionMode_T.RM_UNKNOWN;
		}
		tnp.trailNtwProtectionType =  "Closed";
		tnp.protectionGroupAName =	super.convertName(ELayerRate.PGP.getValue(), tunnel.getTunnelId(), tunnel.getASiteId());
		tnp.protectionGroupZName = super.convertName(ELayerRate.PGP.getValue(), tunnel.getTunnelId(), tunnel.getZSiteId());
		tnp.pgATPList = new NameAndStringValue_T[2][3];
		tnp.pgATPList[0] = super.convertName(ELayerRate.PORT.getValue(), tunnel.getAPortId(), tunnel.getASiteId());
		tnp.pgATPList[1] = super.convertName(ELayerRate.PORT.getValue(), tunnel.getProtectTunnel().getAPortId(), tunnel.getProtectTunnel().getASiteId());

		tnp.pgZTPList = new NameAndStringValue_T[2][3];
		tnp.pgZTPList[0] = super.convertName(ELayerRate.PORT.getValue(), tunnel.getZPortId(), tunnel.getZSiteId());
		tnp.pgZTPList[1] = super.convertName(ELayerRate.PORT.getValue(), tunnel.getProtectTunnel().getZPortId(), tunnel.getProtectTunnel().getZSiteId());

		if(null == tnp.tnpParameters)
			tnp.tnpParameters = new NameAndStringValue_T[9];
	
		tnp.tnpParameters[0] = new NameAndStringValue_T("ApsEnable",tunnel.getApsenable()==1?"Enabled":"Disabled");
		model = this.getRotateModelAndPosition(tunnel.getRotate_a(), "Model");
		position = this.getRotateModelAndPosition(tunnel.getRotate_a(), "Position");
		tnp.tnpParameters[1] = new NameAndStringValue_T("ARotateModel",model);
		tnp.tnpParameters[2] = new NameAndStringValue_T("ARotatePosition",position);
		
		model = this.getRotateModelAndPosition(tunnel.getRotate_z(), "Model");
		position = this.getRotateModelAndPosition(tunnel.getRotate_z(), "Position");
		tnp.tnpParameters[3] = new NameAndStringValue_T("ZRotateModel",model);
		tnp.tnpParameters[4] = new NameAndStringValue_T("ZRotatePosition",position);
		tnp.tnpParameters[5] = super.getMegId(tunnel.getTunnelId(), tunnel.getASiteId(), EOAMType.TUNNEL.toString(), "AMEG", OamTypeEnum.MEP.getValue());
		tnp.tnpParameters[6] = super.getMegId(tunnel.getTunnelId(), tunnel.getZSiteId(), EOAMType.TUNNEL.toString(), "ZMEG", OamTypeEnum.MEP.getValue());
		tnp.tnpParameters[7] = super.getMegId(tunnel.getProtectTunnel().getTunnelId(), tunnel.getProtectTunnel().getASiteId(), EOAMType.TUNNEL.toString(), "AMEG_Pro", OamTypeEnum.MEP.getValue());
		tnp.tnpParameters[8] = super.getMegId(tunnel.getProtectTunnel().getTunnelId(), tunnel.getProtectTunnel().getZSiteId(), EOAMType.TUNNEL.toString(), "ZMEG_Pro", OamTypeEnum.MEP.getValue());
		
		
		
		tnp.workerTrailList = new NameAndStringValue_T[1][1][3];
		tnp.workerTrailList[0][0] = convertName(ELayerRate.TUNNEL.getValue(), tunnel.getTunnelId(), 0);
		tnp.protectionTrail = new NameAndStringValue_T[1][3];
		tnp.protectionTrail[0] = convertName(ELayerRate.TUNNEL.getValue(), tunnel.getProtectTunnel().getTunnelId(), 0);
		tnp.additionalInfo = new NameAndStringValue_T[1]; 
		tnp.additionalInfo[0] = new NameAndStringValue_T("ProtectType", EProtectionGroupType.PGP_1_FOR_N.toString()+"");
	}



	/**
	 * 删除TNP成功后给相应的出参赋值
	 * @param tunnel 删除的相应TNP信息
	 * @param tnpName deleteTNP.value的name值
	 * @param deletedPGList 删除的保护组
	 * @param additionalInfo 给附加信息赋值 暂时没有先赋值为0
	 * @throws ProcessingFailureException 
	 */
	public void setDeletePGsInfo(Tunnel tunnel,ProtectionGroupList_THolder deletedPGList, NVSList_THolder additionalInfo) throws ProcessingFailureException {
		additionalInfo.value = new NameAndStringValue_T[0];
		deletedPGList.value = new ProtectionGroup_T[1];
		deletedPGList.value[0] = new ProtectionGroup_T();
		deletedPGList.value[0].rate = (short) ELayerRate.TUNNEL.getValue();
		deletedPGList.value[0].name = new NameAndStringValue_T[3];
		deletedPGList.value[0].name = super.convertName(ELayerRate.TNP.getValue(), tunnel.getTunnelId(), 0);
		deletedPGList.value[0].nativeEMSName = tunnel.getTunnelName();
		deletedPGList.value[0].userLabel = tunnel.getTunnelName();
		deletedPGList.value[0].owner = super.getOwner();
		deletedPGList.value[0].protectionSchemeState = ProtectionSchemeState_T.PSS_UNKNOWN;
		if(null == deletedPGList.value[0].pgpTPList){
			deletedPGList.value[0].pgpTPList = new NameAndStringValue_T[2][3];
		}
		deletedPGList.value[0].pgpTPList[0][0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		deletedPGList.value[0].pgpTPList[0][1] = new NameAndStringValue_T("ManagedElement", tunnel.getTunnelId()+"");
		deletedPGList.value[0].pgpTPList[0][2] = new NameAndStringValue_T("PTP", tunnel.getTunnelId()+"");
		deletedPGList.value[0].pgpTPList[1][0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		deletedPGList.value[0].pgpTPList[1][1] = new NameAndStringValue_T("ManagedElement", tunnel.getProtectTunnelId()+"");
		deletedPGList.value[0].pgpTPList[1][2] = new NameAndStringValue_T("PTP", tunnel.getProtectTunnelId()+"");
		if(null == deletedPGList.value[0].pgpParameters){
			deletedPGList.value[0].pgpParameters = new NameAndStringValue_T[2];
		}
		deletedPGList.value[0].pgpParameters[0] =  new NameAndStringValue_T("WaitTime", tunnel.getWaittime()+"");
		deletedPGList.value[0].pgpParameters[1] =  new NameAndStringValue_T("Delaytime", tunnel.getDelaytime()+"");
		deletedPGList.value[0].additionalInfo = new NameAndStringValue_T[0];
		deletedPGList.value[0].reversionMode = ReversionMode_T.from_int(tunnel.getIsReverse()+1); 
		
	}

	/**
	 * tnp人工倒换信息
	 * @param tnpName 倒换对象标识符
	 * @param tunnel 倒换的tunnel对象
	 * @param tnpSwitchData 倒换信息
	 * @param result 倒换状态结果
	 * @throws ProcessingFailureException 
	 */
	public void settnpSwitchData(NameAndStringValue_T[] tnpName, Tunnel tunnel,
			TNPSwitchData_THolder tnpSwitchData,String result) throws ProcessingFailureException {
		tnpSwitchData.value = new TNPSwitchData_T();
		tnpSwitchData.value.tnpProtectionType = ProtectionGroupType_T.PGT_MSP_1_FOR_N.toString();
		tnpSwitchData.value.tnpSwitchReason = SwitchReason_T.SR_MANUAL.toString();
		tnpSwitchData.value.layerRate = (short) ELayerRate.TUNNEL.getValue();
		tnpSwitchData.value.tnpName = tnpName;
		tnpSwitchData.value.protectionGroupAName = super.convertName(ELayerRate.TNP.getValue(), tunnel.getTunnelId(), tunnel.getASiteId());
		tnpSwitchData.value.protectionGroupZName = super.convertName(ELayerRate.TNP.getValue(), tunnel.getTunnelId(), tunnel.getZSiteId());
		
		tnpSwitchData.value.workerTrailList = new NameAndStringValue_T[1][1][3];
		tnpSwitchData.value.workerTrailList[0][0] = convertName(ELayerRate.TUNNEL.getValue(), tunnel.getTunnelId(), 0);
		tnpSwitchData.value.protectionTrail = new NameAndStringValue_T[1][3];
		tnpSwitchData.value.protectionTrail[0] = convertName(ELayerRate.TUNNEL.getValue(), tunnel.getProtectTunnel().getTunnelId(), 0);
		tnpSwitchData.value.additionalInfo = new NameAndStringValue_T[1];
		tnpSwitchData.value.additionalInfo[0] = new NameAndStringValue_T("switchStatus",result);
	}

	/**
	 * TNP创建对象转换
	 * @param tnpCreateData		1:1Tunnel的corba对象
	 * @param session 
	 * @return
	 * @throws ProcessingFailureException
	 */
	public Tunnel ConvrtCreateTNP(TrailNtwProtCreateData_T tnpCreateData, ICorbaSession session) throws ProcessingFailureException {
		Tunnel tunnel = null;
		Tunnel protectTunnel = null;
		VerifyNameUtil verifyNameUtil;
		String time;
		try {
			time = DateUtil.getDate(DateUtil.FULLTIME);
			verifyNameUtil = new VerifyNameUtil();
			protectTunnel = new Tunnel();
			tunnel = new Tunnel();
			if(verifyNameUtil.verifyName(EServiceType.TUNNEL.getValue(), tnpCreateData.modifiableAttributes[0].userLabel, null))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"UserLabel already exists.");
			
			if(tnpCreateData.modifiableAttributes.length != 2 )
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			//工作tunnel
			convrtCreateTunnel(tnpCreateData.modifiableAttributes[0],tunnel,time,session.getUserName(),"work");
			//保护Tunnel
			convrtCreateTunnel(tnpCreateData.modifiableAttributes[1],protectTunnel,time,session.getUserName(),"protect");
			tunnel.setProtectTunnel(protectTunnel);
		}catch (ProcessingFailureException e) {
			throw e;
		}catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"ConvrtCreateTNP ex");
		}
		return tunnel;
	}

	/**
	 * 转换tunnel对象
	 * @param trailNtwProtModifyData_T corba路由对象
	 * @param tunnel	tunnel路由对象
	 * @param string	类型
	 * @throws ProcessingFailureException 
	 */
	private void convrtCreateTunnel(TrailNtwProtModifyData_T trailNtwProtModifyData_T, Tunnel tunnel,String time,String creater,String type) throws ProcessingFailureException {
		int activeStatus=2;
		String waitTime;
		String delayTime;
		CorbaServiceConvrtTool corbaServiceConvrtTool;
		try {
			if(trailNtwProtModifyData_T.pgATPList.length != 1||trailNtwProtModifyData_T.pgZTPList.length !=1)
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			
			corbaServiceConvrtTool = new CorbaServiceConvrtTool();
			//工作tunnel
			if("work".equals(type)){
				tunnel.setTunnelName(trailNtwProtModifyData_T.userLabel);
				tunnel.setTunnelType(UiUtil.getCodeByValue("PROTECTTYPE", "2").getId()+"");// 1:1类型
				// 附加信息
				activeStatus = Integer.parseInt(super.getValueByKey(trailNtwProtModifyData_T.additionalInfo, super.ISACTIVE));
				if(!(EActiveStatus.ACTIVITY.getValue()==activeStatus||EActiveStatus.UNACTIVITY.getValue()==activeStatus))
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
				tunnel.setTunnelStatus(activeStatus);// 激活状态 1 为激活，2 未激活
			}else{
				tunnel.setTunnelName(trailNtwProtModifyData_T.userLabel+"_protect");
				tunnel.setTunnelType("0");//保护路径
			}
			tunnel.setASiteId(Integer.parseInt(super.getValueByKey(trailNtwProtModifyData_T.pgATPList[0], super.MANAGEELEMENT)));//A端网元
			tunnel.setAPortId(Integer.parseInt(super.getValueByKey(trailNtwProtModifyData_T.pgATPList[0], super.PTP,tunnel.getASiteId())));//A 端端口
			tunnel.setZSiteId(Integer.parseInt(super.getValueByKey(trailNtwProtModifyData_T.pgZTPList[0], super.MANAGEELEMENT)));
			tunnel.setZPortId(Integer.parseInt(super.getValueByKey(trailNtwProtModifyData_T.pgZTPList[0], super.PTP,tunnel.getZSiteId())));
			
		
			tunnel.setQosList(super.converQosList(trailNtwProtModifyData_T.additionalInfo));
			tunnel.setCreateTime(time);//创建时间
			tunnel.setCreateUser(creater);
			//获取LSP信息
			if(0 == trailNtwProtModifyData_T.route.length)
				trailNtwProtModifyData_T.route = new CrossConnect_T[0];
			tunnel.setLspParticularList(corbaServiceConvrtTool.convertLspToRoute(tunnel, 
					Integer.parseInt(super.getValueByKey(trailNtwProtModifyData_T.additionalInfo, this.SRCINLABEL)), 
					Integer.parseInt(super.getValueByKey(trailNtwProtModifyData_T.additionalInfo, this.SRCOUTLABEL)), 
					Integer.parseInt(super.getValueByKey(trailNtwProtModifyData_T.additionalInfo, this.DESTINLABEL)),
					Integer.parseInt(super.getValueByKey(trailNtwProtModifyData_T.additionalInfo, this.DESTOUTLABEL)), trailNtwProtModifyData_T.route));
			waitTime = super.getValueByKey(trailNtwProtModifyData_T.tnpParameters, "WaitTime");
			delayTime = super.getValueByKey(trailNtwProtModifyData_T.tnpParameters, "DelayTime");
			if(UiUtil.isNull(waitTime))
				tunnel.setWaittime(Integer.parseInt(waitTime));
			if(UiUtil.isNull(delayTime))
				tunnel.setDelaytime(Integer.parseInt(delayTime));
			
		}catch (ProcessingFailureException e) {
			throw e;
		}catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"convrtCreateTunnel ex");
		}
	}

	/**
	 * 获取保护模式 和 位置
	 * @param rotateOrder	倒换命令 参照ERotateType
	 * @param type	模式 或 位置
	 * @return
	 */
	private String getRotateModelAndPosition(int rotateOrder ,String type){
		String result = "UNKNOW";
		String model = "UNKNOW"; //1=强制 2=人工 3=练习
		String position = "UNKNOW";	//1=工作侧 2=保护侧
		if(ERotateType.FORCESWORK.getValue() == rotateOrder){//强制倒换到工作侧
			model = ProtectionCommand_T.PC_FORCED_SWITCH.toString();
			position = ESwitchDirection.WORK.toString();
		}else if(ERotateType.FORCESPRO.getValue() == rotateOrder){//强制倒换到保护侧
			model = ProtectionCommand_T.PC_FORCED_SWITCH.toString();
			position = ESwitchDirection.PROTECT.toString();
		}else if(ERotateType.MANUALWORK.getValue() == rotateOrder){//人工倒换到工作侧
			model = ProtectionCommand_T.PC_MANUAL_SWITCH.toString();
			position = ESwitchDirection.WORK.toString();
		}else if(ERotateType.MANUALPRO.getValue() == rotateOrder){//人工倒换到保护侧
			model = ProtectionCommand_T.PC_MANUAL_SWITCH.toString();
			position = ESwitchDirection.PROTECT.toString();
		}else if(ERotateType.CLEAR.getValue() == rotateOrder){//清除
			model = ProtectionCommand_T.PC_CLEAR.toString();
			position = ESwitchDirection.WORK.toString();
		}else if(ERotateType.LOCK.getValue() == rotateOrder){//锁定
			model = ProtectionCommand_T.PC_LOCKOUT.toString();
			position = ESwitchDirection.WORK.toString();
		}else if(ERotateType.PRACTICEJOB.getValue() == rotateOrder){//人工倒换到工作侧
			model = ProtectionCommand_T.PC_EXERCISER.toString();
			position = ESwitchDirection.WORK.toString();
		}else if(ERotateType.PRACTICEPRO.getValue() == rotateOrder){//人工倒换到保护侧
			model = ProtectionCommand_T.PC_EXERCISER.toString();
			position = ESwitchDirection.PROTECT.toString();
		}else{
			model = ProtectionCommand_T.PC_FORCED_SWITCH.toString();
			position = ESwitchDirection.WORK.toString();
		}
		if("Model".equals(type)){
			result =  model;
		}else if("Position".equals(type)){
			result = position;
		}
		return result;
	}
	
}
