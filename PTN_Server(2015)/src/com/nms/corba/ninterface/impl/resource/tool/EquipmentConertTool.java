package com.nms.corba.ninterface.impl.resource.tool;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.nms.corba.ninterface.enums.EEquipmentType;
import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.model.alarm.CurAlarmService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.util.EquimentDataUtil;
import com.nms.ui.manager.xmlbean.CardXml;

import equipment.EquipmentHolder_T;
import equipment.EquipmentOrHolder_T;
import equipment.Equipment_T;
import equipment.HolderState_T;
import equipment.ServiceState_T;
import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

/**
 * 获取设备信息转换类
 * @author dzy
 *
 */
public class EquipmentConertTool extends CorbaConvertBase{
	
	/**
	 * 转换设备信息集合
	 * @param cardList	板卡集合
	 * @param eqList	设备信息集合
	 * @param type		0:查询网元下或者机架下 1：查询子架下 2：查询槽位下
	 * @throws Exception 
	 */
	public void convertEquipmentList2Corba(
			int neId ,Map<Integer, SlotInst> slotInstMap, Map<Integer,CardInst> cardMap, EquipmentOrHolder_T[] eqList,int type) throws Exception {
		int i = 0;
		for (Entry<Integer, SlotInst> slot : slotInstMap.entrySet()) {//槽位
			eqList[i] = new EquipmentOrHolder_T();
			eqList[i].holder(new EquipmentHolder_T());
			convertEquipmentHolder_T(slot.getValue(), cardMap, eqList[i].holder());
			i++;
		}
		for (Entry<Integer, CardInst> card : cardMap.entrySet()) {//设备
			eqList[i] = new EquipmentOrHolder_T();
			eqList[i].equip(new Equipment_T());
			convertEquipment_T(card.getValue(), slotInstMap.get(card.getKey()), eqList[i].equip());
			i++;
		}
		if(type == 0){//机架
			eqList[i] = new EquipmentOrHolder_T();
			eqList[i].holder(new EquipmentHolder_T());
			convertRack2Corba(neId,eqList[i].holder());
			i++;
		}		
		if(type <= 1){//子架
			eqList[i] = new EquipmentOrHolder_T();
			eqList[i].holder(new EquipmentHolder_T());
			convertShelf2Corba(neId,eqList[i].holder());
		}
	}
	
	/**
	 * 子架
	 * @param neId	网元
	 * @param hold	corba设备对象
	 * @throws ProcessingFailureException
	 */
	public void convertShelf2Corba(int neId,EquipmentHolder_T hold) throws ProcessingFailureException {
		try {
			//网元友好名称
			hold.name = new NameAndStringValue_T[3];
			hold.name[0] = new NameAndStringValue_T(this.EMS, CorbaConfig.getInstanse().getCorbaEmsName());
			hold.name[1] = new NameAndStringValue_T(this.MANAGEELEMENT, neId+"");;
			hold.name[2] = new NameAndStringValue_T(super.EQUIPMENTHOLDER, "/rack=1/shelf=1");
			//设备友好名称
			hold.userLabel = "";
			//设备EMS本地名称
			hold.nativeEMSName = "";
			//拥有者
			hold.owner = CorbaConfig.getInstanse().getEmsVendorName();
			
			//告警上报提示
			hold.alarmReportingIndicator = false;
			//支持的类型包括：RACK / SHELF / SLOT三种
			hold.holderType = "Shelf";
			//EquipmentHolder的状态，仅对slot有效，对其它类型此属性值没有意义
			hold.holderState = HolderState_T.UNKNOWN;
			hold.acceptableEquipmentTypeList = new String[0];
			hold.expectedOrInstalledEquipment = new NameAndStringValue_T[0]; 
			hold.additionalInfo =  new NameAndStringValue_T[4]; 
			//单元盘序号
			hold.additionalInfo[0] = new NameAndStringValue_T("Sequence", "1");
			hold.additionalInfo[1] = new NameAndStringValue_T("Version", "1");
			hold.additionalInfo[2] = new NameAndStringValue_T("ManagedElement", neId+"");
			hold.additionalInfo[3] = new NameAndStringValue_T("PartNumber", "1");
		
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"convertShelf2Corba ex.");
		}
		
	}

	/**
	 * 机架
	 * @param neId	网元
	 * @param hold	corba设备对象
	 * @throws ProcessingFailureException 
	*/
	public void convertRack2Corba(int neId,EquipmentHolder_T hold) throws ProcessingFailureException {

		try {
			//网元友好名称
			hold.name = new NameAndStringValue_T[3];
			hold.name[0] = new NameAndStringValue_T(this.EMS, CorbaConfig.getInstanse().getCorbaEmsName());
			hold.name[1] = new NameAndStringValue_T(this.MANAGEELEMENT, neId+"");;
			hold.name[2] = new NameAndStringValue_T(super.EQUIPMENTHOLDER, "/rack=1");
			//设备友好名称
			hold.userLabel = "";
			//设备EMS本地名称
			hold.nativeEMSName = "";
			//拥有者
			hold.owner = CorbaConfig.getInstanse().getEmsVendorName();
			
			//告警上报提示
			hold.alarmReportingIndicator = false;
			//支持的类型包括：RACK / SHELF / SLOT三种
			hold.holderType = "Rack";
			//EquipmentHolder的状态，仅对slot有效，对其它类型此属性值没有意义
			hold.holderState = HolderState_T.UNKNOWN;
			hold.acceptableEquipmentTypeList = new String[0];
			hold.expectedOrInstalledEquipment = new NameAndStringValue_T[0];
			hold.additionalInfo =  new NameAndStringValue_T[4]; 
			//单元盘序号
			hold.additionalInfo[0] = new NameAndStringValue_T("Sequence", "1");
			hold.additionalInfo[1] = new NameAndStringValue_T("Version", "1");
			hold.additionalInfo[2] = new NameAndStringValue_T("ManagedElement", neId+"");
			hold.additionalInfo[3] = new NameAndStringValue_T("PartNumber", "1");
		
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"convertShelf2Corba ex.");
		}
		
	
	}


	/**
	  * 转换容器信息
	  * @param slotInst		槽位
	  * @param cardMap		板卡集合
	  * @param equipment	corba设备信息
	  * @throws ProcessingFailureException 
	  */
	public void convertEquipmentHolder_T(SlotInst slotInst, Map<Integer,CardInst> cardMap, 
			EquipmentHolder_T hold) throws ProcessingFailureException{
		CardInst cardInst = null;
		try {
			/*..................设备容器信息...............................*/
			if(null != cardMap)
				cardInst = cardMap.get(slotInst.getId());
			
			EquimentDataUtil equimentDataUtil = new EquimentDataUtil();
			//网元友好名称
			hold.name = new NameAndStringValue_T[3];
			hold.name[0] = new NameAndStringValue_T(this.EMS, CorbaConfig.getInstanse().getCorbaEmsName());
			hold.name[1] = new NameAndStringValue_T(this.MANAGEELEMENT, slotInst.getSiteId()+"");;
			hold.name[2] = new NameAndStringValue_T(super.EQUIPMENTHOLDER, "/rack=1/shelf=1/slot="+slotInst.getNumber()+"");
			//友好名称
			hold.userLabel = "";
			//本地名称
			hold.nativeEMSName = "";
			//拥有者
			hold.owner = CorbaConfig.getInstanse().getEmsVendorName();
			//告警上报提示
			hold.alarmReportingIndicator = true;
			//支持的类型包括：RACK / SHELF / SLOT三种
			hold.holderType = "SLOT";
			//安装或配置的单板名称，仅对slot有效
			if(null != cardInst){
				hold.expectedOrInstalledEquipment = new NameAndStringValue_T[1];
				hold.expectedOrInstalledEquipment[0] = new NameAndStringValue_T("cardType",cardInst.getCardName()+"");
			}else{
				hold.expectedOrInstalledEquipment = new NameAndStringValue_T[0];
			}
			//支持的单板类型列表，仅对slot有效，对其它类型此属性没有意义
			List<CardXml> cardXmlList = equimentDataUtil.getCardMenu(slotInst.getSlotType());
			if(null != cardXmlList && cardXmlList.size()>0){
				hold.acceptableEquipmentTypeList = new String[cardXmlList.size()];
				for (int i = 0; i < cardXmlList.size(); i++) {
					hold.acceptableEquipmentTypeList[i] = cardXmlList.get(i).getName();
				}
			}else{
				hold.acceptableEquipmentTypeList = new String[0];
			}
			
			//EquipmentHolder的状态，仅对slot有效，对其它类型此属性值没有意义
			if(null != cardInst){
				hold.holderState = HolderState_T.INSTALLED_AND_EXPECTED;
			}else{
				hold.holderState = HolderState_T.EXPECTED_AND_NOT_INSTALLED;
			}
			hold.additionalInfo =  new NameAndStringValue_T[2]; 
			//告警状态
			hold.additionalInfo[0] = new NameAndStringValue_T("AlarmStatus", "Normal");
			//单元盘序号
			hold.additionalInfo[1] = new NameAndStringValue_T("Sequence", slotInst.getNumber()+"");
			
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"convertEquipmentHolder_T ex.");
		}
	}

	/**
	 * 转换设备
	 * @param cardInst	板卡
	 * @param slotInstMap	槽位集合
	 * @param equip		设备
	 * @throws ProcessingFailureException 
	 * @throws Exception
	 */
	public void convertEquipment_T(CardInst cardInst, SlotInst slotInst, Equipment_T equip) throws ProcessingFailureException{
		CurAlarmService_MB curAlarmService = null;
		SiteService_MB siteService = null;
		SiteInst siteInst;
		try {
			curAlarmService = (CurAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CurrentAlarm);
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if(null == slotInst){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
			}
			//设备标识符
			equip.name = new NameAndStringValue_T[4];
			equip.name[0] = new NameAndStringValue_T(this.EMS, CorbaConfig.getInstanse().getCorbaEmsName());
			equip.name[1] = new NameAndStringValue_T(this.MANAGEELEMENT, cardInst.getSiteId()+"");;
			equip.name[2] = new NameAndStringValue_T(super.EQUIPMENTHOLDER, "/rack=1/shelf=1/slot="+slotInst.getNumber()+"");
			equip.name[3] = new NameAndStringValue_T(super.EQUIPMENT, "1");
			
			//设备友好名称
			equip.userLabel = cardInst.getCardName()+"";
			//设备EMS本地名称
			equip.nativeEMSName = cardInst.getCardName();
			//拥有者
			equip.owner = CorbaConfig.getInstanse().getEmsVendorName();
			
			//附加信息
			equip.additionalInfo =  new NameAndStringValue_T[9];
			//单元盘所属机槽
			equip.additionalInfo[0] = new NameAndStringValue_T("Slot",slotInst.getNumber()+"");
			//是否有保护
			equip.additionalInfo[1] = new NameAndStringValue_T("IsProtected","2");//1=保护,2=没有保护
			//保护方式
			equip.additionalInfo[2] = new NameAndStringValue_T("ProtectionType","UNKNOWN");
			//单元盘使用状态（可用，不可用）
			equip.additionalInfo[3] = new NameAndStringValue_T("Useable","1");//1=可用,2=不可用
			//单元盘告警状态（当前最高告警级别）
			equip.additionalInfo[4] = new NameAndStringValue_T("TopAlarmLevel",CorbaAlarmTool.convertPerceivedSeverity2Corba(curAlarmService.queryTopAlarm(slotInst)).toString());
			//单元盘硬件版本
			equip.additionalInfo[5] = new NameAndStringValue_T("Hardware","");
			
			equip.additionalInfo[6] = new NameAndStringValue_T("OccupySlotNum","1");
			equip.additionalInfo[7] = new NameAndStringValue_T("Sequence",slotInst.getNumber()+"");
			
			//单元盘类型（例如：接口盘、交叉/交换盘、主控盘、时钟盘、电源盘等）
			if("MCU1".equals(cardInst.getCardName().toUpperCase())){		//主控盘
				equip.additionalInfo[8] = new NameAndStringValue_T("ClassType",EEquipmentType.MAINCONTROLERBOARD.toString()+"");
			}else if("GET1".equals(cardInst.getCardName().toUpperCase())||	//接口盘
					"E1T1".equals(cardInst.getCardName().toUpperCase())||
					"GEX1".equals(cardInst.getCardName().toUpperCase())){
				equip.additionalInfo[8] = new NameAndStringValue_T("ClassType",EEquipmentType.INTERFACEDISH.toString()+"");
			}else if("FAN".equals(cardInst.getCardName().toUpperCase())){		//风扇盘
				equip.additionalInfo[8] = new NameAndStringValue_T("ClassType",EEquipmentType.FANBOARD.toString()+"");
			}else if("PWR".equals(cardInst.getCardName().toUpperCase())){		//电源盘
				equip.additionalInfo[8] = new NameAndStringValue_T("ClassType",EEquipmentType.POWERDISH.toString()+"");
			}else{
				equip.additionalInfo[8] = new NameAndStringValue_T("ClassType",EEquipmentType.UNKNOW.toString()+"");
			}
			
			//告警上报提示
			equip.alarmReportingIndicator = true;
			//服务状态
			equip.serviceState = ServiceState_T.IN_SERVICE;	//插入状态
			//网管上配置的单板类型
			equip.expectedEquipmentObjectType = cardInst.getCardName();
			//网元上实际所插的单板类型
			equip.installedEquipmentObjectType = cardInst.getCardName();
			//实际安装的单板的PN
			equip.installedPartNumber = slotInst.getNumber()+"";
			//实际安装的单板的版本号
			siteInst = siteService.select(cardInst.getSiteId());
			if(null != siteInst && UiUtil.isNull(siteInst.getVersions())){
				equip.installedVersion = siteInst.getVersions();
			}else{
				equip.installedVersion = "";
			}
			//单元盘序列号
			equip.installedSerialNumber = "";
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"convertEquipment_T ex.");
		} finally {
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(curAlarmService);
		}
		
	}

}
