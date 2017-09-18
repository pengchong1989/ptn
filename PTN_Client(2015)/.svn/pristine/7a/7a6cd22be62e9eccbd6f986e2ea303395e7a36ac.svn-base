package com.nms.ui.ptn.business.dialog.loopProtect;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import twaver.Element;
import twaver.Link;
import twaver.PopupMenuGenerator;
import twaver.TUIManager;
import twaver.TView;

import com.nms.db.bean.path.Segment;
import com.nms.db.bean.ptn.CommonBean;
import com.nms.db.bean.ptn.path.protect.LoopProtectInfo;
import com.nms.db.enums.EActiveStatus;
import com.nms.db.enums.EManufacturer;
import com.nms.db.enums.EOperationLogType;
import com.nms.db.enums.EServiceType;
import com.nms.model.equipment.shlef.SiteService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.frame.ViewDataTable;
import com.nms.ui.manager.AddOperateLog;
import com.nms.ui.manager.AutoNamingUtil;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.SegmentTopoPanel;
import com.nms.ui.manager.TopoAttachment;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.VerifyNameUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.control.PtnSpinner;
import com.nms.ui.manager.control.PtnTextField;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.manager.keys.StringKeysTitle;
import com.nms.ui.ptn.business.loopProtect.LoopProtectPanel;

public class AddLoopProtectDialog extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ViewDataTable<Segment> segmenttable;
	private JScrollPane jscrollPane_segment;
	private  SegmentTopoPanel segmentTopo = null;
	private HashMap<Integer,Segment> segmentList = new HashMap<Integer, Segment>();
	private LoopProtectPanel loopProtectPanel ;
	
	public AddLoopProtectDialog(LoopProtectPanel view, boolean modal, LoopProtectInfo loopProtectInfo) throws Exception {
		this.setModal(modal);
		initComponents();
		initTopo();
		setLayout();
		loopProtectPanel = view;
		super.setTitle(ResourceUtil.srcStr(StringKeysTitle.TIT_LOOPPROTECT));
	}

	public AddLoopProtectDialog() {

	}

	private void initComponents() throws Exception {
		Dimension dimension = new Dimension(1200, 700);
		this.setSize(dimension);
		this.setMinimumSize(dimension);

		this.lblMessage = new JLabel();
		jSplitPane1 = new javax.swing.JSplitPane();
		jPanel3 = new javax.swing.JPanel();
		lblName = new javax.swing.JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_NAME));
		jTextArea1 = new javax.swing.JTextArea();
		jButton2 = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),true);
		pathCheckButton = new javax.swing.JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_PATH_INSPECT));
		jTextField1 = new PtnTextField(true, PtnTextField.STRING_MAXLENGTH, this.lblMessage, this.jButton2, this);
		this.lblwaitTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_WAIT_TIME));
//		this.txtWaitTime = new PtnTextField(false, PtnTextField.TYPE_INT, PtnTextField.INT_MAXLENGTH, this.lblMessage, this.jButton2, this);
		this.lbldelayTime = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_DELAY_TIME));
//		this.txtDelayTime = new PtnTextField(false, PtnTextField.TYPE_INT, PtnTextField.INT_MAXLENGTH, this.lblMessage, this.jButton2, this);
		
		this.txtDelayTime=new PtnSpinner(PtnSpinner.TYPE_DELAYTIME);
		this.txtWaitTime=new PtnSpinner(PtnSpinner.TYPE_WAITTIME);
		
		this.chkAps = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_APS_ENABLE));
		this.lblType = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_TYPE));
		this.cmbType = new JComboBox();
		this.isBack = new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_BACK));
		segmentLabel = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_LOOP));
		segmenttable = new ViewDataTable<Segment>("loopsegmentTable");
		segmenttable.getTableHeader().setResizingAllowed(true);
		segmenttable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		segmenttable.setTableHeaderPopupMenuFactory(null);
		segmenttable.setTableBodyPopupMenuFactory(null);

		jscrollPane_segment = new JScrollPane();
		jscrollPane_segment.setViewportView(segmenttable);
		this.activeStatusCheck =  new JCheckBox(ResourceUtil.srcStr(StringKeysLbl.LBL_IS_ACTIVATED));
		this.activeStatusCheck.setSelected(true);
		autoNamingBtn = new javax.swing.JButton(ResourceUtil.srcStr(StringKeysLbl.LBL_AUTO_NAME));
		pathCheckButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if(!cheackLoop()){
					DialogBoxUtil.errorDialog(null, ResourceUtil.srcStr(StringKeysTip.TIP_NOTLOOP));
					return;
				}
			}
		});

		jButton2.addActionListener(new MyActionListener() {

			@Override
			public boolean checking() {
				return true;
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					confrimAction();
				} catch (Exception e1) {
					ExceptionManage.dispose(e1,this.getClass());
				}
				
			}
		});
		this.autoNamingBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				autoNamingBtnAction();
				
			}

			
		});
	}

	private void setLayout() {
		this.add(this.jSplitPane1);
		this.jSplitPane1.setLeftComponent(this.jPanel3);
		this.jPanel3.setPreferredSize(new Dimension(280, 700));
		this.jSplitPane1.setRightComponent(segmentTopo);
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 50, 180, 50 };
		layout.columnWeights = new double[] { 0, 0, 0 };
		layout.rowHeights = new int[] { 25, 30, 100, 30, 30, 30, 30, 30, 15, 30 };
		layout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0.2 };
		this.jPanel3.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;

		/** 第0行 */
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(this.lblMessage, c);
		this.jPanel3.add(this.lblMessage);

		/** 第一行 */
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(lblName, c);
		this.jPanel3.add(lblName);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(jTextField1, c);
		this.jPanel3.add(jTextField1);
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(this.autoNamingBtn, c);
		this.jPanel3.add(this.autoNamingBtn);

		/** 第一行 */
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(segmentLabel, c);
		this.jPanel3.add(segmentLabel);
		
		/** 第二行 段 */
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		layout.addLayoutComponent(this.jscrollPane_segment, c);
		this.jPanel3.add(this.jscrollPane_segment);

		/** 第三行 等待恢复时间 */
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(this.lblwaitTime, c);
		this.jPanel3.add(this.lblwaitTime);
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(this.txtWaitTime, c);
		this.jPanel3.add(this.txtWaitTime);
		/** 第四行 拖延时间 */
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(this.lbldelayTime, c);
		this.jPanel3.add(this.lbldelayTime);
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(this.txtDelayTime, c);
		this.jPanel3.add(this.txtDelayTime);
		/** 第五行 aps使能 */
		c.gridx = 0;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 10, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		layout.setConstraints(this.chkAps, c);
		this.jPanel3.add(this.chkAps);
		/** 第六行 是否返回 */
		c.gridx = 0;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 10, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		layout.setConstraints(this.isBack, c);
		this.jPanel3.add(this.isBack);
		
		/** 第7行 是否激活*/
		c.gridx = 0;
		c.gridy = 7;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 10, 5, 5);
		layout.setConstraints(this.activeStatusCheck, c);
		this.jPanel3.add(this.activeStatusCheck);
		
		
		
		/** 第8行 确定按钮 中间空出一行 */
		c.gridx = 0;
		c.gridy = 8;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.insets = new Insets(5, 10, 5, 5);
		c.anchor = GridBagConstraints.EAST;
		c.fill = GridBagConstraints.NONE;
//		layout.setConstraints(this.pathCheckButton, c);
//		this.jPanel3.add(this.pathCheckButton);
		c.gridx = 2;
		c.gridy = 8;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.CENTER;
		c.fill = GridBagConstraints.NONE;
		layout.addLayoutComponent(this.jButton2, c);
		this.jPanel3.add(this.jButton2);
	}

	private void initTopo() {
		try {
			segmentTopo = new SegmentTopoPanel();
			segmentTopo.getNetwork().setPopupMenuGenerator(new PopupMenuGenerator() {
				@SuppressWarnings("unchecked")
				public JPopupMenu generate(TView tview, MouseEvent mouseEvent) {
					JPopupMenu menu = new JPopupMenu();

					if (!tview.getDataBox().getSelectionModel().isEmpty()) {
						final Element element = tview.getDataBox().getLastSelectedElement();
						final List<Element> allElements = tview.getDataBox().getAllElements();
						// 添加段
						 if (element instanceof Link) {
							JMenuItem addjMenuItem = new JMenuItem(ResourceUtil.srcStr(StringKeysLbl.LBL_LOOP));
							addjMenuItem.addActionListener(new java.awt.event.ActionListener() {
								public void actionPerformed(java.awt.event.ActionEvent evt) {
									Segment segment = (Segment)element.getUserObject();
									//两个网元之间只能有一条段
									Segment segmentRemoved = null;
									for(int id :segmentList.keySet())
									{
										int aSiteId = segmentList.get(id).getASITEID();
										int zSiteId = segmentList.get(id).getZSITEID();
										if((aSiteId == segment.getASITEID() && zSiteId == segment.getZSITEID())||
												(aSiteId == segment.getZSITEID() && zSiteId == segment.getASITEID()))
										{
											segmentRemoved = segmentList.get(id);
											segmentList.remove(id);
											break;
										}
									}
									if(segmentRemoved != null){
										for (Element elementLink : allElements) {
											if (elementLink instanceof Link) {
												Link link = (Link) elementLink;
												Segment segmentLink = (Segment) link.getUserObject();
												if(segmentLink.getId() == segmentRemoved.getId()){
													((Link) elementLink).putLinkColor(Color.GREEN);
												}
											}
										}
									}
									segmentList.put(segment.getId(), segment);
									loadPortTable_a(segmentList);
									((Link) element).putLinkColor(Color.yellow);
								}
							});
							
							JMenuItem deletejMenuItem = new JMenuItem(ResourceUtil.srcStr(StringKeysLbl.LBL_LOOPDEL));
							deletejMenuItem.addActionListener(new java.awt.event.ActionListener() {
								public void actionPerformed(java.awt.event.ActionEvent evt) {
									Segment segment = (Segment)element.getUserObject();
									Set<Integer> set = segmentList.keySet(); 
									for(int id :set)
									{
										if(id == segment.getId())
										{
											segmentList.remove(id);
											break;
										}
									}
									loadPortTable_a(segmentList);
									((Link) element).putLinkColor(Color.GREEN);
									
								}
							});
							menu.add(addjMenuItem);
							menu.add(deletejMenuItem);
						}
					}
					return menu;
				}
			});
			
			segmentTopo.getNetwork().addElementClickedActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					Element element = (Element) e.getSource();
					if(element!=null&&element instanceof Link){
						if(element.getUserObject()!=null&&element.getBusinessObject()==null){
							TUIManager.registerAttachment("SegmenttopoTitle", TopoAttachment.class,1, (int) element.getX(), (int) element.getY());
							Segment segment = (Segment)element.getUserObject();
							element.setBusinessObject(segment.getNAME());
							element.addAttachment("SegmenttopoTitle");
						}else{
							element.removeAttachment("SegmenttopoTitle");
							element.setBusinessObject(null);
						}    
					}
				}
			});
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
		}
	}


	/**
	 * 将toop中选择的段添加到表格中
	 * @param segmentList
	 */
	public void loadPortTable_a(HashMap<Integer,Segment> segmentList) {
		getSegmenttable().clear();
		List<Segment> list = new ArrayList<Segment>();
		for(Segment segment :segmentList.values()){
			list.add(segment);
		}
		getSegmenttable().initData(list);
	}

	/**
	 * 创建时收集环保护信息
	 * @throws Exception 
	 */
	public void confrimAction() throws Exception{
		if(!cheackLoop()){
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NOTLOOP));
			return;
		}

		// 验证名称是否合理
		String beforeName = null;
		VerifyNameUtil verifyNameUtil=new VerifyNameUtil();
		if (verifyNameUtil.verifyName(EServiceType.WRAPPINGPROTECT.getValue(), jTextField1.getText().trim(), beforeName)) {
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NAME_EXIST));
			return ;
		}
		
		List<Integer> siteIdList = new ArrayList<Integer>();
		List<Segment> list = new ArrayList<Segment>();
		HashMap<String,Segment> segments = new HashMap<String, Segment>();
		HashMap<Integer,Segment> segmenMap = new HashMap<Integer, Segment>();
		List<LoopProtectInfo> loopProtectInfoList = new ArrayList<LoopProtectInfo>();
		List<Integer> siteList = new ArrayList<Integer>();//网元集合
		List<Segment> segmentlist = new ArrayList<Segment>();//与网元相对应的段集合
		SiteService_MB siteServiceMB=(SiteService_MB) ConstantUtil.serviceFactory.newService_MB(Services.SITE);
		for(Segment segment :segmentList.values()){
			if(!siteIdList.contains(segment.getASITEID())){
				siteIdList.add(segment.getASITEID());
			}
			if(!siteIdList.contains(segment.getZSITEID())){
				siteIdList.add(segment.getZSITEID());
			}
			list.add(segment);
			segments.put("'"+segment.getASITEID()+"','"+segment.getZSITEID()+"'", segment);
		}
		if(siteIdList.size()>list.size()){
			DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_NOTLOOP_WAY));
			return;
		}
		segmenMap.put(list.get(0).getASITEID(),list.get(0));
		int i = 1;
		
		//获取网元和段的map集合
		HashMap<Integer,Segment> segmenHashMap = null;
		while(segmenMap.size() != list.size()){
			
			int siteId = 0;
			Segment segment2 = new Segment();
			if(i==1){
				segment2 = list.get(0);
				siteId = list.get(0).getASITEID();
				segmenHashMap = getNextSegment(list.get(0), segments, list.get(0).getASITEID());
			}else{
				
				if(segmenHashMap != null){
					for(Segment segment : segmenHashMap.values()){
						segment2 = segment;
					}
					for(Integer id : segmenHashMap.keySet()){
						siteId = id;
					}
				}
				
				segmenHashMap = getNextSegment(segment2, segments, siteId);
			}
			//获取网元id和对应的段的信息
			siteList.add(siteId);
			segmentlist.add(segment2);
			segmenMap.put(siteId, segment2);
			i++;
		}
		
		
	//	BusinessidList = businessidService.select_site(siteIds, type);
		//获取每个节点的环网保护信息
		List<CommonBean> loopPathNameList = new ArrayList<CommonBean>();
		for (int j = 0; j < segmentlist.size(); j++) {
			LoopProtectInfo loopProtectInfo = new LoopProtectInfo();
			Segment eastsegment = segmentlist.get(j);
			Segment westsegment = null;
			
			if(j==0){
				westsegment = segmentlist.get(segmentlist.size()-1);
			}else{
				westsegment = segmentlist.get(j-1);
			}
			try {
				if(siteList.get(j) == eastsegment.getASITEID()){
					loopProtectInfo.setEastPort(eastsegment.getAPORTID());
					loopProtectInfo.setEastSlot(eastsegment.getaSlotNumber());
				}else{
					loopProtectInfo.setEastPort(eastsegment.getZPORTID());
					loopProtectInfo.setEastSlot(eastsegment.getzSlotNumber());
				}
				if(siteList.get(j) == westsegment.getASITEID()){
					loopProtectInfo.setWestPort(westsegment.getAPORTID());
					loopProtectInfo.setWestSlot(westsegment.getaSlotNumber());
				}else{
					loopProtectInfo.setWestPort(westsegment.getZPORTID());
					loopProtectInfo.setWestSlot(westsegment.getzSlotNumber());
				}
				loopProtectInfo.setSiteId(siteList.get(j));
				if (EManufacturer.WUHAN.getValue() == siteServiceMB.getManufacturer(siteList.get(j))){
					loopProtectInfo.setNodeId(j+1);
				}
				loopProtectInfo.setCreateUser(ConstantUtil.user.getUser_Name());
				loopProtectInfo.setLoopNodeNumber(segmentlist.size());
				loopProtectInfo.setApsenable(chkAps.isSelected()?1:0);
				loopProtectInfo.setWaittime(Integer.parseInt(txtWaitTime.getTxtData()));
				loopProtectInfo.setDelaytime(Integer.parseInt(txtDelayTime.getTxtData()));
				loopProtectInfo.setActiveStatus(this.activeStatusCheck.isSelected()?EActiveStatus.ACTIVITY.getValue():EActiveStatus.UNACTIVITY.getValue());
			} catch (Exception e) {
				ExceptionManage.dispose(e,this.getClass());
			}
				loopProtectInfo.setName(jTextField1.getText());
				loopProtectInfo.setBackType(isBack.isSelected()?1:0);
				loopProtectInfo.setIsSingle(0);
				loopProtectInfoList.add(loopProtectInfo);
				CommonBean loopPathName = new CommonBean();
				loopPathName.setLoopPathName(eastsegment.getNAME());
				loopPathNameList.add(loopPathName);
			}	
		
		DispatchUtil wrappingDispatch = new DispatchUtil(RmiKeys.RMI_WRAPPING);
		try {
			String result = null;
			result = wrappingDispatch.excuteInsert(loopProtectInfoList);
			DialogBoxUtil.succeedDialog(this, result);
			//添加日志记录
			loopProtectInfoList.get(0).setLoopPathNameList(loopPathNameList);
			for (LoopProtectInfo loop : loopProtectInfoList) {
				AddOperateLog.insertOperLog(jButton2, EOperationLogType.LOOPPROTECTINSERT.getValue(), result, null, 
						loopProtectInfoList.get(0), loop.getSiteId(), loopProtectInfoList.get(0).getName(), "wrapping");
			}
			this.loopProtectPanel.getController().refresh();
			this.dispose();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		} finally {
			UiUtil.closeService_MB(siteServiceMB);
		}
		
	}
	




	
	/**
	 * 检测是否能组成环
	 */
	public boolean cheackLoop(){
		HashMap<String,Segment> segments = new HashMap<String, Segment>();
		List<Segment> segmentsList = new ArrayList<Segment>();
		for(Segment segment :segmentList.values()){
			segments.put("'"+segment.getASITEID()+"','"+segment.getZSITEID()+"'", segment);
			segmentsList.add(segment);
		}
		boolean check = true;
		int i = 1;
		if(segmentsList.size()<3){
			return false;
		}
		HashMap<Integer,Segment> segmentMap = null;
		while(check){
			if(i==1){
				segmentMap = getNextSegment(segmentsList.get(0), segments, segmentsList.get(0).getASITEID());
			}else{
				Segment segment2 = new Segment();
				int siteId = 0;
				if(segmentMap != null){
					for(Segment segment : segmentMap.values()){
						segment2 = segment;
					}
					for(Integer id : segmentMap.keySet()){
						siteId = id;
					}
				}
				segmentMap = getNextSegment(segment2, segments, siteId);//根据当前节点查找下一节点
			}
			
			if(segmentMap == null){//不能组成环时，会在最末端找不到下一节点
				check = false;
			}
			
			if(i>segmentsList.size()){//可以组成环时，可以一直找到下一节点
				check = true;
				break;
			}
			i++;
		}
		return check;
	}
	
	/**
	 * 根据当前段，网元id,搜索下一个段,下一个节点网元id
	 * @param segment
	 * @param segments
	 * @return
	 */
	public HashMap<Integer,Segment> getNextSegment(Segment segment,HashMap<String,Segment> segments,int siteId){
		HashMap<Integer,Segment> nodeSegment = null;
		Segment segmentNext = null;
		int nextSiteId = 0;
		if(segment.getASITEID() == siteId){
			nextSiteId = segment.getZSITEID();
		}else{
			nextSiteId = segment.getASITEID();
		}
		for(String str :segments.keySet()){
			if(str.contains("'"+nextSiteId+"'") && !str.contains("'"+siteId+"'")){//包含下一个节点网元id，不包含上一个节点网元id
				segmentNext = segments.get(str);
				nodeSegment = new HashMap<Integer, Segment>();
				nodeSegment.put(nextSiteId, segmentNext);
			}
		}
		return nodeSegment;	
	}
	private void autoNamingBtnAction() {
		LoopProtectInfo loopProtectInfo;
		try {
			loopProtectInfo = new LoopProtectInfo();
			loopProtectInfo.setIsSingle(0);
			loopProtectInfo.setSiteId(ConstantUtil.siteId);
			AutoNamingUtil autoNamingUtil=new AutoNamingUtil();
			String autoNaming = (String) autoNamingUtil.autoNaming(loopProtectInfo, null, null);
			jTextField1.setText(autoNaming);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	public ViewDataTable<Segment> getSegmenttable() {
		return segmenttable;
	}

	public void setSegmenttable(ViewDataTable<Segment> segmenttable) {
		this.segmenttable = segmenttable;
	}

	public JScrollPane getJscrollPane_segment() {
		return jscrollPane_segment;
	}

	public void setJscrollPane_segment(JScrollPane jscrollPaneSegment) {
		jscrollPane_segment = jscrollPaneSegment;
	}

	public javax.swing.JCheckBox getjCheckBox1() {
		return jCheckBox1;
	}

	public void setjCheckBox1(javax.swing.JCheckBox jCheckBox1) {
		this.jCheckBox1 = jCheckBox1;
	}

	public javax.swing.JLabel getLblName() {
		return lblName;
	}

	public void setLblName(javax.swing.JLabel lblName) {
		this.lblName = lblName;
	}

	public javax.swing.JLabel getjLabel4() {
		return jLabel4;
	}

	public void setjLabel4(javax.swing.JLabel jLabel4) {
		this.jLabel4 = jLabel4;
	}

	public javax.swing.JLabel getjLabel5() {
		return jLabel5;
	}

	public void setjLabel5(javax.swing.JLabel jLabel5) {
		this.jLabel5 = jLabel5;
	}

	public javax.swing.JPanel getjPanel3() {
		return jPanel3;
	}

	public void setjPanel3(javax.swing.JPanel jPanel3) {
		this.jPanel3 = jPanel3;
	}

	public javax.swing.JSplitPane getjSplitPane1() {
		return jSplitPane1;
	}

	public void setjSplitPane1(javax.swing.JSplitPane jSplitPane1) {
		this.jSplitPane1 = jSplitPane1;
	}

	public javax.swing.JTextArea getjTextArea1() {
		return jTextArea1;
	}

	public void setjTextArea1(javax.swing.JTextArea jTextArea1) {
		this.jTextArea1 = jTextArea1;
	}

	public javax.swing.JTextField getjTextField1() {
		return jTextField1;
	}

	public void setjTextField1(javax.swing.JTextField jTextField1) {
		this.jTextField1 = jTextField1;
	}

	public javax.swing.JTextField getjTextField2() {
		return jTextField2;
	}

	public void setjTextField2(javax.swing.JTextField jTextField2) {
		this.jTextField2 = jTextField2;
	}

	public javax.swing.JTextField getjTextField3() {
		return jTextField3;
	}

	public void setjTextField3(javax.swing.JTextField jTextField3) {
		this.jTextField3 = jTextField3;
	}

	public javax.swing.JTextField getjTextField4() {
		return jTextField4;
	}

	public void setjTextField4(javax.swing.JTextField jTextField4) {
		this.jTextField4 = jTextField4;
	}

	public javax.swing.JButton getPathCheckButton() {
		return pathCheckButton;
	}

	public void setPathCheckButton(javax.swing.JButton pathCheckButton) {
		this.pathCheckButton = pathCheckButton;
	}

	public JLabel getLblqos() {
		return lblqos;
	}

	public void setLblqos(JLabel lblqos) {
		this.lblqos = lblqos;
	}

	public JLabel getLbloam() {
		return lbloam;
	}

	public void setLbloam(JLabel lbloam) {
		this.lbloam = lbloam;
	}

	public JTextField getTxtQos() {
		return txtQos;
	}

	public void setTxtQos(JTextField txtQos) {
		this.txtQos = txtQos;
	}

	public JTextField getTxtOam() {
		return txtOam;
	}

	public void setTxtOam(JTextField txtOam) {
		this.txtOam = txtOam;
	}

	public JLabel getLblMessage() {
		return lblMessage;
	}

	public void setLblMessage(JLabel lblMessage) {
		this.lblMessage = lblMessage;
	}

	public JLabel getLblType() {
		return lblType;
	}

	public void setLblType(JLabel lblType) {
		this.lblType = lblType;
	}

	public JComboBox getCmbType() {
		return cmbType;
	}

	public void setCmbType(JComboBox cmbType) {
		this.cmbType = cmbType;
	}

	public JLabel getLblwaitTime() {
		return lblwaitTime;
	}

	public void setLblwaitTime(JLabel lblwaitTime) {
		this.lblwaitTime = lblwaitTime;
	}

	public JLabel getLbldelayTime() {
		return lbldelayTime;
	}

	public void setLbldelayTime(JLabel lbldelayTime) {
		this.lbldelayTime = lbldelayTime;
	}

//	public PtnTextField getTxtWaitTime() {
//		return txtWaitTime;
//	}
//
//	public void setTxtWaitTime(PtnTextField txtWaitTime) {
//		this.txtWaitTime = txtWaitTime;
//	}
//
//	public PtnTextField getTxtDelayTime() {
//		return txtDelayTime;
//	}
//
//	public void setTxtDelayTime(PtnTextField txtDelayTime) {
//		this.txtDelayTime = txtDelayTime;
//	}

	public JCheckBox getChkAps() {
		return chkAps;
	}

	public PtnSpinner getTxtWaitTime() {
		return txtWaitTime;
	}

	public void setTxtWaitTime(PtnSpinner txtWaitTime) {
		this.txtWaitTime = txtWaitTime;
	}

	public PtnSpinner getTxtDelayTime() {
		return txtDelayTime;
	}

	public void setTxtDelayTime(PtnSpinner txtDelayTime) {
		this.txtDelayTime = txtDelayTime;
	}

	public void setChkAps(JCheckBox chkAps) {
		this.chkAps = chkAps;
	}

	public JLabel getSegmentLabel() {
		return segmentLabel;
	}

	public void setSegmentLabel(JLabel segmentLabel) {
		this.segmentLabel = segmentLabel;
	}

	public PtnButton getJButton2() {
		return jButton2;
	}

	public void setJButton2(PtnButton button2) {
		jButton2 = button2;
	}

	private PtnButton jButton2;
	// private javax.swing.JButton jButton3;
	private javax.swing.JCheckBox jCheckBox1;
	private javax.swing.JLabel lblName;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JSplitPane jSplitPane1;
	private javax.swing.JTextArea jTextArea1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JButton pathCheckButton;
	private JLabel lblqos;
	private JLabel lbloam;
	private JTextField txtQos;
	private JTextField txtOam;
	private JLabel lblMessage;
	private JLabel lblType;
	private JComboBox cmbType;
	private JLabel lblwaitTime;
	private JLabel lbldelayTime;
	private PtnSpinner txtWaitTime;
	private PtnSpinner txtDelayTime;
	private JCheckBox chkAps;
	private JLabel segmentLabel;
	private JCheckBox isBack;
	private JCheckBox activeStatusCheck;
	private javax.swing.JButton autoNamingBtn;
}