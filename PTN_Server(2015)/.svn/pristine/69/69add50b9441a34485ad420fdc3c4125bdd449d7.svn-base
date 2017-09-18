package com.nms.corba.ninterface.impl.resource;

import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesIterator_IHolder;
import globaldefs.NamingAttributesList_THolder;
import globaldefs.ProcessingFailureException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import managedElement.CommunicationState_T;
import managedElement.ManagedElementIterator_IHolder;
import managedElement.ManagedElementList_THolder;
import managedElement.ManagedElement_T;
import managedElement.ManagedElement_THolder;

import org.apache.log4j.Logger;

import subnetworkConnection.CCIterator_IHolder;
import subnetworkConnection.CrossConnectList_THolder;
import subnetworkConnection.CrossConnect_T;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.resource.proxy.EquipmentProxy;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.system.SubnetService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.UiUtil;

public class ManagedElementMgr {
	private ICorbaSession session;
	static Logger LOG = Logger.getLogger(ManagedElementMgr.class.getName());
	
	public ManagedElementMgr(ICorbaSession userSession) {
		this.session = userSession;
	}

	/**
	 * 查询网元
	 * @param how_many
	 * @param meList
	 * @param meIt
	 * @throws ProcessingFailureException
	 */
	public static void getAllManagedElements(int how_many,ManagedElementList_THolder meList,ManagedElementIterator_IHolder meIt)
			                                   throws ProcessingFailureException {
		SiteService_MB siteService = null;
		List<SiteInst> siteInsts = null;
		ManagedElement_T[] values = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInsts = siteService.select();
			if(siteInsts != null && siteInsts.size()>0){//遍历网元属性赋值
				values = new ManagedElement_T[siteInsts.size()];
			}
			meList.value = values;
		} catch (Exception e) {
			LOG.error(e);
		} finally{
			UiUtil.closeService_MB(siteService);
		}

	}
	
	/**
	 * 查询所有网元名称
	 * @param how_many
	 * @param nameList
	 * @param nameIt
	 * @throws ProcessingFailureException
	 */
	public static void getAllManagedElementNames(int how_many,
			NamingAttributesList_THolder nameList,
			NamingAttributesIterator_IHolder nameIt)
			throws ProcessingFailureException {

		SiteService_MB siteService = null;
		List<SiteInst> siteInsts = null;
		NameAndStringValue_T[][] values = null;
		try {
			
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInsts = siteService.select();
			if(siteInsts != null && siteInsts.size()>0){//遍历网元属性赋值
				
			}
			nameList.value = values;
		} catch (Exception e) {
			LOG.error(e);
		} finally{
			UiUtil.closeService_MB(siteService);
		}
	}
	
	/**
	 * 通过网元id查询该网元所属子网名称
	 * @param managedElementName
	 * @param subnetNames
	 * @throws ProcessingFailureException
	 */
	public static void getContainingSubnetworkNames(
			NameAndStringValue_T[] managedElementName,
			NamingAttributesList_THolder subnetNames)
			throws ProcessingFailureException {
		SiteService_MB siteService = null;
		SubnetService_MB subnetService = null;
		SiteInst siteInst = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			subnetService = (SubnetService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SUBNETSERVICE);
			siteInst = new SiteInst();
//			siteInst.set
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			UiUtil.closeService_MB(subnetService);
			UiUtil.closeService_MB(siteService);
		}
	}
	
	/**
	 * 查询某网元所有业务
	 * @param managedElementName
	 * @param connectionRateList
	 * @param how_many
	 * @param ccList
	 * @param ccIt
	 * @throws ProcessingFailureException
	 */
	public static void getAllCrossConnections(
			NameAndStringValue_T[] managedElementName,
			short[] connectionRateList, int how_many,
			CrossConnectList_THolder ccList, CCIterator_IHolder ccIt)
			throws ProcessingFailureException {
		ElineInfoService_MB elineService = null;
		EtreeInfoService_MB etreeService = null;
		ElanInfoService_MB elanInfoService = null;
		List<ElineInfo> elineInfos = null;
		Map<String, List<EtreeInfo>> etreeInfos = null;
		Map<String, List<ElanInfo>> elanInfos = null;
		int siteId;
		CrossConnect_T[] value;
		if("DATAX".equals(Integer.parseInt(managedElementName[0].value))){//厂商和设备类型
			try {
				//网元id不确定是否是主键id,但是只有主键id才能确定网元唯一性
				siteId = Integer.parseInt(managedElementName[1].value);
				elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
				for (int i = 0; i < connectionRateList.length; i++) {
					if(connectionRateList[i] == 1){//暂定层速率值为1的表示eline
						elineInfos = elineService.selectNodeBySite(siteId);
					}else if(connectionRateList[i] == 2){//暂定层速率值为2的表示etree
						etreeInfos = etreeService.selectNodeBySite(siteId);
					}else if(connectionRateList[i] == 3){//暂定层速率值为3的表示elan
						elanInfos = elanInfoService.selectSiteNodeBy(siteId);
					}
				}
			} catch (Exception e) {
				LOG.error(e);
			} finally {
				UiUtil.closeService_MB(elineService);
				UiUtil.closeService_MB(etreeService);
				UiUtil.closeService_MB(elanInfoService);
			}
		}
	}

	public void deleteCrossConnections(NameAndStringValue_T[] objectName,
			CrossConnect_T[] deleteList, CrossConnectList_THolder successList,
			CrossConnectList_THolder failedList) {
		
	}

	public void deactivateCrossConnections(NameAndStringValue_T[] objectName,
			CrossConnect_T[] unactivateList,
			CrossConnectList_THolder successList,
			CrossConnectList_THolder failedList) {
		
	}

	public void deleteCrossConnections(NameAndStringValue_T[] objectName) {
	
	}

	public void activateCrossConnections(NameAndStringValue_T[] objectName) {
		
	}

	public void deactivateCrossConnections(NameAndStringValue_T[] objectName) {

	}

	/**
	 * @author guoqc
	 * 获取指定的网元
	 * 入参  managedElementName 名值对，指定设备ID号以及厂商信息
	 * 出参  me 查询的结果
	 */
	public void getManagedElement(NameAndStringValue_T[] managedElementName,
									ManagedElement_THolder me) {
		if(managedElementName.length > 1){
			String emsType = managedElementName[0].name;
			String manufacturer = managedElementName[0].value;
			String siteId = managedElementName[1].value;
			if((CorbaConfig.getInstanse().getCorbaEmsName()).equals(manufacturer)
					&& emsType.equals(CorbaConfig.getInstanse().getEmsType())){
				try {
					//获取设备信息
					List<SiteInst> siteList = this.getSiteInstList(siteId);
					//数据转换
					if(siteList.size() > 0){
						this.convertEquipment2Corba(siteList.get(0), me);
					}
				} catch (Exception e) {
					LOG.error(e);
				}
			}
		}
	}

	/**
	 * 获取网元信息
	 */
	private List<SiteInst> getSiteInstList(String siteId) {
		List<SiteInst> siteList = new ArrayList<SiteInst>();
		SiteService_MB siteService = null;
		try {
			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			SiteInst site = new SiteInst();
			site.setSite_Inst_Id(Integer.parseInt(siteId));
			siteList = siteService.select(site);
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			UiUtil.closeService_MB(siteService);
		}
		return siteList;
	}

	/**
	 * 转化设备信息
	 * @throws IOException 
	 */
	private void convertEquipment2Corba(SiteInst site, ManagedElement_THolder me)throws IOException {
		ManagedElement_T m = new ManagedElement_T();
		m.communicationState = CommunicationState_T.CS_AVAILABLE;//网元的运行状态（可用或不可用）
		m.emsInSyncState = true;
		m.location = "";//网元所在的物理位置（区域、站点、机房，精确到机房）
		m.name = new NameAndStringValue_T[1];//网元友好名称（*）
		m.name[0] = new NameAndStringValue_T("name", site.getCellId());
		m.nativeEMSName = site.getCellId();//网元在厂商网管系统中的本地名称
		m.owner = "";//
		m.productName = CorbaConfig.getInstanse().getCorbaEmsName();//网元供应商名称；
		m.supportedRates = new short[]{1};//网元可能支持的连接速率列表
		m.userLabel = site.getSite_Inst_Id()+"";//网元标识符
		m.version = site.getVersions() == null ? "":site.getVersions();//网元的软件版本；
		//附加信息
		Properties props = new Properties();
		InputStream propsIs = EquipmentProxy.class.getClassLoader().getResourceAsStream("config/equipment.properties");
		props.load(propsIs);
		m.additionalInfo =  new NameAndStringValue_T[9];
		int j = 0;
		//网元类型（基于MPLS-TP的PTN设备）
		m.additionalInfo[j++] = new NameAndStringValue_T("cellType", site.getCellType());
		//网元型号
		m.additionalInfo[j++] = new NameAndStringValue_T("cellType", site.getPlateNumber());
		//网元设备面板图标识(在网元型号不能唯一确定设备面板图样式的情况下，此属性必须提供，并能唯一确定设备面板图样式)
		m.additionalInfo[j++] = new NameAndStringValue_T("panel", "1");
		//网元IP地址
		m.additionalInfo[j++] = new NameAndStringValue_T("IP", site.getCellDescribe());
		//网元告警状态（当前最高告警级别）
		m.additionalInfo[j++] = new NameAndStringValue_T("discAlarm", props.getProperty("discAlarm"));
		//单元盘硬件版本 
		m.additionalInfo[j++] = new NameAndStringValue_T("discHardware", props.getProperty("discHardware"));
		//虚拟网元所映射的真实网元所属厂商网管系统标识符
		m.additionalInfo[j++] = new NameAndStringValue_T("siteLabel", site.getSite_Inst_Id()+"");
		//创建者标记
		m.additionalInfo[j++] = new NameAndStringValue_T("createUser", "admin");
		//创建日期
		m.additionalInfo[j++] = new NameAndStringValue_T("createTime", site.getCreateTime());
		me.value = m;
	}
}
