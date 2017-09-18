package com.nms.ui.ptn.alarm.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nms.db.bean.alarm.CurrentAlarmInfo;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.path.ces.CesInfo;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.eth.ElineInfo;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.ptn.port.PortLagInfo;
import com.nms.model.equipment.port.PortService_MB;
import com.nms.model.path.SegmentService_MB;
import com.nms.model.ptn.path.ces.CesInfoService_MB;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.eth.ElineInfoService_MB;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.tunnel.TunnelService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.ptn.port.PortLagService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;


public class AlarmAnalyse
{
	private CurrentAlarmInfo currentAlarmInfo;
	@SuppressWarnings("unchecked")
	private ViewDataTable mainView;
	@SuppressWarnings("unchecked")
	private ViewDataTable relatedView;
	private final List<Integer> pwIdList = new ArrayList<Integer>();
	private final List<ElineInfo> Elines = new ArrayList<ElineInfo>();
	List<PortInst> portlist = new ArrayList<PortInst>();
	
	@SuppressWarnings("unchecked")
	public AlarmAnalyse(CurrentAlarmInfo currentAlarmInfo, ViewDataTable mainView, ViewDataTable relatedView )
	{
		setCurrentAlarmInfo(currentAlarmInfo);
		setView(mainView);
		setRelatedView(relatedView);
	}
	
	//根据告警的类型来区分该显示哪些业务
	public void show()
	{
		clearData();
		String type = currentAlarmInfo.getObjectType().toString();
		if(type.equals("E1"))
		{
			showMainCES();
		}
		else if(type.equals("PW"))
		{
			showMainPW(0);
			showMainService(1);
		}
		else if(type.equals("TUNNEL"))
		{
			List<Tunnel> tunnels = showMainTunnel(0);
			showMainPWByTunnel(1,tunnels);
			showRelatedService();
		}
		else if(type.equals("SEGMENT"))
		{
			showMainSegments();
			List<Tunnel> tunnels = showMainTunnelBySegment(1);
			showMainPWByTunnel(1,tunnels);
			showRelatedService();
		}
		else if(type.equals("PORT"))
		{
			PortInst portInst = getPortType();
			if(portInst.getPortType().equals("UNI"))
			{
				showMainServiceByPortid(0,portInst);
			}
			else if(portInst.getPortType().equals("NNI"))
			{
				List<Tunnel> tunnels = showTunnel(portInst);
				showMainPWByTunnel(1,tunnels);
				showRelatedService();
			}
		}
	}
	
	private void showMainService(int times)
	{
		showMainEline(pwIdList, times);
		showMainEtree(pwIdList, 1);
		showMainElan(pwIdList, 1);
	}
	
	private void showMainServiceByPortid(int times,PortInst portInst)
	{
		AcPortInfoService_MB acPortInfoServiceMB = null;
		List<Integer> acids = new ArrayList<Integer>();
		PortLagService_MB portLagServiceMB = null;
		List<AcPortInfo> acPortInfos = null;
		try {
			acPortInfoServiceMB = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);
			if(portInst.getLagId() == 0){
				acids = acPortInfoServiceMB.acByPort(portInst.getPortId(), portInst.getSiteId());
			}else{
				portLagServiceMB = (PortLagService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORTLAG);
				PortLagInfo portLagInfo = new PortLagInfo();
				portLagInfo.setSiteId(portInst.getSiteId());
				portLagInfo.setId(portInst.getLagId());
				portLagInfo = portLagServiceMB.selectLAGByCondition(portLagInfo).get(0);
				
				AcPortInfo acPortInfo = new AcPortInfo();
				acPortInfo.setSiteId(portInst.getSiteId());
				acPortInfo.setLagId(portLagInfo.getId());
				acPortInfos = acPortInfoServiceMB.selectByCondition(acPortInfo);	
				for (int i = 0; i < acPortInfos.size(); i++) {
					acids.add(acPortInfos.get(i).getId());
				}
			}
			
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(acPortInfoServiceMB);
			UiUtil.closeService_MB(portLagServiceMB);
		}
		showMainElineByacid(acids, times);
		showMainEtreeandElan(acids, 1);
	}
	
	private void showRelatedService()
	{
		showRelatedEtree(pwIdList);
		showRelatedEline(pwIdList);
		showRelatedElan(pwIdList);
	}
	
	//第一次加载数据用initData，后来就用addData
	@SuppressWarnings("unchecked")
	private List<Tunnel> showMainTunnel(int times)
	{
		Tunnel tunnel = queryTunnel(currentAlarmInfo.getSiteId(),currentAlarmInfo.getObjectId());
		List<Tunnel> tunnels = new ArrayList<Tunnel>();
		tunnels.add(tunnel);
		convertTunnelData(tunnels);
		if(times == 0)
		{
			this.mainView.initData(tunnels);
		}
		else if(times == 1)
		{
			this.mainView.addData(tunnels);
		}
		
		return tunnels;
	}
	
	//第一次加载数据用initData，后来就用addData
	@SuppressWarnings("unchecked")
	private List<Tunnel> showMainTunnelBySegment(int times)
	{
		TunnelService_MB tunnelServiceMB = null;
		List<Tunnel> tunnels = null;
		try {
			tunnelServiceMB = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnels = tunnelServiceMB.selectNodesBySiteId(currentAlarmInfo.getSiteId());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(tunnelServiceMB);
		}
		convertTunnelData(tunnels);
		if(times == 0)
		{
			this.mainView.initData(tunnels);
		}
		else if(times == 1)
		{
			this.mainView.addData(tunnels);
		}
		return tunnels;
	}
	
	@SuppressWarnings("unchecked")
	private List<Tunnel> showTunnel(PortInst portInst)
	{
		TunnelService_MB tunnelServiceMB = null;
		List<Tunnel> tunnels = null;
		try {
			tunnelServiceMB = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnels = tunnelServiceMB.findTunnelByPortId(portInst.getPortId());
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(tunnelServiceMB);
		}
		
		convertTunnelData(tunnels);
		this.mainView.initData(tunnels);
		
		return tunnels;
	}
	@SuppressWarnings("unchecked")
	private void showMainCES()
	{
		List<CesInfo> cesList = null;
		CesInfoService_MB services = null;
		try
		{
			services = (CesInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.CesInfo);
			cesList = services.selectNodeBySite(currentAlarmInfo.getSiteId());
			convertCESData(cesList);
			this.mainView.initData(cesList);
		}
		catch (Exception e)
		{
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(services);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	private void showMainPW(int times)
	{
		List<PwInfo> PWs = queryPWs(currentAlarmInfo.getSiteId(),currentAlarmInfo.getObjectId());
		for(PwInfo pwinfo: PWs)
		{
			pwIdList.add(pwinfo.getPwId());
		}
		convertPwData(PWs);
		if(times==0)
		{
			this.mainView.initData(PWs);
		}
		else if(times==1)
		{
			this.mainView.addData(PWs);
		}
		
	}

	@SuppressWarnings("unchecked")
	private void showMainPWByTunnel(int times,List<Tunnel> tunnels)
	{
		PwInfoService_MB pwInfoServiceMB = null;
		List<PwInfo> pwInfos = null;
		try {
			pwInfoServiceMB = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			List<Integer> integers = new ArrayList<Integer>();
			for (int i = 0; i < tunnels.size(); i++) {
				integers.add(tunnels.get(i).getTunnelId());
			}
			pwInfos = pwInfoServiceMB.selectPwInfoByTunnelId(integers);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(pwInfoServiceMB);
		}
		
		for(PwInfo pwinfo: pwInfos)
		{
			pwIdList.add(pwinfo.getPwId());
		}
		convertPwData(pwInfos);
		if(times==0)
		{
			this.mainView.initData(pwInfos);
		}
		else if(times==1)
		{
			this.mainView.addData(pwInfos);
		}
	}
	
	//清楚面板中的数据
	@SuppressWarnings("unchecked")
	public void clearData()
	{
		this.mainView.initData(null);
		this.relatedView.initData(null);
	}
	
	@SuppressWarnings("unchecked")
	private void showRelatedEline(List<Integer> pwIdList)
	{
		List<ElineInfo> Elines = queryElineByPwids(pwIdList);
		convertElineData(Elines);
		this.relatedView.addData(Elines);
	}
	
	@SuppressWarnings("unchecked")
	private void showMainEline(List<Integer> pwIdList, int times)
	{
		List<ElineInfo> Elines = queryElineByPwids(pwIdList);
		convertElineData(Elines);
		if(times==0)
		{
			this.mainView.initData(Elines);
		}
		else if(times==1)
		{
			this.mainView.addData(Elines);
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	private void showMainElineByacid(List<Integer> acids, int times)
	{
		ElineInfoService_MB elineInfoServiceMB = null;
		List<ElineInfo> Elines = null;
		try {
			elineInfoServiceMB = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			Elines = elineInfoServiceMB.selectByacids(acids);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(elineInfoServiceMB);
		}
		convertElineData(Elines);
		if(times==0)
		{
			this.mainView.initData(Elines);
		}
		else if(times==1)
		{
			this.mainView.addData(Elines);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void showRelatedEtree(List<Integer> pwIdList)
	{
		List<EtreeInfo> etrees = queryEtreeByPwid(pwIdList);
		convertEtreeData(etrees);
		this.relatedView.initData(etrees);
	}
	
	@SuppressWarnings("unchecked")
	private void showMainEtreeandElan(List<Integer> acids, int times)
	{
		EtreeInfoService_MB etreeInfoServiceMB = null;
		List<EtreeInfo> etreeInfos = new ArrayList<EtreeInfo>();
		try {
			etreeInfoServiceMB = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			for (int i = 0; i < acids.size(); i++) {
				List<EtreeInfo> infos = etreeInfoServiceMB.selectEtreeOrElanByAcIdAndSiteId(acids.get(i), currentAlarmInfo.getSiteId());
				if(infos != null){
					etreeInfos.addAll(infos);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
			UiUtil.closeService_MB(etreeInfoServiceMB);
		}
		convertEtreeData(etreeInfos);
		if(times==0)
		{
			this.mainView.initData(etreeInfos);
		}
		else if(times==1)
		{
			this.mainView.addData(etreeInfos);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void showMainEtree(List<Integer> pwIdList, int times)
	{
		List<EtreeInfo> etrees = queryEtreeByPwid(pwIdList);
		convertEtreeData(etrees);
		if(times==0)
		{
			this.mainView.initData(etrees);
		}
		else if(times==1)
		{
			this.mainView.addData(etrees);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void showMainElan(List<Integer> pwIdList, int times)
	{
		List<ElanInfo> elans = queryElansByPw(pwIdList);
		convertELANData(elans);
		if(times==0)
		{
			this.mainView.initData(elans);
		}
		else if(times==1)
		{
			this.mainView.addData(elans);
		}
	}
	
	@SuppressWarnings("unchecked")
	private void showRelatedElan(List<Integer> pwIdList)
	{
		List<ElanInfo> elans = queryElansByPw(pwIdList);
		convertELANData(elans);
		this.relatedView.addData(elans);
	}
	
	@SuppressWarnings("unchecked")
	private void showMainSegments()
	{
		List<Segment> segments = queryAllSegments(this.currentAlarmInfo.getSiteId());
		
		convertSegmentData(segments);
		this.mainView.initData(segments);
	}
	
	private List<Segment>  queryAllSegments(int id)
	{
		List<Segment> segments = new ArrayList<Segment>(); 
		SegmentService_MB segmentservice = null;
		try
		{
			segmentservice = (SegmentService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SEGMENT);
			segments = segmentservice.queryBySiteId(id);
		}
		catch (Exception e)
		{
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(segmentservice);
		}
		
		return segments;
	}
	private List<ElineInfo> queryElineByPwids(List<Integer> pwIdList)
	{
		List<ElineInfo> Elines = new ArrayList<ElineInfo>();
		if(pwIdList.size()>0)
		{
			ElineInfoService_MB elineService = null;
			try
			{
				elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
				Elines = elineService.selectElineByPwId(pwIdList);
			}
			catch (Exception e)
			{
				ExceptionManage.dispose(e,this.getClass());
			}finally{
				UiUtil.closeService_MB(elineService);
			}
		}
		return Elines;
	}
	private Tunnel queryTunnel(int siteId,int serviceId)
	{
		Tunnel tunnel = new Tunnel();
		TunnelService_MB tunnelService = null;
		try
		{
			tunnelService = (TunnelService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Tunnel);
			tunnel = tunnelService.selectBySiteIdAndServiceId(siteId, serviceId);
		}
		catch (Exception e)
		{
			ExceptionManage.dispose(e,this.getClass());
		}finally
		{
			UiUtil.closeService_MB(tunnelService);
		}
	
		return tunnel;
	}
	@SuppressWarnings("unused")
	private List<ElineInfo> queryAllElines(int id)
	{		
		ElineInfoService_MB elineService = null;
		try
		{
			elineService = (ElineInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.Eline);
			Elines.addAll(elineService.selectNodeBySite(id));
		}
		catch (Exception e)
		{
			ExceptionManage.dispose(e,this.getClass());
		}finally
		{
			UiUtil.closeService_MB(elineService);
		}
		
		return Elines;
	}
	
	private  List<EtreeInfo> queryEtreeByPwid(List<Integer> pwIdList)
	{
		List<EtreeInfo> etrees = new ArrayList<EtreeInfo>();
		if(pwIdList.size()>0)
		{
			EtreeInfoService_MB etreeService = null;
			try
			{
				etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
				etrees = etreeService.selectEtreeByPwId(pwIdList);
			}
			catch (Exception e)
			{
				ExceptionManage.dispose(e,this.getClass());
			}finally
			{
				UiUtil.closeService_MB(etreeService);
			}
		}
		return etrees;
		
	}
	@SuppressWarnings("unused")
	private Map<String, List<EtreeInfo>> queryAllEtrees(int id)
	{
		Map<String, List<EtreeInfo>> etrees = new HashMap<String, List<EtreeInfo>>();
		EtreeInfoService_MB etreeService = null;
		try
		{
			etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			etrees = etreeService.selectNodeBySite(id);
		}
		catch (Exception e)
		{
			ExceptionManage.dispose(e,this.getClass());
		}finally
		{
			UiUtil.closeService_MB(etreeService);
		}
		
		return etrees;
	}

	
	//通过pwid查相关的elan以显示在相关影响业务上
	private  List<ElanInfo> queryElansByPw(List<Integer> pwIdList)
	{
		List<ElanInfo> Elans = new ArrayList<ElanInfo>();
		if(pwIdList.size()>0)
		{
			ElanInfoService_MB elanInfoService = null;
			try
			{
				elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
				Elans = elanInfoService.selectElanbypwid(pwIdList);
			}
			catch (Exception e)
			{
				ExceptionManage.dispose(e,this.getClass());
			}finally
			{
				UiUtil.closeService_MB(elanInfoService);
			}
		}
		return Elans;
	}
	@SuppressWarnings("unused")
	private Map<String, List<ElanInfo>>  queryAllElans(int id)
	{
		Map<String, List<ElanInfo>> elans = new HashMap<String, List<ElanInfo>>();
		ElanInfoService_MB elanInfoService = null;
		try
		{
			elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);
			elans = elanInfoService.selectSiteNodeBy(id);
		}
		catch (Exception e)
		{
			ExceptionManage.dispose(e,this.getClass());
		}finally
		{
			UiUtil.closeService_MB(elanInfoService);
		}
		
		return elans;
	}
	
	private List<PwInfo>  queryPWs(int siteId,int serviceId)
	{
		List<PwInfo> PWs = new ArrayList<PwInfo>();
		PwInfoService_MB pwservice  = null;
		try
		{
			pwservice = (PwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);
			PWs = pwservice.selectBysiteIdAndServiceId(siteId,serviceId);
		}
		catch (Exception e)
		{
			ExceptionManage.dispose(e,this.getClass());
		}finally
		{
			UiUtil.closeService_MB(pwservice);
		}
		return PWs;
	}
	
	private PortInst getPortType()
	{
		if(currentAlarmInfo.getObjectType().toString().equals("PORT"))
		{
			//找出其PORT 
			PortService_MB portService = null;
			try
			{
				portService = (PortService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PORT);
				portlist = portService.selectPortbySiteandPortname(currentAlarmInfo.getSiteId(), currentAlarmInfo.getObjectName());
				if(portlist != null && portlist.size()>0)
				{
					return portlist.get(0);
				}
			}
			catch (Exception e)
			{
				ExceptionManage.dispose(e,this.getClass());
			} finally{
				UiUtil.closeService_MB(portService);
			}
		}
		
		return null;
	}
	
	private void convertCESData(List<CesInfo> Ceslist)
	{
		for(CesInfo ces : Ceslist)
		{
			ces.putClientProperty("id", ces.getId());
			ces.putClientProperty("objectName", ces.getName());
			ces.putClientProperty("objectType","CES");
			ces.putClientProperty("activateState", "");
			ces.putClientProperty("consistency", "");
			ces.putClientProperty("createTime", ces.getCreateTime());
			ces.putClientProperty("activateTime", ces.getCreateTime());
			ces.putClientProperty("creater", ces.getCreateUser());
		}
	}
	
	private void convertSegmentData(List<Segment> segments)
	{
		for(Segment sg : segments)
		{
			sg.putClientProperty("id", sg.getId());
			sg.putClientProperty("objectName", sg.getNAME());
			sg.putClientProperty("objectType","Segment");
			sg.putClientProperty("activateState", "");
			sg.putClientProperty("consistency", "");
			sg.putClientProperty("createTime", sg.getCREATTIME());
			sg.putClientProperty("activateTime", sg.getCREATTIME());
			sg.putClientProperty("creater", sg.getCREATUSER());
		}
	}
	
	private void convertTunnelData(List<Tunnel> tunnels)
	{
		for(Tunnel tunnel : tunnels)
		{
			int status = tunnel.getTunnelStatus();
			String active = "";
			if(status == 1)
			{
				active = "activated";
			}
			else
			{
				active = "unactivated";
			}
			tunnel.putClientProperty("id", tunnel.getTunnelId());
			tunnel.putClientProperty("objectName", tunnel.getTunnelName());
			tunnel.putClientProperty("objectType","Tunnel");
			tunnel.putClientProperty("activateState", active);
			tunnel.putClientProperty("consistency", "");
			tunnel.putClientProperty("createTime", tunnel.getCreateTime());
			tunnel.putClientProperty("activateTime", tunnel.getCreateTime());
			tunnel.putClientProperty("creater", tunnel.getCreateUser());
		}
	}
	
	private void convertPwData(List<PwInfo> PWs)
	{
		for(PwInfo pw : PWs)
		{
			int status = pw.getPwStatus();
			String active = "";
			if(status == 1)
			{
				active = "activated";
			}
			else
			{
				active = "unactivated";
			}
			pw.putClientProperty("id", pw.getPwId());
			pw.putClientProperty("objectName", pw.getPwName());
			pw.putClientProperty("objectType","Pw");
			pw.putClientProperty("activateState", active);
			pw.putClientProperty("consistency", "");
			pw.putClientProperty("createTime", pw.getCreateTime());
			pw.putClientProperty("activateTime", pw.getCreateTime());
			pw.putClientProperty("creater", pw.getCreateUser());
		}
	}
	
	private void convertELANData(List<ElanInfo> ElanInfos)
	{
		for(ElanInfo elan : ElanInfos)
		{
			int status = elan.getActiveStatus();
			String active = "";
			if(status == 1)
			{
				active = "activated";
			}
			else
			{
				active = "unactivated";
			}
			elan.putClientProperty("id", elan.getId());
			elan.putClientProperty("objectName", elan.getName());
			elan.putClientProperty("objectType","Elan");
			elan.putClientProperty("activateState", active);
			elan.putClientProperty("consistency", "");
			elan.putClientProperty("createTime", elan.getCreateTime());
			elan.putClientProperty("activateTime", elan.getCreateTime());
			elan.putClientProperty("creater", elan.getCreateUser());
		}
	}
	
	private void convertElineData(List<ElineInfo> Elines)
	{
		for(ElineInfo eline : Elines)
		{
			int status = eline.getActiveStatus();
			String active = "";
			if(status == 1)
			{
				active = "activated";
			}
			else
			{
				active = "unactivated";
			}
			eline.putClientProperty("id", eline.getId());
			eline.putClientProperty("objectName", eline.getName());
			eline.putClientProperty("objectType","Eline");
			eline.putClientProperty("activateState", active);
			eline.putClientProperty("consistency", "");
			eline.putClientProperty("createTime", eline.getCreateTime());
			eline.putClientProperty("activateTime",  eline.getCreateTime());
			eline.putClientProperty("creater", eline.getCreateUser());
		}
	}
	
	private void convertEtreeData(List<EtreeInfo> Etrees)
	{
		for(EtreeInfo etree : Etrees)
		{
			int status = etree.getActiveStatus();
			String active = "";
			if(status == 1)
			{
				active = "activated";
			}
			else
			{
				active = "unactivated";
			}
			etree.putClientProperty("id", etree.getId());
			etree.putClientProperty("objectName", etree.getName());
			etree.putClientProperty("objectType","Etree");
			etree.putClientProperty("activateState", active);
			etree.putClientProperty("consistency", "");
			etree.putClientProperty("createTime", etree.getCreateTime());
			etree.putClientProperty("activateTime",  etree.getCreateTime());
			etree.putClientProperty("creater", etree.getCreateUser());
		}
	}
	
	public void setCurrentAlarmInfo(CurrentAlarmInfo currentAlarmInfo)
	{
		this.currentAlarmInfo = currentAlarmInfo;
	}
	@SuppressWarnings("unchecked")
	public ViewDataTable getView()
	{
		return mainView;
	}
	@SuppressWarnings("unchecked")
	public void setView(ViewDataTable view)
	{
		this.mainView = view;
	}
	@SuppressWarnings("unchecked")
	public void setRelatedView(ViewDataTable relatedView)
	{
		this.relatedView = relatedView;
	}
}
