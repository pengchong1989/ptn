package com.nms.ui.ptn.clock.controller;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.nms.db.bean.equipment.port.PortInst;
import com.nms.db.bean.ptn.CommonBean;
import com.nms.db.bean.ptn.SiteRoate;
import com.nms.db.bean.ptn.clock.FrequencyInfo;
import com.nms.db.enums.EClockQLType;
import com.nms.db.enums.EOperationLogType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.ptn.SiteRoateService_MB;
import com.nms.model.ptn.clock.ClockFrequService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.util.ClockUtil;
import com.nms.ui.ptn.clock.view.ArrangeClockDialog;
import com.nms.ui.ptn.clock.view.ClockRorateDialog;
import com.nms.ui.ptn.clock.view.FrequencyPanel;
import com.nms.ui.ptn.clock.view.QLClockDialog;

public class FrequencyPanelController {

	private FrequencyPanel view;
	private FrequencyInfo info = null;
    private SiteRoate siteRoate=null;
	
	public FrequencyPanelController(FrequencyPanel view) {
		this.view = view;
		addListention();
		try {
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		}
	}

	/**
	 * 初始化界面值
	 */
	private void init() throws Exception {
		ClockFrequService_MB service = null;
		SiteRoateService_MB  siteRoateService=null;
		List<FrequencyInfo> infoList = null;
		try {
			service = (ClockFrequService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ClockFrequ);
			siteRoateService= (SiteRoateService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITEROATE);
			siteRoate=new SiteRoate();
			siteRoate.setSiteId(ConstantUtil.siteId);
			siteRoate.setType("clock");
			List<SiteRoate> sroate=siteRoateService.querBySiteRoate(siteRoate);	
			if(!sroate.isEmpty()){
				siteRoate=sroate.get(0);
			}else{
				siteRoate=null;
			}
			infoList = service.query(ConstantUtil.siteId);
			if (infoList != null && infoList.size() > 0) {
				info = infoList.get(0);
			}
			this.view.refresh(info);
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
		} finally {
			UiUtil.closeService_MB(service);
			UiUtil.closeService_MB(siteRoateService);
		}
	}

	private void addListention() {
		// 设置按钮事件
		this.view.getConfimButton().addActionListener(new MyActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ClockFrequService_MB service = null;
				SiteService_MB siteService=null;
				List<FrequencyInfo> infoList = null;
				FrequencyInfo oldInfo=null;
				ClockUtil clockUtil=new ClockUtil();
				List<CommonBean> oldClockPriList=new ArrayList<CommonBean>();
				List<CommonBean> newClockPriList=new ArrayList<CommonBean>();
				List<CommonBean> oldouterClockList=new ArrayList<CommonBean>();
				List<CommonBean> newouterClockList=new ArrayList<CommonBean>();
				List<CommonBean> oldwaitClockList=new ArrayList<CommonBean>();
				List<CommonBean> newwaitClockList=new ArrayList<CommonBean>();
				Map<String, String> clockPRIMapItem =null;
				Map<String, String> outSelectMapItems =null;
				List<CommonBean> oldQLlist=new ArrayList<CommonBean>();
				List<CommonBean> newQLlist=new ArrayList<CommonBean>();
				List<CommonBean> oldOutQLlist=new ArrayList<CommonBean>();
				List<CommonBean> newOutQLlist=new ArrayList<CommonBean>();
				try {
					service = (ClockFrequService_MB) ConstantUtil.serviceFactory.newService_MB(Services.ClockFrequ);
					String result = null;
					infoList = service.query(ConstantUtil.siteId);
					if (infoList != null && infoList.size() > 0) {
						oldInfo = infoList.get(0);	
					}
					FrequencyInfo info = FrequencyPanelController.this.view.get();
					
					clockPRIMapItem = clockUtil.getClockPRIList(ConstantUtil.siteId);
										
					//比较优先级排序
					//新的
					String []clockPri= info.getClockPRIList().split("\\/");
				    for(int j = 0; j< clockPri.length; j++){				    	
				    	String PortName=getPortName(clockPRIMapItem,clockPri[j]);
				    	if(PortName!=null){
				    		CommonBean commonBean = new CommonBean();
				    		commonBean.setClockPortName(PortName);
					    	newClockPriList.add(commonBean);
				    	}				    	
				    }
				    info.setClockList(newClockPriList);
					if(oldInfo!=null){
						//优先级排序
						//旧的
					    String []OldclockPri= oldInfo.getClockPRIList().split("\\/");
					    for(int j = 0; j< OldclockPri.length; j++){				    	
					    	String PortName=getPortName(clockPRIMapItem,OldclockPri[j]);
					    	if(PortName!=null){
					    		CommonBean commonBean = new CommonBean();
					    		commonBean.setClockPortName(PortName);
					    		oldClockPriList.add(commonBean);
					    	}				    	
					    }
					    oldInfo.setClockList(oldClockPriList);
					}
				   //输出时钟选择
					outSelectMapItems = clockUtil.getOutSelectList(ConstantUtil.siteId);
					//新的
					String []OutSelectList= info.getOutSelectList().split("\\/");
				    for(int j = 0; j< OutSelectList.length; j++){				    	
				    	String PortName=getPortName(outSelectMapItems,OutSelectList[j]);
				    	if(PortName!=null){
				    		CommonBean commonBean = new CommonBean();
				    		commonBean.setClockPortName(PortName);
				    		newouterClockList.add(commonBean);
				    	}				    	
				    }
				    info.setOuterClockList(newouterClockList);
					if(oldInfo!=null){
						//旧的
					    String []OldOutSelectList= oldInfo.getOutSelectList().split("\\/");
					    for(int j = 0; j< OldOutSelectList.length; j++){				    	
					    	String PortName=getPortName(outSelectMapItems,OldOutSelectList[j]);
					    	if(PortName!=null){
					    		CommonBean commonBean = new CommonBean();
					    		commonBean.setClockPortName(PortName);
					    		oldouterClockList.add(commonBean);
					    	}				    	
					    }
					    oldInfo.setOuterClockList(oldouterClockList);
					}
					//等待恢复时间开关								
					siteService=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
					String siteType = siteService.getSiteType(ConstantUtil.siteId);		
					List<PortInst> port =clockUtil.getFrequencyPorts(ConstantUtil.siteId);
					int count = 0;
					if("703A".equals(siteType) || "703B".equals(siteType)
							|| "703B2".equals(siteType) || "703-2A".equals(siteType)){
						count = 6;
					}else if("703-2".equals(siteType) || "703-6".equals(siteType)){
						count = 4;
					}else if("703-1".equals(siteType) || "703-4".equals(siteType) || "703-5".equals(siteType)){
						count = 3;
					}else if("703-3".equals(siteType) || "703-7".equals(siteType)){
						count = 2;
					}else{
						//需要加上外时钟
						count = port.size()+1;
					}
					//新的
					for (int i = 1; i < count; i++) {
						int number = i;
						if (info.getWaitResumeTime().charAt(number) == '1') {
							CommonBean co=new CommonBean();
							co.setClockPortName(selectProt(number,port));
							newwaitClockList.add(co);
						} 
					}
					if(info.getWaitResumeTime().charAt(0) == '1'){
						CommonBean co=new CommonBean();
						co.setClockPortName(ResourceUtil.srcStr(StringKeysObj.OUTER_CLOCK));
						newwaitClockList.add(co);
					}
					info.setWaitResumeTimeList(newwaitClockList);
					//旧的
					if(oldInfo!=null){
						for (int i = 0; i < count-1; i++) {
							int number = i;
							if (oldInfo.getWaitResumeTime().charAt(number) == '1') {
								CommonBean co=new CommonBean();
								co.setClockPortName(selectProt(number,port));								
								oldwaitClockList.add(co);
							} 
						}
						if(oldInfo.getWaitResumeTime().charAt(count-1) == '1'){
							CommonBean co=new CommonBean();
							co.setClockPortName(ResourceUtil.srcStr(StringKeysObj.OUTER_CLOCK));
							oldwaitClockList.add(co);
						}
						oldInfo.setWaitResumeTimeList(oldwaitClockList);
					}
//					//输入源QL
					//新的
					String []qlIn= info.getClockInQLValueList().split("\\/");
					CommonBean common=new CommonBean();
					common.setClockPortName(ResourceUtil.srcStr(StringKeysObj.OUTER_CLOCK));
					common.setQlClock(EClockQLType.forms(Integer.parseInt(qlIn[0])));
					newQLlist.add(common);
				    for(int j = 1; j< qlIn.length; j++){				    	
				    	CommonBean commonBean=getQl(port,qlIn[j],j);
				    	if(commonBean!=null){
				    		newQLlist.add(commonBean);
				    	}
				    }
				    info.setQLlist(newQLlist);
					//旧的
				    if(oldInfo!=null){
				    	
				    	String []oldqlIn= oldInfo.getClockInQLValueList().split("\\/");
						CommonBean com=new CommonBean();
						com.setClockPortName(ResourceUtil.srcStr(StringKeysObj.OUTER_CLOCK));
						com.setQlClock(EClockQLType.forms(Integer.parseInt(qlIn[0])));
						oldQLlist.add(com);
					    for(int j = 1; j< oldqlIn.length; j++){				    	
					    	CommonBean commonBean=getQl(port,oldqlIn[j],j);
					    	if(commonBean!=null){
					    		oldQLlist.add(commonBean);
					    	}
					    }
					    oldInfo.setQLlist(oldQLlist);
					}
//					//输出源QL
					//新的
					String []qlOut= info.getClockOutQLValueList().split("\\/");
					CommonBean cons=new CommonBean();
					cons.setClockPortName(ResourceUtil.srcStr(StringKeysObj.OUTER_CLOCK));
					cons.setQlClock(EClockQLType.forms(Integer.parseInt(qlOut[0])));
					newOutQLlist.add(cons);
				    for(int j = 1; j< qlOut.length; j++){				    	
				    	CommonBean commonBean=getQl(port,qlOut[j],j);
				    	if(commonBean!=null){
				    		newOutQLlist.add(commonBean);
				    	}
				    }				   
				    info.setOuterQlList(newOutQLlist);
					//旧的
				    if(oldInfo!=null){
				    	
				    	String []oldqlOut= oldInfo.getClockOutQLValueList().split("\\/");
						CommonBean coms=new CommonBean();
						coms.setClockPortName(ResourceUtil.srcStr(StringKeysObj.OUTER_CLOCK));
						coms.setQlClock(EClockQLType.forms(Integer.parseInt(oldqlOut[0])));
						oldOutQLlist.add(coms);
					    for(int j = 1; j< oldqlOut.length; j++){				    	
					    	CommonBean commonBean=getQl(port,oldqlOut[j],j);
					    	if(commonBean!=null){
					    		oldOutQLlist.add(commonBean);
					    	}
					    }
					    oldInfo.setOuterQlList(oldOutQLlist);
					}
					
									
					result = configFrequency(info);
					DialogBoxUtil.succeedDialog(FrequencyPanelController.this.view, result);
					this.insertOpeLog(EOperationLogType.CLOCKSET.getValue(), result, oldInfo, info);			
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}finally{
					UiUtil.closeService_MB(siteService);
					UiUtil.closeService_MB(service);
				}
			}
		
		  private String selectProt(int number,List<PortInst> port ){
				String portName=null;
				for (int i=0;i<port.size();i++) {
					if(number==port.get(i).getNumber()){
						portName=port.get(i).getPortName();
					}
				}			
				return portName;
			}
			
			
		private String getPortName(Map<String, String> clockPRIMapItem,String number){
			String portName=null;
			for (String key : clockPRIMapItem.keySet()) {
				if(number.equals(key)){
					portName=clockPRIMapItem.get(key);
				}
			}		
			return portName;
		}
		
		private CommonBean getQl(List<PortInst> portList,String ql,int number){
			CommonBean co = null;
			for (int i=0;i<portList.size();i++) {
				if(number==portList.get(i).getNumber()){
					co = new CommonBean();
					co.setClockPortName(portList.get(i).getPortName());
					co.setQlClock(EClockQLType.forms(Integer.parseInt(ql)));
				}
			}
			
			return co;
		}
		
			private void insertOpeLog(int operationType, String result, FrequencyInfo oldClock, FrequencyInfo newClock){
				SiteService_MB service = null;
				try {
					service = (SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
					String siteName=service.getSiteName(newClock.getSiteId());
					newClock.setSiteName(siteName);
					if(oldClock!=null){
						oldClock.setSiteName(siteName);
					}
				    AddOperateLog.insertOperLog(view.getConfimButton(), operationType, result, oldClock, newClock, newClock.getSiteId(),ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCKFREQUENCY),"FrequencyInfo");		
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				} finally {
					UiUtil.closeService_MB(service);
				}
			}
			
			@Override
			public boolean checking() {
				return true;
			}
		});
		
		this.view.getSyncButton().addActionListener(new MyActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				synchro();
			}

			@Override
			public boolean checking() {
				return true;
			}
			
		});

		// 查询按钮事件
		this.view.getQueryButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					FrequencyPanelController.this.init();
					DialogBoxUtil.succeedDialog(FrequencyPanelController.this.view, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
				} catch (Exception e) {
					ExceptionManage.dispose(e, this.getClass());
				}

			}
		});

		// 设置时钟优先级排列
		view.getSetClockPRIList().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				final ArrangeClockDialog dialog = new ArrangeClockDialog(evt.getActionCommand(), view.getClockPRIMapItems());
				dialog.setSize(new Dimension(500, 520));
				dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));
//				dialog.addWindowListener(new WindowAdapter() {
//					@Override
//					public void windowClosed(WindowEvent e) {
//						dialog.dispose();
//					}
//				});

				dialog.getConfirm().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						ClockUtil clockUtil=new ClockUtil();
						if (dialog.validated()) {
							String result = dialog.getInfo(1);
							view.setDefaultClockPRI(result);
							view.initClockPriAndOutSelect(result, view.getDefaultOutSelect(), clockUtil.getClockPRIList(ConstantUtil.siteId), clockUtil.getOutSelectList(ConstantUtil.siteId));
							DialogBoxUtil.succeedDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
							dialog.dispose();
						} else {
							DialogBoxUtil.errorDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL));
						}
					}
				});
				dialog.setVisible(true);
			}

		});

		// 设置输出时钟选择
		view.getSetOutSelectList().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				final ArrangeClockDialog dialog = new ArrangeClockDialog(evt.getActionCommand(), view.getOutSelectMapItems());
				dialog.setSize(new Dimension(500, 520));
				dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));
//				dialog.addWindowListener(new WindowAdapter() {
//					@Override
//					public void windowClosed(WindowEvent e) {
//						dialog.dispose();
//					}
//				});
				dialog.getConfirm().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						ClockUtil clockUtil=new ClockUtil();
						if (dialog.validated()) {
							String result = dialog.getInfo(2);
							view.setDefaultOutSelect(result);
							view.initClockPriAndOutSelect(view.getDefaultClockPRI(), result, clockUtil.getClockPRIList(ConstantUtil.siteId), clockUtil.getOutSelectList(ConstantUtil.siteId));
							DialogBoxUtil.succeedDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
							dialog.dispose();
						} else {
							DialogBoxUtil.errorDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_NOT_FULL));
						}
					}
				});
				dialog.setVisible(true);
			}
		});

		// 设置输入源QL值GE1
		view.getSetClockInQLValueList().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				ClockUtil clockUtil=new ClockUtil();
				final QLClockDialog dialog = new QLClockDialog(evt.getActionCommand());
				dialog.init(view.getDefaultClockInQLValue());
				List<PortInst> portList = clockUtil.getFrequencyPorts(ConstantUtil.siteId);
				
				if (portList.size() == 4) {
					if(ResourceUtil.language.equals("zh_CN")){
						dialog.setSize(new Dimension(400, 350));
					}else{
						dialog.setSize(new Dimension(400, 350));
					}
					
				} else {
					if(ResourceUtil.language.equals("zh_CN")){
						dialog.setSize(new Dimension(450, 500));
					}else{
						dialog.setSize(new Dimension(600, 500));
					}
				}
				
				dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));
//				dialog.addWindowListener(new WindowAdapter() {
//					@Override
//					public void windowClosed(WindowEvent e) {
//						dialog.dispose();
//					}
//				});
				dialog.getConfirm().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent evt) {
						try {
							view.setDefaultClockInQLValue(dialog.get());
							DialogBoxUtil.succeedDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
							dialog.dispose();
						} catch (Exception e) {
							ExceptionManage.dispose(e, this.getClass());
						}
					}
				});

				dialog.getCancel().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						dialog.dispose();
					}
				});
				dialog.setVisible(true);
			}
		});

		// 输出源的QL值GE1
		view.getSetClockOutQLValueList().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				ClockUtil clockUtil=new ClockUtil();
				final QLClockDialog dialog = new QLClockDialog(evt.getActionCommand());
				dialog.init(view.getDefaultClockOutQLValue());
				List<PortInst> portList = clockUtil.getFrequencyPorts(ConstantUtil.siteId);
//				if (portList.size() == 4) {
//					dialog.setSize(new Dimension(400, 350));
//				} else {
//					dialog.setSize(new Dimension(450, 500));
//				}
				if (portList.size() == 4) {
					if(ResourceUtil.language.equals("zh_CN")){
						dialog.setSize(new Dimension(400, 350));
					}else{
						dialog.setSize(new Dimension(400, 350));
					}
					
				} else {
					if(ResourceUtil.language.equals("zh_CN")){
						dialog.setSize(new Dimension(450, 500));
					}else{
						dialog.setSize(new Dimension(600, 500));
					}
				}
				dialog.setLocation(UiUtil.getWindowWidth(dialog.getWidth()), UiUtil.getWindowHeight(dialog.getHeight()));
//				dialog.addWindowListener(new WindowAdapter() {
//					@Override
//					public void windowClosed(WindowEvent e) {
//						dialog.dispose();
//					}
//				});
				dialog.getConfirm().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						try {
							view.setDefaultClockOutQLValue(dialog.get());
							DialogBoxUtil.succeedDialog(dialog, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
							dialog.dispose();
						} catch (Exception e) {
							ExceptionManage.dispose(e, this.getClass());
						}
					}
				});

				dialog.getCancel().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						dialog.dispose();
					}
				});
				dialog.setVisible(true);
			}

		});
		// 时钟倒换
		view.getClockRorate().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				final ClockRorateDialog dialog = new ClockRorateDialog(info,siteRoate);
				UiUtil.showWindow(dialog, 450, 400);
			}
		});
	}

	public void synchro() {
		DispatchUtil frqDispatch = null;
		try {
			
			frqDispatch = new DispatchUtil(RmiKeys.RMI_CLOCKFREQU);
			String result = frqDispatch.synchro(ConstantUtil.siteId);
			DialogBoxUtil.succeedDialog(null, result);
			//添加日志记录
			this.insertOpeLogSYNC(EOperationLogType.SYNCSLOCK.getValue(), result, null, null);	
			this.init();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			frqDispatch = null;
		}
	}
	
	private void insertOpeLogSYNC(int operationType, String result, Object oldClock, Object newClock){		
		AddOperateLog.insertOperLog(view.getConfimButton(), operationType, result, oldClock, newClock, ConstantUtil.siteId,ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCKFREQUENCY),"FrequencyInfo");				
	}
	
	
	private String configFrequency(FrequencyInfo info) throws Exception {
		DispatchUtil service = null;
		String result = null;
		try {
			service = new DispatchUtil(RmiKeys.RMI_CLOCKFREQU);
			if (info.getId() == 0) {
				result = service.excuteInsert(info);
			} else {
				result = service.excuteUpdate(info);
			}
			this.init();
		} catch (Exception e) {
			ExceptionManage.dispose(e, this.getClass());
			throw e;
		} finally {
			service = null;
		}
		return result;
	}

	public FrequencyPanel getView() {
		return view;
	}

	public void setView(FrequencyPanel view) {
		this.view = view;
	}
}
