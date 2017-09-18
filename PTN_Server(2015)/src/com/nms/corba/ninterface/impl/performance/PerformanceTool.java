package com.nms.corba.ninterface.impl.performance;

import globaldefs.ExceptionType_T;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import performance.PMCollectionTask_T;
import performance.PMData_T;
import performance.PMMeasurement_T;
import performance.PMTPSelect_T;

import com.nms.corba.ninterface.framework.CorbaConfig;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.corba.ninterface.util.CorbaConvertBase;
import com.nms.corba.ninterface.util.DateTimeUtil;
import com.nms.corba.ninterface.util.ELayerRate;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.perform.Capability;
import com.nms.db.bean.perform.CurrentPerforInfo;
import com.nms.db.bean.perform.PerformanceTaskInfo;
import com.nms.db.enums.EMonitorCycle;
import com.nms.db.enums.EObjectType;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.perform.CapabilityService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.ptn.performance.model.CurrentPerformanceFilter;

/**
 *性能的转换类
 * @author sy
 *
 */
public class PerformanceTool extends CorbaConvertBase{
	private Logger LOG = Logger.getLogger(PerformanceProxy.class.getName());
	
	/**
	 * 数据转换
	 * @param currPerformList
	 * @param value
	 * @throws ProcessingFailureException 
	 */
	public void convertAllPerf2Corba(List<CurrentPerforInfo> currPerformList, PMData_T[] value) throws ProcessingFailureException {
		PortService_MB portService =null;
		int portId=0;
		List<PortInst> portInstList=null;	
		try {
			portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
			for (int i = 0; i < currPerformList.size(); i++) {
				CurrentPerforInfo currPerform = currPerformList.get(i);
				PMData_T pm = new PMData_T();								
				portInstList = portService.selectPortbySiteandPortname(currPerformList.get(i).getSiteId(), currPerformList.get(i).getObjectName());					
				if(null != portInstList && portInstList.size()==1){
					portId=portInstList.get(0).getPortId();
				}
				pm.tpName = super.convertName(ELayerRate.PORT.getValue(), portId, currPerformList.get(i).getSiteId());				
				pm.layerRate = 1;// 板卡或者网元，暂时只支持网元
				pm.granularity =currPerform.getMonitorCycle()==1?"15min":"24h";//粒度15分钟，24小时
				pm.retrievalTime =DateTimeUtil.converCorbaTime( currPerform.getPerformanceTime(),DateUtil.FULLTIME);// 性能当前时间
				pm.pmMeasurementList = new PMMeasurement_T[1];
				pm.pmMeasurementList[0] = new PMMeasurement_T();
				pm.pmMeasurementList[0].pmParameterName = currPerform.getCapability().getCapabilityname();//性能名称
				pm.pmMeasurementList[0].pmLocation = "";
				pm.pmMeasurementList[0].value = currPerform.getCapability().getCapabilitycode();//性能值
				pm.pmMeasurementList[0].unit = currPerform.getCapability().getUnitName();//性能单位
				pm.pmMeasurementList[0].intervalStatus = "";//间隔状态
				value[i] = pm;
			}
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		} finally {
			UiUtil.closeService_MB(portService);
		}
	}
	
	/**
	 * 封装性能对象的属性值
	 * @param filter 
	 * @throws ProcessingFailureException 
	 */
	public void getSiteIdList(PMTPSelect_T[] pmTPSelectList, Object filter) throws ProcessingFailureException {
		List<Integer> siteIdList = new ArrayList<Integer>();
		int siteId=0;
		String portName="";//查询历史性能（查库）
		String ptpName="";
		List<String> portList=new ArrayList<String>();//查询当前性能（通过网元查设备，在通过端口过滤）
		for (PMTPSelect_T pm : pmTPSelectList) {
			if(pm.name!=null&&pm.name.length>0){//是否查询整个EMS
				if(pm.name.length<2||pm.name.length>3){// 有条件，即   网元或者 网元+端口
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is invalid!");
				}
				if(CheckParameterUtil.checkEmsName(pm.name[0])&&"ManagedElement".equals(pm.name[1].name)){//验证网元					
					siteId=Integer.parseInt(pm.name[1].value);
					if(siteId<=0){
						throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is invalid!");
					}
					//siteIdList.size()==0 首次循环直接将网元ID 加入到网元集合中
					if(siteIdList.size()==0||!siteIdList.contains(siteId)){//如果 网元ID 集合中还没有siteId，才添加到集合中，否则不在添加到集合中，以防重复
						siteIdList.add(siteId);
					}
//					else{//网元集合中已经存在此网元时，需要判断 ，查询粒度是否相同
//						
//					}
					
					if(CheckParameterUtil.checkPTPName(pm.name)&&super.checkPTPValue(pm.name[2].value)){//若果是3层，需要获取端口，以做端口过滤
						portName=pm.name[2].value.substring(pm.name[2].value.lastIndexOf("=") + 1, pm.name[2].value.length());
						if(!portList.contains(portName)){//如果给定端口名称 已经存在获取的端口集合中，就不在添加此端口名称到端口集合中
							portList.add(portName);
						}				
					}							
				}else{
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "name is invalid!");
				}
			}
		}
		if(UiUtil.isNull(portList)){
			for(int i=0;i<portList.size();i++){
				if(ptpName.equals("")){
					ptpName+="'"+portList.get(i)+"'";
				}else{
					ptpName+=",'"+portList.get(i)+"'";
				}
				
			}
		}
		//当前性能
		if(filter instanceof CurrentPerformanceFilter){
			((CurrentPerformanceFilter) filter).setSiteInsts(siteIdList);
			((CurrentPerformanceFilter) filter).setObjectType(EObjectType.SITEINST);
			//((CurrentPerformanceFilter) filter).setObjectName(portList);
		//历史性能
		}
	}
	/**
	 * 将性能任务对象：PerformanceTaskInfo 转为Corba对象
	 * @param performanceTaskInfo
	 * @return
	 */
	public PMCollectionTask_T converPMCllectionTask_T(PerformanceTaskInfo performanceTaskInfo){
		PMCollectionTask_T pmCollectionTask = new PMCollectionTask_T();
		//性能任务名称
		NameAndStringValue_T[] nameAndStringValue = new NameAndStringValue_T[2];
		nameAndStringValue[0] = new NameAndStringValue_T("EMS",CorbaConfig.getInstanse().getEmsVendorName()); 
		nameAndStringValue[1] = new NameAndStringValue_T("PMTask", performanceTaskInfo.getTaskName()); 
		pmCollectionTask.taskName = nameAndStringValue;
		//性能开始时间
		pmCollectionTask.beginTime =DateTimeUtil.converCorbaTime( performanceTaskInfo.getCreateTime(),DateUtil.FULLTIME);
		//性能结束时间
		if(performanceTaskInfo.getEndTime() != null ){
			pmCollectionTask.endTime = DateTimeUtil.converCorbaTime(performanceTaskInfo.getEndTime(),DateUtil.FULLTIME);
		}else{
			pmCollectionTask.endTime = "";
		}
		//性能周期
		pmCollectionTask.reportInterval = performanceTaskInfo.getMonitorCycle().getInterval()+"";
		//性能参数
		String[] perforType = performanceTaskInfo.getPerforType().trim().split(",");
		String[] pMParameterList = new String[perforType.length];
		for(int j=0;j<pMParameterList.length;j++){
			pMParameterList[j] = perforType[j];
		}
		pmCollectionTask.pMParameterList = pMParameterList;
		//name 对象
		PMTPSelect_T pmTPSelect = new PMTPSelect_T();
		NameAndStringValue_T[] pmTPSelectObject = new NameAndStringValue_T[2];
		pmTPSelectObject[0] = new NameAndStringValue_T(super.EMS,  CorbaConfig.getInstanse().getCorbaEmsName());
		pmTPSelectObject[1] = new NameAndStringValue_T(super.MANAGEELEMENT, performanceTaskInfo.getSiteInst().getSite_Inst_Id()+"");
		
		pmTPSelect.name = pmTPSelectObject;
		String[] granularityList = new String[1];
		granularityList[0] = performanceTaskInfo.getMonitorCycle().getInterval()+"";
		pmTPSelect.granularityList = granularityList;
		
		//根据通道所有的属性必须赋值
		short[] layerRate = {1};
		String[]  pm = {"0"};
		pmTPSelect.layerRateList = layerRate;
		pmTPSelect.pMLocationList = pm;
		pmCollectionTask.additionalInfo = new NameAndStringValue_T[1];
		pmCollectionTask.additionalInfo[0]=new NameAndStringValue_T("pmTaskId",performanceTaskInfo.getId()+"");//性能任务主键ID
		pmCollectionTask.name = pmTPSelect;
		return pmCollectionTask;
	}
	/**
	 * 获取监控周期
	 * @param pmTPSelectList
	 * @param filter
	 * @throws ProcessingFailureException 
	 */
	public List<CurrentPerformanceFilter> getMonitoringCycle(PMTPSelect_T[] pmTPSelectList, CurrentPerformanceFilter filter) throws ProcessingFailureException {
		List<String> layerList=null;
		List<CurrentPerformanceFilter> curPerformanceListFilter=new ArrayList<CurrentPerformanceFilter>();
		CurrentPerformanceFilter cur15m=new CurrentPerformanceFilter();
		CurrentPerformanceFilter cur24h=new CurrentPerformanceFilter();
		boolean flag=false;
		try{
			if(pmTPSelectList.length > 0){
				PMTPSelect_T pm = pmTPSelectList[0];
				String[] layer=pm.granularityList;//粒度：  15min、24h			
				if(layer != null  ){
					layerList=Arrays.asList(layer);				
					if(layerList.size()==0||layerList.contains("15min")){
						//cur15m=filter;
						//15分钟
						cur15m.setObjectName(filter.getObjectName());
						cur15m.setCapabilityIdList(filter.getCapabilityIdList());
						cur15m.setCapabilityNameList(filter.getCapabilityNameList());
						cur15m.setSlotInsts(filter.getSlotInsts());
						cur15m.setSiteInsts(filter.getSiteInsts());
						cur15m.setObjectType(filter.getObjectType());
						cur15m.setTypeStr(filter.getTypeStr());
						cur15m.setPerformanceType(0);
						cur15m.setMonitorCycle(EMonitorCycle.MIN15);						
						//当前15分钟
						cur15m.setPerformanceCount(0);
						cur15m.setPerformanceBeginCount(0);
						cur15m.setPerformanceMonitorTime(porseTime(0));	
						cur15m.setPerformanceOverTime(DateUtil.getDate(DateUtil.FULLTIME));						
						curPerformanceListFilter.add(cur15m);
						flag=true;
					} 
					if(layerList.size()==0||layerList.contains("24hour")){
						//24小时
						cur24h.setObjectName(filter.getObjectName());
						cur24h.setSlotInsts(filter.getSlotInsts());
						cur24h.setCapabilityIdList(filter.getCapabilityIdList());
						cur24h.setCapabilityNameList(filter.getCapabilityNameList());
						cur24h.setSiteInsts(filter.getSiteInsts());
						cur24h.setObjectType(filter.getObjectType());
						cur24h.setTypeStr(filter.getTypeStr());
						cur24h.setMonitorCycle(EMonitorCycle.HOUR24);
						//当前24小时
						cur24h.setPerformanceCount(255);
						cur24h.setPerformanceBeginCount(0);
						cur24h.setPerformanceType(32);
						cur24h.setPerformanceMonitorTime(porseTime(100));
						cur24h.setPerformanceOverTime(DateUtil.getDate(DateUtil.FULLTIME));
						curPerformanceListFilter.add(cur24h);
						flag=true;
					}
					if(!flag) {// 输入的粒度没有与之（15m,24h）相符，
						throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "granularityList is invalid!");
					}					
				}else{
					throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "granularityList is null!");
				}
			}	
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
		return curPerformanceListFilter;
	}
	/**
	 * 处理时间 
	 * @param preseTime
	 * @return
	 */
	private String porseTime(int size) {
		SimpleDateFormat fat = null;
		SimpleDateFormat fat2 = null;
		String preseTimeString = null;
		String preseTimeYear = null;
		String[] str = null;
		Date date = null;
		Date dateNow = null;
		long dateLong = 0;
		long  preseTime = 0; 
		try {
			dateNow = new Date();
			dateLong = dateNow.getTime();
			if(size >= 100){
				fat2 = new SimpleDateFormat("yyyy-MM");
				fat = new SimpleDateFormat("d ");
				preseTime = dateLong-size*24*60 * 60 * 1000;
				date = new Date(preseTime);
				preseTimeYear = fat2.format(date);
				if(size == 100){
					preseTimeString = preseTimeYear ;
				}if(size == 101){
					date = new Date(preseTime);
					preseTimeString = fat.format(date);
					str = preseTimeString.split(":");
					preseTimeString = preseTimeYear+"-"+"";
				}
			}else{
				preseTime = dateLong-size*15 * 60 * 1000;
				fat = new SimpleDateFormat("HH:mm:ss");
				fat2 = new SimpleDateFormat("yyyy-MM-dd");
				date = new Date(preseTime);
				preseTimeYear = fat2.format(date);
				preseTimeString = fat.format(date);
				str = preseTimeString.split(":");
				int i = Integer.valueOf(str[1]) / 15;
				preseTimeString = preseTimeYear + " " + str[0] + ":";
				if (i == 0) {
					preseTimeString += "00";
				}else if (i == 1) {
					preseTimeString += "15";
				}else if (i == 2) {
					preseTimeString += "30";
				}else if (i == 3) {
					preseTimeString += "45";
				}
			}
		} catch (Exception e) {
			LOG.error(e);
		}
		return preseTimeString;
	}
	/**
	 * 取出Corba入参，转为性能过滤对象
	 * @param pmTPSelectList
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws ProcessingFailureException
	 */
	public List<CorbaPerformance> getPerformance(PMTPSelect_T[] pmTPSelectList, 
			String startTime, String endTime)throws ProcessingFailureException{
		int siteId=0;
		String portName="";
		String[] layers=null;
		List<String> layerList=null;
		List<Integer> monList=null;
		CorbaPerformance corbaPerformance=null;//查询历史性能 条件的对象
		List<CorbaPerformance> perforList=new ArrayList<CorbaPerformance>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		try{
			if(pmTPSelectList.length>0){
				for (PMTPSelect_T pm : pmTPSelectList) {
					corbaPerformance=new CorbaPerformance();
					monList=new ArrayList<Integer>();
					if(pm.name!=null&&pm.name.length>0){//是否查询整个EMS
						if(pm.name.length<2||pm.name.length>3){// 有条件，即   网元或者 网元+端口
							throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is invalid!");
						}
						if(CheckParameterUtil.checkEmsName(pm.name[0])&&"ManagedElement".equals(pm.name[1].name)){//验证网元					
							siteId=Integer.parseInt(pm.name[1].value);
							if(siteId<=0){
								throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "name is invalid!");
							}
							if(CheckParameterUtil.checkPTPName(pm.name)&&super.checkPTPValue(pm.name[2].value)){//若果是3层，需要获取端口，以做端口过滤
								if(!super.checkPTPValue(pm.name[2].value)){
									throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"The PTP is invalid!");
								}
								portName=pm.name[2].value.substring(pm.name[2].value.lastIndexOf("=") + 1, pm.name[2].value.length());			
														
							}							
						}else{
							throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "name is invalid!");
						}
					}
					layers = pm.granularityList;
					layerList=Arrays.asList(layers);
					if(layerList!=null){//取出粒度，转为List
						if(layerList.size()==0||layerList.contains("15min")){
							monList.add(EMonitorCycle.valueOf("MIN15").getValue());
						}
						if(layerList.size()==0||layerList.contains("24hour")){
							monList.add(EMonitorCycle.valueOf("HOUR24").getValue());
						}
					}else{
						throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT, "granularityList is null!");
					}
					corbaPerformance.setPortName(portName);
					corbaPerformance.setSiteId(siteId);
					corbaPerformance.setLayerList(monList);
					if(UiUtil.isNull(startTime)&&UiUtil.isNull(endTime)){
						corbaPerformance.setStartTime(sdf.parse(startTime).getTime());
						corbaPerformance.setEndTime(sdf.parse(endTime).getTime());
					}					
					perforList.add(corbaPerformance);
				}
			}else{//查询所有
				corbaPerformance=new CorbaPerformance();
				corbaPerformance.setSiteId(-1);//标识  ： 当前性能应该查询所有网元
				if(UiUtil.isNull(startTime)&&UiUtil.isNull(endTime)){
					corbaPerformance.setStartTime(sdf.parse(startTime).getTime());
					corbaPerformance.setEndTime(sdf.parse(endTime).getTime());
				}	
				monList=new ArrayList<Integer>();			
				monList.add(EMonitorCycle.valueOf("MIN15").getValue());
				monList.add(EMonitorCycle.valueOf("HOUR24").getValue());
				corbaPerformance.setLayerList(monList);
				perforList.add(corbaPerformance);//查询所有历史性能
			}
		}catch(ProcessingFailureException e){
			throw e;
		}
		catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}
		return perforList;
	}
	/**
	 * 添加所有性能类型条件和所有性能类别条件
	 * @return 
	 */
	public String getPerformType(List<Integer> capabilityIdList,
									List<String> capabilityNameList)  throws ProcessingFailureException{
		String strType = "";
		CapabilityService_MB service = null;
		try{
			service = (CapabilityService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Capability);
			List<Capability> perforTypeList = service.select();
			List<Capability> perforList = perforTypeList;
			perforTypeList = this.removeRepeatedType(perforTypeList, 1);
			perforList = this.removeRepeatedType(perforList, 2);		
			for (Capability c : perforTypeList) {
				capabilityIdList.add(Integer.valueOf(c.getId()));
				strType += c.getCapabilitytype()+",";
			}
			strType = strType.substring(0, strType.length() - 1);
			if(UiUtil.isNull(perforList)){
				for (Capability c : perforList) {
					capabilityNameList.add(c.getCapabilitydesc());
				}
			}			
		}catch(ProcessingFailureException e){
			throw e;
		}catch(Exception e){
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR, "Internal error : The process has internal error!");
		}finally{
			UiUtil.closeService_MB(service);
		}
		return strType;
	}
	/**
	 * 去掉重复的值
	 * @param perforTypeList
	 * @param label
	 * @return
	 */
	private List<Capability> removeRepeatedType(List<Capability> perforTypeList, int label) {
		List<Capability> norepeatedCapabilityList = new ArrayList<Capability>();
		norepeatedCapabilityList.addAll(perforTypeList);
		//增加性能类型
		if(label == 1){ 
			for (int i = 0; i < perforTypeList.size() - 1; i++) {
				for (int j = perforTypeList.size() - 1; j > i; j--) {
					if (perforTypeList.get(j).getCapabilitytype().equals(perforTypeList.get(i).getCapabilitytype())) {
						norepeatedCapabilityList.remove(perforTypeList.get(j));
					}
				}
			}
		}else{
			//增加性能类别
			for (int i = 0; i < perforTypeList.size() - 1; i++) {
				for (int j = perforTypeList.size() - 1; j > i; j--) {
					if (perforTypeList.get(j).getCapabilitydesc().equals(perforTypeList.get(i).getCapabilitydesc())) {
						norepeatedCapabilityList.remove(perforTypeList.get(j));
					}
				}
			}
		}
		return norepeatedCapabilityList;
	}
}
