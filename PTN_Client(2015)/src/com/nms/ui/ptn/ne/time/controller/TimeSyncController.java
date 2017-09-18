package com.nms.ui.ptn.ne.time.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.nms.db.bean.ptn.clock.PtpPortinfo;
import com.nms.db.bean.ptn.clock.TimeSyncInfo;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.TimeSyncService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.service.impl.util.SiteUtil;
import com.nms.service.impl.util.WhImplUtil;
import com.nms.ui.frame.AbstractController;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.CheckingUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.ne.time.view.AddPtpPortDialog;
import com.nms.ui.ptn.ne.time.view.TimeSyncPanel;


public class TimeSyncController extends AbstractController{
	
	private TimeSyncPanel view;
	private TimeSyncInfo timesyncinfo = null;	
	public TimeSyncController(TimeSyncPanel timesyncview)
	{
		this.view = timesyncview;
		addListention();
		try {
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	

	public void init() {
		
		TimeSyncService_MB timeSyncService = null;
		List<TimeSyncInfo> timeSyncList = null;
		
		try {
			timeSyncService = (TimeSyncService_MB) ConstantUtil.serviceFactory.newService_MB(Services.TIME_SYNC_MANGE);
			timeSyncList = timeSyncService.select(ConstantUtil.siteId);		
			if(timeSyncList != null && timeSyncList.size()>0){
				timesyncinfo = timeSyncList.get(0);	
				this.view.initData(timesyncinfo.getPtpPortlist());
				this.view.updateUI();
			}else{
				timesyncinfo = new TimeSyncInfo();
				this.view.initData(timesyncinfo.getPtpPortlist());
				this.view.updateUI();
			}
			this.view.timesynview.refresh(timesyncinfo);
							
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(timeSyncService);
		}
	}
	
	
	
	
	
	private void addListention() {
			this.view.getBtnSavePort().addActionListener(new MyActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					String domainNumbe =view.timesynview.getJtfDomain().getText().trim();
				    if(!CheckingUtil.checking(domainNumbe, CheckingUtil.NUMBER_NUM)){	
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_DOMAINERROR1));
						return;
					}else if(Integer.parseInt(domainNumbe)<0 || Integer.parseInt(domainNumbe)>255){
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_DOMAINERROR));
						return;	
					}
					
					String announceOver =view.timesynview.getCmbAnnOver().getText().trim();
					if(!CheckingUtil.checking(announceOver, CheckingUtil.NUMBER_NUM)){	
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_ANNOUNCEERROR1));
						return;
					}else if(Integer.parseInt(announceOver)<0 || Integer.parseInt(announceOver)>255){
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_ANNOUNCEERROR));
						return;	
					}
					
					String delayOver =view.timesynview.getCmbdelayOver().getText().trim();
				    if(!CheckingUtil.checking(delayOver, CheckingUtil.NUMBER_NUM)){		
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_DELAYOVERERROR1));
						return;
					}else if(Integer.parseInt(delayOver)<0 || Integer.parseInt(delayOver)>255){
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_DELAYOVERERROR));
						return;	
					}	
					
					String synCicle=view.timesynview.getCmbSynCicle().getText().trim();
                    if(!CheckingUtil.checking(synCicle, CheckingUtil.NUMBER_NUM)){		
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_SYNCICLEERROR1));
						return;
					}else if(Integer.parseInt(synCicle)<0 || Integer.parseInt(synCicle)>255){
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_SYNCICLEERROR));
						return;	
					}	
					
					String noteCicle=view.timesynview.getCmbNoteCicle().getText().trim();
				    if(!CheckingUtil.checking(noteCicle, CheckingUtil.NUMBER_NUM)){		
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_NOTECICLEERROR1));
						return;	
					}else if(Integer.parseInt(noteCicle)<0 || Integer.parseInt(noteCicle)>255){
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_NOTECICLEERROR));
						return;	
					}	
					
					String outDelay= view.timesynview.getTxtoutDelay().getText().trim();					
					if(!CheckingUtil.checking(outDelay, CheckingUtil.NUMBER_NUM)){	
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_OUTDELAYERROR1));
						return;	
				    }else if(Integer.parseInt(outDelay)<0 || Integer.parseInt(outDelay)>100000){
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_OUTDELAYERROR));
						return;	
					}	
			
					
					String inDelay=view.timesynview.getTxtinDelay().getText().trim();					
					if(!CheckingUtil.checking(inDelay, CheckingUtil.NUMBER_NUM)){		
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_INDELAYERROR));
						return;	
					}else if(Integer.parseInt(inDelay)<0 ||Integer.parseInt(inDelay)>100000){
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_INDELAYERROR));
						return;	
					}	
					
					String srcclockpri1 =view.timesynview.getsrcclockpri1().getText().trim();				    
				    if(!CheckingUtil.checking(srcclockpri1, CheckingUtil.NUMBER_NUM)){	
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_SRCCLOCKPRI1ERROR1));
						return;	
					 }else if(Integer.parseInt(srcclockpri1)<0 || Integer.parseInt(srcclockpri1)>255){
							DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_SRCCLOCKPRI1ERROR));
							return;	
					}	
					
					String srcclockpri2 = view.timesynview.getTxtsrcclockpri2().getText().trim();				
					if(!CheckingUtil.checking(srcclockpri2, CheckingUtil.NUMBER_NUM)){
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_SRCCLOCKPRI2ERROR1));
						return;	
					}else if(Integer.parseInt(srcclockpri2)<0 || Integer.parseInt(srcclockpri2)>255){
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_SRCCLOCKPRI2ERROR));
						return;	
					}
					
					String srcclockLevel =view.timesynview.getTxtsrcclockLevel().getText().trim();
					if(!CheckingUtil.checking(srcclockLevel, CheckingUtil.NUMBER_NUM)){
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_SRCCLOCKLEVELERROR1));
						return;	
					}else if(Integer.parseInt(srcclockLevel)<0 || Integer.parseInt(srcclockLevel)>255){
						DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_SRCCLOCKLEVELERROR));
						return;	
					}	
					TimeSyncInfo info = new TimeSyncInfo();
					info = TimeSyncController.this.view.timesynview.pageSetValue(info,view);
					String a=view.timesynview.getTxtsrcclockId().getText().trim();
               
					if(!CheckingUtil.checking(a, CheckingUtil.SIXTEEN)){
						 DialogBoxUtil.errorDialog(view, ResourceUtil.srcStr(StringKeysTip.TIP_SRCCLOCKIDERROR));
					     return;	
					}	
					String srcclockId1="0";
					String srcclockId2="0";
					String srcclockId3="0";
					if(a.length()<=6){
					      srcclockId1=a;					     					      
						  info.setSrcclockId1(srcclockId1);
						  info.setSrcclockId2(srcclockId2);
						  info.setSrcclockId3(srcclockId3);
					}			
					if(a.length()>6 && a.length()<=12){
						 srcclockId1=a.substring(a.length(), a.length()-6);	
						 srcclockId2=a.substring(a.length()-7, 0);	
						 info.setSrcclockId1(srcclockId1);
						 info.setSrcclockId2(srcclockId2);
						 info.setSrcclockId3(srcclockId3);
						}
					if(a.length()>12){
						 srcclockId1=a.substring(a.length()-6, a.length());	
						 srcclockId2=a.substring(a.length()-12, a.length()-6);	
						 srcclockId3=a.substring(0, a.length()-12);
						 info.setSrcclockId1(srcclockId1);
						 info.setSrcclockId2(srcclockId2);
						 info.setSrcclockId3(srcclockId3);	
						}
				
					TimeSyncService_MB timesyncservice = null;
					String result="";
					try {
						timesyncservice = (TimeSyncService_MB) ConstantUtil.serviceFactory.newService_MB(Services.TIME_SYNC_MANGE);
						DispatchUtil timesyncdispatch = new DispatchUtil(RmiKeys.RMI_TIMESYNC);
						List<TimeSyncInfo> timesyns = timesyncservice.select(ConstantUtil.siteId);
						if(timesyns != null && timesyns.size()>0){						
							result = timesyncdispatch.excutionUpdates(timesyns,info);
							info.setSrcclocktype(Integer.parseInt(Integer.toHexString(info.getSrcclocktype())));
							timesyns.get(0).setSrcclocktype(Integer.parseInt(Integer.toHexString(timesyns.get(0).getSrcclocktype())));
							info.setSrcclockaccuray(Integer.parseInt(Integer.toHexString(info.getSrcclockaccuray())));
							timesyns.get(0).setSrcclockaccuray(Integer.parseInt(Integer.toHexString(timesyns.get(0).getSrcclockaccuray())));
							this.insertOpeLog(EOperationLogType.UPDATETIMESYN.getValue(),result,timesyns.get(0),info);
						}else{
							result = timesyncdispatch.excuteInsert(info);
							info.setSrcclocktype(Integer.parseInt(Integer.toHexString(info.getSrcclocktype())));
							info.setSrcclockaccuray(Integer.parseInt(Integer.toHexString(info.getSrcclockaccuray())));
							this.insertOpeLog(EOperationLogType.INSERTTIMESYN.getValue(), result, null,info);
						}
						DialogBoxUtil.succeedDialog(view, result);
					}
				    catch (Exception e1) {
						ExceptionManage.dispose(e1,this.getClass());
					} finally {
						UiUtil.closeService_MB(timesyncservice);
					}
				}

				@Override
				public boolean checking() {
					// TODO Auto-generated method stub
					return true;
				}
				
			    private void insertOpeLog(int operationType, String result, TimeSyncInfo oldTime, TimeSyncInfo newTime){
					SiteService_MB service = null;
					try {
						service = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
						String siteName=service.getSiteName(newTime.getSiteId());
						newTime.setSiteName(siteName);
						if(oldTime!=null){
							oldTime.setSiteName(siteName);
						}
						AddOperateLog.insertOperLog(view.getBtnSavePort(), operationType, result, oldTime, newTime, newTime.getSiteId(),ResourceUtil.srcStr(StringKeysTab.TAB_TIMEMANAGE),"timeSync");
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					} finally {
						UiUtil.closeService_MB(service);
					}
				}
			});
			
			
			
			this.view.getBtnSynPort().addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						DispatchUtil dispatch = new DispatchUtil(RmiKeys.RMI_TIMESYNC);
						String result = dispatch.synchro(ConstantUtil.siteId);
						DialogBoxUtil.succeedDialog(view, result);
						this.insertOpeLog(EOperationLogType.SYSTIMESYN.getValue(),result,null,null);
						init();
					} catch (Exception e) {
						ExceptionManage.dispose(e, this.getClass());
					}
				}
				
			private void insertOpeLog(int operationType, String result, TimeSyncInfo oldTime, TimeSyncInfo newTime){						
				AddOperateLog.insertOperLog(view.getBtnSynPort(), operationType, result, oldTime, newTime, ConstantUtil.siteId,ResourceUtil.srcStr(StringKeysTab.TAB_TIMEMANAGE),"timeSync");
						
			}
			
			});
			
	}


	/**
	 * 新建
	 */
	
	public void openCreateDialog(){		
		TimeSyncInfo info=new TimeSyncInfo();
		new AddPtpPortDialog(info,view,null);
	}

	
	/**
	 * 修改
	 */
	
	
    public void openUpdateDialog(){		
    	
    	if (this.view.getAllSelect().size() != 1) {
			DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			return;
		} else {
			//if(timesyncinfo !=null){
				new AddPtpPortDialog(timesyncinfo,view,view.getAllSelect().get(0));
			//}
		}
		
	}
    
    /**
	 * 删除
	 */
	
	
    public void delete(){		
    	List<Integer> siteIds = null;
    	if (this.view.getAllSelect().size() == 0) {
			DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			return;
		} else {
			//判断是否为在线脱管网元
			SiteUtil siteUtil = new SiteUtil();
			try {
				if(1==siteUtil.SiteTypeOnlineUtil(ConstantUtil.siteId)){
					WhImplUtil wu = new WhImplUtil();
					siteIds = new ArrayList<Integer>();
					siteIds.add(ConstantUtil.siteId);
					String str=wu.getNeNames(siteIds);
					DialogBoxUtil.errorDialog(this.view, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_DELETEONLINE)+""+str+ResourceUtil.srcStr(StringKeysTip.TIP_ONLINENOT_DELETEONLINE));
					return ;  		    		
					}
				List<PtpPortinfo> p = view.getTable().getAllElement();
		    	for(int j=0;j<this.view.getAllSelect().size();j++){
		    		  for(int i=0;i<p.size();i++){
		    	          if(p.get(i)==this.view.getAllSelect().get(j)){	    		
		    		          p.remove(i);
		    		
		    	 }
		    	}		    
		    }	
		    Collections.reverse(p); 	
			this.view.initData(p);
			this.view.updateUI();
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());				
			}finally{
				siteIds = null;
			}
		    

		}
		
	}


	@Override
	public void refresh() throws Exception {
		// TODO Auto-generated method stub
		
	}

    
    
}
