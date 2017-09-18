package com.nms.corba.ninterface.impl.resource.proxy;

import globaldefs.ExceptionType_T;
import globaldefs.NVSList_THolder;
import globaldefs.NameAndStringValue_T;
import globaldefs.ProcessingFailureException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;

import multiLayerSubnetwork.SubnetworkList_THolder;
import topologicalLink.TopologicalLinkIterator_IHolder;
import topologicalLink.TopologicalLinkList_THolder;
import topologicalLink.TopologicalLink_T;
import topologicalLink.TopologicalLink_THolder;

import com.nms.corba.ninterface.framework.ICorbaSession;
import com.nms.corba.ninterface.impl.notification.CorbaNotifyReport;
import com.nms.corba.ninterface.impl.resource.TopologicalLinkIterator_Impl;
import com.nms.corba.ninterface.impl.resource.tool.CorbaEMSMgrConvertTool;
import com.nms.corba.ninterface.impl.resource.tool.CorbaResConvertTool;
import com.nms.corba.ninterface.impl.util.CheckParameterUtil;
import com.nms.db.bean.path.Segment;
import com.nms.db.enums.EServiceType;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.dispatch.SegmentDispatch;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.VerifyNameUtil;

/**
 * 子网信息操作代理类
 *
 */
public class CorbaEMSMgrProxy {

	ICorbaSession session;
	private Properties localProperties;//读取文件的信息
	
	//构造方法给session赋值
	public CorbaEMSMgrProxy(ICorbaSession userSession){
		this.session = userSession;
	}
	
	/**
	 * 查询子网信息并进行转换
	 * @param sList 转换的对象集合
	 * @throws ProcessingFailureException
	 */
	public void getAllTopLevelSubnetworks(	SubnetworkList_THolder sList)throws ProcessingFailureException{
		try {
			this.getSubnetInfo();
			CorbaEMSMgrConvertTool.convertEMSMgrCorba(localProperties,sList);
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getAllTopLevelSubnetworks ex.");
		}
	}
	
	/**
	 * 修改子网信息配置文件中的友好名称
	 * @param objectName 传来对象判断是否为子网信息
	 * @param userLabel 友好名称
	 * @throws ProcessingFailureException 若不是子网信息则抛出异常
	 */
	public void setUserLabel(NameAndStringValue_T[] objectName,String userLabel) throws ProcessingFailureException{
		
		try {
			this.getSubnetInfo();
			changeEMSMgrCorba(localProperties,"SUBNETWORK_FRIENDLY_NAME",userLabel);
			CorbaNotifyReport.multilayerSubnetworkAttributeChg(objectName,"SUBNETWORK_FRIENDLY_NAME",userLabel);
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"setUserLabel ex.");
		}
	}
	
	/**
	 * 修改子网信息配置文件中的 物理地址 
	 * @param objectName 子网信息标示符
	 * @param additionalInfo 附加信息
	 * @throws ProcessingFailureException
	 */
	public void setAdditionalInfo(NameAndStringValue_T[] objectName,
			NVSList_THolder additionalInfo) throws ProcessingFailureException {
		
		try {
			this.getSubnetInfo();
			for (int i = 0; i < additionalInfo.value.length; i++) {
				if ( null != additionalInfo.value[i].name && additionalInfo.value[i].name.equals("macLocation")) {
					changeEMSMgrCorba(localProperties,"SUBNETWORK_MAC", additionalInfo.value[i].value);
					CorbaNotifyReport.multilayerSubnetworkAttributeChg(objectName,"SUBNETWORK_MAC",additionalInfo.value[i].value);
				}
			}
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"setUserLabel ex.");
		}
	}
	/**
	 * 读取配置文件信息
	 */
	public void getSubnetInfo(){
		
		String fileName = "config" + File.separator + "SubNetworkConfig.properties";
		File localFile = new File(fileName);
		localProperties = new Properties();
		
		FileInputStream fileInput;
		try {
			fileInput = new FileInputStream(localFile);
			BufferedInputStream fileBuffer = new BufferedInputStream(fileInput);
			localProperties.load(fileBuffer);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
		
	}

	/**
	 * 修改子网信息配置文件中的 物理地址 和 友好名称 具体操作
	 * @param localProperties 读取的配置文件信息
	 * @param key 配置文件中的键
	 * @param value 配置文件中的值
	 * @throws Exception 操作有误则跑出异常
	 */
	private void changeEMSMgrCorba(Properties localProperties,
			String key, String value) throws Exception {
		try {
			localProperties.setProperty(key, new String(value.getBytes("GB2312"),"ISO-8859-1"));
			FileOutputStream fos = new FileOutputStream("config" + File.separator + "SubNetworkConfig.properties"); 
			localProperties.store(fos, "Update '"+key+"' '"+value+"'");
			fos.close();
		} catch (Exception e) {
			throw new ProcessingFailureException(
					ExceptionType_T.EXCPT_INTERNAL_ERROR,
					"setUserLabel ex.");
		} 
	}
	
	/**
	 * 查询所有拓扑连接
	 * @param how_many 首次显示数量
	 * @param topoList	首次显示拓扑连接数据
	 * @param topoIt	拓扑连接数据迭代
	 * @throws ProcessingFailureException 
	 */
	public void getAllTopologicalLinks(int how_many,
			TopologicalLinkList_THolder topoList,
			TopologicalLinkIterator_IHolder topoIt) throws ProcessingFailureException {
		SegmentService_MB segmentService = null;
		CorbaResConvertTool corbaResConvertTool;
		try {
			corbaResConvertTool = new CorbaResConvertTool();
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			List<Segment> segmentList =	segmentService.select();
			segmentList = segmentService.select();
			if(null == segmentList || segmentList.size() == 0){
				topoList.value = new TopologicalLink_T[0];
				return;
			}
			topoList.value = new TopologicalLink_T[segmentList.size()];
			corbaResConvertTool.corbaTopologicalLinkListConvrtTool(segmentList,topoList.value);
			//迭代
			TopologicalLinkIterator_Impl iter = new TopologicalLinkIterator_Impl(this.session);
			iter.setIterator(topoIt, topoList, how_many);
			
		}  catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getAllTopLevelTopologicalLinks ex.");
		} finally {
			UiUtil.closeService_MB(segmentService);
		}
	}

	/**
	 * 查询拓扑连接
	 * @param topoLinkName	拓扑连接名称
	 * @param topoLink		拓扑连接
	 * @throws ProcessingFailureException 
	 * @throws Exception 
	 */
	public void getTopologicalLink(NameAndStringValue_T[] topoLinkName,
			TopologicalLink_THolder topoLink) throws ProcessingFailureException  {
		
		String[] id;
		Segment segment;
		List<Segment> segmentList;
		SegmentService_MB segmentService = null;
		CorbaResConvertTool corbaResConvertTool = new CorbaResConvertTool();
		try {
			if(!CheckParameterUtil.checkTopologicalLinkName(topoLinkName))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			if(topoLinkName[1].value.contains("/"));
			id = topoLinkName[1].value.split("/");
			segment = new Segment();
			segment.setId(Integer.parseInt(id[1]));
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			segmentList = segmentService.select(segment);
			if(null == segmentList || segmentList.size() != 1){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
			}
			topoLink.value = new TopologicalLink_T();
			corbaResConvertTool.corbaTopologicalLinkConvrtTool(segmentList.get(0),topoLink.value);
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"getTopologicalLink ex.");
		} finally {
			UiUtil.closeService_MB(segmentService);
		}
	}
	
	/**
	 * 修改拓扑链接名称
	 * @param objectName
	 * @param userLabel
	 * @throws ProcessingFailureException
	 */
	public void setTopoUserLabel(NameAndStringValue_T[] objectName,
			String userLabel) throws ProcessingFailureException {
		Segment segment;
		List<Segment> segmentList;
		SegmentService_MB segmentService = null;
		String[] id = null;
		SegmentDispatch segmentDispatch;
		try {
			segmentDispatch = new SegmentDispatch();
			if(!CheckParameterUtil.checkTopologicalLinkName(objectName))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"Invalid input parameters.");
			if(objectName[1].value.contains("/"))
			id = objectName[1].value.split("/");
			segment = new Segment();
			segmentService = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			
			segment.setName(null);
			segment.setId(Integer.parseInt(id[1]));
			segmentList = segmentService.select(segment);
			if(null == segmentList || segmentList.size() != 1){
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_ENTITY_NOT_FOUND,"The entity is not found.");
			}
			segment = segmentList.get(0);
			
			VerifyNameUtil verifyNameUtil=new VerifyNameUtil();
			if(verifyNameUtil.verifyName(EServiceType.SECTION.getValue(),userLabel , segment.getNAME()))
				throw new ProcessingFailureException(ExceptionType_T.EXCPT_INVALID_INPUT,"UserLabel already exists.");
			
			segment.setNAME(userLabel);
			segmentDispatch.excuteUpdate(segment);
		} catch (ProcessingFailureException e) {
			throw e;
		} catch (Exception e) {
			throw new ProcessingFailureException(ExceptionType_T.EXCPT_INTERNAL_ERROR,"setTopoUserLabel ex.");
		} finally {
			UiUtil.closeService_MB(segmentService);
		}
	}
}
