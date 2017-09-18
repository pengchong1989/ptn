package com.nms.corba.ninterface.impl.resource.proxy;

import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.util.ArrayList;
import java.util.List;

import clockSource.ClockSourceList_THolder;
import clockSource.ClockSource_T;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.resource.tool.CorbaClockTool;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.db.bean.ptn.clock.ClockSource_Corba;
import com.nms.db.bean.ptn.clock.FrequencyInfo;
import com.nms.db.enums.EManufacturer;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.clock.ClockFrequService_MB;
import com.nms.model.ptn.clock.ClockSourceService_Corba_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.UiUtil;

/**
 * 时钟代理
 * @author dzy
 *
 */
public class CorbaClockProxy  extends CorbaConvertBase{
	ICorbaSession session;
	public CorbaClockProxy(ICorbaSession userSession) {
		this.session = userSession;
	}

	/**
	 * 查询时钟源
	 * @param meName 网元标识
	 * @param clockSourceList 时钟源信息集合
	 * @throws ProcessingFailureException 
	 */
	public void getMEClockSource(NameAndStringValue_T[] meName,
			ClockSourceList_THolder clockSourceList) throws ProcessingFailureException {
		
		ClockSourceService_Corba_MB clockSourceService_Corba = null;
		List<ClockSource_Corba> clockSource_CorbaList;
		SiteService_MB siteService = null;
		int neId;
		int manufacturer;
		CorbaClockTool corbaClockTool;
		try {
			if(!CheckParameterUtil.checkMeName(meName))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			
			corbaClockTool = new CorbaClockTool();
			siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
			clockSourceService_Corba = (ClockSourceService_Corba_MB) ConstantUtil.serviceFactory.newService_MB(Services.ClockSource_CORBA);
			neId = Integer.parseInt(meName[1].value);
			manufacturer = siteService.getManufacturer(neId);
			ClockSource_Corba clockSource_Corba= new ClockSource_Corba();
			clockSource_Corba.setSiteId(neId);
			if(manufacturer == EManufacturer.CHENXIAO.getValue()){
				clockSource_CorbaList = clockSourceService_Corba.queryByCondition(clockSource_Corba);
			}else{
				clockSource_CorbaList = getClockFrequ_WUHAN(neId);
			}
			if(null==clockSource_CorbaList||clockSource_CorbaList.size()==0){
				clockSourceList.value = new ClockSource_T[0];
				return;
			}
				
			//转换corba对象
			clockSourceList.value = new ClockSource_T[clockSource_CorbaList.size()];
			corbaClockTool.convertClockListDataX2Corba(clockSource_CorbaList, clockSourceList.value , manufacturer);
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getMEClockSource ex.");
		} finally {
			UiUtil.closeService_MB(siteService);
			UiUtil.closeService_MB(clockSourceService_Corba);
		}
	}

	/**
	 * 通过网元获取武汉时钟源
	 * @param neId 网元ID
	 * @return
	 * @throws ProcessingFailureException 
	 */
	private List<ClockSource_Corba> getClockFrequ_WUHAN(int neId) throws ProcessingFailureException {
		ClockFrequService_MB clockFrequService = null;
		List<ClockSource_Corba> list = null;
		List<FrequencyInfo> frequencyInfoList;
		try {
			list = new ArrayList<ClockSource_Corba>();
			clockFrequService=(ClockFrequService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ClockFrequ);
			frequencyInfoList = clockFrequService.query(neId);
			convertClockFrequ(list,frequencyInfoList);
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getClockFrequ_WUHAN ex.");
		}finally{
			UiUtil.closeService_MB(clockFrequService);
		}
		return list;
	}

	/**
	 * 转换武汉时钟频率
	 * @param list corba时钟频率对象
	 * @param frequencyInfoList		武汉时钟频率对象
	 */
	private void convertClockFrequ(List<ClockSource_Corba> list,
			List<FrequencyInfo> frequencyInfoList) {
		ClockSource_Corba clockSource_Corba;
		for(FrequencyInfo frequencyInfo:frequencyInfoList){
			clockSource_Corba = new ClockSource_Corba();
			clockSource_Corba.setId(frequencyInfo.getId());
			clockSource_Corba.setSiteId(frequencyInfo.getSiteId());
			clockSource_Corba.setPriorityLevel(-1);
			clockSource_Corba.setName("");
			if(0 == frequencyInfo.getClockWorkMode()){//自动， 提供三种时钟模式由设备选择
				clockSource_Corba.setModel(1);
				clockSource_Corba.setType(4);
			}
			if(1 == frequencyInfo.getClockWorkMode()){//保持， 设备保持之前的时钟类型不变
				clockSource_Corba.setModel(3);
				clockSource_Corba.setType(3);
			}
			if(2 == frequencyInfo.getClockWorkMode()){//自由震荡， 内时钟
				clockSource_Corba.setType(1);
				clockSource_Corba.setModel(2);
			}
			list.add(clockSource_Corba);
		}
	}

}
