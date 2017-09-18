package com.nms.corba.ninterface.impl.topo;

import globaldefs.ConnectionDirection_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesIterator_IHolder;
import globaldefs.NamingAttributesList_THolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import topologicalLink.TLCreateData_T;
import topologicalLink.TopologicalLinkIterator_IHolder;
import topologicalLink.TopologicalLinkList_THolder;
import topologicalLink.TopologicalLink_T;
import topologicalLink.TopologicalLink_THolder;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.resource.NamingAttributesIterator_Impl;
import com.nms.corba.ninterface.impl.resource.TopologicalLinkIterator_Impl;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.bean.system.Field;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.system.FieldService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.SegmentDispatch;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class TopoLinkProxy {
	private Logger LOG = Logger.getLogger(TopoLinkProxy.class.getName());
	private ICorbaSession session;
	
	public TopoLinkProxy(ICorbaSession userSession) {
		session = userSession;
	}

	/**
	 * @author guoqc
	 * 获取该域下所有网元的所有段的信息
	 * @param subnetName
	 * @param howMany
	 * @param topoList
	 * @param topoIt
	 */
	public void getAllTopologicalLinks(NameAndStringValue_T[] subnetName,int howMany, 
									   TopologicalLinkList_THolder topoList,
									   TopologicalLinkIterator_IHolder topoIt) {
		try {
			//查询该域下所有网元的所有段
			List<Segment> segmentList = this.getAllSegmentByFiledName(subnetName);
			//数据转换
			if(segmentList.size() > 0){
				topoList.value = new TopologicalLink_T[segmentList.size()];
				this.convertAllSegm2Corba(segmentList, topoList.value);
			}
			//迭代
			TopologicalLinkIterator_Impl iter = new TopologicalLinkIterator_Impl(this.session);
			iter.setIterator(topoIt, topoList, howMany);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	/**
	 * 获取该域下所有网元的所有段的信息
	 */

	private List<Segment> getAllSegmentByFiledName(NameAndStringValue_T[] subnetName) {
		List<Segment> segmentList = new ArrayList<Segment>();
		if(subnetName != null && subnetName.length > 0){
			String fieldName = subnetName[0].name;
			List<Field> fieldList = this.getFieldList(fieldName);
			if(fieldList.size() > 0){
				List<SiteInst> siteList = this.getSiteList(fieldList);
				if(siteList.size() > 0){
					return this.getSegmentList(siteList);
				}
			}
		}
		return segmentList;
	}

	/**
	 * 根据域名查询域信息
	 */
	private List<Field> getFieldList(String fieldName) {
		List<Field> fieldList = new ArrayList<Field>();
		FieldService_MB fieldService = null;
		try {
		    fieldService = (FieldService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Field);
			return fieldService.selectfieldidByName(fieldName);
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			UiUtil.closeService_MB(fieldService);
		}
		return fieldList;
	}

	/**
	 * 根据域id查询该域下的所有网元
	 */
	private List<SiteInst> getSiteList(List<Field> fieldList) {
		List<SiteInst> siteList = new ArrayList<SiteInst>();
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			SiteInst site = new SiteInst();
			site.setFieldID(fieldList.get(0).getId());
			return siteService.selectByFieldId(site);
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return siteList;
	}

	/**
	 * 根据网元信息查找该网元上的所有段信息
	 */
	private List<Segment> getSegmentList(List<SiteInst> siteList) {
		List<Segment> segmentList = new ArrayList<Segment>();
		SegmentService_MB segmentService = null;
		try {
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			for (SiteInst s : siteList) {
				List<Segment> sList = segmentService.selectBySite(s.getSite_Inst_Id());
				if(sList.size() > 0){
					segmentList.addAll(sList);
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}finally{
			UiUtil.closeService_MB(segmentService);
		}
		return segmentList;
	}
	
	/**
	 * 拓扑连接标识符；
	 * 拓扑连接友好名称；（*）
	 * 拓扑连接方向（单向或双向）；
	 * A终端点；
	 * Z终端点；
	 * 拓扑连接的层速率。
	 * @param segmentList
	 * @param value
	 */
	private void convertAllSegm2Corba(List<Segment> segmentList,
			                          TopologicalLink_T[] value) {
		for (int i = 0; i < segmentList.size(); i++) {
			value[i] = new TopologicalLink_T();
			value[i].userLabel = "";
			value[i].name = new NameAndStringValue_T[1];
			value[i].name[0] = new NameAndStringValue_T("name","test");
			value[i].direction = ConnectionDirection_T.CD_BI;
			value[i].aEndTP = new NameAndStringValue_T[1];
			value[i].aEndTP[0] = new NameAndStringValue_T(segmentList.get(i).getASITEID()+"", 
														  segmentList.get(i).getAPORTID()+"");
			value[i].zEndTP = new NameAndStringValue_T[1];
			value[i].zEndTP[0] = new NameAndStringValue_T(segmentList.get(i).getZSITEID()+"", 
														  segmentList.get(i).getZPORTID()+"");
			value[i].rate = 1;
		}
	}

	/**
	 * @author guoqc
	 * 获取指定的段信息
	 * @param topoLinkName
	 * @param topoLink
	 */
	public void getTopologicalLink(NameAndStringValue_T[] topoLinkName,
			                       TopologicalLink_THolder topoLink) {
		try {
			if(topoLinkName != null && topoLinkName.length > 0){
				String segmName = topoLinkName[0].name;
				List<Segment> segmentList = this.getSegmentListByName(segmName);
				if(segmentList.size() > 0){
					TopologicalLink_T[] value = new TopologicalLink_T[1];
					this.convertAllSegm2Corba(segmentList, value);
					topoLink.value = value[0];
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	/**
	 * 根据段名称获取段信息
	 */
	private List<Segment> getSegmentListByName(String segmName) {
		List<Segment> segmentList = new ArrayList<Segment>();
		SegmentService_MB segmentService  = null;
		try {
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			Segment segment = new Segment();
			segment.setName(segmName);
			List<Segment> segList = segmentService.select(segment);
			return segList;
		} catch (Exception e) {
			LOG.error(e);
		}finally{
			UiUtil.closeService_MB(segmentService);
		}
		return segmentList;
	}

	/**
	 * @author guoqc
	 * 获取该域下所有网元的所有段的名称
	 * @param subnetName
	 * @param howMany
	 * @param nameList
	 * @param nameIt
	 */
	public void getAllTopologicalLinkNames(NameAndStringValue_T[] subnetName, int howMany, 
			                               NamingAttributesList_THolder nameList,
			                               NamingAttributesIterator_IHolder nameIt) {
		try {
			//查询该域下所有网元的所有段
			List<Segment> segmentList = this.getAllSegmentByFiledName(subnetName);
			
			//数据转换
			if(segmentList.size() > 0){
				nameList.value = new NameAndStringValue_T[segmentList.size()][1];
				this.convertAllName2Corba(segmentList, nameList.value);
			}
			//迭代
			NamingAttributesIterator_Impl iter = new NamingAttributesIterator_Impl(this.session);
			iter.setIterator(nameIt, nameList, howMany);
		} catch (Exception e) {
			LOG.error(e);
		}
	}

	/**
	 * 把段的信息赋给对应的corba对象
	 */
	private void convertAllName2Corba(List<Segment> segmentList,
									  NameAndStringValue_T[][] value) {
		for (int i = 0; i < segmentList.size(); i++) {
			value[i][0] = new NameAndStringValue_T(segmentList.get(i).getNAME(), "");
		}
	}

	/**
	 * @author guoqc
	 * 删除段
	 * 入参 topoLinkName 根据段的主键id删除
	 */
	public void deleteTopologicalLink(NameAndStringValue_T[] topoLinkName) {
		if(topoLinkName != null && topoLinkName.length > 1){
			String emsType = topoLinkName[0].name;
			String manufacturer = topoLinkName[0].value;
			String segmentId = topoLinkName[1].value;
			if((CorbaConfig.getInstanse().getCorbaEmsName()).equals(manufacturer)
					&& emsType.equals(CorbaConfig.getInstanse().getEmsType())){
				Segment s = new Segment();
				s.setId(Integer.parseInt(segmentId));
				SegmentService_MB segmentService = null;
				try {
					segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
					List<Segment> deleteList = segmentService.queryByCondition(s);
					if(deleteList.size() > 0){
						SegmentDispatch dispatch = new SegmentDispatch();
						dispatch.excuteDelete(deleteList);
					}
				} catch (Exception e) {
					LOG.error(e);
				}finally{
					UiUtil.closeService_MB(segmentService);
				}
			}
		}
	}

	/**
	 * @author guoqc
	 *  创建段
	 *  入参 newTLCreateData
	 *  出参 newTopologicalLink
	 */
	public void createTopologicalLink(TLCreateData_T newTLCreateData,
										TopologicalLink_THolder newTopologicalLink) {
		if(newTLCreateData != null && newTopologicalLink != null){
			String segmentName = newTLCreateData.userLabel;//段名称
			NameAndStringValue_T[] aEndTP = newTLCreateData.aEndTP;//a端信息
			NameAndStringValue_T[] zEndTP = newTLCreateData.zEndTP;//z端信息
			NameAndStringValue_T[] qosInfo =newTLCreateData.additionalCreationInfo;//qos信息
			//创建段
			Segment s = this.setValue2Segment(segmentName, aEndTP, zEndTP, qosInfo);
			//下发数据
			String result = this.sendData(s);
			if(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS).equals(result)){
				this.setValue2Result(segmentName, aEndTP, zEndTP, qosInfo, newTLCreateData, newTopologicalLink);
			}
		}
	}

	/**
	 * 把需要创建的段信息赋值给segment
	 */
	private Segment setValue2Segment(String segmentName, NameAndStringValue_T[] aEndTP, 
			   					     NameAndStringValue_T[] zEndTP, NameAndStringValue_T[] qosInfo) {
		Segment s = new Segment();
		s.setNAME(segmentName);
//		s.setBANDWIDTH();
//		s.setTYPE();
//		s.setSpeedSegment();
		Map<Integer, List<QosQueue>> qosMap = new HashMap<Integer, List<QosQueue>>();
		List<QosQueue> aQosQueue = new ArrayList<QosQueue>();
		List<QosQueue> zQosQueue = new ArrayList<QosQueue>();
		int siteId = 0;
		int portId = 0;
		int slotNumber = 0;
		String workModel = "";
		if(aEndTP != null && aEndTP.length > 3){
			siteId = Integer.parseInt(aEndTP[0].value);
			slotNumber = Integer.parseInt(aEndTP[1].value);
			portId = Integer.parseInt(aEndTP[2].value);
			workModel = this.getWorkModel(aEndTP[3].value);
			s.setASITEID(siteId);
			s.setAPORTID(portId);
			s.setaSlotNumber(slotNumber);
			s.setSpeedSegment(workModel);
			this.getQosMap(qosMap, aQosQueue, qosInfo, siteId, portId);
		}
		if(zEndTP != null && zEndTP.length > 2){
			siteId = Integer.parseInt(zEndTP[0].value);
			slotNumber = Integer.parseInt(zEndTP[1].value);
			portId = Integer.parseInt(zEndTP[2].value);
			workModel = this.getWorkModel(zEndTP[3].value);
			s.setZSITEID(siteId);
			s.setZPORTID(portId);
			s.setzSlotNumber(slotNumber);
			s.setSpeedSegment(workModel);
			this.getQosMap(qosMap, zQosQueue, qosInfo, siteId, portId);
		}
		
		s.setQosMap(qosMap);
		return s;
	}

	/**
	 * 获取工作模式
	 * 通过工作模式,返回对应的code表主键
	 */
	private String getWorkModel(String value) {
		if("autoNegotiation".equals(value)){//自动协商
			return "70";
		}else if("1000MFullDuplex".equals(value)){//1000M全双工
			return "71";
		}else if("100MFullDuplex".equals(value)){//100M全双工
			return "371";
		}else if("10MFullDuplex".equals(value)){//10M全双工
			return "372";
		}else if("100M".equals(value)){//100M
			return "373";
		}else if("100MAutoNegotiation".equals(value)){//自协商100M
			return "374";
		}else if("1G".equals(value)){//1G
			return "376";
		}else if("1GAutoNegotiation".equals(value)){//自协商1G
			return "378";
		}else if("10G".equals(value)){//10G
			return "379";
		}else if("10GAutoNegotiation".equals(value)){//自协商10G
			return "380";
		}
		return "70";
	}

	/**
	 * 封装qos信息
	 */
	private void getQosMap(Map<Integer, List<QosQueue>> qosMap, List<QosQueue> qosQueue,
			  			   NameAndStringValue_T[] qosInfo, int siteId, int portId) {
		QosQueue qos = null;
		if(qosInfo != null && qosInfo.length > 14){
			for (int i = 0; i < 8; i++) {
				qos = new QosQueue();
				qos.setSiteId(siteId);
				qos.setObjType("SECTION");
				qos.setObjId(portId);
				qos.setQueueType(qosInfo[3].value);
				qos.setCos(Integer.parseInt(qosInfo[4].value));
				qos.setCir(Integer.parseInt(qosInfo[5].value));
				qos.setWeight(Integer.parseInt(qosInfo[6].value));
				qos.setGreenLowThresh(Integer.parseInt(qosInfo[7].value));
				qos.setGreenHighThresh(Integer.parseInt(qosInfo[8].value));
				qos.setGreenProbability(Integer.parseInt(qosInfo[9].value));
				qos.setYellowLowThresh(Integer.parseInt(qosInfo[10].value));
				qos.setYellowHighThresh(Integer.parseInt(qosInfo[11].value));
				qos.setYellowProbability(Integer.parseInt(qosInfo[12].value));
				qos.setWredEnable(Integer.parseInt(qosInfo[7].value) == 0 ? Boolean.FALSE:Boolean.TRUE);
				qos.setMostBandwidth(qosInfo[14].value);
				qosQueue.add(qos);
			}
			qosMap.put(siteId, qosQueue);
		}
	}
	
	/**
	 * 下发数据到设备
	 */
	private String sendData(Segment s) {
		try {
			SegmentDispatch dispatch = new SegmentDispatch();
			return dispatch.excuteInsert(s);
		} catch (Exception e) {
			LOG.error(e);
		}
		return "";
	}
	
	/**
	 * 给返回结果赋值
	 */
	private void setValue2Result(String segmentName, NameAndStringValue_T[] aEndTP, 
								NameAndStringValue_T[] zEndTP, NameAndStringValue_T[] qosInfo, 
								TLCreateData_T newTLCreateData, TopologicalLink_THolder newTopologicalLink) {
		newTopologicalLink.value = new TopologicalLink_T();
		newTopologicalLink.value.name = new NameAndStringValue_T[]{};
		newTopologicalLink.value.userLabel = segmentName;
		newTopologicalLink.value.nativeEMSName = segmentName;
		newTopologicalLink.value.aEndTP = aEndTP;
		newTopologicalLink.value.zEndTP = zEndTP;
		newTopologicalLink.value.additionalInfo = qosInfo;
		newTopologicalLink.value.direction = newTLCreateData.direction;
		newTopologicalLink.value.rate = newTLCreateData.rate;
		newTopologicalLink.value.owner = newTLCreateData.owner;
	}
}
