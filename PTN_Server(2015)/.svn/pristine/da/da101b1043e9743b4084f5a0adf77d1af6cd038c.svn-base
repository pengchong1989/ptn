package com.nms.corba.ninterface.util;

import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nms.corba.ninterface.enums.EOAMType;
import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.db.bean.client.Client;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.port.PortStmTimeslot;
import com.nms.db.bean.ptn.oam.OamInfo;
import com.nms.db.bean.ptn.oam.OamMepInfo;
import com.nms.db.bean.ptn.oam.OamMipInfo;
import com.nms.db.bean.ptn.port.Acbuffer;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.db.enums.QosCosLevelEnum;
import com.nms.model.client.ClientService_MB;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.port.PortStmTimeslotService_MB;
import com.nms.model.ptn.LabelInfoService_MB;
import com.nms.model.ptn.oam.OamInfoService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

/**
 * corba转换对象类的基类 提供一些公用方法
 * 
 * @author kk
 * 
 */
public class CorbaConvertBase {

	public final String MULTILAYERSUBNETWORK = "MultiLayerSubnetwork";
	public final String SUBNETWORKCONNECTION = "SubnetworkConnection";
	public final String EMS = "EMS";
	public final String MANAGEELEMENT = "ManagedElement";// 网元
	public final String PTP = "PTP";// 端口标识
	public final String CTP = "CTP";// ces 的AC标识（如：pdh端口id）
	public final String QOSTYPE = "QosType";
	private final String QOSNAME = "QosName";
	public final String QOSCOS = "QosCos";
	private final String A2ZCIR = "A2ZCIR";// 正向CIR
	private final String Z2ACIR = "Z2ACIR";// 反向 CIR
	private final String A2ZPIR = "A2ZPIR";// 正向CIR
	private final String Z2APIR = "Z2APIR";// 反向 CIR
	private final String A2ZCBS = "A2ZCBS";// 正向CIR
	private final String Z2ACBS = "Z2ACBS";// 反向 CIR
	private final String A2ZPBS = "A2ZPBS";// 正向CIR
	private final String Z2APBS = "Z2APBS";// 反向 CIR
	private final String A2ZEBS = "A2ZEBS";// 正向CIR
	private final String Z2AEBS = "Z2AEBS";// 反向 CIR
	private final String A2ZEIR = "A2ZEIR";// 正向CIR
	private final String Z2AEIR = "Z2AEIR";// 反向 CIR
	protected final String CREATETIME = "CreateTime";// 创建时间
	protected final String CREATEUSER = "CreateUser";// 创建人
	protected final String ISACTIVE = "IsActive";// 是否为激活状态
	private final String owner = CorbaConfig.getInstanse().getEmsVendorName();// 拥有者
	protected final String CLIENTNAME = "ClientName";// 客户名称
	protected final String CESTYPE = "CesType";// ces类型
	protected final String CLOCKSOURCE = "ClockSource";// 时钟源
	protected final String TNP = "TrailNtwProtection";// 网络保护
	protected final String TOPOLOGICALLINK = "TopologicalLink";// 时钟源
	protected final String EQUIPMENTHOLDER = "EquipmentHolder";// 设备容器
	protected final String EQUIPMENT = "Equipment";// 单元盘
	private final String FLOWDOMAIN = "FlowDomain";// 流域片段 ；--网络层（端到端）：：（第2层）
	private final String FLOWDOMAINFRAGMENT = "FlowDomainFragment";// 流域片段 ；--网络层（端到端）：：（第3层）
	public String MATRIXFLOWDOMAIN = "MatrixFlowDomain";// 矩阵流域:: 即是单点下业务命名，3层机构中的第3层（前2层同网元）
	protected final String FDFRRATE = "FDFRRATE";// 业务层速率
	public final String USERLABEL = "UserLabel";

	public final String SRCINLABEL = "SrcInLabel"; // 源入标签
	public final String SRCOUTLABEL = "SrcOutLabel"; // 源出标签
	public final String DESTINLABEL = "DestInLabel"; // 宿入标签
	public final String DESTOUTLABEL = "DestOutLabel"; // 宿出标签
	public final String PWTYPE = "PwType";// pw类型
	public final String NATIVEEMSNAME = "NativeEMSName"; // 本地名称
	public final String MATRIXFLOWDOMAINFRAGMENT = "MatrixFlowDomainFragment"; // 单网元以太网业务名称
	public final String MEG = "MEG";// 网元
	public final String SERVERCONNECTIONS = "ServerConnections";// 使用的PW或隧道路径标识列表
	public final String AMEG="AMEG";
	public final String ZMEG="ZMEG";

	/**
	 * 为CORBA对象，名称赋值
	 * 
	 * @param object
	 * @return
	 * @throws ProcessingFailureException
	 */
	public NameAndStringValue_T[] convertName(int rate, int id, int siteId) throws ProcessingFailureException {
		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
		try {
			name[0] = new NameAndStringValue_T(this.EMS, CorbaConfig.getInstanse().getCorbaEmsName());
			name[1] = new NameAndStringValue_T(this.MULTILAYERSUBNETWORK, "1");
			if (rate == ELayerRate.TUNNEL.getValue()) {
				name[2] = new NameAndStringValue_T(this.SUBNETWORKCONNECTION, ELayerRate.TUNNEL.getValue() + "/" + id);
			} else if (rate == ELayerRate.PW.getValue()) {
				name[2] = new NameAndStringValue_T(this.SUBNETWORKCONNECTION, ELayerRate.PW.getValue() + "/" + id);
			} else if (rate == ELayerRate.PORT.getValue()) {
				name[1] = new NameAndStringValue_T(this.MANAGEELEMENT, siteId + "");
				name[2] = new NameAndStringValue_T(this.PTP, this.convertPTPName(id));
			} else if (rate == ELayerRate.CES.getValue()) {
				name[2] = new NameAndStringValue_T(this.SUBNETWORKCONNECTION, ELayerRate.CES.getValue() + "/" + id);
			} else if (rate == ELayerRate.CTP.getValue()) {// 端口id(ces下 ac的端口id )
				name = new NameAndStringValue_T[4];
				name[0] = new NameAndStringValue_T(this.EMS, CorbaConfig.getInstanse().getCorbaEmsName());
				name[1] = new NameAndStringValue_T(this.MANAGEELEMENT, siteId + "");
				name[2] = new NameAndStringValue_T(this.PTP, this.convertPTPName(getPortId(id)));
				name[3] = new NameAndStringValue_T(this.CTP, id + "");
			} else if (rate == ELayerRate.ETHSERVICE.getValue()) {
				name[1] = new NameAndStringValue_T(this.FLOWDOMAIN, "1");
				name[2] = new NameAndStringValue_T(this.FLOWDOMAINFRAGMENT, id + "");
			} else if (rate == ELayerRate.TNP.getValue()) {
				name[2] = new NameAndStringValue_T(this.TNP, ELayerRate.TUNNEL.getValue() + "/" + id);
			} else if (rate == ELayerRate.TOPOLOGICALLINK.getValue()) {
				name = new NameAndStringValue_T[2];
				name[0] = new NameAndStringValue_T(this.EMS, CorbaConfig.getInstanse().getCorbaEmsName());
				name[1] = new NameAndStringValue_T(this.TOPOLOGICALLINK, ELayerRate.TOPOLOGICALLINK.getValue() + "/" + id);
			} else if (rate == ELayerRate.CLOCKSOURCE.getValue()) {
				name[1] = new NameAndStringValue_T(this.MANAGEELEMENT, siteId + "");
				name[2] = new NameAndStringValue_T(this.CLOCKSOURCE, ELayerRate.CLOCKSOURCE.getValue() + "/" + id);
			}else if(rate==ELayerRate.PGP.getValue()){//仅TUNNEL 类型
				name[1]=new NameAndStringValue_T(this.MANAGEELEMENT, siteId+"");
				name[2] = new NameAndStringValue_T(ELayerRate.PGP.toString(), ELayerRate.PGP.getValue()+"/"+id);
			}
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "convertName");
		}

		return name;
	}

	/**
	 * 把端口ID转换成 “rack=1/shelf=3/slot=1/sub_slot=1/port=1”
	 * 
	 * @param portId
	 * @return
	 * @throws Exception
	 */
	private String convertPTPName(int portId) throws Exception {

		PortService_MB portService = null;
		PortInst portInst = null;
		int slotNumber = 0;
		String result = "";
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInst = portService.selectPortybyid(portId);
			if (null != portInst) {
				slotNumber = portInst.getSlotNumber();
				result = "/rack=1/shelf=1/slot=" + slotNumber + "/port=" + portInst.getPortName();
			}

		} catch (Exception e) {
			throw e;
		} finally {
			UiUtil.closeService_MB(portService);
		}
		return result;
	}

	/**
	 * 通过key取出corba对象中的value
	 * 
	 * @param sncCreateData
	 * @param key
	 * @return
	 */
	public String getValueByKey(NameAndStringValue_T[] nameAndStringValue_T, String key) {
		String value = "";
		if (nameAndStringValue_T.length > 0) {
			// 遍历附加信息
			for (int i = 0; i < nameAndStringValue_T.length; i++) {
				// 获取某一队列的 NameAndStringValue_T对象
				if (nameAndStringValue_T[i].name.equals(key)) {
					value = nameAndStringValue_T[i].value;
					break;
				}
			}
		}
		return value;
	}

	/**
	 * 通过key取出corba对象中的value
	 * 
	 * @param sncCreateData
	 * @param key
	 * @return
	 * @throws ProcessingFailureException 
	 */
	public String getValueByKey(NameAndStringValue_T[] nameAndStringValue_T, String key, int siteId) throws ProcessingFailureException {
		String value = "";
		if (nameAndStringValue_T.length > 0) {
			// 遍历附加信息
			for (int i = 0; i < nameAndStringValue_T.length; i++) {
				// 获取某一队列的 NameAndStringValue_T对象
				if (nameAndStringValue_T[i].name.equals(key)) {
					value = nameAndStringValue_T[i].value;
					break;
				}
			}
		}

		// 如果是ptp 有特殊的处理方式
		if (this.PTP.equals(key)) {
			value = this.getPortId(value, siteId);
		}
		return value;
	}

	/**
	 * 从PTP格式的端口名称找出端口ID
	 * 
	 * @param ptpName
	 * @return
	 * @throws ProcessingFailureException 
	 */
	private String getPortId(String ptpName, int siteId) throws ProcessingFailureException {
		String portId = "";
		PortService_MB portService = null;
		try {
			if(!this.checkPTPValue(ptpName)){//验证端口名称  格式是否符合
				throw new ProcessingFailureException(
						ExceptionType_T.EXCPT_INVALID_INPUT,
						"The PTP is invalid!");
			}
			String portName = ptpName.substring(ptpName.lastIndexOf("=") + 1, ptpName.length());
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			List<PortInst> portInstList = portService.selectPortbySiteandPortname(siteId, portName);
			
			if(null != portInstList && portInstList.size()==1){
				portId=portInstList.get(0).getPortId()+"";
			}
			
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "getPortId.");
		} finally {
			UiUtil.closeService_MB(portService);
		}
		if("".equals(portId)){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "getPortId.");
		}


		return portId;
	}

	/**
	 * 获取层速率
	 * 
	 * @param nameAndStringValues
	 *            corba传入参数
	 * @return 层速率枚举
	 * @throws ProcessingFailureException
	 */
	public ELayerRate getLayerRate(NameAndStringValue_T[] nameAndStringValues) throws ProcessingFailureException {
		/*
		 * 验证传人参数的层数率是否正确
		 */
		if (nameAndStringValues[2].value == null || nameAndStringValues[2].value.equals("")) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "nameAndStringValues is invalid!");
		}
		String value = null;
		int index = -1;
		int layerRate_int = 0;
		ELayerRate layerRate = null;
		try {
			// 值为入参的第三层 格式为速率/主键
			value = nameAndStringValues[2].value;
			// 获取"/"索引
			index = this.getIndex(value);
			// 速率为0到"/"的索引
			layerRate_int = Integer.parseInt(value.substring(0, index));
			layerRate = ELayerRate.from(layerRate_int);

		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Internal error : The process has internal error!");
		} finally {
			value = null;
		}
		return layerRate;
	}

	/**
	 * 获取主键
	 * 
	 * @param nameAndStringValues
	 *            corba传入参数
	 * @return 层速率枚举
	 * @throws Exception
	 */
	public int getPrimaryKey(NameAndStringValue_T[] nameAndStringValues) throws ProcessingFailureException {
		/*
		 * 验证传人参数的层数率是否正确
		 */
		if (nameAndStringValues[2].value == null || nameAndStringValues[2].value.equals("")) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Internal error : The process has internal error!");
		}
		String value = null;
		int index = -1;
		int primaryKey = 0;
		try {
			// 值为入参的第三层 格式为速率/主键
			value = nameAndStringValues[2].value;
			// 获取"/"索引
			index = this.getIndex(value);
			// 主键为0到"/"的索引
			primaryKey = Integer.parseInt(value.substring(index + 1, value.length()));

		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Internal error : The process has internal error!");
		} finally {
			value = null;
		}
		return primaryKey;
	}

	/**
	 * 在value字符串中获取"/"的索引
	 * 
	 * @param value
	 *            字符串
	 * @return 索引
	 * @throws Exception
	 */
	private int getIndex(String value) throws ProcessingFailureException {
		int index = -1;
		try {
			// 获取"/"索引
			index = value.indexOf("/");
			if(2!=value.split("/").length){//验证速率格式：　－－－ａ/b/c/d/4
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Internal error : The rate is invalid!");
			}
			// 如果index指向为第一个或者最后一个，说明格式不正确
			if (index < 1 || index == value.length()) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "Internal error : The process has internal error!");
			}
		} catch (ProcessingFailureException e) {
			throw e;
		}
		return index;
	}

	/**
	 * 返回失败信息
	 * 
	 * @param result
	 *            操作返回信息
	 * @return
	 */
	public String outErrorMessage(String result) {
		// 返回失败信息
		if (!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))) {
			return result;
		} else {
			return "";
		}

	}

	/**
	 * 通用; qos信息，转为corBar对象的附加信息
	 * 
	 * @param additionalInfo
	 * @param qosList
	 */
	public void converQos(NameAndStringValue_T[] additionalInfo, List<QosInfo> qosList) {
		/**
		 * 默认 A2Z字样为A端 - qosList.get(0)即：集合第一个元素 Z2A字样为Z端 - qosList.get(1) 即：集合第二个元素
		 */
		additionalInfo[0] = new NameAndStringValue_T(this.A2ZEIR, qosList.get(0).getEir() + "");// 路径类型
		additionalInfo[1] = new NameAndStringValue_T(this.Z2AEIR, qosList.get(1).getEir() + "");// 路径类型
		additionalInfo[2] = new NameAndStringValue_T(this.QOSTYPE, qosList.get(0).getQosType());// 路径类型
		// qos描述信息
		additionalInfo[3] = new NameAndStringValue_T(this.A2ZCIR, qosList.get(0).getCir() + "");// 路径类型
		additionalInfo[4] = new NameAndStringValue_T(this.Z2ACIR, qosList.get(1).getCir() + "");// 路径类型
		additionalInfo[5] = new NameAndStringValue_T(this.A2ZPIR, qosList.get(0).getPir() + "");// 路径类型
		additionalInfo[6] = new NameAndStringValue_T(this.Z2APIR, qosList.get(1).getPir() + "");// 路径类型
		additionalInfo[7] = new NameAndStringValue_T(this.A2ZCBS, qosList.get(0).getCbs() + "");// 路径类型
		additionalInfo[8] = new NameAndStringValue_T(this.Z2ACBS, qosList.get(1).getCbs() + "");// 路径类型
		additionalInfo[9] = new NameAndStringValue_T(this.A2ZPBS, qosList.get(0).getPbs() + "");// 路径类型
		additionalInfo[10] = new NameAndStringValue_T(this.Z2APBS, qosList.get(1).getPbs() + "");// 路径类型
		additionalInfo[11] = new NameAndStringValue_T(this.A2ZEBS, qosList.get(0).getEbs() + "");// 路径类型
		additionalInfo[12] = new NameAndStringValue_T(this.Z2AEBS, qosList.get(1).getEbs() + "");// 路径类型
		additionalInfo[13] = new NameAndStringValue_T(this.QOSCOS, QosCosLevelEnum.from(qosList.get(1).getCos()).toString());// 路径类型
		// additionalInfo[14] = new NameAndStringValue_T(this.QOSNAME, qosList.get(1).getQosname()+ "");// 路径类型
	}

	/**
	 * 根据corba对象的附加信息 获取qos信息
	 * 
	 * @param sncCreateData
	 * @return
	 */
	public List<QosInfo> converQosList(NameAndStringValue_T[] additionalInfo) throws ProcessingFailureException {
		List<QosInfo> qosList = null;
		QosInfo qosBack = null;
		QosInfo qosFront = null;
		try {
			qosList = new ArrayList<QosInfo>();
			qosBack = new QosInfo();
			qosFront = new QosInfo();
			// qos
			qosFront.setDirection("1");
			qosBack.setDirection("2");
			qosFront.setCos(QosCosLevelEnum.from(getValueByKey(additionalInfo, this.QOSCOS)));
			qosBack.setCos(QosCosLevelEnum.from(getValueByKey(additionalInfo, this.QOSCOS)));
			qosFront.setQosname(getValueByKey(additionalInfo, this.QOSNAME));
			// qosBack.setQosname(getValueByKey(additionalInfo, this.QOSNAME));
			qosFront.setQosType(getValueByKey(additionalInfo, this.QOSTYPE));
			qosBack.setQosType(getValueByKey(additionalInfo, this.QOSTYPE));
			qosFront.setCir(Integer.parseInt(getValueByKey(additionalInfo, this.A2ZCIR)));
			qosBack.setCir(Integer.parseInt(getValueByKey(additionalInfo, this.Z2ACIR)));
			qosFront.setPir(Integer.parseInt(getValueByKey(additionalInfo, this.Z2APIR)));
			qosBack.setPir(Integer.parseInt(getValueByKey(additionalInfo, this.A2ZPIR)));
			qosFront.setCbs(Integer.parseInt(getValueByKey(additionalInfo, this.Z2ACBS)));
			qosBack.setCbs(Integer.parseInt(getValueByKey(additionalInfo, this.A2ZCBS)));
			qosFront.setPbs(Integer.parseInt(getValueByKey(additionalInfo, this.Z2APBS)));
			qosBack.setPbs(Integer.parseInt(getValueByKey(additionalInfo, this.A2ZPBS)));
			qosFront.setEbs(Integer.parseInt(getValueByKey(additionalInfo, this.Z2AEBS)));
			qosBack.setEbs(Integer.parseInt(getValueByKey(additionalInfo, this.A2ZEBS)));
			qosFront.setEir(Integer.parseInt(getValueByKey(additionalInfo, this.Z2AEIR)));
			qosBack.setEir(Integer.parseInt(getValueByKey(additionalInfo, this.A2ZEIR)));
			/*
			 * 注意： qos集合第一个元素确保为正向：既：A端 第二个元素为Z 端
			 */
			qosList.add(qosFront);
			qosList.add(qosBack);
	
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			qosBack = null;
			qosFront = null;
		}
		return qosList;
	}

	/**
	 * 根据给定参数 格式为 3/4 (即：速率/主键) 提取主键ID
	 * 
	 * @param name
	 * @return
	 * @throws ProcessingFailureException
	 */
	public int converByElaytoId(int rate, String name) throws ProcessingFailureException {
		String[] speedGroup = name.split("/");
		int id = 0;
		if (speedGroup.length == 2) {// 命名规则： 速率/主键 --如： 3/13 既分割主键Id 得到2个元素（第一个为速率，第二个为要得到的主键）
			if (Integer.parseInt(speedGroup[0]) == rate) {
				id = Integer.parseInt(speedGroup[1]);
			}
		} else {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
		return id;
	}

	/**
	 * 通过客户ID 查找客户名称
	 * 
	 * @param clientId
	 * @return
	 */
	public String queryById(int clientId) throws ProcessingFailureException {
		// 如果没有选中客户： 返回“”
		if (clientId == 0) {
			return "";
		}
		ClientService_MB clientService = null;
		List<Client> clientList = null;
		try {
			clientService = (ClientService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CLIENTSERVICE);
			clientList = clientService.select(clientId);
			if (clientList != null && clientList.size() == 1) {
				return clientList.get(0).getName();
			} else {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
			}
		}
		catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(clientService);
		}
	}

	/**
	 * 通过客户名称,查看数据库是否有数据
	 * 
	 * @param clientId
	 * @return
	 */
	public int queryByName(String name) throws ProcessingFailureException {
		// 如果输入字符为空（字符） ： 返回0
		if (null == name || "".equals(name)) {
			return 0;
		}
		ClientService_MB clientService = null;
		List<Client> clientList = null;
		Client client = null;
		try {
			client = new Client();
			client.setName(name);
			clientService = (ClientService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CLIENTSERVICE);
			clientList = clientService.query(client);
			if (clientList != null && clientList.size() > 0) {// 查数据库，取到数据
				return clientList.get(0).getId();
			} else {// 如果通过给定的名称在数据库中没有查到数据，则以此名称新建一个客户
				client.setGrade(UiUtil.getCodeByValue("clientGrade", "1").getId() + "");// 默认为一级
				return clientService.saveOrUpdate(client);
			}
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(clientService);
		}
	}

	/**
	 * 通过SDH时隙表主键ID 获取 对应的端口主键ID
	 * 
	 * @param portStmId
	 * @return
	 */
	private int getPortId(int portStmId) throws ProcessingFailureException {
		PortStmTimeslotService_MB portStmTimeslotService = null;
		PortStmTimeslot portStmTimeslot = null;
		try {
			portStmTimeslotService = (PortStmTimeslotService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTSTMTIMESLOT);
			portStmTimeslot = portStmTimeslotService.selectById(portStmId);
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(portStmTimeslotService);
		}
		return portStmTimeslot.getPortid();
	}

	public String getOwner() {
		return owner;
	}

	/**
	 * 验证标签的可用行
	 * 
	 * @param aSiteId
	 *            A端网元Id
	 * @param aPortId
	 *            A端端口ID
	 * @param aLabelValue
	 *            A端的出标签
	 * @param zSiteId
	 *            Z端网元ID
	 * @param zPortId
	 *            Z端端口ID
	 * @param zLabelValue
	 *            Z端的出标签
	 * @return result true : A,Z端的标签可用 false ： 标签不可用
	 * @throws Exception
	 */
	public boolean checkLabel(int aSiteId, int aPortId, int aLabelValue, int zSiteId, int zPortId, int zLabelValue) throws Exception {
		List<Integer> labelList = new ArrayList<Integer>();// 判断标签是否可用

		boolean result = false;
		LabelInfoService_MB labelService = null;
		try {
			labelService = (LabelInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.LABELINFO);
			boolean flag = false;
			flag = labelService.isUsedLabel(aLabelValue, aSiteId, "TUNNEL");
			if (flag) {
				flag = labelService.checkingOutLabel(aLabelValue, zPortId, zSiteId, "TUNNEL");
			}

			// 如果flag为true，则表示标签可用，否则不可用
			if (!flag) {
				labelList.add(aLabelValue);
			}
			flag = labelService.isUsedLabel(zLabelValue, zSiteId, "TUNNEL");
			if (flag) {
				flag = labelService.checkingOutLabel(zLabelValue, aPortId, aSiteId, "TUNNEL");
			}
			if (!flag) {
				labelList.add(zLabelValue);
			}
			// 判断标签是否可用，： labelList
			if (labelList.size() == 0) {
				// 没有重复值，标签可用，就可下发入库
				result = true;
			}

		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(labelService);
		}
		return result;
	}

	/**
	 * 验证 给定 的 某个数是否在 给定的数组中
	 * 
	 * @param layers
	 *            数组：
	 * @param lay
	 *            要验证的数
	 * @return flag true: lay 存在数组layers中 false: 数组layers中不存在lay这个数
	 */
	public boolean isExitArrays(short[] layers, short lay) {
		boolean flag = false;// 默认传人的数不在数组中
		Arrays.sort(layers);// 对给定数组进行升向排序
		int index = -1;// 标记某个数在数组的位置，默认不在数组中。即：-1
		index = Arrays.binarySearch(layers, lay);
		if (index >= 0) {
			flag = true;// index>=0，在数组中
		}
		return flag;
	}

	/**
	 * 多个数组进行合并，返回合并后的数组
	 * 
	 * @param <T>
	 * @param first
	 * @param rest
	 * @return
	 */
	public <T> T[] concatAll(T[] first, T[]... rest) {
		int totalLength = first.length;
		for (T[] array : rest) {
			totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (T[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

	/**
	 * OAM标识符转换
	 * 
	 * @param oamInfo
	 *            OAM对象
	 * @return
	 * @throws Exception
	 */
	public NameAndStringValue_T[] convertOAMName(OamInfo oamInfo) throws Exception {
		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
		OamInfoService_MB oamInfoService  = null;
		try {
			name[0] = new NameAndStringValue_T(this.EMS, CorbaConfig.getInstanse().getCorbaEmsName());
			oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
			if (null != oamInfo.getOamMep() && oamInfo.getOamMep().getSiteId() > 0) {
				oamInfo = oamInfoService.queryByCondition(oamInfo, OamTypeEnum.AMEP);
				name[1] = new NameAndStringValue_T(this.MANAGEELEMENT, oamInfo.getOamMep().getSiteId() + "");
				name[2] = new NameAndStringValue_T(this.MEG, oamInfo.getOamMep().getObjType().toString()+"/"+oamInfo.getOamMep().getId() + "");
			}else if (null != oamInfo.getOamMip() && oamInfo.getOamMip().getSiteId() > 0) {
				oamInfo = oamInfoService.queryByCondition(oamInfo, OamTypeEnum.MIP);
				name[1] = new NameAndStringValue_T(this.MANAGEELEMENT, oamInfo.getOamMip().getSiteId() + "");
				name[2] = new NameAndStringValue_T(this.MEG, EOAMType.TUNNEL.toString()+"/"+oamInfo.getOamMip().getId() + "");
			}else if (null != oamInfo.getOamEthernetInfo() && oamInfo.getOamEthernetInfo().getSiteId() > 0) {
				name[1] = new NameAndStringValue_T(this.MANAGEELEMENT, oamInfo.getOamEthernetInfo().getSiteId() + "");
				name[2] = new NameAndStringValue_T(this.MEG, EOAMType.ETHERNET.toString()+"/"+oamInfo.getOamEthernetInfo().getId() + "");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
		 UiUtil.closeService_MB(oamInfoService);
		}
		return name;
	}

	/**
	 * 获取默认的AC流信息
	 * 
	 * @param siteId
	 * @return
	 */
	public List<Acbuffer> getAcBufferList(int siteId) {
		List<Acbuffer> acbufferList = new ArrayList<Acbuffer>();
		Acbuffer acbuffer = new Acbuffer();
		acbuffer.setBufferEnable(1);
		acbuffer.setVlanId(2);
		acbuffer.setSourceMac("00-00-00-00-00-01");
		acbuffer.setTargetMac("00-00-00-00-00-02");
		acbuffer.setEightIp(1);
		acbuffer.setSourceIp("10.18.1.1");
		acbuffer.setTargetIp("10.18.1.2");
		acbuffer.setPir(100);
		acbuffer.setPhb(59);
		acbuffer.setSiteId(siteId);
		acbufferList.add(acbuffer);
		return acbufferList;
	}
	/**
	 * 验证端口名称 ：  格式：/rack=1/shelf=1/slot=1/port=ge.1.1
	 * @param tpName
	 * @return
	 */
	public boolean checkPTPValue(String name){
		String portName="/rack=1/shelf=1/slot=/port=";
		String[] str1=name.split("/");
		String[] str2=portName.split("/");
		if(str1.length==str2.length&&str1[0].equals(str2[0])&&str1[1].equals(str2[1])&&str1[2].contains(str2[2])&&str1[3].contains(str2[3])){
			return true;
		}
		return false;
	}
	
	/**
	 * 转换MEG标识符
	 * @param megId		oamID
	 * @param neId		网元ID
	 * @param type		OAM类型
	 * @return
	 * @throws Exception
	 */
	public NameAndStringValue_T[] convertMEGName(int id,int neId,String type) throws Exception {
		NameAndStringValue_T[] name = new NameAndStringValue_T[3];
		name[0] = new NameAndStringValue_T(this.EMS, CorbaConfig.getInstanse().getCorbaEmsName());
		name[1]=new NameAndStringValue_T(this.MANAGEELEMENT, neId+"");
		name[2] = new NameAndStringValue_T("MEG", EOAMType.valueOf(type).getValue()+"/"+id);
		return name;
	}
	/**
	 * 通过主键ID，网元ID，类型，查询是否有配置OAM，在通过key,给返回附加信息里添加NameAndStringValue_T（key,id）
	 * @param id
	 * 			主键ID
	 * @param siteId
	 * 			网元ID（分A，Z 端）
	 * @param type
	 * 			类型（包括，Tunnel,PW,eline）
	 * @param key
	 * 			分为，AMAG,ZMAG//OamTypeEnum
	 * @return
	 * @throws ProcessingFailureException 
	 */
	public NameAndStringValue_T  getMegId(int id,int siteId,String type,String key,int oamType) throws ProcessingFailureException{
		OamInfoService_MB oamInfoService=null;
		OamMepInfo mepInfo=null;
		OamMipInfo mipInfo=null;
		NameAndStringValue_T name=new NameAndStringValue_T("","");
			try {
				oamInfoService = (OamInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.OamInfo);
				if(oamType==OamTypeEnum.MEP.getValue()){
					mepInfo=new OamMepInfo();
					mepInfo.setServiceId(id);
					mepInfo.setObjType(type);
					mepInfo.setSiteId(siteId);
					mepInfo=oamInfoService.queryMep(mepInfo);
					if(null!=mepInfo&& mepInfo.getId()>0){
						name=new NameAndStringValue_T(key,EOAMType.valueOf(EServiceType.valueOf(type).toString()).getValue()+"/"+mepInfo.getId());
					}
				}else if(oamType==OamTypeEnum.MIP.getValue()){
					mipInfo=new OamMipInfo();
					mipInfo.setServiceId(id);
					mipInfo.setObjType(type);
					mipInfo.setSiteId(siteId);
					mipInfo=oamInfoService.queryMep(mipInfo);
					if(null!=mipInfo&&mipInfo.getId()>0){
						name=new NameAndStringValue_T(key,EOAMType.valueOf(EServiceType.valueOf(type).toString()).getValue()+"/"+mepInfo.getId());
					}
				}else{
					throw new Exception("no find type");
				}
			} catch (Exception e) {
				throw new ProcessingFailureException(
						ExceptionType_T.EXCPT_INTERNAL_ERROR,
						"Internal error : The process has internal error!");
			} finally {
				UiUtil.closeService_MB(oamInfoService);
			}
		return name;
	}
}
