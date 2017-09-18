package com.nms.ui.ptn.performance.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;

public class HisPerformanceExport extends PtnDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1162815286129491555L;
	private PtnButton confirm;
	private JButton cancel;
	private ButtonGroup buttonGroup;
	private JRadioButton nowExport;//导出当前的数据
	private JRadioButton allExport;//导出所有的数据
	private JRadioButton timeExport;//根据时间来导出
	private JLabel timeLbl;
	private JTextField timeTextField;
	private GridBagLayout gridBagLayout=null;
	private JPanel buttonPanel=null;
	private JDialog jDialog=null;
	public HisPerformanceExport() {
		this.setModal(true);
		init();
	}
	private void init() {
		initComponents();
		setLayout();
		addListener();
	}
	/**
	 * 初始化控件
	 */ 
	private void initComponents() {	 
		try {
			gridBagLayout=new GridBagLayout();
			buttonGroup=new ButtonGroup();
			nowExport=new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_NOW_EXPORT));
			nowExport.setSelected(true);
			allExport=new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_ALL_EXPORT));
			timeExport=new JRadioButton(ResourceUtil.srcStr(StringKeysBtn.BTN_TIME_EXPORT_LABLE));
			buttonGroup.add(nowExport);
			buttonGroup.add(allExport);
			buttonGroup.add(timeExport);
			timeLbl=new JLabel(ResourceUtil.srcStr(StringKeysBtn.BTN_TIME_EXPORT_LABLE)+":");
			timeTextField=new JTextField();
			timeTextField.setEditable(false);
			confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM),false);
			cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
			this.setTitle(ResourceUtil.srcStr(StringKeysBtn.BTN_EXPORT));
			//设置按钮的布局
			buttonPanel=new JPanel();
			buttonPanel.add(confirm);
			buttonPanel.add(cancel);
//			txte=new JPanel();
////			txte.add(timeLbl);
////			txte.add(timeTextField);
//			setTextLayout();
			setButtonLayout();
			setLayout();
			this.setLayout(gridBagLayout);
			this.add(nowExport);
			this.add(allExport);
			this.add(timeExport);
			this.add(buttonPanel);
			this.add(timeLbl);
			this.add(timeTextField);
//			this.add(txte); 
			addListener();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}

	private void setLayout() {
		GridBagConstraints gridBagConstraints = null;
		try {
			gridBagConstraints=new GridBagConstraints();
			gridBagLayout.columnWidths = new int[] { 30,30,30,30,30,30};
			gridBagLayout.columnWeights = new double[] {0,0,0};
			gridBagLayout.rowHeights = new int[] {35, 35, 35, 35, 35, 35, 35, 35};
			gridBagLayout.rowWeights = new double[] { 0, 0, 0, 0, 0, 0, 0, 0};
			gridBagConstraints.insets = new Insets(5, 10, 0, 0); 
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(nowExport, gridBagConstraints);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 1;
			gridBagLayout.setConstraints(allExport, gridBagConstraints);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 2;
			gridBagLayout.setConstraints(timeExport, gridBagConstraints);
			
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(timeLbl, gridBagConstraints);
			
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 3;
			gridBagLayout.setConstraints(timeTextField, gridBagConstraints);
			
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 5;
			gridBagLayout.setConstraints(buttonPanel, gridBagConstraints);

		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	private void addListener() {
		jDialog=this;
		try {
			
			this.nowExport.addActionListener(new ActionListener() {
				
				@Override 
				public void actionPerformed(ActionEvent arg0) {
					
					timeTextField.setText("");
					timeTextField.setEditable(false);
				}
			});
			this.allExport.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					timeTextField.setText("");
					timeTextField.setEditable(false);
				}
			});
			this.timeExport.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					timeTextField.setText(sdf.format(new Date()));
					timeTextField.setEditable(true);
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
	 *  按钮所在panel布局
	 */
	private void setButtonLayout() throws Exception {
		GridBagConstraints gridBagConstraints=null;
		GridBagLayout gridBagLayout = null;
		try {
			gridBagLayout = new GridBagLayout();
			gridBagConstraints = new GridBagConstraints();
			gridBagLayout.columnWidths=new int[]{20,20};
			gridBagLayout.columnWeights=new double[]{0,0};
			gridBagLayout.rowHeights=new int[]{21};
			gridBagLayout.rowWeights=new double[]{0};
			
			gridBagConstraints.insets=new Insets(5,10,5,0);
			gridBagConstraints= new GridBagConstraints();
			gridBagConstraints.fill=GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(confirm, gridBagConstraints);
			
			gridBagConstraints.insets = new Insets(5, 25, 5, 5);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(cancel, gridBagConstraints);
			
			buttonPanel.setLayout(gridBagLayout);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public PtnButton getConfirm() {
		return confirm;
	}
	public void setConfirm(PtnButton confirm) {
		this.confirm = confirm;
	}
	public ButtonGroup getButtonGroup() {
		return buttonGroup;
	}
	public JRadioButton getNowExport() {
		return nowExport;
	}
	public JRadioButton getAllExport() {
		return allExport;
	}
	public JRadioButton getTimeExport() {
		return timeExport;
	}
	public JTextField getTimeTextField() {
		return timeTextField;
	}
	
}
