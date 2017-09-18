package com.nms.ui.ptn.clock.view.cx.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import twaver.Element;
import twaver.Node;
import twaver.TDataBox;
import twaver.list.TList;

import com.nms.db.bean.ptn.clock.PortConfigInfo;
import com.nms.model.ptn.clock.PortDispositionInfoService_MB;
import com.nms.model.util.Services;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.manager.keys.StringKeysOperaType;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.clock.view.cx.time.TabPanelTwoTCX;

public class PortMapCreateDialog extends PtnDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7090004269283202345L;
	private JLabel VLANID;
	private JComboBox VLANID_ = null;
	private TDataBox needChooseBox;
	private TList needChooseList;
	private TDataBox choosedBox;
	private TList choosedList;
	private TabPanelTwoTCX tabPanelTwoTCX = null;
	private JButton addButton;
	private JButton removeButton;
	private JPanel controlPane;
	private JScrollPane needChooseScrollPane;
	private JScrollPane choosedScrollPane;
	private PtnButton confirm;
	private JButton cancel;
	private JDialog jDialog=null;
	private GridBagLayout gridBagLayout=null;
	private List<PortConfigInfo> listPortDispositionInfo=null;
	private JPanel titleLayout=null;
	private JPanel buttonLayout=null;
	public PortMapCreateDialog(TabPanelTwoTCX tabPanelTwoTCX) throws Exception {
		try {
			this.tabPanelTwoTCX = tabPanelTwoTCX;
			init();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void init() throws Exception {
		PortDispositionInfoService_MB portDispositionInfoService=null;
		List<PortConfigInfo> lists=null;
		try {
			portDispositionInfoService=(PortDispositionInfoService_MB) ConstantUtil.serviceFactory.newService_MB(Services.PortDispositionInfoService);
			gridBagLayout=new GridBagLayout();
			VLANID=new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_VLAN_ID));
			listPortDispositionInfo=portDispositionInfoService.select(ConstantUtil.siteId);
			
			if(listPortDispositionInfo==null||listPortDispositionInfo.size()==0){
				DialogBoxUtil.errorDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_SELECT_DATA_ONE));
			}else{
				VLANID_=new JComboBox();
				lists=deleteSame(listPortDispositionInfo);
				for(PortConfigInfo info:lists){
						VLANID_.addItem(info.getVlanID());
				}
				initComponents();
				initControlPane();
				setTitleLayout();
				setButton();
				setMainLayout();                                                                        
				needChooseScrollPane.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysLbl.LBL_PORT)));
				choosedScrollPane.setBorder(BorderFactory.createTitledBorder(ResourceUtil.srcStr(StringKeysOperaType.BTN_PORT_MAP)));
				this.add(needChooseScrollPane);
				this.add(choosedScrollPane);
				this.add(controlPane);
				this.add(titleLayout);
				this.add(buttonLayout);
				this.setLayout(gridBagLayout);
				this.setTitle("端口Map表");
				addActionLister();
				UiUtil.showWindow(this, 500, 500);
			}
		} catch (Exception e) {
			throw e;
		}finally{
			UiUtil.closeService_MB(portDispositionInfoService);
		}
	}
//	/**
//	 * 设置确定按钮
//	 */
	private void setButton() {
		GridBagConstraints gridBagConstraints = null;
		GridBagLayout buttonGridBagLayout=null;
		try {
			buttonLayout=new JPanel();
			buttonGridBagLayout=new GridBagLayout();
			gridBagConstraints=new GridBagConstraints();
			buttonGridBagLayout.columnWidths=new int[]{20,100};
			buttonGridBagLayout.columnWeights = new double[] {0,0,0};
			buttonGridBagLayout.rowHeights=new int[]{30,100};
			buttonGridBagLayout.rowWeights = new double[] { 0, 0};
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.gridheight = 1;
			gridBagConstraints.gridwidth = 1;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.fill = GridBagConstraints.WEST;
			gridBagConstraints.insets = new Insets(5, 5, 5, 20);
			buttonGridBagLayout.setConstraints(confirm, gridBagConstraints);
			buttonLayout.add(confirm);
			
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.gridheight = 1;
			gridBagConstraints.gridwidth = 1;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.fill = GridBagConstraints.WEST;
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			buttonGridBagLayout.setConstraints(cancel, gridBagConstraints);
			buttonLayout.add(cancel);
			buttonLayout.setLayout(buttonGridBagLayout);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}

	private void setTitleLayout() {
		GridBagConstraints gridBagConstraints = null;
		GridBagLayout titleGridBagLayout=null;
		try {
			titleLayout=new JPanel();
			titleGridBagLayout=new GridBagLayout();
			gridBagConstraints=new GridBagConstraints();
			titleGridBagLayout.columnWidths=new int[]{50,150};
			titleGridBagLayout.columnWeights = new double[] {0,0,0};
			titleGridBagLayout.rowHeights=new int[]{10};
			titleGridBagLayout.rowWeights = new double[] { 0, 0};
			gridBagConstraints.fill=GridBagConstraints.BOTH; 
			gridBagConstraints.gridx=0;
			gridBagConstraints.gridy=0;
			titleGridBagLayout.setConstraints(VLANID, gridBagConstraints);
			titleLayout.add(VLANID);
			
//			gridBagConstraints.insets = new Insets(5, 5, 0, 0);
			gridBagConstraints.gridx=1;
			gridBagConstraints.gridy=0;
			gridBagConstraints.ipady=1;
			gridBagConstraints.ipadx=50;
			titleGridBagLayout.setConstraints(VLANID_, gridBagConstraints);
			titleLayout.add(VLANID_);
			titleLayout.setLayout(titleGridBagLayout);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}

	/**
	 * 设置主界面
	 */
	private void setMainLayout() {
		GridBagConstraints gridBagConstraints = null;
		try {
			gridBagConstraints=new GridBagConstraints();
			gridBagLayout.columnWidths=new int[]{100,50,150};
			gridBagLayout.columnWeights = new double[] {0,0,0};
			gridBagLayout.rowHeights=new int[]{50,300,50};
			gridBagLayout.rowWeights = new double[] { 0, 0};
			gridBagConstraints.fill=GridBagConstraints.BOTH; 
			
			gridBagConstraints.gridx=0;
			gridBagConstraints.gridy=0;
			gridBagLayout.setConstraints(titleLayout, gridBagConstraints);
			
			gridBagConstraints.insets = new Insets(5, 5, 5, 0);
			gridBagConstraints.gridx=0;
			gridBagConstraints.gridy=1;
			gridBagLayout.setConstraints(needChooseScrollPane, gridBagConstraints);
			
			gridBagConstraints.insets = new Insets(5, 5, 5, 0);
			gridBagConstraints.gridx=1;
			gridBagConstraints.gridy=1;
			gridBagLayout.setConstraints(controlPane, gridBagConstraints);
			
			gridBagConstraints.insets = new Insets(5, 5, 5, 0);
			gridBagConstraints.gridx=2;
			gridBagConstraints.gridy=1;
			gridBagLayout.setConstraints(choosedScrollPane, gridBagConstraints);
			
			gridBagConstraints.insets = new Insets(5, 5, 5, 0);
			gridBagConstraints.gridx=2;
			gridBagConstraints.gridy=2;
			gridBagLayout.setConstraints(buttonLayout, gridBagConstraints);
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 设置按钮的布局
	 */
	private void initControlPane() {
		try {
			controlPane=new JPanel();
			GridBagLayout controlLayout = new GridBagLayout();
			controlLayout.columnWidths = new int[] { 25 };
			controlLayout.columnWeights = new double[] { 0.0 };
			controlLayout.rowHeights = new int[] { 5, 5, 5, 5 };
			controlLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0 };
			controlPane.setLayout(controlLayout);
			GridBagConstraints c = null;
			c = new GridBagConstraints();
			c.fill = GridBagConstraints.BOTH;
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.insets = new Insets(10, 10, 10, 10);
			controlLayout.setConstraints(addButton, c);
			controlPane.add(addButton);
			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			c.gridheight = 1;
			c.insets = new Insets(10, 10, 10, 10);
			controlLayout.setConstraints(removeButton, c);
			controlPane.add(removeButton);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 初始化组界面
	 */
	private void initComponents(){
		try {
			needChooseBox = new TDataBox("NeedChooseList");
			needChooseList = new TList(needChooseBox);
			needChooseList.setTListSelectionMode(TList.CHECK_SELECTION);
			
			choosedBox = new TDataBox("ChoosedList");
			choosedList = new TList(choosedBox);
			choosedList.setTListSelectionMode(TList.CHECK_SELECTION);
			
			addButton = new JButton(">");
			removeButton = new JButton("<");
			
			needChooseScrollPane = new JScrollPane(needChooseList);
			needChooseScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			needChooseScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			needChooseScrollPane.setViewportView(needChooseList);
			
			choosedScrollPane = new JScrollPane(choosedList);
			choosedScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			choosedScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			choosedScrollPane.setViewportView(choosedList);
			
			confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_SAVE),false);
			cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
			//初始化介面
			initChooseBox();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	private void initChooseBox() {
		String portName=null;
		try {
			for(PortConfigInfo info:listPortDispositionInfo){
				if(info.getVlanID().equals(VLANID_.getItemAt(0))){
					Node node = new Node();
					portName=UiUtil.getCodeById(info.getPort()).getCodeName();
//					portName=String.valueOf(info.getPort());
					node.setName(portName);
					System.out.println(node.getName());
					node.setDisplayName(portName);
					node.setUserObject(info);
					needChooseBox.addElement(node);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	private void addActionLister() {
		try {
			jDialog=this;
			//添加事件
			addButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					moveList(needChooseBox.getSelectionModel().getAllSelectedElement(),needChooseBox,choosedBox);
				}
			});
			//删除事件
			removeButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					moveLists(choosedBox.getSelectionModel().getAllSelectedElement(),choosedBox);
				}
			});
			//保存事件
			this.confirm.addActionListener(new MyActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					jDialog.dispose();
				}

				@Override
				public boolean checking() {
					
					return true;
				}
			});
			//取消事件
			this.cancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					jDialog.dispose();
				}
			});
			//下拉列表事件
			VLANID_.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					needChooseBox.clear();
					for(PortConfigInfo info:listPortDispositionInfo){
						if(info.getVlanID().equals(VLANID_.getSelectedItem())){
							Node node = new Node();
							try {
								node.setName(UiUtil.getCodeById(info.getPort()).getCodeName());
								node.setDisplayName(UiUtil.getCodeById(info.getPort()).getCodeName());
								node.setUserObject(info);
								needChooseBox.addElement(node);
							} catch (Exception e) {
								ExceptionManage.dispose(e,this.getClass());
							}
						}
					}
					
				}
			});
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * @param elements
	 * @param source
	 * @param target
	 */
	private void moveList(List<Element> elements, TDataBox source, TDataBox target) {
		Iterator<Element> it =null;
		try {
			it = elements.iterator();
			if(elements.size()>1){
				while (it.hasNext()) {
					Element element = it.next();
					if (!element.isSelected()) {
						it.remove();
					}
//				source.removeElement(element);
				   target.addElement(element);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			it=null;
		}
	}
	/**
	 * @param elements
	 * @param source
	 */
	private void moveLists(List<Element> elements, TDataBox source) {
		Iterator<Element> it =null;
		try {
			it = elements.iterator();
			if(elements.size()>1){
				while (it.hasNext()) {
					Element element = it.next();
					if (!element.isSelected()) {
						it.remove();
					}
				source.removeElement(element);
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			it=null;
		}
	}
	/**
	 *过滤VLANID 
	 * @param portDispositionList
	 * @return
	 */
	private List<PortConfigInfo> deleteSame(List<PortConfigInfo> portDispositionList){
		List<PortConfigInfo> lists=null;
		try {
			lists=new ArrayList<PortConfigInfo>();
			lists.addAll(portDispositionList);
			int ss=lists.size();
			for(int i=0;i<ss;i++){
				for(int j=lists.size()-1;j>i;j--){
					if(lists.get(i).getVlanID().equals(lists.get(j).getVlanID())){
						lists.remove(j);
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
		}
		return lists;
	}

	
}
