package com.nms.corba.ninterface.impl.manageElement.proxy;

import emsMgr.EMS_T;
import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.NamingAttributesIterator_IHolder;
import globaldefs.NamingAttributesList_THolder;
import globaldefs.ProcessingFailureException;

import java.util.List;

import managedElement.ManagedElementIterator_IHolder;
import managedElement.ManagedElementList_THolder;
import managedElement.ManagedElement_T;
import managedElement.ManagedElement_THolder;
import terminationPoint.TerminationPointIterator_IHolder;
import terminationPoint.TerminationPointList_THolder;
import terminationPoint.TerminationPoint_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.manageElement.tool.CorbaManageElementTool;
import com.nms.corba.ninterface.impl.resource.ManagedElementIterator_Impl;
import com.nms.corba.ninterface.impl.resource.NamingAttributesIterator_Impl;
import com.nms.corba.ninterface.impl.resource.TerminationPointIterator_Impl;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class CorbaManageElementProxy {

	ICorbaSession session;

	public CorbaManageElementProxy(ICorbaSession userSession) {
		this.session = userSession;
	}

	/**
	 * 查询所有网元信息
	 * 
	 * @param how_many
	 * @param meList
	 * @param meIt
	 * @throws ProcessingFailureException
	 */
	public void getAllManagedElements(int how_many, ManagedElementList_THolder meList, ManagedElementIterator_IHolder meIt) throws ProcessingFailureException {
		if (how_many == 0) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "输入参数有误");
		}
		List<SiteInst> siteInstList = null;
		CorbaManageElementTool corbaManageElementTool = new CorbaManageElementTool();
		SiteService_MB ss = null;
		try {
			ss = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInstList = ss.select();
			meList.value = new ManagedElement_T[siteInstList.size()];
			corbaManageElementTool.convertManagedElementListDATAX2Corba(siteInstList, meList.value);
			ManagedElementIterator_Impl iter = new ManagedElementIterator_Impl(this.session);
			iter.setIterator(meIt, meList, how_many);

		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getAllDATAXManagedElements ex.");
		} finally {
			UiUtil.closeService_MB(ss);
		}
	}

	/**
	 * function:通过此方法来获取" 以太网层速率参数"
	 * 
	 * @param managedElementName
	 *            NMS传递的数据 NameAndStringValue_T[] 数组 name="EMS" value="DATAX" NMS传递的数据 NameAndStringValue_T[] 数组 name="网元" value="siteID"
	 * @param tpLayerRateList
	 *            层次
	 * @param connectionLayerRateList
	 *            ？？
	 * @param how_many
	 *            表示 返回数据的长度
	 * @param tpList
	 *            要返回的数据 TerminationPointList_THolder（长度根据 == how_many）
	 * @param tpList
	 *            TerminationPointIterator_IHolder 迭代器 将多余的数据都存放在 tpList 中
	 * 
	 */
	public void getAllPTPs(NameAndStringValue_T[] managedElementName, short[] tpLayerRateList, short[] connectionLayerRateList, int how_many, TerminationPointList_THolder tpList, TerminationPointIterator_IHolder tpIt) throws ProcessingFailureException {

		TerminationPointIterator_Impl terminationPointIterator_Impl = null;
		CorbaManageElementTool corbaManageElementTool = null;

		try {
			corbaManageElementTool = new CorbaManageElementTool();
			// managedElementName 验证入参是否为null
			if (null == managedElementName || managedElementName.length != 2) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "managedElementName is error");
			}

			// 验证是否有网元名称
			if (!CheckParameterUtil.checkMeName(managedElementName)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "ManagerElementName is error");
			}

			// 转换ptp对象
			tpList.value = corbaManageElementTool.convertManagedElementPTPsDATAX2Corba(managedElementName[1].value, tpLayerRateList);
			terminationPointIterator_Impl = new TerminationPointIterator_Impl(this.session);
			terminationPointIterator_Impl.setIterator(tpIt, tpList, how_many);
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getAllPTPs.");
		} finally {
			corbaManageElementTool = null;
		}
	}

	/**
	 * 查询所有网元名称
	 * 
	 * @param how_many
	 * @param nameList
	 * @param nameIt
	 * @throws ProcessingFailureException
	 */
	public void getAllManagedElementNames(int how_many, NamingAttributesList_THolder nameList, NamingAttributesIterator_IHolder nameIt) throws ProcessingFailureException {

		SiteService_MB siteService = null;
		List<SiteInst> siteInsts = null;
		NameAndStringValue_T[][] values = null;
		try {

			siteService = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			siteInsts = siteService.select();
			if (siteInsts != null && siteInsts.size() > 0) {// 遍历网元属性赋值
				values = new NameAndStringValue_T[siteInsts.size()][2];
				for (int i = 0; i < siteInsts.size(); i++) {
					values[i][0] = new NameAndStringValue_T("EMS", "DATAX/703B");
					values[i][1] = new NameAndStringValue_T("网元名称", siteInsts.get(i).getCellId());
				}
			}
			nameList.value = values;
			NamingAttributesIterator_Impl iterator = new NamingAttributesIterator_Impl(this.session);
			iterator.setIterator(nameIt, nameList, how_many);
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getAllManagedElementNames.");
		} finally {
			UiUtil.closeService_MB(siteService);
		}
	}

	public static boolean getEms(EMS_T value) {
		return false;
	}

	/**
	 * 根据终端点标识查询
	 * 
	 * @param tpName
	 *            终端点标识
	 * @param tp
	 *            返回结果
	 * @throws ProcessingFailureException
	 */
	public void getTP(NameAndStringValue_T[] tpName, TerminationPoint_THolder tp) throws ProcessingFailureException {

		int portId = 0;
		PortService_MB portService = null;
		PortInst portInst = null;
		CorbaManageElementTool corbaManageElementTool = new CorbaManageElementTool();
		short layer = 0;
		List<PortInst> portInstList = null;
		try {
			// managedElementName 验证入参是否为null
			if (null == tpName || tpName.length != 3) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "tpName is error");
			}

			// 验证是否有网元名称
			if (CheckParameterUtil.checkPTPName(tpName)) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "tpName is error");
			}

			// 根据标识查询端口对象
			portId = Integer.parseInt(corbaManageElementTool.getValueByKey(tpName, corbaManageElementTool.PTP,Integer.parseInt(tpName[1].value)));
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			portInst = new PortInst();
			portInst.setPortId(portId);
			portInstList = portService.select(portInst);

			// 没查到，说明端口标识参数不正确
			if (null == portInstList || portInstList.size() != 1) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "tpName is error");
			}

			portInst = portInstList.get(0);

			// 获取端口速率
			layer = corbaManageElementTool.getLayer(portInst);

			// 如果端口速率不等于e1也不等于eth 那么说明端口标识参数不正确
			if (layer != ELayerRate.PORT.getValue() && layer != ELayerRate.TDMPORT.getValue()) {
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "tpName is error");
			}

			// 转换对象
			tp.value = corbaManageElementTool.convertTerminationPoint(portInst, layer);

		} catch (ProcessingFailureException e) {
			throw e;
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getAllManagedElementNames.");
		} finally {
			UiUtil.closeService_MB(portService);
		}
	}

	public void getManagedElement(NameAndStringValue_T[] meName,
			ManagedElement_THolder me) throws ProcessingFailureException {
		SiteService_MB ss = null;
		try {
			if(!CheckParameterUtil.checkMeName(meName)){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "meName is invalid.");
			}
			ss = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			SiteInst siteinst = new SiteInst();
			int siteId = Integer.valueOf(meName[1].value);
			siteinst.setSite_Inst_Id(siteId);
			List<SiteInst> siteInstList = ss.select(siteinst);
			if(siteInstList.isEmpty() || siteInstList.size() != 1){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "no data!.");
			}
			me.value = new ManagedElement_T();
			CorbaManageElementTool meConvertTool = new CorbaManageElementTool();
			meConvertTool.convertManagedElementDATAX2Corba(siteInstList.get(0), me.value);
			return;
		}catch (ProcessingFailureException e1) {
			ExceptionManage.dispose(e1,this.getClass());
		}catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "getManagedElement ex.");
		} finally {
			UiUtil.closeService_MB(ss);
		}
		
	}
}
