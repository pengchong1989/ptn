package com.nms.snmp.ninteface.impl.inventory;

import java.util.List;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.path.Segment;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.SegmentDispatch;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.snmp.ninteface.framework.TableHandler;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class TopologicalLinkTable extends TableHandler{

	@Override
	public Object getInterfaceData(List<VariableBinding> vbList) { 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setInterfaceData(List<VariableBinding> vbList) {
		 String segmentId = "";
		 String oid = "";
		 String[] oids = null;
		 DispatchInterface dispath = null;
		 SegmentService_MB segmentService = null;
		 try {
		  segmentService = (SegmentService_MB)ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
		  List<Segment> segmentList = segmentService.select();
		  dispath = new SegmentDispatch();
		  if(vbList != null && vbList.size()>0){
		   for(VariableBinding vb : vbList){
			oid = vb.getOid().toString();
			oids = oid.trim().split("\\.");
			segmentId =oids[oids.length-1];
	      	if(segmentList != null && segmentList.size() >0){
				for(Segment segment : segmentList){
				  if(segment.getId() == Integer.parseInt(segmentId)){
					 segment.setNAME(vb.getVariable().toString().split(";")[1]);
					 dispath.excuteUpdate(segment);
			  }
			}
		  }
		}
	  }
	} catch (Exception e) {
		 ExceptionManage.dispose(e,this.getClass());
	} finally {
		UiUtil.closeService_MB(segmentService);
	}
	return true;
}

	@Override
	public void setTable(List<VariableBinding> vbList) {
		
		for (VariableBinding vb : vbList) {
			moTable.setValue(vb);
		}
	}

	@Override
	public void updateTable(Object obj) {
		Segment segment = null;
		SegmentService_MB segmentService = null;
		try {
			segmentService = (SegmentService_MB)ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			List<Segment> segmentList = segmentService.select();
			if(segmentList != null && segmentList.size() >0){
				for(int i = 0; i < segmentList.size(); i++){
				   try {
						  segment = segmentList.get(i);
						  Variable[] variables = new Variable[]{
						    new OctetString(segment.getId()+""),
						    new OctetString(segment.getNAME()),
						    new OctetString("D_BIDIRECTIONAL"),
						    new OctetString((short) ELayerRate.TOPOLOGICALLINK.getValue()+""),
						    new OctetString("aSite="+segment.getShowSiteAname()+"/aPort="+segment.getShowPortAname()),
						    new OctetString("zSite="+segment.getShowSiteZname()+"/zPort="+segment.getShowPortZname()),
						  };
						  addRow(new OID(segment.getId()+""), variables);
					} catch (Exception e) {
						ExceptionManage.dispose(e,this.getClass());
					}
				}
			}
			
			//生成xml文件
//			TopologicalLinkConverXml topologicalLinkConverXml = new TopologicalLinkConverXml();
//			topologicalLinkConverXml.getTopologicalLinkXml(null, segmentList);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(segmentService);
		}
	}
}
