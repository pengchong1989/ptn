package com.nms.ui.manager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.path.SetNameRule;
import com.nms.db.bean.ptn.ARPInfo;
import com.nms.db.bean.ptn.CccInfo;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.eth.DualInfo;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.protect.LoopProtectInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.qinq.QinqInst;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.enums.ECesType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.path.NameRuleService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.keys.StringKeysTip;
/**
 * 自动命名
 * @author dzy
 *
 */
public class AutoNamingUtil {
	/**
	 * 自动命名
	 * @param businessType  业务类型
	 * @param portInst_a a段端口	
	 * @param portInst_z z端端口
	 * @return
	 * @throws Exception
	 */
	public Object autoNaming(Object businessType,PortInst portInst_a,PortInst portInst_z) throws Exception {
		String result = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = simpleDateFormat.format(new Date());
		if(businessType instanceof Segment){
			result = segmentAction(portInst_a,portInst_z);
		}
		if(businessType instanceof Tunnel){
			result = tunnelAction(businessType,portInst_a,portInst_z,time);
		}
		if(businessType instanceof PwInfo){
			result = pwAction(businessType,portInst_a,portInst_z,time);
		}
		if(businessType instanceof CesInfo){
			result = cesAction(businessType,portInst_a,portInst_z,time);
		}
		if(businessType instanceof ElineInfo){
			result=elineInfoAction(businessType,portInst_a,portInst_z,time);
		}
		if(businessType instanceof ElanInfo){
			result=elanInfoAction(businessType,portInst_a,portInst_z,time);
		}
		if(businessType instanceof EtreeInfo){
			result = etreeInfoAction(businessType,portInst_a,portInst_z,time);
		}
		if(businessType instanceof CesInfo){
			result = cesAction(businessType,portInst_a,portInst_z,time);
		}	
		if(businessType instanceof AcPortInfo){
			result = acAction(businessType,portInst_a,portInst_z,time);
		}
		if(businessType instanceof LoopProtectInfo){
			result = loopProtectAction(businessType,portInst_a,portInst_z,time);
		}
		if(businessType instanceof QinqInst){
			result = qinqAction(businessType,portInst_a,portInst_z);
		}if(businessType instanceof DualInfo){
			result = dualInfoAction(businessType,portInst_a,portInst_z,time);
		}if(businessType instanceof CccInfo){
			result = cccInfoAction(businessType,portInst_a,time);
		}
		if(businessType instanceof ARPInfo){
			result = arpAction(businessType, portInst_a, time);
		}
		return result;
	}
	
	/**
	 * 环保护
	 * @param businessType
	 * 				业务类型
	 * @param portInst_a
	 * 				a段端口	
	 * @param portInst_z
	 * 				z段端口	
	 * @param time
	 * 			时间
	 * @return
	 * @throws Exception
	 */
	private String loopProtectAction(Object businessType,
			PortInst portInst_a, PortInst portInst_z, String time) throws Exception {
		String result = null;
		//网络侧
		result = "LOOP_"+time;
		return result;
	}
	/**
	 * ac
	 * @param portInst_a A端口
	 * @param portInst_z Z端口
	 * @param time 
	 * 			时间
	 * @return
	 * @throws Exception
	 */
	private String acAction(Object businessType, PortInst portInst_a,PortInst portInst_z, String time) throws Exception {
		String result = null;
		String aSiteName = null;
		SiteService_MB siteService = null;
		try {
			AcPortInfo acPortInfo =  (AcPortInfo) businessType;
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			aSiteName = ((SiteInst) siteService.select(acPortInfo.getSiteId())).getCellId();
			result = aSiteName+"/"+portInst_a.getPortName()+"_"+time;
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return result;
	}
	/**
	 * ces
	 * @param portInst_a 
	 * 				A端口
	 * @param portInst_z 
	 * 				Z端口
	 * @param time 
	 * 			时间
	 * @return
	 * @throws Exception
	 */
	private String cesAction(Object businessType, PortInst portInst_a,PortInst portInst_z, String time) throws Exception {
		
		String result = null;
		SiteService_MB siteService = null;
		String aSiteName = null;
		try {
			
			CesInfo cesInfo =  (CesInfo) businessType;
			if(0== cesInfo.getIsSingle()){
				result = "CES_"+time;
			}else{
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				aSiteName = ((SiteInst) siteService.select(cesInfo.getaSiteId())).getCellId();
				result = "CES"+ECesType.forms(cesInfo.getCestype())+"/"+aSiteName+"_"+time;
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return result;
	}
	/**
	 * PW
	 * @param portInst_a 
	 * 				A端口
	 * @param portInst_z 
	 * 				Z端口
	 * @param time 
	 * 			时间
	 * @return
	 * @throws Exception
	 */
	private String pwAction(Object businessType, PortInst portInst_a,PortInst portInst_z, String time) throws Exception {
		
		String result = null;
		String aSiteName = null;
		String zSiteName = null;
		SiteService_MB siteService  = null;
		try {
			PwInfo pwInfo =  (PwInfo) businessType;
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			aSiteName = ((SiteInst) siteService.select(pwInfo.getASiteId())).getCellId();
			if(0== pwInfo.getIsSingle()){
				zSiteName = ((SiteInst) siteService.select(pwInfo.getZSiteId())).getCellId();
				result = pwInfo.getType().toString()+"/"+aSiteName +"-"+zSiteName+ "_"+time;
			}else{
				result = "PW"+pwInfo.getType().toString()+"/"+aSiteName + "_"+time;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return result;
	}
	/**
	 * Tunnel
	 * @param portInst_a A端口
	 * @param portInst_z Z端口
	 * @param time 
	 * 			时间
	 * @return
	 * @throws Exception
	 */
	private String tunnelAction(Object businessType,PortInst portInst_a, PortInst portInst_z, String time) throws Exception {
		String result = null;
		String aSiteName = null;
		String zSiteName = null;
		SiteService_MB siteService  = null;
		try {
			Tunnel tunnel = (Tunnel) businessType;
			siteService  = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			aSiteName = ((SiteInst) siteService.select(tunnel.getASiteId())).getCellId();
			if(0== tunnel.getIsSingle()){
				zSiteName = ((SiteInst) siteService.select(tunnel.getZSiteId())).getCellId();
				result = "TUNNEL/"+aSiteName +"-"+zSiteName+ "_"+time;
			}else{
				result = "TUNNEL/"+aSiteName+"_"+time;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return result;
	}
	/**
	 * 段
	 * @param portInst_a A端口
	 * @param portInst_z Z端口
	 * @param time 
	 * @return
	 * @throws Exception
	 */
	private String segmentAction(PortInst portInst_a,PortInst portInst_z) throws Exception {
		String result = "";
		SiteService_MB siteService = null;
		String aSiteName = null;
		String zSiteName = null;
		NameRuleService_MB nameRuleService = null;
		List<SetNameRule> setNameRules = null;
		try {
			if (null==portInst_a || null==portInst_z) {
				return ResourceUtil.srcStr(StringKeysTip.TIP_PORTNULL);
			} 
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			nameRuleService = (NameRuleService_MB) ConstantUtil.serviceFactory.newService_MB(Services.NAMERULESERVICE);
			SetNameRule setNameRule = new SetNameRule();
			setNameRule.setSourcename("SEGMENT");
			setNameRule.setIsUsed(1);
			setNameRules = nameRuleService.select(setNameRule);
			if(setNameRules.size()>0){//存在段的命名规则
				setNameRule = setNameRules.get(0);
				String[] strs = setNameRule.getNamerule().split(" ");
				for (int i = 0; i < strs.length; i++) {
					String str = strs[i];
					if(str.split(":")[0].equals("常量")){
						result += str.split(":")[1];
					}else if(str.split(":")[0].equals("变量")){
						if(str.split(":")[1].equals("层速率")){
							result += "Segment";
						}else if(str.split(":")[1].equals("A端")){
							aSiteName = siteService.select(portInst_a.getSiteId()).getCellId();
							result += aSiteName;
						}else if(str.split(":")[1].equals("Z端")){
							zSiteName = siteService.select(portInst_z.getSiteId()).getCellId();
							result += zSiteName;
						}else if(str.split(":")[1].equals("A端端口")){
							result += portInst_a.getPortName();
						}else if(str.split(":")[1].equals("Z端端口")){
							result += portInst_a.getPortName();
						}
					}else if(str.split(":")[0].equals("连接符")){
						result += str.split(":")[1];
					}
				}
			}else{
				aSiteName = siteService.select(portInst_a.getSiteId()).getCellId();
				zSiteName = siteService.select(portInst_z.getSiteId()).getCellId();
				result = aSiteName + "/"+portInst_a.getPortName()+"-"+zSiteName+ "/"+portInst_z.getPortName();
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(nameRuleService);
		}
		return result;
	}
	/**
	 * Eline
	 * @param businessType
	 * 				业务类型
	 * @param portInst_a
	 * 				A端口
	 * @param portInst_z
	 * 				Z端口
	 * @param time 
	 * 			时间
	 * @return
	 * @throws Exception
	 */
	private  String elineInfoAction(Object businessType,PortInst portInst_a, PortInst portInst_z, String time) throws Exception {
		String result = null;
		SiteService_MB siteService = null;
		String aSiteName = null;
		String zSiteName = null;
		ElineInfo elineInfo = (ElineInfo) businessType;
		SiteInst siteinst;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			/**
			 * ==0 多网元
			 */
			if(0== elineInfo.getIsSingle()){
				if (0==elineInfo.getaSiteId() || 0==elineInfo.getzSiteId()) {
					return ResourceUtil.srcStr(StringKeysTip.TIP_PORTNULL);
				} 
//				siteService = (SiteService) ConstantUtil.serviceFactory.newService(Services.SITE);
				aSiteName = ((SiteInst) siteService.select(elineInfo.getaSiteId())).getCellId();
				zSiteName = ((SiteInst) siteService.select(elineInfo.getzSiteId())).getCellId();
				result = "ELINE"+"/"+aSiteName +"-"+zSiteName+ "_"+time;
			}else{//单网元
//				siteService = (SiteService) ConstantUtil.serviceFactory.newService(Services.SITE);		
				siteinst = siteService.select(elineInfo.getaSiteId());		
				result="ELINE"+"/"+ siteinst.getCellId()+"_"+time;
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return result;
	}
	/**
	 * Elan
	 * @param businessType
	 * 				业务类型
	 * @param portInst_a
	 * 				A端口
	 * @param portInst_z
	 * 				Z端口
	 * @param time 
	 * 			时间
	 * @return
	 * @throws Exception
	 */
	private  String elanInfoAction(Object businessType,PortInst portInst_a, PortInst portInst_z, String time) throws Exception {
		String result = null;
		SiteService_MB siteService = null;
		ElanInfo elanInfo = (ElanInfo) businessType;
		SiteInst siteinst = null;
		try {
			/**
			 * ==0 多网元
			 */
			if(0== elanInfo.getIsSingle()){
				result = "ELAN"+"_"+time;
			}else{//单网元
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);		
				siteinst = siteService.select(elanInfo.getaSiteId());		
				result="ELAN"+"/"+ siteinst.getCellId()+"_"+time;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return result;
	}
	/**
	 * Etree
	 * @param businessType
	 * 				业务类型
	 * @param portInst_a
	 * 				A端口
	 * @param portInst_z
	 * 				Z端口
	 * @param time 
	 * 			时间
	 * @return
	 * @throws Exception
	 */
	private  String etreeInfoAction(Object businessType,PortInst portInst_a, PortInst portInst_z, String time) throws Exception {
		
		String result = null;
		SiteService_MB siteService = null;
		EtreeInfo etreeInfo = (EtreeInfo) businessType;
		
		try {
			/**
			 * ==0 多网元
			 */
			if(0== etreeInfo.getIsSingle()){
				result = "ETREE"+"_"+time;
			}else{//单网元
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				SiteInst siteinst = siteService.select(etreeInfo.getaSiteId());		
				result="ETREE"+"/"+ siteinst.getCellId()+"_"+time;
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return result;
	}

	
	/**
	 * QinQ
	 * @param portInst_a A端口
	 * @param portInst_z Z端口
	 * @return
	 * @throws Exception
	 */
	private  String qinqAction(Object businessType,PortInst portInst_a,PortInst portInst_z) throws Exception {
		String result = null;
		String aSiteName = null;
		String zSiteName = null;
		SiteService_MB siteService = null;
		
		try {
			QinqInst qinq = (QinqInst)businessType;
			siteService  = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			aSiteName = ((SiteInst) siteService.select(qinq.getaSiteId())).getCellId();
			zSiteName = ((SiteInst) siteService.select(qinq.getzSiteId())).getCellId();
			result = "QinQ/"+aSiteName +"-"+zSiteName+ "_"+System.currentTimeMillis();
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return result;
	}

	/**
	 * dualinfo
	 * @param businessType
	 * @param portInst_a
	 * @param portInst_z
	 * @param time 
	 * @return
	 * @throws Exception
	 */
	private  String dualInfoAction(Object businessType,PortInst portInst_a, PortInst portInst_z, String time) throws Exception {
		String result = null;
		SiteService_MB siteService = null;
		DualInfo dualInfo = (DualInfo) businessType;
		
		try {
			/**
			 * ==0 多网元
			 */
			if(0== dualInfo.getIsSingle()){
				result = "dual"+"_"+time;
			}else{//单网元
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				SiteInst siteinst = siteService.select(dualInfo.getaSiteId());		
				result="dual"+"/"+ siteinst.getCellId()+"_"+time;
			}
			
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return result;
	}
	
	/**
	 * cccinfo
	 * @param businessType
	 * @param portInst_a
	 * @param portInst_z
	 * @param time 
	 * @return
	 * @throws Exception
	 */
	private  String cccInfoAction(Object businessType,PortInst portInst_a,  String time) throws Exception {
		String result = null;
		SiteService_MB siteService = null;
		CccInfo cccInfo = (CccInfo) businessType;
		
		try {
			/**
			 * ==0 多网元
			 */
	         //单网元
				siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
				SiteInst siteinst = siteService.select(cccInfo.getaSiteId());		
				result="ccc"+"/"+ siteinst.getCellId()+"_"+time;						
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return result;
	}
	
	private String arpAction(Object businessType, PortInst portInst_a, String time) throws Exception {
		String result = null;
		SiteService_MB siteService = null;
		ARPInfo info = (ARPInfo) businessType;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			SiteInst siteinst = siteService.select(info.getSiteId());		
			result="arp"+"/"+ siteinst.getCellId()+"_"+time;						
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return result;
	}
}