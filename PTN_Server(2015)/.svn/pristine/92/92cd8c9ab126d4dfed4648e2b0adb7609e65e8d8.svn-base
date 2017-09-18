package com.nms.corba.ninterface.impl.protection.tool;

import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.Iterator;
import java.util.List;

import protection.ProtectionGroup_T;
import protection.ProtectionSchemeState_T;
import protection.ProtectionType_T;
import protection.ReversionMode_T;
import protection.SwitchData_T;
import protection.SwitchData_THolder;
import protection.SwitchReason_T;

import com.nms.corba.ninterface.enums.EOAMType;
import com.nms.corba.ninterface.enums.EProtectionGroupType;
import com.nms.corba.ninterface.enums.EProtectionType;
import com.nms.corba.ninterface.enums.ERotateStatus;
import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.ptn.path.protect.Protect_Corba;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.ERotateType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.ui.manager.UiUtil;

/**
 * 保护组转换类
 */
public class CorbaProtectTool extends CorbaConvertBase {

	/**
	 * 数据转换
	 * @param Protect_CorbaList 保护组数据集合
	 * @param meList
	 * @throws Exception 
	 */
	public void protectConvertList(
			List<Protect_Corba> Protect_CorbaList, ProtectionGroup_T[] pgpList) throws Exception {
		int i = 0;
		for(Iterator<Protect_Corba> iter = Protect_CorbaList.iterator(); iter.hasNext();){
			pgpList[i] = new ProtectionGroup_T();
			convertProtectDataX2Corba((Protect_Corba)iter.next(), pgpList[i]);
			i++;
		}
	}
	
	/**
	 * 数据转换
	 * @param siteInst
	 * @param me
	 * @throws Exception 
	 */
	public void convertProtectDataX2Corba(
			Protect_Corba protect, ProtectionGroup_T proGroup) throws Exception {
		int i = 5;
		proGroup.name = new NameAndStringValue_T[3];
		proGroup.name[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		proGroup.name[1] = new NameAndStringValue_T("ManagedElement", protect.getSiteId()+"");
		proGroup.name[2] = new NameAndStringValue_T("PGP", EProtectionType.valueOf(protect.getObjType()).getValue()+"/"+ protect.getId()+"");
		proGroup.nativeEMSName = protect.getName();
		proGroup.userLabel = protect.getName();
		proGroup.owner = super.getOwner();
		proGroup.protectionSchemeState = ProtectionSchemeState_T.PSS_UNKNOWN;
		proGroup.reversionMode = ReversionMode_T.RM_UNKNOWN;
		if(!EProtectionType.LOOPPROTECT.toString().equals(protect.getObjType())){
			if(protect.getReversionMode()==1)//1=可回恢复
				proGroup.reversionMode = ReversionMode_T.RM_REVERTIVE;
			if(protect.getReversionMode()==0)//2=不可恢复
				proGroup.reversionMode = ReversionMode_T.RM_NON_REVERTIVE;
		}
		
		proGroup.rate = (short) ELayerRate.PGP.getValue();
		proGroup.pgpTPList = new NameAndStringValue_T[2][3];
		proGroup.pgpTPList[0] = super.convertName(ELayerRate.PORT.getValue(), protect.getPortId_work(), protect.getSiteId());
		proGroup.pgpTPList[1] = super.convertName(ELayerRate.PORT.getValue(), protect.getPortId_pro(), protect.getSiteId());

		proGroup.pgpParameters = new NameAndStringValue_T[2];
		proGroup.pgpParameters[0] =  new NameAndStringValue_T("WaitTime", protect.getWaitTime()+"");
		proGroup.pgpParameters[1] =  new NameAndStringValue_T("DelayTime", protect.getDelaytime()+"");
		if(EProtectionType.valueOf(protect.getObjType()).getValue() == EProtectionType.TUNNEL.getValue()){
			i++;
			i++;
		}
		if(0!=protect.getSfApsenable())
			i++;
		if(0!=protect.getSdApsenable())
			i++;
		proGroup.additionalInfo = new NameAndStringValue_T[i];
		i=0;
		proGroup.additionalInfo[i++] = new NameAndStringValue_T("ProtectionType", protect.getObjType());
		proGroup.additionalInfo[i] = new NameAndStringValue_T("ProtectionGroupType", EProtectionGroupType.PGP_1_FOR_N.toString());
		if(UiUtil.getCodeByValue("MSPPROTECTTYPE","1").getId() == protect.getProtectType())
			proGroup.additionalInfo[i] = new NameAndStringValue_T("ProtectionGroupType", EProtectionGroupType.PGP_1_PLUS_1.toString());
		proGroup.additionalInfo[++i] =  new NameAndStringValue_T("ManagedElement", protect.getSiteId()+"");
		proGroup.additionalInfo[++i] = new NameAndStringValue_T("RotateStatus", getRotateStatus(protect.getBeRotated()));
		proGroup.additionalInfo[++i] = new NameAndStringValue_T("Apsenable", protect.getApsenable() == 1 ?"Enabled" :"Disabled");
		
		if(0!=protect.getSfApsenable())
			proGroup.additionalInfo[++i] = new NameAndStringValue_T("SfApsenable", UiUtil.getCodeById(protect.getSfApsenable()).getCodeName()); 
		if(0!=protect.getSdApsenable())
			proGroup.additionalInfo[++i] = new NameAndStringValue_T("SdApsenable", UiUtil.getCodeById(protect.getSdApsenable()).getCodeName());  
		
		if(EProtectionType.valueOf(protect.getObjType()).getValue() == EProtectionType.TUNNEL.getValue()){
			Tunnel tunnel = new Tunnel();
			proGroup.additionalInfo[++i] = super.getMegId(protect.getId(), protect.getSiteId(), EOAMType.TUNNEL.toString(), "AMEG", OamTypeEnum.MEP.getValue());
			tunnel.setProtectTunnelId(protect.getId());
			proGroup.additionalInfo[++i] = super.getMegId(tunnel.getTunnelId(), tunnel.getASiteId(), EOAMType.TUNNEL.toString(), "AMEG_Pro", OamTypeEnum.MEP.getValue());
		}
	}
	
	/**
	 * 获取ERotateStatus 倒换状态
	 * @param rotateOrder	ERotateType倒换类型
	 * @return
	 */
	private String getRotateStatus(int rotateOrder) {
		ERotateStatus eRotateStatus = ERotateStatus.IDLE;
		if(ERotateType.FORCESWORK.getValue() == rotateOrder||ERotateType.MANUALWORK.getValue() == rotateOrder||
				ERotateType.PRACTICEJOB.getValue() == rotateOrder || rotateOrder == -1){//强制倒换到工作侧
			eRotateStatus = ERotateStatus.IDLE;
		}else if(ERotateType.FORCESPRO.getValue() == rotateOrder||ERotateType.MANUALPRO.getValue() == rotateOrder||
				ERotateType.PRACTICEPRO.getValue() == rotateOrder){//强制倒换到保护侧
			eRotateStatus = ERotateStatus.ROTATED;
		}
		return eRotateStatus.toString();
	}

	/**
	 * 给倒换信息赋值
	 * @param switchData 倒换信息
	 * @param id 保护组标示中的id
	 * @param workId 工作侧id
	 * @param protectId 保护侧id
	 * @param isTorM 0 为tunnel 1为msp
	 * @param result 倒换执行结果
	 * @throws ProcessingFailureException 
	 */
	public void setSwitchData(int workId,int protectId,SwitchData_THolder switchData, int id,int isTorM,String result) throws ProcessingFailureException {
		switchData.value = new SwitchData_T();
		if (isTorM == 0) {
			switchData.value.layerRate = (short)ELayerRate.TUNNEL.getValue();//层速率
		}else {
			switchData.value.layerRate = (short)ELayerRate.MSP.getValue();//层速率
		}
		switchData.value.groupName = new NameAndStringValue_T[3];// 保护组名称
		switchData.value.groupName[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		switchData.value.groupName[1] = new NameAndStringValue_T("ManagedElement", ""+id);
		switchData.value.groupName[2] = new NameAndStringValue_T("PGP", ELayerRate.PGP.getValue()+"/"+id);
		switchData.value.switchReason = SwitchReason_T.from_int(6);//倒换原因
		switchData.value.protectedTP = new NameAndStringValue_T[3];//被保护的终端点标识符
		switchData.value.protectedTP = super.convertName(ELayerRate.PORT.getValue(), id, workId);
		switchData.value.switchToTP = new NameAndStringValue_T[3];//用来保护的终端点标识符
		switchData.value.switchToTP = super.convertName(ELayerRate.PORT.getValue(), id, protectId);
		switchData.value.protectionType = ProtectionType_T.PT_MSP_APS;
		switchData.value.additionalInfo = new NameAndStringValue_T[1];
		switchData.value.additionalInfo[0] = new NameAndStringValue_T("switchStatus",result);
		
	}

}
