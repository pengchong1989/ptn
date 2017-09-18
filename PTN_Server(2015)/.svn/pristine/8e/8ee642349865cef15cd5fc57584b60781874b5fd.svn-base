package com.nms.corba.test;

import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;
import transmissionDescriptor.DSQoSMap_T;
import transmissionDescriptor.DropProfile_T;
import transmissionDescriptor.EthCOSRuleACL_T;
import transmissionDescriptor.PHBProfile_T;
import transmissionDescriptor.PSCDropStrategy_T;
import transmissionDescriptor.PSCQueueSchedule_T;
import transmissionDescriptor.QoSProfileIterator_IHolder;
import transmissionDescriptor.QoSProfileList_THolder;
import transmissionDescriptor.QoSProfile_T;
import transmissionDescriptor.QoSProfile_THolder;
import transmissionDescriptor.QueueProfile_T;
import transmissionDescriptor.RuleCOS_T;
import transmissionDescriptor.TPEgressStrategy_T;
import transmissionDescriptor.TPIngressStrategy_T;
import transmissionDescriptor.TransmissionDescriptorMgr_I;
import transmissionDescriptor.TransmissionDescriptorMgr_IHelper;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.test.common.CorbaConnect;
import com.nms.db.enums.QosCosLevelEnum;
import common.Common_IHolder;

/**
 * @author guoqc
 * 测试 查询所有QoS策略描述属性/修改某一条QoS策略描述属性/删除一条qos策略描述属性
 */
public class QosTest {
	private TransmissionDescriptorMgr_I transmissionI;
	
	public QosTest() throws ProcessingFailureException{
		Common_IHolder common = new Common_IHolder();
		CorbaConnect.emsSession.getManager("TransmissionDescriptor", common);
		transmissionI = TransmissionDescriptorMgr_IHelper.narrow(common.value);
	}
	
	public static void main(String[] args) throws ProcessingFailureException {
		CorbaConnect c = new CorbaConnect();
		c.isConnect();
		QosTest qosTest = new QosTest();
//		qosTest.getAllQoSProfile();//查询所有QoS策略描述属性
//		qosTest.getQoSProfile();//查询某一条QoS策略描述属性
		qosTest.modifyQoSProfile();//修改某一条QoS策略描述属性
//		qosTest.deleteQoSProfile();//删除一条qos策略描述属性
	}
	
	/**
	 * 查询所有QoS策略描述属性
	 * @throws ProcessingFailureException 
	 */
	private void getAllQoSProfile() throws ProcessingFailureException{
		QoSProfileList_THolder qos = new QoSProfileList_THolder();
		QoSProfileIterator_IHolder qosProfileIt = new QoSProfileIterator_IHolder();
		transmissionI.getAllQoSProfile(2, qos, qosProfileIt);
		if (qos.value != null && qos.value.length > 0) {
			for (int i = 0; i < qos.value.length; i++) {
				System.out.println(qos.value[i].nativeEMSName);
			}
		}
		if (qosProfileIt.value != null) {
			while (qosProfileIt.value.next_n(2, qos)) {
				for (int i = 0; i < qos.value.length; i++) {
					System.out.println(qos.value[i].nativeEMSName);
				}
			}
			qosProfileIt.value.destroy();
		}
	}
	
	/**
	 * 查询某一条QoS策略描述属性
	 * @throws ProcessingFailureException 
	 */
	private void getQoSProfile() throws ProcessingFailureException{
		NameAndStringValue_T[] queryInfo = new NameAndStringValue_T[2];
		queryInfo[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		queryInfo[1] = new NameAndStringValue_T("QoSProfile", "124/54");
		QoSProfile_THolder qos = new QoSProfile_THolder();
		transmissionI.getQoSProfile(queryInfo, qos);
		QoSProfile_T q = qos.value;	
		System.out.println("qos所属网元Id : "+q.userLabel);
		System.out.println("qos名称 : "+q.name[0].value);
		for (int i = 0; i < q.additionalInfo.length; i++) {
			System.out.print(q.additionalInfo[i].name + " : ");
			System.out.println(q.additionalInfo[i].value);
		}
	}
	
	/**
	 * 修改某一条QoS策略描述属性
	 * @throws ProcessingFailureException 
	 */
	private void modifyQoSProfile() throws ProcessingFailureException{
		QoSProfile_T modifyQPData = new QoSProfile_T();
		modifyQPData.nativeEMSName = "llsp1";//qos名称
		modifyQPData.userLabel = "1";//所属网元Id
		modifyQPData.name = new NameAndStringValue_T[2];//qos名称
		modifyQPData.name[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		modifyQPData.name[1] = new NameAndStringValue_T("QoSProfile", "124/54");
		modifyQPData.owner = "";
		
		modifyQPData.egressStrategy = new TPEgressStrategy_T();
		modifyQPData.egressStrategy.dropStrategy = new DropProfile_T();
		modifyQPData.egressStrategy.dropStrategy.id = "";
		modifyQPData.egressStrategy.dropStrategy.pscDropStrategyList = new PSCDropStrategy_T[8];
		modifyQPData.egressStrategy.dropStrategy.pscDropStrategyList[0] = new PSCDropStrategy_T();
		modifyQPData.egressStrategy.dropStrategy.pscDropStrategyList[1] = new PSCDropStrategy_T();
		modifyQPData.egressStrategy.dropStrategy.pscDropStrategyList[2] = new PSCDropStrategy_T();
		modifyQPData.egressStrategy.dropStrategy.pscDropStrategyList[3] = new PSCDropStrategy_T();
		modifyQPData.egressStrategy.dropStrategy.pscDropStrategyList[4] = new PSCDropStrategy_T();
		modifyQPData.egressStrategy.dropStrategy.pscDropStrategyList[5] = new PSCDropStrategy_T();
		modifyQPData.egressStrategy.dropStrategy.pscDropStrategyList[6] = new PSCDropStrategy_T();
		modifyQPData.egressStrategy.dropStrategy.pscDropStrategyList[7] = new PSCDropStrategy_T();
		PSCDropStrategy_T PSCDropStrategy;
		for (int i = 0; i < modifyQPData.egressStrategy.dropStrategy.pscDropStrategyList.length; i++) {
			PSCDropStrategy = modifyQPData.egressStrategy.dropStrategy.pscDropStrategyList[i];
			PSCDropStrategy.dropArithmetic = "1";
			PSCDropStrategy.pscType = QosCosLevelEnum.from(i).toString();
			PSCDropStrategy.arithmeticParam = new NameAndStringValue_T[6];
			PSCDropStrategy.arithmeticParam[0] = new NameAndStringValue_T("GREENDROP","300");
			PSCDropStrategy.arithmeticParam[1] = new NameAndStringValue_T("GREENDROPLTHRED","300");
			PSCDropStrategy.arithmeticParam[2] = new NameAndStringValue_T("GREENDROPHTHRED","300");
			PSCDropStrategy.arithmeticParam[3] = new NameAndStringValue_T("YELLOWDROP","300");
			PSCDropStrategy.arithmeticParam[4] = new NameAndStringValue_T("YELLOWDROPLTHRED","300");
			PSCDropStrategy.arithmeticParam[5] = new NameAndStringValue_T("YELLOWDROPHTHRED","300");
			PSCDropStrategy.additionalInfo = new NameAndStringValue_T[0];
		}
		
		modifyQPData.egressStrategy.dropStrategy.additionalInfo = new NameAndStringValue_T[]{};
		modifyQPData.egressStrategy.queueSchedule = new QueueProfile_T();
		modifyQPData.egressStrategy.queueSchedule.id = "";
		modifyQPData.egressStrategy.queueSchedule.pscQueueScheduleList = new PSCQueueSchedule_T[8];
		modifyQPData.egressStrategy.queueSchedule.pscQueueScheduleList[0] = new PSCQueueSchedule_T();
		modifyQPData.egressStrategy.queueSchedule.pscQueueScheduleList[1] = new PSCQueueSchedule_T();
		modifyQPData.egressStrategy.queueSchedule.pscQueueScheduleList[2] = new PSCQueueSchedule_T();
		modifyQPData.egressStrategy.queueSchedule.pscQueueScheduleList[3] = new PSCQueueSchedule_T();
		modifyQPData.egressStrategy.queueSchedule.pscQueueScheduleList[4] = new PSCQueueSchedule_T();
		modifyQPData.egressStrategy.queueSchedule.pscQueueScheduleList[5] = new PSCQueueSchedule_T();
		modifyQPData.egressStrategy.queueSchedule.pscQueueScheduleList[6] = new PSCQueueSchedule_T();
		modifyQPData.egressStrategy.queueSchedule.pscQueueScheduleList[7] = new PSCQueueSchedule_T();
		PSCQueueSchedule_T PSCQueueSchedule_T;
		for (int i = 0; i < modifyQPData.egressStrategy.queueSchedule.pscQueueScheduleList.length; i++) {
			PSCQueueSchedule_T =  modifyQPData.egressStrategy.queueSchedule.pscQueueScheduleList[i];
			PSCQueueSchedule_T.arithmeticParam  = new NameAndStringValue_T[1];
			PSCQueueSchedule_T.pscType =  QosCosLevelEnum.from(i).toString();
			PSCQueueSchedule_T.arithmeticParam[0] = new NameAndStringValue_T("CIR","300");
			PSCQueueSchedule_T.additionalInfo = new NameAndStringValue_T[0];
		}
		modifyQPData.egressStrategy.queueSchedule.additionalInfo = new NameAndStringValue_T[]{};
		modifyQPData.egressStrategy.additionalInfo = new NameAndStringValue_T[1];
		modifyQPData.egressStrategy.additionalInfo[0] = new NameAndStringValue_T("","1");
		
		
		QoSProfile_THolder qos = new QoSProfile_THolder();
		qos.value = new QoSProfile_T();
		NameAndStringValue_T[] addInfo = new NameAndStringValue_T[9];
		int i = 0;
		addInfo[i++] = new NameAndStringValue_T("QOSTYPE", "L2");
		addInfo[i++] = new NameAndStringValue_T("CIR", "55");
		addInfo[i++] = new NameAndStringValue_T("CBS", "55");
		addInfo[i++] = new NameAndStringValue_T("EIR", "55");
		addInfo[i++] = new NameAndStringValue_T("EBS", "55");
		addInfo[i++] = new NameAndStringValue_T("PIR", "55");
		addInfo[i++] = new NameAndStringValue_T("PBS", "55");
		addInfo[i++] = new NameAndStringValue_T("COLORSENSE", "1");
		addInfo[i++] = new NameAndStringValue_T("COS", "COS");
		modifyQPData.ingressStrategy = new TPIngressStrategy_T[1];
		modifyQPData.ingressStrategy[0] = new TPIngressStrategy_T();
		modifyQPData.ingressStrategy[0].ethCOSRuleACLList = new EthCOSRuleACL_T[1];
		modifyQPData.ingressStrategy[0].ethCOSRuleACLList[0] = new EthCOSRuleACL_T();
		modifyQPData.ingressStrategy[0].ethCOSRuleACLList[0].ethFlowControl = addInfo;
		modifyQPData.ingressStrategy[0].ethCOSRuleACLList[0].ethCOSRuleList = new RuleCOS_T[0];
		modifyQPData.ingressStrategy[0].phb = new PHBProfile_T();
		modifyQPData.ingressStrategy[0].phb = new PHBProfile_T();
		modifyQPData.ingressStrategy[0].phb.additionalInfo = new NameAndStringValue_T[0];
		modifyQPData.ingressStrategy[0].phb.dsQoSMapList = new DSQoSMap_T[0];
		modifyQPData.ingressStrategy[0].phb.id = "";
		modifyQPData.ingressStrategy[0].additionalInfo = new NameAndStringValue_T[0];
		modifyQPData.additionalInfo = new NameAndStringValue_T[0];
		transmissionI.modifyQoSProfile(modifyQPData, qos);
		int a;
		a=1;
	}
	
	/**
	 * 删除一条qos策略描述属性
	 * @throws ProcessingFailureException 
	 */
	private void deleteQoSProfile() throws ProcessingFailureException{
		NameAndStringValue_T[] deleteInfo = new NameAndStringValue_T[2];
		deleteInfo[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());
		deleteInfo[1] = new NameAndStringValue_T("QoSProfile", "124/1");
		transmissionI.deleteQoSProfile(deleteInfo);
	}
}
