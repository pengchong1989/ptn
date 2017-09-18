package com.nms.service.impl.util;

import java.util.ArrayList;
import java.util.List;

import com.nms.corba.ninterface.enums.EOAMType;
import com.nms.db.bean.equipment.card.CardInst;
import com.nms.db.bean.equipment.port.E1Info;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortStm;
import com.nms.db.bean.equipment.port.PortStmTimeslot;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.equipment.slot.SlotInst;
import com.nms.db.bean.ptn.AclInfo;
import com.nms.db.bean.ptn.clock.ClockSource;
import com.nms.db.bean.ptn.clock.ExternalClockInterface;
import com.nms.db.bean.ptn.clock.FrequencyInfo;
import com.nms.db.bean.ptn.clock.FrequencyInfoNeClock;
import com.nms.db.bean.ptn.clock.LineClockInterface;
import com.nms.db.bean.ptn.clock.PortConfigInfo;
import com.nms.db.bean.ptn.clock.TimeManageInfo;
import com.nms.db.bean.ptn.ecn.MCN;
import com.nms.db.bean.ptn.ecn.OSPFInterface;
import com.nms.db.bean.ptn.oam.OamEthernetInfo;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamLinkInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.bean.ptn.oam.OamMipInfo;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.eth.DualInfo;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.protect.DualProtect;
import com.nms.db.bean.ptn.path.protect.LoopProtectInfo;
import com.nms.db.bean.ptn.path.protect.MspProtect;
import com.nms.db.bean.ptn.path.pw.MsPwInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.db.bean.ptn.qos.QosMappingMode;
import com.nms.db.bean.system.OffLinesiteBusi;
import com.nms.db.enums.EActionType;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EPwType;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.model.equipment.card.CardService_MB;
import com.nms.model.equipment.port.E1InfoService_MB;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.port.PortStmService_MB;
import com.nms.model.equipment.port.PortStmTimeslotService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.equipment.slot.SlotService_MB;
import com.nms.model.ptn.clock.ClockFrequService_MB;
import com.nms.model.ptn.clock.ExternalClockInterfaceService_MB;
import com.nms.model.ptn.clock.FrequencyClockManageService_MB;
import com.nms.model.ptn.clock.FrequencyInfoNeClockService_MB;
import com.nms.model.ptn.clock.PortDispositionInfoService_MB;
import com.nms.model.ptn.clock.TimeLineClockInterfaceService_MB;
import com.nms.model.ptn.clock.TimeManageInfoService_MB;
import com.nms.model.ptn.ecn.MCNService_MB;
import com.nms.model.ptn.ecn.OSPFInterfaceService_MB;
import com.nms.model.ptn.oam.OamEthreNetService_MB;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.path.protect.MspProtectService_MB;
import com.nms.model.ptn.path.protect.WrappingProtectService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.ptn.port.DualProtectService_MB;
import com.nms.model.ptn.port.PortLagService_MB;
import com.nms.model.system.OffLinesiteBusiService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.AcCXServiceImpl;
import com.nms.service.impl.cx.CardCXServiceImpl;
import com.nms.service.impl.cx.CesCXServiceImpl;
import com.nms.service.impl.cx.ClockSourceCXServiceImpl;
import com.nms.service.impl.cx.DualCXServiceImpl;
import com.nms.service.impl.cx.E1CXServiceImpl;
import com.nms.service.impl.cx.ELanCXServiceImpl;
import com.nms.service.impl.cx.ETHLinkOamNodeCXServiceImpl;
import com.nms.service.impl.cx.ElineCXServiceImpl;
import com.nms.service.impl.cx.EtreeCXServiceImpl;
import com.nms.service.impl.cx.ExternalClockInterfaceCXServiceImpl;
import com.nms.service.impl.cx.FrequencySiteServiceImpl;
import com.nms.service.impl.cx.LineClockInterfaceCXServiceImpl;
import com.nms.service.impl.cx.MCNCXServiceImpl;
import com.nms.service.impl.cx.MspCXServiceImpl;
import com.nms.service.impl.cx.NNICXServiceImpl;
import com.nms.service.impl.cx.OSPFInterfaceCXServiceImpl;
import com.nms.service.impl.cx.OamCXServiceImpl;
import com.nms.service.impl.cx.PortCXServiceImpl;
import com.nms.service.impl.cx.PortLagCXServiceImpl;
import com.nms.service.impl.cx.PortStmCXServiceImpl;
import com.nms.service.impl.cx.PortStmTimeslotCXServiceImpl;
import com.nms.service.impl.cx.PtpSiteServiceImpl;
import com.nms.service.impl.cx.PwCXServiceImpl;
import com.nms.service.impl.cx.SiteCXServiceImpl;
import com.nms.service.impl.cx.TimePortConfigCXServiceImpl;
import com.nms.service.impl.cx.TunnelCXServiceImpl;
import com.nms.service.impl.cx.UNICXServiceImpl;
import com.nms.service.impl.cx.WrappingCXServiceImpl;
import com.nms.service.impl.wh.AcWHServiceImpl;
import com.nms.service.impl.wh.AclWHServiceImpl;
import com.nms.service.impl.wh.CardWHServiceImpl;
import com.nms.service.impl.wh.ClockFrequWHServiceImpl;
import com.nms.service.impl.wh.DualWHServceImpl;
import com.nms.service.impl.wh.E1WHServiceImpl;
import com.nms.service.impl.wh.ETHLinkOAMConfigWHServiceImpl;
import com.nms.service.impl.wh.ElanWHServiceImpl;
import com.nms.service.impl.wh.ElineWHServiceImpl;
import com.nms.service.impl.wh.EthOAMConfigWHServiceImpl;
import com.nms.service.impl.wh.EtreeWHServiceImpl;
import com.nms.service.impl.wh.ExpMappingPhbWHServiceImpl;
import com.nms.service.impl.wh.OamWHServiceImpl;
import com.nms.service.impl.wh.PhbMappingExpWHServiceImpl;
import com.nms.service.impl.wh.PortLagWHServiceImpl;
import com.nms.service.impl.wh.PortWHServiceImpl;
import com.nms.service.impl.wh.PwWHServiceImpl;
import com.nms.service.impl.wh.SiteWHServiceImpl;
import com.nms.service.impl.wh.TMCOAMCongigWHServiceImpl;
import com.nms.service.impl.wh.TMPOAMConfigWHServiceImpl;
import com.nms.service.impl.wh.TMSOAMConfigWHServiceImpl;
import com.nms.service.impl.wh.TmsOamWHServiceImpl;
import com.nms.service.impl.wh.TunnelWHServiceImpl;
import com.nms.service.impl.wh.WrappingWHServiceImpl;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * 数据下载
 * @author dzy
 *
 */
public class DataDownLoad extends DispatchBase {
	/**
	 * 数据下载
	 * @param downLoadSelected  被选择的模块
	 * 		downLoadSelected[0]
	 * @param siteInstList 
	 * 				网元集合
	 */
	public String dataDownLoadAction(List<SiteInst> siteInstList, int[] downLoadSelected) {
		String result = null;
		if(siteInstList.size()==0){
			return null;
		}
		List<OffLinesiteBusi> offLinesiteBusiList;
		OperationServiceI operationServiceI=null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		try {
			//将晨晓和武汉设备分开，两者的数据下载方法不一样
			List<SiteInst> cxSiteList = this.getSiteListByCondition(siteInstList, 1);
			List<SiteInst> whSiteList = this.getSiteListByCondition(siteInstList, 0);
			if(whSiteList.size() > 0){
				result = this.dataDownLoad4WH(whSiteList, downLoadSelected);
			}
			if(cxSiteList.size() > 0){
				offLinesiteBusiService = (OffLinesiteBusiService_MB)
				ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
				offLinesiteBusiList = offLinesiteBusiService.selectBySiteIds(cxSiteList);
				if(offLinesiteBusiList.size()>0){
					for(OffLinesiteBusi offLinesiteBusi:offLinesiteBusiList){
						if(offLinesiteBusi.getOperType()==EServiceType.CARD.getValue()&&downLoadSelected[0]==1){ //板卡
							cardAction(offLinesiteBusi,operationServiceI);
						}else if(offLinesiteBusi.getOperType()==EServiceType.SITE.getValue()&&downLoadSelected[0]==1){  //配置
							siteConfig(offLinesiteBusi,operationServiceI);
						}else if(offLinesiteBusi.getOperType()==EServiceType.MCN.getValue()&&downLoadSelected[0]==1){  //mcn
							mcnDownLoad(offLinesiteBusi,operationServiceI);
						}else if(offLinesiteBusi.getOperType()==EServiceType.OSPF.getValue()&&downLoadSelected[0]==1){  //OSPF
							ospfDownLoad(offLinesiteBusi,operationServiceI);
						}else if(offLinesiteBusi.getOperType()==EServiceType.ETH.getValue()&&downLoadSelected[1]==1){  //ETH
							ethDownLoad(offLinesiteBusi,operationServiceI);
						}else if(offLinesiteBusi.getOperType()==EServiceType.PDH.getValue()&&downLoadSelected[1]==1){  //PDH
							pdhDownLoad(offLinesiteBusi,operationServiceI);
						}else if(offLinesiteBusi.getOperType()==EServiceType.SDH.getValue()&&downLoadSelected[1]==1){ //SDH
							sdhDownLoad(offLinesiteBusi,operationServiceI);
						}else if(offLinesiteBusi.getOperType()==EServiceType.SDHSLOTTIME.getValue()&&downLoadSelected[1]==1){ //SDH时隙
							sdhStmDownLoad(offLinesiteBusi,operationServiceI);
						}else if(offLinesiteBusi.getOperType()==EServiceType.LAG.getValue()&&downLoadSelected[1]==1){ //LAG
							lagDownLoad(offLinesiteBusi,operationServiceI);
						}else if(offLinesiteBusi.getOperType()==EServiceType.ACPORT.getValue()&&downLoadSelected[1]==1){  //AC
							acDownLoad(offLinesiteBusi,operationServiceI);
						}else if(offLinesiteBusi.getOperType()==EServiceType.TUNNEL.getValue()&&downLoadSelected[2]==1){ //tunnel
							tunnelDownLoad(offLinesiteBusi,operationServiceI);
						}else if(offLinesiteBusi.getOperType()==EServiceType.PW.getValue()&&downLoadSelected[2]==1){  //PW
							pwDownLoad(offLinesiteBusi,operationServiceI);
						}else if(offLinesiteBusi.getOperType()==EServiceType.CES.getValue()&&downLoadSelected[6]==1){  //CES
							cesDownLoad(offLinesiteBusi,operationServiceI);
						}else if(offLinesiteBusi.getOperType()==EServiceType.ELINE.getValue()&&downLoadSelected[3]==1){ //ELINE
							elineDownLoad(offLinesiteBusi,operationServiceI);
						}else if(offLinesiteBusi.getOperType()==EServiceType.ELAN.getValue()&&downLoadSelected[3]==1){  //ELAN
							elanDownLoad(offLinesiteBusi,operationServiceI); 
						}else if(offLinesiteBusi.getOperType()==EServiceType.ETREE.getValue()&&downLoadSelected[3]==1){ //ETREE
							etreeDownLoad(offLinesiteBusi,operationServiceI); 
						}else if(offLinesiteBusi.getOperType()==EServiceType.CLOCKSTATUS.getValue()&&downLoadSelected[5]==1){ //时钟状态
							clockStatusDownLoad(offLinesiteBusi,operationServiceI); 
						}else if(offLinesiteBusi.getOperType()==EServiceType.CLOCKSOURCE.getValue()&&downLoadSelected[5]==1){ //时钟源
							clockSourceDownLoad(offLinesiteBusi,operationServiceI); 
						}else if(offLinesiteBusi.getOperType()==EServiceType.PTPCONFIG.getValue()&&downLoadSelected[5]==1){ //ptp配置
							ptpConfigDownLoad(offLinesiteBusi,operationServiceI); 
						}else if(offLinesiteBusi.getOperType()==EServiceType.CLOCKPORTCONFIG.getValue()&&downLoadSelected[5]==1){ //时钟端口配置
							clockPortConfigDownLoad(offLinesiteBusi,operationServiceI); 
						}else if(offLinesiteBusi.getOperType()==EServiceType.EXTERNALCLOCK.getValue()&&downLoadSelected[5]==1){ //外时钟
							externalClockDownLoad(offLinesiteBusi,operationServiceI); 
						}else if(offLinesiteBusi.getOperType()==EServiceType.LINECLOCK.getValue()&&downLoadSelected[5]==1){ //线路时钟
							lineClockDownLoad(offLinesiteBusi,operationServiceI); 
						}else if(offLinesiteBusi.getOperType()==EServiceType.LOOPPROTECT.getValue()&&downLoadSelected[4]==1){ //环保护
							loopProtectDownLoad(offLinesiteBusi,operationServiceI); 
						}else if(offLinesiteBusi.getOperType()==EServiceType.MSPPROTECT.getValue()&&downLoadSelected[4]==1){ //LSP保护
							mspProtectDownLoad(offLinesiteBusi,operationServiceI); 
						}else if(offLinesiteBusi.getOperType()==EServiceType.DUAL.getValue()&&downLoadSelected[4]==1){ //环保护
							dualProtectDownLoad(offLinesiteBusi,operationServiceI); 
						}else if(offLinesiteBusi.getOperType()==EServiceType.OAM.getValue()&&downLoadSelected[4]==1){ //OAM
							//				oamDownLoad(offLinesiteBusi,operationServiceI); 
						}else if(offLinesiteBusi.getOperType()==EServiceType.OAMETHLINK.getValue()&&downLoadSelected[1]==1){ //OAM
							oamLinkDownLoad(offLinesiteBusi,operationServiceI); 
						}
					}
				}
			}
		} catch (Exception e) {
			return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		}finally{
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
		return result;
	}
	
	private List<SiteInst> getSiteListByCondition(List<SiteInst> siteInstList, int type){
		List<SiteInst> siteList = new ArrayList<SiteInst>();
		for (SiteInst siteInst : siteInstList) {
			if(siteInst.getManufacturer() == type){
				siteList.add(siteInst);
			}
		}
		return siteList;
		
	}
	
	/**
	 * 武汉设备数据下载实现
	 * @throws Exception 
	 */
	private String dataDownLoad4WH(List<SiteInst> siteInstList, int[] downLoadSelected) {
		StringBuffer sb = new StringBuffer();
		for (SiteInst site : siteInstList) {
			String flag = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			try {
				flag = this.downLoad4WHAction(site.getSite_Inst_Id(), downLoadSelected);
			} catch (Exception e) {
				ExceptionManage.dispose(e, this.getClass());
			}
			if(flag.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL))){
				sb.append(site.getCellId()+",");
			}
		}
		String result = sb.toString();
		if(result.length() > 0){
			return result.substring(0, result.length()-1)+ResourceUtil.srcStr(StringKeysObj.NET_BASE)+
			ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		}else{
			return ResultString.CONFIG_SUCCESS;
		}
	}
	
	private String downLoad4WHAction(int siteId, int[] downLoadSelected) throws Exception {
		
		if(1== downLoadSelected[0] || 1 == downLoadSelected[1]){//基础模块，端口:eth,pdh
			List<PortInst> portList = this.getEthPortList(siteId);
			PortWHServiceImpl portWHServiceImpl = new PortWHServiceImpl();
			for (PortInst port : portList) {
				String flag = portWHServiceImpl.excutionUpdate(port);
				if(!ResultString.CONFIG_SUCCESS.equals(flag)){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
				}
			}
			List<E1Info> e1InfoList = this.getE1PortList(siteId);
			if(!e1InfoList.isEmpty()){
				E1WHServiceImpl e1WHServiceImpl = new E1WHServiceImpl();
				String flag = e1WHServiceImpl.excutionUpdate(e1InfoList);
				if(!ResultString.CONFIG_SUCCESS.equals(flag)){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
				}
			}
		}
		if(1 == downLoadSelected[2]){//ptn核心模块 :lag,tunnel,pw,phb映射,ACL,QinQ
			PortLagWHServiceImpl portLagWHServiceImpl = new PortLagWHServiceImpl();
			PortLagInfo portLagInfo = new PortLagInfo();
			portLagInfo.setSiteId(siteId);
			String flag = portLagWHServiceImpl.excutionUpdate(portLagInfo);
			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			}
				
			TunnelWHServiceImpl tunnelWHServiceImpl = new TunnelWHServiceImpl();
			Tunnel tunnel = new Tunnel();
			Lsp lsp = new Lsp();
			lsp.setASiteId(siteId);
			List<Lsp> lspList = new ArrayList<Lsp>();
			lspList.add(lsp);
			tunnel.setLspParticularList(lspList);
			flag = tunnelWHServiceImpl.excutionUpdate(tunnel);
			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			}
				
			PwWHServiceImpl pwWHServiceImpl = new PwWHServiceImpl();
			PwInfo pw = new PwInfo();
			pw.setASiteId(siteId);
			MsPwInfo msPwInfo = new MsPwInfo();
			msPwInfo.setSiteId(siteId);
			List<MsPwInfo> msPwList = new ArrayList<MsPwInfo>();
			pw.setMsPwInfos(msPwList);
			flag = pwWHServiceImpl.excutionUpdate(pw);
			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			}
			
			PhbMappingExpWHServiceImpl phbMappingExpWHServiceImpl = new PhbMappingExpWHServiceImpl();
			QosMappingMode qosMappingMode = new QosMappingMode();
			qosMappingMode.setSiteId(siteId);
			flag = phbMappingExpWHServiceImpl.excutionUpdate(qosMappingMode);
			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			}
				
			ExpMappingPhbWHServiceImpl expMappingPhbWHServiceImpl = new ExpMappingPhbWHServiceImpl();
			flag = expMappingPhbWHServiceImpl.excutionUpdate(qosMappingMode);
			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			}
			
			AclInfo aclInfo = new AclInfo();
			aclInfo.setSiteId(siteId);
			AclWHServiceImpl aclWHServiceImpl = new AclWHServiceImpl();
			flag = aclWHServiceImpl.excutionInsert(aclInfo);
			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			}
			
//			QinQWHServiceImpl qinQWHServiceImpl = new QinQWHServiceImpl();
//			QinqInst qinqInst = new QinqInst();
//			QinqChildInst childInst = new QinqChildInst();
//			childInst.setaSiteId(siteId);
//			List<QinqChildInst> childList = new ArrayList<QinqChildInst>();
//			childList.add(childInst);
//			qinqInst.setQinqChildInst(childList);
//			flag = qinQWHServiceImpl.excutionUpdate(qinqInst);
//			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
//				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
//			}
		}
		if(1 == downLoadSelected[3]){//vpn模块:eth业务
			ElineWHServiceImpl elineWHServiceImpl = new ElineWHServiceImpl();
			ElineInfo elineInfo = new ElineInfo();
			elineInfo.setaSiteId(siteId);
			String flag = elineWHServiceImpl.excutionUpdate(elineInfo);
			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			}
			
			List<EtreeInfo> etreeList = new ArrayList<EtreeInfo>();
			EtreeWHServiceImpl etreeWHServiceImpl = new EtreeWHServiceImpl();
			EtreeInfo etree = new EtreeInfo();
			etree.setRootSite(siteId);
			etreeList.add(etree);
			flag = etreeWHServiceImpl.excutionUpdate(etreeList);
			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			}
		}
		if(1 == downLoadSelected[4]){//保护:环网保护,双规保护,OAM,按需OAM
			List<LoopProtectInfo> loopList = new ArrayList<LoopProtectInfo>();//不需要查库
			WrappingWHServiceImpl wrappingWHServiceImpl = new WrappingWHServiceImpl();
			LoopProtectInfo loop = new LoopProtectInfo();
			loop.setSiteId(siteId);
			loopList.add(loop);
			String flag = wrappingWHServiceImpl.excutionUpdate(loopList);
			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			}
			
			List<DualInfo> dualList = new ArrayList<DualInfo>();
			DualWHServceImpl dualWHServceImpl = new DualWHServceImpl();
			DualInfo dual = new DualInfo();
			dual.setRootSite(siteId);
			dualList.add(dual);
			flag = dualWHServceImpl.excutionUpdate(dualList);
			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			}
			
			List<OamInfo> oamList = this.getOamList(siteId, 1);
			TmsOamWHServiceImpl tmsOamWhServiceImpl = new TmsOamWHServiceImpl();
			for (OamInfo oam : oamList) {
				flag = tmsOamWhServiceImpl.excutionUpdate(oam);
				if(!ResultString.CONFIG_SUCCESS.equals(flag)){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
				}
			}
			
			OamInfo oamInfo = new OamInfo();
			OamMepInfo mep = new OamMepInfo();
			mep.setSiteId(siteId);
			oamInfo.setOamMep(mep);
			TMSOAMConfigWHServiceImpl tmsoamConfigWHServiceImpl = new TMSOAMConfigWHServiceImpl();
			flag = tmsoamConfigWHServiceImpl.excutionUpdate(oamInfo);
			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			}
			
			TMPOAMConfigWHServiceImpl tmpoamConfigWHServiceImpl = new TMPOAMConfigWHServiceImpl();
			flag = tmpoamConfigWHServiceImpl.excutionUpdate(oamInfo);
			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			}
			
			TMCOAMCongigWHServiceImpl tmcoamConfigWHServiceImpl = new TMCOAMCongigWHServiceImpl();
			flag = tmcoamConfigWHServiceImpl.excutionUpdate(oamInfo);
			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			}
			
			EthOAMConfigWHServiceImpl ethOAMConfigWHServiceImpl = new EthOAMConfigWHServiceImpl();
			List<OamInfo> oamInfoList = this.getETHOAM(siteId);
			if(!oamInfoList.isEmpty()){
				flag = ethOAMConfigWHServiceImpl.excutionInsert(oamInfoList);
				if(!ResultString.CONFIG_SUCCESS.equals(flag)){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
				}
			}
		}
		if(1 == downLoadSelected[5]){//时钟频率:时钟
			FrequencyInfo frequencyInfo = this.getClock(siteId);
			if(frequencyInfo != null){
				ClockFrequWHServiceImpl clockFrequWHServiceImpl = new ClockFrequWHServiceImpl();
				String flag = clockFrequWHServiceImpl.excutionUpdate(frequencyInfo);
				if(!ResultString.CONFIG_SUCCESS.equals(flag)){
					return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
				}
			}
		}
		if(1 == downLoadSelected[6]){//CES:ces业务
			ElineInfo elineInfo = new ElineInfo();
			elineInfo.setaSiteId(siteId);
			ElineWHServiceImpl elineWHServiceImpl = new ElineWHServiceImpl();
			String flag = elineWHServiceImpl.excutionUpdate(elineInfo);
			if(!ResultString.CONFIG_SUCCESS.equals(flag)){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
			}
		}
		return ResultString.CONFIG_SUCCESS;
	}

	/**
	 * 查询以太网OAM
	 */
	private List<OamInfo> getETHOAM(int siteId) {
		List<OamInfo> oamInfoList = new ArrayList<OamInfo>();
		OamEthreNetService_MB service = null;
		try {
			service = (OamEthreNetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OAMETHERNET);
			OamEthernetInfo oamEthernetInfo = new OamEthernetInfo();
			oamEthernetInfo.setSiteId(siteId);
			List<OamEthernetInfo> ethOamList = service.queryByNeIDSide(oamEthernetInfo);
			if(ethOamList != null && !ethOamList.isEmpty()){
				OamInfo oam = new OamInfo();
				oam.setOamEthernetInfo(ethOamList.get(0));
				oamInfoList.add(oam);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
		return oamInfoList;
	}

	private List<E1Info> getE1PortList(int siteId) {
		E1InfoService_MB service = null;
		List<E1Info> e1InfoList = new ArrayList<E1Info>();
		try {
			service = (E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);
			e1InfoList = service.selectBySiteId(siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
		return e1InfoList;
	}

	private List<PortInst> getEthPortList(int siteId) {
		PortService_MB	portService = null;
		List<PortInst> portList = new ArrayList<PortInst>();
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			PortInst port = new PortInst();
			port.setSiteId(siteId);
			List<PortInst> portInstList = portService.select(port); 
			if(portInstList != null && !portInstList.isEmpty()){
				portList.addAll(this.getPortInstbyPortType(portInstList));
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(portService);
		}
		return portList;
	}
	
	/**
	 * 按端口类型拿到数据
	 * @param portInstList
	 * @return
	 */
	private List<PortInst> getPortInstbyPortType(List<PortInst> portInstList) {
		List<PortInst> infoList = new ArrayList<PortInst>();
		for (PortInst info : portInstList) {
			if (info.getPortType().equalsIgnoreCase("NNI") || 
					info.getPortType().equalsIgnoreCase("UNI") || 
					info.getPortType().equalsIgnoreCase("NONE")){
				infoList.add(info);
			}
		}
		return infoList;
	}

	private List<OamInfo> getOamList(int siteId, int type) {
		List<OamInfo> oamList = new ArrayList<OamInfo>();
		OamInfoService_MB oamService = null;
		try {
			String objType = "";
			if(type == 1){
				objType = EOAMType.SECTION.toString();
			}else if(type == 2){
				objType = EOAMType.SECTION_TEST.toString();
			}else if(type == 3){
				objType = EOAMType.TUNNEL_TEST.toString();
			}else if(type == 4){
				objType = EOAMType.PW_TEST.toString();
			}
			oamService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			OamInfo oam = new OamInfo();
			OamMepInfo mep = new OamMepInfo();
			mep.setSiteId(siteId);
			mep.setObjType(objType);
			oam.setOamMep(mep);
			oamList = oamService.queryBySiteId(oam, OamTypeEnum.AMEP);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(oamService);
		}
		return oamList;
	}

	private FrequencyInfo getClock(int siteId) {
		ClockFrequService_MB clockService = null;
		try {
			clockService = (ClockFrequService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ClockFrequ);
			List<FrequencyInfo> cList = clockService.query(siteId);
			if(cList != null && !cList.isEmpty()){
				return cList.get(0);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(clockService);
		}
		return null;
	}

	/**
	 * oam管理
	 * @param offLinesiteBusi
	 * 					离线数据
	 * @param operationServiceI
	 * @param type
	 * 			类型
	 * @throws Exception
	 */
	private void oamDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI ,String type) throws Exception {
		
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		String result = null;
		OamInfoService_MB oamInfoService = null;
		
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)
			ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new OamCXServiceImpl();
			}else{
				operationServiceI = new OamWHServiceImpl();
			}
			OamMepInfo oamMepInfo;
			OamMipInfo oamMipInfo;
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			List<OamInfo> oamInfoList = new ArrayList<OamInfo>();
			OamInfo oamInfo = new OamInfo();
			if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
				if("ETH".equals(type)){
					type = "SECTION";
				}
				if((OamTypeEnum.AMEP.getValue()+"").equals(offLinesiteBusi.getType())){
					oamMepInfo = new OamMepInfo();
					oamMepInfo.setId(offLinesiteBusi.getOperId());
					oamMepInfo.setObjId(offLinesiteBusi.getPortId());
					oamMepInfo.setObjType(type);
					oamMepInfo.setSiteId(offLinesiteBusi.getSiteId());
					if(null!=offLinesiteBusi.getActionIdentify()&&!"".equals(offLinesiteBusi.getActionIdentify())){
						oamMepInfo.setServiceId(Integer.parseInt(offLinesiteBusi.getActionIdentify()));
					}
					oamInfo.setOamMep(oamMepInfo);
				}else if((OamTypeEnum.MIP.getValue()+"").equals(offLinesiteBusi.getType())){
					oamMipInfo = new OamMipInfo();
					oamMipInfo.setId(offLinesiteBusi.getOperId());
					oamMipInfo.setObjId(offLinesiteBusi.getPortId());
					oamMipInfo.setObjType(type);
					oamMipInfo.setSiteId(offLinesiteBusi.getSiteId());
					oamInfo.setOamMip(oamMipInfo);
					oamInfo.setOamType(OamTypeEnum.MIP);
				}
			}else{
				if((OamTypeEnum.MEP.getValue()+"").equals(offLinesiteBusi.getType())){
					oamMepInfo = new OamMepInfo();
					oamMepInfo.setId(offLinesiteBusi.getOperId());
					oamMepInfo.setSiteId(offLinesiteBusi.getSiteId());
					oamInfo.setOamMep(oamMepInfo);
					oamInfo = oamInfoService.queryByCondition(oamInfo,OamTypeEnum.AMEP);
					oamInfo.setOamObjType(type);
					oamInfo.setOamType(OamTypeEnum.AMEP);
				}else if((OamTypeEnum.MIP.getValue()+"").equals(offLinesiteBusi.getType())){
					oamMipInfo = new OamMipInfo();
					oamMipInfo.setId(offLinesiteBusi.getOperId());
					oamMipInfo.setSiteId(offLinesiteBusi.getSiteId());
					oamInfo.setOamMip(oamMipInfo);
					oamInfo = oamInfoService.queryByCondition(oamInfo,OamTypeEnum.MIP);
					oamInfo.setOamObjType(type);
					oamInfo.setOamType(OamTypeEnum.MIP);
				}
			}
			oamInfoList.add(oamInfo);
			result = dataDownLoadSaveOrUpdate(oamInfoList,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(oamInfoService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * oam链路
	 * @param offLinesiteBusi
	 * 					离线数据
	 * @param operationServiceI
	 * @throws Exception
	 */
	private void oamLinkDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		
		String result = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		OamInfoService_MB oamInfoService = null;
		
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new ETHLinkOamNodeCXServiceImpl();
			}else{
				operationServiceI = new ETHLinkOAMConfigWHServiceImpl();
			}
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			List<OamInfo> oamInfoList = new ArrayList<OamInfo>();
			OamLinkInfo oamLinkInfo = new OamLinkInfo();
			OamInfo oamInfo = new OamInfo();
			if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
				oamLinkInfo.setSiteId(offLinesiteBusi.getSiteId());
				oamLinkInfo.setObjId(offLinesiteBusi.getPortId());
				oamInfo.setOamLinkInfo(oamLinkInfo);
				oamInfoList.add(oamInfo);
			}else{
				oamLinkInfo.setId(offLinesiteBusi.getOperId());
				oamInfo.setOamLinkInfo(oamLinkInfo);
				oamInfo = oamInfoService.queryByCondition(oamInfo,OamTypeEnum.LINKOAM);
				oamInfoList.add(oamInfo);
			}
			result = dataDownLoadSaveOrUpdate(oamInfoList,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(oamInfoService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * 双规保护
	 * @param offLinesiteBusi
	 * 					离线数据
	 * @param operationServiceI
	 * @throws Exception
	 */
	private void dualProtectDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		
		String result = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		DualProtectService_MB dualProtectService = null;
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			operationServiceI = new DualCXServiceImpl();
			dualProtectService = (DualProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.DUALPROTECTSERVICE);
			List<DualProtect> dualProtectList = new ArrayList<DualProtect>();
			DualProtect dualProtect = new DualProtect();
			
			if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
				dualProtect.setSiteId(offLinesiteBusi.getSiteId());
				dualProtect.setName(offLinesiteBusi.getActionIdentify());
				dualProtectList.add(dualProtect);
			}else{
				dualProtect.setId(offLinesiteBusi.getOperId());;
				dualProtectList = dualProtectService.queryByCondition(dualProtect);
			}
			result = dataDownLoadSaveOrUpdate(dualProtectList,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(dualProtectService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	
	/**
	 * MSP保护
	 * @param offLinesiteBusi
	 * @param operationServiceI
	 * @throws Exception
	 */
	private void mspProtectDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		MspProtectService_MB mspProtectService = null;
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			operationServiceI = new MspCXServiceImpl();
			mspProtectService = (MspProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MSPPROTECT);
			List<MspProtect> mspProtectList = new ArrayList<MspProtect>();
			MspProtect mspProtect = new MspProtect();
			mspProtect.setDataDownLoad(true);
			if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
				mspProtect.setSiteId(offLinesiteBusi.getSiteId());
				mspProtect.setBusinessId(Integer.parseInt(offLinesiteBusi.getActionIdentify()));
				mspProtect.setWorkPortId(offLinesiteBusi.getPortId());
				mspProtectList.add(mspProtect);
			}else{
				mspProtect.setId(offLinesiteBusi.getOperId());;
				mspProtectList = mspProtectService.select(mspProtect);
			}
			result = dataDownLoadSaveOrUpdate(mspProtectList,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(mspProtectService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * 环保护
	 * @param offLinesiteBusi
	 * @param operationServiceI
	 * @throws Exception
	 */
	private void loopProtectDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		WrappingProtectService_MB wrappingProtectService  = null;
		
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
    	   operationServiceI = new WrappingCXServiceImpl();
    	   int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
    	   if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
    		   operationServiceI = new WrappingCXServiceImpl();
    	   }else{
    		   //武汉
    	   }
    	   wrappingProtectService = (WrappingProtectService_MB) ConstantUtil.serviceFactory.newService_MB(Services.WRAPPINGPROTECT);
    	   List<LoopProtectInfo> loopProtectInfoList = new ArrayList<LoopProtectInfo>();
    	   LoopProtectInfo loopProtectInfo = new LoopProtectInfo();
    	   
    	   if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
    		   loopProtectInfo.setSiteId(offLinesiteBusi.getSiteId());
    		   loopProtectInfo.setName(offLinesiteBusi.getActionIdentify());
    		   loopProtectInfoList.add(loopProtectInfo);
    	   }else{
    		   loopProtectInfo.setId(offLinesiteBusi.getOperId());;
    		   loopProtectInfoList = wrappingProtectService.select(loopProtectInfo);
    	   }
    	   result = dataDownLoadSaveOrUpdate(loopProtectInfoList,operationServiceI,offLinesiteBusi.getAction());
    	   if (ResultString.CONFIG_SUCCESS.equals(result)) {
    		   offLinesiteBusiService.delete(offLinesiteBusi);
    	   }
	  } catch (Exception e) {
		  throw e;
	  }finally{
		  UiUtil.closeService_MB(wrappingProtectService);
		  UiUtil.closeService_MB(offLinesiteBusiService);
	  }
	}
	/**
	 * 线路时钟
	 * @param offLinesiteBusi
	 * @param operationServiceI
	 * @throws Exception
	 */
	private void lineClockDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		TimeLineClockInterfaceService_MB timeLineClockInterfaceService = null;
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			List<LineClockInterface> list;
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new LineClockInterfaceCXServiceImpl();
			}else{
				//武汉
			}
			timeLineClockInterfaceService = (TimeLineClockInterfaceService_MB)ConstantUtil.serviceFactory.newService_MB(Services.TimeLineClockInterfaceService);
			LineClockInterface lineClockInterface = new LineClockInterface();
			lineClockInterface.setId(offLinesiteBusi.getOperId());
			list = timeLineClockInterfaceService.select(lineClockInterface);
			result = dataDownLoadSaveOrUpdate(list,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(timeLineClockInterfaceService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	
	/**
	 * 外时钟数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @param offLinesiteBusiService service
	 * @throws Exception
	 */
	private void externalClockDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		ExternalClockInterfaceService_MB externalClockInterfaceService = null;
		
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new ExternalClockInterfaceCXServiceImpl();
			}else{
				//武汉
			}
			externalClockInterfaceService = (ExternalClockInterfaceService_MB)ConstantUtil.serviceFactory.newService_MB(Services.ExternalClockInterfaceService);
			ExternalClockInterface externalClockInterface = new ExternalClockInterface();
			List<ExternalClockInterface> externalClockInterfaceList = new ArrayList<ExternalClockInterface>();
			if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
				externalClockInterface.setSiteId(offLinesiteBusi.getSiteId());
				externalClockInterface.setInterfaceName(offLinesiteBusi.getActionIdentify());
				externalClockInterfaceList.add(externalClockInterface);
				result = operationServiceI.excutionDelete(externalClockInterfaceList);
			}else{
				externalClockInterface.setId(offLinesiteBusi.getOperId());
				externalClockInterfaceList = externalClockInterfaceService.select(externalClockInterface);
			}
			result = dataDownLoadSaveOrUpdate(externalClockInterfaceList,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(externalClockInterfaceService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * 端口配置数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @param offLinesiteBusiService service
	 * @throws Exception
	 */
	private void clockPortConfigDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		PortDispositionInfoService_MB portDispositionInfoService = null;
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new TimePortConfigCXServiceImpl();
			}else{
				//武汉
			}
			portDispositionInfoService = (PortDispositionInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PortDispositionInfoService);
			PortConfigInfo portConfigInfo = new PortConfigInfo();
			List<PortConfigInfo> portConfigInfoList = new ArrayList<PortConfigInfo>() ;
			if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
				portConfigInfo.setSiteId(offLinesiteBusi.getSiteId());
				portConfigInfo.setPort(offLinesiteBusi.getPortId());
				portConfigInfoList.add(portConfigInfo);
			}else{
				portConfigInfo.setId(offLinesiteBusi.getOperId());
				portConfigInfoList = portDispositionInfoService.select(portConfigInfo);
			}
			result = dataDownLoadSaveOrUpdate(portConfigInfoList,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portDispositionInfoService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * 网元ptp配置数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @param offLinesiteBusiService service
	 * @throws Exception
	 */
	private void ptpConfigDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		TimeManageInfoService_MB timeManageInfoService = null;
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI=new PtpSiteServiceImpl();
			}else{
				//武汉
			}
			timeManageInfoService = (TimeManageInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.TimeManageInfoService);
			TimeManageInfo timeManageInfo  = new TimeManageInfo();
			List<TimeManageInfo> timeManageInfoList = null;
			if(EActionType.UPDATE.getValue() == offLinesiteBusi.getAction()){
				timeManageInfo.setId(offLinesiteBusi.getOperId());
				timeManageInfoList= timeManageInfoService.select(timeManageInfo);
			}
			result = dataDownLoadSaveOrUpdate(timeManageInfoList,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(timeManageInfoService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * 时钟源数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @param offLinesiteBusiService service
	 * @throws Exception
	 */
	private void clockSourceDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		FrequencyClockManageService_MB frequencyClockManageService = null;
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new ClockSourceCXServiceImpl();
			}else{
				//武汉
			}
			frequencyClockManageService = (FrequencyClockManageService_MB)ConstantUtil.serviceFactory.newService_MB(Services.FrequencyClockManageService);
			List<ClockSource> clockSourceList = new ArrayList<ClockSource>();
			ClockSource clockSource =new ClockSource();
			if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
				clockSource.setSiteId(offLinesiteBusi.getSiteId());
				clockSource.setPort(offLinesiteBusi.getPortId());
				clockSourceList.add(clockSource);
			}else{
				clockSource.setId(offLinesiteBusi.getOperId());
				clockSourceList = frequencyClockManageService.select(clockSource);
			}
			result = dataDownLoadSaveOrUpdate(clockSourceList,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(frequencyClockManageService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * 时钟状态数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @param offLinesiteBusiService service
	 * @throws Exception
	 */
	private void clockStatusDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		
		String result = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		FrequencyInfoNeClockService_MB frequencyInfoNeClockService = null;
	
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new FrequencySiteServiceImpl();
			}else{
				//武汉
			}
			frequencyInfoNeClockService = (FrequencyInfoNeClockService_MB) ConstantUtil.serviceFactory.newService_MB(Services.FrequencyInfo_neClockService);
			FrequencyInfoNeClock frequencyInfoNeClock = new FrequencyInfoNeClock();
			frequencyInfoNeClock.setId(offLinesiteBusi.getOperId());
			frequencyInfoNeClock = frequencyInfoNeClockService.select(frequencyInfoNeClock).get(0);
			if(EActionType.UPDATE.getValue() == offLinesiteBusi.getAction()){
				result = operationServiceI.excutionUpdate(frequencyInfoNeClock);
			}
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(frequencyInfoNeClockService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * etree数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @param offLinesiteBusiService service
	 * @throws Exception
	 */
	private void etreeDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		EtreeInfoService_MB etreeService = null;
		try {
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new EtreeCXServiceImpl();
			}else{
				operationServiceI = new EtreeWHServiceImpl();
			}
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			EtreeInfo etreeInfo ; 
			List<EtreeInfo> actionList = new ArrayList<EtreeInfo>();
			if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
				if(offLinesiteBusi.getType().equals(TypeAndActionUtil.ACTION_ROOT)){
					String[] a = offLinesiteBusi.getParentBusiId().split(",");
					for(int i =0 ;i<a.length; i++){
						etreeInfo = new EtreeInfo(); 
						etreeInfo.setDataDownLoad(true);
						etreeInfo.setIsSingle(1);
						etreeInfo.setaSiteId(offLinesiteBusi.getSiteId());
						etreeInfo.setRootSite(offLinesiteBusi.getSiteId());
						etreeInfo.setaAcId(offLinesiteBusi.getAcId()); 
						etreeInfo.setaXcId(Integer.parseInt(offLinesiteBusi.getActionIdentify()));
						etreeInfo.setAportId(offLinesiteBusi.getPortId());
						etreeInfo.setPwId(Integer.parseInt(a[i]));
						actionList.add(etreeInfo);
					}
				}else{
					etreeInfo = new EtreeInfo(); 
					etreeInfo.setDataDownLoad(true);
					etreeInfo.setIsSingle(1);
					etreeInfo.setaSiteId(offLinesiteBusi.getSiteId());
					etreeInfo.setBranchSite(offLinesiteBusi.getSiteId());
					etreeInfo.setaAcId(offLinesiteBusi.getAcId());
					etreeInfo.setzXcId(Integer.parseInt(offLinesiteBusi.getActionIdentify()));
					etreeInfo.setAportId(offLinesiteBusi.getPortId());
					etreeInfo.setPwId(Integer.parseInt(offLinesiteBusi.getParentBusiId()));
					actionList.add(etreeInfo);
				}
				result = operationServiceI.excutionDelete(actionList);
			}else{
				etreeInfo = new EtreeInfo(); 
				etreeInfo.setServiceId(Integer.parseInt(offLinesiteBusi.getActionIdentify()));
				actionList = etreeService.select(etreeInfo);
				for(EtreeInfo etreeInfoAction:actionList){
					if(etreeInfoAction.getaSiteId()==offLinesiteBusi.getSiteId()){
						etreeInfoAction.setzSiteId(0);
						etreeInfoAction.setZportId(0);
					}
					if(etreeInfoAction.getzSiteId()==offLinesiteBusi.getSiteId()){
						etreeInfoAction.setaSiteId(0);
						etreeInfoAction.setAportId(0);
					}
					etreeInfoAction.setIsSingle(1);
				}
				if(EActionType.INSERT.getValue() == offLinesiteBusi.getAction()){
					result = operationServiceI.excutionInsert(actionList);
				}else if(EActionType.UPDATE.getValue() == offLinesiteBusi.getAction()){
					result = operationServiceI.excutionUpdate(actionList);
				}
			}
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(etreeService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * elan数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @param offLinesiteBusiService service
	 * @throws Exception
	 */
	private void elanDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		ElanInfoService_MB elanInfoService = null;
		try {
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new ELanCXServiceImpl();
			}else{
				operationServiceI = new ElanWHServiceImpl();
			}
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
			List<ElanInfo> actionList = new ArrayList<ElanInfo>();
			ElanInfo elanInfo ; 
			if(EActionType.DELETE.getValue() != offLinesiteBusi.getAction()){
				elanInfo = new ElanInfo();
				elanInfo.setServiceId(Integer.parseInt(offLinesiteBusi.getActionIdentify()));
				actionList = elanInfoService.select(elanInfo);
				if(0<actionList.size()){
					for(ElanInfo info:actionList){
						if(info.getaSiteId()==offLinesiteBusi.getSiteId()){
							info.setzSiteId(0);
							
						}if(info.getzSiteId()==offLinesiteBusi.getSiteId()){
							info.setaSiteId(0);
						}
						info.setIsSingle(1);
						actionList.clear();
						actionList.add(info);
					}
				}
				if(EActionType.INSERT.getValue() == offLinesiteBusi.getAction()){
					result = operationServiceI.excutionInsert(actionList);
				}else if(EActionType.UPDATE.getValue() == offLinesiteBusi.getAction()){
					result = operationServiceI.excutionUpdate(actionList);
				}
			}else{
				String[] a = offLinesiteBusi.getParentBusiId().split(",");
				for(int i =0 ;i<a.length; i++){
					elanInfo = new ElanInfo();
					elanInfo.setDataDownLoad(true);
					elanInfo.setIsSingle(1);
					elanInfo.setaSiteId(offLinesiteBusi.getSiteId());
					elanInfo.setaAcBusinessId(offLinesiteBusi.getAcId()); 
					elanInfo.setAxcId(Integer.parseInt(offLinesiteBusi.getActionIdentify()));
					elanInfo.setAportId(offLinesiteBusi.getPortId());
					elanInfo.setPwBusinessId(Integer.parseInt(a[i]));
					actionList.add(elanInfo);
				}
				result = operationServiceI.excutionDelete(actionList);
			}
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(elanInfoService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * eline数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @param offLinesiteBusiService service
	 * @throws Exception
	 */
	private void elineDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		
		String result;
		ElineInfoService_MB elineService = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		try {
			if(null!=offLinesiteBusi.getType()&&!"".equals(offLinesiteBusi.getType())){
				this.oamDownLoad(offLinesiteBusi, operationServiceI ,"ELINE");
			}else{
				int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
				if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
					operationServiceI = new ElineCXServiceImpl();
				}else{
					operationServiceI = new ElineWHServiceImpl();
				}
				elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
				ElineInfo elineInfo = new ElineInfo();
				List<ElineInfo> elineInfoList = new ArrayList<ElineInfo>();
				elineInfo.setId(offLinesiteBusi.getOperId());
				if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
					elineInfo.setDataDownLoad(true);
					elineInfo.setaSiteId(offLinesiteBusi.getSiteId());
					elineInfo.setIsSingle(1);
					elineInfo.setPwId(Integer.parseInt(offLinesiteBusi.getParentBusiId()));
					elineInfo.setaAcId(offLinesiteBusi.getAcId());
					elineInfo.setaXcId(Integer.parseInt(offLinesiteBusi.getActionIdentify()));
					elineInfo.setAportId(offLinesiteBusi.getPortId());
					elineInfo.setActiveStatus(EActiveStatus.NONE.getValue());
					elineInfoList.add(elineInfo);
				}else{
					elineInfoList = elineService.selectByCondition_nojoin(elineInfo);
					if(elineInfoList.size()>0){
						for(ElineInfo elineInfoAction:elineInfoList){
							if(elineInfoAction.getaSiteId()==offLinesiteBusi.getSiteId()){
								elineInfoAction.setzSiteId(0);
								elineInfoAction.setZportId(0);
							}
							if(elineInfoAction.getzSiteId()==offLinesiteBusi.getSiteId()){
								elineInfoAction.setaSiteId(0);
								elineInfoAction.setAportId(0);
							}
							elineInfoAction.setIsSingle(1);
							elineInfoList.clear();
							elineInfoList.add(elineInfoAction);
						}
					}
				}
				result = dataDownLoadSaveOrUpdate(elineInfoList,operationServiceI,offLinesiteBusi.getAction());
				offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
				if (ResultString.CONFIG_SUCCESS.equals(result)) {
					offLinesiteBusiService.delete(offLinesiteBusi);
				}
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(elineService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}

	/**
	 * ces数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @throws Exception
	 */
	private void cesDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		
		String result = null;
		List<ElineInfo> elineInfoList ;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		CesInfoService_MB cesInfoService = null;
		
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			CesInfo cesInfo = new CesInfo();
			List<CesInfo> cesInfoList = new ArrayList<CesInfo>();
			cesInfo.setId(offLinesiteBusi.getOperId());;
			if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
				cesInfo.setDataDownLoad(true);
				cesInfo.setCestype(Integer.parseInt(offLinesiteBusi.getType()));
				cesInfo.setPwId(Integer.parseInt(offLinesiteBusi.getParentBusiId()));
				cesInfo.setActiveStatus(EActiveStatus.UNACTIVITY.getValue());
				cesInfo.setaAcId(offLinesiteBusi.getPortId());
				cesInfo.setaSiteId(offLinesiteBusi.getSiteId());
				cesInfo.setServiceId(offLinesiteBusi.getOperId());
				cesInfo.setAxcId(Integer.parseInt(offLinesiteBusi.getActionIdentify()));
				cesInfo.setAportId(offLinesiteBusi.getPortId());
				cesInfo.setCestype(Integer.parseInt(offLinesiteBusi.getType()));
				cesInfo.setIsSingle(1);
				cesInfoList.add(cesInfo);
				int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
				if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
					operationServiceI = new CesCXServiceImpl();
					result = operationServiceI.excutionDelete(cesInfoList);
				}else{
					operationServiceI = new ElineWHServiceImpl();
					elineInfoList = this.cesInfoTOElineInfo(cesInfoList);
					result = operationServiceI.excutionDelete(elineInfoList);
				}
			}else{
				cesInfoList = cesInfoService.queryByCondition_nojoin(cesInfo);
				if(0<cesInfoList.size()){
					cesInfo = cesInfoList.get(0);
					if(cesInfo.getaSiteId()==offLinesiteBusi.getSiteId()){
						cesInfoList.get(0).setzSiteId(0);
						cesInfoList.get(0).setZportId(0);
					}
					if(cesInfo.getzSiteId()==offLinesiteBusi.getSiteId()){
						cesInfoList.get(0).setaSiteId(0);
						cesInfoList.get(0).setAportId(0);
					}
					cesInfoList.get(0).setIsSingle(1);
				}
				int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
				if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
					operationServiceI = new CesCXServiceImpl();
					if(EActionType.INSERT.getValue() == offLinesiteBusi.getAction()){
						result = operationServiceI.excutionInsert(cesInfoList);
					}else if(EActionType.UPDATE.getValue() == offLinesiteBusi.getAction()){
						result = operationServiceI.excutionUpdate(cesInfoList.get(0));
					}
				}else{
					operationServiceI = new ElineWHServiceImpl();
					elineInfoList = this.cesInfoTOElineInfo(cesInfoList);
					if(EActionType.INSERT.getValue() == offLinesiteBusi.getAction()){
						result = operationServiceI.excutionInsert(elineInfoList);
					}else if(EActionType.UPDATE.getValue() == offLinesiteBusi.getAction()){
						result = operationServiceI.excutionUpdate(elineInfoList.get(0));
					}
				}
			}
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(cesInfoService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}

	/**
	 * 武汉（ces业务向eline业务转换）
	 * 
	 * @param cesInfo
	 * @return
	 */
	private List<ElineInfo> cesInfoTOElineInfo(List<CesInfo> cesInfoList) {
		List<ElineInfo> elineInfoList = new ArrayList<ElineInfo>();
		for (CesInfo cesInfo : cesInfoList) {
			ElineInfo elineInfo = new ElineInfo();
			elineInfo.setName(cesInfo.getName());
			elineInfo.setActiveStatus(cesInfo.getActiveStatus());
			elineInfo.setPwId(cesInfo.getPwId());
			elineInfo.setIsSingle(cesInfo.getIsSingle());
			elineInfo.setServiceType(EServiceType.ELINE.getValue());
			elineInfo.setCestype(3);
			elineInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
			elineInfo.setCreateUser(cesInfo.getCreateUser());
			elineInfo.setaSiteId(cesInfo.getaSiteId());
			elineInfo.setAportId(cesInfo.getAportId());
			elineInfo.setaXcId(elineInfo.getaXcId());
			elineInfo.setzSiteId(cesInfo.getzSiteId());
			elineInfo.setZportId(cesInfo.getZportId());
			elineInfo.setzXcId(elineInfo.getzXcId());
			elineInfoList.add(elineInfo);
		}
		return elineInfoList;
	}
	/**
	 * pw数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @throws Exception
	 */
	private void pwDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result;
		//oam操作
		PwInfoService_MB pwInfoService  = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		try {
			if(null!=offLinesiteBusi.getType()&&!"".equals(offLinesiteBusi.getType())&&"".equals(offLinesiteBusi.getParentBusiId())){
				this.oamDownLoad(offLinesiteBusi, operationServiceI,"PW");
			}else{
				int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
				if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
					operationServiceI = new PwCXServiceImpl();
				}else{
					operationServiceI = new PwWHServiceImpl();
				}
				pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
				List<PwInfo> pwInfoList = new ArrayList<PwInfo>();
				PwInfo pwInfo = new PwInfo();
				if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
					if(Integer.parseInt(offLinesiteBusi.getType())==EPwType.ETH.getValue()){
						pwInfo.setType(EPwType.ETH);
					}else if(Integer.parseInt(offLinesiteBusi.getType())==EPwType.PDH.getValue()){
						pwInfo.setType(EPwType.PDH);
					}else if(Integer.parseInt(offLinesiteBusi.getType())==EPwType.SDH.getValue()){
						pwInfo.setType(EPwType.SDH);
					}
					pwInfo.setIsSingle(1);
					pwInfo.setType(EPwType.forms(Integer.parseInt(offLinesiteBusi.getType())));
					//		pwInfo.setAPortId(offLinesiteBusi.getOperId());
					pwInfo.setASiteId(offLinesiteBusi.getSiteId());
					pwInfo.setApwServiceId(Integer.parseInt(offLinesiteBusi.getActionIdentify()));
					pwInfo.setPwStatus(2);
					pwInfoList.add(pwInfo);
				}else{
					pwInfo.setPwId(offLinesiteBusi.getOperId());
					pwInfo = pwInfoService.selectBypwid_notjoin(pwInfo);
					if(pwInfo.getASiteId()==offLinesiteBusi.getSiteId()){
						pwInfo.setZSiteId(0);
					}
					if(pwInfo.getZSiteId()==offLinesiteBusi.getSiteId()){
						pwInfo.setASiteId(0);
					}
					pwInfo.setIsSingle(1);
					pwInfoList.add(pwInfo);
				}
				result = dataDownLoadSaveOrUpdate(pwInfoList,operationServiceI,offLinesiteBusi.getAction());
				offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
				if (ResultString.CONFIG_SUCCESS.equals(result)) {
					offLinesiteBusiService.delete(offLinesiteBusi);
				}
			}
		   } catch (Exception e) {
			 throw e;
		    }finally{
			 UiUtil.closeService_MB(pwInfoService);
		     UiUtil.closeService_MB(offLinesiteBusiService);
		 }
	} 
	/**
	 * tunnel数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @throws Exception
	 */
	private void tunnelDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		
		String result;
		OffLinesiteBusiService_MB offLinesiteBusiService  = null;
		TunnelService_MB tunnelService = null;
		
		try {
			if(null!=offLinesiteBusi.getType()&&!"".equals(offLinesiteBusi.getType())){
				this.oamDownLoad(offLinesiteBusi, operationServiceI,"TUNNEL");
			}else{
				int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
				if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
					operationServiceI = new TunnelCXServiceImpl();
				}else{
					operationServiceI = new TunnelWHServiceImpl();
				}
				tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
				List<Tunnel> tunnelList = new ArrayList<Tunnel>();
				Tunnel tunnel = new Tunnel();
				tunnel.setDataDownLoad(true);
				if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
					tunnel.setIsSingle(1);
					tunnel.setTunnelStatus(2);
					tunnel.setASiteId(offLinesiteBusi.getSiteId());
					tunnel.setAPortId(offLinesiteBusi.getPortId());
					tunnel.setTunnelType(UiUtil.getCodeByValue("PROTECTTYPE", "1").getId()+"");
					List<Lsp> lspList = new ArrayList<Lsp>();
					Lsp lsp = new Lsp();
					lsp.setAtunnelbusinessid(Integer.parseInt(offLinesiteBusi.getActionIdentify()));
					lsp.setASiteId(offLinesiteBusi.getSiteId());
					lsp.setAPortId(offLinesiteBusi.getPortId());
					lspList.add(lsp);
					tunnel.setLspParticularList(lspList);
					tunnelList.add(tunnel);
				}else {
					tunnel.setTunnelId(offLinesiteBusi.getOperId());
					tunnelList = tunnelService.select_nojoin(tunnel);
					if(0<tunnelList.size()){
						tunnel = tunnelList.get(0);
						if(tunnel.getASiteId()==offLinesiteBusi.getSiteId()){
							tunnel.setZSiteId(0);
							tunnel.setZPortId(0);
						}
						if(tunnel.getZSiteId()==offLinesiteBusi.getSiteId()){
							tunnel.setASiteId(0);
							tunnel.setAPortId(0);
						}
					}
					tunnel.setIsSingle(1);
					tunnelList.clear();
					tunnelList.add(tunnel);
				}
				result = dataDownLoadSaveOrUpdate(tunnelList,operationServiceI,offLinesiteBusi.getAction());
				offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
				if (ResultString.CONFIG_SUCCESS.equals(result)) {
					offLinesiteBusiService.delete(offLinesiteBusi);
				}
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
		
	}
	/**
	 * ac数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @throws Exception
	 */
	private void acDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		AcPortInfoService_MB acInfoService = null;
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new AcCXServiceImpl();
			}else{
				operationServiceI=new AcWHServiceImpl();
			}
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			List<AcPortInfo> acPortInfoList = new ArrayList<AcPortInfo>();
			AcPortInfo acPortInfo = new AcPortInfo();
			acPortInfo.setId(offLinesiteBusi.getOperId());
			acPortInfo.setDataDownLoad(true);
			if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
				if(null!=offLinesiteBusi.getParentBusiId()&&!"".equals(offLinesiteBusi.getParentBusiId())){
					acPortInfo.setLagId(Integer.parseInt(offLinesiteBusi.getParentBusiId()));
				}
				acPortInfo.setPortId(offLinesiteBusi.getPortId());
				acPortInfo.setSiteId(offLinesiteBusi.getSiteId());
				acPortInfo.setAcBusinessId(Integer.parseInt(offLinesiteBusi.getActionIdentify()));
				acPortInfoList.add(acPortInfo);
			}else{
				acPortInfoList = acInfoService.selectByCondition(acPortInfo);
			}
			result = dataDownLoadSaveOrUpdate(acPortInfoList,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(acInfoService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * lag数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @throws Exception
	 */
	private void lagDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result;
		PortLagService_MB portLagService = null;
		PortService_MB portService = null;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		try {
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI=new PortLagCXServiceImpl();
			}else{
				operationServiceI=new PortLagWHServiceImpl();
			}
			portLagService = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			offLinesiteBusiService = (OffLinesiteBusiService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			List<PortLagInfo> portLagInfoList = new ArrayList<PortLagInfo>();
			PortLagInfo portLagInfo = new PortLagInfo();
			OffLinesiteBusi offLinesiteBusiSel;
			List<OffLinesiteBusi> offLinesiteBusiList = null;
			List<PortInst> portList ;
			PortInst portInst ;
			if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
				portInst = new PortInst();
				portList = new ArrayList<PortInst>();
				offLinesiteBusiSel = new OffLinesiteBusi();
				offLinesiteBusiList = new ArrayList<OffLinesiteBusi>();
				offLinesiteBusiSel.setSiteId(offLinesiteBusi.getSiteId());
				offLinesiteBusiSel.setAction(offLinesiteBusi.getAction());
				offLinesiteBusiSel.setOperType(offLinesiteBusi.getOperType());
				offLinesiteBusiSel.setActionIdentify(offLinesiteBusi.getActionIdentify());
				offLinesiteBusiList = offLinesiteBusiService.selectByCondition(offLinesiteBusiSel);
			    if(offLinesiteBusiList!=null && offLinesiteBusiList.size()!=0){
					for (OffLinesiteBusi offObj : offLinesiteBusiList) {
						portInst = portService.selectPortybyid(offObj.getPortId());
						if(null!=portInst){
							portList.add(portInst);
						}
					}
			    }
				portLagInfo.setSiteId(offLinesiteBusi.getSiteId());
				portLagInfo.setLagID(Integer.parseInt(offLinesiteBusi.getActionIdentify()));
				portLagInfo.setMeangerStatus(UiUtil.getCodeByValue("ENABLEDSTATUE", "0").getId());
				portLagInfo.setDataDownLoad(true);
				portLagInfo.setPortList(portList);
				portLagInfoList.add(portLagInfo);
			}else {
				portLagInfo.setId(offLinesiteBusi.getOperId());;
				portLagInfoList =  portLagService.selectByCondition(portLagInfo);
			}
			result = dataDownLoadSaveOrUpdate(portLagInfoList,operationServiceI,offLinesiteBusi.getAction());
			
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
					for (OffLinesiteBusi delObj : offLinesiteBusiList) {
						offLinesiteBusiService.delete(delObj);
					}
				}else{
					offLinesiteBusiService.delete(offLinesiteBusi);
				}
				
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(portLagService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * sdh时隙数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @throws Exception
	 */
	private void sdhStmDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		
		String result;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		PortStmTimeslotService_MB porStmTimeslotService = null;
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			porStmTimeslotService = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new PortStmTimeslotCXServiceImpl();
			}else{
				//武汉sdh端口转换类
			}
			PortStmTimeslot portStmTimeslot = porStmTimeslotService.selectById(offLinesiteBusi.getOperId());
			List<PortStmTimeslot> list = new ArrayList<PortStmTimeslot>();
			list.add(portStmTimeslot);
			result = dataDownLoadSaveOrUpdate(list,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(porStmTimeslotService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * shd数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @throws Exception
	 */
	private void sdhDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		PortStmService_MB portStmService = null;
		try {
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new PortStmCXServiceImpl();
			}else{
				//武汉sdh端口转换类
			}
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			portStmService = (PortStmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTM);
			List<PortStm> portStmList = new ArrayList<PortStm>();
			PortStm portStm = new PortStm();
			portStm.setId(offLinesiteBusi.getOperId());;
			portStmList = portStmService.queryByCondition(portStm);
			result = dataDownLoadSaveOrUpdate(portStmList,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portStmService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * pdh数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @throws Exception
	 */
	public void pdhDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		
		String result;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		E1InfoService_MB e1InfoService = null;
		
		try {
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new E1CXServiceImpl();
			}else{
				operationServiceI = new E1WHServiceImpl(); 
			}
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			e1InfoService = (E1InfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.E1Info);
			E1Info e1Info = new E1Info();
			List<E1Info> list = new ArrayList<E1Info>();
			e1Info.setId(offLinesiteBusi.getOperId());
			list = e1InfoService.selectByCondition(e1Info);
			e1Info.setDataDownLoad(true);
			e1Info = list.get(0);
			result = operationServiceI.excutionUpdate(list);
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(e1InfoService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * eth数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @throws Exception
	 */
	private void ethDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		
		String result;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		PortService_MB portService = null;
		
		try {
			if(null!=offLinesiteBusi.getType()&&!"".equals(offLinesiteBusi.getType())){
				if(offLinesiteBusi.getType().equals(OamTypeEnum.LINKOAM.getValue()+"")){
					this.oamLinkDownLoad(offLinesiteBusi, operationServiceI);
				}else{
					this.oamDownLoad(offLinesiteBusi, operationServiceI,"ETH");
				}
			}else{
				offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
				PortInst portInst = new PortInst();
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				List<PortInst> portList = new ArrayList<PortInst>();
				portInst.setPortId(offLinesiteBusi.getOperId());
				portList = portService.select(portInst);
				portInst = portList.get(0);
				int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
				if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
					if(portInst.getPortType().equalsIgnoreCase(TypeAndActionUtil.TYPE_UNI)){
						operationServiceI = new UNICXServiceImpl();
					}else if(portInst.getPortType().equalsIgnoreCase(TypeAndActionUtil.TYPE_NNI)){
						operationServiceI = new NNICXServiceImpl();
					} else {
						operationServiceI = new PortCXServiceImpl();
					}
				}else{
					operationServiceI = new PortWHServiceImpl(); 
				}
				result = dataDownLoadSaveOrUpdate(portList,operationServiceI,offLinesiteBusi.getAction());
				if (ResultString.CONFIG_SUCCESS.equals(result)) {
					offLinesiteBusiService.delete(offLinesiteBusi);
				}
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * ospf数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @throws Exception
	 */
	private void ospfDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		OSPFInterfaceService_MB ospfInterfaceService = null;
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			operationServiceI = new OSPFInterfaceCXServiceImpl();
			ospfInterfaceService = (OSPFInterfaceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OSPFINTERFACE);
			List<OSPFInterface> ospfInterfaceList = new ArrayList<OSPFInterface>();
			OSPFInterface ospfInterface = new OSPFInterface();
			ospfInterface.setId(offLinesiteBusi.getOperId());
			ospfInterface.setNeId(offLinesiteBusi.getSiteId()+"");
			if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
				ospfInterface.setInterfaceName(offLinesiteBusi.getActionIdentify());
				ospfInterfaceList.add(ospfInterface);
			}else{
				ospfInterfaceList = ospfInterfaceService.queryByCondition(ospfInterface);
			}
			result = dataDownLoadSaveOrUpdate(ospfInterfaceList,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(ospfInterfaceService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * mcn数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @throws Exception
	 */
	private void mcnDownLoad(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		MCNService_MB mcnService = null;
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			operationServiceI = new MCNCXServiceImpl();
			mcnService = (MCNService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MCN);
			List<MCN> mcnList = new ArrayList<MCN>();
			mcnList = mcnService.queryBySiteId(offLinesiteBusi.getSiteId());
			result = dataDownLoadSaveOrUpdate(mcnList,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(mcnService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * 网元配置数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @throws Exception
	 */
	private void siteConfig(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		SiteService_MB siteService = null;
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			int manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new SiteCXServiceImpl(); 
			}else{
				operationServiceI = new SiteWHServiceImpl(); 
			}
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			SiteInst siteInst = new SiteInst();
			List<SiteInst> siteList = new ArrayList<SiteInst>();
			siteInst.setSite_Inst_Id(offLinesiteBusi.getOperId());
			siteList = siteService.select(siteInst);
			result = dataDownLoadSaveOrUpdate(siteList,operationServiceI,EActionType.UPDATE.getValue());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}
	/**
	 * 板卡数据下载
	 * @param offLinesiteBusi 离线网元业务对象
	 * @param operationServiceI	驱动实现类接口
	 * @throws Exception
	 */
	private void cardAction(OffLinesiteBusi offLinesiteBusi,OperationServiceI operationServiceI) throws Exception {
		String result;
		int manufacturer = 0;
		OffLinesiteBusiService_MB offLinesiteBusiService = null;
		CardService_MB cardService = null;
		SlotService_MB slotService = null;
		try {
			offLinesiteBusiService = (OffLinesiteBusiService_MB)ConstantUtil.serviceFactory.newService_MB(Services.OFFLINESITEBUSISERVICE);
			cardService = (CardService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CARD);
			slotService = (SlotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SLOT);
			manufacturer = super.getManufacturer(offLinesiteBusi.getSiteId());
			if (manufacturer == EManufacturer.CHENXIAO.getValue()) {
				operationServiceI = new CardCXServiceImpl(); 
			}else{
				operationServiceI = new CardWHServiceImpl(); 
			}
			CardInst cardInst = new CardInst();
			SlotInst slotInst = new SlotInst();
			cardInst.setDataDownLoad(true);
			List<CardInst> cardInstList = new ArrayList<CardInst>();
			if(EActionType.DELETE.getValue() == offLinesiteBusi.getAction()){
				cardInst.setSiteId(offLinesiteBusi.getSiteId());
				slotInst.setNumber(Integer.parseInt(offLinesiteBusi.getActionIdentify()));
				cardInst.setSlotInst(slotInst);
				cardInstList.add(cardInst);
			} else{
				cardInst.setId(offLinesiteBusi.getOperId());
				cardInstList =  cardService.select(cardInst);
				if(cardInstList.size()>0){
					cardInst = cardInstList.get(0);
					slotInst.setId(cardInst.getSlotId());
					slotInst = slotService.select(slotInst).get(0);
					cardInst.setSlotInst(slotInst);
				}
			}
			result = dataDownLoadSaveOrUpdate(cardInstList,operationServiceI,offLinesiteBusi.getAction());
			if (ResultString.CONFIG_SUCCESS.equals(result)) {
				offLinesiteBusiService.delete(offLinesiteBusi);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(cardService);
			UiUtil.closeService_MB(slotService);
			UiUtil.closeService_MB(offLinesiteBusiService);
		}
	}

	/**
	 * 操作方法调用
	 * @param list
	 * 			离线网元业务对象
	 * @param operationServiceI
	 * 				驱动实现类接口
	 * @param action
	 * 			方法
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String dataDownLoadSaveOrUpdate(List list,OperationServiceI operationServiceI,int action) throws Exception{
		String result=null;
		if(list!=null && list.size()>0){
			((ViewDataObj) list.get(0)).setDataDownLoad(true);
			if(EActionType.INSERT.getValue() == action){
				result = operationServiceI.excutionInsert(list.get(0));
			}else if(EActionType.UPDATE.getValue() == action){
				result = operationServiceI.excutionUpdate(list.get(0));
			}else if(EActionType.DELETE.getValue() == action){
				result = operationServiceI.excutionDelete(list);
			}
		}
		return result;
	}
}