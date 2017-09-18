package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.enums.EManufacturer;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.util.Services;
import com.nms.service.OperationServiceI;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.SegmentCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.SegmentDispatchI;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.service.impl.wh.SegmentWHServiceImpl;
import com.nms.service.impl.wh.SiteWHServiceImpl;
import com.nms.service.notify.Message.MessageType;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class SegmentDispatch extends DispatchBase implements SegmentDispatchI {

	@SuppressWarnings("unchecked")
	public String excuteDelete(Object object) throws Exception {
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		String result_wh = null;
		String result_cx = null;
		SegmentService_MB segmentService = null;
		List<Segment> segments = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			segments = (List<Segment>) object;
			
			//虚拟网元操作
			/*String siteCheck =(String) SiteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_DELETE,segments);
			if(null!=siteCheck){
				return siteCheck;
			}*/
			
			// 武汉的直接操作数据库。 所以只下发晨晓的
			operationServiceI_cx = new SegmentCXServiceImpl();
			result_cx = operationServiceI_cx.excutionDelete(segments);
			operationServiceI_wh = new SegmentWHServiceImpl();
			result_wh = operationServiceI_wh.excutionDelete(segments);
			// 判断晨晓的配置是否成功， 成功了删除数据库
			if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
				for (Segment segment : segments) {
					super.notifyCorba("Segment", MessageType.DELETION, segment, "segment",ResultString.CONFIG_SUCCESS);
				}
				segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
				segmentService.deleteByIds(segments);
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationServiceI_cx = null;
			UiUtil.closeService_MB(segmentService);
			operationServiceI_wh = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result_wh)) {
//			WhImplUtil whImplUtil = new WhImplUtil();
//			for(Segment segment : segments){
//				whImplUtil.deleteAlarmAndPerformance(EObjectType.SEGMENT, segment.getId());
//			}
			//离线网元给予提示
			String str = getNotOnlineSiteIdNames(segments);
			segments = null;
			if(str.equals("")){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
		} else {
			return result;
		}
	}

	public String excuteInsert(Object object) throws Exception {
		OperationServiceI operationServiceI_wh = null;
		OperationServiceI operationServiceI_cx = null;
		Segment segment = null;
		String result_wh = null;
		String result_cx = null;
		SegmentService_MB segmentService = null;
		List<Segment> segmentList = null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		segment = (Segment) object;
		try {
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			
			// 插入数据库
			this.insertDao(segment);
			//虚拟网元操作
//		  String siteCheck =(String) SiteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_INSERT,segment);
			// 同时下发武汉和晨晓的
			operationServiceI_wh = new SegmentWHServiceImpl();
			operationServiceI_cx = new SegmentCXServiceImpl();
			result_wh = operationServiceI_wh.excutionInsert(segment);
			result_cx = operationServiceI_cx.excutionInsert(segment);

			// 判断两个下发结果，如果都成功直接返回。
			if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
				result = ResultString.CONFIG_SUCCESS;
			} else {
				// 如果有失败的，把成功的删除。
				segmentList = new ArrayList<Segment>();
				segmentList.add(segment);
				// 如果成功的是晨晓的， 把晨晓的删除。返回的错误消息是武汉的
				if (ResultString.CONFIG_SUCCESS.equals(result_cx)) {
					operationServiceI_cx.excutionDelete(segmentList);
					result = result_wh;
				} else {
					// 如果成功的是武汉的， 把武汉的删除。返回的错误消息是晨晓的
					operationServiceI_wh.excutionDelete(segmentList);
					result = result_cx;
				}
				// 删除数据库
				segmentService.delete(segment.getId());
			}
			super.notifyCorba("Segment", MessageType.CREATION, segment, "segment",result);
		} catch (Exception e) {
			segmentService.delete(segment.getId());
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationServiceI_wh = null;
			operationServiceI_cx = null;
			result_wh = null;
			result_cx = null;
			UiUtil.closeService_MB(segmentService);
			segmentList = null;
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			//离线网元给予提示
			List<Segment> list = new ArrayList<Segment>();
			list.add(segment);
			String str = getNotOnlineSiteIdNames(list);
			if(str.equals("")){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
		
		} else {
			return result;
		}
	}

	public String excuteUpdate(Object object) throws Exception {
		
		OperationServiceI operationServiceI_cx = null;
		OperationServiceI operationServiceI_wh = null;
		Segment segment = null;
		String result_cx = null;
		String result_wh= null;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		SegmentService_MB service = null;
		List<Segment> segments = null;
		try {
			segment = (Segment) object;

			//虚拟网元操作
			/*String siteCheck =(String) SiteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_UPDATE,segment);
			if(null!=siteCheck){
				return siteCheck;
			}*/
			
			//查询修改之前的拓扑连接信息
			service = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			segments = service.select(segment);
			
			// 修改数据库
			this.updateDao(segment);
			// 武汉的直接修改数据库。 所以不用下设备。
			operationServiceI_wh = new SegmentWHServiceImpl();
			result_wh = operationServiceI_wh.excutionUpdate(segment);
			operationServiceI_cx = new SegmentCXServiceImpl();
			result_cx = operationServiceI_cx.excutionUpdate(object);
			// 判断两个下发结果，如果都成功直接返回。
			if (ResultString.CONFIG_SUCCESS.equals(result_cx) && ResultString.CONFIG_SUCCESS.equals(result_wh)) {
				result = ResultString.CONFIG_SUCCESS;
			}
			
			reportMsg(segment , segments,result);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			operationServiceI_cx = null;
			UiUtil.closeService_MB(service);
		}
		if (ResultString.CONFIG_SUCCESS.equals(result)) {
			//离线网元给予提示
			List<Segment> list = new ArrayList<Segment>();
			list.add(segment);
			String str = getNotOnlineSiteIdNames(list);
			if(str.equals("")){
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
			}else{
				return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS)+","+str+ResultString.NOT_ONLINE_SUCCESS;
			}
		} else {
			return result;
		}
	}

	/**
	 * 插入db中
	 * 
	 * @param segment
	 * @throws Exception
	 */
	public void insertDao(Segment segment) throws Exception {
		SegmentService_MB segmentService = null;
		PortInst aPortInst = null;
		PortInst zPortInst = null;
		PortService_MB portService = null;
		Map<Integer, List<QosQueue>> qosMap = null;
		List<QosQueue> qosQueues = null;
		int result = 0;
		try {
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			aPortInst = new PortInst();
			zPortInst = new PortInst();
			aPortInst.setPortId(segment.getAPORTID());
			zPortInst.setPortId(segment.getZPORTID());
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			List<PortInst> aPortList = portService.select(aPortInst);
			List<PortInst> zPortList = portService.select(zPortInst);
			aPortInst = aPortList.get(0);
			zPortInst = zPortList.get(0);
			qosMap = segment.getQosMap();
			if (qosMap.values() != null && qosMap.values().size() > 0) {
				qosQueues = new ArrayList<QosQueue>();
				for (List<QosQueue> qosQueue : qosMap.values()) {
					if (qosQueue != null && qosQueue.size() > 0)
						qosQueues.addAll(qosQueue);
				}

			}
			result = segmentService.saveOrUpdate(segment, qosQueues, segment.getOamList());
			segment.setId(result);
		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
			UiUtil.closeService_MB(segmentService);
			aPortInst = null;
			zPortInst = null;
			qosMap = null;
			qosQueues = null;
		}
	}

	/**
	 * 
	 * 修改数据库段数据。
	 * 
	 * @author kk
	 * 
	 * @param 段对象
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * @Exception 异常对象
	 */
	public void updateDao(Segment segment) throws Exception {
		Map<Integer, List<QosQueue>> qosMap = null;
		List<QosQueue> qosQueues = null;
		SegmentService_MB segmentService = null;
		try {
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			qosMap = segment.getQosMap();
			if (qosMap.values() != null && qosMap.values().size() > 0) {
				qosQueues = new ArrayList<QosQueue>();
				for (List<QosQueue> qosQueue : qosMap.values()) {
					if (qosQueue != null && qosQueue.size() > 0)
						qosQueues.addAll(qosQueue);
				}

			}
			segmentService.saveOrUpdate(segment, qosQueues, segment.getOamList());
		} catch (Exception e) {
			throw e;
		} finally {
			qosMap = null;
			qosQueues = null;
			UiUtil.closeService_MB(segmentService);
		}
	}

	/**
	 * 搜索段
	 * @return
	 */
	public String searchSegment(List<SiteInst> siteInsts,List<Segment> seg) throws Exception {
		OperationServiceI operationServiceI = null;
		try {
//			for (SiteInst siteInsts1 : siteInst) {
//				
//				//虚拟网元操作
//				String siteCheck =(String) SiteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SEARCHSEGMENT,siteInst);
//				if(null!=siteCheck){
//					return siteCheck;
//				}
//				
//				PortDispatch portDispatch = new PortDispatch();
//				portDispatch.synchro(siteInsts1.getSite_Inst_Id());
//			}
			SiteUtil siteUtil=new SiteUtil();
			for (SiteInst siteInst : siteInsts) {
				//虚拟网元操作
				/*String siteCheck =(String) SiteUtil.irtualSiteAction(TypeAndActionUtil.ACTION_SEARCHSEGMENT,siteInst);
				if(null!=siteCheck){
					return siteCheck;
				}*/
				
				if (super.getManufacturer(siteInst.getSite_Inst_Id()) == EManufacturer.WUHAN.getValue()) {
					
				} else {
					operationServiceI = new SegmentCXServiceImpl(siteInsts);
					if(0==siteUtil.SiteTypeUtil(siteInst.getSite_Inst_Id())){
						operationServiceI.synchro(siteInst.getSite_Inst_Id());
				}
				}
			}
			SiteWHServiceImpl siteWHServiceImpl = new SiteWHServiceImpl();
			siteWHServiceImpl.topologicalLinkFound(siteInsts, 0,seg);
		} catch (Exception e) {
			throw e;
		}
		return ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS);
	}

	@Override
	public String synchro(int siteId) throws RemoteException, Exception {
		return null;
	}
	
	/**
	 * 判断是否符合修改上报
	 * @param tunnel 要修改的tunnel
	 * @param beforeTunnel 原有的tunnel
	 * @param result 是否上报标识
	 * @throws Exception 
	 */
	private void reportMsg(Segment segment,List<Segment> segments,String result) throws Exception{
		Map<String, String> attributeMap = new HashMap<String, String>();//上报的修改的属性集合
		if (null != segment.getNAME() && null != segments && 0!= segments.size() && null != segments.get(0).getNAME() && !segment.getNAME().equals(segments.get(0).getNAME())) {
			attributeMap.put("userLabel", segment.getNAME());
		}
		//成功上报操作信息到caoba
		try {
			//属性修改上报
			if (attributeMap.size()>0) {
				attributeMap.put("id", segment.getId()+"");
				super.notifyCorba("SubnetworkConnection", MessageType.ATTRIBUTECHG , attributeMap, ELayerRate.TUNNEL.getValue()+"",result);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public Object consistence(int siteId) throws RemoteException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getNotOnlineSiteIdNames(List<Segment> list) throws Exception {
		List<Integer> siteIds = new ArrayList<Integer>();
		for(Segment sg: list)
		{
			siteIds.add(sg.getASITEID());
			siteIds.add(sg.getZSITEID());
		}
		for( int i=0; i<siteIds.size()-1; i++) 
		{     
	      for(int j=siteIds.size() -1; j>i; j--)
	      {     
	           if(siteIds.get(j)==siteIds.get(i))
	           {     
	        	   siteIds.remove(j);     
	           }      
	        }      
		} 
		WhImplUtil wu = new WhImplUtil();
		return wu.getNeNames(siteIds);
	}
}
