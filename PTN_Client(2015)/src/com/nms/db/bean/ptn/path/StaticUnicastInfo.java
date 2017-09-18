package com.nms.db.bean.ptn.path;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.nms.db.bean.ptn.path.eth.ElanInfo;
import com.nms.db.bean.ptn.path.pw.PwInfo;
import com.nms.db.bean.ptn.path.pw.PwNniInfo;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.model.ptn.path.SingleSpreadService_MB;
import com.nms.model.ptn.path.eth.ElanInfoService_MB;
import com.nms.model.ptn.path.pw.PwInfoService_MB;
import com.nms.model.ptn.path.pw.PwNniInfoService_MB;
import com.nms.model.ptn.port.AcPortInfoService_MB;
import com.nms.model.util.Services;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.UiUtil;

public class StaticUnicastInfo extends ViewDataObj{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 85090669136248551L;
	private int id;
	private int suId;
	private int vplsVs;
	private int portChoice;
	private String vplsNameLog;//log日志用到
	private String acPortNameLog;//log日志用到
	private String macAddress;
	private int siteId;
		
	public String getVplsNameLog() {
		return vplsNameLog;
	}

	public void setVplsNameLog(String vplsNameLog) {
		this.vplsNameLog = vplsNameLog;
	}

	public String getAcPortNameLog() {
		return acPortNameLog;
	}

	public void setAcPortNameLog(String acPortNameLog) {
		this.acPortNameLog = acPortNameLog;
	}

	public int getSiteId() {
		return siteId;
	}

	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSuId() {
		return suId;
	}

	public void setSuId(int suId) {
		this.suId = suId;
	}

	public int getVplsVs() {
		return vplsVs;
	}
	
	private String getPortChoiceName(){
		String port=null;
		AcPortInfoService_MB acService = null;
		List<ElanInfo> elanInfoList = null;
		ElanInfoService_MB elanInfoService = null;
		Set<Integer> acIdSet = null;
		List<Integer> pwIdList = null;
		List<PwInfo> pwIdList1 = null;
		List<Integer> acList = null;
		List<AcPortInfo> acInfoList = null;
		PwNniInfoService_MB pwNniBufferService = null;
		PwInfoService_MB pwInfoService = null;
		AcPortInfo acInfo = null;
		List<PwNniInfo>	pwNNIInfoList = null;
		UiUtil uiUtil = null;	
		try {
			acService = (AcPortInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.AcInfo);			
			elanInfoService = (ElanInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ElanInfo);		
			pwNniBufferService =(PwNniInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PwNniBuffer);
			pwInfoService = (PwInfoService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PwInfo);	
			String VplsName=getVplsName();
			ElanInfo elanInfo = new ElanInfo();
			elanInfo.setName(VplsName);			
			elanInfoList = elanInfoService.selectElan(elanInfo);		
			uiUtil = new UiUtil();
			acIdSet =  new HashSet<Integer>();
			pwIdList = new ArrayList<Integer>();
			for(int i=0;i<elanInfoList.size();i++){
				if(elanInfoList.get(i).getaSiteId() == siteId){
					acIdSet.addAll(uiUtil.getAcIdSets(elanInfoList.get(i).getAmostAcId()));
					pwIdList.add(elanInfoList.get(i).getPwId());
				}else if(elanInfoList.get(i).getzSiteId() == siteId){
					acIdSet.addAll(uiUtil.getAcIdSets(elanInfoList.get(i).getZmostAcId()));
					pwIdList.add(elanInfoList.get(i).getPwId());
				}
									
			}
			acList= new ArrayList<Integer>();
			for (Integer acId : acIdSet) {
				 acList.add(acId);					  
			}
			acInfoList = acService.select(acList);
			PwNniInfo pwNNIInfo= null;
			pwIdList1 = new ArrayList<PwInfo>();
			pwNNIInfoList = new ArrayList<PwNniInfo>();
		    for (Integer pwId : pwIdList) {
				 pwNNIInfo = new PwNniInfo();
				 pwNNIInfo.setSiteId(siteId);
				 pwNNIInfo.setPwId(pwId);
				 pwNNIInfoList.addAll(pwNniBufferService.select(pwNNIInfo));
				 PwInfo pw= new PwInfo();
				 pw.setPwId(pwId);
				 pwIdList1.add(pwInfoService.selectBypwid_notjoin(pw));
			}
		    for(int i=0;i<pwIdList1.size();i++){
		    	for(int j=0;j<pwNNIInfoList.size();j++){
		    		if(pwIdList1.get(i).getPwId()==pwNNIInfoList.get(j).getPwId()){
		    			acInfo = new AcPortInfo();
		    			acInfo.setName(pwIdList1.get(i).getPwName());
		    			acInfo.setLanId(pwNNIInfoList.get(j).getLanId());
		    			acInfoList.add(acInfo);
		    			
		    		}
		    	}
		    }
		    for(AcPortInfo info : acInfoList){	
		    	if(info.getLanId()==getPortChoice()){
		    		port=info.getName();
		    		break;
		    	}
			  
			}
				  
		}catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(pwNniBufferService);
			UiUtil.closeService_MB(acService);
			UiUtil.closeService_MB(pwInfoService);
			UiUtil.closeService_MB(elanInfoService);
			elanInfoList = null;
			acIdSet = null;
			pwIdList = null;
			pwIdList1 = null;
			acList = null;
			acInfoList = null;
			acInfo = null;
			pwNNIInfoList = null;
			uiUtil = null;
		}  
		return port;
		
	}
	
	
	private String getVplsName()
	{
		int vsId = getVplsVs();
		int siteId = getSiteId();
		String serviceName="";
		SingleSpreadService_MB service= null;
		try {
			service = (SingleSpreadService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SINGELSPREAD);
			serviceName = service.selectNameByXcId(vsId, siteId);
			
		}catch(Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
		}
		return serviceName;
	}

	public void setVplsVs(int vplsVs) {
		this.vplsVs = vplsVs;
	}

	public int getPortChoice() {
		return portChoice;
	}

	public void setPortChoice(int portChoice) {
		this.portChoice = portChoice;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void putObjectProperty() {
		try {
			getClientProperties().put("id", getId());
			getClientProperties().put("suId", getSuId());
			getClientProperties().put("vplsVs", getVplsName());
			getClientProperties().put("portChoice", getPortChoiceName());
			getClientProperties().put("macAddress", getMacAddress());
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}

}
