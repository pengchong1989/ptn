package com.nms.corba.ninterface.impl.service.tool;

import globaldefs.ConnectionDirection_T;
import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.List;

import subnetworkConnection.CrossConnect_T;
import subnetworkConnection.NetworkRouted_T;
import subnetworkConnection.Reroute_T;
import subnetworkConnection.RouteCreateData_T;
import subnetworkConnection.RouteDescriptor_T;
import subnetworkConnection.RouteDescriptor_THolder;
import subnetworkConnection.Route_THolder;
import subnetworkConnection.SNCCreateData_T;
import subnetworkConnection.SNCState_T;
import subnetworkConnection.SNCType_T;
import subnetworkConnection.StaticProtectionLevel_T;
import subnetworkConnection.SubnetworkConnection_T;
import subnetworkConnection.TPData_T;
import terminationPoint.TerminationMode_T;
import transmissionParameters.LayeredParameters_T;

import com.nms.corba.ninterface.enums.EOAMType;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.corba.ninterface.util.DateTimeUtil;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Lsp;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.ECesType;
import com.nms.db.enums.EPwType;
import com.nms.db.enums.EServiceType;
import com.nms.db.enums.OamTypeEnum;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

/**
 * corba接口，业务部分转换对象工具类
 * 
 * @author kk
 * 
 */
public class CorbaServiceConvrtTool extends CorbaConvertBase{

	// 交叉路由附加信息KEY值
	private  final String FULLROUTE="FullRoute";//路由是否完整
	private final String TDMTYPE="TDMType";//业务仿真类型





	/**
	 * tunnel集合转换成snc对象
	 * 
	 * @param tunnelList
	 *            tunnel集合
	 * @param sncArray
	 *            snc对象集合
	 * @return	sncList
	 * 		返回SubnetworkConnection_T的集合	
	 * @throws ProcessingFailureException
	 */
	public List<SubnetworkConnection_T> convertTunnelToSnc(List<Tunnel> tunnelList, SubnetworkConnection_T[] sncArray) throws ProcessingFailureException {
		int count = 0;
		List<SubnetworkConnection_T> sncList=new ArrayList<SubnetworkConnection_T>();
		try {
			if (UiUtil.isNull(tunnelList)) {
			//	count = super.getNullIndex(sncArray);
				for (Tunnel tunnel : tunnelList) {
					sncArray[count] = this.convertSnc(tunnel,true);
					sncList.add(sncArray[count]);
					count++;
				}
			}

		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error : The process has internal error!");
		}
		return sncList;
	}

	/**
	 * pw集合转换成snc对象
	 * 
	 * @param pwList
	 *            pw集合
	 * @param sncArray
	 *            snc对象集合
	 * @throws Exception
	 */
	public List<SubnetworkConnection_T> convertPwToSnc(List<PwInfo> pwList, SubnetworkConnection_T[] sncArray) throws ProcessingFailureException {
		int count = 0;
		List<SubnetworkConnection_T> sncList=new ArrayList<SubnetworkConnection_T>();
		try {
			if (UiUtil.isNull(pwList)) {
				//count = super.getNullIndex(sncArray);
				for (PwInfo pwInfo : pwList) {
					sncArray[count] = this.convertSnc(pwInfo,true);
					sncList.add(sncArray[count]);
					count++;
				}
			}
		}catch(ProcessingFailureException e){
				throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error : The process has internal error!");
		}
		return sncList;
	}
	/**
	 * pw集合转换成snc对象
	 * 
	 * @param cesList
	 *            ces集合
	 * @param sncArray
	 *            snc对象集合
	 * @throws Exception
	 */
	public List<SubnetworkConnection_T> convertCesToSnc(List<CesInfo> cesList, SubnetworkConnection_T[] sncArray) throws ProcessingFailureException {
		List<SubnetworkConnection_T> sncList=new ArrayList<SubnetworkConnection_T>();
		int count = 0;
		try {
			if (null != cesList && cesList.size() > 0) {
				//count = super.getNullIndex(sncArray);
				for (CesInfo cesInfo : cesList) {
					sncArray[count] = this.convertSnc(cesInfo);
					sncList.add(sncArray[count]);
					count++;
				}
			}

		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error : The process has internal error!");
		}
		return sncList;
	}

	/**
	 * 将tunnel 转为 corba的SubnetworkConnection_T对象
	 * 
	 * @param tunnel
	 * @return
	 */
	public SubnetworkConnection_T convertSnc(Tunnel tunnel,boolean flag) throws ProcessingFailureException{

		SubnetworkConnection_T subnetworkConnection = new SubnetworkConnection_T();
		subnetworkConnection.name = convertName(ELayerRate.TUNNEL.getValue(), tunnel.getTunnelId(), 0);
		subnetworkConnection.owner = super.getOwner();
		subnetworkConnection.userLabel = tunnel.getTunnelName();
		subnetworkConnection.nativeEMSName = tunnel.getTunnelName();
		subnetworkConnection.rate = (short) ELayerRate.TUNNEL.getValue();
		subnetworkConnection.direction = ConnectionDirection_T.CD_BI;
		subnetworkConnection.sncState = tunnel.getTunnelStatus() == EActiveStatus.ACTIVITY.getValue() ? SNCState_T.SNCS_ACTIVE : SNCState_T.SNCS_NONEXISTENT;
		subnetworkConnection.staticProtectionLevel = StaticProtectionLevel_T.PREEMPTIBLE;
		subnetworkConnection.rerouteAllowed = Reroute_T.RR_YES;// 是否允许重新路由

		subnetworkConnection.aEnd = new TPData_T[1];
		getTPDate(subnetworkConnection.aEnd, true, tunnel);
		subnetworkConnection.zEnd = new TPData_T[1];
		getTPDate(subnetworkConnection.zEnd, false, tunnel);

		subnetworkConnection.networkRouted = NetworkRouted_T.NR_YES;
		//其他不需要并且不可为空的属性
		subnetworkConnection.sncType=SNCType_T.ST_ADD_DROP_A;
		subnetworkConnection.additionalInfo=additionalCreationInfo( tunnel,flag);
		return subnetworkConnection;
	}

	/**
	 * pw转为 corba对象
	 * 
	 * @param pwInfo
	 * @return
	 */
	public SubnetworkConnection_T convertSnc(PwInfo pwInfo,boolean flag)throws ProcessingFailureException {
		SubnetworkConnection_T subnetworkConnection = new SubnetworkConnection_T();
		subnetworkConnection.name = convertName(ELayerRate.PW.getValue(), pwInfo.getPwId(), 0);
		subnetworkConnection.owner = super.getOwner();
		subnetworkConnection.userLabel = pwInfo.getPwName();
		subnetworkConnection.nativeEMSName = pwInfo.getPwName();
		subnetworkConnection.rate = (short) ELayerRate.PW.getValue();
		subnetworkConnection.direction = ConnectionDirection_T.CD_BI;
		subnetworkConnection.sncState = pwInfo.getPwStatus() == EActiveStatus.ACTIVITY.getValue() ? SNCState_T.SNCS_ACTIVE : SNCState_T.SNCS_NONEXISTENT;
		subnetworkConnection.staticProtectionLevel = StaticProtectionLevel_T.PREEMPTIBLE;
		subnetworkConnection.rerouteAllowed = Reroute_T.RR_YES;// 是否允许重新路由
		subnetworkConnection.aEnd = new TPData_T[1];
		getTPDate(subnetworkConnection.aEnd, true, pwInfo);
		subnetworkConnection.zEnd = new TPData_T[1];
		getTPDate(subnetworkConnection.zEnd, false, pwInfo);
		//其他不需要并且不可为空的属性
		subnetworkConnection.sncType=SNCType_T.ST_ADD_DROP_A;
		subnetworkConnection.networkRouted = NetworkRouted_T.NR_YES;
		subnetworkConnection.additionalInfo=additionalCreationInfo( pwInfo,flag);
		return subnetworkConnection;
	}
	

	/**
	 * 转换 a,z 端属性
	 * 
	 * @param flag
	 *            true A ,false Z
	 * @param object
	 * @return
	 */
	private void getTPDate(TPData_T[] tpData, boolean flag, Object object)  throws ProcessingFailureException {

		String ingressTrafficDescriptorName = "";
		String egressTrafficDescriptorName = "";
		Tunnel tunnel = null;
		PwInfo pwInfo = null;
		try {
			if (tpData.length == 0) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "TPData_T is null");
			}
			tpData[0]=new TPData_T();
			tpData[0].tpMappingMode = TerminationMode_T.TM_NA;// 映射方式。
			tpData[0].ingressTrafficDescriptorName = new NameAndStringValue_T[1];
			tpData[0].egressTrafficDescriptorName = new NameAndStringValue_T[1];
			tpData[0].transmissionParams=new LayeredParameters_T[0];
			if (flag) {// A 端
				if (object instanceof Tunnel) {
					tunnel = (Tunnel) object;
					tpData[0].tpName = super.convertName(ELayerRate.PORT.getValue(), tunnel.getAPortId(), tunnel.getASiteId());
					ingressTrafficDescriptorName = tunnel.getLspParticularList().get(0).getBackLabelValue() + "";
					egressTrafficDescriptorName = tunnel.getLspParticularList().get(0).getFrontLabelValue() + "";
				} else if (object instanceof PwInfo) {
					pwInfo = (PwInfo) object;
					tpData[0].tpName = super.convertName(ELayerRate.TUNNEL.getValue(), pwInfo.getTunnelId(), 0);
					ingressTrafficDescriptorName = pwInfo.getOutlabelValue() + "";
					egressTrafficDescriptorName = pwInfo.getInlabelValue() + "";
					
				}
				tpData[0].ingressTrafficDescriptorName[0] = new NameAndStringValue_T(this.SRCINLABEL, ingressTrafficDescriptorName); // 入业务描述符。
				tpData[0].egressTrafficDescriptorName[0] = new NameAndStringValue_T(this.SRCOUTLABEL, egressTrafficDescriptorName);// 出业务描述符。
				
			} else {// Z端
				if (object instanceof Tunnel) {
					tunnel = (Tunnel) object;
					tpData[0].tpName = convertName(ELayerRate.PORT.getValue(), tunnel.getZPortId(), tunnel.getZSiteId());
					ingressTrafficDescriptorName = tunnel.getLspParticularList().get(tunnel.getLspParticularList().size() - 1).getFrontLabelValue() + "";
					egressTrafficDescriptorName = tunnel.getLspParticularList().get(tunnel.getLspParticularList().size() - 1).getBackLabelValue() + "";
					
				} else if (object instanceof PwInfo) {
					pwInfo = (PwInfo) object;
					tpData[0].tpName = convertName(ELayerRate.TUNNEL.getValue(), pwInfo.getTunnelId(), 0);
					ingressTrafficDescriptorName = pwInfo.getInlabelValue() + "";
					egressTrafficDescriptorName = pwInfo.getOutlabelValue() + "";
				}
				tpData[0].ingressTrafficDescriptorName[0] = new NameAndStringValue_T(this.DESTINLABEL, ingressTrafficDescriptorName); // 入业务描述符。
				tpData[0].egressTrafficDescriptorName[0] = new NameAndStringValue_T(this.DESTOUTLABEL, egressTrafficDescriptorName);// 出业务描述符。
				
			}

		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error : The process has internal error!");
		}

	}

	/**
	 * 
	 * 通用 -附加信息
	 * 
	 * @param subnetworkConnection
	 *            corba对象
	 * @param object
	 *            传人对象类型
	 * @throws ProcessingFailureException 
	 */
	private NameAndStringValue_T[] additionalCreationInfo( Object object,boolean flag) throws ProcessingFailureException {
		Tunnel tunnel = null;
		PwInfo pwInfo = null;
		CesInfo cesInfo=null;
		List<QosInfo> qosList=null;
		NameAndStringValue_T[] additionalInfo = null;
		if (object instanceof Tunnel) {// tunnel
			tunnel = (Tunnel) object;
			if(UiUtil.isNull(tunnel.getOamList())&&flag){
				additionalInfo= new NameAndStringValue_T[19];
				additionalInfo[17]=super.getMegId(tunnel.getTunnelId(), tunnel.getASiteId(), EServiceType.TUNNEL.toString(), super.AMEG,OamTypeEnum.MEP.getValue());
				additionalInfo[18]=super.getMegId(tunnel.getTunnelId(), tunnel.getZSiteId(), EServiceType.TUNNEL.toString(), super.ZMEG,OamTypeEnum.MEP.getValue());
			}else{
				additionalInfo= new NameAndStringValue_T[17];
			}
			
			qosList=tunnel.getQosList();
			super.converQos(additionalInfo, qosList);//设置qos信息
			additionalInfo[14] = new NameAndStringValue_T(this.CREATEUSER, conductNull(tunnel.getCreateUser()));//
			additionalInfo[15] = new NameAndStringValue_T(this.CREATETIME, DateTimeUtil.converCorbaTime(tunnel.getCreateTime(),DateUtil.FULLTIME));// 创建时间tunnel.getCreateTime());
			additionalInfo[16] = new NameAndStringValue_T(this.FULLROUTE, tunnel.getLspParticularList().size() > 1 ? "false" : "true");// 是否为完整路由					
			
		} else if (object instanceof PwInfo) {// pw
			pwInfo = (PwInfo) object;
			qosList=pwInfo.getQosList();
			if(UiUtil.isNull(pwInfo.getOamList())){
				additionalInfo = new NameAndStringValue_T[21];
				additionalInfo[19]=super.getMegId(pwInfo.getPwId(), pwInfo.getASiteId(), EServiceType.PW.toString(),super.AMEG,OamTypeEnum.MEP.getValue());
				additionalInfo[20]=super.getMegId(pwInfo.getPwId(), pwInfo.getZSiteId(), EServiceType.PW.toString(),super.ZMEG,OamTypeEnum.MEP.getValue());
			}else{
				additionalInfo = new NameAndStringValue_T[19];
			}						
			super.converQos(additionalInfo, qosList);//设置qos信息
			additionalInfo[14]=new NameAndStringValue_T(this.CREATEUSER,conductNull(pwInfo.getCreateUser()));//
			additionalInfo[15] = new NameAndStringValue_T(this.CREATETIME, DateTimeUtil.converCorbaTime(pwInfo.getCreateTime(),DateUtil.FULLTIME));// 创建时间
			additionalInfo[16] = new NameAndStringValue_T(super.ISACTIVE, pwInfo.getPwStatus()+"");// 激活状态
			additionalInfo[17]=new NameAndStringValue_T(this.SERVERCONNECTIONS,ELayerRate.TUNNEL.getValue()+"/"+pwInfo.getTunnelId());//
			additionalInfo[18]=new NameAndStringValue_T(this.PWTYPE,pwInfo.getType().toString());//pw类型
			
		}else if(object instanceof CesInfo){
			cesInfo=(CesInfo)object;
			additionalInfo= new NameAndStringValue_T[6];
			additionalInfo[0] = new NameAndStringValue_T(this.CREATETIME, DateTimeUtil.converCorbaTime(cesInfo.getCreateTime(),DateUtil.FULLTIME));// 创建时间
			additionalInfo[1] = new NameAndStringValue_T(this.CREATEUSER, conductNull(cesInfo.getCreateUser()));//
			additionalInfo[2]=new NameAndStringValue_T(this.SERVERCONNECTIONS,ELayerRate.PW.getValue()+"/"+cesInfo.getPwId());//ces关联的pw主键ID
			additionalInfo[3] = new NameAndStringValue_T(this.CESTYPE,ECesType.forms(cesInfo.getCestype()).toString());//
			additionalInfo[4] = new NameAndStringValue_T(super.CLIENTNAME,super.queryById(cesInfo.getClientId()));//客户名称
			additionalInfo[5] = new NameAndStringValue_T(this.TDMTYPE,"E1");//业务仿真类型
		}		
		return additionalInfo;
	}

	/**
	 * 创建时把SNC转换为tunnel
	 * 
	 * @param sncCreateData
	 *            corba的SNC对象
	 * @return 转换后的tunnel
	 * @throws Exception
	 */
	public Tunnel convertTunnel(SNCCreateData_T sncCreateData,ICorbaSession userSession) throws ProcessingFailureException {
		Tunnel tunnel = null;
		int aSiteId=0;
		int zSiteId=0;
		int aPortId=0;
		int zPortId=0;
		int aLabelValue=0;
		int zLabelValue=0;
		try {
			aSiteId=Integer.parseInt(super.getValueByKey(sncCreateData.aEnd[0], super.MANAGEELEMENT));
			aPortId=Integer.parseInt(super.getValueByKey(sncCreateData.aEnd[0], super.PTP,aSiteId));
			aLabelValue=Integer.parseInt(super.getValueByKey(sncCreateData.additionalCreationInfo, this.SRCINLABEL));
			zSiteId=Integer.parseInt(super.getValueByKey(sncCreateData.zEnd[0], super.MANAGEELEMENT));
			zPortId=Integer.parseInt(super.getValueByKey(sncCreateData.zEnd[0], super.PTP,zSiteId));
			zLabelValue=Integer.parseInt(super.getValueByKey(sncCreateData.additionalCreationInfo, this.DESTINLABEL));
			boolean flag=super.checkLabel(aSiteId, aPortId, aLabelValue, zSiteId, zPortId, zLabelValue)	;
			if(!flag){
				throw new ProcessingFailureException(
						ExceptionType_T.EXCPT_INVALID_INPUT,
						"The Label Unused");
			}
			tunnel = new Tunnel();
			tunnel.setTunnelName(sncCreateData.userLabel);
			tunnel.setIsReverse(1);
			tunnel.setPosition(1);
			tunnel.setProtectBack(1);
			tunnel.setASiteId(aSiteId);//A端网元
			tunnel.setAPortId(aPortId);//A 端端口
			tunnel.setZSiteId(zSiteId);
			tunnel.setZPortId(zPortId);
			tunnel.setTunnelType( UiUtil.getCodeByValue("PROTECTTYPE", "1").getId()+"");// 普通类型
			// 附加信息
			tunnel.setTunnelStatus(Integer.parseInt(super.getValueByKey(sncCreateData.additionalCreationInfo, super.ISACTIVE)));// 激活状态 1 为激活，2 未激活
			tunnel.setQosList(super.converQosList(sncCreateData.additionalCreationInfo));
			//tunnel.setCreateTime(super.getValueByKey(sncCreateData.additionalCreationInfo, super.CREATETIME));//创建时间
			tunnel.setCreateUser(userSession.getUserName());
			//获取LSP信息
			
			tunnel.setLspParticularList(this.convertLspToRoute(tunnel, aLabelValue, 
										Integer.parseInt(super.getValueByKey(sncCreateData.additionalCreationInfo, this.SRCOUTLABEL)), 
										zLabelValue,
										Integer.parseInt(super.getValueByKey(sncCreateData.additionalCreationInfo, this.DESTOUTLABEL)), 
										sncCreateData.ccInclusions)
										);
			
		}catch (ProcessingFailureException e) {
			throw e;
		}catch(Exception e){
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error : The process has internal error!");
		}
		finally {
		}

		return tunnel;
	}

	/**
	 * 创建时把SNC转换为pwinfo对象
	 * 
	 * @param sncCreateData
	 *            corba的SNC对象
	 * @return 转换后的pw对象
	 * @throws Exception
	 */
	public PwInfo convertPwinfo(SNCCreateData_T sncCreateData,ICorbaSession userSession) throws ProcessingFailureException {
		PwInfo pwInfo = null;
		String pwType="";
		try {
			pwType=super.getValueByKey(sncCreateData.additionalCreationInfo, this.PWTYPE);
			if(EPwType.valueOf(pwType).getValue()<=0||EPwType.valueOf(pwType).getValue()>5){
				throw new ProcessingFailureException(
						ExceptionType_T.EXCPT_INVALID_INPUT,
						"Internal error : PwType is invalid!");
			}
			pwInfo = new PwInfo();
			pwInfo.setType(EPwType.valueOf(pwType));
			pwInfo.setPwName(sncCreateData.userLabel);
			pwInfo.setPayload(496);
			pwInfo.setASiteId(Integer.parseInt(super.getValueByKey(sncCreateData.aEnd[0], this.MANAGEELEMENT)));
			pwInfo.setZSiteId(Integer.parseInt(super.getValueByKey(sncCreateData.zEnd[0], this.MANAGEELEMENT)));
			pwInfo.setZoppositeId(getSiteIp(pwInfo.getASiteId()));
			pwInfo.setAoppositeId(getSiteIp(pwInfo.getZSiteId()));		
			// 附加信息
			pwInfo.setQosList(super.converQosList(sncCreateData.additionalCreationInfo));//获取qos信息
			//pwInfo.setCreateTime(DateUtil.getDate(DateUtil.FULLTIME));
			pwInfo.setCreateUser(userSession.getUserName());
			pwInfo.setPwStatus(Integer.parseInt(super.getValueByKey(sncCreateData.additionalCreationInfo, super.ISACTIVE)));//激活状态
			pwInfo.setTunnelId(super.converByElaytoId(ELayerRate.TUNNEL.getValue(),super.getValueByKey(sncCreateData.additionalCreationInfo, this.SERVERCONNECTIONS)));//pw关联的tunnel主键ID
			pwInfo.setType(EPwType.valueOf(super.getValueByKey(sncCreateData.additionalCreationInfo, this.PWTYPE)));//pw类型
			pwInfo.setInlabelValue(Integer.parseInt(super.getValueByKey(sncCreateData.additionalCreationInfo, this.SRCOUTLABEL)));//出标签
			pwInfo.setOutlabelValue(Integer.parseInt(super.getValueByKey(sncCreateData.additionalCreationInfo, this.SRCINLABEL)));//入标签标签
		} catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error : The process has internal error!");
		} finally {

		}
		return pwInfo;
	}

	/**
	 * 把tunnel对象转换成路由信息(XC)
	 * 
	 * @param tunnel
	 *            tunnel对象
	 * @param route_THolder
	 *            corba的路由对象
	 * @throws Exception
	 */
	public void convertRouteToTunnel(Tunnel tunnel, Route_THolder route_THolder) throws ProcessingFailureException { //		if(route_THolder.value.length<=0){
//			throw new ProcessingFailureException(
//					ExceptionType_T.EXCPT_INTERNAL_ERROR,
//					"输入参数错误");
//		}
		int count = 0; // CrossConnect_T数组的记数器
		try {
			route_THolder.value = new CrossConnect_T[tunnel.getLspParticularList().size() - 1];

			// 遍历所有lsp 把lsp对象转换层
			for (Lsp lsp : tunnel.getLspParticularList()) {
				// 当计数器大于0 说明已经有路由信息，那么先给上一条路由信息赋Z端值。 因为DB中的结构，中间网元的A端值为lsp.get(0)的Z端。 中间网元Z端值为LSP.get(1)的A端
				if (count > 0) {
					route_THolder.value[count - 1].zEndNameList=new NameAndStringValue_T[1][];
					route_THolder.value[count - 1].zEndNameList[0] = this.convertName(ELayerRate.PORT.getValue(), lsp.getAPortId(), lsp.getASiteId());
					route_THolder.value[count - 1].additionalInfo[2] = new NameAndStringValue_T(this.DESTINLABEL, lsp.getBackLabelValue() + "");
					route_THolder.value[count - 1].additionalInfo[3] = new NameAndStringValue_T(this.DESTOUTLABEL, lsp.getFrontLabelValue() + "");
				}

				// 如果此条lsp不是tunnel的最后一站，才去创建中间网元的实例
				if (lsp.getZSiteId() != tunnel.getZSiteId()) {
					route_THolder.value[count] = new CrossConnect_T();
					//设置  没用到的 默认值
					route_THolder.value[count].ccType=SNCType_T.ST_SIMPLE;					
					route_THolder.value[count].active = tunnel.getTunnelStatus() == EActiveStatus.ACTIVITY.getValue() ? true : false;
					route_THolder.value[count].direction = ConnectionDirection_T.CD_BI;
					route_THolder.value[count].aEndNameList=new NameAndStringValue_T[1][];
					route_THolder.value[count].aEndNameList[0] = this.convertName(ELayerRate.PORT.getValue(), lsp.getZPortId(), lsp.getZSiteId());
					if(UiUtil.isNull(tunnel.getOamList())){
						route_THolder.value[count].additionalInfo = new NameAndStringValue_T[5];
						route_THolder.value[count].additionalInfo[4]=super.getMegId(tunnel.getTunnelId(), tunnel.getASiteId(), EServiceType.TUNNEL.toString(), super.MEG, EOAMType.MIP.getValue());
					}else{
						route_THolder.value[count].additionalInfo = new NameAndStringValue_T[4];
					}
					
					route_THolder.value[count].additionalInfo[0] = new NameAndStringValue_T(this.SRCINLABEL, lsp.getFrontLabelValue() + "");
					route_THolder.value[count].additionalInfo[1] = new NameAndStringValue_T(this.SRCOUTLABEL, lsp.getBackLabelValue() + "");
					count++;
				}
			}

		} catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error : The process has internal error!");
		}
	}

	/**
	 * 通过交叉路由信息，转换成LSP集合  创建时用
	 * @param tunnel  已经组成的tunnel对象，AZ的网元ID、端口ID必须存在
	 * @param inLabel_a tunnel A端的输入标签
	 * @param outLabel_a tuunelA端的输出标签
	 * @param inLabel_z tunnel Z端的输入标签
	 * @param outLabel_z tunnel z端的输出标签
	 * @param crossConnectArray corba的交叉路由信息
	 * @return 数据库端的lsp集合
	 * @throws Exception
	 */
	public List<Lsp> convertLspToRoute(Tunnel tunnel, int inLabel_a, int outLabel_a, int inLabel_z, int outLabel_z, CrossConnect_T[] crossConnectArray) throws ProcessingFailureException {
		List<Lsp> lspList = null;
		Lsp lsp = null;
		try {
			lspList = new ArrayList<Lsp>();
			//第一条LSP的A端信息等于tunnel的A端信息
			lsp = new Lsp();
			lsp.setASiteId(tunnel.getASiteId());
			lsp.setAPortId(tunnel.getAPortId());
			lsp.setFrontLabelValue(outLabel_a);
			lsp.setBackLabelValue(inLabel_a);
			if(crossConnectArray!=null){
				//遍历所有交叉点路由信息。每遍历一个，创建一个lsp信息
				for(int i = 0 ; i < crossConnectArray.length ; i++){				
					lsp = this.selectLsp(lsp, crossConnectArray, lspList);					
				}
			}
						
			//最后一条lsp的Z端信息，等于tunnel的Z端信息
			if (lsp!=null&&lsp.getFrontLabelValue() == inLabel_z && lsp.getBackLabelValue() == outLabel_z) {
				lsp.setZSiteId(tunnel.getZSiteId());
				lsp.setZPortId(tunnel.getZPortId());
				lsp.setAoppositeId(this.getSiteIp(lsp.getZSiteId()));
				lsp.setZoppositeId(this.getSiteIp(lsp.getASiteId()));
				lspList.add(lsp);
			}else {
				throw new ProcessingFailureException(
						ExceptionType_T.EXCPT_INVALID_INPUT,
						"Internal error : The process has internal error!");
			}
			

		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error : The process has internal error!");
		}
		return lspList;
	}
	
	/**
	 * 在corba的路由数组中找到匹配的lsp的Z端数据
	 * @param lsp 存在A端数据的LSP信息
	 * @param crossConnectArray corba的路由数组
	 * @param lspList 找到的结果放入此参数中
	 * @return 新的lsp对象 存在A端数据
	 * @throws NumberFormatException 
	 * @throws Exception
	 */
	private Lsp selectLsp(Lsp lsp, CrossConnect_T[] crossConnectArray, List<Lsp> lspList) throws NumberFormatException, Exception {
		Lsp lsp_new = null;
		for (CrossConnect_T crossConnect : crossConnectArray) {
			//如果lsp的前向标签和后向标签与路由信息的源出入标签吻合。 就把lsp的Z端信息赋值。并且创建一个新的lsp对象。A端值为此路由信息的宿信息
			if(UiUtil.isNull(super.getValueByKey(crossConnect.additionalInfo, this.SRCINLABEL))&&UiUtil.isNull(super.getValueByKey(crossConnect.additionalInfo, this.SRCOUTLABEL))){
				if (lsp.getFrontLabelValue() == Integer.parseInt(super.getValueByKey(crossConnect.additionalInfo, this.SRCINLABEL)) && lsp.getBackLabelValue() == Integer.parseInt(super.getValueByKey(crossConnect.additionalInfo, this.SRCOUTLABEL))) {
					//lsp的Z端信息赋值
					boolean flag=super.checkLabel(lsp.getASiteId(), lsp.getZPortId(), lsp.getFrontLabelValue(),
							lsp.getZSiteId(), lsp.getZPortId(), Integer.parseInt(super.getValueByKey(crossConnect.additionalInfo, this.DESTINLABEL)));
					if(!flag){
						throw new ProcessingFailureException(
								ExceptionType_T.EXCPT_INVALID_INPUT,
								"The Label Unused!");
					}
					lsp.setZSiteId(Integer.parseInt(super.getValueByKey(crossConnect.aEndNameList[0], this.MANAGEELEMENT)));
					lsp.setAoppositeId(this.getSiteIp(lsp.getZSiteId()));
					lsp.setZPortId(Integer.parseInt(super.getValueByKey(crossConnect.aEndNameList[0], this.PTP,lsp.getZSiteId())));
					lsp.setZoppositeId(this.getSiteIp(lsp.getASiteId()));
					lspList.add(lsp);
					
					//创建一个新的LSP对象，lsp的A端为此路由的宿端信息
					lsp_new = new Lsp();
					lsp_new.setASiteId(Integer.parseInt(super.getValueByKey(crossConnect.zEndNameList[0], this.MANAGEELEMENT)));
					lsp_new.setAPortId(Integer.parseInt(super.getValueByKey(crossConnect.zEndNameList[0], this.PTP,lsp_new.getASiteId())));
					lsp_new.setFrontLabelValue(Integer.parseInt(super.getValueByKey(crossConnect.additionalInfo, this.DESTOUTLABEL)));
					lsp_new.setBackLabelValue(Integer.parseInt(super.getValueByKey(crossConnect.additionalInfo, this.DESTINLABEL)));
					break;
				}
			}			
		}
		return lsp_new;
	}

	public static void main(String[] args) {

		Tunnel tunnel = new Tunnel();
		tunnel.setASiteId(201);
		tunnel.setAPortId(101);
		tunnel.setZSiteId(202);
		tunnel.setZPortId(102);

		CrossConnect_T[] crossConnectArray = new CrossConnect_T[2];
		CrossConnect_T crossConnect_T = new CrossConnect_T();
		crossConnect_T.additionalInfo=new NameAndStringValue_T[4];
		crossConnect_T.additionalInfo[0] = new NameAndStringValue_T("SrcInLabel", "16");
		crossConnect_T.additionalInfo[1] = new NameAndStringValue_T("SrcOutLabel", "21");
		crossConnect_T.additionalInfo[2] = new NameAndStringValue_T("DestInLabel", "20");
		crossConnect_T.additionalInfo[3] = new NameAndStringValue_T("DestOutLabel", "17");
		crossConnectArray[0] = crossConnect_T;
		
		crossConnect_T = new CrossConnect_T();
		crossConnect_T.additionalInfo=new NameAndStringValue_T[4];
		crossConnect_T.additionalInfo[0] = new NameAndStringValue_T("SrcInLabel", "17");
		crossConnect_T.additionalInfo[1] = new NameAndStringValue_T("SrcOutLabel", "20");
		crossConnect_T.additionalInfo[2] = new NameAndStringValue_T("DestInLabel", "19");
		crossConnect_T.additionalInfo[3] = new NameAndStringValue_T("DestOutLabel", "18");
		crossConnectArray[1] = crossConnect_T;
		
		CorbaServiceConvrtTool corbaServiceConvrtTool=new CorbaServiceConvrtTool();
		try {
			corbaServiceConvrtTool.convertLspToRoute(tunnel, 21, 16, 18, 19, crossConnectArray);
		} catch (Exception e) {
			ExceptionManage.dispose(e,CorbaServiceConvrtTool.class);
		}

	}
	/**
	 * 根据网元 ID 获取网元IP
	 * @param String
	 * @return
	 */
	private String getSiteIp(int siteId){
		SiteInst siteInst=null;
		List<SiteInst> siteInstList=null;
		SiteService_MB siteService=null;
		try{
			siteInst=new SiteInst();
			siteInst.setSite_Inst_Id(siteId);
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInstList=siteService.select(siteInst);
			if(siteInstList!=null&&siteInstList.size()==1){
				siteInst=siteInstList.get(0);
			}else{
				throw new ProcessingFailureException(
						ExceptionType_T.EXCPT_INVALID_INPUT,
						"查询网元IP 出错！！");
			}
		}catch(Exception e){
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(siteService);
		}
		return siteInst.getCellDescribe();
	}
	/**
	 * 将ces对象转为corba对象
	 * @param cesInfo
	 * @return
	 * @throws ProcessingFailureException 
	 */
	public SubnetworkConnection_T convertSnc(CesInfo cesInfo) throws ProcessingFailureException {
		SubnetworkConnection_T subnetworkConnection = new SubnetworkConnection_T();
		subnetworkConnection.name = convertName(ELayerRate.CES.getValue(), cesInfo.getPwId(), 0);
		subnetworkConnection.owner = super.getOwner();
		subnetworkConnection.userLabel = cesInfo.getName();
		subnetworkConnection.nativeEMSName = cesInfo.getName();
		subnetworkConnection.rate = (short) ELayerRate.CES.getValue();
		subnetworkConnection.direction = ConnectionDirection_T.CD_BI;
		subnetworkConnection.sncState =cesInfo.getActiveStatus() == EActiveStatus.ACTIVITY.getValue() ? SNCState_T.SNCS_ACTIVE : SNCState_T.SNCS_NONEXISTENT;
		subnetworkConnection.staticProtectionLevel = StaticProtectionLevel_T.PREEMPTIBLE;
		subnetworkConnection.rerouteAllowed = Reroute_T.RR_YES;// 是否允许重新路由
		subnetworkConnection.aEnd = new TPData_T[1];
		subnetworkConnection.aEnd[0]=this.getTPData();
		subnetworkConnection.zEnd =new TPData_T[1];
		subnetworkConnection.zEnd[0]=this.getTPData();
		NameAndStringValue_T[] aEndTpName=null;
		NameAndStringValue_T[] zEndTpName=null;
		if(cesInfo.getCestype()==ECesType.PDH.getValue()||cesInfo.getCestype()==ECesType.PDHSDH.getValue()){//pdh类型ces,A,Z端网元，端口命名方式为PTP:			
			aEndTpName=convertName(ELayerRate.PORT.getValue(), cesInfo.getaAcId(), cesInfo.getaSiteId());//网元+ac端口		
			zEndTpName=convertName(ELayerRate.PORT.getValue(), cesInfo.getzAcId(), cesInfo.getzSiteId());//网元+ac端口
		}else if(cesInfo.getCestype()==ECesType.SDH.getValue()){//sdh类型ces,A,Z端网元，端口命名方式为CTP:
			aEndTpName=convertName(ELayerRate.CTP.getValue(), cesInfo.getaAcId(), cesInfo.getaSiteId());//网元+ac端口		
			zEndTpName=convertName(ELayerRate.CTP.getValue(), cesInfo.getzAcId(), cesInfo.getzSiteId());//网元+ac端口
		}else if(cesInfo.getCestype()==ECesType.SDHPDH.getValue()){//sdh-pdh类型ces,A,端网元，端口命名方式为CTP:,Z端网元，端口命名方式为PTP:
			aEndTpName=convertName(ELayerRate.CTP.getValue(), cesInfo.getaAcId(), cesInfo.getaSiteId());//网元+ac端口		
			zEndTpName=convertName(ELayerRate.PORT.getValue(), cesInfo.getzAcId(), cesInfo.getzSiteId());//网元+ac端口
		}else if(cesInfo.getCestype()==ECesType.PDHSDH.getValue()){//pdh-sdh类型ces,A,端网元，端口命名方式为PTP:,Z端网元，端口命名方式为CTP:
			aEndTpName=convertName(ELayerRate.PORT.getValue(), cesInfo.getaAcId(), cesInfo.getaSiteId());//网元+ac端口		
			zEndTpName=convertName(ELayerRate.CTP.getValue(), cesInfo.getzAcId(), cesInfo.getzSiteId());//网元+ac端口
		}else {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR, "no find type!");
		}
		subnetworkConnection.aEnd[0].tpName=aEndTpName;
		subnetworkConnection.zEnd[0].tpName=zEndTpName;	
		
		//其他不需要并且不可为空的属性
		subnetworkConnection.sncType=SNCType_T.ST_ADD_DROP_A;
		subnetworkConnection.networkRouted = NetworkRouted_T.NR_YES;
		subnetworkConnection.additionalInfo=additionalCreationInfo( cesInfo,false);
		return subnetworkConnection;
	}
	/**
	 *将corba对象转为CES 如：新建
	 * @param sncCreateData
	 *+	 * @return
	 * @throws Exception
	 */
	public CesInfo convertCesinfo(SNCCreateData_T sncCreateData,ICorbaSession userSession) throws ProcessingFailureException {
		CesInfo cesInfo=null;
		int cesType=0;
		int aAcId = 0;
		int zAcId = 0;
		String TDMType="";
		try {
			TDMType=super.getValueByKey(sncCreateData.additionalCreationInfo, this.TDMTYPE);
			if(!TDMType.equals("E1")){
				throw new ProcessingFailureException(
						ExceptionType_T.EXCPT_INVALID_INPUT,
						"TDMType is invalid! ");
			}
			cesInfo=new CesInfo();
			cesInfo.setName(sncCreateData.userLabel);
			cesType=ECesType.valueOf(super.getValueByKey(sncCreateData.additionalCreationInfo,this.CESTYPE)).getValue();
			cesInfo.setCestype(cesType);//ces类型
			cesInfo.setaSiteId(Integer.parseInt(super.getValueByKey(sncCreateData.aEnd[0], this.MANAGEELEMENT)));//Z端 网元主键ID
			cesInfo.setzSiteId(Integer.parseInt(super.getValueByKey(sncCreateData.zEnd[0], this.MANAGEELEMENT)));//A端 网元主键ID			
			if(cesType==ECesType.PDH.getValue()){
				aAcId=Integer.parseInt(super.getValueByKey(sncCreateData.aEnd[0], this.PTP,cesInfo.getaSiteId()));
				zAcId=Integer.parseInt(super.getValueByKey(sncCreateData.zEnd[0], this.PTP,cesInfo.getzSiteId()));
			}else if(cesType==ECesType.SDH.getValue()){
				aAcId=Integer.parseInt(super.getValueByKey(sncCreateData.aEnd[0], this.CTP));
				zAcId=Integer.parseInt(super.getValueByKey(sncCreateData.zEnd[0], this.CTP));
			}else if(cesType==ECesType.SDHPDH.getValue()){
				aAcId=Integer.parseInt(super.getValueByKey(sncCreateData.aEnd[0], this.CTP));
				zAcId=Integer.parseInt(super.getValueByKey(sncCreateData.zEnd[0], this.PTP,cesInfo.getzSiteId()));
			}else if(cesType==ECesType.PDHSDH.getValue()){
				aAcId=Integer.parseInt(super.getValueByKey(sncCreateData.aEnd[0], this.PTP,cesInfo.getaSiteId()));
				zAcId=Integer.parseInt(super.getValueByKey(sncCreateData.zEnd[0], this.CTP));
			}else {
				throw new ProcessingFailureException(
						ExceptionType_T.EXCPT_INVALID_INPUT,
						"Internal error : The process has internal error!");
			}			
			cesInfo.setaAcId(aAcId);//A端   端口/ac  主键ID
			cesInfo.setzAcId(zAcId);//Z端   端口/ac  主键ID
		//	cesInfo.setCreateTime(super.getValueByKey(sncCreateData.additionalCreationInfo, this.CREATETIME));//创建时间
			cesInfo.setCreateUser(userSession.getUserName());//创建人
			cesInfo.setPwId(super.converByElaytoId(ELayerRate.PW.getValue(),super.getValueByKey(sncCreateData.additionalCreationInfo, super.SERVERCONNECTIONS)));//ces关联的pw主键ID			
			cesInfo.setClientId(super.queryByName(super.getValueByKey(sncCreateData.additionalCreationInfo,super.CLIENTNAME)));//客户名称
			cesInfo.setActiveStatus(Integer.parseInt(super.getValueByKey(sncCreateData.additionalCreationInfo,super.ISACTIVE)));//激活状态
		} catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error : The process has internal error! ");
		} finally {

		}
		return cesInfo;
	}
	
	/**
	 * RouteCreateData_T转换TUNNEL对象
	 * @param createRoute
	 * @return
	 * @throws Exception 
	 */
	public Tunnel convertTunnel(Tunnel tunnel,RouteCreateData_T createRoute) throws ProcessingFailureException {
		Tunnel protectTunnel;
		String waitTime;
		String delayTime;
		try {
			protectTunnel = new Tunnel();
			waitTime = super.getValueByKey(createRoute.additionalCreationInfo, "WaitTime");
			delayTime = super.getValueByKey(createRoute.additionalCreationInfo, "DelayTime");
			if(null != waitTime && !"".equals(waitTime))
				tunnel.setWaittime(Integer.parseInt(waitTime));
			
			if(null != waitTime && !"".equals(delayTime))
				tunnel.setDelaytime(Integer.parseInt(delayTime));
			
			tunnel.setTunnelType(UiUtil.getCodeByValue("PROTECTTYPE", "2").getId()+"");
			
			protectTunnel.setASiteId(Integer.parseInt(createRoute.neTpInclusions[0][1].value));
			protectTunnel.setAPortId(Integer.parseInt(createRoute.neTpInclusions[0][2].value));
			protectTunnel.setZSiteId(Integer.parseInt(createRoute.neTpInclusions[1][1].value));
			protectTunnel.setZPortId(Integer.parseInt(createRoute.neTpInclusions[1][2].value));
			protectTunnel.setIsSingle(0);
			protectTunnel.setTunnelType("0");
			protectTunnel.setLspParticularList(this.convertLspToRoute(tunnel, 
					Integer.parseInt(this.getValueByKey(createRoute.additionalCreationInfo, this.SRCINLABEL)), 
					Integer.parseInt(super.getValueByKey(createRoute.additionalCreationInfo, this.SRCOUTLABEL)), 
					Integer.parseInt(this.getValueByKey(createRoute.additionalCreationInfo, this.DESTINLABEL)),
					Integer.parseInt(super.getValueByKey(createRoute.additionalCreationInfo, this.DESTOUTLABEL)), createRoute.ccInclusions));
			protectTunnel.setQosList(tunnel.getQosList());
			tunnel.setProtectTunnel(protectTunnel);//保护对象转换
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"Internal error : The process has internal error!");
		}
		return tunnel;
	}
	
	/**
	 * 转换TNP模型
	 * @param tunnel tunnel对象
	 * @param theRoute 网络路径保护对象
	 * @throws ProcessingFailureException 
	 */
	public void convertTNP(Tunnel tunnel,RouteDescriptor_THolder theRoute) throws ProcessingFailureException {
		int size = tunnel.getLspParticularList().size()-1;	//crossConnect_T size
		theRoute.value = new RouteDescriptor_T();
		theRoute.value.id = ELayerRate.TUNNEL.getValue()+"/"+tunnel.getTunnelId();
		theRoute.value.actualState = tunnel.getTunnelStatus()+"";
		theRoute.value.additionalInfo = new NameAndStringValue_T[4]; 
		theRoute.value.additionalInfo[0] = new NameAndStringValue_T(this.SRCINLABEL,tunnel.getLspParticularList().get(0).getFrontLabelValue()+"");
		theRoute.value.additionalInfo[1] = new NameAndStringValue_T(this.SRCOUTLABEL,tunnel.getLspParticularList().get(0).getBackLabelValue()+"");
		theRoute.value.additionalInfo[2] = new NameAndStringValue_T(this.DESTINLABEL,tunnel.getLspParticularList().get(0).getBackLabelValue()+"");
		theRoute.value.additionalInfo[3] = new NameAndStringValue_T(this.DESTOUTLABEL,tunnel.getLspParticularList().get(0).getFrontLabelValue()+"");
		if(tunnel.getLspParticularList().size() <= 1){
			theRoute.value.routeXCs = new CrossConnect_T[0];
		}else{
			theRoute.value.routeXCs = this.convertRouteXCs(tunnel,size);
		}
		
	}

	/**
	 * 转换lsp
	 * @param tunnel
	 * @return 返回CrossConnect_T[]
	 * @throws ProcessingFailureException 
	 */
	private CrossConnect_T[] convertRouteXCs(Tunnel tunnel,int size) throws ProcessingFailureException {
		CrossConnect_T[] crossConnect_T = new CrossConnect_T[size];
		crossConnect_T[0] = new CrossConnect_T();
		crossConnect_T[0].zEndNameList[0] = super.convertName(ELayerRate.PORT.getValue(), tunnel.getLspParticularList().get(0).getZPortId(), tunnel.getLspParticularList().get(0).getZSiteId());
		crossConnect_T[0].additionalInfo = new NameAndStringValue_T[4];
		crossConnect_T[0].additionalInfo[0] = new NameAndStringValue_T(this.DESTINLABEL,tunnel.getLspParticularList().get(1).getBackLabelValue()+"");
		crossConnect_T[0].additionalInfo[1] = new NameAndStringValue_T(this.DESTOUTLABEL,tunnel.getLspParticularList().get(1).getFrontLabelValue()+"");
		//遍历lsp  转换成crossConnect_T对象
		for(int i = 0; i<tunnel.getLspParticularList().size()-1; i++){
			crossConnect_T[i].aEndNameList[0] = super.convertName(ELayerRate.PORT.getValue(), tunnel.getLspParticularList().get(i+1).getAPortId(), tunnel.getLspParticularList().get(i+1).getASiteId());
			crossConnect_T[i].additionalInfo[2] = new NameAndStringValue_T(this.SRCINLABEL,tunnel.getLspParticularList().get(i+1).getFrontLabelValue()+"");
			crossConnect_T[i].additionalInfo[3] = new NameAndStringValue_T(this.SRCOUTLABEL,tunnel.getLspParticularList().get(i+1).getBackLabelValue()+"");
		
			crossConnect_T[i+1] = new CrossConnect_T();
			crossConnect_T[i+1].zEndNameList[0] = super.convertName(ELayerRate.PORT.getValue(), tunnel.getLspParticularList().get(i+1).getZPortId(), tunnel.getLspParticularList().get(0).getZSiteId());
			crossConnect_T[i+1].additionalInfo = new NameAndStringValue_T[4];
			crossConnect_T[i+1].additionalInfo[0] = new NameAndStringValue_T(this.DESTINLABEL,tunnel.getLspParticularList().get(i+1).getBackLabelValue()+"");
			crossConnect_T[i+1].additionalInfo[1] = new NameAndStringValue_T(this.DESTOUTLABEL,tunnel.getLspParticularList().get(i+1).getFrontLabelValue()+"");
		}
		
		crossConnect_T[size].aEndNameList[0] = super.convertName(ELayerRate.PORT.getValue(), tunnel.getAPortId(), tunnel.getASiteId());
		crossConnect_T[size].additionalInfo[2] = new NameAndStringValue_T(this.SRCINLABEL,tunnel.getLspParticularList().get(size).getFrontLabelValue()+"");
		crossConnect_T[size].additionalInfo[3] = new NameAndStringValue_T(this.SRCOUTLABEL,tunnel.getLspParticularList().get(size).getBackLabelValue()+"");
	
		return crossConnect_T;
	}
	/**
	 * 某些 错误引发 中断操作直接返回；此时为SubnetworkConnection_T赋值默认值
	 * @return
	 */
	public SubnetworkConnection_T setDefaultSubnetworkConnection_T(){
		SubnetworkConnection_T subnetworkConnection = new SubnetworkConnection_T();
		subnetworkConnection.name = new NameAndStringValue_T[0];
		subnetworkConnection.owner = super.getOwner();
		subnetworkConnection.userLabel = "";
		subnetworkConnection.nativeEMSName = "";
		subnetworkConnection.rate = (short) ELayerRate.CES.getValue();
		subnetworkConnection.direction = ConnectionDirection_T.CD_BI;
		subnetworkConnection.sncState = SNCState_T.SNCS_NONEXISTENT;
		subnetworkConnection.staticProtectionLevel = StaticProtectionLevel_T.PREEMPTIBLE;
		subnetworkConnection.rerouteAllowed = Reroute_T.RR_YES;
		subnetworkConnection.aEnd = new TPData_T[0];
		subnetworkConnection.zEnd =new TPData_T[0];
		NameAndStringValue_T[] aEndTpName=null;
		NameAndStringValue_T[] zEndTpName=null;
		subnetworkConnection.aEnd[0].tpName=aEndTpName;
		subnetworkConnection.zEnd[0].tpName=zEndTpName;	
		subnetworkConnection.sncType=SNCType_T.ST_ADD_DROP_A;
		subnetworkConnection.networkRouted = NetworkRouted_T.NR_YES;
		subnetworkConnection.additionalInfo=new NameAndStringValue_T[0];
		return subnetworkConnection;
	}
	/**
	 * 设置终端点TPData的值
	 * @param data
	 * @return
	 */
	private TPData_T getTPData(){
		TPData_T data=new TPData_T();
		data.tpMappingMode=TerminationMode_T.TM_NA;
		data.transmissionParams=new LayeredParameters_T[0];
		data.ingressTrafficDescriptorName=new NameAndStringValue_T[0];
		data.egressTrafficDescriptorName=new NameAndStringValue_T[0];
		data.tpName=new NameAndStringValue_T[0];
		return data;
	}
	/**
	 * 处理某些可以为空的字符串，若为空，返回“”
	 * @param name
	 * @return
	 */
	private String conductNull(String name){
		if(null==name){
			return "";
		}
		return name;
	}
	/**
	 * 验证给定tunnel 是否为普通tunnel,SNC操作的是普通tunnel
	 * @param tunnel
	 * @return
	 */
	public boolean checkTunnel(Tunnel tunnel)throws ProcessingFailureException{
		try {
			if(tunnel.getTunnelType().equals(UiUtil.getCodeByValue("PROTECTTYPE", "1").getId()+"")){
				return true;
			}
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"Internal error : The process has internal error!");
		}
		return false;
	}
}
