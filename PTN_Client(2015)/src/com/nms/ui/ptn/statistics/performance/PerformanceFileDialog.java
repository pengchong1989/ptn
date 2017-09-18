package com.nms.ui.ptn.statistics.performance;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import twaver.Element;
import twaver.Node;

import com.nms.db.bean.perform.Capability;
import com.nms.db.bean.system.code.Code;
import com.nms.db.enums.EMonitorCycle;
import com.nms.ui.manager.ControlKeyValue;
import com.nms.ui.manager.DialogBoxUtil;
import com.nms.ui.manager.ExceptionManage;
import com.nms.ui.manager.MyActionListener;
import com.nms.ui.manager.ResourceUtil;
import com.nms.ui.manager.UiUtil;
import com.nms.ui.manager.control.NeTreePanel;
import com.nms.ui.manager.control.PtnButton;
import com.nms.ui.manager.control.PtnDialog;
import com.nms.ui.manager.keys.StringKeysBtn;
import com.nms.ui.manager.keys.StringKeysObj;
import com.nms.ui.manager.keys.StringKeysOperaType;
import com.nms.ui.manager.keys.StringKeysTip;
import com.nms.ui.ptn.performance.view.TimeWindow;
/**
 * 性能统计   设置过滤
 * @author sy
 *
 */
public class PerformanceFileDialog extends PtnDialog {

	private static final long serialVersionUID = 2219190791950771503L;
	private PtnButton confirm;
	private JButton cancel;
	private JButton clear;
	private JLabel lblTaskObj;
	private JLabel lblCycle;
	private JComboBox selectTimeType; 
	private JRadioButton rb15min;
	private JRadioButton rb24hour;
	private JComboBox selectTimeTypeOther; 
	private ButtonGroup group;
	private String filterInfo;
    private JPanel buttonPanel;
    private PerformanceInfoPanel view;
	private JLabel endTime;
	private JComboBox selectEndTime;
	private ControlKeyValue newTime;
	private NeTreePanel neTreePanel;//网元树

	public PerformanceFileDialog(PerformanceInfoPanel view) {
		this.view=view;
		this.setModal(true);
		this.init();
		UiUtil.showWindow(this, 420, 560);
	}

	public void init() {
		try {
			this.initComponents();
			this.setLayout();
			this.addListener();
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
		
	}
	public PtnButton getConfirm() {
		return confirm;
	}

	private void addListener() {

		selectEndTime.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				if (arg0.getStateChange() == ItemEvent.SELECTED) {
					ControlKeyValue todTransmissionTimeTypekey_broad = (ControlKeyValue) selectEndTime.getSelectedItem();
					Code code = (Code) (todTransmissionTimeTypekey_broad.getObject());
					if (code != null && code.getCodeValue().equals("6")) {
						TimeWindow timewndow = new TimeWindow();
						UiUtil.showWindow(timewndow, 300, 200);
						if(!(timewndow.getStartTimeText().getText()==null||timewndow.getStartTimeText().getText().equals("")||
								timewndow.getEndTimeText().getText()==null||timewndow.getEndTimeText().getText().equals(""))){
							view.setStartTime(timewndow.getStartTimeText().getText());
							view.setReadEndTime(timewndow.getEndTimeText().getText());
							String addItems = ResourceUtil.srcStr(StringKeysTip.START) + view.getStartTime()+ ResourceUtil.srcStr(StringKeysTip.END)+ view.getReadEndTime();
							if (newTime != null) {
								selectEndTime.removeItem(newTime);
							}
							newTime = new ControlKeyValue("7",addItems);
							selectEndTime.addItem(newTime);
							selectEndTime.setSelectedItem(newTime);
						}
					}
				}
			}
		});

		// 取消按钮
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				PerformanceFileDialog.this.dispose();
			}
		});

		// 清除按钮
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PerformanceFileDialog.this.clear();
			}
		});
	
		rb24hour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(rb24hour.isSelected()){
					rb15min.setSelected(false);
					
					// 将监控周期 传回 界面（长期性能对象中）
					view.setMonitorCycle(EMonitorCycle.HOUR24);
				}
			}
		});
		rb15min.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(rb15min.isSelected()){
					rb24hour.setSelected(false);
					view.setMonitorCycle(EMonitorCycle.MIN15);
				}
			}
		});
		confirm.addActionListener(new MyActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				view.clear();
				int count=0;
				/**
				 * 选取 ： 网元树   选中的 节点  
				 * 		
				 */
				try {
					List<Element> portElement=PerformanceFileDialog.this.neTreePanel.getElement();
					if(portElement!=null&&portElement.size()>0){
						for(Element element:portElement){
							if(element instanceof Node){
								Node node=(Node)element;
								//选中的 性能参数
								if(node!=null&&node.getUserObject() instanceof Capability){
									count++;
								}
							}
						}
						if(count == 0){
						DialogBoxUtil.succeedDialog(PerformanceFileDialog.this, ResourceUtil.srcStr(StringKeysTip.TIP_PERFORMANCEPORT_MAX));
						count=0;
						return;
						}
						if(count>35){
							DialogBoxUtil.succeedDialog(PerformanceFileDialog.this, ResourceUtil.srcStr(StringKeysTip.TIP_PERFORMANCE_MAX));
							count=0;
							return;
						}
						count=0;
					}
					view.setPortElement( portElement);
					/**
					 * 取得  結束時間 （選擇框）
					 * 		code 值
					 */
					String value="";
					if(selectEndTime.getSelectedItem().equals(newTime)){
						value=7+"";
					}else{
						ControlKeyValue controlKeyValue=(ControlKeyValue)selectEndTime.getSelectedItem();
						 value=((Code)controlKeyValue.getObject()).getCodeValue();
						if("0".equals(value)){
							DialogBoxUtil.succeedDialog(PerformanceFileDialog.this, ResourceUtil.srcStr(StringKeysTip.TIP_INPUT_OVERTIME));
							return;
						}
					}
					view.setCode(Integer.parseInt(value));
					if(portElement == null || portElement.size() == 0){
						JOptionPane.showMessageDialog(PerformanceFileDialog.this, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_PERFORMANCE_TYPE));
						return ;
					}
					view.bingData(portElement);
					Boolean flag=validateParams();
					if(flag){
						PerformanceFileDialog.this.dispose();
					}
				} catch (Exception e1) {
					ExceptionManage.dispose(e1, PerformanceFileDialog.class);
				}
				
			}
			
			@Override
			public boolean checking() {
				return true;
			}
		});
	}

	/**
	 * 验证输入数据的正确性
	 * 
	 * @return
	 */
	public boolean validateParams() {
//		Iterator it = this.fileTree.getBox().getSelectionModel().selection();
//		if (!it.hasNext()) {
//			DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_MONITORING_OBJ));
//			return false;
//		}
//		it = typeBox.getSelectionModel().selection();
//		if (!it.hasNext()) {
//			DialogBoxUtil.succeedDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_PERFORMANCE_TYPE));
//			return false;
//		}
		if (!rb15min.isSelected() && !rb24hour.isSelected()) {
			JOptionPane.showMessageDialog(this, ResourceUtil.srcStr(StringKeysTip.TIP_CHOOSE_MONITORING_PERIOD));
			return false;
		}
		return true;
	}


	// 清除面板信息
	private void clear() {
		this.neTreePanel.clear();
		group.clearSelection();
		if(selectTimeTypeOther.isEnabled()){
			selectTimeTypeOther.setEnabled(false);
		}
		if(selectTimeType.isEnabled()){
			selectTimeType.setEnabled(false);
		}
	}

	private void initComponents() throws Exception {
		this.setTitle(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER));
		lblTaskObj = new JLabel(ResourceUtil.srcStr(StringKeysObj.MONITORING_OBJ));	
		this.neTreePanel=new NeTreePanel(false, 6, null,true);
		lblCycle = new JLabel(ResourceUtil.srcStr(StringKeysObj.MONITORING_PERIOD));
		group = new ButtonGroup();
		rb15min = new JRadioButton("15" + ResourceUtil.srcStr(StringKeysObj.MINUTES));
		rb24hour = new JRadioButton("24" + ResourceUtil.srcStr(StringKeysObj.HOURS));
		group.add(rb15min);
		group.add(rb24hour);
		rb15min.setSelected(true);
		view.setMonitorCycle(EMonitorCycle.MIN15);
		selectTimeType=new JComboBox(); 
		selectTimeType.addItem(ResourceUtil.srcStr(StringKeysObj.NOWFifteMINUTES));
		String now=ResourceUtil.srcStr(StringKeysObj.NOWTIMEMINUTES);
		String nowfifte=ResourceUtil.srcStr(StringKeysObj.TIMEMINUTES);
		for(int i=1;i<17;i++){
			selectTimeType.addItem(now+i+nowfifte);
		}
		confirm = new PtnButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CONFIRM),false);
		cancel = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_CANEL));
		
		buttonPanel=new JPanel();
		buttonPanel.add(confirm);
		buttonPanel.add(cancel);
		setCompentLayoutButton(buttonPanel,confirm,cancel);
		clear = new JButton(ResourceUtil.srcStr(StringKeysBtn.BTN_FILTER_CLEAR));
		endTime = new JLabel(ResourceUtil.srcStr(StringKeysOperaType.BTN_OVER_TIME));
		selectEndTime = new JComboBox();
		super.getComboBoxDataUtil().comboBoxData(this.selectEndTime, "hisPerformCount");
	}
	/**
	 *  按钮所在按钮布局
	 */
	private void setCompentLayoutButton(JPanel jpenl,JButton button1,JButton button2) {
		GridBagConstraints gridBagConstraints=null;
		GridBagLayout gridBagLayout = null;
		try {
			gridBagLayout = new GridBagLayout();
			gridBagConstraints = new GridBagConstraints();
			gridBagLayout.columnWidths=new int[]{15,15};
			gridBagLayout.columnWeights=new double[]{0,0};
			gridBagLayout.rowHeights=new int[]{21};
			gridBagLayout.rowWeights=new double[]{0};
			
			gridBagConstraints.insets=new Insets(5,5,5,5);
			gridBagConstraints= new GridBagConstraints();
			gridBagConstraints.fill=GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(button1, gridBagConstraints);
			
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.gridx = 1;
			gridBagConstraints.gridy = 0;
			gridBagLayout.setConstraints(button2, gridBagConstraints);
			
			jpenl.setLayout(gridBagLayout);
		} catch (Exception e) {
			ExceptionManage.dispose(e,this.getClass());
		}
	}
	private void setLayout() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWidths = new int[] { 40, 80, 40, 40, 230 };
		layout.columnWeights = new double[] { 0, 0, 0, 0, 0.2 };
		layout.rowHeights = new int[] {   20, 120,  20, 20 };
		layout.rowWeights = new double[] { 0, 0.2,  0, 0 };
		this.setLayout(layout);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.setConstraints(lblTaskObj, c);
		this.add(lblTaskObj);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(this.neTreePanel, c);
		this.add(this.neTreePanel);


		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(lblCycle, c);
		this.add(lblCycle);
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 0);
		layout.addLayoutComponent(rb15min, c);
		this.add(rb15min);
		c.gridx = 2;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(rb24hour, c);
		this.add(rb24hour);
//		c.fill = GridBagConstraints.BOTH;
//		c.gridx = 0;
//		c.gridy = 3;
//		c.gridheight = 1;
//		c.gridwidth = 1;
//		c.insets = new Insets(5, 5, 5, 10);
//		layout.addLayoutComponent(filterZero, c);
//		this.add(filterZero);
//		c.fill = GridBagConstraints.BOTH;
//		c.gridx = 1;
//		c.gridy =3;
//		c.gridheight = 1;
//		c.gridwidth = 1;
//		c.insets = new Insets(5, 5, 5, 10);
//		layout.addLayoutComponent(filterZeroBox, c);
//		this.add(filterZeroBox);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(endTime, c);
		this.add(endTime);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 100;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(selectEndTime, c);
		this.add(selectEndTime);
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 10);
		layout.addLayoutComponent(clear, c);
		this.add(clear);
		c.gridx = 3;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		layout.addLayoutComponent(confirm, c);
		this.add(confirm);
		c.gridx = 4;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.CENTER;
		layout.addLayoutComponent(cancel, c);
		this.add(cancel);

	}

	public String getFilterInfo() {
		return filterInfo;
	}

	public void setFilterInfo(String filterInfo) {
		this.filterInfo = filterInfo;
	}

}

