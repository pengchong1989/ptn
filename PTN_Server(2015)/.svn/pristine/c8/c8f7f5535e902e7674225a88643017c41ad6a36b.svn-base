package com.nms.db.bean.report;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.MsPwInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EServiceType;
import com.nms.model.ptn.path.eth.EtreeInfoService_MB;
import com.nms.model.ptn.path.pw.MsPwInfoService_MB;
import com.nms.model.report.StaticsticsService_MB;
import com.nms.model.util.Services;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.frame.ViewDataObj;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DateUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTab;
/**
 * 统计业务列表
 * @author sy
 *
 */
public class SSProfess extends ViewDataObj {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;//业务名称
	private int direction;//方向     ：  1 双向，2单向
	private String rate;//速率   :  1 ces 2 以太网
	private String aSiteName;//A端网元ID
	private String zSiteName;//Z端网元ID
	private String aPortName;//A端 AC名称
	private String zPortName;
	private int serviceType;//0 ces    1 eline   2 elan  3  etree
	private int alarmCount;//0 没有告警       >0 有告警
	private int activeStatus;//激活状态
	private String activeTime;//激活時間
	private String createTime;//创建时间
	private String clientName;//客户名称
	private String cos;
	private String cir;
	private String cbs;
	private String eir;
	private String ebs;
	private String pir;
	private int aACId;
	private int zACId;
	private int portId;
	@Override
	public void putObjectProperty() {
		
		try {
					
			//this.putClientProperty("id",count);
			this.putClientProperty("name",this.getName());
			this.putClientProperty("direction",this.getDirection()==1?ResourceUtil.srcStr(StringKeysTab.TAB_TWOWAY):ResourceUtil.srcStr(StringKeysTab.TAB_ONEWAY));
			this.putClientProperty("rate",this.getRate());	
			if(this.getServiceType() == 186){	
				this.putClientProperty("serviceType",ResourceUtil.srcStr(StringKeysLbl.LBL_11_PROTECT));	
			}else if(this.getServiceType() == 185){
				this.putClientProperty("serviceType",ResourceUtil.srcStr(StringKeysObj.STRING_GENERAL));	
			}else if(this.getServiceType() == 5 && this.getId()==0){
				this.putClientProperty("serviceType",ResourceUtil.srcStr(StringKeysObj.STRING_GENERAL));	
			}else if(this.getServiceType() == 5 && this.getId()>0){
				this.putClientProperty("serviceType",ResourceUtil.srcStr(StringKeysObj.STRING_MULTISTAGE));	
			}
			else{
				this.putClientProperty("serviceType",EServiceType.from(this.getServiceType()).toString());
			}		
			if(this.getServiceType()==3 || this.getServiceType()==40 ||this.getServiceType()==2){
				//etree a网元为根节点，z网元为叶子节点
				this.setaSiteName(getAsiteName(this.getServiceType(),this.name).getaSiteName());
				this.setzSiteName(getAsiteName(this.getServiceType(),this.name).getzSiteName());
				this.setaPortName(getAsiteName(this.getServiceType(),this.name).getaPortName());
				this.setzPortName(getAsiteName(this.getServiceType(),this.name).getzPortName());
				this.putClientProperty("aSiteId",getAsiteName(this.getServiceType(),this.name).getaSiteName());
				this.putClientProperty("zSiteId",getAsiteName(this.getServiceType(),this.name).getzSiteName());
				this.putClientProperty("aPortName",getAsiteName(this.getServiceType(),this.name).getaPortName());
				this.putClientProperty("zPortName",getAsiteName(this.getServiceType(),this.name).getzPortName());
			}else if(this.getServiceType() == 5 && this.getId()>0){
				this.setaPortName(getPwAsiteName(this.name,this.id,this.aSiteName,this.zSiteName).getaPortName());
				this.setzPortName(getPwAsiteName(this.name,this.id,this.aSiteName,this.zSiteName).getzPortName());
				this.putClientProperty("zSiteId",this.getzSiteName());
				this.putClientProperty("aSiteId",this.getaSiteName());				
				this.putClientProperty("aPortName",getPwAsiteName(this.name,this.id,this.aSiteName,this.zSiteName).getaPortName());
				this.putClientProperty("zPortName",getPwAsiteName(this.name,this.id,this.aSiteName,this.zSiteName).getzPortName());
				
			}else{
			this.putClientProperty("zSiteId",this.getzSiteName());
			this.putClientProperty("aSiteId",this.getaSiteName());
			this.putClientProperty("zPortName",this.getzPortName());
			this.putClientProperty("aPortName",this.getaPortName());
			}
			this.putClientProperty("alarmCount",this.getAlarmCount()==0?ResourceUtil.srcStr(StringKeysTab.TAB_UNEXIT_ALARM):ResourceUtil.srcStr(StringKeysTab.TAB_EXIT_ALARM));
			this.putClientProperty("activeStatus",EActiveStatus.forms(this.getActiveStatus()).toString());
			this.putClientProperty("activeTime",EActiveStatus.forms(this.getActiveStatus()).getValue() ==1 ?DateUtil.strDate(this.getCreateTime(),DateUtil.FULLTIME):"");
			this.putClientProperty("createTime",DateUtil.strDate(this.getCreateTime(),DateUtil.FULLTIME));
			this.putClientProperty("clientName",this.getClientName());					
					
        
		
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	
	}
	//得到多段PW的端口号
	private SSProfess getPwAsiteName(String name,int id,String aSiteName,String zSiteName){
		SSProfess ss=null;
		String aportName ="";
		String zportName ="";
		StaticsticsService_MB ssService = null;
		MsPwInfoService_MB msPwInfoService=null;
		    	 try {
					 ssService = (StaticsticsService_MB) ConstantUtil.serviceFactory.newService_MB(Services.STATISTICS);
					 msPwInfoService = (MsPwInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.MSPWSERVICE);	
			    	 MsPwInfo ms= new MsPwInfo();
			    	 ms.setPwId(id);
			    	 List<MsPwInfo> msPwInfo=null;
			    	 msPwInfo=msPwInfoService.select(ms);
			    	 int atunnelId=msPwInfo.get(0).getFrontTunnelId();			    
			    	 int ztunnelId=msPwInfo.get(msPwInfo.size()-1).getBackTunnelId();
			    	 aportName=ssService.selectPwPortName(atunnelId,aSiteName).getPortName();
			    	 zportName=ssService.selectPwPortName(ztunnelId,zSiteName).getPortName();
			    	 ss = new SSProfess();
			    	 ss.setaPortName(aportName);
			    	 ss.setzPortName(zportName);
				} catch (Exception e) {
					 ExceptionManage.dispose(e,this.getClass());
				}finally{
					UiUtil.closeService_MB(ssService);
					UiUtil.closeService_MB(msPwInfoService);
				}	    			   			   
		  return ss;
	}
	
	//获取网元名称
	private SSProfess getAsiteName(int serviceType,String name){
		String aSiteName="";
		String zSiteName="";
		EtreeInfoService_MB etreeService = null;		
		List<EtreeInfo> etreeInfoList = null;		
		int siteId=0;
		List<Integer> asiteIdList = null;
		List<Integer> zsiteIdList = null;
		List<Integer> aacInList = null;
		List<Integer> zacInList = null;
		List<Integer> zacInList1 = null;
		List<Integer> aacInList1 = null;
		WhImplUtil wh = new WhImplUtil();
		SSProfess ss=null;
		String zportName = "";
		String aportName="";		
		    try {
			     etreeService = (EtreeInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.EtreeInfo);
			     etreeInfoList=etreeService.selectAll(serviceType,name);
			     if(serviceType == 3){
			    	 aacInList = new ArrayList<Integer>();
		    		 zacInList = new ArrayList<Integer>();
		    		 ss = new SSProfess();
			    	 if(etreeInfoList!=null && etreeInfoList.size()!=0){				    		
			    		 asiteIdList = new ArrayList<Integer>();
			    		 zsiteIdList = new ArrayList<Integer>();
			    		 
			    		 //asiteName
			    		 siteId=etreeInfoList.get(0).getRootSite();
			    		 asiteIdList.add(siteId);
			    		 aSiteName=wh.getNeName(asiteIdList);
			    		 ss.setaSiteName(aSiteName);
			    		 
			    		 //zsitename			    		 
			    		 for(int i=0;i<etreeInfoList.size();i++){			    			 
			    			 zsiteIdList.add(etreeInfoList.get(i).getBranchSite());					    			 
			    			 String  aMostAcId=etreeInfoList.get(i).getAmostAcId();
							 String  zMostAcId=etreeInfoList.get(i).getZmostAcId();
							 
							 if(null !=aMostAcId && !aMostAcId.equals("")){								 
							     String []macs= aMostAcId.split("\\, ");							     
							     for(int j=0;j<macs.length;j++){							    	 
							         aacInList.add(Integer.parseInt(macs[j]));
				           	         }
							     }
							 
							 if(null !=zMostAcId && !zMostAcId.equals("")){
								 String []macs1= zMostAcId.split("\\, ");
								 for(int j=0;j<macs1.length;j++){
								     zacInList.add(Integer.parseInt(macs1[j]));
					           	     }
								} 
							 
						      }
			    		 
			    	     zSiteName = wh.getNeName(zsiteIdList);
	    		         ss.setzSiteName(zSiteName);
			    		 //aportName
			    	     if(etreeInfoList.get(0).getRootSite() == etreeInfoList.get(0).getaSiteId()){
			    		    aportName=aSiteName+":"+getacPortNames(aacInList);
			    		    ss.setaPortName(aportName);
			    		    
			    		    for(int i=0;i<zsiteIdList.size();i++){
			    		    	zacInList1 = new ArrayList<Integer>();
			    		    	zacInList = new ArrayList<Integer>();
			    		    	String  zMostAcIds=etreeService.selectAcIds(serviceType,name,zsiteIdList.get(i)).get(0).getZmostAcId();
			    		    	
			    		    	 if(null !=zMostAcIds && !zMostAcIds.equals("")){
									 String []macss= zMostAcIds.split("\\, ");
									 
									 for(int j=0;j<macss.length;j++){
									     zacInList.add(Integer.parseInt(macss[j]));
						           	     }										 
									 zacInList1.add(zsiteIdList.get(i));									
									 zportName+= wh.getNeName(zacInList1)+":"+getacPortNames(zacInList)+" ";
									}          	    
							    }
			    		        ss.setzPortName(zportName);
			    		     }
			    		     
			    	  
			           if(etreeInfoList.get(0).getRootSite() != etreeInfoList.get(0).getaSiteId()){
			    	    	 aportName=aSiteName+":"+getacPortNames(zacInList);
			    	    	 ss.setaPortName(aportName);			    	    	 
				    		 for(int i=0;i<asiteIdList.size();i++){
				    			 zacInList1 = new ArrayList<Integer>();
			    		    	 zacInList = new ArrayList<Integer>();
			    		    	 String  aMostAcIds=etreeService.selectAcIds(serviceType,name,asiteIdList.get(i)).get(0).getZmostAcId();			    		    	 
			    		    	 if(null !=aMostAcIds && !aMostAcIds.equals("")){
			    		    		 String []macss= aMostAcIds.split("\\, "); 			    		    		 
			    		    		 for(int j=0;j<macss.length;j++){
									     zacInList.add(Integer.parseInt(macss[j]));
						           	     }	
			    		    		 zacInList1.add(asiteIdList.get(i));
			    		    		 zportName+= wh.getNeName(zacInList1)+":"+getacPortNames(aacInList)+" ";
			    		    	 }
			    		    	 
				    		 }	
				    		 ss.setzPortName(zportName);
				    		
			             }
			         } 
			     }
			     if(serviceType == 40){
			    	 aacInList = new ArrayList<Integer>();
		    		 zacInList = new ArrayList<Integer>();
		    		 ss = new SSProfess();
			    	 if(etreeInfoList!=null && etreeInfoList.size()!=0){				    		
			    		 asiteIdList = new ArrayList<Integer>();
			    		 zsiteIdList = new ArrayList<Integer>();
			    		 //asiteName
			    		 siteId=etreeInfoList.get(0).getRootSite();
			    		 asiteIdList.add(siteId);
			    		 aSiteName=wh.getNeName(asiteIdList);
			    		 ss.setaSiteName(aSiteName);
			    		 aacInList.add(etreeInfoList.get(0).getaAcId());
			    		 //zsitename			    		 
			    		 for(int i=0;i<etreeInfoList.size();i++){			    			 
			    			 zsiteIdList.add(etreeInfoList.get(i).getBranchMainSite());		
			    			 zsiteIdList.add(etreeInfoList.get(i).getBranchProtectSite());
			    		 }
			    		 Set<Integer> set = new HashSet<Integer>(zsiteIdList);
			    		 zsiteIdList.clear();
			    		 zsiteIdList.addAll(set);	
			    		 if(zsiteIdList!=null && zsiteIdList.size()!=0){
			    			 for(int i=0;i<zsiteIdList.size();i++){
			    				 if(zsiteIdList.get(i)==0){
			    					 zsiteIdList.remove(zsiteIdList.get(i));
			    				 }
			    		     }
			    		 }
			    	 }
			    	     zSiteName = wh.getNeName(zsiteIdList);
	    		         ss.setzSiteName(zSiteName);
			    		 //aportName	    		         
			    		    aportName=aSiteName+":"+getacPortNames(aacInList);
			    		    ss.setaPortName(aportName);
			    		    for(int i=0;i<zsiteIdList.size();i++){
			    		    	  zacInList1 = new ArrayList<Integer>();
			    		    	  zacInList = new ArrayList<Integer>();
			    		    	  EtreeInfo etree=etreeService.selectAcIds(serviceType,name,zsiteIdList.get(i)).get(0);
			    		    	  int branchM=etree.getBranchMainSite();
			    		    	  int branchP=etree.getBranchProtectSite();
			    		    	
			    		    	  if(branchM!=0){
			    		    		  zacInList.add(etree.getzAcId());
			    		    		  zacInList1.add(zsiteIdList.get(i));									
									  zportName+= wh.getNeName(zacInList1)+":"+getacPortNames(zacInList)+" ";
			    		    	    }
			    		    	
			    		    	  if(branchP!=0){
			    		    		  zacInList.add(etree.getzAcId());
			    		    		  zacInList1.add(zsiteIdList.get(i));									
									  zportName+= wh.getNeName(zacInList1)+":"+getacPortNames(zacInList)+" ";
			    		    	    }
			    		    	           	    
							    }
			    		     ss.setzPortName(zportName);
			    	 }
			    		    
			    	    
			     
			     if(serviceType == 2){			   		    		
		    		 ss = new SSProfess();
			    	 if(etreeInfoList!=null && etreeInfoList.size()!=0){				    					    			
			    		 //asiteName
			    		 List<Integer> asiteIdList1 = new ArrayList<Integer>();
			    		 siteId=etreeInfoList.get(0).getaSiteId();
			    		 asiteIdList1.add(siteId);
			    		 aSiteName=wh.getNeName(asiteIdList1);
			    		 ss.setaSiteName(aSiteName);
			    		
			    		 //aportName			    		 
			    		 String  aMostAcIds=etreeInfoList.get(0).getAmostAcId();
	    		    	 if(null !=aMostAcIds && !aMostAcIds.equals("")){
	    		    		 aacInList = new ArrayList<Integer>();
	    		    		 aacInList1 = new ArrayList<Integer>();
	    		    		 String []macss= aMostAcIds.split("\\, "); 
	    		    		 for(int j=0;j<macss.length;j++){
							     aacInList1.add(Integer.parseInt(macss[j]));							    
				           	     }	
	    		    		 aacInList.add(siteId);
	    		    		 aportName+= wh.getNeName(aacInList)+":"+getacPortNames(aacInList1)+" ";
	    		    	 }			    		
		    	    	 ss.setaPortName(aportName);
		    	    	 //zportName
		    	    	 zacInList = new ArrayList<Integer>();
		    	    	 for(int i=0;i<etreeInfoList.size();i++){
		    	    		 if(etreeInfoList.get(i).getzSiteId()!=etreeInfoList.get(0).getaSiteId()){		    	   		  
		    	    		    zacInList.add(etreeInfoList.get(i).getzSiteId());
		    	    		 }
		    	    		 if(etreeInfoList.get(i).getaSiteId()!=etreeInfoList.get(0).getaSiteId()){
			    	    		    zacInList.add(etreeInfoList.get(i).getaSiteId());
			    	    		 }
		    	    	 }
		    	    		 HashSet<Integer> set= new HashSet<Integer>(zacInList);
		    	    		 zacInList.clear();
		    	    		 zacInList.addAll(set); 
		    	    		 for(int j=0;j<zacInList.size();j++){
		    	    			 aacInList1 = new ArrayList<Integer>();	
		    	    			 zacInList1 = new ArrayList<Integer>();	  
		    	    			 EtreeInfo etree=etreeService.selectAcIds(serviceType,name,zacInList.get(j)).get(0);
		    	    			  if(etree.getaSiteId()==zacInList.get(j)){
		 		    	    		 String []mac2=etree.getAmostAcId().split("\\, ");		    	    			
		 		    	    		 for(int k=0;k<mac2.length;k++){
		 		    	    			aacInList1.add(Integer.parseInt(mac2[k]));
		 		    	    		 }
		 		    	    		 zacInList1.add(zacInList.get(j));	  
		 		    	    		 zportName+= wh.getNeName(zacInList1)+":"+getacPortNames(aacInList1)+" ";  
		    	    			  }
		    	    			  if(etree.getzSiteId()==zacInList.get(j)){
			 		    	    		 String []mac2=etree.getZmostAcId().split("\\, ");		    	    			
			 		    	    		 for(int k=0;k<mac2.length;k++){
			 		    	    			aacInList1.add(Integer.parseInt(mac2[k]));
			 		    	    		 }
			 		    	    		 zacInList1.add(zacInList.get(j));	  
			 		    	    		 zportName+= wh.getNeName(zacInList1)+":"+getacPortNames(aacInList1)+" ";   
			    	    			  }
		    	    		 
		    	    		    
		    	    	 }		    	    		 		    	    	 
                         //zsiteName			    	   
			    		 zSiteName=wh.getNeName(zacInList);
			    		 ss.setzSiteName(zSiteName);
		    	    	 ss.setzPortName(zportName);
					    						         			    		 
			    	 }
			    	 
			     }
			   
				    		  			    	
		    } catch (Exception e) {
			     ExceptionManage.dispose(e,this.getClass());
		    } finally {
			     UiUtil.closeService_MB(etreeService);
			     asiteIdList = null;
				 zsiteIdList = null;
				 aacInList = null;
				 zacInList = null;
				 zacInList1 = null;
				 aacInList1 = null;
				 etreeInfoList =null;
		    }
		
		
		return ss;
		
		
	} 
		
	/**
	 * 通过网元ID来获取网元的IP名称
	 * @param siteList
	 * @return
	 */
	public String getacPortNames(List<Integer> acIdList){
		StaticsticsService_MB ssService = null;	
		String str = new String();
		List<PortInst> port=new ArrayList<PortInst>();
		try {
			if(acIdList != null && acIdList.size()>0){	
				ssService = (StaticsticsService_MB) ConstantUtil.serviceFactory.newService_MB(Services.STATISTICS);				
				HashSet<Integer> hashSet= new HashSet<Integer>(acIdList);
				acIdList.clear();
				acIdList.addAll(hashSet);

				for (int i = 0; i < acIdList.size(); i++) {					
					if(acIdList.get(i) > 0 ){
						PortInst portInst = ssService.selectPortName(acIdList.get(i));
						port.add(portInst);											
					}
				}
				for(int j=0;j<port.size();j++){
					if( j+1<port.size() &&port.get(j).getPortName().equals(port.get(j+1).getPortName())){
						port.remove(j);
					}
				}
					if(port != null && port.size()!=0){						
						for(int j=0;j<port.size();j++){
							if(j == port.size()-1){
							   str +=port.get(j).getPortName();
								}else{							
								   str +=port.get(j).getPortName()+"、";
																
						        }
					        }
					   }
				}
						
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}finally{
			UiUtil.closeService_MB(ssService);
		}
		//如果最后一顿号结尾，去掉
		if(str.endsWith("、"))
		{
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	public int getDirection() {
		return direction;
	}



	public void setDirection(int direction) {
		this.direction = direction;
	}



	public String getRate() {
		return rate;
	}



	public void setRate(String rate) {
		this.rate = rate;
	}


	public String getaSiteName() {
		return aSiteName;
	}

	public void setaSiteName(String aSiteName) {
		this.aSiteName = aSiteName;
	}

	public String getzSiteName() {
		return zSiteName;
	}

	public void setzSiteName(String zSiteName) {
		this.zSiteName = zSiteName;
	}

	public String getaPortName() {
		return aPortName;
	}



	public void setaPortName(String aPortName) {
		this.aPortName = aPortName;
	}



	public String getzPortName() {
		return zPortName;
	}



	public void setzPortName(String zPortName) {
		this.zPortName = zPortName;
	}



	public int getServiceType() {
		return serviceType;
	}



	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}



	public int getAlarmCount() {
		return alarmCount;
	}



	public void setAlarmCount(int alarmCount) {
		this.alarmCount = alarmCount;
	}



	public int getActiveStatus() {
		return activeStatus;
	}



	public void setActiveStatus(int activeStatus) {
		this.activeStatus = activeStatus;
	}



	public String getCreateTime() {
		return createTime;
	}



	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}



	public String getClientName() {
		return clientName;
	}



	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getCos() {
		return cos;
	}

	public void setCos(String cos) {
		this.cos = cos;
	}

	public String getCir() {
		return cir;
	}

	public void setCir(String cir) {
		this.cir = cir;
	}

	public String getCbs() {
		return cbs;
	}

	public void setCbs(String cbs) {
		this.cbs = cbs;
	}

	public String getEir() {
		return eir;
	}

	public void setEir(String eir) {
		this.eir = eir;
	}

	public String getEbs() {
		return ebs;
	}

	public void setEbs(String ebs) {
		this.ebs = ebs;
	}

	public String getPir() {
		return pir;
	}

	public void setPir(String pir) {
		this.pir = pir;
	}

	public String getActiveTime() {
		return activeTime;
	}

	public void setActiveTime(String activeTime) {
		this.activeTime = activeTime;
	}

	public int getzACId() {
		return zACId;
	}

	public void setzACId(int zACId) {
		this.zACId = zACId;
	}

	public int getaACId() {
		return aACId;
	}

	public void setaACId(int aACId) {
		this.aACId = aACId;
	}
	public int getPortId() {
		return portId;
	}

	public void setPortId(int portId) {
		this.portId = portId;
	}
}
