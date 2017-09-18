package com.nms.snmp.ninteface.impl.inventory;

import java.util.ArrayList;
import java.util.List;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.SiteDispatch;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.wh.SiteWHServiceImpl;
import com.nms.snmp.ninteface.framework.TableHandler;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class ManagedElementTable extends TableHandler{
	public ManagedElementTable(){
		
	}
	@Override
	public boolean setInterfaceData(List<VariableBinding> vbList) {
		List<SiteInst> siteInsts = new ArrayList<SiteInst>();
		SiteService_MB siteService = null;
		String oid = "";
		String[] oids = null;
		String siteId = null;
		DispatchInterface dispath = null;
		try {
			dispath  = new SiteDispatch();
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInsts = siteService.select();
			if(vbList != null && vbList.size()>0){
				  for(VariableBinding vb : vbList){
					oid = vb.getOid().toString();
					oids = oid.trim().split("\\.");
					siteId =oids[oids.length-1];
                    for(SiteInst siteInst : siteInsts){
                      if(siteInst.getSite_Inst_Id() == Integer.parseInt(siteId)){
                    	 siteInst.setCellId(vb.getVariable().toString().split(";")[1]);
                    	 dispath.excuteInsert(siteInst);
                   }
                 }			
			  }
			}
		} catch (Exception e) {
			 ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return true;
	}
	@Override
	public Object getInterfaceData(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		List<SiteInst> siteInsts = new ArrayList<SiteInst>();
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInsts = siteService.select();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return siteInsts;
	}

	@Override
	public void updateTable(Object obj) {
		List<SiteInst>  siteInsts = (List<SiteInst>) obj;
		SiteWHServiceImpl siteWHServiceImpl = new SiteWHServiceImpl();
		SiteInst inst = null;
		for(SiteInst siteInst: siteInsts){
			Variable[] rowValues = new Variable[11];
			if(siteInst.getVersions() == null || "".equals(siteInst.getVersions())){
				inst = siteWHServiceImpl.select(siteInst.getSite_Inst_Id());
				if(inst != null){
					rowValues[4] = new OctetString(inst.getSoftEdition());//网元软件版本
				}else{
					rowValues[4] = new OctetString("");//网元软件版本
				}
				
			}else{
				rowValues[4] = new OctetString(siteInst.getSoftEdition()==null?"":siteInst.getSoftEdition());//网元软件版本
			}
			rowValues[0] = new OctetString(siteInst.getSite_Inst_Id()+"");//网元标识符
			rowValues[1] = new OctetString(siteInst.getCellId());//网元友好名称
			rowValues[2] = new OctetString(siteInst.getCellId());//EMS本地名称
			rowValues[3] = new OctetString(siteInst.getSiteLocation());//网元本地位置
			rowValues[5] = new OctetString(siteInst.getCellType());//网元类型
			rowValues[6] = new OctetString(siteInst.getLoginstatus()==1?"CS_AVAILABLE":"CS_UNAVAILABLE");//网元的通信状态
			rowValues[7] = new OctetString("1");//网元层速率
			rowValues[8] = new OctetString(siteInst.getSiteType()==370?"VNE":"PTN DEVICE");//网元类型,虚拟或真实
			rowValues[9] = new OctetString(siteInst.getCellDescribe());//网元管理IP地址
			rowValues[10] = new OctetString("255.255.255.0");//网元类型,虚拟或真实
			
		    addRow(new OID(siteInst.getSite_Inst_Id()+""),rowValues);
		}
//		ManagedElementConvertXml con = new ManagedElementConvertXml();
//		con.getManagedEquipmentXml();
	}
	
	@Override
	public void setTable(List<VariableBinding> vbList) {
		// TODO Auto-generated method stub
		for(VariableBinding vb : vbList){
			moTable.setValue(vb);
		}
	}
}
