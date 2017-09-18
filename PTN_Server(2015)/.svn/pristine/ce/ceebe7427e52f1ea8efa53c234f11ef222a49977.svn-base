package com.nms.corba.ninterface.impl.security.proxy;

import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import transmissionDescriptor.QoSProfileIterator_IHolder;
import transmissionDescriptor.QoSProfileList_THolder;
import transmissionDescriptor.QoSProfile_T;
import transmissionDescriptor.QoSProfile_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.resource.proxy.QoSProfileIterator_Impl;
import com.nms.corba.ninterface.impl.security.tool.CorbaTransmissionTool;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.db.bean.ptn.path.ServiceInfo;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.bean.ptn.qos.QosRelevance;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.ptn.qos.QosInfoService_MB;
import com.nms.model.ptn.qos.QosQueueService_MB;
import com.nms.model.ptn.qos.QosRelevanceService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.AcDispatch;
import com.nms.service.impl.dispatch.ElanDispatch;
import com.nms.service.impl.dispatch.ElineDispatch;
import com.nms.service.impl.dispatch.EtreeDispatch;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

/**
 * 传输代理类
 * @author dzy
 *
 */
public class CorbaTransmissionProxy {
	
	private ICorbaSession session = null;
	private Logger LOG = Logger.getLogger(CorbaTransmissionProxy.class.getName());
	
	public CorbaTransmissionProxy(ICorbaSession userSession) {
		this.session = userSession;
	}
	
	/**
	 * @author guoqc
	 * 查询所有QoS策略描述属性
	 * 入参  how_many
	 * 出参 qosProfileList qos信息
	 * 出参 qosProfileIt 迭代器
	 * @throws ProcessingFailureException 
	 */
	public void getAllQoSProfile(int howMany,QoSProfileList_THolder qosProfileList,
									QoSProfileIterator_IHolder qosProfileIt) throws ProcessingFailureException {
		CorbaTransmissionTool corbaTransmissionTool;
		try {
			corbaTransmissionTool = new CorbaTransmissionTool();
			List<QosQueue> qosQueueList = this.getQosQueue(0);
			if(null == qosQueueList || qosQueueList.size() == 0){
				qosProfileList.value = new QoSProfile_T[0];
				return;
			}
			qosProfileList.value = new QoSProfile_T[qosQueueList.size()/8];
			//数据转换
			corbaTransmissionTool.convertQosList2Corba(qosProfileList, qosQueueList);
			//迭代
			QoSProfileIterator_Impl iter = new QoSProfileIterator_Impl(this.session);
			iter.setIterator(qosProfileList, qosProfileIt, howMany);
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getAllQoSProfile ex.");
		}
	}
	
	

	/**
	 * @author guoqc
	 * 查询某一条QoS策略描述属性
	 * 入参 queryInfo    网元ID，业务类型，主键Id
	 * 出参 arg1 qos信息
	 * @throws ProcessingFailureException 
	 */
	public void getQoSProfile(NameAndStringValue_T[] qpName, QoSProfile_THolder returnInfo) throws ProcessingFailureException {
		CorbaTransmissionTool corbaTransmissionTool;
		String[] id;
		List<QosQueue> qosQueueList;
		AcPortInfoService_MB acInfoService = null;
		QosRelevance qosRelevance;
		AcPortInfo acPortInfo;
		try {
			if(!CheckParameterUtil.checkQOSName(qpName))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			corbaTransmissionTool = new CorbaTransmissionTool();
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			id = qpName[1].value.split("/");
			if(null == returnInfo.value)
				returnInfo.value = new QoSProfile_T();
			if(0 != Integer.parseInt(id[1])){
				//获取qos  关系对象 和 ac
				qosRelevance = this.getQosRelevance(Integer.parseInt(id[1]));
				acPortInfo = acInfoService.selectById(qosRelevance.getObjId());
				if(null == qosRelevance || qosRelevance.getId() == 0 )
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
				//转换ac qos
				corbaTransmissionTool.convertQosInfo2Corba(returnInfo.value, qosRelevance.getQosInfoList().get(0),acPortInfo);
			}
			if(0 != Integer.parseInt(id[0])){
				//获取qos列队
				qosQueueList = getQosQueue(Integer.parseInt(id[0]));
				
				if(null == qosQueueList || qosQueueList.size() != 8)
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
				
				corbaTransmissionTool.convertQosQueue2Corba(returnInfo.value, qosQueueList);
			}
			returnInfo.value.name = qpName;
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getQoSProfile ex.");
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}
	}
	
	/**
	 * @author guoqc
	 * 修改某一条QoS策略描述属性
	 * 入参  modifyQPData 需要修改的qos信息
	 * 出参  qosprofile 修改后的qos信息
	 * @throws Exception 
	 */
	public void modifyQoSProfile(QoSProfile_T modifyQPData, QoSProfile_THolder qosprofile) throws Exception {
		CorbaTransmissionTool corbaTransmissionTool;
		List<QosInfo> qosList; 
		QosInfoService_MB qosService = null;
		QosInfo qosInfo;
		String[] id;
		QosRelevance qosRelevance = null;
		AcPortInfo acPortInfo;
		AcPortInfoService_MB acInfoService = null;
		List<QosQueue> qosQueueList;
		QosQueueService_MB qosQueueService = null;
		try {
			if(null == modifyQPData)
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			corbaTransmissionTool = new CorbaTransmissionTool();
			id = modifyQPData.name[1].value.split("/");
			//转化输出CORBA数据
			if(null == qosprofile.value)
				qosprofile.value = new QoSProfile_T();
			//入口策略  ac
			if(null != modifyQPData.ingressStrategy && modifyQPData.ingressStrategy.length > 0){
				qosInfo = new QosInfo();
				qosList = new ArrayList<QosInfo>(); 
				qosService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
				acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
				qosInfo.setId(Integer.parseInt(id[1]));
				qosList = qosService.queryByCondition(qosInfo);
				corbaTransmissionTool.converCorba2QOS(modifyQPData, qosList);
				qosService.updateQos(qosList);
				qosRelevance = this.getQosRelevance(Integer.parseInt(id[1]));
				//下发设备
//			this.updateQOSDispatch(qosRelevance);
				
				acPortInfo = acInfoService.selectById(qosRelevance.getObjId());
				corbaTransmissionTool.convertQosInfo2Corba(qosprofile.value, qosList.get(0), acPortInfo);
			}
			//出口策略 端口QOS策略
			if(null != modifyQPData.egressStrategy && (null != modifyQPData.egressStrategy.queueSchedule || 
					null != modifyQPData.egressStrategy.dropStrategy)){
				qosQueueService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue);
				qosQueueList = qosQueueService.queryByIdWithUNI(Integer.parseInt(id[0]));
				corbaTransmissionTool.converCorba2QOSQueue(modifyQPData, qosQueueList);
				qosQueueService.saveOrUpdate(qosQueueList);
				corbaTransmissionTool.convertQosQueue2Corba(qosprofile.value, qosQueueList);
			}
			qosprofile.value.name = modifyQPData.name;
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			UiUtil.closeService_MB(qosService);
			UiUtil.closeService_MB(acInfoService);
			UiUtil.closeService_MB(qosQueueService);
		}
	}

	/**
	 * @author guoqc
	 * 删除一条QoS策略描述属性
	 * arg0  需要删除的qos信息
	 * @throws ProcessingFailureException 
	 */
	public void deleteQoSProfile(NameAndStringValue_T[] qpName) throws ProcessingFailureException {
		String[] id;
		QosRelevance qosRelevance;
		if(!CheckParameterUtil.checkQOSName(qpName))
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		id = qpName[1].value.split("/");
		try {
			qosRelevance = this.getQosRelevance(Integer.parseInt(id[1]));
		} catch (NumberFormatException e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
		}
		this.deleteQosInfo(qosRelevance);
	}

	/**
	 * 获取QOS关系
	 * @param id	qosId
	 * @return
	 * @throws ProcessingFailureException 
	 * @throws Exception 
	 */
	private QosRelevance getQosRelevance(int id) throws ProcessingFailureException{
		QosRelevance qosRele = new QosRelevance();
		List<QosInfo> qosList;
		QosRelevanceService_MB qosReleService = null;
		List<QosRelevance> qosreleList;
		QosInfo qos = new QosInfo();
		qos.setId(id);
		QosInfoService_MB qosService = null;
		try {
			qosService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
			qosList = qosService.queryByCondition(qos);
		
			if(null == qosList || qosList.size() != 1 )
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
			
			try {
				qosReleService = (QosRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSRELEVANCE);
				qosRele.setQosGroupId(qosList.get(0).getGroupId());
				qosreleList = qosReleService.selectFor700Plus(qosRele);
			
				if(null == qosreleList || qosreleList.size() !=1)
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
				qosRele = qosreleList.get(0);
				qosRele.setQosInfoList(qosList);
				return qosRele;
				
			} catch (ProcessingFailureException e) {
				throw e;
			}
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getQosRelevance ex.");
		} finally {
			UiUtil.closeService_MB(qosService);
			UiUtil.closeService_MB(qosReleService);
		}
	}

	/**
	 * 获取端口QOS
	 * @param objId 端口ID
	 * @return
	 * @throws ProcessingFailureException 
	 */
	private List<QosQueue> getQosQueue(int id) throws ProcessingFailureException {
		QosQueueService_MB qosQueueService = null;
		try {
			qosQueueService = (QosQueueService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosQueue);
			return qosQueueService.queryByIdWithUNI(id);
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getQosQueue ex.");
		} finally {
			UiUtil.closeService_MB(qosQueueService);
		}
	}
	
	/**
	 * 删除qosInfo集合
	 * 在这里删除实际上是修改qos信息
	 * @throws ProcessingFailureException 
	 */
	private void deleteQosInfo(QosRelevance qosRelevance) throws ProcessingFailureException {
		List<QosInfo> qosInfoList = qosRelevance.getQosInfoList();
		for (QosInfo qos : qosInfoList) {
			qos.setSeq(0);
			qos.setCos(5);
			qos.setCir(1000000);
			qos.setCbs(0);
			qos.setEir(1000000);
			qos.setEbs(0);
			qos.setColorSence(0);
			qos.setPir(2000000);
			qos.setPbs(0);
			qos.setStrategy(0);
		}
		QosInfoService_MB qosService = null;
		try {
			qosService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
			qosService.updateQos(qosInfoList);
//			updateQOSDispatch(qosRelevance);
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"deleteQosInfo ex.");
		} finally {
			UiUtil.closeService_MB(qosService);
		}
	}
	
//	/**
//	 * 更新设备
//	 * @param qosRelevance QOS关系对象
//	 * @throws ProcessingFailureException
//	 */
//	private String updateQOSDispatch(QosRelevance qosRelevance) throws ProcessingFailureException {
//		int type;
//		String result = null;
//		ServiceInfoService serviceInfoService = null;
//		List<ServiceInfo> serviceInfoList;
//		SiteService_MB siteService = null;
//		try {
//			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
//			serviceInfoService = (ServiceInfoService) ConstantUtil.serviceFactory.newService(Services.SERVICEINFO);
//			//武汉修改业务  晨晓修改ac
//			if(EManufacturer.WUHAN.getValue() == siteService.getManufacturer(qosRelevance.getSiteId())){
//				serviceInfoList = serviceInfoService.selectByAcId(qosRelevance.getObjId());
//				if(0 == serviceInfoList.size())
//					throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
//				type = serviceInfoList.get(0).getServiceType();
//				if(type == EServiceType.ELINE.getValue()){
//					result = updateEline(serviceInfoList.get(0));
//				}else if(type == EServiceType.ELAN.getValue()){
//					result = updateElan(serviceInfoList.get(0));
//				}else if(type == EServiceType.ETREE.getValue()){
//					result = updateEtree(serviceInfoList.get(0));
//				}
//			}else{
//				result = updateAcPort(qosRelevance);
//			}
//			
//		} catch (Exception e) {
//			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"updateDispatch ex.");
//		} finally {
//			UiUtil.closeService(serviceInfoService);
//			UiUtil.closeService_MB(siteService);
//		}
//		return result;
//	}

	/**
	 * 更新AC端口 QOS
	 * @param serviceInfo 业务数据库对象
	 * @return
	 * @throws Exception
	 */
	private String updateAcPort(QosRelevance qosRelevance) throws Exception {
		AcPortInfo acPortInfo = null;
		acPortInfo = new AcPortInfo();
		AcDispatch acDispatch = new AcDispatch();
		AcPortInfoService_MB acInfoService = null;
		try {
			acInfoService = (AcPortInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acPortInfo = acInfoService.selectById(qosRelevance.getObjId());
			return acDispatch.excuteUpdate(acPortInfo);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}
		return null;
	}

	/**
	 * 更新武汉AC端口 QOS
	 * @param serviceInfo 业务数据库对象
	 * @return
	 * @throws Exception
	 */
	private String updateEtree(ServiceInfo serviceInfo) throws Exception {
		String result = "";
		List<EtreeInfo> list;
		EtreeDispatch etreeDispatch = new EtreeDispatch();
		EtreeInfoService_MB etreeService = null;
		try {
			etreeService = (EtreeInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			list = etreeService.selectByServiceId(serviceInfo.getServiceId());
			result = etreeDispatch.excuteUpdate(list);
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(etreeService);
		}
		return result;
	}

	/**
	 * 更新武汉AC端口 QOS
	 * @param serviceInfo 业务数据库对象
	 * @return
	 * @throws Exception
	 */
	private String updateElan(ServiceInfo serviceInfo) throws Exception {
		ElanInfo elanInfo = null;
		List<ElanInfo> list;
		elanInfo = new ElanInfo();
		ElanDispatch elanDispatch = new ElanDispatch();
		String result = "";
		ElanInfoService_MB elanInfoService = null;
		try {
			elanInfoService = (ElanInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
			elanInfo.setServiceId(serviceInfo.getServiceId());
			elanInfo.setServiceType(serviceInfo.getServiceType());
			list = elanInfoService.select(elanInfo);
			result = elanDispatch.excuteUpdate(list);
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(elanInfoService);
		}
		return result;
	}

	/**
	 * 更新武汉AC端口 QOS
	 * @param serviceInfo 业务数据库对象
	 * @return
	 * @throws Exception
	 */
	private String updateEline(ServiceInfo serviceInfo) throws Exception {
		String result = "";
		ElineInfo elineinfo = null;
		List<ElineInfo> list;
		ElineInfoService_MB elineService = null;
		try {
			elineinfo = new ElineInfo();
			ElineDispatch elineDispatch = new ElineDispatch();
			elineService = (ElineInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			elineinfo.setServiceId(serviceInfo.getServiceId());
			elineinfo.setServiceType(serviceInfo.getServiceType());
			list = elineService.selectByCondition(elineinfo);
			result = elineDispatch.excuteUpdate(list);
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(elineService);
		}
		return result;
	}

}
