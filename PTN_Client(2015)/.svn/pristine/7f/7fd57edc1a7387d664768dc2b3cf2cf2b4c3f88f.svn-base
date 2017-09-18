package com.nms.ui.manager;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.equipment.manager.UpgradeManage;
import com.nms.db.bean.equipment.shelf.SiteInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.perform.CurrentPerforInfo;
import com.nms.db.bean.perform.PerformanceTaskInfo;
import com.nms.db.bean.ptn.SiteStatusInfo;
import com.nms.db.bean.ptn.clock.FrequencyInfoNeClock;
import com.nms.db.bean.ptn.clock.TimeManageInfo;
import com.nms.db.bean.ptn.clock.TimeSyncInfo;
import com.nms.db.bean.ptn.oamStatus.OamStatusInfo;
import com.nms.db.bean.ptn.qos.QosInfo;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.dispatch.rmi.AlarmDispatchI;
import com.nms.service.impl.dispatch.rmi.CardDispatchI;
import com.nms.service.impl.dispatch.rmi.ClockDispatchI;
import com.nms.service.impl.dispatch.rmi.DispatchInterface;
import com.nms.service.impl.dispatch.rmi.PerformanceDispatchI;
import com.nms.service.impl.dispatch.rmi.QosDispatchI;
import com.nms.service.impl.dispatch.rmi.RmiInitI;
import com.nms.service.impl.dispatch.rmi.SegmentDispatchI;
import com.nms.service.impl.dispatch.rmi.SiteDispatchI;
import com.nms.service.impl.dispatch.rmi.TimeSyncDispatchI;
import com.nms.service.impl.dispatch.rmi.bean.ServiceBean;
import com.nms.service.impl.util.ResultString;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.performance.model.CurrentPerformanceFilter;

/**
 * 界面调用rmi接口辅助类
 * 
 * @author kk
 * 
 */
public class DispatchUtil {

	private Object object_interface;

	/**
	 * 带参数的实例
	 * 
	 * @param rmiKey
	 *            rmi的key 通过key创建rmi实现
	 * @throws Exception
	 */
	public DispatchUtil(String rmiKey) throws Exception {

		try {
			Registry registry = LocateRegistry.getRegistry(ConstantUtil.serviceIp, ConstantUtil.RMI_PORT);
			this.object_interface = this.getInterface(rmiKey, registry);
		} catch (Exception e) {
			throw e;
		}

	}

	/**
	 * 获取接口
	 * 
	 * @param rmiKey
	 * @param registry
	 * @return
	 * @throws RemoteException
	 * @throws NotBoundException
	 */
	private Object getInterface(String rmiKey, Registry registry) throws RemoteException, NotBoundException {

		if (RmiKeys.RMI_FREQUENCYSITE.equals(rmiKey) || RmiKeys.RMI_CLOCKFREQU.equals(rmiKey)) {
			ClockDispatchI clockDispatchI = (ClockDispatchI) registry.lookup(rmiKey);
			return clockDispatchI;
		} else if (RmiKeys.RMI_CARD.equals(rmiKey)) {
			CardDispatchI cardDispatchI = (CardDispatchI) registry.lookup(rmiKey);
			return cardDispatchI;
		} else if (RmiKeys.RMI_ALARM.equals(rmiKey)) {
			AlarmDispatchI alarmDispatchI = (AlarmDispatchI) registry.lookup(rmiKey);
			return alarmDispatchI;
		} else if (RmiKeys.RMI_PERFORMANCE.equals(rmiKey)) {
			PerformanceDispatchI performanceDispatchI = (PerformanceDispatchI) registry.lookup(rmiKey);
			return performanceDispatchI;
		} else if (RmiKeys.RMI_QOS.equals(rmiKey)) {
			QosDispatchI qosDispatchI = (QosDispatchI) registry.lookup(rmiKey);
			return qosDispatchI;
		} else if (RmiKeys.RMI_SEGMENT.equals(rmiKey)) {
			SegmentDispatchI segmentDispatchI = (SegmentDispatchI) registry.lookup(rmiKey);
			return segmentDispatchI;
		} else if (RmiKeys.RMI_SITE.equals(rmiKey)) {
			SiteDispatchI siteDispatchI = (SiteDispatchI) registry.lookup(rmiKey);
			return siteDispatchI;
		} else if (RmiKeys.RMI_INIT.equals(rmiKey)) {
			RmiInitI rmiInitI = (RmiInitI) registry.lookup(rmiKey);
			return rmiInitI;
		}else {
			DispatchInterface dispatchInterface = (DispatchInterface) registry.lookup(rmiKey);
			return dispatchInterface;
		}

	}

	/**
	 * 执行插入方法
	 * 
	 * @param object
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String excuteInsert(Object object) throws RemoteException, Exception {
		DispatchInterface dispatchInterface = (DispatchInterface) this.object_interface;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			result = validateResult(dispatchInterface.excuteInsert(object));
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}

	/**
	 * 执行修改方法
	 * 
	 * @param object
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String excuteUpdate(Object object) throws RemoteException, Exception {
		DispatchInterface dispatchInterface = (DispatchInterface) this.object_interface;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			result = validateResult(dispatchInterface.excuteUpdate(object));
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}

	/**
	 * 执行删除方法
	 * 
	 * @param object
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String excuteDelete(Object object) throws RemoteException, Exception {
		DispatchInterface dispatchInterface = (DispatchInterface) this.object_interface;
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			result = validateResult(dispatchInterface.excuteDelete(object));
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}

	/**
	 * 执行同步方法
	 * 
	 * @param siteId
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String synchro(int siteId) throws RemoteException, Exception {
		DispatchInterface dispatchInterface = (DispatchInterface) this.object_interface;
		String result = ResultString.QUERY_FAILED;
		try {
			result = validateResult(dispatchInterface.synchro(siteId));
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}

	/**
	 * 执行一致性检测方法
	 * @param siteId
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public Object consistence(int siteId) throws RemoteException, Exception {
		DispatchInterface dispatchInterface = (DispatchInterface) this.object_interface;
		return dispatchInterface.consistence(siteId);
	}
	/**
	 * 执行查询告警方法
	 * 
	 * @param objectList
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String executeQueryHisAlarmBySites(List<Integer> objectList) throws RemoteException, Exception {
		
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof AlarmDispatchI) {
				AlarmDispatchI alarmDispatchI = (AlarmDispatchI) this.object_interface;
				result =  validateResult(alarmDispatchI.executeQueryHisAlarmBySites(objectList));
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}

	/**
	 * 同步网元告警
	 * 
	 * @param objectList
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void synchroCurrentAlarm(Object siteId) throws RemoteException, Exception {
		try {
			if (this.object_interface instanceof AlarmDispatchI) {
				AlarmDispatchI alarmDispatchI = (AlarmDispatchI) this.object_interface;
				alarmDispatchI.synchroCurrentAlarm(siteId);
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
	}
	
	/**
	 * 查询设备板卡
	 * 
	 * @param siteId
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public Map<Integer, String> matchingCard(int siteId) throws RemoteException, Exception {
		if (this.object_interface instanceof CardDispatchI) {
			CardDispatchI cardDispatchI = (CardDispatchI) this.object_interface;
			return cardDispatchI.matchingCard(siteId);
		} else {
			throw new Exception("object_interface is error");
		}
	}

	/**
	 * 倒换
	 * 
	 * @param object
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String clcokRorate(Object object) throws RemoteException, Exception {
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof ClockDispatchI) {
				ClockDispatchI clockDispatchI = (ClockDispatchI) this.object_interface;
				result = validateResult(clockDispatchI.clcokRorate(object));
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}

	/**
	 * 根据网元查询对象
	 * 
	 * @param object
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public FrequencyInfoNeClock executeQueryFrequencyBySites(int siteId) throws RemoteException, Exception {
		if (this.object_interface instanceof ClockDispatchI) {
			ClockDispatchI clockDispatchI = (ClockDispatchI) this.object_interface;
			return clockDispatchI.executeQueryFrequencyBySites(siteId);
		} else {
			throw new Exception("object_interface is error");
		}
	}
	/**
	 * 查询当前性能
	 * 
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<CurrentPerforInfo> executeQueryCurrPerforByFilter(CurrentPerformanceFilter filter) throws RemoteException, Exception {
		if (this.object_interface instanceof PerformanceDispatchI) {
			PerformanceDispatchI performanceDispatchI = (PerformanceDispatchI) this.object_interface;
			return performanceDispatchI.executeQueryCurrPerforByFilter(filter);
		} else {
			throw new Exception("object_interface is error");
		}
	}


	/**
	 * 查询長期性能
	 * 
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public String executeQueryHisPerforByTask(PerformanceTaskInfo taskInfo) throws RemoteException, Exception {
		
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof PerformanceDispatchI) {
				PerformanceDispatchI performanceDispatchI = (PerformanceDispatchI) this.object_interface;
				result = validateResult(performanceDispatchI.executeQueryHisPerforByTask(taskInfo));
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}
	/**
	 * 修改qos
	 * 
	 * @param qosInfoList
	 *            要修改的qos列队
	 * @param object
	 *            修改的业务类型。 tnnel、pw、ac
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String excutionUpdate(List<QosInfo> qosInfoList, Object object) throws RemoteException, Exception {
		
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof QosDispatchI) {
				QosDispatchI qosDispatchI = (QosDispatchI) this.object_interface;
				result = validateResult(qosDispatchI.excutionUpdate(qosInfoList, object));
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}

	/**
	 * 搜索段
	 * 
	 * @param siteInst
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String searchSegment(List<SiteInst> siteInst,List<Segment> seg) throws RemoteException, Exception {
		
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof SegmentDispatchI) {
				SegmentDispatchI segmentDispatchI = (SegmentDispatchI) this.object_interface;
				result = validateResult(segmentDispatchI.searchSegment(siteInst,seg));
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
		
	}

	/**
	 * 登陆网元
	 * 
	 * @param siteInstList
	 *            要登陆的网元集合
	 * @throws RemoteException
	 * @throws Exception
	 */
	public void siteLogin(List<SiteInst> siteInstList) throws RemoteException, Exception {
		if (this.object_interface instanceof SiteDispatchI) {
			SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
			siteDispatchI.siteLogin(siteInstList);
		} else {
			throw new Exception("object_interface is error");
		}
	}

	/**
	 * 查询网元
	 * 
	 * @param siteId
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public SiteInst selectSite(int siteId) throws RemoteException, Exception {
		if (this.object_interface instanceof SiteDispatchI) {
			SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
			return siteDispatchI.selectSite(siteId);
		} else {
			throw new Exception("object_interface is error");
		}
	}

	/**
	 * 网元校时
	 * 
	 * @param siteInst
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String currectTime(SiteInst siteInst) throws RemoteException, Exception {
		
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof SiteDispatchI) {
				SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
				result = validateResult(siteDispatchI.currectTime(siteInst));
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}

	/**
	 * 网元初始化
	 * 
	 * @param siteInst
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String clearSite(SiteInst siteInst) throws RemoteException, Exception {
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof SiteDispatchI) {
				SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
				result = validateResult(siteDispatchI.clearSite(siteInst));
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}

	/**
	 * 网元上载
	 * 
	 * @param siteInst
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public byte[] uploadConfig(SiteInst siteInst) throws RemoteException, Exception {
		if (this.object_interface instanceof SiteDispatchI) {
			SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
			return siteDispatchI.uploadConfig(siteInst);
		} else {
			throw new Exception("object_interface is error");
		}
	}

	/**
	 * 网元下载
	 * 
	 * @param siteInst
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String downloadConfig(SiteInst siteInst) throws RemoteException, Exception {
		
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof SiteDispatchI) {
				SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
				result = validateResult(siteDispatchI.downloadConfig(siteInst));
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
		
	}

	/**
	 * 网元搜索
	 * 
	 * @param ip
	 * @param manufacturer
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public List<SiteInst> siteSearch(String ip, int manufacturer) throws RemoteException, Exception {
		if (this.object_interface instanceof SiteDispatchI) {
			SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
			return siteDispatchI.siteSearch(ip, manufacturer);
		} else {
			throw new Exception("object_interface is error");
		}
	}

	/**
	 * 数据下载
	 * 
	 * @param a
	 *            要下载数据的类型
	 */
	public String dataDownLoadActionPerformed(List<SiteInst> siteInstList, int[] a) throws RemoteException, Exception {
		if (this.object_interface instanceof SiteDispatchI) {
			SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
			return siteDispatchI.dataDownLoadActionPerformed(siteInstList, a);
		} else {
			throw new Exception("object_interface is error");
		}
	}

	public TimeManageInfo executeQueryTimeManageInfoBySites(int siteId) throws RemoteException, Exception {
		if (this.object_interface instanceof ClockDispatchI) {
			ClockDispatchI clockDispatchI = (ClockDispatchI) this.object_interface;
			return clockDispatchI.executeQueryTimeManageInfoBySites(siteId);
		} else {
			throw new Exception("object_interface is error");
		}
	}

	/**
	 * 查询网元状态
	 * 
	 * @param siteInst
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public SiteStatusInfo siteStatus(SiteInst siteInst) throws RemoteException, Exception {
		if (this.object_interface instanceof SiteDispatchI) {
			SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
			return siteDispatchI.siteStatus(siteInst);
		} else {
			throw new Exception("object_interface is error");
		}
	}
	
	/**
	 * 查询网元状态
	 * 
	 * @param siteInst
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public OamStatusInfo oamStatus(SiteInst siteInst) throws RemoteException, Exception {
		if (this.object_interface instanceof SiteDispatchI) {
			SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
			return siteDispatchI.oamStatus(siteInst);
		} else {
			throw new Exception("object_interface is error");
		}
	}
	
	public String createOrDeleteDiscardFlow(SiteInst siteInst) throws RemoteException,Exception{
		
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if(this.object_interface instanceof SiteDispatchI){
				SiteDispatchI siteDispatchI =(SiteDispatchI) this.object_interface;
				result = validateResult(siteDispatchI.createOrDeleteDiscardFlow(siteInst));
			}else{
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
		
	}
	
	public List<UpgradeManage> softwareSummary(SiteInst siteInst) throws RemoteException,Exception{
		
			if(this.object_interface instanceof SiteDispatchI){
				SiteDispatchI siteDispatchI =(SiteDispatchI) this.object_interface;
				return siteDispatchI.softwareSummary(siteInst);
			}else{
				throw new Exception("object_interface is error");
			}
	}
	
	public String sendSummary(SiteInst siteInst) throws RemoteException,Exception{
		
		if(this.object_interface instanceof SiteDispatchI){
			SiteDispatchI siteDispatchI =(SiteDispatchI) this.object_interface;
			return siteDispatchI.sendSummary(siteInst);
		}else{
			throw new Exception("object_interface is error");
		}
}
	
	/**
	 * 取消软件升级
	 * 
	 * @param object
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public String executeQueryCancelSoftWare(SiteInst siteInset) throws RemoteException, Exception {
		
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof SiteDispatchI) {
				SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
				result = validateResult(siteDispatchI.cancelSlftWare(siteInset));
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}
	
	/**
	 * 初始化rmi
	 * 
	 * @return 服务器传回来的bean 界面显示用
	 * @throws Exception 
	 */
	public ServiceBean init() throws Exception{
		if (this.object_interface instanceof RmiInitI) {
			RmiInitI rmiInitI = (RmiInitI) this.object_interface;
			return rmiInitI.init();
		} else {
			throw new Exception("object_interface is error");
		}
	}
	
	/**
	 * 清除设备性能
	 * 
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public String executeCleanCurrPerforByFilter(CurrentPerformanceFilter filter) throws RemoteException, Exception {
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof PerformanceDispatchI) {
				PerformanceDispatchI performanceDispatchI = (PerformanceDispatchI) this.object_interface;
				result = performanceDispatchI.executeCleanCurrPerforByFilter(filter);
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}

	/**
	 * 清除告警
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public void clearAlarm(Object object) throws RemoteException, Exception {
		try {
			if (this.object_interface instanceof AlarmDispatchI) {
				AlarmDispatchI alarmDispatchI = (AlarmDispatchI) this.object_interface;
				alarmDispatchI.clearAlarm(object);
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
	}
	
	/**
	 * 查询所有的长期性能任务
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public List<PerformanceTaskInfo> getAllPerformanceTask(Object object) throws RemoteException, Exception {
		List<PerformanceTaskInfo> performanceTaskInfoList = new ArrayList<PerformanceTaskInfo>();
		try {
			if (this.object_interface instanceof PerformanceDispatchI) {
				PerformanceDispatchI performanceDispatchI = (PerformanceDispatchI) this.object_interface;
				performanceTaskInfoList = performanceDispatchI.getAllPerformanceTask(object);
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return performanceTaskInfoList;
	}
	
	/**
	 * 告警屏蔽
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public String alarmShield(Object object) throws RemoteException, Exception {
		
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof AlarmDispatchI) {
				AlarmDispatchI alarmDispatchI = (AlarmDispatchI) this.object_interface;
				result = validateResult(alarmDispatchI.alarmSheild(object));
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}
	/**
	 * 查询SN
	 * @param siteInst
	 * @param isLocaltion
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
	public List<SiteInst> querySn(SiteInst siteInst,int isLocaltion) throws RemoteException, Exception {
		List<SiteInst> SiteInstList = new ArrayList<SiteInst>();
		try {
			if (this.object_interface instanceof SiteDispatchI) {
				SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
				SiteInstList =  siteDispatchI.querySn(siteInst, isLocaltion);
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return SiteInstList;
	}
	
	/**
	 * 设置网元IP
	 * @param siteInst
	 * @return
	 */
	public String setSiteIP(SiteInst siteInst) throws RemoteException, Exception {
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof SiteDispatchI) {
				SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
				result = validateResult(siteDispatchI.setSiteIP(siteInst));
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
		
	}
	
	/**
	 * 拓扑自动发现
	 * @param siteInsts
	 * @param netWorkId
	 * @return
	 * @throws RemoteException
	 * @throws Exception
	 */
//	public String topological(List<SiteInst> siteInsts,int netWorkId,List<Segment> ss) throws RemoteException, Exception {
//		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
//		try {
//			if (this.object_interface instanceof SiteDispatchI) {
//				SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
//				result =  validateResult(siteDispatchI.topologicalLinkFound(siteInsts, netWorkId,ss));
//			} else {
//				throw new Exception("object_interface is error");
//			}
//		} catch (Exception e) {
//			ExceptionManage.dispose(e, getClass());
//		}
//		return result;
//	}
	
	public List<Segment> topological(List<SiteInst> siteInsts,int netWorkId,List<Segment> ss) throws RemoteException, Exception {
		try {
			if (this.object_interface instanceof SiteDispatchI) {
				SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
				ss=siteDispatchI.topologicalLinkFound(siteInsts, netWorkId,ss);
				//result =  validateResult(siteDispatchI.topologicalLinkFound(siteInsts, netWorkId,ss));
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return ss;
	}
	
	private String validateResult(String result){
		String sult = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if( result != null && !"".equals(result)){
				sult = result;
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return sult;
	}
	
	
	public Object pingCMD(String neIP){
		Object process = null;
		try {
			if (this.object_interface instanceof SiteDispatchI) {
				SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
				process = siteDispatchI.pingCMD(neIP);
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return process;
	}
	
	/**
	 * 网元定时重启
	 * @param siteInst
	 * @return
	 */
	public String taskRboot(List<SiteInst> siteInsts) throws RemoteException, Exception {
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof SiteDispatchI) {
				SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
				result = validateResult(siteDispatchI.taskRboot(siteInsts));
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
		
	}

	public String routeIn() throws RemoteException, Exception {
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof SiteDispatchI) {
				SiteDispatchI siteDispatchI = (SiteDispatchI) this.object_interface;
				result = validateResult(siteDispatchI.routeIn());
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
		
	}
	
	
	public String excutionUpdates(List<TimeSyncInfo> timesyns,Object object) throws RemoteException, Exception{
		String result = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			if (this.object_interface instanceof TimeSyncDispatchI) {
				TimeSyncDispatchI timeSyncDispatchI = (TimeSyncDispatchI) this.object_interface;
				result = validateResult(timeSyncDispatchI.excutionUpdates(timesyns, object));
			} else {
				throw new Exception("object_interface is error");
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return result;
	}
	
	
	
}