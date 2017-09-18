package com.nms.snmp.ninteface.impl.inventory;

import java.util.ArrayList;
import java.util.List;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import protection.ReversionMode_T;

import com.nms.corba.ninterface.enums.EProtectionType;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.path.protect.Protect_Corba;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.protect.ProtectServiceCorba_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.TunnelDispatch;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.snmp.ninteface.framework.TableHandler;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class ProtectionGroupTable extends TableHandler {

	@Override
	public Object getInterfaceData(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setInterfaceData(List<VariableBinding> vbList) {
		String id = "";
		String oid = "";
		String[] oids = null;
	    String waitTime = "";
	    String delayTime = "";
		Tunnel tunnel  = null;
		DispatchInterface dispath = null;
		TunnelService_MB tunnelService = null;
		String value = null;
		try {
			dispath = new TunnelDispatch();
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			if(vbList != null && vbList.size()>0){
			 for(VariableBinding vb : vbList){
				oid = vb.getOid().toString();
				oids = oid.trim().split("\\.");
				id =oids[oids.length-1];
				value = vb.getVariable().toString().split(";")[1];
				//index 获取的数据格式应该跟回给snmp客户端一样的 "WaitTime:"+p.getWaitTime()+"/"+"DelayTime:"+p.getDelaytime()  12/9
				waitTime = value.substring(0,value.indexOf("/"));
				delayTime = value.substring(value.indexOf("/")+1,value.length());
				tunnel = new Tunnel();
				tunnel = tunnelService.selectByID(Integer.parseInt(id));
				tunnel.setWaittime(Integer.parseInt(waitTime));
				tunnel.setDelaytime(Integer.parseInt(delayTime));
				dispath.excuteUpdate(tunnel);
			}
		 }
		} catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(tunnelService);
		}
		return true;
	}

	@Override
	public void setTable(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		for (VariableBinding vb : vbList) {
			moTable.setValue(vb);
		}
	}

	@Override
	public void updateTable(Object obj) {
		List<Protect_Corba> protectList = new ArrayList<Protect_Corba>();
		try {
			protectList = getProtect_corba();
			for (Protect_Corba p : protectList) {
				String mode = "";
				if(!EProtectionType.LOOPPROTECT.toString().equals(p.getObjType())){
					if(p.getReversionMode()==1)//1=可回恢复
						mode = ReversionMode_T.RM_REVERTIVE.toString();
					if(p.getReversionMode()==0)//2=不可恢复
						mode = ReversionMode_T.RM_NON_REVERTIVE.toString();
				}else{
					mode = ReversionMode_T.RM_UNKNOWN.toString();
				}
				Variable[] rowValues = new Variable[] {
					new OctetString(p.getId()+""),
					//REVERTIVE、NON_REVERTIVE、UNKNOWN
					
					new OctetString(mode),
					new OctetString("1"),//层速率
					new OctetString("WaitTime:"+p.getWaitTime()+"/"+"DelayTime:"+p.getDelaytime()),//保护组参数
					new OctetString("subnetworkconnection:"+p.getPortName_work()+"/"
							+"subnetprotconnection:"+p.getPortName_pro()),//终端点列表
				};
				addRow(new OID(""+p.getId()), rowValues);
			}
			
			//生成xml文件
//			ProtectionGroupConverXml protectionGroupConverXml = new ProtectionGroupConverXml();
//			protectionGroupConverXml.getProtectionGroupXml(null, protectList);
		} catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		}
	}

	
	private List<Protect_Corba> getProtect_corba(){
		List<Protect_Corba> protectList = new ArrayList<Protect_Corba>();
		SiteService_MB siteService = null;
		ProtectServiceCorba_MB protectService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			List<SiteInst> siteList = siteService.select();
			List<Integer> siteIdList = new ArrayList<Integer>();
			for (SiteInst s : siteList) {
				if(!siteIdList.contains(s.getSite_Inst_Id())){
					siteIdList.add(s.getSite_Inst_Id());
				}
			}
			protectService = (ProtectServiceCorba_MB) ConstantUtil.serviceFactory.newService_MB(Services.PROTECT_CORBA);
			for (Integer siteId : siteIdList) {
				Protect_Corba protect_Corba = new Protect_Corba();
				protect_Corba.setSiteId(siteId);
				protectList.addAll(protectService.queryProtectByCondition(protect_Corba));
			}
		} catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(protectService);
			UiUtil.closeService_MB(siteService);
		}
		return protectList;
	}
}
