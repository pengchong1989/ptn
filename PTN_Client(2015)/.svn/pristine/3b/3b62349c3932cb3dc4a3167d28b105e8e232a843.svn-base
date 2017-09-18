package com.nms.ui.ptn.clock.view.cx.dialog;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import com.nms.db.bean.ptn.clock.ExternalClockInterface;
import com.nms.model.ptn.clock.ExternalClockInterfaceService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.util.RmiKeys;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DispatchUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysLbl;
import com.nms.ui.ptn.clock.view.cx.clockinterface.TabPanelOneTICX;

public class ExternalClockDialog extends PtnDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4620234571834602642L;
	private JLabel slot1 = null;
	private ButtonGroup cbgslot1 = null;
	private JRadioButton slot1TodDisposition = null;// slot1-配置TOD
	private JRadioButton slot1ExtclkDisposition = null;// slot1-配置extclk
	private JRadioButton slot1deleteClockInteface1 = null;// slot1-刪除时钟接口
	private JLabel slot2 = null;
	private ButtonGroup cbgslot2 = null;
	private JRadioButton slot2TodDisposition = null;// slot2-配置TOD
	private JRadioButton slot2ExtclkDisposition = null;// slot2-配置extclk
	private JRadioButton slot2deleteClockInteface2 = null;// slot2-刪除时钟接口
	private PtnButton confirm=null;
	private JButton cancel=null; 
	private JDialog jDialog = null;
	private JPanel checkPanel=null;
	private GridBagLayout gridBagLayout = null;
    private TabPanelOneTICX tabPanelOneTICX =null;
    private JScrollPane contentScrollPane; //添加滚动条
    private JPanel contPanel;
    
	public ExternalClockDialog(TabPanelOneTICX tabPanelOneTICX) {
		this.tabPanelOneTICX=tabPanelOneTICX;
		init();
	}

	/**
	 *初始化界面
	 */
	private void init() {
		try {
			gridBagLayout = new GridBagLayout();
			slot1 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK1_INTEFACE));
			cbgslot1 = new ButtonGroup();
			slot1TodDisposition = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_TODDISPOSITION));
			slot1ExtclkDisposition = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_EXtCLKDiSPOSITION));
			slot1deleteClockInteface1 = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_DELETECLOCKINTEFACE));
			cbgslot1.add(slot1TodDisposition);
			cbgslot1.add(slot1ExtclkDisposition);
			cbgslot1.add(slot1deleteClockInteface1);
			slot1deleteClockInteface1.setVisible(false);
			slot2 = new JLabel(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK2_INTEFACE));
			cbgslot2 = new ButtonGroup();   
			slot2TodDisposition = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_TODDISPOSITION));
			slot2ExtclkDisposition = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_EXtCLKDiSPOSITION));
			slot2deleteClockInteface2 = new JRadioButton(ResourceUtil.srcStr(StringKeysLbl.LBL_DELETECLOCKINTEFACE));
			cbgslot2.add(slot2TodDisposition);
			cbgslot2.add(slot2ExtclkDisposition);
			cbgslot2.add(slot2deleteClockInteface2);
			slot2deleteClockInteface2.setVisible(false);
			
			confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM),false);
			cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL)); 
			
			contPanel=new javax.swing.JPanel();
			
			setStlot2Layout();
			setGridBagLayout();/* 主窗口布局 */
			contPanel.setLayout(gridBagLayout);
			contPanel.add(slot1);
			contPanel.add(slot1TodDisposition);
			contPanel.add(slot1ExtclkDisposition);
			contPanel.add(slot1deleteClockInteface1);
			contPanel.add(slot2);
			contPanel.add(slot2TodDisposition);
			contPanel.add(slot2ExtclkDisposition);
			contPanel.add(slot2deleteClockInteface2);
			//contPanel.add(checkPanel);
			contentScrollPane=new javax.swing.JScrollPane(contPanel);
			contentScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//			contentScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			
			setLayout();
			this.setTitle(ResourceUtil.srcStr(StringKeysLbl.LBL_CLOCK_INTEFACEDISPOSITION));
			setVisable();
			addButtonListener();
			UiUtil.showWindow(this,450, 300);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass()); 
		}

	}
	/**
	 *用于判断各个单选按钮的初始时候 
	 */
	private void setVisable() {
    try {
    	//判断删除时钟什么时候可以显示
		if(tabPanelOneTICX.getTable().getAllElement().size()>0){
			for(ExternalClockInterface cc:tabPanelOneTICX.getTable().getAllElement()){
				if(cc.getInterfaceName().contains("1.1")){
					if(cc.getInterfaceName().contains("tod")){
						slot1TodDisposition.setSelected(true);
					}else{
						slot1ExtclkDisposition.setSelected(true);
					}
					slot1deleteClockInteface1.setVisible(true);
				}
				else if(cc.getInterfaceName().contains("2.1")){
					
					if(cc.getInterfaceName().contains("extclk")){
						slot2ExtclkDisposition.setSelected(true);
					}else{
						slot2TodDisposition.setSelected(true);
					}
					slot2deleteClockInteface2.setVisible(true); 
				}
			}
		}else{
			slot1deleteClockInteface1.setVisible(false);
			slot2deleteClockInteface2.setVisible(false);
		}
	} catch (Exception e) {
		ExceptionManage.dispose(e,this.getClass()); 
	}		
	}
	/**
	 * 设置布局
	 */
	private void setLayout() {
		GridBagConstraints c=null;
		GridBagLayout gridLayout = null;
		gridLayout = new GridBagLayout();
		c = new GridBagConstraints();
		this.setLayout(gridLayout);
		gridLayout.columnWidths=new int[]{350};
		gridLayout.columnWeights=new double[]{0.3};
		gridLayout.rowHeights=new int[]{80,40};
		gridLayout.rowWeights=new double[]{0.1,0.1};
		c.insets=new Insets(5,5,5,10);
		c= new GridBagConstraints();
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		gridLayout.setConstraints(this.contPanel, c);
		this.add(contPanel);
		c.gridy=1;
		c.insets=new Insets(5,200,5,10);
		gridLayout.setConstraints(this.checkPanel, c);
		this.add(checkPanel);
		}
	/**
	 * 设置按钮的布局
	 */
	private void setStlot2Layout() {
		GridBagConstraints gridBagConstraints=null;
		GridBagLayout gridLayout = null;
		try {
			gridLayout = new GridBagLayout();
			gridBagConstraints = new GridBagConstraints();
			checkPanel=new JPanel();
			checkPanel.setLayout(gridLayout);
			
			gridLayout.columnWidths=new int[]{20,20};
			gridLayout.columnWeights=new double[]{0,0};
			gridLayout.rowHeights=new int[]{21};
			gridLayout.rowWeights=new double[]{0};
			gridBagConstraints.insets=new Insets(5,10,5,0);
			gridBagConstraints= new GridBagConstraints();
			gridBagConstraints.fill=GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridLayout.setConstraints(confirm, gridBagConstraints);
			checkPanel.add(confirm);
			
			gridBagConstraints.insets = new Insets(5, 25, 5, 5);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridLayout.setConstraints(cancel, gridBagConstraints);
			checkPanel.add(cancel);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}


	/**
	 *处理事件 
	 */
	private void addButtonListener() {
		try {
			jDialog=this;
			this.confirm.addActionListener(new MyActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					btnSaveData();
					jDialog.dispose();
				}

				@Override
				public boolean checking() {
					
					return true;
				}
			});
			this.cancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					jDialog.dispose();
				}
			});
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
/**
 * 处理保存事件
 * 
 */
	private void btnSaveData() {
		ExternalClockInterface  externalClockInterface=null;
		ExternalClockInterfaceService_MB externalClockInterfaceService=null;
		List<ExternalClockInterface> externalClockInterfaceList=null;
		DispatchUtil externalClockDispatch;
		int operationKey=0;
       try {
    	   externalClockDispatch = new DispatchUtil(RmiKeys.RMI_EXTERNALCLOCK);
    	 externalClockInterfaceService=(ExternalClockInterfaceService_MB)ConstantUtil.serviceFactory.newService_MB(Services.ExternalClockInterfaceService);
    	 externalClockInterface=new ExternalClockInterface();
    	 externalClockInterfaceList=new ArrayList<ExternalClockInterface>();
    	 externalClockInterface.setSiteId(ConstantUtil.siteId);
    	 externalClockInterfaceList=externalClockInterfaceService.select(externalClockInterface);
    	 
    	 if(slot1TodDisposition.isSelected()){
    		 
    		if(isExists(externalClockInterfaceList, "1pps/tod.1.1")){
    			externalClockInterface.setInterfaceName("1pps/tod.1.1");
    			externalClockInterface.setManagingStatus(27);//原36
    			externalClockInterface.setWorkingStatus("514");//"1,2"
    			externalClockInterface.setInterfaceMode(358);
    			externalClockInterface.setTimeOffsetCompensation("0");
    			
    			if(!isExists(externalClockInterfaceList, "1.1")){
    				
    				 for(ExternalClockInterface ss:externalClockInterfaceList){
   					  if(ss.getInterfaceName().contains("1.1")){
   						  externalClockInterface.setId(ss.getId());
   						externalClockDispatch.excuteUpdate(externalClockInterface);
   				        }
    				 }
			  }else{
//				  ExternalClockDispatch e=new ExternalClockDispatch();
				//  externalClockDispatch.excuteInsert(externalClockInterface);
//				  e.excuteInsert(externalClockInterface);
    		}
    		}
    	 }
    	  if(slot1ExtclkDisposition.isSelected()){
    		  
    		  if(isExists(externalClockInterfaceList, "extclk.1.1")){
    			  
    			  externalClockInterface.setInterfaceName("extclk.1.1");
    			  externalClockInterface.setManagingStatus(27);
    			  externalClockInterface.setWorkingStatus("514");
    			  externalClockInterface.setInterfaceMode(360);
    			  externalClockInterface.setInputImpedance(362);
    			  externalClockInterface.setSanBits(364);
    			  
    				if(!isExists(externalClockInterfaceList, "1.1")){
       				 for(ExternalClockInterface ss:externalClockInterfaceList){
      					  if(ss.getInterfaceName().contains("1.1")){
      						  externalClockInterface.setId(ss.getId());
      						externalClockDispatch.excuteUpdate(externalClockInterface);
      				        }
       				 }
   			  }else{
//   				ExternalClockDispatch e=new ExternalClockDispatch();
				//  externalClockDispatch.excuteInsert(externalClockInterface);
//				  e.excuteInsert(externalClockInterface);
   				//externalClockDispatch.excuteInsert(externalClockInterface);
       		}
    		  }
    	 }
    	  if(slot1deleteClockInteface1.isSelected()){
    		  externalClockDispatch.excuteDelete(tabPanelOneTICX.getTable().getAllElement().get(0));
    	 }
    	 
    	  if(slot2TodDisposition.isSelected()){
    		  if(isExists(externalClockInterfaceList, "1pps/tod.2.1")){
    			  externalClockInterface.setInterfaceName("1pps/tod.2.1");
    			  externalClockInterface.setManagingStatus(27);
    			  externalClockInterface.setWorkingStatus("514");
    			  externalClockInterface.setInterfaceMode(358);
    			  externalClockInterface.setTimeOffsetCompensation("0");
    			  
    				if(!isExists(externalClockInterfaceList, "2.1")){
          				 for(ExternalClockInterface ss:externalClockInterfaceList){
         					  if(ss.getInterfaceName().contains("2.1")){
         						  externalClockInterface.setId(ss.getId());
         						 externalClockDispatch.excuteUpdate(externalClockInterface);
         				        }
          				 }
      			  }else{
      				externalClockDispatch.excuteInsert(externalClockInterface);
          		}
    			  
    		  }
    	 }
    	  if(slot2ExtclkDisposition.isSelected()){
    		  if(isExists(externalClockInterfaceList, "extclk.2.1")){
    			  externalClockInterface.setInterfaceName("extclk.2.1");
    			  externalClockInterface.setManagingStatus(514);
    			  externalClockInterface.setWorkingStatus("27");
    			  externalClockInterface.setInterfaceMode(360);
    			  externalClockInterface.setInputImpedance(362);
    			  externalClockInterface.setSanBits(364);
    			  
    				if(!isExists(externalClockInterfaceList, "2.1")){
         				 for(ExternalClockInterface ss:externalClockInterfaceList){
        					  if(ss.getInterfaceName().contains("2.1")){
        						  externalClockInterface.setId(ss.getId());
        						  externalClockDispatch.excuteUpdate(externalClockInterface);
        				        }
         				 }
     			  }else{
     				 externalClockDispatch.excuteInsert(externalClockInterface);
         		}
    			  
    		  }
    	 }
    	  if(slot2deleteClockInteface2.isSelected()){
    		  externalClockDispatch.excuteDelete(tabPanelOneTICX.getTable().getAllElement().get(0));
    	 }
    	  this.confirm.setOperateKey(operationKey);
    	  tabPanelOneTICX.controller.refresh();
	} catch (Exception e) {
		ExceptionManage.dispose(e,this.getClass());
	} finally {
		UiUtil.closeService_MB(externalClockInterfaceService);
	}
	}
	/**
	 *布局组界面
	 */
	private void setGridBagLayout() {
		GridBagConstraints gridBagConstraints = null;
		try {
			gridBagConstraints=new GridBagConstraints();
			gridBagLayout.columnWidths = new int[] { 50,150,150};
			gridBagLayout.columnWeights = new double[] {0,0,0.1};
			gridBagLayout.rowHeights = new int[] { 10,50,30,50,50};
			gridBagLayout.rowWeights = new double[] { 0, 0, 0, 0, 0,};
			gridBagConstraints.insets = new Insets(5, 10, 0, 0); 
			gridBagConstraints.fill = GridBagConstraints.BOTH; 
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(slot1, gridBagConstraints);
			

			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(slot1TodDisposition, gridBagConstraints);
			
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(slot1ExtclkDisposition, gridBagConstraints);
			
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(slot1deleteClockInteface1, gridBagConstraints);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(slot2, gridBagConstraints);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(slot2TodDisposition, gridBagConstraints);
			
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(slot2ExtclkDisposition, gridBagConstraints);
			
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(slot2deleteClockInteface2, gridBagConstraints);
			
			gridBagConstraints.gridx = 2;
			gridBagConstraints.gridy = 4;
			gridBagLayout.setConstraints(checkPanel, gridBagConstraints);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	/**
	 * 
	 * @param externalClockInterfaceList
	 * @param name
	 * @return
	 */
	private boolean isExists(List<ExternalClockInterface> externalClockInterfaceList ,String name){
		boolean flas=true;
		try {
			if(null==externalClockInterfaceList){
				return true;
			}
			else{
				for(ExternalClockInterface cc:externalClockInterfaceList){
					if(cc.getInterfaceName().contains(name)){
						return false;
					}
				}
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		return flas;
	}
}
