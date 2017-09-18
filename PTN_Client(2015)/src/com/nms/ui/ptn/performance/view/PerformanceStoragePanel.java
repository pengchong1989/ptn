package com.nms.ui.ptn.performance.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import com.nms.model.system.PerformanceRamService_MB;
import com.nms.model.util.Services;
import com.nms.rmi.ui.AutoDatabaseBackThradUtil;
import com.nms.ui.Ptnf;
import com.nms.ui.manager.ConstantUtil;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysTab;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.performance.model.PerformanceRAMInfo;

public class PerformanceStoragePanel extends PtnDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7512352924996291890L;

	private JCheckBox time = null;
	private JTextField timeField = null;
	private JCheckBox performanceRAM = null;
	private JTextField ram= null;
	private JButton confirm = null;
	private JButton cancel = null;
	PerformanceRAMInfo performanceRAMInfo = null;
	
	public PerformanceStoragePanel() {
		init();
	}

	private void init() {
		
		try {
			time = new JCheckBox(ResourceUtil.srcStr(StringKeysTab.PERFORMANCETIME));
			timeField = new JTextField();
			timeField.setEditable(false);
			ram = new JTextField();
			ram.setEditable(false);
			performanceRAM = new JCheckBox(ResourceUtil.srcStr(StringKeysTab.PERFORMANCESIZE));
			confirm = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM));
			cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
			this.setTitle(ResourceUtil.srcStr(StringKeysTab.PERFORMANMANAGE));
			initBorderLayout();
			this.setResizable(false);
			setValue();
			addButtonListener();
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	
	
	private void addButtonListener() {
		try {
			this.confirm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					btnSaveConfirm();
				}
			});
			
			this.cancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
				   cancel();
				}
			});
			
			time.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if(time.isSelected()){
						timeField.setEditable(true);
					}else{
						timeField.setEditable(false);
					}
				}
			});
			
			performanceRAM.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(performanceRAM.isSelected()){
						ram.setEditable(true);
					}else{
						ram.setEditable(false);
					}
				}
			});
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	/**
	 * 取消事件
	 * 
	 */
	private void cancel() {
		
		this.dispose();
		
	}

	@SuppressWarnings("deprecation")
	private void btnSaveConfirm() {
		PerformanceRamService_MB performanceRamService = null;
		try {
			performanceRamService = (PerformanceRamService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PERFORMANCERAM);
			performanceRAMInfo.setUserName(ConstantUtil.user.getUser_Name());
			performanceRAMInfo.setTimeSelect(time.isSelected()?"true":"false");
			performanceRAMInfo.setTimeValue(timeField.getText().trim());
			performanceRAMInfo.setRamPerformanceSelect(performanceRAM.isSelected()?"true":"false");
			performanceRAMInfo.setRamValue(ram.getText().trim());
			performanceRamService.save(performanceRAMInfo);
			//设置完成后启动线程
			if(isStartTask()){
				startThrad();
			}
			DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CONFIG_SUCCESS));
			this.dispose();
			
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(performanceRamService);
		}
	}

	private void setValue() {
		PerformanceRamService_MB performanceRamService = null;
		try {
			performanceRamService = (PerformanceRamService_MB)ConstantUtil.serviceFactory.newService_MB(Services.PERFORMANCERAM);
			performanceRAMInfo = performanceRamService.select(ConstantUtil.user.getUser_Name());
			
			if(performanceRAMInfo.getTimeSelect()!= null){
				time.setSelected(true);
				timeField.setEditable(true);
			}
			if(performanceRAMInfo.getRamPerformanceSelect()!= null){
				performanceRAM.setSelected(true);
				ram.setEditable(true);
			}
			if(performanceRAMInfo.getTimeValue()!= null){
				timeField.setText(performanceRAMInfo.getTimeValue());
			}
			if(performanceRAMInfo.getRamValue() != null ){
				ram.setText(performanceRAMInfo.getRamValue());
			}
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			UiUtil.closeService_MB(performanceRamService);
		}
	}
	
	/**
	 * 如果存在这个线程就不在创建
	 * @return
	 */
	private boolean isStartTask(){
		boolean flag = false;
		Map<String,Runnable> threadMap = Ptnf.getPtnf().getThreadMap();
		try {
			if(threadMap != null){
				if(threadMap.get("task_performanceRam_4") != null){
					return false;
				}
			}
		flag = true;
		} catch (Exception e) {
			ExceptionManage.dispose(e, getClass());
		}
		return flag;
	}

	private void startThrad(){
		
		//开启自动的默认备份数据 不影响其他的启动线程任务 2个小时查询一次
		long cycleTime = 2*60*60*1000;
		AutoDatabaseBackThradUtil autoDatabaseBackThradUtil = new AutoDatabaseBackThradUtil();
		try {
			autoDatabaseBackThradUtil.createThread(cycleTime);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}finally{
			 autoDatabaseBackThradUtil = null;
		}
	}
	private void initBorderLayout() {
		GridBagLayout gridBagLayouts = null;
		GridBagConstraints gridBagConstraints = null;
		try {
			gridBagLayouts = new GridBagLayout();
			gridBagConstraints = new GridBagConstraints();

			this.setLayout(gridBagLayouts);
			
			gridBagLayouts.columnWidths = new int[] { 50, 200, 50 };
			gridBagLayouts.columnWeights = new double[] { 0, 0, 0 };
			gridBagLayouts.rowHeights = new int[] { 40, 40, 40, 40};
			gridBagLayouts.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.2 };
			
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.gridwidth = 1;
			gridBagConstraints.insets = new Insets(20, 5, 15, 5);
			gridBagLayouts.setConstraints(time, gridBagConstraints);
			this.add(time);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.gridwidth = 2;
			gridBagLayouts.setConstraints(timeField, gridBagConstraints);
			this.add(timeField);
			
			gridBagConstraints.insets = new Insets(15, 5, 15, 5);
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.gridwidth = 1;
			gridBagLayouts.setConstraints(performanceRAM, gridBagConstraints);
			this.add(performanceRAM);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.gridwidth = 2;
			gridBagLayouts.setConstraints(ram, gridBagConstraints);
			this.add(ram);
			
			gridBagConstraints.fill = GridBagConstraints.NONE;
			gridBagConstraints.anchor = GridBagConstraints.EAST;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 3;
			gridBagConstraints.gridwidth = 1;
			gridBagConstraints.insets = new Insets(5, 5, 0, 10);
			gridBagLayouts.setConstraints(confirm, gridBagConstraints);
			this.add(this.confirm);
			
			gridBagConstraints.gridx = 2;
			gridBagConstraints.insets = new Insets(5, 5, 0, 10);
			gridBagLayouts.setConstraints(this.cancel, gridBagConstraints);
			this.add(this.cancel);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
}
