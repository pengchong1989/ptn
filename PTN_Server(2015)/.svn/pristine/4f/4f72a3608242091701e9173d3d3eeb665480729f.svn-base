package com.nms.service.impl.dispatch;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nms.db.bean.alarm.CurrentAlarmInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.drive.service.bean.AlarmObject;
import com.nms.model.alarm.CurAlarmService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.base.DispatchBase;
import com.nms.service.impl.cx.AlarmCXServiceImpl;
import com.nms.service.impl.dispatch.rmi.AlarmDispatchI;
import com.nms.service.impl.util.ResultString;
import com.nms.service.impl.wh.AlarmWHServiceImpl;
import com.nms.service.notify.Message.MessageType;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTip;

public class AlarmDispatch extends DispatchBase implements AlarmDispatchI{

	/**
	 * 根据网元查询所有的告警,并入库
	 * 
	 * @param objectList
	 *            网元数据库id集合
	 * @return
	 * @throws Exception
	 */
	public String executeQueryHisAlarmBySites(List<Integer> objectList) throws Exception {
		List<Integer> whSiteIdList = new ArrayList<Integer>();
		List<Integer> cxSiteIdList = new ArrayList<Integer>();
		int manufacturer = 0;
//		AlarmWHServiceImpl whOperationServiceI = null;
		AlarmCXServiceImpl operationServiceI = null;
		String ret = ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_FAIL);
		try {
			for (Integer id : objectList) {
				manufacturer = super.getManufacturer(id);
				if (manufacturer==EManufacturer.WUHAN.getValue()) {
					whSiteIdList.add(id);
				} else {
					cxSiteIdList.add(id);
				}
			}
//			whOperationServiceI = new AlarmWHServiceImpl();
			operationServiceI = new AlarmCXServiceImpl();
			// 查询告警，并入库
			if (cxSiteIdList != null && cxSiteIdList.size() > 0) {
				operationServiceI.selectAlarm(cxSiteIdList);
			}

			if (whSiteIdList != null && whSiteIdList.size() > 0) {
				// ret = whOperationServiceI.queryHisAlarmBySites(whSiteIdList);
			}

		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		} finally {

		}
		return ret;
	}

	/**
	 * 转换告警，并且把对应的告警个数，显示到界面中。 此方法要做个单独的线程，每隔20秒调用一次。
	 * 
	 * 主动上报告警。服务端启动时，启动此线程
	 * 
	 * @throws Exception
	 */
	public boolean convertAlarm() throws Exception {
		
		boolean flag=false;
		List<com.nms.drivechenxiao.service.bean.alarm.AlarmObject> alarmObject_cx = null;
		List<AlarmObject> alarmObject_wh = null;
		AlarmCXServiceImpl alarmCXServiceImpl = null;
		AlarmWHServiceImpl alarmWHServiceImpl = null;
		List<CurrentAlarmInfo> currentAlarmInfoList = null;
        try {
		alarmObject_cx = ConstantUtil.alarmObjectService.getAlarmObject_cx();
		ConstantUtil.alarmObjectService.setAlarmObject_cx(new ArrayList<com.nms.drivechenxiao.service.bean.alarm.AlarmObject>());
		alarmObject_wh = ConstantUtil.alarmObjectService.getAlarmObject_wh();
		ConstantUtil.alarmObjectService.setAlarmObject_wh(new ArrayList<AlarmObject>());
		if (alarmObject_cx != null && alarmObject_cx.size() > 0) {
			// 调用晨晓的转换层
			// 在转换层中，我会把驱动层的alarm对象转换成数据库的alarm对象，然后存放到map集合中。
			// map集合定义:Map<SiteInst,List<Map<String,Alarm>>>, List中的map
			// key是一个告警的唯一标识，
			// 比如是tunnel1的1号告警， 通过此KEY来判断此次推上来的告警是否是重复告警，如果重复了。就不添加到map中。
			// 这部分工作由转换层做，但是你要提供这个MAP，我能取到，并且能操作这个map。
			alarmCXServiceImpl = new AlarmCXServiceImpl();
			alarmCXServiceImpl.convertMap(alarmObject_cx);
	//			UiUtil.updateSiteInstAlarm();
			flag=true;
		}
		if (alarmObject_wh != null && alarmObject_wh.size() > 0) {
			// 调用武汉的转换层（武汉的告警暂时没有，所以先不用写）
			alarmWHServiceImpl = new AlarmWHServiceImpl();
			currentAlarmInfoList = alarmWHServiceImpl.registerHisAlarm(alarmObject_wh);
			//将设备报上来的告警数据转给Corba接口
			if(currentAlarmInfoList !=null && currentAlarmInfoList.size()>0){
				for (CurrentAlarmInfo obj : currentAlarmInfoList) {
					int slotId = obj.getSlotId();
					if(slotId >=0){
						int num = getSlotNumber(slotId);
						if(num !=-1){
							obj.setSlotNumber(num);
						}
					}
					super.notifyCorba("alarm", MessageType.ALARM, obj,"",ResultString.CONFIG_SUCCESS);
				}
			}
			flag=true;
		}
	  } catch (Exception e) {
		  ExceptionManage.dispose(e,this.getClass());
	  }finally
	  {
	    alarmObject_cx = null;
		alarmObject_wh = null;
		alarmCXServiceImpl = null;
		alarmWHServiceImpl = null;
		currentAlarmInfoList = null;
	  }
		return flag;
	}
	
	/**
	 * 同步设备当前告警(武汉)
	 * @param siteId
	 */
	public void synchroCurrentAlarm(Object siteId){
		AlarmWHServiceImpl whOperationServiceI = null;
		try {
			whOperationServiceI = new AlarmWHServiceImpl();
			whOperationServiceI.synchroCurrAlarmBySiteId((Integer)siteId);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			whOperationServiceI = null;
		}
	}
	
	/**
	 * 清除告警
	 */
	public void clearAlarm(Object object){
		CurrentAlarmInfo currentAlarmInfo = null;
		CurAlarmService_MB curservice = null;
		try {
			currentAlarmInfo = (CurrentAlarmInfo)object;
			curservice = (CurAlarmService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CurrentAlarm);
//			curservice.delete(currentAlarmInfo.getId());		
			int slotId = currentAlarmInfo.getSlotId();
			if(slotId >=0){
				int num = getSlotNumber(slotId);
				if(num !=-1){
					currentAlarmInfo.setSlotNumber(num);
				}
			}
			currentAlarmInfo.setClearedTime(new Date());
			//上报清除告警
			currentAlarmInfo.setAlarmLevel(0);
			super.notifyCorba("alarm", MessageType.ALARM, currentAlarmInfo,"alarm",ResultString.CONFIG_SUCCESS);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(curservice);
		}
	}
	
	/**
	 * 设置告警屏蔽功能
	 * @param siteId
	 */
	@Override
	public String alarmSheild(Object object) throws RemoteException, Exception {
		AlarmWHServiceImpl whOperationServiceI = null;
		try {
			whOperationServiceI = new AlarmWHServiceImpl();
			return whOperationServiceI.queryShieldAlarm(object);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			whOperationServiceI = null;
		}
		return "";
	}
	
	
}	
