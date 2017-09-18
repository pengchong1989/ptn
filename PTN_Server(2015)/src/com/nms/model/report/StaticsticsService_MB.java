package com.nms.model.report;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.path.eth.EtreeInfo;
import com.nms.db.bean.ptn.path.pw.MsPwInfo;
import com.nms.db.bean.ptn.path.tunnel.Tunnel;
import com.nms.db.bean.ptn.port.AcPortInfo;
import com.nms.db.bean.report.SSAlarm;
import com.nms.db.bean.report.SSCard;
import com.nms.db.bean.report.SSLabel;
import com.nms.db.bean.report.SSPath;
import com.nms.db.bean.report.SSPort;
import com.nms.db.bean.report.SSProfess;
import com.nms.db.dao.ptn.path.eth.EtreeInfoMapper;
import com.nms.db.dao.ptn.path.pw.MsPwInfoMapper;
import com.nms.db.dao.ptn.path.tunnel.TunnelMapper;
import com.nms.db.dao.ptn.port.AcPortInfoMapper;
import com.nms.db.dao.report.StaticsticsMapper;
import com.nms.model.util.ObjectService_Mybatis;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.ExceptionManage;


public class StaticsticsService_MB extends ObjectService_Mybatis {

	  private StaticsticsMapper mapper = null;
		
		public void setPtnuser(String ptnuser) {
			super.ptnuser = ptnuser;
		}

		public void setSqlSession(SqlSession sqlSession) {
			super.sqlSession = sqlSession;
		}
		
		public StaticsticsMapper getMapper() {
			return mapper;
		}

		public void setMapper(StaticsticsMapper mapper) {
			this.mapper = mapper;
		}


	//单独使用于单板的统计
	public List<SSCard> SSCardToCardSelect() throws Exception{
		List<SSCard> sscardlist = new ArrayList<SSCard>();
		sscardlist =this.mapper.queryCard();
		return sscardlist;
	}
	
	/**
	 * 告警统计
	 * 	
	 * @return
	 * @throws Exception
	 */
	public List<SSAlarm> SSAlarmSelect()throws Exception{		
		List<SSAlarm> ssAlarmList=null;
		try{
			ssAlarmList = new ArrayList<SSAlarm>();
			ssAlarmList=this.mapper.queryByCountAlarmLevel();
			if(ssAlarmList !=null && ssAlarmList.size()>0){
				for(int i=0 ;i<ssAlarmList.size();i++){
					int alarmCount=ssAlarmList.get(i).getInstancy()+ssAlarmList.get(i).getMostly()+ssAlarmList.get(i).getUnknow()+ssAlarmList.get(i).getClew()+ssAlarmList.get(i).getSubordination();
					ssAlarmList.get(i).setAlarmCount(alarmCount);
					
				}
			}
		}catch (Exception e) {
			throw e;
		}
		return ssAlarmList;
	}
		
	/**
	 * 统计业务列表
	 * 	
	 * @return
	 * @throws Exception
	 */
	public List<SSProfess>  SSBusiness()throws Exception{	
		List<SSProfess> tunnelList = null;	
		List<SSProfess> pwList = null;
		List<SSProfess> mspwList = null;
		List<SSProfess> elineAcesList = null;
		List<SSProfess> etreeList = null;
		try{
			//普通tunnel
			tunnelList =new ArrayList<SSProfess>();
			tunnelList=this.mapper.queryBusinessOfTunnelList();
			if(tunnelList!=null && tunnelList.size()>0){
				for(int i=0;i<tunnelList.size();i++){
					tunnelList.get(i).setRate("隧道");
					 if(tunnelList.get(i).getServiceType()!=185){
						 tunnelList.get(i).setServiceType(186);
					}
				}
			}
			//普通pw
			pwList =new ArrayList<SSProfess>();
			pwList=this.mapper.queryBusinessOfPwList();
			if(pwList!=null && pwList.size()>0){
				for(int i=0;i<pwList.size();i++){
					pwList.get(i).setRate("伪线");					
					pwList.get(i).setServiceType(5);	
					tunnelList.add(pwList.get(i));
				}
			}
			//多段pw
			mspwList =new ArrayList<SSProfess>();
			mspwList=this.mapper.queryBusinessOfMsPwList();
			if(mspwList!=null && mspwList.size()>0){
				for(int i=0;i<mspwList.size();i++){
					mspwList.get(i).setRate("伪线");					
					mspwList.get(i).setServiceType(5);	
					tunnelList.add(mspwList.get(i));
				}
			}
			//eline、ces
			elineAcesList =new ArrayList<SSProfess>();
			elineAcesList=this.mapper.queryBusinessOfElineAndCesList();
			if(elineAcesList!=null && elineAcesList.size()>0){
				for(int i=0;i<elineAcesList.size();i++){
					if(elineAcesList.get(i).getServiceType()==0){
						elineAcesList.get(i).setRate("CES");				
					  }else{
					    elineAcesList.get(i).setRate("以太网");		
					  }	
					tunnelList.add(elineAcesList.get(i));
				}
			}
			//etree/elan/dual
			etreeList=this.mapper.queryBusinessOfEtreeList();
			if(etreeList!=null && etreeList.size()>0){
				for(int i=0;i<etreeList.size();i++){					
					etreeList.get(i).setRate("以太网");		
					tunnelList.add(etreeList.get(i));
				}
			}
			
		}catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}finally{
		  pwList = null;
		  mspwList = null;
		  elineAcesList = null;
		  etreeList = null;
		}
		return tunnelList;
	
	}

	public List<SSProfess> SSBusinessByPortId(String type, List<Integer> portIdList) {
		List<SSProfess> ssList = new ArrayList<SSProfess>();
		List<SSProfess> ssList1 = new ArrayList<SSProfess>();				
		AcPortInfoMapper  acInfoMapper = null;
		EtreeInfoMapper etreeInfoMapper = null;
		List<Integer> aAcIds =null;
		MsPwInfoMapper msPwInfoMapper = null;
        TunnelMapper tunnelMapper = null;
		List<EtreeInfo> etreeInfoList = null;
		List<AcPortInfo> acPortInfo=null;	
		List<SSProfess> typeList = null;
		List<SSProfess> typeMsPwList = null;
		
		try{	
			    for(int i=0;i<portIdList.size();i++){
			    	typeList = new ArrayList<SSProfess>();
			    	if(type.equals("ETH")){
			    		typeList=this.mapper.SSBusinessEthByPortId(portIdList.get(i));	
			    	}else{
			    		typeList=this.mapper.SSBusinessByPortId(type,portIdList.get(i));	
			    	}
			    			    	
			    	if(type.equals("PW")){
			    		typeMsPwList = new ArrayList<SSProfess>();
			    		typeMsPwList= this.mapper.SSBusinessByPortId("MSPW",portIdList.get(i));
			    		for(int j=0;j<typeList.size();j++){
			    			typeList.get(j).setRate("伪线");					
			    			typeList.get(j).setServiceType(5);	
			    			ssList.add(typeList.get(j));
						}
			    		for(int j=0;j<typeMsPwList.size();j++){
			    			typeMsPwList.get(j).setRate("伪线");					
			    			typeMsPwList.get(j).setServiceType(5);	
			    			ssList.add(typeMsPwList.get(j));
						}
			    	}
			    	
			    	if(type.equals("TUNNEL")){
			    		for(int j=0;j<typeList.size();j++){
			    			typeList.get(j).setRate("隧道");
							 if(typeList.get(j).getServiceType()!=185){
								 typeList.get(j).setServiceType(186);
							}
							 ssList.add(typeList.get(j));
						}
			    	}
			    	
			    	if(type.equals("CES")){
			    		for(int j=0;j<typeList.size();j++){			    			
			    			if(typeList.get(j).getServiceType()==0){
			    				typeList.get(j).setRate("CES");				
							  }else{
								  typeList.get(j).setRate("以太网");		
							  }	
			    			ssList.add(typeList.get(j));
						}			    		
			    	}
			    	if(type.equals("ETH")){
			    		for(int j=0;j<typeList.size();j++){		
			    		    typeList.get(j).setRate("以太网");
			    		    ssList.add(typeList.get(j));
			    		}
			    	}
			    	
			    }	
			    for(SSProfess ss : ssList){
			    	//eline
			    	if(ss.getServiceType()==1){	
			    		aAcIds =new ArrayList<Integer>();
			    		aAcIds.add(ss.getaACId());
			    		aAcIds.add(ss.getzACId());
			    		acInfoMapper = this.sqlSession.getMapper(AcPortInfoMapper.class);
			    		acPortInfo = new ArrayList<AcPortInfo>();
			    		acPortInfo=acInfoMapper.queryByAcIdCondition(aAcIds);
			    		for(int i=0;i<acPortInfo.size();i++){
			    		 if(portIdList.contains(acPortInfo.get(i).getPortId())){
			    			 ssList1.add(ss);
			    			 }
			             }
			    		//dual
			    	 }else if(ss.getServiceType()==40){
			    		 etreeInfoMapper = this.sqlSession.getMapper(EtreeInfoMapper.class);
			    		 acInfoMapper = this.sqlSession.getMapper(AcPortInfoMapper.class);
			    		 etreeInfoList= new ArrayList<EtreeInfo>();
			    		 etreeInfoList=etreeInfoMapper.queryAllEtree(40,ss.getName());
			    		 aAcIds =new ArrayList<Integer>();
			    		 for(int i=0;i<etreeInfoList.size();i++){			    			
			    			 aAcIds.add(etreeInfoList.get(i).getaAcId());
			    			 aAcIds.add(etreeInfoList.get(i).getzAcId());			    			 
			    		 }
			    		 acPortInfo = new ArrayList<AcPortInfo>();
			    		 acPortInfo=acInfoMapper.queryByAcIdCondition(aAcIds);
			    		 for(int i=0;i<acPortInfo.size();i++){
					    	if(portIdList.contains(acPortInfo.get(i).getPortId())){
					    		ssList1.add(ss);
					    		}
					          }
			    	 //elan
			         }else if(ss.getServiceType()==2){
			        	 etreeInfoMapper = this.sqlSession.getMapper(EtreeInfoMapper.class);
			    		 acInfoMapper = this.sqlSession.getMapper(AcPortInfoMapper.class);
			    		 etreeInfoList= new ArrayList<EtreeInfo>();
			    	     etreeInfoList=etreeInfoMapper.queryAllEtree(2,ss.getName());
			    	     aAcIds =new ArrayList<Integer>();
		    		     for(int i=0;i<etreeInfoList.size();i++){
		    			    String []macss=etreeInfoList.get(i).getAmostAcId().split("\\, "); 
    	    			    for(int j=0;j<macss.length;j++){
    	    				   aAcIds.add(Integer.parseInt(macss[j]));
			           	    }
    	    			    String []macs1=etreeInfoList.get(i).getZmostAcId().split("\\, ");
    	    			    for(int j=0;j<macs1.length;j++){
    	    				   aAcIds.add(Integer.parseInt(macs1[j]));
			           	     }		    					    			 
		    		     }
		    		     acPortInfo = new ArrayList<AcPortInfo>();
		    		     acPortInfo=acInfoMapper.queryByAcIdCondition(aAcIds);
		    		     for(int i=0;i<acPortInfo.size();i++){
				    	    if(portIdList.contains(acPortInfo.get(i).getPortId())){
				    		   ssList1.add(ss);
				    		}
				          }	
		    		     //etree
			           }else if(ss.getServiceType()==3){
			        	  etreeInfoMapper = this.sqlSession.getMapper(EtreeInfoMapper.class);
				    	  acInfoMapper = this.sqlSession.getMapper(AcPortInfoMapper.class);
				    	  etreeInfoList= new ArrayList<EtreeInfo>();
			    	      etreeInfoList=etreeInfoMapper.queryAllEtree(3,ss.getName());
			    	      aAcIds =new ArrayList<Integer>();
		    		      for(int i=0;i<etreeInfoList.size();i++){
		    			     String []macss=etreeInfoList.get(i).getAmostAcId().split("\\, "); 
   	    			         for(int j=0;j<macss.length;j++){
   	    				        aAcIds.add(Integer.parseInt(macss[j]));
			           	     }
   	    			         String []macs1=etreeInfoList.get(i).getZmostAcId().split("\\, ");
   	    			         for(int j=0;j<macs1.length;j++){
   	    				        aAcIds.add(Integer.parseInt(macs1[j]));
			           	     }		    					    			 
		    		       }
		    		      acPortInfo = new ArrayList<AcPortInfo>();
		    		      acPortInfo=acInfoMapper.queryByAcIdCondition(aAcIds);
		    		      for(int i=0;i<acPortInfo.size();i++){
				    	     if(portIdList.contains(acPortInfo.get(i).getPortId())){
				    		   ssList1.add(ss);
				    		 }
				          }	
		    		      //多段Pw
			           }else if(ss.getServiceType()==5 && ss.getId()>0){
			        	   msPwInfoMapper = this.sqlSession.getMapper(MsPwInfoMapper.class);
                           tunnelMapper = this.sqlSession.getMapper(TunnelMapper.class);
						   MsPwInfo ms= new MsPwInfo();
						   ms.setPwId(ss.getId());
						   List<MsPwInfo> msPwInfo=null;
						   List<Integer> tunnelId= new ArrayList<Integer>();
						   msPwInfo = new ArrayList<MsPwInfo>();
						   msPwInfo=msPwInfoMapper.queryByCondition(ms);
						   for(int i=0;i<msPwInfo.size();i++){
							   tunnelId.add(msPwInfo.get(i).getBackTunnelId());
							   tunnelId.add(msPwInfo.get(i).getFrontTunnelId());
						   }
						   int atunnelId=msPwInfo.get(0).getFrontTunnelId();			    
						   int ztunnelId=msPwInfo.get(msPwInfo.size()-1).getBackTunnelId();
						   int  aportId=this.mapper.queryPwPortName(atunnelId,ss.getaSiteName()).getPortId();
						   int  zportId=this.mapper.queryPwPortName(ztunnelId,ss.getzSiteName()).getPortId();						    	 
						   if(portIdList.contains(aportId)||portIdList.contains(zportId)){
								ssList1.add(ss);
							}
						   //中间网元类型的
						   for(int j=tunnelId.size()-1;j>=0;j--){
							   if(tunnelId.get(j)==atunnelId ||tunnelId.get(j)==ztunnelId){
								   tunnelId.remove(j);
							   }
						   }
						   HashSet<Integer> hashSet= new HashSet<Integer>(tunnelId);
						   tunnelId.clear();
						   tunnelId.addAll(hashSet);
						   if(tunnelId !=null && tunnelId.size()>0){
						   for(int j=0;j<tunnelId.size();j++){
							   Tunnel tunnel = new Tunnel();
							   tunnel.setTunnelId(tunnelId.get(j));
							   int aport=tunnelMapper.queryByCondition(tunnel).get(0).getaPortId();
							   int zport=tunnelMapper.queryByCondition(tunnel).get(0).getzPortId();
							   if(portIdList.contains(aport)||portIdList.contains(zport)){
									ssList1.add(ss);
								}
						   }
						   }
						   //tunnel和普通PW和ces
			             } else{	    			    	          
			            	 ssList1.add(ss);			    	         			    	          
			             }
			    }
			    //去掉重复的内容
			    for(int i=0;i<ssList1.size();i++){
			    	for(int j=ssList1.size()-1;j>i;j--){
			    		if(ssList1.get(j).getName().equals(ssList1.get(i).getName())){
			    			ssList1.remove(j);
			    		}
			    	}
			    	
			    }					
			    if(!this.sqlSession.getConnection().getAutoCommit()){
			    	this.sqlSession.getConnection().commit();
				}
			} catch (Exception e) {
				try {
					this.sqlSession.getConnection().rollback();
				} catch (SQLException e1) {
					ExceptionManage.dispose(e1,this.getClass());
				}
				ExceptionManage.dispose(e,this.getClass());
			}finally{
				try {
					this.sqlSession.getConnection().setAutoCommit(true);
				} catch (SQLException e) {
					ExceptionManage.dispose(e,this.getClass());
				}
			}		
		return ssList1;
	}
	
	/**
	 * 根据tunnelId和siteName查询端口名称
	 * 
	 * @param siteId
	 *            网元ID
	 * @return 网元对象
	 * @throws Exception
	 */
	public PortInst selectPwPortName(int tunnelId,String siteName) throws Exception {
		PortInst portInst = null;		
		try {
			portInst = new PortInst();
			portInst = this.mapper.queryPwPortName(tunnelId,siteName);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} 
		return portInst;
	}
	
	/**
	 * 根据acId查询端口名称
	 * 
	 * @param siteId
	 *            网元ID
	 * @return 网元对象
	 * @throws Exception
	 */
	public PortInst selectPortName(int acId) throws Exception {
		PortInst portInst = null;
		try {
			portInst = new PortInst();
			portInst = this.mapper.queryPortName(acId);

		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} 
		return portInst;
	}

	
	public List<SSLabel> SSLabelSelect() throws Exception{
		List<SSLabel> ssLabelList = new ArrayList<SSLabel>();
		List<SSLabel> canUsedList = new ArrayList<SSLabel>();
		canUsedList= this.mapper.queryCanUsedLabel();
		List<SSLabel> labelCountList = new ArrayList<SSLabel>();
		labelCountList =this.mapper.queryCountLabel();
		int allLabelCount = ConstantUtil.LABEL_MAXVALUE - 15;
		for (SSLabel countLabel : labelCountList) {
			int siteId2 = countLabel.getSiteId();
			String type2 = countLabel.getLabelType();
			int allCount = Integer.parseInt(countLabel.getLspCount());//数据库总数量
			for (SSLabel canUsedLabel : canUsedList) {
				int siteId = canUsedLabel.getSiteId();
				String type = canUsedLabel.getLabelType();
				int canUsedCount = Integer.parseInt(canUsedLabel.getLspCanUsed());//数据库剩余可用数量
				if(siteId == siteId2 && type.equals(type2)){
					int usedCount = allCount - canUsedCount;//已用数量
					SSLabel ss = new SSLabel();
					ss.setSiteName(canUsedLabel.getSiteName());
					ss.setPortName("ALL");
					ss.setLabelType("ALL");
					ss.setLspCount(allLabelCount+"");
					ss.setLspUsed(usedCount+"");
					ss.setLspCanUsed(allLabelCount-usedCount+"");
					ss.setSiteId(siteId);
					ssLabelList.add(ss);
				}
			}
		}
		return ssLabelList;
	}
	
	public List<SSPort> SSPortSelect() throws Exception{
		List<SSPort> ssportList = new ArrayList<SSPort>();
		ssportList=this.mapper.querySSPort();
		return ssportList;
	}
	
	/**
	 * 端到端路径数量统计
	 */
	public List<SSPath> SSPathSelect() throws Exception{
		List <SSPath> sspath = new ArrayList<SSPath>();
		sspath = this.mapper.queryPathCount();
		return sspath;
	}
	/**
	 * 单网元路径数量统计
	 */
	public List<SSPath> SSNePathSelect() throws Exception{
		List <SSPath> sspath = new ArrayList<SSPath>();
		sspath = this.mapper.queryPathCount_site();
		return sspath;
	}
}
