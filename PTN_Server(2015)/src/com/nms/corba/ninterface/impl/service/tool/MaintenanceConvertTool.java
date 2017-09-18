package com.nms.corba.ninterface.impl.service.tool;

import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.List;

import maintenanceOps.MEGCreateData_T;
import maintenanceOps.MEG_T;
import subnetworkConnection.TPData_T;
import terminationPoint.TerminationMode_T;
import transmissionParameters.LayeredParameters_T;

import com.nms.corba.ninterface.enums.EOAMType;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.oam.OamEthernetInfo;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.bean.ptn.oam.OamMipInfo;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.UiUtil;

/**
 * oam转换类
 * @author dzy
 *
 */
public class MaintenanceConvertTool extends CorbaConvertBase{
	
	/**
	 * 转换oam对象
	 * @param oamInfo
	 * @param value
	 * @param type
	 * @throws Exception 
	 */
	public void convertOAM2Corba(OamInfo oamInfo, MEG_T meg, int type) throws Exception {
		if(EOAMType.MEP.getValue() == type){
			convertOAMMEP2Corba(oamInfo, meg);
		}else if(EOAMType.MIP.getValue() == type){
			convertOAMMIP2Corba(oamInfo, meg);
		}
	}
	

	/**
	 * 转换OAMMip为CORBA 对象
	 * @param oamInfo	oam对象
	 * @param meg	corba对象
	 * @throws Exception 
	 */
	private void convertOAMMIP2Corba(OamInfo oamInfo, MEG_T meg) throws Exception {
		try {
			meg.name = super.convertMEGName(oamInfo.getOamMep().getId(), oamInfo.getOamMep().getSiteId(), EOAMType.TUNNEL.toString());;
			meg.userLabel = "";
			meg.nativeEMSName = "";
			meg.owner = super.getOwner();
			meg.level = -1;
			//OAM类型  (1 = 段 , 2 = lsp, 3 = pw, 4=以太网OAM)
			if("SECTION".equals(oamInfo.getOamMip().getObjType())){
				meg.megType = 1;
				meg.relatedObject = super.convertName(ELayerRate.PORT.getValue(),  oamInfo.getOamMip().getServiceId(),  oamInfo.getOamMip().getSiteId());
			}else if("TUNNEL".equals(oamInfo.getOamMip().getObjType())){
				meg.megType = 2;
				meg.relatedObject = super.convertName(ELayerRate.TUNNEL.getValue(),  oamInfo.getOamMip().getServiceId(),  oamInfo.getOamMip().getSiteId());
			}else if("PW".equals(oamInfo.getOamMip().getObjType())){
				meg.megType = 3;
				meg.relatedObject = super.convertName(ELayerRate.PW.getValue(),  oamInfo.getOamMip().getServiceId(),  oamInfo.getOamMip().getSiteId());
			}
			
			meg.additionalInfo = new NameAndStringValue_T[4];
			meg.additionalInfo[0]=new NameAndStringValue_T("MegId", oamInfo.getOamMip().getMegId()+"");
			meg.additionalInfo[1]=new NameAndStringValue_T("MegIcc", oamInfo.getOamMip().getMegIcc()+"");
			meg.additionalInfo[2]=new NameAndStringValue_T("MegUmc", oamInfo.getOamMip().getMegUmc()+"");
			meg.additionalInfo[3]=new NameAndStringValue_T("MIP", oamInfo.getOamMip().getMipId()+"");
			
			meg.tpList = new TPData_T[1];
			meg.tpList[0] = new TPData_T();
			meg.tpList[0].ingressTrafficDescriptorName = new NameAndStringValue_T[1];
			meg.tpList[0].ingressTrafficDescriptorName[0] = new NameAndStringValue_T("ZMId", oamInfo.getOamMip().getZMId()+"");
			meg.tpList[0].egressTrafficDescriptorName = new NameAndStringValue_T[1];
			meg.tpList[0].egressTrafficDescriptorName[0] = new NameAndStringValue_T("AMID", oamInfo.getOamMip().getAMId()+"");
			meg.tpList[0].transmissionParams = new LayeredParameters_T[0];
			meg.tpList[0].tpName = new NameAndStringValue_T[0];
			meg.tpList[0].tpMappingMode = TerminationMode_T.TM_NA;
			
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"convertOAMMIP2Corba ex.");
		}
	}

	/**
	 * 转换OAMMep为CORBA 对象
	 * @param oamInfo	oam对象
	 * @param meg	corba对象
	 * @throws Exception 
	 */
	public void convertOAMMEP2Corba(OamInfo oamInfo, MEG_T meg) throws Exception {
		try {
			meg.name = super.convertMEGName(oamInfo.getOamMep().getId(), oamInfo.getOamMep().getSiteId(), oamInfo.getOamMep().getObjType());
			meg.userLabel = "";
			meg.nativeEMSName = "";
			meg.owner = super.getOwner();
			meg.level = oamInfo.getOamMep().getMel() ;
			//OAM类型  (1 = 段 , 2 = lsp, 3 = pw, 4=以太网OAM)
			if("SECTION".equals(oamInfo.getOamMep().getObjType())){
				meg.megType = 1;
				meg.relatedObject = super.convertName(ELayerRate.PORT.getValue(),  oamInfo.getOamMep().getServiceId(),  oamInfo.getOamMep().getSiteId());
			}else if("TUNNEL".equals(oamInfo.getOamMep().getObjType())){
				meg.megType = 2;
				meg.relatedObject = super.convertName(ELayerRate.TUNNEL.getValue(),  oamInfo.getOamMep().getServiceId(),  oamInfo.getOamMep().getSiteId());
			}else if("PW".equals(oamInfo.getOamMep().getObjType())){
				meg.megType = 3;
				meg.relatedObject = super.convertName(ELayerRate.PW.getValue(),  oamInfo.getOamMep().getServiceId(),  oamInfo.getOamMep().getSiteId());
			}
			
			meg.additionalInfo = new NameAndStringValue_T[4];
			meg.additionalInfo[0]=new NameAndStringValue_T("MegId", oamInfo.getOamMep().getMegId()+"");
			meg.additionalInfo[1]=new NameAndStringValue_T("MegIcc", oamInfo.getOamMep().getMegIcc()+"");
			meg.additionalInfo[2]=new NameAndStringValue_T("MegUmc", oamInfo.getOamMep().getMegUmc()+"");
			meg.additionalInfo[3]=new NameAndStringValue_T("LpbOutTime", oamInfo.getOamMep().getLpbOutTime()+"");
			
			meg.tpList = new TPData_T[1];
			meg.tpList[0] = new TPData_T();
			meg.tpList[0].ingressTrafficDescriptorName = new NameAndStringValue_T[1];
			meg.tpList[0].ingressTrafficDescriptorName[0] = new NameAndStringValue_T("MEP", oamInfo.getOamMep().getRemoteMepId()+"");
			meg.tpList[0].egressTrafficDescriptorName = new NameAndStringValue_T[1];
			meg.tpList[0].egressTrafficDescriptorName[0] = new NameAndStringValue_T("MEP", oamInfo.getOamMep().getLocalMepId()+"");
			meg.tpList[0].transmissionParams = new LayeredParameters_T[0];
			meg.tpList[0].tpName = new NameAndStringValue_T[0];
			meg.tpList[0].tpMappingMode = TerminationMode_T.TM_NA;
			
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"convertOAMMEP2Corba ex.");
		}
	}


	public void conertEthOAM2Corba(OamEthernetInfo oamEthernetInfo, MEG_T meg) throws Exception {
		try {
			meg.name = super.convertMEGName(oamEthernetInfo.getId(), oamEthernetInfo.getSiteId(), EOAMType.ETHERNET.toString());
			meg.userLabel = "";
			meg.nativeEMSName = "";
			meg.owner = super.getOwner();
			meg.level = -1;
			//OAM类型  (1 = 段 , 2 = lsp, 3 = pw, 4=以太网OAM)
			meg.megType = 4;
			meg.relatedObject = super.convertName(ELayerRate.PORT.getValue(),  Integer.parseInt(oamEthernetInfo.getPort()),  oamEthernetInfo.getSiteId());
		
			meg.additionalInfo = new NameAndStringValue_T[2];
			meg.additionalInfo[0]=new NameAndStringValue_T("MdLevel", oamEthernetInfo.getMdLevel()+"");
			meg.additionalInfo[1]=new NameAndStringValue_T("Ma", oamEthernetInfo.getMaName()+"");
			
			meg.tpList = new TPData_T[1];
			meg.tpList[0] = new TPData_T();
			meg.tpList[0].ingressTrafficDescriptorName = new NameAndStringValue_T[1];
			meg.tpList[0].egressTrafficDescriptorName[0] = new NameAndStringValue_T("MEP", oamEthernetInfo.getMepId()+"");
			meg.tpList[0].ingressTrafficDescriptorName = new NameAndStringValue_T[0];
			meg.tpList[0].transmissionParams = new LayeredParameters_T[0];
			meg.tpList[0].tpName = new NameAndStringValue_T[0];
			meg.tpList[0].tpMappingMode = TerminationMode_T.TM_NA;
			
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"convertOAMMEP2Corba ex.");
		}
		
	}

	/**
	 * 转换CORBA对象2Oam
	 * @param paramMEGCreateData_T CORBA Oam对象
	 * @param oamInfo	Oam对象
	 * @throws ProcessingFailureException 
	 */
	public void convertCorba2Oam(MEGCreateData_T paramMEGCreateData_T,
			OamInfo oamInfo) throws ProcessingFailureException {
	    int oamType = 0; //OAM类型  (是属于 0：段/1：lsp/2：pw)
	    OamMepInfo oamMepInfo = null;//两端
		try {
			if(paramMEGCreateData_T.tpList.length!=1 || paramMEGCreateData_T.tpList[0].egressTrafficDescriptorName.length!=1||
					paramMEGCreateData_T.tpList[0].ingressTrafficDescriptorName.length!=1)
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			oamMepInfo = new OamMepInfo();
			oamType = paramMEGCreateData_T.megType;
			oamMepInfo = new OamMepInfo();
			//OAM类型
			if(oamType == 1){
				oamMepInfo.setObjType(EOAMType.SECTION.toString());
			}else if(oamType == 2){
				oamMepInfo.setObjType(EOAMType.TUNNEL.toString());
			}else if(oamType == 3){
				oamMepInfo.setObjType(EOAMType.PW.toString());
			}else if(oamType == 4){
				oamMepInfo.setObjType(EOAMType.ETHERNET.toString());
			}
			
			//mel 等级
			oamMepInfo.setMel(paramMEGCreateData_T.level);
			//本端维护点
			oamMepInfo.setLocalMepId(Integer.parseInt(paramMEGCreateData_T.tpList[0].egressTrafficDescriptorName[0].value));
			//对端维护点
			oamMepInfo.setRemoteMepId(Integer.parseInt(paramMEGCreateData_T.tpList[0].ingressTrafficDescriptorName[0].value));
			
			//serviced 段的主键ID(pw、tunnel 数据库中主键ID)
			if(oamType != 1){
				oamMepInfo.setServiceId(super.getPrimaryKey(paramMEGCreateData_T.relatedObject));
			}else{
				oamMepInfo.setObjId(super.getPrimaryKey(paramMEGCreateData_T.relatedObject));
			}
			for (int i = 0; i < paramMEGCreateData_T.additionalInfo.length; i++) {
				//设置网元ID
				if("ManagedElement".equals(paramMEGCreateData_T.additionalInfo[i].name))
					oamMepInfo.setSiteId(Integer.parseInt(paramMEGCreateData_T.additionalInfo[i].value));
				//mel ID
				else if("MegId".equals(paramMEGCreateData_T.additionalInfo[i].name))
					oamMepInfo.setMegId(Integer.parseInt(paramMEGCreateData_T.additionalInfo[i].value));
				
				//mgeIcc
				else if("MegIcc".equals(paramMEGCreateData_T.additionalInfo[i].name))
					oamMepInfo.setMegIcc(paramMEGCreateData_T.additionalInfo[i].value);
				//mgeumc
				else if("MegUmc".equals(paramMEGCreateData_T.additionalInfo[i].name))
					oamMepInfo.setMegUmc(paramMEGCreateData_T.additionalInfo[i].value);	
				//环回等待时间
				else if("LpbOutTime".equals(paramMEGCreateData_T.additionalInfo[i].name))
					oamMepInfo.setLpbOutTime(Integer.parseInt(paramMEGCreateData_T.additionalInfo[i].value));	
				//tc
				else if("LspTc".equals(paramMEGCreateData_T.additionalInfo[i].name))
					oamMepInfo.setLspTc(Integer.parseInt(paramMEGCreateData_T.additionalInfo[i].value));
			}
			oamInfo.setOamMep(oamMepInfo);
		} catch (NumberFormatException e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"convertCorba2Oam ex.");
		}
	}

	/**
	 * 转换corba 2 以太网OAM
	 * @param paramMEGCreateData_T	corba对象
	 * @param oamInfo	以太网OAM对象
	 * @return 
	 * @throws ProcessingFailureException 
	 */
	public List<OamInfo> convertCorba2ETHOam(MEGCreateData_T paramMEGCreateData_T,
			OamInfo oamInfo) throws ProcessingFailureException {
		OamEthernetInfo oamEthernetInfo;
		PortService_MB portService = null;
		PortInst portInst;
		List<OamInfo> oamInfoList = null;
		try {
			oamEthernetInfo = new OamEthernetInfo();
			oamInfoList = new ArrayList<OamInfo>();
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			//MepId
			oamEthernetInfo.setMepId(paramMEGCreateData_T.tpList[0].egressTrafficDescriptorName[0].value);
			//承载端口
			portInst = portService.selectPortybyid(Integer.parseInt(paramMEGCreateData_T.relatedObject[2].value));
			oamEthernetInfo.setPort(portInst.getNumber() + "");
			oamEthernetInfo.setSlot(portInst.getSlotNumber());
			oamEthernetInfo.setSiteId(portInst.getSiteId());
			
			for (int i = 0; i < paramMEGCreateData_T.additionalInfo.length; i++) {
				// MD Level
				if("DmLevel".endsWith(paramMEGCreateData_T.additionalInfo[i].name))
					oamEthernetInfo.setDmPriority(paramMEGCreateData_T.additionalInfo[i].value);
				// MA Name
				if("MaName".endsWith(paramMEGCreateData_T.additionalInfo[i].name))
					oamEthernetInfo.setMaName(paramMEGCreateData_T.additionalInfo[i].value);
				//MIP端口
				if("MIP".equals(paramMEGCreateData_T.additionalInfo[i].name)){
					portInst = portService.selectPortybyid(Integer.parseInt(paramMEGCreateData_T.additionalInfo[i].value));
					oamEthernetInfo.setMipPort(portInst.getNumber() + "");
					oamEthernetInfo.setMipSlot(portInst.getSlotNumber());
				}
				// MD Name
				if("MdName".endsWith(paramMEGCreateData_T.additionalInfo[i].name))
					oamEthernetInfo.setMaName(paramMEGCreateData_T.additionalInfo[i].value);
			}
			// 以太网OAM 使能
			oamEthernetInfo.setThernetOAMEnabl(0);
			// 默认MD LEVEL
			oamEthernetInfo.setMdMLevel("0");
			// MP属性：
			oamEthernetInfo.setMpLable(0);
			// CCM发送间隔
			oamEthernetInfo.setCcmsend(3);
			// VLAN
			oamEthernetInfo.setVlan(1+"");
			// MEP ID
			oamEthernetInfo.setMepId(1+"");
			// MEP类型
			oamEthernetInfo.setMepType(0);

			// CCM发送使能
			oamEthernetInfo.setCcmSendEnable(0);
			// CCM接受使能
			oamEthernetInfo.setCcmReceiveEnable(0);
			// CCM优先级
			oamEthernetInfo.setCcmPriority("0");
			// LBM数据TLV类型
			oamEthernetInfo.setLbmTlvType(0);
			// LBM数据TLV长度
			oamEthernetInfo.setLbmTlvTypeLength("41");
			// LBM优先级
			oamEthernetInfo.setLbmPriority("0");
			// LBM丢弃适用性
			oamEthernetInfo.setLbmDiscard(0);
			// LTM优先级
			oamEthernetInfo.setLtmPriority("0");
			// AIS发送使能
			oamEthernetInfo.setAisSendEnabel(0);
			// 客户MD Level
			oamEthernetInfo.setClientMdLevel("0");
			// AIS优先级
			oamEthernetInfo.setAisPriority("0");
			// LCK发送使能
			oamEthernetInfo.setLckSendEnabel(0);
			// LCK优先级
			oamEthernetInfo.setLckPriority("0");
			// AIS/LCK发送周期
			oamEthernetInfo.setAisLckSendCycle(0);
			// TST发送使能
			oamEthernetInfo.setTstSendEnabel(0);
			// TST发送MDLevel
			oamEthernetInfo.setTstSendLevel("0");
			// TST目的MEP MAC
			oamEthernetInfo.setTstPurposeMepMac("00-00-00-00-00-01");
			// TST优先级
			oamEthernetInfo.setTstPriority("0");
			// TST发送使能
			oamEthernetInfo.setSendWay(0);
			// TST丢弃适用性
			oamEthernetInfo.setTstDiscard(0);
			// TST数据TLV类型
			oamEthernetInfo.setTstTlvType(0);
			// TST数据TLV长度
			oamEthernetInfo.setTstTlvLength("45");
			// TST发送周期
			oamEthernetInfo.setTstSendCycle(3);
			// LM使能
			oamEthernetInfo.setLmENable(0);
			// LM优先级
			oamEthernetInfo.setLmPriority(0+"");
			// LM发送周期
			oamEthernetInfo.setLmSendCycle(0);
			// DM使能
			oamEthernetInfo.setDmENable(0);
			// DM优先级
			oamEthernetInfo.setDmPriority(0+"");
			// DM发送周期
			oamEthernetInfo.setDmSendCycle(0);
			// Remote MEP ID 1
			oamEthernetInfo.setRemoteMepId1(0+"");
			// MAC 1地址
			oamEthernetInfo.setMacAddress1("00-00-00-00-00-01");
			// Remote MEP ID 2
			oamEthernetInfo.setRemoteMepId2("0");
			// MAC 2地址
			oamEthernetInfo.setMacAddress2("00-00-00-00-00-01");
			// Remote MEP ID 3
			oamEthernetInfo.setRemoteMepId3("0");
			// MAC3地址
			oamEthernetInfo.setMacAddress3("00-00-00-00-00-01");
			// Remote MEP ID4
			oamEthernetInfo.setRemoteMepId4("0");
			// MAC 4地址
			oamEthernetInfo.setMacAddress4("00-00-00-00-00-01");
			// MIP生成规则
			oamEthernetInfo.setMipCreate(0);

			// 下适配
			oamInfo.setOamEthernetInfo(oamEthernetInfo);
			oamInfoList.add(oamInfo);
			return oamInfoList;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		} finally {
			UiUtil.closeService_MB(portService);
		}
	}

	/**
	 * 给OAM赋值
	 * @param oam
	 * @param objectName 
	 */
	public void setValue2OAM(OamInfo oam, NameAndStringValue_T[] additionalInfo, String objType,String type) {
		
		String isLck;
		if("0".equals(type)){	//CC连通性测试
			String cv = super.getValueByKey(additionalInfo, "CV");
			String cvCle = super.getValueByKey(additionalInfo, "CVCLE");
			if(cv.equals("1")){
				 oam.getOamMep().setCv(true);
				if(cvCle.equals("1")){	//1=3.33ms
					 oam.getOamMep().setCvCycle(87);
				}else if(cvCle.equals("2")){//2=10ms
					 oam.getOamMep().setCvCycle(88);
				}else if(cvCle.equals("3")){//3=100ms
					 oam.getOamMep().setCvCycle(89);
				}else if(cvCle.equals("4")){//4=1000ms
					 oam.getOamMep().setCvCycle(90);
				}
			}else{
				 oam.getOamMep().setCv(false);
				 oam.getOamMep().setCvCycle(0);
			}
		}else{
			isLck = super.getValueByKey(additionalInfo, "LCK");
			if((EServiceType.ETHERNET.toString().equals(objType))){
				if(Integer.parseInt(isLck) == 1){
					oam.getOamEthernetInfo().setLckSendEnabel(1);
				}else{
					oam.getOamEthernetInfo().setLckSendEnabel(0);
				}
			}else{
				if(Integer.parseInt(isLck) == 1){
					 oam.getOamMep().setLck(true);
				}else{
					 oam.getOamMep().setLck(false);
				}
			}
		}
	}

	/**
	 * corba对象转换 mip
	 * @param paramMEGCreateData_T
	 * @param oamInfo
	 * @throws ProcessingFailureException
	 */
	public void convertCorba2MIP(MEGCreateData_T paramMEGCreateData_T,
			OamInfo oamInfo) throws ProcessingFailureException {
		OamMipInfo oamMipInfo = new OamMipInfo();
		try {
			if(paramMEGCreateData_T.tpList.length!=1 || paramMEGCreateData_T.tpList[0].egressTrafficDescriptorName.length!=1||
					paramMEGCreateData_T.tpList[0].ingressTrafficDescriptorName.length!=2)
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			//OAM类型
			oamMipInfo.setObjType(EServiceType.TUNNEL.toString());
			//本端维护点
			oamMipInfo.setMipId(Integer.parseInt(super.getValueByKey(paramMEGCreateData_T.tpList[0].egressTrafficDescriptorName, "MIP")));
			//对端维护点
			oamMipInfo.setAMId(Integer.parseInt(super.getValueByKey(paramMEGCreateData_T.tpList[0].ingressTrafficDescriptorName, "AMIP")));
			//对端维护点
			oamMipInfo.setZMId(Integer.parseInt(super.getValueByKey(paramMEGCreateData_T.tpList[0].ingressTrafficDescriptorName, "ZMIP")));
			//serviced 段的主键ID(pw、tunnel 数据库中主键ID)
			oamMipInfo.setServiceId(super.getPrimaryKey(paramMEGCreateData_T.relatedObject));
			//设置网元ID
			oamMipInfo.setSiteId(Integer.parseInt(super.getValueByKey(paramMEGCreateData_T.additionalInfo, "ManagedElement")));
			//megid
			oamMipInfo.setMegId(Integer.parseInt(super.getValueByKey(paramMEGCreateData_T.additionalInfo, "MegId")));
			oamInfo.setOamMip(oamMipInfo);
		} catch (NumberFormatException e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"convertCorba2Oam ex.");
		}
	
	}
}
