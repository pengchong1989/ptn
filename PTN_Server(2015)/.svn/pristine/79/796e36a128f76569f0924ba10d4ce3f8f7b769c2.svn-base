package com.nms.corba.ninterface.impl.resource.proxy;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import subnetworkConnection.CCIterator_IHolder;
import subnetworkConnection.CrossConnectList_THolder;
import subnetworkConnection.CrossConnect_T;
import subnetworkConnection.CrossConnect_THolder;
import topologicalLink.TopologicalLinkIterator_IHolder;
import topologicalLink.TopologicalLinkList_THolder;
import topologicalLink.TopologicalLink_T;
import topologicalLink.TopologicalLink_THolder;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.notification.CorbaNotifyReport;
import com.nms.corba.ninterface.impl.resource.TopologicalLinkIterator_Impl;
import com.nms.corba.ninterface.impl.resource.tool.CorbaResConvertTool;
import com.nms.corba.ninterface.impl.service.CrossConnectionsIterator_impl;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.corba.ninterface.util.GetResUtil;
import com.nms.corba.ninterface.util.SystemTool;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.CesDispatch;
import com.nms.service.impl.dispatch.SiteDispatch;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.VerifyNameUtil;
import com.nms.ui.manager.keys.StringKeysTip;

import emsMgr.EMS_T;
import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

public class CorbaResourceProxy {

	ICorbaSession session;
	private static Logger LOG = Logger.getLogger(CorbaResourceProxy.class.getName());
	private ELayerRate layerRate = null;// 获取类型
	private int primaryKey = 0;// 获取类型的主键 id

	public CorbaResourceProxy(ICorbaSession userSession) {
		this.session = userSession;

	}

	public boolean getEms(EMS_T ems) throws ProcessingFailureException {
		ems.name = new NameAndStringValue_T[1];
		ems.name[0] = new NameAndStringValue_T("EMS", CorbaConfig.getInstanse().getCorbaEmsName());

		ems.userLabel = CorbaConfig.getInstanse().getEmsUserlabel();
		ems.nativeEMSName = CorbaConfig.getInstanse().getEmsNativeName();
		ems.emsVersion = CorbaConfig.getInstanse().getEmsVersion();
		ems.type = CorbaConfig.getInstanse().getEmsType();
		ems.owner = CorbaConfig.getInstanse().getEmsVendorName();

		ems.additionalInfo = new NameAndStringValue_T[15];
		ems.additionalInfo[0] = new NameAndStringValue_T("IdlVerion", CorbaConfig.getInstanse().getIdlVersion());

		ems.additionalInfo[1] = new NameAndStringValue_T("Location", CorbaConfig.getInstanse().getEmsLocation());
		ems.additionalInfo[2] = new NameAndStringValue_T("IP", SystemTool.getLocalIPStr());
		int port = CorbaConfig.getInstanse().getCorbaNameServicePort();

		ems.additionalInfo[3] = new NameAndStringValue_T("Port", String.valueOf(port));

		ems.additionalInfo[4] = new NameAndStringValue_T("RuningState", "Available");

		ems.additionalInfo[5] = new NameAndStringValue_T("AlarmState", "PerceivedSeverity_T._PS_CRITICAL");

		ems.additionalInfo[6] = new NameAndStringValue_T("MaxManagedMECount", CorbaConfig.getInstanse().getManageablemaxnumforme());

		int meCount = 0;
		List<SiteInst> siteInstList = null;
		try {
			siteInstList = GetResUtil.getAllDATAXManagedElements();
			if (siteInstList != null) {
				meCount = siteInstList.size();
			}
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "获取数据有误");
		}

		ems.additionalInfo[7] = new NameAndStringValue_T("ManagedMECount", String.valueOf(meCount));

		ems.additionalInfo[8] = new NameAndStringValue_T("Role", CorbaConfig.getInstanse().getEmsrole());
		ems.additionalInfo[9] = new NameAndStringValue_T("ManagingDevices", CorbaConfig.getInstanse().getEmsmanagingdevices());

		ems.additionalInfo[10] = new NameAndStringValue_T("Creator", CorbaConfig.getInstanse().getEmscreator());
		ems.additionalInfo[11] = new NameAndStringValue_T("CreateTime", CorbaConfig.getInstanse().getEmscreatetime());
		ems.additionalInfo[12] = new NameAndStringValue_T("EMSHardware", CorbaConfig.getInstanse().getEmshardware());
		ems.additionalInfo[13] = new NameAndStringValue_T("EMSSoftware", CorbaConfig.getInstanse().getEmssoftware());
		ems.additionalInfo[14] = new NameAndStringValue_T("ContactInfo", CorbaConfig.getInstanse().getEmscontactinfo());

		return true;
	}

	/**
	 * @author pengchong 修改网元，只能修改网元名称标识
	 * @param objectName
	 * @param userLabel
	 *            添加字符 userLabel(修改网元信息，) setUserLabel()方法时，additionalInfo为null setAdditionalInfo()方法时，userLabel 为null
	 * @author sy
	 * @param additionalInfo
	 * @throws ProcessingFailureException 
	 */
	public void updateManagedElement(NameAndStringValue_T[] objectName, String userLabel, NVSList_THolder additionalInfo) throws ProcessingFailureException {
		int siteId;
		SiteInst siteInst = null;
		SiteService_MB siteService = null;
		SiteDispatch siteDispatch=null;
		CorbaResConvertTool corbaResConvertTool=new CorbaResConvertTool();
		try {
			siteId = Integer.parseInt(objectName[1].value);
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteDispatch=new SiteDispatch();
			siteInst = siteService.select(siteId);
			if (additionalInfo != null) {
				String name=corbaResConvertTool.getValueByKey(additionalInfo.value, corbaResConvertTool.NATIVEEMSNAME);
				siteInst.setCellId(name);
			} else {
				siteInst.setCellId(userLabel);
			}

			siteDispatch.excuteUpdate(siteInst);
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"Internal error :modify Me is invalid!");
		}finally {
			UiUtil.closeService_MB(siteService);
		}

	}

	/**
	 * 根据网元查询 tunnel/pw等
	 * 
	 * @param managedElementName
	 *            :网元名称
	 * @param connectionRateList
	 *            :层速率值
	 * @param how_many
	 *            :
	 * @param ccList
	 *            :
	 * @param ccIt
	 *            :
	 * @throws ProcessingFailureException
	 */
	public void getAllCrossConnections(NameAndStringValue_T[] managedElementName, short[] layers, int how_many, CrossConnectList_THolder ccList, CCIterator_IHolder ccIt) throws ProcessingFailureException {
		if (how_many <= 0) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "how_many is error");
		}
		int siteId;
		TunnelService_MB tunnelService = null;
		PwInfoService_MB pwInfoService = null;
		List<Tunnel> tunnels = new ArrayList<Tunnel>();
		List<PwInfo> pwInfos = new ArrayList<PwInfo>();
		List<CesInfo> cesInfoList = new ArrayList<CesInfo>();
		CesInfoService_MB cesInfoService = null;
		List<CrossConnect_T> crossList = new ArrayList<CrossConnect_T>();
		boolean flag = false; // 标记是否进行了查询 ，如果为false 说明层速率传入不正确
		CorbaResConvertTool corbaResConvertTool = new CorbaResConvertTool();
		try {
			// 验证输入名称
			if (!CheckParameterUtil.checkMeName(managedElementName)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "managedElementName is error");
			}
			siteId = Integer.parseInt(managedElementName[1].value);
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			pwInfoService = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);

			// 分别验证是否存在各模块的层速率，如果有，查询模块。 注意，layers的length=0时 查询全部
			if (layers.length == 0 || corbaResConvertTool.isExitArrays(layers, (short) ELayerRate.TUNNEL.getValue())) {// 暂定层速率值为1的表示tunnel
				Tunnel tunnelCondition = new Tunnel();
				tunnelCondition.setIsSingle(1);
				tunnelCondition.setTunnelType(UiUtil.getCodeByValue("PROTECTTYPE", "1").getId()+"");
				tunnels = tunnelService.quertyNodeByTunnelCondition(siteId, tunnelCondition, true);
				flag = true;
			}
			if (layers.length == 0 || corbaResConvertTool.isExitArrays(layers, (short) ELayerRate.PW.getValue())) {// 暂定层速率值为2的表示pw
				pwInfos = pwInfoService.selectBySiteId_node(siteId);
				flag = true;
			}
			if (layers.length == 0 || corbaResConvertTool.isExitArrays(layers, (short) ELayerRate.CES.getValue())) {// ces
				cesInfoList = cesInfoService.selectAll_node(siteId);
				flag = true;
			}

			// 层速率参数不正确，抛出异常
			if (!flag) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "layers is error");
			}

			// 分别验证各个模块是否查询到数据，如果查询到了，转换成CrossConnect_T对象
			if (tunnels.size() > 0) {
				crossList.addAll(corbaResConvertTool.tunnelDATAXToCorba(tunnels));
			}
			if (pwInfos.size() > 0) {
				crossList.addAll(corbaResConvertTool.pwDATAXToCorba(pwInfos));
			}
			if (cesInfoList.size() > 0) {
				crossList.addAll(corbaResConvertTool.convertCesToCrossConnect(cesInfoList));
			}

			// 给输出参数赋值
			ccList.value = crossList.toArray(new CrossConnect_T[crossList.size()]);
			CrossConnectionsIterator_impl corssIterator_impl = new CrossConnectionsIterator_impl(this.session);
			corssIterator_impl.setIterator(ccIt, ccList, how_many);
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getAllCrossConnections.");
		} finally {
			UiUtil.closeService_MB(tunnelService);
			UiUtil.closeService_MB(pwInfoService);
			UiUtil.closeService_MB(cesInfoService);
		}
	}

	/**
	 * function:创建单点PW或者tunnel
	 * 
	 * @param objectName
	 *            NMS传递的数据 DATAX/703B 数据格式: 1.name="EMS";value="Huawei/T2000" 2.name="ManagedElement";value="589825"
	 * @param ccList
	 *            创建业务的数据
	 * @param addSuccess
	 *            创建成功有那些数据
	 * @throws ProcessingFailureException
	 */
	public void createCrossConnections(NameAndStringValue_T[] ccName, short connectionRate, CrossConnect_T crossConnectT, CrossConnect_THolder connectTHolder) throws ProcessingFailureException {
		int siteId;
		CorbaResConvertTool corbaResConvertTool = new CorbaResConvertTool();
		try {
			// 验证输入名称
			if (!CheckParameterUtil.checkMeName(ccName)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "ccName is error");
			}
			siteId = Integer.parseInt(ccName[1].value);

			if (connectionRate == ELayerRate.TUNNEL.getValue()) {// 暂定层速率值为1的表示tunnel
				corbaResConvertTool.createTunnel(crossConnectT, siteId, connectTHolder, this.session);
			} else if (connectionRate == ELayerRate.PW.getValue()) {// 暂定层速率值为2的表示pw
				corbaResConvertTool.convertCrossConnnectionsPW(siteId, crossConnectT, connectTHolder, this.session);
			} else if (connectionRate == (short) ELayerRate.CES.getValue()) {// ces
				corbaResConvertTool.convertCreossConnectionsCes(siteId, crossConnectT, connectTHolder, this.session);
			} else {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "connectionRate is error");
			}
		} catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "createCrossConnections.");
		}

	}

	/**
	 * 激活某条tunnel
	 * 
	 * @param arg0
	 *            :厂商名称，网元id，层速率，tunnel/pw名称
	 * @throws ProcessingFailureException
	 */
	public void activateCrossConnections(NameAndStringValue_T[] nameAndStringValue_T) throws ProcessingFailureException {
		CorbaResConvertTool corbaResConvertTool = new CorbaResConvertTool();
		int siteId = 0;

		try {
			// 验证输入名称
			if (!CheckParameterUtil.checkCCName(nameAndStringValue_T)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "nameAndStringValue_T is error");
			}

			this.layerRate = corbaResConvertTool.getLayerRate(nameAndStringValue_T);
			this.primaryKey = corbaResConvertTool.getPrimaryKey(nameAndStringValue_T);
			siteId = Integer.parseInt(corbaResConvertTool.getValueByKey(nameAndStringValue_T, corbaResConvertTool.MANAGEELEMENT));

			if (this.layerRate == ELayerRate.TUNNEL) {// 暂定层速率值为1的表示tunnel
				corbaResConvertTool.activateTunnel(this.primaryKey,siteId);
			} else if (this.layerRate == ELayerRate.PW) {// 暂定层速率值为2的表示pw
				corbaResConvertTool.activatePw(this.primaryKey,siteId);
			} else if (this.layerRate == ELayerRate.CES) {// 单点下ces
				this.updateCesActivateStatus(siteId, primaryKey, EActiveStatus.ACTIVITY.getValue());
			} else {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "输入参数有误");
			}
		}  catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "activateCrossConnections.");
		}
	}

	/**
	 * 去激活tunnel
	 * 
	 * @param arg0
	 *            :厂商名称，网元id，层速率，tunnel/pw名称
	 * @throws ProcessingFailureException
	 */
	public void deactivateCrossConnections(NameAndStringValue_T[] nameAndStringValue_T) throws ProcessingFailureException {
		CorbaResConvertTool corbaResConvertTool = new CorbaResConvertTool();
		int siteId = 0;
		try {
			// 验证输入名称
			if (!CheckParameterUtil.checkCCName(nameAndStringValue_T)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "nameAndStringValue_T is error");
			}
			this.layerRate = corbaResConvertTool.getLayerRate(nameAndStringValue_T);
			this.primaryKey = corbaResConvertTool.getPrimaryKey(nameAndStringValue_T);
			siteId = Integer.parseInt(corbaResConvertTool.getValueByKey(nameAndStringValue_T, corbaResConvertTool.MANAGEELEMENT));
			if (this.layerRate == ELayerRate.TUNNEL) {// 暂定层速率值为1的表示tunnel
				corbaResConvertTool.deactivateTunnel(this.primaryKey,siteId);
			} else if (this.layerRate == ELayerRate.PW) {// 暂定层速率值为2的表示pw
				corbaResConvertTool.deactivatePw(this.primaryKey,siteId);
			} else if (this.layerRate == ELayerRate.CES) {// 去激活ces
				this.updateCesActivateStatus(siteId, primaryKey, EActiveStatus.UNACTIVITY.getValue());
			} else {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "输入参数有误");
			}
		} catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "deactivateCrossConnections.");
		}
	}

	/**
	 * 删除tunnel
	 * 
	 * @param arg0
	 *            :厂商名称，网元id，层速率，tunnel/pw名称
	 * @throws ProcessingFailureException
	 */
	public void deleteCrossConnections(NameAndStringValue_T[] nameAndStringValue_T) throws ProcessingFailureException {
		CorbaResConvertTool corbaResConvertTool = new CorbaResConvertTool();
		int siteId = 0;
		try {
			// 验证输入名称
			if (!CheckParameterUtil.checkCCName(nameAndStringValue_T)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is error");
			}
			this.layerRate = corbaResConvertTool.getLayerRate(nameAndStringValue_T);
			this.primaryKey = corbaResConvertTool.getPrimaryKey(nameAndStringValue_T);
			siteId = Integer.parseInt(corbaResConvertTool.getValueByKey(nameAndStringValue_T, corbaResConvertTool.MANAGEELEMENT));
			if (this.layerRate == ELayerRate.TUNNEL) {// 暂定层速率值为1的表示tunnel
				corbaResConvertTool.deleteTunnel(this.primaryKey,siteId);
			} else if (this.layerRate == ELayerRate.PW) {// 暂定层速率值为2的表示pw
				corbaResConvertTool.deletePw(this.primaryKey,siteId);
			} else if (this.layerRate == ELayerRate.CES) {// 删除ces
				this.deleteCes(siteId, primaryKey);
			} else {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is error");
			}
		} catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "deleteCrossConnections.");
		}

	}

	/**
	 * 根据主键查询一条连接
	 * 
	 * @param ccName
	 *            3层： 2层为网元ID，3层为 层速率/主键ID
	 * @param crossConnection
	 * @throws ProcessingFailureException
	 */
	public void getCrossConnection(NameAndStringValue_T[] ccName, CrossConnect_THolder crossConnection) throws ProcessingFailureException {
		int siteId;
		CesInfoService_MB cesInfoService = null;
		CesInfo cesInfo = null;
		CorbaResConvertTool corbaResConverTool = new CorbaResConvertTool();
		try {
			// 验证输入名称
			if (!CheckParameterUtil.checkCCName(ccName)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "ccName is error");
			}
			// 获取速率
			this.layerRate = corbaResConverTool.getLayerRate(ccName);
			// 获取主键
			this.primaryKey = corbaResConverTool.getPrimaryKey(ccName);
			siteId = Integer.parseInt(ccName[1].value);
			if (layerRate == ELayerRate.TUNNEL) {// 暂定层速率值为1的表示tunnel
				// 根据主键查询tunnel
				Tunnel tunnel = corbaResConverTool.getTunnel(this.primaryKey,siteId);
				crossConnection.value = corbaResConverTool.convertCrossConnect(tunnel);
			} else if (layerRate == ELayerRate.PW) {// 暂定层速率值为2的表示pw
				PwInfo pwInfo = corbaResConverTool.getPwInfo(this.primaryKey,siteId);
				if (null != pwInfo) {
					crossConnection.value = corbaResConverTool.convertCrossConnect(pwInfo);
				}
			} else if (layerRate == ELayerRate.CES) { // 单点下ces
				cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
				cesInfo = cesInfoService.queryBySiteAndId(siteId, primaryKey);
				if (cesInfo != null) {
					crossConnection.value = corbaResConverTool.convertCrossConnect(cesInfo);
				}
			}

			if (null == crossConnection.value) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "ccName is error");
			}

		} catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getCrossConnection.");
		} finally {
			UiUtil.closeService_MB(cesInfoService);
		}
	}

	/**
	 * 根据主键ID 查询ces，修改ces的激活状态
	 * 
	 * @param id
	 * @param activeStatus
	 * @throws ProcessingFailureException
	 */
	private void updateCesActivateStatus(int siteId, int id, int activeStatus) throws ProcessingFailureException {
		CesDispatch cesDispatch = null;
		CesInfoService_MB cesInfoService = null;
		CesInfo cesInfo = null;
		String result = "";
		try {
			cesDispatch = new CesDispatch();
			cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			cesInfo = cesInfoService.queryBySiteAndId(siteId, id);
			if (cesInfo == null) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "输入参数有误，没有找到数据");
			}
			cesInfo.setActiveStatus(activeStatus);
			result = cesDispatch.excuteUpdate(cesInfo);
			if (!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, result);
			}
		}  catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "updateCesActivateStatus.");
		} finally {
			UiUtil.closeService_MB(cesInfoService);
		}
	}

	/**
	 * 根据主键ID 查询ces，删除此ces
	 * 
	 * @param id
	 * @throws ProcessingFailureException
	 */
	private void deleteCes(int siteId, int id) throws ProcessingFailureException {
		CesDispatch cesDispatch = null;
		CesInfoService_MB cesInfoService = null;
		CesInfo cesInfo = null;
		String result = "";
		List<CesInfo> cesInfoList = null;
		try {
			cesDispatch = new CesDispatch();
			cesInfoList = new ArrayList<CesInfo>();
			cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			cesInfo = cesInfoService.queryBySiteAndId(siteId, id);
			if (cesInfo == null) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "输入参数有误，没有找到数据");
			}
			cesInfoList.add(cesInfo);
			result = cesDispatch.excuteDelete(cesInfoList);
			if (!result.equals(ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS))) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, result);
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "deleteCes.");
		} finally {
			UiUtil.closeService_MB(cesInfoService);
		}
	}

	public void setUserLabel(NameAndStringValue_T[] objectName, String userLabel) throws ProcessingFailureException {
		// objectName is EMS
		if (objectName.length == 1) {
			if (CheckParameterUtil.checkEmsName(objectName)) {
				setEmsUserLabel(objectName, userLabel);
				return;
			}
		}
		// objectName is MLSubnet
		else if (objectName.length == 2) {
			if (CheckParameterUtil.checkMLSubnetName(objectName)) {
				CorbaEMSMgrProxy proxy = new CorbaEMSMgrProxy(session);
				proxy.setUserLabel(objectName, userLabel);
				return;
			}
			// objectName is me
			else if (CheckParameterUtil.checkMeName(objectName)) {
				updateManagedElement(objectName, userLabel, null);
				return;
			}
		}
		// objectName is CrossConnection
		else if (objectName.length == 3) {// 单点下 （ccn） tunnel,pw,ces等
			if (CheckParameterUtil.checkCCName(objectName)) {
				this.setCCNUserLabel(objectName, userLabel);
			}

		} else {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "objectName is invalid input,please input ems name.");
		}
	}

	public void setAdditionalInfo(NameAndStringValue_T[] objectName, NVSList_THolder additionalInfo) throws ProcessingFailureException {
		if (additionalInfo.value == null) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "invalid input,additionalInfo.value is null.");
		}
		if (objectName.length == 1) {
			if (CheckParameterUtil.checkEmsName(objectName)) {
				setEmsAdditionalInfo(objectName, additionalInfo);
				return;
			}
		}
		// objectName is MLSubnet
		else if (objectName.length == 2) {
			if (CheckParameterUtil.checkMLSubnetName(objectName)) {
				CorbaEMSMgrProxy proxy = new CorbaEMSMgrProxy(session);
				proxy.setAdditionalInfo(objectName, additionalInfo);
				return;
			}// objectName is me
			if (CheckParameterUtil.checkMeName(objectName)) {
				updateManagedElement(objectName, null, additionalInfo);
				return;
			}

		}
		// objectName is CrossConnection
		else if (objectName.length == 3) {// 单点下 （ccn） tunnel,pw,ces等
			if (CheckParameterUtil.checkCCName(objectName)) {
				updateManagedElement(objectName, null, additionalInfo);
				return;
			}

		}

		throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "objectName is invalid input,please input correct name.");
	}

	private void setEmsAdditionalInfo(NameAndStringValue_T[] objectName, NVSList_THolder additionalInfo) throws ProcessingFailureException {
		if (additionalInfo.value == null) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "invalid input,additionalInfo.value is null.");
		}
		NameAndStringValue_T[] nameAndValue = additionalInfo.value;
		for (int i = 0; i < nameAndValue.length; i++) {
			if (nameAndValue[i].name.equalsIgnoreCase("contactinfo")) {
				boolean bl = CorbaConfig.getInstanse().setEmscontactinfo(nameAndValue[i].value);
				if (!bl) {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "setEmsUserLabel occurs internal exception！");
				}
				CorbaNotifyReport.emsAttributeChg(objectName, "contactinfo", nameAndValue[i].value);
				break;
			}
		}

	}

	private void setEmsUserLabel(NameAndStringValue_T[] objectName, String userLabel) throws ProcessingFailureException {
		// 值未变
		if (CorbaConfig.getInstanse().getEmsUserlabel().equals(userLabel)) {
			return;
		}
		boolean bl = CorbaConfig.getInstanse().setEmsUserlabel(userLabel);
		if (!bl) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "setEmsUserLabel occurs internal exception！");
		}
		CorbaNotifyReport.emsAttributeChg(objectName, "userLabel", userLabel);
	}

	/**
	 * 
	 * 设置友好名称
	 * 
	 * @param objectName
	 * @param userLabel
	 * @author sy
	 */
	private void setCCNUserLabel(NameAndStringValue_T[] nameAndStringValue_T, String userLabel) throws ProcessingFailureException {
		CesInfo cesInfo = null;
		CesInfoService_MB cesInfoService = null;
		VerifyNameUtil verifyNameUtil = null;
		CorbaResConvertTool corbaResConvertTool = new CorbaResConvertTool();
		CesDispatch cesDispatch=null;
		try {
			verifyNameUtil = new VerifyNameUtil();
			this.layerRate = corbaResConvertTool.getLayerRate(nameAndStringValue_T);
			this.primaryKey = corbaResConvertTool.getPrimaryKey(nameAndStringValue_T);
			if (this.layerRate == ELayerRate.CES) {// ces
				cesDispatch=new CesDispatch();
				cesInfoService = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
				cesInfo = cesInfoService.selectServiceInfoById(primaryKey);
				if (cesInfo == null) {
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is error");
				}
				// 验证名称是否重复
				if (!verifyNameUtil.verifyName(EServiceType.CES.getValue(), userLabel, cesInfo.getName())) {
					// 验证通过，修改名称
					cesInfo.setName(userLabel);
					cesDispatch.excuteUpdate(cesInfo);
				} else {// 验证没有通过
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "userlabel repeat");
				}
			} else {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is error");
			}
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "setCCNUserLabel.");
		} finally {
			UiUtil.closeService_MB(cesInfoService);
		}
	}

	/**
	 * 查询所有拓扑连接
	 * 
	 * @param how_many
	 *            首次显示数量
	 * @param topoList
	 *            首次显示拓扑连接数据
	 * @param topoIt
	 *            拓扑连接数据迭代
	 * @throws ProcessingFailureException
	 */
	public void getAllTopologicalLinks(int how_many, TopologicalLinkList_THolder topoList, TopologicalLinkIterator_IHolder topoIt) throws ProcessingFailureException {
		SegmentService_MB segmentService = null;
		CorbaResConvertTool corbaResConvertTool;
		try {
			corbaResConvertTool = new CorbaResConvertTool();
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			List<Segment> segmentList = segmentService.select();
			segmentList = segmentService.select();
			if (null == segmentList || segmentList.size() == 0) {
				topoList.value = new TopologicalLink_T[0];
				return;
			}
			topoList.value = new TopologicalLink_T[segmentList.size()];
			corbaResConvertTool.corbaTopologicalLinkListConvrtTool(segmentList, topoList.value);
			// 迭代
			TopologicalLinkIterator_Impl iter = new TopologicalLinkIterator_Impl(this.session);
			iter.setIterator(topoIt, topoList, how_many);

		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getAllTopLevelTopologicalLinks ex.");
		}finally{
			UiUtil.closeService_MB(segmentService);
		}
	}

	/**
	 * 查询拓扑连接
	 * 
	 * @param topoLinkName
	 *            拓扑连接名称
	 * @param topoLink
	 *            拓扑连接
	 * @throws ProcessingFailureException
	 */
	public void getTopologicalLink(NameAndStringValue_T[] topoLinkName, TopologicalLink_THolder topoLink) throws ProcessingFailureException {
		if (!CheckParameterUtil.checkTopologicalLinkName(topoLinkName))
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "输入参数有误");
		String[] id;
		Segment segment;
		List<Segment> segmentList;
		SegmentService_MB segmentService = null;
		CorbaResConvertTool corbaResConvertTool = new CorbaResConvertTool();
		try {
			if (topoLinkName[1].value.contains("/"))
				;
			id = topoLinkName[1].value.split("/");
			segment = new Segment();
			segment.setId(Integer.parseInt(id[1]));
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			segmentList = segmentService.select(segment);
			if (null == segmentList || segmentList.size() != 1) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND, "实体没有找到");
			}
			topoLink.value = new TopologicalLink_T();
			corbaResConvertTool.corbaTopologicalLinkConvrtTool(segmentList.get(0), topoLink.value);
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getTopologicalLink ex.");
		}finally{
			UiUtil.closeService_MB(segmentService);
		}
	}

}
