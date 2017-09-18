package com.nms.corba.ninterface.impl.service.proxy;

import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.List;

import maintenanceOps.MEGCreateData_T;
import maintenanceOps.MEG_T;
import maintenanceOps.MEG_THolder;
import maintenanceOps.OAMLinkLBResult_T;
import maintenanceOps.OAMLinkLBResult_THolder;

import org.apache.log4j.Logger;

import com.nms.corba.ninterface.enums.EOAMType;
import com.nms.corba.ninterface.impl.service.tool.MaintenanceConvertTool;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.oam.OamEthernetInfo;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamLinkInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.bean.ptn.oam.OamMipInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EPwType;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.oam.OamEthreNetService_MB;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.ptn.port.PortLagService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.dispatch.ETHLinkOAMConfigDispath;
import com.nms.service.impl.dispatch.EthOAMConfigDispath;
import com.nms.service.impl.dispatch.TMCOAMConfigDispath;
import com.nms.service.impl.dispatch.TMPOAMConfigDispath;
import com.nms.service.impl.dispatch.TMSOAMConfigDispath;
import com.nms.service.impl.dispatch.TmsOamDispatch;
import com.nms.service.impl.dispatch.TunnelDispatch;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/***
 * function:实现OAM（段/lsp/tunnel）功能的处理
 * 
 * @author zk
 */
public class CorbaMaintenanceProxy{

	private static Logger LOG = Logger.getLogger(CorbaMaintenanceProxy.class.getName());

	/**
	 * function:创建一条OAM 包含:(段/pw/tunnel层的OAM)
	 * 
	 * @param paramMEGCreateData_T
	 *            创建OAM 包含:(段/pw/tunnel层的OAM)的所有信息
	 * @param paramNVSList_THolder
	 *            需要返回的数据（OAM的信息）
	 * @throw ProcessingFailureException
	 * 
	 */
	public void createMEG(MEGCreateData_T paramMEGCreateData_T, NVSList_THolder paramNVSList_THolder) throws ProcessingFailureException {
		
       OamInfo oamInfo = null ; 
       String result = null;
       MaintenanceConvertTool maintenanceConvertTool;
		try {
			if( null == paramMEGCreateData_T ){
				return ;
			}
			checkIsExits(paramMEGCreateData_T);
			oamInfo = new OamInfo();
			maintenanceConvertTool = new MaintenanceConvertTool();
			if(isMep(paramMEGCreateData_T)){
				//转换CORBA对象2Oam
				maintenanceConvertTool.convertCorba2Oam(paramMEGCreateData_T,oamInfo);
			}else{
				maintenanceConvertTool.convertCorba2MIP(paramMEGCreateData_T, oamInfo);
			}

			//下底层适配
			result = dispatchOam(oamInfo,paramMEGCreateData_T.megType,0); 
			
			//创建成功
			if(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(result)){
				//成功就返回数据
				paramNVSList_THolder.value = maintenanceConvertTool.convertOAMName(oamInfo);
			}else{
				paramNVSList_THolder.value = new NameAndStringValue_T[0];
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"createMEG ex.");
		}finally{
            oamInfo = null ; 
            result = null;
		}
	}
	
	/**
	 * 判断是否是MEP
	 * @param MEGCreateData_T	corba 创建数据
	 * @return
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	private boolean isMep(MEGCreateData_T MEGCreateData_T) throws Exception {
		Tunnel tunnel;
		MaintenanceConvertTool maintenanceConvertTool = null;
		TunnelService_MB tunnelService = null;
		boolean fale = false;
		try {
			if(MEGCreateData_T.megType != EOAMType.TUNNEL.getValue())
				return true;
			List<Integer> ids = new ArrayList<Integer>();
			maintenanceConvertTool = new MaintenanceConvertTool();
			int id = maintenanceConvertTool.getPrimaryKey(MEGCreateData_T.relatedObject);
			int neId = Integer.parseInt(maintenanceConvertTool.getValueByKey(MEGCreateData_T.additionalInfo, "ManagedElement"));
			tunnelService = (TunnelService_MB)ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnel = new Tunnel();
			tunnel.setTunnelId(id);
			List<Tunnel> tunnelList= tunnelService.select_nojoin(tunnel);
			if(tunnelList.size() != 1)
			  throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			tunnel = tunnelList.get(0);
			if(neId == tunnel.getASiteId() || neId == tunnel.getZSiteId())
				return true;
			for (Lsp lsp : tunnel.getLspParticularList()) {
				ids.add(lsp.getASiteId());
				ids.add(lsp.getZSiteId());
			}
			if(null != tunnel.getProtectTunnel()){
				for (Lsp lsp : tunnel.getProtectTunnel().getLspParticularList()) {
					ids.add(lsp.getASiteId());
					ids.add(lsp.getZSiteId());
				}
			}
			if(ids.contains(neId))
				return false;
			
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(tunnelService);
		}
		return fale;
	}

	/**
	 * 创建以太网OAM
	 * @throws ProcessingFailureException 
	 */
	public void createETHOAM(MEGCreateData_T paramMEGCreateData_T, NVSList_THolder paramNVSList_THolder) throws ProcessingFailureException {
		String result;
		OamInfo oamInfo;
		List<OamInfo> oamInfoList = null;
		MaintenanceConvertTool maintenanceConvertTool = null;
		EthOAMConfigDispath dispath;
		SiteService_MB siteService = null;
		try {
			checkIsExits(paramMEGCreateData_T);
				
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			dispath = new EthOAMConfigDispath();
			oamInfo = new OamInfo();
			maintenanceConvertTool = new MaintenanceConvertTool();
		
			//转换CORBA对象2Oam
			if(siteService.getManufacturer(Integer.parseInt(maintenanceConvertTool.getValueByKey(paramMEGCreateData_T.additionalInfo, "ManagedElement")))
					!=EManufacturer.WUHAN.getValue()){
				createMEG(paramMEGCreateData_T,paramNVSList_THolder);
				return;
			}
			oamInfoList = maintenanceConvertTool.convertCorba2ETHOam(paramMEGCreateData_T,oamInfo);
			//下底层适配
			result = dispath.excuteInsert(oamInfoList);
			//创建成功
			if(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(result)){
				//成功就返回数据
				paramNVSList_THolder.value = maintenanceConvertTool.convertOAMName(oamInfo);
			}else{
				paramNVSList_THolder.value = new NameAndStringValue_T[0];
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"createETHOAM ex.");
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	/**
	 * 验证业务是否有OAM
	 * @param paramMEGCreateData_T
	 * @throws ProcessingFailureException 
	 */
	private void checkIsExits(MEGCreateData_T paramMEGCreateData_T) throws ProcessingFailureException {
		try {
			if(null == paramMEGCreateData_T.relatedObject|| paramMEGCreateData_T.relatedObject.length != 3)
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			if(paramMEGCreateData_T.megType ==  EOAMType.ETHERNET.getValue()){
				checkEthreNet(paramMEGCreateData_T);		
			}else{
				checkMEPOrMIP(paramMEGCreateData_T);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		}  catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"checkIsExits ex.");
		}
	}

	/**
	 * 验证以太网OAM
	 * @param paramMEGCreateData_T	corba MEG创建对象
	 * @throws ProcessingFailureException
	 */
	private void checkEthreNet(MEGCreateData_T paramMEGCreateData_T) throws ProcessingFailureException {
		OamEthreNetService_MB oamEthreNetService = null;
		PortService_MB portService = null;
		PortInst portInst;
		OamEthernetInfo oamEthernetInfo;
		List<OamEthernetInfo> list;
		MaintenanceConvertTool maintenanceConvertTool;
		int neId;
		try {
			maintenanceConvertTool = new MaintenanceConvertTool();
			oamEthreNetService = (OamEthreNetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OAMETHERNET);
			neId = Integer.parseInt(maintenanceConvertTool.getValueByKey(paramMEGCreateData_T.additionalInfo, maintenanceConvertTool.MANAGEELEMENT));
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInst = portService.selectPortybyid(Integer.parseInt(maintenanceConvertTool.getValueByKey(paramMEGCreateData_T.relatedObject, maintenanceConvertTool.PTP, neId)));
			if(null == portInst)
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			oamEthernetInfo = new OamEthernetInfo();
			oamEthernetInfo.setPort(portInst.getNumber()+"");
			oamEthernetInfo.setSiteId(portInst.getSiteId());
			oamEthernetInfo.setSlot(portInst.getSlotNumber());
			list = oamEthreNetService.queryByNeIDSide(oamEthernetInfo);
			if(null != list && list.size() > 0 )
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"MEG already exists.");
		}  catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"checkEthreNet ex.");
		} finally {
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(oamEthreNetService);
		}
		
	}

	/**
	 * 验证MEP
	 * @param paramMEGCreateData_T	corba MEG创建对象
	 * @throws ProcessingFailureException
	 */
	private void checkMEPOrMIP(MEGCreateData_T paramMEGCreateData_T) throws ProcessingFailureException {
		OamInfo oamInfo;
		int type;
		int id;
		int neId;
		OamMepInfo oamMepInfo;
		OamMipInfo oamMipInfo;
		OamInfoService_MB oamInfoService = null;
		MaintenanceConvertTool maintenanceConvertTool;
		try {
			maintenanceConvertTool = new MaintenanceConvertTool();
			oamInfo = new OamInfo();
			oamMepInfo = new OamMepInfo();
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			type = Integer.parseInt(paramMEGCreateData_T.relatedObject[2].value.split("/")[0]);
			id = maintenanceConvertTool.getPrimaryKey(paramMEGCreateData_T.relatedObject);
			neId = Integer.parseInt(maintenanceConvertTool.getValueByKey(paramMEGCreateData_T.additionalInfo, "ManagedElement"));
			oamMepInfo.setServiceId(id);
			oamMepInfo.setSiteId(neId);
			if(type ==  EOAMType.SECTION.getValue()){
				oamMepInfo.setObjType(EOAMType.SECTION.toString());
				oamInfo.setOamMep(oamMepInfo);
				oamInfo = oamInfoService.queryByCondition(oamInfo, OamTypeEnum.AMEP);
			}else if(type ==  EOAMType.PW.getValue()){
				oamMepInfo.setObjType(EOAMType.PW.toString());
				oamInfo.setOamMep(oamMepInfo);
				oamInfo = oamInfoService.queryByCondition(oamInfo, OamTypeEnum.AMEP);
			}else if(type ==  EOAMType.TUNNEL.getValue()){
				oamInfo = new OamInfo();
				oamMipInfo = new OamMipInfo();
				oamMipInfo.setObjType(EOAMType.TUNNEL.toString());
				oamMipInfo.setServiceId(id);
				oamMipInfo.setSiteId(neId);
				oamInfo.setOamMip(oamMipInfo);
				oamInfo = oamInfoService.queryByCondition(oamInfo, OamTypeEnum.MIP);
				if(null != oamInfo && null != oamInfo.getOamMip() && 0 != oamInfo.getOamMip().getId())
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"MEG already exists");
				
				oamMepInfo.setObjType(EOAMType.TUNNEL.toString());
				oamInfo.setOamMep(oamMepInfo);
				oamInfo = oamInfoService.queryByCondition(oamInfo, OamTypeEnum.AMEP);
				
			}
			if(null != oamInfo && null != oamInfo.getOamMep() && 0 != oamInfo.getOamMep().getId())
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"MEG already exists.");
		}  catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"checkMEP ex.");
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
	}

	/**
	 * fcuntion:根据不同级别的OAM 下发给不同的底层适配
	 * @param oamInfo 信息
	 * @param oamType oam级别层次（段/tunnel/pw）
	 * @return result //结果 是否成功
	 * @throws Exception
	 */
	public String dispatchOam(OamInfo oamInfo,int oamType,int insertOrUpdate) throws Exception{
		List<OamInfo> oamInfoList = null;
        String result = null;
		try {
			oamInfoList = new ArrayList<OamInfo>();
			oamInfoList.add(oamInfo);
			if(EOAMType.SECTION.getValue() == oamType){
				result = saveSegmeng(oamInfo,insertOrUpdate);
			}else if(EOAMType.TUNNEL.getValue() == oamType){
				result = saveTunnel(oamInfo,insertOrUpdate);
			}else if(EOAMType.PW.getValue() == oamType){
				result = savePW(oamInfo,insertOrUpdate);
			}else if(EOAMType.ETHERNET.getValue() == oamType){
				result = this.saveELineAc(oamInfo,insertOrUpdate);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"dispatchOam ex.");
		}
		return result;
	}
	

	/**
	 * function:查询一条OAM 包含:(段/pw/tunnel层的OAM)
	 * @param paramArrayOfNameAndStringValue_T
	 *             查询OAM所需要的信息 包含:(段/pw/tunnel层的OAM)的所有信息
	 * @param paramMEG_THolder
	 *             需要返回的数据（OAM的信息）
	 *  @throw ProcessingFailureException
	 *           
	 */
	public void getMEG(NameAndStringValue_T[] paramArrayOfNameAndStringValue_T, MEG_THolder paramMEG_THolder)throws ProcessingFailureException { 
		int type;
        int id;
		OamInfo oamInfo = null;
        MaintenanceConvertTool maintenanceConvertTool;
		try {
			if(!CheckParameterUtil.checkMEGName(paramArrayOfNameAndStringValue_T))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			maintenanceConvertTool = new MaintenanceConvertTool();
			type = Integer.parseInt(paramArrayOfNameAndStringValue_T[2].value.substring(0, 1));
			type = getType(type,Integer.parseInt(paramArrayOfNameAndStringValue_T[1].value));
			id = maintenanceConvertTool.getPrimaryKey(paramArrayOfNameAndStringValue_T);
			paramMEG_THolder.value = new MEG_T(); 
			if(EOAMType.ETHERNET.getValue() == type){
				getAndConvertEthOam(id,paramMEG_THolder.value);
			}else{
				oamInfo = getOamInfo(id,type);
				//赋值给返回值
				if(null == oamInfo)
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
				maintenanceConvertTool.convertOAM2Corba(oamInfo,paramMEG_THolder.value,type);
			}
			
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getAndConvertEthOam ex.");
		}finally{
	        oamInfo = null;
		}
	}
	
	/**
	 * 获取类型（转换成 MEP、mip或以太网）
	 * @param type EOAMType
	 * @param neId 网元ID
	 * @return		转成mep 或 ETHERNET
	 * @throws Exception
	 */
	private int getType(int type,int neId) throws Exception {
		SiteService_MB siteService=null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			if(type == EOAMType.SECTION.getValue())
				type = EOAMType.MEP.getValue();	
			else if(type == EOAMType.TUNNEL.getValue())
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Tunnel should be choose MEP or MIP.");
			else if(type == EOAMType.PW.getValue())
				type = EOAMType.MEP.getValue();	
			else if(type == EOAMType.ETHERNET.getValue())
				type = EOAMType.ETHERNET.getValue();		
			else if(type == EOAMType.ETHERNET.getValue()&&EManufacturer.CHENXIAO.getValue() == siteService.getManufacturer(neId))
				type = EOAMType.MEP.getValue();		
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return type;
	}

	private void getAndConvertEthOam(int id, MEG_T meg) throws ProcessingFailureException {
		OamEthernetInfo oamEthernetInfo;
		OamEthreNetService_MB ethOamInfoService = null;
		List<OamEthernetInfo> oamEthernetInfoList;
		  MaintenanceConvertTool maintenanceConvertTool;
		try {
			maintenanceConvertTool = new MaintenanceConvertTool();
			ethOamInfoService = (OamEthreNetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OAMETHERNET);
			oamEthernetInfo = new OamEthernetInfo();
			oamEthernetInfo.setId(id);
			oamEthernetInfoList = ethOamInfoService.queryByNeIDSide(oamEthernetInfo);
			if(null == oamEthernetInfoList || oamEthernetInfoList.size() != 1)
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
			maintenanceConvertTool.conertEthOAM2Corba(oamEthernetInfoList.get(0),meg);
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getAndConvertEthOam ex.");
		} finally {
			UiUtil.closeService_MB(ethOamInfoService);
		}
		
	}

	/**
	 * 通过条件查询所需的oam信息
	 * @param id	oamId
	 * @param type	oam类型
	 * @param busiType	oam业务类型
	 * @return
	 * @throws Exception
	 */
	private OamInfo getOamInfo(int id,int type) throws Exception{
		
		OamMepInfo oamMepInfo = null;
		OamMipInfo oamMipInfo = null;
		OamInfo oamInfo = null; 
		OamInfoService_MB  oamInfoService = null;
		OamTypeEnum oamTypeEnum = null;
		try {
			oamInfo = new OamInfo();
			oamInfoService = (OamInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			if(EOAMType.MEP.getValue() == type){
				oamMepInfo = new OamMepInfo();
				oamMepInfo.setId(id);
				oamInfo.setOamMep(oamMepInfo);
				oamTypeEnum = OamTypeEnum.AMEP;
				oamInfo = oamInfoService.queryByCondition(oamInfo,oamTypeEnum);
			}else if(EOAMType.MIP.getValue() == type){
				oamMipInfo = new OamMipInfo();
				oamMipInfo.setId(id);
				oamInfo.setOamMip(oamMipInfo);
				oamTypeEnum = OamTypeEnum.MIP;
				oamInfo = oamInfoService.queryByCondition(oamInfo,oamTypeEnum);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}finally{
		   oamMepInfo = null;
		   UiUtil.closeService_MB(oamInfoService);
		}
		return oamInfo;
	}
	
	/**
	 *@author guoqc 
	 *主动OAM : 使能/禁止CC联通性功能，含段，LSP，PW等
	 *按需OAM : 使能/禁止锁定功能（LCK）
	 *主动OAM : 启动以太网业务OAM性能测量功能
	 *入参  @param objectName : name=EMS value=”DataX”
				 			  name=”主键id”  value=”主键id”
	 *入参  @param additionalInfo
	 *              业务类型,需要操作的功能
	 *@throw ProcessingFailureException
	 */
	public void setAdditionalInfo(NameAndStringValue_T[] objectName, NVSList_THolder additionalInfo)throws ProcessingFailureException {
		String objType = "";
		MaintenanceConvertTool maintenanceConvertTool;
		String type;
		int objTypeValue;
		int oamId ;  //0=cc连通性测试，1=LCK
		try {
			maintenanceConvertTool = new MaintenanceConvertTool();
			type = maintenanceConvertTool.getValueByKey(additionalInfo.value, "TYPE");
			//OAM类型  (参考EOAMBusiType)
			objTypeValue = Integer.parseInt(objectName[2].value.substring(0, 1));
			if(!CheckParameterUtil.checkMEGName(objectName))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			
			if(!checkPara(objTypeValue,type))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			
			oamId = maintenanceConvertTool.getPrimaryKey(objectName);
			objType = this.getType(objectName);
			//获取oam信息
			OamInfo oam = this.getOamInfoByCondition(objType, oamId);
			//给oam赋值
			maintenanceConvertTool.setValue2OAM(oam, additionalInfo.value, objType,type);
			//下发数据
			this.sendData(oam, objType);
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"setAdditionalInfo ex.");
		}
	}

	/**
	 * 验证入参
	 * @param objTypeValue 业务类型		 0：段/ 1：lsp 2：pw  3:以太网OAM 4:SECTION_TEST 5TUNNEL_TEST；6PW_TEST；
	 * @param type	修改类型 0=修改cc连通性 1= 修改lck使能
	 * @return
	 */
	private boolean checkPara(int objTypeValue, String type) {
		boolean flag = false;
		if("0".equals(type) && (3==objTypeValue||1==objTypeValue||2==objTypeValue))
			return true;
		if("1".equals(type) && (3==objTypeValue||1==objTypeValue||2==objTypeValue||4==objTypeValue))
			return true;
		return flag;
	}

	/**
	 * 通过条件查询所需的oam信息
	 * @param objType 
	 * @param dbId 
	 * @return OamInfo
	 * @throws ProcessingFailureException 
	 * @throws Exception
	 */
	private OamInfo getOamInfoByCondition(String objType, int dbId) throws ProcessingFailureException {
		OamInfo oam = new OamInfo();
		OamEthernetInfo oamEthernet = new OamEthernetInfo();
		OamMepInfo mep = new OamMepInfo();
		oam.setOamMep(mep);
		oam.setOamEthernetInfo(oamEthernet);
		OamEthreNetService_MB oamInfoService = null;
		OamInfoService_MB oamInstService = null;
		try {
			//以太网OAM
			if(EOAMType.ETHERNET.toString().equals(objType)){
				List<OamEthernetInfo> oamEthernetList = new ArrayList<OamEthernetInfo>();
				oamEthernet = new OamEthernetInfo();
				oamEthernet.setId(dbId);
				oamInfoService = (OamEthreNetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OAMETHERNET);
				oamEthernetList = oamInfoService.queryByNeIDSide(oamEthernet);
				if(oamEthernetList.size() != 1)
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
				oam.setOamEthernetInfo(oamEthernetList.get(0));
			}else{
				mep = new OamMepInfo();
				mep.setId(dbId);
				mep.setObjType(objType);
				oam.setOamMep(mep);
				oamInstService = (OamInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
				oam = oamInstService.queryByCondition(oam, OamTypeEnum.AMEP);
				if(null == oam.getOamMep() || 0 == oam.getOamMep().getId())
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
				oam.setOamType(OamTypeEnum.AMEP);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getOamInfoByCondition ex.");
		}finally{
			UiUtil.closeService_MB(oamInfoService);
			UiUtil.closeService_MB(oamInstService);
		}
		return oam;
	}
	
	
	
	/**
	 * 下发数据到设备,并返回结果:成功/失败
	 * @param oam
	 * @param objType
	 * @return
	 * @throws Exception 
	 */
	private String sendData(OamInfo oam, String objType) throws Exception {
		if(EOAMType.SECTION.toString().equals(objType) ||
		   EOAMType.TUNNEL.toString().equals(objType) ||
		   EOAMType.PW.toString().equals(objType)){
			TmsOamDispatch tmsOamDispatch = new TmsOamDispatch();
			return tmsOamDispatch.excuteUpdate(oam);
		}else if(EOAMType.SECTION_TEST.toString().equals(objType)){
			TMSOAMConfigDispath tmsDispatch = new TMSOAMConfigDispath();
			return tmsDispatch.excuteUpdate(oam);
		}else if(EOAMType.TUNNEL_TEST.toString().equals(objType)){
			TMPOAMConfigDispath tmpDispatch = new TMPOAMConfigDispath();
			return tmpDispatch.excuteUpdate(oam);
		}else if(EOAMType.PW_TEST.toString().equals(objType)){
			TMCOAMConfigDispath tmcDispatch = new TMCOAMConfigDispath();
			return tmcDispatch.excuteUpdate(oam);
		}else if(EOAMType.ETHERNET.toString().equals(objType)){
			EthOAMConfigDispath ethDispatch = new EthOAMConfigDispath();
			List<OamInfo> oamList = new ArrayList<OamInfo>();
			oamList.add(oam);
			return ethDispatch.excuteInsert(oamList);
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
	}
	

	/**
	 * @author guoqc
	 * 以太网OAM链路发现功能
	 * 入参  portName  厂商信息/主键id
	 * 入参  discoveryMode  工作模式（主动，被动）
	 * 入参  enableDiscovery  OAM协议是否使能
	 * @throws ProcessingFailureException 
	 */
	public void setOAMLinkDiscovery(NameAndStringValue_T[] portName,
									int discoveryMode, int enableDiscovery) throws ProcessingFailureException {
		int portId;
		int neId;
		try{
			if(!CheckParameterUtil.checkPTPName(portName))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters");
			portId = Integer.parseInt(portName[2].value);
			neId = Integer.parseInt(portName[1].value);
			//获取oam信息
			OamInfo oam = this.getOamLinkInfo(neId,portId);
			//给oam赋值
			this.setValue2OAMLinkInfo(oam, discoveryMode, enableDiscovery);
			//下发数据
			this.sendOamLinkData(oam);
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"setOAMLinkDiscovery ex.");
		}
	}

	/**
	 * 获取oam信息
	 * @param siteId
	 * @return
	 * @throws ProcessingFailureException 
	 */
	private OamInfo getOamLinkInfo(int neId,int portId) throws ProcessingFailureException {
		OamInfo oam = new OamInfo();
		OamLinkInfo oamLink = new OamLinkInfo();
		oamLink.setSiteId(neId);
		oamLink.setObjId(portId);
		oam.setOamLinkInfo(oamLink);
		OamInfoService_MB oamInfoService = null;
		try {
			oamInfoService = (OamInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			oam = oamInfoService.queryByCondition(oam, OamTypeEnum.LINKOAM);
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getOamLinkInfo ex.");
		} finally {
			UiUtil.closeService_MB(oamInfoService);
		}
		return oam;
	}

	/**
	 * 给oam赋值
	 * @param oam
	 * @param discoveryMode
	 * @param enableDiscovery
	 */
	private void setValue2OAMLinkInfo(OamInfo oam, int discoveryMode, int enableDiscovery) {
		OamLinkInfo oamLink = oam.getOamLinkInfo();
		oamLink.setMode(discoveryMode == 1? 547:548);
		oamLink.setOamEnable(enableDiscovery == 0 ? false : true);
	}

	/**
	 * 下发数据到设备
	 * @param oam
	 */
	private String sendOamLinkData(OamInfo oam) {
		try {
			ETHLinkOAMConfigDispath ETHLinkOAMConfigDispath = new ETHLinkOAMConfigDispath();
			return ETHLinkOAMConfigDispath.excuteUpdate(oam);
		} catch (Exception e) {
			LOG.error(e);
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
	}

	/**
	 * @author guoqc
	 * 以太网OAM链路环回
	 * 入参  portName name=EMS value=”DATAX”
				    name=”OAM类型”  value=”主键id”
	 * 入参  linkName  
	 * 入参  enableLB  是否支持远端环回
	 * 出参  oamlinkLBResult  返回以上结果
	 * @throws ProcessingFailureException 
	 */
	public void setOAMLinkLB(NameAndStringValue_T[] portName,
				NameAndStringValue_T[] linkName, int enableLB,
				OAMLinkLBResult_THolder oamlinkLBResult) throws ProcessingFailureException {
		int portId;
		int neId;
		CorbaConvertBase corbaConvertBase;
		try{
			if(!CheckParameterUtil.checkPTPName(portName))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters");
			corbaConvertBase = new CorbaConvertBase();
			portId = Integer.parseInt(portName[2].value);
			neId = Integer.parseInt(portName[1].value);
			//获取oam信息
			OamInfo oam = this.getOamLinkInfo(neId,portId);
			//给oam赋值
			this.setLB2OAMLinkInfo(oam, enableLB);
			//下发数据
			String result = this.sendOamLinkData(oam);
			//如果下发成功,则返回结果
			if(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(result)){
				oamlinkLBResult.value = new OAMLinkLBResult_T();
				oamlinkLBResult.value.errorReason = corbaConvertBase.outErrorMessage(result);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"setOAMLinkLB ex.");
		}
	}

	/**
	 * 以太网链路环回是否使能
	 * @param oam
	 * @param enableLB
	 */
	private void setLB2OAMLinkInfo(OamInfo oam, int enableLB) {
		OamLinkInfo oamLink = oam.getOamLinkInfo();
		oamLink.setRemoteLoop(enableLB);
	}
	
	/**
	 * 获取MEG类型
	 * @param megName	MEG标识符
	 * @return
	 */
	private String getType(NameAndStringValue_T[] megName) {
		String type = null;
		int oamType = Integer.parseInt(megName[2].value.substring(0, 1));
		if(oamType == 1){
			type = "SECTION";
	     }else if(oamType == 2){
	    	 type = "TUNNEL";
	     }else if(oamType == 3){
	    	 type = "PW";
	     }else if(oamType == 4){
	    	 type = "ETHERNET";
	     }else if(oamType == 6){
	    	 type = "SECTION_TEST";
	     }else if(oamType == 7){
	    	 type = "TUNNEL_TEST";
	     }else if(oamType == 8){
	    	 type = "PW_TEST";
	     }
		return type;
	}

	/**
	 * 下发pw
	 * @param oamInfo 	oam对象
	 * @param insertOrUpdate 操作
	 * @return
	 * @throws Exception
	 */
	private String savePW(OamInfo oamInfo, int insertOrUpdate) throws Exception {
		String result = "";
	    PwInfoService_MB pwInfoService = null;
	    PwInfo pwInfo = null;
	    
	    try {
	    	pwInfoService = (PwInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
	    	pwInfo = new PwInfo();
	    	pwInfo.setPwId(oamInfo.getOamMep().getServiceId());
	    	pwInfo = pwInfoService.selectBypwid_notjoin(pwInfo);
			if(pwInfo.getASiteId() == oamInfo.getOamMep().getSiteId()){
				oamInfo.setOamType(OamTypeEnum.AMEP);
			}else{
				oamInfo.setOamType(OamTypeEnum.ZMEP);
			}
	    	if (EPwType.forms(pwInfo.getType().getValue()) == EPwType.ETH) {
	    		oamInfo.setOamObjType("PWETH");
	    		oamInfo.setOamPath("ne/interfaces/pweth/" + oamInfo.getOamMep().getObjId());
	    	} else if (EPwType.forms(pwInfo.getType().getValue()) == EPwType.PDH) {
	    		oamInfo.setOamObjType("PWPDH");
	    		oamInfo.setOamPath("ne/interfaces/pwpdh/" + oamInfo.getOamMep().getObjId());
	    	} else if (EPwType.forms(pwInfo.getType().getValue()) == EPwType.SDH) {
	    		oamInfo.setOamObjType("PWSDH");
	    		oamInfo.setOamPath("ne/interfaces/pwsdh/" + oamInfo.getOamMep().getObjId());
	    	} else if (EPwType.forms(pwInfo.getType().getValue()) == EPwType.SDH_PDH) {
	    		if (oamInfo.getOamMep().getSiteId() == pwInfo.getASiteId()) {
	    			oamInfo.setOamObjType("PWSDH");
	    			oamInfo.setOamPath("ne/interfaces/pwsdh/" + oamInfo.getOamMep().getObjId());
	    		} else {
	    			oamInfo.setOamObjType("PWPDH");
	    			oamInfo.setOamPath("ne/interfaces/pwpdh/" + oamInfo.getOamMep().getObjId());
	    		}
	    	} else if (EPwType.forms(pwInfo.getType().getValue()) == EPwType.PDH_SDH) {
	    		if (oamInfo.getOamMep().getSiteId() == pwInfo.getASiteId()) {
	    			oamInfo.setOamObjType("PWPDH");
	    			oamInfo.setOamPath("ne/interfaces/pwpdh/" + oamInfo.getOamMep().getObjId());
	    		} else {
	    			oamInfo.setOamObjType("PWSDH");
	    			oamInfo.setOamPath("ne/interfaces/pwsdh/" +oamInfo.getOamMep().getObjId());
	    		}
	    	}
	    	DispatchUtil oamDispatch = new DispatchUtil(RmiKeys.RMI_OAM);
	    	if(insertOrUpdate >0){
	    		result = oamDispatch.excuteUpdate(oamInfo);
	    	}else{
	    		result = oamDispatch.excuteInsert(oamInfo);
	    	}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(pwInfoService);
		}
		return result;
	}

	/**
	 * 下发TUNNEL
	 * @param oamInfo oam对象
	 * @param insertOrUpdate	操作
	 * @return
	 * @throws Exception
	 */
	private String saveTunnel(OamInfo oamInfo, int insertOrUpdate) throws Exception {
		String result;
		Tunnel tunnel = null;
		TunnelDispatch tunnelDispatch;
		TunnelService_MB tunnelService = null;
		List<OamInfo> oamList ;
		DispatchUtil oamDispatch ;
		int manufacturer = 0;
		SiteService_MB siteService=null;
		try {
			oamList = new ArrayList<OamInfo>();
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			tunnelDispatch = new TunnelDispatch();
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			if(null != oamInfo.getOamMep()){
				tunnel = tunnelService.selectByTunnelIdAndSiteId(oamInfo.getOamMep().getSiteId(), oamInfo.getOamMep().getServiceId());
				if (tunnel.getASiteId() == oamInfo.getOamMep().getSiteId()) {
					oamInfo.setOamType(OamTypeEnum.AMEP);
				} else {
					oamInfo.setOamType(OamTypeEnum.ZMEP);
				}
				for (Lsp lsp : tunnel.getLspParticularList()) {
					if (lsp.getASiteId() == oamInfo.getOamMep().getSiteId()) {
						oamInfo.getOamMep().setObjId(lsp.getAtunnelbusinessid());
						break;
					}
					if (lsp.getZSiteId() == oamInfo.getOamMep().getSiteId()) {
						oamInfo.getOamMep().setObjId(lsp.getZtunnelbusinessid());
						break;
					}
				}
				manufacturer = siteService.getManufacturer(oamInfo.getOamMep().getSiteId());
			} else {
				tunnel = tunnelService.selectByTunnelIdAndSiteId(oamInfo.getOamMip().getSiteId(), oamInfo.getOamMip().getServiceId());
				for (Lsp lsp : tunnel.getLspParticularList()) {
					if (lsp.getASiteId() == oamInfo.getOamMip().getSiteId()) {
						oamInfo.getOamMip().setObjId(lsp.getAtunnelbusinessid());
						break;
					}
					if (lsp.getZSiteId() == oamInfo.getOamMip().getSiteId()) {
						oamInfo.getOamMip().setObjId(lsp.getZtunnelbusinessid());
						break;
					}
				}
				oamInfo.setOamType(OamTypeEnum.MIP);
				oamInfo.setOamObjType("XC");
				oamInfo.setOamPath("ne/protocols/mpls/xc/" + oamInfo.getOamMip().getObjId());
				manufacturer = siteService.getManufacturer(oamInfo.getOamMip().getSiteId());
			}
			oamList.add(oamInfo);
			tunnel.setOamList(oamList);  
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
				result = tunnelDispatch.excuteUpdate(tunnel);
			} else {
				oamDispatch = new DispatchUtil(RmiKeys.RMI_OAM);
				if (oamInfo.getOamType() == OamTypeEnum.MIP) {
					if (oamInfo.getOamMip().getId() > 0) {
						result = oamDispatch.excuteUpdate(oamInfo);
					} else {
						result = oamDispatch.excuteInsert(oamInfo);
					}
				} else {
					if (oamInfo.getOamMep().getId() > 0) {
						result = oamDispatch.excuteUpdate(oamInfo);
					} else {
						result = oamDispatch.excuteInsert(oamInfo);
					}
				}
			}
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"saveTunnel ex.");
		}finally{
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(siteService);
		}
		return result;
		
	}

	/**
	 * 下发段
	 * @param oamInfo	OAM对象
	 * @param insertOrUpdate	操作
	 * @return
	 * @throws Exception
	 */
	private String saveSegmeng(OamInfo oamInfo, int insertOrUpdate) throws Exception {
		String result;
		TmsOamDispatch tmsOamDispatch = new TmsOamDispatch();
		oamInfo.setOamType(OamTypeEnum.AMEP);
		tmsOamDispatch = new TmsOamDispatch();
		if(insertOrUpdate >0){
			result = tmsOamDispatch.excuteUpdate(oamInfo);
		}else{
			result = tmsOamDispatch.excuteInsert(oamInfo);
		}
		return result;
	}

	private String saveELineAc(OamInfo oamInfo,int insertOrUpdate) throws ProcessingFailureException {
		ElineInfo elineInfo = null;
		AcPortInfoService_MB acInfoService = null;
		PortLagService_MB portLagService = null;
		PortService_MB portService = null;
		SiteService_MB siteService = null;
		ElineInfoService_MB elineService = null;
		List<ElineInfo> list;
		int neId;
		String result;
		OamInfoService_MB oamInfoService = null;
		try {
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			elineService=(ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			neId = oamInfo.getOamMep().getSiteId();
			ElineInfo info = new ElineInfo();
			info.setId(oamInfo.getOamMep().getServiceId());
			list = elineService.selectByCondition(info);
			if(list.size() != 1)
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"The entity is not found");
			elineInfo = list.get(0);
			oamInfo.getOamMep().setObjType(EServiceType.ELINE.toString());
			oamInfo.getOamMep().setServiceId(elineInfo.getId());
			// ac主键
			int acid = 0;
			if (neId == elineInfo.getaSiteId()) {
				acid = elineInfo.getaAcId();
				oamInfo.setOamType(OamTypeEnum.AMEP);
				oamInfo.getOamMep().setObjId(elineInfo.getaXcId());
			} else if (neId == elineInfo.getzSiteId()) {
				acid = elineInfo.getzAcId();
				oamInfo.setOamType(OamTypeEnum.ZMEP);
				oamInfo.getOamMep().setObjId(elineInfo.getzXcId());
			}
			// 通过主键去数据库查询AC对象
			AcPortInfo acPortInfo = new AcPortInfo();
			List<AcPortInfo> acPortInfoList = new ArrayList<AcPortInfo>();
			acPortInfo.setId(acid);
			acPortInfoList = acInfoService.queryByAcPortInfo(acPortInfo);
			if (null == acPortInfoList || acPortInfoList.size() != 1) {
				throw new Exception("查询AC出错");
			}
			acPortInfo = acPortInfoList.get(0);

			// 创建AC设备节点路径。
			oamInfo.setOamObjType("AC");
			if (acPortInfo.getPortId() > 0) {
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				oamInfo.setOamPath("ne/interfaces/eth/" + portService.getPortname(acPortInfo.getPortId()) + "/" + acPortInfo.getAcBusinessId());
			} else {

				portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
				PortLagInfo portLagInfo = new PortLagInfo();
				portLagInfo.setId(acPortInfo.getLagId());
				List<PortLagInfo> portLagInfoList = portLagService.selectByCondition(portLagInfo);

				if (null == portLagInfoList || portLagInfoList.size() != 1) {
					throw new Exception("查询lag出错");
				}
				oamInfo.setOamPath("ne/interfaces/lag/" + portLagInfoList.get(0).getLagID() + "/" + acPortInfo.getAcBusinessId());
				portLagInfoList = null;
				portLagInfo = null;
			}
			int manufacturer = 0;
			manufacturer = siteService.getManufacturer(oamInfo.getOamMep().getSiteId());
			if (manufacturer == EManufacturer.WUHAN.getValue()) {
			    oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
				oamInfoService.saveOrUpdate(oamInfo);
				result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			} else {
				DispatchUtil oamCXServiceImpl = new DispatchUtil(RmiKeys.RMI_OAM);
				if (oamInfo.getOamMep().getId() > 0) {
					result = oamCXServiceImpl.excuteUpdate(oamInfo);
				} else {
					result = oamCXServiceImpl.excuteInsert(oamInfo);
				}
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"saveELineAc ex.");
		} finally {
			UiUtil.closeService_MB(acInfoService);
			UiUtil.closeService_MB(portLagService);
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(oamInfoService);
		}
		return result;
	}
	
}
