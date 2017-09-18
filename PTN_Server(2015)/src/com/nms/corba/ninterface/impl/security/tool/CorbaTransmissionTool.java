package com.nms.corba.ninterface.impl.security.tool;

import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import transmissionDescriptor.DSQoSMap_T;
import transmissionDescriptor.DropProfile_T;
import transmissionDescriptor.EthCOSRuleACL_T;
import transmissionDescriptor.PHBProfile_T;
import transmissionDescriptor.PSCDropStrategy_T;
import transmissionDescriptor.PSCQueueSchedule_T;
import transmissionDescriptor.QoSProfileList_THolder;
import transmissionDescriptor.QoSProfile_T;
import transmissionDescriptor.QueueProfile_T;
import transmissionDescriptor.RuleCOS_T;
import transmissionDescriptor.TPEgressStrategy_T;
import transmissionDescriptor.TPIngressStrategy_T;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.bean.ptn.qos.QosQueue;
import com.nms.db.bean.ptn.qos.QosRelevance;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.model.ptn.port.AcBufferService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.ptn.qos.QosInfoService_MB;
import com.nms.model.ptn.qos.QosRelevanceService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.UiUtil;

/**
 * 传输转换类
 * @author dzy
 *
 */
public class CorbaTransmissionTool extends CorbaConvertBase{
	
	/**
	 * 转换QOS 集合 
	 * @param qosProfileList
	 * @param qosInfoList
	 * @throws ProcessingFailureException 
	 */
	public void convertQosList2Corba(QoSProfileList_THolder qosProfileList,
									List<QosQueue> qosQueueList) throws ProcessingFailureException {
		try {
			int objId = qosQueueList.get(0).getObjId();
			List<QosQueue> list = new ArrayList<QosQueue>();
			int count = 0;
			for (int i = 0; i < qosQueueList.size(); i++) {
				if(objId != qosQueueList.get(i).getObjId()){
					qosProfileList.value[count] = new QoSProfile_T();
					convertQos2Corba(qosProfileList.value[count],list);
					list.removeAll(list);
					count ++;
				}
				objId = qosQueueList.get(i).getObjId();
				list.add(qosQueueList.get(i));
			}
			qosProfileList.value[count] = new QoSProfile_T();
			convertQos2Corba(qosProfileList.value[count],list);
			list.removeAll(list);
		} catch (ProcessingFailureException e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"convertQosList2Corba ex.");
		}
	}
	/**
	 * 转换QOS
	 * @param qosProfileList
	 * @param qosInfoList
	 * @throws ProcessingFailureException 
	 */
	public void convertQos2Corba(QoSProfile_T qoSProfile,List<QosQueue> qosQueueList) throws ProcessingFailureException {
		QosInfo qosInfo;
		List<AcPortInfo> acList;
		AcPortInfo acPortInfo;
		AcPortInfoService_MB acInfoService = null;
		try {
			
			this.convertQosQueue2Corba(qoSProfile, qosQueueList); //转换qos列队配置
			
			acPortInfo = new AcPortInfo();
			acInfoService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			acPortInfo.setSiteId(qosQueueList.get(0).getSiteId());
			acPortInfo.setPortId(qosQueueList.get(0).getObjId());;
			acList = acInfoService.selectByCondition(acPortInfo);
			if(null != acList && acList.size() == 1){
				acPortInfo = acList.get(0);
				qosInfo = getQosRelevanceList(qosQueueList, acPortInfo);
				if(null != qosInfo){
					this.convertQosInfo2Corba(qoSProfile, qosInfo,acPortInfo);		// QOS流
					qoSProfile.name[1] = new NameAndStringValue_T("QoSProfile", qosQueueList.get(0).getObjId()+"/"+qosInfo.getId());
				}
			}
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"convertQos2Corba ex.");
		} finally {
			UiUtil.closeService_MB(acInfoService);
		}
	}
	
	/**
	 * 转换以太网业务QOS
	 * @param qoSProfile	corba qos
	 * @param qosInfo	QOS
	 * @throws ProcessingFailureException 
	 */
	public void convertQosInfo2Corba(QoSProfile_T qoSProfile,QosInfo qosInfo,AcPortInfo acPortInfo) throws ProcessingFailureException {
		String colorAware;
		int qosType = -1;
		RuleCOS_T[]  ruleCOSList;
		NameAndStringValue_T[] ethFlowControl;
		try {
			qoSProfile.name = new NameAndStringValue_T[2];//qos名称
			qoSProfile.name[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
			qoSProfile.name[1] = new NameAndStringValue_T("QoSProfile",0 +"/"+qosInfo.getId());
			qoSProfile.userLabel = "";//qos所属网元id
			qoSProfile.nativeEMSName = "";//qos名称
			qoSProfile.owner = super.getOwner();
			qoSProfile.ingressStrategy = new TPIngressStrategy_T[1];
			
			ruleCOSList = new RuleCOS_T[0]; 	//qos细分流
			setQOSBuffer(ruleCOSList,qosInfo,acPortInfo);
			
			ethFlowControl = new NameAndStringValue_T[7];	//qos
			ethFlowControl[0] = new NameAndStringValue_T("CIR", qosInfo.getCir()+"");
			ethFlowControl[1] = new NameAndStringValue_T("CBS", qosInfo.getCbs()+"");
			ethFlowControl[2] = new NameAndStringValue_T("EIR", qosInfo.getEir()+"");
			ethFlowControl[3] = new NameAndStringValue_T("EBS", qosInfo.getEbs()+"");
			ethFlowControl[4] = new NameAndStringValue_T("PIR", qosInfo.getPir()+"");
			ethFlowControl[5] = new NameAndStringValue_T("PBS", qosInfo.getPbs()+"");
			ethFlowControl[6] = new NameAndStringValue_T("COS", qosInfo.getCos()+"");
			
			//色彩
			colorAware = qosInfo.getColorSence() == 1? "Color-Aware":"Color-Blind";
			
			//QOS类型
			if("L2".equals(qosInfo.getQosType()))
				qosType =1;
			else if("L3".equals(qosInfo.getQosType()))
				qosType =2;
			else if("vlanpri".equals(qosInfo.getQosType()))
				qosType =3;
			
			qoSProfile.ingressStrategy[0] = new TPIngressStrategy_T();
			qoSProfile.ingressStrategy[0].phb = new PHBProfile_T();
			qoSProfile.ingressStrategy[0].phb.additionalInfo = new NameAndStringValue_T[0];
			qoSProfile.ingressStrategy[0].phb.dsQoSMapList = new DSQoSMap_T[0];
			qoSProfile.ingressStrategy[0].phb.id = "";
			qoSProfile.ingressStrategy[0].ethCOSRuleACLList = new EthCOSRuleACL_T[1];
			qoSProfile.ingressStrategy[0].ethCOSRuleACLList[0] = new EthCOSRuleACL_T("-1", qosType, ruleCOSList, ethFlowControl, colorAware);
			qoSProfile.ingressStrategy[0].additionalInfo = new NameAndStringValue_T[0];
			qoSProfile.additionalInfo = new NameAndStringValue_T[0];
			
			if(null == qoSProfile.egressStrategy){
				qoSProfile.egressStrategy = new TPEgressStrategy_T();
				qoSProfile.egressStrategy.additionalInfo = new NameAndStringValue_T[0];
				qoSProfile.egressStrategy.dropStrategy = new DropProfile_T();
				qoSProfile.egressStrategy.dropStrategy.additionalInfo = new NameAndStringValue_T[0];
				qoSProfile.egressStrategy.dropStrategy.pscDropStrategyList = new PSCDropStrategy_T[0];
				qoSProfile.egressStrategy.dropStrategy.id ="";
				qoSProfile.egressStrategy.queueSchedule  = new QueueProfile_T();
				qoSProfile.egressStrategy.queueSchedule.additionalInfo = new NameAndStringValue_T[0];
				qoSProfile.egressStrategy.queueSchedule.pscQueueScheduleList = new PSCQueueSchedule_T[0];
				qoSProfile.egressStrategy.queueSchedule.id = "";
			}
				
		} catch (ProcessingFailureException e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"convertQosInfo2Corba ex.");
		}	
		
		
		
	}
	
	/**
	 *  查询和转换细分流
	 * @param ruleCOSList	corba流对象
	 * @param qosInfo	qos流对象
	 * @param acPortInfo	ac流对象
	 * @throws ProcessingFailureException
	 */
	private void setQOSBuffer(RuleCOS_T[]  ruleCOSList,
			QosInfo qosInfo,AcPortInfo acPortInfo) throws ProcessingFailureException {
		AcBufferService_MB acBufferService = null;
		List<Acbuffer> acbufferList;
		try {
			acBufferService = (AcBufferService_MB) ConstantUtil.serviceFactory.newService_MB(Services.UniBuffer);
			acbufferList = acBufferService.select(acPortInfo.getId());
			if(null != acbufferList && acbufferList.size() > 0){
				covertQosBuffer(ruleCOSList,acbufferList);
			}
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"setQOSBuffer ex.");
		} finally {
			UiUtil.closeService_MB(acBufferService);
		}
	}
	
	/**
	 * 转换qos细分流
	 * @param ethFlowControl corba对象
	 * @param acbuffer	细分流对象
	 */
	private void covertQosBuffer(RuleCOS_T[]  ruleCOSList,
			List<Acbuffer> acbufferList) {
		Acbuffer acbuffer;
		ruleCOSList = new RuleCOS_T[acbufferList.size()]; 	 
		for (int i = 0; i < acbufferList.size(); i++) {
			acbuffer = acbufferList.get(i);
			ruleCOSList[i] = new RuleCOS_T();
			ruleCOSList[i].cosList = new NameAndStringValue_T[27];
			ruleCOSList[i].cosList[0] = new NameAndStringValue_T("CIR", acbuffer.getCir()+"");
			ruleCOSList[i].cosList[1] = new NameAndStringValue_T("CBS", acbuffer.getCbs()+"");
			ruleCOSList[i].cosList[2] = new NameAndStringValue_T("EIR", acbuffer.getEir()+"");
			ruleCOSList[i].cosList[3] = new NameAndStringValue_T("EBS", acbuffer.getEbs()+"");
			ruleCOSList[i].cosList[4] = new NameAndStringValue_T("PIR", acbuffer.getPir()+"");
			ruleCOSList[i].cosList[5] = new NameAndStringValue_T("PBS", acbuffer.getPbs()+"");
			ruleCOSList[i].cosList[6] = new NameAndStringValue_T("CM", acbuffer.getCm()+"");
			ruleCOSList[i].cosList[7] = new NameAndStringValue_T("operatorVlanIdValue", acbuffer.getOperatorVlanIdValue()+"");
			ruleCOSList[i].cosList[8] = new NameAndStringValue_T("operatorVlanIdMask", acbuffer.getOperatorVlanIdMask()+"");
			ruleCOSList[i].cosList[9] = new NameAndStringValue_T("operatorVlanPriorityValue", acbuffer.getOperatorVlanPriorityValue()+"");
			ruleCOSList[i].cosList[10] = new NameAndStringValue_T("operatorVlanPriorityMask", acbuffer.getOperatorVlanPriorityMask()+"");
			ruleCOSList[i].cosList[11] = new NameAndStringValue_T("clientVlanIdValue", acbuffer.getClientVlanIdValue()+"");
			ruleCOSList[i].cosList[12] = new NameAndStringValue_T("clientVlanIdMask", acbuffer.getClientVlanIdMask()+"");
			ruleCOSList[i].cosList[13] = new NameAndStringValue_T("clientVlanPriorityValue", acbuffer.getClientVlanPriorityValue()+"");
			ruleCOSList[i].cosList[14] = new NameAndStringValue_T("clientVlanPriorityMask", acbuffer.getClientVlanPriorityMask()+"");
			ruleCOSList[i].cosList[15] = new NameAndStringValue_T("ethernetTypeValue", acbuffer.getEthernetTypeValue()+"");
			ruleCOSList[i].cosList[16] = new NameAndStringValue_T("ethernetTypeMask", acbuffer.getEthernetTypeValue()+"");
			ruleCOSList[i].cosList[17] = new NameAndStringValue_T("sourceIpLabelMask", acbuffer.getSourceIpLabelMask()+"");
			ruleCOSList[i].cosList[18] = new NameAndStringValue_T("sourceMacLabelValue", acbuffer.getSourceMac()+"");
			ruleCOSList[i].cosList[19] = new NameAndStringValue_T("sourceMacLabelMask", acbuffer.getSourceMacLabelMask()+"");
			ruleCOSList[i].cosList[20] = new NameAndStringValue_T("sinkIpLabelMask", acbuffer.getSinkIpLabelMask()+"");
			ruleCOSList[i].cosList[21] = new NameAndStringValue_T("sinkMacLabelValue", acbuffer.getTargetMac()+"");
			ruleCOSList[i].cosList[22] = new NameAndStringValue_T("sinkMacLabelMask", acbuffer.getSinkMacLabelMask()+"");
			ruleCOSList[i].cosList[23] = new NameAndStringValue_T("iPTypeValue", acbuffer.getiPTypeValue()+"");
			ruleCOSList[i].cosList[24] = new NameAndStringValue_T("iPTypeMask", acbuffer.getiPTypeMask()+"");
			ruleCOSList[i].cosList[25] = new NameAndStringValue_T("ethernetTypeValue", acbuffer.getEthernetTypeValue()+"");
			ruleCOSList[i].cosList[26] = new NameAndStringValue_T("ethernetTypeMask", acbuffer.getEthernetTypeMask()+"");
		}
	}
	/**
	 * 转换端口QOS
	 * @param qoSProfile	corba qos
	 * @param qosQueueList	端口QOS
	 */
	public void convertQosQueue2Corba(QoSProfile_T qoSProfile,
			List<QosQueue> qosQueueList) {
		QosQueue qosQueue = null;
		NameAndStringValue_T[] nameAndStringValue;
		qoSProfile.name = new NameAndStringValue_T[2];//qos名称
		qoSProfile.name[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		qoSProfile.name[1] = new NameAndStringValue_T("QoSProfile", qosQueueList.get(0).getObjId()+"/"+0);
		
		//队列
		qoSProfile.egressStrategy = new TPEgressStrategy_T();
		qoSProfile.egressStrategy.queueSchedule = new QueueProfile_T();
		qoSProfile.egressStrategy.queueSchedule.id = "";
		qoSProfile.egressStrategy.queueSchedule.pscQueueScheduleList = new PSCQueueSchedule_T[qosQueueList.size()];
		for (int i = 0; i < qosQueueList.size(); i++) {
			qosQueue = qosQueueList.get(i);
			nameAndStringValue = new NameAndStringValue_T[1];
			nameAndStringValue[0] = new NameAndStringValue_T("CIR", qosQueue.getCir()+""); //CIR
			qoSProfile.egressStrategy.queueSchedule.pscQueueScheduleList[i] = new PSCQueueSchedule_T(QosCosLevelEnum.from(qosQueue.getCos()).toString(),
					qosQueue.getQueueType(), nameAndStringValue,  new NameAndStringValue_T[0]);
		}
		qoSProfile.egressStrategy.queueSchedule.additionalInfo = new NameAndStringValue_T[0];
 	
		//丢弃
		qoSProfile.egressStrategy.dropStrategy = new DropProfile_T();
		qoSProfile.egressStrategy.dropStrategy.id = "";
		qoSProfile.egressStrategy.dropStrategy.pscDropStrategyList = new PSCDropStrategy_T[qosQueueList.size()];
		for (int i = 0; i < qosQueueList.size(); i++) {
			qosQueue = qosQueueList.get(i);
			nameAndStringValue = new NameAndStringValue_T[6];
			nameAndStringValue[0] = new NameAndStringValue_T("GREENDROP", qosQueue.getGreenProbability()+""); //绿色报文丢弃率
			nameAndStringValue[1] = new NameAndStringValue_T("GREENDROPLTHRED", qosQueue.getGreenLowThresh()+"");//绿色报文丢弃低门
			nameAndStringValue[2] = new NameAndStringValue_T("GREENDROPHTHRED", qosQueue.getGreenHighThresh()+"");//绿色报文丢弃高门限
			nameAndStringValue[3] = new NameAndStringValue_T("YELLOWDROP", qosQueue.getYellowProbability()+"");//黄色报文丢弃率
			nameAndStringValue[4] = new NameAndStringValue_T("YELLOWDROPLTHRED", qosQueue.getYellowLowThresh()+"");//黄色报文丢弃低门限
			nameAndStringValue[5] = new NameAndStringValue_T("YELLOWDROPHTHRED", qosQueue.getYellowHighThresh()+"");//黄色报文丢弃高门限
			
			qoSProfile.egressStrategy.dropStrategy.pscDropStrategyList[i] = new PSCDropStrategy_T(QosCosLevelEnum.from(qosQueue.getCos()).toString(),
					qosQueue.isWredEnable()+"", nameAndStringValue,  new NameAndStringValue_T[0]);
		}
		qoSProfile.egressStrategy.dropStrategy.additionalInfo = new NameAndStringValue_T[0];
		qoSProfile.egressStrategy.additionalInfo = new NameAndStringValue_T[0];
		if(null == qoSProfile.ingressStrategy){
			qoSProfile.ingressStrategy = new TPIngressStrategy_T[0];
		}
			
		
		qoSProfile.additionalInfo = new NameAndStringValue_T[0];
	}
	
	/**
	 * 获取groupId
	 * @param siteId
	 * @param objType
	 * @param objId
	 * @throws ProcessingFailureException 
	 */
	private QosInfo getQosRelevanceList(List<QosQueue> qosQueueList, AcPortInfo acPortInfo) throws ProcessingFailureException {
		QosRelevance qosRele = new QosRelevance();
		List<QosInfo> qosList;
		QosInfoService_MB qosService = null;
		QosRelevanceService_MB qosReleService = null;
		try {
			qosReleService = (QosRelevanceService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QOSRELEVANCE);
			qosRele.setSiteId(qosQueueList.get(0).getSiteId());
			qosRele.setObjType("ACPORT");
			qosRele.setObjId(acPortInfo.getId());
			List<QosRelevance> qosreleList = qosReleService.select(qosRele);
			if(null == qosreleList || qosreleList.size() !=1)
				return null;
			
			qosRele = qosreleList.get(0);
			QosInfo qos = new QosInfo();
			qos.setGroupId(qosRele.getQosGroupId());
		    qosService = (QosInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.QosInfo);
			qosList = qosService.queryByCondition(qos);
			if(null == qosList || qosList.size() != 1 )
				return null;
			return qosList.get(0);
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getQosRelevanceList ex.");
		} finally {
			UiUtil.closeService_MB(qosService);
			UiUtil.closeService_MB(qosReleService);
		}
	}
	
	/**
	 * corba对象转换QOS对象
	 * @param modifyQPData  corba QOS修改对象
	 * @param qosList
	 */
	public void converCorba2QOS(QoSProfile_T modifyQPData, List<QosInfo> qosList) {
		NameAndStringValue_T[] nameAndStringValue;
		nameAndStringValue = modifyQPData.ingressStrategy[0].ethCOSRuleACLList[0].ethFlowControl;
		if(UiUtil.isNull(modifyQPData.ingressStrategy[0].ethCOSRuleACLList[0].colorAware))
			qosList.get(0).setColorSence(Integer.parseInt(modifyQPData.ingressStrategy[0].ethCOSRuleACLList[0].colorAware));
		for (NameAndStringValue_T nameAndStringValue_T : nameAndStringValue) {
			if("CIR".equals(nameAndStringValue_T.name)){
				qosList.get(0).setCir(Integer.parseInt(nameAndStringValue_T.value));
			}else if("CBS".equals(nameAndStringValue_T.name)){
				qosList.get(0).setCbs(Integer.parseInt(nameAndStringValue_T.value));
			}else if("EIR".equals(nameAndStringValue_T.name)){
				qosList.get(0).setEir(Integer.parseInt(nameAndStringValue_T.value));
			}else if("EBS".equals(nameAndStringValue_T.name)){
				qosList.get(0).setCbs(Integer.parseInt(nameAndStringValue_T.value));
			}else if("PIR".equals(nameAndStringValue_T.name)){
				qosList.get(0).setPir(Integer.parseInt(nameAndStringValue_T.value));
			}else if("PBS".equals(nameAndStringValue_T.name)){
				qosList.get(0).setCbs(Integer.parseInt(nameAndStringValue_T.value));
			}else if("QOSTYPE".equals(nameAndStringValue_T.name)){
				qosList.get(0).setQosType(nameAndStringValue_T.value);
			}else if("COS".equals(nameAndStringValue_T.name)){
				qosList.get(0).setCos(QosCosLevelEnum.from(nameAndStringValue_T.value));
			}
		}
	}
	
	/**
	 * 更新QOS队列数据
	 * @param modifyQPData
	 * @param qosQueueList
	 */
	public void converCorba2QOSQueue(QoSProfile_T modifyQPData,
			List<QosQueue> qosQueueList) {
		Map<Integer,QosQueue> map = new HashMap<Integer,QosQueue>();
		for (QosQueue qosQueue : qosQueueList) {
			map.put(qosQueue.getCos(), qosQueue);
		}
		
		for (PSCQueueSchedule_T PSCQueueSchedule : modifyQPData.egressStrategy.queueSchedule.pscQueueScheduleList) 
			converCorba2Queue(PSCQueueSchedule,map.get(QosCosLevelEnum.from(PSCQueueSchedule.pscType)));
		
		
		for (PSCDropStrategy_T PSCDropStrategy : modifyQPData.egressStrategy.dropStrategy.pscDropStrategyList) 
			converCorba2Queue(PSCDropStrategy,map.get(QosCosLevelEnum.from(PSCDropStrategy.pscType)));
		
	}
	
	/**
	 * 修改丢弃数据
	 * @param pSCDropStrategy	corba对象
	 * @param qosQueue	EMS队列对象
	 */
	private void converCorba2Queue(PSCDropStrategy_T pSCDropStrategy,
			QosQueue qosQueue) {
		qosQueue.setWredEnable("1".equals(pSCDropStrategy.dropArithmetic)?true:false);
		if(pSCDropStrategy.arithmeticParam.length != 0){
			for (NameAndStringValue_T nameAndStringValue : pSCDropStrategy.arithmeticParam) {
				if("GREENDROP".equals(nameAndStringValue.name))
					qosQueue.setGreenProbability(Integer.parseInt(nameAndStringValue.value));
				else if("GREENDROPLTHRED".equals(nameAndStringValue.name))
					qosQueue.setGreenLowThresh(Integer.parseInt(nameAndStringValue.value));
				else if("GREENDROPHTHRED".equals(nameAndStringValue.name))
					qosQueue.setGreenHighThresh(Integer.parseInt(nameAndStringValue.value));
				else if("YELLOWDROP".equals(nameAndStringValue.name))
					qosQueue.setYellowProbability(Integer.parseInt(nameAndStringValue.value));
				else if("YELLOWDROPLTHRED".equals(nameAndStringValue.name))
					qosQueue.setYellowLowThresh(Integer.parseInt(nameAndStringValue.value));
				else if("YELLOWDROPHTHRED".equals(nameAndStringValue.name))
					qosQueue.setYellowHighThresh(Integer.parseInt(nameAndStringValue.value));
			}
		}
	}
	
	/**
	 * 修改队列数据
	 * @param pSCQueueSchedule corba对象
	 * @param qosQueue	EMS队列对象
	 */
	private void converCorba2Queue(PSCQueueSchedule_T pSCQueueSchedule,
			QosQueue qosQueue) {
		qosQueue.setQueueType(pSCQueueSchedule.queueArithmetic);
		if(pSCQueueSchedule.arithmeticParam.length == 1 && pSCQueueSchedule.arithmeticParam[0].name.equals("CIR"))
			qosQueue.setCir(Integer.parseInt(pSCQueueSchedule.arithmeticParam[0].value));
		
	}
}
